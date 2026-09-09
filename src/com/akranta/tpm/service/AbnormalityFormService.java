package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AbnTlDtl;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.WomTlWomst;

public interface AbnormalityFormService {
			
	public List<ComboBox> getdepartmentcombo(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getAssemblycombo(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getEquipmentcombo(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getIssueNocombo(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getDetectedBycombo(ComboFilter detecdComboFilter) throws Exception;//String condSql,
	public List<ComboBox> getTypecombo(String abtmType,String abnType,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getTagClasscombo(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getCostCentercombo(String condSql) throws Exception;
	public List<ComboBox> getCategorycombo(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getTradecombo(ComboFilter comboFilter) throws Exception;//String condSql
	public List<ComboBox> getcompletedBycombo(ComboFilter cmpbyComboFilter) throws Exception;//String condSql,
	public List<ComboBox> getMainTypecombo(String condSql,ComboFilter comboFilter)throws Exception;
	//public List<ComboBox> getSubTypecombo(String condSql) throws Exception;
	public AbnTlAbnormality create(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality existAbnTlAbnormality, AbnormalityBean abnBean) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public AbnTlAbnormality update(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality existAbnTlAbnormality, AbnormalityBean abnBean,WomTlWomst womTlWomst) throws ValidationExceptions, Exception;
	public AbnTlAbnormality select(String keyid) throws Exception;
	public List<String[]> getAllModifyForm(CommonFilter commonFilter, String setStatus, String pillarType) throws Exception;
	public AbnTlAbnormality abnformfill(String abnId);
	public List<ComboBox> getImpactcombo(String condSql,ComboFilter comboFilter) throws Exception;
	public AbnTlAbnormality delete(AbnTlAbnormality newAbnTlAbnormality)throws ValidationExceptions, Exception;
	public List<ComboBox> getSubTypecombo(String typeId, String formName,ComboFilter htatypeComboFilter) throws Exception ;
	public List<String[]> getAbnormalityDetails(String keyId) throws Exception ;
	public List<String> getTgtDateFromConfig() throws Exception ;
	public Workbook abnExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String reportType)throws Exception ;
	public String getAbnTypeFlag(String abtmKeyid)throws Exception;
	public AbnTlDtl selectHseData(String abnKeyId,String flag)throws Exception;
	public List<String[]> getAbnormalityDetails(String keyId,GridParams gridParams) throws Exception;
	public String getAbnormalityDetailsCount(String keyId, GridParams gridParams) throws Exception;
	public Workbook AbnDetailsExportExcel(JSONObject tblJSONObj, String format,GridParams gridParams,String keyId,String count) throws Exception;
	public List<String[]> getAllAbnormalityDetails(CommonFilter commonFilter, String keyId, String columnId) throws Exception;
	public Workbook getHSEAbnExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public String checkTag() throws Exception;
	public String checkGen() throws Exception;
	public String checkDirectEntry(String woID)throws Exception;
	public String showAbnQuery()throws Exception;
	public List<AbnTlAbnormality> updateBulkTagRemoval(List<AbnTlAbnormality> newAbnTlAbnormality,AbnTlAbnormality existAbnTlAbnormality)throws Exception;
	public List<String[]> getRepeatedAbn(CommonFilter commonFilter)throws Exception;
	public List<String[]> getAbnAllocation(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getAfeemcombo(ComboFilter comboFilter)throws Exception;
	public List<AbnTlAbnormality> updateAbnAllocation(List<AbnTlAbnormality> newAbnTlAbnormality,AbnTlAbnormality existAbnTlAbnormality)throws Exception;
	public Workbook getAbnAllocationExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public String getAbnTradeValues(String abnmRespons)throws Exception;
	public AbnTlAbnormality updateabncomp(AbnTlAbnormality newAbnTlAbnormality)throws Exception;
	public List<String[]> FillabnpopData(String keyid)throws Exception;
	public String getServiceCount(CommonFilter commonFilter)throws Exception;
	//public List<ComboBox> getSubType(String typeId,ComboFilter htatypeComboFilter) throws Exception ;
	public String getSubType(String typeId)throws Exception;
	public List<AbnTlAbnormality> Multiplecreate(List<AbnTlAbnormality> newAbnTlAbnormality,AbnormalityBean abnormalityBean,String flid,String sectionId,AdmTlUsermst createdBy,String ism,String docID,String types,String remarks) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public abstract List<String[]> getMultipleAbngridDetail(CommonFilter commonFilter) throws Exception;
	public List<String[]> getIndividualModifyForm(CommonFilter commonFilter, String setStatus, String pillarType) throws Exception;
	public Workbook IndividualabnExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String reportType)throws Exception;
	public void AbnormalityFormServiceImplJwt(String JwtToken);
	public abstract List<String[]> getMultipleAbnDetail(List<String> keyids) throws Exception;
	public abstract List<String[]> getAbnUpdatedRow(String keyId) throws Exception;

}
