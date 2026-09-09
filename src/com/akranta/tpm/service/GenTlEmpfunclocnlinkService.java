package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.dao.JhaTlFiveSAuditareamstDao;
import com.akranta.tpm.model.GenTlEmpfunclocnlink;

 //JhaTlFiveSAuditareamstDao jhaTlFiveSAuditareamstDao;

public interface GenTlEmpfunclocnlinkService {
    //GenTlEmpfunclocnlink
	/*public List<GenTlEmpfunclocnlink> create(GenTlEmpfunclocnlink newGenTlEmpfunclocnlink,
			GenTlEmpfunclocnlink existGenTlEmpfunclocnlink,
			EmployeeBean employeeBean) throws Exception;*/

	public	List<GenTlEmpfunclocnlink> create(
			GenTlEmpfunclocnlink newGenTlEmpfunclocnlink,
			GenTlEmpfunclocnlink existGenTlEmpfunclocnlink,
			EmployeeBean employeeBean,
			List<GenTlEmpfunclocnlink> FactorylinkList, String Factoryvar) throws Exception;

	/*List<GenTlEmpfunclocnlink> create(
			List<GenTlEmpfunclocnlink> newGenTlEmpfunclocnlink,
			List<GenTlEmpfunclocnlink> existGenTlEmpfunclocnlink,
			EmployeeBean employeeBean,
			List<GenTlEmpfunclocnlink> FactorylinkList, String Factoryvar)
			throws Exception;*/

	

}
