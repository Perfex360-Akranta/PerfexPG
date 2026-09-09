package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SheTlRiskassessmentdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, rasm_keyid, activity, consequence, hazard, cause, probablityid
		, seviorityid, riskval, risklevelid, controltypeid, controls
		, actplan, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public SheTlRiskassessmentdtl()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getRasdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRasdKeyid(String rasdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rasdKeyid;
	}

	public String getRasdRasmKeyid() {
		return (String) saveArray[ tableFldConstants.rasm_keyid.ordinal() ];
	}

	public void setRasdRasmKeyid(String rasdRasmKeyid) {
		saveArray[ tableFldConstants.rasm_keyid.ordinal() ] = rasdRasmKeyid;
	}

	public String getRasdActivity() {
		return (String) saveArray[ tableFldConstants.activity.ordinal() ];
	}

	public void setRasdActivity(String rasdActivity) {
		saveArray[ tableFldConstants.activity.ordinal() ] = rasdActivity;
	}

	public String getRasdConsequence() {
		return (String) saveArray[ tableFldConstants.consequence.ordinal() ];
	}

	public void setRasdConsequence(String rasdConsequence) {
		saveArray[ tableFldConstants.consequence.ordinal() ] = rasdConsequence;
	}

	public String getRasdHazard() {
		return (String) saveArray[ tableFldConstants.hazard.ordinal() ];
	}

	public void setRasdHazard(String rasdHazard) {
		saveArray[ tableFldConstants.hazard.ordinal() ] = rasdHazard;
	}

	public String getRasdCause() {
		return (String) saveArray[ tableFldConstants.cause.ordinal() ];
	}

	public void setRasdCause(String rasdCause) {
		saveArray[ tableFldConstants.cause.ordinal() ] = rasdCause;
	}

	public String getRasdProbablityid() {
		return (String) saveArray[ tableFldConstants.probablityid.ordinal() ];
	}

	public void setRasdProbablityid(String rasdProbablityid) {
		saveArray[ tableFldConstants.probablityid.ordinal() ] = rasdProbablityid;
	}

	public String getRasdSeviorityid() {
		return (String) saveArray[ tableFldConstants.seviorityid.ordinal() ];
	}

	public void setRasdSeviorityid(String rasdSeviorityid) {
		saveArray[ tableFldConstants.seviorityid.ordinal() ] = rasdSeviorityid;
	}

	public String getRasdRiskval() {
		return (String) saveArray[ tableFldConstants.riskval.ordinal() ];
	}

	public void setRasdRiskval(String rasdRiskval) {
		saveArray[ tableFldConstants.riskval.ordinal() ] = rasdRiskval;
	}

	public String getRasdRisklevelid() {
		return (String) saveArray[ tableFldConstants.risklevelid.ordinal() ];
	}

	public void setRasdRisklevelid(String rasdRisklevelid) {
		saveArray[ tableFldConstants.risklevelid.ordinal() ] = rasdRisklevelid;
	}

	public String getRasdControltypeid() {
		return (String) saveArray[ tableFldConstants.controltypeid.ordinal() ];
	}

	public void setRasdControltypeid(String rasdControltypeid) {
		saveArray[ tableFldConstants.controltypeid.ordinal() ] = rasdControltypeid;
	}

	public String getRasdControls() {
		return (String) saveArray[ tableFldConstants.controls.ordinal() ];
	}

	public void setRasdControls(String rasdControls) {
		saveArray[ tableFldConstants.controls.ordinal() ] = rasdControls;
	}

	public String getRasdActplan() {
		return (String) saveArray[ tableFldConstants.actplan.ordinal() ];
	}

	public void setRasdActplan(String rasdActplan) {
		saveArray[ tableFldConstants.actplan.ordinal() ] = rasdActplan;
	}

	public String getRasdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setRasdTempfield1(String rasdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rasdTempfield1;
	}

	public String getRasdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setRasdTempfield2(String rasdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rasdTempfield2;
	}

	public String getRasdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setRasdTempfield3(String rasdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rasdTempfield3;
	}

	public String getRasdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setRasdTempfield4(String rasdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rasdTempfield4;
	}

	public String getRasdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setRasdTempfield5(String rasdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = rasdTempfield5;
	}

	public String getRasdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRasdActive(String rasdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = rasdActive;
	}

	public String getRasdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setRasdCreatedby(String rasdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = rasdCreatedby;
	}

	public String getRasdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRasdCreatedon(String rasdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = rasdCreatedon;
	}

	public String getRasdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRasdModifiedon(String rasdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = rasdModifiedon;
	}

}

