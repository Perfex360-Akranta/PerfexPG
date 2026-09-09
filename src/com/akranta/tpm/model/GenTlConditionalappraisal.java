package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlConditionalappraisal {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flnid, componentid, dimension, checkingtool, typeofcheck
		, idealcondition, status, actionrequired, remarks, actualconditin
		, responsibility, targetdate, completedby, completeddate, tempfield1
		, tempfield2, tempfield3, tempfield4, active, createdby, createdon
		, modifiedon
	}

	public GenTlConditionalappraisal()
	{
		saveArray = new  Object [ 23 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] SaveArray) {
		this.saveArray=SaveArray;
	}


	public String getCdapKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCdapKeyid(String cdapKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cdapKeyid;
	}

	public String getCdapFlnid() {
		return (String) saveArray[ tableFldConstants.flnid.ordinal() ];
	}

	public void setCdapFlnid(String cdapFlnid) {
		saveArray[ tableFldConstants.flnid.ordinal() ] = cdapFlnid;
	}

	public String getCdapComponentid() {
		return (String) saveArray[ tableFldConstants.componentid.ordinal() ];
	}

	public void setCdapComponentid(String cdapComponentid) {
		saveArray[ tableFldConstants.componentid.ordinal() ] = cdapComponentid;
	}

	public String getCdapDimension() {
		return (String) saveArray[ tableFldConstants.dimension.ordinal() ];
	}

	public void setCdapDimension(String cdapDimension) {
		saveArray[ tableFldConstants.dimension.ordinal() ] = cdapDimension;
	}

	public String getCdapCheckingtool() {
		return (String) saveArray[ tableFldConstants.checkingtool.ordinal() ];
	}

	public void setCdapCheckingtool(String cdapCheckingtool) {
		saveArray[ tableFldConstants.checkingtool.ordinal() ] = cdapCheckingtool;
	}

	public String getCdapTypeofcheck() {
		return (String) saveArray[ tableFldConstants.typeofcheck.ordinal() ];
	}

	public void setCdapTypeofcheck(String cdapTypeofcheck) {
		saveArray[ tableFldConstants.typeofcheck.ordinal() ] = cdapTypeofcheck;
	}

	public String getCdapIdealcondition() {
		return (String) saveArray[ tableFldConstants.idealcondition.ordinal() ];
	}

	public void setCdapIdealcondition(String cdapIdealcondition) {
		saveArray[ tableFldConstants.idealcondition.ordinal() ] = cdapIdealcondition;
	}

	public String getCdapStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setCdapStatus(String cdapStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = cdapStatus;
	}

	public String getCdapActionrequired() {
		return (String) saveArray[ tableFldConstants.actionrequired.ordinal() ];
	}

	public void setCdapActionrequired(String cdapActionrequired) {
		saveArray[ tableFldConstants.actionrequired.ordinal() ] = cdapActionrequired;
	}

	public String getCdapRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setCdapRemarks(String cdapRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = cdapRemarks;
	}

	public String getCdapActualconditin() {
		return (String) saveArray[ tableFldConstants.actualconditin.ordinal() ];
	}

	public void setCdapActualconditin(String cdapActualconditin) {
		saveArray[ tableFldConstants.actualconditin.ordinal() ] = cdapActualconditin;
	}

	public String getCdapResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setCdapResponsibility(String cdapResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = cdapResponsibility;
	}

	public String getCdapTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setCdapTargetdate(String cdapTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = cdapTargetdate;
	}

	public String getCdapCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setCdapCompletedby(String cdapCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = cdapCompletedby;
	}

	public String getCdapCompleteddate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}

	public void setCdapCompleteddate(String cdapCompleteddate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = cdapCompleteddate;
	}

	public String getCdapTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCdapTempfield1(String cdapTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = cdapTempfield1;
	}

	public String getCdapTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCdapTempfield2(String cdapTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cdapTempfield2;
	}

	public String getCdapTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCdapTempfield3(String cdapTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cdapTempfield3;
	}

	public String getCdapTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCdapTempfield4(String cdapTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = cdapTempfield4;
	}

	public String getCdapActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCdapActive(String cdapActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cdapActive;
	}

	public String getCdapCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCdapCreatedby(String cdapCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cdapCreatedby;
	}

	public String getCdapCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCdapCreatedon(String cdapCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cdapCreatedon;
	}

	public String getCdapModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCdapModifiedon(String cdapModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cdapModifiedon;
	}

	
}

