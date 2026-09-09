
package com.akranta.tpm.controller;
import com.akranta.tpm.utils.CommonMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
//import com.akranta.tpm.bean.RiskBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AbnTlAbnhistorydtl;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AbnTlDtl;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.KznTlKaizenbankmst;
/*import com.akranta.tpm.model.SheTlDeriskdtl;
import com.akranta.tpm.model.SheTlDeriskmst;
import com.akranta.tpm.model.SheTlPlantSafetydtl;*/
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.AbnormalityFormService;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.WorkOrderService;
import com.akranta.tpm.service.impl.AbnormalityFormServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.UserServiceImpl;
/*import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
*/import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;
import com.akranta.tpm.utils.WOConstants;
//import com.sun.corba.se.impl.orbutil.closure.Constant;




public class AbnormalityFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * Created : Karthick.T
     * Date : 30 Nov 2011
     * Modified :Siddharth .A
     * Date : 12 Jan 2012
     */
	AbnormalityServiceApi abnServiceApi;
	AbnormalityFormService abnService;
	CommonFilterService commonFilterService; 
	WorkOrderService workOrderService;
	//AbnormalityBean abnBean;
    public AbnormalityFormServlet() throws Exception {
        super();
 /*       abnService = new AbnormalityFormServiceImpl();
        workOrderService = new WorkOrderServiceImpl();
        commonFilterService = new CommonFilterServiceImpl();
    */    
      //  abnBean = new AbnormalityBean();
       
    }

	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
   
    private void initilizeInputMode(FormModes mode, String formName,HttpServletRequest request, HttpSession httpSession)throws Exception
    {    	
    	String abnKeyId =request.getParameter("AbnId");
    	String abndKeyid = request.getParameter("HseAbnId");// For Safety abnormality
    	String abnStatus=request.getParameter("abnStatus");
    	String redirectTo = request.getParameter("redirectFrom");
    	String woKeyId = request.getParameter("WOID");
    	String backTo = request.getParameter(WOConstants.backTo);
    	String enableComplete = request.getParameter("enableComplete");
    	httpSession.removeAttribute(WOConstants.backTo);

		if(UIUtils.isValidKeyId(backTo))
			httpSession.setAttribute(WOConstants.backTo,backTo);
		String delActivity = request.getParameter("delActivity");				
		if(UIUtils.isValidKeyId(delActivity))
			request.setAttribute("delActivity","Y");
    	if(UIUtils.isValidKeyId(redirectTo))
    	{
    		httpSession.removeAttribute("redirectTo");
    		httpSession.setAttribute("redirectTo",redirectTo);
    	}
    	if(UIUtils.isValidKeyId(woKeyId))
    	{
    		WomTlWomst womTlWomst = workOrderService.select(woKeyId);
    		if(UIUtils.isValidKeyId(womTlWomst.getWomsAllotteddate()))
    			request.setAttribute("allottedDateMSR", womTlWomst.getWomsAllotteddate());
    		httpSession.setAttribute("WorkOrderABN", womTlWomst);
    	}
    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	CommonMessage.debugMsg("machineId : " +request.getParameter("machineId"));
    	
    	
    	
		
		AbnormalityBean abnormalityBean = new AbnormalityBean(mode);
		
		//WomTlWomst womTlWomst = (WomTlWomst)httpSession.getAttribute(WOConstants.abnInWOServlet); 	
		AbnTlAbnormality  abnTlAbnormality=null;
		CommonMessage.debugMsg("mode...."+mode);
		if(FormModes.completion.equals(mode))
		{
			mode = FormModes.complete;
			abnormalityBean.setStatus("C");
			//abnormalityBean = new AbnormalityBean(FormModes.completion);
			abnormalityBean.setDisableForm(true);
			
		}
		if(UIUtils.isValidKeyId(abnKeyId))
		{
			request.setAttribute("modify", true);
		}
		if(abnKeyId==null)
		{
			abnormalityBean = new AbnormalityBean(FormModes.create);
			abnormalityBean.setFormActionMode("Create");
		}
		else if( UIUtils.isValidKeyId(abnStatus))
		{
			if(abnStatus.equals("COMPLETED") ){
				mode = FormModes.complete;
				abnormalityBean.setStatus("C");
				//abnormalityBean = new AbnormalityBean(FormModes.completion);
				abnormalityBean.setDisableForm(true);
			}
		
			abnormalityBean.setTagStatus(abnStatus);
		}
	
		
		
		request.setAttribute("abnStatus", abnStatus);
		httpSession.removeAttribute("AbnormalityFormMode");
		httpSession.setAttribute("AbnormalityFormMode", mode);
		
		
		httpSession.setAttribute("abnormalityFormMode", abnormalityBean.getFormMode());
		
		if( abnKeyId != null && abnKeyId.trim().length() > 0 )
		{
			abnTlAbnormality = abnService.select(abnKeyId);
			AbnTlDtl abnTlDtl = null;
			abnStatus = abnTlAbnormality.getAbnmStatus();
			request.setAttribute("abnStatus", abnStatus);
			CommonMessage.debugMsg("ID="+abnTlAbnormality.getAbnmEquipmentid());
			String woendDate = UIUtils.removeDefaultDate(abnTlAbnormality.getAbnmWoendtime()," ");
			
			abnTlAbnormality.setAbnmWoendtime(woendDate);
			if(abnTlAbnormality.getAbnmEffectivedate().contains(Constants.pgPassNullDateTime))
			{
				abnTlAbnormality.setAbnmEffectivedate("");	
			}else {
				abnTlAbnormality.setAbnmEffectivedate(CommonFunctions.pg_getFormatDateFromDate(abnTlAbnormality.getAbnmEffectivedate().substring(0,10)));
			}
			
			if(abnTlAbnormality.getAbnmAccecptDate().contains(Constants.pgPassNullDateTime))
			{
				abnTlAbnormality.setAbnmAccecptDate("");	
			}else{
				abnTlAbnormality.setAbnmAccecptDate(CommonFunctions.pg_getFormatDateFromDate(abnTlAbnormality.getAbnmAccecptDate().substring(0,10)));	
			}
			
			
			if( UIUtils.isValidKeyId(abnStatus))
			{
				CommonMessage.debugMsg("abnStatus123...."+abnStatus);
				if(abnStatus.equals("C") )
				{
					mode = FormModes.complete;
					abnormalityBean.setStatus(abnTlAbnormality.getAbnmStatus());
					//abnormalityBean = new AbnormalityBean(FormModes.completion);
					abnormalityBean.setDisableForm(true);
				}
				abnormalityBean.setStatus(abnStatus);
				abnormalityBean.setTagStatus(abnStatus);
			}
			
			if(UIUtils.isValidKeyId(abndKeyid))
			{
				
				abnTlDtl=  abnService.selectHseData(abndKeyid,null);				
				httpSession.removeAttribute("abnTlDtlAbnServlet");
				httpSession.setAttribute("abnTlDtlAbnServlet", abnTlDtl);
				request.setAttribute("abnTlDtl",abnTlDtl);
			}
			else
			{
				if(!UIUtils.isValidKeyId(redirectTo))
				{
					abnTlDtl=  abnService.selectHseData(abnKeyId,"M");
					httpSession.removeAttribute("abnTlDtlAbnServlet");
					httpSession.setAttribute("abnTlDtlAbnServlet", abnTlDtl);
					request.setAttribute("abnTlDtl",abnTlDtl);
				}
			}
			if(UIUtils.isValidKeyId(enableComplete))
	    	{
				//abnormalityBean.setStatus("C");
	    		request.setAttribute("enableComplete", "Y");
	    	}
			else
			{
				String tag = abnService.checkTag();
				if(UIUtils.isValidKeyId(tag))
				{
					if(tag.equals("Y"))
						request.setAttribute("enableComplete", "Y");
				}
				else
					request.setAttribute("pendingFlag", "N");
			}
			
			String refDocID = abnTlAbnormality.getAbnmRefdocid();
			
			
			CommonMessage.debugMsg(refDocID + " : refDocID");
			if(UIUtils.isValidKeyId(refDocID))
			{
				//madhan 
//				String directEntry = abnService.checkDirectEntry(refDocID);
//				CommonMessage.debugMsg("directEntry : "+ directEntry);
//				if(UIUtils.isValidKeyId(directEntry))
//				{
//					if(directEntry.equals("Y"))
//						request.setAttribute("disableTag", "Y");
//						
//				}
			}
				
			
			List <String []> mchHierachy = commonFilterService.getMachineHierarchy(abnTlAbnormality.getAbnmEquipmentid());
			
			abnormalityBean.setFormMode(mode);
			/*if(abnTlAbnormality.getAbnmWoendtime().contains(Constants.passNullDate)
				||abnTlAbnormality.getAbnmWoendtime().contains(Constants.futureNullDate))
				{abnTlAbnormality.setAbnmWoendtime(CommonFunctions.getDate());}*/
			
			CommonMessage.debugMsg("getAbnmWoendtime ="+abnTlAbnormality.getAbnmWoendtime());
			if(mchHierachy != null && mchHierachy.size() > 0)
			{	abnormalityBean.setFactory(mchHierachy.get(0)[0]);
				abnormalityBean.setCostcenter(mchHierachy.get(0)[5]);
				CommonMessage.debugMsg("mchHierachy.get(0)[0]="+mchHierachy.get(0)[0]);
				CommonMessage.debugMsg("mchHierachy.get(0)[5]="+mchHierachy.get(0)[5]);
			}
			else 
			{
				CommonMessage.debugMsg("MChHierachy Is NuLL");}
			
			String dd = abnTlAbnormality.getAbnmDetectiondate();
			dd =dd.substring(11, 16);
			CommonMessage.debugMsg("dd in servlet="+dd);
			abnormalityBean.setAbnmDetectedbytime(dd);  
			
			fillAbnData(abnTlAbnormality,abnormalityBean);
			
			httpSession.removeAttribute("abnTlAbnormalityAbnServlet");
			httpSession.setAttribute("abnTlAbnormalityAbnServlet", abnTlAbnormality);
		}
		else 
		{
			abnTlAbnormality=new AbnTlAbnormality();
			abnTlAbnormality.setAbnmDetectedby(user.getUsrm_ccno());
			abnTlAbnormality.setAbnmCompletedby(user.getUsrm_ccno());
			String tag = abnService.checkTag();
			
			if(UIUtils.isValidKeyId(tag))
			{
				if(tag.equals("Y"))
					request.setAttribute("pendingFlag", "Y");
				else
					request.setAttribute("pendingFlag", "N");
			}
			else
				request.setAttribute("pendingFlag", "N");
			
		}
		if("SHE".equalsIgnoreCase(formName))
		{
			abnormalityBean.setDisableTagclass(true);
			abnormalityBean.setDisableAbnType(true);
			//abnTlAbnormality.setAbnmTagclassid("TAG000003");
			//abnTlAbnormality.setAbnmTypeid("ABT0007");
			abnormalityBean.setPillarName("SHE");
		}
		else if("JH".equalsIgnoreCase(formName))
		{
			if(mode!=FormModes.view && mode!=FormModes.completion && mode!=FormModes.removal )
			{	abnormalityBean.setDisableTagclass(false);
				abnormalityBean.setDisableAbnType(false);
			}	
			else
			{	abnormalityBean.setDisableTagclass(true);
				abnormalityBean.setDisableAbnType(true);
			}
			abnormalityBean.setPillarName("JH");
		}
	String showCompDate = abnService.showAbnQuery();
		
	CommonMessage.debugMsg("fddhfg test showCompDate............."+showCompDate);
	
		String[] abnValidateArr =  showCompDate.split(",");
		String desc = request.getParameter("desc");
		String remarks = request.getParameter("remarks");
		if(UIUtils.isValidKeyId(desc)){
			abnTlAbnormality.setAbnmDescription(desc);
			
		}
		if(UIUtils.isValidKeyId(remarks)){
			abnTlAbnormality.setAbnmRemarks(remarks);
		}
		String refDocID = request.getParameter("refDocID");
		CommonMessage.debugMsg("Input"+refDocID);
		String refDocType = request.getParameter("refDocType");
		CommonMessage.debugMsg("Input"+refDocType);
		if("VCC".equals(refDocType)){
			CommonMessage.debugMsg("VCC");
			abnTlAbnormality.setAbnmRefdocid(refDocID);
			abnTlAbnormality.setAbnmRefdoctype(refDocType);
		}
			
		CommonMessage.debugMsg("fddhfg test.............");
		
		if(UIUtils.isValidKeyId(abnValidateArr[0]))
			request.setAttribute("showCompDate",abnValidateArr[0]);
		if(UIUtils.isValidKeyId(abnValidateArr[1]))
			request.setAttribute("yyEnable",abnValidateArr[1]);
		if(UIUtils.isValidKeyId(abnValidateArr[2]))
			request.setAttribute("woEnable",abnValidateArr[2]);
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmAccecpatncerequired()))
			abnTlAbnormality.setAbnmAccecpatncerequired(abnTlAbnormality.getAbnmAccecpatncerequired().trim());
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmAccecptDate())&&Constants.pgPassNullDateTime.contains(abnTlAbnormality.getAbnmAccecptDate()))
			abnTlAbnormality.setAbnmAccecptDate("");
		CommonMessage.debugMsg("fddhfg tes t1233.............");
		String frmMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
		if(FormModes.view.equals(frmMode))
		{
			abnormalityBean.setFormActionMode(frmMode);
			abnormalityBean.setDisableForm(true);
		}
		httpSession.removeAttribute("abnormalityBean");
		httpSession.setAttribute("abnormalityBean", abnormalityBean);
		request.setAttribute("abnTlAbnormality", abnTlAbnormality);
		request.setAttribute("mode", frmMode);
		request.setAttribute("abnormalityBean", abnormalityBean);
		CommonMessage.debugMsg("fddhfg tes t1233456.............");
    }
  
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	String dispatchUrl = null;
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("action " + action);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession = request.getSession(false);
		
		
		if( user == null)
			return ;
		
		ComboFilter comboFilter = new ComboFilter();
		
		try {
			HttpSession s = request.getSession(true);
			
			abnService = (AbnormalityFormServiceImpl)UIUtils.getServiceObject(request,"AbnormalityFormServiceImpl");
		//	workOrderService = (WorkOrderServiceImpl)UIUtils.getServiceObject(request,"WorkOrderServiceImpl");
			commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
//			abnServiceApi = new AbnormalityServiceApi();
			CommonMessage.debugMsg("  abnormalityServices jwt token : "+s.getAttribute("tpmjwttoken") );
			abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
		
		
			if(action.equals("Abnormality_input.abnForm") ||(action.equals("HseAbnormality_input.abnForm")))
			{
				CommonMessage.debugMsg("test input action.............");
				//FormModes mode = (FormModes) httpSession.getAttribute("AbnormalityFormMode");
				String frmMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
				String tagClass = request.getParameter("tagClass");
				
				String selMachineId= request.getParameter("selMachineId");
				String clirefDocID = request.getParameter("refDocId");
				String refDocId = request.getParameter("refDocId");
				String refDoctype = request.getParameter("refDoctype");
				String AbnkeyId = request.getParameter("AbnId");
				String rowId = request.getParameter("rowId");
				
				CommonMessage.debugMsg("rowId :"+rowId);
	
                String elementid=CommonFunctions.getLoginElementId(request);
		      //  CommonMessage.debugMsg("login element id"+elementid);
		        String locationid=null;
		        if(elementid.length()>10){
		        locationid=elementid.substring(11,21);
		        //CommonMessage.debugMsg("locationid"+locationid);
		        }
				String frmType=null;
				FormModes mode = FormModes.create;
				CommonMessage.debugMsg("frmMode ="+frmMode + " mode " + mode);
				if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.view) )
					mode = FormModes.view;
				else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.modify) )
					mode = FormModes.modify;
				else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.complete) )
					mode = FormModes.completion;
				else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.removal) )
					mode = FormModes.removal;
				
				if(action.equals("Abnormality_input.abnForm") )
					frmType="JH";
				else if(action.equals("HseAbnormality_input.abnForm"))
					frmType="SHE";
			
				CommonMessage.debugMsg("frmMode ="+frmMode + " mode " + mode);
				String flid= request.getParameter("flid");
				if(UIUtils.isValidKeyId(flid))
					request.setAttribute("flid", flid);
				
				CommonMessage.debugMsg("clirefDocID............."+clirefDocID);
				if(UIUtils.isValidKeyId(selMachineId))
					request.setAttribute("selMachineId", selMachineId);
				if( UIUtils.isValidKeyId(clirefDocID) ){
					
					request.setAttribute("clirefDocID", clirefDocID); 			
					request.setAttribute("clirefDocType", "CLI");
				}
				if( UIUtils.isValidKeyId(refDocId) ){
					request.setAttribute("refDoctype", refDoctype); 			
					request.setAttribute("refDocId", refDocId);
				}
				
				String modeType = request.getParameter("modeType");
				initilizeInputMode(mode,frmType, request, httpSession);
				CommonMessage.debugMsg("fddhfg tes t1233jfgjf............."+mode+" modeType "+modeType);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnormalityForm.jsp");
				request.setAttribute("mode", mode);
				request.setAttribute("modeType", modeType);
				request.setAttribute("tagClass", tagClass);
				request.setAttribute("frmType", frmType);
				request.setAttribute("AbnkeyId", AbnkeyId);
				request.setAttribute("rowId", rowId);
				request.setAttribute("loginUser", user.getUsrm_ccno());
				request.setAttribute("locationid",locationid);
				rd.forward(request, response);
				
			}	
			
			
			else if (action.equals("AbnModify_input.abnForm") || action.equals("AbnCompletion_input.abnForm") || action.equals("AbnAcceptance_input.abnForm")
					|| action.equals("HseModify_input.abnForm") || action.equals("AbnView_input.abnForm") )
			{
				httpSession.setAttribute("AbnormalityFormMode", FormModes.modify);
				FormModes mode = FormModes.modify;
				if(action.equals("AbnCompletion_input.abnForm") )
					mode =  FormModes.complete;
				if(action.equals("HseModify_input.abnForm") )
					mode =  FormModes.view;
				
				String filterString = request.getParameter("filterString");
				String empKeyId=request.getParameter("empKeyId");
				CommonMessage.debugMsg("The empKeyId"+empKeyId);
				String loginUser = user.getUsrm_ccno();
				request.setAttribute("filterStr", filterString);
				request.setAttribute("loginUser", user.getUsrm_ccno());
				request.setAttribute("mode",mode);
				request.setAttribute("empKeyId", empKeyId);
				dispatchUrl="/pages/AbnormalityModificationForm.jsp"; 
			}
			
		//******************************************Individual Abnormality***********************************************//	
			else if(action.equals("AbnIndividualModify_input.abnForm")||action.equals("AbnIndividualView_input.abnForm"))
			{
				httpSession.setAttribute("AbnormalityFormMode", FormModes.modify);
				FormModes mode = FormModes.modify;
				String filterString = request.getParameter("filterString");
				String empKeyId=request.getParameter("empKeyId");
				CommonMessage.debugMsg("The empKeyId"+empKeyId);
				String loginUser = user.getUsrm_ccno();
			  //  CommonMessage.debugMsg("The loginUser"+loginUser);
				request.setAttribute("filterStr", filterString);
				request.setAttribute("loginUser", user.getUsrm_ccno());
				request.setAttribute("mode",mode);
				request.setAttribute("empKeyId", empKeyId);
				dispatchUrl="/pages/AbnormalityIndividualModificationForm.jsp"; 
			}
		
			else if(action.equals("AbnIndividualModify_getCol.abnForm")||action.equals("AbnIndividualView_getCol.abnForm"))
			{
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityFilter",true);
				response.setContentType("text/html");
				httpSession.setAttribute("AbnormalityFilter", commonFilter);
				
				String empKeyId=request.getParameter("empKeyId");
				CommonMessage.debugMsg("The empKeyId"+empKeyId);
				commonFilter.setEmpch(empKeyId);
				
				boolean status = false;
				String abnmStatus="modification";
				int condRow = 1;
				int headerRow=1;
				
				if(action.equals("AbnIndividualModify_getCol.abnForm")){
					 condRow = 0;
					 headerRow=1;
					 status = false;
					String Status= request.getParameter("Type");
					CommonMessage.debugMsg("Status");
					 if(action.equals("AbnCompletion_getCol.abnForm")){
						 commonFilter.setStatus("COMPLETED");
						 String Status1= request.getParameter("cboabnstatus");
						 CommonMessage.debugMsg(Status + " staus");
						 commonFilter.setAbnCatch(Status1);
					 }
					 else{
						 commonFilter.setStatus("PENDING");
					 }
				}
				else if(action.equals("AbnIndividualModify_getCol.abnForm"))
				{
					abnmStatus="modification";
					condRow = 0;
					 headerRow=1;
					 status = false;
					 commonFilter.setDocType("SHE");
				}
				else if(action.equals("AbnIndividualView_getCol.abnForm")){
					condRow = 0;
					 headerRow=1;
					 status = false;
					abnmStatus="view";
					String afeem=request.getParameter("AFEEM");
					CommonMessage.debugMsg(" afeem :: afeem :: 1234 :: 5678 :: "+afeem);
					commonFilter.setAbnImp(afeem);
					//commonFilter.setAllotedDtTo(user.getUsrm_ccno());'
				}
				
				String loginUser = user.getUsrm_ccno();
				commonFilter.setActionKeyId(loginUser);	
			 //   CommonMessage.debugMsg("The loginUser"+loginUser);
			    
				List<String[]> AbnDataList  = abnService.getIndividualModifyForm(commonFilter,abnmStatus,"JH");	
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setMultiSelect(status);
				gridColModel.setHeaderNum(1);
				List<String> formattorList =  new ArrayList<String>();
				String [] colHeader = AbnDataList.get(headerRow);			
				String [] colHeaderCond = AbnDataList.get(condRow);				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				CommonMessage.debugMsg("jsonObject..."+jsonObject);
				jsonObject.put("tableHeight", "74%%");
				jsonObject.put("tableWidth", "106%%");
				httpSession.removeAttribute("abnReportColModel");
				httpSession.setAttribute("abnReportColModel", jsonObject);
				out.println(jsonObject);
			}
			
			else if(action.equals("AbnIndividualModify_getData.abnForm")||action.equals("AbnIndividualView_getData.abnForm"))
			{
				try
				{
					PrintWriter out = response.getWriter();
					UIUtils.displayRequestParamsValue(request);
					CommonFilter commonFilter  = populateCommonFilter(request,"AbnormalityFilter",false);
					String abnmStatus="modification";
					int rowStart = 3;
					String empKeyId=request.getParameter("empKeyId");
					commonFilter.setEmpch(empKeyId);
					String loginUser = user.getUsrm_ccno();	
				    commonFilter.setActionKeyId(loginUser);	
				   // CommonMessage.debugMsg("The loginUser"+loginUser);
					if(action.equals("AbnIndividualModify_getData.abnForm")){
						abnmStatus="modification";
						rowStart = 2;
					
					}
					else if(action.equals("AbnIndividualView_getData.abnForm")){
						rowStart = 2;
						abnmStatus="view";
						//commonFilter.setAllotedDtTo(user.getUsrm_ccno());
						String Status= request.getParameter("cboabnstatus");
						CommonMessage.debugMsg(Status + " staus");
						//String Tag= request.getParameter("cmbAbnmTagclassid");
						//CommonMessage.debugMsg(Tag + " Tag");
						commonFilter.setStatus(Status);
     					//	commonFilter.set(Tag);
						String afeem=request.getParameter("AFEEM");
						CommonMessage.debugMsg(" afeem :: afeem :: 1234 :: 5678 :: "+afeem);
						commonFilter.setAbnImp(afeem);
					}
					commonFilter = FilterValues.getAbnRelatedFilters(request, commonFilter);
	  			 	List<String[]> AbnDataList  = abnService.getIndividualModifyForm(commonFilter,abnmStatus,"JH");
				 	JSONObject AbnormalityData = UIUtils.convertToJqGridTableObject(AbnDataList, request, rowStart, 0, commonFilter.getTotalRecordCnt());
	  			 	out.println(AbnormalityData);
	
			    }catch(Exception e)
				{
					CommonMessage.debugMsg("getdata Exception :"+e.getMessage());
					e.printStackTrace();
				}
			}
	
			else if(action.equals("AbnIndividualModify_getExcel.abnForm")||action.equals("AbnIndividualView_getExcel.abnForm")){
				CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("AbnormalityFilter");
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("abnReportColModel");
				tblJSONObj.put("title", "Individual Abnormality Report");
				String tmpFromRow = commonFilter.getFromRow();
				String viewGrid="AbnModify";
				commonFilter.setFromRow(null);
				String Status="P";
				if(action.equals("AbnIndividualModify_getExcel.abnForm"))
					viewGrid="AbnModify";
				if(action.equals("AbnIndividualView_getExcel.abnForm"))
					commonFilter.setStatus("view");
					tblJSONObj.put("title", "Abnormality Details");
				String format = ExcelUtils.getFormat(request);
				String loginUser = user.getUsrm_ccno();	
			    commonFilter.setActionKeyId(loginUser);	
				commonFilter.setAbnImp("EXCEL");
				commonFilter.setAbnCatch(Status);				
				Workbook wb = abnService.IndividualabnExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);	
				ExcelUtils.writeToResponse(response, wb, "Abnormality", format);
			}
			
			else if(action.equals("AbnCompletionVerifypopup_modify.abnForm")){
	    		String abnKeyid=request.getParameter("keyid");
	    		String abnRowid=request.getParameter("rowid");
	    		request.setAttribute("abnKeyid",abnKeyid);
	    		request.setAttribute("abnRowid",abnRowid);
	    		RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnormalityCompltnPopup.jsp");					
				rd.forward(request, response);
	    	}
			else if(action.equals("AbnCompletionVerifypopup_save.abnForm")){
	    		saveAbnormalityApprovedpopup(request,response);
	    	}
			else if(action.equals("AbnFill_modify.abnForm")){
	    		try {
				    PrintWriter out = response.getWriter();
					String keyid = request.getParameter("keyid");
					CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
					List<String []> condReclData  = abnService.FillabnpopData(keyid);
					out.print( JSONArray.fromCollection(condReclData));
	   			} catch (Exception e) {

				}
	    	}
			else if (action.equals("abnAllocation_input.abnForm")  )
			{
				request.setAttribute("loginUser", user.getUsrm_ccno());
				dispatchUrl="/pages/AbnormalityAllocation.jsp"; 
			}
			
			else if (action.equals("abnAllocation_getCol.abnForm") )
			{
				PrintWriter out = response.getWriter();
				//HttpSession httpSession = request.getSession(false);
				JSONObject repAbnjsonObject = new JSONObject();
				List<String[]> abnAllocation = null;
				
				CommonFilter commonFilter  = populateCommonFilter(request,"AbnormalityFilterAllocation",true);
				commonFilter.setIsGetCol("Y");
				abnAllocation = abnService.getAbnAllocation(commonFilter);
				//repAbnjsonObject = getTableModelAbnAllocation(repeatedAnbn);
				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				//String[] formatterval  = {"dteTargetDate6"};
				String[] formatterval  = {"dteTargetDate#8","cmbAbnmTrade#9","cmbAbnmResponse#11"};
				jqGridTableModel.setFormatterIndex(formatterval);
				
				gridColModel.setHeaderNum(1);
				/*gridColModel.setFormatter("formattorTargetDate");
				gridColModel.setFormattorFromCol("4");
				gridColModel.setFormattorToCol("4");*/
				
				
				
				String [] colHeader = abnAllocation.get(1);			
				String [] colHeaderCond = abnAllocation.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				colModel.set("tableWidth", "106%%");
				colModel.set("tableHeight", "82%%");
				colModel.set("multiSelect", true);
				httpSession.removeAttribute("AbnAllocationColModel");
				 httpSession.setAttribute("AbnAllocationColModel", colModel);
				 out.println(colModel);
			}
			
			else if (action.equals("abnAllocation_getData.abnForm") )
			{
				PrintWriter out = response.getWriter();
				List<String[]> repeatedAnbn = null;
				
				CommonFilter commonFilter  = populateCommonFilter(request,"AbnormalityFilterAllocation",false);
				commonFilter.setIsGetCol("N");
				repeatedAnbn = abnService.getAbnAllocation(commonFilter);
					
				JSONObject repeatedAnbnData = UIUtils.convertToJqGridTableObject(repeatedAnbn,request,2,0,commonFilter.getTotalRecordCnt()); 
  			 	out.println(repeatedAnbnData);  			 	
  			 	
  			 	httpSession.removeAttribute("AbnormalityFilter");
  			 	httpSession.setAttribute("AbnormalityFilterAllocation", commonFilter);

			}
			else if (action.equals("abnAllocation_getExcel.abnForm") )
			{
				CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityFilterAllocation",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("AbnAllocationColModel");
				String keyId = request.getParameter("KeyId");
				tblJSONObj.put("title", "Abnormality Allocation");
				String format = ExcelUtils.getFormat(request);
				
					
				Workbook wb = abnService.getAbnAllocationExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "Abnormality Allocation", format);
				
				
				
				
			}
			else if (action.equals("repeatedAbn_input.abnForm") )
			{
				dispatchUrl="/pages/RepeatedAbn.jsp"; 
			}
			else if(action.equals("repeatedAbn_getCol.abnForm"))
			{
				PrintWriter out = response.getWriter();
				//HttpSession httpSession = request.getSession(false);
				JSONObject repAbnjsonObject = new JSONObject();
				List<String[]> repeatedAnbn = null;
				
				CommonFilter commonFilter  = populateCommonFilter(request,"AbnormalityFilter",true);
				repeatedAnbn = abnService.getRepeatedAbn(commonFilter);
				repAbnjsonObject = getTableModelRepeatedAbn(repeatedAnbn);
				 httpSession.removeAttribute("ActionPlanColModel");
				 httpSession.setAttribute("ActionPlanColModel", repAbnjsonObject);
				 out.println(repAbnjsonObject);
			}
			else if(action.equals("repeatedAbn_getData.abnForm"))
			{
				PrintWriter out = response.getWriter();
				List<String[]> repeatedAnbn = null;
				
				CommonFilter commonFilter  = populateCommonFilter(request,"AbnormalityFilter",true);
				repeatedAnbn = abnService.getRepeatedAbn(commonFilter);

				JSONObject repeatedAnbnData = UIUtils.convertToJqGridTableObject(repeatedAnbn,request,1,0,commonFilter.getTotalRecordCnt()); 
  			 	out.println(repeatedAnbnData);  			 	
  			 	
  			 	httpSession.removeAttribute("AbnormalityFilter");
  			 	httpSession.setAttribute("AbnormalityFilter", commonFilter);

			}
			
			else if (action.equals("AbnBulkTagRemove_input.abnForm"))
			{
				httpSession.setAttribute("AbnormalityFormMode", FormModes.removal);
				request.setAttribute("mode", FormModes.removal);
				String filterString = request.getParameter("filterString");
				request.setAttribute("filterStr", filterString);
				request.setAttribute("loginUser", user.getUsrm_ccno());
				
				dispatchUrl="/pages/AbnormalityBulkTagRemoval.jsp"; 
				
			}
			
			
			
			else if (action.equals("AbnTagRemove_input.abnForm") || action.equals("HseTagRemove_input.abnForm"))
			{
				httpSession.setAttribute("AbnormalityFormMode", FormModes.removal);
				request.setAttribute("mode", FormModes.removal);
				String filterString = request.getParameter("filterString");
				request.setAttribute("filterStr", filterString);
				dispatchUrl="/pages/AbnormalityModificationForm.jsp"; 
				
			}
			else if(action.equals("Abnormality_view.abnForm")  || (action.equals("AbnHTAView_input.abnForm"))
					|| (action.equals("AbnSOCView_input.abnForm")) || (action.equals("AbnUNSView_input.abnForm")))
			{	
				httpSession.setAttribute("AbnormalityFormMode", FormModes.view);
				request.setAttribute("mode", FormModes.view);
				String filterString = request.getParameter("filterString");
				request.setAttribute("filterStr", filterString);
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnormalityModificationForm.jsp");
				
				if (action.equals("HSE_AbnRptGen_input.abnGenRpt")) 
					request.setAttribute("AbnType", "SHE");
				else if(action.equals("AbnHTAView_input.abnForm"))
					request.setAttribute("AbnType", "HTA");
				else if(action.equals("AbnSOCView_input.abnForm"))
					request.setAttribute("AbnType", "SOC");
				else if(action.equals("AbnUNSView_input.abnForm"))
					request.setAttribute("AbnType", "UNS");
				
				rd.forward(request, response);
				
			}
			else if(action.equals("AbnormalityDetails_view.abnForm"))
			{	
				dispatchUrl="/pages/AbnormalityDetails.jsp";
			}
			
			/*else if (action.equals("HseTagRemove_input.abnForm"))
			{
				httpSession.setAttribute("AbnormalityFormMode", FormModes.removal);
				request.setAttribute("mode", FormModes.removal);
				String filterString = request.getParameter("filterString");
				request.setAttribute("filterStr", filterString);
				dispatchUrl="/pages/HseAbnModificationForm.jsp"; 
				
			}*/
			else if(action.equals("HseAbnormality_view.abnForm"))
			{	
				httpSession.setAttribute("AbnormalityFormMode", FormModes.view);
				request.setAttribute("mode", FormModes.view);
				String filterString = request.getParameter("filterString");
				request.setAttribute("filterStr", filterString);
				dispatchUrl="/pages/HseAbnModificationForm.jsp";
			}
			
			
			else if( action.equals("AbnormalityDetails_getCol.abnForm") )
			{
				String keyId = request.getParameter("KeyId");
				
				PrintWriter out = response.getWriter();			 
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityGeneralReport", "AbnDetails"));
			}
			else if( action.equals("AbnormalityDetails_getData.abnForm") )
			{
				
				
				try
				{
					String keyId = request.getParameter("KeyId");
					String columnId = request.getParameter("column");
					String fromDate = request.getParameter("dtFromDate");
					String toDate = request.getParameter("dtToDate");
					String mchId = request.getParameter("mchId");
					UIUtils.displayRequestParamsValue(request);
					CommonFilter commonFilter = populateCommonFilter(request,"abnTlAbnormalityDetails",true);
					
					if(mchId != null && mchId != "")
						commonFilter.setMachineId(mchId);
					
					/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	  }*/
					
					List<String[]> equipmentQueryList  = abnService.getAllAbnormalityDetails(commonFilter,keyId,columnId);
					commonFilter.setFromDate(fromDate);
					commonFilter.setToDate(toDate);
					//CommonMessage.debugMsg("equipmentQueryList " + equipmentQueryList.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(equipmentQueryList,request,0,1,commonFilter.getTotalRecordCnt()); 
	  			 	out.println(equipmentQueryData);
	  			 	
	  			 	commonFilter.setViewClick('N');
	  			 	
	  			 	httpSession.removeAttribute("abnTlAbnormalityDetails");
	  			 	httpSession.setAttribute("abnTlAbnormalityDetails", commonFilter);
	
			    }catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			    
			    
			
				/*String keyId = request.getParameter("KeyId");
				String colId = request.getParameter("column"); 
				PrintWriter out = response.getWriter();
				  GridParams gridParams =(GridParams) httpSession.getAttribute(keyId+"gridParams");
	    		  if( gridParams == null )
	    			  gridParams = new GridParams(); 
	    		  
	    		  FilterValues.populateGridParams(request,gridParams );
				  
	    		  String count  = abnService.getAbnormalityDetailsCount(keyId,gridParams);
	    		  
	    		  httpSession.removeAttribute("AbnDetailsCount");
	    		  httpSession.setAttribute("AbnDetailsCount" ,count);
	    		  
	    			List<String []> abnDetailsList  = abnService.getAbnormalityDetails(keyId,gridParams);
	    			{
	    				JSONObject abnDetailsListData = UIUtils.convertListToJqGridTableObject(abnDetailsList, request,  0, 0,Integer.parseInt(count));
		  			  	out.println(abnDetailsListData);
					}
	    			
	    		  httpSession.removeAttribute(keyId+"gridParams");
	    		  httpSession.setAttribute(keyId+"gridParams" ,gridParams);
				 
				/*try
				{	
					String keyId = request.getParameter("KeyId");
					PrintWriter out = response.getWriter();
					List<String []> abnDetailsList  = abnService.getAbnormalityDetails(keyId);
					
					if(abnDetailsList!=null && abnDetailsList.size()>0)
					{
		  			 	JSONObject abnDetailsListData = UIUtils.convertToJqGridTableObject(abnDetailsList,request,0,0);	  			
		  			 	out.println(abnDetailsListData);
					}
				}catch(Exception e)
				{
					CommonMessage.debugMsg("No Datas Found");
				}*/
			}
			
			else if( action.equals("AbnormalityDetails_getExcel.abnForm")){
				
				String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityGeneralReport", "AbnDetails");
				JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				String keyId = request.getParameter("KeyId");
				tblJSONObj.put("title", "Abnormality Details");
				String format = ExcelUtils.getFormat(request);
				String count = (String) httpSession.getAttribute("AbnDetailsCount");
				GridParams gridParams =(GridParams) httpSession.getAttribute(keyId+"gridParams");
				Workbook wb = abnService.AbnDetailsExportExcel(tblJSONObj,format,gridParams,keyId,count);
				ExcelUtils.writeToResponse(response, wb, "AbnormalityDetails", format);
				
			}
			else if(action.equals("functionalLoc.abnForm")){
				
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				//functLocFieldNameBean.setFactory("cmbFactory");
				functLocFieldNameBean.setSection("cmbAbnmSectionid");
				functLocFieldNameBean.setCell("cmbAbnmCellid");
				functLocFieldNameBean.setMachine("cmbAbnmEquipmentid");
				functLocFieldNameBean.setFunctionalLocId("cmbAbnmFlid");
				
				
				AbnormalityBean abnormalityBean =(AbnormalityBean)httpSession.getAttribute("abnormalityBean"); 
				
				if(abnormalityBean.getPillarName().equalsIgnoreCase("JH"))
				{	
				//	functLocFieldNameBean.setFactMandatory(false);
//					functLocFieldNameBean.setSectMandatory(true);
					functLocFieldNameBean.setCellMandatory(true);
					functLocFieldNameBean.setMachMandatory(false);
				}
				else if(abnormalityBean.getPillarName().equalsIgnoreCase("SHE"))
				{
					//functLocFieldNameBean.setFactMandatory(true);
//					functLocFieldNameBean.setSectMandatory(false);
					functLocFieldNameBean.setCellMandatory(false);
					functLocFieldNameBean.setMachMandatory(false);
				}
				
				FormModes formModes = (FormModes)httpSession.getAttribute("AbnormalityFormMode");
				
				if( formModes == FormModes.completion || formModes == FormModes.removal)
					formModes = FormModes.view;
				
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
				
			}
			
			
			else if(action.equals("Combo_TagNo.abnForm"))
			{
				try 
				{
					comboFilter=UIUtils.fillComboFilter(request);
					String tagno = request.getParameter("tagno");
					List<ComboBox>  TagNo = abnService.getdepartmentcombo(tagno,comboFilter);
					UIUtils.writeComboBox(response, TagNo,comboFilter);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			else if(action.equals("Combo_Afeem.abnForm"))
			{
				try 
				{
					comboFilter=UIUtils.fillComboFilter(request);
					
					List<ComboBox>  afeem = abnService.getAfeemcombo(comboFilter);
					UIUtils.writeComboBox(response, afeem,comboFilter);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			else if(action.equals("Combo_Status.abnForm")){
				try 
				{
					 response.setContentType("text/html;charset=UTF-8");
					 response.setContentType("json");
					 CommonMessage.debugMsg("inside combo_Status general Maintainence");
				 	 PrintWriter out = response.getWriter();
				     out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "selectStatus"));
					 out.close();
	
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			else if(action.equals("Combo_Equipmentid.abnForm"))
			{
				try 
				{
					//ComboFilter comboFilter = new ComboFilter();
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  Equipment = abnService.getEquipmentcombo("",comboFilter);
					UIUtils.writeComboBox(response, Equipment,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			
			else if(action.equals("Combo_IssueNo.abnForm"))
			{
				try 
				{
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  IssueNo = abnService.getIssueNocombo("",comboFilter);
					UIUtils.writeComboBox(response, IssueNo,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}	
				
			else if(action.equals("Combo_DetectedBy.abnForm"))
			{
				try 
				{
					ComboFilter detecdComboFilter = new ComboFilter();
					detecdComboFilter=UIUtils.fillComboFilter(request);
					
					String loginLocnId = (String)httpSession.getAttribute("loginLocnId");
					comboFilter=UIUtils.fillComboFilter(request);
					comboFilter.setCondSql(" AND EMPM_LOCATION ='"+ loginLocnId + "' ");
					
	                List<ComboBox>  DetectedBy = abnService.getDetectedBycombo(detecdComboFilter);
					//UIUtils.writeComboBox(response, DetectedBy);
	                UIUtils.writeComboBox(response, DetectedBy ,detecdComboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}	
			else if(action.equals("Combo_Type.abnForm"))
			{
				try 
				{
					comboFilter=UIUtils.fillComboFilter(request);
					String abtmType= request.getParameter("abtmType");
					String abnType = request.getParameter("abnType");
					List<ComboBox>  type = abnService.getTypecombo(abtmType,abnType,comboFilter);
				    UIUtils.writeComboBox(response, type ,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}	
			else if (action.equals("combo_Assembly.abnForm"))
			{
				try 
				{
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  abnAssembly = abnService.getAssemblycombo("",comboFilter);
					UIUtils.writeComboBox(response, abnAssembly,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
				
			}
			else if( action.equals("Combo_SubType.abnForm"))
			{
				try 
				{
					//ComboFilter htatypeComboFilter = new ComboFilter();
					ComboFilter htatypeComboFilter=UIUtils.fillComboFilter(request);
					PrintWriter out = response.getWriter();
					String abtmKeyid=request.getParameter("abtmKeyid");
					String formName = request.getParameter("frmType");
					//abnService.getSubTypecombo(abtmKeyid);
					List<ComboBox>  abnSubType = abnService.getSubTypecombo(abtmKeyid,formName,htatypeComboFilter);
					UIUtils.writeComboBox(response, abnSubType ,htatypeComboFilter);
					//UIUtils.writeComboBox(response, abnSubType);
				/*	List<String[]>  abnSubType = abnService.getSubTypecombo(abtmKeyid);
					 JSONArray jsonObject = JSONArray.fromObject(abnSubType);
				     CommonMessage.debugMsg(jsonObject);
			         out.print(jsonObject);
			         out.flush();
			         out.close();
					//UIUtils.writeComboBox(response, abnSubType);*/
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				
			}
			else if(action.equals("Combo_TagClass.abnForm"))
			{
				try 
				{
					String frmType = request.getParameter("frmType");
					String condSql="";
					comboFilter=UIUtils.fillComboFilter(request);
						
					if(frmType != null && frmType.equals("SHE"))
						condSql="AND  TAGM_CODE = 'GREEN'";
					else
						condSql="AND  TAGM_CODE <> 'GREEN'";
					
					List<ComboBox>  TagClass = abnService.getTagClasscombo(condSql,comboFilter);
				    UIUtils.writeComboBox(response, TagClass ,comboFilter);
				    
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
		/*	else if(action.equals("combo_CostCenter.commonFilter"))
			{
				try 
				{
	
					List<ComboBox>  CostCenter = abnService.getCostCentercombo("");
					UIUtils.writeComboBox(response, CostCenter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}*/
			
			else if(action.equals("Combo_Category.abnForm"))
			{
				try 
				{
					comboFilter=UIUtils.fillComboFilter(request);
					String formName= request.getParameter("frmType");
					CommonMessage.debugMsg("frmType.."+formName);
					List<ComboBox>  Category = abnService.getCategorycombo(formName,comboFilter);
					//UIUtils.writeComboBox(response, Category);
					UIUtils.writeComboBox(response, Category ,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			else if(action.equals("Combo_Trade.abnForm"))
			{
				try 
				{
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  Trade = abnService.getTradecombo(comboFilter);
					//UIUtils.writeComboBox(response, Trade);
					UIUtils.writeComboBox(response, Trade ,comboFilter);
				} 
				catch (Exception e) 
				{				
					e.printStackTrace();
				}
			}
			
			else if(action.equals("Combo_CompletedBy.abnForm"))
			{
				try 
				{
					String loginLocnId = (String)httpSession.getAttribute("loginLocnId");
					comboFilter=UIUtils.fillComboFilter(request);
					comboFilter.setCondSql(" AND EMPM_LOCATION ='"+ loginLocnId + "' ");
					String cellid = request.getParameter("cellId");
					String tradeId = request.getParameter("trade");
					
					List<ComboBox>  completedBy = abnService.getcompletedBycombo(comboFilter);
					//UIUtils.writeComboBox(response, completedBy);
					UIUtils.writeComboBox(response, completedBy ,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			else if(action.equals("Combo_Impact.abnForm"))
			{
				try 
				{
					comboFilter=UIUtils.fillComboFilter(request);
					String formName= request.getParameter("frmType");
					List<ComboBox>  impact = abnService.getImpactcombo(formName,comboFilter);
					//UIUtils.writeComboBox(response, impact);
					UIUtils.writeComboBox(response, impact ,comboFilter);
					
					
					
					
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			/**    Main Type   **/
	
			else if(action.equals("Combo_MainType.abnForm"))
			{
				try 
				{
					comboFilter=UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("Inside Servlete  MAin TYOEEEEEEE ");
					List<ComboBox>  mainType = abnService.getMainTypecombo("",comboFilter);
					UIUtils.writeComboBox(response, mainType,comboFilter);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			else if(action.equals("getAbnType.abnForm"))
			{
				PrintWriter out = response.getWriter();
				String abtmKeyid= request.getParameter("abtmKeyid");
				String abnType = abnService.getAbnTypeFlag(abtmKeyid);
					
	
				JSONObject returnData= new JSONObject();
			    returnData.put("abnType", abnType);				
				out.print(returnData.toString());
			}
			
			//*********************************************************//
		/*	else if(action.equals("getAbnSubType.abnForm")){
				ComboFilter cmbFilter=UIUtils.fillComboFilter(request);
				//PrintWriter out=response.getWriter();
				String abtmKeyid=request.getParameter("abtmKeyid");
				CommonMessage.debugMsg("AbtmKeyid:::"+abtmKeyid);
				String rowId=request.getParameter("rowid");
				CommonMessage.debugMsg("The RowId"+rowId);
				List<ComboBox> getsubType=abnService.getSubType(abtmKeyid,cmbFilter);
				UIUtils.writeComboBox(response, getsubType ,cmbFilter);
				
			}
			*/
			else if( action.equals("getAbnSubType.abnForm")){				
				try{
					    String abtmkeyid =request.getParameter("abtmKeyid");
					    String rowId =request.getParameter("rowid");
					    String Typeid="";				
					    Typeid =abnService.getSubType(abtmkeyid);
					    CommonMessage.debugMsg("The Type Id:"+Typeid);
					    CommonMessage.debugMsg("The abtmkeyid:"+abtmkeyid);
					    request.setAttribute("Typeid",Typeid);
						JSONObject successData = new JSONObject();
						successData.put("Typeid",Typeid);
						successData.put("abtmkeyid",abtmkeyid);	
						successData.put("rowId",rowId);	
						PrintWriter out= response.getWriter();
						out.print(successData.toString());
					}
				catch (Exception e) {				
					e.printStackTrace();
				}
			}

			
			else if(action.equals("getTrade.abnForm"))
			{
				PrintWriter out = response.getWriter();
				String abnmRespons = request.getParameter("abnmRespons");
				String abnTrade = abnService.getAbnTradeValues(abnmRespons);
				JSONObject returnData= new JSONObject();
			    returnData.put("abnTrade", abnTrade);				
				out.print(returnData.toString());
			}
			else if(action.equals("Datebox_FillTargetDate.abnForm"))
			{
				PrintWriter out =response.getWriter();
				Calendar now = Calendar.getInstance();
				String tagClass = request.getParameter("tagClass");
				String targetDate = request.getParameter("targetDate");
				if(targetDate==null||targetDate.equals("undefined"))
					targetDate=CommonFunctions.getDate();
		
	
	
			    try{
			    	int days=0;
			    	String day="0";
			    	List<String> duration=abnService.getTgtDateFromConfig();
			    	
			    	if(duration!=null && duration.size() > 0)
			    	{
			    		if(tagClass.equals("RED-RED"))
			    			day=duration.get(0);
				    	else if(tagClass.equals("WHITE-WHITE")||tagClass.equals("undefined"))
				    		day=duration.get(1);
			    		
				    	days=Integer.parseInt(day);
				    	now.setTime(new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH).parse(targetDate)); 
				    	now.add(Calendar.DATE, days);
				    }
				    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
					JSONObject returnData= new JSONObject();
				    returnData.put("date", sdf.format(now.getTime()));
				    
					out.print(returnData.toString());
				    } catch (ParseException e) { e.printStackTrace(); }   
			}
			
			else if(action.equals("DateboxMultiple_FillTargetDate.abnForm"))
			{
				PrintWriter out =response.getWriter();
				Calendar now = Calendar.getInstance();
				String tagClass = request.getParameter("tagClass");
				String targetDate = request.getParameter("targetDate");
				String rowId=request.getParameter("rowId");
				if(targetDate==null||targetDate.equals("undefined"))
					targetDate=CommonFunctions.getDate();
		
	
	
			    try{
			    	int days=0;
			    	String day="0";
			    	List<String> duration=abnService.getTgtDateFromConfig();
			    	
			    	if(duration!=null && duration.size() > 0)
			    	{
			    		if(tagClass.equals("RED-RED"))
			    			day=duration.get(0);
				    	else if(tagClass.equals("WHITE-WHITE")||tagClass.equals("undefined"))
				    		day=duration.get(1);
			    		
				    	days=Integer.parseInt(day);
				    	now.setTime(new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH).parse(targetDate)); 
				    	now.add(Calendar.DATE, days);
				    }
				    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
					JSONObject returnData= new JSONObject();
				    returnData.put("date", sdf.format(now.getTime()));
				    returnData.put("rowId",rowId);
					out.print(returnData.toString());
				    } catch (ParseException e) { e.printStackTrace(); }
			    
			}	
			
			
			
			
			else if( action.equals("Abnormality_delete.abnForm")||(action.equals("HseAbnormality_delete.abnForm")))
			{	
				deleteAbn(request,response);
		    }
			
			else if( action.equals("Abnormality_save.abnForm")||action.equals("HseAbnormality_save.abnForm"))
			{	
				saveAbn(request,response);
		    }
			
			//*******************************Multiple Abnormality Save************************************//
			else if(action.equals("MultipleAbnormality_save.abnForm")){
				saveMultipleAbn(request,response);
			}	
			
			else if( action.equals("AbnBulkTagRemove_save.abnForm"))
			{
				saveAbnBulkRemoveTag(request,response);
			}
			
			else if( action.equals("abnAllocation_save.abnForm"))
			{
				saveAbnAllocation(request,response);
			}
			else if( action.equals("Abnormality_yy.abnForm"))
			{
				saveAbn(request,response);
			}			
			else if( action.equals("filterXmlAbnModify_input.abnForm")||action.equals("filterXmlAbnTagRemove_input.abnForm")||
				(action.equals("filterXmlAbnSOCView_input.abnForm")) || action.equals("AbnBulkTagRemove_input.abnForm")
					|| (action.equals("filterXmlAbnHTAView_input.abnForm")) || (action.equals("filterXmlAbnUNSView_input.abnForm")))
	
			{
				response.setContentType("xml"); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/AbnormalityForm.xml") ;
			}
			else if(action.equals("filterXmlHseModify_input.abnForm")|| action.equals("filterXmlHseTagRemove_input.abnForm")||
					action.equals("filterXmlHseAbnormality_view.abnForm"))
	
			{
				response.setContentType("xml"); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/HSEAbnormalityForm.xml") ;
			}
			
			else if(action.equals("AbnModify_getCol.abnForm")||(action.equals("AbnTagRemove_getCol.abnForm"))
					|| (action.equals("AbnHTAView_getCol.abnForm")) || (action.equals("AbnSOCView_getCol.abnForm"))
					|| (action.equals("AbnUNSView_getCol.abnForm")) || action.equals("AbnBulkTagRemove_getCol.abnForm")
					|| action.equals("AbnCompletion_getCol.abnForm") || action.equals("AbnAcceptance_getCol.abnForm")
					|| action.equals("HseModify_getCol.abnForm") || action.equals("HseTagRemove_getCol.abnForm") ||  action.equals("AbnView_getCol.abnForm"))
			{
				PrintWriter out = response.getWriter();
				
				
				CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityFilter",true);
				
				//CommonFilter commonFilter = new CommonFilter();
				//commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);			
				//commonFilter = FilterValues.getAbnRelatedFilters(request, commonFilter);
				response.setContentType("text/html");
				httpSession.setAttribute("AbnormalityFilter", commonFilter);
				
				String empKeyId=request.getParameter("empKeyId");
				CommonMessage.debugMsg("The empKeyId"+empKeyId);
				commonFilter.setEmpch(empKeyId);
				
				boolean status = false;
				String abnmStatus="modification";
				int condRow = 1;
				int headerRow=1;
				
				if(action.equals("AbnModify_getCol.abnForm") || action.equals("AbnCompletion_getCol.abnForm") || action.equals("AbnAcceptance_getCol.abnForm")){
				
					
					//abnmStatus="complete";
					condRow = 0;
					 headerRow=1;
					 status = false;
					String Status= request.getParameter("Type");
					CommonMessage.debugMsg("Status");
					 //commonFilter.setDocType("JH");
					 if(action.equals("AbnCompletion_getCol.abnForm")){
						 commonFilter.setStatus("COMPLETED");
						 //abnmStatus="complete";
						 String Status1= request.getParameter("cboabnstatus");
							CommonMessage.debugMsg(Status + " staus");
							//String Tag= request.getParameter("cmbAbnmTagclassid");
							//CommonMessage.debugMsg(Tag + " Tag");
							commonFilter.setAbnCatch(Status1);
					 }
					 else if(action.equals("AbnAcceptance_getCol.abnForm"))
					 {
						 abnmStatus="accecpt";
					 }
					 else{
						 commonFilter.setStatus("PENDING");
					 }
				}
				else if(action.equals("HseModify_getCol.abnForm") || action.equals("HseTagRemove_getCol.abnForm")||action.equals("AbnModify_getCol.abnForm"))
				{
					abnmStatus="modification";
					condRow = 0;
					 headerRow=1;
					 status = false;
					 commonFilter.setDocType("SHE");
				}
				else if(action.equals("AbnTagRemove_getCol.abnForm")){
					condRow = 1;
					headerRow=0;
					abnmStatus="removal";
					status = false;
					//commonFilter.setDocType("JH");
				}
				else if(action.equals("AbnUNSView_getCol.abnForm")){
					
				}
				else if(action.equals("AbnBulkTagRemove_getCol.abnForm")){
					abnmStatus="bulkRemoval";
					condRow = 0;
					 headerRow=2;
					 status = true;
				}
				else if(action.equals("AbnView_getCol.abnForm")){
					condRow = 0;
					 headerRow=1;
					 status = false;
					abnmStatus="view";
					String afeem=request.getParameter("AFEEM");
					CommonMessage.debugMsg(" afeem :: afeem :: 1234 :: 5678 :: "+afeem);
					commonFilter.setAbnImp(afeem);
					//commonFilter.setAllotedDtTo(user.getUsrm_ccno());'
				}	
				
				String loginUser = user.getUsrm_ccno();	
				if(action.equals("AbnAcceptance_getCol.abnForm"))
					commonFilter.setAcceptenceRequired("Y");
				
				commonFilter.setIsGetCol("Y");
				//commonFilter.get
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityGeneralReport",viewGrid));
				List<String[]> AbnDataList  = abnService.getAllModifyForm(commonFilter,abnmStatus,"JH");
				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setMultiSelect(status);
				
				gridColModel.setHeaderNum(1);
				
				/*gridColModel.setFormatter("formattorAttach");
				gridColModel.setFormattorFromCol("1");
				gridColModel.setFormattorToCol("1");*/
				
				List<String> formattorList =  new ArrayList<String>();
				if(action.equals("AbnCompletion_getCol.abnForm")) {
					formattorList.add("chkFormatter");
					List<String> formattorFromList =  new ArrayList<String>();
					List<String> formattorToList =  new ArrayList<String>();
					formattorFromList.add("1");
					formattorToList.add("1");
				    gridColModel.setMultiformatter(formattorList);
					gridColModel.setMultiformattorFromCol(formattorFromList);
					gridColModel.setMultiformattorToCol(formattorToList);
				}
				 
				String [] colHeader = AbnDataList.get(headerRow);			
				String [] colHeaderCond = AbnDataList.get(condRow);
				
				//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				CommonMessage.debugMsg("jsonObject..."+jsonObject);
				jsonObject.put("tableHeight", "74%%");
				jsonObject.put("tableWidth", "106%%");
				jsonObject.put("paginate", true);// madhan
				httpSession.removeAttribute("abnReportColModel");
				httpSession.setAttribute("abnReportColModel", jsonObject);
				
				out.println(jsonObject);
			}
			
			else if(action.equals("AbnModify_getData.abnForm")||action.equals("AbnTagRemove_getData.abnForm")
					|| action.equals("AbnHTAView_getData.abnForm") || action.equals("AbnSOCView_getData.abnForm")
					|| action.equals("AbnUNSView_getData.abnForm") || action.equals("AbnBulkTagRemove_getData.abnForm")
					|| action.equals("AbnCompletion_getData.abnForm") || action.equals("AbnAcceptance_getData.abnForm")
					|| action.equals("HseModify_getData.abnForm")|| action.equals("HseTagRemove_getData.abnForm") || action.equals("AbnView_getData.abnForm"))
			{
				try
				{
					PrintWriter out = response.getWriter();
					UIUtils.displayRequestParamsValue(request);
					CommonFilter commonFilter  = populateCommonFilter(request,"AbnormalityFilter",false);
					String abnmStatus="modification";
					int rowStart = 2;
					String empKeyId=request.getParameter("empKeyId");
					commonFilter.setEmpch(empKeyId);
					
					if(action.equals("AbnAcceptance_getData.abnForm") || action.equals("AbnCompletion_getData.abnForm"))
					{
						rowStart = 2;
						abnmStatus="complete";
						if(action.equals("AbnAcceptance_getData.abnForm")){
							commonFilter.setAllotedFrom(user.getUsrm_ccno());
							abnmStatus="accecpt";
						}
						else if(action.equals("AbnCompletion_getData.abnForm"))
							{
							
							commonFilter.setAllotedDtTo(user.getUsrm_ccno());
							String Status= request.getParameter("cboabnstatus");
							CommonMessage.debugMsg(Status + " staus");
							//String Tag= request.getParameter("cmbAbnmTagclassid");
							//CommonMessage.debugMsg(Tag + " Tag");
							commonFilter.setAbnCatch(Status);
							
							}
					}
					
					if(action.equals("AbnModify_getData.abnForm") || action.equals("HseModify_getData.abnForm")){
						abnmStatus="modification";
						rowStart = 2;
						if(action.equals("HseModify_getData.abnForm"))
							rowStart = 2;
						
					}
					else if(action.equals("AbnTagRemove_getData.abnForm") || action.equals("HseTagRemove_getData.abnForm") ){
						abnmStatus="removal";
						commonFilter.setAllotedDtTo(user.getUsrm_ccno());

					}
					else if(action.equals("AbnBulkTagRemove_getData.abnForm")){
						abnmStatus="bulkRemoval";
						rowStart = 2;
						commonFilter.setAllotedDtTo(user.getUsrm_ccno());
					}
					else if(action.equals("AbnView_getData.abnForm")){
						rowStart = 2;
						abnmStatus="view";
						//commonFilter.setAllotedDtTo(user.getUsrm_ccno());
						String Status= request.getParameter("cboabnstatus");
						CommonMessage.debugMsg(Status + " staus");
						//String Tag= request.getParameter("cmbAbnmTagclassid");
						//CommonMessage.debugMsg(Tag + " Tag");
						commonFilter.setStatus(Status);
     					//	commonFilter.set(Tag);
						String afeem=request.getParameter("AFEEM");
						CommonMessage.debugMsg(" afeem :: afeem :: 1234 :: 5678 :: "+afeem);
						commonFilter.setAbnImp(afeem);
					} 
					commonFilter = FilterValues.getAbnRelatedFilters(request, commonFilter);
					commonFilter.setIsGetCol("N");
	  			 	List<String[]> AbnDataList  = abnService.getAllModifyForm(commonFilter,abnmStatus,"JH");
				 	JSONObject AbnormalityData = UIUtils.convertToJqGridTableObject(AbnDataList, request, rowStart, 0, commonFilter.getTotalRecordCnt());
	  				
	  			 	out.println(AbnormalityData);
	
			    }catch(Exception e)
				{
					CommonMessage.debugMsg("getdata Exception :"+e.getMessage());
					e.printStackTrace();
				}
			}
			
			else if(action.equals("AbnModify_getExcel.abnForm")||(action.equals("AbnTagRemove_getExcel.abnForm"))
					|| (action.equals("AbnHTAView_getExcel.abnForm")) || (action.equals("AbnSOCView_getExcel.abnForm"))
					|| (action.equals("AbnUNSView_getExcel.abnForm")) || action.equals("AbnBulkTagRemove_getCol.abnForm")
					|| action.equals("AbnCompletion_getExcel.abnForm") || action.equals("AbnAcceptance_getExcel.abnForm")
					 || action.equals("AbnView_getExcel.abnForm"))
			{
			
				CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("AbnormalityFilter");
				
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("abnReportColModel");
				tblJSONObj.put("title", "Abnormality Report");
				
				String tmpFromRow = commonFilter.getFromRow();
				String viewGrid="AbnModify";
				
				
				commonFilter.setFromRow(null);
				if(action.equals("AbnModify_getExcel.abnForm"))
					viewGrid="AbnModify";
				else if(action.equals("AbnTagRemove_getExcel.abnForm"))
					viewGrid="AbnRemovalTag";
				
					
				
				
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityGeneralReport",viewGrid);
				
				//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				tblJSONObj.put("title", "Abnormality Details");
				String format = ExcelUtils.getFormat(request);
				
				if(action.equals("AbnCompletion_getExcel.abnForm"))
					
					commonFilter.setStatus("Status");
				String Status= request.getParameter("cboabnstatus");
				CommonMessage.debugMsg(Status + " staus");
				//String Tag= request.getParameter("cmbAbnmTagclassid");
				//CommonMessage.debugMsg(Tag + " Tag");
				commonFilter.setAbnCatch(Status);
				commonFilter.setAbnImp("EXCEL");
				CommonMessage.debugMsg("Status"+Status);
					//commonFilter.setStatus("Completed");
				if(action.equals("AbnView_getExcel.abnForm"))
					commonFilter.setStatus("view");
				
				
				Workbook wb = abnService.abnExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "Abnormality", format);
				
				
				
			}
			
			//****************************************Abnormality Multiple Entry****************************************************************************//
		        else if(action.equals("MultipleAbnormality_input.abnForm")){
			     CommonMessage.debugMsg("Multiple abn"+action);
			     
			        String frmMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
					String tagClass = request.getParameter("tagClass");
					
					String selMachineId= request.getParameter("selMachineId");
					String clirefDocID = request.getParameter("refDocId");
					String refDocId = request.getParameter("refDocId");
					CommonMessage.debugMsg("The refDocId"+refDocId);
					String refDoctype = request.getParameter("refDoctype");
					CommonMessage.debugMsg("The refDoctype"+refDoctype);
					String AbnkeyId = request.getParameter("AbnId");
					CommonMessage.debugMsg("The AbnkeyId"+AbnkeyId);
					String date=CommonFunctions.dateTimeNow();
					CommonMessage.debugMsg("Date"+date);
					String currentDate=UIUtils.getActualDateForm(date);
		            CommonMessage.debugMsg("CurrentDate"+currentDate);
		            String remarks = request.getParameter("remarks");
		            CommonMessage.debugMsg("The Remarks"+remarks);
	                String elementid=CommonFunctions.getLoginElementId(request);
			      //  CommonMessage.debugMsg("login element id"+elementid);
			        String locationid=null;
			        if(elementid.length()>10){
			        locationid=elementid.substring(11,21);
			        //CommonMessage.debugMsg("locationid"+locationid);
			        }
					String frmType=null;
					FormModes mode = FormModes.create;
					CommonMessage.debugMsg("frmMode ="+frmMode + " mode " + mode);
					if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.view) )
						mode = FormModes.view;
					else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.modify) )
						mode = FormModes.modify;
					else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.complete) )
						mode = FormModes.completion;
					else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.removal) )
						mode = FormModes.removal;
					
					if(action.equals("MultipleAbnormality_input.abnForm") )
						frmType="JH";
					else if(action.equals("HseAbnormality_input.abnForm"))
						frmType="SHE";
				
					CommonMessage.debugMsg("frmMode ="+frmMode + " mode " + mode);
					String flid= request.getParameter("flid");
					if(UIUtils.isValidKeyId(flid))
						request.setAttribute("flid", flid);
					
					CommonMessage.debugMsg("clirefDocID............."+clirefDocID);
					if(UIUtils.isValidKeyId(selMachineId))
						request.setAttribute("selMachineId", selMachineId);
					if( UIUtils.isValidKeyId(clirefDocID) ){
						
						request.setAttribute("clirefDocID", clirefDocID); 			
						request.setAttribute("clirefDocType", "CLI");
					}
					if( UIUtils.isValidKeyId(refDocId) ){
						 CommonMessage.debugMsg("Inside RefDocID" );
						request.setAttribute("refDoctype", refDoctype); 			
						request.setAttribute("refDocId", refDocId);
					}
					
					String modeType = request.getParameter("modeType");
					initilizeInputMode(mode,frmType, request, httpSession);
					CommonMessage.debugMsg("fddhfg tes t1233jfgjf............."+mode+" modeType "+modeType);
					request.setAttribute("mode", mode);
					request.setAttribute("modeType", modeType);
					request.setAttribute("tagClass", tagClass);
					request.setAttribute("frmType", frmType);
					request.setAttribute("AbnkeyId", AbnkeyId);
					request.setAttribute("loginUser", user.getUsrm_ccno());
					request.setAttribute("locationid",locationid);
					request.setAttribute("currentDate",currentDate);
					request.setAttribute("remarks",remarks);
			        RequestDispatcher rd = request.getRequestDispatcher("/pages/MultipleAbnormalityGrid.jsp");					
					rd.forward(request, response);
			     
			}
		    else if (action.equals("MultipleAbnormality_getCol.abnForm")) {
				    		try{
								PrintWriter out = response.getWriter();
								out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.MultipleAbnormality","SafetyDetail"));
								CommonMessage.debugMsg("Multiple Abnormality>>>>>>"+UIUtils.getPropertyValue("com.akranta.tpm.resources.MultipleAbnormality","SafetyDetail"));
							}catch(Exception e){
								e.printStackTrace(); 
							} 			 
						}
			
		    else if( action.equals("MultipleAbnormality_getData.abnForm"))
			{
				
			   try {
					//CommonMessage.debugMsg("RiskAssesmentEntry_getData.risk");
					CommonFilter commonFilter = new CommonFilter();
					String keyid =request.getParameter("keyid");
					String flid =request.getParameter("flid");
					String auditflid =request.getParameter("auditflid");
					String auditdate =request.getParameter("auditdate"); 
					String frmtype=request.getParameter("TYPE");
				  	String mode=request.getParameter("mode");
				  	CommonMessage.debugMsg("all param 1:<->: "+"keyid :"+keyid+" auditflid:"+auditflid);
				  	CommonMessage.debugMsg("all param 2:<->: "+"auditdate :"+auditdate+" frmtype:"+frmtype);
				  	CommonMessage.debugMsg("mode :"+mode);
					CommonMessage.debugMsg("keyid"+keyid);
					commonFilter.setKey(keyid);
					commonFilter.setFlid(auditflid);
					commonFilter.setAuditFromDate(auditdate);
					commonFilter.setType(frmtype);
					commonFilter.setSafetyMode(mode);
					PrintWriter out = response.getWriter();
//					List<String> Keyids = (List<String>)httpSession.getAttribute(flid);
					List<String> Keyids = null ;// (List<String>)httpSession.getAttribute(flid);
//					httpSession.setAttribute("abnormalityKeyIds", abnormalityKeyIds);
//					List<String[]> RskAssActList = abnService.getMultipleAbngridDetail(commonFilter);
					List<String[]> RskAssActList = abnService.getMultipleAbnDetail(Keyids);
					JSONObject RskAssActData = UIUtils.convertToJqGridTableObject(RskAssActList, request, 0, 0);
					out.println(RskAssActData);
				} catch (Exception e) {
					CommonMessage.debugMsg("PLANTSAFETY Exception" + e.getMessage());
					e.printStackTrace();
				}
				}
			
//				  else if( action.equals("MultipleAbnormality_getData.abnForm"))
//							{
//								
//							   try {
//									//CommonMessage.debugMsg("RiskAssesmentEntry_getData.risk");
//									CommonFilter commonFilter = new CommonFilter();
//									String keyid =request.getParameter("keyid");
//									String auditflid =request.getParameter("auditflid");
//									String auditdate =request.getParameter("auditdate"); 
//									String frmtype=request.getParameter("TYPE");
//								  	String mode=request.getParameter("mode");
//								  	CommonMessage.debugMsg("all param 1:<->: "+"keyid :"+keyid+" auditflid:"+auditflid);
//								  	CommonMessage.debugMsg("all param 2:<->: "+"auditdate :"+auditdate+" frmtype:"+frmtype);
//								  	CommonMessage.debugMsg("mode :"+mode);
//									CommonMessage.debugMsg("keyid"+keyid);
//									commonFilter.setKey(keyid);
//									commonFilter.setFlid(auditflid);
//									commonFilter.setAuditFromDate(auditdate);
//									commonFilter.setType(frmtype);
//									commonFilter.setSafetyMode(mode);
//									PrintWriter out = response.getWriter();
//									List<String[]> RskAssActList = abnService.getMultipleAbngridDetail(commonFilter);
//									JSONObject RskAssActData = UIUtils.convertToJqGridTableObject(RskAssActList, request, 2, 0);
//									out.println(RskAssActData);
//								} catch (Exception e) {
//									CommonMessage.debugMsg("PLANTSAFETY Exception" + e.getMessage());
//									e.printStackTrace();
//								}
//								}
			
			
						
			
			
			/**-------------- For SAFETY Abnormality ----------------------- **/
			
			/*else if(action.equals("HseModify_getCol.abnForm")||(action.equals("HseTagRemove_getCol.abnForm"))|| action.equals("HseAbnormality_getCol.abnForm"))
			{
				PrintWriter out = response.getWriter();
				String viewGrid="HseModify";
				
				if(action.equals("HseModify_getCol.abnForm"))
					viewGrid="HseModify";
				else if(action.equals("HseTagRemove_getCol.abnForm"))
					viewGrid="HseModify";
				
				populateCommonFilter(request,"HseAbnormalityFilter",true);
				response.setContentType("text/html");
				
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityGeneralReport",viewGrid));	
			}
			*/
			/*else if(action.equals("HseModify_getData.abnForm")||(action.equals("HseTagRemove_getData.abnForm"))|| action.equals("HseAbnormality_getData.abnForm"))
			{
				try
				{
					PrintWriter out = response.getWriter();
					UIUtils.displayRequestParamsValue(request);
					CommonFilter commonFilter  = populateCommonFilter(request,"HseAbnormalityFilter",false);
					
					String abnmStatus="modification";
					if(action.equals("HseModify_getData.abnForm"))
						abnmStatus="modification";
					else if(action.equals("HseTagRemove_getData.abnForm"))
						abnmStatus="removal";
				CommonMessage.debugMsg("abnmStatus--->"+abnmStatus);
	  			 	List<String[]> AbnDataList  = abnService.getAllModifyForm(commonFilter,abnmStatus,"SHE");
	  			 	int count = AbnDataList.size();
	  			 	Long totalCnt = (long)count;
				 	JSONObject AbnormalityData = UIUtils.convertToJqGridTableObject(AbnDataList, request, 0, 0, commonFilter.getTotalRecordCnt());
	  				httpSession.setAttribute("totalCnt", totalCnt);
	  			 	out.println(AbnormalityData);
	
			    }catch(Exception e)
				{
					CommonMessage.debugMsg("getdata Hse Abnormality Exception :"+e.getMessage());
				}
			}
			*/
			else if(action.equals("HseModify_getExcel.abnForm") || (action.equals("HseTagRemove_getExcel.abnForm"))||(action.equals("HseAbnormality_getExcel.abnForm")))
			{
				//int count = (Integer) httpSession.getAttribute("totalCnt");
				CommonFilter commonFilter  = populateCommonFilter(request,"HseAbnormalityFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityGeneralReport","HseModify");
				JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
				
				tblJSONObj.put("title", "SHE Modification");
				String title = "HSEModification";
				if(action.equals("HseModify_getExcel.abnForm"))
				{
					tblJSONObj.put("title", "SHE Modification");
					title = "SHEModification";
				}
				else if(action.equals("HseTagRemove_getExcel.abnForm"))
				{
					tblJSONObj.put("title", "SHE TagRemoval");
					title = "SHETagRemoval";
				}
				
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = abnService.getHSEAbnExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, title, format);
			}
			else if(action.equals("Abnservice_getCount.abnForm"))
			{
				PrintWriter out=response.getWriter();
				CommonFilter commonFilter  = new CommonFilter();
				String servicedata=abnService.getServiceCount(commonFilter);
				JSONObject returnData= new JSONObject();
			    returnData.put("servicecnt", servicedata);				
				out.print(returnData.toString());
				
			}
			
			
			if (dispatchUrl != null)
			{
				
				RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
				rd.forward(request, response); 
	 
			}
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		
		
		
			
	}
	
	
	
    
private void saveAbnormalityApprovedpopup(HttpServletRequest request,HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
	
	CommonMessage.debugMsg("");
	HttpSession httpSession = request.getSession(false);
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	PrintWriter out = response.getWriter();
	String Json ;
	List<String[]> UpdatedRow = null ;
	
	String keyid = request.getParameter("keyid");
	String abncotrmsre = request.getParameter("abncotrmsre");
	String abnstatus = request.getParameter("abnstatus");
	String abncomptby = request.getParameter("abncomptby");
	String abnremarks = request.getParameter("abnremarks");
	String abncomptdte = request.getParameter("abncomptdte");
	String abnrowid = request.getParameter("abnrowid");
	
	AbnTlAbnormality newAbnTlAbnormality = new AbnTlAbnormality();
	AbnTlAbnormality existAbnTlAbnormality = (AbnTlAbnormality) httpSession.getAttribute("newAbnTlAbnormality");
	newAbnTlAbnormality = (AbnTlAbnormality) UIUtils.setBeanProperties((Object) newAbnTlAbnormality, request);

	newAbnTlAbnormality.setAbnmKeyid(keyid);
	newAbnTlAbnormality.setAbnmCountermeasure(abncotrmsre);
	newAbnTlAbnormality.setAbnmStatus(abnstatus);
	newAbnTlAbnormality.setAbnmCompletedby(abncomptby);
	newAbnTlAbnormality.setAbnmRemarks(abnremarks);
	newAbnTlAbnormality.setAbnmWoendtime(abncomptdte);
	
	CommonMessage.debugMsg("KEYID: "+keyid);
	CommonMessage.debugMsg("RowID: "+abnrowid);
	try{
		if(UIUtils.isValidKeyId(keyid)){
			existAbnTlAbnormality=abnService.updateabncomp(newAbnTlAbnormality);
//			existAbnTlAbnormality = abnServiceApi.updateAbnComp(newAbnTlAbnormality);
			String msgPropertyIdnt = "success-update";
			//CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
			JSONObject err = new JSONObject();
			String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
			err.put("successData",mesg);
			err.put("keyId",existAbnTlAbnormality.getAbnmKeyid());
			err.put("rowId",abnrowid);
			if(UIUtils.isValidKeyId(abnrowid)) {
			  UpdatedRow = abnService.getAbnUpdatedRow(existAbnTlAbnormality.getAbnmKeyid());
			}
			if(UpdatedRow != null ) {
				String[] firstRow = UpdatedRow.get(0);

				// Join columns with comma
				String rowString = String.join(",", firstRow);

				CommonMessage.debugMsg(rowString);
				err.put("updatedRow", rowString);	
			}
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


private void saveAbnAllocation(HttpServletRequest request,	HttpServletResponse response) throws IOException,ValidationExceptions {

	HttpSession httpSession = request.getSession(false);
	ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);	
	String gridData = request.getParameter("allocationGridData");
	
	//String tradeId = request.getParameter("cmbAbnmTradeid");
	//String dteAbnmenddate = request.getParameter("dteAbnmenddate");
	//AbnTlAbnormality newAbnTlAbnormality = new AbnTlAbnormality();	
	AbnTlAbnormality existAbnTlAbnormality = new AbnTlAbnormality();	
	CommonMessage.debugMsg("gridData..."+gridData);
	CommonMessage.debugMsg("gridData..."+gridData);
	//newAbnTlAbnormality.setAbnmCreatedby(user.getUsrm_ccno());
	List<AbnTlAbnormality> newAbnTlAbnormality = new ArrayList<AbnTlAbnormality>();	
	//List<AbnTlDtl> newAbnTlAbnormalityDtl = new ArrayList<AbnTlDtl>();
	AbnTlAbnormality abnTlAbnormality=new AbnTlAbnormality();
	AbnTlDtl abnTlAbnormalityDtl=new AbnTlDtl();
	//newAbnTlAbnormality = (AbnTlAbnormality) UIUtils.setBeanProperties((Object) abnTlAbnormality, request);
	JSONArray abnormality = null;
	if(UIUtils.isValidKeyId(gridData)){
		CommonMessage.debugMsg("data...");
		if( gridData != null && ! gridData.isEmpty())
		{
			CommonMessage.debugMsg("test123...");
			abnormality = JSONArray.fromString(gridData);
			newAbnTlAbnormality=(List<AbnTlAbnormality>)UIUtils.convertJSONArrToList(abnTlAbnormality, abnormality);
			//newAbnTlAbnormalityDtl=(List<AbnTlDtl>)UIUtils.convertJSONArrToList(abnTlAbnormalityDtl, abnormality);
		}
	}
	
	
	
	try {
		if(newAbnTlAbnormality!=null){
			for(int i=0;i<newAbnTlAbnormality.size();i++){
				//abnTlAbnormality.setAbnmCompletedby(newAbnTlAbnormality.get(i).getAbnmCompletedby());
				//newAbnTlAbnormality.get(i).setAbnmCreatedby(user.getUsrm_ccno());
	
				//newAbnTlAbnormality.get(i).setAbnmWoendtime(newAbnTlAbnormality.get(i).getAbnmWoendtime());
				//newAbnTlAbnormality.get(i).setAbnmTradeid(tradeId);
				//newAbnTlAbnormality.get(i).setAbnmTargetdate(dteAbnmenddate);
				
				//if(CommonFunctions.isValidKeyId(newGenTlMchrankparameter.get(i).getMrkpKeyid())){
					//KpiTlActual oldKpiTlActualKk=new KpiTlActual();
				//	Mchrankparameter.setMrkpKeyid(newGenTlMchrankparameter.get(i).getMrkpKeyid());
				//	Mchrankparameter=genTlMchrankparameterService.select(newGenTlMchrankparameter);
				//	newGenTlMchrankparameter.get(i).setMrkpCreatedby(Mchrankparameter.getMrkpCreatedby());
				//	newGenTlMchrankparameter.get(i).setMrkpCreatedon(Mchrankparameter.getMrkpCreatedon());								
				//}
				CommonMessage.debugMsg("test");
				newAbnTlAbnormality.get(i).setAbnmTradeid(newAbnTlAbnormality.get(i).getAbnmTradeid());
				CommonMessage.debugMsg("test1");
				if(UIUtils.isValidKeyId(newAbnTlAbnormality.get(i).getAbnmTargetdate()))
					newAbnTlAbnormality.get(i).setAbnmTargetdate(newAbnTlAbnormality.get(i).getAbnmTargetdate());
				if(UIUtils.isValidKeyId(newAbnTlAbnormality.get(i).getAbnmEffectivedate())) {
					newAbnTlAbnormality.get(i).setAbnmEffectivedate(CommonFunctions.pg_getDateTimeFromDate(newAbnTlAbnormality.get(i).getAbnmEffectivedate()));
				}else {
					newAbnTlAbnormality.get(i).setAbnmEffectivedate(Constants.pgPassNullDateTime);
				}
					
				CommonMessage.debugMsg("test2");
				newAbnTlAbnormality.get(i).setAbnmKeyid(newAbnTlAbnormality.get(i).getAbnmKeyid());

				/*if(newAbnTlAbnormalityDtl.size()>0){
					if(newAbnTlAbnormalityDtl.size() >=i){
					if(UIUtils.isValidKeyId(newAbnTlAbnormalityDtl.get(i).getAbndResponsiblity()))
						newAbnTlAbnormalityDtl.get(i).setAbndResponsiblity(newAbnTlAbnormalityDtl.get(i).getAbndResponsiblity());
					newAbnTlAbnormality.get(i).setAbnTlDtl(newAbnTlAbnormalityDtl.get(i));
					}
				}*/
		
			}
			
			newAbnTlAbnormality = abnService.updateAbnAllocation(newAbnTlAbnormality,existAbnTlAbnormality);
		
		
		
		JSONObject successData = new JSONObject();				
		String msgPropertyIdnt;					 
		msgPropertyIdnt = "success-save";
		
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
		
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);	
		out.print(returnData.toString());
		}
	} catch (ValidationExceptions e) {
		JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "AbnormalityCreation");
		out.print(errMessage.toString());
	} catch (BusinessApplicationExceptions e) {
		CommonMessage.debugMsg("Error Servler e -" + e.toString());
		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "AbnormalityCreation");
		out.print(errMessage.toString());
		CommonMessage.debugMsg(" e " + errMessage);

	} catch (Exception e) {
		CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not Saved");
		out.print(err.toString());
	}
	
	

		
	}


private JSONObject getTableModelAbnAllocation(List<String[]> headers) {

	CommonMessage.debugMsg("getTableModel");
	JqGridTableModel jqGridTableModel = new JqGridTableModel();
	String[] colHeader = headers.get(0);
	
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.setTableButton(true);
	jqGridTableModel.setRowNumbers(true);

	for (int i = 0; i < colHeader.length; i++) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setWidth(150);
		jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);
		if(i==11)
			jqGridColModel.setHidden(true);
		if(i==0){
			jqGridColModel.setWidth(500);
		}
		if(i==1){
			jqGridColModel.setWidth(100);
			jqGridColModel.setFormatter("formattorTargetDate");
		}
		if (i == 2) {
			jqGridColModel.setWidth(130);
			jqGridColModel.setFormatter("formattorTrade");
		}
		if(i==3){
			jqGridColModel.setWidth(200);
			jqGridColModel.setFormatter("formattorRespons");
		}
		if(i==5||i==6||i==10)
			jqGridColModel.setWidth(200);
		 if(i==7){
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("center");
		}
		 if(i==9){
				jqGridColModel.setWidth(100);
			}
		jqGridTableModel.getColModel().add(jqGridColModel);
		CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
	}
	
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "85%%");
	tableModel.set("tableWidth", "108%%");
	tableModel.set("multiSelect", true);
	return tableModel;

}


private JSONObject getTableModelRepeatedAbn(List<String[]> headers) {
	

	CommonMessage.debugMsg("getTableModel");
	JqGridTableModel jqGridTableModel = new JqGridTableModel();
	String[] colHeader = headers.get(0);
	
	jqGridTableModel.getRowHeaders().add(colHeader);
	
	jqGridTableModel.setRowNumbers(true);

	for (int i = 0; i < colHeader.length; i++) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		CommonMessage.debugMsg("colHeader["+i+"]..."+colHeader[i]);
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setWidth(250);
		jqGridColModel.setAlign("center");
		jqGridColModel.setEditable(false);
		if(i==0 ||i==8 ||i==6)
			jqGridColModel.setHidden(true);
		if (i == 1) {
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");
		}
		if(i==2){
			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
		}
		if(i==3){
			jqGridColModel.setWidth(600);
			jqGridColModel.setAlign("left");
		}
		if(i==4){
			jqGridColModel.setWidth(100);
		}
		if(i==5 ){
			jqGridColModel.setWidth(150);
			jqGridColModel.setAlign("left");
		}
		if(i==7){
			jqGridColModel.setWidth(250);
			jqGridColModel.setAlign("left"); 
		}
		
		jqGridTableModel.getColModel().add(jqGridColModel);
		
		CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
	}

	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "90%%");
	tableModel.set("tableWidth", "78%%");
	tableModel.set("multiSelect", true);
	return tableModel;

	
}


private void saveAbnBulkRemoveTag(HttpServletRequest request,HttpServletResponse response) throws IOException,ValidationExceptions {

	HttpSession httpSession = request.getSession(false);
	ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);	
	String gridData = request.getParameter("gridData");
	String completedBy = request.getParameter("cmbAbnmCompletedby");
	//String tradeId = request.getParameter("cmbAbnmTradeid");
	//String dteAbnmenddate = request.getParameter("dteAbnmenddate");
	//AbnTlAbnormality newAbnTlAbnormality = new AbnTlAbnormality();	
	AbnTlAbnormality existAbnTlAbnormality = new AbnTlAbnormality();	
	CommonMessage.debugMsg("gridData..."+gridData);
	//newAbnTlAbnormality.setAbnmCreatedby(user.getUsrm_ccno());
	List<AbnTlAbnormality> newAbnTlAbnormality = new ArrayList<AbnTlAbnormality>();	
	AbnTlAbnormality abnTlAbnormality=new AbnTlAbnormality();
	//newAbnTlAbnormality = (AbnTlAbnormality) UIUtils.setBeanProperties((Object) abnTlAbnormality, request);
	JSONArray abnormality = null;
	if(UIUtils.isValidKeyId(gridData)){
		CommonMessage.debugMsg("data...");
		if( gridData != null && ! gridData.isEmpty())
		{
			CommonMessage.debugMsg("test123...");
			abnormality = JSONArray.fromString(gridData);
			newAbnTlAbnormality=(List<AbnTlAbnormality>)UIUtils.convertJSONArrToList(abnTlAbnormality, abnormality);
		}
	}
	
	
	
	try {
		if(newAbnTlAbnormality!=null){
			for(int i=0;i<newAbnTlAbnormality.size();i++){
				abnTlAbnormality.setAbnmCompletedby(newAbnTlAbnormality.get(i).getAbnmCompletedby());
				newAbnTlAbnormality.get(i).setAbnmCreatedby(user.getUsrm_ccno());
				newAbnTlAbnormality.get(i).setAbnmCompletedby(completedBy);
				newAbnTlAbnormality.get(i).setAbnmWoendtime(newAbnTlAbnormality.get(i).getAbnmWoendtime());
				//newAbnTlAbnormality.get(i).setAbnmTradeid(tradeId);
				//newAbnTlAbnormality.get(i).setAbnmTargetdate(dteAbnmenddate);
				
				//if(CommonFunctions.isValidKeyId(newGenTlMchrankparameter.get(i).getMrkpKeyid())){
					//KpiTlActual oldKpiTlActualKk=new KpiTlActual();
				//	Mchrankparameter.setMrkpKeyid(newGenTlMchrankparameter.get(i).getMrkpKeyid());
				//	Mchrankparameter=genTlMchrankparameterService.select(newGenTlMchrankparameter);
				//	newGenTlMchrankparameter.get(i).setMrkpCreatedby(Mchrankparameter.getMrkpCreatedby());
				//	newGenTlMchrankparameter.get(i).setMrkpCreatedon(Mchrankparameter.getMrkpCreatedon());								
				//}
			}
			newAbnTlAbnormality = abnService.updateBulkTagRemoval(newAbnTlAbnormality,existAbnTlAbnormality);
		
		
		
		JSONObject successData = new JSONObject();				
		String msgPropertyIdnt;					 
		msgPropertyIdnt = "success-save";
		
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
		
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);	
		out.print(returnData.toString());
		}
	} catch (ValidationExceptions e) {
		JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "AbnormalityCreation");
		out.print(errMessage.toString());
	} catch (BusinessApplicationExceptions e) {
		CommonMessage.debugMsg("Error Servler e -" + e.toString());
		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "AbnormalityCreation");
		out.print(errMessage.toString());
		CommonMessage.debugMsg(" e " + errMessage);

	} catch (Exception e) {
		CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not Saved");
		out.print(err.toString());
	}
	
	
	
	}


private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){

		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		
		
		if( commonFilter != null && ! createNew ){
			CommonMessage.debugMsg("false.....");
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			String tvariable;
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
			commonFilter.setViewClick('Y');
			tvariable=request.getParameter("empKeyId");
			CommonMessage.debugMsg("empKeyId ::"+tvariable);
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			if(  "Y".equals(commonFilter.getDefaultFinYear()) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-3).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
		
	}

	private void fillAbnData(AbnTlAbnormality abnTlAbnormality,	AbnormalityBean abnormalityBean) {
		// TODO Auto-generated method stub
		
		abnTlAbnormality.setAbnmCountermeasure(abnTlAbnormality.getAbnmCountermeasure().replace("<*", "").replace("*>", "").replace("{","").replace("}",""));
		abnTlAbnormality.setAbnmRemarks(abnTlAbnormality.getAbnmRemarks().replace("<*", "").replace("*>", "").replace("{","").replace("}",""));
		abnTlAbnormality.setAbnmWhatcause(abnTlAbnormality.getAbnmWhatcause().replace("<*", "").replace("*>", ""));
		abnTlAbnormality.setAbnmWhyabnhappened(abnTlAbnormality.getAbnmWhyabnhappened().replace("<*", "").replace("*>", ""));
		abnTlAbnormality.setAbnmDescription(abnTlAbnormality.getAbnmDescription().replace("<*", "").replace("*>", ""));
		abnTlAbnormality.setAbnmDetailedesc(abnTlAbnormality.getAbnmDetailedesc().replace("<*", "").replace("*>", ""));
		abnTlAbnormality.setAbnmTargetremarks(abnTlAbnormality.getAbnmTargetremarks().replace("<*", "").replace("*>", "").replace("{","").replace("}",""));
		abnTlAbnormality.setAbnmTargetdate(CommonFunctions.pg_getFormatDateFromDate(abnTlAbnormality.getAbnmTargetdate().substring(0,10)) );
		String woEndTime = abnTlAbnormality.getAbnmWoendtime();
		CommonMessage.debugMsg("woEndTime : "+woEndTime);
		if(UIUtils.isValidKeyId(woEndTime))
		{
			if(woEndTime.indexOf(":")>0)
				abnormalityBean.setAbnmendtime(woEndTime.substring(woEndTime.indexOf(" ")+1));
			CommonMessage.debugMsg("woEndTime : "+abnormalityBean.getAbnmendtime());
		}
		//abnTlAbnormality.setAbnmWoendtime(abnTlAbnormality.getAbnmWoendtime().substring(0,11));
		abnTlAbnormality.setAbnmDetectiondate(CommonFunctions.pg_getFormatDateFromDate(abnTlAbnormality.getAbnmDetectiondate().substring(0,10)));
	}


	private void saveAbn(HttpServletRequest request,HttpServletResponse response) throws IOException,BusinessApplicationExceptions
	{
		
		CommonMessage.debugMsg("REMARKS : "+request.getParameter("txtAbnmTargetremarks"));
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	String Json = "";
    
    	CommonMessage.debugMsg("chkAbnmRepotheres : "+request.getParameter("chkAbnmRepotheres"));
    	
    	
    	if( httpSession != null && user != null)
    	{	
    		 
    		AbnTlAbnormality newAbnTlAbnormality = new AbnTlAbnormality();  
    	//	AbnTlDtl newAbnTlDtl= new AbnTlDtl(); 
    		AbnTlAbnhistorydtl newAbnTlAbnhistorydtl= new AbnTlAbnhistorydtl();
    		AbnormalityBean newAbnormalityBean = new AbnormalityBean();
    		WomTlWomst womTlWomst = (WomTlWomst) httpSession.getAttribute("WorkOrderABN");
    		
    		newAbnTlAbnormality =(AbnTlAbnormality)UIUtils.setBeanProperties((Object)newAbnTlAbnormality,request);
    	//	newAbnTlDtl =(AbnTlDtl)UIUtils.setBeanProperties((Object)newAbnTlDtl,request);
    		newAbnTlAbnhistorydtl =(AbnTlAbnhistorydtl)UIUtils.setBeanProperties((Object)newAbnTlAbnhistorydtl,request);
    		newAbnormalityBean =(AbnormalityBean) UIUtils.setBeanProperties((Object)newAbnormalityBean,request);
    		CommonMessage.debugMsg("REMARKS : "+newAbnTlAbnormality.getAbnmTargetremarks());
    		
    		AbnTlAbnormality existAbnTlAbnormality = (AbnTlAbnormality)httpSession.getAttribute("abnTlAbnormalityAbnServlet");    		 
    		AbnTlDtl existAbnTlDtl = (AbnTlDtl)httpSession.getAttribute("abnTlDtlAbnServlet");   
    		AbnTlAbnhistorydtl existAbnTlAbnhistorydtl = (AbnTlAbnhistorydtl)httpSession.getAttribute("abnTlAbnhistorydtlAbnServlet");
    		AbnormalityBean abnormalityFormBean = (AbnormalityBean)httpSession.getAttribute("abnormalityBean");
    		
    		CommonMessage.debugMsg(" Checking value for created by :: Before "+user.getUsrm_ccno()+" :: "+newAbnTlAbnormality.getAbnmCreatedby());
    		newAbnTlAbnormality.setAbnmCreatedby(user.getUsrm_ccno());
    		CommonMessage.debugMsg(" Checking value for created by :: after "+user.getUsrm_ccno()+" :: "+newAbnTlAbnormality.getAbnmCreatedby());
    		abnormalityFormBean.setAbnmDetectedbytime(newAbnormalityBean.getAbnmDetectedbytime());
    		abnormalityFormBean.setAbnmstarttime(newAbnormalityBean.getAbnmstarttime());
    		abnormalityFormBean.setAbnmendtime(newAbnormalityBean.getAbnmendtime());
    		abnormalityFormBean.setAbnmenddate(newAbnormalityBean.getAbnmenddate());
    		abnormalityFormBean.setShowCompDate(newAbnormalityBean.getShowCompDate());
    		
    		String msgPropertyIdnt=null;
    		String RelatedTo = request.getParameter("relatedToCMB");
    		String openactnpln = request.getParameter("openactnpln");
    		String openfilemgr = request.getParameter("openfilemgr");
    		String repOthers = request.getParameter("chkAbnmRepotheres");
    		String rowId = request.getParameter("hdnrowid");
    		
    		List<String[]> UpdatedRow = null ;
    		
    		CommonMessage.debugMsg("openactnpln"+openactnpln);
    		newAbnTlAbnormality =(AbnTlAbnormality)UIUtils.setBeanProperties((Object)newAbnTlAbnormality,request);
    		CommonMessage.debugMsg("REMARKS : "+newAbnTlAbnormality.getAbnmTargetremarks());
    		newAbnTlAbnormality.setAbnmRepOthers(repOthers);
    		if(UIUtils.isValidKeyId(RelatedTo))
    			newAbnTlAbnormality.setAbnmRelatedto(RelatedTo);
    		
    		if(newAbnTlAbnhistorydtl != null)
    			newAbnTlAbnormality.setAbnTlAbnhistorydtl(newAbnTlAbnhistorydtl);
    		
    		/*if(newAbnTlDtl != null)
    			newAbnTlAbnormality.setAbnTlDtl(newAbnTlDtl);*/
    		
    		
    	/*	if(newAbnTlDtl != null)
    		{
    			if(existAbnTlDtl != null)
    			{
    				existAbnTlDtl.setAbndResponsiblity(newAbnTlAbnormality.getAbnTlDtl().getAbndResponsiblity());
    				existAbnTlDtl.setAbndImmediateaction(newAbnTlAbnormality.getAbnTlDtl().getAbndImmediateaction());
    				existAbnTlDtl.setAbndIspokayokeprovided(newAbnTlAbnormality.getAbnTlDtl().getAbndIspokayokeprovided());
    				existAbnTlDtl.setAbndHirarefno(newAbnTlAbnormality.getAbnTlDtl().getAbndHirarefno());
    				existAbnTlDtl.setAbndAvoidrecurrence(newAbnTlAbnormality.getAbnTlDtl().getAbndAvoidrecurrence());
    				existAbnTlDtl.setAbndPokayokeid(newAbnTlAbnormality.getAbnTlDtl().getAbndPokayokeid());
    				existAbnTlDtl.setAbndImprovementteam(newAbnTlAbnormality.getAbnTlDtl().getAbndImprovementteam());
    				String imp = request.getParameter("cboAbndImprovementteam");
    				existAbnTlDtl.setAbndImprovementteam(imp);
    			if(UIUtils.isValidKeyId(existAbnTlDtl.getAbndImprovementteam()))
    			{
    				CommonMessage.debugMsg("Improvem " +newAbnTlDtl.getAbndImprovementteam()+request.getParameter("cboAbndImprovementteam"));
    				 imp = request.getParameter("cboAbndImprovementteam");
    				if(UIUtils.isValidKeyId(imp))
    					existAbnTlDtl.setAbndImprovementteam(imp);
    				else
    					existAbnTlDtl.setAbndImprovementteam(newAbnTlDtl.getAbndImprovementteam());
    				newAbnTlAbnormality.setAbnTlDtl(existAbnTlDtl);
    			}
    			else
    			{
    				CommonMessage.debugMsg("exist dtl ");
    				newAbnTlDtl.setAbndCreatedby(user.getUsrm_ccno());
	    			newAbnTlAbnormality.setAbnTlDtl(newAbnTlDtl);
	    			
    			}
    			}
    			else
    			{
    				CommonMessage.debugMsg("new dtl ");
    				newAbnTlDtl.setAbndCreatedby(user.getUsrm_ccno());
	    			newAbnTlAbnormality.setAbnTlDtl(newAbnTlDtl);
	    			
    			}
    		} */
    		
    		
    		try{
    			//for team						
    			newAbnTlAbnormality.setTeamList(UIUtils.getTeamDetails(request));						
				//CommonMessage.debugMsg("newPlmTlGenmaintenance.teamList1"+newAbnTlAbnormality.getTeamList().get(0).getTmdlTeamid());
    			
    			//if(!(abnormalityFormBean.getFormActionMode().equals("Completed"))&&!(abnormalityFormBean.getFormActionMode().equals("View")) )
    				if(!(abnormalityFormBean.getFormActionMode().equals("View")) )
    			{
    				if( newAbnTlAbnormality.getAbnmKeyid() == null )
    				{	
    					existAbnTlAbnormality =	abnService.create(newAbnTlAbnormality,existAbnTlAbnormality,abnormalityFormBean);
//    					existAbnTlAbnormality = abnServiceApi.insertRecord(newAbnTlAbnormality);
    					msgPropertyIdnt = "success-save";
    				}	
    				else
    				{
    					/*if(existAbnTlDtl!= null)
    						existAbnTlAbnormality.setAbnTlDtl(existAbnTlDtl);
    					*/
    					CommonMessage.debugMsg("Else Called");
//    					existAbnTlAbnormality = abnServiceApi.insertRecord(newAbnTlAbnormality);
    					existAbnTlAbnormality = abnService.update(newAbnTlAbnormality,existAbnTlAbnormality,abnormalityFormBean,womTlWomst);						
    				}
    			}
//    			JSONObject jsonObj = new JSONObject(Json);
				httpSession.setAttribute("abnTlAbnormalityAbnServlet", existAbnTlAbnormality);				
				Boolean clrVal=true;
				msgPropertyIdnt = "Abnsuccess-update";
				if(abnormalityFormBean.getFormActionMode().equals("Modify"))
					msgPropertyIdnt = "Abnsuccess-update";
				/*else if(abnormalityFormBean.getFormActionMode().equals("Completed"))
					{msgPropertyIdnt="cmp-save"; clrVal=false;}*/
				else if(abnormalityFormBean.getFormActionMode().equals("View"))
					msgPropertyIdnt = "Abnsuccess-update";
				else if(abnormalityFormBean.getFormActionMode().equals("Create"))
					msgPropertyIdnt = "Abnsuccess-save";
				// else if(openactnpln.equals("Create"))
				//	msgPropertyIdnt = "  ";
				
				JSONObject successData = new JSONObject();
				
				if(UIUtils.isValidKeyId(rowId)) {
					UpdatedRow = abnService.getAbnUpdatedRow(existAbnTlAbnormality.getAbnmKeyid());
				}
				
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				successData.put("mode",abnormalityFormBean.getFormActionMode());
				successData.put("formType", abnormalityFormBean.getPillarName());
				successData.put("keyId",existAbnTlAbnormality.getAbnmKeyid());
				successData.put("rowId",rowId);
				successData.put("flid",existAbnTlAbnormality.getAbnmFlid());
				successData.put("detectedby",existAbnTlAbnormality.getAbnmDetectedby());
				
				
				if(UIUtils.isValidKeyId(openactnpln)){
					successData.put("openactnpln", true);
					successData.put("flid", newAbnTlAbnormality.getAbnmFlid());
					clrVal=false;
					CommonMessage.debugMsg(" Inside Flid servlet "+newAbnTlAbnormality.getAbnmFlid());
					//successData.put("msg","null");abnTlAbnormality.abnmFlid
				}else
				{
					//successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("openactnpln", false);
					
				}
				
				if(UIUtils.isValidKeyId(openfilemgr)){
					successData.put("openfilemgr", true);
					successData.put("flid", newAbnTlAbnormality.getAbnmFlid());
					clrVal=false;
				}
				else
					successData.put("openfilemgr", false);
				
				String redirectTo = (String) httpSession.getAttribute("redirectTo");
				if(UIUtils.isValidKeyId(redirectTo))
					successData.put("prevForm","Y");
					
				JSONObject returnData = new JSONObject();				
				JSONObject forwardData = new JSONObject();
				String whywhyFlag = request.getParameter("whywhy");
				String woFlag = request.getParameter("workorder");
			CommonMessage.debugMsg("whywhyFlag....."+whywhyFlag);
				if(UIUtils.isValidKeyId(whywhyFlag))
				{
					if(whywhyFlag.equals("Y"))
					{
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmKeyid()))
							forwardData.put("cmbwwmsRefdocno",existAbnTlAbnormality.getAbnmKeyid());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmDetectiondate()))
							forwardData.put("dtewwmsDate",existAbnTlAbnormality.getAbnmDetectiondate());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmEquipmentid()))
							forwardData.put("cmbwwmsMachineid",existAbnTlAbnormality.getAbnmEquipmentid());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmAssemblyid()))
							forwardData.put("cmbwwmsAssemblyid",existAbnTlAbnormality.getAbnmAssemblyid());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmFactoryid()))
							forwardData.put("cmbwwmsFactoryid",existAbnTlAbnormality.getAbnmFactoryid());					
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmCellid()))
							forwardData.put("cmbwwmsCellid",existAbnTlAbnormality.getAbnmCellid());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmSectionid()))
							forwardData.put("cmbwwmsSectionid",existAbnTlAbnormality.getAbnmSectionid());						
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmDetectedby()))
							forwardData.put("cmbwwmsMaintinchargeid",existAbnTlAbnormality.getAbnmDetectedby());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmWhyabnhappened()))
							forwardData.put("txtwwmsFinalaction",existAbnTlAbnormality.getAbnmWhyabnhappened());						
				
						clrVal=false;
						forwardData.put("txtformType","ABN");
						returnData.put("displyMsg",true);
						returnData.put("formMode","YY");
					}
				}
				
				if(UIUtils.isValidKeyId(woFlag))
				{
					if(woFlag.equals("Y"))
					{
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmEquipmentid()))
							forwardData.put("cmbwomsMachineid",existAbnTlAbnormality.getAbnmEquipmentid());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmAssemblyid()))
							forwardData.put("cmbwomsAssemblyid",existAbnTlAbnormality.getAbnmAssemblyid());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmFactoryid()))
							forwardData.put("cmbwomsFactoryid",existAbnTlAbnormality.getAbnmFactoryid());					
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmCellid()))
							forwardData.put("cmbwomsCellid",existAbnTlAbnormality.getAbnmCellid());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmSectionid()))
							forwardData.put("cmbwomsSectionid",existAbnTlAbnormality.getAbnmSectionid());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmRelatedto()))
							forwardData.put("cmbwomsRelatedto",existAbnTlAbnormality.getAbnmRelatedto());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmDetectedby()))
							forwardData.put("cmbwomsBookedby",existAbnTlAbnormality.getAbnmDetectedby());
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmDetectiondate()))
							forwardData.put("dtewomsOccurreddate",existAbnTlAbnormality.getAbnmDetectiondate());						
						if(UIUtils.isValidKeyId(existAbnTlAbnormality.getAbnmKeyid()))
							forwardData.put("cmbwomsRefdocid",existAbnTlAbnormality.getAbnmKeyid());						
							forwardData.put("cmbwomsRefdoctype",WOConstants.refdoctypeabn);
						forwardData.put("linkFrom","ABN");
						returnData.put("displyMsg",false);
						returnData.put("formMode","WO");
					}
				}
				if(UpdatedRow != null ) {
					String[] firstRow = UpdatedRow.get(0);

					// Join columns with comma
					String rowString = String.join(",&!@$", firstRow);

					CommonMessage.debugMsg(rowString);
					returnData.put("updatedRow", rowString);	
				}
				returnData.put("displyMsg",true);
				returnData.put("forwardData",forwardData);
				returnData.put("detectedby",existAbnTlAbnormality.getAbnmDetectedby());
				returnData.put("formClear",clrVal);
				returnData.put("successData", successData);		
				
				String backTo = (String) httpSession.getAttribute(WOConstants.backTo);
				if(UIUtils.isValidKeyId(backTo))
					returnData.put(WOConstants.backTo,backTo);
				outt.print(returnData.toString());		
				
				
			}
    		catch(ValidationExceptions e)
			{
    			e.printStackTrace();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"AbnormalityCreation");
				
				outt.print(errMessage.toString());
			}
    		catch(BusinessApplicationExceptions e)
			{

    			e.printStackTrace();
    			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"AbnormalityCreation");
    			
    			CommonMessage.debugMsg("errMessage::"+errMessage);
    			if("UK_ABN_TL_ABNORMALITY".equals(errMessage))
			{
				CommonMessage.debugMsg("This Record Already Exist");
				errMessage.put("tpmException","This Record Already Exist");
				
			}if("ACTIONPLAN_PENDING".equals(errMessage)){
				CommonMessage.debugMsg("Complete all Action Plans to complete Abnormality");
    			errMessage.put("tpmException","Complete all Action Plans to complete Abnormality");
			}
    			errMessage.put("displyMsg", true);			
				//errMessage.put("ACTIONPLAN_PENDING", "ActionPlan is Pending...");
				outt.print(errMessage.toString());

				
			}
    		catch(Exception e)
			{
    			e.printStackTrace();
				CommonMessage.debugMsg("Error Msg 1:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				outt.print(err.toString());
			}
    	}	
	}
	
	//-----------------------------Multiple Abn Save------------------------------------//
	 @SuppressWarnings("unchecked")
	  private void saveMultipleAbn(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 		// TODO Auto-generated method stub
		     HttpSession httpSession = request.getSession(false);
	    	 ServletOutputStream out = response.getOutputStream();
	    	 AdmTlUsermst createdBy = UIUtils.getLoginUser(request);
		 	String updateMsg=null;
		 	try{
		 		List<String> Keyids = (List<String>)httpSession.getAttribute("abnormalityKeyIds");
		 		AbnTlAbnormality newAbnTlAbnormality = new AbnTlAbnormality();  
	       		AbnormalityBean newAbnormalityBean = new AbnormalityBean();
	       		String abnormalitydetails=request.getParameter("abnormalitydetails");
	       		String flid=request.getParameter("flId");
	       		String sectionId=request.getParameter("sectionId");
	       		String Ism=request.getParameter("Ism");
	       		String Rowid=request.getParameter("rowid");
	       		String abnactnpln=request.getParameter("abnactnpln");
	    		String openfilemgr = request.getParameter("openfilemgr");
	       		String docID=request.getParameter("docId");
	       		String types=request.getParameter("types");
	       		String remarks=request.getParameter("remarks");
	       		CommonMessage.debugMsg("The remarks:::"+remarks);
	       		/*String status=request.getParameter("status");
	       		CommonMessage.debugMsg("The Status::"+status);*/
	       		
	       		CommonMessage.debugMsg("abnormalitydetails abnormalitydetails "+abnormalitydetails);
	       		if(remarks!=null){
	       			newAbnTlAbnormality.setAbnmRemarks(remarks);
	       		}
	       		
				newAbnTlAbnormality =(AbnTlAbnormality)UIUtils.setBeanProperties((Object)newAbnTlAbnormality,request);
	    		AbnTlAbnormality existAbnTlAbnormality = (AbnTlAbnormality)httpSession.getAttribute("newabnTlAbnormalityAbnServlet");    		 		    		
	    		newAbnormalityBean =(AbnormalityBean) UIUtils.setBeanProperties((Object)newAbnormalityBean,request);
	    		AbnormalityBean abnormalityFormBean = (AbnormalityBean)httpSession.getAttribute("abnormalityBean");		
				List<AbnTlAbnormality> AbnormalityList=null;    			
	    		JSONArray abnList= null;	
	    		  String abnmKeyid="";
	    		JSONObject returnData=new JSONObject();
	    		Boolean clrVal=true;
	    	
		 		if(UIUtils.isValidKeyId(abnormalitydetails)){
	    			abnList=JSONArray.fromString(abnormalitydetails);
		 	   		AbnormalityList=(List<AbnTlAbnormality>)UIUtils.convertJSONArrToList(newAbnTlAbnormality, abnList);
		 			if(!UIUtils.isValidKeyId (newAbnTlAbnormality.getAbnmKeyid())){
		 			AbnormalityList =abnService.Multiplecreate(AbnormalityList,newAbnormalityBean,flid,sectionId,createdBy,Ism,docID,types,remarks);
		 		    updateMsg="Data Saved Successfully"; 
		 		   List<String> abnormalityKeyIds = AbnormalityList.stream()
		 		            .map(AbnTlAbnormality::getAbnmKeyid)   // get each keyid
		 		            .filter(Objects::nonNull)               // avoid nulls
		 		            .collect(Collectors.toList());
		 		   
		 		  if (Keyids == null) {
		 			 Keyids = new ArrayList<>();
		 			}

		 		 Keyids.addAll(abnormalityKeyIds);

		 			// Remove duplicates if needed
		 		Keyids = Keyids.stream().distinct().collect(Collectors.toList());

		 		    // ✅ Store in HTTP session
		 		  // httpSession.setAttribute("abnormalityKeyIds", Keyids);
		 		  httpSession.setAttribute(flid, Keyids);
		 		  
		 		    CommonMessage.debugMsg("Stored Abnormality Key IDs in session: " + abnormalityKeyIds);
		 		   //int i=0;
		 		    CommonMessage.debugMsg("GET:"+AbnormalityList.get(0).getAbnmKeyid());
		 		     abnmKeyid=AbnormalityList.get(0).getAbnmKeyid();
		 		   CommonMessage.debugMsg("abnmKeyid:"+abnmKeyid);
		 		    
		 			}
		 			else{
		 				CommonMessage.debugMsg("Inside the Update");
		 				updateMsg="Data Updated Successfully"; 
		 			}
		 		//	String AbnId=newAbnTlAbnormality.getAbnmKeyid();
		 			//CommonMessage.debugMsg("The Abnormality Id:::"+AbnId);
		 			
		 		    JSONObject SuccessData=new JSONObject();
		 	    	SuccessData.put("msg",updateMsg);
		 	    	returnData.put("keyId",abnmKeyid);
		 	    	returnData.put("formClear", false);
		 	    	returnData.put("successData", SuccessData);
		 	    
		 		}
		 		
				if(UIUtils.isValidKeyId(abnactnpln)){
					CommonMessage.debugMsg("Abnormality:::"+abnactnpln);
					returnData.put("RowId",Rowid);
					returnData.put("abnactnpln",true);
					//returnData.put("formClear",false);
					 clrVal=false;
					 int row_id =Integer.parseInt(Rowid) -1 ;
					 
					 returnData.put("keyId",AbnormalityList.get(row_id).getAbnmKeyid());
					 returnData.put("flid",AbnormalityList.get(row_id).getAbnmFlid());
					 returnData.put("mainTask",AbnormalityList.get(row_id).getAbnmDescription());
//					returnData.put("keyId",newAbnTlAbnormality.getAbnmKeyid());	
				}else
				{ 
					//returnData.put("MomDtlkeyid","");
					returnData.put("RowId","");
					returnData.put("formClear",false);
					returnData.put("abnactnpln", false);
					
				}
				if(UIUtils.isValidKeyId(openfilemgr)){
					CommonMessage.debugMsg("Inside the Openfile manager");
					returnData.put("openfilemgr",true);
				//	returnData.put("formClear",false);
				    returnData.put("flid",newAbnTlAbnormality.getAbnmFlid());
				    clrVal=false;
				}
				else{
					returnData.put("openfilemgr",false);
				}
				
				out.print(returnData.toString());
		 		
		 		
		 	}			
			catch(BusinessApplicationExceptions e)
			{
    			e.printStackTrace();
    			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"AbnormalityCreation");
    			CommonMessage.debugMsg("errMessage::"+errMessage);
    			if("UK_ABN_TL_ABNORMALITY".equals(errMessage))
			{
				CommonMessage.debugMsg("This Record Already Exist");
				errMessage.put("tpmException","This Record Already Exist");				
			}if("ACTIONPLAN_PENDING".equals(errMessage)){
				CommonMessage.debugMsg("Complete all Action Plans to complete Abnormality");
    			errMessage.put("tpmException","Complete all Action Plans to complete Abnormality");
			}
    			errMessage.put("displyMsg", true);			
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
	
	
	
	//---------------------------Delete----------------------//
	private void deleteAbn(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	if( httpSession != null && user != null)
    	{	
    		String abnKeyid = request.getParameter("AbnId");

    		if( UIUtils.isValidKeyId(abnKeyid))
    		{
	    		AbnTlAbnormality existAbnTlAbnormality = (AbnTlAbnormality)httpSession.getAttribute("abnTlAbnormalityAbnServlet");    		 
	    		
	    		AbnTlAbnormality newAbnTlAbnormality = new AbnTlAbnormality();  
	    		AbnTlDtl newAbnTlDtl= new AbnTlDtl(); 
	    		AbnormalityBean newAbnormalityBean = new AbnormalityBean();
	    		
	    		newAbnTlAbnormality.setAbnmCreatedby(user.getUsrm_ccno());    		
	    		
	    		newAbnTlAbnormality =(AbnTlAbnormality)UIUtils.setBeanProperties((Object)newAbnTlAbnormality,request);
	    		newAbnTlDtl =(AbnTlDtl)UIUtils.setBeanProperties((Object)newAbnTlDtl,request);
	        	
	    		newAbnormalityBean =(AbnormalityBean) UIUtils.setBeanProperties((Object)newAbnormalityBean,request);
	    		AbnormalityBean abnormalityFormBean = (AbnormalityBean)httpSession.getAttribute("abnormalityBean");	
	    		CommonMessage.debugMsg("Mode in Delete="+abnormalityFormBean.getFormActionMode());
	    		
	    		if(newAbnTlDtl != null)
	    		{
	    			newAbnTlAbnormality.setAbnTlDtl(newAbnTlDtl);
	    		}
	    		
				try
				{

					Boolean displayMsg =false;
					JSONObject successData = new JSONObject();
					String deleteMsg="abnsuccess-delete";
					Boolean clrVal=null;

					if(!(abnormalityFormBean.getFormActionMode().equals("Completed"))&&
							!(abnormalityFormBean.getFormActionMode().equals("View"))&&
							!(abnormalityFormBean.getFormActionMode().equals("Removal")))
					{
						existAbnTlAbnormality = abnService.delete(newAbnTlAbnormality);						
						displayMsg=true;
						httpSession.removeAttribute("abnTlAbnormalityAbnServlet");
					}
						
					 if(abnormalityFormBean.getFormActionMode().equals("Completed"))
					{	displayMsg=true;deleteMsg = "abnexp-delete"; clrVal=false; }
					
					 else if(abnormalityFormBean.getFormActionMode().equals("View"))
					{  displayMsg=true;deleteMsg = "abnvw-delete"; clrVal=false; }
					 
					 else if(abnormalityFormBean.getFormActionMode().equals("Removal"))
						{displayMsg=true;deleteMsg = "abnrmvl-delete"; clrVal=false; }
					 
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",deleteMsg));
					successData.put("keyid", abnKeyid); 
					successData.put("mode",abnormalityFormBean.getFormActionMode());
					JSONObject returnData = new JSONObject();
					returnData.put("formClear",clrVal);
					returnData.put("displyMsg", displayMsg);
					returnData.put("successData", successData);
					out.print(returnData.toString());
				}
				catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "AbnormalityCreation");
					out.print(errMessage.toString());
				}
				catch(BusinessApplicationExceptions e)
				{

					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"AbnormalityCreation");
					out.print(errMessage.toString());
				}
				catch(Exception e)
				{

					JSONObject tpmException = new JSONObject();
					JSONObject err = new JSONObject();
					tpmException.put("msg", "Data Not Deleted");
					tpmException.put("errMsg" , e.getMessage());
					err.put("tpmException",tpmException);
					out.print(err.toString());
				}
    		}
    	}	
    }
