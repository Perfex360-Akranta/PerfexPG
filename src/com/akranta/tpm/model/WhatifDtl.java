package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class WhatifDtl{
	private  Object [] saveArray = null;  

	public enum   tableFldConstants
	{
		keyid,wifmkeyid,rownum,whatif,causes,consequences,
		withoutsafeguards,likehood1,severity1,risk1,recommendations,
		withsafeguards,likehood2,severity2,risk2,remarks,
		tempfield1 ,tempfield2,tempfield3, tempfield4, tempfield5,
		active, createdby, createdon, modifiedon
	}


	public WhatifDtl()
	{
		saveArray = new  Object [ 25 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getWifdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWifdKeyid(String wifdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wifdKeyid;
	}

	public String getWifdWifmKeyid() {
		return (String) saveArray[ tableFldConstants.wifmkeyid.ordinal() ];
	}

	public void setWifdWifmKeyid(String wifdWifmKeyid) {
		saveArray[ tableFldConstants.wifmkeyid.ordinal() ] =wifdWifmKeyid;
	}
	
	public String getWifdRowNum() {
		return (String) saveArray[ tableFldConstants.rownum.ordinal() ];
	}

	public void setWifdRowNum(String wifdRowNum) {
		saveArray[ tableFldConstants.rownum.ordinal() ] =wifdRowNum;
	}
	
	public String getWifdWhatIf() {
		return (String) saveArray[ tableFldConstants.whatif.ordinal() ];
	}

	public void setWifdWhatIf(String wifdWhatIf) {
		saveArray[ tableFldConstants.whatif.ordinal() ] = wifdWhatIf;
	}
	
	public String getWifdCauses() {
		return (String) saveArray[ tableFldConstants.causes.ordinal() ];
	}

	public void setWifdCauses(String wifdCauses) {
		saveArray[ tableFldConstants.causes.ordinal() ] =wifdCauses;
	}
	
	public String getWifdCosequeces() {
		return (String) saveArray[ tableFldConstants.consequences.ordinal() ];
	}

	public void setWifdCosequeces(String wifdCosequeces) {
		saveArray[ tableFldConstants.consequences.ordinal() ]=wifdCosequeces;
	}
	public String getWifdWithoutSafeGuards() {
		return (String) saveArray[ tableFldConstants.withoutsafeguards.ordinal() ];
	}

	public void setWifdWithoutSafeGuards(String wifdWithoutSafeGuards) {
		saveArray[ tableFldConstants.withoutsafeguards.ordinal() ]=wifdWithoutSafeGuards;
	}
	
	public String getWifdLikeHood1() {
		return (String) saveArray[ tableFldConstants.likehood1.ordinal() ];
	}

	public void setWifdLikeHood1(String wifdLikeHood1) {
		saveArray[ tableFldConstants.likehood1.ordinal() ]=wifdLikeHood1;
	}
	
	public String getWifdSeverity1() {
		return (String) saveArray[ tableFldConstants.severity1.ordinal() ];
	}

	public void setWifdSeverity1(String wifdSeverity1) {
		saveArray[ tableFldConstants.severity1.ordinal() ]=wifdSeverity1;
	}
	public String getWifdRisk1() {
		return (String) saveArray[ tableFldConstants.risk1.ordinal() ];
	}

	public void setWifdRisk1(String wifdRisk1) {
		saveArray[ tableFldConstants.risk1.ordinal() ]=wifdRisk1;
	}
	public String getWifdRecommentations() {
		return (String) saveArray[ tableFldConstants.recommendations.ordinal() ];
	}

	public void setWifdRecommentations(String wifdRisk1) {
		saveArray[ tableFldConstants.recommendations.ordinal() ]=wifdRisk1;
	}
	
	
	public String getWifdWithSafeGuards() {
		return (String) saveArray[ tableFldConstants.withsafeguards.ordinal() ];
	}

	public void setWifdWithSafeGuards(String wifdWithSafeGuards) {
		saveArray[ tableFldConstants.withsafeguards.ordinal() ]=wifdWithSafeGuards;
	}
	
	public String getWifdLikeHood2() {
		return (String) saveArray[ tableFldConstants.likehood2.ordinal() ];
	}

	public void setWifdLikeHood2(String wifdLikeHood2) {
		saveArray[ tableFldConstants.likehood2.ordinal() ]=wifdLikeHood2;
	}
	
	public String getWifdSeverity2() {
		return (String) saveArray[ tableFldConstants.severity2.ordinal() ];
	}

	public void setWifdSeverity2(String wifdSeverity2) {
		saveArray[ tableFldConstants.severity2.ordinal() ]=wifdSeverity2;
	}
	public String getWifdRisk2() {
		return (String) saveArray[ tableFldConstants.risk2.ordinal() ];
	}

	public void setWifdRisk2(String wifdRisk2) {
		saveArray[ tableFldConstants.risk2.ordinal() ]=wifdRisk2;
	}
	public String getWifdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setWifdRemarks(String wifdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ]=wifdRemarks;
	}
	public String getWifdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWifdTempfield1(String wifdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wifdTempfield1;
	}
	
	public String getWifdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWifdTempfield2(String wifdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wifdTempfield2;
	}
	public String getWifdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWifdTempfield3(String wifdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wifdTempfield3;
	}
	public String getWifdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setWifdTempfield4(String wifdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = wifdTempfield4;
	}
	public String getWifdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setWifdTempfield5(String wifdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = wifdTempfield5;
	}

	
	public String getWifdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWifdActive(String wifdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wifdActive;
	}

	public String getWifdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWifdCreatedby(String wifdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =wifdCreatedby;
	}

	public String getWifdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWifdCreatedon(String wifdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =wifdCreatedon;
	}

	public String getWifdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWifdModifiedon(String wifdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =wifdModifiedon;
	}

}

