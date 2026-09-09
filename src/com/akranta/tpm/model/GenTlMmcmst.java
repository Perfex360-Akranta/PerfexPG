package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

public class GenTlMmcmst {

	

	private  Object [] saveArray = null;  
	
	private List<GenTlMmcdtl> genTlMmcdtl = null ; 
	public enum   tableFldConstants
	{
		keyid, menuid, formname, ismultipletable, module, isfunctionaltable
		, option, remarks,groupbyfield,  active, createdby, createdon, modifiedon
	}

	public GenTlMmcmst()
	{
		saveArray = new  Object [ 13 ];
		setGenTlMmcdtl(new ArrayList<GenTlMmcdtl>());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMmcnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMmcnKeyid(String mmcnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mmcnKeyid;
	}

	public String getMmcnMenuid() {
		return (String) saveArray[ tableFldConstants.menuid.ordinal() ];
	}

	public void setMmcnMenuid(String mmcnMenuid) {
		saveArray[ tableFldConstants.menuid.ordinal() ] = mmcnMenuid;
	}

	public String getMmcnFormname() {
		return (String) saveArray[ tableFldConstants.formname.ordinal() ];
	}

	public void setMmcnFormname(String mmcnFormname) {
		saveArray[ tableFldConstants.formname.ordinal() ] = mmcnFormname;
	}

	public String getMmcnIsmultipletable() {
		return (String) saveArray[ tableFldConstants.ismultipletable.ordinal() ];
	}

	public void setMmcnIsmultipletable(String mmcnIsmultipletable) {
		saveArray[ tableFldConstants.ismultipletable.ordinal() ] = mmcnIsmultipletable;
	}

	public String getMmcnModule() {
		return (String) saveArray[ tableFldConstants.module.ordinal() ];
	}

	public void setMmcnModule(String mmcnModule) {
		saveArray[ tableFldConstants.module.ordinal() ] = mmcnModule;
	}

	public String getMmcnIsfunctionaltable() {
		return (String) saveArray[ tableFldConstants.isfunctionaltable.ordinal() ];
	}

	public void setMmcnIsfunctionaltable(String mmcnIsfunctionaltable) {
		saveArray[ tableFldConstants.isfunctionaltable.ordinal() ] = mmcnIsfunctionaltable;
	}

	public String getMmcnOption() {
		return (String) saveArray[ tableFldConstants.option.ordinal() ];
	}

	public void setMmcnOption(String mmcnOption) {
		saveArray[ tableFldConstants.option.ordinal() ] = mmcnOption;
	}

	public String getMmcnRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMmcnRemarks(String mmcnRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = mmcnRemarks;
	}

	public String getMmcnGroupByField() {
		return (String) saveArray[ tableFldConstants.groupbyfield.ordinal() ];
	}

	public void setMmcnGroupByField(String mmcnGroupByField) {
		saveArray[ tableFldConstants.groupbyfield.ordinal() ] = mmcnGroupByField;
	}

	public String getMmcnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMmcnActive(String mmcnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mmcnActive;
	}

	public String getMmcnCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMmcnCreatedby(String mmcnCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mmcnCreatedby;
	}

	public String getMmcnCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMmcnCreatedon(String mmcnCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mmcnCreatedon;
	}

	public String getMmcnModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMmcnModifiedon(String mmcnModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mmcnModifiedon;
	}

	/**
	 * @param genTlMmcdtl the genTlMmcdtl to set
	 */
	public void setGenTlMmcdtl(List<GenTlMmcdtl> genTlMmcdtl) {
		this.genTlMmcdtl = genTlMmcdtl;
	}

	/**
	 * @return the genTlMmcdtl
	 */
	public List<GenTlMmcdtl> getGenTlMmcdtl() {
		return genTlMmcdtl;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

}

