package com.akranta.tpm.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.Knowwhybean;
import com.akranta.tpm.bean.PmTaskListBean;
import com.akranta.tpm.bean.VisualConBean;
import com.akranta.tpm.bean.Vocchecklistbean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTargetgroupmst;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.PlmTlPmtasklistmst;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.model.QtmTlKnowwhymst;
import com.akranta.tpm.model.VocTlChecklistdtl;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.impl.KnowWhyServiceImpl;
import com.akranta.tpm.service.KnowWhyService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.KnowWhyServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class ItcKnowwhyServlet
 */

public class KnowwhyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	List<String[]> knowwhyGrid = null;
	DashboardService dashboardService;
	KnowWhyService knowWhyService;
	private String mode;
	private String keyidVer;
     private CommonFilterService commonFilterService;
	public KnowwhyServlet() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		knowWhyService = (KnowWhyServiceImpl)UIUtils.getServiceObject(request, "KnowWhyServiceImpl");
		commonFilterService =(CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
		dashboardService=(DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
		
		knowWhyService.KnowWhyServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg(" action " + action);
		//CommonFilter commonFilter = new CommonFilter();
		// String dispatchUrl = null;
		
		if (action.equals("filterXmlKnwReport_input.KnowWhy") || action.equals("filterXmlKnowWhy_input.KnowWhy") || action.equals("filterXmlKnwView_input.KnowWhy")) {
			UIUtils.forwardRequest(request, response,
					"/tiles/xml/Knowwhy.xml");
		}
		else if (action.equals("KnowWhy_input.KnowWhy")||action.equals("KnwView_input.KnowWhy") || action.equals("KnwReport_input.KnowWhy")) {
			String mode=request.getParameter("mode");
			CommonMessage.debugMsg("The Clicked Mode"+mode);
			request.setAttribute("mode",mode);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/KnowWhyGrid.jsp");
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);

		}
		else if (action.equals("KnowWhy_getCol.KnowWhy")||action.equals("KnwView_getCol.KnowWhy") || action.equals("KnwReport_getCol.KnowWhy")) {
			CommonMessage.debugMsg("wherther the data is enter or not");
			PrintWriter out = response.getWriter();
			httpSession.removeAttribute("knowwhyCommonFilter");
			String mode =request.getParameter("mode");
			CommonMessage.debugMsg(" Inside Servlet Mode :: "+mode);
			CommonFilter commonFilter = populateCommonFilter(request,"knowwhyCommonFilter",true);
			
		  if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 //commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				// commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			commonFilter.setMainGroup(mode);
			commonFilter.setIsGetCol("Y");
			List<String[]> knowwhyGrid = knowWhyService.getKnow(commonFilter);
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true)	;
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
		
			String [] colHeader = knowwhyGrid.get(1);			
			String [] colHeaderCond = knowwhyGrid.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			//jsonObject.put("multiSelect", true);
			jsonObject.put("tableWidth", "110%%");
			jsonObject.put("tableHeight", "80%%");
			//httpSession.removeAttribute("KnowwhyColModel");
			//httpSession.setAttribute("KnowwhyColModel", jsonObject);
			out.println(jsonObject);
		} 
		
		   //JSONObject colModel = 

		 
		
		else if (action.equals("KnowWhy_getData.KnowWhy")||action.equals("KnwView_getData.KnowWhy") || action.equals("KnwReport_getData.KnowWhy")) {
			CommonMessage.debugMsg("get data method");		
			try {
				UIUtils.displayRequestParamsValue(request);	
				httpSession.removeAttribute("knowwhyCommonFilter");
				CommonFilter commonFilter = populateCommonFilter(request,"knowwhyCommonFilter",false);

				 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
					/* commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
					 commonFilter.setToDate(CommonFunctions.getDate());*/
				 }	 
				  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  } 
				  
				String mode =request.getParameter("mode");
				commonFilter.setMainGroup(mode);
				commonFilter.setIsGetCol("N");
				List<String[]> knowwhyGrid = knowWhyService.getKnow(commonFilter);
				
				
				CommonMessage.debugMsg("Data is enter or not" + knowwhyGrid.get(0));
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(knowwhyGrid, request, 2, 0,commonFilter.getTotalRecordCnt()+1);
				out.println(machinegrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}
		 else if(action.equals("functionalLoc.KnowWhy"))
			{
				
		
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				//functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setSection("cmbsusmSectionid");
				functLocFieldNameBean.setCell("cmbsusmCellid");
				functLocFieldNameBean.setMachine("cmbsusmMachineid");
				functLocFieldNameBean.setSectMandatory(true);
				functLocFieldNameBean.setCellMandatory(false);
				functLocFieldNameBean.setMachMandatory(false);
				
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
				
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			}
		
		else if (action.equals("KnowWhygrid_input.KnowWhy")) {
			String keyId = request.getParameter("keyId");
			CommonMessage.debugMsg(keyId + "keyid"); 
			String stuGrid = request.getParameter("grid");
			String clearForm = request.getParameter("clearfrom");
			CommonMessage.debugMsg("Clear Form...." + clearForm);
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			if ("true".equals(stuGrid)) {

			}
			String mode= request.getParameter("mode");
			CommonMessage.debugMsg("modew"+mode);
			//CommonMessage.debugMsg("modew"+mode.length());
			if(mode!=null){
			if(mode.length()==8){
				mode = "View";
			}
			else{
				mode = "approval";
			}
		}
			
			//if(UIUtils.isValidKeyId(mode))
				//mode = "approval";
			request.setAttribute("mode", mode);
			QtmTlKnowwhymst qtmTlKnowwhymst = new QtmTlKnowwhymst();	
			 if(UIUtils.isValidKeyId(keyId)){
				 keyidVer=keyId;
				 CommonMessage.debugMsg("keyidVer:::::::::"+keyidVer);
				 String fileDir=UIUtils.TPM_TEMPIMG_DIR;
				 String imagepath = UIUtils.getImagePath(request);
				 
				 qtmTlKnowwhymst =knowWhyService.getknwwhy(keyId,fileDir,imagepath); 
				 String date= qtmTlKnowwhymst.getKnwmPrepareddate();
				 String versionDate = qtmTlKnowwhymst.getKnwmVersiondate();
				 qtmTlKnowwhymst.setKnwmVersiondate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(versionDate));
				    //date= date.substring(0,11);
				    qtmTlKnowwhymst.setKnwmPrepareddate(CommonFunctions.pg_getDateFromPGTimeStamp(date));	
			 }
			 
			 else{
				 qtmTlKnowwhymst.setKnwmDevelopedby(user.getUsrm_ccno());
				 qtmTlKnowwhymst.setKnwmApprovedby(user.getUsrm_ccno());
				}
			 CommonMessage.debugMsg(qtmTlKnowwhymst.getKnwmPhenomena() + "phenomena");
			 
			 if(UIUtils.isValidKeyId(qtmTlKnowwhymst.getKnwmPhenomena())){
				 qtmTlKnowwhymst.setCheckPhenomena("Y");
			 }
			 else{
				 qtmTlKnowwhymst.setCheckPhenomena("N");
			 }
			 request.setAttribute("qtmTlKnowwhymst", qtmTlKnowwhymst);
			  request.setAttribute("form",clearForm);
			  
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Knowwhy.jsp");
			rd.forward(request, response);

		}
		else if (action.equals("KnowWhygridpop_getCol.KnowWhy")) {
			CommonMessage.debugMsg("wherther the data is enter or not");
			PrintWriter out = response.getWriter();
			String keyid = request.getParameter("keyid");
			String detailgrid = request.getParameter("detailgrid");
			CommonMessage.debugMsg("height::::"+keyid);
	        CommonMessage.debugMsg("height::::"+detailgrid);
	        CommonFilter commonFilter = populateCommonFilter(request,"knowwhygridCommonFilter",true);
	        commonFilter.setKey(keyid);
			List<String[]> MachineGrid = knowWhyService.getITCKnow(commonFilter);
			for (String[] arr : MachineGrid) {
			    CommonMessage.debugMsg(Arrays.toString(arr));
			}
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			//jqGridTableModel.setEnableFilter(true)	;
			//jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
		
			String [] colHeader = MachineGrid.get(1);			
			String [] colHeaderCond = MachineGrid.get(0);
			
			//String [] colHeader = MachineGrid.get(1);			
			//String [] colHeaderCond = MachineGrid.get(2);
			
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			//jsonObject.put("multiSelect", true);
			jsonObject.put("tableWidth", "95%%");//
			jsonObject.put("tableHeight", "35%%");
			httpSession.removeAttribute("KnowwhyColModel");
			httpSession.setAttribute("KnowwhyColModel", jsonObject);
			out.println(jsonObject);
		} 
		else if(action.equals("KnowwhyStatus.KnowWhy")){
			String keyid=request.getParameter("keyid");
			CommonMessage.debugMsg("The keyid Status::::"+keyid);
			PrintWriter out = response.getWriter();
			int count=knowWhyService.getStatusCount(keyid);
			JSONObject returnData = new JSONObject();//
			returnData.put("status", returnData);
			out.println();
		}
		else if (action.equals("KnowWhygridpop_getData.KnowWhy")) {
		
			try {
				String keyid = request.getParameter("keyid");
				CommonFilter commonFilter = populateCommonFilter(request,"knowwhygridCommonFilter",false);
				commonFilter.setKey(keyid);
				List<String[]> MachineGrid = knowWhyService.getITCKnow(commonFilter);
				CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 2, 0);
				out.println(machinegrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}                                                                  
		else if (action.equals("KnowWhy_getExcel.KnowWhy")||action.equals("KnwView_getExcel.KnowWhy") || action.equals("KnwReport_getExcel.KnowWhy")) {
        {
        	httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"knowwhyCommonFilter",false);
			CommonMessage.debugMsg("gridFilter "+commonFilter.getGridFilter());
			JSONObject colmodel = UIUtils.getXlColModel( request, response);
			colmodel.put("title","Know why Report");	
            String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
            String format = ExcelUtils.getFormat(request);
			Workbook wb = knowWhyService.getknowExcel(colmodel,format,commonFilter);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "KnowWhyReports", format);	
			}
			
		}
		else if(action.equals("KnowWhyCount_input.KnowWhy")) 
		{ 
			request.setAttribute("knwwhyCumMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/KnowwhyCummulative.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("KnowWhyCount_getCol.KnowWhy"))
		{
			String firstClick =request.getParameter("firstClick");
			CommonFilter commonFilter = populateCommonFilter(request,"knowwhyCommonFilter",true);
			
			httpSession.removeAttribute("QPointCommonFilter");
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
			 {
				
				  commonFilter =(CommonFilter) httpSession.getAttribute("knowwhyCommonFilter");
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
			
			

			httpSession.removeAttribute("knowwhyCommonFilter");
			httpSession.setAttribute("knowwhyCommonFilter",commonFilter);
			 response.setContentType("text/html");
			 commonFilter.setRowTotal('N');
			 
	    	 List< String[]> knwwhyCummulativeList  = knowWhyService.getKnwwhycnt(commonFilter);
	    	 CommonMessage.debugMsg("Result in Get col Knwo wmy cumulative");
			 for(String[] arr:knwwhyCummulativeList) 
			 {
				 CommonMessage.debugMsg(Arrays.toString(arr));
			 }
			
	      	 JSONObject jsonObject = null;
	      	 if(knwwhyCummulativeList!=null && knwwhyCummulativeList.size() > 0){
	      		 jsonObject = getTableModel(knwwhyCummulativeList,FilterValues.getHeader( commonFilter.getDrillCaption()));
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
		else if( action.equals("KnowWhyCount_getData.KnowWhy") )
		{
			try
			{   
				 PrintWriter out = response.getWriter();
				 CommonFilter commonFilter = populateCommonFilter(request,"knowwhyCommonFilter",false);
				 //commonFilter =(CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
				 //commonFilter =populateCommonFilter(request,oplCummulativeRptIden,false);
				 commonFilter =(CommonFilter) httpSession.getAttribute("knowwhyCommonFilter");
				// String flid=request.getParameter("flid");
				 commonFilter= FilterValues.getCommonFilters(request,commonFilter);
				String drillFlag=request.getParameter("drillFlag");
				 //commonFilter.setFlid(flid);
				if( drillFlag != null)
				 commonFilter.setDrillFlag(drillFlag.charAt(0));
				// CommonMessage.debugMsg("commonFilter"+commonFilter.getMonwise());
				 List< String[]> oplCummulativeList  = knowWhyService.getKnwwhycnt(commonFilter);
				 CommonMessage.debugMsg("Result in Get data Knwo wmy cumulative");
				 for(String[] arr:oplCummulativeList) 
				 {
					 CommonMessage.debugMsg(Arrays.toString(arr));
				 }
				// CommonMessage.debugMsg("oplCummulativeList.size()"+oplCummulativeList.size());
				 if(oplCummulativeList.size()>4){
					// CommonMessage.debugMsg("commonFilter.getTotalRecordCnt()"+commonFilter.getTotalRecordCnt());
					 JSONObject oplCummulativeData = convertToJqGridTableObjectOPL( oplCummulativeList,request,2,0,0,commonFilter.getTotalRecordCnt()+1);
				 	 out.println(oplCummulativeData);
				 }

		    }catch(Exception e)
			{
		    	e.printStackTrace();
				//CommonMessage.debugMsg("error = " +e.getMessage());
			}
		}
		else if(action.equals("KnowWhyCount_getExcel.KnowWhy")){
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"knowwhyCommonFilter",false);
			CommonMessage.debugMsg("gridFilter "+commonFilter.getGridFilter());
			JSONObject colmodel = UIUtils.getXlColModel( request, response);
			colmodel.put("title","Know why Count Report");	
            String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
            String format = ExcelUtils.getFormat(request);
			Workbook wb = knowWhyService.KnowWhyCountExportExcel(commonFilter,colmodel,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "KnowWhyCount", format);
			
		}

		/**----- For GENERATING GRAPH------- **/
		else if( action.equals("chart.KnowWhy"))
		{
			processChart(request,response);
		}
		else if(action.equals("KnwApproval_input.KnowWhy")){
			String mode="approval";
			request.setAttribute("mode", mode);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/KnowWhyGrid.jsp");
			rd.forward(request, response);
			
			
			CommonMessage.debugMsg(" response " + response);
		}
		else if(action.equals("KnowWhyapproval_save.KnowWhy")){
			String appkeyid=request.getParameter("refId");
			String workfloappkeyid=knowWhyService.getapprovalkeyid(appkeyid);
			CommonMessage.debugMsg("workfloappkeyid:::::refId:::::::::::"+workfloappkeyid);
		}
		
		/*else if(action.equals("knowwhyappcountlist_save.Knowwhy")){
			 String knowwhykeyid=request.getParameter("knowwhykeyid");
			 CommonMessage.debugMsg("knowwhykeyid:::"+knowwhykeyid);
			 String knowwhycountlist=knowWhyService.getknowwhycountlist(knowwhykeyid);
		     CommonMessage.debugMsg("knowwhycountlist:::::keyId:::::::::::"+knowwhycountlist);
		}*/
		
		else if (action.equals("KnowWhygrid_save.KnowWhy")) {
			CommonMessage.debugMsg("KnowWhyGrid Save");
			Knowwhybean knowwhybean= new Knowwhybean();
			saveknowwhy(request,response,knowwhybean);
			
		}
		else if (action.equals("KnowWhygrid_delete.KnowWhy")) {
			deleteknowwhy(request,response);
			
		}
		else if (action.equals("Knowwhyform_save.KnowWhy")) {
			Knowwhybean knowwhybean= new Knowwhybean();
			saveknowwhydtl(request,response,knowwhybean);
		}
		
		else if (action.equals("Knowwhyform_delete.KnowWhy")){
			deleteknowwhydtl(request,response);
		}
	
		else if(action.equals("KnwwhyRpt_input.KnowWhy"))
		{	
			RequestDispatcher rd = request.getRequestDispatcher("/pages/KnowwhyRpt.jsp");
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);
		}
		else if(action.equals("KnwwhyRpt_getCol.KnowWhy")){
			CommonMessage.debugMsg("wherther the data is enter or not");
			PrintWriter out = response.getWriter();
			httpSession.removeAttribute("knowwhyCommonFilter");
			CommonFilter commonFilter = populateCommonFilter(request,"knowwhyrptCommonFilter",true);
			List<String[]> knowwhyGrid = knowWhyService.getKnowrpt(commonFilter);
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true)	;
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
		
			String [] colHeader = knowwhyGrid.get(2);			
			String [] colHeaderCond = knowwhyGrid.get(1);
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			//jsonObject.put("multiSelect", true);
			jsonObject.put("tableWidth", "110%%");
			jsonObject.put("tableHeight", "80%%");
			httpSession.removeAttribute("KnowwhyColModel");
			//httpSession.setAttribute("KnowwhyColModel", jsonObject);
			out.println(jsonObject);
		} 
		else if(action.equals("KnwwhyRpt_getData.KnowWhy")){
			CommonMessage.debugMsg("get data method");		
			try {
				UIUtils.displayRequestParamsValue(request);	
				httpSession.removeAttribute("knowwhyCommonFilter");
				CommonFilter commonFilter = populateCommonFilter(request,"knowwhyrptCommonFilter",false);
				List<String[]> knowwhyGrid = knowWhyService.getKnowrpt(commonFilter);
				CommonMessage.debugMsg("Data is enter or not" + knowwhyGrid.get(0));
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(knowwhyGrid, request, 3, 0);
				out.println(machinegrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}
		else if( action.equals("KnwwhyRpt_Excelview.KnowWhy") )
		{
	
			try{
				CommonFilter commonFilter = new CommonFilter();					
				String KnowId = request.getParameter("Knwid");
				String flid = request.getParameter("flid");
				commonFilter.setFlid(flid);
				CommonMessage.debugMsg("keyId::::::"+KnowId);
				String format = ExcelUtils.getFormat(request);
				//commonFilter = FilterValues.getCommonFilters(request, commonFilter);
				//commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
				 commonFilter = populateCommonFilter(request,"knowwhyrptCommonFilter",false);
				
				String path = UIUtils.getExcelTemplatePath(request);  	
				String imagePath = UIUtils.getImagePath(request);
				CommonMessage.debugMsg("image path "+imagePath);
				
				format = "xlsx";
				CommonMessage.debugMsg( " common" );
				Workbook wb = knowWhyService.knowWhyExportExcel(KnowId,format,path,imagePath,commonFilter);
				
	 
				ExcelUtils.writeToResponse(response, wb, "KnowWhyReportSheet_"+KnowId, format);
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
		else if (action.equals("KnowWhyDetails_view.KnowWhy")) {
			String clearForm = request.getParameter("clearForm");
			CommonMessage.debugMsg("clearForm:::"+clearForm);
			request.setAttribute("form", clearForm);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Knowwhydetails.jsp");
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);
		} 
	}


	private void deleteknowwhy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		 String deletemsg;
			if( httpSession != null && user != null)
		     {
				QtmTlKnowwhymst existqtmTlKnowwhymst=(QtmTlKnowwhymst)httpSession.getAttribute("qtmTlKnowwhymst");
	    		QtmTlKnowwhydtl qtmTlKnowwhydtl = new QtmTlKnowwhydtl();
	    		QtmTlKnowwhymst qtmTlKnowwhymst = new QtmTlKnowwhymst();
	    		qtmTlKnowwhydtl =(QtmTlKnowwhydtl)UIUtils.setBeanProperties((Object)qtmTlKnowwhydtl,request); 
	    		qtmTlKnowwhymst =(QtmTlKnowwhymst)UIUtils.setBeanProperties((Object)qtmTlKnowwhymst,request);

	    		Knowwhybean newKnowwhybean = (Knowwhybean)httpSession.getAttribute("Knowwhybean"); 
		    	
	    		qtmTlKnowwhymst.setKnwmCreatedby(user.getUsrm_ccno());
	    		qtmTlKnowwhydtl.setKnwdCreatedby(user.getUsrm_ccno());
	    		//CommonMessage.debugMsg("Created On "+existqtmTlKnowwhymst.getKnwmCreatedon()+" Modified on "+existqtmTlKnowwhymst.getKnwmModifiedon());
	    		existqtmTlKnowwhymst =	knowWhyService.delete(qtmTlKnowwhymst,existqtmTlKnowwhymst,newKnowwhybean);
				deletemsg="Data Deleted Successfully";
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",true);
				returnData.put("successData", successData);				
				out.print(returnData.toString());
			// TODO Auto-generated method stub
		     }
		    }

	private void deleteknowwhydtl(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String dtlkeyid = request.getParameter("dtlkeyid");
		 String deletemsg;
			if( httpSession != null && user != null)
		     {
				QtmTlKnowwhydtl existQtmTlKnowwhydtl = new QtmTlKnowwhydtl();
	    		QtmTlKnowwhydtl qtmTlKnowwhydtl = new QtmTlKnowwhydtl();
	    		Knowwhybean knowwhybean= new Knowwhybean();
	    		qtmTlKnowwhydtl =(QtmTlKnowwhydtl)UIUtils.setBeanProperties((Object)qtmTlKnowwhydtl,request);  
	    		existQtmTlKnowwhydtl =	knowWhyService.deleteknwwhydtl(qtmTlKnowwhydtl,dtlkeyid);
				deletemsg="Data Deleted Successfully";
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",true);
				returnData.put("successData", successData);				
				out.print(returnData.toString());
				
				
			// TODO Auto-generated method stub
		     }
	}

	private void saveknowwhydtl(HttpServletRequest request,
			HttpServletResponse response, Knowwhybean knowwhybean) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String type=request.getParameter("type");
    	String detailSave = request.getParameter("detailSave");
    	CommonMessage.debugMsg("The Type Is"+type);
    	try{
    	if( httpSession != null && user != null)
    	{	
    		QtmTlKnowwhymst qtmTlKnowwhymst = new QtmTlKnowwhymst();
    		QtmTlKnowwhydtl qtmTlKnowwhydtl = new QtmTlKnowwhydtl();
    		//Knowwhybean knowwhybean= new Knowwhybean();
    		
			QtmTlKnowwhymst existQtmTlKnowwhymst = (QtmTlKnowwhymst)httpSession.getAttribute("qtmTlKnowwhymst");
			qtmTlKnowwhymst =(QtmTlKnowwhymst)UIUtils.setBeanProperties((Object)qtmTlKnowwhymst,request);
		 	qtmTlKnowwhydtl =(QtmTlKnowwhydtl)UIUtils.setBeanProperties((Object)qtmTlKnowwhydtl,request);
			List <GenTlAllmoduleimgfile> imagelist =new ArrayList<GenTlAllmoduleimgfile>();
			GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
			String preparedDate = request.getParameter("dteKnwmPrepareddate");
			CommonMessage.debugMsg(preparedDate+"Date prepared");
		 	String savemsg;
		 	String fileDir=UIUtils.TPM_TEMPIMG_DIR;
		 	String imagePath = UIUtils.getImagePath(request);
			qtmTlKnowwhymst.setKnwmCreatedby(user.getUsrm_ccno());
			
			qtmTlKnowwhymst.setFileDir(fileDir);
			qtmTlKnowwhymst.setImagePath(imagePath);
			
			
			CommonMessage.debugMsg(qtmTlKnowwhymst.getIsDtlTrue()  + "  if");
			if(qtmTlKnowwhydtl != null){
				CommonMessage.debugMsg(qtmTlKnowwhymst.getIsDtlTrue()  + " bfr if");
			  if(UIUtils.isValidKeyId(qtmTlKnowwhymst.getIsDtlTrue())) {
				 CommonMessage.debugMsg(qtmTlKnowwhymst.getIsDtlTrue() + " true or false ");
				qtmTlKnowwhymst.setQtmTlKnowwhydtl(qtmTlKnowwhydtl);
			  }
				qtmTlKnowwhydtl.setKnwdCreatedby(user.getUsrm_ccno());
			}
			CommonMessage.debugMsg("servlet check value :  "+qtmTlKnowwhymst.getCheckPhenomena());
				genTlAllmoduleimgfile.setImflBlobimage(imagePath );
				genTlAllmoduleimgfile.setImflFilename(qtmTlKnowwhymst.getKnwmImage());
				genTlAllmoduleimgfile.setImflImagetype("KNW");
				genTlAllmoduleimgfile.setImflRefdoctype("KNW");
				imagelist.add(genTlAllmoduleimgfile);
				//genTlAllmoduleimgfile.setAllmoduleimgfile(imagelist);				
				//qtmTlKnowwhymst.setKnwmImage(genTlAllmoduleimgfile.getImflFilename());
			CommonMessage.debugMsg(qtmTlKnowwhymst.getCheckPhenomena() + " checkvalue");
			if( ! UIUtils.isValidKeyId( qtmTlKnowwhymst.getKnwmKeyid() ))//
			{	
				 existQtmTlKnowwhymst =	knowWhyService.createdtl(qtmTlKnowwhymst,existQtmTlKnowwhymst,knowwhybean);
				 savemsg="Data Saved Successfully";
					 
			}
			else{
				 existQtmTlKnowwhymst= knowWhyService.updatedtl(qtmTlKnowwhymst,existQtmTlKnowwhymst,knowwhybean);
				savemsg="Data Updated Successfully";	
			}
			
				commonFilterService.saveImg(imagelist, existQtmTlKnowwhymst.getKnwmKeyid(),"KNW" );
        		File imgfile = new File(imagePath);      		
    			//UIUtils.delete(imgfile);
				JSONObject successData = new JSONObject();
				successData.put("msg",savemsg);
				JSONObject returnData = new JSONObject();//
				successData.put("keyIdValue",existQtmTlKnowwhymst.getKnwmKeyid());//Added this line - swetha
				if("Y".equals(detailSave)) { //Added this line - swetha
				    successData.put("detailSave", detailSave);
				}
				returnData.put("successData", successData);
				returnData.put("keyid",qtmTlKnowwhymst.getKnwmKeyid());
				returnData.put("image",qtmTlKnowwhymst.getKnwmImage());
				returnData.put("KnowLatVerNo",qtmTlKnowwhymst.getKnwmVersionno());
				returnData.put("knowLatestVerDate",qtmTlKnowwhymst.getKnwmVersiondate());
				returnData.put("KnowCurrentVerNo",qtmTlKnowwhymst.getKnwmTempfield5());
				returnData.put("fileDir",fileDir);
				CommonMessage.debugMsg(fileDir +  " fileDir in servlet");
				CommonMessage.debugMsg(existQtmTlKnowwhymst.getKnwmImage()  +  " images in servlet");
				returnData.put("formClear", false);
				returnData.put("type",type);
				out.print(returnData.toString());//
				out.close();
    				
    		}
        }catch (ValidationExceptions e) {
        	e.printStackTrace();
	        CommonMessage.debugMsg("ValidationExceptions");
	        net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(
			e.toString(), "Knowwhyproperties");
           out.print(errMessage.toString());
          }
      
	catch(Exception e){
		e.printStackTrace();
		CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not Saved");
		out.print(err.toString());
   }
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew)
	{
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			//commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		commonFilter.setViewClick('Y');

		String loginFlid = CommonFunctions.getLoginFlid(request);
		if (!UIUtils.isValidKeyId(commonFilter.getFlid()))
			commonFilter.setFlid(loginFlid);

		return commonFilter;
	}
	 
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("knowwhyCommonFilter");
		
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
			commonFilter = (CommonFilter) httpSession.getAttribute("knowwhyCommonFilter");
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
		
		List<String[]> knwwhyCummulativeList  = knowWhyService.getKnwwhycnt(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(knwwhyCummulativeList != null && knwwhyCummulativeList.size() > 0)
		{	
            String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
		
			chartObj = processLineChart(lcnname,knwwhyCummulativeList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	
	private JSONObject processLineChart(String titlename,List<String[]> knwwhyCummulativeList,CommonFilter commonFilter){
		//CommonMessage.debugMsg("oplCummulativeList size in processline char ="+oplCummulativeList.size());
		if( knwwhyCummulativeList == null || knwwhyCummulativeList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String[] header =  knwwhyCummulativeList.get(1);
		String[] month =  knwwhyCummulativeList.get(1);
		String[] data =  knwwhyCummulativeList.get(knwwhyCummulativeList.size()-1);
		
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
	
		String drillLevel =  FilterValues.getDrillHeader(data[1]);
		StringBuilder title = new StringBuilder( titlename+"-Know Why Cumulative Count - ").append( drillLevel).append( " Wide From ").append(date);
	
		
		String prevMonth = null;
		
		String subTitle = "";//data[2];
		//CommonMessage.debugMsg("subTitle "+subTitle );
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> cumulativeData = new ArrayList<Double>();
		
		for( int i = 3;i < header.length;i++ ){
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
			timeSeries.setName("Know Why Cumulative Count");
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
private JSONObject getTableModel(List<String[]> headers,String caption)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	//	String [] colHeader1 = new String[  headers.get(0).length + 1 ] ;
		
		String [] colHeader = headers.get(1) ;
		int header = colHeader.length;
		String [] headerArr = new String[header];
		for(int k=0;k<headerArr.length;k++)
			

		//CommonMessage.debugMsg(colHeader1 +"  =caption ="+colHeader );
		colHeader[2] = caption;  
		CommonMessage.debugMsg("colHeader[2] ="+colHeader[2]);
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
		{
			headerArr[i] = colHeader[i].replaceAll(" ", "");
			//colHeader1[ i ] = colHeader[i];
			jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex((colHeader[i]).replaceAll(" ", ""));
			jqGridColModel.setName((colHeader[i]).replaceAll(" ", ""));
					
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


	private void saveknowwhy(HttpServletRequest request,
			HttpServletResponse response, Knowwhybean knowwhybean) throws IOException {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside the SaveKnowWhy");
		HttpSession httpSession = request.getSession(false);
		String getmatkeyid=null;
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	try{
    	if( httpSession != null && user != null)
    	{	
    		QtmTlKnowwhymst qtmTlKnowwhymst = new QtmTlKnowwhymst();
    		//QtmTlKnowwhydtl qtmTlKnowwhydtl = new QtmTlKnowwhydtl();
    		//Knowwhybean knowwhybean= new Knowwhybean();
			QtmTlKnowwhymst existQtmTlKnowwhymst = (QtmTlKnowwhymst)httpSession.getAttribute("qtmTlKnowwhymst");
			qtmTlKnowwhymst =(QtmTlKnowwhymst)UIUtils.setBeanProperties((Object)qtmTlKnowwhymst,request);
		 	//qtmTlKnowwhydtl =(QtmTlKnowwhydtl)UIUtils.setBeanProperties((Object)qtmTlKnowwhydtl,request);
			List <GenTlAllmoduleimgfile> imagelist =new ArrayList<GenTlAllmoduleimgfile>();
			GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
			 String VersionNo;

			qtmTlKnowwhymst.getKnwmKeyid();
			CommonMessage.debugMsg("qtmTlKnowwhymst.getKnwmKeyid():::::save Function mst::"+qtmTlKnowwhymst.getKnwmKeyid());
			
			
		 	String savemsg;
		 	String fileDir=UIUtils.TPM_TEMPIMG_DIR;
		 	String imagePath = UIUtils.getImagePath(request);
			qtmTlKnowwhymst.setKnwmCreatedby(user.getUsrm_ccno());
			
			qtmTlKnowwhymst.setFileDir(fileDir);
			qtmTlKnowwhymst.setImagePath(imagePath);
			
			String filemanger = request.getParameter("filemanger");
			
			    CommonMessage.debugMsg("servlet check value :  "+qtmTlKnowwhymst.getCheckPhenomena());
				genTlAllmoduleimgfile.setImflBlobimage(imagePath );
				genTlAllmoduleimgfile.setImflFilename(qtmTlKnowwhymst.getKnwmImage());
				genTlAllmoduleimgfile.setImflImagetype("KNW");
				genTlAllmoduleimgfile.setImflRefdoctype("KNW");
				imagelist.add(genTlAllmoduleimgfile);
				
				
				  if(mode!=null){
					if(mode=="Create"){
						qtmTlKnowwhymst.setKnwmVersionno("1");
					}			
				}
		
			CommonMessage.debugMsg(qtmTlKnowwhymst.getCheckPhenomena() + " checkvalue");
			if( ! UIUtils.isValidKeyId( qtmTlKnowwhymst.getKnwmKeyid() ))//
			{	
				     qtmTlKnowwhymst.setKnwmVersionno("1");
					 existQtmTlKnowwhymst =	knowWhyService.create(qtmTlKnowwhymst,existQtmTlKnowwhymst,knowwhybean);
					 savemsg="Data Saved Successfully";
			}
			else{  
				String Keyidverid=qtmTlKnowwhymst.getKnwmKeyid();
				
				if(Keyidverid!=null){
					
					getmatkeyid=knowWhyService.getkeyiddetail(Keyidverid);
					CommonMessage.debugMsg("getmatkeyid:::::::"+getmatkeyid);
				   
					if(getmatkeyid!=null)
				    {
				    	
						//CommonMessage.debugMsg("inside the if");
						int versionnumb=Integer.parseInt(getmatkeyid);
						CommonMessage.debugMsg("inside the if"+versionnumb);
						int incrval=1;
				    	int versionincr=versionnumb+incrval;
				    	CommonMessage.debugMsg("inside the if"+versionincr);
				    	String vrsnmodify=String.valueOf(versionincr);
				    	CommonMessage.debugMsg("inside the if"+vrsnmodify);
				    	qtmTlKnowwhymst.setKnwmVersionno(vrsnmodify);
				    	 int x=Integer.parseInt(vrsnmodify);
					      CommonMessage.debugMsg("The X Value"+x);
					      x=x-1;
					      String val=String.valueOf(x);
					    //  CommonMessage.debugMsg("The X Value"+val);
					      qtmTlKnowwhymst.setKnwmTempfield5(val);
				   }
				    
				
				}
				  //   qtmTlKnowwhymst.setKnwmVersionno("1");
					 existQtmTlKnowwhymst= knowWhyService.update(qtmTlKnowwhymst,existQtmTlKnowwhymst,knowwhybean);
					 savemsg="Data Updated Successfully";	
			}
			
				commonFilterService.saveImg(imagelist, existQtmTlKnowwhymst.getKnwmKeyid(),"KNW" );
        		File imgfile = new File(imagePath);      		
    			//UIUtils.delete(imgfile);
				JSONObject successData = new JSONObject();
				successData.put("msg",savemsg);
				JSONObject returnData = new JSONObject();//
				returnData.put("successData", successData);
				returnData.put("keyid",qtmTlKnowwhymst.getKnwmKeyid());
				returnData.put("image",qtmTlKnowwhymst.getKnwmImage());
				returnData.put("fileDir",fileDir);
				
				if(UIUtils.isValidKeyId(filemanger)){
				    CommonMessage.debugMsg(" Inside filemanger "+filemanger);
					returnData.put("filemanger",true);
					returnData.put("formClear",false);
				}
				CommonMessage.debugMsg(fileDir +  " fileDir in servlet");
				CommonMessage.debugMsg(existQtmTlKnowwhymst.getKnwmImage()  +  " images in servlet");
				returnData.put("formClear", false);
				out.print(returnData.toString());//
				out.close();
    		}
        }catch (ValidationExceptions e) {
        	e.printStackTrace();
	        CommonMessage.debugMsg("ValidationExceptions");
	        net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(
			e.toString(), "Knowwhyproperties");
           out.print(errMessage.toString());
          }
      
	catch(Exception e){
		e.printStackTrace();
		CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not Saved");
		out.print(err.toString());
   }

}


}
