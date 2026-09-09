package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
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
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.RiskBean;
import com.akranta.tpm.bean.SopBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFnlnrolemap;
import com.akranta.tpm.model.SheTlDeriskdtl;
import com.akranta.tpm.model.SheTlDeriskmst;
import com.akranta.tpm.model.SheTlRiskassessmentdtl;
import com.akranta.tpm.model.SheTlRiskassessmentmst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.RiskAssesmtService;
import com.akranta.tpm.service.impl.RiskAssesmtServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class QualityInspectionServlet
 */

public class RiskAssesmtServlet extends HttpServlet {	
	RiskAssesmtService riskAssesmtService;
    CommonFilter commonFilter;		
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	List<String[]> qualityGrid;
	//QualityInspectionService qualityInspectionService;
    public RiskAssesmtServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		
		try
		{
			riskAssesmtService=(RiskAssesmtServiceImpl)UIUtils.getServiceObject(request,"RiskAssesmtServiceImpl");
		}
		catch (ServiceObjectCreationException e)
		{
		}
		
		String action = UIUtils.getActionPart(request);	
		if (action.equals("RiskAssesment_input.risk")) {		
			CommonFunctions.debugMsg(" sds RiskAssesment_input.risk ");		
			RequestDispatcher rd = request.getRequestDispatcher("/pages/RiskAssessment/RiskAssessmentGrid.jsp");
			rd.forward(request, response);
			CommonFunctions.debugMsg(" response " + response);		
		}
		else if (action.equals("RiskAssesment_view.risk")) {		
			CommonFunctions.debugMsg(" sds RiskAssesment_input.risk ");		
			RequestDispatcher rd = request.getRequestDispatcher("/pages/RiskAssessment/RiskAssessmentGrid.jsp");
			rd.forward(request, response);
			CommonFunctions.debugMsg(" response " + response);		
		}
		else if( action.equals("getModeRiskAssesment_view.risk")){
			System.out.println("action"+action);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("mode","create");
			result.put("url","RiskAssesmentEntry_input.risk");
			result.put("formHeader","Risk Assessment");
			out.println(result);
			
		}
		else if (action.equals("RiskAssesment_getCol.risk")) {//first page
			
			PrintWriter out = response.getWriter();
			String flid = request.getParameter("flid");
			CommonFilter commonFilter = new CommonFilter();
			//commonFilter.setFlid(flid);
			JSONObject jsonObject = new JSONObject();
			List<String[]>riskListGrid = null;		
			try {						
				FilterValues.getCommonFilters(request, commonFilter);
				riskListGrid =  riskAssesmtService.getRiskList(commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}			 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);
			 
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	
			 
			 String [] colHeaderHead = riskListGrid.get(0);
			 String [] colHeader = riskListGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
	   	     jsonObject.set("tableWidth", "108%%");
	     	 jsonObject.set("tableHeight", "62%%");
	   	     httpSession.removeAttribute("riskAssessmentListColModel");
			 httpSession.setAttribute("riskAssessmentListColModel",jsonObject);	
			 CommonFunctions.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);
		} 
		
		else if (action.equals("RiskAssesment_getData.risk")) {			
			CommonFunctions.debugMsg("RiskAssesment_getData.risk");	
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"riskAssesmentListCommonFilter",true);
				FilterValues.getCommonFilters(request, commonFilter);
				String flid = request.getParameter("flid");
				//commonFilter.setFlid(flid);
				List<String[]> MasterGrid = riskAssesmtService.getRiskList(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,1,0);
				CommonFunctions.debugMsg("ResourceGridmod:"+ResourceGridmod);	
				out.println(ResourceGridmod);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}	
		}
		else if(action.equals("RiskAssesment_getExcel.risk"))
		{
			CommonFilter commonFilter = populateCommonFilter(request,"riskAssesmentListCommonFilter",false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);	
			String flid = request.getParameter("flid");
			//commonFilter.setFlid(flid);
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("riskAssessmentListColModel");
			tblJSONObj.put("title", "Risk Report");
			String format = ExcelUtils.getFormat(request);			
			Workbook wb = riskAssesmtService.RiskExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "RiskReport", format);			
		}
		
		else if( action.equals("functionalLoc.risk"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
			functLocFieldNameBean.setSection("cmbbdmsSectionid");
			functLocFieldNameBean.setCell("cmbbdmsCellid");
			functLocFieldNameBean.setMachine("cmbbdmsMachineid");			
			//functLocFieldNameBean.setFactMandatory(true);
			//functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			//functLocFieldNameBean.setMachMandatory(true);			
			FormModes formModes = null;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );				
		}		
		else if(action.equals("RiskAssesmentEntry_save.risk")){
			saveRisk(request,response);		    
		}
		else if( action.equals("RiskAssesmentEntry_delete.risk"))
		{	
			deleterisk(request,response);			
		}	
		else if( action.equals("RiskAssesmentEntry_input.risk") )
		{
			CommonFunctions.debugMsg("RiskAssesmentEntry_input.risk  ");
			String keyid =request.getParameter("keyid");
			CommonFunctions.debugMsg("keyid"+keyid);
			SheTlRiskassessmentmst sheTlRiskassessmentmst =new 	SheTlRiskassessmentmst();
			AdmTlUsermst user = UIUtils.getLoginUser(request);			
			sheTlRiskassessmentmst.setRasmPreparedby(user.getUsrm_ccno());
			sheTlRiskassessmentmst.setRasmDate(CommonFunctions.dateTimeNow());
			if(UIUtils.isValidKeyId(keyid)){
				sheTlRiskassessmentmst = riskAssesmtService.select(keyid);		
			}	
			sheTlRiskassessmentmst.setRasmDate(UIUtils.getActualDateForm(sheTlRiskassessmentmst.getRasmDate()));
			request.setAttribute("sheTlRiskassessmentmst" , sheTlRiskassessmentmst);
			//httpSession.setAttribute(" sheTlRiskassessmentmst", sheTlRiskassessmentmst);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/RiskAssessment/RiskAssesment.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("RiskAssesmentEntry_getCol.risk") )
		{
			/*CommonFunctions.debugMsg("RiskAssesmentEntry_getCol.risk");
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter =null;
			JSONObject jsonObject = new JSONObject();
			List<String[]>riskListGrid = null;		
			try {
				commonFilter = populateCommonFilter(request,"riskAssesmentCommonFilter",true);
				FilterValues.getCommonFilters(request, commonFilter);
				riskListGrid =  riskAssesmtService.getRiskDtlsList(commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}			 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);	
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(false);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);			 
			 String [] colHeaderHead = riskListGrid.get(0);
			 String [] colHeader = riskListGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
	   	     jsonObject.set("tableWidth", "107%%");
	     	 jsonObject.set("tableHeight", "39%%");
	   	     httpSession.removeAttribute("riskCtrColModel");
			 httpSession.setAttribute("riskCtrColModel",jsonObject);	
			 CommonFunctions.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);*/
			try{
				PrintWriter out = response.getWriter();
				CommonFunctions.debugMsg("RiskAssesment_getCol.risk.............");
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.RiskAssesment","riskassesmentdtls"));
				CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RiskAssesment","riskassesmentdtls")); 
				CommonFunctions.debugMsg("RiskAssesment_getCol.risk  end "); 
			}catch(Exception e){
				CommonFunctions.debugMsg("RiskAssesment_getCol.risk Exception" + e.getMessage());
				e.printStackTrace();
			}			 
		}
		else if( action.equals("RiskAssesmentEntry_getData.risk") )
		{
			try {
				CommonFunctions.debugMsg("RiskAssesmentEntry_getData.risk");
				CommonFilter commonFilter = new CommonFilter();
				String keyid =request.getParameter("keyid");
				CommonFunctions.debugMsg("keyid"+keyid);
				commonFilter.setKey(keyid);
				PrintWriter out = response.getWriter();
				List<String[]> RskAssActList = riskAssesmtService.getRiskDtlsList(commonFilter);
				JSONObject RskAssActData = UIUtils.convertToJqGridTableObject(RskAssActList, request, 2, 0);
				out.println(RskAssActData);
			} catch (Exception e) {
				CommonFunctions.debugMsg("RiskAssesmentEntry_getData.risk Exception" + e.getMessage());
				e.printStackTrace();
			}
		}		
		else if (action.equals("DeRiskAssesment_input.risk")) {		
			CommonFunctions.debugMsg(" sds RiskAssesment_input.risk ");		
			RequestDispatcher rd = request.getRequestDispatcher("/pages/RiskAssessment/DeRiskGrid.jsp");
			rd.forward(request, response);
			CommonFunctions.debugMsg(" response " + response);		
		}
		else if (action.equals("DeRiskAssesment_view.risk")) {		
			CommonFunctions.debugMsg(" sds RiskAssesment_input.risk ");		
			RequestDispatcher rd = request.getRequestDispatcher("/pages/RiskAssessment/DeRiskGrid.jsp");
			rd.forward(request, response);
			CommonFunctions.debugMsg(" response " + response);		
		}
		else if( action.equals("getModeDeRiskAssesment_view.risk")){
			CommonFunctions.debugMsg("Action ::: Mode "+action);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("mode","create");
			result.put("url","DeRiskAssesmentEntry_input.risk");
			result.put("formHeader","De-Risk Assessment");
			out.println(result);
		}
		else if (action.equals("DeRiskAssesment_getCol.risk")) {//first page
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(" form getCol");
			String flid = request.getParameter("flid");
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			JSONObject jsonObject = new JSONObject();
			List<String[]>riskListGrid = null;		
			try {						
				FilterValues.getCommonFilters(request, commonFilter);
				riskListGrid =  riskAssesmtService.getDeRiskList(commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}			 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);	
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);			 
			 String [] colHeaderHead = riskListGrid.get(0);
			 String [] colHeader = riskListGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
	   	     jsonObject.set("tableWidth", "108%%");
	     	 jsonObject.set("tableHeight", "62%%");
	   	     httpSession.removeAttribute("deRiskAssessmentListColModel");
			 httpSession.setAttribute("deRiskAssessmentListColModel",jsonObject);	
			 CommonFunctions.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);	
		} 
		else if (action.equals("DeRiskAssesment_getData.risk")) {
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"deRiskAssesmentListCommonFilter",true);
				FilterValues.getCommonFilters(request, commonFilter);
				String flid = request.getParameter("flid");
				//commonFilter.setFlid(flid);
				List<String[]> MasterGrid = riskAssesmtService.getDeRiskList(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,1,0);
				out.println(ResourceGridmod);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}			
		}
		else if(action.equals("DeRiskAssesment_getExcel.risk"))
		{
			CommonFilter commonFilter = populateCommonFilter(request,"deRiskAssesmentListCommonFilter",false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);	
			String flid = request.getParameter("flid");
			//commonFilter.setFlid(flixd);
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("deRiskAssessmentListColModel");
			tblJSONObj.put("title", "De-Risk Report");
			String format = ExcelUtils.getFormat(request);			
			Workbook wb = riskAssesmtService.DeRiskExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "DeRiskReport", format);			
		}
		
		else if( action.equals("DeRiskAssesmentEntry_input.risk") )
		{
			CommonFunctions.debugMsg("DeRiskAssesmentEntry_input.risk  ");
			String keyid =request.getParameter("keyid");
			String dramkeyid =request.getParameter("dramkeyid");
			CommonFunctions.debugMsg("keyid"+keyid);
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			SheTlRiskassessmentmst sheTlRiskassessmentmst =new 	SheTlRiskassessmentmst();
			SheTlDeriskmst sheTlDeriskmst =new 	SheTlDeriskmst();
			if(UIUtils.isValidKeyId(keyid)){
				sheTlRiskassessmentmst = riskAssesmtService.select(keyid);					
				sheTlRiskassessmentmst.setRasmDate(UIUtils.getActualDateForm(sheTlRiskassessmentmst.getRasmDate()));
			}			
			request.setAttribute("sheTlRiskassessmentmst" , sheTlRiskassessmentmst);
			//sheTlDeriskmst.setDramPreparedby(user.getUsrm_ccno());
			sheTlDeriskmst.setDramPreparedby(sheTlRiskassessmentmst.getRasmPreparedby());
			sheTlDeriskmst.setDramDate(CommonFunctions.dateTimeNow());
			if(UIUtils.isValidKeyId(dramkeyid)){
				sheTlDeriskmst = riskAssesmtService.selectDeRisk(dramkeyid);	
			}
			sheTlDeriskmst.setDramDate(UIUtils.getActualDateForm(sheTlDeriskmst.getDramDate()));
			request.setAttribute("sheTlDeriskmst" , sheTlDeriskmst);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/RiskAssessment/DeRisk.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("DeRiskAssesmentEntry_getCol.risk") )
		{			
			try{
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.RiskAssesment","riskassesmentmstdtls"));
				CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RiskAssesment","riskassesmentmstdtls")); 				
			}catch(Exception e){
				e.printStackTrace();
			}			 
		}
		else if( action.equals("DeRiskAssesmentEntry_getData.risk") )
		{
			try {
				CommonFunctions.debugMsg("DeRiskAssesmentEntry_getData.risk");
				CommonFilter commonFilter = new CommonFilter();
				String keyid =request.getParameter("keyid");
				CommonFunctions.debugMsg("keyid"+keyid);
				commonFilter.setKey(keyid);
				PrintWriter out = response.getWriter();
				List<String[]> RskAssActList = riskAssesmtService.getDeRiskDtlsGridList(commonFilter);
				JSONObject RskAssActData = UIUtils.convertToJqGridTableObject(RskAssActList, request, 2, 0);
				out.println(RskAssActData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if( action.equals("DeRiskDetails_view.risk") )
		{
		}
		else if( action.equals("DeRiskDetails_getCol.risk") )
		{			
			try{
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.RiskAssesment","deriskassesmentdtls"));
				//CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RiskAssesment","deriskassesmentdtls")); 
			}catch(Exception e){				
				e.printStackTrace();
			}			 
		}				
		else if( action.equals("DeRiskDetails_getData.risk") )
		{
			try {
				CommonFunctions.debugMsg("DeRiskDetails_getData.risk");
				CommonFilter commonFilter = new CommonFilter();
				String keyid =request.getParameter("keyid");
				CommonFunctions.debugMsg("keyid"+keyid);
				commonFilter.setKey(keyid);
				PrintWriter out = response.getWriter();
				List<String[]> RskAssActList = riskAssesmtService.getDeRiskDtlsList(commonFilter);
				JSONObject RskAssActData = UIUtils.convertToJqGridTableObject(RskAssActList, request, 2, 0);
				out.println(RskAssActData);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if(action.equals("DeRiskAssesmentEntry_save.risk")){
			saveDeRisk(request,response);		    
		}
		else if( action.equals("DeRiskAssesmentEntry_delete.risk"))
		{	
			deleteDeRisk(request,response);			
		}	
		else if( action.equals("ProbablityCombo.risk"))
		{				
			try{
				ComboFilter probFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = riskAssesmtService.getProbablityComboList(probFilterComboFilter);
				UIUtils.writeComboBox(response, probfilcomboList ,probFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if( action.equals("SeviorityCombo.risk"))
		{				
			try{
				ComboFilter sivFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = riskAssesmtService.getSeviorityComboList(sivFilterComboFilter);
				UIUtils.writeComboBox(response, probfilcomboList ,sivFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if( action.equals("RiskLevelCombo.risk"))
		{				
			try{
				ComboFilter riskFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = riskAssesmtService.getRiskLevelComboList(riskFilterComboFilter);
				UIUtils.writeComboBox(response, probfilcomboList ,riskFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if( action.equals("ControlTypeCombo.risk"))
		{				
			try{
				ComboFilter controlFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = riskAssesmtService.getControlTypeComboList(controlFilterComboFilter);
				UIUtils.writeComboBox(response, probfilcomboList ,controlFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if( action.equals("getRiskLevel.risk"))
		{				
			try{
				String riskVal =request.getParameter("riskval");
				String rowId =request.getParameter("rowid");
				String sev =request.getParameter("sev");
				String prob =request.getParameter("prob");
				
				//CommonFunctions.debugMsg("riskVal:"+riskVal);
				String riskLevel="";
				int iRiskVal=0;
				String sevVal="0";
				String probVal="0";
				//if(UIUtils.isValidKeyId(riskVal)){
				if ((UIUtils.isValidKeyId(prob)) && (UIUtils.isValidKeyId(sev))){
					probVal = riskAssesmtService.getProbablityVal(prob);
					//CommonFunctions.debugMsg("probVal:"+probVal);
					sevVal = riskAssesmtService.getSeviorityVal(sev);
					//CommonFunctions.debugMsg("sevVal:"+sevVal);
					iRiskVal=Integer.parseInt(probVal)*Integer.parseInt(sevVal);
					riskVal=String.valueOf(iRiskVal);				
					//CommonFunctions.debugMsg("riskVal:"+riskVal);					
					riskLevel = riskAssesmtService.getRiskLevel(riskVal);
					request.setAttribute("riskLevel" , riskLevel);
					JSONObject successData = new JSONObject();
					successData.put("riskVal",riskVal);
					successData.put("riskLevel",riskLevel);	
					successData.put("rowId",rowId);	
					//CommonFunctions.debugMsg(returnData.toString());
					PrintWriter out= response.getWriter();
					out.print(successData.toString());
				}
				//}						
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		/*else if( action.equals("RoleCombo.risk"))
		{	
			String  flid = request.getParameter("flid");			
		    ComboFilter roleFilterComboFilter = new ComboFilter(); 
		    CommonFunctions.debugMsg("---------------------> "+flid);			    
			StringBuffer cond = new StringBuffer();				
			cond.append(" AND  ROLE_KEYID IN (SELECT  FRL_ROLE_KEYID FROM GEN_TL_FNLNROLEMAP ");
			cond.append(" WHERE FRL_FNLN_KEYID = '" + flid + "' AND FRL_NOOFPERSONS='M') ");
			
			try{
				roleFilterComboFilter=UIUtils.fillComboFilter(request);
				roleFilterComboFilter.setCondSql(cond.toString());
				List<ComboBox> rolefilcomboList = riskAssesmtService.getRoleComboList(roleFilterComboFilter);
				UIUtils.writeComboBox(response, rolefilcomboList ,roleFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}*/
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
			HttpSession httpSession = request.getSession(false);		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}		
		return commonFilter;
	}
	
	private void deleterisk(HttpServletRequest request,
			HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		String deletemsg;
		PrintWriter out= response.getWriter();
		SheTlRiskassessmentmst  newgenTlRiskassessment =new SheTlRiskassessmentmst();	
		newgenTlRiskassessment=(SheTlRiskassessmentmst)UIUtils.setBeanProperties((Object) newgenTlRiskassessment ,request);
		String  riskdetails = request.getParameter("riskdetails");
		try{
			//if()
			newgenTlRiskassessment =riskAssesmtService.delete(newgenTlRiskassessment);
			deletemsg="Data Deleted Successfully";
			JSONObject successData = new JSONObject();
			successData.put("msg",deletemsg);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);		
			//CommonFunctions.debugMsg(returnData.toString());		
			out.print(returnData.toString());
			
		}catch (ValidationExceptions e) 
		{
			CommonFunctions.debugMsg("ValidationExceptions");
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "RiskAssessmentCreation");
			out.print(errMessage.toString());							    	
		}
							
		catch(BusinessApplicationExceptions e)
		{
			CommonFunctions.debugMsg("BusinessApplicationExceptions");
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "RiskAssessmentCreation");
			out.print(errMessage.toString());
			CommonFunctions.debugMsg(" e " + errMessage );
		}
		catch(Exception e)
		{   
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());
		}
	}
	private void deleteDeRisk(HttpServletRequest request,
			HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		String deletemsg;
		PrintWriter out= response.getWriter();
		SheTlDeriskmst  newSheTlDeriskmst =new SheTlDeriskmst();	
		newSheTlDeriskmst=(SheTlDeriskmst)UIUtils.setBeanProperties((Object) newSheTlDeriskmst ,request);
		try{
			newSheTlDeriskmst =riskAssesmtService.delete(newSheTlDeriskmst);		
			deletemsg="Data Deleted Successfully";
			JSONObject successData = new JSONObject();
			successData.put("msg",deletemsg);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);		
			//CommonFunctions.debugMsg(returnData.toString());			
			out.print(returnData.toString());
		}catch (ValidationExceptions e) 
		{
			CommonFunctions.debugMsg("ValidationExceptions");
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "RiskAssessmentCreation");
			out.print(errMessage.toString());							    	
		}
							
		catch(BusinessApplicationExceptions e)
		{
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "RiskAssessmentCreation");
			out.print(errMessage.toString());
			CommonFunctions.debugMsg(" e " + errMessage );
		}
		catch(Exception e)
		{   
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());
		}
	}
	
	private void saveRisk(HttpServletRequest request,
			HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		 
		CommonFunctions.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null){
				SheTlRiskassessmentmst  newSheTlRiskassessmentmst =new 	SheTlRiskassessmentmst();
				SheTlRiskassessmentdtl  sheTlRiskassessmentdtl =new 	SheTlRiskassessmentdtl();
				RiskBean riskbean=new RiskBean();	
				String riskDetails = request.getParameter("riskdetails");
				String actPlan = request.getParameter("actplan");
				String rowId = request.getParameter("rowid");
				CommonFunctions.debugMsg("actPlan"+actPlan);
				CommonFunctions.debugMsg("rowId"+rowId);
				CommonFunctions.debugMsg("riskdetails---------------------:\n"+riskDetails);
				newSheTlRiskassessmentmst =(SheTlRiskassessmentmst)UIUtils.setBeanProperties((Object)newSheTlRiskassessmentmst,request);//master							 	
				SheTlRiskassessmentmst existSheTlRiskassessmentmst = (SheTlRiskassessmentmst)httpSession.getAttribute("newgenTlRiskassessment");
				newSheTlRiskassessmentmst.setRasmCreatedby(user.getUsrm_ccno());
				List<SheTlRiskassessmentdtl> sheTlRiskassessmentdtlList = null;
	    		JSONArray sheTlRiskassessmentdtljson = null;	    			
	    		if(UIUtils.isValidKeyId(riskDetails) )
	    		{
	    			sheTlRiskassessmentdtljson = JSONArray.fromString(riskDetails);
				    sheTlRiskassessmentdtlList=(List<SheTlRiskassessmentdtl>)UIUtils.convertJSONArrToList(sheTlRiskassessmentdtl, sheTlRiskassessmentdtljson);
				    
				    if(sheTlRiskassessmentdtlList!= null)
				    {
				        //CommonFunctions.debugMsg(" riskdetails 2");
				        for(int i=0 ;i<=sheTlRiskassessmentdtlList.size()-1;i++){
				        	//sheTlRiskassessmentdtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
				        }
				        newSheTlRiskassessmentmst.setRiskDetails(sheTlRiskassessmentdtlList);
				    }
	    		}
			 	String savemsg;					
				boolean insert = true;	
				if( ! UIUtils.isValidKeyId (newSheTlRiskassessmentmst.getRasmKeyid() )  ){	
					//CommonFunctions.debugMsg(newgenTlRiskassessment.getRamtManagerisk());
					 existSheTlRiskassessmentmst =riskAssesmtService.create(newSheTlRiskassessmentmst,existSheTlRiskassessmentmst);				
					 savemsg="Data Saved Successfully";
				}
				else{
					insert = false;
					CommonFunctions.debugMsg("update");
					existSheTlRiskassessmentmst =riskAssesmtService.update(newSheTlRiskassessmentmst,existSheTlRiskassessmentmst);				
					savemsg="Data Updated Successfully";					
				}
				JSONObject successData = new JSONObject();
				successData.put("msg",savemsg);
				JSONObject returnData = new JSONObject();//
				returnData.put("successData", successData);
				returnData.put("GenKeyid",  existSheTlRiskassessmentmst.getRasmKeyid());
				successData.put("keyId", newSheTlRiskassessmentmst.getRasmKeyid());
				if(UIUtils.isValidKeyId(actPlan)){
					String tRow = request.getParameter("totalRow");
					CommonFunctions.debugMsg("totalRow=="+tRow);
					int totalRow =0;
					if (UIUtils.isValidKeyId(tRow))
						totalRow = Integer.parseInt(tRow);
					
					int idx=0;
					if(insert){
						CommonFunctions.debugMsg(" Size-->" + newSheTlRiskassessmentmst.getRiskDetails().size());
						List<SheTlRiskassessmentdtl> list=newSheTlRiskassessmentmst.getRiskDetails();
						for(SheTlRiskassessmentdtl e:list){
							CommonFunctions.debugMsg("element :" + e.getRasdKeyid()+ " activity :" +e.getRasdActivity());
						}
						
						CommonFunctions.debugMsg(" newSheTlRiskassessmentmst-->" + newSheTlRiskassessmentmst);
						CommonFunctions.debugMsg(" newSheTlRiskassessmentmst.getRiskDetails()-->" + newSheTlRiskassessmentmst.getRiskDetails());
						CommonFunctions.debugMsg("My Test---:" +(Integer.parseInt(rowId)-1));
						successData.put("dtlkeyid", newSheTlRiskassessmentmst.getRiskDetails().get(Integer.parseInt(rowId)-1).getRasdKeyid());
						successData.put("rowid", rowId);
						successData.put("activity", newSheTlRiskassessmentmst.getRiskDetails().get(Integer.parseInt(rowId)-1).getRasdActivity());
						successData.put("actplan", true);
					}else{
						int size=newSheTlRiskassessmentmst.getRiskDetails().size();
						totalRow=totalRow-1;
						CommonFunctions.debugMsg(" totalRow-->" +totalRow );
						size=size-1;
						CommonFunctions.debugMsg(" size-->" +size );
						for(;size>=0;){
							if(totalRow==Integer.parseInt(rowId)-1){
								idx=size;
								break;
							}
							totalRow--;
							size--;
							
						}
						CommonFunctions.debugMsg(" Index-->" +idx );
						List<SheTlRiskassessmentdtl> list=newSheTlRiskassessmentmst.getRiskDetails();
						for(SheTlRiskassessmentdtl e:list){
							CommonFunctions.debugMsg("element :" + e.getRasdKeyid()+ " activity :" +e.getRasdActivity());
						}
						
						CommonFunctions.debugMsg(" newSheTlRiskassessmentmst-->" + newSheTlRiskassessmentmst);
						CommonFunctions.debugMsg(" newSheTlRiskassessmentmst.getRiskDetails()-->" + newSheTlRiskassessmentmst.getRiskDetails());
					
						CommonFunctions.debugMsg("My Test---:" +(Integer.parseInt(rowId)-1));
						successData.put("dtlkeyid", newSheTlRiskassessmentmst.getRiskDetails().get(idx).getRasdKeyid());
					
						successData.put("rowid", rowId);
						successData.put("activity", newSheTlRiskassessmentmst.getRiskDetails().get(idx).getRasdActivity());
						
						successData.put("actplan", true);
					}
				}else
				{
					//CommonFunctions.debugMsg(" Inside Flid servlet Else");
					successData.put("actplan", false);	
				}
				
				//returnData.put("formClear", false);
				out.print(returnData.toString());//
				out.close();
				}
			
			}catch (ValidationExceptions e) 
			{
				CommonFunctions.debugMsg("ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "RiskAssessmentCreation");
				out.print(errMessage.toString());							    	
			}
								
			catch(BusinessApplicationExceptions e)
			{
				e.printStackTrace();
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "RiskAssessmentCreation");
				out.print(errMessage.toString());
				CommonFunctions.debugMsg(" e " + errMessage );
			}
			catch(Exception e)
			{   
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
			
	}
	
	private void saveDeRisk(HttpServletRequest request,
			HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		 
		CommonFunctions.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null){
				SheTlDeriskmst  newSheTlDeriskmst =new 	SheTlDeriskmst();
				SheTlDeriskdtl  sheTlDeriskdtl =new 	SheTlDeriskdtl();
				RiskBean riskbean=new RiskBean();	
				String deriskdetails = request.getParameter("deriskdetails");
				//CommonFunctions.debugMsg("deriskdetails:"+deriskdetails);
				newSheTlDeriskmst =(SheTlDeriskmst)UIUtils.setBeanProperties((Object)newSheTlDeriskmst,request);//master							 	
				SheTlDeriskmst existSheTlDeriskmst = (SheTlDeriskmst)httpSession.getAttribute("newSheTlDeriskmst");
				newSheTlDeriskmst.setDramCreatedby(user.getUsrm_ccno());
				List<SheTlDeriskdtl> sheTlDeriskdtlList = null;
	    		JSONArray sheTlDeriskdtljson = null;	    			
	    		if(UIUtils.isValidKeyId(deriskdetails) )
	    		{
	    			sheTlDeriskdtljson = JSONArray.fromString(deriskdetails);
	    			//CommonFunctions.debugMsg(" sheTlDeriskdtljson "+sheTlDeriskdtljson);
	    			sheTlDeriskdtlList=(List<SheTlDeriskdtl>)UIUtils.convertJSONArrToList(sheTlDeriskdtl, sheTlDeriskdtljson);
	    			
				    if(sheTlDeriskdtlList!= null)
				    {
				        //CommonFunctions.debugMsg(" riskdetails "+sheTlDeriskdtlList.size());
				        for(int i=0 ;i<=sheTlDeriskdtlList.size()-1;i++){
				        	//sheTlRiskassessmentdtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
				        }
				        newSheTlDeriskmst.setDeRiskDetails(sheTlDeriskdtlList);
				    }
	    		}
			 	String savemsg;					
				boolean insert = true;	
				if( ! UIUtils.isValidKeyId (newSheTlDeriskmst.getDramKeyid())  ){	
					//CommonFunctions.debugMsg(newgenTlRiskassessment.getRamtManagerisk());
					existSheTlDeriskmst =riskAssesmtService.create(newSheTlDeriskmst,existSheTlDeriskmst);				
					 savemsg="Data Saved Successfully";
				}
				else{
					insert = false;
					CommonFunctions.debugMsg("update");
					existSheTlDeriskmst =riskAssesmtService.update(newSheTlDeriskmst,existSheTlDeriskmst);				
					savemsg="Data Updated Successfully";					
				}
				JSONObject successData = new JSONObject();
				successData.put("msg",savemsg);
				JSONObject returnData = new JSONObject();//
				returnData.put("successData", successData);
				returnData.put("GenKeyid",  existSheTlDeriskmst.getDramKeyid());
				returnData.put("formClear", false);
				out.print(returnData.toString());//
				out.close();
				}
			
			}catch (ValidationExceptions e) 
			{
				CommonFunctions.debugMsg("ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "RiskAssessmentCreation");
				out.print(errMessage.toString());							    	
			}
								
			catch(BusinessApplicationExceptions e)
			{
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "RiskAssessmentCreation");
			out.print(errMessage.toString());
			CommonFunctions.debugMsg(" e " + errMessage );
			}
			catch(Exception e)
			{   
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
			}
			
		}

	} 


			
			
				
	




	