
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
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.BAL_BDFailureRptService;
import com.akranta.tpm.service.impl.ActionPlanRptServiceImpl;
import com.akranta.tpm.service.impl.BAL_BDFailureRptServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenTlAssemblymstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_BDFailureReportServlet extends HttpServlet{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	//CommonFilter commonFilter;
	BAL_BDFailureRptService bDFailureRptService;
	
	public BAL_BDFailureReportServlet() throws Exception
	{
		super();
		
		//commonFilter=new CommonFilter();
		//bDFailureRptService = new BDFailureRptServiceImpl();
		
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
		String action = UIUtils.getActionPart(request);
		CommonFunctions.debugMsg(action);
		
		try {
			//actionPlanRptService = (ActionPlanRptServiceImpl) UIUtils.getServiceObject(request, "ActionPlanRptServiceImpl");
			bDFailureRptService = (BAL_BDFailureRptServiceImpl)UIUtils.getServiceObject(request,"BDFailureRptServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}

		
		//Failure
		if( action.equals("filterXmlBDFailureRpt_input.bdFailRpt")){
			response.setContentType("xml"); 
			CommonFunctions.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/BDFailureReport.xml") ;
		}
		else if(action.equals("BDFailureRpt_input.bdFailRpt"))
		{
			CommonFunctions.debugMsg("Brfore jsp");
			//RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/Abnormality.jsp"); 
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/BDFailureRate.jsp");
			rd.forward(request, response); 
		}
		
		else if(action.equals("BDFailureRpt_getCol.bdFailRpt"))
		{	
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"BDFailureRptCommonFilter",true);
			//commonFilter.setMonwise("Y");
			commonFilter.setChkfrequencychkbox("1");
			commonFilter.setChkseveritychkbox("0");
		
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
			List<String[]> bDFailureRpt  = bDFailureRptService.getFrequency(commonFilter);
			httpSession.removeAttribute("BDFailureRptCommonFilter");
			httpSession.setAttribute("BDFailureRptCommonFilter", commonFilter);
			CommonFunctions.debugMsg("jhCompRptList"+bDFailureRpt.size());
			JSONObject bDFailureData = UIUtils.convertToJqGridTableObject(bDFailureRpt,request,0,0,commonFilter.getTotalRecordCnt()+1); 
			httpSession.setAttribute("abnReportServletdata", bDFailureData);

			CommonFunctions.debugMsg("accidentInvestList   "+bDFailureRpt.size());
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(false);
			
			gridColModel.setHeaderNum(1);
			
			String [] colHeader1 = bDFailureRpt.get(1);
			String [] colHeaderCond = bDFailureRpt.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader1);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("data", bDFailureData);
			CommonFunctions.debugMsg("colModel   "+jsonObject);
			
			//JSONObject jsonObject = getTableModel(bDFailureRpt,commonFilter);
			 jsonObject.set("tableWidth", "110%%");
			 jsonObject.set("tableHeight", "106%%"); //860
			CommonFunctions.debugMsg(jsonObject);
			httpSession.removeAttribute("bDFailureRptColModel");
			httpSession.setAttribute("bDFailureRptColModel", jsonObject);
			out.println(jsonObject);
		}
		
		else if( action.equals("BDFailureRpt_getData.bdFailRpt") )
		{
			PrintWriter out = response.getWriter();
			try
			{
				 UIUtils.displayRequestParamsValue(request);
				 String page = request.getParameter("page");	
				 CommonFunctions.debugMsg("page......"+page);
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"BDFailureRptCommonFilter",false);
				 JSONObject jsonObject = new JSONObject();
				// if(page.equals("1"))
	        		// jsonObject = (JSONObject) httpSession.getAttribute("BDFailureReportServletdata");
	        	// else
	        	 //{				
				 commonFilter.setChkfrequencychkbox("1");
					commonFilter.setChkseveritychkbox("0");
	        		 List<String[]> abnormality  = bDFailureRptService.getFrequency( commonFilter);
	        		 jsonObject = UIUtils.convertToJqGridTableObject(abnormality,request,2,0,commonFilter.getTotalRecordCnt()+1); 
	        	 //}
				
				 out.println(jsonObject);
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("BDFailureRptCommonFilter");
	  			 httpSession.setAttribute("BDFailureRptCommonFilter", commonFilter);
				/*JSONObject abnData = null;
				HttpSession session = request.getSession();			
					abnData = (JSONObject)session.getAttribute("abnData");			
					CommonFunctions.debugMsg("abnDate:"+abnData );
  			 	out.println(abnData );*/

		    }
			catch(Exception e)
			{
				//CommonFunctions.debugMsg(e.getMessage());
			}
		}else if( action.equals("BDFailureRpt_getExcel.bdFailRpt")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"BDFailureRptCommonFilter",false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);

			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("bDFailureRptColModel");
			
			tblJSONObj.put("title", "BreakDown Failure Report");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = bDFailureRptService.failureRateExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "BreakDownFailureReport", format);
			
		}
		else if( action.equals("filterXmlBDSplitRpt_input.bdFailRpt")){
			response.setContentType("xml"); 
			CommonFunctions.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/BDSplitReport.xml") ;
		}
		else if(action.equals("BDSplitRpt_input.bdFailRpt"))
		{
			CommonFunctions.debugMsg("Brfore jsp");
			String bdSplitView =request.getParameter("selid");	
			httpSession.removeAttribute("BrkDownSplitRptView");
  			httpSession.setAttribute("BrkDownSplitRptView", bdSplitView);
			//RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/Abnormality.jsp"); 
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/BDSplitRpt.jsp");
			rd.forward(request, response); 
		}
		
		else if(action.equals("BDSplitRpt_getCol.bdFailRpt"))
		{	
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"BDSplitRptCommonFilter",true);
			
			List<String[]> bdSplitList  = bDFailureRptService.getBDSplit(commonFilter);
			CommonFunctions.debugMsg("bdSplitList"+bdSplitList.size());
			JSONObject bdSplitData = UIUtils.convertToJqGridTableObject(bdSplitList,request,1,0,commonFilter.getTotalRecordCnt()+1); 
			CommonFunctions.debugMsg(bdSplitData);
			
			JSONObject jsonObject = getSplitTableModel(bdSplitList);
			jsonObject.set("tableHeight", "280");
			jsonObject.set("tableWidth", "1100");
			CommonFunctions.debugMsg(jsonObject);
			httpSession.removeAttribute("BDSplitRptColModel");
			httpSession.setAttribute("BDSplitRptColModel", jsonObject);
			out.println(jsonObject);
		}
		
		else if( action.equals("BDSplitRpt_getData.bdFailRpt") )
		{
			PrintWriter out = response.getWriter();
			try
			{
				 UIUtils.displayRequestParamsValue(request);
				 String page = request.getParameter("page");	
				 CommonFunctions.debugMsg("page......"+page);
				 //httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"BDSplitRptCommonFilter",false);
				 JSONObject jsonObject = new JSONObject();
								
				
	        	 List<String[]> bdSplitList  = bDFailureRptService.getBDSplit(commonFilter);
	        	 jsonObject = UIUtils.convertToJqGridTableObject(bdSplitList,request,1,0,commonFilter.getTotalRecordCnt()+1); 
	        	 
				
				 out.println(jsonObject);
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("BDSplitRptCommonFilter");
	  			 httpSession.setAttribute("BDSplitRptCommonFilter", commonFilter);
			}
			catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("BDSplitRpt_getExcel.bdFailRpt")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"BDSplitRptCommonFilter",false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("BDSplitRptColModel");
			
			tblJSONObj.put("title", "BreakDown Split Breakup");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = bDFailureRptService.splitBreakupExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "BreakDownSplitBreakup", format);
			
		}
		//Severity
		//BdFailure
		if( action.equals("filterXmlBDSeverityRpt_input.bdFailRpt")){
			response.setContentType("xml"); 
			CommonFunctions.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/BDFailureReport.xml") ;
		}
		else if(action.equals("BDSeverityRpt_input.bdFailRpt"))
		{
			CommonFunctions.debugMsg("Brfore jsp");
			//RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/Abnormality.jsp"); 
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/BDFailureRate.jsp");
			rd.forward(request, response); 
		}
		
		else if(action.equals("BDSeverityRpt_getCol.bdFailRpt"))
		{	
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"BDSeverityRptCommonFilter",true);
		//	commonFilter.setMonwise("Y");
			commonFilter.setChkseveritychkbox("1");
			commonFilter.setChkfrequencychkbox("0");
			List<String[]> bDFailureRpt  = bDFailureRptService.getFrequency(commonFilter);
			CommonFunctions.debugMsg("jhCompRptList"+bDFailureRpt.size());
			JSONObject bDFailureData = UIUtils.convertToJqGridTableObject(bDFailureRpt,request,0,0,commonFilter.getTotalRecordCnt()+1); 
			httpSession.setAttribute("abnReportServletdata", bDFailureData);
			CommonFunctions.debugMsg("accidentInvestList   "+bDFailureRpt.size());
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(false);
			
			gridColModel.setHeaderNum(1);
			
			String [] colHeader1 = bDFailureRpt.get(1);
			String [] colHeaderCond = bDFailureRpt.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader1);
			
			JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			colModel.put("data", bDFailureData);
			CommonFunctions.debugMsg("colModel   "+colModel);
			
			
			//JSONObject colModel = getTableModel(bDFailureRpt,commonFilter);
			colModel.set("tableWidth", "110%%");
			colModel.set("tableHeight", "105%%");
			httpSession.removeAttribute("bDSeverityRptColModel");
			httpSession.setAttribute("bDSeverityRptColModel", colModel);
			out.println(colModel);
				

		}
		
		else if( action.equals("BDSeverityRpt_getData.bdFailRpt") )
		{
			PrintWriter out = response.getWriter();
			try
			{
				 UIUtils.displayRequestParamsValue(request);
				 String page = request.getParameter("page");	
				 CommonFunctions.debugMsg("page......"+page);
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"BDSeverityRptCommonFilter",false);
				 commonFilter.setChkseveritychkbox("1");
				commonFilter.setChkfrequencychkbox("0");
				 JSONObject jsonObject = new JSONObject();
						
        		 List<String[]> abnormality  = bDFailureRptService.getFrequency( commonFilter);
        		 jsonObject = UIUtils.convertToJqGridTableObject(abnormality,request,2,0,commonFilter.getTotalRecordCnt()+1); 
	        
				 out.println(jsonObject);
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("BDSeverityRptCommonFilter");
	  			 httpSession.setAttribute("BDSeverityRptCommonFilter", commonFilter);
				/*JSONObject abnData = null;
				HttpSession session = request.getSession();			
					abnData = (JSONObject)session.getAttribute("abnData");			
					CommonFunctions.debugMsg("abnDate:"+abnData );
  			 	out.println(abnData );*/
		    }
			catch(Exception e)
			{
				//CommonFunctions.debugMsg(e.getMessage());
			}
		}else if( action.equals("BDSeverityRpt_getExcel.bdFailRpt")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"BDSeverityRptCommonFilter",false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("bDSeverityRptColModel");
			
			tblJSONObj.put("title", "BreakDown Severity Report");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = bDFailureRptService.failureRateExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "BreakDownSeverityReport", format);
			
		}
	}
	private JSONObject getTableModel(List<String[]> headers,CommonFilter commonFilter)
	{
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		 
		String[] colHeader = headers.get(0);
		
		String[] emptyrow = new String[colHeader.length]; 	
		

		emptyrow [0] ="";
		emptyrow [1] ="";
				
		for( int i = 2; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}		
		
		//jqGridTableModel.getRowHeaders().add(emptyrow);		
		jqGridTableModel.getRowHeaders().add(colHeader);
		
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		jqGridTableModel.setTableHeight(300);
	//	jqGridTableModel.getColModel().add(getColModel("keyid", 50,"left",true,false,true));
		String headerSql = "'SELECT "; 
		for(int i =0; i < colHeader.length; i++)
		{
			CommonFunctions.debugMsg("Length : "+colHeader[i]);
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setWidth(100);				
			//jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			if(i==0 || i==1)
			{
				jqGridColModel.setHidden(true);
				if(i==1)
					jqGridColModel.setKey(true);
			
			}
			if(i == 2)
			{
				jqGridColModel.setWidth(320);
				jqGridColModel.setAlign("left");
			}
			if(i>2)
			{
				//jqGridColModel.setWidth(320);
				
				jqGridColModel.setAlign("right");
				if(i == colHeader.length-1)
				{
					jqGridColModel.setHidden(true);
				}
			}
			
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL '";
		CommonFunctions.debugMsg("headerSql.....123..."+headerSql);
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
	private JSONObject getSplitTableModel(List<String[]> headers)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String[] row = headers.get(0);
		//row[0] = "S.No";
		jqGridTableModel.getRowHeaders().add(row);	
		
		jqGridTableModel.setTableButton(true);		 
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		for(int i=0;i<row.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(row[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(row[i].replaceAll(" ", "")+ i);
			jqGridColModel.setEditable(false);
			if(i==0)
			{
				jqGridColModel.setWidth(50);
				jqGridColModel.setAlign("center");
				jqGridColModel.setHidden(true);
			}
			else if(i==1)
				jqGridColModel.setHidden(true);
			else if(i==4 || i==8)
			{
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("center");
			}
			else if(i==5)
				jqGridColModel.setWidth(300);
			else if(i==7)
				jqGridColModel.setWidth(100);
			else if(i==9)
				jqGridColModel.setWidth(50);
			else if(i==10 || i==11 || i==12 )
			{
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("right");
			}
			else if( i==13)
				jqGridColModel.setWidth(200);
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		
		
		CommonFunctions.debugMsg(tableModel   + "..................");
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
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");				 
		 	  }
			
			
			commonFilter.setViewClick('Y');
			
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonFunctions.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	}
	

		
}
