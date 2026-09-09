package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlBestdtl.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlKnowwhydtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, knwm_keyid, possiblecauses, knowwhy, solution, normalcondition
		, sustenanceaction, tempfield2, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public QtmTlKnowwhydtl()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public String getKnwdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKnwdKeyid(String knwdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = knwdKeyid;
	}

	public String getKnwdKnwmKeyid() {
		return (String) saveArray[ tableFldConstants.knwm_keyid.ordinal() ];
	}

	public void setKnwdKnwmKeyid(String knwdKnwmKeyid) {
		saveArray[ tableFldConstants.knwm_keyid.ordinal() ] = knwdKnwmKeyid;
	}

	public String getKnwdPossiblecauses() {
		return (String) saveArray[ tableFldConstants.possiblecauses.ordinal() ];
	}

	public void setKnwdPossiblecauses(String knwdPossiblecauses) {
		saveArray[ tableFldConstants.possiblecauses.ordinal() ] = knwdPossiblecauses;
	}

	public String getKnwdKnowwhy() {
		return (String) saveArray[ tableFldConstants.knowwhy.ordinal() ];
	}

	public void setKnwdKnowwhy(String knwdKnowwhy) {
		saveArray[ tableFldConstants.knowwhy.ordinal() ] = knwdKnowwhy;
	}

	public String getKnwdSolution() {
		return (String) saveArray[ tableFldConstants.solution.ordinal() ];
	}

	public void setKnwdSolution(String knwdSolution) {
		saveArray[ tableFldConstants.solution.ordinal() ] = knwdSolution;
	}

	public String getKnwdNormalcondition() {
		return (String) saveArray[ tableFldConstants.normalcondition.ordinal() ];
	}

	public void setKnwdNormalcondition(String knwdNormalcondition) {
		saveArray[ tableFldConstants.normalcondition.ordinal() ] = knwdNormalcondition;
	}

	public String getKnwdSustenanceaction() {
		return (String) saveArray[ tableFldConstants.sustenanceaction.ordinal() ];
	}

	public void setKnwdSustenanceaction(String knwdSustenanceaction) {
		saveArray[ tableFldConstants.sustenanceaction.ordinal() ] = knwdSustenanceaction;
	}

	public String getKnwdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKnwdTempfield2(String knwdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = knwdTempfield2;
	}

	public String getKnwdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKnwdTempfield3(String knwdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = knwdTempfield3;
	}

	public String getKnwdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKnwdTempfield4(String knwdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = knwdTempfield4;
	}

	public String getKnwdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKnwdTempfield5(String knwdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = knwdTempfield5;
	}

	public String getKnwdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKnwdCreatedby(String knwdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = knwdCreatedby;
	}

	public String getKnwdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKnwdActive(String knwdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = knwdActive;
	}

	public String getKnwdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKnwdCreatedon(String knwdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = knwdCreatedon;
	}

	public String getKnwdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKnwdModifiedon(String knwdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = knwdModifiedon;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
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

	public static QtmTlKnowwhydtl fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		QtmTlKnowwhydtl dtl = new QtmTlKnowwhydtl();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			  if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
			//dtl.setValue(field, val != null && val.equals("null") ? null : val);
			  dtl.setValue(field, val);

		}
		return dtl;
	}

	public static List<QtmTlKnowwhydtl> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<QtmTlKnowwhydtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			QtmTlKnowwhydtl dtl = new QtmTlKnowwhydtl();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				Object valueObj = obj.opt(key);
				String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

				dtl.setValue(field, val);
			}

			list.add(dtl);
		}

		return list;
	}
	
	public static String toJsonManualList(List<QtmTlKnowwhydtl> list) {
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

