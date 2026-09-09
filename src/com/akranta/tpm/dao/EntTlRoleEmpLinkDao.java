package com.akranta.tpm.dao;

import com.akranta.tpm.model.EntTlRoleEmpLink;

public interface EntTlRoleEmpLinkDao {

	public abstract EntTlRoleEmpLink create(EntTlRoleEmpLink entTlRoleEmpLink) throws Exception;
	public abstract EntTlRoleEmpLink update(EntTlRoleEmpLink entTlRoleEmpLink) throws Exception;
	public abstract EntTlRoleEmpLink delete(EntTlRoleEmpLink entTlRoleEmpLink) throws Exception;

}

