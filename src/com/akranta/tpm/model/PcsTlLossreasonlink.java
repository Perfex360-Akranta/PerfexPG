package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlLossreasonlink {

	private  Object [] saveArray = null;  
	private List <PcsTlAncilliarytime> pcsTlAncilliarytimeList ;


	public enum   tableFldConstants
	{
		keyid, lossid, reasonid, causeid, rootcauseid, pldetailid, wno
		, minutes, instance, date, shiftid, hourno, factoryid, sectionid
		, cellid, machineid, subgroupid, remarks, msrno, processid
		, flid, elementid,  active, createdby, createdon, modifiedon
	}

	public PcsTlLossreasonlink()
	{
		saveArray = new  Object [ 26 ];
		pcsTlAncilliarytimeList = new ArrayList<PcsTlAncilliarytime>();
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setPcsTlAncilliarytimeList(List <PcsTlAncilliarytime> pcsTlAncilliarytime) {
		this.pcsTlAncilliarytimeList =  pcsTlAncilliarytime;
	}
	public List<PcsTlAncilliarytime> getPcsTlAncilliarytimeList() 
	{
		return pcsTlAncilliarytimeList;
	}
	
	public String getPlrkKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPlrkKeyid(String plrkKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = plrkKeyid;
	}

	public String getPlrkLossid() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setPlrkLossid(String plrkLossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = plrkLossid;
	}

	public String getPlrkReasonid() {
		return (String) saveArray[ tableFldConstants.reasonid.ordinal() ];
	}

	public void setPlrkReasonid(String plrkReasonid) {
		saveArray[ tableFldConstants.reasonid.ordinal() ] = plrkReasonid;
	}

	public String getPlrkCauseid() {
		return (String) saveArray[ tableFldConstants.causeid.ordinal() ];
	}

	public void setPlrkCauseid(String plrkCauseid) {
		saveArray[ tableFldConstants.causeid.ordinal() ] = plrkCauseid;
	}

	public String getPlrkRootcauseid() {
		return (String) saveArray[ tableFldConstants.rootcauseid.ordinal() ];
	}

	public void setPlrkRootcauseid(String plrkRootcauseid) {
		saveArray[ tableFldConstants.rootcauseid.ordinal() ] = plrkRootcauseid;
	}

	public String getPlrkPldetailid() {
		return (String) saveArray[ tableFldConstants.pldetailid.ordinal() ];
	}

	public void setPlrkPldetailid(String plrkPldetailid) {
		saveArray[ tableFldConstants.pldetailid.ordinal() ] = plrkPldetailid;
	}

	public String getPlrkWno() {
		return (String) saveArray[ tableFldConstants.wno.ordinal() ];
	}

	public void setPlrkWno(String plrkWno) {
		saveArray[ tableFldConstants.wno.ordinal() ] = plrkWno;
	}

	public String getPlrkMinutes() {
		return (String) saveArray[ tableFldConstants.minutes.ordinal() ];
	}

	public void setPlrkMinutes(String plrkMinutes) {
		saveArray[ tableFldConstants.minutes.ordinal() ] = plrkMinutes;
	}

	public String getPlrkInstance() {
		return (String) saveArray[ tableFldConstants.instance.ordinal() ];
	}

	public void setPlrkInstance(String plrkInstance) {
		saveArray[ tableFldConstants.instance.ordinal() ] = plrkInstance;
	}

	public String getPlrkDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPlrkDate(String plrkDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = plrkDate;
	}

	public String getPlrkShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setPlrkShiftid(String plrkShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = plrkShiftid;
	}

	public String getPlrkHourno() {
		return (String) saveArray[ tableFldConstants.hourno.ordinal() ];
	}

	public void setPlrkHourno(String plrkHourno) {
		saveArray[ tableFldConstants.hourno.ordinal() ] = plrkHourno;
	}

	public String getPlrkFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setPlrkFactoryid(String plrkFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = plrkFactoryid;
	}

	public String getPlrkSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setPlrkSectionid(String plrkSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = plrkSectionid;
	}

	public String getPlrkCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setPlrkCellid(String plrkCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = plrkCellid;
	}

	public String getPlrkMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setPlrkMachineid(String plrkMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = plrkMachineid;
	}

	public String getPlrkSubgroupid() {
		return (String) saveArray[ tableFldConstants.subgroupid.ordinal() ];
	}

	public void setPlrkSubgroupid(String plrkSubgroupid) {
		saveArray[ tableFldConstants.subgroupid.ordinal() ] = plrkSubgroupid;
	}

	public String getPlrkRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setPlrkRemarks(String plrkRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = plrkRemarks;
	}

	public String getPlrkMsrno() {
		return (String) saveArray[ tableFldConstants.msrno.ordinal() ];
	}

	public void setPlrkMsrno(String plrkMsrno) {
		saveArray[ tableFldConstants.msrno.ordinal() ] = plrkMsrno;
	}

	public String getPlrkProcessid() {
		return (String) saveArray[ tableFldConstants.processid.ordinal() ];
	}

	public void setPlrkProcessid(String plrkProcessid) {
		saveArray[ tableFldConstants.processid.ordinal() ] = plrkProcessid;
	}

	public String getPlrkFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPlrkFlid(String plrkFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = plrkFlid;
	}


	public String getPlrkElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setPlrkElementid(String plrkElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = plrkElementid;
	}

	public String getPlrkActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPlrkActive(String plrkActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = plrkActive;
	}

	public String getPlrkCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPlrkCreatedby(String plrkCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = plrkCreatedby;
	}

	public String getPlrkCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPlrkCreatedon(String plrkCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = plrkCreatedon;
	}

	public String getPlrkModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPlrkModifiedon(String plrkModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = plrkModifiedon;
	}

}

