package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JhAuditCreationBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAuditparameter;
import com.akranta.tpm.service.JhDmtAuditUploadService;
import com.akranta.tpm.service.impl.JhDmtAuditUploadServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class JhDmtAuditUploadServlet
 */
@WebServlet("/JhDmtAuditUploadServlet")
public class JhDmtAuditUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JhDmtAuditUploadService jhdmtAuditUploadService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JhDmtAuditUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try {
			process(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
  private void process(HttpServletRequest request,HttpServletResponse response) throws Exception{
	 
	    HttpSession httpSession = request.getSession(false);		
		String action = UIUtils.getActionPart(request);
		ComboFilter comboFilter = new ComboFilter();
		try {
			jhdmtAuditUploadService = (JhDmtAuditUploadServiceImpl)UIUtils.getServiceObject(request,"JhDmtAuditUploadServiceImpl");
			CommonMessage.debugMsg("  jh AND dmt AUDIT jwt token : "+httpSession.getAttribute("tpmjwttoken") );
			jhdmtAuditUploadService.JhDmtAuditUploadServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		
		} catch (ServiceObjectCreationException e) {
			
		}
	/*  if(action.equals("jhdmtAuditUpload_input.jhdmtauditupd")){
		  
		  UIUtils.forwardRequest(request, response,"/pages/JHAudit/JHDMTAuditUpload.jsp");
		  
	  }*/
		 if(action.equals("jhdmtAuditUpload_input.jhdmtauditupd")) 
		{
			String keyid=request.getParameter("keyId");
			CommonMessage.debugMsg("The keyid is:::"+keyid);
			String mode = request.getParameter("mode");
			CommonMessage.debugMsg("The Mode is:::"+mode);
			String jhamAuditpillar = request.getParameter("jhamAuditpillar");
			String jhamAudittype = request.getParameter("jhamAudittype");
			CommonFilter commonFilter =new CommonFilter();
			CommonMessage.debugMsg("jhamAudittype..."+jhamAudittype);
			String Status=request.getParameter("status");
			
			response.setContentType("text/html");
			if( UIUtils.isValidKeyId(keyid)) //&& userEvent == null) ||( userEvent != null &&  ! "new".equals(userEvent))
			{
				CommonMessage.debugMsg("keyid:"+keyid);
				JhaTlAuditmst jhaTlAuditmst=new JhaTlAuditmst();
				jhaTlAuditmst.setJhamKeyid(keyid);
				jhaTlAuditmst=  jhdmtAuditUploadService.select(jhaTlAuditmst);
				
//				String date = jhaTlAuditmst.getJhamAuditdate();
//			    String date1 = CommonFunctions.pg_getFormatDateFromDate(date);
//				jhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getFormatDateFromDate(date));
				
				//08-jan
				String date = jhaTlAuditmst.getJhamAuditdate();
			    String date1 = CommonFunctions.pg_getDateTimeFromPGTimeStamp(date);
				jhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));
				
				
				JhaTlAuditparameter jhaTlAuditparameter=new JhaTlAuditparameter();
				//String gridmode=request.getParameter("hdnFrmMode");
				CommonMessage.debugMsg("mode..."+mode);
				jhaTlAuditmst.setJhamStatus(Status);
				FormModes modes = FormModes.view;
				if("EDIT".equals(mode))
				{
					modes = FormModes.modify;					
				}
				JhAuditCreationBean jhAuditCreationBean= new JhAuditCreationBean();				
				String minMarks =jhdmtAuditUploadService.getMinMarks(jhaTlAuditmst.getJhamJhstepid() ,jhaTlAuditmst.getJhamAuditteamid());
				jhaTlAuditparameter.setJhapKeyid(jhaTlAuditmst.getJhamAuditteamid());
				jhaTlAuditparameter = jhdmtAuditUploadService.recallValues(jhaTlAuditparameter);
				request.setAttribute("minMarks",minMarks);
				httpSession.setAttribute("jhaTlAuditmst", jhaTlAuditmst);
				httpSession.setAttribute("jhaTlAuditparameter", jhaTlAuditparameter);
				request.setAttribute("jhaTlAuditmst", jhaTlAuditmst);
				request.setAttribute("jhamAuditpillar", jhamAuditpillar);
				request.setAttribute("jhamAudittype", jhamAudittype);
				request.setAttribute("jhaTlAuditparameter", jhaTlAuditparameter);
				request.setAttribute("jhAuditCreationBean",jhAuditCreationBean);
				request.setAttribute("mode",FormModes.modify);
			}
			else{
				request.setAttribute("mode",FormModes.create);
				request.setAttribute("jhamAuditpillar", jhamAuditpillar);
				request.setAttribute("jhamAudittype", jhamAudittype);
			}
			request.setAttribute("jhamAuditpillar",jhamAuditpillar);			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/JHAudit/JHDMTAuditUpload.jsp"); 
			rd.forward(request, response);
		}
			else if(action.equals("jhuploadaudit_recall.jhdmtauditupd")) 
			{
				String jhamAuditpillar = request.getParameter("jhamAuditpillar");
				String jhamAudittype = request.getParameter("jhamAudittype");
				String jhamAuditteamid = request.getParameter("templateid");
				String jhamFlid = request.getParameter("flid");
				String jhamJhstepid = request.getParameter("stepid");
				String jhamAuditdate = request.getParameter("auditdate");
				String jhamAuditortype = request.getParameter("auditortype");
				CommonMessage.debugMsg("jhamAudittype..."+jhamAudittype);
				CommonMessage.debugMsg("jhamAuditpillar..."+jhamAuditpillar);
				CommonMessage.debugMsg("jhamAuditteamid..."+jhamAuditteamid);
				CommonMessage.debugMsg("jhamFlid..."+jhamFlid);
				CommonMessage.debugMsg("jhamJhstepid..."+jhamJhstepid);
				CommonMessage.debugMsg("jhamAuditdate..."+jhamAuditdate);
				CommonMessage.debugMsg("jhamAuditortype..."+jhamAuditortype);
				response.setContentType("text/html");
				if( UIUtils.isValidKeyId(jhamAuditpillar)
						&&  UIUtils.isValidKeyId(jhamAudittype)
						&&  UIUtils.isValidKeyId(jhamAuditteamid)
						&&  UIUtils.isValidKeyId(jhamFlid)
						&&  UIUtils.isValidKeyId(jhamJhstepid)
						&&  UIUtils.isValidKeyId(jhamAuditdate)
						&&  UIUtils.isValidKeyId(jhamAuditortype)) 
				{
					CommonMessage.debugMsg("keyid:"+jhamAuditpillar);
					JhaTlAuditmst jhaTlAuditmst=new JhaTlAuditmst();
					jhaTlAuditmst.setJhamAuditpillar(jhamAuditpillar);
					jhaTlAuditmst.setJhamAudittype(jhamAudittype);
					jhaTlAuditmst.setJhamAuditteamid(jhamAuditteamid);
					jhaTlAuditmst.setJhamFlid(jhamFlid);
					jhaTlAuditmst.setJhamJhstepid(jhamJhstepid);
					jhaTlAuditmst.setJhamAuditortype(jhamAuditortype);
					jhaTlAuditmst.setJhamAuditdate(jhamAuditdate);
					
					String date2 = jhaTlAuditmst.getJhamAuditdate();
					jhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getDateTimeFromDate(date2));
					//jhaTlAuditmst.setJhamAuditdate(UIUtils.getActualDateForm(jhaTlAuditmst.getJhamAuditdate()));
					jhaTlAuditmst.setJhamTotalpoints("0");
					jhaTlAuditmst.setJhamNextauditdate(CommonFunctions.pg_getDateTimeFromDate(date2));
					jhaTlAuditmst.setJhamCreatedon(CommonFunctions.pg_getDateTimeFromDate(date2));
					jhaTlAuditmst.setJhamModifiedon(CommonFunctions.pg_getDateTimeFromDate(date2));
					jhaTlAuditmst=  jhdmtAuditUploadService.select(jhaTlAuditmst);
					
					String date3 = jhaTlAuditmst.getJhamAuditdate();
					jhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date3));
					jhaTlAuditmst.setJhamAuditdate(UIUtils.getActualDateForm(jhaTlAuditmst.getJhamAuditdate()));

					JSONObject successData = new JSONObject(); 
					successData.put("keyId",jhaTlAuditmst.getJhamKeyid());
					ServletOutputStream out = response.getOutputStream();
					JSONObject returnData = new JSONObject();
					returnData.put("jhaTlAuditmst", jhaTlAuditmst);
					returnData.put("successData", successData);
					out.print(returnData.toString());
					out.close();
				}			
			}
			else if(action.equals("fillStatus_recall.jhdmtauditupd")){
				PrintWriter out = response.getWriter();
				String parameter=request.getParameter("parameter");
				String auditTeam =request.getParameter("auditTeam"); 
				String flid =request.getParameter("flid"); 
				String isJhLeader =request.getParameter("isJhLeader"); 
				String rowId =request.getParameter("rowId");
				String jhLeader ="";
				List< String[]> minpointsList  = jhdmtAuditUploadService.getMinPoints(parameter,auditTeam);
				if(UIUtils.isValidKeyId(isJhLeader)){
					jhLeader  = jhdmtAuditUploadService.getJhLeader(flid);
				}
				JSONObject minpointsListdata = new JSONObject(); 
				if(minpointsList.size()>0){
					minpointsListdata.put("points", minpointsList.get(0)[0]);
					
				}
				
				minpointsListdata.put("jhLeader", jhLeader);
				out.print(minpointsListdata);
			}
		 
		 else if(action.equals("jhAuditUploadGrid_input.jhdmtauditupd")) //
			{	String jhamAuditpillar = request.getParameter("TYPE");
				String jhamAudittype = request.getParameter("AUDIT");
				httpSession.setAttribute("jhamAuditpillar", jhamAuditpillar);			
				request.setAttribute("jhamAuditpillar", jhamAuditpillar);
				httpSession.setAttribute("jhamAudittype", jhamAudittype);
				request.setAttribute("jhamAudittype", jhamAudittype);
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/JHAudit/jhAuditUploadGrid.jsp"); 
				rd.forward(request, response);
			}
		 else if(action.equals("jhAuditUploadGrid_getCol.jhdmtauditupd")){
				PrintWriter out = response.getWriter();
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "colModelJhAuditSheet");
				httpSession.removeAttribute("colModeljhAuditUpload");
				httpSession.setAttribute("colModeljhAuditUpload",colModel);
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "colModelJhAuditSheet"));
			}
		 

			else if(action.equals("jhAuditUploadGrid_getData.jhdmtauditupd")){
				PrintWriter out = response.getWriter();
				try
				{	
					String jhamAuditpillar = request.getParameter("TYPE");
					String jhamAudittype = request.getParameter("AUDIT");
					String flid = request.getParameter("flid");
					 UIUtils.displayRequestParamsValue(request);
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"jhAuditUploadCreation",true);			
					 JSONObject jsonObject = new JSONObject();
					 if(!UIUtils.isValidKeyId(flid)){
						 flid=CommonFunctions.getLoginFlid(request);
					 }
					 commonFilter.setFlid(flid);
					 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	  }
					 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
				 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
							  commonFilter.setToDate(CommonFunctions.getDate());
					 }	
					 commonFilter.setKey(jhamAuditpillar);
					 commonFilter.setType(jhamAudittype);
					 List< String[]> jhAuditUploadCreation  = jhdmtAuditUploadService.getjhAuditUploadfillGrid(commonFilter);
					 jsonObject = UIUtils.convertToJqGridTableObject(jhAuditUploadCreation,request,1,1,commonFilter.getTotalRecordCnt());
					 
					 out.println(jsonObject);
					 commonFilter.setViewClick('N');	 
					 httpSession.removeAttribute("jhAuditUploadCreation");
		  			 httpSession.setAttribute("jhAuditUploadCreation", commonFilter);
						
					 
		  		}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
		 
			else if(action.equals("jhAuditUploadGrid_getExcel.jhdmtauditupd")){
				exportToExcel(request,response);
			}
		 
	  else if(action.equals("jhdmtAuditUpload_save.jhdmtauditupd")){
		  CommonMessage.debugMsg("Inside the save");
		  JhAuditCreationBean jhAuditCreationBean = new JhAuditCreationBean();
		 AuditSave(request,response,jhAuditCreationBean); 
	  }
	  else if(action.equals("functionalLoc.jhdmtauditupd"))
		{	
			String type = request.getParameter("type");	
			CommonMessage.debugMsg("The type is:"+type);
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();				
			functLocFieldNameBean.setCompany("cmbComp");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setFactMandatory(false);	
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			//sriram
			functLocFieldNameBean.setMachDisable(true);
			
			FormModes formModes = FormModes.create;					
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);			
		}
		
		
		else if(action.equals("functionalLocDMT.jhdmtauditupd"))
		{	
			String type = request.getParameter("type");	
			CommonMessage.debugMsg("The type DMT is:"+type);
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setFactMandatory(false);			
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			CommonMessage.debugMsg("vctype:"+type);
			functLocFieldNameBean.setMachMandatory(false);
			//sriram
			
		//	if ("DMT".equals(type)) {
			    functLocFieldNameBean.setCellDisable(true);
			    functLocFieldNameBean.setMachDisable(true);
			//}
			
			FormModes formModes = FormModes.create;					
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);			
		}	 
  }
  
	private void exportToExcel(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String jhamAuditpillar = request.getParameter("TYPE");
		String jhamAudittype = request.getParameter("AUDIT");		 
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"jhAuditUploadCreation",false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		commonFilter.setKey(jhamAuditpillar);
		commonFilter.setType(jhamAudittype);
		String tableModel = (String) httpSession.getAttribute("colModeljhAuditUpload");
		JSONObject tblJSONObj = JSONObject.fromString(tableModel);		
		tblJSONObj.put("title", jhamAudittype+" Audit Upload");
		String format = ExcelUtils.getFormat(request);		
		Workbook wb = jhdmtAuditUploadService.jhAuditUploadExportExcel(commonFilter,tblJSONObj,format);
		commonFilter.setFromRow(tmpFromRow);
		
		commonFilter.setFromRow(tmpFromRow);		
		ExcelUtils.writeToResponse(response, wb, jhamAudittype+"AuditUpload", format);
	}
	
  private void AuditSave(HttpServletRequest request, HttpServletResponse response,JhAuditCreationBean jhAuditCreationBean) throws IOException{
  	HttpSession httpSession = request.getSession(false);
  	ServletOutputStream out = response.getOutputStream();
  	AdmTlUsermst user = UIUtils.getLoginUser(request);
  	JSONObject returnData = new JSONObject();
  	JSONObject successData = new JSONObject(); 
  	
  	if( httpSession != null && user != null)
  	{	
  		JhaTlAuditmst newJhaTlAuditmst = new JhaTlAuditmst();
  		newJhaTlAuditmst.setJhamCreatedby(user.getUsrm_ccno());
  		newJhaTlAuditmst =(JhaTlAuditmst)UIUtils.setBeanProperties((Object)newJhaTlAuditmst,request);
		 	jhAuditCreationBean =(JhAuditCreationBean) UIUtils.setBeanProperties((Object)jhAuditCreationBean,request);
		 	JhaTlAuditmst existJhaTlAuditmst = (JhaTlAuditmst)httpSession.getAttribute("jhAuditSheetmstCreationItcServlet"); 
			String fileManager = request.getParameter("filemanager");
	    	
			try{
			   
				boolean insert = true;
				if( UIUtils.isValidKeyId(newJhaTlAuditmst.getJhamKeyid()))
				{
					existJhaTlAuditmst = jhdmtAuditUploadService.update(newJhaTlAuditmst,existJhaTlAuditmst,jhAuditCreationBean);
					insert = false;						
				}
				else{
					existJhaTlAuditmst = jhdmtAuditUploadService.create(newJhaTlAuditmst,existJhaTlAuditmst,jhAuditCreationBean);
				}
				
				new JSONObject();			
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("JhamKeyid",existJhaTlAuditmst.getJhamKeyid());
				String msgPropertyIdnt;				
			 
				if( insert){
					msgPropertyIdnt = "success-save";
				}else
					msgPropertyIdnt = "success-update";
				
			    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				successData.put("mode",jhAuditCreationBean.getFormActionMode() );
				successData.put("keyId", newJhaTlAuditmst.getJhamKeyid());

				if(UIUtils.isValidKeyId(fileManager)){
					
					successData.put("filemanager", true);
				}else
				{
				
					successData.put("filemanager", false);	
				}
				successData.put("pillar", newJhaTlAuditmst.getJhamAuditpillar());	
				successData.put("audittype", newJhaTlAuditmst.getJhamAudittype());	
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				httpSession.setAttribute( "jhAuditSheetmstCreationItcServlet", existJhaTlAuditmst);					
				out.print(returnData.toString());
				out.close();				
			}			
			catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"JhDmtAuditUploadExceptionItc");
				errMessage.put("fromMode",jhAuditCreationBean.getFormActionMode());
				out.print(errMessage.toString());					
			}catch(Exception e){
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}			
	    }	
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);			
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew )
		{
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAudit(request, commonFilter);
			
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}		
		return commonFilter;
	}
}
