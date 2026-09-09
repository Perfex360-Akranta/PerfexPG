package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.GenTlEmployeecost;

public interface GenTlEmployeecostDao {

	
	public abstract GenTlEmployeecost create(GenTlEmployeecost genTlEmployeecost) throws Exception;
	public abstract GenTlEmployeecost update(GenTlEmployeecost genTlEmployeecost) throws Exception;
	public abstract GenTlEmployeecost delete(GenTlEmployeecost genTlEmployeecost) throws Exception;

}

