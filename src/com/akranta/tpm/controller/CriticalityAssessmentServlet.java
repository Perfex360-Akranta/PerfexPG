package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

//import com.akranta.tpm.bean.CriticalityAssessment;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlCriticalityassessment;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.EntTlTrainingneedmst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.CriticalityAssessmentService;
import com.akranta.tpm.service.impl.CriticalityAssessmentServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.service.api.CriticalityServiceApi;


/**
 * Servlet implementation class CriticalityAssessmentServlet

 */

public class CriticalityAssessmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()

     */
	CriticalityAssessmentService criticalityAssessmentService;
	CriticalityServiceApi criticalityserviceapi;
	
	
    public CriticalityAssessmentServlet() {

        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {

		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		    HttpSession httpSession = request.getSession(false);
			
			criticalityAssessmentService = (CriticalityAssessmentServiceImpl) UIUtils.getServiceObject(
					request, "CriticalityAssessmentServiceImpl");
			
			criticalityAssessmentService.CriticalityAssessmentServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
			String action = UIUtils.getActionPart(request);
			//CommonFunctions(" action " + action);
			if (action.equals("criticalityassessment_input.cras")) {
				RequestDispatcher rd = request.getRequestDispatcher("/pages/CriticalityAssessmentMainGrid.jsp");
				rd.forward(request, response);
				//CommonFunctions(" response " + response);

			}
			else if(action.equals("criticalityassessment_getCol.cras"))
			{
				criticalityAssessmentgetCol(request,response);
			}
			else if(action.equals("criticalityassessment_getData.cras"))
			{
				criticalityAssessmentgetData(request,response);
			}
			else if (action.equals("criticalityassessmentMst_input.cras")) {
				String Flid=request.getParameter("Flid");
				String Date=request.getParameter("Dates");
				String DoneBy=request.getParameter("DoneBy");
				String Remarks=request.getParameter("Remarks");
				String crytype=request.getParameter("crytype");
				
				String tradekeyid= request.getParameter("tradekeyid");  
				 String parentFlid= request.getParameter("parentFlid"); 
				 String fromrpt= request.getParameter("fromrpt"); 
				 
				 
				
				  String rptflid=criticalityAssessmentService.getFlid(parentFlid);
				  
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				if(!UIUtils.isValidKeyId(DoneBy)){
					DoneBy=user.getUsrm_ccno();
				}
				
				if(UIUtils.isValidKeyId(fromrpt) && fromrpt.equals("Y"))
				{
					request.setAttribute("flid", rptflid);
					request.setAttribute("crytype", crytype);
					request.setAttribute("criaTradeid", tradekeyid);
				}
				else
				{
					request.setAttribute("flid", Flid);
				}
				
				request.setAttribute("Date", Date);
				request.setAttribute("DoneBy", DoneBy);
				CommonMessage.debugMsg("Remarks" + Remarks);
				if (!UIUtils.isValidKeyId(Remarks))
				{
					Remarks = "";
				}
				if(UIUtils.isValidKeyId(Remarks) && Remarks.equals("undefined"))
				{
					Remarks = "";
				}
				
				request.setAttribute("Remarks", Remarks);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/CriticalityAssessment.jsp");
				rd.forward(request, response);
				//CommonFunctions(" response " + response);

			}
			//start
			else if(action.equals("criticalityassessmentRpt_input.cras")){ 
				////tttttt
				 String keyid= request.getParameter("keyid");  
				 String parentFlid= request.getParameter("parentFlid");    
				 
				
				  String flid=criticalityAssessmentService.getFlid(parentFlid);
		   	      
				  CommonMessage.debugMsg("flidhhhh     "+flid + "keyid" + keyid);
				  
					request.setAttribute("flid", flid );
					
					request.setAttribute("criaTradeid", keyid );
					
				RequestDispatcher rd = request.getRequestDispatcher("/pages/CriticalityAssessment.jsp");
				
				rd.forward(request, response);
			}
			else if(action.equals("criticalReport_input.cras")){     ////tttttt
				RequestDispatcher rd = request.getRequestDispatcher("/pages/CriticalReport.jsp");
				rd.forward(request, response);
			}
			
			else if(action.equals("criticalReport_getCol.cras")){
				CommonMessage.debugMsg("5555555");
				
				  PrintWriter out = response.getWriter();
				 
				  
				  CommonFilter commonFilter = populateCommonFilter(request,"CriticalReportCommonFilter",true);
		   	       
		   	     
				    
				/*	if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	  }*/
				  
					List<String[]> criReport   = criticalityAssessmentService.getcriticalReport(commonFilter);
		   		    JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
				    jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = criReport.get(2);
					String [] colHeaderHead = criReport.get(1);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					jsonObject.set("tableWidth", "100%%");
					jsonObject.set("tableHeight", "75%%");
				    out.println(jsonObject);		
			}
		
			else if(action.equals("criticalReport_getData.cras")){
				
				{
					try
						{
						 /*String parentFlid= request.getParameter("parentFlid"); 
						  CommonMessage.debugMsg("flidhhhh     "+parentFlid);*/
						CommonFilter commonFilter = populateCommonFilter(request,"CriticalReportCommonFilter",false);
						 //String Flid=criticalityAssessmentService.getFlid(parentFlid);
						List<String[]> criReport  = criticalityAssessmentService.getcriticalReport(commonFilter);
						PrintWriter out = response.getWriter();
						JSONObject criGrid=UIUtils.convertToJqGridTableObject(criReport, request,3, 0,commonFilter.getTotalRecordCnt()+1);
						CommonMessage.debugMsg("roleeee   "+criGrid);
						out.println(criGrid);  			 	
					 	//commonFilter.setViewClick('Y');  			 	
					 	//httpSession.removeAttribute("empAttendCommonFilter");
					 	//httpSession.setAttribute("empAttendCommonFilter", commonFilter);
					
					} catch (Exception e) {
						e.printStackTrace();
					}	
			}
	}
			else if(action.equals("criticalReport_getExcel.cras")){
				 httpSession = request.getSession(false);
					CommonFilter commonFilter = populateCommonFilter(request,"CriticalReportCommonFilter",false);
					CommonMessage.debugMsg("excellll");
				    JSONObject colmodel = UIUtils.getXlColModel( request, response);
					
					
					colmodel.put("title","CriticalReport");
		            String format = ExcelUtils.getFormat(request);				
					Workbook wb = criticalityAssessmentService.getcriticalReporcExcel(colmodel,format,commonFilter);
					
					ExcelUtils.writeToResponse(response, wb, "CriticalReport", format);
				
			}
			
			//end 
			
			else if(action.equals("functionalLoc.cras"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setCompany("cmbComp");
				//fact// functLocFieldNameBean.setFactory("cmbFact");
				functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setLocnMandatory(false);
				functLocFieldNameBean.setFactMandatory(false);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(true);
				functLocFieldNameBean.setMachMandatory(false);
				String disableFuncLoc = request.getParameter("disableFuncLoc");
				
				if("true".equals(disableFuncLoc)){
					functLocFieldNameBean.setCompany("cmbComp");
					//fact// functLocFieldNameBean.setFactory("cmbFact");
					functLocFieldNameBean.setSbu("cmbSbu");
					functLocFieldNameBean.setPbu("cmbPbu");
					functLocFieldNameBean.setSection("cmbSect");
					functLocFieldNameBean.setCell("cmbCell");
					functLocFieldNameBean.setMachine("cmbMachine");
					//fact// functLocFieldNameBean.setFactDisable(true);
					functLocFieldNameBean.setSbuDisable(true);
					functLocFieldNameBean.setPbuDisable(true);
					//fact// functLocFieldNameBean.setFactDisable(true);
					functLocFieldNameBean.setSectDisable(true);
					functLocFieldNameBean.setCellDisable(true);
					functLocFieldNameBean.setMachDisable(true);
				}
				
				FormModes formModes = FormModes.create;
				
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
				
			}
			else if(action.equals("criticalityCriteriaDefinition_input.cras")){
				RequestDispatcher rd = request.getRequestDispatcher("/pages/criteriaDefinition.jsp");
				rd.forward(request, response);
			}
			else if(action.equals("criticalityCriteriaDefinition_getCol.cras")){
				String tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.Criticalassessement", "CriteriaDefinition");
				PrintWriter out = response.getWriter();
				out.print(tableModel);
			}
			else if(action.equals("criticalityCriteriaDefinition_getData.cras")){
				
			}
			
			else if(action.equals("assCritical_input.cras")){
				String Keys=request.getParameter("keys");
				CommonMessage.debugMsg("Keys:"+Keys);
				request.setAttribute("Keys", Keys);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/criticalityassessmentpopup.jsp");
				rd.forward(request, response);
			}
			
			else if (action.equals("criticalityassessmentMst_getCol.cras")) {
				String flid = request.getParameter("flid");
				String tradeid = request.getParameter("tradeid");
				String crytype = request.getParameter("crytype");
                
				List<String[]> TrainingNeed = null;
				try {
					PrintWriter out = response.getWriter();
					CommonFilter commonFilter1 = populateCommonFilter(request,"CriticalassessmentCommonFilter", true);
					List<String[]> skReqList = criticalityAssessmentService.getCriticalityfillgriddata(commonFilter1,flid,crytype);
					CommonMessage.debugMsg(skReqList);
					httpSession.setAttribute("TrainingNeed", TrainingNeed );	
					//CommonFunctions("TotalRecords  "+commonFilter1.getTotalRecordCnt());
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();	
					//jqGridTableModel.setGridEdit(true);
					GridColModel gridColModel = new GridColModel();			
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);					
					gridColModel.setHeaderNum(2);
	
					String [] colHeader = skReqList.get(1);
					String [] colHeader1 = skReqList.get(2);	
					String [] colHeaderCond = skReqList.get(0);
				
					List<String[]> headers = new ArrayList<String[]>();
					
					
					headers.add(colHeader);
					headers.add(colHeader1);
					CommonMessage.debugMsg("The Headers"+headers);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					CommonMessage.debugMsg("The Json Object"+jsonObject);
					jsonObject.set("tableWidth", "106%%");
					jsonObject.set("tableHeight", "58%%");
					
					//CommonFunctions(jsonObject);
					httpSession.removeAttribute("TrainingNeedReport");
					httpSession.setAttribute("TrainingNeedReport", jsonObject);
					out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
			} 
	
			   else if (action.equals("criticalityassessmentMst_getData.cras")) {
				//CommonFunctions("get data method");
		
				try {
					CommonFilter commonFilter = populateCommonFilter(request,"CriticalassessmentCommonFilter",false);
					String flid = request.getParameter("flid");
					String machine = request.getParameter("machine");
					String tradeid = request.getParameter("tradeid");
					String crytype = request.getParameter("crytype");
					
					httpSession.setAttribute("machine", machine);
					httpSession.setAttribute("flid", flid);
					
					commonFilter.setChkTrade(tradeid);
					//CommonFunctions("Inside getData of critical "+flid);
					List<String[]> MachineGrid = criticalityAssessmentService.getCriticalityfillgriddata(commonFilter,flid,crytype);
					//CommonFunctions("Data is enter or not" + MachineGrid.size());			
					PrintWriter out = response.getWriter();
					//CommonFunctions("get data method1");
					JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request,3, 0,commonFilter.getTotalRecordCnt()+2);
					//CommonFunctions("TotalRecords  "+commonFilter.getTotalRecordCnt());
					out.println(machinegrid);
				} catch (Exception e) {
					//CommonFunctions(e.getMessage());
				}
		
			} else if(action.equals("criticalityassessment_remarks.cras"))
			{
				     String equm = request.getParameter("equm");
					
					String flid = request.getParameter("flid");
					
					
					PrintWriter out = response.getWriter();
					
					String remarksvalue = criticalityAssessmentService.getCriticalassmntremarks(flid , equm);
					
					JSONObject successData = new JSONObject();					
					JSONObject returnData = new JSONObject();						
					
					if(UIUtils.isValidKeyId(remarksvalue))
					{
						successData.put("remarks", remarksvalue);						
						returnData.put("successData", successData);
					}
					
				
					out.print(returnData.toString());
			  
			}
			
			  else if(action.equals("criticalityassessment_critria.cras")){ String equm =
			  request.getParameter("equm"); String total = request.getParameter("total");
			  String flid = request.getParameter("flid"); String rowId =
			  request.getParameter("rowId"); String trade =
			  request.getParameter("tradeid");
			  
			  PrintWriter out = response.getWriter();
			  
			  List<String[]> Gridvalue =
			  criticalityAssessmentService.getCriticalassmntcritria(flid ,
			  equm,total,trade);
			  
			  JSONObject successData = new JSONObject(); JSONObject returnData = new
			  JSONObject();
			  
			  successData.put("critalstatus", Gridvalue.get(0)[0]);
			  successData.put("rowId", rowId); returnData.put("successData", successData);
			  
			  
			  out.print(returnData.toString()); }
			 
			//mano
			/*
			 * else if(action.equals("criticalityassessment_critria.cras")){ String equm =
			 * request.getParameter("equm"); String total = request.getParameter("total");
			 * String flid = request.getParameter("flid"); String rowId =
			 * request.getParameter("rowId"); String trade =
			 * request.getParameter("tradeid");
			 * 
			 * PrintWriter out = response.getWriter();
			 * 
			 * List<String[]> Gridvalue =
			 * criticalityAssessmentService.getCriticalassmntcritria(flid, equm, total,
			 * trade);
			 * 
			 * JSONObject successData = new JSONObject(); JSONObject returnData = new
			 * JSONObject();
			 * 
			 * // Add validation check if (Gridvalue != null && !Gridvalue.isEmpty()) {
			 * successData.put("critalstatus", Gridvalue.get(0)[4]);
			 * successData.put("rowId", rowId); returnData.put("successData", successData);
			 * } else { // Handle empty result case successData.put("critalstatus",
			 * "No data found"); successData.put("rowId", rowId);
			 * returnData.put("successData", successData); // Or return an error message //
			 * returnData.put("error", "No matching criteria found"); }
			 * 
			 * out.print(returnData.toString()); }
			 */
			 else if(action.equals("criticalityassessment_getExcel.cras")){
				    //String flid = request.getParameter("flid");
				    httpSession = request.getSession(false);			    
					CommonFilter commonFilter = populateCommonFilter(request,"CriticalAssessmentCommonFilter",false);
					String tmpFromRow = commonFilter.getFromRow();//
					commonFilter.setFromRow(null);//
					JSONObject colmodel = (JSONObject) httpSession.getAttribute("CricAssessmentReport");
					colmodel.put("title","Criticality Assessment");
					String format = ExcelUtils.getFormat(request);
					Workbook wb = criticalityAssessmentService.getCriticalassmntExcel(colmodel,format,commonFilter);
					commonFilter.setFromRow(tmpFromRow);//
					ExcelUtils.writeToResponse(response, wb, "CricAssessmentReport", format); 
			   }
			 else if(action.equals("criticalityassessmentMst_getExcel.cras")){
				    httpSession = request.getSession(false);
				    CommonFilter commonFilter = populateCommonFilter(request, "CriticalassessmentCommonFilter", false);
				    String tmpFromRow = commonFilter.getFromRow();
				    commonFilter.setFromRow(null);
				    
				    JSONObject colmodel = (JSONObject) httpSession.getAttribute("TrainingNeedReport");
				    colmodel.put("title", "Criticality Assessment");
				    
				    String format = ExcelUtils.getFormat(request);
				    
				    Workbook wb = criticalityAssessmentService.getCriticalityAssessmentMstExcel(colmodel, format, commonFilter);
				    
				    commonFilter.setFromRow(tmpFromRow);
				    
				    ExcelUtils.writeToResponse(response, wb, "CriticalityAssessmentReport", format);
				    
				}
			   else if(action.equals("criticalReport_getExcel.cras")){
					 httpSession = request.getSession(false);
						CommonFilter commonFilter = populateCommonFilter(request,"CriticalReportCommonFilter",false);
						CommonMessage.debugMsg("excellll");
						String tmpFromRow = commonFilter.getFromRow();//
						commonFilter.setFromRow(null);//
						JSONObject colmodel = UIUtils.getXlColModel( request, response);
						colmodel.put("title","CriticalReport");
			            String format = ExcelUtils.getFormat(request);				
						Workbook wb = criticalityAssessmentService.getcriticalReporcExcel(colmodel,format,commonFilter);
						commonFilter.setFromRow(tmpFromRow);//
						ExcelUtils.writeToResponse(response, wb, "CriticalReport", format);
					
				}
			else if( action.equals("criticalityassessmentMst_save.cras") ){				
				saveCritical(request,response);         		
			}
			else if(action.equals("criticalityassessment_delete.cras"))
			{
				deleteCritical(request,response);
			}
			else if(action.equals("criticalityassessmentMst_Remove.cras")){
				deleteCriteriaList(request,response);
			}
			else if(action.equals("criticalityassessmentMst_delete.cras")){
				DeleteCriteriaMst(request,response);
			}
			
		}

	private void criticalityAssessmentgetCol(HttpServletRequest request,HttpServletResponse response) throws Exception {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"CriticalAssessmentCommonFilter", true);
			//CommonFunctions("getColEntry:::::: ");
			
			commonFilter.setIsGetCol("Y");
			List<String[]> crAssList = criticalityAssessmentService.getCriAssMainGrid(commonFilter);
			JSONObject criAssData= UIUtils.convertToJqGridTableObject(crAssList,request, 1, 0, commonFilter.getTotalRecordCnt());
	         
			httpSession.setAttribute("CricAssessmentReport", criAssData);	
            JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);	
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("FUNCTIONALLOCATION");
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = crAssList.get(1);			
			String [] colHeaderCond = crAssList.get(0);
		
			
			List<String[]> headers = new ArrayList<String[]>();
			
			//headers.add(colHeaderCond);
			headers.add(colHeader);		
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "77%%");
			jsonObject.put("data", criAssData);		
			//CommonFunctions(jsonObject);
			httpSession.removeAttribute("CricAssessmentReport");
			httpSession.setAttribute("CricAssessmentReport", jsonObject);
			out.println(jsonObject);
		
	}
	private void criticalityAssessmentgetData(HttpServletRequest request,HttpServletResponse response) {
			try{
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				CommonFilter commonFilter  = populateCommonFilter(request,"CriticalAssessmentCommonFilter",false);
				commonFilter.setIsGetCol("N");
				List<String[]> crAssList  = criticalityAssessmentService.getCriAssMainGrid(commonFilter);
				//CommonFunctions("crAssList  ::: "+crAssList.size());
			
			 	JSONObject criAssData = UIUtils.convertToJqGridTableObject(crAssList, request,2, 0, commonFilter.getTotalRecordCnt()+3);	
			 	out.println(criAssData);
				httpSession.removeAttribute("CriticalAssessmentCommonFilter");
				httpSession.setAttribute("CriticalAssessmentCommonFilter", commonFilter);
	
		    }catch(Exception e){
				//CommonFunctions("getdata Exception :"+e.getMessage());
				e.printStackTrace();
			}
				
	 }
	private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
			
		  HttpSession httpSession = request.getSession(false);
		  		
		  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		  		if( commonFilter != null && ! createNew ){
		  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
		  			FilterValues.setPaginationParams(request,commonFilter);
		  		}	
		  		else{
		  			commonFilter =  new CommonFilter();
		  			
		  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
		  			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
		  			commonFilter.setViewClick('Y');
		  			httpSession.removeAttribute(beanIdentifier);
		  			httpSession.setAttribute(beanIdentifier, commonFilter);
		  		}
		  		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
		  		
		  		//CommonFunctions("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		  		return commonFilter;
		  	}

	private void deleteCritical(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession(false);  
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String keyid = request.getParameter("keyid");
		
		try{
			if(httpSession !=null && user !=null)
			{BdmTlCriticalityassessment oldBdmTlCriticalityassessment=new BdmTlCriticalityassessment();
			oldBdmTlCriticalityassessment=(BdmTlCriticalityassessment)UIUtils.setBeanProperties((Object)oldBdmTlCriticalityassessment,request);
			if(UIUtils.isValidKeyId(keyid)){
				oldBdmTlCriticalityassessment=criticalityAssessmentService.deleteCritical(oldBdmTlCriticalityassessment);
				String msgPropertyIdnt = "success-delete";
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				//CommonFunctions(err.toString());
				out.print(err.toString());
			   }
			}
			
			  ////CommonFunctions("Delete End");
			
		}catch(Exception e)
		{
			
		}
	}
	
