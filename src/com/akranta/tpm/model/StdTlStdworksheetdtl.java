package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.StdTlStdworksheetmst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class StdTlStdworksheetdtl {
	
	

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, stws_keyid, majorsteps, typeofmanpower, mantime, processtime
		, waittime, traveltime, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, active, createdon, modifiedon
	}

	public StdTlStdworksheetdtl()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getStwdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setStwdKeyid(String stwdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = stwdKeyid;
	}

	public String getStwdStwsKeyid() {
		return (String) saveArray[ tableFldConstants.stws_keyid.ordinal() ];
	}

	public void setStwdStwsKeyid(String stwdStwsKeyid) {
		saveArray[ tableFldConstants.stws_keyid.ordinal() ] = stwdStwsKeyid;
	}

	public String getStwdMajorsteps() {
		return (String) saveArray[ tableFldConstants.majorsteps.ordinal() ];
	}

	public void setStwdMajorsteps(String stwdMajorsteps) {
		saveArray[ tableFldConstants.majorsteps.ordinal() ] = stwdMajorsteps;
	}

	public String getStwdTypeofmanpower() {
		return (String) saveArray[ tableFldConstants.typeofmanpower.ordinal() ];
	}

	public void setStwdTypeofmanpower(String stwdTypeofmanpower) {
		saveArray[ tableFldConstants.typeofmanpower.ordinal() ] = stwdTypeofmanpower;
	}

	public String getStwdMantime() {
		return (String) saveArray[ tableFldConstants.mantime.ordinal() ];
	}

	public void setStwdMantime(String stwdMantime) {
		saveArray[ tableFldConstants.mantime.ordinal() ] = stwdMantime;
	}

	public String getStwdProcesstime() {
		return (String) saveArray[ tableFldConstants.processtime.ordinal() ];
	}

	public void setStwdProcesstime(String stwdProcesstime) {
		saveArray[ tableFldConstants.processtime.ordinal() ] = stwdProcesstime;
	}

	public String getStwdWaittime() {
		return (String) saveArray[ tableFldConstants.waittime.ordinal() ];
	}

	public void setStwdWaittime(String stwdWaittime) {
		saveArray[ tableFldConstants.waittime.ordinal() ] = stwdWaittime;
	}

	public String getStwdTraveltime() {
		return (String) saveArray[ tableFldConstants.traveltime.ordinal() ];
	}

	public void setStwdTraveltime(String stwdTraveltime) {
		saveArray[ tableFldConstants.traveltime.ordinal() ] = stwdTraveltime;
	}

	public String getStwdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setStwdTempfield1(String stwdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = stwdTempfield1;
	}

	public String getStwdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setStwdTempfield2(String stwdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = stwdTempfield2;
	}

	public String getStwdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setStwdTempfield3(String stwdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = stwdTempfield3;
	}

	public String getStwdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setStwdTempfield4(String stwdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = stwdTempfield4;
	}

	public String getStwdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setStwdTempfield5(String stwdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = stwdTempfield5;
	}

	public String getStwdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setStwdCreatedby(String stwdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = stwdCreatedby;
	}

	public String getStwdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setStwdActive(String stwdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = stwdActive;
	}

	public String getStwdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setStwdCreatedon(String stwdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = stwdCreatedon;
	}

	public String getStwdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setStwdModifiedon(String stwdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = stwdModifiedon;
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
  
  public static String toJsonManualList(List<StdTlStdworksheetdtl> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
  

  
  public static StdTlStdworksheetdtl fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  StdTlStdworksheetdtl sws = new StdTlStdworksheetdtl();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        sws.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return sws;
	}
  
  public static List<StdTlStdworksheetdtl> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<StdTlStdworksheetdtl> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        StdTlStdworksheetdtl sws = new StdTlStdworksheetdtl();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            sws.setValue(field, val);
	        }

	        list.add(sws);
	    }

	    return list;
	}

}

