
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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
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
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.OplCummulativeService;
import com.akranta.tpm.service.OplService;
import com.akranta.tpm.service.impl.OplCummulativeServiceImpl;
import com.akranta.tpm.service.impl.OplDrilldownServiceImpl;
import com.akranta.tpm.service.impl.OplServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
public class OplCummulativeServlet extends HttpServlet {
	
	/**
	 * Created By:N.Arun
	 * Modified By:Siddharth .A
	 */
	private static final long serialVersionUID = 1L;
	private OplCummulativeService oplCummulativeService ; 
	OplService oplService;
	DashboardService dashboardService;
	private static final String oplCummulativeRptIden="oplCummulativeFilter";
	public OplCummulativeServlet(){
		/*try {
			oplCummulativeService = new OplCummulativeServiceImpl();
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
		
		HttpSession httpSession = request.getSession(false);	
		
		CommonFilter  commonFilter = null;
		
		String action = UIUtils.getActionPart(request);
		try {
			oplCummulativeService = (OplCummulativeServiceImpl)UIUtils.getServiceObject(request,"OplCummulativeServiceImpl");
            oplService = (OplServiceImpl)UIUtils.getServiceObject(request,"OplServiceImpl"); 
         
       
			CommonMessage.debugMsg("  oplServices jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
			//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
				oplService.OplServiceImplJwt(
						   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
						);
				CommonMessage.debugMsg("  oplServices jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
				//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
				oplCummulativeService.OplCummulativeServiceImplJwt(
							   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
							);
				
            dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		if( action.equals("filterXmlOplCummulative_input.oplcum")){
			 response.setContentType("xml"); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/OPLCumulative.xml") ;
		 }
		
		else if(action.equals("OplCummulative_input.oplcum")) 
		{ 
			request.setAttribute("oplCumMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/OplCummulative.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("OplCummulative_getCol.oplcum"))
		{
			String firstClick =request.getParameter("firstClick");
			
			httpSession.removeAttribute(oplCummulativeRptIden);
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
			 {
				
				 commonFilter =(CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
			 }	
			if(commonFilter==null)
			 commonFilter = new CommonFilter(); 
			 
			 commonFilter= FilterValues.getCommonFilters(request,commonFilter);
			 commonFilter= FilterValues.getOPLandKaizen(request,commonFilter);
			 //commonFilter= populateCommonFilter(request,"HseAccTrendRptCommonFilter",true);
			 
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
		 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(0));
					  commonFilter.setToDate(CommonFunctions.getDate());
			 }
			
			
			PrintWriter out = response.getWriter();
			
			

			httpSession.removeAttribute(oplCummulativeRptIden);
			httpSession.setAttribute(oplCummulativeRptIden,commonFilter);
			 response.setContentType("text/html");
			 commonFilter.setRowTotal('N');
			 
	    	 List< String[]> oplCummulativeList  = oplCummulativeService.getAlloplcumm(commonFilter);
	 			 
	      	 JSONObject jsonObject = null;
	      	 if(oplCummulativeList!=null && oplCummulativeList.size() > 0){
	      		
	      		 jsonObject = getTableModel(oplCummulativeList,FilterValues.getHeader( commonFilter.getDrillCaption()));
	      		
	      		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				// --uncommenting vignesh
//				jqGridTableModel.setRowNumbers(true);
//				jqGridTableModel.setEnableFilter(true);
//				jqGridTableModel.setTableButton(true);
//				gridColModel.setHeaderNum(1);
//				
//				String [] colHeader = oplCummulativeList.get(2);		// --- 2 to 1	
//				String [] colHeaderCond = oplCummulativeList.get(1);    // ---  1 to 0	
//				
//				//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
//				List<String[]> headers = new ArrayList<String[]>();
//				//headers.add(colHeaderCond);
//				headers.add(colHeader);
//				
//				 jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
//				jsonObject.put("tableHeight", "74%%");
//				jsonObject.put("tableWidth", "106%%");
//		      	CommonMessage.debugMsg("jsonObject ="+jsonObject);
		    	// --uncommenting vignesh
				
		      	httpSession.removeAttribute("oplCumReportColModel");
				//httpSession.setAttribute("oplCumReportColModel", jsonObject);
				 out.println(jsonObject);
	      	 }
	      	
			// }
			/* catch(Exception e)
			 {
				 CommonMessage.debugMsg("Exception in colModel Opl Cumulative"+e.getMessage());
			 }*/
		
		}
		else if( action.equals("OplCummulative_getData.oplcum") )
		{
			try
			{   
				 PrintWriter out = response.getWriter();
				 //commonFilter =(CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
				 //commonFilter =populateCommonFilter(request,oplCummulativeRptIden,false);
				 commonFilter =(CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
				// String flid=request.getParameter("flid");
				 commonFilter= FilterValues.getCommonFilters(request,commonFilter);
				String drillFlag=request.getParameter("drillFlag");
				 //commonFilter.setFlid(flid);
				if( drillFlag != null)
				 commonFilter.setDrillFlag(drillFlag.charAt(0));
				// CommonMessage.debugMsg("commonFilter"+commonFilter.getMonwise());
				 List< String[]> oplCummulativeList  = oplCummulativeService.getAlloplcumm(commonFilter);
				// CommonMessage.debugMsg("oplCummulativeList.size()"+oplCummulativeList.size());
				 if(oplCummulativeList.size()>3){ // -- chnaging 3 to 4 vignesh
					// CommonMessage.debugMsg("commonFilter.getTotalRecordCnt()"+commonFilter.getTotalRecordCnt());
					 JSONObject oplCummulativeData = convertToJqGridTableObjectOPL( oplCummulativeList,request,1,0,0,commonFilter.getTotalRecordCnt()+1);
				 	 out.println(oplCummulativeData);
				 }

		    }catch(Exception e)
			{
		    	e.printStackTrace();
				//CommonMessage.debugMsg("error = " +e.getMessage());
			}
		}
		else if( action.equals("OplCummulative_getExcel.oplcum")){
			
			//HttpSession httpSession = request.getSession(false);
			
		     commonFilter = populateCommonFilter(request,oplCummulativeRptIden,false);
		     FilterValues.getCommonFilters(request,commonFilter);
			 FilterValues.getOPLandKaizen(request,commonFilter);
			 
			 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
				   
		 	  }
			String tmpFromRow = commonFilter.getFromRow();
			//commonFilter.setFromRow(null);	
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			 
			String month1   = fromMonth +"  -  "+ toMonth ;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate ;
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			//JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("oplCumReportColModel");
			JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
			//tblJSONObj.put("title", "OPL Drilldown Report" + " - "+month1);
		 	//if( commonFilter.getMonwise().equals("Y")){
				 tblJSONObj.put("title", "OPL Cummulative Report" + " - "+month1);
			// }
			//tblJSONObj.put("title", "Equipment DownTime Report");
			//tblJSONObj.put("transpose", true);
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = oplCummulativeService.getAlloplcummExl(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "OplCummulativeReport", format);
			
		}
		/**----- For GENERATING GRAPH------- **/
		else if( action.equals("chart.oplcum"))
		{
			processChart(request,response);
		}
		else if(action.equals("oplcount_input.oplcum")){
			
			UIUtils.forwardRequest(request, response, "/pages/oplCount.jsp");
  }
	else if( action.equals("oplcount_getCol.oplcum")){
		buildTableCountColModel( request,response);
	}
	else if( action.equals("oplcount_getData.oplcum")){
		
		oplCountData(request , response);
		
	}
	else if( action.equals("oplcount_barchart.oplcum"))
	{
		processbarChart(request,response);
	}
else if( action.equals("oplcount_getExcel.oplcum")){
		oplCountExcel(request,response);
}
		else if (action.equals("oplTransaction_input.oplcum")) {
			String filterString = request.getParameter("filterStr");
			String compId = request.getParameter("flid");
			CommonMessage.debugMsg("My Test Start:" + compId + " sevlet comp   id" + filterString);
			request.setAttribute("compId", compId);
			request.setAttribute("filterStr", filterString);
			UIUtils.forwardRequest(request, response,"/pages/OplTransactionView.jsp");
		}

