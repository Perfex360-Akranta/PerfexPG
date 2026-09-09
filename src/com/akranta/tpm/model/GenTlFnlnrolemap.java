package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlFnlnrolemap {

	private  Object [] saveArray = null;  
	public enum   tableFldConstants
	{
		keyid, fnln_keyid, role_keyid, noofpersons, level, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public GenTlFnlnrolemap()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFrlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFrlKeyid(String frlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = frlKeyid;
	}

	public String getFrlFnlnKeyid() {
		return (String) saveArray[ tableFldConstants.fnln_keyid.ordinal() ];
	}

	public void setFrlFnlnKeyid(String frlFnlnKeyid) {
		saveArray[ tableFldConstants.fnln_keyid.ordinal() ] = frlFnlnKeyid;
	}

	public String getFrlRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setFrlRoleKeyid(String frlRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = frlRoleKeyid;
	}

	public String getFrlNoofpersons() {
		return (String) saveArray[ tableFldConstants.noofpersons.ordinal() ];
	}

	public void setFrlNoofpersons(String frlNoofpersons) {
		saveArray[ tableFldConstants.noofpersons.ordinal() ] = frlNoofpersons;
	}
	
	public String getFrlLevel() {
		return (String) saveArray[ tableFldConstants.level.ordinal() ];
	}

	public void setFrlLevel(String frlLevel) {
		saveArray[ tableFldConstants.level.ordinal() ] = frlLevel;
	}
	
	public String getFrlTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFrlTempfield1(String frlTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = frlTempfield1;
	}

	public String getFrlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFrlTempfield2(String frlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = frlTempfield2;
	}

	public String getFrlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFrlTempfield3(String frlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = frlTempfield3;
	}

	public String getFrlTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFrlTempfield4(String frlTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = frlTempfield4;
	}

	public String getFrlTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFrlTempfield5(String frlTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = frlTempfield5;
	}

	public String getFrlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFrlActive(String frlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = frlActive;
	}

	public String getFrlUserid() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFrlUserid(String frlUserid) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = frlUserid;
	}

	public String getFrlTimestamp() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFrlTimestamp(String frlTimestamp) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = frlTimestamp;
	}

	public String getFrlModtimestamp() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFrlModtimestamp(String frlModtimestamp) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = frlModtimestamp;
	}

}

