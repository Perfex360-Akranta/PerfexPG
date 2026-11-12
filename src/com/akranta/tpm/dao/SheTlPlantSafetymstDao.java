package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlKaizenbankmst;
//import com.akranta.tpm.model.SheTlAudittdtl;
//import com.akranta.tpm.model.SheTlAudittmst;
import com.akranta.tpm.model.SheTlPlantSafetydtl;
import com.akranta.tpm.model.SheTlPlantSafetymst;

import net.sf.json.JSONObject;

public interface SheTlPlantSafetymstDao {

	
	public abstract SheTlPlantSafetymst create(SheTlPlantSafetymst sheTlPlantSafetymst) throws Exception;
	public abstract SheTlPlantSafetymst update(SheTlPlantSafetymst sheTlPlantSafetymst) throws Exception;
	public abstract SheTlPlantSafetymst delete(SheTlPlantSafetymst sheTlPlantSafetymst,SheTlPlantSafetydtl sheTlPlantSafetydtl) throws Exception;
	public abstract List<String[]> getSafetyDetailsList(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getSafetygridDetail(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getAuditExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String type, String formmode) throws SQLException, IOException, Exception;
	public abstract SheTlPlantSafetymst getvaluesmst(String keyid) throws Exception;
	public abstract List<String[]> getCheck(String keyid,String auditflid,String auditdate,String type) throws Exception;
	public abstract List<String[]> deletedtl(String dtlid) throws Exception;
	public abstract SheTlPlantSafetydtl getvaluesdtl(String keyid) throws Exception;
	public abstract List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empid) throws NoDataFoundException, Exception;
	public abstract List<String[]> safetyPopup(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getExistAuditRecord(String combotype, String flId, String datevalue) throws Exception;
	public abstract List<String[]> resupdate(String dtlid,String Resid,String targetdate)throws Exception;
	public abstract List<String[]> verifyupdate(String dtlid,String Verid,String verifydate)throws Exception;
	public abstract String singleResponsiblty(String detailkeyid, String responsevalue, String targetdate, SheTlPlantSafetymst newsheTlPlantSafetymst,SheTlPlantSafetydtl sheTlPlantSafetydtl) throws BusinessApplicationExceptions, Exception;
	public abstract String updateActionClosure(String detailkeyid, String responsevalue, String targetdate,String revisedate, String status, String recomndaction,String remarks, String abnKeyid) throws BusinessApplicationExceptions, Exception;
	public abstract String UpdateVerfification(String detailkeyid, String responsevalue, String targetdate,String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception;
	public abstract List<String> getCheckDeleteList(String detailkeyid, String apmdelete) throws BusinessApplicationExceptions, Exception;
	public abstract String getdetailvalue(String keyid) throws SQLException;
	public abstract List<String[]> getSafetyReportDetails(CommonFilter commonFilter) throws Exception;
	public Workbook getPlantSafetyExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws IOException, SQLException, Exception;
	public List<String[]> PlansafeList(String Keyid,String flid)throws Exception;
	public List<String[]> PlansafeDetailList(String Keyid,String flid)throws Exception;
	public List<String[]> PlanSafetyVerify(String Keyid,String flid)throws Exception;
	public abstract AbnTlAbnormality createPsiAbn(AbnTlAbnormality abnTlAbnormality) throws Exception;
	public abstract AbnTlAbnormality UpdatePSIAbn(AbnTlAbnormality abnTlAbnormality) throws Exception;
	public abstract KznTlKaizenbankmst createPSISuggetion(KznTlKaizenbankmst knTlKaizenbankmst)throws Exception;//Psi for A=Suggeston
	public abstract String getElementId(String flid)throws Exception;
	public abstract KznTlKaizenbankmst updatePSISuggetion(KznTlKaizenbankmst newKznTlKaizenbankmst)throws Exception;
	public abstract void SheTlPlantSafetymstDaoImplJwt(String jwtToken);
}
