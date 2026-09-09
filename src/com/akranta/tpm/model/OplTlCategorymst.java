package com.akranta.tpm.model;


import com.akranta.tpm.dao.sql.TableFieldType;

public class OplTlCategorymst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, tpmpillarid, name, code, description, remarks, active
		, createdby, createdon, modifiedon
	}

	public OplTlCategorymst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	/**
	 * @param saveArray the saveArray to set
	 */
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getOplcKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setOplcKeyid(String oplc_keyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = oplc_keyid;
	}

	public String getOplcTpmpillarid() {
		return (String) saveArray[ tableFldConstants.tpmpillarid.ordinal() ];
	}

	public void setOplcTpmpillarid(String oplc_tpmpillarid) {
		saveArray[ tableFldConstants.tpmpillarid.ordinal() ] = oplc_tpmpillarid;
	}

	public String getOplcName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setOplcName(String oplc_name) {
		saveArray[ tableFldConstants.name.ordinal() ] = oplc_name;
	}

	public String getOplcCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setOplcCode(String oplc_code) {
		saveArray[ tableFldConstants.code.ordinal() ] = oplc_code;
	}

	public String getOplcDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setOplcDescription(String oplc_description) {
		saveArray[ tableFldConstants.description.ordinal() ] = oplc_description;
	}

	public String getOplcRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setOplcRemarks(String oplc_remarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = oplc_remarks;
	}

	public String getOplcActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOplcActive(String oplc_active) {
		saveArray[ tableFldConstants.active.ordinal() ] = oplc_active;
	}

	public String getOplcCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOplcCreatedby(String oplc_createdby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = oplc_createdby;
	}

	public String getOplcCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOplcCreatedon(String oplc_createdon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = oplc_createdon;
	}

	public String getOplcModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOplcModifiedon(String oplc_modifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = oplc_modifiedon;
	}

	public static String getDeleteSql(TableFieldType[] oplcDbFields,
			Object[] saveArray2) {
		// TODO Auto-generated method stub
		return null;
	}

}

