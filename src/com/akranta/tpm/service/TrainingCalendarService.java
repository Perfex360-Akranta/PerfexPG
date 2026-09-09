package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ProgrammstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlProgrammst;
import com.akranta.tpm.model.EntTlUniqpostopicLinkmst;

public interface TrainingCalendarService {
	public List<String[]> getTrainingCalendarList(CommonFilter commonFilter) throws Exception;

	public List<ComboBox> getTopic(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;

	public EntTlProgrammst create(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean)
					throws ValidationExceptions,Exception, BusinessApplicationExceptions;

	public EntTlProgrammst update(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean)
					throws ValidationExceptions,Exception, BusinessApplicationExceptions;

	public EntTlProgrammst deleteRec(EntTlProgrammst newEntTlProgrammst)throws Exception;

	public String getVenuAlloated(String venuId, String fromTime, String tillTime)throws Exception;
	public EntTlProgrammst getprogdata(String topicID, String progMonth, String uniquePos, String porkeyid,String flid)throws Exception;

	public List<String[]> getFaculty(String progKeyid, String month, String uniqPos)throws Exception;

	public List<String[]> getbatch(String progKeyid, String month, String uniqPos)throws Exception;

	public String deleteDetail(String keyId, String gridId, String topicId)throws Exception,BusinessApplicationExceptions;

	public List<String[]> getUpbasedEmployee( CommonFilter commonFilter,String progKeyid,String bachId,String flid, String progType,String roleId,String newFlid)throws Exception;

	public Workbook getTrngCalReportExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;

	public List<String[]> getTrainingCalendarRPT(CommonFilter commonFilter)throws Exception;

	public List<String[]> selectProgId(String progId) throws Exception;

	public Workbook getTrngCalExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws IOException, SQLException, Exception;

	public  Boolean getFrequecyData(String topicID) throws Exception;

	public String getPermStrength(String venuId) throws Exception;

	public List<String[]> getUniqPosData(String progKeyid) throws Exception;
	
	public String getUpbasedEmployeeCount(CommonFilter commonFilter,String progKeyid,String bachId,String flid,String progType,String roleId,String newFlid) throws Exception ;
	
	public List<ComboBox> getFacultyComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
 

    
}
