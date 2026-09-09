package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.MilestoneBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MspTlIndicatorsDtl;
import com.akranta.tpm.model.MspTlIndicatorsMst;

public interface MspTlIndicatorsMstDao {

	public abstract MspTlIndicatorsDtl create(MspTlIndicatorsMst mspTlIndicatorsMst,MspTlIndicatorsDtl mspTlIndicatorsDtl,MilestoneBean msBean) throws Exception;
	public abstract MspTlIndicatorsDtl update(MspTlIndicatorsMst mspTlIndicatorsMst,MspTlIndicatorsDtl mspTlIndicatorsDtl,MilestoneBean msBean) throws Exception;
	public abstract MspTlIndicatorsDtl delete(MspTlIndicatorsMst mspTlIndicatorsMst,MspTlIndicatorsDtl mspTlIndicatorsDtl) throws Exception;
	public MspTlIndicatorsDtl assignParent(MspTlIndicatorsDtl mspTlIndicatorsDtl)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public MspTlIndicatorsDtl select(String nodeId,String Flid) 	throws Exception; 
	public String getTitle(String cellId,String pillar, String title) throws Exception;
	public List<MspTlIndicatorsDtl> getMasterPlanActivities(MspTlIndicatorsDtl mspTlIndicatorsDtl,MspTlIndicatorsMst mspTlIndicatorsMst) throws Exception;
	public List<String[]> getIndicatorsGrid(CommonFilter commonFilter,String pillar) throws Exception;
	public List<String[]> getPlanActual(CommonFilter commonFilter,String pillar,String masterkeyid)throws Exception;
	public List<String[]> getActivitiesForSubcategory(MspTlIndicatorsDtl mspTlIndicatorsDtl,MspTlIndicatorsMst mspTlIndicatorsMst) throws Exception;
	public  List<String[]> getSearchIndicator(String indicatorId,String indName) throws Exception;
	public abstract Workbook getAllIndicatorsExcel(JSONObject jsonObject,CommonFilter commonFilter,String format,String pillar) throws Exception;
	public abstract MspTlIndicatorsMst create(
			MspTlIndicatorsMst newMspTlIndicatorsMst,
			MspTlIndicatorsMst existMspTlIndicatorsMst, MilestoneBean msBean) throws Exception;
	public abstract MspTlIndicatorsMst update(
			MspTlIndicatorsMst newMspTlIndicatorsMst,
			MspTlIndicatorsMst existMspTlIndicatorsMst, MilestoneBean msBean) throws Exception;
	public abstract MspTlIndicatorsMst selectMasKeyid(String masterkeyid) throws Exception;
	public abstract List<String[]> getplandate(String nodeId) throws Exception;

}

