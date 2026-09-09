package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMomdtl.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KznTlBestdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, kzbm_keyid, kaizenid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public KznTlBestdtl()
	{
		saveArray = new  Object [ 12 ];
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

	public String getKzbdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKzbdKeyid(String kzbdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kzbdKeyid;
	}

	public String getKzbdKzbmKeyid() {
		return (String) saveArray[ tableFldConstants.kzbm_keyid.ordinal() ];
	}

	public void setKzbdKzbmKeyid(String kzbdKzbmKeyid) {
		saveArray[ tableFldConstants.kzbm_keyid.ordinal() ] = kzbdKzbmKeyid;
	}

	public String getKzbdKaizenid() {
		return (String) saveArray[ tableFldConstants.kaizenid.ordinal() ];
	}

	public void setKzbdKaizenid(String kzbdKaizenid) {
		saveArray[ tableFldConstants.kaizenid.ordinal() ] = kzbdKaizenid;
	}

	public String getKzbdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKzbdTempfield1(String kzbdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kzbdTempfield1;
	}

	public String getKzbdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKzbdTempfield2(String kzbdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kzbdTempfield2;
	}

	public String getKzbdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKzbdTempfield3(String kzbdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kzbdTempfield3;
	}

	public String getKzbdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKzbdTempfield4(String kzbdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kzbdTempfield4;
	}

	public String getKzbdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKzbdTempfield5(String kzbdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kzbdTempfield5;
	}

	public String getKzbdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKzbdActive(String kzbdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kzbdActive;
	}

	public String getKzbdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKzbdCreatedby(String kzbdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kzbdCreatedby;
	}

	public String getKzbdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKzbdCreatedon(String kzbdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kzbdCreatedon;
	}

	public String getKzbdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKzbdModifiedon(String kzbdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kzbdModifiedon;
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

	public static KznTlBestdtl fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		KznTlBestdtl dtl = new KznTlBestdtl();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			dtl.setValue(field, val != null && val.equals("null") ? null : val);

		}
		return dtl;
	}

	public static List<KznTlBestdtl> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<KznTlBestdtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			KznTlBestdtl dtl = new KznTlBestdtl();

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
	
	public static String toJsonManualList(List<KznTlBestdtl> list) {
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

