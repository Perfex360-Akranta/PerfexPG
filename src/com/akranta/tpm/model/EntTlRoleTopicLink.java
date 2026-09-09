package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlRoleTopicLink {

	private  Object [] saveArray = null;  
	private List<EntTlRoleTopicRating> entTlRoleTopicRatingList = null; 	
	 private String isUniquePositionLink;
	public enum   tableFldConstants
	{
		keyid, rtal_keyid, spok_id, topi_keyid, targetskrm_keyid, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public EntTlRoleTopicLink()
	{
		saveArray = new  Object [ 14 ];
		setEntTlRoleTopicRatingList(new ArrayList<EntTlRoleTopicRating>());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getRtlkKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRtlkKeyid(String rtlkKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rtlkKeyid;
	}

	public String getRtlkRtalKeyid() {
		return (String) saveArray[ tableFldConstants.rtal_keyid.ordinal() ];
	}

	public void setRtlkRtalKeyid(String rtlkRtalKeyid) {
		saveArray[ tableFldConstants.rtal_keyid.ordinal() ] = rtlkRtalKeyid;
	}

	public String getRtlkSpokId() {
		return (String) saveArray[ tableFldConstants.spok_id.ordinal() ];
	}

	public void setRtlkSpokId(String rtlkSpokId) {
		saveArray[ tableFldConstants.spok_id.ordinal() ] = rtlkSpokId;
	}

	public String getRtlkTopiKeyid() {
		return (String) saveArray[ tableFldConstants.topi_keyid.ordinal() ];
	}

	public void setRtlkTopiKeyid(String rtlkTopiKeyid) {
		saveArray[ tableFldConstants.topi_keyid.ordinal() ] = rtlkTopiKeyid;
	}

	public String getRtlkTargetskrmKeyid() {
		return (String) saveArray[ tableFldConstants.targetskrm_keyid.ordinal() ];
	}

	public void setRtlkTargetskrmKeyid(String rtlkTargetskrmKeyid) {
		saveArray[ tableFldConstants.targetskrm_keyid.ordinal() ] = rtlkTargetskrmKeyid;
	}

	public String getRtlkTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setRtlkTempfield1(String rtlkTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rtlkTempfield1;
	}

	public String getRtlkTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setRtlkTempfield2(String rtlkTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rtlkTempfield2;
	}

	public String getRtlkTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setRtlkTempfield3(String rtlkTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rtlkTempfield3;
	}

	public String getRtlkTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setRtlkTempfield4(String rtlkTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rtlkTempfield4;
	}

	public String getRtlkTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setRtlkTempfield5(String rtlkTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = rtlkTempfield5;
	}

	public String getRtlkActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRtlkActive(String rtlkActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = rtlkActive;
	}

	public String getRtlkCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setRtlkCreatedby(String rtlkCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = rtlkCreatedby;
	}

	public String getRtlkCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRtlkCreatedon(String rtlkCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = rtlkCreatedon;
	}

	public String getRtlkModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRtlkModifiedon(String rtlkModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = rtlkModifiedon;
	}

	public void setEntTlRoleTopicRatingList(List<EntTlRoleTopicRating> entTlRoleTopicRatingList) {
		this.entTlRoleTopicRatingList = entTlRoleTopicRatingList;
	}

	public List<EntTlRoleTopicRating> getEntTlRoleTopicRatingList() {
		return entTlRoleTopicRatingList;
	}

	public void setIsUniquePositionLink(String isUniquePositionLink) {
		this.isUniquePositionLink = isUniquePositionLink;
	}

	public String getIsUniquePositionLink() {
		return isUniquePositionLink;
	}

}

