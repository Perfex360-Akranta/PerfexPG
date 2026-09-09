package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;

public interface EntTlSkillindexassessmstDao {

	public abstract EntTlSkillindexassessmst create(EntTlSkillindexassessmst entTlSkillindexassessmst) throws Exception;
	public abstract EntTlSkillindexassessmst update(EntTlSkillindexassessmst entTlSkillindexassessmst) throws Exception;
	public abstract EntTlSkillindexassessmst delete(EntTlSkillindexassessmst entTlSkillindexassessmst) throws Exception;

	public EntTlSkillindexassessmst createAssement( List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst, 
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl) throws Exception;
	
	public List<String[]> getEmpList(CommonFilter commonfilter) throws Exception;
	
	public List<String[]> getEmpListFunction(CommonFilter commonfilter,GridParams gridParams) throws Exception;
	
	public void entTlSkillindexassessmstDaoImplJwt(String JwtToken);
}

