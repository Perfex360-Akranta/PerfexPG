package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.ColumnChart;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.service.AbnormalityReportService;
import com.akranta.tpm.service.impl.AbnormalityReportServiceImpl;
import com.akranta.tpm.service.impl.AbnormalityServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class AbnormalityGenReportServlet extends HttpServlet {
	
	/**
	 * Created by KarthicK.T
	 */
	private static final long serialVersionUID = 1L;
	
	AbnormalityReportService abnormalityReportService ;
	
	public AbnormalityGenReportServlet()
	{	
		super();
	/*	try {
			abnormalityReportService = new AbnormalityReportServiceImpl();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	*/	
		
	}
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
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		HttpSession httpSession = request.getSession(false);
		String action =UIUtils.getActionPart(request); 
		
		try {
			abnormalityReportService = (AbnormalityReportServiceImpl)UIUtils.getServiceObject(request,"AbnormalityReportServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}if(action.equals("AbnUnsafeActCondition_input.abnGenRpt")){
			String backParentId = request.getParameter("backParentId");
			String parentId = request.getParameter("parentId");
			if(!UIUtils.isValidKeyId(parentId))
				parentId=backParentId;
			httpSession.removeAttribute("parentId");
			httpSession.setAttribute("parentId",parentId);
			CommonMessage.debugMsg("AbnUnsafeActCondition_input.abnGenRpt  "+parentId);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnUnsafeActionUnsafePlanDrill.jsp");
			rd.forward(request, response);
		}else if(action.equals("AbnUnsafeChart_input.abnGenRpt")){
			RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnUnsafeChart.jsp");
			request.setAttribute("ChartType", request.getParameter("ChartType"));
			rd.forward(request, response);
		}else if(action.equals("AbnUnsafeActCondition_getCol.abnGenRpt")){
			 CommonFilter  commonFilter  ;
			 commonFilter = new CommonFilter();  
			 httpSession.removeAttribute("AbnUnsafeDrillFilter");
			 httpSession.setAttribute("AbnUnsafeDrillFilter",commonFilter);
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 List< String[]> unsafeDrillDnList  = abnormalityReportService.getUnsafeDrillDn(commonFilter,"");
		   	    JSONObject jsonObject = null;
		 	jsonObject = getTableModel_Unsafe(unsafeDrillDnList);
	   	    jsonObject.set("tableWidth", "100%%");
	     	jsonObject.set("tableHeight", "45%%");
	   	    httpSession.removeAttribute("AplColModel");
			httpSession.setAttribute("AplColModel",jsonObject);	
			CommonMessage.debugMsg("jsonObject " + jsonObject);
			out.println(jsonObject);
		}else if(action.equals("AbnUnsafeActCondition_getData.abnGenRpt")){

			CommonMessage.debugMsg("AbnActionplanDrill_getData.abnAplDrill");
			CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("AbnUnsafeDrillFilter");
			response.setContentType("text/html");	
			String parentId = request.getParameter("parentId");
			PrintWriter out = response.getWriter();
			JSONObject unsafeDrillDnData = null;
			List< String[]> unsafeDrillDnList  = abnormalityReportService.getUnsafeDrillDn(commonFilter,parentId);
			unsafeDrillDnData = UIUtils.convertToJqGridTableObject(unsafeDrillDnList,request,0,0,commonFilter.getTotalRecordCnt());
			CommonMessage.debugMsg("parentId  "+parentId +"  Caption() " +commonFilter.getDrillCaption());
			CommonMessage.debugMsg("unsafeDrillDnData " + unsafeDrillDnData);
			out.println(unsafeDrillDnData);
			
		}else if(action.equals("AbnUnsafeActConditionMonth_getCol.abnGenRpt")){
			 CommonFilter  commonFilter  ;
			 commonFilter = new CommonFilter();  
			 httpSession.removeAttribute("AbnUnsafeFilter");
			 httpSession.setAttribute("AbnUnsafeFilter",commonFilter);
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 List< String[]> unsafeMonthList  = abnormalityReportService.getUnsafeMonth(commonFilter);
		   	    JSONObject jsonObject = null;
		 	jsonObject = getTableModel_UnsafeMonth(unsafeMonthList);
	   	    jsonObject.set("tableWidth", "100%%");
	     	jsonObject.set("tableHeight", "45%%");
	   	    httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel",jsonObject);	
			CommonMessage.debugMsg("jsonObject " + jsonObject);
			out.println(jsonObject);
		}else if(action.equals("AbnUnsafeActConditionMonth_getData.abnGenRpt")){

			CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("AbnUnsafeFilter");
			PrintWriter out = response.getWriter();
			JSONObject unsafeDrillDnData = null;
			List< String[]> unsafeDrillDnList  = abnormalityReportService.getUnsafeMonth(commonFilter);
			unsafeDrillDnData = UIUtils.convertToJqGridTableObject(unsafeDrillDnList,request,0,0,commonFilter.getTotalRecordCnt());
			CommonMessage.debugMsg("unsafeDrillDnData " + unsafeDrillDnData);
			out.println(unsafeDrillDnData);
			
		}
		else if(action.equals("AbnUnsafeActionUnsafePlanDrill_chart.abnGenRpt")){
			String ChartType=request.getParameter("ChartType");
			CommonMessage.debugMsg("ChartType    "+request.getParameter("ChartType"));
			if(ChartType.equals("J"))
				processBarJH( request, response, request.getParameter("Type"));
			else if(ChartType.equals("M"))
				processLineMonth( request, response, request.getParameter("Type"));
		}
		else if(action.equals("filterXmlAbnRptGen_input.abnGenRpt")||(action.equals("filterXmlHSE_AbnRptGen_input.abnGenRpt"))
				|| (action.equals("filterXmlAbnHTAGeneral_input.abnGenRpt"))||(action.equals("filterXmlAbnSOCGeneral_input.abnGenRpt"))
				|| (action.equals("filterXmlAbnUNSGeneral_input.abnGenRpt"))){
			 response.setContentType("xml"); 
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/AbnormalirtRptGeneral.xml");
		 }
		
		
		else if (action.equals("AbnRptGen_input.abnGenRpt") || action.equals("HSE_AbnRptGen_input.abnGenRpt")
				|| action.equals("AbnHTAGeneral_input.abnGenRpt") || action.equals("AbnSOCGeneral_input.abnGenRpt")
				|| action.equals("AbnUNSGeneral_input.abnGenRpt")) 
		{ 
			CommonMessage.debugMsg("get dddaaddata");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnormalityReport.jsp");
			
			
			if (action.equals("HSE_AbnRptGen_input.abnGenRpt")) 
				request.setAttribute("AbnType", "SHE");
			else if(action.equals("AbnHTAGeneral_input.abnGenRpt"))
				request.setAttribute("AbnType", "HTA");
			else if(action.equals("AbnSOCGeneral_input.abnGenRpt"))
				request.setAttribute("AbnType", "SOC");
			else if(action.equals("AbnUNSGeneral_input.abnGenRpt"))
				request.setAttribute("AbnType", "UNS");
			
			rd.forward(request, response);
			
		}
		else if(  action.equals("HSE_AbnRptGen_getCol.abnGenRpt") || (action.equals("AbnUNSGeneral_getCol.abnGenRpt")))
		{
			PrintWriter out = response.getWriter();
			//out.println(getColumnModel());
			CommonFilter commonFilter = populateCommonFilter(request,"AbnRptGenCommonFilter",true);
			
			String abnType=commonFilter.getAbnormalityType();
			String viewModel = "AbnRpt";
			if("HTA".equals(abnType))
				viewModel = "AbnHTA";
			else if("SOC".equals(abnType))
				viewModel = "AbnSOC";
			else if("UNS".equals(abnType))
				viewModel = "AbnUNS";
			else if("SHE".equals(abnType))
				viewModel = "AbnUNS";
			CommonMessage.debugMsg(viewModel);

			String abnReportColModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityGeneralReport", viewModel);
			CommonMessage.debugMsg("viewModel.."+viewModel);
			JSONObject tblJSONObj = JSONObject.fromString(abnReportColModel);
			tblJSONObj.put("tableHeight", "70%%");
			tblJSONObj.put("tableWidth", "106%%");
			//List<String []> abnormalityReportList  = abnormalityReportService.getAllAbnormalityGeneral(commonFilter);
			httpSession.removeAttribute("ColModel");
			httpSession.setAttribute("ColModel", tblJSONObj);
			//JSONObject abnormalityData = UIUtils.convertToJqGridTableObject(abnormalityReportList,request,0,1,commonFilter.getTotalRecordCnt());
			//httpSession.removeAttribute("abnReportGenServletdata");
			//httpSession.setAttribute("abnReportGenServletdata", abnormalityData);						
			out.println(abnReportColModel);
		}
		else if(action.equals("AbnRptGen_getCol.abnGenRpt") || (action.equals("AbnSOCGeneral_getCol.abnGenRpt")) 
				|| (action.equals("AbnHTAGeneral_getCol.abnGenRpt")) )
		{
			PrintWriter out = response.getWriter();
			
			CommonFilter commonFilter = populateCommonFilter(request,"AbnRptGenCommonFilter",true);
			List<String []> abnormalityReportList  = abnormalityReportService.getAllAbnormalityGeneral(commonFilter);
			/*JSONObject abnData;
			abnData= UIUtils.convertToJqGridTableObject(abnormalityReportList,request,3,0,commonFilter.getTotalRecordCnt());
			httpSession.setAttribute("abnReportServletdata", abnData);*/
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = abnormalityReportList.get(2);			
			String [] colHeaderCond = abnormalityReportList.get(1);
			
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			//colModel.put("data", abnData);
			//JSONObject colModel = getTableModel(abnormalityReportList,commonFilter);
			CommonMessage.debugMsg("colModel   "+colModel);
			colModel.set("tableHeight", "90%%");
			colModel.set("tableWidth", "106%%");
			httpSession.setAttribute("ColModel", colModel);
			
			out.println(colModel);
			 
		}
		else if( (action.equals("AbnRptGen_getData.abnGenRpt")) || (action.equals("HSE_AbnRptGen_getData.abnGenRpt"))
				|| (action.equals("AbnHTAGeneral_getData.abnGenRpt")) || (action.equals("AbnSOCGeneral_getData.abnGenRpt"))
				|| (action.equals("AbnUNSGeneral_getData.abnGenRpt")))
		{
			try
			{	
				CommonMessage.debugMsg("Inside  Data action");
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"AbnRptGenCommonFilter",false);
				JSONObject jsonObject = null;
        		List<String []> abnormalityReportList  = abnormalityReportService.getAllAbnormalityGeneral(commonFilter);
        		jsonObject = UIUtils.convertToJqGridTableObject(abnormalityReportList,request,3,0,commonFilter.getTotalRecordCnt()+3);
  			 	out.println(jsonObject);
	  			httpSession.removeAttribute("AbnRptGenCommonFilter");
	  			httpSession.setAttribute("AbnRptGenCommonFilter", commonFilter);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			
		else if( action.equals("AbnRptGen_getExcel.abnGenRpt")|| action.equals("HSE_AbnRptGen_getExcel.abnGenRpt")
				|| (action.equals("AbnHTAGeneral_getExcel.abnGenRpt")) || (action.equals("AbnSOCGeneral_getExcel.abnGenRpt"))
				|| (action.equals("AbnUNSGeneral_getExcel.abnGenRpt"))){
			
			CommonFilter commonFilter = populateCommonFilter(request,"AbnRptGenCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("ColModel");
			tblJSONObj.put("title", "AbnormalityGeneral Report");
			String title = "AbnormalityGeneralReport";
			if(action.equals("HSE_AbnRptGen_getExcel.abnGenRpt"))
				title = "SHEAbnormalityGeneralReport";
			if(action.equals("AbnHTAGeneral_getExcel.abnGenRpt"))
				title = "HTAAbnormalityGeneralReport";
			if(action.equals("AbnSOCGeneral_getExcel.abnGenRpt"))
				title = "SOCAbnormalityGeneralReport";
			if(action.equals("AbnUNSGeneral_getExcel.abnGenRpt"))
				title = "UNSAbnormalityGeneralReport";
			
			String format = ExcelUtils.getFormat(request);
			CommonMessage.debugMsg("tblJSONObj...."+tblJSONObj);
			Workbook wb = abnormalityReportService.abnormalityGeneralReportExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);			
			ExcelUtils.writeToResponse(response, wb, title, format);
			
		}
	}
	
	private JSONObject getTableModel_UnsafeMonth(List<String[]> unsafeMonthList) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		 
		String[] colHeader = {" ","JUL-2013","JUL-2013","JUL-2013","AUG-2013","AUG-2013","AUG-2013","SEP-2013","SEP-2013","SEP-2013","OCT-2013","OCT-2013","OCT-2013","NOV-2013","NOV-2013","NOV-2013"};
		String[] colHeader1 = {" ","Abnormality","Near Miss","Incidents","Abnormality","Near Miss","Incidents","Abnormality","Near Miss","Incidents","Abnormality","Near Miss","Incidents","Abnormality","Near Miss","Incidents"};
		String[] emptyrow = new String[colHeader.length]; 	
		for( int i = 0; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		 
		 
		for(int i =0; i < colHeader.length; i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
		
			jqGridColModel.setWidth(100);				
			jqGridColModel.setEditable(false);
			if (i==0)
				jqGridColModel.setWidth(250);	
			if(i>0)
				jqGridColModel.setAlign("right");
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}
	private void processLineMonth(HttpServletRequest request,
			HttpServletResponse response, String Type) throws IOException {
		PrintWriter out = response.getWriter();			
		ColumnChart columnChart = new ColumnChart();
		String[] colHeader = {"Value1","JUL-2013","10"};
		String[] colHeader1 = {"Value1","AUG-2013","3"};
		String[] colHeader2 = {"Value1","SEP-2013","5"};
		String[] colHeader3 = {"Value1","OCT-2013","2"};
		String[] colHeader4 = {"Value1","NOV-2013","1"};
		
		List<String[]> graphData  = new ArrayList<String[]>();
		if (Type.equals("Condition")){
			String[] Header = {"Value1","JUL-2013","0"};
			String[] Header1 = {"Value1","AUG-2013","1"};
			String[] Header2 = {"Value1","SEP-2013","2"};
			String[] Header3 = {"Value1","OCT-2013","2"};
			String[] Header4 = {"Value1","NOV-2013","3"};
			colHeader=Header;
			colHeader1=Header1;
			colHeader2=Header2;
			colHeader3=Header3;
			colHeader4=Header4;
		}
		graphData.add(colHeader);
		graphData.add(colHeader1);
		graphData.add(colHeader2);
		graphData.add(colHeader3);
		graphData.add(colHeader4);
		List<String[]> graphData1  = new ArrayList<String[]>();
		String[] colFirstHeader = {"Value1","1","4"};
		String[] colFirstHeader1 = {"Value1","1","6"};
		String[] colFirstHeader2 = {"Value1","1","0"};
		String[] colFirstHeader3 = {"Value1","1","1"};
		String[] colFirstHeader4 = {"Value1","1","9"};
		
		List<Double> dataLine1 = new ArrayList<Double>();
		ChartSeries chartSeriesLine1 = new ChartSeries();
		List<String[]> graphData2  = new ArrayList<String[]>();
		String[] colSecondHeader = {"Value1","1","1"};
		String[] colSecondHeader1 = {"Value1","1","9"};
		String[] colSecondHeader2 = {"Value1","1","4"};
		String[] colSecondHeader3 = {"Value1","1","6"};
		String[] colSecondHeader4 = {"Value1","1","2"};
		
		ChartSeries chartSeriesLine2 = new ChartSeries();
		List<Double> dataLine2 = new ArrayList<Double>();
		graphData1.add(colFirstHeader);
		graphData1.add(colFirstHeader1);
		graphData1.add(colFirstHeader2);
		graphData1.add(colFirstHeader3);
		graphData1.add(colFirstHeader4);
		
		graphData2.add(colSecondHeader);
		graphData2.add(colSecondHeader1);
		graphData2.add(colSecondHeader2);
		graphData2.add(colSecondHeader3);
		graphData2.add(colSecondHeader4);
		
		List<String> xCategories = new ArrayList<String>();
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<Double> dataLine = new ArrayList<Double>();//declaring variable for line chart
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();//declaring variable for charseries were the data is to bound
		ChartSeries chartSeriesLine = new ChartSeries();//declaring variable for line were the x and y asix will be added
		
		int size = graphData.size();
		CommonMessage.debugMsg("graphData1...."+graphData1.toString().charAt(9));
		for( int i =0; i<size; i++)
		{	
			xCategories.add(graphData.get(i)[1]);
			dataLine.add(Double.parseDouble(graphData.get(i)[2]));//adding data to the variable of line chart	
			dataLine1.add(Double.parseDouble(graphData1.get(i)[2]));
			dataLine2.add(Double.parseDouble(graphData2.get(i)[2]));
		}
		
		chartSeriesLine.setName("Abnormality");//adding data 
		chartSeriesLine.setData(dataLine);//declaring name for the Line
		chartSeriesLine.setType(ChartTypes.LINE);//declaring which axis it shd be set
		chartSeriesLine.setyAxis(0);
		
		chartSeriesLine1.setName("Near Miss");
		chartSeriesLine1.setData(dataLine1);//declaring name for the Line
		chartSeriesLine1.setType(ChartTypes.LINE);//declaring which axis it shd be set
		chartSeriesLine1.setyAxis(0);
		
		chartSeriesLine2.setName("Incidents");
		chartSeriesLine2.setData(dataLine2);//declaring name for the Line
		chartSeriesLine2.setType(ChartTypes.LINE);//declaring which axis it shd be set
		chartSeriesLine2.setyAxis(0);
	
		chartSeriesList.add(chartSeriesLine);//adding data to chart to be dispalyed
		chartSeriesList.add(chartSeriesLine1);
		chartSeriesList.add(chartSeriesLine2);

		
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();//declaring variable for Y axis
		ChartYAxis yAxis = new ChartYAxis(); 
		yAxis.setMin(0);
		yAxis.getTitle().setText("All Values");//declaring caption for Y axis
		ChartYAxis y1Axis = new ChartYAxis();//declaring 2nd yaxis
		y1Axis.setOpposite(true);//setting the positon were it need to be shown
		y1Axis.setMin(0);
		chartYAxis.add(yAxis);//adding Yaxis to the chart
		chartYAxis.add(y1Axis);//adding Yaxis to the chart
		
		ChartXAxis xAxis = new ChartXAxis(); 
		xAxis.getTitle().setText("Unsafe Values");
		ChartXAxis xaxis = new ChartXAxis();
		xaxis.getTitle().setText("3");
		//lineChart.getxAxis().setTitle(xaxis.getTitle());
		//lineChart.setHeight(100);
		//lineChart.setWidth(500);
		JSONObject returnData = new JSONObject();
		JSONObject chartObj  = columnChart.drawChart(xCategories, chartSeriesList, " Unsafe "+Type, null, chartYAxis);
		/*UIUtils.dashBoardSetChartObject(request,chartObj);
		out.println(chartObj);
		out.close();*/
		returnData.put("chartData", chartObj);
		CommonMessage.debugMsg("chartData  "+returnData.toString());
		out.print(returnData);
		
	}
	private void processBarJH(HttpServletRequest request,
			HttpServletResponse response, String Type) throws IOException {
		
		PrintWriter out = response.getWriter();			
		ColumnChart columnChart = new ColumnChart();
		String[] colHeader = {"Value1","FIBRELINE 1","10"};
		String[] colHeader1 = {"Value1","FH 123 - SHEETER 1,","3"};
		String[] colHeader2 = {"Value1","DISC CHIPPER 1","5"};
		String[] colHeader3 = {"Value1","CAUSTICIZING","2"};
		String[] colHeader4 = {"Value1","FIBRELINE 2","1"};
		
		List<String[]> graphData  = new ArrayList<String[]>();
		if (Type.equals("Condition")){
			String[] Header = {"Value1","FIBRELINE 1","0"};
			String[] Header1 = {"Value1","FH 123 - SHEETER 1,","1"};
			String[] Header2 = {"Value1","DISC CHIPPER 1","2"};
			String[] Header3 = {"Value1","CAUSTICIZING","2"};
			String[] Header4 = {"Value1","FIBRELINE 2","3"};
			colHeader=Header;
			colHeader1=Header1;
			colHeader2=Header2;
			colHeader3=Header3;
			colHeader4=Header4;
		}
		graphData.add(colHeader);
		graphData.add(colHeader1);
		graphData.add(colHeader2);
		graphData.add(colHeader3);
		graphData.add(colHeader4);
		List<String[]> graphData1  = new ArrayList<String[]>();
		String[] colFirstHeader = {"Value1","1","4"};
		String[] colFirstHeader1 = {"Value1","1","6"};
		String[] colFirstHeader2 = {"Value1","1","0"};
		String[] colFirstHeader3 = {"Value1","1","1"};
		String[] colFirstHeader4 = {"Value1","1","9"};
		
		List<Double> dataLine1 = new ArrayList<Double>();
		ChartSeries chartSeriesLine1 = new ChartSeries();
		List<String[]> graphData2  = new ArrayList<String[]>();
		String[] colSecondHeader = {"Value1","1","1"};
		String[] colSecondHeader1 = {"Value1","1","9"};
		String[] colSecondHeader2 = {"Value1","1","4"};
		String[] colSecondHeader3 = {"Value1","1","6"};
		String[] colSecondHeader4 = {"Value1","1","2"};
		
		ChartSeries chartSeriesLine2 = new ChartSeries();
		List<Double> dataLine2 = new ArrayList<Double>();
		graphData1.add(colFirstHeader);
		graphData1.add(colFirstHeader1);
		graphData1.add(colFirstHeader2);
		graphData1.add(colFirstHeader3);
		graphData1.add(colFirstHeader4);
		
		graphData2.add(colSecondHeader);
		graphData2.add(colSecondHeader1);
		graphData2.add(colSecondHeader2);
		graphData2.add(colSecondHeader3);
		graphData2.add(colSecondHeader4);
		
		List<String> xCategories = new ArrayList<String>();
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<Double> dataLine = new ArrayList<Double>();//declaring variable for line chart
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();//declaring variable for charseries were the data is to bound
		ChartSeries chartSeriesLine = new ChartSeries();//declaring variable for line were the x and y asix will be added
		
		int size = graphData.size();
		CommonMessage.debugMsg("graphData1...."+graphData1.toString().charAt(9));
		for( int i =0; i<size; i++)
		{	
			xCategories.add(graphData.get(i)[1]);
			dataLine.add(Double.parseDouble(graphData.get(i)[2]));//adding data to the variable of line chart	
			dataLine1.add(Double.parseDouble(graphData1.get(i)[2]));
			dataLine2.add(Double.parseDouble(graphData2.get(i)[2]));
		}
		
		chartSeriesLine.setName("Abnormality");//adding data 
		chartSeriesLine.setData(dataLine);//declaring name for the Line
		chartSeriesLine.setType(ChartTypes.COLUMN);//declaring which axis it shd be set
		chartSeriesLine.setyAxis(0);
		
		chartSeriesLine1.setName("Near Miss");
		chartSeriesLine1.setData(dataLine1);//declaring name for the Line
		chartSeriesLine1.setType(ChartTypes.COLUMN);//declaring which axis it shd be set
		chartSeriesLine1.setyAxis(0);
		
		chartSeriesLine2.setName("Incidents");
		chartSeriesLine2.setData(dataLine2);//declaring name for the Line
		chartSeriesLine2.setType(ChartTypes.COLUMN);//declaring which axis it shd be set
		chartSeriesLine2.setyAxis(0);
	
		chartSeriesList.add(chartSeriesLine);//adding data to chart to be dispalyed
		chartSeriesList.add(chartSeriesLine1);
		chartSeriesList.add(chartSeriesLine2);

		
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();//declaring variable for Y axis
		ChartYAxis yAxis = new ChartYAxis(); 
		yAxis.setMin(0);
		yAxis.getTitle().setText("All Values");//declaring caption for Y axis
		ChartYAxis y1Axis = new ChartYAxis();//declaring 2nd yaxis
		y1Axis.setOpposite(true);//setting the positon were it need to be shown
		y1Axis.setMin(0);
		chartYAxis.add(yAxis);//adding Yaxis to the chart
		chartYAxis.add(y1Axis);//adding Yaxis to the chart
		
		ChartXAxis xAxis = new ChartXAxis(); 
		xAxis.getTitle().setText("Unsafe Values");
		ChartXAxis xaxis = new ChartXAxis();
		xaxis.getTitle().setText("3");
		//lineChart.getxAxis().setTitle(xaxis.getTitle());
		//lineChart.setHeight(100);
		//lineChart.setWidth(500);
		JSONObject returnData = new JSONObject();
		JSONObject chartObj  = columnChart.drawChart(xCategories, chartSeriesList, " Unsafe "+Type, null, chartYAxis);
		/*UIUtils.dashBoardSetChartObject(request,chartObj);
		out.println(chartObj);
		out.close();*/
		returnData.put("chartData", chartObj);
		CommonMessage.debugMsg("chartData  "+returnData.toString());
		out.print(returnData);
	}
	private JSONObject getTableModel_Unsafe(List<String[]> unsafeDrillDnList) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		 
		String[] colHeader = {"JH","Abnormality","Abnormality","Near Miss","Near Miss","Incidents","Incidents"};
		String[] colHeader1 = {"JH","Unsafe Act","Unsafe Condition","Unsafe Act","Unsafe Condition","Unsafe Act","Unsafe Condition"};
		String[] emptyrow = new String[colHeader.length]; 	
		for( int i = 0; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		 
		 
		for(int i =0; i < colHeader.length; i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
		
			jqGridColModel.setWidth(100);				
			jqGridColModel.setEditable(false);
			if (i==0)
				jqGridColModel.setWidth(250);	
			if(i>0)
				jqGridColModel.setAlign("right");
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}
	private JSONObject getTableModel(List<String[]> abnormalityReportList, CommonFilter commonFilter) {
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = abnormalityReportList.get(0);	
		String [] colHeader1 = abnormalityReportList.get(1); 
		
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);	
		jqGridTableModel.setEnableFilter(true);
		String abnType=commonFilter.getAbnormalityType();
		if("SHE".equals(abnType)){
			jqGridTableModel.getRowHeaders().add(colHeader);
		}else if("SOC".equals(abnType)){
			jqGridTableModel.getRowHeaders().add(colHeader);
		}else if("HTA".equals(abnType)){
			jqGridTableModel.getRowHeaders().add(colHeader);
		}else
			jqGridTableModel.getRowHeaders().add(colHeader1);
		String headerSql = "'SELECT ";
		for(int i =0; i < colHeader.length; i++)
		{			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
					
			jqGridColModel.setWidth( 100);				
			jqGridColModel.setAlign("right");
			jqGridColModel.setEditable(false);
			
			CommonMessage.debugMsg(i+"  i  "+colHeader[i]);
			
			if(colHeader[i].equals("RN") || colHeader[i].equals("ORDERDATE") )
			{
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);			
			}else if(colHeader[i].equals("ASSEMBLY") ||colHeader[i].equals("RESPONSIBILITY") )
			{
				jqGridColModel.setAlign("left");			
			}
			else if(colHeader[i].equals("Abnormality Type") || colHeader[i].equals("Abnormality")
					|| colHeader[i].equals("Why Abnormality happened?")|| colHeader[i].equals("Responsibility")){
				if(colHeader[i].equals("Responsibility") && !commonFilter.getAbnType().equals("SHE"))
					colHeader[i] = "Completed By";
				jqGridColModel.setWidth(300);				
				jqGridColModel.setAlign("left");
			}	
			else if(colHeader[i].equals("Date of Detection") || colHeader[i].equals("Target Date") ){
				jqGridColModel.setWidth( 100);				
				jqGridColModel.setAlign("center");
			}	
			else if(colHeader[i].equals("Station") || colHeader[i].equals("Detector")){
				jqGridColModel.setWidth( 200);				
				jqGridColModel.setAlign("left");
			}	
			else if(colHeader[i].equals("Mould")){
				jqGridColModel.setWidth( 100);				
				jqGridColModel.setAlign("left");
			}	
			else if( colHeader[i].equals("Related To") || colHeader[i].equals("Tag Class")
					|| colHeader[i].equals("Status") || colHeader[i].equals("Source")
					 || colHeader[i].equals("Mode"))
			{
				jqGridColModel.setWidth( 80);				
				jqGridColModel.setAlign("left");
			}
			else if(colHeader[i].equals("Items") )
			{
				colHeader[i] = "Items Cleaning Lubrication Inspection";
				jqGridColModel.setWidth(250);				
				jqGridColModel.setAlign("left");
			}
			else if( colHeader[i].equals("Counter Measure"))
			{
				colHeader[i] = "Items Cleaning Lubrication Inspection(Counter Measure)";
				jqGridColModel.setWidth(320);				
				jqGridColModel.setAlign("left");
			}
			else if(colHeader[i].equals("Machine"))
			{
				jqGridColModel.setWidth(350);
				jqGridColModel.setAlign("left");
			}
			else if(i==5 ||  i==9 || i==10)
			{
				jqGridColModel.setWidth(140);	
			}
			else if(i==8 || i==6|| i==11)
			{
				jqGridColModel.setAlign("left");
				jqGridColModel.setWidth(200);
			}
			else
			{
				jqGridColModel.setWidth( 200);				
				jqGridColModel.setAlign("left");
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		 JSONObject tableModel1 = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel1;
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
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
	
 }
			
	




