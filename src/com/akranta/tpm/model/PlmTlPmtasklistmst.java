package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlPmtasklistmst {

	private  Object [] saveArray = null;  
	private List<PlmTlPmtasklistdtl> pmtasklist ;

	public enum   tableFldConstants
	{
		keyid, taskgroup, tempfield1, tempfield2, tempfield3, tempfield4
		, active, createdby, createdon, modifiedon
	}

	public PlmTlPmtasklistmst()
	{
		setpmtasklist(new ArrayList<PlmTlPmtasklistdtl> ());
		saveArray = new  Object [ 10 ];
	}
	



	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPmtmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPmtmKeyid(String pmtmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pmtmKeyid;
	}

	public String getPmtmTaskgroup() {
		return (String) saveArray[ tableFldConstants.taskgroup.ordinal() ];
	}

	public void setPmtmTaskgroup(String pmtmTaskgroup) {
		saveArray[ tableFldConstants.taskgroup.ordinal() ] = pmtmTaskgroup;
	}

	public String getPmtmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPmtmTempfield1(String pmtmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pmtmTempfield1;
	}

	public String getPmtmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPmtmTempfield2(String pmtmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pmtmTempfield2;
	}

	public String getPmtmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPmtmTempfield3(String pmtmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = pmtmTempfield3;
	}

	public String getPmtmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPmtmTempfield4(String pmtmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = pmtmTempfield4;
	}

	public String getPmtmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPmtmActive(String pmtmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pmtmActive;
	}

	public String getPmtmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPmtmCreatedby(String pmtmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pmtmCreatedby;
	}

	public String getPmtmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPmtmCreatedon(String pmtmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pmtmCreatedon;
	}

	public String getPmtmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPmtmModifiedon(String pmtmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pmtmModifiedon;
	}

	
	public List<PlmTlPmtasklistdtl> getpmtasklist() {
		return pmtasklist;
	}
	

	public void setpmtasklist(List<PlmTlPmtasklistdtl> Pmtasklist) {
		// TODO Auto-generated method stub
		this.pmtasklist = Pmtasklist;
		
	}




	public void setSaveArray(Object[] dataArr) {
		// TODO Auto-generated method stub
		this.saveArray = dataArr;
	}
}



