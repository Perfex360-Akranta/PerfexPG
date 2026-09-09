package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;

public class GenTlChecklistcalanderreview {

	private  Object [] saveArray = null;
	
	public TableFieldType[] chlrDbFields;  
	

	public enum   tableFldConstants
	{
		keyid, chlmkeyid, chlckeyid, chldkeyid,feedback, reviewedweek, reviewedby, auditby
		, auditdate, status, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, active, createdby, createdon, modifiedon
	}
	public TableFieldType[] getchlrDbFields() {
		return chlrDbFields;
	}
	public GenTlChecklistcalanderreview()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getChlrKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setChlrKeyid(String chlrKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = chlrKeyid;
	}

	public String getChlrChlmkeyid() {
		return (String) saveArray[ tableFldConstants.chlmkeyid.ordinal() ];
	}

	public void setChlrChlmkeyid(String chlrChlmkeyid) {
		saveArray[ tableFldConstants.chlmkeyid.ordinal() ] = chlrChlmkeyid;
	}

	public String getChlrChlckeyid() {
		return (String) saveArray[ tableFldConstants.chlckeyid.ordinal() ];
	}

	public void setChlrChlckeyid(String chlrChlckeyid) {
		saveArray[ tableFldConstants.chlckeyid.ordinal() ] = chlrChlckeyid;
	}
	
	public String getChlrChldkeyid() {
		return (String) saveArray[ tableFldConstants.chldkeyid.ordinal() ];
	}

	public void setChlrChldkeyid(String chlrChldkeyid) {
		saveArray[ tableFldConstants.chldkeyid.ordinal() ] = chlrChldkeyid;
	}
	
	public String getChlrFeedback() {
		return (String) saveArray[ tableFldConstants.feedback.ordinal() ];
	}

	public void setChlrFeedback(String chlrFeedback) {
		saveArray[ tableFldConstants.feedback.ordinal() ] = chlrFeedback;
	}


	public String getChlrReviewedweek() {
		return (String) saveArray[ tableFldConstants.reviewedweek.ordinal() ];
	}

	public void setChlrReviewedweek(String chlrReviewedweek) {
		saveArray[ tableFldConstants.reviewedweek.ordinal() ] = chlrReviewedweek;
	}

	public String getChlrReviewedby() {
		return (String) saveArray[ tableFldConstants.reviewedby.ordinal() ];
	}

	public void setChlrReviewedby(String chlrReviewedby) {
		saveArray[ tableFldConstants.reviewedby.ordinal() ] = chlrReviewedby;
	}

	public String getChlrAuditby() {
		return (String) saveArray[ tableFldConstants.auditby.ordinal() ];
	}

	public void setChlrAuditby(String chlrAuditby) {
		saveArray[ tableFldConstants.auditby.ordinal() ] = chlrAuditby;
	}

	public String getChlrAuditdate() {
		return (String) saveArray[ tableFldConstants.auditdate.ordinal() ];
	}

	public void setChlrAuditdate(String chlrAuditdate) {
		saveArray[ tableFldConstants.auditdate.ordinal() ] = chlrAuditdate;
	}

	public String getChlrStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setChlrStatus(String chlrStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = chlrStatus;
	}

	public String getChlrTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setChlrTempfield1(String chlrTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = chlrTempfield1;
	}

	public String getChlrTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setChlrTempfield2(String chlrTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = chlrTempfield2;
	}

	public String getChlrTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setChlrTempfield3(String chlrTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = chlrTempfield3;
	}

	public String getChlrTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setChlrTempfield4(String chlrTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = chlrTempfield4;
	}

	public String getChlrTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setChlrTempfield5(String chlrTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = chlrTempfield5;
	}

	public String getChlrTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setChlrTempfield6(String chlrTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = chlrTempfield6;
	}

	public String getChlrActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setChlrActive(String chlrActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = chlrActive;
	}

	public String getChlrCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setChlrCreatedby(String chlrCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = chlrCreatedby;
	}

	public String getChlrCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setChlrCreatedon(String chlrCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = chlrCreatedon;
	}

	public String getChlrModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setChlrModifiedon(String chlrModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = chlrModifiedon;
	}

}

