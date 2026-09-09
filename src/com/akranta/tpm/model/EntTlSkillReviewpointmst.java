package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlSkillReviewpointmst {

	private  Object [] saveArray = null;  
	
	private EntTlSkillReviewpointdet ReviewDetailas ; 
	
	public enum   tableFldConstants
	{
		keyid, flid, role_keyid, tempid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, active, createdby
		, createdon, modifiedon
	}

	public EntTlSkillReviewpointmst()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSirmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSirmKeyid(String sirmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sirmKeyid;
	}

	public String getSirmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSirmFlid(String sirmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = sirmFlid;
	}

	public String getSirmRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setSirmRoleKeyid(String sirmRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = sirmRoleKeyid;
	}

	public String getSirmTempid() {
		return (String) saveArray[ tableFldConstants.tempid.ordinal() ];
	}

	public void setSirmTempid(String sirmTempid) {
		saveArray[ tableFldConstants.tempid.ordinal() ] = sirmTempid;
	}

	public String getSirmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSirmTempfield1(String sirmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = sirmTempfield1;
	}

	public String getSirmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSirmTempfield2(String sirmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = sirmTempfield2;
	}

	public String getSirmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSirmTempfield3(String sirmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = sirmTempfield3;
	}

	public String getSirmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSirmTempfield4(String sirmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = sirmTempfield4;
	}

	public String getSirmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSirmTempfield5(String sirmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = sirmTempfield5;
	}

	public String getSirmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setSirmTempfield6(String sirmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = sirmTempfield6;
	}

	public String getSirmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setSirmTempfield7(String sirmTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = sirmTempfield7;
	}

	public String getSirmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSirmActive(String sirmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sirmActive;
	}

	public String getSirmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSirmCreatedby(String sirmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sirmCreatedby;
	}

	public String getSirmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSirmCreatedon(String sirmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sirmCreatedon;
	}

	public String getSirmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSirmModifiedon(String sirmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sirmModifiedon;
	}
	public void setReviewPointDetails(EntTlSkillReviewpointdet reviewPointDetail) {
		this.ReviewDetailas = reviewPointDetail;
	}

	public EntTlSkillReviewpointdet getReviewPointDetails() {
		return ReviewDetailas;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

}

