package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlRoleSkillLink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, erdl_keyid, skil_keyid, eff_from_date, eff_till_date, mandatory_type
		, target_rating,rating_type, rating_number, orderno, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public EntTlRoleSkillLink()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getErslKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setErslKeyid(String erslKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = erslKeyid;
	}

	public String getErslErdlKeyid() {
		return (String) saveArray[ tableFldConstants.erdl_keyid.ordinal() ];
	}

	public void setErslErdlKeyid(String erslErdlKeyid) {
		saveArray[ tableFldConstants.erdl_keyid.ordinal() ] = erslErdlKeyid;
	}

	public String getErslSkilKeyid() {
		return (String) saveArray[ tableFldConstants.skil_keyid.ordinal() ];
	}

	public void setErslSkilKeyid(String erslSkilKeyid) {
		saveArray[ tableFldConstants.skil_keyid.ordinal() ] = erslSkilKeyid;
	}

	public String getErslEffFromDate() {
		return (String) saveArray[ tableFldConstants.eff_from_date.ordinal() ];
	}

	public void setErslEffFromDate(String erslEffFromDate) {
		saveArray[ tableFldConstants.eff_from_date.ordinal() ] = erslEffFromDate;
	}

	public String getErslEffTillDate() {
		return (String) saveArray[ tableFldConstants.eff_till_date.ordinal() ];
	}

	public void setErslEffTillDate(String erslEffTillDate) {
		saveArray[ tableFldConstants.eff_till_date.ordinal() ] = erslEffTillDate;
	}

	public String getErslMandatoryType() {
		return (String) saveArray[ tableFldConstants.mandatory_type.ordinal() ];
	}

	public void setErslMandatoryType(String erslMandatoryType) {
		saveArray[ tableFldConstants.mandatory_type.ordinal() ] = erslMandatoryType;
	}

	public String getErslTargetRating() {
		return (String) saveArray[ tableFldConstants.target_rating.ordinal() ];
	}
	
	public void setErslTargetRating(String erslTargetRating) {
		saveArray[ tableFldConstants.target_rating.ordinal() ] = erslTargetRating;
	}

	public String getErslRatingType() {
		return (String) saveArray[ tableFldConstants.rating_type.ordinal() ];
	}
	
	public void setErslRatingType(String erslRatingType) {
		saveArray[ tableFldConstants.rating_type.ordinal() ] = erslRatingType;
	}
	
	
	public String getErslRatingNumber() {
		return (String) saveArray[ tableFldConstants.rating_number.ordinal() ];
	}

	public void setErslRatingNumber(String erslRatingNumber) {
		saveArray[ tableFldConstants.rating_number.ordinal() ] = erslRatingNumber;
	}

	public String getErslOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setErslOrderno(String erslOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = erslOrderno;
	}

	public String getErslTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setErslTempfield1(String erslTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = erslTempfield1;
	}

	public String getErslTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setErslTempfield2(String erslTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = erslTempfield2;
	}

	public String getErslTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setErslTempfield3(String erslTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = erslTempfield3;
	}

	public String getErslTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setErslTempfield4(String erslTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = erslTempfield4;
	}

	public String getErslTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setErslTempfield5(String erslTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = erslTempfield5;
	}

	public String getErslActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setErslActive(String erslActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = erslActive;
	}

	public String getErslCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setErslCreatedby(String erslCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = erslCreatedby;
	}

	public String getErslCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setErslCreatedon(String erslCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = erslCreatedon;
	}

	public String getErslModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setErslModifiedon(String erslModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = erslModifiedon;
	}

}

