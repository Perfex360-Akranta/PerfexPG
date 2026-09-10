package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public String toJsonManual() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"pwrdKeyid\":\"").append(nullSafe(getPwrdkeyid())).append("\",");
		sb.append("\"pwrdMasterid\":\"").append(nullSafe(getPwrdmasterid())).append("\",");
		sb.append("\"pwrdTradeid\":\"").append(nullSafe(getPwrdtradeid())).append("\",");
		sb.append("\"pwrdEmpid\":\"").append(nullSafe(getPwrdempid())).append("\",");
		sb.append("\"pwrdEffectfrom\":\"").append(nullSafe(getPwrdeffectfrom())).append("\",");
		sb.append("\"pwrdEffecttill\":\"").append(nullSafe(getPwrdeffecttill())).append("\",");
		sb.append("\"pwrdTempfield1\":\"").append(nullSafe(getPwrdtempfield1())).append("\",");
		sb.append("\"pwrdTempfield2\":\"").append(nullSafe(getPwrdtempfield2())).append("\",");
		sb.append("\"pwrdTempfield3\":\"").append(nullSafe(getPwrdtempfield3())).append("\",");
		sb.append("\"pwrdTempfield4\":\"").append(nullSafe(getPwrdtempfield4())).append("\",");
		sb.append("\"pwrdActive\":\"").append(nullSafe(getPwrdactive())).append("\",");
		sb.append("\"pwrdCreatedby\":\"").append(nullSafe(getPwrdcreatedby())).append("\"");
		sb.append("}");
		return sb.toString();
	}

	private String nullSafe(String val) {
		if (val == null) return "";
		return val.replace("\"", "\\\"");
	}

	public static BAL_PlmTlWorespdtl fromJson(String json) {
		net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
		return fromJsonObject(obj);
	}

	public static BAL_PlmTlWorespdtl fromJsonObject(net.sf.json.JSONObject obj) {
		BAL_PlmTlWorespdtl dtl = new BAL_PlmTlWorespdtl();
		dtl.setPwrdkeyid(obj.optString("pwrdKeyid", ""));
		dtl.setPwrdmasterid(obj.optString("pwrdMasterid", ""));
		dtl.setPwrdtradeid(obj.optString("pwrdTradeid", ""));
		dtl.setPwrdempid(obj.optString("pwrdEmpid", ""));
		dtl.setPwrdeffectfrom(obj.optString("pwrdEffectfrom", ""));
		dtl.setPwrdeffecttill(obj.optString("pwrdEffecttill", ""));
		dtl.setPwrdtempfield1(obj.optString("pwrdTempfield1", ""));
		dtl.setPwrdtempfield2(obj.optString("pwrdTempfield2", ""));
		dtl.setPwrdtempfield3(obj.optString("pwrdTempfield3", ""));
		dtl.setPwrdtempfield4(obj.optString("pwrdTempfield4", ""));
		dtl.setPwrdactive(obj.optString("pwrdActive", ""));
		dtl.setPwrdcreatedby(obj.optString("pwrdCreatedby", ""));
		return dtl;
	}

}

