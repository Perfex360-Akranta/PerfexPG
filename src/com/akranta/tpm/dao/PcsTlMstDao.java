package com.akranta.tpm.dao;

import com.akranta.tpm.model.PcsTlMst;

public interface PcsTlMstDao {

	public abstract PcsTlMst create(PcsTlMst pcsTlMst) throws Exception;
	public abstract PcsTlMst update(PcsTlMst pcsTlMst) throws Exception;
	public abstract PcsTlMst delete(PcsTlMst pcsTlMst) throws Exception;

}

