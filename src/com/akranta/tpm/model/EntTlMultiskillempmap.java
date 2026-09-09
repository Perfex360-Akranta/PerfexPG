package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlMultiskillempmap {

	private  Object [] saveArray = null;  
	
	private  List<EntTlMultiskillempmap> entTlMultiskillempmap;
	public enum   tableFldConstants
	{
		keyid, employeeid, unipositionid, flid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public EntTlMultiskillempmap()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getMuseKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMuseKeyid(String museKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = museKeyid;
	}

	public String getMuseEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setMuseEmployeeid(String museEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = museEmployeeid;
	}

	public String getMuseUnipositionid() {
		return (String) saveArray[ tableFldConstants.unipositionid.ordinal() ];
	}

	public void setMuseUnipositionid(String museUnipositionid) {
		saveArray[ tableFldConstants.unipositionid.ordinal() ] = museUnipositionid;
	}

	public String getMuseFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMuseFlid(String museFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = museFlid;
	}

	public String getMuseTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMuseTempfield1(String museTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = museTempfield1;
	}

	public String getMuseTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMuseTempfield2(String museTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = museTempfield2;
	}

	public String getMuseTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMuseTempfield3(String museTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = museTempfield3;
	}

	public String getMuseTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMuseTempfield4(String museTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = museTempfield4;
	}

	public String getMuseTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMuseTempfield5(String museTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = museTempfield5;
	}

	public String getMuseActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMuseActive(String museActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = museActive;
	}

	public String getMuseCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMuseCreatedby(String museCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = museCreatedby;
	}

	public String getMuseCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMuseCreatedon(String museCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = museCreatedon;
	}

	public String getMuseModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMuseModifiedon(String museModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = museModifiedon;
	}

	public void setEntTlMultiskillempmap(List<EntTlMultiskillempmap> entTlMultiskillempmap) {
		this.entTlMultiskillempmap = entTlMultiskillempmap;
	}

	public List<EntTlMultiskillempmap> getEntTlMultiskillempmap() {
		return entTlMultiskillempmap;
	}

}

