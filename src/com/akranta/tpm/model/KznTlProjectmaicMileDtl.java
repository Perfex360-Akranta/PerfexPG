package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlProjectmaicMileMst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KznTlProjectmaicMileDtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, kmmm_keyid, milestone, description, targetdate, empm_keyid
		, status, remarks, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, active, createdon, modifiedon,kzpm_keyid
	}

	public KznTlProjectmaicMileDtl()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKmmdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKmmdKeyid(String kmmdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kmmdKeyid;
	}

	public String getKmmdKmmmKeyid() {
		return (String) saveArray[ tableFldConstants.kmmm_keyid.ordinal() ];
	}

	public void setKmmdKmmmKeyid(String kmmdKmmmKeyid) {
		saveArray[ tableFldConstants.kmmm_keyid.ordinal() ] = kmmdKmmmKeyid;
	}

	public String getKmmdMilestone() {
		return (String) saveArray[ tableFldConstants.milestone.ordinal() ];
	}

	public void setKmmdMilestone(String kmmdMilestone) {
		saveArray[ tableFldConstants.milestone.ordinal() ] = kmmdMilestone;
	}

	public String getKmmdDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setKmmdDescription(String kmmdDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = kmmdDescription;
	}

	public String getKmmdTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setKmmdTargetdate(String kmmdTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = kmmdTargetdate;
	}

	public String getKmmdEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setKmmdEmpmKeyid(String kmmdEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = kmmdEmpmKeyid;
	}

	public String getKmmdStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setKmmdStatus(String kmmdStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = kmmdStatus;
	}

	public String getKmmdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setKmmdRemarks(String kmmdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = kmmdRemarks;
	}

	public String getKmmdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKmmdTempfield1(String kmmdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kmmdTempfield1;
	}

	public String getKmmdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKmmdTempfield2(String kmmdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kmmdTempfield2;
	}

	public String getKmmdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKmmdTempfield3(String kmmdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kmmdTempfield3;
	}

	public String getKmmdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKmmdTempfield4(String kmmdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kmmdTempfield4;
	}

	public String getKmmdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKmmdTempfield5(String kmmdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kmmdTempfield5;
	}

	public String getKmmdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKmmdCreatedby(String kmmdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kmmdCreatedby;
	}

	public String getKmmdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKmmdActive(String kmmdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kmmdActive;
	}

	public String getKmmdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKmmdCreatedon(String kmmdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kmmdCreatedon;
	}

	public String getKmmdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKmmdModifiedon(String kmmdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kmmdModifiedon;
	}
	public String getKmmdKzpmKeyid() {
		return (String) saveArray[ tableFldConstants.kzpm_keyid.ordinal() ];
	}

	public void setKmmdKzpmKeyid(String kmmdkzpmkeyid) {
		saveArray[ tableFldConstants.kzpm_keyid.ordinal() ] = kmmdkzpmkeyid;
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

	public static String toJsonManualList(List<KznTlProjectmaicMileDtl> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}




	public static KznTlProjectmaicMileDtl fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlProjectmaicMileDtl pmm = new KznTlProjectmaicMileDtl();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        pmm.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return pmm;
	}

	public static List<KznTlProjectmaicMileDtl> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KznTlProjectmaicMileDtl> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KznTlProjectmaicMileDtl pmm = new KznTlProjectmaicMileDtl();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            pmm.setValue(field, val);
	        }

	        list.add(pmm);
	    }

	    return list;
	}

}

