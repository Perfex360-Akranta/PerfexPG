/* Mod:N.Arun
 * Dt:29.2.12
 * */
package com.akranta.tpm.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.ColumnChart;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.PcsRptService;
import com.akranta.tpm.service.impl.PcsRptServiceImpl;
import com.akranta.tpm.service.impl.PcsSmryRptServiceImpl;



	public class PcsRptServlet extends HttpServlet{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		//CommonFilter commonFilter;
		PcsRptService pcsRptService;
		
		public PcsRptServlet() throws Exception{
			super();
			
			//pcsRptService = new PcsRptServiceImpl();
		}

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
		{ 
			try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
		{
			try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void planVsRejInput(HttpServletRequest request, HttpServletResponse response) throws Exception 
		{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/PlanVsRej.jsp"); 				
			rd.forward(request, response);
		}
		
		public void planVsRejgetCol(HttpServletRequest request, HttpServletResponse response){
			try
			{
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				CommonFilter  commonFilter = null;		
				String firstClick =request.getParameter("firstClick");		
				
				
				if(commonFilter==null)
					commonFilter = new CommonFilter();
				
				FilterValues.getPCS(request, commonFilter);
				
				if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 					
					commonFilter =(CommonFilter) httpSession.getAttribute("planVsRejCommonFilter");
				}	
				
				FilterValues.getCommonFilters(request,commonFilter);
				
				
				httpSession.removeAttribute("planVsRejCommonFilter");
				httpSession.setAttribute("planVsRejCommonFilter", commonFilter);
				 
				List<String[]> planVsRejList;
				
				planVsRejList  = pcsRptService.getPlanVsRejData(commonFilter);
				JSONObject colModel = getTableModel1(planVsRejList);
				 colModel.set("tableHeight", "70%%");
				 colModel.set("tableWidth", "106%%");
				 
				 httpSession.removeAttribute("planVsRejColModel");
				 httpSession.setAttribute("planVsRejColModel", colModel);
				 CommonMessage.debugMsg("COL.."+colModel);
				 out.println(colModel);
				
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		private JSONObject getTableModel1(List<String[]> headers) {
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String[] row =headers.get(1);
			String [] colHeader = headers.get(1);
			String [] colHeader1 = headers.get(2);
			String[] tempCol = new String[row.length];
			
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setTableHeight(350);
			jqGridTableModel.setRowNumbers(true);
			
			for(int i =0; i < colHeader.length; i++)
			{
				tempCol[i]="";
				JqGridColModel jqGridColModel = new JqGridColModel();
				//jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
				//jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
				
				jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
						
				jqGridColModel.setWidth( 100);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				if(i==0 )
				{
					jqGridColModel.setHidden(true);
					jqGridColModel.setKey(true);
				}
				
				if(i==1)
				{
					jqGridColModel.setHidden(true);
					jqGridColModel.setWidth(300);	
					jqGridColModel.setAlign("left");
				
				}
				
				if(i==2)
				{
					jqGridColModel.setHidden(true);
					jqGridColModel.setWidth(300);	
					jqGridColModel.setAlign("left");
				
				}
				
				if(i==3)
				{
					jqGridColModel.setWidth(300);	
					jqGridColModel.setAlign("left");
				
				}
				
				else{
					jqGridColModel.setWidth(90);
					jqGridColModel.setAlign("right");
				}
				
				jqGridTableModel.getColModel().add(jqGridColModel);
			}
			jqGridTableModel.getRowHeaders().add(tempCol); 
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.getRowHeaders().add(colHeader1);

			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 return tableModel;
		}

		public void planVsRejgetData(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			try
			{
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(false);
				
				CommonFilter commonFilter = populateCommonFilter(request,"planVsRejCommonFilter",false);
				//UIUtils.displayRequestParamsValue(request);
				List<String[]> PlanVsRejList;
				
				PlanVsRejList  = pcsRptService.getPlanVsRejData(commonFilter);
  				
				
				JSONObject PlanVsRejData = UIUtils.convertToJqGridTableObject(PlanVsRejList,request,3,0); 
				out.println(PlanVsRejData);
  			 		
				 httpSession.removeAttribute("planVsRejCommonFilter");
				 httpSession.setAttribute("planVsRejCommonFilter", commonFilter);
		 	
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		public void planVsRejgetExcel(HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"planVsRejCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("planVsRejColModel");
			
			tblJSONObj.put("title", "Production vs Line Rejection Report");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = pcsRptService.PlanVsRejExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "ProductionVsLineRejectionReport", format);

		}
		
		public void processPlanVsRejChart(HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			HttpSession httpSession = request.getSession(false);
			
			CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("planVsRejCommonFilter");
			commonFilter.setISFORGRAPH('Y');
			CommonFilter chrtCommonFilter = new  CommonFilter();
			chrtCommonFilter.setISFORGRAPH('Y');
			BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
			FilterValues.getCommonFilters(request, chrtCommonFilter) ;
			chrtCommonFilter.setISFORGRAPH('Y');
			if(chrtCommonFilter.getRowTotal() == null )
				chrtCommonFilter.setRowTotal('N');
				
					
			JSONObject chartObj = null;
			
			String rownum = request.getParameter("rowid");
	 		CommonMessage.debugMsg("Line Chart ..."+rownum);
			List<String[]> planVsRejProcessList  = pcsRptService.getPlanVsRejData(chrtCommonFilter);
			chartObj = processPlanVsRejLineChart(planVsRejProcessList,chrtCommonFilter,rownum);
			 
			chrtCommonFilter = null;
			
			PrintWriter out = response.getWriter();
			out.print(chartObj);
			out.close();
		}
		
		private JSONObject processPlanVsRejLineChart(List<String[]> planVsRejList,CommonFilter commonFilter, String rownum){
			
			if( planVsRejList == null || planVsRejList.size() <= 1  )
				return null;
			
			ChartOptionBean lineChart =  new ChartOptionBean();
			List<String> xAxisCategory = new ArrayList<String>();
			List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
			List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
			
			String title = "Production VS Line Rejection";
			
			String [] header =  planVsRejList.get(0);
			String [] month =  planVsRejList.get(1);
			String [] data =  planVsRejList.get(Integer.parseInt(rownum)+2);
			
			String prevMonth = null;
			
			String subTitle = data[0];
						
			ChartSeries  lineChart0 = new ChartSeries();
			List<Double> lineDataList = new ArrayList<Double>();
			
			ChartSeries lineChart1 = new ChartSeries();
			List<Double> contractList = new ArrayList<Double>();
			
			
			for( int i = 4;i < header.length-2;i++ ){
				if( header[i].contains("P")){
					lineDataList.add(Double.parseDouble(data[i]));
				}
				else if(header[i].contains("A") ){
					contractList.add(Double.parseDouble(data[i]));
				}
		
			 	if( prevMonth == null || ! month[i].equals(prevMonth) ){
					xAxisCategory.add(month[i]);
				}
				prevMonth = month[i];
			}
		
			if( lineDataList.size() > 0){
				lineChart0.setData(lineDataList);
				lineChart0.setType(ChartTypes.SPLINE);
				lineChart0.setName("P");
				chartSeriesList.add(lineChart0);
		
			}

			if( contractList.size() > 0){
				lineChart1.setData(contractList);
				lineChart1.setType(ChartTypes.SPLINE);
				lineChart1.setName("R");
				chartSeriesList.add(lineChart1);
			}
			
//			List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();//declaring variable for Y axis
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.getTitle().setText("Count");
			chartYAxis.add(yAxis);

			return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
			
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			String action = UIUtils.getActionPart(request);
			CommonMessage.debugMsg("action....."+action);
			try {
				pcsRptService = (PcsRptServiceImpl)UIUtils.getServiceObject(request,"PcsRptServiceImpl");
			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
			}
			HttpSession httpSession = request.getSession(false);
			String dispatchUrl =null;
			
			
			if( action.equals("filterXmlPcsReport_input.pcsrpt")){
				 response.setContentType("xml"); 
				CommonMessage.debugMsg("action "+ action); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/PcsReort.xml") ;
			 }
			
			else if(action.equals("PcsReport_input.pcsrpt")) 
			{	
				String filterString = request.getParameter("filterString");
				String fact = request.getParameter("cmbFactid");
				String flid = request.getParameter("flid");
				String sect = request.getParameter("cmbSectid");
				String cell = request.getParameter("cmbCellid");
				String mchm = request.getParameter("cmbMchid");
				String frmDate = request.getParameter("dtFromDate");
				String toDate = request.getParameter("dtToDate");
				String frmMonth = request.getParameter("dtFromMonth");
				String toMonth = request.getParameter("dtToMonth");
				String pcsfilterString = request.getParameter("DatString");
				String fromLossStratifi= request.getParameter("fromLossStratifi");
				CommonMessage.debugMsg("pcsfilterString  :"+pcsfilterString);
			if (UIUtils.isValidKeyId(frmDate) && UIUtils.isValidKeyId(toDate) ){
					request.setAttribute("hdfrmDate",frmDate);
				request.setAttribute("hdtoDate", toDate);
			}
			if (UIUtils.isValidKeyId(frmMonth) && UIUtils.isValidKeyId(toMonth)) {
				request.setAttribute("hdfrmMonth", frmMonth);
				request.setAttribute("hdtoMonth", toMonth);
			}
			if (UIUtils.isValidKeyId(flid)) {
				request.setAttribute("hdnflid", flid);
			}
			if (UIUtils.isValidKeyId(pcsfilterString)) {

				request.setAttribute("pcsfilterString", pcsfilterString);
			}
			if (!UIUtils.isValidKeyId(fact)) {
					 fact = request.getParameter("factId");
					request.setAttribute("hdfact",fact);
				}
				else
					request.setAttribute("hdfact",fact);
				if(!UIUtils.isValidKeyId(sect)){
					 sect = request.getParameter("sectId");
					request.setAttribute("hdnsect",sect);
				}
				else
					request.setAttribute("hdnsect",sect);
				if(!UIUtils.isValidKeyId(cell)){
					cell = request.getParameter("cellId");
					request.setAttribute("hdncell",cell);
				}
				else
					request.setAttribute("hdcell",cell);
				if(UIUtils.isValidKeyId(mchm))
					request.setAttribute("hdmchm",mchm);
				if(UIUtils.isValidKeyId(fromLossStratifi))
					request.setAttribute("fromLossStratifi",fromLossStratifi);
				
				request.setAttribute("filterStr", filterString);
				CommonMessage.debugMsg("before page load");
				request.setAttribute("viewdata", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","viewdata"));
				RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/PcsRpt.jsp"); 
				rd.forward(request, response); 
				CommonMessage.debugMsg("After page load");
			}
			else if(action.equals("PcsReport_getCol.pcsrpt"))
			{
				 CommonMessage.debugMsg("aaaaaaaaaaaaaaaaaaaaaaa"+request.getParameter("cmbFactid") );
				  PrintWriter out = response.getWriter();
				  CommonFilter commonFilter = populateCommonFilter(request,"pcsReportCommonFilter",true);	
				  
				  if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
					  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
					 // commonFilter.setMonwise(" ");
			 	  }
				  //CommonFilter  commonFilter = new CommonFilter();  
				  //FilterValues.getCommonFilters(request,commonFilter);
				  //FilterValues.getPCS(request, commonFilter);
				/*  
				  commonFilter.setMonwise("N");
				  httpSession.removeAttribute("listToJsonObjec");
				  httpSession.setAttribute("listToJsonObjec", commonFilter);
				  */
				  
				  response.setContentType("text/html");
				  List<String[]> pcsRpt  = pcsRptService.getAllPcsRpt(commonFilter);
				  CommonMessage.debugMsg( commonFilter.getTotalRecordCnt()+"Size : "+pcsRpt.size());
				 // JSONObject pcsData = UIUtils.convertToJqGridTableObject(pcsRpt,request,0,0,commonFilter.getTotalRecordCnt()); 
				  //CommonMessage.debugMsg(pcsData);
				  //httpSession.setAttribute("pcsDataServlet", pcsData);	
				  
				  JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					 GridColModel gridColModel = new GridColModel();
					 
					 gridColModel.setHeaderNum(1);					
					 String [] colHeader = pcsRpt.get(1);			
					 String [] colHeaderCond = pcsRpt.get(0);
						
					 List<String[]> headers = new ArrayList<String[]>();
					 headers.add(colHeader);
					 
					 	jqGridTableModel.setTableButton(true);
						//jqGridTableModel.setTableHeight(330);
						jqGridTableModel.setRowNumbers(true);
						jqGridTableModel.setEnableFilter(true);
					
						
				  JSONObject jsonObject =  UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		 		  //JSONObject jsonObject = getTableModel(pcsRpt);
				  jsonObject.set("tableHeight", "70%%");
				  jsonObject.set("tableWidth", "106%%");
			//	  CommonMessage.debugMsg(jsonObject);
				  out.println(jsonObject);
				
				  
				  /*String colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsRpt", "pcsRpt");
				  JSONObject colModelObj = JSONObject.fromString(colmodel );
				  colModelObj.set("tableHeight", "70%%");
				  colModelObj.set("tableWidth", "106%%");
				  httpSession.removeAttribute("pcsReportColModel");
				  httpSession.setAttribute("pcsReportColModel", colModelObj);
				  out.print(colModelObj);*/
			}
			else if( action.equals("PcsReport_getData.pcsrpt") )
			{
				try
				{
					 PrintWriter out = response.getWriter();
					 //String page = request.getParameter("page");	
					  CommonFilter commonFilter = populateCommonFilter(request,"pcsReportCommonFilter",false);
					  JSONObject jsonObject = new JSONObject();
					
		        		 	List< String  []> pcsRpt  = pcsRptService.getAllPcsRpt(commonFilter);	
				
		        		 	jsonObject =  UIUtils.convertToJqGridTableObject(pcsRpt,request,2,0,commonFilter.getTotalRecordCnt()+2);
		        	
					 out.println(jsonObject);
					 httpSession.removeAttribute("pcsReportCommonFilter");
					 httpSession.setAttribute("pcsReportCommonFilter", commonFilter);
	  			    
			    }catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("PcsReport_getExcel.pcsrpt")){
				
				//HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"pcsReportCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				String date = null;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				if("Y".equals(commonFilter.getMonwise()))
					date   = fromMonth +"  -  "+ toMonth ;	
				else
					date   = fromdate +"  -  "+ todate ;
				
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("pcsReportColModel");
				
				tblJSONObj.put("title", "PCS Report"+ " - "+ date);
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = pcsRptService.pcsExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "PcsReport", format);
				
			}
			else if( action.equals("PcsReport_exlView.pcsrpt") )
			{
				  
				  String rowId = request.getParameter("rowId");
				  String date = request.getParameter("date");
				  String sectId = request.getParameter("sectId");
				  String shift = request.getParameter("shift");
				  String prlmid = request.getParameter("prlmid");
				  
				  String cellId = request.getParameter("cellId");
				  String shiftId = request.getParameter("sftId");
				  String shiftKeyId = request.getParameter("shiftId");
				  String sectid = request.getParameter("secId");
				  
				  CommonFilter  commonFilter = new CommonFilter(); 
				   // commonFilter.setCell("rowId");
				  FilterValues.getCommonFilters(request,commonFilter);
				  FilterValues.getPCS(request, commonFilter);
				 
				  String locn=UIUtils.getlocation(request);
					 CommonMessage.debugMsg("locn..........................."+locn);
				
				try
				{
				 	List<String[]> pcsLists  =  pcsRptService.getAllPcsRptExl1(rowId,date,sectId,prlmid,shiftKeyId,commonFilter,locn);
				  	String path = UIUtils.getExcelTemplatePath(request);				 
				 	if( pcsLists.size() > 0 ){
				 		if(locn.equals("VASAI")){
				 			CommonMessage.debugMsg("Vasai.....................");
				 			ExportExcel(request,response,pcsLists);
				 		}
				 		else
				 		{
				 			String [] pcsexl = pcsLists.get(0);				 			
				 			InputStream inp = new FileInputStream(path+"/ProductionLog1.xls");					      
				    		HSSFWorkbook wb = new HSSFWorkbook(inp) ;				    	
				    		inp.close();
				    		 String format;
				    		 format =".xls";
				    		Sheet sheet = wb.getSheet("Production Entry");  				    	
				    		int k = 3;				    		
				    		for(String[] exclRow : pcsLists)
				    		{
				    			int j=3; 				    			 
				    			 for(int i=0;i<exclRow.length;i++)
				    			 { 
				    				  CommonMessage.debugMsg(i + " : "+exclRow[i]);
				    				  sheet.getRow(j).getCell(k).setCellValue(exclRow[i]);
					    			 
					    			 j++;
				    			 }
				    			 k++;
				    			 CommonMessage.debugMsg("shiftId  "+shiftId);
				    			 sheet.getRow(1).getCell(1).setCellValue(sectid); 
				    			 sheet.getRow(1).getCell(3).setCellValue(cellId); 
				    			 sheet.getRow(2).getCell(1).setCellValue(date); 
				    			 sheet.getRow(2).getCell(3).setCellValue(shiftId); 
				    			
				    			
				    		}
				    	
				   
				  
	  			 
	  			  
	  			    wb.writeProtectWorkbook("admin", "admin");
	  			    sheet.protectSheet("admin");
	  			  
	  	    	    response.setContentType("application/vnd.ms-excel");
		  			response.setHeader("Content-Disposition", "inline; filename=" + "ProductionEntry"+format +";charset=UTF-8");
		  			response.setHeader("Pragma", "no-cache");
				   
		  			ServletOutputStream out = response.getOutputStream();  

			        wb.write(out);
			        out.flush();
			        out.close();
			
				}
				 	
				 	} }catch(Exception e)
					{
						CommonMessage.debugMsg(e.getMessage());
					}
				 
			}
			else if( action.equals("PcsReport_exldetView.pcsrpt") )
			{
				
				try{
					  String rowId = request.getParameter("rowId");
					  String date = request.getParameter("date");
					  String sectId = request.getParameter("sectId");
					  String shiftId = request.getParameter("shiftId");
					  String shift = request.getParameter("sftId");
					  String prlmid = request.getParameter("prlmid");
					  String format = ExcelUtils.getFormat(request);
					  String path = UIUtils.getExcelTemplatePath(request);  	
					  String imagePath = UIUtils.getImagePath(request);
					  format = ".xlsx";
					  Workbook wb = pcsRptService.getAllPcsRptdetailExl(rowId,date,sectId,prlmid,shiftId,format,path,imagePath,shift);
						
			 
					  ExcelUtils.writeToResponse(response, wb, "PCSDetailReport",format );
					}
				
					catch(Exception e)
					{
						CommonMessage.debugMsg("err:"+e.getMessage());
						PrintWriter out = response.getWriter();
						JSONObject err = new JSONObject();				
						//err.put("exception",true);				
						err.put("message" ,"Data Not Found" );
						out.print(err.toString());
					}
					
					
				}
			else if( action.equals("filterXmlPCSPlanVsActual_input.pcsrpt")){
				response.setContentType("xml"); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/PcsPlanVsActual.xml") ;
			}
			else if(action.equals("PCSPlanVsActual_input.pcsrpt"))
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
				
				String sectId = request.getParameter("sectId");
				if(!UIUtils.isValidKeyId(sectId)){
					sectId = request.getParameter("cmbSectid");
				}
				request.setAttribute("filterStr", filterString);
				request.setAttribute("hdnsectId", sectId);
				request.setAttribute("fromBackPcs",fromBackPcs);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/PCSPlanVSActual.jsp");
				rd.forward(request, response); 
			}
			else if(action.equals("PCSPlanVsActual_getCol.pcsrpt"))
			{
				 PrintWriter out = response.getWriter();				
				 String firstClick =request.getParameter("firsftClick");
				 CommonFilter  commonFilter  ;
				 commonFilter = populateCommonFilter(request,"PCSPlanVsActual",true);
				 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
				 {
					 CommonMessage.debugMsg("inside filter check");
					 commonFilter =(CommonFilter) httpSession.getAttribute("PCSPlanVsActual");
				 }	
				 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
				 
				httpSession.removeAttribute("PCSPlanVsActual");
				httpSession.setAttribute("PCSPlanVsActual",commonFilter);
				response.setContentType("text/html");
				List<String[]> pcsList  = pcsRptService.getPlanVsActual(commonFilter);
				httpSession.removeAttribute("pcsPlanVsActualList");
				httpSession.setAttribute("pcsPlanVsActualList", pcsList);
				JSONObject jsonObject=null;
				jsonObject = PlanActualTableModel(pcsList,"Plan Vs Actual",commonFilter);
				jsonObject.set("tableHeight", "70%%");
				jsonObject.set("tableWidth", "106%%");
				httpSession.removeAttribute("pcsPlanVsActualColModel");
			    httpSession.setAttribute("pcsPlanVsActualColModel", jsonObject);
				out.println(jsonObject);
			}
			else if( action.equals("PCSPlanVsActual_getData.pcsrpt"))
			{
				try
				{
					 
					 List<String[]> pcsComplianceList =new ArrayList<String[]>();
					 PrintWriter out = response.getWriter();
	  				 CommonFilter commonFilter = populateCommonFilter(request,"PCSPlanVsActual",false);
	  				 pcsComplianceList  = pcsRptService.getPlanVsActual(commonFilter);
	  				
	  				 httpSession.removeAttribute("PCSPlanVsActualgridParams");
	  				 JSONObject listToJsonObject = new JSONObject();
					 CommonMessage.debugMsg(" ccc "+commonFilter.getTotalRecordCnt());
					 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsComplianceList,request,3,0,commonFilter.getTotalRecordCnt()+4);
					 out.println(listToJsonObject);
					 out.close();
				}
			   catch(Exception e)
			   {
					CommonMessage.debugMsg(e.getMessage());
			   }
			}
			else if( action.equals("PCSPlanVsActual_getExcel.pcsrpt")){
				
				CommonFilter commonFilter = populateCommonFilter(request,"PCSPlanVsActual",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				String date   = null;
				if("Y".equals(commonFilter.getMonwise()))
					date   = fromMonth +"  -  "+ toMonth ;
				else
					date   = fromdate +"  -  "+ todate ;
				
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("pcsPlanVsActualColModel");
				tblJSONObj.put("title", "Planned Vs Actual");
				
				String format = ExcelUtils.getFormat(request);
				Workbook wb = pcsRptService.pcsPlanVsActualExport(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "PCSPlanVsActual", format);
				
			}
			if( action.equals("filterXmlLossTimeBreakupSmry_input.pcsrpt")){
				response.setContentType("xml"); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/LossTimeBreakUpSmry.xml") ;
			}
			else if(action.equals("LossTimeBreakupSmry_input.pcsrpt"))
			{				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/LossTimeBreakUpSmry.jsp");
				rd.forward(request, response); 
			}
			else if(action.equals("LossTimeBreakupSmry_getCol.pcsrpt"))
			{	
				String firstClick =request.getParameter("firstClick");
				 CommonFilter  commonFilter  ;
				 commonFilter = new CommonFilter(); 
				
					CommonMessage.debugMsg("getcol:" +firstClick);
			     commonFilter = populateCommonFilter(request,"LossTimeBreakupSmryCommonFilter",true);
				 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
				 {
					 commonFilter =(CommonFilter) httpSession.getAttribute("LossTimeBreakupSmryCommonFilter");
				 }	
				if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
				 
				 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				 commonFilter= 	FilterValues.getPCS(request, commonFilter);
								 
				 //String rptType = request.getParameter("rptType");					
				 //commonFilter.setWostatus(rptType);
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
						  
				 }				
				 httpSession.removeAttribute("LossTimeBreakupSmryCommonFilter");
				 httpSession.setAttribute("LossTimeBreakupSmryCommonFilter",commonFilter);
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 commonFilter.setISFORGRAPH('N');
				 List<String[]> lossBreakupRpt  =  pcsRptService.getLossTimeBreakupSmry(commonFilter);			 
				
				 JSONObject colModel = getLossTimeBreakUpTableModel(lossBreakupRpt,"",commonFilter);
				 colModel.set("tableHeight", "83%%");
				 colModel.set("tableWidth", "106%%");
				 
				 httpSession.removeAttribute("LossTimeBreakupSmryColModel");
				 httpSession.setAttribute("LossTimeBreakupSmryColModel", colModel);				
				 
				 CommonMessage.debugMsg(colModel);
				 out.println(colModel);				
				 out.close();					
			}
			else if( action.equals("LossTimeBreakupSmry_getData.pcsrpt") )
			{
				PrintWriter out = response.getWriter();
				try
				{					
					UIUtils.displayRequestParamsValue(request);
					 
					CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("LossTimeBreakupSmryCommonFilter");
					 commonFilter = populateCommonFilter(request,"LossTimeBreakupSmryCommonFilter",false);
					 JSONObject jsonObject = new JSONObject();		
					 //String rptType = request.getParameter("rptType");					
					// commonFilter.setWostatus(rptType);
					 commonFilter.setISFORGRAPH('N');
					 List<String[]> lossbreakuprpt  = pcsRptService.getLossTimeBreakupSmry(commonFilter);	
		     		jsonObject = UIUtils.convertToJqGridTableObject(lossbreakuprpt,request,1,0,commonFilter.getTotalRecordCnt()+1);
		     		
		     		 out.println(jsonObject);
					 commonFilter.setViewClick('N');
			    }
				catch(Exception e)
				{
					//CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if( action.equals("LossTimeBreakupSmry_getExcel.pcsrpt")){
				
				//HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"LossTimeBreakupSmryCommonFilter",false);
				commonFilter.setViewClick('Y');
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
							
				JSONObject tblJSONObj;				
				tblJSONObj = (JSONObject)httpSession.getAttribute("LossTimeBreakupSmryColModel");
				
				String name = "SectionWise";
				String title = "Section Wise";
				if("MCHM".equals(commonFilter.getLossRptType()))
				{
					name = "MachineWise";
					title = "Machine Wise";
				}
				 else if("CELL".equals(commonFilter.getLossRptType()))
				 {				 
						name = "CellWise";
						title = "Cell Wise";						
				 } 
				 else if("SECT".equals(commonFilter.getLossRptType()))
				 {				 
					name = "SectionWise";
					title = "Section Wise";						
				 }
				
				tblJSONObj.put("title", title);
				String format = ExcelUtils.getFormat(request);				
				
				Workbook wb = pcsRptService.getLossTimeBreakupSmryExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, name, format);
				
			}
			else if(action.equals("chart.pcsrpt"))
			{
				CommonMessage.debugMsg("Chart ");
				//processChart(request,response);
				
				PrintWriter out = response.getWriter();
				//processChart(request,response);
				CommonFilter commonFilter = populateCommonFilter(request,"LossTimeBreakupSmryCommonFilter",false);
				String reportType = "Section";
				 String rptType = request.getParameter("rptType");					
				 commonFilter.setWostatus(rptType);
				  if("SECT".equals(commonFilter.getLossRptType()))
					 reportType = "Section";
				 else if("CELL".equals(commonFilter.getLossRptType()))
					 reportType = "Line";
				 else if("MCHM".equals(commonFilter.getLossRptType()))
					 reportType = "Machine";
				  
				 ChartOptionBean columnChart = new ChartOptionBean();
				 columnChart.setChartType(ChartTypes.BAR);
				String forDashboard = request.getParameter("dashboard");
				/*if( ! "true".equals(forDashboard)){
					commonFilter = (CommonFilter) httpSession.getAttribute("commonFilterIden");
				}
				else{
					commonFilter = new  CommonFilter();
					
					FilterValues.getCommonFilters(request,commonFilter);
					FilterValues.getBDRelated(request, commonFilter);
					commonFilter.setGridSortColumn("1");
					commonFilter.setGridSortOrder("asc");
					
				}*/
					
				commonFilter.setISFORGRAPH('Y');
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				String date   = fromMonth +"  -  "+ toMonth ;
				String paretoOptions =commonFilter.getDrillLevel(); 
			
				
				List<String[]> graphData  = pcsRptService.getLossTimeBreakupSmry(commonFilter);
				List<String> xCategories = new ArrayList<String>();
				List<Double> dataLine = new ArrayList<Double>();//declaring variable for line chart
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();//declaring variable for charseries were the data is to bound
				ChartSeries chartSeriesLine = new ChartSeries();//declaring variable for line were the x and y asix will be added
				
				ChartSeries barChart = new ChartSeries();//declaring variable for barchart
				List<Double> barDataList = new ArrayList<Double>();//declaring variable for bar chart
				
				
				for( int i =1; i<graphData.size(); i++)
				{	
					xCategories.add(graphData.get(i)[2]);
					dataLine.add(Double.parseDouble(graphData.get(i)[3]));//adding data to the variable of line chart
					barDataList.add(Double.parseDouble(graphData.get(i)[3]));//adding data to the variable of Bar chart
					
				}
				
				
				barChart.setName(reportType);//declaring name for the bar
				barChart.setData(barDataList);//adding data 
				barChart.setType(ChartTypes.COLUMN);//declaring which axis it shd be set
				
				/*chartSeriesLine.setName("Cummulative");//adding data			
				chartSeriesLine.setData(dataLine);//declaring name for the Line
				chartSeriesLine.setType(ChartTypes.LINE);//declaring which axis it shd be set
				chartSeriesLine.setyAxis(1);*/
			
				chartSeriesList.add(barChart);//adding data to chart to be dispalyed
				//chartSeriesList.add(chartSeriesLine);//adding data to chart to be dispalyed

				List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();//declaring variable for Y axis
				List<ChartXAxis> chartXAxis =  new ArrayList<ChartXAxis>();//declaring variable for Y axis
				ChartYAxis yAxis = new ChartYAxis();
				
				yAxis.setMin(0);
				yAxis.getTitle().setText("Percentage");//declaring caption for Y axis
				ChartYAxis y1Axis = new ChartYAxis();//declaring 2nd yaxis
				ChartXAxis xaxis = new ChartXAxis();
				
				xaxis.getTitle().setText(reportType);
				
				chartYAxis.add(yAxis);//adding Yaxis to the chart
				
				chartXAxis.add(xaxis);
				columnChart.getxAxis().setTitle(xaxis.getTitle());
				JSONObject chartObj  = columnChart.drawChart(xCategories, chartSeriesList, " Loss Time Breakup Summary("+ reportType +" Wise)  ", null, chartYAxis);
				String dashboardIdnt = request.getParameter("dashboardIdent");
				/*if( UIUtils.isValidKeyId(dashboardIdnt));
					chartObj.set("dashboardIdent", dashboardIdnt);*/
					CommonMessage.debugMsg("Chart : "+chartObj);
				out.println(chartObj);
				out.close();
				
			}
			
			
			/****SectAndMechWiseDefect_input.pcsrpt****/
			if( action.equals("filterXmlSectAndMechWiseDefect_input.pcsrpt")){
				response.setContentType("xml"); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/LossTimeBreakUpSmry.xml") ;
			}
			else if(action.equals("SectAndMechWiseDefect_input.pcsrpt"))
			{				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/SectionAndMchWiseRpt.jsp");
				rd.forward(request, response); 
			}
			else if(action.equals("SectAndMechWiseDefect_getCol.pcsrpt"))
			{	
				String firstClick =request.getParameter("firstClick");
				 CommonFilter  commonFilter  ;
				 commonFilter = new CommonFilter(); 
				
					CommonMessage.debugMsg("getcol:" +firstClick);
					 commonFilter = populateCommonFilter(request,"SectAndMechWiseDefectCommonFilter",true);
				 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
				 {
					 commonFilter =(CommonFilter) httpSession.getAttribute("SectAndMechWiseDefectCommonFilter");
				 }	
				if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
				 
				 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				 commonFilter= 	FilterValues.getPCS(request, commonFilter);
								 
				// String rptType = request.getParameter("rptType");					
				 //commonFilter.setWostatus(rptType);
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
						  
				 }				
				 httpSession.removeAttribute("SectAndMechWiseDefectCommonFilter");
				 httpSession.setAttribute("SectAndMechWiseDefectCommonFilter",commonFilter);
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 commonFilter.setISFORGRAPH('N');
				 List<String[]> lossBreakupRpt  =  pcsRptService.getSectAndMechWiseDefect(commonFilter);			 
				
				 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				 GridColModel gridColModel = new GridColModel();
				 
				 gridColModel.setHeaderNum(1);					
				 String [] colHeader = lossBreakupRpt.get(2);			
				 String [] colHeaderCond = lossBreakupRpt.get(1);
					
				 List<String[]> headers = new ArrayList<String[]>();
				 headers.add(colHeader);
				 jqGridTableModel.setGroupByField("ELEMENTNAME");
				 jqGridTableModel.setTableButton(true);
					jqGridTableModel.setTableHeight(310);
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setGroupBy(true);
					jqGridTableModel.setEnableFilter(true);
				 //JSONObject colModel = getSectMchWiseTableModel(lossBreakupRpt,"",commonFilter);
				JSONObject colModel =  UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				 //colModel.set("tableHeight", "70%%");
				 //colModel.set("tableWidth", "106%%");
				 
				 httpSession.removeAttribute("SectAndMechWiseDefectColModel");
				 httpSession.setAttribute("SectAndMechWiseDefectColModel", colModel);				
				 
				 CommonMessage.debugMsg(colModel);
				 out.println(colModel);				
				 out.close();					
			}
			else if( action.equals("SectAndMechWiseDefect_getData.pcsrpt") )
			{
				PrintWriter out = response.getWriter();
				try
				{					
					UIUtils.displayRequestParamsValue(request);
					 
					CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("SectAndMechWiseDefectCommonFilter");
					commonFilter.setISFORGRAPH('N');
					 commonFilter = populateCommonFilter(request,"SectAndMechWiseDefectCommonFilter",false);
					 JSONObject jsonObject = new JSONObject();		
					// String rptType = request.getParameter("rptType");					
					// commonFilter.setWostatus(rptType);
					 List<String[]> lossbreakuprpt  = pcsRptService.getSectAndMechWiseDefect( commonFilter);	
		     		jsonObject = UIUtils.convertToJqGridTableObject(lossbreakuprpt,request,3,0,commonFilter.getTotalRecordCnt());
		     		
		     		 out.println(jsonObject);
					 commonFilter.setViewClick('N');
			    }
				catch(Exception e)
				{
					//CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if( action.equals("SectAndMechWiseDefect_getExcel.pcsrpt")){
				
				//HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"SectAndMechWiseDefectCommonFilter",false);
				commonFilter.setViewClick('Y');
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
							
				JSONObject tblJSONObj;				
				tblJSONObj = (JSONObject)httpSession.getAttribute("SectAndMechWiseDefectColModel");
				
				String name = "SectionWise";
				String title = "Section Wise";
				
				 
				if("MCHM".equals(commonFilter.getWostatus()))
				{
					name = "MachineWise";
					title = "Machine Wise";
				}
				 else if("CELL".equals(commonFilter.getWostatus()))
				 {				 
						name = "CellWise";
						title = "Cell Wise";						
				 } 
				 else if("SECT".equals(commonFilter.getWostatus()))
				 {				 
					name = "SectionWise";
					title = "Section Wise";						
				 }
				
				tblJSONObj.put("title", title);
				String format = ExcelUtils.getFormat(request);				
				
				Workbook wb = pcsRptService.getSectAndMechWiseDefectExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, name, format);
				
			}
			else if(action.equals("defectChart.pcsrpt"))
			{
				CommonMessage.debugMsg("Chart ");
				//processChart(request,response);
				
				PrintWriter out = response.getWriter();
				//processChart(request,response);
				CommonFilter commonFilter = populateCommonFilter(request,"SectAndMechWiseDefectCommonFilter",false);
				
				String reportType = "Section";
			
				  if("SECT".equals(commonFilter.getLossRptType()))
					 reportType = "Section";
				 else if("CELL".equals(commonFilter.getLossRptType()))
					 reportType = "Line";
				 else if("MCHM".equals(commonFilter.getLossRptType()))
					 reportType = "Machine";
				  
				 ChartOptionBean columnChart = new ChartOptionBean();
				 columnChart.setChartType(ChartTypes.BAR);
				 
				/*String forDashboard = request.getParameter("dashboard");
				if( ! "true".equals(forDashboard)){
					commonFilter = (CommonFilter) httpSession.getAttribute("commonFilterIden");
				}
				else{
					commonFilter = new  CommonFilter();					
					FilterValues.getCommonFilters(request,commonFilter);
					FilterValues.getPCS(request, commonFilter);
					commonFilter.setGridSortColumn("1");
					commonFilter.setGridSortOrder("asc");			
				}			*/
				
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				String date   = fromMonth +"  -  "+ toMonth ;
				String paretoOptions =commonFilter.getDrillLevel(); 
			
				commonFilter.setISFORGRAPH('Y');
				List<String[]> graphData  = pcsRptService.getSectAndMechWiseDefect(commonFilter);
				List<String> xCategories = new ArrayList<String>();
				List<Double> dataLine = new ArrayList<Double>();//declaring variable for line chart
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();//declaring variable for charseries were the data is to bound
				ChartSeries chartSeriesLine = new ChartSeries();//declaring variable for line were the x and y asix will be added
				
				ChartSeries barChart = new ChartSeries();//declaring variable for barchart
				List<Double> barDataList = new ArrayList<Double>();//declaring variable for bar chart
				
				
				for( int i =1; i<graphData.size(); i++)
				{	
					xCategories.add(graphData.get(i)[2]);
					dataLine.add(Double.parseDouble(graphData.get(i)[3]));//adding data to the variable of line chart
					barDataList.add(Double.parseDouble(graphData.get(i)[3]));//adding data to the variable of Bar chart
					
				}
				
				
				barChart.setName(reportType);//declaring name for the bar
				barChart.setData(barDataList);//adding data 
				barChart.setType(ChartTypes.COLUMN);//declaring which axis it shd be set
				
				/*chartSeriesLine.setName("Cummulative");//adding data			
				chartSeriesLine.setData(dataLine);//declaring name for the Line
				chartSeriesLine.setType(ChartTypes.LINE);//declaring which axis it shd be set
				chartSeriesLine.setyAxis(1);*/
			
				chartSeriesList.add(barChart);//adding data to chart to be dispalyed
				//chartSeriesList.add(chartSeriesLine);//adding data to chart to be dispalyed

				List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();//declaring variable for Y axis
				List<ChartXAxis> chartXAxis =  new ArrayList<ChartXAxis>();//declaring variable for Y axis
				ChartYAxis yAxis = new ChartYAxis();
				
				yAxis.setMin(0);
				yAxis.getTitle().setText("Percentage");//declaring caption for Y axis
				ChartYAxis y1Axis = new ChartYAxis();//declaring 2nd yaxis
				ChartXAxis xaxis = new ChartXAxis();
				
				xaxis.getTitle().setText(reportType);
				
				chartYAxis.add(yAxis);//adding Yaxis to the chart
				
				chartXAxis.add(xaxis);
				columnChart.getxAxis().setTitle(xaxis.getTitle());
				JSONObject chartObj  = columnChart.drawChart(xCategories, chartSeriesList, " Defect Summary("+ reportType +" Wise)  ", null, chartYAxis);
				String dashboardIdnt = request.getParameter("dashboardIdent");
				if( UIUtils.isValidKeyId(dashboardIdnt));
					chartObj.set("dashboardIdent", dashboardIdnt);
				CommonMessage.debugMsg("Chart : "+chartObj);
				out.println(chartObj);
				out.close();
				
			}
			
			if( action.equals("filterXmllossBreakupReport_input.pcsrpt")){
				response.setContentType("xml"); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/PcsReort.xml") ;
			}
			else if(action.equals("lossBreakupReport_input.pcsrpt"))
			{
				CommonMessage.debugMsg("lossBreakupReport_input.pcsrpt jsp");
				String filterString = request.getParameter("filterString");
				String module = request.getParameter("module");
				//RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/Abnormality.jsp"); 
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/LossBreakUpReport.jsp");
				rd.forward(request, response); 
			}
			else if(action.equals("lossBreakupReport_getCol.pcsrpt"))
			{	
				String firstClick =request.getParameter("firstClick");
				 CommonFilter  commonFilter  ;
				 commonFilter = new CommonFilter(); 
				
					CommonMessage.debugMsg("getcol:" +firstClick);
					 commonFilter = populateCommonFilter(request,"pcsReportCommonFilter",true);
				 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
				 {
					 commonFilter =(CommonFilter) httpSession.getAttribute("lossBreakupCommonFilter");
				 }	
				if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
				 
				 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				 commonFilter= 	FilterValues.getPCS(request, commonFilter);
				// populateCommonFilter(request,"HseAccTrendRptCommonFilter",true);
				 
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
						  
				 }
				
				String rptType = request.getParameter("rptType");
				CommonMessage.debugMsg("rptType     :"+rptType);
				commonFilter.setWostatus(rptType);
				
				 httpSession.removeAttribute("lossBreakupCommonFilter");
				 httpSession.setAttribute("lossBreakupCommonFilter",commonFilter);
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 
					String loginflid = CommonFunctions.getLoginFlid(request);
					if (commonFilter.getFlid() != null)
					{
						commonFilter.setFlid(loginflid);
					}else
					{  
						commonFilter.setFlid(loginflid);
					}
					CommonMessage.debugMsg("pcs loginflid" + loginflid);
					
				 List<String[]> lossBreakupRpt  =  pcsRptService.getlossBreakup(commonFilter);
				 
				 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(false);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setGroupBy(true);
				 if(rptType.equals("L") )
						jqGridTableModel.setGroupByField("MAINLOSS");
				else
						jqGridTableModel.setGroupByField("MACHINE");
					
				 gridColModel.setHeaderNum(1);
					
					String [] colHeader = lossBreakupRpt.get(2);			
					String [] colHeaderCond = lossBreakupRpt.get(1);
					
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					
					
				// JSONObject colModel = getlossBreaTableModel(lossBreakupRpt,rptType);
				 colModel.set("tableHeight", "85%%");
				 if(rptType.equals("L")){
					 httpSession.removeAttribute("lossBreakupColModel");
					 httpSession.setAttribute("lossBreakupColModel", colModel);
				 }
				 else if(rptType.equals("M")){
					 httpSession.removeAttribute("MchBreakupColModel");
					 httpSession.setAttribute("MchBreakupColModel", colModel);
				 }
				 CommonMessage.debugMsg(colModel);
				 out.println(colModel);
				
				 out.close();
				
				
				
			}
			
			else if( action.equals("lossBreakupReport_getData.pcsrpt") )
			{
				PrintWriter out = response.getWriter();
				try
				{
					CommonMessage.debugMsg("lossBreakupReport_getData");
					UIUtils.displayRequestParamsValue(request);
					 
					String rptType = request.getParameter("rptType");
					CommonMessage.debugMsg("rptType     :"+rptType);
					CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("lossBreakupCommonFilter");
					 commonFilter = populateCommonFilter(request,"pcsReportCommonFilter",false);
					 JSONObject jsonObject = new JSONObject();
					 commonFilter.setWostatus(rptType);
					 List<String[]> lossbreakuprpt  = pcsRptService.getlossBreakup( commonFilter);	
		     		 
		     		 CommonMessage.debugMsg("getTotalRecordCnt"+commonFilter.getTotalRecordCnt());
		     		 	
		     		 jsonObject = UIUtils.convertToJqGridTableObject(lossbreakuprpt,request,3,0,commonFilter.getTotalRecordCnt());
		     		 CommonMessage.debugMsg(jsonObject);
		     		 out.println(jsonObject);
					 commonFilter.setViewClick('Y');	  			 	
		  			
					
			    }
				catch(Exception e)
				{
					//CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("lossBreakupReport_getExcel.pcsrpt")){
				
				//HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"lossBreakupCommonFilter",false);
				commonFilter.setViewClick('Y');
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
				String rptType = commonFilter.getWostatus();
				CommonMessage.debugMsg("rptTypeExcel excel     :"+rptType);
				JSONObject tblJSONObj;
				if(rptType.equals("L"))
					tblJSONObj = (JSONObject)httpSession.getAttribute("lossBreakupColModel");
				else
					tblJSONObj = (JSONObject)httpSession.getAttribute("MchBreakupColModel");
				
				tblJSONObj.put("title", "Loss Breakup  Report");
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = pcsRptService.getlossBreakupExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "LossBreakupReport", format);
				
			}
			//***END OF LOSS BREAKUO REPORT*****//
			//******Cell efficiency ******
			if( action.equals("filterXmlCellefficiencyReport_input.pcsrpt")){
				response.setContentType("xml"); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/PcsReort.xml") ;
			}
			else if(action.equals("CellefficiencyReport_input.pcsrpt"))
			{
				CommonMessage.debugMsg("Brfore jsp");
				String filterString = request.getParameter("filterString");
				String module = request.getParameter("module");
				//RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/Abnormality.jsp"); 
				request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/CellEfficiencyRpt.jsp");
				rd.forward(request, response); 
			}
			
			else if(action.equals("CellefficiencyReport_getCol.pcsrpt"))
			{	
				String firstClick =request.getParameter("firstClick");
				 CommonFilter  commonFilter  ;
				 commonFilter = new CommonFilter(); 
				
					CommonMessage.debugMsg("getcol:" +firstClick);
				 
				 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
				 {
					 commonFilter =(CommonFilter) httpSession.getAttribute("CellefficiencyCommonFilter");
				 }	
				if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
				 
				String drillL = commonFilter.getDrillLevel();				
				String flag = request.getParameter("drillFlag");  				
				//char drillFlag = commonFilter.getDrillFlag();				 
				 
				 if("SFTM".equals(drillL) && flag.equals("b")){						
						 commonFilter.setDrillLevel("ASSM");
					 }
				 
				 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				 commonFilter= 	FilterValues.getPCS(request, commonFilter);
				// populateCommonFilter(request,"HseAccTrendRptCommonFilter",true);
				 
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");		 
					  commonFilter.setRemoveBlank("Y");
					  }						
				 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
						  commonFilter.setRemoveBlank("Y");
				 }
				 httpSession.removeAttribute("CellefficiencyCommonFilter");
				 httpSession.setAttribute("CellefficiencyCommonFilter",commonFilter);
				 response.setContentType("text/html");
				 PrintWriter out = response.getWriter();
				 String drillLevel = commonFilter.getDrillLevel();
				 
				
				 if(drillLevel.equals("ASSM") && flag.equals("f"))
					 commonFilter.setDrillLevel("SFTM");
				
				 List<String[]> hseAccidentRpt  =  pcsRptService.getCellRpt(commonFilter);
				 
				
				 JSONObject colModel = getTableModel(hseAccidentRpt);
				 colModel.set("tableHeight", "93%%");
				 colModel.set("tableWidth", "108%%");
				 
				 httpSession.removeAttribute("CellEfficiencyColModel");
				 httpSession.setAttribute("CellEfficiencyColModel", colModel);
				 CommonMessage.debugMsg(colModel);
				 out.println(colModel);
				
				 out.close();
				
				
				
			}
			
			else if( action.equals("CellefficiencyReport_getData.pcsrpt") )
			{
				PrintWriter out = response.getWriter();
				try
				{
					CommonMessage.debugMsg("CellefficiencyReport_getData");
					UIUtils.displayRequestParamsValue(request);
					 
					
					CommonMessage.debugMsg("SHIFTSELECTED   P:"+request.getParameter("cmbshift"));
					CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("CellefficiencyCommonFilter");
					 String shiftCode = request.getParameter("cmbshift");
					String drillL = commonFilter.getDrillLevel();
					 char drillFlag = commonFilter.getDrillFlag();
					 if("SFTM".equals(drillL) && drillFlag == 'b'){
							
							 commonFilter.setDrillLevel("ASSM");
						 }
					 
					 JSONObject jsonObject = new JSONObject();
					 
					 String drillLevel = commonFilter.getDrillLevel();
					 
					 if(drillLevel.equals("ASSM") && drillFlag == 'f')
						 commonFilter.setDrillLevel("SFTM");
					if(UIUtils.isValidKeyId(shiftCode ))
						commonFilter.setWostatus(shiftCode);//for filter based on shift
					 
					 List<String[]> hseAccidentRpt  = pcsRptService.getCellRpt( commonFilter);	
		     		 CommonMessage.debugMsg("customerComplaintTrendList.size("+hseAccidentRpt.size()+")");
		     		 CommonMessage.debugMsg("getTotalRecordCnt"+commonFilter.getTotalRecordCnt());
		     		 	if (hseAccidentRpt.size() > 2) 
		     		 	jsonObject = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,2,0,hseAccidentRpt.size());
		     		 	
		     		 	 CommonMessage.debugMsg(jsonObject);
		     		 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
		  			
					
			    }
				catch(Exception e)
				{
					//CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("CellefficiencyReport_getExcel.pcsrpt")){
				
				//HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"CellefficiencyCommonFilter",false);
				commonFilter.setViewClick('Y');
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("CellEfficiencyColModel");
				
				tblJSONObj.put("title", "Cell Efficiency  Report");
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = pcsRptService.CellEfficiencyExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "CellEfficiencyReport", format);
				
			}
			
			else if( action.equals("PlanVsRej_input.pcsrpt")){
				
				planVsRejInput(request,response);
			}
			
			else if( action.equals("PlanVsRej_getCol.pcsrpt")){
				planVsRejgetCol(request,response);
				CommonMessage.debugMsg("test exitgetcol");
			}
			
			else if( action.equals("PlanVsRej_getData.pcsrpt")){

				CommonMessage.debugMsg("test getdata");
				planVsRejgetData(request,response);				
			}
			
			else if( action.equals("PlanVsRej_getExcel.pcsrpt")){
				planVsRejgetExcel(request,response);
			}
			
			else if( action.equals("filterXmlPlanVsRej_input.pcsrpt")){
				 response.setContentType("xml"); 
				CommonMessage.debugMsg("action "+ action); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/PlanVsRej.xml") ;
			 }
			
			else if( action.equals("chartPlanVsRej.pcsrpt")){
				
				PrintWriter out = response.getWriter();
				processPlanVsRejChart(request,response);
				
			}
		}
		
		private JSONObject PlanActualTableModel(List<String[]> headers, String string,CommonFilter commonFilter) 
		{

			JqGridTableModel jqGridTableModel = new  JqGridTableModel();	
			List<JqGridColModel>jqGridColModelList =new ArrayList<JqGridColModel>();
			
			String[] row =headers.get(0);
			String[] row2 =headers.get(1);
			int colFlag = row.length;
			String [] colHeader = new String[ colFlag];
			String [] colHeader2 = new String[ colFlag];
			String [] colHeader3 = new String[ colFlag];
			
			
			jqGridTableModel.setTableButton(true);	
			jqGridTableModel.setTableHeight(240);
			jqGridTableModel.setRowNumbers(true);
			
			
			for(int i =0; i < colFlag; i++)
			{
				
				if(i==0 || i==1 || i==2  || i==4)
				{
					jqGridColModelList.add(getColModel(row[i]+i,50,"left",true));	
				}
				else if(i>5)
				{
					jqGridColModelList.add(getColModel(row[i]+i,70,"right",false));						
				}
				else
				{
					if(i==3)
						jqGridColModelList.add(getColModel(row[i]+i,200,"left",false));	
					else
						jqGridColModelList.add(getColModel(row[i]+i,250,"left",false));
				}
				colHeader[i] = "";				
				colHeader2[i] = row[i];
				colHeader3[i] = row2[i];				
			}
			
			jqGridTableModel.setColModel(jqGridColModelList);		
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.getRowHeaders().add(colHeader2);
			jqGridTableModel.getRowHeaders().add(colHeader3);
			
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			CommonMessage.debugMsg(tableModel);
			return tableModel;
		}
		private JqGridColModel getColModel (String colIndex, int width,String allign,boolean hidden)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex);
			jqGridColModel.setName(colIndex);
			jqGridColModel.setWidth( width);				
			jqGridColModel.setAlign(allign);
			jqGridColModel.setEditable(false);
			jqGridColModel.setHidden(hidden);
			
			return jqGridColModel;
		}
