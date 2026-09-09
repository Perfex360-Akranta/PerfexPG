package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class PlmTlConditionalappraisalEntry {

	private  Object [] saveArray = null;  
	private String completedBy;

	public enum   tableFldConstants
	{
		keyid, cdam_keyid, component_type, componentid, newcomponent
		, dimension, checkingtool, typeofcheck, idealtype, idealminimum
		, idealmaximum, uom, idealcondition, actualcondition, actualvalue
		, oknotok, status, actionrequired, refurbishment_status, Cdapkeyid
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, tempfield7, active, createdby, createdon, modifiedon
	}

	public PlmTlConditionalappraisalEntry()
	{
		saveArray = new  Object [ 30 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getCdapKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCdapKeyid(String cdapKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cdapKeyid;
	}

	public String getCdapCdamKeyid() {
		return (String) saveArray[ tableFldConstants.cdam_keyid.ordinal() ];
	}

	public void setCdapCdamKeyid(String cdapCdamKeyid) {
		saveArray[ tableFldConstants.cdam_keyid.ordinal() ] = cdapCdamKeyid;
	}

	public String getCdapComponentType() {
		return (String) saveArray[ tableFldConstants.component_type.ordinal() ];
	}

	public void setCdapComponentType(String cdapComponentType) {
		saveArray[ tableFldConstants.component_type.ordinal() ] = cdapComponentType;
	}

	public String getCdapComponentid() {
		return (String) saveArray[ tableFldConstants.componentid.ordinal() ];
	}

	public void setCdapComponentid(String cdapComponentid) {
		saveArray[ tableFldConstants.componentid.ordinal() ] = cdapComponentid;
	}

	public String getCdapNewcomponent() {
		return (String) saveArray[ tableFldConstants.newcomponent.ordinal() ];
	}

	public void setCdapNewcomponent(String cdapNewcomponent) {
		saveArray[ tableFldConstants.newcomponent.ordinal() ] = cdapNewcomponent;
	}

	public String getCdapDimension() {
		return (String) saveArray[ tableFldConstants.dimension.ordinal() ];
	}

	public void setCdapDimension(String cdapDimension) {
		saveArray[ tableFldConstants.dimension.ordinal() ] = cdapDimension;
	}

	public String getCdapCheckingtool() {
		return (String) saveArray[ tableFldConstants.checkingtool.ordinal() ];
	}

	public void setCdapCheckingtool(String cdapCheckingtool) {
		saveArray[ tableFldConstants.checkingtool.ordinal() ] = cdapCheckingtool;
	}

	public String getCdapTypeofcheck() {
		return (String) saveArray[ tableFldConstants.typeofcheck.ordinal() ];
	}

	public void setCdapTypeofcheck(String cdapTypeofcheck) {
		saveArray[ tableFldConstants.typeofcheck.ordinal() ] = cdapTypeofcheck;
	}

	public String getCdapIdealtype() {
		return (String) saveArray[ tableFldConstants.idealtype.ordinal() ];
	}

	public void setCdapIdealtype(String cdapIdealtype) {
		saveArray[ tableFldConstants.idealtype.ordinal() ] = cdapIdealtype;
	}

	public String getCdapIdealminimum() {
		return (String) saveArray[ tableFldConstants.idealminimum.ordinal() ];
	}

	public void setCdapIdealminimum(String cdapIdealminimum) {
		saveArray[ tableFldConstants.idealminimum.ordinal() ] = cdapIdealminimum;
	}

	public String getCdapIdealmaximum() {
		return (String) saveArray[ tableFldConstants.idealmaximum.ordinal() ];
	}

	public void setCdapIdealmaximum(String cdapIdealmaximum) {
		saveArray[ tableFldConstants.idealmaximum.ordinal() ] = cdapIdealmaximum;
	}

	public String getCdapUom() {
		return (String) saveArray[ tableFldConstants.uom.ordinal() ];
	}

	public void setCdapUom(String cdapUom) {
		saveArray[ tableFldConstants.uom.ordinal() ] = cdapUom;
	}

	public String getCdapIdealcondition() {
		return (String) saveArray[ tableFldConstants.idealcondition.ordinal() ];
	}

	public void setCdapIdealcondition(String cdapIdealcondition) {
		saveArray[ tableFldConstants.idealcondition.ordinal() ] = cdapIdealcondition;
	}

	public String getCdapActualcondition() {
		return (String) saveArray[ tableFldConstants.actualcondition.ordinal() ];
	}

	public void setCdapActualcondition(String cdapActualcondition) {
		saveArray[ tableFldConstants.actualcondition.ordinal() ] = cdapActualcondition;
	}

	public String getCdapActualvalue() {
		return (String) saveArray[ tableFldConstants.actualvalue.ordinal() ];
	}

	public void setCdapActualvalue(String cdapActualvalue) {
		saveArray[ tableFldConstants.actualvalue.ordinal() ] = cdapActualvalue;
	}

	public String getCdapOknotok() {
		return (String) saveArray[ tableFldConstants.oknotok.ordinal() ];
	}

	public void setCdapOknotok(String cdapOknotok) {
		saveArray[ tableFldConstants.oknotok.ordinal() ] = cdapOknotok;
	}

	public String getCdapStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setCdapStatus(String cdapStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = cdapStatus;
	}

	public String getCdapActionrequired() {
		return (String) saveArray[ tableFldConstants.actionrequired.ordinal() ];
	}

	public void setCdapActionrequired(String cdapActionrequired) {
		saveArray[ tableFldConstants.actionrequired.ordinal() ] = cdapActionrequired;
	}

	public String getCdapRefurbishmentStatus() {
		return (String) saveArray[ tableFldConstants.refurbishment_status.ordinal() ];
	}

	public void setCdapRefurbishmentStatus(String cdapRefurbishmentStatus) {
		saveArray[ tableFldConstants.refurbishment_status.ordinal() ] = cdapRefurbishmentStatus;
	}

	public String getCdapCdapkeyid() {
		return (String) saveArray[ tableFldConstants.Cdapkeyid.ordinal() ];
	}

	public void setCdapCdapkeyid(String cdapCdapkeyid) {
		saveArray[ tableFldConstants.Cdapkeyid.ordinal() ] = cdapCdapkeyid;
	}

	public String getCdapTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCdapTempfield2(String cdapTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cdapTempfield2;
	}

	public String getCdapTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCdapTempfield3(String cdapTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cdapTempfield3;
	}

	public String getCdapTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCdapTempfield4(String cdapTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = cdapTempfield4;
	}

	public String getCdapTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setCdapTempfield5(String cdapTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = cdapTempfield5;
	}

	public String getCdapTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setCdapTempfield6(String cdapTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = cdapTempfield6;
	}

	public String getCdapTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setCdapTempfield7(String cdapTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = cdapTempfield7;
	}

	public String getCdapActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCdapActive(String cdapActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cdapActive;
	}

	public String getCdapCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCdapCreatedby(String cdapCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cdapCreatedby;
	}

	public String getCdapCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCdapCreatedon(String cdapCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cdapCreatedon;
	}

	public String getCdapModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCdapModifiedon(String cdapModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cdapModifiedon;
	}
	public String getCompletedBy() {
		return completedBy;
	}
	public void setCompletedBy(String completedBy) {
		this.completedBy=completedBy;
	}
	
	
	public void setValue(tableFldConstants field, String value) {
		if (field != null) {
			saveArray[field.ordinal()] = value;
		}
	}

	/**
	 * Convert detail entry object to JSON string
	 */
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
				if (field.name().equals("keyid") && val == null) {
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

	/**
	 * Parse JSON string to detail entry object
	 */
	public static PlmTlConditionalappraisalEntry fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		PlmTlConditionalappraisalEntry dtlEntry = new PlmTlConditionalappraisalEntry();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			dtlEntry.setValue(field, val != null && val.equals("null") ? null : val);
		}
		return dtlEntry;
	}

	/**
	 * Parse JSON array to list of detail entry objects
	 */
	public static List<PlmTlConditionalappraisalEntry> fromJsonList(String json) {
		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<PlmTlConditionalappraisalEntry> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			PlmTlConditionalappraisalEntry dtlEntry = new PlmTlConditionalappraisalEntry();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				Object valueObj = obj.opt(key);
				String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

				dtlEntry.setValue(field, val);
			}

			list.add(dtlEntry);
		}

		return list;
	}

	/**
	 * Convert list of detail entry objects to JSON array string
	 */
	public static String toJsonManualList(List<PlmTlConditionalappraisalEntry> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		// Add null check
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (i > 0)
					sb.append(",");
				sb.append(list.get(i).toJsonManual());
			}
		}

		sb.append("]");
		return sb.toString();
	}
}

