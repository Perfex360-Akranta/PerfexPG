package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlMultiskilldialymap {


	private  Object [] saveArray = null;  
	private  List<EntTlMultiskilldialymap> entTlMultiskilldialymap;

	public enum   tableFldConstants
	{
		keyid, date, employeeid, unipositionid, flid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public EntTlMultiskilldialymap()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}


	public String getMudmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMudmKeyid(String mudmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mudmKeyid;
	}

	public String getMudmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setMudmDate(String mudmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = mudmDate;
	}

	public String getMudmEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setMudmEmployeeid(String mudmEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = mudmEmployeeid;
	}

	public String getMudmUnipositionid() {
		return (String) saveArray[ tableFldConstants.unipositionid.ordinal() ];
	}

	public void setMudmUnipositionid(String mudmUnipositionid) {
		saveArray[ tableFldConstants.unipositionid.ordinal() ] = mudmUnipositionid;
	}

	public String getMudmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMudmFlid(String mudmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = mudmFlid;
	}

	public String getMudmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMudmTempfield1(String mudmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mudmTempfield1;
	}

	public String getMudmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMudmTempfield2(String mudmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mudmTempfield2;
	}

	public String getMudmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMudmTempfield3(String mudmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mudmTempfield3;
	}

	public String getMudmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMudmTempfield4(String mudmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mudmTempfield4;
	}

	public String getMudmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMudmTempfield5(String mudmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mudmTempfield5;
	}

	public String getMudmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMudmCreatedby(String mudmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mudmCreatedby;
	}

	public String getMudmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMudmActive(String mudmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mudmActive;
	}

	public String getMudmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMudmCreatedon(String mudmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mudmCreatedon;
	}

	public String getMudmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMudmModifiedon(String mudmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mudmModifiedon;
	}

	public void setEntTlMultiskilldialymap(List<EntTlMultiskilldialymap> entTlMultiskilldialymap) {
		this.entTlMultiskilldialymap = entTlMultiskilldialymap;
	}

	public List<EntTlMultiskilldialymap> getEntTlMultiskilldialymap() {
		return entTlMultiskilldialymap;
	}

}

