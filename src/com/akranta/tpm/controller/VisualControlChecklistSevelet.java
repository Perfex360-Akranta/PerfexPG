
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent;

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
import com.akranta.tpm.bean.VisualConBean;
import com.akranta.tpm.bean.VisualControlCheckListBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlVisualcntchecklistdtl;
import com.akranta.tpm.model.GenTlVisualcontrolchecklist;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.SopTlVisualchecklistdtl;
import com.akranta.tpm.model.SopTlVisualchecklistmst;
import com.akranta.tpm.service.VisualControlCheckServices;
import com.akranta.tpm.service.impl.VisualControlCheckServicesImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * 
 */




public class VisualControlChecklistSevelet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    VisualControlCheckServices visualControlCheckServices;
    private static final String commonFilterScore = "VisualScorecommonFilter"; 
       
   
    public VisualControlChecklistSevelet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unused")
	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		HttpSession httpSession = request.getSession(false);
		
		CommonMessage.debugMsg("VisualControlChecklistSevelet.......");	
		String action = UIUtils.getActionPart(request);
		try {
			 visualControlCheckServices = (VisualControlCheckServicesImpl)UIUtils.getServiceObject(request,"VisualControlCheckServicesImpl");
			 visualControlCheckServices.VisualControlCheckServicesImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
			 String dispatchUrl =null;
			
			
			if(action.equals("VisualControlCheckList_input.visc") )
			{  
				UIUtils.forwardRequest(request, response,"/pages/VisualControlCheckListGrid.jsp");	
			}else if (action.equals("filterXmlvisualcontrolchecklistreport_input.visc")) {
				response.setContentType("xml");
				CommonMessage.debugMsg("action " + action);
				UIUtils.forwardRequest(request, response,
						"/tiles/xml/visualControlChecklistRpt.xml");
			}
			else if(action.equals("visualcontrolchecklistreport_input.visc") )
			{  
				UIUtils.forwardRequest(request, response, "/pages/VisualControlCheckListReport.jsp");
			}
			
			else if(action.equals("chartVisualScoreGraph.visc"))
			{
				processChartVisualScoreGraph(request,response);
				
			}

			
			else if(action.equals("VisualControlChart_input.visc") )
			{ 
				response.setContentType("xml");
				String keyId=request.getParameter("keyid");
				
				String grid=request.getParameter("grid");
				CommonMessage.debugMsg("grid     "+ grid);
			     String elementid=CommonFunctions.getLoginElementId(request);
			      //  CommonMessage.debugMsg("login element id"+elementid);
			       String locationid=null;
			        if(elementid.length()>10){
			        locationid=elementid.substring(11,21);
			        //CommonMessage.debugMsg("locationid"+locationid);
			        }
				
				
				String mode= request.getParameter("mode");
				if(UIUtils.isValidKeyId(mode))
					mode = "view";
				request.setAttribute("mode", mode);
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist = new GenTlVisualcontrolchecklist();
				if(UIUtils.isValidKeyId(keyId)){
					
					
					newGenTlVisualcontrolchecklist = visualControlCheckServices.getSelect(keyId);
					
				}else{
					newGenTlVisualcontrolchecklist.setVcclApprovedby(user.getUsrm_ccno());
					newGenTlVisualcontrolchecklist.setVcclEmployeeid(user.getUsrm_ccno());
				}
				request.setAttribute("visualcontrol", newGenTlVisualcontrolchecklist);
				//else if(grid.equals(false)){
					//String mode="grid";
					request.setAttribute("user",user.getUsrm_ccno());
					request.setAttribute("locationid",locationid);
					
				//}
				RequestDispatcher rd = request.getRequestDispatcher("/pages/VisualControlChecklist.jsp");	
				rd.forward(request, response);
			}
			
			else if(action.equals("visualcheckpoints_input.visc") )
			{ 
				response.setContentType("xml"); 
				String keyId=request.getParameter("keyid");
				CommonMessage.debugMsg("keyid "+keyId);
			    if(UIUtils.isValidKeyId(keyId)){
			    	CommonMessage.debugMsg("keyid st "+keyId);
					SopTlVisualchecklistmst newSopTlVisualchecklistmst = new SopTlVisualchecklistmst();
					CommonMessage.debugMsg("keyid st1 "+keyId);
					newSopTlVisualchecklistmst =visualControlCheckServices.getSelectvis(keyId);
					request.setAttribute("newSopTlVisualchecklistmst", newSopTlVisualchecklistmst);
				}
			    CommonMessage.debugMsg("keyid 1  "+keyId);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/visualcheckpointsgrid.jsp");
				 CommonMessage.debugMsg("keyid2"+keyId);
				rd.forward(request, response);
			}
			else if(action.equals("visualcheckpoints_modify.visc") )
			{
				response.setContentType("xml"); 
				String keyId=request.getParameter("keyid");
				CommonMessage.debugMsg("keyid "+keyId);
			    if(UIUtils.isValidKeyId(keyId)){
			    	SopTlVisualchecklistmst newSopTlVisualchecklistmst = new SopTlVisualchecklistmst();
			    	newSopTlVisualchecklistmst =visualControlCheckServices.getSelectvis(keyId);
					request.setAttribute("newSopTlVisualchecklistmst", newSopTlVisualchecklistmst);
			    }
				RequestDispatcher rd = request.getRequestDispatcher("/pages/VisualPopup.jsp");
				 CommonMessage.debugMsg("keyid2");
				rd.forward(request, response);
			}
			else if(action.equals("visualcheckpoints_getCol.visc") )
			{
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlctrlCommonFilter",true);
				List<String[]> visualGridList = visualControlCheckServices.getVisualControlcheckpoints(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject jsonObject = getTableModelcheckpoints(visualGridList);
				httpSession.removeAttribute("VisualColmodel");
				httpSession.setAttribute("VisualColmodel", jsonObject);
				out.println(jsonObject);
				
			}
			else if(action.equals("visualcheckpoints_getData.visc") )
			
				{try
				{
				UIUtils.displayRequestParamsValue(request);	
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",false);
				List<String[]> visualGridList = visualControlCheckServices.getVisualControlcheckpoints(commonFilter);
				PrintWriter out = response.getWriter();
	  			JSONObject visualQueryData = UIUtils.convertToJqGridTableObject(visualGridList,request,1,0); 
	  			out.println(visualQueryData);  			 	
	  			}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			
			
			
			else if(action.equals("functionalLoc.visc"))
			{
				//CommonMessage.debugMsg("action");
					
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				//functLocFieldNameBean.setFactory("factory");
				functLocFieldNameBean.setSection("section");
				functLocFieldNameBean.setCell("cell");
				functLocFieldNameBean.setMachine("machine");
				functLocFieldNameBean.setFunctionalLocId("cmbVcclFlid");
			//	functLocFieldNameBean.setFactMandatory(true);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(true);
				functLocFieldNameBean.setMachMandatory(false);
				
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
				
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			}
			else if( action.equals("VisualControlChart_save.visc"))
			{	
				VisualControlCheckListBean visualControlCheckListBean = new  VisualControlCheckListBean();
				saveVisualControl(request,response,visualControlCheckListBean);
		    }
			else if( action.equals("VisualControlChart_delete.visc"))
			{	
				deleteVisualControl(request,response);
				
		    }
			else if(action.equals("VisualControlCheckList_getExcel.visc"))
			{
				
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();//
				commonFilter.setFromRow(null);//
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("VisualColmodel");
				colmodel.put("title","Visual Control Check List Report");
	            String format = ExcelUtils.getFormat(request);
				
				Workbook wb = visualControlCheckServices.getVisualExcel(colmodel,format,commonFilter);
				commonFilter.setFromRow(tmpFromRow);//
				ExcelUtils.writeToResponse(response, wb, "VisualReports", format);
				
			}
			
			else if(action.equals("visualcheckpoints_getExcel.visc"))
			{
				CommonMessage.debugMsg("servlet excel1"); 
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();//
				commonFilter.setFromRow(null);//
				CommonMessage.debugMsg("servlet excel2"); 
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("VisualColmodel");
				colmodel.put("title","Visual  Check Points Report");
	            String format = ExcelUtils.getFormat(request);
				CommonMessage.debugMsg("servlet excel"); 
				Workbook wb = visualControlCheckServices.getVisualpointsExcel(colmodel,format,commonFilter);
				CommonMessage.debugMsg("servlet excel finishd");
				commonFilter.setFromRow(tmpFromRow);//
				ExcelUtils.writeToResponse(response, wb, "VisualPointsReports", format);
				
			}
			
			
			else if( action.equals("visualcheckpoints_save.visc"))
			{	
				VisualConBean visualBean= new VisualConBean();
				saveVisualConmasdet(request,response,visualBean);
		    }
			else if(action.equals("VisualControlPopup_save.visc"))
			   {//master save
	              VisualConBean visualBean= new VisualConBean();
		          savevisual(request,response);
			   }
			
			/*if (action.equals("VisualControlPopup_input.visc")) 
			{    //key id
				response.setContentType("xml");
				String keyId=request.getParameter("keyid");
				CommonMessage.debugMsg("keyid "+keyId);
			    if(UIUtils.isValidKeyId(keyId)){
			    	CommonMessage.debugMsg("keyid st "+keyId);
					SopTlVisualchecklistmst newSopTlVisualchecklistmst = new SopTlVisualchecklistmst();
					CommonMessage.debugMsg("keyid st1 "+keyId);
					newSopTlVisualchecklistmst =visualControlCheckServices.getSelectvis(keyId);
					request.setAttribute("newSopTlVisualchecklistmst", newSopTlVisualchecklistmst);
				}
			    CommonMessage.debugMsg("keyid 1  "+keyId);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/VisualPopup.jsp");
				 CommonMessage.debugMsg("keyid2"+keyId);
				rd.forward(request, response);
				CommonMessage.debugMsg(" response " + response);
			
			}*/
			else if (action.equals("VisualPopup_getCol.visc"))
			{ 
				//PrintWriter out = response.getWriter();
				String keyId=request.getParameter("keyid");
				CommonFilter commonFilter =  populateCommonFilter(request,"visualCommonFilter",true);
				commonFilter.setKey(keyId);
				List<String[]> visualGridList = visualControlCheckServices.getSelectvisual(commonFilter);
				PrintWriter out = response.getWriter();
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
			
				gridColModel.setHeaderNum(1);
				
				/*gridColModel.setFormatter("txtvisualformater");
				gridColModel.setFormattorFromCol("4");
				gridColModel.setFormattorToCol("4");
				*/
				String [] colHeader = visualGridList.get(1);			
				String [] colHeaderCond = visualGridList.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				
				headers.add(colHeader);
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("multiSelect", true);
				jsonObject.put("tableWidth", "95%%");
				jsonObject.put("tableHeight", "50%%");
				out.println(jsonObject);
			}
			
				
				
			
			
			else if(action.equals("combo_title.visc"))
			{
		
				ComboFilter	comboFilter=UIUtils.fillComboFilter(request);
				//String title = request.getParameter("title");
				String flid = request.getParameter("flid");
				//String flid =request.getParameter("flid");
				CommonMessage.debugMsg("flid "  +    flid);
				
				List<ComboBox> comboList = visualControlCheckServices.getRecordedbyComboList(comboFilter,flid);
				UIUtils.writeComboBox(response, comboList,comboFilter);
			}else if (action.equals("Vclcombo_title.visc")) {
				CommonFilter commonFilter= new CommonFilter();
				PrintWriter out = response.getWriter();
				String title = request.getParameter("title");
				commonFilter.settitle(title);
					List<String []> title1  = visualControlCheckServices.selecttitle( commonFilter);
					out.print( JSONArray.fromCollection(title1));				
			}
			
			else if (action.equals("VisualPopup_getData.visc")) { 
				try
				{
				
					
					String keyId=request.getParameter("keyid");
					CommonFilter commonFilter =  populateCommonFilter(request,"visCommonFilter",false);
					commonFilter.setKey(keyId);
					List<String[]> grid = visualControlCheckServices.getSelectvisual(commonFilter);
					
					PrintWriter out = response.getWriter();
					JSONObject vismaster = UIUtils.convertToJqGridTableObject(grid ,request, 2, 0);
					out.println(vismaster );
					CommonMessage.debugMsg(vismaster );
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
	
				
			else if( action.equals("VisualControlPopup_delete.visc"))
			{	
				deleteVisualmasdetControl(request,response);
				
		    }
			else if( action.equals("VisualPopup_delete.visc"))
			{
			      deletevis(request,response);
			}
			
			
			else if(action.equals("VisualControlPopup_getExcel.visc"))
			{
				
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter1(request,"visCommonFilter",false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("UnSafeWorkColmodel");
				colmodel.put("title","Visual Report");
	            String format = ExcelUtils.getFormat(request);
				
				Workbook wb = visualControlCheckServices.getvisualWorkExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "reports", format);
				
			}
			else if(action.equals("VisualControlCheckList_getCol.visc"))
			{
				httpSession.removeAttribute("VisualControlCommonFilter");
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",true);
				commonFilter.setIsGetCol("Y");
				List<String[]> visualGridList = visualControlCheckServices.getVisualControl(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject jsonObject = getTableModelVisual(visualGridList);
				httpSession.removeAttribute("VisualColmodel");
				httpSession.setAttribute("VisualColmodel", jsonObject);
				out.println(jsonObject);
			}
			else if(action.equals("VisualControlCheckList_getData.visc"))
			{
				try
				{
					UIUtils.displayRequestParamsValue(request);	
					CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",false);
					commonFilter.setIsGetCol("N");
					List<String[]> visualGridList = visualControlCheckServices.getVisualControl(commonFilter);
					PrintWriter out = response.getWriter();
		  			JSONObject visualQueryData = UIUtils.convertToJqGridTableObject(visualGridList,request,1,0,commonFilter.getTotalRecordCnt()); 
					
		  			out.println(visualQueryData);  
		  			httpSession.removeAttribute("VisualControlCommonFilter");
	  			}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if(action.equals("VisualControlCheckListReport_getCol.visc"))
			{
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",true);
				commonFilter.setIsGetCol("Y");
				List<String[]> visualGridList = visualControlCheckServices.getVisualControlReport(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject jsonObject = getTableModelReport(visualGridList);
				httpSession.removeAttribute("VisualColmodel");
				httpSession.setAttribute("VisualColmodel", jsonObject);
				out.println(jsonObject);
			}
			else if(action.equals("VisualControlCheckListReport_getData.visc"))
			{try
				{
				UIUtils.displayRequestParamsValue(request);	
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",false);
				commonFilter.setIsGetCol("N");
				List<String[]> visualGridList = visualControlCheckServices.getVisualControlReport(commonFilter);
				PrintWriter out = response.getWriter();
	  			JSONObject visualQueryData = UIUtils.convertToJqGridTableObject(visualGridList,request,2,1,commonFilter.getTotalRecordCnt()); // changed 09-march
	  			out.println(visualQueryData);  			 	
	  			}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if(action.equals("VisualControlCheckListReport_getExcel.visc"))
			{
				
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("VisualColmodel");
				colmodel.put("title","Visual Control Check List Report");
	            String format = ExcelUtils.getFormat(request);
				
				Workbook wb = visualControlCheckServices.getVisualExcelReport(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "VisualReports", format);
				
			}
			else if(action.equals("Visual_getCol.visc"))
			{	String keyid = request.getParameter("masterkeyid");
		     	String title= request.getParameter("title");
		     	String flid= request.getParameter("flid");
		     	String date = request.getParameter("date");
		     	CommonMessage.debugMsg("title"+title);
		     	CommonMessage.debugMsg("flid"+flid);
		     	CommonMessage.debugMsg("master"+keyid);
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",true);
			    commonFilter.settitle(title);
			    commonFilter.setFlid(flid);
			    commonFilter.setFromDate (date);
				List<String[]> visualGridList = visualControlCheckServices.getVisualControlGrid(commonFilter,keyid);
				PrintWriter out = response.getWriter();
				JSONObject jsonObject = getTableModel(visualGridList);
				httpSession.removeAttribute("VisualColmodel");
				httpSession.setAttribute("VisualColmodel", jsonObject);
				out.println(jsonObject);
			}
			else if(action.equals("Visual_getData.visc"))
			{
				try
				{
				String keyid = request.getParameter("masterkeyid");
				String title= request.getParameter("title");
				String flid= request.getParameter("flid");
				String date = request.getParameter("date");
				UIUtils.displayRequestParamsValue(request);	
				CommonMessage.debugMsg("masterkeyid "+keyid);
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",false);
				commonFilter.settitle(title);
				commonFilter.setFlid(flid);
				commonFilter.setFromDate (date);
				commonFilter.setKey(keyid);
				List<String[]> visualGridList = visualControlCheckServices.getVisualControlGrid(commonFilter,keyid);
				PrintWriter out = response.getWriter();
	  			JSONObject visualQueryData = UIUtils.convertToJqGridTableObject(visualGridList,request,1,0); 
	  			out.println(visualQueryData);  			 	
	  			}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
		
			else if(action.equals("Visual_getExcel.visc"))
			{
				String keyid = request.getParameter("masterkeyid");
				String title= request.getParameter("title");
				String flid= request.getParameter("flid");
				String date = request.getParameter("date");
				UIUtils.displayRequestParamsValue(request);	
				CommonMessage.debugMsg("masterkeyid "+keyid);
				CommonFilter commonFilter = populateCommonFilter(request,"VisualControlCommonFilter",false);
				commonFilter.setFromRow(null);
				CommonMessage.debugMsg("masterkeyid== "+keyid);
				commonFilter.settitle(title);
				commonFilter.setFlid(flid);
				commonFilter.setFromDate (date);
				commonFilter.setKey(keyid);
				
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("VisualColmodel");
				colmodel.put("title","Visual  Check Points Report - " + title + " ( " + date + " ) " );
	            String format = ExcelUtils.getFormat(request);
				CommonMessage.debugMsg("servlet excel"); 
				Workbook wb = visualControlCheckServices.getVisualRptExcel(colmodel,format,commonFilter);
				CommonMessage.debugMsg("servlet excel finishd");
				ExcelUtils.writeToResponse(response, wb, "VisualPointsReports", format);
				
			}
			
			else if(action.equals("visualWorkplaceScore_input.visc"))
			{
				CommonMessage.debugMsg("visualWorkplaceScore_input.visc");
				request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/visualWorkplaceScore.jsp");
				rd.forward(request, response); 
			}
			
			else if (action.equals("visualWorkplaceScore_getCol.visc")) {
				PrintWriter out = response.getWriter();
				String firstClick = request.getParameter("firstClick");
				
				String type = request.getParameter("type");
				
				CommonMessage.debugMsg(" Inside getCol "+type);
				
				CommonFilter commonFilter = new CommonFilter();
				if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
					 commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterScore);
				}
				
				if (commonFilter == null)
					commonFilter = new CommonFilter();
				commonFilter=populateCommonFilter(request,commonFilterScore,true);
				
				if(UIUtils.isValidKeyId(type))
					commonFilter.setAbnViewType(type);
					
				
				httpSession.removeAttribute(commonFilterScore);
				httpSession.setAttribute(commonFilterScore, commonFilter);
				FilterValues.getCommonFilters(request, commonFilter);
				FilterValues.getSafty(request, commonFilter);
				
				if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
				{
					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					commonFilter.setMonwise("Y");
				}
				
				commonFilter.setIsGetCol("Y");
				List<String[]> impVscomList = visualControlCheckServices.getVisualWPScoreGraph(commonFilter);
				JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
				JSONObject jsonObject = getTableModelGraph(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
				jsonObject.set("tableHeight", "73%%");
				jsonObject.set("tableWidth", "107%%");
				httpSession.removeAttribute("visualWPScoreData");
				httpSession.setAttribute("visualWPScoreData", jsonObject);
				CommonMessage.debugMsg("Table Model:" + jsonObject);
				out.println(jsonObject);
			} 
			
			else if (action.equals("visualWorkplaceScore_getData.visc")) {
				try {
					CommonFilter commonFilter = populateCommonFilter(request,commonFilterScore,false);
					response.setContentType("text/html");
					httpSession.getAttribute(commonFilterScore);
					
					String type = request.getParameter("type");
					
					CommonMessage.debugMsg(" Inside getData "+type);
					
					if(UIUtils.isValidKeyId(type))
						commonFilter.setAbnViewType(type);
					
					CommonMessage.debugMsg("commonFilter.getFlid()  "+commonFilter.getFlid());
					
					commonFilter.setIsGetCol("N");
					List<String[]> impVscomList = visualControlCheckServices.getVisualWPScoreGraph(commonFilter);
					
					JSONObject listToJsonObject = new JSONObject();
					
					CommonMessage.debugMsg("size of dash..."+impVscomList.size());
					if (impVscomList != null && impVscomList.size() > 2)
						listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
					httpSession.removeAttribute(commonFilterScore);
					httpSession.setAttribute(commonFilterScore, commonFilter);
					PrintWriter out = response.getWriter();
					out.println(listToJsonObject);
				}

				catch (Exception e) {
					CommonMessage.debugMsg("error " + e.getMessage());
				}
			}
			
			else if( action.equals("visualWorkplaceScore_getExcel.visc"))
			{			
				
			CommonFilter commonFilter = populateCommonFilter(request,commonFilterScore,false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = (JSONObject)httpSession.getAttribute("visualWPScoreData");
			
			//String type = request.getParameter("type");
			CommonMessage.debugMsg("commonFilter.getDrillCaption()"+commonFilter.getDrillCaption());
			
			tableModel.put("title", " Visual Workplace Score " );
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = visualControlCheckServices.visualWPGraphExportExcel(commonFilter,tableModel,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "VisualScore", format);
			}

		} catch (ServiceObjectCreationException e) {
			e.printStackTrace();
			CommonMessage.debugMsg(e);
		}

		
	}
	
	
	private JSONObject getTableModelGraph(List<String[]> headers,String caption) {
		CommonMessage.debugMsg("Enter get col model...."+caption);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		
		String[] colHeader = headers.get(0);
		String[] colHeader1 = headers.get(1);
		colHeader[3] = "DATE";
		if(caption.equals("Factory")){
			caption="PBU";
		}else if(caption.equals("Section")){
			caption="DMT";
		}else if(caption.equals("Line")){
			caption="JH";
		}
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		CommonMessage.debugMsg("test..."+colHeader.length);
		for (int i = 2; i <colHeader.length; i++) {
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		colHeader1[3] = caption;  
		String headerSql = "'SELECT ";
		for (int i = 0; i <= colHeader.length-1; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i == 0 || i == 1 || i==2|| i==4) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			} else if (i > 4) {
				// jqGridColModel.setHidden(false);
				jqGridColModel.setKey(true);
				jqGridColModel.setAlign("right");
				jqGridColModel.setWidth(70);
			}

			else if (i == 16) {
				CommonMessage.debugMsg("Length of col:" + colHeader.length);
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
			
			//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL' ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		tableModel.set("tableHeight", "67%%");
		
		CommonMessage.debugMsg("colmodel:" + tableModel);
		return tableModel;
	}



	private CommonFilter populateCommonFilter1(HttpServletRequest request,
		 String beanIdentifier, boolean createNew) {
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


		
		
	private void deletevis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		CommonMessage.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("Keyid");
		CommonMessage.debugMsg("KEYID:::::::::"+keyid);
		JSONObject err = new JSONObject();
		try
		{
			if(UIUtils.isValidKeyId(keyid))
			{
				visualControlCheckServices.Deletevis(keyid);
				String msgPropertyIdnt = "success-delete";
				CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
			CommonMessage.debugMsg("Delete End");
		// TODO Auto-generated method stub
		
	}catch(Exception e){
		String fkCons = e.getMessage();
		CommonMessage.debugMsg("fkCons"+fkCons);
		if (fkCons.contains("FK_VCDT_VCCD_KEYID")) {
			err.put("deleteMsg","Reference Found Cannot Delete");
			out.print(err.toString());
		}
	}
	}

	private void deleteVisualmasdetControl(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	try{
    	SopTlVisualchecklistmst newSopTlVisualchecklistmst = new SopTlVisualchecklistmst();
        newSopTlVisualchecklistmst=(SopTlVisualchecklistmst)UIUtils.setBeanProperties((Object)newSopTlVisualchecklistmst,request);
    	newSopTlVisualchecklistmst = visualControlCheckServices.delete(newSopTlVisualchecklistmst);
    	JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",true);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
	}
	catch(Exception e)
	{
		CommonMessage.debugMsg("data");
		e.printStackTrace();
		JSONObject err = new JSONObject();
		CommonMessage.debugMsg("in Exception    "+e.getMessage());
		err.put("tpmException", "Data Not Deleted");
		if(UIUtils.isValidKeyId(e.getMessage())){
			CommonMessage.debugMsg("in Exception    "+e.getMessage());
			
			if(e.getMessage().contains("FK")){
				err.put("tpmException", "Reference Data Found ,Data Cannot Be Deleted");
				out.print(err.toString());
			}
		}
	}
}
	private void saveVisualConmasdet(HttpServletRequest request,
			HttpServletResponse response, VisualConBean visualBean) throws Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	try
		{ 
    	
    	if( httpSession != null && user != null)
    	{	
    		SopTlVisualchecklistmst existSopTlVisualchecklistmst= (SopTlVisualchecklistmst)httpSession.getAttribute("newSopTlVisualchecklistmst"); 
    	    SopTlVisualchecklistmst newSopTlVisualchecklistmst = new SopTlVisualchecklistmst();
    		SopTlVisualchecklistdtl newSopTlVisualchecklistdtl = new SopTlVisualchecklistdtl();
    		newSopTlVisualchecklistmst =(SopTlVisualchecklistmst)UIUtils.setBeanProperties((Object)newSopTlVisualchecklistmst,request);
    		CommonMessage.debugMsg("MASTER KEYID:::"+newSopTlVisualchecklistmst.getVccmKeyid());
		    String gridData =  request.getParameter("visDetails");
		   CommonMessage.debugMsg("gridData: "+gridData);
           List<SopTlVisualchecklistdtl> lstSopTlVisualchecklistdtl = null;
		   SopTlVisualchecklistdtl newGenTlNearmissreportdtl=new SopTlVisualchecklistdtl();
		  JSONArray jsonNearMissReport = null;
		
		  if(UIUtils.isValidKeyId(gridData)){CommonMessage.debugMsg("newresr: "+gridData);
			if( gridData != null && ! gridData.isEmpty())
    		{
				jsonNearMissReport = JSONArray.fromString(gridData);
				lstSopTlVisualchecklistdtl=(List<SopTlVisualchecklistdtl>)UIUtils.convertJSONArrToList(newGenTlNearmissreportdtl, jsonNearMissReport);
    		}
		   if(newSopTlVisualchecklistmst!=null)
		   {
				CommonMessage.debugMsg("size lstGenTlNearmissreportdtl..."+lstSopTlVisualchecklistdtl.size());
				newSopTlVisualchecklistmst.setVisualControlmstdet(lstSopTlVisualchecklistdtl);
				//CommonMessage.debugMsg("lstGenTlNearmissreportdtl.size(): "+lstSopTlVisualchecklistdtl.get(0).getVccdKeyid());
			}
		   
        }
		  
		if(newSopTlVisualchecklistmst.getVisualControlmstdet().size()>0) {
			CommonMessage.debugMsg("lstGenTlNearmissreportdtl.criteria: "+lstSopTlVisualchecklistdtl.get(0).getVccdCriteria());
			CommonMessage.debugMsg("lstGenTlNearmissreportdtl.checkpoint: "+lstSopTlVisualchecklistdtl.get(0).getVccdCheckpoints());
		}
		
		if(newSopTlVisualchecklistmst.getVisualControlmstdet().size()==0) {
			throw new BusinessApplicationExceptions("REQ_details,");
		}
		
		CommonMessage.debugMsg("beforesadsa  ");
				JSONObject successData=new JSONObject();
				JSONObject visualChkListSuccessMsg=new JSONObject();
				String savemsg;		
				newSopTlVisualchecklistmst.setVccmCreatedby(user.getUsrm_ccno());
				newSopTlVisualchecklistdtl.setVccdCreatedby(user.getUsrm_ccno());
				
				CommonMessage.debugMsg("sdsdssd: "+!UIUtils.isValidKeyId(newSopTlVisualchecklistmst.getVccmKeyid()));
					 if(!UIUtils.isValidKeyId(newSopTlVisualchecklistmst.getVccmKeyid()))
					 {
						 //CommonMessage.debugMsg("newSopTlVisualchecklistmst.getVccmKeyid "+newSopTlVisualchecklistmst.getVisualControlmstdet().get(0).getVccdCheckpoints());
						 existSopTlVisualchecklistmst=visualControlCheckServices.create(newSopTlVisualchecklistmst, existSopTlVisualchecklistmst);
						 savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save");
					 }
					 else{
						 CommonMessage.debugMsg("newSopTlVisualchecklistmst.getVcclKeyid():update " );
						 existSopTlVisualchecklistmst=visualControlCheckServices.update(newSopTlVisualchecklistmst, existSopTlVisualchecklistmst);
						 savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-update"); //"Data Updated Succesfully";
					 }
	        	       successData.put("msg", savemsg);
	        	       
	        	       CommonMessage.debugMsg("existSopTlVisualchecklistmst.getVccmKeyid()"+existSopTlVisualchecklistmst.getVccmKeyid());
	        	       successData.put("keyid", existSopTlVisualchecklistmst.getVccmKeyid());
	        	       
	        	      visualChkListSuccessMsg.put("successData", successData);
	        	     visualChkListSuccessMsg.put("formClear",false);
	    		     out.print(visualChkListSuccessMsg.toString());
	    		      
	    		     // JSONObject returnData = new JSONObject();//
				     // returnData.put("successData", successData);
				     // returnData.put("formClear", false);
				      //out.print(returnData.toString());//
    	}	      
	
		
		}catch (ValidationExceptions e)
				{
						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"VisualCheck");
		                 out.print(errMessage.toString());
	    	  }
	    	catch (BusinessApplicationExceptions e)
			{
	    		CommonMessage.debugMsg("Business - Error Msg:" + e.getMessage());
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"VisualCheck");
	                 out.print(errMessage.toString());
			}
			catch(Exception e){
					CommonMessage.debugMsg("Error Msg:" + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
			}
			
			
	}
 


	private void savevisual(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
               CommonMessage.debugMsg("nbj");
		
	  	CommonMessage.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null)
				{	
				      SopTlVisualchecklistmst newSopTlVisualchecklistmst=new SopTlVisualchecklistmst();//model
				      VisualConBean visualBean= new VisualConBean();
					  newSopTlVisualchecklistmst =(SopTlVisualchecklistmst)UIUtils.setBeanProperties((Object)newSopTlVisualchecklistmst,request);//master
                      SopTlVisualchecklistmst existSopTlVisualchecklistmst = (SopTlVisualchecklistmst)httpSession.getAttribute("newSopTlVisualchecklistmst");
				 	  String savemsg;
				      boolean insert = true;
						
			if( ! UIUtils.isValidKeyId (newSopTlVisualchecklistmst.getVccmKeyid() )  )//NOT NULL CRETTE
						{	
					   newSopTlVisualchecklistmst=visualControlCheckServices.create(newSopTlVisualchecklistmst ,existSopTlVisualchecklistmst,visualBean);
					   savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save");
						}
			else{
					insert = false;
				    existSopTlVisualchecklistmst=visualControlCheckServices.update(newSopTlVisualchecklistmst ,existSopTlVisualchecklistmst,visualBean);
				    savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-update");
			   }
				
                  JSONObject successData = new JSONObject();
			      successData.put("msg",savemsg);
			      JSONObject returnData = new JSONObject();//
			      returnData.put("successData", successData);
			      returnData.put("formClear", true);
			      out.print(returnData.toString());//
			      out.close();
				}
		}
		catch(Exception e){
			e.printStackTrace();
       }
}

   private JSONObject getTableModelVisual(List<String[]> headers) 
   {
        CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		String[] colHeader1= headers.get(0);
        jqGridTableModel.getRowHeaders().add(colHeader);
        jqGridTableModel.setRowNumbers(true);
        jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setRowHeight(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setLoadOnce(false);
		
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			CommonMessage.debugMsg("colHeader1[i]    "+colHeader1[i]);
			CommonMessage.debugMsg("colHeaderi]    "+colHeader[i]);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(50);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
		
			if (i == 4){
				jqGridColModel.setWidth(120);
				jqGridColModel.setAlign("left");
			}
			if (i ==5){
				jqGridColModel.setWidth(140);
				jqGridColModel.setAlign("left");
			}
			if (i == 3){
				jqGridColModel.setWidth(220);
				jqGridColModel.setAlign("left");
			}
			if (i == 2){
				jqGridColModel.setWidth(450);
				jqGridColModel.setAlign("left");
			}
			if ((i == 0) ||(i==1) || (i==6)){
				jqGridColModel.setHidden(true);
				
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	    }
   private JSONObject getTableModelReport(List<String[]> headers) {

		CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);

		
		jqGridTableModel.getRowHeaders().add(headers.get(1));

		jqGridTableModel.setRowNumbers(true);

		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setRowHeight(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		CommonMessage.debugMsg("No of Column:" + colHeader.length);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(50);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if (i >4){
				jqGridColModel.setWidth(52);
				jqGridColModel.setAlign("right");
			}
			if (i == 2){
				jqGridColModel.setWidth(368);
				jqGridColModel.setAlign("left");
			}
			if (i == 4){
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			if (i == 3){
				jqGridColModel.setWidth(86);
				jqGridColModel.setAlign("left");
			}
			if(i==9){
				jqGridColModel.setWidth(90);
				jqGridColModel.setAlign("right");
			}
			if ((i <= 1)|| (i==8)){
				jqGridColModel.setHidden(true);
				
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		  }

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "80%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	     }
	
	
	private JSONObject getTableModelcheckpoints(List<String[]> headers) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);

		
		jqGridTableModel.getRowHeaders().add(colHeader);

		jqGridTableModel.setRowNumbers(true);

		jqGridTableModel.setTableHeight(600);
		jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setRowHeight(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(50);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			if (i == 2){
				jqGridColModel.setWidth(400);
				jqGridColModel.setAlign("left");
			}
			if (i == 4){
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
			}
			if (i == 3){
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
			}
			if ((i <= 1 || i==6 )){
				jqGridColModel.setHidden(true);
				
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		  }

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	     }

	
	
	private JSONObject getTableModel(List<String[]> headers) {

		CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
        jqGridTableModel.getRowHeaders().add(colHeader);
        jqGridTableModel.setRowNumbers(true);   
		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setRowHeight(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(false);
	    //jqGridTableModel.setLoadOnce(true);//reload
		
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(50);
			jqGridColModel.setAlign("center");
			jqGridColModel.setEditable(false);
		
			if (i == 7){
				jqGridColModel.setWidth(180);
				jqGridColModel.setAlign("left");
			}
			else if (i == 6){
				/*jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");*/
				jqGridColModel.setHidden(true);
			}
			else if (i == 8 ){
				jqGridColModel.setWidth(400);
				jqGridColModel.setAlign("left");
			}else if(i==12){
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("center");
			}
			else if ((i == 0) ||(i==1)||(i==2)||(i==3)||(i==4)||(i==5)||(i==16) 
					|| i==(colHeader.length-3) || i==(colHeader.length-2)|| i==(colHeader.length-1)){
				jqGridColModel.setHidden(true);
				
			}
			else if(i==13 ) {
				jqGridColModel.setWidth(300);//|| i==13
				jqGridColModel.setHidden(true);
			}
			else if((i==14)){
				jqGridColModel.setName("Action Plan");
				jqGridColModel.setFormatter("Actionplan");
				jqGridColModel.setWidth(70);
				jqGridColModel.setHidden(true);
			}
			else if(i==15)
				jqGridColModel.setWidth(70);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
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
  			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
  			commonFilter.setViewClick('Y');
  			httpSession.removeAttribute(beanIdentifier);
  			httpSession.setAttribute(beanIdentifier, commonFilter);
  		}
  		String vcclDate = request.getParameter("vcclDate");
  		if (UIUtils.isValidDate(vcclDate))
  			commonFilter.setFromDate(vcclDate);
  		
  		String loginFlid = CommonFunctions.getLoginFlid(request);
		if (!UIUtils.isValidKeyId(commonFilter.getFlid()))
			commonFilter.setFlid(loginFlid);
		
  		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
  		return commonFilter;
	}
	 private void deleteVisualControl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ServletOutputStream out = response.getOutputStream();
	try {
			// TODO Auto-generated method stub
	    	HttpSession httpSession = request.getSession(false);
	    	
	    	GenTlVisualcontrolchecklist genTlVisualcontrolchecklist = (GenTlVisualcontrolchecklist)httpSession.getAttribute("newSession");
	    	GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist = new GenTlVisualcontrolchecklist ();
	    	newGenTlVisualcontrolchecklist=(GenTlVisualcontrolchecklist)UIUtils.setBeanProperties((Object)newGenTlVisualcontrolchecklist,request);
	    	genTlVisualcontrolchecklist = visualControlCheckServices.delete(newGenTlVisualcontrolchecklist);
	    	JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			JSONObject returnData = new JSONObject();
			returnData.put("formClear",true);
			returnData.put("successData", successData);				
			out.print(returnData.toString());
	
	 }catch (ValidationExceptions e)
		{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"VisualCheck");
              out.print(errMessage.toString());
	  }
	catch (BusinessApplicationExceptions e)
	{
		CommonMessage.debugMsg("Business - Error Msg:" + e.getMessage());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"VisualCheck");
          out.print(errMessage.toString());
	}
	catch(Exception e){
			CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
	}

	 }
	private void saveVisualControl(HttpServletRequest request,
			HttpServletResponse response, VisualControlCheckListBean visualControlCheckListBean) throws Exception
	   {
		String type=request.getParameter("Type");
		CommonMessage.debugMsg("oooooooo");
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	if( httpSession != null && user != null)
    	{	
    		
    		GenTlVisualcontrolchecklist existGenTlVisualcontrolchecklist = (GenTlVisualcontrolchecklist)httpSession.getAttribute("newGenTlVisualcontrolchecklist"); 
    		GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist = new GenTlVisualcontrolchecklist();
    		GenTlVisualcntchecklistdtl newGenTlVisualcntchecklistdtl = new GenTlVisualcntchecklistdtl();
    	     newGenTlVisualcontrolchecklist =(GenTlVisualcontrolchecklist)UIUtils.setBeanProperties((Object)newGenTlVisualcontrolchecklist,request);
    		CommonMessage.debugMsg("MASTER KEYID:::"+newGenTlVisualcontrolchecklist.getVcclKeyid());
    	    String gridData =  request.getParameter("visualCheckData");
    	    String rowId =  request.getParameter("rowId");
		    CommonMessage.debugMsg("gridData: "+gridData);
		    CommonMessage.debugMsg("rowId: "+rowId);
		    
		    List<GenTlVisualcntchecklistdtl> lstGenTlVisualcntchecklistdtls = null;
		    GenTlVisualcntchecklistdtl newGenTlNearmissreportdtl=new GenTlVisualcntchecklistdtl();
		    JSONArray jsonNearMissReport = null;
		    
		    newGenTlVisualcontrolchecklist.setVcclApprovedby(newGenTlVisualcontrolchecklist.getVcclEmployeeid());
		    
	if(UIUtils.isValidKeyId(gridData)){
			if( gridData != null && ! gridData.isEmpty())
    	{
	        jsonNearMissReport = JSONArray.fromString(gridData);
		    lstGenTlVisualcntchecklistdtls=(List<GenTlVisualcntchecklistdtl>)UIUtils.convertJSONArrToList(newGenTlNearmissreportdtl, jsonNearMissReport);
    	}
		if(newGenTlVisualcontrolchecklist!=null)
		{
			 CommonMessage.debugMsg("size lstGenTlNearmissreportdtl..."+lstGenTlVisualcntchecklistdtls.size());
			 newGenTlVisualcontrolchecklist.setVisualControlDetail(lstGenTlVisualcntchecklistdtls);
		for(int i=0;i<lstGenTlVisualcntchecklistdtls.size();i++){
				CommonMessage.debugMsg("lstGenTlNearmissreportdtl.size(): "+lstGenTlVisualcntchecklistdtls.get(i).getVcdtVccdKeyid());
				CommonMessage.debugMsg("key id"+	lstGenTlVisualcntchecklistdtls.get(i).getVcdtVccdKeyid());
				newGenTlNearmissreportdtl.setVcdtVccdKeyid(lstGenTlVisualcntchecklistdtls.get(i).getVcdtVccdKeyid());
				newGenTlNearmissreportdtl.setVcdtCriteriaval(lstGenTlVisualcntchecklistdtls.get(i).getVcdtCriteriaval());
				lstGenTlVisualcntchecklistdtls.get(i).setVcdtCreatedby(user.getUsrm_ccno());
			  }
		   }
		}
	
		
			JSONObject successData=new JSONObject();
			JSONObject returnData=new JSONObject();
			String savemsg;		
			newGenTlVisualcontrolchecklist.setVcclCreatedby(user.getUsrm_ccno());
			 //CommonMessage.debugMsg("11111");
			try
			{
				visualControlCheckListBean.setDtlKeyid(rowId);
				
				CommonMessage.debugMsg("visualControlCheckListBean.getDtlid=="+visualControlCheckListBean.getDtlKeyid());
				CommonMessage.debugMsg("MASTER KEYID:::"+newGenTlVisualcontrolchecklist.getVcclKeyid());
				 if(!UIUtils.isValidKeyId(newGenTlVisualcontrolchecklist.getVcclKeyid()))
				 {
					 //CommonMessage.debugMsg("11111");
				 CommonMessage.debugMsg("newGenTlVisualcontrolchecklist.getVcclTitle(): "+newGenTlVisualcontrolchecklist.getVcclTitle());
				 existGenTlVisualcontrolchecklist=visualControlCheckServices.create(newGenTlVisualcontrolchecklist, existGenTlVisualcontrolchecklist,visualControlCheckListBean);
				 savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save");
				 }
				 
				 else{
					 CommonMessage.debugMsg("newGenTlVisualcontrolchecklist.getVcclKeyid(): "+newGenTlVisualcontrolchecklist.getVcclKeyid());
					 existGenTlVisualcontrolchecklist=visualControlCheckServices.update(newGenTlVisualcontrolchecklist, existGenTlVisualcontrolchecklist,visualControlCheckListBean);
					 savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-update");
				 }
					/*
					 * for(int
					 * i=0;i<existGenTlVisualcontrolchecklist.getVisualControlDetail().size();i++){
					 * if ( !visualControlCheckListBean.getDtlKeyid().equals("null") &&
					 * Integer.parseInt( visualControlCheckListBean.getDtlKeyid()) == (i+1) ) {
					 * visualControlCheckListBean.setDtlKeyid(genTlVisualcntchecklistdtl.
					 * getVcdtKeyid()); setDtlKeyid=true; } }
					 */
				 GenTlVisualcntchecklistdtl genTlVisualcntchecklistdtl =existGenTlVisualcontrolchecklist.getVisualControlDetail().get(Integer.parseInt(rowId)-1);
				 successData.put("msg", savemsg);
		        	successData.put("vcclKeyid", existGenTlVisualcontrolchecklist.getVcclKeyid());
		        	successData.put("vcdtKeyid",existGenTlVisualcontrolchecklist.getVisualControlDetail().get(Integer.parseInt(rowId)-1).getVcdtKeyid());
		        	successData.put("type", type);
		        	returnData.put("successData",successData);
		        	CommonMessage.debugMsg("Type....."+type);
		        	returnData.put("formClear",false);
//			        	if(!UIUtils.isValidKeyId(type)){
//			        	returnData.put("formClear",true);
//			        	}
		    		out.println(returnData.toString());
    		
			} catch (BusinessApplicationExceptions e)
					{
			    		CommonMessage.debugMsg("Business - Error Msg:" + e.getMessage());
							JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"VisualCheck");
			                 out.print(errMessage.toString());
		         }catch (ValidationExceptions e) {
			        CommonMessage.debugMsg("ValidationExceptions");
			        net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(
					e.toString(), "VisualControlCheck");
		            out.print(errMessage.toString());
    	           }
			catch(Exception e){
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
		    }
		
			}
    	 }

	private void processChartVisualScoreGraph(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =null;
		
		String forDashboard = request.getParameter("dashboard");
		String graphType = request.getParameter("graphType");
		String flid = request.getParameter("flid");
		String type = request.getParameter("type");
		
		CommonMessage.debugMsg(" Inside Servlet :: "+type);
		
		CommonMessage.debugMsg(commonFilterScore+" forDashboard.....forDashboard "+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter)httpSession.getAttribute(commonFilterScore);
		}
		else{
			commonFilter = populateCommonFilter(request,commonFilterScore,false);
		}

		
		CommonMessage.debugMsg(commonFilter.getFlid()+" commonFilter.getFlid()  "+commonFilter.getFlid());
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		if (UIUtils.isValidKeyId(flid)) {
			chartCommonFilter.setFlid(flid);
			chartCommonFilter.setRowTotal('N');
		}
		else
			chartCommonFilter.setRowTotal('Y');
		String FirstLevel = request.getParameter("FirstLevel");
		if (UIUtils.isValidKeyId(FirstLevel)) 
			chartCommonFilter.setFirstLevel(FirstLevel);
		if (UIUtils.isValidKeyId(type)) {
			chartCommonFilter.setAbnViewType(type);
			commonFilter.setAbnViewType(type);
		}

		FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		commonFilter.setType(graphType);
		chartCommonFilter.setType(graphType);
		
		//commonFilter.setDrillFlag('f');
		List<String[]> jhVisualScoreList  = visualControlCheckServices.getVisualWPScoreGraph (chartCommonFilter);
		
		JSONObject chartObj = null;
		if(jhVisualScoreList != null && jhVisualScoreList.size() > 0)
		{	chartObj = processLineChartVisual(jhVisualScoreList,chartCommonFilter);
			UIUtils.dashBoardSetChartObject(request,chartObj);
			CommonMessage.debugMsg("chartObj ="+chartObj);
			PrintWriter out = response.getWriter();
			out.print(chartObj);
			out.close();
		}
	}

	private JSONObject processLineChartVisual(List<String[]> jhVisualScoreList,CommonFilter commonFilter){
		
		if( jhVisualScoreList == null || jhVisualScoreList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String title =  " Visual Workplace  Score ";
		
		int index=0;
		if(jhVisualScoreList!=null)
			index=jhVisualScoreList.size()-1;
	
		
		String[] header =  jhVisualScoreList.get(1);
		String[] month =  jhVisualScoreList.get(0);
		String[] data =  jhVisualScoreList.get(2);
		String prevMonth = null;
		
		int rSize = jhVisualScoreList.size()-2;
		int cSize = header.length - 5;
		
		String subTitle = data[3];
		//CommonMessage.debugMsg("subTitle "+subTitle );

		List<Double[]> graphData = new ArrayList<Double[]>();
		int rno=-1;
		Double [][] grData = new Double[rSize][cSize];
		for( int i = 5;i < header.length;i++ ){
			rno = rno +1;
			for (int j = 2; j< jhVisualScoreList.size();j++) {
				data  =  jhVisualScoreList.get(j);
				if(header[i].contains("SCORE") ){
					if (!UIUtils.isValidKeyId(data[i]))
						data[i] = "0";
					grData[j-2][i-5] = Double.parseDouble(data[i]);
				}
/*				else if(header[i].contains("COMPLETED") ){
					completedData.add(Double.parseDouble(data[i]));
				}
*/				
			//	prevMonth = month[i];
			}
		//	if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
		//	}
		}
	
		for(int k=0;k<grData.length ;k++) {
			 
			 ChartSeries timeSeries = new ChartSeries();
			 timeSeries.setData(Arrays.asList(grData[k]));
				
			 if (commonFilter.getType().equals("COLUMN"))
				timeSeries.setType(ChartTypes.COLUMN);
			 else
				timeSeries.setType(ChartTypes.SPLINE);
			
			 //timeSeries.setName("SCORE "+k);
			 timeSeries.setName(jhVisualScoreList.get(k+2)[4]);
			 
			
			 chartSeriesList.add(timeSeries);			
			//yAxis.getTitle().setText("Implemented");
			 ChartYAxis yAxis = new ChartYAxis(); 
			 yAxis.setMin(0);
			 yAxis.getTitle().setText("Total Score");
			 chartYAxis.add(yAxis);
		}	
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
		
	}

}
