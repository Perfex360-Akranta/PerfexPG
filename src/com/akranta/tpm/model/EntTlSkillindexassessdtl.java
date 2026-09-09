package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMomdtl.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class EntTlSkillindexassessdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, siam_keyid, empm_keyid, reviewid, score, criteriaid, total
		, reviewdate, reviewhalf, tempfiled5, active, createdby, createdon
		, modifiedon
	}

	public EntTlSkillindexassessdtl()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSiadKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public void setSiadKeyid(String siadKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = siadKeyid;
	}

	public String getSiadSiamKeyid() {
		return (String) saveArray[ tableFldConstants.siam_keyid.ordinal() ];
	}

	public void setSiadSiamKeyid(String siadSiamKeyid) {
		saveArray[ tableFldConstants.siam_keyid.ordinal() ] = siadSiamKeyid;
	}

	public String getSiadEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setSiadEmpmKeyid(String siadEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = siadEmpmKeyid;
	}

	public String getSiadReviewid() {
		return (String) saveArray[ tableFldConstants.reviewid.ordinal() ];
	}

	public void setSiadReviewid(String siadReviewid) {
		saveArray[ tableFldConstants.reviewid.ordinal() ] = siadReviewid;
	}

	public String getSiadScore() {
		return (String) saveArray[ tableFldConstants.score.ordinal() ];
	}

	public void setSiadScore(String siadScore) {
		saveArray[ tableFldConstants.score.ordinal() ] = siadScore;
	}

	public String getSiadCriteriaid() {
		return (String) saveArray[ tableFldConstants.criteriaid.ordinal() ];
	}

	public void setSiadCriteriaid(String siadCriteriaid) {
		saveArray[ tableFldConstants.criteriaid.ordinal() ] = siadCriteriaid;
	}

	public String getSiadtotal() {
		return (String) saveArray[ tableFldConstants.total.ordinal() ];
	}

	public void setSiadTotal(String siadTotal) {
		saveArray[ tableFldConstants.total.ordinal() ] = siadTotal;
	}

	public String getSiadReviewdate() {
		return (String) saveArray[ tableFldConstants.reviewdate.ordinal() ];
	}

	public void setSiadReviewdate(String siamReviewdate) {
		saveArray[ tableFldConstants.reviewdate.ordinal() ] = siamReviewdate;
	}

	public String getSiadReviewhalf() {
		return (String) saveArray[ tableFldConstants.reviewhalf.ordinal() ];
	}

	public void setSiadReviewhalf(String siadReviewhalf) {
		saveArray[ tableFldConstants.reviewhalf.ordinal() ] = siadReviewhalf;
	}

	public String getSiadTempfiled5() {
		return (String) saveArray[ tableFldConstants.tempfiled5.ordinal() ];
	}

	public void setSiadTempfiled5(String siadTempfiled5) {
		saveArray[ tableFldConstants.tempfiled5.ordinal() ] = siadTempfiled5;
	}

	public String getSiadActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSiadActive(String siadActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = siadActive;
	}

	public String getSiadCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSiadCreatedby(String siadCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = siadCreatedby;
	}

	public String getSiadCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSiadCreatedon(String siadCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = siadCreatedon;
	}

	public String getSiadModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSiadModifiedon(String siadModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = siadModifiedon;
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

	public static EntTlSkillindexassessdtl fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		EntTlSkillindexassessdtl dtl = new EntTlSkillindexassessdtl();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			dtl.setValue(field, val != null && val.equals("null") ? null : val);

		}
		return dtl;
	}

	public static List<EntTlSkillindexassessdtl> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<EntTlSkillindexassessdtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			EntTlSkillindexassessdtl dtl = new EntTlSkillindexassessdtl();

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
	
	public static String toJsonManualList(List<EntTlSkillindexassessdtl> list) {
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

