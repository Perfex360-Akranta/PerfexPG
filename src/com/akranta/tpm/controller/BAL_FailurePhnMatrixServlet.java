
package com.akranta.tpm.controller;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.BAL_FailurePhnMatrixService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_FailurePhnMatrixServiceImpl;
//import com.akranta.tpm.service.impl.BAL_GenTlFactorymstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_FailurePhnMatrixServlet extends HttpServlet {	
	/**
	 * Created By:N.Arun
	 * 
	 * Graph Added By :Siddharth.A
	 */
	private static final long serialVersionUID = 1L;
	BAL_FailurePhnMatrixService failurePhnMatrixService ; 
	public BAL_FailurePhnMatrixServlet(){
	/*	try {
			failurePhnMatrixService = new FailurePhnMatrixServiceImpl();
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
	
		//	PrintWriter out = response.getWriter();
			String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		
		try {
			failurePhnMatrixService = (BAL_FailurePhnMatrixServiceImpl)UIUtils.getServiceObject(request,"BAL_FailurePhnMatrixServiceImpl");
			failurePhnMatrixService.BAL_FailurePhnMatrixServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}

		if( action.equals("filterXmlFailurePhnMatrix_input.fpmRpt")){
			 response.setContentType("xml"); 
			System.out.println("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/FailureMatrix.xml") ;
		 }
			
		else if(action.equals("FailurePhnMatrix_input.fpmRpt")) 
		{
			UIUtils.forwardRequest(request, response,"/pages/Reports/BAL_FailurePhnMatrix.jsp"); 
		
		}
		else if(action.equals("FailurePhnMatrix_getCol.fpmRpt"))
		{
			  PrintWriter out = response.getWriter();
			  CommonFilter commonFilter = populateCommonFilter(request,"ppMatrixCommonFilter",true);	
			  
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
		
			 commonFilter.setIsGetCol("Y");
			  List<String[]> failurePhnMatrixList  = failurePhnMatrixService.getAllFailurePhnMatrix(commonFilter);
			  for(String[] array:failurePhnMatrixList) 
			  {
				  CommonFunctions.debugMsg("failurePhnMatrixList   "+Arrays.toString(array));
			  }
			  response.setContentType("text/html");
			  
			  JSONObject failurePhnMatrixData= UIUtils.convertToJqGridTableObject(failurePhnMatrixList,request,0,0,commonFilter.getTotalRecordCnt());
				CommonFunctions.debugMsg("accidentInvestList   "+failurePhnMatrixList.size());
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);
				
				gridColModel.setHeaderNum(2);
				
				String [] colHeader1 = failurePhnMatrixList.get(1);
				String [] colHeader2 = failurePhnMatrixList.get(2);	
				String [] colHeaderCond = failurePhnMatrixList.get(0);
				
				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader1);
				headers.add(colHeader2);
				
				JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				colModel.put("data", failurePhnMatrixData);
				CommonFunctions.debugMsg("colModel   "+colModel);
			  //JSONObject colModel = getTableModel(failurePhnMatrixList);
			  colModel.set("tableWidth", "106%%");
			  colModel.set("tableHeight", "81%%");
			  httpSession.removeAttribute("ppmatrxReportColModel");
				httpSession.setAttribute("ppmatrxReportColModel", colModel);
				 
			  out.println(colModel);
		}
		
		else if( action.equals("FailurePhnMatrix_getData.fpmRpt") )
		{
			try
			{
				 PrintWriter out = response.getWriter(); 
				 CommonFilter commonFilter = populateCommonFilter(request,"ppMatrixCommonFilter",false);
				 JSONObject jsonObject = new JSONObject();
				 commonFilter.setIsGetCol("N");
				 List<String[]> failurePhnMatrixList  = failurePhnMatrixService.getAllFailurePhnMatrix(commonFilter);
				 jsonObject = UIUtils.convertToJqGridTableObject(failurePhnMatrixList,request,3,0); 
				 out.println(jsonObject);
	         
  			 	 httpSession.removeAttribute("ppMatrixCommonFilter");
  			 	 httpSession.setAttribute("ppMatrixCommonFilter", commonFilter);
		
		    }catch(Exception e)
			{
				System.out.println("error " +e.getMessage());
			}
		}
		else if( action.equals("FailurePhnMatrix_getExcel.fpmRpt")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"ppMatrixCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			String date   = fromMonth +"  -  "+ toMonth ;
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("ppmatrxReportColModel");
			
			tblJSONObj.put("title", "PP Matrix Report"+"  -  "+date);
			tblJSONObj.put("groupBy",false);
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = failurePhnMatrixService.ppmatrixExportExcel(commonFilter,tblJSONObj,format);
			//commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "PPMatrixReport", format);
			
		}
		
		else if( action.equals("chart.fpmRpt"))
		{
			processChart(request,response);
		}
		
	}
	private JSONObject getTableModel(List<String[]> headers)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader0 = headers.get(0);
		String [] colHeader = headers.get(1);
		String [] colHeader1 = headers.get(2);
		//String[] emptyrow = new String[colHeader.length]; 
		
		
		jqGridTableModel.setTableButton(true);
		//jqGridTableModel.setTableHeight(270);
		
		//jqGridTableModel.setGroupSummary(true);
		jqGridTableModel.setRowNumbers(true);
		//jqGridTableModel.setGroupBy(true);
		//jqGridTableModel.setGroupByField(" ");
		
		/*List<JqGridColModel>jqGridColModelList =new ArrayList<JqGridColModel>();
		JqGridColModel jqGridColModel =getColModel("keyid",50,"left");
		jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);
		jqGridColModel = getColModel("Description",250,"left");
		jqGridColModelList.add(jqGridColModel);*/
		/*for(int i =1; i < colHeader0.length; i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader0[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader0[i].replaceAll(" ", "")+ i);
		}*/
		String headerSql = "'SELECT ";
		for(int i =0; i < colHeader.length; i++)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			colHeader0 [i] ="";
			
			//jqGridColModel.setIndex(colHeader0[i].replaceAll(" ", "")+ i);
			//jqGridColModel.setName(colHeader0[i].replaceAll(" ", "")+ i);
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "")+ i);
			//	System.out.println("jsonObject                   "+colHeader1[i]);
			jqGridColModel.setWidth( 100);				
			jqGridColModel.setAlign("center");
			jqGridColModel.setSummaryType("sum");
			jqGridColModel.setSummaryTpl("<b><font >  {0} </font> </b>");
			jqGridColModel.setEditable(false);
			if(!(colHeader1[i].equals("Occurence"))&& !(colHeader1[i].equals("Time")))
				
			{
			if(i==0 )
			{
				jqGridColModel.setAlign("left");
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(300);	
				jqGridColModel.setKey(false);
			}
			if(i==1 )
			{
				//jqGridColModel.setHidden(true);
				//jqGridColModel.setKey(true);
				jqGridColModel.setWidth(250);	
				jqGridColModel.setAlign("left");
				jqGridColModel.setSummaryType("count");
				jqGridColModel.setSummaryTpl("<b><font >Total </font> </b>");
				
			}			
		 	else
			{ 	
				jqGridColModel.setSummaryType("sum");
				jqGridColModel.setSummaryTpl("<b><font color=blue>{0} </font> </b>");
			}
			if(i==2)
			{
				jqGridColModel.setWidth(200);	
				jqGridColModel.setAlign("left");
				jqGridColModel.setSummaryType("sum");
				jqGridColModel.setSummaryTpl("<b><font > </font> </b>");
			
			}
			
			if(i==3){
				jqGridColModel.setWidth(200);
				//jqGridColModel.setName("Physical Phenomena");
				jqGridColModel.setAlign("left");
				jqGridColModel.setSummaryType("sum");
				jqGridColModel.setSummaryTpl("<b><font > </font> </b>");
			}
			
			if(i==4){
				jqGridColModel.setWidth(250);
			  	jqGridColModel.setAlign("left");
			  	jqGridColModel.setSummaryType("sum");
				jqGridColModel.setSummaryTpl("<b><font > </font> </b>");
			}
			if(i==5){
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
				jqGridColModel.setSummaryType("sum");
				jqGridColModel.setSummaryTpl("<b><font > </font> </b>");
			}
			if(i==6){
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
				jqGridColModel.setSummaryType("sum");
				jqGridColModel.setSummaryTpl("<b><font > </font> </b>");
			}
			
			if(i==7){
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
				jqGridColModel.setSummaryType("sum");
				jqGridColModel.setSummaryTpl("<b><font ></font> </b>");
			}
			if(i==8){
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
				jqGridColModel.setSummaryType("sum");
				jqGridColModel.setSummaryTpl("<b><font > </font> </b>");
			}
		 	if(i==9){
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
				jqGridColModel.setSummaryType("sum");
				jqGridColModel.setSummaryTpl("<b><font > </font> </b>");
			} 
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL '";
		CommonFunctions.debugMsg("headerSql.....123..."+headerSql);
		jqGridTableModel.getRowHeaders().add(colHeader0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
	private JqGridColModel getColModel (String colIndex, int width,String allign)
	{
		 //System.out.println("row[3]colIndex " +colIndex);
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
				
		jqGridColModel.setWidth( width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		return jqGridColModel;
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
	
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("ppMatrixCommonFilter");
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		CommonFunctions.debugMsg("In side te procss chart method");
		FilterValues.getCommonFilters(request, chartCommonFilter) ;
		chartCommonFilter.setRowTotal('Y');
		
		List<String[]> failurePhnMatrixList  = failurePhnMatrixService.getAllFailurePhnMatrix(chartCommonFilter);
		
		JSONObject chartObj = null;
		chartObj = processLineChart(failurePhnMatrixList,chartCommonFilter);
		
		CommonFunctions.debugMsg("chartObj ="+chartObj);
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();
	}
	
	private JSONObject processLineChart(List<String[]> failurePhnMatrixList,CommonFilter commonFilter){
		CommonFunctions.debugMsg("failurePhnMatrixList size in proceesline char ="+failurePhnMatrixList.size());
		if( failurePhnMatrixList == null || failurePhnMatrixList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		String fromMonth = commonFilter.getFromMonth();
		String toMonth = commonFilter.getToMonth();
		
		String month1   = fromMonth +"  -  "+ toMonth ;
		
		String title = ("Failure Phenomena Matrix"+ " - "+month1);
		int index=0;
		if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		if(failurePhnMatrixList!=null)
			index=failurePhnMatrixList.size()-1;
	
		String[] header =  failurePhnMatrixList.get(1);
		String[] month =  failurePhnMatrixList.get(2);
		String[] data =  failurePhnMatrixList.get(3);
		String prevMonth = null;
		CommonFunctions.debugMsg(failurePhnMatrixList.get(3).toString()+"In side te procss chart method"+ " "+data[1]);

		
		String subTitle = data[2];
		CommonFunctions.debugMsg("subTitle "+subTitle );
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> occurenceData = new ArrayList<Double>();
		
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		
		CommonFunctions.debugMsg("header.length ="+header.length +"  "+header.toString());
		for( int i = 9;i < header.length;i++ ){
			CommonFunctions.debugMsg(" header.....Occc.. "+header[i] +" month "+month[i]+" data " +data[i]);
			if(header[i].contains("Occurence") ){
				String dat = data[i].trim();
				if ("".equals(dat))
					dat = "0";
				occurenceData.add(Double.valueOf(Double.parseDouble(dat)));
			
			
				//occurenceData.add(Double.parseDouble(data[i]));
			}
			else if(header[i].contains("Time") ){
				CommonFunctions.debugMsg(" header...Tm.... "+header[i]);
				String dat = data[i].trim();
				CommonFunctions.debugMsg("....header...Tm...."+dat);
				if ("".equals(dat))
					dat = "0";
				timeData.add(Double.valueOf(Double.parseDouble(dat)));
			
				//timeData.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
		//	CommonFunctions.debugMsg(""+xAxisCategory.get(i));
			}
			prevMonth = month[i];
		}
		if(occurenceData.size() > 0 )
		{
			timeSeries.setData(occurenceData);
			timeSeries.setType(ChartTypes.SPLINE);
			timeSeries.setName("Occurrences");
			chartSeriesList.add(timeSeries);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Equipment Failure Occurrences");
			
			chartYAxis.add(yAxis);
		}
		if( timeData.size() > 0){
			intstanceSeries.setData(timeData);
			intstanceSeries.setType(ChartTypes.SPLINE);
			intstanceSeries.setName("Time");
			chartSeriesList.add(intstanceSeries);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Equipment Failure Minutes");
			if( chartYAxis.size() > 0)
			{
				yAxis.setOpposite(true);
				intstanceSeries.setyAxis(1);
			}
			
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
}