package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.BAL_GenTlCellmst;

public interface BAL_GenTlCellmstDao {

	public abstract BAL_GenTlCellmst create(BAL_GenTlCellmst genTlCellmst) throws Exception;
	public abstract BAL_GenTlCellmst update(BAL_GenTlCellmst genTlCellmst) throws Exception;
	public abstract BAL_GenTlCellmst delete(String delemode,BAL_GenTlCellmst genTlCellmst) throws Exception;
	public abstract BAL_GenTlCellmst select(String keyid) throws Exception;
	public abstract List<String[]> getGenTlCellmst(CommonFilter commonFilter);
	public String getCompany(BAL_GenTlCellmst genTlCellmst) throws SQLException;
	public abstract BAL_GenTlCellmst fillcellcontrol(String cellkeyid) throws Exception;

}

