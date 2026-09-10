package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BalPlmTlMultipleResp {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, refid, alloted_empid, completed_empid, active
	}

	public BalPlmTlMultipleResp()
	{
		saveArray = new  Object [ 5 ];
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPmrsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPmrsKeyid(String pmrsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pmrsKeyid;
	}

	public String getPmrsRefid() {
		return (String) saveArray[ tableFldConstants.refid.ordinal() ];
	}

	public void setPmrsRefid(String pmrsRefid) {
		saveArray[ tableFldConstants.refid.ordinal() ] = pmrsRefid;
	}

	public String getBdrsRespEmpid() {
		return (String) saveArray[ tableFldConstants.alloted_empid.ordinal() ];
	}

	public void setBdrsRespEmpid(String pmrsAllotedEmpid) {
		saveArray[ tableFldConstants.alloted_empid.ordinal() ] = pmrsAllotedEmpid;
	}

	public String getPmrsCompletedEmpid() {
		return (String) saveArray[ tableFldConstants.completed_empid.ordinal() ];
	}

	public void setPmrsCompletedEmpid(String pmrsCompletedEmpid) {
		saveArray[ tableFldConstants.completed_empid.ordinal() ] = pmrsCompletedEmpid;
	}

	public String getPmrsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPmrsActive(String pmrsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pmrsActive;
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
				if (field.name() == "keyid" && val == null) {
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

	public static BalPlmTlMultipleResp fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		BalPlmTlMultipleResp dtl = new BalPlmTlMultipleResp();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
			dtl.setValue(field, val);

		}
		return dtl;
	}

	public static List<BalPlmTlMultipleResp> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BalPlmTlMultipleResp> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			BalPlmTlMultipleResp dtl = new BalPlmTlMultipleResp();

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
	
	public static String toJsonManualList(List<BalPlmTlMultipleResp> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (int i = 0; i < list.size(); i++) {
			if (i > 0)
				sb.append(",");
			sb.append(list.get(i).toJsonManual());
		}

		sb.append("]");
		return sb.toString();
	}

}

