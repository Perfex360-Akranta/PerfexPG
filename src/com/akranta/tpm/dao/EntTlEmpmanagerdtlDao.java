package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.EntTlEmpmanagerdtl;


public interface EntTlEmpmanagerdtlDao {

	public abstract EntTlEmpmanagerdtl create(EntTlEmpmanagerdtl entTlEmpmanagerdtl) throws Exception;
	public abstract EntTlEmpmanagerdtl update(EntTlEmpmanagerdtl entTlEmpmanagerdtl) throws Exception;
	public abstract EntTlEmpmanagerdtl delete(EntTlEmpmanagerdtl entTlEmpmanagerdtl) throws Exception;
	public EntTlEmpmanagerdtl select(String manIdField) throws Exception;
	public List<String[]> getEmployeeDet(String ManIdField,String EmpIdFeild)throws Exception;
	public List<String[]> getEmployeeDetView()throws Exception;
}

