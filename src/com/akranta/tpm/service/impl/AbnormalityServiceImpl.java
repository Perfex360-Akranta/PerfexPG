package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AbnormalityDao;

import com.akranta.tpm.dao.impl.AbnormalityDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.AbnormalityService;

public class AbnormalityServiceImpl implements AbnormalityService {
	private AbnormalityDao abnormalityDao;
	
	public AbnormalityServiceImpl(DBActionTemplate dbActionTemplate)
	{
		abnormalityDao =  new AbnormalityDaoImpl(dbActionTemplate);
	}
	
	public void AbnormalityServiceImplJwt(String JwtToken){
    	try{
    		abnormalityDao.AbnormalityDaoImplJwt(JwtToken);
//        abnServiceApi = new AbnormalityServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	

	public List<String[]> getAllAbnormality(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.abnormalityDao.getAbnormality(commonFilter);
	}
	public List<String []> getAbnCumulative(CommonFilter commonFilter)  throws Exception{
		return this.abnormalityDao.getAbnCumulative(commonFilter);
	}
	
	@Override
	public List<String[]> getAbnjh(CommonFilter commonFilter) throws Exception {
		return this.abnormalityDao.getAbnjh(commonFilter);
	}
	
	@Override
	public List<String[]> getAbnjhgraph(CommonFilter commonFilter)throws Exception {
		return this.abnormalityDao.getAbnjhgraph(commonFilter);
	}
	public Workbook AbnormalityReportExportExcel(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat) throws Exception {
		return this.abnormalityDao.AbnormalityReportExportExcel(commonFilter,colmodel,rptFormat);
	}


	@Override
	public Workbook AbnjhReportExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String rptformat) throws Exception {
		return this.abnormalityDao.AbnjhReportExportExcel(commonFilter,colmodel,rptformat);
	}


	@Override
	public List<String[]> getAbnIdentifiedCompleted(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.abnormalityDao.getAbnIdentifiedCompleted(commonFilter);
	}


	@Override
	public Workbook AbnormalityIdentifiedExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		// TODO Auto-generated method stub
		return this.abnormalityDao.AbnormalityIdentifiedExportExcel(commonFilter,tblJSONObj,format);
	}




}
