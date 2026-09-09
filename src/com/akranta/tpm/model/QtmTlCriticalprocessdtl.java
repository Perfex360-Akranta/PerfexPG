package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlCriticalprocessdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, crpp_keyid, method, unit, value, min, max
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public QtmTlCriticalprocessdtl()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getCrpdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCrpdKeyid(String crpdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = crpdKeyid;
	}

	public String getCrpdCrppKeyid() {
		return (String) saveArray[ tableFldConstants.crpp_keyid.ordinal() ];
	}

	public void setCrpdCrppKeyid(String crpdCrppKeyid) {
		saveArray[ tableFldConstants.crpp_keyid.ordinal() ] = crpdCrppKeyid;
	}

	public String getCrpdMethod() {
		return (String) saveArray[ tableFldConstants.method.ordinal() ];
	}

	public void setCrpdMethod(String crpdMethod) {
		saveArray[ tableFldConstants.method.ordinal() ] = crpdMethod;
	}

	public String getCrpdUnit() {
		return (String) saveArray[ tableFldConstants.unit.ordinal() ];
	}

	public void setCrpdUnit(String crpdUnit) {
		saveArray[ tableFldConstants.unit.ordinal() ] = crpdUnit;
	}

	public String getCrpdValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setCrpdValue(String crpdValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = crpdValue;
	}

	public String getCrpdMin() {
		return (String) saveArray[ tableFldConstants.min.ordinal() ];
	}

	public void setCrpdMin(String crpdMin) {
		saveArray[ tableFldConstants.min.ordinal() ] = crpdMin;
	}

	public String getCrpdMax() {
		return (String) saveArray[ tableFldConstants.max.ordinal() ];
	}

	public void setCrpdMax(String crpdMax) {
		saveArray[ tableFldConstants.max.ordinal() ] = crpdMax;
	}

	public String getCrpdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCrpdTempfield3(String crpdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = crpdTempfield3;
	}

	public String getCrpdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCrpdTempfield4(String crpdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = crpdTempfield4;
	}

	public String getCrpdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setCrpdTempfield5(String crpdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = crpdTempfield5;
	}

	public String getCrpdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCrpdActive(String crpdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = crpdActive;
	}

	public String getCrpdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCrpdCreatedby(String crpdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = crpdCreatedby;
	}

	public String getCrpdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCrpdCreatedon(String crpdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = crpdCreatedon;
	}

	public String getCrpdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCrpdModifiedon(String crpdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = crpdModifiedon;
	}
	
	/**
	 * Generic setValue method to set values by field enum
	 */
	public void setValue(tableFldConstants field, String value) {
		if (field != null) {
			saveArray[field.ordinal()] = value;
		}
	}
	
	/**
	 * Convert detail object to JSON string
	 */
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
	 * Parse JSON string to detail object
	 */
	public static QtmTlCriticalprocessdtl fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		QtmTlCriticalprocessdtl dtl = new QtmTlCriticalprocessdtl();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			dtl.setValue(field, val != null && val.equals("null") ? null : val);
		}
		return dtl;
	}

	/**
	 * Parse JSON array to list of detail objects
	 */
	public static List<QtmTlCriticalprocessdtl> fromJsonList(String json) {
		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<QtmTlCriticalprocessdtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			QtmTlCriticalprocessdtl dtl = new QtmTlCriticalprocessdtl();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				Object valueObj = obj.opt(key);
				String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

				dtl.setValue(field, val);
			}

			list.add(dtl);
		}

		return list;
	}

	/**
	 * Convert list of detail objects to JSON array string
	 */
	public static String toJsonManualList(List<QtmTlCriticalprocessdtl> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		// Add null check
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (i > 0)
					sb.append(",");
				sb.append(list.get(i).toJsonManual());
			}
		}

		sb.append("]");
		return sb.toString();
	}

}