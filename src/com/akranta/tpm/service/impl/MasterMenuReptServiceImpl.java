package com.akranta.tpm.service.impl;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.dao.MasterMenuReptDao;
import com.akranta.tpm.dao.impl.MasterMenuReptDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.MasterMenuReptService;

public class MasterMenuReptServiceImpl implements MasterMenuReptService {
	
	public void MasterMenuReptServiceImplJwt(String jwtToken) {
	    try {
	        masterMenuReptDao.MasterMenuReptDaoImplJwt(jwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private MasterMenuReptDao masterMenuReptDao;
	
	DBActionTemplate dbActionTemplate;
	
	public MasterMenuReptServiceImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		masterMenuReptDao = new MasterMenuReptDaoImpl(dbActionTemplate);
	}
	
	@Override
	public List<String[]> getMasterMenuRept(CommonFilter commonFilter) throws Exception {
		return this.masterMenuReptDao.getMasterMenuRept(commonFilter);
	}
	
	@Override
	public Workbook MasterMenuReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception {
		return this.masterMenuReptDao.getMasterMenuReportExportExcel(commonFilter, tblJSONObj, format);
	}
}
