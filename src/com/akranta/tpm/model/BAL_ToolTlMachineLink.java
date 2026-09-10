package com.akranta.tpm.model;

import java.util.List;

import com.akranta.tpm.model.BAL_ToolTlMst.tableFldConstants;

public class BAL_ToolTlMachineLink {
private  Object [] saveArray = null;  	
private List<BAL_ToolTlMachineLink> machineList;


	public enum   tableFldConstants
	{
		keyid,toolId,machineId,machineshare,createdby,active, createdon, modifiedon,toolstdlife,toolmanualentry
	}

	public BAL_ToolTlMachineLink()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getTLinkKeyid() {  
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTLinkKeyid(String tlKeyId) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tlKeyId;
	}
	public String getToolId() {  
		return (String) saveArray[ tableFldConstants.toolId.ordinal() ];
	}

	public void setToolId(String toolId) {
		saveArray[ tableFldConstants.toolId.ordinal() ] = toolId;
	}
	public String getMachineId() {  
		return (String) saveArray[ tableFldConstants.machineId.ordinal() ];
	}

	public void setMachineId(String machineId) {
		saveArray[ tableFldConstants.machineId.ordinal() ] = machineId;
	}
	public String getMachineShare() {  
		return (String) saveArray[ tableFldConstants.machineshare.ordinal() ];
	}

	public void setMachineShare(String mchShr) {
		saveArray[ tableFldConstants.machineshare.ordinal() ] = mchShr;
	}
	
	
	public String getCreatesdBy() {  
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCreatesdBy(String crdBy) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = crdBy;
	}
	public String getActive() {  
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setActive(String active) {
		saveArray[ tableFldConstants.active.ordinal() ] = active;
	}
	public String getCreatedOn() {  
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCreatedOn(String crdOn) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = crdOn;
	}
	public String getModifiedOn() {  
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setModifiedOn(String modOn) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = modOn;
	}
	
	public String getStdMchToolLife() {  
		return (String) saveArray[ tableFldConstants.toolstdlife.ordinal() ];
	}

	
	public void setStdMchToolLife(String stdMchLife) {
		saveArray[ tableFldConstants.toolstdlife.ordinal() ] = stdMchLife;
	}
	public String getManualEntryLife() {  
		return (String) saveArray[ tableFldConstants.toolmanualentry.ordinal() ];
	}

	public void setManualEntryLife(String toolMnlEntry) {
		saveArray[ tableFldConstants.toolmanualentry.ordinal() ] = toolMnlEntry;
	}
	
	public List<BAL_ToolTlMachineLink> getMachineList(){
		return machineList;
	}
	
	public void setMachineList(List<BAL_ToolTlMachineLink> mchList) {
		this.machineList = mchList;
	}
	


}
