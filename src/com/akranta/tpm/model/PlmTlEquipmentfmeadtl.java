package com.akranta.tpm.model;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMomdtl.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class PlmTlEquipmentfmeadtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, fmeq_keyid, function, component, functionfail, potentialfailmode
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

	public PlmTlEquipmentfmeadtl()
	{
		saveArray = new  Object [ 29 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFmedKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFmedKeyid(String fmedKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fmedKeyid;
	}

	public String getFmedFmeqKeyid() {
		return (String) saveArray[ tableFldConstants.fmeq_keyid.ordinal() ];
	}

	public void setFmedFmeqKeyid(String fmedFmeqKeyid) {
		saveArray[ tableFldConstants.fmeq_keyid.ordinal() ] = fmedFmeqKeyid;
	}

	public String getFmedFunction() {
		return (String) saveArray[ tableFldConstants.function.ordinal() ];
	}

	public void setFmedFunction(String fmedFunction) {
		saveArray[ tableFldConstants.function.ordinal() ] = fmedFunction;
	}

	public String getFmedComponent() {
		return (String) saveArray[ tableFldConstants.component.ordinal() ];
	}

	public void setFmedComponent(String fmedComponent) {
		saveArray[ tableFldConstants.component.ordinal() ] = fmedComponent;
	}

	public String getFmedFunctionfail() {
		return (String) saveArray[ tableFldConstants.functionfail.ordinal() ];
	}

	public void setFmedFunctionfail(String fmedFunctionfail) {
		saveArray[ tableFldConstants.functionfail.ordinal() ] = fmedFunctionfail;
	}

	public String getFmedPotentialfailmode() {
		return (String) saveArray[ tableFldConstants.potentialfailmode.ordinal() ];
	}

	public void setFmedPotentialfailmode(String fmedPotentialfailmode) {
		saveArray[ tableFldConstants.potentialfailmode.ordinal() ] = fmedPotentialfailmode;
	}

	public String getFmedPotentialeffectfail() {
		return (String) saveArray[ tableFldConstants.potentialeffectfail.ordinal() ];
	}

	public void setFmedPotentialeffectfail(String fmedPotentialeffectfail) {
		saveArray[ tableFldConstants.potentialeffectfail.ordinal() ] = fmedPotentialeffectfail;
	}

	public String getFmedPotentialcausefail() {
		return (String) saveArray[ tableFldConstants.potentialcausefail.ordinal() ];
	}

	public void setFmedPotentialcausefail(String fmedPotentialcausefail) {
		saveArray[ tableFldConstants.potentialcausefail.ordinal() ] = fmedPotentialcausefail;
	}

	public String getFmedSeverityKeyid() {
		return (String) saveArray[ tableFldConstants.severity_keyid.ordinal() ];
	}

	public void setFmedSeverityKeyid(String fmedSeverityKeyid) {
		saveArray[ tableFldConstants.severity_keyid.ordinal() ] = fmedSeverityKeyid;
	}

	public String getFmedOccurrenceKeyid() {
		return (String) saveArray[ tableFldConstants.occurrence_keyid.ordinal() ];
	}

	public void setFmedOccurrenceKeyid(String fmedOccurrenceKeyid) {
		saveArray[ tableFldConstants.occurrence_keyid.ordinal() ] = fmedOccurrenceKeyid;
	}

	public String getFmedDetectionKeyid() {
		return (String) saveArray[ tableFldConstants.detection_keyid.ordinal() ];
	}

	public void setFmedDetectionKeyid(String fmedDetectionKeyid) {
		saveArray[ tableFldConstants.detection_keyid.ordinal() ] = fmedDetectionKeyid;
	}

	public String getFmedRpn() {
		return (String) saveArray[ tableFldConstants.rpn.ordinal() ];
	}

	public void setFmedRpn(String fmedRpn) {
		saveArray[ tableFldConstants.rpn.ordinal() ] = fmedRpn;
	}

	public String getFmedCurrentcontrol() {
		return (String) saveArray[ tableFldConstants.currentcontrol.ordinal() ];
	}

	public void setFmedCurrentcontrol(String fmedCurrentcontrol) {
		saveArray[ tableFldConstants.currentcontrol.ordinal() ] = fmedCurrentcontrol;
	}

	public String getFmedActionplan() {
		return (String) saveArray[ tableFldConstants.actionplan.ordinal() ];
	}

	public void setFmedActionplan(String fmedActionplan) {
		saveArray[ tableFldConstants.actionplan.ordinal() ] = fmedActionplan;
	}

	public String getFmedReseverityKeyid() {
		return (String) saveArray[ tableFldConstants.reseverity_keyid.ordinal() ];
	}

	public void setFmedReseverityKeyid(String fmedReseverityKeyid) {
		saveArray[ tableFldConstants.reseverity_keyid.ordinal() ] = fmedReseverityKeyid;
	}

	public String getFmedReoccurrenceKeyid() {
		return (String) saveArray[ tableFldConstants.reoccurrence_keyid.ordinal() ];
	}

	public void setFmedReoccurrenceKeyid(String fmedReoccurrenceKeyid) {
		saveArray[ tableFldConstants.reoccurrence_keyid.ordinal() ] = fmedReoccurrenceKeyid;
	}

	public String getFmedRedetectionKeyid() {
		return (String) saveArray[ tableFldConstants.redetection_keyid.ordinal() ];
	}

	public void setFmedRedetectionKeyid(String fmedRedetectionKeyid) {
		saveArray[ tableFldConstants.redetection_keyid.ordinal() ] = fmedRedetectionKeyid;
	}

	public String getFmedRerpn() {
		return (String) saveArray[ tableFldConstants.rerpn.ordinal() ];
	}

	public void setFmedRerpn(String fmedRerpn) {
		saveArray[ tableFldConstants.rerpn.ordinal() ] = fmedRerpn;
	}

	public String getFmedReviewby() {
		return (String) saveArray[ tableFldConstants.reviewby.ordinal() ];
	}

	public void setFmedReviewby(String fmedReviewby) {
		saveArray[ tableFldConstants.reviewby.ordinal() ] = fmedReviewby;
	}

	public String getFmedRedate() {
		return (String) saveArray[ tableFldConstants.redate.ordinal() ];
	}

	public void setFmedRedate(String fmedRedate) {
		saveArray[ tableFldConstants.redate.ordinal() ] = fmedRedate;
	}

	public String getFmedTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFmedTempfield1(String fmedTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fmedTempfield1;
	}

	public String getFmedTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFmedTempfield2(String fmedTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fmedTempfield2;
	}

	public String getFmedTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFmedTempfield3(String fmedTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fmedTempfield3;
	}

	public String getFmedTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFmedTempfield4(String fmedTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fmedTempfield4;
	}

	public String getFmedTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFmedTempfield5(String fmedTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fmedTempfield5;
	}

	public String getFmedActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFmedActive(String fmedActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fmedActive;
	}

	public String getFmedCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFmedCreatedby(String fmedCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fmedCreatedby;
	}

	public String getFmedCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFmedCreatedon(String fmedCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fmedCreatedon;
	}

	public String getFmedModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFmedModifiedon(String fmedModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fmedModifiedon;
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

	
	public static List<PlmTlEquipmentfmeadtl> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<PlmTlEquipmentfmeadtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			PlmTlEquipmentfmeadtl dtl = new PlmTlEquipmentfmeadtl();

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
	public static String toJsonManualList(List<PlmTlEquipmentfmeadtl> list) {
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
	
	


