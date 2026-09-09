package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.TrainingBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTrgFaculty;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;

public interface GridBasedTrainingCalendarService {

	List<String[]> getElementId(String loginflid, String loginlevel,String loginElementid, String empId) throws Exception;

	List<String[]> getListTrgCalendar(CommonFilter commonFilter,
			GridParams gridParams) throws Exception;

	List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter) throws Exception;

	List<String[]> getsession(String trgcalKeyid)throws Exception;

	List<String[]> getNewUniqPosData(String trainingKeyid)throws Exception;

	EntTlTragcalmst create(List<EntTlTragcalmst> abnormalityList) throws Exception;
	EntTlTragcalmst update(EntTlTragcalmst newentTlTragcalmst,EntTlTragcalmst existEntTlTragcalmst) throws Exception;

	List<EntTlTtgCalEmpatScore> employeeAttendance(
			List<EntTlTtgCalEmpatScore> employeeAddList,
			CommonFilter commonfilter);

	String getempattn(String masterid);

	List<EntTlTragcalmst> create(List<EntTlTragcalmst> abnormalityList,	TrainingBean abnormalityFormBean) throws Exception;

	EntTlTragcalmst create(EntTlTragcalmst newentTlTragcalmst,
			EntTlTragcalmst existEntTlTragcalmst) throws Exception,
			BusinessApplicationExceptions;

	EntTlTragcalmst create(List<EntTlTragcalmst> abnormalityList,
			EntTlTragcalmst existEntTlTragcalmst) throws Exception;

	EntTlTrgCalSession createSession(EntTlTrgCalSession newentTlTrgCalSession,
			EntTlTrgCalSession existntTlTrgCalSession) throws Exception;

	EntTlTrgCalSession updateSession(EntTlTrgCalSession newentTlTrgCalSession,
			EntTlTrgCalSession existntTlTrgCalSession);

	String chkSessionDate(CommonFilter commonFilter)throws Exception;

	String chkUniqueposition(CommonFilter commonFilter) throws Exception;

	EntTlTrgCalUnqp createUniquePostion(EntTlTrgCalUnqp newentTlTrgCalUnqp,
			EntTlTrgCalUnqp existntTlTrgCalUnqp) throws Exception;

	EntTlTrgCalUnqp updateUniquePostion(EntTlTrgCalUnqp newentTlTrgCalUnqp,
			EntTlTrgCalUnqp existntTlTrgCalUnqp) throws Exception;

	List<String[]> getFaculty(String progKeyid)throws Exception;

	String FacultyCheck(CommonFilter commonFilter)throws Exception;

	EntTlTrgFaculty createFaculty(EntTlTrgFaculty newEntTlTrgFaculty,EntTlTrgFaculty existEntTlTrgFaculty)throws Exception;
	EntTlTrgFaculty updateFaculty(EntTlTrgFaculty newEntTlTrgFaculty,EntTlTrgFaculty existEntTlTrgFaculty)throws Exception;

	List<String[]> getAllEmployee(CommonFilter commonFilter,
			GridParams gridParams)throws Exception;

	List<ComboBox> getRoleComboList(CommonFilter commonFilter)throws Exception;

	List<String[]> gwtJHRoleUniquePos(CommonFilter commonFilter)throws Exception;

	List<EntTlTtgCalEmpatScore> createEmployeeAttendance(List<EntTlTtgCalEmpatScore> employeeAddList,CommonFilter commonfilter)throws Exception;

	List<EntTlTragcalmst> update(List<EntTlTragcalmst> abnormalityList,TrainingBean abnormalityFormBean)throws Exception;

	void DeleteCal(String keyid)throws Exception;

	List<String[]> getListTrgCalendarModify(CommonFilter commonFilter,GridParams gridParams) throws Exception;
	public List<String[]> getListTrgCalendarView(CommonFilter commonFilter,	GridParams gridParams) throws Exception;
	public String deleteDetailRecord(String keyId, String gridId, String topicId) throws Exception,BusinessApplicationExceptions;

	List<EntTlTrgCalUnqp> CreateMultipleUnique(	List<EntTlTrgCalUnqp> uniqueAddList)throws Exception;

	EntTlTragcalmst getselectdata(String calendarkeyid) throws Exception;

	String getempdata(String calendarkeyid)throws Exception;

	String getMaxmarks(String calendarkeyid)throws Exception;

	String getCutoff(String calendarkeyid)throws Exception;
	
	//------ Added by Vignesh 
	 String getAssesType(String calid) throws Exception;

	String chkAssesmentComplted(CommonFilter commonFilter)throws Exception;

	List<ComboBox> getTopic(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;

	String getTrgDateData(String keyId)throws Exception;
	String IsTrainingCompleted(CommonFilter commonFilter)throws Exception;

	List<ComboBox> getUniquePosition(CommonFilter commonFilter,	ComboFilter comboFilter) throws Exception;

	List<ComboBox> getFaculty(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;

	Workbook getTrainingCalendarListExcel( JSONObject colmodel, String format,
			CommonFilter commonFilter)throws Exception;


public void GridBasedTrainingCalendarServiceImplJwt(String string);
}
