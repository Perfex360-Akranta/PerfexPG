package com.akranta.tpm.model;
import java.util.ArrayList;

import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PlmTlToolsdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, standardid, toolid, active, createdby, createdon, modifiedon
	}

	public BAL_PlmTlToolsdtl()
	{
		saveArray = new  Object [ 7 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPtldKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPtldKeyid(String ptldKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ptldKeyid;
	}

	public String getPtldStandardid() {
		return (String) saveArray[ tableFldConstants.standardid.ordinal() ];
	}

	public void setPtldStandardid(String ptldStandardid) {
		saveArray[ tableFldConstants.standardid.ordinal() ] = ptldStandardid;
	}

	public String getPtldToolid() {
		return (String) saveArray[ tableFldConstants.toolid.ordinal() ];
	}

	public void setPtldToolid(String ptldToolid) {
		saveArray[ tableFldConstants.toolid.ordinal() ] = ptldToolid;
	}

	public String getPtldActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPtldActive(String ptldActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ptldActive;
	}

	public String getPtldCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPtldCreatedby(String ptldCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ptldCreatedby;
	}

	public String getPtldCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPtldCreatedon(String ptldCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ptldCreatedon;
	}

	public String getPtldModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPtldModifiedon(String ptldModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ptldModifiedon;
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

	public static String toJsonManualList(List<BAL_PlmTlToolsdtl> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }
	    sb.append("]");
	    return sb.toString();
	}

	public void setValue(tableFldConstants field, Object val) {
	    saveArray[field.ordinal()] = val;
	}

	public static BAL_PlmTlToolsdtl fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    BAL_PlmTlToolsdtl ptld = new BAL_PlmTlToolsdtl();
	    CommonMessage.debugMsg("RAW JSON Response: :" + json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = field.name();

	        CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	        String val = obj.optString(field.name(), null);
	        ptld.setValue(field, val != null && val.equals("null") ? null : val);
	    }
	    return ptld;
	}

	public static List<BAL_PlmTlToolsdtl> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<BAL_PlmTlToolsdtl> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        BAL_PlmTlToolsdtl ptld = new BAL_PlmTlToolsdtl();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            ptld.setValue(field, val);
	        }

	        list.add(ptld);
	    }

	    return list;
	}

}

