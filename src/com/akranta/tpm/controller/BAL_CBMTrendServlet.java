package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_CBMTrendService;
//import com.akranta.tpm.service.BAL_WorkOrderOverViewService;
//import com.akranta.tpm.service.impl.BAL_BudgetPlanAndActualServiceImpl;
import com.akranta.tpm.service.impl.BAL_CBMTrendServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_CBMTrendServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BAL_CBMTrendService cBMTrendService;
	public BAL_CBMTrendServlet(){
	
		/*try{
			cBMTrendService = new CBMTrendServiceImpl();
		}
		catch(Exception e){
			e.printStackTrace();			
		}
		*/		
	}
	
private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew,boolean drilFlag){
		
		
		HttpSession httpSession = request.getSession(false);
		
		System.out.println("createNew...."+drilFlag);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			//if(drilFlag )
				FilterValues.getCommonFilters(request, commonFilter);
				FilterValues.getPMRelated(request, commonFilter);
		}	
		else{
			
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
			commonFilter = 	FilterValues.getPMRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try{
			process(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try{
			process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if( ! UIUtils.checkUserSession( request,response))
			return; 
		
		String dispatchUrl =null;		
		HttpSession h = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		
		try {
			cBMTrendService = (BAL_CBMTrendServiceImpl)UIUtils.getServiceObject(request,"BAL_CBMTrendServiceImpl");
			cBMTrendService.BAL_CBMTrendServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
			
		} catch (ServiceObjectCreationException e) {
			com.akranta.tpm.utils.CommonFunctions.debugMsg(e);
		}
		 
		 if( action.equals("filterXmlCBMTrend_input.cbmTrend")){
				response.setContentType("xml"); 
				System.out.println("action "+ action); 			
				UIUtils.forwardRequest(request, response, "/tiles/xml/WorkOrderOV.xml");
				System.out.println("Exit xml");
			 }	
			
			else if(action.equals("CBMTrend_input.cbmTrend")) 
			{
				UIUtils.forwardRequest(request, response, "/pages/Reports/WorkOrderOverView.jsp"); 
				
			}
			else if(action.equals("CBMTrend_getCol.cbmTrend"))
			{	
				
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				//populateCommonFilter(request,"CBMTrendCommonFilter",true);		
				
				CommonFilter commonFilter = new CommonFilter();
				//commonFilter =  new CommonFilter();				
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  CommonFunctions.debugMsg("CommonFunctions.getFirstDateofMonth(-5).substring(3,11)"+CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");				 
			 	  }
							
				CommonFunctions.debugMsg("commonFilter.getMonwise()"+commonFilter.getMonwise());				
				commonFilter.setViewClick('Y');
				commonFilter.setIsGetCol("Y");
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", "MaintainActivityWiseRpt"));
				List<String[]> maintActivity  = cBMTrendService.getAllCbmTrend(commonFilter);
				for (String[] arr : maintActivity) {
				    CommonFunctions.debugMsg("row: " + Arrays.toString(arr));
				}
				JSONObject jsonObject = getTableModel(maintActivity);
				jsonObject.set("tableHeight", "84%%");
				jsonObject.set("tableWidth", "108%%");
				httpSession.removeAttribute("CBMTrendColData");
				httpSession.setAttribute("CBMTrendColData", jsonObject);
				httpSession.removeAttribute("CBMTrendCommonFilter");
				httpSession.setAttribute("CBMTrendCommonFilter", commonFilter);
				out.println(jsonObject);
				out.close();
				
			
			}
			else if(action.equals("CBMTrend_getData.cbmTrend"))
			{
				try
				{	
					PrintWriter out = response.getWriter();
					CommonFilter commonFilter = new CommonFilter();
					System.out.println("Inside Maint Activity Servlet action");
					HttpSession httpSession = request.getSession(false);
				
					commonFilter = (CommonFilter) httpSession.getAttribute("CBMTrendCommonFilter");
					commonFilter.setIsGetCol("N");
					List<String[]> maintActivity  = cBMTrendService.getAllCbmTrend(commonFilter);
					JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(maintActivity,request,3,0); 
	  				out.println(maintActivityData);
	  				
	  				httpSession.removeAttribute("CBMTrendCommonFilter");
	  			 	httpSession.setAttribute("CBMTrendCommonFilter", commonFilter);
					
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			else if( action.equals("CBMTrend_getExcel.cbmTrend"))
			{			
				
				
			HttpSession httpSession = request.getSession(true);
			//CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false);
			CommonFilter commonFilter = populateCommonFilter(request,"CBMTrendCommonFilter",false,false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = (JSONObject)httpSession.getAttribute("CBMTrendColData");
			
			
			tableModel.put("title", "CBM Reading Trend ");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = cBMTrendService.CBMExcelReport(commonFilter,tableModel,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "CBmReadingTrend", format);
			}
			else if(action.equals("chart.cbmTrend")){
				CommonFunctions.debugMsg("inside the chart action" +action);
				String jobType=request.getParameter("jobType");
				String graphType=request.getParameter("graphType");
				String rowid = request.getParameter("rowId");
				CommonFilter chrtCommonFilter = new  CommonFilter();
				
	CommonFilter commonFilter = populateCommonFilter(request,"CBMTrendCommonFilter",false);
	FilterValues.getBDRelated(request, chrtCommonFilter);//(request, chrtCommonFilter);


				
				FilterValues.getCommonFilters(request, chrtCommonFilter) ;
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				
				String month1   = fromMonth +" - "+ toMonth ;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String date   = fromdate +"  -  "+ todate ;
				String title = null;
				
				/*if(rowid.equals("1")){
					title="CBM Reading Trend ";
				}
				else if(rowid.equals("2")){
					title="PM Activity Summary Trend Week 2 of "+month1;
				}
				else if(rowid.equals("3")){
					title="PM Activity Summary Trend Week 3 of "+month1;
				}else if(rowid.equals("4")){
					title="PM Activity Summary Trend Week 4 of "+month1;
				}
				else {
					title="CBM Reading Trend ";
				}*/
				
				
				title="CBM Reading Trend ";
	String forDashboard = request.getParameter("dashboard");

	if(!UIUtils.isValidKeyId(rowid))

	FilterValues.getCommonFilters(request, chrtCommonFilter) ;
	List<String[]> graphData  =  cBMTrendService.getAllCbmTrend(commonFilter);

	//List<String[]> pmbdList  = pMVsBDService.getBDVSPMVSABNGridReport(commonFilter);
	JSONObject chartObj = null;
	String chartType = request.getParameter("chType");
	if( chartType == null){
		chartObj = processActivityLineChart(graphData,commonFilter,rowid,title,jobType,graphType);
		String dashboardIdnt = request.getParameter("dashboardIdent");
		if( UIUtils.isValidKeyId(dashboardIdnt));
		//	chartObj.set("dashboardIdent", dashboardIdnt);
	}
	PrintWriter out = response.getWriter();
	out.print(chartObj);
	out.close();
		
	}
		
		 
			}
	private JSONObject getTableModel(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);	
		String [] colHeader1 = headers.get(1);
		String [] colHeader0 = new String[ colHeader.length];
		
		
		jqGridTableModel.setRowNumbers(true);
		CommonFunctions.debugMsg("colHeader....."+ headers.get(0));
		for(int i=0; i < colHeader.length; i++)
		{
			colHeader0[i] = "";
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridTableModel.setTableButton(true);		
				
			jqGridColModel.setWidth(100);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0 )
			{
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}
			 else if (i == 1) {
			        jqGridColModel.setWidth(250);
			        jqGridColModel.setAlign("left");
			    }

			 if(i==2)	
				jqGridColModel.setWidth(250);
			 if(i==3)
				 jqGridColModel.setWidth(150);
			else if(i>3)
			{
				jqGridColModel.setWidth(130);				
				jqGridColModel.setAlign("right");
			}
			else
			{
			
		
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
			jqGridTableModel.getRowHeaders().add(colHeader0);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.getRowHeaders().add(colHeader1);
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);		 
		 
		 tableModel.set("tableHeight", "80%%");
		 CommonFunctions.debugMsg("tableModel..."+tableModel);
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
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  CommonFunctions.debugMsg("CommonFunctions.getFirstDateofMonth(-5).substring(3,11)"+CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");				 
			 	  }
							
				CommonFunctions.debugMsg("commonFilter.getMonwise()"+commonFilter.getMonwise());				
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			System.out.println( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
			return commonFilter;
		}	
	 private JSONObject processActivityLineChart(List<String[]> pmbdList,CommonFilter commonFilter,String rowid, String title,String jobType,String graphType){
		 CommonFunctions.debugMsg("222222222222adfsrfa"+rowid);
		 if( pmbdList == null || pmbdList.size() <= 1  )
		 	return null;

		 ChartOptionBean lineChart =  new ChartOptionBean();
		 List<String> xAxisCategory = new ArrayList<String>();
		 List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		 List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();

		 //String title = "Preventive Maintenance Vs Breakdown Vs Abnormality ";

		 int rowno = Integer.parseInt(rowid);
		 String [] header =  pmbdList.get(2);
		 String [] header2 =  pmbdList.get(3);

		 String [] month =  pmbdList.get(1);
		 String [] data =  pmbdList.get(rowno+3);
		 CommonFunctions.debugMsg("rowId................"+rowno);
		 CommonFunctions.debugMsg("header 0................"+header[0]);
		 CommonFunctions.debugMsg("header 1................"+header[1]);
		 CommonFunctions.debugMsg("header 2................"+header[2]);
		 CommonFunctions.debugMsg("header 2................"+month[1]);
		 String prevMonth = null;

		 String subTitle = ""; 
		 ChartSeries timeSeriescbmact = new ChartSeries();
		 ChartSeries timeSeriescbm = new ChartSeries();
		 List<Double> planCbmData = new ArrayList<Double>();
		 List<Double> actualCbmData = new ArrayList<Double>();

		 ChartSeries timeSeriestbmact = new ChartSeries();
		 ChartSeries timeSeriestbm = new ChartSeries();
		 List<Double> planTbmData = new ArrayList<Double>();
		 List<Double> actualTbmData = new ArrayList<Double>();

		 ChartSeries timeSeriesprmact = new ChartSeries();
		 ChartSeries timeSeriesprmactd = new ChartSeries();

		 ChartSeries timeSeriesprm = new ChartSeries();
		 List<Double> planPrmData = new ArrayList<Double>();
		 List<Double> actuaDPrmData = new ArrayList<Double>();
		 List<Double> actualPrmData = new ArrayList<Double>();



		 if(graphType.equals("bar")){
		 
		 for( int i = 4;i < header.length-1;i++ ){  
		 	CommonFunctions.debugMsg("header in side if " +header[i]);
		 	
		 		if(header[i].contains("LOWERREADING") ){
		 			//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
		 	
		 			planPrmData.add(Double.parseDouble(data[i]));
		 		}
		 		else if(header[i].contains("UPPERREADING") ){
		 		
		 			actualPrmData.add(Double.parseDouble(data[i]));
		 			}
		 		else if(header[i].contains("DESIRABLEREADING") ){
			 		
		 			actuaDPrmData.add(Double.parseDouble(data[i]));
		 			}
		 	
		 		
		 				
		 	if( prevMonth == null || ! month[i].equals(prevMonth) ){
		 		xAxisCategory.add(month[i]);
		 		
		 		}	prevMonth = month[i];
		 	 }
		    
		 	
		 
		
		 
		 
		 if(planCbmData.size() > 0 ){
		 	timeSeriescbm.setData(planCbmData);
		 	timeSeriescbm.setType(ChartTypes.COLUMN);
		 	timeSeriescbm.setName("LOWERREADING");
		 	chartSeriesList.add(timeSeriescbm);
		 	
		 	
		 	ChartYAxis yAxis = new ChartYAxis(); 
		 	yAxis.setMin(0);
		 	//yAxis.getTitle().setText("Completed Activity");
		 	chartYAxis.add(yAxis);
		 }
		 if(actualCbmData.size() > 0 ){
		 	timeSeriescbmact.setData(actualCbmData);
		 	timeSeriescbmact.setType(ChartTypes.COLUMN);
		 	timeSeriescbmact.setName("UPPERREADING");
		 	chartSeriesList.add(timeSeriescbmact);
		 	
		 	
		 	ChartYAxis yAxis = new ChartYAxis(); 
		 	yAxis.setMin(0);
		 	yAxis.getTitle().setText("Completed Activity");
		 	chartYAxis.add(yAxis);
		 }



		 if( planPrmData.size() > 0){
		 	timeSeriesprm.setData(planPrmData);
		 	timeSeriesprm.setType(ChartTypes.COLUMN);
		 	timeSeriesprm.setName("LOWERREADING");

		 	chartSeriesList.add(timeSeriesprm);
		 	ChartYAxis yAxis = new ChartYAxis(); 
		 	yAxis.setMin(0);
		 	//yAxis.getTitle().setText("Actual");
		 	chartYAxis.add(yAxis);

		 }

		 if( actualPrmData.size() > 0){
		 	timeSeriesprmact.setData(actualPrmData);
		 	timeSeriesprmact.setType(ChartTypes.COLUMN);
		 	timeSeriesprmact.setName("UPPERREADING");

		 	chartSeriesList.add(timeSeriesprmact);
		 	ChartYAxis yAxis = new ChartYAxis(); 
		 	yAxis.setMin(0);
		 	//yAxis.getTitle().setText("Actual"); DESIRABLEREADING
		 	chartYAxis.add(yAxis);

		 }
		 if( actuaDPrmData.size() > 0){
			 	timeSeriesprmactd.setData(actualPrmData);
			 	timeSeriesprmactd.setType(ChartTypes.COLUMN);
			 	timeSeriesprmactd.setName("DESIRABLEREADING");

			 	chartSeriesList.add(timeSeriesprmact);
			 	ChartYAxis yAxis = new ChartYAxis(); 
			 	yAxis.setMin(0);
			 	//yAxis.getTitle().setText("Actual"); 
			 	chartYAxis.add(yAxis);

			 }
		 

		 
		 else{
	 /*
		 	
		 		for( int i = 1;i < header.length-1;i++ ){  
		 			CommonFunctions.debugMsg("header in side if " +header[i]);
		 			if(header[i].contains("PRM") ){
		 				if(header2[i].contains("Percentage") ){
		 			
		 					planPrmData.add(Double.parseDouble(data[i]));
		 				}
		 				
		 			}
		 				if(header[i].contains("CBM") ){
		 					if(header2[i].contains("Percentage") ){
		 						//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
		 						if(data[i].isEmpty()){
		 							planCbmData.add(Double.parseDouble("0"));

		 						}
		 						else
		 						planCbmData.add(Double.parseDouble(data[i]));
		 						//planCbmData.add(Double.parseDouble(data[i]));
		 					}
		 					
		 				}
		 					if(header[i].contains("TBM") ){
		 						if(header2[i].contains("Percentage") ){
		 							//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
		 							if(data[i].isEmpty()){
		 								planCbmData.add(Double.parseDouble("0"));

		 							}
		 							else
		 							planCbmData.add(Double.parseDouble(data[i]));
		 						//}
		 							//planTbmData.add(Double.parseDouble(data[i]));
		 						}
		 					}
		 			if( prevMonth == null || ! month[i].equals(prevMonth) ){
		 				xAxisCategory.add(month[i]);
		 				
		 				}	prevMonth = month[i];
		 			 }
		 		   }
		 			
		 		*/
		 		
		 		
		 		if(planCbmData.size() > 0 ){
		 			timeSeriescbm.setData(planCbmData);
		 			timeSeriescbm.setType(ChartTypes.SPLINE);
		 			timeSeriescbm.setName("LOWERREADING");
		 			chartSeriesList.add(timeSeriescbm);
		 			
		 			
		 			ChartYAxis yAxis = new ChartYAxis(); 
		 			yAxis.setMin(0);
		 			//yAxis.getTitle().setText("Completed Activity");
		 			chartYAxis.add(yAxis);
		 		}


		 		if( planPrmData.size() > 0){
		 			timeSeriesprm.setData(planPrmData);
		 			timeSeriesprm.setType(ChartTypes.SPLINE);
		 			timeSeriesprm.setName("UPPERREADING");
		 			chartSeriesList.add(timeSeriesprm);
		 			ChartYAxis yAxis = new ChartYAxis(); 
		 			yAxis.setMin(0);
		 			//yAxis.getTitle().setText("Actual");
		 			chartYAxis.add(yAxis);

		 		}

		 		

		 		if( planTbmData.size() > 0){
		 			timeSeriestbm.setData(planTbmData);
		 			timeSeriestbm.setType(ChartTypes.SPLINE);
		 			timeSeriestbm.setName("DESIRABLEREADING");

		 			chartSeriesList.add(timeSeriestbm);
		 			ChartYAxis yAxis = new ChartYAxis(); 
		 			yAxis.setMin(0);
		 			//yAxis.getTitle().setText("Actual");
		 			chartYAxis.add(yAxis);

		 		}

		 		

		 	
		 	
		 }
		 	

		 ChartXAxis xaxis = new ChartXAxis();
		 if(commonFilter.getMonwise().equals("Y"))
		 	xaxis.getTitle().setText("Month");
		 else
		 	xaxis.getTitle().setText("Date");
		 lineChart.getxAxis().setTitle(xaxis.getTitle());
		 }
		 return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);

		 }
		
		 //-------------------------
		 private JSONObject processplanvsactualActivityLineChart(List<String[]> pmbdList,CommonFilter commonFilter,String rowid, String title,String graphType){
		 CommonFunctions.debugMsg("222222222222adfsrfa"+rowid);
		 if( pmbdList == null || pmbdList.size() <= 1  )
		 	return null;

		 ChartOptionBean lineChart =  new ChartOptionBean();
		 List<String> xAxisCategory = new ArrayList<String>();
		 List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		 List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();

		 //String title = "Preventive Maintenance Vs Breakdown Vs Abnormality ";

		 int rowno = Integer.parseInt(rowid);
		 String [] header =  pmbdList.get(1);
		 String [] header2 =  pmbdList.get(2);

		 String [] month =  pmbdList.get(1);
		 String [] data =  pmbdList.get(rowno+2);
		 CommonFunctions.debugMsg("rowId................"+rowno+3);
		 CommonFunctions.debugMsg("header 0................"+header[0]);
		 CommonFunctions.debugMsg("header 1................"+header[1]);
		 CommonFunctions.debugMsg("header 2................"+header[2]);
		 CommonFunctions.debugMsg("header 2................"+month[1]);
		 String prevMonth = null;

		 String subTitle = ""; 
		 ChartSeries timeSeriesact = new ChartSeries();
		 ChartSeries timeSeriespln = new ChartSeries();
		 List<Double> planData = new ArrayList<Double>();
		 List<Double> actualData = new ArrayList<Double>();



		 if(graphType.equals("bar")){

		 for( int i = 1;i < header.length-1;i++ ){  
		 	CommonFunctions.debugMsg("header in side if " +header[i]);

		 		if(header2[i].contains("Plan") ){
		 			//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
		 	
		 			planData.add(Double.parseDouble(data[i]));
		 		}
		 		else if(header2[i].contains("Actual") ){
		 		
		 			actualData.add(Double.parseDouble(data[i]));
		 			}
		 	
		 		
		 	if( prevMonth == null || ! month[i].equals(prevMonth) ){
		 		xAxisCategory.add(month[i]);
		 		
		 		}	prevMonth = month[i];
		 	 }
		   

		 if(planData.size() > 0 ){
		 	timeSeriespln.setData(planData);
		 	timeSeriespln.setType(ChartTypes.COLUMN);
		 	timeSeriespln.setName("Plan");
		 	chartSeriesList.add(timeSeriespln);
		 	
		 	
		 	ChartYAxis yAxis = new ChartYAxis(); 
		 	yAxis.setMin(0);
		 	//yAxis.getTitle().setText("Completed Activity");
		 	chartYAxis.add(yAxis);
		 }
		 if(actualData.size() > 0 ){
		 	timeSeriesact.setData(actualData);
		 	timeSeriesact.setType(ChartTypes.COLUMN);
		 	timeSeriesact.setName("Actual");
		 	chartSeriesList.add(timeSeriesact);
		 }

		 }
		 else{
		 	
		 	
		 		for( int i = 1;i < header.length-1;i++ ){  
		 			CommonFunctions.debugMsg("header in side if " +header[i]);
		 			
		 				if(header2[i].contains("Percentage") ){
		 			
		 					planData.add(Double.parseDouble(data[i]));
		 				}
		 				if( prevMonth == null || ! month[i].equals(prevMonth) ){
		 					xAxisCategory.add(month[i]);
		 					
		 					}	prevMonth = month[i];
		 			
		 		}
		 		
		 		if(planData.size() > 0 ){
		 			timeSeriespln.setData(planData);
		 			timeSeriespln.setType(ChartTypes.SPLINE);
		 			timeSeriespln.setName("Percentage");
		 			chartSeriesList.add(timeSeriespln);
		 			
		 			
		 			ChartYAxis yAxis = new ChartYAxis(); 
		 			yAxis.setMin(0);
		 			//yAxis.getTitle().setText("Completed Activity");
		 			chartYAxis.add(yAxis);
		 		}


		 	
		 	
		 }
		 	

		 ChartXAxis xaxis = new ChartXAxis();
		 if(commonFilter.getMonwise().equals("Y"))
		 	xaxis.getTitle().setText("Month");
		 else
		 	xaxis.getTitle().setText("Date");
		 lineChart.getxAxis().setTitle(xaxis.getTitle());
		 return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);

		 }

}

