package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.KaizenRewardReportDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.KaizenRewardReportDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.KaizenRewardReportService;

public class KaizenRewardReportServiceImpl implements KaizenRewardReportService {

	
	
private KaizenRewardReportDao kaizenRewardReportDao;
	
	public KaizenRewardReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		kaizenRewardReportDao =  new KaizenRewardReportDaoImpl(dbActionTemplate);
	}
	
	public void KaizenRewardReportServiceImplJwt(String JwtToken){
    	try{
    		kaizenRewardReportDao.KaizenRewardReportDaoImplJwt(JwtToken);
    		//machinemasterserviceapi = new MachineMasterServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<String[]> getKaizenRewardGridData(CommonFilter commonFilter) throws IOException, SQLException, Exception {
		// TODO Auto-generated method stub
		return kaizenRewardReportDao.getKaizenRewardGridData(commonFilter);
	}

	@Override
	public Workbook getKaizenRewardGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws IOException, SQLException {
		// TODO Auto-generated method stub
		return kaizenRewardReportDao.getKaizenRewardGridDataExportExcel(commonFilter,tblJSONObj,format);
	}

	@Override
	public List<String[]> getKaizenPendingGridData(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return kaizenRewardReportDao.getKaizenPendingGridData(commonFilter);
	}

	@Override
	public Workbook getKaizenPendingGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		// TODO Auto-generated method stub
		return kaizenRewardReportDao.getKaizenPendingGridDataExportExcel(commonFilter,  tblJSONObj,  format);
	}

	@Override
	public List<String[]> getPendingKaizenReport(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return kaizenRewardReportDao.getPendingKaizenReport(commonFilter);
	}

	@Override
	public Workbook getDtlKaizenPendingGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		// TODO Auto-generated method stub
		return kaizenRewardReportDao.getDtlKaizenPendingGridDataExportExcel(commonFilter,  tblJSONObj,  format);
	}
	
}
