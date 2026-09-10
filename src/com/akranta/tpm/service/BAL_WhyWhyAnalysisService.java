package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.bean.YYFormBean;
import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.BAL_BdmTlWhywhydtl;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.BAL_BdmTlYydonebymst;
import com.akranta.tpm.model.BAL_BdmTlYyeffectivemst;
import com.akranta.tpm.model.CommonFilter;

public interface BAL_WhyWhyAnalysisService {
	public BAL_BdmTlWhywhydtl create(BAL_BdmTlWhywhydtl bdmTlWhywhydtl) throws ValidationExceptions, Exception;
	public BAL_BdmTlWhywhydtl update(BAL_BdmTlWhywhydtl bdmTlWhywhydtl) throws ValidationExceptions, Exception;
	public BAL_BdmTlWhywhydtl delete(BAL_BdmTlWhywhydtl bdmTlWhywhydtl) throws ValidationExceptions, Exception;
	public BAL_BdmTlWhywhymst delete(BAL_BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public BAL_BdmTlWhywhydtl deleteYYDtl(String keyId) throws ValidationExceptions, Exception;	
	public BAL_BdmTlWhywhymst create(BAL_BdmTlWhywhymst newBdmTlWhywhymst,BAL_BdmTlWhywhymst existBdmTlWhywhymst,  YYFormBean yyFormBean ) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public BAL_BdmTlWhywhymst update(BAL_BdmTlWhywhymst newBdmTlWhywhymst,BAL_BdmTlWhywhymst oldBdmTlWhywhymst,  YYFormBean yyFormBean ) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public BAL_BdmTlWhywhymst getWWMS(String wwwsKeyid) throws Exception ;
	public String checkCounterMsr() throws Exception;
	public BAL_BdmTlWhywhymst getWWMSValues(String refDocId) throws Exception;
	public List<Object> getWWDT(String wwwsKeyid) throws Exception ;
	public List<String[]> getAllwhywhyStd(CommonFilter commonFilter) throws Exception;
	public List<String[]> getRootCause(String openMode)throws Exception;
	public List<String []> getProgramList(String start,String end) throws Exception;
	public List<String[]> getPillar(String yyId,String pillarFlag)throws Exception;
	public List<String[]> getSelectedRootCause(String yyNo)throws Exception;
	public String getDocId(String bdId)throws Exception;
	public String checkMstExist(String refDocId)throws Exception;
	public BAL_BdmTlWhywhymst create1(BAL_BdmTlWhywhymst newBdmTlWhywhymst,
			BAL_BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean) throws Exception;
	public BAL_BdmTlWhywhymst update1(BAL_BdmTlWhywhymst newBdmTlWhywhymst,
			BAL_BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean) throws Exception;
	public BAL_BdmTlWhywhymst selectmaskeyid(String maskeyid) throws Exception;
	public Workbook whywhyExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getAllWhywhy(CommonFilter commonFilter) throws Exception;
	public BAL_BdmTlYyeffectivemst yyEffectivenessCreate(BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemst,
			BAL_BdmTlYyeffectivemst existBdmTlYyeffectivemst) throws Exception;
	public BAL_BdmTlYyeffectivemst yyEffectivenessUpdate(BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemst,
			BAL_BdmTlYyeffectivemst existBdmTlYyeffectivemst) throws Exception;
	
	public List<String[]> getWhyWhyGenDrillData(CommonFilter commonfilter) throws Exception;
	
	public Workbook getWhyWhyGenDrillDataExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;

	public BAL_BdmTlYydonebymst yyDonebyCreate(BAL_BdmTlYydonebymst newBdmTlYydonebymst,
			BAL_BdmTlYydonebymst existBdmTlYydonebymst) throws Exception;
	
	public String deleteYYDoneBy(String keyId)throws Exception;
	public String getYYKeyId(String refDocId) throws Exception;
}
