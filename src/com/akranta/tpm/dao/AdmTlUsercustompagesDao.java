package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.AdmTlUsercustompages;

public interface AdmTlUsercustompagesDao {

	public abstract AdmTlUsercustompages create(AdmTlUsercustompages admTlUsercustompages) throws Exception;
	public abstract AdmTlUsercustompages update(AdmTlUsercustompages admTlUsercustompages) throws Exception;
	public abstract AdmTlUsercustompages delete(AdmTlUsercustompages admTlUsercustompages) throws Exception;
	public abstract AdmTlUsercustompages recallHomePage(String userId)throws Exception;
	public abstract List<String[]> getMenuMasterData(String uscpPageuri)throws Exception;

}

