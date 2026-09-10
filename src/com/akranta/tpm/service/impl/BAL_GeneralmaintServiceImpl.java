package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_GeneralmaintDao;

import com.akranta.tpm.dao.impl.BAL_GeneralmaintDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_GeneralmaintService;
import com.akranta.tpm.service.api.FunctionCallApi;


public class BAL_GeneralmaintServiceImpl implements BAL_GeneralmaintService {
	
	
	private BAL_GeneralmaintDao generalmaintDao;
	FunctionCallApi fnCallApi;

	
	DBActionTemplate  dbActionTemplate ;
public BAL_GeneralmaintServiceImpl(DBActionTemplate dbActionTemplate)
{
	generalmaintDao = new BAL_GeneralmaintDaoImpl(dbActionTemplate);
}
public void BAL_GeneralmaintServiceImplJwt(String JwtToken){
    try{
    	generalmaintDao.BAL_GeneralmaintDaoImplJwt(JwtToken);   // dao side
        // (Optional) if you want service-level direct access
       //  oplServiceApi = new OplTlMstServiceApi(JwtToken);
    } catch(Exception e){
        e.printStackTrace();
    }
}

	@Override
	public List<String[]> getAllgeneralmanit(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		return this.generalmaintDao.getAllgeneralmanit(commonFilter);
	}


	@Override
	public List<String[]> getAllGMDrillDnchrt(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Workbook genMaintRptExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.generalmaintDao.getGenMaintRpt(commonFilter,colmodel,rptFormat);
	}
	public List<String[]> getSapInfoList(CommonFilter commFilter) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("service impl getSapinfo list");
		return this.generalmaintDao.getSapInfoList(commFilter);
	}


	
	
}
