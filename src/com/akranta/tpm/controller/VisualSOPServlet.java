package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.OplFormBean;
//import com.akranta.tpm.bean.SlaFormBean;
import com.akranta.tpm.bean.VsopFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.JhaTlVisualsopdtl;
import com.akranta.tpm.model.JhaTlVisualsopmst;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.VisualSOPService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.VisualSOPServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;


public class VisualSOPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VisualSOPService visualSOPService;
	private CommonFilterService commonFilterService;
	CommonFilter commonFilter;
	public VisualSOPServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		CommonMessage.debugMsg(" action " + action);
		visualSOPService = (VisualSOPServiceImpl) UIUtils.getServiceObject(request, "VisualSOPServiceImpl");
		commonFilterService=(CommonFilterServiceImpl)UIUtils.getServiceObject(request, "CommonFilterServiceImpl");
		visualSOPService.VisualSOPServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		if (action.equals("VisualSop_view.VisualSop")) {
			request.setAttribute("formtype", "Entry");
			 request.getSession(false).setAttribute("VisualSopMode", "MODIFY"); // ✅
			UIUtils.forwardRequest(request, response, "/pages/VisualSopReport.jsp");
		}else if (action.equals("VisualSopApproval_view.VisualSop")) {
			request.setAttribute("formtype", "Approval");
			request.getSession(false).setAttribute("VisualSopMode", "APPROVAL"); // ✅
			UIUtils.forwardRequest(request, response, "/pages/VisualSopReport.jsp");
		}
		else if (action.equals("VisualSop_getCol.VisualSop")){
			visualsopReportGetCol(request,response);
		}else if( action.equals("getModeVisualSop_view.VisualSop")){
			CommonMessage.debugMsg("Action ::: Mode "+action);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("mode","create");
			result.put("url","VisualSopDetailUpdate_input.VisualSop");
			result.put("formHeader","Visual SOP");
			out.println(result);
		}else if (action.equals("VisualSop_getData.VisualSop")){
			visualsopReportGetData(request,response);
		}else if( action.equals("VisualSop_getExcel.VisualSop")){
			getVisualSopReportExcel(request,response);
		}else if (action.equals("VisualSopDetailUpdate_input.VisualSop")){
			visualsopReportUpdate(request,response);
		}else if (action.equals("VisualSopDetail_getCol.VisualSop")){
			visualsopDetailReportGetCol(request,response);
		}else if (action.equals("VisualSopDetail_getData.VisualSop")){
			visualsopDetailReportGetData(request,response);
		}else if( action.equals("VisualSopDetail_getExcel.VisualSop")){
			getVisualSopDetailReportExcel(request,response);
		}else if( action.equals("functionalLoc.VisualSop")){
			getFunctionalLocation(request,response);
		}else if (action.equals("PPEDetail_getCol.VisualSop")){
			PPEDetailGetCol(request,response);
		}else if (action.equals("PPEDetail_getData.VisualSop")){
			PPEDetailGetData(request,response);
		}
		// ///For popup
		else if (action.equals("visualSOPDetailAdd_view.VisualSop")) {
			visualsopDetailUpdate(request,response);
		}else if (action.equals("VisualSopDetailUpdate_save.VisualSop")) {
			saveVisualSOPmst(request,response);	
		}else if (action.equals("VisualSoppopDetail_save.VisualSop")){
		   	saveVisualSOPDetail(request,response);	
		}else if (action.equals("VisualSopDetailUpdate_delete.VisualSop")){
		   	deleteVisualSOPmst(request,response);
		}
		else if(action.equals("trademst.VisualSop"))
		{
			try 
			{   ComboFilter currentFilter = new ComboFilter();
				currentFilter = UIUtils.fillComboFilter(request);				
				List<ComboBox>  companyid = visualSOPService.gettradecombo(currentFilter);
				UIUtils.writeComboBox(response, companyid,currentFilter);
			} 
		catch (Exception e) 
			{
				
				e.printStackTrace();
			}
		}
		else if( action.equals("updateApprovedStatusLevel.VisualSop"))
		{   
			JhaTlVisualsopmst newJhaTlVisualsopmst=new JhaTlVisualsopmst();
			String status=request.getParameter("status");
			String nextLevel=request.getParameter("nextLevel");
			String keyid=request.getParameter("keyid");
			CommonMessage.debugMsg("status===="+status);
			newJhaTlVisualsopmst = visualSOPService.updateApprovedStatusLevel(status,keyid, nextLevel);
			
			//saveOpl(request,response,oplFormBean);
	    }
		else if (action.equals("VisualSoppopDetail_delete.VisualSop")) {
		   	deleteVisualSOPDetail(request,response);
		}
		else if (action.equals("VisualSopReport_input.VisualSop")) {	
			 request.getSession(false).setAttribute("VisualSopMode", "REPORT"); // ✅
			request.setAttribute("formtype", "Report");
			UIUtils.forwardRequest(request, response, "/pages/VisualSopReport.jsp");
		}
		else if( action.equals("vsopReport_Excelview.VisualSop") )
		{	
			try{
				CommonFilter commonFilter = new CommonFilter();					
				String vsopId = request.getParameter("vsopId");
				CommonMessage.debugMsg("vsopId::::::"+vsopId);
				
				String format = ExcelUtils.getFormat(request);
				commonFilter = FilterValues.getCommonFilters(request, commonFilter);
				//commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
				
				String path = UIUtils.getExcelTemplatePath(request);  	
				String imagePath = UIUtils.getImagePath(request);
				CommonMessage.debugMsg("vsopId imagePath::::::"+imagePath);
				format = ExcelUtils.getFormat(request);
				CommonMessage.debugMsg(vsopId +" Excel format   " + format +" Excel format   " + path+" Excel format   " + imagePath +" Excel format   " + format);
				format = "xlsx";
				Workbook wb = visualSOPService.VsopExportExcel(vsopId, format, path, imagePath, commonFilter);	 
				ExcelUtils.writeToResponse(response, wb, "Visual_SOP_Report", format);
				}
			
				catch(Exception e)
				{
					CommonMessage.debugMsg("err:"+e.getMessage());
					PrintWriter out = response.getWriter();
					JSONObject err = new JSONObject();				
					//err.put("exception",true);				
					err.put("message" ,"Data Not Found" );
					out.print(err.toString());
				}			
			}
}

