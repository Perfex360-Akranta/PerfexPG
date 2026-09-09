package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.AdmTlUserRoleLink;

public interface AdmTlUserRoleLinkDao {

	public abstract AdmTlUserRoleLink create(AdmTlUserRoleLink admTlUserRoleLink) throws Exception;
	public abstract AdmTlUserRoleLink update(AdmTlUserRoleLink admTlUserRoleLink) throws Exception;
	public abstract String delete(String rollId,String userId) throws Exception;
	public abstract List<String[]> getUserRoll(String userId)throws Exception ;
	public List<String[]> getUserRollS(String userId) throws Exception;

}

