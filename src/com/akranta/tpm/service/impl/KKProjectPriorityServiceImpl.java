package com.akranta.tpm.service.impl;

import java.util.List;

import org.apache.taglibs.standard.lang.jpath.expression.ValidationException;

import com.akranta.tpm.dao.KKProjecPriorityDao;
//import com.akranta.tpm.dao.SLADAO;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.KKProjecPriorityDaoImpl;
//import com.akranta.tpm.dao.impl.SLADaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlDmcprojectprioritymst;
import com.akranta.tpm.model.KznTlKkprojectprioritymst;
import com.akranta.tpm.service.KKProjecPriorityService;
import com.akranta.tpm.service.api.ProjectPriorityServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
//import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing.Validation;

public class KKProjectPriorityServiceImpl implements KKProjecPriorityService{
private KKProjecPriorityDao kkProjecPriorityDao;
private Validations validation;
private ProjectPriorityServiceApi ppServiceApi;
	
	public KKProjectPriorityServiceImpl(DBActionTemplate dbActionTemplate) {		
		kkProjecPriorityDao = new KKProjecPriorityDaoImpl (dbActionTemplate);
		validation = new Validations();
	}
	 public void KKProjectPriorityServiceImplJwt(String JwtToken){
	    	try{
	    		kkProjecPriorityDao.KKProjecPriorityDaoImplJwt(JwtToken);
	    		ppServiceApi = new ProjectPriorityServiceApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	 }

	@Override
	public List<String[]> getAllProjectPrioritisat(CommonFilter commonFilter)
			throws Exception {		
		return kkProjecPriorityDao.getAllProjectPrioritisat(commonFilter);
	}
	
	@Override
	public List<String[]> getAlldmcProjectPrioritisat(CommonFilter commonFilter)
			throws Exception {		
		return kkProjecPriorityDao.getAlldmcProjectPrioritisat(commonFilter);
	}
	@Override
	public KznTlKkprojectprioritymst create(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst,KznTlKkprojectprioritymst existKznTlKkprojectprioritymst)throws Exception {
		CommonMessage.debugMsg("lstKznTlKkprojectprioritydtl   ass(): "+newKznTlKkprojectprioritymst.getDetail().size());
		//return kkProjecPriorityDao.create(newKznTlKkprojectprioritymst);
		return ppServiceApi.saveRecord(newKznTlKkprojectprioritymst);
	}
	@Override
	public KznTlDmcprojectprioritymst createdmcpp(KznTlDmcprojectprioritymst newKznTlDmcprojectprioritymst,KznTlDmcprojectprioritymst existKznTlDmcprojectprioritymst)throws Exception {
		CommonMessage.debugMsg("lstKznTlDmcprojectprioritydtl   ass(): "+newKznTlDmcprojectprioritymst.getDetail().size());
		return kkProjecPriorityDao.createdmcpp(newKznTlDmcprojectprioritymst);
	}
	@Override
	public KznTlKkprojectprioritymst delete(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst,KznTlKkprojectprioritymst existKznTlKkprojectprioritymst)throws Exception {		
		return kkProjecPriorityDao.delete(newKznTlKkprojectprioritymst);
	}

}