private AbnTlAbnormality setAbnValues(WomTlWomst womTlWomst,AbnTlAbnormality newAbnTlAbnormality,HttpServletRequest request) 
{
		String dateTime = CommonFunctions.dateTimeNow();
		String currentDate = CommonFunctions.getDate();
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAssemblyid()))
			newAbnTlAbnormality.setAbnmAssemblyid(womTlWomst.getWomsAssemblyid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCellid()))
			newAbnTlAbnormality.setAbnmCellid(womTlWomst.getWomsCellid());	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsProblem()))
			newAbnTlAbnormality.setAbnmDescription(womTlWomst.getWomsProblem());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsBookingremarks()))
			newAbnTlAbnormality.setAbnmDetailedesc(womTlWomst.getWomsBookingremarks().replace("{", "").replace("}",""));
		if(UIUtils.isValidKeyId(womTlWomst.getWomsOccurreddate()))
			newAbnTlAbnormality.setAbnmDetectiondate(womTlWomst.getWomsOccurreddate());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMachineid()))
			newAbnTlAbnormality.setAbnmEquipmentid(womTlWomst.getWomsMachineid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAllottedremarks()))
			newAbnTlAbnormality.setAbnmRemarks(womTlWomst.getWomsAllottedremarks().replace("{", "").replace("}",""));
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSectionid()))
			newAbnTlAbnormality.setAbnmSectionid(womTlWomst.getWomsSectionid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSectionid()))
			newAbnTlAbnormality.setAbnmTargetdate(womTlWomst.getWomsReporteddate().substring(0,11));
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSectionid()))
			newAbnTlAbnormality.setAbnmTargetremarks(womTlWomst.getWomsAllottedremarks().replace("{", "").replace("}",""));	
	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsReportedby()))
			newAbnTlAbnormality.setAbnmDetectedby(womTlWomst.getWomsReportedby());
			
		if(UIUtils.isValidKeyId(womTlWomst.getWomsRelatedto()))
			newAbnTlAbnormality.setAbnmRelatedto(womTlWomst.getWomsRelatedto());
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsProblem()))
			newAbnTlAbnormality.setAbnmDescription(womTlWomst.getWomsProblem());
			
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMouldid()))	
			newAbnTlAbnormality.setAbnmMould(womTlWomst.getWomsMouldid());	
		

		if(UIUtils.isValidKeyId(womTlWomst.getWomsKeyid()))
			newAbnTlAbnormality.setAbnmWomasterid(womTlWomst.getWomsKeyid());
		


		if(UIUtils.isValidKeyId(womTlWomst.getWomsTradeid()))
			newAbnTlAbnormality.setAbnmTradeid(womTlWomst.getWomsTradeid());
		
		HttpSession httpSession = request.getSession(false);
		AbnormalityBean abnormalityFormBean = (AbnormalityBean)httpSession.getAttribute("abnormalityBean");
    	
		String dd =womTlWomst.getWomsOccurreddate().substring(12, 17);
		abnormalityFormBean.setAbnmDetectedbytime(dd);  
		
		httpSession.setAttribute("abnormalityBean", abnormalityFormBean);	
			
		return newAbnTlAbnormality;
		
	}
	
}

