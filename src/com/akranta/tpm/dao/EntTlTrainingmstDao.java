package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTrainingmst;

public interface EntTlTrainingmstDao {

	public abstract EntTlTrainingmst create(EntTlTrainingmst entTlTrainingmst) throws Exception;
	public abstract EntTlTrainingmst update(EntTlTrainingmst entTlTrainingmst) throws Exception;
	public abstract EntTlTrainingmst delete(EntTlTrainingmst entTlTrainingmst) throws Exception;
	public abstract List<String[]> getTrainingGrid(CommonFilter commonFilter,String typeCp,String keyid) throws Exception;
	public abstract List<String[]> getTrainingGridIn(CommonFilter commonFilter,String typeIP,String keyid) throws Exception;
	public abstract List<String[]> getTrainingFebMain(CommonFilter commonFilter) throws Exception;
	public abstract EntTlTrainingmst select(String  tmstKeyid) throws NoDataFoundException, SQLException, Exception;

}

