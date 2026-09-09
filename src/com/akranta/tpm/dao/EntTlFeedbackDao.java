package com.akranta.tpm.dao;

import com.akranta.tpm.model.EntTlFeedback;

public interface EntTlFeedbackDao {

	public abstract EntTlFeedback create(EntTlFeedback entTlFeedback) throws Exception;
	public abstract EntTlFeedback update(EntTlFeedback entTlFeedback) throws Exception;
	public abstract EntTlFeedback delete(EntTlFeedback entTlFeedback) throws Exception;

}

