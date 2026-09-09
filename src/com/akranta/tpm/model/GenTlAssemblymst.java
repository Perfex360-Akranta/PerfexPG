package com.akranta.tpm.model;
/**
 * Author:N Arun
 * Created on:25.11.2011
 */
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;

public class GenTlAssemblymst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, code, name, description, remarks, type,relatedto, active, createdby
		, createdon, modifiedon
	}

	public GenTlAssemblymst()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	public String getAssmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAssmKeyid(String assmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = assmKeyid;
	}

	public String getAssmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setAssmCode(String assmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = assmCode;
	}
	
	public String getAssmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setAssmName(String assmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = assmName;
	}

	public String getAssmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setAssmDescription(String assmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = assmDescription;
	}

	public String getAssmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setAssmRemarks(String assmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = assmRemarks;
	}

	public String getAssmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setAssmType(String assmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = assmType;
	}

	
	public String getAssmRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setAssmRelatedto(String assmRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = assmRelatedto;
	}	
	
	public String getAssmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAssmActive(String assmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = assmActive;
	}

	public String getAssmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAssmCreatedby(String assmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = assmCreatedby;
	}

	public String getAssmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAssmCreatedon(String assmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = assmCreatedon;
	}

	public String getAssmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAssmModifiedon(String assmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = assmModifiedon;
	}
	
	
	
	
 }

