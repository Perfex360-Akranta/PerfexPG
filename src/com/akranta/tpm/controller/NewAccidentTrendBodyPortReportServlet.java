package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartPie;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.NewAccidentTrendReportService;
import com.akranta.tpm.service.impl.NewAccidentTrendReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;
/**
 * Servlet implementation class NewAccidentTrendBodyPortReportServlet
 */
@WebServlet("/NewAccidentTrendBodyPortReportServlet")
public class NewAccidentTrendBodyPortReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewAccidentTrendReportService  NewAccidenttrendreportservice; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAccidentTrendBodyPortReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
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
	//	doGet(request, response);
		try{
			process(request,response);
		}
		catch(Exception e){
		  e.printStackTrace();	
		}
	}
private void process(HttpServletRequest request,HttpServletResponse response)throws Exception,IOException{
	 HttpSession httpSession=request.getSession(false);
	 NewAccidenttrendreportservice=(NewAccidentTrendReportServiceImpl)UIUtils.getServiceObject(request,"NewAccidentTrendReportServiceImpl");
	 NewAccidenttrendreportservice.NewAccidentTrendReportServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	 String action=UIUtils.getActionPart(request);
	 if(action.equals("NewAccidentRpt_input.natr")){
		    System.out.println("New Accident Report");
			String filterString = request.getParameter("filterString");
			String module = request.getParameter("module"); 
			request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/NewAccidentTrendReport.jsp");
			rd.forward(request, response); 
	 }
	 else if(action.equals("NewAccidentRpt_getCol.natr"))
		{	
			String firstClick =request.getParameter("firstClick");
			CommonFilter  commonFilter  ;
			commonFilter = new CommonFilter(); 
			
				System.out.println("getcol:" +firstClick);
			 
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
			 {
				 commonFilter =(CommonFilter) httpSession.getAttribute("HseAccTrendRptCommonFilter");
			 }	
			if(commonFilter==null)
			 commonFilter = new CommonFilter(); 
			 
			 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
			 commonFilter= 	FilterValues.getSafty(request, commonFilter);
			 
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
		 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
			 }
			 httpSession.removeAttribute("HseAccTrendRptCommonFilter");
			 httpSession.setAttribute("HseAccTrendRptCommonFilter",commonFilter);
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 List<String[]> hseAccidentRpt  = NewAccidenttrendreportservice.getNewAccidentRpt(commonFilter);
			 JSONObject colModel =GetTableModel_ACCTREND(hseAccidentRpt,commonFilter);
			 colModel.set("tableHeight", "71%%");
			 colModel.set("tableWidth", "107%%");
			 
			 httpSession.removeAttribute("HseAccTrendRptColModel");
			 httpSession.setAttribute("HseAccTrendRptColModel", colModel);
			 CommonFunctions.debugMsg(colModel);
			 out.println(colModel);
		}
	 else if(action.equals("NewAccidentRpt_getData.natr") )
		{
			PrintWriter out = response.getWriter();
			try
			{
				CommonFunctions.debugMsg("NewAccidentRpt_getData.natr");
				UIUtils.displayRequestParamsValue(request);
				 
				 CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("HseAccTrendRptCommonFilter");
				 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				 commonFilter= 	FilterValues.getSafty(request, commonFilter);
				 JSONObject jsonObject = new JSONObject();
				 
				 List<String[]> hseAccidentRpt  = NewAccidenttrendreportservice.getNewAccidentRpt(commonFilter);	
	     		 CommonFunctions.debugMsg("customerComplaintTrendList.size("+hseAccidentRpt.size()+")");
	     		 CommonFunctions.debugMsg("getTotalRecordCnt"+commonFilter.getTotalRecordCnt());
	     		 	if (hseAccidentRpt.size() > 3) 
	     		 	jsonObject = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,2,0,hseAccidentRpt.size());
	     		 	
	     		 	 CommonFunctions.debugMsg(jsonObject);
	     		 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
		   }
			catch(Exception e)
			{
			}
		}
	     else if( action.equals("NewAccidentRpt_getExcel.natr")){
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"HseAccTrendRptCommonFilter",false);
			CommonFunctions.debugMsg("gridFilter "+commonFilter.getGridFilter());
			JSONObject tblJSONObj = UIUtils.getXlColModel( request, response);
			tblJSONObj.put("title","Accident Trend Report");
         String format = ExcelUtils.getFormat(request);				
			Workbook wb = NewAccidenttrendreportservice.NewAccidentTrendExportExcel(commonFilter,tblJSONObj,format);
			ExcelUtils.writeToResponse(response, wb, "AccidentTrendReport", format);
		}
	     else if(action.equals("NewBodyPartRpt_input.natr")){
	    	    request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
				RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/NewBodyPartWiseRpt.jsp");
				rd.forward(request, response); 
	     }
	     else if(action.equals("NewBodyPartRpt_getCol.natr"))
			{	
	    	   System.out.println("NewBodyPartRpt Data");
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"HseBodyVsAccRptCommonFilter",true);
				commonFilter.setMonwise("Y");
				
				List<String[]> hseAccidentRpt  =NewAccidenttrendreportservice.getNewBodyPartRpt(commonFilter);
				CommonFunctions.debugMsg("jhCompRptList"+hseAccidentRpt.size());
				System.out.println("Printing the data");
				for (String[] row : hseAccidentRpt) {
				    System.out.println(Arrays.toString(row));
				}
				
				JSONObject HSEData = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,1,0,hseAccidentRpt.size());
				CommonFunctions.debugMsg(HSEData);
				httpSession.setAttribute("HseBodyVsAccRptdata", HSEData);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				jqGridTableModel.setSortable(false);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);
				System.out.println("get(1): " + Arrays.toString(hseAccidentRpt.get(1)));
				System.out.println("get(0): " + Arrays.toString(hseAccidentRpt.get(0)));
				String [] colHeader = hseAccidentRpt.get(0);			
				String [] colHeaderCond = hseAccidentRpt.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("data", HSEData);
				CommonFunctions.debugMsg("colModel   "+jsonObject);
							
				jsonObject.set("tableHeight", "77%%");
				jsonObject.set("tableWidth", "107%%");
				CommonFunctions.debugMsg(jsonObject);
				httpSession.removeAttribute("HseBodyVsAccRptColModel");
				httpSession.setAttribute("HseBodyVsAccRptColModel", jsonObject);
				out.println(jsonObject);	
			}
			
			else if( action.equals("NewBodyPartRpt_getData.natr") )
			{
				 System.out.println("Inside thr NewBodyPart");
				PrintWriter out = response.getWriter();
				try
				{
					 UIUtils.displayRequestParamsValue(request);
					 String page = request.getParameter("page");	
					 CommonFunctions.debugMsg("page......"+page);
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"HseBodyVsAccRptCommonFilter",false);
					
					 JSONObject jsonObject = new JSONObject();
									
		        		 List<String[]> hseAccidentRpt  =NewAccidenttrendreportservice.getNewBodyPartRpt(commonFilter);
		        		 if(hseAccidentRpt.size()>2){
		        		 	jsonObject = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,1,0,hseAccidentRpt.size());
		        		 	out.println(jsonObject);
		        		 }	
						 commonFilter.setViewClick('Y');	  			 	
			  			 httpSession.removeAttribute("HseBodyVsAccRptCommonFilter");
			  			 httpSession.setAttribute("HseBodyVsAccRptCommonFilter", commonFilter);

			    }
				catch(Exception e)
				{
				}
			}
			else if( action.equals("NewBodyPartRpt_getExcel.natr")){
				CommonFilter commonFilter = populateCommonFilter(request,"HseBodyVsAccRptCommonFilter",false);
				commonFilter.setViewClick('Y');
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("HseBodyVsAccRptColModel");
				tblJSONObj.put("title", "Body Part Versus Accident Report");
				String format = ExcelUtils.getFormat(request);
				commonFilter.setFromRow(tmpFromRow);
				Workbook wb =NewAccidenttrendreportservice.NewBodyPortExportExcel(commonFilter,tblJSONObj,format);		
				ExcelUtils.writeToResponse(response, wb, "BodyPartVersusAccidentReport", format);
			}
	       
			else if(action.equals("NewModeWiseRptReport_input.natr"))
			{
				CommonFunctions.debugMsg("Brfore jsp");
				//RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/Abnormality.jsp"); 
				request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
				request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
				RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/HSEModeWiseRpt.jsp");
				rd.forward(request, response); 
			}
	        
			else if(action.equals("NewModeWiseRptReport_getCol.natr"))
			{	
				
				String firstClick =request.getParameter("firstClick");
				 CommonFilter  commonFilter  ;
				 commonFilter = new CommonFilter(); 
				
					System.out.println("getcol:" +firstClick);
				 
				 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
				 {
					 commonFilter =(CommonFilter) httpSession.getAttribute("HseModeWiseRptReportCommonFilter");
				 }	
				if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
				 
				 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				 commonFilter= 	FilterValues.getSafty(request, commonFilter);
				// populateCommonFilter(request,"HseAccTrendRptCommonFilter",true);
				 
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
			 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
				 }
				 httpSession.removeAttribute("HseModeWiseRptReportCommonFilter");
				 httpSession.setAttribute("HseModeWiseRptReportCommonFilter",commonFilter);
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 List<String[]> hseAccidentRpt  =NewAccidenttrendreportservice.getModeWiseReport(commonFilter);
						 // hseAccidentRptService.getModeWiseReport(commonFilter);
				 
				 JSONObject HSEData = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,0,0,hseAccidentRpt.size());
					CommonFunctions.debugMsg(HSEData);
					httpSession.setAttribute("HseBodyVsRptReportdata", HSEData);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					jqGridTableModel.setSortable(false);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = hseAccidentRpt.get(1);			
					String [] colHeaderCond = hseAccidentRpt.get(0);
					
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					colModel.put("data", HSEData);
					CommonFunctions.debugMsg("colModel   "+colModel);
				 
				//JSONObject colModel = getTableModel_BODYVSACC(hseAccidentRpt,commonFilter);
				 colModel.set("tableHeight", "80%%");
				 colModel.set("tableWidth", "107%%");
				 
				 
				 httpSession.removeAttribute("HseModeWiseRptReportColModel");
				 httpSession.setAttribute("HseModeWiseRptReportColModel", colModel);
				 CommonFunctions.debugMsg(colModel);
				 out.println(colModel);
				
				 out.close();
			}
			
			else if( action.equals("NewModeWiseRptReport_getData.natr") )
			{
				PrintWriter out = response.getWriter();
				try
				{
					 UIUtils.displayRequestParamsValue(request);
					 String page = request.getParameter("page");	
					 CommonFunctions.debugMsg("page......"+page);
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"HseModeWiseRptReportCommonFilter",false);
					System.out.println("Date in get Data "+commonFilter.getFromMonth());
					 JSONObject jsonObject = new JSONObject();			
		        		 List<String[]> hseAccidentRpt  = NewAccidenttrendreportservice.getModeWiseReport(commonFilter);
		        		 if(hseAccidentRpt.size() > 3)
							{
		        		 jsonObject = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,3,0,hseAccidentRpt.size()); 
		        	 
					
					 out.println(jsonObject);
					 commonFilter.setViewClick('Y');	  			 	
		  			 httpSession.removeAttribute("HseModeWiseRptReportCommonFilter");
		  			 httpSession.setAttribute("HseModeWiseRptReportCommonFilter", commonFilter);
		  			 
		  			httpSession.setAttribute("fromMonth", commonFilter.getFromMonth());
		  			httpSession.setAttribute("HseModeWiseRptReportCommonFilter", commonFilter);
							}
		        		 else
		        		 {
		        			 
		        		 }

			    }
				catch(Exception e)
				{
				}
			}else if( action.equals("NewModeWiseRptReport_getExcel.natr")){
				
				CommonFilter commonFilter = populateCommonFilter(request,"HseModeWiseRptReportCommonFilter",false);
				commonFilter.setViewClick('Y');
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("HseModeWiseRptReportColModel");
				
				tblJSONObj.put("title", "Mode wise Report Table");
				String format = ExcelUtils.getFormat(request);
				commonFilter.setFromRow(tmpFromRow);
				Workbook wb = NewAccidenttrendreportservice.ModeWiseReportExportExcel(commonFilter,tblJSONObj,format);
				
				
				ExcelUtils.writeToResponse(response, wb, "ModeWiseDataTableReport", format);	
			}
			else if(action.equals("IncidentIdentifed_input.natr")){
				RequestDispatcher rd=request.getRequestDispatcher("pages/SHE/EmployeeIncidentIdentified.jsp");
				rd.forward(request, response);
			}
			else if (action.equals("IncidentIdentifed_getCol.natr")) 
			{
				PrintWriter out = response.getWriter();	
				httpSession=request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"EmployeeIncidentCommonFilter",true);
				System.out.println("From  Month :" +commonFilter.getFromMonth() +" From Date:"+ commonFilter.getFromDate());
				System.out.println("From  Month :" +commonFilter.getFromMonth() +" From Date:"+ commonFilter.getFromDate());
				List<String[]> employeeWiseMonthWiseIncident= NewAccidenttrendreportservice.getEmployeeIncident(commonFilter);
				JSONObject jsonObject = getTableModelEmployeeWiseIncident(employeeWiseMonthWiseIncident);
				jsonObject.set("tableHeight", "72%%");
			    jsonObject.set("tableWidth", "106%%");
				httpSession.removeAttribute("IncidentColData");
				httpSession.setAttribute("IncidentColData", jsonObject);
				System.out.println("Table Model:" + jsonObject);
				out.println(jsonObject);
			}
			else if (action.equals("IncidentIdentifed_getData.natr")) 
			{
				PrintWriter out = response.getWriter();	
				httpSession=request.getSession(false);
				CommonFilter commonFilter=populateCommonFilter(request,"EmployeeIncidentCommonFilter",false);
				List<String[]> IncidentGridData = NewAccidenttrendreportservice.getEmployeeIncident(commonFilter);
				System.out.println("Common Filter "+commonFilter.getToMonth());
				JSONObject dataJson = UIUtils.convertToJqGridTableObject(IncidentGridData,request,4, 0,commonFilter.getTotalRecordCnt()+5);
				out.print(dataJson);
				httpSession.removeAttribute("EmployeeIncidentCommonFilter");
				httpSession.setAttribute("EmployeeIncidentCommonFilter", commonFilter);		
			}
		   else if (action.equals("IncidentIdentifed_getExcel.natr")) 
			{
			    httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"EmployeeIncidentCommonFilter",false);
				JSONObject tblJSONObj = UIUtils.getXlColModel( request, response);
				tblJSONObj.put("title","IncidentIdentified");
	            String format = ExcelUtils.getFormat(request);	
	            
				Workbook wb = NewAccidenttrendreportservice.EmpAccidentExportExcel(commonFilter,tblJSONObj,format);
				ExcelUtils.writeToResponse(response, wb, "EmployeeIncidentIdentified", format);
			}
			
			else if( action.equals("NewModeWiseRptReportchart.natr")){
				System.out.println("NewModeWiseRptReportchart.natr");
				processChart2(request,response);
			}
			else if( action.equals("Bodychart.natr")){
				String forDashboard = request.getParameter("dashboard");			
				CommonFilter commonFilter = null;
				CommonFilter chrtCommonFilter = new  CommonFilter();
				
				if( ! "true".equals(forDashboard)){
					commonFilter = (CommonFilter) httpSession.getAttribute("HseBodyVsAccRptCommonFilter");
					BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
				}
				else{
					FilterValues.getSafty(request, chrtCommonFilter);
				}
				FilterValues.getCommonFilters(request, chrtCommonFilter) ;
				
				String keyId=request.getParameter("rowid");
				
				String fromMonth = chrtCommonFilter.getFromMonth();
				String toMonth = chrtCommonFilter.getToMonth();
				
				String month1   = fromMonth +"  -  "+ toMonth ;
				String fromdate = chrtCommonFilter.getFromDate();
				String todate = chrtCommonFilter.getToDate();
				String date   = fromdate +"  -  "+ todate ;
				String title = "Body Part Versus Accident Report" + " - "+date;
				if( chrtCommonFilter.getMonwise().equals("Y")){
				title = "Body Part Versus Accident Report" + " - "+month1;
				}
				if(chrtCommonFilter.getRowTotal() == null )
					chrtCommonFilter.setRowTotal('N');
			
				List<String[]> graphData  =NewAccidenttrendreportservice.getNewBodyPartRptGraph(chrtCommonFilter,keyId);
				ChartPie pieChart =  new   ChartPie();
				
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
				System.out.println("Printing graph data");
				for(String[] row : graphData) 
				{
					System.out.println(Arrays.toString(row));
				}
				PrintWriter out = response.getWriter();
				String[] header =null;
				String[] data = null;
				
				
				header = graphData.get(1);
				if(graphData.size()>4)
					data = graphData.get(4);
				
				
				String subTitle = ""; //header[3]
				CommonFunctions.debugMsg("graphData.size() :"+graphData.size());
				CommonFunctions.debugMsg("Header");			
				for (String string : header) {
					System.out.print("  " + string);
				}
				CommonFunctions.debugMsg("data");			
			/*	for (String string : data) {
					System.out.print("  " + string);
				}
				*/
				List<Object[]> dataList = new ArrayList<Object[]>();
				for(int i=2 ;i<graphData.size()-1;i++)
				{	
					CommonFunctions.debugMsg("header[data.length-1]"+header[data.length-1]);
					data = graphData.get(i);
					if( header[data.length-1].contains("TOTAL")){
						int len = data.length -1;
						CommonFunctions.debugMsg("Double.parseDouble(data[len])"+Double.parseDouble(data[len]));					
						Object []  pieData = new Object[2];	
						CommonFunctions.debugMsg("data "+data[3]);
						pieData[ 0 ] = data[2];					
						pieData[ 1 ] = Double.parseDouble(data[len]);
						dataList.add(pieData);
					}
				}			
				ChartSeries chartSeries = new ChartSeries();			
				chartSeries.setType(ChartTypes.PIE);
				chartSeries.setData(dataList);
				chartSeriesList.add(chartSeries);
				chartSeries.setSize(200);		
				JSONObject chartObj = pieChart.drawChart(chartSeriesList, title, subTitle);		
				System.out.println("Chart Obj "+chartObj);
				UIUtils.setDashBoardIdentifier(request,chartObj);
				
				out.println(chartObj);
				out.close();
			  }
		
