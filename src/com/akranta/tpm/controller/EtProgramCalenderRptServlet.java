
package com.akranta.tpm.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.taglibs.standard.lang.jpath.expression.CeilingFunction;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartRadar;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.ExcelInsertImage;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.ETProgCalenderRptService;
import com.akranta.tpm.service.EmployeeService;
//import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.ETProgCalenderRptServiceImpl;
import com.akranta.tpm.service.impl.EmployeeServiceImpl;
//import com.akranta.tpm.service.impl.GenVwToolcategoryServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
//import com.akranta.tpm.service.impl.PlmTlEqpgrpstandardServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class EtProgramCalenderRptServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//CommonFilter commonFilter;
	ETProgCalenderRptService eTProgCalenderRptService;
	EmployeeService employeeService;
	DashboardService dashboardService;
	public EtProgramCalenderRptServlet() throws Exception
	{
		super();
		
/*		//commonFilter=new CommonFilter();
		eTProgCalenderRptService = new ETProgCalenderRptServiceImpl();
		employeeService = new EmployeeServiceImpl();
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
		
		HttpSession httpSession = request.getSession(false);
		
		String action = UIUtils.getActionPart(request);
		try {
			eTProgCalenderRptService = (ETProgCalenderRptServiceImpl)UIUtils.getServiceObject(request,"ETProgCalenderRptServiceImpl");
			employeeService = (EmployeeServiceImpl)UIUtils.getServiceObject(request,"EmployeeServiceImpl");
		dashboardService=(DashboardServiceImpl)UIUtils.getServiceObject(request, "DashboardServiceImpl");
		eTProgCalenderRptService.ETProgCalenderRptServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		String dispatchUrl = null;
			//Et Program Calender REPORT
			 /*if( action.equals("filterXmlETPCrptRpt_input.ETPCrpt")){
				response.setContentType("xml"); 
				CommonMessage.debugMsg("action "+ action); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/ETPCrptRpt.xml") ;
			}*/
			if( action.equals("filterXmlETPCrptRpt_input.ETPCrpt")){
				 response.setContentType("xml"); 			
				 UIUtils.forwardRequest(request, response, "/tiles/xml/TrainingCalenderRpt.xml") ;
			}
			else if(action.equals("ETPCrptRpt_input.ETPCrpt"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/ETProgramCalenderRpt.jsp");
				rd.forward(request, response); 
			}
			
			else if(action.equals("ETPCrptRpt_getCol.ETPCrpt"))
			{	
				/*CommonMessage.debugMsg("ETPCrptRpt_getCol.abnRpt");
				PrintWriter out = response.getWriter();
				String ColModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelETPCrptRpt");
				CommonMessage.debugMsg("ColModel:::::::"+ColModel);
				
				httpSession.removeAttribute("eTProgCalenderRptColModel");
				httpSession.setAttribute("eTProgCalenderRptColModel", ColModel);
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelETPCrptRpt"));*/
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"ETProgCalenderRptCommonFilter",true);
				
				List<String[]> abnormality  =  eTProgCalenderRptService.getTrnPrgCal(commonFilter);	
				JSONObject abnData = UIUtils.convertToJqGridTableObject(abnormality,request,-1,0,commonFilter.getTotalRecordCnt()); 
				CommonMessage.debugMsg(abnData);
				httpSession.setAttribute("abnReportServletdata", abnData);
				JSONObject jsonObject = getTableModel(abnormality,commonFilter);
				jsonObject.put("isGroupBy", "false");
				jsonObject.put("groupByField", false);
				jsonObject.put("rowNumbers", true);
				jsonObject.put("groupSummary", false);
				jsonObject.put("tableButton", true);
				jsonObject.set("tableHeight", "90%%");
				
				httpSession.removeAttribute("abnReportColModel");
				httpSession.setAttribute("abnReportColModel", jsonObject);
				out.println(jsonObject);
											
			}
			else if( action.equals("ETPCrptRpt_getData.ETPCrpt") )
			{PrintWriter out = response.getWriter();
			try
			{	
				 UIUtils.displayRequestParamsValue(request);
				
				 request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"ETProgCalenderRptCommonFilter",true);				 
				 JSONObject jsonObject = new JSONObject();				 
				
				CommonMessage.debugMsg(" getData");				
				List< String[]> ppMatMonList  = eTProgCalenderRptService.getTrnPrgCal(commonFilter);	
				
				// if(page.equals("1"))
	        		 //jsonObject = (JSONObject) httpSession.getAttribute("abnReportServletdata");
	        	// else
	        	 //{				
	        		 
				// if(ppMatMonList.size() > 2 )				 
				 jsonObject = UIUtils.convertToJqGridTableObject(ppMatMonList,request,1,0, commonFilter.getTotalRecordCnt());	        	 
	        	 //}
				 out.println(jsonObject);	
				 commonFilter.setViewClick('Y');					 	
				 httpSession.removeAttribute("ETProgCalenderRptCommonFilter");				 
				 httpSession.setAttribute("ETProgCalenderRptCommonFilter", commonFilter);				 
				
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
		else if( action.equals("ETPCrptRpt_getExcel.ETPCrpt")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"ETProgCalenderRptCommonFilter",false);
			//commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = (String)httpSession.getAttribute("abnReportColModel");
			
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelETPCrptRpt");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("abnReportColModel");
			//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			tblJSONObj.put("title", "Training Calendar Report");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = eTProgCalenderRptService.etTrnPrgCalReportExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "ETProgramCalenderReport", format);
			
		}
			 
//**********************Before After Skill Analysis*****************
				else if( action.equals("filterXmlETBASArpt_input.ETBASArpt")){
				 response.setContentType("xml"); 			
				 UIUtils.forwardRequest(request, response, "/tiles/xml/RptTrainingLog.xml") ;
				}
				else if(action.equals("ETBASArpt_input.ETBASArpt"))
				{
					RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/ETBASkillAnalysisRpt.jsp");
					rd.forward(request, response); 
				}
				
				else if(action.equals("ETBASArpt_getCol.ETBASArpt"))
				{	
					
					PrintWriter out = response.getWriter();
					String ColModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelETBASArpt");
					
					httpSession.removeAttribute("eTSkillAnalysisRptColModel");
					httpSession.setAttribute("eTSkillAnalysisRptColModel", ColModel);
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelETBASArpt"));
					
				}
				else if( action.equals("ETBASArpt_getData.ETBASArpt") )
				{PrintWriter out = response.getWriter();
				try
				{	
					 UIUtils.displayRequestParamsValue(request);
					
					 request.getParameter("page");	  
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilterforbeforeAfter(request,"ETSkillAnalysisRptCommonFilter",true);				 
					 JSONObject jsonObject = new JSONObject();				 
					
						
					List< String[]> ppMatMonList  = eTProgCalenderRptService.getSkillAnalysis(commonFilter);	
					
					// if(page.equals("1"))
		        		 //jsonObject = (JSONObject) httpSession.getAttribute("abnReportServletdata");
		        	// else
		        	 //{				
		        		 
					// if(ppMatMonList.size() > 2 )				 
					 jsonObject = UIUtils.convertToJqGridTableObject(ppMatMonList,request,0,0, commonFilter.getTotalRecordCnt());	        	 
		        	 //}
					 out.println(jsonObject);	
					 commonFilter.setViewClick('Y');					 	
					 httpSession.removeAttribute("ETSkillAnalysisRptCommonFilter");				 
					 httpSession.setAttribute("ETSkillAnalysisRptCommonFilter", commonFilter);				 
					
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
				else if( action.equals("ETBASArpt_getExcel.ETBASArpt")){
					
					//HttpSession httpSession = request.getSession(false);
					CommonFilter commonFilter = populateCommonFilter(request,"ETSkillAnalysisRptCommonFilter",false);
					//commonFilter.setViewClick('Y');
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					//String tableModel = (String)httpSession.getAttribute("abnReportColModel");
					
					String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelETBASArpt");
					//JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("abnReportColModel");
					JSONObject tblJSONObj = JSONObject.fromString(tableModel);
					tblJSONObj.put("title","ETBeforeAfterSkillAnalysisReport");
					String format = ExcelUtils.getFormat(request);
					
					Workbook wb = eTProgCalenderRptService.etSkillAnalysisReportExportExcel(commonFilter,tblJSONObj,format);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb, "ET Before After Skill Analysis Report", format);
					
				}
			 
		//**************Plan Vs Completed *************
			if( action.equals("filterXmlETplanVScomp_input.ETPlCompRPT")){
				 response.setContentType("xml"); 			
				 UIUtils.forwardRequest(request, response, "/tiles/xml/EtPlanVsComp.xml") ;
			}
				else if(action.equals("ETplanVScomp_input.ETPlCompRPT")||action.equals("Trainingadherencereport_input.ETPlCompRPT")||action.equals("CustomerComplaintreport_input.ETPlCompRPT"))
				{
					String type=request.getParameter("type");
					request.setAttribute("type",type);
					request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
					RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/ETplanVScompRpt.jsp");
					rd.forward(request, response); 
				}
				
				else if(action.equals("ETplanVScomp_getCol.ETPlCompRPT")||action.equals("Trainingadherencereport_getCol.ETPlCompRPT")||action.equals("CustomerComplaintreport_getCol.ETPlCompRPT"))
				{	
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
			      	
					List<String[]> abnormality  =  eTProgCalenderRptService.getPlanVsCompCal(commonFilter,custmrtype);	
					String fromMonth = commonFilter.getFromMonth();
					String toMonth = commonFilter.getToMonth();
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
					CommonMessage.debugMsg("**************print***************");
					for (String[] arr : abnormality) {
					    CommonMessage.debugMsg(Arrays.toString(arr));
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
					httpSession.setAttribute("fromMonth", fromMonth);
					httpSession.setAttribute("toMonth", toMonth);
					out.println(jsonObject);
					/*JSONObject jsonObject = getTableModel1(abnormality,commonFilter);
					jsonObject.put("isGroupBy", "false");
					jsonObject.put("groupByField", false);
					jsonObject.put("rowNumbers", true);
					jsonObject.put("groupSummary", false);
					jsonObject.put("tableButton", true);
					jsonObject.set("tableHeight", "66.5%%");
					jsonObject.set("tableWidth","107%%");
					
					httpSession.removeAttribute("ETplanVScompColModel");
					httpSession.setAttribute("ETplanVScompModel", jsonObject);
					out.println(jsonObject);*/
					
				}
				else if( action.equals("ETplanVScomp_getData.ETPlCompRPT")||action.equals("Trainingadherencereport_getData.ETPlCompRPT")||action.equals("CustomerComplaintreport_getData.ETPlCompRPT") )
				{PrintWriter out = response.getWriter();
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
					 String fromMonth =(String) httpSession.getAttribute("fromMonth");
					 String toMonth =(String) httpSession.getAttribute("toMonth");
					 commonFilter.setFromMonth(fromMonth);
					 commonFilter.setToMonth(toMonth);
					 List< String[]> ppMatMonList  = eTProgCalenderRptService.getPlanVsCompCal(commonFilter,custmrtype);	
					 for (String[] arr : ppMatMonList) {
						    CommonMessage.debugMsg(Arrays.toString(arr));
						}
					 //if(ppMatMonList.size() > 4 )	
					 int strt= 3;
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
				
				else if(action.equals("ETplanVScomp_getExcel.ETPlCompRPT")||action.equals("Trainingadherencereport_getExcel.ETPlCompRPT")||action.equals("CustomerComplaintreport_getExcel.ETPlCompRPT")){
					
					httpSession = request.getSession(false);                  
					CommonFilter commonFilter = populateCommonFilter(request,"ETplanVScompCommonFilter",false);
					String type=commonFilter.getType();
					 String custmrtype=request.getParameter("custmrtype");
					CommonMessage.debugMsg("custmrtype "+custmrtype);
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
					Workbook wb = eTProgCalenderRptService.etPlanVsCompReportExportExcel(commonFilter,tblJSONObj,format,custmrtype);
					ExcelUtils.writeToResponse(response, wb, "ETPlanVsCompleteReport", format);
					
				}
			
				/*else if(action.equals("Trainingadherencereport_getExcel.ETPlCompRPT")||action.equals("CustomerComplaintreport_getExcel.ETPlCompRPT")){
					
					
				}*/
			
				else if( action.equals("ETplanVScompchart.ETPlCompRPT")||action.equals("dashBoardBarChart.ETPlCompRPT")){
					processChart(request,response);
				}
				else if( action.equals("ETplanVScomplinegrapgh.ETPlCompRPT")||action.equals("dashBoard.ETPlCompRPT")){
					processChart(request,response);
				}
			 /*Program Calender Plan Vs Actual*/
				else if(action.equals("filterXmlprogCalplanVSAct_input.ETPlCompRPT"))
				{
					UIUtils.forwardRequest(request, response, "/tiles/xml/trainingempplanvsactual.xml");
				}
				else if(action.equals("progCalplanVSAct_input.ETPlCompRPT"))
				{
					request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
					RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/ENTProgCalplanVSAct.jsp");
					rd.forward(request, response); 
				}
				else if(action.equals("progCalplanVSAct_getCol.ETPlCompRPT"))
				{	
					PrintWriter out = response.getWriter();
					CommonFilter commonFilter = populateCommonFilter(request,"progCalplanVSActCommonFilter",true);
					
					List<String[]> abnormality  =  eTProgCalenderRptService.getProgCalPlanVsCompCal(commonFilter);	
					JSONObject abnData = UIUtils.convertToJqGridTableObject(abnormality,request,2,0,commonFilter.getTotalRecordCnt()); 
					
					httpSession.setAttribute("ETplanVScompServletdata", abnData);
					JSONObject jsonObject = getTableModelProgCal(abnormality,commonFilter);
					jsonObject.put("isGroupBy", "false");
					jsonObject.put("groupByField", false);
					jsonObject.put("rowNumbers", true);
					jsonObject.put("groupSummary", false);
					jsonObject.put("tableButton", true);
					jsonObject.set("tableHeight", "75%%");
					
					httpSession.removeAttribute("progCalplanVSActColModel");
					httpSession.setAttribute("progCalplanVSActColModel", jsonObject);
					out.println(jsonObject);
					
				}
				else if( action.equals("progCalplanVSAct_getData.ETPlCompRPT") )
				{PrintWriter out = response.getWriter();
				try
				{	
					 UIUtils.displayRequestParamsValue(request);
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"progCalplanVSActRptCommonFilter",true);				 
					 JSONObject jsonObject = new JSONObject();				 
					
					 List< String[]> progCalplanVSAct  =  eTProgCalenderRptService.getProgCalPlanVsCompCal(commonFilter);		
					 jsonObject = UIUtils.convertToJqGridTableObject(progCalplanVSAct,request,2,0,commonFilter.getTotalRecordCnt());	        	 
					 out.println(jsonObject);	
					 commonFilter.setViewClick('N');					 	
					 httpSession.removeAttribute("progCalplanVSActRptCommonFilter");				 
					 httpSession.setAttribute("progCalplanVSActRptCommonFilter", commonFilter);				 
					
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
				else if( action.equals("progCalplanVSAct_getExcel.ETPlCompRPT")){
					
					//HttpSession httpSession = request.getSession(false);
					CommonFilter commonFilter = populateCommonFilter(request,"progCalplanVSActRptCommonFilter",false);
					//commonFilter.setViewClick('Y');
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					//String tableModel = (String)httpSession.getAttribute("abnReportColModel");
					
					//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelETPCrptRpt");
					JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("progCalplanVSActColModel");
					//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
					tblJSONObj.put("title", "Program Calendar Plan Vs Actual");
					String format = ExcelUtils.getFormat(request);
					
					Workbook wb = eTProgCalenderRptService.ProgCalPlanVsActReportExportExcel(commonFilter,tblJSONObj,format);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb, "ProgramCalendarPlanVsActual", format);
					
				}
				else if( action.equals("ETplanVScompchart.ETPlCompRPT")){
					processChart(request,response);
				}
				else if( action.equals("ProgCalplanVSActualchart.ETPlCompRPT")){
					processChartProgCal(request,response);
				}
			/* Before And After Skill Analysis Duration Report */
				else if(action.equals("BefAftSkill_input.ETPlCompRPT"))
				{
					request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
					RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/EntBeforeAftSkillAnalysis.jsp");
					rd.forward(request, response); 
				}
				else if(action.equals("BefAftSkill_getCol.ETPlCompRPT"))
				{	
					
					PrintWriter out = response.getWriter();
					String ColModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelBefAftSkill");
					
					httpSession.removeAttribute("eTBefAftSkillColModel");
					httpSession.setAttribute("eTBefAftSkillColModel", ColModel);
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelBefAftSkill"));
					
				}
				else if( action.equals("BefAftSkill_getData.ETPlCompRPT") )
				{
					PrintWriter out = response.getWriter();
				try
				{	
					 UIUtils.displayRequestParamsValue(request);
					
					 request.getParameter("page");	  
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilterforbeforeAfter(request,"ETBefAftSkillCommonFilter",true);				 
					 JSONObject jsonObject = new JSONObject();				 
				
					 if( Constants.passNullDate.contains(commonFilter.getBefFromDt()) ){
						
						  commonFilter.setBefFromDt(CommonFunctions.getDate());
						  commonFilter.setBefToDt(CommonFunctions.getDate());
					 }	
					 if( Constants.passNullDate.contains(commonFilter.getAftFromDt()) ){
						
						  commonFilter.setAftFromDt( commonFilter.getBefToDt());
						  commonFilter.setAftToDt(CommonFunctions.getDate());
					 }	
					 List< String[]> befAftSkillList  = eTProgCalenderRptService.getBefAftSkill(commonFilter,"");	
					 jsonObject = UIUtils.convertToJqGridTableObject(befAftSkillList,request,0,1, commonFilter.getTotalRecordCnt());	        	 
		        	 
					 out.println(jsonObject);	
					 commonFilter.setViewClick('Y');					 	
					 httpSession.removeAttribute("ETBefAftSkillCommonFilter");				 
					 httpSession.setAttribute("ETBefAftSkillCommonFilter", commonFilter);				 
					
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
				else if (action.equals("BefAftSkillRadarChartPage_input.ETPlCompRPT"))
				{
					String evalAftDate=request.getParameter("evalAftDate");
					String evalBfrDate=request.getParameter("evalBfrDate");
					String empId=request.getParameter("empId");
					String fromSpoke=request.getParameter("isSpoke");
					
					request.removeAttribute("evalBfrDate");
					if(UIUtils.isValidKeyId(evalBfrDate))
						request.setAttribute("evalBfrDate", evalBfrDate);
					httpSession.removeAttribute("evalAftDate");
					httpSession.setAttribute("evalAftDate", evalAftDate);
					httpSession.removeAttribute("empId");
					httpSession.setAttribute("empId",empId);
					httpSession.removeAttribute("fromSpoke");
					httpSession.setAttribute("fromSpoke", fromSpoke);
					if(UIUtils.isValidKeyId(empId))
						request.setAttribute("empId", empId);
					if(UIUtils.isValidKeyId(evalAftDate))
						request.setAttribute("evalAftDate", evalAftDate);
					
					dispatchUrl="/pages/ENT/BefAftRadarChart.jsp";
				}
				else if(action.equals("BefAftSkillRadarChart_input.ETPlCompRPT"))
				{
					CommonMessage.debugMsg("BefAftSkillRadarChart_input.ETPlCompRPT");
					PrintWriter out = response.getWriter();
					
					String empId = request.getParameter("empId");
					
					List<String[]> radarChartList=null;
					CommonFilter commonFilter = populateCommonFilterforbeforeAfter(request,"ETBefAftSkillCommonFilter",false);	
					radarChartList=eTProgCalenderRptService.getBefAftSkill(commonFilter,empId);	
					AdmTlUsermst user = UIUtils.getLoginUser(request);
					String userName=user.getUsrm_username();
					CommonMessage.debugMsg("radarChartList.get(0)[5]"+radarChartList.get(0)[5]);
					CommonMessage.debugMsg("radarChartList.get(0)[6]"+radarChartList.get(0)[6]);
					String Data="[{\"evalAftDate\""+":"+"\""+radarChartList.get(0)[6]+"\",";
					Data+="\"empBefDate\""+":"+"\""+radarChartList.get(0)[5]+"\",";
				    Data+="\"empKeyID\""+":"+"\""+radarChartList.get(0)[1]+"\",";
				    Data+="\"parentNames\""+":"+"\""+radarChartList.get(0)[2]+"\",";
				    Data+="\"empName\""+":"+"\""+radarChartList.get(0)[4]+"\",";
				    Data+="\"preparedBy\""+":"+"\""+userName+"\",";
				    Data+="\"role\""+":"+"\""+radarChartList.get(0)[3]+"\",";
				    
					GenTlEmployeeimg genTlEmployeeimg = new GenTlEmployeeimg();
					  
					  
					genTlEmployeeimg.setEmpiEmployeeid(radarChartList.get(0)[1]);
					String filePath = UIUtils.getImagePath(request);
					genTlEmployeeimg.setEmpiBlobimage(filePath);
					genTlEmployeeimg.setEmpiFilename(UIUtils.TPM_TEMPIMG_DIR);	
					 
			  		genTlEmployeeimg =employeeService.getLayoutImg(genTlEmployeeimg);
			  		String fileName=null;
			  		if(genTlEmployeeimg==null)
			  		{
			  			 fileName="images/EmpDefaultImg.jpg";
			  		}
			  		else
			  		{
			  			 fileName=genTlEmployeeimg.getEmpiFilename();
				  		
			  		}
			  		Data+="\"imageName\""+":"+"\""+fileName+"\"}]";
			  		JSONObject jsonobject=new JSONObject();
					jsonobject.put("Data",Data);
					CommonMessage.debugMsg("jsonobject"+jsonobject);
					out.println(jsonobject);
					
				}
				else if( action.equals("BefAftSkill_getExcel.ETPlCompRPT")){
					
					//HttpSession httpSession = request.getSession(false);
					CommonFilter commonFilter = populateCommonFilter(request,"ETBefAftSkillCommonFilter",false);
					
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
				
					String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ETProgCalenderRptColModel", "colModelBefAftSkill");
					JSONObject tblJSONObj = JSONObject.fromString(tableModel);
					tblJSONObj.put("title", "Before and After Skill Analysis");
					String format = ExcelUtils.getFormat(request);
					
					Workbook wb = eTProgCalenderRptService.etBefAftReportExportExcel(commonFilter,tblJSONObj,format);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb, "BeforeAfterSkillAnalysis", format);
					
				}
				else if(action.equals("BefAftSkill_chart.ETPlCompRPT"))
				{
					String fromSpoke=(String) httpSession.getAttribute("fromSpoke");
					empBefAftSkillRadarChart(request,response,fromSpoke);
				}
				else if( action.equals("exportBefAftRadarChart.ETPlCompRPT")){
					UIUtils.displayRequestParamsValue(request);
					String chartDataStr = request.getParameter("datas");
					//CommonMessage.debugMsg("Chart Data : "+chartDataStr);						
					JSONObject jsonObject =JSONObject.fromString(chartDataStr);					
					JSONObject chartDataObj = (JSONObject) jsonObject.get("chartData");
					//CommonMessage.debugMsg("ChartData----->"+chartDataObj);
					
					String format = ExcelUtils.getFormat(request);		
					String path = UIUtils.getExcelTemplatePath(request);  			
					try {
						Workbook wb = fillBefAftRadarExcelValues(request,path,format,chartDataObj);						
						ExcelUtils.writeToResponse(response, wb, "BeforeAndAfterRadarChart",format );
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else if( action.equals("filterXmlBefAftSkill_input.ETPlCompRPT")){
					response.setContentType("xml"); 			
					UIUtils.forwardRequest(request, response, "/tiles/xml/EntBefAftSkillAnalysis.xml") ;
				}
			/* Before And After Skill Analysis Duration Report */
				if (dispatchUrl != null)
				{
				   UIUtils.forwardRequest(request, response, dispatchUrl);
				}
	}
private void empBefAftSkillRadarChart(HttpServletRequest request,HttpServletResponse response,String fromSpoke) throws IOException{
		
		
		String empId = request.getParameter("empId");
		String evalAftDate = request.getParameter("evalAftDate");
		String evalBfrDate=request.getParameter("evalBfrDate");
		PrintWriter out = response.getWriter();
		try {
			JSONObject returnData = new JSONObject();
			JSONArray convertdData = new JSONArray();
			JSONArray convertdAftData = new JSONArray();
			List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
			List<ChartSeries> chartSeriesAft = new ArrayList<ChartSeries>();
			List<String> xCategories = new ArrayList<String>();
			DecimalFormat twoDForm = new DecimalFormat("#.#");
			
			ChartSeries targetSeries = new ChartSeries();
			ChartSeries currentSeries =new ChartSeries();
			List<Double> targetRateData = new ArrayList<Double>();
			List<Double> currentRateData = new ArrayList<Double>();
			ChartSeries afttargetSeries = new ChartSeries();
			ChartSeries aftcurrentSeries =new ChartSeries();
			List<Double> afttargetRateData = new ArrayList<Double>();
			List<Double> aftcurrentRateData = new ArrayList<Double>();
			//CommonFilter commonFilter = populateCommonFilterforbeforeAfter(request,"ETBefAftSkillCommonFilter",false);	
			if(UIUtils.isValidKeyId(evalBfrDate)){
				List<String[]> empBfrList = eTProgCalenderRptService.getEmpBefAftTopicRatings(empId,evalBfrDate,fromSpoke);
				empBfrList.size();
				JSONArray.fromCollection(empBfrList);
				convertdData = JSONArray.fromCollection(empBfrList);
				for(String[] data : empBfrList ){
					xCategories.add(data[0]); //Topic Names
					if( UIUtils.isValidKeyId(data[1]) && ! data[1].isEmpty()){
						CommonMessage.debugMsg("Double.parseDouble(data[1]"+Double.parseDouble(data[1]));
						targetRateData.add(Double.valueOf(twoDForm.format(Double.parseDouble(data[1])))) ;// Target Rating
					}	
					else
						targetRateData.add(0.0);
					if( UIUtils.isValidKeyId(data[2]) && ! data[2].isEmpty()){
						CommonMessage.debugMsg("Double.parseDouble(data[2]"+Double.parseDouble(data[2]));
						currentRateData.add(Double.valueOf(twoDForm.format(Double.parseDouble(data[2])))) ;// Current Rating
					}	
					else
						currentRateData.add(0.0) ;// Current Rating
				}
			}	
			if(UIUtils.isValidKeyId(evalAftDate)){
				List<String[]> empAftList = eTProgCalenderRptService.getEmpBefAftTopicRatings(empId,evalAftDate,fromSpoke);
				convertdAftData = JSONArray.fromCollection(empAftList);
				for(String[] dataAft : empAftList ){
					xCategories.add(dataAft[0]); //Topic Names
					if( UIUtils.isValidKeyId(dataAft[1]) && ! dataAft[1].isEmpty()){
						CommonMessage.debugMsg("Double.parseDouble(dataAft[1]"+Double.parseDouble(dataAft[1]));
						afttargetRateData.add(Double.valueOf(twoDForm.format(Double.parseDouble(dataAft[1])))) ;// Target Rating
					}	
					else
						afttargetRateData.add(0.0) ;// Target Rating
						
					if( UIUtils.isValidKeyId(dataAft[2]) && ! dataAft[2].isEmpty()){
						CommonMessage.debugMsg("Double.parseDouble(dataAft[2]"+Double.parseDouble(dataAft[2]));
						aftcurrentRateData.add(Double.valueOf(twoDForm.format(Double.parseDouble(dataAft[2])))) ;// Current Rating
					}	
					else
						aftcurrentRateData.add(0.0) ;// Current Rating
				}
			}	
			returnData.put("fromSpoke",fromSpoke);
			returnData.put("fillData",convertdData);
			returnData.put("fillData",convertdAftData);
			
			
			
			
			
			
			
			
			ChartRadar empAssessRadar = new ChartRadar();
			
			
			
			
			targetSeries.setData(targetRateData);
			targetSeries.setName("Target Rating");
			targetSeries.setPointPlacement("on");
			currentSeries.setData(currentRateData);
			currentSeries.setName("Current Rating");
			currentSeries.setPointPlacement("on");
			
			
			afttargetSeries.setData(afttargetRateData);
			afttargetSeries.setName("Target Rating");
			afttargetSeries.setPointPlacement("on");
			aftcurrentSeries.setData(aftcurrentRateData);
			aftcurrentSeries.setName("Current Rating");
			aftcurrentSeries.setPointPlacement("on");
			
			chartSeries.add(targetSeries);
			chartSeries.add(currentSeries);
			
			chartSeriesAft.add(afttargetSeries);
			chartSeriesAft.add(aftcurrentSeries);
			
			
			returnData.put("chartData", empAssessRadar.drawChart(chartSeries, xCategories, "Before Skill Analysis Radar Chart"));
			returnData.put("aftchartData", empAssessRadar.drawChart(chartSeriesAft, xCategories, "After Skill Analysis Radar Chart"));
			//returnData.put("size",listSize);
			CommonMessage.debugMsg(returnData.toString());
			out.print(returnData);
			
		} catch (NoDataFoundException e) {
			out.print( "No Data Found");
		} catch (Exception e) {
			e.printStackTrace();
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
	
	String flid=CommonFunctions.getLoginFlid(request);
	String lcnname=dashboardService.Functionallocn(flid);
	
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
	
	List<String[]> trendList  = eTProgCalenderRptService.getPlanVsCompReportgraph(chartCommonFilter,Flid,Custype);
	List<String[]> cur1Grid =  transposeListArr(trendList);
	for(String[] s:cur1Grid) 
	{
		CommonMessage.debugMsg("printing "+Arrays.toString(s));
	}
	JSONObject chartObj = null;
	
	CommonMessage.debugMsg(" After Checking 12 ");
	
	if(UIUtils.isValidKeyId(chkline))
	{
		chartObj = processLinegraph(lcnname,cur1Grid,commonFilter,rowId,type,Custype);	
	}
	else
	{
		chartObj = processBarChart(lcnname,cur1Grid,commonFilter,rowId,type);
	}
	commonFilter = null;
	UIUtils.dashBoardSetChartObject(request,chartObj);
	CommonMessage.debugMsg("CHART OBJ "+chartObj);
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
	
private JSONObject processLinegraph(String titlename,List<String[]> trendList,CommonFilter commonFilter,String rowId, String type, String custype){
		
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
				  title = titlename+"-Training Adherence - Sessions";
			  else
				  title = titlename+"-Training Adherence - Members";
			}else if(UIUtils.isValidKeyId(custype)){
			    if("ComplaintGallery".equals(custype))
			    	title = titlename+"-Complaint Gallery Report";
			    else
			    	title = titlename+"-CRM Report";
			}else
		         title = titlename+"-Training Program Plan Vs Complete";
		
		String [] header =  trendList.get(3);
		String [] month =  trendList.get(2);
		CommonMessage.debugMsg("HEADER with index:");
		for (int i = 0; i < header.length; i++) {
		    CommonMessage.debugMsg("header[" + i + "] = " + header[i]);
		}

		CommonMessage.debugMsg("MONTH with index:");
		for (int i = 0; i < month.length; i++) {
		    CommonMessage.debugMsg("month[" + i + "] = " + month[i]);
		}

		//int row = Integer.parseInt(rowId)+2;
		int dats=4;
		if("ComplaintGallery".equals(custype)){
			dats=3;	
		}
		String [] data = trendList.get(dats);
		CommonMessage.debugMsg(" Printing the graph datas");
		for(String d : data) 
		{
			
			CommonMessage.debugMsg(d);
			
		}
		 
		//String [] cummulative=null;
		//cummulative =trendList.get(trendList.size()-1);
		CommonMessage.debugMsg("CUSTYPE "+custype);
		String subTitle = "";

		if ("CRM".equals(custype)) {
		    // CRM has only 3 values → use index 0 as subtitle
		    subTitle = data[0];
		} else {
		    // Other types have 4 or more values
		    if (data.length > 3) {
		        subTitle = data[3];
		    } else {
		        subTitle = data[0]; // fallback
		    }
		}
		
		
		String prevMonth = null;
		
		
		
		if("TRADHMEMBERS".equals(type)&& UIUtils.isValidKeyId(type)){
			subTitle=data[3];
		}else if("TRADHRPT".equals(type)&& UIUtils.isValidKeyId(type)){
			subTitle=data[3];
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
		
		
		int startRow = 4;
		int headr = header.length;
		if("ComplaintGallery".equals(custype) ){
			startRow = 3;
		     headr=header.length-1;
		}
		else if("CRM".equals(custype)){
			startRow =1;
			headr = header.length;
		}
		for( int i = startRow;i < headr;i++ ){

			
			if(UIUtils.isValidKeyId(custype)){
				 if("ComplaintGallery".equals(custype)){
					// if(header[i].contains("Completed") ){
					 CommonMessage.debugMsg("data"+data[i]);
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
						    subTitle=data[3];                                      
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
						    subTitle=data[3];
						}
						chartYAxis.add(yAxis);
					}
					
					if( barDataList.size() > 0){
						barChart.setData(barDataList);
						barChart.setType(ChartTypes.LINE);
						 if("TRADHMEMBERS".equals(type)){
						  barChart.setName("Attended");
						  subTitle=data[3];
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

private JSONObject processBarChart(String titlename,List<String[]> trendList,CommonFilter commonFilter,String rowId, String type){
		
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
				 title = titlename+"Customer Complaint";
			 else if("TRADHRPT".equals(type))
				  title = titlename+"Training Adherence Percentage";
		}else
		    title = titlename+"-Training Program Plan Vs Complete";
		
		String [] header =  trendList.get(3);
		String [] month =  trendList.get(2);
		//int row = Integer.parseInt(rowId)+2;
		String [] data =  trendList.get(4);
		String prevMonth = null;
		String subTitle = data[3];
		ChartSeries timeSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		ChartSeries barChart = new ChartSeries();
		List<Double> barDataList = new ArrayList<Double>();
		new ArrayList<Double>();
		new ChartSeries();
		new ArrayList<Double>();
		commonFilter.setMonwise("Y");
		for( int i = 4;i < header.length;i++ )
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
	private JSONObject getTableModel(List<String[]> headers,CommonFilter commonFilter)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);			
		jqGridTableModel.getRowHeaders().add(colHeader);
		List<Integer> rotationRows = new ArrayList<Integer>();
		rotationRows.add(2);
		jqGridTableModel.setRotationRows(rotationRows);
		for(int i =0; i < colHeader.length; i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setWidth(200);		
			//jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			if(i==0 )
			{
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}
			else if (i==1){
				jqGridColModel.setWidth(350);
			}
			else if(i>5){
				jqGridColModel.setWidth(70);
				jqGridColModel.setAlign("right");
				 if(i == 6){
					 jqGridColModel.setWidth(120);
				 }
				
				
			}else if(i==3){
				 jqGridColModel.setWidth(0); 
				 jqGridColModel.setHidden(true);
			}else if(i>3){
				jqGridColModel.setWidth(130);
				jqGridColModel.setAlign("left");
			}
			
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}


	private JSONObject getTableModel1(List<String[]> headers,CommonFilter commonFilter)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(1);
		String [] colHeader1 = headers.get(2);
		String[] emptyrow = new String[colHeader.length]; 
		for(int i =0; i < colHeader.length; i++)
		{	
				emptyrow [i] ="";
		}
				jqGridTableModel.getRowHeaders().add(emptyrow);
				jqGridTableModel.getRowHeaders().add(colHeader);
				jqGridTableModel.getRowHeaders().add(colHeader1);
				
			
				List<Integer> rotationRows = new ArrayList<Integer>();
				rotationRows.add(2);
				jqGridTableModel.setRotationRows(rotationRows);
				
				for(int i =0; i < colHeader.length; i++)
				{
					JqGridColModel jqGridColModel = new JqGridColModel();
					jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
					jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
					jqGridColModel.setWidth(200);				
					//jqGridColModel.setAlign("left");
					jqGridColModel.setEditable(false);		
					if(i == 0)
						jqGridColModel.setHidden(true);		
					else if(i==1){
						//jqGridColModel.setWidth(250);
						//jqGridColModel.setAlign("left");
						jqGridColModel.setHidden(true);
						
					}else if(i==2){
						jqGridColModel.setWidth(100);
						jqGridColModel.setAlign("left");
					}
					else if(i>3) {
						jqGridColModel.setWidth(70);
						jqGridColModel.setAlign("right");
					}
					jqGridTableModel.getColModel().add(jqGridColModel);
				}
		
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 
		 return tableModel;
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
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
	private CommonFilter populateCommonFilterforbeforeAfter(HttpServletRequest request, String beanIdentifier, boolean createNew){
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
			
			//commonFilter.setMonwise("Y");
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
	private JSONObject getTableModelProgCal(List<String[]> headers,CommonFilter commonFilter)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);
		String [] colHeader1 = headers.get(1);
		String[] emptyrow = new String[colHeader.length]; 
		for(int i =0; i < colHeader.length; i++)
		{	
				emptyrow [i] ="";
		}
				jqGridTableModel.getRowHeaders().add(emptyrow);
				jqGridTableModel.getRowHeaders().add(colHeader);
				
				jqGridTableModel.getRowHeaders().add(colHeader1);
			
			
				List<Integer> rotationRows = new ArrayList<Integer>();
				rotationRows.add(2);
				jqGridTableModel.setRotationRows(rotationRows);
				
				for(int i =0; i < colHeader.length; i++)
				{
					CommonMessage.debugMsg("Length : "+colHeader.length);
					JqGridColModel jqGridColModel = new JqGridColModel();
					jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
					jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
					jqGridColModel.setWidth(200);		
					
					//jqGridColModel.setAlign("left");
					jqGridColModel.setEditable(false);
					
					if(i == 0 || i == 1){
						jqGridColModel.setHidden(true);		
					}	
					else if(i==2){
						jqGridColModel.setWidth(250);
						jqGridColModel.setAlign("left");
						
					}else if(i>2){
						jqGridColModel.setWidth(60);
						jqGridColModel.setAlign("right");
					}
					jqGridTableModel.getColModel().add(jqGridColModel);
				}
				
				 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
				 
				 return tableModel;
			}
	
	private void processChartProgCal(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		String rowId=request.getParameter("rownum");
		CommonFilter commonFilter = populateCommonFilter(request,"progCalplanVSActRptCommonFilter",false);//Modified By Dhanalakshmi.R for DashBoard Graph on 7/3/13
		String forDashboard = request.getParameter("dashboard");
		if( ! "true".equals(forDashboard))
		{
			commonFilter = (CommonFilter) httpSession.getAttribute("progCalplanVSActRptCommonFilter");
		}
		else
		{
			commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request,commonFilter);
			FilterValues.getTraning(request, commonFilter);	
			if(!UIUtils.isValidKeyId(rowId))
				rowId="1";
		}
		if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) )
		{
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	 } 
		List<String[]> trendList  = eTProgCalenderRptService.progCalPlanVsCompReportgraph(commonFilter, rowId);
		List<String[]> cur1Grid =  transposeListArr(trendList);
		JSONObject chartObj = null;
		chartObj = processLineChartProgCal(cur1Grid,commonFilter,rowId);
		commonFilter = null;
		UIUtils.dashBoardSetChartObject(request,chartObj);
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();
			
	}

private JSONObject processLineChartProgCal(List<String[]> trendList,CommonFilter commonFilter,String rowId){
		
		if( trendList == null || trendList.size() <= 1  )
			return null;
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		new ChartSeries();
	
		
		String title = "Training Employee Plan Vs Actual";
		
		String [] header =  trendList.get(2);
		String [] month =  trendList.get(1);
		int row = Integer.parseInt(rowId)+2;
		String [] data =  trendList.get(row);
		//String [] cummulative=null;
		//cummulative =trendList.get(trendList.size()-1);
		 
		String prevMonth = null;
		
		String subTitle = data[2];
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		
		ChartSeries barChart = new ChartSeries();
		List<Double> barDataList = new ArrayList<Double>();
		
		new ArrayList<Double>();
		
		
		new ChartSeries();
		new ArrayList<Double>();
		
		for( int i = 3;i < header.length;i++ ){
			if(header[i].contains("Plan") ){
				timeData.add(Double.parseDouble(data[i]));
			   // dataLine.add(Double.parseDouble(cummulative[i]));
			}
			else if(header[i].contains("Actual") ){
				barDataList.add(Double.parseDouble(data[i]));
				//lineYChartList.add(Double.parseDouble(cummulative[i]));
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
					yAxis.getTitle().setText("Plan Vs Actual");
					chartYAxis.add(yAxis);
				}
				if( barDataList.size() > 0){
					barChart.setData(barDataList);
					barChart.setType(ChartTypes.COLUMN);
					barChart.setName("Actual");
					
					chartSeriesList.add(barChart);
				}
		ChartXAxis xaxis = new ChartXAxis();
		CommonMessage.debugMsg("commonFilter.getMonwise()"+commonFilter.getMonwise());
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());	
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
	}

	public Workbook fillBefAftRadarExcelValues(HttpServletRequest request,String path,String format,JSONObject chartObj)throws Exception
	{
		String location = request.getParameter("location");		
		String classifcn = request.getParameter("classifcn");		
		String unitHeading = request.getParameter("unitHeading");			
		String deptHeading = request.getParameter("deptHeading");		
		String unit = request.getParameter("unit");		
		String dept = request.getParameter("dept");		
		String empName = request.getParameter("empName");					
		String role = request.getParameter("role");		
		String preparedBy = request.getParameter("preparedBy");						
		String evaluationDate = request.getParameter("beforeEvaluationDate");			
		String nxtEvaluationDate = request.getParameter("afterEvaluationDate");
		String imgPath =request.getParameter("imgPath");
		
		JSONObject beforeObj = (JSONObject) chartObj.get("before");
		JSONObject afterObj = (JSONObject) chartObj.get("after");
		JSONArray beforeNames = beforeObj.getJSONArray("names");
		JSONArray afterNames = afterObj.getJSONArray("names");
		JSONArray beforeDatas = beforeObj.getJSONArray("datas");
		JSONArray afterDatas = afterObj.getJSONArray("datas");
		JSONArray beforeCatgrs = beforeObj.getJSONArray("categories");
		JSONArray afterCatgrs = afterObj.getJSONArray("categories");
	
		String excelPath = "/BEFAFTRADARCHT";
		InputStream inp = new FileInputStream(path+excelPath+"."+format); 
		//CommonMessage.debugMsg(format + " : "+ExcelUtils.REPORT_FORMAT_EXL_2007);
		Workbook wb = format.equals(ExcelUtils.REPORT_FORMAT_EXL_2007) ?  new XSSFWorkbook(inp):new HSSFWorkbook(inp); // keep 100 rows in memory, exceeding rows will be flushed to disknew XSSFWorkbook(inp);
		inp.close();	
		Sheet firstSheet = wb.getSheetAt(0);
		Sheet secSheet = wb.getSheetAt(1);	
		secSheet.getRow(0).getCell(0).setCellValue("Before Skill Analysis Radar Chart"); 
		firstSheet.getRow(1).getCell(1).setCellValue("Radar Chart-Before and After Skill Analysis"); 
		firstSheet.getRow(2).getCell(2).setCellValue(location);  
		firstSheet.getRow(2).getCell(6).setCellValue(empName);  
		firstSheet.getRow(3).getCell(2).setCellValue(classifcn); 
		firstSheet.getRow(3).getCell(6).setCellValue(role); 
		firstSheet.getRow(4).getCell(1).setCellValue(unitHeading);  
		firstSheet.getRow(4).getCell(2).setCellValue(unit);  
		firstSheet.getRow(4).getCell(6).setCellValue(preparedBy);
		firstSheet.getRow(5).getCell(1).setCellValue(deptHeading);
		firstSheet.getRow(5).getCell(2).setCellValue(dept);  
		firstSheet.getRow(5).getCell(6).setCellValue(nxtEvaluationDate);  
		firstSheet.getRow(6).getCell(2).setCellValue(evaluationDate);  
		 
		int rowNo=0;
		int befRows = 2 + beforeDatas.length();
		int aftRows = 11 + afterDatas.length();
		int befCategories = beforeCatgrs.length();
		int aftCategories = afterCatgrs.length();	
		//CommonMessage.debugMsg(befRows);
		//CommonMessage.debugMsg(aftRows);
		//CommonMessage.debugMsg(befCategories);
		//CommonMessage.debugMsg(aftCategories);
		while(rowNo<200)
		{
			if(rowNo != 0)
				secSheet.createRow(rowNo);
			if(rowNo < 9)
			{
				if(rowNo>=befRows)
					secSheet.getRow(rowNo).setZeroHeight(true);
			}
			else
			{
				if(rowNo>=aftRows)
					secSheet.getRow(rowNo).setZeroHeight(true);
			}
			
			for(int colNo=0;colNo<100;colNo++)
			{
				if(rowNo < 9 && rowNo<befRows)
				{
					setChartValues(secSheet,rowNo,0,colNo,befCategories,beforeCatgrs,beforeNames,beforeDatas,2);
				}
				else 
				{
					//CommonMessage.debugMsg("ELSE : "+rowNo);
					if(rowNo >= 9 && rowNo<aftRows)
						setChartValues(secSheet,rowNo,9,colNo,aftCategories,afterCatgrs,afterNames,afterDatas,11);
				}
			}
			
			rowNo++;
		}
		
		if(format.equals(ExcelUtils.REPORT_FORMAT_EXL_2007))
		{
			List<ExcelInsertImage> imageType = new ArrayList();
			ExcelInsertImage  xlImg = new ExcelInsertImage();							
			xlImg.col1=5;
			xlImg.row1 = 7;
			String impath = UIUtils.getImagePath(request);		
			String[] pathArr = impath.split("/");
		
			String pathImg = "";
			for(int k=0;k<pathArr.length-2;k++)
			{
				pathImg += pathArr[k]+"/";			
			}
			xlImg.imageFileName=pathImg+imgPath;
			//CommonMessage.debugMsg("Add Image Datas......"+pathImg+xlImg.imageFileName);
			imageType.add(xlImg);	
			ExcelUtils.addImages(wb,firstSheet,imageType);
		}
		return wb;
		
		/*if(rowNo != 0)
		{
			if(colNo < befCategories)
			{
				Cell cell = secSheet.getRow(rowNo).getCell(colNo);  
				if (cell == null)  
					cell = secSheet.getRow(rowNo).createCell(colNo);
				if(rowNo == 1)
				{
					if(colNo != 0)
						cell.setCellValue(beforeCatgrs.length()>0?beforeCatgrs.get(colNo-1).toString():""); 
				}
				else
				{
					if(colNo == 0)
					{
						cell.setCellValue(beforeNames.length()>0?beforeNames.get(befRowTitle).toString():"");
						befRowTitle++;
					}
					else
					{
						JSONArray val = beforeDatas.getJSONArray(befRows-2);
						if(UIUtils.isValidKeyId(val.getString(colNo-1)))
							 cell.setCellValue(new Double(val.getDouble(colNo-1)));
						else
							 cell.setCellValue(new Double(0));
					}
				}
			}
			else
			{
				secSheet.setColumnHidden(colNo,true);
			}
		
		}*/
		
}
private void setChartValues(Sheet secSheet,int rowNo,int rowFlag,int colNo,int colFlag,JSONArray categories,JSONArray names,JSONArray datas,int dataArrIndex)
{
	
	if(rowNo != rowFlag)
	{
		if(colNo <= colFlag)
		{
			Cell cell = secSheet.getRow(rowNo).getCell(colNo);  
			if (cell == null)  
				cell = secSheet.getRow(rowNo).createCell(colNo);
			if(rowNo == rowFlag+1)
			{
				if(colNo != 0)
				{
					cell.setCellValue(categories.length()>0?categories.get(colNo-1).toString():""); 
					//CommonMessage.debugMsg(rowNo + "-->"+colNo+" : "+categories.get(colNo-1).toString());
				}
			}
			else
			{
				if(colNo == 0)
				{
					cell.setCellValue(names.length()>0?names.get(rowNo-dataArrIndex).toString():"");
					//nameArrIndex++;
				}
				else
				{
					//CommonMessage.debugMsg("rn "+rowNo);
					JSONArray val = datas.getJSONArray(rowNo-dataArrIndex);
					//CommonMessage.debugMsg("rown "+rowNo);
					//CommonMessage.debugMsg(rowNo + "-->"+colNo+" : "+val.getString(colNo-1));
					if(colNo<=val.length())
					{
						if(UIUtils.isValidKeyId(val.getString(colNo-1)))
							 cell.setCellValue(new Double(val.getDouble(colNo-1)));
						else
							 cell.setCellValue(new Double(0));
					}
					else
						 cell.setCellValue(new Double(0));
					//CommonMessage.debugMsg(rowNo + "Afetr-->"+colNo+" : "+val.getString(colNo-1));
				}
			}
		}
		else
		{
			secSheet.setColumnHidden(colNo,true);
		}
	}
	else
	{
		if(rowFlag == 9 && rowNo == 9)
		{
			if(colNo == 0)
			{
				Cell cell = secSheet.getRow(rowNo).getCell(colNo);  
				if (cell == null)  
					cell = secSheet.getRow(rowNo).createCell(colNo);
				
				cell.setCellValue("After Skill Analysis Radar Chart"); 
			}
		}
	}
}
		
}
