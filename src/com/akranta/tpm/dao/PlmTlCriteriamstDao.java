package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlCriteriamst;

public interface PlmTlCriteriamstDao {

	public abstract PlmTlCriteriamst create(PlmTlCriteriamst plmTlCriteriamst) throws Exception;
	public abstract PlmTlCriteriamst update(PlmTlCriteriamst plmTlCriteriamst) throws Exception;
	public abstract PlmTlCriteriamst delete(PlmTlCriteriamst plmTlCriteriamst) throws Exception;
	public abstract List<String[]> getCriteriaMstList(CommonFilter commonFilter)throws Exception;

}

