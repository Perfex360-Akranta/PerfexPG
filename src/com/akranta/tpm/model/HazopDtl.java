package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class HazopDtl{
	private  Object [] saveArray = null;  
	
	public enum   tableFldConstants
	{
		keyid,mohmkeyid,rownum,guideword,parameter,deviation,causes,consequences,
		withoutsafeguards,likehood1,severity1,risk1,recommendations,
		withsafeguards,likehood2,severity2,risk2,remarks,
		responsibility,target,status, tempfield4, tempfield5,
		active, createdby, createdon, modifiedon
	}


	public HazopDtl()
	{
		saveArray = new  Object [ 27 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getMohdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMohdKeyid(String mohdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mohdKeyid;
	}

	public String getMohdMohmKeyid() {
		return (String) saveArray[ tableFldConstants.mohmkeyid.ordinal() ];
	}

	public void setMohdMohmKeyid(String mohdMohmKeyid) {
		saveArray[ tableFldConstants.mohmkeyid.ordinal() ] =mohdMohmKeyid;
	}
	
	public String getMohdRowNum() {
		return (String) saveArray[ tableFldConstants.rownum.ordinal() ];
	}

	public void setMohdRowNum(String mohdRowNum) {
		saveArray[ tableFldConstants.rownum.ordinal() ] =mohdRowNum;
	}
	
	public String getMohdGuideword() {
		return (String) saveArray[ tableFldConstants.guideword.ordinal() ];
	}

	public void setMohdGuideword(String mohdGuideword) {
		saveArray[ tableFldConstants.guideword.ordinal() ] = mohdGuideword;
	}
	public String getMohdParameter() {
		return (String) saveArray[ tableFldConstants.parameter.ordinal() ];
	}

	public void setMohdParameter(String mohdParameter) {
		saveArray[ tableFldConstants.parameter.ordinal() ] = mohdParameter;
	}
	public String getMohdDeviation() {
		return (String) saveArray[ tableFldConstants.deviation.ordinal() ];
	}

	public void setMohdDeviation(String mohdDeviation) {
		saveArray[ tableFldConstants.deviation.ordinal() ] =mohdDeviation;
	}
	
	public String getMohdCauses() {
		return (String) saveArray[ tableFldConstants.causes.ordinal() ];
	}

	public void setMohdCauses(String mohdCauses) {
		saveArray[ tableFldConstants.causes.ordinal() ] =mohdCauses;
	}
	
	public String getMohdCosequeces() {
		return (String) saveArray[ tableFldConstants.consequences.ordinal() ];
	}

	public void setMohdCosequeces(String mohdCosequeces) {
		saveArray[ tableFldConstants.consequences.ordinal() ]=mohdCosequeces;
	}
	public String getMohdWithoutSafeGuards() {
		return (String) saveArray[ tableFldConstants.withoutsafeguards.ordinal() ];
	}

	public void setMohdWithoutSafeGuards(String mohdWithoutSafeGuards) {
		saveArray[ tableFldConstants.withoutsafeguards.ordinal() ]=mohdWithoutSafeGuards;
	}
	
	public String getMohdLikeHood1() {
		return (String) saveArray[ tableFldConstants.likehood1.ordinal() ];
	}

	public void setMohdLikeHood1(String mohdLikeHood1) {
		saveArray[ tableFldConstants.likehood1.ordinal() ]=mohdLikeHood1;
	}
	
	public String getMohdSeverity1() {
		return (String) saveArray[ tableFldConstants.severity1.ordinal() ];
	}

	public void setMohdSeverity1(String mohdSeverity1) {
		saveArray[ tableFldConstants.severity1.ordinal() ]=mohdSeverity1;
	}
	public String getMohdRisk1() {
		return (String) saveArray[ tableFldConstants.risk1.ordinal() ];
	}

	public void setMohdRisk1(String mohdRisk1) {
		saveArray[ tableFldConstants.risk1.ordinal() ]=mohdRisk1;
	}
	public String getMohdRecommentations() {
		return (String) saveArray[ tableFldConstants.recommendations.ordinal() ];
	}

	public void setMohdRecommentations(String mohdRecommentations) {
		saveArray[ tableFldConstants.recommendations.ordinal() ]=mohdRecommentations;
	}
	
	
	public String getMohdWithSafeGuards() {
		return (String) saveArray[ tableFldConstants.withsafeguards.ordinal() ];
	}

	public void setMohdWithSafeGuards(String mohdWithSafeGuards) {
		saveArray[ tableFldConstants.withsafeguards.ordinal() ]=mohdWithSafeGuards;
	}
	
	public String getMohdLikeHood2() {
		return (String) saveArray[ tableFldConstants.likehood2.ordinal() ];
	}

	public void setMohdLikeHood2(String mohdLikeHood2) {
		saveArray[ tableFldConstants.likehood2.ordinal() ]=mohdLikeHood2;
	}
	
	public String getMohdSeverity2() {
		return (String) saveArray[ tableFldConstants.severity2.ordinal() ];
	}

	public void setMohdSeverity2(String mohdSeverity2) {
		saveArray[ tableFldConstants.severity2.ordinal() ]=mohdSeverity2;
	}
	public String getMohdRisk2() {
		return (String) saveArray[ tableFldConstants.risk2.ordinal() ];
	}

	public void setMohdRisk2(String mohdRisk2) {
		saveArray[ tableFldConstants.risk2.ordinal() ]=mohdRisk2;
	}
	public String getMohdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMohdRemarks(String mohdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ]=mohdRemarks;
	}
	public String getMohdResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setMohdResponsibility(String mohdResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = mohdResponsibility;
	}
	
	public String getMohdTarget() {
		return (String) saveArray[ tableFldConstants.target.ordinal() ];
	}

	public void setMohdTarget(String mohdTarget) {
		saveArray[ tableFldConstants.target.ordinal() ] = mohdTarget;
	}
	public String getMohdStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setMohdStatus(String mohdStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = mohdStatus;
	}
	public String getMohdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMohdTempfield4(String mohdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mohdTempfield4;
	}
	public String getMohdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMohdTempfield5(String mohdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mohdTempfield5;
	}

	
	public String getMohdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMohdActive(String mohdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mohdActive;
	}

	public String getMohdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMohdCreatedby(String mohdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =mohdCreatedby;
	}

	public String getMohdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMohdCreatedon(String mohdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =mohdCreatedon;
	}

	public String getMohdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMohdModifiedon(String mohdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =mohdModifiedon;
	}
}