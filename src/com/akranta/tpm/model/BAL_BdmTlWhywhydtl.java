package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_BdmTlWhywhydtl {

	private  Object [] saveArray = null;
	//private List<BdmTlWhywhydtl> bdmTlWhywhydtl;


	public enum   tableFldConstants
	{
		keyid, wwmsKeyid, slno, why, answer, action, createdby, createdon
		, modifiedon
	}

	public BAL_BdmTlWhywhydtl()
	{
		saveArray = new  Object [ 9 ];
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

	public String getWwdtKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWwdtKeyid(String wwdtKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wwdtKeyid;
	}

	public String getWwdtWwmsKeyid() {
		return (String) saveArray[ tableFldConstants.wwmsKeyid.ordinal() ];
	}

	public void setWwdtWwmsKeyid(String wwdtWwmsKeyid) {
		saveArray[ tableFldConstants.wwmsKeyid.ordinal() ] = wwdtWwmsKeyid;
	}

	public String getWwdtSlno() {
		return (String) saveArray[ tableFldConstants.slno.ordinal() ];
	}

	public void setWwdtSlno(String wwdtSlno) {
		saveArray[ tableFldConstants.slno.ordinal() ] = wwdtSlno;
	}

	public String getWwdtWhy() {
		return (String) saveArray[ tableFldConstants.why.ordinal() ];
	}

	public void setWwdtWhy(String wwdtWhy) {
		saveArray[ tableFldConstants.why.ordinal() ] = wwdtWhy;
	}

	public String getWwdtAnswer() {
		return (String) saveArray[ tableFldConstants.answer.ordinal() ];
	}

	public void setWwdtAnswer(String wwdtAnswer) {
		saveArray[ tableFldConstants.answer.ordinal() ] = wwdtAnswer;
	}

	public String getWwdtAction() {
		return (String) saveArray[ tableFldConstants.action.ordinal() ];
	}

	public void setWwdtAction(String wwdtAction) {
		saveArray[ tableFldConstants.action.ordinal() ] = wwdtAction;
	}

	public String getWwdtCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWwdtCreatedby(String wwdtCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wwdtCreatedby;
	}

	public String getWwdtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWwdtCreatedon(String wwdtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wwdtCreatedon;
	}

	public String getWwdtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWwdtModifiedon(String wwdtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wwdtModifiedon;
	}

	// ==================================================================
	//  MODEL -> JSON
	// ==================================================================

	/** One detail row */
	public String toJsonManual() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		boolean first = true;
		for (tableFldConstants field : tableFldConstants.values()) {
			Object val = saveArray[field.ordinal()];
			if (val == null) {
				continue;
			}
			if (!first) sb.append(",");
			String s = val.toString().replace("\\", "\\\\").replace("\"", "\\\"")
					.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
			sb.append("\"").append(field.name()).append("\":\"").append(s).append("\"");
			first = false;
		}

		sb.append("}");
		return sb.toString();
	}

	/** JSON array of detail rows */
	public static String toJsonManualList(List<BAL_BdmTlWhywhydtl> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		if (list != null) {
			boolean first = true;
			for (BAL_BdmTlWhywhydtl dtl : list) {
				if (!first) sb.append(",");
				sb.append(dtl.toJsonManual());
				first = false;
			}
		}

		sb.append("]");
		return sb.toString();
	}

	// ==================================================================
	//  JSON -> MODEL
	// ==================================================================

	public static BAL_BdmTlWhywhydtl fromJson(String json) {

		JSONObject obj = JSONObject.fromObject(json);
		BAL_BdmTlWhywhydtl dtl = new BAL_BdmTlWhywhydtl();

		for (tableFldConstants field : tableFldConstants.values()) {
			String val = obj.optString(field.name(), null);
			if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
				val = "";
			}
			dtl.setValue(field, val);
		}
		return dtl;
	}

	public static List<BAL_BdmTlWhywhydtl> fromJsonList(String json) {

		List<BAL_BdmTlWhywhydtl> list = new ArrayList<BAL_BdmTlWhywhydtl>();

		JSONArray array = JSONArray.fromObject(json);
		for (int i = 0; i < array.length(); i++) {
			list.add(fromJson(array.getJSONObject(i).toString()));
		}
		return list;
	}
}
