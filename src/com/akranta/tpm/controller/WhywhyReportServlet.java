package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.YYFormBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.BdmTlYydonebymst;
import com.akranta.tpm.model.BdmTlYyeffectivedtl;
import com.akranta.tpm.model.BdmTlYyeffectivemst;
import com.akranta.tpm.model.BdmTlYyproblemattbymst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.BreakdownService;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.WhyWhyAnalysisService;
import com.akranta.tpm.service.WhywhyReportService;
import com.akranta.tpm.service.WorkOrderService;
import com.akranta.tpm.service.impl.BreakdownServiceImpl;
import com.akranta.tpm.service.impl.WhyWhyAnalysisServiceImpl;
import com.akranta.tpm.service.impl.WhywhyReportServiceImpl;
import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

public class WhywhyReportServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	private static final String gendrillcommonfilter = "whywhygendrillfilter"; 
	
	WhywhyReportService whywhyService;
	WhyWhyAnalysisService yyService;
	WorkOrderService workOrderService;
	BreakdownService breakDownService ;
	DashboardService dashboardService;
	WhywhyServiceApi whywhyServiceApi;
	private Collection mode;
	
	String  filePath = null;
	private static String DOC_ROOT_PATH;
	private static String docRealPath;
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static  String APP_DOCMANAGER_PATH ;
	
	//private ServletRequest httpSession;
	/*public void init(ServletConfig config) throws ServletException{
		filePath = config.getServletContext().getRealPath("tmp") + "\\";
		new File(filePath).mkdirs();
	}*/
	
	
	public void init(ServletConfig config) throws ServletException {
    	try{
        super.init(config);
        
        filePath = config.getServletContext().getRealPath("tmp") + "\\";
		new File(filePath).mkdirs();
		
        docRealPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
        
        boolean s = new File(docRealPath).mkdirs();
        
        DOC_ROOT_PATH = getServletContext().getRealPath("DocumentManagerServlet") ;
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        
        String parentFolderName = DOC_ROOT_PATH.substring(DOC_ROOT_PATH.lastIndexOf("\\")+1);
       // CommonMessage.debugMsg( " parentFolderName "  + parentFolderName);
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        //APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        CommonMessage.debugMsg("DOC_ROOT_PATH Path : "+ DOC_ROOT_PATH);
        APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        String basePath = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "FILEMANGER_BASE_PATH");
        CommonMessage.debugMsg(" FileManagerBasePath " + basePath );
        
        boolean pathExist = new File( basePath).exists();
        CommonMessage.debugMsg(" basePath  pathExist " + basePath + "  " + pathExist );
        if( basePath != null && pathExist ){
        	DOC_ROOT_PATH = basePath;
        	APP_DOCMANAGER_PATH = basePath +"/" +parentFolderName ;
        	
        }
        CommonMessage.debugMsg(" DOC_ROOT_PATH " + DOC_ROOT_PATH );
        CommonMessage.debugMsg(" ----APP_DOCMANAGER_PATH " + APP_DOCMANAGER_PATH );
    	}catch(Exception e ){
    		e.printStackTrace();
    		CommonMessage.debugMsg(" File Manager exception " + e.getMessage());
    	}
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
		ComboFilter doccomboFilter = new ComboFilter();
		HttpSession sttpSession = request.getSession(false);

		try {
			whywhyService = (WhywhyReportServiceImpl)UIUtils.getServiceObject(request,"WhywhyReportServiceImpl");
			yyService = (WhyWhyAnalysisServiceImpl)UIUtils.getServiceObject(request,"WhyWhyAnalysisServiceImpl");
			CommonMessage.debugMsg("yyserivce completed ");
			breakDownService  = (BreakdownServiceImpl)UIUtils.getServiceObject(request,"BreakdownServiceImpl");
			CommonMessage.debugMsg("breakDownService completed ");
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			workOrderService   = (WorkOrderServiceImpl)UIUtils.getServiceObject(request,"WorkOrderServiceImpl");
			CommonMessage.debugMsg("workOrderService completed ");
			CommonMessage.debugMsg("  Minutes of Meeting jwt token : "+sttpSession.getAttribute("tpmjwttoken") );
			yyService.WhywhyAnalysisServiceImplJwt((String) (sttpSession.getAttribute("tpmjwttoken") == null ? "" : sttpSession.getAttribute("tpmjwttoken")) );
			whywhyService.WhywhyReportServiceImplJwt((String) (sttpSession.getAttribute("tpmjwttoken") == null ? "" : sttpSession.getAttribute("tpmjwttoken")) );
		}
		
		catch (ServiceObjectCreationException e) {
			//CommonMessage.debugMsg(e);
		}

		
		if( action.equals("filterXmlwhywhyReport_input.why")){
			response.setContentType("xml");		
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyStandard.xml");
		 }else if( action.equals("filterXmlwhywhyEffectiveness_input.why")){
				response.setContentType("xml");		
				CommonMessage.debugMsg("action "+ action); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyEffecRpt.xml");
			}	else if( action.equals("whywhyEffectiveness_input.why")){
				UIUtils.forwardRequest(request, response, "/pages/EffectivenessForm.jsp");
			 }
			else if( action.equals("whywhyEffectiveness_getCol.why")){
				 HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",true);
				
				commonFilter.setIsGetCol("Y");
				
				List< String[]> YYEffectiveList  = whywhyService.getYYEffectiveness(commonFilter);
	   	    	JSONObject jsonObject = null;
			 	
	   	    	JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				
				//jqGridTableModel.setGroupBy(true);
				//jqGridTableModel.setGroupByField("WWMS_KEYID");
				//String[] formatterval  = {"cmbEffective#12"};
				//String[] formatterval  = {"dteTargetDate#6","cmbAbnmTrade#7","cmbAbnmResponse#9"};
				//jqGridTableModel.setFormatterIndex(formatterval);
				
				gridColModel.setHeaderNum(1);
				
				
				
				String [] colHeader = YYEffectiveList.get(1);			
				String [] colHeaderCond = YYEffectiveList.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				colModel.set("multiSelect", true);
				httpSession.removeAttribute("YYEffectColModel");
				httpSession.setAttribute("YYEffectColModel",colModel);	
				CommonMessage.debugMsg("jsonObject " + colModel);
				out.println(colModel);
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYEffectiveness"));
			 }
			 else if(action.equals("whywhyEffectiveness_getData.why"))
				{
					try
					{
						PrintWriter out = response.getWriter();
						JSONObject jsonObject = new JSONObject();
						CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",false);
						commonFilter.setIsGetCol("N");
						
						List< String[]> YYEffectiveList  = whywhyService.getYYEffectiveness(commonFilter);
						CommonMessage.debugMsg("size " + YYEffectiveList.size());
						CommonMessage.debugMsg("common " + commonFilter.getTotalRecordCnt());
						//jsonObject  = UIUtils.convertToJqGridTableObject(YYEffectiveList,request,2,0,YYEffectiveList.size()); 
						jsonObject  = UIUtils.convertToJqGridTableObject(YYEffectiveList,request,2,0,commonFilter.getTotalRecordCnt());
						out.println(jsonObject);
				    }catch(Exception e)
					{
							e.printStackTrace();
					}
				}
		
			 else if(action.equals("whywhyEffectiveness_getExcel.why"))
				{
				    CommonFilter	commonFilter = populateCommonFilter(request,"whywhyCommonFilter",false);
				    HttpSession httpSession = request.getSession(false);
				    String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);//
					httpSession = request.getSession(false);
					JSONObject colmodel = (JSONObject) httpSession.getAttribute("YYEffectColModel");
					colmodel.put("title","Why Why Effectiveness Report");
		            String format = ExcelUtils.getFormat(request);
					Workbook wb = whywhyService.WhyWhyEffectivenessExportExcel(commonFilter,colmodel,format);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response,wb,"WhyWhyEffectivenessReport", format);
				}
			 else if (action.equals("whywhyanalysisgrid_recall.why")){
				    PrintWriter out = response.getWriter();
					String cellId = request.getParameter("cellId");
					String formName=request.getParameter("frm");
					String keyid=request.getParameter("keyid");
					CommonMessage.debugMsg("keyid   keyid  :  "+cellId);
					CommonMessage.debugMsg(" Inside recall method for :: "+keyid+" formName :: "+formName);
					List<String []> condReclData  = whywhyService.FillControlData(cellId,keyid,formName);
					out.print( JSONArray.fromCollection(condReclData));
				}
		else if(action.equals("whywhyReport_input.why")){
			CommonMessage.debugMsg("1111111111111111input   ::::  ");
			request.setAttribute("yyrep", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","yyrep"));
			request.setAttribute("yydetails", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","yydetails"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/WhyWhyStandardReport.jsp"); 
			rd.forward(request, response); 
		}if( action.equals("filterXmlwhywhyanalysis_input.why")){
			response.setContentType("xml");		
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyAnalysisRpt.xml");
		}
		else if( action.equals("whywhyExl_view.why") )
		{
			try{					
				String keyid =request.getParameter("keyId");
				String imagePath = UIUtils.getImagePath(request);
				CommonMessage.debugMsg("keyid::::::"+keyid);
				String format = ExcelUtils.getFormat(request);
				format = "xlsx";
				String path = UIUtils.getExcelTemplatePath(request);  	
				Workbook wb = whywhyService.getwhywhyExlView(keyid,format,path,imagePath); 
				ExcelUtils.writeToResponse(response, wb, "WhyWhy_"+keyid, format);
			}			
			catch(Exception e)
			{
				e.printStackTrace();
				CommonMessage.debugMsg("err:"+e.getMessage());
				PrintWriter out = response.getWriter();
				JSONObject err = new JSONObject();				
				//err.put("exception",true);				
				err.put("message" ,"Data Not Found" );
				out.print(err.toString());
			}				
		}
		else if( action.equals("whywhyanalysis_input.why"))     ///TTTT
		{ 
			CommonMessage.debugMsg("action "+ action); 
		 String mode=request.getParameter("Mode");
		 if(mode==" "||mode==null){
			 mode="modify";
		 }
		 else{
			 mode="View";
		 }
		 request.setAttribute("formMode", mode);
			UIUtils.forwardRequest(request, response, "/pages/WhyWhyAnalysisMainFormGrid.jsp");
		 }
		else if( action.equals("whywhyanalysisageingrpt_input.why"))     ///TTTT
		{ 
			UIUtils.forwardRequest(request, response, "/pages/WhyWhyAnalysisAgeRpt.jsp");
		 }
		else if(action.equals("Whywhycount_input.why")){
			
			UIUtils.forwardRequest(request, response, "/pages/Reports/whywhycount.jsp");
}
	else if( action.equals("Whywhycount_getCol.why")){
		buildTableCountColModel( request,response);
	}
	else if( action.equals("WhywhyRootCausecount_getCol.why")){
		HttpSession httpSession = request.getSession(false);
		 String firstClick =request.getParameter("firstClick");
		 String flid=request.getParameter("flid");
		 CommonFilter  commonFilter  ;
		 commonFilter = new CommonFilter(); 
		
			CommonMessage.debugMsg("getcol:" +firstClick);
		 
		 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
		 {
			 commonFilter =(CommonFilter) httpSession.getAttribute("HseModeWiseRptReportCommonFilter");
		 }	
		if(commonFilter==null)
		 commonFilter = new CommonFilter(); 
		 
		 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
		 commonFilter= 	FilterValues.getSafty(request, commonFilter);
	
		 String CurrentYear=CommonFunctions.getCurrentYear();
		 CommonMessage.debugMsg("CurrentYear"+CurrentYear);
		 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
		 CommonMessage.debugMsg("PreviousYear"+PreviousYear);
		 String FYearStart="APR-"+CurrentYear;
		 CommonMessage.debugMsg("FYearStart"+FYearStart);
		 Integer NextYear=Integer.parseInt(CurrentYear)+1;
		 CommonMessage.debugMsg("FYearEnd"+NextYear);
		// String CurrentYear=CommonFunctions.getCurrentYear();
		 String FYearEnd="MAR-"+(NextYear);
			 commonFilter.setAbnDetect(FYearStart);
		     commonFilter.setAbnAllch(FYearEnd);
		     commonFilter.setFlid(flid);
		 httpSession.removeAttribute("HseModeWiseRptReportCommonFilter");
		 httpSession.setAttribute("HseModeWiseRptReportCommonFilter",commonFilter);
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 List<String[]> hseAccidentRpt  =yyService.getRootCauseList(commonFilter);
				 // hseAccidentRptService.getModeWiseReport(commonFilter);
		 //	List<String[]> impVscomList = adminPanelService.getAllGraphicalSumm(commonFilter);
			
		 JSONObject HSEData = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,0,0,hseAccidentRpt.size());
	
		 CommonMessage.debugMsg(HSEData);
			httpSession.setAttribute("HseBodyVsRptReportdata", HSEData);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setSortable(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = hseAccidentRpt.get(1);			
			String [] colHeaderCond = hseAccidentRpt.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			colModel.put("data", HSEData);
			CommonMessage.debugMsg("colModel   "+colModel);
		 
		//JSONObject colModel = getTableModel_BODYVSACC(hseAccidentRpt,commonFilter);
		 colModel.set("tableHeight", "70%%");
		 colModel.set("tableWidth", "97%%");
		 
		 
		 httpSession.removeAttribute("HseModeWiseRptReportColModel");
		 httpSession.setAttribute("HseModeWiseRptReportColModel", colModel);
		 CommonMessage.debugMsg(colModel);
		 out.println(colModel);
		
		 out.close();
	}
	else if( action.equals("WhywhyRootCausecount_getData.why") )
	{
		PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession(false);
		try
		{
			 UIUtils.displayRequestParamsValue(request);
			 String page = request.getParameter("page");	
			 CommonMessage.debugMsg("page......"+page);
			 String flid=request.getParameter("flid");
			 httpSession = request.getSession();
			 CommonFilter commonFilter = populateCommonFilter(request,"HseModeWiseRptReportCommonFilter",false);
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 CommonMessage.debugMsg("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 CommonMessage.debugMsg("PreviousYear"+PreviousYear);
			 String FYearStart="APR-"+CurrentYear;
			 CommonMessage.debugMsg("FYearStart"+FYearStart);
			 Integer NextYear=Integer.parseInt(CurrentYear)+1;
			 CommonMessage.debugMsg("FYearEnd"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="MAR-"+(NextYear);
			 commonFilter.setAbnDetect(FYearStart);
		     commonFilter.setAbnAllch(FYearEnd);
		     commonFilter.setFlid(flid);
			     JSONObject jsonObject = new JSONObject();			
        		 List<String[]> hseAccidentRpt  = yyService.getRootCauseList(commonFilter);
        		 if(hseAccidentRpt.size() > 3)
					{
        		 jsonObject = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,2,0,hseAccidentRpt.size()); 
        	 
			
			 out.println(jsonObject);
			 commonFilter.setViewClick('Y');	  			 	
  			 httpSession.removeAttribute("HseModeWiseRptReportCommonFilter");
  			 httpSession.setAttribute("HseModeWiseRptReportCommonFilter", commonFilter);
					}
        		 else
        		 {
        			 
        		 }

	    }
		catch(Exception e)
		{
		}
	}
	else if( action.equals("WhywhyCounterMeasure_getCol.why")){
		HttpSession httpSession = request.getSession(false);
		 String firstClick =request.getParameter("firstClick");
		 String flid=request.getParameter("flid");
		 CommonFilter  commonFilter  ;
		 commonFilter = new CommonFilter(); 
		
			CommonMessage.debugMsg("getcol:" +firstClick);
		 
		 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
		 {
			 commonFilter =(CommonFilter) httpSession.getAttribute("HseModeWiseRptReportCommonFilter");
		 }	
		if(commonFilter==null)
		 commonFilter = new CommonFilter(); 
		 
		 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
		 commonFilter= 	FilterValues.getSafty(request, commonFilter);
	
		 String CurrentYear=CommonFunctions.getCurrentYear();
		 CommonMessage.debugMsg("CurrentYear"+CurrentYear);
		 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
		 CommonMessage.debugMsg("PreviousYear"+PreviousYear);
		 String FYearStart="APR-"+CurrentYear;
		 CommonMessage.debugMsg("FYearStart"+FYearStart);
		 Integer NextYear=Integer.parseInt(CurrentYear)+1;
		 CommonMessage.debugMsg("FYearEnd"+NextYear);
		// String CurrentYear=CommonFunctions.getCurrentYear();
		 String FYearEnd="MAR-"+(NextYear);
			 commonFilter.setAbnDetect(FYearStart);
		     commonFilter.setAbnAllch(FYearEnd);
		     commonFilter.setFlid(flid);
		 httpSession.removeAttribute("HseModeWiseRptReportCommonFilter");
		 httpSession.setAttribute("HseModeWiseRptReportCommonFilter",commonFilter);
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 List<String[]> hseAccidentRpt  =yyService.getCounterMeasureList(commonFilter);
				 // hseAccidentRptService.getModeWiseReport(commonFilter);
		 //	List<String[]> impVscomList = adminPanelService.getAllGraphicalSumm(commonFilter);
			
		 JSONObject HSEData = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,0,0,hseAccidentRpt.size());
	
		 CommonMessage.debugMsg(HSEData);
			httpSession.setAttribute("HseBodyVsRptReportdata", HSEData);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setSortable(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = hseAccidentRpt.get(1);			
			String [] colHeaderCond = hseAccidentRpt.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			colModel.put("data", HSEData);
			CommonMessage.debugMsg("colModel   "+colModel);
		 
		//JSONObject colModel = getTableModel_BODYVSACC(hseAccidentRpt,commonFilter);
		 colModel.set("tableHeight", "70%%");
		 colModel.set("tableWidth", "97%%");
		 
		 
		 httpSession.removeAttribute("HseModeWiseRptReportColModel");
		 httpSession.setAttribute("HseModeWiseRptReportColModel", colModel);
		 CommonMessage.debugMsg(colModel);
		 out.println(colModel);
		
		 out.close();
	}
	else if( action.equals("WhywhyCounterMeasure_getData.why") )
	{
		PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession(false);
		try
		{
			 UIUtils.displayRequestParamsValue(request);
			 String page = request.getParameter("page");	
			 CommonMessage.debugMsg("page......"+page);
			 String flid=request.getParameter("flid");
			 httpSession = request.getSession();
			 CommonFilter commonFilter = populateCommonFilter(request,"HseModeWiseRptReportCommonFilter",false);
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 CommonMessage.debugMsg("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 CommonMessage.debugMsg("PreviousYear"+PreviousYear);
			 String FYearStart="APR-"+CurrentYear;
			 CommonMessage.debugMsg("FYearStart"+FYearStart);
			 Integer NextYear=Integer.parseInt(CurrentYear)+1;
			 CommonMessage.debugMsg("FYearEnd"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="MAR-"+(NextYear);
			 commonFilter.setAbnDetect(FYearStart);
		     commonFilter.setAbnAllch(FYearEnd);
		     commonFilter.setFlid(flid);
			     JSONObject jsonObject = new JSONObject();			
        		 List<String[]> hseAccidentRpt  = yyService.getCounterMeasureList(commonFilter);
        		 if(hseAccidentRpt.size() > 3)
					{
        		 jsonObject = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,2,0,hseAccidentRpt.size()); 
        	 
			
			 out.println(jsonObject);
			 commonFilter.setViewClick('Y');	  			 	
  			 httpSession.removeAttribute("HseModeWiseRptReportCommonFilter");
  			 httpSession.setAttribute("HseModeWiseRptReportCommonFilter", commonFilter);
					}
        		 else
        		 {
        			 
        		 }

	    }
		catch(Exception e)
		{
		}
	}	
	else if( action.equals("Whywhycount_getData.why")){
		
		whywhycountGetData(request , response);
		
	}
	else if( action.equals("Whywhycount_barchart.why"))
	{
		processbarChart(request,response);
	}
	else if( action.equals("WhywhycountRootCause_barchart.why"))
	{
		processbarChartRootCause(request,response);
	}
	else if( action.equals("WhywhycountCounter_barchart.why"))
	{
		processbarChartCounter(request,response);
	}
	else if(action.equals("Whywhycount_getExcel.why")){
		exportWhyWhyCountExcel(request,response);
}
		//mano
	else if(action.equals("WhywhyRootCausecount_getExcel.why")){
	    exportWhyWhyCountRootCauseExcel(request,response);
	}
	else if(action.equals("WhywhyCounterMeasure_getExcel.why")){
	    exportWhyWhyCountCounterExcel(request,response);
	}

		 
		else if( action.equals("whywhyanalysismodify_input.why")){
			
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			
			String maskeyid =request.getParameter("keyid");
			String type=request.getParameter("type");
	 		String Mode=request.getParameter("hdnMode"); 

			
			String maskeyid1 = request.getParameter("cmbWwmsKeyid");
			CommonMessage.debugMsg(Mode+"   maskeyid..."+maskeyid+"...maskeyid1..."+maskeyid1);
			if(!UIUtils.isValidKeyId(maskeyid))
				maskeyid = maskeyid1;
			CommonMessage.debugMsg("maskeyid "+maskeyid);
			
			BdmTlWhywhymst newBdmTlWhywhymst = new BdmTlWhywhymst();   
		
			if(UIUtils.isValidKeyId(maskeyid)){
				newBdmTlWhywhymst = yyService.selectmaskeyid(maskeyid);
				//CommonMessage.debugMsg("newBdmTlWhywhymst "+newBdmTlWhywhymst.getWwmsArea());
				String[] rptData = newBdmTlWhywhymst.getWwmsReportdatetime().split(" ");
		 		newBdmTlWhywhymst.setWwmsReportdatetime(rptData[0]);
		 		String date = newBdmTlWhywhymst.getWwmsDate();
		 		String reporteddate = newBdmTlWhywhymst.getWwmsReportdatetime();
		 		
		 		newBdmTlWhywhymst.setWwmsDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));
		 		
		 		newBdmTlWhywhymst.setWwmsReportdatetime(CommonFunctions.pg_getDateTimeFromPGTimeStamp(reporteddate));
		 		
		 		//CommonMessage.debugMsg(" Inside input action :: "+rptData[1]);
		 		CommonMessage.debugMsg(" Checking For Reported Time Date :: "+newBdmTlWhywhymst.getWwmsReportdatetime());
		 		//request.setAttribute("time" ,rptData[1]);	
		 		if(UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsOthercheckpoints()))
		 			request.setAttribute("otherCheck" ,"Y");
		 		else
		 			request.setAttribute("otherCheck" ,"N");
			}
	 		HttpSession httpSession = request.getSession(false);
	 		
	 		String refDocId = request.getParameter("refdocid");
	 		String refdoctype = request.getParameter("refdoctype");
	 		CommonMessage.debugMsg("refdoctype"+refdoctype);
	 		String flid = request.getParameter("flid");
	 		String refdocdate = request.getParameter("refdocdate");
	 		String problem = request.getParameter("problem");
	 		String attendedBy = request.getParameter("attendedBy");
	 		String area = request.getParameter("area");
	 		String pillar = request.getParameter("pillar");
	 		String mode = request.getParameter("yymode");
	 		CommonMessage.debugMsg("MOde:::"+Mode);
	 		
	 		CommonMessage.debugMsg("refDocId.."+refDocId+"...refdoctype.."+refdoctype+"..flid.."+flid+"..refdocdate.."+refdocdate+"..problem.."+problem);
	 		if(UIUtils.isValidKeyId(refDocId))
	 			newBdmTlWhywhymst.setWwmsRefdocno(refDocId);
	 		if(UIUtils.isValidKeyId(refdoctype))
	 			newBdmTlWhywhymst.setWwmsRefdoctype(refdoctype);
	 		if(UIUtils.isValidKeyId(flid))
	 			newBdmTlWhywhymst.setWwmsFlid(flid);
	 		if(UIUtils.isValidKeyId(refdocdate))
	 			newBdmTlWhywhymst.setWwmsDate(refdocdate);
	 		if(UIUtils.isValidKeyId(problem))
	 			newBdmTlWhywhymst.setWwmsProblem(problem);
	 		if(UIUtils.isValidKeyId(area))
	 			newBdmTlWhywhymst.setWwmsArea(area);
	 		if(UIUtils.isValidKeyId(attendedBy))
	 			newBdmTlWhywhymst.setWwmsProblemattendby(attendedBy);
	 		
	 		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsWhywhydoneby()))
	 			newBdmTlWhywhymst.setWwmsWhywhydoneby(user.getUsrm_ccno());
	 		
	 		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsProblemattendby()))
	 			newBdmTlWhywhymst.setWwmsProblemattendby(attendedBy);
	 		
	 		CommonMessage.debugMsg("123,,,"+newBdmTlWhywhymst.getWwmsDate());
			request.setAttribute("newBdmTlWhywhymst" ,newBdmTlWhywhymst);
			request.setAttribute("rcId",newBdmTlWhywhymst.getWwmsRootcauseid() );
			request.setAttribute("refDoctype",refdoctype);
			request.setAttribute("refDocdate",refdocdate);
			request.setAttribute("hdnmode",Mode);
			request.setAttribute("mode",mode);
			request.setAttribute("type",type);
			httpSession.setAttribute("newBdmTlWhywhymst", newBdmTlWhywhymst);
			CommonMessage.debugMsg("action "+ action);
			UIUtils.forwardRequest(request, response, "/pages/WhyWhyAnalysisMainForm.jsp");
		 }
		
		else if(action.equals("whywhyReport_getCol.why"))
		{
			   CommonMessage.debugMsg("1111111111111111list      ::::  col");
			  HttpSession httpSession = request.getSession(false);
			  PrintWriter out = response.getWriter();
			  CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",true);	
			 
			  if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
				  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
				  commonFilter.setToDate(CommonFunctions.getDate());
				 
		 	  }
	 		  
			  List< String[]> whywhyList  = whywhyService.getAllwhywhyStd(commonFilter);
			  String colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.whywhyRpt", "whywhyRpt");
			  JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				gridColModel.setHeaderNum(1);
				
				String [] colHeader = whywhyList.get(0);			
				String [] colHeaderCond = whywhyList.get(1);
				
				//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "82%%");
				jsonObject.put("tableWidth", "106%%");
				httpSession.setAttribute("yyReportColModel", jsonObject);
				out.println(jsonObject);
			
		      
		}	
		else if(action.equals("whywhyReport_getData.why"))
		{
			try
			{
				
				  HttpSession httpSession = request.getSession(false);
				  String page = request.getParameter("page");	
				  CommonMessage.debugMsg("1111111111111111list      :::: daya " +page);
				  PrintWriter out = response.getWriter();
				  CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",false);
				  CommonMessage.debugMsg("  11111 popoi " );
				  JSONObject jsonObject = new JSONObject();
					  List< String[]> whywhyList  = whywhyService.getAllwhywhyStd(commonFilter);
					  CommonMessage.debugMsg("size " + whywhyList.size());
					  jsonObject  = UIUtils.convertToJqGridTableObject(whywhyList,request,2,0,commonFilter.getTotalRecordCnt()); 
		          
					  CommonMessage.debugMsg("  11111"+jsonObject);
		    //      }
					  out.println(jsonObject);
		           
		    }catch(Exception e)
			{
					CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if( action.equals("whywhyReport_getExcel.why")){
			
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate;
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("yyReportColModel");
			
			tblJSONObj.put("title", "Why-Why Standard Report" +"  -  "+date);
			//tblJSONObj.put("groupBy",false);
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = whywhyService.yyExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "Why-WhyStandardReport", format);
			
		}
		/*
		 * else if(action.equals("whywhyanalysismaingrid_getExcel.why")) { HttpSession
		 * httpSession = request.getSession(false); CommonFilter commonFilter =
		 * populateCommonFilter(request,"whywhyanalysisCommonFilter",false);
		 * commonFilter.setViewClick('Y'); String tmpFromRow =
		 * commonFilter.getFromRow(); commonFilter.setFromRow(null);
		 * 
		 * JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("ColModel");
		 * tblJSONObj.put("title","WhyWhyAnalysis"); String format =
		 * ExcelUtils.getFormat(request);
		 * 
		 * Workbook wb = yyService.whywhyExportExcel(commonFilter,tblJSONObj,format);
		 * commonFilter.setFromRow(tmpFromRow); ExcelUtils.writeToResponse(response, wb,
		 * "WhyWhyAnalysis", format);
		 * 
		 * 
		 * }
		 */
		
		else if (action.equals("whywhyanalysismaingrid_getExcel.why")) {
		    try {
		        HttpSession httpSession = request.getSession(false);

		        
		        CommonFilter commonFilter = populateCommonFilter(request, "whywhyanalysisCommonFilter", false);
		        commonFilter.setViewClick('Y');

		        
		        String formMode = (String) httpSession.getAttribute("whywhyAnalysisFormMode");
		        if (formMode == null) formMode = "";
		        commonFilter.setMaintMode(formMode.trim());
		        CommonMessage.debugMsg("Excel FORMMODE from session: [" + formMode.trim() + "]");

		       
		        String tmpFromRow = commonFilter.getFromRow();
		        commonFilter.setFromRow(null);

		        JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("ColModel");
		        tblJSONObj.put("title", "WhyWhyAnalysis");
		        String format = ExcelUtils.getFormat(request);

		        
		        Workbook wb = yyService.whywhyExportExcel(commonFilter, tblJSONObj, format);

		        commonFilter.setFromRow(tmpFromRow);
		        ExcelUtils.writeToResponse(response, wb, "WhyWhyAnalysis", format);

		    } catch (Exception e) {
		        CommonMessage.debugMsg(e.getMessage());
		    }
		}
		
		else if (action.equals("whywhyanalysisageingrpt_getExcel.why")) {
		    try {
		        HttpSession httpSession = request.getSession(false);

		        
		        CommonFilter commonFilter = populateCommonFilter(request, "whywhyanalysisAgeingCommonFilter", false);
		        commonFilter.setViewClick('Y');

		        
		        String formMode = (String) httpSession.getAttribute("whywhyAnalysisFormMode");
		        if (formMode == null) formMode = "";
		        commonFilter.setMaintMode(formMode.trim());
		        CommonMessage.debugMsg("Excel FORMMODE from session: [" + formMode.trim() + "]");

		       
		        String tmpFromRow = commonFilter.getFromRow();
		        commonFilter.setFromRow(null);

		        JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("ColModel");
		        tblJSONObj.put("title", "WhyWhyAnalysis Age Report");
		        String format = ExcelUtils.getFormat(request);

		        
		        Workbook wb = yyService.whywhyAgeExportExcel(commonFilter, tblJSONObj, format);

		        commonFilter.setFromRow(tmpFromRow);
		        ExcelUtils.writeToResponse(response, wb, "WhyWhyAnalysis_Age_Report", format);

		    } catch (Exception e) {
		        CommonMessage.debugMsg(e.getMessage());
		    }
		}
	
		
		 else if(action.equals("yyDonebyLink_getCol.why")){
			 try {
				 String masterkeyid=request.getParameter("masterkeyid");
				 CommonMessage.debugMsg("manocheck" +masterkeyid);
					List<String[]> yyDonebyList = null ;// whywhyService.getyyDoneby(masterkeyid);
					JSONObject jsonObject = getyyDonebyTableModel(yyDonebyList);
					CommonMessage.debugMsg("Table model");
					CommonMessage.debugMsg("jsonObject "+jsonObject);
					PrintWriter  out = response.getWriter();
					out.println(jsonObject);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		
		 else if(action.equals("yyDonebyLink_getData.why")){
			 PrintWriter out = response.getWriter();
			 String masterkeyid=request.getParameter("masterkeyid");
			 List<String[]> yyDonebyList = whywhyService.getyyDoneby(masterkeyid);
				
			 JSONObject yyDonebydata = UIUtils.convertToJqGridTableObject( yyDonebyList, request, 1, 0);
			  out.println(yyDonebydata);
		 }

		if( action.equals("filterXmlwhywhyQtyReport_input.why")){
			response.setContentType("xml"); 
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyQtyStandard.xml") ;
		 }
		
	
		else if(action.equals("whywhyQtyReport_input.why")){			
		//	request.setAttribute("yyrep", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","yyrep"));
		//	request.setAttribute("yydetails", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","yydetails"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/WhyWhyStandardQtyReport.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("whywhyQtyReport_getCol.why"))
		{
			
			  HttpSession httpSession = request.getSession(false);
			  PrintWriter out = response.getWriter();
			  CommonFilter commonFilter = populateCommonFilter(request,"whywhyQtyCommonFilter",true);	
			 
			  List<String[]> yyQtyList  = whywhyService.getAllwhywhyQtyStd(commonFilter);
			  JSONObject bdData = UIUtils.convertToJqGridTableObject(yyQtyList,request,0,0,commonFilter.getTotalRecordCnt());
			  CommonMessage.debugMsg("Total Count : "+commonFilter.getTotalRecordCnt());
			  
			  httpSession.setAttribute("yyQtyDataServlet", bdData);				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(false);
				
				gridColModel.setHeaderNum(1);
				
				String [] colHeader1 = yyQtyList.get(2);
				String [] colHeaderCond = yyQtyList.get(1);
				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader1);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				//jsonObject.put("data", bdData);
				CommonMessage.debugMsg("colModel   "+jsonObject);
			  
			//  JSONObject jsonObject = getTableModel(yyQtyList);
			  jsonObject.put("tableHeight", "92%%");
			  jsonObject.put("tableWidth", "108%%");
			  httpSession.removeAttribute("yyQtyColModel");
		 	  httpSession.setAttribute("yyQtyColModel",jsonObject);
		 	   
			  out.println(jsonObject);
			  
		}	
		
		 else if(action.equals("yyprobAttbyLink_getCol.why")){
			 try {
				 String masterkeyid=request.getParameter("masterkeyid");
				 
				 
				 CommonMessage.debugMsg("getcolid"+masterkeyid);				
					//List<String[]> yyAttbyList = whywhyService.getProbAttby(masterkeyid);
				 List<String[]> yyAttbyList = null ;
					JSONObject jsonObject = getProbAttbyTableModel(yyAttbyList);
					
					CommonMessage.debugMsg("jsonObject "+jsonObject);
					PrintWriter  out = response.getWriter();
					out.println(jsonObject);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 else if(action.equals("yyprobAttbyLink_getData.why")){
			 PrintWriter out = response.getWriter();
			 String masterkeyid=request.getParameter("masterkeyid");
			 List<String[]> yyDonebyList = whywhyService.getProbAttby(masterkeyid);
				
			 JSONObject yyDonebydata = UIUtils.convertToJqGridTableObject( yyDonebyList, request, 1, 0);
			  out.println(yyDonebydata);
		 }
		else if( action.equals("probAttbyLink_save.why"))
		{	
			CommonMessage.debugMsg("Inside probAttbyLink_save.why");
			YYFormBean yyFormBean = new YYFormBean();
			saveprobAttbyLink(request,response,yyFormBean);
			
		}
		else if(action.equals("YYProbAttBy_delete.why")){
			CommonMessage.debugMsg("YYProbAttBy_delete.why");
			deleteYYProbAttBy(request,response);
		}
		

		else if(action.equals("whywhyQtyReport_getData.why"))
		{
			
			try
			{	  
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				String page = request.getParameter("page");	  
				CommonFilter commonFilter = populateCommonFilter(request,"whywhyQtyCommonFilter",true);	
						
				JSONObject jsonObject = new JSONObject();
				CommonMessage.debugMsg("PAGE : "+page);
			
					jsonObject = (JSONObject) httpSession.getAttribute("yyQtyDataServlet");
	        		CommonMessage.debugMsg("Total Count : "+commonFilter.getTotalRecordCnt());
	        		  List<String[]> yyQtyList  = whywhyService.getAllwhywhyQtyStd(commonFilter);
	        		  jsonObject = UIUtils.convertToJqGridTableObject(yyQtyList,request,3,0,commonFilter.getTotalRecordCnt()+1); 
	        	 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("error " +e.getMessage());
			}
		}
		else if( action.equals("whywhyQtyReport_getExcel.why")) /* excel added by Ramya */
		{
			HttpSession httpSession = request.getSession(false);					
			CommonFilter commonFilter = populateCommonFilter(request,"whywhyQtyCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("yyQtyColModel");					
			//JSONObject tblJSONObj = JSONObject.fromString(tableModel);					
			tblJSONObj.put("title", "Why Why Standard Report");
			String format = ExcelUtils.getFormat(request);
			Workbook wb =whywhyService.getAllwhywhyQtyExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "WhyWhyStandardRpt", format);
		}
		/* Created By Suresh.K on Jan 11,2012 */
		else if(action.equals("whywhy_input.why")){
			
			//yyService = (WhyWhyAnalysisServiceImpl)UIUtils.getServiceObject(request,"WhyWhyAnalysisServiceImpl");
			
			UIUtils.displayRequestParamsValue(request);
			HttpSession httpSession = request.getSession(false);
			BdmTlWhywhymst bdmTlWhywhymst = new BdmTlWhywhymst();			
			YYFormBean yyFormBean = new YYFormBean();
			
			String yyRefDocID = request.getParameter("whywhyRefDocID");
			
			
			if(UIUtils.isValidKeyId(yyRefDocID))
			{ 
				if(yyRefDocID.substring(0, 2).equals("BD") || yyRefDocID.substring(0, 2).equals("CU"))
					bdmTlWhywhymst = breakDownService.selectWhyWhy(yyRefDocID);			
				else if(yyRefDocID.substring(0, 2).equals("AB") || yyRefDocID.substring(0, 2).equals("GM"))
				{
					String womsKey = request.getParameter("womsKey");
					WomTlWomst womTlWomst = new WomTlWomst();
					if(UIUtils.isValidKeyId(womsKey))
					{
						womTlWomst = workOrderService.select(womsKey);
						fillYY(bdmTlWhywhymst,womTlWomst);
					}
				}
			}
			else
				bdmTlWhywhymst =(BdmTlWhywhymst) UIUtils.setBeanProperties((Object)bdmTlWhywhymst,request);
			
			String  refdocid = request.getParameter("whywhyRefDocID");
			String  refdoctype = request.getParameter("whywhyRefDocType");
			String  flid = request.getParameter("flid");
			String  refdocdate = request.getParameter("refDocDate");
			CommonMessage.debugMsg("refdocdate"+refdocdate);
			String  problem = request.getParameter("prbolem");
			String  yymode = request.getParameter("yyMode");
			String  attendedBy = request.getParameter("attendedBy");
			String  area = request.getParameter("area");
			String  pillar = request.getParameter("pillar");

			String filterStr = "refdocid="+refdocid+"&refdoctype="+refdoctype+"&flid="+flid+"&refdocdate="+refdocdate+"&problem="+URLEncoder.encode(problem, StandardCharsets.UTF_8)+"&yymode="+yymode+"&attendedBy="+attendedBy+"&area="+area+"&pillar="+pillar;
			CommonMessage.debugMsg("filterStr...."+filterStr);
			if(UIUtils.isValidKeyId(refdocid)){
				bdmTlWhywhymst.setWwmsRefdocno(refdocid);
				httpSession.setAttribute("refdocid",refdocid);
			}
			if(UIUtils.isValidKeyId(refdoctype)){
				bdmTlWhywhymst.setWwmsRefdoctype(refdoctype);
				httpSession.setAttribute("refdoctype",refdoctype);
				//request.setAttribute("refdoctype",refdoctype);
			}
			if(UIUtils.isValidKeyId(flid)){
				bdmTlWhywhymst.setWwmsFlid(flid);
				httpSession.setAttribute("flid",flid);
			}
			if(UIUtils.isValidKeyId(refdocdate)){
				bdmTlWhywhymst.setWwmsDate(refdocdate);
				httpSession.setAttribute("refdocdate",refdocdate);
			}
			if(UIUtils.isValidKeyId(refdocdate)){
				bdmTlWhywhymst.setWwmsProblem(problem);
				httpSession.setAttribute("problem",problem);
			}
			
			yyFormBean =(YYFormBean) UIUtils.setBeanProperties((Object)yyFormBean,request);
			String wwmsKey = (String) httpSession.getAttribute("wwmsKeyid");
			CommonMessage.debugMsg("Form Type .....................>"+yyFormBean.getFormType()+"................................");
			yyFormBean=new YYFormBean();			
			yyFormBean.setFormType("BD");
			if(yyFormBean.getFormType().equals("BD") ||yyFormBean.getFormType().equals("BDM") || yyFormBean.getFormType().equals("CC")|| yyFormBean.getFormType().equals("SHE")|| yyFormBean.getFormType().equals("DOCK")|| yyFormBean.getFormType().equals("IMT")||yyFormBean.getFormType().equals("ABN") )
			{
				if(yyFormBean.getFormType().equals("ABN"))
				{
					String refDocId = bdmTlWhywhymst.getWwmsRefdocno();
					CommonMessage.debugMsg("refDocId "+refDocId);
					if(UIUtils.isValidKeyId(refDocId))
					{
						String existFlag = yyService.checkMstExist(refDocId);
						if(UIUtils.isValidKeyId(existFlag))
						{
							if(Integer.parseInt(existFlag)>0)
								bdmTlWhywhymst = breakDownService.selectWhyWhy(refDocId);
						}
					}
				}
				httpSession.removeAttribute("WHYWHYFORMTYPE");
				httpSession.setAttribute("WHYWHYFORMTYPE",yyFormBean.getFormType());
				if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsKeyid()))
				{
					String YYKey = bdmTlWhywhymst.getWwmsKeyid();
					if(UIUtils.isValidKeyId(YYKey))
					{
						bdmTlWhywhymst = yyService.getWWMS(YYKey);
					}
					List<String[]> yyMstValues = yyService.getSelectedRootCause(bdmTlWhywhymst.getWwmsKeyid());
					//bdmTlWhywhymst.setWwmsFinalaction(yyMstValues.get(0)[6]);
					bdmTlWhywhymst.setWwmsChecksmade(yyMstValues.get(0)[7]);
					bdmTlWhywhymst.setWwmsYoudidnot(yyMstValues.get(0)[8]);
					bdmTlWhywhymst.setWwmsCountermeasure(yyMstValues.get(0)[9]);
					request.setAttribute("rcId", yyMstValues.get(0)[0]);
					request.setAttribute("isJH", yyMstValues.get(0)[2]);
					request.setAttribute("isPM", yyMstValues.get(0)[3]);
					request.setAttribute("isCI", yyMstValues.get(0)[4]);
					request.setAttribute("isET", yyMstValues.get(0)[5]);
			
				}
				request.setAttribute("causeId",request.getParameter("cmbwwmsCauseid"));	
			}				
			else
			{
				CommonMessage.debugMsg("Why Why Key : "+bdmTlWhywhymst.getWwmsKeyid());
				String YYKey = bdmTlWhywhymst.getWwmsKeyid();
				if(UIUtils.isValidKeyId(YYKey))
				{
					bdmTlWhywhymst = yyService.getWWMS(YYKey);
				}
			}
			
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsDate()))
			{
				if(bdmTlWhywhymst.getWwmsDate().indexOf(":")>0)
					bdmTlWhywhymst.setWwmsDate(bdmTlWhywhymst.getWwmsDate().substring(0, bdmTlWhywhymst.getWwmsDate().indexOf(" ")));
			}
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPrevdate()))
			{
				
				CommonMessage.debugMsg(bdmTlWhywhymst.getWwmsPrevdate());
				if(bdmTlWhywhymst.getWwmsPrevdate().indexOf(":")>0)
				{
					bdmTlWhywhymst.setWwmsPrevdate(bdmTlWhywhymst.getWwmsPrevdate().substring(0, bdmTlWhywhymst.getWwmsPrevdate().indexOf(" ")));
					if(bdmTlWhywhymst.getWwmsPrevdate().equals(Constants.pgPassNullDate))
						bdmTlWhywhymst.setWwmsPrevdate("");
				}
				else
					bdmTlWhywhymst.setWwmsPrevdate(bdmTlWhywhymst.getWwmsPrevdate());
			}
			
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPreveffectiveness()))
			{
				bdmTlWhywhymst.setWwmsPreveffectiveness("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsRootcause()))
			{
				bdmTlWhywhymst.setWwmsRootcause("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPokayoke()))
			{
				bdmTlWhywhymst.setWwmsPokayoke("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsMachineid()))
			{
				bdmTlWhywhymst.setWwmsMachineid("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsAssemblyid()))
			{
				bdmTlWhywhymst.setWwmsAssemblyid("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsAccidentphen()))
			{
				bdmTlWhywhymst.setWwmsAccidentphen("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPrevno()))
			{
				bdmTlWhywhymst.setWwmsPrevno("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPrevperson()))
			{
				bdmTlWhywhymst.setWwmsPrevperson("");
			}
			
			request.setAttribute("kznWhyWhyMst",bdmTlWhywhymst);
			request.setAttribute("yyFormBean",yyFormBean);
			CommonMessage.debugMsg("before call jsp"+"................................");
			
			String counterMeasureFlag = yyService.checkCounterMsr();
			if(UIUtils.isValidKeyId(counterMeasureFlag))
				request.setAttribute("counterMeasureFlag",counterMeasureFlag);
			request.setAttribute("filterStr",filterStr);
			request.setAttribute("mode",yymode);
			request.setAttribute("refdoctype",refdoctype);
			CommonMessage.debugMsg("refdoctype"+refdoctype);
			UIUtils.forwardRequest(request, response, "/pages/WhyWhyAnalysisMainFormGrid.jsp");
		}
		else if( action.equals("yy_getCol.why"))
		{
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("yyServletWmsKey");
			httpSession.removeAttribute("yyServletfinalAction");
			httpSession.setAttribute("yyServletWmsKey", request.getParameter("wwmsKey"));
			httpSession.setAttribute("yyServletfinalAction", request.getParameter("finalAction"));
			CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcolModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcolModel"));
		}
		else if(action.equals("yy_getData.why"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(false);
				String wmsKey = (String) httpSession.getAttribute("yyServletWmsKey");
				String finalAction = (String) httpSession.getAttribute("yyServletfinalAction");
				CommonMessage.debugMsg("wmsKey "+wmsKey);
				CommonMessage.debugMsg("finalAction "+finalAction);
			
				List<String []> yyList = new ArrayList();
				if(UIUtils.isValidKeyId(wmsKey))
				{ 
					CommonMessage.debugMsg("Execute Function ");
					yyList  = breakDownService.getYY(wmsKey);
				}
				else
				{
					String[] yyDatas = {finalAction,"{}","{}","{}","{}"};
					yyList.add(yyDatas);
				}
				CommonMessage.debugMsg("yylist roopa STTART:" + yyList);
				JSONObject yyData = UIUtils.convertToJqGridTableObject(yyList,request,0,0);	  	
				CommonMessage.debugMsg("yylist roopa:"+yyData);
  			 	out.println(yyData);
			}catch(Exception e){
					CommonMessage.debugMsg(e.getMessage());
			}
		}
	
		else if( action.equals("yykaizen_getCol.why"))
		{
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("yyKaizenServletWmsKey");
			httpSession.removeAttribute("yyKaizenServletfinalAction");
			httpSession.setAttribute("yyKaizenServletWmsKey", request.getParameter("wwmsKey"));
			httpSession.setAttribute("yyKaizenServletfinalAction", request.getParameter("finalAction"));
			CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYKaizencolModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYKaizencolModel"));
		}
		else if(action.equals("yykaizen_getData.why"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(false);
				String wmsKey = (String) httpSession.getAttribute("yyKaizenServletWmsKey");
				String finalAction = (String) httpSession.getAttribute("yyKaizenServletfinalAction");
				
				List<String []> yyList = new ArrayList();
				if(UIUtils.isValidKeyId(wmsKey))
				{
					yyList  = breakDownService.getYY(wmsKey);
				}
				else
				{
					String[] yyDatas = {finalAction,"{}","{}","{}","{}"};
					yyList.add(yyDatas);
				}
			
				JSONObject yyData = UIUtils.convertToJqGridTableObject(yyList,request,0,0);	  	
				CommonMessage.debugMsg(""+yyData);
  			 	out.println(yyData);
			}catch(Exception e){
					CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("yyShe_getCol.why"))
		{
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("yySheServletWmsKey");
			httpSession.removeAttribute("yySheServletPhen");
			httpSession.setAttribute("yySheServletWmsKey", request.getParameter("wwmsKey"));
			httpSession.setAttribute("yySheServletPhen", request.getParameter("phenomena"));
			CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYSHEcolModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYSHEcolModel"));
		}
		else if(action.equals("yyShe_getData.why"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(false);
				String wmsKey = (String) httpSession.getAttribute("yySheServletWmsKey");
				String phen = (String) httpSession.getAttribute("yySheServletPhen");
				CommonMessage.debugMsg("wmsKey "+wmsKey);
				CommonMessage.debugMsg("phen "+phen);
			
				List<String []> yyList = new ArrayList();
				if(UIUtils.isValidKeyId(wmsKey))
				{ 
					CommonMessage.debugMsg("Execute Function ");
					yyList  = breakDownService.getYY(wmsKey);
				}
				else
				{
					String[] yyDatas = {phen,"{}","{}","{}","{}"};
					yyList.add(yyDatas);
				}
			
				JSONObject yyData = UIUtils.convertToJqGridTableObject(yyList,request,0,0);	  	
				CommonMessage.debugMsg(""+yyData);
  			 	out.println(yyData);
			}catch(Exception e){
					CommonMessage.debugMsg(e.getMessage());
			}
		}
	
	
		else if( action.equals("yyCC_getCol.why"))
		{
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("yyServletWmsKey");
			httpSession.removeAttribute("yyServletfinalAction");
			httpSession.setAttribute("yyServletWmsKey", request.getParameter("wwmsKey"));
			httpSession.setAttribute("yyServletfinalAction", request.getParameter("finalAction"));
			CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcccolModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcccolModel"));
		}
		else if( action.equals("rootcause_getCol.why") )
		{
			//httpSession.setAttribute("wwNo", request.getParameter("wwNo"));				
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel"));
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel"));
		
		}
		else if( action.equals("rootcause_getData.why") )
		{
			try
			{	
				CommonMessage.debugMsg("ROOTCAUSE_getdata");
			
				String openMode = request.getParameter("openMode");
				PrintWriter out = response.getWriter();
				List<String []> rootCauseList  = yyService.getRootCause(openMode);
				JSONObject rcData = UIUtils.convertToJqGridTableObject(rootCauseList,request,0,0);	  
				CommonMessage.debugMsg(""+rcData);
  			 	out.println(rcData);
			}catch(Exception e)
			{
					CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("rootcauseBD_getCol.why") )
		{
			//httpSession.setAttribute("wwNo", request.getParameter("wwNo"));				
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel2"));
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel2"));
		
		}
		else if( action.equals("rootcauseBD_getData.why") )
		{
			try
			{	
				CommonMessage.debugMsg("ROOTCAUSE_getdata");
				String openMode = request.getParameter("openMode");
				PrintWriter out = response.getWriter();
				List<String []> rootCauseList  = yyService.getRootCause(openMode);   ///////////////////
				JSONObject rcData = UIUtils.convertToJqGridTableObject(rootCauseList,request,0,0);	  
				CommonMessage.debugMsg(""+rcData);
  			 	out.println(rcData);
			}catch(Exception e)
			{
					CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("pillar_getCol.why") )
		{
			//httpSession.setAttribute("wwNo", request.getParameter("wwNo"));				
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarColModel"));
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarColModel"));
		
		}
		else if( action.equals("pillar_getData.why") )
		{
			try
			{	
				CommonMessage.debugMsg("PILLAR");
				String yyID = request.getParameter("wwmsKey");
				String pillarFlag = request.getParameter("yyOpenFrom");
				CommonMessage.debugMsg("PILLAR : "+yyID);
				PrintWriter out = response.getWriter();
				List<String []> rootCauseList = new ArrayList();
				if(UIUtils.isValidKeyId(yyID))
				{
					rootCauseList  = yyService.getPillar(yyID,pillarFlag);
					//rootCauseList.get(0)[0]
					rootCauseList = fillPillarDatas(rootCauseList);
				}
				else
				{
					rootCauseList = fillPillar(rootCauseList,pillarFlag);
				}
				JSONObject rcData = UIUtils.convertToJqGridTableObject(rootCauseList,request,0,0);	  
				CommonMessage.debugMsg(""+rcData);
  			 	out.println(rcData);
			}catch(Exception e)
			{
					CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("pillarBD_getCol.why") )
		{
			//httpSession.setAttribute("wwNo", request.getParameter("wwNo"));				
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarBDColModel"));
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarBDColModel"));
		}
		else if( action.equals("pillarBD_getData.why") )
		{
			try
			{	
				CommonMessage.debugMsg("PILLAR");
				String yyID = request.getParameter("wwmsKey");
				String pillarFlag = request.getParameter("yyOpenFrom");
				CommonMessage.debugMsg("PILLAR : "+yyID);
				PrintWriter out = response.getWriter();
				List<String []> rootCauseList = new ArrayList();
				if(UIUtils.isValidKeyId(yyID))
				{
					rootCauseList  = yyService.getPillar(yyID,pillarFlag);
					//rootCauseList.get(0)[0]
					rootCauseList = fillPillarDatas(rootCauseList);
				}
				else
				{
					rootCauseList = fillPillar(rootCauseList,pillarFlag);
				}
				JSONObject rcData = UIUtils.convertToJqGridTableObject(rootCauseList,request,0,0);	  
				CommonMessage.debugMsg(""+rcData);
  			 	out.println(rcData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("whywhyanalysismodify_delete.why"))     //delete
		{	
			CommonMessage.debugMsg("delete");
			PrintWriter out = response.getWriter();
			String yyToDel = request.getParameter("keyid");
			String yyRefdoc = request.getParameter("refdocid");

			CommonMessage.debugMsg("delete1=="+yyToDel);
		//String  rowId = request.getParameter("rowId");
			try{
			if(UIUtils.isValidKeyId(yyToDel))
			{
				CommonMessage.debugMsg("delete2");
				BdmTlWhywhymst bdmTlWhywhymst =  new BdmTlWhywhymst();
				bdmTlWhywhymst.setWwmsKeyid(yyToDel);
				bdmTlWhywhymst.setWwmsRefdocno(yyRefdoc);
				//BdmTlWhywhydtl bdmTlWhywhydtl =  new BdmTlWhywhydtl();
				
				//BdmTlWhywhydtl bdmTlWhywhydtl = yyService.deleteYYDtl(yyToDel);
			bdmTlWhywhymst = yyService.delete(bdmTlWhywhymst);
				JSONObject returnData = new JSONObject();
				returnData.put("msg", "Deleted Successfully");
				//returnData.put("rowId", rowId);
				
				out.print(returnData.toString());
				//bdmTlWhywhydtl = null;
				bdmTlWhywhymst = null;
			}
			}
			catch(BusinessApplicationExceptions e)
			{
				net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"WhyValidation");				
				out.print(errMessage.toString());
				
			}
		}
		else if( action.equals("whywhyanalysismodify_save.why"))
		{	
			CommonMessage.debugMsg("Inside YY Save");
			YYFormBean yyFormBean = new YYFormBean();
			saveYY(request,response,yyFormBean);	
			CommonMessage.debugMsg("Inside xx Save");
		}
		else if(action.equals("whywhyAnalysisMailSend.why")){
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
  	
	    	
			String whywhyNo=request.getParameter("whywhyId");
			String spentTime=request.getParameter("spentTime");
			String problem=request.getParameter("problem");
			String area=request.getParameter("area");
			System.out.print("IN side the Send Mail");
			
			
	    	String empmKeyId=user.getUsrm_ccno();
try{
	    //	sendApprvalMail(request, response,  whywhyNo,problem, spentTime,area);
}catch(Exception e){
	e.printStackTrace();
	
}
		}
		else if( action.equals("doneByLink_save.why"))
		{	
			CommonMessage.debugMsg("Inside doneByLink_save.why");
			YYFormBean yyFormBean = new YYFormBean();
			saveDonebyLink(request,response,yyFormBean);
			
		}
		
		else if(action.equals("YYDoneBy_delete.why")){
			deleteYYDoneBy(request,response);
		}
		
		else if (action.equals("whywhyEffectiveness_save.why"))
		{
			saveWhyWhyEffectiveness(request,response);
		}
		else if( action.equals("whywhyAna_save.why"))    //TTTTTTTTTTTTT
		{	
			CommonMessage.debugMsg("Inside YY Save");
			YYFormBean yyFormBean = new YYFormBean();
			//savewhy(request,response,yyFormBean);	
		}
		else if( action.equals("WhyWhy_delete.why"))
		{	
			PrintWriter out = response.getWriter();
			String yyToDel = request.getParameter("keyid");
			CommonMessage.debugMsg("delete1=="+yyToDel);
			String  rowId = request.getParameter("rowId");
			if(UIUtils.isValidKeyId(yyToDel))
			{
				BdmTlWhywhydtl bdmTlWhywhydtl =  new BdmTlWhywhydtl();
				bdmTlWhywhydtl = yyService.deleteYYDtl(yyToDel);
				JSONObject returnData = new JSONObject();
				returnData.put("msg", "Deleted Successfully");
				returnData.put("formClear",true);
				returnData.put("rowId", rowId);
				out.print(returnData.toString());
			}
		}
		
		else if( action.equals("functionalLoc.why"))
		{
			HttpSession httpSession = request.getSession(false);
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbwwmsFactoryid");
			functLocFieldNameBean.setSection("cmbwwmsSectionid");
			functLocFieldNameBean.setCell("cmbwwmsCellid");
			functLocFieldNameBean.setMachine("cmbwwmsMachineid");
			  functLocFieldNameBean.setFunctionalLocId("txtWwmsFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);    //JH
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
			
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		}
		
		else if( action.equals("combo_yy.why"))
		{
			try {
				ComboFilter comboFilter = new ComboFilter();
					CommonMessage.debugMsg("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
					comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox>  yy = whywhyService.getYY("",comboFilter);
				UIUtils.writeComboBox(response, yy,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (action.equals("whywhyanalysisgrid_getCol.why")) {
	try {
		
		
				PrintWriter out = response.getWriter();
				String masdetkeyid=request.getParameter("masterkeyid");
				//String detkeyid=request.getParameter("detailkeyid");
			
				//List<String[]> analysis = whywhyService.getanalysis(masdetkeyid);
				List<String[]> analysis = null ;
				JSONObject jsonObject = getTableModelAnalyis(analysis);
		       out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (action.equals("whywhyanalysisgrid_getData.why")) {     //Analysis
			try {
				PrintWriter out = response.getWriter();
				String masdetkeyid=request.getParameter("masterkeyid");
				//String detkeyid=request.getParameter("detailkeyid");
				List<String[]> analysis = whywhyService.getanalysis(masdetkeyid);
				JSONObject analysisJson= UIUtils.convertToJqGridTableObject(analysis, request, 1, 0);
				out.println(analysisJson);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}
		else if (action.equals("whywhyanalysismaingrid_getCol.why")) {
			
			
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			CommonMessage.debugMsg(" In side the get Col 01");
		httpSession.removeAttribute("whywhyanalysisCommonFilter");
		String recDocId = request.getParameter("refdocid");	
		String mode=request.getParameter("mode");
		CommonFilter commonFilter = populateCommonFilter(request,"whywhyanalysisCommonFilter",true);
		CommonMessage.debugMsg(" In side the getAfter populate Col 02");
		commonFilter.setWoModeForGrid(mode);
		response.setContentType("text/html");
		if(UIUtils.isValidKeyId(recDocId))
			commonFilter.setRefdocid(recDocId);
		commonFilter.setIsGetCol("Y");
		List<String[]> AbnDataList  = yyService.getAllWhywhy(commonFilter);
		for (String[] row : AbnDataList) {
		    CommonMessage.debugMsg("datamlist"+Arrays.toString(row));
		}
		CommonMessage.debugMsg(" In side the get After Calling service Col 03");
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();
		
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableButton(true);
		
		gridColModel.setHeaderNum(1);
		
		String [] colHeader = AbnDataList.get(1);			
		String [] colHeaderCond = AbnDataList.get(0);
		
		CommonMessage.debugMsg("Col Header "+Arrays.toString(colHeader));
		CommonMessage.debugMsg("Col Header colHeaderCond"+Arrays.toString(colHeaderCond));
		
		
		//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
		List<String[]> headers = new ArrayList<String[]>();
		//headers.add(colHeaderCond);
		headers.add(colHeader);
		
		JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		jsonObject.put("tableHeight", "83%%");
		jsonObject.put("tableWidth", "109%%");
		httpSession.setAttribute("ColModel", jsonObject);
		httpSession.setAttribute("whywhyanalysisCommonFilter", commonFilter);
		out.println(jsonObject);
		}
		/*
		 * else if (action.equals("whywhyanalysismaingrid_getData.why")) { try {
		 * 
		 * HttpSession httpSession = request.getSession(false); String recDocId =
		 * request.getParameter("refdocid"); String mode=request.getParameter("mode");
		 * CommonMessage.debugMsg(" In side the get Data before populate  02"); CommonFilter
		 * commonFilter =
		 * populateCommonFilter(request,"whywhyanalysisCommonFilter",false);
		 * CommonMessage.debugMsg(" In side the get Data Afer populate  02");
		 * 
		 * commonFilter.setWoModeForGrid(mode); if(UIUtils.isValidKeyId(recDocId))
		 * commonFilter.setRefdocid(recDocId);
		 * CommonMessage.debugMsg(" In side the get Data before calling   01");
		 * 
		 * List<String []> WhyReportList = yyService.getAllWhywhy(commonFilter);
		 * PrintWriter out = response.getWriter();
		 * CommonMessage.debugMsg(" In side the get Data Afer callling   02");
		 * 
		 * JSONObject Whymaster = UIUtils.convertToJqGridTableObject(WhyReportList,
		 * request, 2, 0,commonFilter.getTotalRecordCnt());
		 * httpSession.removeAttribute("whywhyanalysisCommonFilter");
		 * out.println(Whymaster ); CommonMessage.debugMsg(Whymaster); } catch (Exception e)
		 * { CommonMessage.debugMsg(e.getMessage()); }
		 * 
		 * }
		 */
		
		else if (action.equals("whywhyanalysismaingrid_getData.why")) {
		    try {
		        HttpSession httpSession = request.getSession(false);
		        String recDocId = request.getParameter("refdocid");
		        String mode = request.getParameter("mode");

		        CommonFilter commonFilter = populateCommonFilter(request, "whywhyanalysisCommonFilter", false);
		        commonFilter.setWoModeForGrid(mode);

		        if (UIUtils.isValidKeyId(recDocId))
		            commonFilter.setRefdocid(recDocId);

		        String formModeForExcel = commonFilter.getWoModeForGrid();
		        if (formModeForExcel == null) formModeForExcel = "";
		        httpSession.setAttribute("whywhyAnalysisFormMode", formModeForExcel.trim());
		        CommonMessage.debugMsg("Stored FORMMODE in session: [" + formModeForExcel.trim() + "]");
		        
		        commonFilter.setIsGetCol("N");

		        List<String[]> WhyReportList = yyService.getAllWhywhy(commonFilter);
		        PrintWriter out = response.getWriter();

		        JSONObject Whymaster = UIUtils.convertToJqGridTableObject(
		                WhyReportList, request, 2, 0, commonFilter.getTotalRecordCnt());

		        
		        out.println(Whymaster);
		        CommonMessage.debugMsg(Whymaster);

		    } catch (Exception e) {
		        CommonMessage.debugMsg(e.getMessage());
		    }
		}
		
else if (action.equals("whywhyanalysisageingrpt_getCol.why")) {
			
			
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			CommonMessage.debugMsg(" In side the get Col 01");
		httpSession.removeAttribute("whywhyanalysisAgeingCommonFilter");
		String recDocId = request.getParameter("refdocid");	
		String mode=request.getParameter("mode");
		CommonFilter commonFilter = populateCommonFilter(request,"whywhyanalysisAgeingCommonFilter",true);
		CommonMessage.debugMsg(" In side the getAfter populate Col 02");
		commonFilter.setWoModeForGrid(mode);
		response.setContentType("text/html");
		
		commonFilter.setIsGetCol("Y");
		List<String[]> AbnDataList  = yyService.getWhywhyAge(commonFilter);
//		for (String[] row : AbnDataList) {
//		    CommonMessage.debugMsg("datamlist"+Arrays.toString(row));
//		}
		CommonMessage.debugMsg(" In side the get After Calling service Col 03");
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();
		
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableButton(true);
		
		gridColModel.setHeaderNum(1);
		
		String [] colHeader = AbnDataList.get(1);			
		String [] colHeaderCond = AbnDataList.get(0);
		
		CommonMessage.debugMsg("Col Header "+Arrays.toString(colHeader));
		CommonMessage.debugMsg("Col Header colHeaderCond"+Arrays.toString(colHeaderCond));
		
		
		//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
		List<String[]> headers = new ArrayList<String[]>();
		//headers.add(colHeaderCond);
		headers.add(colHeader);
		
		JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		jsonObject.put("tableHeight", "83%%");
		jsonObject.put("tableWidth", "109%%");
		httpSession.setAttribute("ColModel", jsonObject);
		httpSession.setAttribute("whywhyanalysisAgeingCommonFilter", commonFilter);
		out.println(jsonObject);
		}
		
else if (action.equals("whywhyanalysisageingrpt_getData.why")) {
    try {
        HttpSession httpSession = request.getSession(false);
        String recDocId = request.getParameter("refdocid");
        String mode = request.getParameter("mode");

        CommonFilter commonFilter = populateCommonFilter(request, "whywhyanalysisAgeingCommonFilter", false);
        commonFilter.setWoModeForGrid(mode);

       
        String formModeForExcel = commonFilter.getWoModeForGrid();
        if (formModeForExcel == null) formModeForExcel = "";
        httpSession.setAttribute("whywhyAnalysisFormMode", formModeForExcel.trim());
        CommonMessage.debugMsg("Stored FORMMODE in session: [" + formModeForExcel.trim() + "]");
        
        commonFilter.setIsGetCol("N");

        List<String[]> WhyReportList = yyService.getWhywhyAge(commonFilter);
        PrintWriter out = response.getWriter();

        JSONObject Whymaster = UIUtils.convertToJqGridTableObject(
                WhyReportList, request, 2, 0, commonFilter.getTotalRecordCnt());

        
        out.println(Whymaster);
        CommonMessage.debugMsg(Whymaster);

    } catch (Exception e) {
        CommonMessage.debugMsg(e.getMessage());
    }
}
		
		
		else if (action.equals("whywhyproposedgrid_getCol.why")) {       //counter measure
			try {
				CommonMessage.debugMsg(" In side the Get Col Proposed 001");
				PrintWriter out = response.getWriter();
				String yyid = request.getParameter("masterkeyid");
				CommonFilter commonFilter =  populateCommonFilter1(request,"whywhyanalysisCounterMeasure",true);
				commonFilter.setYyNo(yyid);
				CommonMessage.debugMsg(" In side the Get Col Proposed 1");
				commonFilter.setIsGetCol("Y");
				List<String[]> Proposed = whywhyService.getProposed(commonFilter);
				CommonMessage.debugMsg(" In side the Get Col  Proposed 2");
				JSONObject jsonObject = getTableModelProposed(Proposed);
			    out.println(jsonObject);
			    CommonMessage.debugMsg(" In side the Get Col  Proposed 3");
			} catch (Exception e) {
				CommonMessage.debugMsg(" In side the Get Col  Proposed 4");
				e.printStackTrace();
			}
		} 
		else if (action.equals("whywhyproposedgrid_getData.why")) {
			try {
				PrintWriter out = response.getWriter();
				String yyid = request.getParameter("masterkeyid");
				CommonMessage.debugMsg(" In side the Get Data  Proposed 1");
				CommonFilter commonFilter =  populateCommonFilter1(request,"whywhyanalysisCounterMeasure",false);
				commonFilter.setYyNo(yyid);
				CommonMessage.debugMsg(" In side the Get Data  Proposed 2");
				
				commonFilter.setIsGetCol("N");
				List<String[]> Proposed = whywhyService.getProposed(commonFilter);
				CommonMessage.debugMsg(" In side the Get Data  Proposed 3");

				JSONObject ProposedJson= UIUtils.convertToJqGridTableObject(Proposed, request, 2, 0);
				out.println(ProposedJson);
				CommonMessage.debugMsg(" In side the Get Data  Proposed 4");

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}
		else if( action.equals("whywhyReport_getExcel.why"))
		{	
			CommonMessage.debugMsg("1111111111111111list      :::: 111");
			HttpSession httpSession = request.getSession(false);
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("whywhyCommonFilter");
			
			List<String[]> whywhyQueryList  =   whywhyService.getAllwhywhyStd(commonFilter);
			String fileName= "whywhyRpt.xls";
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.whywhyRpt", "whywhyRpt");
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			//excelUtils.writeToExcel(request, response, fileName, whywhyQueryList,(short)0,(short)1,(short)0);
						
		} 
		else if( action.equals("whywhyReport_view.why") )/*Created By Siddharth.A*/ 
		{
			try{
				String rowId = request.getParameter("rowId");
			 	String format = ExcelUtils.getFormat(request);
				String path = UIUtils.getExcelTemplatePath(request);  	
				String imagePath = UIUtils.getImagePath(request);
				format = "xlsx";
				Workbook wb = whywhyService.getAllwhywhyRptExl(rowId,format,path,imagePath);
				ExcelUtils.writeToResponse(response, wb, "WhyWhyReport",format );
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
		else if( action.equals("WhyWhyTrainingList_input.why")){
			String flid = request.getParameter("flid");
			request.setAttribute("flid", flid);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/WhyWhyTrainingList.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("WhyWhyTrainingList_view.why")){
			
		}
		else if( action.equals("WhyWhyTrainingList_getCol.why") )
		{	
			CommonMessage.debugMsg("WhyWhyTrainingList_getCol.why");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();			
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "ProgramColModel"));
						
		}
		else if( action.equals("WhyWhyTrainingList_getData.why") )
		{
			try {
				CommonMessage.debugMsg("WhyWhyTrainingList_getData.why");
				PrintWriter out = response.getWriter();
				{					
					String rows = request.getParameter("rows");
		        	String page = request.getParameter("page");		           
	       		    String start = "1";
	       		    String end = "100";
	       		    if(page.equals("1"))
	       		    {       		    	
	       		    }
	       		    else
	       		    {
	       		     int rowStart = (Integer.parseInt(rows)*Integer.parseInt(page))-99;
	        		 int rowEnd = Integer.parseInt(rows)*Integer.parseInt(page);
	        		 start = Integer.toString(rowStart);
	        		 end = Integer.toString(rowEnd);
	       		    }
					//CommonMessage.debugMsg("FORMFLD : "+elemFld);
					String totalCount = "5";//entTlTrainingareaService.getTotalCount(entTlTrainingarea );
					int totalRows = 200;
					
					List<String []> childForParent = yyService.getProgramList(start, end);
					if(UIUtils.isValidKeyId(totalCount))
						totalRows = Integer.parseInt(totalCount);
					
					net.sf.json.JSONObject childForParentData = UIUtils.convertToJqGridTableObject(childForParent,request,0,0,totalRows);
					CommonMessage.debugMsg("childForParentData:"+childForParentData);
					out.println(childForParentData);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( action.equals("WhyWhyTraining_input.why")){
			RequestDispatcher rd = request.getRequestDispatcher("/pages/WhyWhyTrainingPopup.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("whywhyQtyReport_view.why") )/*Created By Siddharth.A*/ 
		{
			try{
				String rowId = request.getParameter("rowId");
			 	String format = ExcelUtils.getFormat(request);
				String path = UIUtils.getExcelTemplatePath(request);  	
				String imagePath = UIUtils.getImagePath(request);
				format = "xlsx";
				Workbook wb = whywhyService.getAllwhywhyQtyRptExl(rowId,format,path,imagePath);
				
	 
				ExcelUtils.writeToResponse(response, wb, "WhyWhyReport",format );
				}
			
				catch(Exception e)
				{
				e.printStackTrace();
					CommonMessage.debugMsg("err:"+e.getMessage());
					PrintWriter out = response.getWriter();
					JSONObject err = new JSONObject();				
					//err.put("exception",true);				
					err.put("message" ,"Data Not Found" );
					out.print(err.toString());
				}
				
				
		}else if( action.equals("filterXmlwhywhyRptGenDrill_view.why")){
			response.setContentType("xml");		
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyAnalysis.xml");
		 }else if(action.startsWith("whywhyRptGenDrill") ) 
		{
			whywhyIdentifiedDrillDownReport( request, response );
		}
			
			
	}
	private void saveWhyWhyEffectiveness(HttpServletRequest request,HttpServletResponse response) throws Exception {
			
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
    	CommonMessage.debugMsg("yyService"+yyService);
    	
    	if( httpSession != null && user != null)
    	{	
    		BdmTlYyeffectivemst existBdmTlYyeffectivemst = (BdmTlYyeffectivemst)httpSession.getAttribute("BdmTlWhywhyMst"); 
    		 
    		BdmTlYyeffectivemst newBdmTlYyeffectivemst = new BdmTlYyeffectivemst();     		
    		newBdmTlYyeffectivemst.setYyefCreatedby(user.getUsrm_ccno());    	  		
    	
    		newBdmTlYyeffectivemst =(BdmTlYyeffectivemst)UIUtils.setBeanProperties((Object)newBdmTlYyeffectivemst,request);
    		
    /*		if(request.getParameter("gridData")== null || request.getParameter("gridData").equals(""))
    		{
	    		CommonMessage.debugMsg("YY Grid : "+request.getParameter("YYAnalysis"));
    		}
    		else
    		{*/
    			CommonMessage.debugMsg("YY Grid = "+request.getParameter("gridData"));
	    		String yyGrid = request.getParameter("gridData");	
	    		
		    	JSONArray jsonArray = JSONArray.fromString(yyGrid);		  
		    	BdmTlYyeffectivedtl newBdmTlYyeffectivedtl = new BdmTlYyeffectivedtl();
		    	newBdmTlYyeffectivedtl.setYyedCreatedby(user.getUsrm_ccno());	 
		    	List<BdmTlYyeffectivedtl> yyDetail = (List<BdmTlYyeffectivedtl>) UIUtils.convertJSONArrToList(newBdmTlYyeffectivedtl, jsonArray);
		    	List<BdmTlYyeffectivemst> yyMst = (List<BdmTlYyeffectivemst>) UIUtils.convertJSONArrToList(newBdmTlYyeffectivemst, jsonArray);
		    	CommonMessage.debugMsg(" SYstem oup "+newBdmTlYyeffectivedtl.getYyedCountermesdate());
		    	if( newBdmTlYyeffectivedtl != null) {
		    		newBdmTlYyeffectivemst.setBdmTlYyeffectivedtl(yyDetail);
		    		newBdmTlYyeffectivemst.setBdmTlYyeffectivemst(yyMst);
		    	}
    		//}
    	
	    	       CommonMessage.debugMsg(" Checking value save :: "+newBdmTlYyeffectivemst.getYyefKeyid());
	    	
    		try{
    			if(!UIUtils.isValidKeyId (newBdmTlYyeffectivemst.getYyefKeyid() )  )
				{	
					CommonMessage.debugMsg("create");
					existBdmTlYyeffectivemst =	yyService.yyEffectivenessCreate(newBdmTlYyeffectivemst,existBdmTlYyeffectivemst);
				}	
				else
				{
					CommonMessage.debugMsg("update");
					existBdmTlYyeffectivemst =	yyService.yyEffectivenessUpdate(newBdmTlYyeffectivemst,existBdmTlYyeffectivemst);						
				}
				
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("cmbWwmsKeyid",existBdmTlYyeffectivemst.getYyefKeyid());
				String formType = (String) httpSession.getAttribute("WHYWHYFORMTYPE");
				if(UIUtils.isValidKeyId(formType))
					persistentData.put("txtFormType",formType);
				//existBdmTlWhywhymst.getWwmsFormtype()
				JSONObject forwardData = new JSONObject();
				
			
				CommonMessage.debugMsg(" Form Mode");
				//httpSession.setAttribute("wwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
				successData.put("keyId", existBdmTlYyeffectivemst.getYyefKeyid());
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);		
				CommonMessage.debugMsg(" returnData.toString() "+returnData.toString());
				out.print(returnData.toString());
			
    		}
    		catch(BusinessApplicationExceptions e)
			{
				//net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"WhyValidation");				
				//out.print(errMessage.toString());
			}
			catch(ValidationExceptions e)
			{				
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"WhyValidation");
				errMessage.put("tpmException", "Data Not Saved");
				out.print(errMessage.toString());			
			}
			
	    	  
    	}
		
	}
	private void deleteYYProbAttBy(HttpServletRequest request,HttpServletResponse response) throws Exception,BusinessApplicationExceptions {
    	PrintWriter out = response.getWriter();
    	 String keyId = request.getParameter("keyid");
		try{
		 String delSuc = yyService.deleteYYProbAttBy(keyId);
		 JSONObject json = new JSONObject();
		 json.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			 out.print(json.toString());
		}catch(BusinessApplicationExceptions e)
			{
				
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("msg"," Record Can't be Deleted Reference Found");
				out.print(returnData.toString());
			}
		
	}
 
private void saveprobAttbyLink(HttpServletRequest request,HttpServletResponse response, YYFormBean yyFormBean) throws Exception {
	
	HttpSession httpSession = request.getSession(false);
	ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);

	CommonMessage.debugMsg("yyService"+yyService);
	
	if( httpSession != null && user != null)
	{	
		
		BdmTlYyproblemattbymst existBdmTlYyproblemattbymst = (BdmTlYyproblemattbymst)httpSession.getAttribute("BdmTlWhywhyMst"); 
		 
		BdmTlYyproblemattbymst newBdmTlYyproblemattbymst = new BdmTlYyproblemattbymst();     		
		newBdmTlYyproblemattbymst =(BdmTlYyproblemattbymst)UIUtils.setBeanProperties((Object)newBdmTlYyproblemattbymst,request);
		newBdmTlYyproblemattbymst.setWwpaCreatedby(user.getUsrm_ccno());    	  		
		
		String yyKeyid = request.getParameter("yyKeyid");
		String yypab = request.getParameter("yypab");
    		   
		if (UIUtils.isValidKeyId(yypab))
			newBdmTlYyproblemattbymst.setWwpaEmpmKeyid(yypab);
		if (UIUtils.isValidKeyId(yyKeyid))
			newBdmTlYyproblemattbymst.setWwpaWwmsKeyid(yyKeyid);
		
		try{
			if(!UIUtils.isValidKeyId (newBdmTlYyproblemattbymst.getWwpaKeyid() )  )
			{	
				CommonMessage.debugMsg("create");
				existBdmTlYyproblemattbymst =	yyService.yyProbAttCreate(newBdmTlYyproblemattbymst,existBdmTlYyproblemattbymst);
			}	
			
			JSONObject persistentData = new JSONObject(); 
			persistentData.put("cmbWwmsKeyid",existBdmTlYyproblemattbymst.getWwpaKeyid());
			//existBdmTlWhywhymst.getWwmsFormtype()
			JSONObject forwardData = new JSONObject();
			
		
			CommonMessage.debugMsg(" Form Mode");
			//httpSession.setAttribute("wwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
			successData.put("keyId", existBdmTlYyproblemattbymst.getWwpaKeyid());
			JSONObject returnData = new JSONObject();
			returnData.put("formClear",false);
			returnData.put("forwardData",forwardData);
			returnData.put("persistentData", persistentData);
			returnData.put("successData", successData);		
			CommonMessage.debugMsg(" returnData.toString() "+returnData.toString());
			out.print(returnData.toString());
		
    }
	catch(ValidationExceptions e)
	{
		e.printStackTrace();
		net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "WhyValidation");
		out.print(errMessage.toString());
	
	}
	catch(BusinessApplicationExceptions e)
	{   
		
		CommonMessage.debugMsg("Error Servler e -"+e.toString());
		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "WhyValidation");
		out.print(errMessage.toString());
		CommonMessage.debugMsg(" e " + errMessage );
	}
	catch(Exception e)
	{				
	
		 e.printStackTrace();
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not Saved");
		out.print(err.toString());			
	}
	}
}


	
private JSONObject getProbAttbyTableModel(List<String[]> yyDonebyList) {
	// TODO Auto-generated method stub
	JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
	
	//String[] colHeader = yyDonebyList.get(0);
	String[] colHeader = {"sno", "keyid", "wwmskeyid", "Employee", "Delete"};
	 
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.setRowNumbers(false);
	jqGridTableModel.setTableHeight(290);  
	jqGridTableModel.setTableWidth(600);
	jqGridTableModel.setTableButton(false);
	jqGridTableModel.setRowNumbers(true);
	for (int i = 0; i < colHeader.length; i++) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setName(colHeader[i].replaceAll(" ", "")); 
		jqGridColModel.setWidth(200);
		jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);	 
		
		if (i <=2) {
			jqGridColModel.setHidden(true);
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");
		}
		if(i==colHeader.length-1){
			jqGridColModel.setFormatter("BtnFormatterDelete");
			jqGridColModel.setWidth(40);
			jqGridColModel.setAlign("center");
		} 
		jqGridTableModel.getColModel().add(jqGridColModel);
	}

	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "30%%");
	tableModel.set("tableWidth", "26%%");
	return tableModel;

}

	 private void deleteYYDoneBy(HttpServletRequest request,HttpServletResponse response) throws Exception,BusinessApplicationExceptions {
	    	PrintWriter out = response.getWriter();
	    	 String keyId = request.getParameter("keyid");
			try{
			 String delSuc = yyService.deleteYYDoneBy(keyId);
			 JSONObject json = new JSONObject();
			 json.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				 out.print(json.toString());
			}catch(BusinessApplicationExceptions e)
				{
					
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
					JSONObject returnData = new JSONObject();
					returnData.put("formClear",false);
					returnData.put("msg"," Record Can't be Deleted Reference Found");
					out.print(returnData.toString());
				}
			
		}
	
	private void saveDonebyLink(HttpServletRequest request,HttpServletResponse response, YYFormBean yyFormBean) throws Exception {
		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
    	CommonMessage.debugMsg("yyService"+yyService);
    	
    	if( httpSession != null && user != null)
    	{	
    		
    		BdmTlYydonebymst existBdmTlYydonebymst = (BdmTlYydonebymst)httpSession.getAttribute("BdmTlWhywhyMst"); 
    		 
    		BdmTlYydonebymst newBdmTlYydonebymst = new BdmTlYydonebymst();     		
    		newBdmTlYydonebymst =(BdmTlYydonebymst)UIUtils.setBeanProperties((Object)newBdmTlYydonebymst,request);
    		newBdmTlYydonebymst.setWwdbCreatedby(user.getUsrm_ccno());    	  		
    		
    		String yyKeyid = request.getParameter("yyKeyid");
    		String yyDonebyid = request.getParameter("yyDonebyid");
	    		   
    		if (UIUtils.isValidKeyId(yyDonebyid))
    			newBdmTlYydonebymst.setWwdbEmpmKeyid(yyDonebyid);
    		if (UIUtils.isValidKeyId(yyKeyid))
    			newBdmTlYydonebymst.setWwdbWwmsKeyid(yyKeyid);
    		
    		try{
    			if(!UIUtils.isValidKeyId (newBdmTlYydonebymst.getWwdbKeyid() )  )
				{	
					CommonMessage.debugMsg("create");
					existBdmTlYydonebymst =	yyService.yyDonebyCreate(newBdmTlYydonebymst,existBdmTlYydonebymst);
				}	
				
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("cmbWwmsKeyid",existBdmTlYydonebymst.getWwdbKeyid());
				//existBdmTlWhywhymst.getWwmsFormtype()
				JSONObject forwardData = new JSONObject();
				
			
				CommonMessage.debugMsg(" Form Mode");
				//httpSession.setAttribute("wwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
				successData.put("keyId", existBdmTlYydonebymst.getWwdbKeyid());
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);		
				CommonMessage.debugMsg(" returnData.toString() "+returnData.toString());
				out.print(returnData.toString());
			
	    }
		catch(ValidationExceptions e)
		{
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "WhyValidation");
			out.print(errMessage.toString());
		
		}
		catch(BusinessApplicationExceptions e)
		{   
			
			CommonMessage.debugMsg("Error Servler e -"+e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "WhyValidation");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage );
		}
		catch(Exception e)
		{				
		
			 e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());			
		}
    	}
    }
	
	private JSONObject getTableModel(List<String[]> whyReportList,
			CommonFilter commonFilter) {
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = whyReportList .get(0);	
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);	
		jqGridTableModel.setEnableFilter(true);
		
		
		for(int i =0; i <colHeader.length; i++)
		{			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
					
			jqGridColModel.setWidth( 100);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			CommonMessage.debugMsg("colheader  "+i+" : "+colHeader[i]);//if i==0
			if(i==5 ||i==2||i==3||i==4){
			 
				jqGridColModel.setWidth(160);
				
			
		}if(i==1)
		{
			jqGridColModel.setHidden(true);
		}
			
	
		
		jqGridTableModel.getColModel().add(jqGridColModel);
	}

	 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 return tableModel;
		
		
	
	}

	private void savewhy(HttpServletRequest request,
			HttpServletResponse response, YYFormBean yyFormBean) throws Exception {
		
		
         CommonMessage.debugMsg("nbj");
		
		CommonMessage.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null)
				{	
				
				
				BdmTlWhywhymst newBdmTlWhywhymst = new BdmTlWhywhymst();     	//model
				newBdmTlWhywhymst =(BdmTlWhywhymst)UIUtils.setBeanProperties((Object)newBdmTlWhywhymst,request);//master
					BdmTlWhywhymst existBdmTlWhywhymst = (BdmTlWhywhymst)httpSession.getAttribute("newBdmTlWhywhymst");
				 	String savemsg;
						boolean insert = true;
					
						if( ! UIUtils.isValidKeyId (newBdmTlWhywhymst.getWwmsKeyid() )  )//NOT NULL CRETTE
						{	
							newBdmTlWhywhymst  =yyService.create1(newBdmTlWhywhymst ,existBdmTlWhywhymst,yyFormBean);
							 savemsg="Data Saved Successfully";
						}
						else{
							insert = false;
							existBdmTlWhywhymst =yyService.update1(newBdmTlWhywhymst ,existBdmTlWhywhymst,yyFormBean);
							savemsg="Data Updated Successfully";
						}
			
			JSONObject successData = new JSONObject();
			successData.put("msg",savemsg);
			JSONObject returnData = new JSONObject();//
			returnData.put("successData", successData);
			returnData.put("WwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
			returnData.put("formClear", false);
			out.print(returnData.toString());//
			out.close();
				}
			
			}
		catch(ValidationExceptions e){
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"QtmstValidation");
				//errMessage.put("formActionMode",StudentMasterBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}

		}


	private JSONObject getTableModel_YYEffectiveness(
			List<String[]> yYEffectiveList) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();

		String[] colHeader = yYEffectiveList.get(0);
		String[] emptyrow = new String[colHeader.length]; 	
		for( int i = 0; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		 
		 
		for(int i =0; i < colHeader.length; i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
		
			jqGridColModel.setWidth(100);				
			jqGridColModel.setEditable(false);
			if(i==0){
				jqGridColModel.setWidth(80);
				jqGridColModel.setFormatter("checkFormatter");
			}
			if(i==1)
				jqGridColModel.setFormatter("dateFormatter");
			if(i==2)
			{
				jqGridColModel.setFormatter("comboFormatter");
				jqGridColModel.setWidth(250);
			}
			if(i==7)
				jqGridColModel.setWidth(200);
			if(i==9)
				jqGridColModel.setWidth(300);
			if(i==10)
				jqGridColModel.setFormatter("buttonFormatter");
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}

	public static List<String[]> setRootCauseDatas()
	{
		List<String []> rootCauseList = new ArrayList<String[]>();
		List<String> fieldNames=  new ArrayList();
		fieldNames.add("POOR BASIC CONDITION");
		fieldNames.add("POOR OPERATING CONDITION");
		fieldNames.add("DETERIORATION");
		fieldNames.add("WEAK DESIGN");
		fieldNames.add("POOR SKILL");
		int j=0;
		while(j<5)
		{
			String[] rootCause = new String[ 6 ];
			for(int i = 0;i<rootCause.length;i++)
			{
				if(i==1)
				{
					rootCause[i] = fieldNames.get(j);
					
				}
				else
					rootCause[i] = "{}";
			}
			rootCauseList.add(rootCause);
			j++;
		}
		return rootCauseList;
	}
	
	public static List<String[]> fillPillarDatas(List<String []> pillarDatas)
	{
		List<String []> rootCauseList = new ArrayList<String[]>();
	
		for(String[] pillar:pillarDatas)
		{
			int k=0;
			String[] fieldName = new String[pillarDatas.get(k).length];
			for(int i =0;i<pillar.length;i++)
			{
				if(i==0)
					fieldName[i] = pillar[i].replace("JHN", "JISHU HOZEN").replace("OPL", "OPL NO.").replace("KZN", "KAIZEN IDEA NO.").replace("PMC", "PM CALENDAR").replace("POK", "POKA YOKE").replace("4MT", "4M TYPE").replace("TRN", "TRAINING");
				else
					fieldName[i] = pillar[i];
			}
			//rootCauseList.set(k, pillar);
			rootCauseList.add(fieldName);
			k++;
			
		}
		
		return rootCauseList;
	}
	public static List<String[]> fillPillar(List<String []> pillarDatas,String pillarFlag)
	{
		List<String []> rootCauseList = new ArrayList<String[]>();
		
		List<String> fieldNames=  new ArrayList();
		if(UIUtils.isValidKeyId(pillarFlag))
		{
			if(pillarFlag.equals("SHE") || pillarFlag.equals("DOCK"))
			{
				fieldNames.add("KAIZEN");	
				fieldNames.add("OJT");
				fieldNames.add("OPL");
				if(pillarFlag.equals("SHE"))
				  fieldNames.add("POKA YOKE");
				else
				  fieldNames.add("SOP");
				fieldNames.add("TRAINING");
			}
			else
			{
				if(pillarFlag.equals("CC") || pillarFlag.equals("IMT"))
					fieldNames.add("4M TYPE");	
				fieldNames.add("JISHU HOZEN");	
				fieldNames.add("KAIZEN IDEA NO.");
				fieldNames.add("OPL NO.");
				fieldNames.add("PM CALENDAR");
				fieldNames.add("TRAINING");
			}
		}
		else
		{
			fieldNames.add("JISHU HOZEN");	
			fieldNames.add("KAIZEN IDEA NO.");
			fieldNames.add("OPL NO.");
			fieldNames.add("PM CALENDAR");
			fieldNames.add("TRAINING");
		}
			
		int j=0;
		while(j<fieldNames.size())
		{
			String[] rootCause = new String[ 3 ];
			for(int i = 0;i<rootCause.length;i++)
			{
				if(i==0)
					rootCause[i] = fieldNames.get(j);
				else if(i==1)
					rootCause[i] = "{}";
				else
					rootCause[i] = "";
				
				CommonMessage.debugMsg(i + " : "+rootCause[i]);
			}
			rootCauseList.add(rootCause);
			j++;
		}
		return rootCauseList;
	}
	
	private void saveYY(HttpServletRequest request,HttpServletResponse response, YYFormBean yyFormBean) throws BusinessApplicationExceptions,Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	JSONObject successData = new JSONObject();
	    String Typenavigate=request.getParameter("Typenavigate");
	    String SpentTime=request.getParameter("timeSpent");
	    String formMode=request.getParameter("formMode");
	    String hdnMode=request.getParameter("hdnMode");
    	CommonMessage.debugMsg(formMode+"  **** yyService"+yyService);
    	String counterMeasure = request.getParameter("hdnCounterMeasure");
    	String empmKeyId=user.getUsrm_ccno();
    	String WhywhyNo= null;
    	String problem=null;
    	CommonMessage.debugMsg("counterMeasure..."+counterMeasure);
    	if( httpSession != null && user != null)
    	{	
    		BdmTlWhywhymst existBdmTlWhywhymst = (BdmTlWhywhymst)httpSession.getAttribute("BdmTlWhywhyMst"); 
    		 
    		BdmTlWhywhymst newBdmTlWhywhymst = new BdmTlWhywhymst();     		
    		newBdmTlWhywhymst.setWwmsCreatedby(user.getUsrm_ccno());    	  		
    	
    		newBdmTlWhywhymst =(BdmTlWhywhymst)UIUtils.setBeanProperties((Object)newBdmTlWhywhymst,request);
    		
    		if(request.getParameter("YYAnalysis")== null || request.getParameter("YYAnalysis").equals(""))
    		{
	    		CommonMessage.debugMsg("YY Grid : "+request.getParameter("YYAnalysis"));
    		}
    		else
    		{
    			CommonMessage.debugMsg("YY Grid Value= "+request.getParameter("YYAnalysis"));
	    		String yyGrid = request.getParameter("YYAnalysis");	
	    		
		    	JSONArray jsonArray = JSONArray.fromString(yyGrid);
		    	CommonMessage.debugMsg("jsonArray=="+jsonArray);
		    	BdmTlWhywhydtl newBdmTlWhywhydtl = new BdmTlWhywhydtl();
		    	newBdmTlWhywhydtl.setWwdtCreatedby(user.getUsrm_ccno());	 
		    	List<BdmTlWhywhydtl> yyDetail = (List<BdmTlWhywhydtl>) UIUtils.convertJSONArrToList(newBdmTlWhywhydtl, jsonArray);
		    	CommonMessage.debugMsg("jsonArray=="+jsonArray);
		    	if( newBdmTlWhywhydtl != null) {
		    		newBdmTlWhywhymst.setBdmTlWhywhydtl(yyDetail);
		    	}
    		}
    	
	    	
	    	yyFormBean =(YYFormBean) UIUtils.setBeanProperties((Object)yyFormBean,request);
    		CommonMessage.debugMsg("Pillar : "+yyFormBean.getWwmsPillarmode());
    		try{
    			if(!UIUtils.isValidKeyId (newBdmTlWhywhymst.getWwmsKeyid() )  )
				{	
					CommonMessage.debugMsg("create");
					existBdmTlWhywhymst =	yyService.create(newBdmTlWhywhymst,existBdmTlWhywhymst,yyFormBean);
				
					String whywhyNo=existBdmTlWhywhymst.getWwmsKeyid();
					String spentTime=existBdmTlWhywhymst.getWwmsTimespent();
					String area=existBdmTlWhywhymst.getWwmsArea();
					problem=existBdmTlWhywhymst.getWwmsProblem();
					String trade = yyService.getWhywhyTrade(existBdmTlWhywhymst.getWwmsTradeId());
					String pillar = yyService.getWhywhyPillar(existBdmTlWhywhymst.getWwmsPillarid());
					
					CommonMessage.debugMsg("IN side the Create " +whywhyNo +"  .... "+problem+" ....  "+spentTime+" .....  "+area);
			    	sendApprvalMail(request, response,  whywhyNo,problem, spentTime,area,trade,pillar);

				}	
				else
				{
					CommonMessage.debugMsg("update");
					existBdmTlWhywhymst =	yyService.update(newBdmTlWhywhymst,existBdmTlWhywhymst,yyFormBean);			
					/*String Status=existBdmTlWhywhymst.getWwmsAppStatus();
					String roleId=existBdmTlWhywhymst.getWwmsAppStatus();
					
					String whywhyNo=existBdmTlWhywhymst.getWwmsKeyid();
					String spentTime=existBdmTlWhywhymst.getWwmsTimespent();
					String area=existBdmTlWhywhymst.getWwmsArea();
					problem=existBdmTlWhywhymst.getWwmsProblem();
					
					CommonMessage.debugMsg(" IN sid the Update Why Why");
					
					if(Status.equals("A") && (roleId.equals("AROL0131") ||roleId.equals("AROL0132")|| roleId.equals("AROL0133")||roleId.equals("AROL0134")||roleId.equals("AROL0135"))){
				    	sendApprvalMail(request, response,  whywhyNo,problem, spentTime,area);

					}*/
							
							
				}
				
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("cmbWwmsKeyid",existBdmTlWhywhymst.getWwmsKeyid());
				String formType = (String) httpSession.getAttribute("WHYWHYFORMTYPE");
				String filemanager = request.getParameter("filemanager");
				if(UIUtils.isValidKeyId(formType))
					persistentData.put("txtFormType",formType);
				//existBdmTlWhywhymst.getWwmsFormtype()
				JSONObject forwardData = new JSONObject();
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsFactoryid()))
					forwardData.put("factoryId",existBdmTlWhywhymst.getWwmsFactoryid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsSectionid()))
					forwardData.put("sectionId",existBdmTlWhywhymst.getWwmsSectionid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsCellid()))
					forwardData.put("cellId",existBdmTlWhywhymst.getWwmsCellid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsMachineid()))
					forwardData.put("machineID",existBdmTlWhywhymst.getWwmsMachineid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsAssemblyid()))
					forwardData.put("assemblyId",existBdmTlWhywhymst.getWwmsAssemblyid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsKeyid()))
					forwardData.put("keyid",existBdmTlWhywhymst.getWwmsKeyid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsRefdocno()))
					forwardData.put("bdId",existBdmTlWhywhymst.getWwmsRefdocno());
				String getDocId = yyService.getDocId(existBdmTlWhywhymst.getWwmsRefdocno());
				CommonMessage.debugMsg("doccccccccccccccccccccccccccccccc "+getDocId);
				
				
				if(UIUtils.isValidKeyId(getDocId))
					forwardData.put("docId",getDocId);
				//forwardData.put("bdmmode","bdmmode");
				
				
				CommonMessage.debugMsg("RootCause JH: "+existBdmTlWhywhymst.getWwmsIsjh());
				CommonMessage.debugMsg("RootCause PM: "+existBdmTlWhywhymst.getWwmsIspm());
				if(UIUtils.isValidKeyId(yyFormBean.getFormActionMode()))
				{
					String formBeanIdentifier = "YYFormBean"+yyFormBean.getFormActionMode();
					CommonMessage.debugMsg("formBeanIdentifier  "+formBeanIdentifier+"  formBeanIdentifier  "+formBeanIdentifier);
					httpSession.setAttribute(formBeanIdentifier,yyFormBean);
				}
				if(UIUtils.isValidKeyId(filemanager)){
					successData.put("wwmsKeyId",existBdmTlWhywhymst.getWwmsKeyid() );
					successData.put("wwmsRefdocno",existBdmTlWhywhymst.getWwmsRefdocno() );
					successData.put("flid", newBdmTlWhywhymst.getWwmsFlid());
					CommonMessage.debugMsg(" Inside Flid servlet "+newBdmTlWhywhymst.getWwmsFlid());
					//successData.put("wwmsRefdocno",true);
					successData.put("Typenavigate",Typenavigate);
					successData.put("filemanager", true);
					successData.put("formClear",false);
				}else
				{
					//successData.put("wwmsRefdocno",true);
					successData.put("filemanager", false);
					successData.put("formClear",true);
				}
				JSONObject returnData = new JSONObject();
				CommonMessage.debugMsg(" Form Mode");
				httpSession.setAttribute("wwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
				//JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
				successData.put("keyId", existBdmTlWhywhymst.getWwmsKeyid());
				successData.put("problem", existBdmTlWhywhymst.getWwmsProblem());
				successData.put("timeSpent", existBdmTlWhywhymst.getWwmsTimespent());
				successData.put("area", existBdmTlWhywhymst.getWwmsArea()); 
				successData.put("formMode", hdnMode);
				successData.put("flid", newBdmTlWhywhymst.getWwmsFlid());

				//JSONObject returnData = new JSONObject();
				if(existBdmTlWhywhymst.getWwmsIskk().equals("Y"))
					returnData.put("OpenForm","Design");
				else if(existBdmTlWhywhymst.getWwmsIsopl().equals("Y"))
					returnData.put("OpenForm","ET");
				else if(existBdmTlWhywhymst.getWwmsIsjh().equals("Y"))
					returnData.put("OpenForm","JH");
				else if(existBdmTlWhywhymst.getWwmsIspm().equals("Y"))
					returnData.put("OpenForm","PM");
				else if(existBdmTlWhywhymst.getWwmsIspy().equals("Y"))
					returnData.put("OpenForm","PY");
				else if(existBdmTlWhywhymst.getWwmsIsojt().equals("Y"))
					returnData.put("OpenForm","OJ");
				else if(existBdmTlWhywhymst.getWwmsIssop().equals("Y"))
					returnData.put("OpenForm","SO");
				else{
					returnData.put("OpenForm","TRN");
					CommonMessage.debugMsg("ELSE ");
				}
				if("modify".equals(hdnMode)) {
					returnData.put("formClear",false);
				}else {
					returnData.put("formClear",true);
				}
				
				returnData.put("formMode",yyFormBean.getFormActionMode());
				returnData.put("Typenavigate",Typenavigate);
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);
				returnData.put("counterMeasure", counterMeasure);
				CommonMessage.debugMsg(" returnData.toString() "+returnData.toString());
				out.print(returnData.toString());
			
    		}
    		catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("BusinessApplicationExceptions......");
    			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"WhyValidation");				
				out.print(errMessage.toString());
			}
			catch(ValidationExceptions e)
			{				
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"WhyValidation");
				errMessage.put("tpmException", "Data Not Saved");
				out.print(errMessage.toString());			
			}
    		

    	}
    	
    	
	}
	
	
	
	private void sendApprvalMail(HttpServletRequest request, HttpServletResponse response,String whywhyNo, String problem,String SpentTime,String area,String trade,String pillar) throws IOException{
		String fileName  = "";
		String val="";
		File f = null;
	//	String whywhyNo = request.getParameter("momKeyId");
		try{
			CommonMessage.debugMsg(" 12 ");
		
		//	List <String []> WhyDetails =yyService.getWhyWhyDetails(whywhyNo);
			List<String[]> empMailIds = yyService.getEmailIds(whywhyNo) ;
			//String ccEmailId=yyService.getCCEmailId(empmKeyId) ;
		    String mailIds =  buildToMailIds(empMailIds);
			CommonMessage.debugMsg(" mailIds :: 12 "+empMailIds.size());
					
			//String empmKeyId=user.getUsrm_ccno();
			
			String format = ".xlsx";
					
			CommonMessage.debugMsg(" fileName :: Checking :: " + fileName);
					
			String imagePath = UIUtils.getImagePath(request);
			CommonMessage.debugMsg("keyid::::::"+whywhyNo);
			//String format = ExcelUtils.getFormat(request);
		
			String paths = UIUtils.getExcelTemplatePath(request);  	
			Workbook wb = whywhyService.getwhywhyExlView(whywhyNo,format,paths,imagePath); 
		//	ExcelUtils.writeToResponse(response, wb, "WhyWhy_"+whywhyNo, format);
			fileName =this.filePath + "WhyWhyAnalysis_"+whywhyNo + "_" + UIUtils.now() +format;
			FileOutputStream out = new FileOutputStream( fileName );

			  wb.write(out); 
			    out.close();
			    out = null;
			    wb = null;
			
			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);
			CommonMessage.debugMsg(" attachmentFiles."+fileName );
			
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
					
			CommonMessage.debugMsg(" fileNamefileNamefileNamefileName "+fileName);
			
			
			String content=" ";
			StringBuilder subject = new StringBuilder();
			subject.append(" Why Why Analysis - ");
			subject.append(whywhyNo);
			
			StringBuilder totalContent = new StringBuilder();
			//totalContent.append(content+"</BR>");
			totalContent.append(System.getProperty("line.separator"));
			totalContent.append("Dear Sir, </BR>");
			totalContent.append(" Following Why Why is Entered In System.   </BR>");
			totalContent.append("</BR>");
			totalContent.append("\r\n");
			totalContent.append(" Why Why No : "+ whywhyNo +" </BR>");
			//CommonMessage.debugMsg();
			totalContent.append(" \r\n ");
			totalContent.append(System.getProperty("line.separator"));

			totalContent.append(" Area : "+ area +" \r\n");
			totalContent.append(" \r\n ");
			totalContent.append(System.getProperty("line.separator"));
			totalContent.append(" Trade : "+ trade +" \r\n");
			totalContent.append(System.getProperty("line.separator"));
			totalContent.append(" Pillar : "+ pillar +" \r\n");
			totalContent.append("&nbsp;");
			totalContent.append(System.getProperty("line.separator"));
			totalContent.append("  \r\n Problem :  "+ problem +" </BR>");
			totalContent.append(System.getProperty("line.separator"));

			totalContent.append("\r\n");
			totalContent.append(" Time Spent  : "+ SpentTime +" Hrs.</BR>");
			totalContent.append(System.getProperty("line.separator"));

			
			totalContent.append("\r\n");
			totalContent.append("\r\n");
			totalContent.append("</BR>");
			totalContent.append("To check the Details Please Find the Attachment " +" </BR>");
			
			totalContent.append("Regards </BR> ");
			totalContent.append("Perfex 360 Team. "+" </BR>");
			
			//totalContent.append(disclaimerNote);
			CommonMessage.debugMsg(" Before sendLotusNotesMail" );
			
/*			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);*/
			CommonMessage.debugMsg(" attachmentFiles."+fileName );
			
			//attachmentFiles = attachFileManagerFiles(attachmentFiles,whywhyNo);
			
			//UIUtils.sendLotusNotesMail(request,response,mailIds,null,subject.toString(),totalContent.toString(),fileName);
			UIUtils.sendLotusNotesMailAttachments(request,response,mailIds,mailIds,subject.toString(),totalContent.toString(),attachmentFiles);
			
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
			//UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Sent SuccessFully ");
			val="false";
			//String mailid=moMeetingService.updateIsmailid(momKeyId,val);
	    //    response.getWriter().print(succssMsg.toString());
	         
		}catch(Exception e){
			CommonMessage.debugMsg(" sendMomMail Exception" +e);
			e.printStackTrace();
			if( fileName != null && f != null)
				UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Not Sent ! "+ e.getMessage());
			val="true";
			try {
				//String mailid=moMeetingService.updateIsmailid(whywhyNo,val);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// response.getWriter().print(succssMsg.toString());
		}
		
	}
	
	///////////
	
	private void sendApprvalPCMail(HttpServletRequest request, HttpServletResponse response,String whywhyNo, String problem,String SpentTime,String area) throws IOException{
		String fileName  = "";
		String val="";
		File f = null;
	//	String whywhyNo = request.getParameter("momKeyId");
		try{
			CommonMessage.debugMsg(" 12 ");
		
		//	List <String []> WhyDetails =yyService.getWhyWhyDetails(whywhyNo);
			List<String[]> empMailIds = yyService.getEmailIds(whywhyNo) ;
			//String ccEmailId=yyService.getCCEmailId(empmKeyId) ;
		    String mailIds =  buildToMailIds(empMailIds);
			CommonMessage.debugMsg(" mailIds :: 12 "+empMailIds.size());
					
			//String empmKeyId=user.getUsrm_ccno();
			
			String format = ".xlsx";
					
			CommonMessage.debugMsg(" fileName :: Checking :: " + fileName);
					
			String imagePath = UIUtils.getImagePath(request);
			CommonMessage.debugMsg("keyid::::::"+whywhyNo);
			//String format = ExcelUtils.getFormat(request);
		
			String paths = UIUtils.getExcelTemplatePath(request);  	
			Workbook wb = whywhyService.getwhywhyExlView(whywhyNo,format,paths,imagePath); 
		//	ExcelUtils.writeToResponse(response, wb, "WhyWhy_"+whywhyNo, format);
			fileName =this.filePath + "WhyWhyAnalysis_"+whywhyNo + "_" + UIUtils.now() +format;
			FileOutputStream out = new FileOutputStream( fileName );

			  wb.write(out); 
			    out.close();
			    out = null;
			    wb = null;
			
			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);
			CommonMessage.debugMsg(" attachmentFiles."+fileName );
			
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
					
			CommonMessage.debugMsg(" fileNamefileNamefileNamefileName "+fileName);
			
			
			String content=" ";
			StringBuilder subject = new StringBuilder();
			subject.append(" Why Why Analysis - ");
			subject.append(whywhyNo);
			
			StringBuilder totalContent = new StringBuilder();
			//totalContent.append(content+"</BR>");
			totalContent.append(System.getProperty("line.separator"));
			totalContent.append("Dear Sir, </BR>");
			totalContent.append(" Following Why Why is Entered In System.   </BR>");
			totalContent.append("</BR>");
			totalContent.append("\r\n");
			totalContent.append(" Why Why No : "+ whywhyNo +" </BR>");
			//CommonMessage.debugMsg();
			totalContent.append(" \r\n ");
			totalContent.append(System.getProperty("line.separator"));

			totalContent.append(" Area : "+ area +" \r\n");
			totalContent.append("&nbsp;");
			totalContent.append(System.getProperty("line.separator"));
			totalContent.append("  \r\n Problem :  "+ problem +" </BR>");
			totalContent.append(System.getProperty("line.separator"));

			totalContent.append("\r\n");
			totalContent.append(" Time Spent  : "+ SpentTime +" Hrs.</BR>");
			totalContent.append(System.getProperty("line.separator"));

			
			totalContent.append("\r\n");
			totalContent.append("\r\n");
			totalContent.append("</BR>");
			totalContent.append("To check the Details Please Find the Attachment " +" </BR>");
			
			totalContent.append("Regards </BR> ");
			totalContent.append("Perfex 360 Team. "+" </BR>");
			
			//totalContent.append(disclaimerNote);
			CommonMessage.debugMsg(" Before sendLotusNotesMail" );
			
/*			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);*/
			CommonMessage.debugMsg(" attachmentFiles."+fileName );
			
			//attachmentFiles = attachFileManagerFiles(attachmentFiles,whywhyNo);
			
			//UIUtils.sendLotusNotesMail(request,response,mailIds,null,subject.toString(),totalContent.toString(),fileName);
			UIUtils.sendLotusNotesMailAttachments(request,response,mailIds,mailIds,subject.toString(),totalContent.toString(),attachmentFiles);
			
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
			//UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Sent SuccessFully ");
			val="false";
			//String mailid=moMeetingService.updateIsmailid(momKeyId,val);
	    //    response.getWriter().print(succssMsg.toString());
	         
		}catch(Exception e){
			CommonMessage.debugMsg(" sendMomMail Exception" +e);
			e.printStackTrace();
			if( fileName != null && f != null)
				UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Not Sent ! "+ e.getMessage());
			val="true";
			try {
				//String mailid=moMeetingService.updateIsmailid(whywhyNo,val);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// response.getWriter().print(succssMsg.toString());
		}
		
	}

	//////////
	private String buildToMailIds(List<String[]> mailIds){
		StringBuilder mailIdsStr = new StringBuilder();
		CommonMessage.debugMsg(" In side he Build mails "+mailIds.size());
		for(String [] mailid : mailIds){
			if(UIUtils.isValidEmail(mailid[0]) ){
				mailIdsStr.append(mailid[0]);
				mailIdsStr.append(',');
			}	
		}
		if(  mailIdsStr.length() > 0 )
			mailIdsStr.deleteCharAt(mailIdsStr.lastIndexOf(","));
		CommonMessage.debugMsg(" Mail Ids to send the mail "+ mailIdsStr.toString());
		return mailIdsStr.toString();
	}
private List<String> attachFileManagerFiles(List<String> attachmentFiles, String whywhyNo) throws Exception {
		
		List<String[]> docMgrids = yyService.getWhyReleatedFileManager(whywhyNo);
		CommonMessage.debugMsg(" size 1234 :: "+docMgrids.size());
		for (int i=0;i<docMgrids.size();i++) {
			String fileName = docMgrids.get(i)[0];
			//commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			String ext = fileName.substring(fileName.lastIndexOf('.'),fileName.length());
			CommonMessage.debugMsg(" Checking for file extension to be correct "+ext); 
		   //DcmTlDocumentmanager dcmTlDocumentmanager = docManagerService.getDocMgr(fileId);		
		   if(UIUtils.isValidKeyId(fileName))
		   {
			   //String fileName = dcmTlDocumentmanager.getDmdmFilename();
			   String path = DOC_ROOT_PATH + docMgrids.get(i)[1];
			   CommonMessage.debugMsg(" Fourth New path "+path);
			   path = path.replace("/", "\\");
			   CommonMessage.debugMsg(" filename extension "+path+ext);
			   //attachmentFiles.add(path+ext);
			   attachmentFiles.add(path);
		   }
		}
		
		return attachmentFiles;
	}
	
	
	/*private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			if(beanIdentifier.equals("whywhyQtyCommonFilter")) 
				commonFilter = 	FilterValues.getQuality(request, commonFilter);
			else			
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(Integer.valueOf(-5)).substring(3,11)); //.substring(3,11)
			 
			  CommonMessage.debugMsg(" In  side the DAte" +CommonFunctions.getFirstDateofMonth(Integer.valueOf(-5)));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  } 
	 	  	
		return commonFilter;
	}
	*/
////    
		 private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
		      HttpSession httpSession = request.getSession(false);
		      CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		      if (commonFilter != null && !createNew) {
		         FilterValues.setPaginationParams(request, commonFilter);
		         FilterValues.getCommonFilters(request, commonFilter);
		      } else {
		         commonFilter = new CommonFilter();
		         commonFilter = FilterValues.getCommonFilters(request, commonFilter);
		         if (beanIdentifier.equals("whywhyQtyCommonFilter")) {
		            commonFilter = FilterValues.getQuality(request, commonFilter);
		         } else {
		            commonFilter = FilterValues.getBDRelated(request, commonFilter);
		         }

		         commonFilter.setViewClick('Y');
		         httpSession.removeAttribute(beanIdentifier);
		         httpSession.setAttribute(beanIdentifier, commonFilter);
		      }

		      if ("01-Jan-1801".contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
		         commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
		         commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
		         commonFilter.setMonwise("Y");
		      }

		      return commonFilter;
		   }
		 
		 ///
	
	
	
	private CommonFilter populateCommonFilter1(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			if(beanIdentifier.equals("whywhyQtyCommonFilter"))
				commonFilter = 	FilterValues.getQuality(request, commonFilter);
			else			
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		/* if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11)); //.substring(3,11)
			 
			  CommonMessage.debugMsg(" In  side the DAte" +CommonFunctions.getFirstDateofMonth(-5));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  } */

		
		return commonFilter;
	}
	
	
	private void fillYY(BdmTlWhywhymst bdmTlWhywhymst,WomTlWomst womTlWomst)
	{
		if(UIUtils.isValidKeyId(womTlWomst.getWomsActivityid()))
			bdmTlWhywhymst.setWwmsRefdocno(womTlWomst.getWomsActivityid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsOccurreddate()))
			bdmTlWhywhymst.setWwmsDate(womTlWomst.getWomsOccurreddate());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMachineid()))
			bdmTlWhywhymst.setWwmsMachineid(womTlWomst.getWomsMachineid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAssemblyid()))
			bdmTlWhywhymst.setWwmsAssemblyid(womTlWomst.getWomsAssemblyid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsFactoryid()))
			bdmTlWhywhymst.setWwmsFactoryid(womTlWomst.getWomsFactoryid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCellid()))
			bdmTlWhywhymst.setWwmsCellid(womTlWomst.getWomsCellid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSectionid()))
			bdmTlWhywhymst.setWwmsSectionid(womTlWomst.getWomsSectionid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsPhenomenaid()))
			bdmTlWhywhymst.setWwmsPhenomenaid(womTlWomst.getWomsPhenomenaid());
		//if(UIUtils.isValidKeyId(womTlWomst.getWomsCauseid()))
			//bdmTlWhywhymst.setwwms;
		if(UIUtils.isValidKeyId(womTlWomst.getWomsReportedby()))
			bdmTlWhywhymst.setWwmsMaintinchargeid(womTlWomst.getWomsReportedby());
		//bdmTlWhywhymst..setwwmss
				
	}
	
	private JSONObject getTableModel(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] row = headers.get(0);
		String [] colHeader1 = headers.get(1);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setTableButton(true);		 
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		String headerSql = "'SELECT ";  
		for(int i=0;i<row.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
			jqGridColModel.setName(row[i].replaceAll(" ", ""));
			jqGridColModel.setEditable(false);
			CommonMessage.debugMsg(i + " : "+row[i]);
			if(i==0 || i==1)
				jqGridColModel.setHidden(true);
			else if(i == 2 || i == 3)
				jqGridColModel.setWidth(100);
			
			else if (i==4 )
			{
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			}
			else if(i == 5)
				jqGridColModel.setWidth(300);
			
			else if(i == 6)
				jqGridColModel.setWidth(100);
			else if(i == 11 || i == 12 || i == 13)
				jqGridColModel.setWidth(100);
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL '";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		CommonMessage.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	private JSONObject getTableModelAnalyis(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		//String [] colHeader1 = headers.get(0);
		String[] colHeader1 = {"txtWwdtKeyid","txtWwdtWhy","Answer","Delete"};
		
		
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setLoadOnce(true);
		//jqGridTableModel.setMultiSelect(false);
		for(int i=0;i<colHeader1.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setEditable(false);
			jqGridColModel.setAlign("left");
		
			if(i==0 ||i==1)
			{				
				jqGridColModel.setHidden(true);
			}
			if(i==2)
			{				
				jqGridColModel.setWidth(420);
				jqGridColModel.setFormatter("txtFormatter");
			}
			if(i==3)
			{				
				jqGridColModel.setWidth(35);
				jqGridColModel.setFormatter("actionFormatterDel");
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth", "47%%");
		CommonMessage.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	private JSONObject getTableModelMainGrid(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader1 = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		for(int i=0;i<colHeader1.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setEditable(false);
			jqGridColModel.setAlign("left");
			jqGridColModel.setWidth(100);
			if(i==1 || i==2)
				jqGridColModel.setWidth(165);
			if(i==3 || i==4 || i==5 || i==6)
				jqGridColModel.setWidth(185);
			if(i==0){
				jqGridColModel.setHidden(true);
				
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		CommonMessage.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	private JSONObject getTableModelProposed(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String[] row = headers.get(0);
		String [] colHeader1 = headers.get(1);
		String [] colHeader0= headers.get(0);
		CommonMessage.debugMsg( " Header )0011.................." +colHeader1   );
		String[] tempCol = new String[row.length];
		jqGridTableModel.getRowHeaders().add(tempCol);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		for(int i=0;i<colHeader1.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(tempCol[i] = "");
			jqGridColModel.setIndex(tempCol[i].replaceAll(" ", ""));
			jqGridColModel.setName(tempCol[i].replaceAll(" ", ""));
			jqGridColModel.setIndex(colHeader0[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader0[i].replaceAll(" ", ""));
			jqGridColModel.setEditable(false);
			jqGridColModel.setAlign("left");
			jqGridColModel.setWidth(100);
			if(i==0 ){
				jqGridColModel.setWidth(252);
			}
			if(i==5 ){
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("center");
				jqGridColModel.setFormatter("txtFormatter1");
			}if(i==2 ){
				jqGridColModel.setWidth(100);
				jqGridColModel.setFormatter("txtFormatter1");
			}if(i==4){
				jqGridColModel.setWidth(75);
			}if(i==1 ){
				jqGridColModel.setWidth(157);
			}
			if(i== colHeader1.length){
				jqGridColModel.setHidden(true);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "34%%");
		tableModel.set("tableWidth", "54%%");

		return tableModel;
	}
	
	private void whywhyIdentifiedDrillDownReport(HttpServletRequest request,HttpServletResponse response ) throws Exception{
		
		String action = UIUtils.getActionPart(request);
		if( action.equals("whywhyRptGenDrill_view.why")){
			
			UIUtils.forwardRequest(request, response, "/pages/Reports/whywhygendrilldown.jsp");
		}
		else if( action.equals("whywhyRptGenDrill_getCol.why")){
			buildTableColModel( request,response);
		}
		else if( action.equals("whywhyRptGenDrill_getData.why")){
			
			whywhyGetData(request , response);
		}
		else if( action.equals("whywhyRptGenDrill_chart.why"))
		{
			processChart(request,response);
		}
		else if(action.equals("whywhyRptGenDrill_getExcel.why")){
			exportWhyWhyGenRptExcel(request,response);
		}
	}


	private void buildTableColModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		String firstClick = request.getParameter("firstClick");

		CommonFilter commonFilter = null;
		if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
			 commonFilter = (CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		}
		
		if (commonFilter == null)
			commonFilter = new CommonFilter();
		
		commonFilter = populateCommonFilter(request,gendrillcommonfilter,true);
		
		httpSession.removeAttribute(gendrillcommonfilter);
		httpSession.setAttribute(gendrillcommonfilter, commonFilter);
		CommonMessage.debugMsg(" Constants.passNullDate " + Constants.passNullDate + " commonFilter.getFromMonth() " +  commonFilter.getFromMonth());
		
		if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
		{
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
			commonFilter.setMonwise("Y");
		}
		
		commonFilter.setIsGetCol("Y");
	
		List<String[]> yyData = yyService.getWhyWhyGenDrillData(commonFilter);
		JSONObject jsonObject = getTableModel(yyData,FilterValues.getHeader(commonFilter.getDrillCaption()));
		jsonObject.set("tableHeight", "80%%");
	    jsonObject.set("tableWidth", "104%%");
		httpSession.removeAttribute("ImprovementColData");
		httpSession.setAttribute("ImprovementColData", jsonObject);
		
		out.println(jsonObject);
	} 
	
	
	private void whywhyGetData(HttpServletRequest request , HttpServletResponse response) throws Exception {

		
		CommonFilter commonFilter = populateCommonFilter(request,gendrillcommonfilter,false);
		
		commonFilter.setIsGetCol("N");
		List<String[]> impVscomList = yyService.getWhyWhyGenDrillData(commonFilter);
		
		JSONObject listToJsonObject = new JSONObject();

		if (impVscomList != null && impVscomList.size() > 1)
			listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 0,commonFilter.getTotalRecordCnt());
		
		PrintWriter out = response.getWriter();
		out.println(listToJsonObject);
		
	}
	
	private JSONObject getTableModel(List<String[]> headers,String caption)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		
		String [] colHeader = headers.get(0) ;
		int header = colHeader.length; // removed -1
		String [] headerArr = new String[header];
		colHeader[2] = caption;  
		
		jqGridTableModel.setTableButton(true);	
		JqGridColModel jqGridColModel =getColModel("keyid1",50,"left");
		
		jqGridColModel.setHidden(true);

		jqGridTableModel.getColModel().add(jqGridColModel);
		jqGridTableModel.setRowNumbers(true);
		
		jqGridColModel = getColModel("keyid2",100,"left");
		jqGridTableModel.getColModel().add(jqGridColModel);
		headerArr[0] = "keyid1";
		headerArr[1] = "keyid2";
		headerArr[2] = "";
		
		jqGridColModel = getColModel("FLLOC",300,"left");
		jqGridColModel.setHidden(false);
		jqGridTableModel.getColModel().add(jqGridColModel);
		
		for(int i =3; i < header; i++)
		{
			headerArr[i] = colHeader[i].replaceAll(" ", "");
			jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(headerArr[i]+""+i);
			jqGridColModel.setName(headerArr[i]+""+i);
			jqGridColModel.setWidth(100);	
			jqGridColModel.setAlign("right");
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		jqGridTableModel.getRowHeaders().add(headerArr);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 	
		tableModel.set("tableHeight", "72%");
		return tableModel;
      }
	
	 private void exportWhyWhyGenRptExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//HttpSession httpSession = request.getSession(false);
		
	     CommonFilter commonFilter = populateCommonFilter(request,gendrillcommonfilter,false);
	    
		
		JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
		
		tblJSONObj.put("title", "Why Why Report");
		
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = yyService.getWhyWhyGenDrillDataExcel(commonFilter,tblJSONObj,format);
		
		
		ExcelUtils.writeToResponse(response, wb, "WhyWhyGenReport", format);
		
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

	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		
		String forDashboard = request.getParameter("dashboard");
		
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
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
		
		List<String[]> whywhyList  = yyService.getWhyWhyGenDrillData(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(whywhyList != null && whywhyList.size() > 0)
		{	
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processLineChart(lcnname,whywhyList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	

	private JSONObject processLineChart(String titlename,List<String[]> whywhyList,CommonFilter commonFilter){

		if( whywhyList == null || whywhyList.size() <= 1  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		//String drillLevel = FilterValues.getHeader(commonFilter.getDrillCaption());
		
	
		//String[] header =  whywhyList.get(0);
		String[] month =  whywhyList.get(0);
		String[] data =  whywhyList.get(whywhyList.size()-1);
		String prevMonth = null;
		if(commonFilter.getRowTotal()==null)
			commonFilter.setRowTotal('N');
		String subTitle = data[2];
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		
	
		String drillLevel =  FilterValues.getDrillHeader(data[1]);
		StringBuilder title = new StringBuilder(titlename+"-WHY WHY Cumulative -").append( drillLevel).append(" Level From ").append(date);
		
		//String title = "WHY WHY Cumulative  " +subTitle ;
		ChartSeries timeSeries = new ChartSeries();
		List<Double> cumulativeData = new ArrayList<Double>();
		
		for( int i = 3;i < month.length;i++ ){ // removed -1
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
			timeSeries.setName("Why Why Cumulative");
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
	
	
	private JSONObject getyyDonebyTableModel(List<String[]> yyDonebyList) {
		// TODO Auto-generated method stub
		JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
		
		//String[] colHeader = yyDonebyList.get(0);	
		String[] colHeader = {"sno", "keyid", "wwmskeyid", "Employee", "Delete"};
		 
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(false);
		jqGridTableModel.setTableHeight(290);  
		jqGridTableModel.setTableWidth(600);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setRowNumbers(true);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")); 
			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);	 
			
			if (i <=2) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			if(i==colHeader.length-1){
				jqGridColModel.setFormatter("BtnFormatterDelete2");
				jqGridColModel.setWidth(40);
				jqGridColModel.setAlign("center");
			} 
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth", "26%%");
		return tableModel;

	}

	private void buildTableCountColModel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		String firstClick = request.getParameter("firstClick");

		CommonFilter commonFilter = null;
		if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
			 commonFilter = (CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		}
		
		if (commonFilter == null)
			commonFilter = new CommonFilter();
		
		commonFilter = populateCommonFilter(request,gendrillcommonfilter,true);
		
		httpSession.removeAttribute(gendrillcommonfilter);
		httpSession.setAttribute(gendrillcommonfilter, commonFilter);
		CommonMessage.debugMsg(" Constants.passNullDate " + Constants.passNullDate + " commonFilter.getFromMonth() " +  commonFilter.getFromMonth());
		
		if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
		{
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(Integer.valueOf(-5)).substring(3, 11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			commonFilter.setMonwise("Y");
		}
	
		List<String[]> yyData = yyService.getWhyWhyCountData(commonFilter);
		CommonMessage.debugMsg("***********printing the count data***");
		for(String[] row:yyData) 
		{
			CommonMessage.debugMsg(Arrays.toString(row));
		}
		JSONObject jsonObject = getTableModel(yyData,FilterValues.getHeader(commonFilter.getDrillCaption()));
		jsonObject.set("tableHeight", "80%%");
	    jsonObject.set("tableWidth", "104%%");
		httpSession.removeAttribute("ImprovementColData");
		httpSession.setAttribute("ImprovementColData", jsonObject);
		
		out.println(jsonObject);
		
	}
	private void whywhycountGetData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		CommonFilter commonFilter = populateCommonFilter(request,gendrillcommonfilter,false);
		List<String[]> impVscomList = yyService.getWhyWhyCountData(commonFilter);
		
		JSONObject listToJsonObject = new JSONObject();

		if (impVscomList != null && impVscomList.size() > 1)
			listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 0,commonFilter.getTotalRecordCnt());
		
		PrintWriter out = response.getWriter();
		out.println(listToJsonObject);
		
		
	}
	
	
	private void exportWhyWhyCountExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{

	     CommonFilter commonFilter = populateCommonFilter(request,gendrillcommonfilter,false);
	    
		
		JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
		
		tblJSONObj.put("title", "Why Why Count");
		
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = yyService.getWhyWhyCountExcel(commonFilter,tblJSONObj,format);
		
		
		ExcelUtils.writeToResponse(response, wb, "WhyWhyCount", format);
		
	}
	private void exportWhyWhyCountRootCauseExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    
	    CommonFilter commonFilter = populateCommonFilter(request,gendrillcommonfilter,false);
	    
	    JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
	    
	    tblJSONObj.put("title", "Why Why Root Cause");
	    
	    String format = ExcelUtils.getFormat(request);
	    
	    Workbook wb = yyService.getWhyWhyCountRootCauseExcel(commonFilter,tblJSONObj,format);
	    
	    ExcelUtils.writeToResponse(response, wb, "WhyWhyRootCause", format);
	}

	private void exportWhyWhyCountCounterExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    
	    CommonFilter commonFilter = populateCommonFilter(request,gendrillcommonfilter,false);
	    
	    JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
	    
	    tblJSONObj.put("title", "Why Why Counter Measure");
	    
	    String format = ExcelUtils.getFormat(request);
	    
	    Workbook wb = yyService.getWhyWhyCountCounterExcel(commonFilter,tblJSONObj,format);
	    
	    ExcelUtils.writeToResponse(response, wb, "WhyWhyCounterMeasure", format);
	}
	
	private void processbarChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		
		String forDashboard = request.getParameter("dashboard");
		
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		}
		else{
			/*commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);	*/
			commonFilter = populateCommonFilter(request,gendrillcommonfilter,true);
		}
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
	
		
		//FilterValues.getCommonFilters(request, chartCommonFilter) ;
		  if(chartCommonFilter.getRowTotal()==null)
		     chartCommonFilter.setRowTotal('Y');
		
		List<String[]> whywhyList  = yyService.getWhyWhyCountData(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(whywhyList != null && whywhyList.size() > 0)
		{
			
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);	
			
			chartObj = processBarChart2(lcnname,whywhyList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	
	private void processbarChartRootCause(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		
		String forDashboard = request.getParameter("dashboard");
		
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		}
		else{
			/*commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);	*/
			commonFilter = populateCommonFilter(request,gendrillcommonfilter,true);
		}
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
	
		
		//FilterValues.getCommonFilters(request, chartCommonFilter) ;
		  if(chartCommonFilter.getRowTotal()==null)
		     chartCommonFilter.setRowTotal('Y');
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 CommonMessage.debugMsg("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 CommonMessage.debugMsg("PreviousYear"+PreviousYear);
			 String FYearStart="APR-"+CurrentYear;
			 CommonMessage.debugMsg("FYearStart"+FYearStart);
			 Integer NextYear=Integer.parseInt(CurrentYear)+1;
			 CommonMessage.debugMsg("FYearEnd"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="MAR-"+(NextYear);
			 chartCommonFilter.setAbnDetect(FYearStart);
			 chartCommonFilter.setAbnAllch(FYearEnd);
			     
		
		List<String[]> whywhyList  = yyService.getRootCauseList(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(whywhyList != null && whywhyList.size() > 0)
		{
			
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);	
			
			chartObj = processBarChartRootCause2(lcnname,whywhyList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	private void processbarChartCounter(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		
		String forDashboard = request.getParameter("dashboard");
		
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		}
		else{
			/*commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);	*/
			commonFilter = populateCommonFilter(request,gendrillcommonfilter,true);
		}
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
	
		
		//FilterValues.getCommonFilters(request, chartCommonFilter) ;
		  if(chartCommonFilter.getRowTotal()==null)
		     chartCommonFilter.setRowTotal('Y');
		     String CurrentYear=CommonFunctions.getCurrentYear();
			 CommonMessage.debugMsg("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 CommonMessage.debugMsg("PreviousYear"+PreviousYear);
			 String FYearStart="APR-"+CurrentYear;
			 CommonMessage.debugMsg("FYearStart"+FYearStart);
			 Integer NextYear=Integer.parseInt(CurrentYear)+1;
			 CommonMessage.debugMsg("FYearEnd"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="MAR-"+(NextYear);
			 chartCommonFilter.setAbnDetect(FYearStart);
			 chartCommonFilter.setAbnAllch(FYearEnd);
		
		List<String[]> whywhyList  = yyService.getCounterMeasureList(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(whywhyList != null && whywhyList.size() > 0)
		{
			
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);	
			
			chartObj = processBarChartCounter2(lcnname,whywhyList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	/*
	 * private JSONObject processBarChartRootCause2(String titlename,List<String[]>
	 * whywhyList,CommonFilter commonFilter){
	 * 
	 * if( whywhyList == null || whywhyList.size() <= 1 ) return null;
	 * ChartOptionBean lineChart = new ChartOptionBean(); List<String> xAxisCategory
	 * = new ArrayList<String>(); List<ChartSeries> chartSeriesList = new
	 * ArrayList<ChartSeries>(); List<ChartYAxis> chartYAxis = new
	 * ArrayList<ChartYAxis>();
	 * 
	 * StringBuilder date=new StringBuilder();
	 * if(commonFilter.getMonwise().equals("Y")) {
	 * date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.
	 * getToMonth()); } else
	 * date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.
	 * getToDate()); String[] header = whywhyList.get(2); String[] month =
	 * whywhyList.get(1); String[] data = whywhyList.get(4);
	 * 
	 * 
	 * //String drillLevel = FilterValues.getDrillHeader(data[0]);
	 * //CommonMessage.debugMsg("The DrillLevel"+drillLevel);
	 * 
	 * StringBuilder title=new
	 * StringBuilder(titlename+"-Why Why Root Cause Trend - ").append(date);
	 * //String title = "Kaizen Benefit Amt Vs Verified Amt Report";
	 * 
	 * 
	 * int rSize = whywhyList.size()-3; //3 int cSize = header.length -2; //2
	 * CommonMessage.debugMsg("rSize"+rSize); CommonMessage.debugMsg("cSize"+cSize);
	 * 
	 * String subTitle=""; Double [][] grData = new Double[rSize][cSize];
	 * CommonMessage.debugMsg("grData"+grData); for( int i =3;i <header.length;i++ ){
	 * for (int j = 3; j< whywhyList.size();j++) { data = whywhyList.get(j);
	 * CommonMessage.debugMsg("The Data IS:::"+data); grData[j-3][i-3] =
	 * Double.parseDouble(data[i]); //
	 * CommonMessage.debugMsg("The GRData Length"+grData.length); }
	 * 
	 * xAxisCategory.add(month[i]); } for(int k=0;k<grData.length;k++){ ChartSeries
	 * timeSeries = new ChartSeries(); timeSeries.setData(Arrays.asList(grData[k]));
	 * timeSeries.setType(ChartTypes.COLUMN);
	 * timeSeries.setName(whywhyList.get(k+3)[2]); //3
	 * CommonMessage.debugMsg("The Kaizen Benefit List::::::"+whywhyList.get(k+3)[1]);
	 * chartSeriesList.add(timeSeries); } ChartYAxis yAxis = new ChartYAxis();
	 * yAxis.setMin(0); chartYAxis.add(yAxis); yAxis.getTitle().setText("Rs");
	 * ChartXAxis xaxis = new ChartXAxis();
	 * if(commonFilter.getMonwise().equals("Y")) xaxis.getTitle().setText("Month");
	 * else xaxis.getTitle().setText("Date");
	 * lineChart.getxAxis().setTitle(xaxis.getTitle()); return
	 * lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(),
	 * subTitle, chartYAxis);
	 * 
	 * }
	 */
	//mano 
	
	private JSONObject processBarChartRootCause2(String titlename, List<String[]> whywhyList, CommonFilter commonFilter){

	    if( whywhyList == null || whywhyList.size() <= 1  )
	        return null;
	    ChartOptionBean lineChart =  new ChartOptionBean();
	    List<String> xAxisCategory = new ArrayList<String>();
	    List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
	    List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();

	    StringBuilder date=new StringBuilder();
	    if(commonFilter.getMonwise().equals("Y"))
	    {
	        date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
	    }
	    else
	        date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());

	    String[] header =  whywhyList.get(2);
	    String[] month =  whywhyList.get(1);
	    String[] data =  whywhyList.get(4);

	    StringBuilder title=new StringBuilder(titlename+"-Why Why Root Cause Trend - ").append(date);

	    int rSize = whywhyList.size()-3;  // Number of data series (categories)
	    int cSize = header.length - 3;     // Include all columns from index 3 onwards (including TOTAL)

	    CommonMessage.debugMsg("rSize"+rSize);
	    CommonMessage.debugMsg("cSize"+cSize);

	    String subTitle="";
	    Double [][] grData = new Double[rSize][cSize];
	    CommonMessage.debugMsg("grData"+grData);

	    // Loop through all columns INCLUDING the TOTAL column
	    for( int i = 3; i < header.length; i++ ){  // Changed: removed -1 to include TOTAL
	        for (int j = 3; j < whywhyList.size(); j++) {
	            data  =  whywhyList.get(j);
	            CommonMessage.debugMsg("The Data IS:::"+data);
	            grData[j-3][i-3] = Double.parseDouble(data[i]);
	        }
	        xAxisCategory.add(month[i]);
	    }

	    // Create series for each category
	    for(int k=0; k<grData.length; k++){
	        ChartSeries timeSeries = new ChartSeries();
	        timeSeries.setData(Arrays.asList(grData[k]));
	        timeSeries.setType(ChartTypes.COLUMN);
	        timeSeries.setName(whywhyList.get(k+3)[2]);
	        CommonMessage.debugMsg("The Kaizen Benefit List::::::"+whywhyList.get(k+3)[1]);
	        chartSeriesList.add(timeSeries);
	    }

	    ChartYAxis yAxis = new ChartYAxis();
	    yAxis.setMin(0);
	    chartYAxis.add(yAxis);
	    yAxis.getTitle().setText("Rs");

	    ChartXAxis xaxis = new ChartXAxis();
	    if(commonFilter.getMonwise().equals("Y"))
	        xaxis.getTitle().setText("Month");
	    else
	        xaxis.getTitle().setText("Date");
	    lineChart.getxAxis().setTitle(xaxis.getTitle());

	    return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	}
	/*
	 * private JSONObject processBarChartCounter2(String titlename,List<String[]>
	 * whywhyList,CommonFilter commonFilter){
	 * 
	 * if( whywhyList == null || whywhyList.size() <= 1 ) return null;
	 * ChartOptionBean lineChart = new ChartOptionBean(); List<String> xAxisCategory
	 * = new ArrayList<String>(); List<ChartSeries> chartSeriesList = new
	 * ArrayList<ChartSeries>(); List<ChartYAxis> chartYAxis = new
	 * ArrayList<ChartYAxis>();
	 * 
	 * StringBuilder date=new StringBuilder();
	 * if(commonFilter.getMonwise().equals("Y")) {
	 * date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.
	 * getToMonth()); } else
	 * date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.
	 * getToDate()); String[] header = whywhyList.get(2); String[] month =
	 * whywhyList.get(1); String[] data = whywhyList.get(4);
	 * 
	 * 
	 * //String drillLevel = FilterValues.getDrillHeader(data[0]);
	 * //CommonMessage.debugMsg("The DrillLevel"+drillLevel);
	 * 
	 * StringBuilder title=new
	 * StringBuilder(titlename+"-Why Why Counter Measure - ").append(date); //String
	 * title = "Kaizen Benefit Amt Vs Verified Amt Report";
	 * 
	 * 
	 * int rSize = whywhyList.size()-3; //3 int cSize = header.length -2; //2
	 * CommonMessage.debugMsg("rSize"+rSize); CommonMessage.debugMsg("cSize"+cSize);
	 * 
	 * String subTitle=""; Double [][] grData = new Double[rSize][cSize];
	 * CommonMessage.debugMsg("grData"+grData); for( int i =3;i <header.length;i++ ){
	 * for (int j = 3; j< whywhyList.size();j++) { data = whywhyList.get(j);
	 * CommonMessage.debugMsg("The Data IS:::"+data); grData[j-3][i-3] =
	 * Double.parseDouble(data[i]); //
	 * CommonMessage.debugMsg("The GRData Length"+grData.length); }
	 * 
	 * xAxisCategory.add(month[i]); } for(int k=0;k<grData.length;k++){ ChartSeries
	 * timeSeries = new ChartSeries(); timeSeries.setData(Arrays.asList(grData[k]));
	 * timeSeries.setType(ChartTypes.COLUMN);
	 * timeSeries.setName(whywhyList.get(k+3)[2]); //3
	 * CommonMessage.debugMsg("The Kaizen Benefit List::::::"+whywhyList.get(k+3)[1]);
	 * chartSeriesList.add(timeSeries); } ChartYAxis yAxis = new ChartYAxis();
	 * yAxis.setMin(0); chartYAxis.add(yAxis); yAxis.getTitle().setText("Rs");
	 * ChartXAxis xaxis = new ChartXAxis();
	 * if(commonFilter.getMonwise().equals("Y")) xaxis.getTitle().setText("Month");
	 * else xaxis.getTitle().setText("Date");
	 * lineChart.getxAxis().setTitle(xaxis.getTitle()); return
	 * lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(),
	 * subTitle, chartYAxis);
	 * 
	 * }
	 */
	//mano 
	/*
	 * private JSONObject processBarChartCounter2(String titlename, List<String[]>
	 * whywhyList, CommonFilter commonFilter){
	 * 
	 * if( whywhyList == null || whywhyList.size() <= 1 ) return null;
	 * ChartOptionBean lineChart = new ChartOptionBean(); List<String> xAxisCategory
	 * = new ArrayList<String>(); List<ChartSeries> chartSeriesList = new
	 * ArrayList<ChartSeries>(); List<ChartYAxis> chartYAxis = new
	 * ArrayList<ChartYAxis>();
	 * 
	 * StringBuilder date=new StringBuilder();
	 * if(commonFilter.getMonwise().equals("Y")) {
	 * date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.
	 * getToMonth()); } else
	 * date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.
	 * getToDate());
	 * 
	 * String[] header = whywhyList.get(2); String[] month = whywhyList.get(1);
	 * String[] data = whywhyList.get(4);
	 * 
	 * StringBuilder title=new
	 * StringBuilder(titlename+"-Why Why Counter Measure - ").append(date);
	 * 
	 * int rSize = whywhyList.size()-3; // Number of data series (categories) int
	 * cSize = header.length - 3; // Include all columns from index 3 onwards
	 * (including TOTAL)
	 * 
	 * CommonMessage.debugMsg("rSize"+rSize); CommonMessage.debugMsg("cSize"+cSize);
	 * 
	 * String subTitle=""; Double [][] grData = new Double[rSize][cSize];
	 * CommonMessage.debugMsg("grData"+grData);
	 * 
	 * // Loop through all columns INCLUDING the TOTAL column for( int i = 3; i <
	 * header.length; i++ ){ // Changed: removed -1 to include TOTAL for (int j = 3;
	 * j < whywhyList.size(); j++) { data = whywhyList.get(j);
	 * CommonMessage.debugMsg("The Data IS:::"+data); grData[j-3][i-3] =
	 * Double.parseDouble(data[i]); } xAxisCategory.add(month[i]); }
	 * 
	 * // Create series for each category for(int k=0; k<grData.length; k++){
	 * ChartSeries timeSeries = new ChartSeries();
	 * timeSeries.setData(Arrays.asList(grData[k]));
	 * timeSeries.setType(ChartTypes.COLUMN);
	 * timeSeries.setName(whywhyList.get(k+3)[2]);
	 * CommonMessage.debugMsg("The Kaizen Benefit List::::::"+whywhyList.get(k+3)[1]);
	 * chartSeriesList.add(timeSeries); }
	 * 
	 * ChartYAxis yAxis = new ChartYAxis(); yAxis.setMin(0); chartYAxis.add(yAxis);
	 * yAxis.getTitle().setText("Rs");
	 * 
	 * ChartXAxis xaxis = new ChartXAxis();
	 * if(commonFilter.getMonwise().equals("Y")) xaxis.getTitle().setText("Month");
	 * else xaxis.getTitle().setText("Date");
	 * lineChart.getxAxis().setTitle(xaxis.getTitle());
	 * 
	 * return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(),
	 * subTitle, chartYAxis); }
	 */
	
	private JSONObject processBarChartCounter2(String titlename, List<String[]> whywhyList, CommonFilter commonFilter){

	    if( whywhyList == null || whywhyList.size() <= 1  )
	        return null;
	    
	    ChartOptionBean lineChart = new ChartOptionBean();
	    List<String> xAxisCategory = new ArrayList<String>();
	    List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
	    List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();

	    StringBuilder date = new StringBuilder();
	    if(commonFilter.getMonwise().equals("Y"))
	    {
	        date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
	    }
	    else
	        date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());

	    // Row structure from PostgreSQL function:
	    // Index 0: Table model row (metadata)
	    // Index 1: Header row 1 (DATAORDER=1) - contains column names
	    // Index 2: Header row 2 (DATAORDER=2) - contains "CNT" labels
	    // Index 3+: Data rows (DATAORDER=3) - countermeasure data
	    // Last row: TOTAL row (DATAORDER=4)
	    
	    String[] headerRow = whywhyList.get(1);  // Row with column names (dates)
	    String[] labelRow = whywhyList.get(2);   // Row with CNT labels

	    StringBuilder title = new StringBuilder(titlename + "-Why Why Counter Measure - ").append(date);

	    // Calculate dimensions
	    // Data rows: skip model(0), header1(1), header2(2), and TOTAL(last row)
	    int rSize = whywhyList.size() - 4;  // Number of countermeasure categories (excludes model, 2 headers, TOTAL)
	    
	    // Columns structure: RN, DATAORDER, MEASURE_ORDER, ResultType, [Date columns...], TOTAL_I
	    // We want columns from index 4 (first date) to end (including TOTAL_I)
	    int cSize = headerRow.length - 4;  // Exclude RN, DATAORDER, MEASURE_ORDER, ResultType
	    
	    CommonMessage.debugMsg("rSize (number of countermeasures): " + rSize);
	    CommonMessage.debugMsg("cSize (number of date columns + TOTAL): " + cSize);

	    String subTitle = "";
	    Double[][] grData = new Double[rSize][cSize];
	    
	    // Build xAxis categories (dates + TOTAL)
	    for(int i = 4; i < headerRow.length; i++){  // Start from column 4 (first date column)
	        xAxisCategory.add(headerRow[i]);
	    }
	    
	    CommonMessage.debugMsg("X-Axis Categories: " + xAxisCategory);

	    // Populate data array - loop through countermeasure rows only
	    for(int j = 0; j < rSize; j++){
	        String[] dataRow = whywhyList.get(j + 3);  // Start from row 3 (first data row)
	        
	        CommonMessage.debugMsg("Processing row " + (j+3) + " - Countermeasure: " + dataRow[3]);
	        
	        // Loop through numeric columns (skip RN, DATAORDER, MEASURE_ORDER, ResultType)
	        for(int i = 4; i < dataRow.length; i++){
	            try {
	                grData[j][i-4] = Double.parseDouble(dataRow[i]);
	            } catch (NumberFormatException e) {
	                System.err.println("Error parsing value at row " + (j+3) + ", col " + i + ": " + dataRow[i]);
	                grData[j][i-4] = 0.0;  // Default to 0 if parsing fails
	            }
	        }
	    }

	    // Create chart series for each countermeasure category
	    for(int k = 0; k < rSize; k++){
	        ChartSeries timeSeries = new ChartSeries();
	        timeSeries.setData(Arrays.asList(grData[k]));
	        timeSeries.setType(ChartTypes.COLUMN);
	        
	        // Get countermeasure name from ResultType column (index 3)
	        String seriesName = whywhyList.get(k + 3)[3];
	        timeSeries.setName(seriesName);
	        
	        CommonMessage.debugMsg("Series " + k + ": " + seriesName + " - Data: " + Arrays.toString(grData[k]));
	        chartSeriesList.add(timeSeries);
	    }

	    ChartYAxis yAxis = new ChartYAxis();
	    yAxis.setMin(0);
	    chartYAxis.add(yAxis);
	    yAxis.getTitle().setText("Count");  // Changed from "Rs" to "Count" since this is countermeasure counts

	    ChartXAxis xaxis = new ChartXAxis();
	    if(commonFilter.getMonwise().equals("Y"))
	        xaxis.getTitle().setText("Month");
	    else
	        xaxis.getTitle().setText("Date");
	    lineChart.getxAxis().setTitle(xaxis.getTitle());

	    return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	}
	private JSONObject processBarChart2(String titlename,List<String[]> whywhyList,CommonFilter commonFilter){

		if( whywhyList == null || whywhyList.size() <= 1  )
			return null;
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		String[] month =  whywhyList.get(0);
		String[] data =  whywhyList.get(whywhyList.size()-2);
		String prevMonth = null;
		
		String subTitle="";
		//String subTitle = data[2];
		//CommonMessage.debugMsg("subTitle"+subTitle);
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		
	
		String drillLevel =  FilterValues.getDrillHeader(data[1]);
		StringBuilder title = new StringBuilder(titlename+"-Why Why Count - ").append( drillLevel).append(" Wide From ").append(date);
		ChartSeries timeSeries = new ChartSeries();
		List<Double> countData = new ArrayList<Double>();
		for( int i = 3;i < month.length;i++ ){ // removed -1
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
			timeSeries.setName("Why Why Count");
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
