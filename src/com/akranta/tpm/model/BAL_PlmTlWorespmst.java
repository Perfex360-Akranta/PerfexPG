package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration.tableFldConstants;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PlmTlWorespmst {

	private  Object [] saveArray = null;  
	private List <BAL_PlmTlWorespdtl> woRespDetail;	

	public enum   tableFldConstants
	{
		pwrmkeyid, pwrmfactoryid, pwrmsectionid, pwrmcellid, pwrmmachineid
		, pwrmlevel, pwrmtradewise, pwrmgeneral, pwrmtempfield1, pwrmtempfield2
		, pwrmtempfield3, pwrmtempfield4, pwrmactive, pwrmcreatedby, pwrmcreatedon
		, pwrmmodifiedon
	}

	public BAL_PlmTlWorespmst()
	{
		saveArray = new  Object [ 16 ];
		setWoRespDetail(new ArrayList <BAL_PlmTlWorespdtl>());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPwrmkeyid() {
		return (String) saveArray[ tableFldConstants.pwrmkeyid.ordinal() ];
	}

	public void setPwrmkeyid(String pwrmkeyid) {
		saveArray[ tableFldConstants.pwrmkeyid.ordinal() ] = pwrmkeyid;
	}

	public String getPwrmfactoryid() {
		return (String) saveArray[ tableFldConstants.pwrmfactoryid.ordinal() ];
	}

	public void setPwrmfactoryid(String pwrmfactoryid) {
		saveArray[ tableFldConstants.pwrmfactoryid.ordinal() ] = pwrmfactoryid;
	}

	public String getPwrmsectionid() {
		return (String) saveArray[ tableFldConstants.pwrmsectionid.ordinal() ];
	}

	public void setPwrmsectionid(String pwrmsectionid) {
		saveArray[ tableFldConstants.pwrmsectionid.ordinal() ] = pwrmsectionid;
	}

	public String getPwrmcellid() {
		return (String) saveArray[ tableFldConstants.pwrmcellid.ordinal() ];
	}

	public void setPwrmcellid(String pwrmcellid) {
		saveArray[ tableFldConstants.pwrmcellid.ordinal() ] = pwrmcellid;
	}

	public String getPwrmmachineid() {
		return (String) saveArray[ tableFldConstants.pwrmmachineid.ordinal() ];
	}

	public void setPwrmmachineid(String pwrmmachineid) {
		saveArray[ tableFldConstants.pwrmmachineid.ordinal() ] = pwrmmachineid;
	}

	public String getPwrmlevel() {
		return (String) saveArray[ tableFldConstants.pwrmlevel.ordinal() ];
	}

	public void setPwrmlevel(String pwrmlevel) {
		saveArray[ tableFldConstants.pwrmlevel.ordinal() ] = pwrmlevel;
	}

	public String getPwrmtradewise() {
		return (String) saveArray[ tableFldConstants.pwrmtradewise.ordinal() ];
	}

	public void setPwrmtradewise(String pwrmtradewise) {
		saveArray[ tableFldConstants.pwrmtradewise.ordinal() ] = pwrmtradewise;
	}

	public String getPwrmgeneral() {
		return (String) saveArray[ tableFldConstants.pwrmgeneral.ordinal() ];
	}

	public void setPwrmgeneral(String pwrmgeneral) {
		saveArray[ tableFldConstants.pwrmgeneral.ordinal() ] = pwrmgeneral;
	}

	public String getPwrmtempfield1() {
		return (String) saveArray[ tableFldConstants.pwrmtempfield1.ordinal() ];
	}

	public void setPwrmtempfield1(String pwrmtempfield1) {
		saveArray[ tableFldConstants.pwrmtempfield1.ordinal() ] = pwrmtempfield1;
	}

	public String getPwrmtempfield2() {
		return (String) saveArray[ tableFldConstants.pwrmtempfield2.ordinal() ];
	}

	public void setPwrmtempfield2(String pwrmtempfield2) {
		saveArray[ tableFldConstants.pwrmtempfield2.ordinal() ] = pwrmtempfield2;
	}

	public String getPwrmtempfield3() {
		return (String) saveArray[ tableFldConstants.pwrmtempfield3.ordinal() ];
	}

	public void setPwrmtempfield3(String pwrmtempfield3) {
		saveArray[ tableFldConstants.pwrmtempfield3.ordinal() ] = pwrmtempfield3;
	}

	public String getPwrmtempfield4() {
		return (String) saveArray[ tableFldConstants.pwrmtempfield4.ordinal() ];
	}

	public void setPwrmtempfield4(String pwrmtempfield4) {
		saveArray[ tableFldConstants.pwrmtempfield4.ordinal() ] = pwrmtempfield4;
	}

	public String getPwrmactive() {
		return (String) saveArray[ tableFldConstants.pwrmactive.ordinal() ];
	}

	public void setPwrmactive(String pwrmactive) {
		saveArray[ tableFldConstants.pwrmactive.ordinal() ] = pwrmactive;
	}

	public String getPwrmcreatedby() {
		return (String) saveArray[ tableFldConstants.pwrmcreatedby.ordinal() ];
	}

	public void setPwrmcreatedby(String pwrmcreatedby) {
		saveArray[ tableFldConstants.pwrmcreatedby.ordinal() ] = pwrmcreatedby;
	}

	public String getPwrmcreatedon() {
		return (String) saveArray[ tableFldConstants.pwrmcreatedon.ordinal() ];
	}

	public void setPwrmcreatedon(String pwrmcreatedon) {
		saveArray[ tableFldConstants.pwrmcreatedon.ordinal() ] = pwrmcreatedon;
	}

	public String getPwrmmodifiedon() {
		return (String) saveArray[ tableFldConstants.pwrmmodifiedon.ordinal() ];
	}

	public void setPwrmmodifiedon(String pwrmmodifiedon) {
		saveArray[ tableFldConstants.pwrmmodifiedon.ordinal() ] = pwrmmodifiedon;
	}

	public void setWoRespDetail(List <BAL_PlmTlWorespdtl> woRespDetail) {
		this.woRespDetail = woRespDetail;
	}

	public List <BAL_PlmTlWorespdtl> getWoRespDetail() {
		return woRespDetail;
	}
	
	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	
	/*
	 * public String toJsonManual() { StringBuilder sb = new StringBuilder();
	 * sb.append("{");
	 * sb.append("\"pwrmKeyid\":\"").append(nullSafe(getPwrmkeyid())).append("\",");
	 * sb.append("\"pwrmFactoryid\":\"").append(nullSafe(getPwrmfactoryid())).append
	 * ("\",");
	 * sb.append("\"pwrmSectionid\":\"").append(nullSafe(getPwrmsectionid())).append
	 * ("\",");
	 * sb.append("\"pwrmCellid\":\"").append(nullSafe(getPwrmcellid())).append("\","
	 * );
	 * sb.append("\"pwrmMachineid\":\"").append(nullSafe(getPwrmmachineid())).append
	 * ("\",");
	 * sb.append("\"pwrmLevel\":\"").append(nullSafe(getPwrmlevel())).append("\",");
	 * sb.append("\"pwrmTradewise\":\"").append(nullSafe(getPwrmtradewise())).append
	 * ("\",");
	 * sb.append("\"pwrmGeneral\":\"").append(nullSafe(getPwrmgeneral())).append(
	 * "\",");
	 * sb.append("\"pwrmTempfield1\":\"").append(nullSafe(getPwrmtempfield1())).
	 * append("\",");
	 * sb.append("\"pwrmTempfield2\":\"").append(nullSafe(getPwrmtempfield2())).
	 * append("\",");
	 * sb.append("\"pwrmTempfield3\":\"").append(nullSafe(getPwrmtempfield3())).
	 * append("\",");
	 * sb.append("\"pwrmTempfield4\":\"").append(nullSafe(getPwrmtempfield4())).
	 * append("\",");
	 * sb.append("\"pwrmActive\":\"").append(nullSafe(getPwrmactive())).append("\","
	 * );
	 * sb.append("\"pwrmCreatedby\":\"").append(nullSafe(getPwrmcreatedby())).append
	 * ("\",");
	 * 
	 * sb.append("\"woRespDetail\":["); if (woRespDetail != null) { for (int i = 0;
	 * i < woRespDetail.size(); i++) { if (i > 0) sb.append(",");
	 * sb.append(woRespDetail.get(i).toJsonManual()); } } sb.append("]");
	 * 
	 * sb.append("}"); return sb.toString(); }
	 * 
	 * private String nullSafe(String val) { if (val == null) return ""; return
	 * val.replace("\"", "\\\""); }
	 * 
	 * public static BAL_PlmTlWorespmst fromJson(String json) {
	 * net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
	 * BAL_PlmTlWorespmst mst = new BAL_PlmTlWorespmst();
	 * 
	 * mst.setPwrmkeyid(obj.optString("pwrmKeyid", ""));
	 * mst.setPwrmfactoryid(obj.optString("pwrmFactoryid", ""));
	 * mst.setPwrmsectionid(obj.optString("pwrmSectionid", ""));
	 * mst.setPwrmcellid(obj.optString("pwrmCellid", ""));
	 * mst.setPwrmmachineid(obj.optString("pwrmMachineid", ""));
	 * mst.setPwrmlevel(obj.optString("pwrmLevel", ""));
	 * mst.setPwrmtradewise(obj.optString("pwrmTradewise", ""));
	 * mst.setPwrmgeneral(obj.optString("pwrmGeneral", ""));
	 * mst.setPwrmtempfield1(obj.optString("pwrmTempfield1", ""));
	 * mst.setPwrmtempfield2(obj.optString("pwrmTempfield2", ""));
	 * mst.setPwrmtempfield3(obj.optString("pwrmTempfield3", ""));
	 * mst.setPwrmtempfield4(obj.optString("pwrmTempfield4", ""));
	 * mst.setPwrmactive(obj.optString("pwrmActive", ""));
	 * mst.setPwrmcreatedby(obj.optString("pwrmCreatedby", ""));
	 * 
	 * List<BAL_PlmTlWorespdtl> detailList = new ArrayList<BAL_PlmTlWorespdtl>(); if
	 * (obj.has("woRespDetail")) { net.sf.json.JSONArray arr =
	 * obj.getJSONArray("woRespDetail"); for (int i = 0; i < arr.length(); i++) {
	 * detailList.add(BAL_PlmTlWorespdtl.fromJsonObject(arr.getJSONObject(i))); } }
	 * mst.setWoRespDetail(detailList);
	 * 
	 * return mst; }
	 */
	
//	public String toJsonManual() {
//	    StringBuilder sb = new StringBuilder();
//	    sb.append("{\"worespMst\":{");
//	    sb.append("\"keyid\":").append(jval(getPwrmkeyid())).append(",");
//	    sb.append("\"factoryid\":").append(jval(getPwrmfactoryid())).append(",");
//	    sb.append("\"sectionid\":").append(jval(getPwrmsectionid())).append(",");
//	    sb.append("\"cellid\":").append(jval(getPwrmcellid())).append(",");
//	    sb.append("\"machineid\":").append(jval(getPwrmmachineid())).append(",");
//	    sb.append("\"level\":").append(jval(getPwrmlevel())).append(",");
//	    sb.append("\"tradewise\":").append(jval(getPwrmtradewise())).append(",");
//	    sb.append("\"general\":").append(jval(getPwrmgeneral())).append(",");
//	    sb.append("\"tempfield1\":").append(jval(getPwrmtempfield1())).append(",");
//	    sb.append("\"tempfield2\":").append(jval(getPwrmtempfield2())).append(",");
//	    sb.append("\"tempfield3\":").append(jval(getPwrmtempfield3())).append(",");
//	    sb.append("\"tempfield4\":").append(jval(getPwrmtempfield4())).append(",");
//	    sb.append("\"active\":").append(jval(getPwrmactive())).append(",");
//	    sb.append("\"createdby\":").append(jval(getPwrmcreatedby()));
//	    sb.append("},\"woRespDetail\":[");
//
//	    if (woRespDetail != null) {
//	        for (int i = 0; i < woRespDetail.size(); i++) {
//	            if (i > 0) sb.append(",");
//	            sb.append(woRespDetail.get(i).toJsonManual());
//	        }
//	    }
//	    sb.append("]}");
//	    return sb.toString();
//	}
//
//	// "" / "{}" / null -> JSON null; otherwise a quoted, escaped string
//	private String jval(String val) {
//	    if (val == null || val.isBlank() || "{}".equals(val)) return "null";
//	    return "\"" + val.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
//	}
//
//	public static BAL_PlmTlWorespmst fromJson(String json) {
//	    net.sf.json.JSONObject root = net.sf.json.JSONObject.fromObject(json);
//	    net.sf.json.JSONObject obj = root.getJSONObject("worespMst");
//
//	    BAL_PlmTlWorespmst mst = new BAL_PlmTlWorespmst();
//	    mst.setPwrmkeyid(obj.optString("keyid", ""));
//	    mst.setPwrmfactoryid(obj.optString("factoryid", ""));
//	    mst.setPwrmsectionid(obj.optString("sectionid", ""));
//	    mst.setPwrmcellid(obj.optString("cellid", ""));
//	    mst.setPwrmmachineid(obj.optString("machineid", ""));
//	    mst.setPwrmlevel(obj.optString("level", ""));
//	    mst.setPwrmtradewise(obj.optString("tradewise", ""));
//	    mst.setPwrmgeneral(obj.optString("general", ""));
//	    mst.setPwrmtempfield1(obj.optString("tempfield1", ""));
//	    mst.setPwrmtempfield2(obj.optString("tempfield2", ""));
//	    mst.setPwrmtempfield3(obj.optString("tempfield3", ""));
//	    mst.setPwrmtempfield4(obj.optString("tempfield4", ""));
//	    mst.setPwrmactive(obj.optString("active", ""));
//	    mst.setPwrmcreatedby(obj.optString("createdby", ""));
//
//	    List<BAL_PlmTlWorespdtl> detailList = new ArrayList<BAL_PlmTlWorespdtl>();
//	    if (root.has("woRespDetail")) {
//	        net.sf.json.JSONArray arr = root.getJSONArray("woRespDetail");
//	        for (int i = 0; i < arr.length(); i++) {
//	            detailList.add(BAL_PlmTlWorespdtl.fromJsonObject(arr.getJSONObject(i)));
//	        }
//	    }
//	    mst.setWoRespDetail(detailList);
//	    return mst;
//	}
	
	
//	public String toJsonManual() {
//	    StringBuilder sb = new StringBuilder();
//	    sb.append("{\"master\":{");
//
//	    boolean first = true;
//	    for (tableFldConstants field : tableFldConstants.values()) {
//	        int index = field.ordinal();
//	        if (!first) sb.append(",");
//	        sb.append("\"").append(field.name()).append("\":");
//	        Object val = saveArray[index];
//	        if (val == null || "{}".equals(val) || val.toString().isBlank()) {
//	            sb.append("null");
//	        } else {
//	            sb.append("\"").append(val.toString().replace("\\", "\\\\").replace("\"", "\\\"")).append("\"");
//	        }
//	        first = false;
//	    }
//	    sb.append("},\"details\":[");
//
//	    if (woRespDetail != null) {
//	        for (int i = 0; i < woRespDetail.size(); i++) {
//	            if (i > 0) sb.append(",");
//	            sb.append(woRespDetail.get(i).toJsonManual());
//	        }
//	    }
//	    sb.append("]}");
//	    return sb.toString();
//	}

//	public static BAL_PlmTlWorespmst fromJson(String json) {
//	    net.sf.json.JSONObject root = net.sf.json.JSONObject.fromObject(json);
//	    net.sf.json.JSONObject obj = root.getJSONObject("master");
//
//	    BAL_PlmTlWorespmst mst = new BAL_PlmTlWorespmst();
//	    for (tableFldConstants field : tableFldConstants.values()) {
//	        String val = obj.optString(field.name(), null);
//	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
//	            val = null;
//	        }
//	        mst.setValue(field, val);
//	    }
//
//	    List<BAL_PlmTlWorespdtl> detailList = new ArrayList<BAL_PlmTlWorespdtl>();
//	    if (root.has("details")) {
//	        net.sf.json.JSONArray arr = root.getJSONArray("details");
//	        for (int i = 0; i < arr.length(); i++) {
//	            detailList.add(BAL_PlmTlWorespdtl.fromJsonObject(arr.getJSONObject(i)));
//	        }
//	    }
//	    mst.setWoRespDetail(detailList);
//	    return mst;
//	}
	
	//elumalai
	
	public String toJsonManual() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{\"master\":{");

	    boolean first = true;
	    for (tableFldConstants field : tableFldConstants.values()) {
	        int index = field.ordinal();
	        if (index < saveArray.length) {
	            if (!first) sb.append(",");
	            sb.append("\"").append(field.name()).append("\":");
	            Object val = saveArray[index];
	            if (field.name().equals("pwrmkeyid") && val == null) {
	                sb.append("null");
	            } else if (val == null) {
	                sb.append("\"\"");
	            } else {
	                sb.append("\"").append(val.toString().replace("\"", "\\\"")).append("\"");
	            }
	            first = false;
	        }
	    }
	    sb.append("},\"details\":[");

	    List<BAL_PlmTlWorespdtl> detailList = getWoRespDetail();
	    if (detailList != null) {
	        for (int i = 0; i < detailList.size(); i++) {
	            if (i > 0) sb.append(",");
	            sb.append(detailList.get(i).toJsonManual());
	        }
	    }
	    sb.append("]}");
	    return sb.toString();
	}
	
	//from json
	
	public static BAL_PlmTlWorespmst fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response (Master): [" + json + "]");

	    JSONObject root = JSONObject.fromObject(json);
	    JSONObject obj = root.has("master") ? root.getJSONObject("master") : root;
	    BAL_PlmTlWorespmst mst = new BAL_PlmTlWorespmst();

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	            val = "";
	        }
	        mst.setValue(field, val);
	    }

	    List<BAL_PlmTlWorespdtl> detailList = new ArrayList<BAL_PlmTlWorespdtl>();
	    if (root.has("details")) {
	        JSONArray arr = root.getJSONArray("details");
	        for (int i = 0; i < arr.length(); i++) {
	            detailList.add(BAL_PlmTlWorespdtl.fromJson(arr.getJSONObject(i).toString()));
	        }
	    }
	    mst.setWoRespDetail(detailList);

	    return mst;
	}
}

