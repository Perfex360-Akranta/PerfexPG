package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlOnlinepreparetestdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, olpm_keyid, olqm_keyid,sortorderno, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, active, createdon, modifiedon
	}

	public EntTlOnlinepreparetestdtl()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOlpdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setOlpdKeyid(String olpdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = olpdKeyid;
	}

	public String getOlpdOlpmKeyid() {
		return (String) saveArray[ tableFldConstants.olpm_keyid.ordinal() ];
	}

	public void setOlpdOlpmKeyid(String olpdOlpmKeyid) {
		saveArray[ tableFldConstants.olpm_keyid.ordinal() ] = olpdOlpmKeyid;
	}

	public String getOlpdOlqmKeyid() {
		return (String) saveArray[ tableFldConstants.olqm_keyid.ordinal() ];
	}

	public void setOlpdOlqmKeyid(String olpdOlqmKeyid) {
		saveArray[ tableFldConstants.olqm_keyid.ordinal() ] = olpdOlqmKeyid;
	}
	public String getOlpdSortorderno() {
		return (String) saveArray[ tableFldConstants.olpm_keyid.ordinal() ];
	}

	public void setOlpdSortorderno(String olpdSortorderno) {
		saveArray[ tableFldConstants.sortorderno.ordinal() ] = olpdSortorderno;
	}

	public String getOlpdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setOlpdTempfield1(String olpdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = olpdTempfield1;
	}

	public String getOlpdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setOlpdTempfield2(String olpdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = olpdTempfield2;
	}

	public String getOlpdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setOlpdTempfield3(String olpdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = olpdTempfield3;
	}

	public String getOlpdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOlpdTempfield4(String olpdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = olpdTempfield4;
	}

	public String getOlpdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setOlpdTempfield5(String olpdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = olpdTempfield5;
	}

	public String getOlpdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOlpdCreatedby(String olpdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = olpdCreatedby;
	}

	public String getOlpdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOlpdActive(String olpdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = olpdActive;
	}

	public String getOlpdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOlpdCreatedon(String olpdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = olpdCreatedon;
	}

	public String getOlpdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOlpdModifiedon(String olpdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = olpdModifiedon;
	}

}

