package com.akranta.tpm.model;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class GenTlLayoutfieldimg {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, bloblength, blobimage, filename, tempfield1, tempfield2
		, tempfield3, active, createdby, createdon, modifiedon
	}

	public GenTlLayoutfieldimg()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getLyfiKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setLyfiKeyid(String lyfiKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = lyfiKeyid;
	}

	public String getLyfiBloblength() {
		return (String) saveArray[ tableFldConstants.bloblength.ordinal() ];
	}

	public void setLyfiBloblength(String lyfiBloblength) {
		saveArray[ tableFldConstants.bloblength.ordinal() ] = lyfiBloblength;
	}

	public String getLyfiBlobimage() {
		return (String) saveArray[ tableFldConstants.blobimage.ordinal() ];
	}

	public void setLyfiBlobimage(String fis) {
		saveArray[ tableFldConstants.blobimage.ordinal() ] = fis;
	}

	public String getLyfiFilename() {
		return (String) saveArray[ tableFldConstants.filename.ordinal() ];
	}

	public void setLyfiFilename(String lyfiFilename) {
		saveArray[ tableFldConstants.filename.ordinal() ] = lyfiFilename;
	}

	public String getLyfiTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setLyfiTempfield1(String lyfiTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = lyfiTempfield1;
	}

	public String getLyfiTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setLyfiTempfield2(String lyfiTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = lyfiTempfield2;
	}

	public String getLyfiTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setLyfiTempfield3(String lyfiTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = lyfiTempfield3;
	}

	public String getLyfiActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setLyfiActive(String lyfiActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = lyfiActive;
	}

	public String getLyfiCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setLyfiCreatedby(String lyfiCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = lyfiCreatedby;
	}

	public String getLyfiCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setLyfiCreatedon(String lyfiCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = lyfiCreatedon;
	}

	public String getLyfiModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setLyfiModifiedon(String lyfiModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = lyfiModifiedon;
	}

}

