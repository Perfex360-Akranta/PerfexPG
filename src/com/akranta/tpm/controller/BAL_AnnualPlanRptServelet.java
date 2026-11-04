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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_MonthlyPlanService;
import com.akranta.tpm.service.impl.AdmTlAlertsServiceImpl;
import com.akranta.tpm.service.impl.BAL_MonPlanServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_AnnualPlanRptServelet extends HttpServlet {
	
	
	BAL_MonthlyPlanService monPlanService;
  public BAL_AnnualPlanRptServelet() {
	  super();
		try {
		//	monPlanService = new MonPlanServiceImpl();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
    
    
    /**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
try {
	processRequest(request, response);
} catch (Exception e) {
	// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
try {
	processRequest(request, response);
} catch (Exception e) {
	// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
   throws Exception {
	   
      HttpSession httpSession = request.getSession(false);
      
      try {
    	  monPlanService = (BAL_MonPlanServiceImpl)UIUtils.getServiceObject(request,"BAL_MonPlanServiceImpl");
    	  
    	  HttpSession h = request.getSession(false);

    	  monPlanService.BAL_MonPlanServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
	   } catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
	   }
		
      String action = UIUtils.getActionPart(request);
	  
	  if( action.equals("filterXmlAnnualcalendar_input.acl")){
			 response.setContentType("xml"); 
			System.out.println("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/AnnualCalender.xml") ;
	   }
	   else if( action.equals("Annualcalendar_input.acl") ){	
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
			
			CommonFunctions.debugMsg(factId +" :: 111111111 ::: "+ sectId );
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/AnnualPlan.jsp"); 
			
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
	   else if( action.equals("Annualcalendar_getCol.acl") )
	   {
		   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);
		   
		  /* if( !UIUtils.isValidKeyId(commonFilter.getYear()) ){
				 commonFilter.setFromMonth("Jan"+CommonFunctions.getFirstDateofMonth(1).substring(6,11));
				 commonFilter.setToMonth("Dec"+CommonFunctions.getDate().substring(6,11));
				  commonFilter.setMonwise("Y");
				  CommonFunctions.debugMsg("YearYearYearYearYear  :"+CommonFunctions.getDate().substring(6,11));
		   }*/
			 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				 commonFilter.setFromMonth("Jan"+CommonFunctions.getFirstDateofMonth(1).substring(6,11));
				 commonFilter.setToMonth("Dec"+CommonFunctions.getDate().substring(6,11));
				  commonFilter.setMonwise("Y");
		 	  }
		   else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
					  commonFilter.setFromDate("01-Jan"+CommonFunctions.getFirstDateofMonth(1).substring(6,11));
					  commonFilter.setToDate("31-Dec"+CommonFunctions.getDate().substring(6,11));
					 // commonFilter.setMonwise(" ");
			 	  }
	   if(UIUtils.isValidKeyId(commonFilter.getYear()) ){
		   
		   commonFilter.setFromMonth("Jan-"+commonFilter.getYear());		  
	       commonFilter.setToMonth("Dec-"+commonFilter.getYear()); 
		   commonFilter.setMonwise("Y");
		  
	   }
	   		commonFilter.setIsGetCol("Y");
		   List<String []> monthlyPlanList  = monPlanService.getMonPlan(commonFilter);
		   for(String[] array:monthlyPlanList) 
	  		 {
	  			 CommonFunctions.debugMsg("Data..."+Arrays.toString(array));
	  		 }
		   
		   JSONObject monthlyPlanData = fillJqGrid(monthlyPlanList,request,2,0,commonFilter);
		  
		   httpSession.setAttribute("monthlyPlanData", monthlyPlanData);						
		   PrintWriter out = response.getWriter();
		   JSONObject jsonObject = getTableModel(monthlyPlanList,commonFilter);				 
		   jsonObject.set("tableHeight", "64%%");
		   jsonObject.set("tableWidth", "108%%");
		   httpSession.removeAttribute("monthCalCol");
		   httpSession.setAttribute("monthCalCol", jsonObject);
		   out.println(jsonObject);
	   }
	   else if( action.equals("Annualcalendar_getData.acl") )
	   {
		   try
			{
			   	 PrintWriter out = response.getWriter();
				 UIUtils.displayRequestParamsValue(request);
				 String page = request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				/* if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-1).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(1).substring(3,11));
					  commonFilter.setMonwise("Y");
			 	 }*/
				 JSONObject jsonObject = new JSONObject();
				 if(page.equals("1"))
	        		 jsonObject = (JSONObject) httpSession.getAttribute("monthlyPlanData");
	        	 else
	        	 {				
	        		 CommonFunctions.debugMsg("Data...");
	        		 commonFilter.setIsGetCol("N");
	        		 List<String[]> monPlan  = monPlanService.getMonPlan(commonFilter);
	        		 for(String[] array:monPlan) 
	        		 {
	        			 CommonFunctions.debugMsg("Data..."+Arrays.toString(array));
	        		 }
	        		 jsonObject = fillJqGrid(monPlan,request,2,0,commonFilter); 
	        	 }
				
				 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			 httpSession.removeAttribute("MonthlyPlanCommonFilter");
	  			 httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);

		    }
			catch(Exception e)
			{
				 CommonFunctions.debugMsg(e.getMessage());
			}
	   }
	   else if( action.equals("Annualcalendar_getExcel.acl")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
			 String tradeId=request.getParameter("hdncmbTradeId");
			String jobType=request.getParameter("hdncmbJobType");
			if(UIUtils.isValidKeyId(tradeId))
		    	commonFilter.setTrarId(tradeId);
		    ComboFilter jobTypeTrade  = new ComboFilter();
		    if(UIUtils.isValidKeyId(jobType))
		    {
		    	jobTypeTrade.setId(jobType);
		    }
		 	commonFilter.setJobtype(jobTypeTrade);
		 	
			String tmpFromRow = commonFilter.getFromRow();
			//commonFilter.setFromRow(null);
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			
			String month1   = fromMonth +"  -  "+ toMonth ;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate ;
		
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("monthCalCol");
			//tblJSONObj.put("title", "Monthly Calender" + " - "+date);
			//if( commonFilter.getMonwise().equals("Y")){
			//	tblJSONObj.put("title", "Monthly Calender" + " - "+month1);
			//}
			//tblJSONObj.put("transpose",true); 
			//tblJSONObj.put("title", "Production Loss Analysis Report");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = monPlanService.getMonPlanExcel(commonFilter,tblJSONObj,format);
			//commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "AnnualCalender", format);
			
		}

}
private JSONObject getTableModel(List<String[]> headers,CommonFilter commonFilter)
{
	JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	String [] monthArr = headers.get(0);
	String [] weekArr = headers.get(1);
	int colFlag = monthArr.length -1;
	int colLength=0;
	CommonFunctions.debugMsg("Count Array "+colFlag);
	String [] colHeader = new String[ colFlag];
	String [] colHeader2 = new String[ colFlag];
	String [] colHeader3 = new String[ colFlag];
	
	colHeader3[0] = colHeader2[0] = " ";
	colHeader3[1] = colHeader2[1] = "Location_id";
	colHeader3[2] = colHeader2[2] = "Unit_id";
	colHeader3[3] = colHeader2[3] = "Section_id";
	colHeader3[4] = colHeader2[4] = "Line_id";
	colHeader3[5] = colHeader2[5] = "Equipment_id";
	colHeader3[6] = colHeader2[6] = "Assembly_id";
	colHeader3[7] = colHeader2[7] = "Location";
	colHeader3[8] = colHeader2[8] = "Factory";
	colHeader3[9] = colHeader2[9] = "Section";
	colHeader3[10] = colHeader2[10] = "Line";
	colHeader3[11] = colHeader2[11] = "Equipment";
	colHeader3[12] = colHeader2[12] = "Equipment No.";
	colHeader3[13] = colHeader2[13] = "Assembly";
	
	
	colHeader[0] = colHeader[1]=colHeader[2] = colHeader[3]=colHeader[4] =colHeader[5]=colHeader[6] = colHeader[7]=colHeader[8] = colHeader[9]=colHeader[10] = colHeader[11]=colHeader[12]=colHeader[13]="";
	List<JqGridColModel>jqGridColModelList =new ArrayList<JqGridColModel>();
	JqGridColModel jqGridColModel =getColModel("sno",40,"left");
	
	//JqGridColModel jqGridColModel =getColModel("sno",40,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);
	jqGridColModel =getColModel("location_id",80,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("factory_id",60,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("line_id",150,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("cell_id",150,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("machine_id",150,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("assembly_id",80,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("Location",80,"left");
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("factory",60,"left");
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("line",150,"left");
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("cell",150,"left");
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("machine",150,"left");
	jqGridColModelList.add(jqGridColModel);
	jqGridColModel =getColModel("machine_no",80,"left");
	jqGridColModelList.add(jqGridColModel);
	jqGridColModel =getColModel("assembly",80,"left");
	jqGridColModelList.add(jqGridColModel);
	colLength=14;
	
//	jqGridTableModel.getRowHeaders().add(colHeader);

	for(int i =colLength; i < monthArr.length-1; i++)
	{
		

		if(i!= monthArr.length-2 || i != monthArr.length-3)
			jqGridColModelList.add(getColModel(weekArr[i]+monthArr[i],40,"center"));
		else
		{
			jqGridColModel =getColModel("Key"+i,80,"left");
			jqGridColModel.setHidden(true);
		}
		colHeader[i] = "";
		colHeader2[i] = monthArr[i];
		colHeader3[i] = weekArr[i];				
	}
	
	jqGridTableModel.setTableButton(true);		 
	//jqGridTableModel.setTableHeight(290);
	//jqGridTableModel.setTableWidth(800);
	jqGridTableModel.setRowNumbers(true);

	jqGridTableModel.setColModel(jqGridColModelList);
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.getRowHeaders().add(colHeader2);
	jqGridTableModel.getRowHeaders().add(colHeader3);		

	
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableWidth","110%%");
	tableModel.set("tableHeight","79%%");

	return tableModel;
	
	
	/*JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	String [] monthArr = headers.get(0);
	String [] weekArr = headers.get(1);
	int colFlag = monthArr.length -1;
	
	String [] colHeader = new String[ colFlag];
	String [] colHeader2 = new String[ colFlag];
	String [] colHeader3 = new String[ colFlag];
	
	colHeader3[0] = colHeader2[0] = " ";
	colHeader3[1] = colHeader2[1] = "Location_id";
	colHeader3[2] = colHeader2[2] = "Unit_id";
	colHeader3[3] = colHeader2[3] = "Section_id";
	colHeader3[4] = colHeader2[4] = "Line_id";
	colHeader3[5] = colHeader2[5] = "machine_id";
	colHeader3[6] = colHeader2[6] = "assembly_id";
	colHeader3[7] = colHeader2[7] = "Location";
	colHeader3[8] = colHeader2[8] = "Factory";
	colHeader3[9] = colHeader2[9] = "Section";
	colHeader3[10] = colHeader2[10] = "Line";
	colHeader3[11] = colHeader2[11] = "Equipment";
	colHeader3[12] = colHeader2[12] = "Assembly";

	colHeader[0] = colHeader[1]=colHeader[2] = colHeader[3]=colHeader[4] =colHeader[5]=colHeader[6] = colHeader[7]=colHeader[8] = colHeader[9]=colHeader[10] =colHeader[11]=colHeader[12]="";
	List<JqGridColModel>jqGridColModelList =new ArrayList<JqGridColModel>();
	JqGridColModel jqGridColModel =getColModel("sno",20,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("location_id",60,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("factory_id",60,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("line_id",150,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("cell_id",150,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("machine_id",150,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("assembly_id",80,"left");
	jqGridColModel.setHidden(true);
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("Location",60,"left");
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("Factory",60,"left");
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("Line",150,"left");
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("Cell",150,"left");
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("Equipment",150,"left");
	jqGridColModelList.add(jqGridColModel);	
	jqGridColModel =getColModel("Assembly",80,"left");
	jqGridColModelList.add(jqGridColModel);	
	

	int colStart = 13;
	for(int i = colStart; i < monthArr.length-1; i++)
	{		
		if(i!= monthArr.length-2 || i != monthArr.length-3)
			jqGridColModelList.add(getColModel(weekArr[i]+monthArr[i],20,"center"));
		else
		{
			jqGridColModel =getColModel("Key"+i,60,"left");
			jqGridColModel.setHidden(true);
		}
		colHeader[i] = "";
		colHeader2[i] = monthArr[i];
		colHeader3[i] = weekArr[i];				
	}
	
	jqGridTableModel.setTableButton(true);		 
	//jqGridTableModel.setTableHeight(220);
	//jqGridTableModel.setTableWidth(800);
	jqGridTableModel.setRowNumbers(true);

	jqGridTableModel.setColModel(jqGridColModelList);
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.getRowHeaders().add(colHeader2);
	jqGridTableModel.getRowHeaders().add(colHeader3);		

	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("TableWidth","40%%");
	tableModel.set("TableHeight","100%%");
	
	return tableModel;*/
}

private JqGridColModel getColModel (String colIndex, int width,String allign)
{
	JqGridColModel jqGridColModel = new JqGridColModel();
	jqGridColModel.setIndex(colIndex);
	jqGridColModel.setName(colIndex);
	jqGridColModel.setWidth( width);				
	jqGridColModel.setAlign(allign);
	jqGridColModel.setEditable(false);
	//jqGridColModel.setFormatter("actionformatter_"+colIndex);			
	return jqGridColModel;
}

private  JSONObject fillJqGrid(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, CommonFilter comFilter)
{
	String rowsStr = request.getParameter("rows");
	String pageStr = request.getParameter("page");
	
	int rows = 100;
	if( rowsStr != null)
		rows = Integer.parseInt(rowsStr);
	
	int page = 1;
	if( pageStr != null)
		page = Integer.parseInt(pageStr);

	JSONObject tableDataObject = new JSONObject();
	
	
	
	tableDataObject.put("page", page); //current page
	tableDataObject.put("total",Math.ceil(comFilter.getTotalRecordCnt()/rows)==0?1:(Math.ceil(comFilter.getTotalRecordCnt()/rows)+1)); // total page
	tableDataObject.put("records", comFilter.getTotalRecordCnt()); //total records
	
	JSONArray rowArr = new JSONArray(); 

	int rowId = rows*page - rows;
	int rowStartFlag = rowId + 2;
	
	//int rowId = 0;		
	
    for( String [] datasRow : dataArrayList)
	{
    	JSONObject rowObj =new JSONObject();
		JSONArray cell=new JSONArray();
		if( rowId >= rowStartFlag )
        {	
        	rowObj.put("id",rowId -rowStart +1);
    		for( int i = colStart ;i < datasRow.length-1; i++)
        	{	if(i>12)
        		 //datasRow[i] = datasRow[i].replace("1", "&#10003").replace("2", "IC").replace("3", "A").replace("0", " ").replace("1","C").replace("4", "X");	
        		datasRow[i] = datasRow[i].replace("2", "IC").replace("3", "A").replace("0", " ").replace("1","C").replace("4", "X");
    			 cell.put(  ( datasRow[i] != null ? datasRow[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", "").replace("(", "").replace(")", ""):" ") );
        	}
    		rowObj.put("cell",cell);
	    	rowArr.put(rowObj);
        }
    	rowId++;
	}
    tableDataObject.put("rows", rowArr);
    
    return tableDataObject;
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
