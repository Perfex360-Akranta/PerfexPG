package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
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
import com.akranta.tpm.service.ProductionLossSummaryService;
//import com.akranta.tpm.service.impl.ENTProgFBServiceImpl;
import com.akranta.tpm.service.impl.ProductionLossSummaryServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class ProductionLossSummaryServletRpt extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	ProductionLossSummaryService productionLossSummaryService;
	
	public ProductionLossSummaryServletRpt() throws Exception
	{
		super();		
		//productionLossSummaryService = new ProductionLossSummaryServiceImpl();
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
		String action = UIUtils.getActionPart(request);
		try {
			productionLossSummaryService = (ProductionLossSummaryServiceImpl)UIUtils.getServiceObject(request,"ProductionLossSummaryServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		if( action.equals("filterXmlProductionLossSummary_input.ProdLossSmry"))
		{
			
			response.setContentType("xml"); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/ProductionLossSmry.xml");
		}	

		if(action.equals("ProductionLossSummary_input.ProdLossSmry"))
		{
			String filterString = request.getParameter("actiopart");
			String frmPcsRpt = request.getParameter("frmPcsRpt");
			String fromBackPcs = null;
			if(!UIUtils.isValidKeyId(filterString))
			{
				filterString = request.getParameter("filterString");
				fromBackPcs = "true";
			}
			else
			{
				 fromBackPcs = "false";
			}
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
			CommonMessage.debugMsg(toDate +" :: 111111111 ::: "+ filterString );
			CommonMessage.debugMsg(factId +" :: 111111111 ::: "+ sectId );
			request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/ProductionLossSummary.jsp");  
			request.setAttribute("filterStr", filterString);
			request.setAttribute("hdnfactId", factId);
			request.setAttribute("hdnshftId", shift);
			request.setAttribute("hdncellId", cellId);
			request.setAttribute("hdnsectId", sectId);
			request.setAttribute("hdndateId", entryDate);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
			request.setAttribute("fromBackPcs",fromBackPcs);
			String fromdate = CommonFunctions.getDate().substring(3,11);
			String todate = CommonFunctions.getDate().substring(3,11);
			request.setAttribute("fromdate", fromdate);
			request.setAttribute("todate", todate);
			request.setAttribute("frmPcsRpt", frmPcsRpt);
			rd.forward(request, response); 
		}
		else if(action.equals("ProductionLossSummary_getCol.ProdLossSmry"))
		{	
			/*CommonMessage.debugMsg("Production Loss Summary getCol");
			
			HttpSession httpSession = request.getSession(false);
			CommonMessage.debugMsg("Inside the getCol");
			PrintWriter out = response.getWriter();
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ProductLossSummary", "ProdLossSmry");
			
			out.println(colModel);
			CommonMessage.debugMsg("OutSide the getCol"+colModel);
			CommonMessage.debugMsg("OutSide the getCol");
		
			httpSession.removeAttribute("ProductionLossSummaryColModel");
			httpSession.setAttribute("ProductionLossSummaryColModel", colModel);*/
			
			PrintWriter out = response.getWriter();
			
			CommonFilter commonFilter = new CommonFilter();
			HttpSession httpSession = request.getSession(false);
			String firstClick = request.getParameter("firstClick");
			
			String chdVal = request.getParameter("chdVal");
			
			commonFilter = populateCommonFilter(request,"ProdLossSmryCommonFilter",true);
			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				 commonFilter = (CommonFilter) httpSession.getAttribute("ProdLossSmryCommonFilter");
			}
			
			if (commonFilter == null)
				commonFilter = new CommonFilter();
			
			
			
			CommonMessage.debugMsg("Is need Product....."+commonFilter.getISNEEDPROD());
			
			httpSession.removeAttribute("ProdLossSmryCommonFilter");
			httpSession.setAttribute("ProdLossSmryCommonFilter", commonFilter);
			//FilterValues.getCommonFilters(request, commonFilter);
			//FilterValues.getPCS(request, commonFilter);
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(""))&& firstClick.equals("Y") ){
				 // commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				 // commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
				  commonFilter.setRemoveBlank("Y");
		 	  }
			else if(Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N"))) 
			{
				//commonFilter.setFromDate("01"+CommonFunctions.getFirstDateofMonth(-1).substring(3, 11));
				//commonFilter.setToDate(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("N");
				commonFilter.setISFORGRAPH('N');
				commonFilter.setRemoveBlank("Y");
			}
			commonFilter.setISFORGRAPH('N');
			
			if(!UIUtils.isValidKeyId(commonFilter.getISNEEDPROD()))
				commonFilter.setISNEEDPROD("Y");
			if(!UIUtils.isValidKeyId(commonFilter.getISNEEDWONO()))
				commonFilter.setISNEEDWONO("Y");
			
			commonFilter.setWostatus(chdVal);//for chk type machine or loss
			List<String []> defectMatrixList  = productionLossSummaryService.getProductionLossSummaryRpt(commonFilter);
			//JSONObject impVscomData = UIUtils.convertToJqGridTableObject(defectMatrixList, request, 0, 0);
			JSONObject jsonObject = null;
			if("Mach".equals(chdVal)){
				List<String[]> prodLossSumryGridData =  transposeListArr(defectMatrixList);
				 jsonObject = getTableModel(prodLossSumryGridData,commonFilter,chdVal);
			}
			else{
				 jsonObject = getTableModel(defectMatrixList,commonFilter,chdVal);
			}
	
			httpSession.removeAttribute("ProductionLossSummaryColModel");
			httpSession.setAttribute("ProductionLossSummaryColModel", jsonObject);
			
			httpSession.removeAttribute("ProdLossSmryCommonFilter");
			httpSession.setAttribute("ProdLossSmryCommonFilter", commonFilter);
			
			out.println(jsonObject);
			
		}
		else if( action.equals("ProductionLossSummary_getData.ProdLossSmry") )
		{
			try
			{
				HttpSession httpSession = request.getSession(false);
				int skipRow = 2;
				CommonFilter commonFilter = populateCommonFilter(request,"ProdLossSmryCommonFilter",false);
				String chdVal = request.getParameter("chdVal");
				if(!UIUtils.isValidKeyId(commonFilter.getISNEEDPROD()))
					commonFilter.setISNEEDPROD("Y");
				if(!UIUtils.isValidKeyId(commonFilter.getISNEEDWONO()))
					commonFilter.setISNEEDWONO("Y");
				commonFilter.setWostatus(chdVal);//for chk type machine or loss
				commonFilter.setToRow("150");
				
				List<String []> defectMatrixList  = productionLossSummaryService.getProductionLossSummaryRpt(commonFilter);
				
  			 	PrintWriter out = response.getWriter();
  			 	JSONObject pmstandardData =null;
  			 
  			 	
  			 	//if(commonFilter.getTotalRecordCnt() == 2)
  			 	//	skipRow = 1;
  			 	
  			 	if("Mach".equals(chdVal)){
  			 		List<String[]> prodLossSumryGridData = null;
  			 		int rowStart = 0;
	  			 	//List<String[]> prodLossSumryGridData =  transposeListArr(defectMatrixList);
  			 		if("Y".equals(commonFilter.getRemoveBlank())){

  			 			 prodLossSumryGridData =UIUtils.transposeListArr(defectMatrixList,"0,-",1,defectMatrixList.size(),0);

  			 			rowStart = 3;
  			 		}
  			 		else{
  			 			prodLossSumryGridData =transposeListArr(defectMatrixList);
  			 			rowStart = 2;
  			 		}
	  			 	commonFilter.setTotalRecordCnt(prodLossSumryGridData.size());
	  			 	pmstandardData = UIUtils.convertToJqGridTableObject(prodLossSumryGridData, request, rowStart,0,commonFilter.getTotalRecordCnt());
  			 	}
  			 	else{
  			 		pmstandardData = UIUtils.convertToJqGridTableObject(defectMatrixList, request, 1,0,commonFilter.getTotalRecordCnt()+2);
  			 	}
  			 	
  			 	out.println(pmstandardData);  			 	
  			 	httpSession.removeAttribute("ProdLossSmryCommonFilter");
  			 	httpSession.setAttribute("ProdLossSmryCommonFilter", commonFilter);

		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}	
		
	
		else if( action.equals("ProductionLossSummary_getExcel.ProdLossSmry")){
			
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("ProdLossSmryCommonFilter");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("ProductionLossSummaryColModel");
			
			
			tblJSONObj.put("title", "Production Loss Summary");
			if("Mach".equals(commonFilter.getWostatus())){
				CommonMessage.debugMsg("machine wise...");
				 tblJSONObj.put("transpose", true);
				// UIUtils.removeBlankRowExcel(commonFilter, tblJSONObj, 3, -1, "0,-,''");
				 
			}
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = productionLossSummaryService.getProdLossSmryExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "ProductionLossSummary", format);
			
		}
	
	}	

