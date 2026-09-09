package com.akranta.tpm.model;
import java.util.List;

public class EntTlProgTargetSkills {

	private  Object [] saveArray = null;  
	private  List<EntTlProgImpactSkills> entTlProgImpactSkills; 
	private String rattingDetails;
	public enum   tableFldConstants
	{
		keyid, prog_keyid, topi_keyid, skil_evaluvationtype, skil_deliverymode
		, eff_from_date, eff_till_date, impact_skillrate, rating_type
		, orderno, spokekeyid, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public EntTlProgTargetSkills()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}

	public String getPrtsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPrtsKeyid(String prtsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = prtsKeyid;
	}

	public String getPrtsProgKeyid() {
		return (String) saveArray[ tableFldConstants.prog_keyid.ordinal() ];
	}

	public void setPrtsProgKeyid(String prtsProgKeyid) {
		saveArray[ tableFldConstants.prog_keyid.ordinal() ] = prtsProgKeyid;
	}

	public String getPrtsTopiKeyid() {
		return (String) saveArray[ tableFldConstants.topi_keyid.ordinal() ];
	}

	public void setPrtsTopiKeyid(String prtsTopiKeyid) {
		saveArray[ tableFldConstants.topi_keyid.ordinal() ] = prtsTopiKeyid;
	}

	public String getPrtsSkilEvaluvationtype() {
		return (String) saveArray[ tableFldConstants.skil_evaluvationtype.ordinal() ];
	}

	public void setPrtsSkilEvaluvationtype(String prtsSkilEvaluvationtype) {
		saveArray[ tableFldConstants.skil_evaluvationtype.ordinal() ] = prtsSkilEvaluvationtype;
	}

	public String getPrtsSkilDeliverymode() {
		return (String) saveArray[ tableFldConstants.skil_deliverymode.ordinal() ];
	}

	public void setPrtsSkilDeliverymode(String prtsSkilDeliverymode) {
		saveArray[ tableFldConstants.skil_deliverymode.ordinal() ] = prtsSkilDeliverymode;
	}

	public String getPrtsEffFromDate() {
		return (String) saveArray[ tableFldConstants.eff_from_date.ordinal() ];
	}

	public void setPrtsEffFromDate(String prtsEffFromDate) {
		saveArray[ tableFldConstants.eff_from_date.ordinal() ] = prtsEffFromDate;
	}

	public String getPrtsEffTillDate() {
		return (String) saveArray[ tableFldConstants.eff_till_date.ordinal() ];
	}

	public void setPrtsEffTillDate(String prtsEffTillDate) {
		saveArray[ tableFldConstants.eff_till_date.ordinal() ] = prtsEffTillDate;
	}

	public String getPrtsImpactSkillrate() {
		return (String) saveArray[ tableFldConstants.impact_skillrate.ordinal() ];
	}

	public void setPrtsImpactSkillrate(String prtsImpactSkillrate) {
		saveArray[ tableFldConstants.impact_skillrate.ordinal() ] = prtsImpactSkillrate;
	}

	public String getPrtsRatingType() {
		return (String) saveArray[ tableFldConstants.rating_type.ordinal() ];
	}

	public void setPrtsRatingType(String prtsRatingType) {
		saveArray[ tableFldConstants.rating_type.ordinal() ] = prtsRatingType;
	}

	public String getPrtsOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setPrtsOrderno(String prtsOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = prtsOrderno;
	}

	public String getPrtsSpokeKeyid() {
		return (String) saveArray[ tableFldConstants.spokekeyid.ordinal() ];
	}

	public void setPrtsSpokeKeyid(String prtsSpokeKeyid) {
		saveArray[ tableFldConstants.spokekeyid.ordinal() ] = prtsSpokeKeyid;
	}

	public String getPrtsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPrtsTempfield2(String prtsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = prtsTempfield2;
	}

	public String getPrtsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPrtsTempfield3(String prtsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = prtsTempfield3;
	}

	public String getPrtsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPrtsTempfield4(String prtsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = prtsTempfield4;
	}

	public String getPrtsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPrtsTempfield5(String prtsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = prtsTempfield5;
	}

	public String getPrtsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPrtsActive(String prtsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = prtsActive;
	}

	public String getPrtsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPrtsCreatedby(String prtsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = prtsCreatedby;
	}

	public String getPrtsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPrtsCreatedon(String prtsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = prtsCreatedon;
	}

	public String getPrtsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPrtsModifiedon(String prtsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = prtsModifiedon;
	}

	public void setEntTlProgImpactSkills(List<EntTlProgImpactSkills> entTlProgImpactSkills) {
		this.entTlProgImpactSkills = entTlProgImpactSkills;
	}

	public List<EntTlProgImpactSkills> getEntTlProgImpactSkills() {
		return entTlProgImpactSkills;
	}

	public void setRattingDetails(String rattingDetails) {
		this.rattingDetails = rattingDetails;
	}

	public String getRattingDetails() {
		return rattingDetails;
	}

	

}

