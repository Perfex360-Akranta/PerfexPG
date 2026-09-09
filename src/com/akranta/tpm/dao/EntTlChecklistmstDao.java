package com.akranta.tpm.dao;

import com.akranta.tpm.model.EntTlChecklistmst;

public interface EntTlChecklistmstDao {

	public abstract EntTlChecklistmst create(EntTlChecklistmst entTlChecklistmst) throws Exception;
	public abstract EntTlChecklistmst update(EntTlChecklistmst entTlChecklistmst) throws Exception;
	public abstract EntTlChecklistmst delete(EntTlChecklistmst entTlChecklistmst) throws Exception;

}

