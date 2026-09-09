package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MpsTlImprovementsdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, improvementid, activitydesc, before, after, tempfield1
		, tempfield2, tempfield3, modifiedby, active, createdby, createdon
		, modifiedon
	}

	public MpsTlImprovementsdtl()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMpidKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMpidKeyid(String mpidKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mpidKeyid;
	}

	public String getMpidImprovementid() {
		return (String) saveArray[ tableFldConstants.improvementid.ordinal() ];
	}

	public void setMpidImprovementid(String mpidImprovementid) {
		saveArray[ tableFldConstants.improvementid.ordinal() ] = mpidImprovementid;
	}

	public String getMpidActivitydesc() {
		return (String) saveArray[ tableFldConstants.activitydesc.ordinal() ];
	}

	public void setMpidActivitydesc(String mpidActivitydesc) {
		saveArray[ tableFldConstants.activitydesc.ordinal() ] = mpidActivitydesc;
	}

	public String getMpidBefore() {
		return (String) saveArray[ tableFldConstants.before.ordinal() ];
	}

	public void setMpidBefore(String mpidBefore) {
		saveArray[ tableFldConstants.before.ordinal() ] = mpidBefore;
	}

	public String getMpidAfter() {
		return (String) saveArray[ tableFldConstants.after.ordinal() ];
	}

	public void setMpidAfter(String mpidAfter) {
		saveArray[ tableFldConstants.after.ordinal() ] = mpidAfter;
	}

	public String getMpidTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMpidTempfield1(String mpidTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mpidTempfield1;
	}

	public String getMpidTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMpidTempfield2(String mpidTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mpidTempfield2;
	}

	public String getMpidTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMpidTempfield3(String mpidTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mpidTempfield3;
	}

	public String getMpidModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setMpidModifiedby(String mpidModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = mpidModifiedby;
	}

	public String getMpidActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMpidActive(String mpidActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mpidActive;
	}

	public String getMpidCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMpidCreatedby(String mpidCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mpidCreatedby;
	}

	public String getMpidCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMpidCreatedon(String mpidCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mpidCreatedon;
	}

	public String getMpidModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMpidModifiedon(String mpidModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mpidModifiedon;
	}

}

