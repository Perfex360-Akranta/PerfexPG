package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EmpMangerLinkBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.EntTlEmpmanagermstModel;
import com.akranta.tpm.model.EntTlEmpmanagerdtl;



public interface EntEmpMangerLinkService {
	public EntTlEmpmanagermstModel create(EntTlEmpmanagermstModel entTlEmpmanagermstModel,EntTlEmpmanagermstModel oldEntTlEmpmanagermstModel, EmpMangerLinkBean empMangerLinkBean ) throws ValidationExceptions, Exception;
	public EntTlEmpmanagermstModel update(EntTlEmpmanagermstModel entTlEmpmanagermstModel,EntTlEmpmanagermstModel oldEntTlEmpmanagermstModel,  EmpMangerLinkBean empMangerLinkBean )  throws Exception;	
	public String delete(EntTlEmpmanagermstModel genTlMouldmst) throws Exception;
	void DeleteEmployeeDet(String EmpDetailkeyId) throws Exception;
	public List<ComboBox> getManagerId(String condSql) throws Exception;
	public List<ComboBox> getEmployeeId(String condSql) throws Exception;
	public EntTlEmpmanagerdtl select(String manIdField) throws Exception;
	public List<String[]> getEmployeeDet(String ManIdField,String EmpIdField)throws Exception;
	public List<String[]> getEmployeeDetView()throws Exception;
	

}
