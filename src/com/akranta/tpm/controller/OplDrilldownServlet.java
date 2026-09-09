
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.OplDrilldownService;

import com.akranta.tpm.service.impl.OplDrilldownServiceImpl;
import com.akranta.tpm.service.impl.OplServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

 

public class OplDrilldownServlet extends HttpServlet {
	
	/**
	 * Created By:Siddharth.A
	 * Modified By :N.Arun
	 * Modified By :Siddharth.A
	 */
	private static final long serialVersionUID = 1L;
	OplDrilldownService oplDrilldownService ; 
	private static final String commonFilterIden="oplDrillcommonFilter";
	public OplDrilldownServlet(){
	/*	try {
			oplDrilldownService = new OplDrilldownServiceImpl();
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
	
		CommonFilter commonFilter =null;	
		HttpSession httpSession = request.getSession(false);
		
		String action = UIUtils.getActionPart(request);
		try {
			oplDrilldownService = (OplDrilldownServiceImpl)UIUtils.getServiceObject(request,"OplDrilldownServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		if( action.equals("filterXmlOplDrlDwnRpt_input.oplDrlDnRpt")){
			 response.setContentType("xml"); 
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/OPLDrilDown.xml") ;
		 }
		
		else if(action.equals("OplDrlDwnRpt_input.oplDrlDnRpt")) 
		{
			request.setAttribute("oplDrillDwnMsg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg") );
			UIUtils.forwardRequest(request, response, "/pages/OplDrilldown.jsp");
		}
		else if(action.equals("OplDrlDwnRpt_getCol.oplDrlDnRpt"))
		{
			
			//populateCommonFilter(request,"OPLDrillDownCommonFilter",true);
			 String firstClick =request.getParameter("firstClick");
			
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 
				 commonFilter =(CommonFilter) httpSession.getAttribute(commonFilterIden);
			 }	
			 if(commonFilter==null)
				 commonFilter = new CommonFilter();
			 FilterValues.getCommonFilters(request,commonFilter);
			 FilterValues.getOPLandKaizen(request,commonFilter);
			 CommonMessage.debugMsg(commonFilter.getFlid()+ " flid filtercondsql");
			 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
				  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
				  commonFilter.setToDate(CommonFunctions.getDate());
				 // commonFilter.setMonwise(" ");
		 	  } 
			
			
			 httpSession.removeAttribute(commonFilterIden);
			 httpSession.setAttribute(commonFilterIden,commonFilter);
			
			 
			PrintWriter out = response.getWriter(); 
			String colmodel=UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRpt","opldrill");
			JSONObject colModelJson= JSONObject.fromString(colmodel);	
			httpSession.removeAttribute("opldrlReportColModel");
			httpSession.setAttribute("opldrlReportColModel", colModelJson);
			JSONArray colNames= (JSONArray) colModelJson.get("rowHeaders");
			
			JSONArray headers = (JSONArray)colNames.get(0);
			headers.put(1,FilterValues.getHeader( commonFilter.getDrillCaption()));
			
			
			colNames.put(0,headers);
			colModelJson.set("rowHeaders", colNames);
			CommonMessage.debugMsg(" ccolModelJson " + FilterValues.getHeader( commonFilter.getDrillCaption()));
			
			out.print(colModelJson);

		}
		else if( action.equals("OplDrlDwnRpt_getData.oplDrlDnRpt") )
		{
			try
			{	  
			
				// UIUtils.displayRequestParamsValue(request);
				 PrintWriter out = response.getWriter();
				
				// commonFilter = populateCommonFilter(request,"OPLDrillDownCommonFilter",false);
				 commonFilter =(CommonFilter) httpSession.getAttribute(commonFilterIden);	
				 //String flid=request.getParameter("flid");
				 String drillflag=request.getParameter("drillFlag");
				 
				 String chkRemoveBlank=request.getParameter("chkRemoveBlank");
				 //commonFilter.setFlid(flid);
				 commonFilter.setDrillFlag(drillflag.charAt(0));
				// commonFilter.setChk(chkRemoveBlank);
				// CommonMessage.debugMsg("flid ::::: "+commonFilter.getFlid());
		      	 response.setContentType("text/html");
			     
				 List< String[]> opldrillList  = oplDrilldownService.getAllopldrill(commonFilter);
				 JSONObject opldrillData = UIUtils.convertToJqGridTableObject(opldrillList,request,0,1);
				 if( opldrillList.size() <= 0 )
					 FilterValues.setBackwardDrillLevel(commonFilter,"");
				 	
				 out.println(opldrillData);
				 commonFilter.setViewClick('N');
  			 	
  			 	 httpSession.removeAttribute(commonFilterIden);
  			 	 httpSession.setAttribute(commonFilterIden, commonFilter);

				 
			}
			
		   catch(Exception e)
			{
				CommonMessage.debugMsg("error " +e.getMessage());
			}
		}
		else if( action.equals("OplDrlDwnRpt_getExcel.oplDrlDnRpt")){
			
			//HttpSession httpSession = request.getSession(false);
		    commonFilter = populateCommonFilter(request,commonFilterIden,false);
			String tmpFromRow = commonFilter.getFromRow();
			//commonFilter.setFromRow(null);	
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			
			String month1   = fromMonth +"  -  "+ toMonth ;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate ;
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			//JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("opldrlReportColModel");
			JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
	
			tblJSONObj.put("title", "OPL Drilldown Report" + " - "+date);
		//	if( commonFilter.getMonwise().equals("Y")){
				//tblJSONObj.put("title", "One Point Lesson Report" + " - "+month1);
			//}
			//tblJSONObj.put("title", "Equipment DownTime Report");
			// tblJSONObj.put("transpose", true);
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = oplDrilldownService.getAllopldrillExl(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "OplDrillDownReport", format);
			
		}
		
	}

	private JSONObject getTableModel(List<String[]> headers)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);			
		jqGridTableModel.getRowHeaders().add(colHeader);
		for(int i =0; i < colHeader.length; i++)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
					
			jqGridColModel.setWidth( 100);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0 ||i==1)
			{
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}
		/*	if(i==2)
			{
				jqGridColModel.setSummaryType("count");
				jqGridColModel.setSummaryTpl("<b><font color=blue>Plant </font> </b>");
			}
			else
			{
			jqGridColModel.setSummaryType("sum");
			jqGridColModel.setSummaryTpl("<b><font color=blue>{0} </font> </b>");
			}*/
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
		
	/*	private  JSONObject fillOPLJqGrid(List<String[]> dataArrayList,HttpServletRequest request,int rowStart)
		{
			
			String rowsStr = request.getParameter("rows");
			String pageStr = request.getParameter("page");
			int rows = 1000;
			if( rowsStr != null)
				rows = Integer.parseInt(rowsStr);
			
			int page = 1;
			if( pageStr != null)
				page = Integer.parseInt(pageStr);
			
			JSONObject tableDataObject = new JSONObject();
			
			if( dataArrayList != null && dataArrayList.size() > 0){
				
				JSONArray rowArr = new JSONArray(); 
		       
				int rowId = 0;
				int i= 0;
				String prevKeyid = null;
				JSONObject rowObj = null ;
				JSONArray cell = null;
				int cnt = 0;
				int total =0;
				while( i < dataArrayList.size())
				{
					String [] curRow =(String[] )dataArrayList.get(i); 
					
		        	if( rowId >= rowStart )
		        	{	
		        	CommonMessage.debugMsg("rowId="+rowId);
		        	CommonMessage.debugMsg("prevKeyid="+prevKeyid);
		        	
		        		if( ! curRow[ 0  ].equals(prevKeyid)  )
		        		{
		        			if( prevKeyid != null )
		        			{
		        				cell.put( total ); 
		        				rowObj.put("cell",cell);
			  		            rowArr.put(rowObj);
			  		            rowId++;
			  		            total = 0;
		        			}
		        			rowObj =new JSONObject();
		        			rowObj.put("id",rowId+1);
		        			cell =new JSONArray();
		        			cell.put( ( curRow[ 0 ] != null ? curRow[0].replace("{", "").replace("}", "").replace("[", "").replace("]", "") :" ") );
		        			cell.put( ( curRow[ 1 ] != null ? curRow[1].replace("{", "").replace("}", "").replace("[", "").replace("]", "") :" ") );
		        			//cell.put( ( curRow[ 2 ] != null ? curRow[2].replace("{", "").replace("}", "").replace("[", "").replace("]", "") :" ") );
		        			
		        		}
		        		cnt = Integer.parseInt(curRow[ 2 ]) ;
		        		CommonMessage.debugMsg("Count:"+cnt);
		        		total += cnt; 
		        		cell.put( cnt ); 
		        	}
		        	prevKeyid = curRow[ 0 ]; // @keyid - to compare next row
		        	i++;
		       }
		
			  tableDataObject.put("page", page); //current page
			  tableDataObject.put("total",(rowId-rowStart ) + 1 ); // total page
			  tableDataObject.put("records", (rowId-rowStart ) + 1); //total records
				
			  
		      // cell = JSONArray.fromCollection(total);
			  	cell.put( total ); 
			   cell.put(1, "<b>TOTAL</b>");
		       rowObj.put("id",rowId+2);
			   rowObj.put("cell",cell);
		       rowArr.put(rowObj);	
			
		       tableDataObject.put("rows", rowArr);
			}	
			else{
				tableDataObject.put("page", 0); //current page
				tableDataObject.put("total",0); // total page
				tableDataObject.put("records", 0); //total records
				
			}    
	       return tableDataObject;
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
				commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
	private  JSONObject fillOPLJqGrid(List<String[]> dataArrayList,HttpServletRequest request,int rowStart)
	{
		//CommonMessage.debugMsg("servlet fillRootJqGrid " );
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 1000;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr);
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
		
		JSONObject tableDataObject = new JSONObject();
		
	
		JSONArray rowArr = new JSONArray(); 
       
      
		int rowId = 0;
		int i= 0;
		String prevKeyid = null;
		JSONObject rowObj = null ;
		JSONArray cell = null;
		JSONArray value = null;
		int cnt = 0;
		int total =0;
		List<Integer> colTotal = new ArrayList<Integer>();
		
		int colWiseTot = 0;
		int colIndex = 0;
		
		while( i < dataArrayList.size())
		{
			String [] curRow =(String[] )dataArrayList.get(i); 
			
        	if( rowId >= rowStart )
        	{	
        		if( ! curRow[ 0  ].equals(prevKeyid)  )
        		{
        			if( prevKeyid != null )
        			{
        				
        				calculateTotal(colTotal,colIndex ,  total,  cell,  rowObj, rowArr);
	  		            rowId++;
	  		            total = 0;
	  		            
	  		            colIndex = 0;
        			}
        			rowObj =new JSONObject();
        			rowObj.put("id",rowId+1);
        			cell =new JSONArray();
        			
        			cell.put( ( curRow[ 0 ] != null ? curRow[0].replace("{", "").replace("}", "").replace("[", "").replace("]", "") :" ") );
        		
        			
        			if( colTotal.size() > colIndex )
	        			colTotal.remove(colIndex);
        			colTotal.add(colIndex,0);
        			
        			colIndex++;
        			cell.put( ( curRow[ 1 ] != null ? curRow[1].replace("{", "").replace("}", "").replace("[", "").replace("]", "") :" ") );
        			if( colTotal.size() > colIndex )
        				colTotal.remove(colIndex);
        			
        			colTotal.add(colIndex,0);
        			colIndex++;
        			
        	//		cell.put( ( curRow[ 2 ] != null ? curRow[2].replace("{", "").replace("}", "").replace("[", "").replace("]", "") :" ") );
        			
        		}
        		cnt = Integer.parseInt(curRow[2]) ;
        		colWiseTot = 0;
        		CommonMessage.debugMsg("////////////"+colTotal.size());
        		if( colTotal.size() > colIndex )
        			colWiseTot = colTotal.get(colIndex);
        		
        		colWiseTot += cnt;
        		if( colTotal.size() > colIndex )
        			colTotal.remove(colIndex);
        		
        		colTotal.add(colIndex,colWiseTot);
        		
        		total += cnt; 
        		cell.put(cnt);
        		
        		colIndex++;
        	}
        	prevKeyid = curRow[ 0 ]; // @keyid - to compare next row
        	i++;
       }
		
		tableDataObject.put("page", page); //current page
	//	tableDataObject.put("total",rowId); // total page
		tableDataObject.put("records", rowId+2); //total records
	   
	   CommonMessage.debugMsg("total " + total);	
	   cell.put("<b>" + total + "</b>"); 
	   rowObj.put("cell",cell);
	  // if( rowId == 0){	
		   calculateTotal(colTotal,colIndex ,  total,  cell,  rowObj, rowArr);
	   //}
	   //else
		//   rowArr.put(rowObj);	
       
       rowObj = new JSONObject();
       cell = JSONArray.fromCollection(colTotal);
       //for( int j = 2; j<colTotal.size();j++)
    	
       //   cell.put(value)  
     
       
       cell.put(1, "<b>TOTAL</b>");
       rowObj.put("id",total);
       rowObj.put("cell",cell);
       rowArr.put(rowObj);	
       
       tableDataObject.put("rows", rowArr);
    

       return tableDataObject;
	}
	
	private void calculateTotal(List<Integer> colTotal, int colIndex , int total, JSONArray cell, JSONObject rowObj,JSONArray rowArr){
		int colWiseTot = 0;
		if( colTotal.size() > colIndex )
			colWiseTot = colTotal.get(colIndex);
		
		colWiseTot += total;
		if( colTotal.size() > colIndex )
			colTotal.remove(colIndex);
		
		colTotal.add(colIndex,colWiseTot);
		
		cell.put("<b>" + total + "</b>"); 
		rowObj.put("cell",cell);
        rowArr.put(rowObj);
	}
}
		