package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.KaizenReportDao;
import com.akranta.tpm.dao.SuggestionNonJHEspReportDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.KaizenReportDaoImpl;
import com.akranta.tpm.dao.impl.SuggestionNonJHEspReportDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.SuggestionNonJHEspReportService;

public class SuggestionNonJHEspReportServiceImpl implements SuggestionNonJHEspReportService {

private SuggestionNonJHEspReportDao suggReportDao;
	
	public SuggestionNonJHEspReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		suggReportDao =  new SuggestionNonJHEspReportDaoImpl(dbActionTemplate);
	}
	

	public void SuggestionNonJHEspReportServiceImplJwt(String JwtToken){
    	try{
    		suggReportDao.SuggestionNonJHEspReportDaoImplJwt(JwtToken);
    //		serviceApi = new ApplicationMaintananceServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	public List<String[]> getSuggestionSummaryGridData(CommonFilter commonFilter) throws Exception{
		// TODO Auto-generated method stub
		return suggReportDao.getSuggestionSummaryGridData(commonFilter);
	}

	@Override
	public Workbook getSuggestionSummaryGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format)throws Exception {
		// TODO Auto-generated method stub
		return suggReportDao.getSuggestionSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);
	}

}
