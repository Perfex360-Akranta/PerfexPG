package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlSusamst {

	private  Object [] saveArray = null;
	private String fileDir;
	private String imagePath;
	private List<GenTlSusadtl> GenTlSusadtl ;
	private List<GenTlAllmoduleimgfile> allmoduleimgfile;
	private String elementid;	

	public enum   tableFldConstants
	{
		keyid, flid, date, description, preparedby, participants
		, conimage, nconimage, docno, safeornot, safe, unsafe
		, tempfield5, tempfield6, active, createdby, createdon, modifiedon
	}

	public GenTlSusamst()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSusmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSusmKeyid(String susmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = susmKeyid;
	}

	public String getSusmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSusmFlid(String susmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = susmFlid;
	}


	public String getSusmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setSusmDate(String susmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = susmDate;
	}

	public String getSusmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setSusmDescription(String susmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = susmDescription;
	}

	public String getSusmPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setSusmPreparedby(String susmPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = susmPreparedby;
	}

	public String getSusmParticipants() {
		return (String) saveArray[ tableFldConstants.participants.ordinal() ];
	}

	public void setSusmParticipants(String susmParticipants) {
		saveArray[ tableFldConstants.participants.ordinal() ] = susmParticipants;
	}

	public String getSusmConimage() {
		return (String) saveArray[ tableFldConstants.conimage.ordinal() ];
	}

	public void setSusmConimage(String susmConimage) {
		saveArray[ tableFldConstants.conimage.ordinal() ] = susmConimage;
	}

	public String getSusmNconimage() {
		return (String) saveArray[ tableFldConstants.nconimage.ordinal() ];
	}

	public void setSusmNconimage(String susmNconimage) {
		saveArray[ tableFldConstants.nconimage.ordinal() ] = susmNconimage;
	}

	public String getSusmDocno() {
		return (String) saveArray[ tableFldConstants.docno.ordinal() ];
	}

	public void setSusmDocno(String susmDocno) {
		saveArray[ tableFldConstants.docno.ordinal() ] = susmDocno;
	}

	public String getSusmSafeornot() {
		return (String) saveArray[ tableFldConstants.safeornot.ordinal() ];
	}

	public void setSusmSafeornot(String susmSafeornot) {
		saveArray[ tableFldConstants.safeornot.ordinal() ] = susmSafeornot;
	}

	public String getSusmSafe() {
		return (String) saveArray[ tableFldConstants.safe.ordinal() ];
	}

	public void setSusmSafe(String susmSafe) {
		saveArray[ tableFldConstants.safe.ordinal() ] = susmSafe;
	}

	public String getSusmUnsafe() {
		return (String) saveArray[ tableFldConstants.unsafe.ordinal() ];
	}

	public void setSusmUnsafe(String susmUnSafe) {
		saveArray[ tableFldConstants.unsafe.ordinal() ] = susmUnSafe;
	}

	public String getSusmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSusmTempfield5(String susmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = susmTempfield5;
	}

	public String getSusmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setSusmTempfield6(String susmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = susmTempfield6;
	}

	public String getSusmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSusmActive(String susmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = susmActive;
	}

	public String getSusmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSusmCreatedby(String susmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = susmCreatedby;
	}

	public String getSusmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSusmCreatedon(String susmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = susmCreatedon;
	}

	public String getSusmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSusmModifiedon(String susmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = susmModifiedon;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

	public void setFileDir(String fileDir) {
		// TODO Auto-generated method stub
		this.fileDir = fileDir;
		
	}
	public String getFileDir() {
		return fileDir;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}


	public List<GenTlSusadtl> getGenTlSusadtl() {
		return GenTlSusadtl;
	}
	
	public void setGenTlSusadtl(List<GenTlSusadtl> genTlSusadtl) {
		// TODO Auto-generated method stub
		this.GenTlSusadtl = genTlSusadtl;
		
	}

	public void setAllmoduleimgfile(List<GenTlAllmoduleimgfile> susaImgList) {
		// TODO Auto-generated method stub
		this.allmoduleimgfile = allmoduleimgfile;
	}
	public List<GenTlAllmoduleimgfile> getAllmoduleimgfile() {
		return allmoduleimgfile;
	}


	public void setElementid(String elementid) {
		this.elementid = elementid;
	}
	
	public String getElementid() {
		return elementid;
	}
}