package com.akranta.tpm.dao;

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

public interface EntTlRoleTrainingareaLinkDao {

	public abstract EntTlRoleTrainingareaLink create(EntTlRoleTrainingareaLink entTlRoleTrainingareaLink) throws Exception;
	public abstract EntTlRoleTrainingareaLink update(EntTlRoleTrainingareaLink entTlRoleTrainingareaLink) throws Exception;
	public abstract EntTlRoleTrainingareaLink delete(EntTlRoleTrainingareaLink entTlRoleTrainingareaLink) throws Exception;
	public abstract List<String[]> getevalGrdData(String trainAreaId,String topiId, String roleId, String spokId) throws Exception;
	
	public List<ComboBox> getSkillRatingCombo() throws Exception  ;
	public List<ComboBox> getTargetRatingCombo() throws Exception ;	
	public List<String[]> getRattings() throws Exception  ;
	public List<String[]> getDeptFuncLinkGrid(String condparam)throws Exception ;
	public List<String[]> getDepartmentType(String deptId)throws Exception ;
	public List<String[]> checkLinkExists(String deptId, String cellId, String roleId) throws Exception;
	
	public String getRattingKeyid(String orderNo) throws Exception;
	public String getRattingLevel(String skrmKeyid) throws Exception;
	
	public List<String[]> getEmployeeGrid(String ErdlKeyid) throws Exception ;
	public List<String[]> getAllTrainAreaRoleView(CommonFilter commonfilter) throws Exception ;
	
	public EntTlRoleDeptLinkmst create(EntTlRoleDeptLinkmst newEntTlRoleDeptLinkmst,EntTlRoleSkillLink newEntTlRoleSkillLink,
			EntTlRoleSkillRating newEntTlRoleSkillRating, DeptFuncLinkBean deptFuncLinkBean) throws Exception ;

	public String deleteRoleSkill(String ErslKeyid) throws Exception ;
	
	public EntTlRoleEmpLink createErdlEmployee(EntTlRoleEmpLink newEntTlRoleEmpLink) throws Exception;
	public String deleteErdlEmployee(String erdlKeyid, String empId, EntTlRoleEmpLink newEntTlRoleEmpLink) throws ValidationExceptions, Exception;
	public abstract EntTlRoleEmpLink create(
			EntTlRoleEmpLink newEntTlRoleEmpLink,
			EntTlRoleTopicLink newEntTlRoleTopicLink,
			DeptFuncLinkBean deptFuncLinkBean);
	public abstract EntTlRoleTrainingareaLink create(EntTlRoleTrainingareaLink newEntTlRoleTrainingareaLink,EntTlRoleTopicLink newEntTlRoleTopicLink,
			EntTlRoleTopicRating newEntTlRoleTopicRating) throws Exception;
	
	public EntTlRoleTrainingareaLink getEntTlRoleTrainingareaLink(
			String roleTrainAreaId) throws Exception ;
	public abstract String deleteTopicRating(String rtalKeyid, String rtlkKeyid)throws Exception ;
	public abstract String getcheckmanuf(String reftype)throws Exception ;
	public abstract String getChildVals(String keyId)throws Exception ;
	public abstract String getdispFuncloc(String trarKeyid)throws Exception ;
	public abstract String getspokId(String topKey)throws Exception ;
	public abstract String getdispFunclockey(String trarKeyid)throws Exception ;
	public abstract Map<Integer, List<String[]>> getViewExcel(String condparam)throws Exception;
	public abstract List<String[]> getEmployeeList(String erdlKeyid, GridParams gridParams, String empFilter)throws Exception;
	public abstract String saveErdlEmployee(String employeelist,String rtalKeyID, String usrm_ccno)throws Exception;
	public abstract Workbook getEmpRoleExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter)throws Exception;
}