else if( action.equals("chart.natr")){
String forDashboard = request.getParameter("dashboard");

CommonFilter commonFilter = null;
CommonFilter chrtCommonFilter = new  CommonFilter();
if( ! "true".equals(forDashboard)){
	commonFilter = (CommonFilter) httpSession.getAttribute("HseModeWiseRptReportCommonFilter");
	BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
}
else{
	
	FilterValues.getSafty(request, chrtCommonFilter);
	 //commonFilter = populateCommonFilter(request,"HseModeWiseAccRptCommonFilter",false);
}

FilterValues.getCommonFilters(request, chrtCommonFilter) ;

String keyId=request.getParameter("rownum");

String fromMonth = chrtCommonFilter.getFromMonth();
String toMonth = chrtCommonFilter.getToMonth();

String month1   = fromMonth +"  -  "+ toMonth ;
String fromdate = chrtCommonFilter.getFromDate();
String todate = chrtCommonFilter.getToDate();
String date   = fromdate +"  -  "+ todate ;
String title = "Mode Wise Report Table " + " - "+date;
if( chrtCommonFilter.getMonwise().equals("Y")){
title = "Mode Wise  Report Table" + " - "+month1;
}
if(chrtCommonFilter.getRowTotal() == null )
	chrtCommonFilter.setRowTotal('N');

List<String[]> graphData  = NewAccidenttrendreportservice.getModeWiseRptReportgraph(chrtCommonFilter,keyId);
ChartPie pieChart =  new   ChartPie();

List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();

PrintWriter out = response.getWriter();
String[] header = graphData.get(1);
String[] data = graphData.get(4);


String subTitle = "";//header[3]

List<Object[]> dataList = new ArrayList<Object[]>();

for(int i=4 ;i<graphData.size()-1;i++)
{
	data = graphData.get(i);
	if( header[data.length-1].contains("TOTAL")){
		Object []  pieData = new Object[2];
		int len = data.length -1;
		pieData[ 0 ] = data[3];
		
		pieData[ 1 ] = Double.parseDouble(data[len]);
		dataList.add(pieData);
	}
}

ChartSeries chartSeries = new ChartSeries();

chartSeries.setType(ChartTypes.PIE);
chartSeries.setData(dataList);
chartSeriesList.add(chartSeries);
chartSeries.setSize(200);

JSONObject chartObj =pieChart.drawChart(chartSeriesList, title, subTitle);
UIUtils.setDashBoardIdentifier(request,chartObj);
	
out.println(chartObj);
out.close();
}
    else if( action.equals("NewAccidentRptTrendchart.natr")){
				processChart(request,response);
			}	
  }
  private void processChart2(HttpServletRequest request, HttpServletResponse response) throws Exception{
	HttpSession httpSession = request.getSession(false);
	
	CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("HseModeWiseRptReportCommonFilter");
	String forDashboard = request.getParameter("dashboard");
	CommonFunctions.debugMsg("forDashboard....."+forDashboard);
	if( ! "true".equals(forDashboard)){
		CommonFunctions.debugMsg("to check dash board");
		commonFilter = (CommonFilter) httpSession.getAttribute("HseModeWiseRptReportCommonFilter");
	}
	else{
		//commonFilter = new  CommonFilter();
		CommonFunctions.debugMsg("to check dash board true ");
		//FilterValues.getCommonFilters(request,commonFilter);
		//FilterValues.getOPLandKaizen(request, commonFilter);
		 commonFilter = populateCommonFilter(request,"HseModeWiseRptReportCommonFilter",false);
	}
	
	CommonFilter chrtCommonFilter = new  CommonFilter();
	String keyId=request.getParameter("rowid");
	String rowId=request.getParameter("rownum");
	
	CommonFunctions.debugMsg(" :: keyId "+keyId);	
	
	CommonFunctions.debugMsg(" :: rowId "+rowId);
	
	//BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
	
	FilterValues.getCommonFilters(request, chrtCommonFilter) ;

	
	if(chrtCommonFilter.getRowTotal() == null )
		chrtCommonFilter.setRowTotal('Y');
	
	List<String[]> trendList  = NewAccidenttrendreportservice.getModeWiseRptReportgraph(chrtCommonFilter,rowId);
	CommonFunctions.debugMsg(" trendList :: 11 "+trendList.size()+" trendList :: 22 "+trendList);
	List<String[]> cur1Grid =  transposeListArr1(trendList);
	
	JSONObject chartObj = null;
		chartObj = processLineChart1(cur1Grid,chrtCommonFilter,rowId);
	chrtCommonFilter = null;
	
	PrintWriter out = response.getWriter();
	UIUtils.dashBoardSetChartObject(request,chartObj);
	out.print(chartObj);
	out.close();
		
}
private List<String[]> transposeListArr1(List<String[]> dataList)
{
	CommonFunctions.debugMsg("INSIDE transposeListArr1"+dataList.size());
	if( dataList.size() <=0 ) return null;
	CommonFunctions.debugMsg("INSIDE transposeListArr2"+dataList.size());
	List<String[]> transposeList = new ArrayList<String[]>();		
	for( int i =0; i<dataList.size(); i++)
	{	
		String [] tRow = new String [ dataList.get(0).length];
		CommonFunctions.debugMsg("tRow [ j ] : "+tRow.length);
		for (int j=0; j<dataList.get(0).length;j++)
		{
			tRow [ j ]= dataList.get(i)[j];//.equals("0")?dataList.get(i)[j].replace("0", "-"):dataList.get(i)[j];
			
		}
		transposeList.add(tRow);
	}
	return transposeList;
}

