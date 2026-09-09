package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class AbnTlDtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid,abnormalityid, ispokayokeprovided, priority, hirarefno, immediateaction
		, effectleadsto, avoidrecurrence, pokayokeid, improvementteam
		, responsiblity, circleid, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public AbnTlDtl()
	{
		saveArray = new  Object [  20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getAbndKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAbndKeyid(String abndKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = abndKeyid;
	}
	public String getAbndAbnormalityid() {
		return (String) saveArray[ tableFldConstants.abnormalityid.ordinal() ];
	}

	public void setAbndAbnormalityid(String abndAbnormalityid) {
		saveArray[ tableFldConstants.abnormalityid.ordinal() ] = abndAbnormalityid;
	}
	
	public String getAbndIspokayokeprovided() {
		return (String) saveArray[ tableFldConstants.ispokayokeprovided.ordinal() ];
	}

	public void setAbndIspokayokeprovided(String abndIspokayokeprovided) {
		saveArray[ tableFldConstants.ispokayokeprovided.ordinal() ] = abndIspokayokeprovided;
	}

	public String getAbndPriority() {
		return (String) saveArray[ tableFldConstants.priority.ordinal() ];
	}

	public void setAbndPriority(String abndPriority) {
		saveArray[ tableFldConstants.priority.ordinal() ] = abndPriority;
	}

	public String getAbndHirarefno() {
		return (String) saveArray[ tableFldConstants.hirarefno.ordinal() ];
	}

	public void setAbndHirarefno(String abndHirarefno) {
		saveArray[ tableFldConstants.hirarefno.ordinal() ] = abndHirarefno;
	}

	public String getAbndImmediateaction() {
		return (String) saveArray[ tableFldConstants.immediateaction.ordinal() ];
	}

	public void setAbndImmediateaction(String abndImmediateaction) {
		saveArray[ tableFldConstants.immediateaction.ordinal() ] = abndImmediateaction;
	}

	public String getAbndEffectleadsto() {
		return (String) saveArray[ tableFldConstants.effectleadsto.ordinal() ];
	}

	public void setAbndEffectleadsto(String abndEffectleadsto) {
		saveArray[ tableFldConstants.effectleadsto.ordinal() ] = abndEffectleadsto;
	}

	public String getAbndAvoidrecurrence() {
		return (String) saveArray[ tableFldConstants.avoidrecurrence.ordinal() ];
	}

	public void setAbndAvoidrecurrence(String abndAvoidrecurrence) {
		saveArray[ tableFldConstants.avoidrecurrence.ordinal() ] = abndAvoidrecurrence;
	}

	public String getAbndPokayokeid() {
		return (String) saveArray[ tableFldConstants.pokayokeid.ordinal() ];
	}

	public void setAbndPokayokeid(String abndPokayokeid) {
		saveArray[ tableFldConstants.pokayokeid.ordinal() ] = abndPokayokeid;
	}

	public String getAbndImprovementteam() {
		return (String) saveArray[ tableFldConstants.improvementteam.ordinal() ];
	}

	public void setAbndImprovementteam(String abndImprovementteam) {
		saveArray[ tableFldConstants.improvementteam.ordinal() ] = abndImprovementteam;
	}

	public String getAbndResponsiblity() {
		return (String) saveArray[ tableFldConstants.responsiblity.ordinal() ];
	}

	public void setAbndResponsiblity(String abndResponsiblity) {
		saveArray[ tableFldConstants.responsiblity.ordinal() ] = abndResponsiblity;
	}

	public String getAbndCircleid() {
		return (String) saveArray[ tableFldConstants.circleid.ordinal() ];
	}

	public void setAbndCircleid(String abndCircleid) {
		saveArray[ tableFldConstants.circleid.ordinal() ] = abndCircleid;
	}

	public String getAbndTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setAbndTempfield2(String abndTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = abndTempfield2;
	}

	public String getAbndTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAbndTempfield3(String abndTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = abndTempfield3;
	}

	public String getAbndTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setAbndTempfield4(String abndTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = abndTempfield4;
	}

	public String getAbndTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setAbndTempfield5(String abndTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = abndTempfield5;
	}

	public String getAbndActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAbndActive(String abndActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = abndActive;
	}

	public String getAbndCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAbndCreatedby(String abndCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = abndCreatedby;
	}

	public String getAbndCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAbndCreatedon(String abndCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = abndCreatedon;
	}

	public String getAbndModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAbndModifiedon(String abndModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = abndModifiedon;
	}

	

}

