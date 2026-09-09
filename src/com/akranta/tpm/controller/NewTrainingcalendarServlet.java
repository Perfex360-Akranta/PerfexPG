package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONValue;

import com.akranta.tpm.model.AdmTlUsermst;
//import com.akranta.tpm.model.BdmTlCriticalityassessment;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTrgCalEmp;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTrgFaculty;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.service.NewTrainingcalendarService;
import com.akranta.tpm.service.impl.NewTrainingcalendarServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class NewTrainingcalendarServlet
 */
  /*Created by Ilanthamilan*/
@WebServlet("/NewTrainingcalendarServlet")
public class NewTrainingcalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewTrainingcalendarService newTrainingcalendarService;
	  String filePath=null;
	  private static String DOC_ROOT_PATH;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewTrainingcalendarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try{
			process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try{
			process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
   private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException,Exception{
	    HttpSession httpSession=request.getSession(false);
	   String action = UIUtils.getActionPart(request);
	   
	   newTrainingcalendarService=(NewTrainingcalendarServiceImpl)UIUtils.getServiceObject(request,"NewTrainingcalendarServiceImpl");
	   
		CommonMessage.debugMsg("  newTrainingcalendarService jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
	//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
		newTrainingcalendarService.NewTrainingcalendarServiceImplJwt(
				   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
				);
		
	   String loginflid=CommonFunctions.getLoginFlid(request);
	
		 String loginlevel=CommonFunctions.getLoginLevel(request);
		  String loginElementid = (String) httpSession.getAttribute("loginElementid");
		  AdmTlUsermst user=UIUtils.getLoginUser(request);
	   String empId=user.getUsrm_ccno();
		List<String []> getUserLoginDtl= newTrainingcalendarService.getElementId(loginflid,loginlevel, loginElementid,empId);
		String elementid = getUserLoginDtl.get(0)[0];
		String flidnew = getUserLoginDtl.get(0)[1];
		String level = getUserLoginDtl.get(0)[2];
		String rolename=getUserLoginDtl.get(0)[3];
		String roledetail=rolename;
	   String rolekeyid=getUserLoginDtl.get(0)[4];
	  /* CommonMessage.debugMsg("rolename"+rolename);
	   CommonMessage.debugMsg("rolekeyid"+rolekeyid);
	   CommonMessage.debugMsg("level"+level);
	   CommonMessage.debugMsg("flidnew"+flidnew);
	   CommonMessage.debugMsg("elementid"+elementid);*/
	   
	   
	   if(action.equals("NewTrainingCalender_input.ntrc")){
		   
	       String mode = request.getParameter("mode");
		   String Freqmode = request.getParameter("Freqmode");
		   String calid = request.getParameter("progid");
		   String flid = request.getParameter("flid");
		   String date=CommonFunctions.dateTimeNow();
			String currentdate=UIUtils.getActualDateForm(date);
		 	EntTlTragcalmst entTlTragcalmst = new EntTlTragcalmst();
		 	if(!UIUtils.isValidKeyId(flid)){
				flid=(String) httpSession.getAttribute("loginFlid");
			}
			
		 	
		
		 	
		 	
		 	if("modify".equals(mode)||("view".equals(mode))){
		 		
		 	    entTlTragcalmst = newTrainingcalendarService.getselectdata(calid);
		 	   //////////////////////////////////////////
		 	   /// 
		 	    String empcount=newTrainingcalendarService.getempdata(calid);
		 	    
		 	    CommonMessage.debugMsg("servlet after api empdata " + empcount);
		 	    
		 	    String empattn=newTrainingcalendarService.getempattn(calid);
		 	    
		 	    
		 	   CommonMessage.debugMsg("servlet after api empattendance-------- " + empattn);
		 	   
		 	    
		 	   CommonFilter commonFilter=new CommonFilter();
		 	   String maxmarks = null;
		 	  String Cuttoff = null;
		 	 String cnt=null;
		 	 String assesType=null;
		 	    if(!empattn.equals("0"))
		 	    {
		 	    	commonFilter.setKey(calid);
		 	    	commonFilter.setMaintMode("modify");
		 	    	
		 	    	maxmarks=newTrainingcalendarService.getMaxmarks(calid);
		 	    	  CommonMessage.debugMsg("servlet after api getMaxmarks-------- " + maxmarks);
		 	    	
		 	    	  Cuttoff=newTrainingcalendarService.getCutoff(calid);
		 	    	  CommonMessage.debugMsg("servlet after api CUTOFF -------- " + Cuttoff);
		 	    	//------ vignesh 
		 	    
		 	    	  assesType = newTrainingcalendarService.getAssesType(calid);
		 	    	  CommonMessage.debugMsg("servlet after api GETASSESMENT-------- " + assesType);
		 	    
		 	    	  cnt = newTrainingcalendarService.chkAssesmentComplted(commonFilter);
		 	    	  CommonMessage.debugMsg("servlet after api CHKASSESMENT -------- " + cnt);
		 	    	  
		 	    	request.setAttribute("asscnt", Integer.parseInt(cnt));
		 	    }
		 	    request.setAttribute("empcount", Integer.parseInt(empcount));
		 	    //////////////////////////////////////////////
			 	request.setAttribute("entTlTragcalmst", entTlTragcalmst);
			 	request.setAttribute("maxmarks" , maxmarks);
			 	request.setAttribute("Cuttoff" , Cuttoff);
			 	request.setAttribute("assesType" , assesType);
			 	CommonMessage.debugMsg("assesment type = in servlet " + assesType);
			 	request.setAttribute("empattn", Integer.parseInt(empattn));
			 	httpSession.setAttribute("entTlTragcalmst_Servlet", entTlTragcalmst);
			 	
		 	}
		 	
		 	
		 	request.setAttribute("mode", mode);
		 	request.setAttribute("rolename",rolename);
		 	request.setAttribute("currentdate",currentdate.substring(0,11));
		     UIUtils.forwardRequest(request, response,"/pages/newENT/NewTrainingCalendar.jsp");     
		   
		   //UIUtils.forwardRequest(request, response,"/pages/newENT/NewTrainingCalendar.jsp");
	   }
	   
	   
	   if(action.equals("NewTrainingCalendermodify_input.ntrc")){
			  
		   String mode = request.getParameter("mode");
		   CommonMessage.debugMsg("The Mode"+mode);
		   request.setAttribute("mode", mode);
	        UIUtils.forwardRequest(request, response,"/pages/newENT/NewTrainingCalendarModify.jsp");
  }
	   else if(action.equals("TrainingIdentified.ntrc")){
			PrintWriter out = response.getWriter(); 
			CommonMessage.debugMsg("Inside GetCol");
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "TrainingIdentified"));
		}
	   else if(action.equals("TopicDetails.ntrc")){
		 
		   PrintWriter out=response.getWriter();
			String topicid=request.getParameter("topicid");
			String trainingmode=newTrainingcalendarService.getTrainingMode(topicid);
			String trainingtype=newTrainingcalendarService.getTrainingTopicType(topicid);
			CommonMessage.debugMsg("the pssrCount"+trainingtype);
			 JSONObject json=new JSONObject();
			 json.put("trainingtype", trainingtype);
			 json.put("trainingmode", trainingmode);
			 out.println(json);
	   }
		   
		   
	   else if(action.equals("EmpWiseTrainingReport_input.ntrc")){
		   CommonMessage.debugMsg("Action "+action);
		   UIUtils.forwardRequest(request, response,"/pages/newENT/EmpWiseTrainingReport.jsp");
		   
	   }
	   else if(action.equals("EmpWiseTrainingReport_getCol.ntrc")){
           PrintWriter out = response.getWriter();
          CommonFilter commonFilter=new CommonFilter();
          JSONObject jsonObject = new JSONObject();
          commonFilter  = populateCommonFilter(request,"TrainingCommonFilter",true);   
          try
          {  
             
              //UIUtils.displayRequestParamsValue(request);   
              String flid=request.getParameter("flid");
               /*String mode = request.getParameter("frmMode");
               CommonMessage.debugMsg("mode"+mode);*/
              // commonFilter.setType(mode);
               if(UIUtils.isValidKeyId(flid))
              commonFilter.setFlid(flid);
              String keyid=request.getParameter("keyid");
              if(UIUtils.isValidKeyId(keyid))
              commonFilter.setKey(keyid);
              //FilterValues.getCommonFilters(request, commonFilter);
            //  GridParams gridparams = new GridParams();
              //FilterValues.populateGridParams(request, gridparams);
          //    FilterValues.getCommonFilters(request, commonFilter);
           //   CommonMessage.debugMsg("getdata ");
          	commonFilter.setIsGetCol("Y");
              List<String[]> AuditListGrid  =newTrainingcalendarService.getEmpWiseTrainingReport(commonFilter);
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
                httpSession.removeAttribute("TrainingColmodel");
                httpSession.setAttribute("TrainingColmodel",jsonObject);   
                out.println(jsonObject);                  
              
          }catch(Exception e)
          {
              CommonMessage.debugMsg(e.getMessage());
          }
             
           }
   else if(action.equals("EmpWiseTrainingReport_getData.ntrc")){           
           PrintWriter out = response.getWriter();
           CommonFilter commonFilter= populateCommonFilter(request,"TrainingCommonFilter",false);   
              try
              {  
                 
                  UIUtils.displayRequestParamsValue(request);   
                  String flid=request.getParameter("flid");
                  commonFilter.setFlid(flid);
                  String keyid=request.getParameter("keyid");
                  commonFilter.setKey(keyid);
               	commonFilter.setIsGetCol("N");
                  List<String[]> Grid  =    newTrainingcalendarService.getEmpWiseTrainingReport(commonFilter);
                  CommonMessage.debugMsg("equipmentQueryList " + Grid.size());
                  JSONObject Data = UIUtils.convertToJqGridTableObject(Grid,request,2,0,commonFilter.getTotalRecordCnt()-2); 
                  commonFilter.setViewClick('N'); 
             	  httpSession.removeAttribute("TrainingCommonFilter");
      			  httpSession.setAttribute("TrainingCommonFilter", commonFilter);
                  out.println(Data); 
              }catch(Exception e)
              {
                  CommonMessage.debugMsg(e.getMessage());
              }
       }
	   
   else if(action.equals("EmpWiseTrainingReport_getExcel.ntrc")){
		 CommonFilter commonFilter = new CommonFilter();
		 commonFilter = populateCommonFilter(request,"TrainingCommonFilter",false);
		 String tmpFromRow = commonFilter.getFromRow();
	     commonFilter.setFromRow(null);
		 JSONObject colmodel = (JSONObject) httpSession.getAttribute("TrainingColmodel");
		 colmodel.put("title","TrainingCalendar Report");
         String format = ExcelUtils.getFormat(request);
		 Workbook wb = newTrainingcalendarService.getEmpWiseTrainingCalendarListExcel(colmodel,format,commonFilter);
		 commonFilter.setFromRow(tmpFromRow);
		 ExcelUtils.writeToResponse(response, wb, "TrainingCalendarReport", format);
	}
	   
	   else if(action.equals("NewTrainingCalendermodify_getCol.ntrc")){
           PrintWriter out = response.getWriter();
          CommonFilter commonFilter=new CommonFilter();
          JSONObject jsonObject = new JSONObject();
          commonFilter  = populateCommonFilter(request,"TrainingCommonFilter",true);   
          try
          {  
             
              //UIUtils.displayRequestParamsValue(request);   
              String flid=request.getParameter("flid");
               String mode = request.getParameter("frmMode");
               CommonMessage.debugMsg("mode"+mode);
               commonFilter.setType(mode);
               if(UIUtils.isValidKeyId(flid))
              commonFilter.setFlid(flid);
              String keyid=request.getParameter("keyid");
              if(UIUtils.isValidKeyId(keyid))
              commonFilter.setKey(keyid);
              //FilterValues.getCommonFilters(request, commonFilter);
              GridParams gridparams = new GridParams();
              //FilterValues.populateGridParams(request, gridparams);
          //    FilterValues.getCommonFilters(request, commonFilter);
              CommonMessage.debugMsg("getdata ");
              commonFilter.setIsGetCol("Y");
              List<String[]> AuditListGrid  =newTrainingcalendarService.getTrainingCalendarList(gridparams,commonFilter);
              //JSONObject Data = getTableModel(Grid);
           //    CommonMessage.debugMsg("equipmentQueryList " + Grid.size());
              //PrintWriter out = response.getWriter();
               //JSONObject Data = UIUtils.convertToJqGridTableObject(Grid,request,2,0);
            // httpSession.removeAttribute("TrainingColmodel");
            // httpSession.setAttribute("TrainingColmodel", Data);
          //CommonMessage.debugMsg("Data " + Data);
               //out.println(Data);
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
                httpSession.removeAttribute("TrainingColmodel");
                httpSession.setAttribute("TrainingColmodel",jsonObject);   
                out.println(jsonObject);                  
              
          }catch(Exception e)
          {
              CommonMessage.debugMsg(e.getMessage());
          }
             
           }
	   // -- Vignesh 12Dec2025 ---------------------------------------------------------------------------//
	   
//	   else if(action.equals("NewTrainingCalendermodify_getData.ntrc")){
//		    PrintWriter out = response.getWriter();
//		    CommonFilter commonFilter  = populateCommonFilter(request,"TrainingCommonFilter",false);   
//		    try
//		    {  
//		        UIUtils.displayRequestParamsValue(request);   
//
//		        String flid = request.getParameter("flid");
//		        String mode = request.getParameter("frmMode");
//		        commonFilter.setType(mode);
//		        commonFilter.setFlid(flid);
//
//		        String keyid = request.getParameter("keyid");
//		        commonFilter.setKey(keyid);
//
//		        GridParams gridparams = new GridParams();
//
//		        CommonMessage.debugMsg("getdata ");
//
//		        List<String[]> Grid  = newTrainingcalendarService.getTrainingCalendarList(gridparams, commonFilter);
//
//		        CommonMessage.debugMsg("equipmentQueryList " + Grid.size());
//
//		        // 🔍 DEBUG: print page, rows, first and last KEYID
//		        try {
//		            String pageStr = request.getParameter("page");
//		            String rowsStr = request.getParameter("rows");
//
//		            int page = 1;
//		            int rows = 100;
//		            if (pageStr != null && !pageStr.isEmpty()) {
//		                page = Integer.parseInt(pageStr);
//		            }
//		            if (rowsStr != null && !rowsStr.isEmpty()) {
//		                rows = Integer.parseInt(rowsStr);
//		            }
//
//		            // We know:
//		            // row 0 -> TABLEMODEL
//		            // row 1 -> HEADER
//		            // row 2.. -> actual data rows
//		            if (Grid != null && Grid.size() > 2) {
//		                String[] firstDataRow = Grid.get(2);                   // first data row
//		                String[] lastDataRow  = Grid.get(Grid.size() - 1);     // last data row
//
//		                // Column order in SQL:
//		                // 0 = rn, 1 = dataorder, 2 = keyid, then etcm_caldate, ...
//		                String firstKeyId = (firstDataRow.length > 2 ? firstDataRow[2] : "NULL");
//		                String lastKeyId  = (lastDataRow.length  > 2 ? lastDataRow[2]  : "NULL");
//
//		                CommonMessage.debugMsg(
//		                    "PAGE DEBUG => page=" + page +
//		                    ", rows=" + rows +
//		                    ", Grid.size()=" + Grid.size() +
//		                    ", first KEYID=" + firstKeyId +
//		                    ", last KEYID=" + lastKeyId
//		                );
//		            } else {
//		                CommonMessage.debugMsg(
//		                    "PAGE DEBUG => Grid is empty or only has header/tablemodel. size=" +
//		                    (Grid == null ? "NULL" : Grid.size())
//		                );
//		            }
//		        } catch (Exception dbgEx) {
//		            CommonMessage.debugMsg("PAGE DEBUG ERROR: " + dbgEx.getMessage());
//		        }
//		        // 🔍 DEBUG END
//
//		        // IMPORTANT: you are still using colStart = 0 here
//		        // If later we decide to hide rn & dataorder from jqGrid cells,
//		        // we can change this to colStart = 2.
//		        JSONObject Data = UIUtils.convertToJqGridTableObject(
//		                Grid, request, 2, 0, commonFilter.getTotalRecordCnt()
//		        ); 
//
//		        commonFilter.setViewClick('N'); 
//		        httpSession.removeAttribute("TrainingCommonFilter");
//		        httpSession.setAttribute("TrainingCommonFilter", commonFilter);
//
//		        out.println(Data); 
//		    } catch(Exception e) {
//		        CommonMessage.debugMsg(e.getMessage());
//		    }
//		}

		   
	   else if(action.equals("NewTrainingCalendermodify_getData.ntrc")){
           
           PrintWriter out = response.getWriter();
          // CommonFilter commonFilter=new CommonFilter();
           CommonFilter     commonFilter  = populateCommonFilter(request,"TrainingCommonFilter",false);   
              try
              {  
                  //CommonFilter commonFilter=new CommonFilter();
                 
                  UIUtils.displayRequestParamsValue(request);   
                   //String flid=request.getParameter("flid");
                  String flid = request.getParameter("flid");
                  if (UIUtils.isValidKeyId(flid)) {
                      commonFilter.setFlid(flid);
                  }

                   String mode = request.getParameter("frmMode");
                   commonFilter.setType(mode);
                  commonFilter.setFlid(flid);
                  String keyid=request.getParameter("keyid");
                  commonFilter.setKey(keyid);
                 
                  //FilterValues.getCommonFilters(request, commonFilter);
                  GridParams gridparams = new GridParams();
                  //FilterValues.populateGridParams(request, gridparams);
                  
                  // -- Commenting FilterValues.getCommonFilters to check the pagination  vignesh 12Dec2025-- 
                  
                  FilterValues.getCommonFilters(request, commonFilter);
                  
                  CommonMessage.debugMsg("getdata ");
                  commonFilter.setIsGetCol("N");
                  List<String[]> Grid  =    newTrainingcalendarService.getTrainingCalendarList(gridparams,commonFilter);
              	
                     CommonMessage.debugMsg("equipmentQueryList " + Grid.size());
                  //PrintWriter out = response.getWriter();
                    // JSONObject Data = UIUtils.convertToJqGridTableObject(Grid,request,2,0);
                     JSONObject Data = UIUtils.convertToJqGridTableObject(Grid,request,2,0,commonFilter.getTotalRecordCnt()); 
                     commonFilter.setViewClick('N'); 
             	 	httpSession.removeAttribute("TrainingCommonFilter");
      			 	httpSession.setAttribute("TrainingCommonFilter", commonFilter);
                     out.println(Data); 
              }catch(Exception e)
              {
                  CommonMessage.debugMsg(e.getMessage());
              }
       }
	   
	// -- Vignesh 12Dec2025 ---------------------------------------------------------------------------//
	   else if(action.equals("functionalLoc.ntrc"))
			{
		    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbTraFactoryid");
			functLocFieldNameBean.setSection("cmbTraSectionid");
			functLocFieldNameBean.setCell("cmbTraCellid");
			functLocFieldNameBean.setMachine("cmbTraMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbTraFlid");
			functLocFieldNameBean.setSbu("cmbTraSbu");
			functLocFieldNameBean.setPbu("cmbTraPbu");
			
			functLocFieldNameBean.setLocnMandatory(true);
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);

           FormModes formModes = FormModes.create;
           UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);	 
			}
	                    //*******Faculty Related**************//
	   else if(action.equals("Faculty_getCol.ntrc")){
		   try {
			    String progKeyid = request.getParameter("TraKeyid");
			    CommonMessage.debugMsg("The progKeyid"+progKeyid);
				List<String[]> facultyList = newTrainingcalendarService.getFaculty(progKeyid);
				JSONObject jsonObject = getfacultyTableModel(facultyList);
				CommonMessage.debugMsg("Table model");
				CommonMessage.debugMsg("jsonObject "+jsonObject);
				PrintWriter  out = response.getWriter();
				out.println(jsonObject);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	   
	       else if(action.equals("Faculty_getData.ntrc")){
		 
		    PrintWriter out = response.getWriter();
		    String progKeyid = request.getParameter("TraKeyid");
		    CommonMessage.debugMsg("the progKeyid"+progKeyid);
			 List<String[]> facultyList = newTrainingcalendarService.getFaculty(progKeyid);
			 JSONObject facultydata = UIUtils.convertToJqGridTableObject( facultyList, request, 0, 0);
			 out.println(facultydata);
		   
	     }
	   
	       else if( action.equals("ettrade.ntrc")){	
				ComboFilter currentFilter = new ComboFilter();
	    	   try {
	    		   currentFilter=UIUtils.fillComboFilter(request);	
	   			   List<ComboBox> course = newTrainingcalendarService.getETTradeComboList(currentFilter);
	   			   UIUtils.writeComboBox(response, course, currentFilter);
	   				} catch (Exception e) {
	   					e.printStackTrace();
	   				}
			}
	               
	                     //*************Session Related***************//
	   else if(action.equals("session_getCol.ntrc")){
			 try {
					String TrgcalKeyid = request.getParameter("TraKeyid");
					CommonMessage.debugMsg("The TrgcalKeyid::::"+TrgcalKeyid);
					List<String[]> sessionList = newTrainingcalendarService.getsession(TrgcalKeyid);
					JSONObject jsonObject = getsessionTableModel(sessionList);
					CommonMessage.debugMsg("Table model");
					CommonMessage.debugMsg("jsonObject "+jsonObject);
					PrintWriter  out = response.getWriter();
					out.println(jsonObject);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 else if(action.equals("session_getData.ntrc")){
			 PrintWriter out = response.getWriter();
			 String TrgcalKeyid = request.getParameter("TraKeyid");
			 CommonMessage.debugMsg("The TrgcalKeyid"+TrgcalKeyid);
			 List<String[]> sessionList = newTrainingcalendarService.getsession(TrgcalKeyid);
			 JSONObject sessiondata = UIUtils.convertToJqGridTableObject( sessionList, request, 1, 0);
			 out.println(sessiondata);
		 }
	/////////////my change 17oct2016////////
		 else if(action.equals("Sessionlist.ntrc")){
			 CommonMessage.debugMsg("SessionList");
			   try{
				    String calkeyid=request.getParameter("calkeyid");
				    ComboFilter combofilter=UIUtils.fillComboFilter(request);
				    CommonFilter commonfilter=new CommonFilter();
				    commonfilter.setKey(calkeyid);
				    List<ComboBox> sessionInfo=newTrainingcalendarService.session(combofilter,commonfilter);
				    UIUtils.writeComboBox(response,sessionInfo,combofilter);			    
				  }
				 catch(Exception e){
					 e.printStackTrace();
				 } 
		 }
	   // -------- Vignesh -- altering for excel filter 04dec2025 -----------------//
//		 else if(action.equals("NewTrainingCalendermodify_getExcel.ntrc")){
//				//httpSession = request.getSession(false);
//				CommonFilter commonFilter = new CommonFilter();
//				 commonFilter = populateCommonFilter(request,"NewEmployeeCommonFilter",false);
//				 String tmpFromRow = commonFilter.getFromRow();
//					commonFilter.setFromRow(null);
//					UIUtils.displayRequestParamsValue(request);   
//	                  String flid=request.getParameter("flid");
//	                   String mode = request.getParameter("frmMode");
//	                   commonFilter.setType(mode);
//	                  commonFilter.setFlid(flid);
//	                  String keyid=request.getParameter("keyid");
//	                  commonFilter.setKey(keyid);
//	                 
//	                  //FilterValues.getCommonFilters(request, commonFilter);
//	                  GridParams gridparams = new GridParams();
//	                  //FilterValues.populateGridParams(request, gridparams);
//	                  FilterValues.getCommonFilters(request, commonFilter);
//	               
//				 JSONObject colmodel = (JSONObject) httpSession.getAttribute("TrainingColmodel");
//				 colmodel.put("title","TrainingCalendar Report");
//	             String format = ExcelUtils.getFormat(request);
//				Workbook wb = newTrainingcalendarService.getTrainingCalendarListExcel(colmodel,format,commonFilter);
//				commonFilter.setFromRow(tmpFromRow);
//				ExcelUtils.writeToResponse(response, wb, "TrainingCalendarReport", format);
//				
//			}
	   
	   
		 else if (action.equals("NewTrainingCalendermodify_getExcel.ntrc")) {
			    CommonFilter commonFilter;

			    // 1) Reuse the grid’s saved filter (it includes GRIDFILTER)
			    CommonFilter saved = (CommonFilter) httpSession.getAttribute("TrainingCommonFilter");

			    if (saved != null) {
			        // clone or reuse; if CommonFilter is mutable, reuse carefully
			        commonFilter = saved;
			    } else {
			        // safe fallback: build from request using the SAME key as grid
			        commonFilter = populateCommonFilter(request, "TrainingCommonFilter", false);
			        FilterValues.getCommonFilters(request, commonFilter); // may be empty if no filters in req
			    }

			    // 2) Overlay explicit params from request without wiping GRIDFILTER
			    String flid = request.getParameter("flid");
			    if (UIUtils.isValidKeyId(flid)) commonFilter.setFlid(flid);

			    String mode = request.getParameter("frmMode");
			    if (UIUtils.isValidKeyId(mode)) commonFilter.setType(mode); // e.g., "modify"

			    String keyid = request.getParameter("keyid");
			    if (UIUtils.isValidKeyId(keyid)) commonFilter.setKey(keyid);

			    // 3) Export should ignore paging; keep your limit or widen it
			    String tmpFromRow = commonFilter.getFromRow();
			    commonFilter.setFromRow("1 AND 1000"); // or keep "1 AND 1000" if required

			    // 4) Build Excel and restore FromRow afterwards
			    JSONObject colmodel = (JSONObject) httpSession.getAttribute("TrainingColmodel");
			    colmodel.put("title", "TrainingCalendar Report");
			    String format = ExcelUtils.getFormat(request);

			    Workbook wb = newTrainingcalendarService.getTrainingCalendarListExcel(colmodel, format, commonFilter);
			    commonFilter.setFromRow(tmpFromRow);

			    ExcelUtils.writeToResponse(response, wb, "TrainingCalendarReport", format);
			}

	   // -------- Vignesh -- altering for excel filter 04dec2025 -----------------//
	/////////////////////////////////////////////   
		 else if(action.equals("EmpTrainingAtt_getCol.ntrc")){
		    PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"NewEmployeeCommonFilter",true);
			String MasterId=request.getParameter("TraKeyid");
			String Mark=request.getParameter("Mark");
			String Assess=request.getParameter("Asse");
			CommonMessage.debugMsg("Mark"+Mark+"Assess"+Assess);
			GridParams gridParams=new GridParams();
			FilterValues.populateGridParams(request,gridParams);
			commonFilter.setKey(MasterId); 
			if(Mark.equals("Y") && Assess.equals("Y"))
			{
		    CommonMessage.debugMsg("Inside if");
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "EmpAttendance"));			
			}
			else{
			    CommonMessage.debugMsg("Inside Else");
		        out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "EmpAttendanceMarks"));
			}
		//	List<String[]> emplistData = newTrainingcalendarService.getAllEmployee(commonFilter,gridParams);
		//	JSONObject jsonObject = getTableModel(emplistData);
			httpSession.removeAttribute("NewEmployeeCommonFilter");
			httpSession.setAttribute("NewEmployeeCommonFilter", commonFilter);
			httpSession.removeAttribute("NewEmployeeColmodel");
		//	httpSession.setAttribute("NewEmployeeColmodel", jsonObject);
	 }
		else if (action.equals("EmpTrainingAtt_getData.ntrc")) {
			try {
				CommonFilter commonFilter = new CommonFilter();
				PrintWriter out = response.getWriter();
				String MasterId = request.getParameter("TraKeyid");
				CommonMessage.debugMsg("The MasterId:::"+MasterId);
				commonFilter.setKey(MasterId);
			 commonFilter = populateCommonFilter(request,"NewEmployeeCommonFilter",false);
			 GridParams gridParams=new GridParams();
			 FilterValues.populateGridParams(request,gridParams);
			 List<String[]> emplistData = newTrainingcalendarService.getAllEmployee(commonFilter,gridParams);
		     JSONObject emplist = UIUtils.convertToJqGridTableObject(emplistData, request,1,0,gridParams.getTotalRecordCnt());			 
			 out.println(emplist);
			}
		 catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

else if(action.equals("EmpTrainingAtt_getExcel.ntrc")){
			 httpSession = request.getSession(false);
			 CommonFilter commonFilter = new CommonFilter();
			 commonFilter = populateCommonFilter(request,"NewEmployeeCommonFilter",false);
			 String tmpFromRow = commonFilter.getFromRow();
			 commonFilter.setFromRow(null);
			 JSONObject colmodel = (JSONObject) httpSession.getAttribute("NewEmployeeColmodel");
			 colmodel.put("title","EmployeeAttendance Report");
             String format = ExcelUtils.getFormat(request);
			 Workbook wb = newTrainingcalendarService.getAllEmployeeExcel(colmodel,format,commonFilter);
			 commonFilter.setFromRow(tmpFromRow);
			 ExcelUtils.writeToResponse(response, wb, "EmployeeAttendenceReport", format);
			
		}
		else if (action.equals("NewUniquePositionSelectd_getCol.ntrc")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", true);
			String MasterId=request.getParameter("TrgId");
			commonFilter.setKey(MasterId); 
			CommonMessage.debugMsg("The commonFilter"+commonFilter);
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewUniquePosition", "selNewUniquePos"));
			httpSession.removeAttribute("NewuniqueGridCommonFilter");
			httpSession.setAttribute("NewuniqueGridCommonFilter", commonFilter);
		}
	   
		else if (action.equals("NewUniquePositionSelectd_getData.ntrc")) {
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
				PrintWriter out = response.getWriter();
				String MasterId=request.getParameter("TrgId");
				commonFilter.setKey(MasterId); 
				GridParams gridParams=new GridParams();
				FilterValues.populateGridParams(request,gridParams);
				List<String[]> uniqueEmplist = newTrainingcalendarService.getAllUniqueEmployee(commonFilter,gridParams);
				JSONObject uniquePOSEmp = UIUtils.convertToJqGridTableObject(uniqueEmplist, request,1, 0,gridParams.getTotalRecordCnt());
				out.println(uniquePOSEmp);
				httpSession.removeAttribute("NewuniqueGridCommonFilter");
				httpSession.setAttribute("NewuniqueGridCommonFilter",commonFilter);	

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
	   
		 else if(action.equals("getjhvalue.ntrc")){
			  PrintWriter out=response.getWriter();
			  String originalid = request.getParameter("originalval");
			  CommonMessage.debugMsg("The Original ID::"+originalid);
			  List<String[]> keyids= newTrainingcalendarService.getflid(originalid);
			  JSONObject returnData = new JSONObject();
			  JSONObject successData = new JSONObject();
			  successData.put("locn", keyids.get(0)[0]);
			  successData.put("sect", keyids.get(0)[1]);
			  successData.put("flid", keyids.get(0)[2]);
			  successData.put("cellid", keyids.get(0)[3]);
			  returnData.put("successData",successData);
			  out.print(returnData);   
		   }
		else if(action.equals("EmployeeUniqueAdd_input.ntrc")){
			
			String flid=request.getParameter("flid");
			String locnid=request.getParameter("locnId");
			String Calendarkeyid=request.getParameter("keyid");
			String uniq=request.getParameter("uniq");
			CommonMessage.debugMsg("The Calendarkeyid:::"+Calendarkeyid);
			request.setAttribute("flid",flid);
			request.setAttribute("uniq",uniq);
			request.setAttribute("locnid",locnid);
			request.setAttribute("keyid",Calendarkeyid);
			UIUtils.forwardRequest(request, response,"pages/newENT/NewUniqueEmployeeAdd.jsp");
		}
	   
		else if (action.equals("EmployeeUniqueAdd_getCol.ntrc")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", true);
			String flid=request.getParameter("flid");
			String fnln=request.getParameter("fnln");
			CommonMessage.debugMsg("The Flid"+flid);
			String role=request.getParameter("roleKeyId");
			CommonMessage.debugMsg("The Role keyid"+role);
			//String role=request.getParameter("role");
			String pillarrole=request.getParameter("pillarId");
			CommonMessage.debugMsg("The pillarrole:::"+pillarrole);
			String TrainingId=request.getParameter("TrainingId");
			CommonMessage.debugMsg("the TrainingId"+TrainingId);
			String uniq=request.getParameter("uniq");
			commonFilter.setFlid(flid);
			commonFilter.setRoleLevel(role);
			commonFilter.setRefdocid(pillarrole);
			commonFilter.setKey(TrainingId);
			commonFilter.setFactoryId(fnln);
			commonFilter.setUniquePos(uniq);
			CommonMessage.debugMsg("The commonFilter"+commonFilter);
			
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewUniquePosition", "selNewEmpPos"));
			httpSession.removeAttribute("NewuniqueGridCommonFilter");
			httpSession.setAttribute("NewuniqueGridCommonFilter", commonFilter);
		}
	   
		else if (action.equals("EmployeeUniqueAdd_getData.ntrc")) {
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
				PrintWriter out = response.getWriter();
			/*	String flid=request.getParameter("flid");
				String role=request.getParameter("role");
				String pillarrole=request.getParameter("pillarrole");
				String TrainingId=request.getParameter("TrainingId");
				commonFilter.setKey(TrainingId);
				CommonMessage.debugMsg("the TrainingId"+TrainingId);
				commonFilter.setRefdocid(pillarrole);
				commonFilter.setFlid(flid);
				commonFilter.setRoleLevel(role);*/
				
				String flid=request.getParameter("flid");
				String fnln=request.getParameter("fnln");
				CommonMessage.debugMsg("The Flid"+flid);
				String role=request.getParameter("roleKeyId");
				CommonMessage.debugMsg("The Role keyid"+role);
				//String role=request.getParameter("role");
				String pillarrole=request.getParameter("pillarId");
				CommonMessage.debugMsg("The pillarrole:::"+pillarrole);
				String TrainingId=request.getParameter("TrainingId");
				CommonMessage.debugMsg("the TrainingId"+TrainingId);
				String uniq=request.getParameter("uniq");
				String EmployeeGender=request.getParameter("EmployeeGender");
				String EmployeeType=request.getParameter("EmployeeType");
				commonFilter.setFlid(flid);
				commonFilter.setRoleLevel(role);
				commonFilter.setRefdocid(pillarrole);
				commonFilter.setKey(TrainingId);
				commonFilter.setFactoryId(fnln);
				commonFilter.setUniquePos(uniq);
				commonFilter.setEmpwiseType(EmployeeType);
				commonFilter.setEmpch(EmployeeGender);
				List<String[]> uniqueEmplist = newTrainingcalendarService.getAllUniqueEmployeePopup(commonFilter);
				JSONObject uniquePOSEmp = UIUtils.convertToJqGridTableObject(uniqueEmplist, request, 1, 1,commonFilter.getTotalRecordCnt());
				out.println(uniquePOSEmp);
				httpSession.removeAttribute("NewuniqueGridCommonFilter");
				httpSession.setAttribute("NewuniqueGridCommonFilter",commonFilter);

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		/*else if(action.equals("entEmployeeAttendance_save.ntrc")){
			saveEmployeeAttendance(request,response);
		}*/
		else if(action.equals("entAddEmployeeUp_save.ntrc")){
			  saveEmployeeUp(request,response);
		}
		else if(action.equals("rating_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "Ratings"));
		}
		else if(action.equals("AnchoredBy_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "AnchoredBy"));
		}
		else if(action.equals("TrainingType_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "TrainingType"));
		}
	  // --------------------------By KIran -
	   
	   
		else if(action.equals("AssessmentReq_combo.ntrc")){
			
			
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "AssessmentReq"));
		}
	   
		else if(action.equals("MaterialReady_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "MaterialReady"));
		}
	   
		else if(action.equals("MarksReq_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "MarksReq"));
		}
		else if(action.equals("TrngCompleted_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "TrngCompleted"));
		}
	   
		else if(action.equals("AssmntCompleted_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "AssmntCompleted"));
		}
	   
		else if(action.equals("Ratings_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "Ratings"));
		}
	   
		else if(action.equals("AssmntType_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "AssmntType"));
		}
	   ///////////////////------------------
		else if(action.equals("TrainingType_combo.ntrc")){
			PrintWriter out = response.getWriter(); 
			out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NEWENTEmpAttendance", "TrainingType"));
		}
	   
		else if(action.equals("entAddEmployee_save.ntrc")){
			saveSessionEmployeeLink(request,response);
		}
		else if(action.equals("entAddEmployee_delete.ntrc")){
			    deleteEmployee(request,response);
		}
		 else if(action.equals("deleteDetailRecord.ntrc")){
			 deleteDetailRecord(request,response);
		 }
		 else if(action.equals("MultipleUniquePosition_save.ntrc")){
				SaveMultipleUniquePosition(request,response);
			}
		 else if(action.equals("NewuniquePositionLink_getCol.ntrc")){
			 try {
				    String TrainingKeyid = request.getParameter("TraKeyid");
					CommonMessage.debugMsg("The TrainingKeyid"+TrainingKeyid);
					List<String[]> facultyList = newTrainingcalendarService.getNewUniqPosData(TrainingKeyid);
					JSONObject jsonObject = getUniquePositionTableModel(facultyList);
					CommonMessage.debugMsg("Table model");
					CommonMessage.debugMsg("jsonObject "+jsonObject);
					PrintWriter  out = response.getWriter();
					out.println(jsonObject);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 } else if(action.equals("NewuniquePositionLink_getData.ntrc")){
			    PrintWriter out = response.getWriter();
			    String TrainingKeyid = request.getParameter("TraKeyid");
				CommonMessage.debugMsg("The TrainingKeyid"+TrainingKeyid);
				List<String[]> uniqposList = newTrainingcalendarService.getNewUniqPosData(TrainingKeyid);
			    JSONObject facultydata = UIUtils.convertToJqGridTableObject( uniqposList, request, 1, 0);
			    out.println(facultydata);
		 }
	   
		 else if(action.equals("NewTrainingCalender_save.ntrc")){
			 TrainingCalSave(request,response);
		 }
		 else if(action.equals("NewTrainingCalender_delete.ntrc")){
			 TrainingCalDelete(request,response);
		 }
		 
		 else if(action.equals("chkcompleted.ntrc")){
			 PrintWriter out = response.getWriter();
			 String keyid=request.getParameter("keyid");
			 CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
			 commonFilter.setKey(keyid);
			 String TrainingData  = newTrainingcalendarService.IsTrainingCompleted(commonFilter);
			 JSONObject json=new JSONObject();
			json.put("iscomplete", TrainingData);
			out.println(json);
			
		 }
		 
		 else if(action.equals("FacultyCheck.ntrc")){
			 PrintWriter out = response.getWriter();
			 String keyid=request.getParameter("keyid");
			 String faucltyid=request.getParameter("faclid");
			 CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
			 commonFilter.setKey(keyid);
			 commonFilter.setEmpch(faucltyid);
			 String faculty  = newTrainingcalendarService.FacultyCheck(commonFilter);
			 JSONObject json=new JSONObject();
			json.put("fcltycnt", Integer.parseInt(faculty));
			json.put("fcltyid", faucltyid);
			json.put("keyid", keyid);
			out.println(json);
			
		 }
		 else if(action.equals("chkSessionDate.ntrc")){
			 PrintWriter out = response.getWriter();
			 String keyid=request.getParameter("keyid");
			 String sessiondate=request.getParameter("sedte");
			 CommonMessage.debugMsg("The sessiondate"+sessiondate);
			 String frmtime=request.getParameter("frmtme");
			 CommonMessage.debugMsg("The frmtime"+frmtime);
			 String totime=request.getParameter("totme"); 
			 CommonMessage.debugMsg("The totime"+totime);
			 String sessionid=request.getParameter("sesid");
			 CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
			 commonFilter.setKey(keyid);
			 commonFilter.setDteOccuredto(sessiondate);
			 commonFilter.setDteAllotedfrm(frmtime);
			 commonFilter.setDteAllotedto(totime);
			 
			 String session  = newTrainingcalendarService.chkSessionDate(commonFilter);
			 JSONObject json=new JSONObject();
			json.put("sessioncnt", Integer.parseInt(session));
			json.put("sessiondate", sessiondate);
			json.put("keyid", keyid);
			json.put("frmtime", frmtime);
			json.put("totime", totime);
			json.put("sessionid", sessionid);
			out.println(json);
		 }
	   
		 else if(action.equals("MultipleUniqueAdd_input.ntrc")){
			 String Calendarflid=request.getParameter("Calendarflid");
			 CommonMessage.debugMsg("Functional Location"+Calendarflid);
			 String CalendarId=request.getParameter("CalendarId");
			 CommonMessage.debugMsg("CalendarId"+CalendarId);
			 String SectionId=request.getParameter("sectionId");
			 CommonMessage.debugMsg("SectionId"+SectionId);
			 String cellId=request.getParameter("cellId");
			 CommonMessage.debugMsg("cellId"+cellId);
			// String JHFlid=newTrainingcalendarService.getJHFlid(Calendarflid);
			// CommonMessage.debugMsg("JHFlid"+JHFlid);
            // request.setAttribute("JHFlid",JHFlid);
             request.setAttribute("CalendarId",CalendarId);
             request.setAttribute("Calendarflid",Calendarflid);
             request.setAttribute("SectionId",SectionId);
             request.setAttribute("cellId",cellId);
			 UIUtils.forwardRequest(request, response,"pages/newENT/MultipleUniqueAdd.jsp");
		 }
	   
		 else if (action.equals("MultipleUniqueAdd_getCol.ntrc")) {
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", true);
				String Calendarflid=request.getParameter("Calendarflid");
				CommonMessage.debugMsg("The Flid"+Calendarflid);
				String CalendarId=request.getParameter("CalendarId");
				CommonMessage.debugMsg("CalendarId"+CalendarId);
				commonFilter.setFlid(Calendarflid);
				commonFilter.setKey(CalendarId);
				CommonMessage.debugMsg("The commonFilter"+commonFilter);
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewUniquePosition", "AddMultipleUniquePos"));
				httpSession.removeAttribute("NewuniqueGridCommonFilter");
				httpSession.setAttribute("NewuniqueGridCommonFilter", commonFilter);
			}
	   
		 else if (action.equals("MultipleUniqueAdd_getData.ntrc")) {
				try {
					CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
					PrintWriter out = response.getWriter();
					String Calendarflid=request.getParameter("Calendarflid");
					CommonMessage.debugMsg("The Flid"+Calendarflid);
					String CalendarId=request.getParameter("CalendarId");
					String filters= request.getQueryString().toString();  //getParameter("filters");
					//Object object= JSONValue.parse(filters);
					
					//JSONObject jobject=(JSONObject)object;
					//String filterData=(String)jobject.get("data");
					CommonMessage.debugMsg(filters +"   CalendarId"+CalendarId);
					commonFilter.setFlid(Calendarflid);
					commonFilter.setKey(CalendarId);
					commonFilter.getGridFilter();
					List<String[]> uniqueEmplist = newTrainingcalendarService.gwtJHRoleUniquePos(commonFilter);
					JSONObject uniquePOSEmp = UIUtils.convertToJqGridTableObject(uniqueEmplist, request,0,0,commonFilter.getTotalRecordCnt());
					out.println(uniquePOSEmp);
					httpSession.removeAttribute("NewuniqueGridCommonFilter");
					httpSession.setAttribute("NewuniqueGridCommonFilter",commonFilter);

				} catch (Exception e) {
					CommonMessage.debugMsg(e.getMessage());
				}
			}
	   
		 else if(action.equals("chkUniQupostion.ntrc")){
			 PrintWriter out = response.getWriter();
			 String keyid=request.getParameter("keyid");
			 String Upid=request.getParameter("Upid");
			 String uniqukeyid=request.getParameter("uniqukeyid");
			 String chkuniq=request.getParameter("chkuni");
			 CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
			 commonFilter.setKey(keyid);
			 commonFilter.setUtil(Upid);
			
			 commonFilter.setKK(uniqukeyid);
			 commonFilter.setCellch(chkuniq);
			 
			 
			 String uniqueposition = newTrainingcalendarService.chkUniqueposition(commonFilter);
			 JSONObject json=new JSONObject();
			json.put("uniquecnt", Integer.parseInt(uniqueposition));
			json.put("chkuniq", chkuniq);
			json.put("keyid", keyid);
			json.put("Upid", Upid);
			json.put("uniqukeyid", uniqukeyid);
		
			out.println(json);
			
		 }
		 
		 else if(action.equals("chkEmployee.ntrc")){
			 PrintWriter out = response.getWriter();
			 String keyid=request.getParameter("keyid");
			 String empid=request.getParameter("empid");
			 String SessionId=request.getParameter("SessionId");
		    CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
			 commonFilter.setKey(keyid);
			 commonFilter.setUtil(empid);
			 commonFilter.setKK(SessionId);
			String empcount = newTrainingcalendarService.chkEmployee(commonFilter);
			 JSONObject json=new JSONObject();
			json.put("empcnt", Integer.parseInt(empcount));
			//json.put("chkuniq", chkuniq);
			json.put("keyid", keyid);
			json.put("empid", empid);
			json.put("SessionId", SessionId);
		
			out.println(json);
			
		 }
		 else if(action.equals("getjhforRole.ntrc")){
			 PrintWriter out = response.getWriter();
			 String roleid=request.getParameter("roleid");
			CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
			 commonFilter.setKey(roleid);
			
			  List<String[]> keyids = newTrainingcalendarService.chkJHforRole(commonFilter);
			 JSONObject returnData = new JSONObject();
			  JSONObject successData = new JSONObject();
			  successData.put("sect", keyids.get(0)[0]);
			  successData.put("cell", keyids.get(0)[1]);
			 
			  returnData.put("successData",successData);
			  out.print(returnData);   
			
		
		//	out.println(json);
			
		 }
	   
		 else if(action.equals("chkAssessmentCompleted.ntrc")){
			 PrintWriter out = response.getWriter();
			 String keyid=request.getParameter("keyid");
			// String empid=request.getParameter("empid");
			// String SessionId=request.getParameter("SessionId");
		    CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
			 commonFilter.setKey(keyid);
			 commonFilter.setMaintMode("chkassess");
			String cnt = newTrainingcalendarService.chkAssesmentComplted(commonFilter);
			 JSONObject json=new JSONObject();
			//json.put("empcnt", Integer.parseInt(empcount));
			//json.put("chkuniq", chkuniq);
			json.put("keyid", keyid);
			json.put("cnt", cnt);
			
		
			out.println(json);
			
		 }
	   
		 else if(action.equals("topic_fillcombo.ntrc")){
			 try {
					CommonMessage.debugMsg("TOPIC---mmmmmas");
					ComboFilter	comboFilter = UIUtils.fillComboFilter(request);
					String roleid = request.getParameter("roleId");
					String flid = request.getParameter("flId");
					String relTo = request.getParameter("relTo");
					String topicType = request.getParameter("topicType");
					//String location=CommonFunctions.getLoginLocaton(request);
					String location=request.getParameter("locnid");
					CommonMessage.debugMsg("location::::"+location);
					//relTo="GT";
					CommonMessage.debugMsg("relTo " +relTo);
					CommonFilter commonFilter = new CommonFilter();
					commonFilter.setFlid(flid);
					commonFilter.setLoss(location);
					commonFilter.setRange(roleid);//for Sending role id in commonfilter
					commonFilter.setRelatedToMchMld(topicType);//for Sending relatedtoTopic id in commonfilter
					List<ComboBox> topicData = newTrainingcalendarService.getTopic(commonFilter,comboFilter);
					UIUtils.writeComboBox(response,topicData,comboFilter);
					CommonMessage.debugMsg("mTOPIC");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		 }
	   
		 else if(action.equals("facultyCombo.ntrc"))
 			 
			{
	String loginFlid = CommonFunctions.getLoginFlid(request);

			CommonFilter  commonFilter = new CommonFilter();
			//String locnid;
		//	List<ComboBox> comboList = new ArrayList<ComboBox>();

			
						if( UIUtils.isValidKeyId(loginFlid)){
					commonFilter.setFlid(loginFlid);
			}
						ComboFilter comboFilter=UIUtils.fillComboFilter(request);		
				String location=request.getParameter("locnid");
			 CommonMessage.debugMsg("locnid"+location);
				if( UIUtils.isValidKeyId(location))
				{
					commonFilter.setAbnAllch(location);
				}else{
					
					String	locnid=CommonFunctions.getLoginLocaton(request);
						commonFilter.setAbnAllch(locnid);
					
					
				}
				
				List<ComboBox>	comboList = newTrainingcalendarService.getFacultyComboList(commonFilter,comboFilter);	
				UIUtils.writeComboBox(response,comboList,comboFilter);
				CommonMessage.debugMsg("mTOPIC");
			}
	   
	   
		 else if(action.equals("NewTrainingCalenderEditing_input.ntrc")){
			    String mode = request.getParameter("mode");
			    if(!UIUtils.isValidKeyId(mode)){
			        mode = "modify";
			    }
			    request.setAttribute("mode", mode);
			    UIUtils.forwardRequest(request, response,"/pages/newENT/NewTrainingCalendarModifyAM.jsp");
			}
		   else if(action.equals("TrainingCalendarEdit_input.ntrc")){
			    
			    String mode = request.getParameter("mode");
			    String Freqmode = request.getParameter("Freqmode");
			    String calid = request.getParameter("progid");
			    String flid = request.getParameter("flid");

			   
			    if(!UIUtils.isValidKeyId(mode) && UIUtils.isValidKeyId(calid)){
			        mode = "modify";
			    }
			    CommonMessage.debugMsg("TrainingCalendarEdit_input.ntrc -- resolved mode=" + mode + ", calid=" + calid);

			    String date = CommonFunctions.dateTimeNow();
			    String currentdate = UIUtils.getActualDateForm(date);
			    EntTlTragcalmst entTlTragcalmst = new EntTlTragcalmst();
			    if(!UIUtils.isValidKeyId(flid)){
			        flid = (String) httpSession.getAttribute("loginFlid");
			    }
			    if("modify".equals(mode) || ("view".equals(mode))){
			        entTlTragcalmst = newTrainingcalendarService.getselectdata(calid);
			        String empcount = newTrainingcalendarService.getempdata(calid);
			        String empattn = newTrainingcalendarService.getempattn(calid);

			        CommonFilter commonFilter = new CommonFilter();
			        String maxmarks = null;
			        String Cuttoff = null;
			        String cnt = null;
			        String assesType = null;
			        if(!empattn.equals("0")){
			            commonFilter.setKey(calid);
			            commonFilter.setMaintMode("modify");
			            maxmarks = newTrainingcalendarService.getMaxmarks(calid);
			            Cuttoff = newTrainingcalendarService.getCutoff(calid);
			            assesType = newTrainingcalendarService.getAssesType(calid);
			            //-----------------------------------gopi----------------------------------
			            cnt = newTrainingcalendarService.chkAssesmentComplted(commonFilter);
			           // cnt = newTrainingcalendarService.getAssessmentComStatus(calid);


			            request.setAttribute("asscnt", Integer.parseInt(cnt));
			        }
			        request.setAttribute("empcount", Integer.parseInt(empcount));
			        request.setAttribute("entTlTragcalmst", entTlTragcalmst);
			        request.setAttribute("maxmarks", maxmarks);
			        request.setAttribute("Cuttoff", Cuttoff);
			        request.setAttribute("assesType", assesType);
			        request.setAttribute("empattn", Integer.parseInt(empattn));
			        httpSession.setAttribute("entTlTragcalmst_Servlet", entTlTragcalmst);
			    }

			    request.setAttribute("mode", mode);
			    request.setAttribute("rolename", rolename);
			    request.setAttribute("currentdate", currentdate.substring(0,11));
			    
			    // Puthu JSP ku forward pannuradhu
			    UIUtils.forwardRequest(request, response, "/pages/newENT/TrainingCalendarEdit.jsp");
			}
	   
		   else if(action.equals("NewTrainingCalenderEditing_getCol.ntrc")){
			    PrintWriter out = response.getWriter();
			    CommonFilter commonFilter=new CommonFilter();
			    JSONObject jsonObject = new JSONObject();
			    commonFilter  = populateCommonFilter(request,"TrainingCommonFilter",true);   
			    try
			    {  
			        String flid=request.getParameter("flid");
			        String mode = request.getParameter("frmMode");
			        CommonMessage.debugMsg("mode"+mode);
			        commonFilter.setType(mode);
			        if(UIUtils.isValidKeyId(flid))
			            commonFilter.setFlid(flid);
			        String keyid=request.getParameter("keyid");
			        if(UIUtils.isValidKeyId(keyid))
			            commonFilter.setKey(keyid);

			        commonFilter.setIsGetCol("Y");
			        List<String[]> AuditListGrid = newTrainingcalendarService.getTrainingCalendarList(new GridParams(),commonFilter);

			        JqGridTableModel jqGridTableModel = new JqGridTableModel();           
			        GridColModel gridColModel = new GridColModel();           

			        jqGridTableModel.setSortable(true);
			        jqGridTableModel.setTableButton(true);
			        jqGridTableModel.setEnableFilter(true);
			        jqGridTableModel.setRowNumbers(true);
			        gridColModel.setHeaderNum(1);
			        gridColModel.setFormattorFromCol("0");
			        gridColModel.setFormattorToCol("0");

			        String [] colHeaderHead = AuditListGrid.get(0);
			        String [] colHeader = AuditListGrid.get(1);
			        List<String[]> headers = new ArrayList<String[]>();   
			        headers.add(colHeader);           
			        jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);           
			        jsonObject.set("tableWidth", "108%%");
			        jsonObject.set("tableHeight", "80%%");
			        httpSession.removeAttribute("TrainingColmodel");
			        httpSession.setAttribute("TrainingColmodel",jsonObject);   
			        out.println(jsonObject);                  

			    }catch(Exception e)
			    {
			        CommonMessage.debugMsg(e.getMessage());
			    }
			}
		   else if(action.equals("NewTrainingCalenderEditing_getData.ntrc")){
			    
			    PrintWriter out = response.getWriter();
			    CommonFilter commonFilter  = populateCommonFilter(request,"TrainingCommonFilter",false);   
			    try
			    {  
			        UIUtils.displayRequestParamsValue(request);   
			        String flid = request.getParameter("flid");
			        if (UIUtils.isValidKeyId(flid)) {
			            commonFilter.setFlid(flid);
			        }

			        String mode = request.getParameter("frmMode");
			        commonFilter.setType(mode);
			        commonFilter.setFlid(flid);
			        String keyid=request.getParameter("keyid");
			        commonFilter.setKey(keyid);

			        GridParams gridparams = new GridParams();
			        FilterValues.getCommonFilters(request, commonFilter);

			        CommonMessage.debugMsg("getdata ");
			        commonFilter.setIsGetCol("N");
			        List<String[]> Grid = newTrainingcalendarService.getTrainingCalendarList(gridparams,commonFilter);

			        CommonMessage.debugMsg("equipmentQueryList " + Grid.size());
			        JSONObject Data = UIUtils.convertToJqGridTableObject(Grid,request,2,0,commonFilter.getTotalRecordCnt());
			        commonFilter.setViewClick('N'); 
			        httpSession.removeAttribute("TrainingCommonFilter");
			        httpSession.setAttribute("TrainingCommonFilter", commonFilter);
			        out.println(Data); 
			    }catch(Exception e)
			    {
			        CommonMessage.debugMsg(e.getMessage());
			    }
			}

		   else if(action.equals("TrainingCalModifyPopup_input.ntrc")){
			    String keyid = request.getParameter("progid");
			    request.setAttribute("keyid", keyid);
			    UIUtils.forwardRequest(request, response,"/pages/newENT/TrainingCalendarModifyPopup.jsp");
			}

		   else if(action.equals("resetAssessmentAM.ntrc")){
			     PrintWriter out = response.getWriter();
			     String keyid = request.getParameter("keyid");
			     CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
			     commonFilter.setKey(keyid);
			     String result = newTrainingcalendarService.resetAssessmentForMaintenance(commonFilter);
			     JSONObject json = new JSONObject();
			     json.put("keyid", keyid);
			     json.put("result", result);
			     json.put("msg", "Training Calendar reopened for modification");
			     out.println(json);
			 }

		   else if(action.equals("TrainingCalendarEdit_save.ntrc")){
			     TrainingCalSave(request,response);
			}

	   
   }
   
   //-------------------Added by Gopi----------------------------------------------------
   private String[] appendColumn(String[] arr, String value){
	    String[] newArr = new String[arr.length+1];
	    System.arraycopy(arr,0,newArr,0,arr.length);
	    newArr[arr.length]=value;
	    return newArr;
	}

 
   private void deleteEmployee(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ServletOutputStream out = response.getOutputStream();
		String EmpmKeyid=request.getParameter("EmpmKeyid");
		HttpSession httpSession=request.getSession(false);
		EntTlTrgCalEmp entTlTrgCalEmp=new EntTlTrgCalEmp();
		
		EntTlTrgCalEmp existentTlTrgCalEmp=(EntTlTrgCalEmp)httpSession.getAttribute("EntTlTrgCalEmp");
		if(EmpmKeyid!=null){
			entTlTrgCalEmp.setEtceKeyid(EmpmKeyid);
		}
		entTlTrgCalEmp=(EntTlTrgCalEmp)UIUtils.setBeanProperties((Object)entTlTrgCalEmp, request);
		existentTlTrgCalEmp=newTrainingcalendarService.EmployeeDelete(entTlTrgCalEmp);
		JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",false);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
	}
   private void saveEmployeeUp(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession httpSession = request.getSession(false);
   	ServletOutputStream out = response.getOutputStream();
   	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
   	
   	if( httpSession != null && user != null)
   	{	
   		
   		EntTlTrgCalEmp existEntTlTrgCalEmp = (EntTlTrgCalEmp)httpSession.getAttribute("EntTlTrgCalEmp"); 
   		EntTlTrgCalEmp newEntTlTrgCalEmp=new EntTlTrgCalEmp();
	    String saveMsg=null;	
   		newEntTlTrgCalEmp=(EntTlTrgCalEmp)UIUtils.setBeanProperties((Object)newEntTlTrgCalEmp,request); 	  		
   		String MasterId=request.getParameter("Mstkeyid");
	    String empId=request.getParameter("upEmpId");
	    String SessionId=request.getParameter("SessionId");	
	    	
	    newEntTlTrgCalEmp.setEtceCreatedby(user.getUsrm_ccno());
	    	if(UIUtils.isValidKeyId(empId))
	    		newEntTlTrgCalEmp.setEtceEmpmKeyid(empId);
   		   if(UIUtils.isValidKeyId(MasterId))
   			 newEntTlTrgCalEmp.setEtceEtcmKeyid(MasterId);
   		   if(UIUtils.isValidKeyId(SessionId))
   			   newEntTlTrgCalEmp.setEtceEtcsKeyid(SessionId);
               
   		try{
   			
   			if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceKeyid())){	
   			existEntTlTrgCalEmp=newTrainingcalendarService.EmployeebyCreate(newEntTlTrgCalEmp, existEntTlTrgCalEmp);
   				saveMsg="Data Saved Successfully";
   			}
   			else{
   				saveMsg="Data Updated Successfully";
   			}
				JSONObject persistentData = new JSONObject(); 
				JSONObject forwardData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);		
				out.print(returnData.toString());
				
				
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
   private void deleteDetailRecord(HttpServletRequest request,HttpServletResponse response) throws Exception,BusinessApplicationExceptions {
	     PrintWriter out = response.getWriter();
	     String keyId = request.getParameter("keyid");
	     CommonMessage.debugMsg("The keyId"+keyId);
		 String gridId = request.getParameter("gridId");
		 CommonMessage.debugMsg("The gridId"+gridId);
		 String TrainingId = request.getParameter("TraKeyid");
		 CommonMessage.debugMsg("The TrainingId"+TrainingId);
		 try{
		 String delSuc =newTrainingcalendarService.deleteDetailRecord(keyId,gridId,TrainingId);
		 JSONObject json = new JSONObject();
		 json.put("msg",delSuc);
		 json.put("gridid",gridId);
			 out.print(json.toString());
		}catch(BusinessApplicationExceptions e)
			{	
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				successData.put("gridId",gridId);
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("msg"," Record Can't be Deleted Reference Found");
				out.print(returnData.toString());
			}
	}
   

   @SuppressWarnings("unchecked")
   private void SaveMultipleUniquePosition(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidationExceptions{
			HttpSession httpsession=request.getSession(false);
		    ServletOutputStream out=response.getOutputStream();
		    AdmTlUsermst user=UIUtils.getLoginUser(request);
		    String saveMsg=null;
		    try{
		    	    if(httpsession!=null && user!=null){
		    	    	
		    		String paramJsonArr=request.getParameter("paramconvertArr");
		    		CommonMessage.debugMsg("paramJsonArr"+paramJsonArr);
		    		String CalendarId=request.getParameter("CalendarId");
		    		String SectionId=request.getParameter("SectionId"); 
		    		CommonMessage.debugMsg("SectionId"+SectionId);
		    		String CellId=request.getParameter("CellId");
		    		CommonMessage.debugMsg("CellId"+CellId);
		    		
		    		EntTlTrgCalUnqp entTlTrgCalUnqp=new EntTlTrgCalUnqp();
		    		
		    		JSONArray UniqueList=null; 
		    		List<EntTlTrgCalUnqp> UniqueAddList = null;	
		    		if(UIUtils.isValidKeyId(paramJsonArr)){
		    			UniqueList=JSONArray.fromString(paramJsonArr);
		    		    CommonMessage.debugMsg("UniqueList"+UniqueList);
		    		    UniqueAddList=(List<EntTlTrgCalUnqp>)UIUtils.convertJSONArrToList(entTlTrgCalUnqp,UniqueList);		    		   
		    			int j;
		    			for(j=0;j<UniqueAddList.size();j++)
		    			{
		    				
		    				if(UniqueAddList.get(j).getEtcuEtcmKeyid()==null)
			    			{
		    					UniqueAddList.get(j).setEtcuEtcmKeyid(CalendarId);
			    			}
		    			/*	if(UniqueAddList.get(j).getEtcuRoleDmt()==null)
			    			{
		    					UniqueAddList.get(j).setEtcuRoleDmt(SectionId);
			    			}
		    				if(UniqueAddList.get(j).getEtcuRoleJh()==null){
		    					UniqueAddList.get(j).setEtcuRoleJh(CellId);	
		    				}*/
		    				
		    				UniqueAddList.get(j).setEtcuCreatedby(user.getUsrm_ccno());
		    			}
		    			UniqueAddList=newTrainingcalendarService.CreateMultipleUnique(UniqueAddList);
		    			saveMsg="Data Saved Successfully";
		    		}
		    	    }
		    	    JSONObject SuccessData=new JSONObject();
			    	SuccessData.put("msg",saveMsg);
			    	SuccessData.put("formClear",false);
			    	JSONObject returnData=new JSONObject();
			    	returnData.put("successData", SuccessData);	
			    	returnData.put("formClear",false);
			    	out.print(returnData.toString());
			    	out.close();
		    	}
		    
		    catch (Exception e) {
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}   
		}

   @SuppressWarnings("unchecked")
   private void saveSessionEmployeeLink(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidationExceptions{
			HttpSession httpsession=request.getSession(false);
		    ServletOutputStream out=response.getOutputStream();
		    AdmTlUsermst user=UIUtils.getLoginUser(request);
		    String saveMsg=null;
		    try{
		    	    if(httpsession!=null && user!=null){
		    	    	
		    		String paramJsonArr=request.getParameter("paramconvertArr");
		    		CommonMessage.debugMsg("paramJsonArr"+paramJsonArr);
		    		String SessionId=request.getParameter("sessionid");
		    		String MasterId=request.getParameter("keyid");
		    		EntTlTrgCalEmp Employeelink= new EntTlTrgCalEmp();
		    		JSONArray EmployeeList=null; 
		    		List<EntTlTrgCalEmp> EmployeeAddList = null;	
		    		if(UIUtils.isValidKeyId(paramJsonArr)){
		    		  EmployeeList=JSONArray.fromString(paramJsonArr);
		    		  CommonMessage.debugMsg("EmployeeList"+EmployeeList);
		    		   EmployeeAddList=(List<EntTlTrgCalEmp>)UIUtils.convertJSONArrToList(Employeelink,EmployeeList);		    		   
		    			int j;
		    			for(j=0;j<EmployeeAddList.size();j++)
		    			{
		    				if(EmployeeAddList.get(j).getEtceEtcsKeyid()==null)
			    			{
		    					EmployeeAddList.get(j).setEtceEtcsKeyid(SessionId);
			    			}
		    				if(EmployeeAddList.get(j).getEtceEtcmKeyid()==null)
			    			{
		    					EmployeeAddList.get(j).setEtceEtcmKeyid(MasterId);
			    			}
		    				EmployeeAddList.get(j).setEtceCreatedby(user.getUsrm_ccno());
		    			}
		    			EmployeeAddList=newTrainingcalendarService.createSessionEmployee(EmployeeAddList);
		    			saveMsg="Data Saved Successfully";
		    		}
		    	    }
		    	    JSONObject SuccessData=new JSONObject();
			    	SuccessData.put("msg",saveMsg);
			    	SuccessData.put("formClear",false);
			    	JSONObject returnData=new JSONObject();
			    	returnData.put("successData", SuccessData);	
			    	returnData.put("formClear",false);
			    	out.print(returnData.toString());
			    	out.close();

		    	}
		    
		    catch (Exception e) {
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}   
		}
   
   private void TrainingCalDelete(HttpServletRequest request,HttpServletResponse response)throws IOException{
    	HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	if( httpSession != null && user != null)
		    	{	EntTlTragcalmst existEntTlTragcalmst = (EntTlTragcalmst)httpSession.getAttribute("entTlTragcalmst");
		    	EntTlTragcalmst newEntTlTragcalmst = new EntTlTragcalmst();
		    	newEntTlTragcalmst = (EntTlTragcalmst)UIUtils.setBeanProperties((Object)newEntTlTragcalmst,request);
		    	try{
		    		 existEntTlTragcalmst =	newTrainingcalendarService.deleteTrainingCal(newEntTlTragcalmst);
		    		httpSession.setAttribute(existEntTlTragcalmst.getEtcmKeyid(), existEntTlTragcalmst);		
					JSONObject mode = new JSONObject();
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("TraCalId",newEntTlTragcalmst.getEtcmKeyid());					
					JSONObject successData = new JSONObject();
					successData.put("errMsg",false);
					successData.put("TraCalId",newEntTlTragcalmst.getEtcmKeyid());
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
					successData.put("keyid",existEntTlTragcalmst.getEtcmKeyid());
					JSONObject returnData = new JSONObject();						
					returnData.put("successData",successData);
					returnData.put("formClear", true);	
					CommonMessage.debugMsg("successData"+successData);		
					out.print(returnData.toString());
					CommonMessage.debugMsg("end of Training save");
		    	}
			catch(Exception e){
				CommonMessage.debugMsg("Exception: master "+newEntTlTragcalmst.getEtcmKeyid());
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");			
				JSONObject successData = new JSONObject();
				successData.put("Keyid",newEntTlTragcalmst.getEtcmKeyid());	
				successData.put("errMsg", true);
				successData.put("errMsg", true);
				successData.put("msg"," Record Can't be Deleted Reference Found");
				err.put("successData", successData);		
				e.printStackTrace();
				out.print(err.toString());
			}
		  }  
	   
   }
   
   


   private JSONObject getfacultyTableModel(List<String[]> facultyList) {
		// TODO Auto-generated method stub
   	    JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
   	    
		//String[] colHeader = facultyList.get(0);		
   	    // vignesh changing
   	  String[] colHeader = new String[] {
   	        "DUMMY",            // 0  -> was '' column
   	        "ftym_empm_keyid",  // 1
   	        "etcf_keyid",       // 2
   	        "Faculty",          // 3
   	        "Delete"            // 4
   	    };
		 
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(false);
		jqGridTableModel.setTableHeight(500);  
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
				jqGridColModel.setWidth(40);
				jqGridColModel.setAlign("left");
			}
		
			
			if(i==colHeader.length-1){
				jqGridColModel.setFormatter("BtnFormatterDelete");
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("center");
			} 
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "40%%");
		tableModel.set("tableWidth", "35%%");
		return tableModel;

	}
   
  private JSONObject getTableModel(List<String[]> headers) {
               
         JqGridTableModel jqGridTableModel = new JqGridTableModel();
         String[] colHeader = headers.get(1);
         jqGridTableModel.setEnableFilter(true);
         jqGridTableModel.setRowNumbers(true);
         jqGridTableModel.setTableButton(true);
         jqGridTableModel.setTableHeight(500);
         jqGridTableModel.setTableWidth(500);

         for (int i = 0; i < colHeader.length; i++) {
         
             JqGridColModel jqGridColModel = new JqGridColModel();
             jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
             jqGridColModel.setWidth(0);
             jqGridColModel.setAlign("left");
             jqGridColModel.setEditable(false);
             if((i == 0) || (i == 1) ){
             	 jqGridColModel.setHidden(true);	 
              }
            
           
            if(i==4){
         	    jqGridColModel.setWidth(150);
                //jqGridColModel.setAlign("left");
                jqGridColModel.setHidden(true);	
            }
            
            if(i==5){
         	   jqGridColModel.setWidth(150);
                jqGridColModel.setAlign("left");
            }
            if(i==6){
           	   jqGridColModel.setWidth(250);
                  jqGridColModel.setAlign("left");
              }
            if(i==7){
         	   jqGridColModel.setWidth(250);
                jqGridColModel.setAlign("left");
            }
            if(i==8){
           	   jqGridColModel.setWidth(250);
                  jqGridColModel.setAlign("left");
              }
           
            jqGridTableModel.getColModel().add(jqGridColModel);
         }

         JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
         tableModel.set("tableHeight", "80%%");
         tableModel.set("tableWidth", "80%%");
         return tableModel;
	}  
   private JSONObject getUniquePositionTableModel(List<String[]> facultyList) {
 		// TODO Auto-generated method stub
    	JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
 		
 		String[] colHeader = facultyList.get(0);		
 		 
 		jqGridTableModel.getRowHeaders().add(colHeader);
 		jqGridTableModel.setRowNumbers(false);
 		jqGridTableModel.setTableHeight(500);  
 		jqGridTableModel.setTableWidth(600);
 		jqGridTableModel.setTableButton(false);
 		jqGridTableModel.setRowNumbers(true);
 		for (int i = 0; i < colHeader.length; i++) {
 			JqGridColModel jqGridColModel = new JqGridColModel();
 			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
 			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")); 
 			jqGridColModel.setWidth(300);
 			jqGridColModel.setAlign("left");
 			jqGridColModel.setEditable(false);	 
 			
 			 if(i==0){
 				    jqGridColModel.setHidden(true);
				    jqGridColModel.setAlign("left");	
					jqGridColModel.setWidth(80);
				 }
 			 if(i==1){
 				    jqGridColModel.setHidden(true);
				    jqGridColModel.setAlign("left");	
					jqGridColModel.setWidth(80);
				 }
 			 if(i==2){
 				    jqGridColModel.setHidden(false);
				    jqGridColModel.setAlign("left");	
					jqGridColModel.setWidth(180);
				 }
 			 if(i==3){
 				   jqGridColModel.setHidden(false);
				    jqGridColModel.setAlign("left");	
					jqGridColModel.setWidth(160);
				 }
 			if(i==4){
 				jqGridColModel.setHidden(false);
			    jqGridColModel.setAlign("left");	
				jqGridColModel.setWidth(150);
			 }
 			if(i==colHeader.length-1){
 				jqGridColModel.setFormatter("BtnFormatterDelete");
 				jqGridColModel.setWidth(70);
 				jqGridColModel.setAlign("center");
 			}
 			jqGridTableModel.getColModel().add(jqGridColModel);
 		}

 		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
 		tableModel.set("tableHeight", "35%%");
 		tableModel.set("tableWidth", "35%%");
 		return tableModel;

 	}
   @SuppressWarnings("unchecked")
   private void TrainingCalSave(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
	    ServletOutputStream out = response.getOutputStream();
	    AdmTlUsermst user = UIUtils.getLoginUser(request);
	    String calendardate=request.getParameter("dteSessiondate");
	    String caldate=request.getParameter("dteEtcmCalendarDate");
	    String fromtime=request.getParameter("spnsessionFromTime");
	    String totime=request.getParameter("spnsessionTillTime");
	    String savemode=request.getParameter("Savetype");
	    CommonMessage.debugMsg("the savemode"+savemode);
	    String masterid=request.getParameter("txtEtcmKeyid");
	  String sesId=request.getParameter("sesId");
	  if (!UIUtils.isValidKeyId(sesId)) {
		    sesId = request.getParameter("txtEtcsKeyid");
		}
		CommonMessage.debugMsg("TrainingCalSave – raw sesId param = '" + sesId + "'");
	String GridList=request.getParameter("paramJsonArrConvert");
	  CommonMessage.debugMsg("the GridList ++++++++ " +GridList);
	String savemsg=null;
	String updateMsg=null;
	EntTlTragcalmst existEntTlTragcalmst = (EntTlTragcalmst)httpSession.getAttribute("entTlTragcalmst");
   
	//-- vignesh -------- 09dec2025 ----------//
	
	try{
	if(GridList==null)
	{
	    if(savemode==null)
	    	savemode="nofaculty";
	    String fromdate=calendardate+" "+fromtime;
	    String todate=calendardate+" "+totime;


	if( httpSession != null && user != null)
	{	
		    EntTlTragcalmst newentTlTragcalmst = new EntTlTragcalmst();

				//*SessionMst*//
			EntTlTrgCalSession newentTlTrgCalSession = new EntTlTrgCalSession();
			 if(masterid!=null)
		  	    {
		  	    newentTlTrgCalSession.setEtcsEtcmKeyid(masterid);
		  	    }
			EntTlTrgCalSession existntTlTrgCalSession = (EntTlTrgCalSession)httpSession.getAttribute("entTlTrgCalSession");
			
			//**Faculty*//
			EntTlTrgFaculty newentTlTrgFaculty= new EntTlTrgFaculty();
			newentTlTrgFaculty.setEtcfCreatedby(user.getUsrm_ccno());
			if(masterid!=null)
	  	    {
				newentTlTrgFaculty.setEtcfEtcmKeyid(masterid);
	  	    }
			EntTlTrgFaculty existntTlTrgFaculty=(EntTlTrgFaculty)httpSession.getAttribute("entlTrainingFaculty");
			
			//*UnqpMst*//		
			EntTlTrgCalUnqp newentTlTrgCalUnqp=new EntTlTrgCalUnqp();
			newentTlTrgCalUnqp.setEtcuCreatedby(user.getUsrm_ccno());
			EntTlTrgCalUnqp existentTlTrgCalUnqp=(EntTlTrgCalUnqp)httpSession.getAttribute("entTlTrgCalUnqp");
			if(masterid!=null)
	  	    {
				newentTlTrgCalUnqp.setEtcuEtcmKeyid(masterid);
	  	    }
			
			newentTlTragcalmst.setEtcmCreatedBy(user.getUsrm_ccno());
			newentTlTragcalmst =(EntTlTragcalmst)UIUtils.setBeanProperties((Object)newentTlTragcalmst,request);
			
			newentTlTrgCalSession = (EntTlTrgCalSession)UIUtils.setBeanProperties((Object)newentTlTrgCalSession,request);
			//}
			
			newentTlTrgFaculty = (EntTlTrgFaculty)UIUtils.setBeanProperties((Object)newentTlTrgFaculty,request);
			newentTlTrgCalUnqp=(EntTlTrgCalUnqp)UIUtils.setBeanProperties((Object)newentTlTrgCalUnqp,request);
			
		 	
		 	if(UIUtils.isValidKeyId(newentTlTrgCalSession.getIsSession()))
		 	
		 		CommonMessage.debugMsg("Inside the Session");
		 		//newentTlTrgCalSession.setEtcsFromDate(newentTlTrgCalSession.getEtcsTillDate()+" "+newentTlTrgCalSession.getSessionFromTime());
		 		//newentTlTrgCalSession.setEtcsTillDate(newentTlTrgCalSession.getEtcsTillDate()+ " "+newentTlTrgCalSession.getSessionTillTime());
		 	  if(newentTlTragcalmst.getEtcmKeyid()==null)
		 	  {  
		 	
		 	   newentTlTrgCalSession.setEtcsFromDate(fromdate);
		 	   newentTlTrgCalSession.setEtcsTillDate(todate);
		 	   newentTlTrgCalSession.setEtcsSessionDate(caldate);
		 	   
		 	   
		 	  }
		 	  else{
		 		 newentTlTrgCalSession.setEtcsFromDate(fromdate);
			 	   newentTlTrgCalSession.setEtcsTillDate(todate);
			 	   newentTlTrgCalSession.setEtcsSessionDate(calendardate);
			 	   
			 	   
		 	  }
		 	   if(savemode.equals("Faculty"))
			     {
			    	newentTlTrgCalSession.setEtcsName("Faculty");
			     }
		 	   
		 	   // replacing savemode session block -- vignesh 
		 	   
		 	  if (savemode.equals("Session")) {
		 		    newentTlTrgCalSession.setEtcsName("Session");

		 		    // sesId is passed from JSP as &sesId=...
		 		    if (UIUtils.isValidKeyId(sesId)) {
		 		        // ✅ Set the key on the SAME session object that you will pass to DAO
		 		        newentTlTrgCalSession.setEtcsKeyid(sesId.trim());
		 		        CommonMessage.debugMsg("TrainingCalSave – editing existing session, sesId=" + sesId);
		 		    } else {
		 		        CommonMessage.debugMsg("TrainingCalSave – creating NEW session (no sesId)");
		 		    }
		 		}

		 	 CommonMessage.debugMsg("TrainingCalSave – final sessionMaster key="
		 		    + newentTlTrgCalSession.getEtcsKeyid()
		 		    + ", name=" + newentTlTrgCalSession.getEtcsName());

		 		
		 	// newentTlTragcalmst.setsessionMaster(newentTlTrgCalSession);
		 	 
//		 	   if(savemode.equals("Session"))
//			     {
//			    	newentTlTrgCalSession.setEtcsName("Session");
//			    	if(sesId!=null)
//			    			{
//			    		// ----commneted first line and added new if block by vignesh
//			    	//	newentTlTrgCalSession.setEtcsKeyid(sesId);
//			    		  if (newentTlTragcalmst.getsessionMaster() == null) {
//			    		        newentTlTragcalmst.setsessionMaster(new EntTlTrgCalSession());
//			    		    }
//			    		    newentTlTragcalmst.getsessionMaster().setEtcsKeyid(sesId.trim());
//			    		  
//			    			}
//			    
//			     }
		 	
		 	
		         newentTlTragcalmst.setsessionMaster(newentTlTrgCalSession);
		         
		         String fac=newentTlTrgFaculty.getIsFacultyLink();
		         CommonMessage.debugMsg("inside the faculty lik"+fac);
		 		if(UIUtils.isValidKeyId(newentTlTrgFaculty.getIsFacultyLink())){
		 			CommonMessage.debugMsg("inside the faculty lik");
		 		newentTlTragcalmst.setFaculty(newentTlTrgFaculty);
		 		}
		 		if(UIUtils.isValidKeyId(newentTlTrgCalUnqp.getIsUniquePosition())){
		 			newentTlTragcalmst.setRoleLink(newentTlTrgCalUnqp);
		 			newentTlTrgCalUnqp.setEtcuRoleDmt(newentTlTrgCalUnqp.getEtcuRoleDmt());
		 			newentTlTrgCalUnqp.setEtcuRoleJh(newentTlTrgCalUnqp.getEtcuRoleJh());
		 		}
		 		String allUnique=request.getParameter("allUnique");
		 		if(UIUtils.isValidKeyId(allUnique))
		 			newentTlTragcalmst.setAllUniquePosition(allUnique);
		 		
			if( ! UIUtils.isValidKeyId(newentTlTragcalmst.getEtcmKeyid()))
			{	
				 if (!UIUtils.isValidKeyId(newentTlTrgFaculty.getIsFacultyLink()))
			     { 
			    	 CommonMessage.debugMsg(" entering if block inside create");
			 			
			    	throw new Exception("Please add faculty before saving training calendar.");
			      
			     }else {
			    	 existEntTlTragcalmst =	newTrainingcalendarService.create(newentTlTragcalmst,existEntTlTragcalmst);
					 savemsg="Data Saved Successfully";
			     }
					 
			}
			else{  
				     
				     existEntTlTragcalmst= newTrainingcalendarService.update(newentTlTragcalmst,existEntTlTragcalmst);
					 savemsg="Data Updated Successfully";	
			}
			
			    httpSession.setAttribute("TraKeyAfterSave", existEntTlTragcalmst.getEtcmKeyid());
	}
	
	JSONObject successData = new JSONObject();
	successData.put("msg",savemsg);
	JSONObject returnData = new JSONObject();
	returnData.put("successData", successData);
	returnData.put("TraCalId",existEntTlTragcalmst.getEtcmKeyid());
	// vignesh chnaged 20Feb2026
//	returnData.put("TraCreateTime",CommonFunctions.pg_getDateTimeFromPGTimeStamp(existEntTlTragcalmst.getEtcmCreatedDateTime()));
	returnData.put("TraCreateTime",existEntTlTragcalmst.getEtcmCreatedDateTime());
	
	returnData.put("savemode",savemode);
	returnData.put("formClear", false);
	httpSession.setAttribute("entTlTragcalmst", existEntTlTragcalmst);
	out.print(returnData.toString());
	
	}
	else{
		String cutoff=request.getParameter("CutOff");
		String max=request.getParameter("max");
		String typ=request.getParameter("typ");
		String keyid=request.getParameter("kid");
		String topicid=request.getParameter("tid");
		String locnid=request.getParameter("lid");
		String flid=request.getParameter("flid");
		String assess=request.getParameter("chkEtcaAssessmentCom");
		String createDateTime = request.getParameter("txtEtcmCreatedDateTime");
		if(assess==null)
		{
			assess="N";
		}
		CommonFilter commonfilter=new CommonFilter();
		commonfilter.setKey(keyid);
		commonfilter.setFlid(flid);
		commonfilter.setLossId(locnid);
		commonfilter.setTopicid(topicid);//user.getUsrm_ccno();
		commonfilter.setChkExternal(user.getUsrm_ccno());
		EntTlTtgCalEmpatScore EmployeeAttendancelink= new EntTlTtgCalEmpatScore();
		EmployeeAttendancelink.setEtcaCreatedby(user.getUsrm_ccno());
		JSONArray EmployeeAttendanceList=null;
		if(UIUtils.isValidKeyId(GridList)){
		  EmployeeAttendanceList=JSONArray.fromString(GridList);
		   List<EntTlTtgCalEmpatScore> EmployeeAddList=(List<EntTlTtgCalEmpatScore>)UIUtils.convertJSONArrToList(EmployeeAttendancelink,EmployeeAttendanceList);		    		   
		   
		   int i=0;
		   for(i=0;i<EmployeeAddList.size();i++)
		   {
			   EmployeeAddList.get(i).setEtcaCutOff(cutoff);
			   EmployeeAddList.get(i).setEtcaMaxMarks(max);
			   EmployeeAddList.get(i).setEtcaType(typ);
			   EmployeeAddList.get(i).setEtcaAssessmentCom(assess);
			   EmployeeAddList.get(i).setEtcaCreatedby(user.getUsrm_ccno());			  
		   }
		 
		   EmployeeAddList=newTrainingcalendarService.createEmployeeAttendance(EmployeeAddList,commonfilter);  
		   String empattn=newTrainingcalendarService.getempattn(masterid);
		   updateMsg="Data Saved Successfully";
		   
		    JSONObject SuccessData=new JSONObject();
	    	SuccessData.put("msg",updateMsg);
	    	JSONObject returnData=new JSONObject();
	    	returnData.put("formClear", false);
	    	returnData.put("successData", SuccessData);
	    	returnData.put("TraCalId",masterid);
	    	//-- Vignesh changed 
	    	returnData.put("TraCreateTime",createDateTime);
	   
	    	
	    	returnData.put("savemode","Employee Attendance");
	    	returnData.put("TraCalAttId",empattn);
	    	out.print(returnData.toString());
		}
	}			
  }
	catch (ValidationExceptions e) {
		net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "NewTrainingCalendar");
		out.print(errMessage.toString());
	}
	/*
	 * catch(Exception e){ e.printStackTrace(); JSONObject err = new JSONObject();
	 * err.put("tpmException", "Data Not Saved"); out.print(err.toString()); }
	 */
	catch(Exception e){
	    e.printStackTrace();

	    String errMsg = e.getMessage();

	    if (errMsg == null || errMsg.trim().length() == 0) {
	        errMsg = "Data Not Saved";
	    }

	    JSONObject err = new JSONObject();
	    err.put("tpmException", errMsg);
	    out.print(err.toString());
	}
} 

   private JSONObject getsessionTableModel(List<String[]> batchList) {
		// TODO Auto-generated method stub
       JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
		
		String[] colHeader = batchList.get(0);		
		 
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(false);
		jqGridTableModel.setTableHeight(500);  
		jqGridTableModel.setTableWidth(400);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setRowNumbers(true);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")); 
			jqGridColModel.setWidth(220);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);	 
			
			if (i <=3 ) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			
			if(i == 1)  {
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			if(i == 2)  {
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			
			if(i == 3)  {
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}

			if(i == 4)  {
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			if(i == 5 )  {
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			
			/*if(i==colHeader.length-2){
				jqGridColModel.setFormatter("btnEmpFormater");
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("center");
			}*/
			if(i==colHeader.length-1){
				jqGridColModel.setFormatter("BtnFormatterDelete");
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			}
			 
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "40%%");
		tableModel.set("tableWidth", "35%%");
		return tableModel;
	}
   
   private CommonFilter populateCommonFilter(HttpServletRequest request,
			String string, boolean b){
	       
	  HttpSession httpSession = request.getSession(false);

		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(string);
		if( commonFilter != null && ! b ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(string);
			httpSession.setAttribute(string, commonFilter);
		}
		return commonFilter;
	}
   
   
   
}
