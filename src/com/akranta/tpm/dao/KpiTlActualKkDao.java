package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlActualKk;

public interface KpiTlActualKkDao {
	public abstract List<KpiTlActualKk> create(List<KpiTlActualKk> newKpiTlActualKk) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public abstract List<KpiTlActualKk> update(List<KpiTlActualKk> newKpiTlActualKk) throws Exception;
	public abstract List<KpiTlActualKk> delete(List<KpiTlActualKk> newKpiTlActualKk) throws Exception;
	public String getPillarKeyId(String pillarCode)throws Exception;
	public abstract KpiTlActualKk select(KpiTlActualKk newKpiTlActualKk) throws Exception;
	public List<String[]> selectSchedule(KpiTlActualKk newKpiTlActualKk)throws Exception;
	public abstract List<KpiTlActualKk> selectList(KpiTlActualKk newKpiTlActualKk) throws Exception;
	public List<String[]> getCostOfQualitygridData(KpiTlActualKk newKpiTlActualKk, GridParams gridParams, CommonFilter commonFilter)throws Exception;
	public List<String[]> getDatesList(KpiTlActualKk newKpiTlActualKk)throws Exception;		
	public String getStartMonth()throws Exception;
	public abstract List<String[]> getListOfIndicators(CommonFilter comonFilter)throws Exception;
	public abstract List<String[]> getWeeksList(KpiTlActualKk newKpiTlActualKk)throws Exception;
 
	public abstract List<String[]> getKPIRemarks(CommonFilter comonFilter) throws Exception;
	public abstract Workbook getActualKKListExl(KpiTlActualKk newKpiTlActualKk,
			GridParams gridParams, CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public String getFnlnDescription(String flid) throws Exception;

	public abstract List<String[]> getCostOfQualitygridDataNewDM(KpiTlActualKk newKpiTlActualKk, GridParams gridParams,
			CommonFilter commonFilter)throws Exception;

	public abstract List<String[]> getIndicatorRemarks(String indicatorid) throws Exception;
	public abstract List<String[]> getKPIRemarksReport(CommonFilter comonFilter) throws Exception;
	public abstract List<String[]> getKPIkeyid(String kinkid, String flid, String year,
			String freq) throws Exception;
	public abstract String getKPIyear(String year) throws Exception;
	List<String[]> KPIDeviationData(String flid,String year,String CurrDate,String CurrMonthYear,String frequency) throws Exception;
    public List<String[]> getKPIEmpMailIds(String flid,String location,String rolename) throws Exception;
    public String getKPIDeviationCount(String flid,String year,String CurrDate,String CurrMonthYear ,String frequency) throws Exception;
    public List<String[]> getElementId(String loginflid, String loginlevel,
    		 String loginElementid, String empId) throws Exception;
  public  List<String[]> gerpakpidata(CommonFilter commonFilter) throws Exception;
	//List<String[]> getrpakpidata(CommonFilter commonFilter) throws Exception;
	
  
  public abstract void KpiTlActualKkDaoImplJwt(String jwtToken);

}

