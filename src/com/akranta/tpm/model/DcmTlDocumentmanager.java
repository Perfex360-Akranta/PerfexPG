package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class DcmTlDocumentmanager {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, refdocno, refdoctype, isodoctype, slno, filename, description
		, keywords, bloblength, blobfile, category, owner, approvedby
		, subjectarea, title, path,type, active, createdby, createdon, modifiedon
	}

	public DcmTlDocumentmanager()
	{
		saveArray = new  Object [ 21 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getDmdmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDmdmKeyid(String dmdmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dmdmKeyid;
	}

	public String getDmdmRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setDmdmRefdocno(String dmdmRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = dmdmRefdocno;
	}

	public String getDmdmRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setDmdmRefdoctype(String dmdmRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = dmdmRefdoctype;
	}

	public String getDmdmIsodoctype() {
		return (String) saveArray[ tableFldConstants.isodoctype.ordinal() ];
	}

	public void setDmdmIsodoctype(String dmdmIsodoctype) {
		saveArray[ tableFldConstants.isodoctype.ordinal() ] = dmdmIsodoctype;
	}

	public String getDmdmSlno() {
		return (String) saveArray[ tableFldConstants.slno.ordinal() ];
	}

	public void setDmdmSlno(String dmdmSlno) {
		saveArray[ tableFldConstants.slno.ordinal() ] = dmdmSlno;
	}

	public String getDmdmFilename() {
		return (String) saveArray[ tableFldConstants.filename.ordinal() ];
	}

	public void setDmdmFilename(String dmdmFilename) {
		saveArray[ tableFldConstants.filename.ordinal() ] = dmdmFilename;
	}

	public String getDmdmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setDmdmDescription(String dmdmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = dmdmDescription;
	}

	public String getDmdmKeywords() {
		return (String) saveArray[ tableFldConstants.keywords.ordinal() ];
	}

	public void setDmdmKeywords(String dmdmKeywords) {
		saveArray[ tableFldConstants.keywords.ordinal() ] = dmdmKeywords;
	}

	public String getDmdmBloblength() {
		return (String) saveArray[ tableFldConstants.bloblength.ordinal() ];
	}

	public void setDmdmBloblength(String dmdmBloblength) {
		saveArray[ tableFldConstants.bloblength.ordinal() ] = dmdmBloblength;
	}

	public String getDmdmBlobfile() {
		return (String) saveArray[ tableFldConstants.blobfile.ordinal() ];
	}

	public void setDmdmBlobfile(String dmdmBlobfile) {
		saveArray[ tableFldConstants.blobfile.ordinal() ] = dmdmBlobfile;
	}

	public String getDmdmCategory() {
		return (String) saveArray[ tableFldConstants.category.ordinal() ];
	}

	public void setDmdmCategory(String dmdmCategory) {
		saveArray[ tableFldConstants.category.ordinal() ] = dmdmCategory;
	}

	public String getDmdmOwner() {
		return (String) saveArray[ tableFldConstants.owner.ordinal() ];
	}

	public void setDmdmOwner(String dmdmOwner) {
		saveArray[ tableFldConstants.owner.ordinal() ] = dmdmOwner;
	}

	public String getDmdmApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setDmdmApprovedby(String dmdmApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = dmdmApprovedby;
	}

	public String getDmdmSubjectarea() {
		return (String) saveArray[ tableFldConstants.subjectarea.ordinal() ];
	}

	public void setDmdmSubjectarea(String dmdmSubjectarea) {
		saveArray[ tableFldConstants.subjectarea.ordinal() ] = dmdmSubjectarea;
	}

	public String getDmdmTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setDmdmTitle(String dmdmTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = dmdmTitle;
	}

	public String getDmdmPath() {
		return (String) saveArray[ tableFldConstants.path.ordinal() ];
	}

	public void setDmdmPath(String dmdmPath) {
		saveArray[ tableFldConstants.path.ordinal() ] = dmdmPath;
	}

	
	public String getDmdmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setDmdmType(String dmdmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = dmdmType;
	}

	/*public String getDmdmTemp2() {
		return (String) saveArray[ tableFldConstants.temp2.ordinal() ];
	}

	public void setDmdmTemp2(String dmdmTemp2) {
		saveArray[ tableFldConstants.temp2.ordinal() ] = dmdmTemp2;
	}

	public String getDmdmTemp3() {
		return (String) saveArray[ tableFldConstants.temp3.ordinal() ];
	}

	public void setDmdmTemp3(String dmdmTemp3) {
		saveArray[ tableFldConstants.temp3.ordinal() ] = dmdmTemp3;
	}*/

	public String getDmdmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDmdmActive(String dmdmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dmdmActive;
	}

	public String getDmdmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDmdmCreatedby(String dmdmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dmdmCreatedby;
	}

	public String getDmdmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDmdmCreatedon(String dmdmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dmdmCreatedon;
	}

	public String getDmdmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDmdmModifiedon(String dmdmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dmdmModifiedon;
	}

}

