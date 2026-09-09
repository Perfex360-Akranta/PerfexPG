package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.service.DirectKaizenService;
import com.akranta.tpm.service.impl.DirectKaizenServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class DirectKaizenServlet
 */
public class DirectKaizenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DirectKaizenService directKaizenService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
   /* public DirectKaizenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			process(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			process(request, response);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request,HttpServletResponse response)throws ServletException,Exception{		
		HttpSession httpSession = request.getSession(false);
	    String action = UIUtils.getActionPart(request);
	    try{
	    directKaizenService=(DirectKaizenServiceImpl)UIUtils.getServiceObject(request,"DirectKaizenServiceImpl");	
	    directKaizenService.DirectKaizenServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")));
	    }
	    catch(ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
	    //****************************DirectKaizen*******************************//
	    if(action.equals("NewKaizenBankSuggestion_input.dkzn")){
		String Relatedto=request.getParameter("Relatedto");
		String refDocType = request.getParameter("refDocType");
		String refdocno = request.getParameter("refDocNo");
		String mchId=request.getParameter("machineID");
		String flid = request.getParameter("flid");
		String hsesfty=request.getParameter("hsesfty");
		String frmmode=request.getParameter("frmmode");
		String keyid=request.getParameter("kznKeyid");
    	KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst();
		
		if(UIUtils.isValidKeyId(flid))
			newKznTlKaizenbankmst.setKzbnFlid(flid);

		CommonMessage.debugMsg("refDocType..."+refDocType+"..refdocno.."+refdocno);
		httpSession.setAttribute("refDocType", refDocType);
		httpSession.setAttribute("refdocno", refdocno);

		newKznTlKaizenbankmst.setKzbnRefdoctype(refDocType);
		newKznTlKaizenbankmst.setKzbnRefdocno(refdocno);
		
		if("EHS".equals(Relatedto))
		{
			request.setAttribute("Relatedto", "EHS");	
		}
		request.setAttribute("frmmode", frmmode);
		RequestDispatcher rd = request.getRequestDispatcher("/pages/NewKaizen.jsp");	
		request.setAttribute("AccSingle",request.getParameter("AccSingle"));
		String Keyid = request.getParameter("Keyid");
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		if (UIUtils.isValidKeyId(Keyid)){
			newKznTlKaizenbankmst =directKaizenService.selectMasterKeyid(Keyid);
			newKznTlKaizenbankmst.setKzbnAcrejby(user.getUsrm_ccno());
    		CommonMessage.debugMsg(newKznTlKaizenbankmst.getKzbnTargetdate().contains(Constants.futureNullDate)+" Source  "+newKznTlKaizenbankmst.getKzbnFlid());
    		String targetDate = newKznTlKaizenbankmst.getKzbnTargetdate();
    		String accDate = newKznTlKaizenbankmst.getKzbnAcceptrejon();
			 final long MILLIS_IN_A_DAY = 1000*60*60*24;    
			 DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
			 Date occuredDate = dateFormat.parse(targetDate);
			 Date oneDayBefore = new Date(occuredDate.getTime());
			 targetDate = dateFormat.format(oneDayBefore);
			 
			 Date occuredDateAcc = dateFormat.parse(accDate);
			 Date oneDayBeforeAcc = new Date(occuredDateAcc.getTime());

			 accDate = dateFormat.format(oneDayBeforeAcc);
			 newKznTlKaizenbankmst.setKzbnAcceptrejon(UIUtils.getActualDateForm(newKznTlKaizenbankmst.getKzbnAcceptrejon()));
		     newKznTlKaizenbankmst.setKzbnTargetdate(UIUtils.getActualDateForm(newKznTlKaizenbankmst.getKzbnTargetdate()));
		     newKznTlKaizenbankmst.setKzbnTargetdate(UIUtils.getActualDateForm(newKznTlKaizenbankmst.getKzbnDate()));
    		if( newKznTlKaizenbankmst.getKzbnTargetdate().contains(Constants.futureNullDate)){
			     newKznTlKaizenbankmst.setKzbnTargetdate("");
		 	  }
    		if( newKznTlKaizenbankmst.getKzbnAcceptrejon().contains(Constants.futureNullDate)){
			     newKznTlKaizenbankmst.setKzbnAcceptrejon("");
		 	  }
		     CommonMessage.debugMsg("shiftDate    "+targetDate);
		}else{
			newKznTlKaizenbankmst.setKzbnSuggestedby(user.getUsrm_ccno());
		}
		request.setAttribute("kaizenbank", newKznTlKaizenbankmst);
		request.setAttribute("mchId", mchId);
		httpSession.setAttribute("kaizenbank", newKznTlKaizenbankmst);
		request.setAttribute("ehssfty", hsesfty);	
		rd.forward(request, response);  
	    }
	    else if(action.equals("NewKaizenBankSuggestion_save.dkzn")){
	    	String saveType=request.getParameter("frmType");
    		if(!UIUtils.isValidKeyId(saveType))
    			saveType="S";
    		CommonMessage.debugMsg("saveType   "+saveType);
    		saveNewKaizenBank(request,response,saveType);
	    }
	  /*  }
	    catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
	}*/
	}
	private void saveNewKaizenBank(HttpServletRequest request,HttpServletResponse response,String type) throws Exception {
		CommonMessage.debugMsg("----My Test-----");
		HttpSession httpSession = request.getSession(true);
		String operation="other";
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String dataFlid=request.getParameter("dataFlid");
    	String dataKeyid=request.getParameter("dataKeyid");
    	String dataSuggest=request.getParameter("dataSuggest");
    	String AccSingle=request.getParameter("AccSingle");
    	String chkkzbnOthers=request.getParameter("chkkzbnOthers");
    	String refDocType = (String)httpSession.getAttribute("refDocType");
    	String refdocNo = (String)httpSession.getAttribute("refdocno");
    	String ehsrelated=request.getParameter("ehs");
    	CommonMessage.debugMsg("ehsrelated::::"+ehsrelated);
    	if( httpSession != null && user != null)
    	{	
    		String[] dataFlidArr =null;	
    		String[] dataKeyidArr =null;
    		String[] dataSuggestArr=null;
    		if(UIUtils.isValidKeyId(dataFlid) && UIUtils.isValidKeyId(dataKeyid)&&UIUtils.isValidKeyId(dataSuggest))
    		{
    			dataFlidArr = dataFlid.split(",");
	    		dataKeyidArr = dataKeyid.split(",");
	    		dataSuggestArr = dataSuggest.split(",");
	    		CommonMessage.debugMsg(dataFlidArr.length+"    "+dataKeyidArr.length);
    		}
    		KaizenFormBean kaizenFormBean =(KaizenFormBean)httpSession.getAttribute("DirectKaizenServletKaizenFormBean");
    		KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
    		GenTlEmployeemst newGenTlEmployeemst= new GenTlEmployeemst();
    		KznTlKaizenbankmst existKznTlKaizenbankmst = (KznTlKaizenbankmst) httpSession.getAttribute("kaizenbank");
    		CommonMessage.debugMsg(" Inside Servlet Action :: For Others ::  "+newKznTlKaizenbankmst.getkzbnOthers());
    		CommonMessage.debugMsg(" Inside Servlet Action :: For Others benefit ::  "+newKznTlKaizenbankmst.getKzbnBenefit());
    		CommonMessage.debugMsg(" refdocNo===" + refdocNo);
    		newKznTlKaizenbankmst.setkzbnOthers(chkkzbnOthers);
    		String approavalflag=request.getParameter("txtapproavalflag");
    		CommonMessage.debugMsg(" Inside approavalflag :: "+approavalflag);
    		newKznTlKaizenbankmst.getKzbnApprovalflag(approavalflag);
    		try{
	    		newKznTlKaizenbankmst=(KznTlKaizenbankmst)UIUtils.setBeanProperties((Object)newKznTlKaizenbankmst,request);
	    		newKznTlKaizenbankmst.setKzbnCreatedby(user.getUsrm_keyid());
	    
	    		if (UIUtils.isValidKeyId(refDocType))
	    			newKznTlKaizenbankmst.setKzbnRefdoctype(refDocType);
	    		
	    		if (UIUtils.isValidKeyId(refdocNo))
	    			newKznTlKaizenbankmst.setKzbnRefdocno(refdocNo);
	    		
	    		if(ehsrelated!=null)
	      		{
	      		if (ehsrelated.equals("Y"))
	      		{
	      			newKznTlKaizenbankmst.setKzbnEhsrelated("Y");
	      			existKznTlKaizenbankmst.setKzbnEhsrelated("Y");
	      		}
	      		else
	      		{
	      			newKznTlKaizenbankmst.setKzbnEhsrelated("N");
	      			existKznTlKaizenbankmst.setKzbnEhsrelated("N");
	      		}
	      		}
	    		
	    		String saveMsg ;
	    		
	    		CommonMessage.debugMsg("newKznTlKaizenbankmst.getKzbnKeyid()"+newKznTlKaizenbankmst.getKzbnKeyid());
					if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnKeyid())){
						 CommonMessage.debugMsg("Inside the create method");
						 existKznTlKaizenbankmst = directKaizenService.createDKaizen(newKznTlKaizenbankmst,existKznTlKaizenbankmst,type,AccSingle,dataKeyidArr,dataFlidArr,dataSuggestArr);
						 saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
						 operation="save";
						 
					}	
					else{
						newKznTlKaizenbankmst.setKzbnRefdocno(refdocNo);
			      		newKznTlKaizenbankmst.setKzbnRefdoctype(refDocType);
			      		if(ehsrelated!=null)
			      		{
			      		if (ehsrelated.equals("Y"))
			      		{
			      			newKznTlKaizenbankmst.setKzbnEhsrelated("Y");
			      			existKznTlKaizenbankmst.setKzbnEhsrelated("Y");
			      		}
			      		else
			      		{
			      			newKznTlKaizenbankmst.setKzbnEhsrelated("N");
			      			existKznTlKaizenbankmst.setKzbnEhsrelated("N");
			      		}
			      		}
			      		
						existKznTlKaizenbankmst = directKaizenService.updateDKaizen(newKznTlKaizenbankmst,existKznTlKaizenbankmst,type,AccSingle,dataKeyidArr,dataFlidArr,dataSuggestArr);
						saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update");
						operation="update"; 
					}
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				returnData.put("successData",successData);
				successData.put("AccSingle", request.getParameter("AccSingle"));
				successData.put("SuggestionNo",existKznTlKaizenbankmst.getKzbnKeyid());
				returnData.put("formClear",false);
				out.print(returnData.toString());
				Boolean clrVal=true;
	    	}catch (BusinessApplicationExceptions e){
    			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "KaizenBankValidation");
    			out.print(errMessage.toString());
    			CommonMessage.debugMsg(" e " + errMessage );
    	    }catch (ValidationExceptions e) {
				CommonMessage.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "KaizenBankValidation");
				out.print(errMessage.toString());
	    	}
	    	catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				outt.print(err.toString());
			}
    	}			
	}

}
	
