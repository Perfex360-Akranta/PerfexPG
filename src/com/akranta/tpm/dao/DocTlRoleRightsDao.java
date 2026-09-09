package com.akranta.tpm.dao;

import com.akranta.tpm.model.DocTlRoleRights;

public interface DocTlRoleRightsDao {

	public abstract DocTlRoleRights create(DocTlRoleRights docTlRoleRights) throws Exception;
	public abstract DocTlRoleRights update(DocTlRoleRights docTlRoleRights) throws Exception;
	public abstract DocTlRoleRights delete(DocTlRoleRights docTlRoleRights) throws Exception;

}

