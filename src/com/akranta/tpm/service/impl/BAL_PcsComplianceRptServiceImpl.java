

package com.akranta.tpm.service.impl;
import com.akranta.tpm.bean.NoPlanSaveBean;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_PcsComplianceRptDao;


import com.akranta.tpm.dao.impl.BAL_PcsComplianceRptDaoImpl;
import com.akranta.tpm.service.api.FunctionCallApi;


import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.BAL_PcsComplianceRptService;




public class BAL_PcsComplianceRptServiceImpl implements BAL_PcsComplianceRptService {

private BAL_PcsComplianceRptDao pcsComplianceRptDao;
FunctionCallApi fnCallApi;

	
	public BAL_PcsComplianceRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsComplianceRptDao =  new BAL_PcsComplianceRptDaoImpl(dbActionTemplate);
	}
	public void BAL_PcsComplianceRptServiceImplJwt(String JwtToken){
	    try{
	    	pcsComplianceRptDao.BAL_PcsComplianceRptDaoImplJwt(JwtToken);   // dao side
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	public List<String []> getAllPcsRpt(CommonFilter commonFilter,String from) throws Exception
	{
		return this.pcsComplianceRptDao.getpcsRpt(commonFilter,from);
	}
	public List<String []> getAllPcsTimeRpt(CommonFilter commonFilter,String from) throws Exception
	{
		return this.pcsComplianceRptDao.getpcsTimeRpt(commonFilter,from);
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