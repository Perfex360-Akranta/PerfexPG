package com.akranta.tpm.model;

public class ToolChangeTlDetails {
private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		                                                                                                                                                                                  

		keyid,toolkeyid,toolserialno,changeddate,changeType,sectionid,cellid,machineid,stdcotime,actcotime,estsharp,sharpno,
		lfwrtsharp,lastchangedate,prdtillchdate,nextchangedate,changeLinkId,factoryid,remark,lifeExtended,lifeEarly,trialTool,tempfield6,active, createdby, createdon, modifiedon,
		usedremark,stdLife,extendedLife,changeReason,whywhy,flid,tempfiled8,tempfiled9,tempfiled10
	}
                                                                                                                                                                                        


	public ToolChangeTlDetails()
	{
		saveArray = new  Object [ 36 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	
	public String getToolKeyid() {  
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setToolKeyid(String keyId) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = keyId;
	}
	
	public String getToolMstKeyid() {  
		return (String) saveArray[ tableFldConstants.toolkeyid.ordinal() ];
	}

	public void setToolMstKeyid(String toolKeyId) {
		saveArray[ tableFldConstants.toolkeyid.ordinal() ] = toolKeyId;
	}
	public String getToolSrNo() {  
		return (String) saveArray[ tableFldConstants.toolserialno.ordinal() ];
	}

	public void setToolSrNo(String srno) {
		saveArray[ tableFldConstants.toolserialno.ordinal() ] = srno;
	}
	public String getChangeDate() {  
		return (String) saveArray[ tableFldConstants.changeddate.ordinal() ];
	}

	public void setChangeDate(String chDate) {
		saveArray[ tableFldConstants.changeddate.ordinal() ] = chDate;
	}
	public String getNextDate() {  
		return (String) saveArray[ tableFldConstants.nextchangedate.ordinal() ];
	}

	public void setNextDate(String nxDate) {
		saveArray[ tableFldConstants.nextchangedate.ordinal() ] = nxDate;
	}
	public String getLastDate() {  
		return (String) saveArray[ tableFldConstants.lastchangedate.ordinal() ];
	}

	public void setLastDate(String lsDate) {
		saveArray[ tableFldConstants.lastchangedate.ordinal() ] = lsDate;
	}
	public String getChangeType() {  
		return (String) saveArray[ tableFldConstants.changeType.ordinal() ];
	}

	public void setChangeType(String ChangeType) {
		saveArray[ tableFldConstants.changeType.ordinal() ] = ChangeType;
	}
	public String getSection() {  
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setSection(String section) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = section;
	}
	public String getCell() {  
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setCell(String cellId) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = cellId;
	}
	public String getMachine() {  
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMachine(String machineId) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = machineId;
	}
	
	public String getStdCoTime() {  
		return (String) saveArray[ tableFldConstants.stdcotime.ordinal() ];
	}

	public void setStdCoTime(String coTime) {
		saveArray[ tableFldConstants.stdcotime.ordinal() ] = coTime;
	}
	public String getActCoTime() {  
		return (String) saveArray[ tableFldConstants.actcotime.ordinal() ];
	}

	public void setActCoTime(String actTime) {
		saveArray[ tableFldConstants.actcotime.ordinal() ] = actTime;
	}
	public String getEstSharp() {  
		return (String) saveArray[ tableFldConstants.estsharp.ordinal() ];
	}

	public void setEstSharp(String estSharp) {
		saveArray[ tableFldConstants.estsharp.ordinal() ] = estSharp;
	}
	public String getSharpNo() {  
		return (String) saveArray[ tableFldConstants.sharpno.ordinal() ];
	}

	public void setSharpNo(String shrNo) {
		saveArray[ tableFldConstants.sharpno.ordinal() ] = shrNo;
	}
	public String getLfWrtSharp() {  
		return (String) saveArray[ tableFldConstants.lfwrtsharp.ordinal() ];
	}

	public void setLfWrtSharp(String lfwrtSrp) {
		saveArray[ tableFldConstants.lfwrtsharp.ordinal() ] = lfwrtSrp;
	}
	public String getActive() {  
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setActive(String active) {
		saveArray[ tableFldConstants.active.ordinal() ] = active;
	}
	public String getPrdLstCh() {  
		return (String) saveArray[ tableFldConstants.prdtillchdate.ordinal() ];
	}

	public void setPrdLstCh(String prdtillchdate) {
		saveArray[ tableFldConstants.prdtillchdate.ordinal() ] = prdtillchdate;
	}
	public String getFactory() {  
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setFactory(String factoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = factoryid;
	}
	
	public String getRemark() {  
		return (String) saveArray[ tableFldConstants.remark.ordinal() ];
	}

	public void setRemark(String temp2) {
		saveArray[ tableFldConstants.remark.ordinal() ] = temp2;
	}	
	
	public String getLifeExtended() {  
		return (String) saveArray[ tableFldConstants.lifeExtended.ordinal() ];
	}

	public void setLifeExtended(String temp3) {
		saveArray[ tableFldConstants.lifeExtended.ordinal() ] = temp3;
	}	
	public String getLifeEarly() {  
		return (String) saveArray[ tableFldConstants.lifeEarly.ordinal() ];
	}

	public void setLifeEarly(String early) {
		saveArray[ tableFldConstants.lifeEarly.ordinal() ] = early;
	}	
	public String getTrialTool() {  
		return (String) saveArray[ tableFldConstants.trialTool.ordinal() ];
	}

	public void setTrialTool(String trial) {
		saveArray[ tableFldConstants.trialTool.ordinal() ] = trial;
	}	
	public String getTempField6() {  
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setTempField6(String temp6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = temp6;
	}	
	
	public String getCreatedBy() {  
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCreatedBy(String createdBy) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = createdBy;
	}
	public String getCreatedOn() {  
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCreatedOn(String createdOn) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = createdOn;
	}
	public String getModifiedOn() {  
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setModifiedOn(String modifiedOn) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = modifiedOn;
	}
	///////////
	//usedremark,stdLife,extendedLife,changeReason,whywhy,tempfiled7,tempfiled8,tempfiled9,tempfiled10
	public String getUsedRemark() {  
		return (String) saveArray[ tableFldConstants.usedremark.ordinal() ];
	}

	public void setUsedRemark(String usedRemark) {
		saveArray[ tableFldConstants.usedremark.ordinal() ] = usedRemark;
	}
	public String getStdLife() {  
		return (String) saveArray[ tableFldConstants.stdLife.ordinal() ];
	}

	public void setStdLife(String stdLife) {
		saveArray[ tableFldConstants.stdLife.ordinal() ] = stdLife;
	}

	public String getExtendedLife() {  
		return (String) saveArray[ tableFldConstants.extendedLife.ordinal() ];
	}

	public void setExtendedLife(String extendedLife) {
		saveArray[ tableFldConstants.extendedLife.ordinal() ] = extendedLife;
	}

	public String getChangeReason() {  
		return (String) saveArray[ tableFldConstants.changeReason.ordinal() ];
	}

	public void setChangeReason(String changeReason) {
		saveArray[ tableFldConstants.changeReason.ordinal() ] = changeReason;
	}

	
	public String getWhyWhy() {  
		return (String) saveArray[ tableFldConstants.whywhy.ordinal() ];
	}

	public void setWhyWhy(String whywhy) {
		saveArray[ tableFldConstants.whywhy.ordinal() ] = whywhy;
	}

	public String getFlid() {  
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setFlid(String flid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = flid;
	}
	
	public String getTempField8() {  
		return (String) saveArray[ tableFldConstants.tempfiled8.ordinal() ];
	}

	public void setTempField8(String temp8) {
		saveArray[ tableFldConstants.tempfiled8.ordinal() ] = temp8;
	}
	public String getTempField9() {  
		return (String) saveArray[ tableFldConstants.tempfiled9.ordinal() ];
	}

	public void setTempField9(String temp9) {
		saveArray[ tableFldConstants.tempfiled9.ordinal() ] = temp9;
	}
	public String getTempField10() {  
		return (String) saveArray[ tableFldConstants.tempfiled10.ordinal() ];
	}

	public void setTempField10(String temp10) {
		saveArray[ tableFldConstants.tempfiled10.ordinal() ] = temp10;
	}



}