private void saveCritical(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		HttpSession httpSession = request.getSession(false);
    	PrintWriter out = response.getWriter();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	try{    		
    		if( httpSession != null && user != null){
	    		List<BdmTlCriticalityassessment> existBdmTlCriticalityassessments = (List<BdmTlCriticalityassessment>)httpSession.getAttribute("bdmTlCriticalityassessment");
	    		BdmTlCriticalityassessment neweEntTlTrainingneedmst= new BdmTlCriticalityassessment();	
	    		BdmTlCriticalityassessment neweEntTlTrainingneedmstA= new BdmTlCriticalityassessment();	
	    		neweEntTlTrainingneedmstA=(BdmTlCriticalityassessment)UIUtils.setBeanProperties((Object)neweEntTlTrainingneedmstA,request);	
	    	    neweEntTlTrainingneedmst.setCasmCreatedby(user.getUsrm_ccno());	
	    		
	    		String gridData =  request.getParameter("grdCriticallityData");
	    		//CommonFunctions("gridData  "+gridData);
	    		String machineid =  (String)httpSession.getAttribute("machine");
	    		CommonMessage.debugMsg("mmachine"+machineid);
	    		String flidid =  (String)httpSession.getAttribute("flid");
	    		
	    		List<BdmTlCriticalityassessment> CrtieriaGridList1 = null;	
				JSONArray criticalJson = null;
	    		
			if(UIUtils.isValidKeyId(gridData)){
				
					criticalJson = JSONArray.fromString(gridData);
					CrtieriaGridList1=(List<BdmTlCriticalityassessment>)UIUtils.convertJSONArrToList( neweEntTlTrainingneedmst, criticalJson);	    		  
					//CommonFunctions("newBdmTlCriticalityassessment"+CrtieriaGridList1);				
					for(int i=0;i<CrtieriaGridList1.size();i++){
						CrtieriaGridList1.get(i).setCasmCreatedby(user.getUsrm_ccno());
						CrtieriaGridList1.get(i).setCasmDate(neweEntTlTrainingneedmstA.getCasmDate());
						CrtieriaGridList1.get(i).setCasmFlid(neweEntTlTrainingneedmstA.getCasmFlid());
						CrtieriaGridList1.get(i).setCasmRemarks(neweEntTlTrainingneedmstA.getCasmRemarks());
						CrtieriaGridList1.get(i).setCasmDoneby(neweEntTlTrainingneedmstA.getCasmDoneby());
						CrtieriaGridList1.get(i).setCasmTradeid(neweEntTlTrainingneedmstA.getCasmTradeid());
						//CommonFunctions("DONTEBY  "+neweEntTlTrainingneedmstA.getCasmDoneby());		
					}
				
				
			}    
			String saveMsg = null ;
		
			if(!UIUtils.isValidKeyId(CrtieriaGridList1.get(0).getCasmKeyid())){
			     saveMsg = "Data Saved Successfully";
		     }
			else 
			{
				 saveMsg = "Data Updated Successfully";
			}
			existBdmTlCriticalityassessments= criticalityAssessmentService.create( CrtieriaGridList1);
			
			
			JSONObject returnData = new JSONObject();				
			JSONObject successData = new JSONObject();	
			successData.put("msg", saveMsg);
			returnData.put("formClear",false);	
			returnData.put("successData",successData);
			out.print(returnData.toString());	
			//CommonFunctions("create end");
			}
		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		
	}
	private void deleteCriteriaList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
	    try{
    	   if( httpSession != null && user != null){
		       String CriteriaDeleteList = request.getParameter("DeleteCriteriaList");
		       //CommonFunctions("CriteriaDeleteList :::::: "+CriteriaDeleteList);
		       CriteriaDeleteList= criticalityAssessmentService.DeleteCriteriaList(CriteriaDeleteList);
		       
		       String msgPropertyIdnt = "success-delete";
				//CommonFunctions("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
			
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				JSONObject returnData = new JSONObject();						
				
				successData.put("errMsg", false);
				returnData.put("successData", successData);
				returnData.put("formClear", true);
				returnData.put("displyMsg",true);
				//CommonFunctions("successData"+successData);		
				out.print(returnData.toString());
    	   }
	    }
	    catch (Exception e) {
			
		}
	}
	
	private void DeleteCriteriaMst(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
	    try{
    	   if( httpSession != null && user != null){
		       String CriteriaDeleteFlidList = request.getParameter("DeleteCriteriaFlidList");
		       //CommonFunctions("CriteriaDeleteList :::::: "+CriteriaDeleteFlidList);
		       CriteriaDeleteFlidList= criticalityAssessmentService.DeleteFlidListMst(CriteriaDeleteFlidList);
		       
		       String msgPropertyIdnt = "success-delete";
				//CommonFunctions("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
			
				JSONObject successData = new JSONObject();
				successData.put("CriteriaId", CriteriaDeleteFlidList.length());
				successData.put("mesg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				JSONObject returnData = new JSONObject();						
				
				successData.put("errMsg", false);
				returnData.put("successData", successData);
				returnData.put("formClear", true);
				returnData.put("displyMsg",true);
				//CommonFunctions("successData"+successData);		
				out.print(returnData.toString());
    	   }
	    }
	    catch (Exception e) {
			
		}
		
	}

	private JSONObject getTableModel(List<String[]> headers) {

			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			String[] row = headers.get(1);
			String[] colHeader = headers.get(0);
			String[] colHeader1 = headers.get(1);
			String[] tempCol = new String[row.length];
			jqGridTableModel.getRowHeaders().add(tempCol);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.getRowHeaders().add(colHeader1);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableHeight(10000);
			jqGridTableModel.setTableWidth(800);
			for (int i = 0; i < colHeader.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				//jqGridColModel.setIndex(tempCol[i] = "");
				tempCol[i] = "";
				//jqGridColModel.setIndex(tempCol[i].replaceAll(" ", ""));
				//jqGridColModel.setName(tempCol[i].replaceAll(" ", ""));
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				//jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
				//jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("right");
				jqGridColModel.setEditable(false);
				
				if(i==0||i==1||i==2){
					jqGridColModel.setHidden(true);
				}
					
			if (i == 3) {//jqGridColModel.setHidden(true);
				///jqGridColModel.setFormatter("formatterTxtJHLevel");
				jqGridColModel.setWidth(75);
				jqGridColModel.setAlign("right");
			}
				if (i ==4) {		
					//jqGridColModel.setFormatter("formatterTxtJHLevel");
					jqGridColModel.setWidth(200);
					jqGridColModel.setAlign("left");
				}
				if (i == 5) {			
					jqGridColModel.setFormatter("formatterTxtJHLevel");
					jqGridColModel.setWidth(105);
					jqGridColModel.setAlign("left");
				}
				if (i>5) {
					jqGridColModel.setFormatter("TxtJHLevel");
					jqGridColModel.setWidth(70);
					jqGridColModel.setAlign("right");
				}
				
				if (i==colHeader.length-1) {
					jqGridColModel.setFormatter("TxtJHLevel");
					jqGridColModel.setWidth(68);
					jqGridColModel.setAlign("right");
				}
				
				
				
				jqGridTableModel.getColModel().add(jqGridColModel);
				
				//CommonFunctions("colHeader["+i+"]"+colHeader[i]);
				
			}
		      
			
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "70%%");
			tableModel.set("tableWidth", "110.5%%");
			return tableModel;
		}
	
}
