package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.BdmTlMst;
import com.akranta.tpm.model.BdmTlNewphncausereq;
import com.akranta.tpm.model.BdmTlPhenomenamst;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlUnplannedmaintdtl;
import com.akranta.tpm.model.PlmTlUnplannedmaintmst;
import com.akranta.tpm.model.SapExternalRepair;
import com.akranta.tpm.model.SapExternalServiceDtl;
import com.akranta.tpm.model.SapExternalServiceMst;
import com.akranta.tpm.model.SapTlMaintenanceOrdermst;
import com.akranta.tpm.model.SapTlMaintenanceorder;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.model.WomTlWomst;

public interface BreakdownService {

	public BdmTlMst create(BdmTlMst newBdmTlMst,BdmTlMst oldBdmTlMst,  BDFormBean bdFormBean ) throws ValidationExceptions, Exception;
	public BdmTlMst update(BdmTlMst newBdmTlMst,BdmTlMst oldBdmTlMst,  BDFormBean bdFormBean,WomTlWomst womTlWomst)  throws Exception;
	public BdmTlMst delete(BdmTlMst bdmTlMst) throws Exception;
	public WomTlCommunicationlog saveCommTxt(WomTlCommunicationlog newWomTlCommunicationlog,WomTlCommunicationlog existWomTlCommunicationlog,BDFormBean bdFormBean) throws Exception;
	public List<String[]> getAllBreakdown(CommonFilter commonFilter) throws Exception;
	public BdmTlMst select(String keyid) throws Exception;
	public BdmTlDtl selectBd(String keyid) throws Exception;
	public PlmTlUnplannedmaintmst selectUPM(String keyid) throws Exception;
	public PlmTlUnplannedmaintdtl selectUPMDetail(String keyid) throws Exception;
	public BdmTlWhywhymst selectWhyWhy(String keyid) throws Exception;
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
	public List<ComboBox> getSpare(String condSql,String assmId, ComboFilter comboFilter) throws Exception;
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
	public List<String[]> getSapInfoList(String wwNo)throws Exception;
	public List<String[]> getExtServiceList(CommonFilter commonFilter, String ExtMasterId)throws Exception;
	public List<String[]> getExtSubList(String wwNo)throws Exception;
	public List<String[]> getExtRepairList(String wwNo)throws Exception;
	public SapExternalServiceMst createExtService( SapExternalServiceMst newSapExternalServiceMst,
			SapExternalServiceMst existSapExternalServiceMst, BDFormBean bdFormBean)throws Exception;
	public SapExternalServiceMst updateExtService( SapExternalServiceMst newSapExternalServiceMst,
			SapExternalServiceMst existSapExternalServiceMst, BDFormBean bdFormBean)throws Exception;
	public SapExternalServiceMst getExtServiceData(String extKeyid)throws Exception;
	public SapExternalServiceDtl createExtServiceDtl(
			SapExternalServiceDtl newSapExternalServiceDtl,
			SapExternalServiceDtl existSapExternalServiceDtl,
			BDFormBean bdFormBean)throws Exception;
	public SapExternalServiceDtl updateExtServiceDtl(
			SapExternalServiceDtl newSapExternalServiceDtl,
			SapExternalServiceDtl existSapExternalServiceDtl,
			BDFormBean bdFormBean)throws Exception;
	public SapExternalServiceDtl getserviceDtlData(String detailKeyid)throws Exception;
	public String delteExtDetail(String detailKeyid)throws Exception;
	public SapExternalRepair createExtRepairDtl(
			SapExternalRepair newSapExternalRepair,
			SapExternalRepair existSapExternalRepair, BDFormBean bdFormBean)throws Exception;
	public SapExternalRepair updateExtRepairDtl(
			SapExternalRepair newSapExternalRepair,
			SapExternalRepair existSapExternalRepair, BDFormBean bdFormBean)throws Exception;
	public SapExternalRepair getRepairDtlData(String detailRepairKeyid)throws Exception;
	public String delteExtRprDetail(String rpeDetailKeyid)throws Exception;
	public SapTlMaintenanceOrdermst getSapSpareInFoData(String bdKeyid)throws Exception;
	public String deleteCommLog(String comLogKeyid) throws Exception  ;
	public List<String[]> getExtServiceList(CommonFilter commonFilter, String womsId, String taskId) throws Exception;
	public String getServicemstId(String taskid) throws Exception;
	public List<ComboBox> getServiceNo(ComboFilter comboFilter) throws Exception;
	public List<String[]> getServiceText(String servmId) throws Exception;
	public List<String[]> getBDAnalysisRpt(CommonFilter commonFilter) throws Exception;
	public Workbook getBreakdownAnalysisExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws IOException, SQLException, Exception;

	public void BreakdownServiceImplJwt(String string);
}
