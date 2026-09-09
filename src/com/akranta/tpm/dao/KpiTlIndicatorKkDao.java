package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.sql.KpiTlIndicatorDeptLinkSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlIndicator;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.model.KpiTlIndicatorKk;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public interface KpiTlIndicatorKkDao {

	public List<KpiTlIndicatorKk> getKpiTlIndicatorKkValues(KpiTlIndicatorKk kpiTlIndicatorKk) throws Exception;
	public List<KpiTlIndicatorKk> getAllkeyInd(KpiTlIndicatorKk kpiTlIndicatorKk) throws Exception;
	public KpiTlIndicatorKk create(KpiTlIndicatorKk newKpiTlIndicatorKk)throws ValidationExceptions,BusinessApplicationExceptions, Exception;	
	public KpiTlIndicatorKk update(KpiTlIndicatorKk newKpiTlIndicatorKk)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public KpiTlIndicatorKk delete(KpiTlIndicatorKk kpiTlIndicatorKk)throws BusinessApplicationExceptions, Exception;
	public KpiTlIndicator select(KpiTlIndicator kpiTlIndicatorKk)throws Exception;
	public List<KpiTlIndicatorKk> selectList(KpiTlIndicatorKk kpiTlIndicatorKk)throws Exception;
	public  List<String[]>  getSearchNode(String searchNode,String originalId) throws Exception;
	public int getkeyIndLevel(KpiTlIndicatorKk kpiTlIndicatorKk)throws Exception;	
	public int getConfigkeyIndLevel()throws Exception;
	public String getPillarKeyId(String pillarCode)throws Exception;
	public String getSortNo(KpiTlIndicatorKk newKpiTlIndicatorKk)throws Exception;
	public List<String[]> getKPIProd(GridParams gridParams, CommonFilter commonFilter)throws Exception;
	//public List<String[]> getKPIProdData(String indicatorId, GridParams gridParams, String pillCode)throws Exception;
	public KpiTlIndicatorDeptLink createIndicatorDeptLink(
			KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink, String pillCode, String indicatorId,String  deptId, String isIndicatorFactory2, String drillLevel)throws Exception;
	public Workbook KpiExportExcel(net.sf.json.JSONObject tblJSONObj, String format, String compId, String factId, String sectId, String cellId,String drillLevel, String indicatorId, String pillCode,String pillarId,GridParams gridparams)throws Exception;
	public List<String[]> getKpiReport(CommonFilter commonFilter)throws Exception;
	public KpiTlIndicatorKk select(KpiTlIndicatorKk kpiTlIndicatorKk) throws Exception;
	public String validatekeyIndLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink)throws Exception;
	public String validateKeyInactiveListLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception;
	public String validatekeyActiveLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception;
	public com.akranta.tpm.model.KpiTlIndicatorDeptLink deleteDeptLink(
			KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink);
	public String deptid(String kpid,String Flid) throws SQLException;
	public String dapartmentid(String kidlkeyid) throws SQLException;
	public List<String[]> getKPIActiveInactiveProd(GridParams gridParams, CommonFilter commonFilter)throws Exception;
	
	public void KpiTlIndicatorKkDaoImplJwt(String jwtToken);


}

