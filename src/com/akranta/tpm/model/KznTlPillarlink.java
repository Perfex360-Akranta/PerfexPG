package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlPillarlink {

	private  Object [] saveArray = null;  
	private String dbMode;
	private String selectionFlag;

	public enum   tableFldConstants
	{
		kaizenid, tpmpillarid, kzncategoryid, active, createdby, createdon
		, modifiedon
	}

	public KznTlPillarlink()
	{
		saveArray = new  Object [ 7 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKzplKaizenid() {
		return (String) saveArray[ tableFldConstants.kaizenid.ordinal() ];
	}

	public void setKzplKaizenid(String kzplKaizenid) {
		saveArray[ tableFldConstants.kaizenid.ordinal() ] = kzplKaizenid;
	}

	public String getKzplTpmpillarid() {
		return (String) saveArray[ tableFldConstants.tpmpillarid.ordinal() ];
	}

	public void setKzplTpmpillarid(String kzplTpmpillarid) {
		saveArray[ tableFldConstants.tpmpillarid.ordinal() ] = kzplTpmpillarid;
	}

	public String getKzplKzncategoryid() {
		return (String) saveArray[ tableFldConstants.kzncategoryid.ordinal() ];
	}

	public void setKzplKzncategoryid(String kzplKzncategoryid) {
		saveArray[ tableFldConstants.kzncategoryid.ordinal() ] = kzplKzncategoryid;
	}

	public String getKzplActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKzplActive(String kzplActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kzplActive;
	}

	public String getKzplCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKzplCreatedby(String kzplCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kzplCreatedby;
	}

	public String getKzplCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKzplCreatedon(String kzplCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kzplCreatedon;
	}

	public String getKzplModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKzplModifiedon(String kzplModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kzplModifiedon;
	}

	/**
	 * @param selectionFlag the selectionFlag to set
	 */
	public void setSelectionFlag(String selectionFlag) {
		this.selectionFlag = selectionFlag;
	}

	/**
	 * @return the selectionFlag
	 */
	public String getSelectionFlag() {
		return selectionFlag;
	}

	/**
	 * @param dbMode the dbMode to set
	 */
	public void setDbMode(String dbMode) {
		this.dbMode = dbMode;
	}

	/**
	 * @return the dbMode
	 */
	public String getDbMode() {
		return dbMode;
	}

}

