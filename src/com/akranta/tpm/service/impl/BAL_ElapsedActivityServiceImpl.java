package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_ElapsedActivityDao;
import com.akranta.tpm.dao.impl.BAL_ElapsedActivityDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_ElapsedActivityService;



public class BAL_ElapsedActivityServiceImpl  implements BAL_ElapsedActivityService {
	
private BAL_ElapsedActivityDao elapsedActivityDao;
	
	public BAL_ElapsedActivityServiceImpl(DBActionTemplate dbActionTemplate)
	{
		elapsedActivityDao = new BAL_ElapsedActivityDaoImpl(dbActionTemplate);
	}
	
	public void BAL_ElapsedActivityServiceImplJwt(String JwtToken){
	    try{
	    	elapsedActivityDao.BAL_ElapsedActivityDaoImplJwt(JwtToken);   // dao side
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	public List<String[]> getAllElapsedMaintActivity(CommonFilter commonFilter) throws Exception
	{
		return this.elapsedActivityDao.getAllElapsedMaintActivity(commonFilter);
	}


	public Workbook elapsedMaintReportExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		return this.elapsedActivityDao.elapsedMaintReportExportExcel(commonFilter,tblJSONObj,format);
	}

	
}
