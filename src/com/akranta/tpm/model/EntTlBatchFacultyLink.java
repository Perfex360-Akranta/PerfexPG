package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlBatchFacultyLink {

	private  Object [] saveArray = null;  
	private List<EntTlFacultymst> entTlFacultymst ;
    private String frmGrid;
	public enum   tableFldConstants
	{
		keyid, bach_keyid, ftym_keyid, eff_fromdate, eff_tilldate, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public EntTlBatchFacultyLink()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getBflkKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBflkKeyid(String bflkKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bflkKeyid;
	}

	public String getBflkBachKeyid() {
		return (String) saveArray[ tableFldConstants.bach_keyid.ordinal() ];
	}

	public void setBflkBachKeyid(String bflkBachKeyid) {
		saveArray[ tableFldConstants.bach_keyid.ordinal() ] = bflkBachKeyid;
	}

	public String getBflkFtymKeyid() {
		return (String) saveArray[ tableFldConstants.ftym_keyid.ordinal() ];
	}

	public void setBflkFtymKeyid(String bflkFtymKeyid) {
		saveArray[ tableFldConstants.ftym_keyid.ordinal() ] = bflkFtymKeyid;
	}

	public String getBflkEffFromdate() {
		return (String) saveArray[ tableFldConstants.eff_fromdate.ordinal() ];
	}

	public void setBflkEffFromdate(String bflkEffFromdate) {
		saveArray[ tableFldConstants.eff_fromdate.ordinal() ] = bflkEffFromdate;
	}

	public String getBflkEffTilldate() {
		return (String) saveArray[ tableFldConstants.eff_tilldate.ordinal() ];
	}

	public void setBflkEffTilldate(String bflkEffTilldate) {
		saveArray[ tableFldConstants.eff_tilldate.ordinal() ] = bflkEffTilldate;
	}

	public String getBflkTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setBflkTempfield1(String bflkTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = bflkTempfield1;
	}

	public String getBflkTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setBflkTempfield2(String bflkTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = bflkTempfield2;
	}

	public String getBflkTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setBflkTempfield3(String bflkTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = bflkTempfield3;
	}

	public String getBflkTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setBflkTempfield4(String bflkTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = bflkTempfield4;
	}

	public String getBflkTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setBflkTempfield5(String bflkTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = bflkTempfield5;
	}

	public String getBflkActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBflkActive(String bflkActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bflkActive;
	}

	public String getBflkCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBflkCreatedby(String bflkCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bflkCreatedby;
	}

	public String getBflkCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBflkCreatedon(String bflkCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bflkCreatedon;
	}

	public String getBflkModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBflkModifiedon(String bflkModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bflkModifiedon;
	}

	public void setEntTlFacultymst(List<EntTlFacultymst> entTlFacultymst) {
		this.entTlFacultymst = entTlFacultymst;
	}

	public List<EntTlFacultymst> getEntTlFacultymst() {
		return entTlFacultymst;
	}

	public void setFrmGrid(String frmGrid) {
		this.frmGrid = frmGrid;
	}

	public String getFrmGrid() {
		return frmGrid;
	}

}

