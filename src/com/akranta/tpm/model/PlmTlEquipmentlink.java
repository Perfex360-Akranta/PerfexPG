package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlEquipmentlink {

	private  Object [] saveArray = null;  
	private String flg;
	private List<PlmTlEquipmentlink> PmEqpList;

	public enum   tableFldConstants
	{
		keyid, eqpkeyid, pmtm_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public PlmTlEquipmentlink()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEqplKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEqplKeyid(String eqplKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = eqplKeyid;
	}

	public String getEqplEqpkeyid() {
		return (String) saveArray[ tableFldConstants.eqpkeyid.ordinal() ];
	}

	public void setEqplEqpkeyid(String eqplEqpkeyid) {
		saveArray[ tableFldConstants.eqpkeyid.ordinal() ] = eqplEqpkeyid;
	}

	public String getEqplPmtmKeyid() {
		return (String) saveArray[ tableFldConstants.pmtm_keyid.ordinal() ];
	}

	public void setEqplPmtmKeyid(String eqplPmtmKeyid) {
		saveArray[ tableFldConstants.pmtm_keyid.ordinal() ] = eqplPmtmKeyid;
	}

	public String getEqplTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEqplTempfield1(String eqplTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = eqplTempfield1;
	}

	public String getEqplTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEqplTempfield2(String eqplTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = eqplTempfield2;
	}

	public String getEqplTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEqplTempfield3(String eqplTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = eqplTempfield3;
	}

	public String getEqplTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEqplTempfield4(String eqplTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = eqplTempfield4;
	}

	public String getEqplActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEqplActive(String eqplActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = eqplActive;
	}

	public String getEqplCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEqplCreatedby(String eqplCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = eqplCreatedby;
	}

	public String getEqplCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEqplCreatedon(String eqplCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = eqplCreatedon;
	}

	public String getEqplModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEqplModifiedon(String eqplModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = eqplModifiedon;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public String getFlg() {
		return flg;
	}

	public void setPmEqpList(List<PlmTlEquipmentlink> pmEqpList) {
		PmEqpList = pmEqpList;
	}

	public List<PlmTlEquipmentlink> getPmEqpList() {
		return PmEqpList;
	}

}

