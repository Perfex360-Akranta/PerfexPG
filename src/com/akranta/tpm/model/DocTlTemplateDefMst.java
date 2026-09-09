package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class DocTlTemplateDefMst {

	private  Object [] saveArray = null;  
	private List<DocTlTemplateDefDtl> docTempTlDtl ;
	

	public enum   tableFldConstants
	{
		keyid, documenttype, code, tempfield, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public DocTlTemplateDefMst()
	{
		saveArray = new  Object [ 12 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getDtpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDtpmKeyid(String dtpmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dtpmKeyid;
	}

	public String getDtpmDocumenttype() {
		return (String) saveArray[ tableFldConstants.documenttype.ordinal() ];
	}

	public void setDtpmDocumenttype(String dtpmDocumenttype) {
		saveArray[ tableFldConstants.documenttype.ordinal() ] = dtpmDocumenttype;
	}

	public String getDtpmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setDtpmCode(String dtpmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = dtpmCode;
	}

	public String getDtpmTempfield() {
		return (String) saveArray[ tableFldConstants.tempfield.ordinal() ];
	}

	public void setDtpmTempfield(String dtpmTempfield) {
		saveArray[ tableFldConstants.tempfield.ordinal() ] = dtpmTempfield;
	}

	public String getDtpmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDtpmTempfield2(String dtpmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dtpmTempfield2;
	}

	public String getDtpmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDtpmTempfield3(String dtpmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dtpmTempfield3;
	}

	public String getDtpmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDtpmTempfield4(String dtpmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dtpmTempfield4;
	}

	public String getDtpmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setDtpmTempfield5(String dtpmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = dtpmTempfield5;
	}

	public String getDtpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDtpmActive(String dtpmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dtpmActive;
	}

	public String getDtpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDtpmCreatedby(String dtpmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dtpmCreatedby;
	}

	public String getDtpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDtpmCreatedon(String dtpmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dtpmCreatedon;
	}

	public String getDtpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDtpmModifiedon(String dtpmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dtpmModifiedon;
	}

	public void setDocTempTlDtl(List<DocTlTemplateDefDtl> docTempTlDtl) {
		this.docTempTlDtl = docTempTlDtl;
	}

	public List<DocTlTemplateDefDtl> getDocTempTlDtl() {
		return docTempTlDtl;
	}

}

