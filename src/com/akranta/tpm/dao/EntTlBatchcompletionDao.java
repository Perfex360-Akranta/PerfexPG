package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.bean.ENTBatchComplnBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlBatchcompletion;

public interface EntTlBatchcompletionDao {

	public abstract EntTlBatchcompletion create(EntTlBatchcompletion entTlBatchcompletion,ENTBatchComplnBean entBatchComplnBean) throws Exception;
	public abstract EntTlBatchcompletion update(EntTlBatchcompletion entTlBatchcompletion) throws Exception;
	public abstract EntTlBatchcompletion delete(EntTlBatchcompletion entTlBatchcompletion) throws Exception;
	public EntTlBatchcompletion updateMailSent(EntTlBatchcompletion entTlBatchcompletion,String batchId) throws Exception;
	public List<String[]> getBatchComplnData(String progKeyId,String progYear) throws Exception;
	public List<String[]> getBatchComplnDatas(CommonFilter commonFilter) throws Exception;
	public List<String[]> getEmpMailId(String batchId) throws Exception;
	public EntTlBatchcompletion saveBatch(EntTlBatchcompletion newEntTlBatchcompletion) throws Exception;
	public abstract List<String[]> getManagerMailId(String batchId)throws Exception;
	public abstract String getBatchComplnremarks(String batchid)throws Exception;

}