private JSONObject getLossTimeBreakUpTableModel(List<String[]> headers, String string,CommonFilter commonFilter) 
{

JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	
	String [] colHeader = headers.get(0);
	jqGridTableModel.getRowHeaders().add(colHeader);

	jqGridTableModel.setTableButton(true);
	jqGridTableModel.setTableHeight(980);
	jqGridTableModel.setRowNumbers(true);
	jqGridTableModel.setEnableFilter(true);
	
	jqGridTableModel.setGroupBy(true);
	for(int i =0; i < colHeader.length; i++)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				
		jqGridColModel.setWidth( 150);				
		jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);
		
		if(i==0 || i==1)
		{
			jqGridColModel.setHidden(true);		
			if(i==1)
				jqGridColModel.setKey(true);
		}
		
		else if(i==2)
		{
			 if("M".equals(commonFilter.getWostatus()))
				 colHeader[i] = "Machine Name";
			 else if("C".equals(commonFilter.getWostatus()))
				 colHeader[i] = "Line Name";
			 else if("S".equals(commonFilter.getWostatus()))
				 colHeader[i] = "Section Name";
			
			jqGridColModel.setWidth(250);
			jqGridColModel.setAlign("left");
			jqGridColModel.setHidden(true);
		}
		
		else if(i==3){
			
			colHeader[i] = "Loss Time Main Head Titles";
			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
		}		
		else if(i==4)
		{			
			colHeader[i] = "Loss Time Sub Head Name";
			jqGridColModel.setWidth(150);
			
		}
		else if(i==5)
		{			
			colHeader[i] = "Total Loss Time";
			jqGridColModel.setWidth(150);
			jqGridColModel.setAlign("right");
		}
		else if(i==6)
		{			
			colHeader[i] = "%";
			jqGridColModel.setWidth(80);
			jqGridColModel.setAlign("right");
			jqGridColModel.setHidden(true);
		}
		
		if(i==colHeader.length-1)
			jqGridColModel.setHidden(true);
		
		jqGridTableModel.getColModel().add(jqGridColModel);
	}
	
	
		jqGridTableModel.setGroupByField("ELEMENTNAME");
	 
	
	 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 return tableModel;
}

