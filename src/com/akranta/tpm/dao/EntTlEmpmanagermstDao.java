package com.akranta.tpm.dao;

import com.akranta.tpm.model.EntTlEmpmanagermstModel;


public interface EntTlEmpmanagermstDao {

	public abstract EntTlEmpmanagermstModel create(EntTlEmpmanagermstModel entTlEmpmanagermst) throws Exception;
	public abstract EntTlEmpmanagermstModel update(EntTlEmpmanagermstModel entTlEmpmanagermst) throws Exception;
	public  String delete(EntTlEmpmanagermstModel entTlEmpmanagermst) throws Exception;
	public abstract  void DeleteEmployeeDet(String EmpDetailkeyId) throws Exception;

}