private void visualsopReportUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception {
	String frmMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
	String keyId = request.getParameter("keyId");
	String formtype = request.getParameter("formtype");
	FormModes mode = FormModes.create;
	CommonMessage.debugMsg("frmMode ="+frmMode + " mode " + mode);
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	JhaTlVisualsopmst newJhaTlVisualsopmst=new JhaTlVisualsopmst();
	if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.view) )
		mode = FormModes.view;
	else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.modify) )
		mode = FormModes.modify;
	if(UIUtils.isValidKeyId(frmMode)){
		if (frmMode.equals("MODIFY")) {
			newJhaTlVisualsopmst=visualSOPService.getAllFillControl(keyId);
		}
	}if(!UIUtils.isValidKeyId(keyId)){
		newJhaTlVisualsopmst.setVsomApprovedby( user.getUsrm_ccno());
		newJhaTlVisualsopmst.setVsomPreparedby( user.getUsrm_ccno());
		newJhaTlVisualsopmst.setVsomIssuedby(user.getUsrm_ccno());
	}
	
	
	VsopFormBean vsopFormBean = new VsopFormBean("");

	if ("Report".equals(formtype))
	{
		vsopFormBean = new VsopFormBean("Report");
		CommonMessage.debugMsg("Inside Report action "+formtype);
		vsopFormBean.setDisableForRpt("true");			
	}
	else if ("Approval".equals(formtype))
	{
		vsopFormBean = new VsopFormBean("Report");
		CommonMessage.debugMsg("Inside Report action "+formtype);
		vsopFormBean.setDisableForRpt("true");
	}
	
	else 
		vsopFormBean.setDisableForRpt("false");
	
	CommonMessage.debugMsg("requestScope.vsopFormBean.disableForrpt"+vsopFormBean.getDisableForRpt());
	request.setAttribute("VsopFormBean",vsopFormBean);	
	
	request.setAttribute("newJhaTlVisualsopmst", newJhaTlVisualsopmst);
	request.setAttribute("mode", mode);
	CommonMessage.debugMsg("mode"+mode);
	request.setAttribute("formtype", formtype);
	UIUtils.forwardRequest(request, response, "/pages/Visualsop.jsp");
}
private void visualsopDetailUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception {
	String detKeyId = request.getParameter("detKeyId");
	String frmMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
	String keyId = request.getParameter("keyId");
	FormModes mode = FormModes.create;
	CommonMessage.debugMsg("frmMode ="+frmMode + " mode " + mode);
	if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.view) )
		mode = FormModes.view;
	else if(frmMode != null && frmMode.equalsIgnoreCase(FormModeConsts.modify) )
		mode = FormModes.modify;
	
	if (frmMode.equals("MODIFY")) {
		JhaTlVisualsopdtl newJhaTlVisualsopdtl=new JhaTlVisualsopdtl();	
		newJhaTlVisualsopdtl=visualSOPService.getAllFillControlDtl(detKeyId);
		String fileName=UIUtils.TPM_TEMPIMG_DIR;
		newJhaTlVisualsopdtl.setVsodImgtoolused(fileName+newJhaTlVisualsopdtl.getVsodImgtoolused());
		request.setAttribute("newJhaTlVisualsopdtl", newJhaTlVisualsopdtl);
		
	}
	request.setAttribute("mode", mode);
	request.setAttribute("mstKeyId", keyId);
	UIUtils.forwardRequest(request, response, "/pages/VisualSopDetail.jsp");
}
private void visualsopReportGetCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
		CommonMessage.debugMsg("VisualSOP Report Getcol" );
		PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession(false);
		JSONObject jsonObject = new JSONObject();
		List<String[]> visualSopGrid = null;
		try {
		commonFilter = populateCommonFilter(request,"VisualSopCommonFilter",true);
		commonFilter.setIsGetCol("Y");
		visualSopGrid = visualSOPService.getAllVisualSopDetailReport(commonFilter);
		} catch (Exception e) {
		e.printStackTrace();
		}
		jsonObject = getTableModelForVisualsopReport(visualSopGrid);
		httpSession.removeAttribute("vsopColModel");
		httpSession.setAttribute("vsopColModel", jsonObject);
		out.println(jsonObject);
}
private void visualsopReportGetData(HttpServletRequest request,	HttpServletResponse response) throws IOException{
		try {
		CommonFilter commonFilter = new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		commonFilter = populateCommonFilter(request,"VisualSopCommonFilter",false);
		

        // ✅ Read mode from session (set during view load)
        HttpSession httpSession = request.getSession(false);
        String mode = (String) httpSession.getAttribute("VisualSopMode");
        
//     // ✅ No need to read from user object
//        if (UIUtils.isValidKeyId(commonFilter.getRoleLevel()))
//            commonFilter.setRoleLevel(commonFilter.getRoleLevel()); // already set, nothing extra needed

        if ("APPROVAL".equals(mode))
            commonFilter.setMainGroup("APPROVAL");
        else if ("MODIFY".equals(mode))
            commonFilter.setMainGroup("MODIFY");
        else if ("REPORT".equals(mode))
            commonFilter.setMainGroup("REPORT");
		
		String location = CommonFunctions.getLoginLocaton(request);
		if (commonFilter.getLocation() != null)
		{
			commonFilter.getLocation().setId(location);
		}else
		{  
			ComboFilter  locationCmb = new ComboFilter();
			locationCmb.setId(location);
			commonFilter.setLocation(locationCmb);
		}
		commonFilter.setIsGetCol("N");
		List<String[]> sopReportGrid = visualSOPService.getAllVisualSopDetailReport(commonFilter);
		PrintWriter out = response.getWriter();
		JSONObject sopReportGridmod = UIUtils.convertToJqGridTableObject(sopReportGrid, request, 2,1);// changed 09-march
		out.println(sopReportGridmod);
		} catch (Exception e) {
		CommonMessage.debugMsg(e.getMessage());
		}
}
private void getVisualSopReportExcel(HttpServletRequest request,HttpServletResponse response)  throws Exception {
	HttpSession httpSession = request.getSession(false);
	CommonFilter	commonFilter = populateCommonFilter(request,"VisualSopCommonFilter",false);
	String tmpFromRow = commonFilter.getFromRow();
	commonFilter.setFromRow(null);
	httpSession = request.getSession(false);
	JSONObject colmodel = (JSONObject) httpSession.getAttribute("vsopColModel");
	colmodel.put("title","Visual SOP Report");
    String format = ExcelUtils.getFormat(request);
	Workbook wb = visualSOPService.visualSOPExportExcel(commonFilter,colmodel,format);
	commonFilter.setFromRow(tmpFromRow);
	ExcelUtils.writeToResponse(response,wb,"Visual_SOP_Report", format);
}

