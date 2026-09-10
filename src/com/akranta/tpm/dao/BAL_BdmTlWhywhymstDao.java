package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.BAL_BdmTlWhywhydtl;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.BAL_BdmTlYydonebymst;
import com.akranta.tpm.model.BAL_BdmTlYyeffectivemst;
import com.akranta.tpm.model.CommonFilter;

public interface BAL_BdmTlWhywhymstDao {

	public abstract BAL_BdmTlWhywhymst create(BAL_BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,Exception;
	public abstract BAL_BdmTlWhywhymst update(BAL_BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,Exception;
	public abstract BAL_BdmTlWhywhymst delete(BAL_BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,Exception;
	public BAL_BdmTlWhywhydtl deleteYYDtl(String keyId) throws ValidationExceptions, Exception;
	public BAL_BdmTlWhywhymst getWWMS(String wwwsKeyid) throws Exception ;
	public String checkCounterMsr() throws Exception;
	public BAL_BdmTlWhywhymst getWWMSValues(String refDocId) throws Exception;
	public List<Object> getWWDT(String wwwsKeyid) throws Exception ;
	public List<String[]> getWhywhyStd(CommonFilter commonFilter)throws Exception;
	public List<String[]> getRootCause(String openMode)throws Exception;
	public List<String[]> getPillar(String yyId,String pillarFlag)throws Exception;
	public List<String[]> getSelectedRootCause(String yyNo)throws Exception;
	public List<String []> getProgramList(String start,String end) throws Exception;
	public String getDocId(String bdId)throws Exception;
	public BAL_BdmTlWhywhymst select(String keyid) throws Exception;
	public String checkMstExist(String refDocNo) throws Exception;
	public abstract BAL_BdmTlWhywhymst create1(BAL_BdmTlWhywhymst newBdmTlWhywhymst) throws Exception;
	public abstract BAL_BdmTlWhywhymst selectmaskeyid(String maskeyid)throws Exception;
	public abstract Workbook whywhyExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public abstract List<String[]> getAllWhywhy(CommonFilter commonFilter) throws Exception;
	public  BAL_BdmTlYyeffectivemst yyEffectivenessCreate(BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemst);
	public  BAL_BdmTlYyeffectivemst yyEffectivenessUpdate(BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemst);
	public List<String[]> getWhyWhyGenDrillData(CommonFilter commonfilter) throws Exception;
	public abstract Workbook getWhyWhyGenDrillDataExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;

	public BAL_BdmTlYydonebymst yyDonebyCreate(BAL_BdmTlYydonebymst newBdmTlYydonebymst) throws Exception;
	public String deleteYYDoneBy(String keyId)throws Exception ;
	public abstract String getYYKeyId(String refDocId) throws Exception;
}

