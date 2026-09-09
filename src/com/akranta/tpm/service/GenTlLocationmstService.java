package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlLocationmstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.GenTlLocationmst;

public interface GenTlLocationmstService {
	public GenTlLocationmst create(GenTlLocationmst newGenTlLocationmst,GenTlLocationmst oldGenTlLocationmst,  GenTlLocationmstBean genTlLocationmstBean ) throws ValidationExceptions, Exception;
	public GenTlLocationmst update(GenTlLocationmst newGenTlLocationmst,GenTlLocationmst oldGenTlLocationmst,  GenTlLocationmstBean genTlLocationmstBean )  throws Exception;
	public GenTlLocationmst delete(GenTlLocationmst genTlLocationmst) throws Exception;
	//public List<ComboBox> getGenTlLocationmstcombo(String condSql)  throws Exception;
	public GenTlLocationmst select(String keyid) throws Exception;

}
