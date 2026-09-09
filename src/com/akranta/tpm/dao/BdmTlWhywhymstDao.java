package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.BdmTlYydonebymst;
import com.akranta.tpm.model.BdmTlYyeffectivemst;
import com.akranta.tpm.model.BdmTlYyproblemattbymst;
import com.akranta.tpm.model.CommonFilter;

public interface BdmTlWhywhymstDao {

	public abstract BdmTlWhywhymst create(BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,Exception;
	public abstract BdmTlWhywhymst update(BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,Exception;
	public abstract BdmTlWhywhymst delete(BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,Exception;
	public BdmTlWhywhydtl deleteYYDtl(String keyId) throws ValidationExceptions, Exception;
	public BdmTlWhywhymst getWWMS(String wwwsKeyid) throws Exception ;
	public String checkCounterMsr() throws Exception;
	public BdmTlWhywhymst getWWMSValues(String refDocId) throws Exception;
	public List<Object> getWWDT(String wwwsKeyid) throws Exception ;
	public List<String[]> getWhywhyStd(CommonFilter commonFilter)throws Exception;
	public List<String[]> getRootCause(String openMode)throws Exception;
	public List<String[]> getPillar(String yyId,String pillarFlag)throws Exception;
	public List<String[]> getSelectedRootCause(String yyNo)throws Exception;
	public List<String []> getProgramList(String start,String end) throws Exception;
	public String getDocId(String bdId)throws Exception;
	public BdmTlWhywhymst select(String keyid) throws Exception;
	public String checkMstExist(String refDocNo) throws Exception;
	public abstract BdmTlWhywhymst create1(BdmTlWhywhymst newBdmTlWhywhymst) throws Exception;
	public abstract BdmTlWhywhymst selectmaskeyid(String maskeyid)throws Exception;
	public abstract Workbook whywhyExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public abstract Workbook whywhyAgeExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public abstract List<String[]> getAllWhywhy(CommonFilter commonFilter) throws Exception;
	
	public abstract List<String[]> getWhywhyAge(CommonFilter commonFilter) throws Exception;
	public  BdmTlYyeffectivemst yyEffectivenessCreate(BdmTlYyeffectivemst newBdmTlYyeffectivemst);
	public  BdmTlYyeffectivemst yyEffectivenessUpdate(BdmTlYyeffectivemst newBdmTlYyeffectivemst);
	public List<String[]> getWhyWhyGenDrillData(CommonFilter commonfilter) throws Exception;
	public abstract Workbook getWhyWhyGenDrillDataExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	public BdmTlYydonebymst yyDonebyCreate(BdmTlYydonebymst newBdmTlYydonebymst) throws Exception;
	public String deleteYYDoneBy(String keyId)throws Exception ;
	public abstract BdmTlYyproblemattbymst yyProbattCreate(BdmTlYyproblemattbymst newBdmTlYyproblemattbymst)throws Exception;
	public abstract String deleteYYProbAttBy(String keyId) throws Exception;
	public  List<String[]> getWhyWhyCountData(CommonFilter commonfilter)throws Exception;
	public abstract Workbook getWhyWhyCountExcel(CommonFilter commonFilter,
				JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getRootCauseList(CommonFilter commonFilter) throws Exception;
	//mano
	public abstract Workbook getWhyWhyCountRootCauseExcel(CommonFilter commonFilter,
	        JSONObject tblJSONObj, String format) throws Exception;

	public List<String[]> getCounterMeasureList(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getWhyWhyCountCounterExcel(CommonFilter commonFilter,
	        JSONObject tblJSONObj, String format) throws Exception;
	public abstract String getCCEmailId(String empmKeyId) throws Exception;
	public abstract List<String[]> getEmailIds(String whyWhyNo) throws Exception;
	public abstract List<String[]> getPcEmailIds(String whywhyNo) throws Exception;
	public abstract void BdmTlWhywhymstDaoImplJwt(String jwtToken);
	public abstract String getWhywhyTrade(String tradeId) throws Exception;
	public abstract String getWhywhyPillar(String pillaeId) throws Exception;

}

