package com.akranta.tpm.dao;

import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.GenTlActionplandtl;

public interface GenTlActionplandtlDao {

	public abstract GenTlActionplandtl create(GenTlActionplandtl genTlActionplandtl) throws Exception;
	public abstract GenTlActionplandtl update(GenTlActionplandtl genTlActionplandtl) throws Exception;
	public abstract GenTlActionplandtl delete(GenTlActionplandtl genTlActionplandtl) throws Exception;
	
	

}

