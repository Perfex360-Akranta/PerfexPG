package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AbnStraficationDao;
import com.akranta.tpm.dao.impl.AbnStraficationDaoImpl;
import com.akranta.tpm.dao.impl.AbnormalityDaoImpl;



import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.AbnStraficationService;
import com.akranta.tpm.service.api.AbnormalityServiceApi;


public class AbnStraficationServiceImpl implements AbnStraficationService {
	private AbnStraficationDao abnStraficationDao;
	
	public AbnStraficationServiceImpl(DBActionTemplate dbActionTemplate)
	{
		abnStraficationDao =  new AbnStraficationDaoImpl(dbActionTemplate);
	}
	
	public void AbnStraficationServiceImplJwt(String JwtToken){
    	try{
    		abnStraficationDao.AbnStraficationDaoImplJwt(JwtToken);
        
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<String[]> getAllAbnStrafication(CommonFilter commonFilter)throws Exception {
	
		return this.abnStraficationDao.getAbnStrafication(commonFilter);
	}

	@Override
	public Workbook AbnStartificationReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat)	throws Exception {
		return this.abnStraficationDao.AbnStartificationReportExportExcel(commonFilter,colmodel,rptFormat);
	}

	public List<String[]> getAlStrafication(CommonFilter commonFilter)throws Exception {
		return this.abnStraficationDao.getAlStraficationDD(commonFilter);
	}
	
	public Workbook StartificationReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat)	throws Exception {
		return this.abnStraficationDao.AbnStartificationReportExportExcel(commonFilter,colmodel,rptFormat);
}

	@Override
	public List<String[]> getAllAbnStraficationType(CommonFilter commonFilter) throws Exception {
		return this.abnStraficationDao.getAbnStraficationType(commonFilter);
		}

	@Override
	public List<String[]> getAllAbnStraficationImpact(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.abnStraficationDao.getAbnStraficationImpact(commonFilter);
	}
	public Workbook StartificationIdeVsComExportExcel(CommonFilter commonFilter, JSONObject colmodel, String format)	throws Exception {
		return this.abnStraficationDao.StartificationIdeVsComExportExcel(commonFilter,colmodel,format);
}  
	
	}
