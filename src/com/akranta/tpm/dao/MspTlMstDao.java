package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.MilestoneBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MspTlDtl;
import com.akranta.tpm.model.MspTlMst;

public interface MspTlMstDao {

	public abstract MspTlMst create(MspTlMst mspTlMst, MilestoneBean msBean) throws Exception,BusinessApplicationExceptions;
	public abstract MspTlMst update(MspTlMst mspTlMst) throws Exception;
	public abstract MspTlMst delete(MspTlMst mspTlMst) throws Exception;
	public abstract MspTlDtl deleteMilestone(MspTlDtl mspTlDtl) throws Exception;
	public abstract MspTlMst deleteMilestone(MspTlMst mspTlMst,MilestoneBean msBean)throws Exception;
	public List<String[]> getAllMilestones(CommonFilter commonFilter,String indicatorId,String cellId,String mode,String fromDate) throws Exception;
	public List<String[]> getAllHistory(String dtlId) throws Exception;
	public MspTlMst selectMst(String indicatorId,String cellId,String fromDate,String toDate,String flag)throws Exception;
	public List<String[]> getColorsFromConfiguration() throws Exception;
	public abstract Workbook getPlanActualExcel(String pillar,JSONObject jsonObject,CommonFilter commonFilter,String format) throws Exception;
	public abstract Workbook getAllHistoryExcel(String dtlId,JSONObject jsonObject,String format)throws Exception;

}

