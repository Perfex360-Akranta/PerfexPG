package com.akranta.tpm.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BdmTlWhywhydtl {

	private  Object [] saveArray = null;  
	//private List<BdmTlWhywhydtl> bdmTlWhywhydtl;
	

	public enum   tableFldConstants
	{
		keyid, wwmsKeyid, slno, why, answer, action, createdby, createdon
		, modifiedon
	}

	public BdmTlWhywhydtl()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
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
	//Date mano string changes to date 
	public String getWwdtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWwdtCreatedon(String wwdtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wwdtCreatedon;
	}
	//Mano
	public String getWwdtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWwdtModifiedon(String wwdtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wwdtModifiedon;
	}
	//MANO
	@Override
	public String toString() {
	    return "BdmTlWhywhydtl{" +
	            "wwdtKeyid='" + getWwdtKeyid() + '\'' +
	            ", wwdtWwmsKeyid='" + getWwdtWwmsKeyid() + '\'' +
	            ", wwdtSlno='" + getWwdtSlno() + '\'' +
	            ", wwdtWhy='" + getWwdtWhy() + '\'' +
	            ", wwdtAnswer='" + getWwdtAnswer() + '\'' +
	            ", wwdtAction='" + getWwdtAction() + '\'' +
	            ", wwdtCreatedby='" + getWwdtCreatedby() + '\'' +
	            ", wwdtCreatedon='" + getWwdtCreatedon() + '\'' +
	            ", wwdtModifiedon='" + getWwdtModifiedon() + '\'' +
	            '}';
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
	            
	            // ✅ ALWAYS output null for keyid if it's null/empty
	            if (field.name().equals("keyid") && (val == null || val.toString().trim().isEmpty() || val.equals("{}"))) {
	                sb.append("null");  // Don't quote it - send actual null
	            } else if (val == null || val.equals("{}")) {
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

	public static String toJsonManualList(List<BdmTlWhywhydtl> list) {
	    if (list == null || list.isEmpty()) {
	        return "[]";
	    }
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }
	    sb.append("]");
	    return sb.toString();
	}

	public static List<BdmTlWhywhydtl> fromJsonList(String json) {
	    List<BdmTlWhywhydtl> list = new ArrayList<>();
	    JSONArray arr = JSONArray.fromObject(json);
	    for (int i = 0; i < arr.length(); i++) {
	        list.add(fromJson(arr.getJSONObject(i).toString()));
	    }
	    return list;
	}

	public static BdmTlWhywhydtl fromJson(String json) {
	    JSONObject obj = JSONObject.fromObject(json);
	    BdmTlWhywhydtl dtl = new BdmTlWhywhydtl();
	    for (tableFldConstants field : tableFldConstants.values()) {
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
	        dtl.saveArray[field.ordinal()] = val != null && val.equals("null") ? null : val;
	    }
	    return dtl;
	}

}

