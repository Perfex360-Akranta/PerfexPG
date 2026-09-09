package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class OplTlPillarlink {

	private  Object [] saveArray = null;  
	
	private String dbMode;
	private String selectionFlag;
	
	public enum   tableFldConstants
	{
		oplid, tpmpillarid, oplcategoryid, createdby, createdon
	}

	public OplTlPillarlink()
	{
		saveArray = new  Object [ 5 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOpplOplid() {
		return (String) saveArray[ tableFldConstants.oplid.ordinal() ];
	}

	public void setOpplOplid(String oppl_oplid) {
		saveArray[ tableFldConstants.oplid.ordinal() ] = oppl_oplid;
	}

	public String getOpplTpmpillarid() {
		return (String) saveArray[ tableFldConstants.tpmpillarid.ordinal() ];
	}

	public void setOpplTpmpillarid(String oppl_tpmpillarid) {
		saveArray[ tableFldConstants.tpmpillarid.ordinal() ] = oppl_tpmpillarid;
	}

	public String getOpplOplcategoryid() {
		return (String) saveArray[ tableFldConstants.oplcategoryid.ordinal() ];
	}

	public void setOpplOplcategoryid(String oppl_oplcategoryid) {
		saveArray[ tableFldConstants.oplcategoryid.ordinal() ] = oppl_oplcategoryid;
	}

	public String getOpplCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOpplCreatedby(String oppl_createdby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = oppl_createdby;
	}

	public String getOpplCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOpplCreatedon(String oppl_createdon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = oppl_createdon;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setDbMode(String mode) {
		this.dbMode = mode;
	}

	/**
	 * @return the mode
	 */
	public String getDbMode() {
		return dbMode;
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
}

