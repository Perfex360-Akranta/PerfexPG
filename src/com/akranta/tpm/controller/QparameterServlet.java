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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.QPointBean;
import com.akranta.tpm.bean.QparameterBean;
import com.akranta.tpm.bean.UpstreamDefect;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlUpstreamdefect;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.QtmTlKnowwhymst;
import com.akranta.tpm.model.QtmTlQpoint;
import com.akranta.tpm.model.QtmTlQpointdtl;
import com.akranta.tpm.model.QtmTlQpointdtls;
import com.akranta.tpm.model.QtmTlQpointmst;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.QtmTIQpointmstService;
import com.akranta.tpm.service.impl.QtmTIQpointServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.service.impl.DashboardServiceImpl;

/**
 * Servlet implementation class QualityInspectionServlet
 */

public class QparameterServlet extends HttpServlet {
	DashboardService dashboardService;
	QtmTIQpointmstService qtmtiqpointmstService;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public QparameterServlet() {
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
		

		
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
            
dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
		qtmtiqpointmstService= (QtmTIQpointServiceImpl)UIUtils.getServiceObject(request, "QtmTIQpointServiceImpl");
		HttpSession httpSession = request.getSession(false);
		qtmtiqpointmstService.QtmTIQpointmstServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		
		String action = UIUtils.getActionPart(request);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		//CommonMessage.debugMsg(" action " + action);
		// commonFilter = new CommonFilter();
		// String dispatchUrl = null;
		//HttpSession httpSession = request.getSession(false);
		
		
		
		
		
		/*if (action.equals("QP_input.qp"))      // to display the master data to get the jsp page. 
		{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/QMastergrid1.jsp");    //Q-pointNew.jsp
			//RequestDispatcher rd = request.getRequestDispatcher("/pages/Q-pointNewMainGrid.jsp");
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);
		
		}
		else if (action.equals("QPointsnew_input.qp"))      // to display the master data to get the jsp page. 
		
		{
			UIUtils.forwardRequest(request, response, "/pages/QPointnewGrid.jsp");
		
		}
		else if (action.equals("QPointsnew_getCol.qp"))      // to show the table grid 
		{
		try {
			String type= request.getParameter("type");	
			PrintWriter out = response.getWriter();
			List<String[]> analysis = qtmtiqpointmstService.getPoint(type);
			JSONObject jsonObject = getTableModelPoints(analysis);
		    out.println(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		} 
		else if (action.equals("QPointsnew_getData.qp"))   // for view of table data 
		{
			try
			{	String type= request.getParameter("type");
				List<String[]> Grid =	qtmtiqpointmstService.getPoint(type);
				CommonMessage.debugMsg("print"+Grid.size());
				PrintWriter out = response.getWriter();
				JSONObject mastgrid = UIUtils.convertToJqGridTableObject(Grid, request, 1, 0);
				out.println(mastgrid);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if (action.equals("QP_getCol.qp"))      // to show the table grid 
		{
			

			PrintWriter out = response.getWriter();

			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Qparameter","Qparameter"));
		} 
		else if (action.equals("QP_getData.qp"))   // for view of table data 
		{
			
		
			try
			{
				CommonMessage.debugMsg("QP_getdata.qp");
				String masterKeyid = request.getParameter("keyid");
				CommonMessage.debugMsg("masterKeyid"+masterKeyid);
				//FilterValues.getCommonFilters(request, commonFilter);
				//CommonFilter commonFilter =  populateCommonFilter(request,"EquipmentCommonFilter",false);
				List<String[]> Grid =	qtmtiqpointmstService.getmaster(masterKeyid);
				CommonMessage.debugMsg("print"+Grid.size());
				PrintWriter out = response.getWriter();
				
				JSONObject mastgrid = UIUtils.convertToJqGridTableObject(Grid, request, 0, 0);
			CommonMessage.debugMsg("print343453");
			CommonMessage.debugMsg(mastgrid);
				out.println(mastgrid);
				
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
			else if(action.equals("4m.qp"))
			   {
				   PrintWriter out = response.getWriter();
				   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.4m","4m"));	   
			   }
		
		else if( action.equals("functionalLoc.qp"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbFactoryid");
			functLocFieldNameBean.setSection("cmbSectionid");
			functLocFieldNameBean.setCell("cmbCellid");
			functLocFieldNameBean.setMachine("cmbMachineid");
			//functLocFieldNameBean.setFactMandatory(true);
			//functLocFieldNameBean.setSectMandatory(false);
			//functLocFieldNameBean.setCellMandatory(true);
			//functLocFieldNameBean.setMachMandatory(true);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		
		
	}
		
		
		else if (action.equals("QPPopUp_input.qp"))    //to show the jsp of table 
		{
		
			String masterKeyid =request.getParameter("keyid");
			String detailKeyid =request.getParameter("detailkeyid");
		
			QtmTlQpointdtl dtl= new QtmTlQpointdtl();//model
			if(UIUtils.isValidKeyId(detailKeyid))
			{
				dtl = qtmtiqpointmstService.getalldetail(detailKeyid);
				
				//detail  and  master keyid
				request.setAttribute("newqtmTlQpointdtl", dtl );
			
			}
			
			request.setAttribute("masterkeyid", masterKeyid);
			httpSession.setAttribute("dtl", dtl);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Q-pointNew.jsp");//   Qparameter1.jsp
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);
			
		}
		else if (action.equals("QPPopUp_save.qp"))
		{
			
			CommonMessage.debugMsg("ggyw");
		    saveDetail(request,response);
			
			
		}
		else if( action.equals("QPPopUp_delete.qp"))
		{	
			HttpSession httpSession1 = request.getSession(false);
			
			
			deletedetail(request,response);
			
		}
		
		
		
		// for master grid table creation
		
		
		
		else if (action.equals("MasterGrid_getCol.qp"))  //master grid 
		{
			
			
			
			CommonMessage.debugMsg("bhjd");
			PrintWriter out = response.getWriter();
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.StudentMaster", "student1"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.QMastergrid","master"));
			
		} 
		
		else if (action.equals("MasterGrid_getData.qp")) // for master grid value to get from sql 
		{

			try
			{
				
				//FilterValues.getCommonFilters(request, commonFilter);
				//CommonFilter commonFilter =  populateCommonFilter(request,"EquipmentCommonFilter",false);
				List<String[]> SopMaster =	qtmtiqpointmstService.getAllSop();
				PrintWriter out = response.getWriter();
				
				JSONObject sopmaster = UIUtils.convertToJqGridTableObject(SopMaster, request, 0, 0);
				out.println(sopmaster );
				CommonMessage.debugMsg(sopmaster );
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());//
			}
		}
		
	                // for master data ctrls and table grid display
	 if( action.equals("Master_delete.qp"))
			{	
				HttpSession httpSession1= request.getSession(false);
				CommonMessage.debugMsg("keyid");
				QparameterBean Bean	 = (QparameterBean)httpSession1.getAttribute("Bean");
				deletemaster(request,response,Bean);
				
			}
			
	else if (action.equals("Master_input.qp")) {
				
			
				
				String keyid =request.getParameter("keyid");
				CommonMessage.debugMsg("keyid"+keyid);
				
				QtmTlQpointmst newQtmTlQpointmst=new QtmTlQpointmst();//model
			
				if(UIUtils.isValidKeyId(keyid)){
					newQtmTlQpointmst = qtmtiqpointmstService.select(keyid);
					CommonMessage.debugMsg("newQtmTlQpointmst "+newQtmTlQpointmst.getQpmApprovedby());
					
					CommonMessage.debugMsg("preparedadate"+newQtmTlQpointmst.getQpmApproveddate());
					newQtmTlQpointmst.setQpmApproveddate(newQtmTlQpointmst.getQpmApproveddate().substring(0, 12));
					newQtmTlQpointmst.setQpmPrepareddate(newQtmTlQpointmst.getQpmPrepareddate().substring(0, 12));
					CommonMessage.debugMsg("preparedadate after setting"+newQtmTlQpointmst.getQpmApproveddate()); 
					request.setAttribute("newQtmTlQpointmst" ,newQtmTlQpointmst);
				}
		 		
				
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Qparameter.jsp"); 
				CommonMessage.debugMsg("jsp" + newQtmTlQpointmst);
				rd.forward(request, response); 
     	}	
		
		
		else if(action.equals("Master_save.qp"))                //  save the data for master
		{
			CommonMessage.debugMsg("ggyw");
		    saveQParameter(request,response);
		    CommonMessage.debugMsg("ttt");
		}
	                           
		else */ if(action.equals("QPointNew_input.qp"))                         //QP_input.qp QPointNew_input.qp
		{
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Q-pointNewMainGrid.jsp");
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);
			
		}                      
		else if(action.equals("QPointNew_getCol.qp"))                         
		{
			List<String[]> ctrlResGrid = null;
			PrintWriter out = response.getWriter();
			//JSONObject jsonObject = new JSONObject();  //QTM_FN_QPOINTSNEWMAINGRID
		
			try {
			    CommonFilter commonFilter = populateCommonFilter(request,"QPointCommonFilter",true);
			    
			    String flid=request.getParameter("flid");
				String dtFromDate=request.getParameter("dtFromDate");
				String dtToDate=request.getParameter("dtToDate");
				
				if(UIUtils.isValidKeyId(dtFromDate))
				commonFilter.setBefToDt(dtFromDate);
				
				if(UIUtils.isValidKeyId(flid))
				commonFilter.setFlid(flid);
				
				if(UIUtils.isValidKeyId(dtToDate))
				commonFilter.setAftToDt(dtToDate);
				commonFilter.setIsGetCol("Y");
				
			    ctrlResGrid = qtmtiqpointmstService.getQPointNewGrid(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				String [] colHeader = ctrlResGrid.get(1);
				String [] colHeaderHead = ctrlResGrid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				
				CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
				colmodel.set("tableWidth", "106%%");
				colmodel.set("tableHeight", "80%%");
				
				out.println(colmodel);

			} catch (Exception e) {
				e.printStackTrace();
			}		
			
		}
		else if(action.equals("QPointNew_getData.qp"))                         
		{
			try
			{   

				UIUtils.displayRequestParamsValue(request);	
				CommonFilter commonFilter = populateCommonFilter(request,"QPointCommonFilter",false);
				
				String filter=request.getParameter("filter");
				String dtFromDate=request.getParameter("dtFromDate");
				String dtToDate=request.getParameter("dtToDate");
				
			    if(UIUtils.isValidKeyId(dtFromDate))
				commonFilter.setBefToDt(dtFromDate);
				
				if(UIUtils.isValidKeyId(filter))
				commonFilter.setBdActivity(filter);
				//commonFilter.setFlid(filter);
				if(UIUtils.isValidKeyId(dtToDate))
				commonFilter.setAftToDt(dtToDate);
				commonFilter.setIsGetCol("N");
				
				List<String[]> UpstreamGrid  =qtmtiqpointmstService.getQPointNewGrid(commonFilter);
  			 	CommonMessage.debugMsg("equipmentQueryList " + UpstreamGrid.size());
				PrintWriter out = response.getWriter();
  			 	JSONObject UpstreamData = UIUtils.convertToJqGridTableObject(UpstreamGrid,request,2,0,commonFilter.getTotalRecordCnt()+2); 
  			 	out.println(UpstreamData);  

		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
			
		}
		
		else if(action.equals("Qpointsdeployed_input.qp")) 
		{ 
			request.setAttribute("qpointCumMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/QpointCummulative.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("Qpointsdeployed_getCol.qp"))
		{
			String firstClick =request.getParameter("firstClick");
			CommonFilter commonFilter = populateCommonFilter(request,"QPointCommonFilter",true);
			
			httpSession.removeAttribute("QPointCommonFilter");
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
			 {
				
				  commonFilter =(CommonFilter) httpSession.getAttribute("QPointCommonFilter");
			 }	
			if(commonFilter==null)
			 commonFilter = new CommonFilter(); 
			 
			 commonFilter= FilterValues.getCommonFilters(request,commonFilter);
			 commonFilter= FilterValues.getOPLandKaizen(request,commonFilter);
			 //commonFilter= populateCommonFilter(request,"HseAccTrendRptCommonFilter",true);
			 
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
		 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(0));
					  commonFilter.setToDate(CommonFunctions.getDate());
			 }
			
			
			PrintWriter out = response.getWriter();
			
			

			httpSession.removeAttribute("QPointCommonFilter");
			httpSession.setAttribute("QPointCommonFilter",commonFilter);
			 response.setContentType("text/html");
			 commonFilter.setRowTotal('N');
			 
	    	 List< String[]> qpointCummulativeList  = qtmtiqpointmstService.getQptscnt(commonFilter);
	 			 
	      	 JSONObject jsonObject = null;
	      	 if(qpointCummulativeList!=null && qpointCummulativeList.size() > 0){
	      		 jsonObject = getTableModel(qpointCummulativeList,FilterValues.getHeader( commonFilter.getDrillCaption()));
	      		httpSession.removeAttribute("oplCumReportColModel");
				httpSession.setAttribute("oplCumReportColModel", jsonObject);
				 out.println(jsonObject);
	      	 }
	      	
			// }
			/* catch(Exception e)
			 {
				 CommonMessage.debugMsg("Exception in colModel Opl Cumulative"+e.getMessage());
			 }*/
		
		}
		else if( action.equals("Qpointsdeployed_getData.qp") )
		{
			try
			{   
				 PrintWriter out = response.getWriter();
				 CommonFilter commonFilter = populateCommonFilter(request,"QPointCommonFilter",false);
				 //commonFilter =(CommonFilter) httpSession.getAttribute(oplCummulativeRptIden);
				 //commonFilter =populateCommonFilter(request,oplCummulativeRptIden,false);
				 commonFilter =(CommonFilter) httpSession.getAttribute("QPointCommonFilter");
				// String flid=request.getParameter("flid");
				 commonFilter= FilterValues.getCommonFilters(request,commonFilter);
				String drillFlag=request.getParameter("drillFlag");
				 //commonFilter.setFlid(flid);
				if( drillFlag != null)
				 commonFilter.setDrillFlag(drillFlag.charAt(0));
				// CommonMessage.debugMsg("commonFilter"+commonFilter.getMonwise());
				 List< String[]> oplCummulativeList  = qtmtiqpointmstService.getQptscnt(commonFilter);
				// CommonMessage.debugMsg("oplCummulativeList.size()"+oplCummulativeList.size());
				 if(oplCummulativeList.size()>3){
					// CommonMessage.debugMsg("commonFilter.getTotalRecordCnt()"+commonFilter.getTotalRecordCnt());
					 JSONObject oplCummulativeData = convertToJqGridTableObjectOPL( oplCummulativeList,request,1,0,0,commonFilter.getTotalRecordCnt()+1);
				 	 out.println(oplCummulativeData);
				 }

		    }catch(Exception e)
			{
		    	e.printStackTrace();
				//CommonMessage.debugMsg("error = " +e.getMessage());
			}
		}
		else if( action.equals("Qpointsdeployed_getExcel.qp") )
		{
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"QPointCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("oplCumReportColModel");
			
			
			
			if (tblJSONObj == null) {
			    CommonMessage.debugMsg("ERROR - oplCumReportColModel is NULL in session!");
			    // Initialize a default one or throw error
			}
			tblJSONObj.put("title", "Q Point Details Cumulative Report");
			
			String format = ExcelUtils.getFormat(request);
			 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			
			Workbook wb = qtmtiqpointmstService.qPpointCumulativeExportExcel(commonFilter,tblJSONObj,format);
			CommonMessage.debugMsg("Number of sheets: " + wb.getNumberOfSheets());

			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			    CommonMessage.debugMsg("Sheet " + i + ": " + wb.getSheetName(i));
			}
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "QpointCumulativeReport", format);
		}
		
