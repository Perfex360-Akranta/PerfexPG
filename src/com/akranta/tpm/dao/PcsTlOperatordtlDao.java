package com.akranta.tpm.dao;

import com.akranta.tpm.model.PcsTlOperatordtl;

public interface PcsTlOperatordtlDao {

	public abstract PcsTlOperatordtl create(PcsTlOperatordtl pcsTlOperatordtl) throws Exception;
	public abstract PcsTlOperatordtl update(PcsTlOperatordtl pcsTlOperatordtl) throws Exception;
	public abstract PcsTlOperatordtl delete(PcsTlOperatordtl pcsTlOperatordtl) throws Exception;

}

