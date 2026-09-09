package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.KaizenReportService;
import com.akranta.tpm.service.SuggestionNonJHEspReportService;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.KaizenReportServiceImpl;
import com.akranta.tpm.service.impl.SuggestionNonJHEspReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/**
 * Servlet implementation class SuggestionNonJHEspReport
 */
@WebServlet("/SuggestionNonJHEspReport")
public class SuggestionNonJHEspReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SuggestionNonJHEspReportService suggestionReportService;
	DashboardService dashboardService;
	private static final String commonFilterIden = "impVsCompcommonFilter";
       
   
		
    public SuggestionNonJHEspReportServlet() {
      try{
        suggestionReportService= new SuggestionNonJHEspReportServiceImpl(null);  
      }catch(Exception e){
    	  e.printStackTrace();
      }
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!UIUtils.checkUserSession(request, response))
			return;
		String dispatchUrl = null;
		String action = UIUtils.getActionPart(request);
		HttpSession h=request.getSession(false);
		try {
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");

			suggestionReportService = (SuggestionNonJHEspReportServiceImpl)UIUtils.getServiceObject(request,"SuggestionNonJHEspReportServiceImpl");
			suggestionReportService.SuggestionNonJHEspReportServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")));
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}	
	if(action.equals("espSuggestionSummaryReport_input.njes")){
		
		CommonMessage.debugMsg("kaizenSuggestionSummaryReport_input.njes");
		UIUtils.forwardRequest(request, response,"/pages/Reports/ESPsuggestionSummaryreport.jsp");
		
	}
	
	else if(action.equals("espSuggestionSummaryReport_getCol.njes")){			
		PrintWriter out = response.getWriter();	
		HttpSession httpSession=request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"SuggestionSummaryCommonFilter",true);
		commonFilter.setIsGetCol("Y");
		List<String[]> kaizenSuggestionGridData  = suggestionReportService.getSuggestionSummaryGridData(commonFilter);			
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		GridColModel gridColModel = new GridColModel();
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		gridColModel.setHeaderNum(1);
		String[] colHeader = kaizenSuggestionGridData.get(1);
		String[] colHeaderCond = kaizenSuggestionGridData.get(0);
		List<String[]> headers = new ArrayList<String[]>();			
		headers.add(colHeader);			
		JSONObject colModel =new JSONObject();
		colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
		colModel.set("tableHeight", "90%%");
		colModel.set("tableWidth", "110%%");
		httpSession.removeAttribute("SuggestionSummaryColModel");
		httpSession.setAttribute("SuggestionSummaryColModel", colModel);			
		httpSession.removeAttribute("SuggestionSummaryCommonFilter");
		httpSession.setAttribute("SuggestionSummaryCommonFilter", commonFilter);			
		out.println(colModel);			
	}
	
	else if(action.equals("espSuggestionSummaryReport_getData.njes")){
		PrintWriter out = response.getWriter();	
		HttpSession httpSession=request.getSession(false);
		CommonFilter commonFilter=populateCommonFilter(request, "SuggestionSummaryCommonFilter", false);
		commonFilter.setIsGetCol("N");
		List<String[]> KaizenGridData = suggestionReportService.getSuggestionSummaryGridData(commonFilter);
		JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData,request, 2, 0,commonFilter.getTotalRecordCnt() );
		out.print(dataJson);
		httpSession.removeAttribute("SuggestionSummaryCommonFilter");
		httpSession.setAttribute("SuggestionSummaryCommonFilter", commonFilter);			
	}
	
	else if(action.equals("espSuggestionSummaryReport_getExcel.njes")) {
		HttpSession httpSession=request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"SuggestionSummaryCommonFilter",false);			
		JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("SuggestionSummaryColModel");
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		tblJSONObj.put("title", "Non JH ESP Suggestion Summary Report  ");			
		String format = ExcelUtils.getFormat(request);	
		Workbook wb = suggestionReportService.getSuggestionSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);			
		commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, "NonJHEspSuggestionSummaryReport", format);		
	 }
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		/*String drFlg = request.getParameter("drillDown");
		char drillFlag = '-';
		if (UIUtils.isValidKeyId(drFlg))
			drillFlag = drFlg.trim().charAt(0);
		*/
		//commonFilter.setDrillFlag(drillFlag);
		//CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}

}
