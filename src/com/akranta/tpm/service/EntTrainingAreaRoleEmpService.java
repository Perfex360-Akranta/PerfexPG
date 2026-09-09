/*Created By : Babu.d*/
package com.akranta.tpm.service;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DeptFuncLinkBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlRoleDeptLinkmst;
import com.akranta.tpm.model.EntTlRoleEmpLink;
import com.akranta.tpm.model.EntTlRoleSkillLink;
import com.akranta.tpm.model.EntTlRoleSkillRating;
import com.akranta.tpm.model.EntTlRoleTopicLink;
import com.akranta.tpm.model.EntTlRoleTopicRating;
import com.akranta.tpm.model.EntTlRoleTrainingareaLink;

public interface EntTrainingAreaRoleEmpService {
	
	public List<ComboBox> getDepartmentCombo() throws Exception ;
	public List<ComboBox> getDeptFunctionCombo(String deptId) throws Exception ;
	public List<ComboBox> getRoleCombo() throws Exception ;
	public List<ComboBox> getSkillCombo() throws Exception;
	
	public List<ComboBox> getSkillRatingCombo() throws Exception;
	public List<ComboBox> getTargetRatingCombo(String fromCreation) throws Exception;
	public List<String[]> getRattings() throws Exception;
	public List<String[]> checkLinkExists(String deptId, String cellId, String roleId) throws Exception ;
	
	public List<String[]> getDeptFuncLinkGrid(String condparam)throws Exception;
	public List<String[]> getDepartmentType(String deptId)throws Exception;
	
	public List<String[]> getEmployeeGrid(String ErdlKeyid) throws Exception ;
	public List<String[]> getAllTrainAreaRoleView(CommonFilter commonFilter) throws Exception ;	
	public List<ComboBox> getTrainingAreaCombo(String type) throws Exception ;
	
	EntTlRoleEmpLink create(EntTlRoleEmpLink newEntTlRoleEmpLink,EntTlRoleTopicLink newEntTlRoleTopicLink,
					EntTlRoleTrainingareaLink newEntTlRoleTrainingareaLink, EntTlRoleTopicRating newEntTlRoleTopicRating, DeptFuncLinkBean deptFuncLinkBean)throws Exception ;

	public String deleteRoleSkill(String ErslKeyid) throws Exception;
	
	public EntTlRoleEmpLink createErdlEmployee(EntTlRoleEmpLink newEntTlRoleEmpLink, String from, String rtalKeyID, String createdBY) throws Exception;
	public String deleteErdlEmployee(String erdlKeyid, String empId, EntTlRoleEmpLink newEntTlRoleEmpLink) throws ValidationExceptions, Exception;
	public List<ComboBox> getevalCombo(String condsql) throws Exception;
	public List<ComboBox> getTopic(String spokeid, String trarkeyid) throws Exception;
	
	public EntTlRoleTrainingareaLink create(EntTlRoleTrainingareaLink newEntTlRoleTrainingareaLink) throws Exception ;
	
	public List<String[]> getevalGrdData(String trainAreaId, String topiId, String roleId, String spokId)
			throws Exception;
	
	public EntTlRoleTrainingareaLink getEntTlRoleTrainingareaLink(String roleTrainAreaId)throws Exception;
	public String deleteTopicRating(String rtalKeyid, String rtlkKeyid)throws Exception;
	public List<ComboBox> getTrnAreacomboVals(String elementtype, String parentId) throws Exception;
	public String getcheckmanuf(String reftype)throws Exception ;
	public String getChildVals(String keyId)throws Exception ;
	public String getdispFuncloc(String trarKeyid)throws Exception ;
	public String getspokId(String topKey)throws Exception ;
	public List<ComboBox> getSpokeKeyidCombo(String string)throws Exception ;
	public String getdispFunclockey(String trarKeyid)throws Exception ;
	public Workbook getViewExcel(String condparam, String format, String path, String roleName, String trcl)throws Exception;
	public List<String[]> getEmployeeList(String erdlKeyid, GridParams gridParams, String empFilter)throws Exception;
	public String saveErdlEmployee(String employeelist, String rtalKeyID,
			String usrm_ccno)throws Exception;
	public Workbook getEmpRoleExcel(JSONObject colmodel, String format, CommonFilter commonFilter)throws Exception;
	}
