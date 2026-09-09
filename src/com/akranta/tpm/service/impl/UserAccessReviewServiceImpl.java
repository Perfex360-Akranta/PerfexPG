package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.UserAccessReviewDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.UserAccessReviewDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.UserAccessReviewService;

public class UserAccessReviewServiceImpl implements UserAccessReviewService {

	public void UserAccessReviewServiceImplJwt(String jwtToken) {
		try {
			userAccessReviewDao.UserAccessReviewDaoImplJwt(jwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UserAccessReviewDao userAccessReviewDao;

	DBActionTemplate dbActionTemplate;

	public UserAccessReviewServiceImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		userAccessReviewDao = new UserAccessReviewDaoImpl(dbActionTemplate);
	}

	@Override
	public List<String[]> getUserAccessReviewRept(CommonFilter commonFilter) throws Exception {
		return this.userAccessReviewDao.getUserAccessReviewRept(commonFilter);
	}

	@Override
	public Workbook UserAccessReviewExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception {
		return this.userAccessReviewDao.getUserAccessReviewExportExcel(commonFilter, tblJSONObj, format);
	}
}
