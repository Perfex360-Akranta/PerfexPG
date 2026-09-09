package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.PlmTlEquipmentfmeadtl.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class PlmTlProcessfmeadtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, fmpm_keyid, processstep, keyprocessinput, potentialfailmode
		, potentialeffectfail, potentialcausefail, severity_keyid, occurrence_keyid
		, detection_keyid, rpn, currentcontrol, actionplan, reseverity_keyid
		, reoccurrence_keyid, redetection_keyid, rerpn, reviewby, redate
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public PlmTlProcessfmeadtl()
	{
		saveArray = new  Object [ 28 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFmpdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFmpdKeyid(String fmpdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fmpdKeyid;
	}

	public String getFmpdFmpmKeyid() {
		return (String) saveArray[ tableFldConstants.fmpm_keyid.ordinal() ];
	}

	public void setFmpdFmpmKeyid(String fmpdFmpmKeyid) {
		saveArray[ tableFldConstants.fmpm_keyid.ordinal() ] = fmpdFmpmKeyid;
	}

	public String getFmpdProcessstep() {
		return (String) saveArray[ tableFldConstants.processstep.ordinal() ];
	}

	public void setFmpdProcessstep(String fmpdProcessstep) {
		saveArray[ tableFldConstants.processstep.ordinal() ] = fmpdProcessstep;
	}

	public String getFmpdKeyprocessinput() {
		return (String) saveArray[ tableFldConstants.keyprocessinput.ordinal() ];
	}

	public void setFmpdKeyprocessinput(String fmpdKeyprocessinput) {
		saveArray[ tableFldConstants.keyprocessinput.ordinal() ] = fmpdKeyprocessinput;
	}

	public String getFmpdPotentialfailmode() {
		return (String) saveArray[ tableFldConstants.potentialfailmode.ordinal() ];
	}

	public void setFmpdPotentialfailmode(String fmpdPotentialfailmode) {
		saveArray[ tableFldConstants.potentialfailmode.ordinal() ] = fmpdPotentialfailmode;
	}

	public String getFmpdPotentialeffectfail() {
		return (String) saveArray[ tableFldConstants.potentialeffectfail.ordinal() ];
	}

	public void setFmpdPotentialeffectfail(String fmpdPotentialeffectfail) {
		saveArray[ tableFldConstants.potentialeffectfail.ordinal() ] = fmpdPotentialeffectfail;
	}

	public String getFmpdPotentialcausefail() {
		return (String) saveArray[ tableFldConstants.potentialcausefail.ordinal() ];
	}

	public void setFmpdPotentialcausefail(String fmpdPotentialcausefail) {
		saveArray[ tableFldConstants.potentialcausefail.ordinal() ] = fmpdPotentialcausefail;
	}

	public String getFmpdSeverityKeyid() {
		return (String) saveArray[ tableFldConstants.severity_keyid.ordinal() ];
	}

	public void setFmpdSeverityKeyid(String fmpdSeverityKeyid) {
		saveArray[ tableFldConstants.severity_keyid.ordinal() ] = fmpdSeverityKeyid;
	}

	public String getFmpdOccurrenceKeyid() {
		return (String) saveArray[ tableFldConstants.occurrence_keyid.ordinal() ];
	}

	public void setFmpdOccurrenceKeyid(String fmpdOccurrenceKeyid) {
		saveArray[ tableFldConstants.occurrence_keyid.ordinal() ] = fmpdOccurrenceKeyid;
	}

	public String getFmpdDetectionKeyid() {
		return (String) saveArray[ tableFldConstants.detection_keyid.ordinal() ];
	}

	public void setFmpdDetectionKeyid(String fmpdDetectionKeyid) {
		saveArray[ tableFldConstants.detection_keyid.ordinal() ] = fmpdDetectionKeyid;
	}

	public String getFmpdRpn() {
		return (String) saveArray[ tableFldConstants.rpn.ordinal() ];
	}

	public void setFmpdRpn(String fmpdRpn) {
		saveArray[ tableFldConstants.rpn.ordinal() ] = fmpdRpn;
	}

	public String getFmpdCurrentcontrol() {
		return (String) saveArray[ tableFldConstants.currentcontrol.ordinal() ];
	}

	public void setFmpdCurrentcontrol(String fmpdCurrentcontrol) {
		saveArray[ tableFldConstants.currentcontrol.ordinal() ] = fmpdCurrentcontrol;
	}

	public String getFmpdActionplan() {
		return (String) saveArray[ tableFldConstants.actionplan.ordinal() ];
	}

	public void setFmpdActionplan(String fmpdActionplan) {
		saveArray[ tableFldConstants.actionplan.ordinal() ] = fmpdActionplan;
	}

	public String getFmpdReseverityKeyid() {
		return (String) saveArray[ tableFldConstants.reseverity_keyid.ordinal() ];
	}

	public void setFmpdReseverityKeyid(String fmpdReseverityKeyid) {
		saveArray[ tableFldConstants.reseverity_keyid.ordinal() ] = fmpdReseverityKeyid;
	}

	public String getFmpdReoccurrenceKeyid() {
		return (String) saveArray[ tableFldConstants.reoccurrence_keyid.ordinal() ];
	}

	public void setFmpdReoccurrenceKeyid(String fmpdReoccurrenceKeyid) {
		saveArray[ tableFldConstants.reoccurrence_keyid.ordinal() ] = fmpdReoccurrenceKeyid;
	}

	public String getFmpdRedetectionKeyid() {
		return (String) saveArray[ tableFldConstants.redetection_keyid.ordinal() ];
	}

	public void setFmpdRedetectionKeyid(String fmpdRedetectionKeyid) {
		saveArray[ tableFldConstants.redetection_keyid.ordinal() ] = fmpdRedetectionKeyid;
	}

	public String getFmpdRerpn() {
		return (String) saveArray[ tableFldConstants.rerpn.ordinal() ];
	}

	public void setFmpdRerpn(String fmpdRerpn) {
		saveArray[ tableFldConstants.rerpn.ordinal() ] = fmpdRerpn;
	}

	public String getFmpdReviewby() {
		return (String) saveArray[ tableFldConstants.reviewby.ordinal() ];
	}

	public void setFmpdReviewby(String fmpdReviewby) {
		saveArray[ tableFldConstants.reviewby.ordinal() ] = fmpdReviewby;
	}

	public String getFmpdRedate() {
		return (String) saveArray[ tableFldConstants.redate.ordinal() ];
	}

	public void setFmpdRedate(String fmpdRedate) {
		saveArray[ tableFldConstants.redate.ordinal() ] = fmpdRedate;
	}

	public String getFmpdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFmpdTempfield1(String fmpdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fmpdTempfield1;
	}

	public String getFmpdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFmpdTempfield2(String fmpdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fmpdTempfield2;
	}

	public String getFmpdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFmpdTempfield3(String fmpdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fmpdTempfield3;
	}

	public String getFmpdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFmpdTempfield4(String fmpdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fmpdTempfield4;
	}

	public String getFmpdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFmpdTempfield5(String fmpdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fmpdTempfield5;
	}

	public String getFmpdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFmpdActive(String fmpdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fmpdActive;
	}

	public String getFmpdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFmpdCreatedby(String fmpdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fmpdCreatedby;
	}

	public String getFmpdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFmpdCreatedon(String fmpdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fmpdCreatedon;
	}

	public String getFmpdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFmpdModifiedon(String fmpdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fmpdModifiedon;
	}
	
	
	public String toJsonManual() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		boolean first = true;
		for (tableFldConstants field : tableFldConstants.values()) {
			int index = field.ordinal();
			if (index < saveArray.length) {
				if (!first)
					sb.append(",");
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

	
	public static List<PlmTlProcessfmeadtl> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<PlmTlProcessfmeadtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			PlmTlProcessfmeadtl dtl = new PlmTlProcessfmeadtl();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				Object valueObj = obj.opt(key);
				  String val = valueObj.toString();
		            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
		                val = "";
		            }
				//String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
				
				

				dtl.setValue(field, val);
			}

			list.add(dtl);
		}

		return list;
	}
	public static String toJsonManualList(List<PlmTlProcessfmeadtl> list) {
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
	

}

