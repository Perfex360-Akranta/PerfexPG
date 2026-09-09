package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
//import com.akranta.tpm.bean.GenTlAchievementsBean;
//import com.akranta.tpm.bean.GenTlEquipmentfmeaBean;
//import com.akranta.tpm.bean.GenTlProcessfmeaBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
//import com.akranta.tpm.bean.RiskBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.PlmTlDesignfmeamstSql;
import com.akranta.tpm.dao.sql.PlmTlEquipmentfmeamstSql;
import com.akranta.tpm.dao.sql.PlmTlProcessfmeamstSql;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.GenTlAchievements;
//import com.akranta.tpm.model.GenTlDesignfmea;
import com.akranta.tpm.model.GenTlEquipmentfmea;
//import com.akranta.tpm.model.GenTlProcessfmea;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.PlmTlDesignfmeadtl;
import com.akranta.tpm.model.PlmTlDesignfmeamst;
import com.akranta.tpm.model.PlmTlEquipmentfmeadtl;
import com.akranta.tpm.model.PlmTlEquipmentfmeamst;
import com.akranta.tpm.model.PlmTlProcessfmeadtl;
import com.akranta.tpm.model.PlmTlProcessfmeamst;
//import com.akranta.tpm.model.SheTlRiskassessmentdtl;
//import com.akranta.tpm.model.SheTlRiskassessmentmst;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.FMEAFormatService;
import com.akranta.tpm.service.MonthlyPlanService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.FMEAFormatServiceImpl;
import com.akranta.tpm.service.impl.MonPlanServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
//import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

/**
 * Servlet implementation class FEMAFormatServlet
 */

