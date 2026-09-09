package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlUniqpostopicLinkmst;

public interface EntTlUniqpostopicLinkmstDao {

	public abstract EntTlUniqpostopicLinkmst create(EntTlUniqpostopicLinkmst entTlTaskMappingTopicmst) throws Exception;
	public abstract EntTlUniqpostopicLinkmst update(EntTlUniqpostopicLinkmst entTlTaskMappingTopicmst) throws Exception;
	public abstract EntTlUniqpostopicLinkmst delete(EntTlUniqpostopicLinkmst entTlTaskMappingTopicmst) throws Exception;
	public abstract List<String[]> getTaskMainGrid(CommonFilter commonFilter) throws Exception;
	public abstract EntTlUniqpostopicLinkmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception;
	public abstract List<String[]> getTopicDetail(CommonFilter commonFilter, String flid, String uniquePostion, String mstkeyid, String createmode, String topic);
	public abstract Workbook TaskTopicExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws SQLException, Exception;
	public abstract void DeleteTasklist(String keyid) throws BusinessApplicationExceptions, Exception;

}

