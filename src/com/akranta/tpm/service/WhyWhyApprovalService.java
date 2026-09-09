package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.CommonFilter;

public interface WhyWhyApprovalService {

	public List<String[]> getAllWhywhyApproval(CommonFilter commonFilter,String roleId,String empId) throws Exception;

	public BdmTlWhywhymst updateApprovalAI(BdmTlWhywhymst newBdmTlWhywhymst,
			BdmTlWhywhymst existBdmTlWhywhymst)throws Exception;

	public BdmTlWhywhymst update(BdmTlWhywhymst newBdmTlWhywhymst,
			BdmTlWhywhymst existBdmTlWhywhymst)throws Exception;

	public List<String[]> getSpentTime(String keyId) throws Exception;
	public void WhywhyApprovalServiceImplJwt(String JwtToken);

}
