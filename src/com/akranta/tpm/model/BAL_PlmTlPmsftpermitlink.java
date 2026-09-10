package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PlmTlPmsftpermitlink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		pmstandardid, sftpermitid, sftpermittype, tempfield1
	}

	public BAL_PlmTlPmsftpermitlink()
	{
		saveArray = new  Object [ 4 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPsplPmstandardid() {
		return (String) saveArray[ tableFldConstants.pmstandardid.ordinal() ];
	}

	public void setPsplPmstandardid(String psplPmstandardid) {
		saveArray[ tableFldConstants.pmstandardid.ordinal() ] = psplPmstandardid;
	}

	public String getPsplSftpermitid() {
		return (String) saveArray[ tableFldConstants.sftpermitid.ordinal() ];
	}

	public void setPsplSftpermitid(String psplSftpermitid) {
		saveArray[ tableFldConstants.sftpermitid.ordinal() ] = psplSftpermitid;
	}

	public String getPsplSftpermittype() {
		return (String) saveArray[ tableFldConstants.sftpermittype.ordinal() ];
	}

	public void setPsplSftpermittype(String psplSftpermittype) {
		saveArray[ tableFldConstants.sftpermittype.ordinal() ] = psplSftpermittype;
	}

	public String getPsplTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPsplTempfield1(String psplTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = psplTempfield1;
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
	            if (field.name() == "pmstandardid" && val == null) {
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

	public static String toJsonManualList(List<BAL_PlmTlPmsftpermitlink> list) {
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

	public static BAL_PlmTlPmsftpermitlink fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    BAL_PlmTlPmsftpermitlink pspl = new BAL_PlmTlPmsftpermitlink();
	    CommonMessage.debugMsg("RAW JSON Response: :" + json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = field.name();

	        CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	        String val = obj.optString(field.name(), null);
	        pspl.setValue(field, val != null && val.equals("null") ? null : val);
	    }
	    return pspl;
	}

	public static List<BAL_PlmTlPmsftpermitlink> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<BAL_PlmTlPmsftpermitlink> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        BAL_PlmTlPmsftpermitlink pspl = new BAL_PlmTlPmsftpermitlink();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            pspl.setValue(field, val);
	        }

	        list.add(pspl);
	    }

	    return list;
	}

}

