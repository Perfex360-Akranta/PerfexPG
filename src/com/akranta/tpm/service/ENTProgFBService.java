package com.akranta.tpm.service;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.EntTlFeedback;
import com.akranta.tpm.model.EntTlFeedbackparammst;

import java.util.List;

public interface ENTProgFBService {
	public abstract EntTlFeedbackparammst create(EntTlFeedbackparammst newEntTlFeedbackparammst,EntTlFeedbackparammst oldEntTlFeedbackparammst) throws Exception;
	public abstract EntTlFeedbackparammst update(EntTlFeedbackparammst newEntTlFeedbackparammst,EntTlFeedbackparammst oldEntTlFeedbackparammst) throws Exception;
	public abstract EntTlFeedbackparammst delete(EntTlFeedbackparammst entTlFeedbackparammst) throws Exception;
	public abstract EntTlFeedback create(EntTlFeedback newEntTlFeedback,EntTlFeedback oldEntTlFeedback) throws Exception;
	public abstract EntTlFeedback update(EntTlFeedback newEntTlFeedback,EntTlFeedback oldEntTlFeedback) throws Exception;
	public abstract EntTlFeedback delete(EntTlFeedback entTlFeedback) throws Exception;
	public List<EntTlFeedbackparammst> getAllFeedback(EntTlFeedbackparammst entTlFeedbackparammst)throws Exception;
	public EntTlFeedbackparammst select(String keyid) throws Exception;
	public EntTlFeedbackparammst inactiveFB(EntTlFeedbackparammst entTlFeedbackparammst)throws Exception;
	public List<ComboBox> getComboFB(String condSql) throws Exception;
	public List<ComboBox> getComboFBParent(String condSql) throws Exception;
	public String getDisplayOrder(String parentId) throws Exception;
	public List<String[]> getEmployees(String progId,String fromDate,String toDate) throws Exception;
	public List<String[]> getQuestions() throws Exception;
	public List<String[]> getFBDatas(String empId,String progId) throws Exception;
	public List<String[]> getRating(String empId) throws Exception;
}