private JSONObject getSectMchWiseTableModel(List<String[]> headers, String string,CommonFilter commonFilter) {
			
JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	
	String [] colHeader = headers.get(0);
	jqGridTableModel.getRowHeaders().add(colHeader);

	jqGridTableModel.setTableButton(true);
	jqGridTableModel.setTableHeight(980);
	jqGridTableModel.setRowNumbers(true);
	jqGridTableModel.setGroupBy(true);
	jqGridTableModel.setEnableFilter(true);
	
	String headerSql = "'SELECT ";
	for(int i =0; i < colHeader.length; i++)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				
		jqGridColModel.setWidth( 150);				
		jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);
		
		if(i==0 ||i==1  )
		{
			jqGridColModel.setHidden(true);
			if(i==1)
				jqGridColModel.setKey(true);
		}
		
		else if(i==2)
		{
			 if("M".equals(commonFilter.getWostatus()))
				 colHeader[i] = "Machine Name";
			 else if("C".equals(commonFilter.getWostatus()))
				 colHeader[i] = "Line Name";
			 else if("S".equals(commonFilter.getWostatus()))
				 colHeader[i] = "Section Name";
			
			jqGridColModel.setWidth(250);
			jqGridColModel.setAlign("left");
		}
		
		else if(i==3){
			
			colHeader[i] = "Defect-Head";
			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
		}		
		else if(i==4)
		{
			
			colHeader[i] = "Defect Qty";
			jqGridColModel.setWidth(80);
			jqGridColModel.setAlign("right");
		}
		else if(i==5)
		{
			
			colHeader[i] = "%";
			jqGridColModel.setWidth(80);
			jqGridColModel.setAlign("right");
		}
		
		
		if(i==colHeader.length-1)
			jqGridColModel.setHidden(true);
		
		jqGridTableModel.getColModel().add(jqGridColModel);
		CommonMessage.debugMsg("123......."+jqGridColModel.getIndex());
		headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
	}
	jqGridTableModel.setGroupByField("ELEMENTNAME");
	 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		
	 return tableModel;
		}

