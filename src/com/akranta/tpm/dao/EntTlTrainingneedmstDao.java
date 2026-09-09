package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTrainingneedmst;

public interface EntTlTrainingneedmstDao {
	public abstract List<EntTlTrainingneedmst> create(List<EntTlTrainingneedmst> trainingNeedGridList1, List<EntTlTrainingneedmst> trainingKeyid) throws Exception;
	public abstract EntTlTrainingneedmst update(EntTlTrainingneedmst entTlTrainingNeedindenfymst) throws Exception;
	public abstract EntTlTrainingneedmst delete(EntTlTrainingneedmst entTlTrainingNeedindenfymst) throws Exception;
	public abstract List<String[]> getTopic(String type,CommonFilter commonFilter, String flid, String date) throws Exception;
	public abstract List<String[]> getTRneedMainGrid(CommonFilter commonFilter1) throws Exception;
	public abstract Workbook TrNeedExcel(CommonFilter commonFilter1,JSONObject colmodel, String format) throws SQLException, IOException, Exception;
	public abstract List<EntTlTrainingneedmst> deleteTrneed(List<EntTlTrainingneedmst> trainingKeyid) throws Exception;
	public abstract List<String[]> getTrneedReport(CommonFilter commonFilter1) throws Exception;
	public abstract Workbook TrNeedReportExcel(CommonFilter commonFilter1, JSONObject colmodel, String format) throws SQLException, Exception;
}

