
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


//import com.akranta.tpm.dao.EquipmentDownTimeDao;
import com.akranta.tpm.dao.BAL_OeearprqrDao;

//import com.akranta.tpm.dao.impl.EquipmentDownTimeDaoImpl;
import com.akranta.tpm.dao.impl.BAL_OeearprqrDaoImpl;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;


//import com.akranta.tpm.service.EquipmentDownTimeService;
import com.akranta.tpm.service.BAL_OeearprqrService;
import com.akranta.tpm.service.api.FunctionCallApi;


public class BAL_OeearprqrServiceImpl implements BAL_OeearprqrService{

	private BAL_OeearprqrDao oeearprqrDao;
	FunctionCallApi fnCallApi;

	
	
	public BAL_OeearprqrServiceImpl(DBActionTemplate dbActionTemplate)
	{
		oeearprqrDao = new BAL_OeearprqrDaoImpl(dbActionTemplate);
	
	}
	public void BAL_OeearprqrServiceImplJwt(String JwtToken){
	    try{
	    	oeearprqrDao.BAL_OeearprqrDaoImplJwt(JwtToken);   // dao side
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}


	@Override
	public List<String[]> getAllOeearprqr(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		return this.oeearprqrDao.getAllOeearprqr( commonFilter);
	}


	@Override
	public Workbook oeeExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		return this.oeearprqrDao.getAllOeearprqrExl( commonFilter,colmodel,rptFormat);
	}


	@Override
	public List<String[]> getAllChartType(CommonFilter commonFilter)	throws Exception {
		return this.oeearprqrDao.getAllChartType( commonFilter);
	}

}