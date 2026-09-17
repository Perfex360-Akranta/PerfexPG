package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PlmTlWorespmst.tableFldConstants;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PlmTlWorespdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		pwrdkeyid, pwrdmasterid, pwrdtradeid, pwrdempid, pwrdeffectfrom
		, pwrdeffecttill, pwrdtempfield1, pwrdtempfield2, pwrdtempfield3
		, pwrdtempfield4, pwrdactive, pwrdcreatedby, pwrdcreatedon, pwrdmodifiedon
	}

	public BAL_PlmTlWorespdtl()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPwrdkeyid() {
		return (String) saveArray[ tableFldConstants.pwrdkeyid.ordinal() ];
	}

	public void setPwrdkeyid(String pwrdkeyid) {
		saveArray[ tableFldConstants.pwrdkeyid.ordinal() ] = pwrdkeyid;
	}

	public String getPwrdmasterid() {
		return (String) saveArray[ tableFldConstants.pwrdmasterid.ordinal() ];
	}

	public void setPwrdmasterid(String pwrdmasterid) {
		saveArray[ tableFldConstants.pwrdmasterid.ordinal() ] = pwrdmasterid;
	}

	public String getPwrdtradeid() {
		return (String) saveArray[ tableFldConstants.pwrdtradeid.ordinal() ];
	}

	public void setPwrdtradeid(String pwrdtradeid) {
		saveArray[ tableFldConstants.pwrdtradeid.ordinal() ] = pwrdtradeid;
	}

	public String getPwrdempid() {
		return (String) saveArray[ tableFldConstants.pwrdempid.ordinal() ];
	}

	public void setPwrdempid(String pwrdempid) {
		saveArray[ tableFldConstants.pwrdempid.ordinal() ] = pwrdempid;
	}

	public String getPwrdeffectfrom() {
		return (String) saveArray[ tableFldConstants.pwrdeffectfrom.ordinal() ];
	}

	public void setPwrdeffectfrom(String pwrdeffectfrom) {
		saveArray[ tableFldConstants.pwrdeffectfrom.ordinal() ] = pwrdeffectfrom;
	}

	public String getPwrdeffecttill() {
		return (String) saveArray[ tableFldConstants.pwrdeffecttill.ordinal() ];
	}

	public void setPwrdeffecttill(String pwrdeffecttill) {
		saveArray[ tableFldConstants.pwrdeffecttill.ordinal() ] = pwrdeffecttill;
	}

	public String getPwrdtempfield1() {
		return (String) saveArray[ tableFldConstants.pwrdtempfield1.ordinal() ];
	}

	public void setPwrdtempfield1(String pwrdtempfield1) {
		saveArray[ tableFldConstants.pwrdtempfield1.ordinal() ] = pwrdtempfield1;
	}

	public String getPwrdtempfield2() {
		return (String) saveArray[ tableFldConstants.pwrdtempfield2.ordinal() ];
	}

	public void setPwrdtempfield2(String pwrdtempfield2) {
		saveArray[ tableFldConstants.pwrdtempfield2.ordinal() ] = pwrdtempfield2;
	}

	public String getPwrdtempfield3() {
		return (String) saveArray[ tableFldConstants.pwrdtempfield3.ordinal() ];
	}

	public void setPwrdtempfield3(String pwrdtempfield3) {
		saveArray[ tableFldConstants.pwrdtempfield3.ordinal() ] = pwrdtempfield3;
	}

	public String getPwrdtempfield4() {
		return (String) saveArray[ tableFldConstants.pwrdtempfield4.ordinal() ];
	}

	public void setPwrdtempfield4(String pwrdtempfield4) {
		saveArray[ tableFldConstants.pwrdtempfield4.ordinal() ] = pwrdtempfield4;
	}

	public String getPwrdactive() {
		return (String) saveArray[ tableFldConstants.pwrdactive.ordinal() ];
	}

	public void setPwrdactive(String pwrdactive) {
		saveArray[ tableFldConstants.pwrdactive.ordinal() ] = pwrdactive;
	}

	public String getPwrdcreatedby() {
		return (String) saveArray[ tableFldConstants.pwrdcreatedby.ordinal() ];
	}

	public void setPwrdcreatedby(String pwrdcreatedby) {
		saveArray[ tableFldConstants.pwrdcreatedby.ordinal() ] = pwrdcreatedby;
	}

	public String getPwrdcreatedon() {
		return (String) saveArray[ tableFldConstants.pwrdcreatedon.ordinal() ];
	}

	public void setPwrdcreatedon(String pwrdcreatedon) {
		saveArray[ tableFldConstants.pwrdcreatedon.ordinal() ] = pwrdcreatedon;
	}

	public String getPwrdmodifiedon() {
		return (String) saveArray[ tableFldConstants.pwrdmodifiedon.ordinal() ];
	}

	public void setPwrdmodifiedon(String pwrdmodifiedon) {
		saveArray[ tableFldConstants.pwrdmodifiedon.ordinal() ] = pwrdmodifiedon;
	}
	
	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	
	/*
	 * public String toJsonManual() { StringBuilder sb = new StringBuilder();
	 * sb.append("{");
	 * sb.append("\"pwrdKeyid\":\"").append(nullSafe(getPwrdkeyid())).append("\",");
	 * sb.append("\"pwrdMasterid\":\"").append(nullSafe(getPwrdmasterid())).append(
	 * "\",");
	 * sb.append("\"pwrdTradeid\":\"").append(nullSafe(getPwrdtradeid())).append(
	 * "\",");
	 * sb.append("\"pwrdEmpid\":\"").append(nullSafe(getPwrdempid())).append("\",");
	 * sb.append("\"pwrdEffectfrom\":\"").append(nullSafe(getPwrdeffectfrom())).
	 * append("\",");
	 * sb.append("\"pwrdEffecttill\":\"").append(nullSafe(getPwrdeffecttill())).
	 * append("\",");
	 * sb.append("\"pwrdTempfield1\":\"").append(nullSafe(getPwrdtempfield1())).
	 * append("\",");
	 * sb.append("\"pwrdTempfield2\":\"").append(nullSafe(getPwrdtempfield2())).
	 * append("\",");
	 * sb.append("\"pwrdTempfield3\":\"").append(nullSafe(getPwrdtempfield3())).
	 * append("\",");
	 * sb.append("\"pwrdTempfield4\":\"").append(nullSafe(getPwrdtempfield4())).
	 * append("\",");
	 * sb.append("\"pwrdActive\":\"").append(nullSafe(getPwrdactive())).append("\","
	 * );
	 * sb.append("\"pwrdCreatedby\":\"").append(nullSafe(getPwrdcreatedby())).append
	 * ("\""); sb.append("}"); return sb.toString(); }
	 * 
	 * private String nullSafe(String val) { if (val == null) return ""; return
	 * val.replace("\"", "\\\""); }
	 * 
	 * public static BAL_PlmTlWorespdtl fromJson(String json) {
	 * net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json); return
	 * fromJsonObject(obj); }
	 * 
	 * public static BAL_PlmTlWorespdtl fromJsonObject(net.sf.json.JSONObject obj) {
	 * BAL_PlmTlWorespdtl dtl = new BAL_PlmTlWorespdtl();
	 * dtl.setPwrdkeyid(obj.optString("pwrdKeyid", ""));
	 * dtl.setPwrdmasterid(obj.optString("pwrdMasterid", ""));
	 * dtl.setPwrdtradeid(obj.optString("pwrdTradeid", ""));
	 * dtl.setPwrdempid(obj.optString("pwrdEmpid", ""));
	 * dtl.setPwrdeffectfrom(obj.optString("pwrdEffectfrom", ""));
	 * dtl.setPwrdeffecttill(obj.optString("pwrdEffecttill", ""));
	 * dtl.setPwrdtempfield1(obj.optString("pwrdTempfield1", ""));
	 * dtl.setPwrdtempfield2(obj.optString("pwrdTempfield2", ""));
	 * dtl.setPwrdtempfield3(obj.optString("pwrdTempfield3", ""));
	 * dtl.setPwrdtempfield4(obj.optString("pwrdTempfield4", ""));
	 * dtl.setPwrdactive(obj.optString("pwrdActive", ""));
	 * dtl.setPwrdcreatedby(obj.optString("pwrdCreatedby", "")); return dtl; }
	 */
	
