

package com.akranta.tpm.service.impl;
import com.akranta.tpm.bean.NoPlanSaveBean;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.PcsComplianceRptDao;


import com.akranta.tpm.dao.impl.PcsComplianceRptDaoImpl;


import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.PcsComplianceRptService;




public class PcsComplianceRptServiceImpl implements PcsComplianceRptService {

private PcsComplianceRptDao pcsComplianceRptDao;
	
	public PcsComplianceRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsComplianceRptDao =  new PcsComplianceRptDaoImpl(dbActionTemplate);
	}
	
	public List<String []> getAllPcsRpt(CommonFilter commonFilter,String from) throws Exception
	{
		return this.pcsComplianceRptDao.getpcsRpt(commonFilter,from);
	}

	@Override
	public Workbook pcscompExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat,String from) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsComplianceRptDao.getpcscompExl(commonFilter,colmodel,rptFormat,from);	}

	@Override
	public String getShiftKeyid(String factId, String shiftCode) throws Exception {
		return this.pcsComplianceRptDao.getShiftKeyid(factId, shiftCode);
	}

	@Override
	public List<String[]> getNoPlanDuration(String mchId, String date,
			String shift) throws Exception {
		// TODO Auto-generated method stub
		return pcsComplianceRptDao.getNoPlan(mchId,date,shift);
	}

	@Override
	public List<String[]> SaveNoPlan(List<NoPlanSaveBean> noPlanSaveList,
			String usrm_ccno) throws Exception {
		// TODO Auto-generated method stub
		return pcsComplianceRptDao.NoPlanSave(noPlanSaveList,usrm_ccno);
	}


	
}