		else if (action.equals("oplTransaction_getCol.oplcum")) {
			String act = request.getParameter("checkVal");
			String firstClick = request.getParameter("firstClick");
			PrintWriter out = response.getWriter();
			commonFilter=populateCommonFilter(request, "TransactionViewCommonFilter", true);
			String flid = request.getParameter("flid");
			commonFilter.setFlid(flid);
			response.setContentType("text/html");
			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter
							.getMonwise().equals("Y"))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(
						-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,
						11));
				commonFilter.setMonwise("Y");
			}

			if (commonFilter.getCompany() == null) {
				String compId = request.getParameter("compId");
				ComboFilter company = new ComboFilter();
				company.setId(compId != null ? compId : "{}");
				commonFilter.setCompany(company);
			}
			if (commonFilter.getLocation() == null) {
				String compId = request.getParameter("locaId");
				ComboFilter company = new ComboFilter();
				company.setId(compId != null ? compId : "{}");
				commonFilter.setLocation(company);
			}

			if (commonFilter.getFactory() == null) {
				String compId = request.getParameter("factId");
				ComboFilter company = new ComboFilter();
				company.setId(compId != null ? compId : "{}");
				commonFilter.setFactory(company);
			}

			if (commonFilter.getSection() == null) {
				String compId = request.getParameter("sectId");
				ComboFilter company = new ComboFilter();
				company.setId(compId != null ? compId : "{}");
				commonFilter.setSection(company);
			}

			if (commonFilter.getCell() == null) {
				String compId = request.getParameter("cellId");
				ComboFilter company = new ComboFilter();
				company.setId(compId != null ? compId : "{}");
				commonFilter.setCell(company);
			}

			if (commonFilter.getMachine() == null) {
				String compId = request.getParameter("mchId");
				ComboFilter company = new ComboFilter();
				company.setId(compId != null ? compId : "{}");
				commonFilter.setMachine(company);
			}

			httpSession.removeAttribute("TransactionViewCommonFilter");
			httpSession.setAttribute("TransactionViewCommonFilter", commonFilter);

			
			//List<String[]> suggestionGridData=kaizenReportService.getKaizenSuggestionSummaryGridData(commonFilter);	
			List<String[]> OPLGridData  = oplService.getOPLSummaryGridData(commonFilter);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);

			gridColModel.setHeaderNum(1);

			String[] colHeader = OPLGridData.get(1); // vignesh
			String[] colHeaderCond = OPLGridData.get(0); // vignesh 
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "80%%");
			colModel.set("tableWidth", "110%%");
			httpSession.removeAttribute("suggestionSummaryColData");
			httpSession.setAttribute("suggestionSummaryColData", colModel);

			CommonMessage.debugMsg(colModel);
			out.println(colModel);

		} else if (action.equals("oplTransaction_getData.oplcum")) {
			String firstClick = request.getParameter("firstClick");
			CommonMessage.debugMsg("Suggestion View--- " + firstClick);
			PrintWriter out = response.getWriter();
			String flid = request.getParameter("flid");
			//CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			commonFilter=populateCommonFilter(request, "TransactionViewCommonFilter", false);
			commonFilter.setFlid(flid);
		/*	getComboSelectionId(commonFilter.getCompany());
			getComboSelectionId(commonFilter.getLocation());
			getComboSelectionId(commonFilter.getSection());
			getComboSelectionId(commonFilter.getFactory());
			getComboSelectionId(commonFilter.getCell());
			getComboSelectionId(commonFilter.getMachine());*/

			List<String[]> OPLGridData  = oplService.getOPLSummaryGridData(commonFilter); // vignesh chnaging 3 to 2
			JSONObject oplData = UIUtils.convertToJqGridTableObject(OPLGridData, request, 2, 0,commonFilter.getTotalRecordCnt());

			CommonMessage.debugMsg("Suggestion View Data " + OPLGridData);
			out.println(oplData);
		}

		else if (action.equals("oplTransaction_getExcel.oplcum")) {

			commonFilter = populateCommonFilter(request,"TransactionViewCommonFilter", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = (JSONObject) httpSession.getAttribute("suggestionSummaryColData");
			tableModel.put("title", "OPL Report ");
			String format = ExcelUtils.getFormat(request);
			Workbook wb =oplService.getOplSummaryGridDataExportExcel(commonFilter,tableModel,format);
			//Workbook wb = imprvmntSmryService.improvementSmrySubReportExportExcel(commonFilter,tableModel, format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb,"OPLReport", format);
		}

		
	}
	
	
		private JSONObject getTableModel(List<String[]> headers,String caption)
		{
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		//	String [] colHeader1 = new String[  headers.get(0).length + 1 ] ;
			
			String [] colHeader = headers.get(0) ;
			int header = colHeader.length; // length -1 to length vignesh
			String [] headerArr = new String[header];
			for(int k=0;k<headerArr.length;k++)
				

			//CommonMessage.debugMsg(colHeader1 +"  =caption ="+colHeader );
			colHeader[2] = caption;   
			CommonMessage.debugMsg("colHeader[1] ="+colHeader[1]); 
			
			//colHeader[1] = caption;  
		
			jqGridTableModel.setTableButton(true);	
			JqGridColModel jqGridColModel =getColModel("keyid1",50,"left");
			jqGridColModel.setHidden(true);
	
			jqGridTableModel.getColModel().add(jqGridColModel);
			jqGridTableModel.setRowNumbers(true);
			jqGridColModel = getColModel("keyid2",100,"left");
			jqGridTableModel.getColModel().add(jqGridColModel);
			//colHeader1[ 0 ] = colHeader[0];
			//colHeader1[ 1 ] = colHeader[1];
			headerArr[0] = "keyid";
			headerArr[1] = "keyid";
			String headerSql = "'SELECT ";
			for(int i =2; i < header; i++)
			{  // for debug vignesh
				CommonMessage.debugMsg("inside for colHeader["+i+"] = "+colHeader[i]); 
				
				headerArr[i] = colHeader[i].replaceAll(" ", "");
				//colHeader1[ i ] = colHeader[i];
				jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex((colHeader[i]+i).replaceAll(" ", ""));
				jqGridColModel.setName((colHeader[i]+i).replaceAll(" ", ""));
				
				
						
				if(i==2 )
				{
					jqGridColModel.setHidden(false);
					jqGridColModel.setAlign("left");
					jqGridColModel.setWidth(300);	
				}
				if(i>2)
				{
					jqGridColModel.setWidth(100);	
					jqGridColModel.setHidden(false);
					jqGridColModel.setAlign("right");
				}
				if(i==colHeader.length-1)
				{
					//jqGridColModel.setHidden(true);
				}
				jqGridTableModel.getColModel().add(jqGridColModel);
			//	headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
			}
			jqGridTableModel.getRowHeaders().add(headerArr);
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
			CommonMessage.debugMsg("headerSql.....123..."+headerSql);
			
			 	
				tableModel.set("tableHeight", "72%");
				return tableModel;
	      }
		
		private  JSONObject fillOPLCumulativeJqGrid(List<String[]> dataArrayList,HttpServletRequest request)
		{
			JSONObject tableDataObject = new JSONObject();
			String rowsStr = request.getParameter("rows");
			String pageStr = request.getParameter("page");
			int rows = 1000;
			if( rowsStr != null)
				rows = Integer.parseInt(rowsStr);
			
			int page = 1;
			if( pageStr != null)
				page = Integer.parseInt(pageStr);
			
			JSONArray rowArr = new JSONArray(); 
		
			if(dataArrayList!=null && dataArrayList.size() > 1){
			
				JSONObject rowObj = null ;
				JSONArray cell = null;
				int cnt = 0;
				int total =0;
				List<Integer> colTotal = new ArrayList<Integer>();
				List<Integer> cumulative = new ArrayList<Integer>();
				
				int colWiseTot = 0;
			
				int i=1 ;
				for(;i < dataArrayList.size(); i++ ){
					
					String [] curRow =(String[] )dataArrayList.get(i); 
					
					rowObj =new JSONObject();
	    			rowObj.put("id",i);
	    			
	    			cell =new JSONArray();
	    			cell.put(curRow[0]);
	    			rowObj.put("cell",cell);
	    			
	    			if( colTotal.size() > 0 )
	        			colTotal.remove(0);
	    			
	    			colTotal.add(0,0);
	    			
	    			cell.put(curRow[1]);
	    			
	    			rowObj.put("cell",cell);
	    			
	    			if( colTotal.size() > 1 )
	        			colTotal.remove(1);
	    			colTotal.add(1,0);
	    			
	    			total =0;
	    	
	    			for(int j=2 ;j < curRow.length; j++ ){
		    			cnt = Integer.parseInt(curRow[j]);
		    			cell.put(cnt);
		    			rowObj.put("cell",cell);
		    			total += cnt;
		    			
		    			colWiseTot = 0;
		        		if( colTotal.size() > j )
		        			colWiseTot = colTotal.get(j);
		      		
		        		colWiseTot += cnt;
		        	
		        		if( colTotal.size() > j )
		        			colTotal.remove(j);
		        		
		        		colTotal.add(j,colWiseTot);
					}
	    			if(Integer.valueOf(total)!=null)
	    				cell.put("<b>"+ total+ "</b>");
	    			
	    			rowObj.put("cell",cell);
					rowArr.put(rowObj);
				}
					
				cumulative.add(0,0);
				cumulative.add(1,0);
			
				for( int k = 2; k<colTotal.size(); k++)
				{	
					cnt = colTotal.get(k);
					CommonMessage.debugMsg("cnt= "+cnt);
						
					total += cnt;
					cumulative.add(total);
					
				}
				//cell.put("<b>"+cumulative+"</b>");
				tableDataObject.put("page", page); //current page
			//	tableDataObject.put("total",rowId); // total page
				tableDataObject.put("records", i+1); //total records
				
			  
		       rowObj = new JSONObject();
		       cell = JSONArray.fromCollection(colTotal);
		      
		       
		      cell.put(1, "<b><font color=blue>TOTAL</font> </b>");
		      cell.put(colTotal.size(),"<b>" + total + "</b>");
		       
		       
		       rowObj.put("id",i++);
		       rowObj.put("cell",cell);
		       rowArr.put(rowObj);	
		       
		       rowObj = new JSONObject();
		       cell = JSONArray.fromCollection(cumulative);
		      // cell.put("")
		       cell.put(1, "<b><font color=blue>CUMULATIVE </font> </b>");
		       cell.put(colTotal.size(), " ");
		      // cell.put("<b>"+cell+"</b>");
		       rowObj.put("id",i++);
		       rowObj.put("cell", cell );
		       rowArr.put(rowObj);
		       
		       tableDataObject.put("rows", rowArr);
			}
			else{
				tableDataObject.put("page", page); //current page
				tableDataObject.put("records", 0); //t
				tableDataObject.put("rows", rowArr);
			}
				
	       return tableDataObject;
		}
		
		private JqGridColModel getColModel (String colIndex, int width,String allign)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex);
			jqGridColModel.setName(colIndex);
			jqGridColModel.setHidden(true);			
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
				commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
				CommonMessage.debugMsg("commonFilter333333"+commonFilter.getMonwise());
				 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
					  commonFilter.setdFromDate(CommonFunctions.getFirstDateofMonth(0));
					  CommonMessage.debugMsg("INSIDE POPCOMMON FILTER");
					  commonFilter.setToDate(CommonFunctions.getDate());
				 	}
				 
				 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise()==null ||  commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  CommonMessage.debugMsg("flag"+commonFilter.getDrillLevel());
					  //if(UIUtils.isValidKeyId(commonFilter.getDrillLevel()))
					 // {
						 // if(commonFilter.getDrillLevel().equals(DrillLevelConstants.COMPANY))
							  commonFilter.setMonwise("Y");
					 // }
					
			 	  }
				 	 
				//commonFilter.setMonwise("Y");
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
		
		private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
			
		/*	if(commonFilter!= null){
				FilterValues.getCommonFilters(request, commonFilter) ;
			}
			else
				commonFilter =  new CommonFilter();
		*/	
			String forDashboard = request.getParameter("dashboard");
			
			String dashboardtype = request.getParameter("EMPLILLAR");
			
			CommonMessage.debugMsg(" :: dashboardtype :: Servlet ::"+dashboardtype);
			
			if( ! "true".equals(forDashboard)){
				CommonMessage.debugMsg("to check dash board");
				commonFilter = (CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
			}
			else{
				commonFilter = new  CommonFilter();
				CommonMessage.debugMsg("to check dash board true ");
				FilterValues.getCommonFilters(request,commonFilter);
				FilterValues.getOPLandKaizen(request, commonFilter);

				if(UIUtils.isValidKeyId(dashboardtype))
					commonFilter.setType(dashboardtype);
			}
			
			CommonFilter chartCommonFilter = new CommonFilter(); 
			BeanUtils.copyProperties(chartCommonFilter, commonFilter);
			
			//FilterValues.getCommonFilters(request, chartCommonFilter) ;
			
			chartCommonFilter.setRowTotal('Y');
			
			List<String[]> oplCummulativeList  = oplCummulativeService.getAlloplcumm(chartCommonFilter);
			
			JSONObject chartObj = null;
			if(oplCummulativeList != null && oplCummulativeList.size() > 0)
			{
				
				String flids=CommonFunctions.getLoginFlid(request);
				String lcnname=dashboardService.Functionallocn(flids);
				
				chartObj = processLineChart(lcnname,oplCummulativeList,chartCommonFilter);
			
				PrintWriter out = response.getWriter();
				UIUtils.dashBoardSetChartObject(request,chartObj);
				out.print(chartObj);
				out.close();
			}
		}
		
		private JSONObject processLineChart(String titlename,List<String[]> oplCummulativeList,CommonFilter commonFilter){
			//CommonMessage.debugMsg("oplCummulativeList size in processline char ="+oplCummulativeList.size());
			if( oplCummulativeList == null || oplCummulativeList.size() <= 2  )
				return null;
			
			ChartOptionBean lineChart =  new ChartOptionBean();
			List<String> xAxisCategory = new ArrayList<String>();
			List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
			List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
			
			String[] header =  oplCummulativeList.get(0);
			String[] month =  oplCummulativeList.get(1);
			String[] data =  oplCummulativeList.get(oplCummulativeList.size()-1);
			
			StringBuilder date = new StringBuilder();
			
			if(commonFilter.getMonwise().equals("Y"))
			{
				date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
			}
			else
				date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		
			String drillLevel =  FilterValues.getDrillHeader(data[1]);
			StringBuilder title = new StringBuilder( titlename+"-OPL Cumulative Report - ").append( drillLevel).append( " Wide From ").append(date);
		
			
			String prevMonth = null;
			
			String subTitle = "";//data[2];
			//CommonMessage.debugMsg("subTitle "+subTitle );
			
			ChartSeries timeSeries = new ChartSeries();
			List<Double> cumulativeData = new ArrayList<Double>();
			
			for( int i = 3;i < header.length-1;i++ ){
				if (!UIUtils.isValidKeyId(data[i]))
					data[i]="0";
					cumulativeData.add(Double.parseDouble(data[i]));
				
					if( prevMonth == null || ! month[i].equals(prevMonth) ){
					xAxisCategory.add(month[i]);
				}
				prevMonth = month[i];
			}
			if(cumulativeData.size() > 0 )
			{
				timeSeries.setData(cumulativeData);
				timeSeries.setType(ChartTypes.SPLINE);
				timeSeries.setName("OPL Cumulative");
				chartSeriesList.add(timeSeries);
				
				ChartYAxis yAxis = new ChartYAxis(); 
				yAxis.setMin(0);
				yAxis.getTitle().setText("Numbers");
				chartYAxis.add(yAxis);
			}
			ChartXAxis xaxis = new ChartXAxis();
			if(commonFilter.getMonwise().equals("Y"))
				xaxis.getTitle().setText("Month");
			else
				xaxis.getTitle().setText("Date");
			lineChart.getxAxis().setTitle(xaxis.getTitle());
			return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
			
		}
		
		public static JSONObject convertToJqGridTableObjectOPL(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, int colSub, long totalRecords){
			String rowsStr = request.getParameter("rows");
			String pageStr = request.getParameter("page");
			int rows = 100;
			if( rowsStr != null)
				rows = Integer.parseInt(rowsStr);
			
			int page = 1;
			if( pageStr != null)
				page = Integer.parseInt(pageStr);
			
			JSONObject tableDataObject = new JSONObject();
			
			CommonMessage.debugMsg(" totalRecords " + totalRecords);
			
			tableDataObject.put("page", page); //current page
			tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
			//if( page == 1)
			tableDataObject.put("records", totalRecords - rowStart); //total records
			
			JSONArray rowArr = new JSONArray(); 
	       
	      
			int rowId = rows * (page-1);
			int slno = 0;
	        for( String [] row : dataArrayList)
			{
	        	if( slno++ >= rowStart )
	        	{	
		    	    JSONObject rowObj =new JSONObject();
		    	    	
		    	    rowObj.put("id",rowId -rowStart +1);
		            
		            JSONArray cell=new JSONArray();
		            // colsub -1 to colsub -- vignesh
		            for( int i = colStart ;i < (row.length - colSub); i++)
		            {	 
		            	cell.put( ( row[i] != null ?row[i].isEmpty() ?" ":  row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") ); 
		            }	
		            rowObj.put("cell",cell);
		            
		            rowArr.put(rowObj);
	        	}
	        	rowId++;
	       }

	        tableDataObject.put("rows", rowArr);
	        
	        return tableDataObject;
		}
		private void buildTableCountColModel(HttpServletRequest request,HttpServletResponse response) throws Exception{
			
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");

			CommonFilter commonFilter = null;
			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				 commonFilter = (CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
			}
			
			if (commonFilter == null)
				commonFilter = new CommonFilter();
			
			commonFilter = populateCommonFilter(request,oplCummulativeRptIden,true);
			
			httpSession.removeAttribute(oplCummulativeRptIden);
			httpSession.setAttribute(oplCummulativeRptIden, commonFilter);
			CommonMessage.debugMsg(" Constants.passNullDate " + Constants.passNullDate + " commonFilter.getFromMonth() " +  commonFilter.getFromMonth());
			
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
			commonFilter.setIsGetCol("Y");
			List<String[]> yyData = oplCummulativeService.getOplCountData(commonFilter);
			JSONObject jsonObject = getTableModel(yyData,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "80%%");
		    jsonObject.set("tableWidth", "104%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			
			out.println(jsonObject);
			

			}


			private void oplCountExcel(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
			  CommonFilter commonFilter = populateCommonFilter(request,oplCummulativeRptIden,false);
			    
				
				JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
				
				tblJSONObj.put("title", "OPLCount");
				
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = oplCummulativeService.getOplCountExcel(commonFilter,tblJSONObj,format);
				
				
				ExcelUtils.writeToResponse(response, wb, "OPLCount", format);
				
			}
			
			private void oplCountData(HttpServletRequest request,HttpServletResponse response)throws Exception {
			CommonFilter commonFilter = populateCommonFilter(request,oplCummulativeRptIden,false);
			commonFilter.setIsGetCol("N");
			List<String[]> impVscomList = oplCummulativeService.getOplCountData(commonFilter);
			
			JSONObject listToJsonObject = new JSONObject();

			if (impVscomList != null && impVscomList.size() > 1)
				
				listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 0,commonFilter.getTotalRecordCnt());
			
			PrintWriter out = response.getWriter();
			out.println(listToJsonObject);

			}

			
			private void processbarChart(HttpServletRequest request,HttpServletResponse response) throws Exception{
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
			
			String forDashboard = request.getParameter("dashboard");
			
			if( ! "true".equals(forDashboard)){
				commonFilter = (CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
			}
			else{
				commonFilter = new  CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter) ;
				FilterValues.getBDRelated(request, commonFilter);		
			}
			
			CommonFilter chartCommonFilter = new CommonFilter(); 
			BeanUtils.copyProperties(chartCommonFilter, commonFilter);
			
			//FilterValues.getCommonFilters(request, chartCommonFilter) ;
			
			chartCommonFilter.setRowTotal('Y');
			
			List<String[]> whywhyList  = oplCummulativeService.getOplCountData(chartCommonFilter);
			
			CommonMessage.debugMsg("whywhyList"+whywhyList);
			JSONObject chartObj = null;
			if(whywhyList != null && whywhyList.size() > 0)
			{	
				String flids=CommonFunctions.getLoginFlid(request);
				CommonMessage.debugMsg("flid fff is :::"+flids);
				String lcnname=dashboardService.Functionallocn(flids);
				chartObj = processBarChart2(lcnname,whywhyList,chartCommonFilter);
			
				PrintWriter out = response.getWriter();
				UIUtils.dashBoardSetChartObject(request,chartObj);
				out.print(chartObj);
				out.close();
			}
				
			}
		private JSONObject processBarChart2(String titlenme,List<String[]> oplCummulativeList,CommonFilter commonFilter){

			if( oplCummulativeList == null || oplCummulativeList.size() <= 1  )
				return null;
			ChartOptionBean lineChart =  new ChartOptionBean();
			List<String> xAxisCategory = new ArrayList<String>();
			List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
			List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();			
			String[] month =  oplCummulativeList.get(0);
			String[] data =  oplCummulativeList.get(oplCummulativeList.size()-2);
			String prevMonth = null;
			
			//String subTitle = data[2];
			String subTitle="";
			CommonMessage.debugMsg("subTitle"+subTitle);
			StringBuilder date = new StringBuilder();
			if(commonFilter.getRowTotal() == null )
				commonFilter.setRowTotal('N');
			if(commonFilter.getMonwise().equals("Y"))
			{
				date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
			}
			else
				date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
			
		
			String drillLevel =  FilterValues.getDrillHeader(data[1]);
			CommonMessage.debugMsg("drillLevel"+drillLevel);
			StringBuilder title = new StringBuilder(titlenme+"-OPL Count - ").append( drillLevel).append( " Wide From ").append(date);
			ChartSeries timeSeries = new ChartSeries();
			List<Double> countData = new ArrayList<Double>();
			for( int i = 3;i < month.length-1;i++ ){
				countData.add(Double.parseDouble(data[i]));
				
					if( prevMonth == null || ! month[i].equals(prevMonth) ){
					xAxisCategory.add(month[i]);
				}
				prevMonth = month[i];
			}
			if(countData.size() > 0 )
			{
				timeSeries.setData(countData);
				timeSeries.setType(ChartTypes.COLUMN);
				timeSeries.setName("OPL Count");
				chartSeriesList.add(timeSeries);
				
				ChartYAxis yAxis = new ChartYAxis(); 
				yAxis.setMin(0);
				yAxis.getTitle().setText("Numbers");
				chartYAxis.add(yAxis);
			}
			ChartXAxis xaxis = new ChartXAxis();
			if(commonFilter.getMonwise().equals("Y"))
				xaxis.getTitle().setText("Month");
			else
				xaxis.getTitle().setText("Date");
			lineChart.getxAxis().setTitle(xaxis.getTitle());
			return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
			
		}

			
			
	 }


				