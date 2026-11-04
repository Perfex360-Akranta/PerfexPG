package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_PMComplianceDao;
import com.akranta.tpm.dao.impl.BAL_PMComplianceDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_PMComplainceRptService;


public class BAL_PMComplianceServiceImpl implements BAL_PMComplainceRptService {

	private BAL_PMComplianceDao pMComplianceDao;


	public BAL_PMComplianceServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pMComplianceDao = new BAL_PMComplianceDaoImpl(dbActionTemplate);

	}	
	
	public void BAL_PMComplianceServiceImpJwt(String JwtToken){
    	try{
    		pMComplianceDao.BAL_PMComplianceDaoImplJwt(JwtToken);
    		
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	public List<String[]> getAllPMCompliance(CommonFilter commonFilter)	throws Exception {
		
		return this.pMComplianceDao.getAllPMCompliance(commonFilter);		
	}
public List<String[]> getAllPMComplianceW(CommonFilter commonFilter)	throws Exception {
		
		return this.pMComplianceDao.getAllPMComplianceW(commonFilter);		
	}
	
	public Workbook PMComplainceReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.pMComplianceDao.PMComplainceReportExportExcel(commonFilter,colModel,rptFormat);
	}
	public List<String[]> getAllEmployeeActivity(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.pMComplianceDao.getAllEmployeeActivity(commonFilter);
	}
	@Override
	public Workbook EmployeeWiseActivityRptExportExcel(
			CommonFilter commonFilter, JSONObject tableModel, String format) {
		// TODO Auto-generated method stub
		return pMComplianceDao.EmployeeWiseActivityRptExportExcel(commonFilter,tableModel,  format);
	}
	@Override
	public List<String[]> getAllPMComplianceNew(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		try {
			return pMComplianceDao.getAllPMComplianceNew(commonFilter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Workbook newPMComplainceReportExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		return pMComplianceDao.newPMComplainceReportExportExcel(commonFilter,tableModel,  format) ;
	}
}

