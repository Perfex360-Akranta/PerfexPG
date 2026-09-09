package com.akranta.tpm.model;

public class GenTlFilemanager {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, refdocno, refdoctype, doctype, slno, filename, description
		, bloblength, fileblob, temp1, temp2, temp3, temp4, temp5, active
		, createdby, createdon, modifiedon
	}

	public GenTlFilemanager()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	public String getFlmnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFlmnKeyid(String flmnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = flmnKeyid;
	}

	public String getFlmnRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setFlmnRefdocno(String flmnRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = flmnRefdocno;
	}

	public String getFlmnRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setFlmnRefdoctype(String flmnRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = flmnRefdoctype;
	}

	public String getFlmnDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setFlmnDoctype(String flmnDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = flmnDoctype;
	}

	public String getFlmnSlno() {
		return (String) saveArray[ tableFldConstants.slno.ordinal() ];
	}

	public void setFlmnSlno(String flmnSlno) {
		saveArray[ tableFldConstants.slno.ordinal() ] = flmnSlno;
	}

	public String getFlmnFilename() {
		return (String) saveArray[ tableFldConstants.filename.ordinal() ];
	}

	public void setFlmnFilename(String flmnFilename) {
		saveArray[ tableFldConstants.filename.ordinal() ] = flmnFilename;
	}

	public String getFlmnDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setFlmnDescription(String flmnDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = flmnDescription;
	}

	public String getFlmnBloblength() {
		return (String) saveArray[ tableFldConstants.bloblength.ordinal() ];
	}

	public void setFlmnBloblength(String flmnBloblength) {
		saveArray[ tableFldConstants.bloblength.ordinal() ] = flmnBloblength;
	}

	public String getFlmnFileblob() {
		return (String) saveArray[ tableFldConstants.fileblob.ordinal() ];
	}

	public void setFlmnFileblob(String flmnFileblob) {
		saveArray[ tableFldConstants.fileblob.ordinal() ] = flmnFileblob;
	}

	public String getFlmnTemp1() {
		return (String) saveArray[ tableFldConstants.temp1.ordinal() ];
	}

	public void setFlmnTemp1(String flmnTemp1) {
		saveArray[ tableFldConstants.temp1.ordinal() ] = flmnTemp1;
	}

	public String getFlmnTemp2() {
		return (String) saveArray[ tableFldConstants.temp2.ordinal() ];
	}

	public void setFlmnTemp2(String flmnTemp2) {
		saveArray[ tableFldConstants.temp2.ordinal() ] = flmnTemp2;
	}

	public String getFlmnTemp3() {
		return (String) saveArray[ tableFldConstants.temp3.ordinal() ];
	}

	public void setFlmnTemp3(String flmnTemp3) {
		saveArray[ tableFldConstants.temp3.ordinal() ] = flmnTemp3;
	}

	public String getFlmnTemp4() {
		return (String) saveArray[ tableFldConstants.temp4.ordinal() ];
	}

	public void setFlmnTemp4(String flmnTemp4) {
		saveArray[ tableFldConstants.temp4.ordinal() ] = flmnTemp4;
	}

	public String getFlmnTemp5() {
		return (String) saveArray[ tableFldConstants.temp5.ordinal() ];
	}

	public void setFlmnTemp5(String flmnTemp5) {
		saveArray[ tableFldConstants.temp5.ordinal() ] = flmnTemp5;
	}

	public String getFlmnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFlmnActive(String flmnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = flmnActive;
	}

	public String getFlmnCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFlmnCreatedby(String flmnCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = flmnCreatedby;
	}

	public String getFlmnCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFlmnCreatedon(String flmnCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = flmnCreatedon;
	}

	public String getFlmnModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFlmnModifiedon(String flmnModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = flmnModifiedon;
	}

}