private JSONObject getlossBreaTableModel(List<String[]> headers, String rptType) {
			// TODO Auto-generated method stub
	JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	
	String [] colHeader = headers.get(0);
	String [] colHeader1 = headers.get(1);
	jqGridTableModel.getRowHeaders().add(colHeader1);
	
	jqGridTableModel.setTableButton(true);
	jqGridTableModel.setTableHeight(980);
	jqGridTableModel.setRowNumbers(true);
	jqGridTableModel.setGroupBy(true);
	jqGridTableModel.setEnableFilter(true);
	
	int len = 0;
	if(rptType.equals("L") ){
		jqGridTableModel.setGroupByField("MAINLOSS");
		len = colHeader.length;
	}
	else{
		jqGridTableModel.setGroupByField("MACHINE");
		len = colHeader.length;
	}
	String headerSql = "'SELECT ";
	for(int i =0; i < len; i++)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				
		jqGridColModel.setWidth( 100);				
		jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);
		
		if(i==0  )
		{
			jqGridColModel.setHidden(true);
		//	jqGridColModel.setKey(true);
		}
		
		else if(i>=10 && i<=18)
		{
			jqGridColModel.setHidden(true);
		//	jqGridColModel.setKey(true);
		}
		else if(i==1)
		{
			jqGridColModel.setWidth(80);	
			jqGridColModel.setAlign("center");
		}
		else if(i==2)
		{
			jqGridColModel.setWidth(30);
			jqGridColModel.setAlign("center");
		}
		
		else if(i>=3&& i<=6){
			jqGridColModel.setWidth(250);
			jqGridColModel.setAlign("left");
		}
		else if(i == 6 && rptType.equals("L")){
			
			jqGridColModel.setHidden(true);
		}
		else if(i == 5 && rptType.equals("M")){
			
			jqGridColModel.setHidden(true);
		}
		
		else if(colHeader[i].equals("Loss Time / Qty") )
		{
			jqGridColModel.setAlign("right");
		}
		else{
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");
		}
		if(rptType.equals("M") && i == colHeader.length-1 )
			jqGridColModel.setHidden(true);

		CommonMessage.debugMsg(i+" -- --- "+colHeader[i]);
		jqGridTableModel.getColModel().add(jqGridColModel);
		CommonMessage.debugMsg("123......."+jqGridColModel.getIndex());
		headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
	}
	headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
	CommonMessage.debugMsg("headerSql.....123..."+headerSql);
	
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 return tableModel;
		}


