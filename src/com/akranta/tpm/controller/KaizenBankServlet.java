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

//import lotus.domino.NotesException;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;

import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.dao.impl.Constants;

import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlKaizenbankmst;

import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GridColModel;

import com.akranta.tpm.service.KaizenBankService;
import com.akranta.tpm.service.impl.KaizenBankServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

public class KaizenBankServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	KaizenBankService kaizenBankService;
	String glbLocation="";
	/*private DBActionTemplate dbActionTemplate;

	public KaizenBankServlet(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			processRequest(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			processRequest(request, response);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	private void processRequest(HttpServletRequest request,	HttpServletResponse response)throws Exception {
		
		HttpSession httpSession = request.getSession(false);
	    String action = UIUtils.getActionPart(request);
	    CommonFilter commonFilter;

		/*String Location=CommonFunctions.getLoginLocaton(request);
		CommonMessage.debugMsg("the Location"+Location);
		glbLocation=Location;
		String logFlid=CommonFunctions.getLoginFlid(request);
		CommonMessage.debugMsg("Flid:::"+logFlid);*/
	    try {
	    	kaizenBankService= (KaizenBankServiceImpl) UIUtils.getServiceObject(request,"KaizenBankServiceImpl");
		    kaizenBankService.KaizenBankServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );

	    		    	
	    	if(action.equals("KaizenBank_input.kznbnk"))
			{
	    		/*CommonMessage.debugMsg("inputAction");
	    		String Relatedto=request.getParameter("Relatedto");
	    		CommonMessage.debugMsg("45646464      "+Relatedto);
	    		request.setAttribute("Relatedto", Relatedto);
	    		if(Relatedto!=null)
	    		{
	    			UIUtils.forwardRequest(request, response, "/pages/KaizenBank.jsp");	
	    	if(action.equals("KaizenBankSuggestionMainGrid_input.kznbnk"));
					frmType="JH";
	    		}
				else 
				{
					if(action.equals("EhsSuggestionGrid_input.kznbnk"));
					Relatedto="EHS";
					UIUtils.forwardRequest(request, response, "/pages/KaizenBank.jsp");	
				}
			
					frmType="SHE";
	    		if(UIUtils.isValidKeyId(Relatedto))
	    		{
	    			RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenBank.jsp");
	    			rd.forward(request, response);
	    		}else
	    		{
	    			
	    		}
	    		request.setAttribute("Relatedto", Relatedto);*/	
	    		CommonMessage.debugMsg("Input Action");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenBank.jsp");					
				rd.forward(request, response);
			}
	    	
	    	else if(action.equals("KaizenBankSuggestionMainGrid_input.kznbnk") ||   (action.equals("EhsSuggestionGrid_input.kznbnk"))) 
			{
	    		
	    		CommonMessage.debugMsg("KaizenBankkkk");
				String Relatedto="";
				CommonMessage.debugMsg("Relatedto          "+Relatedto);
				
				
				if(action.equals("EhsSuggestionGrid_input.kznbnk"))
					request.setAttribute("Relatedto", "EHS");	
				
				String Sftysugstn=request.getParameter("Sftysugstn");  
	    		String frmMode=request.getParameter("frmMode");
				
	    		request.setAttribute("Sftysugstn", Sftysugstn);
				request.setAttribute("frmMode", frmMode);
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenBankSuggMainGrid.jsp");					
				rd.forward(request, response);
			}
	    	else if(action.equals("KaizenBankSuggestionMainGrid_getCol.kznbnk") ){   
	    		try {
	    			AdmTlUsermst user = UIUtils.getLoginUser(request);
	    			
					 commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);
					 String Relatedtos=request.getParameter("Relatedto");
					 commonFilter.setRelatedtos(Relatedtos);
					PrintWriter out = response.getWriter();
					
					String loginFlid = CommonFunctions.getLoginFlid(request);
					if ( !UIUtils.isValidKeyId(commonFilter.getFlid()))
						commonFilter.setFlid( loginFlid);
					
					commonFilter.setAbnDetectBy(user.getUsrm_ccno());
					
					String hsesfty=request.getParameter("hsesfty");
		    		  if(UIUtils.isValidKeyId(hsesfty))
		    		  commonFilter.setSafetyMode(hsesfty);
					commonFilter.setIsGetCol("Y");
					List<String []> getKznBnkGrid  = kaizenBankService.getmaingrid(commonFilter);
					//JSONObject kznabnkData = UIUtils.convertToJqGridTableObject(getKznBnkGrid,request,0,0,commonFilter.getTotalRecordCnt());
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
					String [] colHeader = getKznBnkGrid.get(1);			
					String [] colHeaderCond = getKznBnkGrid.get(0);
					
					gridColModel.setHeaderNum(1);
					//CommonMessage.debugMsg("   kznabnkData     "+kznabnkData);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.set("tableWidth", "106%%");
					//jsonObject.put("data", kznabnkData);
					jsonObject.set("tableHeight", "79%%");
					httpSession.removeAttribute("KaizenBank");
					httpSession.setAttribute("KaizenBank", jsonObject);
					out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		
			}
			else if(action.equals("KaizenBankSuggestionMainGrid_getData.kznbnk")){   
	    		
	    		  commonFilter = populateCommonFilter(request,"KaizenBankGrid",false);
	    		    String Relatedtos=request.getParameter("Relatedto");
					 commonFilter.setRelatedtos(Relatedtos);
					 
					 String loginFlid = CommonFunctions.getLoginFlid(request);
						if ( !UIUtils.isValidKeyId(commonFilter.getFlid()))
							commonFilter.setFlid( loginFlid);
						
						String hsesfty=request.getParameter("hsesfty");
			    		  if(UIUtils.isValidKeyId(hsesfty))
			    		  commonFilter.setSafetyMode(hsesfty);
						
			    		  commonFilter.setIsGetCol("N");	
	    		  List<String []> getmaingridList  = kaizenBankService.getmaingrid(commonFilter);
	             PrintWriter out = response.getWriter();
	             JSONObject getmaingriddata = UIUtils.convertToJqGridTableObject(getmaingridList,request,2,0,commonFilter.getTotalRecordCnt()+1); 
	  			 out.println(getmaingriddata);
			}
	    	
	    	//***************************************Individual Suggestion******************************************************//
		 	else if(action.equals("IndividualKaizenBankSuggestionMainGrid_input.kznbnk")) 
			{	
				String Relatedto="";
				if(action.equals("EhsSuggestionGrid_input.kznbnk"))
					request.setAttribute("Relatedto", "EHS");	
				String Sftysugstn=request.getParameter("Sftysugstn");  
	    		String frmMode=request.getParameter("frmMode");
	    		AdmTlUsermst user = UIUtils.getLoginUser(request);
	    		String EmpKeyid=user.getUsrm_ccno();
	    		CommonMessage.debugMsg("The EmpKeyid"+EmpKeyid);
	    		request.setAttribute("Sftysugstn", Sftysugstn);
				request.setAttribute("frmMode", frmMode);
				request.setAttribute("EmpKeyid",EmpKeyid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/IndividualSuggestionMainGrid.jsp");					
				rd.forward(request, response);
			}
			
				else if(action.equals("IndividualKaizenBankSuggestionMainGrid_getCol.kznbnk") ){   
	    		try {
	    			AdmTlUsermst user = UIUtils.getLoginUser(request);
	    			
					 commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);
					 String Relatedtos=request.getParameter("Relatedto");
					 commonFilter.setRelatedtos(Relatedtos);
					 PrintWriter out = response.getWriter();
			         String EmpKeyid=user.getUsrm_ccno();
			         CommonMessage.debugMsg("The EmpKeyid"+EmpKeyid);
			         commonFilter.setGetKaizenkey(EmpKeyid);
					
					String loginFlid = CommonFunctions.getLoginFlid(request);
					if ( !UIUtils.isValidKeyId(commonFilter.getFlid()))
						commonFilter.setFlid( loginFlid);
					
					commonFilter.setAbnDetectBy(user.getUsrm_ccno());
					
					String hsesfty=request.getParameter("hsesfty");
		    		  if(UIUtils.isValidKeyId(hsesfty))
		    		  commonFilter.setSafetyMode(hsesfty);
					
					List<String []> getKznBnkGrid  = kaizenBankService.getIndividualmaingrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
					String [] colHeader = getKznBnkGrid.get(0);			
					String [] colHeaderCond = getKznBnkGrid.get(1);
					
					gridColModel.setHeaderNum(1);
					//CommonMessage.debugMsg("   kznabnkData     "+kznabnkData);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.set("tableWidth", "106%%");
					//jsonObject.put("data", kznabnkData);
					jsonObject.set("tableHeight", "79%%");
					httpSession.removeAttribute("KaizenBank");
					httpSession.setAttribute("KaizenBank", jsonObject);
					out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		
			}
			else if(action.equals("IndividualKaizenBankSuggestionMainGrid_getData.kznbnk")){   
	    		     commonFilter = populateCommonFilter(request,"KaizenBankGrid",false);
	    		     String Relatedtos=request.getParameter("Relatedto");
					 commonFilter.setRelatedtos(Relatedtos);
				 	 AdmTlUsermst user = UIUtils.getLoginUser(request);
			    	 String EmpKeyid=user.getUsrm_ccno();
			    	 commonFilter.setGetKaizenkey(EmpKeyid);
			    	 CommonMessage.debugMsg("The EmpKeyid"+EmpKeyid);
					 String loginFlid = CommonFunctions.getLoginFlid(request);
						if ( !UIUtils.isValidKeyId(commonFilter.getFlid()))
							commonFilter.setFlid( loginFlid);
						String hsesfty=request.getParameter("hsesfty");
			    		  if(UIUtils.isValidKeyId(hsesfty))
			    		  commonFilter.setSafetyMode(hsesfty);
	    		  List<String []> getmaingridList  = kaizenBankService.getIndividualmaingrid(commonFilter);
	             PrintWriter out = response.getWriter();
	             JSONObject getmaingriddata = UIUtils.convertToJqGridTableObject(getmaingridList,request,3,0,commonFilter.getTotalRecordCnt()+1); 
	  			 out.println(getmaingriddata);
			}
			else if(action.equals("IndividualKaizenBankSuggestionMainGrid_getExcel.kznbnk") ){
				commonFilter = populateCommonFilter(request,"KaizenBankGrid",false);
				 AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	 String EmpKeyid=user.getUsrm_ccno();
		    	 commonFilter.setGetKaizenkey(EmpKeyid);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("KaizenBank");
				colmodel.put("title","Individual Kaizen Bank");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = kaizenBankService.getIndividualKaizenMainExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "KaizenBank", format);		
			}
	    	
			else if(action.equals("KaizenBankPopup_input.kznbnk") )
			{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenBankPopup.jsp");	
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
				newKznTlKaizenbankmst.setKzbnAcrejby(user.getUsrm_ccno());
				newKznTlKaizenbankmst.setKzbnImplementedby(user.getUsrm_ccno());
				newKznTlKaizenbankmst.setKzbnResponsibility(user.getUsrm_ccno());
				newKznTlKaizenbankmst.setKzbnCompletedby(user.getUsrm_ccno());
				newKznTlKaizenbankmst.setKzbnVerifiedby(user.getUsrm_ccno());
				CommonMessage.debugMsg("frmtype=   "+request.getParameter("frmType"));
				request.setAttribute("kaizenbank", newKznTlKaizenbankmst);
				request.setAttribute("frmType",request.getParameter("frmType"));
				rd.forward(request, response);
			}
			else if(action.equals("KaizenBankSuggestion_input.kznbnk") )
			{
				CommonMessage.debugMsg("AccSingle " +request.getParameter("AccSingle"));
				CommonMessage.debugMsg("inputAction");
	    		String Relatedto=request.getParameter("Relatedto");
	    		String refDocType = request.getParameter("refDocType");
	    		CommonMessage.debugMsg("The refDocType"+refDocType);
	    		String refdocno = request.getParameter("refDocNo");
	    		CommonMessage.debugMsg("The refdocno"+refdocno);
	    		String mchId=request.getParameter("machineID");
	    		String flid = request.getParameter("flid");
	    		String hsesfty=request.getParameter("hsesfty");
	    		String frmmode=request.getParameter("frmmode");
	    		String keyid=request.getParameter("kznKeyid");
	    		
		    	KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
		    	
	    		
	    		if(UIUtils.isValidKeyId(flid))
	    			newKznTlKaizenbankmst.setKzbnFlid(flid);

	    		CommonMessage.debugMsg("refDocType..."+refDocType+"..refdocno.."+refdocno);
	    		httpSession.setAttribute("refDocType", refDocType);
	    		httpSession.setAttribute("refdocno", refdocno);
	    		

				newKznTlKaizenbankmst.setKzbnRefdoctype(refDocType);
				newKznTlKaizenbankmst.setKzbnRefdocno(refdocno);
				
	    		
	    		CommonMessage.debugMsg("45646464      "+Relatedto);
	    		//request.setAttribute("Relatedto", Relatedto);
	    		if("EHS".equals(Relatedto))
	    		{
	    			request.setAttribute("Relatedto", "EHS");	
	    			//request.setAttribute("hsesfty", "Y");
	    		}
	    		
				 
	    		request.setAttribute("frmmode", frmmode);
	    	
	    		//request.setAttribute("ehssfty", "Y");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Kaizen.jsp");	
				request.setAttribute("AccSingle",request.getParameter("AccSingle"));
				String Keyid = request.getParameter("Keyid");
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
				if (UIUtils.isValidKeyId(Keyid)){
					newKznTlKaizenbankmst =kaizenBankService.selectmst(Keyid);
					newKznTlKaizenbankmst.setKzbnAcrejby(user.getUsrm_ccno());
		    		CommonMessage.debugMsg(newKznTlKaizenbankmst.getKzbnTargetdate().contains(Constants.futureNullDate)+" Source  "+newKznTlKaizenbankmst.getKzbnFlid());
		    		String targetDate = newKznTlKaizenbankmst.getKzbnTargetdate();
		    		String accDate = newKznTlKaizenbankmst.getKzbnAcceptrejon();
	    			 final long MILLIS_IN_A_DAY = 1000*60*60*24;    
					 //teFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
//					 Date ocuredDate = dateFormat.parse(targetDate);
//	    			 String dateOnly = targetDate.substring(0, targetDate.indexOf('T'));
//	    			 
//	    			String occuredDate = CommonFunctions.pg_getDateFromPGTimeStamp(targetDate);
//					 Date oneDayBefore = new Date(occuredDate.getTime());
//					 targetDate = dateFormat.format(oneDayBefore);
//					 
//					 Date occuredDateAcc = dateFormat.parse(accDate);
//					 Date oneDayBeforeAcc = new Date(occuredDateAcc.getTime());
//	
//					 accDate = dateFormat.format(oneDayBeforeAcc);
					 newKznTlKaizenbankmst.setKzbnAcceptrejon(UIUtils.getActualDateForm(newKznTlKaizenbankmst.getKzbnAcceptrejon()));
				     newKznTlKaizenbankmst.setKzbnTargetdate(UIUtils.getActualDateForm(newKznTlKaizenbankmst.getKzbnTargetdate()));
				     newKznTlKaizenbankmst.setKzbnDate(CommonFunctions.pg_getDateFromPGTimeStamp(newKznTlKaizenbankmst.getKzbnDate()));
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
	    	
	    	//////////////New////////
			else if(action.equals("NewKaizenBankSuggestion_input.kznbnk") )
			{
				CommonMessage.debugMsg("AccSingle " +request.getParameter("AccSingle"));
				CommonMessage.debugMsg("inputAction");
	    		String Relatedto=request.getParameter("Relatedto");
	    		String refDocType = request.getParameter("refDocType");
	    		String refdocno = request.getParameter("refDocNo");
	    		String mchId=request.getParameter("machineID");
	    		String flid = request.getParameter("flid");
	    		String hsesfty=request.getParameter("hsesfty");
	    		String frmmode=request.getParameter("frmmode");
	    		String keyid=request.getParameter("kznKeyid");
	    		
		    	KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
		    	
	    		
	    		if(UIUtils.isValidKeyId(flid))
	    			newKznTlKaizenbankmst.setKzbnFlid(flid);

	    		CommonMessage.debugMsg("refDocType..."+refDocType+"..refdocno.."+refdocno);
	    		httpSession.setAttribute("refDocType", refDocType);
	    		httpSession.setAttribute("refdocno", refdocno);
	    		

				newKznTlKaizenbankmst.setKzbnRefdoctype(refDocType);
				newKznTlKaizenbankmst.setKzbnRefdocno(refdocno);
				
	    		
	    		CommonMessage.debugMsg("45646464      "+Relatedto);
	    		//request.setAttribute("Relatedto", Relatedto);
	    		if("EHS".equals(Relatedto))
	    		{
	    			request.setAttribute("Relatedto", "EHS");	
	    			//request.setAttribute("hsesfty", "Y");
	    		}
	    		
				 
	    		request.setAttribute("frmmode", frmmode);
	    	
	    		//request.setAttribute("ehssfty", "Y");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/NewKaizen.jsp");	
				request.setAttribute("AccSingle",request.getParameter("AccSingle"));
				String Keyid = request.getParameter("Keyid");
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
				if (UIUtils.isValidKeyId(Keyid)){
					newKznTlKaizenbankmst =kaizenBankService.selectmst(Keyid);
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
	       	
			else if(action.equals("KaizenBankAcceptReject_input.kznbnk") || action.equals("KaizenBankImplement_input.kznbnk") || action.equals("KaizenBankcComplete_input.kznbnk")
					||action.equals("KaizenBankcVerified_input.kznbnk")
					||action.equals("KaizenBankView_input.kznbnk") 
					||action.equals("KaizenBankIndividualView_input.kznbnk")
					||action.equals("KaizenBankImplement_input.kznbnk")||action.equals("KaizenBankImplemented_input.kznbnk") )

			{
				CommonMessage.debugMsg("Inside the Input method");
				commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);
				request.setAttribute("ROLELEVENO", commonFilter.getRoleLevel());
				
				CommonMessage.debugMsg(commonFilter.getRoleLevel() +" In side the Servlet");
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				String Sftyacrt=request.getParameter("mode");
				String hsesfty=request.getParameter("hsesfty");
				CommonMessage.debugMsg("hsesfty0"+hsesfty);
				String viewMode=request.getParameter("Mode");
				CommonMessage.debugMsg("viewMode"+viewMode);
				String fromMode=request.getParameter("fromMode");
				CommonMessage.debugMsg("fromMode"+fromMode);
				String kzbnKeyid=request.getParameter("KzbnKeyid");
				CommonMessage.debugMsg("kzbnKeyid"+kzbnKeyid);
				request.setAttribute("viewMode", viewMode);
				request.setAttribute("fromMode", fromMode);
				request.setAttribute("Sftyacrt", Sftyacrt);
				request.setAttribute("hsesfty", hsesfty);
				request.setAttribute("loginUserId", user.getUsrm_ccno());
				request.setAttribute("kzbnKeyid", kzbnKeyid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenBankMainGrid.jsp");
				rd.forward(request, response);
			}
			else if(action.equals("KaizenBankViewIn_input.kznbnk"))

			{
				CommonMessage.debugMsg("Action Is:::"+action);
				commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);
				request.setAttribute("ROLELEVENO", commonFilter.getRoleLevel());
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				String UserId=user.getUsrm_ccno();
				CommonMessage.debugMsg("UserID Is"+UserId);
				String Sftyacrt=request.getParameter("mode");
				String hsesfty=request.getParameter("hsesfty");
				CommonMessage.debugMsg("hsesfty0"+hsesfty);
				String viewMode=request.getParameter("Mode");
				CommonMessage.debugMsg("viewMode"+viewMode);
				String fromMode=request.getParameter("fromMode");
				CommonMessage.debugMsg("fromMode"+fromMode);
				String kzbnKeyid=request.getParameter("KzbnKeyid");
				CommonMessage.debugMsg("kzbnKeyid"+kzbnKeyid);
				request.setAttribute("UserId", UserId);
				request.setAttribute("viewMode", viewMode);
				request.setAttribute("fromMode", fromMode);
				request.setAttribute("Sftyacrt", Sftyacrt);
				request.setAttribute("hsesfty", hsesfty);
				request.setAttribute("loginUserId", user.getUsrm_ccno());
				request.setAttribute("kzbnKeyid", kzbnKeyid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/IndividualKaizenBankMainGrid.jsp");
				rd.forward(request, response);
			}
			else if(action.equals("KaizenBankMOCView.kznbnk"))	{
	   	 PrintWriter out = response.getWriter();
	   	 CommonMessage.debugMsg("IN Servlet");
		 String Suggestnid=request.getParameter("Suggestnid");
	 	 CommonMessage.debugMsg("IN Suggestnid"+Suggestnid);
		// String PrjTeamId=request.getParameter("PrjTeamId");
		 commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);
		 commonFilter.setKey(Suggestnid);
		
		 List<String[]> MOCKAIZEN  = kaizenBankService.MOCcheck(commonFilter);
		 CommonMessage.debugMsg("IN Servlet"+MOCKAIZEN);
		 JSONObject json=new JSONObject();
		json.put("MOC",MOCKAIZEN );
		json.put("Suggestnid", Suggestnid);
	
		out.println(json);
		
			}
	    	
			else if(action.equals("KaizenBankViewIn_getCol.kznbnk"))
					{   
					    		try {
					    			CommonMessage.debugMsg("Action Is:::"+action);
									commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);

									//String hsesfty=request.getParameter("hdnhsesfty");
									String hsesfty=request.getParameter("hsesfty");
									CommonMessage.debugMsg("hsesfty1"+hsesfty);
									String kznvw=request.getParameter("kznvw");
									String kzbnkeyid=request.getParameter("KzbnKeyid");
									String fromMode=request.getParameter("fromMode");
									CommonMessage.debugMsg("fromMode"+fromMode);
									AdmTlUsermst user = UIUtils.getLoginUser(request);
									String UserId=user.getUsrm_ccno();
									CommonMessage.debugMsg("UserID Is"+UserId);
									
									if(action.equals("KaizenBankViewIn_getCol.kznbnk"))
										commonFilter.setKznBankType("R");
								if(UIUtils.isValidKeyId(hsesfty))
									   commonFilter.setSafetyMode(hsesfty);
									
									if(UIUtils.isValidKeyId(fromMode))
										  commonFilter.setType(fromMode);
									if(UIUtils.isValidKeyId(UserId))
									commonFilter.setKAIZEN(UserId);
									if(UIUtils.isValidKeyId(kznvw))
									   commonFilter.setSafetyMode(kznvw);
									
									if(UIUtils.isValidKeyId(kzbnkeyid))
										  commonFilter.setKey(kzbnkeyid);
									PrintWriter out = response.getWriter();
									List<String []> getKznBnkGrid  = kaizenBankService.getfillgriddataIndividual(commonFilter);
									//JSONObject kznabnkData = UIUtils.convertToJqGridTableObject(getKznBnkGrid,request,0,0,commonFilter.getTotalRecordCnt());
									JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
									GridColModel gridColModel = new GridColModel();
									
									jqGridTableModel.setRowNumbers(true);
									jqGridTableModel.setEnableFilter(true);
									jqGridTableModel.setTableButton(true);
									
									String [] colHeader = getKznBnkGrid.get(2);			
									String [] colHeaderCond = getKznBnkGrid.get(1);
									
									List<String> formattorList =  new ArrayList<String>();
									
										formattorList.add("chkFormatter");
										formattorList.add("btnformatter");
										List<String> formattorFromList =  new ArrayList<String>();
										
										formattorFromList.add("2");
										formattorFromList.add(String.valueOf(colHeader.length-1));
										List<String> formattorToList =  new ArrayList<String>();
										formattorToList.add("2");
										formattorToList.add(String.valueOf(colHeader.length-1));
										gridColModel.setMultiformatter(formattorList);
										gridColModel.setMultiformattorFromCol(formattorFromList);
										gridColModel.setMultiformattorToCol(formattorToList);
										gridColModel.setHeaderNum(1);
									
										
										//CommonMessage.debugMsg("   kznabnkData     "+kznabnkData);
									
									List<String[]> headers = new ArrayList<String[]>();
									headers.add(colHeader);
									
									JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
									jsonObject.set("tableWidth", "106%%");
									//jsonObject.put("data", kznabnkData);
									jsonObject.set("tableHeight", "76%%");
									
									if(action.equals("KaizenBankcVerified_getCol.kznbnk"))
										jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("hidden",false);
									else
										jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("hidden",true);
									
									
									httpSession.removeAttribute("KaizenBank");
									httpSession.setAttribute("KaizenBank", jsonObject);
									out.println(jsonObject);
								} catch (Exception e) {
									e.printStackTrace();
								}
					    		
					}
			else if(action.equals("KaizenBankViewIn_getData.kznbnk"))
			{   
				CommonMessage.debugMsg("Action Is:::"+action);
	    		  commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);
	    		  AdmTlUsermst user = UIUtils.getLoginUser(request);
					String UserId=user.getUsrm_ccno();
					CommonMessage.debugMsg("UserID Is"+UserId);
	    		  String hsesfty=request.getParameter("hsesfty");
	    		  CommonMessage.debugMsg("hsesfty2"+hsesfty);
	    		  String kznvw=request.getParameter("kznvw");
	    		  String kzbnkeyid=request.getParameter("KzbnKeyid");
	    		  CommonMessage.debugMsg("kzbnkeyid2"+kzbnkeyid);
	    		  String fromMode=request.getParameter("fromMode");
	    		  if(action.equals("KaizenBankViewIn_getData.kznbnk"))
						commonFilter.setKznBankType("R");
	    		  if(UIUtils.isValidKeyId(UserId))
						commonFilter.setKAIZEN(UserId);
					
	    		   if(UIUtils.isValidKeyId(hsesfty))
	    		  commonFilter.setSafetyMode(hsesfty);
	    		  
	    		   if(UIUtils.isValidKeyId(fromMode))
						  commonFilter.setType(fromMode);
	    		   
				   if(UIUtils.isValidKeyId(kznvw))
					   commonFilter.setSafetyMode(kznvw);
				   
				   if(UIUtils.isValidKeyId(kzbnkeyid))
						  commonFilter.setKey(kzbnkeyid);
			 List<String []> getAllFiveSAuditarea  = kaizenBankService.getfillgriddataIndividual(commonFilter);
	             PrintWriter out = response.getWriter();
	             JSONObject FivesAudit = UIUtils.convertToJqGridTableObject(getAllFiveSAuditarea,request,3,0,commonFilter.getTotalRecordCnt()+1); 
	  			 out.println(FivesAudit);
			}
	    
			
			else if(action.equals("KaizenBankDelete_input.kznbnk")){
				KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
				commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);
				request.setAttribute("ROLELEVENO", commonFilter.getRoleLevel());
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				String Sftyacrt=request.getParameter("mode");
				String viewMode=request.getParameter("Mode");
			    String kzbnKeyid= request.getParameter("KzbnKeyid");
			    CommonMessage.debugMsg("The KaizenBank Keyid::::::"+kzbnKeyid);
				request.setAttribute("kzbnKeyid", kzbnKeyid);
				request.setAttribute("viewMode", viewMode);
				request.setAttribute("Sftyacrt", Sftyacrt);
				request.setAttribute("loginUserId", user.getUsrm_ccno());
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Suggestiondelete.jsp");
				rd.forward(request, response);
			}
			   else if(action.equals("KaizenSuggestionRejectStatus_input.kznbnk")){
                   CommonMessage.debugMsg("Kaizen SuggestionRejectStatus");
                   KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
   				commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);
   				request.setAttribute("ROLELEVENO", commonFilter.getRoleLevel());
   				AdmTlUsermst user = UIUtils.getLoginUser(request);
   				String Sftyacrt=request.getParameter("mode");
   				String viewMode=request.getParameter("Mode");
   			    String KzbnKeyid= request.getParameter("KzbnKeyid");
   			    CommonMessage.debugMsg("The KaizenBank Sugg RejectStatus Keyid::::::"+KzbnKeyid);
   				request.setAttribute("KzbnKeyid", KzbnKeyid);
   				request.setAttribute("viewMode", viewMode);
   				request.setAttribute("Sftyacrt", Sftyacrt);
   				request.setAttribute("loginUserId", user.getUsrm_ccno());
   				RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenSuggRejectstatus.jsp");
   				rd.forward(request, response);
            }
			
			else if(action.equals("KaizenBankAcceptReject_getCol.kznbnk") || action.equals("KaizenBankImplement_getCol.kznbnk") || action.equals("KaizenBankcComplete_getCol.kznbnk")
					||action.equals("KaizenBankcVerified_getCol.kznbnk") 
					||action.equals("KaizenBankView_getCol.kznbnk") 
					||action.equals("KaizenBankIndividualView_getCol.kznbnk")
					||action.equals("KaizenBankDelete_getCol.kznbnk")
					||action.equals("KaizenBankImplement_getCol.kznbnk")||action.equals("KaizenBankImplemented_getCol.kznbnk")||action.equals("KaizenSuggestionRejectStatus_getCol.kznbnk"))
			{   
	    		try {
					commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);

					//String hsesfty=request.getParameter("hdnhsesfty");
					String hsesfty=request.getParameter("hsesfty");
					CommonMessage.debugMsg("hsesfty1"+hsesfty);
					String kznvw=request.getParameter("kznvw");
					String kzbnkeyid=request.getParameter("KzbnKeyid");
					String fromMode=request.getParameter("fromMode");
					CommonMessage.debugMsg("kzbnkeyid1"+kzbnkeyid);
					AdmTlUsermst user = UIUtils.getLoginUser(request);
					String loginId=user.getUsrm_ccno();
					CommonMessage.debugMsg("LoginId:::"+loginId);
					if(action.equals("KaizenBankAcceptReject_getCol.kznbnk"))
						commonFilter.setKznBankType("A");
					else if(action.equals("KaizenBankImplement_getCol.kznbnk"))
						commonFilter.setKznBankType("I");
					else if(action.equals("KaizenBankcComplete_getCol.kznbnk"))
						commonFilter.setKznBankType("C");
					else if(action.equals("KaizenBankcVerified_getCol.kznbnk")) 
					{
							commonFilter.setKznBankType("V");
					        commonFilter.setKey(kzbnkeyid);
					}
					else if(action.equals("KaizenBankcVerified_getCol.kznbnk")) 
							commonFilter.setKznBankType("V");
					else if(action.equals("KaizenBankView_getCol.kznbnk"))
						commonFilter.setKznBankType("R");
					else if(action.equals("KaizenBankDelete_getCol.kznbnk"))
					{
						commonFilter.setKznBankType("R");
						 commonFilter.setKey(kzbnkeyid);
					}
					else if(action.equals("KaizenSuggestionRejectStatus_getCol.kznbnk"))
						commonFilter.setKznBankType("R");
					else if(action.equals("KaizenBankImplement_getCol.kznbnk"))
						commonFilter.setKznBankType("I");
					else if(action.equals("KaizenBankImplemented_getCol.kznbnk"))
						commonFilter.setKznBankType("M");
					else if(action.equals("KaizenBankIndividualView_getCol.kznbnk"))
						commonFilter.setKznBankType("IV");
					    commonFilter.setGetKaizenkey(loginId);			
					
					if(UIUtils.isValidKeyId(hsesfty))
					   commonFilter.setSafetyMode(hsesfty);
					
					if(UIUtils.isValidKeyId(fromMode))
						  commonFilter.setType(fromMode);
					
					
					if(UIUtils.isValidKeyId(kznvw))
					   commonFilter.setSafetyMode(kznvw);
					
					if(UIUtils.isValidKeyId(kzbnkeyid))
						  commonFilter.setKey(kzbnkeyid);
					
						commonFilter.setIsGetCol("Y");
					PrintWriter out = response.getWriter();
					List<String []> getKznBnkGrid  = kaizenBankService.getfillgriddata(commonFilter);
					//JSONObject kznabnkData = UIUtils.convertToJqGridTableObject(getKznBnkGrid,request,0,0,commonFilter.getTotalRecordCnt());
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
//					String [] colHeader = getKznBnkGrid.get(2);			
//					String [] colHeaderCond = getKznBnkGrid.get(1);
					String [] colHeader = getKznBnkGrid.get(1);
					String [] colHeaderCond = getKznBnkGrid.get(0);
					
					List<String> formattorList =  new ArrayList<String>();
					
					if(action.equals("KaizenBankImplement_getCol.kznbnk") ||
							action.equals("KaizenBankcVerified_getCol.kznbnk")) {
					
						formattorList.add("chkFormatter");
						formattorList.add("btnformatter");
						formattorList.add("cmbStatusformatter");
						formattorList.add("cmbApprovedbyformatter");
						formattorList.add("txtImplCostformatter");
						formattorList.add("dteTargetDateformatter");
						formattorList.add("cmbResponsbilityformatter");
						formattorList.add("btnMOCformatter");
						//formattorList.add("btnMOCformatter");
						List<String> formattorFromList =  new ArrayList<String>();
						
						formattorFromList.add("2");
						formattorFromList.add("9");
						formattorFromList.add("10");
						formattorFromList.add("11");
						formattorFromList.add("12");
						formattorFromList.add("13");
						formattorFromList.add("14");
						formattorFromList.add("16");
						formattorFromList.add("27");
						CommonMessage.debugMsg("Formatt"+colHeader.length);
						//formattorFromList.add(String.valueOf(colHeader.length-1));
						List<String> formattorToList =  new ArrayList<String>();
						formattorToList.add("2");
						formattorToList.add("9");
						formattorToList.add("10");
						formattorToList.add("11");
						formattorToList.add("12");
						formattorToList.add("13");
						formattorToList.add("14");
						formattorToList.add("16");
						formattorFromList.add("27");
						CommonMessage.debugMsg("Formatt TO"+formattorFromList);
						//formattorToList.add(String.valueOf(colHeader.length-1));
						gridColModel.setMultiformatter(formattorList);
						gridColModel.setMultiformattorFromCol(formattorFromList);
						gridColModel.setMultiformattorToCol(formattorToList);
						gridColModel.setHeaderNum(1);
					}
					else if(action.equals("KaizenBankImplemented_getCol.kznbnk")){
						formattorList.add("chkFormatter");
						formattorList.add("btnformatter");
						CommonMessage.debugMsg("In Else"+formattorList);
						List<String> formattorFromList =  new ArrayList<String>();
						
						formattorFromList.add("2");
						formattorFromList.add(String.valueOf(colHeader.length-2));
						List<String> formattorToList =  new ArrayList<String>();
						formattorToList.add("2");
						formattorToList.add(String.valueOf(colHeader.length-2));
						gridColModel.setMultiformatter(formattorList);
						gridColModel.setMultiformattorFromCol(formattorFromList);
						gridColModel.setMultiformattorToCol(formattorToList);
						gridColModel.setHeaderNum(1);
					}
					else {
						formattorList.add("chkFormatter");
						formattorList.add("btnformatter");
						CommonMessage.debugMsg("In Else"+formattorList);
						List<String> formattorFromList =  new ArrayList<String>();
						
						formattorFromList.add("2");
						formattorFromList.add(String.valueOf(colHeader.length-1));
						List<String> formattorToList =  new ArrayList<String>();
						formattorToList.add("2");
						formattorToList.add(String.valueOf(colHeader.length-1));
						gridColModel.setMultiformatter(formattorList);
						gridColModel.setMultiformattorFromCol(formattorFromList);
						gridColModel.setMultiformattorToCol(formattorToList);
						gridColModel.setHeaderNum(1);
					}
						
						//CommonMessage.debugMsg("   kznabnkData     "+kznabnkData);
					
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.set("tableWidth", "106%%");
					//jsonObject.put("data", kznabnkData);
					jsonObject.set("tableHeight", "76%%");
					if(action.equals("KaizenBankDelete_getCol.kznbnk"))
						jsonObject.set("multiSelect",true);
					 else if(action.equals("KaizenSuggestionRejectStatus_getCol.kznbnk"))
						jsonObject.set("multiSelect",true);
					if(action.equals("KaizenBankcVerified_getCol.kznbnk"))
						jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("hidden",false);
					else
						jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("hidden",false);
					CommonMessage.debugMsg("In else");
					
					httpSession.removeAttribute("KaizenBank");
					httpSession.setAttribute("KaizenBank", jsonObject);
					out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		
			}
			else if(action.equals("KaizenBankAcceptReject_getData.kznbnk") || action.equals("KaizenBankImplement_getData.kznbnk") 
					|| action.equals("KaizenBankcComplete_getData.kznbnk")
					||action.equals("KaizenBankcVerified_getData.kznbnk") 
					||action.equals("KaizenBankAcceptVerified_getData.kznbnk") 
					||action.equals("KaizenBankView_getData.kznbnk")
					||action.equals("KaizenBankIndividualView_getData.kznbnk")
					||action.equals("KaizenBankImplement_getData.kznbnk")||action.equals("KaizenBankImplemented_getData.kznbnk")||
					action.equals("KaizenBankDelete_getData.kznbnk")||action.equals("KaizenSuggestionRejectStatus_getData.kznbnk"))
			{   
	    		
	    		  commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);

	    		  String hsesfty=request.getParameter("hsesfty");
	    		  CommonMessage.debugMsg("hsesfty2"+hsesfty);
	    		  String kznvw=request.getParameter("kznvw");
	    		  String kzbnkeyid=request.getParameter("KzbnKeyid");
	    		  CommonMessage.debugMsg("kzbnkeyid2"+kzbnkeyid);
	    		  String fromMode=request.getParameter("fromMode");
	    		  AdmTlUsermst user = UIUtils.getLoginUser(request);
				  String loginId=user.getUsrm_ccno();
				  CommonMessage.debugMsg("LoginId:::"+loginId);
	    		  if(action.equals("KaizenBankAcceptReject_getData.kznbnk"))
						commonFilter.setKznBankType("A");
					else if(action.equals("KaizenBankImplement_getData.kznbnk"))
					{
						commonFilter.setKznBankType("I");
	    		  
	    		         commonFilter.setKey(kzbnkeyid);
					}
					else if(action.equals("KaizenBankcComplete_getData.kznbnk"))
						commonFilter.setKznBankType("C");
					else if(action.equals("KaizenBankView_getData.kznbnk"))
						commonFilter.setKznBankType("R");
					else if(action.equals("KaizenBankDelete_getData.kznbnk"))
					{
						commonFilter.setKznBankType("R");
	    		        commonFilter.setKey(kzbnkeyid);
			         }
					else if(action.equals("KaizenBankImplement_getData.kznbnk"))
						commonFilter.setKznBankType("I");
					else if(action.equals("KaizenSuggestionRejectStatus_getData.kznbnk"))
						commonFilter.setKznBankType("R");
					else if(action.equals("KaizenBankcVerified_getData.kznbnk")) 
					{
							commonFilter.setKznBankType("V");
					        commonFilter.setKey(kzbnkeyid);
					}
	    		  
					else if(action.equals("KaizenBankAcceptVerified_getData.kznbnk"))
					{
					commonFilter.setKznBankType("V");
			        commonFilter.setKey(kzbnkeyid);
			       }
					else if(action.equals("KaizenBankAcceptVerified_getData.kznbnk"))
						commonFilter.setKznBankType("V");
					else if(action.equals("KaizenBankImplemented_getData.kznbnk"))
						commonFilter.setKznBankType("M");
					else if(action.equals("KaizenBankIndividualView_getData.kznbnk"))
						commonFilter.setKznBankType("IV");
	    		        commonFilter.setGetKaizenkey(loginId);
	    		   if(UIUtils.isValidKeyId(hsesfty))
	    		  commonFilter.setSafetyMode(hsesfty);
	    		  
	    		   if(UIUtils.isValidKeyId(fromMode))
						  commonFilter.setType(fromMode);
	    		   
				   if(UIUtils.isValidKeyId(kznvw))
					   commonFilter.setSafetyMode(kznvw);
				   
				   if(UIUtils.isValidKeyId(kzbnkeyid))
						  commonFilter.setKey(kzbnkeyid);
					
				  
				   commonFilter.setIsGetCol("N");
	    		 List<String []> getAllFiveSAuditarea  = kaizenBankService.getfillgriddata(commonFilter);
	             PrintWriter out = response.getWriter();
	             JSONObject FivesAudit = UIUtils.convertToJqGridTableObject(getAllFiveSAuditarea,request,2,0,commonFilter.getTotalRecordCnt()+1); 
	  			 out.println(FivesAudit);
			}
			else if(action.equals("KaizenBankImplement_input.kznbnk")){
				CommonMessage.debugMsg("Suggestion");
				String SuggId=request.getParameter("sugg");
				CommonMessage.debugMsg("Suggestion"+SuggId);
				request.setAttribute("SuggId",SuggId);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenBankMainGrid.jsp");
				rd.forward(request, response);
			}
	    	else if(action.equals("functionalLoc.kznbnk"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setCompany("cmbComp");
				String flid=request.getParameter("flid"); 
				//fact// functLocFieldNameBean.setFactory("cmbFact");
				functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setFactMandatory(false);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(true);
				functLocFieldNameBean.setMachMandatory(false);
				functLocFieldNameBean.setFunctionalLocId(flid);
				String disableFuncLoc = request.getParameter("disableFuncLoc");
				
				if("true".equals(disableFuncLoc)){
					functLocFieldNameBean.setCompany("cmbComp");
					//fact// functLocFieldNameBean.setFactory("cmbFact");
					functLocFieldNameBean.setSbu("cmbSbu");
					functLocFieldNameBean.setPbu("cmbPbu");
					functLocFieldNameBean.setSection("cmbSect");
					functLocFieldNameBean.setCell("cmbCell");
					functLocFieldNameBean.setMachine("cmbMachine");
					//fact// functLocFieldNameBean.setFactDisable(true);
					functLocFieldNameBean.setSbuDisable(true);
					functLocFieldNameBean.setPbuDisable(true);
					//fact// functLocFieldNameBean.setFactDisable(true);
					functLocFieldNameBean.setSectDisable(true);
					functLocFieldNameBean.setCellDisable(true);
					functLocFieldNameBean.setMachDisable(true);
				}
				
				FormModes formModes = FormModes.create;
				
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
				
			}
	    	else if(action.equals("Kaizen_input.kznbnk"))
			{
	    		String keyId = request.getParameter("keyid");
	    		if(UIUtils.isValidKeyId(keyId)){
	    			KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst();
					newKznTlKaizenbankmst = kaizenBankService.getRecall(keyId);
					httpSession.setAttribute("kaizenbank", newKznTlKaizenbankmst);
					request.setAttribute("kaizenbank",newKznTlKaizenbankmst);
	    		}
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Kaizen.jsp");					
				rd.forward(request, response);
			}
	    	else if(action.equals("Kaizen_save.kznbnk")){
	    		saveKaizenBank(request,response,"");
	    	}
	    	else if(action.equals("KaizenBankSuggStatusPopup_input.kznbnk")){
				String kznKeyid=request.getParameter("Keyid");
				CommonMessage.debugMsg("The kznsuggKeyid popup"+kznKeyid);
				String kznsuggstatus=request.getParameter("kznstatus");
				CommonMessage.debugMsg("The kznsuggstatus popup"+kznsuggstatus);
	    		request.setAttribute("kznsuggKeyid",kznKeyid);
	    		request.setAttribute("kznsuggstatus",kznsuggstatus);
				RequestDispatcher rd1=request.getRequestDispatcher("/pages/KaizenSugstnRejectStatusPopup.jsp");
			    rd1.forward(request,response);	
			}
	    	else if(action.equals("KaizenSuggrejectststatus_save.kznbnk")){
	    		saveKaizenSuggRejectStatus(request,response);
	    	}
	    	else if(action.equals("kaizenBankAcceptVerifypopup_modify.kznbnk")){
	    		String suggKeyid=request.getParameter("keyid");
	    		request.setAttribute("suggKeyid",suggKeyid);
	    		RequestDispatcher rd = request.getRequestDispatcher("/pages/KaizenSugstnPopup.jsp");					
	    		rd.forward(request, response);
	    	}
	    	else if(action.equals("kaizenBankAcceptVerifypopup_save.kznbnk")){
	    		saveKaizenApprovedVerifypopup(request,response);
	    	}
	    	else if(action.equals("kaizenFillkzn_modify.kznbnk")){
	    		try {
				    PrintWriter out = response.getWriter();
					String keyid = request.getParameter("keyid");
					CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
					List<String []> condReclData  = kaizenBankService.FillkznData(keyid);
					out.print( JSONArray.fromCollection(condReclData));
	   			} catch (Exception e) {

				}
	    	}
	    	
	    	else if(action.equals("kaizenBankAcceptVerify_save.kznbnk") || action.equals("KaizenBankcVerified_save.kznbnk")){
	    		saveKaizenApprovedVerify(request,response,"");
	    	}
	    	
	    	else if(action.equals("KaizenBankSuggestion_save.kznbnk") || action.equals("KaizenBankPopup_save.kznbnk")){
	    		String saveType=request.getParameter("frmType");
	    		if(!UIUtils.isValidKeyId(saveType))
	    			saveType="S";
	    		CommonMessage.debugMsg("saveType   "+saveType);
	    		saveKaizenBank(request,response,saveType);
	    	}
	    
	    	//************************************Multiple Suggestion Entry********************************************************//
	    	else if(action.equals("MultipleSuggestionEntry_input.kznbnk")){
	    		String date=CommonFunctions.dateTimeNow();
	    		String currentDate=UIUtils.getActualDateForm(date);
	    		String Location=CommonFunctions.getLoginLocaton(request);
	    		CommonMessage.debugMsg("the Location"+Location);
	    		glbLocation=Location;
	    		String logFlid=CommonFunctions.getLoginFlid(request);
	    		CommonMessage.debugMsg("Flid:::"+logFlid);
	    	    RequestDispatcher rd = request.getRequestDispatcher("/pages/MultipleSuggestionEntry.jsp");					
	       		request.setAttribute("currentDate",currentDate);
	    		request.setAttribute("glbLocation",glbLocation);
	    		request.setAttribute("logFlid",logFlid);
	    	    rd.forward(request, response);	
	    	 }
	    	else if(action.equals("MultipleSuggestionEntry_getCol.kznbnk")){
	    		try{
	    			PrintWriter out=response.getWriter();
	    			out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.MultipleSuggEntry","SuggetionDetail"));
					CommonMessage.debugMsg("Multiple Suggestion>>>>>>"+UIUtils.getPropertyValue("com.akranta.tpm.resources.MultipleSuggEntry","SuggetionDetail"));
	    		}
	    		catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}
	    	else if(action.equals("MultipleSuggestionEntry_save.kznbnk")){
	    		saveMultipleSuggestion(request,response);
	    	}	
	    	
	    	else if(action.equals("kaizenThemeCategory_recall.kznbnk")){
	    		PrintWriter out=response.getWriter();
	    		String Keyid=request.getParameter("KEYID");
	    		CommonMessage.debugMsg("The keyid is::"+Keyid);
	    		List<String[]> list=kaizenBankService.FillThemeCategoryData(Keyid);
	    		out.print(JSONArray.fromCollection(list));
	    		
	    	} 	
	    	
	    	else if(action.equals("KaizenBankSuggestion_delete.kznbnk")){
	    		deleteKaizenBank(request,response);
	    	}
	    	else if(action.equals("KaizenBank_getCol.kznbnk")){   
	    		try {
					commonFilter = populateCommonFilter(request,"KaizenBankGrid",true);
					PrintWriter out = response.getWriter();
					List<String []> getAllFiveSAuditarea  = kaizenBankService.getfillgriddata(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
					gridColModel.setFormatter("txtformatter");
					gridColModel.setFormattorFromCol("15");
					gridColModel.setFormattorToCol("15");
					
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = getAllFiveSAuditarea.get(2);			
					String [] colHeaderCond = getAllFiveSAuditarea.get(1);
					
					CommonMessage.debugMsg("   TABLEMODEL     "+getAllFiveSAuditarea.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.set("tableWidth", "106%%");
					jsonObject.set("tableHeight", "78%%");
					httpSession.removeAttribute("KaizenBank");
					httpSession.setAttribute("KaizenBank KaizenBank ", jsonObject);
					out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		
			}
	    	else if(action.equals("KaizenBank_getData.kznbnk")){  
	    		
	    		 commonFilter = populateCommonFilter(request,"KaizenBankGrid",false);
	    		 List<String []> getAllFiveSAuditarea  = kaizenBankService.getfillgriddata(commonFilter);
	             PrintWriter out = response.getWriter();
	             JSONObject FivesAudit = UIUtils.convertToJqGridTableObject(getAllFiveSAuditarea,request,3,0,commonFilter.getTotalRecordCnt()); 
	  			 out.println(FivesAudit);
			}else if(action.equals("KaizenBankAcceptReject_getExcel.kznbnk") || action.equals("KaizenBankImplement_getExcel.kznbnk") 
					|| action.equals("KaizenBankcComplete_getExcel.kznbnk") || action.equals("KaizenBankcVerified_getExcel.kznbnk")
					||action.equals("KaizenBankView_getExcel.kznbnk")
					||action.equals("KaizenBankIndividualView_getExcel.kznbnk")
					||action.equals("KaizenBankImplement_getExcel.kznbnk")||action.equals("KaizenBankImplemented_getExcel.kznbnk") )
			{
				commonFilter = populateCommonFilter(request,"KaizenBankGrid",false);
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				String loginId=user.getUsrm_ccno();
				CommonMessage.debugMsg("LoginId:::"+loginId);
			 	commonFilter.setKznBankType("IV");
		        commonFilter.setGetKaizenkey(loginId);
				 if(action.equals("KaizenBankAcceptReject_getExcel.kznbnk"))
						commonFilter.setKznBankType("A");
					else if(action.equals("KaizenBankImplement_getExcel.kznbnk"))
						commonFilter.setKznBankType("I");
					else if(action.equals("KaizenBankcComplete_getExcel.kznbnk"))
						commonFilter.setKznBankType("C");
					else if(action.equals("KaizenBankcVerified_getExcel.kznbnk"))
						commonFilter.setKznBankType("V");
					else if(action.equals("KaizenBankView_getExcel.kznbnk"))
						commonFilter.setKznBankType("R");
					else if(action.equals("KaizenBankImplement_getExcel.kznbnk"))
						commonFilter.setKznBankType("I");
					else if(action.equals("KaizenBankImplemented_getExcel.kznbnk"))
						commonFilter.setKznBankType("M");
					else if(action.equals("KaizenBankIndividualView_getExcel.kznbnk"))
						commonFilter.setKznBankType("IV");
				
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("KaizenBank");
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				colmodel.put("title","Kaizen Bank");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = kaizenBankService.getKaizenExcel(colmodel,format,commonFilter);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "KaizenBank", format);
			}
	    	
			else if(action.equals("KaizenBankSuggestionMainGrid_getExcel.kznbnk") ){
				commonFilter = populateCommonFilter(request,"KaizenBankGrid",false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("KaizenBank");
				colmodel.put("title","Kaizen Bank");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = kaizenBankService.getKaizenMainExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "KaizenBank", format);
				
			}
	    	
	    	else if(action.equals("statuscombo.kznbnk")){
	    		PrintWriter out = response.getWriter();			
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Status", "status"));
			}
	    	else if(action.equals("SafetySuggestionGrid_input.kznbnk"))
			{
	    		commonFilter = populateCommonFilter(request,"SafetySuggGrid",true);
	    		String KzbnKeyid= request.getParameter("KzbnKeyid");
				 String fromMode=request.getParameter("fromMode"); 
				
					request.setAttribute("kzbnKeyid", KzbnKeyid);
					request.setAttribute("fromMode", fromMode);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/SafetySuggestionGrid.jsp");					
				rd.forward(request, response);
			}
	    	else if(action.equals("SafetySuggestionGrid_getCol.kznbnk")){   
	    		try {
					commonFilter = populateCommonFilter(request,"SafetySuggGrid",true);
					String KzbnKeyid = request.getParameter("KzbnKeyid");
					String fromMode=request.getParameter("fromMode");
					
					if(UIUtils.isValidKeyId(KzbnKeyid))
						  commonFilter.setKey(KzbnKeyid);
					if(UIUtils.isValidKeyId(fromMode))
						  commonFilter.setType(fromMode);
					PrintWriter out = response.getWriter();
					List<String []> getAllFiveSAuditarea  = kaizenBankService.getSafetySuggGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setGridEdit(true);
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = getAllFiveSAuditarea.get(2);			
					String [] colHeaderCond = getAllFiveSAuditarea.get(1);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.set("tableWidth", "106%%");
					jsonObject.set("tableHeight", "78%%");
					httpSession.removeAttribute("SafetySuggestion");
					httpSession.setAttribute("SafetySuggestion", jsonObject);
					out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		
			}
	    	else if(action.equals("SafetySuggestionGrid_getData.kznbnk")){  
	    		String search = request.getParameter("_search");
	    		
	    		 commonFilter = populateSafetyCommonFilter(request,"SafetySuggGrid",false);
	    		 String KzbnKeyid=request.getParameter("KzbnKeyid");
	    		 String fromMode=request.getParameter("fromMode");
	    		
	    		 if(UIUtils.isValidKeyId(KzbnKeyid))
					  commonFilter.setKey(KzbnKeyid);
	    		 if(UIUtils.isValidKeyId(fromMode))
					  commonFilter.setType(fromMode);
	    		 List<String []> getAllSafety  = kaizenBankService.getSafetySuggGrid(commonFilter);
	             PrintWriter out = response.getWriter();
	             JSONObject safetyJson = UIUtils.convertToJqGridTableObject(getAllSafety,request,3,0,commonFilter.getTotalRecordCnt()+3); 
	  			 out.println(safetyJson);
			}
	    		
			else if(action.equals("SafetySuggestionGrid_getExcel.kznbnk") ){
				commonFilter = populateSafetyCommonFilter(request,"SafetySuggGrid",false);
				JSONObject colmodel =  UIUtils.getXlColModel(request, response);
				colmodel.put("title","Safety Suggestion");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = kaizenBankService.getSafetyExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "SafetySuggestion", format);
				
			}
			else if(action.equals("SafetySuggestionGrid_save.kznbnk")){
	    		saveSafetySugg(request,response);
	    	}


			 else if(action.equals("SafetySuggestionSummaryReport_input.kznbnk")){
								CommonMessage.debugMsg("SafetySuggestionSummaryReport_input.kznbnk");
								CommonMessage.debugMsg("SafetySuggestionSummaryReport_input.kznbnk");
								UIUtils.forwardRequest(request, response,"/pages/SafetySuggestionSummaryReport.jsp");
								
							}else if(action.equals("SafetySuggestionSummaryReport_getCol.kznbnk")){		
								CommonMessage.debugMsg("SafetySuggestionSummaryReport_getCol.kznbnk");
								PrintWriter out = response.getWriter();	
								 httpSession=request.getSession(false);
						  commonFilter = populateCommonFilter(request,"SafetySuggestionSummaryCommonFilter",true);			
								List<String[]> SafetySuggestionGridData  = kaizenBankService.getSafetySuggestionSummaryGridData(commonFilter);			
								JqGridTableModel jqGridTableModel = new JqGridTableModel();
								GridColModel gridColModel = new GridColModel();
								jqGridTableModel.setRowNumbers(true);
								jqGridTableModel.setEnableFilter(false);
								jqGridTableModel.setTableButton(true);
								jqGridTableModel.setEnableFilter(true);
								gridColModel.setHeaderNum(1);
								String[] colHeader = SafetySuggestionGridData.get(2);
								String[] colHeaderCond = SafetySuggestionGridData.get(1);
								List<String[]> headers = new ArrayList<String[]>();			
								headers.add(colHeader);			
								JSONObject colModel =new JSONObject();
								colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
								colModel.set("tableHeight", "90%%");
								colModel.set("tableWidth", "110%%");
								httpSession.removeAttribute("SafetySuggestionSummaryColModel");
								httpSession.setAttribute("SafetySuggestionSummaryColModel", colModel);			
								httpSession.removeAttribute("SafetySuggestionSummaryCommonFilter");
								httpSession.setAttribute("SafetySuggestionSummaryCommonFilter", commonFilter);			
								out.println(colModel);			
							}else if(action.equals("SafetySuggestionSummaryReport_getData.kznbnk")){
								PrintWriter out = response.getWriter();	
								httpSession=request.getSession(false);
								 commonFilter=populateCommonFilter(request, "SafetySuggestionSummaryCommonFilter", false);
								List<String[]> KaizenBankGridData = kaizenBankService.getSafetySuggestionSummaryGridData(commonFilter);
								JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenBankGridData,request, 3, 0,commonFilter.getTotalRecordCnt() );
								out.print(dataJson);
								httpSession.removeAttribute("SafetySuggestionSummaryCommonFilter");
								httpSession.setAttribute("SafetySuggestionSummaryCommonFilter", commonFilter);			
							}else if(action.equals("SafetySuggestionSummaryReport_getExcel.kznbnk")){
								 httpSession=request.getSession(false);
								commonFilter = populateCommonFilter(request,"SafetySuggestionSummaryCommonFilter",false);			
								JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("SafetySuggestionSummaryColModel");
								String tmpFromRow = commonFilter.getFromRow();
								commonFilter.setFromRow(null);
								tblJSONObj.put("title", "Safety Suggestion Summary Report  ");			
								String format = ExcelUtils.getFormat(request);	
								Workbook wb = kaizenBankService.getSafetySuggestionSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);			
								commonFilter.setFromRow(tmpFromRow);
								ExcelUtils.writeToResponse(response, wb, "SafetySuggestionSummaryReport", format);		
							}
				    
	    } catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
		}
		
	}
	
	private void saveSafetySugg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String dataStatus=request.getParameter("dataStatus");
    	CommonMessage.debugMsg("dataStatus  "+dataStatus);
    	if( httpSession != null && user != null)
    	{	
    		KznTlKaizenbankmst kznTlKaizenbankmst =new KznTlKaizenbankmst();
    		List<KznTlKaizenbankmst> existKznTlKaizenbankmst = new ArrayList<KznTlKaizenbankmst>();
    		JSONArray dataStatusJsonArr = JSONArray.fromString(dataStatus);
    		List<KznTlKaizenbankmst> kznTlEvmstList = null;   	
			kznTlEvmstList=(List<KznTlKaizenbankmst>)UIUtils.convertJSONArrToList(kznTlKaizenbankmst, dataStatusJsonArr);
	    	
			CommonMessage.debugMsg(" Status :: Remarks :: "+kznTlEvmstList.get(0).getKzbnAccrejremarks());
			
			try{
	    		String saveMsg=""; ;
				if( kznTlEvmstList!=null){	
					existKznTlKaizenbankmst = kaizenBankService.updateSugg(kznTlEvmstList);
					saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
					saveMsg="Status Updated Successfully";
				}else
					saveMsg="Status Not Updated Successfully";
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				returnData.put("successData",successData);
				returnData.put("formClear",false);
				out.print(returnData.toString());
	    	}
	    	catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    	}	
		
	}
	
	
	private void deleteKaizenBank(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	
    	String KznBankKeyid=request.getParameter("kanbankkeyid");
    	CommonMessage.debugMsg("The KaizenBankKeyid"+KznBankKeyid);
    	String KznMode=request.getParameter("kznMode");
    	KznTlKaizenbankmst existKznTlKaizenbankmst = (KznTlKaizenbankmst)httpSession.getAttribute("newSession");
    	KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
    	
    	if(KznMode!=null){
    		newKznTlKaizenbankmst.setKzbnKeyid(KznBankKeyid);
    	}
    	
    	newKznTlKaizenbankmst=(KznTlKaizenbankmst)UIUtils.setBeanProperties((Object)newKznTlKaizenbankmst,request);
    	existKznTlKaizenbankmst = kaizenBankService.delete(newKznTlKaizenbankmst);
    	JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",false);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
		// TODO Auto-generated method stub
	}
	private void saveKaizenBank(HttpServletRequest request,HttpServletResponse response,String type) throws Exception {
		CommonMessage.debugMsg("----My Test-----");
		HttpSession httpSession = request.getSession(true);
		String operation="other";
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String dataFlid=request.getParameter("dataFlid");
    	CommonMessage.debugMsg("dataFlid::::"+dataFlid);
    	String dataKeyid=request.getParameter("dataKeyid");
    	CommonMessage.debugMsg("dataKeyid::::"+dataKeyid);
    	String dataSuggest=request.getParameter("dataSuggest");
    	CommonMessage.debugMsg("dataSuggest::::"+dataSuggest);
    	String AccSingle=request.getParameter("AccSingle");
    	String chkkzbnOthers=request.getParameter("chkkzbnOthers");
    	String refDocType = (String)httpSession.getAttribute("refDocType");
    	String refdocNo = (String)httpSession.getAttribute("refdocno");
    	String chkvalue=request.getParameter("chkvalue");
    	String hsesfty=request.getParameter("hdnehssfty");
    	CommonMessage.debugMsg("hsesfty::::"+hsesfty);
    	CommonMessage.debugMsg("chkvalue::::"+chkvalue);
    	
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
    		KaizenFormBean kaizenFormBean =(KaizenFormBean)httpSession.getAttribute("KaizenBankServletKaizenFormBean");
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
	    		
	    		if(hsesfty!=null)
	      		{
	      		if (hsesfty.equals("Y"))
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
					if(   !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnKeyid())  ){
						existKznTlKaizenbankmst = kaizenBankService.create(newKznTlKaizenbankmst,existKznTlKaizenbankmst,type,AccSingle,dataKeyidArr,dataFlidArr,dataSuggestArr);
						 saveMsg = "Data Saved Successfully";//UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
						 operation="save";
						 
					}	
					else{
						newKznTlKaizenbankmst.setKzbnRefdocno(refdocNo);
			      		newKznTlKaizenbankmst.setKzbnRefdoctype(refDocType);
			      		if(hsesfty!=null)
			      		{
			      		if (hsesfty.equals("Y"))
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
			      		/*else
			      		{
			      			newKznTlKaizenbankmst.setKzbnEhsrelated("Y");
			      			//existKznTlKaizenbankmst.setKzbnEhsrelated("Y");
			      		}
			      	*/
			      		
						existKznTlKaizenbankmst = kaizenBankService.update(newKznTlKaizenbankmst,existKznTlKaizenbankmst,type,AccSingle,dataKeyidArr,dataFlidArr,dataSuggestArr);
						saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update");
						operation="update"; 
					}
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				if (UIUtils.isValidKeyId(refdocNo))
					successData.put("direct","N");
				successData.put("AccSingle", request.getParameter("AccSingle"));
				returnData.put("successData",successData);
				returnData.put("formClear",false);
				out.print(returnData.toString());
				Boolean clrVal=true;
				try{			
    			    String isMailRequired=request.getParameter("isMailRequired");
    			    CommonMessage.debugMsg("----Mail Sending activity Started-----");
    			    CommonMessage.debugMsg("isMailRequired:" + isMailRequired);
    			    CommonMessage.debugMsg("approavalflag :" + approavalflag);
    			    CommonMessage.debugMsg("Suggestion:" + existKznTlKaizenbankmst.getKzbnKaizen());

    			    if(approavalflag.equals("Y") && isMailRequired.equals("on")){
    			    	sendMailToSuggestedBy(request, response, existKznTlKaizenbankmst.getKzbnSuggestedby(),existKznTlKaizenbankmst.getKzbnKeyid() ,existKznTlKaizenbankmst.getKzbnKaizen() );
						sendMailToJhLeader(request, response, existKznTlKaizenbankmst.getKzbnKeyid(),existKznTlKaizenbankmst.getKzbnFlid(),existKznTlKaizenbankmst.getKzbnKaizen());
    			    }else{
    			    	 CommonMessage.debugMsg("----Mail Not Send "+"isMailRequired:" + isMailRequired+"approavalflag :" + approavalflag);
    			    }
				
               }catch(Exception e){
             	  CommonMessage.debugMsg("Suggestion save mail " +e);
               }
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
	
	
	private void saveKaizenApprovedVerifypopup(HttpServletRequest request,HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Servlet Remove:");
		
		HttpSession httpSession = request.getSession(false);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		String kznkaizen = request.getParameter("kznkaizen");
		String kznstatus = request.getParameter("kznstatus");
		String kznApprvid = request.getParameter("kznApprvid");
		String kznimpcost = request.getParameter("kznimpcost");
		String kzndate = request.getParameter("kzndate");
		String kznremarks = request.getParameter("kznremarks");
		String respons = request.getParameter("respons");
		String mocrequired=request.getParameter("mocrequired");
		String mocitem=request.getParameter("mocitem");
		CommonMessage.debugMsg("MOC Required"+mocrequired);
		KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
		KznTlKaizenbankmst existKznTlKaizenbankmst = (KznTlKaizenbankmst) httpSession.getAttribute("newKznTlKaizenbankmst");
		newKznTlKaizenbankmst = (KznTlKaizenbankmst) UIUtils.setBeanProperties((Object) newKznTlKaizenbankmst, request);

		CommonMessage.debugMsg(" Inside Servlet Action :: For Others ::  "+newKznTlKaizenbankmst.getKzbnKaizen());
		
		KznTlKaizenbankmst newkznTlKaizenbankmst =new KznTlKaizenbankmst();
		
		newkznTlKaizenbankmst.setKzbnKeyid(keyid);
		newkznTlKaizenbankmst.setKzbnKaizen(kznkaizen);
		newkznTlKaizenbankmst.setKzbnStatus(kznstatus);
		newkznTlKaizenbankmst.setKzbnAcrejby(kznApprvid);
		newkznTlKaizenbankmst.setKzbnImplementcost(kznimpcost);
		newkznTlKaizenbankmst.setKzbnTargetdate(kzndate);
		newkznTlKaizenbankmst.setKzbnVerifyremarks(kznremarks);
		newkznTlKaizenbankmst.setKzbnResponsibility(respons);
		newkznTlKaizenbankmst.setKzbnmocrequired(mocrequired);
		newkznTlKaizenbankmst.setKzbnMocitem(mocitem);
		CommonMessage.debugMsg("KEYID: "+keyid);
		try{
			if(UIUtils.isValidKeyId(keyid)){
				existKznTlKaizenbankmst=kaizenBankService.updatekznsugg(newkznTlKaizenbankmst);
				
				CommonMessage.debugMsg("---Mail sending to SuggestedBy for status---");
    			//for (KznTlKaizenbankmst kznTlKaizenbankmst1 : kaizenVerifyList) {
    				//CommonMessage.debugMsg("Sugggested by:"+kznTlKaizenbankmst1.getKzbnSuggestedby()+ "KeyId: " +kznTlKaizenbankmst1.getKzbnKeyid()+  "Status: " +kznTlKaizenbankmst1.getKzbnStatus()+ "Respo:" +kznTlKaizenbankmst1.getKzbnResponsibility()+ "sUGGESTION: "+kznTlKaizenbankmst1.getKzbnKaizen());
    				String status="";
    				if("E".equals(kznstatus)){
    					status="Rework";   					
    				}else if("R".equals(kznstatus)){
    					status="Rejected";   	
    				}else if("V".equals(kznstatus)){
    					status="Accepted";   	
    				//}
    				//sendMailToSuggestedByForStatus(request, response, keyid, status, kznApprvid,respons,kznkaizen);
				}
    			sendMailToSuggestedByForStatus(request, response, keyid, status, kznApprvid,respons,kznkaizen);
    			
				String msgPropertyIdnt = "success-update";
				//CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
			    CommonMessage.debugMsg("Delete End");
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception: "+e);
			JSONObject err = new JSONObject();
			String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
			err.put("successData",mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}
	}
private void saveKaizenSuggRejectStatus(HttpServletRequest request,HttpServletResponse response)throws Exception{
	HttpSession httpSession = request.getSession(false);
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
	PrintWriter out = response.getWriter();
	String keyid = request.getParameter("kznsuggkeyid");
	CommonMessage.debugMsg("The KaizenSuggReject keyid"+keyid);
	String kznstatus = request.getParameter("kznstatus");
	CommonMessage.debugMsg("The Kaizen Status"+kznstatus);
		
	KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
	KznTlKaizenbankmst existKznTlKaizenbankmst = (KznTlKaizenbankmst) httpSession.getAttribute("newKznTlKaizenbankmst");
	newKznTlKaizenbankmst = (KznTlKaizenbankmst) UIUtils.setBeanProperties((Object) newKznTlKaizenbankmst, request);

	CommonMessage.debugMsg(" Inside Servlet Action :: For Others ::  "+newKznTlKaizenbankmst.getKzbnKaizen());
	
	KznTlKaizenbankmst newkznTlKaizenbankmst =new KznTlKaizenbankmst();
	
/*	newkznTlKaizenbankmst.setKzbnKeyid(keyid);
       if(kznstatus.equals("ACCEPTED")){
    	   String kznSuggstatus="R";
		newkznTlKaizenbankmst.setKzbnStatus(kznSuggstatus);
	}*/
	newkznTlKaizenbankmst.setKzbnKeyid(keyid);
	newkznTlKaizenbankmst.setKzbnStatus(kznstatus);  
	CommonMessage.debugMsg("The KeyID"+keyid);
	try{
		if(UIUtils.isValidKeyId(keyid)){
			existKznTlKaizenbankmst=kaizenBankService.updatekznsuggstatus(newkznTlKaizenbankmst);
			String msgPropertyIdnt = "success-update";
			//CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
			JSONObject err = new JSONObject();
			String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
			err.put("successData",mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}
		  //  CommonMessage.debugMsg("Delete End");
	}
	catch(Exception e)
	{
		CommonMessage.debugMsg("Exception: "+e);
		JSONObject err = new JSONObject();
		String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
		err.put("successData",mesg);
		CommonMessage.debugMsg(err.toString());
		out.print(err.toString());
	}
}
	
	private void saveKaizenApprovedVerify(HttpServletRequest request,HttpServletResponse response,String type) throws Exception {
		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	
    	CommonMessage.debugMsg("saveKaizenApprovedVerify");

    		
    		String kaizenDatas = request.getParameter("kaizenDatas");
    		
    		if(UIUtils.isValidKeyId(kaizenDatas))
    		{	
    		
    		try{
    			KaizenFormBean kaizenFormBean =(KaizenFormBean)httpSession.getAttribute("KaizenBankServletKaizenFormBean");
    			 
    			KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst();
    			JSONArray jsonArray = JSONArray.fromString(kaizenDatas);
    			List<KznTlKaizenbankmst> kaizenVerifyList = (List<KznTlKaizenbankmst>) UIUtils.convertJSONArrToList(newKznTlKaizenbankmst, jsonArray);
    			
    			CommonMessage.debugMsg("kaizenVerifyList.size"+kaizenVerifyList.size());
    			
    			
    		
    			KznTlKaizenbankmst kznTlKaizenbankmst =	kaizenBankService.updateVerifyDetails(kaizenVerifyList);
    			
    			CommonMessage.debugMsg("---Mail sending to SuggestedBy for status---");
    			for (KznTlKaizenbankmst kznTlKaizenbankmst1 : kaizenVerifyList) {
    				CommonMessage.debugMsg("Sugggested by:"+kznTlKaizenbankmst1.getKzbnSuggestedby()+ "KeyId: " +kznTlKaizenbankmst1.getKzbnKeyid()+  "Status: " +kznTlKaizenbankmst1.getKzbnStatus()+ "Respo:" +kznTlKaizenbankmst1.getKzbnResponsibility()+ "sUGGESTION: "+kznTlKaizenbankmst1.getKzbnKaizen());
    				String status="";
    				if("E".equals(kznTlKaizenbankmst1.getKzbnStatus())){
    					status="Rework";   					
    				}else if("R".equals(kznTlKaizenbankmst1.getKzbnStatus())){
    					status="Rejected";   	
    				}else if("V".equals(kznTlKaizenbankmst1.getKzbnStatus())){
    					status="Accepted";   	
    				}
    				sendMailToSuggestedByForStatus(request, response, kznTlKaizenbankmst1.getKzbnKeyid(), status, kznTlKaizenbankmst1.getKzbnSuggestedby(),kznTlKaizenbankmst1.getKzbnResponsibility(),kznTlKaizenbankmst1.getKzbnKaizen());
				}
    				
    			String saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
    		
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				returnData.put("successData",successData);
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
	
   //********************************MultipleSuggestion*******************************************//
	@SuppressWarnings("unchecked")
	private void saveMultipleSuggestion(HttpServletRequest request,HttpServletResponse response) throws IOException, BusinessApplicationExceptions{
	

 		// TODO Auto-generated method stub
     HttpSession httpSession = request.getSession(false);
	 ServletOutputStream out = response.getOutputStream();
	 AdmTlUsermst createdBy = UIUtils.getLoginUser(request);
 	String updateMsg=null;
 	try{
		KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst();
   		String Suggestiondetails=request.getParameter("SuggestionDetails");
   		String flid=request.getParameter("flId");
   		String sectionId=request.getParameter("sectionId");
   		newKznTlKaizenbankmst =(KznTlKaizenbankmst)UIUtils.setBeanProperties((Object)newKznTlKaizenbankmst,request);
   		KznTlKaizenbankmst existAbnTlAbnormality = (KznTlKaizenbankmst)httpSession.getAttribute("newKznTlKaizenbankmstServlet");    		 		    			
		List<KznTlKaizenbankmst> SuggetionList=null;    			
		JSONArray SugList= null;	
		
		JSONObject returnData=new JSONObject();
 		if(UIUtils.isValidKeyId(Suggestiondetails)){
 			SugList=JSONArray.fromString(Suggestiondetails);
 			SuggetionList=(List<KznTlKaizenbankmst>)UIUtils.convertJSONArrToList(newKznTlKaizenbankmst,SugList);
 			if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnKeyid())){
 				SuggetionList =kaizenBankService.MultipleSuggestion(SuggetionList,flid,sectionId,createdBy);
 		        updateMsg="Data Saved Successfully"; 
 			}
 			else{
 				updateMsg="Data Updated Successfully"; 
 			}
 		    JSONObject SuccessData=new JSONObject();
 	    	SuccessData.put("msg",updateMsg);
 	    	returnData.put("keyId",newKznTlKaizenbankmst.getKzbnKeyid());
 	    	returnData.put("formClear", false);
 	    	returnData.put("successData", SuccessData);
 	    	out.print(returnData.toString());
 		}
 	}
 	catch (BusinessApplicationExceptions e){
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
		out.print(err.toString());
	}
}	
	
	
	 private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
		 
			HttpSession httpSession = request.getSession(false);
	  		
	  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
	  		if( commonFilter != null && ! createNew ){
	  			FilterValues.setPaginationParams(request,commonFilter);
	  		}	
	  		else{
	  			commonFilter =  new CommonFilter();
	  			
	  			AdmTlUsermst user = UIUtils.getLoginUser(request);
				commonFilter.setAbnDetectBy(user.getUsrm_ccno());
				
	  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
	  			//commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
	  			commonFilter.setViewClick('Y');
	  			httpSession.removeAttribute(beanIdentifier);
	  			httpSession.setAttribute(beanIdentifier, commonFilter);
	  		}
	  		
	  		return commonFilter;
	  	}
	 private CommonFilter populateSafetyCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
		 
			HttpSession httpSession = request.getSession(false);
	  		
	  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
	  		if( commonFilter != null && ! createNew ){
	  			FilterValues.setPaginationParams(request,commonFilter);
	  		}	
	  		else{
	  			commonFilter =  new CommonFilter();
	  			
	  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
	  			commonFilter = 	FilterValues.getSafty(request, commonFilter);
	  			commonFilter.setViewClick('Y');
	  			httpSession.removeAttribute(beanIdentifier);
	  			httpSession.setAttribute(beanIdentifier, commonFilter);
	  		}
	  		
	  		return commonFilter;
	  	}
	 private JSONObject getTableModel(List<String[]> headers) {
			
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			String[] colHeader = headers.get(0);
			String[] colHeader1 = headers.get(1);
			jqGridTableModel.getRowHeaders().add(colHeader1);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableHeight(100);
			jqGridTableModel.setTableWidth(500);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setGroupSummary(false);
			String headerSql = "'SELECT ";
			for (int i = 0; i < colHeader.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setAlign("left");
				jqGridColModel.setWidth(80);
				
				if(i==2){
					jqGridColModel.setAlign("left");
					jqGridColModel.setWidth(415);
					
				}
				if(i==3||i==8||i==9){
					jqGridColModel.setAlign("center");
					jqGridColModel.setWidth(80);
				}
				
				if(i==colHeader.length-1){
					jqGridColModel.setFormatter("txtformatter");
				}
				if(i==0||i==1){
					jqGridColModel.setHidden(false);
				}
					
				jqGridTableModel.getColModel().add(jqGridColModel);
				headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
			}
			
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
			CommonMessage.debugMsg("headerSql.....123..."+headerSql);
			tableModel.set("tableHeight", "70%%");
			tableModel.set("tableWidth", "108%%");
			
			return tableModel;
		}
	 private void sendMailToSuggestedBy(HttpServletRequest request, HttpServletResponse response,String empmKeyId,String suggestionNo,String suggestion) throws ServletException, IOException{
		 CommonMessage.debugMsg("---------Sending Mail to Suggested by----------");
		 CommonMessage.debugMsg("Employee Id:" +empmKeyId);
		 CommonMessage.debugMsg("SuggestionNo:" +suggestionNo);
			try {
				
				if(UIUtils.isValidKeyId(empmKeyId)){
					
					String emailId= kaizenBankService.getEmailId(empmKeyId);	
					CommonMessage.debugMsg("Email ID1  :" +UIUtils.isValidEmail(emailId) + "  " + emailId);
					 if(UIUtils.isValidEmail(emailId)){
						String disclaimerNote = "--";			
						StringBuilder subject = new StringBuilder("");
						subject.append(" Kaizen Suggestion Submitted for Approval ");						
						StringBuilder totalContent = new StringBuilder();					
						totalContent.append("\r\n");
						totalContent.append("Suggestion: " +suggestion);
						totalContent.append("\r\n");
						totalContent.append("\r\n");
						totalContent.append("\r\n");					
						totalContent.append(disclaimerNote);
						totalContent.append("\r\n");
						//totalContent.append("Regards,");
						totalContent.append("\r\n");
						totalContent.append("This is system generated mail. Please do not reply to this mail,\n For any further assistance please contact perfex support team");							
						CommonMessage.debugMsg(" Before sendLotusNotesMail to employee" );
						UIUtils.sendLotusNotesMail(request,response,emailId,null,subject.toString(),totalContent.toString(),null);
						CommonMessage.debugMsg("Content :\n" +totalContent.toString());
						CommonMessage.debugMsg(" After sendLotusNotesMail to employee" );
					 }else{
						 CommonMessage.debugMsg(" Mail not send to eSuggested by no mail id" );
					 }
				}
			}
			catch( ValidationExceptions e){			
				CommonMessage.debugMsg("ValidationExceptions from  sendMailToSuggestedBy()" + e);
			}catch (BusinessApplicationExceptions e) {
				CommonMessage.debugMsg("BusinessApplicationExceptions from  sendMailToSuggestedBy()" + e);
			} /*
				 * catch(NotesException e){
				 * CommonMessage.debugMsg("NotesException from  sendMailToSuggestedBy()" + e);
				 * }
				 */catch(Exception e){
				CommonMessage.debugMsg("Exception from  sendMailToSuggestedBy()" + e);
			} catch(Throwable e){
				
			}
			 CommonMessage.debugMsg("--------- Mail send to Suggested by----------");
			
		}
	 private void sendMailToJhLeader(HttpServletRequest request, HttpServletResponse response,String kaizenNo,String kaizenFlid,String suggestion) throws ServletException, IOException,Exception{
		 CommonMessage.debugMsg("---------Sending Mail To Jh Leader for Suggestion Approvel----------");
		 CommonMessage.debugMsg("kaizen No:" +kaizenNo);
		 CommonMessage.debugMsg("kaizenFlid :" +kaizenFlid); 
		 String kaizenSuggestedName=kaizenBankService.getSuggestedName(kaizenNo);
		 CommonMessage.debugMsg("The kaizenSuggestedName::::"+kaizenSuggestedName);
			try {
				
				if(UIUtils.isValidKeyId(kaizenFlid)){		
					
					String emailId= kaizenBankService.getJhLeaderEmailId(kaizenFlid);	
					CommonMessage.debugMsg("Jh Leader E-Mail Id  :" +UIUtils.isValidEmail(emailId) + "  " + emailId);
					if(UIUtils.isValidEmail(emailId)){	
						
						String disclaimerNote = "--";			
						StringBuilder subject = new StringBuilder("");
						subject.append(" Kaizen Suggestion Wating for Approval ");						
						StringBuilder totalContent = new StringBuilder();					
						totalContent.append("\r\n");
						totalContent.append("Suggestion  : " +suggestion).append("\r\n");
						totalContent.append("\r\n");
						totalContent.append("Suggested By  : " +kaizenSuggestedName);
						totalContent.append("\r\n");
						totalContent.append(disclaimerNote);
						totalContent.append("\r\n");
						//totalContent.append("Regards,");
						totalContent.append("\r\n");
						totalContent.append("This is system generated mail. Please do not reply to this mail,\n For any further assistance please contact perfex support team");							
						CommonMessage.debugMsg(" Before sendLotusNotesMail to JH leader" );
						UIUtils.sendLotusNotesMail(request,response,emailId,null,subject.toString(),totalContent.toString(),null);
						CommonMessage.debugMsg("Content :\n" +totalContent.toString());
						CommonMessage.debugMsg(" After sendLotusNotesMail to jh leader" );
					}
				}else{
					CommonMessage.debugMsg(" Mail not send to jh leader for suggestion Approval no mail id" );
				}
			}
			catch( ValidationExceptions e){				
				CommonMessage.debugMsg("ValidationExceptions from  sendMailToJhLeader()" + e);
			
			}catch (BusinessApplicationExceptions e) {
				CommonMessage.debugMsg("BusinessApplicationExceptions from  sendMailToJhLeader()" + e);
			
			} /*
				 * catch(NotesException e) {
				 * CommonMessage.debugMsg("NotesException from  sendMailToJhLeader()" + e); }
				 */catch(Exception e){
				
				CommonMessage.debugMsg("Exception from  sendMailToJhLeader()" + e);
			} catch(Throwable e){
				
			}
			
			 CommonMessage.debugMsg("--------- Mail Send To Jh Leader for Suggestion Approvel----------");
		}

	 private void sendMailToSuggestedByForStatus(HttpServletRequest request, HttpServletResponse response,String kaizenNo,String suggestionStatus,String suggestedBy, String responsibility,String kaizen) throws ServletException, IOException{
		 CommonMessage.debugMsg("---------sendMailToSuggestedByForStatus----------");
		 CommonMessage.debugMsg("kaizen No:" +kaizenNo);
		 CommonMessage.debugMsg("Status :" +suggestionStatus);
				
			try {
				
				if(UIUtils.isValidKeyId(kaizenNo)){		
					//Receiving Emplyee Key Id and mail id
					String datas= kaizenBankService.getEmailIdOfSuggestedBy(kaizenNo);	
					if(UIUtils.isValidKeyId(datas)){
						String dataArray[]=datas.split(";");
						String emailId=dataArray[0];
						String empmKeyIdSuggestedBy=dataArray[1];
						String ehsrelated=dataArray[2];
						kaizen = dataArray[3];
						CommonMessage.debugMsg("KeyId Suggested By  :"+ empmKeyIdSuggestedBy);
						CommonMessage.debugMsg("Resposibility       :" + responsibility);
						CommonMessage.debugMsg("Email ID            :"+ emailId);
						CommonMessage.debugMsg("ehsrelated            :"+ ehsrelated);
						CommonMessage.debugMsg("kaizen            :"+ kaizen);
						//Send Mail to Suggested By for notification
						if(UIUtils.isValidEmail(emailId)){								
							String disclaimerNote = "--";			
							StringBuilder subject = new StringBuilder("");
							subject.append(" Kaizen Suggestion Status");						
							StringBuilder totalContent = new StringBuilder();					
							totalContent.append("\r\n");
							totalContent.append("Suggestion: " +kaizen);
							totalContent.append("Status : " +suggestionStatus).append("\r\n").append("\r\n").append("\r\n");
							totalContent.append(disclaimerNote);
							totalContent.append("\r\n");
							//totalContent.append("Regards,");
							totalContent.append("\r\n");
							totalContent.append("This is system generated mail. Please do not reply to this mail,\n For any further assistance please contact perfex support team");							
							CommonMessage.debugMsg(" Before sendLotusNotesMail to Suggested by for status" );
							UIUtils.sendLotusNotesMail(request,response,emailId,null,subject.toString(),totalContent.toString(),null);
							CommonMessage.debugMsg(" After sendLotusNotesMail to Suggested by for status" );
							CommonMessage.debugMsg("totalContent: "  +totalContent.toString());
						}
						
						//Send Mail to Reposible person
						if(UIUtils.isValidKeyId(responsibility)){
							CommonMessage.debugMsg("responsibility : "  +responsibility + " Mail is going to responsible person");
							if(!responsibility.equals(empmKeyIdSuggestedBy) && ehsrelated.equals("N")){
								String email_id= kaizenBankService.getEmailId(responsibility);	
								CommonMessage.debugMsg("Email ID1  :" +UIUtils.isValidEmail(email_id) + "  " + email_id);
								 if(UIUtils.isValidEmail(email_id)){
									String disclaimerNote = "--";			
									StringBuilder subject = new StringBuilder("");
									subject.append(" Kaizen Waiting for Implementation ");						
									StringBuilder totalContent = new StringBuilder();					
									totalContent.append("\r\n");
									totalContent.append("Kaizen: " +kaizen);
									totalContent.append("\r\n");
									totalContent.append("\r\n");
									totalContent.append("\r\n");					
									totalContent.append(disclaimerNote);
									totalContent.append("\r\n");
									//totalContent.append("Regards,");
									totalContent.append("\r\n");
									totalContent.append("This is system generated mail. Please do not reply to this mail,\n For any further assistance please contact perfex support team");					
									CommonMessage.debugMsg(" Before sendLotusNotesMail Reponsible Person" );
									UIUtils.sendLotusNotesMail(request,response,emailId,null,subject.toString(),totalContent.toString(),null);
									CommonMessage.debugMsg("Content :\n" +totalContent.toString());
									CommonMessage.debugMsg(" After sendLotusNotesMail to employee" );
								 }else{
									 CommonMessage.debugMsg(" Mail not send to eSuggested by no mail id" );
								 }
							}
						}
					}
				}
			}
			catch( ValidationExceptions e){				
				CommonMessage.debugMsg("ValidationExceptions from  sendMailToSuggestedByForStatus()" + e);
			
			}catch (BusinessApplicationExceptions e) {
				CommonMessage.debugMsg("BusinessApplicationExceptions from  sendMailToSuggestedByForStatus()" + e);
			
			} /*
				 * catch(NotesException e) { CommonFunctions.
				 * debugMsg("NotesException from  sendMailToSuggestedByForStatus()" + e); }
				 */catch(Exception e){
				
				CommonMessage.debugMsg("Exception from  sendMailToSuggestedByForStatus()" + e);
			}catch(Throwable e){
				
			}
			
			CommonMessage.debugMsg("End");
		}

	 
	 
	 
}
