package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BdmTlYydonebymst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, wwms_keyid, empm_keyid, tempfield1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public BdmTlYydonebymst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}


	public String getWwdbKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWwdbKeyid(String wwdbKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wwdbKeyid;
	}

	public String getWwdbWwmsKeyid() {
		return (String) saveArray[ tableFldConstants.wwms_keyid.ordinal() ];
	}

	public void setWwdbWwmsKeyid(String wwdbWwmsKeyid) {
		saveArray[ tableFldConstants.wwms_keyid.ordinal() ] = wwdbWwmsKeyid;
	}

	public String getWwdbEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setWwdbEmpmKeyid(String wwdbEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = wwdbEmpmKeyid;
	}

	public String getWwdbTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWwdbTempfield1(String wwdbTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wwdbTempfield1;
	}

	public String getWwdbTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWwdbTempfield2(String wwdbTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wwdbTempfield2;
	}

	public String getWwdbTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWwdbTempfield3(String wwdbTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wwdbTempfield3;
	}

	public String getWwdbActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWwdbActive(String wwdbActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wwdbActive;
	}

	public String getWwdbCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWwdbCreatedby(String wwdbCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wwdbCreatedby;
	}

	public String getWwdbCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWwdbCreatedon(String wwdbCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wwdbCreatedon;
	}

	public String getWwdbModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWwdbModifiedon(String wwdbModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wwdbModifiedon;
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

	public static String toJsonManualList(List<BdmTlYydonebymst> list) {
	    if (list == null || list.isEmpty()) {
	        return "[]";
	    }
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }
	    sb.append("]");
	    return sb.toString();
	}

	public static List<BdmTlYydonebymst> fromJsonList(String json) {
	    List<BdmTlYydonebymst> list = new ArrayList<>();
	    JSONArray arr = JSONArray.fromObject(json);
	    for (int i = 0; i < arr.length(); i++) {
	        list.add(fromJson(arr.getJSONObject(i).toString()));
	    }
	    return list;
	}

	public static BdmTlYydonebymst fromJson(String json) {
	    JSONObject obj = JSONObject.fromObject(json);
	    BdmTlYydonebymst mst = new BdmTlYydonebymst();
	    for (tableFldConstants field : tableFldConstants.values()) {
	        String val = obj.optString(field.name(), null);
	        mst.saveArray[field.ordinal()] = val != null && val.equals("null") ? null : val;
	    }
	    return mst;
	}

}

