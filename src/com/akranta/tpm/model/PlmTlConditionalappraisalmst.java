package com.akranta.tpm.model;
import java.util.List;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class PlmTlConditionalappraisalmst {

	private  Object [] saveArray = null;  
	private PlmTlConditionalappraisal plmTlConditionalappraisal ;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public PlmTlConditionalappraisalmst()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] SaveArray) {
		this.saveArray=SaveArray;
	}
	public String getCdamKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCdamKeyid(String cdamKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cdamKeyid;
	}

	public String getCdamFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setCdamFlid(String cdamFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = cdamFlid;
	}

	public String getCdamElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setCdamElementid(String cdamElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = cdamElementid;
	}

	public String getCdamDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setCdamDate(String cdamDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = cdamDate;
	}

	public String getCdamTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCdamTempfield1(String cdamTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = cdamTempfield1;
	}

	public String getCdamTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCdamTempfield2(String cdamTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cdamTempfield2;
	}

	public String getCdamTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCdamTempfield3(String cdamTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cdamTempfield3;
	}

	public String getCdamTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCdamTempfield4(String cdamTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = cdamTempfield4;
	}

	public String getCdamTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setCdamTempfield5(String cdamTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = cdamTempfield5;
	}

	public String getCdamActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCdamActive(String cdamActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cdamActive;
	}

	public String getCdamCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCdamCreatedby(String cdamCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cdamCreatedby;
	}

	public String getCdamCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCdamCreatedon(String cdamCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cdamCreatedon;
	}

	public String getCdamModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCdamModifiedon(String cdamModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cdamModifiedon;
	}

	public void setPlmTlConditionalappraisal(PlmTlConditionalappraisal plmTlConditionalappraisal) {
		this.plmTlConditionalappraisal = plmTlConditionalappraisal;
	}

	public PlmTlConditionalappraisal getPlmTlConditionalappraisal() {
		return plmTlConditionalappraisal;
	}
	
	
	public void setValue(tableFldConstants field, String value) {
		if (field != null) {
			saveArray[field.ordinal()] = value;
		}
	}

	/**
	 * Convert master object to JSON string
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
	 * Parse JSON string to master object
	 */
	public static PlmTlConditionalappraisalmst fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		PlmTlConditionalappraisalmst mst = new PlmTlConditionalappraisalmst();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			mst.setValue(field, val != null && val.equals("null") ? null : val);
		}
		return mst;
	}
}

