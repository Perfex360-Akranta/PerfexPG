package com.akranta.tpm.dao;

import com.akranta.tpm.model.BAL_BdmTlDtl;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;

public interface BAL_BdmTlDtlDao {

	public abstract BAL_BdmTlDtl create(BAL_BdmTlDtl bdmTlDtl) throws Exception;
	public abstract BAL_BdmTlDtl update(BAL_BdmTlDtl bdmTlDtl) throws Exception;
	public abstract BAL_BdmTlDtl delete(BAL_BdmTlDtl bdmTlDtl) throws Exception;
	public BAL_BdmTlDtl selectBd(String keyid) throws Exception;
	public BAL_BdmTlWhywhymst selectWhyWhy(String keyid) throws Exception ;
	public abstract BAL_BdmTlDtl getUpdateErpStatus(BAL_BdmTlDtl newBdmTlDtl)throws Exception;
	//String getUpdateErpStatus(String success, String tranId) throws Exception;

}

