package com.akranta.tpm.dao;

import com.akranta.tpm.model.EntTlBudgetExpensetype;

public interface EntTlBudgetExpensetypeDao {

	public abstract EntTlBudgetExpensetype create(EntTlBudgetExpensetype entTlBudgetExpensetype) throws Exception;
	public abstract EntTlBudgetExpensetype update(EntTlBudgetExpensetype entTlBudgetExpensetype) throws Exception;
	public abstract EntTlBudgetExpensetype delete(EntTlBudgetExpensetype entTlBudgetExpensetype) throws Exception;

}

