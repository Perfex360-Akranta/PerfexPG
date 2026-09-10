package com.akranta.tpm.dao;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.model.BAL_PlmTlWorespmst;

public interface BAL_PlmTlWorespmstDao {

	public abstract BAL_PlmTlWorespmst create(BAL_PlmTlWorespmst plmTlWorespmst) throws Exception;
	public abstract BAL_PlmTlWorespmst update(BAL_PlmTlWorespmst plmTlWorespmst) throws Exception;
	public abstract BAL_PlmTlWorespmst delete(BAL_PlmTlWorespmst plmTlWorespmst) throws Exception;
	public abstract String delSelectedTrade(String worespdtlId) throws BusinessApplicationExceptions, Exception;
	
	public abstract void balPlmTlWorespmstDaoImplJwt(String jwtToken);

}