private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
	HttpSession httpSession = request.getSession(false);
	
	CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("HseAccTrendRptCommonFilter");
	CommonFilter chrtCommonFilter = new  CommonFilter();
	String keyId=request.getParameter("rowid");
	String forDashboard = request.getParameter("dashboard");
	CommonFunctions.debugMsg("keyId....."+keyId);
	String rowId=request.getParameter("rownum");
	CommonFunctions.debugMsg(rowId + " rowid");
	String graphType = request.getParameter("chartType");
	String indexid=request.getParameter("indexid");
	CommonFunctions.debugMsg( indexid + " indexid");
	
	if( ! "true".equals(forDashboard))
	{
		CommonFunctions.debugMsg("to check dash board");
		commonFilter = (CommonFilter) httpSession.getAttribute("HseAccTrendRptCommonFilter");
	}
	else
	{
		commonFilter= populateCommonFilter(request,"HseAccTrendRptCommonFilter",false);
		CommonFunctions.debugMsg("to check dash board true ");				
	}
	
	BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
	//FilterValues.getCommonFilters(request, chrtCommonFilter) ;

	//if(chrtCommonFilter.getRowTotal() == null )
		chrtCommonFilter.setRowTotal('Y');
	
	List<String[]> trendList=null;
	     trendList=NewAccidenttrendreportservice.getNewHseAccTrendRptgraph(chrtCommonFilter,keyId);
                              
	List<String[]> cur1Grid =  transposeListArr(trendList);
	
	JSONObject chartObj = null;
	chartObj =processLineChart(cur1Grid,chrtCommonFilter,keyId,graphType,indexid);
		
	chrtCommonFilter = null;
	UIUtils.dashBoardSetChartObject(request,chartObj);
	PrintWriter out = response.getWriter();
	out.print(chartObj);
	out.close();	
}
private JSONObject processLineChart1(List<String[]> trendList,CommonFilter commonFilter,String rowId){
	
	if( trendList == null || trendList.size() <= 1  )
		return null;
	ChartOptionBean lineChart =  new ChartOptionBean();
	List<String> xAxisCategory = new ArrayList<String>();
	List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
	List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
	CommonFunctions.debugMsg(" rowId :: "+rowId);
	String title = "Mode Wise Accident Report";
	
	String [] header =  trendList.get(3);
	String [] month =  trendList.get(2);
	String [] data =  trendList.get(4);
	String prevMonth = null;
	
	String subTitle = "";
	CommonFunctions.debugMsg("subTitle"+subTitle);
	
	ChartSeries barChart = new ChartSeries();
	List<Double> barDataList = new ArrayList<Double>();
	
	CommonFunctions.debugMsg("trendList.size(): " + trendList.size());
	/*CommonFunctions.debugMsg("Header");
	for (String str : header) {
		System.out.print("  " +str );
	}
	CommonFunctions.debugMsg("month");
	for (String str : month) {
		System.out.print("  " +str );
	}
	CommonFunctions.debugMsg("data");
	for (String str : data) {
		System.out.print("  " +str );
	}  */
	if( ("TOTAL").equals(rowId)){
	for( int i = 3;i < header.length-1;i++ ){
		if( header[i].contains("CNT")){
			if(UIUtils.isValidKeyId(data[i])){
				barDataList.add(Double.parseDouble(data[i]));
			}else{
				barDataList.add(Double.parseDouble("0"));
			}
		}
		
		if( prevMonth == null || ! month[i].equals(prevMonth) ){
			xAxisCategory.add(month[i]);
		}
		prevMonth = month[i];
	}
	}else
	{
		for( int i = 4;i < header.length-1;i++ ){
			if( header[i].contains("CNT")){
				barDataList.add(Double.parseDouble(data[i]));
			}
			
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
	}
	 if( barDataList.size() > 0){
			barChart.setData(barDataList);
			barChart.setType(ChartTypes.COLUMN);             // THIS IS FOR  CNT
			if( ("TOTAL").equals(rowId))
				barChart.setName("Total");
			else
			    barChart.setName(data[3]);
			chartSeriesList.add(barChart);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("No of Accidents");
			chartYAxis.add(yAxis);
			}
	ChartXAxis xaxis = new ChartXAxis();
	if(commonFilter.getMonwise().equals("Y"))
		xaxis.getTitle().setText("Month");
	else
		xaxis.getTitle().setText("Date");
	lineChart.getxAxis().setTitle(xaxis.getTitle());
	return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
}
private JSONObject getTableModelEmployeeWiseIncident(List<String[]> headers) {
	JqGridTableModel jqGridTableModel = new JqGridTableModel();
	
	String[] colHeader = headers.get(2);
	String[] colHeader1 = headers.get(3);
	String[] emptyrow = new String[colHeader.length];
	emptyrow[0] = "";
	emptyrow[1] = "";
	CommonFunctions.debugMsg("test..."+colHeader.length);
	System.out.println("col header lenght"+colHeader.length);
	for (int i =2; i <colHeader.length; i++) {
		emptyrow[i] = "";
	}
	jqGridTableModel.getRowHeaders().add(emptyrow);
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.getRowHeaders().add(colHeader1);
	jqGridTableModel.setTableButton(true);
	jqGridTableModel.setRowNumbers(true);
	//colHeader1[3] = caption;  
	for (int i = 0; i <= colHeader.length-1; i++) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
		jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
		jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
		jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

		jqGridColModel.setWidth(200);
		jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);

		if (i == 0 || i == 1 || i==2) {
			jqGridColModel.setHidden(true);
			jqGridColModel.setKey(true);
		} else if (i > 3) {
			// jqGridColModel.setHidden(false);
			jqGridColModel.setKey(true);
			jqGridColModel.setAlign("right");
			jqGridColModel.setWidth(74);
		}

		else if (i == 16) {
			CommonFunctions.debugMsg("Length of col:" + colHeader.length);
			jqGridColModel.setHidden(true);
			jqGridColModel.setKey(true);
		}

		jqGridTableModel.getColModel().add(jqGridColModel);
		
		//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
	}
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "67%%");
	return tableModel;
}
private JSONObject processLineChart(List<String[]> trendList,CommonFilter commonFilter,String rowId,String graphType,String indexid){
	
	if( trendList == null || trendList.size() <= 1  )
		return null;
	ChartOptionBean lineChart =  new ChartOptionBean();
	List<String> xAxisCategory = new ArrayList<String>();
	List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
	List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();

	
	String title = " Accident Trend Report - " + indexid;
	
	String [] header =  trendList.get(2);
	String [] month =  trendList.get(1);
	String [] data =  trendList.get(trendList.size()-1);
		
	String [] cummulative=null;
	 
	String prevMonth = null;
	
	String subTitle = "";
	
	ChartSeries barChartMin = new ChartSeries();
	List<Double> barMinDataList = new ArrayList<Double>();
	
	ChartSeries barChartMaj = new ChartSeries();
	List<Double> barMajDataList = new ArrayList<Double>();
	
	ChartSeries barChartNma = new ChartSeries();
	List<Double> barNmaDataList = new ArrayList<Double>();
	
	List<Double> dataLine = new ArrayList<Double>();//declaring variable for line chart
	
	List<Double> lineYChartList = new ArrayList<Double>();//declaring variable for bar chart
		if(!UIUtils.isValidKeyId(commonFilter.getTotal()))
		commonFilter.setTotal("N");
	for( int i = 4;i < header.length;i++ ){
		if(header[i].equalsIgnoreCase(indexid) ){
			barMinDataList.add(Double.parseDouble(data[i]));
		}
		
		
		if( prevMonth == null || ! month[i].equals(prevMonth) ){
			xAxisCategory.add(month[i]);
		}
		prevMonth = month[i];
	}
	
	String chartType = null;
	if("BAR".equals(graphType))		
		chartType = ChartTypes.COLUMN;
	else
		chartType = ChartTypes.LINE;
	
		if(barMinDataList.size() > 0 ){
			barChartMin.setData(barMinDataList);
			barChartMin.setType(chartType);					
			barChartMin.setName(indexid);
			chartSeriesList.add(barChartMin);					
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Accident Trend");
			chartYAxis.add(yAxis);
		}
	ChartXAxis xaxis = new ChartXAxis();
	if(commonFilter.getMonwise().equals("Y"))
		xaxis.getTitle().setText("Month");
	else
		xaxis.getTitle().setText("Date");
	
	lineChart.getxAxis().setTitle(xaxis.getTitle());
	return lineChart.drawChart(xAxisCategory, chartSeriesList, "Accident Trend Report ", indexid, chartYAxis);
}
private List<String[]> transposeListArr(List<String[]> dataList)
{
	if( dataList.size() <=0 ) return null;
	List<String[]> transposeList = new ArrayList<String[]>();		
	for( int i =0; i<dataList.size(); i++)
	{	
		String [] tRow = new String [ dataList.get(0).length];
		
		for (int j=0; j<dataList.get(0).length;j++)
		{
			tRow [ j ]= dataList.get(i)[j];//.equals("0")?dataList.get(i)[j].replace("0", "-"):dataList.get(i)[j];
			
		}
		transposeList.add(tRow);
	}
	return transposeList;
}
private JSONObject GetTableModel_ACCTREND(List<String[]> headers,CommonFilter commonFilter)
{
	JqGridTableModel jqGridTableModel = new JqGridTableModel();
	 
	String[] colHeader = headers.get(0);
	String[] colHeader1 = headers.get(1);
	
	
	String[] emptyrow = new String[colHeader.length]; 	
	

	emptyrow [0] ="";
	emptyrow [1] ="";
			
	for( int i = 2; i < colHeader.length;i++ ){			
		emptyrow [i] = "";
	}		
	
	jqGridTableModel.getRowHeaders().add(emptyrow);		
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.getRowHeaders().add(colHeader1);
	
	jqGridTableModel.setSortable(false);
	jqGridTableModel.setTableButton(true);
	jqGridTableModel.setRowNumbers(true);		
	jqGridTableModel.setTableHeight(300);
//	jqGridTableModel.getColModel().add(getColModel("keyid", 50,"left",true,false,true));
	 
	  		for(int i =0; i < colHeader.length; i++)
	{
		//CommonFunctions.debugMsg("Length : "+colHeader.length);
		JqGridColModel jqGridColModel = new JqGridColModel();
	
		jqGridColModel.setWidth(100);				
		//jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);
		
		if(i==0 || i == 1 || i == 2)
		{
			jqGridColModel.setHidden(true);
			if(i==0)
				jqGridColModel.setKey(true);
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
		
		}
		if(i == 3)
		{
			jqGridColModel.setWidth(320);
			jqGridColModel.setAlign("left");
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
		}
		if(i>3)
		{
			jqGridColModel.setWidth(125);
			
			jqGridColModel.setAlign("right");
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+i);
		}
		
		
		jqGridTableModel.getColModel().add(jqGridColModel);
	}
	 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 return tableModel;
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
		commonFilter = 	FilterValues.getSafty(request, commonFilter);
		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  CommonFunctions.debugMsg("CommonFunctions.getFirstDateofMonth(-5).substring(3,11)"+CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");				 
	 	  }
		
		CommonFunctions.debugMsg("SKIP"+commonFilter.getChkSkipLine());
		commonFilter.setViewClick('Y');
		
		httpSession.removeAttribute(beanIdentifier);
		httpSession.setAttribute(beanIdentifier, commonFilter);
	}
	CommonFunctions.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
	return commonFilter;
}
}