		/**----- For GENERATING GRAPH------- **/
		else if( action.equals("chart.qp"))
		{
			processChart(request,response);
		}
		
		else if(action.equals("QPointFormNew_input.qp"))                         //QP_input.qp QPointNew_input.qp
		{
			
			String Grid = request.getParameter("grid");
			String Keyid1 = request.getParameter("keyId");
			String Date = request.getParameter("Date");
			String New = request.getParameter("new");
			String FnlnId = request.getParameter("flId");
			String Area = request.getParameter("AREA");
			String KPOV = request.getParameter("KPOV");
			String PREPAREDBY = request.getParameter("PREPAREDBY");
			String Keyid = request.getParameter("KEYID");
			
			CommonMessage.debugMsg(" Keyid :: "+  Keyid);
			
			CommonMessage.debugMsg(" Keyid1 :: "+ Keyid1);
			
			CommonMessage.debugMsg(" Date :: "+Date);
			
			CommonMessage.debugMsg(" Area :: "+Area+" KPOV ::  "+KPOV+" PREPAREDBY :: "+PREPAREDBY);
			
			
			QtmTlQpoint  newQtmTlQpoint  = new QtmTlQpoint();
			
			String userId = user.getUsrm_ccno();//

			
			if(UIUtils.isValidKeyId(Keyid))
			{
				QtmTlQpoint qtmTlQpoint = new QtmTlQpoint();	
				
				qtmTlQpoint= qtmtiqpointmstService.getFilControlData(Keyid);
				
				newQtmTlQpoint.setQptmPreparedby(userId);
				if( !UIUtils.isValidKeyId(newQtmTlQpoint.getQptmPreparedby()))
				{
					newQtmTlQpoint.setQptmPreparedby(userId);
				}
				String date = qtmTlQpoint.getQptmDate();
				qtmTlQpoint.setQptmDate(CommonFunctions.pg_getDateFromPGTimeStamp(date));
				request.setAttribute("newQtmTlQpoint", qtmTlQpoint);
			}else{
				
				newQtmTlQpoint.setQptmFlid(FnlnId);
				newQtmTlQpoint.setQptmDate(Date);
				newQtmTlQpoint.setQptmArea(Area);
				newQtmTlQpoint.setQptmKpov(KPOV);
				newQtmTlQpoint.setQptmPreparedby(PREPAREDBY);
				
			}
			
			//request.setAttribute("newQtmTlQpoint", newQtmTlQpoint);
			//request.setAttribute("New", New);
			//request.setAttribute("FnlnId", FnlnId);
			//request.setAttribute("FnlnId", Area);
			//request.setAttribute("FnlnId", KPOV);
			//request.setAttribute("FnlnId", PREPAREDBY);
			//request.setAttribute("userId", userId);
			
			CommonMessage.debugMsg(" Area :: "+Area+" KPOV ::  "+KPOV+" PREPAREDBY :: "+PREPAREDBY);
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Q-pointNew.jsp");
			rd.forward(request, response);
			CommonMessage.debugMsg(" response " + response);
			
		}
		else if(action.equals("QPointFormNew_getCol.qp"))                         //QP_input.qp QPointNew_input.qp
		{
			List<String[]> ctrlResGrid = null;
			PrintWriter out = response.getWriter();
			//JSONObject jsonObject = new JSONObject();
		
			try {
				
				String keyId = request.getParameter("keyId");
				
				String Area = request.getParameter("Area");
				String Kpov = request.getParameter("Kpov");
				String Preparedby = request.getParameter("Preparedby");
				
			    CommonFilter commonFilter = populateCommonFilter(request,"UpstreamDefectCommonFilter",true);
			    ctrlResGrid = qtmtiqpointmstService.getQPointFormNewGrid(commonFilter,keyId,Area,Kpov,Preparedby);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				
				jqGridTableModel.setSortable(true);
				//jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				String [] colHeader = ctrlResGrid.get(1);
				String [] colHeaderHead = ctrlResGrid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				
				CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
				colmodel.set("tableWidth", "106%%");
				colmodel.set("tableHeight", "56%%");
				
				out.println(colmodel);

			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}
		else if(action.equals("QPointFormNew_getData.qp"))                         //QP_input.qp QPointNew_input.qp
		{
			try
			{   

				UIUtils.displayRequestParamsValue(request);	
				CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
				
				String Date = request.getParameter("Date");
			    String FnlnId = request.getParameter("flId");
			    String keyId = request.getParameter("keyId");
			    String Area = request.getParameter("Area");
				String Kpov = request.getParameter("Kpov");
				String Preparedby = request.getParameter("Preparedby");
				
			    
			    
			    String QpointDate = "";
			    
			    if(UIUtils.isValidKeyId(Date)){
			    	 QpointDate  = Date ;// Date.substring(0, 11);
			    }
			    
				CommonMessage.debugMsg(" Date :: "+Date+" FnlnId :: "+FnlnId+" UpstreamDate :: "+QpointDate);
			    
			    commonFilter.setFlid(FnlnId);
				commonFilter.setDteend(QpointDate);
				List<String[]> UpstreamGrid   = qtmtiqpointmstService.getQPointFormNewGrid(commonFilter,keyId,Area,Kpov,Preparedby);
				
  			 	CommonMessage.debugMsg("equipmentQueryList " + UpstreamGrid.size());
				PrintWriter out = response.getWriter();
  			 	JSONObject UpstreamData = UIUtils.convertToJqGridTableObject(UpstreamGrid,request,2,0,commonFilter.getTotalRecordCnt()+2); 
  			 	out.println(UpstreamData);  

		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		
		}
		else if(action.equals("QPointFormNew_recall.qp")){
			
			PrintWriter out = response.getWriter();
			String keyid = request.getParameter("KEYID");
			CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
			List<String []> condReclData  = qtmtiqpointmstService.FillControlData(keyid);
			out.print( JSONArray.fromCollection(condReclData));
			
		}
		
		else if( action.equals("functionalLoc.qp"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbFactoryid");
			functLocFieldNameBean.setSection("cmbSectionid");
			functLocFieldNameBean.setCell("cmbCellid");
			functLocFieldNameBean.setMachine("cmbMachineid");
			//functLocFieldNameBean.setFactMandatory(true);
			//functLocFieldNameBean.setSectMandatory(false);
			//functLocFieldNameBean.setCellMandatory(true);
			//functLocFieldNameBean.setMachMandatory(true);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		
		
	}else if(action.equals("QPointFormNew_save.qp")) 
	{
		QPointBean qPointBean = new QPointBean();
		saveQPoint(request, response, qPointBean);
	}
	else if(action.equals("QPointFormNew_delete.qp")) 
	{
		QPointBean qPointBean = new QPointBean();
		deleteQPoint(request, response, qPointBean);
	}
	else if(action.equals("QPointForm_delete.qp")) 
	{
		QPointBean qPointBean = new QPointBean();
		deleteQPointdtl(request, response, qPointBean);
	}
	else if(action.equals("QPointNew_getExcel.qp")){
		
		CommonMessage.debugMsg(" get data.....");
		/*try
		{   
            httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"QPointCommonFilter",false);
			JSONObject colmodel = UIUtils.getXlColModel(request,response);
			colmodel.put("title","Q-Points");
            String format = ExcelUtils.getFormat(request);
			
			Workbook wb = qtmtiqpointmstService.getQPointExcel(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "QPointReport", format);
			

	    }catch(Exception e)
		{
			CommonMessage.debugMsg(e.getMessage());
		}
	}*/
	//changes done by vijay
		httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"QPointCommonFilter",false);
		 String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
		 JSONObject colmodel = UIUtils.getXlColModel(request,response);
		colmodel.put("title","Q-Points");
      String format = ExcelUtils.getFormat(request);
		
		Workbook wb = qtmtiqpointmstService.getQPointExcel(colmodel,format,commonFilter);
		commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, "QPointReport", format);
	}
		
}
	
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("QPointCommonFilter");
		
