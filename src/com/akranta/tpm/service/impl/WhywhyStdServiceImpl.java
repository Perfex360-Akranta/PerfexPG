package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.dao.WhywhyDao;
import com.akranta.tpm.dao.impl.WhywhyStdDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.WhywhyStdService;


public  class WhywhyStdServiceImpl implements WhywhyStdService {

private WhywhyDao whywhyDao;
	
	public WhywhyStdServiceImpl(DBActionTemplate dbActionTemplate)
	{
		whywhyDao =  new WhywhyStdDaoImpl(dbActionTemplate);
	}
	
	public List<String[]> getAllwhywhyStd(CommonFilter commonFilter) throws Exception
	{
		
		return this.whywhyDao.getWhywhyStd(commonFilter);
		
	}

	

	

	
}
