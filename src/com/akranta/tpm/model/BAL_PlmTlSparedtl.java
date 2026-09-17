package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PlmTlSparedtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, standardid, spareid, quantity, createdby, createdon, modifiedon
	}

	public BAL_PlmTlSparedtl()
	{
		saveArray = new  Object [ 7 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPspdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPspdKeyid(String pspd_keyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pspd_keyid;
	}

	public String getPspdStandardid() {
		return (String) saveArray[ tableFldConstants.standardid.ordinal() ];
	}

	public void setPspdStandardid(String pspd_standardid) {
		saveArray[ tableFldConstants.standardid.ordinal() ] = pspd_standardid;
	}

	public String getPspdSpareid() {
		return (String) saveArray[ tableFldConstants.spareid.ordinal() ];
	}

	public void setPspdSpareid(String pspd_spareid) {
		saveArray[ tableFldConstants.spareid.ordinal() ] = pspd_spareid;
	}

	public String getPspdQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setPspdQuantity(String pspd_quantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = pspd_quantity;
	}

	public String getPspdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPspdCreatedby(String pspd_createdby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pspd_createdby;
	}

	public String getPspdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPspdCreatedon(String pspd_createdon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pspd_createdon;
	}

	public String getPspdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPspdModifiedon(String pspd_modifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pspd_modifiedon;
	}
	public Object getValue(tableFldConstants field) {
		return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
		saveArray[field.ordinal()] = value;
	}
	public String toJsonManual() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		boolean first = true;
		for (tableFldConstants field : tableFldConstants.values()) {
			int index = field.ordinal();
			if (index < saveArray.length) {
				if (!first) sb.append(",");
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

	public static String toJsonManualList(List<BAL_PlmTlSparedtl> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (int i = 0; i < list.size(); i++) {
			if (i > 0) sb.append(",");
			sb.append(list.get(i).toJsonManual());
		}

		sb.append("]");
		return sb.toString();
	}

	public static BAL_PlmTlSparedtl fromJson(String json) {
		JSONObject obj = JSONObject.fromObject(json);

		BAL_PlmTlSparedtl spr = new BAL_PlmTlSparedtl();

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();
			String val = obj.optString(key, null);
			if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
				val = "";
			}
			spr.setValue(field, val);
		}
		return spr;
	}

	public static List<BAL_PlmTlSparedtl> fromJsonList(String json) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BAL_PlmTlSparedtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			BAL_PlmTlSparedtl spr = new BAL_PlmTlSparedtl();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();
				Object valueObj = obj.opt(key);
				String val = (valueObj == null) ? "" : valueObj.toString();
				if ("null".equalsIgnoreCase(val) || "{}".equals(val)) {
					val = "";
				}
				spr.setValue(field, val);
			}

			list.add(spr);
		}

		return list;
	}

}

