package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.BdmTlCriticalityassessment;
import com.akranta.tpm.model.CommonFilter;

public interface CriticalityAssessmentService {
	
	public List<String[]> getCriticality() throws Exception ;

	public List<String[]> getCriticalityfillgriddata(CommonFilter commonFilter1, String flid, String crytype) throws Exception ;

	public List<BdmTlCriticalityassessment> create(List<BdmTlCriticalityassessment> CrtieriaGridList1)throws Exception ;

	public List<BdmTlCriticalityassessment> update(List<BdmTlCriticalityassessment> newBdmTlCriticalityassessment,List<BdmTlCriticalityassessment> existBdmTlCriticalityassessment) throws Exception;

	public BdmTlCriticalityassessment deleteCritical(BdmTlCriticalityassessment oldBdmTlCriticalityassessment) throws Exception;

	public Workbook getCriticalassmntExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;

	public List<String[]> getCriAssMainGrid(CommonFilter commonFilter) throws Exception;

	public String DeleteCriteriaList(String criteriaDeleteList);

	public String DeleteFlidListMst(String criteriaDeleteFlidList);

	public List<String[]> getCriticalassmntcritria( String flid ,String equm, String total, String trade) throws Exception;

	public String getCriticalassmntremarks(String flid, String equm) throws Exception;
	
	public List<String[]> getcriticalReport(CommonFilter commonFilter) throws Exception;

	public String getFlid(String parentFlid)throws Exception;

	public Workbook getcriticalReporcExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter)throws Exception;

	public Workbook getCriticalityAssessmentMstExcel(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception;
	
	public void CriticalityAssessmentServiceImplJwt(String JwtToken);
}
