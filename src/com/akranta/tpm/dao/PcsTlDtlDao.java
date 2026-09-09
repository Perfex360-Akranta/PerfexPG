package com.akranta.tpm.dao;

import com.akranta.tpm.model.PcsTlDtl;

public interface PcsTlDtlDao {

	public abstract PcsTlDtl create(PcsTlDtl pcsTlDtl) throws Exception;
	public abstract PcsTlDtl update(PcsTlDtl pcsTlDtl) throws Exception;
	public abstract PcsTlDtl delete(PcsTlDtl pcsTlDtl) throws Exception;

}

