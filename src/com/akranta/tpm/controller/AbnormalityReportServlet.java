package com.akranta.tpm.controller;
/*
 * MODIFIED BY:DHANALAKSHMI.R
 * DATE:25.2.13
 */
import java.io.IOException;
import com.akranta.tpm.utils.CommonMessage;
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
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.AbnormalityRptService;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.impl.AbnormalityRptServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
public class AbnormalityReportServlet extends HttpServlet{
/**
 **/
	private static final long serialVersionUID = 1L;
	
	//CommonFilter commonFilter;
	AbnormalityRptService abnormalityRptService;
    DashboardService dashboardService;
	private String type;
	
	public AbnormalityReportServlet() throws Exception
	{
		super();
		
		//commonFilter=new CommonFilter();
		//abnormalityRptService = new AbnormalityRptServiceImpl();
		
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
		
		try {
			abnormalityRptService = (AbnormalityRptServiceImpl)UIUtils.getServiceObject(request,"AbnormalityRptServiceImpl");
			abnormalityRptService.AbnormalityRptServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		//test
		if( action.equals("filterXmlAbnRpt_input.abnRpt") || (action.equals("filterXmlHSE_AbnRpt_input.abnRpt"))
				|| (action.equals("filterXmlAbnSOCAbnRpt_input.abnRpt")) || (action.equals("filterXmlAbnHTAAbnRpt_input.abnRpt"))
				|| (action.equals("filterXmlAbnUNSAbnRpt_input.abnRpt")))
		{
			response.setContentType("xml"); 
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/AbnormalityReport.xml") ;
		}
		else if(action.equals("AbnRpt_input.abnRpt")|| (action.equals("HSE_AbnRpt_input.abnRpt"))
				|| (action.equals("AbnSOCAbnRpt_input.abnRpt")) || (action.equals("AbnHTAAbnRpt_input.abnRpt"))
				|| (action.equals("AbnUNSAbnRpt_input.abnRpt")))
		{			 
			RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnormalityReport.jsp");
		
			if (action.equals("HSE_AbnRpt_input.abnRpt")) 
				request.setAttribute("AbnType", "SHE");
			else if(action.equals("AbnHTAAbnRpt_input.abnRpt"))
				request.setAttribute("AbnType", "HTA");
			else if(action.equals("AbnSOCAbnRpt_input.abnRpt"))
				request.setAttribute("AbnType", "SOC");
			else if(action.equals("AbnUNSAbnRpt_input.abnRpt"))
				request.setAttribute("AbnType", "UNS");
			
			rd.forward(request, response); 
		}
		else if(action.equals("AbnRpt_getCol.abnRpt") || action.equals("HSE_AbnRpt_getCol.abnRpt")
				|| (action.equals("AbnHTAAbnRpt_getCol.abnRpt")) || (action.equals("AbnSOCAbnRpt_getCol.abnRpt"))
				|| (action.equals("AbnUNSAbnRpt_getCol.abnRpt")))
		{	
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityReportCommonFilter",true);
			//commonFilter.setMonwise("N");
			String type=request.getParameter("type");
			String afeem=request.getParameter("AFEEM");
			CommonMessage.debugMsg(" afeem :: afeem :: getCol :: "+afeem);
			commonFilter.setAbnImp(afeem);
			CommonMessage.debugMsg("Check type in servlet"+type);
			commonFilter.setIsGetCol("Y");
			List<String[]> abnormality  = abnormalityRptService.getAllAbnormality( commonFilter,type);
			
			JSONObject abnData;
			if(action.equals("AbnRpt_getCol.abnRpt"))
			{
				CommonMessage.debugMsg("HSE_AbnRpt_input.abnRpt");
				abnData= UIUtils.convertToJqGridTableObject(abnormality,request,2,0,commonFilter.getTotalRecordCnt()+2);
			}	
			else{
				CommonMessage.debugMsg("commonFilter.getTotalRecordCnt()"+commonFilter.getTotalRecordCnt());
				abnData = UIUtils.convertToJqGridTableObject(abnormality,request,2,0,commonFilter.getTotalRecordCnt()+2); 
			}
			//commonFilter.setToRow(commonFilter.getToRow()+2);
			
			httpSession.setAttribute("abnData", abnData);
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			
//			gridColModel.setFormatter("tickAction");
//			gridColModel.setFormattorFromCol("16");
			gridColModel.setFormattorToCol(String.valueOf(abnormality.get(2).length-1));
			
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = abnormality.get(2);			
			String [] colHeaderCond = abnormality.get(0);
//			CommonMessage.debugMsg("colHeader :"+colHeader[1]);
//			CommonMessage.debugMsg("colHeaderCond :"+colHeaderCond[1]);
			
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			CommonMessage.debugMsg("before uiutils  ");
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			CommonMessage.debugMsg("after  uiutils  ");
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "75%%");
			/*
			jsonObject.put("isGroupBy", "false");
			jsonObject.put("groupByField", false);
			jsonObject.put("rowNumbers", true);
			jsonObject.put("groupSummary", false);
			jsonObject.put("tableButton", true);
			*/
			//jsonObject.put("tableHeight", "75%");
			jsonObject.put("data", abnData);
			
			CommonMessage.debugMsg(jsonObject);
			httpSession.removeAttribute("abnReportColModel");
			httpSession.setAttribute("abnReportColModel", jsonObject);
			out.println(jsonObject);			
		
		}
		
		else if( action.equals("AbnRpt_getData.abnRpt")  || action.equals("HSE_AbnRpt_getData.abnRpt")
				|| (action.equals("AbnHTAAbnRpt_getData.abnRpt")) || (action.equals("AbnSOCAbnRpt_getData.abnRpt"))
				|| (action.equals("AbnUNSAbnRpt_getData.abnRpt")))
		{
			PrintWriter out = response.getWriter();
			try
			{
				
				 UIUtils.displayRequestParamsValue(request);
				 request.getParameter("page");
				 httpSession = request.getSession();
				 String loginFlid = (String) httpSession.getAttribute("loginFlid");
				 CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityReportCommonFilter",false);
				 String type=request.getParameter("type");
				 String afeem=request.getParameter("AFEEM");
				
				 
				 CommonMessage.debugMsg("chk type in servlet::"+type);
				 //if(!UIUtils.isValidKeyId(commonFilter.getFlid()))
				// commonFilter.setChkActwise(chkval);
				 CommonMessage.debugMsg(" afeem :: afeem :: getData :: "+afeem);
				 commonFilter.setAbnImp(afeem);
					
				 if(!UIUtils.isValidKeyId(commonFilter.getFlid()))
					 commonFilter.setFlid(loginFlid);
				 JSONObject jsonObject = new JSONObject();	
				 commonFilter.setIsGetCol("N");
        		 List<String[]> abnormality  = abnormalityRptService.getAllAbnormality( commonFilter,type);
        		 CommonMessage.debugMsg("commonFilter.getTotalRecordCnt()"+commonFilter.getTotalRecordCnt());
        		 jsonObject = UIUtils.convertToJqGridTableObject(abnormality,request,3,0,commonFilter.getTotalRecordCnt()+4); 
        		 out.println(jsonObject);
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("AbnormalityReportCommonFilter");
	  			 httpSession.setAttribute("AbnormalityReportCommonFilter", commonFilter);
		    }
			catch(Exception e)
			{
				//CommonMessage.debugMsg(e.getMessage());
			}
		}else if( action.equals("AbnRpt_getExcel.abnRpt")  || action.equals("HSE_AbnRpt_getExcel.abnRpt")
				|| (action.equals("AbnHTAAbnRpt_getExcel.abnRpt")) || (action.equals("AbnSOCAbnRpt_getExcel.abnRpt"))
				|| (action.equals("AbnUNSAbnRpt_getExcel.abnRpt"))){
			
			
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityReportCommonFilter",false);
			//String type=request.getParameter("type");
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("abnReportColModel");
			tblJSONObj.put("title", "Abnormality Report");
			String format = ExcelUtils.getFormat(request);
			 String type=request.getParameter("type");
			Workbook wb = abnormalityRptService.abnormalityReportExportExcel(commonFilter,tblJSONObj,format,type);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "AbnormalityReport", format);
			
		}
			//AGING REPORT
			else if( action.equals("filterXmlAbnAgeRpt_input.abnRpt")){
				response.setContentType("xml"); 
				CommonMessage.debugMsg("action "+ action); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/AbnormalityAging.xml") ;
			}
			else if(action.equals("AbnAgeRpt_input.abnRpt") || action.equals("SOCAgeRpt_input.abnRpt")
					|| action.equals("UNSAgeRpt_input.abnRpt") ||  action.equals("HTAAgeRpt_input.abnRpt")
					||  action.equals("HSEAgeRpt_input.abnRpt"))
			{
				CommonMessage.debugMsg("Brfore jsp");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/AbnormalityAgeingReport.jsp");
				
				if (action.equals("HSEAgeRpt_input.abnRpt")) 
					request.setAttribute("AbnType", "SHE");
				else if(action.equals("HTAAgeRpt_input.abnRpt"))
					request.setAttribute("AbnType", "HTA");
				else if(action.equals("SOCAgeRpt_input.abnRpt"))
					request.setAttribute("AbnType", "SOC");
				else if(action.equals("UNSAgeRpt_input.abnRpt"))
					request.setAttribute("AbnType", "UNS");
				
				rd.forward(request, response); 
			}
			
