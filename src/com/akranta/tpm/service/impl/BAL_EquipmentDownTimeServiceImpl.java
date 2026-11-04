package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.dao.EquipmentDownTimeDao;

import com.akranta.tpm.dao.impl.BAL_EquipmentDownTimeDaoImpl;

import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.BAL_EquipmentDownTimeService;


public class BAL_EquipmentDownTimeServiceImpl implements BAL_EquipmentDownTimeService{

	private EquipmentDownTimeDao equipmentDownTimeDao;
	
	
	public BAL_EquipmentDownTimeServiceImpl(DBActionTemplate dbActionTemplate)
	{
		equipmentDownTimeDao = new BAL_EquipmentDownTimeDaoImpl(dbActionTemplate);
	
	}
	
	//jwt
	public void BAL_EquipmentDownTimeServiceImplJwt(String JwtToken){
	    try{
	    	equipmentDownTimeDao.BAL_EquipmentDownTimeDaoImplJwt(JwtToken);   // dao side
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}


	@Override
	public List<String[]> getAllEquipmentDownTimet(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.equipmentDownTimeDao.getAllEquipmentDownTime( commonFilter);
	}


	@Override
	public List<String[]> getAllcount(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		return this.equipmentDownTimeDao.getAllcount( commonFilter);
	}
	public List<String[]> getSumOfEquipmentDownTimeForDrill(CommonFilter commonFilter) throws Exception{
		return this.equipmentDownTimeDao.getSumOfEquipmentDownTimeForDrill( commonFilter);
	}


	@Override
	public Workbook downtimeExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.equipmentDownTimeDao.getdownTimeReport(commonFilter,colmodel,rptFormat);
	}
}
