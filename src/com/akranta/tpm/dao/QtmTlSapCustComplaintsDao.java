package com.akranta.tpm.dao;


import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.UserLoginDetailsBean;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.AdmTlLoginframework;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
public interface QtmTlSapCustComplaintsDao {
 public List<String[]> custComList()throws Exception;
 public List<String[]> customerList(String Keyid)throws Exception;
 public Workbook getCustomerComplaintExportToExcel(JSONObject colmodel,String format,
			CommonFilter commonFilter1)throws Exception;
}

