
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.KaizenReportDao;
import com.akranta.tpm.dao.impl.KaizenReportDaoImpl;
 //import com.akranta.tpm.model.MachineReportModel;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.KaizenReportService;


public  class KaizenReportServiceImpl implements KaizenReportService {

private KaizenReportDao kaizenReportDao;
	
	public KaizenReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		kaizenReportDao =  new KaizenReportDaoImpl(dbActionTemplate);
	}
	
	public void KaizenReportServiceImplJwt(String JwtToken){
    	try{
    		kaizenReportDao.KaizenReportDaoImplJwt(JwtToken);
    //		serviceApi = new ApplicationMaintananceServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	
	public List<String[]> getAllKaizenReport( ) throws Exception
	{
		return this.kaizenReportDao.getKaizenReport();
	}

	@Override
	public List<String[]> getJHKaizenMonthwise(CommonFilter commonFilter,String deptWise,String rptNmae
			) throws Exception {
		// TODO Auto-generated method stub
		return kaizenReportDao.getJHKaizenMonthwise(commonFilter,deptWise,rptNmae);
	}

	@Override
	public Workbook jhKaizenMonwiseExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format, String deptWise,String rptName)
			throws Exception {
		// TODO Auto-generated method stub
		return kaizenReportDao.jhKaizenMonwiseExportExcel(commonFilter,tblJSONObj,format,deptWise,rptName);
	}

	
	//kaizen per persons
	
	@Override
	public List<String[]> getAllKaizenPerPerson(CommonFilter commonFilter)throws Exception {
		return this.kaizenReportDao.getAllKaizenPerPerson(commonFilter);
	}

	public List<String[]> getAllKaizenNosPerPerson(CommonFilter commonFilter)throws Exception {
		return this.kaizenReportDao.getAllKaizenNosPerPerson(commonFilter);
	}
	@Override
	public Workbook perPersonKaizenExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.kaizenReportDao.perPersonKaizenExportExcel(commonFilter,colModel,rptFormat);
	}


	@Override
	public List<String[]> getKaizenSummaryGridData(CommonFilter commonFilter) throws Exception{
		return kaizenReportDao.getKaizenSummaryGridData(commonFilter);
	}
	
	@Override
	public Workbook getKaizenSummaryGridDataExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception {
		
		return kaizenReportDao.getKaizenSummaryGridDataExportExcel(commonFilter,tableModel,format);
		
	}
	
	@Override
	public List<String[]> getKaizenSuggestionSummaryGridData(CommonFilter commonFilter) throws Exception{
		return kaizenReportDao.getKaizenSuggestionSummaryGridData(commonFilter);
	}
	
	@Override
	public Workbook getKaizenSuggestionSummaryGridDataExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception {
		
		return kaizenReportDao.getKaizenSuggestionSummaryGridDataExportExcel(commonFilter,tableModel,format);
		
	}

	@Override
	public List<String[]> getKaizenSynopsisData(CommonFilter commonFilter)
			throws Exception {
		
		return kaizenReportDao.getKaizenSynopsisData(commonFilter);
	}

	@Override
	public Workbook getKaizenSynopsisDataExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		
		return kaizenReportDao.getKaizenSynopsisDataExportExcel(commonFilter, tableModel, format);
	}

	@Override
	public List<String[]> getSuggVsKaizenData(CommonFilter commonFilter) throws Exception{
		return kaizenReportDao.getSuggVsKaizenData(commonFilter);
	}

	@Override
	public Workbook getSuggestionAccKaizenExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception {
		
		return kaizenReportDao.getSuggestionAccKaizenExportExcel(commonFilter,tableModel,format);
		
	}
	
	@Override
	public List<String[]> getKaizenBenefitData(CommonFilter commonFilter)
			throws Exception {
		
		return kaizenReportDao.getKaizenBenefitData(commonFilter);
	}
	
	@Override
	public List<String[]> KaizenBenefitListGraph(CommonFilter commonFilter,String rowid)
			throws Exception {
		
		return kaizenReportDao.KaizenBenefitListGraph(commonFilter,rowid);
	}
	
	@Override
	public Workbook getKaizenBenefitExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception {
		
		return kaizenReportDao.getKaizenBenefitExportExcel(commonFilter,tableModel,format);
		
	}


}