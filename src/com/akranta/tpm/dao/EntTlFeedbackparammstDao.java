package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.EntTlFeedbackparammst;

public interface EntTlFeedbackparammstDao {

	public abstract EntTlFeedbackparammst create(EntTlFeedbackparammst entTlFeedbackparammst) throws Exception;
	public abstract EntTlFeedbackparammst update(EntTlFeedbackparammst entTlFeedbackparammst) throws Exception;
	public abstract EntTlFeedbackparammst delete(EntTlFeedbackparammst entTlFeedbackparammst) throws Exception;
	public List<EntTlFeedbackparammst> getAllFeedback(EntTlFeedbackparammst entTlFeedbackparammst)throws Exception;
	public EntTlFeedbackparammst select(String keyid) throws Exception;
	public EntTlFeedbackparammst inactiveFB(EntTlFeedbackparammst entTlFeedbackparammst)throws Exception;
	public String getDisplayOrder(String parentId) throws Exception;
	public List<String[]> getEmployees(String progId,String fromDate,String toDate) throws Exception;
	public List<String[]> getQuestions() throws Exception;
	public List<String[]> getFBDatas(String empId,String progId) throws Exception;
	public List<String[]> getRating(String empId) throws Exception;

}

