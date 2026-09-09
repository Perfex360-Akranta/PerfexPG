package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlAssessmentdtl {

	private  Object [] saveArray = null;  
	private List<EntTlAssessmentChecklist> entTlAssessmentChecklist;
     
	public enum   tableFldConstants
	{
		keyid, asmm_keyid, spok_keyid, topi_keyid, cutoff, score, result
		, prog_keyid, bach_keyid, current_rate, previous_rate, faculty
		, type, tempfield3, tempfield4, tempfield5, tempfield6
		, tempfield7, tempfield8, tempfield9, tempfield10, active, createdby
		, createdon, modifiedon 
	}

	public EntTlAssessmentdtl()
	{
		saveArray = new  Object [ 25 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	public String getAsmdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAsmdKeyid(String asmdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = asmdKeyid;
	}

	public String getAsmdAsmmKeyid() {
		return (String) saveArray[ tableFldConstants.asmm_keyid.ordinal() ];
	}

	public void setAsmdAsmmKeyid(String asmdAsmmKeyid) {
		saveArray[ tableFldConstants.asmm_keyid.ordinal() ] = asmdAsmmKeyid;
	}

	public String getAsmdSpokKeyid() {
		return (String) saveArray[ tableFldConstants.spok_keyid.ordinal() ];
	}

	public void setAsmdSpokKeyid(String asmdSpokKeyid) {
		saveArray[ tableFldConstants.spok_keyid.ordinal() ] = asmdSpokKeyid;
	}

	public String getAsmdTopiKeyid() {
		return (String) saveArray[ tableFldConstants.topi_keyid.ordinal() ];
	}

	public void setAsmdTopiKeyid(String asmdTopiKeyid) {
		saveArray[ tableFldConstants.topi_keyid.ordinal() ] = asmdTopiKeyid;
	}

	public String getAsmdCutoff() {
		return (String) saveArray[ tableFldConstants.cutoff.ordinal() ];
	}

	public void setAsmdCutoff(String asmdCutoff) {
		saveArray[ tableFldConstants.cutoff.ordinal() ] = asmdCutoff;
	}

	public String getAsmdScore() {
		return (String) saveArray[ tableFldConstants.score.ordinal() ];
	}

	public void setAsmdScore(String asmdScore) {
		saveArray[ tableFldConstants.score.ordinal() ] = asmdScore;
	}

	public String getAsmdResult() {
		return (String) saveArray[ tableFldConstants.result.ordinal() ];
	}

	public void setAsmdResult(String asmdResult) {
		saveArray[ tableFldConstants.result.ordinal() ] = asmdResult;
	}

	public String getAsmdProgKeyid() {
		return (String) saveArray[ tableFldConstants.prog_keyid.ordinal() ];
	}

	public void setAsmdProgKeyid(String asmdProgKeyid) {
		saveArray[ tableFldConstants.prog_keyid.ordinal() ] = asmdProgKeyid;
	}

	public String getAsmdBachKeyid() {
		return (String) saveArray[ tableFldConstants.bach_keyid.ordinal() ];
	}

	public void setAsmdBachKeyid(String asmdBachKeyid) {
		saveArray[ tableFldConstants.bach_keyid.ordinal() ] = asmdBachKeyid;
	}

	public String getAsmdCurrentRate() {
		return (String) saveArray[ tableFldConstants.current_rate.ordinal() ];
	}

	public void setAsmdCurrentRate(String asmdCurrentRate) {
		saveArray[ tableFldConstants.current_rate.ordinal() ] = asmdCurrentRate;
	}

	public String getAsmdPreviousRate() {
		return (String) saveArray[ tableFldConstants.previous_rate.ordinal() ];
	}

	public void setAsmdPreviousRate(String asmdPreviousRate) {
		saveArray[ tableFldConstants.previous_rate.ordinal() ] = asmdPreviousRate;
	}

	public String getAsmdFaculty() {
		return (String) saveArray[ tableFldConstants.faculty.ordinal() ];
	}

	public void setAsmdFaculty(String asmdFaculty) {
		saveArray[ tableFldConstants.faculty.ordinal() ] = asmdFaculty;
	}

	public String getAsmdType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setAsmdType(String asmdType) {
		saveArray[ tableFldConstants.type.ordinal() ] = asmdType;
	}

	public String getAsmdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAsmdTempfield3(String asmdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = asmdTempfield3;
	}

	public String getAsmdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setAsmdTempfield4(String asmdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = asmdTempfield4;
	}

	public String getAsmdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setAsmdTempfield5(String asmdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = asmdTempfield5;
	}

	public String getAsmdTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setAsmdTempfield6(String asmdTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = asmdTempfield6;
	}

	public String getAsmdTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setAsmdTempfield7(String asmdTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = asmdTempfield7;
	}

	public String getAsmdTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setAsmdTempfield8(String asmdTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = asmdTempfield8;
	}

	public String getAsmdTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setAsmdTempfield9(String asmdTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = asmdTempfield9;
	}

	public String getAsmdTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setAsmdTempfield10(String asmdTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = asmdTempfield10;
	}

	public String getAsmdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAsmdActive(String asmdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = asmdActive;
	}

	public String getAsmdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAsmdCreatedby(String asmdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = asmdCreatedby;
	}

	public String getAsmdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAsmdCreatedon(String asmdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = asmdCreatedon;
	}

	public String getAsmdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAsmdModifiedon(String asmdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = asmdModifiedon;
	}
	public void setEntTlAssessmentChecklist(List<EntTlAssessmentChecklist> entTlAssessmentChecklist) {
		this.entTlAssessmentChecklist =entTlAssessmentChecklist ;
	}

	public List<EntTlAssessmentChecklist> getEntTlAssessmentChecklist() {
		return this.entTlAssessmentChecklist;
	}
}

