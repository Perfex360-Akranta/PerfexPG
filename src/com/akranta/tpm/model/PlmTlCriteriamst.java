package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlCriteriamst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, code, name, minimumpoints, maximumpoints, tradeid
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public PlmTlCriteriamst()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getCriaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCriaKeyid(String criaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = criaKeyid;
	}

	public String getCriaFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setCriaFlid(String criaFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = criaFlid;
	}

	public String getCriaCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setCriaCode(String criaCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = criaCode;
	}

	public String getCriaName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setCriaName(String criaName) {
		saveArray[ tableFldConstants.name.ordinal() ] = criaName;
	}

	public String getCriaMinimumpoints() {
		return (String) saveArray[ tableFldConstants.minimumpoints.ordinal() ];
	}

	public void setCriaMinimumpoints(String criaMinimumpoints) {
		saveArray[ tableFldConstants.minimumpoints.ordinal() ] = criaMinimumpoints;
	}

	public String getCriaMaximumpoints() {
		return (String) saveArray[ tableFldConstants.maximumpoints.ordinal() ];
	}

	public void setCriaMaximumpoints(String criaMaximumpoints) {
		saveArray[ tableFldConstants.maximumpoints.ordinal() ] = criaMaximumpoints;
	}

	public String getCriaTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setCriaTradeid(String criaTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = criaTradeid;
	}

	public String getCriaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCriaTempfield2(String criaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = criaTempfield2;
	}

	public String getCriaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCriaTempfield3(String criaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = criaTempfield3;
	}

	public String getCriaTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCriaTempfield4(String criaTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = criaTempfield4;
	}

	public String getCriaTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setCriaTempfield5(String criaTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = criaTempfield5;
	}

	public String getCriaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCriaActive(String criaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = criaActive;
	}

	public String getCriaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCriaCreatedby(String criaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = criaCreatedby;
	}

	public String getCriaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCriaCreatedon(String criaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = criaCreatedon;
	}

	public String getCriaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCriaModifiedon(String criaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = criaModifiedon;
	}

}

