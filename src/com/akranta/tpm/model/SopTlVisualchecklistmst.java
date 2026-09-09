package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SopTlVisualchecklistmst {

	private  Object [] saveArray = null;  
	
	
	private List<SopTlVisualchecklistdtl> visualControlmstdet ;
	public enum   tableFldConstants
	{
		keyid, title, tempfield1, flid, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, active, createdon, modifiedon
	}

	public SopTlVisualchecklistmst()
	{
		saveArray = new  Object [ 12 ];
	}

	
public Object[] getSaveArray() {
	return saveArray;
}
public void setSaveArray(Object[] saveArray) {
	this.saveArray = saveArray;
}
	public String getVccmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVccmKeyid(String vccmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = vccmKeyid;
	}

	public String getVccmTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setVccmTitle(String vccmTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = vccmTitle;
	}

	public String getVccmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setVccmTempfield1(String vccmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = vccmTempfield1;
	}

	public String getVccmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setVccmFlid(String vccmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = vccmFlid;
	}

	public String getVccmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setVccmTempfield2(String vccmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = vccmTempfield2;
	}

	public String getVccmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setVccmTempfield3(String vccmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = vccmTempfield3;
	}

	public String getVccmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setVccmTempfield4(String vccmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = vccmTempfield4;
	}

	public String getVccmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setVccmTempfield5(String vccmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = vccmTempfield5;
	}

	public String getVccmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVccmCreatedby(String vccmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = vccmCreatedby;
	}

	public String getVccmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVccmActive(String vccmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = vccmActive;
	}

	public String getVccmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVccmCreatedon(String vccmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = vccmCreatedon;
	}

	public String getVccmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVccmModifiedon(String vccmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = vccmModifiedon;
	}
	

	public void setVisualControlmstdet(List<SopTlVisualchecklistdtl> visualControlmstdet) {
		this.visualControlmstdet = visualControlmstdet;
	}

	public List<SopTlVisualchecklistdtl> getVisualControlmstdet() {
		return visualControlmstdet;
	}

	
	

}

