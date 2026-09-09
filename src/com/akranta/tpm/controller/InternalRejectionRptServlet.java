package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.ColumnChart;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.InternalRejectionReport;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.InternalRejectionRptService;
import com.akranta.tpm.service.api.InternalRejectionSerivceApi;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.ImprvmntSmryServiceImpl;
import com.akranta.tpm.service.impl.InternalRejectionRptServiceImpl;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class InternalRejectionRptServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Workbook wb = null;
	private static final String REPORT_FORMAT_EXL_2007 = "xlsx";
    DashboardService dashboardService;
	InternalRejectionRptService internalRejectionRptService;
	

	public InternalRejectionRptServlet() {
		/*
		 * try { internalRejectionRptService = new
		 * InternalRejectionRptServiceImpl(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
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
		if (!UIUtils.checkUserSession(request, response))
			return;

		String action = UIUtils.getActionPart(request);
		HttpSession h = request.getSession(false);  
		try {
			
			internalRejectionRptService = (InternalRejectionRptServiceImpl) UIUtils
					.getServiceObject(request,
							"InternalRejectionRptServiceImpl");
			dashboardService=(DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			internalRejectionRptService.InternalRejectionRptServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
			
			
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		if (action.equals("filterXmlInternalRejecRpt_input.irr")) {
			UIUtils.forwardRequest(request, response,
					"/tiles/xml/InternalRejectionRpt.xml");
		}

		else if (action.equals("InternalRejecRpt_input.irr")) {
			request.setAttribute("drilldownMsg", UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.CommonMessages", "drilldownQty"));
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/Reports/InternalRejectionRpt.jsp");
			rd.forward(request, response);
		}

		else if (action.equals("InternalRejecRpt_getCol.irr")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = new CommonFilter();
			HttpSession httpSession = request.getSession(false);

			populateCommonFilter(request, "internalrejqty", true);
			commonFilter = (CommonFilter) httpSession
					.getAttribute("internalrejqty");
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
			List<String[]> internalrejqty = internalRejectionRptService
					.getInternalRejectionRptnew(commonFilter);
			CommonMessage.debugMsg("Printing Data IR");
			for(String[] arr : internalrejqty) 
			{
				CommonMessage.debugMsg(Arrays.toString(arr));
			}
			// List<String[]> pcsGridData = transposeListArr(pcsRpt, hide);
			JSONObject jsonObject = getTableModel(internalrejqty);

			httpSession.removeAttribute("InternalRejColModel");
			// httpSession.setAttribute("InternalRejColModel", commonFilter);
			httpSession.setAttribute("InternalRejColModel", jsonObject);
			response.setContentType("text/html");

			out.println(jsonObject);

		} else if (action.equals("InternalRejecRpt_getData.irr")) {
			try {
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = new CommonFilter();

				populateCommonFilter(request, "internalrejqty", true);
				commonFilter = (CommonFilter) httpSession
						.getAttribute("internalrejqty");
				commonFilter.setRowTotal(Character.valueOf('N'));
				if (("01-Jan-1801".contains(commonFilter.getFromMonth()))
						&& ((commonFilter.getMonwise() == null) || (commonFilter
								.getMonwise().equals("Y")))) {
					commonFilter.setFromMonth(CommonFunctions
							.getFirstDateofMonth(Integer.valueOf(-1))
							.substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate()
							.substring(3, 11));

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

				PrintWriter out = response.getWriter();

				List<String[]> internalList = internalRejectionRptService
						.getInternalRejectionRptnew(commonFilter);
				CommonMessage.debugMsg(internalList);
				// List<String[]> internalDnList1 = fillValues(internalList);
				JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(
						internalList, request,1, 0,
						commonFilter.getTotalRecordCnt());

				out.println(jhDrillDnData);
				httpSession.removeAttribute("InternalRejCommonFilter");
				httpSession.setAttribute("InternalRejCommonFilter",
						commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}

		else if (action.equals("InternalRejecRpt_getExcel.irr")) {
			HttpSession httpSession = request.getSession(false);

			CommonFilter commonFilter = (CommonFilter) httpSession
					.getAttribute("InternalRejCommonFilter");

			commonFilter = (CommonFilter) httpSession
					.getAttribute("internalrejqty");
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

			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tblJSONObj = (JSONObject) httpSession
					.getAttribute("InternalRejColModel");

			String drillLevel = FilterValues.getHeader(commonFilter
					.getDrillCaption());
			if(commonFilter.getMonwise().equals("Y")){
				tblJSONObj.put("title", "InternalRejection - " + commonFilter.getFromMonth() +" - " + commonFilter.getToMonth());
			}else{
				tblJSONObj.put("title", "InternalRejection - " + commonFilter.getFromDate() +" - " + commonFilter.getToDate());
			}
			String format = ExcelUtils.getFormat(request);
			commonFilter.setFromRow(tmpFromRow);

			Workbook wb = internalRejectionRptService.getExportExcel(format,
					tblJSONObj, commonFilter);

			ExcelUtils.writeToResponse(response, wb, "InternalRejectionReport",
					format);

		}

		else if (action.equals("chart.irr")) {

			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			ColumnChart columnChart = new ColumnChart();
			CommonFilter commonFilter = (CommonFilter) httpSession
					.getAttribute("InternalRejCommonFilter");
			// CommonFilter commonFilter =
			// populateCommonFilter(request,"InternalRejCommonFilter",false);
			String forDashboard = request.getParameter("dashboard");
			String flid=CommonFunctions.getLoginFlid(request);
			String lcn=dashboardService.Functionallocn(flid);
			CommonMessage.debugMsg("forDashboard....." + forDashboard);
			if (!"true".equals(forDashboard)) {
				CommonMessage.debugMsg("to check dash board");
				commonFilter = (CommonFilter) httpSession
						.getAttribute("InternalRejCommonFilter");
			} else {
				commonFilter = new CommonFilter();
				commonFilter = FilterValues.getCommonFilters(request,
						commonFilter);
				commonFilter = FilterValues.getQuality(request, commonFilter);
			}
			String drillId = request.getParameter("parentId");
			CommonMessage.debugMsg("drillIdbbbbbbbbbbbb........." + drillId);
			BeanUtils.copyProperties(commonFilter, commonFilter);
			// getDrillLevelforGraph( request,drillId ,commonFilter);
			String rowid = request.getParameter("rowid");

			/*
			 * String name = null; if(drillId.substring(0,3).equals("FCT")) name
			 * = "Unit"; if(drillId.substring(0,3).equals("PRS")) name =
			 * "Process"; if(drillId.substring(0,3).equals("PRS")) name =
			 * "Process"; if(drillId.substring(0,3).equals("QPH")) name =
			 * "Phenomena"; if(drillId.substring(0,3).equals("QCM")) name =
			 * "Cause"; String cellVal = request.getParameter("cellVal");
			 */

			if (commonFilter.getRowTotal() == null)
				commonFilter.setRowTotal('N');

			List<String[]> graphData = internalRejectionRptService
					.getInternalRejectionRptnew(commonFilter);
			// List<String[]> graphData = fillValues(internalDnList);

			List<String> xCategories = new ArrayList<String>();
			List<Double> dataLine = new ArrayList<Double>();
			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
			ChartSeries chartSeriesLine = new ChartSeries();
			CommonMessage.debugMsg("rowid" + rowid);
			String[] month = graphData.get(0);
			String[] data = graphData.get(Integer.parseInt(rowid) );
			String DMTname = data[0];
			for (int i = 1; i < month.length; i++) {
				if (!UIUtils.isValidKeyId(data[i]))
					data[i] = "0";

				CommonMessage.debugMsg("data[i]" + data[i]);
				xCategories.add(month[i]);
				dataLine.add(Double.parseDouble(data[i]));
			}
			// chartSeriesLine.setName(data[0]);
			chartSeriesLine.setName(DMTname);
			chartSeriesLine.setData(dataLine);
			chartSeriesLine.setType(ChartTypes.LINE);

			chartSeriesList.add(chartSeriesLine);// adding data to chart to be
													// dispalyed

			List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();// declaring
																		// variable
																		// for Y
																		// axis
			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(0);
			yAxis.getTitle().setText("Count");// declaring caption for Y axis
			chartYAxis.add(yAxis);// adding Yaxis to the chart

			// String drillLevel =
			// FilterValues.getHeader(commonFilter.getDrillCaption());

			ChartXAxis xaxis = new ChartXAxis();
			if (commonFilter.getMonwise().equals("Y"))
				xaxis.getTitle().setText("Month");
			else
				xaxis.getTitle().setText("Date");
			columnChart.getxAxis().setTitle(xaxis.getTitle());
			JSONObject chartObj = columnChart.drawChart(xCategories,
					chartSeriesList, lcn+"-Internal Rejection Report for the "
							+ DMTname + " DMT ", null, chartYAxis);
			UIUtils.dashBoardSetChartObject(request, chartObj);
			out.println(chartObj);
			out.close();
		}
	}

	public Workbook fillInternalValues(
			Map<Integer, List<String[]>> internalRejData, String path,
			String format, JSONObject tableModel) throws IOException {

		boolean transpose = false;
		this.wb = format.equals(REPORT_FORMAT_EXL_2007) ? new SXSSFWorkbook(
				(transpose ? -1 : 100)) : new HSSFWorkbook(); // keep 100 rows
																// in memory,
																// exceeding
																// rows will be
																// flushed to
																// disk
		// Sheet sheet = wb.createSheet();

		Sheet sheet = wb.createSheet();
		sheet.setDisplayGridlines(false);

		List<String[]> curExcel = internalRejData.get(0);
		curExcel = transposeListArr(curExcel);

		if (curExcel != null && curExcel.size() > 0) {
			CommonMessage.debugMsg("cur size................"
					+ curExcel.size());
			String[] internalExl = curExcel.get(1);
			for (int i = 0; i < internalExl.length; i++) {
				CommonMessage.debugMsg("cur1 Data................"
						+ internalExl[i]);
				sheet.getRow(1).getCell(1).setCellValue("");
			}
		}
		return null;

	}
	
	private List<String[]> fillValues(Map<Integer, List<String[]>> internalDnList) {

		List<String[]> modifyData = new ArrayList<String[]>();

		List<String[]> cur1 = internalDnList.get(0);
		List<String[]> cur2 = internalDnList.get(1);

		List<String[]> cur1Grid = transposeListArr(cur1);
		if (cur1Grid != null && cur1Grid.size() > 0) {
			for (int j = 0; j < cur1Grid.size(); j++) {
				String[] impshtexl = cur1Grid.get(j);
				modifyData.add(impshtexl);
			}
		}

		if (cur2 != null && cur2.size() > 0) {
			for (int j = 2; j < cur2.size(); j++) {
				String[] impshtexl = cur2.get(j);
				modifyData.add(impshtexl);
			}
		}

		return modifyData;
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

	private JSONObject getTableModel(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		//CommonMessage.debugMsg("header..." + colHeader[2]);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridTableModel.setTableButton(true);

			jqGridColModel.setWidth(75);
			jqGridColModel.setAlign("center");
			jqGridColModel.setEditable(false);

			if (i == 0) {
				jqGridColModel.setWidth(300);
				jqGridColModel.setAlign("left");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);

		tableModel.set("tableHeight", "87%%");
		tableModel.set("tablewidth", "120%%");
		return tableModel;
	}

	private List<String[]> transposeListArr(List<String[]> dataList) {
		if (dataList.size() <= 0)
			return null;
		List<String[]> transposeList = new ArrayList<String[]>();
		for (int i = 0; i < dataList.get(0).length; i++) {
			String[] tRow = new String[dataList.size()];
			for (int j = 0; j < dataList.size(); j++) {
				tRow[j] = dataList.get(j)[i].equals("0") ? dataList.get(j)[i]
						.replace("0", "-") : dataList.get(j)[i];
			}
			transposeList.add(tRow);
		}
		return transposeList;
	}

	private void getDrillLevelForFilter(HttpServletRequest request,
			CommonFilter commonFilter) {
		CommonMessage.debugMsg("get data form drill level......."
				+ FilterCondSql.getComboSelectionId(commonFilter.getProcess()));
		String circel = FilterCondSql.getComboSelectionId(commonFilter
				.getCircle());
		String machine = FilterCondSql.getComboSelectionId(commonFilter
				.getMachine());
		String process = FilterCondSql.getComboSelectionId(commonFilter
				.getProcess());
		String Phenomena = FilterCondSql.getComboSelectionId(commonFilter
				.getDefactPhenamena());
		String locn = FilterCondSql.getComboSelectionId(commonFilter
				.getLocation());
		CommonMessage.debugMsg("get data form drill level......." + process);
		if (UIUtils.isValidKeyId(circel))
			commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
		if (UIUtils.isValidKeyId(machine))
			commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
		if (UIUtils.isValidKeyId(locn))
			commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
		if (UIUtils.isValidKeyId(process))
			commonFilter.setDrillLevel(DrillLevelConstants.QUALITYPHENOMENA);
		if (UIUtils.isValidKeyId(Phenomena))
			commonFilter.setDrillLevel(DrillLevelConstants.QUALITYCAUSE);
	}

	private void getDrillLevel(HttpServletRequest request, String keyIdVal,
			CommonFilter commonFilter, String doubleClick) {

		String parentId = request.getParameter("parentId");
		CommonMessage.debugMsg("parentId..." + parentId
				+ "  doubleClick..........." + doubleClick);
		if (doubleClick == null)
			getDrillLevelForFilter(request, commonFilter);

		else if (doubleClick.equals("Y")) {
			if (commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag
					&& parentId.substring(0, 3).equals("FCT"))
				commonFilter.setDrillLevel(DrillLevelConstants.PROCESS);
			else if (commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag
					&& parentId.substring(0, 3).equals("PRS"))
				commonFilter
						.setDrillLevel(DrillLevelConstants.QUALITYPHENOMENA);
			else if (commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag
					&& parentId.substring(0, 3).equals("QPH"))
				commonFilter.setDrillLevel(DrillLevelConstants.QUALITYCAUSE);

			if (commonFilter.getDrillFlag() == DrillLevelConstants.backwardFlag
					&& parentId.substring(0, 3).equals("QCM"))
				commonFilter
						.setDrillLevel(DrillLevelConstants.QUALITYPHENOMENA);
			else if (commonFilter.getDrillFlag() == DrillLevelConstants.backwardFlag
					&& parentId.substring(0, 3).equals("QPH"))
				commonFilter.setDrillLevel(DrillLevelConstants.PROCESS);
			else if (commonFilter.getDrillFlag() == DrillLevelConstants.backwardFlag
					&& parentId.substring(0, 3).equals("PRS"))
				commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
		}

		CommonMessage.debugMsg("drill Level..."
				+ commonFilter.getDrillLevel());
	}

	private void getDrillLevelforGraph(HttpServletRequest request,
			String keyIdVal, CommonFilter commonFilter) {
		if (keyIdVal.substring(0, 3).equals("FCT"))
			commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
		if (keyIdVal.substring(0, 3).equals("QCM"))
			commonFilter.setDrillLevel(DrillLevelConstants.QUALITYCAUSE);
		CommonMessage.debugMsg("Drill Level......."
				+ commonFilter.getDrillLevel());
	}

}
