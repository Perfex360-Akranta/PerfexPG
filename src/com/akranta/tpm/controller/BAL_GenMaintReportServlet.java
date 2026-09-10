/*Author Manikandan*/
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

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

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
import com.akranta.tpm.service.BAL_GeneralmaintService;
import com.akranta.tpm.service.impl.BAL_GeneralmaintServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_GenMaintReportServlet extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	BAL_GeneralmaintService generalmaintService ; 
	public BAL_GenMaintReportServlet(){
		/*try {
			generalmaintService = new GeneralmaintServiceImpl();
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
			
			HttpSession httpSession = request.getSession(false);
			//
	    String action = UIUtils.getActionPart(request);
		try {
			generalmaintService = (BAL_GeneralmaintServiceImpl)UIUtils.getServiceObject(request,"BAL_GeneralmaintServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}	

		if( action.equals("filterXmlgenMain_input.genmainRpt")||action.equals("filterXmlgenMainMould_input.genmainRpt")||action.equals("filterXmlgenMainSetupAndAdjust_input.genmainRpt")){
			 response.setContentType("xml"); 
			System.out.println("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/generalMaintaince.xml") ;
		 }
		else if(action.equals("genMain_input.genmainRpt")||action.equals("genMainMould_input.genmainRpt")||action.equals("genMainSetupAndAdjust_input.genmainRpt")) 
		{
			/*for navigating back to the drillLevel from view mode to report*/
			String filtStr = request.getParameter("filterString");
			String selid = request.getParameter("selid");
			String filterStr = request.getParameter("filterStr");
			
			String filter = request.getParameter("filter");
			CommonFunctions.debugMsg("keyId to be shown...................."+filter);
			String keyId = request.getParameter("keyId");
			CommonFunctions.debugMsg("keyId to be shown...................."+keyId);
			
			String parentId = request.getParameter("parentId");
			request.setAttribute("filterStr", filtStr);
			request.setAttribute("selid", selid);
			String mchId = request.getParameter("mchId");
			if(mchId != null && mchId !="")
				request.setAttribute("mchId", mchId);
			request.setAttribute("filterStr", filterStr);
			request.setAttribute("filter", filter);
			request.setAttribute("parentId", parentId);
			request.setAttribute("keyId", keyId);
			if(action.equals("genMainMould_input.genmainRpt"))
				request.setAttribute("relatedTo","MOULD");
			if(action.equals("genMainSetupAndAdjust_input.genmainRpt"))
				request.setAttribute("setupandadjustment", "true");
			request.setAttribute("hiddenUrl", action); // ADD THIS
			UIUtils.forwardRequest(request, response,"/pages/Reports/GeneralMaintReport.jsp"); 
		
		}
		else if(action.equals("genMain_getCol.genmainRpt")||action.equals("genMainMould_getCol.genmainRpt")||action.equals("genMainSetupAndAdjust_getCol.genmainRpt"))
		{
			//httpSession.removeAttribute("generalmanitFilter");
			
			PrintWriter out = response.getWriter();
			 String firstClick =request.getParameter("firstClick");
			 CommonFilter  commonFilter = null;
			
			/* if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){
				 commonFilter =(CommonFilter) httpSession.getAttribute("generalmanitFilter");
			 }*/	
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
			 
				 
			 //commonFilter = populateCommonFilter(request,"generalmanitFilter",true); 
			 commonFilter = populateCommonFilter(request,"generalmanitFilter",( UIUtils.isValidKeyId(firstClick) || (firstClick!= null && ! firstClick.equals("Y") )),true);
			 commonFilter.setAssemblyDrillExist(true);
			// commonFilter=FilterValues.getCommonFilters(request,commonFilter);
			 //commonFilter= FilterValues.getBDRelated(request,commonFilter);
			  if(action.equals("genMainMould_getCol.genmainRpt"))
					 commonFilter.setRelatedToMchMld("MLD");
			  else 
				  commonFilter.setRelatedToMchMld("MCH");
			  
			  /*setting only month skiping the date field*/
		 	  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
				 
		 	  }
		 	 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
		 		 commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(0));
				  commonFilter.setToDate(CommonFunctions.getDate());
				 
		 	  }
	      	  response.setContentType("text/html");
			  //commonFilter.setMonwise("Y");
	      	  	
		  		//httpSession.removeAttribute("generalmanitdrillDown");
	      	commonFilter.setISFORGRAPH('N');
			  List<String[]> generalmanitList  = generalmaintService.getAllgeneralmanit(commonFilter);
			  JSONObject jsonObject = null;
			  
			  	if( generalmanitList.size() > 0){
			  		
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					gridColModel.setHeaderNum(2);		
					
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setRowNumbers(true);
					
			  		List<String[]> headers = new ArrayList<String[]>();				
					//headers.add(colHeader);
					String [] colHeader2 = generalmanitList.get(2);
					String [] colHeader3 = generalmanitList.get(3);
					String [] colHeaderHead = generalmanitList.get(1);
					headers.add(colHeader2);	
					headers.add(colHeader3);	

					
					jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			  		//String [] colHeader2 = generalmanitList.get(1);  // DATAORDER=2 header (date groups)
//			  		String [] colHeader3 = generalmanitList.get(2);  // DATAORDER=3 sub-header (OCCURENCE/TIME)
//			  		String [] colHeaderHead = generalmanitList.get(0); // DATAORDER=1 tablemodel
//			  		headers.add(colHeader2);	
//			  		headers.add(colHeader3);
//
//			  		jsonObject = UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);
				   /*jsonObject = getTableModel(generalmanitList,FilterValues.getHeader(commonFilter.getDrillCaption()),commonFilter);
				    * 
				    */
				   jsonObject.set("tableHeight", "70%%");
				   jsonObject.set("tableWidth", "105%%");
			  	}
		      	/*else{
		      		FilterValues.setBackwardDrillLevel(commonFilter,"");
		      		jsonObject = new JSONObject();
		      		jsonObject.put("noData",true);
		      		jsonObject.put("noDataMsg","No data found");
		      	}*/
			  	httpSession.removeAttribute("generalmanitdrillDown");
			  	httpSession.setAttribute("generalmanitdrillDown",jsonObject);
			  	httpSession.removeAttribute("generalmanitFilter");
		  		httpSession.setAttribute("generalmanitFilter",commonFilter);
		  		out.println(jsonObject);
			
		}
		else if( action.equals("genMain_getData.genmainRpt")||action.equals("genMainMould_getData.genmainRpt")||action.equals("genMainSetupAndAdjust_getData.genmainRpt") )
		{
			try
			{
				PrintWriter out = response.getWriter();
				
				 CommonFilter  commonFilter = (CommonFilter) httpSession.getAttribute("generalmanitFilter");
				  commonFilter = populateCommonFilter(request,"generalmanitFilter",false,false);
				 CommonFunctions.debugMsg("commonFilter"+commonFilter);
				 commonFilter.setRowTotal('N');
				 String sbuId = request.getParameter("cmbSbuid");
				 commonFilter.setSect(sbuId);
				 System.out.println("SBU SAVE "+sbuId);
				 
				 if(action.equals("genMainSetupAndAdjust_getData.genmainRpt"))
			 		 commonFilter.setWostatus("S");//for setup and adjustment 
				 commonFilter.setISFORGRAPH('N');
				 List<String[]> generalmanitList  = generalmaintService.getAllgeneralmanit(commonFilter);
				 JSONObject listToJsonObject = UIUtils.convertToJqGridTableObject(generalmanitList,request,4,0);
				 
				 httpSession.removeAttribute("generalmanitFilter");
			  	 httpSession.setAttribute("generalmanitFilter",commonFilter);
				 out.println(listToJsonObject);
			   
			
		    }catch(Exception e)
			{
				System.out.println("error " +e.getMessage());
			}
		}
		 else if( action.equals("genMain_getExcel.genmainRpt")||action.equals("genMainMould_getExcel.genmainRpt")){
	 			
			 CommonFilter commonFilter = populateCommonFilter(request,"generalmanitFilter",false,false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("generalmanitdrillDown");
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				
				String month1   = fromMonth +"  -  "+ toMonth ;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String date   = fromdate +"  -  "+ todate ;
				tblJSONObj.put("title", "General Maintenance DrillDown " + " - "+date);
				if( commonFilter.getMonwise().equals("Y")){
					tblJSONObj.put("title", "General Maintenance DrillDown " + " - "+month1);
				}
				//tblJSONObj.put("title", "Equipment DownTime Report");
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = generalmaintService.genMaintRptExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "GeneralMaintenanceDrillDown", format);
				
	 		}
		else if( action.equals("chart.genmainRpt")){
			PrintWriter out = response.getWriter();
			//processChart(request,response);
			
			//ColumnChart columnChart = new ColumnChart();
			CommonFilter  chrtcommonFilter = (CommonFilter) httpSession.getAttribute("generalmanitFilter");
			//CommonFunctions.debugMsg("inside chart action for GM report"+request.getParameter("MAINKEYID"));
			
			
			BeanUtils.copyProperties(chrtcommonFilter, chrtcommonFilter);
			FilterValues.getCommonFilters(request, chrtcommonFilter) ;
			
			if(chrtcommonFilter.getRowTotal() == null )
				chrtcommonFilter.setRowTotal('Y');
			chrtcommonFilter.setISFORGRAPH('Y');
			List<String[]> graphData  =  generalmaintService.getAllgeneralmanit(chrtcommonFilter);//adding Data to graph
			
			ChartOptionBean lineChart =  new ChartOptionBean();
			List<String> xAxisCategory = new ArrayList<String>();
			List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
			List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
			
			String title = "General Maintainence Graph";
			
			
			String [] header =  graphData.get(0);
			String [] month =  graphData.get(2);
			String [] data =  graphData.get(4);
			String prevMonth = null;
			
			String subTitle = data[2];
			
			ChartSeries timeSeries = new ChartSeries();
			List<Double> timeData = new ArrayList<Double>();
			
			ChartSeries intstanceSeries = new ChartSeries();
			List<Double> instanceData = new ArrayList<Double>();
			
			
			for( int i = 2;i < header.length-1;i++ ){
				if( month[i].contains("YTD")) continue;
				if( month[i].contains("AVG")) continue;
				else if(header[i].contains("-T") ){

					timeData.add(Double.parseDouble(data[i]));
				}
				else if(header[i].contains("-I") ){
					CommonFunctions.debugMsg("Empty String "+data[i]);
					instanceData.add(Double.parseDouble(data[i]));
					
				}
				if( prevMonth == null || ! month[i].equals(prevMonth) ){
					
					xAxisCategory.add(month[i]);
				}
				prevMonth = month[i];
			}
			if(timeData.size() > 0 ){
				timeSeries.setData(timeData);
				timeSeries.setType(ChartTypes.SPLINE);
				timeSeries.setName("Time");
				chartSeriesList.add(timeSeries);
				
				
				ChartYAxis yAxis = new ChartYAxis(); 
				yAxis.setMin(0);
				yAxis.getTitle().setText("Time");
				chartYAxis.add(yAxis);
			}
			if( instanceData.size() > 0){
				intstanceSeries.setData(instanceData);
				intstanceSeries.setType(ChartTypes.SPLINE);
				intstanceSeries.setName("Occurrences");
				chartSeriesList.add(intstanceSeries);
				
				ChartYAxis yAxis = new ChartYAxis(); 
				yAxis.setMin(0);
				yAxis.getTitle().setText("Occurence");
				if( chartYAxis.size() > 0)
				{
					yAxis.setOpposite(true);
					intstanceSeries.setyAxis(1);
				}
				
				chartYAxis.add(yAxis);
			}
		
			ChartXAxis xaxis = new ChartXAxis();
			if(chrtcommonFilter.getMonwise().equals("Y"))
				xaxis.getTitle().setText("Month");
			else
				xaxis.getTitle().setText("Date");
			lineChart.getxAxis().setTitle(xaxis.getTitle());
			
			JSONObject chartObj  =lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
			out.println(chartObj);
			out.close();
			
		}

	}

	private JSONObject getTableModel(List<String[]> headers, String caption,CommonFilter commonFilter)
	{
		if( headers.size() < 0 )
			return null;
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);
		
		String [] colHeader1 = headers.get(1);
		
		String[] emptyrow = new String[colHeader.length]; 
		
		
		emptyrow [0] ="";
		emptyrow [1] ="";
		emptyrow [2] ="";
		emptyrow [3] ="";
		colHeader1 [3] ="";
		
		for( int i = 4; i < colHeader.length;i++ ){
			colHeader[i] = colHeader[i].substring(colHeader[i].lastIndexOf("-")+1);
			colHeader[i] = colHeader[i].replace("I","Occurences").replace("T", "Time");
			
			emptyrow [i] ="";
		}
		
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.getRowHeaders().add(colHeader);
		//	colHeader[i] = colHeader[i].replace("I", newChar)   
			
		//jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setTableButton(true);
	//	String [] colHeader = headers.get(0);
		CommonFunctions.debugMsg("RElTTo  "+caption);
		if( "MLD".equals(commonFilter.getRelatedToMchMld()) && caption.equals("Equipment") ){
			CommonFunctions.debugMsg("RElTTo  "+caption);
			CommonFunctions.debugMsg("RElTTo  :"+commonFilter.getRelatedToMchMld());
			colHeader[3] = "Mould";
		}
		else
			colHeader[3] = caption;  
		
		  
		//jqGridTableModel.getRowHeaders().add(colHeader);
	
		//jqGridTableModel.setGroupByField(" ");
		jqGridTableModel.setTableHeight(250);
		jqGridTableModel.setRowNumbers(true);
		//jqGridTableModel.setGroupBy(true);
		jqGridTableModel.setGroupSummary(true);
		//jqGridTableModel.setRowNumbers(true);
		
		
		jqGridTableModel.getColModel().add(getColModel("GroupField", 10,"left",true,true,false));
		jqGridTableModel.getColModel().add(getColModel("keyid", 0,"left",true,false,true));
		jqGridTableModel.getColModel().add(getColModel("keyidh", 10,"left",true,false,true));
	    //jqGridTableModel.getColModel().add(getColModel("dataorder", 0,"left",true,false,true));
		JqGridColModel jqGridColModel =getColModel("codeField",400,"left",false,false,false);
		
		//jqGridTableModel.getColModel().add(getColModel("ronum", 40,"left",true,false,false));
		
		jqGridColModel.setSummaryType("count");
		jqGridColModel.setSummaryTpl("<b><font >Total</font> </b>");
		jqGridTableModel.getColModel().add( jqGridColModel);
		String colIndex ="";
		colHeader = headers.get(0);
		for(int i =4; i < colHeader1.length; i++)
		{

			colIndex = colHeader1[i].replaceAll(" ", "")+i ;
			
			jqGridTableModel.getColModel().add(getColModel( colIndex,100,"center", false,true,false));
			emptyrow [i] ="";
		}
		
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
	private JqGridColModel getColModel (String colIndex, int width,String allign,boolean hidden,boolean groupbyfield,boolean key)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
				
		jqGridColModel.setWidth( width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		jqGridColModel.setHidden(hidden);
		jqGridColModel.setKey(true);
		
		if(groupbyfield){
			
			jqGridColModel.setSummaryType("sum");
			jqGridColModel.setSummaryTpl("<b><font>  {0} </font> </b>");
		}
		return jqGridColModel;
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew,boolean drilFlag){
		HttpSession httpSession = request.getSession(false);
		
		CommonFunctions.debugMsg("createNew...."+drilFlag);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			//if(drilFlag )
				FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
			commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
			
		
		}
		
		return commonFilter;
	}
}
				
