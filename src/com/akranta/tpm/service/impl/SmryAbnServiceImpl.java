package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.SmryAbnDao;
import com.akranta.tpm.dao.impl.SmryAbnDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.SmryAbnService;


public class SmryAbnServiceImpl implements SmryAbnService {
	
private SmryAbnDao smryAbnDao;
	
	public SmryAbnServiceImpl(DBActionTemplate dbActionTemplate)
	{
		smryAbnDao =  new SmryAbnDaoImpl(dbActionTemplate);
	}
	
	public void SmryAbnServiceImplJwt(String JwtToken){
    	try{
    		smryAbnDao.SmryAbnDaoImplJwt(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<String []> getAllAbnormalities(CommonFilter commonFilter,String checkField) throws Exception
	{
		return this.smryAbnDao.getAllSmryAbns(commonFilter,checkField);
	}

	public Workbook smryAbnormalityReportExportExcel(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat,String checkField) throws Exception {
		return this.smryAbnDao.smryAbnormalityReportExportExcel(commonFilter,colmodel,rptFormat,checkField);
	}

	@Override
	public List<String[]> getAllAbnEmp(CommonFilter commonFilter)throws Exception {
		
			return this.smryAbnDao.getAllSmryAbnEmp(commonFilter);

	}
	@Override
	public List<String[]> getAllManager(CommonFilter commonFilter)throws Exception {
		
			return this.smryAbnDao.getAllManager(commonFilter);

	}

	@Override
	public Workbook AbnsmryEmpReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat)	throws Exception {
		return this.smryAbnDao.AbnsmryEmpReportExportExcel(commonFilter,colmodel,rptFormat);
	}
	
	@Override
	public Workbook ManagerExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat)	throws Exception {
		return this.smryAbnDao.ManagerExportExcel(commonFilter,colmodel,rptFormat);
	}


	@Override
	public List<String[]> getpiechart(CommonFilter piechrtCommonFilter, String flag) throws Exception  {
		// TODO Auto-generated method stub
		return this.smryAbnDao.getpiechart(piechrtCommonFilter,flag);
	}
}
