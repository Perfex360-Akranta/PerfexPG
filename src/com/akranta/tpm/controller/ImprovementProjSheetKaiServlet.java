/*  Author ManiKandan
 * 
 * Modified : Karthick.T 
 * 
 * Date : 24-Nov-2011
 * */
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

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.ImprovementProjSheetKaiService;
import com.akranta.tpm.service.impl.ImprovementProjSheetKaiServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
public class ImprovementProjSheetKaiServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;	
	ImprovementProjSheetKaiService improvementProjSheetKaiService;
	public ImprovementProjSheetKaiServlet() throws Exception{
		super();	
		//improvementProjSheetKaiService = new ImprovementProjSheetKaiServiceImpl();		
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
		String action = UIUtils.getActionPart(request);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		try {
			improvementProjSheetKaiService = (ImprovementProjSheetKaiServiceImpl)UIUtils.getServiceObject(request,"ImprovementProjSheetKaiServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}		
		String dispatchUrl =null;
		 if( action.equals("filterXmlImpprojSht_input.ipsrpt")){			
				response.setContentType("xml"); 				
				UIUtils.forwardRequest(request, response, "/tiles/xml/ImprovementProjectSheet.xml") ;
			 }	
		if(action.equals("ImpprojSht_input.ipsrpt")) 
		{
			CommonMessage.debugMsg("before page load");
			RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/ImprovementProjSheet.jsp"); 
			rd.forward(request, response); 
			CommonMessage.debugMsg("After page load");
		}
		else if(action.equals("ImpprojSht_getCol.ipsrpt"))//
		{			
			HttpSession httpSession = request.getSession(false);
			CommonFilter  commonFilter = populateCommonFilter(request,"ImprovementProjSheetCommonFilter",true);	
			//FilterValues.getCommonFilters(request, commonFilter);
			//FilterValues.getOPLandKaizen(request, commonFilter);
			commonFilter.setViewClick('Y');
			if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			
			PrintWriter out = response.getWriter();
			List<String[]> impprojshtQueryList  =  improvementProjSheetKaiService.getAllImprProjSht(commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			
			gridColModel.setHeaderNum(1);
			gridColModel.setFormatter("actionFormatterHD");
			gridColModel.setFormattorFromCol("1");
			gridColModel.setFormattorToCol("2");
			String [] colHeader = impprojshtQueryList.get(0);			
			String [] colHeaderCond = impprojshtQueryList.get(1);
			
			CommonMessage.debugMsg("   TABLEMODEL     "+impprojshtQueryList.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			httpSession.removeAttribute("ImprovementProjSheetColmodel");
			httpSession.setAttribute("ImprovementProjSheetColmodel", jsonObject);
			jsonObject.put("tableHeight", "82%%");
			jsonObject.put("tableWidth", "106%%");
			out.println(jsonObject);
			/*String colmodel =UIUtils.getPropertyValue("com.akranta.tpm.resources.ImprovementProjectSheetRpt", "ImprovementProjectSheet");
			JSONObject colModelObj = JSONObject.fromString(colmodel );
			CommonMessage.debugMsg("");
			out.print(colModelObj);*/
		}
		else if( action.equals("ImpprojSht_getData.ipsrpt") )
		{
			try
			{
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();				
				CommonFilter  commonFilter =populateCommonFilter(request,"ImprovementProjSheetCommonFilter",false);	
				
				 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
					  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
				  } 
				 
				//CommonFilter  commonFilter = (CommonFilter ) httpSession.getAttribute("ImprovementProjSheetCommonFilter");
  			 	List<String[]> impprojshtQueryList  =  improvementProjSheetKaiService.getAllImprProjSht(commonFilter);
  			 	
  			 	JSONObject improjshtData = UIUtils.convertToJqGridTableObject(impprojshtQueryList,request,2,0,commonFilter.getTotalRecordCnt());   				
  			 	out.println(improjshtData);  				
  				
		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}		
		else if( action.equals("ImpprojSht_getExcel.ipsrpt"))
		{			
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"ImprovementProjSheetCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			HttpSession httpSession = request.getSession(false);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ImprovementProjectSheetRpt", "ImprovementProjectSheet");
			//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("ImprovementProjSheetColmodel");
			
			tblJSONObj.put("title", "Improvement Report");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = improvementProjSheetKaiService.improvementSmryReportExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "ImprovementReport", format);
			
			/*HttpSession httpSession = request.getSession(false);
			String format = ExcelUtils.getFormat(request);
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("ImprovementProjSheetCommonFilter");
			List<String[]> impprojshtQueryList  =  improvementProjSheetKaiService.getAllImprProjSht(commonFilter);
			String fileName= "ImprovementProjSheetRpt.xls";
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ImprovementProjectSheetRpt", "ImprovementProjectSheet");
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			//ExcelUtilsk.writeToResponse(response, wb, "OPLSummary", format);
			//excelUtils.writeToExcel(request, response, fileName, impprojshtQueryList,(short)0,(short)1,(short)0);*/
			
		}
		else if( action.equals("ImpprojSht_view.ipsrpt"))
		{	
			
			try{
			
			CommonFilter  commonFilter = new CommonFilter();
			String kaizId = request.getParameter("kaizenId");
			CommonMessage.debugMsg(" kaizId :: 1234 "+kaizId);
			String benTypeVal = request.getParameter("benTypeVal");
			CommonMessage.debugMsg(" benTypeVal :: 1234 "+benTypeVal);
			String flid=request.getParameter("flid");
			CommonMessage.debugMsg(" flid :: 1234 "+flid);
			String User=user.getUsrm_ccno();
			CommonMessage.debugMsg(" User :: 1234 "+User);
			//commonFilter.setFlid(flid);
			String workFlow = request.getParameter("workFlow");//workFlow
			CommonMessage.debugMsg(" workFlow :: "+workFlow);
			String format = ExcelUtils.getFormat(request);
			String path = UIUtils.getExcelTemplatePath(request);  	
			String imagePath = UIUtils.getImagePath(request);
			CommonMessage.debugMsg("format:::"+format);
			CommonMessage.debugMsg("path:::"+path);
			CommonMessage.debugMsg("kaizId:::"+kaizId);
			Workbook wb = improvementProjSheetKaiService.kaizenExportExcel(kaizId,flid,benTypeVal,User,format,path,imagePath,workFlow);
			format = ".xlsx";
 
			//ExcelUtils.writeToResponse(response, wb, "KaizenIdeaSheet", format);
			ExcelUtils.writeToResponse(response, wb, "KaizenSheet_"+kaizId, format);
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
			commonFilter = 	FilterValues.getCommonFilters(request,commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getOPLandKaizen(request,commonFilter);
			//commonFilter.setMonwise("N");
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
	
}