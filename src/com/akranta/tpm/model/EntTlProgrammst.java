package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlProgrammst {

	private  Object [] saveArray = null;  
	private EntBatchMst batchmaster;
	private EntTlFacultytopiclink facultyTopicLink;
	private EntTlProgTargetSkills progTargetSkills;
	private EntTlBatchSchedule entTlBatchSchedule;
	private EntTlRoleTopicLink roleTopicLink;
	private String progTopicId;
	private String prtrkeyid;
	private String ecalkeyid;
	private String bsdlKeyid;
	
	 
	public enum   tableFldConstants
	{
		keyid, code, name, trar_keyid, purpose, benifit, remarks, is_evaluation_need
		, min_duration, max_duration, contact_info, type, spoke_keyid
		, function, uniquepos, month,materialready, tgtm_keyid,repeatedprogram,frequency
		, effectivefrom,effectivetill,elementid,tempfield1,tempfield2,tempfield3
		, active, createdby, createdon, modifiedon
	}

	public EntTlProgrammst()
	{
		saveArray = new  Object [ 30 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getProgKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setProgKeyid(String progKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = progKeyid;
	}

	public String getProgCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setProgCode(String progCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = progCode;
	}

	public String getProgName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setProgName(String progName) {
		saveArray[ tableFldConstants.name.ordinal() ] = progName;
	}

	public String getProgTrarKeyid() {
		return (String) saveArray[ tableFldConstants.trar_keyid.ordinal() ];
	}

	public void setProgTrarKeyid(String progTrarKeyid) {
		saveArray[ tableFldConstants.trar_keyid.ordinal() ] = progTrarKeyid;
	}

	public String getProgPurpose() {
		return (String) saveArray[ tableFldConstants.purpose.ordinal() ];
	}

	public void setProgPurpose(String progPurpose) {
		saveArray[ tableFldConstants.purpose.ordinal() ] = progPurpose;
	}

	public String getProgBenifit() {
		return (String) saveArray[ tableFldConstants.benifit.ordinal() ];
	}

	public void setProgBenifit(String progBenifit) {
		saveArray[ tableFldConstants.benifit.ordinal() ] = progBenifit;
	}

	public String getProgRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setProgRemarks(String progRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = progRemarks;
	}

	public String getProgIsEvaluationNeed() {
		return (String) saveArray[ tableFldConstants.is_evaluation_need.ordinal() ];
	}

	public void setProgIsEvaluationNeed(String progIsEvaluationNeed) {
		saveArray[ tableFldConstants.is_evaluation_need.ordinal() ] = progIsEvaluationNeed;
	}

	public String getProgMinDuration() {
		return (String) saveArray[ tableFldConstants.min_duration.ordinal() ];
	}

	public void setProgMinDuration(String progMinDuration) {
		saveArray[ tableFldConstants.min_duration.ordinal() ] = progMinDuration;
	}

	public String getProgMaxDuration() {
		return (String) saveArray[ tableFldConstants.max_duration.ordinal() ];
	}

	public void setProgMaxDuration(String progMaxDuration) {
		saveArray[ tableFldConstants.max_duration.ordinal() ] = progMaxDuration;
	}

	public String getProgContactInfo() {
		return (String) saveArray[ tableFldConstants.contact_info.ordinal() ];
	}

	public void setProgContactInfo(String progContactInfo) {
		saveArray[ tableFldConstants.contact_info.ordinal() ] = progContactInfo;
	}

	public String getProgType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setProgType(String progType) {
		saveArray[ tableFldConstants.type.ordinal() ] = progType;
	}

	public String getProgSpokeKeyid() {
		return (String) saveArray[ tableFldConstants.spoke_keyid.ordinal() ];
	}

	public void setProgSpokeKeyid(String progSpokeKeyid) {
		saveArray[ tableFldConstants.spoke_keyid.ordinal() ] = progSpokeKeyid;
	}

	public String getProgFunction() {
		return (String) saveArray[ tableFldConstants.function.ordinal() ];
	}

	public void setProgFunction(String progFunction) {
		saveArray[ tableFldConstants.function.ordinal() ] = progFunction;
	}

	public String getProgUniquepos() {
		return (String) saveArray[ tableFldConstants.uniquepos.ordinal() ];
	}

	public void setProgUniquepos(String progUniquepos) {
		saveArray[ tableFldConstants.uniquepos.ordinal() ] = progUniquepos;
	}

	public String getProgMonth() {
		return (String) saveArray[ tableFldConstants.month.ordinal() ];
	}

	public void setProgMonth(String progMonth) {
		saveArray[ tableFldConstants.month.ordinal() ] = progMonth;
	}

	public String getProgMaterialReady() {
		return (String) saveArray[ tableFldConstants.materialready.ordinal() ];
	}

	public void setProgMaterialReady(String progMaterialReady) {
		saveArray[ tableFldConstants.materialready.ordinal() ] = progMaterialReady;
	}

	public String getProgTgtmKeyid() {
		return (String) saveArray[ tableFldConstants.tgtm_keyid.ordinal() ];
	}

	public void setProgTgtmKeyid(String progTgtmKeyid) {
		saveArray[ tableFldConstants.tgtm_keyid.ordinal() ] = progTgtmKeyid;
	}
	
	public String getProgRepeatedProgram() {
		return (String) saveArray[ tableFldConstants.repeatedprogram.ordinal() ];
	}

	public void setProgRepeatedProgram(String progProgRepeatedProgram) {
		saveArray[ tableFldConstants.repeatedprogram.ordinal() ] = progProgRepeatedProgram;
	}

	public String getProgFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setProgFrequency(String progFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = progFrequency;
	}

	public String getProgEffectiveFrom() {
		return (String) saveArray[ tableFldConstants.effectivefrom.ordinal() ];
	}

	public void setProgEffectiveFrom(String progEffectiveFrom) {
		saveArray[ tableFldConstants.effectivefrom.ordinal() ] = progEffectiveFrom;
	}

	public String getProgEffectiveTill() {
		return (String) saveArray[ tableFldConstants.effectivetill.ordinal() ];
	}

	public void setProgEffectiveTill(String progEffectiveTill) {
		saveArray[ tableFldConstants.effectivetill.ordinal() ] = progEffectiveTill;
	}
 
	public void setProgElementid(String progElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = progElementid;
		 
	}

	public String getProgElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setProgTempfield1(String progTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = progTempfield1;
	}

	public String getProgTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setProgTempfield2(String progTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = progTempfield2;
	}

	public String getProgTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setProgTempfield3(String progTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = progTempfield3;
	}

	public String getProgTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}
	public String getProgActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setProgActive(String progActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = progActive;
	}

	public String getProgCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setProgCreatedby(String progCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = progCreatedby;
	}

	public String getProgCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setProgCreatedon(String progCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = progCreatedon;
	}

	public String getProgModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setProgModifiedon(String progModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = progModifiedon;
	}

	public EntBatchMst getBatchmaster() {
		return batchmaster;
	}

	public void setBatchmaster(EntBatchMst batchmaster) {
		this.batchmaster = batchmaster;
	}

	public EntTlFacultytopiclink getFacultyTopicLink() {
		return facultyTopicLink;
	}

	public void setFacultyTopicLink(EntTlFacultytopiclink facultyTopicLink) {
		this.facultyTopicLink = facultyTopicLink;
	}

	public String getProgTopicId() {
		return progTopicId;
	}

	public void setProgTopicId(String progTopicId) {
		this.progTopicId = progTopicId;
	}

	public String getPrtrkeyid() {
		return prtrkeyid;
	}

	public void setPrtrkeyid(String prtrkeyid) {
		this.prtrkeyid = prtrkeyid;
	}

	public String getEcalkeyid() {
		return ecalkeyid;
	}

	public void setEcalkeyid(String ecalkeyid) {
		this.ecalkeyid = ecalkeyid;
	}

	public EntTlProgTargetSkills getProgTargetSkills() {
		return progTargetSkills;
	}

	public void setProgTargetSkills(EntTlProgTargetSkills progTargetSkills) {
		this.progTargetSkills = progTargetSkills;
	}

	public EntTlBatchSchedule getEntTlBatchSchedule() {
		return entTlBatchSchedule;
	}

	public void setEntTlBatchSchedule(EntTlBatchSchedule entTlBatchSchedule) {
		this.entTlBatchSchedule = entTlBatchSchedule;
	}

	public String getBsdlKeyid() {
		return bsdlKeyid;
	}

	public void setBsdlKeyid(String bsdlKeyid) {
		this.bsdlKeyid = bsdlKeyid;
	}

	public void setRoleTopicLink(EntTlRoleTopicLink roleTopicLink) {
		this.roleTopicLink = roleTopicLink;
	}

	public EntTlRoleTopicLink getRoleTopicLink() {
		return roleTopicLink;
	}

	

	 

}

