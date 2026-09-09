package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhnTlSladtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, slamid, productid, uomid, mutualtarget, mutualmin, mutualmax
		, qualitycharacter, remarks, adherenceperc, effectivedate, frequency
		, approvalstatus, tempfield5, active, createdby, createdon, modifiedon
		
		
	}

	public JhnTlSladtl()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
		
	}

	public String getSladKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSladKeyid(String sladKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sladKeyid;
	}

	public String getSladSlamid() {
		return (String) saveArray[ tableFldConstants.slamid.ordinal() ];
	}

	public void setSladSlamid(String sladSlamid) {
		saveArray[ tableFldConstants.slamid.ordinal() ] = sladSlamid;
	}

	public String getSladProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setSladProductid(String sladProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = sladProductid;
	}

	public String getSladUomid() {
		return (String) saveArray[ tableFldConstants.uomid.ordinal() ];
	}

	public void setSladUomid(String sladUomid) {
		saveArray[ tableFldConstants.uomid.ordinal() ] = sladUomid;
	}

	public String getSladMutualtarget() {
		return (String) saveArray[ tableFldConstants.mutualtarget.ordinal() ];
	}

	public void setSladMutualtarget(String sladMutualtarget) {
		saveArray[ tableFldConstants.mutualtarget.ordinal() ] = sladMutualtarget;
	}

	public String getSladMutualmin() {
		return (String) saveArray[ tableFldConstants.mutualmin.ordinal() ];
	}

	public void setSladMutualmin(String sladMutualmin) {
		saveArray[ tableFldConstants.mutualmin.ordinal() ] = sladMutualmin;
	}

	public String getSladMutualmax() {
		return (String) saveArray[ tableFldConstants.mutualmax.ordinal() ];
	}

	public void setSladMutualmax(String sladMutualmax) {
		saveArray[ tableFldConstants.mutualmax.ordinal() ] = sladMutualmax;
	}

	public String getSladQualitycharacter() {
		return (String) saveArray[ tableFldConstants.qualitycharacter.ordinal() ];
	}

	public void setSladQualitycharacter(String sladQualitycharacter) {
		saveArray[ tableFldConstants.qualitycharacter.ordinal() ] = sladQualitycharacter;
	}

	public String getSladRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSladRemarks(String sladRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = sladRemarks;
	}

	public String getSladAdherenceperc() {
		return (String) saveArray[ tableFldConstants.adherenceperc.ordinal() ];
	}

	public void setSladAdherenceperc(String sladAdherenceperc) {
		saveArray[ tableFldConstants.adherenceperc.ordinal() ] = sladAdherenceperc;
	}

	public String getSladEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setSladEffectivedate(String sladEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = sladEffectivedate;
	}

	public String getSladFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setSladFrequency(String sladFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = sladFrequency;
	}

	public String getSladApprovalStatus() {
		return (String) saveArray[ tableFldConstants.approvalstatus.ordinal() ];
	}

	public void setSladApprovalStatus(String sladApprovalstatus) {
		saveArray[ tableFldConstants.approvalstatus.ordinal() ] = sladApprovalstatus;
	}

	public String getSladTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSladTempfield5(String sladTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = sladTempfield5;
	}

	public String getSladActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSladActive(String sladActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sladActive;
	}

	public String getSladCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSladCreatedby(String sladCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sladCreatedby;
	}

	public String getSladCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSladCreatedon(String sladCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sladCreatedon;
	}

	public String getSladModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSladModifiedon(String sladModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sladModifiedon;
	}

}

