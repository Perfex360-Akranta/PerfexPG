package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.EntTlBudgetmst;

public interface EntTlBudgetmstDao {

	public abstract EntTlBudgetmst create(EntTlBudgetmst entTlBudgetmst) throws Exception;
	public abstract EntTlBudgetmst update(EntTlBudgetmst entTlBudgetmst) throws Exception;
	public abstract EntTlBudgetmst delete(EntTlBudgetmst entTlBudgetmst) throws Exception;
	public List<String[]> getBudget(String batchId) throws Exception;
	public EntTlBudgetmst getBudgetFromKey(String budgetId) throws Exception;

}

