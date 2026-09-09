package com.akranta.tpm.dao;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AdmTlRoleMenuLink;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeemstUpl;
import com.akranta.tpm.upload.UploadException;

public interface EmployeeUploadDao {
public String populateTempTable(String excelFileName,
			GenTlEmployeemstUpl genTlEmployeemstUpl) throws UploadException, ValidationExceptions, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, IOException;
public List<String[]> getEmployeeList(CommonFilter commonFilter)throws Exception;
public String EmployeeUploadValidate(String errflag) throws Exception;
public String EmployeeUploadDelete(String errflag) throws Exception;
public String EmployeeCreate(String errflag) throws Exception;
public String ErrorFlagCount() throws Exception;
public abstract void resetpwd(List<String> loginIds) throws Exception;
public List<String[]> ActiveMenuList(CommonFilter commonFilter, GridParams gridParams) throws Exception;
public List<AdmTlRoleMenuLink> createBasis(List<AdmTlRoleMenuLink> employeeAddList, String mocKeyid) throws Exception;
public List<String[]> getEmpData(GridParams gridParams,String location)throws Exception;
public abstract void EmpActive(List<String> EmpIds) throws Exception;
public abstract void EmpInActive(List<String> loginId,String ValidTillDate) throws Exception;
public List<String[]> getDocData(String RefDocType) throws Exception;

}
