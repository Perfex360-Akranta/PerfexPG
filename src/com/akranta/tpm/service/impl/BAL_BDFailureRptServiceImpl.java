package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_BDFailureRptDao;

import com.akranta.tpm.dao.impl.BAL_BDFailureRptDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_BDFailureRptService;

public class BAL_BDFailureRptServiceImpl implements BAL_BDFailureRptService {
	private BAL_BDFailureRptDao bDFailureRptDao;
	
	public BAL_BDFailureRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		bDFailureRptDao =  new BAL_BDFailureRptDaoImpl(dbActionTemplate);
	}
	

	public List<String[]> getFrequency(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.bDFailureRptDao.getFrequency(commonFilter);
	}
	public List<String[]> getBDSplit(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.bDFailureRptDao.getBDSplit(commonFilter);
	}
	
	
	public Workbook failureRateExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.bDFailureRptDao.getfailureRateExportExcel(commonFilter,colmodel,rptFormat);
	}
	
	public Workbook splitBreakupExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.bDFailureRptDao.splitBreakupExportExcel(commonFilter,colmodel,rptFormat);
	}
	

}
