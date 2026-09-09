package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlWorkorderlink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, pldetailid, masterid, wno, theoriticalcycletime, actualcycletime
		, calendartime, noplaninmins, plannedqty, producedqty, rejectionqty
		, unaccountedtime, productiontime, temp1, temp2, temp3, active
		, createdby, createdon, modifiedon
	}

	public PcsTlWorkorderlink()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPtwoKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPtwoKeyid(String ptwoKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ptwoKeyid;
	}

	public String getPtwoPldetailid() {
		return (String) saveArray[ tableFldConstants.pldetailid.ordinal() ];
	}

	public void setPtwoPldetailid(String ptwoPldetailid) {
		saveArray[ tableFldConstants.pldetailid.ordinal() ] = ptwoPldetailid;
	}

	public String getPtwoMasterid() {
		return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
	}

	public void setPtwoMasterid(String ptwoMasterid) {
		saveArray[ tableFldConstants.masterid.ordinal() ] = ptwoMasterid;
	}

	public String getPtwoWno() {
		return (String) saveArray[ tableFldConstants.wno.ordinal() ];
	}

	public void setPtwoWno(String ptwoWno) {
		saveArray[ tableFldConstants.wno.ordinal() ] = ptwoWno;
	}

	public String getPtwoTheoriticalcycletime() {
		return (String) saveArray[ tableFldConstants.theoriticalcycletime.ordinal() ];
	}

	public void setPtwoTheoriticalcycletime(String ptwoTheoriticalcycletime) {
		saveArray[ tableFldConstants.theoriticalcycletime.ordinal() ] = ptwoTheoriticalcycletime;
	}

	public String getPtwoActualcycletime() {
		return (String) saveArray[ tableFldConstants.actualcycletime.ordinal() ];
	}

	public void setPtwoActualcycletime(String ptwoActualcycletime) {
		saveArray[ tableFldConstants.actualcycletime.ordinal() ] = ptwoActualcycletime;
	}

	public String getPtwoCalendartime() {
		return (String) saveArray[ tableFldConstants.calendartime.ordinal() ];
	}

	public void setPtwoCalendartime(String ptwoCalendartime) {
		saveArray[ tableFldConstants.calendartime.ordinal() ] = ptwoCalendartime;
	}

	public String getPtwoNoplaninmins() {
		return (String) saveArray[ tableFldConstants.noplaninmins.ordinal() ];
	}

	public void setPtwoNoplaninmins(String ptwoNoplaninmins) {
		saveArray[ tableFldConstants.noplaninmins.ordinal() ] = ptwoNoplaninmins;
	}

	public String getPtwoPlannedqty() {
		return (String) saveArray[ tableFldConstants.plannedqty.ordinal() ];
	}

	public void setPtwoPlannedqty(String ptwoPlannedqty) {
		saveArray[ tableFldConstants.plannedqty.ordinal() ] = ptwoPlannedqty;
	}

	public String getPtwoProducedqty() {
		return (String) saveArray[ tableFldConstants.producedqty.ordinal() ];
	}

	public void setPtwoProducedqty(String ptwoProducedqty) {
		saveArray[ tableFldConstants.producedqty.ordinal() ] = ptwoProducedqty;
	}

	public String getPtwoRejectionqty() {
		return (String) saveArray[ tableFldConstants.rejectionqty.ordinal() ];
	}

	public void setPtwoRejectionqty(String ptwoRejectionqty) {
		saveArray[ tableFldConstants.rejectionqty.ordinal() ] = ptwoRejectionqty;
	}

	public String getPtwoUnaccountedtime() {
		return (String) saveArray[ tableFldConstants.unaccountedtime.ordinal() ];
	}

	public void setPtwoUnaccountedtime(String ptwoUnaccountedtime) {
		saveArray[ tableFldConstants.unaccountedtime.ordinal() ] = ptwoUnaccountedtime;
	}

	public String getPtwoProductiontime() {
		return (String) saveArray[ tableFldConstants.productiontime.ordinal() ];
	}

	public void setPtwoProductiontime(String ptwoProductiontime) {
		saveArray[ tableFldConstants.productiontime.ordinal() ] = ptwoProductiontime;
	}

	public String getPtwoTemp1() {
		return (String) saveArray[ tableFldConstants.temp1.ordinal() ];
	}

	public void setPtwoTemp1(String ptwoTemp1) {
		saveArray[ tableFldConstants.temp1.ordinal() ] = ptwoTemp1;
	}

	public String getPtwoTemp2() {
		return (String) saveArray[ tableFldConstants.temp2.ordinal() ];
	}

	public void setPtwoTemp2(String ptwoTemp2) {
		saveArray[ tableFldConstants.temp2.ordinal() ] = ptwoTemp2;
	}

	public String getPtwoTemp3() {
		return (String) saveArray[ tableFldConstants.temp3.ordinal() ];
	}

	public void setPtwoTemp3(String ptwoTemp3) {
		saveArray[ tableFldConstants.temp3.ordinal() ] = ptwoTemp3;
	}

	public String getPtwoActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPtwoActive(String ptwoActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ptwoActive;
	}

	public String getPtwoCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPtwoCreatedby(String ptwoCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ptwoCreatedby;
	}

	public String getPtwoCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPtwoCreatedon(String ptwoCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ptwoCreatedon;
	}

	public String getPtwoModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPtwoModifiedon(String ptwoModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ptwoModifiedon;
	}

}

