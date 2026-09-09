package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlCriticalprocessmst {

	private  Object [] saveArray = null;  
	
	private QtmTlCriticalprocessdtl qtmTlCriticalprocessdtl = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, parameter, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public QtmTlCriticalprocessmst()
	{
		saveArray = new  Object [ 14 ];
		setQtmTlCriticalprocessdtl(new QtmTlCriticalprocessdtl());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	
	public String getCrppKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCrppKeyid(String crppKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = crppKeyid;
	}

	public String getCrppFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setCrppFlid(String crppFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = crppFlid;
	}

	public String getCrppElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setCrppElementid(String crppElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = crppElementid;
	}

	public String getCrppDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setCrppDate(String crppDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = crppDate;
	}

	public String getCrppParameter() {
		return (String) saveArray[ tableFldConstants.parameter.ordinal() ];
	}

	public void setCrppParameter(String crppParameter) {
		saveArray[ tableFldConstants.parameter.ordinal() ] = crppParameter;
	}

	public String getCrppTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCrppTempfield1(String crppTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = crppTempfield1;
	}

	public String getCrppTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCrppTempfield2(String crppTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = crppTempfield2;
	}

	public String getCrppTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCrppTempfield3(String crppTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = crppTempfield3;
	}

	public String getCrppTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCrppTempfield4(String crppTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = crppTempfield4;
	}

	public String getCrppTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setCrppTempfield5(String crppTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = crppTempfield5;
	}

	public String getCrppActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCrppActive(String crppActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = crppActive;
	}

	public String getCrppCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCrppCreatedby(String crppCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = crppCreatedby;
	}

	public String getCrppCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCrppCreatedon(String crppCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = crppCreatedon;
	}

	public String getCrppModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCrppModifiedon(String crppModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = crppModifiedon;
	}

	public void setQtmTlCriticalprocessdtl(QtmTlCriticalprocessdtl qtmTlCriticalprocessdtl) {
		this.qtmTlCriticalprocessdtl = qtmTlCriticalprocessdtl;
	}

	public QtmTlCriticalprocessdtl getQtmTlCriticalprocessdtl() {
		return qtmTlCriticalprocessdtl;
	}
	
	/**
	 * Generic setValue method to set values by field enum
	 */
	public void setValue(tableFldConstants field, String value) {
		if (field != null) {
			saveArray[field.ordinal()] = value;
		}
	}
	
	public String toJsonManual() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		boolean first = true;
		for (tableFldConstants field : tableFldConstants.values()) {
			int index = field.ordinal();
			if (index < saveArray.length) {
				if (!first)
					sb.append(",");
				sb.append("\"").append(field.name()).append("\":");
				Object val = saveArray[index];
				if (field.name().equals("keyid") && val == null) {
					sb.append("null");
				} else if (val == null) {
					sb.append("\"{}\"");
				} else {
					sb.append("\"").append(val.toString()).append("\"");
				}
				first = false;
			}
		}

		sb.append("}");
		return sb.toString();
	}

	/**
	 * Parse JSON string to master object
	 */
	public static QtmTlCriticalprocessmst fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		QtmTlCriticalprocessmst mst = new QtmTlCriticalprocessmst();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			mst.setValue(field, val != null && val.equals("null") ? null : val);
		}
		return mst;
	}

}