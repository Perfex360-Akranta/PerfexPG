package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.model.BAL_BdmTlDtl;
import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PcsTlPcsBd;
import com.akranta.tpm.model.PlmTlUnplannedmaintdtl;
import com.akranta.tpm.model.PlmTlUnplannedmaintmst;
import com.akranta.tpm.model.SapExternalRepair;
import com.akranta.tpm.model.SapExternalServiceDtl;
import com.akranta.tpm.model.SapExternalServiceMst;
import com.akranta.tpm.model.SapTlMaintenanceOrdermst;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.model.WomTlWomst;

public interface BAL_BreakdownService {

	public BAL_BdmTlMst create(BAL_BdmTlMst newBdmTlMst,BAL_BdmTlMst oldBdmTlMst,  BAL_BDFormBean bdFormBean ) throws ValidationExceptions, Exception;
	public BAL_BdmTlMst update(BAL_BdmTlMst newBdmTlMst,BAL_BdmTlMst oldBdmTlMst,  BAL_BDFormBean bdFormBean,WomTlWomst womTlWomst)  throws Exception;
	public BAL_BdmTlMst delete(BAL_BdmTlMst bdmTlMst) throws Exception;
	public WomTlCommunicationlog saveCommTxt(WomTlCommunicationlog newWomTlCommunicationlog,WomTlCommunicationlog existWomTlCommunicationlog,BAL_BDFormBean bdFormBean) throws Exception;
	public List<String[]> getAllBreakdown(CommonFilter commonFilter) throws Exception;
	public BAL_BdmTlMst select(String keyid) throws Exception;
	public BAL_BdmTlDtl selectBd(String keyid) throws Exception;
	public PlmTlUnplannedmaintmst selectUPM(String keyid) throws Exception;
	public PlmTlUnplannedmaintdtl selectUPMDetail(String keyid) throws Exception;
	public BAL_BdmTlWhywhymst selectWhyWhy(String keyid) throws Exception;
	public List<String[]> getAllBD(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getComboShift(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getMould(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getRepeatedbdno(String condSql,String assmId,String eqpId,String idFlag, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getFinalTrade(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getAlarm(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getFailureType(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getPhenomena(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getCause(String condSql,String phenId,String assmId, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getBDClassification(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getSpare(String fctId, ComboFilter comboFilter) throws Exception;
	//public List<String[]> getShift(List<String> paramValues);
	public String getShift(ShiftBean shiftBean);
	public List<String[]> getDownTime(List<String> paramValues);
	public List<String[]> getCommText(String bdId);
	public List<String[]> getYY(String wwNo);
	public List<String[]> getPillarClassfcn(String pillar) throws Exception;
	public List<String[]> getRootCause(String wwNo)throws Exception;
	public String getPhenType(String bookedPhen);
	public Workbook breakdownExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getAllBreakdownnew(CommonFilter commonFilter)throws Exception;
	public List<String[]> getSapInfoList(CommonFilter commFilter)throws Exception;  
	public List<String[]> getExtServiceList(String ExtMasterId)throws Exception;
	public List<String[]> getExtSubList(String wwNo)throws Exception;
	public List<String[]> getExtRepairList(String wwNo)throws Exception;
	public SapExternalServiceMst createExtService( SapExternalServiceMst newSapExternalServiceMst,
			SapExternalServiceMst existSapExternalServiceMst, BAL_BDFormBean bdFormBean)throws Exception;
	public SapExternalServiceMst updateExtService( SapExternalServiceMst newSapExternalServiceMst,
			SapExternalServiceMst existSapExternalServiceMst, BAL_BDFormBean bdFormBean)throws Exception;
	public SapExternalServiceMst getExtServiceData(String extKeyid)throws Exception;
	public SapExternalServiceDtl createExtServiceDtl(
			SapExternalServiceDtl newSapExternalServiceDtl,
			SapExternalServiceDtl existSapExternalServiceDtl,
			BAL_BDFormBean bdFormBean)throws Exception;
	public SapExternalServiceDtl updateExtServiceDtl(
			SapExternalServiceDtl newSapExternalServiceDtl,
			SapExternalServiceDtl existSapExternalServiceDtl,
			BAL_BDFormBean bdFormBean)throws Exception;
	public SapExternalServiceDtl getserviceDtlData(String detailKeyid)throws Exception;
	public String delteExtDetail(String detailKeyid)throws Exception;
	public SapExternalRepair createExtRepairDtl(
			SapExternalRepair newSapExternalRepair,
			SapExternalRepair existSapExternalRepair, BAL_BDFormBean bdFormBean)throws Exception;
	public SapExternalRepair updateExtRepairDtl(
			SapExternalRepair newSapExternalRepair,
			SapExternalRepair existSapExternalRepair, BAL_BDFormBean bdFormBean)throws Exception;
	public SapExternalRepair getRepairDtlData(String detailRepairKeyid)throws Exception;
	public String delteExtRprDetail(String rpeDetailKeyid)throws Exception;
	public SapTlMaintenanceOrdermst getSapSpareInFoData(String bdKeyid)throws Exception;

	public List<String[]> getRepeatedBreakDown(CommonParams commonParams) throws Exception;
	public List<String[]> getBDAnalysisRpt(CommonFilter commonFilter) throws Exception;
	public Workbook bdRptSummaryExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public List<ComboBox> getSubAssembly(String machineId, String assmId,
			ComboFilter comboFilter)throws Exception;
	public List<String[]> getMultipleReposibility(String bdEmrNo, CommonParams commonParams) throws Exception;
	public BAL_BdmTlDtl updateErpStatus(BAL_BdmTlDtl newBdmTlDtl, BAL_BdmTlDtl existBdmTlDtl) throws Exception;
	public List<String[]> getBreakDownList(CommonFilter commonFilter) throws Exception;
	public BAL_BdmTlMst createpcsBd(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst existBdmTlMst,
			BAL_BDFormBean bdFormBean) throws Exception;
	public BAL_BdmTlMst deletePcsbd(BAL_BdmTlMst newBdmTlMst) throws Exception;
	public BAL_BdmTlMst updatepcsBd(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst existBdmTlMst,
			BAL_BDFormBean bdFormBean, WomTlWomst womTlWomst)throws Exception;
	 public void BAL_BreakdownServiceImplJwt(String JwtToken);
	

}
