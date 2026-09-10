package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_BreakDownParetoDao;
import com.akranta.tpm.dao.impl.BAL_BreakDownParetoDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_BreakDownParetoService;

public class BAL_BreakDownParetoServiceImpl implements BAL_BreakDownParetoService {
private BAL_BreakDownParetoDao breakdownRptDao;
	
	
	public BAL_BreakDownParetoServiceImpl(DBActionTemplate dbActionTemplate)
	{
		breakdownRptDao = new BAL_BreakDownParetoDaoImpl(dbActionTemplate);
	
	}

	public List<String[]> getAllbkdpareto(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.breakdownRptDao.getAllbkdpareto( commonFilter);
	}

	@Override
	public Workbook paretoExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptformat) throws Exception {
		// TODO Auto-generated method stub
		return this.breakdownRptDao.getparetoExcel( commonFilter,colmodel,rptformat);
	}
}
