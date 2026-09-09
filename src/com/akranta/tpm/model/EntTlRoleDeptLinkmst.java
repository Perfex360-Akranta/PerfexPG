package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlRoleDeptLinkmst {

	private  Object [] saveArray = null;  
	
	private List <EntTlRoleSkillRating> entTlRoleSkillRatingList ;
	private String rattingDetails;
	
	public enum   tableFldConstants
	{
		keyid, fact_keyid, dept_keyid, cell_function, cell_fun_keyid
		, role_keyid, elective_skills, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public EntTlRoleDeptLinkmst()
	{
		saveArray = new  Object [ 16 ];
		entTlRoleSkillRatingList = new ArrayList<EntTlRoleSkillRating>();
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setEntTlRoleSkillRatingList(List <EntTlRoleSkillRating> entTlRoleSkillRatingList) {
		this.entTlRoleSkillRatingList =  entTlRoleSkillRatingList;
	}
	public List<EntTlRoleSkillRating> getEntTlRoleSkillRatingList() 
	{
		return entTlRoleSkillRatingList;
	}

	public String getRattingDetails() {
		return rattingDetails;
	}

	public void setRattingDetails(String rattingDetails) {
		this.rattingDetails = rattingDetails;
	}
	
	
	public String getErdlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setErdlKeyid(String erdlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = erdlKeyid;
	}

	public String getErdlFactKeyid() {
		return (String) saveArray[ tableFldConstants.fact_keyid.ordinal() ];
	}

	public void setErdlFactKeyid(String erdlFactKeyid) {
		saveArray[ tableFldConstants.fact_keyid.ordinal() ] = erdlFactKeyid;
	}

	public String getErdlDeptKeyid() {
		return (String) saveArray[ tableFldConstants.dept_keyid.ordinal() ];
	}

	public void setErdlDeptKeyid(String erdlDeptKeyid) {
		saveArray[ tableFldConstants.dept_keyid.ordinal() ] = erdlDeptKeyid;
	}

	public String getErdlCellFunction() {
		return (String) saveArray[ tableFldConstants.cell_function.ordinal() ];
	}

	public void setErdlCellFunction(String erdlCellFunction) {
		saveArray[ tableFldConstants.cell_function.ordinal() ] = erdlCellFunction;
	}

	public String getErdlCellFunKeyid() {
		return (String) saveArray[ tableFldConstants.cell_fun_keyid.ordinal() ];
	}

	public void setErdlCellFunKeyid(String erdlCellFunKeyid) {
		saveArray[ tableFldConstants.cell_fun_keyid.ordinal() ] = erdlCellFunKeyid;
	}

	public String getErdlRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setErdlRoleKeyid(String erdlRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = erdlRoleKeyid;
	}

	public String getErdlElectiveSkills() {
		return (String) saveArray[ tableFldConstants.elective_skills.ordinal() ];
	}

	public void setErdlElectiveSkills(String erdlElectiveSkills) {
		saveArray[ tableFldConstants.elective_skills.ordinal() ] = erdlElectiveSkills;
	}

	public String getErdlTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setErdlTempfield1(String erdlTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = erdlTempfield1;
	}

	public String getErdlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setErdlTempfield2(String erdlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = erdlTempfield2;
	}

	public String getErdlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setErdlTempfield3(String erdlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = erdlTempfield3;
	}

	public String getErdlTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setErdlTempfield4(String erdlTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = erdlTempfield4;
	}

	public String getErdlTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setErdlTempfield5(String erdlTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = erdlTempfield5;
	}

	public String getErdlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setErdlActive(String erdlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = erdlActive;
	}

	public String getErdlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setErdlCreatedby(String erdlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = erdlCreatedby;
	}

	public String getErdlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setErdlCreatedon(String erdlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = erdlCreatedon;
	}

	public String getErdlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setErdlModifiedon(String erdlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = erdlModifiedon;
	}

}

