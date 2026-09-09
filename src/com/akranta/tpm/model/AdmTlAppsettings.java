package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class AdmTlAppsettings {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, code, settingvalue, type, uom, transactionmode, checktablename
		, systemmode, order, remarks, pillar, active, createdby, createdon
		, modifiedon
	}

	public AdmTlAppsettings()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
public void setSaveArray(Object [] saveArray) {
		
		this.saveArray = saveArray;
	}
	public String getAppsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAppsKeyid(String appsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = appsKeyid;
	}

	public String getAppsName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setAppsName(String appsName) {
		saveArray[ tableFldConstants.name.ordinal() ] = appsName;
	}

	public String getAppsCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setAppsCode(String appsCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = appsCode;
	}

	public String getAppsSettingvalue() {
		return (String) saveArray[ tableFldConstants.settingvalue.ordinal() ];
	}

	public void setAppsSettingvalue(String appsSettingvalue) {
		saveArray[ tableFldConstants.settingvalue.ordinal() ] = appsSettingvalue;
	}

	public String getAppsType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setAppsType(String appsType) {
		saveArray[ tableFldConstants.type.ordinal() ] = appsType;
	}

	public String getAppsUom() {
		return (String) saveArray[ tableFldConstants.uom.ordinal() ];
	}

	public void setAppsUom(String appsUom) {
		saveArray[ tableFldConstants.uom.ordinal() ] = appsUom;
	}

	public String getAppsTransactionmode() {
		return (String) saveArray[ tableFldConstants.transactionmode.ordinal() ];
	}

	public void setAppsTransactionmode(String appsTransactionmode) {
		saveArray[ tableFldConstants.transactionmode.ordinal() ] = appsTransactionmode;
	}

	public String getAppsChecktablename() {
		return (String) saveArray[ tableFldConstants.checktablename.ordinal() ];
	}

	public void setAppsChecktablename(String appsChecktablename) {
		saveArray[ tableFldConstants.checktablename.ordinal() ] = appsChecktablename;
	}

	public String getAppsSystemmode() {
		return (String) saveArray[ tableFldConstants.systemmode.ordinal() ];
	}

	public void setAppsSystemmode(String appsSystemmode) {
		saveArray[ tableFldConstants.systemmode.ordinal() ] = appsSystemmode;
	}

	public String getAppsOrder() {
		return (String) saveArray[ tableFldConstants.order.ordinal() ];
	}

	public void setAppsOrder(String appsOrder) {
		saveArray[ tableFldConstants.order.ordinal() ] = appsOrder;
	}

	public String getAppsRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setAppsRemarks(String appsRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = appsRemarks;
	}

	public String getAppsPillar() {
		return (String) saveArray[ tableFldConstants.pillar.ordinal() ];
	}

	public void setAppsPillar(String appsPillar) {
		saveArray[ tableFldConstants.pillar.ordinal() ] = appsPillar;
	}

	public String getAppsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAppsActive(String appsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = appsActive;
	}

	public String getAppsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAppsCreatedby(String appsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = appsCreatedby;
	}

	public String getAppsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAppsCreatedon(String appsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = appsCreatedon;
	}

	public String getAppsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAppsModifiedon(String appsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = appsModifiedon;
	}

}

