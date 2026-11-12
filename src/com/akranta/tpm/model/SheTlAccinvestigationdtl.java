package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SheTlAccinvestigationdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, ainm_keyid, reclocation, recommendation, target, agency
		, remarks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, active, createdby, createdon, modifiedon
	}

	public SheTlAccinvestigationdtl()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getAindKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAindKeyid(String aindKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = aindKeyid;
	}

	public String getAindAinmKeyid() {
		return (String) saveArray[ tableFldConstants.ainm_keyid.ordinal() ];
	}

	public void setAindAinmKeyid(String aindAinmKeyid) {
		saveArray[ tableFldConstants.ainm_keyid.ordinal() ] = aindAinmKeyid;
	}

	public String getAindReclocation() {
		return (String) saveArray[ tableFldConstants.reclocation.ordinal() ];
	}

	public void setAindReclocation(String ainmReclocation) {
		saveArray[ tableFldConstants.reclocation.ordinal() ] = ainmReclocation;
	}

	public String getAindRecommendation() {
		return (String) saveArray[ tableFldConstants.recommendation.ordinal() ];
	}

	public void setAindRecommendation(String ainmRecommendation) {
		saveArray[ tableFldConstants.recommendation.ordinal() ] = ainmRecommendation;
	}

	public String getAindTarget() {
		return (String) saveArray[ tableFldConstants.target.ordinal() ];
	}

	public void setAindTarget(String ainmTarget) {
		saveArray[ tableFldConstants.target.ordinal() ] = ainmTarget;
	}

	public String getAindAgency() {
		return (String) saveArray[ tableFldConstants.agency.ordinal() ];
	}

	public void setAindAgency(String ainmAgency) {
		saveArray[ tableFldConstants.agency.ordinal() ] = ainmAgency;
	}

	public String getAindRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setAindRemarks(String ainmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = ainmRemarks;
	}

	public String getAindTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setAindTempfield1(String aindTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = aindTempfield1;
	}

	public String getAindTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setAindTempfield2(String aindTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = aindTempfield2;
	}

	public String getAindTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAindTempfield3(String aindTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = aindTempfield3;
	}

	public String getAindTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setAindTempfield4(String aindTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = aindTempfield4;
	}

	public String getAindTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setAindTempfield5(String aindTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = aindTempfield5;
	}

	public String getAindTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setAindTempfield6(String aindTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = aindTempfield6;
	}

	public String getAindTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setAindTempfield7(String aindTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = aindTempfield7;
	}

	public String getAindActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAindActive(String aindActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = aindActive;
	}

	public String getAindCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAindCreatedby(String aindCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = aindCreatedby;
	}

	public String getAindCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAindCreatedon(String aindCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = aindCreatedon;
	}

	public String getAindModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAindModifiedon(String aindModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = aindModifiedon;
	}

}

