package com.akranta.tpm.dao;

import com.akranta.tpm.model.EntTlRoleSkillLink;

public interface EntTlRoleSkillLinkDao {

	public abstract EntTlRoleSkillLink create(EntTlRoleSkillLink entTlRoleSkillLink) throws Exception;
	public abstract EntTlRoleSkillLink update(EntTlRoleSkillLink entTlRoleSkillLink) throws Exception;
	public abstract EntTlRoleSkillLink delete(EntTlRoleSkillLink entTlRoleSkillLink) throws Exception;

}

