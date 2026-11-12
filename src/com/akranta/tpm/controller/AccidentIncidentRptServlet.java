package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.SheAccIncBean;
import com.akranta.tpm.bean.SheActionPlanBean;
import com.akranta.tpm.bean.SheTlIncidentEmployeetBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.JhaTlAuditreportdtl;
import com.akranta.tpm.model.JhaTlAuditreportmst;
import com.akranta.tpm.model.PlmTlConditionalappraisal;
import com.akranta.tpm.model.SheTlAccinvestigationdtl;
import com.akranta.tpm.model.SheTlAccinvestigationmst;
import com.akranta.tpm.model.SheTlActionplan;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.model.SheTlIncidentmst;
import com.akranta.tpm.service.AccidentIncidentRptingService;
import com.akranta.tpm.service.AccidentInvestigationService;
import com.akranta.tpm.service.impl.AccidentIncidentRptingServiceImpl;
import com.akranta.tpm.service.impl.AccidentInvestigationServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class AccidentIncidentRptServlet extends HttpServlet {
	AccidentIncidentRptingService  accidentIncidentRptingService ;
	AccidentInvestigationService accidentInvestigationService;
	  public AccidentIncidentRptServlet() {
	        super();
	     
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
			  String action = UIUtils.getActionPart(request) ;
		      ComboFilter comboFilter = new ComboFilter();
			  try {
				  accidentIncidentRptingService = (AccidentIncidentRptingServiceImpl)UIUtils.getServiceObject(request,"AccidentIncidentRptingServiceImpl");
				  accidentInvestigationService=(AccidentInvestigationServiceImpl)UIUtils.getServiceObject(request,"AccidentInvestigationServiceImpl");
				} catch (ServiceObjectCreationException e) {
					CommonFunctions.debugMsg(e);
				}
				
			   if( action.equals("safteyincident_input.accIncRpt") ){	
				   
				   String keyid = request.getParameter(ReqtParamNameConst.KEYID);
				   String bookingMode = request.getParameter("bookingMode");
				   String status = request.getParameter("pending");
				   if(UIUtils.isValidKeyId(bookingMode))
				   {
					   request.setAttribute("bookingMode", bookingMode);
				   }
				   //keyid="INC08120006";
				   String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
				   FormModes mode = FormModes.create;	
				   SheTlIncidentmst sheTlIncidentmst = null;
				   httpSession.removeAttribute("sheTlIncidentMst");
				   if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
						
						String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
						CommonFunctions.debugMsg(formMode + "...........");
						if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
						{
							mode =  FormModes.modify;
						}else if( formMode.equals( FormModeConsts.view)){
							CommonFunctions.debugMsg( "INSIDE VIEW MODE");
							mode=FormModes.view;
						}
						
						sheTlIncidentmst = accidentIncidentRptingService.select(keyid);
						
						if( UIUtils.isValidKeyId(status ))
						{
							if(status.equals("actionPlan"))
							{
								request.setAttribute("openPop","actionPlan");
							}
						}
						//setIncidentFormValues(sheTlIncidentmst);
						httpSession.setAttribute("sheTlIncidentMst" , sheTlIncidentmst);
						//request.setAttribute("sheTlIncidentMst", sheTlIncidentmst);
					}
				   else
				   {
					   sheTlIncidentmst = new SheTlIncidentmst();
					   AdmTlUsermst user = UIUtils.getLoginUser(request);
					   sheTlIncidentmst.setSincReportedBy(user.getUsrm_ccno());
				   }
				   SheAccIncBean sheAccIncBean = new SheAccIncBean(mode);
				   
				   String filePath = UIUtils.getImagePath(request);					
					try
					{	 
						 System.out.println("Inside image service");
						 String fileName=UIUtils.TPM_TEMPIMG_DIR;
						 sheAccIncBean = accidentIncidentRptingService.getImage(fileName,filePath,sheAccIncBean,sheTlIncidentmst);
						 
				    }
					catch(Exception e)
					{
						System.out.println("Exception in while Selecting Image");   
					}
					
				    
				    httpSession.removeAttribute("formMode");
					httpSession.setAttribute("formMode",mode);
				    request.setAttribute("formMode", mode);
				    request.setAttribute("sheAccIncBean", sheAccIncBean);
				    request.setAttribute("sheTlIncidentMst", sheTlIncidentmst);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/accidentIncidentReporting.jsp"); 
					rd.forward(request, response); 
					
			   }else if(action.equals("filterXmlAccincident_input.accIncRpt")){
				   response.setContentType("xml");		
					UIUtils.forwardRequest(request, response, "/tiles/xml/AccIncdnt.xml");
			   }
			   else if( action.equals("Accincident_input.accIncRpt") ){	
				   	String filterStr = request.getParameter("filterString");
					request.setAttribute("filterStr", filterStr);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/IncidentReportingMaster.jsp"); 
					rd.forward(request, response); 
			   }
			   else if(action.equals("Accincident_getCol.accIncRpt")){
					PrintWriter out = response.getWriter();
					JSONObject jsonObjectd = null;
					CommonFilter commonFilter = populateCommonFilter(request,"IncRptCommonFilter",true);	
					
					if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	  }
					
					commonFilter.setSafetyMode("Create");
					List<String[]> incidentList  = accidentIncidentRptingService.getAllIncidents( commonFilter);
					jsonObjectd = UIUtils.convertToJqGridTableObject(incidentList,request,1,0,commonFilter.getTotalRecordCnt());

					JSONObject jsonObject = getTableModel(incidentList);
					jsonObject.set("tableHeight", "64%%");
				    jsonObject.set("tableWidth", "106%%");
					httpSession.removeAttribute("IncdntColModel");
			 	    httpSession.setAttribute("IncdntColModel",jsonObject);
			 	   
					out.println(jsonObject);
				    /*CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.AccidentIncidentReporting", "colModelEmployeeDtls"));
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.AccidentIncidentReporting", "colModelEmployeeDtls"));*/ 
			   }
			   else if(action.equals("Accincident_getData.accIncRpt")){
				   try
					{	  
					   System.out.println("Inside  Data action");
						PrintWriter out = response.getWriter();
						UIUtils.displayRequestParamsValue(request);
						String page = request.getParameter("page");	  
						httpSession = request.getSession();
						CommonFilter commonFilter = populateCommonFilter(request,"IncRptCommonFilter",false);	
						commonFilter.setSafetyMode("Create");
						JSONObject jsonObject = null;
						System.out.println("test local addr..."+ request.getLocalAddr());
		        		CommonFunctions.debugMsg("Total Count : "+commonFilter.getTotalRecordCnt());
		        		List<String[]> incList  = accidentIncidentRptingService.getAllIncidents( commonFilter);
		        		CommonFunctions.debugMsg("List Size......"+commonFilter.getTotalRecordCnt());
		        		 jsonObject = UIUtils.convertToJqGridTableObject(incList,request,2,0,commonFilter.getTotalRecordCnt()+1);
		        		 commonFilter.setViewClick('N');	  		
		        		 httpSession.removeAttribute("IncRptCommonFilter");
		 	  			httpSession.setAttribute("IncRptCommonFilter", commonFilter);
						 out.println(jsonObject);
							  			 	
					}
					catch(Exception e)
					{
						CommonFunctions.debugMsg("error " +e.getMessage());
					}
					/* try
					{	
					   PrintWriter out = response.getWriter();
					   SheTlIncidentEmployee sheTlIncidentEmployee =new SheTlIncidentEmployee();
					   String incidentKey = request.getParameter("incidentKey");
					   CommonFunctions.debugMsg("Incident Key : "+incidentKey);
					   sheTlIncidentEmployee.setSiemSincKeyid(incidentKey);
					  // sheTlIncidentEmployee.setBflkBachKeyid(bflkBachKeyid);
					   List<String []> batchFacultyList  = accidentIncidentRptingService.getIncidentEmployee(sheTlIncidentEmployee);
		  			   JSONObject batchFacultyData = UIUtils.convertToJqGridTableObject(batchFacultyList,request,0,0);
		  			   CommonFunctions.debugMsg(batchFacultyData);
		  			   out.println(batchFacultyData);
					
					}catch(Exception e)
					{
						CommonFunctions.debugMsg(e.getMessage());
					}*/
			   }
			   else if(action.equals("Accincident_getExcel.accIncRpt") || (action.equals("AccincidentModify_getExcel.accIncRpt")))
			   {
				 
					CommonFilter commonFilter = populateCommonFilter(request,"IncRptCommonFilter",false);
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);		
					JSONObject tblJSONObj = new JSONObject();
					if(action.indexOf("Modify")>0)
						tblJSONObj = (JSONObject)httpSession.getAttribute("IncidentColModel");
					else
						tblJSONObj = (JSONObject)httpSession.getAttribute("IncdntColModel");
					tblJSONObj.put("title", "AccidentIncident Report");
					String format = ExcelUtils.getFormat(request);
					CommonFunctions.debugMsg("tblJSONObj...."+tblJSONObj);
					Workbook wb = accidentIncidentRptingService.accidentIncidentReportExportExcel(commonFilter,tblJSONObj,format);
					commonFilter.setFromRow(tmpFromRow);
					
					ExcelUtils.writeToResponse(response, wb, "IncidentBooking", format);
			   }
			   
			   else if(action.equals("AccidentInvestRpt_input.accIncRpt"))
				{	
					CommonFunctions.debugMsg("input....");
					RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/AccidentInvestRpt.jsp"); 
					rd.forward(request, response); 
				}
				else if(action.equals("AccidentInvestRpt_getCol.accIncRpt"))
				{	
					PrintWriter out = response.getWriter();			
					CommonFunctions.debugMsg("getCol");
					try 
						{
						 String firstClick =request.getParameter("firstClick");
						 CommonFilter  commonFilter  ;
						 commonFilter = populateCommonFilter(request,"accidentCommonFilter",true);			
						
						 List<String[]> accidentInvestList = accidentIncidentRptingService.getAccidentInvest(commonFilter);
							JSONObject ApData= UIUtils.convertToJqGridTableObject(accidentInvestList,request,3,0,commonFilter.getTotalRecordCnt());
							CommonFunctions.debugMsg("accidentInvestList   "+accidentInvestList.size());
							JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
							GridColModel gridColModel = new GridColModel();
							
							jqGridTableModel.setRowNumbers(true);
							jqGridTableModel.setEnableFilter(true);
							jqGridTableModel.setTableButton(true);
							
							gridColModel.setHeaderNum(1);
							
							String [] colHeader = accidentInvestList.get(1);			
							String [] colHeaderCond = accidentInvestList.get(0);
							
							List<String[]> headers = new ArrayList<String[]>();
							headers.add(colHeader);
							
							JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
							colModel.put("data", ApData);
							CommonFunctions.debugMsg("colModel   "+colModel);
							
							//JSONObject jsonObject = getTableModel(accidentInvestList,commonFilter);
							colModel.set("tableHeight", "75%%");
							colModel.set("tableWidth", "107%%");
							httpSession.removeAttribute("accidentCommonFilter");
							httpSession.setAttribute("accidentCommonFilter",commonFilter);
							httpSession.removeAttribute("accidentColModel");
							httpSession.setAttribute("accidentColModel",colModel);
							out.println(colModel);
						} 
					catch (Exception e) 
						{
							e.printStackTrace();
						}
				}
				else if(action.equals("AccidentInvestRpt_getData.accIncRpt"))
				{	
					try
					{	
						CommonFunctions.debugMsg("getCol");
						PrintWriter out = response.getWriter();
						CommonFilter commonFilter = populateCommonFilter(request,"accidentCommonFilter",false);
						List<String[]> accidentInvestList = accidentIncidentRptingService.getAccidentInvest(commonFilter);
						
		  			 	JSONObject calendarData = UIUtils.convertToJqGridTableObject(accidentInvestList,request,2,0,commonFilter.getTotalRecordCnt());
		  			 	CommonFunctions.debugMsg(calendarData);
		  			 	out.println(calendarData);
					}catch(Exception e)
					{
						CommonFunctions.debugMsg(e.getMessage());
					}
				}
			   
			   
			   else if( action.equals("AccidentInvestRpt_getExcel.accIncRpt")){
					
					//HttpSession httpSession = request.getSession(false);
					CommonFilter commonFilter = populateCommonFilter(request,"accidentCommonFilter",false);
					commonFilter.setViewClick('Y');
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
					JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("accidentColModel");
					
					tblJSONObj.put("title", "Accident Investigation Report");
					String format = ExcelUtils.getFormat(request);
					
					Workbook wb = accidentIncidentRptingService.getAccidentInvestExportExcel(commonFilter,tblJSONObj,format);
					commonFilter.setFromRow(tmpFromRow);
					
					ExcelUtils.writeToResponse(response, wb, "AccidentInvestigationReport", format);
					
				}
			   else if(action.equals("filterXmlAccincidentModify_input.accIncRpt")){
				   response.setContentType("xml");		
					UIUtils.forwardRequest(request, response, "/tiles/xml/AccIncdnt.xml");
			   }
			   else if( action.equals("AccincidentModify_input.accIncRpt") ){	
				   	String filterStr = request.getParameter("filterString");
					request.setAttribute("filterStr", filterStr);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/IncidentReportingMaster.jsp"); 
					rd.forward(request, response); 
			   }
			   else if( action.equals("AccincidentModify_getCol.accIncRpt") ){	
				   	PrintWriter out = response.getWriter();
					CommonFilter commonFilter = populateCommonFilter(request,"IncidentRptCommonFilter",true);	
					
					if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	  }
					
					commonFilter.setSafetyMode("Modify");
					List<String[]> incidentList  = accidentIncidentRptingService.getAllIncidents( commonFilter);
					JSONObject incidentData = UIUtils.convertToJqGridTableObject(incidentList,request,2,0,commonFilter.getTotalRecordCnt()+1); 
					httpSession.setAttribute("incidentDataServlet", incidentData);				

					JSONObject jsonObject = getTableModel(incidentList);
					jsonObject.set("tableHeight", "70%%");
				    jsonObject.set("tableWidth", "106%%");
					httpSession.removeAttribute("IncidentColModel");
			 	    httpSession.setAttribute("IncidentColModel",jsonObject);
			 	   
					out.println(jsonObject);
			   }
			   else if( action.equals("AccincidentModify_getData.accIncRpt") ){	
			   try
				{	  
					PrintWriter out = response.getWriter();
					UIUtils.displayRequestParamsValue(request);
					String page = request.getParameter("page");	  
					CommonFilter commonFilter = populateCommonFilter(request,"IncidentRptCommonFilter",false);
					commonFilter.setSafetyMode("Modify");
					//CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("IncidentRptCommonFilter");			
					JSONObject jsonObject = new JSONObject();
					CommonFunctions.debugMsg("PAGE : "+page);

						jsonObject = (JSONObject) httpSession.getAttribute("incidentDataServlet");

		        		CommonFunctions.debugMsg("Total Count : "+commonFilter.getTotalRecordCnt());
		        		List<String[]> woReqList  = accidentIncidentRptingService.getAllIncidents( commonFilter);
		        		jsonObject = UIUtils.convertToJqGridTableObject(woReqList,request,2,0,commonFilter.getTotalRecordCnt()+1);
					 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
				}
				catch(Exception e)
				{
					CommonFunctions.debugMsg("error " +e.getMessage());
				}
			   }
			   else if(action.equals("filterXmlAccincidentView_input.accIncRpt")){
				   response.setContentType("xml");		
					UIUtils.forwardRequest(request, response, "/tiles/xml/AccIncdnt.xml");
			   }
			   else if( action.equals("AccincidentView_input.accIncRpt") ){	
				   	String parentId = request.getParameter("cmbinjuryType");
				   	String fromMonth = request.getParameter("dtFromMonth");
					String toMonth = request.getParameter("dtToMonth");
					String filterStr = request.getParameter("filterString");
				   	request.setAttribute("cmbinjuryType", parentId);
					request.setAttribute("dtFromMonth", fromMonth);
					request.setAttribute("dtToMonth", toMonth);
					request.setAttribute("filterStr", filterStr);
					CommonFunctions.debugMsg("filterStr"+filterStr);
					CommonFunctions.debugMsg("filterStr--"+fromMonth+"-"+toMonth+"-"+parentId);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/IncidentViewRpt.jsp"); 
					rd.forward(request, response); 
			   }
			   else if( action.equals("AccincidentView_getCol.accIncRpt") ){	
				   	
				   PrintWriter out = response.getWriter();
					CommonFilter commonFilter = populateCommonFilter(request,"IncidentRptCommonFilter",true);
					
					if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	  }
					
					
					List<String[]> incidentList  = accidentIncidentRptingService.getAllIncidents( commonFilter);
					CommonFunctions.debugMsg("incidentList size ....."+incidentList.size());
					JSONObject incidentData = UIUtils.convertToJqGridTableObject(incidentList,request,1,0,commonFilter.getTotalRecordCnt()+1); 
					httpSession.setAttribute("incidentDataServlet", incidentData);				

					JSONObject jsonObject = getTableModel(incidentList);
					jsonObject.set("tableHeight", "76%%");
					jsonObject.set("tableWidth", "106%%");
					jsonObject.put("loadOnce", false);
					httpSession.removeAttribute("IncidentColModel");
			 	    httpSession.setAttribute("IncidentColModel",jsonObject);
			 	   
					out.println(jsonObject);
			   }
			   else if( action.equals("AccincidentView_getData.accIncRpt") ){	
			   try
				{	  
				  
				   
				   PrintWriter out = response.getWriter();
					UIUtils.displayRequestParamsValue(request);
					String page = request.getParameter("page");	  
					CommonFilter commonFilter = populateCommonFilter(request,"IncidentRptCommonFilter",false);
					
					List<String[]> woReqList  = accidentIncidentRptingService.getAllIncidents( commonFilter);
					 
					/*int rowCount = woReqList.size();
					CommonFunctions.debugMsg("count...."+rowCount);
					Long totalCnt = (long)rowCount;
					commonFilter.setTotalRecordCnt(totalCnt);*/

					//CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("IncidentRptCommonFilter");			
					JSONObject jsonObject = new JSONObject();
					CommonFunctions.debugMsg("PAGE : "+page);
					//if(page.equals("1"))
						//jsonObject = (JSONObject) httpSession.getAttribute("incidentDataServlet");
		        	//else
		        	//{
		        		CommonFunctions.debugMsg("Total Count : "+commonFilter.getTotalRecordCnt());
		        		//List<String[]> woReqList  = accidentIncidentRptingService.getAllIncidents( commonFilter);
		        		jsonObject = UIUtils.convertToJqGridTableObject(woReqList,request,2,0,commonFilter.getTotalRecordCnt()+1);
		        		
		        	//}
		        		
		        		CommonFunctions.debugMsg("Length...."+jsonObject.toString());
						
		        		jsonObject.put("msg","Success");
						
						JSONObject returnData = new JSONObject();
						returnData.put("successData", jsonObject);
						returnData.put("rows", jsonObject);
						
						httpSession.removeAttribute("IncidentRptCommonFilter");
						httpSession.setAttribute("IncidentRptCommonFilter" , commonFilter);
						
						httpSession.removeAttribute("totalCnt");
						httpSession.setAttribute("totalCnt" , commonFilter.getTotalRecordCnt());
					 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
				}
				catch(Exception e)
				{
					CommonFunctions.debugMsg("error " +e.getMessage());
				}
			   }

			   else if(action.equals("AccincidentView_getExcel.accIncRpt") || (action.equals("AccincidentModify_getExcel.accIncRpt")))
			   {
				    CommonFilter commonFilter = populateCommonFilter(request,"IncidentRptCommonFilter",false);
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);		
					JSONObject tblJSONObj = new JSONObject();
					if(action.indexOf("Modify")>0)
						tblJSONObj = (JSONObject)httpSession.getAttribute("IncidentColModel");
					else
						tblJSONObj = (JSONObject)httpSession.getAttribute("IncidentColModel");
					tblJSONObj.put("title", "AccidentIncident Report");
					String format = ExcelUtils.getFormat(request);
					CommonFunctions.debugMsg("tblJSONObj...."+tblJSONObj);
					Workbook wb = accidentIncidentRptingService.accidentIncidentReportExportExcel(commonFilter,tblJSONObj,format);
					commonFilter.setFromRow(tmpFromRow);
					
					ExcelUtils.writeToResponse(response, wb, "IncidentBookingView", format);
			   }		
			   else if( action.equals("safteyincident_save.accIncRpt"))
				{	
					CommonFunctions.debugMsg("Saveeeeeee");
					String saveMode = request.getParameter("saveMode");
					String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
					CommonFunctions.debugMsg("Saveeeeeee : "+formMode);
					SheAccIncBean sheAccIncBean = new  SheAccIncBean();
					if(UIUtils.isValidKeyId(saveMode))
						sheAccIncBean.setSaveMode(saveMode);
					if(UIUtils.isValidKeyId(formMode))
						sheAccIncBean.setFormMode(formMode);
					saveAccidentInc(request,response,sheAccIncBean);
					
			    }
			   else if( action.equals("SheEmployeeAdd_input.accIncRpt") ){	
				    String incidentKey = request.getParameter("incdtKeyId");
				    CommonFunctions.debugMsg("Key : "+incidentKey);
				    if(UIUtils.isValidKeyId(incidentKey))
				    	request.setAttribute("incidentId", incidentKey);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/SHEEmployeepopUp.jsp"); 
					rd.forward(request, response); 
					 
			   } 
			   else if(action.equals("SheEmployeeAdd_getCol.accIncRpt")){
				    CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.AccidentIncidentReporting", "colModelEmployeeDtls"));
				    String gridType = request.getParameter("gridType");
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.AccidentIncidentReporting", gridType)); 
			   }
			   else if(action.equals("SheEmployeeAdd_getData.accIncRpt")){
				   try
					{	
					   PrintWriter out = response.getWriter();
					   SheTlIncidentEmployee sheTlIncidentEmployee =new SheTlIncidentEmployee();
					   String incidentKey = request.getParameter("incidentKey");
					   String siemEmpmKeyid=request.getParameter("siemEmpmKeyid");
					   String siemInjuryModeid=request.getParameter("siemInjuryModeid");
					   CommonFunctions.debugMsg("Incident Key : "+incidentKey);
					   sheTlIncidentEmployee.setSiemSincKeyid(incidentKey);
					   sheTlIncidentEmployee.setSiemEmpmKeyid(siemEmpmKeyid);
					   sheTlIncidentEmployee.setSiemInjuryModeid(siemInjuryModeid);
					  // sheTlIncidentEmployee.setBflkBachKeyid(bflkBachKeyid);
					   List<String []> employeeList  = new ArrayList<String[]>();
					   if(UIUtils.isValidKeyId(sheTlIncidentEmployee.getSiemSincKeyid()))
						   employeeList =  accidentIncidentRptingService.getIncidentEmployee(sheTlIncidentEmployee);
					   if(employeeList.size()>0)
					   {
						   JSONObject employeesData = UIUtils.convertToJqGridTableObject(employeeList,request,0,0);
						   CommonFunctions.debugMsg(employeesData);
						   out.println(employeesData);
					   }
					
					}catch(Exception e)
					{
						CommonFunctions.debugMsg(e.getMessage());
					}
			   }
			   
			   else if(action.equals("SheEmployeeAdd_reCall.accIncRpt"))
			   {
				   String keyId = request.getParameter("keyId");
				   CommonFunctions.debugMsg("KeyId...."+keyId);
				   
				   System.out.println("Recall function");
					PrintWriter out = response.getWriter();
					SheTlIncidentEmployee sheTlIncidentEmployee = accidentIncidentRptingService.recallEmployee(keyId);
					
				
					//httpSession.setAttribute("newsheTlIncidentEmployee", sheTlIncidentEmployee);
					
					JSONObject employeeData = UIUtils.fromTpmModel(sheTlIncidentEmployee);
					System.out.println("Recall value:"+employeeData);			
					
					
					JSONObject returndata = new JSONObject();
					returndata.put("employeeData", employeeData);					
					System.out.println("Data:"+returndata);
					out.print(returndata);
					
					
					
			   }
			   
			   else if(action.equals("SheActionPlan_reCall.accIncRpt"))
			   {
				   String keyId = request.getParameter("keyId");
				   CommonFunctions.debugMsg("KeyId...."+keyId);   
				   PrintWriter out = response.getWriter();
				   SheTlActionplan sheTlActionplan = accidentIncidentRptingService.recallAction(keyId);
				   if(UIUtils.isValidKeyId(sheTlActionplan.getSacpTargetDate()))
				   {
					   if(sheTlActionplan.getSacpTargetDate().indexOf(":")>0)
						   sheTlActionplan.setSacpTargetDate(sheTlActionplan.getSacpTargetDate().substring(0, sheTlActionplan.getSacpTargetDate().indexOf(" ")));
					   if(sheTlActionplan.getSacpTargetDate().equals(Constants.passNullDate))
							  sheTlActionplan.setSacpTargetDate("");
				   }
				   if(UIUtils.isValidKeyId(sheTlActionplan.getSacpCompletedDate()))
				   {
					   if(sheTlActionplan.getSacpCompletedDate().indexOf(":")>0)
						   sheTlActionplan.setSacpCompletedDate(sheTlActionplan.getSacpCompletedDate().substring(0, sheTlActionplan.getSacpCompletedDate().indexOf(" ")));
					   if(sheTlActionplan.getSacpCompletedDate().equals(Constants.passNullDate) || sheTlActionplan.getSacpCompletedDate().equals(Constants.futureNullDate))
							  sheTlActionplan.setSacpCompletedDate("");					  
				   }
				   JSONObject actionPlanData = UIUtils.fromTpmModel(sheTlActionplan);				  			
				   JSONObject returndata = new JSONObject();
				   returndata.put("actionPlanData", actionPlanData);		
				   System.out.println(returndata);
				   out.print(returndata);
				}
			   else if(action.equals("ActionPlan_getCol.accIncRpt")){
				    PrintWriter out = response.getWriter();
				    String gridType = request.getParameter("gridType");
				    CommonFunctions.debugMsg("gridType..."+gridType);
				    
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.AccidentIncidentReporting", gridType)); 
			   }
			   else if(action.equals("ActionPlan_getData.accIncRpt")){
				    

				   try
					{	
					   PrintWriter out = response.getWriter();
					   SheTlActionplan sheTlActionplan =new SheTlActionplan();
					   String incidentKey = request.getParameter("incidentKey");
					   CommonFunctions.debugMsg("Incident Key : "+incidentKey);
					   sheTlActionplan.setSacpSincKeyid(incidentKey);
					  // sheTlIncidentEmployee.setBflkBachKeyid(bflkBachKeyid);
					   if(UIUtils.isValidKeyId(incidentKey))
					   {
						   List<String []> batchFacultyList  = accidentIncidentRptingService.getIncidentActionPlan(sheTlActionplan);
			  			   JSONObject batchFacultyData = UIUtils.convertToJqGridTableObject(batchFacultyList,request,0,0);
			  			   CommonFunctions.debugMsg(batchFacultyData);
			  			   out.println(batchFacultyData);
					   }
					
					}catch(Exception e)
					{
						CommonFunctions.debugMsg(e.getMessage());
					}
					
			   }
			   
			   else if( action.equals("ActionPlan_Excelview.accIncRpt") )/*Created By Siddharth.A*/ 
				{
			
					try{
						CommonFilter commonFilter = new CommonFilter();					
						String insId = request.getParameter("InsId");
						String format = ExcelUtils.getFormat(request);
						commonFilter = FilterValues.getCommonFilters(request, commonFilter);
						commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
						
						String path = UIUtils.getExcelTemplatePath(request);  	
						String imagePath = UIUtils.getImagePath(request);
						
						format = "xlsx";
						
						Workbook wb = accidentIncidentRptingService.getAccInvestExportExcel(format,path,imagePath,commonFilter,insId);
						
			 
						ExcelUtils.writeToResponse(response, wb, "AccidentIncedentReport", format);
						}
					
						catch(Exception e)
						{
							System.out.println("err:"+e.getMessage());
							PrintWriter out = response.getWriter();
							JSONObject err = new JSONObject();				
							//err.put("exception",true);				
							err.put("message" ,"Data Not Found" );
							out.print(err.toString());
						}
						
					
		}
			   else if(action.equals("ActionPlan_Delete.accIncRpt")){
				   SheActionPlanBean sheActionPlanBean=new SheActionPlanBean();
				   deleteActionPlan(request,response,sheActionPlanBean);
			   }
			   else if(action.equals("safteyincident_delete.accIncRpt"))
			   {
				   CommonFunctions.debugMsg("Saveeeeeee");
					String saveMode = request.getParameter("saveMode");
					String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
					CommonFunctions.debugMsg("Saveeeeeee : "+formMode);
					SheAccIncBean sheAccIncBean = new  SheAccIncBean();
					if(UIUtils.isValidKeyId(saveMode))
						sheAccIncBean.setSaveMode(saveMode);
					if(UIUtils.isValidKeyId(formMode))
						sheAccIncBean.setFormMode(formMode);
					deleteAccidentInc(request,response,sheAccIncBean);
			   }
			   else if( action.equals("SheActionPlanAdd_input.accIncRpt") ){	
				   AdmTlUsermst user = new AdmTlUsermst();
				   request.setAttribute("compBy", user.getUsrm_ccno());
					RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/ActionPlanpopUp.jsp"); 
					rd.forward(request, response); 					 
			   } 
			   else if(action.equals("SheActionPlanAdd_Save.accIncRpt")){
				   SheActionPlanBean sheActionPlanBean=new SheActionPlanBean();				   
				   saveActionPlan(request,response,sheActionPlanBean);
			   }
 
			   else if(action.equals("SheEmployeeAdd_Save.accIncRpt")){
				   SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean=new SheTlIncidentEmployeetBean();
				   CommonFunctions.debugMsg("SheEmployeeAdd_Save.accIncRpt");
				   saveincidentEmployee(request,response,sheTlIncidentEmployeetBean);
			   }
			   else if(action.equals("SheEmployeeAdd_Delete.accIncRpt")){
				   SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean=new SheTlIncidentEmployeetBean();
				   deleteincidentEmployee(request,response,sheTlIncidentEmployeetBean);
			   }
			 
			   else if( action.equals("functionalLoc.accIncRpt"))
				{
					FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
					functLocFieldNameBean.setLocation("cmbsincLocnkeyid");
					functLocFieldNameBean.setFactory("cmbsincFactkeyid");
					functLocFieldNameBean.setSection("cmbsincSectkeyid");
					functLocFieldNameBean.setCell("cmbsincCellkeyid");
					functLocFieldNameBean.setMachine("cmbsincMachkeyid");
					functLocFieldNameBean.setLocnMandatory(true);
					functLocFieldNameBean.setFactMandatory(false);
					functLocFieldNameBean.setSectMandatory(false);
					functLocFieldNameBean.setCellMandatory(false);
					functLocFieldNameBean.setMachMandatory(false);
					
					FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
					
					if( formModes == FormModes.completion)
						formModes = FormModes.view;
					else if(formModes.equals(FormModes.modify))
						formModes = FormModes.view;
					
					UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
				
				}
			   else if(action.equals("InjuryCombo.accIncRpt"))
			   {
				   try 
					{
					   	CommonFilter commonFilter=new CommonFilter();
					   	comboFilter = UIUtils.fillComboFilter(request);
						List<ComboBox>  DetectedBy = accidentIncidentRptingService.getInjuryComboList(commonFilter, comboFilter);
						UIUtils.writeComboBox(response, DetectedBy,comboFilter);
					} 
					catch (Exception e) 
					{
						
						e.printStackTrace();
					}
			   }
			   else if(action.equals("TypeCombo.accIncRpt"))
			   {
				   try 
					{
					   	CommonFilter commonFilter=new CommonFilter();
					   	comboFilter = UIUtils.fillComboFilter(request);
						List<ComboBox>  DetectedBy = accidentIncidentRptingService.getTypeComboList(commonFilter, comboFilter);
						UIUtils.writeComboBox(response, DetectedBy,comboFilter);
					} 
					catch (Exception e) 
					{
						
						e.printStackTrace();
					}
			   }
			   else if(action.equals("BodyPartCombo.accIncRpt"))
			   {
				   try 
					{
					   	CommonFilter commonFilter=new CommonFilter();
					   	comboFilter = UIUtils.fillComboFilter(request);
						List<ComboBox>  DetectedBy = accidentIncidentRptingService.getBodyPartComboList(commonFilter, comboFilter);
						UIUtils.writeComboBox(response, DetectedBy,comboFilter);
					} 
					catch (Exception e) 
					{
						
						e.printStackTrace();
					}
			   }
			   else if(action.equals("IncidentTypeCombo.accIncRpt"))
			   {
				   try 
					{
					   	CommonFilter commonFilter=new CommonFilter();
					   	comboFilter = UIUtils.fillComboFilter(request);
						List<ComboBox>  DetectedBy = accidentIncidentRptingService.getIncidentTypeComboList(commonFilter, comboFilter);
						UIUtils.writeComboBox(response, DetectedBy,comboFilter);
					} 
					catch (Exception e) 
					{
						
						e.printStackTrace();
					}
			   } 
			   else if(action.equals("accInvestigatnFormGrid_view.accIncRpt")){
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/AccInvestigatnFormGrid.jsp"); 
				   rd.forward(request, response);
			   }
			   else if(action.equals("accInvestigatnFormGrid_getCol.accIncRpt")){
				   PrintWriter out = response.getWriter();
					CommonFilter commonFilter=populateCommonFilter(request, "accInvestigatnFormGrid",true);
					List<String[]> accInvestGrid = null;
					try {
						accInvestGrid = accidentInvestigationService.getAccInvestMainGrid(commonFilter);
					} catch (Exception e) {
						e.printStackTrace();
					}
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
					String [] colHeader = accInvestGrid.get(2);			
					String [] colHeaderCond = accInvestGrid.get(1);
					gridColModel.setHeaderNum(1);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.set("tableWidth", "105%%");
					jsonObject.set("tableHeight", "75%%");
					out.println(jsonObject);
			   }
			   else if(action.equals("accInvestigatnFormGrid_getData.accIncRpt")){
				   try {
						CommonFilter commonFilter=populateCommonFilter(request, "accInvestigatnFormGrid",false);
						List<String[]> conditionalGrid = accidentInvestigationService.getAccInvestMainGrid(commonFilter);
						PrintWriter out = response.getWriter();
						JSONObject conditionaljson = UIUtils.convertToJqGridTableObject(conditionalGrid, request, 3, 0);
						out.println(conditionaljson);
					} catch (Exception e) {
						e.printStackTrace();
					}
			   }
			   else if( action.equals("accInvestigatnFormGrid_getExcel.accIncRpt"))
				{
					CommonFilter commonFilter=populateCommonFilter(request, "accInvestigatnFormGrid",false);
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);	
					JSONObject colmodel = UIUtils.getXlColModel(request, response);
					colmodel.put("title","Accident Investigation");
					String format = ExcelUtils.getFormat(request);
					Workbook wb = accidentInvestigationService.getExcel(colmodel,format,commonFilter);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb, "AccidentInvestigation", format);
				}
			   else if( action.equals("getModeaccInvestigatnFormGrid_view.accIncRpt")){
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					JSONObject result = new JSONObject();
					result.put("mode","create");
					result.put("url","accInvestigatnForm_input.accIncRpt");
					result.put("formHeader","Accident Investigation Entry");
					out.println(result);
				}else if(action.equals("accInvestigatnForm_input.accIncRpt")){
					String keyid = request.getParameter("keyid");
					CommonFunctions.debugMsg("keyid "+keyid);
					AdmTlUsermst user = UIUtils.getLoginUser(request);
					SheTlAccinvestigationmst sheTlAccinvestigationmst = new SheTlAccinvestigationmst();
					if (UIUtils.isValidKeyId(keyid)) {
						sheTlAccinvestigationmst= accidentInvestigationService.getAllData(keyid);
						sheTlAccinvestigationmst.setAinmAccdate(UIUtils.getActualDateForm(sheTlAccinvestigationmst.getAinmAccdate()));
					}else{
						sheTlAccinvestigationmst.setAinmResponsibility(user.getUsrm_ccno());
					}
					request.setAttribute("sheTlAccinvestigationmst", sheTlAccinvestigationmst);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/AccInvestigatnForm.jsp"); 
					rd.forward(request, response);
				}else if(action.equals("accInvestigatnForm_getCol.accIncRpt")){
					PrintWriter out = response.getWriter();
					String keyid=request.getParameter("keyid");
					CommonFilter commonFilter=populateCommonFilter(request, "accInvestigatnForm",true);
					if(UIUtils.isValidKeyId(keyid))
						commonFilter.setKey(keyid);
					List<String[]> accInvestGrid = null;
					try {
						accInvestGrid = accidentInvestigationService.getAccInvestDtl(commonFilter);
					} catch (Exception e) {
						e.printStackTrace();
					}
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(false);
					jqGridTableModel.setGridEdit(true);
					
					String [] colHeader = accInvestGrid.get(2);			
					String [] colHeaderCond = accInvestGrid.get(1);
					gridColModel.setHeaderNum(1);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.set("tableWidth", "100%%");
					jsonObject.set("tableHeight", "25%%");
					out.println(jsonObject);
				}
				else if(action.equals("accInvestigatnForm_getData.accIncRpt")){
					try {
						String keyid=request.getParameter("keyid");
						CommonFilter commonFilter=populateCommonFilter(request, "accInvestigatnForm",false);
						if(UIUtils.isValidKeyId(keyid))
							commonFilter.setKey(keyid);
						List<String[]> conditionalGrid = accidentInvestigationService.getAccInvestDtl(commonFilter);
						PrintWriter out = response.getWriter();
						JSONObject conditionaljson = UIUtils.convertToJqGridTableObject(conditionalGrid, request, 3, 0);
						out.println(conditionaljson);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(action.equals("accInvestigatnForm_save.accIncRpt")){
					saveAccInvestForm(request,response);
				}else if(action.equals("accInvestigatnForm_delete.accIncRpt")){
					deleteAccInvestForm(request,response);
				}else if(action.equals("accInvestigatnFormDtl_delete.accIncRpt")){
					deleteDtlAccInvestForm(request,response);
				}
			   else if(action.equals("AccidentInvesticatoinRpt_input.accIncRpt")){
				   //UIUtils.forwardRequest(request, response, "/pages/SHE/AccidentInvestigationRpt.jsp");
				   String filterStr = request.getParameter("filterString");
				   request.setAttribute("filterStr", filterStr);
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/AccidentInvestigationRpt.jsp"); 
				   rd.forward(request, response);
			   }
			   else if(action.equals("AccidentInvesticatoinRpt_getCol.accIncRpt")){
				   accidentInvestigationgetCol(request, response);
				   
			   }
			   else if(action.equals("AccidentInvesticatoinRpt_getData.accIncRpt")){
				   accidentInvestigationgetData(request, response);  		 
		       }
			   else if(action.equals("AccidentInvesticatoinRpt_getExcel.accIncRpt")){
				   accidentInvestigationgetExcel(request, response);  		 
		       }
			   else if(action.equals("functionalLocAccInves.accIncRpt"))
				{
					FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
					functLocFieldNameBean.setCompany("cmbComp");
					functLocFieldNameBean.setSbu("cmbSbu");
					functLocFieldNameBean.setPbu("cmbPbu");
					functLocFieldNameBean.setSection("cmbSectionid");
					functLocFieldNameBean.setCell("cmbCellid");
					functLocFieldNameBean.setMachine("cmbMachineid");
					functLocFieldNameBean.setFunctionalLocId("cmbAinmFlid");
					functLocFieldNameBean.setFactMandatory(false);
					functLocFieldNameBean.setMachMandatory(false);
					functLocFieldNameBean.setLocnMandatory(true);
					functLocFieldNameBean.setSectMandatory(false);
					functLocFieldNameBean.setCellMandatory(false);
					FormModes formModes = FormModes.create;
					UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
				}
		}			

		private void deleteDtlAccInvestForm(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			boolean clearFrm=true;
			
			String accidentInvestDtl = request.getParameter("accidentInvestDtl");
			if( httpSession != null && user != null)
			{
				JSONObject successData=new JSONObject();
				JSONObject returnData=new JSONObject();
				CommonFunctions.debugMsg("accidentInvestDtl  "+accidentInvestDtl);
				List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl=new ArrayList<SheTlAccinvestigationdtl>();
				if(UIUtils.isValidKeyId(accidentInvestDtl)){
					JSONArray accidentInvestDtlJsonArr = JSONArray.fromString(accidentInvestDtl);
					SheTlAccinvestigationdtl sheTlAccinvestigationdtl=new SheTlAccinvestigationdtl();
					listSheTlAccinvestigationdtl=(List<SheTlAccinvestigationdtl>)UIUtils.convertJSONArrToList(sheTlAccinvestigationdtl, accidentInvestDtlJsonArr);
				}
				String savemsg;		
				try
				{
					if(listSheTlAccinvestigationdtl.size()>0){
						listSheTlAccinvestigationdtl=accidentInvestigationService.deleteDtl(listSheTlAccinvestigationdtl);
						savemsg=" Data Deleted Succesfully";
					}else
						savemsg=" No Data to Delete";
					
					returnData.put("formClear", clearFrm);
		        	successData.put("msg", savemsg);
		        	returnData.put("mstDelete", "N");
		        	returnData.put("successData", successData);
		    		out.print(returnData.toString());
				}
		    	catch(Exception e)
				{
		    		CommonFunctions.debugMsg("Inside Exceptions");
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Deleted");
					out.print(err.toString());
					e.printStackTrace();
				}
			}
		}


		private void deleteAccInvestForm(HttpServletRequest request,
				HttpServletResponse response) throws Exception{
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);

			if( httpSession != null && user != null)
			{  
				SheTlAccinvestigationmst sheTlAccinvestigationmst=new SheTlAccinvestigationmst();
				SheTlAccinvestigationmst  existSheTlAccinvestigationmst =(SheTlAccinvestigationmst)httpSession.getAttribute("genTlConditionalappraisal");
				sheTlAccinvestigationmst=(SheTlAccinvestigationmst)UIUtils.setBeanProperties((Object)sheTlAccinvestigationmst,request);
				JSONObject successData=new JSONObject();
				JSONObject returnData=new JSONObject();
				sheTlAccinvestigationmst.setAinmCreatedby(user.getUsrm_ccno());
				
				String savemsg;		
				try
				{
					if(UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmKeyid()))
				 	{
						existSheTlAccinvestigationmst=accidentInvestigationService.delete(sheTlAccinvestigationmst);
						 savemsg=" Data Deleted Succesfully";
					 }
					 else{
						 savemsg= "Data Not Deleted";
					 }
					returnData.put("formClear", true);
					returnData.put("mstDelete", "Y");
		        	successData.put("msg", savemsg);
		        	returnData.put("successData", successData);
		    		out.print(returnData.toString());
				}
		    	catch(Exception e)
				{
		    		CommonFunctions.debugMsg("Inside Exceptions");
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Deleted");
					out.print(err.toString());
					e.printStackTrace();
				}
			}
		}


		private void saveAccInvestForm(HttpServletRequest request,
				HttpServletResponse response)throws Exception {
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String elementid=request.getParameter("hdnelementId");
			boolean clearFrm=true;
			String accidentInvestDtl = request.getParameter("accidentInvestDtl");
			if( httpSession != null && user != null)
			{  
				SheTlAccinvestigationmst sheTlAccinvestigationmst=new SheTlAccinvestigationmst();
				SheTlAccinvestigationmst  existSheTlAccinvestigationmst =(SheTlAccinvestigationmst)httpSession.getAttribute("genTlConditionalappraisal");
				sheTlAccinvestigationmst=(SheTlAccinvestigationmst)UIUtils.setBeanProperties((Object)sheTlAccinvestigationmst,request);
				JSONObject successData=new JSONObject();
				JSONObject returnData=new JSONObject();
				sheTlAccinvestigationmst.setAinmCreatedby(user.getUsrm_ccno());
				sheTlAccinvestigationmst.setAinmElementid(elementid);
				CommonFunctions.debugMsg("accidentInvestDtl  "+accidentInvestDtl);
				if(UIUtils.isValidKeyId(accidentInvestDtl)){
					JSONArray accidentInvestDtlJsonArr = JSONArray.fromString(accidentInvestDtl);
					List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl=new ArrayList<SheTlAccinvestigationdtl>();
					SheTlAccinvestigationdtl sheTlAccinvestigationdtl=new SheTlAccinvestigationdtl();
					listSheTlAccinvestigationdtl=(List<SheTlAccinvestigationdtl>)UIUtils.convertJSONArrToList(sheTlAccinvestigationdtl, accidentInvestDtlJsonArr);
					
					if(listSheTlAccinvestigationdtl.size()>0){
						sheTlAccinvestigationmst.setSheTlAccinvestigationdtl(listSheTlAccinvestigationdtl);
					}
				}
				String savemsg;		
				try
				{
					if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmKeyid()))
				 	{
						existSheTlAccinvestigationmst=accidentInvestigationService.create(sheTlAccinvestigationmst, existSheTlAccinvestigationmst);
						 savemsg=" Data Saved Succesfully";
					 }
					 else{
						 clearFrm=false;
						 existSheTlAccinvestigationmst=accidentInvestigationService.update(sheTlAccinvestigationmst, existSheTlAccinvestigationmst);
						 savemsg= "Data Updated Succesfully";
					 }
					returnData.put("formClear", clearFrm);
		        	successData.put("msg", savemsg);
		        	returnData.put("successData", successData);
		    		out.print(returnData.toString());
				}catch (ValidationExceptions e) {
					CommonFunctions.debugMsg("Inside ValidationExceptions");
					net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "AccInvestigationValidation");
					e.printStackTrace();
					out.print(errMessage.toString());
		    	}
		    	catch(Exception e)
				{
		    		CommonFunctions.debugMsg("Inside Exceptions");
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
					e.printStackTrace();
				}
			}
		}


		private void deleteAccidentInc(HttpServletRequest request,HttpServletResponse response, SheAccIncBean sheAccIncBean) throws Exception 
		{			
			
			HttpSession httpSession = request.getSession(false);
			PrintWriter outdel = response.getWriter();

			AdmTlUsermst user = UIUtils.getLoginUser(request);

			if( httpSession != null && user != null)
			{	
				SheTlIncidentmst sheTlIncidentmst = new SheTlIncidentmst();
				String rowId=request.getParameter("rowId");
				
				sheAccIncBean =(SheAccIncBean)UIUtils.setBeanProperties((Object)sheAccIncBean,request);
				try{
							String keyId=request.getParameter("keyId");
							sheTlIncidentmst.setSincKeyid(keyId);
							SheTlIncidentmst delStaus = accidentIncidentRptingService.delete(sheTlIncidentmst);
							JSONObject returnData = new JSONObject();					
							returnData.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));		
							returnData.put("formClear",true);
							outdel.print(returnData.toString());
							outdel.close();
					}
				
				
				catch(ValidationExceptions e)
				{
					PrintWriter  outexdel = response.getWriter();
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ActionPlan");
					
					errMessage.put("formActionMode",sheAccIncBean.getFormActionMode());				
					outexdel.print(errMessage.toString());
					
				}
				catch (Exception e)
				{
					CommonFunctions.debugMsg("err...."+e.toString());
					PrintWriter  outexdel = response.getWriter();
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"AccidentIncidentReporting");					
									
					outexdel.print("Completed Data Should Not Delete");
					
				}
				
			}
			
		}


		private void accidentInvestigationgetCol(HttpServletRequest request,HttpServletResponse response) throws Exception {
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"AccidentInvRptCommonFilter", true);
			CommonFunctions.debugMsg("getColEntry:::::: ");
			List<String[]> accRptList = accidentIncidentRptingService.getAccidentInvRpt(commonFilter);
			JSONObject accRptIndexData= UIUtils.convertToJqGridTableObject(accRptList,request, 2, 0, commonFilter.getTotalRecordCnt());
             
			httpSession.setAttribute("AccInvReport", accRptIndexData);	
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);			
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = accRptList.get(2);			
			String [] colHeaderCond = accRptList.get(1);
	
			
			List<String[]> headers = new ArrayList<String[]>();
			
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "65%%");
		
			jsonObject.put("data", accRptIndexData);
			
			CommonFunctions.debugMsg(jsonObject);
			httpSession.removeAttribute("AccInvReport");
			httpSession.setAttribute("AccInvReport", jsonObject);
			out.println(jsonObject);
			
		}
		private void accidentInvestigationgetData(HttpServletRequest request,HttpServletResponse response) {
			try
			{
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(false);
				UIUtils.displayRequestParamsValue(request);
				CommonFilter commonFilter  = populateCommonFilter(request,"AccidentInvRptCommonFilter",false);
				List<String[]> accRptList = accidentIncidentRptingService.getAccidentInvRpt(commonFilter);
			 	JSONObject accRptIndexData = UIUtils.convertToJqGridTableObject(accRptList, request,3, 0, commonFilter.getTotalRecordCnt()+3);	
			 	out.println(accRptIndexData);
				httpSession.removeAttribute("AccidentInvRptCommonFilter");
  			 	httpSession.setAttribute("AccidentInvRptCommonFilter", commonFilter);

	           }catch(Exception e)
		         {
					System.out.println("getdata Exception :"+e.getMessage());
					e.printStackTrace();
		         }
	       }
		private void accidentInvestigationgetExcel(HttpServletRequest request,HttpServletResponse response) throws SQLException, Exception {
			    HttpSession httpSession = request.getSession(false);
			    CommonFilter commonFilter = populateCommonFilter(request,"AccidentInvRptCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("AccInvReport");
				colmodel.put("title","ACCIDENT INVESTIGATION REPORT");
			    String format = ExcelUtils.getFormat(request);
				Workbook wb = accidentIncidentRptingService.AccInvRptExcel(commonFilter,colmodel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response,wb,"AccInvReport", format);
		}

		private JSONObject getTableModel(List<String[]> headers,	CommonFilter commonFilter) {
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String [] colHeader = headers.get(0);			
			jqGridTableModel.getRowHeaders().add(colHeader);
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			String headerSql = "'SELECT ";
			for(int i =0; i < colHeader.length; i++)
			{
				
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setEditable(false);
				
				
				if(i==0 || i==colHeader.length-1 || i==3 ||i==4 || i==5 || i==8 || i==11 ||i==12 || i==18 || i==19)
				{	jqGridColModel.setHidden(true);
					if(i==18)
						jqGridColModel.setKey(true);				
				}
				else if(i==1 || i==2 || i==9 ||i==13 || i==14 || i==17)
				{	
					 jqGridColModel.setWidth(120);
					 jqGridColModel.setAlign("left");
				}
				else if(i==6 || i==7  || i==10||i==15)
				{	
					 jqGridColModel.setWidth(80);
					 jqGridColModel.setAlign("center");
				}
				else if(i==16 )
				{	
					 jqGridColModel.setWidth(80);
					 jqGridColModel.setAlign("left");
				}
				jqGridTableModel.getColModel().add(jqGridColModel);
				jqGridTableModel.setTableButton(true);
				headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
			}
			headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
			
			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 return tableModel;
		}


		private void saveAccidentInc(HttpServletRequest request,HttpServletResponse response, SheAccIncBean sheAccIncBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		SheTlIncidentmst existSheTlIncidentmst = (SheTlIncidentmst)httpSession.getAttribute("sheTlIncidentMst");
	    		SheTlIncidentmst newSheTlIncidentmst = new SheTlIncidentmst();
	    		
	    		newSheTlIncidentmst.setSincCreatedby(user.getUsrm_ccno());
	    		
	    		newSheTlIncidentmst =(SheTlIncidentmst)UIUtils.setBeanProperties((Object)newSheTlIncidentmst,request);
	    		sheAccIncBean = (SheAccIncBean)UIUtils.setBeanProperties((Object)sheAccIncBean,request);
	    		
	    		
	    		String imgOplPresentImgFilename= request.getParameter("imgPresentImgFilename");
				String imgOplAfterImgFilename =request.getParameter("imgAfterImgFilename");
				
				String imagePath = UIUtils.getImagePath(request);
				
				List <GenTlAllmoduleimgfile> imgfileList =new ArrayList<GenTlAllmoduleimgfile>();
				
				if( imgOplPresentImgFilename != null){
					GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
					genTlAllmoduleimgfile.setImflBlobimage(imagePath );
					genTlAllmoduleimgfile.setImflFilename(imgOplPresentImgFilename);
					genTlAllmoduleimgfile.setImflImagetype("PRE");
					genTlAllmoduleimgfile.setImflRefdoctype("INC");
					imgfileList.add(genTlAllmoduleimgfile);
					System.out.println(" genTlAllmoduleimgfile.setToimBlobimage( " + genTlAllmoduleimgfile.getImflBlobimage());
				}
				
				if( imgOplAfterImgFilename != null){
					GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
					genTlAllmoduleimgfile.setImflBlobimage(imagePath );
					genTlAllmoduleimgfile.setImflFilename(imgOplAfterImgFilename);
					genTlAllmoduleimgfile.setImflImagetype("AFT");
					genTlAllmoduleimgfile.setImflRefdoctype("INC");
					imgfileList.add(genTlAllmoduleimgfile);
				}
				
				newSheTlIncidentmst.setAllmoduleimgfile(imgfileList) ;	
				
				
				
				
	    		String bookingMode = request.getParameter("hdnBookingMode");
	    		
	    		try{
	    			boolean insert = true;
					if(  newSheTlIncidentmst.getSincKeyid() == null )
					{	
						existSheTlIncidentmst =	accidentIncidentRptingService.create(newSheTlIncidentmst,existSheTlIncidentmst,sheAccIncBean);
					}	
					else
					{   CommonFunctions.debugMsg(".....Inside update mode ...");
						if(UIUtils.isValidKeyId(bookingMode) && bookingMode.equals("Booking"))
							insert = true;
						else
							insert = false;
						existSheTlIncidentmst = accidentIncidentRptingService.update(newSheTlIncidentmst,existSheTlIncidentmst,sheAccIncBean);						
					}
					
					httpSession.setAttribute(existSheTlIncidentmst.getSincKeyid(), existSheTlIncidentmst);
					httpSession.removeAttribute("sheTlIncidentmst");
					httpSession.setAttribute("sheTlIncidentmst", existSheTlIncidentmst);
					
					String formBeanIdentifier = "SheAccIncBean"+sheAccIncBean.getFormActionMode();					
					httpSession.setAttribute(formBeanIdentifier,sheAccIncBean);
					
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("AccKeyid",existSheTlIncidentmst.getSincKeyid());
					persistentData.put("fromBean", formBeanIdentifier);
					
					JSONObject forwardData = new JSONObject();
					JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();
					CommonFunctions.debugMsg("Save Mode : "+ sheAccIncBean.getSaveMode());
					if(UIUtils.isValidKeyId(sheAccIncBean.getSaveMode()))
					{
						if(sheAccIncBean.getSaveMode().equals("ADDEMP"))
						{
							returnData.put("formClear",false);
							returnData.put("displyMsg",false);
							returnData.put("afterSave","openPop");							
						}
						else if(sheAccIncBean.getSaveMode().equals("YY"))
						{
							if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincKeyid()))
								forwardData.put("cmbwwmsRefdocno",existSheTlIncidentmst.getSincKeyid());
							//if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincOccurredDate()))
								//forwardData.put("dtewwmsDate",existSheTlIncidentmst.getSincOccurredDate());
							if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincMachKeyid()))
								forwardData.put("cmbwwmsMachineid",existSheTlIncidentmst.getSincMachKeyid());							
							if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincFactKeyid()))
								forwardData.put("cmbwwmsFactoryid",existSheTlIncidentmst.getSincFactKeyid());						
							if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincCellKeyid()))
								forwardData.put("cmbwwmsCellid",existSheTlIncidentmst.getSincCellKeyid());
							if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincSectKeyid()))
								forwardData.put("cmbwwmsSectionid",existSheTlIncidentmst.getSincSectKeyid());
							
							if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincCountermeasure()))
								forwardData.put("txtwwmsCountermeasure",existSheTlIncidentmst.getSincCountermeasure());
							//if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincPhenomena()))
								//forwardData.put("cmbwwmsPhenomenaid",existSheTlIncidentmst.getSincPhenomena());							
							
							//if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincReportedBy()))
								//forwardData.put("cmbwwmsMaintinchargeid",existSheTlIncidentmst.getSincReportedBy());
							
							if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincYyno()))
								forwardData.put("txtwwmsKeyid",existSheTlIncidentmst.getSincYyno());
							
							if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincExactDesc()))
								forwardData.put("txtWwmsAccidentdesc",existSheTlIncidentmst.getSincExactDesc());
							
							if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincPhenomena()))
								forwardData.put("txtWwmsAccidentphen",existSheTlIncidentmst.getSincPhenomena());
							
							forwardData.put("txtformType","SFT");
							persistentData.put("OpenTab", "OpenYY");
							returnData.put("afterSave",sheAccIncBean.getSaveMode());
							returnData.put("formClear",false);
						}
					}
					String msgPropertyIdnt;
					 
					if( insert){
						msgPropertyIdnt = "success-save";
					}else
						msgPropertyIdnt = "success-update";
					
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("mode",sheAccIncBean.getFormMode());
					successData.put("keyId", existSheTlIncidentmst.getSincKeyid());
					
				
					returnData.put("forwardData",forwardData);
					returnData.put("persistentData", persistentData);
					
					returnData.put("successData", successData);						
					CommonFunctions.debugMsg(returnData.toString());
					out.print(returnData.toString());
					if((formMode!=null)&&!(formMode.equals("View")))
						accidentIncidentRptingService.saveImg(existSheTlIncidentmst);
	    		}catch(ValidationExceptions e)
				{
	    			CommonFunctions.debugMsg(" 1 Catch ");
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "AccidentIncidentReporting");
					errMessage.put("fromMode",sheAccIncBean.getFormActionMode());
					out.print(errMessage.toString());
				}catch(BusinessApplicationExceptions e)
				{
					CommonFunctions.debugMsg(" 2 Catch ");
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "AccidentIncidentReporting");
					out.print(errMessage.toString());					
					
				}catch(Exception e)
				{
					CommonFunctions.debugMsg(" 3 Catch ");
					CommonFunctions.debugMsg(e.getMessage());
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
			if( commonFilter != null && ! createNew ){
				FilterValues.setPaginationParams(request,commonFilter);
			}	
			else{
				commonFilter =  new CommonFilter();
				
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
				commonFilter = 	FilterValues.getSafty(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
		private JSONObject getTableModel(List<String[]> headers)
		{
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String[] row = headers.get(0);
			String[] row1 = headers.get(1);
			jqGridTableModel.getRowHeaders().add(row1);	
			
			jqGridTableModel.setTableButton(true);		 
			jqGridTableModel.setTableHeight(290);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			for(int i=0;i<row.length;i++)
			{
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
				jqGridColModel.setName(row[i].replaceAll(" ", ""));
				jqGridColModel.setEditable(false);
				CommonFunctions.debugMsg(i + " : "+row[i]);
				if(i==0 || i==9)
					jqGridColModel.setHidden(true);
				else if(i==1 || i==25||i==2||i==6||i==7)
					jqGridColModel.setWidth(80);
				else if(i==5)
					jqGridColModel.setWidth(300);	
				
				else if(i==3 || i==4 || i==19 || i==23)
				{
					 jqGridColModel.setWidth(100);
					 jqGridColModel.setAlign("center");
				}
				else if(i==9 || i==24)
					jqGridColModel.setWidth(300);
				else if(i==10 || i==11)
				{
					jqGridColModel.setWidth(80);
				}
				else if(i==11 || i==21 || i==22)
					jqGridColModel.setWidth(200);
				else if(i>15 && i<20)
				{
					if(i==16)
						jqGridColModel.setWidth(500);
					else 
						jqGridColModel.setWidth(350);
				}				
				else if(i==20 || (i>25 && i<28) || i==29)
				{
					if(i==20)
						jqGridColModel.setWidth(500);
					else
						jqGridColModel.setWidth(350);
				}			
				jqGridTableModel.getColModel().add(jqGridColModel);
			}
			
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			
			
			CommonFunctions.debugMsg(tableModel   + "..................");
			return tableModel;
		}
		
private void saveincidentEmployee(HttpServletRequest request, HttpServletResponse response,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean ) throws BusinessApplicationExceptions,IOException{
			
	    	
	    	HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	String incId=request.getParameter("incId");
	    	String contractEmployee = request.getParameter("cmbSiemContractEmpmKeyid");
	    	String contractComp = request.getParameter("txtSiemCompany");	  
	    	CommonFunctions.debugMsg("incId"+incId);
	    	if( httpSession != null && user != null)
	    	{	
	    		SheTlIncidentEmployee newsheTlIncidentEmployee =new SheTlIncidentEmployee();
	    		
				
	    		newsheTlIncidentEmployee =(SheTlIncidentEmployee)UIUtils.setBeanProperties((Object)newsheTlIncidentEmployee,request);
	    		sheTlIncidentEmployeetBean =(SheTlIncidentEmployeetBean) UIUtils.setBeanProperties((Object)sheTlIncidentEmployeetBean,request);
	    		if(UIUtils.isValidKeyId(contractEmployee))
	    			sheTlIncidentEmployeetBean.setContractEmp(contractEmployee.toUpperCase());	
	    		if(UIUtils.isValidKeyId(contractComp))
	    			sheTlIncidentEmployeetBean.setContractComp(contractComp.toUpperCase());	 
	    		SheTlIncidentEmployee existSheTlIncidentEmployee = (SheTlIncidentEmployee)httpSession.getAttribute("EntBatchFacultyCreationServlet"); 
				
				try{
						boolean insert = true;
						if( newsheTlIncidentEmployee.getSiemKeyid() == null )
						{
							newsheTlIncidentEmployee.setSiemSincKeyid(incId);
							existSheTlIncidentEmployee = accidentIncidentRptingService.create(newsheTlIncidentEmployee,existSheTlIncidentEmployee,sheTlIncidentEmployeetBean);
						}
						else{
							insert = false;
							existSheTlIncidentEmployee = accidentIncidentRptingService.update(newsheTlIncidentEmployee,existSheTlIncidentEmployee,sheTlIncidentEmployeetBean);
							
						}
						new JSONObject();
					
						JSONObject persistentData = new JSONObject(); 
						persistentData.put("batchFacultyid",existSheTlIncidentEmployee.getSiemKeyid());
						
						
					    String msgPropertyIdnt;				
					 
						 if( insert){
							msgPropertyIdnt = "success-save";
						 }else
							 msgPropertyIdnt = "success-update";
						 JSONObject successData = new JSONObject(); 
					    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
						successData.put("mode",sheTlIncidentEmployeetBean.getFormActionMode() );
						successData.put("keyId", existSheTlIncidentEmployee.getSiemKeyid());
						JSONObject returnData = new JSONObject();	
						if(UIUtils.isValidKeyId(existSheTlIncidentEmployee.getSiemSincKeyid()))
							returnData.put("IncidentKey", existSheTlIncidentEmployee.getSiemSincKeyid());
						returnData.put("formClear",false);
						returnData.put("successData", successData);
							
						//httpSession.removeAttribute("EntEmpMangerLinkServlet");
						httpSession.setAttribute( "EntBatchFacultyCreationServlet", existSheTlIncidentEmployee);
						//httpSession.removeAttribute("genTlCompanymstBean");
						System.out.println(" after save  3");
						out.print(returnData.toString());
						out.close();
					
				}catch(BusinessApplicationExceptions e)
				{
					CommonFunctions.debugMsg(" Inside BusinessApplication Exception ");
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "AccidentIncedientEmployee");
					out.print(errMessage.toString());					
				}			
				catch(ValidationExceptions e)
				{
					System.out.println("e.yguiyg " + e.getMessage());
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"AccidentIncedientEmployee");
					errMessage.put("fromMode",sheTlIncidentEmployeetBean.getFormActionMode());
					out.print(errMessage.toString());
						
				}catch(Exception e)
				{
					System.out.println("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					if(e.getMessage() == null)
						err.put("tpmException", "Faculty already exist for this batch");
					else{
						err.put("tpmException", "Data Not Saved");
					}
					out.print(err.toString());
				}
				
		    }	
		}
 private void deleteincidentEmployee(HttpServletRequest request, HttpServletResponse response,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean) throws Exception{
	HttpSession httpSession = request.getSession(false);
	PrintWriter outdel = response.getWriter();
	//ServletOutputStream outdel = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);

	if( httpSession != null && user != null)
	{	
		
		//String BatchFacultyId = request.getParameter("BatchFacultyId");
		
		String rowId=request.getParameter("rowId");
		SheTlIncidentEmployee sheTlIncidentEmployee =new SheTlIncidentEmployee();
		sheTlIncidentEmployee =(SheTlIncidentEmployee)UIUtils.setBeanProperties((Object)sheTlIncidentEmployee,request);
		httpSession.getAttribute("EntBatchFacultyCreationServlet");
		
		try{
					String incEmpId=request.getParameter("incEmpId");
					sheTlIncidentEmployee.setSiemKeyid(incEmpId);
					CommonFunctions.debugMsg("sheTlIncidentEmployee"+sheTlIncidentEmployee.getSiemKeyid());
					SheTlIncidentEmployee delStaus = accidentIncidentRptingService.delete(sheTlIncidentEmployee);					
					JSONObject returnData = new JSONObject();					
					returnData.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));		
					returnData.put("formClear",true);
					returnData.put("rowId",request.getParameter("rowId"));
					outdel.print(returnData.toString());
					outdel.close();
			}
		
		
		catch(ValidationExceptions e)
		{
			PrintWriter  outexdel = response.getWriter();
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"EntTlBatchFacultyException");
			
			errMessage.put("formActionMode",sheTlIncidentEmployeetBean.getFormActionMode());				
			outexdel.print(errMessage.toString());
			
		}
		
	}
}

 private void saveActionPlan(HttpServletRequest request, HttpServletResponse response,SheActionPlanBean sheActionPlanBean ) throws IOException{
 	
 	
 	HttpSession httpSession = request.getSession(false);
 	ServletOutputStream out = response.getOutputStream();
 	AdmTlUsermst user = UIUtils.getLoginUser(request);
 	String incId=request.getParameter("incId");
 	CommonFunctions.debugMsg("incId"+incId);
 	if( httpSession != null && user != null)
 	{	
 		SheTlActionplan newSheTlActionplan =new SheTlActionplan();
 		
 		newSheTlActionplan.setSacpCreatedBy(user.getUsrm_ccno());
 		if(UIUtils.isValidKeyId(incId))
 			newSheTlActionplan.setSacpSincKeyid(incId);
 		newSheTlActionplan =(SheTlActionplan)UIUtils.setBeanProperties((Object)newSheTlActionplan,request);
 		sheActionPlanBean =(SheActionPlanBean) UIUtils.setBeanProperties((Object)sheActionPlanBean,request);
 		SheTlActionplan existSheTlActionplan = (SheTlActionplan)httpSession.getAttribute("SheActionPlan"); 
 		
 		CommonFunctions.debugMsg("test to be countu........."+newSheTlActionplan.getSacpKeyid());
 		try{
 				boolean insert = true;
 				if( newSheTlActionplan.getSacpKeyid() == null )
 				{
 					
 					existSheTlActionplan = accidentIncidentRptingService.create(newSheTlActionplan,existSheTlActionplan,sheActionPlanBean);
 				}
 				else{
 					existSheTlActionplan = accidentIncidentRptingService.update(newSheTlActionplan,existSheTlActionplan,sheActionPlanBean);
 					insert = false;
 				}
 				new JSONObject();
 			
 				JSONObject persistentData = new JSONObject(); 
 				persistentData.put("batchFacultyid",existSheTlActionplan.getSacpKeyid());
 				
 				
 			    String msgPropertyIdnt;				
 			 
 				 if( insert){
 					msgPropertyIdnt = "success-save";
 				 }else
 					 msgPropertyIdnt = "success-update";
 				 JSONObject successData = new JSONObject(); 
 			    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
 				successData.put("mode",sheActionPlanBean.getFormActionMode() );
 				successData.put("keyId", existSheTlActionplan.getSacpKeyid());
 				JSONObject returnData = new JSONObject();	
 				//returnData.put("formClear",false);
 				returnData.put("successData", successData);
 					
 				//httpSession.removeAttribute("EntEmpMangerLinkServlet");
 				httpSession.setAttribute( "SheActionPlan", existSheTlActionplan);
 				//httpSession.removeAttribute("genTlCompanymstBean");
 				System.out.println(" after save  3");
 				out.print(returnData.toString());
 				out.close();
 			
 		}catch(BusinessApplicationExceptions e)
 		{ 
 			System.out.println("BusinessApplicationExcepions");
 			JSONObject errMessage = new JSONObject();
 			errMessage.put("tpmException", "Faculty already exist for this batch");
 			out.print(errMessage.toString());
 		}			
 		catch(ValidationExceptions e)
 		{
 			//System.out.println("e.yguiyg " + e.getMessage());
 			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ActionPlan");
 			errMessage.put("fromMode",sheActionPlanBean.getFormActionMode());
 			out.print(errMessage.toString());
 				
 		}catch(Exception e)
 		{
 			//System.out.println("gete. " + e.getMessage());
 			JSONObject err = new JSONObject();
 			
 				err.put("tpmException", "Data Not Saved");
 			
 			out.print(err.toString());
 		}
 		
     }	
 }
 private void deleteActionPlan(HttpServletRequest request, HttpServletResponse response,SheActionPlanBean sheActionPlanBean) throws Exception{
		HttpSession httpSession = request.getSession(false);
		PrintWriter outdel = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if( httpSession != null && user != null)
		{	
			String rowId=request.getParameter("rowId");
			SheTlActionplan sheTlActionplan =new SheTlActionplan();
			sheActionPlanBean =(SheActionPlanBean)UIUtils.setBeanProperties((Object)sheActionPlanBean,request);
			
			try{
					String keyId=request.getParameter("keyId");
					sheTlActionplan.setSacpKeyid(keyId);
					CommonFunctions.debugMsg("test data tdfdkfds........."+keyId);
					SheTlActionplan delStaus = accidentIncidentRptingService.deleteActionPlan(sheTlActionplan);
					CommonFunctions.debugMsg("test data tdfdkfds.........");
					JSONObject returnData = new JSONObject();					
					returnData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));		
					returnData.put("formClear",true);
					CommonFunctions.debugMsg("test data tdfdkfds.........");
					outdel.print(returnData.toString());
					//outdel.close();
				}
			catch(ValidationExceptions e)
			{
				PrintWriter  outexdel = response.getWriter();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ActionPlan");
				errMessage.put("formActionMode",sheActionPlanBean.getFormActionMode());				
				outexdel.print(errMessage.toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				PrintWriter  outexdel = response.getWriter();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ActionPlan");
				errMessage.put("formActionMode",sheActionPlanBean.getFormActionMode());				
				outexdel.print(errMessage.toString());
			}
		}
	}
	private void setIncidentFormValues(SheTlIncidentmst sheTlIncidentmst)
	{
		if(UIUtils.isValidKeyId(sheTlIncidentmst.getSincAnalysedDate()) && sheTlIncidentmst.getSincAnalysedDate().length()>11)
		{
			sheTlIncidentmst.setSincAnalysedDate(sheTlIncidentmst.getSincAnalysedDate().substring(0, sheTlIncidentmst.getSincAnalysedDate().indexOf(" ")));
			if(sheTlIncidentmst.getSincAnalysedDate().equals(Constants.passNullDate))
				sheTlIncidentmst.setSincAnalysedDate("");
		}
		if(UIUtils.isValidKeyId(sheTlIncidentmst.getSincOccurredDate()) && sheTlIncidentmst.getSincOccurredDate().length()>10)
		{
			sheTlIncidentmst.setSincOccurredDate(sheTlIncidentmst.getSincOccurredDate().substring(0, sheTlIncidentmst.getSincOccurredDate().indexOf(" ")));
			if(sheTlIncidentmst.getSincOccurredDate().equals(Constants.passNullDate))
				sheTlIncidentmst.setSincOccurredDate("");
		}
		if(UIUtils.isValidKeyId(sheTlIncidentmst.getSincReportedDate()) && sheTlIncidentmst.getSincReportedDate().length()>10)
		{
			sheTlIncidentmst.setSincReportedDate(sheTlIncidentmst.getSincReportedDate().substring(0, sheTlIncidentmst.getSincReportedDate().indexOf(" ")));
			if(sheTlIncidentmst.getSincReportedDate().equals(Constants.futureNullDate))
				sheTlIncidentmst.setSincReportedDate("");
		}
	}
	
}
