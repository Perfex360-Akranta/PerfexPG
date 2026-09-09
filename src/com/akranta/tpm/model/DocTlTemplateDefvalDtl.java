package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class DocTlTemplateDefvalDtl {

	private  Object [] saveArray = null;  
	private List<DocTlTemplateDefvalDtl> docTlTemplateDefvalDtl ;

	public enum   tableFldConstants
	{
		keyid, dtpm_keyid, dtpd_keyid, keyword, value, dmdm_keyid, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public DocTlTemplateDefvalDtl()
	{
		saveArray = new  Object [ 14 ];
		setDocTlTemplateDefvalDtl(new ArrayList<DocTlTemplateDefvalDtl>());
	}
	
	
	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getDtpvKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDtpvKeyid(String dtpvKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dtpvKeyid;
	}

	public String getDtpvDtpmKeyid() {
		return (String) saveArray[ tableFldConstants.dtpm_keyid.ordinal() ];
	}

	public void setDtpvDtpmKeyid(String dtpvDtpmKeyid) {
		saveArray[ tableFldConstants.dtpm_keyid.ordinal() ] = dtpvDtpmKeyid;
	}

	public String getDtpvDtpdKeyid() {
		return (String) saveArray[ tableFldConstants.dtpd_keyid.ordinal() ];
	}

	public void setDtpvDtpdKeyid(String dtpvDtpdKeyid) {
		saveArray[ tableFldConstants.dtpd_keyid.ordinal() ] = dtpvDtpdKeyid;
	}

	public String getDtpvKeyword() {
		return (String) saveArray[ tableFldConstants.keyword.ordinal() ];
	}

	public void setDtpvKeyword(String dtpvKeyword) {
		saveArray[ tableFldConstants.keyword.ordinal() ] = dtpvKeyword;
	}

	public String getDtpvValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setDtpvValue(String dtpvValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = dtpvValue;
	}

	public String getDtpvDmdmKeyid() {
		return (String) saveArray[ tableFldConstants.dmdm_keyid.ordinal() ];
	}

	public void setDtpvDmdmKeyid(String dtpvDmdmKeyid) {
		saveArray[ tableFldConstants.dmdm_keyid.ordinal() ] = dtpvDmdmKeyid;
	}

	public String getDtpvTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDtpvTempfield2(String dtpvTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dtpvTempfield2;
	}

	public String getDtpvTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDtpvTempfield3(String dtpvTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dtpvTempfield3;
	}

	public String getDtpvTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDtpvTempfield4(String dtpvTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dtpvTempfield4;
	}

	public String getDtpvTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setDtpvTempfield5(String dtpvTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = dtpvTempfield5;
	}

	public String getDtpvActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDtpvActive(String dtpvActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dtpvActive;
	}

	public String getDtpvCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDtpvCreatedby(String dtpvCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dtpvCreatedby;
	}

	public String getDtpvCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDtpvCreatedon(String dtpvCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dtpvCreatedon;
	}

	public String getDtpvModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDtpvModifiedon(String dtpvModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dtpvModifiedon;
	}


	public List<DocTlTemplateDefvalDtl> getDocTlTemplateDefvalDtl() {
		return docTlTemplateDefvalDtl;
	}


	public void setDocTlTemplateDefvalDtl(
			List<DocTlTemplateDefvalDtl> docTlTemplateDefvalDtl) {
		this.docTlTemplateDefvalDtl = docTlTemplateDefvalDtl;
	}

}

