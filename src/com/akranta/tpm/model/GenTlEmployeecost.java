package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlEmployeecost {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, employeeid, effectivefromdate, effectivetilldate, costperhour
		, otcostperhour, calloutcostperhour, type, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public GenTlEmployeecost()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEmpcKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEmpcKeyid(String empcKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = empcKeyid;
	}

	public String getEmpcEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setEmpcEmployeeid(String empcEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = empcEmployeeid;
	}

	public String getEmpcEffectivefromdate() {
		return (String) saveArray[ tableFldConstants.effectivefromdate.ordinal() ];
	}

	public void setEmpcEffectivefromdate(String empcEffectivefromdate) {
		saveArray[ tableFldConstants.effectivefromdate.ordinal() ] = empcEffectivefromdate;
	}

	public String getEmpcEffectivetilldate() {
		return (String) saveArray[ tableFldConstants.effectivetilldate.ordinal() ];
	}

	public void setEmpcEffectivetilldate(String empcEffectivetilldate) {
		saveArray[ tableFldConstants.effectivetilldate.ordinal() ] = empcEffectivetilldate;
	}

	public String getEmpcCostperhour() {
		return (String) saveArray[ tableFldConstants.costperhour.ordinal() ];
	}

	public void setEmpcCostperhour(String empcCostperhour) {
		saveArray[ tableFldConstants.costperhour.ordinal() ] = empcCostperhour;
	}

	public String getEmpcOtcostperhour() {
		return (String) saveArray[ tableFldConstants.otcostperhour.ordinal() ];
	}

	public void setEmpcOtcostperhour(String empcOtcostperhour) {
		saveArray[ tableFldConstants.otcostperhour.ordinal() ] = empcOtcostperhour;
	}

	public String getEmpcCalloutcostperhour() {
		return (String) saveArray[ tableFldConstants.calloutcostperhour.ordinal() ];
	}

	public void setEmpcCalloutcostperhour(String empcCalloutcostperhour) {
		saveArray[ tableFldConstants.calloutcostperhour.ordinal() ] = empcCalloutcostperhour;
	}

	public String getEmpcType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setEmpcType(String empcType) {
		saveArray[ tableFldConstants.type.ordinal() ] = empcType;
	}

	public String getEmpcTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEmpcTempfield2(String empcTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = empcTempfield2;
	}

	public String getEmpcTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEmpcTempfield3(String empcTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = empcTempfield3;
	}

	public String getEmpcTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEmpcTempfield4(String empcTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = empcTempfield4;
	}

	public String getEmpcTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEmpcTempfield5(String empcTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = empcTempfield5;
	}

	public String getEmpcActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEmpcActive(String empcActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = empcActive;
	}

	public String getEmpcCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEmpcCreatedby(String empcCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = empcCreatedby;
	}

	public String getEmpcCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEmpcCreatedon(String empcCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = empcCreatedon;
	}

	public String getEmpcModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEmpcModifiedon(String empcModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = empcModifiedon;
	}

}