//	public String toJsonManual() {
//	    StringBuilder sb = new StringBuilder();
//	    sb.append("{");
//	    sb.append("\"keyid\":").append(jval(getPwrdkeyid())).append(",");
//	    sb.append("\"masterid\":").append(jval(getPwrdmasterid())).append(",");
//	    sb.append("\"tradeid\":").append(jval(getPwrdtradeid())).append(",");
//	    sb.append("\"empid\":").append(jval(getPwrdempid())).append(",");
//	    sb.append("\"effectfrom\":").append(jval(toIso(getPwrdeffectfrom()))).append(",");
//	    sb.append("\"effecttill\":").append(jval(toIso(getPwrdeffecttill()))).append(",");
//	    sb.append("\"tempfield1\":").append(jval(getPwrdtempfield1())).append(",");
//	    sb.append("\"tempfield2\":").append(jval(getPwrdtempfield2())).append(",");
//	    sb.append("\"tempfield3\":").append(jval(getPwrdtempfield3())).append(",");
//	    sb.append("\"tempfield4\":").append(jval(getPwrdtempfield4())).append(",");
//	    sb.append("\"active\":").append(jval(getPwrdactive())).append(",");
//	    sb.append("\"createdby\":").append(jval(getPwrdcreatedby()));
//	    sb.append("}");
//	    return sb.toString();
//	}
//
//	private String jval(String val) {
//	    if (val == null || val.isBlank() || "{}".equals(val)) return "null";
//	    return "\"" + val.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
//	}
//
//	// dd-MMM-yyyy HH:mm:ss (or plain dd-MMM-yyyy) -> ISO yyyy-MM-dd'T'HH:mm:ss
//	private String toIso(String value) {
//	    if (value == null || value.isBlank() || "{}".equals(value)) return null;
//	    String v = value.trim();
//	    try {
//	        java.text.SimpleDateFormat withTime =
//	            new java.text.SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", java.util.Locale.ENGLISH);
//	        withTime.setLenient(false);
//	        java.util.Date d = withTime.parse(v);
//	        return new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.ENGLISH).format(d);
//	    } catch (Exception e1) {
//	        try {
//	            java.text.SimpleDateFormat dateOnly =
//	                new java.text.SimpleDateFormat("dd-MMM-yyyy", java.util.Locale.ENGLISH);
//	            dateOnly.setLenient(false);
//	            java.util.Date d = dateOnly.parse(v);
//	            return new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.ENGLISH).format(d);
//	        } catch (Exception e2) {
//	            return null;
//	        }
//	    }
//	}
//
//	public static BAL_PlmTlWorespdtl fromJson(String json) {
//	    net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
//	    return fromJsonObject(obj);
//	}
//
//	public static BAL_PlmTlWorespdtl fromJsonObject(net.sf.json.JSONObject obj) {
//	    BAL_PlmTlWorespdtl dtl = new BAL_PlmTlWorespdtl();
//	    dtl.setPwrdkeyid(obj.optString("keyid", ""));
//	    dtl.setPwrdmasterid(obj.optString("masterid", ""));
//	    dtl.setPwrdtradeid(obj.optString("tradeid", ""));
//	    dtl.setPwrdempid(obj.optString("empid", ""));
//	    dtl.setPwrdeffectfrom(obj.optString("effectfrom", ""));
//	    dtl.setPwrdeffecttill(obj.optString("effecttill", ""));
//	    dtl.setPwrdtempfield1(obj.optString("tempfield1", ""));
//	    dtl.setPwrdtempfield2(obj.optString("tempfield2", ""));
//	    dtl.setPwrdtempfield3(obj.optString("tempfield3", ""));
//	    dtl.setPwrdtempfield4(obj.optString("tempfield4", ""));
//	    dtl.setPwrdactive(obj.optString("active", ""));
//	    dtl.setPwrdcreatedby(obj.optString("createdby", ""));
//	    return dtl;
//	}
	
	
	
