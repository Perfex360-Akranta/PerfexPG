package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlIndicator;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.model.KpiTlIndicatorKk;

public interface KpiTlIndicatorKkService {
	public List<KpiTlIndicatorKk> getKpiTlIndicatorKkValues(KpiTlIndicatorKk KpiTlIndicatorKk) throws Exception;	
	public List<KpiTlIndicatorKk> getAllkeyInd(KpiTlIndicatorKk KpiTlIndicatorKk) throws Exception;
	public KpiTlIndicatorKk create(KpiTlIndicatorKk newKpiTlIndicatorKk,KpiTlIndicatorKk oldKpiTlIndicatorKk)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public KpiTlIndicatorKk update(KpiTlIndicatorKk newKpiTlIndicatorKk,KpiTlIndicatorKk oldKpiTlIndicatorKk)throws ValidationExceptions,BusinessApplicationExceptions, Exception;		
	public KpiTlIndicatorKk delete(KpiTlIndicatorKk newKpiTlIndicatorKk)throws BusinessApplicationExceptions, Exception;
	public KpiTlIndicatorDeptLink deleteDeptLink(KpiTlIndicatorDeptLink newKpiTlIndicatorDeptLink)throws BusinessApplicationExceptions, Exception;
	
	public KpiTlIndicator select(KpiTlIndicator newKpiTlIndicatorKk)throws Exception;	
	public KpiTlIndicatorKk select(KpiTlIndicatorKk newKpiTlIndicatorKk)throws Exception;
	public List<KpiTlIndicatorKk> selectList(KpiTlIndicatorKk newKpiTlIndicatorKk)throws Exception;	
	public List<String[]> getSearchNode(String searchNode,String originalId) throws Exception;
	public String validatekeyIndLevel(KpiTlIndicatorKk newKpiTlIndicatorKk)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public String validateDelkeyIndLevel(KpiTlIndicatorKk newKpiTlIndicatorKk)throws Exception;	
	public List<ComboBox> getParentComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getUomComboList(ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getCostAreaComboList(ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getDeptComboList(ComboFilter comboFilter)throws Exception;	
	public List<ComboBox> getIndicatorCombo(String type,ComboFilter comboFilter) throws Exception ;	
	public String getPillarKeyId(String pillarCode)throws Exception;
	public String getSortNo(KpiTlIndicator kpiTlIndicatorKk)throws Exception;
	public List<ComboBox> getIndicatorComboList(CommonFilter commonFilter, String pillCode, ComboFilter comboFilter)throws Exception;
	public List<String[]> getKPIProd( GridParams gridParams, CommonFilter commonFilter)throws Exception;
	//public List<String[]> getKPIProdData(String indicatorId, GridParams gridParams, String pillCode)throws Exception;
	public KpiTlIndicatorDeptLink createPillFactLink(
			KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink,String ccno,String pillCode,String drillLevel,String indicatorId,String deptId,String isIndicatorFactory)throws Exception;
	public Workbook KPIProdFactExportExcel(net.sf.json.JSONObject tblJSONObj, String format, String compId, String factId,String sectId, String cellId, String drillLevel, String indicatorId, String pillCode, String pillarId,GridParams gridparams)throws Exception;	
//new Indcator
	public List<String[]> getKpiReport(CommonFilter commonFilter)	throws Exception;
	public KpiTlIndicator createKPI(KpiTlIndicator newKpiTlIndicator,KpiTlIndicator existKpiTlIndicator)throws Exception;
	public KpiTlIndicator updateKPI(KpiTlIndicator newKpiTlIndicator,KpiTlIndicator existKpiTlIndicator) throws Exception;
	public String getLocation(String flId)throws Exception;
	public String validatekeyIndLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception;
	public String validateKeyInactiveListLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception;
	public String validatekeyActiveLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception;
	public String deptid(String kpid,String Flid) throws SQLException;
	public String dapartmentid(String kidlkeyid) throws SQLException;
	public List<String[]> getKPIActiveInactiveProd(GridParams gridParams, CommonFilter commonFilter)throws Exception;
	
    public void KpiTlIndicatorKkServiceImplJwt(String JwtToken);


}
