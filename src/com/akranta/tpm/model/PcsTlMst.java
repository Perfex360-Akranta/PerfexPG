package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlMst {

	private  Object [] saveArray = null;
	private List<PcsTlDtl> pcsDetail ;
	private List<PcsTlLossreasonlink> pcsLosslink ;
	
	
	public enum   tableFldConstants
	{
		keyid, date, factoryid, sectionid, cellid, machineid, shiftid
		, entrydate, entryby, updatedby, updateddate, approvedby, approvedate
		, apporvedflag, shiftincharge, subgroupid, isnoplan, logtype
		, timeorqty, completedflag, tempfield2, flid, elementid, active, createdby, createdon
		, modifiedon
	}

	public PcsTlMst()
	{
		setPcsDetail(new ArrayList<PcsTlDtl> ());		
		setPcslosslink(new ArrayList<PcsTlLossreasonlink> ());
		saveArray = new  Object [ 50 ];
	}


	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public List<PcsTlDtl> getPcsDetail() {
		return pcsDetail;
	}

	public void setPcsDetail(List<PcsTlDtl> pcsDetail) {
		this.pcsDetail= pcsDetail;
	}	

	public List<PcsTlLossreasonlink> getPcslosslink() {
		return pcsLosslink;
	}

	public void setPcslosslink(List<PcsTlLossreasonlink> pcsLosslink) {
		this.pcsLosslink= pcsLosslink;
	}	

	public String getPrlmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPrlmKeyid(String prlmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = prlmKeyid;
	}

	public String getPrlmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPrlmDate(String prlmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = prlmDate;
	}

	public String getPrlmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setPrlmFactoryid(String prlmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = prlmFactoryid;
	}

	public String getPrlmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setPrlmSectionid(String prlmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = prlmSectionid;
	}

	public String getPrlmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setPrlmCellid(String prlmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = prlmCellid;
	}

	public String getPrlmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setPrlmMachineid(String prlmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = prlmMachineid;
	}

	public String getPrlmShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setPrlmShiftid(String prlmShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = prlmShiftid;
	}

	public String getPrlmEntrydate() {
		return (String) saveArray[ tableFldConstants.entrydate.ordinal() ];
	}

	public void setPrlmEntrydate(String prlmEntrydate) {
		saveArray[ tableFldConstants.entrydate.ordinal() ] = prlmEntrydate;
	}

	public String getPrlmEntryby() {
		return (String) saveArray[ tableFldConstants.entryby.ordinal() ];
	}

	public void setPrlmEntryby(String prlmEntryby) {
		saveArray[ tableFldConstants.entryby.ordinal() ] = prlmEntryby;
	}

	public String getPrlmUpdatedby() {
		return (String) saveArray[ tableFldConstants.updatedby.ordinal() ];
	}

	public void setPrlmUpdatedby(String prlmUpdatedby) {
		saveArray[ tableFldConstants.updatedby.ordinal() ] = prlmUpdatedby;
	}

	public String getPrlmUpdateddate() {
		return (String) saveArray[ tableFldConstants.updateddate.ordinal() ];
	}

	public void setPrlmUpdateddate(String prlmUpdateddate) {
		saveArray[ tableFldConstants.updateddate.ordinal() ] = prlmUpdateddate;
	}

	public String getPrlmApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setPrlmApprovedby(String prlmApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = prlmApprovedby;
	}

	public String getPrlmApprovedate() {
		return (String) saveArray[ tableFldConstants.approvedate.ordinal() ];
	}

	public void setPrlmApprovedate(String prlmApprovedate) {
		saveArray[ tableFldConstants.approvedate.ordinal() ] = prlmApprovedate;
	}

	public String getPrlmApporvedflag() {
		return (String) saveArray[ tableFldConstants.apporvedflag.ordinal() ];
	}

	public void setPrlmApporvedflag(String prlmApporvedflag) {
		saveArray[ tableFldConstants.apporvedflag.ordinal() ] = prlmApporvedflag;
	}

	public String getPrlmShiftincharge() {
		return (String) saveArray[ tableFldConstants.shiftincharge.ordinal() ];
	}

	public void setPrlmShiftincharge(String prlmShiftincharge) {
		saveArray[ tableFldConstants.shiftincharge.ordinal() ] = prlmShiftincharge;
	}

	public String getPrlmSubgroupid() {
		return (String) saveArray[ tableFldConstants.subgroupid.ordinal() ];
	}

	public void setPrlmSubgroupid(String prlmSubgroupid) {
		saveArray[ tableFldConstants.subgroupid.ordinal() ] = prlmSubgroupid;
	}

	public String getPrlmIsnoplan() {
		return (String) saveArray[ tableFldConstants.isnoplan.ordinal() ];
	}

	public void setPrlmIsnoplan(String prlmIsnoplan) {
		saveArray[ tableFldConstants.isnoplan.ordinal() ] = prlmIsnoplan;
	}

	public String getPrlmLogtype() {
		return (String) saveArray[ tableFldConstants.logtype.ordinal() ];
	}

	public void setPrlmLogtype(String prlmLogtype) {
		saveArray[ tableFldConstants.logtype.ordinal() ] = prlmLogtype;
	}

	public String getPrlmTimeorqty() {
		return (String) saveArray[ tableFldConstants.timeorqty.ordinal() ];
	}

	public void setPrlmTimeorqty(String prlmTimeorqty) {
		saveArray[ tableFldConstants.timeorqty.ordinal() ] = prlmTimeorqty;
	}

	public String getPrlmCompletedflag() {
		return (String) saveArray[ tableFldConstants.completedflag.ordinal() ];
	}

	public void setPrlmCompletedflag(String PrlmCompletedflag) {
		saveArray[ tableFldConstants.completedflag.ordinal() ] = PrlmCompletedflag;
	}

	public String getPrlmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPrlmTempfield2(String prlmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = prlmTempfield2;
	}


	public String getPrlmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPrlmFlid(String prlmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = prlmFlid;
	}

	public String getPrlmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setPrlmElementid(String prlmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = prlmElementid;
	}

	public String getPrlmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPrlmActive(String prlmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = prlmActive;
	}

	public String getPrlmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPrlmCreatedby(String prlmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = prlmCreatedby;
	}

	public String getPrlmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPrlmCreatedon(String prlmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = prlmCreatedon;
	}

	public String getPrlmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPrlmModifiedon(String prlmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = prlmModifiedon;
	}

}

