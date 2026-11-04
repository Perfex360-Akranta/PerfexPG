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
import com.akranta.tpm.service.BAL_PMComplainceRptService;
import com.akranta.tpm.service.impl.BAL_PMComplianceServiceImpl;
import com.akranta.tpm.service.impl.BAL_PmStandardServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;




public class BAL_PMComplainceRtpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	BAL_PMComplainceRptService pMComplainceRptService;
	private static final String commonFilterIden = "impVsCompcommonFilter";

	public BAL_PMComplainceRtpServlet() {
	/*	try {
			pMComplainceRptService = new PMComplianceServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/	
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!UIUtils.checkUserSession(request, response))
			return;
		String action = UIUtils.getActionPart(request);
		try {
			HttpSession h = request.getSession(true);
			pMComplainceRptService = (BAL_PMComplianceServiceImpl)UIUtils.getServiceObject(request,"BAL_PMComplianceServiceImpl");
			pMComplainceRptService.BAL_PMComplianceServiceImpJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );

	
		} catch (ServiceObjectCreationException e) {
			System.out.println(e);
		}
		
		if (action.equals("filterXmlPMCompliance_input.pmCompliance")) {
			response.setContentType("xml");		
			UIUtils.forwardRequest(request, response,"/tiles/xml/PMCompliance.xml");
		} 
		else if (action.equals("PMCompliance_input.pmCompliance")) {		
			UIUtils.forwardRequest(request, response,"/pages/Reports/PMCompliance.jsp");
		} 
		else if (action.equals("PMCompliance_getCol.pmCompliance")) {
			CommonFilter commonFilter = null;
			HttpSession httpSession = request.getSession(true);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			
			if (commonFilter == null)
				commonFilter = new CommonFilter();
			
			/*populateCommonFilter(request,"PmComplaince",true);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getPMRelated(request, commonFilter);
			
			
			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				commonFilter = (CommonFilter) httpSession.getAttribute("PmComplaince");
			}*/
			
			
			/*httpSession.removeAttribute("PmComplaince");
			httpSession.setAttribute("PmComplaince", commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getPMRelated(request, commonFilter);*/
			
			commonFilter = populateCommonFilter(request,"PmComplaince",( UIUtils.isValidKeyId(firstClick) || (firstClick!= null && ! firstClick.equals("Y") )),true);
			System.out.println("ChkSkipLine     :"+commonFilter.getChkSkipLine());
			System.out.println("Monthly......................................     :"+commonFilter.getMonthly());
			if(!UIUtils.isValidKeyId(commonFilter.getMonthly())){
				System.out.println("commonFilter.getWeekly()  :" +commonFilter.getWeekly());
				commonFilter.setMonthly("N");
			}
			   //commonFilter.setMonthly("Y");
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
			httpSession.removeAttribute("PmComplaince");
			httpSession.setAttribute("PmComplaince",commonFilter);
			String weekly = null;
			commonFilter.setIsGetCol("Y");
			List<String[]> impVscomList = pMComplainceRptService.getAllPMCompliance(commonFilter);
			JSONObject impVscomListData = UIUtils.convertToJqGridTableObject(impVscomList,request,0,0,commonFilter.getTotalRecordCnt()+2);
			System.out.println("impVscomListData:"+impVscomListData);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			 
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableHeight(250);			 
			String [] colHeaderHead = impVscomList.get(0);	
			String [] colHeader = impVscomList.get(1);
			
			String [] colHeader1 = impVscomList.get(2);	
			List<String[]> headers = new ArrayList<String[]>();
			String caption=FilterValues.getHeader( commonFilter.getDrillCaption());			 
			if(!"Y" .equals(commonFilter.getMonthly()))
				   weekly = "Y";			
			System.out.println("commonFilter.getDrillLevel() getCol__------------------"+commonFilter.getDrillLevel());
			colHeader1[1]=caption;
			colHeader[1]=caption;			
			//System.out.println("test..."+colHeader.toString());	
			if(UIUtils.isValidKeyId(weekly) && weekly.equals("Y")){
				System.out.println("weekly.."+weekly);
				gridColModel.setHeaderNum(1);	
				headers.add(colHeader);
				//headers.add(colHeader1);
			}
			else{
				gridColModel.setHeaderNum(1);	
				headers.add(colHeader);	
				//headers.add(colHeader1);

			}
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			 
			jsonObject.set("tableHeight", "70%%");
			jsonObject.set("tableWidth", "108%%");
			jsonObject.put("data", impVscomListData);
			httpSession.removeAttribute("PmComplainceColModel");
			httpSession.setAttribute("PmComplainceColModel",jsonObject);
			System.out.println("Table Model:" + jsonObject);
			out.println(jsonObject);
		} 
		
		else if (action.equals("PMCompliance_getData.pmCompliance")) {
			try {
				HttpSession httpSession = request.getSession(true);
				//CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false);
				CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false,false);
				response.setContentType("text/html");
				System.out.println("ChkSkipLine Data    :"+commonFilter.getChkSkipLine());
				System.out.println("commonFilter.setMonthly------------------"+commonFilter.getComp());
				System.out.println("commonFilter.setMonthly------------------"+commonFilter.getMonthly());
					//commonFilter.setMonthly("Y");
				//httpSession.getAttribute("commonFilterIden");
				commonFilter.setIsGetCol("N");
				List<String[]> impVscomList = pMComplainceRptService.getAllPMCompliance(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				System.out.println("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 3){
				if(!"Y" .equals(commonFilter.getMonthly()))
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request,3,0);
				else
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request,2,0);
				}
				/*httpSession.removeAttribute("PmComplaince");
				httpSession.setAttribute("PmComplaince",commonFilter);*/
				
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject.toString());
			}

			catch (Exception e) {
				System.out.println("error " + e.getMessage());
			}
		}
		
		else if( action.equals("PMCompliance_getExcel.pmCompliance"))
		{			
			
			
		HttpSession httpSession = request.getSession(true);
		//CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false);
		CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false,false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("PmComplainceColModel");
		
		
		tableModel.put("title", "PmComplaince Report - "+commonFilter.getDrillCaption()+" Level");
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = pMComplainceRptService.PMComplainceReportExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, "PmComplaince", format);
		}
		
	//	_____________________
		
		else if (action.equals("PMComplianceNew_input.pmCompliance")) {		
			UIUtils.forwardRequest(request, response,"/pages/Reports/NewPMCompliance.jsp");
		} 
		else if (action.equals("PMComplianceNew_getCol.pmCompliance")) {
			CommonFilter commonFilter = null;
			HttpSession httpSession = request.getSession(true);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			
			if (commonFilter == null)
				commonFilter = new CommonFilter();
		
			
			commonFilter = populateCommonFilter(request,"NewPmComplaince",( UIUtils.isValidKeyId(firstClick) || (firstClick!= null && ! firstClick.equals("Y") )),true);
			System.out.println("ChkSkipLine     :"+commonFilter.getChkSkipLine());
			System.out.println("Monthly......................................     :"+commonFilter.getMonthly());
			if(!UIUtils.isValidKeyId(commonFilter.getMonthly())){
				System.out.println("commonFilter.getWeekly()  :" +commonFilter.getWeekly());
				commonFilter.setMonthly("N");
			}
			   //commonFilter.setMonthly("Y");
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
			httpSession.removeAttribute("NewPmComplaince");
			httpSession.setAttribute("NewPmComplaince",commonFilter);
			String weekly = null;
			List<String[]> impVscomList = pMComplainceRptService.getAllPMComplianceNew(commonFilter);
			JSONObject impVscomListData = UIUtils.convertToJqGridTableObject(impVscomList,request,0,0,commonFilter.getTotalRecordCnt()+2);
			System.out.println("impVscomListData:"+impVscomListData);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			 
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableHeight(250);			 
			String [] colHeaderHead = impVscomList.get(0);	
			String [] colHeader = impVscomList.get(1);
			
			String [] colHeader1 = impVscomList.get(2);	
			List<String[]> headers = new ArrayList<String[]>();
			String caption=FilterValues.getHeader( commonFilter.getDrillCaption());			 
			if(!"Y" .equals(commonFilter.getMonthly()))
				   weekly = "Y";			
			System.out.println("commonFilter.getDrillLevel() getCol__------------------"+commonFilter.getDrillLevel());
			colHeader1[1]=caption;
			colHeader[1]=caption;			
			//System.out.println("test..."+colHeader.toString());	
			if(UIUtils.isValidKeyId(weekly) && weekly.equals("Y")){
				System.out.println("weekly.."+weekly);
				gridColModel.setHeaderNum(1);	
				headers.add(colHeader);
				//headers.add(colHeader1);
			}
			else{
				gridColModel.setHeaderNum(1);	
				headers.add(colHeader);	
				//headers.add(colHeader1);

			}
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			 
			jsonObject.set("tableHeight", "70%%");
			jsonObject.set("tableWidth", "108%%");
			jsonObject.put("data", impVscomListData);
			httpSession.removeAttribute("NewPmComplainceColModel");
			httpSession.setAttribute("NewPmComplainceColModel",jsonObject);
			System.out.println("Table Model:" + jsonObject);
			out.println(jsonObject);
		} 
		
		else if (action.equals("PMComplianceNew_getData.pmCompliance")) {
			try {
				HttpSession httpSession = request.getSession(true);
				//CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false);
				CommonFilter commonFilter = populateCommonFilter(request,"NewPmComplaince",false,false);
				response.setContentType("text/html");
				System.out.println("ChkSkipLine Data    :"+commonFilter.getChkSkipLine());
				System.out.println("commonFilter.setMonthly------------------"+commonFilter.getComp());
				System.out.println("commonFilter.setMonthly------------------"+commonFilter.getMonthly());
					//commonFilter.setMonthly("Y");
				//httpSession.getAttribute("commonFilterIden");
				List<String[]> impVscomList = pMComplainceRptService.getAllPMComplianceNew(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				System.out.println("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 3){
				if(!"Y" .equals(commonFilter.getMonthly()))
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request,3,0);
				else
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request,2,0);
				}
				
				
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject.toString());
			}

			catch (Exception e) {
				System.out.println("error " + e.getMessage());
			}
		}
		
		else if( action.equals("PMComplianceNew_getExcel.pmCompliance"))
		{			
			
			try {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"NewPmComplaince",false,false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("NewPmComplainceColModel");
		
		System.out.println(" In side the get Excel Report 1");
		tableModel.put("title", "PmComplaince Report - "+commonFilter.getDrillCaption()+" Level");
		//String format = ExcelUtils.getFormat(request);
		System.out.println(" In side the get Excel Report 2");

		String format="xlsx";
		Workbook wb = pMComplainceRptService.newPMComplainceReportExportExcel(commonFilter,tableModel,format);
		System.out.println(" In side the get Excel Report 3");

		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, "PmComplaince", format);
		System.out.println(" In side the get Excel Report 4");

			}catch(Exception e) {
				e.printStackTrace();
			}
			}
		
		//______________________
		
		else if(action.equals("MaintActivityReport_input.pmCompliance"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/employeewiseactivityrpt.jsp"); 
			rd.forward(request, response);
		}
		else if(action.equals("MaintActivityReport_getCol.pmCompliance"))
		{
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(true);
			CommonFilter commonFilter = populateCommonFilter(request,"EmployeeActivityWiseCommonFilter",false,false);	
			
			
			String mchWise = commonFilter.getMachineWise();
			String EquiWise = commonFilter.getEquipmentWise();
			
			String colModel = null;//= "MaintainActivityWiseRpt";
			String empwise = request.getParameter("empwise");
			System.out.println("empwise IN SIDE THE sERVLET " +empwise);
			if(! "Y".equals(empwise)){
				if(mchWise != null && mchWise.equals("1")){
					colModel = "MaintainActivityWiseRpt";
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", colModel));
				}else{ 
					colModel = "MaintainWorkOrderWiseRpt";
				}
				
			}
			commonFilter.setEmpWise(empwise);
			List<String []> employeeActivity  = pMComplainceRptService.getAllEmployeeActivity(commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			
			gridColModel.setHeaderNum(1);
			String [] colHeader = employeeActivity.get(1);			
			String [] colHeaderCond = employeeActivity.get(0);
			
			System.out.println("   TABLEMODEL     "+colHeader.length);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "80%%");
			jsonObject.put("tableWidth", "106%%");
	      	System.out.println("jsonObject ="+jsonObject);
	      	out.println(jsonObject);
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", colModel));
		
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null ) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			httpSession.removeAttribute("EmployeeActivityWiseCommonFilter");
			httpSession.setAttribute("EmployeeActivityWiseCommonFilter", commonFilter);
		}
		else if(action.equals("MaintActivityReport_getData.pmCompliance"))
		{

			try
			{	
				PrintWriter out = response.getWriter();
				 
				HttpSession httpSession = request.getSession(true);
			
				CommonFilter commonFilter = populateCommonFilter(request,"EmployeeActivityWiseCommonFilter",false,false);		
					
				List<String[]> employeeActivity  = pMComplainceRptService.getAllEmployeeActivity(commonFilter);
				JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(employeeActivity,request,2,0,commonFilter.getTotalRecordCnt());
				
  				out.println(maintActivityData);
  				
  				httpSession.removeAttribute("EmployeeActivityWiseCommonFilter");
  			 	httpSession.setAttribute("EmployeeActivityWiseCommonFilter", commonFilter);
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else if( action.equals("MaintActivityReport_getExcel.pmCompliance"))
		{						
			CommonFilter commonFilter = populateCommonFilter(request,"EmployeeActivityWiseCommonFilter" +"",false,false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = UIUtils.getXlColModel(request,response); //UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", "MaintainActivityWiseRpt");
			//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			System.out.println(" tableModel " + tableModel);
			String title = "Employee Wise Activity Report";
			//if("Y".equals(commonFilter.getEmpWise() ))
				//title = "Employee Wise Activity Report";*/
			
				tableModel.put("title",title );
			String format = ExcelUtils.getFormat(request);
			
			//tableModel.getJSONArray("colModel").getJSONObject(2).set("hidden", false);
			
			Workbook wb = pMComplainceRptService.EmployeeWiseActivityRptExportExcel(commonFilter,tableModel,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, title.replaceAll(" ", ""), format);
			
		}
	}

	private JSONObject getTableModel(List<String[]> headers, String weekly,String caption) {
		System.out.println("Enter get col model"+headers.size());
		CommonFilter commonFilter = new CommonFilter();
		System.out.println("caption...."+commonFilter.getDrillCaption());
		String[] colHeader = headers.get(0);	
		String[] colHeaders = headers.get(1);	
		//String[] colHeader1 = headers.get(2);
		List<JqGridColModel>jqGridColModelList =new ArrayList<JqGridColModel>();
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] emptyrow = new String[colHeader.length]; 
		colHeaders[2]=caption;
		colHeader[2]=caption;
		for(int i=0;i<=colHeader.length-1;i++)
		{
			emptyrow[i]="";
			System.out.println(colHeader[i]);
			System.out.println(colHeaders[i]);
			//System.out.println(colHeader1[i]);
		}
		System.out.println("test..."+colHeader.toString());	
		if(UIUtils.isValidKeyId(weekly) && weekly.equals("Y")){
			System.out.println("weekly.."+weekly);
			jqGridTableModel.getRowHeaders().add(emptyrow);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.getRowHeaders().add(colHeaders);
		}
		else{
			jqGridTableModel.getRowHeaders().add(colHeader);	
		}
		//jqGridTableModel.getRowHeaders().add(emptyrow);
				
		//jqGridTableModel.getRowHeaders().add(colHeaders);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(250);
	
		for (int i = 0; i <= colHeader.length-1; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			
			if(UIUtils.isValidKeyId(colHeader[i]))
			{
				if(i>=0 && i<=1)
				{
					
					System.out.println( i +" !!!! "+colHeaders[i]);
					jqGridColModelList.add(getColModel(colHeaders[i],200,"left"));
					jqGridColModel.setHidden(true);
				}
				if(UIUtils.isValidKeyId(weekly)){
					if(i==2 ){
						System.out.println("colHeader["+i+"] "+colHeader[i]+"~~~"+colHeaders[i]+"~~~"+emptyrow[i]);
						
						jqGridColModelList.add(getColModel(emptyrow[i],200,"center"));
						jqGridColModelList.add(getColModel(colHeader[i],200,"center"));
						jqGridColModelList.add(getColModel(colHeaders[i],200,"center"));
						//jqGridColModel.setWidth(120);
						jqGridColModel.setIndex(emptyrow[i].replaceAll(" ", "") + i);
					    jqGridColModel.setName(emptyrow[i].replaceAll(" ", "") + i);
					    
					}
					if(i>4 && i < colHeader.length-1){
						jqGridColModelList.add(getColModel(colHeaders[i]+colHeader[i],20,"center"));
					   
					}
					if(i>2 && i < colHeader.length-1){
						System.out.println( i +" !!!! "+colHeaders[i]);
						jqGridColModel.setWidth(40);
					}
					System.out.println("inside weekly   :"+weekly);
					if(i ==  colHeader.length-1){
						jqGridColModel.setHidden(true);
					}
				}
				/*if(i>2 && i < colHeader.length-2)
					jqGridColModelList.add(getColModel(colHeaders[i]+colHeader[i],40,"center"));*/
				//jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
				//jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			}
			
			if (i == 0 || i == 1 ) {
				jqGridColModel.setIndex("rnum"+i );
				jqGridColModel.setName("rnum"+i );
				jqGridColModel.setKey(true);
				jqGridColModel.setHidden(true);
				if(i==1){
					jqGridColModel.setIndex("keyId" );
					jqGridColModel.setName("keyId" );
					
				}
			}
			
			//jqGridColModel.setWidth(400);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i == 0 || i == 1 ) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			} else if (i > 2) {
				// jqGridColModel.setHidden(false);
				//jqGridColModel.setKey(true);
				jqGridColModel.setAlign("right");
				//jqGridColModel.setWidth(100);
			}

		/*	else if (i == 16) {
				System.out.println("Length of col:" + colHeader.length);
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			}*/

			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		System.out.println("colmodel:" + tableModel);
		return tableModel;
	}
	private JqGridColModel getColModel (String colIndex, int width,String allign)
	{
		System.out.println("getColModel  :-[ "+colIndex+" ]");
		System.out.println("getColModel-Width   :"+width);
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setWidth( width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		//jqGridColModel.setFormatter("actionformatter_"+colIndex);			
		return jqGridColModel;
	}
	
		private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew,boolean drilFlag){
			
			
			HttpSession httpSession = request.getSession(false);
			
			System.out.println("createNew...."+drilFlag);
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
			if( commonFilter != null && ! createNew ){
				FilterValues.setPaginationParams(request,commonFilter);
				//if(drilFlag )
					FilterValues.getCommonFilters(request, commonFilter);
					FilterValues.getPMRelated(request, commonFilter);
			}	
			else{
				
				commonFilter =  new CommonFilter();
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
				commonFilter = 	FilterValues.getPMRelated(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
	
	}
