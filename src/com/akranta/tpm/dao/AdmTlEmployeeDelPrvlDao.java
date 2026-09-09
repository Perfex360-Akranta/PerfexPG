package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AdmTlEmployeeDelPrvl;
import com.akranta.tpm.model.CommonFilter;

public interface AdmTlEmployeeDelPrvlDao {
	 public List<String[]> EmployeeData(CommonFilter commonFilter) throws Exception;
	 public  AdmTlEmployeeDelPrvl EmployeeCreate(AdmTlEmployeeDelPrvl newadmTlEmployeeDelPrvl) throws Exception;
	 public String ChkEmployeeList(CommonFilter commonFilter) throws Exception;
	 public List<String[]> roleEmpList(CommonFilter commonFilter,GridParams gridparam)throws Exception;
	 public List<String[]> addEmpList(CommonFilter commonFilter,GridParams gridparam)throws Exception;
     public List<String[]> EmployeeList(CommonFilter commonFilter) throws Exception;
     public List<AdmTlEmployeeDelPrvl> AddEmployee(List<AdmTlEmployeeDelPrvl> admtlEmployeeDelPrvl) throws Exception;
     public List<String> EmployeeDeletelist(String Empid) throws Exception;
}
