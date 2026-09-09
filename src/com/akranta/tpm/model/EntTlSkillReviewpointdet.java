package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlSkillReviewpointdet {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, sirm_keyid, reviewpoint, reviewno, reviewtype, spok_keyid
		, subreviewno, tempfield4, tempfield5, tempfield6, tempfield7
		, active, createdby, createdon, modifiedon
	}

	public EntTlSkillReviewpointdet()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSirdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSirdKeyid(String sirdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sirdKeyid;
	}

	public String getSirdSirmKeyid() {
		return (String) saveArray[ tableFldConstants.sirm_keyid.ordinal() ];
	}

	public void setSirdSirmKeyid(String sirdSirmKeyid) {
		saveArray[ tableFldConstants.sirm_keyid.ordinal() ] = sirdSirmKeyid;
	}

	public String getSirdReviewpoint() {
		return (String) saveArray[ tableFldConstants.reviewpoint.ordinal() ];
	}

	public void setSirdReviewpoint(String sirdReviewpoint) {
		saveArray[ tableFldConstants.reviewpoint.ordinal() ] = sirdReviewpoint;
	}

	public String getSirdReviewno() {
		return (String) saveArray[ tableFldConstants.reviewno.ordinal() ];
	}

	public void setSirdReviewno(String sirdReviewno) {
		saveArray[ tableFldConstants.reviewno.ordinal() ] = sirdReviewno;
	}

	public String getSirdReviewtype() {
		return (String) saveArray[ tableFldConstants.reviewtype.ordinal() ];
	}

	public void setSirdReviewtype(String sirdReviewtype) {
		saveArray[ tableFldConstants.reviewtype.ordinal() ] = sirdReviewtype;
	}

	public String getSirdSpokKeyid() {
		return (String) saveArray[ tableFldConstants.spok_keyid.ordinal() ];
	}

	public void setSirdSpokKeyid(String sirdSpokKeyid) {
		saveArray[ tableFldConstants.spok_keyid.ordinal() ] = sirdSpokKeyid;
	}

	public String getSirdSubreviewno() {
		return (String) saveArray[ tableFldConstants.subreviewno.ordinal() ];
	}

	public void setSirdSubreviewno(String sirdSubreviewno) {
		saveArray[ tableFldConstants.subreviewno.ordinal() ] = sirdSubreviewno;
	}

	public String getSirdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSirdTempfield4(String sirdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = sirdTempfield4;
	}

	public String getSirdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSirdTempfield5(String sirdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = sirdTempfield5;
	}

	public String getSirdTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setSirdTempfield6(String sirdTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = sirdTempfield6;
	}

	public String getSirdTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setSirdTempfield7(String sirdTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = sirdTempfield7;
	}

	public String getSirdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSirdActive(String sirdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sirdActive;
	}

	public String getSirdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSirdCreatedby(String sirdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sirdCreatedby;
	}

	public String getSirdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSirdCreatedon(String sirdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sirdCreatedon;
	}

	public String getSirdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSirdModifiedon(String sirdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sirdModifiedon;
	}

}

