package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DashboardDispbean;
//import com.akranta.tpm.bean.DashboardDispbean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlDashboadUserrights;
import com.akranta.tpm.model.AdmTlScrollmsgmst;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlJhactivitychartdtl;
//import com.akranta.tpm.model.GenTlJhactivitychartdtl;
import com.akranta.tpm.model.GenTlJhactivitychartmst;
import com.akranta.tpm.model.GenTlMessageboard;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.JhactivitychartService;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.JhactivitychartServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DashBoardServlet
 */

public class DashBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DashboardService  dashboardService= null;
	private JhactivitychartService jhactivitychartService= null;
    public DashBoardServlet() {
    	
        super();
       /* try {
		//	dashboardService = new DashboardServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        // TODO Auto-generated constructor stub
         * *
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
		}
	}

	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		try {
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			jhactivitychartService = (JhactivitychartServiceImpl)UIUtils.getServiceObject(request,"JhactivitychartServiceImpl");
		CommonMessage.debugMsg("action  :  "+action);
			if( action.equals("getPillars.dashboard") ){
				getDashboardPillars(request,response);
			}else if( action.equals("getModejhActivityMainGrid_view.dashboard")){
				CommonMessage.debugMsg("Action ::: Mode "+action);
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				JSONObject result = new JSONObject();
				result.put("mode","create");
				result.put("url","jhActEntryFrm_input.dashboard");
				result.put("formHeader","JH Activity Entry");
				out.println(result);
			}else if(((action).toUpperCase()).contains("JHACTIVITY")){
				jhActivity(action,request,response,httpSession);
			}else if(action.equals("jhActEntryFrm_input.dashboard")){
				String mstKeyid=request.getParameter("MSTKEYID");
				CommonMessage.debugMsg("mstKeyid  :  "+mstKeyid);
				GenTlJhactivitychartmst newGenTlJhactivitychartmst = new GenTlJhactivitychartmst ();
				if(UIUtils.isValidKeyId(mstKeyid)){
					newGenTlJhactivitychartmst=jhactivitychartService.selectMstData(mstKeyid);
					CommonMessage.debugMsg(newGenTlJhactivitychartmst.getAchmFrequency() +"Frequency");
				}
				RequestDispatcher rd = request.getRequestDispatcher("/pages/jhActivityEntry.jsp");
				request.removeAttribute("genTlJhactivitychartmst");
				request.setAttribute("genTlJhactivitychartmst",newGenTlJhactivitychartmst);
				rd.forward(request, response);
			}else if(action.equals("jhActEntryFrm_getCol.dashboard")){
				PrintWriter out = response.getWriter();
				String mstKeyid=request.getParameter("MSTKEYID");
				CommonFilter commonFilter =populateCommonFilter(request,"jhActGridDtl",true);
				commonFilter.setKey(mstKeyid);
				List<String[]> jhActList =jhactivitychartService.getjhActDtl(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();

				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setGridEdit(true);
				gridColModel.setHeaderNum(1);
				
				String [] colHeader1 = jhActList.get(2);
				String [] colHeaderCond = jhActList.get(1);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader1);
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "65%%");
				jsonObject.put("tableWidth", "106%%");
				httpSession.removeAttribute("jhActivityGridDtl");
				httpSession.setAttribute("jhActivityGridDtl", jsonObject);
				out.println(jsonObject);
			}
			
			else if(action.equals("jhActEntryFrm_getData.dashboard")){
				String mstKeyid=request.getParameter("MSTKEYID");
				CommonFilter commonFilter = populateCommonFilter(request,"jhActGridDtl",false);
				commonFilter.setKey(mstKeyid);
				List<String[]> custGrid = jhactivitychartService.getjhActDtl(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject jhActgridData = UIUtils.convertToJqGridTableObject(custGrid, request, 3, 0);
				out.println(jhActgridData);
			}else if(action.equals("jhFrequency_combo.dashboard")){
				try{
					PrintWriter out = response.getWriter();
					CommonMessage.debugMsg("property file "+UIUtils.getPropertyValue("com.akranta.tpm.resources.jhActivityProp","frequency"));
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.jhActivityProp","frequency"));
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(action.equals("jhActEntryFrm_save.dashboard")){
				saveJhAct(request,response,httpSession);
			}else if(action.equals("jhActEntryFrm_delete.dashboard")){
				deleteJhAct(request,response,httpSession);
			}else if(action.equals("jHActDtl_delete.dashboard")){
				deleteJhActDtl(request,response,httpSession);
			}
			else if(action.equals("getRelatedData.dashboard")){
				getRelatedData(request,response);
			}
			else if(action.equals("dashboard_input.dashboard")){
				displayDashboardPage(request,response);
			}
			else if(action.equals("newdashboard_input.dashboard")){
				displayNewDashboardPage(request,response);
			}
			
			else if(action.equals("newdashboardTable1_input.dashboard")){
				displayNewDashboardPageTable1(request,response);
			}
			else if(action.equals("newdashboardTable2_input.dashboard")){
				displayNewDashboardPageTable2(request,response);
			}
			else if(action.equals("newdashboardTable3_input.dashboard")){
				displayNewDashboardPageTable3(request,response);
			}
			else if(action.equals("newdashboardTable4_input.dashboard")){
				displayNewDashboardPageTable4(request,response);
			}
		else if(action.equals("newdashboardCharts_input.dashboard")){
				displayNewDashboardPageCharts(request,response);
			}
			else if(action.equals("zoomChart_input.dashboard")){
				String chrtdivId = request.getParameter("chrtdivId");
				request.setAttribute("chrtdivId",chrtdivId);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Gen/zoomChart.jsp"); 
				rd.forward(request, response);
			}
			else if(action.equals("dashboarPage_input.dashboard")){
				try {
					CommonMessage.debugMsg("action else  "+action);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/Gen/dashBoardContent.jsp"); 
					rd.forward(request, response);
				} catch (ServletException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(action.equals("DBUserRights_input.dashboard")){
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Gen/dashbrdUsrRghts.jsp"); 
				rd.forward(request, response); 
			}
			else if (action.equals("MessagePopUp_input.dashboard")) {
				UIUtils.forwardRequest(request, response, "/tiles/MessagePopUp.jsp");
			}else if (action.equals("MessagePopUp_getCol.dashboard")){
				MessagePopUpGetCol(request,response);
			}else if (action.equals("MessagePopUp_getData.dashboard")){
				MessagePopUpGetData(request,response);

			}else if (action.equals("message_getMsgs.dashboard")){
				getMessageBoardMsgs(request,response);
			}
			else if(action.equals("MessageDashBoard_input.dashboard")){
				String Mesgkeyid =request.getParameter(ReqtParamNameConst.KEYID);
				if ((Mesgkeyid != null)){
					GenTlMessageboard newGenTlMessageboard = dashboardService.selectMessage(Mesgkeyid);
					CommonMessage.debugMsg("keyid "+newGenTlMessageboard.getMsgbKeyid());
			      	request.setAttribute("Mesg", newGenTlMessageboard);
				}
				UIUtils.forwardRequest(request, response, "/pages/MessageDashBoard.jsp");
			}
			else if(action.equals("MessageDashBoard_save.dashboard")){
				messageDashboardSave(request,response);
			}
			else if(action.equals("MessageDashBoard_delete.dashboard")){
				messageDashboardDelete(request,response);
			}
			
			else if(action.equals("messageboard_input.dashboard")){
				
				UIUtils.forwardRequest(request, response, "/pages/MessageBoardNew.jsp");
			}
			
			else if(action.equals("messageboard_getCol.dashboard")){
							
				//HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				String flid=request.getParameter("flid");
				String roleId=request.getParameter("roleId");
				String keyid=request.getParameter("keyid");
				
				CommonFilter commonFilter = populateCommonFilter(request,"MessageBoardCommonFilter", true);
				try{
					
					
					if(UIUtils.isValidKeyId(keyid))
						commonFilter.setKey(keyid);
						
				List<String[]> moReqList = dashboardService.getFillMsggrid(commonFilter);
				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				gridColModel.setFormattorFromCol("0");
				gridColModel.setFormattorToCol("0");
				
				String [] colHeader = moReqList.get(1);
				String [] colHeaderHead = moReqList.get(0);
				 
				List<String[]> headers = new ArrayList<String[]>();	
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "90%%");
		    	jsonObject.set("tableHeight", "45%%");
		    	httpSession.setAttribute("MessageBoardColModel", jsonObject);
				out.println(jsonObject);
				
				}catch(Exception e){
						e.printStackTrace();
				}
			
			}
						
			else if(action.equals("messageboard_getData.dashboard")){
				
			
				try
				{   
					UIUtils.displayRequestParamsValue(request);
					String flid=request.getParameter("flid");

				    CommonFilter commonFilter = populateCommonFilter(request,"MessageBoardCommonFilter",false);
				    
					List<String[]> minOfMeetingList  = dashboardService.getFillMsggrid( commonFilter);
	  			 	JSONObject MoReqData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
					PrintWriter out = response.getWriter();
	  			 	out.println(MoReqData);  			 	
	  			 	commonFilter.setViewClick('N');  			 	
	  			 	httpSession.removeAttribute("MinOfMeetingCommonFilter");
	  			 	httpSession.setAttribute("MinOfMeetingCommonFilter", commonFilter);

			    }catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
           }
			
			else if(action.equals("messageboard_recall.dashboard"))
			{
			    PrintWriter out = response.getWriter();
				String keyid = request.getParameter("KEYID");
				CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
				List<String []> condReclData  = dashboardService.FillControlData(keyid);
				out.print( JSONArray.fromCollection(condReclData));
			}
			else if(action.equals("tobedisplay_combo.dashboard")){
				try{
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MessageBoardform","tobedisplay"));
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			else if( action.equals("functionalLoc.dashboard"))
			{
					CommonMessage.debugMsg("function location ");
					FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
					//functLocFieldNameBean.setFactory("cmbMomdFactoryid");
					functLocFieldNameBean.setSection("cmbSmsgSectionid");
					functLocFieldNameBean.setCell("cmbSmsgCellid");
					functLocFieldNameBean.setMachine("cmbSmsgMachineid");
					functLocFieldNameBean.setFunctionalLocId("cmbSmsgFlid");
					functLocFieldNameBean.setSbu("cmbSmsgSbu");
					functLocFieldNameBean.setPbu("cmbSmsgPbu");
					functLocFieldNameBean.setLocnMandatory(true);
					functLocFieldNameBean.setFactMandatory(false);
					functLocFieldNameBean.setSectMandatory(false);
					functLocFieldNameBean.setCellMandatory(false);
					functLocFieldNameBean.setMachMandatory(false);
					CommonMessage.debugMsg("functionlocation null ");
	                FormModes formModes = FormModes.create;
	                UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
			 }
			
             else if(action.equals("messageboard_save.dashboard")){
				
            	 AdmTlScrollmsgmst admTlScrollmsgmst = new AdmTlScrollmsgmst();
         		 saveMessageBoard(request, response);
			  }
             else if(action.equals("messageboard_delete.dashboard")){
 				
            	 AdmTlScrollmsgmst admTlScrollmsgmst = new AdmTlScrollmsgmst();
         		 deleteMessageBoard(request, response);
			  }
			else if(action.equals("DBUserRights_getCol.dashboard")){
				CommonMessage.debugMsg("Inside"+action);
				 PrintWriter out = response.getWriter();
				 String fromExcel="false";
				 List<String[]> DBUserRights=null;
				 DBUserRights =dashboardService.getDBUserRights();
				 JSONObject colModel = getTableModelDBUserRights(DBUserRights,fromExcel);
				 JSONObject colmodel=getTableModelDBUserRightsxcel(DBUserRights);
				 httpSession.removeAttribute("DBUserRightsColModel");
				 httpSession.setAttribute("DBUserRightsColModel", colModel);
				 httpSession.removeAttribute("DBUserRightsColModelxcel");
				 httpSession.setAttribute("DBUserRightsColModelxcel", colmodel);
				 
				 out.println(colModel);
				
			}
			
			
			else if(action.equals("NewDashboard_Excelview.dashboard")){
				 try{
					 String CurrentYear=CommonFunctions.getCurrentYear();
					 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
					 
					 CommonMessage.debugMsg("PreviousYear"+PreviousYear);
					 CommonMessage.debugMsg("CurrentYear"+CurrentYear);
					 String FYearStart="01-APR-"+PreviousYear;
					 CommonMessage.debugMsg("FYearStart"+FYearStart);
					 String NextYear=CurrentYear.substring(0,3);
					 CommonMessage.debugMsg("FYearEnd"+NextYear);
					// String CurrentYear=CommonFunctions.getCurrentYear();
					 String FYearEnd="31-MAR-"+(NextYear)+1;
					 CommonMessage.debugMsg("FYearEnd"+FYearEnd);
					 String JHkeyid=request.getParameter("JHkeyid");
					 String QuarterFirst=CommonFunctions.getFirstDateofMonth(-2).substring(3,11);	 
					 String QuarterSecMonth=CommonFunctions.getFirstDateofMonth(-1).substring(3,11);
					 String DMTflid=request.getParameter("DMTflid");
                     String DmtOriginalId=dashboardService.getDmtFlid(DMTflid);
					 CommonMessage.debugMsg("The QuarterSecond"+QuarterSecMonth);
					 CommonMessage.debugMsg("QuarterFirst"+QuarterFirst);
					 String QuarterFirst1=QuarterFirst;
					 CommonMessage.debugMsg("QuarterFirst"+QuarterFirst1);
					 String CurrentMonth=CommonFunctions.getCurrentMonth();
					 String QuarterEnd=CurrentMonth+"-"+CurrentYear;
					 CommonMessage.debugMsg("CurrentMonth"+QuarterEnd);
					 String date=CommonFunctions.getDate();
					 CommonMessage.debugMsg("Date:"+date);
					 String CurrentDate=date.replace("-","");
	  				 String flid=request.getParameter("flid");
	  				 String fromMonth=request.getParameter("fromMonth");
	  				 String toMonth=request.getParameter("toMonth");
	  				 String FirstMonth=request.getParameter("FirstMonth");
	  				//&FromDate="+FromDate+"&ToDate="+ToDate+"&Finance="+Finance
	  				 String FromDate=request.getParameter("FromDate");
	  				CommonMessage.debugMsg("FromDate::::"+FromDate);
	  				 String ToDate=request.getParameter("ToDate");
	  				 String Finance=request.getParameter("Finance");
	  				 String StartMonth=null;
	  			    String EndMonth=null;
	  			    if(FromDate.length()>1&&ToDate.length()>1){
	  			    	StartMonth=FromDate.substring(3,11);
	  			        CommonMessage.debugMsg("FromMonth::::"+StartMonth);
	  			        EndMonth=ToDate.substring(3,11); 			    
	  			        CommonMessage.debugMsg("ToMonth::::"+EndMonth);
	  			    }

	  				 String JHName=dashboardService.FunctionallocnJHID(flid);
	  				 String DMTname=dashboardService.FunctionallocnDMTID(flid);
	  				 CommonMessage.debugMsg("DEPARTMENT"+DMTname);
	  				 String Location=dashboardService.LocationName(flid);
	  				 String format=ExcelUtils.getFormat(request);
	  				 String path = UIUtils.getExcelTemplatePath(request);
				     

	  					 Workbook wb = dashboardService.NewDashboardExcelView(flid,fromMonth,toMonth,FirstMonth,FYearStart,FYearEnd,QuarterFirst1,QuarterSecMonth,QuarterEnd,Location,JHName,DMTname,DmtOriginalId,date,format,path,JHkeyid,FromDate,ToDate,StartMonth,EndMonth,Finance);
					     format = ".xlsx";
					     ExcelUtils.writeToExcelViewResponse(response, wb,DMTname+"_DSB_"+CurrentDate, format); 

	  			
	  			 }
	  			 catch(Exception e){
	  				 
	  				 PrintWriter out=response.getWriter();
	  				 JSONObject err=new JSONObject();
	  				 err.put("message","Data Not Found");
	  				 out.print(err.toString());
	  				 
	  			 }
				
			}
			
			else if(action.equals("DBUserRights_getData.dashboard")){
				PrintWriter out = response.getWriter();
				try
				{
					 UIUtils.displayRequestParamsValue(request);
					 httpSession = request.getSession();
					
					 JSONObject jsonObject = new JSONObject();
					 List<String[]> DBUserRights=dashboardService.getDBUserRights();
					 jsonObject = UIUtils.convertToJqGridTableObject(DBUserRights,request,2,0); 
					 out.println(jsonObject);
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			else if(action.equals("DBUserRights_save.dashboard")){
				SaveDBUserRights(request, response);
				
			}
			else if(action.equals("DBUserRights_getExcel.dashboard")){
				CommonFilter commonFilter = new CommonFilter();
				//commonFilter.setIsneedroleid("N");
				JSONObject tblJSONObj=(JSONObject)httpSession.getAttribute("DBUserRightsColModelxcel");
				tblJSONObj.put("title", "DashBoard User Rights");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = dashboardService.getDBUserRightsExcel(commonFilter,tblJSONObj,format);
				CommonMessage.debugMsg("Inside get excel");
				ExcelUtils.writeToResponse(response, wb, "DashBoardUserRights", format);
			}
			
		} catch (ServiceObjectCreationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
			
	}
	

	private void deleteMessageBoard(HttpServletRequest request,
			HttpServletResponse response)throws IOException {
		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	
    	try
		{
    	if(httpSession !=null && user !=null)
		{
    		AdmTlScrollmsgmst existadmTlScrollmsgmst = new AdmTlScrollmsgmst();
    		
    		AdmTlScrollmsgmst newadmTlScrollmsgmst = new AdmTlScrollmsgmst();
    		
    		newadmTlScrollmsgmst=(AdmTlScrollmsgmst)UIUtils.setBeanProperties((Object)newadmTlScrollmsgmst,request);
			//JhaTlFiveSAuditareamst existJhaTlFiveSAuditareamst=(JhaTlFiveSAuditareamst)httpSession.getAttribute("existJhaTlFiveSAuditareamst");
    		
    		String Messagekeyid = request.getParameter("keyid");
    		
    		CommonMessage.debugMsg(" Criticalkeyid"+Messagekeyid);
    		
    		if(UIUtils.isValidKeyId(Messagekeyid))
    			newadmTlScrollmsgmst.setSmsgKeyid(Messagekeyid);
    		
			
			JSONObject successData=new JSONObject();
			JSONObject Messagedelete=new JSONObject();
			String msgPropertyIdnt;	
			if(UIUtils.isValidKeyId(newadmTlScrollmsgmst.getSmsgKeyid())){
				
				newadmTlScrollmsgmst=dashboardService.deleteMessageBoardNew(newadmTlScrollmsgmst);
				httpSession.removeAttribute("MessageBoard"+newadmTlScrollmsgmst.getSmsgKeyid());
				msgPropertyIdnt = "success-delete";
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				 
			}
			
			 successData.put("formClear",true);
	    	 Messagedelete.put("successData", successData);
	    	 out.print(Messagedelete.toString());

    		}
		}
		catch(Exception e)
		{
			
		}

		
	}

	private void saveMessageBoard(HttpServletRequest request,
			HttpServletResponse response)throws IOException {
		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
		HttpSession httpSession = request.getSession(false);    	
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		ServletOutputStream out = response.getOutputStream();
		String hdnelementId = request.getParameter("hdnelementId");

  try
  {
	if( httpSession != null && user != null)
	{	
		AdmTlScrollmsgmst existadmTlScrollmsgmst = new AdmTlScrollmsgmst();
		
		AdmTlScrollmsgmst newadmTlScrollmsgmst = new AdmTlScrollmsgmst();
		
		newadmTlScrollmsgmst=(AdmTlScrollmsgmst)UIUtils.setBeanProperties((Object)newadmTlScrollmsgmst,request);
		
		newadmTlScrollmsgmst.setSmsgCreatedby(user.getUsrm_ccno());

		boolean insert = true;

		if( newadmTlScrollmsgmst.getSmsgKeyid() == null )
		 {							
			CommonMessage.debugMsg("KeyId is Null" );
			existadmTlScrollmsgmst = dashboardService.createMessageBoard(newadmTlScrollmsgmst,existadmTlScrollmsgmst);
		 }	
		else
		  {
			CommonMessage.debugMsg("Update function");						
			existadmTlScrollmsgmst = dashboardService.updateMessageBoard(newadmTlScrollmsgmst,existadmTlScrollmsgmst);
			insert = false;
		  }					
		JSONObject successData = new JSONObject();
		JSONObject returnData = new JSONObject();
		String msgPropertyIdnt;					 
		 if( insert)
		   {
			 msgPropertyIdnt = "success-save";
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			successData.put("keyId", existadmTlScrollmsgmst.getSmsgKeyid());
			
			returnData.put("successData", successData);
			returnData.put("keyId",existadmTlScrollmsgmst.getSmsgKeyid());
			out.print(returnData.toString());
		   }
		 else{
			msgPropertyIdnt = "success-update";
		    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
            successData.put("keyId", existadmTlScrollmsgmst.getSmsgKeyid());
			
			returnData.put("successData", successData);
			returnData.put("keyId",existadmTlScrollmsgmst.getSmsgKeyid());
			out.print(returnData.toString());
		 }	
		 
      }
	
  }
  catch (ValidationExceptions e) 
  {
		CommonMessage.debugMsg("ValidationExceptions "+e.getMessage());
		net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "criticalProcessValidations");
		out.print(errMessage.toString());
  }
 catch(Exception e)
      {   
	 	e.printStackTrace();
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not Saved");
		out.print(err.toString());
      }

		
	}

	private void deleteJhActDtl(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) throws Exception {
		PrintWriter out= response.getWriter();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String jhActDtl=request.getParameter("jhActDtl");
    	GenTlJhactivitychartdtl newGenTlJhactivitychartdtl = new GenTlJhactivitychartdtl ();
		GenTlJhactivitychartdtl existGenTlJhactivitychartdtl = new  GenTlJhactivitychartdtl();
		Boolean clrVal=true;
    		newGenTlJhactivitychartdtl=(GenTlJhactivitychartdtl)UIUtils.setBeanProperties((Object)newGenTlJhactivitychartdtl,request);
    		String saveMsg ;
    		JSONArray jhActDtlJsonArr = JSONArray.fromString(jhActDtl);
    		List<GenTlJhactivitychartdtl> jhActDtlList = null;  
    		GenTlJhactivitychartdtl genTlJhactivitychartdtl = new GenTlJhactivitychartdtl(); 
    		genTlJhactivitychartdtl.setJacdCreatedby(user.getUsrm_keyid());
			jhActDtlList=(List<GenTlJhactivitychartdtl>)UIUtils.convertJSONArrToList(genTlJhactivitychartdtl, jhActDtlJsonArr);
			CommonMessage.debugMsg("jhActDtlList.size() "+jhActDtlList.get(0).getJacdKeyid());
    		existGenTlJhactivitychartdtl = jhactivitychartService.delete(jhActDtlList);
    		JSONObject successData = new JSONObject();
    		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
    		JSONObject returnData = new JSONObject();
    		returnData.put("formClear",true);
    		returnData.put("successData", successData);				
    		out.print(returnData.toString());
		
	}

	private void deleteJhAct(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) throws Exception {
		PrintWriter out = response.getWriter();
		GenTlJhactivitychartmst genTlJhactivitychartmst = new GenTlJhactivitychartmst();
		GenTlJhactivitychartmst existGenTlJhactivitychartmst = new GenTlJhactivitychartmst();
		genTlJhactivitychartmst=(GenTlJhactivitychartmst)UIUtils.setBeanProperties((Object)genTlJhactivitychartmst,request);
		existGenTlJhactivitychartmst = jhactivitychartService.delete(genTlJhactivitychartmst);
    	JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",true);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
	}

	private void saveJhAct(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) throws Exception,BusinessApplicationExceptions,ValidationExceptions {
		PrintWriter out= response.getWriter();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String jhActDtl=request.getParameter("jhActDtl");
    	CommonMessage.debugMsg("jhActDtl "+jhActDtl);
    	if( httpSession != null && user != null)
    	{	
    		GenTlJhactivitychartmst newGenTlJhactivitychartmst = new GenTlJhactivitychartmst ();
    		GenTlJhactivitychartmst existGenTlJhactivitychartmst = (GenTlJhactivitychartmst) httpSession.getAttribute("genTlJhactivitychartmst");
    		Boolean clrVal=true;
	    	try{
	    		newGenTlJhactivitychartmst=(GenTlJhactivitychartmst)UIUtils.setBeanProperties((Object)newGenTlJhactivitychartmst,request);
	    		newGenTlJhactivitychartmst.setAchmCreatedby(user.getUsrm_keyid());
	    		String saveMsg ;
	    		if(UIUtils.isValidKeyId(jhActDtl)){
		    		JSONArray jhActDtlJsonArr = JSONArray.fromString(jhActDtl);
		    		List<GenTlJhactivitychartdtl> jhActDtlList = null;  
		    		GenTlJhactivitychartdtl genTlJhactivitychartdtl = new GenTlJhactivitychartdtl(); 
		    		genTlJhactivitychartdtl.setJacdCreatedby(user.getUsrm_keyid());
	    			jhActDtlList=(List<GenTlJhactivitychartdtl>)UIUtils.convertJSONArrToList(genTlJhactivitychartdtl, jhActDtlJsonArr);
	    			newGenTlJhactivitychartmst.setGenTlJhactivitychartdtl(jhActDtlList);
	    			CommonMessage.debugMsg("jhActDtlList.size() "+jhActDtlList.get(0).getJacdKeyid());
	    		}
					if( !UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmKeyid())) {	
						existGenTlJhactivitychartmst = jhactivitychartService.create(newGenTlJhactivitychartmst,existGenTlJhactivitychartmst);
						 saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
					}	
					else{
						existGenTlJhactivitychartmst = jhactivitychartService.update(newGenTlJhactivitychartmst,existGenTlJhactivitychartmst);
						saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update");
						clrVal=false;
					}
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				returnData.put("successData",successData);
				returnData.put("formClear",clrVal);
				out.print(returnData.toString());
				
	    	}catch (BusinessApplicationExceptions e){
	    		CommonMessage.debugMsg("BusinessApplicationExceptions ");
    			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "jhActivityProp");
    			out.print(errMessage.toString());
    			e.printStackTrace();
    	    }catch (ValidationExceptions e) {
				CommonMessage.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "jhActivityProp");
				e.printStackTrace();
				out.print(errMessage.toString());
	    	}
	    	catch(Exception e)
			{
	    		CommonMessage.debugMsg("Exceptions");
	    		e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    	}
	}

	private void jhActivity(String action, HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession ) throws Exception {
		CommonMessage.debugMsg("in jhactivity  "+action);
		if(action.contains("_input") || action.contains("_view")){
			jhActivityInput(request,response,httpSession,action );
		}else if(action.contains("_getCol")){
			jhActivitygetCol(request,response,httpSession,action );	
		}else if(action.contains("_getData")){
			jhActivitygetData(request,response,httpSession,action );
		}else if(action.equals("jhActivityMainGrid_getExcel.dashboard")){
			CommonFilter commonFilter =new CommonFilter();//populateCommonFilter(request,"jhActGridDtl",true);
			JSONObject tblJSONObj=(JSONObject)httpSession.getAttribute("jhActivityGrid");
			tblJSONObj.put("title", "JH Activity");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = jhactivitychartService.getjhActGridExcel(commonFilter,tblJSONObj,format);
			CommonMessage.debugMsg("Inside get excel");
			ExcelUtils.writeToResponse(response, wb, "JHActivity", format);
		}
	}
	
	private void jhActivitygetCol(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession,String action ) throws Exception {
		PrintWriter out = response.getWriter();
		CommonFilter commonFilter =populateCommonFilter(request,"jhActGridData",true);
		List<String[]> jhActList =null;
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();
		if(action.equals("jhActivityMainGrid_getCol.dashboard")){
			CommonMessage.debugMsg(" inside getCol");
			jhActList =jhactivitychartService.getjhAct(commonFilter);
			jqGridTableModel.setTableButton(true);
		}else{
			CommonMessage.debugMsg(" inside else");
			jhActList =dashboardService.getjhAct(commonFilter);
			jqGridTableModel.setTableButton(false);
		}
		

		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		
		gridColModel.setHeaderNum(1);
		
		String [] colHeader1 = jhActList.get(2);
		String [] colHeaderCond = jhActList.get(1);
		List<String[]> headers = new ArrayList<String[]>();
		headers.add(colHeader1);
		JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		if(action.equals("jhActivityMainGrid_getCol.dashboard")){
			jsonObject.put("tableHeight", "75%%");
			jsonObject.put("tableWidth", "108%%");
		}else{
			jsonObject.put("tableHeight", "65%%");
			jsonObject.put("tableWidth", "106%%");
		}
		httpSession.removeAttribute("jhActivityGrid");
		httpSession.setAttribute("jhActivityGrid", jsonObject);
		out.println(jsonObject);
	}
	private void jhActivitygetData(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession,String action ) throws Exception {
		CommonFilter commonFilter = populateCommonFilter(request,"jhActGridData",false);
		List<String[]> jhActList = null;
		if(action.equals("jhActivityMainGrid_getData.dashboard")){
			CommonMessage.debugMsg(" inside getCol");
			jhActList =jhactivitychartService.getjhAct(commonFilter);
		}else{
			CommonMessage.debugMsg(" inside else");
			jhActList =dashboardService.getjhAct(commonFilter);
		}
		PrintWriter out = response.getWriter();
		JSONObject jhActgridData = UIUtils.convertToJqGridTableObject(jhActList, request, 3, 0);
		out.println(jhActgridData);
	}

	private void jhActivityInput(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession,String action ) throws ServletException, IOException {
		if(action.equals("jhActivityMainGrid_view.dashboard")){
			CommonMessage.debugMsg("jhActivityMainGrid_input.dashboard  : "+action);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/jhActivityEntryGrid.jsp"); 
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/jhActivityMain.jsp"); 
			rd.forward(request, response);
		}
	}

	private JSONObject getTableModelDBUserRightsxcel(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		 
		String[] colHeader = headers.get(1);
		
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		jqGridTableModel.setTableHeight(300);
	
		 for(int i =0; i < colHeader.length; i++)
		{
			CommonMessage.debugMsg("Length : "+colHeader.length);
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+"_"+ i);
			CommonMessage.debugMsg("colHeader[i] "+colHeader[i]);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+"_"+ i);
			jqGridColModel.setWidth(100);				
			jqGridColModel.setEditable(false);
			
			if(i<=1)
			{
				jqGridColModel.setHidden(true);
				//jqGridColModel.setKey(true);
			}
			else if(i==2){
				jqGridColModel.setWidth(130);
				jqGridColModel.setAlign("left");
			}
			else
			{
				 if(i>=3 ){
				    jqGridColModel.setFormatter("chkbox_DBUserRights");
					jqGridColModel.setWidth(150);
					jqGridColModel.setAlign("center");
					 
				 }
			}
			 jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 tableModel.set("tableHeight", "82%%");
		tableModel.set("tableWidth", "100%%");
		 return tableModel;
	
	}

	private void SaveDBUserRights(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		UIUtils.displayRequestParamsValue(request);
		if (httpSession != null && user != null) 
			{
				try 
				{
					List<AdmTlDashboadUserrights> existAdmTlDashboadUserrights = (List<AdmTlDashboadUserrights>)httpSession.getAttribute("AdmTlDashboadUserrights");
					AdmTlDashboadUserrights admtlUserRights=new AdmTlDashboadUserrights();	
					admtlUserRights = (AdmTlDashboadUserrights) UIUtils.setBeanProperties((Object) admtlUserRights,request);
					
					String DBUserRightsList=request.getParameter("selectedDBUserRights");
					CommonMessage.debugMsg("dbuser"+DBUserRightsList);
					List<AdmTlDashboadUserrights> newDBUserRightsList = null;
					JSONArray DBUserRightsLinkJson = null;
					if (DBUserRightsList!= null && !DBUserRightsList.isEmpty()) 
					{
						DBUserRightsLinkJson = JSONArray.fromString(DBUserRightsList);
						newDBUserRightsList = (List<AdmTlDashboadUserrights>) UIUtils.convertJSONArrToList(admtlUserRights,DBUserRightsLinkJson);
						
					}
					String saveMsg = null ;
					if(newDBUserRightsList!=null){
						existAdmTlDashboadUserrights=dashboardService.createDBUserRights(newDBUserRightsList,existAdmTlDashboadUserrights,user.getUsrm_ccno());
						saveMsg = "Data Saved Successfully";
						JSONObject returnData = new JSONObject();				
						JSONObject successData = new JSONObject();	
						successData.put("msg", saveMsg);
						returnData.put("formClear",false);	
						returnData.put("successData",successData);
						out.print(returnData.toString());	
					}
					else {}
					}
				catch (Exception e) 
				{
					e.printStackTrace();
					CommonMessage.debugMsg("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
			}	
		
	}

	private JSONObject getTableModelDBUserRights(List<String[]> headers,String fromExcel) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		 
		String[] colHeader = headers.get(0);
		
		String[] colHeader1 = headers.get(1);
		String[] emptyrow = new String[colHeader.length]; 
		 for(int i =0; i < colHeader.length; i++)
			{
			 emptyrow[i]="";
			}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		jqGridTableModel.setTableHeight(300);
	
		 for(int i =0; i < colHeader.length; i++)
		{
			CommonMessage.debugMsg("Length : "+colHeader.length);
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+"_"+ i);
			CommonMessage.debugMsg("colHeader[i] "+colHeader[i]);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+"_"+ i);
			jqGridColModel.setWidth(100);				
			
			jqGridColModel.setEditable(false);
			
			if(i<=1)
			{
				jqGridColModel.setHidden(true);
				//jqGridColModel.setKey(true);
			}
			else if(i==2){
				jqGridColModel.setWidth(130);
				jqGridColModel.setAlign("left");
			}
			else
			{
				 if(i>=3 ){
				    jqGridColModel.setFormatter("chkbox_DBUserRights");
					jqGridColModel.setWidth(150);
					jqGridColModel.setAlign("center");
					 
				 }
			}
			 jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 tableModel.set("tableHeight", "82%%");
		tableModel.set("tableWidth", "100%%");
		 return tableModel;
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request,
			String string, boolean b) {
		// TODO Auto-generated method stub
      HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(string);
		if( commonFilter != null && ! b ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			//commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(string);
			httpSession.setAttribute(string, commonFilter);
		}
		
		String vcumKeyid = request.getParameter("vcumKeyid");
		String flid = request.getParameter("flid");
		String vchmKeyid = request.getParameter("vchmKeyid");
		String type = request.getParameter("type");
		
		CommonMessage.debugMsg("type=="+type);
		
		commonFilter.setFlid(flid);
		commonFilter.setKey(vcumKeyid);
		commonFilter.setKK(vchmKeyid);
		commonFilter.setType(type);
		
		CommonMessage.debugMsg("type=="+commonFilter.getType());
		
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		ComboFilter employee = new ComboFilter();
		employee.setId(user.getUsrm_ccno());
		commonFilter.setEmployee(employee);

		return commonFilter;
		
		
	}
	

	private JqGridColModel getColModel(String colIndex, int width,String align,boolean hidden,boolean groupbyfield,boolean key) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setWidth(width);				
		jqGridColModel.setAlign(align);
		jqGridColModel.setHidden(hidden);
		jqGridColModel.setKey(key);
		if(!colIndex.equals("dataorder")&&!colIndex.contains("checkRolevalue"))
		{
			if(!colIndex.equals("menu")&&!colIndex.equals("menuID"))
			jqGridColModel.setFormatter("chkbox_DBUserRights");
		}
		else
			jqGridColModel.setEditable(false);
		return jqGridColModel;
	}

	private void getDashboardPillars(HttpServletRequest request,HttpServletResponse response){
		try {
			CommonMessage.debugMsg("inside getDashboardPillars");
			List<String[]> pillarDetails =   dashboardService.getDhashboardPillars();
			CommonMessage.debugMsg("pillarDetails "+pillarDetails.size());
			PrintWriter out = response.getWriter();
			
			out.print( JSONArray.fromCollection( pillarDetails) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void displayNewDashboardPageCharts(HttpServletRequest request,HttpServletResponse response){
		try {
			CommonMessage.debugMsg("Inside the saral dashboard");
			String pillar ="NDB";
			CommonMessage.debugMsg("The Pillar Is:::"+pillar);
			String fromPage = request.getParameter("fromPage");
			String type = request.getParameter("type");
			CommonMessage.debugMsg("The Type Is"+type);
			/*CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			request.setAttribute("FromMonth", commonFilter.getFromMonth());
			request.setAttribute("ToMonth", commonFilter.getToMonth());
			request.setAttribute("LstFromMonth", CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
			request.setAttribute("lastTheeMonth", CommonFunctions.getFirstDateofMonth(-2).substring(3,11));
			request.setAttribute("lastTwoyear", CommonFunctions.getFirstDateofMonth(-22).substring(3,11));	*/
			request.setAttribute("pillar", pillar);
			request.setAttribute("type", type);
			//request.setAttribute("fromPage", fromPage);
			UIUtils.forwardRequest(request, response, "/pages/Gen/NewdashboardChart.jsp");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	
	private void displayNewDashboardPageTable1(HttpServletRequest request,HttpServletResponse response){
		try {
			CommonMessage.debugMsg("Inside the saral dashboard");
			String pillar = request.getParameter("pillar");
			CommonMessage.debugMsg("The Pillar Is:::"+pillar);
			String fromPage = request.getParameter("fromPage");
			String type = request.getParameter("type");
			CommonMessage.debugMsg("The Type Is"+type);
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			request.setAttribute("FromMonth", commonFilter.getFromMonth());
			request.setAttribute("ToMonth", commonFilter.getToMonth());
			request.setAttribute("LstFromMonth", CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
			request.setAttribute("lastTheeMonth", CommonFunctions.getFirstDateofMonth(-2).substring(3,11));
			request.setAttribute("lastTwoyear", CommonFunctions.getFirstDateofMonth(-22).substring(3,11));	
			request.setAttribute("pillar", pillar);
			request.setAttribute("type", type);
			request.setAttribute("fromPage", fromPage);
			UIUtils.forwardRequest(request, response, "/pages/Gen/NewdashboardTable.jsp");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	private void displayNewDashboardPageTable2(HttpServletRequest request,HttpServletResponse response){
		try {
			CommonMessage.debugMsg("Inside the saral dashboard");
			String pillar = request.getParameter("pillar");
			CommonMessage.debugMsg("The Pillar Is:::"+pillar);
			String fromPage = request.getParameter("fromPage");
			String type = request.getParameter("type");
			CommonMessage.debugMsg("The Type Is"+type);
			
			String flid=CommonFunctions.getLoginFlid(request);
			CommonMessage.debugMsg("Loginflid"+flid);
			
			/*CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			request.setAttribute("FromMonth", commonFilter.getFromMonth());
			request.setAttribute("ToMonth", commonFilter.getToMonth());
			request.setAttribute("LstFromMonth", CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
			request.setAttribute("lastTheeMonth", CommonFunctions.getFirstDateofMonth(-2).substring(3,11));
			request.setAttribute("lastTwoyear", CommonFunctions.getFirstDateofMonth(-22).substring(3,11));	*/
			request.setAttribute("pillar", pillar);
			request.setAttribute("type", type);
			request.setAttribute("flid",flid); 
			//request.setAttribute("fromPage", fromPage);
			UIUtils.forwardRequest(request, response, "/pages/Gen/DashboardAETAdherence.jsp");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	private void displayNewDashboardPageTable3(HttpServletRequest request,HttpServletResponse response){
		try {
			CommonMessage.debugMsg("Inside the saral dashboard");
			String pillar = request.getParameter("pillar");
			CommonMessage.debugMsg("The Pillar Is:::"+pillar);
			String fromPage = request.getParameter("fromPage");
			String type = request.getParameter("type");
			CommonMessage.debugMsg("The Type Is"+type);
			
			String flid=CommonFunctions.getLoginFlid(request);
			CommonMessage.debugMsg("Loginflid"+flid);
			
			/*CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			request.setAttribute("FromMonth", commonFilter.getFromMonth());
			request.setAttribute("ToMonth", commonFilter.getToMonth());
			request.setAttribute("LstFromMonth", CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
			request.setAttribute("lastTheeMonth", CommonFunctions.getFirstDateofMonth(-2).substring(3,11));
			request.setAttribute("lastTwoyear", CommonFunctions.getFirstDateofMonth(-22).substring(3,11));	*/
			request.setAttribute("pillar", pillar);
			request.setAttribute("type", type);
			request.setAttribute("flid",flid); 
			//request.setAttribute("fromPage", fromPage);
			UIUtils.forwardRequest(request, response, "/pages/Gen/DashboardPACTAdherence.jsp");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	private void displayNewDashboardPageTable4(HttpServletRequest request,HttpServletResponse response){
		try {
			CommonMessage.debugMsg("Inside the saral dashboard");
			String pillar = request.getParameter("pillar");
			CommonMessage.debugMsg("The Pillar Is:::"+pillar);
			String fromPage = request.getParameter("fromPage");
			String type = request.getParameter("type");
			CommonMessage.debugMsg("The Type Is"+type);
			
			String flid=CommonFunctions.getLoginFlid(request);
			CommonMessage.debugMsg("Loginflid"+flid);
			
			/*CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			request.setAttribute("FromMonth", commonFilter.getFromMonth());
			request.setAttribute("ToMonth", commonFilter.getToMonth());
			request.setAttribute("LstFromMonth", CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
			request.setAttribute("lastTheeMonth", CommonFunctions.getFirstDateofMonth(-2).substring(3,11));
			request.setAttribute("lastTwoyear", CommonFunctions.getFirstDateofMonth(-22).substring(3,11));	*/
			request.setAttribute("pillar", pillar);
			request.setAttribute("type", type);
			request.setAttribute("flid",flid); 
			//request.setAttribute("fromPage", fromPage);
			UIUtils.forwardRequest(request, response, "/pages/Gen/DashboardLastMomPoint.jsp");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	private void displayNewDashboardPage(HttpServletRequest request,HttpServletResponse response){
		try {
			CommonMessage.debugMsg("Inside the saral dashboard");
			String pillar = request.getParameter("pillar");
			CommonMessage.debugMsg("The Pillar Is:::"+pillar);
			String fromPage = request.getParameter("fromPage");
			String type = request.getParameter("type");
			CommonMessage.debugMsg("The Type Is"+type);
			
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 CommonMessage.debugMsg("CurrentYear"+CurrentYear);
			 String FYearStart="01-APR-"+CurrentYear;
			 CommonMessage.debugMsg("FYearStart"+FYearStart);
		//	 String NextYear=CurrentYear.substring(0,3);
			 int NextYear=Integer.parseInt(CurrentYear)+1; 
			 CommonMessage.debugMsg("NextYear"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="31-MAR-"+(NextYear);
			 CommonMessage.debugMsg("FYearEnd Date :::::::::"+FYearEnd);
			 
	

			String flid=CommonFunctions.getLoginFlid(request);
			CommonMessage.debugMsg("Loginflid"+flid);
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			String LastDate=CommonFunctions.getDate();
			CommonMessage.debugMsg("lastdate"+LastDate);
			request.setAttribute("FromMonth", commonFilter.getFromMonth());
			request.setAttribute("ToMonth", commonFilter.getToMonth());
			
			request.setAttribute("StartMonth", CommonFunctions.getFirstDateofMonth(-5).substring(1,11));
			commonFilter.setToMonth(CommonFunctions.getLastDayOfMonth(LastDate));
			request.setAttribute("sixMonth",CommonFunctions.getLastDayOfMonth(LastDate));
			
			request.setAttribute("FirstMonth", CommonFunctions.getFirstDateofMonth(-11).substring(1,11));
			
			request.setAttribute("FYearFirstMonth", CommonFunctions.getFirstDateofMonth(-8).substring(1,11));
			
			request.setAttribute("FYearEndMonth", CommonFunctions.getFirstDateofMonth(3).substring(1,11));
			
		//	request.setAttribute("ToMonth", commonFilter.getLastDayOfMonth);
			request.setAttribute("EndMonth", CommonFunctions.getFirstDateofMonth(1).substring(1,11));
			//request.setAttribute("EndMonth", CommonFunctions.getDate().substring(1,11));
			request.setAttribute("LstFromMonth", CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
			request.setAttribute("lastTheeMonth", CommonFunctions.getFirstDateofMonth(-2).substring(3,11));
			request.setAttribute("lastTwoyear", CommonFunctions.getFirstDateofMonth(-22).substring(3,11));
			
			request.setAttribute("YearStartMonth", CommonFunctions.getFirstDateofMonth(1).substring(3,11));
			request.setAttribute("YearEndMonth", CommonFunctions.getFirstDateofMonth(-10).substring(3,11));

			request.setAttribute("pillar", pillar);
			request.setAttribute("type", type);
			request.setAttribute("fromPage", fromPage);
			request.setAttribute("flid", flid);
			request.setAttribute("FYearStart", FYearStart);
			UIUtils.forwardRequest(request, response, "/pages/Gen/Newdashboard.jsp");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	private void displayDashboardPage(HttpServletRequest request,HttpServletResponse response){
		try {
			
			String pillar = request.getParameter("pillar");
			String fromPage = request.getParameter("fromPage");
			
			String type = request.getParameter("type");
			CommonMessage.debugMsg("The Type Is"+type);
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			request.setAttribute("FromMonth", commonFilter.getFromMonth());
			request.setAttribute("ToMonth", commonFilter.getToMonth());
			request.setAttribute("LstFromMonth", CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
			request.setAttribute("lastTheeMonth", CommonFunctions.getFirstDateofMonth(-2).substring(3,11));
			request.setAttribute("lastTwoyear", CommonFunctions.getFirstDateofMonth(-22).substring(3,11));	
			request.setAttribute("pillar", pillar);
			request.setAttribute("type", type);
			request.setAttribute("fromPage", fromPage);
			UIUtils.forwardRequest(request, response, "/pages/Gen/dashboard.jsp");
		/*	if(type.equals("GPH"))
				UIUtils.forwardRequest(request, response, "/pages/Gen/dashboard.jsp");
			else
				UIUtils.forwardRequest(request, response, "/pages/Gen/dashboardGrid.jsp");*/
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	private void getRelatedData(HttpServletRequest request,HttpServletResponse response){
		try {
			
			String pillar = request.getParameter("pillar");
			CommonMessage.debugMsg("Pillar :::::::::::"+pillar);
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			 String userid =  user.getUsrm_keyid();
			//String userid =  request.getParameter("userid");
			//String level = request.getParameter("level");
			List<DashboardDispbean> pillarDetails =   dashboardService.getDashboardRptDetails(pillar,userid);
			CommonMessage.debugMsg( " pillarDetails " + pillarDetails.size());
			PrintWriter out = response.getWriter(); 
			
			out.print( JSONArray.fromCollection(pillarDetails));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void messageDashboardSave(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
	    	if( httpSession != null && user != null){	
	    		GenTlMessageboard existGenTlMessageboard = (GenTlMessageboard)httpSession.getAttribute("genTlMessageboard");	    		
	    		GenTlMessageboard newGenTlMessageboard = new GenTlMessageboard();	    		
	    		newGenTlMessageboard.setMsgbCreatedby(user.getUsrm_ccno());		    		
	    		newGenTlMessageboard =(GenTlMessageboard)UIUtils.setBeanProperties((Object)newGenTlMessageboard,request);		    		

	    		String saveMsg = null ;
				if( newGenTlMessageboard.getMsgbKeyid()== null ){	
					existGenTlMessageboard =dashboardService.createMessage(newGenTlMessageboard,existGenTlMessageboard);					
					saveMsg = "Data Saved Successfully";
				}	
				else{
					existGenTlMessageboard =dashboardService.updateMessage(newGenTlMessageboard,existGenTlMessageboard);
					CommonMessage.debugMsg("Update SucessFuly  ");
					saveMsg = "Data Updated Successfully";
								
				}				
				CommonMessage.debugMsg("save details "+existGenTlMessageboard.getMsgbKeyid());
				JSONObject returnData = new JSONObject();			
				JSONObject successData = new JSONObject();		
				successData.put("msg", saveMsg);
				returnData.put("formClear",false);	
				returnData.put("successData",successData);
				out.print(returnData.toString());
	    	 }		    	
		}
		catch(ValidationExceptions e){
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "MessageBoard");
			out.print(errMessage.toString());
			
		}
		catch(BusinessApplicationExceptions e){   
			
			CommonMessage.debugMsg("Error Servler e -"+e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "MessageBoard");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage );
		}
		catch(Exception e){				
		    e.printStackTrace();
			JSONObject err = new JSONObject();
			out.print(err.toString());			
		}				
	}
	private void messageDashboardDelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
	if( httpSession != null && user != null)
	  {	
			GenTlMessageboard existGenTlMessageboard = (GenTlMessageboard)httpSession.getAttribute("genTlMessageboard");	    	
			 	    		
			GenTlMessageboard newGenTlMessageboard = new GenTlMessageboard();
					
			newGenTlMessageboard.setMsgbCreatedby(user.getUsrm_ccno());
			 	      		
			newGenTlMessageboard =(GenTlMessageboard)UIUtils.setBeanProperties((Object)newGenTlMessageboard,request);	   			 
		try {								
			existGenTlMessageboard = dashboardService.deleteMessageBoard(newGenTlMessageboard);										
			httpSession.setAttribute(existGenTlMessageboard.getMsgbKeyid(), existGenTlMessageboard);		
			JSONObject mode = new JSONObject();
			JSONObject persistentData = new JSONObject(); 				
			JSONObject successData = new JSONObject();
			successData.put("Keyid", newGenTlMessageboard.getMsgbKeyid());
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			JSONObject returnData = new JSONObject();						
			returnData.put("successData", successData);
			returnData.put("formClear", true);	
			CommonMessage.debugMsg("successData"+successData);		
			out.print(returnData.toString());
			
			
		}
	catch(BusinessApplicationExceptions e)
	{
		CommonMessage.debugMsg("Business EXC "+e);
		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "");
		out.print(errMessage.toString());
		
	}
	catch(Exception e)
	{
		JSONObject err = new JSONObject();
		String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");			
		JSONObject successData = new JSONObject();
		err.put("successData", successData);		
		e.printStackTrace();
		out.print(err.toString());
	}
}
		
	}
	private void MessagePopUpGetCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String msgString=request.getParameter("msgString");
		String roleId = request.getParameter("roleId");
		String loginFlid = CommonFunctions.getLoginFlid(request);
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject();
		List<String[]> msgGrid = null;
		try {
			msgGrid = dashboardService.getMessage(loginFlid, roleId);
		} catch (Exception e) {
		e.printStackTrace();
		}
		jsonObject = getTableModelFormsgGrid(msgGrid);
		out.println(jsonObject);
}
private void MessagePopUpGetData(HttpServletRequest request,	HttpServletResponse response) throws IOException{
		try {
		String msgString=request.getParameter("msgString");
		String loginFlid = CommonFunctions.getLoginFlid(request);
		String roleId = request.getParameter("roleId");
		List<String[]> getMessageGrid = dashboardService.getMessage(loginFlid, roleId);
		PrintWriter out = response.getWriter();
		JSONObject messageGridJson = UIUtils.convertToJqGridTableObject(getMessageGrid, request, 1	,0);
		out.println(messageGridJson);
		} catch (Exception e) {
		CommonMessage.debugMsg(e.getMessage());
		}
}
private void getMessageBoardMsgs(HttpServletRequest request,	HttpServletResponse response) throws IOException{
	try {
	String msgString=request.getParameter("msgString");
	String loginFlid = CommonFunctions.getLoginFlid(request);
	String roleId = request.getParameter("roleId");
	List<String[]> getMessageGrid = dashboardService.getMessage(loginFlid, roleId);
	PrintWriter out = response.getWriter();
	JSONObject msgBoard = new JSONObject();
	JSONArray messages =  JSONArray.fromCollection(getMessageGrid);
	msgBoard.put("msgs", messages);
	out.println(msgBoard);
	} catch (Exception e) {
	CommonMessage.debugMsg(e.getMessage());
	}
}

private JSONObject getTableModelFormsgGrid(List<String[]> headers) {
	JqGridTableModel jqGridTableModel = new JqGridTableModel();
	String[] colHeader = headers.get(0);
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.setRowNumbers(true);
	//jqGridTableModel.setTableHeight(100); 
	//jqGridTableModel.setTableWidth(500);
	String[] colIndex = headers.get(0);
	for (int i = 0; i < colHeader.length; i++) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
		jqGridColModel.setName(colIndex[i].replaceAll(" ", ""));
		//jqGridColModel.setAlign("center");
		
		if(i==0)
		{
			jqGridColModel.setWidth(100);
			//jqGridColModel.setAlign("left");
			
		}
		else 
			jqGridColModel.setWidth(470);
		
		
		jqGridTableModel.getColModel().add(jqGridColModel);
	}
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "70%%");
	tableModel.set("tableWidth", "60%%");
	return tableModel;
}
}
