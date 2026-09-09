package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlEnergyconsumptiondtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, encm_keyid, machineid, productid, standardwt, exppowercons
		, actpowercons, expproduction, noofpiecesrejected, active, createdby
		, createdon, modifiedon
	}

	public PcsTlEnergyconsumptiondtl()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEncdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEncdKeyid(String encdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = encdKeyid;
	}

	public String getEncdEncmKeyid() {
		return (String) saveArray[ tableFldConstants.encm_keyid.ordinal() ];
	}

	public void setEncdEncmKeyid(String encdEncmKeyid) {
		saveArray[ tableFldConstants.encm_keyid.ordinal() ] = encdEncmKeyid;
	}

	public String getEncdMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setEncdMachineid(String encdMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = encdMachineid;
	}

	public String getEncdProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setEncdProductid(String encdProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = encdProductid;
	}

	public String getEncdStandardwt() {
		return (String) saveArray[ tableFldConstants.standardwt.ordinal() ];
	}

	public void setEncdStandardwt(String encdStandardwt) {
		saveArray[ tableFldConstants.standardwt.ordinal() ] = encdStandardwt;
	}

	public String getEncdExppowercons() {
		return (String) saveArray[ tableFldConstants.exppowercons.ordinal() ];
	}

	public void setEncdExppowercons(String encdExppowercons) {
		saveArray[ tableFldConstants.exppowercons.ordinal() ] = encdExppowercons;
	}

	public String getEncdActpowercons() {
		return (String) saveArray[ tableFldConstants.actpowercons.ordinal() ];
	}

	public void setEncdActpowercons(String encdActpowercons) {
		saveArray[ tableFldConstants.actpowercons.ordinal() ] = encdActpowercons;
	}

	public String getEncdExpproduction() {
		return (String) saveArray[ tableFldConstants.expproduction.ordinal() ];
	}

	public void setEncdExpproduction(String encdExpproduction) {
		saveArray[ tableFldConstants.expproduction.ordinal() ] = encdExpproduction;
	}

	public String getEncdNoofpiecesrejected() {
		return (String) saveArray[ tableFldConstants.noofpiecesrejected.ordinal() ];
	}

	public void setEncdNoofpiecesrejected(String encdNoofpiecesrejected) {
		saveArray[ tableFldConstants.noofpiecesrejected.ordinal() ] = encdNoofpiecesrejected;
	}

	public String getEncdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEncdActive(String encdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = encdActive;
	}

	public String getEncdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEncdCreatedby(String encdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = encdCreatedby;
	}

	public String getEncdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEncdCreatedon(String encdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = encdCreatedon;
	}

	public String getEncdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEncdModifiedon(String encdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = encdModifiedon;
	}

}

