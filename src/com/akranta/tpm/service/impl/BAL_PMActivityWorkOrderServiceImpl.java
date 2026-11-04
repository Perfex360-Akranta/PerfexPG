package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.BAL_PMActivityWorkOrderDao;
import com.akranta.tpm.dao.BAL_PlmTlShutdowncalDao;
import com.akranta.tpm.dao.BAL_PlmTlStandardsDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.BAL_MaintActivityDaoImpl;
import com.akranta.tpm.dao.impl.BAL_PMActivityWorkOrderDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_PMActivityWorkOrderService;
import com.akranta.tpm.service.api.BestKaizenServiceApi;
import com.akranta.tpm.utils.Validations;

public class BAL_PMActivityWorkOrderServiceImpl implements BAL_PMActivityWorkOrderService {
	
	private CommonFilterDao commonFilterDao;
	private DBActionTemplate dbActionTemplate;
	private BAL_PMActivityWorkOrderDao pMActivityWorkOrderDao;
	
	public BAL_PMActivityWorkOrderServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pMActivityWorkOrderDao =  new BAL_PMActivityWorkOrderDaoImpl(dbActionTemplate);
		this.dbActionTemplate =dbActionTemplate;
	}
	
	public void BAL_PMActivityWorkOrderServiceImplJwt(String JwtToken){
    	try{
    		pMActivityWorkOrderDao.BAL_PMActivityWorkOrderDaoImplJwt(JwtToken);
    		//serviceApi = new BestKaizenServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }

	public List<String[]> getAllWoActivity(CommonFilter commonFilter) throws Exception{
		// TODO Auto-generated method stub
		return pMActivityWorkOrderDao.getAllWoActivity(commonFilter);
	}

	@Override
	public Workbook getAllWoActivityExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		return pMActivityWorkOrderDao.getAllWoActivityExportExcel(commonFilter,tableModel,format);
	}

	@Override
	public List<String[]> getPendingActivity(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return pMActivityWorkOrderDao.getPendingActivity(commonFilter);
	}

	@Override
	public Workbook getPendingActivityExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		return pMActivityWorkOrderDao.getPendingActivityExportExcel(commonFilter,tableModel,format);
	}

}