public class FMEAFormatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	CommonFilterService commonFilterService ;
	FMEAFormatService fmeaFormatService;
	MonthlyPlanService monthlyPlanService;
    public FMEAFormatServlet() {
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
	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
		commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
		fmeaFormatService = (FMEAFormatServiceImpl) UIUtils.getServiceObject(
				request, "FMEAFormatServiceImpl");
		
		monthlyPlanService = (MonPlanServiceImpl)UIUtils.getServiceObject(request,"MonPlanServiceImpl");
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg(" action " + action);
		//CommonFilter commonFilter = new CommonFilter();
		// String dispatchUrl = null;
		HttpSession httpSession = request.getSession(false);
		fmeaFormatService.FMEAFormatServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		if (action.equals("FMEA_input.fmeaf")) {
			String type = request.getParameter("type");
			String fmeaDocType = request.getParameter("fmeaDocType");
			String fmeaDocmstid = request.getParameter("fmeaDocmstid");
			String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");
			request.setAttribute("fmeaType", type);
			request.setAttribute("fmeaDocType", fmeaDocType);
			request.setAttribute("fmeaDocmstid", fmeaDocmstid);
			request.setAttribute("fmeaDocdtlsid", fmeaDocdtlsid);
			
			String processId = request.getParameter("processId");
			String subProcessid = request.getParameter("subProcessid");
			if (UIUtils.isValidKeyId(processId))
				request.setAttribute("processId", processId);
			if (UIUtils.isValidKeyId(subProcessid))
				request.setAttribute("subProcessid", subProcessid);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/FMEA/FMEAList.jsp");
			rd.forward(request, response);
			
			CommonMessage.debugMsg(" response " + response);
		}
		else if (action.equals("FMEAEntry_input.fmeaf")) {
			loadEntry(request, response);
		}
		else if( action.equals("systemCombo.fmeaf"))
		{
			try{
				ComboFilter probFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = fmeaFormatService.getSystemComboList(probFilterComboFilter,"");
				UIUtils.writeComboBox(response, probfilcomboList ,probFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}else if(action.equals("functionalLocequp.fmeaf"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSectionid");
			functLocFieldNameBean.setCell("cmbCellid");
			functLocFieldNameBean.setMachine("cmbMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbCdapFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
			FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		} 
		else if(action.equals("functionalLocprocess.fmeaf"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSectionid");
			functLocFieldNameBean.setCell("cmbCellid");
			functLocFieldNameBean.setMachine("cmbMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbCdapFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		}
		else if( action.equals("subSystemCombo.fmeaf"))
		{
			try{
				ComboFilter probFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = fmeaFormatService.getSubSystemComboList(probFilterComboFilter,"");
				UIUtils.writeComboBox(response, probfilcomboList ,probFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		else if( action.equals("subEqpmnCombo.fmeaf"))
		{
			try{
				String flid=request.getParameter("flid");
				String cell =request.getParameter("cellid");
				CommonMessage.debugMsg(" flid :: "+flid);
				ComboFilter probFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = fmeaFormatService.getSubEqupmentComboList(probFilterComboFilter,flid,cell);
				UIUtils.writeComboBox(response, probfilcomboList ,probFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
	 
		
		else if( action.equals("occuranceCombo.fmeaf"))
		{
			try{
				ComboFilter probFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = fmeaFormatService.getOccuranceComboList(probFilterComboFilter,"");
				UIUtils.writeComboBox(response, probfilcomboList ,probFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if( action.equals("detectionCombo.fmeaf"))
		{
			try{
				ComboFilter probFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = fmeaFormatService.getDetectionComboList(probFilterComboFilter,"");
				UIUtils.writeComboBox(response, probfilcomboList ,probFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if( action.equals("severityCombo.fmeaf"))
		{
			try{
				ComboFilter probFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = fmeaFormatService.getSeverityComboList(probFilterComboFilter,"");
				UIUtils.writeComboBox(response, probfilcomboList ,probFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if( action.equals("componentCombo.fmeaf"))
		{
			try{
				ComboFilter probFilterComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = fmeaFormatService.getComponentComboList(probFilterComboFilter,"");
				UIUtils.writeComboBox(response, probfilcomboList ,probFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if( action.equals("FMEA_getCol.fmeaf") )
		{			
			getColFmeaMstList(request, response, httpSession);	 
		}				
		else if( action.equals("FMEA_getData.fmeaf") )
		{
			getDataFmeaMstList(request, response, httpSession);
		}
		else if(action.equals("FMEA_getExcel.fmeaf"))
		{
			getExcelFmeaMstList(request, response, httpSession);				
		}
		else if(action.equals("FMEA_viewExcel.fmeaf"))
		{
			getExcelFmea(request, response, httpSession);				
		}
		else if( action.equals("FMEAEntry_getCol.fmeaf") )
		{			
			getColFmeaDtlsList(request, response, httpSession);	 
		}				
		else if( action.equals("FMEAEntry_getData.fmeaf") )
		{
			getDataFmeaDtlsList(request, response, httpSession);
		}
		else if( action.equals("FMEAEntry_save.fmeaf") )
		{			
			saveFMEA(request,response);
		}
		else if( action.equals("FMEAEntry_delete.fmeaf") )
		{			
			//deleteFMEA(request,response);
			CommonMessage.debugMsg("deleteDtlsFMEA");
			deleteDtlsFMEA(request,response);
		}
		else if( action.equals("FMEAEntryDtls_delete.fmeaf") )
		{			
			deleteDtlsFMEA(request,response);
		}
		else if( action.equals("FMEARe_getCol.fmeaf") )
		{			
			getColFmeaReviewList(request, response, httpSession);	 
		}				
		else if( action.equals("FMEARe_getData.fmeaf") )
		{
			getDataFmeaReviewList(request, response, httpSession);
		}	
		else if(action.equals("getTotalVal.fmeaf")){
			getTotalVal(request, response, httpSession);
		}
		else if(action.equals("FMEAEntryFLID_input.fmeaf"))
		{
			String cellid=request.getParameter("cellid");
			String rowid=request.getParameter("rowid");
			String flid=fmeaFormatService.getflid(cellid);
			JSONObject successData = new JSONObject();
			successData.put("flid",flid);
			successData.put("rowid",rowid);
			JSONObject returnData = new JSONObject();//
			returnData.put("successData", successData);
			response.getWriter().println(returnData.toString());
			response.getWriter().close();
		}else if(action.equals("FMEAEntry_getEquipFmea.fmeaf"))
		{
		    String equipId = request.getParameter("equipId");
		    String flid    = request.getParameter("flid");

		    JSONObject successData = new JSONObject();
		    JSONObject returnData  = new JSONObject();

		    try {
		        List<String[]> dataList =
		            fmeaFormatService.getExistingFmeaByEquip(equipId, flid);

		        if(dataList != null && dataList.size() > 0){
		            String[] row = dataList.get(0);
		            // row[0]=FMEQ_KEYID, row[1]=FMEQ_NO, row[2]=FMEQ_DATE,
		            // row[3]=FMEQ_PREPAREDBY, row[4]=FMEQ_CORETEAM, row[5]=FMEQ_SUPEQUIPID
		            successData.put("keyid",      row[0] != null ? row[0] : "");
		            successData.put("fmeaNo",     row[1] != null ? row[1] : "");
		            successData.put("fmeaDate",   row[2] != null ? UIUtils.getActualDateForm(row[2]) : "");
		            successData.put("preparedBy", row[3] != null ? row[3] : "");
		            successData.put("coreTeam",   row[4] != null ? row[4] : "");
		            String supEquip = row[5] != null ? row[5] : "";
		            successData.put("supEquipid", supEquip.equals("{}") ? "" : supEquip);
		        } else {
		            successData.put("keyid",      "");
		            successData.put("fmeaNo",     "");
		            successData.put("fmeaDate",   "");
		            successData.put("preparedBy", "");
		            successData.put("coreTeam",   "");
		            successData.put("supEquipid", "");
		        }
		    } catch(Exception e){
		        e.printStackTrace();
		        successData.put("keyid",      "");
		        successData.put("fmeaNo",     "");
		        successData.put("fmeaDate",   "");
		        successData.put("preparedBy", "");
		        successData.put("coreTeam",   "");
		        successData.put("supEquipid", "");
		    }

		    returnData.put("successData", successData);
		    response.getWriter().println(returnData.toString());
		    response.getWriter().close();
		}
		
		
	}

	private void getTotalVal(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) throws  Exception {
		PrintWriter out = response.getWriter();
		String severity = request.getParameter("severity");
		String occurance = request.getParameter("occurance");
		String detection = request.getParameter("detection");
		String rowId = request.getParameter("rowId");
		List<String []> totalVal  = fmeaFormatService.getTotalVal(severity,occurance,detection);
		CommonMessage.debugMsg("totalVal.size() "+totalVal.size());
		if(!(totalVal.size()>0))
		{
			String [] rowVal=new String[1];
			rowVal[0]="0";
			totalVal.add(rowVal);
		}
		String [] rowData=new String[1];
		rowData[0]=rowId;
		totalVal.add(rowData);
		CommonMessage.debugMsg(totalVal.get(1)[0]+" totalVal.get(1)[0] "+totalVal.get(0)[0]);
		out.print( JSONArray.fromCollection(totalVal));	
		
	}

	private void saveFMEA(HttpServletRequest request,HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception 
    {
    	
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null){
				String type = request.getParameter("type");
				String fmeadetails = request.getParameter("fmeadtls");
				String fmeadtlsreview = request.getParameter("fmeadtlsreview");
				
				CommonMessage.debugMsg(" type = " + type);
				CommonMessage.debugMsg(" fmeadetails = " + fmeadetails);
				CommonMessage.debugMsg(" fmeadtlsreview = " + fmeadtlsreview);
				
				String actPlan = request.getParameter("actplan");
				String rowId = request.getParameter("rowid");
				CommonMessage.debugMsg("actPlan"+actPlan);
				CommonMessage.debugMsg("rowId"+rowId);
				String savemsg="";		
				boolean insert = true;	
				String keyId="";
				String dtlKeyId="";
				String activity="";
				if(UIUtils.isValidKeyId(type) && (UIUtils.isValidKeyId(fmeadetails)||UIUtils.isValidKeyId(fmeadtlsreview))){					
					if(type.equals("design")){
						PlmTlDesignfmeamst  newPlmTlDesignfmeamst =new 	PlmTlDesignfmeamst();
						PlmTlDesignfmeadtl  newPlmTlDesignfmeadtl =new PlmTlDesignfmeadtl();
						newPlmTlDesignfmeamst =(PlmTlDesignfmeamst)UIUtils.setBeanProperties((Object)newPlmTlDesignfmeamst,request);//master							 	
						PlmTlDesignfmeamst existPlmTlDesignfmeamst = (PlmTlDesignfmeamst)httpSession.getAttribute("newPlmTlDesignfmeamst");
						newPlmTlDesignfmeamst.setFmdmCreatedby(user.getUsrm_ccno());
						List<PlmTlDesignfmeadtl> plmTlDesignfmeadtlList = new ArrayList<PlmTlDesignfmeadtl>();
						if(UIUtils.isValidKeyId(fmeadetails)){
				    		JSONArray plmTlDesignfmeadtljson = null;	
				    		plmTlDesignfmeadtljson = JSONArray.fromString(fmeadetails);
				    		plmTlDesignfmeadtlList=(List<PlmTlDesignfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlDesignfmeadtl, plmTlDesignfmeadtljson);
						    if(plmTlDesignfmeadtlList!= null)
						    {
						        for(int i=0 ;i<=plmTlDesignfmeadtlList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        }
						    }
						}
					    List<PlmTlDesignfmeadtl> plmTlDesignfmeadtlReList = null;
					    if(UIUtils.isValidKeyId(fmeadtlsreview)){
					    	JSONArray plmTlDesignfmeadtlRejson = null;	
					    	plmTlDesignfmeadtlRejson = JSONArray.fromString(fmeadtlsreview);
				    		plmTlDesignfmeadtlReList=(List<PlmTlDesignfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlDesignfmeadtl, plmTlDesignfmeadtlRejson);
						    if(plmTlDesignfmeadtlReList!= null)
						    {						    	
						        for(int i=0 ;i<=plmTlDesignfmeadtlReList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        	PlmTlDesignfmeadtl  plmTlDesignfmeadtl =plmTlDesignfmeadtlReList.get(i);
						        	plmTlDesignfmeadtlList.add(plmTlDesignfmeadtl);
						        }
						    }
					    }
					    CommonMessage.debugMsg("newPlmTlDesignfmeamst.getFmdmKeyid():"+newPlmTlDesignfmeamst.getFmdmKeyid());
					    newPlmTlDesignfmeamst.setPlmTlDesignfmeadtl(plmTlDesignfmeadtlList);
						if( ! UIUtils.isValidKeyId (newPlmTlDesignfmeamst.getFmdmKeyid())){	
							existPlmTlDesignfmeamst =fmeaFormatService.create(newPlmTlDesignfmeamst,existPlmTlDesignfmeamst);				
							savemsg="Data Saved Successfully";
						}
						else{
							insert = false;
							CommonMessage.debugMsg("update");
							existPlmTlDesignfmeamst =fmeaFormatService.update(newPlmTlDesignfmeamst,existPlmTlDesignfmeamst);				
							savemsg="Data Updated Successfully";					
						}
						keyId=newPlmTlDesignfmeamst.getFmdmKeyid();
						if(UIUtils.isValidKeyId(actPlan)){
							dtlKeyId=newPlmTlDesignfmeamst.getplmTlDesignfmeadtl().get(0).getFmddKeyid();
							activity=newPlmTlDesignfmeamst.getplmTlDesignfmeadtl().get(0).getFmddPotentialfailmode();
						}
					}
					else if(type.equals("equipment")){
						PlmTlEquipmentfmeamst  newPlmTlEquipmentfmeamst =new PlmTlEquipmentfmeamst();
						PlmTlEquipmentfmeadtl  newPlmTlEquipmentfmeadtl =new PlmTlEquipmentfmeadtl();
						newPlmTlEquipmentfmeamst =(PlmTlEquipmentfmeamst)UIUtils.setBeanProperties((Object)newPlmTlEquipmentfmeamst,request);//master							 	
						PlmTlEquipmentfmeamst existPlmTlEquipmentfmeamst = (PlmTlEquipmentfmeamst)httpSession.getAttribute("newPlmTlEquipmentfmeamst");
						newPlmTlEquipmentfmeamst.setFmeqCreatedby(user.getUsrm_ccno());
						List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtlList = null;
			    		JSONArray plmTlEquipmentfmeadtlJson = null;	
			    		if(UIUtils.isValidKeyId(fmeadetails)){
				    		plmTlEquipmentfmeadtlJson = JSONArray.fromString(fmeadetails);
				    		plmTlEquipmentfmeadtlList=(List<PlmTlEquipmentfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlEquipmentfmeadtl, plmTlEquipmentfmeadtlJson);
						    if(plmTlEquipmentfmeadtlList!= null)
						    {
						        for(int i=0 ;i<=plmTlEquipmentfmeadtlList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        }
						        
						    }
			    		}
					    if(UIUtils.isValidKeyId(fmeadtlsreview)){
						    List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtlReList = null;
				    		JSONArray plmTlEquipmentfmeadtlRejson = null;	
				    		plmTlEquipmentfmeadtlRejson = JSONArray.fromString(fmeadtlsreview);
				    		plmTlEquipmentfmeadtlReList=(List<PlmTlEquipmentfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlEquipmentfmeadtl, plmTlEquipmentfmeadtlRejson);
						    if(plmTlEquipmentfmeadtlReList!= null)
						    {
						    	if(plmTlEquipmentfmeadtlList==null){
						    		plmTlEquipmentfmeadtlList=new ArrayList<PlmTlEquipmentfmeadtl>();
						    	}
						        for(int i=0 ;i<=plmTlEquipmentfmeadtlReList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        	PlmTlEquipmentfmeadtl  plmTlEquipmentfmeadtl =plmTlEquipmentfmeadtlReList.get(i);
						        	//plmTlEquipmentfmeadtl.setFmedReviewby(user.getUsrm_ccno());
						        	//plmTlEquipmentfmeadtl.setFmedRedate();
						        	plmTlEquipmentfmeadtlList.add(plmTlEquipmentfmeadtl);
						        }
						    }
					    }
					    newPlmTlEquipmentfmeamst.setPlmTlEquipmentfmeadtl(plmTlEquipmentfmeadtlList);
						if( ! UIUtils.isValidKeyId (newPlmTlEquipmentfmeamst.getFmeqKeyid())){	
							existPlmTlEquipmentfmeamst =fmeaFormatService.create(newPlmTlEquipmentfmeamst,existPlmTlEquipmentfmeamst);				
							savemsg="Data Saved Successfully";
						}
						else{
							insert = false;
							CommonMessage.debugMsg("update");
							existPlmTlEquipmentfmeamst =fmeaFormatService.update(newPlmTlEquipmentfmeamst,existPlmTlEquipmentfmeamst);	
							CommonMessage.debugMsg("eup Nmber "+existPlmTlEquipmentfmeamst.getFmeqNo());
							savemsg="Data Updated Successfully";					
						}
//						keyId=newPlmTlEquipmentfmeamst.getFmeqKeyid();
						keyId=existPlmTlEquipmentfmeamst.getFmeqKeyid();
						if(UIUtils.isValidKeyId(actPlan)){
							dtlKeyId=newPlmTlEquipmentfmeamst.getplmTlEquipmentfmeadtl().get(0).getFmedKeyid();
							activity=newPlmTlEquipmentfmeamst.getplmTlEquipmentfmeadtl().get(0).getFmedPotentialfailmode();
						}
					}
					else if(type.equals("process")){
						PlmTlProcessfmeamst  newPlmTlProcessfmeamst =new 	PlmTlProcessfmeamst();
						PlmTlProcessfmeadtl  newPlmTlProcessfmeadtl =new PlmTlProcessfmeadtl();
						newPlmTlProcessfmeamst =(PlmTlProcessfmeamst)UIUtils.setBeanProperties((Object)newPlmTlProcessfmeamst,request);//master							 	
						PlmTlProcessfmeamst existPlmTlProcessfmeamst = (PlmTlProcessfmeamst)httpSession.getAttribute("newPlmTlProcessfmeamst");
						CommonMessage.debugMsg("getFmpmKeyid:"+newPlmTlProcessfmeamst.getFmpmKeyid());
						newPlmTlProcessfmeamst.setFmpmCreatedby(user.getUsrm_ccno());
						List<PlmTlProcessfmeadtl> plmTlProcessfmeadtlList = null;
			    		JSONArray plmTlProcessfmeadtlJson = null;	
			    		if(UIUtils.isValidKeyId(fmeadetails)){
				    		plmTlProcessfmeadtlJson = JSONArray.fromString(fmeadetails);
				    		plmTlProcessfmeadtlList=(List<PlmTlProcessfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlProcessfmeadtl, plmTlProcessfmeadtlJson);
						    if(plmTlProcessfmeadtlList!= null)
						    {
						        for(int i=0 ;i<=plmTlProcessfmeadtlList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        }
						    }
			    		}
					    List<PlmTlProcessfmeadtl> plmTlProcessfmeadtlReList = null;
			    		JSONArray plmTlProcessfmeadtlRejson = null;	
			    		if(UIUtils.isValidKeyId(fmeadtlsreview)){
				    		plmTlProcessfmeadtlRejson = JSONArray.fromString(fmeadtlsreview);
				    		plmTlProcessfmeadtlReList=(List<PlmTlProcessfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlProcessfmeadtl, plmTlProcessfmeadtlRejson);
						    if(plmTlProcessfmeadtlReList!= null)
						    {
						    	if(plmTlProcessfmeadtlList==null){
						    		plmTlProcessfmeadtlList=new ArrayList<PlmTlProcessfmeadtl>();
						    	}
						    	
						        for(int i=0 ;i<=plmTlProcessfmeadtlReList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        	PlmTlProcessfmeadtl  plmTlProcessfmeadtl =plmTlProcessfmeadtlReList.get(i);
						        	CommonMessage.debugMsg("getFmpdDetectionKeyid:"+plmTlProcessfmeadtl.getFmpdRedetectionKeyid());
						        	CommonMessage.debugMsg("getFmpdOccurrenceKeyid:"+plmTlProcessfmeadtl.getFmpdReoccurrenceKeyid());
						        	CommonMessage.debugMsg("getFmpdSeverityKeyid:"+plmTlProcessfmeadtl.getFmpdReseverityKeyid());
						        	//plmTlProcessfmeadtl.setFmpdReviewby(user.getUsrm_ccno());
						        	//plmTlEquipmentfmeadtl.setFmedRedate();
						        	plmTlProcessfmeadtlList.add(plmTlProcessfmeadtl);
						        }
						    }
			    		}
					    newPlmTlProcessfmeamst.setPlmTlProcessfmeadtl(plmTlProcessfmeadtlList);
						if( ! UIUtils.isValidKeyId (newPlmTlProcessfmeamst.getFmpmKeyid())){	
							existPlmTlProcessfmeamst =fmeaFormatService.create(newPlmTlProcessfmeamst,existPlmTlProcessfmeamst);	
							CommonMessage.debugMsg("Nmber "+existPlmTlProcessfmeamst.getFmpmNo());
							savemsg="Data Saved Successfully";
						}
						else{
							insert = false;
							CommonMessage.debugMsg("update");
							existPlmTlProcessfmeamst =fmeaFormatService.update(newPlmTlProcessfmeamst,existPlmTlProcessfmeamst);				
							savemsg="Data Updated Successfully";					
						}
						//keyId=newPlmTlProcessfmeamst.getFmpmKeyid();
						keyId=existPlmTlProcessfmeamst.getFmpmKeyid();
						if(UIUtils.isValidKeyId(actPlan)){
							dtlKeyId=newPlmTlProcessfmeamst.getplmTlProcessfmeadtl().get(0).getFmpdKeyid();
							activity=newPlmTlProcessfmeamst.getplmTlProcessfmeadtl().get(0).getFmpdPotentialfailmode();
						}
					}
					if(UIUtils.isValidKeyId(savemsg)){
						JSONObject successData = new JSONObject();
						successData.put("msg",savemsg);
						JSONObject returnData = new JSONObject();//
						returnData.put("successData", successData);
						successData.put("keyId", keyId);
						if(UIUtils.isValidKeyId(actPlan)){
							CommonMessage.debugMsg(" Inside servlet If");
							successData.put("dtlkeyid", dtlKeyId);	
							successData.put("rowid", rowId);
							successData.put("activity", activity);
							CommonMessage.debugMsg(" Inside servlet ");
							successData.put("actplan", true);
						}else{
							CommonMessage.debugMsg(" Inside Flid servlet Else");
							successData.put("actplan", false);	
						}						
						returnData.put("formClear", false);
						out.print(returnData.toString());//
						out.close();
					}
				  }
				}
			}catch (ValidationExceptions e) 
			{
				CommonMessage.debugMsg("ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "FMEAValidation");
				out.print(errMessage.toString());							    	
			}
								
			catch(BusinessApplicationExceptions e)
			{
				e.printStackTrace();
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "FMEAValidation");
				out.print(errMessage.toString());
				CommonMessage.debugMsg(" e " + errMessage );
			}
			catch(Exception e)
			{   
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
			
    }
	private void deleteFMEA(HttpServletRequest request,HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception 
    {
    	
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null){
				String type = request.getParameter("type");
				String rowId = request.getParameter("rowid");
				CommonMessage.debugMsg("rowId"+rowId);
				CommonMessage.debugMsg("type"+type);
				String msg="";		
				if(UIUtils.isValidKeyId(type)){					
					if(type.equals("design")){
						PlmTlDesignfmeamst  newPlmTlDesignfmeamst =new 	PlmTlDesignfmeamst();
						PlmTlDesignfmeamst  existPlmTlDesignfmeamst =new 	PlmTlDesignfmeamst();
						newPlmTlDesignfmeamst =(PlmTlDesignfmeamst)UIUtils.setBeanProperties((Object)newPlmTlDesignfmeamst,request);//master
						CommonMessage.debugMsg("newPlmTlDesignfmeamst.getFmdmKeyid():"+newPlmTlDesignfmeamst.getFmdmKeyid());
						if(  UIUtils.isValidKeyId (newPlmTlDesignfmeamst.getFmdmKeyid())){	
							existPlmTlDesignfmeamst =fmeaFormatService.delete(newPlmTlDesignfmeamst);				
							msg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete");				
						}
					}
					else if(type.equals("equipment")){
						PlmTlEquipmentfmeamst  newPlmTlEquipmentfmeamst =new PlmTlEquipmentfmeamst();
						newPlmTlEquipmentfmeamst =(PlmTlEquipmentfmeamst)UIUtils.setBeanProperties((Object)newPlmTlEquipmentfmeamst,request);//master							 	
						PlmTlEquipmentfmeamst existPlmTlEquipmentfmeamst = (PlmTlEquipmentfmeamst)httpSession.getAttribute("newPlmTlEquipmentfmeamst");
						if( UIUtils.isValidKeyId (newPlmTlEquipmentfmeamst.getFmeqKeyid())){
							existPlmTlEquipmentfmeamst =fmeaFormatService.delete(newPlmTlEquipmentfmeamst);				
							msg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete");					
						}
					}
					else if(type.equals("process")){
						PlmTlProcessfmeamst  newPlmTlProcessfmeamst =new 	PlmTlProcessfmeamst();
						newPlmTlProcessfmeamst =(PlmTlProcessfmeamst)UIUtils.setBeanProperties((Object)newPlmTlProcessfmeamst,request);//master							 	
						PlmTlProcessfmeamst existPlmTlProcessfmeamst = (PlmTlProcessfmeamst)httpSession.getAttribute("newPlmTlProcessfmeamst");						
						if(  UIUtils.isValidKeyId (newPlmTlProcessfmeamst.getFmpmKeyid())){	
							existPlmTlProcessfmeamst =fmeaFormatService.delete(newPlmTlProcessfmeamst);				
							msg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete");				
						}
					}
					if(UIUtils.isValidKeyId(msg)){
						JSONObject successData = new JSONObject();
						successData.put("msg",msg);
						JSONObject returnData = new JSONObject();//
						returnData.put("successData", successData);
						returnData.put("formClear", false);
						out.print(returnData.toString());//
						out.close();
					}
				  }
				}
			}catch (ValidationExceptions e) 
			{
				CommonMessage.debugMsg("ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "FMEAValidation");
				out.print(errMessage.toString());							    	
			}
								
			catch(BusinessApplicationExceptions e)
			{
				e.printStackTrace();
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "FMEAValidation");
				out.print(errMessage.toString());
				CommonMessage.debugMsg(" e " + errMessage );
			}
			catch(Exception e)
			{   
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
			
    }
	private void deleteDtlsFMEA(HttpServletRequest request,HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception 
    {
    	
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null){
				String type = request.getParameter("type");
				String fmeadetails = request.getParameter("fmeadtls");
				String fmeadtlsreview = request.getParameter("fmeadtlsreview");
				String rowId = request.getParameter("rowid");
				CommonMessage.debugMsg("rowId"+rowId);
				String msg="";		
				if(UIUtils.isValidKeyId(type) && (UIUtils.isValidKeyId(fmeadetails)||UIUtils.isValidKeyId(fmeadtlsreview))){					
					if(type.equals("design")){
						PlmTlDesignfmeamst  newPlmTlDesignfmeamst =new 	PlmTlDesignfmeamst();
						PlmTlDesignfmeadtl  newPlmTlDesignfmeadtl =new PlmTlDesignfmeadtl();
						newPlmTlDesignfmeamst =(PlmTlDesignfmeamst)UIUtils.setBeanProperties((Object)newPlmTlDesignfmeamst,request);//master							 	
						PlmTlDesignfmeamst existPlmTlDesignfmeamst = (PlmTlDesignfmeamst)httpSession.getAttribute("newPlmTlDesignfmeamst");
						newPlmTlDesignfmeamst.setFmdmCreatedby(user.getUsrm_ccno());
						List<PlmTlDesignfmeadtl> plmTlDesignfmeadtlList = new ArrayList<PlmTlDesignfmeadtl>();
						if(UIUtils.isValidKeyId(fmeadetails)){
				    		JSONArray plmTlDesignfmeadtljson = null;	
				    		plmTlDesignfmeadtljson = JSONArray.fromString(fmeadetails);
				    		plmTlDesignfmeadtlList=(List<PlmTlDesignfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlDesignfmeadtl, plmTlDesignfmeadtljson);
						    if(plmTlDesignfmeadtlList!= null)
						    {
						        for(int i=0 ;i<=plmTlDesignfmeadtlList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        }
						    }
						}
					    List<PlmTlDesignfmeadtl> plmTlDesignfmeadtlReList = null;
					    if(UIUtils.isValidKeyId(fmeadtlsreview)){
					    	JSONArray plmTlDesignfmeadtlRejson = null;	
					    	plmTlDesignfmeadtlRejson = JSONArray.fromString(fmeadtlsreview);
				    		plmTlDesignfmeadtlReList=(List<PlmTlDesignfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlDesignfmeadtl, plmTlDesignfmeadtlRejson);
						    if(plmTlDesignfmeadtlReList!= null)
						    {						    	
						        for(int i=0 ;i<=plmTlDesignfmeadtlReList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        	PlmTlDesignfmeadtl  plmTlDesignfmeadtl =plmTlDesignfmeadtlReList.get(i);
						        	plmTlDesignfmeadtlList.add(plmTlDesignfmeadtl);
						        }
						    }
					    }
					    CommonMessage.debugMsg("newPlmTlDesignfmeamst.getFmdmKeyid():"+newPlmTlDesignfmeamst.getFmdmKeyid());
					    newPlmTlDesignfmeamst.setPlmTlDesignfmeadtl(plmTlDesignfmeadtlList);
						if(  UIUtils.isValidKeyId (newPlmTlDesignfmeamst.getFmdmKeyid())){	
							existPlmTlDesignfmeamst =fmeaFormatService.deleteDtls(newPlmTlDesignfmeamst);				
							msg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete");				
						}
					}
					else if(type.equals("equipment")){
						PlmTlEquipmentfmeamst  newPlmTlEquipmentfmeamst =new PlmTlEquipmentfmeamst();
						PlmTlEquipmentfmeadtl  newPlmTlEquipmentfmeadtl =new PlmTlEquipmentfmeadtl();
						newPlmTlEquipmentfmeamst =(PlmTlEquipmentfmeamst)UIUtils.setBeanProperties((Object)newPlmTlEquipmentfmeamst,request);//master							 	
						PlmTlEquipmentfmeamst existPlmTlEquipmentfmeamst = (PlmTlEquipmentfmeamst)httpSession.getAttribute("newPlmTlEquipmentfmeamst");
						newPlmTlEquipmentfmeamst.setFmeqCreatedby(user.getUsrm_ccno());
						List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtlList =  new ArrayList<PlmTlEquipmentfmeadtl>();
						if(UIUtils.isValidKeyId(fmeadetails)){
				    		JSONArray plmTlEquipmentfmeadtlJson = null;	
				    		plmTlEquipmentfmeadtlJson = JSONArray.fromString(fmeadetails);
				    		plmTlEquipmentfmeadtlList=(List<PlmTlEquipmentfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlEquipmentfmeadtl, plmTlEquipmentfmeadtlJson);
						    if(plmTlEquipmentfmeadtlList!= null)
						    {
						        for(int i=0 ;i<=plmTlEquipmentfmeadtlList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        }
						        
						    }
						}
					    List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtlReList = null;
					    if(UIUtils.isValidKeyId(fmeadtlsreview)){
				    		JSONArray plmTlEquipmentfmeadtlRejson = null;	
				    		plmTlEquipmentfmeadtlRejson = JSONArray.fromString(fmeadtlsreview);
				    		plmTlEquipmentfmeadtlReList=(List<PlmTlEquipmentfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlEquipmentfmeadtl, plmTlEquipmentfmeadtlRejson);
						    if(plmTlEquipmentfmeadtlReList!= null)
						    {
						    	if(plmTlEquipmentfmeadtlReList.size()<0){
						    		plmTlEquipmentfmeadtlReList=new ArrayList<PlmTlEquipmentfmeadtl>();
						    	}
						        for(int i=0 ;i<=plmTlEquipmentfmeadtlReList.size()-1;i++){
						        	PlmTlEquipmentfmeadtl  plmTlEquipmentfmeadtl =plmTlEquipmentfmeadtlReList.get(i);
						        	plmTlEquipmentfmeadtlList.add(plmTlEquipmentfmeadtl);
						        }
						    }
					    }
					    newPlmTlEquipmentfmeamst.setPlmTlEquipmentfmeadtl(plmTlEquipmentfmeadtlList);
						if( UIUtils.isValidKeyId (newPlmTlEquipmentfmeamst.getFmeqKeyid())){
							existPlmTlEquipmentfmeamst =fmeaFormatService.deleteDtls(newPlmTlEquipmentfmeamst);				
							msg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete");					
						}
					}
					else if(type.equals("process")){
						PlmTlProcessfmeamst  newPlmTlProcessfmeamst =new 	PlmTlProcessfmeamst();
						PlmTlProcessfmeadtl  newPlmTlProcessfmeadtl =new PlmTlProcessfmeadtl();
						newPlmTlProcessfmeamst =(PlmTlProcessfmeamst)UIUtils.setBeanProperties((Object)newPlmTlProcessfmeamst,request);//master							 	
						PlmTlProcessfmeamst existPlmTlProcessfmeamst = (PlmTlProcessfmeamst)httpSession.getAttribute("newPlmTlProcessfmeamst");
						newPlmTlProcessfmeamst.setFmpmCreatedby(user.getUsrm_ccno());
						List<PlmTlProcessfmeadtl> plmTlProcessfmeadtlList = new ArrayList<PlmTlProcessfmeadtl>();
						if(UIUtils.isValidKeyId(fmeadetails)){
				    		JSONArray plmTlProcessfmeadtlJson = null;	
				    		plmTlProcessfmeadtlJson = JSONArray.fromString(fmeadetails);
				    		plmTlProcessfmeadtlList=(List<PlmTlProcessfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlProcessfmeadtl, plmTlProcessfmeadtlJson);
						    if(plmTlProcessfmeadtlList!= null)
						    {
						        for(int i=0 ;i<=plmTlProcessfmeadtlList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        }
						    }
						}
					    List<PlmTlProcessfmeadtl> plmTlProcessfmeadtlReList = null;
					    if(UIUtils.isValidKeyId(fmeadtlsreview)){
				    		JSONArray plmTlProcessfmeadtlRejson = null;	
				    		plmTlProcessfmeadtlRejson = JSONArray.fromString(fmeadtlsreview);
				    		plmTlProcessfmeadtlReList=(List<PlmTlProcessfmeadtl>)UIUtils.convertJSONArrToList(newPlmTlProcessfmeadtl, plmTlProcessfmeadtlRejson);
						    if(plmTlProcessfmeadtlReList!= null)
						    {
						    	if(plmTlProcessfmeadtlReList.size()<0){
						    		plmTlProcessfmeadtlList=new ArrayList<PlmTlProcessfmeadtl>();
						    	}
						        for(int i=0 ;i<=plmTlProcessfmeadtlReList.size()-1;i++){
						        	//plmTlDesignfmeadtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
						        	PlmTlProcessfmeadtl  plmTlProcessfmeadtl =plmTlProcessfmeadtlReList.get(i);
						        	//plmTlProcessfmeadtl.setFmpdReviewby(user.getUsrm_ccno());
						        	//plmTlEquipmentfmeadtl.setFmedRedate();
						        	plmTlProcessfmeadtlList.add(plmTlProcessfmeadtl);
						        }
						    }
					    }
					    newPlmTlProcessfmeamst.setPlmTlProcessfmeadtl(plmTlProcessfmeadtlList);
						if(  UIUtils.isValidKeyId (newPlmTlProcessfmeamst.getFmpmKeyid())){	
							existPlmTlProcessfmeamst =fmeaFormatService.deleteDtls(newPlmTlProcessfmeamst);				
							msg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete");				
						}
					}
					if(UIUtils.isValidKeyId(msg)){
						JSONObject successData = new JSONObject();
						successData.put("msg",msg);
						JSONObject returnData = new JSONObject();//
						returnData.put("successData", successData);
						returnData.put("formClear", false);
						out.print(returnData.toString());//
						out.close();
					}
				  }
				}
			}catch (ValidationExceptions e) 
			{
				CommonMessage.debugMsg("ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "FMEAValidation");
				out.print(errMessage.toString());							    	
			}
								
			catch(BusinessApplicationExceptions e)
			{
				e.printStackTrace();
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "FMEAValidation");
				out.print(errMessage.toString());
				CommonMessage.debugMsg(" e " + errMessage );
			}
			catch(Exception e)
			{   
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
			
    }
	
	
	private void getColFmeaDtlsList(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession) throws IOException{
		PrintWriter out = response.getWriter();
		String keyId =request.getParameter("keyid");
		String type =request.getParameter("type");
		String fmeaDocType = request.getParameter("fmeaDocType");
		String fmeaDocmstid = request.getParameter("fmeaDocmstid");
		String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");
		CommonFilter commonFilter = new CommonFilter();
		CommonMessage.debugMsg("keyid"+keyId);
		commonFilter.setKey(keyId);
		commonFilter.setBdType(type);
		commonFilter.setDocType(fmeaDocType);
		commonFilter.setDM(fmeaDocmstid);
		commonFilter.setDmtdetailid(fmeaDocdtlsid);
		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;		
		try {						
			FilterValues.getCommonFilters(request, commonFilter);
			projectListGrid =  fmeaFormatService.getFMEADtlsList(commonFilter);
			
			CommonMessage.debugMsg("Printinh the list");
			for(String[] arr:projectListGrid) 
			{
				CommonMessage.debugMsg(Arrays.toString(arr));
			}
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}			 
		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		 GridColModel gridColModel = new GridColModel();			
		 gridColModel.setHeaderNum(1);
		 
		 jqGridTableModel.setSortable(false);			 
		 jqGridTableModel.setTableButton(false);
		 jqGridTableModel.setEnableFilter(true);
		 jqGridTableModel.setRowNumbers(true);	
		 jqGridTableModel.setGridEdit(true);
		 
		 String [] colHeaderHead = projectListGrid.get(0);
		 String [] colHeader = projectListGrid.get(1);
		 List<String[]> headers = new ArrayList<String[]>();	
		 headers.add(colHeader);
		 
		 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
   	     jsonObject.set("tableWidth", "104%%");
     	 jsonObject.set("tableHeight", "49%%");
   	     httpSession.removeAttribute("fmeaDtlsColModel");
		 httpSession.setAttribute("fmeaDtlsColModel",jsonObject);	
		 CommonMessage.debugMsg("jsonObject " + jsonObject);
		 out.println(jsonObject);
	}
	
	private void getDataFmeaDtlsList(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession) throws IOException{
		CommonMessage.debugMsg("getDataFmeaDtlsList");	
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"fmeaDtlsCommonFilter",true);
			FilterValues.getCommonFilters(request, commonFilter);
			String keyId =request.getParameter("keyid");
			String type =request.getParameter("type");
			String fmeaDocType = request.getParameter("fmeaDocType");
			String fmeaDocmstid = request.getParameter("fmeaDocmstid");
			String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");
			CommonMessage.debugMsg("keyid"+keyId);
			commonFilter.setKey(keyId);
			commonFilter.setBdType(type);
			commonFilter.setDocType(fmeaDocType);
			commonFilter.setDM(fmeaDocmstid);
			commonFilter.setDmtdetailid(fmeaDocdtlsid);
			List<String[]> MasterGrid = fmeaFormatService.getFMEADtlsList(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
			out.println(ResourceGridmod);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}
	
	private void getColFmeaReviewList(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession) throws IOException{
		CommonMessage.debugMsg("getColFmeaReviewList");	
		PrintWriter out = response.getWriter();
		String keyId =request.getParameter("keyid");
		String type =request.getParameter("type");
		String fmeaDocType = request.getParameter("fmeaDocType");
		String fmeaDocmstid = request.getParameter("fmeaDocmstid");
		String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");
		CommonFilter commonFilter = new CommonFilter();
		CommonMessage.debugMsg("keyid"+keyId);
		commonFilter.setKey(keyId);
		commonFilter.setBdType(type);
		commonFilter.setDocType(fmeaDocType);
		commonFilter.setDM(fmeaDocmstid);
		commonFilter.setDmtdetailid(fmeaDocdtlsid);
		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;		
		try {						
			FilterValues.getCommonFilters(request, commonFilter);
			projectListGrid =  fmeaFormatService.getFMEAReviewList(commonFilter);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}			 
		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		 GridColModel gridColModel = new GridColModel();			
		 gridColModel.setHeaderNum(1);
		 
		 jqGridTableModel.setSortable(false);			 
		 jqGridTableModel.setTableButton(false);
		 jqGridTableModel.setEnableFilter(true);
		 jqGridTableModel.setRowNumbers(true);	
		 jqGridTableModel.setGridEdit(true);
		 String [] colHeaderHead = projectListGrid.get(0);
		 String [] colHeader = projectListGrid.get(1);
		 List<String[]> headers = new ArrayList<String[]>();	
		 headers.add(colHeader);
		 
		 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
   	     jsonObject.set("tableWidth", "104%%");
     	 jsonObject.set("tableHeight", "49%%");
   	     httpSession.removeAttribute("fmeaReviewColModel");
		 httpSession.setAttribute("fmeaReviewColModel",jsonObject);	
		 CommonMessage.debugMsg("jsonObject " + jsonObject);
		 out.println(jsonObject);
	}
	
	private void getDataFmeaReviewList(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		CommonMessage.debugMsg("getDataFmeaReviewList");	
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"fmeaREviewCommonFilter",true);
			FilterValues.getCommonFilters(request, commonFilter);
			String keyId =request.getParameter("keyid");
			String type =request.getParameter("type");
			String fmeaDocType = request.getParameter("fmeaDocType");
			String fmeaDocmstid = request.getParameter("fmeaDocmstid");
			String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");
			CommonMessage.debugMsg("keyid"+keyId);
			commonFilter.setKey(keyId);
			commonFilter.setBdType(type);
			commonFilter.setDocType(fmeaDocType);
			commonFilter.setDM(fmeaDocmstid);
			commonFilter.setDmtdetailid(fmeaDocdtlsid);
			List<String[]> MasterGrid = fmeaFormatService.getFMEAReviewList(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
			out.println(ResourceGridmod);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}
	
	private void getColFmeaMstList(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		PrintWriter out = response.getWriter();
		
		CommonFilter  commonFilter = populateCommonFilter(request,"fmeaMstCommonFilter",true);
		
		
		String flid =request.getParameter("flid");
		String type =request.getParameter("type");
		String fmeaDocType = request.getParameter("fmeaDocType");
		String fmeaDocmstid = request.getParameter("fmeaDocmstid");
		String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");
		
		
		//FilterValues.getCommonFilters(request, commonFilter);
		
		
		CommonMessage.debugMsg("flid"+flid);
		//commonFilter.setFlid(flid);
		commonFilter.setBdType(type);
		commonFilter.setDocType(fmeaDocType);
		commonFilter.setDM(fmeaDocmstid);
		commonFilter.setDmtdetailid(fmeaDocdtlsid);
		commonFilter.setIsGetCol("Y");
		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;		
		try {	
			
			
			projectListGrid =  fmeaFormatService.getFMEAMstList(commonFilter);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}			 
		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		 GridColModel gridColModel = new GridColModel();			
		 gridColModel.setHeaderNum(1);
		 
		 jqGridTableModel.setSortable(false);			 
		 jqGridTableModel.setTableButton(true);
		 jqGridTableModel.setEnableFilter(true);
		 jqGridTableModel.setRowNumbers(true);
		 String [] colHeaderHead = projectListGrid.get(0);
		 String [] colHeader = projectListGrid.get(1);
		 List<String[]> headers = new ArrayList<String[]>();	
		 headers.add(colHeader);
		 
		 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
   	     jsonObject.set("tableWidth", "108%%");
     	 jsonObject.set("tableHeight", "85%%");
   	     httpSession.removeAttribute("fmeaMstColModel");
		 httpSession.setAttribute("fmeaMstColModel",jsonObject);	
		 CommonMessage.debugMsg("jsonObject " + jsonObject);
		 out.println(jsonObject);
	}
	
	
	
	private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
		
		  HttpSession httpSession = request.getSession(false);
		  		
		  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		  		if( commonFilter != null && ! createNew ){
		  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
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
		  		//CommonFunctions("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		  		return commonFilter;
		  	}
	
	
	/*private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){

		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			CommonMessage.debugMsg("false.....");		
						
			FilterValues.setPaginationParams(request,commonFilter);
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
		}	
		
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
			commonFilter.setViewClick('Y');
			
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
		
	}*/

	
	private void getDataFmeaMstList(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		CommonMessage.debugMsg("getDataFmeaMstList");	
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"fmeaMstCommonFilter",false);
			//FilterValues.getCommonFilters(request, commonFilter);
			//String flid =request.getParameter("flid");
			String type =request.getParameter("type");
			String fmeaDocType = request.getParameter("fmeaDocType");
			String fmeaDocmstid = request.getParameter("fmeaDocmstid");
			String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");
			CommonMessage.debugMsg("flid"+commonFilter.getFlid());
			//commonFilter.setFlid(flid);
			commonFilter.setBdType(type);
			commonFilter.setDocType(fmeaDocType);
			commonFilter.setDM(fmeaDocmstid);
			commonFilter.setDmtdetailid(fmeaDocdtlsid);
			commonFilter.setIsGetCol("N");
			
			List<String[]> MasterGrid = fmeaFormatService.getFMEAMstList(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
			out.println(ResourceGridmod);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}
	private void getExcelFmeaMstList(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) throws Exception {
		CommonFilter commonFilter = populateCommonFilter(request,"fmeaMstCommonFilter",false);
		commonFilter.setViewClick('Y');
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);	
		String flid =request.getParameter("flid");
		String type =request.getParameter("type");
		String fmeaDocType = request.getParameter("fmeaDocType");
		String fmeaDocmstid = request.getParameter("fmeaDocmstid");
		String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");
		commonFilter.setFlid(flid);
		commonFilter.setBdType(type);
		commonFilter.setDocType(fmeaDocType);
		commonFilter.setDM(fmeaDocmstid);
		commonFilter.setDmtdetailid(fmeaDocdtlsid);
		JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("fmeaMstColModel");
		tblJSONObj.put("title", type + " Fmea Report");
		String format = ExcelUtils.getFormat(request);			
		Workbook wb = fmeaFormatService.getFmeaListExportExcel(commonFilter,tblJSONObj,format);
		commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, type+"FmeaReport", format);		
		
	}
	
	private void getExcelFmea(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) throws IOException {
		// TODO Auto-generated method stub
		try{
			CommonFilter commonFilter = new CommonFilter();					
			String keyid =request.getParameter("keyid");
			String flid =request.getParameter("flid");
			String type =request.getParameter("type");
			String fmeaDocType = request.getParameter("fmeaDocType");
			String fmeaDocmstid = request.getParameter("fmeaDocmstid");
			String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");
			CommonMessage.debugMsg("keyid::::::"+keyid);
			String format = ExcelUtils.getFormat(request);
			commonFilter = FilterValues.getCommonFilters(request, commonFilter);
			commonFilter.setKey(keyid);
			commonFilter.setFlid(flid);
			commonFilter.setBdType(type);
			commonFilter.setDocType(fmeaDocType);
			commonFilter.setDM(fmeaDocmstid);
			commonFilter.setDmtdetailid(fmeaDocdtlsid);
			format = "xlsx";
			String path = UIUtils.getExcelTemplatePath(request); 
			CommonMessage.debugMsg("FMEA excel path" + path);
			Workbook wb = fmeaFormatService.getFmeaExcel(keyid,format,path,commonFilter); 
			ExcelUtils.writeToResponse(response, wb, type+"fmea_"+keyid, format);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			PrintWriter out = response.getWriter();
			JSONObject err = new JSONObject();		
			err.put("message" ,"Data Not Found" );
			out.print(err.toString());
		}
	}

	private void loadEntry(HttpServletRequest request,HttpServletResponse response) throws IOException{
		CommonMessage.debugMsg("loadEntry");	
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			String type = request.getParameter("type");
			String keyId = request.getParameter("keyid");
			String fmeaDocType = request.getParameter("fmeaDocType");
			String fmeaDocmstid = request.getParameter("fmeaDocmstid");
			String fmeaDocdtlsid = request.getParameter("fmeaDocdtlsid");	
			request.setAttribute("fmeaDate", UIUtils.getActualDateForm(CommonFunctions.dateTimeNow()));
			request.setAttribute("fmeaPreparedby", user.getUsrm_ccno());
			if(UIUtils.isValidKeyId(keyId)){
				List<String[]> fmeaList = fmeaFormatService.select(keyId,type);
				if(fmeaList.size()>0){					
					String[] fmea=fmeaList.get(0);
					request.setAttribute("fmeaKeyid", fmea[0]);
					request.setAttribute("fmeaFlid", fmea[1]);
					request.setAttribute("fmeaDate", UIUtils.getActualDateForm(fmea[2]));
					request.setAttribute("fmeaNo", fmea[3]);
					request.setAttribute("fmeaPreparedby", fmea[4]);
					request.setAttribute("fmeaCoreteam", fmea[5]);
					
					if(type.equals("design")){
						request.setAttribute("fmdmSystemid", fmea[6]);
						request.setAttribute("fmdmSupsystemid", fmea[7]);
						request.setAttribute("fmdmComponentid", fmea[8]);
					}
					else if(type.equals("equipment")){
						request.setAttribute("fmeqEquipid", fmea[6]);
						request.setAttribute("fmeqSupequipid", fmea[7]);
					}
					else if(type.equals("process")){
						request.setAttribute("fmpmProcessid", fmea[6]);
						request.setAttribute("fmpmSupprocessid", fmea[7]);
					}
				}				
			}
			else { 
				String processId = request.getParameter("processId");
				String subProcessid = request.getParameter("subProcessid");
				if (UIUtils.isValidKeyId(processId))
					request.setAttribute("fmpmProcessid", processId);
				if (UIUtils.isValidKeyId(subProcessid))
					request.setAttribute("fmpmSupprocessid", subProcessid);

			}
				
						
			request.setAttribute("fmeaType", type);
			request.setAttribute("fmeaDocType", fmeaDocType);
			request.setAttribute("fmeaDocmstid", fmeaDocmstid);
			request.setAttribute("fmeaDocdtlsid", fmeaDocdtlsid);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/FMEA/FMEAEntry.jsp");
			rd.forward(request, response);			
			CommonMessage.debugMsg(" response " + response);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}
	
		
}
