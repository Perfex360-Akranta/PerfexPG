package com.akranta.tpm.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_OeearprqrService;
import com.akranta.tpm.service.impl.BAL_OeearprqrServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;


public class BAL_OeearprqrServlet extends HttpServlet {	
	/**
	 * Created By:N.Arun
	 */
	private static final long serialVersionUID = 1L;
	BAL_OeearprqrService oeearprqrService ; 
	private static String reportType=null;
	public BAL_OeearprqrServlet(){
		/*try {
			oeearprqrService = new OeearprqrServiceImpl();
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
				
			//PrintWriter out = response.getWriter();
		String action = UIUtils.getActionPart(request);
		try {
			oeearprqrService = (BAL_OeearprqrServiceImpl)UIUtils.getServiceObject(request,"BAL_OeearprqrServiceImpl");
			
			HttpSession h = request.getSession(false);

			oeearprqrService.BAL_OeearprqrServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
			
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		HttpSession httpSession = request.getSession(false);
		if( action.equals("filterXmlOeearprqr_input.baloeear")){
			response.setContentType("xml"); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/oeearprqr.xml") ;
		 }
		else if(action.equals("Oeearprqr_input.baloeear")) 
		{
			String comp = request.getParameter("cmbCompid");
			String locn = request.getParameter("cmbLocnid");
			String fact = request.getParameter("cmbFactid");
			String sect = request.getParameter("cmbSectid");
			String cell = request.getParameter("cmbCellid");
			String mchm = request.getParameter("cmbMchid");
			String parentId= request.getParameter("parentId");
			String fromMonth=request.getParameter("dtFromMonth");
			String toMonth=request.getParameter("dtToMonth");
			 
			String chkMonthwise=request.getParameter("chkMonthwise");
			String chkDatewise=request.getParameter("chkDatewise");
			if(UIUtils.isValidKeyId(comp))
			{
				request.setAttribute("hiddenIds",comp);
			    httpSession.removeAttribute("hiddenIds");
			    httpSession.setAttribute("hiddenIds",comp);
			}
			if(UIUtils.isValidKeyId(locn))
			{
				request.setAttribute("hiddenIds",locn);
			    httpSession.removeAttribute("hiddenIds");
			    httpSession.setAttribute("hiddenIds",locn);
			}
			if(UIUtils.isValidKeyId(fact))
			{
				request.setAttribute("hiddenIds",fact);
			    httpSession.removeAttribute("hiddenIds");
			    httpSession.setAttribute("hiddenIds",fact);
			}
			if(UIUtils.isValidKeyId(sect)){
				request.setAttribute("hiddenIds",sect);
			    httpSession.removeAttribute("hiddenIds");
			    httpSession.setAttribute("hiddenIds",sect);
			}
			if(UIUtils.isValidKeyId(cell)){
				request.setAttribute("hiddenIds",cell);
			    httpSession.removeAttribute("hiddenIds");
			    httpSession.setAttribute("hiddenIds",cell);
			}
			if(UIUtils.isValidKeyId(mchm)){
				request.setAttribute("hiddenIds",mchm);
			    httpSession.removeAttribute("hiddenIds");
			    httpSession.setAttribute("hiddenIds",mchm);
			}
			if(UIUtils.isValidKeyId(parentId)){
				request.setAttribute("hiddenparentId",parentId);
			    httpSession.removeAttribute("hiddenparentId");
			    httpSession.setAttribute("hiddenparentId",parentId);
			}
			request.setAttribute("drilldownMsgs", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			request.setAttribute("cmbCompid",comp);
			request.setAttribute("fromMonth", fromMonth);
			request.setAttribute("toMonth", toMonth);
			request.setAttribute("chkMonthwise",chkMonthwise);
			request.setAttribute("chkDatewise",chkDatewise);
			UIUtils.forwardRequest(request, response,"/pages/Reports/Oeearprqr.jsp"); 
		
		}
		else if(action.equals("Oeearprqr_getCol.baloeear"))
		{
			 String hiddenId =  (String) httpSession.getAttribute("hiddenIds");
			 CommonFunctions.debugMsg("211111111111 "+ hiddenId);
			 String drillFlag = "f";
			 String firstClick =request.getParameter("firstClick");
			  drillFlag =request.getParameter("drillFlag");
			
			 CommonFilter  commonFilter;
			// commonFilter = new CommonFilter(); 
			 
		 commonFilter = populateCommonFilter(request,"oeearprqrFilter",( UIUtils.isValidKeyId(firstClick) || (firstClick!= null && ! firstClick.equals("Y") )),true);
		 
		// DRILL FROM JSP---gopi
		 String reqDrill = request.getParameter("DRILLLEVEL");
		 String reqComp  = request.getParameter("COMPANYID");
		 if (reqDrill != null && reqDrill.trim().length() > 0) {
		     commonFilter.setDrillLevel(reqDrill);
		     if (reqComp != null && reqComp.trim().length() > 0) {
		         ComboFilter cmbCompany = new ComboFilter();
		         cmbCompany.setId(reqComp);
		         commonFilter.setCompany(cmbCompany);
		     }
		 }
		 //----------------
		//	 commonFilter = populateCommonFilter(request,"oeearprqrFilter",true);
			// commonFilter = 	FilterValues.getPCS(request, commonFilter);
		/*	 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 					
					commonFilter =(CommonFilter) httpSession.getAttribute("oeearprqrFilter");
				}	
			 CommonFunctions.debugMsg("firstClick    :"+firstClick);
		 */
			  
			// commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 

			 
			 /*	if(UIUtils.isValidKeyId(hiddenId))
				{
			 		FilterValues.setDrillLevel(hiddenId ,commonFilter);
			 	//	FilterValues.setMainKeyidForDrillDown(commonFilter, hiddenId);
				 } */
		/* 	  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-6).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
				  commonFilter.setChkboxMgrCal("N");
				 
			
		 	  }
		 	  else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
						  commonFilter.setToDate(CommonFunctions.getDate());
						  commonFilter.setMonwise("N");
						  CommonFunctions.debugMsg("date Filter==null   "+commonFilter.getFromDate());
				 	  }
		 	*/  
		 	 if((commonFilter.getRemoveBlank()) == null)
				  commonFilter.setRemoveBlank("Y");
		 	  CommonFunctions.debugMsg("date jk : "+commonFilter.getMonwise());
		 	  httpSession.removeAttribute("hiddenIds");
		 	  httpSession.removeAttribute("oeearprqrFilter");
			  httpSession.setAttribute("oeearprqrFilter",commonFilter);
	      	  response.setContentType("text/html");
			  List<String[]> oeearprqrList  = oeearprqrService.getAllOeearprqr(commonFilter);
			  PrintWriter out = response.getWriter();
			  JSONObject jsonObject = getTableModel(oeearprqrList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			  jsonObject.set("tableHeight", "74%%");
			  jsonObject.set("tableWidth", "105%%");
			 
			  httpSession.removeAttribute("oeeReportColModel");
			  httpSession.setAttribute("oeeReportColModel", jsonObject);
			  CommonFunctions.debugMsg(jsonObject);
			  out.println(jsonObject);
			
		}
		else if( action.equals("Oeearprqr_getData.baloeear") )
		{
			try
			{
				
				 PrintWriter out = response.getWriter(); 
				// CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("oeearprqrFilter");
				 CommonFilter commonFilter = populateCommonFilter(request,"oeearprqrFilter",false,false);
				
				
				 List<String[]> oeearprqrList  = oeearprqrService.getAllOeearprqr(commonFilter);
				 JSONObject listToJsonObject = new JSONObject();
				 
				 if(oeearprqrList != null && oeearprqrList.size()>2)
					 listToJsonObject = UIUtils.convertToJqGridTableObject(oeearprqrList,request,2,0,commonFilter.getTotalRecordCnt()+2);
				
				 System.out.println(listToJsonObject);
				 out.println(listToJsonObject);
			
  			 	 /*httpSession.removeAttribute("oeearprqrFilter");
  			 	 httpSession.setAttribute("oeearprqrFilter", commonFilter);*/
			
		    }catch(Exception e)
			{
				System.out.println("error " +e.getMessage());
			} 
		    
		   
		}
		else if( action.equals("Oeearprqr_getExcel.baloeear")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"oeearprqrFilter",false,false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			
			String month1   = fromMonth +"  -  "+ toMonth ;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate ;
		
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("oeeReportColModel");
			
			tblJSONObj.put("title", "OEE/AR/PR/QR Report" + " - "+date);
			if( commonFilter.getMonwise().equals("Y")){
				tblJSONObj.put("title", "OEE/AR/PR/QR Report" + " - "+month1);
			}
			 
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = oeearprqrService.oeeExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "OeeReport", format);
			
		}
		 else if( action.equals("chart.baloeear")){
				CommonFunctions.debugMsg("222222222222");
				
				processChart(request,response);
			}		
		 else if( action.equals("chartType.oeear")){
				processChart1(request,response);
			}	
	}
