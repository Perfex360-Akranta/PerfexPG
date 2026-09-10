package com.akranta.tpm.controller;

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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.BAL_FunctLocFieldNameBean;
import com.akranta.tpm.bean.BalWorkOrderDetailsBean;
import com.akranta.tpm.bean.BalWorkOrderDetailsLstBean;
import com.akranta.tpm.bean.BalWorkOrderFormBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
//import com.akranta.tpm.bean.WorkOrderDetailsBean;
//import com.akranta.tpm.bean.WorkOrderDetailsLstBean;
//import com.akranta.tpm.bean.WorkOrderFormBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BalPlmTlCalendar;
import com.akranta.tpm.model.BalPlmTlMultipleResp;
import com.akranta.tpm.model.BalPlmTlSpareconsumed;
import com.akranta.tpm.model.BalPlmTlSparecostactual;
import com.akranta.tpm.model.BalPlmTlWofeedback;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlCalendar;
import com.akranta.tpm.model.PlmTlMultipleResp;
import com.akranta.tpm.model.PlmTlSpareconsumed;
import com.akranta.tpm.model.PlmTlSparecostactual;
import com.akranta.tpm.model.PlmTlWofeedback;
import com.akranta.tpm.model.BalWoObservation;
import com.akranta.tpm.service.MonthlyPlanEntryService;
import com.akranta.tpm.service.impl.MonPlanEntryServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;



public class MonthlyPlanConfigEntryServlet extends HttpServlet {
	
	
	MonthlyPlanEntryService monPlanEntryService;
	  public MonthlyPlanConfigEntryServlet() {
		  super();
			/*try {
				monPlanEntryService = new MonPlanServiceImpl();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			*/
	    }
	    
	    
	    /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			try {
				processRequest(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			try {
				processRequest(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		   throws Exception {
			
				HttpSession httpSession = request.getSession(false);
				String action = BAL_UIUtils.getActionPart(request);
				
				try {
					//monPlanEntryService = (MonPlanServiceImpl)UIUtils.getServiceObject(request,"MonPlanServiceImpl");

					monPlanEntryService = (MonPlanEntryServiceImpl)BAL_UIUtils.getServiceObject(request,"MonPlanEntryServiceImpl");
					monPlanEntryService.MonPlanEntryServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
				} catch (ServiceObjectCreationException e) {
					CommonFunctions.debugMsg(e);
				}
			   if( action.equals("filterXmlmonplanconf_input.mpce")){
					 response.setContentType("xml"); 
					System.out.println("action "+ action); 
					UIUtils.forwardRequest(request, response, "/tiles/xml/monthlyPlanconfig.xml") ;
			   }
			   if( action.equals("yrlplanconf_input.mpce") ){
				   CommonFunctions.debugMsg("In side Entry Servlet");
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/CalendarGen.jsp");
				   rd.forward(request, response);
			   }
			   if( action.equals("monplanconf_input.mpce") ){	
				CommonFunctions.debugMsg("In side Entry Servlet");
				   String mchId = request.getParameter("cmbMchid");
				   String cellId = request.getParameter("cmbCellid");
				   String sectId = request.getParameter("cmbSectid");
				   String factId = request.getParameter("cmbFactid");
				   String fromMonth = request.getParameter("dtFromMonth");
				   String toMonth = request.getParameter("dtToMonth");
				   String relTo = request.getParameter("cboRelatedTo");
				   String filterString = request.getParameter("filterString");
				   String asseqpWise = request.getParameter("asseqpWise");
				   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				   			
				   if( fromMonth == null ){
					   String currentDate = CommonFunctions.getDate();
					   fromMonth = CommonFunctions.addMonth(currentDate,-3);
					   toMonth = CommonFunctions.addMonth(currentDate,0);
					   
					   fromMonth = fromMonth.substring(fromMonth.indexOf("-")+1);
					   toMonth = toMonth.substring(toMonth.indexOf("-")+1);
					  // CommonFunctions.debugMsg("Tomonth "+ CommonFunctions.getCurrentMonth()+CommonFunctions.getCurrentYear()+" from month"+CommonFunctions.addMonth(currentDate,-3));
					   //int year = Integer.parseInt(CommonFunctions.getCurrentYear());
					   //fromMonth =   CommonFunctions.getCurrentMonth() +"-"+(year-1);
					   //toMonth =   CommonFunctions.getCurrentMonth() +"-"+(year);
				   }
				   //RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/allocationUpdtCancel.jsp");
				   //RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/updatecancelWo.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/pages/monthlyplanconfigEntry.jsp");
					request.setAttribute("machineId", mchId);
					request.setAttribute("cellId", cellId);
					request.setAttribute("sectionId", sectId);
					request.setAttribute("factId", factId);
					request.setAttribute("fromMonth", fromMonth);
					request.setAttribute("toMonth", toMonth);
					request.setAttribute("relatedTo", relTo);
					request.setAttribute("filterString", filterString);
					request.setAttribute("asseqpWise", asseqpWise);
					request.setAttribute("hdncmbTradeId", tradeId);
					request.setAttribute("hdncmbJobType", jobType);
					//String sql = "select CNFM_KEYID  from "+TableNames.TBL_ADM_TL_CONFIGURATIONMST +" where CNFM_CODE = 'ENTCODETRUE'";
					rd.forward(request, response); 
			   }
			   else if( action.equals("monplanconsolidate_input.mpce") )
			   {
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/monthlyplanconfiguration.jsp");
				   rd.forward(request, response);
			   }
			   else if( action.equals("monplanconsolidate_getCol.mpce") )
			   {
				   PrintWriter out = response.getWriter();	
					List<String[]> managercompfrmtgrid = null;
					try {
						managercompfrmtgrid = monPlanEntryService.getfillgridheader();
					} catch (Exception e) {
						e.printStackTrace();
					}
					JSONObject jsonObject = getTableModel1(managercompfrmtgrid);
					out.println(jsonObject);
			   }
			  
			   else if( action.equals("monplanconf_getCol.mpce") )
			   {
				   String type= request.getParameter("type");
				   String eqpWise = request.getParameter("chkeqpmntchkbox");//Swetha added for the checkbox switch
				   
				   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
				   commonFilter.setType(eqpWise);//Swetha added for the checkbox switch

				   if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-2).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(0).substring(3,11));

						  commonFilter.setMonwise("Y");
						  
				   }
				   else{
				 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
							  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
							  commonFilter.setToDate(CommonFunctions.getDate());
							 // commonFilter.setMonwise(" ");
					 	  }
				   }
				   CommonFunctions.debugMsg(" Assembly Wise.......... "+commonFilter.getEqpmnt());

				   List<String []> monthlyPlanList  = monPlanEntryService.getMonPlan(commonFilter,type);
				   CommonFunctions.debugMsg("*******************************");
				   for (String[] arr : monthlyPlanList) {
					   CommonFunctions.debugMsg(Arrays.toString(arr));
					}
				  
				   JSONObject monthlyPlanData = fillJqGrid(monthlyPlanList,request,2,0,commonFilter);
				   httpSession.setAttribute("monthlyPlanData", monthlyPlanData);						
				   PrintWriter out = response.getWriter();
				   JSONObject jsonObject = getTableModel(monthlyPlanList,commonFilter.getEqpmnt());				 

				   httpSession.removeAttribute("monthCalColModel");
				   httpSession.setAttribute("monthCalColModel", jsonObject);
				   out.println(jsonObject);
			   }
			   else if( action.equals("monplanconf_getData.mpce") )
			   {
				   try
					{
					   	 PrintWriter out = response.getWriter();
						 BAL_UIUtils.displayRequestParamsValue(request);
						 String page = request.getParameter("page");
						 String type = request.getParameter("type");
						 	String eqpWise = request.getParameter("chkeqpmntchkbox");
						 	
						 httpSession = request.getSession();
						 CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
						 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
							  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-1).substring(3,11));
							  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(1).substring(3,11));
							  commonFilter.setMonwise("Y");
					 	 }
						 JSONObject jsonObject = (JSONObject) httpSession.getAttribute("monthlyPlanData"); 
						 
						
						 commonFilter.setType(eqpWise);
						 if(page.equals("1") && jsonObject != null){
							 CommonFunctions.debugMsg("Type==="+type);
			        		 
						 }else
			        	 {				
							
			        		 List<String[]> monPlan  = monPlanEntryService.getMonPlan(commonFilter,type);
			        		 jsonObject = fillJqGrid(monPlan,request,2,0,commonFilter); 
			        		// jsonObject=fillJ
			        	 }
					
						 out.println(jsonObject);
						 commonFilter.setViewClick('N');	  			 	
			  			 httpSession.removeAttribute("MonthlyPlanCommonFilter");
			  			 httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
			  			httpSession.removeAttribute("monthlyPlanData");

				    }
					catch(Exception e)
					{
						 CommonFunctions.debugMsg(e.getMessage());
					}
			   }
			 
			   
			   else if( action.equals("monplanconsolidate_getData.mpce") )
			   {
				     CommonFilter commonFilter = new CommonFilter();
		    		 List<String []> getAllmonthlysolidate  = monPlanEntryService.getfillgriddata(commonFilter);
		             PrintWriter out = response.getWriter();
		                
		  			 JSONObject Monthlysolidate = BAL_UIUtils.convertToJqGridTableObject(getAllmonthlysolidate,request,0,0); 
		  			 out.println(Monthlysolidate);
			   }
			   else if( action.equals("monplanconf_getExcel.mpce")){
					
					//HttpSession httpSession = request.getSession(false);
					CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					//commonFilter.setToRow(null);
					String fromMonth = commonFilter.getFromMonth();
					String toMonth = commonFilter.getToMonth();
					
					String month1   = fromMonth +"  -  "+ toMonth ;
					String fromdate = commonFilter.getFromDate();
					String todate = commonFilter.getToDate();
					String date   = fromdate +"  -  "+ todate ;
					
					//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
					JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("monthCalColModel");
					//tblJSONObj.put("title", "Monthly Calender" + " - "+date);
					//if( commonFilter.getMonwise().equals("Y")){
					//	tblJSONObj.put("title", "Monthly Calender" + " - "+month1);
					//}
					//tblJSONObj.put("transpose",true); 
					//tblJSONObj.put("title", "Production Loss Analysis Report");
					String format = ExcelUtils.getFormat(request);
					
					Workbook wb = monPlanEntryService.getMonPlanExcel(commonFilter,tblJSONObj,format);
					commonFilter.setFromRow(tmpFromRow);
					
					ExcelUtils.writeToResponse(response, wb, "MonthlyCalender", format);
					
				}
			   	else if(action.equals("monplanconf_process.mpce")){
				   
			   		try
					{
			   			CommonFunctions.debugMsg("In side Process");
					   	 PrintWriter out = response.getWriter();
						 CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);
						 CommonFunctions.debugMsg("In side Process     ....... " + commonFilter.getSectionId());
						 JSONObject jsonObject = (JSONObject) httpSession.getAttribute("monthlyPlanData"); 
					   	List<String[]> monPlan  = monPlanEntryService.refreshProcess(commonFilter);
		        		 jsonObject = fillJqGrid(monPlan,request,2,0,commonFilter);
						 BAL_UIUtils.displayRequestParamsValue(request);
						 String page = request.getParameter("page");	  
						 httpSession = request.getSession();
						// CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
						 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					   		  CommonFunctions.debugMsg("In side Process in if");
							  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-1).substring(3,11));
							  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(1).substring(3,11));
							  commonFilter.setMonwise("Y");
					 	 }
						// JSONObject jsonObject = (JSONObject) httpSession.getAttribute("monthlyPlanData"); 
						 if(page.equals("1") && jsonObject != null){
					   			CommonFunctions.debugMsg("In side Process iff");

						 }else
			        	 {				
					   			CommonFunctions.debugMsg("In side Process else");

			        		// List<String[]> monPlan  = monPlanEntryService.refreshProcess(commonFilter);
			        		 jsonObject = fillJqGrid(monPlan,request,2,0,commonFilter); 
			        		// jsonObject=fillJ
			        	 }
						
						 out.println(jsonObject);
						 commonFilter.setViewClick('N');	  			 	
			  			 httpSession.removeAttribute("MonthlyPlanCommonFilter");
			  			 httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
			  			httpSession.removeAttribute("monthlyPlanData");

				    }
					catch(Exception e)
					{
						 CommonFunctions.debugMsg(e.getMessage());
					}
			   }
			   
			   else if( action.equals("openMpDialog_input.mpce")){
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/mpdialog.jsp"); 
				   rd.forward(request, response);
			   }
			   else if( action.equals("generatewo_input.mpce")){
				   String cmbMach = request.getParameter("cmbMchid");
				   String cmbAssm = request.getParameter("cmbAssmbid");
				   String cmbfct = request.getParameter("cmbFactid");
				   String cmbsect = request.getParameter("cmbSectid");
				   String dtFromMonth = request.getParameter("dtFromMonth");
				   String strweekNo = request.getParameter("weekNo");
				   String chkselected = request.getParameter("chkselected");
				   String chkmodifyselected = request.getParameter("chkmodifyselected");
				   String persistdata = request.getParameter("from");
				   
				   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				   
				   httpSession.setAttribute("chkmodifyselected",chkmodifyselected);
				   httpSession.setAttribute("viewChecked", chkselected);

				   String filtrs = null;
				   filtrs = "&cmbMchid="+cmbMach;
				   filtrs +="&cmbAssmbid="+cmbAssm;
				   filtrs +="&cmbFactid="+cmbfct;
				   filtrs +="&cmbSectid="+cmbsect;
				   filtrs +="&dtFromDate="+dtFromMonth;
				   filtrs +="&dtToDate="+dtFromMonth;
				   filtrs +="&weekNo="+strweekNo;
				   filtrs += "&hdncmbTradeId="+tradeId;
				   filtrs +="&hdncmbJobType="+jobType;
				   httpSession.setAttribute("FromMonth", dtFromMonth);
				   httpSession.setAttribute("assmId", cmbAssm);
				   
				   request.setAttribute("persistdata", persistdata);
				   request.setAttribute("filter_string", filtrs);
				   if(BAL_UIUtils.isValidKeyId(chkmodifyselected))
					   request.setAttribute("modeModify", chkmodifyselected);
				   request.setAttribute("mode", chkselected);
				   request.setAttribute("url","PM");
				   request.setAttribute("completedByid", BAL_UIUtils.getLoginUser(request).getUsrm_ccno());
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/genEntryWO.jsp");//genEntryWO.jsp 
				   rd.forward(request, response); 
			   }
			   
			   // For Mobile Development //
			   else if( action.equals("pmmobiledevelopmnt_input.mpce")){

			   String cmbsect = request.getParameter("cmbSectid");
			   String dtFromMonth = request.getParameter("dtFromMonth");
			   String strweekNo = request.getParameter("weekNo");
			   String chkselected = request.getParameter("chkselected");
			   String chkmodifyselected = request.getParameter("chkmodifyselected");
			   String persistdata = request.getParameter("from");
			   
			   String tradeId=request.getParameter("hdncmbTradeId");
			   String jobType=request.getParameter("hdncmbJobType");
			   
			   httpSession.setAttribute("chkmodifyselected",chkmodifyselected);
			   httpSession.setAttribute("viewChecked", chkselected);

			   
			   httpSession.setAttribute("FromMonth", dtFromMonth);
			   
			   request.setAttribute("persistdata", persistdata);
			   if(BAL_UIUtils.isValidKeyId(chkmodifyselected))
				   request.setAttribute("modeModify", chkmodifyselected);
			   request.setAttribute("mode", chkselected);
			   request.setAttribute("url","PM");
			   request.setAttribute("completedByid", BAL_UIUtils.getLoginUser(request).getUsrm_ccno());
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/PMMobileDevelopment.jsp");//genEntryWO.jsp 
			   rd.forward(request, response); 
		   }
			   else if( action.equals("pmmobiledevelopmnt_getCol.mpce") )
			   {
				   String mchId = request.getParameter("mchId");

				   httpSession.setAttribute("mchId", mchId);
				   //List<String []> monthlyPlanList  = monPlanEntryService.getTask();
				   
				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
			/*	   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				    if(UIUtils.isValidKeyId(tradeId))
				    	commonFilter.setTrarId(tradeId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				 	commonFilter.setJobtype(jobTypeTrade);*/
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   PrintWriter out = response.getWriter();
				   out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "MobWorkOrderGeneration"));
				   System.out.println("col Model "+BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "MobWorkOrderGeneration"));
			   }
			   else if( action.equals("pmmobiledevelopmnt_getData.mpce") )
			   {
				   
				   CommonFilter commonFilter  = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				
				   String mchId = request.getParameter("mchId");
				   commonFilter.setMachineId(mchId);
				   //commonFilter.setFromDate("01-jan-2019");
				   //commonFilter.setToDate("31-DEC-2019");
				   
				   /*   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				    if(UIUtils.isValidKeyId(tradeId))
				    	commonFilter.setTrarId(tradeId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				 	commonFilter.setJobtype(jobTypeTrade);*/
				   String weekN0 = (String)httpSession.getAttribute("weekno");
				   commonFilter.setWeekno(weekN0);
				   if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						 
						  // commonFilter.setMonwise(" ");
						  CommonFunctions.debugMsg("fromDate Else    :"+commonFilter.getFromDate()+"----"+commonFilter.getToDate());
				 	  }
				   commonFilter.setFromDate(CommonFunctions
							.getFirstDateofMonth(Integer.valueOf(-3)));
					commonFilter.setToDate(CommonFunctions.getDate());
					  httpSession.removeAttribute("MonthlyPlanCommonFilter");
					  httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
					  List<String []> wogenGrid  = null;
					  PrintWriter out = response.getWriter();
					  JSONObject wogenGriddata = null;
					  wogenGrid  = monPlanEntryService.getAllwoGenDataMobile(commonFilter);
					  wogenGriddata = BAL_UIUtils.convertToJqGridTableObject(wogenGrid,request,0,0);
					  CommonFunctions.debugMsg(wogenGrid.size());
					  out.println(wogenGriddata);
					  CommonFunctions.debugMsg("assmblyGriddata  "+wogenGriddata);
				   
			   }
			   
			   
			   
