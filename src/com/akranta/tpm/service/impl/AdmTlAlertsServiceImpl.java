package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.AdmTlAlertsDao;
import com.akranta.tpm.dao.impl.AdmTlAlertsDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.AdmTlAlertsService;
import com.akranta.tpm.service.api.FishBoneServiceApi;

public class AdmTlAlertsServiceImpl implements AdmTlAlertsService{
	
	AdmTlAlertsDao admTlAlertsDao;
	public AdmTlAlertsServiceImpl(DBActionTemplate dbActionTemplate)
	{
		admTlAlertsDao=new AdmTlAlertsDaoImpl(dbActionTemplate);
	}
	public void AdmTlAlertsServiceImplJwt(String JwtToken){
	    try{
	   // 	oplTlMstDao.OplTlMstDaoImplJwt(JwtToken);   // dao side
	        // (Optional) if you want service-level direct access
	    	admTlAlertsDao.AdmTlAlertsDaoImplJwt(JwtToken);
	    //	fishBoneServiceApi = new FishBoneServiceApi(JwtToken);
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	@Override
	public  List<String[]> getAlertGrid()throws Exception
	{
		return admTlAlertsDao.getAlertGrid();
	}
	@Override
	public List<String[]> getAlertResGrid(String Name,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		return admTlAlertsDao.getAlertResGrid(Name,gridParams);
	}
	@Override
	public Workbook AlertsExportExcel(JSONObject colmodel, String format,GridParams gridParams,String keyId)
			throws Exception {
		// TODO Auto-generated method stub
		return admTlAlertsDao.getAlertsExcel(colmodel, format,gridParams,keyId);
	}
	@Override
	public List<String[]> getReminderData(String loggeduser,GridParams gridParams,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return admTlAlertsDao.getReminderData(loggeduser,gridParams,commonFilter);
	}
	@Override
	public Workbook getReminderExcel(JSONObject colmodel,GridParams gridParams,String format,
			CommonFilter commonFilter,String loggedUser) throws Exception {
		// TODO Auto-generated method stub
		return admTlAlertsDao.getReminderExcel(colmodel,gridParams,format,commonFilter,loggedUser);
	}


	public List<String[]> getApprovalData(String loggeduser, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return admTlAlertsDao.getApprovalData(loggeduser,commonFilter);
	}
}
