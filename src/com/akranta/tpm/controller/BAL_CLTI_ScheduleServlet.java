/*Author : Manikandan*/
package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.mail.internet.InternetAddress;
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

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_JhclitCalendarBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.BAL_KznTlMailIdsBean;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.BAL_JhclitCalendarModel;
//import com.akranta.tpm.model.KznTlEmailids;
import com.akranta.tpm.service.BAL_CLTI_ScheduleService;
import com.akranta.tpm.service.BAL_CltiScheduleService;
import com.akranta.tpm.service.CommonFilterService;
//import com.akranta.tpm.service.JhClitCalendarService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_CLTI_ScheduleServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.Mail;

/**
 * Servlet implementation class JH_CLIT
 */

public class BAL_CLTI_ScheduleServlet extends HttpServlet {
	
	 private static final long serialVersionUID = 1L;

	private static final int JhclitCalendarModel = 0;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	String  filePath = null;
	
	//private ServletRequest httpSession;
	public void init(ServletConfig config) throws ServletException{
		filePath = config.getServletContext().getRealPath("tmp") + "\\";
		new File(filePath).mkdirs();
	}
	BAL_CLTI_ScheduleService cltiScheduleService;
	 CommonFilterService commonFilterService;
     public BAL_CLTI_ScheduleServlet() throws Exception {
        super();
  /*      // TODO Auto-generated constructor stub
       	        System.out.println(" initialising servlet ....");
       	        
       	        jhncalendarService =  new JhClitCalendarServiceImpl();
				commonFilterService      =  new CommonFilterServiceImpl();
//				jhclitCalendarBean       =  new JhclitCalendarBean();
		*/		
	    }
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		   process(request, response);
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
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		} 
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession = request.getSession();	
		String action = UIUtils.getActionPart(request);
		try {
			cltiScheduleService =  (BAL_CLTI_ScheduleServiceImpl)UIUtils.getServiceObject(request,"CLTI_ScheduleServiceImpl");
			commonFilterService =  (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}

		String dispatchUrl =null;
		 if( action.equals("filterXmljhcalendar_input.jhcal") ||action.equals("filterXmljhplancreation_input.jhcal")){
				response.setContentType("xml"); 
				System.out.println("action "+ action); 
				PrintWriter out = response.getWriter();
				out.print("<fromDate>01-Jan-2012</fromDate>");
				UIUtils.forwardRequest(request, response, "/tiles/xml/JHcalendar.xml") ;
		 }
		 
		 else if(action.equals("clitCalendar_input.cltishe")) 
		{
			 BAL_JhclitCalendarBean jhclitCalendarBean = new BAL_JhclitCalendarBean();
			jhclitCalendarBean.setActionmode(action);
			String cellId = request.getParameter("cellId");//for jh scheduled/std report
			String machineId = request.getParameter("mchineId");//for jh scheduled/std report
			String shift = request.getParameter("shift");
			String abnmId = request.getParameter("abnmId");
			
			CommonFunctions.debugMsg("shiftId....."+shift);
			CommonFunctions.debugMsg("cellId....."+cellId);
			CommonFunctions.debugMsg("machinenId....."+machineId);

			httpSession.setAttribute("actionmode",action);
			httpSession.removeAttribute("AssemblyId");
			//httpSession.setAttribute("AssemblyId", areaId);
			
			
			request.setAttribute("cellId", cellId);//for jh scheduled/std report
			request.setAttribute("machineId", machineId);//for jh scheduled/std report
		
			RequestDispatcher rd = request.getRequestDispatcher("/pages/jhclit/CLTI_Schedule.jsp");
			
			rd.forward(request, response); 
		}

		else if(action.equals("clitCalendar_getCol.cltishe"))
		{
			System.out.println("inside getcol");
			 CommonFilter commonFilter = populateCommonFilter(request,"clitCalendarCommonFilter",true);

			// populateCommonFilter(request,"clitCalendarCommonFilter",true);	
			String  mchID = (String) request.getAttribute("machineId");//for jh scheduled . staandard report
			String cellId = request.getParameter("cellId");//for jh scheduled/std report
			String machineId = request.getParameter("machId");//for jh scheduled/std report
			String shiftId = request.getParameter("shiftId");
			String FactoryId =request.getParameter("factId");
			String SectionId =request.getParameter("sectionId");			
			System.out.println(SectionId +"machine id  " +FactoryId +" machineId "+shiftId);
			//CommonFilter  fltrstring=(CommonFilter) request.getAttribute("dataStr");//for jh scheduled . staandard report
			//CommonFilter commonFilter = new CommonFilter();
			
			//commonFilter.setShiftId(shift);
				System.out.println("data from jhscheduled report");
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			    commonFilter= 	FilterValues.getJHCLIT(request, commonFilter);
			    commonFilter.setFactoryId(request.getParameter("factId"));
				commonFilter.setSectionId(request.getParameter("sectionId"));
				commonFilter.setFlid(request.getParameter("flid"));
				commonFilter.setMachineId(request.getParameter("machId"));
				commonFilter.setCellId(request.getParameter("cellId"));
		
				List<String[]> jhnfnGetScheduled ;
			PrintWriter out = response.getWriter();
			String dateTime = CommonFunctions.dateTimeNow();
			System.out.println("month cur  :"+dateTime.substring(0, 11));
			String curDate = dateTime.substring(0, 11);
			System.out.println("month cur  :"+curDate.substring(3));
			System.out.println("date " + commonFilter.getFromDate());
			commonFilter.setFromDate("01-"+commonFilter.getFromMonth());
			if( commonFilter.getFromDate().equals(Constants.passNullDate))
				commonFilter.setFromMonth(curDate.substring(3));
						
			boolean shiftWise = false;		
			ComboFilter cmbJHfreq = new ComboFilter();
			
			if( ! UIUtils.isValidKeyId(FilterCondSql.getComboSelectionId(commonFilter.getFrequency())) )
			{	
				cmbJHfreq.setId("X");
				commonFilter.setFrequency(cmbJHfreq);
			}
			if(FilterCondSql.getComboSelectionId(commonFilter.getFrequency()).equals("S") )
				shiftWise = true;
			 jhnfnGetScheduled  = cltiScheduleService.getAlljhnfnGetScheduledArray(commonFilter,shiftId);
		
			System.out.println("jhnfnGetScheduled  ----:"+jhnfnGetScheduled.size());			
			httpSession = request.getSession();
			httpSession.removeAttribute("clitCalendarCommonFilter");
			httpSession.setAttribute("clitCalendarCommonFilter", commonFilter);
			
			JSONObject listToJsonObject = UIUtils.convertToJqGridTableObject(jhnfnGetScheduled,request,0,0,commonFilter.getTotalRecordCnt()); 
			 httpSession.setAttribute("jhcompdata", listToJsonObject);									
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				System.out.println("commonFilter.getTotalRecordCnt()  ----:"+commonFilter.getTotalRecordCnt());

				if(jhnfnGetScheduled.size()>2){
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(false);
				//jqGridTableModel.setCellEdit(true);
				//jqGridTableModel.setMultiSelect(true);
				
				String [] colHeaderCond = jhnfnGetScheduled.get(0);
				String [] colHeader1 = jhnfnGetScheduled.get(1);
				String [] colHeader2 = jhnfnGetScheduled.get(2);
				List<String[]> headers = new ArrayList<String[]>();
				if( shiftWise ){
					headers.add(colHeader1);
					headers.add(colHeader2);
					gridColModel.setHeaderNum(2);
		
				}else {
					headers.add(colHeader1);
					gridColModel.setHeaderNum(1);
				} 
				CommonFunctions.debugMsg(headers.toString() +" colHeader1.length......"+colHeader1.length);
		 		CommonFunctions.debugMsg(gridColModel.getHeaderNum() +"......rowheader formatter  "+jqGridTableModel.getRowHeaders());
				List<String> formattorList =  new ArrayList<String>();
				
				//formattorList.add("chkFormatter");
				formattorList.add("clrFormatter");
				
				List<String> formattorFromList =  new ArrayList<String>();
				//formattorFromList.add("8");
				formattorFromList.add("11");
				
				List<String> formattorToList =  new ArrayList<String>();
				//formattorToList.add("8");
				formattorToList.add("41");
				
				gridColModel.setMultiformatter(formattorList);
				gridColModel.setMultiformattorFromCol(formattorFromList);
				gridColModel.setMultiformattorToCol(formattorToList);
				
				JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				colModel.put("data", listToJsonObject);
				
				
				CommonFunctions.debugMsg("colModel   "+gridColModel.getFormatter());
				colModel.put("tableHeight", "83%%");
				colModel.put("tableWidth", "105%%");
				httpSession.setAttribute("clitCalendarColModel", colModel);				
				out.println(colModel);	
			
				}
		}
		 
		
		 
		else if(action.equals("clitCalendar_getData.cltishe"))
			
		{ CommonFunctions.debugMsg("In side the getdata Method");
						try
				{
					PrintWriter out = response.getWriter();
					System.out.println("inside get Data");
					JSONObject jhcalendarData = null;
					//String assemblyId = (String)httpSession.getAttribute("AssemblyId");
					//CommonFunctions.debugMsg("Assembly Id in getcol......"+assemblyId);
					 CommonFilter commonFilter = populateCommonFilter(request,"clitCalendarCommonFilter",false);

					commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);		
					commonFilter = FilterValues.getJHCLIT(request, commonFilter);

					/*for checking if the eqp fact sect cell exist in calendar table or not**/
					boolean shiftWise = false;
					//String keyId = request.getParameter("filterString");
					String cellId = request.getParameter("cellId");//for jh scheduled/std report
					String machineId = request.getParameter("mchineId");//for jh scheduled/std report
					String shiftId = request.getParameter("shiftId");
					
					
						
					if( ! UIUtils.isValidKeyId(commonFilter.getJhfreq()) )
						commonFilter.setJhfreq("X");
					
					if(commonFilter.getJhfreq().equals("S") )
						shiftWise = true;
					
					
					httpSession.setAttribute("JHCALfrmMonth",commonFilter.getFromMonth());
					
					CommonFunctions.debugMsg(" commonFilter.getFromMonth() " +shiftId);
					List<String[]> jhnfnGetScheduled  = cltiScheduleService.getAlljhnfnGetScheduledArray(commonFilter,shiftId);
					
					 
					if(jhnfnGetScheduled.size()<2){
						
					
						response.sendError(204, "No Plan exists");
						throw new Exception("No Plan exists");
						//return;
					}
					int days = UIUtils.getMaxDayOfMonth(commonFilter.getFromMonth());
					
					// addAdditionalRows(jhnfnGetScheduled,days, shiftWise,actionmode);
					int rowStart =2;
					if( shiftWise)
						rowStart = 1;
					
					JSONObject jhnScheduledData = UIUtils.convertToJqGridTableObject(jhnfnGetScheduled,request,rowStart,0);
					
					
					//JSONObject jhnScheduledData =ConvertRowToColJqGrid(jhnfnGetScheduled,request,0,days);
				
	  			 	out.println(jhnScheduledData);

			    }
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					
				}
			}
		else if (action.equals("clitCalendarCompletion_input.cltishe")){		
			BAL_JhclitCalendarBean jhclitCalendarBean = new BAL_JhclitCalendarBean();
			jhclitCalendarBean.setActionmode(action);
			String cellId = request.getParameter("cellId");//for jh scheduled/std report
			String machineId = request.getParameter("mchineId");//for jh scheduled/std report
			String shift = request.getParameter("shift");
			String abnmId = request.getParameter("abnmId");
			
			CommonFunctions.debugMsg("shiftId....."+shift);
			CommonFunctions.debugMsg("cellId....."+cellId);
			CommonFunctions.debugMsg("machinenId....."+machineId);

			httpSession.setAttribute("actionmode",action);
			httpSession.removeAttribute("AssemblyId");
			//httpSession.setAttribute("AssemblyId", areaId);
			
			
			request.setAttribute("cellId", cellId);//for jh scheduled/std report
			request.setAttribute("machineId", machineId);//for jh scheduled/std report
		
			RequestDispatcher rd = request.getRequestDispatcher("/pages/jhclit/CLTI_Completion.jsp");
			
			rd.forward(request, response); 
			
			
		}
		else if(action.equals("clitCalendarCompletion_getCol.cltishe")){
			 CommonFilter commonFilter = populateCommonFilter(request,"clitCalendarCommonFilter",true);

			// populateCommonFilter(request,"clitCalendarCommonFilter",true);	
			String  mchID = (String) request.getAttribute("machineId");//for jh scheduled . staandard report
			String cellId = request.getParameter("cellId");//for jh scheduled/std report
			String machineId = request.getParameter("machId");//for jh scheduled/std report
			String shiftId = request.getParameter("shiftId");
			String FactoryId =request.getParameter("factId");
			String SectionId =request.getParameter("sectionId");			
			System.out.println(SectionId +"machine id  " +FactoryId +" machineId "+shiftId);
			//CommonFilter  fltrstring=(CommonFilter) request.getAttribute("dataStr");//for jh scheduled . staandard report
			//CommonFilter commonFilter = new CommonFilter();
			
			//commonFilter.setShiftId(shift);
				System.out.println("data from jhscheduled report");
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			    commonFilter= 	FilterValues.getJHCLIT(request, commonFilter);
			    commonFilter.setFactoryId(request.getParameter("factId"));
				commonFilter.setSectionId(request.getParameter("sectionId"));
				commonFilter.setFlid(request.getParameter("flid"));
				commonFilter.setMachineId(request.getParameter("machId"));
				commonFilter.setCellId(request.getParameter("cellId"));
		
				List<String[]> jhnfnGetScheduled ;
			PrintWriter out = response.getWriter();
			String dateTime = CommonFunctions.dateTimeNow();
			System.out.println("month cur  :"+dateTime.substring(0, 11));
			String curDate = dateTime.substring(0, 11);
			System.out.println("month cur  :"+curDate.substring(3));
			System.out.println("date " + commonFilter.getFromDate());
			commonFilter.setFromDate("01-"+commonFilter.getFromMonth());
			if( commonFilter.getFromDate().equals(Constants.passNullDate))
				commonFilter.setFromMonth(curDate.substring(3));
						
			boolean shiftWise = false;		
			ComboFilter cmbJHfreq = new ComboFilter();
			
			if( ! UIUtils.isValidKeyId(FilterCondSql.getComboSelectionId(commonFilter.getFrequency())) )
			{	
				cmbJHfreq.setId("X");
				commonFilter.setFrequency(cmbJHfreq);
			}
			if(FilterCondSql.getComboSelectionId(commonFilter.getFrequency()).equals("S") )
				shiftWise = true;
			 jhnfnGetScheduled  = cltiScheduleService.getAlljhnfnGetScheduledArray(commonFilter,shiftId);
		
			System.out.println("jhnfnGetScheduled  ----:"+jhnfnGetScheduled.size());			
			httpSession = request.getSession();
			httpSession.removeAttribute("clitCalendarCommonFilter");
			httpSession.setAttribute("clitCalendarCommonFilter", commonFilter);
			
			JSONObject listToJsonObject = UIUtils.convertToJqGridTableObject(jhnfnGetScheduled,request,0,0,commonFilter.getTotalRecordCnt()); 
			 httpSession.setAttribute("jhcompdata", listToJsonObject);									
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				System.out.println("commonFilter.getTotalRecordCnt()  ----:"+commonFilter.getTotalRecordCnt());

				if(jhnfnGetScheduled.size()>2){
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setCellEdit(true);
				jqGridTableModel.setMultiSelect(true);
				
				String [] colHeaderCond = jhnfnGetScheduled.get(0);
				String [] colHeader1 = jhnfnGetScheduled.get(1);
				String [] colHeader2 = jhnfnGetScheduled.get(2);
				List<String[]> headers = new ArrayList<String[]>();
				if( shiftWise ){
					headers.add(colHeader1);
					headers.add(colHeader2);
					gridColModel.setHeaderNum(2);
		
				}else {
					headers.add(colHeader1);
					gridColModel.setHeaderNum(1);
				} 
				CommonFunctions.debugMsg(headers.toString() +" colHeader1.length......"+colHeader1.length);
		 		CommonFunctions.debugMsg(gridColModel.getHeaderNum() +"......rowheader formatter  "+jqGridTableModel.getRowHeaders());
				List<String> formattorList =  new ArrayList<String>();
				
				//formattorList.add("chkFormatter");
				
				formattorList.add("obsFormatter");
				formattorList.add("tagFormatter");
				formattorList.add("clrFormatter");
				
				List<String> formattorFromList =  new ArrayList<String>();
				formattorFromList.add("8");
				formattorFromList.add("9");
				formattorFromList.add("11");
				
				List<String> formattorToList =  new ArrayList<String>();
				formattorToList.add("8");
				formattorToList.add("9");
				formattorToList.add("41");
				
				gridColModel.setMultiformatter(formattorList);
				gridColModel.setMultiformattorFromCol(formattorFromList);
				gridColModel.setMultiformattorToCol(formattorToList);
				
				JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				colModel.put("data", listToJsonObject);
				
				
				CommonFunctions.debugMsg("colModel mmmmmm  "+gridColModel.getFormatter());
				colModel.put("tableHeight", "83%%");
				colModel.put("tableWidth", "105%%");
				httpSession.setAttribute("clitCalendarColModel", colModel);				
				out.println(colModel);	
			
				}
			
		}
		else if (action.equals("clitCalendarCompletion_getData.cltishe")){
			try
			{
				PrintWriter out = response.getWriter();
				System.out.println("inside get Data");
				JSONObject jhcalendarData = null;
				//String assemblyId = (String)httpSession.getAttribute("AssemblyId");
				//CommonFunctions.debugMsg("Assembly Id in getcol......"+assemblyId);
				 CommonFilter commonFilter = populateCommonFilter(request,"clitCalendarCommonFilter",false);

				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);		
				commonFilter = FilterValues.getJHCLIT(request, commonFilter);

				/*for checking if the eqp fact sect cell exist in calendar table or not**/
				boolean shiftWise = false;
				//String keyId = request.getParameter("filterString");
				String cellId = request.getParameter("cellId");//for jh scheduled/std report
				String machineId = request.getParameter("mchineId");//for jh scheduled/std report
				String shiftId = request.getParameter("shiftId");
				
				
					
				if( ! UIUtils.isValidKeyId(commonFilter.getJhfreq()) )
					commonFilter.setJhfreq("X");
				
				if(commonFilter.getJhfreq().equals("S") )
					shiftWise = true;
				
				
				httpSession.setAttribute("JHCALfrmMonth",commonFilter.getFromMonth());
				
				CommonFunctions.debugMsg(" commonFilter.getFromMonth() " +shiftId);
				List<String[]> jhnfnGetScheduled  = cltiScheduleService.getAlljhnfnGetScheduledArray(commonFilter,shiftId);
				
				 
				if(jhnfnGetScheduled.size()<2){
					
				
					response.sendError(204, "No Plan exists");
					throw new Exception("No Plan exists");
					//return;
				}
				int days = UIUtils.getMaxDayOfMonth(commonFilter.getFromMonth());
				
				// addAdditionalRows(jhnfnGetScheduled,days, shiftWise,actionmode);
				int rowStart =2;
				if( shiftWise)
					rowStart = 1;
				
				JSONObject jhnScheduledData = UIUtils.convertToJqGridTableObject(jhnfnGetScheduled,request,rowStart,0);
				
				
				//JSONObject jhnScheduledData =ConvertRowToColJqGrid(jhnfnGetScheduled,request,0,days);
			
  			 	out.println(jhnScheduledData);

		    }
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				
			}

		}
		 
		else if(action.equals("employee_mail_input.cltishe")){
            String sectionId= request.getParameter("sectionId");
            String refId= request.getParameter("refid");
            String cellName= request.getParameter("cellName");
            String machineName= request.getParameter("mchineName");
            String assembly= request.getParameter("assembly");
            String activity= request.getParameter("activity");
            String observation= request.getParameter("observation");
           String dsa= request.getParameter("ds");

			CommonFunctions.debugMsg("sectionId ..dsa."+dsa);
			
			request.setAttribute("cellName", cellName);
			request.setAttribute("machineName", machineName);
			request.setAttribute("assembly", assembly);
			request.setAttribute("activity", activity);
			request.setAttribute("observation", observation);
			//request.setAttribute("refId", refId);*/
            request.setAttribute("sectionId", sectionId);
			request.setAttribute("refId", refId);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/jhclit/MailIds.jsp");	
			CommonFunctions.debugMsg("observation ..."+refId);

			rd.forward(request, response); 
			

		}
		else if(action.equals("employee_mail_getCol.cltishe")){					

				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.JhclitMail", "MailList"));
				
			}
		//kaizen_Mail_save.kaizen
			else if(action.equals("employee_mail_getData.cltishe")){					
				try
				{
					  
					UIUtils.displayRequestParamsValue(request);
					 String sectionId= request.getParameter("sectionId");
					 CommonFunctions.debugMsg(" sectionId 0000"+ sectionId);
					CommonFilter commonFilter = populateCommonFilter(request,"clitCalendarCommonFilter",false);
							commonFilter.setSectionId(sectionId);							
					List<String[]> equipmentQueryList  = cltiScheduleService.getEmployeeMailList(sectionId);// (commonFilter,keyId,columnId);
					
					CommonFunctions.debugMsg("equipmentQueryList " + equipmentQueryList.size());
					PrintWriter out = response.getWriter();
	  			 	JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(equipmentQueryList,request,0,0,commonFilter.getTotalRecordCnt()); 
	  			 	out.println(equipmentQueryData);  
	  			 						
					CommonFunctions.debugMsg("equipmentQueryList 2 " + equipmentQueryData.toString());

	  			 	httpSession.removeAttribute("kznEmployeeList");
	  			 	httpSession.setAttribute("kznEmployeeList", commonFilter);
	
			    }catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
			}
		 
		 
			else if(  action.contains("email_send.cltishe")){
			
				String from="perfex360@bajajauto.co.in";
				//List<String> recipientList=null;
				String email=request.getParameter("ds");			
				List<BAL_KznTlMailIdsBean> kznTlMailIdsBeanList=null;
				
				BAL_KznTlMailIdsBean kznTlMailIdsBean = new BAL_KznTlMailIdsBean();
			//	KznTlEmailids newKznTlEmailids = new KznTlEmailids();
				kznTlMailIdsBean =(BAL_KznTlMailIdsBean)UIUtils.setBeanProperties((Object)kznTlMailIdsBean,request);
				
				CommonFunctions.debugMsg(" get Mail id"+email);
       
				JSONArray kznTeammembersJson = null;
				boolean insert=false;				
				
			  if(UIUtils.isValidKeyId(email)){
				CommonFunctions.debugMsg("in if employee List " );				
				kznTeammembersJson = JSONArray.fromString(email);
				CommonFunctions.debugMsg("test ine servelet"+kznTeammembersJson+   "     ");
				
				kznTlMailIdsBeanList=(List<BAL_KznTlMailIdsBean>)UIUtils.convertJSONArrToList(kznTlMailIdsBean, kznTeammembersJson);

				String sendTo=null;
				if(kznTlMailIdsBeanList.size()>=0){				
				for(int i=0;i<kznTlMailIdsBeanList.size();i++) {
					if(i==0){
					sendTo=kznTlMailIdsBeanList.get(i).getEmpEmail().concat(";");
					}
					else
						sendTo+=kznTlMailIdsBeanList.get(i).getEmpEmail().concat(";");

					CommonFunctions.debugMsg("insidesapsapres" + i +" "+ sendTo);
					} 
				}
				//String sendTo = request.getParameter("sendTo");
				String ccTo = request.getParameter("ccTo");
				String[] recipientList = sendTo.split(";");
				//String[] recipientListCC = ccTo.split(";");
				
				InternetAddress[] toAdress=new InternetAddress[recipientList.length];
				int i = 0;
				for (String recipient : recipientList) {
					toAdress[i] = new InternetAddress(recipient.trim());
				    i++;
				}
			
				String subject1 ="Abnormality Found at the Time Of JH" ;
				
			//	String message = " Dear Sir,\n Following Abnormality Found at the Time Of JH Details Attached. \n PFA.";

				String  attachmentFile = request.getParameter("fileName");
				String fileName=null;							
				 try{
					
					CommonFilter  commonFilter = new CommonFilter();
					String refId = request.getParameter("refId");
					String User=user.getUsrm_ccno();									
													
					ResultSet dataList = cltiScheduleService.JhExportData(refId);
					String cellName=null;
					String machinename= null;
					String machineNo=null;
					String assembly= null;
					String activity=null;
					String trade= null;
					String remark=null;
					String problem= null;
					String detectedBy= null;
					String abnTag=null;
					String color=null;
					while (dataList.next()) {
			           
						 cellName = dataList.getString(1);      
			             machinename = dataList.getString(2);
			             machineNo = dataList.getString(3);      
			             assembly = dataList.getString(4);
			             activity = dataList.getString(5);      
			             trade = dataList.getString(6);
			             remark = dataList.getString(7);      
			             problem = dataList.getString(8);
			             detectedBy = dataList.getString(9);
			             abnTag = dataList.getString(10);

					}
					if (abnTag.equals("RED")){
						color="#F80D0D";
					}
					else{
						color="#FFC300";
					}
					
					String message = " Dear Sir,<br> Following Abnormality Found at the Time Of JH Activity." +
					
							 " <br> Cell &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp; "+cellName +" ," +
							 " <br> Machine &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;" +machinename +" - "+machineNo+" ,"+
							 " <br> Assembly &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;" +assembly+" ,"+
							 " <br> Activity &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;" +activity+" ,"+
							 " <br> Abnormality &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp; <body style='color:#C70039; text-transform: uppercase;'>  <b>" +problem+" </b>,</body> "+
							 " <br> Abnormality Tag &nbsp; : &nbsp;<body style='color:"+color+" ;'>" +abnTag+" ,</body> "+
							 " <br> Detected By &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;   " +detectedBy+" .";
						CommonFunctions.debugMsg(" Before sendLotusNotesMail 1  " +cellName); 
						CommonFunctions.debugMsg(" Before sendLotusNotesMail 2  " +machinename);
						CommonFunctions.debugMsg(" Before sendLotusNotesMail 3  " +message);

						Mail mail = new Mail(); 
						//mail.sendMailWithoutAttachment( from,toAdress ,subject1, message);
					CommonFunctions.debugMsg(" After sendLotusNotesMail" );			
					
					JSONObject succssMsg= new JSONObject();
					succssMsg.put("msg", "Mail Sent SuccessFully ");
			        response.getWriter().print(succssMsg.toString());
			         
					
					
					}
				
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("err:"+e.getMessage());
						//PrintWriter out = response.getWriter();
						JSONObject err = new JSONObject();				
						//err.put("exception",true);				
						err.put("message" ,"Data Not Found" );
					//	out.print(err.toString());
					}
				 
				System.out.println("sendTo"+sendTo +"attachmentFile"+attachmentFile+"..");
				
				
			
			  }
			}
			
		 
		 /*
		 else if(action.equals("clitCompliance_input.cltishe")) 
			{
				 JhclitCalendarBean jhclitCalendarBean = new JhclitCalendarBean();
				jhclitCalendarBean.setActionmode(action);
				String cellId = request.getParameter("cellId");//for jh scheduled/std report
				String machineId = request.getParameter("mchineId");//for jh scheduled/std report
				String shift = request.getParameter("shift");				
				CommonFunctions.debugMsg("shiftId....."+shift);
				CommonFunctions.debugMsg("cellId....."+cellId);
				CommonFunctions.debugMsg("machinenId....."+machineId);

				httpSession.setAttribute("actionmode",action);
				httpSession.removeAttribute("AssemblyId");
				//httpSession.setAttribute("AssemblyId", areaId);
				
				
				request.setAttribute("cellId", cellId);//for jh scheduled/std report
				request.setAttribute("machineId", machineId);//for jh scheduled/std report
			
				RequestDispatcher rd = request.getRequestDispatcher("/pages/jhclit/clitCompliance.jsp");
				
				rd.forward(request, response); 
			}

			else if(action.equals("clitCompliance_getCol.cltishe"))
			{
				System.out.println("inside getcol");
				 CommonFilter commonFilter = populateCommonFilter(request,"clitCalendarCommonFilter",true);

				// populateCommonFilter(request,"clitCalendarCommonFilter",true);	
				String  mchID = (String) request.getAttribute("machineId");//for jh scheduled . staandard report
				String cellId = request.getParameter("cellId");//for jh scheduled/std report
				String machineId = request.getParameter("machId");//for jh scheduled/std report
				String shiftId = request.getParameter("shiftId");
				String FactoryId =request.getParameter("factId");
				String SectionId =request.getParameter("sectionId");
				
				System.out.println(SectionId +"machine id  " +FactoryId +" machineId "+shiftId);
				//CommonFilter  fltrstring=(CommonFilter) request.getAttribute("dataStr");//for jh scheduled . staandard report
				//CommonFilter commonFilter = new CommonFilter();
				
				//commonFilter.setShiftId(shift);
					System.out.println("data from jhscheduled report");
					commonFilter= 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
				    commonFilter= 	FilterValues.getJHCLIT(request, commonFilter);
				    commonFilter.setFactoryId(request.getParameter("factId"));
					commonFilter.setSectionId(request.getParameter("sectionId"));
					commonFilter.setFlid(request.getParameter("flid"));
					commonFilter.setMachineId(request.getParameter("machId"));
					commonFilter.setCellId(request.getParameter("cellId"));
			
				    CommonFunctions.debugMsg("Assembly Id .............."+commonFilter.getMachineId());
					CommonFunctions.debugMsg("Assembly Id .............."+commonFilter.getFactoryId());
					CommonFunctions.debugMsg("Assembly Id .............."+commonFilter.getFromMonth());
					CommonFunctions.debugMsg("Assembly Id .............."+commonFilter.getSectionId());
					CommonFunctions.debugMsg("Assembly Id .............."+commonFilter.getFlid());

				PrintWriter out = response.getWriter();
				String dateTime = CommonFunctions.dateTimeNow();
				System.out.println("month cur  :"+dateTime.substring(0, 11));
				String curDate = dateTime.substring(0, 11);
				System.out.println("month cur  :"+curDate.substring(3));
				System.out.println("date " + commonFilter.getFromDate());
				commonFilter.setFromDate("01-"+commonFilter.getFromMonth());
				if( commonFilter.getFromDate().equals(Constants.passNullDate))
					commonFilter.setFromMonth(curDate.substring(3));
							
				boolean shiftWise = false;		
				ComboFilter cmbJHfreq = new ComboFilter();
				
				if( ! UIUtils.isValidKeyId(FilterCondSql.getComboSelectionId(commonFilter.getFrequency())) )
				{	
					cmbJHfreq.setId("X");
					commonFilter.setFrequency(cmbJHfreq);
				}
				if(FilterCondSql.getComboSelectionId(commonFilter.getFrequency()).equals("S") )
					shiftWise = true;
				
				List<String[]> jhnfnGetScheduled  = cltiScheduleService.getAlljhnfnGetScheduledArray(commonFilter,shiftId);
				System.out.println("jhnfnGetScheduled  ----:"+jhnfnGetScheduled.size());			
				httpSession = request.getSession();
				httpSession.removeAttribute("clitCalendarCommonFilter");
				httpSession.setAttribute("clitCalendarCommonFilter", commonFilter);
				
				JSONObject listToJsonObject = UIUtils.convertToJqGridTableObject(jhnfnGetScheduled,request,0,0,commonFilter.getTotalRecordCnt()); 
				 httpSession.setAttribute("jhcompdata", listToJsonObject);									
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					System.out.println("commonFilter.getTotalRecordCnt()  ----:"+commonFilter.getTotalRecordCnt());

					if(jhnfnGetScheduled.size()>2){
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(false);
					jqGridTableModel.setTableButton(false);
					jqGridTableModel.setCellEdit(true);
					jqGridTableModel.setMultiSelect(true);
					
					String [] colHeaderCond = jhnfnGetScheduled.get(0);
					String [] colHeader1 = jhnfnGetScheduled.get(1);
					String [] colHeader2 = jhnfnGetScheduled.get(2);
					List<String[]> headers = new ArrayList<String[]>();
					if( shiftWise ){
						headers.add(colHeader1);
						headers.add(colHeader2);
						gridColModel.setHeaderNum(2);
			
					}else {
						headers.add(colHeader1);
						gridColModel.setHeaderNum(1);
					} 
					CommonFunctions.debugMsg(headers.toString() +" colHeader1.length......"+colHeader1.length);
			 		CommonFunctions.debugMsg(gridColModel.getHeaderNum() +"......rowheader formatter  "+jqGridTableModel.getRowHeaders());
					List<String> formattorList =  new ArrayList<String>();
					
					//formattorList.add("chkFormatter");
					formattorList.add("clrFormatter");
					
					List<String> formattorFromList =  new ArrayList<String>();
					//formattorFromList.add("8");
					formattorFromList.add("11");
					
					List<String> formattorToList =  new ArrayList<String>();
					//formattorToList.add("8");
					formattorToList.add("41");
					
					gridColModel.setMultiformatter(formattorList);
					gridColModel.setMultiformattorFromCol(formattorFromList);
					gridColModel.setMultiformattorToCol(formattorToList);
					/*gridColModel.setFormattorFromCol("8");
					gridColModel.setFormattorToCol("8");
					gridColModel.setFormatter("clrFormatter");
					gridColModel.setFormattorFromCol("11");
					gridColModel.setFormattorToCol("41"); */
					//gridColModel.setFormattorToCol(String.valueOf(colHeader1.length));
					
				/*	JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					colModel.put("data", listToJsonObject);
					
					
					CommonFunctions.debugMsg("colModel   "+gridColModel.getFormatter());
					colModel.put("tableHeight", "88%%");
					colModel.put("tableWidth", "103%%");
					 
					out.println(colModel);	
				
					}
			}
			 
			
			 
			else if(action.equals("clitCompliance_getData.cltishe"))
				
			{ CommonFunctions.debugMsg("clitcompliance_input.cltishe In side the getdata Method");
							try
					{
						PrintWriter out = response.getWriter();
						System.out.println("inside get Data");
						JSONObject jhcalendarData = null;
						//String assemblyId = (String)httpSession.getAttribute("AssemblyId");
						//CommonFunctions.debugMsg("Assembly Id in getcol......"+assemblyId);
						 CommonFilter commonFilter = populateCommonFilter(request,"clitCalendarCommonFilter",false);

						//CommonFilter  fltrstring=(CommonFilter) request.getAttribute("filterString");//for jh scheduled . staandard report
						
						// CommonFilter  commonFilter = new CommonFilter();
						commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);		
						commonFilter = FilterValues.getJHCLIT(request, commonFilter);

						/*for checking if the eqp fact sect cell exist in calendar table or not**/
			/*			boolean shiftWise = false;
						//String keyId = request.getParameter("filterString");
						String cellId = request.getParameter("cellId");//for jh scheduled/std report
						String machineId = request.getParameter("mchineId");//for jh scheduled/std report
						String shiftId = request.getParameter("shiftId");
						
						
							
						if( ! UIUtils.isValidKeyId(commonFilter.getJhfreq()) )
							commonFilter.setJhfreq("X");
						
						if(commonFilter.getJhfreq().equals("S") )
							shiftWise = true;
						
						
						//commonFilter.getAssembly().setId(assemblyId);
						
						//String actionmode = (String)httpSession.getAttribute("actionmode");
						
						
						httpSession.setAttribute("JHCALfrmMonth",commonFilter.getFromMonth());
						
						CommonFunctions.debugMsg(" commonFilter.getFromMonth() " +shiftId);
						List<String[]> jhnfnGetScheduled  = cltiScheduleService.getAlljhnfnGetScheduledArray(commonFilter,shiftId);
						
						 
						if(jhnfnGetScheduled.size()<2){
							
						
							response.sendError(204, "No Plan exists");
							throw new Exception("No Plan exists");
							//return;
						}
						int days = UIUtils.getMaxDayOfMonth(commonFilter.getFromMonth());
						
						// addAdditionalRows(jhnfnGetScheduled,days, shiftWise,actionmode);
						int rowStart =2;
						if( shiftWise)
							rowStart = 1;
						
						JSONObject jhnScheduledData = UIUtils.convertToJqGridTableObject(jhnfnGetScheduled,request,rowStart,0);
						
						
						//JSONObject jhnScheduledData =ConvertRowToColJqGrid(jhnfnGetScheduled,request,0,days);
					
		  			 	out.println(jhnScheduledData);

				    }
					catch(Exception e)
					{
						System.out.println(e.getMessage());
						
					}
				} */
		 //////////////////////////////////////////////////////////report//////////////////////
		 
			else if(action.equals("clitCompliance_input.cltishe"))
			{
				String filterStr = request.getParameter("filterString");
				request.setAttribute("filterStr", filterStr);
				
				//CommonFunctions.debugMsg(" in side PmActvityWo_input.actWo   " +action);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/jhclit/clitCompliance.jsp"); 
				rd.forward(request, response);
			}
			else if(action.equals("clitCompliance_getCol.cltishe"))
			{	
				try{					
				PrintWriter out = response.getWriter();			
				CommonFilter commonFilter = populateCommonFilter(request,"jhActivityWoCommonFilter",true);	
				commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
				commonFilter = FilterValues.getJHCLIT (request, commonFilter);
				 commonFilter.setFromDate(CommonFunctions.getDate());
				  commonFilter.setToDate(CommonFunctions.getDate());			 
				
				CommonFunctions.debugMsg(" inside get col above method call" +commonFilter);
				List<String[]> activityList  = cltiScheduleService.jhComplience(commonFilter);	  
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setPaginate(true);
				jqGridTableModel.setGroupBy(true);
				jqGridTableModel.setGroupByField("MACHINE");
			
				gridColModel.setHeaderNum(1);
				String [] colHeader = activityList.get(1);			
				String [] colHeaderCond = activityList.get(0); 
				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "80%%");
				jsonObject.put("tableWidth", "106%%");
		      	CommonFunctions.debugMsg("jsonObject ="+colHeader.toString());
		      	out.println(jsonObject);
			
				/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null ) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  } */
				httpSession.removeAttribute("jhActivityWoCommonFilter");
				httpSession.setAttribute("jhActivityWoCommonFilter", commonFilter);
				CommonFunctions.debugMsg(" at the end of get col method");
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
				
			else if(action.equals("clitCompliance_getData.cltishe"))
			{
				CommonFunctions.debugMsg(" inside get datadata above method call");
				try
				{	
					PrintWriter out = response.getWriter();
					UIUtils.displayRequestParamsValue(request);
					// request.getParameter("page");
					 //httpSession = request.getSession(false);
					 httpSession = request.getSession();
					CommonFilter commonFilter = populateCommonFilter(request,"jhActivityWoCommonFilter",false);		
						
					List<String[]> employeeActivity  = cltiScheduleService.jhComplience(commonFilter);
					JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(employeeActivity,request,2,0,commonFilter.getTotalRecordCnt());
					
	  				out.println(maintActivityData);
	  				
	  				httpSession.removeAttribute("jhActivityWoCommonFilter");
	  			 	httpSession.setAttribute("jhActivityWoCommonFilter", commonFilter);
					
				}catch(Exception e)
				{
					e.printStackTrace();
					
				}
			}
			else if( action.equals("clitCompliance_getExcel.cltishe"))
			{		
				
				
				CommonFilter commonFilter = populateCommonFilter(request,"jhActivityWoCommonFilter" ,false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject tableModel = UIUtils.getXlColModel(request,response); 
				CommonFunctions.debugMsg(" tableModel " + tableModel);
				String title = "JH  Activities Report";
							
				tableModel.put("title",title );
				String format = ExcelUtils.getFormat(request);			
				
				Workbook wb = cltiScheduleService.jhComplienceExportExcel(commonFilter,tableModel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, title.replaceAll(" ", ""), format);
				
			}
		 
		 ////////////////////////////////////////////////////////report end/////////////////////
		 
else if( action.equals("jhcalendar_getExcel.cltishe")||action.equals("jhplancreation_getExcel.cltishe")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"jhclitCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			//JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("ColModel");
			JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
			tblJSONObj.put("title", "Plan And Actual Report");
			String format = ExcelUtils.getFormat(request);
			CommonFunctions.debugMsg("Format...."+format);
			boolean shiftWise = false;
			if( ! UIUtils.isValidKeyId(commonFilter.getJhfreq()) )
				commonFilter.setJhfreq("X");
			if(commonFilter.getJhfreq().equals("S") )
				shiftWise = true;
			
			Workbook wb = cltiScheduleService.JHCLITExportExcel(commonFilter,tblJSONObj,format);
			Sheet sheet = wb.getSheetAt(0);
			sheet.setMargin((short) 1,0.25);
			sheet.getColumnStyle(0).setWrapText(true);
			sheet.getColumnStyle(1).setWrapText(true);
			sheet.getColumnStyle(2).setWrapText(true);
			sheet.getColumnStyle(3).setWrapText(true);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "Plan_Vs_Actual", format);
			
		}
