package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTaskMappingksamst;
import com.akranta.tpm.model.EntTlTaskdtl;
import com.akranta.tpm.model.EntTlTaskmst;

public interface EntTlTaskmstDao {

	public abstract EntTlTaskmst create(EntTlTaskmst entTlTaskmst, EntTlTaskdtl newEntTlTaskdtl) throws Exception;
	public abstract EntTlTaskmst update(EntTlTaskmst entTlTaskmst, EntTlTaskdtl newEntTlTaskdtl) throws Exception;
	public abstract EntTlTaskmst delete(EntTlTaskmst entTlTaskmst, String mastkeyid, String details) throws Exception;
	public abstract EntTlTaskmst createmst(EntTlTaskmst newEntTlTaskmst, EntTlTaskdtl newEntTlTaskdtl) throws Exception;
	public abstract EntTlTaskmst updatemst(EntTlTaskmst newEntTlTaskmst, EntTlTaskdtl newEntTlTaskdtl) throws Exception;
	public abstract List<String[]> getKsaMainGrid(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getKsaDetail(CommonFilter commonFilter,String mstkeyid, String flid, String unique);
	public abstract Workbook TaskKsaExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws IOException, SQLException, Exception;
	public abstract EntTlTaskmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception;
	public abstract void DeleteRplist(String keyid, String deletechid, String[] detials) throws Exception;
	public abstract List<String[]> getnewSelect(String flid, String unique);
}

