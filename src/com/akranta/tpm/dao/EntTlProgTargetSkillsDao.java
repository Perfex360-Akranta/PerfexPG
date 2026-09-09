package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.EntTlProgTargetSkills;

public interface EntTlProgTargetSkillsDao {

	public abstract EntTlProgTargetSkills create(EntTlProgTargetSkills entTlProgTargetSkills) throws Exception;
	public abstract EntTlProgTargetSkills update(EntTlProgTargetSkills entTlProgTargetSkills) throws Exception;
	public abstract EntTlProgTargetSkills delete(EntTlProgTargetSkills entTlProgTargetSkills) throws Exception;
	public abstract EntTlProgTargetSkills getSkilformValues(String progKeyId) throws NoDataFoundException, SQLException, Exception;
	public abstract List<String[]> getskillgridData(String string) throws Exception;
	public String delgridData(String prtsKeyid) throws Exception;
	public abstract String chkKeyExists(String progKeyId) throws BusinessApplicationExceptions,SQLException;
	public abstract String getRattingKeyid(String orderno) throws Exception;

}
