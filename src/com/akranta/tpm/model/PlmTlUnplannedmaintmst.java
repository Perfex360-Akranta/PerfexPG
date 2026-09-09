package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlUnplannedmaintmst {

	private  Object [] saveArray = null;  
	
	private List<PlmTlUnplannedmaintdtl> unplannedDetail ;
	public enum   tableFldConstants
	{
		keyid, entrydate, shiftid, factoryid, sectionid, cellid, machineid
		, assemblyid, partlocationid, alarmdescription, bdtype, reporteddate
		, receiveddate, wostarttime, woendtime, breaktime, actualworktime
		, downtime, prodaccepdate, bookedphenomena, phenomenadescription
		, bookedcause, finalphenomena, finalcause, bookedtrade, finaltrade
		, problemdescription, isbdlocked, shiftincharge, status, bookedby
		, remarks, bookingtype, bdrelatedto, wno, spareid, priority, woallottedflag
		, wostartflag, woendflag, woprodaccepflag, subassemblyid, repeatedbdflag
		, repeatedbdno, relatedto, mould, active, createdby, createdon
		, modifiedon
	}

	public PlmTlUnplannedmaintmst()
	{
		setUnplannedDetail(new ArrayList<PlmTlUnplannedmaintdtl> ());
		saveArray = new  Object [ 50 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public List<PlmTlUnplannedmaintdtl> getUnplannedDetail() {
		return unplannedDetail;
	}

	public void setUnplannedDetail(List<PlmTlUnplannedmaintdtl> unplannedDetail) {
		this.unplannedDetail = unplannedDetail;
	}

	public String getUpmmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setUpmmKeyid(String upmmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = upmmKeyid;
	}

	public String getUpmmEntrydate() {
		return (String) saveArray[ tableFldConstants.entrydate.ordinal() ];
	}

	public void setUpmmEntrydate(String upmmEntrydate) {
		saveArray[ tableFldConstants.entrydate.ordinal() ] = upmmEntrydate;
	}

	public String getUpmmShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setUpmmShiftid(String upmmShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = upmmShiftid;
	}

	public String getUpmmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setUpmmFactoryid(String upmmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = upmmFactoryid;
	}

	public String getUpmmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setUpmmSectionid(String upmmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = upmmSectionid;
	}

	public String getUpmmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setUpmmCellid(String upmmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = upmmCellid;
	}

	public String getUpmmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setUpmmMachineid(String upmmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = upmmMachineid;
	}

	public String getUpmmAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setUpmmAssemblyid(String upmmAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = upmmAssemblyid;
	}

	public String getUpmmPartlocationid() {
		return (String) saveArray[ tableFldConstants.partlocationid.ordinal() ];
	}

	public void setUpmmPartlocationid(String upmmPartlocationid) {
		saveArray[ tableFldConstants.partlocationid.ordinal() ] = upmmPartlocationid;
	}

	public String getUpmmAlarmdescription() {
		return (String) saveArray[ tableFldConstants.alarmdescription.ordinal() ];
	}

	public void setUpmmAlarmdescription(String upmmAlarmdescription) {
		saveArray[ tableFldConstants.alarmdescription.ordinal() ] = upmmAlarmdescription;
	}

	public String getUpmmBdtype() {
		return (String) saveArray[ tableFldConstants.bdtype.ordinal() ];
	}

	public void setUpmmBdtype(String upmmBdtype) {
		saveArray[ tableFldConstants.bdtype.ordinal() ] = upmmBdtype;
	}

	public String getUpmmReporteddate() {
		return (String) saveArray[ tableFldConstants.reporteddate.ordinal() ];
	}

	public void setUpmmReporteddate(String upmmReporteddate) {
		saveArray[ tableFldConstants.reporteddate.ordinal() ] = upmmReporteddate;
	}

	public String getUpmmReceiveddate() {
		return (String) saveArray[ tableFldConstants.receiveddate.ordinal() ];
	}

	public void setUpmmReceiveddate(String upmmReceiveddate) {
		saveArray[ tableFldConstants.receiveddate.ordinal() ] = upmmReceiveddate;
	}

	public String getUpmmWostarttime() {
		return (String) saveArray[ tableFldConstants.wostarttime.ordinal() ];
	}

	public void setUpmmWostarttime(String upmmWostarttime) {
		saveArray[ tableFldConstants.wostarttime.ordinal() ] = upmmWostarttime;
	}

	public String getUpmmWoendtime() {
		return (String) saveArray[ tableFldConstants.woendtime.ordinal() ];
	}

	public void setUpmmWoendtime(String upmmWoendtime) {
		saveArray[ tableFldConstants.woendtime.ordinal() ] = upmmWoendtime;
	}

	public String getUpmmBreaktime() {
		return (String) saveArray[ tableFldConstants.breaktime.ordinal() ];
	}

	public void setUpmmBreaktime(String upmmBreaktime) {
		saveArray[ tableFldConstants.breaktime.ordinal() ] = upmmBreaktime;
	}

	public String getUpmmActualworktime() {
		return (String) saveArray[ tableFldConstants.actualworktime.ordinal() ];
	}

	public void setUpmmActualworktime(String upmmActualworktime) {
		saveArray[ tableFldConstants.actualworktime.ordinal() ] = upmmActualworktime;
	}

	public String getUpmmDowntime() {
		return (String) saveArray[ tableFldConstants.downtime.ordinal() ];
	}

	public void setUpmmDowntime(String upmmDowntime) {
		saveArray[ tableFldConstants.downtime.ordinal() ] = upmmDowntime;
	}

	public String getUpmmProdaccepdate() {
		return (String) saveArray[ tableFldConstants.prodaccepdate.ordinal() ];
	}

	public void setUpmmProdaccepdate(String upmmProdaccepdate) {
		saveArray[ tableFldConstants.prodaccepdate.ordinal() ] = upmmProdaccepdate;
	}

	public String getUpmmBookedphenomena() {
		return (String) saveArray[ tableFldConstants.bookedphenomena.ordinal() ];
	}

	public void setUpmmBookedphenomena(String upmmBookedphenomena) {
		saveArray[ tableFldConstants.bookedphenomena.ordinal() ] = upmmBookedphenomena;
	}

	public String getUpmmPhenomenadescription() {
		return (String) saveArray[ tableFldConstants.phenomenadescription.ordinal() ];
	}

	public void setUpmmPhenomenadescription(String upmmPhenomenadescription) {
		saveArray[ tableFldConstants.phenomenadescription.ordinal() ] = upmmPhenomenadescription;
	}

	public String getUpmmBookedcause() {
		return (String) saveArray[ tableFldConstants.bookedcause.ordinal() ];
	}

	public void setUpmmBookedcause(String upmmBookedcause) {
		saveArray[ tableFldConstants.bookedcause.ordinal() ] = upmmBookedcause;
	}

	public String getUpmmFinalphenomena() {
		return (String) saveArray[ tableFldConstants.finalphenomena.ordinal() ];
	}

	public void setUpmmFinalphenomena(String upmmFinalphenomena) {
		saveArray[ tableFldConstants.finalphenomena.ordinal() ] = upmmFinalphenomena;
	}

	public String getUpmmFinalcause() {
		return (String) saveArray[ tableFldConstants.finalcause.ordinal() ];
	}

	public void setUpmmFinalcause(String upmmFinalcause) {
		saveArray[ tableFldConstants.finalcause.ordinal() ] = upmmFinalcause;
	}

	public String getUpmmBookedtrade() {
		return (String) saveArray[ tableFldConstants.bookedtrade.ordinal() ];
	}

	public void setUpmmBookedtrade(String upmmBookedtrade) {
		saveArray[ tableFldConstants.bookedtrade.ordinal() ] = upmmBookedtrade;
	}

	public String getUpmmFinaltrade() {
		return (String) saveArray[ tableFldConstants.finaltrade.ordinal() ];
	}

	public void setUpmmFinaltrade(String upmmFinaltrade) {
		saveArray[ tableFldConstants.finaltrade.ordinal() ] = upmmFinaltrade;
	}

	public String getUpmmProblemdescription() {
		return (String) saveArray[ tableFldConstants.problemdescription.ordinal() ];
	}

	public void setUpmmProblemdescription(String upmmProblemdescription) {
		saveArray[ tableFldConstants.problemdescription.ordinal() ] = upmmProblemdescription;
	}

	public String getUpmmIsbdlocked() {
		return (String) saveArray[ tableFldConstants.isbdlocked.ordinal() ];
	}

	public void setUpmmIsbdlocked(String upmmIsbdlocked) {
		saveArray[ tableFldConstants.isbdlocked.ordinal() ] = upmmIsbdlocked;
	}

	public String getUpmmShiftincharge() {
		return (String) saveArray[ tableFldConstants.shiftincharge.ordinal() ];
	}

	public void setUpmmShiftincharge(String upmmShiftincharge) {
		saveArray[ tableFldConstants.shiftincharge.ordinal() ] = upmmShiftincharge;
	}

	public String getUpmmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setUpmmStatus(String upmmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = upmmStatus;
	}

	public String getUpmmBookedby() {
		return (String) saveArray[ tableFldConstants.bookedby.ordinal() ];
	}

	public void setUpmmBookedby(String upmmBookedby) {
		saveArray[ tableFldConstants.bookedby.ordinal() ] = upmmBookedby;
	}

	public String getUpmmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setUpmmRemarks(String upmmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = upmmRemarks;
	}

	public String getUpmmBookingtype() {
		return (String) saveArray[ tableFldConstants.bookingtype.ordinal() ];
	}

	public void setUpmmBookingtype(String upmmBookingtype) {
		saveArray[ tableFldConstants.bookingtype.ordinal() ] = upmmBookingtype;
	}

	public String getUpmmBdrelatedto() {
		return (String) saveArray[ tableFldConstants.bdrelatedto.ordinal() ];
	}

	public void setUpmmBdrelatedto(String upmmBdrelatedto) {
		saveArray[ tableFldConstants.bdrelatedto.ordinal() ] = upmmBdrelatedto;
	}

	public String getUpmmWno() {
		return (String) saveArray[ tableFldConstants.wno.ordinal() ];
	}

	public void setUpmmWno(String upmmWno) {
		saveArray[ tableFldConstants.wno.ordinal() ] = upmmWno;
	}

	public String getUpmmSpareid() {
		return (String) saveArray[ tableFldConstants.spareid.ordinal() ];
	}

	public void setUpmmSpareid(String upmmSpareid) {
		saveArray[ tableFldConstants.spareid.ordinal() ] = upmmSpareid;
	}

	public String getUpmmPriority() {
		return (String) saveArray[ tableFldConstants.priority.ordinal() ];
	}

	public void setUpmmPriority(String upmmPriority) {
		saveArray[ tableFldConstants.priority.ordinal() ] = upmmPriority;
	}

	public String getUpmmWoallottedflag() {
		return (String) saveArray[ tableFldConstants.woallottedflag.ordinal() ];
	}

	public void setUpmmWoallottedflag(String upmmWoallottedflag) {
		saveArray[ tableFldConstants.woallottedflag.ordinal() ] = upmmWoallottedflag;
	}

	public String getUpmmWostartflag() {
		return (String) saveArray[ tableFldConstants.wostartflag.ordinal() ];
	}

	public void setUpmmWostartflag(String upmmWostartflag) {
		saveArray[ tableFldConstants.wostartflag.ordinal() ] = upmmWostartflag;
	}

	public String getUpmmWoendflag() {
		return (String) saveArray[ tableFldConstants.woendflag.ordinal() ];
	}

	public void setUpmmWoendflag(String upmmWoendflag) {
		saveArray[ tableFldConstants.woendflag.ordinal() ] = upmmWoendflag;
	}

	public String getUpmmWoprodaccepflag() {
		return (String) saveArray[ tableFldConstants.woprodaccepflag.ordinal() ];
	}

	public void setUpmmWoprodaccepflag(String upmmWoprodaccepflag) {
		saveArray[ tableFldConstants.woprodaccepflag.ordinal() ] = upmmWoprodaccepflag;
	}

	public String getUpmmSubassemblyid() {
		return (String) saveArray[ tableFldConstants.subassemblyid.ordinal() ];
	}

	public void setUpmmSubassemblyid(String upmmSubassemblyid) {
		saveArray[ tableFldConstants.subassemblyid.ordinal() ] = upmmSubassemblyid;
	}

	public String getUpmmRepeatedbdflag() {
		return (String) saveArray[ tableFldConstants.repeatedbdflag.ordinal() ];
	}

	public void setUpmmRepeatedbdflag(String upmmRepeatedbdflag) {
		saveArray[ tableFldConstants.repeatedbdflag.ordinal() ] = upmmRepeatedbdflag;
	}

	public String getUpmmRepeatedbdno() {
		return (String) saveArray[ tableFldConstants.repeatedbdno.ordinal() ];
	}

	public void setUpmmRepeatedbdno(String upmmRepeatedbdno) {
		saveArray[ tableFldConstants.repeatedbdno.ordinal() ] = upmmRepeatedbdno;
	}

	public String getUpmmRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setUpmmRelatedto(String upmmRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = upmmRelatedto;
	}

	public String getUpmmMould() {
		return (String) saveArray[ tableFldConstants.mould.ordinal() ];
	}

	public void setUpmmMould(String upmmMould) {
		saveArray[ tableFldConstants.mould.ordinal() ] = upmmMould;
	}

	public String getUpmmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setUpmmActive(String upmmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = upmmActive;
	}

	public String getUpmmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setUpmmCreatedby(String upmmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = upmmCreatedby;
	}

	public String getUpmmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setUpmmCreatedon(String upmmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = upmmCreatedon;
	}

	public String getUpmmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setUpmmModifiedon(String upmmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = upmmModifiedon;
	}

}

