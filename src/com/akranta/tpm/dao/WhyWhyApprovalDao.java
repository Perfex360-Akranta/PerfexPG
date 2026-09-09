package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.CommonFilter;

public interface WhyWhyApprovalDao {

	List<String[]> getAllWhywhyApproval(CommonFilter commonFilter,String roleId,String empId)throws Exception;

	BdmTlWhywhymst update(BdmTlWhywhymst newBdmTlWhywhymst) throws Exception;

	List<String[]> getSpentTime(String keyId) throws Exception;
	public abstract void WhywhyApprovalDaoImplJwt(String jwtToken);

}
