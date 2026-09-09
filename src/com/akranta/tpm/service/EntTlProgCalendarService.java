package com.akranta.tpm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EntTlProgCalendarBean;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlProgCalendar;
import com.akranta.tpm.model.EntTlSelfnominationmst;


public interface EntTlProgCalendarService 
{

	public List<EntTlProgCalendar> create(List<EntTlProgCalendar> newEntTlProgCalendar,List<EntTlProgCalendar> existEntTlProgCalendar,
			EntTlProgCalendarBean EntTlProgCalendarBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public List<EntTlProgCalendar> update(List<EntTlProgCalendar> newEntTlProgCalendar,List<EntTlProgCalendar> existEntTlProgCalendar,
			EntTlProgCalendarBean EntTlProgCalendarBean)throws Exception;
	public List<EntTlProgCalendar> delete(List<EntTlProgCalendar>  newEntTlProgCalendar)throws Exception;
	public EntTlProgCalendar select(EntTlProgCalendar entTlProgCalendar)throws Exception;
	public List<String[]> selectSchedule(EntTlProgCalendar entTlProgCalendar)throws Exception;
	public List<EntTlProgCalendar> selectList(EntTlProgCalendar entTlProgCalendar)throws Exception;	
	public List<ComboBox> getProgramComboList(CommonFilter commonFilter)throws Exception ;
	public List<ComboBox> getSpokeComboList(CommonFilter commonFilter)throws Exception ;
	public List<String[]> getDatesList(String year,int noOfMonths,String ProgId)throws Exception ;
	public List<String[]> getProgCalendergridData(String year,int noOfMonths,String ProgId,String frmType)throws Exception ;
	public List<String[]> getSelfNominationReport(CommonFilter commonFilter, String EmpId, String ProgId, String BatchId, String userid, String Status)throws Exception ;
	public List<String[]> getSelfNominationPopUp(CommonFilter commonFilter, String empid)throws Exception ;
	public List<EntTlSelfnominationmst> createNomination(List<EntTlSelfnominationmst> NominationList1, String nominatonDelete) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException, Exception;
	public List<EntTlSelfnominationmst> updateNomination(List<EntTlSelfnominationmst> nominationList1, List<EntTlSelfnominationmst> nominationDelete) throws Exception;
	public List<String[]> getSelfNominationMainGrid(CommonFilter commonFilter, String empid) throws Exception;
	public Workbook SelfNominationExcel(CommonFilter commonFilter,JSONObject colmodel, String format, String empid) throws IOException, SQLException, Exception;
	public List<EntTlSelfnominationmst> deleteSelf(List<EntTlSelfnominationmst> nominationList1) throws Exception;
	
}
