package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.ENTBatchComplnBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlBatchcompletion;
import com.akranta.tpm.model.EntTlBudgetmst;
import com.akranta.tpm.model.WomTlCommunicationlog;

public interface ENTBatchComplnService {
	List<ComboBox> getProgkeyidCombo(String condsql, ComboFilter comboFilter) throws Exception;
	List<ComboBox> getExpenseTypeCombo(String condsql, ComboFilter comboFilter) throws Exception;
	public List<String[]> getBatchComplnData(String progKeyId,String progYear) throws Exception;
	public List<String[]> getBatchComplnDatas(CommonFilter commonFilter) throws Exception;
	public List<String[]> getBudget(String batchId) throws Exception;
	public EntTlBudgetmst getBudgetFromKey(String budgetId) throws Exception;
	public EntTlBatchcompletion saveBatch(EntTlBatchcompletion newEntTlBatchcompletion,EntTlBatchcompletion oldEntTlBatchcompletion,ENTBatchComplnBean entBatchComplnBean,String localAddr) throws Exception;
	public EntTlBudgetmst saveBudget(EntTlBudgetmst newEntTlBudgetmst,EntTlBudgetmst oldEntTlBudgetmst)throws Exception;
	public EntTlBudgetmst updateBudget(EntTlBudgetmst newEntTlBudgetmst,EntTlBudgetmst oldEntTlBudgetmst)throws Exception;
	public EntTlBudgetmst deleteBudget(EntTlBudgetmst newEntTlBudgetmst)throws Exception;
	public String getBatchComplnremarks(String batchid)throws Exception;

}
