package com.akranta.tpm.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import oracle.sql.DATE;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartLegend;
import com.akranta.tpm.bean.ChartPie;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.service.EquipmentDownTimeService;
import com.akranta.tpm.service.BAL_LossStratificationService;
//import com.akranta.tpm.service.impl.EquipmentDownTimeServiceImpl;
import com.akranta.tpm.service.impl.KpiTlIndicatorKkServiceImpl;
import com.akranta.tpm.service.impl.BAL_LossStratificationServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;


public class BAL_LossStratificationServlet extends HttpServlet {	
	/**
	 * Created By:N.Arun
	 */
	private static final long serialVersionUID = 1L;
	BAL_LossStratificationService lossStratificationService ; 
	public BAL_LossStratificationServlet(){
		/*try {
			lossStratificationService = new LossStratificationServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if( ! UIUtils.checkUserSession( request,response))
			return; 
			
			String dispatchUrl =null;
			
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		try {
			lossStratificationService = (BAL_LossStratificationServiceImpl)UIUtils.getServiceObject(request,"BAL_LossStratificationServiceImpl");
			HttpSession h = request.getSession(false);

			lossStratificationService.BAL_LossStratificationServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}

		if( action.equals("filterXmlLossStratification_input.ballosstr")){
			 response.setContentType("xml"); 
			System.out.println("action "+ action); 
			String allLosses="N";
			request.setAttribute("allLosses", allLosses);
			UIUtils.forwardRequest(request, response, "/tiles/xml/LossStratification.xml") ;
		 }
		else if(action.equals("LossStratification_input.ballosstr")) 
		{
			CommonFunctions.debugMsg("aaaaaaaaaaaaaaaaaaaaaaa");
			String filterString = request.getParameter("filterString");
			String allLosses = request.getParameter("allLoss");
			request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			request.setAttribute("lossstrgraph", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","lossstrgraph"));
			request.setAttribute("losssub", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","losssub"));
			request.setAttribute("filterStr", filterString);
			request.setAttribute("allLosses", allLosses);
			 CommonFunctions.debugMsg(allLosses + "FromDate   "+request.getParameter("dtFromDate"));
			//UIUtils.forwardRequest(request, response,"/tiles/extras.jsp"); 
			UIUtils.forwardRequest(request, response,"/pages/Reports/LossStratification.jsp"); 

		}
		else if(action.equals("LossStratification_getCol.ballosstr"))
		{
			CommonFunctions.debugMsg(" action  "+action);	
			 String firstClick =request.getParameter("firstClick");
			 CommonFilter commonFilter = populateCommonFilter(request,"lossStratification",( UIUtils.isValidKeyId(firstClick) || (firstClick!= null && ! firstClick.equals("Y") )),true);

			// CommonFilter commonFilter = populateCommonFilter(request,"lossStratification",true);
			// commonFilter = new CommonFilter(); 
			 //populateCommonFilter(request,"EquipmentDownTime",true);
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){
				 commonFilter =(CommonFilter) httpSession.getAttribute("lossStratification");
			 }	
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
			 getComboSelectionId(commonFilter.getCompany());
			 getComboSelectionId(commonFilter.getLocation());
			 getComboSelectionId(commonFilter.getSection());
			 getComboSelectionId(commonFilter.getCell());
			 getComboSelectionId(commonFilter.getMachine());
			 CommonFunctions.debugMsg("commonFilter.getCompany()"+commonFilter.getSection());
			
		 	  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
				  commonFilter.setRemoveBlank("Y");
		 	  }
		 	  else
			 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
						  commonFilter.setRemoveBlank("Y");
						 // commonFilter.setMonwise(" ");
				 	  }
				 
		 	 CommonFunctions.debugMsg(":::::"+ commonFilter.getFromMonth());
			 CommonFunctions.debugMsg(":::::"+ commonFilter.getToMonth());
			 CommonFunctions.debugMsg(":::::"+ commonFilter.getFromDate());
			 CommonFunctions.debugMsg(":::::"+ commonFilter.getToDate());
			
			 httpSession.removeAttribute("lossStratification");
			 httpSession.setAttribute("lossStratification",commonFilter);
	      	 response.setContentType("text/html");
	      	// if( commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag && commonFilter.getDrillLevel().equals(DrillLevelConstants.COMPANY)  )
				//	commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
	      	 
			  List<String[]> lossStratificationList  = lossStratificationService.getAllLossStratification(commonFilter);
			  lossStratificationList = cleanSbLossList(lossStratificationList);   // add gopi
			  List<String[]> lossStratificationData =  transposeListArr(lossStratificationList);
			  
			
			  PrintWriter out = response.getWriter();
			  JSONObject colModel = getTableModel(lossStratificationData,FilterValues.getHeader(commonFilter.getDrillCaption()));
			  colModel.set("tableHeight", "74%%");
			  colModel.set("tableWidth", "110%%");
			  httpSession.removeAttribute("lossReportColModel");
			  httpSession.setAttribute("lossReportColModel", colModel);
			  System.out.println("colModel  "+colModel);
			  out.println(colModel);
			
		}
		
		else if (action.equals("LossStratificationSubGrid_getData.ballosstr"))
		{
			String keyId = request.getParameter("keyID");	
			String sectid = request.getParameter("sectid");	
			String factId = request.getParameter("fctid");
			
			String rowId = request.getParameter("rowId");
			//CommonFunctions.debugMsg("Section88........"+sectid);
			
			try {		
				
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("lossStratification");
				//PrintWriter out = response.getWriter();
				/*CommonFilter chrtCommonFilter = new  CommonFilter();
				// CommonFilter chrtCommonFilter = null;
				 BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
				 FilterValues.getCommonFilters(request, chrtCommonFilter); 
				*/
			 	commonFilter.setLossId(keyId);
			/*	ComboFilter cmbSection = new ComboFilter();				
				cmbSection.setId(sectid);
				commonFilter.setSection(cmbSection);
				
				ComboFilter cmbFact = new ComboFilter();
				cmbFact.setId(factId);
				commonFilter.setFactory(cmbFact);*/
				 CommonFunctions.debugMsg("1111111111111  "+getComboSelectionId(commonFilter.getSection()));
				 getComboSelectionId(commonFilter.getCompany());
				 getComboSelectionId(commonFilter.getLocation());
				 getComboSelectionId(commonFilter.getFactory());
				 getComboSelectionId(commonFilter.getSection());
				 getComboSelectionId(commonFilter.getCell());
				 getComboSelectionId(commonFilter.getMachine());	
		
			//	CommonFunctions.debugMsg("Section........"+cmbSection.getId());
			//	CommonFunctions.debugMsg("Section........"+cmbFact.getId());
				List<String[]> lossRpt = lossStratificationService.getAllLossStratificationSubGrid(commonFilter);
				CommonFunctions.debugMsg("lossRptData size::: "+lossRpt.size());
				List<String[]> lossRptGridData =  transposeListArr(lossRpt);
				CommonFunctions.debugMsg("lossRptData grid data::"+lossRptGridData.size());
				JSONObject lossRptData = UIUtils.convertToJqGridTableObject(lossRptGridData, request,1,0);
				CommonFunctions.debugMsg("lossRptData "+lossRptData );
				
			//	 httpSession.removeAttribute("lossStratification");
  			 //	 httpSession.setAttribute("lossStratification", commonFilter);
				
				lossRptData.put("msg","Success" );
				lossRptData.put("rowId",keyId );
				lossRptData.put("gridRowId", rowId);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", lossRptData);
				returnData.put("rows", lossRptData);
				
				out.println(lossRptData);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}		
			
		}
		else if( action.equals("LossStratification_getData.ballosstr") )
		{
			try
			{
				// UIUtils.displayRequestParamsValue(request);
				String allLoss="N";
				if(action.equals("filterXmlLossStratification_input.ballosstr")){
					 allLoss="N";
				}
				
				// allLoss=request.getParameter("allLoss");
				//CommonFunctions.debugMsg(allLoss+" all Losses......");
				//--------gopi---------
				 allLoss=request.getParameter("allLoss");
				 if(allLoss == null)
					 allLoss = "N";
				CommonFunctions.debugMsg(allLoss+" all Losses......");
				// HttpSession httpSession = request.getSession();equipmentDownTimeList != null && 
				// CommonFilter commonFilter = populateCommonFilter(request,"EquipmentDownTime",false);
				 PrintWriter out = response.getWriter();
				 CommonFilter commonFilter = populateCommonFilter(request,"lossStratification",false,false);
				 CommonFunctions.debugMsg("commonFilter.getFlid()   "+commonFilter.getFlid());
				// if( commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag && commonFilter.getDrillLevel().equals(DrillLevelConstants.COMPANY)  )
					//	commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
			      	 
				 List<String[]> lossStratificationList  = lossStratificationService.getAllLossStratification(commonFilter);
				 lossStratificationList = cleanSbLossList(lossStratificationList);   // add Gopi
				 JSONObject listToJsonObject = new JSONObject();
				 List<String[]> lossStratificationData = null;
				 /*if(allLoss.equals("Y")){
					 commonFilter.setRemoveBlank("N");
				 }
				 else{
					 commonFilter.setRemoveBlank("Y");
					 if(allLoss.equals("Y")){
					lossStratificationData =  transposeListArr(lossStratificationList);
				}else{
				 }*/
				if("Y".equals(commonFilter.getRemoveBlank()) && allLoss.equals("Y") ){
					CommonFunctions.debugMsg("in side all Loss null "+allLoss);
					 //if(allLoss.equals("Y"))
							
							lossStratificationData =  transposeListArr(lossStratificationList);
					
					// else					
					//	 CommonFunctions.debugMsg("in side all Loss inner else "+allLoss);
					// lossStratificationData = UIUtils.transposeListArr(lossStratificationList,"0,-",4,lossStratificationList.size(),0);    
				}
				else
				{
					 lossStratificationData = UIUtils.transposeListArr(lossStratificationList,"0,-",4,lossStratificationList.size(),0);    

				}
					//lossStratificationData =  transposeListArr(lossStratificationList);
				
				if("Y".equals(commonFilter.getRemoveBlank())&& allLoss.equals("N")){
					CommonFunctions.debugMsg("in side all Loss null "+allLoss);
					 lossStratificationData = UIUtils.transposeListArr(lossStratificationList,"0,-",4,lossStratificationList.size(),0);    

				}
				else
					lossStratificationData =  transposeListArr(lossStratificationList);

				
				 if(lossStratificationData != null && lossStratificationData.size()>2)
					 listToJsonObject = UIUtils.convertToJqGridTableObject( lossStratificationData,request,2,0);
				
				 System.out.println(listToJsonObject);
				 out.println(listToJsonObject);
				// commonFilter.setViewClick('N');
  			 	
  			 	 httpSession.removeAttribute("lossStratification");
  			 	 httpSession.setAttribute("lossStratification", commonFilter);
			
		    }catch(Exception e)
			{
				System.out.println("error " +e.getMessage());
			}
		}
		else if( action.equals("LossStratification_getExcel.ballosstr")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"lossStratification",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			
			String month1   = fromMonth +"  -  "+ toMonth ;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate ;
		
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("lossReportColModel");
			tblJSONObj.put("transpose", true);
			tblJSONObj.put("title", "LossStratification Report" + " - "+date);
			if( commonFilter.getMonwise().equals("Y")){
				tblJSONObj.put("title", "LossStratification Report" + " - "+month1);
			}
			//tblJSONObj.put("title", "LossStratification Report");
			String format = ExcelUtils.getFormat(request);
			
			UIUtils.removeBlankRowExcel(commonFilter, tblJSONObj, 4, -1, "0,-");
			
			/*if( "Y".equals(commonFilter.getRemoveBlank() )){
				JSONObject removeBlank = new JSONObject();
				 
				removeBlank.put("fromCol", 4);
				removeBlank.put("toCol", -1);
				removeBlank.put("value", "0");
				tblJSONObj.put("removeRow",removeBlank);
			}*/
			
			Workbook wb = lossStratificationService.lossExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "LossStratification", format);
			
		}
		//// Kiran
		
		else if(action.equals("LossStratificationShift_input.ballosstr")) 
		{
			CommonFunctions.debugMsg("aaaaaaaaaaaaaaaaaaaaaaa");
			String filterString = request.getParameter("filterString");
			request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			request.setAttribute("lossstrgraph", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","lossstrgraph"));
			request.setAttribute("losssub", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","losssub"));
			request.setAttribute("filterStr", filterString);
			
			 CommonFunctions.debugMsg("FromDate   "+request.getParameter("dtFromDate"));
			//UIUtils.forwardRequest(request, response,"/tiles/extras.jsp"); 
			UIUtils.forwardRequest(request, response,"/pages/Reports/LossStratificationShift.jsp"); 

		}
		else if(action.equals("LossStratificationShift_getCol.ballosstr"))
		{
			CommonFunctions.debugMsg(" action  "+action);	
			 String firstClick =request.getParameter("firstClick");
			 CommonFilter commonFilter = populateCommonFilter(request,"lossStratification",( UIUtils.isValidKeyId(firstClick) || (firstClick!= null && ! firstClick.equals("Y") )),true);

			// CommonFilter commonFilter = populateCommonFilter(request,"lossStratification",true);
			// commonFilter = new CommonFilter(); 
			 //populateCommonFilter(request,"EquipmentDownTime",true);
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){
				 commonFilter =(CommonFilter) httpSession.getAttribute("lossStratification");
			 }	
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 
			 getComboSelectionId(commonFilter.getCompany());
			 getComboSelectionId(commonFilter.getLocation());
			 getComboSelectionId(commonFilter.getSection());
			 getComboSelectionId(commonFilter.getCell());
			 getComboSelectionId(commonFilter.getMachine());
			
		 	  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
				  commonFilter.setRemoveBlank("Y");
		 	  }
		 	  else
			 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
						  commonFilter.setRemoveBlank("Y");
						 // commonFilter.setMonwise(" ");
				 	  }
				 
		 	 CommonFunctions.debugMsg(":::::"+ commonFilter.getFromMonth());
			 CommonFunctions.debugMsg(":::::"+ commonFilter.getToMonth());
			 CommonFunctions.debugMsg(":::::"+ commonFilter.getFromDate());
			 CommonFunctions.debugMsg(":::::"+ commonFilter.getToDate());
			
			 httpSession.removeAttribute("lossStratification");
			 httpSession.setAttribute("lossStratification",commonFilter);
	      	 response.setContentType("text/html");
	      	// if( commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag && commonFilter.getDrillLevel().equals(DrillLevelConstants.COMPANY)  )
				//	commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
	      	 
			  List<String[]> lossStratificationList  = lossStratificationService.getAllLossStratificationShift(commonFilter);
			  lossStratificationList = cleanSbLossList(lossStratificationList);
			  for(int r = 0; r < Math.min(6, lossStratificationList.size()); r++)
				    System.out.println("HDRCHK r" + r + " : " + Arrays.toString(lossStratificationList.get(r)));
			  List<String[]> lossStratificationData =  transposeListArr(lossStratificationList);
			  
			
			  PrintWriter out = response.getWriter();
			 // JSONObject colModel = getTableModel(lossStratificationData,FilterValues.getHeader(commonFilter.getDrillCaption()));
			  JSONObject colModel = getTableModel(lossStratificationData,"Cell");
			  colModel.set("tableHeight", "74%%");
			  colModel.set("tableWidth", "110%%");
			  httpSession.removeAttribute("lossReportColModel");
			  httpSession.setAttribute("lossReportColModel", colModel);
			  System.out.println("colModel  "+colModel);
			  out.println(colModel);
			
		}
		
		else if (action.equals("LossStratificationSubGridShift_getData.ballosstr"))
		{
			String keyId = request.getParameter("keyID");	
			String sectid = request.getParameter("sectid");	
			String factId = request.getParameter("fctid");
			
			String rowId = request.getParameter("rowId");
			//CommonFunctions.debugMsg("Section88........"+sectid);
			
			try {		
				
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("lossStratification");
				//PrintWriter out = response.getWriter();
				/*CommonFilter chrtCommonFilter = new  CommonFilter();
				// CommonFilter chrtCommonFilter = null;
				 BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
				 FilterValues.getCommonFilters(request, chrtCommonFilter); 
				*/
			 	commonFilter.setLossId(keyId);
			/*	ComboFilter cmbSection = new ComboFilter();				
				cmbSection.setId(sectid);
				commonFilter.setSection(cmbSection);
				
				ComboFilter cmbFact = new ComboFilter();
				cmbFact.setId(factId);
				commonFilter.setFactory(cmbFact);*/
				 CommonFunctions.debugMsg("1111111111111  "+getComboSelectionId(commonFilter.getSection()));
				 getComboSelectionId(commonFilter.getCompany());
				 getComboSelectionId(commonFilter.getLocation());
				 getComboSelectionId(commonFilter.getFactory());
				 getComboSelectionId(commonFilter.getSection());
				 getComboSelectionId(commonFilter.getCell());
				 getComboSelectionId(commonFilter.getMachine());	
		
			//	CommonFunctions.debugMsg("Section........"+cmbSection.getId());
			//	CommonFunctions.debugMsg("Section........"+cmbFact.getId());
				List<String[]> lossRpt = lossStratificationService.getAllLossStratificationSubGridShift(commonFilter);
				CommonFunctions.debugMsg("lossRptData size::: "+lossRpt.size());
				List<String[]> lossRptGridData =  transposeListArr(lossRpt);
				CommonFunctions.debugMsg("lossRptData grid data::"+lossRptGridData.size());
				JSONObject lossRptData = UIUtils.convertToJqGridTableObject(lossRptGridData, request,1,0);
				CommonFunctions.debugMsg("lossRptData "+lossRptData );
				
			//	 httpSession.removeAttribute("lossStratification");
  			 //	 httpSession.setAttribute("lossStratification", commonFilter);
				
				lossRptData.put("msg","Success" );
				lossRptData.put("rowId",keyId );
				lossRptData.put("gridRowId", rowId);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", lossRptData);
				returnData.put("rows", lossRptData);
				
				out.println(lossRptData);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}		
			
		}
		else if( action.equals("LossStratificationShift_getData.ballosstr") )
		{
			try
			{
				// UIUtils.displayRequestParamsValue(request);
				
				// HttpSession httpSession = request.getSession();equipmentDownTimeList != null && 
				// CommonFilter commonFilter = populateCommonFilter(request,"EquipmentDownTime",false);
				 PrintWriter out = response.getWriter();
				 CommonFilter commonFilter = populateCommonFilter(request,"lossStratification",false,false);
				 CommonFunctions.debugMsg("commonFilter.getFlid()   "+commonFilter.getFlid());
				// if( commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag && commonFilter.getDrillLevel().equals(DrillLevelConstants.COMPANY)  )
					//	commonFilter.setDrillLevel(DrillLevelConstants.FACTORY);
			      	 
				 List<String[]> lossStratificationList  = lossStratificationService.getAllLossStratificationShift(commonFilter);
				 lossStratificationList = cleanSbLossList(lossStratificationList);
				 for(int r = 0; r < Math.min(6, lossStratificationList.size()); r++)
					    System.out.println("HDRCHK r" + r + " : " + Arrays.toString(lossStratificationList.get(r)));
				 JSONObject listToJsonObject = new JSONObject();
				 List<String[]> lossStratificationData = null;
				/* if("Y".equals(commonFilter.getRemoveBlank())){
					 //lossStratificationData = UIUtils.transposeListArr(lossStratificationList,"0,-",4,lossStratificationList.size(),0);    
					 lossStratificationData = UIUtils.transposeListArr(lossStratificationList,"0,-",5,lossStratificationList.size(),0);
					}
				 	else
				 		lossStratificationData =  transposeListArr(lossStratificationList);
				 if(lossStratificationData != null && lossStratificationData.size()>2)
					 listToJsonObject = UIUtils.convertToJqGridTableObject( lossStratificationData,request,2,0);
				 */
				 if("Y".equals(commonFilter.getRemoveBlank())){
					 lossStratificationData = UIUtils.transposeListArr(lossStratificationList,"0,-",4,lossStratificationList.size(),0);
					}
				 	else
				 		lossStratificationData =  transposeListArr(lossStratificationList);
				 System.out.println("SHIFT getData transposed size=" + (lossStratificationData == null ? -1 : lossStratificationData.size()));
				 if(lossStratificationData != null && lossStratificationData.size()>2)
					 listToJsonObject = UIUtils.convertToJqGridTableObject( lossStratificationData,request,2,0);
				
				 System.out.println(listToJsonObject);
				 out.println(listToJsonObject);
				// commonFilter.setViewClick('N');
  			 	
  			 	 httpSession.removeAttribute("lossStratification");
  			 	 httpSession.setAttribute("lossStratification", commonFilter);
			
		    }catch(Exception e)
			{
				System.out.println("error " +e.getMessage());
			}
		}
		else if( action.equals("LossStratificationShift_getExcel.ballosstr")){
			try
			{
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"lossStratification",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			
			String month1   = fromMonth +"  -  "+ toMonth ;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate ;
		
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("lossReportColModel");
			tblJSONObj.put("transpose", true);
			tblJSONObj.put("title", "LossStratification Report" + " - "+date);
			if( commonFilter.getMonwise().equals("Y")){
				tblJSONObj.put("title", "LossStratification Report" + " - "+month1);
			}
			//tblJSONObj.put("title", "LossStratification Report");
			String format = ExcelUtils.getFormat(request);
			
			UIUtils.removeBlankRowExcel(commonFilter, tblJSONObj, 4, -1, "0,-");
			
			/*if( "Y".equals(commonFilter.getRemoveBlank() )){
				JSONObject removeBlank = new JSONObject();
				 
				removeBlank.put("fromCol", 4);
				removeBlank.put("toCol", -1);
				removeBlank.put("value", "0");
				tblJSONObj.put("removeRow",removeBlank);
			}*/
			
			Workbook wb = lossStratificationService.lossExportExcelShift(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "LossStratification", format);
			
		}catch(Exception e){
		    e.printStackTrace();
		    CommonFunctions.debugMsg("Shift excel error: " + e);
		}
		}
		
		/// Kiran
		//-------------Gopi--------
		else if( action.equals("chartShift.ballosstr")){
			CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("lossStratification");
			CommonFilter chrtCommonFilter = new  CommonFilter();
			BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
			FilterValues.getCommonFilters(request, chrtCommonFilter);
			stripColSuffix(chrtCommonFilter);   //gopi
			if(chrtCommonFilter.getRowTotal() == null )
				chrtCommonFilter.setRowTotal('N');

			String title = "Loss Stratification" + " - " + commonFilter.getFromDate() + "  -  " + commonFilter.getToDate();
			if("Y".equals(commonFilter.getMonwise()))
				title = "Loss Stratification" + " - " + commonFilter.getFromMonth() + "  -  " + commonFilter.getToMonth();

			boolean isPercent = "Y".equals(request.getParameter("chkPercnt"));
			boolean skipZero  = "N".equals(request.getParameter("chkZeroLoss"));

			List<String[]> graphData = lossStratificationService.getAllLossStratificationShift(chrtCommonFilter);
			graphData = cleanSbLossList(graphData);      // re-enabled : removes odr/ordercol, fixes column order

			PrintWriter out = response.getWriter();
			if(graphData == null || graphData.size() < 6)
			{
				out.println(new JSONObject());
				out.close();
				return;
			}
			String[] header = graphData.get(3);   // names row
			String[] data   = graphData.get(5);   // first data row
			String subTitle = data[1] + " - " + data[2] + " (" + data[3] + ")";

			// 4..21 = 14 losses + Unreported + Total Loss + Loading + Operating + Availability Loss, 25 = ROA, 26 = ROP
			int[] want = {4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,25,26};

			List<String> nList = new ArrayList<String>();
			List<Double> vList = new ArrayList<Double>();
			double total = 0;
			for(int k = 0; k < want.length; k++){
				int j = want[k];
				if(j >= header.length || j >= data.length) continue;
				String nm = header[j] == null ? "" : header[j].trim();
				if(nm.length() == 0) continue;
				if(nm.equalsIgnoreCase("ROA")) nm = "Availability (A)";
				else if(nm.equalsIgnoreCase("ROP")) nm = "Performance (P)";
				else if(nm.equalsIgnoreCase("LOADINGTIME")) nm = "Loading Time";
				else if(nm.equalsIgnoreCase("PRODUCTIONTIME")) nm = "Operating Time";
				double v = toDbl(data[j]);
				if(skipZero && v == 0) continue;
				nList.add(nm);
				vList.add(v);
				total += v;
			}

			DecimalFormat df = new DecimalFormat(".00");
			df.setRoundingMode(RoundingMode.FLOOR);
			List<Object[]> dataList = new ArrayList<Object[]>();
			for(int k = 0; k < nList.size(); k++){
				Object[] pieData = new Object[2];
				pieData[0] = nList.get(k);
				if(isPercent && total > 0)
					pieData[1] = Double.parseDouble(df.format((vList.get(k)/total)*100));
				else
					pieData[1] = vList.get(k);
				dataList.add(pieData);
			}

			ChartPie pieChart = new ChartPie();
			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
			ChartSeries chartSeries = new ChartSeries();
			chartSeries.setType(ChartTypes.PIE);
			chartSeries.setData(dataList);
			chartSeries.setSize(200);
			chartSeriesList.add(chartSeries);
			out.println(pieChart.drawChart(chartSeriesList, title, subTitle));
			out.close();
		}
		//----------------------gopi------------------------------------------
		
		else if( action.equals("chart.ballosstr")){
			//processChart(request,response);
			CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("lossStratification");
			
			CommonFilter chrtCommonFilter = new  CommonFilter();
			
			BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
			FilterValues.getCommonFilters(request, chrtCommonFilter) ;
			stripColSuffix(chrtCommonFilter);   //gopi
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			
			String month1   = fromMonth +"  -  "+ toMonth ;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate ;
			String title = "Loss Stratification" + " - "+date;
			if( commonFilter.getMonwise().equals("Y")){
			title = "Loss Stratification" + " - "+month1;
			}
			if(chrtCommonFilter.getRowTotal() == null )
				chrtCommonFilter.setRowTotal('N');
		
			List<String[]> graphData  =  lossStratificationService.getAllLossGraph(chrtCommonFilter);
			//gopi----------
			/*if(graphData == null || graphData.size() < 6){
				PrintWriter o = response.getWriter();
				o.println(new JSONObject());
				o.close();
				return;
			}
			//----------------------
			ChartPie pieChart =  new   ChartPie();
			
			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
			
			PrintWriter out = response.getWriter();
			String[] header = graphData.get(3);
			String[] data = graphData.get(5);
			String subTitle = data[1] ;*/
			String[][] pick = pickGraphRows(graphData);
			if(pick == null){
				PrintWriter o = response.getWriter();
				o.println(new JSONObject());
				o.close();
				return;
			}
			ChartPie pieChart =  new   ChartPie();

			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();

			PrintWriter out = response.getWriter();
			String[] header = pick[0];
			String[] data = pick[1];
			String subTitle = data[2];
			
			//---------------gopi-------------------------
			
		//	String fromMonth = commonFilter.getFromMonth();
			//String toMonth = commonFilter.getToMonth();
			//String date   = fromMonth +"  -  "+ toMonth ;
			List<Object[]> dataList = new ArrayList<Object[]>();
			CommonFunctions.debugMsg("data.length111111111111111"+data.length);
			//for(int j=5;j<data.length-1;j++){
			for(int j=3;j<data.length;j++){	
				Object []  pieData = new Object[2];
				pieData[ 0 ] = header[j];
				//pieData[ 1 ] = Double.parseDouble(data[j]);
				pieData[ 1 ] = toDbl(data[j]);
				dataList.add(pieData);
				//CommonFunctions.debugMsg("222"+ Double.parseDouble(data[j]));
			}
			
			ChartSeries chartSeries = new ChartSeries();
			
			chartSeries.setType(ChartTypes.PIE);
			chartSeries.setData(dataList);
			chartSeriesList.add(chartSeries);
			chartSeries.setSize(200);
			
		//	chartSeries.setCenter(200);
			CommonFunctions.debugMsg(chartSeriesList.size());
			out.println(pieChart.drawChart(chartSeriesList, title, subTitle));
			out.close();
		  }
		
		
 
	else if( action.equals("chartzero.ballosstr")){
		//processChart(request,response);
		PrintWriter out = response.getWriter();
		CommonFilter commonFilter =  (CommonFilter) httpSession.getAttribute("lossStratification");
		
		CommonFilter chrtCommonFilter = new  CommonFilter();
		String forDashboard = request.getParameter("dashboard");
		CommonFunctions.debugMsg("forDashboard....."+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonFunctions.debugMsg("to check dash board");
			chrtCommonFilter = (CommonFilter) httpSession.getAttribute("lossStratification");
		}
		else{
			
			CommonFunctions.debugMsg("to check dash board true ");
			
			FilterValues.getCommonFilters(request, chrtCommonFilter) ;
			FilterValues.getPCS(request, chrtCommonFilter);
			
		}
		CommonFunctions.debugMsg(chrtCommonFilter.getToMonth() +"commonFilter.getFromMonth()  "+chrtCommonFilter.getFromMonth());
		String fromMonth = chrtCommonFilter.getFromMonth(); 
		String toMonth = chrtCommonFilter.getToMonth();		
		String month1   = fromMonth +"  -  "+ toMonth ;
		String fromdate = chrtCommonFilter.getFromDate();
		String todate = chrtCommonFilter.getToDate();
		String date   = fromdate +"  -  "+ todate ;
		String title = "Loss Stratification" + " - "+date;
		if( chrtCommonFilter.getMonwise().equals("Y")){
		title = "Loss Stratification" + " - "+month1;
		}
		if(chrtCommonFilter.getRowTotal() == null )
			chrtCommonFilter.setRowTotal('N');
	
		List<String[]> graphData  =  lossStratificationService.getAllLossStratification(chrtCommonFilter);
		//gopi----------
		if(graphData == null || graphData.size() < 6){
			out.println(new JSONObject());
			out.close();
			return;
		}
		//----------------------
		ChartPie pieChart =  new   ChartPie();
		
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		
		
		String[] header = graphData.get(3);
		String[] data = graphData.get(5);
		String subTitle = data[1] ;
		
		

		List<Object[]> dataList = new ArrayList<Object[]>();
		CommonFunctions.debugMsg("data.length111111111111111:"+data.toString());
		
	//	Double  total = null;
		//if()
		//total = sumForPercentage(data);
		try{	
		for(int j=3;j<data.length-12;j++){
			
			Object []  pieData = new Object[2];
			pieData[ 0 ] = header[j];
			
			//CommonFunctions.debugMsg("data.length11111111111111 1  :"+  Double.parseDouble(data[j]));
			//if(percentage != null){
			//	CommonFunctions.debugMsg("222::ooooooooooooooo" );
		//	pieData[ 1 ] =(  (Double.parseDouble(data[j])/total)  *100);
			//}
			//else
			
				//pieData[ 1 ] =Double.parseDouble(data[j]);
			pieData[ 1 ] = toDbl(data[j]);
			
			dataList.add(pieData); 
			//CommonFunctions.debugMsg("222::"+ Double.parseDouble(data[j]));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		ChartSeries chartSeries = new ChartSeries();
		
		chartSeries.setType(ChartTypes.PIE);
		chartSeries.setData(dataList);
		chartSeriesList.add(chartSeries);
		chartSeries.setSize(200);
	//	chartSeries.setCenter(200);
		CommonFunctions.debugMsg(chartSeriesList.size());
		JSONObject chartObj = null;
		chartObj = pieChart.drawChart(chartSeriesList, title, subTitle );
		String dashboardIdnt = request.getParameter("dashboardIdent");
		
		CommonFunctions.debugMsg("dashboardIdnt    :"+dashboardIdnt);
		if( UIUtils.isValidKeyId(dashboardIdnt));
			chartObj .set("dashboardIdent", dashboardIdnt);
			//UIUtils.setDashBoardIdentifier(request,chartObj);
	     UIUtils.dashBoardSetChartObject(request,chartObj);
		out.println(chartObj);
		out.close();
	
		}
	else if( action.equals("chartpercent.ballosstr")){
		//processChart(request,response);
		PrintWriter out = response.getWriter();
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("lossStratification");
		
		CommonFilter chrtCommonFilter = new  CommonFilter();
		
		BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
		FilterValues.getCommonFilters(request, chrtCommonFilter) ;
		stripColSuffix(chrtCommonFilter);   //gopi
		String fromMonth = commonFilter.getFromMonth();
		String toMonth = commonFilter.getToMonth();
		
		String month1   = fromMonth +"  -  "+ toMonth ;
		String fromdate = commonFilter.getFromDate();
		String todate = commonFilter.getToDate();
		String date   = fromdate +"  -  "+ todate ;
		String title = "Loss Stratification" + " - "+date;
		if( commonFilter.getMonwise().equals("Y")){
		title = "Loss Stratification" + " - "+month1;
		}
		if(chrtCommonFilter.getRowTotal() == null )
			chrtCommonFilter.setRowTotal('N');
	
		List<String[]> graphData  =  lossStratificationService.getAllLossGraph(chrtCommonFilter);
		//gopi----------
		String[][] pick = pickGraphRows(graphData);
		if(pick == null){
			out.println(new JSONObject());
			out.close();
			return;
		}

		ChartPie pieChart =  new   ChartPie();

		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();

		String[] header = pick[0];
		String[] data = pick[1];
		String subTitle = data[2];
	
		//String fromMonth = commonFilter.getFromMonth();
		//String toMonth = commonFilter.getToMonth();
		//String date   = fromMonth +"  -  "+ toMonth ;
		List<Object[]> dataList = new ArrayList<Object[]>();
		//CommonFunctions.debugMsg("data.length111111111111111:"+data);
		
		Double  total = null;
		//if()
		total = sumForPercentage(data);
		DecimalFormat df = new DecimalFormat(".00");
		df.setRoundingMode(RoundingMode.FLOOR);
		CommonFunctions.debugMsg("data.length111111111111111 ++"+total  );
		
	//	for(int j=5;j<data.length-1;j++){
		/*for(int j=3;j<data.length-12;j++){		
			Object []  pieData = new Object[2];
			pieData[ 0 ] = header[j];
			pieData[ 1 ] =(  (Double.parseDouble(data[j])/total)  *100);
			pieData[ 1 ] =Double.parseDouble(df.format(pieData[ 1 ]));
			dataList.add(pieData); 
			CommonFunctions.debugMsg("222::"+ Double.parseDouble(data[j]));
		}*/
		//--------------gopi---------------------------------
	       for(int j=3;j<data.length;j++){		
			Object []  pieData = new Object[2];
			pieData[ 0 ] = header[j];
			pieData[ 1 ] = (total > 0) ? Double.parseDouble(df.format((toDbl(data[j])/total)*100)) : 0.0;
			dataList.add(pieData); 
		}
		
		ChartSeries chartSeries = new ChartSeries();
		
		chartSeries.setType(ChartTypes.PIE);
		chartSeries.setData(dataList);
		chartSeriesList.add(chartSeries);
		chartSeries.setSize(200);
	//	chartSeries.setCenter(200);
		CommonFunctions.debugMsg(chartSeriesList.size());
		out.println(pieChart.drawChart(chartSeriesList, title, subTitle));
		out.close();
	  }
	
}
	//-----------------------gopi------------
	private String[][] pickGraphRows(List<String[]> g)
	{
	    if (g == null || g.isEmpty()) return null;

	    for (int r = 0; r < g.size(); r++)
	        System.out.println("GRAPHROW " + r + " len=" + (g.get(r) == null ? -1 : g.get(r).length) + " : " + Arrays.toString(g.get(r)));

	    // 1. find the colorder row (all numeric) -> tells the real position of each logical column
	    int ordIdx = -1;
	    for (int r = 0; r < Math.min(10, g.size()) && ordIdx == -1; r++)
	    {
	        String[] row = g.get(r);
	        //if (row == null || row.length < 30) continue;
	        if (row == null || row.length < 6) continue;
	        boolean allNum = true;
	        for (int c = 0; c < row.length; c++)
	            if (row[c] == null || !row[c].trim().matches("\\d{1,3}")) { allNum = false; break; }
	        if (allNum) ordIdx = r;
	    }
	    if (ordIdx == -1) { System.out.println("GRAPHPICK colorder row not found"); return null; }

	    String[] ord = g.get(ordIdx);
	    int[] pos = new int[64];
	    Arrays.fill(pos, -1);
	    for (int c = 0; c < ord.length; c++)
	    {
	        int n = Integer.parseInt(ord[c].trim());
	        if (n < pos.length) pos[n] = c;
	    }
	    int ordCol = pos[2];                                   // physical column holding "ordercol"
	    if (ordCol == -1) { System.out.println("GRAPHPICK ordercol column not found"); return null; }

	    // 2. names row = ordercol 6, data row = ordercol 8
	    String[] names = null, data = null;
	    for (int r = 0; r < g.size(); r++)
	    {
	        if (r == ordIdx) continue;
	        String[] row = g.get(r);
	        if (row == null || row.length <= ordCol || row[ordCol] == null) continue;
	        String oc = row[ordCol].trim();
	        if ("6".equals(oc) && names == null) names = row;
	        else if ("8".equals(oc) && data == null) data = row;
	    }
	    if (names == null || data == null)
	    {
	        System.out.println("GRAPHPICK names=" + (names != null) + " data=" + (data != null));
	        return null;
	    }

	    // 3. logical columns for the pie (colorder numbers):
	    // 6..19 = 14 losses + Unreported, 20 = Total Loss, 21 = Loading Time, 22 = Operating Time,
	    // 23 = Availability Loss, 27 = Availability (A), 28 = Performance (P)
	    int[] want = {6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,27,28};

	    String subTitle = (pos[5] >= 0 && pos[5] < data.length && data[pos[5]] != null) ? data[pos[5]].trim() : "";

	    List<String> nList = new ArrayList<String>();
	    List<String> dList = new ArrayList<String>();
	    for (int k = 0; k < want.length; k++)
	    {
	        int p = pos[want[k]];
	        if (p < 0 || p >= names.length || p >= data.length) continue;
	        String nm = names[p] == null ? "" : names[p].trim();
	        if (nm.length() == 0) continue;
	        if (nm.equalsIgnoreCase("ROA")) nm = "Availability (A)";
	        else if (nm.equalsIgnoreCase("ROP")) nm = "Performance (P)";
	        else if (nm.equalsIgnoreCase("LOADINGTIME")) nm = "Loading Time";
	        else if (nm.equalsIgnoreCase("PRODUCTIONTIME")) nm = "Operating Time";
	        nList.add(nm);
	        dList.add(data[p]);
	    }

	    // compact arrays: index 2 = subtitle, index 3.. = pie items
	    String[] n2 = new String[3 + nList.size()];
	    String[] d2 = new String[3 + nList.size()];
	    Arrays.fill(n2, "");
	    Arrays.fill(d2, "");
	    d2[2] = subTitle;
	    for (int k = 0; k < nList.size(); k++) { n2[3 + k] = nList.get(k); d2[3 + k] = dList.get(k); }
	    return new String[][] { n2, d2 };
	}
	//---------------------------gopi----------------------------
	//------gopi--------------
	private double toDbl(String s) {
		if (s == null) return 0;
		s = s.trim();
		if (s.length() == 0 || s.equals("-")) return 0;
		try { return Double.parseDouble(s); } catch (Exception e) { return 0; }
	}
	//------------------gopi----------------
	//------gopi--------------
	// getTableModel duplicate colname suffix (_6) remove, real key id mattum
	private void stripColSuffix(CommonFilter f) {
		ComboFilter[] cmb = { f.getCompany(), f.getLocation(), f.getFactory(), f.getSection(), f.getCell(), f.getMachine() };
		for (ComboFilter c : cmb) {
			if (c != null && c.getId() != null)
				c.setId(c.getId().replaceAll("_\\d+$", ""));
		}
	}
	//------------------gopi----------------
	//--------gopi-----
	/*private  double sumForPercentage( String[] data) {
	     double sum = 0  ; 
	     CommonFunctions.debugMsg("222222 "+data.length);
	    // for (int i=5;i< data.length-1;i++){
	     for(int i=2;i<data.length-12;i++){
	    	CommonFunctions.debugMsg("::::  "+i+" 222  "+ Double.parseDouble(data[i]));
	         sum = sum + Double.parseDouble(data[i]);
	     }
	     return sum;
	    
	}*/
	private  double sumForPercentage( String[] data) {
	     double sum = 0;
	     for(int i=3;i<data.length;i++){
	    	    sum = sum + toDbl(data[i]);
	    	}
	     return sum;
	}
	
 
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew,boolean drilFlag){
		HttpSession httpSession = request.getSession(false);
		
		CommonFunctions.debugMsg("createNew...."+drilFlag);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			if(drilFlag )
				FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
			commonFilter = 	FilterValues.getPCS(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
			
		
		}
		
		return commonFilter;
	}

	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("lossStratification");
		
		CommonFilter chrtCommonFilter = new  CommonFilter();
		
		BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
		FilterValues.getCommonFilters(request, chrtCommonFilter) ;
		
		if(chrtCommonFilter.getRowTotal() == null )
			chrtCommonFilter.setRowTotal('N');
		
		JSONObject chartObj = null;
		String chartType = request.getParameter("chType");

		if( chartType == null){
			List<String[]> equipmentDownTimeList  = lossStratificationService.getAllLossGraph(chrtCommonFilter);
			 chartObj = processPieChart(equipmentDownTimeList,chrtCommonFilter);
		}
		
		chrtCommonFilter = null;
		
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();
	}
	private JSONObject processPieChart(List<String[]> equipmentDownTimeList,CommonFilter commonFilter) throws IllegalAccessException, InvocationTargetException{
		if( equipmentDownTimeList == null || equipmentDownTimeList.size() <= 1  )
			return null;
		
		ChartPie pieChart =  new   ChartPie();
		
		String title = "Loss Stratification";
		ChartSeries chartSeries = new ChartSeries();
		chartSeries.setType("pie");
		
		String []  header = equipmentDownTimeList.get(0);
		chartSeries.setName(header[1]);
		List<Object[]> dataList = new ArrayList<Object[]>();
		
		int rCnt = 0;
		for(String [] row :  equipmentDownTimeList){
			if( rCnt++ <= 0  ) continue;
			Object []  data = new Object[2];
			data[ 0 ] =  row[2];
			data[ 6 ] = Double.parseDouble(row[2]);
			dataList.add(data);
		}
				
		chartSeries.setData(dataList);
		chartSeries.setSize(200);
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		chartSeriesList.add(chartSeries);
		
		return pieChart.drawChart(chartSeriesList, title, "");
	}
	
	private JSONObject getTableModel(List<String[]> headers, String caption)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		
		
		
		
		//String [] colHeader1 = headers.get(0);
		String [] colHeader = headers.get(1);
		String[] emptyrow = new String[colHeader.length]; 
		
		
		emptyrow [0] ="";
		emptyrow [1] ="";
		emptyrow [2] ="";
		emptyrow [3] ="";
		emptyrow [4] ="";
		//emptyrow [5] ="";
		colHeader[0] = "keyid";
		colHeader[1] = "m";
		colHeader[2] = "num";		
		colHeader [4] ="UoM";
		//colHeader [5] ="Date";
		
	/*	for( int i = 3; i < colHeader.length;i++ ){
			colHeader[i] = colHeader[i].substring(colHeader[i].lastIndexOf("-")+1);
			colHeader[i] = colHeader[i].replace("I","Occurences").replace("T", "Time");
			
			emptyrow [i] ="";
		}*/
		
		jqGridTableModel.getRowHeaders().add(emptyrow);
		
		
	//	jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.getRowHeaders().add(colHeader);
		//	colHeader[i] = colHeader[i].replace("I", newChar)   
			
		//jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setTableButton(true);
	//	String [] colHeader = headers.get(0);
		colHeader[3] = caption;
	//	colHeader1[1] = "UoM";  
		
		  
		//jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(true);	
		//jqGridTableModel.setGroupByField("GroupField");
		//jqGridTableModel.setTableHeight(255);
	//	jqGridTableModel.setGroupBy(true);
		//jqGridTableModel.setGroupSummary(true);
		
		//jqGridTableModel.setRowNumbers(true);
		
		jqGridTableModel.getColModel().add(getColModel("keyid", 50,"left",true,false,true));
		
	//	JqGridColModel jqGridColModel =getColModel("codeField",100,"center",true,true,false);
		jqGridTableModel.getColModel().add(getColModel("GroupF",50,"left",true,false,false));
		jqGridTableModel.getColModel().add(getColModel("Group",50,"left",true,false,false));
		jqGridTableModel.getColModel().add(getColModel("GroupField",250,"left",false,false,false));
		jqGridTableModel.getColModel().add(getColModel("GroupFie",50,"left",false,false,false));
		/*//jqGridColModel.setSummaryType("count");
		jqGridColModel.setSummaryTpl("<b><font >Total</font> </b>");
		jqGridTableModel.getColModel().add( jqGridColModel);*/
		/*String colIndex ="";
		colHeader = headers.get(0);
		for(int i =5; i < colHeader.length; i++)
		{
			emptyrow [i] ="";
			CommonFunctions.debugMsg("lossRptData iiii::: "+i);
			CommonFunctions.debugMsg("lossRptData colheader::: "+colHeader);
			colIndex = colHeader[i].replaceAll(" ", "").replace("-", "");
			CommonFunctions.debugMsg("lossRptDatacolindex ::"+ colIndex);
			jqGridTableModel.getColModel().add(getColModel( colIndex,150,"center", false,true,false));
		}*/
		
		String colIndex ="";
		colHeader = headers.get(0);
		java.util.Set<String> seenCols = new java.util.HashSet<String>();   // added
		for(int i =5; i < colHeader.length; i++)
		{
		    emptyrow [i] ="";
		    CommonFunctions.debugMsg("lossRptData iiii::: "+i);
		    CommonFunctions.debugMsg("lossRptData colheader::: "+colHeader);
		    colIndex = colHeader[i].replaceAll(" ", "").replace("-", "");
		    if(!seenCols.add(colIndex))                // added
		        colIndex = colIndex + "_" + i;         // added
		    CommonFunctions.debugMsg("lossRptDatacolindex ::"+ colIndex);
		    jqGridTableModel.getColModel().add(getColModel( colIndex,150,"center", false,true,false));
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 CommonFunctions.debugMsg("tableModel ::"+ tableModel);
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
		
		if(groupbyfield){
			jqGridColModel.setSummaryType("sum");
			jqGridColModel.setSummaryTpl("<b><font >  {0} </font> </b>");
		}
		return jqGridColModel;
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
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getPCS(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
	private List<String[]> transposeListArr(List<String[]> dataList)
	{
		if( dataList.size() <=0 ) return null;
		//Object[] pcsRptArr = dataList.toArray();
		
		List<String[]> transposeList = new ArrayList<String[]>();
		
		for( int i =0; i<dataList.get(0).length; i++)
		{	
			String [] tRow = new String [ dataList.size()];
			for (int j=0; j<dataList.size();j++)
			{
				tRow [ j ]= dataList.get(j)[i];

			}
			transposeList.add(tRow);
		}
		return transposeList;
	}
	//--------------------------------------------------gopi-----------------
	private List<String[]> cleanSbLossList(List<String[]> list)
	{
	    if(list == null || list.isEmpty())
	        return list;

	    final int HDR_ROWS = 5;     // keyid, isloss, rowcolor, name, uom
	    final int FIRST_HDR = 3;    // ordercol 3..7 = header rows
	    final int DATA_ORD = 8;     // ordercol 8 = data rows

	    // 1. find the colorder row (all numeric)
	    int ordIdx = -1;
	    for(int r = 0; r < Math.min(10, list.size()) && ordIdx == -1; r++)
	    {
	        String[] row = list.get(r);
	        if(row == null || row.length < 6)
	            continue;
	        boolean allNum = true;
	        for(int c = 0; c < row.length; c++)
	        {
	            if(row[c] == null || !row[c].trim().matches("\\d+"))
	            {
	                allNum = false;
	                break;
	            }
	        }
	        if(allNum)
	            ordIdx = r;
	    }
	    System.out.println("CLEAN colorder row idx=" + ordIdx);
	    if(ordIdx == -1)
	        return list;

	    // 2. odr / ordercol positions + dynamic column count (positions start from 4)
	    String[] ord = list.get(ordIdx);
	    int odrCol = -1;
	    int ordCol = -1;
	    int COLS = 0;
	    for(int c = 0; c < ord.length; c++)
	    {
	        String v = ord[c].trim();
	        if("1".equals(v))
	            odrCol = c;
	        else if("2".equals(v))
	            ordCol = c;
	        else if(Integer.parseInt(v) >= 4)
	            COLS++;
	    }
	    if(odrCol == -1 || ordCol == -1 || COLS == 0)
	    {
	        System.out.println("CLEAN odrCol/ordCol not found");
	        return list;
	    }

	    // keep[k] = index of the physical column that holds report position (k + 4)
	    int[] keep = new int[COLS];
	    for(int k = 0; k < COLS; k++)
	    {
	        String target = String.valueOf(k + 4);
	        int found = -1;
	        for(int c = 0; c < ord.length; c++)
	        {
	            if(target.equals(ord[c].trim()))
	            {
	                found = c;
	                break;
	            }
	        }
	        if(found == -1)
	        {
	            System.out.println("CLEAN colorder position " + target + " not found");
	            return list;
	        }
	        keep[k] = found;
	    }
	    System.out.println("CLEAN COLS=" + COLS + " odrCol=" + odrCol + " ordCol=" + ordCol);

	    // 3. split rows into fixed header slots + data rows
	    String[][] hdr = new String[HDR_ROWS][];
	    List<Object[]> dataRows = new ArrayList<Object[]>(); // {Long odr, String[] row}

	    for(int r = 0; r < list.size(); r++)
	    {
	        if(r == ordIdx)
	            continue;
	        String[] row = list.get(r);
	        if(row == null || row.length <= Math.max(odrCol, ordCol) || row[ordCol] == null)
	            continue;
	        String oc = row[ordCol].trim();
	        if(!oc.matches("\\d+"))
	            continue;
	        long ocv = Long.parseLong(oc);
	        if(ocv < FIRST_HDR)
	            continue; // colmodel (1) and colorder (2)

	        String[] out = new String[COLS];
	        for(int k = 0; k < COLS; k++)
	            out[k] = (row[keep[k]] == null ? "" : row[keep[k]]);

	        long odrv = 0;
	        try { odrv = Long.parseLong(row[odrCol].trim()); } catch(Exception e) {}

	        if(ocv >= FIRST_HDR && ocv < FIRST_HDR + HDR_ROWS)
	        {
	            int slot = (int)(ocv - FIRST_HDR);
	            if(hdr[slot] != null)
	                System.out.println("CLEAN DUPLICATE header ordercol=" + ocv);
	            hdr[slot] = out;
	        }
	        else if(ocv == DATA_ORD)
	        {
	            dataRows.add(new Object[] { Long.valueOf(odrv), out });
	        }
	        else
	        {
	            System.out.println("CLEAN unknown ordercol=" + ocv + " skipped");
	        }
	    }

	    // 4. sort data rows by odr
	    java.util.Collections.sort(dataRows, new java.util.Comparator<Object[]>() {
	        public int compare(Object[] a, Object[] b) {
	            return ((Long) a[0]).compareTo((Long) b[0]);
	        }
	    });

	    // 5. build final list : always exactly 5 header rows, then data rows
	    List<String[]> cleaned = new ArrayList<String[]>();
	    for(int h = 0; h < HDR_ROWS; h++)
	    {
	        if(hdr[h] == null)
	        {
	            System.out.println("CLEAN MISSING header ordercol=" + (h + FIRST_HDR) + " -> blank row added");
	            String[] blank = new String[COLS];
	            Arrays.fill(blank, "");
	            if(h == 0) // keyid row missing -> unique synthetic keys
	            {
	                blank[0] = "KEYIDFIELD";
	                blank[1] = "CODEFIELD";
	                for(int k = 3; k < COLS; k++)
	                    blank[k] = "LOSS" + k;
	            }
	            cleaned.add(blank);
	        }
	        else
	            cleaned.add(hdr[h]);
	    }
	    for(Object[] d : dataRows)
	        cleaned.add((String[]) d[1]);

	    System.out.println("CLEAN cleaned rows=" + cleaned.size() + " (header=" + HDR_ROWS + ", data=" + dataRows.size() + ")");
	    return cleaned;
	}
	// removes leading odr column (offset) and trailing ordercol; report has 29 columns
	private String[] trimColsLoss(String[] row, int offset)
	{
		if(row == null)
			return row;
		int usable = row.length - offset;
		int newLen = Math.min(29, usable);
		String[] out = new String[newLen];
		System.arraycopy(row, offset, out, 0, newLen);
		return out;
	}
	
	//------------------------gopi----------------------------
	private static JSONObject convertToJqGridTable(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart)
	{
		return convertToJqGridTable(dataArrayList, request, rowStart, colStart,  dataArrayList.size());
	}

	private static JSONObject convertToJqGridTable (List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, long totalRecords)
	{
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 100;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr);
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
		
		JSONObject tableDataObject = new JSONObject();
		
		System.out.println(" totalRecords " + totalRecords);
		
		tableDataObject.put("page", page); //current page
		tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
		//if( page == 1)
		tableDataObject.put("records", totalRecords); //total records
		
		JSONArray rowArr = new JSONArray(); 
       
      
		int rowId = rows * (page-1);
        for( String [] row : dataArrayList)
		{
        	if( rowId >= rowStart )
        	{	
	    	    JSONObject rowObj =new JSONObject();
	    	    	
	    	    rowObj.put("id",rowId -rowStart +1);
	            
	            JSONArray cell=new JSONArray();
	            
	            for( int i = colStart ;i < row.length ; i++)
	            {	 
	            	cell.put( ( row[i] != null ? row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") ); 
	            }	
	            rowObj.put("id",row[1]);
	            rowObj.put("cell",cell);
	            
	            rowArr.put(rowObj);
        	}
        	rowId++;
       }

        tableDataObject.put("rows", rowArr);
        
        return tableDataObject;

	}
	private static String getComboSelectionId(ComboFilter comboFilter){ 		
		if( comboFilter != null && UIUtils.isValidKeyId( comboFilter.getId() ) )
			return comboFilter.getId();
		return "";
	}
}
				
