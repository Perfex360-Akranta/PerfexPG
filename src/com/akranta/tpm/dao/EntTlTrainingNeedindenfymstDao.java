package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTrainingNeedindenfymst;

public interface EntTlTrainingNeedindenfymstDao {
	public abstract EntTlTrainingNeedindenfymst create(EntTlTrainingNeedindenfymst entTlTrainingNeedindenfymst) throws Exception;
	public abstract EntTlTrainingNeedindenfymst update(EntTlTrainingNeedindenfymst entTlTrainingNeedindenfymst) throws Exception;
	public abstract EntTlTrainingNeedindenfymst delete(EntTlTrainingNeedindenfymst entTlTrainingNeedindenfymst) throws Exception;
	public abstract List<String[]> getTopic(String type,CommonFilter commonFilter, String flid) throws Exception;
}

