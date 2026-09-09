package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlEmpnomenationmst {

	private  Object [] saveArray = null;  
	
	private List <EntTlEmpnomenationmst> listentTlEmpnomenationmst;
   
	public enum   tableFldConstants
	{
		keyid, programid, batchid, empid, nomenatedby, nomenateddate
		, approvedby, approveddate, remarks, status, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, modifiedby
		, active, createdby, createdon, modifiedon
	}

	public EntTlEmpnomenationmst()
	{
		saveArray = new  Object [ 21 ];
		listentTlEmpnomenationmst = new ArrayList<EntTlEmpnomenationmst>();
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public List<EntTlEmpnomenationmst> getlistentTlEmpnomenationmstlist() 
	{
		
		return listentTlEmpnomenationmst;
		
	}
	public void setlistentTlEmpnomenationmstlist(List <EntTlEmpnomenationmst> listentTlEmpnomenationmstlist) {
		this.listentTlEmpnomenationmst=listentTlEmpnomenationmstlist;
	}

	public String getEpnmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEpnmKeyid(String epnmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = epnmKeyid;
	}

	public String getEpnmProgramid() {
		return (String) saveArray[ tableFldConstants.programid.ordinal() ];
	}

	public void setEpnmProgramid(String epnmProgramid) {
		saveArray[ tableFldConstants.programid.ordinal() ] = epnmProgramid;
	}

	public String getEpnmBatchid() {
		return (String) saveArray[ tableFldConstants.batchid.ordinal() ];
	}

	public void setEpnmBatchid(String epnmBatchid) {
		saveArray[ tableFldConstants.batchid.ordinal() ] = epnmBatchid;
	}

	public String getEpnmEmpid() {
		return (String) saveArray[ tableFldConstants.empid.ordinal() ];
	}

	public void setEpnmEmpid(String epnmEmpid) {
		saveArray[ tableFldConstants.empid.ordinal() ] = epnmEmpid;
	}

	public String getEpnmNomenatedby() {
		return (String) saveArray[ tableFldConstants.nomenatedby.ordinal() ];
	}

	public void setEpnmNomenatedby(String epnmNomenatedby) {
		saveArray[ tableFldConstants.nomenatedby.ordinal() ] = epnmNomenatedby;
	}

	public String getEpnmNomenateddate() {
		return (String) saveArray[ tableFldConstants.nomenateddate.ordinal() ];
	}

	public void setEpnmNomenateddate(String epnmNomenateddate) {
		saveArray[ tableFldConstants.nomenateddate.ordinal() ] = epnmNomenateddate;
	}

	public String getEpnmApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setEpnmApprovedby(String epnmApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = epnmApprovedby;
	}

	public String getEpnmApproveddate() {
		return (String) saveArray[ tableFldConstants.approveddate.ordinal() ];
	}

	public void setEpnmApproveddate(String epnmApproveddate) {
		saveArray[ tableFldConstants.approveddate.ordinal() ] = epnmApproveddate;
	}

	public String getEpnmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setEpnmRemarks(String epnmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = epnmRemarks;
	}

	public String getEpnmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setEpnmStatus(String epnmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = epnmStatus;
	}

	public String getEpnmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEpnmTempfield1(String epnmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = epnmTempfield1;
	}

	public String getEpnmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEpnmTempfield2(String epnmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = epnmTempfield2;
	}

	public String getEpnmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEpnmTempfield3(String epnmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = epnmTempfield3;
	}

	public String getEpnmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEpnmTempfield4(String epnmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = epnmTempfield4;
	}

	public String getEpnmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEpnmTempfield5(String epnmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = epnmTempfield5;
	}

	public String getEpnmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setEpnmTempfield6(String epnmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = epnmTempfield6;
	}

	public String getEpnmModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setEpnmModifiedby(String epnmModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = epnmModifiedby;
	}

	public String getEpnmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEpnmActive(String epnmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = epnmActive;
	}

	public String getEpnmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEpnmCreatedby(String epnmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = epnmCreatedby;
	}

	public String getEpnmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEpnmCreatedon(String epnmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = epnmCreatedon;
	}

	public String getEpnmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEpnmModifiedon(String epnmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = epnmModifiedon;
	}

}

