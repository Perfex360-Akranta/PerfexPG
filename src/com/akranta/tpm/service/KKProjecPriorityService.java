package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlDmcprojectprioritymst;
import com.akranta.tpm.model.KznTlKkprojectprioritymst;

public interface KKProjecPriorityService {
	public List<String[]> getAllProjectPrioritisat(CommonFilter commonFilter) throws Exception;
	
	public List<String[]> getAlldmcProjectPrioritisat(CommonFilter commonFilter) throws Exception;

	public KznTlKkprojectprioritymst create(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst,KznTlKkprojectprioritymst existKznTlKkprojectprioritymst) throws Exception;
	
	public KznTlDmcprojectprioritymst createdmcpp(KznTlDmcprojectprioritymst newKznTlDmcprojectprioritymst,KznTlDmcprojectprioritymst existKznTlDmcprojectprioritymst) throws Exception;

	public KznTlKkprojectprioritymst delete(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst,KznTlKkprojectprioritymst existKznTlKkprojectprioritymst) throws Exception;

	public void KKProjectPriorityServiceImplJwt(String JwtToken);
}
