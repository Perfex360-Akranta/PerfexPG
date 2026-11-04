package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_EquipmentReportDao;
import com.akranta.tpm.dao.impl.BAL_EquipmentReportDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_EquipmentReportService;



public  class BAL_EquipmentReportServiceImpl implements BAL_EquipmentReportService {

private BAL_EquipmentReportDao equipmentReportDao;
	
	public BAL_EquipmentReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		equipmentReportDao =  new BAL_EquipmentReportDaoImpl(dbActionTemplate);
	}
	
	public void BAL_EquipmentReportServiceImplJwt(String JwtToken){
    	try{
    		equipmentReportDao.BAL_EquipmentReportDaoImplJwt(JwtToken);
    		
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<String[]> getAllEquipmentQuery(CommonFilter commonFilter) throws Exception
	{
		return this.equipmentReportDao.getEquipmentQuery( commonFilter);
	}
	public List<String[]> getAllEquipmentControlPanelService(CommonFilter commonFilter) throws Exception
	{
		return this.equipmentReportDao.getEquipmentControlPanelDao( commonFilter);
	}
		
	public List<String[]> getAllEqmpthistry(CommonFilter commonFilter) throws Exception
	{
		//System.out.println("Inside the service impl...........");
		return this.equipmentReportDao.getEqmpthistry(commonFilter);
	}
	
	public Workbook equipmentQueryExportExcel(CommonFilter commonFilter ,JSONObject colModel,String rptFormat) throws Exception{
		return this.equipmentReportDao.equipmentQueryExportExcel(commonFilter,  colModel,rptFormat);
	}

	
	public Workbook equipmentHistoryExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.equipmentReportDao.equipmentHistoryExportExcel(commonFilter,  colModel,rptFormat);
	}

	
	public List<String[]> getAllMouldHistory(CommonFilter commonFilter)	throws Exception {
		return this.equipmentReportDao.getAllMouldHistory(commonFilter);
	}

	@Override
	public Workbook getAllMouldHistoryExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception {
		return this.equipmentReportDao.getAllMouldHistoryExportExcel(commonFilter,colModel,rptFormat);
	}
}
