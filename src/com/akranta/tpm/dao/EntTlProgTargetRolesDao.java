package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.EntTlProgTargetRoles;

public interface EntTlProgTargetRolesDao {

	public abstract EntTlProgTargetRoles create(EntTlProgTargetRoles entTlProgTargetRoles) throws Exception;
	public abstract EntTlProgTargetRoles update(EntTlProgTargetRoles entTlProgTargetRoles) throws Exception;
	public abstract EntTlProgTargetRoles delete(EntTlProgTargetRoles entTlProgTargetRoles) throws Exception;
	public abstract List<String[]> getRolegridData(String progKeyId) throws Exception;
	public abstract EntTlProgTargetRoles getRoleformValues(String progKeyId) throws NoDataFoundException, SQLException, Exception;
	public  String delRolegridData(String prtrKeyid) throws Exception;
	public abstract String chkKeyRoleExists(String progKeyId) throws SQLException, Exception;

}

