package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhnTlSlamst {

	private  Object [] saveArray = null;  
	private List<JhnTlSladtl> JhnTlSladtl ;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, source, date, fromdptid, todptid, status , approvedby
		, approveddate, remarks, preparedby , prepareddate
		, frmdmtapproval, todmtapproval, todmtqmapproval
		, active, createdby , createdon, modifiedon
	}

	public JhnTlSlamst()
	{
		setJhnTlSladtl(new ArrayList<JhnTlSladtl> ());
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
		
	}
	public void setJhnTlSladtl(List<JhnTlSladtl> JhnTlSladtl) {
		this.JhnTlSladtl = JhnTlSladtl;
	}

	public List<JhnTlSladtl> getJhnTlSladtl() {
		return JhnTlSladtl;
	}

	public String getSlamKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSlamKeyid(String slamKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = slamKeyid;
	}

	public String getSlamFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSlamFlid(String slamFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = slamFlid;
	}

	public String getSlamElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setSlamElementid(String slamElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = slamElementid;
	}

	public String getSlamSource() {
		return (String) saveArray[ tableFldConstants.source.ordinal() ];
	}

	public void setSlamSource(String slamSource) {
		saveArray[ tableFldConstants.source.ordinal() ] = slamSource;
	}

	public String getSlamDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setSlamDate(String slamDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = slamDate;
	}

	public String getSlamFromdptid() {
		return (String) saveArray[ tableFldConstants.fromdptid.ordinal() ];
	}

	public void setSlamFromdptid(String slamFromdptid) {
		saveArray[ tableFldConstants.fromdptid.ordinal() ] = slamFromdptid;
	}

	public String getSlamTodptid() {
		return (String) saveArray[ tableFldConstants.todptid.ordinal() ];
	}

	public void setSlamTodptid(String slamTodptid) {
		saveArray[ tableFldConstants.todptid.ordinal() ] = slamTodptid;
	}
	
	public String getSlamStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setSlamStatus(String slamStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = slamStatus;
	}


	public String getSlamApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setSlamApprovedby(String slamApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = slamApprovedby;
	}

	public String getSlamApproveddate() {
		return (String) saveArray[ tableFldConstants.approveddate.ordinal() ];
	}

	public void setSlamApproveddate(String slamApproveddate) {
		saveArray[ tableFldConstants.approveddate.ordinal() ] = slamApproveddate;
	}

	public String getSlamRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSlamRemarks(String slamRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = slamRemarks;
	}

	public String getSlamPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}
	
	public void setSlamPreparedby(String slamPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = slamPreparedby;
	}
	
	public String getSlamPrepareddate() {
		return (String) saveArray[ tableFldConstants.prepareddate.ordinal() ];
	}
	
	public void setSlamPrepareddate(String slamPrepareddate) {
		saveArray[ tableFldConstants.prepareddate.ordinal() ] = slamPrepareddate;
	}
		
	public String getSlamFrmdmtapproval() {
		return (String) saveArray[ tableFldConstants.frmdmtapproval.ordinal() ];
	}

	public void setSlamFrmdmtapproval(String slamFrmdmtapproval) {
		saveArray[ tableFldConstants.frmdmtapproval.ordinal() ] = slamFrmdmtapproval;
	}

	public String getSlamTodmtapproval() {
		return (String) saveArray[ tableFldConstants.todmtapproval.ordinal() ];
	}

	public void setSlamTodmtapproval(String slamTodmtapproval) {
		saveArray[ tableFldConstants.todmtapproval.ordinal() ] = slamTodmtapproval;
	}

	public String getSlamTodmtqmapproval() {
		return (String) saveArray[ tableFldConstants.todmtqmapproval.ordinal() ];
	}

	public void setSlamTodmtqmapproval(String slamTodmtqmapproval) {
		saveArray[ tableFldConstants.todmtqmapproval.ordinal() ] = slamTodmtqmapproval;
	}

	public String getSlamActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSlamActive(String slamActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = slamActive;
	}

	public String getSlamCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSlamCreatedby(String slamCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = slamCreatedby;
	}

	public String getSlamCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSlamCreatedon(String slamCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = slamCreatedon;
	}

	public String getSlamModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSlamModifiedon(String slamModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = slamModifiedon;
	}

}

