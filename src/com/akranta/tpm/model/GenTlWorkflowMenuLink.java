package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlWorkflowMenuLink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, menuno, wrkm_keyid, trans_code, tempfield1, tempfield2
		, tempfield3, active, createdby, createdon, modifiedon
	}

	public GenTlWorkflowMenuLink()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWrmlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWrmlKeyid(String wrmlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wrmlKeyid;
	}

	public String getWrmlMenuno() {
		return (String) saveArray[ tableFldConstants.menuno.ordinal() ];
	}

	public void setWrmlMenuno(String wrmlMenuno) {
		saveArray[ tableFldConstants.menuno.ordinal() ] = wrmlMenuno;
	}

	public String getWrmlWrkmKeyid() {
		return (String) saveArray[ tableFldConstants.wrkm_keyid.ordinal() ];
	}

	public void setWrmlWrkmKeyid(String wrmlWrkmKeyid) {
		saveArray[ tableFldConstants.wrkm_keyid.ordinal() ] = wrmlWrkmKeyid;
	}

	public String getWrmlTransCode() {
		return (String) saveArray[ tableFldConstants.trans_code.ordinal() ];
	}

	public void setWrmlTransCode(String wrmlTransCode) {
		saveArray[ tableFldConstants.trans_code.ordinal() ] = wrmlTransCode;
	}

	public String getWrmlTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWrmlTempfield1(String wrmlTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wrmlTempfield1;
	}

	public String getWrmlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWrmlTempfield2(String wrmlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wrmlTempfield2;
	}

	public String getWrmlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWrmlTempfield3(String wrmlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wrmlTempfield3;
	}

	public String getWrmlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWrmlActive(String wrmlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wrmlActive;
	}

	public String getWrmlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWrmlCreatedby(String wrmlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wrmlCreatedby;
	}

	public String getWrmlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWrmlCreatedon(String wrmlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wrmlCreatedon;
	}

	public String getWrmlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWrmlModifiedon(String wrmlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wrmlModifiedon;
	}

}

