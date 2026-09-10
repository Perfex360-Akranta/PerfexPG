package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BalPlmTlCbmwocompdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, pmstandardid, pmcalendarid, wofeedbackid, pmentrytype
		, pmjobtype, activitydate, minimumreading, maximumreading, adjustedreading
		, currentreading, nextduedate, middlemax, tempfield1, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedon
	}

	public BalPlmTlCbmwocompdtl()
	{
		saveArray = new  Object [ 21 ];
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getCmcdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCmcdKeyid(String cmcdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cmcdKeyid;
	}

	public String getCmcdPmstandardid() {
		return (String) saveArray[ tableFldConstants.pmstandardid.ordinal() ];
	}

	public void setCmcdPmstandardid(String cmcdPmstandardid) {
		saveArray[ tableFldConstants.pmstandardid.ordinal() ] = cmcdPmstandardid;
	}

	public String getCmcdPmcalendarid() {
		return (String) saveArray[ tableFldConstants.pmcalendarid.ordinal() ];
	}

	public void setCmcdPmcalendarid(String cmcdPmcalendarid) {
		saveArray[ tableFldConstants.pmcalendarid.ordinal() ] = cmcdPmcalendarid;
	}

	public String getCmcdWofeedbackid() {
		return (String) saveArray[ tableFldConstants.wofeedbackid.ordinal() ];
	}

	public void setCmcdWofeedbackid(String cmcdWofeedbackid) {
		saveArray[ tableFldConstants.wofeedbackid.ordinal() ] = cmcdWofeedbackid;
	}

	public String getCmcdPmentrytype() {
		return (String) saveArray[ tableFldConstants.pmentrytype.ordinal() ];
	}

	public void setCmcdPmentrytype(String cmcdPmentrytype) {
		saveArray[ tableFldConstants.pmentrytype.ordinal() ] = cmcdPmentrytype;
	}

	public String getCmcdPmjobtype() {
		return (String) saveArray[ tableFldConstants.pmjobtype.ordinal() ];
	}

	public void setCmcdPmjobtype(String cmcdPmjobtype) {
		saveArray[ tableFldConstants.pmjobtype.ordinal() ] = cmcdPmjobtype;
	}

	public String getCmcdActivitydate() {
		return (String) saveArray[ tableFldConstants.activitydate.ordinal() ];
	}

	public void setCmcdActivitydate(String cmcdActivitydate) {
		saveArray[ tableFldConstants.activitydate.ordinal() ] = cmcdActivitydate;
	}

	public String getCmcdMinimumreading() {
		return (String) saveArray[ tableFldConstants.minimumreading.ordinal() ];
	}

	public void setCmcdMinimumreading(String cmcdMinimumreading) {
		saveArray[ tableFldConstants.minimumreading.ordinal() ] = cmcdMinimumreading;
	}

	public String getCmcdMaximumreading() {
		return (String) saveArray[ tableFldConstants.maximumreading.ordinal() ];
	}

	public void setCmcdMaximumreading(String cmcdMaximumreading) {
		saveArray[ tableFldConstants.maximumreading.ordinal() ] = cmcdMaximumreading;
	}

	public String getCmcdAdjustedreading() {
		return (String) saveArray[ tableFldConstants.adjustedreading.ordinal() ];
	}

	public void setCmcdAdjustedreading(String cmcdAdjustedreading) {
		saveArray[ tableFldConstants.adjustedreading.ordinal() ] = cmcdAdjustedreading;
	}

	public String getCmcdCurrentreading() {
		return (String) saveArray[ tableFldConstants.currentreading.ordinal() ];
	}

	public void setCmcdCurrentreading(String cmcdCurrentreading) {
		saveArray[ tableFldConstants.currentreading.ordinal() ] = cmcdCurrentreading;
	}

	public String getCmcdNextduedate() {
		return (String) saveArray[ tableFldConstants.nextduedate.ordinal() ];
	}

	public void setCmcdNextduedate(String cmcdNextduedate) {
		saveArray[ tableFldConstants.nextduedate.ordinal() ] = cmcdNextduedate;
	}

	public String getCmcdMiddlemax() {
		return (String) saveArray[ tableFldConstants.middlemax.ordinal() ];
	}

	public void setCmcdMiddlemax(String cmcdMiddlemax) {
		saveArray[ tableFldConstants.middlemax.ordinal() ] = cmcdMiddlemax;
	}

	public String getCmcdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCmcdTempfield1(String cmcdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = cmcdTempfield1;
	}

	public String getCmcdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCmcdTempfield2(String cmcdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cmcdTempfield2;
	}

	public String getCmcdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCmcdTempfield3(String cmcdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cmcdTempfield3;
	}

	public String getCmcdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCmcdTempfield4(String cmcdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = cmcdTempfield4;
	}

	public String getCmcdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCmcdActive(String cmcdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cmcdActive;
	}

	public String getCmcdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCmcdCreatedby(String cmcdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cmcdCreatedby;
	}

	public String getCmcdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCmcdCreatedon(String cmcdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cmcdCreatedon;
	}

	public String getCmcdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCmcdModifiedon(String cmcdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cmcdModifiedon;
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

	public static BalPlmTlCbmwocompdtl fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		BalPlmTlCbmwocompdtl dtl = new BalPlmTlCbmwocompdtl();
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

	public static List<BalPlmTlCbmwocompdtl> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BalPlmTlCbmwocompdtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			BalPlmTlCbmwocompdtl dtl = new BalPlmTlCbmwocompdtl();

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
	
	public static String toJsonManualList(List<BalPlmTlCbmwocompdtl> list) {
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

