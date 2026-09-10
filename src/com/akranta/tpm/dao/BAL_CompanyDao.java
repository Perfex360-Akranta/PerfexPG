package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.BAL_Company;

public interface BAL_CompanyDao {
	public BAL_Company create(BAL_Company company) throws Exception;
	public BAL_Company update(BAL_Company company) throws Exception;
	public BAL_Company delete(BAL_Company company) throws Exception;
	public BAL_Company getComapany(String keyId) throws Exception;
	public List<ComboBox> getCompanyComboList(ComboFilter comboFilter) throws Exception;
}
