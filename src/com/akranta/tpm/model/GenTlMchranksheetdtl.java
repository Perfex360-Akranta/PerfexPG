package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlMchranksheetdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, masterid, parameterid, pointsscored, active, createdby
		, createdon, modifiedon
	}

	public GenTlMchranksheetdtl()
	{
		saveArray = new  Object [ 8 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMrsdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMrsdKeyid(String mrsdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mrsdKeyid;
	}

	public String getMrsdMasterid() {
		return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
	}

	public void setMrsdMasterid(String mrsdMasterid) {
		saveArray[ tableFldConstants.masterid.ordinal() ] = mrsdMasterid;
	}

	public String getMrsdParameterid() {
		return (String) saveArray[ tableFldConstants.parameterid.ordinal() ];
	}

	public void setMrsdParameterid(String mrsdParameterid) {
		saveArray[ tableFldConstants.parameterid.ordinal() ] = mrsdParameterid;
	}

	public String getMrsdPointsscored() {
		return (String) saveArray[ tableFldConstants.pointsscored.ordinal() ];
	}

	public void setMrsdPointsscored(String mrsdPointsscored) {
		saveArray[ tableFldConstants.pointsscored.ordinal() ] = mrsdPointsscored;
	}

	public String getMrsdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMrsdActive(String mrsdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mrsdActive;
	}

	public String getMrsdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMrsdCreatedby(String mrsdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mrsdCreatedby;
	}

	public String getMrsdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMrsdCreatedon(String mrsdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mrsdCreatedon;
	}

	public String getMrsdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMrsdModifiedon(String mrsdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mrsdModifiedon;
	}

}

