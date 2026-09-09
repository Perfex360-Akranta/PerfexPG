package com.akranta.tpm.dao;

import com.akranta.tpm.model.AdmTlUsersessions;

public interface AdmTlUsersessionsDao {

	public abstract AdmTlUsersessions create(AdmTlUsersessions admTlUsersessions) throws Exception;
	public abstract AdmTlUsersessions update(AdmTlUsersessions admTlUsersessions) throws Exception;
	public abstract AdmTlUsersessions delete(AdmTlUsersessions admTlUsersessions) throws Exception;

}

