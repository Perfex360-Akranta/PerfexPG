package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlProgCalendar;

public interface EntTlProgCalendarDao {

	public abstract List<EntTlProgCalendar> create(List<EntTlProgCalendar> entTlProgCalendar) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public abstract List<EntTlProgCalendar> update(List<EntTlProgCalendar> entTlProgCalendar) throws Exception;
	public abstract List<EntTlProgCalendar> delete(List<EntTlProgCalendar> entTlProgCalendar) throws Exception;
	public abstract EntTlProgCalendar select(EntTlProgCalendar entTlProgCalendar) throws Exception;
	public List<String[]> selectSchedule(EntTlProgCalendar EntTlProgCalendar)throws Exception;
	public abstract List<EntTlProgCalendar> selectList(EntTlProgCalendar entTlProgCalendar) throws Exception;
	public List<String[]> getDatesList(String month,int noOfMonths,String ProgId)throws Exception;
	public List<String[]> getProgCalendergridData(String month,int noOfMonths,String ProgId,String frmType)throws Exception;
	public String getStartMonth()throws Exception;
	public abstract List<String[]> getSelfNominationReport(CommonFilter commonFilter, String empId, String progId, String batchId, String userid, String Status)throws Exception;
	public abstract List<String[]> getSelfNominationPopUp(CommonFilter commonFilter, String empid)throws Exception;
	public abstract List<String[]> getSelfNominationMainGrid(CommonFilter commonFilter, String empid) throws Exception;
}

