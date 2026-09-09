package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class QtmTlTestscrapbrkup {

	private  Object [] saveArray = null;  	
	

	private List<QtmTlTestscrapbrkup> qtmTlTestscrapbrkup;

	public enum   tableFldConstants
	{
		rejectionid, masterid, quantity, remarks, backlogflag, referencekeyid
		, active, createdby, createdon, modifiedon
	}

	public QtmTlTestscrapbrkup()
	{
		saveArray = new  Object [ 10 ];
		setQtmTlTestscrapbrkup(new ArrayList<QtmTlTestscrapbrkup> ());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public List<QtmTlTestscrapbrkup> getQtmTlTestscrapbrkup() {
		return qtmTlTestscrapbrkup;
	}

	public void setQtmTlTestscrapbrkup(List<QtmTlTestscrapbrkup> qtmTlTestscrapbrkup) {
		this.qtmTlTestscrapbrkup = qtmTlTestscrapbrkup;
	}
	public String getQsbrRejectionid() {
		return (String) saveArray[ tableFldConstants.rejectionid.ordinal() ];
	}

	public void setQsbrRejectionid(String qsbrRejectionid) {
		saveArray[ tableFldConstants.rejectionid.ordinal() ] = qsbrRejectionid;
	}

	public String getQsbrMasterid() {
		return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
	}

	public void setQsbrMasterid(String qsbrMasterid) {
		saveArray[ tableFldConstants.masterid.ordinal() ] = qsbrMasterid;
	}

	public String getQsbrQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setQsbrQuantity(String qsbrQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = qsbrQuantity;
	}

	public String getQsbrRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setQsbrRemarks(String qsbrRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = qsbrRemarks;
	}

	public String getQsbrBacklogflag() {
		return (String) saveArray[ tableFldConstants.backlogflag.ordinal() ];
	}

	public void setQsbrBacklogflag(String qsbrBacklogflag) {
		saveArray[ tableFldConstants.backlogflag.ordinal() ] = qsbrBacklogflag;
	}

	public String getQsbrReferencekeyid() {
		return (String) saveArray[ tableFldConstants.referencekeyid.ordinal() ];
	}

	public void setQsbrReferencekeyid(String qsbrReferencekeyid) {
		saveArray[ tableFldConstants.referencekeyid.ordinal() ] = qsbrReferencekeyid;
	}

	public String getQsbrActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQsbrActive(String qsbrActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qsbrActive;
	}

	public String getQsbrCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQsbrCreatedby(String qsbrCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qsbrCreatedby;
	}

	public String getQsbrCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQsbrCreatedon(String qsbrCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qsbrCreatedon;
	}

	public String getQsbrModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQsbrModifiedon(String qsbrModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = qsbrModifiedon;
	}

}

