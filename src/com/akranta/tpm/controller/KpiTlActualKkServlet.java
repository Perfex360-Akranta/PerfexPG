package com.akranta.tpm.controller;

import java.io.File;

/**
 * Author:Roopa
 * Created on : 06/09/2012
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.ColumnChart;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridEditOptions;
import com.akranta.tpm.bean.KpiTlActualKkBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.KpiTlKpiremarksBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlActualKk;
import com.akranta.tpm.model.KpiTlIndicatorKk;
import com.akranta.tpm.model.KpiTlKpiremarks;
import com.akranta.tpm.service.KpiTlActualKkService;
import com.akranta.tpm.service.impl.KpiTlActualKkServiceImpl;
import com.akranta.tpm.service.KpiTlIndicatorKkService;
import com.akranta.tpm.service.impl.KpiTlIndicatorKkServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;
//import com.sun.corba.se.spi.orbutil.threadpool.Work;

public class KpiTlActualKkServlet extends HttpServlet {
	private static final long serialVersionUID = -8070953412956046800L;
	KpiTlActualKkService kpiTlActualKkService;
	KpiTlIndicatorKkService kpiTlIndicatorKkService;
	String glbmsg="";
	String glbLocation="";
	String filePath=null;
    String rolename="";
	private static String DOC_ROOT_PATH;
    private static String docRealPath;
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static  String APP_DOCMANAGER_PATH ;
	public KpiTlActualKkServlet() {
		super();
		/*
		 * try { kpiTlActualKkService = new KpiTlActualKkServiceImpl();
		 * kpiTlIndicatorKkService = new KpiTlIndicatorKkServiceImpl(); }catch
		 * (Exception e) { }
		 */
	}
	
	
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
	    

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String uri = request.getRequestURI();
		HttpSession httpSession = request.getSession(false);
		response.setContentType("application/json");
		String action = UIUtils.getActionPart(request);
		try {
			kpiTlActualKkService = (KpiTlActualKkServiceImpl) UIUtils
					.getServiceObject(request, "KpiTlActualKkServiceImpl");
			kpiTlIndicatorKkService = (KpiTlIndicatorKkServiceImpl) UIUtils
					.getServiceObject(request, "KpiTlIndicatorKkServiceImpl");
			
			kpiTlActualKkService.KpiTlActualKkServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
			kpiTlIndicatorKkService.KpiTlIndicatorKkServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
			
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}


		if (action.equals("kpiActualKk_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "KK", "view");
		} else if (action.equals("kpiActualKkActual_view.kpiActKk")
				|| action.equals("IndicatorMainactualKK_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "KK",
					"actual");
		} else if (action.equals("kpiActualKkTarget_view.kpiActKk")
				|| action.equals("IndicatorMaintargetKK_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "KK",
					"target");
		} else if (action.equals("kpiActualQm_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "QM", "view");
		} else if (action.equals("kpiActualQmActual_view.kpiActKk")
				|| action.equals("IndicatorMainactualQM_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "QM",
					"actual");
		} else if (action.equals("kpiActualQmTarget_view.kpiActKk")
				|| action.equals("IndicatorMaintargetQM_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "QM",
					"target");
		} else if (action.equals("kpiActualShe_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "SHE",
					"view");
		} else if (action.equals("kpiActualSheActual_view.kpiActKk")
				|| action.equals("IndicatorMainactualSHE_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "SHE",
					"actual");
		} else if (action.equals("kpiActualSheTarget_view.kpiActKk")
				|| action.equals("IndicatorMaintargetSHE_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "SHE",
					"target");
		} else if (action.equals("kpiActualEt_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "ET", "view");
		} else if (action.equals("kpiActualEtActual_view.kpiActKk")
				|| action.equals("IndicatorMainactualET_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "ET",
					"actual");
		} else if (action.equals("kpiActualEtTarget_view.kpiActKk")
				|| action.equals("IndicatorMaintargetET_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "ET",
					"target");
		} else if (action.equals("kpiActualPm_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "PM", "view");
		} else if (action.equals("kpiActualPmActual_view.kpiActKk")
				|| action.equals("IndicatorMainactualPPM_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "PM",
					"actual");
		} else if (action.equals("kpiActualPmTarget_view.kpiActKk")
				|| action.equals("IndicatorMaintargetPPM_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "PM",
					"target");
		} else if (action.equals("kpiActualJh_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "JH", "view");
		} else if (action.equals("kpiActualJhActual_view.kpiActKk")
				|| action.equals("IndicatorMainactualJH_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "JH",
					"actual");
		} else if (action.equals("kpiActualJhTarget_view.kpiActKk")
				|| action.equals("IndicatorMaintargetJH_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "JH",
					"target");
		} else if (action.equals("kpiActualDm_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "DM", "view");
		} else if (action.equals("kpiActualDmActual_view.kpiActKk")
				|| action.equals("IndicatorMainactualDM_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "DM",
					"actual");
		} else if (action.equals("kpiActualDmTarget_view.kpiActKk")
				|| action.equals("IndicatorMaintargetDM_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "DM",
					"target");
		} else if (action.equals("kpiActualOtpm_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "OTPM",
					"view");
		} else if (action.equals("kpiActualOtpmActual_view.kpiActKk")
				|| action.equals("IndicatorMainactualOTPM_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "OTPM",
					"actual");
		} else if (action.equals("kpiActualOtpmTarget_view.kpiActKk")
				|| action.equals("IndicatorMaintargetOTPM_view.kpiActKk")) {
			LoadCostOfQualityEntry(request, response, httpSession, "OTPM",
					"target");
		} else if (action.equals("kpiActualKk_input.kpiActKk")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/KPI/KpiTlActualKk.jsp");
			rd.forward(request, response);
		} else if (action.equals("functionalLoc.kpiActKk")) {
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbKaukFactoryid");
			functLocFieldNameBean.setSection("cmbKaukSectionid");
			functLocFieldNameBean.setCell("cmbKaukCellid");
			// functLocFieldNameBean.setMachine("");
			// functLocFieldNameBean.setFactMandatory(true);
			functLocFieldNameBean.setSectMandatory(true);
			// functLocFieldNameBean.setCellMandatory(true);
			// functLocFieldNameBean.setMachMandatory(false);
			FormModes formModes = (FormModes) httpSession
					.getAttribute("kpiKkFormMode");
			if (formModes == FormModes.completion)
				formModes = FormModes.view;
			UIUtils.setFunctionalLocationPopupVal(request, response,
					functLocFieldNameBean, formModes);
		}

		else if (action.equals("kpiRemarksReport_input.kpiActKk")) {
			// HttpSession httpSession = request.getSession(false);
			String IndicatiorName = request.getParameter("IndicatiorName");
			String Frequency = request.getParameter("Frequency");
			String IndicatiorId = request.getParameter("Indicator");
			String flId = request.getParameter("flid");
			String kpiMonth = request.getParameter("kpiMonth");
			String dbyear = request.getParameter("dbyear");
			String kprmDate = request.getParameter("kprmDate");//added
			CommonMessage.debugMsg("STEP3: Popup received dbyear = " + request.getParameter("dbyear"));

			// CommonMessage.debugMsg("IndicatiorId..."+IndicatiorId);
			String Uom = request.getParameter("Uom");
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/KPI/remarksReport.jsp");
			request.setAttribute("kprmDate", kprmDate);//added
			request.setAttribute("IndicatiorId", IndicatiorId);
			request.setAttribute("IndicatiorName", IndicatiorName);
			request.setAttribute("Frequency", Frequency);
			request.setAttribute("flid", flId);
			request.setAttribute("Uom", Uom);
			request.setAttribute("kpiMonth", kpiMonth);
			request.setAttribute("dbyear", dbyear);
			httpSession.setAttribute("IndicatiorId", IndicatiorId);
			rd.forward(request, response);
		} else if (action.equals("kpiRemarksReport_getCol.kpiActKk")) {
			CommonMessage.debugMsg("kpiRemarksReport_getCol.kpiActKk");
			String indicatorid = (String) httpSession.getAttribute("IndicatiorId");
			String Frequency = request.getParameter("Frequency");
			String Year = request.getParameter("year");
			String dbyear = request.getParameter("dbyear");
			CommonMessage.debugMsg("STEP4: getCol received dbyear = " + request.getParameter("dbyear"));

			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"KPIRemarksBeanFilter", true);
			commonFilter.setFreq(Frequency);
			commonFilter.setYear(Year);
			commonFilter.setYear(dbyear);
			String kprmDate = request.getParameter("kprmDate");
		    if (kprmDate != null && !kprmDate.trim().isEmpty() && !kprmDate.equals("01-Jan-1801")) {
		        commonFilter.setFromDate(kprmDate);
		    }
			
			request.setAttribute("dbyear", dbyear);
			
			CommonMessage.debugMsg("Frequency in Remarkbtn ::"+commonFilter.getFreq());
			CommonMessage.debugMsg("Year in Remarkbtn ::"+commonFilter.getYear());
			commonFilter.setIndicator(indicatorid);
			List<String[]> kpiRemarksData = kpiTlActualKkService
					.getKPIRemarksReport(commonFilter);
			JSONObject colmodel = getRemarksTableModel(kpiRemarksData.get(1),
					kpiRemarksData.get(1), 'M', true, false);
			httpSession.removeAttribute("KPIRemarksColModel");
			httpSession.setAttribute("KPIRemarksColModel", colmodel);
			out.println(colmodel);

		} else if (action.equals("kpiRemarksReport_getData.kpiActKk")) {
			CommonMessage.debugMsg("kpiRemarksReport_getData.kpiActKk");
			String indicatorid = (String) httpSession
					.getAttribute("IndicatiorId");
			CommonFilter commonFilter = populateCommonFilter(request,
					"KPIRemarksBeanFilter", false);
			String Frequency = request.getParameter("Frequency");
			String Year = request.getParameter("year");
			
			String dbyear = request.getParameter("dbyear");
			
			commonFilter.setIndicator(indicatorid);
			commonFilter.setFreq(Frequency);
			commonFilter.setYear(Year);
			String kprmDate = request.getParameter("kprmDate");
		    if (kprmDate != null && !kprmDate.trim().isEmpty() && !kprmDate.equals("01-Jan-1801")) {
		        commonFilter.setFromDate(kprmDate);
		    }
			CommonMessage.debugMsg("Year in Remarkbtn ::"+commonFilter.getYear());
			
			CommonMessage.debugMsg("Frequency in Remarkbtn ::"+commonFilter.getFreq());
			
			request.setAttribute("dbyear", dbyear);
			httpSession.removeAttribute("KPIRemarksBeanFilter");
			
			httpSession.setAttribute("KPIRemarksBeanFilter", commonFilter);
			List<String[]> kpiRemarksData = kpiTlActualKkService.getKPIRemarksReport(commonFilter);
			JSONObject jsonObject = UIUtils.convertToJqGridTableObject(	kpiRemarksData, request, 2, 0,	commonFilter.getTotalRecordCnt());
			CommonMessage.debugMsg("jsonObject " + jsonObject);
			PrintWriter out = response.getWriter();
			out.println(jsonObject);
		}

		else if (action.equals("kpiRemarks_input.kpiActKk")) {
			// HttpSession httpSession = request.getSession(false);
			String IndicatiorName = request.getParameter("IndicatiorName");
			String Frequency = request.getParameter("Frequency");
			String IndicatiorId = request.getParameter("Indicator");
			String flId = request.getParameter("flid");
			String kpiMonth = request.getParameter("kpiMonth");
			String kprmDate = request.getParameter("kprmDate");
			// CommonMessage.debugMsg("IndicatiorId..."+IndicatiorId);
			String Uom = request.getParameter("Uom");
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/KPI/remarksPopup.jsp");
			 request.setAttribute("kprmDate", kprmDate);
			request.setAttribute("IndicatiorId", IndicatiorId);
			request.setAttribute("IndicatiorName", IndicatiorName);
			request.setAttribute("Frequency", Frequency);
			request.setAttribute("flid", flId);
			request.setAttribute("Uom", Uom);
			request.setAttribute("kpiMonth", kpiMonth);
			httpSession.setAttribute("IndicatiorId", IndicatiorId);
			rd.forward(request, response);
		} else if (action.equals("kpiRemarks_getCol.kpiActKk")) {
			CommonMessage.debugMsg("kpiRemarks_getCol.kpiActKk");
			String indicatorid = (String) httpSession.getAttribute("IndicatiorId");
			String kprmDate = request.getParameter("kprmDate");
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"KPIRemarksBeanFilter", true);
			commonFilter.setIndicator(indicatorid);
			if (kprmDate != null && !kprmDate.trim().isEmpty() && !kprmDate.equals("01-Jan-1801")) {
		        commonFilter.setFromDate(kprmDate);
		    }
			
			List<String[]> kpiRemarksData = kpiTlActualKkService.getKPIRemarks(commonFilter);
			JSONObject colmodel = getRemarksTableModel(kpiRemarksData.get(1),
					kpiRemarksData.get(1), 'M', true, false);
			httpSession.removeAttribute("KPIRemarksColModel");
			httpSession.setAttribute("KPIRemarksColModel", colmodel);
			out.println(colmodel);

		} else if (action.equals("kpiRemarks_getData.kpiActKk")) {
			CommonMessage.debugMsg("kpiRemarks_getData.kpiActKk");
			String indicatorid = (String) httpSession
					.getAttribute("IndicatiorId");
			String kprmDate = request.getParameter("kprmDate");
			CommonFilter commonFilter = populateCommonFilter(request,
					"KPIRemarksBeanFilter", false);
			if (kprmDate != null && !kprmDate.trim().isEmpty() && !kprmDate.equals("01-Jan-1801")) {
		        commonFilter.setFromDate(kprmDate);
		    }
			commonFilter.setIndicator(indicatorid);
			httpSession.removeAttribute("KPIRemarksBeanFilter");
			httpSession.setAttribute("KPIRemarksBeanFilter", commonFilter);
			List<String[]> kpiRemarksData = kpiTlActualKkService.getKPIRemarks(commonFilter);
			JSONObject jsonObject = UIUtils.convertToJqGridTableObject(	kpiRemarksData, request, 2, 0,	commonFilter.getTotalRecordCnt());
			CommonMessage.debugMsg("jsonObject " + jsonObject);
			PrintWriter out = response.getWriter();
			out.println(jsonObject);
		} else if (action.equals("kpiActualKk_getCol.kpiActKk")) {
			CommonMessage.debugMsg("kpiActualKk_getCol.kpiActKk---1");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			CommonFilter commonFilter = populateCommonFilter(request,"ActualKKListCommonFilter", true);
			// String indicatorid =
			// (String)httpSession.getAttribute("IndicatiorId");
			httpSession.setAttribute("KPItype", "Y");
			//jsonObject = getColActualKkList(request, response, httpSession,	commonFilter); //**This is line hidden for exportexcel purpose**
			List<String[]> costOfQualityGrid = null;											//**bcoz getExcel need "Entrytype" field
			String year = request.getParameter("year");
			String flId = request.getParameter("flId");
			String factId = request.getParameter("factId");
			String sectionId = request.getParameter("sectionId");
			String cellId = request.getParameter("cellId");
			String pillarId = request.getParameter("pillarId");
			String isActual = request.getParameter("isActual");
			String FormMode = request.getParameter("frmMode");
			commonFilter.setSafetyMode(FormMode);
			CommonMessage.debugMsg("The FormMode::::"+FormMode);
			String type = "N";
			type = (String) httpSession.getAttribute("KPItype");

			String frmType = request.getParameter("type");
			CommonMessage.debugMsg("The frmType is"+frmType);
			commonFilter.setType(frmType);
			String kinkFrequency = request.getParameter("kinkFrequency");
			String month = request.getParameter("month");

			CommonMessage.debugMsg("frequency ====>" + kinkFrequency+ " Month:=> " + month);

			KpiTlActualKk newKpiTlActualKk = new KpiTlActualKk();
			newKpiTlActualKk = (KpiTlActualKk) UIUtils.setBeanProperties(newKpiTlActualKk, request);
			// CommonMessage.debugMsg("1. newKpiTlActualKk.getKaukFreqtype(): " +
			// newKpiTlActualKk.getKaukFreqtype());

			if (!CommonFunctions.isValidKeyId(year))
				year = CommonFunctions.getCurrentYear();

			newKpiTlActualKk.setKaukCalendaryear(year);

			newKpiTlActualKk.setKaukDeptid("");
			newKpiTlActualKk.setKaukDepttype("");
			if (CommonFunctions.isValidKeyId(flId))
				newKpiTlActualKk.setKaukDeptid(flId);
			/*
			 * if (CommonFunctions.isValidKeyId(factId))
			 * newKpiTlActualKk.setKaukFactoryid(factId); if
			 * (CommonFunctions.isValidKeyId(sectionId))
			 * newKpiTlActualKk.setKaukSectionid(sectionId); if
			 * (CommonFunctions.isValidKeyId(cellId))
			 * newKpiTlActualKk.setKaukCellid(cellId);
			 */
			if (CommonFunctions.isValidKeyId(pillarId))
				newKpiTlActualKk.setKaukPillarid(pillarId);
			if (CommonFunctions.isValidKeyId(isActual))
				newKpiTlActualKk.setKaukIsactual(isActual);
			// JSONObject costOfQualityGriddata = null;
			GridParams gridParams;

			gridParams = new GridParams();
			commonFilter.setTemp("kinkFrequency=" + kinkFrequency + ";month="+ month + ";");
			commonFilter.setIsGetCol("Y");
			
			try {

				costOfQualityGrid = kpiTlActualKkService.getCostOfQualitygridData(newKpiTlActualKk, gridParams, commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// costOfQualityGriddata =
			// UIUtils.convertToJqGridTableObject(costOfQualityGrid,request,0,0,gridParams.getTotalRecordCnt());
			// httpSession.setAttribute("costOfQualityGriddata",
			// costOfQualityGriddata);
			jsonObject = getTableModel(costOfQualityGrid, FormMode,	year, type, kinkFrequency);
			JSONObject jsonObjectExcel = getTableModelExcel(costOfQualityGrid, FormMode,	year, type, kinkFrequency);
			if (costOfQualityGrid.size() > 0) {
				jsonObject.set("tableHeight", "87%%");
				jsonObject.set("tableWidth", "100%%");
			}

			httpSession.removeAttribute("ActualKKListColModel");
			httpSession.setAttribute("ActualKKListColModel", jsonObject);
			httpSession.removeAttribute("ActualKKListColModelExcel");
			httpSession.setAttribute("ActualKKListColModelExcel", jsonObjectExcel);
			out.println(jsonObject);
		}

		else if (action.equals("kpiActualKk_getData.kpiActKk")) {
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			jsonObject = getDataActualKkList(request, response, httpSession);
			out.println(jsonObject);
		} else if (action.equals("kpiActualKk_getExcel.kpiActKk")) {
			CommonMessage.debugMsg("kpiActualKk_getExcel.kpiActKk");
			getExlActualKkList(request, response, httpSession);
		} else if (action.equals("kpiActualKkWeekly_view.kpiActKk")) {
			LoadCostOfQualityDailyEntry(request, response, httpSession);
			// CommonMessage.debugMsg("inside weeklty ");
			String monthYear = request.getParameter("monthYear");
			// CommonMessage.debugMsg("monthYear  "+monthYear);
			request.setAttribute("monthYear", monthYear);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/KPI/KpiActualKKWeekly.jsp");
			rd.forward(request, response);
			// CommonMessage.debugMsg("after dispatch");
		} else if (action.equals("kpiActualKkDaily_view.kpiActKk")) {
			LoadCostOfQualityDailyEntry(request, response, httpSession);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/KPI/KpiTlActualKkDaily.jsp");
			rd.forward(request, response);
		} else if (action.equals("kpiActualKkActual_save.kpiActKk")
				|| action.equals("IndicatorMainactualKK_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualKkTarget_save.kpiActKk")
				|| action.equals("IndicatorMainactualKK_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualQmActual_save.kpiActKk")
				|| action.equals("IndicatorMainactualQM_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualQmTarget_save.kpiActKk")
				|| action.equals("IndicatorMaintargetQM_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualSheActual_save.kpiActKk")
				|| action.equals("IndicatorMainactualSHE_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualSheTarget_save.kpiActKk")
				|| action.equals("IndicatorMaintargetSHE_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualEtActual_save.kpiActKk")
				|| action.equals("IndicatorMainactualET_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualEtTarget_save.kpiActKk")
				|| action.equals("IndicatorMaintargetET_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualPmActual_save.kpiActKk")
				|| action.equals("IndicatorMainactualPPM_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualPmTarget_save.kpiActKk")
				|| action.equals("IndicatorMaintargetPPM_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualJhActual_save.kpiActKk")
				|| action.equals("IndicatorMainactualJH_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualJhTarget_save.kpiActKk")
				|| action.equals("IndicatorMaintargetJH_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualDmActual_save.kpiActKk")
				|| action.equals("IndicatorMainactualDM_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualDmTarget_save.kpiActKk")
				|| action.equals("IndicatorMaintargetDM_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualOtpmActual_save.kpiActKk")
				|| action.equals("IndicatorMainactualOTPM_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		} else if (action.equals("kpiActualOtpmTarget_save.kpiActKk")
				|| action.equals("IndicatorMaintargetOTPM_save.kpiActKk")) {
			saveKpiActualKk(request, response);
		}
		
		 else if(action.equals("KPItlActual_Excelview.kpiActKk")){
			  try{ 
				//CommonFilter commonFilter = new CommonFilter();	
			  //  String Keyid=request.getParameter("keyid");
			    String flid=request.getParameter("flid");
			    CommonMessage.debugMsg("The Flid"+flid);
			    String year=request.getParameter("year");
			    CommonMessage.debugMsg("The Year:::"+year);
			    String CurrDate=request.getParameter("CurrDate");
			    CommonMessage.debugMsg("The CurrDate:"+CurrDate);
				String CurrMonthYear=request.getParameter("CurrMonthYear");
				CommonMessage.debugMsg("The CurrMonthYear:::"+CurrMonthYear);
				String frequency=request.getParameter("vFreq");
				CommonMessage.debugMsg("The vFreq:::"+frequency);
			    String format = ExcelUtils.getFormat(request);
			    CommonMessage.debugMsg("The format"+format);
			    String path = UIUtils.getExcelTemplatePath(request);
			    CommonMessage.debugMsg("The path"+path);
			    Workbook wb = kpiTlActualKkService.KPIDeviationExcelView(flid,year,CurrDate,CurrMonthYear,frequency,format,path);
			    format = ".xlsx";
			    ExcelUtils.writeToResponse(response, wb, "KPIDeviation_"+flid, format);
			  }
				catch(Exception e)
				{
					PrintWriter out = response.getWriter();
					JSONObject err = new JSONObject();				
					err.put("message" ,"Data Not Found" );
					out.print(err.toString());
				}
		   } 

		else if (action.equals("kpiRemarks_save.kpiActKk")) {
			saveKpiRemarksKk(request, response);
		}
		
		else if(action.equals("KPIDeviation_sendMail.kpiActKk")){
			KpiTlActualKk kpiTlActualKk=new KpiTlActualKk();
			SendKPIDeviationMail(request,response,kpiTlActualKk);
		}

		else if (action.equals("kpiActualKkWeekly_list.kpiActKk")) {
			JSONObject jSONObject = new JSONObject();
			JSONArray jSONArray = new JSONArray();
			PrintWriter out = response.getWriter();
			String monthYear = request.getParameter("monthYear");
			String indicatorid = request.getParameter("indicatorid");
			String year = request.getParameter("year");
			String factId = request.getParameter("factId");
			String sectionId = request.getParameter("sectionId");
			String cellId = request.getParameter("cellId");
			String pillarId = request.getParameter("pillarId");
			String isActual = request.getParameter("isActual");
			/* Added for DEMO purpose* */
			/*
			 * monthYear = " Dec-2013"; indicatorid ="KIN0000005"; year ="2013";
			 * factId="FCT0000012"; sectionId="LIN0000026"; cellId="CEL0000066";
			 * pillarId="TGT003"; isActual="Y";
			 */
			/** END */
			KpiTlActualKk newKpiTlActualKk = new KpiTlActualKk();
			newKpiTlActualKk = (KpiTlActualKk) UIUtils.setBeanProperties(
					newKpiTlActualKk, request);

			if (CommonFunctions.isValidKeyId(monthYear))
				newKpiTlActualKk.setKaukMonthyear(monthYear);
			if (CommonFunctions.isValidKeyId(indicatorid))
				newKpiTlActualKk.setKaukIndicatorid(indicatorid);
			if (CommonFunctions.isValidKeyId(year))
				newKpiTlActualKk.setKaukCalendaryear(year);
			/*
			 * if (CommonFunctions.isValidKeyId(factId))
			 * newKpiTlActualKk.setKaukFactoryid(factId); if
			 * (CommonFunctions.isValidKeyId(sectionId))
			 * newKpiTlActualKk.setKaukSectionid(sectionId); if
			 * (CommonFunctions.isValidKeyId(cellId))
			 * newKpiTlActualKk.setKaukCellid(cellId);
			 */
			if (CommonFunctions.isValidKeyId(pillarId))
				newKpiTlActualKk.setKaukPillarid(pillarId);
			if (CommonFunctions.isValidKeyId(isActual))
				newKpiTlActualKk.setKaukIsactual(isActual);
			// CommonMessage.debugMsg("newKpiTlActualKk.getKaukMonthyear():" +
			// newKpiTlActualKk.getKaukMonthyear());

			List<String[]> weekList = new ArrayList<String[]>();
			weekList = kpiTlActualKkService.getWeeksList(newKpiTlActualKk);

			if (weekList.size() > 0) {
				jSONArray = jSONArray.fromCollection(weekList);
				jSONObject.put("monthList", jSONArray);
			}
			/*
			 * jSONObject.put("year",year); jSONObject.put("progId",progId);
			 * jSONObject.put("monthwise","Y");
			 */
			out.print(jSONObject.toString());
		} else if (action.equals("kpiActualKkDaily_list.kpiActKk")) {
			JSONObject jSONObject = new JSONObject();
			JSONArray jSONArray = new JSONArray();
			PrintWriter out = response.getWriter();
			String monthYear = request.getParameter("monthYear");
			String indicatorid = request.getParameter("indicatorid");
			String year = request.getParameter("year");
			String factId = request.getParameter("factId");
			String sectionId = request.getParameter("sectionId");
			String cellId = request.getParameter("cellId");
			String pillarId = request.getParameter("pillarId");
			String isActual = request.getParameter("isActual");
			/* Added for DEMO purpose* */
			/*
			 * monthYear = "01-Dec-2013"; indicatorid ="KIN0000005"; year
			 * ="2013"; factId="FCT0000012"; sectionId="LIN0000026";
			 * cellId="CEL0000066"; pillarId="TGT003"; isActual="Y"; /**END
			 */
			KpiTlActualKk newKpiTlActualKk = new KpiTlActualKk();
			newKpiTlActualKk = (KpiTlActualKk) UIUtils.setBeanProperties(
					newKpiTlActualKk, request);

			if (CommonFunctions.isValidKeyId(monthYear))
				newKpiTlActualKk.setKaukMonthyear(monthYear);
			if (CommonFunctions.isValidKeyId(indicatorid))
				newKpiTlActualKk.setKaukIndicatorid(indicatorid);
			if (CommonFunctions.isValidKeyId(year))
				newKpiTlActualKk.setKaukCalendaryear(year);

			newKpiTlActualKk.setKaukDeptid("");
			newKpiTlActualKk.setKaukDepttype("");
			/*
			 * if (CommonFunctions.isValidKeyId(factId))
			 * newKpiTlActualKk.setKaukCalendaryear(year);
			 * 
			 * newKpiTlActualKk.setKaukFactoryid(factId); if
			 * (CommonFunctions.isValidKeyId(sectionId))
			 * newKpiTlActualKk.setKaukSectionid(sectionId); if
			 * (CommonFunctions.isValidKeyId(cellId))
			 * newKpiTlActualKk.setKaukCellid(cellId);
			 */
			if (CommonFunctions.isValidKeyId(pillarId))
				newKpiTlActualKk.setKaukPillarid(pillarId);
			if (CommonFunctions.isValidKeyId(isActual))
				newKpiTlActualKk.setKaukIsactual(isActual);
			// CommonMessage.debugMsg("newKpiTlActualKk.getKaukMonthyear():" +
			// newKpiTlActualKk.getKaukMonthyear());

			List<String[]> dateList = new ArrayList<String[]>();
			dateList = kpiTlActualKkService.getDatesList(newKpiTlActualKk);

			if (dateList.size() > 0) {
				jSONArray = jSONArray.fromCollection(dateList);
				jSONObject.put("monthList", jSONArray);
			}
			/*
			 * jSONObject.put("year",year); jSONObject.put("progId",progId);
			 * jSONObject.put("monthwise","Y");
			 */
			out.print(jSONObject.toString());
		}
		/** Added BY ManiKandan **/

		else if (action.equals("kpiIndicatorList_view.kpiActKk")) {
			String flid = request.getParameter("flid");
			String pillar = request.getParameter("pillar");
			String KpiKeyid = request.getParameter("kpikeyid");
			CommonMessage.debugMsg("flid=== " + flid);
			String from = request.getParameter("from");
			String rptType = request.getParameter("rptType");
			String calendaryr = request.getParameter("dbyear");
			String vMonth = request.getParameter("vMonth");
			
			String type = request.getParameter("type");
			String monthYear = request.getParameter("monthYear");
			if (!UIUtils.isValidKeyId(type))
				type = "KPI";
			request.setAttribute("flid", flid);
			request.setAttribute("pilarref", pillar);
			request.setAttribute("from", from);
			request.setAttribute("Kpikeyid", KpiKeyid);
			request.setAttribute("rpttype", rptType);
			request.setAttribute("monthyear", monthYear);
			request.setAttribute("type", type);
			request.setAttribute("calyr", calendaryr);
			request.setAttribute("vMonth", vMonth);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/KPI/KpiIndicatorList.jsp");
			rd.forward(request, response);
		} else if (action.equals("kpiIndicatorgrd_input.kpiActKk")) {

		} else if (action.equals("kpiIndicatorgrd_getCol.kpiActKk")) {
			PrintWriter out = response.getWriter();
			// CommonMessage.debugMsg("getCol");
			String colmodel = UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.KpiTlIndicatorKkProp",
					"colModelKpiIndicator");
			JSONObject jsonObject = JSONObject.fromString(colmodel);
			jsonObject.set("multiSelect", true);
			out.println(jsonObject);
		} else if (action.equals("kpiIndicatorgrd_getData.kpiActKk")) {
			JSONObject kpiListIndicator = null;
			List<String[]> kpiListIndicatorLst = null;
			// CommonMessage.debugMsg("getData");
			PrintWriter out = response.getWriter();
			String pillarHdn = request.getParameter("pillarHdn");
			String flId = request.getParameter("flId");
			String rptType = request.getParameter("rptType");
			String monthYear = request.getParameter("monthYear");
			String type = request.getParameter("type");
			// CommonMessage.debugMsg("pillarHdn "+pillarHdn);
			httpSession.setAttribute("FLID", flId);
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setPillarWise(pillarHdn);
			commonFilter.setFlid(flId);
			commonFilter.setType(type);
			commonFilter.setKK(rptType);
			kpiListIndicatorLst = kpiTlActualKkService
					.getListOfIndicators(commonFilter);
			kpiListIndicator = UIUtils.convertToJqGridTableObject(
					kpiListIndicatorLst, request, 0, 0);
			// CommonMessage.debugMsg("getData ;;"+kpiListIndicatorLst.size());
			out.println(kpiListIndicator);
		} else if (action.equals("page_input.kpiActKk") ||action.equals("kpiIndicatorchartpage_input.kpiActKk")) {
			String indicatorId = request.getParameter("grdData");
			String indicatorName = request.getParameter("indicatorName");
			String rptType = request.getParameter("rptType");
			String monthYear = request.getParameter("monthYear");
			String type = request.getParameter("type");
			String calyr = request.getParameter("calyr");
			String month=request.getParameter("vMonth");
			
			String dbyear = request.getParameter("dbyear");
			
			
			if (!UIUtils.isValidKeyId(type))
				type = "KPI";
			// CommonMessage.debugMsg("indicatorName  :"+indicatorName);
			request.setAttribute("indicatorId", indicatorId);
			request.setAttribute("indicatorName", indicatorName);
			request.setAttribute("rptType", rptType);
			request.setAttribute("monthYear", monthYear);
			request.setAttribute("calyr", calyr);
			request.setAttribute("type", type);
			request.setAttribute("month", month);
			
			request.setAttribute("dbyear", dbyear); 
			
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/KPI/KpiIndicatorGraph.jsp");
			rd.forward(request, response);
		} else if (action.equals("kpiIndicator_chart.kpiActKk")) {
			String indicatorId = request.getParameter("idicatorId");
			String chartDiv = request.getParameter("chartDiv");
			String rptType =request.getParameter("rptType");
			String monthid = request.getParameter("monthid");
			
			if(rptType.equals("DM"))
				kpiIndicatorChartDM(request, response, indicatorId, chartDiv);
			else 
				kpiIndicatorChart(request, response, indicatorId, chartDiv);

		//	kpiIndicatorChart(request, response, indicatorId, chartDiv);
		}		else if (action.equals("rpakpi.kpiActKk")){
			CommonMessage.debugMsg("am entering in to the new KPI Check Servlet");
		    PrintWriter out=response.getWriter();
		    String kpiId=request.getParameter("kpiId");
		  
		    CommonFilter commonFilter=populateCommonFilter(request, "KPICommonFilter", false);
		    commonFilter.setKey(kpiId);
		    
		    List<String []>fnlncount=kpiTlActualKkService.getrpakpidata(commonFilter);
		    JSONObject jsonObject=new JSONObject();
		    jsonObject.put("kpikeyid", fnlncount);
		    out.println(jsonObject);
			}
		
		else if (action.equals("exportChart.kpiActKk")) {
			
		       
			String type = request.getParameter("type");
			String yr =request.getParameter("yr");
			String chartDataStr = request.getParameter("chartData");
			String indicatorid  = request.getParameter("indicatorid");
			CommonMessage.debugMsg("indicatorid :"+indicatorid );
			String calyr = request.getParameter("calyear");
			String flid = request.getParameter("flid");
			String freq = request.getParameter("frequency");
			//String actvalue = request.getParameter("actvalue");
			//CommonMessage.debugMsg("actvalue :"+actvalue );
			String multiple = request.getParameter("multiple");
			
			String format = ExcelUtils.getFormat(request);
			//String year=kpiTlActualKkService.getKPICalendaryr(chartid.trim());
			String path = UIUtils.getExcelTemplatePath(request);
			
			
		/*	List<String[]> Kpid=kpiTlActualKkService.getKPIkeyid(chartid.trim(),flid,calyr,freq.trim());//FLID","Year","Freq" for future implementation if required
			
			 StringBuffer Kpilists = new StringBuffer();
			
			int i;
			
			if(Kpid!=null&&Kpid.size()!=0)
			{
			    for(i=0;i<=Kpid.size()-1;i++)
			    {
			    	
			    	Kpilists.append("'"+Kpid.get(i)[0]+"'");
			    	
			    	if ( i != Kpid.size()-1){
			    		
			    		Kpilists.append(", ");
			          }
			    	
			     }
			}
			
			
			List<String[]> remarks=null;
			
			if(Kpilists!=null&&Kpilists.length()!=0)
			{	
			remarks=kpiTlActualKkService.getIndicatorRemarks(Kpilists.toString());
			}
			else{
				remarks=null;
			}*/
			/*if(!actvalue.equals("0"))
			{	
			remarks=kpiTlActualKkService.getIndicatorRemarks(actvalue.trim());
			}
			else{
				remarks=null;
			}*/
			//CommonMessage.debugMsg("multiple " + multiple +" fff " + format +  " Chart Data : "+chartDataStr);
			List<String[]> remarks=null;
			//List<String[]> kpiRemarksData = kpiTlActualKkService
			//		.getKPIRemarksReport(commonFilter);
			//remarks=kpiTlActualKkService.getIndicatorRemarks(indicatorid.trim());
			
			// Safe-check for null or empty indicatorid
			// sriram 24-Nov-2025
			if (indicatorid != null && !indicatorid.trim().isEmpty()) {
			    remarks = kpiTlActualKkService.getIndicatorRemarks(indicatorid.trim());
			} else {
			    CommonMessage.debugMsg("⚠️ indicatorid is NULL or EMPTY in exportChart.kpiActKk");
			    remarks = new ArrayList<>(); // return empty list instead of null to avoid later NPEs
			}
			Workbook wb = null;
			String title ="";
			try {
			    if (!"Y".equals(multiple)) {
			        JSONObject jsonObject = JSONObject.fromString(chartDataStr);
			        JSONObject chartObj = (JSONObject) jsonObject.get("chartDatas");
			        JSONObject headersObj = (JSONObject) chartObj.get("headers");
			        title = headersObj.getString("title");
			        if (UIUtils.isValidKeyId(title))
			            title = title.replace(" ", "");

			        wb = fillExcValues(jsonObject, path, ExcelUtils.REPORT_FORMAT_EXL_2007, remarks);

			        // Write to temp file then stream to response
			        java.io.File tempFile = java.io.File.createTempFile("kpi_chart_", ".xlsx");
			        try {
			            java.io.FileOutputStream fos = new java.io.FileOutputStream(tempFile);
			            wb.write(fos);
			            fos.close();
			            wb.close();

			            java.io.FileInputStream fis = new java.io.FileInputStream(tempFile);
			            byte[] excelBytes = new byte[(int) tempFile.length()];
			            fis.read(excelBytes);
			            fis.close();

			            String fileName = title + ".xlsx";
			            response.reset();
			            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			            response.setContentLength(excelBytes.length);

			            java.io.OutputStream out = response.getOutputStream();
			            out.write(excelBytes);
			            out.flush();

			            CommonMessage.debugMsg(">>> ✅ Excel written successfully. Size: " + excelBytes.length + " bytes");

			        } finally {
			            if (tempFile.exists()) {
			                tempFile.delete();
			            }
			        }

			        return;

			    } else {
			        String fileName = request.getParameter("fileName");
			        title = fileName;
			        JSONArray jsonArray = JSONArray.fromString(chartDataStr);
			        wb = createMultipleGraph(jsonArray, path, format);

			        // ✅ writeToResponse ONLY in else branch
			        ExcelUtils.writeToResponse(response, wb, title, format);
			    }

			} catch (Exception e) {
			    e.printStackTrace();
			}
			
		}

		
		/*
		 * else if (action.equals("exportChart.kpiActKk")) {
		 * 
		 * 
		 * String type = request.getParameter("type"); String yr
		 * =request.getParameter("yr"); String chartDataStr =
		 * request.getParameter("chartData"); String indicatorid =
		 * request.getParameter("indicatorid");
		 * CommonMessage.debugMsg("indicatorid :"+indicatorid ); String calyr =
		 * request.getParameter("calyear"); String flid = request.getParameter("flid");
		 * String freq = request.getParameter("frequency"); //String actvalue =
		 * request.getParameter("actvalue");
		 * //CommonMessage.debugMsg("actvalue :"+actvalue ); String multiple =
		 * request.getParameter("multiple");
		 * 
		 * String format = ExcelUtils.getFormat(request); //String
		 * year=kpiTlActualKkService.getKPICalendaryr(chartid.trim()); String path =
		 * UIUtils.getExcelTemplatePath(request);
		 * 
		 * 
		 * 
		 * //CommonMessage.debugMsg("multiple " + multiple +" fff " + format +
		 * " Chart Data : "+chartDataStr); List<String[]> remarks=null; //List<String[]>
		 * kpiRemarksData = kpiTlActualKkService // .getKPIRemarksReport(commonFilter);
		 * //remarks=kpiTlActualKkService.getIndicatorRemarks(indicatorid.trim());
		 * 
		 * // Safe-check for null or empty indicatorid // sriram 24-Nov-2025 if
		 * (indicatorid != null && !indicatorid.trim().isEmpty()) { remarks =
		 * kpiTlActualKkService.getIndicatorRemarks(indicatorid.trim()); } else {
		 * CommonMessage.
		 * debugMsg("⚠️ indicatorid is NULL or EMPTY in exportChart.kpiActKk"); remarks
		 * = new ArrayList<>(); // return empty list instead of null to avoid later NPEs
		 * } Workbook wb = null; String title =""; try { if( ! "Y".equals(multiple)){
		 * JSONObject jsonObject =JSONObject.fromString(chartDataStr); //
		 * CommonMessage.debugMsg("jsonObject1" +jsonObject); JSONObject chartObj =
		 * (JSONObject) jsonObject.get("chartDatas");
		 * //CommonMessage.debugMsg("chartObj1" +chartObj); JSONObject headersObj =
		 * (JSONObject) chartObj.get("headers"); //CommonMessage.debugMsg("headersObj1"
		 * +headersObj); title = headersObj.getString("title");
		 * if(UIUtils.isValidKeyId(title)) title = title.replace(" ", ""); wb =
		 * fillExcValues(jsonObject,path,format,remarks); }else{ String fileName =
		 * request.getParameter("fileName"); title =fileName; JSONArray jsonArray
		 * =JSONArray.fromString(chartDataStr);
		 * //CommonMessage.debugMsg("else-jsonArray" +jsonArray); wb =
		 * createMultipleGraph(jsonArray,path,format); }
		 * 
		 * ExcelUtils.writeToResponse(response, wb, title,format ); } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * }
		 */
			
			
		/** END **/
	}

	

	/*private void kpiIndicatorChartDM(HttpServletRequest request, HttpServletResponse response, String indicatorId,
			String chartDiv) throws Exception {
		CommonMessage.debugMsg("inside of kpiIndicatorChartDM ");
		String rptType = request.getParameter("rptType");
		String monthYear = request.getParameter("monthYear");
		String type = request.getParameter("type");
	//	String monthid = request.getParameter("month");
		String monthid = request.getParameter("monthid");
		CommonMessage.debugMsg("rptType"+rptType);
		CommonMessage.debugMsg("monthYear"+monthYear);
		CommonMessage.debugMsg("type"+type);
		CommonMessage.debugMsg("month in kpichart"+monthid);
		
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		ColumnChart columnChart = new ColumnChart();
		String fromdash = request.getParameter("from");
		CommonFilter commonFilter = new CommonFilter();
		commonFilter.setType(type);
		 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
		 commonFilter= 	FilterValues.getSafty(request, commonFilter);
		commonFilter.setToMonth(monthid);
		KpiTlActualKk kpiTlActualKk = (KpiTlActualKk) httpSession
				.getAttribute("newKpiTlActualKk");
		if (null == kpiTlActualKk)
			kpiTlActualKk = new KpiTlActualKk();
		kpiTlActualKk.setKaukIndicatorid(indicatorId);
		if ("dashbd".equals(fromdash)) {
			kpiTlActualKk.setKaukIsactual("Y");
			kpiTlActualKk.setKaukTempfield1("dashbd");
		}
		//commonFilter.setFromMonth(monthid);
		CommonMessage.debugMsg(" Constants.passNullDate " + Constants.passNullDate + " commonFilter.getFromMonth() " +  commonFilter.getFromMonth());
		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			 
		//	CommonMessage.debugMsg("chkt htbdfkbsdkbjhfv common filter");
		
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-6).substring(3,11));
			  commonFilter.setToMonth(monthid);
			  //commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  }
		GridParams gridParams;
		gridParams = new GridParams();
		//kpiTlActualKk.setKaukMonthyear("");
		//kpiTlActualKk.setKaukFreqtype("M");
		// CommonMessage.debugMsg("   indickpimonthYear :"+monthYear);
		//if (CommonFunctions.isValidKeyId(monthYear)) {
			//kpiTlActualKk.setKaukMonthyear(monthYear);
			//kpiTlActualKk.setKaukFreqtype("D");
		//}
		//if (CommonFunctions.isValidKeyId(monthid)) {
			kpiTlActualKk.setKaukMonthyear(monthYear);
			kpiTlActualKk.setKaukFreqtype("DM");
			//commonFilter.setTemp("rptType=" );
		//}
		commonFilter.setTemp("rptType=" +rptType);
		
		List<String[]> kpiIndiList = kpiTlActualKkService.getCostOfQualitygridDataNewDM(kpiTlActualKk, gridParams,commonFilter);	
		CommonMessage.debugMsg("Chart query checking");
		String[] month = null;
		String[] data = null;
		List<String> xCategories = new ArrayList<String>();
		// List<String> xCategoriesBar = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		ChartSeries actualSeriesColumn = new ChartSeries();
		List<Double> actualDataList = new ArrayList<Double>();
		if("M".equals(kpiTlActualKk.getKaukFreqtype())){
			String tmpActualData = kpiIndiList.get(2)[16]; // changed by karthikeyan.b from 15 to 17 for position changed in Query
			tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData.substring(tmpActualData.indexOf(";") + 1);

			if (!UIUtils.isValidKeyId(tmpActualData))
				actualDataList.add(Double.parseDouble("0"));
			else
				actualDataList.add(Double.parseDouble(tmpActualData));

			tmpActualData = kpiIndiList.get(2)[16];// changed by karthikeyan.b from 16 to 18 for position changed in Query
			tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData.substring(tmpActualData.indexOf(";") + 1);

			if (!UIUtils.isValidKeyId(tmpActualData))
				actualDataList.add(Double.parseDouble("0"));
			else
				actualDataList.add(Double.parseDouble(tmpActualData));

			CommonMessage.debugMsg("xCategories1 : " + xCategories);
			actualSeriesColumn.setName("Previous-Years");
			actualSeriesColumn.setData(actualDataList);
			actualSeriesColumn.setType(ChartTypes.COLUMN);
			chartSeriesList.add(actualSeriesColumn);// adding data to chart to be dispalyed 	

		}
		
		month = kpiIndiList.get(0);
		CommonMessage.debugMsg("month: "+month);
		for (String m : month) {
			CommonMessage.debugMsg("M: " +m);			
		}
		int monthlen = month.length;
		String currentMonth = CommonFunctions.getCurrentMonth() + "-" + CommonFunctions.getCurrentYear();
		CommonMessage.debugMsg("currentMonth: "+currentMonth);
		if ("D".equals(kpiTlActualKk.getKaukFreqtype())) {
			currentMonth = CommonFunctions.getDateWithFormat("dd-MMM-yy");
	}
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();// declaring variable for Y axis
		for (int j = 2; j < kpiIndiList.size(); j++) {
			ChartSeries chartSeriesLine = new ChartSeries();
			List<Double> dataLine = new ArrayList<Double>();
			data = kpiIndiList.get(j);
			
			for (String d : data) {
				CommonMessage.debugMsg("data[])--" + d);
			}
			String[] datasplit = null;
			CommonMessage.debugMsg("data[12])--" + data[12]);
			CommonMessage.debugMsg("Month Length " + monthlen);
			if ("Quarterly".equals(data[12])) {
				for (int i = 16; i < monthlen-1; i++) {
					CommonMessage.debugMsg("1");
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
					CommonMessage.debugMsg("2");
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 14 || i == 15)) {
									CommonMessage.debugMsg("3");
									CommonMessage.debugMsg("actualDataList.adfghgtfhghdfhfhhhd(null))");
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								CommonMessage.debugMsg("3Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										
										month[i].toUpperCase())) {
									CommonMessage.debugMsg("4");
									monthlen = i + 1;
								}
							}
						}
						CommonMessage.debugMsg("5");
						if (i == 14 || i == 15) {
							CommonMessage.debugMsg("6");
							if (j == 2) {
								CommonMessage.debugMsg("7");
								if (UIUtils.isValidKeyId(data[i])) {
									CommonMessage.debugMsg("8");
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										CommonMessage.debugMsg("9");
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} 
									else {
										CommonMessage.debugMsg("10");
										dataLine.add(0.0);
									}
								} 
								//CommonMessage.debugMsg("4");
								else
									CommonMessage.debugMsg("11");
									dataLine.add(0.0);
							} else
								CommonMessage.debugMsg("12");
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							CommonMessage.debugMsg("13");
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[0]));
						} else {
							CommonMessage.debugMsg("14");
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}
			} else if ("Monthly".equals(data[12])) {
				
				for (int i = 16; i < monthlen-1; i++) {
					CommonMessage.debugMsg("1");
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
					CommonMessage.debugMsg("2");
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 14 || i == 15)) {
									CommonMessage.debugMsg("3");
									CommonMessage.debugMsg("actualDataList.adfghgtfhghdfhfhhhd(null))");
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								CommonMessage.debugMsg("3Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										
										month[i].toUpperCase())) {
									CommonMessage.debugMsg("4");
									monthlen = i + 1;
								}
							}
						}
						CommonMessage.debugMsg("5");
						if (i == 14 || i == 15) {
							CommonMessage.debugMsg("6");
							if (j == 2) {
								CommonMessage.debugMsg("7");
								if (UIUtils.isValidKeyId(data[i])) {
									CommonMessage.debugMsg("8");
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										CommonMessage.debugMsg("9");
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} 
									else {
										CommonMessage.debugMsg("10");
										dataLine.add(0.0);
									}
								} 
								//CommonMessage.debugMsg("4");
								else
									CommonMessage.debugMsg("11");
									dataLine.add(0.0);
							} else
								CommonMessage.debugMsg("12");
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							CommonMessage.debugMsg("13");
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[0]));
						} else {
							CommonMessage.debugMsg("14");
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}

			} else if ("Half Yearly".equals(data[12])) {
				for (int i = 16; i < monthlen-1; i++) {
					CommonMessage.debugMsg("1");
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
					CommonMessage.debugMsg("2");
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 14 || i == 15)) {
									CommonMessage.debugMsg("3");
									CommonMessage.debugMsg("actualDataList.adfghgtfhghdfhfhhhd(null))");
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								CommonMessage.debugMsg("3Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										
										month[i].toUpperCase())) {
									CommonMessage.debugMsg("4");
									monthlen = i + 1;
								}
							}
						}
						CommonMessage.debugMsg("5");
						if (i == 14 || i == 15) {
							CommonMessage.debugMsg("6");
							if (j == 2) {
								CommonMessage.debugMsg("7");
								if (UIUtils.isValidKeyId(data[i])) {
									CommonMessage.debugMsg("8");
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										CommonMessage.debugMsg("9");
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} 
									else {
										CommonMessage.debugMsg("10");
										dataLine.add(0.0);
									}
								} 
								//CommonMessage.debugMsg("4");
								else
									CommonMessage.debugMsg("11");
									dataLine.add(0.0);
							} else
								CommonMessage.debugMsg("12");
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							CommonMessage.debugMsg("13");
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[0]));
						} else {
							CommonMessage.debugMsg("14");
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}
			}else if ("Yearly".equals(data[12])) {
				for (int i = 16; i < monthlen-1; i++) {
					CommonMessage.debugMsg("1");
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
					CommonMessage.debugMsg("2");
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 14 || i == 15)) {
									CommonMessage.debugMsg("3");
									CommonMessage.debugMsg("actualDataList.adfghgtfhghdfhfhhhd(null))");
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								CommonMessage.debugMsg("3Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										
										month[i].toUpperCase())) {
									CommonMessage.debugMsg("4");
									monthlen = i + 1;
								}
							}
						}
						CommonMessage.debugMsg("5");
						if (i == 14 || i == 15) {
							CommonMessage.debugMsg("6");
							if (j == 2) {
								CommonMessage.debugMsg("7");
								if (UIUtils.isValidKeyId(data[i])) {
									CommonMessage.debugMsg("8");
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										CommonMessage.debugMsg("9");
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} 
									else {
										CommonMessage.debugMsg("10");
										dataLine.add(0.0);
									}
								} 
								//CommonMessage.debugMsg("4");
								else
									CommonMessage.debugMsg("11");
									dataLine.add(0.0);
							} else
								CommonMessage.debugMsg("12");
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							CommonMessage.debugMsg("13");
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[0]));
						} else {
							CommonMessage.debugMsg("14");
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}
			}else if ("Daily".equals(data[12])) {
				
				for (int i = 16; i < monthlen-1; i++) {
					CommonMessage.debugMsg("1");
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
					CommonMessage.debugMsg("2");
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 14 || i == 15)) {
									CommonMessage.debugMsg("3");
									CommonMessage.debugMsg("actualDataList.adfghgtfhghdfhfhhhd(null))");
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								CommonMessage.debugMsg("3Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										
										month[i].toUpperCase())) {
									CommonMessage.debugMsg("4");
									monthlen = i + 1;
								}
							}
						}
						CommonMessage.debugMsg("5");
						if (i == 14 || i == 15) {
							CommonMessage.debugMsg("6");
							if (j == 2) {
								CommonMessage.debugMsg("7");
								if (UIUtils.isValidKeyId(data[i])) {
									CommonMessage.debugMsg("8");
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										CommonMessage.debugMsg("9");
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} 
									else {
										CommonMessage.debugMsg("10");
										dataLine.add(0.0);
									}
								} 
								//CommonMessage.debugMsg("4");
								else
									CommonMessage.debugMsg("11");
									dataLine.add(0.0);
							} else
								CommonMessage.debugMsg("12");
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							CommonMessage.debugMsg("13");
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[0]));
						} else {
							CommonMessage.debugMsg("14");
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}
			}
			else if ("Fortnight".equals(data[12])) {
				for (int i = 16; i < monthlen-1; i++) {
					CommonMessage.debugMsg("1");
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
					CommonMessage.debugMsg("2");
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 14 || i == 15)) {
									CommonMessage.debugMsg("3");
									CommonMessage.debugMsg("actualDataList.adfghgtfhghdfhfhhhd(null))");
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								CommonMessage.debugMsg("3Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										
										month[i].toUpperCase())) {
									CommonMessage.debugMsg("4");
									monthlen = i + 1;
								}
							}
						}
						CommonMessage.debugMsg("5");
						if (i == 14 || i == 15) {
							CommonMessage.debugMsg("6");
							if (j == 2) {
								CommonMessage.debugMsg("7");
								if (UIUtils.isValidKeyId(data[i])) {
									CommonMessage.debugMsg("8");
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										CommonMessage.debugMsg("9");
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} 
									else {
										CommonMessage.debugMsg("10");
										dataLine.add(0.0);
									}
								} 
								//CommonMessage.debugMsg("4");
								else
									CommonMessage.debugMsg("11");
									dataLine.add(0.0);
							} else
								CommonMessage.debugMsg("12");
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							CommonMessage.debugMsg("13");
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[0]));
						} else {
							CommonMessage.debugMsg("14");
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}
			}else if ("Weekly".equals(data[12])) {
				for (int i = 16; i < monthlen-1; i++) {
					CommonMessage.debugMsg("1");
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
					CommonMessage.debugMsg("2");
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 14 || i == 15)) {
									CommonMessage.debugMsg("3");
									CommonMessage.debugMsg("actualDataList.adfghgtfhghdfhfhhhd(null))");
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								CommonMessage.debugMsg("3Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										
										month[i].toUpperCase())) {
									CommonMessage.debugMsg("4");
									monthlen = i + 1;
								}
							}
						}
						CommonMessage.debugMsg("5");
						if (i == 14 || i == 15) {
							CommonMessage.debugMsg("6");
							if (j == 2) {
								CommonMessage.debugMsg("7");
								if (UIUtils.isValidKeyId(data[i])) {
									CommonMessage.debugMsg("8");
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										CommonMessage.debugMsg("9");
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} 
									else {
										CommonMessage.debugMsg("10");
										dataLine.add(0.0);
									}
								} 
								//CommonMessage.debugMsg("4");
								else
									CommonMessage.debugMsg("11");
									dataLine.add(0.0);
							} else
								CommonMessage.debugMsg("12");
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							CommonMessage.debugMsg("13");
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[0]));
						} else {
							CommonMessage.debugMsg("14");
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}
			}

			String name = "";
			// chartSeriesLine.setName(data[11]);
			if (j == 2) {
				name = "Actual";
			} else {
				name = "Target";
			}
			chartSeriesLine.setName(name);
			chartSeriesLine.setData(dataLine);
			chartSeriesLine.setType(ChartTypes.LINE);
			
			chartSeriesList.add(chartSeriesLine);// adding data to chart to be
													// dispalyed
			if ("Target".equals(data[10])) {
				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				yAxis.getTitle().setText(data[13]);// declaring caption for Y
													// axis

				chartYAxis.add(yAxis);// adding Yaxis to the chart
			}
		}// end outer for

		CommonMessage.debugMsg("Fid:"
				+ (String) httpSession.getAttribute("FLID"));
		String flid = (String) httpSession.getAttribute("FLID");
		CommonMessage.debugMsg("Fid:" + flid);
		String fnlnDescription = kpiTlActualKkService.getFnlnDescription(flid);
		List<String> xCategoriesTemp = new ArrayList<String>();
		
		  for (String value : xCategories) {
		  CommonMessage.debugMsg("value.length()"+value.length());
		  if(value.length()==11){ xCategoriesTemp.add(value.substring(0, 2));
		  
		  }else{ xCategoriesTemp.add(value); } }
		 
		xCategories=xCategoriesTemp;
		String title = "";
		
		//  if(monthYear==null){
			//  title=xCategories.get(2).substring(4); 
		  //}else{
			  title=monthid; 
	//	  }
		 
		CommonMessage.debugMsg("xCategories" + xCategories);
		// CommonMessage.debugMsg("xCategoriesTemp" +xCategoriesTemp);
		// columnChart.setHeight(280);
		JSONObject chartObj = columnChart.drawChart(xCategories,
				chartSeriesList, fnlnDescription + " - "
						+ kpiIndiList.get(1)[11] , "",
				chartYAxis);
		UIUtils.dashBoardSetChartObject(request, chartObj);
		chartObj.put("chartDiv", chartDiv);
		out.println(chartObj);
		out.close();
		
		
	}

	*/

	private void kpiIndicatorChartDM(HttpServletRequest request, HttpServletResponse response, String indicatorId,
			String chartDiv) throws Exception {
		CommonMessage.debugMsg("inside kpiIndicatorChartDM========================================================================================");
		String rptType = request.getParameter("rptType");
		String monthYear = request.getParameter("monthYear");
		String type = request.getParameter("type");
	//	String monthid = request.getParameter("month");
		String monthid = request.getParameter("monthid");
		
		
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		ColumnChart columnChart = new ColumnChart();
		String fromdash = request.getParameter("from");
		CommonFilter commonFilter = new CommonFilter();
		commonFilter.setType(type);
		 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
		 commonFilter= 	FilterValues.getSafty(request, commonFilter);
		commonFilter.setToMonth(monthid);
		KpiTlActualKk kpiTlActualKk = (KpiTlActualKk) httpSession
				.getAttribute("newKpiTlActualKk");
		if (null == kpiTlActualKk)
			kpiTlActualKk = new KpiTlActualKk();
		kpiTlActualKk.setKaukIndicatorid(indicatorId);
		if ("dashbd".equals(fromdash)) {
			kpiTlActualKk.setKaukIsactual("Y");
			kpiTlActualKk.setKaukTempfield1("dashbd");
		}
		//commonFilter.setFromMonth(monthid);
		CommonMessage.debugMsg(" Constants.passNullDate " + Constants.passNullDate + " commonFilter.getFromMonth() " +  commonFilter.getFromMonth());
		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			 
		//	CommonMessage.debugMsg("chkt htbdfkbsdkbjhfv common filter");
		
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-6).substring(3,11));
			  commonFilter.setToMonth(monthid);
			  //commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  }
		GridParams gridParams;
		gridParams = new GridParams();
		//kpiTlActualKk.setKaukMonthyear("");
		//kpiTlActualKk.setKaukFreqtype("M");
		// CommonMessage.debugMsg("   indickpimonthYear :"+monthYear);
		//if (CommonFunctions.isValidKeyId(monthYear)) {
			//kpiTlActualKk.setKaukMonthyear(monthYear);
			//kpiTlActualKk.setKaukFreqtype("D");
		//}
		//if (CommonFunctions.isValidKeyId(monthid)) {
			kpiTlActualKk.setKaukMonthyear(monthYear);
			kpiTlActualKk.setKaukFreqtype("DM");
			//commonFilter.setTemp("rptType=" );
		//}
		commonFilter.setTemp("rptType=" +rptType);
		
		List<String[]> kpiIndiList = kpiTlActualKkService.getCostOfQualitygridDataNewDM(kpiTlActualKk, gridParams,commonFilter);	
	
		String[] month = null;
		String[] data = null;
		List<String> xCategories = new ArrayList<String>();
		// List<String> xCategoriesBar = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		ChartSeries actualSeriesColumn = new ChartSeries();
		List<Double> actualDataList = new ArrayList<Double>();
		if("M".equals(kpiTlActualKk.getKaukFreqtype())){
			String tmpActualData = kpiIndiList.get(2)[16]; 
			tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData.substring(tmpActualData.indexOf(";") + 1);

			if (!UIUtils.isValidKeyId(tmpActualData))
				actualDataList.add(Double.parseDouble("0"));
			else
				actualDataList.add(Double.parseDouble(tmpActualData));

			tmpActualData = kpiIndiList.get(2)[16];// changed by karthikeyan.b from 16 to 18 for position changed in Query
			tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData.substring(tmpActualData.indexOf(";") + 1);

			if (!UIUtils.isValidKeyId(tmpActualData))
				actualDataList.add(Double.parseDouble("0"));
			else
				actualDataList.add(Double.parseDouble(tmpActualData));

			CommonMessage.debugMsg("xCategories1 : " + xCategories);
			actualSeriesColumn.setName("Previous-Years");
			actualSeriesColumn.setData(actualDataList);
			actualSeriesColumn.setType(ChartTypes.LINE);
			chartSeriesList.add(actualSeriesColumn);// adding data to chart to be dispalyed 	

		}
		
		
		
		month = kpiIndiList.get(0);
		CommonMessage.debugMsg("month: "+month);
		/*for (String m : month) {
			CommonMessage.debugMsg("M: " +m);			
		}*/
		int monthlen = month.length;
		String currentMonth = CommonFunctions.getCurrentMonth() + "-" + CommonFunctions.getCurrentYear();
		CommonMessage.debugMsg("currentMonth: "+currentMonth);
		if ("D".equals(kpiTlActualKk.getKaukFreqtype())) {
			currentMonth = CommonFunctions.getDateWithFormat("dd-MMM-yy");
	}
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();// declaring variable for Y axis
		for (int j = 2; j < kpiIndiList.size(); j++) {
			ChartSeries chartSeriesLine = new ChartSeries();
			List<Double> dataLine = new ArrayList<Double>();
			data = kpiIndiList.get(j);
			/*
			for (String d : data) {
				CommonMessage.debugMsg("data[])--" + d);
			}*/
			String[] datasplit = null;
			CommonMessage.debugMsg("data[12])--" + data[12]);
			
			CommonMessage.debugMsg("Month Length " + monthlen);
			
			if ("Quarterly".equals(data[12])) {
				
				CommonMessage.debugMsg("inside Quarterly 111111111111111111111");
				
				 for (int i = 17; i < monthlen-1; i++) {
						
					 
						CommonMessage.debugMsg(i + " " + j);
						//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
						
							if (j == 2) {
								if (monthlen > i) {
									if (!(i == 15 || i == 16)) {
										
										actualDataList.add(0.0);
									}
									CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
									
									xCategories.add(month[i]);
									if (currentMonth.toUpperCase().equals(
											
											month[i].toUpperCase())) {
										
										monthlen = i + 1;
									}
								}
							}
							
							if (i == 15 || i == 16) {
							
								if (j == 2) {
								
									if (UIUtils.isValidKeyId(data[i])) {
										
										datasplit = data[i].split(";");
										if (datasplit.length > 1) {
											
											dataLine.add(Double
													.parseDouble(datasplit[1]));
										} 
										else {
											
											dataLine.add(0.0);
										}
									} 
									//CommonMessage.debugMsg("4");
									else
										
										dataLine.add(0.0);
								} else
									
									dataLine.add(0.0);

							} else if (!"0".equals(data[i])) {
								
								datasplit = data[i].split(";");
								dataLine.add(Double.parseDouble(datasplit[0]));
							} else {
								
								dataLine.add(Double.parseDouble(data[i]));
							}
												
				}
			} else if ("Monthly".equals(data[12])) {
				
				CommonMessage.debugMsg("inside Monthly 2222222222222222222222");
				
				 for (int i = 17; i < monthlen-1; i++) {
						
						CommonMessage.debugMsg(i + " " + j);
						//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
						
							if (j == 2) {
								if (monthlen > i) {
									if (!(i == 15 || i == 16)) {
										
										actualDataList.add(0.0);
									}
									CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
									
									xCategories.add(month[i]);
									if (currentMonth.toUpperCase().equals(
											
											month[i].toUpperCase())) {
										
										monthlen = i + 1;
									}
								}
							}
							
							if (i == 15 || i == 16) {
							
								if (j == 2) {
								
									if (UIUtils.isValidKeyId(data[i])) {
										
										datasplit = data[i].split(";");
										if (datasplit.length > 1) {
											
											dataLine.add(Double
													.parseDouble(datasplit[1]));
										} 
										else {
											
											dataLine.add(0.0);
										}
									} 
									//CommonMessage.debugMsg("4");
									else
										
										dataLine.add(0.0);
								} else
									
									dataLine.add(0.0);

							} else if (!"0".equals(data[i])) {
								
								datasplit = data[i].split(";");
								dataLine.add(Double.parseDouble(datasplit[0]));
							} else {
								
								dataLine.add(Double.parseDouble(data[i]));
							}
										}

			} else if ("Half Yearly".equals(data[12])) {
				
				CommonMessage.debugMsg("inside Half Yearly 333333333333333333333");
				
				 for (int i = 17; i < monthlen-1; i++) {
						
						CommonMessage.debugMsg(i + " " + j);
						//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
						
							if (j == 2) {
								if (monthlen > i) {
									if (!(i == 15 || i == 16)) {
										
										actualDataList.add(0.0);
									}
									CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
									
									xCategories.add(month[i]);
									if (currentMonth.toUpperCase().equals(
											
											month[i].toUpperCase())) {
										
										monthlen = i + 1;
									}
								}
							}
							
							if (i == 15 || i == 16) {
							
								if (j == 2) {
								
									if (UIUtils.isValidKeyId(data[i])) {
										
										datasplit = data[i].split(";");
										if (datasplit.length > 1) {
											
											dataLine.add(Double
													.parseDouble(datasplit[1]));
										} 
										else {
											
											dataLine.add(0.0);
										}
									} 
									//CommonMessage.debugMsg("4");
									else
										
										dataLine.add(0.0);
								} else
									
									dataLine.add(0.0);

							} else if (!"0".equals(data[i])) {
								
								datasplit = data[i].split(";");
								dataLine.add(Double.parseDouble(datasplit[0]));
							} else {
								
								dataLine.add(Double.parseDouble(data[i]));
							}
										 }
			}else if ("Yearly".equals(data[12])) {
				
				CommonMessage.debugMsg("inside Yearly 444444444444444444444");
				
				 for (int i = 17; i < monthlen-1; i++) {
						
						CommonMessage.debugMsg(i + " " + j);
						//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
						
							if (j == 2) {
								if (monthlen > i) {
									if (!(i == 15 || i == 16)) {
										
										actualDataList.add(0.0);
									}
									CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
									
									xCategories.add(month[i]);
									if (currentMonth.toUpperCase().equals(
											
											month[i].toUpperCase())) {
										
										monthlen = i + 1;
									}
								}
							}
							
							if (i == 15 || i == 16) {
							
								if (j == 2) {
								
									if (UIUtils.isValidKeyId(data[i])) {
										
										datasplit = data[i].split(";");
										if (datasplit.length > 1) {
											
											dataLine.add(Double
													.parseDouble(datasplit[1]));
										} 
										else {
											
											dataLine.add(0.0);
										}
									} 
									//CommonMessage.debugMsg("4");
									else
										
										dataLine.add(0.0);
								} else
									
									dataLine.add(0.0);

							} else if (!"0".equals(data[i])) {
								
								datasplit = data[i].split(";");
								dataLine.add(Double.parseDouble(datasplit[0]));
							} else {
								
								dataLine.add(Double.parseDouble(data[i]));
							}
												
				}
			}else if ("Daily".equals(data[12])) {
				
				CommonMessage.debugMsg("inside Yearly 444444444444444444444");
			
				for (int i = 17; i < monthlen-1; i++) {
					
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
					
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 15 || i == 16)) {
									
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										
										month[i].toUpperCase())) {
									
									monthlen = i + 1;
								}
							}
						}
						
						if (i == 15 || i == 16) {
						
							if (j == 2) {
							
								if (UIUtils.isValidKeyId(data[i])) {
									
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} 
									else {
										
										dataLine.add(0.0);
									}
								} 
								//CommonMessage.debugMsg("4");
								else
									
									dataLine.add(0.0);
							} else
								
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[0]));
						} else {
							
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}
			}
			else if ("Fortnight".equals(data[12])) {
				
				CommonMessage.debugMsg("inside Fortnight 5555555555555555555555");
				
				 for (int i = 17; i < monthlen-1; i++) {
						
						CommonMessage.debugMsg(i + " " + j);
						//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
						
							if (j == 2) {
								if (monthlen > i) {
									if (!(i == 15 || i == 16)) {
										
										actualDataList.add(0.0);
									}
									CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
									
									xCategories.add(month[i]);
									if (currentMonth.toUpperCase().equals(
											
											month[i].toUpperCase())) {
										
										monthlen = i + 1;
									}
								}
							}
							
							if (i == 15 || i == 16) {
							
								if (j == 2) {
								
									if (UIUtils.isValidKeyId(data[i])) {
										
										datasplit = data[i].split(";");
										if (datasplit.length > 1) {
											
											dataLine.add(Double
													.parseDouble(datasplit[1]));
										} 
										else {
											
											dataLine.add(0.0);
										}
									} 
									//CommonMessage.debugMsg("4");
									else
										
										dataLine.add(0.0);
								} else
									
									dataLine.add(0.0);

							} else if (!"0".equals(data[i])) {
								
								datasplit = data[i].split(";");
								dataLine.add(Double.parseDouble(datasplit[0]));
							} else {
								
								dataLine.add(Double.parseDouble(data[i]));
							}
						
				}
			}else if ("Weekly".equals(data[12])) {
				
				
				CommonMessage.debugMsg("inside Weekly 6666666666666666666666666");
				
	          for (int i = 17; i < monthlen-1; i++) {
					
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
					
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 15 || i == 16)) {
									
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										
										month[i].toUpperCase())) {
									
									monthlen = i + 1;
								}
							}
						}
						
						if (i == 15 || i == 16) {
						
							if (j == 2) {
							
								if (UIUtils.isValidKeyId(data[i])) {
									
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} 
									else {
										
										dataLine.add(0.0);
									}
								} 
								//CommonMessage.debugMsg("4");
								else
									
									dataLine.add(0.0);
							} else
								
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[0]));
						} else {
							
							dataLine.add(Double.parseDouble(data[i]));
						}
					
				//}
					}
				
			}

			String name = "";
			// chartSeriesLine.setName(data[11]);
			if (j == 2) {
				name = "Actual";
			} else {
				name = "Target";
			}
			chartSeriesLine.setName(name);
			chartSeriesLine.setData(dataLine);
			chartSeriesLine.setType(ChartTypes.LINE);
			
			chartSeriesList.add(chartSeriesLine);// adding data to chart to be
													// dispalyed
			/*if ("Target".equals(data[10])) {
				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				yAxis.getTitle().setText(data[13]);// declaring caption for Y
													// axis

				chartYAxis.add(yAxis);// adding Yaxis to the chart
			}*/
		}// end outer for

		CommonMessage.debugMsg("Fid:"+ (String) httpSession.getAttribute("FLID"));
		String flid = (String) httpSession.getAttribute("FLID");
		CommonMessage.debugMsg("Fid:" + flid);
		String fnlnDescription = kpiTlActualKkService.getFnlnDescription(flid);
		List<String> xCategoriesTemp = new ArrayList<String>();
		
		  for (String value : xCategories) {
		  CommonMessage.debugMsg("value.length()"+value.length());
		  if(value.length()==11){ xCategoriesTemp.add(value.substring(0, 2));
		  
		  }else{ xCategoriesTemp.add(value); } }
		 
		xCategories=xCategoriesTemp;
		String title = "";
		
		//  if(monthYear==null){
			//  title=xCategories.get(2).substring(4); 
		  //}else{
			  title=monthid; 
	//	  }
		 
		CommonMessage.debugMsg("xCategories" + xCategories);
		// CommonMessage.debugMsg("xCategoriesTemp" +xCategoriesTemp);
		// columnChart.setHeight(280);
		JSONObject chartObj = columnChart.drawChart(xCategories,
				chartSeriesList, fnlnDescription + " - "
						+ kpiIndiList.get(1)[11] , "",
				chartYAxis);
		UIUtils.dashBoardSetChartObject(request, chartObj);
		//chartObj.put("chartDiv", chartDiv);
		chartObj.put("chartDiv", chartDiv);
		chartObj.put("indiid", kpiIndiList.get(1)[8]);
		chartObj.put("freq", kpiTlActualKk.getKaukFreqtype());
		chartObj.put("flid", flid);
	    chartObj.put("KPIActual", kpiIndiList.get(2)[17]);
		out.println(chartObj);
		out.close();
		
		
	}

	

	public Workbook createMultipleGraph(JSONArray jsonArray,String path,String format)throws Exception
	{
		//CommonMessage.debugMsg("createMultipleGraph");
		String excelPath = "/multiplesheetgraph_line"  ;//getExcelFile(type); //getChartTemplateName(type);
		CommonMessage.debugMsg( " excelPath : "+path+excelPath);
		InputStream inp = new FileInputStream(path+excelPath+"."+format); 
		//CommonMessage.debugMsg(format + " : "+ExcelUtils.REPORT_FORMAT_EXL_2007);
		Workbook wb = format.equals(ExcelUtils.REPORT_FORMAT_EXL_2007) ?  new XSSFWorkbook(inp):new HSSFWorkbook(inp); 
		inp.close();
		int totalSheet = wb.getNumberOfSheets()-1;
		//CommonMessage.debugMsg("totalSheet:" +totalSheet);
		int remvIndx = jsonArray.length();
		//CommonMessage.debugMsg("jsonArray.length():" +remvIndx);
		String sheetName ="";
		for(int g=0;g < totalSheet  ; g++ ){

			if( g >= jsonArray.length()    ){
				wb.removeSheetAt(remvIndx);
				continue;
			}
			
			JSONObject jsonObject = jsonArray.getJSONObject(g);
			//CommonMessage.debugMsg("jsonObject-2:" +jsonObject);
			JSONObject datasObj = (JSONObject) jsonObject.get("values");
			//CommonMessage.debugMsg("datasObj-2:" +datasObj);
			JSONObject chartObj = (JSONObject) jsonObject.get("chartDatas");
			//CommonMessage.debugMsg("chartObj-2:" +chartObj);
			JSONObject headersObj = (JSONObject) chartObj.get("headers");	
			//CommonMessage.debugMsg("headersObj-2:" +headersObj);
			JSONArray dataArray = datasObj.getJSONArray("datas");
			//CommonMessage.debugMsg("dataArray-2:" +dataArray);
			JSONArray headersArray = headersObj.getJSONArray("colHeaders");
			//CommonMessage.debugMsg("headersArray-2:" +headersArray);
			JSONArray rowTitle = headersObj.getJSONArray("rowHeaders");	
			//CommonMessage.debugMsg("rowTitle-2:" +rowTitle);
			JSONArray datas =  dataArray.getJSONArray(0);
			//CommonMessage.debugMsg("datas-2:" +datas);
			String titleY = headersObj.getString("yAxis");
			//CommonMessage.debugMsg("titleY-2:" +titleY);
			String titleX = headersObj.getString("xAxis");
			//CommonMessage.debugMsg("titleX-2:" +titleX);
			String title = headersObj.getString("title");
			//CommonMessage.debugMsg("title-2:" +title);
		
			
			//Sheet sheet = wb.getSheetAt(g);
			Sheet firstsheet = wb.getSheetAt(g);
			
			
			int colCount =datas.length();
			//CommonMessage.debugMsg("datas.length()-2:" +colCount);
			int totCols = 200;
			int rowStart = 200;
			int flag = rowStart ;
			int noRows = 15; 
			int rowEnds = rowStart+noRows; 
			int dataRowsCount =rowStart + dataArray.length();
			int titleNo =0;
			int arrIndex=0;
			int titleRow = 199;
			int titleCol = 0;
			int xAxisTitleCol = 2 ;
			int yAxisTitleCol = 1 ;
			
			//title = org.apache.commons.lang.StringEscapeUtils.unescapeJava(title);
			
			Row row = firstsheet.createRow(titleRow);
			if( title != null && title.length() > 31 )
				sheetName = title.substring(0,title.length()-1);
			else
				sheetName = title;
			
			//sheetName = sheetName.replaceAll("/","").replaceAll("\\","");
			sheetName = sheetName.replaceAll("[^\\w\\s\\-_]", "");
			
			wb.setSheetName(g, sheetName);
			
			Cell cell = row.getCell(titleCol);  
			if (cell == null)  
			   cell = row.createCell(titleCol);
			
			cell.setCellValue(title);
			
			
			cell = row.getCell(xAxisTitleCol);  
			if (cell == null)  
			   cell = row.createCell(xAxisTitleCol);
			
			cell.setCellValue(UIUtils.isValidKeyId(titleX)?titleX:"");
			
			cell = row.getCell(yAxisTitleCol);  
			if (cell == null)  
			   cell = row.createCell(yAxisTitleCol);
			cell.setCellValue(UIUtils.isValidKeyId(titleY)?titleY:"");
			
			
			
			while(flag<rowEnds)
			{
				row = firstsheet.createRow(flag);
				if(flag >= dataRowsCount ) // || flag < rowFlag)
				{
					row.setZeroHeight(true);
					flag++;
					continue;
				}
				JSONArray val = dataArray.getJSONArray(arrIndex++);
				//CommonMessage.debugMsg("dataArray["+arrIndex+ "]==" +val);
				for(int j=0;j<totCols;j++)
				{	
					if( j <= colCount)
					{
					   cell = row.getCell(j);  
					   if (cell == null)  
						   cell = row.createCell(j);	
					   if(flag==rowStart)
					   {
						   if(j != 0)
							   cell.setCellValue(headersArray.length()>0?headersArray.get(j-1).toString():"");	
					   }
					   else
					   {
						   
						   if(j==0)
						   {
							   cell.setCellValue(rowTitle.get(titleNo).toString());
							   titleNo++;								   
						   }
						   else
						   {
							   if(UIUtils.isValidKeyId(val.getString(j-1)))
								   cell.setCellValue(new Double(val.getDouble(j-1)));
							   else
								   cell.setCellValue(new Double(0));
							   
						   }
					   }
					   	CellStyle cellstyle =wb.createCellStyle();
						cellstyle.setAlignment(HorizontalAlignment.RIGHT);	
						cell.setCellStyle(cellstyle);
					}
					else if( flag == rowStart)
				    {
					   firstsheet.setColumnHidden(j,true);
				    }
				}
				flag++;
				
			}
		}
		return wb;
	}
	
	
	/*private static String getExcelFile(String type)
	{
		if(UIUtils.isValidKeyId(type))
		{
			if(type.equals("line") || type.equals("spline"))
				return  "/LineChart";
			else if(type.equals("LineSecondary"))
				return  "/LineSecondary";
			else if(type.equals("pie"))
				return  "/PieChart";
			else if(type.equals("PieColumn"))
				return  "/PieColChart";
			else if(type.equals("column"))
				return  "/ColChart";
			else if(type.equals("Pareto"))
				return  "/KPIRemarks";
			else
			{
				return  "/KPIRemarks";
			}
		
	}*/
	private static int getRowNo(String type)
	{
		
		if(UIUtils.isValidKeyId(type))
		{
			if(type.equals("line") )
				return  2;
			else if (type.equals("spline"))
				return 2;
			else if(type.equals("LineSecondary"))
				return  2;
			else if(type.equals("pie"))
				return  2;
			else if(type.equals("PieColumn"))
				return  7;
			else if(type.equals("column"))
				return  2;
			else if(type.equals("Pareto"))
				return  2;
		}
		else
			return  2;
		return 2;
	}
	/*
	 * public Workbook fillExcValues(JSONObject jsonObject,String path,String
	 * format, List<String[]> remarks)throws Exception { JSONObject datasObj =
	 * (JSONObject) jsonObject.get("values"); JSONObject chartObj = (JSONObject)
	 * jsonObject.get("chartDatas"); JSONObject headersObj = (JSONObject)
	 * chartObj.get("headers"); JSONArray dataArray =
	 * datasObj.getJSONArray("datas"); JSONArray headersArray =
	 * headersObj.getJSONArray("colHeaders"); JSONArray rowTitle =
	 * headersObj.getJSONArray("rowHeaders"); JSONArray datas =
	 * dataArray.getJSONArray(0); String type = datasObj.getString("type"); String
	 * titleY = headersObj.getString("yAxis"); String titleX =
	 * headersObj.getString("xAxis"); String title = headersObj.getString("title");
	 * //String subtitle = headersObj.getString("subtitle");
	 * //CommonMessage.debugMsg("String subtitle"+subtitle); //
	 * CommonMessage.debugMsg("rowTitle"+title); String excelPath=null;
	 * if(remarks!=null&&remarks.size()!=0) { excelPath
	 * ="/KPIRemarks";//getChartTemplateName(type); }else{ excelPath ="/Pareto"; }
	 * //CommonMessage.debugMsg(type + " excelPath : "+path+excelPath);
	 * 
	 * InputStream inp = new FileInputStream(path+excelPath+"."+format);
	 * //CommonMessage.debugMsg(format + " : "+ExcelUtils.REPORT_FORMAT_EXL_2007);
	 * Workbook wb = format.equals(ExcelUtils.REPORT_FORMAT_EXL_2007) ? new
	 * XSSFWorkbook(inp):new HSSFWorkbook(inp); inp.close(); Sheet sheet =
	 * wb.getSheetAt(1); Sheet firstsheet = wb.getSheetAt(0);
	 * 
	 * 
	 * int colCount =datas.length(); int rowFlag = getRowNo(type); int dataRowsCount
	 * = rowFlag + dataArray.length(); int flag = 0; int titleNo =0; int arrIndex=0;
	 * String titlenew=null;
	 * 
	 * while(flag<200) { Row row = sheet.createRow(flag);
	 * 
	 * if(flag >= dataRowsCount ) // || flag < rowFlag) { if(flag>1){
	 * row.setZeroHeight(true); //sheet.removeRow(row); } flag++; continue; }
	 * for(int j=0;j<200;j++) { if(flag < dataRowsCount) { Cell cell =
	 * row.getCell(j); if (cell == null) cell = row.createCell(j); if( j <=
	 * colCount) { if(flag == 0) { if(j == 0) { cell.setCellValue(title);
	 * if(type.equals("pie")) { int f=flag; while(f<60) { firstsheet.createRow(f);
	 * f++; } } } else if(j==1)
	 * cell.setCellValue(UIUtils.isValidKeyId(titleY)?titleY:""); else if(j==2)
	 * cell.setCellValue(UIUtils.isValidKeyId(titleX)?titleX:""); } else if(flag==1)
	 * { if(j != 0)
	 * cell.setCellValue(headersArray.length()>0?headersArray.get(j-1).toString():""
	 * ); } else { //CommonMessage.debugMsg(flag + " : "+rowFlag); if(flag >=
	 * rowFlag) { // int valNo=(flag-2)%5;
	 * 
	 * JSONArray val = dataArray.getJSONArray(arrIndex); if(j==0) {
	 * if(type.equals("pie")) { Cell fccell = firstsheet.getRow(flag-1).getCell(12);
	 * if (fccell == null) fccell = firstsheet.getRow(flag-1).createCell(12);
	 * fccell.setCellValue(rowTitle.get(titleNo).toString());
	 * 
	 * } cell.setCellValue(rowTitle.get(titleNo).toString()); titleNo++; } else {
	 * if(j==colCount) arrIndex++; if(UIUtils.isValidKeyId(val.getString(j-1)))
	 * cell.setCellValue(new Double(val.getDouble(j-1))); else cell.setCellValue(new
	 * Double(0));
	 * 
	 * if(type.equals("pie")) { Cell fccell =
	 * firstsheet.getRow(flag-1).getCell(j+12); if (fccell == null) fccell =
	 * firstsheet.getRow(flag-1).createCell(j+12);
	 * if(UIUtils.isValidKeyId(val.getString(j-1))) fccell.setCellValue(new
	 * Double(val.getDouble(j-1))); else fccell.setCellValue(new Double(0));
	 * 
	 * } //CommonMessage.debugMsg("Value : "+val.getDouble(j-1)); }
	 * 
	 * }
	 * 
	 * } CellStyle cellstyle =wb.createCellStyle();
	 * cellstyle.setAlignment(HorizontalAlignment.RIGHT);
	 * cell.setCellStyle(cellstyle); } else if( flag == 0) {
	 * sheet.setColumnHidden(j,true); } } } flag++;
	 * 
	 * }
	 * 
	 * 
	 * if(remarks !=null && remarks.size()>0) { int g1; int strRow=36; Cell cell
	 * =null; Cell cell1 =null; for(g1=0;g1<remarks.size();g1++){
	 * 
	 * Row row=null; row= firstsheet.getRow(strRow+g1); if(row==null) { row =
	 * firstsheet.createRow(strRow+g1); }
	 * 
	 * cell = row.getCell(3); cell1 = row.getCell(6);
	 * 
	 * 
	 * if (cell == null || cell1 == null) { cell = row.createCell(3); cell1 =
	 * row.createCell(6);
	 * 
	 * cell.setCellType(CellType.NUMERIC); cell1.setCellType(CellType.STRING); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * cell.setCellType(CellType.NUMERIC); cell1.setCellType(CellType.STRING);
	 * cell.setCellValue(remarks.get(g1)[0]);
	 * cell1.setCellValue(remarks.get(g1)[1]); //
	 * CommonMessage.debugMsg("remarks.get(g1)[0].substring(0,10"+remarks.get(g1)[0]
	 * );
	 * 
	 * //row.setZeroHeight(true); }
	 * 
	 * CellStyle cellstyle2 =wb.createCellStyle();
	 * //cellstyle2.setAlignment(CellStyle.ALIGN_CENTER);
	 * cell.setCellStyle(cellstyle2); cell1.setCellStyle(cellstyle2); //cellstyle.
	 * 
	 * //firstsheet.getRow(44).setZeroHeight(true);
	 * //cellstyle.setBorderLeft((short) 10); // cellstyle.setBorderBottom((short)
	 * 10); //cellstyle.setBorderRight((short) 10); //cell.setCellStyle(cellstyle);
	 * }
	 * 
	 * 
	 * //cell2.setCellStyle(cellstyle);
	 * //CommonMessage.debugMsg("titlenew"+titlenew);
	 * 
	 * return wb; }
	 */
	
	private String columnLetter(int colIndex) {
	    StringBuilder sb = new StringBuilder();
	    colIndex++; // make 1-based
	    while (colIndex > 0) {
	        int rem = (colIndex - 1) % 26;
	        sb.insert(0, String.valueOf((char) ('A' + rem)));
	        colIndex = (colIndex - 1) / 26;
	    }
	    return sb.toString();
	}


	public Workbook fillExcValues(JSONObject jsonObject, String path, String format, List<String[]> remarks) throws Exception {
	    JSONObject datasObj = (JSONObject) jsonObject.get("values");
	    JSONObject chartObj = (JSONObject) jsonObject.get("chartDatas");
	    JSONObject headersObj = (JSONObject) chartObj.get("headers");
	    JSONArray dataArray = datasObj.getJSONArray("datas");
	    JSONArray headersArray = headersObj.getJSONArray("colHeaders");
	    JSONArray rowTitle = headersObj.getJSONArray("rowHeaders");
	    JSONArray datas = dataArray.getJSONArray(0);
	    String type = datasObj.getString("type");
	    String titleY = headersObj.getString("yAxis");
	    String titleX = headersObj.getString("xAxis");
	    String title = headersObj.getString("title");

	    String excelPath = null;
	    if (remarks != null && remarks.size() != 0) {
	        excelPath = "/KPIRemarks";
	    } else {
	        excelPath = "/Pareto";
	    }

	    
	    String originalFormat = format;
	    format = ExcelUtils.REPORT_FORMAT_EXL_2007;
	    CommonMessage.debugMsg(">>> FORMAT forced to: " + format + " (was: " + originalFormat + ")");

	    InputStream inp = new FileInputStream(path + excelPath + "." + format);
	    Workbook wb = new XSSFWorkbook(inp);

	    CommonMessage.debugMsg(">>> Template loaded. Sheet count: " + wb.getNumberOfSheets());
	    for (int i = 0; i < wb.getNumberOfSheets(); i++) {
	        CommonMessage.debugMsg(">>>   Sheet[" + i + "]: " + wb.getSheetName(i));
	    }
	    CommonMessage.debugMsg(">>> fillExcValues writing data to sheet index: 1 = " + wb.getSheetName(1));
	    inp.close();

	    CommonMessage.debugMsg(">>> WB TYPE: " + wb.getClass().getName());

	    Sheet sheet     = wb.getSheetAt(1);
	    Sheet firstsheet = wb.getSheetAt(0);

	    int colCount      = datas.length();
	    int rowFlag       = getRowNo(type);
	    int dataRowsCount = rowFlag + dataArray.length();
	    int flag          = 0;
	    int titleNo       = 0;
	    int arrIndex      = 0;

	    while (flag < 200) {
	        if (flag >= dataRowsCount) {
	            flag++;
	            continue;
	        }

	        Row row = sheet.createRow(flag);

	        for (int j = 0; j < 200; j++) {
	            if (flag < dataRowsCount) {
	                Cell cell = row.getCell(j);
	                if (cell == null)
	                    cell = row.createCell(j);

	                if (j <= colCount) {
	                    if (flag == 0) {
	                        if (j == 0) {
	                            cell.setCellValue(title);
	                            if (type.equals("pie")) {
	                                int f = flag;
	                                while (f < 60) {
	                                    firstsheet.createRow(f);
	                                    f++;
	                                }
	                            }
	                        } else if (j == 1)
	                            cell.setCellValue(UIUtils.isValidKeyId(titleY) ? titleY : "");
	                        else if (j == 2)
	                            cell.setCellValue(UIUtils.isValidKeyId(titleX) ? titleX : "");

	                    } else if (flag == 1) {
	                        if (j != 0) {
	                            String headerVal = headersArray.length() > 0
	                                    ? headersArray.get(j - 1).toString() : "";
	                            try {
	                                double numVal = Double.parseDouble(headerVal);
	                                cell.setCellValue(numVal); // e.g., 1.0, 2.0 ... 30.0
	                            } catch (NumberFormatException nfe) {
	                                cell.setCellValue(headerVal); // keep as string if not numeric
	                            }
	                        }

	                    } else {
	                        if (flag >= rowFlag) {
	                            JSONArray val = dataArray.getJSONArray(arrIndex);
	                            if (j == 0) {
	                                if (type.equals("pie")) {
	                                    Cell fccell = firstsheet.getRow(flag - 1).getCell(12);
	                                    if (fccell == null)
	                                        fccell = firstsheet.getRow(flag - 1).createCell(12);
	                                    fccell.setCellValue(rowTitle.get(titleNo).toString());
	                                }
	                                cell.setCellValue(rowTitle.get(titleNo).toString());
	                                titleNo++;
	                            } else {
	                                if (j == colCount)
	                                    arrIndex++;
	                                if (UIUtils.isValidKeyId(val.getString(j - 1)))
	                                    cell.setCellValue(val.getDouble(j - 1));
	                                else
	                                    cell.setCellValue(0);
	                                if (type.equals("pie")) {
	                                    Cell fccell = firstsheet.getRow(flag - 1).getCell(j + 12);
	                                    if (fccell == null)
	                                        fccell = firstsheet.getRow(flag - 1).createCell(j + 12);
	                                    if (UIUtils.isValidKeyId(val.getString(j - 1)))
	                                        fccell.setCellValue(val.getDouble(j - 1));
	                                    else
	                                        fccell.setCellValue(0);
	                                }
	                            }
	                        }
	                    }

	                    CellStyle cellstyle = wb.createCellStyle();
	                    cellstyle.setAlignment(HorizontalAlignment.RIGHT);
	                    cell.setCellStyle(cellstyle);

	                } else if (flag == 0) {
	                    sheet.setColumnHidden(j, true);
	                }
	            }
	        }
	        flag++;
	    }

	    CommonMessage.debugMsg(">>> DEBUG Sheet2 contents:");
	    Sheet debugSheet = wb.getSheetAt(1);
	    for (int r = 0; r <= 4; r++) {
	        Row debugRow = debugSheet.getRow(r);
	        if (debugRow == null) {
	            CommonMessage.debugMsg(">>>   Row[" + r + "] = NULL");
	            continue;
	        }
	        StringBuilder rowStr = new StringBuilder(">>>   Row[" + r + "]: ");
	        for (int c = 0; c <= 31; c++) {
	            Cell dc = debugRow.getCell(c);
	            if (dc == null)
	                rowStr.append("[null] ");
	            else if (dc.getCellType() == CellType.NUMERIC)
	                rowStr.append(dc.getNumericCellValue()).append(" ");
	            else if (dc.getCellType() == CellType.STRING)
	                rowStr.append("\"").append(dc.getStringCellValue()).append("\" ");
	            else
	                rowStr.append("[?] ");
	        }
	        CommonMessage.debugMsg(rowStr);
	    }

	    int totalCols     = headersArray.length();  
	    int totalDataRows = dataArray.length();    
	    int dataStartRowEx = rowFlag + 1;            

	    String dataSheetName = wb.getSheetName(1);
	    String lastDataCol   = columnLetter(totalCols); 

	   
	    String dynCatFormula = "'" + dataSheetName + "'!$B$2:$" + lastDataCol + "$2";

	  
	    String[] dynValFormulas = new String[totalDataRows];
	    for (int s = 0; s < totalDataRows; s++) {
	        int excelRow = dataStartRowEx + s; 
	        dynValFormulas[s] = "'" + dataSheetName + "'!$B$" + excelRow
	                + ":$" + lastDataCol + "$" + excelRow;
	    }

	    CommonMessage.debugMsg(">>> Dynamic formulas — totalCols=" + totalCols
	            + " totalDataRows=" + totalDataRows
	            + " dataStartRowEx=" + dataStartRowEx);
	    CommonMessage.debugMsg(">>> dynCatFormula : " + dynCatFormula);
	    for (int s = 0; s < dynValFormulas.length; s++) {
	        CommonMessage.debugMsg(">>> dynValFormulas[" + s + "]: " + dynValFormulas[s]);
	    }

	   
	    boolean catIsString = false;
	    try {
	        Cell sampleHeaderCell = wb.getSheetAt(1).getRow(1).getCell(1);
	        catIsString = (sampleHeaderCell != null
	                && sampleHeaderCell.getCellType() == CellType.STRING);
	    } catch (Exception ex) {
	        catIsString = false;
	    }
	    CommonMessage.debugMsg(">>> catIsString=" + catIsString);

	   
	    CommonMessage.debugMsg(">>> ENTERING CHART FIX BLOCK");
	    try {
	        XSSFWorkbook xwb   = (XSSFWorkbook) wb;
	        XSSFSheet chartSheet = xwb.getSheetAt(0);
	        XSSFDrawing drawing  = chartSheet.getDrawingPatriarch();

	        
	        String targetName = "Target";
	        String actualName = "Actual";
	        try {
	            JSONObject chartDatas = (JSONObject) jsonObject.get("chartDatas");
	            JSONArray seriesArr   = chartDatas.getJSONArray("series");
	            if (seriesArr.length() > 0)
	                targetName = seriesArr.getJSONObject(0).getString("name");
	            if (seriesArr.length() > 1)
	                actualName = seriesArr.getJSONObject(1).getString("name");
	            CommonMessage.debugMsg(">>> Series names from JSON: " + targetName + ", " + actualName);
	        } catch (Exception ex) {
	            CommonMessage.debugMsg(">>> Using default series names: " + ex.getMessage());
	        }

	        if (drawing != null && !drawing.getCharts().isEmpty()) {
	            XSSFChart chart  = drawing.getCharts().get(0);
	            CTPlotArea plotArea = chart.getCTChart().getPlotArea();

	            CommonMessage.debugMsg(">>> BEFORE cleanup — BarCharts=" + plotArea.getBarChartList().size()
	                    + " LineCharts=" + plotArea.getLineChartList().size());

	            
	            for (CTBarChart barChart : plotArea.getBarChartList()) {
	                int serCount = barChart.getSerList().size();
	                for (int s = serCount - 1; s >= 0; s--)
	                    barChart.removeSer(s);
	                CommonMessage.debugMsg(">>> Cleared " + serCount + " bar series");
	            }

	          
	            for (CTLineChart lc : plotArea.getLineChartList()) {
	                int serCount = lc.getSerList().size();
	                for (int s = serCount - 1; s >= 0; s--)
	                    lc.removeSer(s);
	                CommonMessage.debugMsg(">>> Cleared " + serCount + " line series");
	            }

	            
	            CTLineChart lineChart;
	            if (plotArea.getLineChartList().isEmpty()) {
	                lineChart = plotArea.addNewLineChart();
	                lineChart.addNewGrouping().setVal(STGrouping.STANDARD);
	                lineChart.addNewVaryColors().setVal(false);
	                CommonMessage.debugMsg(">>> Created new line chart");
	            } else {
	                lineChart = plotArea.getLineChartList().get(0);
	                CommonMessage.debugMsg(">>> Reusing existing line chart");
	            }

	           
	            CTLineSer targetSer = lineChart.addNewSer();
	            targetSer.addNewIdx().setVal(0);
	            targetSer.addNewOrder().setVal(0);

	            CTSerTx targetTx = targetSer.addNewTx();
	            targetTx.addNewStrRef().addNewStrCache().addNewPt().setV(targetName);
	            targetTx.getStrRef().getStrCache().getPtArray(0).setIdx(0);
	            targetTx.getStrRef().getStrCache().addNewPtCount().setVal(1);

	            
	            if (catIsString) {
	                targetSer.addNewCat().addNewStrRef().setF(dynCatFormula);
	            } else {
	                targetSer.addNewCat().addNewNumRef().setF(dynCatFormula);
	            }
	            targetSer.addNewVal().addNewNumRef().setF(dynValFormulas[0]);
	            targetSer.addNewSmooth().setVal(false);
	            targetSer.addNewMarker().addNewSymbol().setVal(STMarkerStyle.DIAMOND);
	            CommonMessage.debugMsg(">>> ✅ TARGET series added — val: " + dynValFormulas[0]);

	            
	            CTLineSer actualSer = lineChart.addNewSer();
	            actualSer.addNewIdx().setVal(1);
	            actualSer.addNewOrder().setVal(1);

	            CTSerTx actualTx = actualSer.addNewTx();
	            actualTx.addNewStrRef().addNewStrCache().addNewPt().setV(actualName);
	            actualTx.getStrRef().getStrCache().getPtArray(0).setIdx(0);
	            actualTx.getStrRef().getStrCache().addNewPtCount().setVal(1);

	            // Category
	            if (catIsString) {
	                actualSer.addNewCat().addNewStrRef().setF(dynCatFormula);
	            } else {
	                actualSer.addNewCat().addNewNumRef().setF(dynCatFormula);
	            }
	            actualSer.addNewVal().addNewNumRef().setF(dynValFormulas[1]);
	            actualSer.addNewSmooth().setVal(false);
	            actualSer.addNewMarker().addNewSymbol().setVal(STMarkerStyle.DIAMOND);
	            CommonMessage.debugMsg(">>> ✅ ACTUAL series added — val: " + dynValFormulas[1]);

	            
	            try {
	                CTChart ctChart = chart.getCTChart();
	                if (ctChart.getPlotVisOnly() == null)
	                    ctChart.addNewPlotVisOnly().setVal(false);
	                else
	                    ctChart.getPlotVisOnly().setVal(false);
	                CommonMessage.debugMsg(">>> ✅ plotVisOnly set to false");
	            } catch (Exception ex) {
	                CommonMessage.debugMsg(">>> plotVisOnly error: " + ex.getMessage());
	            }

	            CommonMessage.debugMsg(">>> ✅ Chart fix complete. Total line series: "
	                    + lineChart.getSerList().size());
	        }

	    } catch (Exception e) {
	        CommonMessage.debugMsg(">>> CHART FIX ERROR: " + e.getMessage());
	        e.printStackTrace();
	    }
	    CommonMessage.debugMsg(">>> CHART FIX BLOCK DONE");
	    // ====================================================

	    // Remarks section
	    if (remarks != null && remarks.size() > 0) {
	        int g1;
	        int strRow = 36;
	        Cell cell  = null;
	        Cell cell1 = null;
	        for (g1 = 0; g1 < remarks.size(); g1++) {
	            Row row = firstsheet.getRow(strRow + g1);
	            if (row == null)
	                row = firstsheet.createRow(strRow + g1);
	            cell  = row.getCell(3);
	            cell1 = row.getCell(6);
	            if (cell == null || cell1 == null) {
	                cell  = row.createCell(3);
	                cell1 = row.createCell(6);
	                cell.setCellType(CellType.NUMERIC);
	                cell1.setCellType(CellType.STRING);
	            }
	            cell.setCellType(CellType.NUMERIC);
	            cell1.setCellType(CellType.STRING);
	            cell.setCellValue(remarks.get(g1)[0]);
	            cell1.setCellValue(remarks.get(g1)[1]);
	        }
	        CellStyle cellstyle2 = wb.createCellStyle();
	        cell.setCellStyle(cellstyle2);
	        cell1.setCellStyle(cellstyle2);
	    }

	    return wb;
	}


	private JSONObject getKPIRemarks(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession,
			CommonFilter commonFilter) throws Exception {
		List<String[]> getKPIRemarksGrid = null;
		// JSONObject getKPIRemarksGridData = null;
		// CommonFilter comonFilter = new CommonFilter();
		// CommonMessage.debugMsg("GGGGGG......"+comonFilter.getIndicator());
		getKPIRemarksGrid = kpiTlActualKkService.getKPIRemarks(commonFilter);
		CommonMessage.debugMsg("getKPIRemarksGrid --:" + getKPIRemarksGrid);
		JSONObject jsonObject = getTableModelRemarks(getKPIRemarksGrid);
		// getKPIRemarksGridData =
		// UIUtils.convertToJqGridTableObject(getKPIRemarksGrid,request,0,0,getKPIRemarksGrid.size());
		CommonMessage.debugMsg("jsonObject --:" + jsonObject);
		return jsonObject;
	}

	private JSONObject getTableModelRemarks(List<String[]> headers) {
		// CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		CommonMessage.debugMsg("colHeader:" + colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader);
		// jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(500);

		for (int i = 0; i < colHeader.length; i++) {
			CommonMessage.debugMsg("colHeader:[" + i + "]" + colHeader[i]);
			JqGridColModel jqGridColModel = new JqGridColModel();

			if (i == 0 || i == colHeader.length - 1) {
				jqGridColModel.setHidden(true);
				if (i == 0) {
					jqGridColModel.setIndex("cmbKprmKeyid");
					jqGridColModel.setName("cmbKprmKeyid");
				}
				// jqGridColModel.setKey(true);
			} else if (i == 2) {
				jqGridColModel.setWidth(100);
			} else {
				jqGridColModel.setWidth(300);
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				jqGridColModel.setFormatter("txaRemarksFormatter");
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth", "40%%");

		return tableModel;

	}

	private synchronized void kpiIndicatorChart_1(HttpServletRequest request,
			HttpServletResponse response, String indicatorId, String chartDiv)
			throws Exception {
		CommonMessage.debugMsg("kpiIndicatorChart");
		String rptType = request.getParameter("rptType");
		String monthYear = request.getParameter("monthYear");
		String type = request.getParameter("type");
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		ColumnChart columnChart = new ColumnChart();
		String fromdash = request.getParameter("from");
		CommonFilter commonFilter = new CommonFilter();
		commonFilter.setType(type);
		KpiTlActualKk kpiTlActualKk = (KpiTlActualKk) httpSession
				.getAttribute("newKpiTlActualKk");
		if (null == kpiTlActualKk)
			kpiTlActualKk = new KpiTlActualKk();
		kpiTlActualKk.setKaukIndicatorid(indicatorId);
		if ("dashbd".equals(fromdash)) {
			kpiTlActualKk.setKaukIsactual("Y");
			kpiTlActualKk.setKaukTempfield1("dashbd");
		}
		GridParams gridParams;
		gridParams = new GridParams();
		kpiTlActualKk.setKaukMonthyear("");
		kpiTlActualKk.setKaukFreqtype("M");
		// CommonMessage.debugMsg("   indickpimonthYear :"+monthYear);
		if (CommonFunctions.isValidKeyId(monthYear)) {
			kpiTlActualKk.setKaukMonthyear(monthYear);
			kpiTlActualKk.setKaukFreqtype("D");
		}

		CommonMessage.debugMsg(" 1 getKaukMonthyear  :"
				+ kpiTlActualKk.getKaukMonthyear());
		List<String[]> kpiIndiList = kpiTlActualKkService
				.getCostOfQualitygridData(kpiTlActualKk, gridParams,
						commonFilter);
		CommonMessage.debugMsg("  kpiIndiList  :" + kpiIndiList);
		String[] month = null;
		String[] data = null;
		List<String> xCategories = new ArrayList<String>();
		// List<String> xCategoriesBar = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		ChartSeries actualSeriesColumn = new ChartSeries();
		List<Double> actualDataList = new ArrayList<Double>();
		String tmpActualData = kpiIndiList.get(1)[17]; // changed by
														// karthikeyan.b from 15
														// to 17 for position
														// changed in Query
		tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData
				.substring(tmpActualData.indexOf(";") + 1);

		if (!UIUtils.isValidKeyId(tmpActualData))
			actualDataList.add(Double.parseDouble("0"));
		else
			actualDataList.add(Double.parseDouble(tmpActualData));

		tmpActualData = kpiIndiList.get(1)[18];// changed by karthikeyan.b from
												// 16 to 18 for position changed
												// in Query
		tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData
				.substring(tmpActualData.indexOf(";") + 1);

		if (!UIUtils.isValidKeyId(tmpActualData))
			actualDataList.add(Double.parseDouble("0"));
		else
			actualDataList.add(Double.parseDouble(tmpActualData));

		// CommonMessage.debugMsg("tmpActualData  18:=-="+tmpActualData);

		/*
		 * if( kpiIndiList.size() > 2){ ChartSeries targetSeriesColumn = new
		 * ChartSeries(); List<Double> targetDataList = new ArrayList<Double>();
		 * String tmpData = kpiIndiList.get(2)[14]; tmpData =
		 * tmpData.equals("0")?"0":tmpData.substring(tmpData.indexOf(";")+1);
		 * CommonMessage.debugMsg("tmpData  14:=-="+tmpData);
		 * targetDataList.add(Double.parseDouble( tmpData)); tmpData =
		 * kpiIndiList.get(2)[15]; tmpData =
		 * tmpData.equals("0")?"0":tmpData.substring(tmpData.indexOf(";")+1);
		 * CommonMessage.debugMsg("tmpData  15:=-="+tmpData);
		 * targetDataList.add(Double.parseDouble( tmpData));
		 * targetSeriesColumn.setName("BM-YR");
		 * targetSeriesColumn.setData(targetDataList);
		 * targetSeriesColumn.setType(ChartTypes.COLUMN);
		 * chartSeriesList.add(targetSeriesColumn);//adding data to chart to be
		 * dispalyed }
		 */
		month = kpiIndiList.get(0);
		// xCategories.add("Excellence");
		// xCategories.add("BM-YR");
		int monthlen = month.length;
		// CommonMessage.debugMsg("month  "+month.length);
		String currentMonth = CommonFunctions.getCurrentMonth() + "-"
				+ CommonFunctions.getCurrentYear();
		if ("D".equals(kpiTlActualKk.getKaukFreqtype())) {
			currentMonth = CommonFunctions.getDateWithFormat("dd-MMM-yy");
		}
		// CommonMessage.debugMsg("currentMonth  "+currentMonth);
		for (int i = 17; i < monthlen; i++) {
			if (monthlen > i) {
				if (!(i == 17 || i == 18)) {
					// actualDataList.add(null);
					actualDataList.add(0.0);
				}
				// CommonMessage.debugMsg("------------" +month[i]);
				xCategories.add(month[i]);
				if (currentMonth.toUpperCase().equals(month[i].toUpperCase())) {
					monthlen = i + 1;
				}
			}

		}
		actualSeriesColumn.setName("Previous-Years");
		actualSeriesColumn.setData(actualDataList);
		actualSeriesColumn.setType(ChartTypes.COLUMN);
		chartSeriesList.add(actualSeriesColumn);// adding data to chart to be
												// dispalyed
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();// declaring
																	// variable
																	// for Y
																	// axis
		for (int j = 1; j < kpiIndiList.size(); j++) {
			ChartSeries chartSeriesLine = new ChartSeries();

			List<Double> dataLine = new ArrayList<Double>();

			data = kpiIndiList.get(j);
			String[] datasplit = null;

			for (int i = 17; i < monthlen; i++) {
				if (i == 17 || i == 18) {
					if (UIUtils.isValidKeyId(data[i])) {
						datasplit = data[i].split(";");
						if (datasplit.length > 1) {
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(0.0);
						}
					} else
						dataLine.add(0.0);
				} else if ("Daily".equals(data[12])
						&& "Target".equals(data[10])) {
					if (!"0".equals(data[19])) {
						datasplit = data[19].split(";");
						dataLine.add(Double.parseDouble(datasplit[1]));
					} else {
						dataLine.add(Double.parseDouble(data[19]));
					}
				} else if (!"0".equals(data[i])) {
					datasplit = data[i].split(";");
					dataLine.add(Double.parseDouble(datasplit[1]));
				} else {
					dataLine.add(Double.parseDouble(data[i]));
				}

			}

			chartSeriesLine.setName(data[11]);
			chartSeriesLine.setData(dataLine);
			chartSeriesLine.setType(ChartTypes.LINE);
			chartSeriesList.add(chartSeriesLine);// adding data to chart to be
													// dispalyed
			if ("Target".equals(data[10])) {
				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				yAxis.getTitle().setText(data[13]);// declaring caption for Y
													// axis

				chartYAxis.add(yAxis);// adding Yaxis to the chart
			}
		}
		columnChart.setHeight(280);
		
		CommonMessage.debugMsg("Fid:"
				+ (String) httpSession.getAttribute("FLID"));
		String flid = (String) httpSession.getAttribute("FLID");
		CommonMessage.debugMsg("Fid:" + flid);

		String fnlnDescription = kpiTlActualKkService.getFnlnDescription(flid);
		// CommonMessage.debugMsg("xCategories" +xCategories);
		// CommonMessage.debugMsg("chartSeriesList" +chartSeriesList);
		// CommonMessage.debugMsg("chartYAxis" +chartYAxis);
		List<String> xCategoriesTemp = new ArrayList<String>();
		for (String value : xCategories) {

			if (value.length() == 11) {
				xCategoriesTemp.add(value.substring(0, 2));

			} else {
				xCategoriesTemp.add(value);
			}
		}
		xCategories = xCategoriesTemp;
		String title = "";
		if (monthYear == null) {
			title = xCategories.get(2).substring(4);
		} else {
			title = monthYear;
		}
		CommonMessage.debugMsg("xCategories" + xCategories);
		CommonMessage.debugMsg("xCategoriesTemp" + xCategoriesTemp);
		JSONObject chartObj = columnChart.drawChart(xCategories,
				chartSeriesList, fnlnDescription + " - "
						+ kpiIndiList.get(1)[11] + " For " + title, "",
				chartYAxis);
		// JSONObject chartObj = columnChart.drawChart(xCategories,
		// chartSeriesList, kpiIndiList.get(1)[11], "test", chartYAxis);
		UIUtils.dashBoardSetChartObject(request, chartObj);
		// chartObj.put("month", month);
		// chartObj.put("data", data);
		chartObj.put("chartDiv", chartDiv);
		out.println(chartObj);
		out.close();
	}

	private synchronized void kpiIndicatorChart_04_07(
			HttpServletRequest request, HttpServletResponse response,
			String indicatorId, String chartDiv) throws Exception {
		CommonMessage.debugMsg("kpiIndicatorChart");
		String rptType = request.getParameter("rptType");
		String monthYear = request.getParameter("monthYear");
		String type = request.getParameter("type");
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		ColumnChart columnChart = new ColumnChart();
		String fromdash = request.getParameter("from");
		CommonFilter commonFilter = new CommonFilter();
		commonFilter.setType(type);
		KpiTlActualKk kpiTlActualKk = (KpiTlActualKk) httpSession
				.getAttribute("newKpiTlActualKk");
		if (null == kpiTlActualKk)
			kpiTlActualKk = new KpiTlActualKk();
		kpiTlActualKk.setKaukIndicatorid(indicatorId);
		if ("dashbd".equals(fromdash)) {
			kpiTlActualKk.setKaukIsactual("Y");
			kpiTlActualKk.setKaukTempfield1("dashbd");
		}
		GridParams gridParams;
		gridParams = new GridParams();
		kpiTlActualKk.setKaukMonthyear("");
		kpiTlActualKk.setKaukFreqtype("M");
		// CommonMessage.debugMsg("   indickpimonthYear :"+monthYear);
		if (CommonFunctions.isValidKeyId(monthYear)) {
			kpiTlActualKk.setKaukMonthyear(monthYear);
			kpiTlActualKk.setKaukFreqtype("D");
		}

		CommonMessage.debugMsg(" 1 getKaukMonthyear  :"
				+ kpiTlActualKk.getKaukMonthyear());
		List<String[]> kpiIndiList = kpiTlActualKkService
				.getCostOfQualitygridData(kpiTlActualKk, gridParams,
						commonFilter);
		CommonMessage.debugMsg("  kpiIndiList  :" + kpiIndiList);
		String[] month = null;
		String[] data = null;
		List<String> xCategories = new ArrayList<String>();
		// List<String> xCategoriesBar = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		ChartSeries actualSeriesColumn = new ChartSeries();
		List<Double> actualDataList = new ArrayList<Double>();
		String tmpActualData = kpiIndiList.get(2)[17]; // changed by
														// karthikeyan.b from 15
														// to 17 for position
														// changed in Query
		tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData
				.substring(tmpActualData.indexOf(";") + 1);

		if (!UIUtils.isValidKeyId(tmpActualData))
			actualDataList.add(Double.parseDouble("0"));
		else
			actualDataList.add(Double.parseDouble(tmpActualData));

		tmpActualData = kpiIndiList.get(2)[18];// changed by karthikeyan.b from
												// 16 to 18 for position changed
												// in Query
		tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData
				.substring(tmpActualData.indexOf(";") + 1);

		if (!UIUtils.isValidKeyId(tmpActualData))
			actualDataList.add(Double.parseDouble("0"));
		else
			actualDataList.add(Double.parseDouble(tmpActualData));

		month = kpiIndiList.get(0);
		int monthlen = month.length;

		String currentMonth = CommonFunctions.getCurrentMonth() + "-"
				+ CommonFunctions.getCurrentYear();
		if ("D".equals(kpiTlActualKk.getKaukFreqtype())) {
			currentMonth = CommonFunctions.getDateWithFormat("dd-MMM-yy");
		}

		for (int i = 17; i < monthlen; i++) {
			if (monthlen > i) {
				if (!(i == 17 || i == 18)) {
					// actualDataList.add(null);
					actualDataList.add(0.0);
				}
				// CommonMessage.debugMsg("------------" +month[i]);
				xCategories.add(month[i]);
				if (currentMonth.toUpperCase().equals(month[i].toUpperCase())) {
					monthlen = i + 1;
				}
			}

		}
		CommonMessage.debugMsg("xCategories1 : " + xCategories);
		actualSeriesColumn.setName("Previous-Years");
		actualSeriesColumn.setData(actualDataList);
		actualSeriesColumn.setType(ChartTypes.COLUMN);
		chartSeriesList.add(actualSeriesColumn);// adding data to chart to be
												// dispalyed

		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();// declaring
																	// variable
																	// for Y
																	// axis
		for (int j = 1; j < kpiIndiList.size(); j++) {
			ChartSeries chartSeriesLine = new ChartSeries();

			List<Double> dataLine = new ArrayList<Double>();

			data = kpiIndiList.get(j);
			String[] datasplit = null;

			for (int i = 17; i < monthlen; i++) {
				if (i == 17 || i == 18) {
					if (j == 2) {
						if (UIUtils.isValidKeyId(data[i])) {
							datasplit = data[i].split(";");
							if (datasplit.length > 1) {
								dataLine.add(Double.parseDouble(datasplit[1]));
							} else {
								dataLine.add(0.0);
							}
						} else
							dataLine.add(0.0);
					} else
						dataLine.add(0.0);
					// continue;
				}
				/*
				 * else if("Daily".equals(data[12]) &&
				 * "Target".equals(data[10])){ if(!"0".equals(data[19])){
				 * datasplit = data[19].split(";");
				 * dataLine.add(Double.parseDouble(datasplit[1])); } else{
				 * dataLine.add(Double.parseDouble(data[19])); } }
				 */
				else if (!"0".equals(data[i])) {
					datasplit = data[i].split(";");
					dataLine.add(Double.parseDouble(datasplit[1]));
				} else {
					dataLine.add(Double.parseDouble(data[i]));
				}

			}

			String name = "";
			// chartSeriesLine.setName(data[11]);
			if (j == 1) {
				name = "Target ";
			} else {
				name = "Actual ";
			}
			chartSeriesLine.setName(name);
			chartSeriesLine.setData(dataLine);
			chartSeriesLine.setType(ChartTypes.LINE);
			chartSeriesList.add(chartSeriesLine);// adding data to chart to be
													// dispalyed
			if ("Target".equals(data[10])) {
				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				yAxis.getTitle().setText(data[13]);// declaring caption for Y
													// axis

				chartYAxis.add(yAxis);// adding Yaxis to the chart
			}
		}

		CommonMessage.debugMsg("Fid:"
				+ (String) httpSession.getAttribute("FLID"));
		String flid = (String) httpSession.getAttribute("FLID");
		CommonMessage.debugMsg("Fid:" + flid);
		String fnlnDescription = kpiTlActualKkService.getFnlnDescription(flid);
		List<String> xCategoriesTemp = new ArrayList<String>();
		for (String value : xCategories) {

			if (value.length() == 11) {
				xCategoriesTemp.add(value.substring(0, 2));

			} else {
				xCategoriesTemp.add(value);
			}
		}
		xCategories = xCategoriesTemp;
		String title = "";
		if (monthYear == null) {
			title = xCategories.get(2).substring(4);
		} else {
			title = monthYear;
		}
		CommonMessage.debugMsg("xCategories" + xCategories);
		CommonMessage.debugMsg("xCategoriesTemp" + xCategoriesTemp);
		// columnChart.setHeight(280);
		JSONObject chartObj = columnChart.drawChart(xCategories,
				chartSeriesList, fnlnDescription + " - "
						+ kpiIndiList.get(1)[11] + " For " + title, "",
				chartYAxis);
		UIUtils.dashBoardSetChartObject(request, chartObj);
		chartObj.put("chartDiv", chartDiv);
    //    chartObj.put("chartDivid", kpiIndiList.get(1)[8] );
		out.println(chartObj);
		out.close();
	}

	private synchronized void kpiIndicatorChart_ok(HttpServletRequest request,
			HttpServletResponse response, String indicatorId, String chartDiv)
			throws Exception {
		CommonMessage.debugMsg("kpiIndicatorChart");
		String rptType = request.getParameter("rptType");
		String monthYear = request.getParameter("monthYear");
		String type = request.getParameter("type");
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		ColumnChart columnChart = new ColumnChart();
		String fromdash = request.getParameter("from");
		CommonFilter commonFilter = new CommonFilter();
		commonFilter.setType(type);
		KpiTlActualKk kpiTlActualKk = (KpiTlActualKk) httpSession
				.getAttribute("newKpiTlActualKk");
		if (null == kpiTlActualKk)
			kpiTlActualKk = new KpiTlActualKk();
		kpiTlActualKk.setKaukIndicatorid(indicatorId);
		if ("dashbd".equals(fromdash)) {
			kpiTlActualKk.setKaukIsactual("Y");
			kpiTlActualKk.setKaukTempfield1("dashbd");
		}
		GridParams gridParams;
		gridParams = new GridParams();
		kpiTlActualKk.setKaukMonthyear("");
		kpiTlActualKk.setKaukFreqtype("M");
		// CommonMessage.debugMsg("   indickpimonthYear :"+monthYear);
		if (CommonFunctions.isValidKeyId(monthYear)) {
			kpiTlActualKk.setKaukMonthyear(monthYear);
			kpiTlActualKk.setKaukFreqtype("D");
		}

		List<String[]> kpiIndiList = kpiTlActualKkService
				.getCostOfQualitygridData(kpiTlActualKk, gridParams,
						commonFilter);
	
		String[] month = null;
		String[] data = null;
		List<String> xCategories = new ArrayList<String>();
		// List<String> xCategoriesBar = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		ChartSeries actualSeriesColumn = new ChartSeries();
		List<Double> actualDataList = new ArrayList<Double>();
		String tmpActualData = kpiIndiList.get(2)[17]; // changed by
														// karthikeyan.b from 15
														// to 17 for position
														// changed in Query
		tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData
				.substring(tmpActualData.indexOf(";") + 1);

		if (!UIUtils.isValidKeyId(tmpActualData))
			actualDataList.add(Double.parseDouble("0"));
		else
			actualDataList.add(Double.parseDouble(tmpActualData));

		tmpActualData = kpiIndiList.get(2)[18];// changed by karthikeyan.b from
												// 16 to 18 for position changed
												// in Query
		tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData
				.substring(tmpActualData.indexOf(";") + 1);

		if (!UIUtils.isValidKeyId(tmpActualData))
			actualDataList.add(Double.parseDouble("0"));
		else
			actualDataList.add(Double.parseDouble(tmpActualData));

		month = kpiIndiList.get(0);
		int monthlen = month.length;

		String currentMonth = CommonFunctions.getCurrentMonth() + "-"
				+ CommonFunctions.getCurrentYear();
		if ("D".equals(kpiTlActualKk.getKaukFreqtype())) {
			currentMonth = CommonFunctions.getDateWithFormat("dd-MMM-yy");
		}

		CommonMessage.debugMsg("xCategories1 : " + xCategories);
		actualSeriesColumn.setName("Previous-Years");
		actualSeriesColumn.setData(actualDataList);
		actualSeriesColumn.setType(ChartTypes.COLUMN);
		chartSeriesList.add(actualSeriesColumn);// adding data to chart to be
												// dispalyed
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();// declaring
																	// variable
																	// for Y
																	// axis

		for (int j = 1; j < kpiIndiList.size(); j++) {
			ChartSeries chartSeriesLine = new ChartSeries();
			List<Double> dataLine = new ArrayList<Double>();
			data = kpiIndiList.get(j);
			String[] datasplit = null;
			CommonMessage.debugMsg("data[12])--" + data[12]);
			CommonMessage.debugMsg("Month Length " + monthlen);
			if ("Quarterly".equals(data[12])) {
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18 || i == 21 || i == 24 || i == 27
							|| i == 30) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			} else if ("Monthly".equals(data[12])) {
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);

					if (j == 2) {
						if (monthlen > i) {
							if (!(i == 17 || i == 18)) {
								// actualDataList.add(null);
								actualDataList.add(0.0);
							}
							CommonMessage.debugMsg("Month[" + i + "]"
									+ month[i]);
							xCategories.add(month[i]);
							if (currentMonth.toUpperCase().equals(
									month[i].toUpperCase())) {
								monthlen = i + 1;
							}
						}
					}

					if (i == 17 || i == 18) {
						if (j == 2) {
							if (UIUtils.isValidKeyId(data[i])) {
								datasplit = data[i].split(";");
								if (datasplit.length > 1) {
									dataLine.add(Double
											.parseDouble(datasplit[1]));
								} else {
									dataLine.add(0.0);
								}
							} else
								dataLine.add(0.0);
						} else
							dataLine.add(0.0);

					} else if (!"0".equals(data[i])) {
						datasplit = data[i].split(";");
						dataLine.add(Double.parseDouble(datasplit[1]));
					} else {
						dataLine.add(Double.parseDouble(data[i]));
					}
				}

			} else if ("Half Yearly".equals(data[12])) {
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18 || i == 24 || i == 30 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			}else if ("Yearly".equals(data[12])) {
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18  || i == 30 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			}else if ("Daily".equals(data[12])) {
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}
			}else if ("Fortnight".equals(data[12])) {
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18  || i == 32||  i == 46 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			}else if ("Weekly".equals(data[12])) {
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18 || i == 25 || i == 32|| i == 39 || i == 46 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			}

			String name = "";
			// chartSeriesLine.setName(data[11]);
			if (j == 1) {
				name = "Target";
			} else {
				name = "Actual";
			}
			chartSeriesLine.setName(name);
			chartSeriesLine.setData(dataLine);
			chartSeriesLine.setType(ChartTypes.LINE);
			chartSeriesList.add(chartSeriesLine);// adding data to chart to be
													// dispalyed
			if ("Target".equals(data[10])) {
				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				yAxis.getTitle().setText(data[13]);// declaring caption for Y
													// axis

				chartYAxis.add(yAxis);// adding Yaxis to the chart
			}
		}// end outer for

		CommonMessage.debugMsg("Fid:"
				+ (String) httpSession.getAttribute("FLID"));
		String flid = (String) httpSession.getAttribute("FLID");
		CommonMessage.debugMsg("Fid:" + flid);
		String fnlnDescription = kpiTlActualKkService.getFnlnDescription(flid);
		List<String> xCategoriesTemp = new ArrayList<String>();
		
		  for (String value : xCategories) {
		  
		  if(value.length()==11){ xCategoriesTemp.add(value.substring(0, 2));
		  
		  }else{ xCategoriesTemp.add(value); } }
		 
		xCategories=xCategoriesTemp;
		String title = "";
		
		  if(monthYear==null){
			  title=xCategories.get(2).substring(4); 
		  }else{
			  title=monthYear; 
		  }
		 
		CommonMessage.debugMsg("xCategories" + xCategories);
		// CommonMessage.debugMsg("xCategoriesTemp" +xCategoriesTemp);
		// columnChart.setHeight(280);
		JSONObject chartObj = columnChart.drawChart(xCategories,
				chartSeriesList, fnlnDescription + " - "
						+ kpiIndiList.get(1)[11] + " For " + title, "",
				chartYAxis);
		UIUtils.dashBoardSetChartObject(request, chartObj);
		chartObj.put("chartDiv", chartDiv);
		out.println(chartObj);
		out.close();
	}

	private synchronized void kpiIndicatorChart(HttpServletRequest request,
			HttpServletResponse response, String indicatorId, String chartDiv)
			throws Exception {
		
		CommonMessage.debugMsg("inside kpiIndicatorChart========================================================================================");
		
		CommonMessage.debugMsg("kpiIndicatorChart");
		String rptType = request.getParameter("rptType");
		String monthYear = request.getParameter("monthYear");
		String type = request.getParameter("type");
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		ColumnChart columnChart = new ColumnChart();
		String fromdash = request.getParameter("from");
		CommonFilter commonFilter = new CommonFilter();
		commonFilter.setType(type);
		KpiTlActualKk kpiTlActualKk = (KpiTlActualKk) httpSession
				.getAttribute("newKpiTlActualKk");
		if (null == kpiTlActualKk)
			kpiTlActualKk = new KpiTlActualKk();
		kpiTlActualKk.setKaukIndicatorid(indicatorId);
		if ("dashbd".equals(fromdash)) {
			kpiTlActualKk.setKaukIsactual("Y");
			kpiTlActualKk.setKaukTempfield1("dashbd");
		}
		GridParams gridParams;
		gridParams = new GridParams();
		kpiTlActualKk.setKaukMonthyear("");
		kpiTlActualKk.setKaukFreqtype("M");
		// CommonMessage.debugMsg("   indickpimonthYear :"+monthYear);
		if (CommonFunctions.isValidKeyId(monthYear)) {
			kpiTlActualKk.setKaukMonthyear(monthYear);
			kpiTlActualKk.setKaukFreqtype("D");
		}
		commonFilter.setTemp("rptType=" +rptType);
		List<String[]> kpiIndiList = kpiTlActualKkService.getCostOfQualitygridData(kpiTlActualKk, gridParams,commonFilter);	
		String[] month = null;
		String[] data = null;
		List<String> xCategories = new ArrayList<String>();
		// List<String> xCategoriesBar = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		ChartSeries actualSeriesColumn = new ChartSeries();
		List<Double> actualDataList = new ArrayList<Double>();
		if("M".equals(kpiTlActualKk.getKaukFreqtype())){
			String tmpActualData = kpiIndiList.get(2)[17]; // changed by karthikeyan.b from 15 to 17 for position changed in Query
			tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData.substring(tmpActualData.indexOf(";") + 1);

			if (!UIUtils.isValidKeyId(tmpActualData))
				actualDataList.add(Double.parseDouble("0"));
			else
				actualDataList.add(Double.parseDouble(tmpActualData));

			tmpActualData = kpiIndiList.get(2)[18];// changed by karthikeyan.b from 16 to 18 for position changed in Query
			tmpActualData = tmpActualData.equals("0") ? "0" : tmpActualData.substring(tmpActualData.indexOf(";") + 1);

			if (!UIUtils.isValidKeyId(tmpActualData))
				actualDataList.add(Double.parseDouble("0"));
			else
				actualDataList.add(Double.parseDouble(tmpActualData));

			CommonMessage.debugMsg("xCategories1 : " + xCategories);
			actualSeriesColumn.setName("Previous-Years");
			actualSeriesColumn.setData(actualDataList);
			actualSeriesColumn.setType(ChartTypes.COLUMN);
			chartSeriesList.add(actualSeriesColumn);// adding data to chart to be dispalyed 	

		}
		
		month = kpiIndiList.get(0);
		/*for (String m : month) {
			CommonMessage.debugMsg("M: " +m);			
		}*/
		int monthlen = month.length;
		String currentMonth = CommonFunctions.getCurrentMonth() + "-" + CommonFunctions.getCurrentYear();
		CommonMessage.debugMsg("currentMonth: "+currentMonth);
		if ("D".equals(kpiTlActualKk.getKaukFreqtype())) {
			currentMonth = CommonFunctions.getDateWithFormat("dd-MMM-yy");
		}
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();// declaring variable for Y axis
		for (int j = 1; j < kpiIndiList.size(); j++) {
			ChartSeries chartSeriesLine = new ChartSeries();
			List<Double> dataLine = new ArrayList<Double>();
			data = kpiIndiList.get(j);
			/*
			for (String d : data) {
				CommonMessage.debugMsg("data[])--" + d);
			}*/
			String[] datasplit = null;
			CommonMessage.debugMsg("data[12])--" + data[12]);
			CommonMessage.debugMsg("Month Length " + monthlen);
			if ("Quarterly".equals(data[12])) {
				
				CommonMessage.debugMsg("inside Quarterly = 111111111111111111111111111111111");
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18 || i == 21 || i == 24 || i == 27	|| i == 30) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								
								if (currentMonth.toUpperCase().equals(	month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			} else if ("Monthly".equals(data[12])) {
				
				CommonMessage.debugMsg("inside Monthly = 222222222222222222222222");
				
				for (int i = 17; i < monthlen-2; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i >= 17 &&  i <= 31) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								/*if (currentMonth.toUpperCase().equals(month[i].toUpperCase())) {
									monthlen = i + 1;
								}*/
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									
									if (datasplit.length > 1) {
										dataLine.add(Double.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);
	
						} else if (!"0".equals(data[i])) {
							//CommonMessage.debugMsg("--Data[ "+ i +" ]" + data[i] );
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}

			} else if ("Half Yearly".equals(data[12])) {
				
				CommonMessage.debugMsg("inside Half Yearly = 3333333333333333333333");
				
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18 || i == 24 || i == 30 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			}else if ("Yearly".equals(data[12])) {
				
				CommonMessage.debugMsg("Yearly = 44444444444444444444444");
				for (int i = 17; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18  || i == 30 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			}else if ("Daily".equals(data[12])) {
				
				CommonMessage.debugMsg("Daily = 55555555555555555555");
				
				for (int i = 19; i < monthlen-2; i++) {
					CommonMessage.debugMsg(i + " " + j);
					//if (i == 17 || i == 18 || i == 24 || i == 30 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"	+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				//}
			}else if ("Fortnight".equals(data[12])) {
				
				CommonMessage.debugMsg("Fortnight = 6666666666666666666666666");
				
				for (int i = 19; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18  || i == 32||  i == 46 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			}else if ("Weekly".equals(data[12])) {
				
				CommonMessage.debugMsg("Weekly = 77777777777777777777777");
				
				for (int i = 19; i < monthlen; i++) {
					CommonMessage.debugMsg(i + " " + j);
					if (i == 17 || i == 18 || i == 25 || i == 32|| i == 39 || i == 46 ) {
						if (j == 2) {
							if (monthlen > i) {
								if (!(i == 17 || i == 18)) {
									// actualDataList.add(null);
									actualDataList.add(0.0);
								}
								CommonMessage.debugMsg("Month[" + i + "]"
										+ month[i]);
								xCategories.add(month[i]);
								if (currentMonth.toUpperCase().equals(
										month[i].toUpperCase())) {
									monthlen = i + 1;
								}
							}
						}

						if (i == 17 || i == 18) {
							if (j == 2) {
								if (UIUtils.isValidKeyId(data[i])) {
									datasplit = data[i].split(";");
									if (datasplit.length > 1) {
										dataLine.add(Double
												.parseDouble(datasplit[1]));
									} else {
										dataLine.add(0.0);
									}
								} else
									dataLine.add(0.0);
							} else
								dataLine.add(0.0);

						} else if (!"0".equals(data[i])) {
							CommonMessage.debugMsg("Data[ "+ i +" ]" + data[i] );
							datasplit = data[i].split(";");
							dataLine.add(Double.parseDouble(datasplit[1]));
						} else {
							dataLine.add(Double.parseDouble(data[i]));
						}
					}
				}
			}

			String name = "";
			// chartSeriesLine.setName(data[11]);
			if (j == 1) {
				name = "Target";
			} else {
				name = "Actual";
			}
			chartSeriesLine.setName(name);
			chartSeriesLine.setData(dataLine);
			chartSeriesLine.setType(ChartTypes.LINE);
			chartSeriesList.add(chartSeriesLine);// adding data to chart to be
													// dispalyed
			if ("Target".equals(data[10])) {
				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				yAxis.getTitle().setText(data[13]);// declaring caption for Y
													// axis

				chartYAxis.add(yAxis);// adding Yaxis to the chart
			}
		}// end outer for

		CommonMessage.debugMsg("Fid:"
				+ (String) httpSession.getAttribute("FLID"));
		String flid = (String) httpSession.getAttribute("FLID");
		CommonMessage.debugMsg("Fid:" + flid);
		String fnlnDescription = kpiTlActualKkService.getFnlnDescription(flid);
		List<String> xCategoriesTemp = new ArrayList<String>();
		
		  for (String value : xCategories) {
		  
		  if(value.length()==11){ xCategoriesTemp.add(value.substring(0, 2));
		  
		  }else{ xCategoriesTemp.add(value); } }
		 
		xCategories=xCategoriesTemp;
		String title = "";
		
		  if(monthYear==null){
			  title=xCategories.get(2).substring(4); 
		  }else{
			  title=monthYear; 
		  }
		 
		CommonMessage.debugMsg("xCategories" + xCategories);
		// CommonMessage.debugMsg("xCategoriesTemp" +xCategoriesTemp);
		// columnChart.setHeight(280);
		JSONObject chartObj = columnChart.drawChart(xCategories,
				chartSeriesList, fnlnDescription + " - "
						+ kpiIndiList.get(1)[11] + " For " + title, " ",
				chartYAxis);
		UIUtils.dashBoardSetChartObject(request, chartObj);
		chartObj.put("chartDiv", chartDiv);
		chartObj.put("indiid", kpiIndiList.get(1)[8]);
		chartObj.put("freq", kpiTlActualKk.getKaukFreqtype());
		chartObj.put("flid", flid);
	    chartObj.put("KPIActual", kpiIndiList.get(2)[17]);
		out.println(chartObj);
		out.close();
	}

	private void saveKpiActualKk(HttpServletRequest request,HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception{
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		KpiTlActualKkBean kpiTlActualKkBean = new KpiTlActualKkBean();
		String openactnpln = request.getParameter("openactnpln");
		String whywhy = request.getParameter("whywhy");
		String dateTime = CommonFunctions.dateTimeNow();
		String date=dateTime.substring(0, 11);
		String hh=dateTime.substring(12,14);
		String mi=dateTime.substring(15, 17);
		String ss=dateTime.substring(18, 20);
		int sec=Integer.parseInt(ss);
		int seccn=0;
		CommonMessage.debugMsg("date"+date+"hh"+hh+"mi"+mi+"ss"+ss);
		String currentTime=dateTime.substring(12, 20);
		String flid=request.getParameter("flid");
		CommonMessage.debugMsg("The Flid::"+flid);
		String year=request.getParameter("year");
		CommonMessage.debugMsg("The Year:::"+year);
		String CurrDate=request.getParameter("CurrDate");
		CommonMessage.debugMsg("The CurrDate:::"+CurrDate);
		String formMode=request.getParameter("formMode");
		CommonMessage.debugMsg("the formMode is:::"+formMode);
		String frequency=request.getParameter("vFreq");
		CommonMessage.debugMsg("The frequency:::::"+frequency);
		String CurrMonthYear=request.getParameter("CurrMonthYear");
		CommonMessage.debugMsg("The CurrMonthYear::::"+CurrMonthYear);
		
		
		String count=null;
		try {
			if (httpSession != null && user != null) {
				List<KpiTlActualKk> existKpiTlActualKk = (List<KpiTlActualKk>) httpSession.getAttribute("KpiTlActualKk");
				kpiTlActualKkBean = (KpiTlActualKkBean) UIUtils.setBeanProperties((Object) kpiTlActualKkBean, request);
				String mulitMethodStr = request.getParameter("multiplemethods");
				CommonMessage.debugMsg("The mulitMethodStr"+mulitMethodStr);
				String type = request.getParameter("type");
				String rowId = request.getParameter("rowId");
				List<KpiTlActualKk> newKpiTlActualKk = null;
				JSONArray kpiTlActualKkJson = null;

				if (UIUtils.isValidKeyId(mulitMethodStr)) 
				{
						KpiTlActualKk kpiTlActualKk = new KpiTlActualKk();
					if (mulitMethodStr != null && !mulitMethodStr.isEmpty()) {
					   kpiTlActualKkJson = JSONArray.fromString(mulitMethodStr);
						newKpiTlActualKk = (List<KpiTlActualKk>) UIUtils.convertJSONArrToList(kpiTlActualKk,kpiTlActualKkJson);
					}
					
					
					String firstDate = null;
					String saveMsg = null;
					if (newKpiTlActualKk != null) {
						for (int i = 0; i <= newKpiTlActualKk.size() - 1; i++) {
							seccn=sec+i;
							String mnthyr=newKpiTlActualKk.get(i).getKaukMonthyear();
							if(newKpiTlActualKk.get(i).getKaukFreqtype().equals("M")){
							if(mnthyr!=null){
							if(mnthyr.length()>11)
							{  
								 CommonMessage.debugMsg("mnthyr.substring(3,13)"+mnthyr.substring(3,mnthyr.length()));
								 newKpiTlActualKk.get(i).setKaukFreqtype("D");
								newKpiTlActualKk.get(i).setKaukMonthyear(mnthyr.substring(3,mnthyr.length()));
							}
							}
							}
							newKpiTlActualKk.get(i).setKaukCreatedby(user.getUsrm_ccno());
//							if (CommonFunctions.isValidKeyId(newKpiTlActualKk.get(i).getKaukKeyid())) {	
//								KpiTlActualKk oldKpiTlActualKk = new KpiTlActualKk();
//								oldKpiTlActualKk.setKaukKeyid(newKpiTlActualKk
//										.get(i).getKaukKeyid());
//							
//								
//								oldKpiTlActualKk = kpiTlActualKkService
//										.select(oldKpiTlActualKk);
//								
//								
//							
//														
//													
//								newKpiTlActualKk.get(i).setKaukMonthyear(oldKpiTlActualKk.getKaukMonthyear());	
//								newKpiTlActualKk.get(i).setKaukCreatedby(
//										oldKpiTlActualKk.getKaukCreatedby());
//								newKpiTlActualKk.get(i).setKaukCreatedon(
//										oldKpiTlActualKk.getKaukCreatedon());
//								oldKpiTlActualKk.setKaukKeyid(newKpiTlActualKk.get(i).getKaukKeyid());
//								
//							
//							    
//								oldKpiTlActualKk = kpiTlActualKkService.select(oldKpiTlActualKk);
//								newKpiTlActualKk.get(i).setKaukCreatedby(oldKpiTlActualKk.getKaukCreatedby());
//								newKpiTlActualKk.get(i).setKaukCreatedon(oldKpiTlActualKk.getKaukCreatedon());					
//							}
						}
					/*	
						if(!UIUtils.isValidKeyId(kpiTlActualKk.getKaukKeyid())){
							SendKPIDeviationMail(request,response,);
						}*/
						
						if(kpiTlActualKk.getKaukKeyid()==null){
						CommonMessage.debugMsg("Inside the Keyid null");
						
						
					
						existKpiTlActualKk = kpiTlActualKkService.create(newKpiTlActualKk,existKpiTlActualKk,kpiTlActualKkBean);
					//	if(formMode=="actual"){
						count=kpiTlActualKkService.getKPIDeviationCount(flid,year,CurrDate,CurrMonthYear,frequency);
						CommonMessage.debugMsg("Count is:::"+count);
					//	}
						saveMsg = "Data Saved Successfully";
						}
						
						Boolean clrVal = false;
						JSONObject returnData = new JSONObject();
						JSONObject successData = new JSONObject();
						if (UIUtils.isValidKeyId(openactnpln)) {
							for (int i = 0; i <= newKpiTlActualKk.size() - 1; i++) {
								if ("Y".equals(openactnpln)) {
									successData.put("openactnpln", true);
									successData.put("keyid", newKpiTlActualKk.get(Integer.parseInt(rowId) - 1).getKaukKeyid());
									clrVal = false;
									if (i == Integer.parseInt(rowId) - 1)
										i = newKpiTlActualKk.size();
								}
							}
						} else if (UIUtils.isValidKeyId(whywhy)) {
							for (int i = 0; i <= newKpiTlActualKk.size() - 1; i++) {
								if ("Y".equals(whywhy)) {
									successData.put("whywhy", true);
									successData.put("keyid", newKpiTlActualKk.get(Integer.parseInt(rowId) - 1).getKaukKeyid());
									clrVal = false;
									if (i == Integer.parseInt(rowId) - 1)
										i = newKpiTlActualKk.size();
								}
							}
						}
						successData.put("month", newKpiTlActualKk.get(0)
								.getKaukCalendaryear());
					    successData.put("date",newKpiTlActualKk.get(0)
					    		.getKaukMonthyear());	
						successData.put("pillarId", newKpiTlActualKk.get(0)
								.getKaukPillarid());
						
						successData.put("deptid",newKpiTlActualKk.get(0).getKaukDeptid());
						successData.put("msg", saveMsg);
						successData.put("type", type);
						successData.put("count",count);
						successData.put("formMode",formMode);
						successData.put("frequency",frequency);
						successData.put("CurrMonthYear",CurrMonthYear);
						successData.put("isActual",newKpiTlActualKk.get(0).getKaukIsactual());
						//successData.put("vMonth",vMonth);
						returnData.put("formClear", clrVal);
						returnData.put("successData", successData);
						out.print(returnData.toString());
					}
				}
			}

		} catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),
					"KpiTlActualKk");
			errMessage.put("fromMode", kpiTlActualKkBean.getFormActionMode());
			out.print(errMessage.toString());

		} catch (BusinessApplicationExceptions e) {
			net.sf.json.JSONObject errMessage = UIUtils
					.businessValidationExceptions(e.toString(), "KpiTlActualKk");
			errMessage.put("tpmException", "Data Already Exists in this Month");
			out.print(errMessage.toString());

		} catch (Exception e){
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}

	private void saveKpiRemarksKk(HttpServletRequest request,
			HttpServletResponse response) throws ValidationExceptions,
			BusinessApplicationExceptions, Exception {
		CommonMessage.debugMsg("saveKpiRemarksKk");
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		KpiTlKpiremarksBean kpiTlKpiremarksBean = new KpiTlKpiremarksBean();
		String action = request.getParameter("action");
		try {
			if (httpSession != null && user != null) {
				List<KpiTlKpiremarks> existKpiTlKpiremarks = (List<KpiTlKpiremarks>) httpSession
						.getAttribute("KpiTlKpiremarks");
				CommonMessage.debugMsg("existKpiTlKpiremarks------------1");
				kpiTlKpiremarksBean = (KpiTlKpiremarksBean) UIUtils
						.setBeanProperties((Object) kpiTlKpiremarksBean,
								request);
				CommonMessage.debugMsg("kpiTlKpiremarksBean------------2");
				String mulitMethodStr = request.getParameter("multiplemethods");
				CommonMessage.debugMsg("mulitMethodStr...3" + mulitMethodStr);

				List<KpiTlKpiremarks> newKpiTlKpiremarks = null;
				JSONArray KpiTlKpiremarksJson = null;

				if (UIUtils.isValidKeyId(mulitMethodStr)) {
					CommonMessage.debugMsg("4.............");
					KpiTlKpiremarks KpiTlKpiremarks = new KpiTlKpiremarks();
					if (mulitMethodStr != null && !mulitMethodStr.isEmpty()) {
						KpiTlKpiremarksJson = JSONArray
								.fromString(mulitMethodStr);
						newKpiTlKpiremarks = (List<KpiTlKpiremarks>) UIUtils
								.convertJSONArrToList(KpiTlKpiremarks,
										KpiTlKpiremarksJson);
						CommonMessage.debugMsg("5.............");
					}
					String firstDate = null;
					String saveMsg = null;
					if (newKpiTlKpiremarks != null) {
						CommonMessage.debugMsg("6.............");
						for (int i = 0; i <= newKpiTlKpiremarks.size() - 1; i++) {

							newKpiTlKpiremarks.get(i).setKprmCreatedby(
									user.getUsrm_ccno());
							CommonMessage.debugMsg("getKprmKeyid---:"
									+ newKpiTlKpiremarks.get(i).getKprmKeyid());
							CommonMessage.debugMsg("7.............");
//							if (CommonFunctions.isValidKeyId(newKpiTlKpiremarks
//									.get(i).getKprmKeyid())) {
//								CommonMessage.debugMsg("8.............");
//								KpiTlKpiremarks oldKpiTlKpiremarks = new KpiTlKpiremarks();
//								oldKpiTlKpiremarks
//										.setKprmKeyid(newKpiTlKpiremarks.get(i)
//												.getKprmKeyid());
//								oldKpiTlKpiremarks = kpiTlActualKkService
//										.selectRemarks(oldKpiTlKpiremarks);
//																							
//								
//								newKpiTlKpiremarks.get(i).setKprmCreatedby(
//										oldKpiTlKpiremarks.getKprmCreatedby());
//								newKpiTlKpiremarks.get(i).setKprmCreatedon(
//										oldKpiTlKpiremarks.getKprmCreatedon());
//								CommonMessage.debugMsg("9.............");
//							}
						}

						CommonMessage.debugMsg("10.............");
						// CommonMessage.debugMsg("key id available");
						existKpiTlKpiremarks = kpiTlActualKkService
								.createRemarks(newKpiTlKpiremarks,
										existKpiTlKpiremarks,
										kpiTlKpiremarksBean);
						CommonMessage.debugMsg("10.............");
						if (action.equals("save")) {
							saveMsg = "Data Saved Successfully";
						} else if (action.equals("delete")) {
							saveMsg = "Data Deleted Successfully";
						}
						JSONObject returnData = new JSONObject();
						JSONObject successData = new JSONObject();
						successData.put("msg", saveMsg);
						returnData.put("formClear", false);
						returnData.put("successData", successData);
						out.print(returnData.toString());
						/*
						 * } else{
						 * CommonMessage.debugMsg("Referred By Another Table"
						 * ); JSONObject err = new JSONObject();
						 * err.put("tpmException",
						 * valid);//"This Calendar Referred Batch Schedule");
						 * out.print(err.toString()); }
						 */
					}
				}
			}

		} catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),
					"KpiTlActualKk");
			errMessage.put("fromMode", kpiTlKpiremarksBean.getFormActionMode());
			out.print(errMessage.toString());

		} catch (BusinessApplicationExceptions e) {
			net.sf.json.JSONObject errMessage = UIUtils
					.businessValidationExceptions(e.toString(), "KpiTlActualKk");
			errMessage.put("tpmException", "Data Not Saved");
			out.print(errMessage.toString());

		} catch (Exception e) {
			CommonMessage.debugMsg("Exception");
			CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
	
	private void SendKPIDeviationMail(HttpServletRequest request,
			HttpServletResponse response,KpiTlActualKk kpiTlActualKk) throws IOException{
		// TODO Auto-generated method stub
		 CommonMessage.debugMsg("inside of mail method");
		 String fileName="";
		 File f = null;
		try{
			String flid=request.getParameter("deptid");
			CommonMessage.debugMsg("The Flid is :::::"+flid);
			String year=request.getParameter("month");
			CommonMessage.debugMsg("The KPI year::::"+year);
			String CurrDate=request.getParameter("CurrDate");
			CommonMessage.debugMsg("CurrDate"+CurrDate);
			String CurrMonthYear=request.getParameter("CurrMonthYear");
			CommonMessage.debugMsg("The CurrMonthYear:::"+CurrMonthYear);
			String frequency=request.getParameter("frequency");
			CommonMessage.debugMsg("The vFreq:::"+frequency);
			String format = ExcelUtils.getFormat(request);

		    List<String[]> empMailIds = kpiTlActualKkService.getKPIEmpMailIds(flid,glbLocation,rolename);		    
		    String mailIds = buildToMailIds(empMailIds);
		    CommonMessage.debugMsg("mailIds::::"+mailIds);
			if( mailIds.isEmpty())
			{
				JSONObject succssMsg= new JSONObject();
				glbmsg="No Valid MailId Found ";				
				return ;
			}
			format = ".xlsx";
	        String path = UIUtils.getExcelTemplatePath(request);
			fileName =this.filePath + "KPIDeviation_"+flid + "_" + UIUtils.now() +format;
			CommonMessage.debugMsg("The FileName is::"+fileName);
		    Workbook wb = kpiTlActualKkService.KPIDeviationExcelView(flid,year,CurrDate,CurrMonthYear,frequency,format,path);
            			
			FileOutputStream out = new FileOutputStream(fileName);
		    wb.write(out); 
		    out.close();
		    out = null;
		    wb = null;

		    CommonMessage.debugMsg("File Write Completed ");
		    f = new File( fileName);
			
			String content = UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting", "MOMMeetingMailContent");
			CommonMessage.debugMsg("The Content is::"+content);
			String disclaimerNote = UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting", "MOMMeetingMailDisclaimer");
			CommonMessage.debugMsg("The disclaimerNote::"+disclaimerNote);
			StringBuilder subject = new StringBuilder("KPI");
			subject.append(" KPIDeviation -"+flid);
			subject.append(year);
			
			StringBuilder totalContent = new StringBuilder();
			totalContent.append(content);
			totalContent.append("\r\n");
			totalContent.append("\r\n");
			totalContent.append(disclaimerNote);
			CommonMessage.debugMsg(" Before sendLotusNotesMail" );
			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);
			
			//attachmentFiles = attachFileManagerFiles(attachmentFiles,flid);
		 try{
			UIUtils.sendLotusNotesMailAttachments(request,response,mailIds,null,subject.toString(),totalContent.toString(),attachmentFiles);
		 }	
		 catch(Exception e){
				CommonMessage.debugMsg(" sendMomMail Exception" );
				glbmsg="Mail Not Sent !";
			}
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
			UIUtils.delete( f );
			glbmsg="Mail Sent SuccessFully !";
	         
		}catch(Exception e){
			CommonMessage.debugMsg(" sendMomMail Exception" );
			if( fileName != null && f != null)
				UIUtils.delete( f );
			glbmsg="Mail Not Sent !";
		}
	}
	
/*private List<String> attachFileManagerFiles(List<String> attachmentFiles, String momKeyId) throws Exception {
		
		List<String[]> docMgrids = nearMissService.getNearMissReleatedFileManager(momKeyId);
		//CommonMessage.debugMsg(" size 1234 :: "+docMgrids.size());
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
			   //attachmentFiles.add(path);
		   }
		}
		return attachmentFiles;
	}*/

	private String buildToMailIds(List<String[]> mailIds){
		StringBuilder mailIdsStr = new StringBuilder();
		for(String [] mailid : mailIds){
			if(UIUtils.isValidEmail(mailid[1]) ){
				mailIdsStr.append(mailid[1]);
				mailIdsStr.append(',');
			}	
		}
		if(  mailIdsStr.length() > 0 )
			mailIdsStr.deleteCharAt(mailIdsStr.lastIndexOf(","));
		    return mailIdsStr.toString();
	}

	private JSONObject getDataActualKkList(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		List<String[]> costOfQualityGrid = null;
		String year = request.getParameter("year");
		String flId = request.getParameter("flId");
		String factId = request.getParameter("factId");
		String sectionId = request.getParameter("sectionId");
		String cellId = request.getParameter("cellId");
		String pillarId = request.getParameter("pillarId");
		String isActual = request.getParameter("isActual");
		String type = request.getParameter("type");

		CommonFilter commonFilter = populateCommonFilter(request,"ActualKKListCommonFilter", false);
		commonFilter.setType(type);

		KpiTlActualKk newKpiTlActualKk = new KpiTlActualKk();
		newKpiTlActualKk = (KpiTlActualKk) UIUtils.setBeanProperties(newKpiTlActualKk, request);
		if (!CommonFunctions.isValidKeyId(year))
			year = CommonFunctions.getCurrentYear();
		newKpiTlActualKk.setKaukCalendaryear(year);
		newKpiTlActualKk.setKaukDeptid("");
		newKpiTlActualKk.setKaukDepttype("");
		if (CommonFunctions.isValidKeyId(flId))
			newKpiTlActualKk.setKaukDeptid(flId);
		/*
		 * if (CommonFunctions.isValidKeyId(factId))
		 * newKpiTlActualKk.setKaukFactoryid(factId); if
		 * (CommonFunctions.isValidKeyId(sectionId))
		 * newKpiTlActualKk.setKaukSectionid(sectionId); if
		 * (CommonFunctions.isValidKeyId(cellId))
		 * newKpiTlActualKk.setKaukCellid(cellId);
		 */
		if (CommonFunctions.isValidKeyId(pillarId))
			newKpiTlActualKk.setKaukPillarid(pillarId);
		if (CommonFunctions.isValidKeyId(isActual))
			newKpiTlActualKk.setKaukIsactual(isActual);

		httpSession.setAttribute("newKpiTlActualKk", newKpiTlActualKk);
		JSONObject costOfQualityGriddata = null;
		GridParams gridParams;
		gridParams = (GridParams) httpSession.getAttribute(pillarId
				+ "gridParams");
		try {

			if (gridParams != null)
				CommonMessage.debugMsg("gridParams1=>"
						+ gridParams.getFromRow() + "--"
						+ gridParams.getToRow() + "--"
						+ gridParams.getTotalRecordCnt());
			if (gridParams == null)
				gridParams = new GridParams();

			// JSONObject tableModel =
			// (JSONObject)httpSession.getAttribute(session_ident_masterTblConfColModel+menuId);

			FilterValues.populateGridParams(request, gridParams);
			commonFilter.setIsGetCol("N");
			costOfQualityGrid = kpiTlActualKkService.getCostOfQualitygridData(
					newKpiTlActualKk, gridParams, commonFilter);
			String totlarowcount = String.valueOf(costOfQualityGrid.size());

			long totalRwCnt = Long.parseLong(totlarowcount);
			gridParams.setTotalRecordCnt(totalRwCnt);
			httpSession.removeAttribute(pillarId + "gridParams");
			// CommonMessage.debugMsg("gridParams3=>"+gridParams);
			httpSession.setAttribute(pillarId + "gridParams", gridParams);

		} catch (Exception e) {
			e.printStackTrace();
		}
		costOfQualityGriddata = UIUtils.convertToJqGridTableObject(
				costOfQualityGrid, request, 1, 0,
				gridParams.getTotalRecordCnt());
		// CommonMessage.debugMsg("costOfQualityGriddata"+costOfQualityGriddata);
		return costOfQualityGriddata;
	}

	private JSONObject getColActualKkList(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession,
			CommonFilter commonFilter) throws IOException {
		// CommonMessage.debugMsg("inside col Model");
		List<String[]> costOfQualityGrid = null;
		String year = request.getParameter("year");
		String flId = request.getParameter("flId");
		String factId = request.getParameter("factId");
		String sectionId = request.getParameter("sectionId");
		String cellId = request.getParameter("cellId");
		String pillarId = request.getParameter("pillarId");
		String isActual = request.getParameter("isActual");
		String FormMode = request.getParameter("frmMode");
		String type = "N";
		type = (String) httpSession.getAttribute("KPItype");

		String frmType = request.getParameter("type");
		commonFilter.setType(frmType);
		
		String kinkFrequency = request.getParameter("kinkFrequency");
		String month = request.getParameter("month");

		CommonMessage.debugMsg("frequency ====>" + kinkFrequency+ " Month:=> " + month);

		KpiTlActualKk newKpiTlActualKk = new KpiTlActualKk();
		newKpiTlActualKk = (KpiTlActualKk) UIUtils.setBeanProperties(newKpiTlActualKk, request);
		// CommonMessage.debugMsg("1. newKpiTlActualKk.getKaukFreqtype(): " +
		// newKpiTlActualKk.getKaukFreqtype());

		if (!CommonFunctions.isValidKeyId(year))
			year = CommonFunctions.getCurrentYear();

		newKpiTlActualKk.setKaukCalendaryear(year);

		newKpiTlActualKk.setKaukDeptid("");
		newKpiTlActualKk.setKaukDepttype("");
		if (CommonFunctions.isValidKeyId(flId))
			newKpiTlActualKk.setKaukDeptid(flId);
		/*
		 * if (CommonFunctions.isValidKeyId(factId))
		 * newKpiTlActualKk.setKaukFactoryid(factId); if
		 * (CommonFunctions.isValidKeyId(sectionId))
		 * newKpiTlActualKk.setKaukSectionid(sectionId); if
		 * (CommonFunctions.isValidKeyId(cellId))
		 * newKpiTlActualKk.setKaukCellid(cellId);
		 */
		if (CommonFunctions.isValidKeyId(pillarId))
			newKpiTlActualKk.setKaukPillarid(pillarId);
		if (CommonFunctions.isValidKeyId(isActual))
			newKpiTlActualKk.setKaukIsactual(isActual);
		// JSONObject costOfQualityGriddata = null;
		GridParams gridParams;

		gridParams = new GridParams();
		commonFilter.setTemp("kinkFrequency=" + kinkFrequency + ";month="+ month + ";");
		try {

			costOfQualityGrid = kpiTlActualKkService.getCostOfQualitygridData(newKpiTlActualKk, gridParams, commonFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// costOfQualityGriddata =
		// UIUtils.convertToJqGridTableObject(costOfQualityGrid,request,0,0,gridParams.getTotalRecordCnt());
		// httpSession.setAttribute("costOfQualityGriddata",
		// costOfQualityGriddata);
		JSONObject jsonObject = getTableModel(costOfQualityGrid, FormMode,	year, type, kinkFrequency);
		JSONObject jsonObjectExcel = getTableModelExcel(costOfQualityGrid, FormMode,	year, type, kinkFrequency);
		if (costOfQualityGrid.size() > 0) {
			jsonObject.set("tableHeight", "87%%");
			jsonObject.set("tableWidth", "100%%");
		}
		
		return jsonObject;
	}

	private void getExlActualKkList(HttpServletRequest request,	HttpServletResponse response, HttpSession httpSession)	throws Exception {
		String year = request.getParameter("year");
		String flId = request.getParameter("flId");
		String pillarId = request.getParameter("pillarId");
		String isActual = request.getParameter("isActual");
		String type = request.getParameter("type");
		//sriram 20-NOv-2025
		String formMode = request.getParameter("frmMode");  

		CommonFilter commonFilter = populateCommonFilter(request,"ActualKKListCommonFilter", false);
		commonFilter.setViewClick('Y');
		commonFilter.setType(type);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		commonFilter.setFlid(flId);
		//sriram 20-NOv-2025
		commonFilter.setSafetyMode(formMode);  

		KpiTlActualKk newKpiTlActualKk = new KpiTlActualKk();
		newKpiTlActualKk = (KpiTlActualKk) UIUtils.setBeanProperties(newKpiTlActualKk, request);
		
		
		String kinkFrequency = request.getParameter("kinkFrequency");
		String month = request.getParameter("month");
		CommonMessage.debugMsg(" getExcel frequency ====>" + kinkFrequency+ " Month:=> " + month);
		commonFilter.setTemp("kinkFrequency=" + kinkFrequency + ";month="+ month + ";");
		
		if (!CommonFunctions.isValidKeyId(year))
			year = CommonFunctions.getCurrentYear();
		newKpiTlActualKk.setKaukCalendaryear(year);
		newKpiTlActualKk.setKaukDeptid("");
		newKpiTlActualKk.setKaukDepttype("");
		if (CommonFunctions.isValidKeyId(flId))
			newKpiTlActualKk.setKaukDeptid(flId);
		if (CommonFunctions.isValidKeyId(pillarId))
			newKpiTlActualKk.setKaukPillarid(pillarId);
		if (CommonFunctions.isValidKeyId(isActual))
			newKpiTlActualKk.setKaukIsactual(isActual);
	/*	if (CommonFunctions.isValidKeyId(request.getParameter("kinkFrequency"))){
			newKpiTlActualKk.setKaukFreqtype(request.getParameter("kinkFrequency"));
			commonFilter.setTemp("kinkFrequency=" + kinkFrequency + ";month="+ month + ";");
		}

*/
		httpSession.setAttribute("newKpiTlActualKk", newKpiTlActualKk);
		GridParams gridParams;
		gridParams = (GridParams) httpSession.getAttribute(pillarId	+ "gridParams");
		// if( gridParams != null)
		// CommonMessage.debugMsg("gridParams1=>"+gridParams.getFromRow()+"--"+gridParams.getToRow()+"--"+gridParams.getTotalRecordCnt());
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("ActualKKListColModelExcel");
		CommonMessage.debugMsg("before tblJSONObj");
		tblJSONObj.put("title", "Target/Actual List");
		CommonMessage.debugMsg("after tblJSONObj");
		String format = ExcelUtils.getFormat(request);
		Workbook wb = kpiTlActualKkService.getActualKKListExl(newKpiTlActualKk,	gridParams, commonFilter, tblJSONObj, format);
		commonFilter.setFromRow(tmpFromRow);
		
		Sheet sheet = wb.getSheetAt(0);
		sheet.createFreezePane(3,0);
        
		/*
		Row row = sheet.getRow(9);
	        CellStyle cellStyle = wb.createCellStyle();
	        cellStyle = wb.createCellStyle();
	        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        row.setRowStyle(cellStyle); 
		*/
		ExcelUtils.writeToResponse(response, wb, "Target/ActualReport", format);
	}

//	private JSONObject getTableModel(List<String[]> headers, String FormMode,	String year, String type,String kinkFrequency) {
//		CommonMessage.debugMsg("getTableModel---------100");
//		CommonMessage.debugMsg("FormMode :" + FormMode + " year " + year	+ "  type " + type+"Frequency"+kinkFrequency+"headers"+headers);
///*
//		for (String[] strings : headers) {
//			CommonMessage.debugMsg();
//			for (String string : strings) {
//				CommonMessage.debugMsg(string + "   ");
//			}
//
//		}*/
//
//		JqGridTableModel jqGridTableModel = new JqGridTableModel();
//		int lastYer = Integer.parseInt(year) - 1;
//		int lastYer1 = Integer.parseInt(year) - 2;
//
//		String[] colHeader = headers.get(0);
//		colHeader[16] = "Remarks";
//		colHeader[17] = Integer.toString(lastYer1);
//		colHeader[18] = Integer.toString(lastYer);
//		/*
//		 * if("Y".equals(type)) colHeader[17] = "Remarks"; else colHeader[17]
//		 * =Integer.toString(lastYer1);
//		 */
//		/*
//		 * CommonMessage.debugMsg("headers.get(0):" + headers.get(0));
//		 * CommonMessage.debugMsg("headers Completed");
//		 */
//		String[] emptyrow = new String[colHeader.length];
//		emptyrow[0] = "";
//		emptyrow[1] = "";
//		jqGridTableModel.getRowHeaders().add(colHeader);
//		// jqGridTableModel.setTableButton(true);
//		jqGridTableModel.setRowNumbers(true);
//		jqGridTableModel.setTableButton(true);
//		jqGridTableModel.setTableHeight(100);
//		jqGridTableModel.setTableWidth(1000);
//		
//
//		for (int i = 0; i < colHeader.length; i++) {
//			JqGridColModel jqGridColModel = new JqGridColModel();
//
//			jqGridColModel.setWidth(299);
//			jqGridColModel.setAlign("left");
//			jqGridColModel.setEditable(false);
//
//			if (i <= 9 || i == 14) {
//				jqGridColModel.setHidden(true);
//			} else if (i == 10) {
//				// jqGridColModel.setWidth(100);
//				jqGridColModel.setAlign("left");
//			} else if (i == 11) {
//				jqGridColModel.setWidth(60);
//				jqGridColModel.setAlign("left");
//				// jqGridColModel.setHidden(true);
//			} else if (i == 12) {
//				jqGridColModel.setWidth(100);
//				jqGridColModel.setAlign("left");
//			} else if (i == 13) {
//				jqGridColModel.setWidth(40);
//				jqGridColModel.setAlign("left");
//			}
//			if (i == 6) {
//				// jqGridColModel.setKey(true);
//			}
//			if (i <= 13) {
//				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
//				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
//			} else if (i > 13 && !colHeader[i].equals("Calctype") /*&& !colHeader[i].equals("SumOrAvg")*/) {
//				//CommonMessage.debugMsg("2000000000000000");
//				CommonMessage.debugMsg("colHeader["+i+"] "+ colHeader[i]);
//				
//				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + "_"
//						+ i);
//				jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + "_"
//						+ i);
//				jqGridColModel.setWidth(80);
//				jqGridColModel.setAlign("right");
//			   jqGridColModel.setFormatter("txtFormatter");
//				jqGridColModel.setCellattr("0");
//
//				if (i == 15) {
//					if ("target".equals(FormMode))
//						jqGridColModel.setHidden(true);
//					else
//						jqGridColModel.setFormatter("txtButton");
//
//				}
//				if (colHeader[i].equals("SumOrAvg") && kinkFrequency.equals("M")) {
//					jqGridColModel.setHidden(true);
//					jqGridColModel.setEditable(false);
//				}
//			}
//			if (colHeader[i].equals("Calctype")) {
//				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
//				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
//				jqGridColModel.setHidden(true);
//			}
//			
//			jqGridTableModel.getColModel().add(jqGridColModel);
//		}
//
//		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
//		tableModel.set("tableHeight", "82%%");
//		tableModel.set("tableWidth", "100%%");
//
//		return tableModel;
//	}

	
	private JSONObject getTableModel(List<String[]> headers, String FormMode, String year, String type,
			String kinkFrequency) {
		CommonMessage.debugMsg("getTableModel---------100");
		CommonMessage.debugMsg("FormMode :" + FormMode + " year " + year + "  type " + type + "Frequency" + kinkFrequency
				+ "headers" + headers);
		/*
		 * for (String[] strings : headers) {
		 * CommonMessage.debugMsg();
		 * for (String string : strings) {
		 * CommonMessage.debugMsg(string + "   ");
		 * }
		 * 
		 * }
		 */

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		int lastYer = Integer.parseInt(year) - 1;
		int lastYer1 = Integer.parseInt(year) - 2;

		String[] colHeader = headers.get(0);
		colHeader[16] = "Remarks";
		colHeader[17] = Integer.toString(lastYer1);
		colHeader[18] = Integer.toString(lastYer);
		/*
		 * if("Y".equals(type)) colHeader[17] = "Remarks"; else colHeader[17]
		 * =Integer.toString(lastYer1);
		 */
		/*
		 * CommonMessage.debugMsg("headers.get(0):" + headers.get(0));
		 * CommonMessage.debugMsg("headers Completed");
		 */
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		jqGridTableModel.getRowHeaders().add(colHeader);
		// jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(1000);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();

			jqGridColModel.setWidth(299);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i <= 10 || i == 15) {
				jqGridColModel.setHidden(true);
			} else if (i == 11) {
				// jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			} else if (i == 12) {
				jqGridColModel.setWidth(60);
				jqGridColModel.setAlign("left");
				// jqGridColModel.setHidden(true);
			} else if (i == 13) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			} else if (i == 14) {
				jqGridColModel.setWidth(40);
				jqGridColModel.setAlign("left");
			}
			if (i == 7) {
				// jqGridColModel.setKey(true);
			}
			
			if (i <= 14) {
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setFrozen(true);
			} else if (i > 14 && !colHeader[i].equals("Calctype") /* && !colHeader[i].equals("SumOrAvg") */) {
				// CommonMessage.debugMsg("2000000000000000");
				CommonMessage.debugMsg("colHeader[" + i + "] " + colHeader[i]);

				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + "_"
						+ i);
				jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + "_"
						+ i);
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("right");
				jqGridColModel.setFormatter("txtFormatter");
				jqGridColModel.setCellattr("0");
				if (i == 15) {
					jqGridColModel.setFrozen(true);
				}
				if (i == 16) {
					if ("target".equals(FormMode))
						jqGridColModel.setHidden(true);
					else {
						jqGridColModel.setFormatter("txtButton");
						jqGridColModel.setFrozen(true);
					}
						

				}
				if (colHeader[i].equals("SumOrAvg") && kinkFrequency.equals("M")) {
					jqGridColModel.setHidden(true);
					jqGridColModel.setEditable(false);
				}
			}
			if (colHeader[i].equals("Calctype")) {
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setHidden(true);
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "82%%");
		tableModel.set("tableWidth", "100%%");

		return tableModel;
	}
	private JSONObject getTableModelExcel(List<String[]> headers, String FormMode,	String year, String type,String kinkFrequency) {
		CommonMessage.debugMsg("getTableModel---------100");
		CommonMessage.debugMsg("FormMode :" + FormMode + " year " + year	+ "  type " + type);
/*
		for (String[] strings : headers) {
			CommonMessage.debugMsg();
			for (String string : strings) {
				CommonMessage.debugMsg(string + "   ");
			}

		}*/

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		int lastYer = Integer.parseInt(year) - 1;
		int lastYer1 = Integer.parseInt(year) - 2;

		String[] colHeader = headers.get(0);
		colHeader[16] = "Remarks";
		colHeader[17] = Integer.toString(lastYer1);
		colHeader[18] = Integer.toString(lastYer);
		/*
		 * if("Y".equals(type)) colHeader[17] = "Remarks"; else colHeader[17]
		 * =Integer.toString(lastYer1);
		 */
		/*
		 * CommonMessage.debugMsg("headers.get(0):" + headers.get(0));
		 * CommonMessage.debugMsg("headers Completed");
		 */
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		jqGridTableModel.getRowHeaders().add(colHeader);
		// jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(1000);
				
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();

			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
				
			if (i <= 9 || i == 15) {
				jqGridColModel.setHidden(true);
			} else if (i == 11) {
				// jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			} else if (i == 12) {
				jqGridColModel.setWidth(60);
				jqGridColModel.setAlign("left");
				// jqGridColModel.setHidden(true);
			} else if (i == 13) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			} else if (i == 14) {
				jqGridColModel.setWidth(40);
				jqGridColModel.setAlign("left");
			}
			if (i == 7) {
				// jqGridColModel.setKey(true);
			}
			if (i <= 14) {
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			} else if (i > 14 && !colHeader[i].equals("Calctype") /*&& !colHeader[i].equals("SumOrAvg")*/) {
				//CommonMessage.debugMsg("2000000000000000");
				CommonMessage.debugMsg("colHeader["+i+"] "+ colHeader[i]);
				
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + "_"
						+ i);
				jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + "_"
						+ i);
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("right");
				jqGridColModel.setFormatter("txtFormatter");
				jqGridColModel.setCellattr("0");

				if (i == 16) {
					if ("target".equals(FormMode))
						jqGridColModel.setHidden(true);
					else
						jqGridColModel.setFormatter("txtButton");

				}
				if (colHeader[i].equals("SumOrAvg") && kinkFrequency.equals("M")) {
					jqGridColModel.setHidden(true);
					jqGridColModel.setEditable(false);
				}
			}
			if (colHeader[i].equals("Calctype")) {
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setHidden(true);
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "82%%");
		tableModel.set("tableWidth", "100%%");

		return tableModel;
	}
	private void LoadCostOfQualityDailyEntry(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		String userEvent = request.getParameter("userEvent");
		String keyid = request.getParameter("keyId");
		String year = request.getParameter("year");
		String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
		String flid = request.getParameter("flid");
		String factId = request.getParameter("factId");
		String sectionId = request.getParameter("sectionId");
		String cellId = request.getParameter("cellId");
		String Uom = request.getParameter("Uom");
		String pillarId = request.getParameter("pillarId");
		String indicatorId = request.getParameter("indicatorId");
		String monthYear = request.getParameter("monthYear");
		String isActual = request.getParameter("isActual");
		String frmMode = request.getParameter("kaukMode");
		String excellenceVal = request.getParameter("excellenceVal");
		String benchMarkVal = request.getParameter("benchMarkVal");
		String kaukIndicatorName = "";
		FormModes mode = FormModes.create;
		String kaukMode = request.getParameter("mode");
		// CommonMessage.debugMsg("monthYear................."+monthYear);
		if (formMode != null && formMode.equalsIgnoreCase(FormModeConsts.view))
			mode = FormModes.view;
		else if (formMode != null
				&& formMode.equalsIgnoreCase(FormModeConsts.modify))
			mode = FormModes.modify;

		KpiTlActualKkBean kpiTlActualKkBean = new KpiTlActualKkBean(mode);

		httpSession.removeAttribute("kaukKeyId");
		httpSession.removeAttribute("kpiTlActualKkBean");
		httpSession.removeAttribute("kpiTlActualKk");
		httpSession.removeAttribute("kaukMode");

		KpiTlActualKk kpiTlActualKk = new KpiTlActualKk();
		KpiTlIndicatorKk newKpiTlIndicatorKk = new KpiTlIndicatorKk();
		try {
			if (CommonFunctions.isValidKeyId(isActual))
				kpiTlActualKk.setKaukIsactual(isActual);
			if (CommonFunctions.isValidKeyId(year))
				kpiTlActualKk.setKaukCalendaryear(year);

			kpiTlActualKk.setKaukDeptid("");
			kpiTlActualKk.setKaukDepttype("");

			/*
			 * if (CommonFunctions.isValidKeyId(factId))
			 * kpiTlActualKk.setKaukFactoryid(factId); if
			 * (CommonFunctions.isValidKeyId(sectionId))
			 * kpiTlActualKk.setKaukSectionid(sectionId); if
			 * (CommonFunctions.isValidKeyId(cellId))
			 * kpiTlActualKk.setKaukCellid(cellId);
			 */
			if (CommonFunctions.isValidKeyId(flid))
				kpiTlActualKk.setKaukDeptid(flid);
			if (CommonFunctions.isValidKeyId(pillarId))
				kpiTlActualKk.setKaukPillarid(pillarId);
			if (CommonFunctions.isValidKeyId(benchMarkVal))
				kpiTlActualKk.setKaukBenchmarkvalue(benchMarkVal);
			if (CommonFunctions.isValidKeyId(excellenceVal))
				kpiTlActualKk.setKaukExcellencevalue(excellenceVal);
			if (CommonFunctions.isValidKeyId(indicatorId)) {
				kpiTlActualKk.setKaukIndicatorid(indicatorId);
				newKpiTlIndicatorKk.setKinkKeyid(indicatorId);
			}

			if (UIUtils.isValidKeyId(Uom))
				request.setAttribute("Uom", Uom);
			if (CommonFunctions.isValidKeyId(monthYear))
				kpiTlActualKk.setKaukMonthyear(monthYear);
			newKpiTlIndicatorKk = kpiTlIndicatorKkService
					.select(newKpiTlIndicatorKk);
			kaukIndicatorName = newKpiTlIndicatorKk.getKinkIndicatorname();
			kpiTlActualKk = (KpiTlActualKk) UIUtils.setBeanProperties(
					kpiTlActualKk, request);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("kpiTlActualKk", kpiTlActualKk);
		request.setAttribute("kpiTlActualKkBean", kpiTlActualKkBean);
		request.setAttribute(ReqtParamNameConst.FORM_MODE, mode);
		request.setAttribute("mode", kaukMode);
		request.setAttribute("kaukMonthKeyId", keyid);
		request.setAttribute("kaukDeptid", flid);
		request.setAttribute("frmMode", frmMode);
		request.setAttribute("manualCalctype",
				newKpiTlIndicatorKk.getKinkManualcalctype());
		request.setAttribute("kaukIndicatorName", kaukIndicatorName);
		request.setAttribute("inactMsg", UIUtils.getPropertyValue(
				"com.akranta.tpm.resources.CommonMessages", "inactive-confirm"));
		httpSession.setAttribute("kpiTlActualKk", kpiTlActualKk);
		httpSession.setAttribute("kpiTlActualKkBean", kpiTlActualKkBean);
		httpSession.setAttribute("kaukMode", mode);
	}

	private void LoadCostOfQualityEntry(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession,
			String pillar, String frmMode) throws IOException, ServletException {
		String userEvent = request.getParameter("userEvent");
		String keyid = request.getParameter("keyId");
		String year = request.getParameter("year");
		String type = request.getParameter("type");
		if (!CommonFunctions.isValidKeyId(type)) {
			type = "KPI";
		}
		
		  String loginflid=CommonFunctions.getLoginFlid(request);
		  String loginlevel=CommonFunctions.getLoginLevel(request);
		  String loginElementid = (String) httpSession.getAttribute("loginElementid");
		  AdmTlUsermst user=UIUtils.getLoginUser(request);
	      String empId=user.getUsrm_ccno();
	      try{
		  List<String []> getUserLoginDtl= kpiTlActualKkService.getElementId(loginflid,loginlevel, loginElementid,empId);
		String elementid = getUserLoginDtl.get(0)[0];
		String flidnew = getUserLoginDtl.get(0)[1];
		String level = getUserLoginDtl.get(0)[2];
		rolename=getUserLoginDtl.get(0)[3];
		String roledetail=rolename;
	    String rolekeyid=getUserLoginDtl.get(0)[4];
	      }
	      catch(Exception e){
	    	  e.printStackTrace();
	      }
		
		
		CommonMessage.debugMsg("The RoleName:::"+rolename);
		
		

		String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
		FormModes mode = FormModes.create;
		String kaukMode = request.getParameter("mode");
		String pillarId = "";
		/* CommonMessage.debugMsg("kaukMode................."+kaukMode); */
		if (formMode != null && formMode.equalsIgnoreCase(FormModeConsts.view))
			mode = FormModes.view;
		else if (formMode != null
				&& formMode.equalsIgnoreCase(FormModeConsts.modify))
			mode = FormModes.modify;
		
		String Location=CommonFunctions.getLoginLocaton(request);
		CommonMessage.debugMsg("Location:::"+Location);
		glbLocation=Location;
		String logFlid=CommonFunctions.getLoginFlid(request);
		CommonMessage.debugMsg("Flid:::"+logFlid);
		String kpiDate=CommonFunctions.getDate();
		CommonMessage.debugMsg("The kpiDate"+kpiDate);
		String currentmonth=CommonFunctions.getCurrentMonth();
		CommonMessage.debugMsg("Current Month::;:"+currentmonth);
		String CurrYear=CommonFunctions.getCurrentYear();
		CommonMessage.debugMsg("CurrYear"+CurrYear);
		String CurrMonthYear=currentmonth+"-"+CurrYear;
		CommonMessage.debugMsg("The CurrMonthYear"+CurrMonthYear);

		KpiTlActualKkBean kpiTlActualKkBean = new KpiTlActualKkBean(mode);

		String factId = request.getParameter("factId");
		String sectionId = request.getParameter("sectionId");
		String cellId = request.getParameter("cellId");
		String pilId = request.getParameter("pillarId");
		String isActual = request.getParameter("isActual");
		String YearVal = request.getParameter("year");
		String filter = request.getParameter("filter");
		request.setAttribute("factId", factId);
		request.setAttribute("sectId", sectionId);
		request.setAttribute("cellId", cellId);
		request.setAttribute("pillarId", pilId);
		request.setAttribute("isActual", isActual);
		request.setAttribute("year", YearVal);
		request.setAttribute("filter", filter);
		
        
		httpSession.removeAttribute("kaukKeyId");
		httpSession.removeAttribute("kpiTlActualKkBean");
		httpSession.removeAttribute("kpiTlActualKk");
		httpSession.removeAttribute("kaukMode");

		KpiTlActualKk kpiTlActualKk = new KpiTlActualKk();
		if (!CommonFunctions.isValidKeyId(year)) {
			year = CommonFunctions.getCurrentYear();
			kpiTlActualKk.setKaukCalendaryear(year);
		}
		try {
			pillarId = kpiTlActualKkService.getPillarKeyId(pillar);
			kpiTlActualKk.setKaukPillarid(pillarId);
			kpiTlActualKk = (KpiTlActualKk) UIUtils.setBeanProperties(
					kpiTlActualKk, request);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("kpiTlActualKk", kpiTlActualKk);
		request.setAttribute("kpiTlActualKkBean", kpiTlActualKkBean);
		request.setAttribute(ReqtParamNameConst.FORM_MODE, mode);
		request.setAttribute("mode", kaukMode);
		request.setAttribute("frmMode", frmMode);
		request.setAttribute("kaukPillar", pillar);
		request.setAttribute("type", type);
		request.setAttribute("logFlid",logFlid);
		request.setAttribute("kpiDate",kpiDate);
		request.setAttribute("CurrMonthYear",CurrMonthYear);
		request.setAttribute("rolename",rolename);
		request.setAttribute("inactMsg", UIUtils.getPropertyValue(
				"com.akranta.tpm.resources.CommonMessages", "inactive-confirm"));
		request.setAttribute("glbLocation",glbLocation);
		httpSession.setAttribute("kpiTlActualKk", kpiTlActualKk);
		httpSession.setAttribute("kpiTlActualKkBean", kpiTlActualKkBean);
		httpSession.setAttribute("kaukMode", mode);
           
		// request.setAttribute("flid","FNLN00000161");
		RequestDispatcher rd = request
				.getRequestDispatcher("/pages/KPI/KpiTlActualKk.jsp");
		rd.forward(request, response);
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request,
			String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession
				.getAttribute(beanIdentifier);
		if (commonFilter != null && !createNew) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();
			commonFilter = FilterValues.getCommonFilters(request, commonFilter); //
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}

		return commonFilter;
	}

	private JSONObject getRemarksTableModel(String[] colIndex,
			String[] colHeader, char type, boolean enableFilter,
			boolean tableButton) {// List<String[]> headers) {
		// CommonMessage.debugMsg("colIndex :" +colIndex[0]+ "  "
		// +colIndex[1]+ "    "+ colIndex[2]);
		// CommonMessage.debugMsg("colHeader :" +colHeader[0]+ "  "
		// +colHeader[1]+ "    "+ colHeader[2]);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableButton(tableButton);
		jqGridTableModel.setEnableFilter(enableFilter);

		for (int i = 0; i < colIndex.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
			jqGridColModel.setName(jqGridColModel.getIndex());
			jqGridColModel.setWidth(150);
			jqGridColModel.setAlign("left");
			if ((i == 0 || i == 1 || i == 5)) {
				jqGridColModel.setHidden(true);
				// jqGridColModel.setFormatter("txtcmbKprmKeyidFormatter");
				// jqGridColModel.setIndex("cmbKprmKeyid");
				// jqGridColModel.setName("cmbKprmKeyid");
			}
			if (i == 1) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setFormatter("txtcmbKprmKeyidFormatter");
				// jqGridColModel.setIndex("cmbKprmKeyid");
				// jqGridColModel.setName("cmbKprmKeyid");
			}
			if ((i == 3)) {
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				jqGridColModel.setFormatter("txaRemarksFormatter");
			}
			if (i == 4) {
				jqGridColModel.setAlign("center");
				jqGridColModel.setWidth(78);
				jqGridColModel.setFormatter("txtcmbKprmDateFormatter");
			}
			if (i == 2) {
				jqGridColModel.setAlign("center");
				jqGridColModel.setWidth(60);
				jqGridColModel.setFormatter("txtChkBoxFormatter");
			}

			jqGridTableModel.getColModel().add(jqGridColModel);

		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "90%%");
		tableModel.set("tableWidth", "45%%");

		return tableModel;
	}

}