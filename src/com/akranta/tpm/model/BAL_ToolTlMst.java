package com.akranta.tpm.model;

public class BAL_ToolTlMst {
	
private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid,toolcode,toolname,stdlife,stdcotime,remark,active,estsharp,
		revsharp,category, createdby, createdon, modifiedon
	}

	public BAL_ToolTlMst()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getToolKeyid() {  
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setToolKeyid(String keyId) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = keyId;
	}
	public String getToolCode() {  
		return (String) saveArray[ tableFldConstants.toolcode.ordinal() ];
	}

	public void setToolCode(String toolCode) {
		saveArray[ tableFldConstants.toolcode.ordinal() ] = toolCode;
	}
	public String getToolName() {  
		return (String) saveArray[ tableFldConstants.toolname.ordinal() ];
	}

	public void setToolName(String toolname) {
		saveArray[ tableFldConstants.toolname.ordinal() ] = toolname;
	}
	public String getStdLife() {  
		return (String) saveArray[ tableFldConstants.stdlife.ordinal() ];
	}

	public void setStdLife(String stdLife) {
		saveArray[ tableFldConstants.stdlife.ordinal() ] = stdLife;
	}
	
	public String getStdCoTime() {  
		return (String) saveArray[ tableFldConstants.stdcotime.ordinal() ];
	}

	public void setStdCoTime(String stdCoTime) {
		saveArray[ tableFldConstants.stdcotime.ordinal() ] = stdCoTime;
	}
	public String getRemark() {  
		return (String) saveArray[ tableFldConstants.remark.ordinal() ];
	}

	public void setRemark(String remark) {
		saveArray[ tableFldConstants.remark.ordinal() ] = remark;
	}
	public String getEstSharp() {  
		return (String) saveArray[ tableFldConstants.estsharp.ordinal() ];
	}

	public void setEstSharp(String estSharp) {
		saveArray[ tableFldConstants.estsharp.ordinal() ] = estSharp;
	}
	public String getRevShrp() {  
		return (String) saveArray[ tableFldConstants.revsharp.ordinal() ];
	}

	public void setRevShrp(String revsharp) {
		saveArray[ tableFldConstants.revsharp.ordinal() ] = revsharp;
	}
	public String getToolCategory() {  
		return (String) saveArray[ tableFldConstants.category.ordinal() ];
	}

	public void setToolCategory(String category) {
		saveArray[ tableFldConstants.category.ordinal() ] = category;
	}
	
	public String getActive() {  
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setActive(String active) {
		saveArray[ tableFldConstants.active.ordinal() ] = active;
	}
	public String getCreatedBy() {  
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCreatedBy(String createdby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = createdby;
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

	public void setModifiedOn(String modifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = modifiedon;
	}
	

}
