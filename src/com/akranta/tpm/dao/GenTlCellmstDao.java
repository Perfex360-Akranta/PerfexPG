package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GenTlCellmst;

public interface GenTlCellmstDao {

	public abstract GenTlCellmst create(GenTlCellmst genTlCellmst) throws Exception;
	public abstract GenTlCellmst update(GenTlCellmst genTlCellmst) throws Exception;
	public abstract GenTlCellmst delete(String delemode,GenTlCellmst genTlCellmst) throws Exception;
	public abstract GenTlCellmst select(String keyid) throws Exception;
	public abstract List<String[]> getGenTlCellmst(CommonFilter commonFilter);
	public String getCompany(GenTlCellmst genTlCellmst) throws SQLException;
	public abstract GenTlCellmst fillcellcontrol(String cellkeyid) throws Exception;

}

