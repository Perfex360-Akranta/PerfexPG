package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlRoleEmpLink {

	private  Object [] saveArray = null;  
	private List <EntTlRoleEmpLink> methodEntTlRoleEmployeeLink;

	public enum   tableFldConstants
	{
		keyid, erdl_keyid, empm_keyid, current_skillratingid, target_skillratingid
		, eff_from_date, eff_till_date, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public EntTlRoleEmpLink()
	{
		saveArray = new  Object [ 16 ];
	}

	public List<EntTlRoleEmpLink> getmethodEntTlRoleEmployeeLink() 
	{
		return methodEntTlRoleEmployeeLink;
	}
	public void setmethodEntTlRoleEmployeeLink(List <EntTlRoleEmpLink> methodEntTlRoleEmployeeLink) {
		this.methodEntTlRoleEmployeeLink=methodEntTlRoleEmployeeLink;
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getErelKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setErelKeyid(String erelKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = erelKeyid;
	}

	public String getErelRtalKeyid() {
		return (String) saveArray[ tableFldConstants.erdl_keyid.ordinal() ];
	}

	public void setErelRtalKeyid(String erelRtalKeyid) {
		saveArray[ tableFldConstants.erdl_keyid.ordinal() ] = erelRtalKeyid;
	}

	public String getErelEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setErelEmpmKeyid(String erelEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = erelEmpmKeyid;
	}

	public String getErelCurrentSkillratingid() {
		return (String) saveArray[ tableFldConstants.current_skillratingid.ordinal() ];
	}

	public void setErelCurrentSkillratingid(String erelCurrentSkillratingid) {
		saveArray[ tableFldConstants.current_skillratingid.ordinal() ] = erelCurrentSkillratingid;
	}

	public String getErelTargetSkillratingid() {
		return (String) saveArray[ tableFldConstants.target_skillratingid.ordinal() ];
	}

	public void setErelTargetSkillratingid(String erelTargetSkillratingid) {
		saveArray[ tableFldConstants.target_skillratingid.ordinal() ] = erelTargetSkillratingid;
	}

	public String getErelEffFromDate() {
		return (String) saveArray[ tableFldConstants.eff_from_date.ordinal() ];
	}

	public void setErelEffFromDate(String erelEffFromDate) {
		saveArray[ tableFldConstants.eff_from_date.ordinal() ] = erelEffFromDate;
	}

	public String getErelEffTillDate() {
		return (String) saveArray[ tableFldConstants.eff_till_date.ordinal() ];
	}

	public void setErelEffTillDate(String erelEffTillDate) {
		saveArray[ tableFldConstants.eff_till_date.ordinal() ] = erelEffTillDate;
	}

	public String getErelTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setErelTempfield1(String erelTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = erelTempfield1;
	}

	public String getErelTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setErelTempfield2(String erelTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = erelTempfield2;
	}

	public String getErelTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setErelTempfield3(String erelTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = erelTempfield3;
	}

	public String getErelTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setErelTempfield4(String erelTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = erelTempfield4;
	}

	public String getErelTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setErelTempfield5(String erelTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = erelTempfield5;
	}

	public String getErelActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setErelActive(String erelActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = erelActive;
	}

	public String getErelCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setErelCreatedby(String erelCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = erelCreatedby;
	}

	public String getErelCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setErelCreatedon(String erelCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = erelCreatedon;
	}

	public String getErelModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setErelModifiedon(String erelModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = erelModifiedon;
	}

}

