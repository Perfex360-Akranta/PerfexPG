package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AbnTlAbnhistorydtl;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AbnTlDtl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.WomTlWomst;

public interface AbnTlAbnormalityDao {

	public abstract AbnTlAbnormality create(AbnTlAbnormality abnTlAbnormality) throws Exception,BusinessApplicationExceptions;
	public abstract AbnTlAbnormality update(AbnTlAbnormality abnTlAbnormality,WomTlWomst womTlWomst,AbnTlAbnhistorydtl abnTlAbnhistorydtl) throws Exception;
	public abstract AbnTlAbnormality delete(AbnTlAbnormality abnTlAbnormality) throws Exception;
	public abstract AbnTlAbnormality select(String AbnmTagno) throws Exception;
	public abstract List<String[]> getModifyFormDao(CommonFilter commonFilter, String setStatus, String pillarType) throws Exception;
	public abstract AbnTlAbnormality abnformfill(String abnId);
	public abstract String getShift(String flid, String time)throws Exception;
	public List<String[]> getAbnormalityDetails(String keyId) throws Exception ;
	public List<String> getTgtDateFromConfig()throws Exception ;
	public Workbook abnExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String reportType)throws Exception ;
	public String getTagCode(String tagclassId)throws Exception;
	public String getAbnTypeFlag(String abtmKeyid)throws Exception;
	public AbnTlDtl selectHseData(String abdKeyId,String flag)throws Exception;
	public List<String[]> getAbnormalityDetails(String keyId,GridParams gridParams) throws Exception;
	public String getAbnormalityDetailsCount(String keyId,GridParams gridParams) throws Exception;
	public List<String[]> getAllAbnormalityDetails(CommonFilter commonFilter, String keyid, String columnId)throws Exception;
	public Workbook getExportExcel(JSONObject colModel, String format,GridParams gridParams, String keyId, String count) throws Exception;
	public abstract Workbook getHSEAbnExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception;
	public String checkTag() throws Exception;
	public String checkGen() throws Exception;
	public String checkDirectEntry(String woID)throws Exception;
	public String showAbnQuery()throws Exception;
	public List<AbnTlAbnormality> updateBulkTagRemoval(List<AbnTlAbnormality> newAbnTlAbnormality)throws Exception;
	public String getElementID(String abnmEquipmentid)throws Exception;
	public  List<String[]> getRepeatedAbn(CommonFilter commonFilter)throws Exception;
	public  List<String[]> getAbnAllocation(CommonFilter commonFilter) throws Exception;
	public  List<AbnTlAbnormality> updateAbnAllocation(List<AbnTlAbnormality> newAbnTlAbnormality) throws Exception;
	public abstract Workbook getAbnAllocationExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)throws Exception;
	public String getAbnTradeValues(String abnmRespons) throws Exception;
	public abstract AbnTlAbnormality updateabncomp(AbnTlAbnormality newAbnTlAbnormality)throws Exception;
	public abstract List<String[]> FillabnpopData(String keyid)throws Exception;
	public abstract String getServiceCount(CommonFilter commonFilter) throws Exception;
	public String getSubType(String typeId)throws Exception;
	public List<AbnTlAbnormality> Multiplecreate(List<AbnTlAbnormality> list) throws Exception,BusinessApplicationExceptions;
	public List<String[]> getMultipleAbngridDetail(CommonFilter commonFilter) throws Exception;
	public List<String[]> getIndividualModifyForm(CommonFilter commonFilter, String setStatus, String pillarType) throws Exception;
	public Workbook IndividualabnExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String reportType)throws Exception;
	public AbnTlAbnormality createAbnormalitypsi(AbnTlAbnormality abnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality, AbnormalityBean abnormalityFormBean)throws Exception;
	public abstract AbnTlAbnormality createAbnormalitypsiNew(AbnTlAbnormality abnTlAbnormality) throws Exception;
	public abstract AbnTlAbnormality updateAbnm(AbnTlAbnormality abnTlAbnormality) throws Exception;
	public abstract void AbnTlAbnormalityDaoImplJwt(String jwtToken);
	public abstract List<String[]> getMultipleAbnDetail(List<String> keyids) throws Exception;
	public abstract List<String[]> getAbnUpdatedRow(String keyId) throws Exception;
	
	
}

