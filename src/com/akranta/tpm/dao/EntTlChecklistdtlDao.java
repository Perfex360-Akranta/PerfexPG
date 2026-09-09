package com.akranta.tpm.dao;

import com.akranta.tpm.model.EntTlChecklistdtl;

public interface EntTlChecklistdtlDao {

	public abstract EntTlChecklistdtl create(EntTlChecklistdtl entTlChecklistdtl) throws Exception;
	public abstract EntTlChecklistdtl update(EntTlChecklistdtl entTlChecklistdtl) throws Exception;
	public abstract EntTlChecklistdtl delete(EntTlChecklistdtl entTlChecklistdtl) throws Exception;

}

