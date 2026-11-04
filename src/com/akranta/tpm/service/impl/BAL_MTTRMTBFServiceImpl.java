package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.dao.BAL_MTTRMTBFDao;
import com.akranta.tpm.dao.impl.BAL_MTTRMTBFDaoImpl;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_MTTRMTBFService;

public class BAL_MTTRMTBFServiceImpl implements BAL_MTTRMTBFService{

	private BAL_MTTRMTBFDao mTTRMTBFDao;
	
	
	public BAL_MTTRMTBFServiceImpl(DBActionTemplate dbActionTemplate)
	{
		mTTRMTBFDao = new BAL_MTTRMTBFDaoImpl(dbActionTemplate);
	
	}
	
	public void BAL_MTTRMTBFServiceImplJwt(String JwtToken){
    	try{
    		mTTRMTBFDao.BAL_MTTRMTBFDaoImplJwt(JwtToken);
    		
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }


	
	public List<String[]> getAllMTTRMTBF(CommonFilter commonFilter)	throws Exception {		
		return this.mTTRMTBFDao.getAllMTTRMTBF( commonFilter);
	}



	public Workbook mttrExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat,String type) throws Exception {
		return this.mTTRMTBFDao.getMTTRMTBFExl( commonFilter,colmodel,rptFormat,type);
	}



	public List<String[]> getAllMTBF(CommonFilter commonFilter)	throws Exception {
		return this.mTTRMTBFDao.getAllMTBF(commonFilter);
	}



	@Override
	public void getRefresh(CommonFilter commonFilter) throws Exception {
		this.mTTRMTBFDao.getRefresh(commonFilter);
		
	}



	@Override
	public List<String[]> getchart1(String filterString, String month) throws Exception 
	{
		// TODO Auto-generated method stub
		return  this.mTTRMTBFDao.getchart1(filterString,month);
	
	}
	


	
	
}
