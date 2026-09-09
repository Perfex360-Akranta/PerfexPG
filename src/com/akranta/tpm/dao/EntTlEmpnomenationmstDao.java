package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.EntTlEmpnomenationmst;

public interface EntTlEmpnomenationmstDao {

	public abstract List<EntTlEmpnomenationmst> create(List<EntTlEmpnomenationmst> newentTlEmpnomenationmst) throws Exception;
	public abstract List<EntTlEmpnomenationmst> update(List<EntTlEmpnomenationmst> newentTlEmpnomenationmst) throws Exception;
	public abstract EntTlEmpnomenationmst delete(EntTlEmpnomenationmst entTlEmpnomenationmst) throws Exception;
	public abstract List<String[]> getnewempnominationDetail() throws Exception;
	public abstract List<String[]> EmpnominationReview() throws Exception;
	public abstract List<String[]> getResultGridEmp(String form, String keyid)throws Exception;

}

