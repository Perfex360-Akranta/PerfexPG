package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BdmTlYyproblemattbymst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, wwms_keyid, empm_keyid, tempfield1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public BdmTlYyproblemattbymst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWwpaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWwpaKeyid(String wwpaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wwpaKeyid;
	}

	public String getWwpaWwmsKeyid() {
		return (String) saveArray[ tableFldConstants.wwms_keyid.ordinal() ];
	}

	public void setWwpaWwmsKeyid(String wwpaWwmsKeyid) {
		saveArray[ tableFldConstants.wwms_keyid.ordinal() ] = wwpaWwmsKeyid;
	}

	public String getWwpaEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setWwpaEmpmKeyid(String wwpaEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = wwpaEmpmKeyid;
	}

	public String getWwpaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWwpaTempfield1(String wwpaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wwpaTempfield1;
	}

	public String getWwpaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWwpaTempfield2(String wwpaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wwpaTempfield2;
	}

	public String getWwpaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWwpaTempfield3(String wwpaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wwpaTempfield3;
	}

	public String getWwpaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWwpaActive(String wwpaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wwpaActive;
	}

	public String getWwpaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWwpaCreatedby(String wwpaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wwpaCreatedby;
	}

	public String getWwpaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWwpaCreatedon(String wwpaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wwpaCreatedon;
	}

	public String getWwpaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWwpaModifiedon(String wwpaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wwpaModifiedon;
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

	public static String toJsonManualList(List<BdmTlYyproblemattbymst> list) {
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

	public static List<BdmTlYyproblemattbymst> fromJsonList(String json) {
	    List<BdmTlYyproblemattbymst> list = new ArrayList<>();
	    JSONArray arr = JSONArray.fromObject(json);
	    for (int i = 0; i < arr.length(); i++) {
	        list.add(fromJson(arr.getJSONObject(i).toString()));
	    }
	    return list;
	}

	public static BdmTlYyproblemattbymst fromJson(String json) {
	    JSONObject obj = JSONObject.fromObject(json);
	    BdmTlYyproblemattbymst mst = new BdmTlYyproblemattbymst();
	    for (tableFldConstants field : tableFldConstants.values()) {
	        String val = obj.optString(field.name(), null);
	        mst.saveArray[field.ordinal()] = val != null && val.equals("null") ? null : val;
	    }
	    return mst;
	}

}

