package com.akranta.tpm.service.impl;

import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.BAL_PlmTlShutdowncalDao;
import com.akranta.tpm.dao.BAL_PlmTlStandardsDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.BAL_PlmTlShutdowncalDaoImpl;
import com.akranta.tpm.dao.impl.BAL_PlmTlStandardsDaoImpl;
import com.akranta.tpm.service.BAL_PlmTlShutdowncalservice;
import com.akranta.tpm.service.BAL_PlmTlStandardsService;
import com.akranta.tpm.utils.Validations;

public class BAL_PlmTlShutdowncalserviceImpl implements BAL_PlmTlShutdowncalservice {
	private CommonFilterDao commonFilterDao;
	private BAL_PlmTlShutdowncalDao plmTlShutdowncalDao;
	private Validations validations; 
	public BAL_PlmTlShutdowncalserviceImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		plmTlShutdowncalDao = new BAL_PlmTlShutdowncalDaoImpl(dbActionTemplate); 
		validations  = new Validations();
	}
}
