package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.model.GenTlEmpfunclocnlink;

public interface GenTlEmpfunclocnlinkDao {

	public abstract List<GenTlEmpfunclocnlink> create(List<GenTlEmpfunclocnlink> newgentlempfunclocnlinkList, EmployeeBean employeeBean) throws Exception;
	public abstract GenTlEmpfunclocnlink update(GenTlEmpfunclocnlink genTlEmpfunclocnlink) throws Exception;
	public abstract GenTlEmpfunclocnlink delete(GenTlEmpfunclocnlink genTlEmpfunclocnlink) throws Exception;
	//public abstract List<GenTlEmpfunclocnlink> createFactory(
		//	GenTlEmpfunclocnlink newGenTlEmpfunclocnlink);
	//public abstract List<GenTlEmpfunclocnlink> create(
		//	GenTlEmpfunclocnlink newGenTlEmpfunclocnlink);
	//List<GenTlEmpfunclocnlink> factorylinkList
   public abstract List<GenTlEmpfunclocnlink> create(GenTlEmpfunclocnlink newGenTlEmpfunclocnlink) throws Exception;
}