	/*	if(commonFilter!= null){
			FilterValues.getCommonFilters(request, commonFilter) ;
		}
		else
			commonFilter =  new CommonFilter();
	*/	
		String forDashboard = request.getParameter("dashboard");
		
		String dashboardtype = request.getParameter("EMPLILLAR");
		
		CommonMessage.debugMsg(" :: dashboardtype :: Servlet ::"+dashboardtype);
		
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute("QPointCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			CommonMessage.debugMsg("to check dash board true ");
			FilterValues.getCommonFilters(request,commonFilter);
			FilterValues.getOPLandKaizen(request, commonFilter);

			if(UIUtils.isValidKeyId(dashboardtype))
				commonFilter.setType(dashboardtype);
		}
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		//FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		chartCommonFilter.setRowTotal('Y');
		
		List<String[]> qpointCummulativeList  = qtmtiqpointmstService.getQptscnt(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(qpointCummulativeList != null && qpointCummulativeList.size() > 0)
		{	
			

String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processLineChart(lcnname,qpointCummulativeList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	private JSONObject processLineChart(String tn,List<String[]> qpointCummulativeList,CommonFilter commonFilter){
		//CommonMessage.debugMsg("oplCummulativeList size in processline char ="+oplCummulativeList.size());
		if( qpointCummulativeList == null || qpointCummulativeList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String[] header =  qpointCummulativeList.get(0);
		String[] month =  qpointCummulativeList.get(0);//change by swetha - 13 march
		String[] data =  qpointCummulativeList.get(qpointCummulativeList.size()-1);
		
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
	
		String drillLevel =  FilterValues.getDrillHeader(data[1]);
		StringBuilder title = new StringBuilder( tn+"-Q Point Deployed Cumulative Count - ").append( drillLevel).append( " Wide From ").append(date);
	
		
		String prevMonth = null;
		
		String subTitle = "";//data[2];
		//CommonMessage.debugMsg("subTitle "+subTitle );
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> cumulativeData = new ArrayList<Double>();
		
		for( int i = 3;i < header.length;i++ )//Swetha - header.length-1 -> header.length
		{
			
			
			if (!UIUtils.isValidKeyId(data[i])) {
				CommonMessage.debugMsg("entered into if "+i);
				data[i]="0";
			}
				cumulativeData.add(Double.parseDouble(data[i]));
				
				if( prevMonth == null || ! month[i].equals(prevMonth) )
				{
			
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(cumulativeData.size() > 0 )
		{
			timeSeries.setData(cumulativeData);
			timeSeries.setType(ChartTypes.SPLINE);
			timeSeries.setName("Q Point Displayed Cumulative Count");
			chartSeriesList.add(timeSeries);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Numbers");
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
//	private JSONObject processLineChart(String tn,List<String[]> qpointCummulativeList,CommonFilter commonFilter){
//		//CommonMessage.debugMsg("oplCummulativeList size in processline char ="+oplCummulativeList.size());
//		if( qpointCummulativeList == null || qpointCummulativeList.size() <= 2  )
//			return null;
//		
//		ChartOptionBean lineChart =  new ChartOptionBean();
//		List<String> xAxisCategory = new ArrayList<String>();
//		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
//		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
//		
//		String[] header =  qpointCummulativeList.get(0);
//		String[] month =  qpointCummulativeList.get(0);//13MAR2026 1 TO 0
//		String[] data =  qpointCummulativeList.get(qpointCummulativeList.size()-1);
//		
//		StringBuilder date = new StringBuilder();
//		
//		if(commonFilter.getMonwise().equals("Y"))
//		{
//			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
//		}
//		else
//			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
//	
//		String drillLevel =  FilterValues.getDrillHeader(data[1]);
//		StringBuilder title = new StringBuilder( tn+"-Q Point Deployed Cumulative Count - ").append( drillLevel).append( " Wide From ").append(date);
//	
//		
//		String prevMonth = null;
//		
//		String subTitle = "";//data[2];
//		//CommonMessage.debugMsg("subTitle "+subTitle );
//		
//		ChartSeries timeSeries = new ChartSeries();
//		List<Double> cumulativeData = new ArrayList<Double>();
//		
//		for( int i = 3;i < header.length;i++ ){ //  header.length-1 TO header.length
//			if (!UIUtils.isValidKeyId(data[i]))
//				data[i]="0";
//				cumulativeData.add(Double.parseDouble(data[i]));
//			
//				if( prevMonth == null || ! month[i].equals(prevMonth) ){
//				xAxisCategory.add(month[i]);
//			}
//			prevMonth = month[i];
//		}
//		if(cumulativeData.size() > 0 )
//		{
//			timeSeries.setData(cumulativeData);
//			timeSeries.setType(ChartTypes.SPLINE);
//			timeSeries.setName("Q Point Displayed Cumulative Count");
//			chartSeriesList.add(timeSeries);
//			
//			ChartYAxis yAxis = new ChartYAxis(); 
//			yAxis.setMin(0);
//			yAxis.getTitle().setText("Numbers");
//			chartYAxis.add(yAxis);
//		}
//		ChartXAxis xaxis = new ChartXAxis();
//		if(commonFilter.getMonwise().equals("Y"))
//			xaxis.getTitle().setText("Month");
//		else
//			xaxis.getTitle().setText("Date");
//		lineChart.getxAxis().setTitle(xaxis.getTitle());
//		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
//		
//	}
	
	private void deleteQPoint(HttpServletRequest request,HttpServletResponse response, QPointBean qPointBean)throws IOException {
		       // TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try
		{
	    	if(httpSession !=null && user !=null)
			{
	    		QtmTlQpoint existQtmTlQpoint  = new QtmTlQpoint();
	    		QtmTlQpoint  newQtmTlQpoint  = new QtmTlQpoint();
	    		QtmTlQpointdtls  newQtmTlQpointdtls  = new QtmTlQpointdtls();
	    		String QtmKeyid=request.getParameter("txtQptmKeyid");
	    		CommonMessage.debugMsg(" QtmKeyid :: "+QtmKeyid);
	    		newQtmTlQpointdtls=(QtmTlQpointdtls)UIUtils.setBeanProperties((Object)newQtmTlQpointdtls,request);
	    		newQtmTlQpoint=(QtmTlQpoint)UIUtils.setBeanProperties((Object)newQtmTlQpoint,request);
	    		newQtmTlQpointdtls.setQptdQptmKeyid(QtmKeyid);
	    		newQtmTlQpoint.setQptmKeyid(QtmKeyid);
				CommonMessage.debugMsg("newGenTlUpstreamdefect.getUpsdKeyid()  "+newQtmTlQpointdtls.getQptdQptmKeyid());
				newQtmTlQpoint=qtmtiqpointmstService.deleteQPoint(newQtmTlQpoint);
				httpSession.removeAttribute("GenTlUpstreamdefect"+newQtmTlQpointdtls.getQptdKeyid());		
				JSONObject successData=new JSONObject();
	    		JSONObject QPointdatadelete=new JSONObject();
	    		String savemsg;
				savemsg= "Data Deleted succesfully";
	    	    successData.put("msg", savemsg);
	    		successData.put("formClear",true);
	    		QPointdatadelete.put("successData", successData);
	    		out.print(QPointdatadelete.toString());
	    		
	    	}
		}
		
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception: "+e);
			JSONObject err = new JSONObject();
			String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
			err.put("successData",mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}
	}

	private void deleteQPointdtl(HttpServletRequest request,HttpServletResponse response, QPointBean qPointBean)throws IOException {
	       // TODO Auto-generated method stub
	
	UIUtils.displayRequestParamsValue(request);
	HttpSession httpSession = request.getSession(false);    	
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	ServletOutputStream out = response.getOutputStream();
	try
	{
	if(httpSession !=null && user !=null)
	{
		QtmTlQpoint existQtmTlQpoint  = new QtmTlQpoint();
		QtmTlQpoint  newQtmTlQpoint  = new QtmTlQpoint();
		QtmTlQpointdtls  newQtmTlQpointdtls  = new QtmTlQpointdtls();
		
		String QtdKeyid=request.getParameter("keyid");
		
		CommonMessage.debugMsg(" QtdKeyid :: "+QtdKeyid);
		
		newQtmTlQpointdtls=(QtmTlQpointdtls)UIUtils.setBeanProperties((Object)newQtmTlQpointdtls,request);
		newQtmTlQpoint=(QtmTlQpoint)UIUtils.setBeanProperties((Object)newQtmTlQpoint,request);
		newQtmTlQpointdtls.setQptdKeyid(QtdKeyid);
		newQtmTlQpoint.setQtmTlQpointdtls(newQtmTlQpointdtls);
		CommonMessage.debugMsg("newGenTlUpstreamdefect.getUpsdKeyid()  "+newQtmTlQpointdtls.getQptdQptmKeyid());
			newQtmTlQpoint=qtmtiqpointmstService.deleteQPoint(newQtmTlQpoint);
			httpSession.removeAttribute("GenTlUpstreamdefect"+newQtmTlQpointdtls.getQptdKeyid());
			
		
	
		JSONObject successData=new JSONObject();
		JSONObject QPointdatadelete=new JSONObject();
		String savemsg;
	   if( newQtmTlQpointdtls.getQptdKeyid()==null )
		{
			savemsg=" Data Not Deleted ";
			
		}
		else
		{
			savemsg= "Data Deleted succesfully";
			
		}
	    
	    successData.put("msg", savemsg);
		successData.put("formClear",true);
		QPointdatadelete.put("successData", successData);
		out.print(QPointdatadelete.toString());
		
	}
	}
	
	catch(Exception e)
	{ 
		CommonMessage.debugMsg("Exception: "+e);
		JSONObject err = new JSONObject();
		String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
		err.put("successData",mesg);
		CommonMessage.debugMsg(err.toString());
		out.print(err.toString());
	}
}
	private void saveQPoint(HttpServletRequest request,HttpServletResponse response, QPointBean qPointBean)throws IOException {

		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
		HttpSession httpSession = request.getSession(false);    	
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		ServletOutputStream out = response.getOutputStream();
		String type=request.getParameter("type");
		CommonMessage.debugMsg("type"+type);
		//String mode=null;


  try
  {
	if( httpSession != null && user != null)
	{	
		QtmTlQpoint existQtmTlQpoint  = new QtmTlQpoint();
		
		QtmTlQpoint  newQtmTlQpoint  = new QtmTlQpoint();
		QtmTlQpointdtls  newQtmTlQpointdtls  = new QtmTlQpointdtls();
		
		newQtmTlQpoint=(QtmTlQpoint)UIUtils.setBeanProperties((Object)newQtmTlQpoint,request);
		newQtmTlQpointdtls=(QtmTlQpointdtls)UIUtils.setBeanProperties((Object)newQtmTlQpointdtls,request);
		
		newQtmTlQpoint.setQptmCreatedby(user.getUsrm_ccno());
		//newQtmTlQpointdtls.setQptdCreatedby(user.getUsrm_ccno());
		//newGenTlUpstreamdefect =(GenTlVisitors)UIUtils.setBeanProperties((Object)newGenTlVisitors,request);
		CommonMessage.debugMsg(newQtmTlQpointdtls.getQptdQpoint() + "  qpoint");
		if(newQtmTlQpointdtls != null){
			CommonMessage.debugMsg(newQtmTlQpoint.getIsDtlTrue()  + " bfr if");
		  if(UIUtils.isValidKeyId(newQtmTlQpoint.getIsDtlTrue())) {
			  CommonMessage.debugMsg(newQtmTlQpointdtls.getQptdQpoint() + " aftr  qpoint");
			 CommonMessage.debugMsg(newQtmTlQpoint.getIsDtlTrue() + " true or false ");
			 newQtmTlQpoint.setQtmTlQpointdtls(newQtmTlQpointdtls);
		  }
		  newQtmTlQpointdtls.setQptdCreatedby(user.getUsrm_ccno());
		}
		String filemanger=request.getParameter("filemanger");
		
		CommonMessage.debugMsg(" Inside save Action For KeyId :: "+newQtmTlQpoint.getQptmKeyid());
		
		boolean insert = true;
		
		//CommonMessage.debugMsg("  newGenTlMommst.getMomsKeyId() " +  newGenTlVisitors.getVisiMomsKeyid());
		if( newQtmTlQpoint.getQptmKeyid() == null )
		 {							
			CommonMessage.debugMsg("KeyId is Null" );
			existQtmTlQpoint =	qtmtiqpointmstService.createQpoint(newQtmTlQpoint,existQtmTlQpoint,qPointBean);
		 }	
		else
		  {
			CommonMessage.debugMsg("Update function");						
			existQtmTlQpoint = qtmtiqpointmstService.updateQpoint(newQtmTlQpoint,existQtmTlQpoint,qPointBean);
			// mode="Modify";
			insert = false;
		  }					
		
		//httpSession.setAttribute("GenTlMOmmst", existGenTlVisitors);
		
		JSONObject successData = new JSONObject();
		String msgPropertyIdnt;					 
			 if( insert)
			   {
				msgPropertyIdnt = "success-save";
			   }
			 else
				msgPropertyIdnt = "success-update";
		 CommonMessage.debugMsg(existQtmTlQpoint.getQptmKeyid() + " keyid");
		    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
			successData.put("keyId", existQtmTlQpoint.getQptmKeyid());
			/*if(mode!=null)
    		{
    		CommonMessage.debugMsg("mode");
    			successData.put("mode", mode);
    		
    			
    		}*/
			//successData.put("dtlkeyid",existQtmTlQpoint.getQtmTlQpointdtls().getQptdQptmKeyid());
			
			JSONObject returnData = new JSONObject();
			
			if(UIUtils.isValidKeyId(filemanger)){
			    CommonMessage.debugMsg(" Inside filemanger "+filemanger);
				returnData.put("filemanger",true);
				returnData.put("formClear",false);
			}
			
			returnData.put("successData", successData);				
			returnData.put("formClear",false);
			returnData.put("keyId", existQtmTlQpoint.getQptmKeyid());
			returnData.put("type",type);
			//returnData.put("dtlkeyid",existQtmTlQpoint.getQtmTlQpointdtls().getQptdKeyid());
			
			out.print(returnData.toString());
	}
  }
  catch (ValidationExceptions e) 
  {
		CommonMessage.debugMsg("ValidationExceptions");
		net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "QPointValidation");
		out.print(errMessage.toString());
		    	
  }
								
 catch(Exception e)
      {   
	      e.printStackTrace();
         /*CommonMessage.debugMsg("Error Msg:" + e.getMessage());
         JSONObject err = new JSONObject();
         err.put("tpmException", "Data Not Saved");
         out.print(err.toString());*/
      }
}
	private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
		
        HttpSession httpSession = request.getSession(false);
  		
  		
  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
  		if( commonFilter != null && ! createNew ){
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
  		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
  		return commonFilter;
  	}
	
	
	private void deletedetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String msg;
		QtmTlQpointdtl newQtmTlpointdtl=new QtmTlQpointdtl();//model
		QparameterBean Bean= new QparameterBean();//bean
			
		QtmTlQpointdtl existQtmTlpointdtl= new 	QtmTlQpointdtl();
		newQtmTlpointdtl=(	QtmTlQpointdtl)UIUtils.setBeanProperties((Object)newQtmTlpointdtl,request);
		

		existQtmTlpointdtl = qtmtiqpointmstService.delete(newQtmTlpointdtl,existQtmTlpointdtl);
			 msg="Data Deleted Successfully";

			 JSONObject successData = new JSONObject();
				successData.put("msg",msg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);	
				returnData.put("formClear", true);
		
				PrintWriter out= response.getWriter();
				out.print(returnData.toString());//
				//out.close();
		
		
		
	}

	private void saveDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null)
				{	
				
				 QtmTlQpointdtl newqtmTlQpointdtl = new QtmTlQpointdtl();
					QparameterBean para= new 	QparameterBean();
					QtmTlQpointmst qtmTlQpointmst = new QtmTlQpointmst();
					CommonMessage.debugMsg("KKKKKKKK"+qtmTlQpointmst.getQpmKeyid());
					newqtmTlQpointdtl =(QtmTlQpointdtl)UIUtils.setBeanProperties((Object)newqtmTlQpointdtl,request);
					CommonMessage.debugMsg("studentsds code~~ "+newqtmTlQpointdtl.getQpdFrequency());
			
					QtmTlQpointdtl existqtmTlQpointdtl = (QtmTlQpointdtl)httpSession.getAttribute("para");
				      CommonMessage.debugMsg("key id"+newqtmTlQpointdtl .getQpdKeyid());
					String savemsg=null;
						boolean insert = true;
						
						if(newqtmTlQpointdtl.getQpdKeyid() == null)
						{	CommonMessage.debugMsg("key id");
						existqtmTlQpointdtl =qtmtiqpointmstService.create(newqtmTlQpointdtl,existqtmTlQpointdtl );	
					
						savemsg="Data Saved Successfully";
						}
							 
						else{
							insert = false;
							existqtmTlQpointdtl =  qtmtiqpointmstService.update(newqtmTlQpointdtl , existqtmTlQpointdtl );
							
							
							savemsg="Data Updated Successfully";
							
						}
				
			
			JSONObject successData = new JSONObject();
			successData.put("msg",savemsg);
			JSONObject returnData = new JSONObject();//
			returnData.put("successData", successData);
			
			returnData.put("formClear", true);
			out.print(returnData.toString());//
			out.close();
			
			
				}
			
				}catch(ValidationExceptions e){
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"QPMValidation");
					//errMessage.put("formActionMode",StudentMasterBean.getFormActionMode());
					out.print(errMessage.toString());
					
				}
		
		
		
		
		
	}

	private void deletemaster(HttpServletRequest request,
			HttpServletResponse response, QparameterBean bean) throws Exception {
		
		String msg;
		QtmTlQpointmst newqtmtiqpointmst=new QtmTlQpointmst();//model
		QparameterBean Bean	= new QparameterBean ();//bean
			
		QtmTlQpointmst existQtmTlSopmst= new 	QtmTlQpointmst();
		newqtmtiqpointmst =(	QtmTlQpointmst)UIUtils.setBeanProperties((Object)newqtmtiqpointmst,request);
		

				 existQtmTlSopmst = qtmtiqpointmstService.delete(newqtmtiqpointmst,existQtmTlSopmst,Bean);
				 msg="Data Deleted Successfully";

				JSONObject successData = new JSONObject();
				successData.put("msg",msg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);	
				returnData.put("formClear", true);
				//CommonMessage.debugMsg(returnData.toString());
				PrintWriter out= response.getWriter();
				out.print(returnData.toString());//
				out.close();
			// TODO Auto-generated method stub
			
		
		
		
		// TODO Auto-generated method stub
		
	}

	private void saveQParameter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
			
		CommonMessage.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null)
				{	
				
				QtmTlQpointmst newqtmtiqpointmst = new QtmTlQpointmst();
					QparameterBean para= new 	QparameterBean();
					newqtmtiqpointmst =(QtmTlQpointmst)UIUtils.setBeanProperties((Object)newqtmtiqpointmst,request);
					CommonMessage.debugMsg("studentsds code~~ "+newqtmtiqpointmst.getQpmApproveddate());
			
					  QtmTlQpointmst existqtmtiqpointmst = (QtmTlQpointmst)httpSession.getAttribute("para");
				      CommonMessage.debugMsg("key id"+newqtmtiqpointmst.getQpmKeyid());
					String savemsg=null;
						boolean insert = true;
						
						if(newqtmtiqpointmst.getQpmKeyid() == null)
						{	CommonMessage.debugMsg("key id");
						existqtmtiqpointmst =qtmtiqpointmstService.create(newqtmtiqpointmst,existqtmtiqpointmst);	
					
						savemsg="Data Saved Successfully";
						}
							 
						else{
							insert = false;
							existqtmtiqpointmst =  qtmtiqpointmstService.update(newqtmtiqpointmst, existqtmtiqpointmst,  para);
							
							
							savemsg="Data Updated Successfully";
							
						}
				
			
			JSONObject successData = new JSONObject();
			successData.put("msg",savemsg);
			JSONObject returnData = new JSONObject();//
			returnData.put("successData", successData);
			returnData.put("keyid", existqtmtiqpointmst.getQpmKeyid());
			returnData.put("formClear", false);
			out.print(returnData.toString());//
			out.close();
			
			
				}
			
				}catch(ValidationExceptions e){
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"QPMValidation");
					//errMessage.put("formActionMode",StudentMasterBean.getFormActionMode());
					out.print(errMessage.toString());
					
				}
	}
