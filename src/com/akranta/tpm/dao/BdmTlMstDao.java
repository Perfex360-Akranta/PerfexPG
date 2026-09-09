package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.model.BdmTlMst;
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

public interface BdmTlMstDao {

	public abstract BdmTlMst create(BdmTlMst bdmTlMst) throws Exception;
	public abstract BdmTlMst update(BdmTlMst bdmTlMst,WomTlWomst womTlWomst) throws Exception;
	public abstract BdmTlMst delete(BdmTlMst bdmTlMst) throws Exception;
	public WomTlCommunicationlog saveCommTxt(WomTlCommunicationlog newWomTlCommunicationlog) throws Exception;
	public BdmTlMst select(String keyid) throws Exception;
	public PlmTlUnplannedmaintmst selectUPM(String keyid) throws Exception;
	public PlmTlUnplannedmaintdtl selectUPMDetail(String keyid) throws Exception;
	public List<String[]> getAllBD(CommonFilter commonFilter) throws Exception;
	//public List<String[]> getShift(List<String> paramValues);
	public String getShift(ShiftBean shiftBean);
	public List<String[]> getDownTime(List<String> paramValues);
	public List<String[]> getCommText(String bdId);
	public List<String[]> getYY(String wwNo);
	public List<String[]> getRootCause(String wwNo)throws Exception;
	public String getPhenType(String bookedPhen);
	public List<String[]> getPillarClassfcn(String pillar) throws Exception;
	public Workbook breakdownRpt(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;
	public abstract List<String[]> getAllBreakdownnew(CommonFilter commonFilter)throws Exception;
	public List<String[]> getSapInfoList(String wwNo) throws Exception;
	public List<String[]> getExtServiceList(CommonFilter commonFilter, String wwNo)throws Exception;
	public List<String[]> getExtSubList(String wwNo)throws Exception;
	public List<String[]> getExtRepairList(String wwNo)throws Exception;
	public abstract SapExternalServiceMst createExtService(
			SapExternalServiceMst newSapExternalServiceMst)throws Exception;
	public abstract SapExternalServiceMst getExtServiceData(String extKeyid)throws Exception;
	public abstract SapExternalServiceMst updateExtService(
			SapExternalServiceMst newSapExternalServiceMst)throws Exception;
	public abstract SapExternalServiceDtl createExtServiceDtl(
			SapExternalServiceDtl newSapExternalServiceDtl)throws Exception;
	public abstract SapExternalServiceDtl getServiceDtlData(String detailKeyid)throws Exception;
	public abstract SapExternalServiceDtl UpdateExtServiceDtl(
			SapExternalServiceDtl newSapExternalServiceDtl)throws Exception;
	public abstract String delteExtDetail(String detailKeyid)throws Exception;
	public abstract SapExternalRepair createExtRepairDtl(
			SapExternalRepair newSapExternalRepair)throws Exception;
	public abstract SapExternalRepair updateExtRepair(
			SapExternalRepair newSapExternalRepair)throws Exception;
	public abstract SapExternalRepair getRepairDtlData(
			String detailRepairKeyid)throws Exception;
	public abstract String delteExtRprDetail(String rpeDetailKeyid)throws Exception;
	public abstract SapTlMaintenanceOrdermst getSapSpareInFoData(String bdKeyid)throws Exception;
	public String deleteCommLog(String comLogKeyid) throws Exception  ;
	public  List<String[]> getExtServiceList(CommonFilter commonFilter, String womsId,	String taskId) throws Exception;
	public abstract String getServicemstId(String taskid) throws Exception ;
	public  List<String[]> getServiceText(String servmId) throws Exception;
	public abstract List<String[]> getBDAnalysisRpt(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getsummaryAnalysisExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws IOException, SQLException, Exception;
	

	public abstract void BdmTlMstDaoImplJwt(String jwtToken);
}

