package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_BdmTlYyeffectivedtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, yyef_keyid, countermesid, countermestype, empm_keyid, countermesdate
		, effectiveid, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, tempfield7, active, createdby, createdon
		, modifiedon
	}

	public BAL_BdmTlYyeffectivedtl()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getYyedKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setYyedKeyid(String yyedKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = yyedKeyid;
	}

	public String getYyedYyefKeyid() {
		return (String) saveArray[ tableFldConstants.yyef_keyid.ordinal() ];
	}

	public void setYyedYyefKeyid(String yyedYyefKeyid) {
		saveArray[ tableFldConstants.yyef_keyid.ordinal() ] = yyedYyefKeyid;
	}

	public String getYyedCountermesid() {
		return (String) saveArray[ tableFldConstants.countermesid.ordinal() ];
	}

	public void setYyedCountermesid(String yyedCountermesid) {
		saveArray[ tableFldConstants.countermesid.ordinal() ] = yyedCountermesid;
	}

	public String getYyedCountermestype() {
		return (String) saveArray[ tableFldConstants.countermestype.ordinal() ];
	}

	public void setYyedCountermestype(String yyedCountermestype) {
		saveArray[ tableFldConstants.countermestype.ordinal() ] = yyedCountermestype;
	}

	public String getYyedEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setYyedEmpmKeyid(String yyedEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = yyedEmpmKeyid;
	}

	public String getYyedCountermesdate() {
		return (String) saveArray[ tableFldConstants.countermesdate.ordinal() ];
	}

	public void setYyedCountermesdate(String yyedCountermesdate) {
		saveArray[ tableFldConstants.countermesdate.ordinal() ] = yyedCountermesdate;
	}

	public String getYyedEffectiveid() {
		return (String) saveArray[ tableFldConstants.effectiveid.ordinal() ];
	}

	public void setYyedEffectiveid(String yyedEffectiveid) {
		saveArray[ tableFldConstants.effectiveid.ordinal() ] = yyedEffectiveid;
	}

	public String getYyedTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setYyedTempfield1(String yyedTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = yyedTempfield1;
	}

	public String getYyedTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setYyedTempfield2(String yyedTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = yyedTempfield2;
	}

	public String getYyedTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setYyedTempfield3(String yyedTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = yyedTempfield3;
	}

	public String getYyedTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setYyedTempfield4(String yyedTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = yyedTempfield4;
	}

	public String getYyedTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setYyedTempfield5(String yyedTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = yyedTempfield5;
	}

	public String getYyedTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setYyedTempfield6(String yyedTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = yyedTempfield6;
	}

	public String getYyedTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setYyedTempfield7(String yyedTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = yyedTempfield7;
	}

	public String getYyedActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setYyedActive(String yyedActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = yyedActive;
	}

	public String getYyedCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setYyedCreatedby(String yyedCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = yyedCreatedby;
	}

	public String getYyedCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setYyedCreatedon(String yyedCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = yyedCreatedon;
	}

	public String getYyedModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setYyedModifiedon(String yyedModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = yyedModifiedon;
	}

}