private JSONObject getTableModel(List<String[]> headers,String caption)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	//	String [] colHeader1 = new String[  headers.get(0).length + 1 ] ;
		
		String [] colHeader = headers.get(0) ;
		int header = colHeader.length; // changed from -1 
		String [] headerArr = new String[header];
		for(int k=0;k<headerArr.length;k++)
			

		//CommonMessage.debugMsg(colHeader1 +"  =caption ="+colHeader );
		colHeader[2] = caption;  
		CommonMessage.debugMsg("colHeader[1] ="+colHeader[1]);
		//colHeader[1] = caption;  
	
		jqGridTableModel.setTableButton(true);	
		JqGridColModel jqGridColModel =getColModel("keyid1",50,"left");
		jqGridColModel.setHidden(true);

		jqGridTableModel.getColModel().add(jqGridColModel);
		jqGridTableModel.setRowNumbers(true);
		jqGridColModel = getColModel("keyid2",100,"left");
		jqGridTableModel.getColModel().add(jqGridColModel);
		//colHeader1[ 0 ] = colHeader[0];
		//colHeader1[ 1 ] = colHeader[1];
		headerArr[0] = "keyid";
		headerArr[1] = "keyid";
		String headerSql = "'SELECT ";
		for(int i =2; i < header; i++)
		{
			headerArr[i] = colHeader[i].replaceAll(" ", "");
			//colHeader1[ i ] = colHeader[i];
			jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex((colHeader[i]+i).replaceAll(" ", ""));
			jqGridColModel.setName((colHeader[i]+i).replaceAll(" ", ""));
					
			if(i==2 )
			{
				jqGridColModel.setHidden(false);
				jqGridColModel.setAlign("left");
				jqGridColModel.setWidth(300);	
			}
			if(i>2)
			{
				jqGridColModel.setWidth(100);	
				jqGridColModel.setHidden(false);
				jqGridColModel.setAlign("right");
			}
			if(i==colHeader.length-1)
			{
				//jqGridColModel.setHidden(true);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		//	headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		jqGridTableModel.getRowHeaders().add(headerArr);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		
		 	
			tableModel.set("tableHeight", "72%");
			return tableModel;
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
public static JSONObject convertToJqGridTableObjectOPL(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, int colSub, long totalRecords){
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 100;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr);
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
		
		JSONObject tableDataObject = new JSONObject();
		
		CommonMessage.debugMsg(" totalRecords " + totalRecords);
		
		tableDataObject.put("page", page); //current page
		tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
		//if( page == 1)
		tableDataObject.put("records", totalRecords - rowStart); //total records
		
		JSONArray rowArr = new JSONArray(); 
       
      
		int rowId = rows * (page-1);
		int slno = 0;
        for( String [] row : dataArrayList)
		{
        	if( slno++ >= rowStart )
        	{	
	    	    JSONObject rowObj =new JSONObject();
	    	    	
	    	    rowObj.put("id",rowId -rowStart +1);
	            
	            JSONArray cell=new JSONArray();
	            
	            for( int i = colStart ;i < (row.length - colSub); i++)
	            {	 
	            	cell.put( ( row[i] != null ?row[i].isEmpty() ?" ":  row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") ); 
	            }	
	            rowObj.put("cell",cell);
	            
	            rowArr.put(rowObj);
        	}
        	rowId++;
       }

        tableDataObject.put("rows", rowArr);
        
        return tableDataObject;

	}

	
	private JSONObject getTableModelPoints(List<String[]> headers)
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
			jqGridColModel.setAlign("right");
			if(i==0 ){
				jqGridColModel.setWidth(800);
				jqGridColModel.setAlign("left");
			}
			
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		CommonMessage.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	
	
}
	
	
	



				

		
	

	

	
	
	
	
	
	
	
	
	
	
	

