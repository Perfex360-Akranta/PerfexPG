package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSelfnominationmst;

public interface EntTlSelfnominationmstDao {

	public abstract List<EntTlSelfnominationmst> create(List<EntTlSelfnominationmst> nominationList1, String nominatonDelete) throws Exception;
	public abstract List<EntTlSelfnominationmst> update(List<EntTlSelfnominationmst> nominationList1, List<EntTlSelfnominationmst> nominationDelete) throws Exception;
	public abstract List<EntTlSelfnominationmst> delete(List<EntTlSelfnominationmst> nominationList1) throws Exception;
	public abstract Workbook SelfNominationExcel(CommonFilter commonFilter,JSONObject colmodel, String format, String empid) throws IOException, SQLException, Exception;	
}

