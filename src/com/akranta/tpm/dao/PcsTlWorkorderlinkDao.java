package com.akranta.tpm.dao;

import com.akranta.tpm.model.PcsTlWorkorderlink;

public interface PcsTlWorkorderlinkDao {

	public abstract PcsTlWorkorderlink create(PcsTlWorkorderlink pcsTlWorkorderlink) throws Exception;
	public abstract PcsTlWorkorderlink update(PcsTlWorkorderlink pcsTlWorkorderlink) throws Exception;
	public abstract PcsTlWorkorderlink delete(PcsTlWorkorderlink pcsTlWorkorderlink) throws Exception;

}

