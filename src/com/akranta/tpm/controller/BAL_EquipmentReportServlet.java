package com.akranta.tpm.controller;

import java.io.IOException;
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

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.BAL_EquipmentControlPanelService;
import com.akranta.tpm.service.BAL_EquipmentReportService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_EquipmentControlPanelServiceImpl;
import com.akranta.tpm.service.impl.EquipmentFormServiceImpl;
import com.akranta.tpm.service.impl.BAL_EquipmentReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class BAL_EquipmentReportServlet extends HttpServlet {
	
	/**
	 * created by Karthick.T
	 */
	private static final long serialVersionUID = 1L;
	
	BAL_EquipmentReportService equipmentReportService;
	
	
	public BAL_EquipmentReportServlet()
	{  
		super();
		/*try{
			equipmentReportService= new EquipmentReportServiceImpl();
			
		}
		catch(Exception e){
			
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
		
		String action = UIUtils.getActionPart(request);
		HttpSession h = request.getSession(false);
		
		try {
			equipmentReportService= (BAL_EquipmentReportServiceImpl)UIUtils.getServiceObject(request,"BAL_EquipmentReportServiceImpl");
			equipmentReportService.BAL_EquipmentReportServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
			
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}

		String dispatchUrl =null;
		 if(action.equals("EqpControlPanel_input.equipmentRpt")) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/EquipmentControlPanel.jsp"); 
			rd.forward(request, response); 
		}
		 else if( action.equals("filterXmlEqpQuery_input.equipmentRpt")){
			 response.setContentType("xml"); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/EquipmentQuery.xml") ;
		 }
		 else if(action.equals("EqpQuery_input.equipmentRpt"))
		{
			String filter = request.getParameter("filter");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/EquipmentQueryReport.jsp"); 
			request.setAttribute("filter", filter);				
			rd.forward(request, response); 
		}
		else if(action.equals("EqpQuery_getCol.equipmentRpt"))
		{
			//out.println(getEquipmentColumnModel());
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"EquipmetntQueryCommonFilter",true);
			commonFilter.setIsGetCol("Y");
			List<String[]> equipmentQueryList  = equipmentReportService.getAllEquipmentQuery(commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			
			gridColModel.setHeaderNum(1);
			
			String [] colHeader1 = equipmentQueryList.get(1);
			String [] colHeaderCond = equipmentQueryList.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader1);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			CommonFunctions.debugMsg("colModel   "+jsonObject);
		  
		//  JSONObject jsonObject = getTableModel(yyQtyList);
		  jsonObject.put("tableHeight", "75%%");
		  jsonObject.put("tableWidth", "108%%");
		  out.println(jsonObject);
			/*System.out.println(" after populate col ");			
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery"));*/
		}
		
		else if( action.equals("EqpQuery_getData.equipmentRpt") )
		{
			try
			{HttpSession httpSession = request.getSession(false);
				UIUtils.displayRequestParamsValue(request);
				
				
				CommonFilter commonFilter = populateCommonFilter(request,"EquipmetntQueryCommonFilter",false);
				
				commonFilter.setIsGetCol("N");
				List<String[]> equipmentQueryList  = equipmentReportService.getAllEquipmentQuery(commonFilter);
  			 	
				CommonFunctions.debugMsg("equipmentQueryList " + equipmentQueryList.size());
				PrintWriter out = response.getWriter();
  			 	JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(equipmentQueryList,request,2,0,commonFilter.getTotalRecordCnt()); 
  			 	out.println(equipmentQueryData);
  			 	
  			 	commonFilter.setViewClick('N');
  			 	
  			 	httpSession.removeAttribute("EquipmetntQueryCommonFilter");
  			 	httpSession.setAttribute("EquipmetntQueryCommonFilter", commonFilter);

		    }catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else if( action.equals("EqpQuery_getExcel.equipmentRpt")){
			
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("EquipmetntQueryCommonFilter");
			CommonFunctions.debugMsg("ExcelPage Loading");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			
			tblJSONObj.put("title", "Equipment Query");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = equipmentReportService.equipmentQueryExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "EquipmentQuery", format);
			
		}
		else if(action.equals("EqpControlPanel_getCol.equipmentRpt"))
		{
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpControlPanel"));
		}
		else if( action.equals("EqpControlPanel_getData.equipmentRpt") )
		{
			try
			{	PrintWriter out = response.getWriter();
				CommonFilter  commonFilter;
				commonFilter =  new CommonFilter();
				 	List<String[]> equipmentControlPanelList  = equipmentReportService.getAllEquipmentControlPanelService(commonFilter);

				 	JSONObject EquipmentControlPanelData = UIUtils.convertToJqGridTableObject(equipmentControlPanelList,request,0,0); 
					
				 	out.println(EquipmentControlPanelData);

		    }catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
			
		else if( action.equals("filterXmlEqmpthistryReport_input.equipmentRpt")){
				response.setContentType("xml"); 
				System.out.println("action "+ action); 
				PrintWriter out = response.getWriter();
				out.print("<fromDate>01-Jan-2012</fromDate>");
				UIUtils.forwardRequest(request, response, "/tiles/xml/EquipmentHistory.xml") ;
			 }
		
			else if(action.equals("EqmpthistryReport_input.equipmentRpt")) 
			{
				//dispatchUrl = "/pages/eqmpthistryrpt.jsp";
				request.setAttribute("clickLable", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","clickLable"));
				System.out.println("Dispatch url");
				String filter = request.getParameter("filter");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Eqmpthistryrpt.jsp"); 
				request.setAttribute("filter", filter);				
				rd.forward(request, response); 
			}
			else if(action.equals("EqmpthistryReport_getCol.equipmentRpt"))
			{
				String groupByStr = "0";
				groupByStr = request.getParameter("gropByEqp");
				
				if(groupByStr==null)
					groupByStr = "0";
				
				CommonFilter commonFilter = populateCommonFilter(request,"EquipmentHistoryCommonFilter",true);		
				commonFilter.setIsGetCol("Y");
				PrintWriter out = response.getWriter();
				if(groupByStr.equals("1")){
					
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EquipmentHistoryGrpBy"));
				}
				else{
					List<String[]> equipmentHistoryList  = equipmentReportService.getAllEqmpthistry(commonFilter);
					System.out.println("Group by false");
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
					gridColModel.setHeaderNum(1);
					
					String [] colHeader1 = equipmentHistoryList.get(1);
					String [] colHeaderCond = equipmentHistoryList.get(0);
					
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader1);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					CommonFunctions.debugMsg("colModel   "+jsonObject);
				  
				//  JSONObject jsonObject = getTableModel(yyQtyList);
				  jsonObject.put("tableHeight", "75%%");
				  jsonObject.put("tableWidth", "108%%");
				  out.println(jsonObject);
					//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EquipmentHistory"));
				}
			}
			else if( action.equals("EqmpthistryReport_getData.equipmentRpt") )
			{
				try
				{
								
					HttpSession httpSession = request.getSession();
					CommonFilter commonFilter = populateCommonFilter(request,"EquipmentHistoryCommonFilter",false);
					PrintWriter out = response.getWriter();
					commonFilter.setIsGetCol("N");
	  			 	List<String[]> equipmentHistoryList  = equipmentReportService.getAllEqmpthistry(commonFilter);	  			 	
	  			 	JSONObject equipmentHistryData = UIUtils.convertToJqGridTableObject(equipmentHistoryList,request,2,0,commonFilter.getTotalRecordCnt()); 
	  			 	out.println(equipmentHistryData);	  			 	
	  			 	commonFilter.setViewClick('N');	  	
	  			 	
	  			 	httpSession.removeAttribute("EquipmentHistoryCommonFilter");
	  			 	httpSession.setAttribute("EquipmentHistoryCommonFilter", commonFilter);

			    }catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			else if( action.equals("EqmpthistryReport_getExcel.equipmentRpt")){
				
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"EquipmentHistoryCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EquipmentHistory");
				JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				
				tblJSONObj.put("title", "Equipment Report");
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = equipmentReportService.equipmentHistoryExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "EquipmentReport", format);
				
			}
		 
			else if( action.equals("filterXmlMouldhistryReport_input.equipmentRpt")){				
				UIUtils.forwardRequest(request, response, "/tiles/xml/MouldOutputProd.xml") ;
			 }
			else if(action.equals("MouldhistryReport_input.equipmentRpt")) 
			{				
				request.setAttribute("clickLable", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","clickLable"));
				String filter = request.getParameter("filter");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Eqmpthistryrpt.jsp"); 
				request.setAttribute("filter", filter);				
				rd.forward(request, response); 
			}
			else if(action.equals("MouldhistryReport_getCol.equipmentRpt"))
			{
				HttpSession httpSession = request.getSession(false);
				String groupByStr = "0";
				groupByStr = request.getParameter("gropByEqp");
				
				if(groupByStr==null)
					groupByStr = "0";
				
				populateCommonFilter(request,"EquipmentHistoryCommonFilter",true);		
				
				JSONObject tblJSONObj;
				String colModel = "";
				PrintWriter out = response.getWriter();
				if(groupByStr.equals("1")){
					
					 colModel = (UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "MouldHistoryGrpBy"));
					tblJSONObj = JSONObject.fromString(colModel);
				}
				else{
					System.out.println("Group by false");
					 colModel = (UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "MouldHistory"));
					tblJSONObj = JSONObject.fromString(colModel);
				}
				httpSession.removeAttribute("ColModel");
				httpSession.setAttribute("ColModel", tblJSONObj);
				out.println(colModel);
			}
			else if( action.equals("MouldhistryReport_getData.equipmentRpt") )
			{
				try
				{
								
					HttpSession httpSession = request.getSession();
					CommonFilter commonFilter = populateCommonFilter(request,"EquipmentHistoryCommonFilter",false);
					PrintWriter out = response.getWriter();
					
	  			 	List<String[]> equipmentHistoryList  = equipmentReportService.getAllMouldHistory(commonFilter);	  			 	
	  			 	JSONObject equipmentHistryData = UIUtils.convertToJqGridTableObject(equipmentHistoryList,request,0,1,commonFilter.getTotalRecordCnt()); 
	  			 	out.println(equipmentHistryData);	  			 	
	  			 	commonFilter.setViewClick('N');	  			 	
	  			 	httpSession.removeAttribute("EquipmentHistoryCommonFilter");
	  			 	httpSession.setAttribute("EquipmentHistoryCommonFilter", commonFilter);

			    }catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			else if( action.equals("MouldhistryReport_getExcel.equipmentRpt")){
				
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"EquipmentHistoryCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				
				JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("ColModel");
				
				tblJSONObj.put("title", "Mould History Report");
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = equipmentReportService.getAllMouldHistoryExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "MouldHistoryReport", format);
				
			}
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
			commonFilter = 	FilterValues.getEquipmentRelated(request, commonFilter);
		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		commonFilter.setViewClick('Y');
		System.out.println( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
	
	
}
				
				