private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
	//HttpSession httpSession = request.getSession(false);
	CommonFilter commonFilter = populateCommonFilter(request,"LossTimeBreakupSmryCommonFilter",false);
	
	 String rptType = request.getParameter("rptType");					
	 commonFilter.setWostatus(rptType);
	 
	String forDashboard = request.getParameter("dashboard");
	CommonMessage.debugMsg("forDashboard....."+forDashboard);
	/*if( ! "true".equals(forDashboard)){
		CommonMessage.debugMsg("to check dash board");
		commonFilter = (CommonFilter) httpSession.getAttribute("LossTimeBreakupSmryCommonFilter");
	}
	else{
		commonFilter = new  CommonFilter();
		CommonMessage.debugMsg("to check dash board true ");
		FilterValues.getCommonFilters(request,commonFilter);
		FilterValues.getPCS(request, commonFilter);
	}	*/
	CommonMessage.debugMsg("After Com Filter ");
	if(commonFilter.getRowTotal() == null )
		commonFilter.setRowTotal('Y');
	
	if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
		  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
		  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
		  commonFilter.setMonwise("Y");
 	  }
	commonFilter.setISFORGRAPH('Y');
	 List<String[]> lossbreakuprpt  = pcsRptService.getLossTimeBreakupSmry(commonFilter);	
	
	JSONObject chartObj = null;
	
	chartObj = processLineChart(lossbreakuprpt,commonFilter,ChartTypes.COLUMN);
	
	UIUtils.dashBoardSetChartObject(request,chartObj);
		
	PrintWriter out = response.getWriter();
	out.print(chartObj);
	out.close();
		
}



