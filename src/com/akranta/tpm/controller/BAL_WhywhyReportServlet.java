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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.YYFormBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_BdmTlWhywhydtl;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.BAL_BdmTlYydonebymst;
import com.akranta.tpm.model.BAL_BdmTlYyeffectivedtl;
import com.akranta.tpm.model.BAL_BdmTlYyeffectivemst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.BAL_BreakdownService;
import com.akranta.tpm.service.BAL_WhyWhyAnalysisService;
import com.akranta.tpm.service.BAL_WhywhyReportService;
import com.akranta.tpm.service.WorkOrderService;
import com.akranta.tpm.service.impl.BAL_BreakdownServiceImpl;
import com.akranta.tpm.service.impl.BAL_WhyWhyAnalysisServiceImpl;
import com.akranta.tpm.service.impl.BAL_WhywhyReportServiceImpl;
import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

public class BAL_WhywhyReportServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	private static final String gendrillcommonfilter = "whywhygendrillfilter"; 
	
	BAL_WhywhyReportService whywhyService;
	BAL_WhyWhyAnalysisService yyService;
	WorkOrderService workOrderService;
	BAL_BreakdownService breakDownService ;
	
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
		String action = UIUtils.getActionPart(request);
		//HttpSession httpSession = request.getSession(false);

		try {
			whywhyService = (BAL_WhywhyReportServiceImpl)UIUtils.getServiceObject(request,"BAL_WhywhyReportServiceImpl");
			yyService = (BAL_WhyWhyAnalysisServiceImpl)UIUtils.getServiceObject(request,"BAL_WhyWhyAnalysisServiceImpl");
			CommonFunctions.debugMsg("yyserivce completed ");
			breakDownService  = (BAL_BreakdownServiceImpl)UIUtils.getServiceObject(request,"BAL_BreakdownServiceImpl");
			CommonFunctions.debugMsg("breakDownService completed ");
			workOrderService   = (WorkOrderServiceImpl)UIUtils.getServiceObject(request,"WorkOrderServiceImpl");
			CommonFunctions.debugMsg("workOrderService completed ");
		} 
		catch (ServiceObjectCreationException e) {
			//CommonFunctions.debugMsg(e);
		}

		
		if( action.equals("filterXmlwhywhyReport_input.balwhy")){
			response.setContentType("xml");		
			CommonFunctions.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyStandard.xml");
		 }else if( action.equals("filterXmlwhywhyEffectiveness_input.balwhy")){
				response.setContentType("xml");		
				CommonFunctions.debugMsg("action "+ action); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyEffecRpt.xml");
			}	else if( action.equals("whywhyEffectiveness_input.balwhy")){
				UIUtils.forwardRequest(request, response, "/pages/bal_EffectivenessForm.jsp");
			 }else if( action.equals("whywhyEffectiveness_getCol.balwhy")){
				 HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",true);
				List< String[]> YYEffectiveList  = whywhyService.getYYEffectiveness(commonFilter);
	   	    	JSONObject jsonObject = null;
			 	
	   	    	JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				
				//jqGridTableModel.setGroupBy(true);
				//jqGridTableModel.setGroupByField("WWMS_KEYID");
				//String[] formatterval  = {"cmbEffective#12"};
				//String[] formatterval  = {"dteTargetDate#6","cmbAbnmTrade#7","cmbAbnmResponse#9"};
				//jqGridTableModel.setFormatterIndex(formatterval);
				
				gridColModel.setHeaderNum(1);
				
				
				
				String [] colHeader = YYEffectiveList.get(1);			
				String [] colHeaderCond = YYEffectiveList.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				colModel.set("multiSelect", true);
				httpSession.removeAttribute("YYEffectColModel");
				httpSession.setAttribute("YYEffectColModel",colModel);	
				System.out.println("jsonObject " + colModel);
				out.println(colModel);
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYEffectiveness"));
			 }
			 else if(action.equals("whywhyEffectiveness_getData.balwhy"))
				{
					try
					{
						PrintWriter out = response.getWriter();
						JSONObject jsonObject = new JSONObject();
						CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",false);
						List< String[]> YYEffectiveList  = whywhyService.getYYEffectiveness(commonFilter);
						CommonFunctions.debugMsg("size " + YYEffectiveList.size());
						jsonObject  = UIUtils.convertToJqGridTableObject(YYEffectiveList,request,2,0,YYEffectiveList.size()); 
						out.println(jsonObject);
				    }catch(Exception e)
					{
							e.printStackTrace();
					}
				}
		
			 else if(action.equals("whywhyEffectiveness_getExcel.balwhy"))
				{
				    CommonFilter	commonFilter = populateCommonFilter(request,"whywhyCommonFilter",false);
				    HttpSession httpSession = request.getSession(false);
				    String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);//
					httpSession = request.getSession(false);
					JSONObject colmodel = (JSONObject) httpSession.getAttribute("YYEffectColModel");
					colmodel.put("title","Why Why Effectiveness Report");
		            String format = ExcelUtils.getFormat(request);
					Workbook wb = whywhyService.WhyWhyEffectivenessExportExcel(commonFilter,colmodel,format);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response,wb,"WhyWhyEffectivenessReport", format);
				}
			 else if (action.equals("whywhyanalysisgrid_recall.balwhy")){
				    PrintWriter out = response.getWriter();
					String cellId = request.getParameter("cellId");
					String formName=request.getParameter("frm");
					String keyid=request.getParameter("keyid");
					CommonFunctions.debugMsg("keyid   keyid  :  "+cellId);
					CommonFunctions.debugMsg(" Inside recall method for :: "+keyid+" formName :: "+formName);
					List<String []> condReclData  = whywhyService.FillControlData(cellId,keyid,formName);
					out.print( JSONArray.fromCollection(condReclData));
				}
		else if(action.equals("whywhyReport_input.balwhy")){
			request.setAttribute("yyrep", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","yyrep"));
			request.setAttribute("yydetails", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","yydetails"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/bal_WhyWhyStandardReport.jsp"); 
			rd.forward(request, response); 
		}if( action.equals("filterXmlwhywhyanalysis_input.balwhy")){
			response.setContentType("xml");		
			CommonFunctions.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyAnalysisRpt.xml");
		}
		else if( action.equals("whywhyExl_view.balwhy") )
		{
			try{					
				String keyid =request.getParameter("keyId");
				String imagePath = UIUtils.getImagePath(request);
				CommonFunctions.debugMsg("keyid::::::"+keyid);
				String format = ExcelUtils.getFormat(request);
				format = "xlsx";
				String path = UIUtils.getExcelTemplatePath(request);  	
				Workbook wb = whywhyService.getwhywhyExlView(keyid,format,path,imagePath); 
				ExcelUtils.writeToResponse(response, wb, "WhyWhy_"+keyid, format);
			}			
			catch(Exception e)
			{
				e.printStackTrace();
				CommonFunctions.debugMsg("err:"+e.getMessage());
				PrintWriter out = response.getWriter();
				JSONObject err = new JSONObject();				
				//err.put("exception",true);				
				err.put("message" ,"Data Not Found" );
				out.print(err.toString());
			}				
		}
		else if( action.equals("whywhyanalysis_input.balwhy"))     ///TTTT
		{ 
			CommonFunctions.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/pages/bal_WhyWhyAnalysisMainFormGrid.jsp");
		 }
		 
		else if( action.equals("whywhyanalysismodify_input.balwhy")){
			
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			
			String maskeyid =request.getParameter("keyid");
			
			String RefDocId=request.getParameter("refdocid");
			String finalaction=request.getParameter("txtwwmsFinalaction");
			System.out.println(" final Action ..........."+finalaction);
			String maskeyid1 = request.getParameter("cmbWwmsKeyid");
			String masRefkeyid = null;
			
			if(!UIUtils.isValidKeyId(maskeyid))
				maskeyid = maskeyid1;
			if(UIUtils.isValidKeyId(RefDocId))
				masRefkeyid=yyService.getYYKeyId(RefDocId);
			if(!UIUtils.isValidKeyId(maskeyid))
				maskeyid=masRefkeyid;
			CommonFunctions.debugMsg("maskeyid..."+maskeyid+"...maskeyid1..."+masRefkeyid);
			BAL_BdmTlWhywhymst newBdmTlWhywhymst = new BAL_BdmTlWhywhymst();   
		
			if(UIUtils.isValidKeyId(maskeyid)){
				newBdmTlWhywhymst = yyService.selectmaskeyid(maskeyid);
				//System.out.println("newBdmTlWhywhymst "+newBdmTlWhywhymst.getWwmsArea());
				String[] rptData = newBdmTlWhywhymst.getWwmsReportdatetime().split(" ");
		 		newBdmTlWhywhymst.setWwmsReportdatetime(rptData[0]);
		 		newBdmTlWhywhymst.setWwmsDate(newBdmTlWhywhymst.getWwmsDate().substring(0,11));
		 		CommonFunctions.debugMsg(" Inside input action :: "+rptData[1]);
		 		CommonFunctions.debugMsg(" Checking For Reported Time Date :: "+newBdmTlWhywhymst.getWwmsReportdatetime());
		 		request.setAttribute("time" ,rptData[1]);	
		 		if(UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsOthercheckpoints()))
		 			request.setAttribute("otherCheck" ,"Y");
		 		else
		 			request.setAttribute("otherCheck" ,"N");
			}
	 		HttpSession httpSession = request.getSession(false);
	 		
	 		String refDocId = request.getParameter("refdocid");
	 		String refdoctype = request.getParameter("refdoctype");
	 		String flid = request.getParameter("flid");
	 		String refdocdate = request.getParameter("refdocdate");
	 		String problem = request.getParameter("problem");
	 		String attendedBy = request.getParameter("attendedBy");
	 		String area = request.getParameter("area");
	 		String pillar = request.getParameter("pillar");
	 		String mode = request.getParameter("yymode");
	 		String counterMeasure=request.getParameter("countermeasure");
	 		String immediateAction=request.getParameter("immediateAction");
	 		String finalAction=request.getParameter("finalAction");
	 		String rootCause =request.getParameter("rootCause");
	 		System.out.println(rootCause);
	 		CommonFunctions.debugMsg("refDocId.."+rootCause+"..problem.."+problem+"...fianalaction       "+ finalAction+"...COUNTERMEASURE       "+ counterMeasure+"...refdoctype.."+refdoctype+"..flid.."+flid+"..refdocdate.."+refdocdate);
	 		if(UIUtils.isValidKeyId(refDocId))
	 			newBdmTlWhywhymst.setWwmsRefdocno(refDocId);
	 		if(UIUtils.isValidKeyId(refdoctype))
	 			newBdmTlWhywhymst.setWwmsRefdoctype(refdoctype);
	 		if(UIUtils.isValidKeyId(flid))
	 			newBdmTlWhywhymst.setWwmsFlid(flid);
	 		if(UIUtils.isValidKeyId(refdocdate))
	 			newBdmTlWhywhymst.setWwmsDate(refdocdate);
	 		if(UIUtils.isValidKeyId(problem))
	 			newBdmTlWhywhymst.setWwmsProblem(problem);
	 		if(UIUtils.isValidKeyId(area))
	 			newBdmTlWhywhymst.setWwmsArea(area);
	 		if(UIUtils.isValidKeyId(attendedBy))
	 			newBdmTlWhywhymst.setWwmsProblemattendby(attendedBy);
	 		if(UIUtils.isValidKeyId(counterMeasure))
	 			newBdmTlWhywhymst.setWwmsCountermeasure(counterMeasure);
	 		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsWhywhydoneby()))
	 			newBdmTlWhywhymst.setWwmsWhywhydoneby(user.getUsrm_ccno());
	 		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsFinalaction()))
	 			newBdmTlWhywhymst.setWwmsFinalaction(finalAction);
	 	//	if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsRootcause()))
	 		//newBdmTlWhywhymst.setWwmsRootcause(rootCause);
	 		
	 		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsProblemattendby()))
	 			newBdmTlWhywhymst.setWwmsProblemattendby(attendedBy);
	 		
	 		CommonFunctions.debugMsg("123,,,"+newBdmTlWhywhymst.getWwmsDate());
			request.setAttribute("newBdmTlWhywhymst" ,newBdmTlWhywhymst);
			request.setAttribute("rcId",newBdmTlWhywhymst.getWwmsRootcauseid() );
			
			request.setAttribute("refDocdate",refdocdate);
			request.setAttribute("mode",mode);
			httpSession.setAttribute("newBdmTlWhywhymst", newBdmTlWhywhymst);
			CommonFunctions.debugMsg("action "+ action);
			UIUtils.forwardRequest(request, response, "/pages/BAL_WhyWhyAnalysisMainForm.jsp");
		 }
		
		else if(action.equals("whywhyReport_getCol.balwhy"))
		{
			   CommonFunctions.debugMsg("1111111111111111list      ::::  col");
			  HttpSession httpSession = request.getSession(false);
			  PrintWriter out = response.getWriter();
			  CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",true);	
			 
			  if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
				  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
				  commonFilter.setToDate(CommonFunctions.getDate());
				 
		 	  }
	 		  
			  List< String[]> whywhyList  = whywhyService.getAllwhywhyStd(commonFilter);
			  String colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.whywhyRpt", "whywhyRpt");
			  JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				gridColModel.setHeaderNum(1);
				
				String [] colHeader = whywhyList.get(0);			
				String [] colHeaderCond = whywhyList.get(1);
				
				//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "82%%");
				jsonObject.put("tableWidth", "106%%");
				httpSession.setAttribute("yyReportColModel", jsonObject);
				out.println(jsonObject);
			
		      
		}	
		else if(action.equals("whywhyReport_getData.balwhy"))
		{
			try
			{
				
				  HttpSession httpSession = request.getSession(false);
				  String page = request.getParameter("page");	
				  CommonFunctions.debugMsg("1111111111111111list      :::: daya " +page);
				  PrintWriter out = response.getWriter();
				  CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",false);
				  CommonFunctions.debugMsg("  11111 popoi " );
				  JSONObject jsonObject = new JSONObject();
					  List< String[]> whywhyList  = whywhyService.getAllwhywhyStd(commonFilter);
					  CommonFunctions.debugMsg("size " + whywhyList.size());
					  jsonObject  = UIUtils.convertToJqGridTableObject(whywhyList,request,2,0,commonFilter.getTotalRecordCnt()); 
		          
					  CommonFunctions.debugMsg("  11111"+jsonObject);
		    //      }
					  out.println(jsonObject);
		           
		    }catch(Exception e)
			{
					CommonFunctions.debugMsg(e.getMessage());
			}
		}

		else if( action.equals("whywhyReport_getExcel.balwhy")){
			
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"whywhyCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate;
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("yyReportColModel");
			
			tblJSONObj.put("title", "Why-Why Standard Report" +"  -  "+date);
			//tblJSONObj.put("groupBy",false);
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = whywhyService.yyExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "Why-WhyStandardReport", format);
			
		}
		else if(action.equals("whywhyanalysismaingrid_getExcel.balwhy"))
		{
			  HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"whywhyanalysisCommonFilter",false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("ColModel");
			tblJSONObj.put("title","WhyWhyAnalysis");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = yyService.whywhyExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "WhyWhyAnalysis", format);
			
			
		}
	
		
		 else if(action.equals("yyDonebyLink_getCol.balwhy")){
			 try {
				 String masterkeyid=request.getParameter("masterkeyid");
					List<String[]> yyDonebyList = whywhyService.getyyDoneby(masterkeyid);
					JSONObject jsonObject = getyyDonebyTableModel(yyDonebyList);
					System.out.println("Table model");
					System.out.println("jsonObject "+jsonObject);
					PrintWriter  out = response.getWriter();
					out.println(jsonObject);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		
		 else if(action.equals("yyDonebyLink_getData.balwhy")){
			 PrintWriter out = response.getWriter();
			 String masterkeyid=request.getParameter("masterkeyid");
			 List<String[]> yyDonebyList = whywhyService.getyyDoneby(masterkeyid);
				
			 JSONObject yyDonebydata = UIUtils.convertToJqGridTableObject( yyDonebyList, request, 1, 0);
			  out.println(yyDonebydata);
		 }

		if( action.equals("filterXmlwhywhyQtyReport_input.balwhy")){
			response.setContentType("xml"); 
			CommonFunctions.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyQtyStandard.xml") ;
		 }
		
	
		else if(action.equals("whywhyQtyReport_input.balwhy")){			
		//	request.setAttribute("yyrep", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","yyrep"));
		//	request.setAttribute("yydetails", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","yydetails"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/bal_WhyWhyStandardQtyReport.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("whywhyQtyReport_getCol.balwhy"))
		{
			
			  HttpSession httpSession = request.getSession(false);
			  PrintWriter out = response.getWriter();
			  CommonFilter commonFilter = populateCommonFilter(request,"whywhyQtyCommonFilter",true);	
			 
			  List<String[]> yyQtyList  = whywhyService.getAllwhywhyQtyStd(commonFilter);
			  JSONObject bdData = UIUtils.convertToJqGridTableObject(yyQtyList,request,0,0,commonFilter.getTotalRecordCnt());
			  CommonFunctions.debugMsg("Total Count : "+commonFilter.getTotalRecordCnt());
			  
			  httpSession.setAttribute("yyQtyDataServlet", bdData);				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(false);
				
				gridColModel.setHeaderNum(1);
				
				String [] colHeader1 = yyQtyList.get(2);
				String [] colHeaderCond = yyQtyList.get(1);
				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader1);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				//jsonObject.put("data", bdData);
				CommonFunctions.debugMsg("colModel   "+jsonObject);
			  
			//  JSONObject jsonObject = getTableModel(yyQtyList);
			  jsonObject.put("tableHeight", "92%%");
			  jsonObject.put("tableWidth", "108%%");
			  httpSession.removeAttribute("yyQtyColModel");
		 	  httpSession.setAttribute("yyQtyColModel",jsonObject);
		 	   
			  out.println(jsonObject);
			  
		}	
		else if(action.equals("whywhyQtyReport_getData.balwhy"))
		{
			
			try
			{	  
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				String page = request.getParameter("page");	  
				CommonFilter commonFilter = populateCommonFilter(request,"whywhyQtyCommonFilter",true);	
						
				JSONObject jsonObject = new JSONObject();
				CommonFunctions.debugMsg("PAGE : "+page);
			
					jsonObject = (JSONObject) httpSession.getAttribute("yyQtyDataServlet");
	        		CommonFunctions.debugMsg("Total Count : "+commonFilter.getTotalRecordCnt());
	        		  List<String[]> yyQtyList  = whywhyService.getAllwhywhyQtyStd(commonFilter);
	        		  jsonObject = UIUtils.convertToJqGridTableObject(yyQtyList,request,3,0,commonFilter.getTotalRecordCnt()+1); 
	        	 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
			}
			catch(Exception e)
			{
				CommonFunctions.debugMsg("error " +e.getMessage());
			}
		}
		else if( action.equals("whywhyQtyReport_getExcel.balwhy")) /* excel added by Ramya */
		{
			HttpSession httpSession = request.getSession(false);					
			CommonFilter commonFilter = populateCommonFilter(request,"whywhyQtyCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("yyQtyColModel");					
			//JSONObject tblJSONObj = JSONObject.fromString(tableModel);					
			tblJSONObj.put("title", "Why Why Standard Report");
			String format = ExcelUtils.getFormat(request);
			Workbook wb =whywhyService.getAllwhywhyQtyExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "WhyWhyStandardRpt", format);
		}
		/* Created By Suresh.K on Jan 11,2012 */
		else if(action.equals("whywhy_input.balwhy")){
			
			//yyService = (WhyWhyAnalysisServiceImpl)UIUtils.getServiceObject(request,"WhyWhyAnalysisServiceImpl");
			
			UIUtils.displayRequestParamsValue(request);
			HttpSession httpSession = request.getSession(false);
			BAL_BdmTlWhywhymst bdmTlWhywhymst = new BAL_BdmTlWhywhymst();			
			YYFormBean yyFormBean = new YYFormBean();
			
			String yyRefDocID = request.getParameter("whywhyRefDocID");
			
			
			if(UIUtils.isValidKeyId(yyRefDocID))
			{ 
				if(yyRefDocID.substring(0, 2).equals("BD") || yyRefDocID.substring(0, 2).equals("CU"))
					bdmTlWhywhymst = breakDownService.selectWhyWhy(yyRefDocID);			
				else if(yyRefDocID.substring(0, 2).equals("AB") || yyRefDocID.substring(0, 2).equals("GM"))
				{
					String womsKey = request.getParameter("womsKey");
					WomTlWomst womTlWomst = new WomTlWomst();
					if(UIUtils.isValidKeyId(womsKey))
					{
						womTlWomst = workOrderService.select(womsKey);
						fillYY(bdmTlWhywhymst,womTlWomst);
					}
				}
			}
			else
				bdmTlWhywhymst =(BAL_BdmTlWhywhymst) UIUtils.setBeanProperties((Object)bdmTlWhywhymst,request);
			
			String  refdocid = request.getParameter("whywhyRefDocID");
			String  refdoctype = request.getParameter("whywhyRefDocType");
			String  flid = request.getParameter("flid");
			String  refdocdate = request.getParameter("refDocDate");
			String  problem = request.getParameter("prbolem");
			String  yymode = request.getParameter("yyMode");
			//String  attendedBy = request.getParameter("attendedBy");
			String  area = request.getParameter("area");
			String  pillar = request.getParameter("pillar");

			String filterStr = "refdocid="+refdocid+"&refdoctype="+refdoctype+"&flid="+flid+"&refdocdate="+refdocdate+"&problem="+problem+"&yymode="+yymode+"&area="+area+"&pillar="+pillar;//"&attendedBy="+attendedBy+
			CommonFunctions.debugMsg("filterStr...."+filterStr);
			if(UIUtils.isValidKeyId(refdocid)){
				bdmTlWhywhymst.setWwmsRefdocno(refdocid);
				httpSession.setAttribute("refdocid",refdocid);
			}
			if(UIUtils.isValidKeyId(refdoctype)){
				bdmTlWhywhymst.setWwmsRefdoctype(refdoctype);
				httpSession.setAttribute("refdoctype",refdoctype);
			}
			if(UIUtils.isValidKeyId(flid)){
				bdmTlWhywhymst.setWwmsFlid(flid);
				httpSession.setAttribute("flid",flid);
			}
			if(UIUtils.isValidKeyId(refdocdate)){
				bdmTlWhywhymst.setWwmsDate(refdocdate);
				httpSession.setAttribute("refdocdate",refdocdate);
			}
			if(UIUtils.isValidKeyId(refdocdate)){
				bdmTlWhywhymst.setWwmsProblem(problem);
				httpSession.setAttribute("problem",problem);
			}
			
			yyFormBean =(YYFormBean) UIUtils.setBeanProperties((Object)yyFormBean,request);
			String wwmsKey = (String) httpSession.getAttribute("wwmsKeyid");
			CommonFunctions.debugMsg("Form Type .....................>"+yyFormBean.getFormType()+"................................");
			yyFormBean=new YYFormBean();			
			yyFormBean.setFormType("BD");
			if(yyFormBean.getFormType().equals("BD") ||yyFormBean.getFormType().equals("BDM") || yyFormBean.getFormType().equals("CC")|| yyFormBean.getFormType().equals("SHE")|| yyFormBean.getFormType().equals("DOCK")|| yyFormBean.getFormType().equals("IMT")||yyFormBean.getFormType().equals("ABN") )
			{
				if(yyFormBean.getFormType().equals("ABN"))
				{
					String refDocId = bdmTlWhywhymst.getWwmsRefdocno();
					CommonFunctions.debugMsg("refDocId "+refDocId);
					if(UIUtils.isValidKeyId(refDocId))
					{
						String existFlag = yyService.checkMstExist(refDocId);
						if(UIUtils.isValidKeyId(existFlag))
						{
							if(Integer.parseInt(existFlag)>0)
								bdmTlWhywhymst = breakDownService.selectWhyWhy(refDocId);
						}
					}
				}
				httpSession.removeAttribute("WHYWHYFORMTYPE");
				httpSession.setAttribute("WHYWHYFORMTYPE",yyFormBean.getFormType());
				if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsKeyid()))
				{
					String YYKey = bdmTlWhywhymst.getWwmsKeyid();
					if(UIUtils.isValidKeyId(YYKey))
					{
						bdmTlWhywhymst = yyService.getWWMS(YYKey);
					}
					List<String[]> yyMstValues = yyService.getSelectedRootCause(bdmTlWhywhymst.getWwmsKeyid());
					//bdmTlWhywhymst.setWwmsFinalaction(yyMstValues.get(0)[6]);
					bdmTlWhywhymst.setWwmsChecksmade(yyMstValues.get(0)[7]);
					bdmTlWhywhymst.setWwmsYoudidnot(yyMstValues.get(0)[8]);
					bdmTlWhywhymst.setWwmsCountermeasure(yyMstValues.get(0)[9]);
					request.setAttribute("rcId", yyMstValues.get(0)[0]);
					request.setAttribute("isJH", yyMstValues.get(0)[2]);
					request.setAttribute("isPM", yyMstValues.get(0)[3]);
					request.setAttribute("isCI", yyMstValues.get(0)[4]);
					request.setAttribute("isET", yyMstValues.get(0)[5]);
			
				}
				request.setAttribute("causeId",request.getParameter("cmbwwmsCauseid"));	
			}				
			else
			{
				CommonFunctions.debugMsg("Why Why Key : "+bdmTlWhywhymst.getWwmsKeyid());
				String YYKey = bdmTlWhywhymst.getWwmsKeyid();
				if(UIUtils.isValidKeyId(YYKey))
				{
					bdmTlWhywhymst = yyService.getWWMS(YYKey);
				}
			}
			
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsDate()))
			{
				if(bdmTlWhywhymst.getWwmsDate().indexOf(":")>0)
					bdmTlWhywhymst.setWwmsDate(bdmTlWhywhymst.getWwmsDate().substring(0, bdmTlWhywhymst.getWwmsDate().indexOf(" ")));
			}
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPrevdate()))
			{
				
				CommonFunctions.debugMsg(bdmTlWhywhymst.getWwmsPrevdate());
				if(bdmTlWhywhymst.getWwmsPrevdate().indexOf(":")>0)
				{
					bdmTlWhywhymst.setWwmsPrevdate(bdmTlWhywhymst.getWwmsPrevdate().substring(0, bdmTlWhywhymst.getWwmsPrevdate().indexOf(" ")));
					if(bdmTlWhywhymst.getWwmsPrevdate().equals(Constants.passNullDate))
						bdmTlWhywhymst.setWwmsPrevdate("");
				}
				else
					bdmTlWhywhymst.setWwmsPrevdate(bdmTlWhywhymst.getWwmsPrevdate());
			}
			
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPreveffectiveness()))
			{
				bdmTlWhywhymst.setWwmsPreveffectiveness("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsRootcause()))
			{
				bdmTlWhywhymst.setWwmsRootcause("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPokayoke()))
			{
				bdmTlWhywhymst.setWwmsPokayoke("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsMachineid()))
			{
				bdmTlWhywhymst.setWwmsMachineid("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsAssemblyid()))
			{
				bdmTlWhywhymst.setWwmsAssemblyid("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsAccidentphen()))
			{
				bdmTlWhywhymst.setWwmsAccidentphen("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPrevno()))
			{
				bdmTlWhywhymst.setWwmsPrevno("");
			}
			if(!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPrevperson()))
			{
				bdmTlWhywhymst.setWwmsPrevperson("");
			}
			
			request.setAttribute("kznWhyWhyMst",bdmTlWhywhymst);
			request.setAttribute("yyFormBean",yyFormBean);
			CommonFunctions.debugMsg("before call jsp"+"................................");
			
			String counterMeasureFlag = yyService.checkCounterMsr();
			if(UIUtils.isValidKeyId(counterMeasureFlag))
				request.setAttribute("counterMeasureFlag",counterMeasureFlag);
			
			request.setAttribute("filterStr",filterStr);
			request.setAttribute("mode",yymode);
			UIUtils.forwardRequest(request, response, "/pages/bal_WhyWhyAnalysisMainFormGrid.jsp");
		}
		
		else if( action.equals("yy_getCol.balwhy"))
		{
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("yyServletWmsKey");
			httpSession.removeAttribute("yyServletfinalAction");
			httpSession.setAttribute("yyServletWmsKey", request.getParameter("wwmsKey"));
			httpSession.setAttribute("yyServletfinalAction", request.getParameter("finalAction"));
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcolModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcolModel"));
		}
		else if(action.equals("yy_getData.balwhy"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(false);
				String wmsKey = (String) httpSession.getAttribute("yyServletWmsKey");
				String finalAction = (String) httpSession.getAttribute("yyServletfinalAction");
				CommonFunctions.debugMsg("wmsKey "+wmsKey);
				CommonFunctions.debugMsg("finalAction "+finalAction);
			
				List<String []> yyList = new ArrayList();
				if(UIUtils.isValidKeyId(wmsKey))
				{ 
					CommonFunctions.debugMsg("Execute Function ");
					yyList  = breakDownService.getYY(wmsKey);
				}
				else
				{
					String[] yyDatas = {finalAction,"{}","{}","{}","{}"};
					yyList.add(yyDatas);
				}
				CommonFunctions.debugMsg("yylist roopa STTART:" + yyList);
				JSONObject yyData = UIUtils.convertToJqGridTableObject(yyList,request,0,0);	  	
				CommonFunctions.debugMsg("yylist roopa:"+yyData);
  			 	out.println(yyData);
			}catch(Exception e){
					CommonFunctions.debugMsg(e.getMessage());
			}
		}
	
		else if( action.equals("yykaizen_getCol.balwhy"))
		{
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("yyKaizenServletWmsKey");
			httpSession.removeAttribute("yyKaizenServletfinalAction");
			httpSession.setAttribute("yyKaizenServletWmsKey", request.getParameter("wwmsKey"));
			httpSession.setAttribute("yyKaizenServletfinalAction", request.getParameter("finalAction"));
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYKaizencolModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYKaizencolModel"));
		}
		else if(action.equals("yykaizen_getData.balwhy"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(false);
				String wmsKey = (String) httpSession.getAttribute("yyKaizenServletWmsKey");
				String finalAction = (String) httpSession.getAttribute("yyKaizenServletfinalAction");
				
				List<String []> yyList = new ArrayList();
				if(UIUtils.isValidKeyId(wmsKey))
				{
					yyList  = breakDownService.getYY(wmsKey);
				}
				else
				{
					String[] yyDatas = {finalAction,"{}","{}","{}","{}"};
					yyList.add(yyDatas);
				}
			
				JSONObject yyData = UIUtils.convertToJqGridTableObject(yyList,request,0,0);	  	
				CommonFunctions.debugMsg(""+yyData);
  			 	out.println(yyData);
			}catch(Exception e){
					CommonFunctions.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("yyShe_getCol.balwhy"))
		{
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("yySheServletWmsKey");
			httpSession.removeAttribute("yySheServletPhen");
			httpSession.setAttribute("yySheServletWmsKey", request.getParameter("wwmsKey"));
			httpSession.setAttribute("yySheServletPhen", request.getParameter("phenomena"));
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYSHEcolModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYSHEcolModel"));
		}
		else if(action.equals("yyShe_getData.balwhy"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(false);
				String wmsKey = (String) httpSession.getAttribute("yySheServletWmsKey");
				String phen = (String) httpSession.getAttribute("yySheServletPhen");
				CommonFunctions.debugMsg("wmsKey "+wmsKey);
				CommonFunctions.debugMsg("phen "+phen);
			
				List<String []> yyList = new ArrayList();
				if(UIUtils.isValidKeyId(wmsKey))
				{ 
					CommonFunctions.debugMsg("Execute Function ");
					yyList  = breakDownService.getYY(wmsKey);
				}
				else
				{
					String[] yyDatas = {phen,"{}","{}","{}","{}"};
					yyList.add(yyDatas);
				}
			
				JSONObject yyData = UIUtils.convertToJqGridTableObject(yyList,request,0,0);	  	
				CommonFunctions.debugMsg(""+yyData);
  			 	out.println(yyData);
			}catch(Exception e){
					CommonFunctions.debugMsg(e.getMessage());
			}
		}
	
	
		else if( action.equals("yyCC_getCol.balwhy"))
		{
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("yyServletWmsKey");
			httpSession.removeAttribute("yyServletfinalAction");
			httpSession.setAttribute("yyServletWmsKey", request.getParameter("wwmsKey"));
			httpSession.setAttribute("yyServletfinalAction", request.getParameter("finalAction"));
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcccolModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcccolModel"));
		}
		else if( action.equals("rootcause_getCol.balwhy") )
		{
			//httpSession.setAttribute("wwNo", request.getParameter("wwNo"));				
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel"));
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel"));
		
		}
		else if( action.equals("rootcause_getData.balwhy") )
		{
			try
			{	
				CommonFunctions.debugMsg("ROOTCAUSE_getdata");
			
				String openMode = request.getParameter("openMode");
				PrintWriter out = response.getWriter();
				List<String []> rootCauseList  = yyService.getRootCause(openMode);
				JSONObject rcData = UIUtils.convertToJqGridTableObject(rootCauseList,request,0,0);	  
				CommonFunctions.debugMsg(""+rcData);
  			 	out.println(rcData);
			}catch(Exception e)
			{
					CommonFunctions.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("rootcauseBD_getCol.balwhy") )
		{
			//httpSession.setAttribute("wwNo", request.getParameter("wwNo"));				
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel2"));
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel2"));
		
		}
		else if( action.equals("rootcauseBD_getData.balwhy") )
		{
			try
			{	
				CommonFunctions.debugMsg("ROOTCAUSE_getdata");
				String openMode = request.getParameter("openMode");
				PrintWriter out = response.getWriter();
				List<String []> rootCauseList  = yyService.getRootCause(openMode);   ///////////////////
				JSONObject rcData = UIUtils.convertToJqGridTableObject(rootCauseList,request,0,0);	  
				CommonFunctions.debugMsg(""+rcData);
  			 	out.println(rcData);
			}catch(Exception e)
			{
					CommonFunctions.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("pillar_getCol.balwhy") )
		{
			//httpSession.setAttribute("wwNo", request.getParameter("wwNo"));				
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarColModel"));
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarColModel"));
		
		}
		else if( action.equals("pillar_getData.balwhy") )
		{
			try
			{	
				CommonFunctions.debugMsg("PILLAR");
				String yyID = request.getParameter("wwmsKey");
				String pillarFlag = request.getParameter("yyOpenFrom");
				CommonFunctions.debugMsg("PILLAR : "+yyID);
				PrintWriter out = response.getWriter();
				List<String []> rootCauseList = new ArrayList();
				if(UIUtils.isValidKeyId(yyID))
				{
					rootCauseList  = yyService.getPillar(yyID,pillarFlag);
					//rootCauseList.get(0)[0]
					rootCauseList = fillPillarDatas(rootCauseList);
				}
				else
				{
					rootCauseList = fillPillar(rootCauseList,pillarFlag);
				}
				JSONObject rcData = UIUtils.convertToJqGridTableObject(rootCauseList,request,0,0);	  
				CommonFunctions.debugMsg(""+rcData);
  			 	out.println(rcData);
			}catch(Exception e)
			{
					CommonFunctions.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("pillarBD_getCol.balwhy") )
		{
			//httpSession.setAttribute("wwNo", request.getParameter("wwNo"));				
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarBDColModel"));
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarBDColModel"));
		}
		else if( action.equals("pillarBD_getData.balwhy") )
		{
			try
			{	
				CommonFunctions.debugMsg("PILLAR");
				String yyID = request.getParameter("wwmsKey");
				String pillarFlag = request.getParameter("yyOpenFrom");
				CommonFunctions.debugMsg("PILLAR : "+yyID);
				PrintWriter out = response.getWriter();
				List<String []> rootCauseList = new ArrayList();
				if(UIUtils.isValidKeyId(yyID))
				{
					rootCauseList  = yyService.getPillar(yyID,pillarFlag);
					//rootCauseList.get(0)[0]
					rootCauseList = fillPillarDatas(rootCauseList);
				}
				else
				{
					rootCauseList = fillPillar(rootCauseList,pillarFlag);
				}
				JSONObject rcData = UIUtils.convertToJqGridTableObject(rootCauseList,request,0,0);	  
				CommonFunctions.debugMsg(""+rcData);
  			 	out.println(rcData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("whywhyanalysismodify_delete.balwhy"))     //delete
		{	
			CommonFunctions.debugMsg("delete");
			PrintWriter out = response.getWriter();
			String yyToDel = request.getParameter("keyid");
			String yyRefdoc = request.getParameter("refdocid");

			CommonFunctions.debugMsg("delete1=="+yyToDel);
		//String  rowId = request.getParameter("rowId");
			try{
			if(UIUtils.isValidKeyId(yyToDel))
			{
				CommonFunctions.debugMsg("delete2");
				BAL_BdmTlWhywhymst bdmTlWhywhymst =  new BAL_BdmTlWhywhymst();
				bdmTlWhywhymst.setWwmsKeyid(yyToDel);
				bdmTlWhywhymst.setWwmsRefdocno(yyRefdoc);
				//BdmTlWhywhydtl bdmTlWhywhydtl =  new BdmTlWhywhydtl();
				
				//BdmTlWhywhydtl bdmTlWhywhydtl = yyService.deleteYYDtl(yyToDel);
			bdmTlWhywhymst = yyService.delete(bdmTlWhywhymst);
				JSONObject returnData = new JSONObject();
				returnData.put("msg", "Deleted Successfully");
				//returnData.put("rowId", rowId);
				
				out.print(returnData.toString());
				//bdmTlWhywhydtl = null;
				bdmTlWhywhymst = null;
			}
			}
			catch(BusinessApplicationExceptions e)
			{
				net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"WhyValidation");				
				out.print(errMessage.toString());
				
			}
		}
		else if( action.equals("whywhyanalysismodify_save.balwhy"))
		{	
			CommonFunctions.debugMsg("Inside YY Save");
			YYFormBean yyFormBean = new YYFormBean();
			saveYY(request,response,yyFormBean);	
			CommonFunctions.debugMsg("Inside xx Save");
		}
		
		else if( action.equals("doneByLink_save.balwhy"))
		{	
			CommonFunctions.debugMsg("Inside doneByLink_save.balwhy");
			YYFormBean yyFormBean = new YYFormBean();
			saveDonebyLink(request,response,yyFormBean);
			
		}
		
		else if(action.equals("YYDoneBy_delete.balwhy")){
			deleteYYDoneBy(request,response);
		}
		
		else if (action.equals("whywhyEffectiveness_save.balwhy"))
		{
			saveWhyWhyEffectiveness(request,response);
		}
		else if( action.equals("whywhyAna_save.balwhy"))    //TTTTTTTTTTTTT
		{	
			CommonFunctions.debugMsg("Inside YY Save");
			YYFormBean yyFormBean = new YYFormBean();
			//savewhy(request,response,yyFormBean);	
		}
		else if( action.equals("WhyWhy_delete.balwhy"))
		{	
			PrintWriter out = response.getWriter();
			String yyToDel = request.getParameter("keyid");
			CommonFunctions.debugMsg("delete1=="+yyToDel);
			String  rowId = request.getParameter("rowId");
			if(UIUtils.isValidKeyId(yyToDel))
			{
				BAL_BdmTlWhywhydtl bdmTlWhywhydtl =  new BAL_BdmTlWhywhydtl();
				bdmTlWhywhydtl = yyService.deleteYYDtl(yyToDel);
				JSONObject returnData = new JSONObject();
				returnData.put("msg", "Deleted Successfully");
				returnData.put("formClear",true);
				returnData.put("rowId", rowId);
				out.print(returnData.toString());
			}
		}
		else if( action.equals("functionalLoc.balwhy"))
		{
			HttpSession httpSession = request.getSession(false);
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbwwmsFactoryid");
			functLocFieldNameBean.setSection("cmbwwmsSectionid");
			functLocFieldNameBean.setCell("cmbwwmsCellid");
			functLocFieldNameBean.setMachine("cmbwwmsMachineid");
			  functLocFieldNameBean.setFunctionalLocId("txtWwmsFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);    //JH
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
			
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		}
		
		else if( action.equals("combo_yy.balwhy"))
		{
			try {
				ComboFilter comboFilter = new ComboFilter();
					CommonFunctions.debugMsg("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
					comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox>  yy = whywhyService.getYY("",comboFilter);
				UIUtils.writeComboBox(response, yy,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (action.equals("whywhyanalysisgrid_getCol.balwhy")) {
	try {
		
		
				PrintWriter out = response.getWriter();
				String masdetkeyid=request.getParameter("masterkeyid");
				//String detkeyid=request.getParameter("detailkeyid");
			
				List<String[]> analysis = whywhyService.getanalysis(masdetkeyid);
				JSONObject jsonObject = getTableModelAnalyis(analysis);
		       out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (action.equals("whywhyanalysisgrid_getData.balwhy")) {     //Analysis
			try {
				PrintWriter out = response.getWriter();
				String masdetkeyid=request.getParameter("masterkeyid");
				//String detkeyid=request.getParameter("detailkeyid");
				List<String[]> analysis = whywhyService.getanalysis(masdetkeyid);
				JSONObject analysisJson= UIUtils.convertToJqGridTableObject(analysis, request, 1, 0);
				out.println(analysisJson);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		else if (action.equals("whywhyanalysismaingrid_getCol.balwhy")) {
			
			
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			
		httpSession.removeAttribute("whywhyanalysisCommonFilter");
		String recDocId = request.getParameter("refdocid");	
		CommonFilter commonFilter = populateCommonFilter(request,"whywhyanalysisCommonFilter",true);
		response.setContentType("text/html");
		if(UIUtils.isValidKeyId(recDocId))
			commonFilter.setRefdocid(recDocId);
		List<String[]> AbnDataList  = yyService.getAllWhywhy(commonFilter);
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();
		
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableButton(true);
		
		gridColModel.setHeaderNum(1);
		
		String [] colHeader = AbnDataList.get(2);			
		String [] colHeaderCond = AbnDataList.get(1);
		
		//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
		List<String[]> headers = new ArrayList<String[]>();
		//headers.add(colHeaderCond);
		headers.add(colHeader);
		
		JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		jsonObject.put("tableHeight", "83%%");
		jsonObject.put("tableWidth", "109%%");
		httpSession.setAttribute("ColModel", jsonObject);
		httpSession.setAttribute("whywhyanalysisCommonFilter", commonFilter);
		out.println(jsonObject);
		}
		else if (action.equals("whywhyanalysismaingrid_getData.balwhy")) {
			try {
				
				HttpSession httpSession = request.getSession(false);
				String recDocId = request.getParameter("refdocid");	
				CommonFilter commonFilter =  populateCommonFilter(request,"whywhyanalysisCommonFilter",false);
				if(UIUtils.isValidKeyId(recDocId))
					commonFilter.setRefdocid(recDocId);
				  List<String []> WhyReportList  =  yyService.getAllWhywhy(commonFilter);
				PrintWriter out = response.getWriter();
				
				JSONObject Whymaster = UIUtils.convertToJqGridTableObject(WhyReportList, request, 3, 0,commonFilter.getTotalRecordCnt());
				httpSession.removeAttribute("whywhyanalysisCommonFilter");
				out.println(Whymaster );
				System.out.println(Whymaster);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		else if (action.equals("whywhyproposedgrid_getCol.balwhy")) {       //counter measure
			try {
				PrintWriter out = response.getWriter();
				String yyid = request.getParameter("masterkeyid");
				CommonFilter commonFilter =  populateCommonFilter(request,"whywhyanalysisCommonFilter",true);
				commonFilter.setYyNo(yyid);
				List<String[]> Proposed = whywhyService.getProposed(commonFilter);
				JSONObject jsonObject = getTableModelProposed(Proposed);
			    out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (action.equals("whywhyproposedgrid_getData.balwhy")) {
			try {
				PrintWriter out = response.getWriter();
				String yyid = request.getParameter("masterkeyid");
				CommonFilter commonFilter =  populateCommonFilter(request,"whywhyanalysisCommonFilter",false);
				commonFilter.setYyNo(yyid);
				List<String[]> Proposed = whywhyService.getProposed(commonFilter);
				JSONObject ProposedJson= UIUtils.convertToJqGridTableObject(Proposed, request, 2, 0);
				out.println(ProposedJson);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		else if( action.equals("whywhyReport_getExcel.balwhy"))
		{	
			CommonFunctions.debugMsg("1111111111111111list      :::: 111");
			HttpSession httpSession = request.getSession(false);
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("whywhyCommonFilter");
			
			List<String[]> whywhyQueryList  =   whywhyService.getAllwhywhyStd(commonFilter);
			String fileName= "whywhyRpt.xls";
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.whywhyRpt", "whywhyRpt");
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			//excelUtils.writeToExcel(request, response, fileName, whywhyQueryList,(short)0,(short)1,(short)0);
						
		} 
		else if( action.equals("whywhyReport_view.balwhy") )/*Created By Siddharth.A*/ 
		{
			try{
				String rowId = request.getParameter("rowId");
			 	String format = ExcelUtils.getFormat(request);
				String path = UIUtils.getExcelTemplatePath(request);  	
				String imagePath = UIUtils.getImagePath(request);
				format = "xlsx";
				Workbook wb = whywhyService.getAllwhywhyRptExl(rowId,format,path,imagePath);
				ExcelUtils.writeToResponse(response, wb, "WhyWhyReport",format );
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
		else if( action.equals("WhyWhyTrainingList_input.balwhy")){
			String flid = request.getParameter("flid");
			request.setAttribute("flid", flid);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/bal_WhyWhyTrainingList.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("WhyWhyTrainingList_view.balwhy")){
			
		}
		else if( action.equals("WhyWhyTrainingList_getCol.balwhy") )
		{	
			System.out.println("WhyWhyTrainingList_getCol.balwhy");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();			
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "ProgramColModel"));
						
		}
		else if( action.equals("WhyWhyTrainingList_getData.balwhy") )
		{
			try {
				System.out.println("WhyWhyTrainingList_getData.balwhy");
				PrintWriter out = response.getWriter();
				{					
					String rows = request.getParameter("rows");
		        	String page = request.getParameter("page");		           
	       		    String start = "1";
	       		    String end = "100";
	       		    if(page.equals("1"))
	       		    {       		    	
	       		    }
	       		    else
	       		    {
	       		     int rowStart = (Integer.parseInt(rows)*Integer.parseInt(page))-99;
	        		 int rowEnd = Integer.parseInt(rows)*Integer.parseInt(page);
	        		 start = Integer.toString(rowStart);
	        		 end = Integer.toString(rowEnd);
	       		    }
					//CommonFunctions.debugMsg("FORMFLD : "+elemFld);
					String totalCount = "5";//entTlTrainingareaService.getTotalCount(entTlTrainingarea );
					int totalRows = 200;
					
					List<String []> childForParent = yyService.getProgramList(start, end);
					if(UIUtils.isValidKeyId(totalCount))
						totalRows = Integer.parseInt(totalCount);
					
					net.sf.json.JSONObject childForParentData = UIUtils.convertToJqGridTableObject(childForParent,request,0,0,totalRows);
					CommonFunctions.debugMsg("childForParentData:"+childForParentData);
					out.println(childForParentData);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( action.equals("WhyWhyTraining_input.balwhy")){
			RequestDispatcher rd = request.getRequestDispatcher("/pages/bal_WhyWhyTrainingPopup.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("whywhyQtyReport_view.balwhy") )/*Created By Siddharth.A*/ 
		{
			try{
				String rowId = request.getParameter("rowId");
			 	String format = ExcelUtils.getFormat(request);
				String path = UIUtils.getExcelTemplatePath(request);  	
				String imagePath = UIUtils.getImagePath(request);
				format = "xlsx";
				Workbook wb = whywhyService.getAllwhywhyQtyRptExl(rowId,format,path,imagePath);
				
	 
				ExcelUtils.writeToResponse(response, wb, "WhyWhyReport",format );
				}
			
				catch(Exception e)
				{
				e.printStackTrace();
					System.out.println("err:"+e.getMessage());
					PrintWriter out = response.getWriter();
					JSONObject err = new JSONObject();				
					//err.put("exception",true);				
					err.put("message" ,"Data Not Found" );
					out.print(err.toString());
				}
				
				
		}else if( action.equals("filterXmlwhywhyRptGenDrill_view.balwhy")){
			response.setContentType("xml");		
			CommonFunctions.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyAnalysis.xml");
		 }else if(action.startsWith("whywhyRptGenDrill") ) 
		{
			whywhyIdentifiedDrillDownReport( request, response );
		}
			
			
	}
	private void saveWhyWhyEffectiveness(HttpServletRequest request,HttpServletResponse response) throws Exception {
			
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
    	CommonFunctions.debugMsg("yyService"+yyService);
    	
    	if( httpSession != null && user != null)
    	{	
    		BAL_BdmTlYyeffectivemst existBdmTlYyeffectivemst = (BAL_BdmTlYyeffectivemst)httpSession.getAttribute("BdmTlWhywhyMst"); 
    		 
    		BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemst = new BAL_BdmTlYyeffectivemst();     		
    		newBdmTlYyeffectivemst.setYyefCreatedby(user.getUsrm_ccno());    	  		
    	
    		newBdmTlYyeffectivemst =(BAL_BdmTlYyeffectivemst)UIUtils.setBeanProperties((Object)newBdmTlYyeffectivemst,request);
    		
    /*		if(request.getParameter("gridData")== null || request.getParameter("gridData").equals(""))
    		{
	    		CommonFunctions.debugMsg("YY Grid : "+request.getParameter("YYAnalysis"));
    		}
    		else
    		{*/
    			CommonFunctions.debugMsg("YY Grid = "+request.getParameter("gridData"));
	    		String yyGrid = request.getParameter("gridData");	
	    		
		    	JSONArray jsonArray = JSONArray.fromString(yyGrid);		  
		    	BAL_BdmTlYyeffectivedtl newBdmTlYyeffectivedtl = new BAL_BdmTlYyeffectivedtl();
		    	newBdmTlYyeffectivedtl.setYyedCreatedby(user.getUsrm_ccno());	 
		    	List<BAL_BdmTlYyeffectivedtl> yyDetail = (List<BAL_BdmTlYyeffectivedtl>) UIUtils.convertJSONArrToList(newBdmTlYyeffectivedtl, jsonArray);
		    	List<BAL_BdmTlYyeffectivemst> yyMst = (List<BAL_BdmTlYyeffectivemst>) UIUtils.convertJSONArrToList(newBdmTlYyeffectivemst, jsonArray);
		    	
		    	if( newBdmTlYyeffectivedtl != null) {
		    		newBdmTlYyeffectivemst.setBdmTlYyeffectivedtl(yyDetail);
		    		newBdmTlYyeffectivemst.setBdmTlYyeffectivemst(yyMst);
		    	}
    		//}
    	
	    	       CommonFunctions.debugMsg(" Checking value save :: "+newBdmTlYyeffectivemst.getYyefKeyid());
	    	
    		try{
    			if(!UIUtils.isValidKeyId (newBdmTlYyeffectivemst.getYyefKeyid() )  )
				{	
					CommonFunctions.debugMsg("create");
					existBdmTlYyeffectivemst =	yyService.yyEffectivenessCreate(newBdmTlYyeffectivemst,existBdmTlYyeffectivemst);
				}	
				else
				{
					CommonFunctions.debugMsg("update");
					existBdmTlYyeffectivemst =	yyService.yyEffectivenessUpdate(newBdmTlYyeffectivemst,existBdmTlYyeffectivemst);						
				}
				
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("cmbWwmsKeyid",existBdmTlYyeffectivemst.getYyefKeyid());
				String formType = (String) httpSession.getAttribute("WHYWHYFORMTYPE");
				if(UIUtils.isValidKeyId(formType))
					persistentData.put("txtFormType",formType);
				//existBdmTlWhywhymst.getWwmsFormtype()
				JSONObject forwardData = new JSONObject();
				
			
				CommonFunctions.debugMsg(" Form Mode");
				//httpSession.setAttribute("wwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
				successData.put("keyId", existBdmTlYyeffectivemst.getYyefKeyid());
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);		
				CommonFunctions.debugMsg(" returnData.toString() "+returnData.toString());
				out.print(returnData.toString());
			
    		}
    		catch(BusinessApplicationExceptions e)
			{
				//net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"WhyValidation");				
				//out.print(errMessage.toString());
			}
			catch(ValidationExceptions e)
			{				
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"WhyValidation");
				errMessage.put("tpmException", "Data Not Saved");
				out.print(errMessage.toString());			
			}
			
	    	  
    	}
		
	}
	
	 private void deleteYYDoneBy(HttpServletRequest request,HttpServletResponse response) throws Exception,BusinessApplicationExceptions {
	    	PrintWriter out = response.getWriter();
	    	 String keyId = request.getParameter("keyid");
			try{
			 String delSuc = yyService.deleteYYDoneBy(keyId);
			 JSONObject json = new JSONObject();
			 json.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				 out.print(json.toString());
			}catch(BusinessApplicationExceptions e)
				{
					
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
					JSONObject returnData = new JSONObject();
					returnData.put("formClear",false);
					returnData.put("msg"," Record Can't be Deleted Reference Found");
					out.print(returnData.toString());
				}
			
		}
	
	private void saveDonebyLink(HttpServletRequest request,HttpServletResponse response, YYFormBean yyFormBean) throws Exception {
		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
    	CommonFunctions.debugMsg("yyService"+yyService);
    	
    	if( httpSession != null && user != null)
    	{	
    		
    		BAL_BdmTlYydonebymst existBdmTlYydonebymst = (BAL_BdmTlYydonebymst)httpSession.getAttribute("BdmTlWhywhyMst"); 
    		 
    		BAL_BdmTlYydonebymst newBdmTlYydonebymst = new BAL_BdmTlYydonebymst();     		
    		newBdmTlYydonebymst =(BAL_BdmTlYydonebymst)UIUtils.setBeanProperties((Object)newBdmTlYydonebymst,request);
    		newBdmTlYydonebymst.setWwdbCreatedby(user.getUsrm_ccno());    	  		
    		
    		String yyKeyid = request.getParameter("yyKeyid");
    		String yyDonebyid = request.getParameter("yyDonebyid");
	    		   
    		if (UIUtils.isValidKeyId(yyDonebyid))
    			newBdmTlYydonebymst.setWwdbEmpmKeyid(yyDonebyid);
    		if (UIUtils.isValidKeyId(yyKeyid))
    			newBdmTlYydonebymst.setWwdbWwmsKeyid(yyKeyid);
    		
    		try{
    			if(!UIUtils.isValidKeyId (newBdmTlYydonebymst.getWwdbKeyid() )  )
				{	
					CommonFunctions.debugMsg("create");
					existBdmTlYydonebymst =	yyService.yyDonebyCreate(newBdmTlYydonebymst,existBdmTlYydonebymst);
				}	
				
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("cmbWwmsKeyid",existBdmTlYydonebymst.getWwdbKeyid());
				//existBdmTlWhywhymst.getWwmsFormtype()
				JSONObject forwardData = new JSONObject();
				
			
				CommonFunctions.debugMsg(" Form Mode");
				//httpSession.setAttribute("wwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
				successData.put("keyId", existBdmTlYydonebymst.getWwdbKeyid());
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);		
				CommonFunctions.debugMsg(" returnData.toString() "+returnData.toString());
				out.print(returnData.toString());
			
	    }
		catch(ValidationExceptions e)
		{
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "WhyValidation");
			out.print(errMessage.toString());
		
		}
		catch(BusinessApplicationExceptions e)
		{   
			
			System.out.println("Error Servler e -"+e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "WhyValidation");
			out.print(errMessage.toString());
			System.out.println(" e " + errMessage );
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
	
	private JSONObject getTableModel(List<String[]> whyReportList,
			CommonFilter commonFilter) {
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = whyReportList .get(0);	
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);	
		jqGridTableModel.setEnableFilter(true);
		
		
		for(int i =0; i <colHeader.length; i++)
		{			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
					
			jqGridColModel.setWidth( 100);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			CommonFunctions.debugMsg("colheader  "+i+" : "+colHeader[i]);//if i==0
			if(i==5 ||i==2||i==3||i==4){
			 
				jqGridColModel.setWidth(160);
				
			
		}if(i==1)
		{
			jqGridColModel.setHidden(true);
		}
			
	
		
		jqGridTableModel.getColModel().add(jqGridColModel);
	}

	 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 return tableModel;
		
		
	
	}

	private void savewhy(HttpServletRequest request,
			HttpServletResponse response, YYFormBean yyFormBean) throws Exception {
		
		
         System.out.println("nbj");
		
		CommonFunctions.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null)
				{	
				
				
				BAL_BdmTlWhywhymst newBdmTlWhywhymst = new BAL_BdmTlWhywhymst();     	//model
				newBdmTlWhywhymst =(BAL_BdmTlWhywhymst)UIUtils.setBeanProperties((Object)newBdmTlWhywhymst,request);//master
					BAL_BdmTlWhywhymst existBdmTlWhywhymst = (BAL_BdmTlWhywhymst)httpSession.getAttribute("newBdmTlWhywhymst");
				 	String savemsg;
						boolean insert = true;
					
						if( ! UIUtils.isValidKeyId (newBdmTlWhywhymst.getWwmsKeyid() )  )//NOT NULL CRETTE
						{	
							newBdmTlWhywhymst  =yyService.create1(newBdmTlWhywhymst ,existBdmTlWhywhymst,yyFormBean);
							 savemsg="Data Saved Successfully";
						}
						else{
							insert = false;
							existBdmTlWhywhymst =yyService.update1(newBdmTlWhywhymst ,existBdmTlWhywhymst,yyFormBean);
							savemsg="Data Updated Successfully";
						}
			
			JSONObject successData = new JSONObject();
			successData.put("msg",savemsg);
			JSONObject returnData = new JSONObject();//
			returnData.put("successData", successData);
			returnData.put("WwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
			returnData.put("formClear", false);
			out.print(returnData.toString());//
			out.close();
				}
			
			}
		catch(ValidationExceptions e){
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"QtmstValidation");
				//errMessage.put("formActionMode",StudentMasterBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}

		}


	private JSONObject getTableModel_YYEffectiveness(
			List<String[]> yYEffectiveList) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();

		String[] colHeader = yYEffectiveList.get(0);
		String[] emptyrow = new String[colHeader.length]; 	
		for( int i = 0; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		 
		 
		for(int i =0; i < colHeader.length; i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
		
			jqGridColModel.setWidth(100);				
			jqGridColModel.setEditable(false);
			if(i==0){
				jqGridColModel.setWidth(80);
				jqGridColModel.setFormatter("checkFormatter");
			}
			if(i==1)
				jqGridColModel.setFormatter("dateFormatter");
			if(i==2)
			{
				jqGridColModel.setFormatter("comboFormatter");
				jqGridColModel.setWidth(250);
			}
			if(i==7)
				jqGridColModel.setWidth(200);
			if(i==9)
				jqGridColModel.setWidth(300);
			if(i==10)
				jqGridColModel.setFormatter("buttonFormatter");
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}

	public static List<String[]> setRootCauseDatas()
	{
		List<String []> rootCauseList = new ArrayList<String[]>();
		List<String> fieldNames=  new ArrayList();
		fieldNames.add("POOR BASIC CONDITION");
		fieldNames.add("POOR OPERATING CONDITION");
		fieldNames.add("DETERIORATION");
		fieldNames.add("WEAK DESIGN");
		fieldNames.add("POOR SKILL");
		int j=0;
		while(j<5)
		{
			String[] rootCause = new String[ 6 ];
			for(int i = 0;i<rootCause.length;i++)
			{
				if(i==1)
				{
					rootCause[i] = fieldNames.get(j);
					
				}
				else
					rootCause[i] = "{}";
			}
			rootCauseList.add(rootCause);
			j++;
		}
		return rootCauseList;
	}
	
	public static List<String[]> fillPillarDatas(List<String []> pillarDatas)
	{
		List<String []> rootCauseList = new ArrayList<String[]>();
	
		for(String[] pillar:pillarDatas)
		{
			int k=0;
			String[] fieldName = new String[pillarDatas.get(k).length];
			for(int i =0;i<pillar.length;i++)
			{
				if(i==0)
					fieldName[i] = pillar[i].replace("JHN", "JISHU HOZEN").replace("OPL", "OPL NO.").replace("KZN", "KAIZEN IDEA NO.").replace("PMC", "PM CALENDAR").replace("POK", "POKA YOKE").replace("4MT", "4M TYPE").replace("TRN", "TRAINING");
				else
					fieldName[i] = pillar[i];
			}
			//rootCauseList.set(k, pillar);
			rootCauseList.add(fieldName);
			k++;
			
		}
		
		return rootCauseList;
	}
	public static List<String[]> fillPillar(List<String []> pillarDatas,String pillarFlag)
	{
		List<String []> rootCauseList = new ArrayList<String[]>();
		
		List<String> fieldNames=  new ArrayList();
		if(UIUtils.isValidKeyId(pillarFlag))
		{
			if(pillarFlag.equals("SHE") || pillarFlag.equals("DOCK"))
			{
				fieldNames.add("KAIZEN");	
				fieldNames.add("OJT");
				fieldNames.add("OPL");
				if(pillarFlag.equals("SHE"))
				  fieldNames.add("POKA YOKE");
				else
				  fieldNames.add("SOP");
				fieldNames.add("TRAINING");
			}
			else
			{
				if(pillarFlag.equals("CC") || pillarFlag.equals("IMT"))
					fieldNames.add("4M TYPE");	
				fieldNames.add("JISHU HOZEN");	
				fieldNames.add("KAIZEN IDEA NO.");
				fieldNames.add("OPL NO.");
				fieldNames.add("PM CALENDAR");
				fieldNames.add("TRAINING");
			}
		}
		else
		{
			fieldNames.add("JISHU HOZEN");	
			fieldNames.add("KAIZEN IDEA NO.");
			fieldNames.add("OPL NO.");
			fieldNames.add("PM CALENDAR");
			fieldNames.add("TRAINING");
		}
			
		int j=0;
		while(j<fieldNames.size())
		{
			String[] rootCause = new String[ 3 ];
			for(int i = 0;i<rootCause.length;i++)
			{
				if(i==0)
					rootCause[i] = fieldNames.get(j);
				else if(i==1)
					rootCause[i] = "{}";
				else
					rootCause[i] = "";
				
				CommonFunctions.debugMsg(i + " : "+rootCause[i]);
			}
			rootCauseList.add(rootCause);
			j++;
		}
		return rootCauseList;
	}
	
	private void saveYY(HttpServletRequest request,HttpServletResponse response, YYFormBean yyFormBean) throws BusinessApplicationExceptions,Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
    	String counterMeasure = request.getParameter("hdnCounterMeasure");
    	
    	if( httpSession != null && user != null)
    	{	
    		BAL_BdmTlWhywhymst existBdmTlWhywhymst = (BAL_BdmTlWhywhymst)httpSession.getAttribute("BdmTlWhywhyMst"); 
    		 
    		BAL_BdmTlWhywhymst newBdmTlWhywhymst = new BAL_BdmTlWhywhymst();     		
    		   	  		
    	
    		newBdmTlWhywhymst =(BAL_BdmTlWhywhymst)UIUtils.setBeanProperties((Object)newBdmTlWhywhymst,request);
    		newBdmTlWhywhymst.setWwmsCreatedby(user.getUsrm_ccno()); 
    		if(request.getParameter("YYAnalysis")== null || request.getParameter("YYAnalysis").equals(""))
    		{
	    		CommonFunctions.debugMsg("YY Grid : "+request.getParameter("YYAnalysis"));
    		}
    		else
    		{
    			String yyGrid = request.getParameter("YYAnalysis");	
	    		
		    	JSONArray jsonArray = JSONArray.fromString(yyGrid);
		    	BAL_BdmTlWhywhydtl newBdmTlWhywhydtl = new BAL_BdmTlWhywhydtl();
		    	newBdmTlWhywhydtl.setWwdtCreatedby(user.getUsrm_ccno());	 
		    	List<BAL_BdmTlWhywhydtl> yyDetail = (List<BAL_BdmTlWhywhydtl>) UIUtils.convertJSONArrToList(newBdmTlWhywhydtl, jsonArray);
		    	if( newBdmTlWhywhydtl != null) {
		    		newBdmTlWhywhymst.setBdmTlWhywhydtl(yyDetail);
		    	}
    		}
    	
	    	
	    	yyFormBean =(YYFormBean) UIUtils.setBeanProperties((Object)yyFormBean,request);
    		try{
    			if(!UIUtils.isValidKeyId (newBdmTlWhywhymst.getWwmsKeyid() )  )
				{	
					existBdmTlWhywhymst =	yyService.create(newBdmTlWhywhymst,existBdmTlWhywhymst,yyFormBean);
					CommonFunctions.debugMsg("CREATE METHOD");
				}	
				else
				{
					existBdmTlWhywhymst =	yyService.update(newBdmTlWhywhymst,existBdmTlWhywhymst,yyFormBean);		
					CommonFunctions.debugMsg("UPDATE METHOD");

				}
				
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("cmbWwmsKeyid",existBdmTlWhywhymst.getWwmsKeyid());
				String formType = (String) httpSession.getAttribute("WHYWHYFORMTYPE");
				if(UIUtils.isValidKeyId(formType))
					persistentData.put("txtFormType",formType);
				//existBdmTlWhywhymst.getWwmsFormtype()
				JSONObject forwardData = new JSONObject();
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsFactoryid()))
					forwardData.put("factoryId",existBdmTlWhywhymst.getWwmsFactoryid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsSectionid()))
					forwardData.put("sectionId",existBdmTlWhywhymst.getWwmsSectionid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsCellid()))
					forwardData.put("cellId",existBdmTlWhywhymst.getWwmsCellid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsMachineid()))
					forwardData.put("machineID",existBdmTlWhywhymst.getWwmsMachineid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsAssemblyid()))
					forwardData.put("assemblyId",existBdmTlWhywhymst.getWwmsAssemblyid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsKeyid()))
					forwardData.put("keyid",existBdmTlWhywhymst.getWwmsKeyid());
				if(UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsRefdocno()))
					forwardData.put("bdId",existBdmTlWhywhymst.getWwmsRefdocno());
				String getDocId = yyService.getDocId(existBdmTlWhywhymst.getWwmsRefdocno());
				CommonFunctions.debugMsg("doccccccccccccccccccccccccccccccc "+getDocId);
				
				
				if(UIUtils.isValidKeyId(getDocId))
					forwardData.put("docId",getDocId);
				//forwardData.put("bdmmode","bdmmode");
				
				
				CommonFunctions.debugMsg("RootCause JH: "+existBdmTlWhywhymst.getWwmsIsjh());
				CommonFunctions.debugMsg("RootCause PM: "+existBdmTlWhywhymst.getWwmsIspm());
				if(UIUtils.isValidKeyId(yyFormBean.getFormActionMode()))
				{
					String formBeanIdentifier = "YYFormBean"+yyFormBean.getFormActionMode();
					CommonFunctions.debugMsg("formBeanIdentifier  "+formBeanIdentifier+"  formBeanIdentifier  "+formBeanIdentifier);
					httpSession.setAttribute(formBeanIdentifier,yyFormBean);
				}
				CommonFunctions.debugMsg(" Form Mode");
				httpSession.setAttribute("wwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
				successData.put("keyId", existBdmTlWhywhymst.getWwmsKeyid());
				JSONObject returnData = new JSONObject();
				if(existBdmTlWhywhymst.getWwmsIskk().equals("Y"))
					returnData.put("OpenForm","Design");
				else if(existBdmTlWhywhymst.getWwmsIsopl().equals("Y"))
					returnData.put("OpenForm","ET");
				else if(existBdmTlWhywhymst.getWwmsIsjh().equals("Y"))
					returnData.put("OpenForm","JH");
				else if(existBdmTlWhywhymst.getWwmsIspm().equals("Y"))
					returnData.put("OpenForm","PM");
				else if(existBdmTlWhywhymst.getWwmsIspy().equals("Y"))
					returnData.put("OpenForm","PY");
				else if(existBdmTlWhywhymst.getWwmsIsojt().equals("Y"))
					returnData.put("OpenForm","OJ");
				else if(existBdmTlWhywhymst.getWwmsIssop().equals("Y"))
					returnData.put("OpenForm","SO");
				else{
					returnData.put("OpenForm","TRN");
					CommonFunctions.debugMsg("ELSE ");
				}
				returnData.put("formClear",false);
				returnData.put("formMode",yyFormBean.getFormActionMode());
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);
				returnData.put("counterMeasure", counterMeasure);
				CommonFunctions.debugMsg(" returnData.toString() "+returnData.toString());
				out.print(returnData.toString());
			
    		}
    		catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("BusinessApplicationExceptions......");
    			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"WhyValidation");				
				out.print(errMessage.toString());
			}
			catch(ValidationExceptions e)
			{				
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"WhyValidation");
				errMessage.put("tpmException", "Data Not Saved");
				out.print(errMessage.toString());			
			}
    		/*catch(Exception e)
			{
    			CommonFunctions.debugMsg("test exception12");
    			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"WhyValidation");				
				out.print(errMessage.toString());
			}*/
	    	  
    	}
    	
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			if(beanIdentifier.equals("whywhyQtyCommonFilter"))
				commonFilter = 	FilterValues.getQuality(request, commonFilter);
			else			
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  } 

		
		return commonFilter;
	}
	
	
	private void fillYY(BAL_BdmTlWhywhymst bdmTlWhywhymst,WomTlWomst womTlWomst)
	{
		if(UIUtils.isValidKeyId(womTlWomst.getWomsActivityid()))
			bdmTlWhywhymst.setWwmsRefdocno(womTlWomst.getWomsActivityid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsOccurreddate()))
			bdmTlWhywhymst.setWwmsDate(womTlWomst.getWomsOccurreddate());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMachineid()))
			bdmTlWhywhymst.setWwmsMachineid(womTlWomst.getWomsMachineid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAssemblyid()))
			bdmTlWhywhymst.setWwmsAssemblyid(womTlWomst.getWomsAssemblyid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsFactoryid()))
			bdmTlWhywhymst.setWwmsFactoryid(womTlWomst.getWomsFactoryid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCellid()))
			bdmTlWhywhymst.setWwmsCellid(womTlWomst.getWomsCellid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSectionid()))
			bdmTlWhywhymst.setWwmsSectionid(womTlWomst.getWomsSectionid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsPhenomenaid()))
			bdmTlWhywhymst.setWwmsPhenomenaid(womTlWomst.getWomsPhenomenaid());
		//if(UIUtils.isValidKeyId(womTlWomst.getWomsCauseid()))
			//bdmTlWhywhymst.setwwms;
		if(UIUtils.isValidKeyId(womTlWomst.getWomsReportedby()))
			bdmTlWhywhymst.setWwmsMaintinchargeid(womTlWomst.getWomsReportedby());
		//bdmTlWhywhymst..setwwmss
				
	}
	
	private JSONObject getTableModel(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] row = headers.get(0);
		String [] colHeader1 = headers.get(1);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setTableButton(true);		 
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		String headerSql = "'SELECT ";  
		for(int i=0;i<row.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
			jqGridColModel.setName(row[i].replaceAll(" ", ""));
			jqGridColModel.setEditable(false);
			CommonFunctions.debugMsg(i + " : "+row[i]);
			if(i==0 || i==1)
				jqGridColModel.setHidden(true);
			else if(i == 2 || i == 3)
				jqGridColModel.setWidth(100);
			
			else if (i==4 )
			{
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			}
			else if(i == 5)
				jqGridColModel.setWidth(300);
			
			else if(i == 6)
				jqGridColModel.setWidth(100);
			else if(i == 11 || i == 12 || i == 13)
				jqGridColModel.setWidth(100);
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL '";
		CommonFunctions.debugMsg("headerSql.....123..."+headerSql);
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		CommonFunctions.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	private JSONObject getTableModelAnalyis(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader1 = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setLoadOnce(true);
		//jqGridTableModel.setMultiSelect(false);
		for(int i=0;i<colHeader1.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setEditable(false);
			jqGridColModel.setAlign("left");
		
			if(i==0 ||i==1)
			{				
				jqGridColModel.setHidden(true);
			}
			if(i==2)
			{				
				jqGridColModel.setWidth(550);
				jqGridColModel.setFormatter("txtFormatter");
			}
			if(i==3)
			{				
				jqGridColModel.setWidth(50);
				jqGridColModel.setFormatter("actionFormatterDel");
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth", "60%%");
		CommonFunctions.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	private JSONObject getTableModelMainGrid(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader1 = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		for(int i=0;i<colHeader1.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setEditable(false);
			jqGridColModel.setAlign("left");
			jqGridColModel.setWidth(100);
			if(i==1 || i==2)
				jqGridColModel.setWidth(165);
			if(i==3 || i==4 || i==5 || i==6)
				jqGridColModel.setWidth(185);
			if(i==0){
				jqGridColModel.setHidden(true);
				
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		CommonFunctions.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	private JSONObject getTableModelProposed(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String[] row = headers.get(2);
		String [] colHeader1 = headers.get(1);
		String [] colHeader0= headers.get(0);
		CommonFunctions.debugMsg(colHeader1   + "..................");
		String[] tempCol = new String[row.length];
		jqGridTableModel.getRowHeaders().add(tempCol);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		for(int i=0;i<colHeader1.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(tempCol[i] = "");
			jqGridColModel.setIndex(tempCol[i].replaceAll(" ", ""));
			jqGridColModel.setName(tempCol[i].replaceAll(" ", ""));
			jqGridColModel.setIndex(colHeader0[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader0[i].replaceAll(" ", ""));
			jqGridColModel.setEditable(false);
			jqGridColModel.setAlign("left");
			jqGridColModel.setWidth(100);
			if(i==0 ){
				jqGridColModel.setWidth(252);
			}
			if(i==5 ){
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("center");
				jqGridColModel.setFormatter("txtFormatter1");
			}if(i==2 ){
				jqGridColModel.setWidth(100);
				jqGridColModel.setFormatter("txtFormatter1");
			}if(i==4){
				jqGridColModel.setWidth(75);
			}if(i==1 ){
				jqGridColModel.setWidth(157);
			}
			if(i== colHeader1.length-1){
				jqGridColModel.setHidden(true);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "34%%");
		tableModel.set("tableWidth", "54%%");

		return tableModel;
	}
	
	private void whywhyIdentifiedDrillDownReport(HttpServletRequest request,HttpServletResponse response ) throws Exception{
		
		String action = UIUtils.getActionPart(request);
		if( action.equals("whywhyRptGenDrill_view.balwhy")){
			
			UIUtils.forwardRequest(request, response, "/pages/Reports/bal_whywhygendrilldown.jsp");
		}
		else if( action.equals("whywhyRptGenDrill_getCol.balwhy")){
			buildTableColModel( request,response);
		}
		else if( action.equals("whywhyRptGenDrill_getData.balwhy")){
			
			whywhyGetData(request , response);
		}
		else if( action.equals("whywhyRptGenDrill_chart.balwhy"))
		{
			processChart(request,response);
		}
		else if(action.equals("whywhyRptGenDrill_getExcel.balwhy")){
			exportWhyWhyGenRptExcel(request,response);
		}
	}

	
	private void buildTableColModel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		String firstClick = request.getParameter("firstClick");

		CommonFilter commonFilter = null;
		if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
			 commonFilter = (CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		}
		
		if (commonFilter == null)
			commonFilter = new CommonFilter();
		
		commonFilter = populateCommonFilter(request,gendrillcommonfilter,true);
		
		httpSession.removeAttribute(gendrillcommonfilter);
		httpSession.setAttribute(gendrillcommonfilter, commonFilter);
		System.out.println(" Constants.passNullDate " + Constants.passNullDate + " commonFilter.getFromMonth() " +  commonFilter.getFromMonth());
		
		if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
		{
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			commonFilter.setMonwise("Y");
		}
	
		List<String[]> yyData = yyService.getWhyWhyGenDrillData(commonFilter);
		JSONObject jsonObject = getTableModel(yyData,FilterValues.getHeader(commonFilter.getDrillCaption()));
		jsonObject.set("tableHeight", "80%%");
	    jsonObject.set("tableWidth", "104%%");
		httpSession.removeAttribute("ImprovementColData");
		httpSession.setAttribute("ImprovementColData", jsonObject);
		
		out.println(jsonObject);
	} 
	
	
	private void whywhyGetData(HttpServletRequest request , HttpServletResponse response) throws Exception {

		
		CommonFilter commonFilter = populateCommonFilter(request,gendrillcommonfilter,false);
		List<String[]> impVscomList = yyService.getWhyWhyGenDrillData(commonFilter);
		
		JSONObject listToJsonObject = new JSONObject();

		if (impVscomList != null && impVscomList.size() > 1)
			listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 0,commonFilter.getTotalRecordCnt());
		
		PrintWriter out = response.getWriter();
		out.println(listToJsonObject);
		
	}
	
	private JSONObject getTableModel(List<String[]> headers,String caption)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		
		String [] colHeader = headers.get(0) ;
		int header = colHeader.length-1;
		String [] headerArr = new String[header];
		colHeader[2] = caption;  
		
		jqGridTableModel.setTableButton(true);	
		JqGridColModel jqGridColModel =getColModel("keyid1",50,"left");
		
		jqGridColModel.setHidden(true);

		jqGridTableModel.getColModel().add(jqGridColModel);
		jqGridTableModel.setRowNumbers(true);
		
		jqGridColModel = getColModel("keyid2",100,"left");
		jqGridTableModel.getColModel().add(jqGridColModel);
		headerArr[0] = "keyid1";
		headerArr[1] = "keyid2";
		headerArr[2] = "";
		
		jqGridColModel = getColModel("FLLOC",300,"left");
		jqGridColModel.setHidden(false);
		jqGridTableModel.getColModel().add(jqGridColModel);
		
		for(int i =3; i < header; i++)
		{
			headerArr[i] = colHeader[i].replaceAll(" ", "");
			jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(headerArr[i]+""+i);
			jqGridColModel.setName(headerArr[i]+""+i);
			jqGridColModel.setWidth(100);	
			jqGridColModel.setAlign("right");
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		jqGridTableModel.getRowHeaders().add(headerArr);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 	
		tableModel.set("tableHeight", "72%");
		return tableModel;
      }
	
	 private void exportWhyWhyGenRptExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//HttpSession httpSession = request.getSession(false);
		
	     CommonFilter commonFilter = populateCommonFilter(request,gendrillcommonfilter,false);
	    
		
		JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
		
		tblJSONObj.put("title", "Why Why Report");
		
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = yyService.getWhyWhyGenDrillDataExcel(commonFilter,tblJSONObj,format);
		
		
		ExcelUtils.writeToResponse(response, wb, "WhyWhyGenReport", format);
		
	}
	
	private JqGridColModel getColModel (String colIndex, int width,String allign)
	{
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setHidden(true);			
		jqGridColModel.setWidth( width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		return jqGridColModel;
	}

	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		
		String forDashboard = request.getParameter("dashboard");
		
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		}
		else{
			commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);		
		}
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		//FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		chartCommonFilter.setRowTotal('Y');
		
		List<String[]> whywhyList  = yyService.getWhyWhyGenDrillData(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(whywhyList != null && whywhyList.size() > 0)
		{	chartObj = processLineChart(whywhyList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	

	private JSONObject processLineChart(List<String[]> whywhyList,CommonFilter commonFilter){

		if( whywhyList == null || whywhyList.size() <= 1  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		//String drillLevel = FilterValues.getHeader(commonFilter.getDrillCaption());
		
	
		//String[] header =  whywhyList.get(0);
		String[] month =  whywhyList.get(0);
		String[] data =  whywhyList.get(whywhyList.size()-1);
		String prevMonth = null;
		
		String subTitle = data[2];
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		
	
		String drillLevel =  FilterValues.getDrillHeader(data[1]);
		StringBuilder title = new StringBuilder("WHY WHY Cumulative  ").append( drillLevel).append(" Level From ").append(date);
		
		//String title = "WHY WHY Cumulative  " +subTitle ;
		ChartSeries timeSeries = new ChartSeries();
		List<Double> cumulativeData = new ArrayList<Double>();
		
		for( int i = 3;i < month.length-1;i++ ){
				cumulativeData.add(Double.parseDouble(data[i]));
			
				if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(cumulativeData.size() > 0 )
		{
			timeSeries.setData(cumulativeData);
			timeSeries.setType(ChartTypes.SPLINE);
			timeSeries.setName("Cumulative");
			chartSeriesList.add(timeSeries);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("WHY WHY Cumulative Count");
			chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
		
	}
	
	
	private JSONObject getyyDonebyTableModel(List<String[]> yyDonebyList) {
		// TODO Auto-generated method stub
    	JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
		
		String[] colHeader = yyDonebyList.get(0);		
		 
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(false);
		jqGridTableModel.setTableHeight(290);  
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
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			if(i==colHeader.length-1){
				jqGridColModel.setFormatter("BtnFormatterDelete");
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			} 
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth", "33%%");
		return tableModel;

	}

}

				