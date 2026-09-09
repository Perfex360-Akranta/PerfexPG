package com.akranta.tpm.service.impl;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AccessReviewrptDao;
import com.akranta.tpm.dao.impl.AccessReviewrptDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.AccessReviewrptService;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class AccessReviewrptServiceImpl implements AccessReviewrptService  {
	private AccessReviewrptDao  accessReviewrptDao;
	private Validations validations ;
	private DBActionTemplate  dbActionTemplate ; 
	
	public AccessReviewrptServiceImpl(DBActionTemplate dbActionTemplate){
		
		accessReviewrptDao = new AccessReviewrptDaoImpl(dbActionTemplate);
		this.dbActionTemplate = dbActionTemplate;
		validations = new Validations();
	}
	@Override
	public List<String[]> getAccessReview(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.accessReviewrptDao.getAccessReview(commonFilter);
	}
	@Override
	public Workbook getAccessReviewrptExcel(JSONObject colmodel, String format, CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.accessReviewrptDao.getAccessReviewrptExcel(colmodel,format,commonFilter);
	}

}