private void processChart1(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession httpSession = request.getSession(false);
		String chart = request.getParameter("chartType");
		/*String forDashboard = request.getParameter("dashboard");
		
		String chart = request.getParameter("chartType");
		CommonFilter commonFilter = null;
		CommonFilter chrtCommonFilter = new  CommonFilter();
		
		if( ! "true".equals(forDashboard)){
			
			commonFilter = (CommonFilter) httpSession.getAttribute("oeearprqrFilter");
			BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
		}
		else{
			FilterValues.getPCS(request, chrtCommonFilter);
		}
			
		FilterValues.getCommonFilters(request, chrtCommonFilter) ;
		
		if(chrtCommonFilter.getRowTotal() == null )
			chrtCommonFilter.setRowTotal('N');
		*/
		String forDashboard = request.getParameter("dashboard");
		
		CommonFilter commonFilter = null;
		CommonFilter chrtCommonFilter = new  CommonFilter();
		
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute("oeearprqrFilter");
			BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
			chrtCommonFilter.setFromRow(null);
		}
		else{
			FilterValues.getPCS(request, chrtCommonFilter);
			FilterValues.getCommonFilters(request, chrtCommonFilter);
		}
		
		if(chrtCommonFilter.getRowTotal() == null )
			chrtCommonFilter.setRowTotal('N');
		
		chrtCommonFilter.setChartType(chart);

		 List<String[]> oeearprqrList  = oeearprqrService.getAllChartType(chrtCommonFilter);
		
		JSONObject chartObj = new JSONObject();
		JSONObject chartData = new JSONObject();
		if( oeearprqrList != null && oeearprqrList.size() >0 ){
			chartData.set("title", oeearprqrList.get(0)[1]);
			chartData.set("data", Double.parseDouble( oeearprqrList.get(0)[2]));
			chartData.set("type", "gauge");
			chartData.set("chType", chart);
		}	
		chartObj.set("chartData", chartData);
		PrintWriter out = response.getWriter();
		chrtCommonFilter = null;
		if (chartObj == null) chartObj = new JSONObject();
		UIUtils.setDashBoardIdentifier(request, chartObj);
		out.print(chartObj);
		out.close();
	}
	private static String getComboSelectionId(ComboFilter comboFilter){ 		
		if( comboFilter != null && UIUtils.isValidKeyId( comboFilter.getId() ) )
			return comboFilter.getId();
		return "";
	}
	private JSONObject getTableModel(List<String[]> headers, String caption)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		
		String [] colHeader1 = headers.get(0);
		String [] colHeader = headers.get(1);
		
		
		
		String[] emptyrow = new String[colHeader.length]; 
		
		
		emptyrow [0] ="";
		emptyrow [1] ="";
		//emptyrow [2] ="";
		 
		colHeader[1] = caption;
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.getRowHeaders().add(colHeader);
	
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
	
		//jqGridTableModel.setTableHeight(270);
	
		
		//jqGridTableModel.getColModel().add(getColModel("keyid", 0,"left",true,false,true));
		
		jqGridTableModel.getColModel().add(getColModel("KEYFIELD2", 0,"left",true,false,true));
		
		JqGridColModel jqGridColModel =getColModel("codeField",350,"left",false,true,false);

		jqGridColModel.setSummaryTpl("<b><font >Total</font> </b>");
		jqGridTableModel.getColModel().add( jqGridColModel);
		String colIndex ="";
		colHeader = headers.get(0);
		for(int i =2; i < colHeader.length; i++)
		{
			emptyrow [i] ="";
			colIndex = colHeader[i].replaceAll(" ", "").replace("-", "")+i ;
			
			jqGridTableModel.getColModel().add(getColModel( colIndex,100,"center", false,true,false));
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
		jqGridColModel.setKey(true);
		
		if(groupbyfield){
			jqGridColModel.setSummaryType("sum");
			jqGridColModel.setSummaryTpl("<b><font >  {0} </font> </b>");
		}
		return jqGridColModel;
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
		
		 
	//	CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("oeearprqrFilter");
		//CommonFilter commonFilter = populateCommonFilter(request,"oeearprqrFilter",false);
		/*String compid = request.getParameter("cmbCompid");
		String locnid = request.getParameter("cmbLocnid");
		String factId = request.getParameter("cmbFactid");
		String sectid = request.getParameter("cmbSectid");
		String cellid = request.getParameter("cmbCellid");
		String mchid = request.getParameter("cmbMchid");
		*/
	//	CommonFilter chrtCommonFilter = populateCommonFilter(request,"oeearprqrFilter",false,false);
/*		ComboFilter cmbCompany = new ComboFilter();
		ComboFilter cmbLocation = new ComboFilter();
		ComboFilter cmbFactory = new ComboFilter();
		ComboFilter cmbSection = new ComboFilter();
		ComboFilter cmbCell = new ComboFilter();
		ComboFilter cmbMachine = new ComboFilter();	
		if( UIUtils.isValidKeyId(compid)){
			cmbCompany.setId(compid);
			chrtCommonFilter.setCompany(cmbCompany);
		}
		if( UIUtils.isValidKeyId(locnid)){
			cmbLocation.setId(locnid);
			chrtCommonFilter.setLocation(cmbLocation);
		}
		if( UIUtils.isValidKeyId(factId)){
			cmbFactory.setId(factId);
			chrtCommonFilter.setFactory(cmbFactory);
		}
		if( UIUtils.isValidKeyId(sectid)){
			cmbSection.setId(sectid);
			chrtCommonFilter.setSection(cmbSection);
		}
		if( UIUtils.isValidKeyId(cellid)){
			cmbCell.setId(cellid);
			chrtCommonFilter.setCell(cmbCell);
		}
		if( UIUtils.isValidKeyId(mchid)){
			cmbMachine.setId(mchid);
			chrtCommonFilter.setMachine(cmbMachine);
		}
		//BeanUtils.copyProperties(chrtCommonFilter,commonFilter);
		
		//FilterValues.getCommonFilters(request, chrtCommonFilter) ;			
		/*if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		*/

		
		String forDashboard = request.getParameter("dashboard");
		
		CommonFilter commonFilter = null;
		CommonFilter chrtCommonFilter = new  CommonFilter();
		
		/*if( ! "true".equals(forDashboard)){
			
			commonFilter = (CommonFilter) httpSession.getAttribute("oeearprqrFilter");
			BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
		}
		else{
			FilterValues.getPCS(request, chrtCommonFilter);
		}
			
		FilterValues.getCommonFilters(request, chrtCommonFilter) ;
		*/
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute("oeearprqrFilter");
			BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
			chrtCommonFilter.setFromRow(null);
		}
		else{
			FilterValues.getPCS(request, chrtCommonFilter);
			FilterValues.getCommonFilters(request, chrtCommonFilter);
		}
		
		if(chrtCommonFilter.getRowTotal() == null )
			chrtCommonFilter.setRowTotal('N');


		 List<String[]> oeearprqrList  = oeearprqrService.getAllOeearprqr(chrtCommonFilter);
		 //---gopi
		 System.out.println("CHART list size = " + (oeearprqrList == null ? "null" : oeearprqrList.size()));
		 if (oeearprqrList != null) for (String[] r : oeearprqrList) System.out.println("CHART row = " + java.util.Arrays.toString(r));
		 //-----gopi
		//List<String  []> sparesConsList  = sprConsumptionService.getAllSpares(commonFilter,reportType);
		
		JSONObject chartObj = null;
		String chartType = request.getParameter("chType");		
		
		//if( ! (DrillLevelConstants.ASSEMBLY.equals(commonFilter.getDrillLevel()) || DrillLevelConstants.PHENOMENA.equals(commonFilter.getDrillLevel())) )
		//{
		if( chartType == null){
		
			chartObj = processLineChart(oeearprqrList,chrtCommonFilter);
			
		}
		//else{
			//chartObj = processPieChart(sparesConsList,chrtCommonFilter);
		//}
	//	chrtCommonFilter = null;
		
		PrintWriter out = response.getWriter();
		chrtCommonFilter = null;
		if (chartObj == null) chartObj = new JSONObject();
		UIUtils.setDashBoardIdentifier(request, chartObj);
		out.print(chartObj);
		out.close();
			
	}
	private static Double toD(String s){
	    try { return (s == null || s.trim().isEmpty()) ? 0.0 : Double.parseDouble(s.trim()); }
	    catch (Exception e) { return 0.0; }
	}

	private JSONObject processLineChart(List<String[]> oeearprqrList,CommonFilter commonFilter){
		 
		if( oeearprqrList == null || oeearprqrList.size() <= 2  )
			return null;
		 
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		String fromMonth = commonFilter.getFromMonth();
		String toMonth = commonFilter.getToMonth();
		
		String month1   = fromMonth +"  -  "+ toMonth ;
		String fromdate = commonFilter.getFromDate();
		String todate = commonFilter.getToDate();
		String date   = fromdate +"  -  "+ todate ;
		String title = "OEE" + " - "+date;
		if( commonFilter.getMonwise().equals("Y")){
		title = "OEE" + " - "+month1;
		}
	 
		
		
		String [] header =  oeearprqrList.get(1);
		String [] month =  oeearprqrList.get(0);
		String [] data =  oeearprqrList.get(2);
		String prevMonth = null;
		
		String subTitle = data[1];
		
		ChartSeries arSeries = new ChartSeries();
		List<Double> arData = new ArrayList<Double>();
		
		ChartSeries prSeries = new ChartSeries();
		List<Double> prData = new ArrayList<Double>();
		
		ChartSeries qrSeries = new ChartSeries();
		List<Double> qrData = new ArrayList<Double>();
		
		ChartSeries oeeSeries = new ChartSeries();
		List<Double> oeeData = new ArrayList<Double>();
		String headrROA = null;
		String headrROP = null;
		String headrROQ = null;
		
		for( int i = 2;i < header.length-4;i++ ){
			CommonFunctions.debugMsg("data"+data[i]);
			
			if(header[i].contains("ROA") || header[i].contains("AR") ){
				arData.add(Double.parseDouble(data[i]));
				headrROA = header[i];
				CommonFunctions.debugMsg("headrROA        :"+headrROA);
			}
			else if(header[i].contains("ROP")||header[i].contains("PR") ){
				prData.add(Double.parseDouble(data[i]));
				headrROP = header[i];
			}
			else if(header[i].contains("ROQ")||header[i].contains("QR") ){
				qrData.add(Double.parseDouble(data[i]));
				headrROQ = header[i];
			}
			else if(header[i].contains("OEE") ){
				oeeData.add(Double.parseDouble(data[i]));
			}
			
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		
		if(arData.size() > 0 ){
			arSeries.setData(arData);
			arSeries.setType(ChartTypes.SPLINE);
			arSeries.setName(headrROA );
			chartSeriesList.add(arSeries);
				
			
			
		}
		if(prData.size() > 0 ){
			prSeries.setData(prData);
			prSeries.setType(ChartTypes.SPLINE);
			prSeries.setName(headrROP);
			
			chartSeriesList.add(prSeries);
		}	
			
		if(qrData.size() > 0 ){
			qrSeries.setData(qrData);
			qrSeries.setType(ChartTypes.SPLINE);
			qrSeries.setName(headrROQ);
			chartSeriesList.add(qrSeries);
		}		
				
		if(oeeData.size() > 0 ){
			oeeSeries.setData(oeeData);
			oeeSeries.setType(ChartTypes.SPLINE);
			oeeSeries.setName("OEE");
			chartSeriesList.add(oeeSeries);
		}		
					
		ChartYAxis yAxis = new ChartYAxis(); 
		yAxis.setMin(0);
		yAxis.getTitle().setText("Value in Percentage");
		chartYAxis.add(yAxis);
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());

		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
		
	}
}
				
