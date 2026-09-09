package com.akranta.tpm.model;

import java.util.List;

import com.akranta.tpm.model.SapTlSparesreplaced.tableFldConstants;

public class TlmTLAlertDetails {
	private  Object [] saveArray = null;  
	private List<TlmTLAlertDetails> tlmTLAlertDetails;
 
	public enum   tableFldConstants
	
	
	{
		keyId,section, cell, machine, tool, datechanged, quantity, toolSrNo
		, toolShrpno, tempfield1
	}

	public TlmTLAlertDetails()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public String getKeyid() {
		return (String) saveArray[ tableFldConstants.keyId.ordinal() ];
	}

	public void setKeyid(String keyid) {
		saveArray[ tableFldConstants.keyId.ordinal() ] = keyid;
	}

	public String getSection() {
		return (String) saveArray[ tableFldConstants.section.ordinal() ];
	}

	public void setSection(String section) {
		saveArray[ tableFldConstants.section.ordinal() ] = section;
	}

	public String getCell() {
		return (String) saveArray[ tableFldConstants.cell.ordinal() ];
	}

	public void setCell(String cellid) {
		saveArray[ tableFldConstants.cell.ordinal() ] = cellid;
	}

	public String getMachine() {
		return (String) saveArray[ tableFldConstants.machine.ordinal() ];
	}

	public void setMachine(String machine) {
		saveArray[ tableFldConstants.machine.ordinal() ] = machine;
	}

	public String getTool() {
		return (String) saveArray[ tableFldConstants.tool.ordinal() ];
	}

	public void setTool(String tool) {
		saveArray[ tableFldConstants.tool.ordinal() ] = tool;
	}

	public String getDateChanged() {
		return (String) saveArray[ tableFldConstants.datechanged.ordinal() ];
	}

	public void setDateChanged(String datechanged) {
		saveArray[ tableFldConstants.datechanged.ordinal() ] = datechanged;
	}

	public String getQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setQuantity(String quantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = quantity;
	}
	
	public String getToolSrNo() {
		return (String) saveArray[ tableFldConstants.toolSrNo.ordinal() ];
	}

	public void setToolSrNo(String toolSrNo) {
		saveArray[ tableFldConstants.toolSrNo.ordinal() ] = toolSrNo;
	}
	public String getToolShrpNo() {
		return (String) saveArray[ tableFldConstants.toolShrpno.ordinal() ];
	}

	public void setToolShrpNo(String toolShrpno) {
		saveArray[ tableFldConstants.toolShrpno.ordinal() ] = toolShrpno;
	}
	
	public String getTempField1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTempField1(String tempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = tempfield1;
	}
	
	public List<TlmTLAlertDetails> getToolDetails(){
		return tlmTLAlertDetails;
	}
	
	public void setToolDetails(List<TlmTLAlertDetails> dtlsList) {
		this.tlmTLAlertDetails = dtlsList;
	}
	
	
}
