package com.akranta.tpm.service.impl;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AbnormalityRptDao;
import com.akranta.tpm.dao.impl.AbnormalityRptDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.AbnormalityRptService;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.utils.CommonMessage;
public class AbnormalityRptServiceImpl implements AbnormalityRptService {
	private AbnormalityRptDao abnormalityRptDao;
	
	public AbnormalityRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		abnormalityRptDao =  new AbnormalityRptDaoImpl(dbActionTemplate);
	}
	public void AbnormalityRptServiceImplJwt(String JwtToken){
    	try{
    		abnormalityRptDao.AbnTlAbnormalityDaoImplJwt(JwtToken);
//        abnServiceApi = new AbnormalityServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	

	public List<String[]> getAllAbnormality(CommonFilter commonFilter, String type) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Chk in Serv impl"+type);
		return this.abnormalityRptDao.getAbnormality(commonFilter,type);
	}
	
	public List<String[]> getAgeAbnormality(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.abnormalityRptDao.getAgeAbnormalityDao(commonFilter);
	}
	public Workbook abnormalityReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat,String type) throws Exception{
		return this.abnormalityRptDao.getAbnormalityReport(commonFilter,colmodel,rptFormat,type);
	}
	public Workbook abnormalityAgeReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.abnormalityRptDao.getAbnormalityAgeReport(commonFilter,colmodel,rptFormat);
	}


	@Override
	public List<String[]> getAbnTagTrend(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return abnormalityRptDao.getAbnTagTrend(commonFilter);
	}


	@Override
	public Workbook getAbnTagTrendExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		return abnormalityRptDao.getAbnTagTrendExportExcel(commonFilter,tableModel,format);
	}
	@Override
	public Workbook AbnDetailsExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception {
		return this.abnormalityRptDao.getExportExcel(colmodel,format,commonFilter1);
	}

	@Override
	public List<String[]> getAllAbnormalityDetails(CommonFilter commonFilter,String keyid,String columnId,String colIndexName)throws Exception {
		return this.abnormalityRptDao.getAllAbnormalityDetails(commonFilter,keyid,columnId, colIndexName);
	}


	@Override
	public List<String[]> getAbnSummaryGridData(CommonFilter commonFilter) throws Exception{
		return abnormalityRptDao.getAbnSummaryGridData(commonFilter);
	}
	
	@Override
	public Workbook getAbnSummaryGridDataExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception {
		
		return abnormalityRptDao.getAbnSummaryGridDataExportExcel(commonFilter,tableModel,format);
		
	}


}
