package com.akranta.tpm.dao;

import com.akranta.tpm.model.PcsTlAncilliarytime;

public interface PcsTlAncilliarytimeDao {

	public abstract PcsTlAncilliarytime create(PcsTlAncilliarytime pcsTlAncilliarytime) throws Exception;
	public abstract PcsTlAncilliarytime update(PcsTlAncilliarytime pcsTlAncilliarytime) throws Exception;
	public abstract PcsTlAncilliarytime delete(PcsTlAncilliarytime pcsTlAncilliarytime) throws Exception;

}

