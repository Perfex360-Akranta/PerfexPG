package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.AdmTlDashboadUserrights;
import com.akranta.tpm.model.CommonFilter;

public interface AdmTlDashboadUserrightsDao {

	
	public abstract AdmTlDashboadUserrights update(AdmTlDashboadUserrights admTlDashboadUserrights) throws Exception;
	public abstract AdmTlDashboadUserrights delete(AdmTlDashboadUserrights admTlDashboadUserrights) throws Exception;
	public abstract List<AdmTlDashboadUserrights> create(List<AdmTlDashboadUserrights> newDBUserRightsList) throws Exception;
	public abstract Workbook getDBUserRightsExcel(CommonFilter commonFilter, JSONObject colModel,String rptFormat) throws IOException, SQLException, Exception;
	public abstract List<String[]> getDBUserRights() throws Exception;

}

