package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.bean.ProgramBatchLinkBean;
import com.akranta.tpm.model.EntTlBatchSchedule;

public interface EntTlBatchScheduleDao {

	public abstract EntTlBatchSchedule create(EntTlBatchSchedule entTlBatchSchedule) throws Exception;
	public abstract EntTlBatchSchedule update(EntTlBatchSchedule entTlBatchSchedule) throws Exception;
	public abstract EntTlBatchSchedule delete(EntTlBatchSchedule entTlBatchSchedule) throws Exception;

	public EntTlBatchSchedule multiQuery(EntTlBatchSchedule entTlBatchSchedule) 	throws Exception ;
		
	
	public List<String[]> getProgBatchGrid(String fromDt, String tillDt, String progId, String batchId) throws Exception;
	
	public List<String[]> getBatchDates(String batchId) throws Exception;
	public List<String[]> getCalendarKeyid(String progId, String month) throws Exception;
	
	public List<String[]> getFutureDays(String progId, String batchId , ProgramBatchLinkBean programBatchLinkBean) throws Exception;
	public abstract String getProgDuration(String progId) throws Exception;
}

