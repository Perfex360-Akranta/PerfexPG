package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.xml.bind.ValidationException;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTrgCalEmp;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;
public interface NewTrgCalDao {
	public abstract List<String[]> getFaculty(String progKeyid) throws Exception;
	public List<String[]> getAllEmployee(CommonFilter commonFilter,GridParams gridParams) throws Exception;
	public List<String[]> getsession(String progKeyid)throws Exception;
	public List<String[]> getAllUniqueEmployee(CommonFilter commonFilter,GridParams gridParams) throws Exception;
	public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter) throws Exception;
	public List<String[]> getNewUniqPosData(String keyid) throws Exception;
	public EntTlTragcalmst create(EntTlTragcalmst entTlTragcalmst) throws Exception;
	public EntTlTragcalmst update(EntTlTragcalmst entTlTragcalmst) throws Exception;
	public EntTlTragcalmst deleteTrainingCal(EntTlTragcalmst newEntTlTragcalmst) throws Exception;
	public List<EntTlTrgCalEmp> createSessionEmployee(List<EntTlTrgCalEmp> entTlSessionEmployeeLink) throws Exception;
    public List<EntTlTrgCalEmp> DeleteSessionEmployee(List<EntTlTrgCalEmp> entTlSessionEmployeeLink) throws Exception;
    public List<EntTlTrgCalEmp> createSessionEmployeeUpdate(List<EntTlTrgCalEmp> entTlSessionEmployeeLink) throws Exception;
	public List<String[]> getflid(String originalid) throws Exception;
	public List<String[]> getTrainingCalendarList(GridParams gridparams, CommonFilter commonFilter) throws Exception;
	public List<String[]> getEmpWiseTrainingReport(CommonFilter commonFilter) throws Exception;
	public EntTlTragcalmst getselectdata(String calid) throws Exception;
	public String IsTrainingCompleted(CommonFilter commonFilter) throws Exception;
	public List<String[]> getElementId(String loginflid, String loginlevel,
	String loginElementid, String empId) throws Exception;
	public String getempdata(String calid) throws Exception;
	public String FacultyCheck(CommonFilter commonFilter) throws Exception;
	public String chkSessionDate(CommonFilter commonFilter) throws Exception;
	public List<EntTlTtgCalEmpatScore> createEmployeeAttendance(List<EntTlTtgCalEmpatScore> entEmployeeScore, CommonFilter commonfilter) throws Exception;
	public EntTlTrgCalEmp EmployeebyCreate(EntTlTrgCalEmp newEntTlTrgCalEmp) throws Exception;
    public String chkUniqueposition(CommonFilter commonFilter) throws Exception;
	public String chkEmployee(CommonFilter commonFilter) throws Exception;
	public List<String[]> chkJHforRole(CommonFilter commonFilter) throws Exception;
	public String deleteDetailRecord(String keyId, String gridId, String TrainingId)throws Exception,BusinessApplicationExceptions;
	public List<EntTlTtgCalEmpatScore> UpdateEmployeeAttendance(
			List<EntTlTtgCalEmpatScore> fillValuesEmployeeAttendance) throws Exception;
	public String getempattn(String calid) throws Exception;
	public String getCutoff(String calid) throws Exception;
	//------ Added by Vignesh 
	public String getAssesType(String calid) throws Exception;
	public String getMaxmarks(String calid) throws Exception;
	public String chkAssesmentComplted(CommonFilter commonFilter) throws Exception;
	public Workbook getAllEmployeeExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws  Exception;
	public Workbook getTrainingCalendarListExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws  Exception;
	public Workbook getEmpWiseTrainingCalendarListExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws  Exception;
	public  String getTrainingMode(String topicid) throws Exception;
	public  String getTrainingTopicType(String topicid) throws Exception;
    public EntTlTrgCalEmp EmployeeDelete(EntTlTrgCalEmp newEntTlTrgCalEmp) throws Exception;
    public List<String[]> gwtJHRoleUniquePos(CommonFilter commonFilter) throws Exception;
    public String getJHFlid(String Flid)throws Exception;
    public List<EntTlTrgCalUnqp> CreateMultipleUnique(List<EntTlTrgCalUnqp> entTlTrgCalUnqpLink) throws Exception;
    
	public abstract void NewTrgCalDaoImplJwt(String jwtToken);

}
