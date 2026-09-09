
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

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.PcsSmryRptService;

import com.akranta.tpm.service.impl.PcsSmryRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;


public class PcsSmryRptServlet extends HttpServlet {	
	/**
	 * Created By:N.Arun
	 */
	private static final long serialVersionUID = 1L;
	PcsSmryRptService pcsSmryRptService ; 
	public PcsSmryRptServlet(){
	/*	try {
			pcsSmryRptService = new PcsSmryRptServiceImpl();
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
			
			//PrintWriter out = response.getWriter();
		String action = UIUtils.getActionPart(request);
		try {
			pcsSmryRptService = (PcsSmryRptServiceImpl)UIUtils.getServiceObject(request,"PcsSmryRptServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		HttpSession httpSession = request.getSession(false);
		if( action.equals("filterXmlPcsSummary_input.pcsry")){
			 response.setContentType("xml"); 
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/PcsSmryRpt.xml") ;
		 }
		else if(action.equals("PcsSummary_input.pcsry")||action.equals("PcsSummarytst_input.pcsry")) 
		{
			request.setAttribute("drilldownMsgs", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsgs"));
			String locn = UIUtils.getlocation(request);
			request.setAttribute("locn",locn);
			String factId = request.getParameter("cmbFactid");
			if(!UIUtils.isValidKeyId(factId)){
				factId = request.getParameter("cmbFactid");
			}
			String sectId = request.getParameter("cmbSectid");
			if(!UIUtils.isValidKeyId(sectId)){
				sectId = request.getParameter("cmbSectid");
			}
			String toDate = request.getParameter("dtToDate");
			if(!UIUtils.isValidKeyId(toDate)){
				toDate = request.getParameter("dtToDate");
			}
			String fromDate = request.getParameter("dtFromDate");
			if(!UIUtils.isValidKeyId(fromDate)){
				fromDate = request.getParameter("dtFromDate");
			}
			
			String filterString = request.getParameter("actiopart");
			if(!UIUtils.isValidKeyId(filterString))
			{
				filterString = request.getParameter("actiopart");
			}
			request.setAttribute("filterStr", filterString);
			request.setAttribute("hdnfactId", factId);
			request.setAttribute("hdnsectId", sectId);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
			
			UIUtils.forwardRequest(request, response,"/pages/Reports/PcsSmryRpt.jsp"); 
		
		}
		else if(action.equals("PcsSummary_getCol.pcsry"))
		{ 
			 PrintWriter out = response.getWriter();
			 String firstClick =request.getParameter("firstClick");
			 CommonFilter  commonFilter  = null; 
			  
			 String forTab = request.getParameter("forTab");
			 String forTabMT = request.getParameter("forTabMT");
			 String forTabDaily = request.getParameter("forTabDaily");
			 String forTabMonthly = request.getParameter("forTabMonthly");
			 String forTabRemarks = request.getParameter("forTabRemarks");
			
	    	 if(UIUtils.isValidKeyId(forTabDaily)){
	    		
	    		 commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTabDaily",true);
	    		 commonFilter.setWostatus("forTabDaily");
	    	 }
	    	 else if(UIUtils.isValidKeyId(forTab)){
	    		 commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTab",true);
	    		 commonFilter.setWostatus("forTab");
	    		
	    	 }
	    	 else if(UIUtils.isValidKeyId(forTabMT)){
	    		 commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTabMT",true);
	    		 commonFilter.setWostatus("forTabMT");
	    		 commonFilter.setWoapproval("monthly");
	    	 }
	    	 else if(UIUtils.isValidKeyId(forTabMonthly)){
	    		 commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTabMonthly",true);
	    		 commonFilter.setWostatus("forTabMonthly");
	    		 commonFilter.setWoapproval("monthly");
	    	 }
	    	 else
	    		 commonFilter = populateCommonFilter(request,"pcsSummaryFilter",true);
	    	 
	    	 CommonMessage.debugMsg("forTabGetCol :: ::: "+forTabDaily);
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){
				 commonFilter =(CommonFilter) httpSession.getAttribute("pcsSummaryFilter");
			 }	
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
			
			// FilterValues.getCommonFilters(request,commonFilter);
			// FilterValues.getPCS(request, commonFilter);
			 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
				  commonFilter.setRemoveBlank("Y");
		 	  }
			 else
			  if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
				  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
				  commonFilter.setToDate(CommonFunctions.getDate());
				  commonFilter.setRemoveBlank("Y");
				 // commonFilter.setMonwise(" ");
		 	  }
			 
			 httpSession.removeAttribute("pcsSummaryFilter");
			 httpSession.setAttribute("pcsSummaryFilter",commonFilter);
	      	 response.setContentType("text/html");
	    	
	    	 if(!UIUtils.isValidKeyId(forTabRemarks)){
		      	 List<String[]> pcsSummaryList  = pcsSmryRptService.getAllPcsSummary(commonFilter);
		      	// List<String[]> pcsRptData =  transposeListArr(pcsSummaryList);
				 JSONObject colModel = getTableModel(pcsSummaryList,commonFilter);
				 
				 /*if(UIUtils.isValidKeyId(forTabDaily) && UIUtils.isValidKeyId(forTabMonthly)){
					 colModel.set("tableWidth", "89%%");
					 colModel.set("tableHeight", "72%%");
				 }
				 else{
					 colModel.set("tableWidth", "96%%");
					 colModel.set("tableHeight", "60%%");
				 }*/
				 //colModel.set("sortable", "false");		
				 if(commonFilter.getWostatus().equals("forTabDaily")){
					 httpSession.removeAttribute("pcsSmryReportColModelforTabDaily");
				 	 httpSession.setAttribute("pcsSmryReportColModelforTabDaily", colModel);
				 }
				 else if(commonFilter.getWostatus().equals("forTab")){
					 httpSession.removeAttribute("pcsSmryReportColModelforTab");
				 	 httpSession.setAttribute("pcsSmryReportColModelforTab", colModel);
				 }
				 else if(commonFilter.getWostatus().equals("forTabMT")){
					 httpSession.removeAttribute("pcsSmryReportColModelforTabMT");
				 	 httpSession.setAttribute("pcsSmryReportColModelforTabMT", colModel);
				 }
				 else if(commonFilter.getWostatus().equals("forTabMonthly")){
					 httpSession.removeAttribute("pcsSmryReportColModelforTabMonthly");
				 	 httpSession.setAttribute("pcsSmryReportColModelforTabMonthly", colModel);
				 }
				 CommonMessage.debugMsg(colModel);
				 out.println(colModel);
	    	 }
	    	 else{
	    		 String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel","pcsRemarkscolmodel");	
	    		
				 JSONObject tblJSONObj = JSONObject.fromString(colModel);
				 httpSession.removeAttribute("pcsSmryReportColModel");
				 httpSession.setAttribute("pcsSmryReportColModel", tblJSONObj);
				 CommonMessage.debugMsg(colModel);
				 out.println(colModel);
	    	 }
			 
		}
		else if( action.equals("PcsSummary_getData.pcsry") )
		{
			try
			{
				 PrintWriter out = response.getWriter(); 
				 String forTab = request.getParameter("forTab");
				 String forTabMT = request.getParameter("forTabMT");
				 String forTabDaily = request.getParameter("forTabDaily");
				 String forTabMonthly = request.getParameter("forTabMonthly");
				 String forTabRemarks = request.getParameter("forTabRemarks");
				 CommonMessage.debugMsg("forTab :: "+forTab +"::: "+ forTabMT+"::: "+forTabDaily+"::: "+forTabMonthly);
				 //CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("pcsSummaryFilter");
				/* CommonFilter commonFilter = populateCommonFilter(request,"pcsSummaryFilter",false);
				 
				 List<String[]> pcsSummaryList1  = pcsSmryRptService.getAllPcsSummary(commonFilter);
				 JSONObject listToJsonObject1 = new JSONObject();
				 List<String[]> pcsSummaryData1 =  transposeListArr(pcsSummaryList1);
				 if(pcsSummaryData1 != null && pcsSummaryData1.size()>0)
					 listToJsonObject1 = UIUtils.convertToJqGridTableObject( pcsSummaryData1,request,2,0);
		 
			/*	 if(pcsSummaryList != null && pcsSummaryList.size()>0)
					 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsSummaryList,request,0,0);
				*/
				/* CommonMessage.debugMsg(listToJsonObject1);
				 out.println(listToJsonObject1);
			     httpSession.removeAttribute("pcsSummaryFilter");
  			 	 httpSession.setAttribute("pcsSummaryFilter", commonFilter);*/
				// if(forTab != null && forTab !=" " && forTab !="" && forTab !="null"){
				 if(UIUtils.isValidKeyId(forTab)){//forTabMT != " "   && forTabMT !="" && forTabMT !="null"){
					 CommonMessage.debugMsg("forTab :: 111111111111111111111111 " );
						 CommonFilter commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTab",false);
						 if(UIUtils.isValidKeyId(forTab)){
				    		 commonFilter.setWostatus("forTab");
				    		 commonFilter.setWoapproval("daily");
				    	 }
				    	
						 List<String[]> pcsSummaryList  = pcsSmryRptService.getAllPcsSummary(commonFilter);
						 JSONObject listToJsonObject = new JSONObject();
						 //List<String[]> pcsSummaryData =  transposeListArr(pcsSummaryList);
						 //if(pcsSummaryData != null && pcsSummaryData.size()>0)
							 listToJsonObject = UIUtils.convertToJqGridTableObject( pcsSummaryList,request,1,0,commonFilter.getTotalRecordCnt());
				 
					/*	 if(pcsSummaryList != null && pcsSummaryList.size()>0)
							 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsSummaryList,request,0,0);
						*/
						 CommonMessage.debugMsg(listToJsonObject);
						 out.println(listToJsonObject);
					    // httpSession.removeAttribute("pcsSummaryFilter");
		  			 	 //httpSession.setAttribute("pcsSummaryFilter", commonFilter);
				 } 
			//}
			/*  catch(Exception e)
				{
					CommonMessage.debugMsg("error " +e.getMessage());
				}
		}
		else if( action.equals("PcsSummaryMon_getData.pcsry") ){
			try
			{*/
				// PrintWriter out = response.getWriter(); 
			//else if(forTabMT != null || forTabMT !=" " || forTabMT !="" || forTabMT !="null"  ){
			else  if(UIUtils.isValidKeyId(forTabMT)){
				 CommonMessage.debugMsg("forTab :: 111111111111111111111111 22222222222" );
						 CommonFilter commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTabMT",false);
						 String fromDate =commonFilter.getFromDate();
						 String toDate = commonFilter.getToDate();
						 //commonFilter.setFromDate("01-"+fromDate.substring(fromDate.indexOf("-")+1 ));//.substring(fromDate.indexOf("-")+1 )
						 //commonFilter.setToDate("30-" +ToDate.substring(3,11));
						 String lastDay = CommonFunctions.getLastDayOfMonth(toDate);
						 if(UIUtils.isValidKeyId(forTabMT)){
				    		 commonFilter.setWostatus("forTabMT");
				    		 commonFilter.setWoapproval("monthly");
				    	 }
				    	 
						// commonFilter.setToDate(lastDay);
						 List<String[]> pcsSummaryList  = pcsSmryRptService.getAllPcsSummary(commonFilter);
						 JSONObject listToJsonObject = new JSONObject();
						// List<String[]> pcsSummaryData =  transposeListArr(pcsSummaryList);
						// if(pcsSummaryData != null && pcsSummaryData.size()>0)
							 listToJsonObject = UIUtils.convertToJqGridTableObject( pcsSummaryList,request,1,0,commonFilter.getTotalRecordCnt());
						 
					/*	 if(pcsSummaryList != null && pcsSummaryList.size()>0)
							 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsSummaryList,request,0,0);
						*/
						 CommonMessage.debugMsg(listToJsonObject);
						 out.println(listToJsonObject);
					    // httpSession.removeAttribute("pcsSummaryFilter");
		  			 	// httpSession.setAttribute("pcsSummaryFilter", commonFilter);
				}
			else if(UIUtils.isValidKeyId(forTabDaily)){
				 CommonMessage.debugMsg("forTabDaily :: 111111111111111111111111 22222222222" );
						 CommonFilter commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTabDaily",false);
						 String fromDate =commonFilter.getFromDate();
						 String toDate = commonFilter.getToDate();
						 if(UIUtils.isValidKeyId(forTabDaily)){
				    		 commonFilter.setWostatus("forTabDaily");
				    		 commonFilter.setWoapproval("daily");
						 }
						// commonFilter.setFromDate("01-"+fromDate.substring(fromDate.indexOf("-")+1 ));//.substring(fromDate.indexOf("-")+1 )
						 //commonFilter.setToDate("30-" +ToDate.substring(3,11));
						 //String lastDay = CommonFunctions.getLastDayOfMonth(toDate);
						 commonFilter.setWostatus("forTabDaily");
						// commonFilter.setToDate(lastDay);
						 List<String[]> pcsSummaryList  = pcsSmryRptService.getAllPcsSummary(commonFilter);
						 JSONObject listToJsonObject = new JSONObject();
						// List<String[]> pcsSummaryData =  transposeListArr(pcsSummaryList);
						// if(pcsSummaryData != null && pcsSummaryData.size()>0)
							 listToJsonObject = UIUtils.convertToJqGridTableObject( pcsSummaryList,request,2,0,commonFilter.getTotalRecordCnt());
						 
					/*	 if(pcsSummaryList != null && pcsSummaryList.size()>0)
							 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsSummaryList,request,0,0);
						*/
						 CommonMessage.debugMsg(listToJsonObject);
						 out.println(listToJsonObject);
					    // httpSession.removeAttribute("pcsSummaryFilter");
		  			 	// httpSession.setAttribute("pcsSummaryFilter", commonFilter);
				}
			else if(UIUtils.isValidKeyId(forTabMonthly)){
				
				 CommonMessage.debugMsg("forTabMonthly :: 111111111111111111111111 22222222222" );
						 CommonFilter commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTabMonthly",false);
						 String fromDate =commonFilter.getFromDate();
						 String toDate = commonFilter.getToDate();
						// commonFilter.setFromDate("01-"+fromDate.substring(fromDate.indexOf("-")+1 ));//.substring(fromDate.indexOf("-")+1 )
						 //commonFilter.setToDate("30-" +ToDate.substring(3,11));
						 //String lastDay = CommonFunctions.getLastDayOfMonth(toDate);
						 if(UIUtils.isValidKeyId(forTabMonthly)){
				    		
				    		 commonFilter.setWostatus("forTabMonthly");
				    		 commonFilter.setWoapproval("monthly");
				    	 }
						
						// commonFilter.setToDate(lastDay);
						 List<String[]> pcsSummaryList  = pcsSmryRptService.getAllPcsSummary(commonFilter);
						 JSONObject listToJsonObject = new JSONObject();
						// List<String[]> pcsSummaryData =  transposeListArr(pcsSummaryList);
						// if(pcsSummaryData != null && pcsSummaryData.size()>0)
							 listToJsonObject = UIUtils.convertToJqGridTableObject( pcsSummaryList,request,2,0,commonFilter.getTotalRecordCnt());
						 
					/*	 if(pcsSummaryList != null && pcsSummaryList.size()>0)
							 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsSummaryList,request,0,0);
						*/
						 CommonMessage.debugMsg(listToJsonObject);
						 out.println(listToJsonObject);
					    // httpSession.removeAttribute("pcsSummaryFilter");
		  			 	// httpSession.setAttribute("pcsSummaryFilter", commonFilter);
				}
			 else if(UIUtils.isValidKeyId(forTabRemarks)){
				 CommonMessage.debugMsg("forTabRemarks ::  Y");
				 CommonFilter commonFilter = populateCommonFilter(request,"pcsSummaryFilter",false);
	        	   List<String[]> pcsRemarksList  = pcsSmryRptService.getpcsRemarks(commonFilter);
		    		 JSONObject listToJsonObject = new JSONObject();
					 listToJsonObject = UIUtils.convertToJqGridTableObject( pcsRemarksList,request,0,0);
					 CommonMessage.debugMsg(listToJsonObject);
					 out.println(listToJsonObject);
	           }
			else{
				/* CommonFilter commonFilter = populateCommonFilter(request,"pcsSummaryFilter",false);
				 
				 List<String[]> pcsSummaryList  = pcsSmryRptService.getAllPcsSummary(commonFilter);
				 JSONObject listToJsonObject = new JSONObject();
				 List<String[]> pcsSummaryData =  transposeListArr(pcsSummaryList);
				 if(pcsSummaryData != null && pcsSummaryData.size()>0)
					 listToJsonObject = UIUtils.convertToJqGridTableObject( pcsSummaryData,request,2,0);*/
		 
			/*	 if(pcsSummaryList != null && pcsSummaryList.size()>0)
					 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsSummaryList,request,0,0);
				*/
				/* CommonMessage.debugMsg(listToJsonObject);
				 out.println(listToJsonObject);
			     httpSession.removeAttribute("pcsSummaryFilter");
  			 	 httpSession.setAttribute("pcsSummaryFilter", commonFilter);*/
			}
          
			}
		    catch(Exception e)
			{
				CommonMessage.debugMsg("error " +e.getMessage());
			}
		}
		else if( action.equals("PcsSummary_getExcel.pcsry")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = null;
			
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = null;
			
			 String forTab = request.getParameter("forTab");
			 String forTabMT = request.getParameter("forTabMT");
			 String forTabDaily = request.getParameter("forTabDaily");
			 String forTabMonthly = request.getParameter("forTabMonthly");
			 String type = null;
			 if(UIUtils.isValidKeyId(forTabDaily)){
				 commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTabDaily",false);
				 tblJSONObj = (JSONObject)httpSession.getAttribute("pcsSmryReportColModelforTabDaily");
				 
				 CommonMessage.debugMsg("test forTabDaily");
				 type = "forTabDaily";
			 }
	    	 else if(UIUtils.isValidKeyId(forTab)){
	    		 commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTab",false);
	    		CommonMessage.debugMsg("test forTab");
	    		  tblJSONObj = (JSONObject)httpSession.getAttribute("pcsSmryReportColModelforTab");
	    		  type = "forTab";
	    	 }
	    	 else if(UIUtils.isValidKeyId(forTabMT)){
	    		 commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTabMT",false);
	    		 CommonMessage.debugMsg("test forTabMT");
	    		 tblJSONObj = (JSONObject)httpSession.getAttribute("pcsSmryReportColModelforTabMT");
	    		 
	    		 type = "forTabMT";
	    		 //commonFilter.setWoapproval("monthly");
	    	 }
	    	 else if(UIUtils.isValidKeyId(forTabMonthly)){
	    		 commonFilter = populateCommonFilter(request,"pcsSummaryFilterforTabMonthly",false);
	    		 CommonMessage.debugMsg("test forTabMonthly");
	    		 
	    		 tblJSONObj = (JSONObject)httpSession.getAttribute("pcsSmryReportColModelforTabMonthly");
	    		 
	    		 type = "forTabMonthly";
	    		 //commonFilter.setWoapproval("monthly");
	    	 }
	    	 else{
	    		 commonFilter = populateCommonFilter(request,"pcsSummaryFilter",false);
	    		 String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel","pcsRemarkscolmodel");
	    		  tblJSONObj = JSONObject.fromString(tableModel);
	    		
	    		 type = "remarks";
	    	 }
			 
			
		//	tblJSONObj.put("transpose", true);
			tblJSONObj.put("title", "PCS Summary Report" );
			String format = ExcelUtils.getFormat(request);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate ;
			Workbook wb = pcsSmryRptService.pcsSmryExportExcel(commonFilter,tblJSONObj,format,type);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "PcsSummaryReport", format);
			
		}
	}
	
	
	private JSONObject getTableModel(List<String[]> headers,CommonFilter commonFilter)
	{
		String tabSelected = null;
		 String [] colHeader = headers.get(1);
		 String [] colHeader_0 = headers.get(0);
		// colHeader_0[4]="Total";
		 String[] emptyrow = new String[colHeader.length];
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		List<Integer> rotationRows = new ArrayList<Integer>();
		 if( commonFilter.getWostatus().equals("forTabDaily")||commonFilter.getWostatus().equals("forTabMonthly"))
		 {
			 tabSelected = "PRODUCTIONRPT";
			
			 for(int i =0; i < colHeader_0.length; i++)
				{	
				 
				 emptyrow[i] = "";
				}
			 rotationRows.add(2);
			//jqGridTableModel.getRowHeaders().add(emptyrow);
			jqGridTableModel.getRowHeaders().add(colHeader_0);
			//jqGridTableModel.getRowHeaders().add(colHeader);
			
		 }
    	 else if(commonFilter.getWostatus().equals("forTab")||commonFilter.getWostatus().equals("forTabMT"))
    	 {
    		 tabSelected = "PRODUCTIONSMMRY";
    		 rotationRows.add(2);
    		 jqGridTableModel.getRowHeaders().add(colHeader_0);
    	 }
    	 else if(commonFilter.getWostatus().equals("forTabMT"))
		 {
			  tabSelected = "PRODUCTIONSMMRY";
			  rotationRows.add(2);
			 jqGridTableModel.getRowHeaders().add(colHeader);
		 }
		CommonMessage.debugMsg("getTableModel");
		
		
		/*CommonMessage.debugMsg("headers.get(0):" + headers.get(0));
		CommonMessage.debugMsg("headers Completed");*/
		
				
		//jqGridTableModel.setTableButton(true);		
		jqGridTableModel.setRowNumbers(true);	
		/*jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(1000);*/
		
		
		jqGridTableModel.setRotationRows(rotationRows);
		CommonMessage.debugMsg("tabSelected   :"+tabSelected);
		for(int i =0; i < colHeader.length; i++)
		{			
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setAlign("right");
			if("PRODUCTIONSMMRY".equals(tabSelected) ){
				CommonMessage.debugMsg("tabSelected if  :"+tabSelected);
				
				if( i<=3 ){
					jqGridColModel.setHidden(true);				
				}
				if( i==colHeader.length-1 ){
					jqGridColModel.setHidden(true);				
				}
				if(i==4){
					jqGridColModel.setAlign("left");
					jqGridColModel.setWidth(180);
				}
				if(i>5 && i<15){
					
					CommonMessage.debugMsg("value of i :"+i+" : "+colHeader[i]);
				jqGridColModel.setWidth(80);
				if(i>6)
					jqGridColModel.setHeaderRotation("90");
					
				}
				if( i>=15 && i<=colHeader.length){
					jqGridColModel.setWidth(80);
					jqGridColModel.setHeaderRotation("90");
				}
				
				
			}
			else if("PRODUCTIONRPT".equals(tabSelected) ){
				CommonMessage.debugMsg("tabSelected else  :"+tabSelected);

				if( i>=0 && i<=1){
					CommonMessage.debugMsg("tabSelected else if :"+tabSelected);
					jqGridColModel.setHidden(true);				
				}
				if( i==colHeader.length-1 ){
					jqGridColModel.setHidden(true);				
				}
				jqGridColModel.setWidth( 129);
				if( i>3){
				jqGridColModel.setAlign("right");
				}
				if(i == 2)
					jqGridColModel.setAlign("left");
				if(i>2 && i!=colHeader.length)
					jqGridColModel.setHeaderRotation("90");
			}
			
			jqGridColModel.setEditable(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 if("PRODUCTIONSMMRY".equals(tabSelected))
			 tableModel.set("tableHeight", "70%%");
		 else
			 tableModel.set("tableHeight", "62%%");

			 tableModel.set("tableWidth", "95%%");
		 
		 return tableModel;
		/*JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		 
		String [] colHeader_0 = headers.get(0);
		String [] colHeader = headers.get(1 );
		
		//String [] colHeader1 = headers.get(1);
		
		//String[] emptyrow = new String[colHeader.length]; 
		 
		 /*colHeader [0] ="Result";
		 colHeader [1] ="UOM";*/
		//colHeader [2] ="";
		//colHeader1 [2] ="";
	 
	//	jqGridTableModel.getRowHeaders().add(emptyrow);
		/*jqGridTableModel.getRowHeaders().add(colHeader_0);
		jqGridTableModel.getRowHeaders().add(colHeader);
	 
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.getColModel().add(getColModel("keyid", 250,"left",true,true,true));
		
		JqGridColModel jqGridColModel =getColModel("codeField",250,"right",false,true,false);
		jqGridTableModel.getColModel().add(getColModel("GroupField", 250,"center",false,true,false));
		
		//jqGridColModel.setSummaryType("count");
		jqGridColModel.setSummaryTpl("<b><font >Total</font> </b>");
		jqGridTableModel.getColModel().add( jqGridColModel);
		String colIndex ="";
		colHeader = headers.get(0);
		for(int i =3; i < colHeader.length; i++)
		{
			if(i==9)
				jqGridColModel.setHidden(true);
			CommonMessage.debugMsg("colHeader.length"+colHeader.length);
			colIndex = colHeader[i].replaceAll(" ", "").replace("-", "")+i ;
			
			jqGridTableModel.getColModel().add(getColModel( colIndex,100,"right", false,true,false));
		}
		
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;*/
	}
	/*private JqGridColModel getColModel (String colIndex, int width,String allign,boolean hidden,boolean groupbyfield,boolean key)
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
			jqGridColModel.setSummaryTpl("<b><font >  {0} </font> </b>");
		}
		return jqGridColModel;
	}*/
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getPCS(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
	private List<String[]> transposeListArr(List<String[]> dataList)
	{
		if( dataList.size() <=0 ) return null;
		//Object[] pcsRptArr = dataList.toArray();
		
		List<String[]> transposeList = new ArrayList<String[]>();
		
		for( int i =0; i<dataList.get(0).length; i++)
		{	
			String [] tRow = new String [ dataList.size()];
			for (int j=0; j<dataList.size();j++)
			{
				tRow [ j ]= dataList.get(j)[i];

			}
			transposeList.add(tRow);
		}
		return transposeList;
	}

}
				
