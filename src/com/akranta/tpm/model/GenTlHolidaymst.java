package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;

public class GenTlHolidaymst {

	private  Object [] saveArray = null;  
	public static final String TBL_GEN_TL_HOLIDAYMST = "GEN_TL_HOLIDAYMST";  
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	public enum   tableFldConstants
	{
		keyid, name, date, description, factory, holidayflag, createdby
		, createdon, modifiedon
	}


	
	public GenTlHolidaymst()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	
	public String getHolmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setHolmKeyid(String holmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = holmKeyid;
	}

	public String getHolmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setHolmName(String holmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = holmName;
	}

	public String getHolmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setHolmDate(String holmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = holmDate;
	}

	public String getHolmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setHolmDescription(String holmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = holmDescription;
	}

	public String getHolmFactory() {
		return (String) saveArray[ tableFldConstants.factory.ordinal() ];
	}

	public void setHolmFactory(String holmFactory) {
		saveArray[ tableFldConstants.factory.ordinal() ] = holmFactory;
	}

	public String getHolmHolidayflag() {
		return (String) saveArray[ tableFldConstants.holidayflag.ordinal() ];
	}

	public void setHolmHolidayflag(String holmHolidayflag) {
		saveArray[ tableFldConstants.holidayflag.ordinal() ] = holmHolidayflag;
	}

	public String getHolmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setHolmCreatedby(String holmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = holmCreatedby;
	}

	public String getHolmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setHolmCreatedon(String holmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = holmCreatedon;
	}

	public String getHolmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setHolmModifiedon(String holmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = holmModifiedon;
	}



	
	


	
}

