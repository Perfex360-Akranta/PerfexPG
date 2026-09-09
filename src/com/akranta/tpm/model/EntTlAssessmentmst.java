package com.akranta.tpm.model;
/**
 * Author:Roopa
 * Created on : 12/08/2012
 */
import java.util.ArrayList;
import java.util.List;

public class EntTlAssessmentmst {

	private  Object [] saveArray = null;  
	
	private List<EntTlAssessmentdtl> entTlAssessmentdtl;
	private List<EntTlAssessmentmst> entTlAssessmentmst;
	private String progKeyid ;
	private String cutoff;
	private String facultyId;
	public enum   tableFldConstants
	{
		keyid, evaluation_desc, evaluation_date, evaluation_no, evaluation_type, is_locked
		, trar_keyid, role_keyid, empm_keyid, faculty, remarks
		, fact_id, assessedby, elementid, tempfield6, tempfield7
		, tempfield8, tempfield9, tempfield10, active, createdby, createdon
		, modifiedon
	}

	public EntTlAssessmentmst()
	{
		saveArray = new  Object [ 23 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	public String getAsmmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAsmmKeyid(String asmmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = asmmKeyid;
	}

	public String getAsmmEvaluationDesc() {
		return (String) saveArray[ tableFldConstants.evaluation_desc.ordinal() ];
	}

	public void setAsmmEvaluationDesc(String asmmEvaluationDesc) {
		saveArray[ tableFldConstants.evaluation_desc.ordinal() ] = asmmEvaluationDesc;
	}

	public String getAsmmEvaluationDate() {
		return (String) saveArray[ tableFldConstants.evaluation_date.ordinal() ];
	}

	public void setAsmmEvaluationDate(String asmmEvaluationDate) {
		saveArray[ tableFldConstants.evaluation_date.ordinal() ] = asmmEvaluationDate;
	}

	public String getAsmmEvaluationNo() {
		return (String) saveArray[ tableFldConstants.evaluation_no.ordinal() ];
	}

	public void setAsmmEvaluationNo(String asmmEvaluationNo) {
		saveArray[ tableFldConstants.evaluation_no.ordinal() ] = asmmEvaluationNo;
	}
	
	public String getAsmmEvaluationType() {
		return (String) saveArray[ tableFldConstants.evaluation_type.ordinal() ];
	}

	public void setAsmmEvaluationType(String asmmEvaluationType) {
		saveArray[ tableFldConstants.evaluation_type.ordinal() ] = asmmEvaluationType;
	}

	public String getAsmmIsLocked() {
		return (String) saveArray[ tableFldConstants.is_locked.ordinal() ];
	}

	public void setAsmmIsLocked(String asmmIsLocked) {
		saveArray[ tableFldConstants.is_locked.ordinal() ] = asmmIsLocked;
	}

	public String getAsmmTrarKeyid() {
		return (String) saveArray[ tableFldConstants.trar_keyid.ordinal() ];
	}

	public void setAsmmTrarKeyid(String asmmTrarKeyid) {
		saveArray[ tableFldConstants.trar_keyid.ordinal() ] = asmmTrarKeyid;
	}

	public String getAsmmRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setAsmmRoleKeyid(String asmmRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = asmmRoleKeyid;
	}

	public String getAsmmEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setAsmmEmpmKeyid(String asmmEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = asmmEmpmKeyid;
	}

	public String getAsmmFaculty() {
		return (String) saveArray[ tableFldConstants.faculty.ordinal() ];
	}

	public void setAsmmFaculty(String asmmFaculty) {
		saveArray[ tableFldConstants.faculty.ordinal() ] = asmmFaculty;
	}

	public String getAsmmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setAsmmRemarks(String asmmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = asmmRemarks;
	}

	public String getAsmmFactId() {
		return (String) saveArray[ tableFldConstants.fact_id.ordinal() ];
	}

	public void setAsmmFactId(String asmmFactId) {
		saveArray[ tableFldConstants.fact_id.ordinal() ] = asmmFactId;
	}

	public String getAsmmAssessedBy() {
		return (String) saveArray[ tableFldConstants.assessedby.ordinal() ];
	}

	public void setAsmmAssessedBy(String asmmAssessedBy) {
		saveArray[ tableFldConstants.assessedby.ordinal() ] = asmmAssessedBy;
	}

	public String getAsmmElementId() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setAsmmElementId(String asmmElementId) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = asmmElementId;
	}

	public String getAsmmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setAsmmTempfield6(String asmmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = asmmTempfield6;
	}

	public String getAsmmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setAsmmTempfield7(String asmmTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = asmmTempfield7;
	}

	public String getAsmmTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setAsmmTempfield8(String asmmTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = asmmTempfield8;
	}

	public String getAsmmTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setAsmmTempfield9(String asmmTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = asmmTempfield9;
	}

	public String getAsmmTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setAsmmTempfield10(String asmmTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = asmmTempfield10;
	}

	public String getAsmmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAsmmActive(String asmmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = asmmActive;
	}

	public String getAsmmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAsmmCreatedby(String asmmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = asmmCreatedby;
	}

	public String getAsmmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAsmmCreatedon(String asmmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = asmmCreatedon;
	}

	public String getAsmmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAsmmModifiedon(String asmmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = asmmModifiedon;
	}
	
	public void setEntTlAssessmentdtl(List<EntTlAssessmentdtl> entTlAssessmentdtl) {
		this.entTlAssessmentdtl =entTlAssessmentdtl ;
	}

	public List<EntTlAssessmentdtl> getEntTlAssessmentdtl() {
		return this.entTlAssessmentdtl;
	}

	public void setEntTlAssessmentmst(List<EntTlAssessmentmst> entTlAssessmentmst) {
		this.entTlAssessmentmst = entTlAssessmentmst;
	}

	public List<EntTlAssessmentmst> getEntTlAssessmentmst() {
		return entTlAssessmentmst;
	}

	public String getProgKeyid() {
		return progKeyid;
	}

	public void setProgKeyid(String progKeyid) {
		this.progKeyid = progKeyid;
	}

	public String getCutoff() {
		return cutoff;
	}

	public void setCutoff(String cutoff) {
		this.cutoff = cutoff;
	}

	public String getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

}



