package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlDmcprojectprioritymst;
import com.akranta.tpm.model.KznTlKkprojectprioritymst;

public interface KKProjecPriorityDao {
	public List<String[]> getAllProjectPrioritisat(CommonFilter commonFilter) throws Exception;
	
	public List<String[]> getAlldmcProjectPrioritisat(CommonFilter commonFilter) throws Exception;

	public KznTlKkprojectprioritymst create(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst) throws Exception;
	
	public KznTlDmcprojectprioritymst createdmcpp(KznTlDmcprojectprioritymst newKznTlDmcprojectprioritymst) throws Exception;

	public KznTlKkprojectprioritymst delete(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst) throws Exception;
	
	public void KKProjecPriorityDaoImplJwt(String JwtToken);

}
