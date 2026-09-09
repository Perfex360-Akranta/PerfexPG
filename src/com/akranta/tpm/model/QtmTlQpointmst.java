package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class QtmTlQpointmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flnid, preparedby, approvedby, prepareddate, approveddate
		, tempfield1, tempfield2, tempfield3, tempfield4, active, createdby
		, createdon, modifiedon
	}

	public QtmTlQpointmst()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getQpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQpmKeyid(String qpmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qpmKeyid;
	}

	public String getQpmFlnid() {
		return (String) saveArray[ tableFldConstants.flnid.ordinal() ];
	}

	public void setQpmFlnid(String qpmFlnid) {
		saveArray[ tableFldConstants.flnid.ordinal() ] = qpmFlnid;
	}

	public String getQpmPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setQpmPreparedby(String qpmPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = qpmPreparedby;
	}

	public String getQpmApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setQpmApprovedby(String qpmApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = qpmApprovedby;
	}

	public String getQpmPrepareddate() {
		return (String) saveArray[ tableFldConstants.prepareddate.ordinal() ];
	}

	public void setQpmPrepareddate(String qpmPrepareddate) {
		saveArray[ tableFldConstants.prepareddate.ordinal() ] = qpmPrepareddate;
	}

	public String getQpmApproveddate() {
		return (String) saveArray[ tableFldConstants.approveddate.ordinal() ];
	}

	public void setQpmApproveddate(String qpmApproveddate) {
		saveArray[ tableFldConstants.approveddate.ordinal() ] = qpmApproveddate;
	}

	public String getQpmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setQpmTempfield1(String qpmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = qpmTempfield1;
	}

	public String getQpmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setQpmTempfield2(String qpmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = qpmTempfield2;
	}

	public String getQpmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setQpmTempfield3(String qpmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = qpmTempfield3;
	}

	public String getQpmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setQpmTempfield4(String qpmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = qpmTempfield4;
	}

	public String getQpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQpmActive(String qpmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qpmActive;
	}

	public String getQpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQpmCreatedby(String qpmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qpmCreatedby;
	}

	public String getQpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQpmCreatedon(String qpmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qpmCreatedon;
	}

	public String getQpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQpmModifiedon(String qpmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = qpmModifiedon;
	}

	public void setSaveArray(Object[] dataArr) {
		this.saveArray = dataArr ;
		
	}

}

