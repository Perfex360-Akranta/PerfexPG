package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.dao.InternalRejectionDao;

import com.akranta.tpm.dao.impl.InternalRejectionDaoImpl;

import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.InternalRejectionService;


public class InternalRejectionServiceImpl implements InternalRejectionService{

	private InternalRejectionDao internalRejectionDao;
	
	
	public InternalRejectionServiceImpl(DBActionTemplate dbActionTemplate)
	{
		internalRejectionDao = new InternalRejectionDaoImpl(dbActionTemplate);
	
	}


	@Override
	public List<String[]> getCalender(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.internalRejectionDao.getCalender( commonFilter);
	}
}
