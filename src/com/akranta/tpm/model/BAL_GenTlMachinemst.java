package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_GenTlMachinemst {

	private  Object [] saveArray = null;  
	private List<GenTlMchemplink> operatorgrid;  
	private List<GenTlMchmaintteamlink> maintainceGrid;  
	private List<GenTlMachineskillmst> operatorSkillGrid;  
	private List<GenTlMachineskillmst> maintainceSkillGrid;  
	private List<GenTlMchparameterlink> equipmentParameterGrid;  
	private List<GenTlMchsubmchlink> subEquipmentGrid;
	private BAL_GenTlFunctionallocn genTlFunctionallocn;  

	public enum   tableFldConstants
	{
		keyid, machineno, machinename, cellid, subcellid, equipmentgroup
		, controltype, purpose, category, subcategory, machinerank, jhstep
		, jhstepdate, phase, wires, ipvolt, ipvoltmin, ipvoltmax, ipfreq
		, ipfreqmin, ipfreqmax, powersupply, connectedload, dbno, sbno
		, specification, remarks, manufacturerid, manufactureddate, make
		, model, mfrslno, mfrremarks, supplierid, pono, podate, purchasedate
		, purchaseprice, installeddate, isunderwarranty, warrantydate
		, supplierremarks, isunderamc, amcdate, amcvendor, amcrenewaldate
		, amcremarks, machineorder, effectivedate, inactivateddate, includeforproduction
		, givesfinaloutput, costcentreid, circleid, iscavityormandrel
		, maxmeterreading, currencyid, workcenter, technicalid, type
		, tradeid, oldmachineno, tempfield4, tempfield5, elementid, flid
		, active, createdby, createdon, modifiedon
	}

	public BAL_GenTlMachinemst()
	{
		setOperatorgrid(new ArrayList<GenTlMchemplink> ());	
		saveArray = new  Object [ 70 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getMchmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMchmKeyid(String mchmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mchmKeyid;
	}

	public String getMchmMachineno() {
		return (String) saveArray[ tableFldConstants.machineno.ordinal() ];
	}

	public void setMchmMachineno(String mchmMachineno) {
		saveArray[ tableFldConstants.machineno.ordinal() ] = mchmMachineno;
	}

	public String getMchmMachinename() {
		return (String) saveArray[ tableFldConstants.machinename.ordinal() ];
	}

	public void setMchmMachinename(String mchmMachinename) {
		saveArray[ tableFldConstants.machinename.ordinal() ] = mchmMachinename;
	}

	public String getMchmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setMchmCellid(String mchmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = mchmCellid;
	}

	public String getMchmSubcellid() {
		return (String) saveArray[ tableFldConstants.subcellid.ordinal() ];
	}

	public void setMchmSubcellid(String mchmSubcellid) {
		saveArray[ tableFldConstants.subcellid.ordinal() ] = mchmSubcellid;
	}

	public String getMchmEquipmentgroup() {
		return (String) saveArray[ tableFldConstants.equipmentgroup.ordinal() ];
	}

	public void setMchmEquipmentgroup(String mchmEquipmentgroup) {
		saveArray[ tableFldConstants.equipmentgroup.ordinal() ] = mchmEquipmentgroup;
	}

	public String getMchmControltype() {
		return (String) saveArray[ tableFldConstants.controltype.ordinal() ];
	}

	public void setMchmControltype(String mchmControltype) {
		saveArray[ tableFldConstants.controltype.ordinal() ] = mchmControltype;
	}

	public String getMchmPurpose() {
		return (String) saveArray[ tableFldConstants.purpose.ordinal() ];
	}

	public void setMchmPurpose(String mchmPurpose) {
		saveArray[ tableFldConstants.purpose.ordinal() ] = mchmPurpose;
	}

	public String getMchmCategory() {
		return (String) saveArray[ tableFldConstants.category.ordinal() ];
	}

	public void setMchmCategory(String mchmCategory) {
		saveArray[ tableFldConstants.category.ordinal() ] = mchmCategory;
	}

	public String getMchmSubcategory() {
		return (String) saveArray[ tableFldConstants.subcategory.ordinal() ];
	}

	public void setMchmSubcategory(String mchmSubcategory) {
		saveArray[ tableFldConstants.subcategory.ordinal() ] = mchmSubcategory;
	}

	public String getMchmMachinerank() {
		return (String) saveArray[ tableFldConstants.machinerank.ordinal() ];
	}

	public void setMchmMachinerank(String mchmMachinerank) {
		saveArray[ tableFldConstants.machinerank.ordinal() ] = mchmMachinerank;
	}

	public String getMchmJhstep() {
		return (String) saveArray[ tableFldConstants.jhstep.ordinal() ];
	}

	public void setMchmJhstep(String mchmJhstep) {
		saveArray[ tableFldConstants.jhstep.ordinal() ] = mchmJhstep;
	}

	public String getMchmJhstepdate() {
		return (String) saveArray[ tableFldConstants.jhstepdate.ordinal() ];
	}

	public void setMchmJhstepdate(String mchmJhstepdate) {
		saveArray[ tableFldConstants.jhstepdate.ordinal() ] = mchmJhstepdate;
	}

	public String getMchmPhase() {
		return (String) saveArray[ tableFldConstants.phase.ordinal() ];
	}

	public void setMchmPhase(String mchmPhase) {
		saveArray[ tableFldConstants.phase.ordinal() ] = mchmPhase;
	}

	public String getMchmWires() {
		return (String) saveArray[ tableFldConstants.wires.ordinal() ];
	}

	public void setMchmWires(String mchmWires) {
		saveArray[ tableFldConstants.wires.ordinal() ] = mchmWires;
	}

	public String getMchmIpvolt() {
		return (String) saveArray[ tableFldConstants.ipvolt.ordinal() ];
	}

	public void setMchmIpvolt(String mchmIpvolt) {
		saveArray[ tableFldConstants.ipvolt.ordinal() ] = mchmIpvolt;
	}

	public String getMchmIpvoltmin() {
		return (String) saveArray[ tableFldConstants.ipvoltmin.ordinal() ];
	}

	public void setMchmIpvoltmin(String mchmIpvoltmin) {
		saveArray[ tableFldConstants.ipvoltmin.ordinal() ] = mchmIpvoltmin;
	}

	public String getMchmIpvoltmax() {
		return (String) saveArray[ tableFldConstants.ipvoltmax.ordinal() ];
	}

	public void setMchmIpvoltmax(String mchmIpvoltmax) {
		saveArray[ tableFldConstants.ipvoltmax.ordinal() ] = mchmIpvoltmax;
	}

	public String getMchmIpfreq() {
		return (String) saveArray[ tableFldConstants.ipfreq.ordinal() ];
	}

	public void setMchmIpfreq(String mchmIpfreq) {
		saveArray[ tableFldConstants.ipfreq.ordinal() ] = mchmIpfreq;
	}

	public String getMchmIpfreqmin() {
		return (String) saveArray[ tableFldConstants.ipfreqmin.ordinal() ];
	}

	public void setMchmIpfreqmin(String mchmIpfreqmin) {
		saveArray[ tableFldConstants.ipfreqmin.ordinal() ] = mchmIpfreqmin;
	}

	public String getMchmIpfreqmax() {
		return (String) saveArray[ tableFldConstants.ipfreqmax.ordinal() ];
	}

	public void setMchmIpfreqmax(String mchmIpfreqmax) {
		saveArray[ tableFldConstants.ipfreqmax.ordinal() ] = mchmIpfreqmax;
	}

	public String getMchmPowersupply() {
		return (String) saveArray[ tableFldConstants.powersupply.ordinal() ];
	}

	public void setMchmPowersupply(String mchmPowersupply) {
		saveArray[ tableFldConstants.powersupply.ordinal() ] = mchmPowersupply;
	}

	public String getMchmConnectedload() {
		return (String) saveArray[ tableFldConstants.connectedload.ordinal() ];
	}

	public void setMchmConnectedload(String mchmConnectedload) {
		saveArray[ tableFldConstants.connectedload.ordinal() ] = mchmConnectedload;
	}

	public String getMchmDbno() {
		return (String) saveArray[ tableFldConstants.dbno.ordinal() ];
	}

	public void setMchmDbno(String mchmDbno) {
		saveArray[ tableFldConstants.dbno.ordinal() ] = mchmDbno;
	}

	public String getMchmSbno() {
		return (String) saveArray[ tableFldConstants.sbno.ordinal() ];
	}

	public void setMchmSbno(String mchmSbno) {
		saveArray[ tableFldConstants.sbno.ordinal() ] = mchmSbno;
	}

	public String getMchmSpecification() {
		return (String) saveArray[ tableFldConstants.specification.ordinal() ];
	}

	public void setMchmSpecification(String mchmSpecification) {
		saveArray[ tableFldConstants.specification.ordinal() ] = mchmSpecification;
	}

	public String getMchmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMchmRemarks(String mchmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = mchmRemarks;
	}

	public String getMchmManufacturerid() {
		return (String) saveArray[ tableFldConstants.manufacturerid.ordinal() ];
	}

	public void setMchmManufacturerid(String mchmManufacturerid) {
		saveArray[ tableFldConstants.manufacturerid.ordinal() ] = mchmManufacturerid;
	}

	public String getMchmManufactureddate() {
		return (String) saveArray[ tableFldConstants.manufactureddate.ordinal() ];
	}

	public void setMchmManufactureddate(String mchmManufactureddate) {
		saveArray[ tableFldConstants.manufactureddate.ordinal() ] = mchmManufactureddate;
	}

	public String getMchmMake() {
		return (String) saveArray[ tableFldConstants.make.ordinal() ];
	}

	public void setMchmMake(String mchmMake) {
		saveArray[ tableFldConstants.make.ordinal() ] = mchmMake;
	}

	public String getMchmModel() {
		return (String) saveArray[ tableFldConstants.model.ordinal() ];
	}

	public void setMchmModel(String mchmModel) {
		saveArray[ tableFldConstants.model.ordinal() ] = mchmModel;
	}

	public String getMchmMfrslno() {
		return (String) saveArray[ tableFldConstants.mfrslno.ordinal() ];
	}

	public void setMchmMfrslno(String mchmMfrslno) {
		saveArray[ tableFldConstants.mfrslno.ordinal() ] = mchmMfrslno;
	}

	public String getMchmMfrremarks() {
		return (String) saveArray[ tableFldConstants.mfrremarks.ordinal() ];
	}

	public void setMchmMfrremarks(String mchmMfrremarks) {
		saveArray[ tableFldConstants.mfrremarks.ordinal() ] = mchmMfrremarks;
	}

	public String getMchmSupplierid() {
		return (String) saveArray[ tableFldConstants.supplierid.ordinal() ];
	}

	public void setMchmSupplierid(String mchmSupplierid) {
		saveArray[ tableFldConstants.supplierid.ordinal() ] = mchmSupplierid;
	}

	public String getMchmPono() {
		return (String) saveArray[ tableFldConstants.pono.ordinal() ];
	}

	public void setMchmPono(String mchmPono) {
		saveArray[ tableFldConstants.pono.ordinal() ] = mchmPono;
	}

	public String getMchmPodate() {
		return (String) saveArray[ tableFldConstants.podate.ordinal() ];
	}

	public void setMchmPodate(String mchmPodate) {
		saveArray[ tableFldConstants.podate.ordinal() ] = mchmPodate;
	}

	public String getMchmPurchasedate() {
		return (String) saveArray[ tableFldConstants.purchasedate.ordinal() ];
	}

	public void setMchmPurchasedate(String mchmPurchasedate) {
		saveArray[ tableFldConstants.purchasedate.ordinal() ] = mchmPurchasedate;
	}

	public String getMchmPurchaseprice() {
		return (String) saveArray[ tableFldConstants.purchaseprice.ordinal() ];
	}

	public void setMchmPurchaseprice(String mchmPurchaseprice) {
		saveArray[ tableFldConstants.purchaseprice.ordinal() ] = mchmPurchaseprice;
	}

	public String getMchmInstalleddate() {
		return (String) saveArray[ tableFldConstants.installeddate.ordinal() ];
	}

	public void setMchmInstalleddate(String mchmInstalleddate) {
		saveArray[ tableFldConstants.installeddate.ordinal() ] = mchmInstalleddate;
	}

	public String getMchmIsunderwarranty() {
		return (String) saveArray[ tableFldConstants.isunderwarranty.ordinal() ];
	}

	public void setMchmIsunderwarranty(String mchmIsunderwarranty) {
		saveArray[ tableFldConstants.isunderwarranty.ordinal() ] = mchmIsunderwarranty;
	}

	public String getMchmWarrantydate() {
		return (String) saveArray[ tableFldConstants.warrantydate.ordinal() ];
	}

	public void setMchmWarrantydate(String mchmWarrantydate) {
		saveArray[ tableFldConstants.warrantydate.ordinal() ] = mchmWarrantydate;
	}

	public String getMchmSupplierremarks() {
		return (String) saveArray[ tableFldConstants.supplierremarks.ordinal() ];
	}

	public void setMchmSupplierremarks(String mchmSupplierremarks) {
		saveArray[ tableFldConstants.supplierremarks.ordinal() ] = mchmSupplierremarks;
	}

	public String getMchmIsunderamc() {
		return (String) saveArray[ tableFldConstants.isunderamc.ordinal() ];
	}

	public void setMchmIsunderamc(String mchmIsunderamc) {
		saveArray[ tableFldConstants.isunderamc.ordinal() ] = mchmIsunderamc;
	}

	public String getMchmAmcdate() {
		return (String) saveArray[ tableFldConstants.amcdate.ordinal() ];
	}

	public void setMchmAmcdate(String mchmAmcdate) {
		saveArray[ tableFldConstants.amcdate.ordinal() ] = mchmAmcdate;
	}

	public String getMchmAmcvendor() {
		return (String) saveArray[ tableFldConstants.amcvendor.ordinal() ];
	}

	public void setMchmAmcvendor(String mchmAmcvendor) {
		saveArray[ tableFldConstants.amcvendor.ordinal() ] = mchmAmcvendor;
	}

	public String getMchmAmcrenewaldate() {
		return (String) saveArray[ tableFldConstants.amcrenewaldate.ordinal() ];
	}

	public void setMchmAmcrenewaldate(String mchmAmcrenewaldate) {
		saveArray[ tableFldConstants.amcrenewaldate.ordinal() ] = mchmAmcrenewaldate;
	}

	public String getMchmAmcremarks() {
		return (String) saveArray[ tableFldConstants.amcremarks.ordinal() ];
	}

	public void setMchmAmcremarks(String mchmAmcremarks) {
		saveArray[ tableFldConstants.amcremarks.ordinal() ] = mchmAmcremarks;
	}

	public String getMchmMachineorder() {
		return (String) saveArray[ tableFldConstants.machineorder.ordinal() ];
	}

	public void setMchmMachineorder(String mchmMachineorder) {
		saveArray[ tableFldConstants.machineorder.ordinal() ] = mchmMachineorder;
	}

	public String getMchmEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setMchmEffectivedate(String mchmEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = mchmEffectivedate;
	}

	public String getMchmInactivateddate() {
		return (String) saveArray[ tableFldConstants.inactivateddate.ordinal() ];
	}

	public void setMchmInactivateddate(String mchmInactivateddate) {
		saveArray[ tableFldConstants.inactivateddate.ordinal() ] = mchmInactivateddate;
	}

	public String getMchmIncludeforproduction() {
		return (String) saveArray[ tableFldConstants.includeforproduction.ordinal() ];
	}

	public void setMchmIncludeforproduction(String mchmIncludeforproduction) {
		saveArray[ tableFldConstants.includeforproduction.ordinal() ] = mchmIncludeforproduction;
	}

	public String getMchmGivesfinaloutput() {
		return (String) saveArray[ tableFldConstants.givesfinaloutput.ordinal() ];
	}

	public void setMchmGivesfinaloutput(String mchmGivesfinaloutput) {
		saveArray[ tableFldConstants.givesfinaloutput.ordinal() ] = mchmGivesfinaloutput;
	}

	public String getMchmCostcentreid() {
		return (String) saveArray[ tableFldConstants.costcentreid.ordinal() ];
	}

	public void setMchmCostcentreid(String mchmCostcentreid) {
		saveArray[ tableFldConstants.costcentreid.ordinal() ] = mchmCostcentreid;
	}

	public String getMchmCircleid() {
		return (String) saveArray[ tableFldConstants.circleid.ordinal() ];
	}

	public void setMchmCircleid(String mchmCircleid) {
		saveArray[ tableFldConstants.circleid.ordinal() ] = mchmCircleid;
	}

	public String getMchmIscavityormandrel() {
		return (String) saveArray[ tableFldConstants.iscavityormandrel.ordinal() ];
	}

	public void setMchmIscavityormandrel(String mchmIscavityormandrel) {
		saveArray[ tableFldConstants.iscavityormandrel.ordinal() ] = mchmIscavityormandrel;
	}

	public String getMchmMaxmeterreading() {
		return (String) saveArray[ tableFldConstants.maxmeterreading.ordinal() ];
	}

	public void setMchmMaxmeterreading(String mchmMaxmeterreading) {
		saveArray[ tableFldConstants.maxmeterreading.ordinal() ] = mchmMaxmeterreading;
	}

	public String getMchmCurrencyid() {
		return (String) saveArray[ tableFldConstants.currencyid.ordinal() ];
	}

	public void setMchmCurrencyid(String mchmCurrencyid) {
		saveArray[ tableFldConstants.currencyid.ordinal() ] = mchmCurrencyid;
	}

	public String getMchmWorkcenter() {
		return (String) saveArray[ tableFldConstants.workcenter.ordinal() ];
	}

	public void setMchmWorkcenter(String mchmWorkcenter) {
		saveArray[ tableFldConstants.workcenter.ordinal() ] = mchmWorkcenter;
	}

	public String getMchmTechnicalid() {
		return (String) saveArray[ tableFldConstants.technicalid.ordinal() ];
	}

	public void setMchmTechnicalid(String mchmTechnicalid) {
		saveArray[ tableFldConstants.technicalid.ordinal() ] = mchmTechnicalid;
	}

	public String getMchmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setMchmType(String mchmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = mchmType;
	}

	public String getMchmTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setMchmTradeid(String mchmTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = mchmTradeid;
	}

	public String getMchmoldmachineno() {
		return (String) saveArray[ tableFldConstants.oldmachineno.ordinal() ];
	}

	public void setMchmoldmachineno(String mchmoldmachineno) {
		saveArray[ tableFldConstants.oldmachineno.ordinal() ] = mchmoldmachineno;
	}

	public String getMchmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMchmTempfield4(String mchmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mchmTempfield4;
	}

	public String getMchmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMchmTempfield5(String mchmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mchmTempfield5;
	}

	public String getMchmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setMchmElementid(String mchmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = mchmElementid;
	}

	public String getMchmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMchmFlid(String mchmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = mchmFlid;
	}

	public String getMchmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMchmActive(String mchmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mchmActive;
	}

	public String getMchmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMchmCreatedby(String mchmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mchmCreatedby;
	}

	public String getMchmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMchmCreatedon(String mchmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mchmCreatedon;
	}

	public String getMchmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMchmModifiedon(String mchmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mchmModifiedon;
	}

	public void setOperatorgrid(List<GenTlMchemplink> operatorgrid) {
		this.operatorgrid = operatorgrid;
	}

	public List<GenTlMchemplink> getOperatorgrid() {
		return operatorgrid;
	}

	public void setmaintainceGrid(List<GenTlMchmaintteamlink> maintainceGrid) {
		this.setMaintainceGrid(maintainceGrid);
		
	}

	public void setMaintainceGrid(List<GenTlMchmaintteamlink> maintainceGrid) {
		this.maintainceGrid = maintainceGrid;
	}

	public List<GenTlMchmaintteamlink> getMaintainceGrid() {
		return maintainceGrid;
	}

	public void setOperatorSkillGrid(List<GenTlMachineskillmst> operatorSkillGrid) {
		this.operatorSkillGrid = operatorSkillGrid;
	}

	public List<GenTlMachineskillmst> getOperatorSkillGrid() {
		return operatorSkillGrid;
	}

	public void setmaintainceSkillGrid(List<GenTlMachineskillmst> maintainceSkillGrid) {
		this.maintainceSkillGrid = maintainceSkillGrid;
		
	}

	public void setMaintainceSkillGrid(List<GenTlMachineskillmst> maintainceSkillGrid) {
		this.maintainceSkillGrid = maintainceSkillGrid;
	}

	public List<GenTlMachineskillmst> getMaintainceSkillGrid() {
		return maintainceSkillGrid;
	}

	public void setEquipmentParameterGrid(List<GenTlMchparameterlink> equipmentParameterGrid) {
		this.equipmentParameterGrid = equipmentParameterGrid;
	}

	public List<GenTlMchparameterlink> getEquipmentParameterGrid() {
		return equipmentParameterGrid;
	}

	public void setSubEquipmentGrid(List<GenTlMchsubmchlink> subEquipmentGrid) {
		this.subEquipmentGrid = subEquipmentGrid;
	}

	public List<GenTlMchsubmchlink> getSubEquipmentGrid() {
		return subEquipmentGrid;
	}

	public void setGenTlFunctionallocn(BAL_GenTlFunctionallocn genTlFunctionallocn) {
		this.genTlFunctionallocn = genTlFunctionallocn;
	}

	public BAL_GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}

}

