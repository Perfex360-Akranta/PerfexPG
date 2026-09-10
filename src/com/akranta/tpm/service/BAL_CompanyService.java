package com.akranta.tpm.service;

import java.util.HashMap;
import java.util.List;

import com.akranta.tpm.bean.BAL_CompanyBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.BAL_Company;

public interface BAL_CompanyService {
	
	public BAL_CompanyBean createCompany(BAL_CompanyBean companyBean )throws Exception ;
	public BAL_CompanyBean updateCompany(BAL_CompanyBean companyBean ) throws Exception;
	public BAL_CompanyBean deleteCompany(BAL_CompanyBean companyBean ) throws Exception;
	public BAL_Company getCompany(BAL_CompanyBean companyBean ) throws Exception;
	
	public List<ComboBox> getCompanyComboList(ComboFilter comboFilter) throws Exception;
	
}
