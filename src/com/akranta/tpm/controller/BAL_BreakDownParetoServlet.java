package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartData;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.ColumnChart;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_BreakDownParetoService;
import com.akranta.tpm.service.BAL_BreakdownRptService;
import com.akranta.tpm.service.BAL_LossSummaryRptService;
import com.akranta.tpm.service.impl.BAL_BreakDownParetoServiceImpl;
import com.akranta.tpm.service.impl.BAL_BreakdownRptServiceImpl;
import com.akranta.tpm.service.impl.BAL_PpMatrixRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_BreakDownParetoServlet extends HttpServlet {
		
		/**
		 * Created Manikandan
		 */
		private static final long serialVersionUID = 1L;
		BAL_BreakDownParetoService breakdownParetoRptService ; 
		public BAL_BreakDownParetoServlet(){
			/*try {
				breakdownParetoRptService = new BreakDownParetoServiceImpl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
			if( ! UIUtils.checkUserSession( request,response))
				return; 
			
			String dispatchUrl =null;
			CommonFilter commonFilter = null;
			String action = UIUtils.getActionPart(request);
			HttpSession httpSession = request.getSession(false);

			try {
				breakdownParetoRptService = (BAL_BreakDownParetoServiceImpl)UIUtils.getServiceObject(request,"BreakDownParetoServiceImpl");
			} catch (ServiceObjectCreationException e) {
				CommonFunctions.debugMsg(e);
			}
			
			 if( action.equals("filterXmlbreakDwnRpt_input.bdmprto")){
				    PrintWriter out = response.getWriter();
					response.setContentType("xml"); 
					System.out.println("action "+ action); 
					out.print("<fromDate>01-Jan-2012</fromDate>");
					UIUtils.forwardRequest(request, response, "/tiles/xml/BreakDownPareto.xml") ;
				 }	
			 
			 if(action.equals("breakDwnRpt_input.bdmprto")) 
			{
				UIUtils.forwardRequest(request, response, "/pages/Reports/BreakDownPareto.jsp");
			}
	
			else if(action.equals("breakDwnRpt_getCol.bdmprto"))
			{
				 httpSession.removeAttribute("commonFilterIden");
				 String firstClick =request.getParameter("firstClick");
				 PrintWriter out = response.getWriter();	
				 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 
					 commonFilter =(CommonFilter) httpSession.getAttribute("commonFilterIden");
				 }	
				 if(commonFilter==null)
					 commonFilter = new CommonFilter();
				
				 FilterValues.getCommonFilters(request,commonFilter);
				 FilterValues.getBDRelated(request, commonFilter);
				 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
					 
			 	  }
				 response.setContentType("text/html");
			    
				 setParetoOption(commonFilter);
				 httpSession.removeAttribute("commonFilterIden");
				 httpSession.setAttribute("commonFilterIden",commonFilter);
				 
				String colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BreakDwnParetoRpt","breakdownPareto");
				String paretoOptions =commonFilter.getDrillLevel(); 
				if( paretoOptions.equals("SECT"))
					paretoOptions = "Section ";
				else if( paretoOptions.equals("MCHM"))
					paretoOptions = "Equipment ";
				else if( paretoOptions.equals("BSCM"))
					paretoOptions = "Cause Wise";
				else if( paretoOptions.equals("BPHM"))
					paretoOptions = "Phenomena ";
				else if( paretoOptions.equals("CELL"))
					paretoOptions = "Line ";
				else if( paretoOptions.equals("ASSM"))
					paretoOptions = "Assembly ";
				else if( paretoOptions.equals("EQGM"))
					paretoOptions = "Equipment Group ";
				JSONObject colModelObj = JSONObject.fromString(colmodel );
				colModelObj.getJSONArray("rowHeaders").getJSONArray(0).put(1, paretoOptions);
				
				colModelObj.set("tableHeight", "85%%");
				colModelObj.set("tableWidth", "108%%");
				//colModelObj.set();
				
				httpSession.removeAttribute("paretoReportColModel");
			    httpSession.setAttribute("paretoReportColModel", colModelObj);
				out.print(colModelObj);
				
			}
			else if( action.equals("breakDwnRpt_getData.bdmprto") )
			{ CommonFunctions.debugMsg("mak getdata ");
				try
				{
					 PrintWriter out = response.getWriter();
					 commonFilter = populateCommonFilter(request,"commonFilterIden",false);
					// commonFilter =(CommonFilter) httpSession.getAttribute("commonFilterIden");	
					 CommonFunctions.debugMsg("mak  "+commonFilter.getCboparetooptions());
			      	commonFilter.setISFORGRAPH('N');
					
				    String backValue=request.getParameter("backVal");
				    System.out .println("backValue="+backValue);
				    
					 List< String[]> Breakdwnpareto  =  breakdownParetoRptService.getAllbkdpareto(commonFilter);
					 JSONObject BreakdwnparetoData =  UIUtils.convertToJqGridTableObject(Breakdwnpareto,request,0,0); 
					 
					 out.println(BreakdwnparetoData);
	  			 	 httpSession.removeAttribute("commonFilterIden");
	  			 	 httpSession.setAttribute("commonFilterIden", commonFilter);
					}
				
				
			   catch(Exception e)
				{
					System.out.println("error " +e.getMessage());
				}
			}
			else if( action.equals("breakDwnRpt_getExcel.bdmprto")){
				
				//HttpSession httpSession = request.getSession(false);
				commonFilter = populateCommonFilter(request,"commonFilterIden",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("paretoReportColModel");
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				String date   = fromMonth +"  -  "+ toMonth ;
				
				tblJSONObj.put("title", "Breakdown Pareto Report "+"  -  "+date);
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = breakdownParetoRptService.paretoExportExcel(commonFilter,tblJSONObj,format);
				  
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "BreakdownParetoReport", format);
				
			}
			
			else if( action.equals("chart.bdmprto")){
				
				PrintWriter out = response.getWriter();
				//processChart(request,response);
				
				ColumnChart columnChart = new ColumnChart();
				String forDashboard = request.getParameter("dashboard");
				if( ! "true".equals(forDashboard)){
					commonFilter = (CommonFilter) httpSession.getAttribute("commonFilterIden");
				}
				else{
					commonFilter = new  CommonFilter();
					
					FilterValues.getCommonFilters(request,commonFilter);
					FilterValues.getBDRelated(request, commonFilter);
					commonFilter.setGridSortColumn("1");
					commonFilter.setGridSortOrder("asc");
					setParetoOption(commonFilter);
				}
					
				commonFilter.setISFORGRAPH('Y');
				
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				
				String fromDate = commonFilter.getFromDate();
				String toDate = commonFilter.getToDate();
				
				String monthWise = commonFilter.getMonwise();
				String date   = null;
				
				if("Y".equals(monthWise))				
					 date   = fromMonth +"  -  "+ toMonth ;
				else
					 date   = fromDate +"  -  "+ toDate ;
				
				
				String paretoOptions =commonFilter.getDrillLevel(); 
				if( paretoOptions.equals("SECT"))
					paretoOptions = "Section Wise";
				else if( paretoOptions.equals("MCHM"))
					paretoOptions = "Machine Wise";
				else if( paretoOptions.equals("BSCM"))
					paretoOptions = "Cause Wise";
				else if( paretoOptions.equals("BPHM"))
					paretoOptions = "Phenomena Wise";
				else if( paretoOptions.equals("CELL"))
					paretoOptions = "Line Wise";
				else if( paretoOptions.equals("ASSM"))
					paretoOptions = "Assembly Wise";
				else if( paretoOptions.equals("EQGM"))
					paretoOptions = "Equipment Group Wise";
				
				commonFilter.setISFORGRAPH('Y');
				
				List<String[]> graphData  = breakdownParetoRptService.getAllbkdpareto(commonFilter);//adding Data to graph
				List<String> xCategories = new ArrayList<String>();
				List<Double> dataLine = new ArrayList<Double>();//declaring variable for line chart
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();//declaring variable for charseries were the data is to bound
				ChartSeries chartSeriesLine = new ChartSeries();//declaring variable for line were the x and y asix will be added
				
				ChartSeries barChart = new ChartSeries();//declaring variable for barchart
				List<Double> barDataList = new ArrayList<Double>();//declaring variable for bar chart
				
				
				for( int i =0; i<graphData.size(); i++)
				{	
					xCategories.add(graphData.get(i)[1]);
					dataLine.add(Double.parseDouble(graphData.get(i)[3]));//adding data to the variable of line chart
					barDataList.add(Double.parseDouble(graphData.get(i)[2]));//adding data to the variable of Bar chart
					
				}
				
				
				barChart.setName("DownTime");//declaring name for the bar
				barChart.setData(barDataList);//adding data 
				barChart.setType(ChartTypes.COLUMN);//declaring which axis it shd be set
				chartSeriesLine.setName("Cumulative");//adding data 
				chartSeriesLine.setData(dataLine);//declaring name for the Line
				chartSeriesLine.setType(ChartTypes.LINE);//declaring which axis it shd be set
				chartSeriesLine.setyAxis(1);
			
				chartSeriesList.add(barChart);//adding data to chart to be dispalyed
				chartSeriesList.add(chartSeriesLine);//adding data to chart to be dispalyed

				List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();//declaring variable for Y axis
				List<ChartXAxis> chartXAxis =  new ArrayList<ChartXAxis>();//declaring variable for Y axis
				ChartYAxis yAxis = new ChartYAxis();
				
				yAxis.setMin(0);
				yAxis.getTitle().setText("DownTime in Minutes");//declaring caption for Y axis
				ChartYAxis y1Axis = new ChartYAxis();//declaring 2nd yaxis
				ChartXAxis xaxis = new ChartXAxis();
				y1Axis.setOpposite(true);//setting the positon were it need to be shown
				y1Axis.setMin(0);
				y1Axis.getTitle().setText("Cumulative Percentage");//declaring caption for Y axis
				xaxis.getTitle().setText("Equipment");
				//CommonFunctions.debugMsg("xaxis.getTitle()"+xaxis.getTitle().getText());
				chartYAxis.add(yAxis);//adding Yaxis to the chart
				chartYAxis.add(y1Axis);//adding Yaxis to the chart
				chartXAxis.add(xaxis);
				columnChart.getxAxis().setTitle(xaxis.getTitle());
				JSONObject chartObj  = columnChart.drawChart(xCategories, chartSeriesList, "BreakDown Pareto ("+ paretoOptions +")  "+date, null, chartYAxis);
				String dashboardIdnt = request.getParameter("dashboardIdent");
				if( UIUtils.isValidKeyId(dashboardIdnt));
					chartObj.set("dashboardIdent", dashboardIdnt);
					
				out.println(chartObj);
				out.close();
				
			}
		
		}
		private void setParetoOption(CommonFilter commonFilter){
		 	if(FilterCondSql.getComboSelectionId(commonFilter.getCboparetooptions()).equals("EW"))
			{
				CommonFunctions.debugMsg("OPtions  "+commonFilter.getCboparetooptions());
				commonFilter.setDrillLevel("MCHM");
			}
	     	else if(FilterCondSql.getComboSelectionId(commonFilter.getCboparetooptions()).equals("EGW"))
			{
				CommonFunctions.debugMsg("OPtions  "+commonFilter.getCboparetooptions());
				commonFilter.setDrillLevel("EQGM");
			}
	     	else if(FilterCondSql.getComboSelectionId(commonFilter.getCboparetooptions()).equals("AW"))
			{
				CommonFunctions.debugMsg("OPtions  "+commonFilter.getCboparetooptions());
				commonFilter.setDrillLevel("ASSM");
			}
	     	else if(FilterCondSql.getComboSelectionId(commonFilter.getCboparetooptions()).equals("CW"))
			{
				CommonFunctions.debugMsg("OPtions  "+commonFilter.getCboparetooptions());
				commonFilter.setDrillLevel("CELL");
			}
	     	else if(FilterCondSql.getComboSelectionId(commonFilter.getCboparetooptions()).equals("PW"))
			{
				CommonFunctions.debugMsg("OPtions  "+commonFilter.getCboparetooptions());
				commonFilter.setDrillLevel("BPHM");
			}
	     	else if(FilterCondSql.getComboSelectionId(commonFilter.getCboparetooptions()).equals("CauseW"))
			{
				CommonFunctions.debugMsg("OPtions  "+commonFilter.getCboparetooptions());
				commonFilter.setDrillLevel("BCSM");
			}
	     	else if(FilterCondSql.getComboSelectionId(commonFilter.getCboparetooptions()).equals("SW"))
			{
				CommonFunctions.debugMsg("OPtions  "+commonFilter.getCboparetooptions());
				commonFilter.setDrillLevel("SECT");
			} 
	     	else
	     		 commonFilter.setDrillLevel("MCHM");
	     	if(!UIUtils.isValidKeyId(commonFilter.getTxttop()))
	     		commonFilter.setTxttop("20");
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
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
	
	}

