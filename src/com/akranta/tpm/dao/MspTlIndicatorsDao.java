package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.MilestoneBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MspTlIndicators;

public interface MspTlIndicatorsDao {

	public abstract MspTlIndicators create(MspTlIndicators mspTlIndicators,MilestoneBean msBean) throws Exception;
	public abstract MspTlIndicators update(MspTlIndicators mspTlIndicators,MilestoneBean msBean) throws Exception;
	public abstract MspTlIndicators delete(MspTlIndicators mspTlIndicators) throws Exception;
	public MspTlIndicators assignParent(MspTlIndicators mspTlIndicators)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public MspTlIndicators select(String nodeId,String cellId) 	throws Exception; 
	public String getTitle(String cellId) throws Exception;
	public List<MspTlIndicators> getMasterPlanActivities(MspTlIndicators mspTlIndicators) throws Exception;
	public List<String[]> getIndicatorsGrid(CommonFilter commonFilter) throws Exception;
	public List<String[]> getPlanActual(CommonFilter commonFilter,String pillar)throws Exception;
	public List<String[]> getActivitiesForSubcategory(MspTlIndicators mspTlIndicators) throws Exception;
	public  List<String[]> getSearchIndicator(String indicatorId,String indName) throws Exception;
	public abstract Workbook getAllIndicatorsExcel(JSONObject jsonObject,CommonFilter commonFilter,String format) throws Exception;

}

