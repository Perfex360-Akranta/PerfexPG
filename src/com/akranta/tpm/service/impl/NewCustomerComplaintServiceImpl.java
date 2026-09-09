package com.akranta.tpm.service.impl;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.QtmTlSapCustComplaintsDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.QtmTlSapCustComplaintsDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.NewCustomerComplaintService;

import net.sf.json.JSONObject;

public class NewCustomerComplaintServiceImpl implements NewCustomerComplaintService{
	private QtmTlSapCustComplaintsDao qtmTlsapCustComplaintsDao;
	DBActionTemplate  dbActionTemplate;
	public NewCustomerComplaintServiceImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		this.dbActionTemplate = dbActionTemplate;
		qtmTlsapCustComplaintsDao =  new QtmTlSapCustComplaintsDaoImpl(dbActionTemplate);
	}
	public List<String[]> custComList()throws Exception{
		
		return qtmTlsapCustComplaintsDao.custComList();
	}
    public List<String[]> customerList(String Keyid) throws Exception{
    	
    	return qtmTlsapCustComplaintsDao.customerList(Keyid);
    }
    public Workbook getCustomerComplaintExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		return this.qtmTlsapCustComplaintsDao.getCustomerComplaintExportToExcel(colmodel,format,commonFilter1);
	}
}
