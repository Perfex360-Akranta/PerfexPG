package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class BAL_PlmTlMethodsmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, pmkeyid, activity, instructions, duration, standards, workpermitrequired
		, permittype, safetyinstruction, protectiveequipements, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public BAL_PlmTlMethodsmst()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPmmsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPmmsKeyid(String pmmsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pmmsKeyid;
	}

	public String getPmmsPmkeyid() {
		return (String) saveArray[ tableFldConstants.pmkeyid.ordinal() ];
	}

	public void setPmmsPmkeyid(String pmmsPmkeyid) {
		saveArray[ tableFldConstants.pmkeyid.ordinal() ] = pmmsPmkeyid;
	}

	public String getPmmsActivity() {
		return (String) saveArray[ tableFldConstants.activity.ordinal() ];
	}

	public void setPmmsActivity(String pmmsActivity) {
		saveArray[ tableFldConstants.activity.ordinal() ] = pmmsActivity;
	}

	public String getPmmsInstructions() {
		return (String) saveArray[ tableFldConstants.instructions.ordinal() ];
	}

	public void setPmmsInstructions(String pmmsInstructions) {
		saveArray[ tableFldConstants.instructions.ordinal() ] = pmmsInstructions;
	}

	public String getPmmsDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setPmmsDuration(String pmmsDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = pmmsDuration;
	}

	public String getPmmsStandards() {
		return (String) saveArray[ tableFldConstants.standards.ordinal() ];
	}

	public void setPmmsStandards(String pmmsStandards) {
		saveArray[ tableFldConstants.standards.ordinal() ] = pmmsStandards;
	}

	public String getPmmsWorkpermitrequired() {
		return (String) saveArray[ tableFldConstants.workpermitrequired.ordinal() ];
	}

	public void setPmmsWorkpermitrequired(String pmmsWorkpermitrequired) {
		saveArray[ tableFldConstants.workpermitrequired.ordinal() ] = pmmsWorkpermitrequired;
	}

	public String getPmmsPermittype() {
		return (String) saveArray[ tableFldConstants.permittype.ordinal() ];
	}

	public void setPmmsPermittype(String pmmsPermittype) {
		saveArray[ tableFldConstants.permittype.ordinal() ] = pmmsPermittype;
	}

	public String getPmmsSafetyinstruction() {
		return (String) saveArray[ tableFldConstants.safetyinstruction.ordinal() ];
	}

	public void setPmmsSafetyinstruction(String pmmsSafetyinstruction) {
		saveArray[ tableFldConstants.safetyinstruction.ordinal() ] = pmmsSafetyinstruction;
	}

	public String getPmmsProtectiveequipements() {
		return (String) saveArray[ tableFldConstants.protectiveequipements.ordinal() ];
	}

	public void setPmmsProtectiveequipements(String pmmsProtectiveequipements) {
		saveArray[ tableFldConstants.protectiveequipements.ordinal() ] = pmmsProtectiveequipements;
	}

	public String getPmmsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPmmsTempfield1(String pmmsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pmmsTempfield1;
	}

	public String getPmmsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPmmsTempfield2(String pmmsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pmmsTempfield2;
	}

	public String getPmmsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPmmsTempfield3(String pmmsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = pmmsTempfield3;
	}

	public String getPmmsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPmmsTempfield4(String pmmsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = pmmsTempfield4;
	}

	public String getPmmsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPmmsTempfield5(String pmmsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = pmmsTempfield5;
	}

	public String getPmmsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPmmsActive(String pmmsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pmmsActive;
	}

	public String getPmmsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPmmsCreatedby(String pmmsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pmmsCreatedby;
	}

	public String getPmmsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPmmsCreatedon(String pmmsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pmmsCreatedon;
	}

	public String getPmmsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPmmsModifiedon(String pmmsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pmmsModifiedon;
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
	            if (field.name() == "keyid" && val == null) {
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

	public static String toJsonManualList(List<BAL_PlmTlMethodsmst> list) {
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

	public static BAL_PlmTlMethodsmst fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    BAL_PlmTlMethodsmst pmms = new BAL_PlmTlMethodsmst();
	    CommonMessage.debugMsg("RAW JSON Response: :" + json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = field.name();

	        CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	        String val = obj.optString(field.name(), null);
	        pmms.setValue(field, val != null && val.equals("null") ? null : val);
	    }
	    return pmms;
	}

	public static List<BAL_PlmTlMethodsmst> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<BAL_PlmTlMethodsmst> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        BAL_PlmTlMethodsmst pmms = new BAL_PlmTlMethodsmst();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            pmms.setValue(field, val);
	        }

	        list.add(pmms);
	    }

	    return list;
	}

}

