package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SopTlVisualchecklistdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, vccm_keyid, checkpoints, sortorder, criteria, orderno
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public SopTlVisualchecklistdtl()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getVccdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVccdKeyid(String vccdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = vccdKeyid;
	}

	public String getVccdVccmKeyid() {
		return (String) saveArray[ tableFldConstants.vccm_keyid.ordinal() ];  
	}

	public void setVccdVccmKeyid(String vccdVccmKeyid) {
		saveArray[ tableFldConstants.vccm_keyid.ordinal() ] = vccdVccmKeyid;
	}

	public String getVccdCheckpoints() {
		return (String) saveArray[ tableFldConstants.checkpoints.ordinal() ];
	}

	public void setVccdCheckpoints(String vccdCheckpoints) {
		saveArray[ tableFldConstants.checkpoints.ordinal() ] = vccdCheckpoints;
	}

	public String getVccdSortorder() {
		return (String) saveArray[ tableFldConstants.sortorder.ordinal() ];
	}

	public void setVccdSortorder(String vccdSortorder) {
		saveArray[ tableFldConstants.sortorder.ordinal() ] = vccdSortorder;
	}

	public String getVccdCriteria() {
		return (String) saveArray[ tableFldConstants.criteria.ordinal() ];
	}

	public void setVccdCriteria(String vccdCriteria) {
		saveArray[ tableFldConstants.criteria.ordinal() ] = vccdCriteria;
	}

	public String getVccdOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setVccdOrderno(String vccdOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = vccdOrderno;
	}

	public String getVccdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setVccdTempfield3(String vccdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = vccdTempfield3;
	}

	public String getVccdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setVccdTempfield4(String vccdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = vccdTempfield4;
	}

	public String getVccdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setVccdTempfield5(String vccdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = vccdTempfield5;
	}

	public String getVccdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVccdCreatedby(String vccdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = vccdCreatedby;
	}

	public String getVccdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVccdActive(String vccdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = vccdActive;
	}

	public String getVccdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVccdCreatedon(String vccdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = vccdCreatedon;
	}

	public String getVccdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVccdModifiedon(String vccdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = vccdModifiedon;
	}

}

