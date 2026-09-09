package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.YYFormBean;
import com.akranta.tpm.model.BdmTlMst;
import com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.BdmTlYydonebymst;
import com.akranta.tpm.model.BdmTlYyeffectivemst;
import com.akranta.tpm.model.BdmTlYyproblemattbymst;
import com.akranta.tpm.model.CommonFilter;

public interface WhyWhyAnalysisService {
	public BdmTlWhywhydtl create(BdmTlWhywhydtl bdmTlWhywhydtl) throws ValidationExceptions, Exception;
	public BdmTlWhywhydtl update(BdmTlWhywhydtl bdmTlWhywhydtl) throws ValidationExceptions, Exception;
	public BdmTlWhywhydtl delete(BdmTlWhywhydtl bdmTlWhywhydtl) throws ValidationExceptions, Exception;
	public BdmTlWhywhymst delete(BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public BdmTlWhywhydtl deleteYYDtl(String keyId) throws ValidationExceptions, Exception;	
	public BdmTlWhywhymst create(BdmTlWhywhymst newBdmTlWhywhymst,BdmTlWhywhymst existBdmTlWhywhymst,  YYFormBean yyFormBean ) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public BdmTlWhywhymst update(BdmTlWhywhymst newBdmTlWhywhymst,BdmTlWhywhymst oldBdmTlWhywhymst,  YYFormBean yyFormBean ) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public BdmTlWhywhymst getWWMS(String wwwsKeyid) throws Exception ;
	public String checkCounterMsr() throws Exception;
	public BdmTlWhywhymst getWWMSValues(String refDocId) throws Exception;
	public List<Object> getWWDT(String wwwsKeyid) throws Exception ;
	public List<String[]> getAllwhywhyStd(CommonFilter commonFilter) throws Exception;
	public List<String[]> getRootCause(String openMode)throws Exception;
	public List<String []> getProgramList(String start,String end) throws Exception;
	public List<String[]> getPillar(String yyId,String pillarFlag)throws Exception;
	public List<String[]> getSelectedRootCause(String yyNo)throws Exception;
	public String getDocId(String bdId)throws Exception;
	public String checkMstExist(String refDocId)throws Exception;
	public BdmTlWhywhymst create1(BdmTlWhywhymst newBdmTlWhywhymst,
			BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean) throws Exception;
	public BdmTlWhywhymst update1(BdmTlWhywhymst newBdmTlWhywhymst,
			BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean) throws Exception;
	public BdmTlWhywhymst selectmaskeyid(String maskeyid) throws Exception;
	public Workbook whywhyExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public Workbook whywhyAgeExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getAllWhywhy(CommonFilter commonFilter) throws Exception;
	public List<String[]> getWhywhyAge(CommonFilter commonFilter) throws Exception;
	public BdmTlYyeffectivemst yyEffectivenessCreate(BdmTlYyeffectivemst newBdmTlYyeffectivemst,
			BdmTlYyeffectivemst existBdmTlYyeffectivemst) throws Exception;
	public BdmTlYyeffectivemst yyEffectivenessUpdate(BdmTlYyeffectivemst newBdmTlYyeffectivemst,
			BdmTlYyeffectivemst existBdmTlYyeffectivemst) throws Exception;
	
	public List<String[]> getWhyWhyGenDrillData(CommonFilter commonfilter) throws Exception;
	
	public Workbook getWhyWhyGenDrillDataExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;

	public BdmTlYydonebymst yyDonebyCreate(BdmTlYydonebymst newBdmTlYydonebymst,
			BdmTlYydonebymst existBdmTlYydonebymst) throws Exception;
	
	public String deleteYYDoneBy(String keyId)throws Exception;
	public BdmTlYyproblemattbymst yyProbAttCreate(BdmTlYyproblemattbymst newBdmTlYyproblemattbymst,
			BdmTlYyproblemattbymst existBdmTlYyproblemattbymst)throws Exception;
	public String deleteYYProbAttBy(String keyId)throws Exception;
	public List<String[]> getWhyWhyCountData(CommonFilter commonFilter) throws Exception;
	public Workbook getWhyWhyCountExcel(CommonFilter commonFilter,
				JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getRootCauseList(CommonFilter commonFilter) throws Exception;
	//mano
	public Workbook getWhyWhyCountRootCauseExcel(CommonFilter commonFilter,
	        JSONObject tblJSONObj, String format) throws Exception;

	public List<String[]> getCounterMeasureList(CommonFilter commonFilter) throws Exception;
	public Workbook getWhyWhyCountCounterExcel(CommonFilter commonFilter,
	        JSONObject tblJSONObj, String format) throws Exception;

	//public String getEmailId(String empmKeyId) throws Exception;
	public List<String[]> getEmailIds(String whywhyNo)throws Exception;
	public List<String[]> getPcEmailIds(String whywhyNo)throws Exception;

	public List<String[]> getWhyReleatedFileManager(String whywhyNo) throws Exception;
	public String getCCEmailId(String empmKeyId)throws Exception;
	public void WhywhyAnalysisServiceImplJwt(String JwtToken);
	public abstract String getWhywhyTrade(String tradeId) throws Exception;
	public abstract String getWhywhyPillar(String pillarId) throws Exception;
}
