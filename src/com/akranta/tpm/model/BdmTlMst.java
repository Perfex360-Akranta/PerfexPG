 package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BdmTlMst {

	private  Object [] saveArray = null;  
	private List<BdmTlDtl> bdmDetail ;
	private List<BdmTlShiftwisesplit> bdmShiftwise;
	private String externalserviceId;

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
		, repeatedbdno, relatedto, mould, elementid, flid, processid
		, isstandby, standbyequipment, breakdowntime, productionstop, immediateaction
		, tempfield3, tempfield4, tempfield5, tempfield6, tempfield7
		, active, createdby, createdon, modifiedon
	}

	public BdmTlMst()
	{
		setBdmDetail(new ArrayList<BdmTlDtl> ());
		setBdmShiftwise(new ArrayList<BdmTlShiftwisesplit> ());
		saveArray = new  Object [ 63 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getBdmsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBdmsKeyid(String bdmsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bdmsKeyid;
	}

	public String getBdmsEntrydate() {
		return (String) saveArray[ tableFldConstants.entrydate.ordinal() ];
	}

	public void setBdmsEntrydate(String bdmsEntrydate) {
		saveArray[ tableFldConstants.entrydate.ordinal() ] = bdmsEntrydate;
	}

	public String getBdmsShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setBdmsShiftid(String bdmsShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = bdmsShiftid;
	}

	public String getBdmsFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setBdmsFactoryid(String bdmsFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = bdmsFactoryid;
	}

	public String getBdmsSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setBdmsSectionid(String bdmsSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = bdmsSectionid;
	}

	public String getBdmsCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setBdmsCellid(String bdmsCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = bdmsCellid;
	}

	public String getBdmsMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setBdmsMachineid(String bdmsMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = bdmsMachineid;
	}

	public String getBdmsAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setBdmsAssemblyid(String bdmsAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = bdmsAssemblyid;
	}

	public String getBdmsPartlocationid() {
		return (String) saveArray[ tableFldConstants.partlocationid.ordinal() ];
	}

	public void setBdmsPartlocationid(String bdmsPartlocationid) {
		saveArray[ tableFldConstants.partlocationid.ordinal() ] = bdmsPartlocationid;
	}

	public String getBdmsAlarmdescription() {
		return (String) saveArray[ tableFldConstants.alarmdescription.ordinal() ];
	}

	public void setBdmsAlarmdescription(String bdmsAlarmdescription) {
		saveArray[ tableFldConstants.alarmdescription.ordinal() ] = bdmsAlarmdescription;
	}

	public String getBdmsBdtype() {
		return (String) saveArray[ tableFldConstants.bdtype.ordinal() ];
	}

	public void setBdmsBdtype(String bdmsBdtype) {
		saveArray[ tableFldConstants.bdtype.ordinal() ] = bdmsBdtype;
	}

	public String getBdmsReporteddate() {
		return (String) saveArray[ tableFldConstants.reporteddate.ordinal() ];
	}

	public void setBdmsReporteddate(String bdmsReporteddate) {
		saveArray[ tableFldConstants.reporteddate.ordinal() ] = bdmsReporteddate;
	}

	public String getBdmsReceiveddate() {
		return (String) saveArray[ tableFldConstants.receiveddate.ordinal() ];
	}

	public void setBdmsReceiveddate(String bdmsReceiveddate) {
		saveArray[ tableFldConstants.receiveddate.ordinal() ] = bdmsReceiveddate;
	}

	public String getBdmsWostarttime() {
		return (String) saveArray[ tableFldConstants.wostarttime.ordinal() ];
	}

	public void setBdmsWostarttime(String bdmsWostarttime) {
		saveArray[ tableFldConstants.wostarttime.ordinal() ] = bdmsWostarttime;
	}

	public String getBdmsWoendtime() {
		return (String) saveArray[ tableFldConstants.woendtime.ordinal() ];
	}

	public void setBdmsWoendtime(String bdmsWoendtime) {
		saveArray[ tableFldConstants.woendtime.ordinal() ] = bdmsWoendtime;
	}

	public String getBdmsBreaktime() {
		return (String) saveArray[ tableFldConstants.breaktime.ordinal() ];
	}

	public void setBdmsBreaktime(String bdmsBreaktime) {
		saveArray[ tableFldConstants.breaktime.ordinal() ] = bdmsBreaktime;
	}

	public String getBdmsActualworktime() {
		return (String) saveArray[ tableFldConstants.actualworktime.ordinal() ];
	}

	public void setBdmsActualworktime(String bdmsActualworktime) {
		saveArray[ tableFldConstants.actualworktime.ordinal() ] = bdmsActualworktime;
	}

	public String getBdmsDowntime() {
		return (String) saveArray[ tableFldConstants.downtime.ordinal() ];
	}

	public void setBdmsDowntime(String bdmsDowntime) {
		saveArray[ tableFldConstants.downtime.ordinal() ] = bdmsDowntime;
	}

	public String getBdmsProdaccepdate() {
		return (String) saveArray[ tableFldConstants.prodaccepdate.ordinal() ];
	}

	public void setBdmsProdaccepdate(String bdmsProdaccepdate) {
		saveArray[ tableFldConstants.prodaccepdate.ordinal() ] = bdmsProdaccepdate;
	}

	public String getBdmsBookedphenomena() {
		return (String) saveArray[ tableFldConstants.bookedphenomena.ordinal() ];
	}

	public void setBdmsBookedphenomena(String bdmsBookedphenomena) {
		saveArray[ tableFldConstants.bookedphenomena.ordinal() ] = bdmsBookedphenomena;
	}

	public String getBdmsPhenomenadescription() {
		return (String) saveArray[ tableFldConstants.phenomenadescription.ordinal() ];
	}

	public void setBdmsPhenomenadescription(String bdmsPhenomenadescription) {
		saveArray[ tableFldConstants.phenomenadescription.ordinal() ] = bdmsPhenomenadescription;
	}

	public String getBdmsBookedcause() {
		return (String) saveArray[ tableFldConstants.bookedcause.ordinal() ];
	}

	public void setBdmsBookedcause(String bdmsBookedcause) {
		saveArray[ tableFldConstants.bookedcause.ordinal() ] = bdmsBookedcause;
	}

	public String getBdmsFinalphenomena() {
		return (String) saveArray[ tableFldConstants.finalphenomena.ordinal() ];
	}

	public void setBdmsFinalphenomena(String bdmsFinalphenomena) {
		saveArray[ tableFldConstants.finalphenomena.ordinal() ] = bdmsFinalphenomena;
	}

	public String getBdmsFinalcause() {
		return (String) saveArray[ tableFldConstants.finalcause.ordinal() ];
	}

	public void setBdmsFinalcause(String bdmsFinalcause) {
		saveArray[ tableFldConstants.finalcause.ordinal() ] = bdmsFinalcause;
	}

	public String getBdmsBookedtrade() {
		return (String) saveArray[ tableFldConstants.bookedtrade.ordinal() ];
	}

	public void setBdmsBookedtrade(String bdmsBookedtrade) {
		saveArray[ tableFldConstants.bookedtrade.ordinal() ] = bdmsBookedtrade;
	}

	public String getBdmsFinaltrade() {
		return (String) saveArray[ tableFldConstants.finaltrade.ordinal() ];
	}

	public void setBdmsFinaltrade(String bdmsFinaltrade) {
		saveArray[ tableFldConstants.finaltrade.ordinal() ] = bdmsFinaltrade;
	}

	public String getBdmsProblemdescription() {
		return (String) saveArray[ tableFldConstants.problemdescription.ordinal() ];
	}

	public void setBdmsProblemdescription(String bdmsProblemdescription) {
		saveArray[ tableFldConstants.problemdescription.ordinal() ] = bdmsProblemdescription;
	}

	public String getBdmsIsbdlocked() {
		return (String) saveArray[ tableFldConstants.isbdlocked.ordinal() ];
	}

	public void setBdmsIsbdlocked(String bdmsIsbdlocked) {
		saveArray[ tableFldConstants.isbdlocked.ordinal() ] = bdmsIsbdlocked;
	}

	public String getBdmsShiftincharge() {
		return (String) saveArray[ tableFldConstants.shiftincharge.ordinal() ];
	}

	public void setBdmsShiftincharge(String bdmsShiftincharge) {
		saveArray[ tableFldConstants.shiftincharge.ordinal() ] = bdmsShiftincharge;
	}

	public String getBdmsStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setBdmsStatus(String bdmsStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = bdmsStatus;
	}

	public String getBdmsBookedby() {
		return (String) saveArray[ tableFldConstants.bookedby.ordinal() ];
	}

	public void setBdmsBookedby(String bdmsBookedby) {
		saveArray[ tableFldConstants.bookedby.ordinal() ] = bdmsBookedby;
	}

	public String getBdmsRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setBdmsRemarks(String bdmsRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = bdmsRemarks;
	}

	public String getBdmsBookingtype() {
		return (String) saveArray[ tableFldConstants.bookingtype.ordinal() ];
	}

	public void setBdmsBookingtype(String bdmsBookingtype) {
		saveArray[ tableFldConstants.bookingtype.ordinal() ] = bdmsBookingtype;
	}

	public String getBdmsBdrelatedto() {
		return (String) saveArray[ tableFldConstants.bdrelatedto.ordinal() ];
	}

	public void setBdmsBdrelatedto(String bdmsBdrelatedto) {
		saveArray[ tableFldConstants.bdrelatedto.ordinal() ] = bdmsBdrelatedto;
	}

	public String getBdmsWno() {
		return (String) saveArray[ tableFldConstants.wno.ordinal() ];
	}

	public void setBdmsWno(String bdmsWno) {
		saveArray[ tableFldConstants.wno.ordinal() ] = bdmsWno;
	}

	public String getBdmsSpareid() {
		return (String) saveArray[ tableFldConstants.spareid.ordinal() ];
	}

	public void setBdmsSpareid(String bdmsSpareid) {
		saveArray[ tableFldConstants.spareid.ordinal() ] = bdmsSpareid;
	}

	public String getBdmsPriority() {
		return (String) saveArray[ tableFldConstants.priority.ordinal() ];
	}

	public void setBdmsPriority(String bdmsPriority) {
		saveArray[ tableFldConstants.priority.ordinal() ] = bdmsPriority;
	}

	public String getBdmsWoallottedflag() {
		return (String) saveArray[ tableFldConstants.woallottedflag.ordinal() ];
	}

	public void setBdmsWoallottedflag(String bdmsWoallottedflag) {
		saveArray[ tableFldConstants.woallottedflag.ordinal() ] = bdmsWoallottedflag;
	}

	public String getBdmsWostartflag() {
		return (String) saveArray[ tableFldConstants.wostartflag.ordinal() ];
	}

	public void setBdmsWostartflag(String bdmsWostartflag) {
		saveArray[ tableFldConstants.wostartflag.ordinal() ] = bdmsWostartflag;
	}

	public String getBdmsWoendflag() {
		return (String) saveArray[ tableFldConstants.woendflag.ordinal() ];
	}

	public void setBdmsWoendflag(String bdmsWoendflag) {
		saveArray[ tableFldConstants.woendflag.ordinal() ] = bdmsWoendflag;
	}

	public String getBdmsWoprodaccepflag() {
		return (String) saveArray[ tableFldConstants.woprodaccepflag.ordinal() ];
	}

	public void setBdmsWoprodaccepflag(String bdmsWoprodaccepflag) {
		saveArray[ tableFldConstants.woprodaccepflag.ordinal() ] = bdmsWoprodaccepflag;
	}

	public String getBdmsSubassemblyid() {
		return (String) saveArray[ tableFldConstants.subassemblyid.ordinal() ];
	}

	public void setBdmsSubassemblyid(String bdmsSubassemblyid) {
		saveArray[ tableFldConstants.subassemblyid.ordinal() ] = bdmsSubassemblyid;
	}

	public String getBdmsRepeatedbdflag() {
		return (String) saveArray[ tableFldConstants.repeatedbdflag.ordinal() ];
	}

	public void setBdmsRepeatedbdflag(String bdmsRepeatedbdflag) {
		saveArray[ tableFldConstants.repeatedbdflag.ordinal() ] = bdmsRepeatedbdflag;
	}

	public String getBdmsRepeatedbdno() {
		return (String) saveArray[ tableFldConstants.repeatedbdno.ordinal() ];
	}

	public void setBdmsRepeatedbdno(String bdmsRepeatedbdno) {
		saveArray[ tableFldConstants.repeatedbdno.ordinal() ] = bdmsRepeatedbdno;
	}

	public String getBdmsRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setBdmsRelatedto(String bdmsRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = bdmsRelatedto;
	}

	public String getBdmsMould() {
		return (String) saveArray[ tableFldConstants.mould.ordinal() ];
	}

	public void setBdmsMould(String bdmsMould) {
		saveArray[ tableFldConstants.mould.ordinal() ] = bdmsMould;
	}

	public String getBdmsElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setBdmsElementid(String bdmsElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = bdmsElementid;
	}

	public String getBdmsFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setBdmsFlid(String bdmsFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = bdmsFlid;
	}

	public String getBdmsProcessid() {
		return (String) saveArray[ tableFldConstants.processid.ordinal() ];
	}

	public void setBdmsProcessid(String bdmsProcessid) {
		saveArray[ tableFldConstants.processid.ordinal() ] = bdmsProcessid;
	}

	public String getBdmsIsstandby() {
		return (String) saveArray[ tableFldConstants.isstandby.ordinal() ];
	}

	public void setBdmsIsstandby(String bdmsIsstandby) {
		saveArray[ tableFldConstants.isstandby.ordinal() ] = bdmsIsstandby;
	}

	public String getBdmsStandbyequipment() {
		return (String) saveArray[ tableFldConstants.standbyequipment.ordinal() ];
	}

	public void setBdmsStandbyequipment(String bdmsStandbyequipment) {
		saveArray[ tableFldConstants.standbyequipment.ordinal() ] = bdmsStandbyequipment;
	}

	public String getBdmsBreakdowntime() {
		return (String) saveArray[ tableFldConstants.breakdowntime.ordinal() ];
	}

	public void setBdmsBreakdowntime(String bdmsBreakdowntime) {
		saveArray[ tableFldConstants.breakdowntime.ordinal() ] = bdmsBreakdowntime;
	}

	public String getBdmsProductionstop() {
		return (String) saveArray[ tableFldConstants.productionstop.ordinal() ];
	}

	public void setBdmsProductionstop(String bdmsProductionstop) {
		saveArray[ tableFldConstants.productionstop.ordinal() ] = bdmsProductionstop;
	}

	public String getBdmsImmediateaction() {
		return (String) saveArray[ tableFldConstants.immediateaction.ordinal() ];
	}

	public void setBdmsImmediateaction(String immediateaction) {
		saveArray[ tableFldConstants.immediateaction.ordinal() ] = immediateaction;
	}

	public String getBdmsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setBdmsTempfield3(String bdmsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = bdmsTempfield3;
	}

	public String getBdmsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setBdmsTempfield4(String bdmsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = bdmsTempfield4;
	}

	public String getBdmsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setBdmsTempfield5(String bdmsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = bdmsTempfield5;
	}

	public String getBdmsTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setBdmsTempfield6(String bdmsTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = bdmsTempfield6;
	}

	public String getBdmsTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setBdmsTempfield7(String bdmsTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = bdmsTempfield7;
	}

	public String getBdmsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBdmsActive(String bdmsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bdmsActive;
	}

	public String getBdmsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBdmsCreatedby(String bdmsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bdmsCreatedby;
	}

	public String getBdmsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBdmsCreatedon(String bdmsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bdmsCreatedon;
	}

	public String getBdmsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBdmsModifiedon(String bdmsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bdmsModifiedon;
	}
	 public void setSaveArray(Object[] saveArray) {
			this.saveArray = saveArray;
		}

	public List<BdmTlDtl> getBdmDetail() {
		return bdmDetail;
	}

	public void setBdmDetail(List<BdmTlDtl> bdmDetail) {
		this.bdmDetail = bdmDetail;
	}

	public List<BdmTlShiftwisesplit> getBdmShiftwise() {
		return bdmShiftwise;
	}

	public void setBdmShiftwise(List<BdmTlShiftwisesplit> bdmShiftwise) {
		this.bdmShiftwise = bdmShiftwise;
	}

	public String getExternalserviceId() {
		return externalserviceId;
	}

	public void setExternalserviceId(String externalserviceId) {
		this.externalserviceId = externalserviceId;
	}
		
		
}

