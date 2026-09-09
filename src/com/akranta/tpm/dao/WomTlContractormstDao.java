package com.akranta.tpm.dao;

import com.akranta.tpm.model.WomTlContractormst;

public interface WomTlContractormstDao {

	public abstract WomTlContractormst create(WomTlContractormst womTlContractormst) throws Exception;
	public abstract WomTlContractormst update(WomTlContractormst womTlContractormst) throws Exception;
	public abstract WomTlContractormst delete(WomTlContractormst womTlContractormst) throws Exception;

}

