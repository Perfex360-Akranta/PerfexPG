package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.EntTlEmployeeRatting;
import com.akranta.tpm.model.EntTlTragcalquad;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.NewETReportService;
import com.akranta.tpm.service.impl.NewETReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NewETReportServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final String commonFilterScore = "SkillIndexScorecommonFilter";
	private static final String commonFilterIden = "impVsCompcommonFilter";
	NewETReportService newETReportService; 
	
	public NewETReportServlet() {
        super();
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
	private void process(HttpServletRequest request,HttpServletResponse response)  throws Exception{
		
		HttpSession httpSession=request.getSession();
		String action=UIUtils.getActionPart(request);
		try{
			newETReportService=(NewETReportServiceImpl)UIUtils.getServiceObject(request,"NewETReportServiceImpl");
		
			CommonMessage.debugMsg("  newETReportService jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
			//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			newETReportService.NewETReportServiceImplJwt(
						   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
						);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	 if(action.equals("NewETplanVScomp_input.newentRpt")||action.equals("NewTrainingadherencereport_input.newentRpt"))
		{
		 
		 CommonMessage.debugMsg("chk the servlet");
		 String type=request.getParameter("type");
			request.setAttribute("type",type);
			request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENTNEW/Reports/NewETplanVScompRpt.jsp");
			///perfexitc_dev/WebContent/pages/ENTNEW/Reports/NewETplanVScompRpt.jsp
			rd.forward(request, response); 
			
		}
	 else if(action.equals("fqmr_input.newentRpt")){
		 
		 String loginflid=CommonFunctions.getLoginFlid(request);
		 CommonMessage.debugMsg("loginflid:::"+loginflid);
		 request.setAttribute("flid", loginflid);
	
		 RequestDispatcher rd=request.getRequestDispatcher("/pages/FourQuadMatrixrpt.jsp");
		 rd.forward(request,response);
		 
		 
		 
	 }
		else if(action.equals("NewAverageSkillIndexRpt_input.newentRpt"))
		{
			CommonMessage.debugMsg("AverageSkillIndexScore_input.skillIndex");
			request.setAttribute("GraphMsg", "Click on Row and click Graph button to View Graph");//UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/avgSkillIndexScore.jsp");
			rd.forward(request, response); 
		}
	 else if( action.equals("NewETplanVScompchart.newentRpt")){
			processChart(request,response);
		}
	 
	 else if( action.equals("NewETplanVScomplinegrapgh.newentRpt")){
			processChart(request,response);
		} 
	 else if(action.equals("NewQuadrant3Assessment_input.newentRpt")||(action.equals("NewQuadrant4Assessment_input.newentRpt"))){
			//RequestDispatcher rd = request.getRequestDispatcher("/pages/newENT/assesmentLevel.jsp");

		   String masterid=request.getParameter("masterid");
			CommonMessage.debugMsg("ms key id is"+masterid);
			request.setAttribute("Masterkeyid",masterid);
		    RequestDispatcher rd = request.getRequestDispatcher("/pages/ENTNEW/Reports/NewassmLevelMainGrid.jsp");

		  

			rd.forward(request, response);			
		}
	 else if(action.equals("NewQuadrant3Assessmentfrm_save.newentRpt")||action.equals("NewQuadrant4Assessmentfrm_save.newentRpt")){
			saveAssementlevel(request,response);
		}
		else if(action.equals("NewNominatnReport_input.newentRpt"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("pages/ENTNEW/Reports/NewNomntnrpt.jsp"); 
			rd.forward(request, response);
			///perfexitc_dev/WebContent/pages/ENTNEW/Reports/NewNomntnrpt.jsp
		}
	 else if(action.equals("NewETplanVScomp_getCol.newentRpt")||action.equals("NewTrainingadherencereport_getCol.newentRpt"))
		{	
		 CommonMessage.debugMsg("getcol of servlet");
			PrintWriter out = response.getWriter();
			String firstClick =request.getParameter("firstClick");
			CommonFilter commonFilter = populateCommonFilter(request,"ETplanVScompCommonFilter",true);	
			//if(commonFilter==null)
				//commonFilter = new CommonFilter();
			//CommonFilter commonFilter = populateCommonFilter(request,"ETplanVScompCommonFilter",true);
			FilterValues.getAbnRelatedFilters(request, commonFilter);
			
			String type=request.getParameter("type");
			String custmrtype=request.getParameter("custmrtype");
			
			CommonMessage.debugMsg(" Inside custmrtype :: getCol "+custmrtype);
			
			CommonMessage.debugMsg(" Inside getCol TRADHRPT ::CUSCOMPNTRPT "+type);
			
			if(UIUtils.isValidKeyId(type)){
			  if("TRADHRPT".equals(type)||"TRADHMEMBERS".equals(type))
				commonFilter.setType(type);
			}else if(UIUtils.isValidKeyId(custmrtype)){
			    if("ComplaintGallery".equals(custmrtype)||"CRM".equals(custmrtype))
				    //commonFilter.setAbnViewType(custmrtype);
			        commonFilter.setMachineId(custmrtype);
			 }
			//CommonMessage.debugMsg(commonFilter.getRowTotal() + "  row totsl 78  ");
			if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 					
				commonFilter =(CommonFilter) httpSession.getAttribute("ETplanVScompRptCommonFilter");
			}	
			//CommonMessage.debugMsg(commonFilter.getRowTotal() + "  row totsl");
			commonFilter.setRowTotal('N');
			FilterValues.getCommonFilters(request,commonFilter);
			
			httpSession.removeAttribute("ETplanVScompRptCommonFilter");
			httpSession.setAttribute("ETplanVScompRptCommonFilter",commonFilter);
	      	response.setContentType("text/html"); 
	      	
			List<String[]> abnormality  =  newETReportService.getPlanVsCompCal(commonFilter,custmrtype);	
			
			CommonMessage.debugMsg("list of string returned from function  " + abnormality);
			
			JSONObject abnData = UIUtils.convertToJqGridTableObject(abnormality,request,2,0,commonFilter.getTotalRecordCnt()); 
			httpSession.setAttribute("ETplanVScompServletdata", abnData);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false)	;
			jqGridTableModel.setTableButton(true);
			
			CommonMessage.debugMsg("bdfr" + UIUtils.isValidKeyId(type));
			if(UIUtils.isValidKeyId(type)){
				CommonMessage.debugMsg(" gallery" + type);
				if("ComplaintGallery".equals(custmrtype)||"CRM".equals(custmrtype)){
					CommonMessage.debugMsg("inside gallery");
					gridColModel.setHeaderNum(1);
				}
				else{
					CommonMessage.debugMsg("else gallery");
					gridColModel.setHeaderNum(1);
					gridColModel.setHeaderNum(2);
				}
			}else{
				gridColModel.setHeaderNum(1);
				gridColModel.setHeaderNum(2);
			}
			
			
			
			String [] colHeader = abnormality.get(1);
			String [] colHeader1 = abnormality.get(2);
			String [] colHeaderCond = abnormality.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			headers.add(colHeader1);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			
			//jsonObject.put("multiSelect", true);
			jsonObject.put("tableWidth", "107%%");
			jsonObject.put("tableHeight", "66.5%%%%");
			httpSession.removeAttribute("ETplanVScompColModel");
			httpSession.setAttribute("ETplanVScompModel", jsonObject);
			out.println(jsonObject);
			
			
		}
		else if (action.equals("fqmr_getCol.newentRpt")) {
			PrintWriter out = response.getWriter();
		String Cellid=request.getParameter("Cellid");
		CommonMessage.debugMsg("CellId 1 "+Cellid);
			CommonFilter commonFilter = populateCommonFilter(request,"FourQuadrantMatrixCommonFilter",true);
			commonFilter.setCellch(Cellid);
			commonFilter.setIsGetCol("Y");
			List<String[]> fourquadrantgrid = newETReportService.getfourquadrant(commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);		
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);	
			String [] colHeader = fourquadrantgrid.get(1);	
			String [] colHeaderCond = fourquadrantgrid.get(0);
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			gridColModel.setFormatter("fourquadgridimage");
		    gridColModel.setFormattorFromCol("1");
		    gridColModel.setFormattorToCol(""+(colHeader.length));
			JSONObject colModel =new JSONObject();
			colModel= UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			colModel.put("tableWidth", "110%%");
			colModel.put("tableHeight", "80%%");
			httpSession.setAttribute("FourQuadrantMatrixColModel", colModel);
			httpSession.removeAttribute("FourQuadrantMatrixCommonFilter");
			httpSession.setAttribute("FourQuadrantMatrixCommonFilter", commonFilter);	
			out.println(colModel);
		} 
		
		else if (action.equals("fqmr_getData.newentRpt")) {
			try {
				UIUtils.displayRequestParamsValue(request);	
				String Cellid=request.getParameter("Cellid");
			CommonMessage.debugMsg("CellId 1 "+Cellid);
				CommonFilter commonFilter = populateCommonFilter(request,"FourQuadrantMatrixCommonFilter",false);
			commonFilter.setCellch(Cellid);
			commonFilter.setIsGetCol("N");
				List<String[]> fourquadrantgrid   = newETReportService.getfourquadrant(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(fourquadrantgrid , request, 2, 0,fourquadrantgrid.size());
				out.println(machinegrid);
				httpSession.removeAttribute("FourQuadrantMatrixCommonFilter");
				httpSession.setAttribute("FourQuadrantMatrixCommonFilter", commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("fqmr_getExcel.newentRpt"))
		{		
			CommonFilter commonFilter = populateCommonFilter(request,"FourQuadrantMatrixCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
		    //JSONObject colmodel = UIUtils.getXlColModel( request, response);
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("FourQuadrantMatrixColModel");
			colmodel.put("title","Ent Four Quadrant");
			colmodel.put("rowHeight", 29.25);
            String format = ExcelUtils.getFormat(request);		
            String imagepath=UIUtils.getAppPath(request);
			Workbook wb = newETReportService.FourExcelReport(commonFilter,colmodel,format,imagepath);
			
			//25-Nov-2025 Write Excel to HTTP Response
			response.setContentType(
			    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			);
			response.setHeader(
			    "Content-Disposition",
			    "attachment; filename=\"EntFourQuadrant.xlsx\""
			);

			ServletOutputStream out = response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
			wb.close();

			commonFilter.setFromRow(tmpFromRow);
			}
  else if(action.equals("fqmreport.newentRpt")){
	CommonMessage.debugMsg("am entering in to the new Fqmreport Servlet");
    PrintWriter out=response.getWriter();
    String flid=request.getParameter("flid");
    String frmdate=request.getParameter("frmdate");
    String todate=request.getParameter("todate");
    CommonFilter commonFilter=populateCommonFilter(request, "FourQuadrantMatrixCommonFilter", false);
    commonFilter.setFlid(flid);
    commonFilter.setFromDate(frmdate);
    commonFilter.setToDate(todate);
    String fnlncount=newETReportService.getfunctionaldata(commonFilter);
    JSONObject jsonObject=new JSONObject();
    jsonObject.put("Qdrantcnt", fnlncount);
    out.println(jsonObject);
    
    
		}
	 
	 
	 
	 
	
	 
	 else if (action.equals("NewAverageSkillIndexScore_getCol.newentRpt")) {
		 
			httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			
			String type = request.getParameter("type");
			
			CommonMessage.debugMsg(" Inside getCol "+type);
			
			CommonFilter commonFilter = new CommonFilter();
			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				 commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterScore);
			}
			
			if (commonFilter == null)
				commonFilter = new CommonFilter();
			commonFilter=populateCommonFilter(request,commonFilterScore,true);
			
			if(UIUtils.isValidKeyId(type))
				commonFilter.setAbnViewType(type);
				
			
			httpSession.removeAttribute(commonFilterScore);
			httpSession.setAttribute(commonFilterScore, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getSafty(request, commonFilter);
			
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
		
			List<String[]> impVscomList = newETReportService.getavgSkillScoreGraph(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			JSONObject jsonObject = getTableModelGraph(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "73%%");
			jsonObject.set("tableWidth", "107%%");
			httpSession.removeAttribute("avgSkillScoreData");
			httpSession.setAttribute("avgSkillScoreData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		} 
		
		else if (action.equals("NewAverageSkillIndexScore_getData.newentRpt")) {
			try {
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterScore,false);
				response.setContentType("text/html");
				httpSession.getAttribute(commonFilterScore);
				
				String type = request.getParameter("type");
				
				CommonMessage.debugMsg(" Inside getData "+type);
				
				if(UIUtils.isValidKeyId(type))
					commonFilter.setAbnViewType(type);
				
				CommonMessage.debugMsg("commonFilter.getFlid()  "+commonFilter.getFlid());
				List<String[]> impVscomList = newETReportService.getavgSkillScoreGraph(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 3, 0);
				httpSession.removeAttribute(commonFilterScore);
				httpSession.setAttribute(commonFilterScore, commonFilter);
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		
		else if( action.equals("NewAverageSkillIndexScore_getExcel.newentRpt"))
		{			
		 httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterScore,false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("avgSkillScoreData");
		
		//String type = request.getParameter("type");
		CommonMessage.debugMsg("commonFilter.getDrillCaption()"+commonFilter.getDrillCaption());
		
		String reviewDate = request.getParameter("reviewDate");
		tableModel.put("title", " Average SkillIndex Score "  + reviewDate );
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = newETReportService.avgSkillGraphExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, "SkillIndexScore", format);
		}
	 
		else if (action.equals("NewTrnhrsPerPersonPerMonth_input.newentRpt")) {
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String comp = request.getParameter("cmbCompid");
			String type =request.getParameter("type");
			CommonMessage.debugMsg("Type in input"+type);
			String types=request.getParameter("types");
			CommonMessage.debugMsg("Types is input"+types);
			request.setAttribute("hiddenCompId",comp);
			
		    String toDate = request.getParameter("toDate");
			if(!UIUtils.isValidKeyId(toDate)){
				toDate = request.getParameter("dtToDate");
			}
			String fromDate = request.getParameter("fromDate");
			if(!UIUtils.isValidKeyId(fromDate)){
				fromDate = request.getParameter("dtFromDate");
			}
			request.setAttribute("hdndateId", entryDate);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
		
			String fromdate = CommonFunctions.getDate().substring(3,11);
			String todate = CommonFunctions.getDate().substring(3,11);
			request.setAttribute("fromdate", fromdate);
			request.setAttribute("todate", todate);
			request.setAttribute("type",type);
			request.setAttribute("types",types);
			UIUtils.forwardRequest(request, response,"/pages/ENTNEW/Reports/NewETTrainingPerPersons.jsp");
		} 
	
		else if(action.equals("NewQuadrant4Assessmentfrm_input.newentRpt")||action.equals("NewQuadrant3Assessmentfrm_input.newentRpt")){
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String topicId = request.getParameter("topicId");
			String flid = request.getParameter("flid");
			String keyid=request.getParameter("keyid");

			String Masterkeyid=request.getParameter("masterid");
			CommonMessage.debugMsg("ms key id is"+Masterkeyid);

			String masterid=request.getParameter("masterid");
		//	CommonMessage.debugMsg("The masterid"+masterid);
			request.setAttribute("masterid",masterid);

			request.setAttribute("topicId", topicId);
			request.setAttribute("flid", flid);
			request.setAttribute("keyid",keyid);
			request.setAttribute("masterid", Masterkeyid);
			request.setAttribute("loggedUser", user.getUsrm_ccno());
			  RequestDispatcher rd = request.getRequestDispatcher("/pages/ENTNEW/Reports/NewassesmentLevel.jsp");
			  rd.forward(request, response);			
		}
		///perfexitc_dev/WebContent/pages/ENTNEW/Reports/NewETTrainingPerPersons.jsp
		else if (action.equals("NewTrnhrsPerPersonPerMonth_getCol.newentRpt")) {
			httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
				
			CommonFilter commonFilter = new CommonFilter();
			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				 commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
			}
						
			if (commonFilter == null)
				commonFilter = new CommonFilter();
			commonFilter=populateCommonFilter(request,commonFilterIden,true);
			httpSession.removeAttribute(commonFilterIden);
			httpSession.setAttribute(commonFilterIden, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getSafty(request, commonFilter);
			
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
		
			String type=request.getParameter("type");
			CommonMessage.debugMsg("The Type:::::"+type);
			String types=request.getParameter("types");
			CommonMessage.debugMsg("Types is input"+types);

			if(UIUtils.isValidKeyId(type))
				commonFilter.setAbnViewType(type);
			/*if(UIUtils.isValidKeyId(types))
				commonFilter.setTrarId(types);
			*/
           
			List<String[]> impVscomList = newETReportService.getAllPerPerson(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			JSONObject jsonObject = getTableModel(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "73%%");
			jsonObject.set("tableWidth", "107%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		} 
		else if (action.equals("NewTrnNosPerPersonPerMonth_input.newentRpt")){
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String comp = request.getParameter("cmbCompid");
			//String type =request.getParameter("type");
			//CommonMessage.debugMsg("Type in input"+type);
			//String types=request.getParameter("types");
			//CommonMessage.debugMsg("Types is input"+types);
			request.setAttribute("hiddenCompId",comp);
			
		    String toDate = request.getParameter("toDate");
			if(!UIUtils.isValidKeyId(toDate)){
				toDate = request.getParameter("dtToDate");
			}
			String fromDate = request.getParameter("fromDate");
			if(!UIUtils.isValidKeyId(fromDate)){
				fromDate = request.getParameter("dtFromDate");
			}
			request.setAttribute("hdndateId", entryDate);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
		
			String fromdate = CommonFunctions.getDate().substring(3,11);
			String todate = CommonFunctions.getDate().substring(3,11);
			request.setAttribute("fromdate", fromdate);
			request.setAttribute("todate", todate);
			//request.setAttribute("type",type);
			//request.setAttribute("types",types);
			UIUtils.forwardRequest(request, response,"/pages/ENTNEW/Reports/NewETTrainingNosPerPersons.jsp");
		}
	 //http://localhost:6080/perfexitc/NewbatchCompletion_input.newentRpt?mode=VIEW
		else if (action.equals("NewbatchCompletion_input.newentRpt"))
		{
			//AdmTlUsermst user = UIUtils.getLoginUser(request);
			//request.setAttribute("User", user.getUsrm_ccno());
			String mode = request.getParameter("mode");
			if (!UIUtils.isValidKeyId(mode))
				mode = "CREATE";
				
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENTNEW/Reports/NewEntBatchCompletion.jsp");
			///perfexitc_dev/WebContent/pages/ENTNEW/Reports/NewEntBatchCompletion.jsp
			request.setAttribute("mode", mode);
			rd.forward(request, response); 
		}
		else if(action.equals("NewSkillGapReport_input.newentRpt"))
		{	
			RequestDispatcher rd = request.getRequestDispatcher( "/pages/ENTNEW/Reports/NewSkillGapReport.jsp");
			rd.forward(request, response); 
		}

		else if(action.equals("NewTrnNosPerPersonPerMonth_getCol.newentRpt"))
		{
		httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
				
			CommonFilter commonFilter = new CommonFilter();
			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				 commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
			}
						
			if (commonFilter == null)
				commonFilter = new CommonFilter();
			commonFilter=populateCommonFilter(request,commonFilterIden,true);
			httpSession.removeAttribute(commonFilterIden);
			httpSession.setAttribute(commonFilterIden, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getSafty(request, commonFilter);
			
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
           
			List<String[]> impVscomList = newETReportService.getAllNosPerPerson(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			JSONObject jsonObject = getTableModel(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "73%%");
			jsonObject.set("tableWidth", "107%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		}
		else if(action.equals("NewTrnNosPerPersonPerMonth_getData.newentRpt"))
		{
			try {
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				response.setContentType("text/html");
				httpSession.getAttribute(commonFilterIden);
				CommonMessage.debugMsg("commonFilter.getFlid()  "+commonFilter.getFlid());				
				//String type=request.getParameter("type");
				//String types=request.getParameter("types");
			  //  CommonMessage.debugMsg("Types is input"+types);


				//if(UIUtils.isValidKeyId(types))
				//	commonFilter.setAbnViewType(types);
				/*if(UIUtils.isValidKeyId(types))
					commonFilter.setTrarId(types);
				*/
				List<String[]> impVscomList = newETReportService.getAllNosPerPerson(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 3, 0);
				httpSession.removeAttribute(commonFilterIden);
				httpSession.setAttribute(commonFilterIden, commonFilter);
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
			
		}
		
		else if (action.equals("NewTrnhrsPerPersonPerMonth_getData.newentRpt")) { 
			try {
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				response.setContentType("text/html");
				httpSession.getAttribute(commonFilterIden);
				CommonMessage.debugMsg("commonFilter.getFlid()  "+commonFilter.getFlid());				
				String type=request.getParameter("type");
				String types=request.getParameter("types");
			    CommonMessage.debugMsg("Types is input"+types);


				if(UIUtils.isValidKeyId(type))
					commonFilter.setAbnViewType(type);
				/*if(UIUtils.isValidKeyId(types))
					commonFilter.setTrarId(types);
				*/
				List<String[]> impVscomList = newETReportService.getAllPerPerson(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
				httpSession.removeAttribute(commonFilterIden);
				httpSession.setAttribute(commonFilterIden, commonFilter);
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		
		else if( action.equals("NewTrnhrsPerPersonPerMonth_getExcel.newentRpt"))
		{			
			
		 httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		
		String type=request.getParameter("type");
		
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("ImprovementColData");
		
		String title=null;
		
		if(UIUtils.isValidKeyId(type))
			commonFilter.setAbnViewType(type);
		
		if(UIUtils.isValidKeyId(type)){
			tableModel.put("title", " Training hrs Per Person Report");
		     title="TraininghrsPerPersonPerMonth";
		}	
		else
		{
		    tableModel.put("title", " Kaizen Per Person Report");
		    title="ImprovementVsCompleted";
		}
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = newETReportService.perPersonKaizenExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, title, format);
		
		}
		else if( action.equals("NewTrnNosPerPersonPerMonth_getExcel.newentRpt"))
		{			
			
		 httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		
		String type=request.getParameter("type");
		
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("ImprovementColData");
		
		String title=null;
		
		if(UIUtils.isValidKeyId(type))
			commonFilter.setAbnViewType(type);
		
		if(UIUtils.isValidKeyId(type)){
			tableModel.put("title", " Training hrs Per Person Report");
		     title="TraininghrsPerPersonPerMonth";
		}	
		else
		{
		    tableModel.put("title", " New ET Nos Per Person Report");
		    title="ImprovementVsCompleted";
		}
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = newETReportService.NosperPersonKaizenExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, title, format);
		
		}
	 //chartNewETNosPerPerson.newentRpt
		else if(action.equals("chartNewETNosPerPerson.newentRpt"))
		{
			processChartETNosPerPerson(request,response);
			
		} 
	 
	 //chartNewETHrsPerPerson.newentRpt
		else if(action.equals("chartNewETHrsPerPerson.newentRpt"))
		{
			processChartEThrsPerPerson(request,response);
			
		} 
		
		else if(action.equals("NewETplanVScomp_getExcel.newentRpt")||action.equals("NewTrainingadherencereport_getExcel.newentRpt")){
			
			httpSession = request.getSession(false);                  
			CommonFilter commonFilter = populateCommonFilter(request,"ETplanVScompCommonFilter",false);
			String type=commonFilter.getType();
			 String custmrtype=request.getParameter("custmrtype");
			
			JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
			if("TRADHRPT".equals(type)){
				tblJSONObj.put("title","Training Adherence Report");
			}
			else if("TRADHMEMBERS".equals(type)){
				tblJSONObj.put("title","Training Adherence Members");
			}
			else if(UIUtils.isValidKeyId(custmrtype)){
				 if("ComplaintGallery".equals(custmrtype)){
					 tblJSONObj.put("title","Complaint Gallery");
				 }
				 else if("CRM".equals(custmrtype)){
					 tblJSONObj.put("title","Complaint Gallery");
				 }
			}
			else{
			      tblJSONObj.put("title","Training Program Plan Vs completed");
			}
            String format = ExcelUtils.getFormat(request);				
			Workbook wb = newETReportService.etPlanVsCompReportExportExcel(commonFilter,tblJSONObj,format,custmrtype);
			ExcelUtils.writeToResponse(response, wb, "ETPlanVsCompleteReport", format);
			
		}
	 
		else if( action.equals("NewETplanVScomp_getData.newentRpt")||action.equals("NewTrainingadherencereport_getData.newentRpt") )
		{
			PrintWriter out = response.getWriter();
		try
		{	
			 UIUtils.displayRequestParamsValue(request);
			
			 request.getParameter("page");	  
			 httpSession = request.getSession();                       
			 CommonFilter commonFilter = populateCommonFilter(request,"ETplanVScompCommonFilter",false);				 
			 commonFilter.setRowTotal('N');
			 JSONObject jsonObject = new JSONObject();				 
			
			 String type=request.getParameter("type");
			 
			 String custmrtype=request.getParameter("custmrtype");
				
			 CommonMessage.debugMsg(" Inside custmrtype :: getData ::"+custmrtype);
				
				
			 if(UIUtils.isValidKeyId(type)){
				 if("TRADHRPT".equals(type)||"TRADHMEMBERS".equals(type))
					commonFilter.setType(type);
			}else if(UIUtils.isValidKeyId(custmrtype)){
			      if("ComplaintGallery".equals(custmrtype)||"CRM".equals(custmrtype))
				    commonFilter.setType(custmrtype);
			 } 
			 
			 commonFilter.setViewClick('Y');					 	
			 httpSession.removeAttribute("ETplanVScompRptCommonFilter");				 
			 httpSession.setAttribute("ETplanVScompRptCommonFilter", commonFilter);
			 commonFilter.setIsGetCol("N");
			 List< String[]> ppMatMonList  = newETReportService.getPlanVsCompCal(commonFilter,custmrtype);	
		
			 // vignesh printing list of strings 
			 
			 for(String[] arr:ppMatMonList) {
				 CommonMessage.debugMsg(Arrays.toString(arr));
			 }
			 //if(ppMatMonList.size() > 4 )	
			 int strt= 3;
			  
			 
//			 if(UIUtils.isValidKeyId(type)){
//				    if("TRADHRPT".equals(type))
//					 strt=3;
//				 }
			 
			 if(UIUtils.isValidKeyId(custmrtype)){
			    if("ComplaintGallery".equals(custmrtype))
				 strt=2;
			 }
			 
			 jsonObject = UIUtils.convertToJqGridTableObject(ppMatMonList,request,strt,0);	 
			 
			 out.println(jsonObject);	
			 				 
		}			
		catch(Exception e)
		{
			CommonMessage.debugMsg(e.getMessage());
			JSONObject successData = new JSONObject();
			
    	
	    	 String sucessmsg = "noData";
	    	 successData.put("msg",sucessmsg );
	    	 out.println(successData);	
    	return;
		}
		
			
		}
else if(action.equals("NewQuadrant3Assessment_getCol.newentRpt")){
			
			try
			{
				CommonMessage.debugMsg("am ready");
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"assmlevel",false);
			
			 // If filter doesn't exist in session, create new one
	        if (commonFilter == null) {
	            commonFilter = populateCommonFilter(request, "assmlevel", true);
	        }
	        
			String level = request.getParameter("level");
			String mode = request.getParameter("mode");
			CommonMessage.debugMsg("The mode"+mode);
			String topicId = request.getParameter("topicId");

			CommonMessage.debugMsg("The topicId::::"+topicId);
			String masterkeyid=request.getParameter("masterid");
			CommonMessage.debugMsg("Mskeyid is"+masterkeyid);

			CommonMessage.debugMsg("The topicId"+topicId);

			String roleId = request.getParameter("roleId");
			CommonMessage.debugMsg("roleID is"+roleId);
			String selFlid = request.getParameter("selFlid");
			String masterid=request.getParameter("masterid");
			CommonMessage.debugMsg("The getcol masterid"+masterid);
			if(UIUtils.isValidKeyId(selFlid))
				commonFilter.setFlid(selFlid);
			if(!UIUtils.isValidKeyId(roleId))
				roleId = "";
			if(!UIUtils.isValidKeyId(topicId))
				topicId ="";

			if(!UIUtils.isValidKeyId(masterkeyid))
				masterkeyid="";
			commonFilter.setKey(masterkeyid);

			if(!UIUtils.isValidKeyId(masterid))
				masterid ="";
			

			commonFilter.setAssType(mode);
			commonFilter.setType(level);
			//commonFilter.setFlid(selFlid);
			commonFilter.setRefdocid(roleId);//for Setting Roleid
			commonFilter.setTopicid(topicId);
			commonFilter.setKey(masterid);
			//List<String[]> emplistData = null;
			//PrintWriter out = response.getWriter();
			commonFilter.setIsGetCol("Y");
			List<String[]> assemntLevel = newETReportService.getEmployeeLevel(commonFilter);
				//emplistData = trainingAttendanceService.getAllEmployee(progkeyid);
			//JSONObject jsonObject = getTableModelTrngEmp(Test);
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			CommonMessage.debugMsg("mode+mode   "+mode);
			jqGridTableModel.setMultiSelect(true);
			jqGridTableModel.setGridEdit(true);
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = assemntLevel.get(1);			
			String [] colHeaderCond = assemntLevel.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
 
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "70%%");
			boolean modeg = true;
			if("grid".equals(mode)){
				//CommonMessage.debugMsg("inside mode grid");
				CommonMessage.debugMsg("inside mode grid");
				modeg = false;
			}
			 CommonMessage.debugMsg("modeg   "+modeg);
			jsonObject.set("multiSelect", modeg);
			 jsonObject.set("gridEdit", modeg);
			jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("hidden",true);
			CommonMessage.debugMsg(colHeader.length+"    Colmodel  "+jsonObject);
			httpSession.setAttribute("assmLevelColMod",jsonObject);
			out.println(jsonObject);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
//else if(action.equals("NewQuadrant3Assessment_getCol.newentRpt")){
//			
//			try
//			{
//				CommonMessage.debugMsg("am ready");
//			PrintWriter out = response.getWriter();
//			CommonFilter commonFilter = populateCommonFilter(request,"assmlevel",true);
//			String level = request.getParameter("level");
//			String mode = request.getParameter("mode");
//			CommonMessage.debugMsg("The mode"+mode);
//			String topicId = request.getParameter("topicId");
//
//			CommonMessage.debugMsg("The topicId::::"+topicId);
//			String masterkeyid=request.getParameter("masterid");
//			CommonMessage.debugMsg("Mskeyid is"+masterkeyid);
//
//			CommonMessage.debugMsg("The topicId"+topicId);
//
//			String roleId = request.getParameter("roleId");
//			CommonMessage.debugMsg("roleID is"+roleId);
//			String selFlid = request.getParameter("selFlid");
//			String masterid=request.getParameter("masterid");
//			CommonMessage.debugMsg("The getcol masterid"+masterid);
//			if(UIUtils.isValidKeyId(selFlid))
//				commonFilter.setFlid(selFlid);
//			if(!UIUtils.isValidKeyId(roleId))
//				roleId = "";
//			if(!UIUtils.isValidKeyId(topicId))
//				topicId ="";
//
//			if(!UIUtils.isValidKeyId(masterkeyid))
//				masterkeyid="";
//			commonFilter.setKey(masterkeyid);
//
//			if(!UIUtils.isValidKeyId(masterid))
//				masterid ="";
//			
//
//			commonFilter.setAssType(mode);
//			commonFilter.setType(level);
//			//commonFilter.setFlid(selFlid);
//			commonFilter.setRefdocid(roleId);//for Setting Roleid
//			commonFilter.setTopicid(topicId);
//			commonFilter.setKey(masterid);
//			//List<String[]> emplistData = null;
//			//PrintWriter out = response.getWriter();
//			List<String[]> assemntLevel = newETReportService.getEmployeeLevel(commonFilter);
//				//emplistData = trainingAttendanceService.getAllEmployee(progkeyid);
//			//JSONObject jsonObject = getTableModelTrngEmp(Test);
//			
//			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
//			GridColModel gridColModel = new GridColModel();
//			
//			jqGridTableModel.setRowNumbers(true);
//			jqGridTableModel.setEnableFilter(true);
//			jqGridTableModel.setTableButton(true);
//			CommonMessage.debugMsg("mode+mode   "+mode);
//			jqGridTableModel.setMultiSelect(true);
//			jqGridTableModel.setGridEdit(true);
//			gridColModel.setHeaderNum(1);
//			
//			String [] colHeader = assemntLevel.get(1);			
//			String [] colHeaderCond = assemntLevel.get(0);
//			
//			List<String[]> headers = new ArrayList<String[]>();
//			//headers.add(colHeaderCond);
//			headers.add(colHeader);
// 
//			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
//			jsonObject.set("tableWidth", "106%%");
//			jsonObject.set("tableHeight", "70%%");
//			boolean modeg = true;
//			if("grid".equals(mode)){
//				//CommonMessage.debugMsg("inside mode grid");
//				CommonMessage.debugMsg("inside mode grid");
//				modeg = false;
//			}
//			 CommonMessage.debugMsg("modeg   "+modeg);
//			jsonObject.set("multiSelect", modeg);
//			 jsonObject.set("gridEdit", modeg);
//			jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("hidden",true);
//			CommonMessage.debugMsg(colHeader.length+"    Colmodel  "+jsonObject);
//			httpSession.setAttribute("assmLevelColMod",jsonObject);
//			out.println(jsonObject);
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
//		else if(action.equals("NewQuadrant3Assessment_getData.newentRpt")){
//			try {
//				 PrintWriter out = response.getWriter();
//				 String progkeyid = request.getParameter("progkeyid");
//				 String date = request.getParameter("date");
//				 String mode = request.getParameter("mode");
//                 String level = request.getParameter("level");
//            //     String masterid=request.getParameter("masterid");
//     			// CommonMessage.debugMsg("The getcol masterid"+masterid);
//				CommonFilter commonFilter = populateCommonFilter(request,"assmlevel",false);
// 				 commonFilter.setProgram(progkeyid);
// 				 commonFilter.setAssType(mode);
// 				 commonFilter.setType(level);
// 			//	commonFilter.setKey(masterid);
// 				String selFlid = request.getParameter("selFlid");
// 				if(UIUtils.isValidKeyId(selFlid))
// 					commonFilter.setFlid(selFlid);
//
//				 CommonMessage.debugMsg("get data method");
//				 List<String[]> emplistData = newETReportService.getEmployeeLevel(commonFilter);
//				 JSONObject emplist= UIUtils.convertToJqGridTableObject(emplistData, request, 2,0);
//				 out.println(emplist);
//			}catch(Exception e){
//				
//			}
//		}
else if(action.equals("NewQuadrant3Assessment_getData.newentRpt")){
	try {
		 PrintWriter out = response.getWriter();
		 String progkeyid = request.getParameter("progkeyid");
		 String date = request.getParameter("date");
		 String mode = request.getParameter("mode");
         String level = request.getParameter("level");
    //     String masterid=request.getParameter("masterid");
			// CommonMessage.debugMsg("The getcol masterid"+masterid);
		CommonFilter commonFilter = populateCommonFilter(request,"assmlevel",false);
		
		
		  // If filter doesn't exist, create new one
        if (commonFilter == null) {
            commonFilter = populateCommonFilter(request, "assmlevel", true);
        }
			 commonFilter.setProgram(progkeyid);
			 commonFilter.setAssType(mode);
			 commonFilter.setType(level);
		//	commonFilter.setKey(masterid);
			String selFlid = request.getParameter("selFlid");
			if(UIUtils.isValidKeyId(selFlid))
				commonFilter.setFlid(selFlid);

		 CommonMessage.debugMsg("get data method");
		 commonFilter.setIsGetCol("N");
		 List<String[]> emplistData = newETReportService.getEmployeeLevel(commonFilter);
		 JSONObject emplist= UIUtils.convertToJqGridTableObject(emplistData, request, 2,0,commonFilter.getTotalRecordCnt());
		 out.println(emplist);
	}catch(Exception e){
		
	}
}
		 else if(action.equals("NewQuadrant3Assessment_getExcel.newentRpt")){
				CommonFilter commonFilter1 = populateCommonFilter(request,"assmlevel",false);
				String tmpFromRow = commonFilter1.getFromRow();
				commonFilter1.setFromRow(null);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("assmLevelColMod");
				String level = request.getParameter("level");
				String levelNo = "3";
				if (level.length()>=5)
					levelNo = level.substring(5);
				
				colmodel.put("title","Level "+levelNo+" Assesment");
			    String format = ExcelUtils.getFormat(request);
				Workbook wb = newETReportService.assemeExcel(commonFilter1,colmodel,format);
				commonFilter1.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response,wb,"Level"+levelNo+"Assesment", format);	
			
	}
		else if( action.equals("NewbatchCompletion_getCol.newentRpt"))
		{	
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"BatchComplnCommonFilter",true);			
			String mode = request.getParameter("mode");
			CommonMessage.debugMsg("mode===="+mode);
			commonFilter.setType(mode);
			List<String[]> batchCompList  =  newETReportService.getBatchComplnDatas(commonFilter);
			JSONObject batchCompData = UIUtils.convertToJqGridTableObject(batchCompList,request,3,0,commonFilter.getTotalRecordCnt()); 
			
			httpSession.setAttribute("batchCompDataServlet", batchCompData);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);			
			gridColModel.setHeaderNum(1);
			List<String> formattorList =  new ArrayList<String>();
			
			formattorList.add("button_SaveBatch");
			formattorList.add("button_cancelBatch");
			formattorList.add("button_SaveBudget");
			List<String> formattorFromList =  new ArrayList<String>();

			String [] colHeader = batchCompList.get(1);			
			String [] colHeaderCond = batchCompList.get(0);
			formattorFromList.add("4");
			formattorFromList.add("5");
			formattorFromList.add("6");
			List<String> formattorToList =  new ArrayList<String>();
			formattorToList.add("4");
			formattorToList.add("5");
			formattorToList.add("6");
			gridColModel.setMultiformatter(formattorList);
			gridColModel.setMultiformattorFromCol(formattorFromList);
			gridColModel.setMultiformattorToCol(formattorToList);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "80%%");
			jsonObject.put("tableButton", false);
			/*
			jsonObject.put("isGroupBy", "false");
			jsonObject.put("groupByField", false);
			jsonObject.put("rowNumbers", true);
			jsonObject.put("groupSummary", false);
			jsonObject.put("tableButton", true);
			*/
			//jsonObject.put("tableHeight", "75%");
			jsonObject.put("data", batchCompData);
			
			CommonMessage.debugMsg(jsonObject);
			httpSession.removeAttribute("batchCompColModel");
			httpSession.setAttribute("batchCompColModel", jsonObject);
			out.println(jsonObject);
			//String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.ENTBatchCompln", "getColModel");
			//httpSession.removeAttribute("batchCompColModel");
			//httpSession.setAttribute("batchCompColModel", colModel);	
			//out.println(colModel);
			//PrintWriter out = response.getWriter();
			//CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.ENTBatchCompln", "getColModel"));
		    //out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ENTBatchCompln", "getColModel"));
		}
		else if( action.equals("NewbatchCompletion_getData.newentRpt"))
		{
			try
			{
			List<String []> batchComplnGrid  = null;
			PrintWriter out = response.getWriter();
			UIUtils.displayRequestParamsValue(request);
			String page = request.getParameter("page");
			String mode = request.getParameter("mode");
			CommonFilter commonFilter = populateCommonFilter(request,"BatchComplnCommonFilter",true);
			CommonMessage.debugMsg("mode===="+mode);
			commonFilter.setType(mode);
			CommonMessage.debugMsg("Total Record Count : "+commonFilter.getTotalRecordCnt());
			JSONObject jsonObject = new JSONObject();
			
			jsonObject = (JSONObject) httpSession.getAttribute("batchCompDataServlet");
			List<String[]> batchCompList  =  newETReportService.getBatchComplnDatas(commonFilter);
			jsonObject = UIUtils.convertToJqGridTableObject(batchCompList,request,2,0,commonFilter.getTotalRecordCnt()+3); 
			 out.println(jsonObject);
			 commonFilter.setViewClick('N');	  			 	
 			 httpSession.removeAttribute("BatchComplnCommonFilter");
 			 httpSession.setAttribute("BatchComplnCommonFilter", commonFilter);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		
		}
		else if(action.equals("NewSkillGapReport_getCol.newentRpt"))
		{	
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"SkillGapReportCommonFilter",true);
				PrintWriter out = response.getWriter();
				String empmid=request.getParameter("empmid");
				String unqid=request.getParameter("unqid");
				
				if(UIUtils.isValidKeyId(empmid))
					commonFilter.setKey(empmid);
				
				if(UIUtils.isValidKeyId(unqid))
					commonFilter.setUniquePos(unqid);
					
				List<String[]> SkillGapReportGrid = newETReportService.getSkillGapReportGrid(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();
				GridColModel gridColModel = new GridColModel();
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);
				String [] colHeader = SkillGapReportGrid.get(1);
				String [] colHeaderHead = SkillGapReportGrid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				httpSession.setAttribute("SkillGapReportDatas", colmodel);
				colmodel.set("tableWidth", "106%%");
				colmodel.set("tableHeight", "80%%");
				out.println(colmodel);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		else if(action.equals("NewSkillGapReport_getData.newentRpt"))
		{	
			try
			{   
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);	
				CommonFilter commonFilter = populateCommonFilter(request,"SkillGapReportCommonFilter",false);
                

				String empmid=request.getParameter("empmid");
				String unqid=request.getParameter("unqid");
				
				if(UIUtils.isValidKeyId(empmid))
					commonFilter.setKey(empmid);
				
				if(UIUtils.isValidKeyId(unqid))
					commonFilter.setUniquePos(unqid);
				
				
				List<String[]> SkillGapReportGrid = newETReportService.getSkillGapReportGrid(commonFilter);
				JSONObject SkillGridData = UIUtils.convertToJqGridTableObject(SkillGapReportGrid,request,2,0,commonFilter.getTotalRecordCnt()); 
				
  			 	out.println(SkillGridData);  
  			 	
             }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}

		}else if(action.equals("NewSkillGapReport_getExcel.newentRpt"))
		{
			//************************************************
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"SkillGapReportCommonFilter",false);	
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String empmid=request.getParameter("empmid");
			String unqid=request.getParameter("unqid");
			
			if(UIUtils.isValidKeyId(empmid))
				commonFilter.setKey(empmid);
			
			if(UIUtils.isValidKeyId(unqid))
				commonFilter.setUniquePos(unqid);
			
						
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("SkillGapReportDatas");
			colmodel.put("title","Skill Gap Report");
            String format = ExcelUtils.getFormat(request);
			
			Workbook wb = newETReportService.getSkillGapReportExcel(colmodel,format,commonFilter);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "SkillGapReport", format);
			
		}
		else if(action.equals("NewSkillGapReport_recall.newentRpt"))
		{
			PrintWriter out = response.getWriter();
			String keyid = request.getParameter("KEYID");
			String type = request.getParameter("type");
			CommonMessage.debugMsg("keyid   keyid  :  "+keyid+" type :: "+type);
			List<String []> condReclData  = newETReportService.FillControlData(keyid,type);
			out.print( JSONArray.fromCollection(condReclData));
		}
		else if(action.equals("NewNominatnReport_getCol.newentRpt"))
		{
			 httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				String firstClick =request.getParameter("firstClick");
				String flid= request.getParameter("flid");
				CommonFilter commonFilter = populateCommonFilter(request,"NominationReportCommonFilter",true);
				httpSession.removeAttribute("NominationReportCommonFilter");
				httpSession.setAttribute("NominationReportCommonFilter",commonFilter);
				if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
		   	    List< String[]> nomtnList  = newETReportService.getnomitnrpt(commonFilter);	
		   	    
		   	    CommonMessage.debugMsg(nomtnList.size() + "  near servlet");
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				gridColModel.setHeaderNum(1);		
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setGroupBy(true);
				jqGridTableModel.setGroupByField("TITLE");
				List<String[]> headers = new ArrayList<String[]>();				
				String [] colHeader2 = nomtnList.get(1);
				String [] colHeaderHead = nomtnList.get(0);
				headers.add(colHeader2);	
				
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
		   	    CommonMessage.debugMsg(jsonObject + " jsonobject");
				jsonObject.set("tableWidth", "106%%");
		     	jsonObject.set("tableHeight", "72%%");
		   	    httpSession.removeAttribute("nomiatnColModel");
				httpSession.setAttribute("nomiatnColModel",jsonObject);			
				out.println(jsonObject);
			    
			
		}
		else if(action.equals("NewNominatnReport_getData.newentRpt"))
		{
			try
			{	  	
				httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				String flid= request.getParameter("flid");
				UIUtils.displayRequestParamsValue(request);
				CommonFilter commonFilter = populateCommonFilter(request,"NominationReportCommonFilter",false);
				if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
       		    List<String[]> nomintnData  =  newETReportService.getnomitnrpt(commonFilter);
       		    JSONObject jsonObject  =UIUtils.convertToJqGridTableObject(nomintnData, request, 2, 0, commonFilter.getTotalRecordCnt());
				out.println(jsonObject);
				httpSession.removeAttribute("NominationReportCommonFilter");
				 httpSession.setAttribute("NominationReportCommonFilter", commonFilter);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("error " +e.getMessage());
			}
		}
		else if(action.equals("NewNominatnReport_getExcel.newentRpt"))
		{
			httpSession = request.getSession(false);
			CommonFilter commonFilter1 = populateCommonFilter(request,"NominationReportCommonFilter",false);	
			String tmpFromRow = commonFilter1.getFromRow();
			commonFilter1.setFromRow(null);
			JSONObject colmodel = UIUtils.getXlColModel(request,response);
			colmodel.put("title","Nomination List");
			 CommonMessage.debugMsg(colmodel + " colmodel") ;
            String format = ExcelUtils.getFormat(request);
			Workbook wb = newETReportService.getnomitnExportToExcel(colmodel,format,commonFilter1);
			commonFilter1.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "NominationReport", format);
			
		}
		else if(action.equals("newTraSummRpt_input.newentRpt")){
           	CommonMessage.debugMsg("TraSummReport");
            UIUtils.forwardRequest(request,response,"pages/ENTNEW/Reports/NewTrainingSummReport.jsp"); 	
		}
		/*else if(action.equals("newTraSummRpt_getCol.newentRpt")){
			    CommonMessage.debugMsg("Training Summary Report getCol");
			    httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				String flid= request.getParameter("flid");
				CommonFilter commonFilter = populateCommonFilter(request,"TrainingSummReportCommonFilter",true);
				httpSession.removeAttribute("TrainingSummReportCommonFilter");
				httpSession.setAttribute("TrainingSummReportCommonFilter",commonFilter);
				if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
		   	    List< String[]> nomtnList  = newETReportService.getTrainingSummRpt(commonFilter);
		   	    
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				gridColModel.setHeaderNum(1);		
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setGroupBy(true);
				List<String[]> headers = new ArrayList<String[]>();				
				String [] colHeader2 = nomtnList.get(1);
				String [] colHeaderHead = nomtnList.get(0);
				headers.add(colHeader2);	
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "106%%");
		     	jsonObject.set("tableHeight", "72%%");
		   	    httpSession.removeAttribute("TrainingSummColModel");
				httpSession.setAttribute("TrainingSummColModel",jsonObject);			
				out.println(jsonObject);
		}
		else if(action.equals("newTraSummRpt_getData.newentRpt"))
		{
			try
			{	
				CommonMessage.debugMsg("Training Summary Report getData");
				httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				String flid= request.getParameter("flid");
				UIUtils.displayRequestParamsValue(request);
				CommonFilter commonFilter = populateCommonFilter(request,"TrainingSummReportCommonFilter",false);
				if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
       		    List<String[]> nomintnData  =  newETReportService.getTrainingSummRpt(commonFilter);
       		    JSONObject jsonObject  =UIUtils.convertToJqGridTableObject(nomintnData, request, 2, 0, commonFilter.getTotalRecordCnt());
				out.println(jsonObject);
				httpSession.removeAttribute("TrainingSummReportCommonFilter");
				httpSession.setAttribute("TrainingSummReportCommonFilter", commonFilter);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("error " +e.getMessage());
			}
		}*/
		   else if(action.equals("newTraSummRpt_getCol.newentRpt")){
	           PrintWriter out = response.getWriter();
	          CommonFilter commonFilter=new CommonFilter();
	          JSONObject jsonObject = new JSONObject();
	          commonFilter  = populateCommonFilter(request,"TrainingSummReportCommonFilter",true);   
	          try
	          {  
	        	   String flid=request.getParameter("flid");
	        	   CommonMessage.debugMsg("flidflidflidflid... ..."+commonFilter.getFlid());
	                if(UIUtils.isValidKeyId(flid)){
	                	flid=flid;
	                }
	                else{
	                	flid=commonFilter.getFlid();
	                }
	                commonFilter.setFlid(flid);
	            	commonFilter.setIsGetCol("Y");
				    List< String[]> AuditListGrid  = newETReportService.getTrainingSummRpt(commonFilter);	

	               JqGridTableModel jqGridTableModel = new  JqGridTableModel();           
	               GridColModel gridColModel = new GridColModel();           
	             jqGridTableModel.setSortable(true);
	       		jqGridTableModel.setTableButton(true);
	       		jqGridTableModel.setEnableFilter(true);
	       		jqGridTableModel.setRowNumbers(true);
	       		gridColModel.setHeaderNum(1);//9
	       		gridColModel.setFormattorFromCol("0");
	       		gridColModel.setFormattorToCol("0");
	               
	               String [] colHeaderHead = AuditListGrid.get(0);
	               String [] colHeader = AuditListGrid.get(1);
	               List<String[]> headers = new ArrayList<String[]>();   
	               headers.add(colHeader);           
	               jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);           
	                jsonObject.set("tableWidth", "108%%");
	                jsonObject.set("tableHeight", "80%%");
	                httpSession.removeAttribute("TrainingSummColModel");
	                httpSession.setAttribute("TrainingSummColModel",jsonObject);   
	                out.println(jsonObject);                  
	              
	          }catch(Exception e)
	          {
	              CommonMessage.debugMsg(e.getMessage());
	          }  
	           }
			   
		   else if(action.equals("newTraSummRpt_getData.newentRpt")){
	              PrintWriter out = response.getWriter();
	              CommonFilter     commonFilter  = populateCommonFilter(request,"TrainingSummReportCommonFilter",false);   
	              try
	              {   
	                  UIUtils.displayRequestParamsValue(request);   
	                  
	                  String flid=request.getParameter("flid");
		        	   CommonMessage.debugMsg("flidflidflidflid... IN getData..."+commonFilter.getFlid());
		                if(UIUtils.isValidKeyId(flid)){
		                	flid=flid;
		                }
		                else{
		                	flid=commonFilter.getFlid();
		                }
		                commonFilter.setFlid(flid);
		            	commonFilter.setIsGetCol("N");
				  List<String[]> Grid  = newETReportService.getTrainingSummRpt(commonFilter);	
	              	
	                     JSONObject Data = UIUtils.convertToJqGridTableObject(Grid,request,2,0,commonFilter.getTotalRecordCnt()+2); 
	                     commonFilter.setViewClick('N'); 
	             	 	httpSession.removeAttribute("TrainingSummReportCommonFilter");
	      			 	httpSession.setAttribute("TrainingSummReportCommonFilter", commonFilter);
	                     out.println(Data); 
	              }catch(Exception e)
	              {
	                  CommonMessage.debugMsg(e.getMessage());
	              }
	       }
			 else if(action.equals("newTraSummRpt_getExcel.newentRpt")){
					 CommonFilter commonFilter = new CommonFilter();
					 commonFilter = populateCommonFilter(request,"TrainingSummReportCommonFilter",false);
					 String tmpFromRow = commonFilter.getFromRow();
						commonFilter.setFromRow(null);
					 JSONObject colmodel = (JSONObject) httpSession.getAttribute("TrainingSummColModel");
					 colmodel.put("title","TrainingCalendar Report");
		             String format = ExcelUtils.getFormat(request);
					Workbook wb = newETReportService.getTrainingSummaryExcel(colmodel,format,commonFilter);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb, "TrainingSummaryReport", format);
					
				}
				
	 
		else if(action.equals("NewNominatnReport_getData.newentRpt"))
		{
			try
			{	
				CommonMessage.debugMsg("Training Summary Report getData");
				httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				String flid= request.getParameter("flid");
				UIUtils.displayRequestParamsValue(request);
				CommonFilter commonFilter = populateCommonFilter(request,"TrainingSummReportCommonFilter",false);
				if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
       		    List<String[]> nomintnData  =  newETReportService.getTrainingSummRpt(commonFilter);
       		    JSONObject jsonObject  =UIUtils.convertToJqGridTableObject(nomintnData, request, 2, 0, commonFilter.getTotalRecordCnt());
				out.println(jsonObject);
				httpSession.removeAttribute("TrainingSummReportCommonFilter");
				httpSession.setAttribute("TrainingSummReportCommonFilter", commonFilter);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("error " +e.getMessage());
			}
		}
	 
}

	private void saveAssementlevel(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	if( httpSession != null && user != null)
    	{	
    		 String levelData =  request.getParameter("asslevel");
 		    String identifyLevel = request.getParameter("curLevel");
 		   String topicId = request.getParameter("topicId");
 		  String keyId = request.getParameter("Keyid");
 		  
 		 String level = request.getParameter("level");
 		 
 		String levelValue = request.getParameter("hdnLevel");
 		CommonMessage.debugMsg("level val"+levelValue);
 		 String UpdateList = request.getParameter("UpdateList");
 		 //UpdateList
 		 String userid=user.getUsrm_ccno();
 		  EntTlTragcalquad existEntTlTragcalquad= (EntTlTragcalquad)httpSession.getAttribute("newEntTlTragcalquad"); 
 		 EntTlTragcalquad newEntTlTragcalquad = new EntTlTragcalquad();//mas
    		
		    CommonMessage.debugMsg("levelData  "+levelData);
		    CommonMessage.debugMsg("identifyLevel  "+identifyLevel);
		    CommonMessage.debugMsg("topicId  "+topicId);
		    CommonMessage.debugMsg("keyId  "+keyId);
		    CommonMessage.debugMsg("level  "+level);
		    CommonMessage.debugMsg("UpdateList  "+UpdateList);
		    //CommonMessage.debugMsg("UpdateList 1 "+UpdateList.replace(",'));
		   
		    
		    List<EntTlTragcalquad> lstEntTlEmployeeRatting = null;
		    JSONArray jsonLevelArray = null;
		    newEntTlTragcalquad.setEtcqCreatedby(user.getUsrm_ccno());
		  if(UIUtils.isValidKeyId(levelData)){ //master json
				if( levelData != null && ! levelData.isEmpty())
	    		{
					CommonMessage.debugMsg("coming inside");
					jsonLevelArray = JSONArray.fromString(levelData);
					CommonMessage.debugMsg("jsonLevelArray"+jsonLevelArray);
					CommonMessage.debugMsg("jsonLevelArray"+jsonLevelArray);
					lstEntTlEmployeeRatting=(List<EntTlTragcalquad>)UIUtils.convertJSONArrToList(newEntTlTragcalquad, jsonLevelArray);
					CommonMessage.debugMsg("size lstEntTlEmployeeRatting..."+lstEntTlEmployeeRatting.size());
					CommonMessage.debugMsg("size lstEntTlEmployeeRatting..."+lstEntTlEmployeeRatting);
	    		}

				  if(lstEntTlEmployeeRatting!=null)
				   {
					  CommonMessage.debugMsg("coming insid2e");
					   newEntTlTragcalquad.setEntTlEmployeeRatting(lstEntTlEmployeeRatting);
						CommonMessage.debugMsg("lstEntTlEmployeeRatting.size(): "+lstEntTlEmployeeRatting.size());
						for(int i=0;i<lstEntTlEmployeeRatting.size();i++){
							CommonMessage.debugMsg("coming inside of for");
							CommonMessage.debugMsg("employee: "+newEntTlTragcalquad.getEntTlEmployeeRatting().get(i).getEtcqEmpmKeyid());
							CommonMessage.debugMsg("evaldate: "+newEntTlTragcalquad.getEntTlEmployeeRatting().get(i).getEtcqCurrLevel());
						}
					}
			
			   JSONObject successData=new JSONObject();
						JSONObject AssementEffSuccessMsg=new JSONObject();
				String savemsg="";	
						try
						{
							//newEntTlTragcalquad.setLevel(identifyLevel);
						//newEntTlTragcalquad.setTopicId(topicId);
					//	CommonMessage.debugMsg("newEntTlEmployeeRatting.setLevel(   "+newEntTlTragcalquad.getEtcqCurrLevel());
				//		CommonMessage.debugMsg("newEntTlEmployeeRatting.settopicl(   "+newEntTlTragcalquad.getEtcqTopicid());
							if(  UIUtils.isValidKeyId (UpdateList)  )//NOT NULL CRETTE
							 {
								CommonMessage.debugMsg("Inside of IF");
								existEntTlTragcalquad=newETReportService.createAssmLevel(UpdateList,userid);
								//newEntTlEmployeeRatting =entTlAssessmentmstService.create(newEntTlEmployeeRatting,existEntTlEmployeeRatting,entTlAssessmentmstBean);
								 savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save");
							}
							/*else{
								CommonMessage.debugMsg("Update ASsmlevel  ");
								 existEntTlTragcalquad=newETReportService.updateAssmLevel(newEntTlTragcalquad, existEntTlTragcalquad);
								 savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-update"); //"Data Updated Succesfully";
							 }*/
			        	       successData.put("msg", savemsg);
			        	       String keyid = existEntTlTragcalquad.getEtcqKeyid();
			        	       CommonMessage.debugMsg("keyiddd-"+keyid);
			        	       successData.put("keyid", keyid);
			        	       AssementEffSuccessMsg.put("successData", successData);
			        	       
			        	       AssementEffSuccessMsg.put("formClear", false);
			    		      out.print(AssementEffSuccessMsg.toString());
			
					}catch (ValidationExceptions e)
					{
							JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"TrainingAtten");
			                 out.print(errMessage.toString());
		    	  }
					catch(Exception e){
							CommonMessage.debugMsg("Error Msg:" + e.getMessage());
							JSONObject err = new JSONObject();
							err.put("tpmException", "Data Not Saved");
							out.print(err.toString());
					}

		  }   
    	}
		
}

	
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);//Modified By Dhanalakshmi.R For DashBoard Graph on 7/3/13
		String rowId=request.getParameter("rownum");
		String Flid=request.getParameter("flid");
		CommonMessage.debugMsg(Flid + " flid process ");
		CommonFilter commonFilter =null;
		String chkline=request.getParameter("chkline");
		String forDashboard = request.getParameter("dashboard");
		String FirstLevel="Y";
		CommonMessage.debugMsg("chk grph");
		 //commonFilter = populateCommonFilter(request,"ETplanVScompRptCommonFilter",false);
		 //commonFilter.setFirstLevel(FirstLevel);
		if( ! "true".equals(forDashboard))
		{
			commonFilter = (CommonFilter) httpSession.getAttribute("ETplanVScompRptCommonFilter");
		}
		else
		{
			commonFilter = new  CommonFilter();
			
			FilterValues.getTraning(request, commonFilter);	
			/*if(!UIUtils.isValidKeyId(rowId))
				if(action.equals("dashBoardBarChart.ETPlCompRPT"))
					rowId="4";
				else
					rowId="1";*/
			if(!UIUtils.isValidKeyId(chkline))
				if(action.equals("dashBoard.ETPlCompRPT"))
					chkline="1";
		}
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		/* CommonMessage.debugMsg(commonFilter.getKey() +  " conflid");
		if(!UIUtils.isValidKeyId(commonFilter.getKey())){
			commonFilter.setRowTotal('Y');
		}
		else */
		//chartCommonFilter.setFirstLevel(FirstLevel);
		
		String tot=request.getParameter("tot");
		if("TOTAL".equals(tot)){
			chartCommonFilter.setRowTotal('Y');
			chartCommonFilter.setTotal(tot);
			
		}else{
			FilterValues.getCommonFilters(request,chartCommonFilter);
			chartCommonFilter.setFirstLevel("Y");
		}
		/*if( Constants.passNullDate.contains(chartCommonFilter.getFromMonth())&& (chartCommonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) )
		{
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	 }*/
		
		String type=request.getParameter("type");
		
		String Custype=request.getParameter("Custype");
		
		
		 if(UIUtils.isValidKeyId(type)){
			 if("TRADHRPT".equals(type)||"TRADHMEMBERS".equals(type))
				 chartCommonFilter.setType(type);
		 }
			CommonMessage.debugMsg("chk grph1");
		List<String[]> trendList  = newETReportService.getPlanVsCompReportgraph(chartCommonFilter,Flid,Custype);
		List<String[]> cur1Grid =  transposeListArr(trendList);
		JSONObject chartObj = null;
		
		CommonMessage.debugMsg(" After Checking 12 ");
		
		if(UIUtils.isValidKeyId(chkline))
		{
			chartObj = processLinegraph(cur1Grid,commonFilter,rowId,type,Custype);	
		}
		else
		{
			chartObj = processBarChart(cur1Grid,commonFilter,rowId,type);
		}
		commonFilter = null;
		UIUtils.dashBoardSetChartObject(request,chartObj);
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();			
	}


	private List<String[]> transposeListArr(List<String[]> dataList)
	{
		
		if( dataList.size() <=0 ) return null;
		List<String[]> transposeList = new ArrayList<String[]>();		
		for( int i =0; i<dataList.size(); i++)
		{	
			String [] tRow = new String [ dataList.get(0).length];
			for (int j=0; j<dataList.get(0).length;j++)
			{
				tRow [ j ]= dataList.get(i)[j];//.equals("0")?dataList.get(i)[j].replace("0", "-"):dataList.get(i)[j];
			}
			transposeList.add(tRow);
		}
		return transposeList;
	}
	
private JSONObject processLinegraph(List<String[]> trendList,CommonFilter commonFilter,String rowId, String type, String custype){
		
		if( trendList == null || trendList.size() <= 1  )
			return null;
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		new ChartSeries();
	
		CommonMessage.debugMsg(" Checking For DrillCaption()"+commonFilter.getDrillCaption());
		
		String title=null;
		if(UIUtils.isValidKeyId(type)){
			  if("TRADHRPT".equals(type))
				  title = "Training Adherence - Sessions";
			  else
				  title = "Training Adherence - Members";
			}else if(UIUtils.isValidKeyId(custype)){
			    if("ComplaintGallery".equals(custype))
			    	title = "Complaint Gallery Report";
			    else
			    	title = "CRM Report";
			}else
		         title = "Training Program Plan Vs Complete";
		
		String [] header =  trendList.get(2);   // vignesh  -1
		String [] month =  trendList.get(1);    // vigneshh -1
		//int row = Integer.parseInt(rowId)+2;
		int dats=3;  // vigneshh -1
		if("ComplaintGallery".equals(custype)){
			dats=3;	 // vigneshh -1
		}
		String [] data = trendList.get(dats);
		 
		//String [] cummulative=null;
		//cummulative =trendList.get(trendList.size()-1);
		
		
		String prevMonth = null;
		
		String subTitle = data[3]; // 
		
		if("TRADHMEMBERS".equals(type)&& UIUtils.isValidKeyId(type)){
			subTitle=data[2];  
		}else if("TRADHRPT".equals(type)&& UIUtils.isValidKeyId(type)){
			subTitle=data[2];  // for jh-c
		}
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		
		ChartSeries barChart = new ChartSeries();
		List<Double> barDataList = new ArrayList<Double>();
		
		ChartSeries adhChart = new ChartSeries();
		List<Double> AdherenceDataList = new ArrayList<Double>();
		
		ChartSeries complaintChart = new ChartSeries();
		List<Double> complaintDataList = new ArrayList<Double>();
		
		ChartSeries crmChart = new ChartSeries();
		List<Double> crmDataList = new ArrayList<Double>();
		
		new ArrayList<Double>();
		
		new ChartSeries();
		new ArrayList<Double>();
		
		
		int startRow = 3; // 4 to 3 vignesh
		int headr = header.length;
		if("ComplaintGallery".equals(custype) ){
			startRow = 3;
		     headr=header.length-1;  // removing -1 
		}
		else if("CRM".equals(custype)){
			startRow =1;
			headr = header.length;
		}
		for( int i = startRow;i < headr;i++ ){

			
			if(UIUtils.isValidKeyId(custype)){
				 if("ComplaintGallery".equals(custype)){
					// if(header[i].contains("Completed") ){
						 complaintDataList.add(Double.parseDouble(data[i]));
					// }
				 }else if("CRM".equals(custype)){
					 //if(header[i].contains("Completed") ){
						 crmDataList.add(Double.parseDouble(data[i]));
					 //}
				 }
			}else{
				 if(header[i].contains("Plan") ){
					timeData.add(Double.parseDouble(data[i]));
				   // dataLine.add(Double.parseDouble(cummulative[i]));
				}
				 else if(header[i].contains("Planned")&& "TRADHMEMBERS".equals(type) ){
						timeData.add(Double.parseDouble(data[i]));
					   // dataLine.add(Double.parseDouble(cummulative[i]));
				}
				else if(header[i].contains("Attended") && "TRADHMEMBERS".equals(type)){
					barDataList.add(Double.parseDouble(data[i]));
					//lineYChartList.add(Double.parseDouble(cummulative[i]));    Attended
				}
				else if(header[i].contains("Completed")){
					barDataList.add(Double.parseDouble(data[i]));
					//lineYChartList.add(Double.parseDouble(cummulative[i]));    Attended
				}
				else if(header[i].contains("Adherence") ){
					AdherenceDataList.add(Double.parseDouble(data[i]));
					//lineYChartList.add(Double.parseDouble(cummulative[i]));
				}
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(UIUtils.isValidKeyId(custype)){
			if( complaintDataList.size() >=0 && "ComplaintGallery".equals(custype)){
				complaintChart.setData(complaintDataList);
				complaintChart.setType(ChartTypes.SPLINE);
				complaintChart.setName("Complaint Gallery");
				subTitle=data[2];
				ChartYAxis yAxis = new ChartYAxis(); 
				yAxis.setMin(0);
				//if("ComplaintGallery".equals(custype))
					//yAxis.getTitle().setText("Complaint Gallery");
		
				//chartYAxis.add(yAxis);
		        chartSeriesList.add(complaintChart);
			}
			if( crmDataList.size() >=0 && "CRM".equals(custype)){
				crmChart.setData(crmDataList);
				crmChart.setType(ChartTypes.SPLINE);
				crmChart.setName("Customer Complaints");
				subTitle="Customer Complaints";
				ChartYAxis yAxis = new ChartYAxis(); 
				yAxis.setMin(0);
		        chartSeriesList.add(crmChart);
				
		        //if("CRM".equals(custype))
					//yAxis.getTitle().setText("Customer Complaints");

		        //chartYAxis.add(yAxis);
		    
			}
			}else {
					if(timeData.size() > 0 ){
						timeSeries.setData(timeData);
						timeSeries.setType(ChartTypes.LINE);
						if("TRADHMEMBERS".equals(type) ){
						    timeSeries.setName("Planned");
						    subTitle=data[2];         //  3 to 2                              
						}else
							timeSeries.setName("Plan");
						
						chartSeriesList.add(timeSeries);
						ChartYAxis yAxis = new ChartYAxis(); 
						yAxis.setMin(0);
						if(UIUtils.isValidKeyId(type)){
							yAxis.getTitle().setText("Adherence Percentage");
							//subTitle="Adherence Percentage";
						}
						else{
						    yAxis.getTitle().setText("Plan Vs Complete");
						    subTitle=data[2]; //  3 to 2
						}
						chartYAxis.add(yAxis);
					}
					
					if( barDataList.size() > 0){
						barChart.setData(barDataList);
						barChart.setType(ChartTypes.LINE);
						 if("TRADHMEMBERS".equals(type)){
						  barChart.setName("Attended");
						  subTitle=data[2];  // 3 to 2
						 }else
						  barChart.setName("Completed");
						chartSeriesList.add(barChart);
					}
					
					if( AdherenceDataList.size() > 0){
						adhChart.setData(AdherenceDataList);
						adhChart.setType(ChartTypes.LINE);
						adhChart.setName("Adherence");
						chartSeriesList.add(adhChart);
					}
			}
		ChartXAxis xaxis = new ChartXAxis();
		if(UIUtils.isValidKeyId( commonFilter.getMonwise()) && commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		
		
		CommonMessage.debugMsg(" title "+title+" subTitle "+subTitle+" chartYAxis "+chartYAxis);
		
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
	}

private JSONObject processBarChart(List<String[]> trendList,CommonFilter commonFilter,String rowId, String type){
		
		if( trendList == null || trendList.size() <= 1  )
			return null;
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		new ChartSeries();
		
		CommonMessage.debugMsg(" Checking Here For Type "+type);
		
		String title =null;
		if(UIUtils.isValidKeyId(type)){
			 if("CUSCOMPNTRPT".equals(type))
				 title = "Customer Complaint";
			 else if("TRADHRPT".equals(type))
				  title = "Training Adherence Percentage";
		}else
		    title = "Training Program Plan Vs Complete";
		
		String [] header =  trendList.get(2);  // 3 to 2
		String [] month =  trendList.get(1); // 2 to 1
		//int row = Integer.parseInt(rowId)+2; 
		String [] data =  trendList.get(3);  // 4 to 3
		String prevMonth = null;
		String subTitle = data[2]; //  3 to 2
		ChartSeries timeSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		ChartSeries barChart = new ChartSeries();
		List<Double> barDataList = new ArrayList<Double>();
		new ArrayList<Double>();
		new ChartSeries();
		new ArrayList<Double>();
		commonFilter.setMonwise("Y");
		for( int i = 3;i < header.length;i++ ) // 4 to 3
		{
			if(header[i].contains("Plan") )
			{
				timeData.add(Double.parseDouble(data[i]));		 
			}
			else if(header[i].contains("Completed") )
			{
				barDataList.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		
				if(timeData.size() > 0 ){
					timeSeries.setData(timeData);
					timeSeries.setType(ChartTypes.COLUMN);
					
				    timeSeries.setName("Plan");
					chartSeriesList.add(timeSeries);
					
					
					ChartYAxis yAxis = new ChartYAxis(); 
					yAxis.setMin(0);
					//yAxis.getTitle().setText("Plan Vs Complete");
					yAxis.getTitle().setText("No Of Trainings");
					chartYAxis.add(yAxis);
				}
				if( barDataList.size() > 0){
					barChart.setData(barDataList);
					barChart.setType(ChartTypes.COLUMN);
					barChart.setName("Completed");
					
					chartSeriesList.add(barChart);
				}
		ChartXAxis xaxis = new ChartXAxis();
		
		CommonMessage.debugMsg(commonFilter.getMonwise() + " monthwise");
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
	}

	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			CommonMessage.debugMsg("inside else");
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getTraning(request, commonFilter);
			/* if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
				  commonFilter.setdFromDate(CommonFunctions.getFirstDateofMonth(0));
				  CommonMessage.debugMsg("INSIDE POPCOMMON FILTER");
				  commonFilter.setToDate(CommonFunctions.getDate());
			 	}*/
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  		 
		 	  }
			
			commonFilter.setMonwise("Y");
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
	private JSONObject getTableModelGraph(List<String[]> headers,String caption) {
		CommonMessage.debugMsg("Enter get col model...."+caption);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		
		String[] colHeader = headers.get(1);
		String[] colHeader1 = headers.get(2);
		colHeader[3] = "DATE";
		if(caption.equals("Factory")){
			caption="PBU";
		}else if(caption.equals("Section")){
			caption="DMT";
		}else if(caption.equals("Line")){
			caption="JH";
		}
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		CommonMessage.debugMsg("test..."+colHeader.length);
		for (int i = 2; i <colHeader.length; i++) {
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		colHeader1[3] = caption;
		colHeader[3] = caption;
		String headerSql = "'SELECT ";
		for (int i = 0; i <= colHeader.length-1; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i == 0 || i == 1 || i==2) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			} else if (i > 3) {
				// jqGridColModel.setHidden(false);
				jqGridColModel.setKey(true);
				jqGridColModel.setAlign("right");
				jqGridColModel.setWidth(70);
			}

			else if (i == 16) {
				CommonMessage.debugMsg("Length of col:" + colHeader.length);
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
			
			//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL' ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		tableModel.set("tableHeight", "67%%");
		
		CommonMessage.debugMsg("colmodel:" + tableModel);
		return tableModel;
	}
	private JSONObject getTableModel(List<String[]> headers,String caption) {
		CommonMessage.debugMsg("Enter get col model...."+caption);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		
		String[] colHeader = headers.get(0);
		String[] colHeader1 = headers.get(1);
		colHeader[2] = "DATE"; //  3 to 2
		if(caption.equals("Factory")){
			caption="PBU";
		}else if(caption.equals("Section")){
			caption="DMT";
		}else if(caption.equals("Line")){
			caption="JH";
		}
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		CommonMessage.debugMsg("test..."+colHeader.length);
		for (int i = 2; i <colHeader.length; i++) {
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		colHeader1[2] = caption;   //  3 to 2
		String headerSql = "'SELECT ";
		for (int i = 0; i <= colHeader.length-1; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i == 0 || i == 1 ) { // removing i ==2
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			} else if (i >= 3) { //  making >= instead of >
				// jqGridColModel.setHidden(false);
				jqGridColModel.setKey(true);
				jqGridColModel.setAlign("right");
				jqGridColModel.setWidth(70);
			}

			else if (i == 16) {
				CommonMessage.debugMsg("Length of col:" + colHeader.length);
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
			
			//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL' ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		tableModel.set("tableHeight", "67%%");
		
		CommonMessage.debugMsg("colmodel:" + tableModel);
		return tableModel;
	}

	private void processChartETNosPerPerson(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =null;
		
		String forDashboard = request.getParameter("dashboard");
		String graphType = request.getParameter("graphType");
		String flid = request.getParameter("flid");
		String type=request.getParameter("type");

		CommonMessage.debugMsg(commonFilterIden+" forDashboard.....forDashboard "+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter)httpSession.getAttribute(commonFilterIden);
		}
		else{
			commonFilter = populateCommonFilter(request,commonFilterIden,true);
		}

		CommonMessage.debugMsg(commonFilter.getFlid()+" commonFilter.getFlid()  "+commonFilter.getFlid());
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		if (UIUtils.isValidKeyId(flid)) {
			chartCommonFilter.setFlid(flid);
			chartCommonFilter.setRowTotal('N');
		}
		else
			chartCommonFilter.setRowTotal('Y');
		String FirstLevel = request.getParameter("FirstLevel");
		if (UIUtils.isValidKeyId(FirstLevel)) 
			chartCommonFilter.setFirstLevel(FirstLevel);

		
		if(UIUtils.isValidKeyId(type)){
			commonFilter.setAbnViewType(type);
			chartCommonFilter.setAbnViewType(type);
		}
		

		FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		commonFilter.setType(graphType);
		chartCommonFilter.setType(graphType);
		
		
		//commonFilter.setDrillFlag('f');
		List<String[]> improvementVsCompletedList  = newETReportService.getAllNosPerPerson(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(improvementVsCompletedList != null && improvementVsCompletedList.size() > 0)
		{	chartObj = processLineChartIncident1(improvementVsCompletedList,chartCommonFilter,type);
			UIUtils.dashBoardSetChartObject(request,chartObj);
			CommonMessage.debugMsg("chartObj ="+chartObj);
			PrintWriter out = response.getWriter();
			out.print(chartObj);
			out.close();
		}
	}

	private void processChartEThrsPerPerson(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =null;
		
		String forDashboard = request.getParameter("dashboard");
		String graphType = request.getParameter("graphType");
		String flid = request.getParameter("flid");
		String type=request.getParameter("type");

		CommonMessage.debugMsg(commonFilterIden+" forDashboard.....forDashboard "+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter)httpSession.getAttribute(commonFilterIden);
		}
		else{
			commonFilter = populateCommonFilter(request,commonFilterIden,true);
		}

		CommonMessage.debugMsg(commonFilter.getFlid()+" commonFilter.getFlid()  "+commonFilter.getFlid());
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		if (UIUtils.isValidKeyId(flid)) {
			chartCommonFilter.setFlid(flid);
			chartCommonFilter.setRowTotal('N');
		}
		else
			chartCommonFilter.setRowTotal('Y');
		String FirstLevel = request.getParameter("FirstLevel");
		if (UIUtils.isValidKeyId(FirstLevel)) 
			chartCommonFilter.setFirstLevel(FirstLevel);

		
		if(UIUtils.isValidKeyId(type)){
			commonFilter.setAbnViewType(type);
			chartCommonFilter.setAbnViewType(type);
		}
		

		FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		commonFilter.setType(graphType);
		chartCommonFilter.setType(graphType);
		
		
		//commonFilter.setDrillFlag('f');
		List<String[]> improvementVsCompletedList  = newETReportService.getAllPerPerson(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(improvementVsCompletedList != null && improvementVsCompletedList.size() > 0)
		{	chartObj = processLineChartIncident1(improvementVsCompletedList,chartCommonFilter,type);
			UIUtils.dashBoardSetChartObject(request,chartObj);
			CommonMessage.debugMsg("chartObj ="+chartObj);
			PrintWriter out = response.getWriter();
			out.print(chartObj);
			out.close();
		}
	}
	
	private JSONObject processLineChartIncident1(List<String[]> improvementVsCompletedList,CommonFilter commonFilter,String type){
		if( improvementVsCompletedList == null || improvementVsCompletedList.size() <= 2  )
			return null;
		//chartCommonFilter.setAbnViewType(type);
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String title=null;
		
		CommonMessage.debugMsg(" Inside Checking :: type"+type);
		 
        StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());	
		
		if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
			title= "Training Hours Per Person Per Month";
		else
			title= "Training Nos Per Person Per Month"+"-"+date;
		
		int index=0;
		if(improvementVsCompletedList!=null)
			index=improvementVsCompletedList.size()-1;
	
		String[] header =  improvementVsCompletedList.get(1);  // vignesh chnaging 
		String[] month =  improvementVsCompletedList.get(0);  // vignesh chnaging 
		String[] data =  improvementVsCompletedList.get(2);  //  vignesh chnaging 
		String prevMonth = null;
		
		String subTitle = data[2]; //  vignesh changing
		CommonMessage.debugMsg("subTitle "+subTitle );
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();
		
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();
		
		for( int i = 4;i < header.length;i++ ){
			if(header[i].contains("Implemented") ){
				identifiedData.add(Double.parseDouble(data[i]));
			}else if(header[i].contains("Trai") && UIUtils.isValidKeyId(commonFilter.getAbnViewType())){ 
				identifiedData.add(Double.parseDouble(data[i]));
			}
			else if(header[i].contains("COMPLETED") ){
				completedData.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		
		}
		
		
		if(identifiedData.size() > 0 )
		{
			timeSeries.setData(identifiedData);
			if (commonFilter.getType().equals("COLUMN"))
				timeSeries.setType(ChartTypes.COLUMN);
			else
				timeSeries.setType(ChartTypes.SPLINE);
		 	 
			if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
			{
				timeSeries.setName("Training hours per person per month");
				chartSeriesList.add(timeSeries);
			}
			else{
				timeSeries.setName("Training Nos per person per month");
				chartSeriesList.add(timeSeries);
			}
			
				
			//yAxis.getTitle().setText("Implemented");
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
			yAxis.getTitle().setText("Training hours per Person per Month");
			else
			yAxis.getTitle().setText("Numbers");	
			chartYAxis.add(yAxis);
		}
		if( completedData.size() > 0){
			intstanceSeries.setData(completedData);
			if (commonFilter.getType().equals("BAR"))
				timeSeries.setType(ChartTypes.BAR);
			else
				timeSeries.setType(ChartTypes.SPLINE);
			intstanceSeries.setName("Completed");
			chartSeriesList.add(intstanceSeries);			
			//ChartYAxis yAxis = new ChartYAxis(); 			
			/*if( chartYAxis.size() > 0)
			{
				yAxis.setOpposite(true);
				intstanceSeries.setyAxis(1);
			}*/
			
			//chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
		
	}

	
}