else if( action.equals("cmb_shift.cltishe"))
{
	try {					
		String fromTime = request.getParameter("fromTime");
		ShiftBean  shiftBean = new ShiftBean();
		shiftBean.setFactId(request.getParameter("flid"));
		shiftBean.setSectId(request.getParameter("mchId"));
		shiftBean.setCellId(request.getParameter("cellId"));
		shiftBean.setFromTime(fromTime);		
		String occDate = request.getParameter("occurreddate");
		String shiftDate = occDate;
		
	
		
		if(UIUtils.isValidKeyId(occDate) && UIUtils.isValidKeyId(fromTime))
		{
			if(Integer.parseInt(fromTime.substring(0, 2)) < 7)
			{
				 final long MILLIS_IN_A_DAY = 1000*60*60*24;    
				 DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				 Date occuredDate = dateFormat.parse(occDate);
				 Date oneDayBefore = new Date(occuredDate.getTime() - MILLIS_IN_A_DAY);

			     shiftDate = dateFormat.format(oneDayBefore);
			     CommonFunctions.debugMsg(shiftDate);
					
			}
			
		}
		String shift = cltiScheduleService.getShift(shiftBean);
		//String shift = UIUtils.getShift(request.getParameter("factId"),request.getParameter("sectId"),request.getParameter("cellId"),request.getParameter("fromTime"));
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); 	
		
		 CommonFunctions.debugMsg(shift+"------------");
		JSONObject shiftObj = new  JSONObject();
		shiftObj.put("shift",shift);
		CommonFunctions.debugMsg(shiftDate + " : shiftDate");
		if(UIUtils.isValidKeyId(shiftDate))
		{
			shiftObj.put("shiftDate",shiftDate);
			shiftObj.put("ShiftId",request.getParameter("ShiftId"));
			shiftObj.put("ShiftDateId",request.getParameter("ShiftDateID"));
		}
	    CommonFunctions.debugMsg(shiftObj);
        out.print(shiftObj);					
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

			else if( action.equals("clitCalendar_save.cltishe"))
			{	
				 user = UIUtils.getLoginUser(request);
				String user_createdBy   = user.getUsrm_ccno();
			      String activityid=request.getParameter("actvity");
			      CommonFunctions.debugMsg("  Inside servlet "+activityid);
			      String actvty=activityid.replaceAll("\"", "");
			      String mchineId=request.getParameter("mchineId");
			      String shiftId=request.getParameter("shiftId");
			      String cellId=request.getParameter("cellId");
			      String status=request.getParameter("status");
			      String observtn=request.getParameter("observation");			      
			      String observation=observtn.replace("\"", "");
			      String abnTagClass =request.getParameter("abnTagClassList");
			      String tagClass = abnTagClass.replace("\"", "");
			      JSONObject standard=new JSONObject();
			      JSONObject stnd=new JSONObject();
			      String actarry[]=actvty.split(",");
			      String observationlst[]=observation.split(",");
			      String tagclasslst[]=tagClass.split(",");

			      List<String> obsernlst = new ArrayList<String>();
			     List<String> tagclasslist = new ArrayList<String>();
			     List<String> actlst = new ArrayList<String>();
			     for(String actlist:actarry)
			     {
			    	 actlst.add(actlist);
			     }
			     
			     for(String obsrvnlist:observationlst)
			     {
			    	 obsernlst.add(obsrvnlist);
			     }
			     for(String taglist:tagclasslst)
			     {
			    	 tagclasslist.add(taglist);
			     }
			     
			     
			   
			      System.out.println("eqlist"+tagClass);
			      System.out.println("actlst"+actlst);
			      try{
					   //  String stndrds= cltiScheduleService.save(actlst,cellId,mchineId,shiftId,user_createdBy,status,observation,tagClass);

			     String stndrds= cltiScheduleService.save(actlst,cellId,mchineId,shiftId,user_createdBy,status,obsernlst,tagclasslist);
			      stnd.set("result", "success");
			      }
			      catch(Exception e){
			    	  e.printStackTrace();
			    	 stnd.set("result", "error");
			      }
			    
			      
			      standard.set("stnd",stnd);
			      response.getWriter().println(standard);
			      
			  } 
		   
			else if(action.equals("jhClitcalendar_del.jhcal")){
				System.out.println("DELETED " + request.getParameter("position"));
				BAL_JhclitCalendarBean jhclitCalendarBean = new BAL_JhclitCalendarBean();
				jhclitCalendarBean.setActionmode("delete");
				updateCal(request,response,jhclitCalendarBean);
			}
		 
			else if(action.equals("jhClitcalendar_getExportExcel.jhcal")){
				
				try
				{
					//PrintWriter out = response.getWriter();
					System.out.println("inside get Data");
					JSONObject jhcalendarData = null;
					//CommonFilter  commonFilter = new CommonFilter();
					//commonFilter=(CommonFilter) request.getAttribute("JHCLITCommonFilter");//for jh scheduled . staandard report
					CommonFilter commonFilter = populateCommonFilter(request,"jhclitCommonFilter",false);
					//commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);		
					//commonFilter = FilterValues.getJHCLIT(request, commonFilter);
					
					boolean shiftWise = false;
					String keyId = request.getParameter("filterString");
					
					if(keyId != null)
					{
					  String assemblyId = null ;
					  String[] temp;
					  /* delimiter */
					  String delimiter = "&";					
					  temp = keyId.split(delimiter);
					  for(int i =0; i < temp.length ; i++)
					  {
						  String assm = temp[4];
						  assemblyId=assm.substring(7,15);
					  }
				
					  //String compId = request.getParameter("compId");
						ComboFilter assembly =null;
						if( UIUtils.isValidKeyId(assemblyId ))
						{	
							assembly = new ComboFilter();
							assembly.setId(assemblyId);
							commonFilter.setAssembly(assembly);
						}
					}
					
					if( ! UIUtils.isValidKeyId(commonFilter.getJhfreq()) )
						commonFilter.setJhfreq("X");
					
					if(commonFilter.getJhfreq().equals("S") )
						shiftWise = true;
					
					String actionmode = (String)httpSession.getAttribute("actionmode");
					
				 	String format = ExcelUtils.getFormat(request);
					String path = UIUtils.getExcelTemplatePath(request);  	
					String imagePath = UIUtils.getImagePath(request);
					format = "xlsx";
					
					Workbook wb =  cltiScheduleService.getJhnCalenderExportExcel(commonFilter,format,path,imagePath);
					
					ExcelUtils.writeToResponse(response, wb, "CLITCalender",format );
					
					//List<String[]> jhnfnGetScheduled  = jhncalendarService.getJhnCalenderExportExcel(commonFilter);
					
					
					
					/*
					if(jhnfnGetScheduled.size()<2){
						response.sendError(204, "No Plan exists");
						throw new Exception("No Plan exists");
					}
					int days = UIUtils.getMaxDayOfMonth(commonFilter.getFromMonth());
					int rowStart =2;
					if( shiftWise)
						rowStart = 1;
					
					JSONObject jhnScheduledData = UIUtils.convertToJqGridTableObject(jhnfnGetScheduled,request,rowStart,0);
	  			 	out.println(jhnScheduledData);
					*/
			    }
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					
				}
			}
		}
		
		
	//for adding plaaned column
	private void addAdditionalRows(List<String[]> dataList,int days, boolean shiftWise, String actionmode)
	{
		int  dataRowStart = 1;
		int multBy = 1;
		if( shiftWise){
			dataRowStart = 2;
			multBy = 3;
		}
		int zero_cal = 0;
		int arryLength  = dataList.get(0).length;
		String dateTime = CommonFunctions.dateTimeNow();
		String currentDate = dateTime.substring(0, 2);
		
		CommonFunctions.debugMsg(" currentDate " + currentDate);
		String[] status  =  new String [arryLength];
		//status = "S";
		String [] planValue = new String [arryLength];
		
		String [] actualValue = new String [arryLength];
		
		String [] emptyValue = new String [arryLength];
		BAL_JhclitCalendarBean jhclitCalendarBean = new BAL_JhclitCalendarBean();
		int dayStartCol = arryLength - (days * multBy);
		
		
		final int totminsColIndx = 12; 
		final int actualColIndx = 10;
		/*status[ totminsColIndx ] = "Status";
		planValue[ totminsColIndx ] = "Planned";
		actualValue[ totminsColIndx ] = "Actual";*/
		
		status[ totminsColIndx ] = "Status";
		planValue[ totminsColIndx ] = "Planned";
		actualValue[ totminsColIndx ] = "Actual";
		CommonFunctions.debugMsg(" dayStartCol..... " );
		
		for (int i = dayStartCol; i < status.length; i++) {
			
			actualValue[i] = planValue[i] ="0";
		}
	
       int rowIndx = 0;
       CommonFunctions.debugMsg(" dayStartCol1..... " );
      
       for(String [] test : dataList ){
    		if(rowIndx++ >= dataRowStart){
				for(int colindx = 11;colindx<test.length;colindx++){
					CommonFunctions.debugMsg(" dayStartCol2..... " );
					/*if(!(test[colindx].equals("0"))&&!(test[colindx].equals("X"))){
					
						planValue[colindx]=test[8];
						actualValue[colindx]=test[colindx];
					}*/
					CommonFunctions.debugMsg(" pl " + (Double.parseDouble(planValue[ colindx ]) + " - " + Double.parseDouble(test[totminsColIndx])) );
					/***added to round off the plan and actual value***/
					Double planVal = Double.parseDouble(planValue[ colindx ]);
					Double actualVal = Double.parseDouble(actualValue[ colindx ]);
					Double testcolTot = Double.parseDouble(test[totminsColIndx]);
					Double testcol = Double.parseDouble(test[colindx]);
					DecimalFormat df = new DecimalFormat(".00");
					df.setRoundingMode(RoundingMode.FLOOR);
					//CommonFunctions.debugMsg(" testcol"+colindx+ ">   " + testcol +" -- "+actualVal+" -- "+actualValue[ colindx ] );
					if(  test[colindx].equals("-1") || (Pattern.matches("^\\d*\\.?\\d+$", test[colindx]) && ! test[colindx].equals("0"))) //plan duaration
						planValue[ colindx ] = (Double.parseDouble(df.format(planVal))+ Double.parseDouble(df.format(testcolTot)))+"";
					if(! test[colindx].equals("0") && ! test[colindx].equals("-1"))
						actualValue[ colindx ] = (Double.parseDouble(df.format(actualVal))+ Double.parseDouble(df.format(testcol)))+"";
					CommonFunctions.debugMsg(" dayStartCol3..... " );
					//double finalValue = Math.round( value * 100.0 ) / 100.0;
					
					//CommonFunctions.debugMsg(" pl " + planValue[ colindx ] +" -- "+colindx );
				}
    		}	
       }	
		/*for(String [] row : dataList ){
			if(rowIndx++ >= dataRowStart){
				DecimalFormat roundFormatter = new DecimalFormat("########0.0000");

				for( int colindx = dayStartCol;colindx  < row.length ;colindx++){
					
					
					if( row[colindx].equals("0") || row[colindx].equals("X")) //plan duaration
						planValue[ colindx ] = (Double.parseDouble(planValue[ colindx ])+ Double.parseDouble(row[totminsColIndx]))+"";
					if(!row[colindx].equals("0")&&!(test[colindx].equals("X"))) //actual
						actualValue[ colindx ] =roundFormatter.format( (Double.parseDouble(actualValue[ colindx ])+ Double.parseDouble(row[actualColIndx])))+"";
					//System.out.println("TOTAL-----   :"+planValue[ colindx ]);
				}
			}	
		}	*/	
		
		/*dataList.add(1, actual);
		dataList.add(1, plan);*/
		//CommonFunctions.debugMsg(dataRowStart+ "  jhclitCalendarBean.getActionmode()  :"+actionmode);	
     //  CommonFunctions.debugMsg("actualValue[0]  "+actualValue[0]);
		actualValue[0]=planValue[0]= "creation";
		CommonFunctions.debugMsg(" actualValue " + actualValue);
	//	CommonFunctions.debugMsg("sfafa" +actualValue.length+" -()- "+planValue.length);
		
			//if(jhclitCalendarBean.getActionmode().equals("jhcalendar_input.jhcal")){
			if(actionmode.equals("jhcalendar_input.jhcal")){
				//CommonFunctions.debugMsg("inside if create");
				dataList.add(dataRowStart, actualValue);
				dataList.add(dataRowStart, planValue);
				//CommonFunctions.debugMsg("End of  createin");
			}
			else{
				
				
				//CommonFunctions.debugMsg("I cond   --  "+Integer.parseInt(currentDate));
				for (int i = dayStartCol; i<=Integer.parseInt(currentDate)+(dayStartCol-1); i++) {
					//CommonFunctions.debugMsg("planValue -- -- -- --"+planValue[i]);
					
					if(Double.parseDouble(planValue[i])>zero_cal){
					//if( test[colindx].equals("X") || ( UIUtils.isNumericString(test[colindx]) && ! test[colindx].equals("0")))
						status[i] = "chkB";}
					//CommonFunctions.debugMsg("status["+i+"] -- -- -- --"+status[i]);
				}
					//System.out.println("status[i]  ");
				status[0]= "creation";
				dataList.add(dataRowStart, actualValue);
				dataList.add(dataRowStart, planValue);
				dataList.add(dataRowStart, status);
				CommonFunctions.debugMsg("end");
			 }	
			
		}
	//}
	
	 //for populating data
			
			private JSONObject getColumnModel(List<String[]> colHeaders,boolean shiftWise)
			{	
				JqGridTableModel jqGridTableModel = new JqGridTableModel();
				
				 int rowIndx = 0;
				 float dataRowStart = (float) 1.1;
					for(String row :  (String[])colHeaders.get(0) ){
						if(rowIndx++ >= dataRowStart){
							System.out.println(" header TRow Count " +rowIndx);
						}
					}
				String [] rowHeaders = (String[])colHeaders.get(0);
				
				jqGridTableModel.setTableButton(true);
				
				if( shiftWise ){
					String [] rowHeader1 = new String[ rowHeaders.length ]; 
					
					for(int i =0;i<rowHeaders.length;i++ ){
					  	rowHeader1[ i ] = rowHeaders[i]; 
					  	rowHeaders[i] =" ";
					} 	
					
					
					jqGridTableModel.getRowHeaders().add(rowHeaders);
					
					
					jqGridTableModel.getRowHeaders().add(rowHeader1);
					
					String [] rowHeader2 = (String[])colHeaders.get(1);
					jqGridTableModel.getRowHeaders().add(rowHeader2);
					
				
					rowHeaders = (String[])colHeaders.get(1);
				}
				else
					jqGridTableModel.getRowHeaders().add(rowHeaders);
				System.out.println("Col Model");
				jqGridTableModel.getColModel().add(createColModel("RefDocno",120,true,"","","left"));
				jqGridTableModel.getColModel().add(createColModel("Equipmentarea",300,false,"","","left")); 
				jqGridTableModel.getColModel().add(createColModel("mainpart",120,false,"","","left"));   
			 	jqGridTableModel.getColModel().add(createColModel("item",300,false,"","","left"));   
			 	jqGridTableModel.getColModel().add(createColModel("classification",120,false,"","","left"));   
			 	jqGridTableModel.getColModel().add(createColModel("frequency",120,false,"","","left"));   
			 	jqGridTableModel.getColModel().add(createColModel("method",100,false,"","","left"));   
			 	jqGridTableModel.getColModel().add(createColModel("tool",120,false,"img_tools","","center"));
			 	jqGridTableModel.getColModel().add(createColModel("Activity",80,false,"chkbox_Activity","","center"));   
			 	jqGridTableModel.getColModel().add(createColModel("timeinmins",80,false,"","","right"));
			 	//jqGridTableModel.getColModel().add(createColModel("actual",120,true,"",""));
			 	jqGridTableModel.setTableHeight(300);
			 	jqGridTableModel.setTableWidth(1100);
			 	//jqGridTableModel.setCellEdit(false);
			 	//jqGridTableModel.setCellSubmitLocal(true);
			 	for(int i = 0; i <rowHeaders.length ;i++){
			 		CommonFunctions.debugMsg("rowheader  "+rowHeaders[i]);
			 	}
				for(int i = 10; i <rowHeaders.length ;i++){
			 		JqGridColModel jqGridColModel = new JqGridColModel();
			 		System.out.println("rowHeaders["+i+"]  :"+rowHeaders[i]);
			 		jqGridColModel.setName(rowHeaders[i]);
			 		jqGridColModel.setIndex(rowHeaders[i]);
			 		jqGridColModel.setWidth(30);
			 		jqGridColModel.setHidden(false);
			 		CommonFunctions.debugMsg("rowheader formatter  "+rowHeaders[i]);
			 		jqGridColModel.setFormatter("clrFormatter");
			 		jqGridColModel.setEditable(false);
			 		jqGridTableModel.getColModel().add(jqGridColModel);
			 	}
				//jqGridTableModel.getHeaders().add(rowHeaders);
			 	System.out.println("before return");
				return UIUtils.getJqGridTableModel(jqGridTableModel);
				//return getColumnType(colNames) ;
				//return UIUtils.getGroupByColumnModel(colNames);
				//return getColumnType(colNames) ;
			}
	 
	 	private JqGridColModel createColModel(String colIndexName, int width,boolean hidden,String formatter,String editable,String align){
	 		JqGridColModel jqGridColModel = new JqGridColModel();
	 		
	 		System.out.println("formatter  :"+formatter);
	 		jqGridColModel.setName(colIndexName);
	 		jqGridColModel.setIndex(colIndexName);
	 		jqGridColModel.setWidth(width);
	 		jqGridColModel.setHidden(hidden);
	 		jqGridColModel.setFormatter(formatter);
	 		jqGridColModel.setEditable(true);
	 		jqGridColModel.setAlign(align);
	 		
	 		return jqGridColModel;
	 	}

	 
		//updateCal
	private void updateCal(HttpServletRequest request, HttpServletResponse response,BAL_JhclitCalendarBean jhclitCalendarBean  ) throws IOException{
		//assmid
		System.out.println("inside updateCal");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		try{
	    	if( httpSession != null && user != null)
	    	{	
	    		System.out.println("inside  Try bloc");
	    		
	    		String calGridStr = (String) httpSession.getAttribute("jhCalGridData");
	    		String from_month = (String)httpSession.getAttribute("JHCALfrmMonth");
	    		BAL_JhclitCalendarModel newjhncalendar   = new BAL_JhclitCalendarModel();
	    		
	    		System.out.println("before list  :"+calGridStr);
	    		System.out.println("From month   :"+from_month);
	    		//List<JhclitCalendarModel> calList = null;
	    		List<BAL_JhclitCalendarModel>  jhclitCalendarModelList= null;
				JSONArray calGridjson = null;
			
				if(UIUtils.isValidKeyId(calGridStr)){
					System.out.println("before list  :"+calGridStr);
				   calGridjson = JSONArray.fromString(calGridStr);
				   System.out.println("calGridjson   :"+calGridjson);	
				   jhclitCalendarModelList=(List<BAL_JhclitCalendarModel>)UIUtils.convertJSONArrToList(newjhncalendar, calGridjson);
				   System.out.println("calList   :"+jhclitCalendarModelList);
				
					if(jhclitCalendarModelList!= null){
						String s = jhclitCalendarModelList.get(0).toString();
						////System.out.println(" $$$$"+newjhncalendar.getClcaActualduration());
					    //System.out.println("servlet List DATE  :"+newjhncalendar.getClcaPlandate());
					    newjhncalendar.setClcaCreatedby(user.getUsrm_ccno());
					    String user_createdBy   = user.getUsrm_ccno();
					  
					    if(jhclitCalendarBean.getActionmode().equals("delete")){
					    	System.out.println("Actionmode     :"+jhclitCalendarBean.getActionmode());
					    	newjhncalendar.setClcaActualduration("0"); 
					    	newjhncalendar =  cltiScheduleService.update(jhclitCalendarModelList,from_month,user_createdBy,jhclitCalendarBean);
					    }else{
					    	newjhncalendar =  cltiScheduleService.update(jhclitCalendarModelList,from_month,user_createdBy,jhclitCalendarBean);
					    }
					}
				}	
				// System.out.println("calGridjson   :"+calGridjson);	
				 	JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
					//successData.put("msg", "Data Saved Successfully");
					returnData.put("successData",successData);
					out.print(returnData.toString());
					//out.print()	
					System.out.println("end of save");
	    	}	
	    	
		}catch(ValidationExceptions e)
		{
			System.out.println(e);
			
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "ClitcreationException");
			System.out.println(errMessage.toString());
			out.print(errMessage.toString());
			
		}catch(Exception e)
		{
			System.out.println("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
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
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
			commonFilter = 	FilterValues.getJHCLIT(request, commonFilter);//get related filters
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
	
}
