package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.ValidationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ProgrammstBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlProgrammst;
import com.akranta.tpm.model.EntTlUniqpostopicLinkmst;

public interface TrainingCalendarDao {
	public List<String[]> getTrainingCalendarList(CommonFilter commonFilter) throws Exception;

	public EntTlProgrammst create(EntTlProgrammst newEntTlProgrammst, ProgrammstBean programmstBean)throws Exception, BusinessApplicationExceptions, ValidationException;

	public EntTlProgrammst deleteRec(EntTlProgrammst newEntTlProgrammst)throws Exception;
	public String getVenuAlloated(String venuId, String fromTime, String tillTime) throws Exception ;
	public EntTlProgrammst getprogdata(String topicID, String progMonth, String uniquePos , String porkeyid,String flid)throws Exception;

	public List<String[]> getFaculty(String progId, String month, String uniqPos)throws Exception;

	public List<String[]> getbatch(String progKeyid, String month, String uniqPos)throws Exception;

	public EntTlProgrammst update(EntTlProgrammst newEntTlProgrammst, ProgrammstBean programmstBean)
			throws ValidationExceptions,Exception, BusinessApplicationExceptions;

	public String deleteDetail(String keyId, String gridId, String topicId)throws Exception,BusinessApplicationExceptions;

	public List<String[]> getUpbasedEmployee( CommonFilter commonFilter,String progKeyid,String bachId,String flid,String progType,String roleId,String newFlid)throws Exception;

	public Workbook getTrngCalReportExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format)throws Exception;

	public List<String[]> getTrainingCalendarRPT(CommonFilter commonFilter)throws Exception;

	public List<String[]> getselectProgId(String progId) throws Exception;

	public Workbook getTrngCalExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws IOException, SQLException, Exception;

	public Boolean getFrequecyData(String topicID) throws Exception;

	public String getPermStrength(String venuId)throws Exception;

	public List<String[]> getUniqPosData(String progKeyid)throws Exception;
	
	public String getUpbasedEmployeeCount(CommonFilter commonFilter,String progKeyid,String bachId,String flid,String progType, String roleId,String newFlid) throws Exception ;
    
}
