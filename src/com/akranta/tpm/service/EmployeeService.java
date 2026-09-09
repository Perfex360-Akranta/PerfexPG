package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlLayoutfieldimg;
import com.akranta.tpm.model.KznTlMst;

public interface EmployeeService {
	
	public GenTlEmployeemst create(GenTlEmployeemst newGenTlEmployeemst,GenTlEmployeemst oldGenTlEmployeemst,  EmployeeBean employeeBean ) throws ValidationExceptions, Exception;
	public GenTlEmployeemst update(GenTlEmployeemst newGenTlEmployeemst,GenTlEmployeemst oldGenTlEmployeemst,  EmployeeBean employeeBean )  throws Exception;
	public GenTlEmployeemst delete(GenTlEmployeemst genTlEmployeemst) throws Exception;
	
		
	public GenTlEmployeemst select(String keyid) throws Exception;
	public GenTlEmployeedtl getselect(String keyid) throws Exception;
	public  List<String[]> getAllEmployeeService() throws Exception;
	
	public List<ComboBox> getdepartmentcombo(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getEMPMdesignation(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getEMPMGrade(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getEMPMTrade(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getEmpmKeyid(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getEmpmCity(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getEMPMNAME(String condSql,ComboFilter comboFilter) throws Exception;
	
	public List<ComboBox> getEmpdStateid(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getEmpdCountryid(String condSql,ComboFilter comboFilter) throws Exception;

	public GenTlEmployeeimg selectImg(String nodeId) throws Exception;
	public GenTlEmployeeimg getLayoutImg(GenTlEmployeeimg genTlEmployeeimg) throws Exception;
	
	public List<String[]> getAllFactoryname(String Employeeid,String Funloclink) throws Exception;
	public List<String[]> getAllSectionName(String Employeeid,String Funloclink) throws Exception;
	public List<String[]> getAllLineName(String Employeeid,String Funloclink) throws Exception;
	public List<String[]> getAllEquipmentName(String Employeeid,String Funloclink)throws Exception;
	public List<String[]> getRoleEmpGrid(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getcompanycombo(ComboFilter currentFilter) throws Exception;
	public List<ComboBox> getlocationcombo(ComboFilter comboFilter) throws Exception; 
	public String EmployeeData(String empKeyid,String empName,String empPhoneNo,String empEmail) throws Exception;
	public GenTlEmployeemst imagecreate(GenTlEmployeemst newGenTlEmployeemst,GenTlEmployeemst oldGenTlEmployeemst,  EmployeeBean employeeBean,String userkeyid ) throws ValidationExceptions, Exception;
	public List<String[]> getEmpData(String userKeyid)throws Exception;
	
	public void EmployeeServiceImplJwt(String JwtToken);

}
