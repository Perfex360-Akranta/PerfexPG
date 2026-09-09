package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.KpiTlActualKkBean;
import com.akranta.tpm.bean.KpiTlKpiremarksBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlActualKk;
import com.akranta.tpm.model.KpiTlKpiremarks;

public interface KpiTlActualKkService 
{
	public List<KpiTlActualKk> create(List<KpiTlActualKk> newKpiTlActualKk,List<KpiTlActualKk> existKpiTlActualKk,
			KpiTlActualKkBean newKpiTlActualKkBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	
	public List<KpiTlKpiremarks> createRemarks(List<KpiTlKpiremarks> newKpiTlKpiremarks,List<KpiTlKpiremarks> existKpiTlKpiremarks,
			KpiTlKpiremarksBean kpiTlKpiremarksBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	
	public List<KpiTlActualKk> update(List<KpiTlActualKk> newKpiTlActualKk,List<KpiTlActualKk> existKpiTlActualKk,
			KpiTlActualKkBean newKpiTlActualKkBean)throws Exception;
	public List<KpiTlActualKk> delete(List<KpiTlActualKk>  newKpiTlActualKk)throws Exception;
	public String getPillarKeyId(String pillarCode)throws Exception;
	public KpiTlActualKk select(KpiTlActualKk newKpiTlActualKk)throws Exception;
	public KpiTlKpiremarks selectRemarks(KpiTlKpiremarks newKpiTlKpiremarks)throws Exception;
	public List<String[]> selectSchedule(KpiTlActualKk newKpiTlActualKk)throws Exception;
	public List<KpiTlActualKk> selectList(KpiTlActualKk newKpiTlActualKk)throws Exception;	
	public List<ComboBox> getCostAreaComboList()throws Exception ;
	public List<ComboBox> getParentComboList()throws Exception ;
	public List<String[]> getCostOfQualitygridData(KpiTlActualKk newKpiTlActualKk, GridParams gridParams, CommonFilter commonFilter)throws Exception ;	
	public List<String[]> getDatesList(KpiTlActualKk newKpiTlActualKk)throws Exception ;
	public List<String[]> getListOfIndicators(CommonFilter commonFilter)throws Exception ;
	public List<String[]> getWeeksList(KpiTlActualKk newKpiTlActualKk)throws Exception ; 
	public List<String[]> getKPIRemarks(CommonFilter comonFilter) throws Exception ;
	public Workbook getActualKKListExl(KpiTlActualKk newKpiTlActualKk,GridParams gridParams, CommonFilter commonFilter,
			JSONObject tblJSONObj, String format)throws Exception ;
	public String getFnlnDescription(String flid) throws Exception;


	public List<String[]> getCostOfQualitygridDataNewDM(KpiTlActualKk kpiTlActualKk, GridParams gridParams,
			CommonFilter commonFilter)throws Exception;

    public List<String[]> getIndicatorRemarks(String indicatorid) throws Exception;
    public List<String[]> getKPIRemarksReport(CommonFilter comonFilter) throws Exception ;

	public List<String[]> getKPIkeyid(String kinkid, String flid, String year,
			String freq) throws Exception ;
    public String getKPICalendaryr(String year) throws Exception ;
    public Workbook KPIDeviationExcelView(String flid, String year,String CurrDate,String CurrMonthYear,String frequency,String format,String path)throws Exception;
    public List<String[]> getKPIEmpMailIds(String flid,String location,String rolename) throws Exception;
    public String getKPIDeviationCount(String flid,String year,String CurrDate,String CurrMonthYear ,String frequency) throws Exception;
    public List<String[]> getElementId(String loginflid, String loginlevel,
    		 String loginElementid, String empId) throws Exception;
    public List<String[]> getrpakpidata(CommonFilter commonFilter) throws Exception;
    
    public void KpiTlActualKkServiceImplJwt(String JwtToken);
}