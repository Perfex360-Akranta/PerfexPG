package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlDmcprojectprioritydtl {

	private  Object [] saveArray = null;  
	
	private String flag;
	
	public enum   tableFldConstants
	{
		keyid, dmpm_keyid, kkpm_keyid, score, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public KznTlDmcprojectprioritydtl()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getDmdlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDmdlKeyid(String dmdlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dmdlKeyid;
	}

	public String getDmdlDmpmKeyid() {
		return (String) saveArray[ tableFldConstants.dmpm_keyid.ordinal() ];
	}

	public void setDmdlDmpmKeyid(String dmdlDmpmKeyid) {
		saveArray[ tableFldConstants.dmpm_keyid.ordinal() ] = dmdlDmpmKeyid;
	}

	public String getDmdlKkpmKeyid() {
		return (String) saveArray[ tableFldConstants.kkpm_keyid.ordinal() ];
	}

	public void setDmdlKkpmKeyid(String dmdlKkpmKeyid) {
		saveArray[ tableFldConstants.kkpm_keyid.ordinal() ] = dmdlKkpmKeyid;
	}

	public String getDmdlScore() {
		return (String) saveArray[ tableFldConstants.score.ordinal() ];
	}

	public void setDmdlScore(String dmdlScore) {
		saveArray[ tableFldConstants.score.ordinal() ] = dmdlScore;
	}

	public String getDmdlTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setDmdlTempfield1(String dmdlTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = dmdlTempfield1;
	}

	public String getDmdlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDmdlTempfield2(String dmdlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dmdlTempfield2;
	}

	public String getDmdlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDmdlTempfield3(String dmdlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dmdlTempfield3;
	}

	public String getDmdlTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDmdlTempfield4(String dmdlTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dmdlTempfield4;
	}

	public String getDmdlTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setDmdlTempfield5(String dmdlTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = dmdlTempfield5;
	}

	public String getDmdlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDmdlCreatedby(String dmdlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dmdlCreatedby;
	}

	public String getDmdlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDmdlActive(String dmdlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dmdlActive;
	}

	public String getDmdlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDmdlCreatedon(String dmdlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dmdlCreatedon;
	}

	public String getDmdlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDmdlModifiedon(String dmdlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dmdlModifiedon;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

}

