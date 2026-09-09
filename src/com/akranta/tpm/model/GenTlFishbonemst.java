package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlFishbonemst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, elementid, refdocid, refdoctype, title, problem
		, revisionno, prepareddate, preparedby, approveddate, approvedby
		, status, defect, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public GenTlFishbonemst()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

	public String getFismKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFismKeyid(String fismKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fismKeyid;
	}

	public String getFismFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setFismFlid(String fismFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = fismFlid;
	}

	public String getFismElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setFismElementid(String fismElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = fismElementid;
	}

	public String getFismRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setFismRefdocid(String fismRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = fismRefdocid;
	}

	public String getFismRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setFismRefdoctype(String fismRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = fismRefdoctype;
	}

	public String getFismTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setFismTitle(String fismTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = fismTitle;
	}

	public String getFismProblem() {
		return (String) saveArray[ tableFldConstants.problem.ordinal() ];
	}

	public void setFismProblem(String fismProblem) {
		saveArray[ tableFldConstants.problem.ordinal() ] = fismProblem;
	}

	public String getFismRevisionno() {
		return (String) saveArray[ tableFldConstants.revisionno.ordinal() ];
	}

	public void setFismRevisionno(String fismRevisionno) {
		saveArray[ tableFldConstants.revisionno.ordinal() ] = fismRevisionno;
	}

	public String getFismPrepareddate() {
		return (String) saveArray[ tableFldConstants.prepareddate.ordinal() ];
	}

	public void setFismPrepareddate(String fismPrepareddate) {
		saveArray[ tableFldConstants.prepareddate.ordinal() ] = fismPrepareddate;
	}

	public String getFismPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setFismPreparedby(String fismPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = fismPreparedby;
	}

	public String getFismApproveddate() {
		return (String) saveArray[ tableFldConstants.approveddate.ordinal() ];
	}

	public void setFismApproveddate(String fismApproveddate) {
		saveArray[ tableFldConstants.approveddate.ordinal() ] = fismApproveddate;
	}

	public String getFismApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setFismApprovedby(String fismApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = fismApprovedby;
	}

	public String getFismStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setFismStatus(String fismStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = fismStatus;
	}

	public String getFismDefect() {
		return (String) saveArray[ tableFldConstants.defect.ordinal() ];
	}

	public void setFismDefect(String fismDefect) {
		saveArray[ tableFldConstants.defect.ordinal() ] = fismDefect;
	}

	public String getFismTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFismTempfield2(String fismTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fismTempfield2;
	}

	public String getFismTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFismTempfield3(String fismTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fismTempfield3;
	}

	public String getFismTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFismTempfield4(String fismTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fismTempfield4;
	}

	public String getFismTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFismTempfield5(String fismTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fismTempfield5;
	}

	public String getFismActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFismActive(String fismActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fismActive;
	}

	public String getFismCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFismCreatedby(String fismCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fismCreatedby;
	}

	public String getFismCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFismCreatedon(String fismCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fismCreatedon;
	}

	public String getFismModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFismModifiedon(String fismModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fismModifiedon;
	}

	
}