else if( action.equals("MobileCompleteWO_input.mpce")){
				  
				   
			   }
			   
		  	   else if( action.equals("MobileCompleteWO_getCol.mpce") )
			   {
				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   String woId = request.getParameter("woId");
				   String machId = request.getParameter("cmbMchid");
				   String type = request.getParameter("type");
				   httpSession.setAttribute("woId",woId);
				   httpSession.setAttribute("machId",machId);
				   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
				  // String tradeId=request.getParameter("hdncmbTradeId");
				  
				   commonFilter.setMachineId(machId);
				   commonFilter.setWodetailid(woId);
				   String jobType=request.getParameter("jobtype");
				   CommonFunctions.debugMsg(jobType+"  jobtype  " +"%%%%woId "+woId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(type))
				    {
				    	jobTypeTrade.setId(type);
				    }
				 	commonFilter.setJobtype(jobTypeTrade);
				 	
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   PrintWriter out = response.getWriter();
				   CommonFunctions.debugMsg("typetype"+type);
				   out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "MobileCompleteWO"));
				   System.out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "MobileCompleteWO"));
				   
				   //
				/*   List<String []> monthlyPlanList  = monPlanEntryService.getSchedule(commonFilter);
				   JSONObject jsonObject = getTableModel(monthlyPlanList,commonFilter.getEqpmnt());				 
				   
				   httpSession.removeAttribute("colComWoModel");
				   httpSession.setAttribute("colComWoModel", jsonObject);
				   CommonFunctions.debugMsg("in side col,,"+httpSession.getAttribute("colComWoModel"));
				   //out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "CompleteWO"));
				   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "MobWorkOrderGeneration"));
*/
			   }
			   else if( action.equals("MobileCompleteWO_getData.mpce") )
			   {
				   
				   CommonFilter commonFilter  = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				   String jobType=request.getParameter("jobtype");
				   String type=request.getParameter("type");
				   String woId = request.getParameter("woId");
				   String machId = request.getParameter("cmbMchid");
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				 	commonFilter.setJobtype(jobTypeTrade);
				   
				  // String woId = (String)httpSession.getAttribute("woId");
				   String viewChecked=(String)httpSession.getAttribute("viewChecked");
				   String weekN0 = (String)httpSession.getAttribute("weekno");
				   commonFilter.setWeekno(weekN0);
				   commonFilter.setMachineId(machId);
				   commonFilter.setWodetailid(woId);
				  // commonFilter.setWodetailid(woId);
				   if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						 
						  // commonFilter.setMonwise(" ");
						 // CommonFunctions.debugMsg("fromDate Else    :"+commonFilter.getFromDate()+"----"+commonFilter.getToDate());
				 	  }
				   		httpSession.removeAttribute("MonthlyPlanCommonFilter");
					  httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
					  List<String []> woCompGrid  = null;
					  PrintWriter out = response.getWriter();
					  JSONObject woCompGriddata = null;
					
					  woCompGrid  = monPlanEntryService.getSchedule(commonFilter);
					  
					  woCompGriddata = BAL_UIUtils.convertToJqGridTableObject(woCompGrid,request,0,0);
					  //CommonFunctions.debugMsg(woCompGrid.size());
					  out.println(woCompGriddata);
					  //CommonFunctions.debugMsg("assmblyGriddata  "+woCompGriddata);
				   
			   }
			  
			   
			   
			   
			   
			   
			   
			   // For Mobile Development //
			   else if( action.equals("genWO_input.mpce")){
				   String fltrStr = request.getParameter("weekNo");
				   CommonFunctions.debugMsg("fltrAMK  :"+fltrStr);
				   String filtrs = null;
				   filtrs = "cmbMchid="+fltrStr;
				   				  
			   }
			   else if(action.equals("sdmOpen_input.mpce")){
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/ShutdownMaint.jsp"); 
				   rd.forward(request, response);  
			   }
			   else if(action.equals("AbnModify_input.mpce")){
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/wogenAbnormality.jsp"); 
				   rd.forward(request, response);  
			   }
			   else if(action.equals("AbnModify_getCol.mpce"))
				{
					PrintWriter out = response.getWriter();
					
					String fromWogen = request.getParameter("fromWogen");
					populateCommonFilter(request,"MonthlyPlanCommonFilter",true);
					
					response.setContentType("text/html");
					/*String colmod = UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder","AbnModify");
					JSONObject jsonObject = JSONObject.fromString(colmod);
					 jsonObject.set("tableHeight", "29%%");
				     jsonObject.set("tableWidth", "30.5%%");*/
					
						
						/*jsonObject.getJSONArray("colModel").getJSONObject(1).set("hidden",true);
						jsonObject.getJSONArray("colModel").getJSONObject(2).set("hidden",false);*/
					
					//out.println(jsonObject);
					out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder","AbnModify"));
				}
				
				else if(action.equals("AbnModify_getData.mpce"))
				{
					try
					{
						PrintWriter out = response.getWriter();
						BAL_UIUtils.displayRequestParamsValue(request);
						CommonFilter commonFilter  = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
						String weekNO = request.getParameter("weekNO");
						String monthstr = request.getParameter("monthstr");
						commonFilter.setFromDate(monthstr);
						commonFilter.setToDate(monthstr);
						String abnmStatus="modification";
						if(action.equals("AbnModify_getData.abnForm"))
							abnmStatus="modification";
						else if(action.equals("AbnTagRemove_getData.abnForm"))
							abnmStatus="removal";
					
		  			 	List<String[]> AbnDataList  =  monPlanEntryService.getAllModifyForm(commonFilter,weekNO);
					 	JSONObject AbnormalityData = BAL_UIUtils.convertToJqGridTableObject(AbnDataList, request, 0, 0, commonFilter.getTotalRecordCnt());
		  				
		  			 	out.println(AbnormalityData);

				    }catch(Exception e)
					{
						System.out.println("getdata Exception :"+e.getMessage());
					}
				}
			   else if( action.equals("genWO_getCol.mpce") )
			   {
				   String weekNO = request.getParameter("weekNo");

				   httpSession.setAttribute("weekno", weekNO);
				   //List<String []> monthlyPlanList  = monPlanEntryService.getTask();
				   
				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
				   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				    if(BAL_UIUtils.isValidKeyId(tradeId))
				    	commonFilter.setTrarId(tradeId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				 	commonFilter.setJobtype(jobTypeTrade);
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   PrintWriter out = response.getWriter();
				   out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "WorkOrderGeneration"));
				   System.out.println("col Model "+BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "WorkOrderGeneration"));
			   }
			   else if( action.equals("genWO_getData.mpce") )
			   {
				   
				   CommonFilter commonFilter  = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				    if(BAL_UIUtils.isValidKeyId(tradeId))
				    	commonFilter.setTrarId(tradeId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				 	commonFilter.setJobtype(jobTypeTrade);
				   String weekN0 = (String)httpSession.getAttribute("weekno");
				   commonFilter.setWeekno(weekN0);
				   if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						 
						  // commonFilter.setMonwise(" ");
						  CommonFunctions.debugMsg("fromDate Else    :"+commonFilter.getFromDate()+"----"+commonFilter.getToDate());
				 	  }
					  httpSession.removeAttribute("MonthlyPlanCommonFilter");
					  httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
					  List<String []> wogenGrid  = null;
					  PrintWriter out = response.getWriter();
					  JSONObject wogenGriddata = null;
					  wogenGrid  = monPlanEntryService.getAllwoGenData(commonFilter);
					  wogenGriddata = BAL_UIUtils.convertToJqGridTableObject(wogenGrid,request,0,0);
					  CommonFunctions.debugMsg(wogenGrid.size());
					  out.println(wogenGriddata);
					  CommonFunctions.debugMsg("assmblyGriddata  "+wogenGriddata);
				   
			   }
			   /*Work order details pop*/
			   else if(action.equals("woDetails_save.mpce")){
				   
			   }
			   else if(action.equals("sprWOdetails_input.mpce")){
				   
			   }
			   else if(action.equals("sprWOdetails_getCol.mpce")){
				   PrintWriter out = response.getWriter();
				   String pmstdId = request.getParameter("pmstdId");
				   httpSession.setAttribute("pmstdIdDrt",pmstdId);
				   out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder","spareGrid"));
				   System.out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "spareGrid"));
			   }
			   else if(action.equals("sprWOdetails_getData.mpce")){
				   List<String []> spareGrid  = null;
					  PrintWriter out = response.getWriter();
					  String pmstdIdDrt = (String)httpSession.getAttribute("pmstdIdDrt");
					  String pmstdId = null;
					  if(BAL_UIUtils.isValidKeyId(pmstdIdDrt))
							  pmstdId = pmstdIdDrt ;
					  else
						pmstdId = (String)httpSession.getAttribute("pmstdId");
					  
					  JSONObject spareGriddata = null;
					  spareGrid  = monPlanEntryService.getspareData(pmstdId);
					  spareGriddata = BAL_UIUtils.convertToJqGridTableObject(spareGrid,request,0,0);
					  CommonFunctions.debugMsg(spareGrid.size());
					  out.println(spareGriddata);
					  CommonFunctions.debugMsg("spareGriddata  "+spareGriddata);
			   }
			   else if( action.equals("workOrderDetails_input.mpce")){
				  String activityDesp = request.getParameter("freqData");
				  String machId = request.getParameter("machId");
				  String woDetailId = request.getParameter("woDetailId");
				  String pmCalId = request.getParameter("pmCalId");
				  String pmstdId = request.getParameter("pmstdId");
				  String duration = request.getParameter("duration");
				  String startDate = request.getParameter("startDate");
				  String jobType = request.getParameter("jobType");
				  String observation = request.getParameter("observation");
				  String completedBy = request.getParameter("completedBy");
				  String viewChecked=(String)httpSession.getAttribute("viewChecked");
				  httpSession.setAttribute("pmstdId", pmstdId);
				  httpSession.setAttribute("pmCalId", pmCalId);
				  httpSession.setAttribute("woDetailId", woDetailId);
				  httpSession.setAttribute("jobType", jobType);
				 
				  request.setAttribute("activityDesp", activityDesp);
				  request.setAttribute("machId", machId);
				  request.setAttribute("startDate", startDate);
				  request.setAttribute("duration", duration);
				  request.setAttribute("observation", observation);
				  BalWorkOrderDetailsBean workOrderDetailsBean = null;
				  PlmTlWofeedback plmtlwofeedback = new PlmTlWofeedback();
				  plmtlwofeedback.setWofbObservation(observation);
				  String   modestr =  null;
				  if(viewChecked.equals("Y")){
					  FormModes mode = FormModes.view;
					  modestr =  "view";
					   workOrderDetailsBean = new BalWorkOrderDetailsBean(modestr);
					     workOrderDetailsBean.setDisableForm("true");
					     request.setAttribute("completedBy",completedBy);
					     request.setAttribute("mode", modestr);
				  }  
				  request.setAttribute("plmtlwofeedback", plmtlwofeedback);
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/WorkOrder/WorkorderDetails.jsp"); 
				   rd.forward(request, response);   
			   }
			   else if(action.equals("reschedule_input.mpce")){
				   AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
				   request.setAttribute("user",user.getUsrm_ccno());
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/rescheduleActivity.jsp"); 
				   rd.forward(request, response);    
			   }
			   else if(action.equals("updtCancelGridsel_input.mpce") ){
				   String generateWO = request.getParameter("generateWO");
				   String prepByid  = request.getParameter("prepByid");
				   String actTypeSel = request.getParameter("actTypeSel");
				   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				   
				   if(! BAL_UIUtils.isValidKeyId(prepByid)){
					   prepByid  = request.getParameter("allotedto");
				   }
				   httpSession.setAttribute("WOgenCancl",generateWO);
				   if(BAL_UIUtils.isValidKeyId(generateWO)){
					   request.setAttribute("actTypeSel",actTypeSel);
				   }
				   if(BAL_UIUtils.isValidKeyId(generateWO)){
					   request.setAttribute("generateWO",generateWO);
				   }
				   if(BAL_UIUtils.isValidKeyId(prepByid)){
					   request.setAttribute("prepByid",prepByid);
				   }
				   request.setAttribute("loginEmpId",BAL_UIUtils.getLoginUser(request).getUsrm_ccno());
				   
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/allocationUpdtCancel.jsp"); 
				   rd.forward(request, response);    
			   }
			   else if(action.equals("updtCancelGridsel_getCol.mpce") ){
				   PrintWriter out = response.getWriter();
				  // String pmstdId = request.getParameter("pmstdId");
				  // httpSession.setAttribute("pmstdIdDrt",pmstdId);
				  // String workorderno = request.getParameter("workorderno");

				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
				   
				   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				    if(BAL_UIUtils.isValidKeyId(tradeId))
				    	commonFilter.setTrarId(tradeId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				 	commonFilter.setJobtype(jobTypeTrade);
				   String activitySelcted= request.getParameter("activitySelcted");
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   if(BAL_UIUtils.isValidKeyId(activitySelcted))
					   out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelSdmActivity"));
				   else
					   out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder","WorkOrderAlloted"));
				   
			//	   System.out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "WorkOrderAlloted"));
				   
			   }
			   
			   else if(action.equals("updtCancelGridsel_getData.mpce") ){
				   CommonFilter commonFilter  = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				    if(BAL_UIUtils.isValidKeyId(tradeId))
				    	commonFilter.setTrarId(tradeId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				    commonFilter.setJobtype(jobTypeTrade);
				    CommonFunctions.debugMsg("Trade Id "+tradeId+"  Job Type "+jobType);
				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   String workorderno = request.getParameter("workorderno");
				   String activitySelcted= request.getParameter("activitySelcted");

				   String WOgenCancl = request.getParameter("generateWO");
				   String weekNO = request.getParameter("weekNo");
				   commonFilter.setWeekno(weekNO);
				   JSONObject updatecancelGridsel = null;
				   PrintWriter out = response.getWriter();
				   if(BAL_UIUtils.isValidKeyId(activitySelcted)){
					   String monthYear = request.getParameter("dtFromDate");
					   if(weekNO.equals("1"))
						   monthYear = "01-"+monthYear;
					   if(weekNO.equals("2"))
						   monthYear = "08-"+monthYear;
					   if(weekNO.equals("3"))
						   monthYear = "15-"+monthYear;
					   if(weekNO.equals("4"))
						   monthYear = "22-"+monthYear;
					   commonFilter.setFromDate(monthYear);
					List<String[]>  activityList = monPlanEntryService.getfillActivity(commonFilter);
					   JSONObject activityListgrd = BAL_UIUtils.convertToJqGridTableObject(activityList, request, 0, 0);
					   out.println(activityListgrd);
				   }
				   else{
					   List<String []>   updatecancelsellist  = monPlanEntryService.getupdatecancelselctData(commonFilter,workorderno,WOgenCancl);
					   updatecancelGridsel = BAL_UIUtils.convertToJqGridTableObject(updatecancelsellist,request,0,0);
					   out.print(updatecancelGridsel);
				   }
			   }
			   else if(action.equals("kaizenGridsel_input.mpce")){
				   
			   }
//			  for new activity kaizen
			    else if(action.equals("kaizenGridsel_getCol.mpce") ){
				   PrintWriter out = response.getWriter();
				  // String pmstdId = request.getParameter("pmstdId");
				  // httpSession.setAttribute("pmstdIdDrt",pmstdId);
				   String KAIZEN = request.getParameter("KAIZEN");
				   CommonFunctions.debugMsg("KAIZEN  :"+KAIZEN);
				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
				   
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder","WorkOrderAlloted"));
				   System.out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "WorkOrderAlloted"));
				   
			   }
			    else if(action.equals("Observations_getCol.mpce") ){
					   response.getWriter().println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder","AddObservations"));
				   }
			    else if(action.equals("Observations_getData.mpce") ){
			    	CommonParams commonParams = new CommonParams();
			    	String flid = request.getParameter("flid");
			    	commonParams.setFlid(flid);
			    	FilterValues.populateGridParams(request,commonParams);
			    	List<String[]> observationList =  monPlanEntryService.getObservations(commonParams);
			    	response.getWriter().print(BAL_UIUtils.convertToJqGridTableObject(observationList,request,0,1,commonParams.getTotalRecordCnt()));
				}
			    else if(action.equals("Observations_save.mpce")){
			    	saveObservation(request,response);
			    }
			   else if(action.equals("kaizenGridsel_getData.mpce") ){
				   CommonFilter commonFilter  = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   String KAIZEN = request.getParameter("KAIZEN");
				   CommonFunctions.debugMsg("KAIZEN  :"+KAIZEN);
				  
				   JSONObject kaizenGridsel = null;
				   PrintWriter out = response.getWriter();
				   List<String []>   kaizenlist  = monPlanEntryService.getkaizenData(commonFilter,KAIZEN);
				   kaizenGridsel = BAL_UIUtils.convertToJqGridTableObject(kaizenlist,request,0,0);
				   CommonFunctions.debugMsg( kaizenlist.size());
				   out.print(kaizenGridsel);
			   }
			   else if(action.equals("generateWorkOrderABN.mpce")){
				   PrintWriter out = response.getWriter();
				   String selActforWoGen = request.getParameter("genGridData");
				   String weekno  = request.getParameter("weekno");
				   String strtDate  = request.getParameter("frmDatee");
				   String allotedTo = request.getParameter("allotedTo");
				   String description = request.getParameter("description");
				   strtDate = weekno+"/"+strtDate+"/"+description+"/"+allotedTo;
				   CommonFunctions.debugMsg(selActforWoGen+"strtDate :"+strtDate ); 
				   String genrWoABNStmnt = monPlanEntryService.generateWOForABN(selActforWoGen,strtDate);
				   JSONObject returnData = new JSONObject();

				   if(!genrWoABNStmnt.equals("wonotgenerated")){
					   CommonFunctions.debugMsg("genrWoStmnt   "+genrWoABNStmnt);
					   returnData.put("retmsg",genrWoABNStmnt);

				   }
				   else
					   returnData.put("retmsg","err");
				   out.print(returnData.toString());

			   }
			   else if(action.equals("generateWorkOrderSDM.mpce")){
				   PrintWriter out = response.getWriter();
				   AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
				   String selActforWoGen = request.getParameter("genGridData");
				   String strtDate  = request.getParameter("frmDatee");
				   String allotedTo = request.getParameter("allotedTo");
				   String description = request.getParameter("description");
				   String weekNo = request.getParameter("weekNo");
				   String month = request.getParameter("month");
				   String year = request.getParameter("year");
				   strtDate = strtDate+"/"+allotedTo+"/"+description+"/"+weekNo+"/"+month+"/"+year+"/"+user.getUsrm_ccno();
				   CommonFunctions.debugMsg("strtDate :"+strtDate ); 
				   String genrWoStmnt = monPlanEntryService.generateWOSDM(selActforWoGen,strtDate);
				   JSONObject returnData = new JSONObject();

				   if(!genrWoStmnt.equals("wonotgenerated")){
					   CommonFunctions.debugMsg("genrWoStmnt   "+genrWoStmnt);
					   returnData.put("retmsg",genrWoStmnt);

				   }
				   else
					   returnData.put("retmsg","err");
				   out.print(returnData.toString());
				   

			   }
			   else if(action.equals("pmMultipleResp_input.mpce"))
				{
					String pmWOKeyid =request.getParameter("pmWOKeyid");	
					String pmmachId=request.getParameter("machId");
					request.setAttribute("pmWOKeyid", pmWOKeyid);
					request.setAttribute("bdmachId", pmmachId);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/pmMultipleResp.jsp"); 
					rd.forward(request, response);
				}
				else if(action.equals("pmMultipleResp_getCol.mpce"))
				{
					PrintWriter out = response.getWriter();
					out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "empallgrid"));
				}
				else if(action.equals("pmMultipleResp_getData.mpce"))
				{
					String pmWOKeyid =request.getParameter("pmWOKeyid");	
					String pmmachId=request.getParameter("machId");
					CommonParams commonParams = new CommonParams();
					FilterValues.populateGridParams(request,commonParams);
					CommonFunctions.debugMsg("Machine Id"+pmmachId);
					commonParams.setFlid(pmmachId);
					List<String[]> bdList  = monPlanEntryService.getMultipleReposibility(pmWOKeyid,commonParams);
					JSONObject repeatedBdTblObj = BAL_UIUtils.convertToJqGridTableObject(bdList,request,0,1,commonParams.getTotalRecordCnt());
					response.getWriter().print(repeatedBdTblObj);
					
				}
			   else if(action.equals("generateWorkOrder.mpce")){
				   PrintWriter out = response.getWriter();
				   String selActforWoGen = request.getParameter("genGridData");
				   String strtDate  = request.getParameter("frmDatee");
				   String allotedTo = request.getParameter("allotedTo");
				   String description = request.getParameter("description");
				   String loginUser = BAL_UIUtils.getLoginUser(request).getUsrm_ccno();
				   strtDate = strtDate+"/"+allotedTo+"/"+description +"/"+loginUser;
				   
				   System.out.println("selActforWoGen "+selActforWoGen+"  strtDate"+strtDate+"  allotedTo"+allotedTo+ " description "+description+"  "+loginUser);
				   BalPlmTlWofeedback  newPlmTlWofeedback  = new BalPlmTlWofeedback ();
				   List<BalPlmTlMultipleResp> MultiResponse= null;
				   if(request.getParameter("hdnMultiresp")== null || request.getParameter("hdnMultiresp").equals(""))
		    		{
					   String genrWoStmnt = monPlanEntryService.generateWO(selActforWoGen,strtDate,newPlmTlWofeedback);
					   CommonFunctions.debugMsg("Inside the if");
			    }
		    		else
		    		{
		    			
			    		String multiresp = request.getParameter("hdnMultiresp");
			    		CommonFunctions.debugMsg(multiresp+"........Debug");
			    		JSONArray jsonArray = JSONArray.fromString(multiresp);		    	
			    		BalPlmTlMultipleResp plmTlMultipleResp = new BalPlmTlMultipleResp();
				    	MultiResponse = (List<BalPlmTlMultipleResp>) BAL_UIUtils.convertJSONArrToList(plmTlMultipleResp, jsonArray);
				    	if(plmTlMultipleResp != null)
				    		newPlmTlWofeedback.setplmTlMultipleResp(MultiResponse);
				    }
				   CommonFunctions.debugMsg("strtDate :"+strtDate ); 
				   String genrWoStmnt = monPlanEntryService.generateWO(selActforWoGen,strtDate,newPlmTlWofeedback);
				  // String genrWoStmnt = monPlanEntryService.generateWO(selActforWoGen,strtDate);
				   
				   CommonFunctions.debugMsg(genrWoStmnt+"wo order");
				   JSONObject returnData = new JSONObject();

				   if(!genrWoStmnt.equals("wonotgenerated")){
					   CommonFunctions.debugMsg("genrWoStmnt   "+genrWoStmnt);
					   returnData.put("retmsg",genrWoStmnt);

				   }
				   else
					   returnData.put("retmsg","err");
				   out.print(returnData.toString());
				   
			   }
			   else if(action.equals("generateWorkOrderKaizen.mpce")){
				   PrintWriter out = response.getWriter();
				   String selActforWoKzn = request.getParameter("genGridData");
				   String weekno  = request.getParameter("weekno");
				   String strtDate  = request.getParameter("frmDatee");
				   String allotedTo = request.getParameter("allotedTo");
				   String description = request.getParameter("description");
				   strtDate = weekno+"/"+strtDate+"/"+description+"/"+allotedTo;
				   CommonFunctions.debugMsg(selActforWoKzn+"strtDate :"+strtDate ); 
				   String genrWoKZNStmnt = monPlanEntryService.generateWOForKZN(selActforWoKzn,strtDate);
				   JSONObject returnData = new JSONObject();

				   if(!genrWoKZNStmnt.equals("wonotgenerated")){
					   CommonFunctions.debugMsg("genrWoStmnt   "+genrWoKZNStmnt);
					   returnData.put("retmsg",genrWoKZNStmnt);

				   }
				   else
					   returnData.put("retmsg","err");
				   out.print(returnData.toString());

			   }
			   else if(action.equals("updateActivites_input.mpce")){
				   PrintWriter out = response.getWriter();
				   String updateWoId = request.getParameter("gridData");
				   String allotedtocombo = request.getParameter("allotedtocombo");
				   String description  = request.getParameter("txtdescription");
				   String workorderno = request.getParameter("workorderno");
				   String noofActivites = request.getParameter("noofActivites");
				   CommonFunctions.debugMsg(updateWoId+"----"+allotedtocombo+"----"+description);
				   BalPlmTlWofeedback  newPlmTlWofeedback  = new BalPlmTlWofeedback ();
				   List<BalPlmTlMultipleResp> MultiResponse= null;
				   if(request.getParameter("hdnMultiresp")== null || request.getParameter("hdnMultiresp").equals(""))
		    		{
			    	}
		    		else
		    		{
		    			
			    		String multiresp = request.getParameter("hdnMultiresp");
			    		JSONArray jsonArray = JSONArray.fromString(multiresp);		
			    		newPlmTlWofeedback.setPmstandId(workorderno);
			    		BalPlmTlMultipleResp plmTlMultipleResp = new BalPlmTlMultipleResp();
				    	MultiResponse = (List<BalPlmTlMultipleResp>) BAL_UIUtils.convertJSONArrToList(plmTlMultipleResp, jsonArray);
				    	if(plmTlMultipleResp != null)
				    		newPlmTlWofeedback.setplmTlMultipleResp(MultiResponse);
				    }
				   String returnStmnt = monPlanEntryService.updateallocated(workorderno,updateWoId,allotedtocombo,newPlmTlWofeedback);
				   CommonFunctions.debugMsg("returnStmnt  :"+returnStmnt);
				   String returnMsg = null;
				   if(returnStmnt.equals("Success"))
					   returnMsg ="Work-Order Has Been Updated ";
				   else if(returnStmnt.equals("fail"))
					   returnMsg ="Error";
				   JSONObject returnData = new JSONObject();
				   returnData.put("successData",returnMsg);
    			   out.print(returnData.toString());
			   }
			   else if(action.equals("cancelActivites_input.mpce")){
				   PrintWriter out = response.getWriter();
				   String cancelWoId = request.getParameter("gridData");
				   String allotedtocombo = request.getParameter("allotedtocombo");
				   String description  = request.getParameter("txtdescription");
				   String workorderno = request.getParameter("workorderno");
				   String noofActivites = request.getParameter("noofActivites");
				   CommonFunctions.debugMsg(cancelWoId+"----"+allotedtocombo+"----"+description);
				   String returnStmnt = monPlanEntryService.cancelallocated(workorderno,cancelWoId,noofActivites);
				   CommonFunctions.debugMsg("returnStmnt  :"+returnStmnt);
				   String returnMsg = null;
				   if(returnStmnt.equals("Success"))
					   returnMsg ="Work-Order Has Been Cancelled ";
				   else if(returnStmnt.equals("fail"))
					   returnMsg ="Error";
				   JSONObject returnData = new JSONObject();
				   returnData.put("successData",returnMsg);
    			   out.print(returnData.toString());
				   
			   }
			   else if(action.equals("reschedule_save.mpce")){
				   
				   CommonFunctions.debugMsg("inside save action of reschedule");
				   System.out.println("inside SAve reschdule");
				   
				   AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
					   String cmbMach = request.getParameter("cmbMchid");
					   String fromMonth = request.getParameter("fromMonth");
					   String reschudledate = request.getParameter("reschudledate");
					   String weekNum = request.getParameter("weekNum");
					   String reason  = request.getParameter("reason");
					   String remarks = request.getParameter("remarks");
					   String reschdWeekNo = request.getParameter("reschdWeekNo");
					   String reschduleBy = request.getParameter("reschduleBy");
					   String reshdType =request.getParameter("reshdType");
					   String assemblyId = request.getParameter("assemblyId");
					  // String createdBy = user.getUsrm_ccno();
					   
					   String newDay =null;
					  
					   if(BAL_UIUtils.isValidKeyId(remarks))
					   		remarks="--";
					   String datas = cmbMach+"/"+fromMonth+"/"+reschudledate+"/"+weekNum+"/"+reason+"/"+remarks+"/"+reschdWeekNo+"/"+reschduleBy+"/"+reshdType+"/"+assemblyId;
					   
					  String saveReschedu = monPlanEntryService.saveReschedule(datas);
					  JSONObject returnData = new JSONObject();
					  PrintWriter out = response.getWriter();
					  if(saveReschedu.equals("Rescheduled")){
						   CommonFunctions.debugMsg("saveReschedu   "+saveReschedu);
						   returnData.put("retmsg","Rescheduled Successfully");

					   } else if(saveReschedu.equals("cantdo")){
						   CommonFunctions.debugMsg("saveReschedu   "+saveReschedu);
						   returnData.put("retmsg","Rescheduled Cannot Be Done For This Activity ");

					   }
					   else
						   returnData.put("retmsg","Rescheduled not Done");
					   out.print(returnData.toString());
					
				  // WorkOrderDetailsBean workOrderDetailsBean = new WorkOrderDetailsBean();
				  // saveReschedule(request,response,workOrderDetailsBean);
			   }
			   else if(action.equals("updatecancelgrid_input.mpce") ){
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/updatecancelWo.jsp"); 
				   rd.forward(request, response);    
			   }
			   else if(action.equals("updatecancelgrid_getCol.mpce") ){
				   PrintWriter out = response.getWriter();
				  // String pmstdId = request.getParameter("pmstdId");
				  // httpSession.setAttribute("pmstdIdDrt",pmstdId);
				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
				   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				    if(BAL_UIUtils.isValidKeyId(tradeId))
				    	commonFilter.setTrarId(tradeId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				    commonFilter.setJobtype(jobTypeTrade);
				   String weekno = request.getParameter("weekNo");
				   commonFilter.setWeekno(weekno);
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder","WorkOrderUpdateCancel"));
				   
			   }
			   else if(action.equals("updatecancelgrid_getData.mpce") ){
				   CommonFunctions.debugMsg("WEkkE nOOO  : inside");
				   CommonFilter commonFilter  = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				   String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("hdncmbJobType");
				    if(BAL_UIUtils.isValidKeyId(tradeId))
				    	commonFilter.setTrarId(tradeId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				    commonFilter.setJobtype(jobTypeTrade);
				    CommonFunctions.debugMsg("Trade Id "+tradeId+"  Job Type "+jobType);
				   String weekno = request.getParameter("weekNo");
				   commonFilter.setWeekno(weekno);
				   CommonFunctions.debugMsg("WEkkE nOOO  :"+commonFilter.getWeekno());
				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   JSONObject updatecancelGrid = null;
				   PrintWriter out = response.getWriter();
				  
				   List<String []>   updatecancellist  = monPlanEntryService.getupdatecancelData(commonFilter);
				   updatecancelGrid = BAL_UIUtils.convertToJqGridTableObject(updatecancellist,request,0,0);
				   CommonFunctions.debugMsg( updatecancellist.size());
				   out.print(updatecancelGrid);
			   }
			   else if(action.equals("workOrderDetails_save.mpce")){
				   
				   CommonFunctions.debugMsg("inside save action of workOrderDetails");
				   BalWorkOrderDetailsBean workOrderDetailsBean = new BalWorkOrderDetailsBean();
				   saveWODetails(request,response,workOrderDetailsBean);
			   }
			   else if( action.equals("generatewo_save.mpce")){
				      CommonFunctions.debugMsg("inside gen Wo of mce save ");
				      String viewChecked=(String)httpSession.getAttribute("viewChecked");
				      CommonFunctions.debugMsg("inside gen Wo save " +viewChecked);
				      String chkmodifyselected =(String)httpSession.getAttribute("chkmodifyselected");
				      if(BAL_UIUtils.isValidKeyId(chkmodifyselected))
				    	  viewChecked="N";
					  BalWorkOrderDetailsBean workOrderDetailsBean = new BalWorkOrderDetailsBean();
					  if(viewChecked.equals("N"))
					    saveGDWODetails(request,response,workOrderDetailsBean);
			   }
			   
			   //
			   
			   //
			   /*End*/
			   /*WorkOrder Completeion Grid*/
			   else if(action.equals("cbmWO_input.mpce")){
				   //inspectionId=&uomId
				   String inspectionId = request.getParameter("inspectionId");
				   String uomid = request.getParameter("uomId");
				   String hdnCurReadId = request.getParameter("hdnCurReadId"); 
				   String hdnCBMWodata = request.getParameter("hdnCBMWodata");
				   String hdnzoneCondition= request.getParameter("hdnzoneCondition");
				   String pmsdId = request.getParameter("pmsdId");
				   request.setAttribute("pmsdId", pmsdId);
				   request.setAttribute("inspectionId", inspectionId);
				   request.setAttribute("uomid", uomid);
				   request.setAttribute("hdnCurReadId",hdnCurReadId);
				   request.setAttribute("hdnCBMWodata",hdnCBMWodata);
				   request.setAttribute("hdnzoneCondition",hdnzoneCondition);
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/PMStandards/WOCBM.jsp"); 
				   rd.forward(request, response);  
			   }
			   else if( action.equals("completeWO_input.mpce")){
				  
				   
			   }
			   
		  	   else if( action.equals("completeWO_getCol.mpce") )
			   {
				   httpSession.removeAttribute("MonthlyPlanCommonFilter");
				   String woId = request.getParameter("woId");
				   String machId = request.getParameter("cmbMchid");
				   String type = request.getParameter("type");
				   httpSession.setAttribute("woId",woId);
				   httpSession.setAttribute("machId",machId);
				   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
				  // String tradeId=request.getParameter("hdncmbTradeId");
				   String jobType=request.getParameter("jobtype");
				   CommonFunctions.debugMsg(jobType+"  jobtype  " +"%%%%woId "+woId);
				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				 	commonFilter.setJobtype(jobTypeTrade);
				 	
				   httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
				   PrintWriter out = response.getWriter();
				   CommonFunctions.debugMsg("typetype"+type);
				   out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "CompleteWOCBM"));
				   			//
				   List<String []> monthlyPlanList  = monPlanEntryService.getMonPlan(commonFilter,type);
				   JSONObject jsonObject = getTableModel(monthlyPlanList,commonFilter.getEqpmnt());				 
				   
				   httpSession.removeAttribute("colComWoModel");
				   httpSession.setAttribute("colComWoModel", jsonObject);
				   System.out.println("in side col,,"+httpSession.getAttribute("colComWoModel"));
				   //out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "CompleteWO"));
				   
			   }
			   else if( action.equals("completeWO_getData.mpce") )
			   {
				   
				   CommonFilter commonFilter  = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				   String jobType=request.getParameter("jobtype");
				   String type=request.getParameter("type");

				    ComboFilter jobTypeTrade  = new ComboFilter();
				    if(BAL_UIUtils.isValidKeyId(jobType))
				    {
				    	jobTypeTrade.setId(jobType);
				    }
				 	commonFilter.setJobtype(jobTypeTrade);
				   
				   String woId = (String)httpSession.getAttribute("woId");
				   String viewChecked=(String)httpSession.getAttribute("viewChecked");
				   String weekN0 = (String)httpSession.getAttribute("weekno");
				   commonFilter.setWeekno(weekN0);

				   commonFilter.setWodetailid(woId);
				   if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						 
						  // commonFilter.setMonwise(" ");
						 // CommonFunctions.debugMsg("fromDate Else    :"+commonFilter.getFromDate()+"----"+commonFilter.getToDate());
				 	  }
				   		httpSession.removeAttribute("MonthlyPlanCommonFilter");
					  httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
					  List<String []> woCompGrid  = null;
					  PrintWriter out = response.getWriter();
					  JSONObject woCompGriddata = null;
					
					  woCompGrid  = monPlanEntryService.getAllwoCompData(commonFilter,viewChecked,type);
					  
					  woCompGriddata = BAL_UIUtils.convertToJqGridTableObject(woCompGrid,request,0,0);
					  //CommonFunctions.debugMsg(woCompGrid.size());
					  out.println(woCompGriddata);
					  //CommonFunctions.debugMsg("assmblyGriddata  "+woCompGriddata);
				   
			   }
			  
			   
			   else if( action.equals("completeWO_getExcel.mpce")){
				   
				   String tableModel=BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "CompleteWOCBMExcel");
					JSONObject tblJSONObj = JSONObject.fromString(tableModel);
					String weekNo=request.getParameter("weekNo"); 
					String month=request.getParameter("month");
					String machineid=request.getParameter("cmbMchid");
					CommonFunctions.debugMsg(" machineid>>>>>>>" +machineid);
					List<String []> machineList  = monPlanEntryService.getMachine(machineid);
					String machine=machineList.get(0)[0];
					CommonFunctions.debugMsg(machine +"machine,,,,,,,");
					
					request.setAttribute("excel", "excel");
					tblJSONObj.put("title", "PM Check Sheet for "+machine +"  " +month+ "  Week No."+weekNo);
					String format = ExcelUtils.getFormat(request);
					//String count = (String) httpSession.getAttribute("AbnDetailsCount");
					//GridParams gridParams =(GridParams) httpSession.getAttribute(keyId+"gridParams");
					CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);

					Workbook wb = monPlanEntryService.PmCheckListExportExcel(commonFilter,tblJSONObj,format);

					ExcelUtils.writeToResponse(response, wb, "PMDetails", format);
					
			   }/*
				   
				   
				   
					//HttpSession httpSession = request.getSession(false);
					CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					//commonFilter.setToRow(null);
					String fromMonth = commonFilter.getFromMonth();
					String toMonth = commonFilter.getToMonth();
					
					String month1   = fromMonth +"  -  "+ toMonth ;
					String fromdate = commonFilter.getFromDate();
					String todate = commonFilter.getToDate();
					String date   = fromdate +"  -  "+ todate ;
					CommonFunctions.debugMsg("In Side completeWO_getExcel.mpce   " +httpSession.getAttribute("colComWoModel"));
					//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
					JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("colComWoModel");
				
					
					//tblJSONObj.put("title", "Monthly Calender" + " - "+date);
					//if( commonFilter.getMonwise().equals("Y")){
					//	tblJSONObj.put("title", "Monthly Calender" + " - "+month1);
					//}
					//tblJSONObj.put("transpose",true); 
					//tblJSONObj.put("title", "Production Loss Analysis Report");
					String format = ExcelUtils.getFormat(request);
					
					Workbook wb = monPlanEntryService.PmCheckListExportExcel(commonFilter,tblJSONObj,format);
					commonFilter.setFromRow(tmpFromRow);
					
					ExcelUtils.writeToResponse(response, wb, "MonthlyCalender", format);
			   }
				   
				   /*
			    httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
				
				String tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkOrder", "CompleteWOCBM");
				httpSession.removeAttribute("colModel");
				 httpSession.setAttribute("colModel", tableModel);
				
				JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("colModel");
				
				String isHSE = request.getParameter("isHSE");			
				
				commonFilter.setAbnIsHSE(isHSE);	
				CommonFunctions.debugMsg("tblJSONObj    "  +tableModel);
				//tblJSONObj.put("title", "Abnormality Tag Trend Report - "+commonFilter.getDrillCaption()+" Wide ");
				//tblJSONObj.put("title", "PM Schedule ");
				String format = ExcelUtils.getFormat(request);
				 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  } 
				
				Workbook wb = monPlanEntryService.PmCheckListExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "PM_Check_List", format);
				
			}
			
			*/
			   
			   
			   
			   
			   /*End*/
			   else if( action.equals("functionalLoc.mpce")){
				   
				    BAL_FunctLocFieldNameBean functLocFieldNameBean = new BAL_FunctLocFieldNameBean();
					functLocFieldNameBean.setFactory("cmbgenwFactoryid");
					functLocFieldNameBean.setSection("cmbgenwSectionid");
					functLocFieldNameBean.setCell("cmbgenwCellid");
					functLocFieldNameBean.setMachine("cmbgenwMachineid");
					functLocFieldNameBean.setFactMandatory(true);
					functLocFieldNameBean.setSectMandatory(false);
					functLocFieldNameBean.setCellMandatory(true);
					functLocFieldNameBean.setMachMandatory(true);
					
					FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
					
					if( formModes == FormModes.completion)
						formModes = FormModes.view;
					
					BAL_UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
				   
			   }
			   
		}

		private void saveGDWODetails(HttpServletRequest request,HttpServletResponse response,BalWorkOrderDetailsBean workOrderDetailsBean) throws IOException {
			// TODO Auto-generated method stub
			System.out.println("inside Save WO GDRD DETAILS");
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
			try{
		    	if( httpSession != null && user != null)
		    	{	
		    		BalPlmTlWofeedback existPlmTlWofeedback = (BalPlmTlWofeedback)httpSession.getAttribute("plmTlWofeedback_Servlet"); 
		    		BalPlmTlWofeedback  nwPlmTlWofeedback  = new BalPlmTlWofeedback ();
		    	//	newPlmTlWofeedback =(PlmTlWofeedback)UIUtils.setBeanProperties((Object)newPlmTlWofeedback,request);
		    	//	PlmTlSpareconsumed newPlmTlSpareconsumed = new PlmTlSpareconsumed();
		    	//	PlmTlSparecostactual newPlmTlSparecostactual = new PlmTlSparecostactual();
		    		BalWorkOrderDetailsLstBean newworkOrderDetailsBean = new BalWorkOrderDetailsLstBean();
		    		//nwPlmTlWofeedback.setWofbCreatedby(user.getUsrm_ccno());
		    		
//		    		WorkOrderDetailsLstBean newworkOrderDetailsBean = new WorkOrderDetailsLstBean();
		    		BalWorkOrderFormBean newworkOrderFormBean = new BalWorkOrderFormBean();
		    		BalWorkOrderDetailsBean newWODetailsBean = new BalWorkOrderDetailsBean();
		    		newworkOrderFormBean =(BalWorkOrderFormBean) BAL_UIUtils.setBeanProperties((Object)newworkOrderFormBean,request);
				//	String planQuantity = request.getParameter("planQuantity");
				//	String sparename = request.getParameter("sparename");
				//	String spareId = request.getParameter("spareId");

				//	String actualQuantity = request.getParameter("actualQuantity");
					String gridData = request.getParameter("gridData");
					
				CommonFunctions.debugMsg("gridData in servlet    "+gridData);
					
					String grdmachId= request.getParameter("txtmachId");
					String frmDate = request.getParameter("dtFromMonth");
					String completedBy= request.getParameter("completedBycombo");
					String modeselected = request.getParameter("modeselected");
					CommonFunctions.debugMsg("gridData in modeselected    "+modeselected);
					if(request.getParameter("hdnMultiresp")== null || request.getParameter("hdnMultiresp").equals(""))
		    		{
			    	}
		    		else
		    		{
		    			
			    		String multiresp = request.getParameter("hdnMultiresp");
			    		CommonFunctions.debugMsg("multiresp" + multiresp);
			    		String wono=request.getParameter("txtWogenwoNo");
			    		JSONArray jsonArray = JSONArray.fromString(multiresp);		    	
			    		BalPlmTlMultipleResp plmTlMultipleResp = new BalPlmTlMultipleResp();
				    	nwPlmTlWofeedback.setPmstandId(wono);
				    	List<BalPlmTlMultipleResp> MultiResponse = (List<BalPlmTlMultipleResp>) BAL_UIUtils.convertJSONArrToList(plmTlMultipleResp, jsonArray);
				    	if(plmTlMultipleResp != null)
				    		nwPlmTlWofeedback.setplmTlMultipleResp(MultiResponse);
				    }
					/** For CBM **/
					String txtCmcdCurrentreading = request.getParameter("txtCmcdCurrentreading");
					String AdjustReading = request.getParameter("AdjustReading");
					String dteCmcdNextduedate = request.getParameter("dteCmcdNextduedate");
					String minimumReading = request.getParameter("minimumReading");
					String calenderIdCBM = request.getParameter("calenderIdCBM");
					String pmstdidCBM = request.getParameter("pmstdidCBM");
					String maximumReading = request.getParameter("maximumReading");
					
					workOrderDetailsBean =(BalWorkOrderDetailsBean) BAL_UIUtils.setBeanProperties((Object)workOrderDetailsBean,request);
					
					if(BAL_UIUtils.isValidKeyId(txtCmcdCurrentreading)){
						workOrderDetailsBean.setAdjustReading(AdjustReading);
						workOrderDetailsBean.setCurrentReading(txtCmcdCurrentreading);
						workOrderDetailsBean.setNextdueDate(dteCmcdNextduedate);
						workOrderDetailsBean.setMinmumReading(minimumReading);
						workOrderDetailsBean.setMaximumReading(maximumReading);
						workOrderDetailsBean.setPmCalendarId(calenderIdCBM);
						workOrderDetailsBean.setPmStdId(pmstdidCBM);
					}
					
					
					if(BAL_UIUtils.isValidKeyId(modeselected))
						newWODetailsBean.setModeModify(modeselected);
					List<BalWorkOrderDetailsLstBean> gridList = null;
					JSONArray gridjson = null;
					if(BAL_UIUtils.isValidKeyId(gridData)){
						CommonFunctions.debugMsg("griddata in side o &&&&&&  " +gridData);
				 	gridjson = JSONArray.fromString(gridData);
						gridList=(List<BalWorkOrderDetailsLstBean>) BAL_UIUtils.convertJSONArrToList(newworkOrderDetailsBean, gridjson);
						CommonFunctions.debugMsg("griddata in side o  :  "+gridList);
						newworkOrderFormBean.setStartDate(frmDate);
						newworkOrderFormBean.setWofbCreatedby(user.getUsrm_ccno());
						newworkOrderFormBean.setWofbCompletedby(completedBy);
						if(gridList!= null)
							newworkOrderFormBean.setGrdWoBean(gridList);
					}
					List<BalWorkOrderDetailsLstBean> gridfara = newworkOrderFormBean.getGrdWoBean();
					newworkOrderFormBean.setGrdWoBean(gridfara);
					newworkOrderDetailsBean =(BalWorkOrderDetailsLstBean) BAL_UIUtils.setBeanProperties((Object)newworkOrderDetailsBean,request);
					
					for(BalWorkOrderDetailsLstBean gridPickUp:gridfara)
					{
						gridPickUp.setMachineId(grdmachId);
						gridPickUp.setFromMonth(frmDate);
						
						newworkOrderDetailsBean.setFromMonth(gridPickUp.getFromMonth());
						newworkOrderDetailsBean.setPmCalendarId(gridPickUp.getPmCalendarId());
						newworkOrderDetailsBean.setPmstandId(gridPickUp.getPmstandId());
						newworkOrderDetailsBean.setWksmWodetailid(gridPickUp.getWksmWodetailid());
						newworkOrderDetailsBean.setWofbDuration(gridPickUp.getPlannedduration());
						newworkOrderDetailsBean.setWofbObservation(gridPickUp.getWofbObservation());
						
					}
					workOrderDetailsBean.setGrdWoBean(gridfara);
					CommonFunctions.debugMsg("End Of Save In MCe");
					//System.out.println("Duration  grid     :-" +nwPlmTlWofeedback.getWofbFeedbackid());
				/*	if(UIUtils.isValidKeyId(spareId)){
						newPlmTlSpareconsumed.setPspcQuantity(actualQuantity);
						newPlmTlSpareconsumed.setPspcSpareid(spareId);
					//	newPlmTlSparecostactual.setPscaDoctype(jobType);
						newPlmTlSparecostactual.setPscaQuantity(actualQuantity);
						newPlmTlSparecostactual.setPscaSparesid(spareId);
						 if(UIUtils.isValidKeyId(modeselected)){
							newworkOrderDetailsBean.setSpareConsumed(newPlmTlSpareconsumed);
							newworkOrderDetailsBean.setSpareCostActual(newPlmTlSparecostactual);
						 }
						 else{
							 workOrderDetailsBean.setSpareConsumed(newPlmTlSpareconsumed);
							 workOrderDetailsBean.setSpareCostActual(newPlmTlSparecostactual);
						 }
					
						// for getting value from spare Grid PlmTlSparecostactual
					}*/
						/**/
					    String saveMsg = null ;
						String resultCallBack  = null;
					    if(BAL_UIUtils.isValidKeyId(modeselected)){
					    resultCallBack  =monPlanEntryService.modifyCompWo(newworkOrderDetailsBean,completedBy);
						if(resultCallBack.equals("Success"))
								saveMsg = "Modified Successfully";
							else
								saveMsg = resultCallBack;
					   } 
					   else{
							resultCallBack  =monPlanEntryService.savegrdWOD(workOrderDetailsBean,newworkOrderFormBean,nwPlmTlWofeedback);

							if(resultCallBack.equals("Success"))
								saveMsg = "Data Saved Successfully";
							else
								saveMsg = "Data not Saved";//resultCallBack;
					   }
						
						JSONObject returnData = new JSONObject();
						JSONObject successData = new JSONObject();
						//successData.put("formClear",false);	
						successData.put("msg", saveMsg);
						returnData.put("successData",successData);
						returnData.put("formClear",false);
						out.print(returnData.toString());
						//out.print()	
						System.out.println("end of savell");
			    	}
			}
					
		    	
			catch(Exception e){
		    		
		    }
		}
					
	


		private void saveWODetails(HttpServletRequest request,HttpServletResponse response,BalWorkOrderDetailsBean workOrderDetailsBean) throws IOException {
			// TODO Auto-generated method stub
			System.out.println("inside SAve WO DETAILS");
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
			
			 /* direct Save Datas
			 String activityDesp = request.getParameter("freqData");
			  String machId = request.getParameter("machId");
			  String wodetailId = request.getParameter("woDetailId");
			  String pmCalId = request.getParameter("pmCalId");
			  String pmstdId = request.getParameter("pmstdId");
			  String duration = request.getParameter("duration");
			  String startDate = request.getParameter("startDate");
			  String jobType = request.getParameter("jobType");
			  String directSave= request.getParameter("directSave");
			  CommonFunctions.debugMsg("actdirectSave   :" +directSave);
			/* End */
			 String woId = (String)httpSession.getAttribute("woId");
			 String  wodetailId = (String)httpSession.getAttribute("woDetailId");
			 String  pmCalId = (String)httpSession.getAttribute("pmCalId");
			 String machId = (String)httpSession.getAttribute("machId");
			 String fromMonth = (String)httpSession.getAttribute("FromMonth");
			 String assmId = (String)httpSession.getAttribute("assmId");
			 String	 pmstdId = (String)httpSession.getAttribute("pmstdId");
			 String	 jobType = (String)httpSession.getAttribute("jobType");
			 String locn=BAL_UIUtils.getlocation(request);
			 workOrderDetailsBean.setDBLocation(locn);
			 
				try{
			    	if( httpSession != null && user != null)
			    	{	
			    		BalPlmTlWofeedback existPlmTlWofeedback = (BalPlmTlWofeedback)httpSession.getAttribute("plmTlWofeedback_Servlet"); 
			    		BalPlmTlWofeedback  newPlmTlWofeedback  = new BalPlmTlWofeedback ();
			    		BalPlmTlSpareconsumed newPlmTlSpareconsumed = new BalPlmTlSpareconsumed();
			    		BalPlmTlSparecostactual newPlmTlSparecostactual = new BalPlmTlSparecostactual();
			    		newPlmTlWofeedback.setWofbCreatedby(user.getUsrm_ccno());
			    		newPlmTlWofeedback =(BalPlmTlWofeedback)BAL_UIUtils.setBeanProperties((Object)newPlmTlWofeedback,request);
						workOrderDetailsBean =(BalWorkOrderDetailsBean) BAL_UIUtils.setBeanProperties((Object)workOrderDetailsBean,request);
						if(BAL_UIUtils.isValidKeyId(pmstdId))
							workOrderDetailsBean.setPmStdId(pmstdId);
						if(BAL_UIUtils.isValidKeyId(woId))
							workOrderDetailsBean.setWOmstId(woId);
						if(BAL_UIUtils.isValidKeyId(assmId))
							workOrderDetailsBean.setAssmbleyId(assmId);
						if(BAL_UIUtils.isValidKeyId(fromMonth))
							workOrderDetailsBean.setFromMonth(fromMonth);
						if(BAL_UIUtils.isValidKeyId(pmCalId))
							workOrderDetailsBean.setPmCalendarId(pmCalId);
						if(BAL_UIUtils.isValidKeyId(wodetailId))
							newPlmTlWofeedback .setWofbWodetailid(wodetailId);
						if(BAL_UIUtils.isValidKeyId(machId))
							newPlmTlWofeedback .setWofbMachineid(machId);
						CommonFunctions.debugMsg("after setting data ");
						String planQuantity = request.getParameter("planQuantity");
						String sparename = request.getParameter("sparename");
						String spareId = request.getParameter("spareId");
						String actualQuantity = request.getParameter("actualQuantity");
						
						if(BAL_UIUtils.isValidKeyId(spareId)){
							newPlmTlSpareconsumed.setPspcQuantity(actualQuantity);
							newPlmTlSpareconsumed.setPspcSpareid(spareId);
							newPlmTlSparecostactual.setPscaDoctype(jobType);
							newPlmTlSparecostactual.setPscaQuantity(actualQuantity);
							newPlmTlSparecostactual.setPscaSparesid(spareId);
							newPlmTlWofeedback.setSpareConsumed(newPlmTlSpareconsumed);
							newPlmTlWofeedback.setSpareCostActual(newPlmTlSparecostactual);
						}
						String saveMsg = null ;
						/* for getting value from spare Grid PlmTlSparecostactual*/
						
						/**/
						if( ! BAL_UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbFeedbackid()) )
						{	
							existPlmTlWofeedback =	monPlanEntryService.saveWOD(newPlmTlWofeedback,existPlmTlWofeedback,workOrderDetailsBean);
							saveMsg = "Data Saved Successfully";
						}
						JSONObject returnData = new JSONObject();
						JSONObject successData = new JSONObject();
						
						successData.put("msg", saveMsg);
						returnData.put("successData",successData);
						out.print(returnData.toString());
						//out.print()	
			    	}
				}catch(ValidationExceptions e)
				{
					JSONObject errMessage = BAL_UIUtils.validationExceptions(e.toString(),  "WorkOrderDetailsException");
					out.print(errMessage.toString());
				}catch(Exception e){
			  
				}
			}

		private JSONObject getTableModel(List<String[]> headers, String assEqpWise)
		{
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String [] monthArr = headers.get(0);
			String [] weekArr = headers.get(1);
			int colFlag = monthArr.length -1;
			int colLength=0;
			CommonFunctions.debugMsg("Count Array "+colFlag);
			String [] colHeader = new String[ colFlag];
			String [] colHeader2 = new String[ colFlag];
			String [] colHeader3 = new String[ colFlag];
			if("ASS".equals(assEqpWise)){
			colHeader3[0] = colHeader2[0] = " ";
			colHeader3[1] = colHeader2[1] = "Location_id";
			colHeader3[2] = colHeader2[2] = "Unit_id";
			colHeader3[3] = colHeader2[3] = "Section_id";
			colHeader3[4] = colHeader2[4] = "Line_id";
			colHeader3[5] = colHeader2[5] = "Equipment_id";
			colHeader3[6] = colHeader2[6] = "Assembly_id";
			colHeader3[7] = colHeader2[7] = "Location";
			colHeader3[8] = colHeader2[8] = "Factory";
			colHeader3[9] = colHeader2[9] = "Section";
			colHeader3[10] = colHeader2[10] = "Line";
			colHeader3[11] = colHeader2[11] = "Equipment Name";
			colHeader3[12] = colHeader2[12] = "Equipment No.";
			colHeader3[13] = colHeader2[13] = "Assembly";
			colHeader[14]="";
			}
			else if("EQP".equals(assEqpWise))
			{
				colHeader3[0] = colHeader2[0] = " ";
				colHeader3[1] = colHeader2[1] = "Location_id";
				colHeader3[2] = colHeader2[2] = "Unit_id";
				colHeader3[3] = colHeader2[3] = "Section_id";
				colHeader3[4] = colHeader2[4] = "Line_id";
				colHeader3[5] = colHeader2[5] = "Equipment_id";
				colHeader3[6] = colHeader2[6] = "Location";
				colHeader3[7] = colHeader2[7] = "Factory";
				colHeader3[8] = colHeader2[8] = "Section";
				colHeader3[9] = colHeader2[9] = "Line";
				colHeader3[10] = colHeader2[10] = "Equipment Name";
				colHeader3[11] = colHeader2[11] = "Equipment No.";
			}
			colHeader[0] = colHeader[1]=colHeader[2] = colHeader[3]=colHeader[4] =colHeader[5]=colHeader[6] = colHeader[7]=colHeader[8] = colHeader[9]=colHeader[10] = colHeader[11]= colHeader[12]=colHeader[13]= colHeader[14]= "";//colHeader[13]= colHeader[14]="";
			List<JqGridColModel>jqGridColModelList =new ArrayList<JqGridColModel>();
			JqGridColModel jqGridColModel =getColModel("sno",40,"left");
			if("ASS".equals(assEqpWise))
			{
			//JqGridColModel jqGridColModel =getColModel("sno",40,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);
			jqGridColModel =getColModel("location_id",80,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("factory_id",60,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("line_id",150,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("cell_id",150,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("machine_id",150,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("assembly_id",80,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("Location",80,"left");
			//jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("factory",60,"left");
			//jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("line",150,"left");
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("cell",150,"left");
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("machinename",150,"left");
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("machineno",80,"left");
			jqGridColModelList.add(jqGridColModel);
			jqGridColModel =getColModel("assembly",80,"left");
			jqGridColModelList.add(jqGridColModel);
			colLength=14;
			}
			else if("EQP".equals(assEqpWise))
			{
				
				jqGridColModel.setHidden(true);
				jqGridColModelList.add(jqGridColModel);
				jqGridColModel =getColModel("location_id",80,"left");
				jqGridColModel.setHidden(true);
				jqGridColModelList.add(jqGridColModel);	
				jqGridColModel =getColModel("factory_id",60,"left");
				jqGridColModel.setHidden(true);
				jqGridColModelList.add(jqGridColModel);	
				jqGridColModel =getColModel("line_id",150,"left");
				jqGridColModel.setHidden(true);
				jqGridColModelList.add(jqGridColModel);	
				jqGridColModel =getColModel("cell_id",150,"left");
				jqGridColModel.setHidden(true);
				jqGridColModelList.add(jqGridColModel);	
				jqGridColModel =getColModel("machine_id",150,"left");
				jqGridColModel.setHidden(true);
				jqGridColModelList.add(jqGridColModel);	
				jqGridColModel =getColModel("Location",80,"left");
				//jqGridColModel.setHidden(true);
				jqGridColModelList.add(jqGridColModel);	
				jqGridColModel =getColModel("factory",60,"left");
				//jqGridColModel.setHidden(true);
				jqGridColModelList.add(jqGridColModel);	
				jqGridColModel =getColModel("line",150,"left");
				jqGridColModelList.add(jqGridColModel);	
				jqGridColModel =getColModel("cell",150,"left");
				jqGridColModelList.add(jqGridColModel);	
				jqGridColModel =getColModel("machinename",150,"left");
				jqGridColModelList.add(jqGridColModel);
				jqGridColModel =getColModel("machineno",80,"left");
				jqGridColModelList.add(jqGridColModel);
				colLength=12;
			}
		//	jqGridTableModel.getRowHeaders().add(colHeader);
		
			for(int i =colLength; i < monthArr.length-1; i++)
			{
				

				if(i!= monthArr.length-2 || i != monthArr.length-3)
					jqGridColModelList.add(getColModel(weekArr[i]+monthArr[i],40,"center"));
				else
				{
					jqGridColModel =getColModel("Key"+i,80,"left");
					jqGridColModel.setHidden(true);
				}
				colHeader[i] = "";
				colHeader2[i] = monthArr[i];
				colHeader3[i] = weekArr[i];				
			}
			
			jqGridTableModel.setTableButton(true);		 
			//jqGridTableModel.setTableHeight(290);
			//jqGridTableModel.setTableWidth(800);
			jqGridTableModel.setRowNumbers(true);
		
			jqGridTableModel.setColModel(jqGridColModelList);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.getRowHeaders().add(colHeader2);
			jqGridTableModel.getRowHeaders().add(colHeader3);		
		
			
			JSONObject tableModel = BAL_UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableWidth","110%%");
			tableModel.set("tableHeight","79%%");

			return tableModel;
		}
		private JSONObject getTableModel1(List<String[]> headers) {
			// TODO Auto-generated method stub
			
		    JqGridTableModel jqGridTableModel = new JqGridTableModel();
			String[] row = headers.get(1);
			String[] colHeader = headers.get(0);
			String[] colHeader1 = headers.get(1);
			
			String[] tempCol = new String[row.length];
			jqGridTableModel.getRowHeaders().add(tempCol);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.getRowHeaders().add(colHeader1);
			
			jqGridTableModel.setRowNumbers(false);
			
			for (int i = 0; i < colHeader.length; i++) {
				System.out.println("Inside colmodel"+colHeader.length);
				System.out.println("colHeader1[i]"+colHeader1[i]);
				
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(tempCol[i] = "");
				jqGridColModel.setIndex(tempCol[i].replaceAll(" ", ""));
				jqGridColModel.setName(tempCol[i].replaceAll(" ", ""));
			    jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "")+"col"+i);
				jqGridColModel.setName(colHeader1[i].replaceAll(" ", "")+"col"+i);		
				
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				
			   if(i==0)
			   {
				   jqGridColModel.setWidth(336);
			   }
			   if(i==1){
					jqGridColModel.setWidth(236);
					}
		         if(i>=2){
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			}
                	
				jqGridTableModel.getColModel().add(jqGridColModel);
			}
			
			JSONObject tableModel = BAL_UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "80%%");
			tableModel.set("tableWidth", "95%%");
			return tableModel;
		}

		private JqGridColModel getColModel (String colIndex, int width,String allign)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex);
			jqGridColModel.setName(colIndex);
			jqGridColModel.setWidth( width);				
			jqGridColModel.setAlign(allign);
			jqGridColModel.setEditable(false);
			//jqGridColModel.setFormatter("actionformatter_"+colIndex);			
			return jqGridColModel;
		}
		
		private  JSONObject fillJqGrid(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, CommonFilter comFilter)
		{
			String rowsStr = request.getParameter("rows");
			String pageStr = request.getParameter("page");
			long totalRecords = comFilter.getTotalRecordCnt();
			
			int rows = 100;
			if( rowsStr != null)
				rows = Integer.parseInt(rowsStr);
			
			int page = 1;
			if( pageStr != null)
				page = Integer.parseInt(pageStr);
		
			JSONObject tableDataObject = new JSONObject();
			
			 CommonFunctions.debugMsg(" ****Assembly Wise "+comFilter.getEqpmnt());
			 int flgAE = 0;
			 if("ASS".equals(comFilter.getEqpmnt()))
				 flgAE=13;
			 else if("EQP".equals(comFilter.getEqpmnt()))
				 flgAE=11;
				 
			
			tableDataObject.put("page", page); //current page
			tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
			tableDataObject.put("records", totalRecords); //total records
			
			JSONArray rowArr = new JSONArray(); 
		
			int rowId = rows*(page-1);
			int rowStartFlag = rowId + 2;
			
			//int rowId = 0;		
			
	        for( String [] datasRow : dataArrayList)
			{
	        	JSONObject rowObj =new JSONObject();
    			JSONArray cell=new JSONArray();
    			if( rowId >= rowStartFlag )
		        {	
		        	rowObj.put("id",rowId -rowStart +1);
	        		for( int i = colStart ;i < datasRow.length-1; i++)
		        	{	
	        			if(datasRow[i].equals("1")){
	        			}
	        			if(i>flgAE)
		        		 datasRow[i] = datasRow[i].replace("2","IC").replace("3","A").replace("0"," ");	
	        			 cell.put(  ( datasRow[i] != null ? datasRow[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", "").replace("(", "").replace(")", ""):" ") );
		        	}
	        		rowObj.put("cell",cell);
	    	    	rowArr.put(rowObj);
		        }
	        	rowId++;
			}
	        tableDataObject.put("rows", rowArr);

			 CommonFunctions.debugMsg(" ****Assembly Wise "+tableDataObject);
	        return tableDataObject;
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
			CommonFunctions.debugMsg(commonFilter.getEqpmnt());
			return commonFilter;
		}
		
	private void saveObservation(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
		
			try{
		    	if( httpSession != null && user != null)
		    	{	
		    		BalPlmTlCalendar plmTlCalendar = new BalPlmTlCalendar();		    		
		    		plmTlCalendar =(BalPlmTlCalendar)BAL_UIUtils.setBeanProperties((Object)plmTlCalendar,request);
		    		plmTlCalendar.setPmclCreatedby(user.getUsrm_ccno());		    		
					String observationsStr = request.getParameter("Observations");					
					if(BAL_UIUtils.isValidKeyId(observationsStr)){
						BalWoObservation woObservation = new BalWoObservation();
						JSONArray observationsJsonArr = JSONArray.fromString(observationsStr);
						List<BalWoObservation> observationsList = (List<BalWoObservation>) BAL_UIUtils.convertJSONArrToList(woObservation, observationsJsonArr);
						plmTlCalendar.setWoObservations(observationsList);
					}
					String saveMsg = null ;					
					monPlanEntryService.saveObservations(plmTlCalendar);
					saveMsg = "Data Saved Successfully";
					JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();					
					successData.put("msg", saveMsg);
					returnData.put("successData",successData);
					out.print(returnData.toString());
					//out.print()	
					System.out.println("end of save");
		    	}
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = BAL_UIUtils.validationExceptions(e.toString(),  "WorkOrderDetailsException");
				out.print(errMessage.toString());
			}catch(Exception e){
		  
		}
	}
}