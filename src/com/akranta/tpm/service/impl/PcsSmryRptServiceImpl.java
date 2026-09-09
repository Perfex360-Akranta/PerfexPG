package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.dao.PcsSmryRptDao;

import com.akranta.tpm.dao.impl.PcsSmryRptDaoImpl;

import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.PcsSmryRptService;


public class PcsSmryRptServiceImpl implements PcsSmryRptService{

	private PcsSmryRptDao pcsSmryRptDao;
	
	
	public PcsSmryRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsSmryRptDao = new PcsSmryRptDaoImpl(dbActionTemplate);
	
	}


	@Override
	public List<String[]> getAllPcsSummary(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.pcsSmryRptDao.getAllPcsSummary( commonFilter);
	}


	@Override
	public Workbook pcsSmryExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat,String type) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsSmryRptDao.getAllPcsSummaryExl( commonFilter,colmodel,rptFormat,type);
	}


	@Override
	public List<String[]> getpcsRemarks(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.pcsSmryRptDao.getpcsRemarks( commonFilter);
	}

}
