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
import com.akranta.tpm.utils.CommonFunctions;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.OplSummaryService;
import com.akranta.tpm.service.BAL_PmStandardServices;
import com.akranta.tpm.service.impl.OplSummaryServiceImpl;
//import com.akranta.tpm.service.impl.BAL_PMVsBDServiceImpl;
import com.akranta.tpm.service.impl.BAL_PmStandardServiceImpl;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_PmStandardServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	
	BAL_PmStandardServices pmStandardServices;
	
	public BAL_PmStandardServlet() throws Exception
	{
		super();		
		
		//pmStandardServices = new PmStandardServiceImpl();
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
			pmStandardServices = (BAL_PmStandardServiceImpl)UIUtils.getServiceObject(request,"BAL_PmStandardServiceImpl");
			
			HttpSession h = request.getSession(false);

			pmStandardServices.BAL_PmStandardServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );

	
		} catch (ServiceObjectCreationException e) {
			com.akranta.tpm.utils.CommonFunctions.debugMsg(e);
		}
		if( action.equals("filterXmlPmStandardReport_input.pmStandarsRpt"))
		{
			System.out.println("action "+ action); 
			response.setContentType("xml"); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/PMStandard.xml");
		}	

		/*else if(action.equals("PmStandardReport_input.pmStandarsRpt"))
		{
			CommonFunctions.debugMsg("Dispatch URL");
			String factory=request.getParameter("factId");
			String sectionId=request.getParameter("sectionId");
			String cellId=request.getParameter("cellId");
			String machineId=request.getParameter("machId");
			request.setAttribute("machId", machineId);
			request.setAttribute("factId", factory);
			request.setAttribute("cellId", cellId);
			request.setAttribute("sectionId", sectionId);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/PmStandard.jsp");  
			rd.forward(request, response); 
		}
		else if(action.equals("PmStandardReport_getCol.pmStandarsRpt"))
		{	
			HttpSession httpSession = request.getSession(true);
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = new CommonFilter();
			
			commonFilter = populateCommonFilter(request,"PmStandardCommonFilter",true);
			commonFilter.setSectionId(request.getParameter("sectionId"));
			commonFilter.setCellId(request.getParameter("cellId"));
			commonFilter.setFactoryId(request.getParameter("factId"));
			commonFilter.setMachineId(request.getParameter("machId"));
			
			List<String []> pmstandardList  = pmStandardServices.getAllPmStandard(commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("groupByMachine");
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = pmstandardList.get(1);			
			String [] colHeaderCond = pmstandardList.get(0);
			
			//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "86%%");
			jsonObject.put("tableWidth", "106%%");
	      	CommonFunctions.debugMsg("jsonObject ="+jsonObject);
	      	httpSession.setAttribute("PMStdColModel", jsonObject);
	      	out.println(jsonObject);
	      	String tabelModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PMStandardRpt", "PMStandardRpt");
			JSONObject colModel = JSONObject.fromString(tabelModel);
			out.println(colModel);
			httpSession.setAttribute("PMStdColModel", colModel);
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null ) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			httpSession.removeAttribute("PmStandardCommonFilter");
			httpSession.setAttribute("PmStandardCommonFilter", commonFilter);
			
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PMStandardRpt", "PMStandardRpt"));
		}
		else if( action.equals("PmStandardReport_getData.pmStandarsRpt") )
		{
			try
			{
				HttpSession httpSession = request.getSession(true);
				
				CommonFilter commonFilter = populateCommonFilter(request,"PmStandardCommonFilter",false);
				commonFilter.setSectionId(request.getParameter("sectionId"));
				commonFilter.setCellId(request.getParameter("cellId"));
				commonFilter.setFactoryId(request.getParameter("factId"));
				commonFilter.setMachineId(request.getParameter("machId"));
				
				List<String []> pmstandardList  = pmStandardServices.getAllPmStandard(commonFilter);
  			 	PrintWriter out = response.getWriter();
  			 	JSONObject pmstandardData = UIUtils.convertToJqGridTableObject(pmstandardList,request,2,0,commonFilter.getTotalRecordCnt()); 
  			 	out.println(pmstandardData);  			 	
  			 	httpSession.removeAttribute("PmStandardCommonFilter");
  			 	httpSession.setAttribute("PmStandardCommonFilter", commonFilter);

		    }catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}*/	
		
	else if( action.equals("PmStandardReport_getExcel.pmStandarsRpt"))
		{			
			
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"PmStandardCommonFilter",false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
	//	String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PMStandardRpt", "PMStandardRpt");
		//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
		JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("PMStdColModel");
		tblJSONObj.put("title", "PMStandards Report");
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = pmStandardServices.PMStandardReportExportExcel(commonFilter,tblJSONObj,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, "PMStandards", format);
		
		
		
		
		/*HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("PmStandardCommonFilter");
			List<String[]> pmstandardList  = pmStandardServices.getAllPmStandard(commonFilter);
			String fileName= "PmStandardRpt.xls";
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PMStandardRpt", "PMStandardRpt");
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);*/
			//excelUtils.writeToExcel(request, response, fileName, pmstandardList,(short)0,(short)1,(short)0);
						
		}
	else if(action.equals("PmStandardReport_input.pmStandarsRpt"))
	{
		RequestDispatcher rd = request.getRequestDispatcher("/pages/pmStandardRptnew.jsp");  
		rd.forward(request, response); 
	}
	else if(action.equals("PmStandardReport_getCol.pmStandarsRpt")){
		HttpSession httpSession = request.getSession(true);
		PrintWriter out = response.getWriter();
		CommonFilter commonFilter = new CommonFilter();
		
		commonFilter = populateCommonFilter(request,"PmStandardrptTemp",true);
		commonFilter.setSectionId(request.getParameter("sectionId"));
		commonFilter.setCellId(request.getParameter("cellId"));
		commonFilter.setFactoryId(request.getParameter("factId"));
		commonFilter.setMachineId(request.getParameter("machId"));
		commonFilter.setIsGetCol("Y");
		List<String []> pmstandardList  = pmStandardServices.getPmStandardRptTemp(commonFilter);
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();
		
		jqGridTableModel.setRowNumbers(true);
		//jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableButton(true);
		//jqGridTableModel.setGroupBy(true);
		//jqGridTableModel.setGroupByField("groupByMachine");
		gridColModel.setHeaderNum(1);
		
		String [] colHeader = pmstandardList.get(1);			
		String [] colHeaderCond = pmstandardList.get(0);
		
		//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
		List<String[]> headers = new ArrayList<String[]>();
		//headers.add(colHeaderCond);
		headers.add(colHeader);
		
		JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		jsonObject.put("tableHeight", "86%%");
		jsonObject.put("tableWidth", "106%%");
      	CommonFunctions.debugMsg("jsonObject ="+jsonObject);
      	httpSession.setAttribute("PMStdColModel", jsonObject);
      	out.println(jsonObject);
      	/*String tabelModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PMStandardRpt", "PMStandardRpt");
		JSONObject colModel = JSONObject.fromString(tabelModel);
		out.println(colModel);
		httpSession.setAttribute("PMStdColModel", colModel);*/
		/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null ) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  }*/
		httpSession.removeAttribute("PmStandardrptTemp");
		httpSession.setAttribute("PmStandardrptTemp", commonFilter);
	}	
	else if(action.equals("PmStandardReport_getData.pmStandarsRpt")){
		try
		{
			HttpSession httpSession = request.getSession(true);
			
			CommonFilter commonFilter = populateCommonFilter(request,"PmStandardrptTemp",false);
			commonFilter.setSectionId(request.getParameter("sectionId"));
			commonFilter.setCellId(request.getParameter("cellId"));
			commonFilter.setFactoryId(request.getParameter("factId"));
			commonFilter.setMachineId(request.getParameter("machId"));
			commonFilter.setIsGetCol("N");
			List<String []> pmstandardList  = pmStandardServices.getPmStandardRptTemp(commonFilter);
			 	PrintWriter out = response.getWriter();
			 	JSONObject pmstandardData = UIUtils.convertToJqGridTableObject(pmstandardList,request,2,0,commonFilter.getTotalRecordCnt()); 
			 	out.println(pmstandardData);  			 	
			 	httpSession.removeAttribute("PmStandardCommonFilter");
			 	httpSession.setAttribute("PmStandardCommonFilter", commonFilter);

	    }catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	else if(action.equals("PmStandardrptTemplateAssembly_input.pmStandarsRpt"))
	{
		CommonFunctions.debugMsg("Assembly Servlet");
		String tradeId=request.getParameter("tradeId");
		String tradeName=request.getParameter("tradeName");
		String equipmentName=request.getParameter("equipmentname");
		String equipmentNo=request.getParameter("equipmentno");
		String equipmentKeyid=request.getParameter("machId");
		request.setAttribute("tradeId", tradeId);
		request.setAttribute("tradeName", tradeName);
		request.setAttribute("equipmentName", equipmentName);
		request.setAttribute("equipmentNo", equipmentNo);
		request.setAttribute("equipmentKeyid", equipmentKeyid);
		RequestDispatcher rd = request.getRequestDispatcher("/pages/pmStandardAssemblyRpt.jsp");  
		rd.forward(request, response); 
	}
	else if(action.equals("PmStandardrptTemplateAssembly_getCol.pmStandarsRpt"))
	{
		HttpSession httpSession = request.getSession(true);
		PrintWriter out = response.getWriter();
		CommonFilter commonFilter = new CommonFilter();
		
		commonFilter = populateCommonFilter(request,"PmStandardAssemblyrptTemp",true);
		commonFilter.setSectionId(request.getParameter("sectionId"));
		commonFilter.setCellId(request.getParameter("cellId"));
		commonFilter.setFactoryId(request.getParameter("factId"));
		commonFilter.setMachineId(request.getParameter("machId"));
		commonFilter.setTrarId(request.getParameter("tradeId"));
		List<String []> pmstandardList  = pmStandardServices.getPmStandardAssemblyRptTemp(commonFilter);
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();
		
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableButton(true);
		gridColModel.setHeaderNum(1);
		
		String [] colHeader = pmstandardList.get(1);			
		String [] colHeaderCond = pmstandardList.get(0);
		
		List<String[]> headers = new ArrayList<String[]>();
		headers.add(colHeader);
		
		JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		jsonObject.put("tableHeight", "86%%");
		jsonObject.put("tableWidth", "106%%");
      	CommonFunctions.debugMsg("jsonObject ="+jsonObject);
      	httpSession.setAttribute("PMStdColModel", jsonObject);
      	out.println(jsonObject);
      	httpSession.removeAttribute("PmStandardAssemblyCommonFilter");
		httpSession.setAttribute("PmStandardAssemblyCommonFilter", commonFilter);
	}
	else if(action.equals("PmStandardrptTemplateAssembly_getData.pmStandarsRpt"))
	{
		try
		{
			HttpSession httpSession = request.getSession(true);
			
			CommonFilter commonFilter = populateCommonFilter(request,"PmStandardAssemblyrptTemp",false);
			commonFilter.setSectionId(request.getParameter("sectionId"));
			commonFilter.setCellId(request.getParameter("cellId"));
			commonFilter.setFactoryId(request.getParameter("factId"));
			commonFilter.setMachineId(request.getParameter("machId"));
			commonFilter.setTrarId(request.getParameter("tradeId"));
			
			List<String []> pmstandardList  = pmStandardServices.getPmStandardAssemblyRptTemp(commonFilter);
			 	PrintWriter out = response.getWriter();
			 	JSONObject pmstandardData = UIUtils.convertToJqGridTableObject(pmstandardList,request,2,0,commonFilter.getTotalRecordCnt()); 
			 	out.println(pmstandardData);  			 	
			 	httpSession.removeAttribute("PmStandardAssemblyCommonFilter");
			 	httpSession.setAttribute("PmStandardAssemblyCommonFilter", commonFilter);

	    }catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	else if(action.equals("PmStandardrptTemplateAssembly_getExcel.pmStandarsRpt"))
	{
		try
		{
						
				CommonFunctions.debugMsg(" In side get Exel  "+request.getParameter("machId"));
				
				CommonFunctions.debugMsg(" In side get Exel tradeId "+request.getParameter("tradeId"));
				HttpSession httpSession;	
			 httpSession = request.getSession(false);
			//CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false);
			 CommonFilter commonFilter = populateCommonFilter(request,"PmStandardCommonFilter",false);
			 commonFilter.setMachineId(request.getParameter("machId"));
			 commonFilter.setTrarId(request.getParameter("tradeId"));
			 CommonFunctions.debugMsg(" In side get Exel  "+commonFilter.getMachineId());
			 String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = (JSONObject)httpSession.getAttribute("PMStdColModel");
					
			tableModel.put("title", "PM STANDARD Report  ");//+ commonFilter.getTools() +" To "+ commonFilter.getTools());
			String format = ExcelUtils.getFormat(request);		
			Workbook wb = pmStandardServices.PMStandardReportExportToExcel(commonFilter,tableModel,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "PMStandards", format);
						

	    }catch(Exception e)
		{
	    	e.printStackTrace();
			//System.out.println(e.getMessage());
		}
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
			commonFilter = 	FilterValues.getPMRelated(request, commonFilter);
			
			
			  
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		System.out.println( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
}
				
				