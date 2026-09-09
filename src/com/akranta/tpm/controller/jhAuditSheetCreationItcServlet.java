package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
/*import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.BatchBean;*/
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartPie;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.ColumnChart;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JhAuditCreationBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.JhaTlAuditdtl;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAuditparameter;
import com.akranta.tpm.model.JhaTlAuditreportdtl;
import com.akranta.tpm.model.JhaTlAuditreportmst;
import com.akranta.tpm.model.JhaTlAudittemplate;
import com.akranta.tpm.model.JhaTlTemplategradelink;
import com.akranta.tpm.model.JhaTlTemplatelevellink;
import com.akranta.tpm.model.JhaTlTemplatemchlink;
import com.akranta.tpm.model.JhaTlTemplatesteplink;
import com.akranta.tpm.model.KznTlEvaluationmst;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.service.JHAuditSheetCreationItcService;
import com.akranta.tpm.service.jhAuditElementReportService;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;
import com.akranta.tpm.service.impl.jhAuditElementReportServiceImpl;
import com.akranta.tpm.service.impl.JHAuditSheetCreationItcServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class jhAuditSheetCreationItcServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	
	//CommonFilter commonFilter;
	JHAuditSheetCreationItcServiApi jHAuditSheetCreationItcServiApi;
	JHAuditSheetCreationItcService jhAuditSheetCreationItcService;
	jhAuditElementReportService jhAuditElementReportService;
	private static final String AdmUploadExcelServlet_filename = "jhAuditSheetCreationItcServletFilename";
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static String realPath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public void init(ServletConfig config) throws ServletException {
	        super.init(config);
	        realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
	        boolean s = new File(realPath).mkdirs();
	        
	    }
    
	public jhAuditSheetCreationItcServlet()
	{
		super();
		/*try{
			jhAuditSheetCreationItcService = new JHAuditSheetCreationItcServiceImpl();
		
		}catch(Exception e)		{
			
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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unused", "unused", "unused" })
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession httpSession = request.getSession(false);		
		String action = UIUtils.getActionPart(request);
		String dispatchUrl = null;
		ComboFilter comboFilter = new ComboFilter();
		try {
			
			
			jhAuditSheetCreationItcService = (JHAuditSheetCreationItcServiceImpl)UIUtils.getServiceObject(request,"JHAuditSheetCreationItcServiceImpl");
			jhAuditElementReportService= (jhAuditElementReportServiceImpl)UIUtils.getServiceObject(request,"jhAuditElementReportServiceImpl");
			CommonMessage.debugMsg("  jh AND dmt AUDIT jwt token : "+httpSession.getAttribute("tpmjwttoken") );
			jhAuditSheetCreationItcService.JHAuditSheetCreationItcServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		
			
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		//CommonMessage.debugMsg("Action:"+action);
		if(action.equals("jhAuditSheetGrid_input.jhAuditItc") || action.equals("dmtAuditSheetGrid_input.jhAuditItc")) //
		{	String jhamAuditpillar = request.getParameter("TYPE");
			String jhamAudittype = request.getParameter("AUDIT");
			httpSession.setAttribute("jhamAuditpillar", jhamAuditpillar);			
			request.setAttribute("jhamAuditpillar", jhamAuditpillar);
			httpSession.setAttribute("jhamAudittype", jhamAudittype);
			request.setAttribute("jhamAudittype", jhamAudittype);
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/JHAudit/jhAuditSheetCreationItc.jsp"); 
			rd.forward(request, response);
		}
		
		else if(action.equals("jhAuditSheetGrid_getCol.jhAuditItc")||action.equals("dmtAuditSheetGrid_getCol.jhAuditItc")){
			PrintWriter out = response.getWriter();
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "colModelJhAuditSheet");
			httpSession.removeAttribute("colModeljhAuditSheet");
			httpSession.setAttribute("colModeljhAuditSheet",colModel);
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "colModelJhAuditSheet"));
		}
		
		else if(action.equals("jhAuditSheetGrid_getData.jhAuditItc")||action.equals("dmtAuditSheetGrid_getData.jhAuditItc")){
			PrintWriter out = response.getWriter();
			try
			{	
				String jhamAuditpillar = request.getParameter("TYPE");
				String jhamAudittype = request.getParameter("AUDIT");
				String flid = request.getParameter("flid");
				 UIUtils.displayRequestParamsValue(request);
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"jhAuditSheetCreation",true);			
				 JSONObject jsonObject = new JSONObject();
				 if(!UIUtils.isValidKeyId(flid)){
					 flid=CommonFunctions.getLoginFlid(request);
				 }
				 commonFilter.setFlid(flid);
				 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
			 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
				 }	
				 commonFilter.setKey(jhamAuditpillar);
				 commonFilter.setType(jhamAudittype);
				 List< String[]> jhAuditSheetCreation  = jhAuditSheetCreationItcService.getjhAuditSheetfillGrid(commonFilter);
				 jsonObject = UIUtils.convertToJqGridTableObject(jhAuditSheetCreation,request,1,1,commonFilter.getTotalRecordCnt());
				 
				 out.println(jsonObject);
				 commonFilter.setViewClick('N');	 
				 httpSession.removeAttribute("jhAuditSheetCreation");
	  			 httpSession.setAttribute("jhAuditSheetCreation", commonFilter);
					
				 
	  		}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		else if(action.equals("jhParameter_input.jhAuditItc") || action.equals("dmtParameter_input.jhAuditItc"))
		{
			String jhapAuditpillar = request.getParameter("TYPE");
			String jhapAudittype = request.getParameter("AUDIT");
			httpSession.setAttribute("jhapAuditpillar", jhapAuditpillar);
			request.setAttribute("jhapAuditpillar", jhapAuditpillar);	
			
			httpSession.setAttribute("jhapAudittype", jhapAudittype);
			request.setAttribute("jhapAudittype", jhapAudittype);	
			RequestDispatcher rd = request.getRequestDispatcher("/pages/JHAudit/JHParameterItc.jsp");
			rd.forward(request, response);
		}		
		
		else if(action.equals("jhAuditElementReportGrid_view.jhAuditItc"))
		{
			UIUtils.forwardRequest(request, response, "/pages/jhaAuditElementReportGrid.jsp");
		}
		else if( action.equals("getModejhAuditElementReportGrid_view.jhAuditItc")){
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("mode","create");
			result.put("url","jhAuditElementReport_input.jhAuditItc");
			result.put("formHeader","Audit Element Report");
			out.println(result);
		}else if(action.equals("jhAuditElementReportGrid_getCol.jhAuditItc"))
		{
			try{
				CommonFilter commonFilter = populateCommonFilter(request,"AuditElementReportGrid",true);
				
				PrintWriter out = response.getWriter();
				List<String []> getOtherList  = jhAuditElementReportService.getMainGrid(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true); 
				String [] colHeader = getOtherList.get(2);			
				String [] colHeaderCond = getOtherList.get(1);
				
				gridColModel.setHeaderNum(1);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "105%%");
				jsonObject.set("tableHeight", "80%%");
				httpSession.removeAttribute("AuditElementReportGridobj");
				httpSession.setAttribute("AuditElementReportGridobj", jsonObject);
				out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(action.equals("jhAuditElementReportGrid_getData.jhAuditItc"))
		{
			try
			{
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"AuditElementReportGrid",false);
				List<String []> getOtherList  = jhAuditElementReportService.getMainGrid(commonFilter);
  			 	JSONObject OtherLossJson = UIUtils.convertToJqGridTableObject(getOtherList,request,3,0,commonFilter.getTotalRecordCnt()+3); 
  			 	out.println(OtherLossJson);
  			 	httpSession.removeAttribute("AuditElementReportGrid");
		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}	
		}else if(action.equals("jhAuditElementReportGrid_getExcel.jhAuditItc"))
		{
			try
			{
				CommonFilter commonFilter=populateCommonFilter(request, "AuditElementReportGrid",true);
				JSONObject colmodel = UIUtils.getXlColModel(request, response);
				colmodel.put("title","Audit Element Report");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = jhAuditElementReportService.getExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "AuditElementReport", format);
		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}	
		}
		else if(action.equals("jhAuditElementReport_input.jhAuditItc"))
		{
			String keyid=request.getParameter("Keyid");
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			JhaTlAuditreportmst jhaTlAuditreportmst=new JhaTlAuditreportmst();
			if(UIUtils.isValidKeyId(keyid)){
				jhaTlAuditreportmst=jhAuditElementReportService.selectData(keyid);
			}else
			{
				jhaTlAuditreportmst.setAurmPreparedby(user.getUsrm_ccno());
				jhaTlAuditreportmst.setAurmUploadedby(user.getUsrm_ccno());
			}
			request.setAttribute("jhaTlAuditreportmst", jhaTlAuditreportmst);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/jhaAuditElementReport.jsp");
			rd.forward(request, response);		
		}else if(action.equals("jhAuditElementReport_getCol.jhAuditItc"))
		{
			try{
				CommonFilter commonFilter = populateCommonFilter(request,"AuditElementReport",true);
				String mstKeyid=request.getParameter("mstKeyid");
				PrintWriter out = response.getWriter();
				commonFilter.setAuditRpt(mstKeyid);
				List<String []> getOtherList  = jhAuditElementReportService.getGriddata(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(false); 
				String [] colHeader = getOtherList.get(2);			
				String [] colHeaderCond = getOtherList.get(1);
				
				gridColModel.setHeaderNum(1);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "105%%");
				jsonObject.set("tableHeight", "60%%");
				httpSession.removeAttribute("AuditElementReportobj");
				httpSession.setAttribute("AuditElementReportobj", jsonObject);
				out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(action.equals("jhAuditElementReport_getData.jhAuditItc"))
		{
			try
			{
				String mstKeyid=request.getParameter("mstKeyid");
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"AuditElementReport",false);
				commonFilter.setAuditRpt(mstKeyid);
				List<String []> getOtherList  = jhAuditElementReportService.getGriddata(commonFilter);
  			 	JSONObject OtherLossJson = UIUtils.convertToJqGridTableObject(getOtherList,request,3,0,commonFilter.getTotalRecordCnt()+3); 
  			 	out.println(OtherLossJson);
  			 	httpSession.removeAttribute("AuditElementReport");
		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}	
		}else if (action.equals("file_upload.jhAuditItc")) 
		{
			PrintWriter out = response.getWriter();
			InputStream is = null;
	        FileOutputStream fos = null;	
	        String filename = request.getHeader("X-File-Name");
	        if(filename !=null){	        	
	        	filename = filename.replaceAll(" ","_").replaceAll("%20","_");
	        	CommonMessage.debugMsg("fileename: "+filename);
	        }
	        httpSession.removeAttribute(AdmUploadExcelServlet_filename);
			httpSession.setAttribute(AdmUploadExcelServlet_filename,filename);
	        try {
	            is = request.getInputStream();	
	            CommonMessage.debugMsg(realPath + " : file name :" + filename);
	            fos = new FileOutputStream(new File(realPath + filename));
	            IOUtils.copy(is, fos);   
	        } catch (FileNotFoundException ex) {
	            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
	            out.print("{success: false}");
	            CommonMessage.debugMsg(" : file exception :");
	        } catch (IOException ex) {
	            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
	            CommonMessage.debugMsg(" : io exception :");
	            out.print("{success: false}");
	        }  finally {
	            try {
	                fos.close();
	                is.close();
	                fos =null;
	                is =null;
	            } catch (IOException ignored) {
	            }
	        }
		}else if(action.equals("file_save.jhAuditItc"))
		{
			saveAuditEleRep(request, response);		
		}
		else if( action.equals("jhAuditElementReport_save.jhAuditItc"))
		{
			 updatejhAuditElementReport(request,response);
			}
		else if( action.equals("jhAuditElementReport_delete.jhAuditItc"))
		{
			 deletejhAuditElementReport(request,response);
		}
		else if( action.equals("jhAuditElementExcel_delete.jhAuditItc"))
		{
			 deletejhAuditElementExlData(request,response);
		}	
		else if(action.equals("functionalLocDMT.jhAuditItc"))
		{	
			
			String type = request.getParameter("type");	
			CommonMessage.debugMsg(">>> >>>>>>>>Type from request = " + type);

			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setFactMandatory(false);			
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			CommonMessage.debugMsg("vctype:"+type);
			functLocFieldNameBean.setMachMandatory(false);
			//sriram
			//if ("DMT".equalsIgnoreCase(type)) {
			    functLocFieldNameBean.setCellDisable(true);
			    functLocFieldNameBean.setMachDisable(true);
			//}

			FormModes formModes = FormModes.create;					
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);			
		}
		else if(action.equals("functionalLoc.jhAuditItc"))
		{	
			String type = request.getParameter("type");	
			CommonMessage.debugMsg("> >> >> >>>>>>>>Type from request = " + type);
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();				
			functLocFieldNameBean.setCompany("cmbComp");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setFactMandatory(false);	
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);	
			//sriram 
			functLocFieldNameBean.setMachDisable(true);
			FormModes formModes = FormModes.create;					
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);			
		}
		else if(action.equals("jhLevel_input.jhAuditItc"))
		{
			
		}
		else if(action.equals("jhLevel_getCol.jhAuditItc"))
		{
			PrintWriter out = response.getWriter();
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "colModelJhAuditLevel");
			httpSession.removeAttribute("colModelLevel");
			httpSession.setAttribute("colModelLevel",colModel);
			out.println(colModel);			
		
		}
		else if(action.equals("jhLevel_getData.jhAuditItc"))
			
		{
			CommonMessage.debugMsg("ENTERED GET DATA");
			CommonMessage.debugMsg("jhLevel_getData.jhAuditItc:");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			String templateId = request.getParameter("templateId");
			String jhapAuditpillar = request.getParameter("jhapAuditpillar");
			CommonMessage.debugMsg("templateId:"+templateId);
			CommonFilter commonFilter = populateCommonFilter(request,"jhAuditParamter",true);
			commonFilter.setJhTemplateId(templateId);
			List< String[]> jhAuditLevel  = jhAuditSheetCreationItcService.getjhAuditLevelGrid(commonFilter);	
			CommonMessage.debugMsg("DATAS "+jhAuditLevel.toString());
			jsonObject = UIUtils.convertToJqGridTableObject(jhAuditLevel,request,0,0);			 
			out.println(jsonObject);
			commonFilter.setViewClick('N');	 
			httpSession.removeAttribute("colModelLevel");		
		}
		
		else if(action.equals("jhTemplate_input.jhAuditItc"))
		{			
		}
		
		else if(action.equals("jhTemplate_getCol.jhAuditItc"))
		{
			PrintWriter out = response.getWriter();
			String scWidth = request.getParameter("scWidth");
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "colModelJhParameter");
			JSONObject jsonColModel=JSONObject.fromString(colModel);
			if( Integer.parseInt(scWidth)<= 1024){
				jsonColModel.set("tableHeight", "48%%");
			}	
			else{
				jsonColModel.set("tableHeight", "64%%");
			}
			httpSession.removeAttribute("colModelTemplate");
			httpSession.setAttribute("colModelTemplate",colModel);
			out.println(jsonColModel);		
		}
		
		else if(action.equals("jhTemplate_getData.jhAuditItc"))
		{
			PrintWriter out = response.getWriter();
			try
			{	//String jhapAuditpillar = (String)httpSession.getAttribute("jhapAuditpillar");
				
				 UIUtils.displayRequestParamsValue(request);
				 httpSession = request.getSession();
				 String templateId = request.getParameter("templateId");				
				 CommonFilter commonFilter = populateCommonFilter(request,"jhAuditParamter",true);
				
				 commonFilter.setJhTemplateId(templateId);
				 
				 JSONObject jsonObject = new JSONObject();
				 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
			 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
				 }	
				 List< String[]> jhAuditSheetCreation  = jhAuditSheetCreationItcService.getjhAuditParamterGrid(commonFilter);
				 jsonObject = UIUtils.convertToJqGridTableObject(jhAuditSheetCreation,request,0,0,commonFilter.getTotalRecordCnt());
				 
				 out.println(jsonObject);
				 commonFilter.setViewClick('N');	 
				 httpSession.removeAttribute("jhAuditParamter");
	  			 httpSession.setAttribute("jhAuditParamter", commonFilter);
					
				 
	  		}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
			
		}		
		else if(action.equals("jhaudit_recall.jhAuditItc")) 
		{
			String jhamAuditpillar = request.getParameter("jhamAuditpillar");
			String jhamAudittype = request.getParameter("jhamAudittype");
			String jhamAuditteamid = request.getParameter("templateid");
			String jhamFlid = request.getParameter("flid");
			String jhamJhstepid = request.getParameter("stepid");
			String jhamAuditdate = request.getParameter("auditdate");
			String jhamAuditortype = request.getParameter("auditortype");
			CommonMessage.debugMsg("jhamAudittype..."+jhamAudittype);
			CommonMessage.debugMsg("jhamAuditpillar..."+jhamAuditpillar);
			CommonMessage.debugMsg("jhamAuditteamid..."+jhamAuditteamid);
			CommonMessage.debugMsg("jhamFlid..."+jhamFlid);
			CommonMessage.debugMsg("jhamJhstepid..."+jhamJhstepid);
			CommonMessage.debugMsg("jhamAuditdate..."+jhamAuditdate);
			CommonMessage.debugMsg("jhamAuditortype..."+jhamAuditortype);
			response.setContentType("text/html");
			if( UIUtils.isValidKeyId(jhamAuditpillar)
					&&  UIUtils.isValidKeyId(jhamAudittype)
					&&  UIUtils.isValidKeyId(jhamAuditteamid)
					&&  UIUtils.isValidKeyId(jhamFlid)
					&&  UIUtils.isValidKeyId(jhamJhstepid)
					&&  UIUtils.isValidKeyId(jhamAuditdate)
					&&  UIUtils.isValidKeyId(jhamAuditortype)) 
			{
				CommonMessage.debugMsg("keyid:"+jhamAuditpillar);
				JhaTlAuditmst jhaTlAuditmst=new JhaTlAuditmst();
				jhaTlAuditmst.setJhamAuditpillar(jhamAuditpillar);
				jhaTlAuditmst.setJhamAudittype(jhamAudittype);
				jhaTlAuditmst.setJhamAuditteamid(jhamAuditteamid);
				jhaTlAuditmst.setJhamFlid(jhamFlid);
				jhaTlAuditmst.setJhamJhstepid(jhamJhstepid);
				jhaTlAuditmst.setJhamAuditortype(jhamAuditortype);
				jhaTlAuditmst.setJhamAuditdate(jhamAuditdate);
				String date2 = jhaTlAuditmst.getJhamAuditdate();
				jhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getDateTimeFromDate(date2));
				//jhaTlAuditmst.setJhamAuditdate(UIUtils.getActualDateForm(jhaTlAuditmst.getJhamAuditdate()));
				jhaTlAuditmst.setJhamTotalpoints("0");
				jhaTlAuditmst.setJhamNextauditdate(CommonFunctions.pg_getDateTimeFromDate(date2));
				jhaTlAuditmst.setJhamCreatedon(CommonFunctions.pg_getDateTimeFromDate(date2));
				jhaTlAuditmst.setJhamModifiedon(CommonFunctions.pg_getDateTimeFromDate(date2));
				
				
				jhaTlAuditmst=  jhAuditSheetCreationItcService.select(jhaTlAuditmst);
				
				String date3 = jhaTlAuditmst.getJhamAuditdate();
				jhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date3));
				jhaTlAuditmst.setJhamAuditdate(UIUtils.getActualDateForm(jhaTlAuditmst.getJhamAuditdate()));
				
			
				/*JhaTlAuditparameter jhaTlAuditparameter=new JhaTlAuditparameter();
				//String gridmode=request.getParameter("hdnFrmMode");
				JhAuditCreationBean jhAuditCreationBean= new JhAuditCreationBean();				
				String minMarks =jhAuditSheetCreationItcService.getMinMarks(jhaTlAuditmst.getJhamJhstepid() ,jhaTlAuditmst.getJhamAuditteamid());
				jhaTlAuditparameter.setJhapKeyid(jhaTlAuditmst.getJhamAuditteamid());
				jhaTlAuditparameter = jhAuditSheetCreationItcService.recallValues(jhaTlAuditparameter);
				request.setAttribute("minMarks",minMarks);
				httpSession.setAttribute("jhaTlAuditparameter", jhaTlAuditparameter);
				request.setAttribute("jhaTlAuditparameter", jhaTlAuditparameter);*/
				JSONObject successData = new JSONObject(); 
				successData.put("keyId",jhaTlAuditmst.getJhamKeyid());
				ServletOutputStream out = response.getOutputStream();
				JSONObject returnData = new JSONObject();
				returnData.put("jhaTlAuditmst", jhaTlAuditmst);
				returnData.put("successData", successData);
				out.print(returnData.toString());
				out.close();
			}			
		}
		//Done by Mohammad Azeem Baba to View Last 3 Audits
	
		else if(action.equals("jhAuditCreation_input.jhAuditItc")) 
		{
			CommonMessage.debugMsg("AuditCreation Is.............."+action);
			String keyid=request.getParameter("keyId");
			String mode = request.getParameter("mode");
			String jhamAuditpillar = request.getParameter("jhamAuditpillar");
			CommonMessage.debugMsg("Audit Pillar Is.............."+jhamAuditpillar);     
			String jhamAudittype = request.getParameter("jhamAudittype");
			CommonMessage.debugMsg("Audit Type Is.............."+jhamAudittype);
			CommonFilter commonFilter =new CommonFilter();
			CommonMessage.debugMsg("jhamAudittype..."+jhamAudittype);
			response.setContentType("text/html");
			if( UIUtils.isValidKeyId(keyid)) //&& userEvent == null) ||( userEvent != null &&  ! "new".equals(userEvent))
			{
				CommonMessage.debugMsg("keyid:"+keyid);
				JhaTlAuditmst jhaTlAuditmst=new JhaTlAuditmst();
				jhaTlAuditmst.setJhamKeyid(keyid);
				
//				String date4 = jhaTlAuditmst.getJhamAuditdate();
//				if(date4!=null) {
//					CommonMessage.debugMsg("4 date null check");
//				jhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getDateTimeFromDate(date4));
//				}
				
				jhaTlAuditmst=  jhAuditSheetCreationItcService.select(jhaTlAuditmst);
				
				//08-jan
				String date = jhaTlAuditmst.getJhamAuditdate();
			    String date1 = CommonFunctions.pg_getDateTimeFromPGTimeStamp(date);
				jhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));
				
				JhaTlAuditparameter jhaTlAuditparameter=new JhaTlAuditparameter();
				//String gridmode=request.getParameter("hdnFrmMode");
				CommonMessage.debugMsg("mode..."+mode);
				
				FormModes modes = FormModes.view;
				if("EDIT".equals(mode))
				{
					modes = FormModes.modify;					
				}
				//String auditLevel= jhAuditSheetCreationItcService.getAuditLevelCurrent(commonFilter);
				JhAuditCreationBean jhAuditCreationBean= new JhAuditCreationBean();				
				String minMarks =jhAuditSheetCreationItcService.getMinMarks(jhaTlAuditmst.getJhamJhstepid() ,jhaTlAuditmst.getJhamAuditteamid());
				jhaTlAuditparameter.setJhapKeyid(jhaTlAuditmst.getJhamAuditteamid());
				jhaTlAuditparameter = jhAuditSheetCreationItcService.recallValues(jhaTlAuditparameter);
				
				request.setAttribute("minMarks",minMarks);
				httpSession.setAttribute("jhaTlAuditmst", jhaTlAuditmst);
				httpSession.setAttribute("jhaTlAuditparameter", jhaTlAuditparameter);
				request.setAttribute("jhaTlAuditmst", jhaTlAuditmst);
				request.setAttribute("jhamAuditpillar", jhamAuditpillar);
				request.setAttribute("jhamAudittype", jhamAudittype);
				request.setAttribute("jhaTlAuditparameter", jhaTlAuditparameter);
				request.setAttribute("jhAuditCreationBean",jhAuditCreationBean);
				request.setAttribute("mode",FormModes.modify);
			}
			else{
				request.setAttribute("mode",FormModes.create);
				request.setAttribute("jhamAuditpillar", jhamAuditpillar);
				request.setAttribute("jhamAudittype", jhamAudittype);
			}
			request.setAttribute("jhamAuditpillar",jhamAuditpillar);			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/JHAudit/jhNewAuditCreationItc.jsp"); 
			rd.forward(request, response);
		}
		else if(action.equals("jhAuditCreation_pieChart.jhAuditItc")){
			PrintWriter out = response.getWriter();	
			ChartPie pieChart =  new   ChartPie();
			CommonMessage.debugMsg("inside Pie chart...   ");
			
			String title ="";
			String[] colHeader = {"50 TPH COAL PLANT","6"};
			String[] colHeader5 = {"50 TPH COAL PLANT","5"};
			String[] colHeader1 = {"CLO2 PLANT","3"};
			String[] colHeader2 = {"FIBRELINE 1","7"};
			String[] colHeader3 = {"FIBRELINE 2","2"};
			String[] colHeader4 = {"CAUSTICIZING","4"};
			
			String forDashboard = request.getParameter("dashboard");
			CommonMessage.debugMsg("forDashboard....."+forDashboard);
			List<String[]> graphData  = new ArrayList<String[]>();
			//if( ! "true".equals(forDashboard)){
			
			
			graphData.add(colHeader);
			graphData.add(colHeader1);
			graphData.add(colHeader2);
			graphData.add(colHeader3);
			graphData.add(colHeader4);
			graphData.add(colHeader5);
			//}
			String []  header = graphData.get(0);
		
			List<String> xCategories = new ArrayList<String>();
			List<Double> dataLine = new ArrayList<Double>();//declaring variable for line chart
			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();//declaring variable for charseries were the data is to bound
			List<Object[]> dataList = new ArrayList<Object[]>();
			int rCnt=0;
			for(String [] row :  graphData){
				if( rCnt++ <= 0  ) continue;
				Object []  data = new Object[2];
				data[ 0 ] =  row[0];
				data[ 1 ] = Double.parseDouble(row[1]);
				dataList.add(data);
			}
			
			ChartSeries chartSeries = new ChartSeries();
			chartSeries.setName(header[1]);
			chartSeries.setType(ChartTypes.PIE);
			chartSeries.setData(dataList);
			chartSeriesList.add(chartSeries);
			chartSeries.setSize(200);
			JSONObject chartObj = null;
			chartObj = pieChart.drawChart(chartSeriesList, "JH Audit Score", "");
			UIUtils.dashBoardSetChartObject(request,chartObj);
			CommonMessage.debugMsg(chartSeriesList.size());
			out.println(chartObj );
			out.close();
		}
		
		else if(action.equals("jhAuditUpload_input.jhAuditItc")){
			
			//PrintWriter out=response.getWriter();
			
           CommonMessage.debugMsg("Am in action of jhAuditUpload"+action);
          
           RequestDispatcher rd= request.getRequestDispatcher("/pages/JHAudit/JHAuditUpload.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("JhAuditLastThree_input.jhAuditItc")){
			
			
			
			CommonMessage.debugMsg("Action isss"+action);
			CommonMessage.debugMsg("Enterd in given Url to Check Last 3 Audits");
			
			String flid=request.getParameter("flid");
			CommonMessage.debugMsg("Flid isss"+flid);
			
			String pillar=request.getParameter("pillar");
			CommonMessage.debugMsg("Pillar isss"+pillar);
			
			String audittype=request.getParameter("audittype");
			CommonMessage.debugMsg("Pillar isss"+audittype);
			
			request.setAttribute("flid", flid);
        	request.setAttribute("pillar", pillar);
        	request.setAttribute("audittype", audittype);
			//dispatchUrl="/pages/JHAudit/JHAuditUpload.jsp"; 
			RequestDispatcher rd = request.getRequestDispatcher("/pages/JHAudit/jhNewAuditLastThreeView.jsp"); 
			rd.forward(request, response);
		}
		else if(action.equals("JhAuditLastThree_getCol.jhAuditItc")){
			
			CommonMessage.debugMsg("am in gc of JhAuditLastThree");
		
			PrintWriter out = response.getWriter();
			String pillar = request.getParameter("pillar");
			CommonMessage.debugMsg("JH pillar"+pillar);
			String audittype = request.getParameter("audittype");
			CommonMessage.debugMsg("JH pillar type"+audittype);
			String flid = request.getParameter("flid");
			CommonMessage.debugMsg("JH pillar flid"+flid);
			 UIUtils.displayRequestParamsValue(request);
			CommonFilter commonFilter = populateCommonFilter(request,"JhAuditSheetUploadCommonFilter",true);
			commonFilter.setKey(pillar);
			commonFilter.setType(audittype);
			commonFilter.setFlid(flid);
			List< String[]> jhAuditSheetCreation  = jhAuditSheetCreationItcService.getlastauditgrid(commonFilter);
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);		
			jqGridTableModel.setTableButton(false);
			gridColModel.setHeaderNum(1);	
			String [] colHeader = jhAuditSheetCreation.get(1);	//today
			String [] colHeaderCond = jhAuditSheetCreation.get(0);
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			JSONObject colModel =new JSONObject();
			colModel= UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			colModel.put("tableWidth", "100%%");
			colModel.put("tableHeight", "60%%");
			httpSession.setAttribute("colModeljhAuditSheet", colModel);
			httpSession.removeAttribute("JhAuditSheetUploadCommonFilter");
			httpSession.setAttribute("JhAuditSheetUploadCommonFilter", commonFilter);	
			out.println(colModel);
		
			CommonMessage.debugMsg("Col Model Is"+colModel);
			httpSession.removeAttribute("colModeljhAuditSheet");
			httpSession.setAttribute("colModeljhAuditSheet",colModel);
		
		}
		/*
		 
		 List<String[]> fourquadrantgrid   = newETReportService.getfourquadrant(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(fourquadrantgrid , request, 2, 0,fourquadrantgrid.size());
				out.println(machinegrid);
				httpSession.removeAttribute("FourQuadrantMatrixCommonFilter");
				httpSession.setAttribute("FourQuadrantMatrixCommonFilter", commonFilter);
		 
		 
		 
		 
		 */
		
		
		
		
		else if(action.equals("JhAuditLastThree_getData.jhAuditItc")){
			CommonMessage.debugMsg("Action in gd"+action);
			 PrintWriter out = response.getWriter();
			 try
				{	
					String pillar = request.getParameter("pillar");
					CommonMessage.debugMsg("JH pillar"+pillar);
					String audittype = request.getParameter("audittype");
					CommonMessage.debugMsg("JH pillar type"+audittype);
					String flid = request.getParameter("flid");
					CommonMessage.debugMsg("JH pillar flid"+flid);
					 UIUtils.displayRequestParamsValue(request);
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"JhAuditSheetUploadCommonFilter",false);
					 JSONObject jsonObject = new JSONObject();
					 if(!UIUtils.isValidKeyId(flid)){
						 flid=CommonFunctions.getLoginFlid(request);
						 CommonMessage.debugMsg("JH pillar flid"+flid);
					 }
					 commonFilter.setFlid(flid);
					 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	  }
					 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
				 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
							  commonFilter.setToDate(CommonFunctions.getDate());
					 }	
					 commonFilter.setKey(pillar);
					 commonFilter.setType(audittype);
					 List< String[]> jhAuditSheetCreation  = jhAuditSheetCreationItcService.getlastauditgrid(commonFilter);
					
					 jsonObject = UIUtils.convertToJqGridTableObject(jhAuditSheetCreation,request,2,0,commonFilter.getTotalRecordCnt());
					 out.println(jsonObject);
					 commonFilter.setViewClick('N');	 
					 httpSession.removeAttribute("JhAuditSheetUploadCommonFilter");
		  			 httpSession.setAttribute("JhAuditSheetUploadCommonFilter", commonFilter);
						
					 
		  		}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			
			
		}
			
		else if(action.equals("jhAuditCreation_getCol.jhAuditItc")){
			PrintWriter out = response.getWriter();
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "colModelJhAuditCreation");
			httpSession.removeAttribute("colModelJhAuditCreation");
			httpSession.setAttribute("colModelJhAuditCreation",colModel);
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "colModelJhAuditCreation"));
		}
		else if(action.equals("jhAuditCreation_getData.jhAuditItc")){
			CommonMessage.debugMsg("jhAuditCreation_getData.jhAuditItc");
			PrintWriter out = response.getWriter();
			try
			{
				 UIUtils.displayRequestParamsValue(request);
				 httpSession = request.getSession();
				 String templateId = request.getParameter("templateId");
				 String jhamKeyID = request.getParameter("jhamKeyID");
				 String jhamAuditpillar = request.getParameter("jhamAuditpillar");
				 String jhstepid = request.getParameter("jhstepid");
				 CommonMessage.debugMsg("jhamKeyID:"+jhamKeyID);
				 CommonFilter commonFilter = populateCommonFilter(request,"jhAuditSheetCreation",true);	
				 JhaTlAuditmst jhaTlAuditmst = new JhaTlAuditmst();
				 JSONObject jsonObject = new JSONObject();
				 if(CommonFunctions.isValidKeyId(jhamKeyID) || CommonFunctions.isValidKeyId(templateId)){
					 	List< String[]> jhAuditSheetCreation  = jhAuditSheetCreationItcService.getjhAuditGridSql(jhaTlAuditmst,templateId,jhamKeyID,jhamAuditpillar,jhstepid);
					 	CommonMessage.debugMsg("jhAuditSheetCreation"+jhAuditSheetCreation);
					 	jsonObject = UIUtils.convertToJqGridTableObject(jhAuditSheetCreation,request,0,0,commonFilter.getTotalRecordCnt());
					 	int rowCount = jhAuditSheetCreation.size();
					 	httpSession.setAttribute("totalCnt" , rowCount);
				 }
				 out.println(jsonObject);
				 commonFilter.setViewClick('N');	 
				 httpSession.removeAttribute("jhAuditSheetCreation");
	  			 httpSession.setAttribute("jhAuditSheetCreation", commonFilter);
	  		 }catch(Exception e)
	  		 {
				CommonMessage.debugMsg(e.getMessage());
	  		 }
		}
		else if(action.equals("jhAuditCreation_getExcel.jhAuditItc"))
		{
			httpSession = request.getSession(false);
			
			String flId = request.getParameter("flId");
			String jhamKeyID = request.getParameter("jhamKeyID");
			JhaTlAuditmst jhaTlAuditmst = new JhaTlAuditmst();
			 
			CommonFilter commonFilter = populateCommonFilter(request,"jhAuditSheetCreation",true);
			String tmpFromRow = commonFilter.getFromRow();
			
			int count = (Integer) httpSession.getAttribute("totalCnt");
			String cnt = Integer.toString(count);
			
			commonFilter.setFromRow(null);
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "colModelJhAuditCreation");
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			
			tblJSONObj.put("title", "JHAudit Sheet Creation");
			String format = ExcelUtils.getFormat(request);
			commonFilter.setFromRow(tmpFromRow);
			commonFilter.setToRow(cnt);
			Workbook wb = null;//jhAuditSheetCreationItcService.getjhAuditGridExportExcel(jhaTlAuditmst,flId,jhamKeyID,commonFilter,tblJSONObj,format);
			CommonMessage.debugMsg("check for excel1......."+commonFilter.getFromRow());
			ExcelUtils.writeToResponse(response, wb, "JHAuditSheetCreation", format);
		}
		else if(action.equals("jhAuditCreation_save.jhAuditItc"))
		{	
			JhAuditCreationBean jhAuditCreationBean = new JhAuditCreationBean();
			saveAudit(request,response,jhAuditCreationBean);
		}
		else if(action.equals("gradeid_recall.jhAuditItc")){
			PrintWriter out = response.getWriter();
			String points=request.getParameter("point");
			String keyID =request.getParameter("keyID"); 
			String rowid =request.getParameter("rowId");
			CommonMessage.debugMsg("points....."+points+"...keyID..."+keyID);
			List< String[]> gradeList  = jhAuditSheetCreationItcService.getgradeid(points,keyID);
			//CommonMessage.debugMsg("length....0"+gradeList.size());
			//CommonMessage.debugMsg("length...."+gradeList);
			JSONObject gradeListdata = new JSONObject(); 
			CommonMessage.debugMsg("keyid..."+gradeList.get(0)[0]+"...name..."+gradeList.get(0)[1]+"...");
			
			if(gradeList.size()>0){
				CommonMessage.debugMsg("keyid..."+gradeList.get(0)[0]+"...name..."+gradeList.get(0)[1]+"...");
				gradeListdata.put("keyid", gradeList.get(0)[0]);
				gradeListdata.put("name", gradeList.get(0)[1]);
				gradeListdata.put("rowid", rowid);
				out.print(gradeListdata);
			}		
		}
		else if(action.equals("fillStatus_recall.jhAuditItc")){
			PrintWriter out = response.getWriter();
			String parameter=request.getParameter("parameter");
			String auditTeam =request.getParameter("auditTeam"); 
			String flid =request.getParameter("flid"); 
			String isJhLeader =request.getParameter("isJhLeader"); 
			String rowId =request.getParameter("rowId");
			String jhLeader ="";
			List< String[]> minpointsList  = jhAuditSheetCreationItcService.getMinPoints(parameter,auditTeam);
			if(UIUtils.isValidKeyId(isJhLeader)){
				jhLeader  = jhAuditSheetCreationItcService.getJhLeader(flid);
			}
			JSONObject minpointsListdata = new JSONObject(); 
			if(minpointsList.size()>0){
				minpointsListdata.put("points", minpointsList.get(0)[0]);
				
			}
			
			minpointsListdata.put("jhLeader", jhLeader);
			out.print(minpointsListdata);
		}
		else if(action.equals("jhAuditCreation_delete.jhAuditItc"))
		{
			JhAuditCreationBean jhAuditCreationBean = new JhAuditCreationBean();
			deleteAudit(request,response,jhAuditCreationBean);
		}
		else if(action.equals("jhAuditSheetGrid_getExcel.jhAuditItc"))
		{
			exportToExcel(request,response);
		}
		else if(action.equals("jhAuditCreation_recall.jhAuditItc")){
			PrintWriter out = response.getWriter();
			//String keyid=request.getParameter("keyId");
			String flId=request.getParameter("flId");			
			String mode = request.getParameter("mode");
			String minMarks="";
			if (CommonFunctions.isValidKeyId(flId)){
				JhAuditCreationBean jhAuditCreationBean= new JhAuditCreationBean();
				JhaTlAuditmst jhaTlAuditmst = new JhaTlAuditmst();
				jhaTlAuditmst.setJhamFlid(flId);				
				JhaTlAuditparameter jhaTlAuditparameter=new JhaTlAuditparameter();		
				CommonFilter commonFilter=new CommonFilter();				
				String count=jhAuditSheetCreationItcService.getSelectCnt(jhaTlAuditmst);
				CommonMessage.debugMsg("count :"+count);
				CommonMessage.debugMsg("flId :"+flId);
				commonFilter.setJhTemplateId(flId);
				String auditLevel= "";			
				auditLevel=jhAuditSheetCreationItcService.getAuditLevelCurrent(commonFilter);			
				CommonMessage.debugMsg("auditLevel :"+auditLevel);
				jhaTlAuditparameter.setJhapAuditlevel(auditLevel);
				
				if(Integer.parseInt(count)>0){				
					/*
					jhaTlAuditmst=jhAuditSheetCreationItcService.select(jhaTlAuditmst);		
					jhaTlAuditparameter.setJhapK
					eyid(jhaTlAuditmst.getJhamAuditteamid());
					jhaTlAuditparameter = jhAuditSheetCreationItcService.recallValues(jhaTlAuditparameter);	
					*/	
				}
				else{
					//String auditLevel= jhAuditSheetCreationItcService.getAuditLevelCurrent(commonFilter);
					//CommonMessage.debugMsg("auditLevel :"+auditLevel);
				}
				/*FormModes modes = FormModes.view;
				if("EDIT".equals(mode))
				{
					modes = FormModes.modify;
				}
				else{
				}*/
				//CommonMessage.debugMsg("jhaTlAuditmst.getJhamAuditteamid()"+jhaTlAuditmst.getJhamAuditteamid());
				httpSession.setAttribute("jhaTlAuditmst", jhaTlAuditmst);
				httpSession.setAttribute("jhaTlAuditparameter", jhaTlAuditparameter);
				request.setAttribute("auditLevel", auditLevel);
				request.setAttribute("minMarks", minMarks);
				request.setAttribute("jhaTlAuditmst", jhaTlAuditmst);
				request.setAttribute("jhaTlAuditparameter", jhaTlAuditparameter);	
				request.setAttribute("jhAuditCreationBean",jhAuditCreationBean);
				JSONObject jhamdata =  UIUtils.fromTpmModel(jhaTlAuditmst);
				JSONObject jhamdataparam =  UIUtils.fromTpmModel(jhaTlAuditparameter);
				JSONObject returndata = new JSONObject();
				returndata.put("jhAuditCreationdata", jhamdata);
				returndata.put("jhaTlAuditparameter", jhamdataparam);
				returndata.put("auditLevel", auditLevel);
				//returndata.put("keyid", keyid);
				out.print(returndata.toString());				
				//String created=entBatchMst.getBachCreatedon();
				//CommonMessage.debugMsg("entBatchMst.getBachCreatedon()="+created);		
			}
		}
		
		else if( action.equals("jhAuditLevelCombo.jhAuditItc"))
		{	
			String flid = request.getParameter("flId");
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			comboFilter = UIUtils.fillComboFilter(request);
			List<ComboBox>  DetectedBy = jhAuditSheetCreationItcService.getJhStepComboList(commonFilter,comboFilter);
			UIUtils.writeComboBox(response, DetectedBy,comboFilter);
		}	
		else if( action.equals("jhAuditTypeCombo.jhAuditItc"))
		{	
			//CommonMessage.debugMsg("inside jhAuditLevelCombo.jhAuditItc");
	 	    PrintWriter outl = response.getWriter();
	 	    outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "comboAuditType"));
	    	CommonMessage.debugMsg("SELECT   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "comboAuditLevel"));
   
		}	
		else if( action.equals("jhapTemplate.jhAuditItc"))
		{				
			try {			
				StringBuffer condSql=new StringBuffer();
				String pillar = request.getParameter("pillar");
				String type = request.getParameter("type");
				String levelId = request.getParameter("levelId");
				CommonFilter commonFilter = new CommonFilter();	
				commonFilter.setType(pillar);	
				commonFilter.setKey(type);
				commonFilter.setKK(levelId);
				/*if (CommonFunctions.isValidKeyId(pillar))
					condSql.append(" AND JHAP_AUDITPILLAR='" + pillar + "' ");
				if (CommonFunctions.isValidKeyId(type))
					condSql.append(" AND JHAP_AUDITTYPE='" + type + "' ");
				if (CommonFunctions.isValidKeyId(levelId))
					condSql.append(" AND JHAP_AUDITLEVEL='" + levelId + "' ");
				CommonMessage.debugMsg("jhapTemplate.jhAuditItc condSql.toString():"+condSql.toString());*/
				comboFilter = UIUtils.fillComboFilter(request);
				//comboFilter.setCondSql(condSql.toString());
				List<ComboBox>  template = jhAuditSheetCreationItcService.getJhTemplateComboList(commonFilter,comboFilter);
				UIUtils.writeComboBox(response, template,comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}		   
		}	
		else if( action.equals("jhStepFill.jhAuditItc")){
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new  JSONObject();
			JSONObject jhStep = new  JSONObject();
			String flId = request.getParameter("flId");
			String templateId = request.getParameter("templateId");
			String jhStepId = jhAuditSheetCreationItcService.getjhStepkeyId(flId);
			
			JhaTlTemplatelevellink jhaTlTemplatelevellink =new JhaTlTemplatelevellink();
			try {
				jhaTlTemplatelevellink =jhAuditSheetCreationItcService.getAuditLevel(flId,templateId, jhStepId);
			} 
			catch (Exception e) {
				jhaTlTemplatelevellink =new JhaTlTemplatelevellink();
				jhaTlTemplatelevellink.setJtllMinimumpoints("0");
				jhaTlTemplatelevellink.setJtllAuditlevelid("-");
			}
			/*if(UIUtils.isValidKeyId(auditLevel)){
				String minMarks =jhAuditSheetCreationItcService.getMinMarks(auditLevel);
				if(UIUtils.isValidKeyId(minMarks))
				  jsonObject.put("minMarks",minMarks);
			}*/			
			if(UIUtils.isValidKeyId(jhStepId))
				 jsonObject.put("jhStepkey",jhStepId);
			if(UIUtils.isValidKeyId(jhaTlTemplatelevellink.getJtllAuditlevelid())){
				 jsonObject.put("auditL",jhaTlTemplatelevellink.getJtllAuditlevelid());
			}else{
				 jsonObject.put("msg", "Audit level already exists for this Equipment");
			}
			if(UIUtils.isValidKeyId("flId"))
				 jsonObject.put("flId",flId);	 
			jhStep.put("jhStepID", jsonObject);
			jhStep.put("jhaTlTemplatelevellink", jhaTlTemplatelevellink);
			out.println(jhStep);
		}
		else if(action.equals("jhParameter_save.jhAuditItc")|| action.equals("dmtParameter_save.jhAuditItc"))
		{
			saveJHParameter(request,response);
		}
		else if(action.equals("jhParameter_delete.jhAuditItc")|| action.equals("dmtParameter_delete.jhAuditItc"))
		{
			deleteJHParameter(request,response);
		}
		else if(action.equals("parameter_remove.jhAuditItc"))
		{
			deleteJHParameterTemplate(request,response);
		}
		else if(action.equals("jhFillControls_select.jhAuditItc"))
		{
			PrintWriter out = response.getWriter();
			String templateId = request.getParameter("templateId");
			String flId = request.getParameter("flId");
			String date = request.getParameter("date");
			String auditType = request.getParameter("auditType");
			String jhStepId = request.getParameter("jhstep");
			JhaTlAuditparameter jhaTlAuditparameter=new JhaTlAuditparameter();			
			jhaTlAuditparameter.setJhapKeyid(templateId);
			jhaTlAuditparameter.setJhapAudittype(auditType);
			
			jhaTlAuditparameter = jhAuditSheetCreationItcService.recallValues(jhaTlAuditparameter);
			String date1 = jhaTlAuditparameter.getJhapRevisiondate();
			jhaTlAuditparameter.setJhapRevisiondate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date1));
			jhaTlAuditparameter.setJhapRevisiondate(UIUtils.getActualDateForm(jhaTlAuditparameter.getJhapRevisiondate()));
			CommonMessage.debugMsg("Remarks"+jhaTlAuditparameter.getJhapRemarks());
			if("{}".equals(jhaTlAuditparameter.getJhapRemarks())) {
				jhaTlAuditparameter.setJhapRemarks("");
			}
			CommonMessage.debugMsg("Remarks"+jhaTlAuditparameter.getJhapRemarks());
			JSONObject jhParameterData = UIUtils.fromTpmModel(jhaTlAuditparameter);
			CommonMessage.debugMsg("Remarks"+jhParameterData.getString("JhapRemarks"));
			JSONObject returndata = new JSONObject();
			returndata.put("jhaTlAuditparameter", jhParameterData);	
			if (CommonFunctions.isValidKeyId(templateId)){
				JhaTlTemplatelevellink jhaTlTemplatelevellink=new JhaTlTemplatelevellink();
				//jhaTlTemplatelevellink.setJtllTemplateid(templateId);
				//jhaTlTemplatelevellink= jhAuditSheetCreationItcService.getAppLvel(jhaTlTemplatelevellink);	
				//CommonMessage.debugMsg("inside jhFillControls_select.jhAuditItc:"+jhaTlTemplatelevellink.getJtllAuditlevelid());
				JSONObject jsonObject = new  JSONObject();
				//String jhStepId = jhAuditSheetCreationItcService.getjhStepkeyId(templateId);
				
				try {
					jhaTlTemplatelevellink=jhAuditSheetCreationItcService.getAuditLevel(templateId,flId, jhStepId);
				} 
				catch (Exception e) {
					jhaTlTemplatelevellink =new JhaTlTemplatelevellink();
					jhaTlTemplatelevellink.setJtllMinimumpoints("0");
					jhaTlTemplatelevellink.setJtllAuditlevelid("-");
				}

					
				CommonMessage.debugMsg("jhStepId======"+jhStepId);
				JhaTlAuditmst jhaTlAuditmst = jhAuditSheetCreationItcService.getExistingjhmKeyid(templateId,flId, date, auditType, jhStepId);
				//CommonMessage.debugMsg("inside jhFillControls_select.jhAuditItc:"+jhaTlTemplatelevellink.getJtllMinimumpoints());
				//CommonMessage.debugMsg("jhamKeyid======"+jhaTlAuditmst);
				CommonMessage.debugMsg("jhStepId:"+jhStepId);
				//String auditLevel =jhAuditSheetCreationItcService.getAuditLevel(templateId,flId);				
				if(UIUtils.isValidKeyId(jhStepId))
					 jsonObject.put("jhStepkey",jhStepId);
				if(UIUtils.isValidKeyId(jhaTlTemplatelevellink.getJtllAuditlevelid())){
					 returndata.put("auditL",jhaTlTemplatelevellink.getJtllAuditlevelid());
				}else{
					 returndata.put("msg", "Audit level already exists for this Functional Location");
				}				
				returndata.put("jhaTlTemplatelevellink", jhaTlTemplatelevellink);		
				returndata.put("jhStepID", jsonObject);		
				returndata.put("jhaTlAuditmst", jhaTlAuditmst);
			}
			out.print(returndata);
		}
		else if (action.equals("JHTeamCombo.jhAuditItc")){
			//CommonMessage.debugMsg("inside combo_JHTeam.jhAuditItc");			
	 	    /*PrintWriter outl = response.getWriter();
	 	    outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "comboTeam"));
	    	CommonMessage.debugMsg("SELECT:"+UIUtils.getPropertyValue("com.akranta.tpm.resources.jhAuditSheetCreationItc", "comboTeam"));	*/
	    	try 
			{			
			CommonFilter commonFilter = new CommonFilter();		
			comboFilter = UIUtils.fillComboFilter(request);
			String templateId = request.getParameter("templateId");
			commonFilter.setKey(templateId);
			List<ComboBox>  DetectedBy = jhAuditSheetCreationItcService.getJhAppLevelComboList(commonFilter,comboFilter);
			UIUtils.writeComboBox(response, DetectedBy,comboFilter);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}	
		
		else if (action.equals("jhAuditReport_input.jhAuditItc")){
			CommonMessage.debugMsg("jhAuditReport_input.jhAuditItc");
			String auditpillar = request.getParameter("TYPE");
			String auditType = request.getParameter("AUDIT");
			httpSession.setAttribute("Auditpillar", auditpillar);
			httpSession.setAttribute("auditType", auditType);	
			request.setAttribute("auditpillar", auditpillar);					
			request.setAttribute("auditType", auditType);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/JHAudit/jhlAuditReport.jsp");
			rd.forward(request, response);		
		}
		else if(action.equals("jhAuditReport_getCol.jhAuditItc"))
		{
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg(" form getCol");
			String  pillar =request.getParameter("TYPE");
			String  autittype = request.getParameter("AUDIT");
			String  flid = request.getParameter("flid");			
        	CommonMessage.debugMsg(" form getCol::::: " + pillar + ",autittype:"+autittype);			
			JSONObject jsonObject = new JSONObject();
			List<String[]>jhAuditList = null;
			CommonFilter commonFilter = new CommonFilter();			
			try {				
				commonFilter.setFlid(flid);	
				commonFilter.setKey(pillar);
				commonFilter.setType(autittype);
				FilterValues.getCommonFilters(request, commonFilter);
				commonFilter.setIsGetCol("Y");
				jhAuditList =  jhAuditSheetCreationItcService.getAuditReportGrid(commonFilter);	
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
				 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);	
			 jqGridTableModel.setSortable(false);
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);
			 
			 String [] colHeaderHead = jhAuditList.get(0);
			 String [] colHeader = jhAuditList.get(1);	
			 
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			 
	   	     jsonObject.set("tableWidth", "100%%");
	     	 jsonObject.set("tableHeight", "90%%");
	   	     httpSession.removeAttribute("jhAuditMulltiReportColModel");
			 httpSession.setAttribute("jhAuditMulltiReportColModel",jsonObject);	
			 //CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);			
		}
		
		else if (action.equals("jhAuditReport_getData.jhAuditItc")) {
			PrintWriter out = response.getWriter();
			String  pillar =request.getParameter("TYPE");
			String  autittype = request.getParameter("AUDIT");
			String  flid = request.getParameter("flid");
			CommonMessage.debugMsg(" form getData:::: " + pillar);
			List<String[]>jhAuditList = null;
			CommonFilter commonFilter = populateCommonFilter(request,"jhAuditMulltiReportCommonFilter",true);
			try {
				commonFilter.setFlid(flid);	
				commonFilter.setKey(pillar);
				commonFilter.setType(autittype);
				FilterValues.getCommonFilters(request, commonFilter);
				commonFilter.setIsGetCol("N");
				jhAuditList =  jhAuditSheetCreationItcService.getAuditReportGrid(commonFilter);
				JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(jhAuditList, request,2, 0,commonFilter.getTotalRecordCnt());
				out.println(studentreportgrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		else if (action.equals("dmtMultiLevel_input.jhAuditItc")){
			CommonMessage.debugMsg("dmtMultiLevel_input.jhAuditItc");
			/*String  paramid = request.getParameter("paramid");	
			String  flid = request.getParameter("flid");
			String  pillar =request.getParameter("TYPE");
			String  autittype = request.getParameter("audittype");*/
			RequestDispatcher rd = request.getRequestDispatcher("/pages/JHAudit/dmtMultiLevelAuditReport.jsp");
			rd.forward(request, response);		
		}	
		else if(action.equals("dmtMultiLevel_getCol.jhAuditItc"))
		{
			CommonMessage.debugMsg("dmtMultiLevel_getCol.jhAuditItc");
			String  paramid = request.getParameter("paramid");	
			String  flid = request.getParameter("flid");
			String  autittype = request.getParameter("audittype");
			String  month = request.getParameter("month");			
			PrintWriter out = response.getWriter();			
			CommonFilter commonFilter = new CommonFilter();	
			List<String[]>jhAuditList = null;
						
			try {
				commonFilter.setFlid(flid);
				commonFilter.setKey(paramid);
				commonFilter.setAuditRpt(autittype);
				commonFilter.setFromMonth(month);
				FilterValues.getCommonFilters(request, commonFilter);
				commonFilter.setIsGetCol("Y");
				jhAuditList  = jhAuditSheetCreationItcService.getDMTMultiLevelAuditGrid(commonFilter);			
				
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(6);	
			 jqGridTableModel.setSortable(false);
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(false);
			 jqGridTableModel.setRowNumbers(true);
			 
			 String [] colHeaderHead = jhAuditList.get(0);
			 String [] colHeader1 = jhAuditList.get(2);	
			 String [] colHeader2 = jhAuditList.get(3);	
			 String [] colHeader3 = jhAuditList.get(4);	
			 String [] colHeader4 = jhAuditList.get(5);	
			 String [] colHeader5 = jhAuditList.get(6);
			 String [] colHeader6 = jhAuditList.get(7);	
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader1);
			 headers.add(colHeader2);
			 headers.add(colHeader3);
			 headers.add(colHeader4);
			 headers.add(colHeader5);
			 headers.add(colHeader6);
			 JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			 
			 jsonObject.set("tableWidth", "100%%");
			 jsonObject.set("tableHeight", "68%%");
			 httpSession.removeAttribute("jhAuditRptColModel");
			 httpSession.setAttribute("jhAuditRptColModel",jsonObject);	
			 //CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);			
		}	
		
		else if(action.equals("dmtMultiLevel_getData.jhAuditItc"))
		{
			CommonMessage.debugMsg("dmtMultiLevel_getData.jhAuditItc");
			PrintWriter out = response.getWriter();			
			String  autittype = request.getParameter("audittype");
			String  paramid = request.getParameter("paramid");	
			String  flid = request.getParameter("flid");	
			String  month = request.getParameter("month");
			CommonMessage.debugMsg(" form getData:::: " + paramid);
			List<String[]>jhAuditList = null;
			CommonFilter commonFilter = populateCommonFilter(request,"jhAuditRptCommonFilter",true);
			try {
				commonFilter.setFlid(flid);
				commonFilter.setKey(paramid);	
				commonFilter.setAuditRpt(autittype);
				commonFilter.setFromMonth(month);
				FilterValues.getCommonFilters(request, commonFilter);
				commonFilter.setIsGetCol("N");
				jhAuditList =  jhAuditSheetCreationItcService.getDMTMultiLevelAuditGrid(commonFilter);
				JSONObject jhAuditListgrid = UIUtils.convertToJqGridTableObject(jhAuditList, request,8, 0);
				out.println(jhAuditListgrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		else if(action.equals("dmtMultiLevel_getExcel.jhAuditItc"))
		{
			exportToExcelMultiLevel(request, response,httpSession);						
		}
		 
		else if (action.equals("jhAuditReport_getExcel.jhAuditItc")) {
			exportToExcelMultiLevelGrid(request, response);
		}

	}
	
	
	private void deletejhAuditElementExlData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	PrintWriter out = response.getWriter();
    	try
		{
	    	if(httpSession !=null && user !=null)
			{
	    		String auditReport = request.getParameter("auditReportdata");
    			JSONArray auditReportJsonArr = JSONArray.fromString(auditReport);
    			List<JhaTlAuditreportdtl> ListJhaTlAuditreportdtl=null;
    			JhaTlAuditreportdtl jhaTlAuditreportdtl=new JhaTlAuditreportdtl();
    			ListJhaTlAuditreportdtl=(List<JhaTlAuditreportdtl>)UIUtils.convertJSONArrToList(jhaTlAuditreportdtl, auditReportJsonArr);

	    		JSONObject successData=new JSONObject();
	    		JSONObject returnData=new JSONObject();
	    		String savemsg; 
	    		
	    		ListJhaTlAuditreportdtl=jhAuditElementReportService.delete(ListJhaTlAuditreportdtl);
				savemsg=" Data Deleted succesfully";
				
	    		successData.put("msg", savemsg);
	    		returnData.put("formClear",false);
	    		returnData.put("reload","Y");
	    		returnData.put("successData", successData);
	    		CommonMessage.debugMsg("returnData:   "+returnData.toString());
	    		out.print(returnData.toString());
			}
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("delete Exceptions "+e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException","Data Not Deleted");
			out.print(err.toString());
			e.printStackTrace();
		}
	}

	private void updatejhAuditElementReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String Keyid=request.getParameter("Keyid");
		String hdnelementId=request.getParameter("hdnelementId");
		
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		PrintWriter out = response.getWriter();
		String auditReport = request.getParameter("auditReportdata");
		JhaTlAuditreportmst jhaTlAuditreportmst=new JhaTlAuditreportmst();
		jhaTlAuditreportmst=(JhaTlAuditreportmst)UIUtils.setBeanProperties((Object)jhaTlAuditreportmst,request);
		jhaTlAuditreportmst.setAurmCreatedby(user.getUsrm_ccno());
		jhaTlAuditreportmst.setAurmElementid(hdnelementId);
		CommonMessage.debugMsg("auditReport  "+auditReport);
		if(UIUtils.isValidKeyId(auditReport)){
			JSONArray auditReportJsonArr = JSONArray.fromString(auditReport);
			List<JhaTlAuditreportdtl> ListJhaTlAuditreportdtl=null;
			JhaTlAuditreportdtl jhaTlAuditreportdtl=new JhaTlAuditreportdtl();
			ListJhaTlAuditreportdtl=(List<JhaTlAuditreportdtl>)UIUtils.convertJSONArrToList(jhaTlAuditreportdtl, auditReportJsonArr);
			
			CommonMessage.debugMsg("ListJhaTlAuditreportdtl.size()  "+ListJhaTlAuditreportdtl.size());
			for(int i=0;i<ListJhaTlAuditreportdtl.size();i++){
				CommonMessage.debugMsg(i+"  i  ListJhaTlAuditreportdtl.get(i)"+ListJhaTlAuditreportdtl.get(i).getAurdOrderno());
				CommonMessage.debugMsg(i+"  i  ListJhaTlAuditreportdtl.get(i)"+ListJhaTlAuditreportdtl.get(i).getAurdAuditelement());
				CommonMessage.debugMsg(i+"  i  ListJhaTlAuditreportdtl.get(i)"+ListJhaTlAuditreportdtl.get(i).getAurdMa());
				CommonMessage.debugMsg(i+"  i  ListJhaTlAuditreportdtl.get(i)"+ListJhaTlAuditreportdtl.get(i).getAurdObservations());
				CommonMessage.debugMsg(i+"  i  ListJhaTlAuditreportdtl.get(i)"+ListJhaTlAuditreportdtl.get(i).getAurdSheetno());
				CommonMessage.debugMsg(i+"  i  ListJhaTlAuditreportdtl.get(i)"+ListJhaTlAuditreportdtl.get(i).getAurdSheetname());
				
			}
			if(ListJhaTlAuditreportdtl.size()>0){
				jhaTlAuditreportmst.setJhaTlAuditreportdtl(ListJhaTlAuditreportdtl);
			}
		}
		
		JSONObject successData=new JSONObject();
		JSONObject returnData=new JSONObject();
		String savemsg; 
		
		if(UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmKeyid())){
			jhaTlAuditreportmst=jhAuditElementReportService.update(jhaTlAuditreportmst);
			savemsg=" Data Updated succesfully";
		}
		else
		{
			savemsg= "Data Not Updated";
		}
		successData.put("msg", savemsg);
		returnData.put("formClear",false);
		returnData.put("successData", successData);
		CommonMessage.debugMsg("returnData:  "+returnData.toString());
		out.print(returnData.toString());
	}

	private void deletejhAuditElementReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try
		{
	    	if(httpSession !=null && user !=null)
			{
	    		JhaTlAuditreportmst jhaTlAuditreportmst=new JhaTlAuditreportmst();
	    		jhaTlAuditreportmst=(JhaTlAuditreportmst)UIUtils.setBeanProperties((Object)jhaTlAuditreportmst,request);
	    		JSONObject successData=new JSONObject();
	    		JSONObject returnData=new JSONObject();
	    		String savemsg; 
	    		
				if(UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmKeyid())){
					jhaTlAuditreportmst=jhAuditElementReportService.delete(jhaTlAuditreportmst);
					savemsg=" Data Deleted succesfully";
				}
				else
				{
					savemsg= "Data Not Deleted  ";
				}
	    		successData.put("msg", savemsg);
	    		returnData.put("formClear",true);
	    		returnData.put("reload","N");
	    		returnData.put("successData", successData);
	    		CommonMessage.debugMsg("returnData:   "+returnData.toString());
	    		out.print(returnData.toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void saveAuditEleRep(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		String file;
		try{
			String elementId=request.getParameter("hdnelementId");
			String fileSave=request.getParameter("fileSave");
			HttpSession httpSession = request.getSession(false);
			AdmTlUsermst userid = UIUtils.getLoginUser(request);
			String excelFileName1= (String) httpSession.getAttribute(AdmUploadExcelServlet_filename);
			String excelFileName=(realPath+""+excelFileName1);
			CommonMessage.debugMsg("fileSave :  "+fileSave+"excelFileName :  "+excelFileName);
			JhaTlAuditreportmst jhaTlAuditreportmst= new JhaTlAuditreportmst();
			jhaTlAuditreportmst =(JhaTlAuditreportmst) UIUtils.setBeanProperties((Object)jhaTlAuditreportmst,request);
			boolean frmClr=true;
			if(UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmKeyid()))
				frmClr=false;
			jhaTlAuditreportmst.setAurmElementid(elementId);
			if("Y".equals(fileSave))
				jhaTlAuditreportmst.setAurmUploadedfile(excelFileName1.toUpperCase());
			else
				jhaTlAuditreportmst.setAurmUploadedfile("");
			String savemsg;	
			
			jhaTlAuditreportmst.setAurmCreatedby(userid.getUsrm_ccno());
			file = jhAuditElementReportService.populateTempTable(excelFileName,jhaTlAuditreportmst);
			if(file.equals("No"))
				savemsg=" Data Not Supported";
			else
				savemsg=" Data Uploaded succesfully";
			httpSession.removeAttribute(AdmUploadExcelServlet_filename);
			JSONObject successData=new JSONObject();
			JSONObject returnData=new JSONObject();
	    	successData.put("msg", savemsg);
    		returnData.put("formClear",frmClr);
	    	returnData.put("successData", successData);
			out.print(returnData.toString());
		}catch(ValidationExceptions e){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"AuditreporValidations");
			e.printStackTrace();
			out.print(errMessage.toString());
		}catch(Exception e){
			CommonMessage.debugMsg("Inside Exceptions "+e.getMessage());
			JSONObject err = new JSONObject();
			String msg="Data Not Uploaded";
			if(e.toString().contains("UK_UPLOADEDFILE"))
				msg="Data Already Exists";
			err.put("tpmException",msg);
			out.print(err.toString());
			e.printStackTrace();
		}
	}

	private void deleteJHParameter(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{

		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		if( httpSession != null && user != null)
		{	
			String templateId = request.getParameter("templateId");
			
			JhaTlAuditparameter existJhaTlAuditmst = (JhaTlAuditparameter)httpSession.getAttribute("jhAuditSheetmstCreationItcServlet");
			JhaTlAuditparameter newJhaTlAuditparameter = new JhaTlAuditparameter();
			JhaTlAuditparameter existJhaTlAuditparameter = (JhaTlAuditparameter)httpSession.getAttribute("jhaTlAuditparameterServlet");		
			newJhaTlAuditparameter.setJhapCreatedby(user.getUsrm_ccno());
			newJhaTlAuditparameter.setJhapKeyid(templateId);
			newJhaTlAuditparameter =(JhaTlAuditparameter)UIUtils.setBeanProperties((Object)newJhaTlAuditparameter,request);
			try{							
				existJhaTlAuditmst = jhAuditSheetCreationItcService.delete(newJhaTlAuditparameter);	
				//httpSession.setAttribute(existJhaTlAuditmst.getJhamKeyid(), existJhaTlAuditmst);
				httpSession.setAttribute("JhaTlAuditmst", existJhaTlAuditmst);					
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));			
				//successData.put("keyId", existJhaTlAuditmst.getJhamKeyid());
				JSONObject returnData = new JSONObject();	
				successData.put("pillar", newJhaTlAuditparameter.getJhapAuditpillar());	
				successData.put("audittype", newJhaTlAuditparameter.getJhapAudittype());	
				returnData.put("successData", successData);		
				out.print(returnData.toString());
			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "JHParameterCreationItc");
				out.print(errMessage.toString());
			} catch (BusinessApplicationExceptions e) {
				CommonMessage.debugMsg("Error Servler e -" + e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "JHParameterCreationItc");
				out.print(errMessage.toString());
				CommonMessage.debugMsg(" e " + errMessage);
			} catch (Exception e) {
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
		}   	
	
	}

	private void deleteJHParameterTemplate(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{

		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		if( httpSession != null && user != null)
		{				
			String parameterId = request.getParameter("parameterId");			
			try{							
				jhAuditSheetCreationItcService.deleteParameter(parameterId) ;				
				JSONObject successData = new JSONObject();				
				successData.put("msg", " Record is deleted ");
				//successData.put("rowId", rowId);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				out.print(returnData.toString());		
			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "JHParameterCreationItc");
				out.print(errMessage.toString());
			} catch (BusinessApplicationExceptions e) {
				CommonMessage.debugMsg("Error Servler e -" + e.toString());
				JSONObject successData = new JSONObject();	
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "JHParameterCreationItc");
				CommonMessage.debugMsg(" e " + errMessage);
				successData.put("msg", "");
				//successData.put("rowId", rowId);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("error", errMessage.toString());
				out.print(returnData.toString());	
			} catch (Exception e) {
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
		}   	
	
	}
	
	private void saveJHParameter(HttpServletRequest request,HttpServletResponse response) throws IOException {

	HttpSession httpSession = request.getSession(false);
	ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
	//String equipmentGrid = request.getParameter("equipmentGrid");
	//String jhStepGrid = request.getParameter("jhStepGrid");
	String templateId = request.getParameter("templateId");
	String jhAuditLevelGrid = request.getParameter("jhAuditLevel");
	String auditTemplate = request.getParameter("auditTemplate");
	String errText = request.getParameter("errText");
	String fileManager=request.getParameter("filemanager");
	//String gradeTemplate = request.getParameter("gradeTemplate");
	
	CommonMessage.debugMsg("TemplateId = " + templateId);
	CommonMessage.debugMsg("jhAuditLevelGrid = " + jhAuditLevelGrid);
	CommonMessage.debugMsg("auditTemplate = " + auditTemplate);
	CommonMessage.debugMsg("fileManager = " + fileManager);
	CommonMessage.debugMsg("ErrText = " + errText);

	
	JhaTlAuditparameter newJhaTlAuditparameter = new JhaTlAuditparameter();
	JhaTlAuditparameter existJhaTlAuditparameter = new JhaTlAuditparameter();
	
	JhaTlTemplatemchlink newJhaTlTemplatemchlink = new JhaTlTemplatemchlink();
	JhaTlTemplatemchlink existJhaTlTemplatemchlink = (JhaTlTemplatemchlink)httpSession.getAttribute("JhaTlTemplatemchlink");
	
	newJhaTlAuditparameter.setJhapCreatedby(user.getUsrm_ccno());
	newJhaTlTemplatemchlink.setJtmlTemplateid(templateId);
	newJhaTlAuditparameter.setJhapKeyid(templateId);
	
	newJhaTlAuditparameter = (JhaTlAuditparameter) UIUtils.setBeanProperties((Object) newJhaTlAuditparameter, request);
	if(UIUtils.isValidKeyId(jhAuditLevelGrid))
	{
		 JSONArray jsonJHLevel = JSONArray.fromString(jhAuditLevelGrid);
		 CommonMessage.debugMsg("enter servlet 1");
		 JhaTlTemplatelevellink jhaTlTemplatelevellink = new JhaTlTemplatelevellink();
		 List<JhaTlTemplatelevellink> jhLevelGridVal =(List<JhaTlTemplatelevellink>)UIUtils.convertJSONArrToList(jhaTlTemplatelevellink,jsonJHLevel);
		 if(jhLevelGridVal != null)
			 newJhaTlAuditparameter.setJhLevelGrid(jhLevelGridVal);
	}
	
	if(UIUtils.isValidKeyId(auditTemplate))
	{
		 JSONArray jsonAuditTemplate = JSONArray.fromString(auditTemplate);
		 JhaTlAudittemplate jhaTlAudittemplate = new JhaTlAudittemplate();
		 List<JhaTlAudittemplate> jhAuditTemplateVal =(List<JhaTlAudittemplate>)UIUtils.convertJSONArrToList(jhaTlAudittemplate,jsonAuditTemplate);
		 if(jhAuditTemplateVal != null)
			 newJhaTlAuditparameter.setJhAuditTemplate(jhAuditTemplateVal);
	}
	try {
		CommonMessage.debugMsg("enter servlet 2");
		CommonMessage.debugMsg("after the ben properties");
		existJhaTlAuditparameter = jhAuditSheetCreationItcService.create(newJhaTlAuditparameter,existJhaTlAuditparameter);			
		JSONObject successData = new JSONObject();				
		String msgPropertyIdnt;		
		CommonMessage.debugMsg("enter servlet 3");
		msgPropertyIdnt = "success-save";
		
		successData.put("keyId", newJhaTlAuditparameter.getJhapKeyid());	
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
		successData.put("pillar", newJhaTlAuditparameter.getJhapAuditpillar());	
		successData.put("audittype", newJhaTlAuditparameter.getJhapAudittype());	
		JSONObject returnData = new JSONObject();
		returnData.put("formClear", false);
		if(UIUtils.isValidKeyId(fileManager)){
			//CommonMessage.debugMsg(" fileManagerv Inside Flid servlet if");
			successData.put("filemanager", true);
		}else
		{
			//CommonMessage.debugMsg(" fileManager Inside Flid servlet Else");
			successData.put("filemanager", false);	
		}
		returnData.put("successData", successData);	
		out.print(returnData.toString());
		
		} catch (ValidationExceptions e) {
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "JHParameterCreationItc");
			out.print(errMessage.toString());
		} catch (BusinessApplicationExceptions e) {
			CommonMessage.debugMsg("Error Servler e -" + e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "JHParameterCreationItc");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage);
		} catch (Exception e) {
			CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
	private void saveAudit(HttpServletRequest request, HttpServletResponse response,JhAuditCreationBean jhAuditCreationBean) throws IOException{
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	JSONObject returnData = new JSONObject();
    	JSONObject successData = new JSONObject(); 
    	
    	if( httpSession != null && user != null)
    	{	
    		JhaTlAuditmst newJhaTlAuditmst = new JhaTlAuditmst();
    		JhaTlAuditdtl newJhaTlAuditdtl = new JhaTlAuditdtl();
    		newJhaTlAuditmst.setJhamCreatedby(user.getUsrm_ccno());
    		newJhaTlAuditdtl.setJhadCreatedby(user.getUsrm_ccno());
    		newJhaTlAuditmst =(JhaTlAuditmst)UIUtils.setBeanProperties((Object)newJhaTlAuditmst,request);
    		newJhaTlAuditdtl =(JhaTlAuditdtl)UIUtils.setBeanProperties((Object)newJhaTlAuditdtl,request);
		 	jhAuditCreationBean =(JhAuditCreationBean) UIUtils.setBeanProperties((Object)jhAuditCreationBean,request);
		 	JhaTlAuditmst existJhaTlAuditmst = (JhaTlAuditmst)httpSession.getAttribute("jhAuditSheetmstCreationItcServlet"); 
			JhaTlAuditdtl existJhaTlAuditdtl = (JhaTlAuditdtl)httpSession.getAttribute("jhAuditSheetdtlCreationItcServlet"); 
			String pointsScored = request.getParameter("audit");
			String actPlan = request.getParameter("actplan");
			String fileManager = request.getParameter("filemanager");
			String rowId = request.getParameter("rowid");
			//CommonMessage.debugMsg("pointsScored"+pointsScored);
			CommonMessage.debugMsg("actPlan"+actPlan);
			CommonMessage.debugMsg("rowId"+rowId);
			List<JhaTlAuditdtl> auditDtlList = null;
	    	JSONArray auditDtlListJson = null;
	    	if(UIUtils.isValidKeyId(pointsScored))
	    	{
	    		auditDtlListJson = JSONArray.fromString(pointsScored);
	    		CommonMessage.debugMsg("auditDtlListJson"+auditDtlListJson);
	    		auditDtlList = (List<JhaTlAuditdtl>)UIUtils.convertJSONArrToList(newJhaTlAuditdtl,auditDtlListJson);
	    		if(auditDtlList!= null){
	    			CommonMessage.debugMsg("AuditlinkList---"+auditDtlList);
	    			CommonMessage.debugMsg("AuditlinkList.size---"+auditDtlList.size());
	    			newJhaTlAuditdtl.setmethodjhaTlAuditdtl(auditDtlList);
	    			newJhaTlAuditmst.setAuditDtl(auditDtlList);
	    		}
		    }
	    	
			try{
			    String count = jhAuditSheetCreationItcService.getjhauditLevelCountforFlid(newJhaTlAuditmst.getJhamFlid());
			    //if(!count.equals("0")){
				boolean insert = true;
				if( UIUtils.isValidKeyId(newJhaTlAuditmst.getJhamKeyid()))
				{
					existJhaTlAuditmst = jhAuditSheetCreationItcService.update(newJhaTlAuditmst,existJhaTlAuditmst,jhAuditCreationBean);
					insert = false;						
				}
				else{
					existJhaTlAuditmst = jhAuditSheetCreationItcService.create(newJhaTlAuditmst,existJhaTlAuditmst,jhAuditCreationBean);
				}
				
				new JSONObject();			
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("JhamKeyid",existJhaTlAuditmst.getJhamKeyid());
				String msgPropertyIdnt;				
			 
				if( insert){
					msgPropertyIdnt = "success-save";
					
					
				}else
					msgPropertyIdnt = "success-update";
				
			    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				successData.put("mode",jhAuditCreationBean.getFormActionMode() );
				successData.put("keyId", existJhaTlAuditmst.getJhamKeyid());
				//if (actPlan.equals("true"))				
					//successData.put("dtlkeyid", newJhaTlAuditmst.getAuditDtl().get(Integer.parseInt(rowId)-1).getJhadKeyid());	
				
				if(UIUtils.isValidKeyId(actPlan)){
					//CommonMessage.debugMsg(" Inside Flid servlet If");
					successData.put("dtlkeyid", newJhaTlAuditmst.getAuditDtl().get(Integer.parseInt(rowId)-1).getJhadKeyid());	
					successData.put("rowid", rowId);
					//CommonMessage.debugMsg(" Inside Flid servlet "+newOplTlMst.getOplmFlid());
					successData.put("actplan", true);
				}else
				{
					//CommonMessage.debugMsg(" Inside Flid servlet Else");
					successData.put("actplan", false);	
				}
				if(UIUtils.isValidKeyId(fileManager)){
					//CommonMessage.debugMsg(" fileManagerv Inside Flid servlet if");
					successData.put("filemanager", true);
				}else
				{
					//CommonMessage.debugMsg(" fileManager Inside Flid servlet Else");
					successData.put("filemanager", false);	
				}
				successData.put("pillar", newJhaTlAuditmst.getJhamAuditpillar());	
				successData.put("audittype", newJhaTlAuditmst.getJhamAudittype());	
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				httpSession.setAttribute( "jhAuditSheetmstCreationItcServlet", existJhaTlAuditmst);
			    // }else{	
			    // 	successData.put("countMsg","AuditLevel already exists");
			    // 	returnData.put("auditMsg",successData);
			    // }					
				out.print(returnData.toString());
				out.close();				
			}			
			catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"JhAuditCreationExceptionItc");
				errMessage.put("fromMode",jhAuditCreationBean.getFormActionMode());
				out.print(errMessage.toString());					
			}catch(Exception e){
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}			
	    }	
	}
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);			
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew )
		{
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAudit(request, commonFilter);
			
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}		
		return commonFilter;
	}
	
	private void exportToExcel(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String jhamAuditpillar = request.getParameter("TYPE");
		String jhamAudittype = request.getParameter("AUDIT");		 
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"jhAuditSheetCreation",false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		commonFilter.setKey(jhamAuditpillar);
		commonFilter.setType(jhamAudittype);
		String tableModel = (String) httpSession.getAttribute("colModeljhAuditSheet");
		JSONObject tblJSONObj = JSONObject.fromString(tableModel);		
		tblJSONObj.put("title", jhamAudittype+" Audit Sheet");
		String format = ExcelUtils.getFormat(request);		
		Workbook wb = jhAuditSheetCreationItcService.jhAuditSheetExportExcel(commonFilter,tblJSONObj,format);
		commonFilter.setFromRow(tmpFromRow);
		
		commonFilter.setFromRow(tmpFromRow);		
		ExcelUtils.writeToResponse(response, wb, jhamAudittype+"AuditSheet", format);
	}
	
	private void exportToExcelMultiLevelGrid(HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
		String  pillar =request.getParameter("TYPE");
		String  autittype = request.getParameter("AUDIT");
		String  flid = request.getParameter("flid");	 
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"jhAuditMulltiReportCommonFilter",false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		commonFilter.setFlid(flid);	
		commonFilter.setKey(pillar);
		commonFilter.setType(autittype);
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("jhAuditMulltiReportColModel");	
		tableModel.put("title", autittype+" Audit Sheet Report");
		String format = ExcelUtils.getFormat(request);		
		Workbook wb = jhAuditSheetCreationItcService.getjhAuditMultiGridExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);		
		ExcelUtils.writeToResponse(response, wb, autittype+"AuditSheetReport", format);
	}
	
	private void exportToExcelMultiLevel(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws Exception
	{		
		String  paramid = request.getParameter("paramid");	
		String  flid = request.getParameter("flid");			
		String  autittype = request.getParameter("audittype"); 
		String  month = request.getParameter("month");
		String  level = request.getParameter("level");
		httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"jhAuditRptCommonFilter",false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		commonFilter.setFlid(flid);
		commonFilter.setKey(paramid);
		commonFilter.setFromMonth(month);
		commonFilter.setAuditRpt(autittype);
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("jhAuditRptColModel");	
		tableModel.put("title", autittype+" Audit Sheet Report - "+ level);
		String format = ExcelUtils.getFormat(request);		
		Workbook wb =jhAuditSheetCreationItcService.getjhAuditMultiExportExcel(commonFilter,tableModel,format);
		wb.createCellStyle().setWrapText(true);
		commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, autittype+"AuditSheetReport", format);
	}
	
	private void deleteAudit(HttpServletRequest request,HttpServletResponse response, JhAuditCreationBean jhAuditCreationBean) throws IOException
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		JhaTlAuditmst existJhaTlAuditmst = (JhaTlAuditmst)httpSession.getAttribute("jhAuditSheetmstCreationItcServlet"); 
    		
    		JhaTlAuditmst newJhaTlAuditmst = new JhaTlAuditmst();
    		JhaTlAuditdtl newJhaTlAuditdtl = new JhaTlAuditdtl();
    		
    		newJhaTlAuditmst.setJhamCreatedby(user.getUsrm_ccno());
    		newJhaTlAuditdtl.setJhadCreatedby(user.getUsrm_ccno());
    		
    		newJhaTlAuditmst =(JhaTlAuditmst)UIUtils.setBeanProperties((Object)newJhaTlAuditmst,request);
    		newJhaTlAuditdtl =(JhaTlAuditdtl)UIUtils.setBeanProperties((Object)newJhaTlAuditdtl,request);	    		
    		newJhaTlAuditmst.getAuditDtl().add(newJhaTlAuditdtl);
    		
    		jhAuditCreationBean =(JhAuditCreationBean) UIUtils.setBeanProperties((Object)jhAuditCreationBean,request);
    		
		
			try{
								
				existJhaTlAuditmst = jhAuditSheetCreationItcService.delete(newJhaTlAuditmst);						
				
				httpSession.setAttribute(existJhaTlAuditmst.getJhamKeyid(), existJhaTlAuditmst);
				httpSession.setAttribute("BdmTlMst", existJhaTlAuditmst);
				String formBeanIdentifier = "JhAuditCreationBean"+jhAuditCreationBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,jhAuditCreationBean);
						
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				successData.put("mode",jhAuditCreationBean.getFormMode() );
				successData.put("keyId", existJhaTlAuditmst.getJhamKeyid());
				JSONObject returnData = new JSONObject();
				
				returnData.put("successData", successData);				
				
				out.print(returnData.toString());
				
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"JhAuditCreationExceptionItc");
				errMessage.put("fromMode",jhAuditCreationBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
    	}    	
	}
	
	private JSONObject dmtMultiLevelGetCol(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,CommonFilter commonFilter) throws IOException{
   		
		List<String []> progCalenderGrid  = null;	
		JSONObject progCalenderGriddata = null;
		try {
			progCalenderGrid  = jhAuditSheetCreationItcService.getDMTMultiLevelAuditGrid(commonFilter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		progCalenderGriddata = UIUtils.convertToJqGridTableObject(progCalenderGrid,request,0,0);
		httpSession.setAttribute("progCalenderGriddata", progCalenderGriddata);
		JSONObject jsonObject = getTableModel(progCalenderGrid);
		if(progCalenderGrid.size()>0){		
			//jsonObject.set("tableHeight", "50%%");
			jsonObject.set("tableWidth", "100%%");			
		}
		return jsonObject;
   	}
	
	private JSONObject getTableModel(List<String[]> headers)
	{
		
		CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);
		String [] colHeader1 = headers.get(1);
		String [] colHeader2 = headers.get(2);
		String [] colHeader3 = headers.get(3);
		String [] colHeader4 = headers.get(4);
		//CommonMessage.debugMsg("headers.get(0):" + headers.get(0));
		//CommonMessage.debugMsg("headers Completed");
		String[] emptyrow = new String[colHeader.length];
		
		for(int i =0; i < colHeader.length; i++)
		{
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.getRowHeaders().add(colHeader3);	
		jqGridTableModel.getRowHeaders().add(colHeader4);	
		//jqGridTableModel.setTableButton(true);		
		jqGridTableModel.setRowNumbers(true);
	
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(1000);
		
		for(int i =0; i < colHeader.length; i++)
		{			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setWidth( 299);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if( i<=1){
				jqGridColModel.setHidden(true);				
			}
			else if( i==2 ){
					jqGridColModel.setWidth( 199);				
					jqGridColModel.setAlign("left");
			}
			else if( i==3){
				jqGridColModel.setWidth( 10);				
				jqGridColModel.setAlign("left");
			}
			else if( i==4 ){
				jqGridColModel.setWidth( 299);				
				jqGridColModel.setAlign("left");
			}			
			else if(i>4) {
				jqGridColModel.setWidth(63);
				jqGridColModel.setAlign("right");
			}
			if (i==1){
				jqGridColModel.setKey(true);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 tableModel.set("tableHeight", "45%%");
		 tableModel.set("tableWidth", "90%%");
		 return tableModel;
	}
	
}
				
				