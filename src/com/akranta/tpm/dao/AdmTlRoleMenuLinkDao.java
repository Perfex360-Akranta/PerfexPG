package com.akranta.tpm.dao;

import com.akranta.tpm.model.AdmTlRoleMenuLink;

public interface AdmTlRoleMenuLinkDao {

	public abstract AdmTlRoleMenuLink create(AdmTlRoleMenuLink admTlRoleMenuLink) throws Exception;
	public abstract AdmTlRoleMenuLink update(AdmTlRoleMenuLink admTlRoleMenuLink) throws Exception;
	public abstract AdmTlRoleMenuLink delete(AdmTlRoleMenuLink admTlRoleMenuLink) throws Exception;
	public String checkMenuRoleExist(String roleId,String menuId) throws Exception;
}

