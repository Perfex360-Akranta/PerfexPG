package com.akranta.tpm.model;


public class GenTlSusaAddBehaviormst {

	private  Object [] saveArray = null;
		

	public enum   tableFldConstants
	{
		keyid, susn_keyid,flid,type,behavcategory, behavior,cause, action, probability,
		consequence,remarks,image,actionplanid,tempfield1, tempfield2,tempfield3, 
		tempfield4,tempfield5,createdby,active,createdon, modifiedon
	}
  
	public GenTlSusaAddBehaviormst()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getSuabKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSuabKeyid(String suabKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = suabKeyid;
	}

	public String getSuabSusnKeyid() {
		return (String) saveArray[ tableFldConstants.susn_keyid.ordinal() ];
	}

	public void setSuabSusnKeyid(String suabSusnKeyid) {
		saveArray[ tableFldConstants.susn_keyid.ordinal() ] = suabSusnKeyid;
	}
	public String getSuabFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSuabFlid(String suabFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = suabFlid;
	}

	public String getSuabType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setSuabType(String suabType) {
		saveArray[ tableFldConstants.type.ordinal() ] = suabType;
	}
	public String getSuabBehavCategory() {
		return (String) saveArray[ tableFldConstants.behavcategory.ordinal() ];
	}

	public void setSuabBehavCategory(String suabBehavCategory) {
		saveArray[ tableFldConstants.behavcategory.ordinal() ] = suabBehavCategory;
	}

	public String getSuabBehavior() {
		return (String) saveArray[ tableFldConstants.behavior.ordinal() ];
	}

	public void setSuabBehavior(String suabBehavior) {
		saveArray[ tableFldConstants.behavior.ordinal() ] = suabBehavior;
	}

	public String getSuabCause() {
		return (String) saveArray[ tableFldConstants.cause.ordinal() ];
	}

	public void setSuabCause(String suabCause) {
		saveArray[ tableFldConstants.cause.ordinal() ] = suabCause;
	}

	public String getSuabAction() {
		return (String) saveArray[ tableFldConstants.action.ordinal() ];
	}

	public void setSuabAction(String suabAction) {
		saveArray[ tableFldConstants.action.ordinal() ] = suabAction;
	}

	public String getSuabProbability(){
		return (String) saveArray[ tableFldConstants.probability.ordinal() ];
	}

	public void setSuabProbability(String suabProbability) {
		saveArray[ tableFldConstants.probability.ordinal() ] = suabProbability;
	}

	public String getSuabConsequence() {
		return (String) saveArray[ tableFldConstants.consequence.ordinal() ];
	}

	public void setSuabConsequence(String suabConsequence) {
		saveArray[ tableFldConstants.consequence.ordinal() ] = suabConsequence;
	}

	public String getSuabRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSuabRemarks(String suabRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = suabRemarks;
	}

	public String getSuabImage() {
		return (String) saveArray[ tableFldConstants.image.ordinal() ];
	}

	public void setSuabImage(String suabImage) {
		saveArray[ tableFldConstants.image.ordinal() ] =suabImage;
	}
	
	public String getSuabActionPlanId() {
		return (String) saveArray[ tableFldConstants.actionplanid.ordinal() ];
	}

	public void setSuabActionPlanId(String suabActionPlanId) {
		saveArray[ tableFldConstants.actionplanid.ordinal() ] =suabActionPlanId;
	}	
	public String getSuabTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSuabTempfield1(String suabTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = suabTempfield1;
	}

	public String getSuabTempField2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSuabTempField2(String suabTempField2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = suabTempField2;
	}

	public String getSuabTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSuabTempfield3(String suabTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = suabTempfield3;
	}

	public String getSuabTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSuabTempfield4(String suabTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = suabTempfield4;
	}
	public String getSuabTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSuabTempfield5(String suabTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = suabTempfield5;
	}
	public String getSuabCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSuabCreatedby(String suabCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = suabCreatedby;
	}

	public String getSuabActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSuabActive(String suabActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = suabActive;
	}
	public String getSuabCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSuabCreatedon(String suabCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = suabCreatedon;
	}

	public String getSuabModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSuabModifiedon(String suabModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = suabModifiedon;
	}
}