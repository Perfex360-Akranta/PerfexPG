package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;

public class BAL_GenTlShiftmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, code, duration, breaktime, shiftorder, starttime
		, endtime, description, effectivedate, inactivedate, factoryid
		, sectionid, cellid,elementid, flid,active, createdby, createdon, modifiedon
	}

	public BAL_GenTlShiftmst()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}


	public String getSftmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSftmKeyid(String sftmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sftmKeyid;
	}

	public String getSftmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setSftmName(String sftmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = sftmName;
	}

	public String getSftmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setSftmCode(String sftmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = sftmCode;
	}

	public String getSftmDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setSftmDuration(String sftmDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = sftmDuration;
	}

	public String getSftmBreaktime() {
		return (String) saveArray[ tableFldConstants.breaktime.ordinal() ];
	}

	public void setSftmBreaktime(String sftmBreaktime) {
		saveArray[ tableFldConstants.breaktime.ordinal() ] = sftmBreaktime;
	}

	public String getSftmShiftorder() {
		return (String) saveArray[ tableFldConstants.shiftorder.ordinal() ];
	}
	

	public void setSftmShiftorder(String sftmShiftorder) {
		saveArray[ tableFldConstants.shiftorder.ordinal() ] = sftmShiftorder;
	}

	public String getSftmStarttime() {
		return (String) saveArray[ tableFldConstants.starttime.ordinal() ];
	}

	public void setSftmStarttime(String sftmStarttime) {
		saveArray[ tableFldConstants.starttime.ordinal() ] = sftmStarttime;
	}

	public String getSftmEndtime() {
		return (String) saveArray[ tableFldConstants.endtime.ordinal() ];
	}

	public void setSftmEndtime(String sftmEndtime) {
		saveArray[ tableFldConstants.endtime.ordinal() ] = sftmEndtime;
	}

	public String getSftmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setSftmDescription(String sftmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = sftmDescription;
	}

	public String getSftmEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setSftmEffectivedate(String sftmEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = sftmEffectivedate;
	}

	public String getSftmInactivedate() {
		return (String) saveArray[ tableFldConstants.inactivedate.ordinal() ];
	}

	public void setSftmInactivedate(String sftmInactivedate) {
		saveArray[ tableFldConstants.inactivedate.ordinal() ] = sftmInactivedate;
	}

	public String getSftmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setSftmFactoryid(String sftmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = sftmFactoryid;
	}

	public String getSftmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setSftmSectionid(String sftmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = sftmSectionid;
	}

	public String getSftmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setSftmCellid(String sftmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = sftmCellid;
	}
	
	public String getSftmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setSftmElementid(String sftmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = sftmElementid;
	}
	
	
	public String getSftmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSftmFlid(String sftmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = sftmFlid;
	}

	public String getSftmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSftmActive(String sftmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sftmActive;
	}

	public String getSftmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSftmCreatedby(String sftmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sftmCreatedby;
	}

	public String getSftmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSftmCreatedon(String sftmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sftmCreatedon;
	}

	public String getSftmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSftmModifiedon(String sftmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sftmModifiedon;
	}



	

}

