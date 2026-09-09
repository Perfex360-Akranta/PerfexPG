package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class PcsTlLosscapture {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, date, shiftid, fromtime, totime, losstime, lossreason
		, lossid, tradeid, prod_impact, prod_imp_qty, lossdescription, pldetailsid
		, Equipment,tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public PcsTlLosscapture()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPlosKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPlosKeyid(String plosKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = plosKeyid;
	}

	public String getPlosFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPlosFlid(String plosFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = plosFlid;
	}

	public String getPlosDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPlosDate(String plosDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = plosDate;
	}

	public String getPlosShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setPlosShiftid(String plosShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = plosShiftid;
	}

	public String getPlosFromtime() {
		return (String) saveArray[ tableFldConstants.fromtime.ordinal() ];
	}

	public void setPlosFromtime(String plosFromtime) {
		saveArray[ tableFldConstants.fromtime.ordinal() ] = plosFromtime;
	}

	public String getPlosTotime() {
		return (String) saveArray[ tableFldConstants.totime.ordinal() ];
	}

	public void setPlosTotime(String plosTotime) {
		saveArray[ tableFldConstants.totime.ordinal() ] = plosTotime;
	}

	public String getPlosLosstime() {
		return (String) saveArray[ tableFldConstants.losstime.ordinal() ];
	}

	public void setPlosLosstime(String plosLosstime) {
		saveArray[ tableFldConstants.losstime.ordinal() ] = plosLosstime;
	}

	public String getPlosLossreason() {
		return (String) saveArray[ tableFldConstants.lossreason.ordinal() ];
	}

	public void setPlosLossreason(String plosLossreason) {
		saveArray[ tableFldConstants.lossreason.ordinal() ] = plosLossreason;
	}

	public String getPlosLossid() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setPlosLossid(String plosLossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = plosLossid;
	}

	public String getPlosTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setPlosTradeid(String plosTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = plosTradeid;
	}

	public String getPlosProdImpact() {
		return (String) saveArray[ tableFldConstants.prod_impact.ordinal() ];
	}

	public void setPlosProdImpact(String plosProdImpact) {
		saveArray[ tableFldConstants.prod_impact.ordinal() ] = plosProdImpact;
	}

	public String getPlosProdImpQty() {
		return (String) saveArray[ tableFldConstants.prod_imp_qty.ordinal() ];
	}

	public void setPlosProdImpQty(String plosProdImpQty) {
		saveArray[ tableFldConstants.prod_imp_qty.ordinal() ] = plosProdImpQty;
	}

	public String getPLosLossdescription() {
		return (String) saveArray[ tableFldConstants.lossdescription.ordinal() ];
	}

	public void setPlosLossdescription(String plosLossdescription) {
		saveArray[ tableFldConstants.lossdescription.ordinal() ] = plosLossdescription;
	}

	public String getPlospldetailsid() {
		return (String) saveArray[ tableFldConstants.pldetailsid.ordinal() ];
	}

	public void setPlosPldetailsid(String plosPldetailsid) {
		saveArray[ tableFldConstants.pldetailsid.ordinal() ] = plosPldetailsid;
	}

	public String getPlosEquipment() {
		return (String) saveArray[ tableFldConstants.Equipment.ordinal() ];
	}

	public void setPlosEquipment(String plosEquipment) {
		saveArray[ tableFldConstants.Equipment.ordinal() ] = plosEquipment;
	}

	public String getPlosTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPlosTempfield3(String detecteedby) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = detecteedby;
	}

	public String getPlosTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPlosTempfield4(String plosTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = plosTempfield4;
	}

	public String getPlosTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPlosTempfield5(String plosTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = plosTempfield5;
	}

	public String getPlosActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPlosActive(String plosActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = plosActive;
	}

	public String getPlosCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPlosCreatedby(String plosCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = plosCreatedby;
	}

	public String getPlosCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPlosCreatedon(String plosCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = plosCreatedon;
	}

	public String getPlosModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPlosModifiedon(String plosModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = plosModifiedon;
	}
	
	// -----------------------------------
	// JSON helpers (Abnormality style)
	// -----------------------------------
	private static String jsonKey(tableFldConstants field) {
	    // currently 1:1 mapping (snake_case keys exactly match enum names)
	    return field.name();
	}

	private static String escapeJson(String s) {
	    if (s == null) return null;
	    return s.replace("\\", "\\\\")
	            .replace("\"", "\\\"")
	            .replace("\r", "\\r")
	            .replace("\n", "\\n")
	            .replace("\t", "\\t");
	}

	// ✅ Needed for fromJson()/fromJsonList()
	public void setValue(tableFldConstants field, String value) {
	    if (value != null && "null".equalsIgnoreCase(value)) value = null;
	    saveArray[field.ordinal()] = value;
	}

	// -----------------------------------
	// toJsonManual (same pattern as Abnormality)
	// -----------------------------------
	public String toJsonManual() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");

	    boolean first = true;

	    for (tableFldConstants field : tableFldConstants.values()) {
	        int index = field.ordinal();
	        if (index >= saveArray.length) continue;

	        if (!first) sb.append(",");

	        String key = jsonKey(field);
	        sb.append("\"").append(key).append("\":");

	        Object val = saveArray[index];

	        // ✅ keyid must be null on insert
	        if (field == tableFldConstants.keyid && val == null) {
	            sb.append("null");
	        }
	        // ✅ all other null -> "{}" (your convention)
	        else if (val == null) {
	            sb.append("\"{}\"");
	        }
	        // ✅ normal value -> quoted string
	        else {
	            sb.append("\"").append(escapeJson(val.toString())).append("\"");
	        }

	        first = false;
	    }

	    sb.append("}");
	    return sb.toString();
	}

	// -----------------------------------
	// fromJson (same as Abnormality style)
	// -----------------------------------
	public static PcsTlLosscapture fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    PcsTlLosscapture p = new PcsTlLosscapture();

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = jsonKey(field);

	        Object valueObj = obj.opt(key);
	        String val = (valueObj == null || "null".equalsIgnoreCase(valueObj.toString()))
	                     ? null
	                     : valueObj.toString();

	        p.setValue(field, val);
	    }

	    return p;
	}

	// -----------------------------------
	// fromJsonList (same as Abnormality style)
	// -----------------------------------
	public static List<PcsTlLosscapture> fromJsonList(String json) {
	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<PcsTlLosscapture> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {
	        JSONObject obj = jsonArray.getJSONObject(i);

	        PcsTlLosscapture p = new PcsTlLosscapture();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = jsonKey(field);

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equalsIgnoreCase(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            p.setValue(field, val);
	        }

	        list.add(p);
	    }

	    return list;
	}

	// -----------------------------------
	// toJsonManualList (same as Abnormality style)
	// -----------------------------------
	public static String toJsonManualList(List<PcsTlLosscapture> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}


}

