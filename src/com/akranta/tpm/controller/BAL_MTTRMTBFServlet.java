
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.NewBeanInstanceStrategy;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.BAL_MTTRMTBFService;

import com.akranta.tpm.service.impl.BAL_MTTRMTBFServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_MTTRMTBFServlet extends HttpServlet {
	
	/**
	
	 * Created By :N.Arun
	 */
	private static final long serialVersionUID = 1L;
	BAL_MTTRMTBFService mTTRMTBFService ; 
	public BAL_MTTRMTBFServlet(){
		/*try {
			mTTRMTBFService = new MTTRMTBFServiceImpl();
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
	
	@SuppressWarnings("unchecked")
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if( ! UIUtils.checkUserSession( request,response))
			return; 
		
		String dispatchUrl =null;
		
		
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		try {
			mTTRMTBFService = (BAL_MTTRMTBFServiceImpl)UIUtils.getServiceObject(request,"BAL_MTTRMTBFServiceImpl");
			mTTRMTBFService.BAL_MTTRMTBFServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
			
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		
		if( action.equals("filterXmlMTTRMTBF_input.MTTR")){
			 response.setContentType("xml"); 
			System.out.println("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/MTTRMTBF.xml") ;
		 }
		else if(action.equals("MTBF_getRefresh.MTTR"))
		{
			CommonFilter commonFilter = populateCommonFilter(request,"MTTRMTBF",false); 
			String refresh =request.getParameter("refresh");
			 CommonFunctions.debugMsg("refresh...."+refresh);
			 mTTRMTBFService.getRefresh(commonFilter);
			 
		}
		if( action.equals("filterXmlMTBF_input.MTTR")){
			 response.setContentType("xml"); 
			System.out.println("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/MTTRMTBF.xml") ;
		 }
		else if(action.equals("MTBF_input.MTTR")) 
		{
			String filterString = request.getParameter("filterString");
			request.setAttribute("filterStr", filterString);
			
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			if(!UIUtils.isValidKeyId(cellId)){
				cellId = request.getParameter("cmbCellid");
			}
			String factId = request.getParameter("factId");
			if(!UIUtils.isValidKeyId(factId)){
				factId = request.getParameter("cmbFactid");
			}
			String sectId = request.getParameter("sectId");
			if(!UIUtils.isValidKeyId(sectId)){
				sectId = request.getParameter("cmbSectid");
			}
			String toDate = request.getParameter("toDate");
			if(!UIUtils.isValidKeyId(toDate)){
				toDate = request.getParameter("dtToDate");
			}
			String fromDate = request.getParameter("fromDate");
			if(!UIUtils.isValidKeyId(fromDate)){
				fromDate = request.getParameter("dtFromDate");
			}
			CommonFunctions.debugMsg(toDate +" :: 111111111 ::: "+ filterString );
			CommonFunctions.debugMsg(factId +" :: 111111111 ::: "+ sectId );
			request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
			RequestDispatcher rd =null;
			rd=request.getRequestDispatcher("/pages/Reports/MTBFReport.jsp");
			
			request.setAttribute("filterStr", filterString);
			request.setAttribute("hdnfactId", factId);
			request.setAttribute("hdnshftId", shift);
			request.setAttribute("hdncellId", cellId);
			request.setAttribute("hdnsectId", sectId);
			request.setAttribute("hdndateId", entryDate);
			request.setAttribute("hdnfromdate",fromDate);
			request.setAttribute("hdntodate", toDate);
			String fromdate = CommonFunctions.getDate().substring(3,11);
			String todate = CommonFunctions.getDate().substring(3,11);
			request.setAttribute("fromdate", fromdate);
			request.setAttribute("todate", todate);
			
			rd.forward(request, response); 
		}
		
		else if(action.equals("MTBF_getCol.MTTR"))
		{
	
			 PrintWriter out = response.getWriter();
			 String firstClick =request.getParameter("firstClick");
			 String factId = request.getParameter("cmbSbuid");
			 String parentId = request.getParameter("parentId");
			 String sectionId = request.getParameter("cmbSectid");
			 
			
			 CommonFilter  commonFilter = null;
		
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 				
				 commonFilter =(CommonFilter) httpSession.getAttribute("MTTRMTBF");
			 }	
			 if(commonFilter==null)
				 commonFilter = new CommonFilter();
			
			 String drillL = commonFilter.getDrillLevel();				
				String flag = request.getParameter("drillFlag"); 	
				 if("SPRM".equals(drillL) && flag.equals("b")){						
						 commonFilter.setDrillLevel("PHEN");
					 }
			
					 
			 FilterValues.getCommonFilters(request,commonFilter);
			 FilterValues.getBDRelated(request,commonFilter);
			 
			 if(UIUtils.isValidKeyId(sectionId)
				        && "f".equals(flag)
				        && !UIUtils.isValidKeyId(request.getParameter("parentId"))
				       ) {
				    commonFilter.setDrillLevel("SECT");
				}
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			  commonFilter.setViewClick('Y');
		
			 httpSession.removeAttribute("MTTRMTBF");
			 httpSession.setAttribute("MTTRMTBF",commonFilter);
	      	 response.setContentType("text/html");
	    
	      	String drillLevel = commonFilter.getDrillLevel();
			 if(drillLevel.equals("PHEN") && flag.equals("f"))
				 commonFilter.setDrillLevel("SPRM");
			 
			 List< String[]> mTTRMTBFList  = mTTRMTBFService.getAllMTBF(commonFilter);
			 
			 httpSession.removeAttribute("MtbfListValue");
		     httpSession.setAttribute("MtbfListValue", mTTRMTBFList);
		     
			 String type = "MTBF";
			
			 JSONObject colModel = getTableModel(mTTRMTBFList,FilterValues.getHeader(commonFilter.getDrillCaption()),commonFilter,type);
			 colModel.set("tableWidth", "108%%");
			 colModel.set("tableHeight", "78%%");
			 httpSession.removeAttribute("mttrReportColModel");
		     httpSession.setAttribute("mttrReportColModel", colModel);
			 System.out.println(colModel);
			 out.println(colModel);
			
			
		}
		else if( action.equals("MTBF_getData.MTTR") )
		{
			try
			{	  
				 PrintWriter out = response.getWriter(); 				
				 CommonFilter commonFilter = populateCommonFilter(request,"MTTRMTBF",false); 
				 
				 
				 List< String[]> mTTRMTBFList =  (List<String[]>) httpSession.getAttribute("MtbfListValue");
				 
				 CommonFunctions.debugMsg("Size of list value.............."+mTTRMTBFList.size());
				// List< String[]> mTTRMTBFList  = mTTRMTBFService.getAllMTBF(commonFilter);
				 JSONObject mTTRMTBFData = new JSONObject();
				 
				 if(mTTRMTBFList != null && mTTRMTBFList.size()>1)
					 mTTRMTBFData = UIUtils.convertToJqGridTableObject(mTTRMTBFList,request,1,0);
				 
				 out.println(mTTRMTBFData);
				 commonFilter.setViewClick('N');		
				 httpSession.removeAttribute("MTTRMTBF");
			     httpSession.setAttribute("MTTRMTBF", commonFilter);
			}catch(Exception e)
			{
				System.out.println("error " +e.getMessage());
			}
		}
		
		else if( action.equals("MTBF_getExcel.MTTR")){			
			String type = "MTBF";
			ExportExcel(request,response,type);
			
		}
		
		
		else if(action.equals("MTTRMTBF_input.MTTR")) 
		{
			CommonFunctions.debugMsg("::MTTRMTBF_input.MTTR");
			String filterString = request.getParameter("filterString");
			request.setAttribute("filterStr", filterString);
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			if(!UIUtils.isValidKeyId(cellId)){
				cellId = request.getParameter("cmbCellid");
			}
			String factId = request.getParameter("factId");
			if(!UIUtils.isValidKeyId(factId)){
				factId = request.getParameter("cmbFactid");
			}
			String sectId = request.getParameter("sectId");
			if(!UIUtils.isValidKeyId(sectId)){
				sectId = request.getParameter("cmbSectid");
			}
			String toDate = request.getParameter("toDate");
			if(!UIUtils.isValidKeyId(toDate)){
				toDate = request.getParameter("dtToDate");
			}
			String fromDate = request.getParameter("fromDate");
			if(!UIUtils.isValidKeyId(fromDate)){
				fromDate = request.getParameter("dtFromDate");
			}
			CommonFunctions.debugMsg(toDate +" :: 111111111 ::: "+ filterString );
			CommonFunctions.debugMsg(factId +" :: 111111111 ::: "+ sectId );
			request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
			RequestDispatcher rd =null;
			rd=request.getRequestDispatcher("/pages/Reports/MTTRMTBF.jsp");
			
			request.setAttribute("filterStr", filterString);
			request.setAttribute("hdnfactId", factId);
			request.setAttribute("hdnshftId", shift);
			request.setAttribute("hdncellId", cellId);
			request.setAttribute("hdnsectId", sectId);
			request.setAttribute("hdndateId", entryDate);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
			String fromdate = CommonFunctions.getDate().substring(3,11);
			String todate = CommonFunctions.getDate().substring(3,11);
			request.setAttribute("fromdate", fromdate);
			request.setAttribute("todate", todate);
			
			rd.forward(request, response); 
		}
		else if(action.equals("MTTRMTBF_getCol.MTTR"))
		{	
			 PrintWriter out = response.getWriter();
			 String firstClick =request.getParameter("firstClick");
			 String sectionId = request.getParameter("cmbSectid");
			 
			 CommonFilter  commonFilter = null;
		
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 				
				 commonFilter =(CommonFilter) httpSession.getAttribute("MTTRMTBF");
			 }	
			 if(commonFilter==null)
				 commonFilter = new CommonFilter();

			 
			 String drillL = commonFilter.getDrillLevel();				
				String flag = request.getParameter("drillFlag"); 	
				 if("SPRM".equals(drillL) && flag.equals("b")){						
						 commonFilter.setDrillLevel("PHEN");
					 }
				 
			 FilterValues.getCommonFilters(request,commonFilter);
			 FilterValues.getBDRelated(request,commonFilter);
			 
			 if(UIUtils.isValidKeyId(sectionId)
				        && "f".equals(flag)
				        && !UIUtils.isValidKeyId(request.getParameter("parentId"))
				       ) {
				    commonFilter.setDrillLevel("SECT");
				}
			 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			  
			 commonFilter.setViewClick('Y');		
			 httpSession.removeAttribute("MTTRMTBF");
			 httpSession.setAttribute("MTTRMTBF",commonFilter);
	      	 response.setContentType("text/html");
	      	  
	      	String drillLevel = commonFilter.getDrillLevel();
			 if(drillLevel.equals("PHEN") && flag.equals("f"))
				 commonFilter.setDrillLevel("SPRM");
			 
	    	 List< String[]> mTTRMTBFList  = mTTRMTBFService.getAllMTTRMTBF(commonFilter);
	    	 
	    	 for(String[] array:mTTRMTBFList) 
	    	 {
	    		 CommonFunctions.debugMsg(Arrays.toString(array));
	    	 }
			 String type = "MTTR";			
			 JSONObject colModel = getTableModel(mTTRMTBFList,FilterValues.getHeader(commonFilter.getDrillCaption()),commonFilter,type);
			 colModel.set("tableWidth", "106%%");
			 colModel.set("tableHeight", "78%%");
			 
			 httpSession.removeAttribute("mttrReportColModel");
		     httpSession.setAttribute("mttrReportColModel", colModel);
			 System.out.println(colModel);
			 out.println(colModel);
			
			
		}
		
		
		
		else if( action.equals("MTTRMTBF_getData.MTTR") )
		{
			try
			{	  
				 PrintWriter out = response.getWriter(); 				
				 CommonFilter commonFilter = populateCommonFilter(request,"MTTRMTBF",false); 
				 String sectionId = request.getParameter("cmbSectid");
				 String flag = request.getParameter("drillFlag"); 	
				 if(UIUtils.isValidKeyId(sectionId)
					        && "f".equals(flag)
					        && !UIUtils.isValidKeyId(request.getParameter("parentId"))
					       ) {
					    commonFilter.setDrillLevel("SECT");
					}
				 List< String[]> mTTRMTBFList  = mTTRMTBFService.getAllMTTRMTBF(commonFilter);
				 JSONObject mTTRMTBFData = new JSONObject();
				 
				 for(String[] array:mTTRMTBFList) 
		    	 {
		    		 CommonFunctions.debugMsg(Arrays.toString(array));
		    	 }
				 
				 CommonFunctions.debugMsg(mTTRMTBFList.size()+"size");
				 
				 if(mTTRMTBFList != null && mTTRMTBFList.size()>1)
					 mTTRMTBFData = UIUtils.convertToJqGridTableObject(mTTRMTBFList,request,1,0);
				 
				 out.println(mTTRMTBFData);
				 commonFilter.setViewClick('N');
				
			}catch(Exception e)
			{
				System.out.println("error " +e.getMessage());
			}
		}
		else if (action.equals("MTTRMTBFDetails_input.MTTR"))
        {
			String filterString = request.getParameter("sectId");
			String month = request.getParameter("month");
			 request.setAttribute("sectId", filterString);
			 request.setAttribute("month", month);
			 CommonFunctions.debugMsg("sectid::::::  ?: "  +request.getParameter("sectId"));
			 RequestDispatcher rd = request.getRequestDispatcher("/pages/MttrMttbfDetails.jsp");     
			 rd.forward(request, response);
		 }
		
		else if (action.equals("MTTRMTBFDetails_getCol.MTTR"))
		{
		
        CommonFunctions.debugMsg("sds");
		PrintWriter out = response.getWriter();
				
		out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Standardizedworkchart","StdWrkChrtGrid"));
	     } 
		
		else if (action.equals("MTTRMTBFDetails_getData.MTTR")) 
		{

			try
			{
				String filterString = request.getParameter("sectId");
				String month = request.getParameter("month");
				
				CommonFunctions.debugMsg("Section ID :::  "+filterString);
				CommonFunctions.debugMsg("month :::  "+month);
				List<String[]> WorkGrid = mTTRMTBFService.getchart1(filterString,month);
				PrintWriter out = response.getWriter();
				//CommonFilter commonFilter = populateCommonFilter(request,"VoiceCustomerCommonFilter",false);
				
				
				JSONObject workgrid = UIUtils.convertToJqGridTableObject(WorkGrid, request, 0, 0);
				out.println(workgrid);
				 
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			}
		
		else if( action.equals("MTTRMTBF_getExcel.MTTR")){			
			String type = "MTTR";
			ExportExcel(request,response,type);
			
		}
		else if( action.equals("chart.MTTR")){
			String type = request.getParameter("type");
			processChart(request,response,type);			
		}	
		
	}
	private void ExportExcel(HttpServletRequest request, HttpServletResponse response,String type) throws Exception{
		
		HttpSession httpSession = request.getSession(false);	
		CommonFilter commonFilter = populateCommonFilter(request,"MTTRMTBF",false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		String fromMonth = commonFilter.getFromMonth();
		String toMonth = commonFilter.getToMonth();
		String date   = fromMonth +"  -  "+ toMonth ;			
		JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("mttrReportColModel");	
		
		if(type.equals("MTBF"))
			tblJSONObj.put("title", "MTBF Report"+"  -  "+date);
		else
			tblJSONObj.put("title", "MTTR Report"+"  -  "+date);
		
		String format = ExcelUtils.getFormat(request);			
		Workbook wb = mTTRMTBFService.mttrExportExcel(commonFilter,tblJSONObj,format,type);			  
		commonFilter.setFromRow(tmpFromRow);			
		ExcelUtils.writeToResponse(response, wb, "MTTRMTBFReport", format);
		
	}
	private void processChart(HttpServletRequest request, HttpServletResponse response,String type) throws Exception{
		HttpSession httpSession = request.getSession(false);		
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("MTTRMTBF");
		CommonFilter chrtCommonFilter = new  CommonFilter();		
		BeanUtils.copyProperties(chrtCommonFilter, commonFilter);		
		FilterValues.getCommonFilters(request, chrtCommonFilter) ;
		
		List<String[]> mTTRMTBFList = null;
		if(type.equals("MTTR"))
			 mTTRMTBFList  = mTTRMTBFService.getAllMTTRMTBF(chrtCommonFilter);
		else
			mTTRMTBFList  = mTTRMTBFService.getAllMTBF(chrtCommonFilter);
		
		JSONObject chartObj = null;
		String chartType = request.getParameter("chType");
		
		if( chartType == null){
			chartObj = processLineChart(mTTRMTBFList,chrtCommonFilter,type);
		}
		else{
			chartObj = processPieChart(mTTRMTBFList,chrtCommonFilter,type);
		}
		chrtCommonFilter = null;
		
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();
			
	}
	
	private JSONObject processPieChart(List<String[]> equipmentDownTimeList,CommonFilter commonFilter,String type){
	
			return null;
		
	}
	
	private JSONObject processLineChart(List<String[]> mTTRMTBFList,CommonFilter commonFilter,String type){		
		if( mTTRMTBFList == null || mTTRMTBFList.size() <= 1  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		String [] header =  mTTRMTBFList.get(0);
		String [] month =  mTTRMTBFList.get(0);
		String [] data =  mTTRMTBFList.get(1);
		String prevMonth = null;
		int length =0;
		String title = null;
		String subTitle = null;
		String yaxisName = null;
		String fromDate = "";
		String toDate = "";
		if(commonFilter.getMonwise().equals("Y"))
		{
			fromDate = commonFilter.getFromMonth();
			toDate = commonFilter.getToMonth();
		}
		else{
			fromDate = commonFilter.getFromDate();
			toDate = commonFilter.getToDate();
		}
		if(type.equals("MTTR"))
		{
			title = "Breakdown MTTR From "+fromDate+" To "+toDate;
			subTitle = data[1];
			length = header.length;
			yaxisName = "MTTR (in Hours)";
		}
		else
		{
			title = "Breakdown MTBF From "+fromDate+" To "+toDate;
			 subTitle = data[1];
			 length = header.length-1;
			 yaxisName = "MTBF (in Hours)";		 		 
		}			
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		
		for( int i = 2;i < length;i++ ){
			
			timeData.add(Double.parseDouble(data[i]));
			
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(timeData.size() > 0 ){
			timeSeries.setData(timeData);
			timeSeries.setType(ChartTypes.SPLINE);
			timeSeries.setName("Month");
			chartSeriesList.add(timeSeries);
			
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText(yaxisName);
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
	
	private JSONObject getTableModel(List<String[]> headers, String caption,CommonFilter commonFilter,String type)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);			
		
		if(type.equals("MTB"))
		{
			/*colHeader[0] = caption;  
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setTableButton(true);
			
			for(int i =0; i < colHeader.length; i++)
			{			
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
				jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
						
				jqGridColModel.setWidth( 100);				
				jqGridColModel.setAlign("right");
				jqGridColModel.setEditable(false);
				if(i==0 )				{
					jqGridColModel.setHidden(false);	
					jqGridColModel.setAlign("left");
				}
				if(i==1){
				jqGridColModel.setWidth( 80);				
				jqGridColModel.setAlign("right");
				}		
				if(i==colHeader.length-1)
				{
					jqGridColModel.setHidden(true);
					jqGridColModel.setKey(true);	
				}
				jqGridTableModel.getColModel().add(jqGridColModel);
			}*/
			
		}
		else{
			colHeader[1] = caption;  
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			
		for(int i =0; i < colHeader.length; i++)
		{			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
					
			jqGridColModel.setWidth( 100);				
			jqGridColModel.setAlign("right");
			jqGridColModel.setEditable(false);
			if(i==0 )
			{
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}
			if(i==1){
			jqGridColModel.setWidth( 250);				
			jqGridColModel.setAlign("left");
			}		
			if(type.equals("MTBF"))
			{
				if(i==colHeader.length-1)
				{
					jqGridColModel.setHidden(true);
					jqGridColModel.setKey(true);	
				}
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
	private JqGridColModel getColModel (String colIndex, int width,String allign)
	{
		System.out.println("row[3]colIndex " +colIndex);
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
		
}
		