//	public String toJsonManual() {
//	    StringBuilder sb = new StringBuilder();
//	    sb.append("{");
//	    boolean first = true;
//	    for (tableFldConstants field : tableFldConstants.values()) {
//	        if (!first) sb.append(",");
//	        sb.append("\"").append(field.name()).append("\":");
//	        Object val = saveArray[field.ordinal()];
//	        if (val == null || "{}".equals(val) || val.toString().isBlank()) {
//	            sb.append("null");
//	        } else {
//	            sb.append("\"").append(val.toString().replace("\\", "\\\\").replace("\"", "\\\"")).append("\"");
//	        }
//	        first = false;
//	    }
//	    sb.append("}");
//	    return sb.toString();
//	}
//
//	public static BAL_PlmTlWorespdtl fromJson(String json) {
//	    net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
//	    return fromJsonObject(obj);
//	}
//
//	public static BAL_PlmTlWorespdtl fromJsonObject(net.sf.json.JSONObject obj) {
//	    BAL_PlmTlWorespdtl dtl = new BAL_PlmTlWorespdtl();
//	    for (tableFldConstants field : tableFldConstants.values()) {
//	        String val = obj.optString(field.name(), null);
//	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
//	            val = null;
//	        }
//	        dtl.setValue(field, val);
//	    }
//	    return dtl;
//	}
	
	//elumalai
	
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
                if (field.name().equals("keyid") && val == null) {
                    sb.append("null");
                } else if (val == null) {
                    sb.append("\"\"");
                } else {
                    sb.append("\"").append(val.toString().replace("\"", "\\\"")).append("\"");
                }
                first = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }
	
	// from json
	public static BAL_PlmTlWorespdtl fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response (Master): [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        BAL_PlmTlWorespdtl dtl = new BAL_PlmTlWorespdtl();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.opt(key));
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            dtl.setValue(field, val);
        }

        return dtl;
    }

}

