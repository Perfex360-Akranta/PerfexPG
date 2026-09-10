package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.bean.BAL_CompanyBean;
import com.akranta.tpm.dao.BAL_CompanyDao;
import com.akranta.tpm.dao.impl.BAL_CompanyDaoImpl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.BAL_Company;
import com.akranta.tpm.service.BAL_CompanyService;

public class BAL_CompanyServiceImpl implements BAL_CompanyService {
	
	private BAL_CompanyDao companyDao; 
	public BAL_CompanyServiceImpl(DBActionTemplate dbActionTemplate)
	{
		companyDao = new BAL_CompanyDaoImpl(dbActionTemplate);
	}
	
	public BAL_CompanyBean createCompany(BAL_CompanyBean companyBean ) throws Exception
	{
		companyBean.setCompanyMaster(this.companyDao.create(companyBean.getCompanyMaster()));
		
		return companyBean;
	}
	
	public BAL_CompanyBean updateCompany(BAL_CompanyBean companyBean ) throws Exception
	{
		companyBean.setCompanyMaster(this.companyDao.update(companyBean.getCompanyMaster()));
		
		return companyBean;
	}
	public BAL_CompanyBean deleteCompany(BAL_CompanyBean companyBean ) throws Exception
	{
		companyBean.setCompanyMaster(this.companyDao.delete(companyBean.getCompanyMaster()));
		
		return companyBean;
	}
	
	public BAL_Company getCompany(BAL_CompanyBean companyBean ) throws Exception
	{
		return this.companyDao.getComapany(companyBean.getCompanyMaster().getKeyid());
	}

	@Override
	public List<ComboBox> getCompanyComboList(ComboFilter comboFilter) throws Exception
	{	
		return this.companyDao.getCompanyComboList(comboFilter);
	}
	
}
