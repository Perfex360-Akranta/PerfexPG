package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

//import com.akranta.tpm.dao.CustomerComplaintStmtRptDao;
import com.akranta.tpm.dao.EntTlTrainingHoursDao;
//import com.akranta.tpm.dao.impl.CustomerComplaintStmtRptDaoImpl;
import com.akranta.tpm.dao.impl.EntTlTrainingHoursDaoImpl;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.service.CustomerComplaintStmtRptService;
import com.akranta.tpm.service.EntTlTrainingHoursService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;



public  class EntTlTrainingHoursServiceImpl implements EntTlTrainingHoursService {

private EntTlTrainingHoursDao entTlTrainingHoursDao;
	
	public EntTlTrainingHoursServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entTlTrainingHoursDao =  new EntTlTrainingHoursDaoImpl(dbActionTemplate);
	}
	
	public List<String[]> getTrainingHoursPgm(CommonFilter commonFilter) throws Exception
	{
		CommonMessage.debugMsg("getTrainingHoursPgm");
		return this.entTlTrainingHoursDao.getTrainingHoursPgm(commonFilter);
	}
	public List<String[]> getTrainingHoursPgmGraph(CommonFilter commonFilter) throws Exception
	{
		CommonMessage.debugMsg("getTrainingHoursPgm");
		return this.entTlTrainingHoursDao.getTrainingHoursPgmGraph(commonFilter);
	}
	@Override
	public Workbook getTrainingHoursExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTrainingHoursDao.getTrainingHoursExportExcel(commonFilter,colmodel,rptFormat);
	}

}
