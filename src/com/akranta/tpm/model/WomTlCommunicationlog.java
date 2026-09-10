package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class WomTlCommunicationlog {

	private  Object [] saveArray = null;


	public enum   tableFldConstants
	{
		keyid, wonumber, date, communicationtext, level, enteredby, displayorderno
		, active, createdby, createdon, modifiedon
	}

	public WomTlCommunicationlog()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public Object getValue(tableFldConstants field) {
		return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
		saveArray[field.ordinal()] = value;
	}

	public String getWcmlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWcmlKeyid(String wcmlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wcmlKeyid;
	}

	public String getWcmlWonumber() {
		return (String) saveArray[ tableFldConstants.wonumber.ordinal() ];
	}

	public void setWcmlWonumber(String wcmlWonumber) {
		saveArray[ tableFldConstants.wonumber.ordinal() ] = wcmlWonumber;
	}

	public String getWcmlDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setWcmlDate(String wcmlDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = wcmlDate;
	}

	public String getWcmlCommunicationtext() {
		return (String) saveArray[ tableFldConstants.communicationtext.ordinal() ];
	}

	public void setWcmlCommunicationtext(String wcmlCommunicationtext) {
		saveArray[ tableFldConstants.communicationtext.ordinal() ] = wcmlCommunicationtext;
	}

	public String getWcmlLevel() {
		return (String) saveArray[ tableFldConstants.level.ordinal() ];
	}

	public void setWcmlLevel(String wcmlLevel) {
		saveArray[ tableFldConstants.level.ordinal() ] = wcmlLevel;
	}

	public String getWcmlEnteredby() {
		return (String) saveArray[ tableFldConstants.enteredby.ordinal() ];
	}

	public void setWcmlEnteredby(String wcmlEnteredby) {
		saveArray[ tableFldConstants.enteredby.ordinal() ] = wcmlEnteredby;
	}

	public String getWcmlDisplayorderno() {
		return (String) saveArray[ tableFldConstants.displayorderno.ordinal() ];
	}

	public void setWcmlDisplayorderno(String wcmlDisplayorderno) {
		saveArray[ tableFldConstants.displayorderno.ordinal() ] = wcmlDisplayorderno;
	}

	public String getWcmlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWcmlActive(String wcmlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wcmlActive;
	}

	public String getWcmlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWcmlCreatedby(String wcmlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wcmlCreatedby;
	}

	public String getWcmlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWcmlCreatedon(String wcmlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wcmlCreatedon;
	}

	public String getWcmlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWcmlModifiedon(String wcmlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wcmlModifiedon;
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

	public static WomTlCommunicationlog fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		WomTlCommunicationlog mst = new WomTlCommunicationlog();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
				val = "";
			}
			mst.setValue(field, val != null && val.equals("null") ? null : val);

		}
		return mst;
	}

}