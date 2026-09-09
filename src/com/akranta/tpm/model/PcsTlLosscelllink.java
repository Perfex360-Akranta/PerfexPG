package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlLosscelllink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, cellid, parameterid, effectivedate, inactivedate, tempfield1
		, tempfield2, active, createdby, createdon, modifiedon
	}

	public PcsTlLosscelllink()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPlflKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPlflKeyid(String plflKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = plflKeyid;
	}

	public String getPlflCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setPlflCellid(String plflCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = plflCellid;
	}

	public String getPlflParameterid() {
		return (String) saveArray[ tableFldConstants.parameterid.ordinal() ];
	}

	public void setPlflParameterid(String plflParameterid) {
		saveArray[ tableFldConstants.parameterid.ordinal() ] = plflParameterid;
	}

	public String getPlflEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setPlflEffectivedate(String plflEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = plflEffectivedate;
	}

	public String getPlflInactivedate() {
		return (String) saveArray[ tableFldConstants.inactivedate.ordinal() ];
	}

	public void setPlflInactivedate(String plflInactivedate) {
		saveArray[ tableFldConstants.inactivedate.ordinal() ] = plflInactivedate;
	}

	public String getPlflTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPlflTempfield1(String plflTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = plflTempfield1;
	}

	public String getPlflTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPlflTempfield2(String plflTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = plflTempfield2;
	}

	public String getPlflActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPlflActive(String plflActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = plflActive;
	}

	public String getPlflCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPlflCreatedby(String plflCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = plflCreatedby;
	}

	public String getPlflCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPlflCreatedon(String plflCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = plflCreatedon;
	}

	public String getPlflModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPlflModifiedon(String plflModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = plflModifiedon;
	}

}

