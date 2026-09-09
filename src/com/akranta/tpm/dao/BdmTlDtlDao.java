package com.akranta.tpm.dao;

import com.akranta.tpm.model.BdmTlDtl;

public interface BdmTlDtlDao {

	public abstract BdmTlDtl create(BdmTlDtl bdmTlDtl) throws Exception;
	public abstract BdmTlDtl update(BdmTlDtl bdmTlDtl) throws Exception;
	public abstract BdmTlDtl delete(BdmTlDtl bdmTlDtl) throws Exception;
	public BdmTlDtl selectBd(String keyid) throws Exception;

}

