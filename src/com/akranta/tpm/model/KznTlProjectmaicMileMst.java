package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlActionplanmst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KznTlProjectmaicMileMst {

	private  Object [] saveArray = null;  
	private String type;
	private String historykeyid;
	private List<KznTlProjectmaicMileDtl> milestonedetail;
	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, flid, stages, fromdate, todate, empm_keyid
		, status, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public KznTlProjectmaicMileMst()
	{
		saveArray = new  Object [ 17 ];
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKmmmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKmmmKeyid(String kmmmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kmmmKeyid;
	}

	public String getKmmmKzpmKeyid() {
		return (String) saveArray[ tableFldConstants.kzpm_keyid.ordinal() ];
	}

	public void setKmmmKzpmKeyid(String kmmmKzpmKeyid) {
		saveArray[ tableFldConstants.kzpm_keyid.ordinal() ] = kmmmKzpmKeyid;
	}

	public String getKmmmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setKmmmFlid(String kmmmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = kmmmFlid;
	}

	public String getKmmmStages() {
		return (String) saveArray[ tableFldConstants.stages.ordinal() ];
	}

	public void setKmmmStages(String kmmmStages) {
		saveArray[ tableFldConstants.stages.ordinal() ] = kmmmStages;
	}

	public String getKmmmFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setKmmmFromdate(String kmmmFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = kmmmFromdate;
	}

	public String getKmmmTodate() {
		return (String) saveArray[ tableFldConstants.todate.ordinal() ];
	}

	public void setKmmmTodate(String kmmmTodate) {
		saveArray[ tableFldConstants.todate.ordinal() ] = kmmmTodate;
	}

	public String getKmmmEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setKmmmEmpmKeyid(String kmmmEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = kmmmEmpmKeyid;
	}

	public String getKmmmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setKmmmStatus(String kmmmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = kmmmStatus;
	}

	public String getKmmmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKmmmTempfield1(String kmmmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kmmmTempfield1;
	}

	public String getKmmmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKmmmTempfield2(String kmmmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kmmmTempfield2;
	}

	public String getKmmmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKmmmTempfield3(String kmmmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kmmmTempfield3;
	}

	public String getKmmmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKmmmTempfield4(String kmmmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kmmmTempfield4;
	}

	public String getKmmmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKmmmTempfield5(String kmmmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kmmmTempfield5;
	}

	public String getKmmmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKmmmCreatedby(String kmmmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kmmmCreatedby;
	}

	public String getKmmmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKmmmActive(String kmmmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kmmmActive;
	}

	public String getKmmmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKmmmCreatedon(String kmmmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kmmmCreatedon;
	}

	public String getKmmmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKmmmModifiedon(String kmmmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kmmmModifiedon;
	}

	public void setMilestonedetail(List<KznTlProjectmaicMileDtl> milestonedetail) {
		this.milestonedetail = milestonedetail;
	}

	public List<KznTlProjectmaicMileDtl> getMilestonedetail() {
		return milestonedetail;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setHistorykeyid(String historykeyid) {
		this.historykeyid = historykeyid;
	}
	public String getHistorykeyid() {
		return historykeyid;
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

	public static String toJsonManualList(List<KznTlProjectmaicMileMst> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}




	public static KznTlProjectmaicMileMst fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlProjectmaicMileMst pmm = new KznTlProjectmaicMileMst();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        pmm.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return pmm;
	}

	public static List<KznTlProjectmaicMileMst> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KznTlProjectmaicMileMst> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KznTlProjectmaicMileMst pmm = new KznTlProjectmaicMileMst();

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

