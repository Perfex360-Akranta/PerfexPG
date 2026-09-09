package com.akranta.tpm.dao;

import com.akranta.tpm.model.EntTlRoleTopicLink;

public interface EntTlRoleTopicLinkDao {

	public abstract EntTlRoleTopicLink create(EntTlRoleTopicLink entTlRoleTopicLink) throws Exception;
	public abstract EntTlRoleTopicLink update(EntTlRoleTopicLink entTlRoleTopicLink) throws Exception;
	public abstract EntTlRoleTopicLink delete(EntTlRoleTopicLink entTlRoleTopicLink) throws Exception;

}

