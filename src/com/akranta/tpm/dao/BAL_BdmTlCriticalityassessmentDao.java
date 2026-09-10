package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.BAL_BdmTlCriticalityassessment;
import com.akranta.tpm.model.CommonFilter;

public interface BAL_BdmTlCriticalityassessmentDao {

	public abstract List<BAL_BdmTlCriticalityassessment> create(List<BAL_BdmTlCriticalityassessment> CrtieriaGridList1) throws Exception;
	public abstract List<BAL_BdmTlCriticalityassessment> update(List<BAL_BdmTlCriticalityassessment> newBdmTlCriticalityassessment,List<BAL_BdmTlCriticalityassessment> existBdmTlCriticalityassessment)throws Exception;
	public abstract BAL_BdmTlCriticalityassessment deleteCritical(BAL_BdmTlCriticalityassessment oldBdmTlCriticalityassessment)throws Exception;
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
}

