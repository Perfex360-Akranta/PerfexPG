package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlEmployeeimg {

	private  Object [] saveArray = null;  
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		employeeid, blobimage, bloblength, filename, modifiedon
	}

	public GenTlEmployeeimg()
	{
		saveArray = new  Object [ 5 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEmpiEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setEmpiEmployeeid(String empiEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = empiEmployeeid;
	}

	public String getEmpiBlobimage() {
		return (String) saveArray[ tableFldConstants.blobimage.ordinal() ];
	}

	public void setEmpiBlobimage(String empiBlobimage) {
		saveArray[ tableFldConstants.blobimage.ordinal() ] = empiBlobimage;
	}

	public String getEmpiBloblength() {
		return (String) saveArray[ tableFldConstants.bloblength.ordinal() ];
	}

	public void setEmpiBloblength(String empiBloblength) {
		saveArray[ tableFldConstants.bloblength.ordinal() ] = empiBloblength;
	}

	public String getEmpiFilename() {
		return (String) saveArray[ tableFldConstants.filename.ordinal() ];
	}

	public void setEmpiFilename(String empiFilename) {
		saveArray[ tableFldConstants.filename.ordinal() ] = empiFilename;
	}

	public String getEmpiModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEmpiModifiedon(String empiModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = empiModifiedon;
	}


}

