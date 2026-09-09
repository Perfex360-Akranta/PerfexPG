package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlAllmoduleimgfile {

	private  Object [] saveArray = null;  
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public enum   tableFldConstants
	{
		refkeyid, refdoctype, imagetype, blobimage, bloblength, filename
		, tempfield1, tempfield2, modifiedon
	}

	public GenTlAllmoduleimgfile()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getImflRefkeyid() {
		return (String) saveArray[ tableFldConstants.refkeyid.ordinal() ];
	}

	public void setImflRefkeyid(String imflRefkeyid) {
		saveArray[ tableFldConstants.refkeyid.ordinal() ] = imflRefkeyid;
	}

	public String getImflRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setImflRefdoctype(String imflRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = imflRefdoctype;
	}

	public String getImflImagetype() {
		return (String) saveArray[ tableFldConstants.imagetype.ordinal() ];
	}

	public void setImflImagetype(String imflImagetype) {
		saveArray[ tableFldConstants.imagetype.ordinal() ] = imflImagetype;
	}

	public String getImflBlobimage() {
		return (String) saveArray[ tableFldConstants.blobimage.ordinal() ];
	}

	public void setImflBlobimage(String imflBlobimage) {
		saveArray[ tableFldConstants.blobimage.ordinal() ] = imflBlobimage;
	}

	public String getImflBloblength() {
		return (String) saveArray[ tableFldConstants.bloblength.ordinal() ];
	}

	public void setImflBloblength(String imflBloblength) {
		saveArray[ tableFldConstants.bloblength.ordinal() ] = imflBloblength;
	}

	public String getImflFilename() {
		return (String) saveArray[ tableFldConstants.filename.ordinal() ];
	}

	public void setImflFilename(String imflFilename) {
		saveArray[ tableFldConstants.filename.ordinal() ] = imflFilename;
	}

	public String getImflTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setImflTempfield1(String imflTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = imflTempfield1;
	}

	public String getImflTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setImflTempfield2(String imflTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = imflTempfield2;
	}

	public String getImflModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setImflModifiedon(String imflModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = imflModifiedon;
	}

	
	
}

