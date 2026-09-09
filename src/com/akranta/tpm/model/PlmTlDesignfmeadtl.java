package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlDesignfmeadtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, fmdm_keyid, item, function, potentialfailmode, potentialeffectfail
		, potentialcausefail, severity_keyid, occurrence_keyid, detection_keyid
		, rpn, currentcontrol, actionplan, reseverity_keyid, reoccurrence_keyid
		, redetection_keyid, rerpn, reviewby, redate, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public PlmTlDesignfmeadtl()
	{
		saveArray = new  Object [ 28 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFmddKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFmddKeyid(String fmddKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fmddKeyid;
	}

	public String getFmddFmdmKeyid() {
		return (String) saveArray[ tableFldConstants.fmdm_keyid.ordinal() ];
	}

	public void setFmddFmdmKeyid(String fmddFmdmKeyid) {
		saveArray[ tableFldConstants.fmdm_keyid.ordinal() ] = fmddFmdmKeyid;
	}

	public String getFmddItem() {
		return (String) saveArray[ tableFldConstants.item.ordinal() ];
	}

	public void setFmddItem(String fmddItem) {
		saveArray[ tableFldConstants.item.ordinal() ] = fmddItem;
	}

	public String getFmddFunction() {
		return (String) saveArray[ tableFldConstants.function.ordinal() ];
	}

	public void setFmddFunction(String fmddFunction) {
		saveArray[ tableFldConstants.function.ordinal() ] = fmddFunction;
	}

	public String getFmddPotentialfailmode() {
		return (String) saveArray[ tableFldConstants.potentialfailmode.ordinal() ];
	}

	public void setFmddPotentialfailmode(String fmddPotentialfailmode) {
		saveArray[ tableFldConstants.potentialfailmode.ordinal() ] = fmddPotentialfailmode;
	}

	public String getFmddPotentialeffectfail() {
		return (String) saveArray[ tableFldConstants.potentialeffectfail.ordinal() ];
	}

	public void setFmddPotentialeffectfail(String fmddPotentialeffectfail) {
		saveArray[ tableFldConstants.potentialeffectfail.ordinal() ] = fmddPotentialeffectfail;
	}

	public String getFmddPotentialcausefail() {
		return (String) saveArray[ tableFldConstants.potentialcausefail.ordinal() ];
	}

	public void setFmddPotentialcausefail(String fmddPotentialcausefail) {
		saveArray[ tableFldConstants.potentialcausefail.ordinal() ] = fmddPotentialcausefail;
	}

	public String getFmddSeverityKeyid() {
		return (String) saveArray[ tableFldConstants.severity_keyid.ordinal() ];
	}

	public void setFmddSeverityKeyid(String fmddSeverityKeyid) {
		saveArray[ tableFldConstants.severity_keyid.ordinal() ] = fmddSeverityKeyid;
	}

	public String getFmddOccurrenceKeyid() {
		return (String) saveArray[ tableFldConstants.occurrence_keyid.ordinal() ];
	}

	public void setFmddOccurrenceKeyid(String fmddOccurrenceKeyid) {
		saveArray[ tableFldConstants.occurrence_keyid.ordinal() ] = fmddOccurrenceKeyid;
	}

	public String getFmddDetectionKeyid() {
		return (String) saveArray[ tableFldConstants.detection_keyid.ordinal() ];
	}

	public void setFmddDetectionKeyid(String fmddDetectionKeyid) {
		saveArray[ tableFldConstants.detection_keyid.ordinal() ] = fmddDetectionKeyid;
	}

	public String getFmddRpn() {
		return (String) saveArray[ tableFldConstants.rpn.ordinal() ];
	}

	public void setFmddRpn(String fmddRpn) {
		saveArray[ tableFldConstants.rpn.ordinal() ] = fmddRpn;
	}

	public String getFmddCurrentcontrol() {
		return (String) saveArray[ tableFldConstants.currentcontrol.ordinal() ];
	}

	public void setFmddCurrentcontrol(String fmddCurrentcontrol) {
		saveArray[ tableFldConstants.currentcontrol.ordinal() ] = fmddCurrentcontrol;
	}

	public String getFmddActionplan() {
		return (String) saveArray[ tableFldConstants.actionplan.ordinal() ];
	}

	public void setFmddActionplan(String fmddActionplan) {
		saveArray[ tableFldConstants.actionplan.ordinal() ] = fmddActionplan;
	}

	public String getFmddReseverityKeyid() {
		return (String) saveArray[ tableFldConstants.reseverity_keyid.ordinal() ];
	}

	public void setFmddReseverityKeyid(String fmddReseverityKeyid) {
		saveArray[ tableFldConstants.reseverity_keyid.ordinal() ] = fmddReseverityKeyid;
	}

	public String getFmddReoccurrenceKeyid() {
		return (String) saveArray[ tableFldConstants.reoccurrence_keyid.ordinal() ];
	}

	public void setFmddReoccurrenceKeyid(String fmddReoccurrenceKeyid) {
		saveArray[ tableFldConstants.reoccurrence_keyid.ordinal() ] = fmddReoccurrenceKeyid;
	}

	public String getFmddRedetectionKeyid() {
		return (String) saveArray[ tableFldConstants.redetection_keyid.ordinal() ];
	}

	public void setFmddRedetectionKeyid(String fmddRedetectionKeyid) {
		saveArray[ tableFldConstants.redetection_keyid.ordinal() ] = fmddRedetectionKeyid;
	}

	public String getFmddRerpn() {
		return (String) saveArray[ tableFldConstants.rerpn.ordinal() ];
	}

	public void setFmddRerpn(String fmddRerpn) {
		saveArray[ tableFldConstants.rerpn.ordinal() ] = fmddRerpn;
	}

	public String getFmddReviewby() {
		return (String) saveArray[ tableFldConstants.reviewby.ordinal() ];
	}

	public void setFmddReviewby(String fmddReviewby) {
		saveArray[ tableFldConstants.reviewby.ordinal() ] = fmddReviewby;
	}

	public String getFmddRedate() {
		return (String) saveArray[ tableFldConstants.redate.ordinal() ];
	}

	public void setFmddRedate(String fmddRedate) {
		saveArray[ tableFldConstants.redate.ordinal() ] = fmddRedate;
	}

	public String getFmddTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFmddTempfield1(String fmddTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fmddTempfield1;
	}

	public String getFmddTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFmddTempfield2(String fmddTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fmddTempfield2;
	}

	public String getFmddTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFmddTempfield3(String fmddTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fmddTempfield3;
	}

	public String getFmddTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFmddTempfield4(String fmddTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fmddTempfield4;
	}

	public String getFmddTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFmddTempfield5(String fmddTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fmddTempfield5;
	}

	public String getFmddActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFmddActive(String fmddActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fmddActive;
	}

	public String getFmddCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFmddCreatedby(String fmddCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fmddCreatedby;
	}

	public String getFmddCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFmddCreatedon(String fmddCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fmddCreatedon;
	}

	public String getFmddModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFmddModifiedon(String fmddModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fmddModifiedon;
	}

}

