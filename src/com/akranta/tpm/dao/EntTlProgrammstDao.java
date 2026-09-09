package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.EntTlProgrammst;

public interface EntTlProgrammstDao {

	public abstract EntTlProgrammst create(EntTlProgrammst entTlProgrammst) throws Exception;
	public abstract EntTlProgrammst update(EntTlProgrammst entTlProgrammst) throws Exception;
	public abstract EntTlProgrammst delete(EntTlProgrammst entTlProgrammst) throws Exception;
	public abstract List<String[]> getgridData() throws Exception;
	public abstract EntTlProgrammst fillFormvalues(String progKey) throws NoDataFoundException, SQLException, Exception;
	public abstract String getevalTypeId(String topickeyId)throws Exception;

}