			else if(action.equals("AbnAgeRpt_getCol.abnRpt") || action.equals("SOCAgeRpt_getCol.abnRpt")
					|| action.equals("UNSAgeRpt_getCol.abnRpt") ||  action.equals("HTAAgeRpt_getCol.abnRpt")
					||  action.equals("HSEAgeRpt_getCol.abnRpt"))
			{	
				CommonMessage.debugMsg("AbnAgeRpt_getCol.abnRpt");
				PrintWriter out = response.getWriter();
				 CommonFilter commonFilter = populateAgeCommonFilter(request,"AbnormalityReportCommonFilter",true);
				String ColModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityAgeRptColModel", "colModelAbnAgeRpt");
				List< String[]> ppMatMonList  = abnormalityRptService.getAgeAbnormality(commonFilter);
				//NW MADE CHANGES HERE
				//JSONObject jsonObject = JSONObject.fromString(ColModel);
				//jsonObject.set("tableHeight", "280");
				//jsonObject.set("tableWidth", "1120");
				
				httpSession.removeAttribute("abnReportColModel");
				//httpSession.setAttribute("abnReportColModel", ColModel);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				
				gridColModel.setHeaderNum(1);
				gridColModel.setFormatter("tickAction");
				gridColModel.setFormattorFromCol("9");
				gridColModel.setFormattorToCol("13");
				String [] colHeader = ppMatMonList.get(2);			
				String [] colHeaderCond = ppMatMonList.get(1);
				
				//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "85%%");
				jsonObject.put("tableWidth", "106%%");
				//httpSession.setAttribute("abnReportColModel", jsonObject);
				out.println(jsonObject);
			   // out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityAgeRptColModel", "colModelAbnAgeRpt"));
											
			}
			else if( action.equals("AbnAgeRpt_getData.abnRpt") || action.equals("SOCAgeRpt_getData.abnRpt")
					|| action.equals("UNSAgeRpt_getData.abnRpt") ||  action.equals("HTAAgeRpt_getData.abnRpt")
					 ||  action.equals("HSEAgeRpt_getData.abnRpt"))
			{
				PrintWriter out = response.getWriter();
				try
				{	
					 UIUtils.displayRequestParamsValue(request);
					 httpSession = request.getSession();
					 String flid= request.getParameter("flid");
						if(!UIUtils.isValidKeyId(flid)){
							flid=(String) httpSession.getAttribute("loginFlid");
						}
					 CommonFilter commonFilter = populateAgeCommonFilter(request,"AbnormalityReportCommonFilter",false);	
					commonFilter.setFlid(flid);
					 JSONObject jsonObject = new JSONObject();				 
					
					CommonMessage.debugMsg(" getData");				
					List< String[]> ppMatMonList  = abnormalityRptService.getAgeAbnormality(commonFilter);	
					 CommonMessage.debugMsg("ppMatMonListList" + ppMatMonList.size() );
					 CommonMessage.debugMsg("commonFilter.getTotalRecordCnt()" + commonFilter.getTotalRecordCnt());
					 if(ppMatMonList.size() > 2 )				 
					 jsonObject = UIUtils.convertToJqGridTableObject(ppMatMonList,request,3,0, commonFilter.getTotalRecordCnt()+4);	        	 
					 out.println(jsonObject);	
					// commonFilter.setViewClick('Y');					 	
					 httpSession.removeAttribute("AbnormalityReportCommonFilter");				 
					 httpSession.setAttribute("AbnormalityReportCommonFilter", commonFilter);				 
		     }			
			catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
				JSONObject successData = new JSONObject();
				
				CommonMessage.debugMsg("noData");
   	    	 	String sucessmsg = "noData";
   	    	 	successData.put("msg",sucessmsg );
   	    		out.println(successData);	
   	    		return;
			}
	    }
		else if( action.equals("AbnAgeRpt_getExcel.abnRpt") || action.equals("SOCAgeRpt_getExcel.abnRpt")
				|| action.equals("UNSAgeRpt_getExcel.abnRpt") ||  action.equals("HTAAgeRpt_getExcel.abnRpt")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateAgeCommonFilter(request,"AbnormalityReportCommonFilter",false);
			//commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = (String)httpSession.getAttribute("abnReportColModel");
			
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.AbnormalityAgeRptColModel", "colModelAbnAgeRpt");
			//JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("tableModel");
			//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
			tblJSONObj.put("title", "Abnormality Ageing Report");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = abnormalityRptService.abnormalityAgeReportExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			CommonMessage.debugMsg("inside excel");
			ExcelUtils.writeToResponse(response, wb, "AbnormalityAgeingReport", format);
			
		}
		//added By Dhanalakshmi.R for Abn Tag Trend
		else if(action.endsWith("filterXmlAbnTagTrendRpt_input.abnRpt"))
		{
			UIUtils.forwardRequest(request, response, "/tiles/xml/AbnTagTrend.xml") ;
		}
		else if(action.equals("AbnTagTrendRpt_input.abnRpt"))
		{
			UIUtils.forwardRequest(request, response, "/pages/Reports/AbnTagTrendReport.jsp") ;
		}
		else if(action.equals("AbnTagTrendRpt_getCol.abnRpt"))
		{
			  PrintWriter out = response.getWriter();
			  String firstClick =request.getParameter("firstClick");
			  CommonFilter  commonFilter= new CommonFilter(); 
			  FilterValues.getAbnRelatedFilters(request,commonFilter);
		      if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
		      {			
		    	  CommonMessage.debugMsg("first click.....");
				commonFilter = (CommonFilter) httpSession.getAttribute("AbnTagTrendFilter");
				
		      }						
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 			
			 FilterValues.getCommonFilters(request,commonFilter);				
			
			 httpSession.removeAttribute("AbnTagTrendFilter");
			 httpSession.setAttribute("AbnTagTrendFilter",commonFilter);
			 response.setContentType("text/html");
			 
			 commonFilter.setIsGetCol("Y");
			 List<String[]> abnTagTrendList=abnormalityRptService.getAbnTagTrend(commonFilter);
			 		 
			 JSONObject abnTagTrendListData = UIUtils.convertToJqGridTableObject(abnTagTrendList,request,0,0,commonFilter.getTotalRecordCnt()+2);
			 CommonMessage.debugMsg("abnTagTrendListData:"+abnTagTrendListData);
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(2);	
			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setRowNumbers(true);
			 jqGridTableModel.setGroupSummary(true);		
			 String [] colHeaderHead = abnTagTrendList.get(0);			 
			 String [] colHeader1 = abnTagTrendList.get(1);		
			 String [] colHeader = abnTagTrendList.get(2);					 
			 List<String[]> headers = new ArrayList<String[]>();
			 headers.add(colHeader1);
			 headers.add(colHeader);
			 
			 JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			 CommonMessage.debugMsg("jsonObject:"+jsonObject);
			 jsonObject.set("tableWidth", "110%%");
			 jsonObject.set("tableHeight", "68%%");
			 jsonObject.put("data", abnTagTrendListData);
			 httpSession.removeAttribute("abnTagTrendColModel");
			 httpSession.setAttribute("abnTagTrendColModel",jsonObject);
			 out.println(jsonObject);	
		}
		else if(action.equals("AbnTagTrendRpt_getData.abnRpt"))
		{
			try 
			{
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"AbnTagTrendFilter",false);
				//CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("AbnTagTrendFilter");
				response.setContentType("text/html");
				UIUtils.displayRequestParamsValue(request);
				String flid=request.getParameter("flid");
				if(!UIUtils.isValidKeyId(flid)){
					flid=(String) httpSession.getAttribute("loginFlid");
				}
				String drillflag=request.getParameter("drillFlag");
				if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if(UIUtils.isValidKeyId(drillflag)){
					CommonMessage.debugMsg("  (drillflag.trim()).charAt(0)   "+(drillflag.trim()).charAt(0));
					commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
				}
				httpSession.getAttribute("AbnTagTrendFilter");
				httpSession.setAttribute("AbnTagTrendFilter",commonFilter);
				CommonMessage.debugMsg("process...."+FilterCondSql.getComboSelectionId(commonFilter.getProcess()));
				
				commonFilter.setIsGetCol("N");
				
				List<String[]> abnTagTrendList = abnormalityRptService.getAbnTagTrend(commonFilter);
				JSONObject listToJsonObject = new JSONObject();
				if (abnTagTrendList != null && abnTagTrendList.size() > 3)
					listToJsonObject = UIUtils.convertToJqGridTableObject(abnTagTrendList,request,3,0);
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}
			catch (Exception e)
			{
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		else if(action.equals("AbnTagTrendRpt_getExcel.abnRpt"))
		{
			CommonFilter commonFilter = populateCommonFilter(request,"AbnTagTrendFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);				
			JSONObject tableModel = (JSONObject) httpSession.getAttribute("abnTagTrendColModel");
			tableModel.put("title", "Abnormality Tag Trend Report - "+commonFilter.getDrillCaption()+" Wide ");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = abnormalityRptService.getAbnTagTrendExportExcel(commonFilter,tableModel,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "AbnTagTrendReport", format);
		}
		else if( action.equals("AbnTagTrendRpt_getChart.abnRpt"))
		{
			CommonMessage.debugMsg("Chart ");
			processChart(request,response);
			
		}
		else if(action.equals("AbnormalityDetails_view.abnRpt"))
		{	
			UIUtils.forwardRequest(request, response,"/pages/AbnormalityDetails.jsp");
		}
		else if( action.equals("AbnormalityDetails_getCol.abnRpt") ){
			String keyId = request.getParameter("KeyId");
			String columnId = request.getParameter("column");
			String fromDate = request.getParameter("dtFromDate");
			String toDate = request.getParameter("dtToDate");
			String mchId = request.getParameter("mchId");
			String chkTradewise = request.getParameter("chkTradewise");
			String colIndexName = request.getParameter("colIndexName");
			String header2= request.getParameter("header2");
			CommonMessage.debugMsg(colIndexName + " colIndexName");
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"abnTlAbnormalityDetails",true);
			commonFilter.setFromDate(fromDate);
			commonFilter.setToDate(toDate);
			commonFilter.setHeader2(header2);
			commonFilter.setChktradewise(chkTradewise);
			List<String[]> abnfrmGrid = abnormalityRptService.getAllAbnormalityDetails(commonFilter,keyId,columnId,colIndexName);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true)	;
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
	
			String [] colHeader = abnfrmGrid.get(2);			
			String [] colHeaderCond = abnfrmGrid.get(1);
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			//jsonObject.put("multiSelect", true);
			jsonObject.put("tableWidth", "110%%");
			jsonObject.put("tableHeight", "80%%");
			httpSession.removeAttribute("abnTlAbnormalityDetails");
			out.println(jsonObject);
	} 
		else if( action.equals("AbnormalityDetails_getData.abnRpt") )
		{
			
			
			try
			{
				String keyId = request.getParameter("KeyId");
				String columnId = request.getParameter("column");
				String fromDate = request.getParameter("dtFromDate");
				String toDate = request.getParameter("dtToDate");
				String mchId = request.getParameter("mchId");
				String chkTradewise = request.getParameter("chkTradewise");
				String colIndexName = request.getParameter("colIndexName");
				String header2= request.getParameter("header2");
				CommonMessage.debugMsg(chkTradewise+ " get value"); 
				UIUtils.displayRequestParamsValue(request);
				CommonFilter commonFilter = populateCommonFilter(request,"abnTlAbnormalityDetails",true);
				
				if(mchId != null && mchId != "")
					commonFilter.setMachineId(mchId);
				
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				commonFilter.setHeader2(header2);
				commonFilter.setFromDate(fromDate);
				commonFilter.setToDate(toDate);
				commonFilter.setChktradewise(chkTradewise);
				CommonMessage.debugMsg("chkTradewise " + chkTradewise);
				List<String[]> equipmentQueryList  = abnormalityRptService.getAllAbnormalityDetails(commonFilter,keyId,columnId,colIndexName);
				
				
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg(commonFilter.getTotalRecordCnt() + " total cnt");
  			 	JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(equipmentQueryList,request,3,0,commonFilter.getTotalRecordCnt()+3); 
  			 	out.println(equipmentQueryData);
  			 	
  			 	commonFilter.setViewClick('N');
  			 	
  			 	httpSession.removeAttribute("abnTlAbnormalityDetails");
  			 	httpSession.setAttribute("abnTlAbnormalityDetails", commonFilter);

		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		   
		}
		
		else if( action.equals("AbnormalityDetails_getExcel.abnRpt")){
			String keyId = request.getParameter("KeyId");
			String columnId = request.getParameter("column");
			String fromDate = request.getParameter("dtFromDate");
			String toDate = request.getParameter("dtToDate");
			String mchId = request.getParameter("mchId");
			String chkTradewise = request.getParameter("chkTradewise");
			String colIndexName = request.getParameter("colIndexName");;
			httpSession = request.getSession(false);
			CommonFilter commonFilter1 = populateCommonFilter(request,"abnTlAbnormalityDetails",false);	
			commonFilter1.setFromDate(fromDate);
			commonFilter1.setToDate(toDate);
			commonFilter1.setColumnId(columnId);
			commonFilter1.setColIndexName(colIndexName);
			commonFilter1.setKey(keyId);
			commonFilter1.setChktradewise(chkTradewise);
			JSONObject colmodel = UIUtils.getXlColModel(request,response);
			colmodel.put("title","Abnormality Details");
           String format = ExcelUtils.getFormat(request);
			
			Workbook wb = abnormalityRptService.AbnDetailsExportExcel(colmodel,format,commonFilter1);
			ExcelUtils.writeToResponse(response, wb, "AbnormalityDetails", format);
			
		
		}
		
else if(action.equals("abnSummaryReport_input.abnRpt")){
			
			CommonMessage.debugMsg("abnSummaryReport_input.kaz");
			UIUtils.forwardRequest(request, response,"/pages/AbnormalitySummaryReport.jsp");
			
		}else if(action.equals("abnSummaryReport_getCol.abnRpt")){			
			PrintWriter out = response.getWriter();	
			httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"AbnSummaryCommonFilter",true);	
			commonFilter.setIsGetCol("Y");
			List<String[]> kaizenSuggestionGridData  = abnormalityRptService.getAbnSummaryGridData(commonFilter);			
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);
			gridColModel.setHeaderNum(1);
			String[] colHeader = kaizenSuggestionGridData.get(1);
			String[] colHeaderCond = kaizenSuggestionGridData.get(0);
			List<String[]> headers = new ArrayList<String[]>();			
			headers.add(colHeader);			
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "90%%");
			colModel.set("tableWidth", "110%%");
			httpSession.removeAttribute("AbnSummaryColModel");
			httpSession.setAttribute("AbnSummaryColModel", colModel);			
			httpSession.removeAttribute("AbnSummaryCommonFilter");
			httpSession.setAttribute("AbnSummaryCommonFilter", commonFilter);			
			out.println(colModel);			
		}else if(action.equals("abnSummaryReport_getData.abnRpt")){
			PrintWriter out = response.getWriter();	
			httpSession=request.getSession(false);
			CommonFilter commonFilter=populateCommonFilter(request, "AbnSummaryCommonFilter", false);
			commonFilter.setIsGetCol("N");
			List<String[]> KaizenGridData = abnormalityRptService.getAbnSummaryGridData(commonFilter);
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData,request, 2, 0,commonFilter.getTotalRecordCnt() );
			out.print(dataJson);
			httpSession.removeAttribute("AbnSummaryCommonFilter");
			httpSession.setAttribute("AbnSummaryCommonFilter", commonFilter);			
		}else if(action.equals("abnSummaryReport_getExcel.abnRpt")){
			httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"AbnSummaryCommonFilter",false);			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("AbnSummaryColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "Abnormality Summary Report  ");			
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = abnormalityRptService.getAbnSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);			
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "AbnormalitySummaryReport", format);		
		}
		
		
	}
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =null;
		String forDashboard = request.getParameter("dashboard");
		String rowid = "1";
		String rowids= request.getParameter("rowid");
		if(UIUtils.isValidKeyId(rowids))
			rowid = rowids;
		CommonMessage.debugMsg("forDashboard....."+forDashboard);
		if( ! "true".equals(forDashboard))
		{
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute("AbnTagTrendFilter");
		}
		else
		{
			commonFilter= populateCommonFilter(request,"AbnTagTrendFilter",true);
			CommonMessage.debugMsg("to check dash board true ");
			/*commonFilter = new  CommonFilter();
			CommonMessage.debugMsg("to check dash board true ");
			FilterValues.getCommonFilters(request,commonFilter);
			FilterValues.getAbnRelatedFilters(request, commonFilter);	*/		
		}
		CommonMessage.debugMsg("After Com Filter ");
		if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) )
		{
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	 } 
		List<String[]> abnTagTrendList  = abnormalityRptService.getAbnTagTrend(commonFilter);;
		/*String [] curRow =(String[] )abnTagTrendList.get(abnTagTrendList.size()-1);   
		String [] cumRow = new String[ curRow.length ];
    	int cnt = 0;
		int total =0;
		int remTotal = 0;
		cumRow[0]=cumRow[1]="Cumulative";
    	for( int k = 2; k<curRow.length; k++)
		{	
    		cnt = Integer.parseInt(curRow[k]);	
    		if(k%2 == 0)
    		{
    			total += cnt;    			
    			cumRow[k] = Integer.toString(total);
    			CommonMessage.debugMsg(k +" cur Row Red: "+cumRow[k]);
    		}
    		else
    		{
    			remTotal += cnt;	
	    		cumRow[k] = Integer.toString(remTotal);
	    		CommonMessage.debugMsg(k +" cur Row White: "+cumRow[k]);
    		}			
		}
    	abnTagTrendList.add(cumRow);*/
		JSONObject chartObj = null;



