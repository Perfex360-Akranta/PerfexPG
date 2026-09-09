package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.EntempAttendenceRptDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.EntEmpAttendenceRptDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.EntEmpAttendenceRptService;

public class EntEmpAttendenceRptServiceImpl implements EntEmpAttendenceRptService {
	EntempAttendenceRptDao entempAttendenceRptDao;
	 public EntEmpAttendenceRptServiceImpl(DBActionTemplate dbActionTemplate) {
	        super();
	        	entempAttendenceRptDao = new EntEmpAttendenceRptDaoImpl(dbActionTemplate);
	    }

		
	@Override
	public List<String[]> getEmpAttendence(CommonFilter commonFilter,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		return entempAttendenceRptDao.getEmpAttendence(commonFilter,gridParams);
	}


	@Override
	public Workbook EmpAttReportExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		return entempAttendenceRptDao.getEmployeeAttendceExcel(commonFilter,tableModel,format);
	}

}