private void PPEDetailGetCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
	PrintWriter out = response.getWriter();
	JSONObject jsonObject = new JSONObject();
	HttpSession httpSession = request.getSession(false);
	List<String[]> ppeGrid = null;
	String fileName=UIUtils.TPM_TEMPIMG_DIR;
	
	try {
		commonFilter = populateCommonFilter(request,"VisualSopDetailCommonFilter",true);
		ppeGrid = visualSOPService.getAllPPEDetail(fileName);
	} catch (Exception e) {
		e.printStackTrace();
	}
	jsonObject = getTableModelForPPE(ppeGrid);
	httpSession.removeAttribute("");
	httpSession.setAttribute("", jsonObject);
	
	out.println(jsonObject);	
}
private void PPEDetailGetData(HttpServletRequest request,HttpServletResponse response) throws Exception{
	try {
		
		String fileName=UIUtils.getImagePath(request);
		CommonMessage.debugMsg("PPEDetailGetData fileName" +fileName);
		FilterValues.getCommonFilters(request, commonFilter);
		commonFilter = populateCommonFilter(request,"VisualSopDetailCommonFilter",false);
		List<String[]> ppeGrid = visualSOPService.getAllPPEDetail(fileName);
		PrintWriter out = response.getWriter();
		JSONObject sopReportGridmod = UIUtils.convertToJqGridTableObject(ppeGrid, request, 1,0);
		out.println(sopReportGridmod);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}
	
	/*List<String[]> PPEGrid = visualSOPService.getAllPPEDetail();
	PrintWriter out = response.getWriter();
	JSONObject ppeGridmod = UIUtils.convertToJqGridTableObject(PPEGrid, request, 2,0);
	out.println(ppeGridmod);
	} catch (Exception e) {
		CommonMessage.debugMsg(e.getMessage());
	}*/


private void visualsopDetailReportGetCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		HttpSession httpSession = request.getSession(false);
		List<String[]> visualSopGrid = null;
		String fileName=UIUtils.getImagePath(request);
		CommonMessage.debugMsg("visualsopDetailReportGetCol fileName" + fileName);
		String keyId = request.getParameter("keyId");
		try {
			commonFilter = populateCommonFilter(request,"VisualSopDetailCommonFilter",true);
			visualSopGrid = visualSOPService.getAllVisualSopDetail(keyId,fileName,commonFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject = getTableModelForVisualsop(visualSopGrid);
		httpSession.removeAttribute("vsopDetailColModel");
		httpSession.setAttribute("vsopDetailColModel", jsonObject);
		httpSession.setAttribute("keyId", keyId);
		out.println(jsonObject);
}
private void visualsopDetailReportGetData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
		String fileName=UIUtils.TPM_TEMPIMG_DIR;
		String keyId = request.getParameter("keyId");
		FilterValues.getCommonFilters(request, commonFilter);
		commonFilter = populateCommonFilter(request,"VisualSopDetailCommonFilter",false);
		List<String[]> sopReportGrid = visualSOPService	.getAllVisualSopDetail(keyId,fileName,commonFilter);
		PrintWriter out = response.getWriter();
		JSONObject sopReportGridmod = UIUtils.convertToJqGridTableObject(sopReportGrid, request, 1,0);
		out.println(sopReportGridmod);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
}
private void getVisualSopDetailReportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
	HttpSession httpSession = request.getSession(false);
	CommonFilter	commonFilter = populateCommonFilter(request,"VisualSopDetailCommonFilter",false);
	String tmpFromRow = commonFilter.getFromRow();
	commonFilter.setFromRow(null);
	String KeyId=	(String)httpSession.getAttribute("keyId");
	httpSession = request.getSession(false);
	JSONObject colmodel = (JSONObject) httpSession.getAttribute("vsopDetailColModel");
	colmodel.put("title","Visual SOP Detail Report");
    String format = ExcelUtils.getFormat(request);
	commonFilter.setVisualKeyId(KeyId);
	Workbook wb = visualSOPService.visualSOPDetailExportExcel(commonFilter,colmodel,format);
	commonFilter.setFromRow(tmpFromRow);
	ExcelUtils.writeToResponse(response,wb,"Visual_SOP_Detail_Report", format);
}
private void getFunctionalLocation(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
		functLocFieldNameBean.setFactory("cmbVsomFactoryid");
		functLocFieldNameBean.setSection("cmbVsomSectionid");
		functLocFieldNameBean.setCell("cmbVsomCellid");
		functLocFieldNameBean.setMachine("cmbVsomMachineid");
		functLocFieldNameBean.setFunctionalLocId("cmbVsomFlnid");
		functLocFieldNameBean.setFactMandatory(false);
		functLocFieldNameBean.setSectMandatory(false);
		functLocFieldNameBean.setCellMandatory(true);
		functLocFieldNameBean.setMachMandatory(false);
	    FormModes formModes = FormModes.create;
        UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
}


