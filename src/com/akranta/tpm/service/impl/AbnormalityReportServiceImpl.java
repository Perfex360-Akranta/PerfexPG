

package com.akranta.tpm.service.impl;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AbnormalityReportDao;
import com.akranta.tpm.dao.impl.AbnormalityReportDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.AbnormalityReportService;


public  class AbnormalityReportServiceImpl implements AbnormalityReportService {

	private AbnormalityReportDao abnormalityReportDao;
	
	public AbnormalityReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		abnormalityReportDao =  new AbnormalityReportDaoImpl(dbActionTemplate);
	}
	
	public List<String[]> getAllAbnormalityGeneral(CommonFilter commonFilter) throws Exception
	{
		return this.abnormalityReportDao.getAbnormalityGeneral(commonFilter);
	}

	@Override
	public Workbook abnormalityGeneralReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat)throws Exception {
		return this.abnormalityReportDao.abnormalityGeneralReportExportExcel(commonFilter,colmodel,rptFormat);
	}

	@Override
	public List<String[]> getUnsafeDrillDn(CommonFilter commonFilter,String parentId) throws Exception {
		return this.abnormalityReportDao.getUnsafeDrillDn(commonFilter,parentId) ;
	}

	@Override
	public List<String[]> getUnsafeMonth(CommonFilter commonFilter) throws Exception {
		return this.abnormalityReportDao.getUnsafeMonth(commonFilter) ;
	}


	
	}
