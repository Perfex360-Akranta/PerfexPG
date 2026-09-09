package com.akranta.tpm.controller;
/*
 * Created By:Dhanalakshmi.R
 * Date:5-1-2013
 * */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.PcsTlLctcostperminute;
import com.akranta.tpm.service.PcsLossCostService;
import com.akranta.tpm.service.impl.PcsLossCostServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/**
 * Servlet implementation class PcsLossCostServlet
 */

public class PcsLossCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PcsLossCostService pcsLossCostService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PcsLossCostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				pcsLossCostService = (PcsLossCostServiceImpl)UIUtils.getServiceObject(request,"PcsLossCostServiceImpl");
				if(action.equals("PcsLossCostDD_input.lossCst")) 
				{
					request.setAttribute("DrillDown", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
				 	UIUtils.forwardRequest(request, response, "/pages/PcsLossCost.jsp"); 
				}
				else if(action.equals("PcsLossCostDD_getCol.lossCst"))
				{	
					PrintWriter out = response.getWriter();
					CommonFilter  commonFilter= new CommonFilter(); 
					String firstClick =request.getParameter("firstClick");	
					if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){			
					commonFilter = (CommonFilter) httpSession.getAttribute("pcsLossCostDrillcommonFilter");
					}						
					if(commonFilter==null)
					commonFilter = new CommonFilter(); 			
					FilterValues.getCommonFilters(request,commonFilter);				
					FilterValues.getPCS(request,commonFilter);
					httpSession.removeAttribute("pcsLossCostDrillcommonFilter");
					httpSession.setAttribute("pcsLossCostDrillcommonFilter",commonFilter);
					response.setContentType("text/html");
					
					List<String[]> DrillDnList = pcsLossCostService.getPcsLossCost(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(false)	;
					jqGridTableModel.setTableButton(true);
					gridColModel.setHeaderNum(1);
					jqGridTableModel.setGridEdit(true);
					String [] colHeader = DrillDnList.get(2);			
					String [] colHeaderCond = DrillDnList.get(1);
					
					List<String[]> headers = new ArrayList<String[]>();
					
					headers.add(colHeader);
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					//jsonObject.put("multiSelect", true);
					jsonObject.put("tableWidth", "106%%");
					jsonObject.put("tableHeight", "74%%");
					/*String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.LossCstCreation","colModelLossCst");
					JSONObject colmodel = JSONObject.fromString(tableModel);
					String deptName="";
					if("LOCN".equalsIgnoreCase(commonFilter.getDrillCaption()))
						deptName="Location";
					else if("FACT".equalsIgnoreCase(commonFilter.getDrillCaption()))
						deptName="SBU";	
					else if("SECT".equalsIgnoreCase(commonFilter.getDrillCaption()))
						deptName="PBU";	
					else if("CELL".equalsIgnoreCase(commonFilter.getDrillCaption()))
						deptName="JH";	
					else if("MCHM".equalsIgnoreCase(commonFilter.getDrillCaption()))
						deptName="Equipment";	
					
					colmodel.set("rowHeaders", "[[\"SNO\",\"KEYID\",\""+deptName+"\",\"costid\",\"Select\",\"setvalue\",\"Effective Date\",\"Cost Per Hour\"]]");
					colmodel.set("rowNumbers", true);*/
					httpSession.removeAttribute("PCSLossCostColData");
					httpSession.setAttribute("PCSLossCostColData", jsonObject);
					out.println(jsonObject);
				 }
				else if( action.equals("PcsLossCostDD_getData.lossCst") )
				{
					try
					{		
						PrintWriter out = response.getWriter();
						CommonFilter  commonFilter =populateCommonFilter(request,"pcsLossCostDrillcommonFilter",false);
						response.setContentType("text/html");		
						//commonFilter.setDrillLevel("CELL");
						List< String[]> DrillDnList  = pcsLossCostService.getPcsLossCost(commonFilter);
						JSONObject jhDrillDnData = UIUtils.convertToJqGridTableObject(DrillDnList,request,3,0,commonFilter.getTotalRecordCnt()+3);
						out.println(jhDrillDnData);	   
					}
					catch(Exception e)
					{}
				}
		 
			else if( action.equals("PcsLossCostDD_getExcel.lossCst"))
			{			
				CommonFilter commonFilter = populateCommonFilter(request,"pcsLossCostDrillcommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);				
				JSONObject tableModel = (JSONObject) httpSession.getAttribute("PCSLossCostColData");
				tableModel.put("title", "PCS Loss Cost Report - "+commonFilter.getDrillCaption()+" Level");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = pcsLossCostService.getPcsLossCostExportExcel(commonFilter,tableModel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "PCSLossCostReport", format);
			}
			else if(action.equals("PcsLossCostDD_save.lossCst"))
			{
				savePcsLossCst(request,response);	
			}
				
			else if(action.equals("filterXmlPcsLossCostDD_input.lossCst"))
			{
				UIUtils.forwardRequest(request, response, "tiles/xml/PcsLossCost.xml"); 
			}
		 }
		catch(ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		 }		

	}
	@SuppressWarnings("unchecked")
	private void savePcsLossCst(HttpServletRequest request,
			HttpServletResponse response)throws Exception,IOException {
		
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		UIUtils.displayRequestParamsValue(request);
		if (httpSession != null && user != null) 
		{
			try 
			{
				PcsTlLctcostperminute newPcsTlLctcostperminute = new PcsTlLctcostperminute();
				newPcsTlLctcostperminute = (PcsTlLctcostperminute) UIUtils.setBeanProperties((Object) newPcsTlLctcostperminute,request);
				String lossCstlist = request.getParameter("selectedrowIDs");
				CommonMessage.debugMsg(" lossCstlist111  "+lossCstlist);
				List<PcsTlLctcostperminute> lossCstList = null;   
				JSONArray lossCstJson = null;
				if (UIUtils.isValidKeyId(lossCstlist)) 
				{
					CommonMessage.debugMsg("lossCstlist.length():"+lossCstlist.length());
					lossCstJson = JSONArray.fromString(lossCstlist);
					lossCstList = (List<PcsTlLctcostperminute>) UIUtils.convertJSONArrToList(newPcsTlLctcostperminute,lossCstJson);	
					
					CommonMessage.debugMsg("newPcsTlLctcostperminute1000    "+lossCstList);
					//CommonMessage.debugMsg("newPcsTlLctcostperminute2000    "+newPcsTlLctcostperminute.getLcpmFromdate());
				}
				else
				{
					throw new BusinessApplicationExceptions("Select Effective Date & Cost Per Minute To save");
				}
				CommonMessage.debugMsg("user.getUsrm_ccno():"+user.getUsrm_ccno());
				CommonMessage.debugMsg("Statements");
				pcsLossCostService.create(lossCstList,user.getUsrm_ccno());
				JSONObject successData = new JSONObject();
				successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
				JSONObject result = new JSONObject();
				//result.put("formClear", false);
				result.put("displyMsg", true);
				result.put("successData", successData);
				out.print(result.toString());
				out.close();

			}
			catch(ValidationExceptions e)
			{
				CommonMessage.debugMsg("e.yguiyg " + e.getMessage());
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"PCSLossCost");
				out.print(errMessage.toString());
			}
			catch(BusinessApplicationExceptions b)
			{
				JSONObject result = new JSONObject();
				result.put("tpmException",b.getMessage());
				out.print(result.toString());
				out.close();

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
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
			commonFilter = 	FilterValues.getPCS(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		return commonFilter;
	}
	
	

}
