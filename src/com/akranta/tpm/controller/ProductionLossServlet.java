package com.akranta.tpm.controller;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTitle;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.ColumnChart;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.ProductionLossService;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.service.api.PcsEntryServiceApi;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.ProductionLossServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

public class ProductionLossServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductionLossService productionLossService;
    DashboardService dashboardService;
    
    PcsEntryServiceApi pcsEntryServiceApi;	
    
	public ProductionLossServlet() throws Exception {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		try {
			this.productionLossService = ((ProductionLossServiceImpl) UIUtils
					.getServiceObject(request, "ProductionLossServiceImpl"));
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
      
			
			CommonMessage.debugMsg("  oplServices jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
			//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			productionLossService.ProductionLossServiceImplJwt(
						   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
						);
			
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		
		String dispatchUrl = null;

		if (action.equals("filterXmlProductionLoss_input.prdloss")) {
			response.setContentType("xml");
			CommonMessage.debugMsg("action " + action);
			UIUtils.forwardRequest(request, response,
					"/tiles/xml/ProductionLossAnalysis.xml");
		} else if (action.equals("productionLossSubGrid_input.prdloss")) {
			dispatchUrl = "/pages/workRespPop.jsp";
		} else if (action.equals("productionLossSubGrid_getCol.prdloss")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = new CommonFilter();

			populateCommonFilter(request, "productionLoss", true);
			commonFilter = (CommonFilter) httpSession
					.getAttribute("productionLoss");
			commonFilter.setRowTotal(Character.valueOf('N'));
			if (("01-Jan-1801".contains(commonFilter.getFromMonth()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter
							.getMonwise().equals("Y")))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(
						Integer.valueOf(-1)).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,
						11));

				commonFilter.setMonwise("Y");
			} else if (("01-Jan-1801".contains(commonFilter.getFromDate()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter
							.getMonwise().equals(" ")))) {
				commonFilter.setFromDate(CommonFunctions
						.getFirstDateofMonth(Integer.valueOf(-3)));
				commonFilter.setToDate(CommonFunctions.getDate());
			}

			CommonMessage.debugMsg("Oourence Chkboc:"
					+ commonFilter.getChkoccurchkbox());
			if (commonFilter.getChkboxoccurence() == null)
				commonFilter.setChkboxoccurence("Y");
			if (commonFilter.getChkboxtime() == null) {
				commonFilter.setChkboxtime("Y");
			}
			String hide = "Y";
			List<String[]> pcsRpt = this.productionLossService
					.getAllproductionLoss(commonFilter);
			List<String[]> pcsGridData = transposeListArr(pcsRpt, hide);
			JSONObject jsonObject = getTableModel1(pcsGridData, dispatchUrl);

			httpSession.removeAttribute("productionLoss");
			httpSession.setAttribute("productionLoss", commonFilter);
			response.setContentType("text/html");

			out.println(jsonObject);
		} else if (action.equals("productionLossSubGrid_getData.prdloss")) {
			String keyId = request.getParameter("keyID");

			String sectid = request.getParameter("sectid");
			String factId = request.getParameter("fctid");
			String rowId = request.getParameter("rowId");
			try {
				CommonFilter commonFilter = (CommonFilter) httpSession
						.getAttribute("productionLoss");
				PrintWriter out = response.getWriter();
				request.setAttribute("occurance",
						commonFilter.getChkboxoccurence());
				request.setAttribute("time", commonFilter.getChkboxtime());

				commonFilter.setLossId(keyId);
				ComboFilter cmbSection = new ComboFilter();
				cmbSection.setId(sectid);
				commonFilter.setSection(cmbSection);

				ComboFilter cmbFact = new ComboFilter();
				cmbFact.setId(factId);
				commonFilter.setFactory(cmbFact);
				String hide = "N";

				List<String[]> pcsRpt = this.productionLossService
						.getAllproductionLossSubGrid(commonFilter);
				List<String[]> pcsGridData = transposeListArr(pcsRpt, hide);
				JSONObject pcsRptData = UIUtils.convertToJqGridTableObject(
						pcsGridData, request, 5, 0);

				pcsRptData.put("msg", "Success");
				pcsRptData.put("rowId", keyId);
				pcsRptData.put("gridRowId", rowId);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", pcsRptData);
				returnData.put("rows", pcsRptData);

				out.println(pcsRptData);
			} catch (Exception e) {
				PrintWriter out = response.getWriter();
				JSONObject err = new JSONObject();
				err.put("exception", true);
				err.put("gridRowId", rowId);
				err.put("messages", "Data Not Found");
				out.print(err.toString());
			}

		} else if (action.equals("productionLossDrillDown_input.prdloss")) {
			CommonMessage.debugMsg("productionLossDrillDown_input..........");
			// dispatchUrl = "/pages/Reports/ProductionLossDrillDown.jsp";
			RequestDispatcher rd = request
					.getRequestDispatcher("pages/Reports/ProductionLossDrillDown.jsp");
			rd.forward(request, response);
		} 
		else if (action.equals("productionLossDrillDown_getCol.prdloss")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = new CommonFilter();

			populateCommonFilter(request, "productionLoss", true);
			commonFilter = (CommonFilter) httpSession
					.getAttribute("productionLoss");
			commonFilter.setRowTotal(Character.valueOf('N'));
			if (("01-Jan-1801".contains(commonFilter.getFromMonth()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter
							.getMonwise().equals("Y")))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(
						Integer.valueOf(-5)).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,
						11));

				commonFilter.setMonwise("Y");
			} else if (("01-Jan-1801".contains(commonFilter.getFromDate()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter
							.getMonwise().equals(" ")))) {
				commonFilter.setFromDate(CommonFunctions
						.getFirstDateofMonth(Integer.valueOf(-3)));
				commonFilter.setToDate(CommonFunctions.getDate());
			}

			CommonMessage.debugMsg("Oourence Chkboc:"
					+ commonFilter.getChkoccurchkbox());
			if (commonFilter.getChkboxoccurence() == null)
				commonFilter.setChkboxoccurence("Y");
			if (commonFilter.getChkboxtime() == null) {
				commonFilter.setChkboxtime("Y");
			}
			String hide = "Y";
			 commonFilter.setIsGetCol("Y");
			List<String[]> pcsRpt = this.productionLossService
					.getAllproductionLossDrill(commonFilter);
			// List<String[]> pcsGridData = transposeListArr(pcsRpt, hide);
			JSONObject jsonObject = getTableModel1(pcsRpt, dispatchUrl);

			httpSession.removeAttribute("productionLoss");
			httpSession.setAttribute("productionLoss", commonFilter);
			httpSession.removeAttribute("productionLossColModel");
			httpSession.setAttribute("productionLossColModel", jsonObject);
			response.setContentType("text/html");

			out.println(jsonObject);
		}
		else if (action.equals("productionLossDrillDown_getData.prdloss")) {
			String keyId = request.getParameter("keyID");

			String sectid = request.getParameter("sectid");
			String factId = request.getParameter("fctid");
			String rowId = request.getParameter("rowId");
			try {
				CommonFilter commonFilter = (CommonFilter) httpSession
						.getAttribute("productionLoss");
				PrintWriter out = response.getWriter();
				request.setAttribute("occurance",
						commonFilter.getChkboxoccurence());
				request.setAttribute("time", commonFilter.getChkboxtime());

				commonFilter.setLossId(keyId);
				ComboFilter cmbSection = new ComboFilter();
				cmbSection.setId(sectid);
				commonFilter.setSection(cmbSection);

				ComboFilter cmbFact = new ComboFilter();
				cmbFact.setId(factId);
				commonFilter.setFactory(cmbFact);
				String hide = "N";
				 commonFilter.setIsGetCol("N");
				List<String[]> pcsRpt = this.productionLossService
						.getAllproductionLossDrill(commonFilter);
				// List<String[]> pcsGridData = transposeListArr(pcsRpt, hide);
				JSONObject pcsRptData = UIUtils.convertToJqGridTableObject(
						pcsRpt, request, 1, 0);

				pcsRptData.put("msg", "Success");
				pcsRptData.put("rowId", keyId);
				pcsRptData.put("gridRowId", rowId);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", pcsRptData);
				returnData.put("rows", pcsRptData);

				out.println(pcsRptData);
			} catch (Exception e) {
				PrintWriter out = response.getWriter();
				JSONObject err = new JSONObject();
				err.put("exception", true);
				err.put("gridRowId", rowId);
				err.put("messages", "Data Not Found");
				out.print(err.toString());
			}
			//--------progress
		}else if(action.equals("productionLossDrillDown_getExcel.prdloss")){
			CommonMessage.debugMsg("Action:" + action);
			CommonFilter commonFilter = populateCommonFilter(request,"productionLoss", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("productionLossColModel");
			tblJSONObj.put("title", "Loss Analysis Report"); 
			String format = ExcelUtils.getFormat(request);
			Workbook wb = this.productionLossService.getLossAnalysisExportExcel(commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "LossAnalysisReport", format);
			
		}
		else if(action.equals("ProductionLossTimeRpt_input.prdloss")){
			CommonMessage.debugMsg("Production LossTime Report");
			RequestDispatcher rd=request.getRequestDispatcher("pages/Reports/Losstimereport.jsp");
			rd.forward(request,response);
		}
		else if(action.equals("chartLossTime.prdloss")){
			
		    processLossTimeNew(request,response);	
		}

		else if (action.equals("ProductionLossTimeRpt_getCol.prdloss")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = new CommonFilter();

			populateCommonFilter(request, "productionLoss", true);
			commonFilter = (CommonFilter) httpSession
					.getAttribute("productionLoss");
			commonFilter.setRowTotal(Character.valueOf('N'));
			if (("01-Jan-1801".contains(commonFilter.getFromMonth()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter
							.getMonwise().equals("Y")))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(
						Integer.valueOf(-5)).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,
						11));

				commonFilter.setMonwise("Y");
			} else if (("01-Jan-1801".contains(commonFilter.getFromDate()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter
							.getMonwise().equals(" ")))) {
				commonFilter.setFromDate(CommonFunctions
						.getFirstDateofMonth(Integer.valueOf(-3)));
				commonFilter.setToDate(CommonFunctions.getDate());
			}

			CommonMessage.debugMsg("Oourence Chkboc:"
					+ commonFilter.getChkoccurchkbox());
			if (commonFilter.getChkboxoccurence() == null)
				commonFilter.setChkboxoccurence("Y");
			if (commonFilter.getChkboxtime() == null) {
				commonFilter.setChkboxtime("Y");
			}
			String hide = "Y";
			 commonFilter.setIsGetCol("Y");
			List<String[]> pcsRpt = this.productionLossService
					.getAllLossTimeDrill(commonFilter);
			// List<String[]> pcsGridData = transposeListArr(pcsRpt, hide);
			JSONObject jsonObject = getTableModel1(pcsRpt, dispatchUrl);

			httpSession.removeAttribute("productionLoss");
			httpSession.setAttribute("productionLoss", commonFilter);
			httpSession.removeAttribute("productionLossColModel");
			httpSession.setAttribute("productionLossColModel", jsonObject);
			response.setContentType("text/html");

			out.println(jsonObject);
		}
		else if (action.equals("ProductionLossTimeRpt_getData.prdloss")) {
			String keyId = request.getParameter("keyID");

			String sectid = request.getParameter("sectid");
			String factId = request.getParameter("fctid");
			String rowId = request.getParameter("rowId");
			try {
				CommonFilter commonFilter = (CommonFilter) httpSession
						.getAttribute("productionLoss");
				PrintWriter out = response.getWriter();
				request.setAttribute("occurance",
						commonFilter.getChkboxoccurence());
				request.setAttribute("time", commonFilter.getChkboxtime());

				commonFilter.setLossId(keyId);
				ComboFilter cmbSection = new ComboFilter();
				cmbSection.setId(sectid);
				commonFilter.setSection(cmbSection);

				ComboFilter cmbFact = new ComboFilter();
				cmbFact.setId(factId);
				commonFilter.setFactory(cmbFact);
				String hide = "N";
				 commonFilter.setIsGetCol("N");
				List<String[]> pcsRpt = this.productionLossService
						.getAllLossTimeDrill(commonFilter);
				JSONObject pcsRptData = UIUtils.convertToJqGridTableObject(
						pcsRpt, request, 1, 0);

				pcsRptData.put("msg", "Success");
				pcsRptData.put("rowId", keyId);
				pcsRptData.put("gridRowId", rowId);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", pcsRptData);
				returnData.put("rows", pcsRptData);

				out.println(pcsRptData);
			} catch (Exception e) {
				PrintWriter out = response.getWriter();
				JSONObject err = new JSONObject();
				err.put("exception", true);
				err.put("gridRowId", rowId);
				err.put("messages", "Data Not Found");
				out.print(err.toString());
			}
		}
		else if(action.equals("ProductionLossTimeRpt_getExcel.prdloss")){
			CommonFilter commonFilter = populateCommonFilter(request,"productionLoss", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("productionLossColModel");
			tblJSONObj.put("title", "Loss Time Report"); 
			String format = ExcelUtils.getFormat(request);
			Workbook wb = this.productionLossService.getLossAnalysisExportExcel(commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "LossTimeReport", format);
			
		}
	
		else if (action.equals("chartNew1.prdloss")) {
			httpSession = request.getSession(false);
			CommonFilter commonFilter = null;

			String rowno = request.getParameter("PARAMCODE1");
			String Istrend = request.getParameter("Istrend");
			String selmonth = request.getParameter("selmonth");
			CommonMessage.debugMsg("The SelMonth::::"+selmonth);
			String Isparato = request.getParameter("Isparato");


            String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			String forDashboard = request.getParameter("dashboard");
			if (!"true".equals(forDashboard)) {
				commonFilter = (CommonFilter) httpSession
						.getAttribute("productionLoss");
			} else {
				commonFilter = new CommonFilter();
				commonFilter = FilterValues.getCommonFilters(request,
						commonFilter); // getFilterValues(request);
				commonFilter = FilterValues.getOPLandKaizen(request,
						commonFilter);

			}
			CommonFilter chartCommonFilter = new CommonFilter();

			BeanUtils.copyProperties(chartCommonFilter, commonFilter);

			CommonMessage.debugMsg(" flid " + commonFilter.getFlid());
			// FilterValues.getCommonFilters(request, chartCommonFilter) ;

			chartCommonFilter.setRowTotal('Y');
			chartCommonFilter.setRange(rowno);
              
			
			
			
			
			if (Isparato.equals("Y")) {
				chartCommonFilter.setPareto('Y');
			} else {
				chartCommonFilter.setPareto('N');

			}
			
			if (("01-Jan-1801".contains(chartCommonFilter.getFromMonth()))
					&& ((chartCommonFilter.getMonwise() == null) || (chartCommonFilter
							.getMonwise().equals("Y")))) {
				chartCommonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(
						Integer.valueOf(-3)).substring(3, 11));
				chartCommonFilter.setToMonth(CommonFunctions.getDate().substring(3,
						11));

				chartCommonFilter.setMonwise("Y");
			} else if (("01-Jan-1801".contains(chartCommonFilter.getFromDate()))
					&& ((chartCommonFilter.getMonwise() == null) || (chartCommonFilter
							.getMonwise().equals(" ")))) {
				chartCommonFilter.setFromDate(CommonFunctions
						.getFirstDateofMonth(Integer.valueOf(-3)));
				chartCommonFilter.setToDate(CommonFunctions.getDate());
			}
			

			List<String[]> graphData = this.productionLossService.getAllproductionLossNewChart(chartCommonFilter,selmonth);
			CommonMessage.debugMsg("graphData.size()1: " +graphData.size());
			JSONObject chartObj = null;
			/*making data for empty Parato chart*/ 
			if (graphData.size() == 0 && Isparato.equals("Y")){
				graphData = new ArrayList<String[]>();
				String dummy[]={"0","0","0","0","0"};
				graphData.add(dummy);
				CommonMessage.debugMsg("graphData.size(): " +graphData.size());
			}
			
			if (graphData != null && graphData.size() > 0) {
				if (Istrend.equals("Y")) {
					chartObj = processTrendChartNew(lcnname,graphData,	chartCommonFilter, selmonth);
					
				} else if (Isparato.equals("Y")) {
					chartObj = processparetoChart(lcnname,graphData,chartCommonFilter, selmonth);
				} else {
					chartObj = processLineChartNew(graphData,chartCommonFilter);
				}
				// UIUtils.dashBoardSetChartObject(request,chartObj);
				PrintWriter out = response.getWriter();
				UIUtils.dashBoardSetChartObject(request, chartObj);
				out.print(chartObj);
				out.close();
			}

		}
		
		else if (action.equals("ProductionLoss_input.prdloss")) {
			String fact = request.getParameter("cmbFactid");
			String sect = request.getParameter("cmbSectid");
			String cell = request.getParameter("cmbCellid");
			String mchm = request.getParameter("cmbMchid");

			if (UIUtils.isValidKeyId(fact))
				request.setAttribute("hiddenIds", fact);
			if (UIUtils.isValidKeyId(sect))
				request.setAttribute("hiddenIds", sect);
			if (UIUtils.isValidKeyId(cell))
				request.setAttribute("hiddenIds", cell);
			if (UIUtils.isValidKeyId(mchm)) {
				request.setAttribute("hiddenIds", mchm);
			}
			request.setAttribute("DoubleClickGraph", UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.CommonMessages", "forgraph"));
			request.setAttribute("ProdLoss", UIUtils
					.getPropertyValue(
							"com.akranta.tpm.resources.CommonMessages",
							"prodLossAnlys"));
			request.setAttribute("DoubleClickParato", UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.CommonMessages", "forparato"));

			RequestDispatcher rd = request
					.getRequestDispatcher("pages/Reports/ProductionLoss.jsp");
			rd.forward(request, response);
		} else if (action.equals("ProductionLoss_getCol.prdloss")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = new CommonFilter();

			populateCommonFilter(request, "productionLoss", true);
			commonFilter = (CommonFilter) httpSession
					.getAttribute("productionLoss");
			commonFilter.setRowTotal(Character.valueOf('N'));

			if (("01-Jan-1801".contains(commonFilter.getFromMonth()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter
							.getMonwise().equals("Y")))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(
						Integer.valueOf(-2)).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,
						11));
				commonFilter.setMonwise("Y");
				commonFilter.setRemoveBlank("Y");
			} else if (("01-Jan-1801".contains(commonFilter.getFromDate()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter
							.getMonwise().equals(" ")))) {
				commonFilter.setFromDate(CommonFunctions
						.getFirstDateofMonth(Integer.valueOf(-3)));
				commonFilter.setToDate(CommonFunctions.getDate());
				commonFilter.setRemoveBlank("Y");
			}

			if (commonFilter.getChkboxoccurence() == null)
				commonFilter.setChkboxoccurence("Y");
			if (commonFilter.getChkboxtime() == null) {
				commonFilter.setChkboxtime("Y");
			}
			commonFilter.setParamCode(FilterCondSql
					.getComboSelectionId(commonFilter.getLossType()));
			String hide = "Y";

			String loginflid = CommonFunctions.getLoginFlid(request);
			if (commonFilter.getFlid() != null) {
				commonFilter.setFlid(loginflid);
			} else {
				commonFilter.setFlid(loginflid);
			}
			CommonMessage.debugMsg("pcs loginflid" + loginflid);

			List<String[]> pcsRpt = this.productionLossService
					.getAllproductionLoss(commonFilter);
			
			List<String[]> pcsGridData = transposeListArr(pcsRpt, hide);

			JSONObject jsonObject = getTableModel1(pcsGridData, dispatchUrl);
			jsonObject.set("tableHeight", "70%%");
			jsonObject.set("tableWidth", "106%%");
			httpSession.removeAttribute("productionLoss");
			httpSession.setAttribute("productionLoss", commonFilter);
			httpSession.removeAttribute("lossReportColModel");
			httpSession.setAttribute("lossReportColModel", jsonObject);

			response.setContentType("text/html");
			jsonObject.put("sortable", false);
			out.println(jsonObject);
		} else if (action.equals("ProductionLoss_getData.prdloss")) {
			try {
				CommonFilter commonFilter = (CommonFilter) httpSession
						.getAttribute("productionLoss");
				PrintWriter out = response.getWriter();
				request.setAttribute("occurance",
						commonFilter.getChkboxoccurence());
				request.setAttribute("time", commonFilter.getChkboxtime());

				List<String[]> pcsRpt = this.productionLossService
						.getAllproductionLoss(commonFilter);

				String hide = "Y";
				List<String[]> pcsGridData = null;

				if ("Y".equals(commonFilter.getRemoveBlank())) {
					pcsGridData = UIUtils.transposeListArr(pcsRpt, "0,-", 6,
							pcsRpt.size(), 2);
				} else
					pcsGridData = transposeListArr(pcsRpt, hide);
				JSONObject pcsRptData = UIUtils.convertToJqGridTableObject(
						pcsGridData, request, 2, 0);
				out.println(pcsRptData);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}	 else if (action.equals("ProductionLoss_getExcel.prdloss")) {
			CommonFilter commonFilter = populateCommonFilter(request,
					"productionLoss", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();

			String month1 = fromMonth + "  -  " + toMonth;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date = fromdate + "  -  " + todate;

			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("lossReportColModel");
			tblJSONObj
					.put("title", "Production Loss Analysis Report - " + date);

			if (commonFilter.getMonwise().equals("Y")) {
				tblJSONObj.put("title", "Production Loss Analysis Report - "
						+ month1);
			}
			tblJSONObj.put("transpose", true);
			UIUtils.removeBlankRowExcel(commonFilter, tblJSONObj, 5, -1, "0,-");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = this.productionLossService.lossExportExcel(
					commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "ProductionLossReport",
					format);
		} else {

			if (action.equals("chartNew.prdloss")) {

				httpSession = request.getSession(false);
				// CommonFilter commonFilter =(CommonFilter)
				// httpSession.getAttribute(commonFilterIden);
				// CommonFilter commonFilter =
				// populateCommonFilter(request,commonFilterIden,false);
				// FilterValues.getCommonFilters(request, commonFilter) ;
				CommonFilter commonFilter = null;

				String rowno = request.getParameter("PARAMCODE1");
				String Istrend = request.getParameter("Istrend");
				String selmonth = request.getParameter("selmonth");
				String Isparato = request.getParameter("Isparato");
                String flids=CommonFunctions.getLoginFlid(request);
			   String lcnname=dashboardService.Functionallocn(flids);
				String forDashboard = request.getParameter("dashboard");
				if (!"true".equals(forDashboard)) {
					commonFilter = (CommonFilter) httpSession
							.getAttribute("productionLoss");
				} else {
					commonFilter = new CommonFilter();
					commonFilter = FilterValues.getCommonFilters(request,
							commonFilter); // getFilterValues(request);
					commonFilter = FilterValues.getOPLandKaizen(request,
							commonFilter);

				}
				CommonFilter chartCommonFilter = new CommonFilter();

				BeanUtils.copyProperties(chartCommonFilter, commonFilter);

				CommonMessage.debugMsg(" flid " + commonFilter.getFlid());
				// FilterValues.getCommonFilters(request, chartCommonFilter) ;

				chartCommonFilter.setRowTotal('Y');
				chartCommonFilter.setRange(rowno);

				if (Isparato.equals("Y")) {
					chartCommonFilter.setPareto('Y');
				} else {
					chartCommonFilter.setPareto('N');

				}

				List<String[]> graphData = this.productionLossService.getAllproductionLossNewChart(chartCommonFilter,selmonth);
				CommonMessage.debugMsg("graphData.size()1: " +graphData.size());
				JSONObject chartObj = null;
				/*making data for empty Parato chart*/ 
				if (graphData.size() == 0 && Isparato.equals("Y")){
					graphData = new ArrayList<String[]>();
					String dummy[]={"0","0","0","0","0"};
					graphData.add(dummy);
					CommonMessage.debugMsg("graphData.size(): " +graphData.size());
				}
				
				if (graphData != null && graphData.size() > 0) {
					if (Istrend.equals("Y")) {
						chartObj = processTrendChart(lcnname,graphData,	chartCommonFilter, selmonth);
						
					} else if (Isparato.equals("Y")) {
						chartObj = processparetoChart(lcnname,graphData,chartCommonFilter, selmonth);
					} else {
						chartObj = processLineChartNew(graphData,chartCommonFilter);
					}
					// UIUtils.dashBoardSetChartObject(request,chartObj);
					PrintWriter out = response.getWriter();
					UIUtils.dashBoardSetChartObject(request, chartObj);
					out.print(chartObj);
					out.close();
				}

			}
			if (action.equals("chart.prdloss")) {
				ColumnChart columnChart = new ColumnChart();
				CommonFilter commonFilter;
				String forDashboard = request.getParameter("dashboard");
				CommonMessage.debugMsg("forDashboard....." + forDashboard);
				if (!"true".equals(forDashboard)) {
					CommonMessage.debugMsg("to check dash board");
					commonFilter = (CommonFilter) httpSession
							.getAttribute("productionLoss");
				} else {
					commonFilter = new CommonFilter();
					CommonMessage.debugMsg("to check dash board true ");
					FilterValues.getCommonFilters(request, commonFilter);
					FilterValues.getPCS(request, commonFilter);

				}

				CommonFilter chrtCommonFilter = new CommonFilter();
				BeanUtils.copyProperties(chrtCommonFilter, commonFilter);

				String paramCode = request.getParameter("PARAMCODE1");
				PrintWriter out = response.getWriter();

				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();

				String month1 = fromMonth + "  -  " + toMonth;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String date = fromdate + "  -  " + todate;
				String title = "Production Loss Analysis - " + date;

				if (commonFilter.getMonwise().equals("Y")) {
					title = "Production Loss Analysis - " + month1;
				}

				if (chrtCommonFilter.getRowTotal() == null) {
					chrtCommonFilter.setRowTotal(Character.valueOf('Y'));
				}
				if (UIUtils.isValidKeyId(paramCode)) {
					chrtCommonFilter.setParamCode(paramCode);
				}
				CommonMessage.debugMsg("cpde..."
						+ chrtCommonFilter.getParamCode());
				chrtCommonFilter.setISFORGRAPH(Character.valueOf('Y'));
				chrtCommonFilter.setPareto(Character.valueOf('N'));

				if ((chrtCommonFilter.getChkoccurchkbox() == "Y")
						|| (chrtCommonFilter.getChktimeChkBox() == "Y")) {
					CommonMessage.debugMsg("Occurence time is yes");
				}

				List<String[]> graphData = this.productionLossService
						.getAllproductionLoss(chrtCommonFilter);

				List<String> xCategories = new ArrayList<String>();
				List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();
				List<Double> dataLine = new ArrayList<Double>();// declaring
																// variable for
																// line chart
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();// declaring
																					// variable
																					// for
																					// charseries
																					// were
																					// the
																					// data
																					// is
																					// to
																					// bound
				ChartSeries chartSeriesLine = new ChartSeries();

				ChartSeries timeSeries = new ChartSeries();
				List timeData = new ArrayList();

				ChartSeries intstanceSeries = new ChartSeries();
				List instanceData = new ArrayList();

				for (String[] row : graphData) {
					if (row[1].equals("OCCURENCE")) {
						CommonMessage.debugMsg("OCCURENCE" + row[1]);
						instanceData.add(Double.valueOf(Double
								.parseDouble(row[2].replace(" ", "0").replace(
										"-", "0"))));
						xCategories.add(row[0]);
					} else if (row[1].equals("TIME")) {
						CommonMessage.debugMsg("TIME" + row[1]);
						timeData.add(Double.valueOf(Double.parseDouble(row[2]
								.replace(" ", "0").replace("-", "0"))));
						xCategories.add(row[0]);
					}
				}
				if (timeData.size() > 0) {
					timeSeries.setData(timeData);
					timeSeries.setType("spline");
					timeSeries.setName("Time");
					chartSeriesList.add(timeSeries);
					ChartYAxis yAxis = new ChartYAxis();
					yAxis.setMin(Integer.valueOf(0));
					yAxis.getTitle().setText("Count");
					chartYAxis.add(yAxis);
				}
				/*
				 * CommonMessage.debugMsg("commonFilter.getChkboxoccurence()" +
				 * commonFilter.getChkboxoccurence());
				 * if(commonFilter.getChkboxoccurence().equals("Y")) { if
				 * (instanceData.size() > 0) {
				 * intstanceSeries.setData(instanceData);
				 * intstanceSeries.setType("spline");
				 * intstanceSeries.setName("Occurence");
				 * chartSeriesList.add(intstanceSeries);
				 * 
				 * ChartYAxis yAxis = new ChartYAxis();
				 * yAxis.setMin(Integer.valueOf(0));
				 * yAxis.getTitle().setText("OCCURENCE"); if (chartYAxis.size()
				 * > 0) { yAxis.setOpposite(Boolean.valueOf(true));
				 * intstanceSeries.setyAxis(Integer.valueOf(1)); }
				 * chartYAxis.add(yAxis); } }
				 */

				ChartXAxis xaxis = new ChartXAxis();
				if (commonFilter.getMonwise().equals("Y"))
					xaxis.getTitle().setText("Month");
				else
					xaxis.getTitle().setText("Date");
				columnChart.getxAxis().setTitle(xaxis.getTitle());
				JSONObject chartObj = columnChart.drawChart(xCategories,
						chartSeriesList, title, null, chartYAxis);
				UIUtils.dashBoardSetChartObject(request, chartObj);
				out.println(chartObj);
				out.close();
			} else if (action.equals("SubLossChart.prdloss")) {
				ColumnChart columnChart = new ColumnChart();
				CommonFilter commonFilter = (CommonFilter) httpSession
						.getAttribute("productionLoss");
				CommonFilter chrtCommonFilter = new CommonFilter();
				BeanUtils.copyProperties(chrtCommonFilter, commonFilter);

				PrintWriter out = response.getWriter();

				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();

				String month1 = fromMonth + "  -  " + toMonth;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String date = fromdate + "  -  " + todate;
				String title = "Production Loss Analysis - " + date;

				if (commonFilter.getMonwise().equals("Y")) {
					title = "Production Loss Analysis - " + month1;
				}

				if (chrtCommonFilter.getRowTotal() == null) {
					chrtCommonFilter.setRowTotal(Character.valueOf('Y'));
				}
				String paramCode = request.getParameter("PARAMCODE1");

				if (chrtCommonFilter.getParamCode() == null) {
					chrtCommonFilter.setParamCode(paramCode);
				}
				chrtCommonFilter.setISFORGRAPH(Character.valueOf('Y'));
				chrtCommonFilter.setPareto(Character.valueOf('N'));

				if ((chrtCommonFilter.getChkoccurchkbox() == "Y")
						|| (chrtCommonFilter.getChktimeChkBox() == "Y")) {
					CommonMessage.debugMsg("Occurence time is yes");
				}
				List<String[]> graphData = this.productionLossService
						.getAllproductionLossSubGrid(chrtCommonFilter);

				List<String> xCategories = new ArrayList<String>();
				List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();
				List<Double> dataLine = new ArrayList<Double>();// declaring
																// variable for
																// line chart
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();// declaring
																					// variable
																					// for
																					// charseries
																					// were
																					// the
																					// data
																					// is
																					// to
																					// bound
				ChartSeries chartSeriesLine = new ChartSeries();

				ChartSeries timeSeries = new ChartSeries();
				List<Double> timeData = new ArrayList<Double>();

				ChartSeries intstanceSeries = new ChartSeries();
				List<Double> instanceData = new ArrayList<Double>();

				for (String[] row : graphData) {

					if (row[0].equals("OCCURENCE")) {
						if ((!row[1].equalsIgnoreCase("AVERAGE"))
								&& (!row[1].equalsIgnoreCase("YTD"))) {
							instanceData.add(Double.valueOf(Double
									.parseDouble(row[2].replace(" ", "0")
											.replace("-", "0"))));
							xCategories.add(row[1]);
						}
					} else if (row[0].equals("TIME")) {
						if ((!row[1].equalsIgnoreCase("AVERAGE"))
								&& (!row[1].equalsIgnoreCase("YTD"))) {
							timeData.add(Double.valueOf(Double
									.parseDouble(row[2].replace(" ", "0")
											.replace("-", "0"))));
						}
					}
				}

				if (timeData.size() > 0) {
					timeSeries.setData(timeData);
					timeSeries.setType("spline");
					timeSeries.setName("Time");
					chartSeriesList.add(timeSeries);
					ChartYAxis yAxis = new ChartYAxis();
					yAxis.setMin(Integer.valueOf(0));
					yAxis.getTitle().setText("Count");
					chartYAxis.add(yAxis);
				}
				if (instanceData.size() > 0) {
					intstanceSeries.setData(instanceData);
					intstanceSeries.setType("spline");
					intstanceSeries.setName("Occurence");
					chartSeriesList.add(intstanceSeries);

					ChartYAxis yAxis = new ChartYAxis();
					yAxis.setMin(Integer.valueOf(0));
					yAxis.getTitle().setText("OCCURENCE");
					if (chartYAxis.size() > 0) {
						yAxis.setOpposite(Boolean.valueOf(true));
						intstanceSeries.setyAxis(Integer.valueOf(1));
					}
					chartYAxis.add(yAxis);
				}

				JSONObject chartObj = columnChart.drawChart(xCategories,
						chartSeriesList, title, null, chartYAxis);
				out.println(chartObj);
				out.close();
			} else if (action.equals("chartMonth.prdloss")) {
				ColumnChart columnChart = new ColumnChart();
				CommonFilter commonFilter;
				String forDashboard = request.getParameter("dashboard");
				CommonMessage.debugMsg("forDashboard....." + forDashboard);
				if (!"true".equals(forDashboard)) {
					CommonMessage.debugMsg("to check dash board");
					commonFilter = (CommonFilter) httpSession
							.getAttribute("productionLoss");
				} else {
					commonFilter = new CommonFilter();
					CommonMessage.debugMsg("to check dash board true ");
					FilterValues.getCommonFilters(request, commonFilter);
					FilterValues.getPCS(request, commonFilter);

				}

				CommonFilter chrtCommonFilter = new CommonFilter();
				BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
				String monthWise = request.getParameter("month")
						.substring(0, 8);

				chrtCommonFilter.setIsForTotalCnt('Y');

				chrtCommonFilter.setFromMonth(monthWise);
				chrtCommonFilter.setToMonth(monthWise);
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();

				String month1 = fromMonth + "  -  " + toMonth;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String date = fromdate + "  -  " + todate;
				String title = "Production Loss Analysis - " + date;

				if (commonFilter.getMonwise().equals("Y")) {
					title = "Production Loss Analysis - " + month1;
				}

				PrintWriter out = response.getWriter();
				if (chrtCommonFilter.getRowTotal() == null) {
					chrtCommonFilter.setRowTotal(Character.valueOf('Y'));
				}
				String paramCode = request.getParameter("PARAMCODE1");

				if (chrtCommonFilter.getParamCode() == null)
					chrtCommonFilter.setParamCode(paramCode);
				chrtCommonFilter.setPareto(Character.valueOf('N'));
				chrtCommonFilter.setISFORGRAPH(Character.valueOf('N'));

				List<String[]> graphData = this.productionLossService
						.getAllproductionLoss(chrtCommonFilter);
				List<String> xCategories = new ArrayList<String>();
				List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();
				List<Double> dataLine = new ArrayList<Double>();// declaring
																// variable for
																// line chart
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();// declaring
																					// variable
																					// for
																					// charseries
																					// were
																					// the
																					// data
																					// is
																					// to
																					// bound
				ChartSeries chartSeriesLine = new ChartSeries();// declaring
																// variable for
																// line were the
																// x and y asix
																// will be added

				ChartSeries timeSeries = new ChartSeries();
				List<Double> timeData = new ArrayList<Double>();

				ChartSeries intstanceSeries = new ChartSeries();
				List<Double> instanceData = new ArrayList<Double>();

				ChartSeries avgSeries = new ChartSeries();
				List<Double> avgData = new ArrayList<Double>();

				ChartSeries ytdSeries = new ChartSeries();
				List<Double> ytdData = new ArrayList<Double>();

				int xAxisFlag = 1;
				String[] xAxisData = (String[]) graphData.get(4);
				for (int i = 2; i < xAxisData.length - 6; i++) {
					xCategories.add(xAxisData[i]);
				}

				String[] getdata2 = (String[]) graphData.get(6);

				for (int i = 2; i < getdata2.length - 6; i++) {
					if (getdata2[1].equals("OCCURENCE"))
						instanceData.add(Double.valueOf(Double
								.parseDouble(getdata2[i].replace(" ", "0")
										.replace("-", "0"))));
					else if (getdata2[1].equals("TIME")) {
						timeData.add(Double.valueOf(Double
								.parseDouble(getdata2[i].replace(" ", "0")
										.replace("-", "0"))));
					}
				}
				if (timeData.size() > 0) {
					timeSeries.setData(timeData);
					timeSeries.setType("spline");
					timeSeries.setName("Time");
					chartSeriesList.add(timeSeries);
					ChartYAxis yAxis = new ChartYAxis();
					yAxis.setMin(Integer.valueOf(0));
					yAxis.getTitle().setText("Time");
					chartYAxis.add(yAxis);
				}
				if (instanceData.size() > 0) {
					intstanceSeries.setData(instanceData);
					intstanceSeries.setType("spline");
					intstanceSeries.setName("Occurrences");
					chartSeriesList.add(intstanceSeries);

					ChartYAxis yAxis = new ChartYAxis();
					yAxis.setMin(Integer.valueOf(0));
					yAxis.getTitle().setText("OCCURRENCES");
					if (chartYAxis.size() > 0) {
						yAxis.setOpposite(Boolean.valueOf(true));
						avgSeries.setyAxis(Integer.valueOf(1));
					}
					chartYAxis.add(yAxis);
				}

				if (avgData.size() > 0) {
					intstanceSeries.setData(avgData);
					intstanceSeries.setType("spline");
					intstanceSeries.setName("Occurence");
					chartSeriesList.add(intstanceSeries);
					ChartYAxis yAxis = new ChartYAxis();
					yAxis.setMin(Integer.valueOf(0));
					yAxis.getTitle().setText("Average");
					if (chartYAxis.size() > 0) {
						yAxis.setOpposite(Boolean.valueOf(true));
						avgSeries.setyAxis(Integer.valueOf(1));
					}
					chartYAxis.add(yAxis);
				}

				if (ytdData.size() > 0) {
					intstanceSeries.setData(ytdData);
					intstanceSeries.setType("spline");
					intstanceSeries.setName("Occurence");
					chartSeriesList.add(intstanceSeries);

					ChartYAxis yAxis = new ChartYAxis();
					yAxis.setMin(Integer.valueOf(0));
					yAxis.getTitle().setText("YTD");
					if (chartYAxis.size() > 0) {
						yAxis.setOpposite(Boolean.valueOf(true));
						ytdSeries.setyAxis(Integer.valueOf(1));
					}
					chartYAxis.add(yAxis);
				}

				JSONObject chartObj = columnChart.drawChart(xCategories,
						chartSeriesList, title, null, chartYAxis);
				UIUtils.dashBoardSetChartObject(request, chartObj);
				out.println(chartObj);
				out.close();
			} else if (action.equals("chartMonthWise.prdloss")) {
				String monthWise = request.getParameter("month")
						.substring(0, 8);
				String occ = request.getParameter("occ");
				CommonFilter commonFilter;
				String forDashboard = request.getParameter("dashboard");
				CommonMessage.debugMsg("forDashboard....." + forDashboard);
				if (!"true".equals(forDashboard)) {
					CommonMessage.debugMsg("to check dash board");
					commonFilter = (CommonFilter) httpSession
							.getAttribute("productionLoss");
				} else {
					commonFilter = new CommonFilter();
					CommonMessage.debugMsg("to check dash board true ");
					// FilterValues.getCommonFilters(request,commonFilter);
					// FilterValues.getPCS(request, commonFilter);
					populateCommonFilter(request, "productionLoss", false);

				}

				CommonFilter chrtCommonFilter = new CommonFilter();
				BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
				ColumnChart columnChart = new ColumnChart();

				PrintWriter out = response.getWriter();
				if (chrtCommonFilter.getRowTotal() == null)
					chrtCommonFilter.setRowTotal(Character.valueOf('Y'));
				if (monthWise != null) {
					chrtCommonFilter.setFromMonth(commonFilter.getFromMonth());
					chrtCommonFilter.setToMonth(commonFilter.getToMonth());
				}

				chrtCommonFilter.setPareto(Character.valueOf('N'));
				chrtCommonFilter.setISFORGRAPH(Character.valueOf('N'));
				chrtCommonFilter.setIsForTotalCnt('Y');
				chrtCommonFilter.setRemoveBlank("Y");
				List graphData = this.productionLossService
						.getAllproductionLossGraph(chrtCommonFilter);

				List xCategories = new ArrayList();
				List dataLine = new ArrayList();
				List chartSeriesList = new ArrayList();
				ChartSeries chartSeriesLine = new ChartSeries();

				ChartSeries barChart = new ChartSeries();
				List barDataList = new ArrayList();
				int len = ((String[]) graphData.get(6)).length;
				int totlen = len - 17;

				Double countSum = Double.valueOf(0.0D);

				int cnt = paretosum(graphData, chrtCommonFilter);

				for (int i = 2; i < len - 15; i++) {
					xCategories.add(((String[]) graphData.get(4))[i]);
					if ((((String[]) graphData.get(6))[i].trim() != "-")
							&& (Double
									.parseDouble(((String[]) graphData.get(6))[i]) < 0.0D)) {
						((String[]) graphData.get(6))[i] = "0";
					}

					barDataList.add(Double.valueOf(Double
							.parseDouble(((String[]) graphData.get(6))[i]
									.replace(" ", "0").replace("-", "0"))));
					countSum = Double.valueOf(countSum.doubleValue()
							+ Math.abs(Double.parseDouble(((String[]) graphData
									.get(6))[i].replace(" ", "0").replace("-",
									"0"))));
					double cntPer = countSum.doubleValue() * 100.0D / cnt;

					DecimalFormat df = new DecimalFormat(".00");
					df.setRoundingMode(RoundingMode.FLOOR);

					dataLine.add(Double.valueOf(Double.parseDouble(df
							.format(cntPer))));
				}
				if (occ.equals("1"))
					barChart.setName("Occurence");
				else
					barChart.setName("Time");
				barChart.setData(barDataList);
				barChart.setType("column");
				chartSeriesLine.setName("Cummulative");
				chartSeriesLine.setData(dataLine);
				chartSeriesLine.setType("line");
				chartSeriesLine.setyAxis(Integer.valueOf(1));
				chartSeriesList.add(barChart);
				chartSeriesList.add(chartSeriesLine);

				List chartYAxis = new ArrayList();
				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(Integer.valueOf(0));
				if (occ.equals("1"))
					yAxis.getTitle().setText("Occurance");
				else
					yAxis.getTitle().setText("Time in Minutes");
				ChartYAxis y1Axis = new ChartYAxis();
				y1Axis.setOpposite(Boolean.valueOf(true));
				y1Axis.setMin(Integer.valueOf(0));
				y1Axis.getTitle().setText("Cummulative Percentage");
				chartYAxis.add(yAxis);
				chartYAxis.add(y1Axis);

				JSONObject chartObj = columnChart.drawChart(xCategories,
						chartSeriesList, "Production Loss Analysis - "
								+ monthWise, null, chartYAxis);
				UIUtils.dashBoardSetChartObject(request, chartObj);
				out.println(chartObj);
				out.close();
			} else if (action.equals("chartPareto.prdloss")) {
				String paramCode = request.getParameter("PARAMCODE");
				String selectmonth = request.getParameter("selectmonth");

				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = (CommonFilter) httpSession
						.getAttribute("productionLoss");
				CommonFilter chrtCommonFilter = new CommonFilter();
				BeanUtils.copyProperties(chrtCommonFilter, commonFilter);

				commonFilter.setISFORGRAPH(Character.valueOf('Y'));
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();

				String month1 = fromMonth + "  -  " + toMonth;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String date = fromdate + "  -  " + todate;
				String title = "Production Loss Analysis - " + date;

				if (commonFilter.getMonwise().equals("Y")) {
					title = "Production Loss Analysis - " + month1;
				}
				ColumnChart columnChart = new ColumnChart();

				if (chrtCommonFilter.getRowTotal() == null)
					chrtCommonFilter.setRowTotal(Character.valueOf('Y'));
				/*
				 * if (chrtCommonFilter.getParamCode() == null) {
				 * chrtCommonFilter.setParamCode(paramCode); }
				 */
				if (UIUtils.isValidKeyId(paramCode)) {
					chrtCommonFilter.setParamCode(paramCode);
				}
				chrtCommonFilter.setPareto(Character.valueOf('Y'));
				List<String[]> graphData = this.productionLossService
						.getAllproductionLossforparatograph(chrtCommonFilter,
								selectmonth);
				CommonMessage.debugMsg("graphData.size()" + graphData.size());
				/*
				 * List xCategories = new ArrayList(); List dataLine = new
				 * ArrayList(); List chartSeriesList = new ArrayList();
				 * ChartSeries chartSeriesLine = new ChartSeries();
				 * 
				 * ChartSeries barChart = new ChartSeries(); List barDataList =
				 * new ArrayList(); CommonMessage.debugMsg(" graphData.size()" +
				 * graphData.size()); for (int i = 0; i < graphData.size(); i++)
				 * {
				 * 
				 * xCategories.add(((String[])graphData.get(i)));
				 * barDataList.add
				 * (Double.valueOf(Double.parseDouble(((String[])graphData
				 * .get(i)).replace(" ", "0"))));
				 * dataLine.add(Double.valueOf(Double
				 * .parseDouble(((String[])graphData.get(i))[0].replace(" ",
				 * "0")))); }
				 * 
				 * barChart.setName("DownTime"); barChart.setData(barDataList);
				 * barChart.setType("column");
				 * chartSeriesLine.setName("Cummulative");
				 * chartSeriesLine.setData(dataLine);
				 * chartSeriesLine.setType("line");
				 * chartSeriesLine.setyAxis(Integer.valueOf(1));
				 * 
				 * chartSeriesList.add(barChart);
				 * chartSeriesList.add(chartSeriesLine);
				 * 
				 * List chartYAxis = new ArrayList(); ChartYAxis yAxis = new
				 * ChartYAxis(); yAxis.setMin(Integer.valueOf(0));
				 * yAxis.getTitle().setText("DownTime in Minutes"); ChartYAxis
				 * y1Axis = new ChartYAxis();
				 * y1Axis.setOpposite(Boolean.valueOf(true));
				 * y1Axis.setMin(Integer.valueOf(0));
				 * y1Axis.getTitle().setText("Cummulative Percentage");
				 * chartYAxis.add(yAxis); chartYAxis.add(y1Axis);
				 * 
				 * JSONObject chartObj = columnChart.drawChart(xCategories,
				 * chartSeriesList, title, null, chartYAxis);
				 * out.println(chartObj); out.close();
				 */
				JSONObject chartObj = null;
				String chartType = request.getParameter("chType");
				// if( chartType == null)
				chartObj = processparatoChart(graphData, commonFilter);

				out.print(chartObj);
				out.close();
			}else if (action.equals("filterXmlProductionLossRpt_input.prdloss")) {
				response.setContentType("xml");
				CommonMessage.debugMsg("action " + action);
				UIUtils.forwardRequest(request, response,
						"/tiles/xml/ProductionLossRpt.xml");
			}
			else if(action.equals("ProductionLossRpt_input.prdloss")){
				CommonMessage.debugMsg("Action :" +action);
				
				RequestDispatcher rd = request
						.getRequestDispatcher("pages/Reports/ProductionLossReport.jsp");
				rd.forward(request, response);
				
			}else if(action.equals("ProductionLossRpt_getCol.prdloss")){
				CommonMessage.debugMsg("Action :" +action);
				
				CommonFilter commonFilter = populateCommonFilter(request,"ProductRptCommonFilter",true);
				
				PrintWriter out = response.getWriter();
				String colModelIdent = "lossReportColModel";				
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.LossReportModel",colModelIdent));
				//httpSession.removeAttribute("ProductRptColModel");
				//httpSession.setAttribute("ProductRptColModel", colModelIdent);
			
			}else if(action.equals("ProductionLossRpt_getData.prdloss")){
				
				CommonMessage.debugMsg("Action :" +action);
				PrintWriter out=response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"ProductRptCommonFilter",false);
				List<String[]> masterGrid = productionLossService.getLossByFunctionalLocation(commonFilter);
				CommonMessage.debugMsg("Action: displaymasterGrid"+masterGrid );
				JSONObject dataJson = UIUtils.convertToJqGridTableObject(masterGrid,request, 0, 0,commonFilter.getTotalRecordCnt() );
				out.print(dataJson);
				CommonMessage.debugMsg("jsonObject  :"+dataJson);
				httpSession.removeAttribute("ProductRptCommonFilter");
  			 	httpSession.setAttribute("ProductRptCommonFilter", commonFilter);
			}
			else if( action.equals("ProductionLossRpt_getExcel.prdloss")){
				
				CommonFilter commonFilter = populateCommonFilter(request,"ProductRptCommonFilter",false);			
								
				JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				
				tblJSONObj.put("title", "Loss  Report"); 
				String format = ExcelUtils.getFormat(request);
				Workbook wb = productionLossService.getLossExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "LossReport", format);
				
			}
			
		}
	}
	private JSONObject processparetoChart(String titlename,List<String[]> improvementVsCompletedList,CommonFilter commonFilter, String selmonth) {
		ChartOptionBean lineChart = new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();
		CommonMessage.debugMsg("improvementVsCompletedList.size(): " +improvementVsCompletedList.size());
		String[] header = improvementVsCompletedList.get(0);
		String[] data = new String[header.length];
		String prevMonth = null;

		String subTitle = "";// data[2];

		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();

		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();

		double dat=0;
		for (int d = 0; d < improvementVsCompletedList.size(); d++) {
			data = improvementVsCompletedList.get(d);
			for (int i = 1; i <= header.length; i++) {				
				if (i == 4) {
					if (UIUtils.isValidKeyId(data[3])) {					
						identifiedData.add(Double.valueOf(Double.parseDouble(data[3])));
					}else{
						identifiedData.add(Double.valueOf(Double.parseDouble("0")));
					}					
				}
				if (i == 5) {
					if (UIUtils.isValidKeyId(data[3])) {					
						dat = dat + Double.parseDouble(data[4].trim());
					}else{
						dat=0;
					}					
					Double pct = 0.00;					
					pct =  (Math.round(dat * 100.0) / 100.0);
					CommonMessage.debugMsg("pct=="+pct);
					if (pct>100)
						pct = (double) 100.00;
					completedData.add(pct);
					
				}
			}
			if (prevMonth == null || !data[1].equals(prevMonth)) {
				xAxisCategory.add(data[1]);
			}
			prevMonth = data[1];
		}
		String Title = titlename+"-Loss Pareto - " + selmonth;
		if(selmonth.equals("TOTAL")){
			Title += " - "+commonFilter.getFromMonth() +" - " +commonFilter.getToMonth();
		}
		if (identifiedData.size() > 0) {
			timeSeries.setData(identifiedData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setyAxis(0);
			timeSeries.setName("Losses");
			chartSeriesList.add(timeSeries);
			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(0);
			yAxis.getTitle().setText("Time");
			chartYAxis.add(yAxis);
		}
		if (completedData.size() > 0) {
			intstanceSeries.setData(completedData);
			intstanceSeries.setyAxis(1);
			intstanceSeries.setType(ChartTypes.SPLINE);
			intstanceSeries.setName("cumulative Percentage");
			chartSeriesList.add(intstanceSeries);			
			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(0);
			yAxis.setMax(100);			
			yAxis.setOpposite(true);
			yAxis.getTitle().setText("cumulative Percentage");
			chartYAxis.add(yAxis);			
		}
		ChartXAxis xaxis = new ChartXAxis();
		if (commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Losses");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, Title,subTitle, chartYAxis);

	}
	
	private void processLossTimeNew(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("productionLoss");
		
		String forDashboard = request.getParameter("dashboard");
		String keyid=request.getParameter("rowid");
		CommonMessage.debugMsg("The KeyId is:::"+keyid);
		String rowId=request.getParameter("rownum");
		CommonMessage.debugMsg("The KeyId is:::"+rowId);
		CommonFilter chartCommonFilter = new CommonFilter(); 

		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute("productionLoss");
		}
		else{
			commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);	
			
		}
		
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		if(chartCommonFilter.getRowTotal()==null)
		chartCommonFilter.setRowTotal('N');
	    List<String[]> Losslist =productionLossService.getAllLossTimeNewChart(commonFilter,rowId);	

		List<String[]> coubnts=transposeListArr1(Losslist);
		JSONObject chartObj = null;
		if(Losslist != null && Losslist.size() > 0)
		{
			
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processBarChartLoss(lcnname,Losslist,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	
	private JSONObject processBarChartLoss(String titlename,List<String[]> LossList,CommonFilter commonFilter){
		
		if( LossList == null || LossList.size() <= 1  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
	     
		StringBuilder date=new StringBuilder();
        if(commonFilter.getRowTotal()==null)
        	commonFilter.setRowTotal('N');
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		    StringBuilder title=new StringBuilder(titlename+"-Loss Time Report").append(" - ").append(date);

		
		String[] header =  LossList.get(1); // -- changing 
		String[] month =  LossList.get(0); 
		String[] data =  LossList.get(2);
		
		int rSize = LossList.size()-1;
		 CommonMessage.debugMsg("The RSize:::::"+rSize);
		int cSize = header.length-3;
		 CommonMessage.debugMsg("The CSize:::::"+cSize);
		//String subTitle = data[2];
		String subTitle="";
		Double [][] grData = new Double[rSize][cSize];
		// ----- VIgnesh i =2 
		for( int i = 2; i < header.length-1;i++ ){
			for (int j = 1; j< LossList.size();j++) {
				data  =  LossList.get(j);
				CommonMessage.debugMsg("The Data IS:::" + (UIUtils.isValidKeyId(data[i]) ? data[i] : 0));
					grData[j-1][i-2] = Double.parseDouble( UIUtils.isValidKeyId(data[i]) ? data[i] : "0");
					CommonMessage.debugMsg("The GRData Length"+grData.length);
			}
				xAxisCategory.add(month[i]);
				 CommonMessage.debugMsg("The Month Of i::::"+month[i]);
		}
	    
		for(int k=0;k<grData.length-1;k++) {
			 
			 ChartSeries timeSeries = new ChartSeries();
			 timeSeries.setData(Arrays.asList(grData[k]));
			 timeSeries.setType(ChartTypes.COLUMN);
			 timeSeries.setName(LossList.get(k+1)[1]);
			 CommonMessage.debugMsg("LossList Data::::::::"+LossList.get(k+1)[1]);
			 chartSeriesList.add(timeSeries);			
			 }	
		  ChartYAxis yAxis = new ChartYAxis(); 
		 yAxis.setMin(0);
		 yAxis.getTitle().setText("Hours");
		 chartYAxis.add(yAxis);
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	} 
	
	private List<String[]> transposeListArr1(List<String[]> dataList)
	{
		CommonMessage.debugMsg("INSIDE transposeListArr1"+ dataList.size());
		if( dataList.size() <=0 ) return null;
		CommonMessage.debugMsg("INSIDE transposeListArr2"+ dataList.size());
		List<String[]> transposeList = new ArrayList<String[]>();		
		for( int i =1; i<dataList.size(); i++)
		{	
			String [] tRow = new String [ dataList.get(0).length];
			CommonMessage.debugMsg("tRow [ j ] : "+tRow.length);
			for (int j=1; j<dataList.get(0).length;j++)
			{
				tRow [ j ]= dataList.get(i)[j];//.equals("0")?dataList.get(i)[j].replace("0", "-"):dataList.get(i)[j];
				
			}
			transposeList.add(tRow);
		}
		return transposeList;
	}
	
	private JSONObject processparetoChart_ok(String titlename,List<String[]> improvementVsCompletedList,CommonFilter commonFilter, String selmonth) {
		ChartOptionBean lineChart = new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();
		
		String[] header = improvementVsCompletedList.get(0);
		String[] data = new String[header.length];
		String prevMonth = null;

		String subTitle = "";// data[2];

		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();

		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();

		//DecimalFormat df =new DecimalFormat("0.00");
		double dat=0;
		for (int d = 0; d < improvementVsCompletedList.size(); d++) {
			data = improvementVsCompletedList.get(d);

			for (int i = 1; i <= header.length; i++) {
				
				if (i == 4) {
					String dat1 = data[3].trim();
					if ("".equals(dat1))
						dat1 = "0";
					identifiedData.add(Double.valueOf(Double.parseDouble(dat1)));
				}
				if (i == 5) {
					//String data2  = df.format(data[4].trim()); 
					dat = dat + Double.parseDouble(data[4].trim());	
					
					if ("".equals(dat))
						dat = 0;
					Double pct = 0.00;
					
					pct =  (Math.round(dat * 100.0) / 100.0);
					CommonMessage.debugMsg("pct=="+pct);
					if (pct>100)
						pct = (double) 100.00;
					completedData.add(pct);
					//completedData.add((double)Math.round(dat));
				}

			}

			if (prevMonth == null || !data[1].equals(prevMonth)) {
				xAxisCategory.add(data[1]);
			}
			prevMonth = data[1];

		}

		String Title = titlename+"-Loss Pareto of the Month " + selmonth;
		
	
		//CommonMessage.debugMsg("identifiedData.size()" + identifiedData.size());
		//CommonMessage.debugMsg("completedData.size()" + completedData.size());
		if (identifiedData.size() > 0) {
			timeSeries.setData(identifiedData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setyAxis(0);
			timeSeries.setName("Losses");
			chartSeriesList.add(timeSeries);
			// yAxis.getTitle().setText("Identified");
			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(0);
			yAxis.getTitle().setText("Time");
			chartYAxis.add(yAxis);

		}

		if (completedData.size() > 0) {
			intstanceSeries.setData(completedData);
			intstanceSeries.setyAxis(1);
			intstanceSeries.setType(ChartTypes.SPLINE);
			intstanceSeries.setName("cumulative Percentage");
			chartSeriesList.add(intstanceSeries);
			
			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(0);
			yAxis.setMax(100);			
			yAxis.setOpposite(true);
			yAxis.getTitle().setText("cumulative Percentage");
			chartYAxis.add(yAxis);

			
		}
		ChartXAxis xaxis = new ChartXAxis();
		if (commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Losses");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, Title,
				subTitle, chartYAxis);

	}
	private JSONObject processTrendChartNew(String titlename,List<String[]> improvementVsCompletedList,	CommonFilter commonFilter, String selmonth) {
		ChartOptionBean lineChart = new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();

		StringBuilder date = new StringBuilder();
		String[] header = improvementVsCompletedList.get(0);
		String[] month = improvementVsCompletedList.get(0);
		CommonMessage.debugMsg("THE MONTH"+month);
		CommonMessage.debugMsg("THW MONTH LENGTH"+month.length);
		String[] data = new String[header.length];
		String prevMonth = null;

		String subTitle = "";
		CommonMessage.debugMsg("improvementVsCompletedList.size: " +improvementVsCompletedList.size());

		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();
		if(improvementVsCompletedList.size()==1){
			for (int d = 0; d < improvementVsCompletedList.size(); d++) {		
				data = improvementVsCompletedList.get(d);
				for (int i = 1; i < header.length; i++) {				
					if (selmonth.equals(month[i])) {						
						identifiedData.add(Double.valueOf(Double.parseDouble("0")));						
					}
				}			
				xAxisCategory.add(data[0]);				
		   }
			
		}else{

			for (int d = 1; d < improvementVsCompletedList.size(); d++) {		
				data = improvementVsCompletedList.get(d);
				for (int i = 1; i < header.length; i++) {				
					if (selmonth.equals(month[i])) {
						/*
						String dat = data[i].trim();
						if ("".equals(dat))
							dat = "0";
						identifiedData.add(Double.valueOf(Double.parseDouble(dat)));*/
						if (UIUtils.isValidKeyId(data[i])) {					
							identifiedData.add(Double.valueOf(Double.parseDouble(data[i])));
						}else{
							identifiedData.add(Double.valueOf(Double.parseDouble("0")));
						}
						
					}
				}
			//	if (prevMonth == null || !data[0].equals(prevMonth)) {
				xAxisCategory.add(data[0]);
				/*}
				prevMonth = data[0];*/
		   }
		}
		String Title = titlename+"New Loss Trend - " + selmonth;
		if(selmonth.equals("TOTAL")){
			Title += " - " +commonFilter.getFromMonth() +" - " +commonFilter.getToMonth();
		}

		
		
		if (identifiedData.size() > 0) {
			timeSeries.setData(identifiedData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setName(selmonth);
			chartSeriesList.add(timeSeries);
			// yAxis.getTitle().setText("Identified");
			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(0);
			yAxis.getTitle().setText("Time");
			chartYAxis.add(yAxis);

		}
		if (completedData.size() > 0) {
			intstanceSeries.setData(completedData);
			intstanceSeries.setType(ChartTypes.BAR);
			intstanceSeries.setName("Implemented");
			chartSeriesList.add(intstanceSeries);
			
		}
		ChartXAxis xaxis = new ChartXAxis();
		if (commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Losses");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, Title,
				subTitle, chartYAxis);

	}
	private JSONObject processTrendChart(String titlename,List<String[]> improvementVsCompletedList,	CommonFilter commonFilter, String selmonth) {
		ChartOptionBean lineChart = new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();

		//String[] data = improvementVsCompletedList.get(improvementVsCompletedList.size() - 1);

		StringBuilder date = new StringBuilder();

		//String[] header = improvementVsCompletedList.get(2);
		String[] header = improvementVsCompletedList.get(0);
		String[] month = improvementVsCompletedList.get(0);
		String[] data = new String[header.length];
		
		/*if(improvementVsCompletedList.size()>1){
			data = improvementVsCompletedList.get(improvementVsCompletedList.size() - 1);
		}
		*/
		String prevMonth = null;

		String subTitle = "";// data[2];
		CommonMessage.debugMsg("improvementVsCompletedList.size: " +improvementVsCompletedList.size());

		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();
		if(improvementVsCompletedList.size()==1){
			for (int d = 0; d < improvementVsCompletedList.size(); d++) {		
				data = improvementVsCompletedList.get(d);
				for (int i = 1; i < header.length; i++) {				
					if (selmonth.equals(month[i])) {						
						identifiedData.add(Double.valueOf(Double.parseDouble("0")));						
					}
				}			
				xAxisCategory.add(data[0]);				
		   }
			
		}else{

			for (int d = 1; d < improvementVsCompletedList.size(); d++) {		
				data = improvementVsCompletedList.get(d);
				for (int i = 1; i < header.length; i++) {				
					if (selmonth.equals(month[i])) {
						/*
						String dat = data[i].trim();
						if ("".equals(dat))
							dat = "0";
						identifiedData.add(Double.valueOf(Double.parseDouble(dat)));*/
						if (UIUtils.isValidKeyId(data[i])) {					
							identifiedData.add(Double.valueOf(Double.parseDouble(data[i])));
						}else{
							identifiedData.add(Double.valueOf(Double.parseDouble("0")));
						}
						
					}
				}
			//	if (prevMonth == null || !data[0].equals(prevMonth)) {
				xAxisCategory.add(data[0]);
				/*}
				prevMonth = data[0];*/
		   }
		}
		String Title = titlename+"-Loss Trend - " + selmonth;
		if(selmonth.equals("TOTAL")){
			Title += " - " +commonFilter.getFromMonth() +" - " +commonFilter.getToMonth();
		}

		if (identifiedData.size() > 0) {
			timeSeries.setData(identifiedData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setName(selmonth);
			chartSeriesList.add(timeSeries);
			// yAxis.getTitle().setText("Identified");
			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(0);
			yAxis.getTitle().setText("Time");
			chartYAxis.add(yAxis);

		}
		if (completedData.size() > 0) {
			intstanceSeries.setData(completedData);
			intstanceSeries.setType(ChartTypes.BAR);
			intstanceSeries.setName("Implemented");
			chartSeriesList.add(intstanceSeries);
			
		}
		ChartXAxis xaxis = new ChartXAxis();
		if (commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Losses");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, Title,
				subTitle, chartYAxis);

	}

	private JSONObject processLineChartNew(	List<String[]> improvementVsCompletedList, CommonFilter commonFilter) {
		/*if (improvementVsCompletedList == null	|| improvementVsCompletedList.size() <2)
			return null;*/

		ChartOptionBean lineChart = new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();
		String[] header = improvementVsCompletedList.get(0);
		String[] data = new String[header.length];			
		String[] month = improvementVsCompletedList.get(0);

		String rno = commonFilter.getRange();
		CommonMessage.debugMsg("Loss Name :" +rno);
		//if (UIUtils.isValidKeyId(rno))
			//CommonMessage.debugMsg("UIUtils.isValidKeyId(rno):" +UIUtils.isValidKeyId(rno));
		int index = -1;
		if (UIUtils.isValidKeyId(rno)){
			for (int i=0; i<improvementVsCompletedList.size(); i++) {
				String [] element = improvementVsCompletedList.get(i);
				CommonMessage.debugMsg("element[0]: " +element[1]);
				CommonMessage.debugMsg(element[1]+".equals("+rno+"): " +element[1].equals(rno));
				if(element[1].contains(rno)){
					index = i ;
					data = improvementVsCompletedList.get(index);
					break;
					
				}
				
			}
			if("TOTAL".equals(rno) && improvementVsCompletedList.size()>2){
				index = improvementVsCompletedList.size()-1 ;
				data = improvementVsCompletedList.get(index);
			}
		}
		
		
		
		String prevMonth = null;

		String subTitle = "";// data[2];

		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();	
		// -- Vignesh chnaged i to 2
		for (int i = 2; i < header.length-1; i++) {	
			CommonMessage.debugMsg("row("+i+"): " +data[i]);
			if (UIUtils.isValidKeyId(data[i])) {
				CommonMessage.debugMsg("row("+i+") if: " +data[i]);
				identifiedData.add(Double.valueOf(Double.parseDouble(data[i])));
			}else{
				CommonMessage.debugMsg("row("+i+") else: " +data[i]);
				identifiedData.add(Double.valueOf(Double.parseDouble("0")));
			}
			
			
			if (prevMonth == null || !month[i].equals(prevMonth)) {
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		
		String Title ="";
		if(UIUtils.isValidKeyId(data[0])){
			//-- vignesh chnaged data to 1
			Title = data[1]  + " - Production Loss Analysis -  ";
		}else{
			Title ="Production Loss Analysis -  ";
		}
		

		if (identifiedData.size() > 0) {
			timeSeries.setData(identifiedData);
			timeSeries.setType(ChartTypes.LINE);
			//-- vignesh chnaged data to 1
			timeSeries.setName(data[1]);
			chartSeriesList.add(timeSeries);
			// yAxis.getTitle().setText("Identified");
			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(0);
			yAxis.getTitle().setText("Loss Hours");
			chartYAxis.add(yAxis);

		}
		
		ChartXAxis xaxis = new ChartXAxis();
		if (commonFilter.getMonwise().equals("Y")){
			xaxis.getTitle().setText("Month");
			Title += commonFilter.getFromMonth() + " - " + commonFilter.getToMonth();
		}
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, Title,subTitle, chartYAxis);

	}

	private JSONObject processparatoChart(List<String[]> trendList,
			CommonFilter commonFilter) throws Exception {

		if (trendList == null)
			return null;
		ChartOptionBean lineChart = new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();

		String title = "Loss Trend";

		String[] header = trendList.get(0);
		String[] month = trendList.get(0);
		// String [] data = trendList.get(Integer.parseInt(rowId));
		String[] cummulative = null;

		String prevMonth = null;

		String subTitle = "";

		// declaring variable for line chart

		// declaring variable for bar chart

		for (int d = 1; d < trendList.size(); d++) {
			cummulative = trendList.get(d);
			ChartSeries chartSeriesLine = new ChartSeries();
			ChartSeries chartSeriesLine1 = new ChartSeries();
			List<Double> dataLine = new ArrayList<Double>();
			List<Double> lineYChartList = new ArrayList<Double>();
			double greatervalue = 0;

			String descsql = "select * from ( ";
			for (int i = 3; i < 22; i++) {

				double losstime = Double.parseDouble(cummulative[i]);

				if (losstime > 0) {
					if (i > 3) {
						descsql = descsql + " union all ";
					}

					descsql = descsql + " select  " + cummulative[i] + ",'"
							+ month[i] + "' from dual ";
				}
			}

			descsql = descsql + " ) order by 1 desc";

			CommonMessage.debugMsg("descsql" + descsql);
			List<String[]> descloss = productionLossService
					.getdescorderlosses(descsql);

			for (int r = 1; r < descloss.size(); r++) {

				CommonMessage.debugMsg("cummulative[i]" + descloss.get(r)[0]);

				dataLine.add(Double.parseDouble(descloss.get(r)[0]));

				if (prevMonth == null || !descloss.get(r)[1].equals(prevMonth)) {
					xAxisCategory.add(descloss.get(r)[1]);
				}
				prevMonth = descloss.get(r)[1];

			}

			if (dataLine.size() > 0) {
				chartSeriesLine.setName(cummulative[0]);// adding data
				chartSeriesLine.setData(dataLine);// declaring name for the Line
				chartSeriesLine.setType(ChartTypes.COLUMN);// declaring which
															// axis it shd be
															// set
				// chartSeriesLine.setyAxis(1);
				chartSeriesList.add(chartSeriesLine);

				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				yAxis.getTitle().setText("Loss Trend");
				chartYAxis.add(yAxis);

			}

			/*
			 * if(lineYChartList.size() > 0){
			 * chartSeriesLine1.setName("Cummulative Closed");//adding data
			 * chartSeriesLine1.setData(lineYChartList);//declaring name for the
			 * Line chartSeriesLine1.setType(ChartTypes.COLUMN);//declaring
			 * which axis it shd be set //chartSeriesLine1.setyAxis(1);
			 * 
			 * chartSeriesList.add(chartSeriesLine1); }
			 */
		}

		return lineChart.drawChart(xAxisCategory, chartSeriesList, title,
				subTitle, chartYAxis);
	}

	private int paretosum(List<String[]> graphData, CommonFilter commonFilter) {
		int len = ((String[]) graphData.get(6)).length;
		int count = 0;
		for (int i = 2; i < len - 15; i++) {
			count = (int) (count + Math.abs(Double
					.parseDouble(((String[]) graphData.get(6))[i].replace(" ",
							"0").replace("-", "0"))));
		}
		return count;
	}

	private JSONObject processLineChart(List<String[]> productionLossList,
			CommonFilter commonFilter) {
		if ((productionLossList == null) || (productionLossList.size() <= 2)) {
			return null;
		}
		ChartOptionBean lineChart = new ChartOptionBean();
		List xAxisCategory = new ArrayList();
		List chartSeriesList = new ArrayList();
		List chartYAxis = new ArrayList();
		String title = "Cumulative Tag Summary Identified VS Removed";

		String[] header = (String[]) productionLossList.get(1);
		String[] month = (String[]) productionLossList.get(0);
		String[] data = (String[]) productionLossList.get(3);

		String prevMonth = null;
		String subTitle = "";

		ChartSeries timeSeries = new ChartSeries();
		List timeData = new ArrayList();

		ChartSeries intstanceSeries = new ChartSeries();
		List instanceData = new ArrayList();

		for (int i = 2; i < header.length; i++) {
			if (header[i].contains("IDENTIFIED")) {
				timeData.add(Double.valueOf(Double.parseDouble(data[i])));
			} else if (header[i].contains("REMOVED")) {
				instanceData.add(Double.valueOf(Double.parseDouble(data[i])));
			}
			if ((prevMonth == null) || (!month[i].equals(prevMonth))) {
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if (timeData.size() > 0) {
			timeSeries.setData(timeData);
			timeSeries.setType("spline");
			timeSeries.setName("Identified");
			chartSeriesList.add(timeSeries);

			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(Integer.valueOf(0));
			yAxis.getTitle().setText("Count");
			chartYAxis.add(yAxis);
		}
		if (instanceData.size() > 0) {
			intstanceSeries.setData(instanceData);
			intstanceSeries.setType("spline");
			intstanceSeries.setName("Removed");
			chartSeriesList.add(intstanceSeries);
		}

		return lineChart.drawChart(xAxisCategory, chartSeriesList, title,
				subTitle, chartYAxis);
	}

	private JSONObject getTableModel1(List<String[]> headers, String caption) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();

		String[] colHeader = (String[]) headers.get(0);
		// String[] colHeader1 = (String[])headers.get(1);

		/*
		 * colHeader[4] = "Loss / Month"; colHeader1[4] = "Loss";
		 * 
		 * colHeader[5] = ""; colHeader1[5] = "UoM";
		 */
		String[] emptyrow = new String[colHeader.length];

		emptyrow[0] = "";
		emptyrow[1] = "";

		for (int i = 2; i < colHeader.length; i++) {
			emptyrow[i] = "";
		}

		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		// jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);

		jqGridTableModel.setTableHeight(270);
		jqGridTableModel.setLoadOnce(true);

		for (int i = 0; i < colHeader.length; i++) {
			
			CommonMessage.debugMsg("inside for colHeader["+i+"] = "+colHeader[i]); 
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(150);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			// if(i==1)
			// jqGridColModel.setKey(true);
			if (i == 1) {
				// jqGridColModel.setHidden(true);
				// jqGridColModel.setKey(true);
			}
			
			if (i < 4) {
				// jqGridColModel.setHidden(true);
				// jqGridColModel.setKey(true);
			} else if (i == 4) {
				// jqGridColModel.setWidth(350);
				jqGridColModel.setAlign("left");
			} else if (i == 5) {
				// jqGridColModel.setWidth(50);
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
			} else if (i > 1) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("right");
				jqGridColModel.setEditable(false);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "73%%");
		return tableModel;
	}

	private List<String[]> transposeListArr(List<String[]> dataList, String hide) {
		if (dataList.size() <= 0)
			return null;
		int len = 0;
		List transposeList = new ArrayList();
		if (hide.equals("Y"))
			len = ((String[]) dataList.get(0)).length - 2;
		else if (hide.equals("N"))
			len = ((String[]) dataList.get(0)).length;
		for (int i = 0; i < len; i++) {
			String[] tRow = new String[dataList.size()];
			for (int j = 0; j < dataList.size(); j++) {
				tRow[j] = (((String[]) dataList.get(j))[i].equals("0") ? ((String[]) dataList
						.get(j))[i].replace("0", "-") : ((String[]) dataList
						.get(j))[i]);
			}

			transposeList.add(tRow);
		}

		return transposeList;
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request,
			String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession
				.getAttribute(beanIdentifier);
		if ((commonFilter != null) && (!createNew)) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();
			commonFilter = FilterValues.getCommonFilters(request, commonFilter);
			commonFilter = FilterValues.getPCS(request, commonFilter);
			commonFilter.setViewClick('Y');

			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}

		return commonFilter;
	}

	private JqGridColModel getColModel(String colIndex, int width,
			String allign, boolean hidden, boolean groupbyfield, boolean key) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);

		jqGridColModel.setWidth(width);
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		jqGridColModel.setHidden(hidden);
		jqGridColModel.setKey(key);
		return jqGridColModel;
	}
}
