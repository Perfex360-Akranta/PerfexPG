package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlProductionplan {

	private  Object [] saveArray = null;  
	private List<PcsTlProductionplan> prodPlan ;

	public enum   tableFldConstants
	{
		keyid, entrydate, factoryid, sectionid, cellid, machineid, productid
		, plandate, planqty, revisionno, revisiondate, plannedby, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public PcsTlProductionplan()
	{
		setProdPlan(new ArrayList<PcsTlProductionplan>());
		saveArray = new  Object [ 21 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public List<PcsTlProductionplan> getProdPlan() {
		return prodPlan;
	}

	public void setProdPlan(List<PcsTlProductionplan> prodPlan) {
		this.prodPlan = prodPlan;
	}

	public String getPrplKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPrplKeyid(String prplKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = prplKeyid;
	}

	public String getPrplEntrydate() {
		return (String) saveArray[ tableFldConstants.entrydate.ordinal() ];
	}

	public void setPrplEntrydate(String prplEntrydate) {
		saveArray[ tableFldConstants.entrydate.ordinal() ] = prplEntrydate;
	}

	public String getPrplFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setPrplFactoryid(String prplFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = prplFactoryid;
	}

	public String getPrplSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setPrplSectionid(String prplSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = prplSectionid;
	}

	public String getPrplCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setPrplCellid(String prplCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = prplCellid;
	}

	public String getPrplMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setPrplMachineid(String prplMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = prplMachineid;
	}

	public String getPrplProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setPrplProductid(String prplProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = prplProductid;
	}

	public String getPrplPlandate() {
		return (String) saveArray[ tableFldConstants.plandate.ordinal() ];
	}

	public void setPrplPlandate(String prplPlandate) {
		saveArray[ tableFldConstants.plandate.ordinal() ] = prplPlandate;
	}

	public String getPrplPlanqty() {
		return (String) saveArray[ tableFldConstants.planqty.ordinal() ];
	}

	public void setPrplPlanqty(String prplPlanqty) {
		saveArray[ tableFldConstants.planqty.ordinal() ] = prplPlanqty;
	}

	public String getPrplRevisionno() {
		return (String) saveArray[ tableFldConstants.revisionno.ordinal() ];
	}

	public void setPrplRevisionno(String prplRevisionno) {
		saveArray[ tableFldConstants.revisionno.ordinal() ] = prplRevisionno;
	}

	public String getPrplRevisiondate() {
		return (String) saveArray[ tableFldConstants.revisiondate.ordinal() ];
	}

	public void setPrplRevisiondate(String prplRevisiondate) {
		saveArray[ tableFldConstants.revisiondate.ordinal() ] = prplRevisiondate;
	}

	public String getPrplPlannedby() {
		return (String) saveArray[ tableFldConstants.plannedby.ordinal() ];
	}

	public void setPrplPlannedby(String prplPlannedby) {
		saveArray[ tableFldConstants.plannedby.ordinal() ] = prplPlannedby;
	}

	public String getPrplTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPrplTempfield1(String prplTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = prplTempfield1;
	}

	public String getPrplTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPrplTempfield2(String prplTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = prplTempfield2;
	}

	public String getPrplTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPrplTempfield3(String prplTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = prplTempfield3;
	}

	public String getPrplTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPrplTempfield4(String prplTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = prplTempfield4;
	}

	public String getPrplTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPrplTempfield5(String prplTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = prplTempfield5;
	}

	public String getPrplActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPrplActive(String prplActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = prplActive;
	}

	public String getPrplCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPrplCreatedby(String prplCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = prplCreatedby;
	}

	public String getPrplCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPrplCreatedon(String prplCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = prplCreatedon;
	}

	public String getPrplModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPrplModifiedon(String prplModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = prplModifiedon;
	}

}

