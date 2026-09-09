package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlDmcfipworkflow {

	private  Object [] saveArray = null;  
	private GenTlDmcfipworkflow newGenTlDmcfipworkflow;
	private String delKeyid;

	public enum   tableFldConstants
	{
		keyid, fipno, fipdate, projectleader, pbuhead, kkchampion, financehead
		, fipstage, approvalstatus, statusmessage, tempfield1, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedby
		, modifiedon
	}

	public GenTlDmcfipworkflow()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	
	public void setSaveArray(Object [] saveArray) {		
		 this.saveArray = saveArray;
	}
	
	public String getDfiwKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDfiwKeyid(String dfiwKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dfiwKeyid;
	}

	public String getDfiwFipno() {
		return (String) saveArray[ tableFldConstants.fipno.ordinal() ];
	}

	public void setDfiwFipno(String dfiwFipno) {
		saveArray[ tableFldConstants.fipno.ordinal() ] = dfiwFipno;
	}

	public String getDfiwFipdate() {
		return (String) saveArray[ tableFldConstants.fipdate.ordinal() ];
	}

	public void setDfiwFipdate(String dfiwFipdate) {
		saveArray[ tableFldConstants.fipdate.ordinal() ] = dfiwFipdate;
	}

	public String getDfiwProjectleader() {
		return (String) saveArray[ tableFldConstants.projectleader.ordinal() ];
	}

	public void setDfiwProjectleader(String dfiwProjectleader) {
		saveArray[ tableFldConstants.projectleader.ordinal() ] = dfiwProjectleader;
	}

	public String getDfiwPbuhead() {
		return (String) saveArray[ tableFldConstants.pbuhead.ordinal() ];
	}

	public void setDfiwPbuhead(String dfiwPbuhead) {
		saveArray[ tableFldConstants.pbuhead.ordinal() ] = dfiwPbuhead;
	}

	public String getDfiwKkchampion() {
		return (String) saveArray[ tableFldConstants.kkchampion.ordinal() ];
	}

	public void setDfiwKkchampion(String dfiwKkchampion) {
		saveArray[ tableFldConstants.kkchampion.ordinal() ] = dfiwKkchampion;
	}

	public String getDfiwFinancehead() {
		return (String) saveArray[ tableFldConstants.financehead.ordinal() ];
	}

	public void setDfiwFinancehead(String dfiwFinancehead) {
		saveArray[ tableFldConstants.financehead.ordinal() ] = dfiwFinancehead;
	}

	public String getDfiwFipstage() {
		return (String) saveArray[ tableFldConstants.fipstage.ordinal() ];
	}

	public void setDfiwFipstage(String dfiwFipstage) {
		saveArray[ tableFldConstants.fipstage.ordinal() ] = dfiwFipstage;
	}

	public String getDfiwApprovalstatus() {
		return (String) saveArray[ tableFldConstants.approvalstatus.ordinal() ];
	}

	public void setDfiwApprovalstatus(String dfiwApprovalstatus) {
		saveArray[ tableFldConstants.approvalstatus.ordinal() ] = dfiwApprovalstatus;
	}

	public String getDfiwStatusmessage() {
		return (String) saveArray[ tableFldConstants.statusmessage.ordinal() ];
	}

	public void setDfiwStatusmessage(String dfiwStatusmessage) {
		saveArray[ tableFldConstants.statusmessage.ordinal() ] = dfiwStatusmessage;
	}

	public String getDfiwTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setDfiwTempfield1(String dfiwTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = dfiwTempfield1;
	}

	public String getDfiwTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDfiwTempfield2(String dfiwTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dfiwTempfield2;
	}

	public String getDfiwTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDfiwTempfield3(String dfiwTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dfiwTempfield3;
	}

	public String getDfiwTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDfiwTempfield4(String dfiwTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dfiwTempfield4;
	}

	public String getDfiwActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDfiwActive(String dfiwActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dfiwActive;
	}

	public String getDfiwCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDfiwCreatedby(String dfiwCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dfiwCreatedby;
	}

	public String getDfiwCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDfiwCreatedon(String dfiwCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dfiwCreatedon;
	}

	public String getDfiwModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setDfiwModifiedby(String dfiwModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = dfiwModifiedby;
	}

	public String getDfiwModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDfiwModifiedon(String dfiwModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dfiwModifiedon;
	}

	public void setDelKeyid(String delKeyid) {
		this.delKeyid = delKeyid;
	}

	public String getDelKeyid() {
		return delKeyid;
	}
}

