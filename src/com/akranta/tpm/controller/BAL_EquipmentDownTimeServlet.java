package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;
import net.sf.json.JSONObject;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartPie;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.BAL_EquipmentDownTimeService;
import com.akranta.tpm.service.impl.BAL_EquipmentDownTimeServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_EquipmentDownTimeServlet extends HttpServlet {
	/**
	 * Created By:N.Arun Modified By:Manikandan.R
	 */
	private static final long serialVersionUID = 1L;
	BAL_EquipmentDownTimeService equipmentDownTimeService;
	BAL_EquipmentDownTimeServiceImpl equipmentDownTimeServiceImpl;

	public BAL_EquipmentDownTimeServlet() {
		/**
		 * try { equipmentDownTimeService = new EquipmentDownTimeServiceImpl();
		 * } catch (Exception e) { // TODO Auto-generated catch block
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

		HttpSession httpSession = request.getSession(false);
		try {
			equipmentDownTimeService = (BAL_EquipmentDownTimeServiceImpl) UIUtils
					.getServiceObject(request, "BAL_EquipmentDownTimeServiceImpl");
			
			HttpSession h = request.getSession(false);

			equipmentDownTimeService.BAL_EquipmentDownTimeServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
			
		} catch (ServiceObjectCreationException e) {

		}

		if (action.equals("filterXmlEquipmentDownTime_input.BeDTRpt")) {

			response.setContentType("xml");
			UIUtils.forwardRequest(request, response,
					"/tiles/xml/EquipmentDownTime.xml");
		} else if (action.equals("EquipmentDownTime_input.BeDTRpt")) {
			String filterString = request.getParameter("filterString");
			request.setAttribute("drilldownMsgs", UIUtils
					.getPropertyValue(
							"com.akranta.tpm.resources.CommonMessages",
							"drilldownMsgs"));
			request.setAttribute("eqpgraph", UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.CommonMessages", "eqpgraph"));
			request.setAttribute("filterStr", filterString);
			UIUtils.forwardRequest(request, response,
					"/pages/Reports/BAL_EquipmentDownTime.jsp");

		} else if (action.equals("EquipmentDownTime_getCol.BeDTRpt")) {
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			List<CommonFilter> breakUpList = new ArrayList<CommonFilter>();
			CommonFilter commonFilter = null;
			if (!UIUtils.isValidKeyId(firstClick)
					|| (firstClick != null && !firstClick.equals("Y"))) {
				commonFilter = (CommonFilter) httpSession
						.getAttribute("equipmentDownTimeFilter");
			}

			if (commonFilter == null)
				commonFilter = new CommonFilter();

			/*
			 * if (Constants.passNullDate.contains(commonFilter.getFromMonth())
			 * && (commonFilter.getMonwise() == null ||
			 * commonFilter.getMonwise().equals("Y"))) {
			 * commonFilter.setFromMonth
			 * (CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
			 * commonFilter
			 * .setToMonth(CommonFunctions.getDate().substring(3,11));
			 * commonFilter.setMonwise("Y"); commonFilter.setRemoveBlank("Y"); }
			 * else if(
			 * Constants.passNullDate.contains(commonFilter.getFromDate())&&
			 * (commonFilter.getMonwise() == null ||
			 * commonFilter.getMonwise().equals(" ")) ) {
			 * commonFilter.setFromDate
			 * (CommonFunctions.getFirstDateofMonth(-3));
			 * commonFilter.setToDate(CommonFunctions.getDate()); }
			 */

			httpSession.removeAttribute("equipmentDownTimeFilter");
			httpSession.setAttribute("BreakUpList", breakUpList);

			FilterValues.getCommonFilters(request, commonFilter);

			FilterValues.getBDRelated(request, commonFilter);

			httpSession.setAttribute("equipmentDownTimeFilter", commonFilter);
			commonFilter.setISFORGRAPH('N');
			commonFilter.setIsGetCol("Y");
			List<String[]> equipmentDownTimeList = equipmentDownTimeService
					.getAllEquipmentDownTimet(commonFilter);
			CommonFunctions.debugMsg("equipmentDownTimeList:"
					+ equipmentDownTimeList);
			JSONObject equipmentDownTimeData = UIUtils
					.convertToJqGridTableObject(equipmentDownTimeList, request,
							4, 0, commonFilter.getTotalRecordCnt()+ 4);
			CommonFunctions.debugMsg("equipmentDownTimeData:"
					+ equipmentDownTimeData);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			gridColModel.setHeaderNum(2);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);

			/*
			 * String [] colHeaderHead = equipmentDownTimeList.get(0); String [] colHeader =
			 * equipmentDownTimeList.get(1); String [] colHeader1 =
			 * equipmentDownTimeList.get(2); String [] colHeader2 =
			 * equipmentDownTimeList.get(3);
			 */
			
			String[] colHeaderHead = equipmentDownTimeList.get(0);
			String[] colHeader = equipmentDownTimeList.get(2);
			String[] colHeader1 = equipmentDownTimeList.get(1);
			colHeader1[2] = "";

			for (int i = 4; i < colHeader.length; i++) {
				colHeader[i] = colHeader[i].substring(colHeader[i]
						.lastIndexOf("-") + 1);
				CommonFunctions.debugMsg(colHeader[i]);
				colHeader[i] = colHeader[i].replace("I", "Occurences").replace(
						"T", "Time");
				CommonFunctions.debugMsg(colHeader[i]);
			}
			colHeader[3] = FilterValues.getHeader(commonFilter
					.getDrillCaption());

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader1);
			headers.add(colHeader);

			JSONObject jsonObject = UIUtils.getTableModel(headers,
					colHeaderHead, jqGridTableModel, gridColModel);
			CommonFunctions.debugMsg("jsonObject:" + jsonObject);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "72%%");
			jsonObject.put("data", equipmentDownTimeData);

			CommonFunctions.debugMsg(jsonObject);
			httpSession.removeAttribute("eqpReportColModel");
			httpSession.setAttribute("eqpReportColModel", jsonObject);
			out.println(jsonObject);
		} else if (action.equals("EquipmentDownTime_getData.BeDTRpt")) {
			try {
				CommonFilter commonFilter = null;
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				// BreakUpList = FilterValues.getBreakUpRelated(request,
				// BreakUpList);
				commonFilter = (CommonFilter) httpSession
						.getAttribute("equipmentDownTimeFilter");
				FilterValues.getCommonFilters(request, commonFilter);
				commonFilter.setISFORGRAPH('N');
				commonFilter.setIsGetCol("N");
				List<String[]> equipmentDownTimeList = equipmentDownTimeService
						.getAllEquipmentDownTimet(commonFilter);
				
				/*
				 * for (int r = 0; r < equipmentDownTimeList.size(); r++)
				 * CommonFunctions.debugMsg("row " + r + ": " +
				 * java.util.Arrays.toString(equipmentDownTimeList.get(r)));
				 */
				
				CommonFunctions.debugMsg("dsata size........"
						+ equipmentDownTimeList.size());
				JSONObject listToJsonObject = UIUtils
						.convertToJqGridTableObject(equipmentDownTimeList,
								request, 3, 0,
								commonFilter.getTotalRecordCnt()+5);

				if (equipmentDownTimeList.size() <= 0)
					FilterValues.setBackwardDrillLevel(commonFilter, "");

				out.println(listToJsonObject);
				commonFilter.setViewClick('N');

				httpSession.removeAttribute("equipmentDownTimeFilter");
				httpSession.setAttribute("equipmentDownTimeFilter",
						commonFilter);

			} catch (Exception e) {
				System.out.println("error " + e.getMessage());
			}
		}

		/*
		 * else if( action.equals("chart.eDTRpt")){
		 * processChart(request,response); }
		 */

		else if (action.equals("EquipmentDownTime_getExcel.BeDTRpt")) {
			CommonFilter commonFilter = new CommonFilter();
			commonFilter = populateCommonFilter(request,
					"equipmentDownTimeFilter", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);

			JSONObject tblJSONObj = (JSONObject) httpSession
					.getAttribute("eqpReportColModel");
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();

			String month1 = fromMonth + "  -  " + toMonth;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date = fromdate + "  -  " + todate;
			tblJSONObj.put("title", "Equipment DownTime Report" + " - " + date);

			if (commonFilter.getMonwise().equals("Y")) {
				tblJSONObj.put("title", "Equipment DownTime Report" + " - "
						+ month1);
			}

			String format = ExcelUtils.getFormat(request);
			Workbook wb = equipmentDownTimeService.downtimeExportExcel(
					commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, "EquipmentDownTimeReport",
					format);

		}

		else if (action.equals("chart.BeDTRpt")) {
			PrintWriter out = response.getWriter();
			CommonFilter chrtcommonFilter = (CommonFilter) httpSession
					.getAttribute("equipmentDownTimeFilter");

			BeanUtils.copyProperties(chrtcommonFilter, chrtcommonFilter);
			FilterValues.getCommonFilters(request, chrtcommonFilter);

			if (chrtcommonFilter.getRowTotal() == null)
				chrtcommonFilter.setRowTotal('Y');
			chrtcommonFilter.setISFORGRAPH('Y');
			List<String[]> graphData = equipmentDownTimeService
					.getAllEquipmentDownTimet(chrtcommonFilter);
			ChartOptionBean lineChart = new ChartOptionBean();
			List<String> xAxisCategory = new ArrayList<String>();
			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
			List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();
			
			/*
			 * for(String[] array:graphData) {
			 * CommonFunctions.debugMsg("row "+Arrays.toString(array)); }
			 */

			String title = "Equipment Downtime Graph";

			String[] header = graphData.get(0);
			String[] month = graphData.get(1);
			String[] data = graphData.get(3);
			String prevMonth = null;

			String subTitle = data[2];

			ChartSeries timeSeries = new ChartSeries();
			List<Double> timeData = new ArrayList<Double>();

			ChartSeries intstanceSeries = new ChartSeries();
			List<Double> instanceData = new ArrayList<Double>();
			
			CommonFunctions.debugMsg("header "+header);
			
			CommonFunctions.debugMsg("month "+month);
			
			CommonFunctions.debugMsg("data "+data);

			for (int i = 3; i < header.length; i++) {
				if (month[i].contains("YTD"))
					continue;
				if (month[i].contains("AVG"))
					continue;
				else if (header[i].contains("_T")) {

					timeData.add(Double.parseDouble(data[i]));
				} else if (header[i].contains("_I")) {

					instanceData.add(Double.parseDouble(data[i]));
				}
				if (prevMonth == null || !month[i].equals(prevMonth)) {

					xAxisCategory.add(month[i]);
				}
				prevMonth = month[i];
			}
			if (timeData.size() > 0) {
				timeSeries.setData(timeData);
				timeSeries.setType(ChartTypes.SPLINE);
				timeSeries.setName("Time");
				chartSeriesList.add(timeSeries);

				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				yAxis.getTitle().setText("Time");
				chartYAxis.add(yAxis);
			}
			if (instanceData.size() > 0) {
				intstanceSeries.setData(instanceData);
				intstanceSeries.setType(ChartTypes.SPLINE);
				intstanceSeries.setName("Occurrences");
				chartSeriesList.add(intstanceSeries);

				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				yAxis.getTitle().setText("Occurence");
				if (chartYAxis.size() > 0) {
					yAxis.setOpposite(true);
					intstanceSeries.setyAxis(1);
				}

				chartYAxis.add(yAxis);
			}

			ChartXAxis xaxis = new ChartXAxis();
			if (chrtcommonFilter.getMonwise().equals("Y"))
				xaxis.getTitle().setText("Month");
			else
				xaxis.getTitle().setText("Date");
			lineChart.getxAxis().setTitle(xaxis.getTitle());

			JSONObject chartObj = lineChart.drawChart(xAxisCategory,
					chartSeriesList, title, subTitle, chartYAxis);
			out.println(chartObj);
			out.close();

		}
	}

	/*
	 * private void processChart(HttpServletRequest request, HttpServletResponse
	 * response) throws Exception{ HttpSession httpSession =
	 * request.getSession(false); String forDashboard =
	 * request.getParameter("dashboard");
	 * 
	 * CommonFilter commonFilter = null; CommonFilter chrtCommonFilter = new
	 * CommonFilter(); String rowId = request.getParameter("rowid"); if( !
	 * "true".equals(forDashboard)){
	 * 
	 * commonFilter = (CommonFilter)
	 * httpSession.getAttribute("equipmentDownTimeFilter");
	 * BeanUtils.copyProperties(chrtCommonFilter, commonFilter); } else{
	 * 
	 * FilterValues.getBDRelated(request, chrtCommonFilter); }
	 * 
	 * FilterValues.getCommonFilters(request, chrtCommonFilter) ;
	 * 
	 * if(chrtCommonFilter.getRowTotal() == null )
	 * chrtCommonFilter.setRowTotal('N');
	 * 
	 * JSONObject chartObj = null; String chartType =
	 * request.getParameter("chType");
	 * 
	 * 
	 * 
	 * if("L".equals(chartType)){ chrtCommonFilter.setISFORGRAPH('Y');
	 * chrtCommonFilter.setGridSortColumn("1"); List<String[]>
	 * equipmentDownTimeList =
	 * equipmentDownTimeService.getAllEquipmentDownTimet(chrtCommonFilter);
	 * chartObj =
	 * processLineChart(equipmentDownTimeList,chrtCommonFilter,rowId); } else{
	 * 
	 * List<String[]> equipmentDownTimeList =
	 * equipmentDownTimeService.getSumOfEquipmentDownTimeForDrill
	 * (chrtCommonFilter); chartObj =
	 * processPieChart(equipmentDownTimeList,chrtCommonFilter); }
	 * 
	 * chrtCommonFilter = null; UIUtils.setDashBoardIdentifier(request,
	 * chartObj); PrintWriter out = response.getWriter(); out.print(chartObj);
	 * out.close(); }
	 * 
	 * private JSONObject processPieChart(List<String[]>
	 * equipmentDownTimeList,CommonFilter commonFilter) throws
	 * IllegalAccessException, InvocationTargetException{ if(
	 * equipmentDownTimeList == null || equipmentDownTimeList.size() <= 1 )
	 * return null;
	 * 
	 * ChartPie pieChart = new ChartPie();
	 * 
	 * String fromMonth = commonFilter.getFromMonth(); String toMonth =
	 * commonFilter.getToMonth();
	 * 
	 * String month1 = fromMonth +"  -  "+ toMonth ; String fromdate =
	 * commonFilter.getFromDate(); String todate = commonFilter.getToDate();
	 * String date = fromdate +"  -  "+ todate ; String title =
	 * "Equipment Down Time Analysis" + " - "+date;
	 * 
	 * if( commonFilter.getMonwise().equals("Y")){ title =
	 * "Equipment Down Time Analysis" + " - "+month1; } ChartSeries chartSeries
	 * = new ChartSeries(); chartSeries.setType("pie");
	 * 
	 * String [] header = equipmentDownTimeList.get(0);
	 * chartSeries.setName(header[2]); List<Object[]> dataList = new
	 * ArrayList<Object[]>();
	 * 
	 * int rCnt = 0; for(String [] row : equipmentDownTimeList) { if( rCnt++ <=
	 * 0 ) continue; Object [] data = new Object[2]; data[ 0 ] = row[1]; data[ 1
	 * ] = Double.parseDouble(row[2]); dataList.add(data); }
	 * 
	 * chartSeries.setData(dataList); chartSeries.setSize(200);
	 * List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
	 * chartSeriesList.add(chartSeries);
	 * 
	 * return pieChart.drawChart(chartSeriesList, title, ""); }
	 * 
	 * private JSONObject processLineChart(List<String[]>
	 * equipmentDownTimeList,CommonFilter commonFilter,String rowId){ if(
	 * equipmentDownTimeList == null || equipmentDownTimeList.size() <= 2 )
	 * return null;
	 * 
	 * ChartOptionBean lineChart = new ChartOptionBean(); List<String>
	 * xAxisCategory = new ArrayList<String>(); List<ChartSeries>
	 * chartSeriesList = new ArrayList<ChartSeries>(); List<ChartYAxis>
	 * chartYAxis = new ArrayList<ChartYAxis>();
	 * 
	 * String fromMonth = commonFilter.getFromMonth(); String toMonth =
	 * commonFilter.getToMonth();
	 * 
	 * String month1 = fromMonth +"  -  "+ toMonth ; String fromdate =
	 * commonFilter.getFromDate(); String todate = commonFilter.getToDate();
	 * String date = fromdate +"  -  "+ todate ; String title =
	 * "Equipment Down Time Analysis" + " - "+date;
	 * 
	 * if( commonFilter.getMonwise().equals("Y")){ title =
	 * "Equipment Down Time Analysis" + " - "+month1; }
	 * 
	 * String [] header = equipmentDownTimeList.get(0); String [] month =
	 * equipmentDownTimeList.get(1); String [] data =
	 * equipmentDownTimeList.get(Integer.parseInt(rowId)); String prevMonth =
	 * null;
	 * 
	 * String subTitle = data[2];
	 * 
	 * ChartSeries timeSeries = new ChartSeries(); List<Double> timeData = new
	 * ArrayList<Double>();
	 * 
	 * ChartSeries intstanceSeries = new ChartSeries(); List<Double>
	 * instanceData = new ArrayList<Double>();
	 * 
	 * 
	 * for( int i = 3;i < header.length-2;i++ ){
	 * 
	 * if( month[i].contains("YTD")) continue; if(header[i].contains("I") ){
	 * instanceData.add(Double.parseDouble(data[i])); } else
	 * if(header[i].contains("T") ){ timeData.add(Double.parseDouble(data[i]));
	 * } if( prevMonth == null || ! month[i].equals(prevMonth) ){
	 * xAxisCategory.add(month[i]); } prevMonth = month[i]; } if(timeData.size()
	 * > 0 ){ timeSeries.setData(timeData);
	 * timeSeries.setType(ChartTypes.SPLINE); timeSeries.setName("Time");
	 * chartSeriesList.add(timeSeries);
	 * 
	 * ChartYAxis yAxis = new ChartYAxis(); yAxis.setMin(0);
	 * yAxis.getTitle().setText("Equipment Failure Minutes");
	 * chartYAxis.add(yAxis); } if( instanceData.size() > 0){
	 * intstanceSeries.setData(instanceData);
	 * intstanceSeries.setType(ChartTypes.SPLINE);
	 * intstanceSeries.setName("Occurrences");
	 * chartSeriesList.add(intstanceSeries);
	 * 
	 * ChartYAxis yAxis = new ChartYAxis(); yAxis.setMin(0);
	 * yAxis.getTitle().setText("Equipment Failure Occurrences");
	 * 
	 * if( chartYAxis.size() > 0) { yAxis.setOpposite(true);
	 * intstanceSeries.setyAxis(1); }
	 * 
	 * chartYAxis.add(yAxis); } ChartXAxis xaxis = new ChartXAxis();
	 * 
	 * if(commonFilter.getMonwise().equals("Y"))
	 * xaxis.getTitle().setText("Month"); else xaxis.getTitle().setText("Date");
	 * 
	 * lineChart.getxAxis().setTitle(xaxis.getTitle()); return
	 * lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle,
	 * chartYAxis);
	 * 
	 * }
	 */
	private JSONObject getTableModel(List<String[]> headers, String caption) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();

		String[] colHeader = headers.get(0);
		String[] colHeader1 = headers.get(1);
		String[] emptyrow = new String[colHeader.length];

		emptyrow[0] = "";
		emptyrow[2] = "";
		colHeader1[2] = "";

		for (int i = 3; i < colHeader.length; i++) {
			colHeader[i] = colHeader[i]
					.substring(colHeader[i].lastIndexOf("-") + 1);
			colHeader[i] = colHeader[i].replace("I", "Occurences").replace("T",
					"Time");
			emptyrow[i] = "";
		}

		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);

		colHeader[2] = caption;

		jqGridTableModel.getColModel().add(
				getColModel("rnum", 0, "left", true, false, true));
		jqGridTableModel.getColModel().add(
				getColModel("keyid", 0, "left", true, false, false));
		JqGridColModel jqGridColModel = getColModel("codeField", 350, "left",
				false, true, false);

		jqGridTableModel.getColModel().add(jqGridColModel);
		String colIndex = "";
		colHeader = headers.get(0);

		for (int i = 3; i < colHeader1.length; i++) {
			colIndex = colHeader1[i].replaceAll(" ", "") + i;
			jqGridTableModel.getColModel().add(
					getColModel(colIndex, 100, "center", false, true, false));
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
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

		if (groupbyfield) {
			jqGridColModel.setSummaryType("sum");
			jqGridColModel.setSummaryTpl("<b><font >  {0} </font> </b>");
		}
		return jqGridColModel;
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request,
			String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession
				.getAttribute(beanIdentifier);
		if (commonFilter != null && !createNew) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();

			commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		return commonFilter;
	}
}
