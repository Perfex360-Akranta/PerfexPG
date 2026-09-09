package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.BdmTlCriticalityassessment;
import com.akranta.tpm.model.CommonFilter;

public interface BdmTlCriticalityassessmentDao {

	public abstract List<BdmTlCriticalityassessment> create(List<BdmTlCriticalityassessment> CrtieriaGridList1) throws Exception;
	public abstract List<BdmTlCriticalityassessment> update(List<BdmTlCriticalityassessment> newBdmTlCriticalityassessment,List<BdmTlCriticalityassessment> existBdmTlCriticalityassessment)throws Exception;
	public abstract BdmTlCriticalityassessment deleteCritical(BdmTlCriticalityassessment oldBdmTlCriticalityassessment)throws Exception;
	public abstract List<String[]> getCriticality()throws Exception;
	public abstract List<String[]> getCriticalityfillgriddata(CommonFilter commonFilter1, String flid, String crytype)throws Exception;
	public abstract Workbook getCriticalassmntExcel(JSONObject colmodel,String format, CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getCriAssMainGrid(CommonFilter commonFilter) throws Exception;
	public abstract String getElementID(String casmFlid) throws Exception;
	public abstract String DeleteCriteriaList(String criteriaDeleteList);
	public abstract String DeleteFlidListMst(String criteriaDeleteFlidList);
	public abstract List<String[]> getCriticalassmntcritria( String flid ,  String equm,
			String total, String trade) throws Exception;
	public abstract String getCriticalassmntremarks(String flid, String equm)throws Exception;	
	public abstract List<String[]> getcriticalReport(CommonFilter commonFilter)throws Exception;
	public abstract String getFlid(String parentFlid)throws Exception;
	public abstract Workbook getcriticalReporcExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter)throws Exception;
	public abstract Workbook getCriticalityAssessmentMstExcel(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception;
	
	public abstract void BdmTlCriticalityassessmentDaoImplJwt(String jwtToken);
	
}

