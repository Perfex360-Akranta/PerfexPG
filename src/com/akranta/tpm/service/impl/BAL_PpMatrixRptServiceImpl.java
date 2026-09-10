package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.dao.BAL_PpMatrixRptDao;
import com.akranta.tpm.dao.impl.BAL_PpMatrixRptDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_PpMatrixRptService;


public class BAL_PpMatrixRptServiceImpl implements BAL_PpMatrixRptService{
	
	private BAL_PpMatrixRptDao ppMatrixRptDao;
	public BAL_PpMatrixRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		ppMatrixRptDao =  new BAL_PpMatrixRptDaoImpl(dbActionTemplate);
	}	
	public List<String[]> getPPMatrixMonthRpt(CommonFilter commonFilter) throws Exception
	{
		return this.ppMatrixRptDao.getPPMatrixMonthRptDao(commonFilter);
	}
	
	public List<String[]> getPPMatrixMachineRpt(CommonFilter commonFilter) throws Exception
	{
		return this.ppMatrixRptDao.getPPMatrixMachineRptDao(commonFilter);
	}
	public Workbook getPPMatrixMonthRptExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.ppMatrixRptDao.getPPMatrixMonthRptExcelDao(commonFilter,colmodel,rptFormat);
	}
	public Workbook getPPMatrixMachineRptExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.ppMatrixRptDao.getPPMatrixMachineRptExcelDao(commonFilter,colmodel,rptFormat);
	}
	
}
