package com.akranta.tpm.service.impl;

import java.util.List;

import com.akranta.tpm.dao.WhyWhyApprovalDao;


import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.WhyWhyApprovalDaoImpl;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.WhyWhyApprovalService;
import com.akranta.tpm.service.api.WhywhyServiceApi;


public class WhyWhyApprovalServiceImpl implements WhyWhyApprovalService {
private WhyWhyApprovalDao whyWhyApprovalDao;
DBActionTemplate dbActionTemplate;

private WhywhyServiceApi whywhyServiceApi;


	
	public WhyWhyApprovalServiceImpl(DBActionTemplate dbActionTemplate)
	{
		whyWhyApprovalDao =  new WhyWhyApprovalDaoImpl(dbActionTemplate);
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void WhywhyApprovalServiceImplJwt(String JwtToken){
    	try{
    		whyWhyApprovalDao.WhywhyApprovalDaoImplJwt(JwtToken);
    		whywhyServiceApi = new WhywhyServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<String[]> getAllWhywhyApproval(CommonFilter commonFilter,String roleId,String empId)
			throws Exception {
		// TODO Auto-generated method stub
		return whyWhyApprovalDao.getAllWhywhyApproval(commonFilter,roleId, empId);
	}
	@Override
	public BdmTlWhywhymst updateApprovalAI(BdmTlWhywhymst newBdmTlWhywhymst,
			BdmTlWhywhymst existBdmTlWhywhymst) throws Exception {
		// TODO Auto-generated method stub
		//return whyWhyApprovalDao.update(newBdmTlWhywhymst);
		return whywhyServiceApi.updateApprovalAI(newBdmTlWhywhymst);
	}

	@Override
	public BdmTlWhywhymst update(BdmTlWhywhymst newBdmTlWhywhymst,
			BdmTlWhywhymst existBdmTlWhywhymst) throws Exception {
		// TODO Auto-generated method stub
		//return whyWhyApprovalDao.update(newBdmTlWhywhymst);
		return whywhyServiceApi.updateApproval(newBdmTlWhywhymst);
	}
	@Override
	public List<String[]> getSpentTime(String keyId) throws Exception {
		// TODO Auto-generated method stub
		//return whyWhyApprovalDao.getSpentTime(keyId);
		return whywhyServiceApi.getSpentTime(keyId);
	}
	
}
