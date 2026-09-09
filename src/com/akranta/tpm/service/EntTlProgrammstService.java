package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.EntTlProgTargetRolesBean;
import com.akranta.tpm.bean.EntTlProgTargetSkillsBean;
import com.akranta.tpm.bean.ProgrammstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.EntTlProgTargetRoles;
import com.akranta.tpm.model.EntTlProgTargetSkills;
import com.akranta.tpm.model.EntTlProgrammst;

public interface EntTlProgrammstService {

	List<ComboBox> getProgkeyidCombo(String condsql, ComboFilter comboFilter) throws Exception;

	EntTlProgrammst create(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean) throws Exception;

	List<String[]> getgridData() throws Exception;

	EntTlProgrammst fillFormvalues(String progKey) throws NoDataFoundException, SQLException, Exception;

	EntTlProgrammst update(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean) throws Exception;

	List<ComboBox> getDelvModeCombo(String condSql, ComboFilter comboFilter) throws Exception;

	List<ComboBox> getEvalTypeCombo(String condSql, ComboFilter comboFilter) throws Exception;
/*For Skill Master*/
	List<ComboBox> getSkillkeyidCombo(String condSql, ComboFilter comboFilter) throws Exception;

	EntTlProgTargetSkills createSkill(EntTlProgTargetSkills newEntTlProgTargetSkills,
			EntTlProgTargetSkills existEntTlProgTargetSkills,EntTlProgTargetSkillsBean entTlProgTargetSkillsBean) throws Exception;

	EntTlProgTargetSkills updateSkill(EntTlProgTargetSkills newEntTlProgTargetSkills,
			EntTlProgTargetSkills existEntTlProgTargetSkills,EntTlProgTargetSkillsBean entTlProgTargetSkillsBean) throws Exception;

	EntTlProgTargetSkills getSkilformValues(String progKeyId) throws NoDataFoundException, SQLException, Exception;

	List<String[]> getskillgridData(String string) throws Exception;
/*End*/
/*For Role Master*/
	List<ComboBox> getRoleCombo(String keyId, String progKeyid, ComboFilter comboFilter) throws Exception;

	List<String[]> getRolegridData(String string) throws Exception;

	EntTlProgTargetRoles createRole(EntTlProgTargetRoles newEntTlProgTargetRoles,EntTlProgTargetRoles existEntTlProgTargetRoles,
			EntTlProgTargetRolesBean entTlProgTargetRolesBean) throws Exception;

	EntTlProgTargetRoles updateRole(EntTlProgTargetRoles newEntTlProgTargetRoles,EntTlProgTargetRoles existEntTlProgTargetRoles,
			EntTlProgTargetRolesBean entTlProgTargetRolesBean)throws Exception;

	EntTlProgTargetRoles getRoleformValues(String progKeyId) throws NoDataFoundException, SQLException, Exception;
	/*End*/

	 public String delgridData(String prtsKeyid) throws Exception;

	public String delRolegridData(String prtrKeyid) throws Exception;

	public String chkKeyExists(String skillkeyId) throws SQLException, BusinessApplicationExceptions;

	public String chkKeyRoleExists(String progKeyId) throws SQLException, Exception;

	public EntTlProgrammst deleteProg(EntTlProgrammst newEntTlProgrammst) throws BusinessApplicationExceptions,Exception;

	public List<ComboBox> getSkillTypeCombo(String condsql, ComboFilter comboFilter) throws Exception;

	public List<ComboBox> getSpokeKeyidCombo(String keyId, ComboFilter comboFilter)throws Exception;

	public String getevalTypeId(String topickeyId)throws Exception;

	public List<ComboBox> getProgkeyid1levCombo(String condsql, ComboFilter comboFilter)
			throws Exception;

	public List<ComboBox> getProgkeyid4levCombo(String flid, ComboFilter comboFilter) throws Exception;


}
