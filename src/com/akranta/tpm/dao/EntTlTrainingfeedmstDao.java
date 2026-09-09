package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTrainingfeedmst;

public interface EntTlTrainingfeedmstDao {

	public abstract EntTlTrainingfeedmst create(EntTlTrainingfeedmst entTlTrainingfeedmst) throws Exception;
	public abstract EntTlTrainingfeedmst update(EntTlTrainingfeedmst entTlTrainingfeedmst) throws Exception;
	public abstract EntTlTrainingfeedmst delete(EntTlTrainingfeedmst entTlTrainingfeedmst) throws Exception;
	public abstract EntTlTrainingfeedmst select(String keyid) throws NoDataFoundException, SQLException, Exception;
	public abstract Workbook TrainingFeedbackExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception;
	public abstract List<String[]> getTrainingFebMain(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getTrainingGrid(CommonFilter commonFilter,String typeCp, String keyid) throws Exception;
	public abstract List<String[]> getTrainingGridIn(CommonFilter commonFilter,String typeIP, String keyid) throws Exception;
	public abstract String getDesignation(String empId) throws Exception;
	public abstract List<String[]> gettrainingfeedbackexcelview(String tfbfId,String format, String path, String flid, String user,String title, CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> gettrainingfeedbackgridexcelview(String tfbfId, String format, String path, String flid,String user, String title, CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> gettrainingfeedbackscndgridexcelview(String tfbfId, String format, String path, String flid,String user, String title, CommonFilter commonFilter)throws Exception;

}

