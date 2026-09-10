package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BalPlmTlSpareconsumed {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, wodetailid, pmcalendarid, spareid, quantity, cost, isactivitydone
		, remarks, createdby, createdon, modifiedon
	}

	public BalPlmTlSpareconsumed()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPspcKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public void setPspcKeyid(String pspcKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pspcKeyid;
	}

	public String getPspcWodetailid() {
		return (String) saveArray[ tableFldConstants.wodetailid.ordinal() ];
	}

	public void setPspcWodetailid(String pspcWodetailid) {
		saveArray[ tableFldConstants.wodetailid.ordinal() ] = pspcWodetailid;
	}

	public String getPspcPmcalendarid() {
		return (String) saveArray[ tableFldConstants.pmcalendarid.ordinal() ];
	}

	public void setPspcPmcalendarid(String pspcPmcalendarid) {
		saveArray[ tableFldConstants.pmcalendarid.ordinal() ] = pspcPmcalendarid;
	}

	public String getPspcSpareid() {
		return (String) saveArray[ tableFldConstants.spareid.ordinal() ];
	}

	public void setPspcSpareid(String pspcSpareid) {
		saveArray[ tableFldConstants.spareid.ordinal() ] = pspcSpareid;
	}

	public String getPspcQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setPspcQuantity(String pspcQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = pspcQuantity;
	}

	public String getPspcCost() {
		return (String) saveArray[ tableFldConstants.cost.ordinal() ];
	}

	public void setPspcCost(String pspcCost) {
		saveArray[ tableFldConstants.cost.ordinal() ] = pspcCost;
	}

	public String getPspcIsactivitydone() {
		return (String) saveArray[ tableFldConstants.isactivitydone.ordinal() ];
	}

	public void setPspcIsactivitydone(String pspcIsactivitydone) {
		saveArray[ tableFldConstants.isactivitydone.ordinal() ] = pspcIsactivitydone;
	}

	public String getPspcRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setPspcRemarks(String pspcRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = pspcRemarks;
	}

	public String getPspcCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPspcCreatedby(String pspcCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pspcCreatedby;
	}

	public String getPspcCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPspcCreatedon(String pspcCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pspcCreatedon;
	}

	public String getPspcModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPspcModifiedon(String pspcModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pspcModifiedon;
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

	public static BalPlmTlSpareconsumed fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		BalPlmTlSpareconsumed dtl = new BalPlmTlSpareconsumed();
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

	public static List<BalPlmTlSpareconsumed> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BalPlmTlSpareconsumed> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			BalPlmTlSpareconsumed dtl = new BalPlmTlSpareconsumed();

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
	
	public static String toJsonManualList(List<BalPlmTlSpareconsumed> list) {
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

