package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlProjectResourceLink.tableFldConstants;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenTlWorkflowdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, wrkm_keyid, stage, type, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public GenTlWorkflowdtl()
	{
		saveArray = new  Object [ 12 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWrkdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWrkdKeyid(String wrkdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wrkdKeyid;
	}

	public String getWrkdWrkmKeyid() {
		return (String) saveArray[ tableFldConstants.wrkm_keyid.ordinal() ];
	}

	public void setWrkdWrkmKeyid(String wrkdWrkmKeyid) {
		saveArray[ tableFldConstants.wrkm_keyid.ordinal() ] = wrkdWrkmKeyid;
	}

	public String getWrkdStage() {
		return (String) saveArray[ tableFldConstants.stage.ordinal() ];
	}

	public void setWrkdStage(String wrkdStage) {
		saveArray[ tableFldConstants.stage.ordinal() ] = wrkdStage;
	}

	public String getWrkdType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setWrkdType(String wrkdType) {
		saveArray[ tableFldConstants.type.ordinal() ] = wrkdType;
	}

	public String getWrkdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWrkdTempfield1(String wrkdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wrkdTempfield1;
	}

	public String getWrkdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWrkdTempfield2(String wrkdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wrkdTempfield2;
	}

	public String getWrkdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWrkdTempfield3(String wrkdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wrkdTempfield3;
	}

	public String getWrkdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setWrkdTempfield4(String wrkdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = wrkdTempfield4;
	}

	public String getWrkdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWrkdActive(String wrkdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wrkdActive;
	}

	public String getWrkdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWrkdCreatedby(String wrkdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wrkdCreatedby;
	}

	public String getWrkdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWrkdCreatedon(String wrkdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wrkdCreatedon;
	}

	public String getWrkdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWrkdModifiedon(String wrkdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wrkdModifiedon;
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
	            if(field.name() == "keyid" && val == null) {
	            	sb.append("null");
	            }else if (val == null) {
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
  
  public static String toJsonManualList(List<GenTlWorkflowdtl> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
  

  

  
  public static GenTlWorkflowdtl fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  GenTlWorkflowdtl wfd = new GenTlWorkflowdtl();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        wfd.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return wfd;
	}
  
  public static List<GenTlWorkflowdtl> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<GenTlWorkflowdtl> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        GenTlWorkflowdtl wfd = new GenTlWorkflowdtl();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            wfd.setValue(field, val);
	        }

	        list.add(wfd);
	    }

	    return list;
	}

}

