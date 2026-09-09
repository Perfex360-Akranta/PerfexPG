package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class StdTlStdworksheetmst {

	private  Object [] saveArray = null;  
	
	private  List<StdTlStdworksheetdtl> stdTlStdworksheetdtl;
	

	public enum   tableFldConstants
	{
		keyid, date, by, approvedby, flid, elementid, process, budgetedtime
		, type, cycletime, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public StdTlStdworksheetmst()
	{
		//saveArray = new  Object [ 12 ];
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	

	public String getStwsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setStwsKeyid(String stwsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = stwsKeyid;
	}

	public String getStwsDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setStwsDate(String stwsDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = stwsDate;
	}

	public String getStwsBy() {
		return (String) saveArray[ tableFldConstants.by.ordinal() ];
	}

	public void setStwsBy(String stwsBy) {
		saveArray[ tableFldConstants.by.ordinal() ] = stwsBy;
	}

	public String getStwsApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setStwsApprovedby(String stwsApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = stwsApprovedby;
	}

	public String getStwsFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setStwsFlid(String stwsFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = stwsFlid;
	}

	public String getStwsElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setStwsElementid(String stwsElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = stwsElementid;
	}

	public String getStwsProcess() {
		return (String) saveArray[ tableFldConstants.process.ordinal() ];
	}

	public void setStwsProcess(String stwsProcess) {
		saveArray[ tableFldConstants.process.ordinal() ] = stwsProcess;
	}

	public String getStwsBudgetedtime() {
		return (String) saveArray[ tableFldConstants.budgetedtime.ordinal() ];
	}

	public void setStwsBudgetedtime(String stwsBudgetedtime) {
		saveArray[ tableFldConstants.budgetedtime.ordinal() ] = stwsBudgetedtime;
	}

	public String getStwsType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setStwsType(String stwsType) {
		saveArray[ tableFldConstants.type.ordinal() ] = stwsType;
	}

	public String getStwsCycletime() {
		return (String) saveArray[ tableFldConstants.cycletime.ordinal() ];
	}

	public void setStwsCycletime(String stwsCycletime) {
		saveArray[ tableFldConstants.cycletime.ordinal() ] = stwsCycletime;
	}

	public String getStwsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setStwsTempfield3(String stwsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = stwsTempfield3;
	}

	public String getStwsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setStwsTempfield4(String stwsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = stwsTempfield4;
	}

	public String getStwsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setStwsTempfield5(String stwsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = stwsTempfield5;
	}

	public String getStwsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setStwsCreatedby(String stwsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = stwsCreatedby;
	}

	public String getStwsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setStwsActive(String stwsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = stwsActive;
	}

	public String getStwsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setStwsCreatedon(String stwsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = stwsCreatedon;
	}

	public String getStwsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setStwsModifiedon(String stwsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = stwsModifiedon;
	}

	public void setStdTlStdworksheetdtl(List<StdTlStdworksheetdtl> stdTlStdworksheetdtl) {
		this.stdTlStdworksheetdtl = stdTlStdworksheetdtl;
	}

	public List<StdTlStdworksheetdtl> getstdTlStdworksheetdtl() {
		return stdTlStdworksheetdtl;
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
  
  public static String toJsonManualList(List<StdTlStdworksheetmst> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
  
//  public static AbnTlAbnormality fromJson(String json) {
//	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");
//
//	  JSONObject obj = JSONObject.fromObject(json);
//
//	  AbnTlAbnormality abn = new AbnTlAbnormality();
//	  CommonMessage.debugMsg("RAW JSON Response: :"+json);
//
//	    for (tableFldConstants field : tableFldConstants.values()) {
//	    	String key = field.name();
//	    	
//	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
//	    	Object rawVal = obj.opt(key);  // Returns Object or null if key not found
//	    	String val = null;
//
//	    	if (rawVal != null) {
//	    	    String strVal = rawVal.toString();
//	    	    if (!strVal.equals("{}") && !strVal.equals("-") && !strVal.equalsIgnoreCase("null")) {
//	    	        val = strVal;
//	    	    }
//	    	}
//	    	abn.setValue(field, val);
//	    	
//	    }
//	    return abn;
//	}
  
  public static StdTlStdworksheetmst fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  StdTlStdworksheetmst sws = new StdTlStdworksheetmst();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        sws.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return sws;
	}
  
  public static List<StdTlStdworksheetmst> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<StdTlStdworksheetmst> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        StdTlStdworksheetmst sws = new StdTlStdworksheetmst();

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

