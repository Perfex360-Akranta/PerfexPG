package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTrgFaculty;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;

public interface GridBasedTrainigCalendarDao {

	List<String[]> getElementId(String loginflid, String loginlevel,
			String loginElementid, String empId) throws Exception;

	List<String[]> getListTrgCalendar(CommonFilter commonFilter,GridParams gridParams)throws Exception;

	List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter)throws Exception;

	List<String[]> getsession(String trgcalKeyid)throws Exception;

	List<String[]> getNewUniqPosData(String trainingKeyid) throws Exception;

	List<EntTlTtgCalEmpatScore> employeeAttendance(
			List<EntTlTtgCalEmpatScore> fillValuesEmployeeAttendance,
			CommonFilter commonfilter)throws Exception;

	EntTlTragcalmst create(EntTlTragcalmst newentTlTragcalmst)throws Exception;

	//<EntTlTragcalmst> GrdBsdTrgCalcreate(Object trngCalfillValues)throws Exception;

	List<EntTlTragcalmst> GrdBsdTrgCalcreate(List<EntTlTragcalmst> trngCalList) throws Exception;

	EntTlTragcalmst create(List<EntTlTragcalmst> trngCalfillValues)throws Exception;

	EntTlTrgCalSession createSession(EntTlTrgCalSession newentTlTrgCalSession) throws Exception;

	String chkSessionDate(CommonFilter commonFilter)throws Exception;

	String chkUniqueposition(CommonFilter commonFilter) throws Exception;

	EntTlTrgCalUnqp createUniquePostion(EntTlTrgCalUnqp newentTlTrgCalUnqp)throws Exception;

	List<String[]> getFaculty(String progKeyid) throws Exception;

	String FacultyCheck(CommonFilter commonFilter)throws Exception;

	EntTlTrgFaculty createFaculty(EntTlTrgFaculty newEntTlTrgFaculty)throws Exception;

	List<String[]> getAllEmployee(CommonFilter commonFilter, GridParams gridParams) throws Exception;

	List<String[]> gwtJHRoleUniquePos(CommonFilter commonFilter)throws Exception;

	List<EntTlTtgCalEmpatScore> createEmployeeAttendance(
			List<EntTlTtgCalEmpatScore> fillValuesEmployeeAttendanceList,
			CommonFilter commonfilter)throws Exception;

	List<EntTlTragcalmst> GrdBsdTrgCalUpdate(List<EntTlTragcalmst> trngCalfillValues)throws Exception;

	void DeleteCal(String keyid) throws Exception;

	List<String[]> getListTrgCalendarModify(CommonFilter commonFilter,
			GridParams gridParams) throws Exception;

	
	public List<String[]> getListTrgCalendarView(CommonFilter commonFilter,
			GridParams gridParams) throws Exception;
	public String deleteDetailRecord(String keyId, String gridId, String topicId) throws Exception,BusinessApplicationExceptions;

	List<EntTlTrgCalUnqp> CreateMultipleUnique(	List<EntTlTrgCalUnqp> fillValuesMultipleUniquePos)throws Exception;


	EntTlTragcalmst getselectdata(String calendarkeyid) throws Exception;

	String getempdata(String calendarkeyid)throws Exception;

	String getMaxmarks(String calendarkeyid)throws Exception;

	String getCutoff(String calendarkeyid)throws Exception;

	String chkAssesmentComplted(CommonFilter commonFilter)throws Exception;

	String getempattn(String masterid)throws Exception;

	String getTrgDateData(String keyId)throws Exception;
    String IsTrainingCompleted(CommonFilter commonFilter)
			throws Exception ;

	Workbook getTrainingCalendarListExcel( JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;


}
