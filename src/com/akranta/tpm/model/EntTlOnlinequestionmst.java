package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlOnlinequestionmst {

	private  Object [] saveArray = null;  
	
	private List<EntTlOnlinequestiondtl> prepareQuestion;
	public enum   tableFldConstants
	{
		keyid, programid, topicid, type, question, imagename, hint, description
		, marks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public EntTlOnlinequestionmst()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOlqmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setOlqmKeyid(String olqmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = olqmKeyid;
	}

	public String getOlqmProgramid() {
		return (String) saveArray[ tableFldConstants.programid.ordinal() ];
	}

	public void setOlqmProgramid(String olqmProgramid) {
		saveArray[ tableFldConstants.programid.ordinal() ] = olqmProgramid;
	}

	public String getOlqmTopicid() {
		return (String) saveArray[ tableFldConstants.topicid.ordinal() ];
	}

	public void setOlqmTopicid(String olqmTopicid) {
		saveArray[ tableFldConstants.topicid.ordinal() ] = olqmTopicid;
	}

	public String getOlqmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setOlqmType(String olqmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = olqmType;
	}

	public String getOlqmQuestion() {
		return (String) saveArray[ tableFldConstants.question.ordinal() ];
	}

	public void setOlqmQuestion(String olqmQuestion) {
		saveArray[ tableFldConstants.question.ordinal() ] = olqmQuestion;
	}

	public String getOlqmImagename() {
		return (String) saveArray[ tableFldConstants.imagename.ordinal() ];
	}

	public void setOlqmImagename(String olqmImagename) {
		saveArray[ tableFldConstants.imagename.ordinal() ] = olqmImagename;
	}

	public String getOlqmHint() {
		return (String) saveArray[ tableFldConstants.hint.ordinal() ];
	}

	public void setOlqmHint(String olqmHint) {
		saveArray[ tableFldConstants.hint.ordinal() ] = olqmHint;
	}

	public String getOlqmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setOlqmDescription(String olqmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = olqmDescription;
	}

	public String getOlqmMarks() {
		return (String) saveArray[ tableFldConstants.marks.ordinal() ];
	}

	public void setOlqmMarks(String olqmMarks) {
		saveArray[ tableFldConstants.marks.ordinal() ] = olqmMarks;
	}

	public String getOlqmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setOlqmTempfield1(String olqmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = olqmTempfield1;
	}

	public String getOlqmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setOlqmTempfield2(String olqmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = olqmTempfield2;
	}

	public String getOlqmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setOlqmTempfield3(String olqmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = olqmTempfield3;
	}

	public String getOlqmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOlqmTempfield4(String olqmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = olqmTempfield4;
	}

	public String getOlqmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setOlqmTempfield5(String olqmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = olqmTempfield5;
	}

	public String getOlqmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOlqmCreatedby(String olqmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = olqmCreatedby;
	}

	public String getOlqmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOlqmActive(String olqmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = olqmActive;
	}

	public String getOlqmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOlqmCreatedon(String olqmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = olqmCreatedon;
	}

	public String getOlqmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOlqmModifiedon(String olqmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = olqmModifiedon;
	}

	public void setPrepareQuestion(List<EntTlOnlinequestiondtl> prepareQuestion) {
		this.prepareQuestion = prepareQuestion;
	}

	public List<EntTlOnlinequestiondtl> getPrepareQuestion() {
		return prepareQuestion;
	}

	public void setSaveArray(Object [] saveArray) {
		 this.saveArray = saveArray;
	}
	
}

