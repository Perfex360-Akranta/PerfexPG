package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlToolsimg {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, blobimage, bloblength, filename, modifiedon
	}

	public GenTlToolsimg()
	{
		saveArray = new  Object [ 5 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	public String getToimKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setToimKeyid(String toimKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = toimKeyid;
	}

	public String getToimBlobimage() {
		return (String) saveArray[ tableFldConstants.blobimage.ordinal() ];
	}

	public void setToimBlobimage(String toimBlobimage) {
		saveArray[ tableFldConstants.blobimage.ordinal() ] = toimBlobimage;
	}

	public String getToimBloblength() {
		return (String) saveArray[ tableFldConstants.bloblength.ordinal() ];
	}

	public void setToimBloblength(String toimBloblength) {
		saveArray[ tableFldConstants.bloblength.ordinal() ] = toimBloblength;
	}

	public String getToimFilename() {
		return (String) saveArray[ tableFldConstants.filename.ordinal() ];
	}

	public void setToimFilename(String toimFilename) {
		saveArray[ tableFldConstants.filename.ordinal() ] = toimFilename;
	}

	public String getToimModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setToimModifiedon(String toimModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = toimModifiedon;
	}

}

