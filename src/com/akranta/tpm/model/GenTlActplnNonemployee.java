package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlActplnNonemployee {
	

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, apld_keyid, refdocid, remarks, tempfiled1, tempfiled2
		, tempfiled3, tempfiled4, tempfiled5, tempfiled6, active, createdby
		, createdon, modifiedon
	}

	public GenTlActplnNonemployee()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getNactKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setNactKeyid(String nactKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = nactKeyid;
	}

	public String getNactName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setNactName(String nactName) {
		saveArray[ tableFldConstants.name.ordinal() ] = nactName;
	}

	public String getNactApldKeyid() {
		return (String) saveArray[ tableFldConstants.apld_keyid.ordinal() ];
	}

	public void setNactApldKeyid(String nactApldKeyid) {
		saveArray[ tableFldConstants.apld_keyid.ordinal() ] = nactApldKeyid;
	}

	public String getNactRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setNactRefdocid(String nactRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = nactRefdocid;
	}

	public String getNactRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setNactRemarks(String nactRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = nactRemarks;
	}

	public String getNactTempfiled1() {
		return (String) saveArray[ tableFldConstants.tempfiled1.ordinal() ];
	}

	public void setNactTempfiled1(String nactTempfiled1) {
		saveArray[ tableFldConstants.tempfiled1.ordinal() ] = nactTempfiled1;
	}

	public String getNactTempfiled2() {
		return (String) saveArray[ tableFldConstants.tempfiled2.ordinal() ];
	}

	public void setNactTempfiled2(String nactTempfiled2) {
		saveArray[ tableFldConstants.tempfiled2.ordinal() ] = nactTempfiled2;
	}

	public String getNactTempfiled3() {
		return (String) saveArray[ tableFldConstants.tempfiled3.ordinal() ];
	}

	public void setNactTempfiled3(String nactTempfiled3) {
		saveArray[ tableFldConstants.tempfiled3.ordinal() ] = nactTempfiled3;
	}

	public String getNactTempfiled4() {
		return (String) saveArray[ tableFldConstants.tempfiled4.ordinal() ];
	}

	public void setNactTempfiled4(String nactTempfiled4) {
		saveArray[ tableFldConstants.tempfiled4.ordinal() ] = nactTempfiled4;
	}

	public String getNactTempfiled5() {
		return (String) saveArray[ tableFldConstants.tempfiled5.ordinal() ];
	}

	public void setNactTempfiled5(String nactTempfiled5) {
		saveArray[ tableFldConstants.tempfiled5.ordinal() ] = nactTempfiled5;
	}

	public String getNactTempfiled6() {
		return (String) saveArray[ tableFldConstants.tempfiled6.ordinal() ];
	}

	public void setNactTempfiled6(String nactTempfiled6) {
		saveArray[ tableFldConstants.tempfiled6.ordinal() ] = nactTempfiled6;
	}

	public String getNactActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setNactActive(String nactActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = nactActive;
	}

	public String getNactCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setNactCreatedby(String nactCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = nactCreatedby;
	}

	public String getNactCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setNactCreatedon(String nactCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = nactCreatedon;
	}

	public String getNactModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setNactModifiedon(String nactModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = nactModifiedon;
	}

}

