
/* Mod:N.Arun
 * Dt:29.2.12
 * Modified By:Dhanalakshmi.R
 * Date:23.11.12--For No Plan Entry
 * */
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
	import net.sf.json.JSONArray;
	import net.sf.json.JSONObject;
	import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
	import com.akranta.tpm.utils.ExcelUtils;
	import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
	import com.akranta.tpm.bean.GridParams;
	import com.akranta.tpm.bean.JqGridColModel;
	import com.akranta.tpm.bean.JqGridTableModel;
	import com.akranta.tpm.bean.NoPlanSaveBean;
	import com.akranta.tpm.dao.impl.Constants;
	import com.akranta.tpm.model.AdmTlUsermst;
	import com.akranta.tpm.model.CommonFilter;
	import com.akranta.tpm.service.PcsComplianceRptService;
	import com.akranta.tpm.service.impl.PcsComplianceRptServiceImpl;
	
	public class PcsComplianceRptServlet extends HttpServlet
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		CommonFilter commonFilter;
		PcsComplianceRptService pcsComplianceRptService;
		
		public PcsComplianceRptServlet() throws Exception{
			super();
		
			//pcsComplianceRptService = new PcsComplianceRptServiceImpl();
		}

		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
		{ 
			try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
		{
			try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@SuppressWarnings("unchecked")
		private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			HttpSession httpSession = request.getSession(false);
			String action = UIUtils.getActionPart(request);
			CommonMessage.debugMsg("action: "+ action); 
			try {
				pcsComplianceRptService = (PcsComplianceRptServiceImpl)UIUtils.getServiceObject(request,"PcsComplianceRptServiceImpl");
			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
			}
			if( action.equals("filterXmlPcsComplianceRpt_input.pcscomp")||(action.equals("filterXmlPcsnoPlanEntryRpt_input.pcscomp"))){
				response.setContentType("xml"); 
				CommonMessage.debugMsg("action "+ action); 
				String fromPcsornoPlanEntry="";
				if(action.equals("filterXmlPcsnoPlanEntryRpt_input.pcscomp"))
					fromPcsornoPlanEntry="noPlanEntry";
				else
					fromPcsornoPlanEntry="pcsCompliance";
				CommonMessage.debugMsg("fromPcsornoPlanEntry:"+fromPcsornoPlanEntry);
				if(fromPcsornoPlanEntry.equals("pcsCompliance"))
					UIUtils.forwardRequest(request, response, "/tiles/xml/PcsCompliance.xml") ;
				else if(fromPcsornoPlanEntry.equals("noPlanEntry"))
					UIUtils.forwardRequest(request, response, "/tiles/xml/NoPlanEntry.xml") ;	
			 }
			
			else if(action.equals("PcsComplianceRpt_input.pcscomp")||action.equals("PcsnoPlanEntryRpt_input.pcscomp")) 
			{
				String fromPcsornoPlanEntry="";
				if(action.equals("PcsnoPlanEntryRpt_input.pcscomp"))
					fromPcsornoPlanEntry="noPlanEntry";
				else
					fromPcsornoPlanEntry="pcsCompliance";
				request.setAttribute("fromPcsornoPlanEntry", fromPcsornoPlanEntry);
				httpSession.removeAttribute("fromPcsornoPlanEntry");
				httpSession.setAttribute("fromPcsornoPlanEntry", fromPcsornoPlanEntry);
				request.setAttribute("pcstick", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","pcstick"));
				String filterString = request.getParameter("actiopart");
				String frmPcsRpt = request.getParameter("frmPcsRpt");
				String fromBackPcs = null;
				if(!UIUtils.isValidKeyId(filterString))
				{
					filterString = request.getParameter("filterString");
					fromBackPcs = "true";
				}
				else
				{
					 fromBackPcs = "false";
				}
				String entryDate = request.getParameter("date");
				String shift = request.getParameter("shift");
				String cellId = request.getParameter("cellId");
				if(!UIUtils.isValidKeyId(cellId)){
					cellId = request.getParameter("cmbCellid");
				}
				String factId = request.getParameter("factId");
				if(!UIUtils.isValidKeyId(factId)){
					factId = request.getParameter("cmbFactid");
				}
				String sectId = request.getParameter("sectId");
				if(!UIUtils.isValidKeyId(sectId)){
					sectId = request.getParameter("cmbSectid");
				}
				String toDate = request.getParameter("toDate");
				if(!UIUtils.isValidKeyId(toDate)){
					toDate = request.getParameter("dtToDate");
				}
				String fromDate = request.getParameter("fromDate");
				if(!UIUtils.isValidKeyId(fromDate)){
					fromDate = request.getParameter("dtFromDate");
				}
				CommonMessage.debugMsg(toDate +" :: 111111111 ::: "+ filterString );
				CommonMessage.debugMsg(factId +" :: 111111111 ::: "+ sectId );
				request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
				RequestDispatcher rd =null;
				if(fromPcsornoPlanEntry.equals("pcsCompliance"))
					rd = request.getRequestDispatcher("pages/Reports/PcsComplianceRpt.jsp"); 
				else if(fromPcsornoPlanEntry.equals("noPlanEntry"))
					rd = request.getRequestDispatcher("pages/Reports/PcsCompliance_NoPlan.jsp"); 	
				request.setAttribute("filterStr", filterString);
				request.setAttribute("hdnfactId", factId);
				request.setAttribute("hdnshftId", shift);
				request.setAttribute("hdncellId", cellId);
				request.setAttribute("hdnsectId", sectId);
				request.setAttribute("hdndateId", entryDate);
				request.setAttribute("hdnfromdate", fromDate);
				request.setAttribute("hdntodate", toDate);
				request.setAttribute("fromBackPcs",fromBackPcs);
				String fromdate = CommonFunctions.getDate().substring(3,11);
				String todate = CommonFunctions.getDate().substring(3,11);
				request.setAttribute("fromdate", fromdate);
				request.setAttribute("todate", todate);
				request.setAttribute("frmPcsRpt", frmPcsRpt);
				rd.forward(request, response); 
				
			}
			else if(action.equals("PcsComplianceRpt_getCol.pcscomp")||action.equals("PcsnoPlanEntryRpt_getCol.pcscomp"))
			{
				 PrintWriter out = response.getWriter();
				 String fromPcsornoPlanEntry="";
				 if(action.equals("PcsnoPlanEntryRpt_getCol.pcscomp"))
					 fromPcsornoPlanEntry="noPlanEntry";
				 else
					 fromPcsornoPlanEntry="pcsCompliance";
				 CommonMessage.debugMsg("fromPcsornoPlanEntry:"+fromPcsornoPlanEntry);
				 String firstClick =request.getParameter("firsftClick");
				 CommonFilter  commonFilter  ;
				 commonFilter = populateCommonFilter(request,"pcsComplianceFilter",true);
				 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
				 {
					 CommonMessage.debugMsg("inside filter check");
					 commonFilter =(CommonFilter) httpSession.getAttribute("pcsComplianceFilter");
				 }	
				 if(commonFilter==null)
					 commonFilter = new CommonFilter(); 
				 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				 else
				 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) )
				 {
					  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
						 
				 }
				httpSession.removeAttribute("pcsComplianceFilter");
				httpSession.setAttribute("pcsComplianceFilter",commonFilter);
				response.setContentType("text/html");
				List<String[]> pcsList  = pcsComplianceRptService.getAllPcsRpt(commonFilter,fromPcsornoPlanEntry);
				httpSession.removeAttribute("pcsList");
				httpSession.setAttribute("pcsList", pcsList);
				JSONObject jsonObject=null;
				if(fromPcsornoPlanEntry.equals("pcsCompliance"))
				{
					jsonObject = getTableModel(pcsList);
					CommonMessage.debugMsg("pcsList1:"+pcsList);
					jsonObject.set("rowNumbers", true);
					jsonObject.set("tableHeight", "75%%");
					jsonObject.set("tableWidth", "110%%");
					
				}
				else if(fromPcsornoPlanEntry.equals("noPlanEntry"))
				{	
					if(pcsList!=null)
					{jsonObject=getTableModelNoPlan(pcsList);
					jsonObject.set("rowNumbers", true);
					jsonObject.set("tableHeight","65%%");
					jsonObject.set("tableWidth", "107%%");}
					CommonMessage.debugMsg("pcsList1:"+pcsList);
				}
				httpSession.removeAttribute("pcscompReportColModel");
			    httpSession.setAttribute("pcscompReportColModel", jsonObject);
				out.println(jsonObject);
			}
			else if( action.equals("PcsComplianceRpt_getData.pcscomp")||action.equals("PcsnoPlanEntryRpt_getData.pcscomp") )
			{
				try
				{
					 String fromPcsornoPlanEntry="";
					 List<String[]> pcsComplianceList =new ArrayList<String[]>();
					 if(action.equals("PcsnoPlanEntryRpt_getData.pcscomp"))
						 fromPcsornoPlanEntry="noPlanEntry";
					 else
						 fromPcsornoPlanEntry="pcsCompliance";
					 CommonMessage.debugMsg("fromPcsornoPlanEntry:"+fromPcsornoPlanEntry);
					 PrintWriter out = response.getWriter();
	  				 CommonFilter commonFilter = populateCommonFilter(request,"pcsComplianceFilter",false);
	  				 GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
	  				 httpSession.setAttribute("gridParams", gridParams);
	  				 if (gridParams == null)
	  				 {	
	  					 gridParams = new GridParams();
	  					 pcsComplianceList=(List<String[]>)httpSession.getAttribute("pcsList");
	  				 }
	  				 else
	  					pcsComplianceList  = pcsComplianceRptService.getAllPcsRpt(commonFilter,fromPcsornoPlanEntry);
	  				 FilterValues.populateGridParams(request, gridParams);
	  				 httpSession.removeAttribute("gridParams");
	  				 JSONObject listToJsonObject = new JSONObject();
					 CommonMessage.debugMsg(" ccc "+commonFilter.getTotalRecordCnt());
					 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsComplianceList,request,0,0,commonFilter.getTotalRecordCnt());
					 out.println(listToJsonObject);
					 out.close();
	  			    
			    }
			   catch(Exception e)
			   {
					CommonMessage.debugMsg(e.getMessage());
			   }
			}
			else if( action.equals("getShiftKeyid.pcscomp"))
			{
				try {					
						PrintWriter out = response.getWriter();				
						String factId = request.getParameter("factId");
						String shiftCode = request.getParameter("shiftCode");
						CommonMessage.debugMsg("factId "+ factId+"shiftCode "+shiftCode);
						String shiftId = pcsComplianceRptService.getShiftKeyid(factId, shiftCode);
						JSONObject shiftData =new JSONObject();
						if (UIUtils.isValidKeyId(shiftId))
							shiftData.put("shift", shiftId);
						else
							shiftData.put("shift", "");
						out.print(shiftData);
					} 
				catch (Exception e) 
				{
					 e.printStackTrace();
				}				
			}
			else if(action.equals("noPlan_input.pcscomp"))
			{
				String factId = request.getParameter("factId");
				String shiftCode = request.getParameter("shiftCode");
				CommonMessage.debugMsg("factId "+ factId+"shiftCode "+shiftCode);
				String shift = pcsComplianceRptService.getShiftKeyid(factId, shiftCode);
				String date=request.getParameter("date");
				String mchId=request.getParameter("mchId");
				String cellId=request.getParameter("cellId");
				String sectId=request.getParameter("secId");
				String partialNoplanSatus=request.getParameter("partialNoplanSatus");
				String duration="";
				String fromTime="";
				String toTime="";
				String fromDate="";
				String toDate="";
				String ShiftstrtTime="";
				String ShiftstrtTimeS="";
				String ShiftEndTime="";
				String ShiftEndTimeS="";
				List<String[]> Duration=pcsComplianceRptService.getNoPlanDuration(mchId,date,shift);
				CommonMessage.debugMsg("Duration:size:"+Duration.size());
				if(Duration.size()==1)
				{ 
					duration=Duration.get(0)[2];
					fromTime=Duration.get(0)[0];
					toTime=Duration.get(0)[1];
					fromTime=fromTime.substring(12,17);
					fromDate=Duration.get(0)[0].substring(0,11);
					CommonMessage.debugMsg("fromDate..."+fromDate+"..."+Duration.get(0)[1]);
					toTime=Duration.get(0)[1].substring(12,17);
					toDate=Duration.get(0)[1].substring(0,11);
					ShiftstrtTimeS=Duration.get(0)[0];
					ShiftstrtTime=ShiftstrtTimeS.replaceAll("\\s","");
					CommonMessage.debugMsg("ShiftstrtTime;"+ShiftstrtTime);
					ShiftEndTimeS=Duration.get(0)[1];
					ShiftEndTime=ShiftEndTimeS.replaceAll("\\s", "");
				}
				else if(Duration.size()==2)
				{
					duration=Duration.get(1)[2];
					fromTime=Duration.get(1)[0];
					toTime=Duration.get(1)[1];
					fromTime=fromTime.substring(12,17);
					fromDate=Duration.get(0)[0].substring(0,11);
					CommonMessage.debugMsg("fromDate..."+fromDate+"..."+Duration.get(0)[1]);
					toTime=toTime.substring(12,17);
					toDate=Duration.get(0)[1].substring(0,11);
					ShiftstrtTimeS=Duration.get(0)[0];
					ShiftstrtTime=ShiftstrtTimeS.replaceAll("\\s","");
					ShiftEndTimeS=Duration.get(0)[1];
					ShiftEndTime=ShiftEndTimeS.replaceAll("\\s", "");
				}
				request.setAttribute("fromTime",fromTime);
				request.setAttribute("toTime",toTime);
				request.setAttribute("fromDate", fromDate);
				request.setAttribute("toDate", toDate);
				request.setAttribute("mchId", mchId);
				request.setAttribute("shift", shiftCode);
				request.setAttribute("date",date);
				request.setAttribute("duration",duration);
				request.setAttribute("ShiftstrtTime",ShiftstrtTime);
				request.setAttribute("ShiftEndTime",ShiftEndTime);
				request.setAttribute("ShiftstrtTimeS",ShiftstrtTimeS);
				request.setAttribute("ShiftEndTimeS",ShiftEndTimeS);
				request.setAttribute("partialNoplanSatus", partialNoplanSatus);
				UIUtils.forwardRequest(request, response, "pages/pcs/NoPlanSavePopup.jsp");
				
			}
			else if( action.equals("PcsComplianceRpt_getExcel.pcscomp")||action.equals("PcsnoPlanEntryRpt_getExcel.pcscomp")){
				
				CommonFilter commonFilter = populateCommonFilter(request,"pcsComplianceFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				String date   = null;
				if("Y".equals(commonFilter.getMonwise()))
					date   = fromMonth +"  -  "+ toMonth ;
				else
					date   = fromdate +"  -  "+ todate ;
				String fromPcsornoPlanEntry="";
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("pcscompReportColModel");
				if(action.equals("PcsnoPlanEntryRpt_getExcel.pcscomp"))
					fromPcsornoPlanEntry="noPlanEntry";
				else
					fromPcsornoPlanEntry="pcsCompliance";
				String title="";
				if(fromPcsornoPlanEntry.equals("pcsCompliance"))
				{
					title="PcsComplianceReport";
					tblJSONObj.put("title", "PCS Compliance Report" +" - "+date);
				}
				else if(fromPcsornoPlanEntry.equals("noPlanEntry"))
				{
					title="NoPlanEntryReport";
					tblJSONObj.put("title", "No Plan Report" +" - "+date);
				}
				
				String format = ExcelUtils.getFormat(request);
				Workbook wb = pcsComplianceRptService.pcscompExportExcel(commonFilter,tblJSONObj,format,fromPcsornoPlanEntry);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, title, format);
				
			}
			else if(action.equals("PcsnoPlanEntryRpt_save.pcscomp"))
			{
				CommonMessage.debugMsg("no plan save action");
				SaveNoPlan(request,response);
			}
		}
		@SuppressWarnings("unchecked")
		private void SaveNoPlan(HttpServletRequest request,HttpServletResponse response) throws IOException
		{   
			CommonMessage.debugMsg("save no plan");
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			UIUtils.displayRequestParamsValue(request);
			if (httpSession != null && user != null) 
			{
				try 
				{
					NoPlanSaveBean noPlanSaveBean =new NoPlanSaveBean();
					String noPlanSave = request.getParameter("noPlanSave");
					List<NoPlanSaveBean> noPlanSaveList = null;
					JSONArray noPlanSaveJson = null;
					if (UIUtils.isValidKeyId(noPlanSave)) 
					{
						noPlanSaveJson = JSONArray.fromString(noPlanSave);
						noPlanSaveList = (List<NoPlanSaveBean>) UIUtils.convertJSONArrToList(noPlanSaveBean,noPlanSaveJson);			
					}
					pcsComplianceRptService.SaveNoPlan(noPlanSaveList,user.getUsrm_ccno());
					String msgPropertyIdnt;
					msgPropertyIdnt = "success-save";
					JSONObject successData = new JSONObject();
					successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					JSONObject result = new JSONObject();
					result.put("formClear", false);
					result.put("displyMsg", true);
					result.put("successData", successData);
					out.print(result.toString());
					out.close();
				}
				catch (Exception e) 
				{
					CommonMessage.debugMsg("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
			}		
		}

		private JSONObject getTableModel(List<String[]> headers)
		{
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			
			String [] colHeader2 = headers.get(0);
			String [] colHeader = headers.get(1);
			String[] emptyrow = new String[colHeader.length]; 
			emptyrow [0] ="";
			emptyrow [1] ="";
			emptyrow [2] ="";		
			emptyrow [3] ="";
			emptyrow [4] ="Date";
			emptyrow [5] ="Date";
			emptyrow [6] ="Date";
			//emptyrow [7] ="";
			
			
			colHeader2 [3] ="Date";
			colHeader2 [4] ="Date";
			colHeader2 [5] ="Date";
			//colHeader2 [6] ="Date";
			jqGridTableModel.getRowHeaders().add(emptyrow);
			jqGridTableModel.getRowHeaders().add(colHeader2);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setTableButton(true);
		 	jqGridTableModel.setGroupSummary(true);			
			
		 	JqGridColModel jqGridColModel = getColModel("KEYFIELD", 50,"left",true,false,false);
		 	jqGridTableModel.getColModel().add(getColModel("KEYFIELD", 50,"left",true,false,false));
			jqGridTableModel.getColModel().add(getColModel("SUBGROUPID", 50,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("code", 350,"left",false,false,false));//
			jqGridTableModel.getColModel().add(getColModel("costcenter", 100,"left",false,false,false));
			jqGridTableModel.getColModel().add(getColModel("heat", 450,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("treat", 350,"left",true,false,false));//
			jqGridColModel.setSummaryTpl("<b><font >Total</font> </b>");
			jqGridTableModel.getColModel().add( jqGridColModel);
			String colIndex ="";
			colHeader = headers.get(0);
			for(int i =7; i < colHeader.length; i++)
			{
				emptyrow [i] ="";

				colIndex = colHeader[i].replaceAll(" ", "").replace("-", "")+i ;
				
				jqGridTableModel.getColModel().add(getColModel( colIndex,25,"center", false,true,false));
			}
			
			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 return tableModel;
		}
		private JSONObject getTableModelNoPlan(List<String[]> headers)
		{
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String [] colHeader2 = headers.get(0);
			String [] colHeader = headers.get(1);
			String[] emptyrow = new String[colHeader.length]; 
			emptyrow [7] ="";
			emptyrow [6] ="";
			emptyrow [9] ="";
			emptyrow [8] ="";		
			emptyrow[5]="";	
			emptyrow[4]="";	
			colHeader2[6]="Date";
			colHeader2[5]="";
			colHeader2[4]="";
			jqGridTableModel.getRowHeaders().add(emptyrow);
			jqGridTableModel.getRowHeaders().add(colHeader2);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setGroupSummary(true);			
			JqGridColModel jqGridColModel =getColModel("eqpno", 85,"left",false,false,false);
			jqGridTableModel.getColModel().add(getColModel("fact", 50,"left",true,false,false));		
			jqGridTableModel.getColModel().add(getColModel("sect", 50,"left",true,false,false));		
			jqGridTableModel.getColModel().add(getColModel("cell", 50,"left",true,false,false));
			jqGridTableModel.getColModel().add(getColModel("machine",50,"left",true,false,false));
		 	jqGridTableModel.getColModel().add(getColModel("mainid",50,"left",true,false,false));
		 	jqGridTableModel.getColModel().add(getColModel("eqpname",250,"left",false,false,false));
		 	jqGridTableModel.getColModel().add( jqGridColModel);
			String colIndex ="";
			colHeader = headers.get(0);
			for(int i =7; i < colHeader.length; i++)
			{
				emptyrow [i] ="";
				colIndex = colHeader[i].replaceAll(" ", "").replace("-", "")+(i+1) ;
				JqGridColModel jqGridColModel1=getColModel( colIndex,25,"center", false,true,false);
				jqGridTableModel.getColModel().add(jqGridColModel1);
				
			}
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 return tableModel;
		}
		private JqGridColModel getColModel (String colIndex, int width,String allign,boolean hidden,boolean groupbyfield,boolean key)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex);
			jqGridColModel.setName(colIndex);
			jqGridColModel.setWidth( width);				
			jqGridColModel.setAlign(allign);
			jqGridColModel.setEditable(false);
			jqGridColModel.setHidden(hidden);
			jqGridColModel.setKey(key);
			return jqGridColModel;
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
				commonFilter = 	FilterValues.getPCS(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
		
	}