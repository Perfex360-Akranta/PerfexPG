package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class DocTlTemplateDefDtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, dtpm_keyid, keyword, type, tempfield, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public DocTlTemplateDefDtl()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getDtpdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDtpdKeyid(String dtpdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dtpdKeyid;
	}

	public String getDtpdDtpmKeyid() {
		return (String) saveArray[ tableFldConstants.dtpm_keyid.ordinal() ];
	}

	public void setDtpdDtpmKeyid(String dtpdDtpmKeyid) {
		saveArray[ tableFldConstants.dtpm_keyid.ordinal() ] = dtpdDtpmKeyid;
	}

	public String getDtpdKeyword() {
		return (String) saveArray[ tableFldConstants.keyword.ordinal() ];
	}

	public void setDtpdKeyword(String dtpdKeyword) {
		saveArray[ tableFldConstants.keyword.ordinal() ] = dtpdKeyword;
	}

	public String getDtpdType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setDtpdType(String dtpdType) {
		saveArray[ tableFldConstants.type.ordinal() ] = dtpdType;
	}

	public String getDtpdTempfield() {
		return (String) saveArray[ tableFldConstants.tempfield.ordinal() ];
	}

	public void setDtpdTempfield(String dtpdTempfield) {
		saveArray[ tableFldConstants.tempfield.ordinal() ] = dtpdTempfield;
	}

	public String getDtpdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDtpdTempfield2(String dtpdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dtpdTempfield2;
	}

	public String getDtpdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDtpdTempfield3(String dtpdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dtpdTempfield3;
	}

	public String getDtpdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDtpdTempfield4(String dtpdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dtpdTempfield4;
	}

	public String getDtpdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setDtpdTempfield5(String dtpdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = dtpdTempfield5;
	}

	public String getDtpdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDtpdActive(String dtpdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dtpdActive;
	}

	public String getDtpdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDtpdCreatedby(String dtpdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dtpdCreatedby;
	}

	public String getDtpdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDtpdCreatedon(String dtpdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dtpdCreatedon;
	}

	public String getDtpdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDtpdModifiedon(String dtpdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dtpdModifiedon;
	}

}

