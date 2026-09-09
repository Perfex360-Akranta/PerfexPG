
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
	import org.apache.poi.ss.usermodel.Workbook;
	import net.sf.json.JSONArray;
	import net.sf.json.JSONObject;


	
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
	import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
	import com.akranta.tpm.model.ComboFilter;
	import com.akranta.tpm.model.CommonFilter;
	import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GridColModel;
	import com.akranta.tpm.service.OplService;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
	import com.akranta.tpm.service.impl.OplServiceImpl;
import com.akranta.tpm.service.impl.OplTlMstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
	import com.akranta.tpm.utils.ExcelUtils;
//import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

	public class OplReportServlet extends HttpServlet{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		
		OplService oplService;
		OplTlMstServiceApi oplServiceApi;	
		public OplReportServlet() throws Exception{
			super();
		
			//oplService = new OplServiceImpl();
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
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			String dispatchUrl =null;
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String action = UIUtils.getActionPart(request);
			
			try {
				oplService = (OplServiceImpl)UIUtils.getServiceObject(request,"OplServiceImpl");
				HttpSession httpSession = request.getSession(false);  
				CommonMessage.debugMsg("  oplServices jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
				//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
					oplService.OplServiceImplJwt(
							   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
							);
					
			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
			}
			
			if( action.equals("filterXmlOplReport_input.oplrpt")){
				 response.setContentType("xml"); 
				CommonMessage.debugMsg("action "+ action); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/OPLReport.xml") ;
			 }
			
			
			else if(action.equals("OplReport_input.oplrpt")) 
			{
				CommonMessage.debugMsg("before page load");
				request.setAttribute("excelView", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","excelView"));
				RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/OPl.jsp"); 
				rd.forward(request, response); 
				CommonMessage.debugMsg("After page load");
			}
			
			else if(action.equals("oplSummaryReport_input.oplrpt")){
				CommonMessage.debugMsg("oplSummaryReport_input.oplrpt");
				UIUtils.forwardRequest(request, response,"/pages/OplSummaryReport.jsp");
				
			}else if(action.equals("oplSummaryReport_getCol.oplrpt")){
				PrintWriter out = response.getWriter();	
				HttpSession httpSession=request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"OPLSummaryCommonFilter",true);
				
				commonFilter.setIsGetCol("Y");
				List<String[]> OPLGridData  = oplService.getOPLSummaryGridData(commonFilter);
				
				JqGridTableModel jqGridTableModel = new JqGridTableModel();
				GridColModel gridColModel = new GridColModel();

				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);

				gridColModel.setHeaderNum(1);

				String[] colHeader = OPLGridData.get(1); // CHANGED FROM 2 TO 1 VIGNESH
				String[] colHeaderCond = OPLGridData.get(0); // CHANGED FROM 1 TO 0 VIGNESH
				List<String[]> headers = new ArrayList<String[]>();
				
				headers.add(colHeader);
				
				JSONObject colModel =new JSONObject();
				colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
				colModel.set("tableHeight", "90%%");
				colModel.set("tableWidth", "110%%");
				httpSession.removeAttribute("OPLSummaryColModel");
				httpSession.setAttribute("OPLSummaryColModel", colModel);
				
				httpSession.removeAttribute("OPLSummaryCommonFilter");
				httpSession.setAttribute("OPLSummaryCommonFilter", commonFilter);
				
				out.println(colModel);
				

				
			}else if(action.equals("oplSummaryReport_getData.oplrpt")){
				
				PrintWriter out = response.getWriter();	
				HttpSession httpSession=request.getSession(false);
				CommonFilter commonFilter=populateCommonFilter(request, "OPLSummaryCommonFilter", false);
				commonFilter.setIsGetCol("N");
				List<String[]> OPLGridData = oplService.getOPLSummaryGridData(commonFilter);
				JSONObject dataJson = UIUtils.convertToJqGridTableObject(OPLGridData,request, 2, 0,commonFilter.getTotalRecordCnt() ); // -- CAHNGED FROM 3 TO 2 VIGNESH
				out.print(dataJson);
				httpSession.removeAttribute("OPLSummaryCommonFilter");
				httpSession.setAttribute("OPLSummaryCommonFilter", commonFilter);
				
			}else if(action.equals("oplSummaryReport_getExcel.oplrpt")){
				HttpSession httpSession=request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"OPLSummaryCommonFilter",false);			
				JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("OPLSummaryColModel");
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				tblJSONObj.put("title", "OPL Summary Report");			
				String format = ExcelUtils.getFormat(request);	
				Workbook wb = oplService.getOplSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);
				
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "OPLSummaryReport", format);
			}
			
			else if(action.equals("OplReport_getCol.oplrpt"))
			{
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				
				CommonFilter commonFilter =populateCommonFilter(request,"OnePointLessonCommonFilter",true);				
				 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
					 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
					 commonFilter.setToDate(CommonFunctions.getDate());
				 }	 
				  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  } 
		 		commonFilter.setViewClick('Y');
		 		commonFilter.setIsGetCol("Y");
		 		List<String []> oplModifyList  = oplService.getAllOPl(commonFilter);
				//if(action.equals("oplVw_getCol.opl")){
					//out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", "oplView"));
				//}else{
					// jsonObject = getTableModel(oplCummulativeList,FilterValues.getHeader( commonFilter.getDrillCaption()));
		      		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = oplModifyList.get(1);			
					String [] colHeaderCond = oplModifyList.get(0);
					
					//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					httpSession.removeAttribute("oplReportColModel");
					//httpSession.setAttribute("oplReportColModel", jsonObject);
					jsonObject.put("tableHeight", "80%%");
					jsonObject.put("tableWidth", "106%%");
			      	CommonMessage.debugMsg("jsonObject ="+jsonObject);
			      	 out.println(jsonObject);
			      	/*String colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRpt","OPLRpt");
				JSONObject colModelObj = JSONObject.fromString(colmodel );
				httpSession.removeAttribute("oplReportColModel");
				httpSession.setAttribute("oplReportColModel", colModelObj);
				out.print(colModelObj);*/
				
				
			}
			else if( action.equals("OplReport_getData.oplrpt") )
			{
				try
				{
						UIUtils.displayRequestParamsValue(request);
						PrintWriter out = response.getWriter();
						HttpSession httpSession = request.getSession();
						CommonFilter commonFilter = populateCommonFilter(request,"OnePointLessonCommonFilter",true);
						
						FilterValues.getCommonFilters(request, commonFilter);
						FilterValues.getOPLandKaizen(request, commonFilter);
					 	
						 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
							 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
							 commonFilter.setToDate(CommonFunctions.getDate());
						 }	 
						  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
							  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
							  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
							  commonFilter.setMonwise("Y");
					 	  } 
						  commonFilter.setIsGetCol("N");
					 	List<String[]> oplQueryList  =  oplService.getAllOPl(commonFilter);
					 	JSONObject oplData = UIUtils.convertToJqGridTableObject(oplQueryList, request, 2, 0, commonFilter.getTotalRecordCnt()+2);
					 	//(oplQueryList,request,0,0); 
	  				
				 	 	out.println(oplData);
		  			 	commonFilter.setViewClick('N');
		  			 	
		  			 	httpSession.removeAttribute("OnePointLessonCommonFilter");
		  			 	httpSession.setAttribute("OnePointLessonCommonFilter", commonFilter);

			    }
				catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
		
			else if( action.equals("OplReport_getExcel.oplrpt")){
				
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"OnePointLessonCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("oplReportColModel");
				JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();
				
				String month1   = fromMonth +"  -  "+ toMonth ;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String date   = fromdate +"  -  "+ todate ;
				
				if((!Constants.passNullDate.contains(commonFilter.getFromDate()) || Constants.passNullDate.contains(commonFilter.getToDate())) && commonFilter.getMonwise().equals("N"))
				tblJSONObj.put("title", "One Point Lesson Report"+ " - "+date);
				if((!Constants.passNullDate.contains(commonFilter.getFromMonth()) || Constants.passNullDate.contains(commonFilter.getToMonth())) && commonFilter.getMonwise().equals("Y"))
				tblJSONObj.put("title", "One Point Lesson Report" + " - " + month1);	
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = oplService.getAllOPlxl(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "OplReport", format);
				
			}
			else if( action.equals("OplReport_Excelview.oplrpt") )/*Created By Siddharth.A*/ 
			{
		
				
					try{
						CommonFilter commonFilter = new CommonFilter();					
						String OPLId = request.getParameter("oplId");
						String flid = request.getParameter("flid");
						String type = request.getParameter("type");
						CommonMessage.debugMsg("OPLId::::::"+OPLId);
						CommonMessage.debugMsg("type::::::"+type);
						String format = ExcelUtils.getFormat(request);
						commonFilter = FilterValues.getCommonFilters(request, commonFilter);
						commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
						String User=user.getUsrm_ccno();
						String path = UIUtils.getExcelTemplatePath(request);  	
						String imagePath = UIUtils.getImagePath(request);
						format = "xlsx";
						Workbook wb = oplService.kaizenExportExcel(OPLId,format,path,flid,type,User,imagePath,commonFilter);
						ExcelUtils.writeToResponse(response, wb, "OplReportSheet_"+OPLId, format);
					
					}
				
					catch(Exception e)
					{
						CommonMessage.debugMsg("err:"+e.getMessage());
						PrintWriter out = response.getWriter();
						JSONObject err = new JSONObject();				
						//err.put("exception",true);				
						err.put("message" ,"Data Not Found" );
						out.print(err.toString());
					}
					
				
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
				
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
				commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
		
}