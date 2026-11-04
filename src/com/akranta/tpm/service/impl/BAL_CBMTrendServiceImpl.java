package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_CBMTrendDao;
//import com.akranta.tpm.dao.BAL_WorkOrderOverViewDao;
import com.akranta.tpm.dao.impl.BAL_CBMTrendDaoImpl;
//import com.akranta.tpm.dao.impl.BAL_WorkOrderOverViewDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_CBMTrendService;
import com.akranta.tpm.service.api.MomServiceApi;

import net.sf.json.JSONObject;


public class BAL_CBMTrendServiceImpl implements BAL_CBMTrendService {

	private BAL_CBMTrendDao cBMTrendDao;
	public BAL_CBMTrendServiceImpl(DBActionTemplate dbActionTemplate)
	{
		cBMTrendDao =  new BAL_CBMTrendDaoImpl(dbActionTemplate);
	}
	
	public void BAL_CBMTrendServiceImplJwt(String JwtToken){
    	try{
    		cBMTrendDao.BAL_CBMTrendDaoImplJwt(JwtToken);
    		
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<String[]> getAllCbmTrend(CommonFilter commonFilter)	throws Exception {
		
		return this.cBMTrendDao.getAllCbmTrend(commonFilter);
	}
	
	public Workbook CBMExcelReport(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.cBMTrendDao.CBMExcelReport(commonFilter,colModel,rptFormat);
	}

}
