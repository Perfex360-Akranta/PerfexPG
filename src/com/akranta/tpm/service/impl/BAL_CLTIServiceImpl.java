package com.akranta.tpm.service.impl;

import java.util.List;

import com.akranta.tpm.dao.BAL_CLTIDao;
import com.akranta.tpm.dao.impl.BAL_CLTIDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_CLTIService;

public class BAL_CLTIServiceImpl implements BAL_CLTIService{
	private BAL_CLTIDao cltiDao ;
	public BAL_CLTIServiceImpl(DBActionTemplate dbActionTemplate) {
		cltiDao=new BAL_CLTIDaoImpl(dbActionTemplate);
		
	}

	@Override
	public List<String[]> getCLTIReport(CommonFilter commonFilter)
			throws Exception {
		
		return this.cltiDao.getCLTIReport(commonFilter);
	}

}
