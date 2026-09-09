package com.akranta.tpm.model;
import com.akranta.tpm.utils.CommonMessage;
import java.util.ArrayList;
import java.util.List;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenTlWorkflowInfo {

	private  Object [] saveArray = null;  
	private String nextEmpId =null;
	private Character enable = null;
	public enum   tableFldConstants
	{
		keyid, wrml_keyid, ref_id, ref_type, role_id, status, employee_id
		, date, remarks, wrkd_keyid, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, createdon, modifiedon
	}

	public GenTlWorkflowInfo()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWrinKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWrinKeyid(String wrinKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wrinKeyid;
	}

	public String getWrinWrmlKeyid() {
		return (String) saveArray[ tableFldConstants.wrml_keyid.ordinal() ];
	}

	public void setWrinWrmlKeyid(String wrinWrmlKeyid) {
		saveArray[ tableFldConstants.wrml_keyid.ordinal() ] = wrinWrmlKeyid;
	}

	public String getWrinRefId() {
		return (String) saveArray[ tableFldConstants.ref_id.ordinal() ];
	}

	public void setWrinRefId(String wrinRefId) {
		saveArray[ tableFldConstants.ref_id.ordinal() ] = wrinRefId;
	}

	public String getWrinRefType() {
		return (String) saveArray[ tableFldConstants.ref_type.ordinal() ];
	}

	public void setWrinRefType(String wrinRefType) {
		saveArray[ tableFldConstants.ref_type.ordinal() ] = wrinRefType;
	}

	public String getWrinRoleId() {
		return (String) saveArray[ tableFldConstants.role_id.ordinal() ];
	}

	public void setWrinRoleId(String wrinRoleId) {
		saveArray[ tableFldConstants.role_id.ordinal() ] = wrinRoleId;
	}

	public String getWrinStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setWrinStatus(String wrinStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = wrinStatus;
	}

	public String getWrinEmployeeId() {
		return (String) saveArray[ tableFldConstants.employee_id.ordinal() ];
	}

	public void setWrinEmployeeId(String wrinEmployeeId) {
		saveArray[ tableFldConstants.employee_id.ordinal() ] = wrinEmployeeId;
	}

	public String getWrinDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setWrinDate(String wrinDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = wrinDate;
	}

	public String getWrinRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setWrinRemarks(String wrinRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = wrinRemarks;
	}

	public String getWrinWrkdKeyid() {
		return (String) saveArray[ tableFldConstants.wrkd_keyid.ordinal() ];
	}

	public void setWrinWrkdKeyid(String wrinWrkdKeyid) {
		saveArray[ tableFldConstants.wrkd_keyid.ordinal() ] = wrinWrkdKeyid;
	}

	public String getWrinTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWrinTempfield2(String wrinTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wrinTempfield2;
	}

	public String getWrinTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWrinTempfield3(String wrinTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wrinTempfield3;
	}

	public String getWrinTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setWrinTempfield4(String wrinTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = wrinTempfield4;
	}

	public String getWrinTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setWrinTempfield5(String wrinTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = wrinTempfield5;
	}

	public String getWrinCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWrinCreatedby(String wrinCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wrinCreatedby;
	}

	public String getWrinCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWrinCreatedon(String wrinCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wrinCreatedon;
	}

	public String getWrinModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWrinModifiedon(String wrinModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wrinModifiedon;
	}

	public void setEnable(Character enable) {
		this.enable = enable;
	}

	public Character getEnable() {
		return enable;
	}

	public String getNextEmpId() {
		return nextEmpId;
	}

	public void setNextEmpId(String nextEmpId) {
		this.nextEmpId = nextEmpId;
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
  
  public static String toJsonManualList(List<GenTlWorkflowInfo> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
  

  
  public static GenTlWorkflowInfo fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  GenTlWorkflowInfo wfi = new GenTlWorkflowInfo();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        wfi.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return wfi;
	}
  
  public static List<GenTlWorkflowInfo> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<GenTlWorkflowInfo> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        GenTlWorkflowInfo wfi = new GenTlWorkflowInfo();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            wfi.setValue(field, val);
	        }

	        list.add(wfi);
	    }

	    return list;
	}

}

