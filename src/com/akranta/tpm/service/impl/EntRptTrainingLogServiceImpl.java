package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.dao.EntRptTrainingLogDao;
import com.akranta.tpm.dao.impl.EntRptTrainingLogDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.EntRptTrainingLogService;

public class EntRptTrainingLogServiceImpl implements EntRptTrainingLogService 
{
	private EntRptTrainingLogDao entRptTrainingLogDao;

	public EntRptTrainingLogServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entRptTrainingLogDao = new EntRptTrainingLogDaoImpl(dbActionTemplate);
	
	}
	@Override
	public List<String[]> getRptTrainingLog (CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.entRptTrainingLogDao.getRptTrainingLog( commonFilter );
	}
	@Override
	public Workbook entRptTrainingLogExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String rptFormat)throws Exception {
		// TODO Auto-generated method stub
		return this.entRptTrainingLogDao.getTainingLogRpt(commonFilter,colmodel,rptFormat);
	}

}
