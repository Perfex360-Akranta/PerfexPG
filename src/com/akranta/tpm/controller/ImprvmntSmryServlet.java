package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartPie;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.ImprvmntSmryService;
import com.akranta.tpm.service.KaizenReportService;

import com.akranta.tpm.service.impl.ImprovementVsCompletedServiceImpl;
import com.akranta.tpm.service.impl.ImprvmntSmryServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.KaizenReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;


public class ImprvmntSmryServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;	
	ImprvmntSmryService imprvmntSmryService ; 
	KaizenReportService kaizenReportService;
	DashboardService dashboardService;
	public ImprvmntSmryServlet(){
		/*try {
			imprvmntSmryService = new ImprvmntSmryServiceImpl();
		} catch (Exception e) {
			
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
		
		HttpSession httpSession = request.getSession(false);

		String action = UIUtils.getActionPart(request);
		try {
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			imprvmntSmryService = (ImprvmntSmryServiceImpl) UIUtils.getServiceObject(request, "ImprvmntSmryServiceImpl");
			kaizenReportService = (KaizenReportServiceImpl) UIUtils.getServiceObject(request, "KaizenReportServiceImpl");
			
			CommonMessage.debugMsg("  kaizenReportService jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
			//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			kaizenReportService.KaizenReportServiceImplJwt(
						   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
						);
			
			
			CommonMessage.debugMsg("  imprvmntSmryService jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
			//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			imprvmntSmryService.ImprvmntSmryServiceImplJwt(
						   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
						);
				
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}		

		 if( action.equals("filterXmlImpSmry_input.impSmrRpt")){
			 PrintWriter out = response.getWriter();
				response.setContentType("xml"); 
				CommonMessage.debugMsg("action "+ action); 
				out.print("<fromDate>01-Jan-2012</fromDate>");
				UIUtils.forwardRequest(request, response, "/tiles/xml/ImprovementSmry.xml") ;
			 }	
		 else if(action.equals("ImpSmry_input.impSmrRpt")) 
		{
			 String filterString = request.getParameter("filterString");
			 request.setAttribute("DrillDown", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			 request.setAttribute("filterStr", filterString);
			 UIUtils.forwardRequest(request, response, "/pages/Improvmntsmry.jsp"); 
			
		}
		 
		 else if(action.equals("ImpSmry_getCol.impSmrRpt"))
			{	
				 String firstClick =request.getParameter("firstClick");
				
				 CommonMessage.debugMsg("jhDrillDnDatafirstClick--- " + firstClick);
				 PrintWriter out = response.getWriter();
				 CommonFilter  commonFilter  ;
				 commonFilter = new CommonFilter(); 
				
				if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){
				
					 commonFilter =(CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
				 }				
			
				 if(commonFilter==null)
					 commonFilter = new CommonFilter(); 
				 
				 FilterValues.getCommonFilters(request,commonFilter);				
				 FilterValues.getOPLandKaizen(request,commonFilter);
					
				 httpSession.setAttribute("improveDrillcommonFilter",commonFilter);
				 response.setContentType("text/html");
			
				if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
				{
					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					commonFilter.setMonwise("Y");
					
				}
				 commonFilter.setIsGetCol("Y");
				 List< String[]> jhDrillDnList  = imprvmntSmryService.getAllImprovement(commonFilter);	
				 CommonMessage.debugMsg("jhDrillDnList:"+jhDrillDnList.get(0)[1]);
				 JSONObject jsonObject = getTableModel(jhDrillDnList,FilterValues.getHeader(commonFilter.getDrillCaption()));
				 jsonObject.set("tableWidth", "108%%");
				 jsonObject.set("tableHeight", "70%%");
				 httpSession.removeAttribute("ImprovementSummaryColData");
	  			 httpSession.setAttribute("ImprovementSummaryColData", jsonObject);
	  			 	
				  CommonMessage.debugMsg(jsonObject);
				  out.println(jsonObject);
				 
			
			}
			
			else if( action.equals("ImpSmry_getData.impSmrRpt") )
			{
				try
				{		
					PrintWriter out = response.getWriter();
					String firstClick =request.getParameter("firstClick");
					 CommonFilter  commonFilter =populateCommonFilter(request,"improveDrillcommonFilter",false);
					CommonMessage.debugMsg("firstClick123445==="+firstClick);
					if (UIUtils.isValidKeyId(firstClick) && firstClick.trim().equals("N") ) { 
						CommonMessage.debugMsg("firstClick==="+commonFilter.getFirstLevel());
						commonFilter.setFirstLevel("N");
					}
					CommonMessage.debugMsg("firstClick55555==="+commonFilter.getFirstLevel());
					 response.setContentType("text/html");		
					 String flid=request.getParameter("flid");
					String drillflag=request.getParameter("drillFlag");
					if(UIUtils.isValidKeyId(flid))
						commonFilter.setFlid(flid);
					if(UIUtils.isValidKeyId(drillflag)){
						CommonMessage.debugMsg("  (drillflag.trim()).charAt(0)   "+(drillflag.trim()).charAt(0));
						commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
					}
					
					
					 getComboSelectionId(commonFilter.getCompany());
					 getComboSelectionId(commonFilter.getLocation());
					 getComboSelectionId(commonFilter.getSection());
					 getComboSelectionId(commonFilter.getFactory());
					 getComboSelectionId(commonFilter.getCell());
					 getComboSelectionId(commonFilter.getMachine());
					 commonFilter.setIsGetCol("N");
					 List< String[]> jhDrillDnList  = imprvmntSmryService.getAllImprovement(commonFilter);
					 JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(jhDrillDnList,request,1,0);
					
					 CommonMessage.debugMsg("jhDrillDnData " + jhDrillDnData);
					 out.println(jhDrillDnData);	   
					   
					   
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());				
				}
			}
		 
			else if( action.equals("ImpSmry_getExcel.impSmrRpt"))
			{			
				
				CommonFilter commonFilter = populateCommonFilter(request,"improveDrillcommonFilter",false);
						
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);				
				JSONObject tableModel = (JSONObject) httpSession.getAttribute("ImprovementSummaryColData");
				
				tableModel.put("title", "ImprovementSummary Report - "+commonFilter.getDrillCaption()+" Level");
				String format = ExcelUtils.getFormat(request);
				CommonMessage.debugMsg("Before WorkBook....."+format);
				Workbook wb = imprvmntSmryService.improvementSmryReportExportExcel(commonFilter,tableModel,format);
				CommonMessage.debugMsg("After WorkBook.....");
				commonFilter.setFromRow(tmpFromRow);
				CommonMessage.debugMsg("month..."+commonFilter.getFromMonth());
				ExcelUtils.writeToResponse(response, wb, "ImprovementSummaryReport", format);
			}
		 else if(action.equals("ImpSmrySubGrp_input.impSmrRpt"))
		 {			
			 String filterString = request.getParameter("filterStr");			 
			 String compId = request.getParameter("flid");	
			 CommonMessage.debugMsg(compId + " sevlet comp   id" + filterString);
			 request.setAttribute("compId", compId);
			 request.setAttribute("filterStr", filterString);
			 UIUtils.forwardRequest(request, response, "/pages/Reports/ImprovmntsmrySubGroup.jsp");		
		 }
		 
		 
		 else if(action.equals("ImpSmrySubGrp_getCol.impSmrRpt"))
		 {
			String act = request.getParameter("checkVal");			
			String firstClick =request.getParameter("firstClick");			
			 PrintWriter out = response.getWriter();
			 CommonFilter  commonFilter;
			 commonFilter = new CommonFilter(); 
			 String subGrp = request.getParameter("subGrp");
			 String flid = request.getParameter("flid");
			
			/*if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){
				 commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 }		*/				
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 	
			 commonFilter.setFlid(flid);
			 FilterValues.getCommonFilters(request,commonFilter);				
			 FilterValues.getOPLandKaizen(request,commonFilter);
			 httpSession.removeAttribute("improveDrillcommonFilter");
			 httpSession.setAttribute("improveDrillcommonFilter",commonFilter);
			 
			 response.setContentType("text/html");		
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");	
			}
			
			if(commonFilter.getCompany()==null)
			 {
				 String compId = request.getParameter("compId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCompany(company);						
			 }
			if(commonFilter.getLocation()==null)
			 {
				 String compId = request.getParameter("locaId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setLocation(company);
			 }
			 
			 if(commonFilter.getFactory()==null)
			 {
				 String compId = request.getParameter("factId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setFactory(company);
			 }
			 
			  if(commonFilter.getSection()==null)
			 {
				 String compId = request.getParameter("sectId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setSection(company);
			 }
			 
			  if(commonFilter.getCell()==null)
			 {
				 String compId = request.getParameter("cellId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCell(company);	
			 }
			 
			  if(commonFilter.getMachine()==null)
			 {
				 String compId = request.getParameter("mchId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setMachine(company);
			 }
			  commonFilter.setIsGetCol("Y");
			List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrp(commonFilter);	 			 
			 JSONObject jsonObject = getTableModel1(impSubGrpList);	
			 jsonObject.set("tableHeight", "72%%");
		     jsonObject.set("tableWidth", "108%%");
			 httpSession.removeAttribute("ImprovementSummarySubGroupColData");
			 httpSession.setAttribute("ImprovementSummarySubGroupColData", jsonObject);
			  out.println(jsonObject);			
		 }
		 else if(action.equals("ImpSmrySubGrp_getData.impSmrRpt"))
			{	
			 String firstClick =request.getParameter("firstClick");
			 CommonMessage.debugMsg("jhDrillDnDatafirstClick--- " + firstClick);
			 PrintWriter out = response.getWriter();		
			 String flid = request.getParameter("flid");
			 CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 commonFilter.setFlid(flid);
			 getComboSelectionId(commonFilter.getCompany());
			 getComboSelectionId(commonFilter.getLocation());
			 getComboSelectionId(commonFilter.getSection());
			 getComboSelectionId(commonFilter.getFactory());
			 getComboSelectionId(commonFilter.getCell());
			 getComboSelectionId(commonFilter.getMachine());
			 commonFilter.setIsGetCol("N");
			 List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrp(commonFilter);
			 JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(impSubGrpList,request,1,0); // vignesh 2 to 1				
			
			 out.println(jhDrillDnData);
			}
		 
		 else if( action.equals("ImpSmrySubGrp_getExcel.impSmrRpt"))
			{			
				
			 CommonFilter  commonFilter =populateCommonFilter(request,"improveDrillcommonFilter",false);				
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);				
				JSONObject tableModel = (JSONObject) httpSession.getAttribute("ImprovementSummarySubGroupColData");			
				tableModel.put("title", "ImprovementSummary SubGroup - ResultWise ");
				String format = ExcelUtils.getFormat(request);				
				Workbook wb = imprvmntSmryService.improvementSmrySubReportExportExcel(commonFilter,tableModel,format);				
				commonFilter.setFromRow(tmpFromRow);				
				ExcelUtils.writeToResponse(response, wb, "ImprovementSummaryResultWiseReport", format);
			}
		 
		 else if(action.equals("NewImpSmrySubGrp_input.impSmrRpt"))
		 {			
		
			// request.setAttribute("filterStr", filterString);
				String comp = request.getParameter("cmbCompid");
				String locn=request.getParameter("cmbLocnid");
				String fact = request.getParameter("cmbFactid");
				String sect = request.getParameter("cmbSectid");
				String fromMonth=request.getParameter("dtFromMonth");
				String toMonth=request.getParameter("dtToMonth");
				String toDate = request.getParameter("dtToDate");
				String fromDate = request.getParameter("dtFromDate");
				String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
				request.setAttribute("hiddenCompId",comp);
				request.setAttribute("hiddenLocnId",locn);
				request.setAttribute("hiddenFactId",fact);
				request.setAttribute("hiddenSectId",sect);
				request.setAttribute("hiddenFromMonth",fromMonth);
				request.setAttribute("hiddenToMonth",toMonth);
				request.setAttribute("hdnfromdate", fromDate);
				request.setAttribute("hdntodate", toDate);
			 UIUtils.forwardRequest(request, response, "/pages/Reports/NewImprovmntsmrySubGroup.jsp");		
		 }
		
		 else if(action.equals("NewImpSmrySubGrp_getCol.impSmrRpt"))
		 {
			String act = request.getParameter("checkVal");			
			String firstClick =request.getParameter("firstClick");			
			 PrintWriter out = response.getWriter();
			 CommonFilter  commonFilter;
			 commonFilter = new CommonFilter(); 
			 String subGrp = request.getParameter("subGrp");
			 String flid = request.getParameter("flid");
			
			/*if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){
				 commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 }		*/				
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 	
			 commonFilter.setFlid(flid);
			 FilterValues.getCommonFilters(request,commonFilter);				
			 FilterValues.getOPLandKaizen(request,commonFilter);
			 httpSession.removeAttribute("improveDrillcommonFilter");
			 httpSession.setAttribute("improveDrillcommonFilter",commonFilter);
			 
			 response.setContentType("text/html");		
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");	
			}
			
			if(commonFilter.getCompany()==null)
			 {
				 String compId = request.getParameter("compId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCompany(company);						
			 }
			if(commonFilter.getLocation()==null)
			 {
				 String compId = request.getParameter("locaId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setLocation(company);
			 }
			 
			 if(commonFilter.getFactory()==null)
			 {
				 String compId = request.getParameter("factId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setFactory(company);
			 }
			 
			  if(commonFilter.getSection()==null)
			 {
				 String compId = request.getParameter("sectId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setSection(company);
			 }
			 
			  if(commonFilter.getCell()==null)
			 {
				 String compId = request.getParameter("cellId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCell(company);	
			 }
			 
			  if(commonFilter.getMachine()==null)
			 {
				 String compId = request.getParameter("mchId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setMachine(company);
			 }
			 
			List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrp(commonFilter);	 			 
			 JSONObject jsonObject = getTableModel1(impSubGrpList);	
			 jsonObject.set("tableHeight", "72%%");
		     jsonObject.set("tableWidth", "108%%");
			 httpSession.removeAttribute("ImprovementSummarySubGroupColData");
			 httpSession.setAttribute("ImprovementSummarySubGroupColData", jsonObject);
			  out.println(jsonObject);			
		 }
		
		 else if(action.equals("NewImpSmrySubGrp_getData.impSmrRpt"))
			{	
			 String firstClick =request.getParameter("firstClick");
			 CommonMessage.debugMsg("jhDrillDnDatafirstClick--- " + firstClick);
			 PrintWriter out = response.getWriter();		
			 String flid = request.getParameter("flid");
			 CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 commonFilter.setFlid(flid);
			 getComboSelectionId(commonFilter.getCompany());
			 getComboSelectionId(commonFilter.getLocation());
			 getComboSelectionId(commonFilter.getSection());
			 getComboSelectionId(commonFilter.getFactory());
			 getComboSelectionId(commonFilter.getCell());
			 getComboSelectionId(commonFilter.getMachine());
			
			 List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrp(commonFilter);
			 JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(impSubGrpList,request,1,0);				
			
			 out.println(jhDrillDnData);
			}
		  
		 else if( action.equals("NewImpSmrySubGrp_getExcel.impSmrRpt"))
			{			
				
			   CommonFilter  commonFilter =populateCommonFilter(request,"improveDrillcommonFilter",false);				
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);				
				JSONObject tableModel = (JSONObject) httpSession.getAttribute("ImprovementSummarySubGroupColData");			
				tableModel.put("title", "ImprovementSummary SubGroup - ResultWise ");
				String format = ExcelUtils.getFormat(request);				
				Workbook wb = imprvmntSmryService.improvementSmrySubReportExportExcel(commonFilter,tableModel,format);				
				commonFilter.setFromRow(tmpFromRow);				
				ExcelUtils.writeToResponse(response, wb, "ImprovementSummaryResultWiseReport", format);
			}
		 
		 else if(action.equals("newpiechart.impSmrRpt")){
			 
				
        	 CommonFilter commonFilter = populateCommonFilter(request,"improveDrillcommonFilter",false);
        	 String flids=CommonFunctions.getLoginFlid(request);
 			String lcnname=dashboardService.Functionallocn(flids);
     		String forDashboard = request.getParameter("dashboard");
     		CommonMessage.debugMsg("The Dashboard Is::"+forDashboard);
     		String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
     		if( ! "true".equals(forDashboard)){
     			CommonMessage.debugMsg("to check dash board");
     			commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
     		}
     		else{
     			commonFilter = new  CommonFilter();
     			CommonMessage.debugMsg("to check dash board true ");
     			FilterValues.getCommonFilters(request,commonFilter);
     			FilterValues.getAbnRelatedFilters(request, commonFilter);
     		
     			
     		}
     		
     		
     		if(commonFilter.getRowTotal() == null )
     			commonFilter.setRowTotal('Y');
         
	 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
		  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
		  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
		  commonFilter.setMonwise("Y");
 	  }
			List<String[]> graphData  = imprvmntSmryService.getAllImprovementSubGrp(commonFilter);
			
			
			ChartPie pieChart =  new   ChartPie();
			
			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
			
			PrintWriter out = response.getWriter();
			//String[] header = graphData.get(1);
			String[] header =new String[ graphData.size()];
			String[] data = new String[ graphData.size()];
		
			int k=0;
			for(String [] str:graphData){
				//CommonMessage.debugMsg("Value of K:"+k);
				if(k>1){
					header[k]=str[2];
					data[k]=str[3];
					
				}
				CommonMessage.debugMsg(str[2] + "   " + str[3] + "  " + str.length);
				k++;
				
				
			}
		
			String subTitle = "";//header[3]
			
			List<Object[]> dataList = new ArrayList<Object[]>();
		
			for(int i=2;i<data.length;i++){
				CommonMessage.debugMsg(data[i]+"  data[i]  header[i] "+header[i]);
			}
			
			for(int i=2;i<data.length-1;i++){
				Object []  pieData = new Object[2];
				pieData[ 0 ] =header[i];
				pieData[ 1 ] = Double.parseDouble(data[i]);
				dataList.add(pieData);
			}
			
			ChartSeries chartSeries = new ChartSeries();
			
			chartSeries.setType(ChartTypes.PIE);
			chartSeries.setData(dataList);
			chartSeriesList.add(chartSeries);
			chartSeries.setSize(200);
			 
			JSONObject chartObj =pieChart.drawChart(chartSeriesList,lcnname+"-Classification  Of Kaizen Count" + " - "+fromMonth+"-"+toMonth,subTitle);
			UIUtils.setDashBoardIdentifier(request,chartObj);
				
			out.println(chartObj);
			out.close();
		  }
		 
		 else if(action.equals("piechart.impSmrRpt")){
			 
			 String forDashboard = request.getParameter("dashboard");
			 String flid = request.getParameter("flid");	
			 String subGrp=request.getParameter("subGrp");
			 CommonMessage.debugMsg(flid + " piechart keyid");
				CommonFilter commonFilter = null;
				CommonFilter chrtCommonFilter = new  CommonFilter();
				
				
				if( ! "true".equals(forDashboard)){
					commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
				}
				else{
					FilterValues.getSafty(request, chrtCommonFilter);
				}
				
				commonFilter.setFlid(flid);
				
				BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
				FilterValues.getCommonFilters(request, chrtCommonFilter) ;
				chrtCommonFilter.setFlid(flid);
				String rownum=request.getParameter("rownum");
				
				
				String fromMonth = chrtCommonFilter.getFromMonth();
				String toMonth = chrtCommonFilter.getToMonth();
				
				String month1   = fromMonth +"  -  "+ toMonth ;
				String fromdate = chrtCommonFilter.getFromDate();
				String todate = chrtCommonFilter.getToDate();
				String date   = fromdate +"  -  "+ todate ;
				String title="";
				if(subGrp.equals("P")){
					 title = "Pillar Wise Kaize Report" + " - "+date;
					if( chrtCommonFilter.getMonwise().equals("Y")){
					title = "Pillar Wise Kaize Report" + " - "+month1;
				}
				}if(subGrp.equals("R")){
					title = "Result Wise Kaize Report" + " - "+date;
					if( chrtCommonFilter.getMonwise().equals("Y")){
					title = "Result Wise Kaize Report" + " - "+month1;
					}
				}
				if(chrtCommonFilter.getRowTotal() == null )
					chrtCommonFilter.setRowTotal('N');
				
				chrtCommonFilter.setStatus(subGrp);
				List<String[]> graphData  = imprvmntSmryService.getpiechart(chrtCommonFilter);
				
				
				ChartPie pieChart =  new   ChartPie();
				
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
				
				PrintWriter out = response.getWriter();
				//String[] header = graphData.get(1);
				String[] header =new String[ graphData.size()];
				String[] data = new String[ graphData.size()];
			
				int k=0;
				for(String [] str:graphData){
					//CommonMessage.debugMsg("Value of K:"+k);
					if(k>1){
						header[k]=str[2];
						data[k]=str[3];
						
					}
					CommonMessage.debugMsg(str[2] + "   " + str[3] + "  " + str.length);
					k++;
					
					
				}
			
				String subTitle = "";//header[3]
				
				List<Object[]> dataList = new ArrayList<Object[]>();
			
				for(int i=2;i<data.length;i++){
					CommonMessage.debugMsg(data[i]+"  data[i]  header[i] "+header[i]);
				}
				
				for(int i=2;i<data.length-1;i++){
					Object []  pieData = new Object[2];
					pieData[ 0 ] =header[i];
					pieData[ 1 ] = Double.parseDouble(data[i]);
					dataList.add(pieData);
				}
				
				ChartSeries chartSeries = new ChartSeries();
				
				chartSeries.setType(ChartTypes.PIE);
				chartSeries.setData(dataList);
				chartSeriesList.add(chartSeries);
				chartSeries.setSize(200);
				
				JSONObject chartObj =pieChart.drawChart(chartSeriesList, title, subTitle);
				UIUtils.setDashBoardIdentifier(request,chartObj);
					
				out.println(chartObj);
				out.close();
			  }
		 
		 else if(action.equals("ImpSmrySubGrpLoss_input.impSmrRpt"))
		 {			
			 String filterString = request.getParameter("filterStr");			
			 String compId = request.getParameter("flid");			
			 request.setAttribute("compId", compId);
			 request.setAttribute("filterStr", filterString);
			 UIUtils.forwardRequest(request, response, "/pages/Reports/ImprovmntsmrySubGroup.jsp");		
		 }
		 
		 else if(action.equals("ImpSmrySubGrpLoss_getCol.impSmrRpt"))
		 {
			String act = request.getParameter("checkVal");			
			String firstClick =request.getParameter("firstClick");			
			 PrintWriter out = response.getWriter();
			 CommonFilter  commonFilter;
			 commonFilter = new CommonFilter(); 
			 String subGrp = request.getParameter("subGrp");
				
			if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){				
				 commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 }		
			
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 			
			 FilterValues.getCommonFilters(request,commonFilter);				
			 FilterValues.getOPLandKaizen(request,commonFilter);
			 			 
			 httpSession.removeAttribute("improveDrillcommonFilter");
			 httpSession.setAttribute("improveDrillcommonFilter",commonFilter);
			 
			 response.setContentType("text/html");		
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");	
			}
			
			 if(commonFilter.getCompany()==null)
			 {
				 String compId = request.getParameter("compId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCompany(company);						
			 }
			 
			  if(commonFilter.getLocation()==null)
			 {
				 String compId = request.getParameter("locaId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setLocation(company);
			 }
			 
			 
			  if(commonFilter.getFactory()==null)
			 {
				 String compId = request.getParameter("factId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setFactory(company);
			 }
			 
			  if(commonFilter.getSection()==null)
			 {
				 String compId = request.getParameter("sectId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setSection(company);	
			 }
			 
			  if(commonFilter.getCell()==null)
			 {
				 String compId = request.getParameter("cellId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCell(company);
			 }
			 
			  if(commonFilter.getMachine()==null)
			 {
				 String compId = request.getParameter("mchId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setMachine(company);
			 }
			 
			 
			List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrpLoss(commonFilter);	 			 
			 JSONObject jsonObject = getTableModel1(impSubGrpList);		
			 httpSession.removeAttribute("ImprovementSummarySubLossGroupColData");
			 httpSession.setAttribute("ImprovementSummarySubLossGroupColData", jsonObject);
			  out.println(jsonObject);			
		 }
		 else if(action.equals("ImpSmrySubGrpLoss_getData.impSmrRpt"))
			{	
			 String firstClick =request.getParameter("firstClick");			 
			 PrintWriter out = response.getWriter();			
			 CommonFilter  commonFilter  ;
			 commonFilter = new CommonFilter(); 			 
					
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){
				 commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 }		
			 
			 getComboSelectionId(commonFilter.getCompany());
			 getComboSelectionId(commonFilter.getLocation());
			 getComboSelectionId(commonFilter.getSection());
			 getComboSelectionId(commonFilter.getFactory());
			 getComboSelectionId(commonFilter.getCell());
			 getComboSelectionId(commonFilter.getMachine());
			 
			 List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrpLoss(commonFilter);
			 JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(impSubGrpList,request,2,0);			
			  out.println(jhDrillDnData);
			}
		 else if( action.equals("ImpSmrySubGrpLoss_getExcel.impSmrRpt"))
			{			
				CommonFilter  commonFilter =populateCommonFilter(request,"improveDrillcommonFilter",false);				
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);				
				JSONObject tableModel = (JSONObject) httpSession.getAttribute("ImprovementSummarySubLossGroupColData");			
				tableModel.put("title", "ImprovementSummary SubGroup -LossWise ");
				String format = ExcelUtils.getFormat(request);				
				Workbook wb = imprvmntSmryService.improvementSmrySubLossReportExportExcel(commonFilter,tableModel,format);				
				commonFilter.setFromRow(tmpFromRow);				
				ExcelUtils.writeToResponse(response, wb, "ImprovementSummaryLossWiseReport", format);
			}
		
		 
		 else if(action.equals("ImpSmrySubGrpPiller_input.impSmrRpt"))
		 {			
			 String filterString = request.getParameter("filterStr");			
			 String compId = request.getParameter("flid");					
			 request.setAttribute("compId", compId);
			 request.setAttribute("filterStr", filterString);
			 UIUtils.forwardRequest(request, response, "/pages/Reports/ImprovmntsmrySubGroup.jsp");		
		 }
		 
		 else if(action.equals("ImpSmrySubGrpPiller_getCol.impSmrRpt"))
		 {
			 String act = request.getParameter("checkVal");			
			 String firstClick =request.getParameter("firstClick");			
			 PrintWriter out = response.getWriter();
			 CommonFilter  commonFilter;
			 commonFilter = new CommonFilter(); 
			
						
			if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){			
				
				 commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 }						
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 			
			 FilterValues.getCommonFilters(request,commonFilter);				
			 FilterValues.getOPLandKaizen(request,commonFilter);
			
			 httpSession.removeAttribute("improveDrillcommonFilter");
			 httpSession.setAttribute("improveDrillcommonFilter",commonFilter);
			 
			 response.setContentType("text/html");		
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");	
			}
			
			 if(commonFilter.getCompany()==(null))
			 {
				 String compId = request.getParameter("compId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCompany(company);	
			 }
			 
			 if(commonFilter.getLocation()==null)
			 {
				 String compId = request.getParameter("locaId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setLocation(company);
			 }
			 
			 if(commonFilter.getFactory()==null)
			 {
				 String compId = request.getParameter("factId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setFactory(company);
			 }
			 
			 if(commonFilter.getSection()==null)
			 {
				 String compId = request.getParameter("sectId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setSection(company);	
			 }
			 
			 if(commonFilter.getCell()==null)
			 {
				 String compId = request.getParameter("cellId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCell(company);
			 }
			 
			 if(commonFilter.getMachine()==null)
			 {
				 String compId = request.getParameter("mchId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setMachine(company);
			 }
			 
			List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrpPiller(commonFilter);	 			 
			 JSONObject jsonObject = getTableModel1(impSubGrpList);		
			 httpSession.removeAttribute("ImprovementSummarySubPillerGroupColData");
			 httpSession.setAttribute("ImprovementSummarySubPillerGroupColData", jsonObject);
			  out.println(jsonObject);			
		 }
		 else if(action.equals("ImpSmrySubGrpPiller_getData.impSmrRpt"))
			{	
			 String firstClick =request.getParameter("firstClick");
			 CommonMessage.debugMsg("jhDrillDnDatafirstClick--- " + firstClick);
			 PrintWriter out = response.getWriter();		
			 String flid = request.getParameter("flid");
			 CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 commonFilter.setFlid(flid);
			 getComboSelectionId(commonFilter.getCompany());
			 getComboSelectionId(commonFilter.getLocation());
			 getComboSelectionId(commonFilter.getSection());
			 getComboSelectionId(commonFilter.getFactory());
			 getComboSelectionId(commonFilter.getCell());
			 getComboSelectionId(commonFilter.getMachine());
			 
			 /*
			 String firstClick =request.getParameter("firstClick");
			 CommonMessage.debugMsg("jhDrillDnDatafirstClick--- " + firstClick);
			 PrintWriter out = response.getWriter();			
			 CommonFilter  commonFilter  ;
			 commonFilter = new CommonFilter(); 			 
					
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){					
				
				 commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 }			
			 
			 getComboSelectionId(commonFilter.getCompany());
			 getComboSelectionId(commonFilter.getLocation());
			 getComboSelectionId(commonFilter.getSection());
			 getComboSelectionId(commonFilter.getFactory());
			 getComboSelectionId(commonFilter.getCell());
			 getComboSelectionId(commonFilter.getMachine());
			*/
			 List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrpPiller(commonFilter);
			 JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(impSubGrpList,request,1,0);				
			
			 out.println(jhDrillDnData);
			}
		 
		 else if( action.equals("ImpSmrySubGrpPiller_getExcel.impSmrRpt"))
			{			
				
				
			 	CommonFilter commonFilter =populateCommonFilter(request,"improveDrillcommonFilter",false);				
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);				
				JSONObject tableModel = (JSONObject) httpSession.getAttribute("ImprovementSummarySubPillerGroupColData");				
				tableModel.put("title", "ImprovementSummary SubGroup - PillerWise  ");
				String format = ExcelUtils.getFormat(request);				
				Workbook wb = imprvmntSmryService.improvementSmrySubPillerReportExportExcel(commonFilter,tableModel,format);			
				commonFilter.setFromRow(tmpFromRow);			
				ExcelUtils.writeToResponse(response, wb, "ImprovementSummaryPillerWiseReport", format);
			}
		 
		 else if(action.equals("ImpSmrySubGrpEqp_input.impSmrRpt"))
		 {			
			 String filterString = request.getParameter("filterStr");			
			 String compId = request.getParameter("flid");				
			 request.setAttribute("compId", compId);
			 request.setAttribute("filterStr", filterString);
			 UIUtils.forwardRequest(request, response, "/pages/Reports/ImprovmntsmrySubGroup.jsp");		
		 }
		 
		 else if(action.equals("ImpSmrySubGrpEqp_getCol.impSmrRpt"))
		 {
			String act = request.getParameter("checkVal");			
			String firstClick =request.getParameter("firstClick");			
			 PrintWriter out = response.getWriter();
			 CommonFilter  commonFilter;
			 commonFilter = new CommonFilter(); 
			 String subGrp = request.getParameter("subGrp");
					
			if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){					 
				 commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 }						
			 if(commonFilter==null)
				 commonFilter = new CommonFilter(); 			
			 FilterValues.getCommonFilters(request,commonFilter);				
			 FilterValues.getOPLandKaizen(request,commonFilter);
			 httpSession.removeAttribute("improveDrillcommonFilter");
			 httpSession.setAttribute("improveDrillcommonFilter",commonFilter);
			
			 response.setContentType("text/html");		
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");	
			}
			
			 if(commonFilter.getCompany()==null)
			 {
				 String compId = request.getParameter("compId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCompany(company);	
			 }
			 
			 if(commonFilter.getLocation()==null)
			 {
				 String compId = request.getParameter("locaId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setLocation(company);
			 }
			 
			 if(commonFilter.getFactory()==null)
			 {
				 String compId = request.getParameter("factId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setFactory(company);
			 }
			 
			 if(commonFilter.getSection()==null)
			 {
				 String compId = request.getParameter("sectId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setSection(company);	
			 }
			 
			 if(commonFilter.getCell()==null)
			 {
				 String compId = request.getParameter("cellId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setCell(company);	
			 }
			 
			 if(commonFilter.getMachine()==null)
			 {
				 String compId = request.getParameter("mchId");				
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId:"{}");				
					commonFilter.setMachine(company);
			 }
			 
			List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrpEqp(commonFilter);	 			 
			 JSONObject jsonObject = getTableModel1(impSubGrpList);		
			 httpSession.removeAttribute("ImprovementSummarySubEquipGroupColData");
			 httpSession.setAttribute("ImprovementSummarySubEquipGroupColData", jsonObject);
			  out.println(jsonObject);			
		 }
		 else if(action.equals("ImpSmrySubGrpEqp_getData.impSmrRpt"))
			{	
			 String firstClick =request.getParameter("firstClick");			
			 PrintWriter out = response.getWriter();			
			 CommonFilter  commonFilter  ;
			 commonFilter = new CommonFilter(); 			 
					
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){					
				
				 commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
			 }		
			 
			 getComboSelectionId(commonFilter.getCompany());
			 getComboSelectionId(commonFilter.getLocation());
			 getComboSelectionId(commonFilter.getSection());
			 getComboSelectionId(commonFilter.getFactory());
			 getComboSelectionId(commonFilter.getCell());
			 getComboSelectionId(commonFilter.getMachine());
			 
			
			 
			 List< String[]> impSubGrpList  = imprvmntSmryService.getAllImprovementSubGrpEqp(commonFilter);
			 JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(impSubGrpList,request,2,0);				
			 CommonMessage.debugMsg("jhDrillDnData " + jhDrillDnData);
			 out.println(jhDrillDnData);
			}
	
		 else if( action.equals("ImpSmrySubGrpEqp_getExcel.impSmrRpt"))
			{			
				
				
			 	CommonFilter commonFilter =populateCommonFilter(request,"improveDrillcommonFilter",false);
				
				
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);				
				JSONObject tableModel = (JSONObject) httpSession.getAttribute("ImprovementSummarySubEquipGroupColData");
				tableModel.put("title", "ImprovementSummary SubGroup-EquipmentWise ");
				String format = ExcelUtils.getFormat(request);				
				Workbook wb = imprvmntSmryService.improvementSmrySubEquipReportExportExcel(commonFilter,tableModel,format);				
				commonFilter.setFromRow(tmpFromRow);				
				ExcelUtils.writeToResponse(response, wb, "ImprovementSummaryEquipmentWiseReport", format);
			}
		 
		 //added on 29oct14
			else if (action.equals("ImpSmryView_input.impSmrRpt")) {
				String filterString = request.getParameter("filterStr");
				String compId = request.getParameter("flid");
				CommonMessage.debugMsg("My Test Start:" + compId + " sevlet comp   id" + filterString);
				request.setAttribute("compId", compId);
				request.setAttribute("filterStr", filterString);
				UIUtils.forwardRequest(request, response,"/pages/SuggestionTransaction.jsp");
			}

			else if (action.equals("ImpSmryView_getCol.impSmrRpt")) {
				String act = request.getParameter("checkVal");
				String firstClick = request.getParameter("firstClick");
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter=populateCommonFilter(request, "TransactionViewCommonFilter", true);
				String flid = request.getParameter("flid");
				commonFilter.setFlid(flid);
				response.setContentType("text/html");
				if (Constants.passNullDate.contains(commonFilter.getFromMonth())
						&& (commonFilter.getMonwise() == null || commonFilter
								.getMonwise().equals("Y"))) {
					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(
							-5).substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate().substring(3,
							11));
					commonFilter.setMonwise("Y");
				}

				if (commonFilter.getCompany() == null) {
					String compId = request.getParameter("compId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setCompany(company);
				}
				if (commonFilter.getLocation() == null) {
					String compId = request.getParameter("locaId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setLocation(company);
				}

				if (commonFilter.getFactory() == null) {
					String compId = request.getParameter("factId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setFactory(company);
				}

				if (commonFilter.getSection() == null) {
					String compId = request.getParameter("sectId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setSection(company);
				}

				if (commonFilter.getCell() == null) {
					String compId = request.getParameter("cellId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setCell(company);
				}

				if (commonFilter.getMachine() == null) {
					String compId = request.getParameter("mchId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setMachine(company);
				}

				httpSession.removeAttribute("TransactionViewCommonFilter");
				httpSession.setAttribute("TransactionViewCommonFilter", commonFilter);

				commonFilter.setIsGetCol("Y");
				List<String[]> suggestionGridData=kaizenReportService.getKaizenSuggestionSummaryGridData(commonFilter);			
				JqGridTableModel jqGridTableModel = new JqGridTableModel();
				GridColModel gridColModel = new GridColModel();

				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);

				gridColModel.setHeaderNum(1);

				String[] colHeader = suggestionGridData.get(1);  // 2 to 1 vignesh 
				String[] colHeaderCond = suggestionGridData.get(0);  // 1 to  0 vignesh
				List<String[]> headers = new ArrayList<String[]>();
				
				headers.add(colHeader);
				
				JSONObject colModel =new JSONObject();
				colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
				colModel.set("tableHeight", "80%%");
				colModel.set("tableWidth", "110%%");
				httpSession.removeAttribute("suggestionSummaryColData");
				httpSession.setAttribute("suggestionSummaryColData", colModel);

				CommonMessage.debugMsg(colModel);
				out.println(colModel);

			} else if (action.equals("ImpSmryView_getData.impSmrRpt")) {
				String firstClick = request.getParameter("firstClick");
				CommonMessage.debugMsg("Suggestion View--- " + firstClick);
				PrintWriter out = response.getWriter();
				String flid = request.getParameter("flid");
				//CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
				CommonFilter commonFilter=populateCommonFilter(request, "TransactionViewCommonFilter", false);
				commonFilter.setFlid(flid);
				getComboSelectionId(commonFilter.getCompany());
				getComboSelectionId(commonFilter.getLocation());
				getComboSelectionId(commonFilter.getSection());
				getComboSelectionId(commonFilter.getFactory());
				getComboSelectionId(commonFilter.getCell());
				getComboSelectionId(commonFilter.getMachine());
				commonFilter.setIsGetCol("N");
				List<String[]> suggestionGridData = kaizenReportService.getKaizenSuggestionSummaryGridData(commonFilter);
				JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(suggestionGridData, request, 2, 0,commonFilter.getTotalRecordCnt());

				CommonMessage.debugMsg("Suggestion View Data " + suggestionGridData);
				out.println(jhDrillDnData);
			}

			else if (action.equals("ImpSmryView_getExcel.impSmrRpt")) {

				CommonFilter commonFilter = populateCommonFilter(request,"TransactionViewCommonFilter", false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject tableModel = (JSONObject) httpSession.getAttribute("suggestionSummaryColData");
				tableModel.put("title", "Kaizen Suggestions ");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = kaizenReportService.getKaizenSuggestionSummaryGridDataExportExcel(commonFilter, tableModel, format);
				//Workbook wb = imprvmntSmryService.improvementSmrySubReportExportExcel(commonFilter,tableModel, format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb,"SuggestionSummary", format);
			}
			/**************Action to view kaizen transaction **************/
			else if (action.equals("ImpSmrykaizen_input.impSmrRpt")) {
				String filterString = request.getParameter("filterStr");
				String compId = request.getParameter("flid");
				CommonMessage.debugMsg("My Test Start:" + compId + " sevlet comp   id" + filterString);
				request.setAttribute("compId", compId);
				request.setAttribute("filterStr", filterString);
				UIUtils.forwardRequest(request, response,"/pages/ImplementedKaizen.jsp");
			}

			else if (action.equals("ImpSmrykaizen_getCol.impSmrRpt")) {
				CommonMessage.debugMsg("My Test Start 2:" );
				String act = request.getParameter("checkVal");
				String firstClick = request.getParameter("firstClick");
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter=populateCommonFilter(request, "TransactionViewCommonFilter", true);
				String flid = request.getParameter("flid");
				commonFilter.setFlid(flid);
				
				response.setContentType("text/html");
				if (Constants.passNullDate.contains(commonFilter.getFromMonth())
						&& (commonFilter.getMonwise() == null || commonFilter
								.getMonwise().equals("Y"))) {
					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(
							-5).substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate().substring(3,
							11));
					commonFilter.setMonwise("Y");
				}

				if (commonFilter.getCompany() == null) {
					String compId = request.getParameter("compId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setCompany(company);
				}
				if (commonFilter.getLocation() == null) {
					String compId = request.getParameter("locaId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setLocation(company);
				}

				if (commonFilter.getFactory() == null) {
					String compId = request.getParameter("factId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setFactory(company);
				}

				if (commonFilter.getSection() == null) {
					String compId = request.getParameter("sectId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setSection(company);
				}

				if (commonFilter.getCell() == null) {
					String compId = request.getParameter("cellId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setCell(company);
				}

				if (commonFilter.getMachine() == null) {
					String compId = request.getParameter("mchId");
					ComboFilter company = new ComboFilter();
					company.setId(compId != null ? compId : "{}");
					commonFilter.setMachine(company);
				}

				httpSession.removeAttribute("TransactionViewCommonFilter");
				httpSession.setAttribute("TransactionViewCommonFilter", commonFilter);

				
				List<String[]> suggestionGridData=kaizenReportService.getKaizenSummaryGridData(commonFilter);		
				//List<String[]> suggestionGridData=kaizenReportService.getKaizenSuggestionSummaryGridData(commonFilter);	
				JqGridTableModel jqGridTableModel = new JqGridTableModel();
				GridColModel gridColModel = new GridColModel();

				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);

				gridColModel.setHeaderNum(1);  //  1 to 0 vignesh 

				String[] colHeader = suggestionGridData.get(1); //  2 to 1 vignesh 
				String[] colHeaderCond = suggestionGridData.get(0);  //  1 to 0 vignesh 
				List<String[]> headers = new ArrayList<String[]>();
				
				headers.add(colHeader);
				
				JSONObject colModel =new JSONObject();
				colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
				colModel.set("tableHeight", "80%%");
				colModel.set("tableWidth", "110%%");
				httpSession.removeAttribute("suggestionSummaryColData");
				httpSession.setAttribute("suggestionSummaryColData", colModel);

				CommonMessage.debugMsg(colModel);
				out.println(colModel);

			} else if (action.equals("ImpSmrykaizen_getData.impSmrRpt")) {
				String firstClick = request.getParameter("firstClick");
				CommonMessage.debugMsg("Suggestion View--- " + firstClick);
				PrintWriter out = response.getWriter();
				String flid = request.getParameter("flid");
				//CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("improveDrillcommonFilter");
				CommonFilter commonFilter=populateCommonFilter(request, "TransactionViewCommonFilter", false);
				commonFilter.setFlid(flid);
				getComboSelectionId(commonFilter.getCompany());
				getComboSelectionId(commonFilter.getLocation());
				getComboSelectionId(commonFilter.getSection());
				getComboSelectionId(commonFilter.getFactory());
				getComboSelectionId(commonFilter.getCell());
				getComboSelectionId(commonFilter.getMachine());

				List<String[]> suggestionGridData = kaizenReportService.getKaizenSummaryGridData(commonFilter);
				JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(suggestionGridData, request, 2, 0,commonFilter.getTotalRecordCnt());//elumalai feb 07

				CommonMessage.debugMsg("Suggestion View Data " + suggestionGridData);
				out.println(jhDrillDnData);
			}

			else if (action.equals("ImpSmrykaizen_getExcel.impSmrRpt")) {

				CommonFilter commonFilter = populateCommonFilter(request,"TransactionViewCommonFilter", false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject tableModel = (JSONObject) httpSession.getAttribute("suggestionSummaryColData");
				tableModel.put("title", "Kaizen Report ");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = kaizenReportService.getKaizenSummaryGridDataExportExcel(commonFilter, tableModel, format);
				//Workbook wb = kaizenReportService.getKaizenSuggestionSummaryGridDataExportExcel(commonFilter, tableModel, format);
				
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb,"ImplementedKaizen", format);
			}
	}
	private JSONObject getTableModel1(List<String[]> headers)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);	// --vignesh to 0	
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.getRowHeaders().add(colHeader);
		
		CommonMessage.debugMsg("colHeader.length:"+colHeader.length);
		
		for(int i =0; i < colHeader.length; i++)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
			jqGridTableModel.setTableButton(true);		
				
			jqGridColModel.setWidth(300);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0 )
			{
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}else if(i==1){
				jqGridColModel.setHidden(true);
			}
			else if(i==2)
			{
				jqGridColModel.setWidth(160);				
				jqGridColModel.setAlign("left");
			}
			else if(i==3)
			{
				
				jqGridColModel.setWidth(120);				
				jqGridColModel.setAlign("right");
			}else if(i>3)
			{
				jqGridColModel.setHidden(true);
				
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);		 
		 
		 tableModel.set("tableHeight", "90%%");
		 return tableModel;
		
	}
	private JSONObject getTableModel(List<String[]> headers,String caption)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);			
		jqGridTableModel.getRowHeaders().add(colHeader);
		
		colHeader[2] = caption;  
		CommonMessage.debugMsg("colHeader.length:"+colHeader.length);
		for(int i =0; i < colHeader.length; i++)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
			jqGridTableModel.setTableButton(true);		
				
			jqGridColModel.setWidth(300);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0 || i==1)
			{
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}
			else if(i==2)
			{
				jqGridColModel.setSummaryType("count");
				jqGridColModel.setSummaryTpl("<b><font color=blue>Plant </font> </b>");
			}
			else if(i>2)
			{
				jqGridColModel.setWidth(120);				
				jqGridColModel.setAlign("right");
			}
			else
			{
			jqGridColModel.setSummaryType("sum");
			jqGridColModel.setSummaryTpl("<b><font color=blue>{0} </font> </b>");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);		 
		 
		 tableModel.set("tableHeight", "90%%");
		 return tableModel;
	}

	
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			CommonMessage.debugMsg("First Click========000");
			//commonFilter.setViewClick('N');
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getOPLandKaizen(request,commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
			CommonMessage.debugMsg("First Click========111");
		}
		
		return commonFilter;
	}
	private static String getComboSelectionId(ComboFilter comboFilter){ 		
		if( comboFilter != null && UIUtils.isValidKeyId( comboFilter.getId() ) )
			return comboFilter.getId();
		return "";
	}
}



