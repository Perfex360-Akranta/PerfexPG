package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BalPlmTlObservations {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, date, refid, observation, targetdate, foundby, status
		, responsibility, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public BalPlmTlObservations()
	{
		saveArray = new  Object [ 17 ];
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

	public String getObsvKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setObsvKeyid(String obsvKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = obsvKeyid;
	}

	public String getObsvFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setObsvFlid(String obsvFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = obsvFlid;
	}

	public String getObsvDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setObsvDate(String obsvDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = obsvDate;
	}

	public String getObsvRefid() {
		return (String) saveArray[ tableFldConstants.refid.ordinal() ];
	}

	public void setObsvRefid(String obsvRefid) {
		saveArray[ tableFldConstants.refid.ordinal() ] = obsvRefid;
	}

	public String getObsvObservation() {
		return (String) saveArray[ tableFldConstants.observation.ordinal() ];
	}

	public void setObsvObservation(String obsvObservation) {
		saveArray[ tableFldConstants.observation.ordinal() ] = obsvObservation;
	}

	public String getObsvTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setObsvTargetdate(String obsvTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = obsvTargetdate;
	}

	public String getObsvFoundby() {
		return (String) saveArray[ tableFldConstants.foundby.ordinal() ];
	}

	public void setObsvFoundby(String obsvFoundby) {
		saveArray[ tableFldConstants.foundby.ordinal() ] = obsvFoundby;
	}

	public String getObsvStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setObsvStatus(String obsvStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = obsvStatus;
	}

	public String getObsvResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setObsvResponsibility(String obsvResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = obsvResponsibility;
	}

	public String getObsvTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setObsvTempfield2(String obsvTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = obsvTempfield2;
	}

	public String getObsvTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setObsvTempfield3(String obsvTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = obsvTempfield3;
	}

	public String getObsvTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setObsvTempfield4(String obsvTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = obsvTempfield4;
	}

	public String getObsvTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setObsvTempfield5(String obsvTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = obsvTempfield5;
	}

	public String getObsvActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setObsvActive(String obsvActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = obsvActive;
	}

	public String getObsvCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setObsvCreatedby(String obsvCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = obsvCreatedby;
	}

	public String getObsvCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setObsvCreatedon(String obsvCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = obsvCreatedon;
	}

	public String getObsvModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setObsvModifiedon(String obsvModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = obsvModifiedon;
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

	public static BalPlmTlObservations fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		BalPlmTlObservations dtl = new BalPlmTlObservations();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
			dtl.setValue(field, val);

		}
		return dtl;
	}

	public static List<BalPlmTlObservations> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BalPlmTlObservations> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			BalPlmTlObservations dtl = new BalPlmTlObservations();

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
	
	public static String toJsonManualList(List<BalPlmTlObservations> list) {
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