String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
		String chartType = request.getParameter("chType");
		CommonMessage.debugMsg("chartType ="+chartType);
		//if( chartType == null)
		chartObj = processLineChart(lcnname,abnTagTrendList,commonFilter,rowid,chartType);
		UIUtils.dashBoardSetChartObject(request,chartObj);
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();	
	}
	private JSONObject processLineChart(String titlename,List<String[]> abnTagTrendList,CommonFilter commonFilter,String rowid,String chartType)
	{
		CommonMessage.debugMsg("abnTagTrendList size:"+abnTagTrendList.size());
		
		int rowno = Integer.parseInt(rowid);
		if( abnTagTrendList == null || abnTagTrendList.size() <= 2 )
			return null;
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		CommonMessage.debugMsg("graph header..........................."+commonFilter.getDrillCaption());
		
		String abnType = commonFilter.getAbnormalityType();
		String date;
		if(abnType==null)
			abnType="";
		if(commonFilter.getMonwise().equals("Y"))
			date = commonFilter.getFromMonth()+" To "+commonFilter.getToMonth();
		else
			date = commonFilter.getFromDate()+" - "+commonFilter.getToDate(); 
		
		String [] header =  abnTagTrendList.get(2);
		String [] month =  abnTagTrendList.get(1);
		String [] data =  abnTagTrendList.get(rowno+2);
		String [] headerData =  abnTagTrendList.get(3);
		String drillLevel = FilterValues.getDrillHeader(headerData[0]);
		CommonMessage.debugMsg(drillLevel+" eleTypeArray[1]  "+headerData[0]);
		String title = titlename+"-Abnormality Tag Trend Report-" + drillLevel + " Wide From "+date;
		String prevMonth = null;
		String subTitle = "";
		ChartSeries timeSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> instanceData = new ArrayList<Double>();
		
		for( int i = 2;i < header.length-1;i++ )
		{			
			
			if(header[i].contains("Red") )
				timeData.add(Double.parseDouble(data[i]));			
			else if(header[i].contains("White") )
				instanceData.add(Double.parseDouble(data[i]));
			if( prevMonth == null || ! month[i].equals(prevMonth) )
				xAxisCategory.add(month[i]);
			prevMonth = month[i];
		}
		if( instanceData.size() > 0)
		{
			CommonMessage.debugMsg("graph instance...........................");
			intstanceSeries.setData(instanceData);
			intstanceSeries.setType(chartType);
			intstanceSeries.setName("White");
			chartSeriesList.add(intstanceSeries);
		}
		if(timeData.size() > 0 )
		{
			CommonMessage.debugMsg("graph time...........................");
			timeSeries.setData(timeData);
			timeSeries.setType(chartType);
			timeSeries.setName("Red");
			chartSeriesList.add(timeSeries);
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("No.Of Tags");
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

	private JSONObject getTableModel(List<String[]> headers)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		jqGridTableModel.setRowNumbers(true);
		String [] colHeader2 = headers.get(0);
		String [] colHeader = headers.get(1);
		String[] emptyrow = new String[colHeader.length]; 
		//emptyrow [1] ="";
		CommonMessage.debugMsg("colHeader.length:"+colHeader.length);
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setGroupSummary(true);			
		JqGridColModel jqGridColModel =getColModel("levelName",325,"left",false,false,false);
		jqGridTableModel.getColModel().add(getColModel("levelId",50,"left",true,false,false));		
		jqGridTableModel.getColModel().add( jqGridColModel);
		String colIndex ="";
		String headerSql = "'SELECT ";
		colHeader = headers.get(0);
		for(int i =2;i < colHeader.length; i++)
		{
			emptyrow[i-1] ="";
			colIndex = colHeader[i-1].replaceAll(" ", "").replace("-", "")+(i-1) ;
			JqGridColModel jqGridColModel1;
			if(i==colHeader.length-1)
				jqGridColModel1=getColModel( colIndex,50,"center", true,true,false);
			else
				jqGridColModel1=getColModel( colIndex,65,"center", false,true,false);
			jqGridTableModel.getColModel().add(jqGridColModel1);
			CommonMessage.debugMsg("123......."+jqGridColModel1.getIndex());
			//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel1);
		}
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		
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
		jqGridColModel.setKey(key);
		return jqGridColModel;
	}
	@SuppressWarnings("unused")
	private JSONObject getTableModel1(List<String[]> headers, CommonFilter commonFilter) {
		
		CommonMessage.debugMsg("Inside getTableModel1 ");
		
		JqGridTableModel jqGridTableModel = new JqGridTableModel();		 
		String[] colHeader = headers.get(0);		
		/*String[] emptyrow = new String[colHeader.length];	

		emptyrow [0] ="";
		emptyrow [1] ="";
				
		for( int i = 2; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}	*/	
		
				
		jqGridTableModel.getRowHeaders().add(colHeader);		
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		jqGridTableModel.setTableHeight(300);
		CommonMessage.debugMsg("COL++++++length"+colHeader.length);
		for (int i = 0; i < colHeader.length; i++) 
		{
			CommonMessage.debugMsg("INSIDE FOR LOOP" + colHeader[i]);
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") );
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") );
			jqGridColModel.setAlign("left");			
			jqGridColModel.setEditable(false);			
			
			if(i==0 || i==1 )
			{				
				 jqGridColModel.setHidden(true);
				 jqGridColModel.setKey(true);
			}
			else if(i>1)
			{
				jqGridColModel.setEditable(false);
				if(i==2)
				{
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
				}
				if(i==3)
				{
				jqGridColModel.setWidth(200);
				jqGridColModel.setAlign("left");
				}
				
				else
				{
					jqGridColModel.setWidth(75);
				}			
			}			
			 if(i>7)
			{
				CommonMessage.debugMsg("TICKACTION" +colHeader[i]);
				jqGridColModel.setFormatter("tickAction");
				jqGridColModel.setAlign("center");
			}
			 
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight","80%%");
		CommonMessage.debugMsg("end of getTableModel1 ");
		return tableModel;
	}
	
	
	private CommonFilter populateAgeCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
			 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	}
	

		
}
