package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


//import com.akranta.tpm.dao.EquipmentDownTimeDao;
import com.akranta.tpm.dao.BAL_LossStratificationDao;

//import com.akranta.tpm.dao.impl.EquipmentDownTimeDaoImpl;
import com.akranta.tpm.dao.impl.BAL_LossStratificationDaoImpl;

import com.akranta.tpm.model.CommonFilter;

//import com.akranta.tpm.service.EquipmentDownTimeService;
import com.akranta.tpm.service.BAL_LossStratificationService;


public class BAL_LossStratificationServiceImpl implements BAL_LossStratificationService{

	private BAL_LossStratificationDao lossStratificationDao;
	
	
	public BAL_LossStratificationServiceImpl(DBActionTemplate dbActionTemplate)
	{
		lossStratificationDao = new BAL_LossStratificationDaoImpl(dbActionTemplate);
	
	}
	public void BAL_LossStratificationServiceImplJwt(String JwtToken){
	    try{
	    	lossStratificationDao.BAL_LossStratificationDaoImplJwt(JwtToken);   // dao side
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}


	@Override
	public List<String[]> getAllLossStratification(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.lossStratificationDao.getAllLossStratification( commonFilter);
	}


	@Override
	public List<String[]> getAllLossStratificationSubGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.lossStratificationDao.getAllLossStratificationSubGrid( commonFilter);
	}


	 
	public List<String[]> getAllLossGraph(CommonFilter commonFilter) throws Exception {
		return this.lossStratificationDao.getAllLossGraph(commonFilter);
	}


	@Override
	public Workbook lossExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		return this.lossStratificationDao.getAllLossExl(commonFilter,colmodel,rptFormat);
	}


	@Override
	public Workbook lossExportExcelShift(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		 return this.lossStratificationDao.getAllLossExlShift(commonFilter,tblJSONObj,format);
	}


	@Override
	public List<String[]> getAllLossStratificationShift(
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		 return this.lossStratificationDao.getAllLossStratificationShift( commonFilter);
	}


	@Override
	public List<String[]> getAllLossStratificationSubGridShift(
			CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		return this.lossStratificationDao.getAllLossStratificationSubGridShift( commonFilter);
	}
		
}
