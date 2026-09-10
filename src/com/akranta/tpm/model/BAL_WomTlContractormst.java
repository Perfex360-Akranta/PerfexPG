package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlContractormst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, vendorid, unskilledgradeid, effectivefromdate, effectivetodate
		, costperhour, otcost, holidaycost, type, tempfield1, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedon
	}

	public BAL_WomTlContractormst()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getCncsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCncsKeyid(String cncsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cncsKeyid;
	}

	public String getCncsVendorid() {
		return (String) saveArray[ tableFldConstants.vendorid.ordinal() ];
	}

	public void setCncsVendorid(String cncsVendorid) {
		saveArray[ tableFldConstants.vendorid.ordinal() ] = cncsVendorid;
	}

	public String getCncsUnskilledgradeid() {
		return (String) saveArray[ tableFldConstants.unskilledgradeid.ordinal() ];
	}

	public void setCncsUnskilledgradeid(String cncsUnskilledgradeid) {
		saveArray[ tableFldConstants.unskilledgradeid.ordinal() ] = cncsUnskilledgradeid;
	}

	public String getCncsEffectivefromdate() {
		return (String) saveArray[ tableFldConstants.effectivefromdate.ordinal() ];
	}

	public void setCncsEffectivefromdate(String cncsEffectivefromdate) {
		saveArray[ tableFldConstants.effectivefromdate.ordinal() ] = cncsEffectivefromdate;
	}

	public String getCncsEffectivetodate() {
		return (String) saveArray[ tableFldConstants.effectivetodate.ordinal() ];
	}

	public void setCncsEffectivetodate(String cncsEffectivetodate) {
		saveArray[ tableFldConstants.effectivetodate.ordinal() ] = cncsEffectivetodate;
	}

	public String getCncsCostperhour() {
		return (String) saveArray[ tableFldConstants.costperhour.ordinal() ];
	}

	public void setCncsCostperhour(String cncsCostperhour) {
		saveArray[ tableFldConstants.costperhour.ordinal() ] = cncsCostperhour;
	}

	public String getCncsOtcost() {
		return (String) saveArray[ tableFldConstants.otcost.ordinal() ];
	}

	public void setCncsOtcost(String cncsOtcost) {
		saveArray[ tableFldConstants.otcost.ordinal() ] = cncsOtcost;
	}

	public String getCncsHolidaycost() {
		return (String) saveArray[ tableFldConstants.holidaycost.ordinal() ];
	}

	public void setCncsHolidaycost(String cncsHolidaycost) {
		saveArray[ tableFldConstants.holidaycost.ordinal() ] = cncsHolidaycost;
	}

	public String getCncsType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setCncsType(String cncsType) {
		saveArray[ tableFldConstants.type.ordinal() ] = cncsType;
	}

	public String getCncsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCncsTempfield1(String cncsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = cncsTempfield1;
	}

	public String getCncsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCncsTempfield2(String cncsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cncsTempfield2;
	}

	public String getCncsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCncsTempfield3(String cncsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cncsTempfield3;
	}

	public String getCncsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCncsTempfield4(String cncsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = cncsTempfield4;
	}

	public String getCncsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCncsActive(String cncsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cncsActive;
	}

	public String getCncsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCncsCreatedby(String cncsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cncsCreatedby;
	}

	public String getCncsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCncsCreatedon(String cncsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cncsCreatedon;
	}

	public String getCncsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCncsModifiedon(String cncsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cncsModifiedon;
	}

}