private JSONObject processLineChart(List<String[]> lossbreakuprpt,CommonFilter commonFilter,String chartType){
	if( lossbreakuprpt == null || lossbreakuprpt.size() <= 2  )
		return null;
	
	ChartOptionBean lineChart =  new ChartOptionBean();
	List<String> xAxisCategory = new ArrayList<String>();
	List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
	List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
	CommonMessage.debugMsg("graph header..........................."+commonFilter.getDrillCaption());
	String drillLevel = FilterValues.getHeader(commonFilter.getDrillCaption());
	String abnType = commonFilter.getAbnormalityType();
	String date;
	if(abnType==null)
		abnType="";
	if(commonFilter.getMonwise().equals("Y"))
	{
		date = commonFilter.getFromMonth()+" To "+commonFilter.getToMonth();
	}
	else
		date = commonFilter.getFromDate()+" - "+commonFilter.getToDate(); 
	String title = abnType+" Cumulative Report -" + drillLevel + " Level From "+date;
	
	
	String [] header =  lossbreakuprpt.get(1);
	
	String [] data =  lossbreakuprpt.get(3);
	
	
	
	String subTitle = "";
	
	ChartSeries timeSeries = new ChartSeries();
	List<String> timeData = new ArrayList<String>();
	
	ChartSeries intstanceSeries = new ChartSeries();
	List<Double> instanceData = new ArrayList<Double>();
	
	
	for( int i = 2;i < lossbreakuprpt.size();i++ ){			


			timeData.add(lossbreakuprpt.get(i)[2]);		
			xAxisCategory.add(lossbreakuprpt.get(i)[3]);
			instanceData.add(Double.parseDouble(lossbreakuprpt.get(i)[3]));
		
	}
	if(timeData.size() > 0 ){
		timeSeries.setData(timeData);
		timeSeries.setType(chartType);
		timeSeries.setName("Identified");
		chartSeriesList.add(timeSeries);
		
		
		ChartYAxis yAxis = new ChartYAxis(); 
		yAxis.setMin(0);
		yAxis.getTitle().setText("No.Of Tags");
		chartYAxis.add(yAxis);
	}
	if( instanceData.size() > 0){
		intstanceSeries.setData(instanceData);
		intstanceSeries.setType(chartType);
		intstanceSeries.setName("Removed");
		chartSeriesList.add(intstanceSeries);
		
		
	}
	ChartXAxis xaxis = new ChartXAxis();
	if(commonFilter.getMonwise().equals("Y"))
		xaxis.getTitle().setText("Month");
	else
		xaxis.getTitle().setText("Date");
	lineChart.getxAxis().setTitle(xaxis.getTitle());
	return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
	
}
private void ExportExcel(HttpServletRequest request, HttpServletResponse response, List<String[]> pcsLists) throws IOException {
			
	
	  String rowId = request.getParameter("rowId");
	  String date = request.getParameter("date");
	  String sectId = request.getParameter("sectId");
	  String prlmid = request.getParameter("prlmid");
	  String shift = request.getParameter("shift");
	  String cellId = request.getParameter("cellId");
	  String shiftId = request.getParameter("sftId");
	  String shiftKeyId = request.getParameter("shiftId");
	  String sectid = request.getParameter("secId");
	
	String path = UIUtils.getExcelTemplatePath(request);	
	String [] pcsexl = pcsLists.get(0);				 			
		InputStream inp = new FileInputStream(path+"/ProductionEntry_Vasai.xls");	
		
	HSSFWorkbook wb = new HSSFWorkbook(inp) ;				    	
	inp.close();
	 String format;
	 format =".xls";
	Sheet sheet = wb.getSheet("Production Entry");  				    	
	int k = 3;				    		
	for(String[] exclRow : pcsLists)
	{
		int j=3; 				    			 
		 for(int i=0;i<exclRow.length;i++)
		 { CommonMessage.debugMsg("i:"+i+"j-"+j+"k-"+k);
			  CommonMessage.debugMsg(i + " : "+exclRow[i]);
			  sheet.getRow(j).getCell(k).setCellValue(exclRow[i]);
			  CommonMessage.debugMsg(i + " : "+exclRow[i]);
			  CommonMessage.debugMsg(i + j+k);
			 j++;
		 }
		 k++;

		 sheet.getRow(1).getCell(1).setCellValue(sectid); 
		 sheet.getRow(1).getCell(3).setCellValue(cellId); 
		 sheet.getRow(2).getCell(1).setCellValue(date); 
		 sheet.getRow(2).getCell(3).setCellValue(shift); 
		
		
	}

			wb.writeProtectWorkbook("admin", "admin");
			sheet.protectSheet("admin");
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename=" + "ProductionEntry"+format +";charset=UTF-8");
			response.setHeader("Pragma", "no-cache");
			
			ServletOutputStream out = response.getOutputStream();  
			
			wb.write(out);
			out.flush();
			out.close();
			
		}
		private JSONObject getTableModel(List<String[]> headers)
		{
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			
			String [] colHeader = headers.get(0);
			jqGridTableModel.getRowHeaders().add(colHeader);
		
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setTableHeight(350);
			jqGridTableModel.setRowNumbers(true);
			String headerSql = "'SELECT ";
			for(int i =0; i < colHeader.length; i++)
			{
				
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
						
				jqGridColModel.setWidth( 100);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				if(i==0 || i==1 ||i==2 ||i>=7)
				{
					jqGridColModel.setHidden(true);
					if(i==0)
						jqGridColModel.setKey(true);
				}
				
				if(i==4)
				{
					jqGridColModel.setWidth(400);	
					jqGridColModel.setAlign("left");
				
				}
				
				
				else{
					jqGridColModel.setWidth(100);
					jqGridColModel.setAlign("left");
				}
				
				jqGridTableModel.getColModel().add(jqGridColModel);
				CommonMessage.debugMsg("123......."+jqGridColModel.getIndex());
				headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
			}
			jqGridTableModel.setGroupByField("ELEMENTNAME");
			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
				CommonMessage.debugMsg("headerSql.....123..."+headerSql);
			
			// JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
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
				commonFilter = 	FilterValues.getPCS(request, commonFilter);
				commonFilter.setViewClick('Y');
				commonFilter.setISFORGRAPH('N');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
	}