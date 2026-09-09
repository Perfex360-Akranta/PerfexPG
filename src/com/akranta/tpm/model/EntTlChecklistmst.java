package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlChecklistmst {

	private  Object [] saveArray = null;  
	private List<EntTlChecklistdtl> checkListDetail ;

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	public enum   tableFldConstants
	{
		keyid, topi_keyid, skrm_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public EntTlChecklistmst()
	{
		saveArray = new  Object [ 11 ];
		setCheckListDetail(new ArrayList<EntTlChecklistdtl> ());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getChkmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setChkmKeyid(String chkmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = chkmKeyid;
	}

	public String getChkmTopiKeyid() {
		return (String) saveArray[ tableFldConstants.topi_keyid.ordinal() ];
	}

	public void setChkmTopiKeyid(String chkmTopiKeyid) {
		saveArray[ tableFldConstants.topi_keyid.ordinal() ] = chkmTopiKeyid;
	}

	public String getChkmSkrmKeyid() {
		return (String) saveArray[ tableFldConstants.skrm_keyid.ordinal() ];
	}

	public void setChkmSkrmKeyid(String chkmSkrmKeyid) {
		saveArray[ tableFldConstants.skrm_keyid.ordinal() ] = chkmSkrmKeyid;
	}

	public String getChkmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setChkmTempfield1(String chkmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = chkmTempfield1;
	}

	public String getChkmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setChkmTempfield2(String chkmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = chkmTempfield2;
	}

	public String getChkmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setChkmTempfield3(String chkmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = chkmTempfield3;
	}

	public String getChkmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setChkmTempfield4(String chkmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = chkmTempfield4;
	}

	public String getChkmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setChkmActive(String chkmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = chkmActive;
	}

	public String getChkmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setChkmCreatedby(String chkmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = chkmCreatedby;
	}

	public String getChkmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setChkmCreatedon(String chkmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = chkmCreatedon;
	}

	public String getChkmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setChkmModifiedon(String chkmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = chkmModifiedon;
	}

	public void setCheckListDetail(List<EntTlChecklistdtl> checkListDetail) {
		this.checkListDetail = checkListDetail;
	}

	public List<EntTlChecklistdtl> getCheckListDetail() {
		return checkListDetail;
	}

	
	
}

