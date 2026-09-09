package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class AbnTlAbnhistorydtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, abnm_keyid, type, date, reasons, changeby, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, active, createdby, createdon, modifiedon
	}

	public AbnTlAbnhistorydtl()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getAbnhKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAbnhKeyid(String abnhKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = abnhKeyid;
	}

	public String getAbnhAbnmKeyid() {
		return (String) saveArray[ tableFldConstants.abnm_keyid.ordinal() ];
	}

	public void setAbnhAbnmKeyid(String abnhAbnmKeyid) {
		saveArray[ tableFldConstants.abnm_keyid.ordinal() ] = abnhAbnmKeyid;
	}

	public String getAbnhType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setAbnhType(String abnhType) {
		saveArray[ tableFldConstants.type.ordinal() ] = abnhType;
	}

	public String getAbnhDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setAbnhDate(String abnhDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = abnhDate;
	}

	public String getAbnhReasons() {
		return (String) saveArray[ tableFldConstants.reasons.ordinal() ];
	}

	public void setAbnhReasons(String abnhReasons) {
		saveArray[ tableFldConstants.reasons.ordinal() ] = abnhReasons;
	}

	public String getAbnhChangeby() {
		return (String) saveArray[ tableFldConstants.changeby.ordinal() ];
	}

	public void setAbnhChangeby(String abnhChangeby) {
		saveArray[ tableFldConstants.changeby.ordinal() ] = abnhChangeby;
	}

	public String getAbnhTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setAbnhTempfield1(String abnhTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = abnhTempfield1;
	}

	public String getAbnhTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setAbnhTempfield2(String abnhTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = abnhTempfield2;
	}

	public String getAbnhTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAbnhTempfield3(String abnhTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = abnhTempfield3;
	}

	public String getAbnhTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setAbnhTempfield4(String abnhTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = abnhTempfield4;
	}

	public String getAbnhTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setAbnhTempfield5(String abnhTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = abnhTempfield5;
	}

	public String getAbnhTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setAbnhTempfield6(String abnhTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = abnhTempfield6;
	}

	public String getAbnhActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAbnhActive(String abnhActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = abnhActive;
	}

	public String getAbnhCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAbnhCreatedby(String abnhCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = abnhCreatedby;
	}

	public String getAbnhCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAbnhCreatedon(String abnhCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = abnhCreatedon;
	}

	public String getAbnhModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAbnhModifiedon(String abnhModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = abnhModifiedon;
	}

}

