/*Created By : Babu.d*/
package com.akranta.tpm.service;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DeptFuncLinkBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.EntTlRoleDeptLinkmst;
import com.akranta.tpm.model.EntTlRoleEmpLink;
import com.akranta.tpm.model.EntTlRoleSkillLink;
import com.akranta.tpm.model.EntTlRoleSkillRating;

public interface EntDeptFuncLinkService {
	
	public List<ComboBox> getDepartmentCombo() throws Exception ;
	public List<ComboBox> getDeptFunctionCombo(String deptId) throws Exception ;
	public List<ComboBox> getRoleCombo() throws Exception ;
	public List<ComboBox> getSkillCombo() throws Exception;
	
	public List<ComboBox> getSkillRatingCombo() throws Exception;
	public List<ComboBox> getTargetRatingCombo(String fromCreation) throws Exception;
	public List<String[]> getRattings() throws Exception;
	public List<String[]> checkLinkExists(String deptId, String cellId, String roleId) throws Exception ;
	
	public List<String[]> getDeptFuncLinkGrid(String erdlKeyid)throws Exception;
	public List<String[]> getDepartmentType(String deptId)throws Exception;
	
	public List<String[]> getEmployeeGrid(String ErdlKeyid) throws Exception ;
	public List<String[]> getDeptFuncView(String deptId, String cellId, String roleId) throws Exception ;	
	
	EntTlRoleDeptLinkmst create(EntTlRoleDeptLinkmst newEntTlRoleDeptLinkmst,EntTlRoleSkillLink newEntTlRoleSkillLink,
					EntTlRoleSkillRating newEntTlRoleSkillRating, DeptFuncLinkBean deptFuncLinkBean)throws Exception ;

	public String deleteRoleSkill(String ErslKeyid) throws Exception;
	
	public EntTlRoleEmpLink createErdlEmployee(EntTlRoleEmpLink newEntTlRoleEmpLink,DeptFuncLinkBean deptFuncLinkBean) throws Exception;
	public String deleteErdlEmployee(String erdlKeyid, String empId) throws ValidationExceptions, Exception;
}
