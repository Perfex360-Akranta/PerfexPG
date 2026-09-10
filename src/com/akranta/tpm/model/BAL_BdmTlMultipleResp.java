package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_BdmTlMultipleResp {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, refid, resp_empid, tempfield, active
	}

	public BAL_BdmTlMultipleResp()
	{
		saveArray = new  Object [ 5 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getBdrsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBdrsKeyid(String bdrsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bdrsKeyid;
	}

	public String getBdrsRefid() {
		return (String) saveArray[ tableFldConstants.refid.ordinal() ];
	}

	public void setBdrsRefid(String bdrsRefid) {
		saveArray[ tableFldConstants.refid.ordinal() ] = bdrsRefid;
	}

	public String getBdrsRespEmpid() {
		return (String) saveArray[ tableFldConstants.resp_empid.ordinal() ];
	}

	public void setBdrsRespEmpid(String bdrsRespEmpid) {
		saveArray[ tableFldConstants.resp_empid.ordinal() ] = bdrsRespEmpid;
	}

	public String getBdrsTempfield() {
		return (String) saveArray[ tableFldConstants.tempfield.ordinal() ];
	}

	public void setBdrsTempfield(String bdrsTempfield) {
		saveArray[ tableFldConstants.tempfield.ordinal() ] = bdrsTempfield;
	}

	public String getBdrsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBdrsActive(String bdrsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bdrsActive;
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
	            if (field == tableFldConstants.keyid && val == null) {
	                sb.append("null");          // null keyid  →  INSERT on Spring side
	            } else if (val == null) {
	                sb.append("\"{}\"");        // other nulls →  empty placeholder
	            } else {
	                sb.append("\"")
	                  .append(val.toString().replace("\\", "\\\\")
	                                        .replace("\"", "\\\""))
	                  .append("\"");
	            }
	            first = false;
	        }
	    }
	    sb.append("}");
	    return sb.toString();
	}

	/**
	 * Deserialises a JSON string (Spring response "resp" object) back into a
	 * BAL_BdmTlMultipleResp instance.
	 */
	public static BAL_BdmTlMultipleResp fromJson(String json) {
	    net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
	    BAL_BdmTlMultipleResp resp = new BAL_BdmTlMultipleResp();
	    for (tableFldConstants field : tableFldConstants.values()) {
	        String val = obj.optString(field.name(), null);
	        // Treat JSON null / literal "null" / "{}" placeholder as empty string
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	            val = "";
	        }
	        resp.saveArray[field.ordinal()] = val;
	    }
	    return resp;
	}
	
	public static List<BAL_BdmTlMultipleResp> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BAL_BdmTlMultipleResp> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			BAL_BdmTlMultipleResp resp  = new BAL_BdmTlMultipleResp();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				Object valueObj = obj.opt(key);
				String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
				
				resp .setValue(field, val);
			}

			list.add(resp);
		}

		return list;
	}
	
	public static String toJsonManualList(List<BAL_BdmTlMultipleResp> list) {
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

