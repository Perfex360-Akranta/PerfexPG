package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_BdmTlYycountermeasurelink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, yyid, refdoctype, countermsrid, woid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifieyon
	}

	public BAL_BdmTlYycountermeasurelink()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getYycmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setYycmKeyid(String yycmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = yycmKeyid;
	}

	public String getYycmYyid() {
		return (String) saveArray[ tableFldConstants.yyid.ordinal() ];
	}

	public void setYycmYyid(String yycmYyid) {
		saveArray[ tableFldConstants.yyid.ordinal() ] = yycmYyid;
	}

	public String getYycmRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setYycmRefdoctype(String yycmRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = yycmRefdoctype;
	}

	public String getYycmCountermsrid() {
		return (String) saveArray[ tableFldConstants.countermsrid.ordinal() ];
	}

	public void setYycmCountermsrid(String yycmCountermsrid) {
		saveArray[ tableFldConstants.countermsrid.ordinal() ] = yycmCountermsrid;
	}

	public String getYycmWoid() {
		return (String) saveArray[ tableFldConstants.woid.ordinal() ];
	}

	public void setYycmWoid(String yycmWoid) {
		saveArray[ tableFldConstants.woid.ordinal() ] = yycmWoid;
	}

	public String getYycmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setYycmTempfield1(String yycmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = yycmTempfield1;
	}

	public String getYycmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setYycmTempfield2(String yycmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = yycmTempfield2;
	}

	public String getYycmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setYycmTempfield3(String yycmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = yycmTempfield3;
	}

	public String getYycmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setYycmTempfield4(String yycmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = yycmTempfield4;
	}

	public String getYycmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setYycmTempfield5(String yycmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = yycmTempfield5;
	}

	public String getYycmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setYycmActive(String yycmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = yycmActive;
	}

	public String getYycmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setYycmCreatedby(String yycmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = yycmCreatedby;
	}

	public String getYycmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setYycmCreatedon(String yycmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = yycmCreatedon;
	}

	public String getYycmModifieyon() {
		return (String) saveArray[ tableFldConstants.modifieyon.ordinal() ];
	}

	public void setYycmModifieyon(String yycmModifieyon) {
		saveArray[ tableFldConstants.modifieyon.ordinal() ] = yycmModifieyon;
	}

}

