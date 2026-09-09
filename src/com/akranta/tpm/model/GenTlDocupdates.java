package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlDocupdates {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, feedbackid, refdoctype, refdocid, updatedoctype, detailid
		, createdby, createdon, modifiedon
	}

	public GenTlDocupdates()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getDcupKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDcupKeyid(String dcupKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dcupKeyid;
	}

	public String getDcupFeedbackid() {
		return (String) saveArray[ tableFldConstants.feedbackid.ordinal() ];
	}

	public void setDcupFeedbackid(String dcupFeedbackid) {
		saveArray[ tableFldConstants.feedbackid.ordinal() ] = dcupFeedbackid;
	}

	public String getDcupRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setDcupRefdoctype(String dcupRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = dcupRefdoctype;
	}

	public String getDcupRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setDcupRefdocid(String dcupRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = dcupRefdocid;
	}

	public String getDcupUpdatedoctype() {
		return (String) saveArray[ tableFldConstants.updatedoctype.ordinal() ];
	}

	public void setDcupUpdatedoctype(String dcupUpdatedoctype) {
		saveArray[ tableFldConstants.updatedoctype.ordinal() ] = dcupUpdatedoctype;
	}

	public String getDcupDetailid() {
		return (String) saveArray[ tableFldConstants.detailid.ordinal() ];
	}

	public void setDcupDetailid(String dcupDetailid) {
		saveArray[ tableFldConstants.detailid.ordinal() ] = dcupDetailid;
	}

	public String getDcupCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDcupCreatedby(String dcupCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dcupCreatedby;
	}

	public String getDcupCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDcupCreatedon(String dcupCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dcupCreatedon;
	}

	public String getDcupModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDcupModifiedon(String dcupModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dcupModifiedon;
	}

}