private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		
		//if(!UIUtils.isValidKeyId(commonFilter.getISNEEDPROD()))
			
		
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			CommonMessage.debugMsg("fromDate123....."+commonFilter.getFromMonth());
			commonFilter = 	FilterValues.getCommonFilters(request,commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getPCS(request, commonFilter);
			
			CommonMessage.debugMsg("fromDate....."+commonFilter.getFromMonth());
			if(commonFilter.getISNEEDPROD() != null)
			{
				
			}
			else
				commonFilter.setISNEEDPROD("Y");
			
			if(commonFilter.getISNEEDWONO() != null)
			{}
			else
				commonFilter.setISNEEDWONO("Y");
			CommonMessage.debugMsg("123"+commonFilter.getISNEEDPROD());
			
			
			
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}

		return commonFilter;
	}

private JSONObject getTableModel(List<String[]> headers,CommonFilter commonFilter, String chdVal) {
	JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	String  chktype = commonFilter.getWostatus();//for chk type machine or loss
	int val = 0;
	if(UIUtils.isValidKeyId(chktype)){
		if(chktype.equals("Mach"))
			val = 1;
	}
	String [] colHeader = headers.get(val);	
	
	jqGridTableModel.getRowHeaders().add(colHeader);	
	//jqGridTableModel.setRowNumbers(true);
	CommonMessage.debugMsg("123333.................."+colHeader.length);
	for(int i =0; i < colHeader.length; i++)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
		jqGridTableModel.setTableButton(true);		
	//	jqGridTableModel.setRowNumbers(true);
		
		
		if(!"Mach".equals(chdVal)){
			
			jqGridTableModel.setGroupBy(true);
			
			jqGridTableModel.setGroupByField("MachineNo");
			CommonMessage.debugMsg(colHeader[i] +"  ~@~@~@  "+i);
			if(i==3){
				CommonMessage.debugMsg(colHeader[i] +"  ------  "+i);
				jqGridColModel.setWidth(80);
			}
			if(i==4){
				jqGridColModel.setHidden(true);
			}
			if(i==5)
			{
				jqGridColModel.setWidth(150);
			}
			else if(i==7)
			{
				jqGridColModel.setWidth(150);
			}
			if(i>=7 && i<= colHeader.length){
				jqGridColModel.setAlign("right");
				if(colHeader[i].equals("Measuring and Adjustment"))
					jqGridColModel.setWidth(200);
			}
			else{
				jqGridColModel.setAlign("left");
				jqGridColModel.setWidth(80);	
			}
		}
		else{
			
			if(i==5)
			{
				jqGridColModel.setWidth(50);				
				//jqGridColModel.setAlign("left");
			}
			if(i>=1 && i<= colHeader.length){

				jqGridColModel.setAlign("right");
				jqGridColModel.setWidth(50);
			}
			else{
				jqGridColModel.setAlign("left");
				jqGridColModel.setWidth(250);
			}
			
		}
		
		
		jqGridColModel.setEditable(false);
		
		
		
		if(i==4)
		{
			jqGridColModel.setWidth(50);				
			//jqGridColModel.setAlign("left");
		}
		
		if(i==6)
		{
			jqGridColModel.setWidth(50);				
			//jqGridColModel.setAlign("left");
		}
		
		else if(i==9)
		{
			jqGridColModel.setWidth(50);				
		//	jqGridColModel.setAlign("left");
		}
		/*if("Mach".equals(chktype)){
			if(i==0)
				jqGridColModel.setHidden(true);
		}*/
		if(colHeader[i].equals("1") || colHeader[i].trim().equals(""))
		{
			jqGridColModel.setHidden(true);
			jqGridColModel.setKey(true);
		}
			
		else if(colHeader[i].equals("Date") )  
		{
			jqGridColModel.setWidth(200);				
			jqGridColModel.setAlign("center");
		
		}
		else if(colHeader[i].trim().equals("MachineNo[MachineName]") || colHeader[i].equals("Product")
				|| colHeader[i].equals("MachineNo[SetupAndAdjustment]"))
		{
			jqGridColModel.setWidth(200);				
			//jqGridColModel.setAlign("left");
		}
		else if(colHeader[i].trim().equals("ProductCode") )
		{
			jqGridColModel.setWidth(250);				
			//jqGridColModel.setAlign("left");
		}
		else if(colHeader[i].equals("MinorStoppage") || colHeader[i].equals("WorkOrderNo") )
		{
			jqGridColModel.setWidth(250);				
			//jqGridColModel.setAlign("left");
		}
		else if( colHeader[i].equals("AvailableTime") 
				|| colHeader[i].equals("AvailableTime") || colHeader[i].equals("EquipmentFailure")
				|| colHeader[i].equals("ShutdownLoss") || colHeader[i].equals("ManagementLoss") 
				|| colHeader[i].equals("OperatingMotion") || colHeader[i].equals("LineOrganizational")
				|| colHeader[i].equals("Logistics") || colHeader[i].equals("list_Die,ToolandJig")
				|| colHeader[i].equals("OperatingTime") || colHeader[i].equals("StandardCycleTime")
				|| colHeader[i].equals("ActualCycleTime")|| colHeader[i].equals("NoofCavity") 
				|| colHeader[i].equals("AcceptedQuantity")|| colHeader[i].equals("RejectedQuantity") )
		{
			jqGridColModel.setWidth(200);				
			//jqGridColModel.setAlign("left");
		}
		else if(colHeader[i].equals("WorkOrderQty") || colHeader[i].equals("ToolsChange") 
				|| colHeader[i].equals("StartupLoss") || colHeader[i].equals("SpeedLoss") 
				|| colHeader[i].equals("TotalLoss"))
		{
			jqGridColModel.setWidth(200);				
			//jqGridColModel.setAlign("left");
		}
		else if( colHeader[i].equals("MandA"))
		{
			jqGridColModel.setWidth(80);				
			//jqGridColModel.setAlign("left");
		}
		/*else if(colHeader[i].equals("Shift"))
		{
			String cellVal = jqGridColModel.getCellattr();
			CommonMessage.debugMsg("Cell val=..........."+cellVal);
			jqGridColModel.setCellattr("Shift - "+cellVal);
			jqGridColModel.setWidth(80);				
			jqGridColModel.setAlign("left");
		}*/
		
		
		/*else if(i==3)
		{
			jqGridColModel.setWidth(100);				
			jqGridColModel.setAlign("center");
		}
		else if(i==4 ){
			jqGridColModel.setWidth(300);
			jqGridColModel.setAlign("left");
		}
		
		if(commonFilter.getISNEEDPROD()=="Y" )
		{
			if(i==5 || i==6 || i==7)
			{
				jqGridColModel.setWidth(150);				
				jqGridColModel.setAlign("left");
			}
		}
		else if(i==9|| i==11 || i==13|| i==14||i==17)
		{
			jqGridColModel.setWidth(150);				
			jqGridColModel.setAlign("right");
		}
		else if(i==12||i==15 || i==18)
		{
			jqGridColModel.setWidth(120);				
			jqGridColModel.setAlign("right");
		}

		else if(i==5 || i==8 || i==7 || i==10)

		{
			jqGridColModel.setWidth(100);				
			jqGridColModel.setAlign("right");
		}
		else if(i==16){
			jqGridColModel.setWidth(100);				
			jqGridColModel.setAlign("right");
		}
		else if(i==20){
			jqGridColModel.setWidth(130);				
			jqGridColModel.setAlign("right");
		}
		else
		{
			jqGridColModel.setWidth(80);				
			jqGridColModel.setAlign("right");
	
		}*/
		
		jqGridTableModel.getColModel().add(jqGridColModel);
	}
	 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);	
	
	 String shiftVal = commonFilter.getPcsShift();
	 String pcsDate = commonFilter.getPcsDate();
	
		 tableModel.set("tableHeight", "90%%");
		 
		 if(shiftVal != null && shiftVal.equals("Y"))
		 {
			 tableModel.set("groupByField","Shift");
			 tableModel.set("groupBy", true);
		 }
		  if(pcsDate != null && pcsDate.equals("Y"))
		 {
			 tableModel.set("groupByField","Date");
			 tableModel.set("groupBy", true);
		 }
	
	CommonMessage.debugMsg(" tableModel " + tableModel);
	 return tableModel;
	}
private List<String[]> transposeListArr(List<String[]> dataList)
{
	if( dataList.size() <=0 ) return null;
	//Object[] pcsRptArr = dataList.toArray();
	
	List<String[]> transposeList = new ArrayList<String[]>();
	
	for( int i =1; i<dataList.get(0).length; i++)
	{	
		String [] tRow = new String [ dataList.size()];
		for (int j=0; j<dataList.size();j++)
		{
			tRow [ j ]= dataList.get(j)[i];

		}
		
		transposeList.add(tRow);
	}
	CommonMessage.debugMsg(" transposeList" + transposeList.size() + " dataList.get(0).length " + dataList.get(0).length);
	return transposeList;
}
}
