package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BalPlmTlSparecostactual {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		pmcalendarid, sitreference, sparesid, quantity, rate, value, refdocno
		, doctype, requestedby, date, tempfield1, tempfield2, createdby
		, createdon, modifiedon
	}

	public BalPlmTlSparecostactual()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPscaPmcalendarid() {
		return (String) saveArray[ tableFldConstants.pmcalendarid.ordinal() ];
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	public void setPscaPmcalendarid(String pscaPmcalendarid) {
		saveArray[ tableFldConstants.pmcalendarid.ordinal() ] = pscaPmcalendarid;
	}

	public String getPscaSitreference() {
		return (String) saveArray[ tableFldConstants.sitreference.ordinal() ];
	}

	public void setPscaSitreference(String pscaSitreference) {
		saveArray[ tableFldConstants.sitreference.ordinal() ] = pscaSitreference;
	}

	public String getPscaSparesid() {
		return (String) saveArray[ tableFldConstants.sparesid.ordinal() ];
	}

	public void setPscaSparesid(String pscaSparesid) {
		saveArray[ tableFldConstants.sparesid.ordinal() ] = pscaSparesid;
	}

	public String getPscaQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setPscaQuantity(String pscaQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = pscaQuantity;
	}

	public String getPscaRate() {
		return (String) saveArray[ tableFldConstants.rate.ordinal() ];
	}

	public void setPscaRate(String pscaRate) {
		saveArray[ tableFldConstants.rate.ordinal() ] = pscaRate;
	}

	public String getPscaValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setPscaValue(String pscaValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = pscaValue;
	}

	public String getPscaRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setPscaRefdocno(String pscaRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = pscaRefdocno;
	}

	public String getPscaDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setPscaDoctype(String pscaDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = pscaDoctype;
	}

	public String getPscaRequestedby() {
		return (String) saveArray[ tableFldConstants.requestedby.ordinal() ];
	}

	public void setPscaRequestedby(String pscaRequestedby) {
		saveArray[ tableFldConstants.requestedby.ordinal() ] = pscaRequestedby;
	}

	public String getPscaDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPscaDate(String pscaDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = pscaDate;
	}

	public String getPscaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPscaTempfield1(String pscaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pscaTempfield1;
	}

	public String getPscaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPscaTempfield2(String pscaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pscaTempfield2;
	}

	public String getPscaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPscaCreatedby(String pscaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pscaCreatedby;
	}

	public String getPscaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPscaCreatedon(String pscaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pscaCreatedon;
	}

	public String getPscaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPscaModifiedon(String pscaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pscaModifiedon;
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

	public static BalPlmTlSparecostactual fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		BalPlmTlSparecostactual dtl = new BalPlmTlSparecostactual();
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

	public static List<BalPlmTlSparecostactual> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BalPlmTlSparecostactual> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			BalPlmTlSparecostactual dtl = new BalPlmTlSparecostactual();

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
	
	public static String toJsonManualList(List<BalPlmTlSparecostactual> list) {
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

