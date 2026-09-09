package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DeptFuncLinkBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.EntTlRoleDeptLinkmst;
import com.akranta.tpm.model.EntTlRoleEmpLink;
import com.akranta.tpm.model.EntTlRoleSkillLink;
import com.akranta.tpm.model.EntTlRoleSkillRating;


public interface EntTrainingAreaRoleEmpDao {
	public List<ComboBox> getSkillRatingCombo() throws Exception  ;
	public List<ComboBox> getTargetRatingCombo() throws Exception ;	
	public List<String[]> getRattings() throws Exception  ;
	public List<String[]> getDeptFuncLinkGrid(String erdlKeyid)throws Exception ;
	public List<String[]> getDepartmentType(String deptId)throws Exception ;
	public List<String[]> checkLinkExists(String deptId, String cellId, String roleId) throws Exception;
	
	public String getRattingKeyid(String orderNo) throws Exception;
	public String getRattingLevel(String skrmKeyid) throws Exception;
	
	public List<String[]> getEmployeeGrid(String ErdlKeyid) throws Exception ;
	public List<String[]> getDeptFuncView(String deptId, String cellId, String roleId) throws Exception ;
	
	public EntTlRoleDeptLinkmst create(EntTlRoleDeptLinkmst newEntTlRoleDeptLinkmst,EntTlRoleSkillLink newEntTlRoleSkillLink,
			EntTlRoleSkillRating newEntTlRoleSkillRating, DeptFuncLinkBean deptFuncLinkBean) throws Exception ;

	public String deleteRoleSkill(String ErslKeyid) throws Exception ;
	
	public EntTlRoleEmpLink createErdlEmployee(EntTlRoleEmpLink newEntTlRoleEmpLink,DeptFuncLinkBean deptFuncLinkBean) throws Exception;
	public String deleteErdlEmployee(String erdlKeyid, String empId) throws ValidationExceptions, Exception;	
}

