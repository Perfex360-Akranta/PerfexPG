package com.akranta.tpm.model;

public class BAL_BdmTlMachineruntime {

	private  Object [] saveArray = null;  
	private String isDelete;
	private String refEffDate;
	private String refValue;
	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, runhrsvalue, effectivefrom
		, effectivetill, tempfield1, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public BAL_BdmTlMachineruntime()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public String getIsDelete()
	{
		return isDelete;
	}
	public void setIsDelete(String isDelete)
	{
		this.isDelete=isDelete;
	}
	public String getRefEffDate()
	{
		return refEffDate;
	}
	public void setRefEffDate(String refEffDate)
	{
		this.refEffDate=refEffDate;
	}
	public String getRefValue()
	{
		return refValue;
	}
	public void setRefValue(String refValue)
	{
		this.refValue=refValue;
	}
	
	public String getMcrhKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMcrhKeyid(String mcrhKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mcrhKeyid;
	}

	public String getMcrhFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setMcrhFactoryid(String mcrhFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = mcrhFactoryid;
	}

	public String getMcrhSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setMcrhSectionid(String mcrhSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = mcrhSectionid;
	}

	public String getMcrhCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setMcrhCellid(String mcrhCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = mcrhCellid;
	}

	public String getMcrhMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMcrhMachineid(String mcrhMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = mcrhMachineid;
	}

	public String getMcrhRunhrsvalue() {
		return (String) saveArray[ tableFldConstants.runhrsvalue.ordinal() ];
	}

	public void setMcrhRunhrsvalue(String mcrhRunhrsvalue) {
		saveArray[ tableFldConstants.runhrsvalue.ordinal() ] = mcrhRunhrsvalue;
	}

	public String getMcrhEffectivefrom() {
		return (String) saveArray[ tableFldConstants.effectivefrom.ordinal() ];
	}

	public void setMcrhEffectivefrom(String mcrhEffectivefrom) {
		saveArray[ tableFldConstants.effectivefrom.ordinal() ] = mcrhEffectivefrom;
	}

	public String getMcrhEffectivetill() {
		return (String) saveArray[ tableFldConstants.effectivetill.ordinal() ];
	}

	public void setMcrhEffectivetill(String mcrhEffectivetill) {
		saveArray[ tableFldConstants.effectivetill.ordinal() ] = mcrhEffectivetill;
	}

	public String getMcrhTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMcrhTempfield1(String mcrhTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mcrhTempfield1;
	}

	public String getMcrhTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMcrhTempfield2(String mcrhTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mcrhTempfield2;
	}

	public String getMcrhTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMcrhTempfield3(String mcrhTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mcrhTempfield3;
	}

	public String getMcrhActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMcrhActive(String mcrhActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mcrhActive;
	}

	public String getMcrhCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMcrhCreatedby(String mcrhCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mcrhCreatedby;
	}

	public String getMcrhCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMcrhCreatedon(String mcrhCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mcrhCreatedon;
	}

	public String getMcrhModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMcrhModifiedon(String mcrhModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mcrhModifiedon;
	}

}

