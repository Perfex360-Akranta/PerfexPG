package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlToolsmst {

	private  Object [] saveArray = null;  
	
	private GenTlToolsimg genTlToolsimg;
	public enum   tableFldConstants
	{
		keyid, name, code, category, uses, remarks, isimageavl, type
		, active, createdby, createdon, modifiedon
	}

	public GenTlToolsmst()
	{
		setGenTlToolsimg(new GenTlToolsimg());
		saveArray = new  Object [ 12 ];
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTolmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTolmKeyid(String tolm_keyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tolm_keyid;
	}

	public String getTolmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setTolmName(String tolm_name) {
		saveArray[ tableFldConstants.name.ordinal() ] = tolm_name;
	}

	public String getTolmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setTolmCode(String tolm_code) {
		saveArray[ tableFldConstants.code.ordinal() ] = tolm_code;
	}

	public String getTolmCategory() {
		return (String) saveArray[ tableFldConstants.category.ordinal() ];
	}

	public void setTolmCategory(String tolm_category) {
		saveArray[ tableFldConstants.category.ordinal() ] = tolm_category;
	}

	public String getTolmUses() {
		return (String) saveArray[ tableFldConstants.uses.ordinal() ];
	}

	public void setTolmUses(String tolm_uses) {
		saveArray[ tableFldConstants.uses.ordinal() ] = tolm_uses;
	}

	public String getTolmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setTolmRemarks(String tolm_remarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = tolm_remarks;
	}

	public String getTolmIsimageavl() {
		return (String) saveArray[ tableFldConstants.isimageavl.ordinal() ];
	}

	public void setTolmIsimageavl(String tolm_isimageavl) {
		saveArray[ tableFldConstants.isimageavl.ordinal() ] = tolm_isimageavl;
	}

	public String getTolmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setTolmType(String tolm_type) {
		saveArray[ tableFldConstants.type.ordinal() ] = tolm_type;
	}

	public String getTolmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTolmActive(String tolm_active) {
		saveArray[ tableFldConstants.active.ordinal() ] = tolm_active;
	}

	public String getTolmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTolmCreatedby(String tolm_createdby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tolm_createdby;
	}

	public String getTolmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTolmCreatedon(String tolm_createdon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tolm_createdon;
	}

	public String getTolmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTolmModifiedon(String tolm_modifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tolm_modifiedon;
	}

	public void setGenTlToolsimg(GenTlToolsimg genTlToolsimg) {
		this.genTlToolsimg = genTlToolsimg;
	}

	public GenTlToolsimg getGenTlToolsimg() {
		return genTlToolsimg;
	}

}

