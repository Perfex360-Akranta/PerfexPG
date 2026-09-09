package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.AdmTlLoginframework;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;

public interface AdmTlLoginframeworkDao {

	List<String[]> getReleaseGrid(CommonFilter commonFilter, String statusType) throws Exception;

	List<AdmTlUsermst> Update(AdmTlUsermst newAdmTlUsermst,
			List<AdmTlUsermst> newadmTlUsermst2, String statusType);

	Workbook getReleasLockedAccountExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter, String statusType) throws Exception;

	AdmTlLoginframework ChangeRleasePassrd(AdmTlLoginframework loginframework,
			String defpasswrd) throws Exception;

	public abstract List<String[]> getloginframeworkgrid() throws Exception;
	public abstract AdmTlLoginframework create(AdmTlLoginframework admTlLoginframework) throws Exception;
	public abstract AdmTlLoginframework update(AdmTlLoginframework admTlLoginframework) throws Exception;
	public abstract AdmTlLoginframework delete(AdmTlLoginframework admTlLoginframework) throws Exception;

	public AdmTlLoginframework getloginframwworkdata() throws Exception;

	public String passwordhistory(String userkeyid, String password) throws Exception;

	

	
	
}
