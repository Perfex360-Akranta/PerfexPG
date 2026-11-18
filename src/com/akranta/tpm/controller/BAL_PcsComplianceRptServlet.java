
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
	import com.akranta.tpm.utils.CommonFunctions;
	import com.akranta.tpm.utils.ExcelUtils;
	import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
	import com.akranta.tpm.bean.GridParams;
	import com.akranta.tpm.bean.JqGridColModel;
	import com.akranta.tpm.bean.JqGridTableModel;
	import com.akranta.tpm.bean.NoPlanSaveBean;
	import com.akranta.tpm.dao.impl.Constants;
	import com.akranta.tpm.model.AdmTlUsermst;
	import com.akranta.tpm.model.CommonFilter;
	import com.akranta.tpm.service.BAL_PcsComplianceRptService;
	import com.akranta.tpm.service.impl.BAL_PcsComplianceRptServiceImpl;
	
	public class BAL_PcsComplianceRptServlet extends HttpServlet
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		CommonFilter commonFilter;
		BAL_PcsComplianceRptService pcsComplianceRptService;
		
		public BAL_PcsComplianceRptServlet() throws Exception{
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
			System.out.println("action: "+ action); 
			try {
				pcsComplianceRptService = (BAL_PcsComplianceRptServiceImpl)UIUtils.getServiceObject(request,"BAL_PcsComplianceRptServiceImpl");
				HttpSession h = request.getSession(false);

				pcsComplianceRptService.BAL_PcsComplianceRptServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
			} catch (ServiceObjectCreationException e) {
				CommonFunctions.debugMsg(e);
			}
			if( action.equals("filterXmlPcsComplianceRpt_input.balpcscomp")||action.equals("filterXmlPcsnoPlanEntryRpt_input.balpcscomp")
				    ||action.equals("filterXmlPcsTimeRpt_input.balpcscomp")){			
				response.setContentType("xml"); 
				System.out.println("action "+ action); 
				String fromPcsornoPlanEntry="";
				if(action.equals("filterXmlPcsnoPlanEntryRpt_input.balpcscomp"))
					fromPcsornoPlanEntry="noPlanEntry";
				else
					fromPcsornoPlanEntry="pcsCompliance";
				CommonFunctions.debugMsg("fromPcsornoPlanEntry:"+fromPcsornoPlanEntry);
				if(fromPcsornoPlanEntry.equals("pcsCompliance"))
					UIUtils.forwardRequest(request, response, "/tiles/xml/PcsCompliance.xml") ;
				else if(fromPcsornoPlanEntry.equals("noPlanEntry"))
					UIUtils.forwardRequest(request, response, "/tiles/xml/NoPlanEntry.xml") ;	
			 }
			
			else if(action.equals("PcsComplianceRpt_input.balpcscomp")||action.equals("PcsnoPlanEntryRpt_input.balpcscomp")) 
			{
				String fromPcsornoPlanEntry="";
				if(action.equals("PcsnoPlanEntryRpt_input.balpcscomp"))
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
				CommonFunctions.debugMsg(toDate +" :: 111111111 ::: "+ filterString );
				CommonFunctions.debugMsg(factId +" :: 111111111 ::: "+ sectId );
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
			else if(action.equals("PcsComplianceRpt_getCol.balpcscomp")||action.equals("PcsnoPlanEntryRpt_getCol.balpcscomp"))
			{
				 PrintWriter out = response.getWriter();
				 String fromPcsornoPlanEntry="";
				 if(action.equals("PcsnoPlanEntryRpt_getCol.balpcscomp"))
					 fromPcsornoPlanEntry="noPlanEntry";
				 else
					 fromPcsornoPlanEntry="pcsCompliance";
				 CommonFunctions.debugMsg("fromPcsornoPlanEntry:"+fromPcsornoPlanEntry);
				 String firstClick =request.getParameter("firsftClick");
				 CommonFilter  commonFilter  ;
				 commonFilter = populateCommonFilter(request,"pcsComplianceFilter",true);
				 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
				 {
					 CommonFunctions.debugMsg("inside filter check");
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
				System.out.println("fromPcsornoPlanEntry"+fromPcsornoPlanEntry);
				List<String[]> pcsList  = pcsComplianceRptService.getAllPcsRpt(commonFilter,fromPcsornoPlanEntry);
				//--------------gopi---------------
				if(fromPcsornoPlanEntry.equals("pcsCompliance") && pcsList != null)
				{
					pcsList = cleanSbList(pcsList);
					commonFilter.setTotalRecordCnt((long) pcsList.size());
				}
				httpSession.removeAttribute("pcsList");
				httpSession.removeAttribute("pcsList");
				httpSession.setAttribute("pcsList", pcsList);
				JSONObject jsonObject=null;
				if(fromPcsornoPlanEntry.equals("pcsCompliance"))
				{
					jsonObject = getTableModel(pcsList);
					CommonFunctions.debugMsg("pcsList1:"+pcsList);
					jsonObject.set("rowNumbers", true);
					jsonObject.set("tableHeight", "75%%");
					jsonObject.set("tableWidth", "110%%");
					httpSession.removeAttribute("pcscompReportColModel");
				    httpSession.setAttribute("pcscompReportColModel", jsonObject);
				    System.out.println("Before Response in Pcs Compliance servlet ");
					out.println(jsonObject);
					
				}
				else if(fromPcsornoPlanEntry.equals("noPlanEntry"))
				{	
					if(pcsList!=null)
					{jsonObject=getTableModelNoPlan(pcsList);
					jsonObject.set("rowNumbers", true);
					jsonObject.set("tableHeight","65%%");
					jsonObject.set("tableWidth", "107%%");}
					CommonFunctions.debugMsg("pcsList1:"+pcsList);
					httpSession.removeAttribute("pcscompReportColModel");
				    httpSession.setAttribute("pcscompReportColModel", jsonObject);
					out.println(jsonObject);
				}
				
			}
			else if( action.equals("PcsComplianceRpt_getData.balpcscomp")||action.equals("PcsnoPlanEntryRpt_getData.balpcscomp") )
			{
				try
				{
					 String fromPcsornoPlanEntry="";
					 List<String[]> pcsComplianceList =new ArrayList<String[]>();
					 if(action.equals("PcsnoPlanEntryRpt_getData.balpcscomp"))
						 fromPcsornoPlanEntry="noPlanEntry";
					 else
						 fromPcsornoPlanEntry="pcsCompliance";
					 CommonFunctions.debugMsg("fromPcsornoPlanEntry:"+fromPcsornoPlanEntry);
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
	  				 //--------gopi------------
	  				if(fromPcsornoPlanEntry.equals("pcsCompliance") && pcsComplianceList != null)
	  				{
	  					pcsComplianceList = cleanSbList(pcsComplianceList);
	  					commonFilter.setTotalRecordCnt((long) pcsComplianceList.size());
	  				}
	  				FilterValues.populateGridParams(request, gridParams);
	  				 FilterValues.populateGridParams(request, gridParams);
	  				 httpSession.removeAttribute("gridParams");
	  				 JSONObject listToJsonObject = new JSONObject();
					 CommonFunctions.debugMsg(" ccc "+commonFilter.getTotalRecordCnt());
					 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsComplianceList,request,0,0,commonFilter.getTotalRecordCnt());
					 out.println(listToJsonObject);
					 out.close();
	  			    
			    }
			   catch(Exception e)
			   {
					System.out.println(e.getMessage());
			   }
			}
			else if( action.equals("getShiftKeyid.balpcscomp"))
			{
				try {					
						PrintWriter out = response.getWriter();				
						String factId = request.getParameter("factId");
						String shiftCode = request.getParameter("shiftCode");
						CommonFunctions.debugMsg("factId "+ factId+"shiftCode "+shiftCode);
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
			else if(action.equals("noPlan_input.balpcscomp"))
			{
				String factId = request.getParameter("factId");
				String shiftCode = request.getParameter("shiftCode");
				CommonFunctions.debugMsg("factId "+ factId+"shiftCode "+shiftCode);
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
				CommonFunctions.debugMsg("Duration:size:"+Duration.size());
				if(Duration.size()==1)
				{ 
					duration=Duration.get(0)[2];
					fromTime=Duration.get(0)[0];
					toTime=Duration.get(0)[1];
					fromTime=fromTime.substring(12,17);
					fromDate=Duration.get(0)[0].substring(0,11);
					CommonFunctions.debugMsg("fromDate..."+fromDate+"..."+Duration.get(0)[1]);
					toTime=Duration.get(0)[1].substring(12,17);
					toDate=Duration.get(0)[1].substring(0,11);
					ShiftstrtTimeS=Duration.get(0)[0];
					ShiftstrtTime=ShiftstrtTimeS.replaceAll("\\s","");
					CommonFunctions.debugMsg("ShiftstrtTime;"+ShiftstrtTime);
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
					CommonFunctions.debugMsg("fromDate..."+fromDate+"..."+Duration.get(0)[1]);
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
			else if( action.equals("PcsComplianceRpt_getExcel.balpcscomp")||action.equals("PcsnoPlanEntryRpt_getExcel.balpcscomp")||action.equals("PcsTimeRpt_getExcel.balpcscomp")){
				
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
				/*String fromPcsornoPlanEntry="";
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("pcscompReportColModel");
				if(action.equals("PcsnoPlanEntryRpt_getExcel.balpcscomp"))
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
				*/
				//---Gopi---------
				String fromPcsornoPlanEntry="";
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("pcscompReportColModel");
				if(action.equals("PcsnoPlanEntryRpt_getExcel.balpcscomp"))
					fromPcsornoPlanEntry="noPlanEntry";
				else if(action.equals("PcsTimeRpt_getExcel.balpcscomp"))   // added
					fromPcsornoPlanEntry="pcsTime";                         // added
				else
					fromPcsornoPlanEntry="pcsCompliance";
				String title="";
				if(fromPcsornoPlanEntry.equals("pcsCompliance"))
				{
					title="PcsComplianceReport";
					tblJSONObj.put("title", "PCS Compliance Report" +" - "+date);
				}
				else if(fromPcsornoPlanEntry.equals("pcsTime"))             // added
				{                                                           // added
					title="PcsComplianceTimeReport";                        // added
					tblJSONObj.put("title", "PCS Compliance Time Report" +" - "+date);   // added
				}                                                           // added
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
			else if(action.equals("PcsnoPlanEntryRpt_save.balpcscomp"))
			{
				CommonFunctions.debugMsg("no plan save action");
				SaveNoPlan(request,response);
			}
		
		
		
		else if(action.equals("PcsTimeRpt_input.balpcscomp")) 
		{
			String fromPcsornoPlanEntry="";
			if(action.equals("PcsnoPlanEntryRpt_input.balpcscomp"))
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
			CommonFunctions.debugMsg(toDate +" :: 111111111 ::: "+ filterString );
			CommonFunctions.debugMsg(factId +" :: 111111111 ::: "+ sectId );
			request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			RequestDispatcher rd =null;
			rd = request.getRequestDispatcher("pages/Reports/PcsComplianceTime.jsp");			
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
			
		else if(action.equals("PcsTimeRpt_getCol.balpcscomp"))
		{
			 PrintWriter out = response.getWriter();
			 String fromPcsornoPlanEntry="";
			 if(action.equals("PcsnoPlanEntryRpt_getCol.balpcscomp"))
				 fromPcsornoPlanEntry="noPlanEntry";
			 else
				 fromPcsornoPlanEntry="pcsCompliance";
			 CommonFunctions.debugMsg("fromPcsornoPlanEntry:"+fromPcsornoPlanEntry);
			 String firstClick =request.getParameter("firsftClick");
			 CommonFilter  commonFilter  ;
			 commonFilter = populateCommonFilter(request,"pcsComplianceFilter",true);
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
			 {
				 CommonFunctions.debugMsg("inside filter check");
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
			System.out.println("fromPcsornoPlanEntry"+fromPcsornoPlanEntry);
			List<String[]> pcsList  = pcsComplianceRptService.getAllPcsTimeRpt(commonFilter,fromPcsornoPlanEntry);
			if(fromPcsornoPlanEntry.equals("pcsCompliance") && pcsList != null)
			{
				pcsList = cleanSbTimeList(pcsList);
				commonFilter.setTotalRecordCnt((long) pcsList.size());
			}
			httpSession.removeAttribute("pcsList");
			httpSession.setAttribute("pcsList", pcsList);
			JSONObject jsonObject=null;
			if(fromPcsornoPlanEntry.equals("pcsCompliance"))
			{
				jsonObject = getTableModelTime(pcsList);
				CommonFunctions.debugMsg("pcsList1:"+pcsList);
				jsonObject.set("rowNumbers", true);
				jsonObject.set("tableHeight", "75%%");
				jsonObject.set("tableWidth", "110%%");
				httpSession.removeAttribute("pcscompReportColModel");
			    httpSession.setAttribute("pcscompReportColModel", jsonObject);
			    System.out.println("Before Response in Pcs Compliance servlet ");
				out.println(jsonObject);
				
			}
			else if(fromPcsornoPlanEntry.equals("noPlanEntry"))
			{	
				if(pcsList!=null)
				{jsonObject=getTableModelNoPlan(pcsList);
				jsonObject.set("rowNumbers", true);
				jsonObject.set("tableHeight","65%%");
				jsonObject.set("tableWidth", "107%%");}
				CommonFunctions.debugMsg("pcsList1:"+pcsList);
				httpSession.removeAttribute("pcscompReportColModel");
			    httpSession.setAttribute("pcscompReportColModel", jsonObject);
				out.println(jsonObject);
			}
			
		}
		else if( action.equals("PcsTimeRpt_getData.balpcscomp") )
		{
			try
			{
				 String fromPcsornoPlanEntry="";
				 List<String[]> pcsComplianceList =new ArrayList<String[]>();
				 if(action.equals("PcsnoPlanEntryRpt_getData.pcscomp"))
					 fromPcsornoPlanEntry="noPlanEntry";
				 else
					 fromPcsornoPlanEntry="pcsCompliance";
				 CommonFunctions.debugMsg("fromPcsornoPlanEntry:"+fromPcsornoPlanEntry);
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
  					//pcsComplianceList  = pcsComplianceRptService.getAllPcsRpt(commonFilter,fromPcsornoPlanEntry);
  					pcsComplianceList  = pcsComplianceRptService.getAllPcsTimeRpt(commonFilter,fromPcsornoPlanEntry);
  				if(fromPcsornoPlanEntry.equals("pcsCompliance") && pcsComplianceList != null)
  				{
  					pcsComplianceList = cleanSbTimeList(pcsComplianceList);
  					commonFilter.setTotalRecordCnt((long) pcsComplianceList.size());
  				}
  				 FilterValues.populateGridParams(request, gridParams);
  				 httpSession.removeAttribute("gridParams");
  				 JSONObject listToJsonObject = new JSONObject();
				 CommonFunctions.debugMsg(" ccc "+commonFilter.getTotalRecordCnt());
				 listToJsonObject = UIUtils.convertToJqGridTableObject(pcsComplianceList,request,0,0,commonFilter.getTotalRecordCnt());
				 out.println(listToJsonObject);
				 out.close();
  			    
		    }
		   catch(Exception e)
		   {
				System.out.println(e.getMessage());
		   }
		}
		}
		@SuppressWarnings("unchecked")
		private void SaveNoPlan(HttpServletRequest request,HttpServletResponse response) throws IOException
		{   
			CommonFunctions.debugMsg("save no plan");
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
					CommonFunctions.debugMsg("gete. " + e.getMessage());
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
			CommonFunctions.debugMsg("emptyrow   "+emptyrow+"colHeader.length" );
			emptyrow [0] ="";
			emptyrow [1] ="";
			emptyrow [2] ="";		
			emptyrow [3] ="";
			emptyrow [4] ="";
			emptyrow [5] ="";
			emptyrow [6] ="";
			emptyrow [7] ="";
			emptyrow [8] ="";
			//emptyrow [9] ="";		

			colHeader2 [6] ="";
			colHeader2 [7] ="";
			colHeader2 [8] ="Date";
			
			jqGridTableModel.getRowHeaders().add(emptyrow);
			jqGridTableModel.getRowHeaders().add(colHeader2);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setTableButton(true);
		 	jqGridTableModel.setGroupSummary(true);			
			
		 /*	JqGridColModel jqGridColModel = getColModel("KEYFIELD", 50,"left",true,false,false);
		 	jqGridTableModel.getColModel().add(getColModel("KEYFIELD", 50,"left",true,false,false));
			jqGridTableModel.getColModel().add(getColModel("SUBGROUPID", 50,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("code", 350,"left",false,false,false));//
			jqGridTableModel.getColModel().add(getColModel("costcenter", 100,"left",false,false,false));
			jqGridTableModel.getColModel().add(getColModel("heat", 450,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("treat", 350,"left",true,false,false));//
			 UNION  SELECT 'PLANTID','PLANTNAME','FACTORYID','FACTORYNAME','SECTIONID','SECTIONNAME','CELLID','CELLNAME','COSTCENTERID' ,'1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3','1','2','3', 2 AS DATAORDER FROM DUAL ORDER BY DATAORDER
				PLANTID								
			*/
		 	
		 	JqGridColModel jqGridColModel =  getColModel("PLANTID", 50,"left",true,false,false);
		 	jqGridTableModel.getColModel().add(getColModel("PLANTNAME", 50,"left",true,false,false));
			jqGridTableModel.getColModel().add(getColModel("FACTORYID", 50,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("FACTORYNAME", 10,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("SECTIONID", 90,"center",false,false,false));
			jqGridTableModel.getColModel().add(getColModel("SECTIONNAME", 10,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("CELLID", 250,"left",false,false,false));//
			jqGridTableModel.getColModel().add(getColModel("CELLNAME", 10,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("COSTCENTER", 250,"left",false,false,false));//
			//jqGridTableModel.getColModel().add(getColModel("10", 100,"center",false,false,false));//	
			//jqGridTableModel.getColModel().add(getColModel("11", 100,"center",false,false,false));//			

		 	jqGridColModel.setSummaryTpl("<b><font >Total</font> </b>");
			jqGridTableModel.getColModel().add( jqGridColModel);
			String colIndex ="";
			colHeader = headers.get(0);
			for(int i =9; i < colHeader.length; i++)
			{
				emptyrow [i] ="";

				colIndex = colHeader[i].replaceAll(" ", "").replace("-", "")+i ;
				
				jqGridTableModel.getColModel().add(getColModel( colIndex,25,"center", false,true,false));
			}
			
			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 CommonFunctions.debugMsg(tableModel+" tableModel ");
			 return tableModel;
		}
		
		/*private JSONObject getTableModelTime(List<String[]> headers)
		{
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			
			String [] colHeader2 = headers.get(0);
			String [] colHeader = headers.get(1);
			String[] emptyrow = new String[colHeader.length]; 
			CommonFunctions.debugMsg("emptyrow   "+emptyrow+"colHeader.length" );
			emptyrow [0] ="";
			emptyrow [1] ="";
			emptyrow [2] ="";		
			emptyrow [3] ="";
			emptyrow [4] ="";
			emptyrow [5] ="";
			emptyrow [6] ="";
			//emptyrow [7] ="";
			//emptyrow [8] ="";
			//emptyrow [9] ="";		

			colHeader2 [5] ="";
			colHeader2 [6] ="";
			colHeader2 [7] ="Date";
			
			jqGridTableModel.getRowHeaders().add(emptyrow);
			jqGridTableModel.getRowHeaders().add(colHeader2);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setTableButton(true);
		 	jqGridTableModel.setGroupSummary(true);		
		
		 	
		 	JqGridColModel jqGridColModel =  getColModel("FACTORYNAME", 50,"left",false,false,false);
		 	//jqGridTableModel.getColModel().add(getColModel("PLANTNAME", 50,"left",true,false,false));
			//jqGridTableModel.getColModel().add(getColModel("FACTORYID", 50,"left",true,false,false));//
			//jqGridTableModel.getColModel().add(getColModel("FACTORYNAME", 10,"left",true,false,false));//
			//jqGridTableModel.getColModel().add(getColModel("SECTIONID", 90,"center",false,false,false));
			jqGridTableModel.getColModel().add(getColModel("SECTIONNAME", 10,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("CELLNAME", 10,"left",true,false,false));//
			jqGridTableModel.getColModel().add(getColModel("CELLID", 250,"left",false,false,false));//
			jqGridTableModel.getColModel().add(getColModel("SHIFTID", 250,"left",false,false,false));//
			jqGridTableModel.getColModel().add(getColModel("HOUR", 250,"left",false,false,false));//
			jqGridTableModel.getColModel().add(getColModel("SHIFT", 250,"left",false,false,false));//
			//jqGridTableModel.getColModel().add(getColModel("DATE", 250,"left",false,false,false));//

			//jqGridTableModel.getColModel().add(getColModel("10", 100,"center",false,false,false));//	
			//jqGridTableModel.getColModel().add(getColModel("11", 100,"center",false,false,false));//			

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
			 CommonFunctions.debugMsg(tableModel+" tableModel ");
			 return tableModel;
		}*/
		
		private JSONObject getTableModelTime(List<String[]> headers)
		{
		    JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		    
		    String [] colHeader2 = headers.get(0);
		    String [] colHeader = headers.get(1);
		    String[] emptyrow = new String[colHeader.length]; 
		    CommonFunctions.debugMsg("emptyrow   "+emptyrow+"colHeader.length" );
		    emptyrow [0] ="";
		    emptyrow [1] ="";
		    emptyrow [2] ="";		
		    emptyrow [3] ="";
		    emptyrow [4] ="";
		    emptyrow [5] ="";

		    colHeader2 [0] ="";
		    colHeader2 [1] ="";
		    colHeader2 [2] ="";
		    colHeader2 [3] ="DATE";
		    colHeader2 [4] ="";
		    colHeader2 [5] ="";
		    
		    jqGridTableModel.getRowHeaders().add(emptyrow);
		    jqGridTableModel.getRowHeaders().add(colHeader2);
		    jqGridTableModel.getRowHeaders().add(colHeader);
		    jqGridTableModel.setTableButton(true);
		    jqGridTableModel.setGroupSummary(true);		

		    jqGridTableModel.getColModel().add(getColModel("FACTORYNAME", 250,"left",false,false,false));
		    jqGridTableModel.getColModel().add(getColModel("SECTIONNAME", 10,"left",true,false,false));
		    jqGridTableModel.getColModel().add(getColModel("CELLNAME", 10,"left",true,false,false));
		    jqGridTableModel.getColModel().add(getColModel("CELLID", 250,"left",false,false,false));
		    jqGridTableModel.getColModel().add(getColModel("SHIFTID", 250,"left",false,false,false));

		    JqGridColModel jqGridColModel =  getColModel("DAYTIME", 250,"left",false,false,false);
		    jqGridColModel.setSummaryTpl("<b><font >Total</font> </b>");
		    jqGridTableModel.getColModel().add( jqGridColModel);

		    String colIndex ="";
		    colHeader = headers.get(0);
		    for(int i =6; i < colHeader.length; i++)
		    {
		        emptyrow [i] ="";

		        colIndex = colHeader[i].replaceAll(" ", "").replace("-", "")+i ;
		        
		        jqGridTableModel.getColModel().add(getColModel( colIndex,25,"center", false,true,false));
		    }
		    
		     JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		     CommonFunctions.debugMsg(tableModel+" tableModel ");
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
		//Gopi--------------------- _sb function returns extra meta rows (colmodel, colorder...). 
		// Keep only [DATE row, names row, data rows...] and align columns so that PLANTID is index 0.
		private List<String[]> cleanSbList(List<String[]> list)
		{
		    if(list == null || list.isEmpty())
		        return list;
		    int dateIdx = -1;
		    int subIdx = -1;
		    int offset = 0;
		    for(int r = 0; r < list.size(); r++)
		    {
		        String[] row = list.get(r);
		        if(row == null)
		            continue;
		        for(int c = 0; c < row.length; c++)
		        {
		            if(dateIdx == -1 && "DATE".equals(row[c]))
		                dateIdx = r;
		            if(subIdx == -1 && "PLANTID".equals(row[c]))
		            {
		                subIdx = r;
		                offset = c;
		            }
		        }
		        if(dateIdx != -1 && subIdx != -1)
		            break;
		    }
		    if(dateIdx == -1 || subIdx == -1)
		        return list;

		    List<String[]> cleaned = new ArrayList<String[]>();
		    cleaned.add(trimCols(list.get(dateIdx), offset));
		    cleaned.add(trimCols(list.get(subIdx), offset));
		    for(int r = subIdx + 1; r < list.size(); r++)
		        cleaned.add(trimCols(list.get(r), offset));
		    return cleaned;
		}

		// removes leading extra columns (offset) and any trailing extra column (like ordercol)
		// final length = 9 fixed columns + 3 columns per date
		private String[] trimCols(String[] row, int offset)
		{
		    if(row == null)
		        return row;
		    int usable = row.length - offset;
		    if(usable < 9)
		        return row;
		    int newLen = 9 + ((usable - 9) / 3) * 3;
		    String[] out = new String[newLen];
		    System.arraycopy(row, offset, out, 0, newLen);
		    return out;
		}
		// Time report : keep only [DATE row, SHIFT row, data rows...] and align columns so FACTORYNAME is index 0
		private List<String[]> cleanSbTimeList(List<String[]> list)
		{
		    if(list == null || list.isEmpty())
		        return list;
		    int dateIdx = -1;
		    int subIdx = -1;
		    int offset = 0;
		    for(int r = 0; r < list.size(); r++)
		    {
		        String[] row = list.get(r);
		        if(row == null)
		            continue;
		        for(int c = 0; c < row.length; c++)
		        {
		            if(dateIdx == -1 && "DATE".equals(row[c]))
		                dateIdx = r;
		            if(subIdx == -1 && "SHIFT".equals(row[c]))
		            {
		                subIdx = r;
		                offset = c;
		            }
		        }
		        if(dateIdx != -1 && subIdx != -1)
		            break;
		    }
		    if(dateIdx == -1 || subIdx == -1)
		        return list;

		    List<String[]> cleaned = new ArrayList<String[]>();
		    cleaned.add(trimColsTime(list.get(dateIdx), offset));
		    cleaned.add(trimColsTime(list.get(subIdx), offset));
		    for(int r = subIdx + 1; r < list.size(); r++)
		        cleaned.add(trimColsTime(list.get(r), offset));
		    return cleaned;
		}

		// final length = 6 fixed columns + 3 columns per date
		private String[] trimColsTime(String[] row, int offset)
		{
		    if(row == null)
		        return row;
		    int usable = row.length - offset;
		    if(usable < 6)
		        return row;
		    int newLen = 6 + ((usable - 6) / 3) * 3;
		    String[] out = new String[newLen];
		    System.arraycopy(row, offset, out, 0, newLen);
		    return out;
		}
		//----------------end gopi------------------------------------
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