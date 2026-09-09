
package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlNearmissreportdtlnew {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, nmrtkeyid, nearkeyid, code, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public GenTlNearmissreportdtlnew()
	{
		saveArray = new  Object [ 12 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray=saveArray;
	}


	public String getNmuaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setNmuaKeyid(String nmuaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = nmuaKeyid;
	}

	public String getNmuaNmrtkeyid() {
		return (String) saveArray[ tableFldConstants.nmrtkeyid.ordinal() ];
	}

	public void setNmuaNmrtkeyid(String nmuaNmrtkeyid) {
		saveArray[ tableFldConstants.nmrtkeyid.ordinal() ] = nmuaNmrtkeyid;
	}

	public String getNmuaNearkeyid() {
		return (String) saveArray[ tableFldConstants.nearkeyid.ordinal() ];
	}

	public void setNmuaNearkeyid(String nmuaNearkeyid) {
		saveArray[ tableFldConstants.nearkeyid.ordinal() ] = nmuaNearkeyid;
	}

	public String getNmuaCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setNmuaCode(String nmuaCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = nmuaCode;
	}

	public String getNmuaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setNmuaTempfield1(String nmuaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = nmuaTempfield1;
	}

	public String getNmuaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setNmuaTempfield2(String nmuaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = nmuaTempfield2;
	}

	public String getNmuaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setNmuaTempfield3(String nmuaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = nmuaTempfield3;
	}

	public String getNmuaTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setNmuaTempfield4(String nmuaTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = nmuaTempfield4;
	}

	public String getNmuaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setNmuaActive(String nmuaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = nmuaActive;
	}

	public String getNmuaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setNmuaCreatedby(String nmuaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = nmuaCreatedby;
	}

	public String getNmuaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setNmuaCreatedon(String nmuaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = nmuaCreatedon;
	}

	public String getNmuaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setNmuaModifiedon(String nmuaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = nmuaModifiedon;
	}

}

