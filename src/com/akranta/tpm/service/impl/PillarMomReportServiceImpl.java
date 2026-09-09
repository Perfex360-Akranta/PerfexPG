package com.akranta.tpm.service.impl;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.PillarMomReportDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
//import com.akranta.tpm.dao.impl.MaintActivityDaoImpl;
import com.akranta.tpm.dao.impl.PillarMomReportDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.PillarMomReportService;
import com.akranta.tpm.service.api.ApplicationMaintananceServiceApi;

import net.sf.json.JSONObject;

public class PillarMomReportServiceImpl implements PillarMomReportService {

private PillarMomReportDao pillarMomReportDao;
	
	public PillarMomReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pillarMomReportDao = new PillarMomReportDaoImpl(dbActionTemplate);
	}
	public void PillarMomReportServiceImplJwt(String JwtToken){
    	try{
    		pillarMomReportDao.PillarMomReportDaoImplJwt(JwtToken);
    		//serviceApi = new ApplicationMaintananceServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	public Workbook getPillarMomAttdExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) {
		// TODO Auto-generated method stub
		return pillarMomReportDao.getPillarMomAttdExportExcel(commonFilter,tblJSONObj,format);
	}
	
	@Override
	public List<String[]> getPillarMomAttd(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		try {
			return pillarMomReportDao.getPillarMomAttd(commonFilter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
