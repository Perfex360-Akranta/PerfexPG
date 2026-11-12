package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.SheTlPlantSafetyBean;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlKaizenbankmst;
//import com.akranta.tpm.model.SheTlAudittdtl;
//import com.akranta.tpm.model.SheTlAudittmst;
import com.akranta.tpm.model.SheTlPlantSafetydtl;
import com.akranta.tpm.model.SheTlPlantSafetymst;

import net.sf.json.JSONObject;

public interface EHSPlantSafetyService {

public SheTlPlantSafetymst create(SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetymst existSheTlPlantSafetymst,SheTlPlantSafetyBean sheTlPlantSafetyBean) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
public SheTlPlantSafetymst update(SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetymst existSheTlPlantSafetymst,SheTlPlantSafetyBean sheTlPlantSafetyBean) throws ValidationExceptions,BusinessApplicationExceptions,Exception;
public SheTlPlantSafetymst delete(SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetydtl sheTlPlantSafetydtl) throws Exception;	
public List<String[]> deletedtl(String dtlid) throws Exception;
public List<String[]> getCheck(String keyid,String auditflid,String auditdate,String type) throws Exception;
public SheTlPlantSafetymst getvaluesmst(String keyid) throws Exception ;
public SheTlPlantSafetydtl getvaluesdtl(String keyid) throws Exception ;
public List<String[]> getSafetyDetailsList(CommonFilter commonFilter) throws Exception;
public Workbook AuditExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format, String type, String mode) throws SQLException, IOException, Exception;
public List<String[]> getSafetygridDetail(CommonFilter commonFilter) throws Exception;
public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws NoDataFoundException, Exception;
public List<String[]> safetyPopup(CommonFilter commonFilter)throws Exception;
public List<String[]> ExitsAuditData(String combotype, String flId, String datevalue) throws Exception;
public List<String[]> resupdate(String dtlid,String Resid,String targetdate)throws Exception;
public List<String[]> verifyupdate(String dtlid,String Verid,String verifydate)throws Exception;
public String singleResponsiblty(String detailkeyid, String responsevalue, String targetdate, SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetydtl sheTlPlantSafetydtl) throws BusinessApplicationExceptions, Exception;
public String updateActionCLosure(String detailkeyid, String responsevalue, String targetdate, String reviseddate, String status, String correctiveaction,String remarks, String abnKeyid) throws BusinessApplicationExceptions, Exception;
public String getResponse(String modiefiedid) throws SQLException;
public String getObser(String modiefiedid) throws SQLException;	
public String UpdateVerfification(String detailkeyid, String responsevalue, String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception;
public List<String> getCheckDeleteList(String detailkeyid, String apmdelete) throws BusinessApplicationExceptions, Exception;
public String getdetailvalue(String keyid) throws SQLException;
public List<String[]> getSafetyReportDetails(CommonFilter commonFilter) throws Exception;
public Workbook getPlantSafetyExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws IOException, SQLException, Exception;
public String Response(String modiefiedid)throws SQLException;
public Workbook PlanSafetyExcelView(String plansafetykeyid, String flid,String format,String path)throws Exception;
public String getMaster(String replace)throws SQLException;
public AbnTlAbnormality create(AbnTlAbnormality abnTlAbnormality) throws Exception;
public AbnTlAbnormality updateAbnormality(AbnTlAbnormality abnTlAbnormality) throws Exception;
public KznTlKaizenbankmst createSuggestion(KznTlKaizenbankmst newKznTlKaizenbankmst) throws Exception;//Suggestion in psi type
public String getElementId(String flid)throws Exception;
public KznTlKaizenbankmst UpdateSuggestion(KznTlKaizenbankmst newKznTlKaizenbankmst)throws Exception ;
public void EHSPlantSafetyServiceImplJwt(String JwtToken);

}

