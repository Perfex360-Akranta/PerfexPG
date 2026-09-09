package com.akranta.tpm.dao;

import com.akranta.tpm.model.EntTlRoleSkillRating;

public interface EntTlRoleSkillRatingDao {

	public abstract EntTlRoleSkillRating create(EntTlRoleSkillRating entTlRoleSkillRating) throws Exception;
	public abstract EntTlRoleSkillRating update(EntTlRoleSkillRating entTlRoleSkillRating) throws Exception;
	public abstract EntTlRoleSkillRating delete(EntTlRoleSkillRating entTlRoleSkillRating) throws Exception;

}