private void saveVisualSOPmst(HttpServletRequest request,HttpServletResponse response) throws IOException,ValidationExceptions{
		String type=request.getParameter("Type");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		if( httpSession != null && user != null)
		{  
		JhaTlVisualsopmst newJhaTlVisualsopmst=new JhaTlVisualsopmst();	
		JhaTlVisualsopmst existJhaTlVisualsopmst=(JhaTlVisualsopmst)httpSession.getAttribute("newJhaTlVisualsopmst");
		newJhaTlVisualsopmst=(JhaTlVisualsopmst)UIUtils.setBeanProperties((Object)newJhaTlVisualsopmst,request);
		newJhaTlVisualsopmst.setVsomCreatedby(user.getUsrm_ccno());
		JSONObject successData=new JSONObject();
		JSONObject returnData=new JSONObject();
		String savemsg;	
			try
			{
			 if(newJhaTlVisualsopmst.getVsomKeyid()==null){
				 existJhaTlVisualsopmst=visualSOPService.create(newJhaTlVisualsopmst, existJhaTlVisualsopmst);
				 savemsg=" Data Saved Succesfully";
			 }else{
				 existJhaTlVisualsopmst=visualSOPService.update(newJhaTlVisualsopmst, existJhaTlVisualsopmst);
				 savemsg= "Data Updated Succesfully";
			}
        	successData.put("msg", savemsg);
        	successData.put("vsomKeyid", existJhaTlVisualsopmst.getVsomKeyid());
        	successData.put("type", type);
        	returnData.put("successData",successData);
        	CommonMessage.debugMsg("Type....."+type);
        	if(type == "dtlPop")
        	{
        		  returnData.put("formClear",false);
        	}else if(type == "fileMng")
        	{
        		returnData.put("formClear",false);
        	}
        	else
        	{
        		returnData.put("formClear",false);
        	}
//        	if(!UIUtils.isValidKeyId(type)){
//        	returnData.put("formClear",true);
//        	}
    		out.println(returnData.toString());
    		}catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "VisualSopCreation");
			out.print(errMessage.toString());
    		}catch(Exception e){
    		}
		
		}
}
private void saveVisualSOPDetail(HttpServletRequest request,HttpServletResponse response) throws IOException,BusinessApplicationExceptions,ValidationExceptions{
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		if( httpSession != null && user != null){  
		JhaTlVisualsopdtl newJhaTlVisualsopdtl=new JhaTlVisualsopdtl();	
		JhaTlVisualsopdtl existJhaTlVisualsopdtl=(JhaTlVisualsopdtl)httpSession.getAttribute("newJhaTlVisualsopdtl");
		newJhaTlVisualsopdtl=(JhaTlVisualsopdtl)UIUtils.setBeanProperties((Object)newJhaTlVisualsopdtl,request);
		newJhaTlVisualsopdtl.setVsodCreatedby(user.getUsrm_ccno());
		JSONObject successData=new JSONObject();
		JSONObject returnData=new JSONObject();
		
		/** For Saving Image **/
		String imgVsodImgtoolusedFilename1= request.getParameter("imgVsodImgtoolusedFilename");
		String toremove = "tmp/images/";
		String regex = java.util.regex.Pattern.quote(toremove);
		String imgVsodImgtoolusedFilename = imgVsodImgtoolusedFilename1.replaceAll(regex, "");
		
		CommonMessage.debugMsg("imgOplPresentImgFilename:::::::="  +  imgVsodImgtoolusedFilename);
		String imagePath = UIUtils.getImagePath(request);
		CommonMessage.debugMsg("imagePath:::::="+imagePath);
		List <GenTlAllmoduleimgfile> imgfileList =new ArrayList<GenTlAllmoduleimgfile>();
		GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
		if( imgVsodImgtoolusedFilename != null || imgVsodImgtoolusedFilename !="no-image"){
			genTlAllmoduleimgfile.setImflBlobimage(imagePath );
			genTlAllmoduleimgfile.setImflFilename(imgVsodImgtoolusedFilename);
			genTlAllmoduleimgfile.setImflImagetype("TOL");
			genTlAllmoduleimgfile.setImflRefdoctype("VSP");
			imgfileList.add(genTlAllmoduleimgfile);
			newJhaTlVisualsopdtl.setVsodImgtoolused(genTlAllmoduleimgfile.getImflFilename());
		}
		//genTlAllmoduleimgfile.setAllmoduleimgfile(imgfileList);
		/**---------- **/
		String savemsg;	
			try
			{
			 if(newJhaTlVisualsopdtl.getVsodKeyid()==null){
				 
				 existJhaTlVisualsopdtl=visualSOPService.create(newJhaTlVisualsopdtl, existJhaTlVisualsopdtl);
				 commonFilterService.saveImg(imgfileList, existJhaTlVisualsopdtl.getVsodKeyid(),genTlAllmoduleimgfile.getImflRefdoctype() );
				 savemsg="Data saved successfully";
			 }else{
				 if(newJhaTlVisualsopdtl.getVsodImgtoolused()=="" && imgVsodImgtoolusedFilename=="");{
					 visualSOPService.deleteForImg(newJhaTlVisualsopdtl.getVsodKeyid());
			     }
				 commonFilterService.saveImg(imgfileList, newJhaTlVisualsopdtl.getVsodKeyid(),genTlAllmoduleimgfile.getImflRefdoctype() );
				 existJhaTlVisualsopdtl=visualSOPService.update(newJhaTlVisualsopdtl, existJhaTlVisualsopdtl);
				 savemsg= "Data updated successfully";
			 }
			 successData.put("msg", savemsg);
        	 returnData.put("successData",successData);
        	 returnData.put("formClear",false);
    		 out.println(returnData.toString());
		    }catch (ValidationExceptions e){
				CommonMessage.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "VisualSopDetailCreation");
				out.print(errMessage.toString());
    	    }catch (BusinessApplicationExceptions e){
    			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "VisualSopDetailCreation");
    			out.print(errMessage.toString());
    			CommonMessage.debugMsg(" e " + errMessage );
    	    }catch(Exception e){
		    }
		}
}
private void deleteVisualSOPmst(HttpServletRequest request,HttpServletResponse response) throws Exception {
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try
		{
    	if(httpSession !=null && user !=null){
    		JhaTlVisualsopmst oldJhaTlVisualsopmst=new JhaTlVisualsopmst();	
    		oldJhaTlVisualsopmst=(JhaTlVisualsopmst)UIUtils.setBeanProperties((Object)oldJhaTlVisualsopmst,request);
    		JSONObject successData=new JSONObject();
    		JSONObject visualsuccess=new JSONObject();
    		String savemsg;
			if(UIUtils.isValidKeyId(oldJhaTlVisualsopmst.getVsomKeyid())){
				oldJhaTlVisualsopmst=visualSOPService.delete(oldJhaTlVisualsopmst);
				savemsg="Data deleted successfully";
			}else{
				savemsg= "Data Not Deleted  ";
			}
    		successData.put("msg", savemsg);
    		visualsuccess.put("successData", successData);
    		
    		out.print(visualsuccess.toString());
		}
		}
		catch(Exception e)
		{
			
		}
}
private void deleteVisualSOPDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try
		{
    	if(httpSession !=null && user !=null)
		{
    		JhaTlVisualsopdtl oldJhaTlVisualsopdtl=new JhaTlVisualsopdtl();	

    		oldJhaTlVisualsopdtl=(JhaTlVisualsopdtl)UIUtils.setBeanProperties((Object)oldJhaTlVisualsopdtl,request);
    		JSONObject successData=new JSONObject();
    		JSONObject visualsuccess=new JSONObject();
    		String savemsg;
			
			if(UIUtils.isValidKeyId(oldJhaTlVisualsopdtl.getVsodKeyid())){
	    		String imgVsodImgtoolusedFilename= request.getParameter("imgVsodImgtoolusedFilename");
				oldJhaTlVisualsopdtl.setVsodImgtoolused(imgVsodImgtoolusedFilename);
				CommonMessage.debugMsg(""+oldJhaTlVisualsopdtl.getVsodImgtoolused());
				oldJhaTlVisualsopdtl=visualSOPService.delete(oldJhaTlVisualsopdtl);
				savemsg="Data deleted successfully";
			}
			else
			{
				savemsg= "Data Not Deleted  ";
			}
    		successData.put("msg", savemsg);
    		visualsuccess.put("successData", successData);
    		visualsuccess.put("formClear",false);
    		out.print(visualsuccess.toString());
		}
		}
		catch(Exception e)
		{
			
		}
}
private JSONObject getTableModelForVisualsopReport(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(1); // changed 09-march
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(300);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		String[] colIndex = headers.get(0);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", " "));
		   	jqGridColModel.setWidth(145);
			if(i==0 ||i==1){
				jqGridColModel.setHidden(true);
			}
			if(i==3){
				
				jqGridColModel.setWidth(200);
			}
			if(i==2){
				
				jqGridColModel.setWidth(300);
			}
			if(i==4){
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(300);
			}
			if(i==5){
				//jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(250);
			}
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
}
private JSONObject getTableModelForPPE(List<String[]> headers) {
	JqGridTableModel jqGridTableModel = new JqGridTableModel();
	String[] colHeader = headers.get(0);
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.setRowNumbers(true);
	jqGridTableModel.setTableHeight(100);
	jqGridTableModel.setTableWidth(500);
	jqGridTableModel.setTableButton(false);
	jqGridTableModel.setEnableFilter(false);
	String[] colIndex = headers.get(0);
	for (int i = 0; i < colHeader.length; i++) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
		jqGridColModel.setName(colIndex[i].replaceAll(" ", ""));
		
		jqGridColModel.setWidth(100);
		jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);
		
		if(i==0){
			jqGridColModel.setAlign("center");
			jqGridColModel.setWidth(50);
			jqGridColModel.setFormatter("formattercheckbox");
		}
		
		if(i==1) {
			jqGridColModel.setHidden(true);
		}
		
		if(i==2) {
			jqGridColModel.setHidden(true);
		}
		
		if(i==4){
			jqGridColModel.setAlign("center");
			jqGridColModel.setWidth(120);
			jqGridColModel.setFormatter("txtFormatterToolimg");
		}
		
		
		
		CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
		jqGridTableModel.getColModel().add(jqGridColModel);
	}
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "27%%");
	tableModel.set("tableWidth", "27%%");
	return tableModel;
}
private JSONObject getTableModelForVisualsop(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(500);
		/*jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);*/
		String[] colIndex = headers.get(0);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(250);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0){
				jqGridColModel.setHidden(true);
			}
			if(i==1){
				jqGridColModel.setWidth(240);
			}
			if(i==2){
				jqGridColModel.setWidth(150);
			}
			if(i==3){
				jqGridColModel.setWidth(130);
			}
			if(i==5){
				jqGridColModel.setAlign("center");
				jqGridColModel.setWidth(120);
				jqGridColModel.setFormatter("txtFormatter");
			}
			
			if(i==7){
				jqGridColModel.setAlign("center");
				jqGridColModel.setWidth(60);
			}
			
			if(i==6){
				jqGridColModel.setHidden(true);
			}
			if(i==8){
				jqGridColModel.setHidden(true);
			}
			/*if(i==6){
				jqGridColModel.setAlign("center");
				jqGridColModel.setWidth(120);
				jqGridColModel.setFormatter("txtFormatter");
			}*/
			CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "27%%");
		tableModel.set("tableWidth", "100%%");
		return tableModel;
		
		
		
}
private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		return commonFilter;
}

}
