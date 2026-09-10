package com.akranta.tpm.service;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_WOResponsibilityBean;
import com.akranta.tpm.model.BAL_PlmTlWorespmst;

public interface BAL_PlmTlWorespmstService {

	BAL_PlmTlWorespmst saveWorkresp(BAL_PlmTlWorespmst newplmTlWorespmst,
			BAL_PlmTlWorespmst existplmTlWorespmst, BAL_WOResponsibilityBean wOResponsibilityBean) throws ValidationExceptions, Exception;

	BAL_PlmTlWorespmst update(BAL_PlmTlWorespmst newplmTlWorespmst,BAL_PlmTlWorespmst existplmTlWorespmst,
			BAL_WOResponsibilityBean wOResponsibilityBean) throws ValidationExceptions;

	String delSelectedTrade(String worespdtlId) throws BusinessApplicationExceptions, Exception;
	
	public void BAL_PlmTlWorespmstServiceImplJwt(String jwtToken);

}
