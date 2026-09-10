package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_BdmTlCausemst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, code, phenomenaid, remarks, iscausedefined, active
		, createdby, createdon, modifiedon
	}

	public BAL_BdmTlCausemst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	// added by priyanka on 16/07/2026
	public void setValue(tableFldConstants field, Object val) {
	    saveArray[field.ordinal()] = val;
	}

	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}
	// end
	
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	public String getBcsmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBcsmKeyid(String bcsmkeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bcsmkeyid;
	}

	public String getBcsmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setBcsmName(String bcsmname) {
		saveArray[ tableFldConstants.name.ordinal() ] = bcsmname;
	}

	public String getBcsmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setBcsmCode(String bcsmcode) {
		saveArray[ tableFldConstants.code.ordinal() ] = bcsmcode;
	}

	public String getBcsmPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setBcsmPhenomenaid(String bcsmphenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = bcsmphenomenaid;
	}

	public String getBcsmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setBcsmRemarks(String bcsmremarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = bcsmremarks;
	}

	public String getBcsmIscausedefined() {
		return (String) saveArray[ tableFldConstants.iscausedefined.ordinal() ];
	}

	public void setBcsmIscausedefined(String bcsmiscausedefined) {
		saveArray[ tableFldConstants.iscausedefined.ordinal() ] = bcsmiscausedefined;
	}

	public String getBcsmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBcsmActive(String bcsmactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bcsmactive;
	}

	public String getBcsmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBcsmCreatedby(String bcsmcreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bcsmcreatedby;
	}

	public String getBcsmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBcsmCreatedon(String bcsmcreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bcsmcreatedon;
	}

	public String getBcsmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBcsmModifiedon(String bcsmmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bcsmmodifiedon;
	}
	
	// added by priyanka on 16/07/2026
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
	            String fieldName = field.name();
	            if ((fieldName.equals("keyid") || fieldName.equals("code")) && val == null) {
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

	public static BAL_BdmTlCausemst fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    BAL_BdmTlCausemst bcsm = new BAL_BdmTlCausemst();
	    CommonMessage.debugMsg("RAW JSON Response: :" + json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = field.name();

	        CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	        String val = obj.optString(field.name(), null);
	        //bcsm.setValue(field, val != null && val.equals("null") ? null : val);
	        if (val == null
	                || "null".equalsIgnoreCase(val)
	                || "{}".equals(val)
	                || "[object Object]".equalsIgnoreCase(val)) {
	            val = "";
	        }
	        bcsm.setValue(field, val);
	    }
	    return bcsm;
	}

	public static List<BAL_BdmTlCausemst> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<BAL_BdmTlCausemst> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        BAL_BdmTlCausemst bcsm = new BAL_BdmTlCausemst();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            //Object valueObj = obj.opt(key);
	            //String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
	            String val = obj.optString(field.name(), null);

	            //bcsm.setValue(field, val);
	            if (val == null
	                    || "null".equalsIgnoreCase(val)
	                    || "{}".equals(val)
	                    || "[object Object]".equalsIgnoreCase(val)) {
	                val = "";
	            }

	            bcsm.setValue(field, val);
	        }

	        list.add(bcsm);
	    }

	    return list;
	}

	public static String toJsonManualList(List<BAL_BdmTlCausemst> list) {
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
	// end
	
	

}

