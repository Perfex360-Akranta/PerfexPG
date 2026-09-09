package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTaskMappingksadtl;
import com.akranta.tpm.model.EntTlTaskMappingksamst;

public interface EntTlTaskMappingksamstDao {

	public abstract EntTlTaskMappingksamst create(EntTlTaskMappingksamst entTlTaskMappingksamst, EntTlTaskMappingksadtl newentTlTaskMappingksadtl) throws Exception;
	public abstract EntTlTaskMappingksamst update(EntTlTaskMappingksamst entTlTaskMappingksamst, EntTlTaskMappingksadtl newentTlTaskMappingksadtl) throws Exception;
	public abstract EntTlTaskMappingksamst delete(EntTlTaskMappingksamst entTlTaskMappingksamst, String mastkeyid, String details) throws Exception;
	public abstract List<String[]> getKsaDetail(CommonFilter commonFilter, String mstkeyid);
	public abstract EntTlTaskMappingksamst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception;
	public abstract List<String[]> getKsaMainGrid(CommonFilter commonFilter) throws Exception;
	public abstract void DeleteRplist(String keyid, String deletechid) throws BusinessApplicationExceptions, Exception;
	public abstract Workbook TaskKsaExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws SQLException, Exception;
	public abstract EntTlTaskMappingksamst createmst(EntTlTaskMappingksamst newentTlTaskMappingksamst, EntTlTaskMappingksadtl entTlTaskMappingksadtl) throws Exception;
	public abstract EntTlTaskMappingksamst updatemst(EntTlTaskMappingksamst newentTlTaskMappingksamst, EntTlTaskMappingksadtl entTlTaskMappingksadtl) throws Exception;
}

