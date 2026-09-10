package com.akranta.tpm.model;
import java.util.ArrayList;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import java.util.List;

public class BAL_PlmTlPlanconfiguration {
	private List <BAL_PlmTlWorespmst> woRespMast;
	private List <BAL_PlmTlWorespdtl> woRespDetail;
	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, level, weekno
		, monthly, quarterly, halfyearly, yearly, yearly2, yearly3, yearly4
		, yearly5, yearly6, yearly7, yearly8, yearly9, yearly10, frequency
		, assemblyid, tempfield1, tempfield2, tempfield3, tempfield4
		,flid,elementid, active, createdby, createdon, modifiedon
	}

	public BAL_PlmTlPlanconfiguration()
	{
		saveArray = new  Object [ 32 ];
		setWoRespMast(new ArrayList <BAL_PlmTlWorespmst>());
		setWoRespDetail(new ArrayList <BAL_PlmTlWorespdtl>());
	}
	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		 this.saveArray = saveArray;
	}
	
	

	public String getPplcKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPplcKeyid(String pplcKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pplcKeyid;
	}

	public String getPplcFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setPplcFactoryid(String pplcFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = pplcFactoryid;
	}
	public String getPplcFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPplcFlid(String pplcFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = pplcFlid;
	}
	public String getPplcElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setPplcElementid(String pplcElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = pplcElementid;
	}
	public String getPplcSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setPplcSectionid(String pplcSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = pplcSectionid;
	}

	public String getPplcCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setPplcCellid(String pplcCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = pplcCellid;
	}

	public String getPplcMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setPplcMachineid(String pplcMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = pplcMachineid;
	}

	public String getPplcLevel() {
		return (String) saveArray[ tableFldConstants.level.ordinal() ];
	}

	public void setPplcLevel(String pplcLevel) {
		saveArray[ tableFldConstants.level.ordinal() ] = pplcLevel;
	}

	public String getPplcWeekno() {
		return (String) saveArray[ tableFldConstants.weekno.ordinal() ];
	}

	public void setPplcWeekno(String pplcWeekno) {
		saveArray[ tableFldConstants.weekno.ordinal() ] = pplcWeekno;
	}

	public String getPplcMonthly() {
		return (String) saveArray[ tableFldConstants.monthly.ordinal() ];
	}

	public void setPplcMonthly(String pplcMonthly) {
		saveArray[ tableFldConstants.monthly.ordinal() ] = pplcMonthly;
	}

	public String getPplcQuarterly() {
		return (String) saveArray[ tableFldConstants.quarterly.ordinal() ];
	}

	public void setPplcQuarterly(String pplcQuarterly) {
		saveArray[ tableFldConstants.quarterly.ordinal() ] = pplcQuarterly;
	}

	public String getPplcHalfyearly() {
		return (String) saveArray[ tableFldConstants.halfyearly.ordinal() ];
	}

	public void setPplcHalfyearly(String pplcHalfyearly) {
		saveArray[ tableFldConstants.halfyearly.ordinal() ] = pplcHalfyearly;
	}

	public String getPplcYearly() {
		return (String) saveArray[ tableFldConstants.yearly.ordinal() ];
	}

	public void setPplcYearly(String pplcYearly) {
		saveArray[ tableFldConstants.yearly.ordinal() ] = pplcYearly;
	}

	public String getPplcYearly2() {
		return (String) saveArray[ tableFldConstants.yearly2.ordinal() ];
	}

	public void setPplcYearly2(String pplcYearly2) {
		saveArray[ tableFldConstants.yearly2.ordinal() ] = pplcYearly2;
	}

	public String getPplcYearly3() {
		return (String) saveArray[ tableFldConstants.yearly3.ordinal() ];
	}

	public void setPplcYearly3(String pplcYearly3) {
		saveArray[ tableFldConstants.yearly3.ordinal() ] = pplcYearly3;
	}

	public String getPplcYearly4() {
		return (String) saveArray[ tableFldConstants.yearly4.ordinal() ];
	}

	public void setPplcYearly4(String pplcYearly4) {
		saveArray[ tableFldConstants.yearly4.ordinal() ] = pplcYearly4;
	}

	public String getPplcYearly5() {
		return (String) saveArray[ tableFldConstants.yearly5.ordinal() ];
	}

	public void setPplcYearly5(String pplcYearly5) {
		saveArray[ tableFldConstants.yearly5.ordinal() ] = pplcYearly5;
	}

	public String getPplcYearly6() {
		return (String) saveArray[ tableFldConstants.yearly6.ordinal() ];
	}

	public void setPplcYearly6(String pplcYearly6) {
		saveArray[ tableFldConstants.yearly6.ordinal() ] = pplcYearly6;
	}

	public String getPplcYearly7() {
		return (String) saveArray[ tableFldConstants.yearly7.ordinal() ];
	}

	public void setPplcYearly7(String pplcYearly7) {
		saveArray[ tableFldConstants.yearly7.ordinal() ] = pplcYearly7;
	}

	public String getPplcYearly8() {
		return (String) saveArray[ tableFldConstants.yearly8.ordinal() ];
	}

	public void setPplcYearly8(String pplcYearly8) {
		saveArray[ tableFldConstants.yearly8.ordinal() ] = pplcYearly8;
	}

	public String getPplcYearly9() {
		return (String) saveArray[ tableFldConstants.yearly9.ordinal() ];
	}

	public void setPplcYearly9(String pplcYearly9) {
		saveArray[ tableFldConstants.yearly9.ordinal() ] = pplcYearly9;
	}

	public String getPplcYearly10() {
		return (String) saveArray[ tableFldConstants.yearly10.ordinal() ];
	}

	public void setPplcYearly10(String pplcYearly10) {
		saveArray[ tableFldConstants.yearly10.ordinal() ] = pplcYearly10;
	}

	public String getPplcFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setPplcFrequency(String pplcFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = pplcFrequency;
	}

	public String getPplcAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setPplcAssemblyid(String pplcAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = pplcAssemblyid;
	}

	public String getPplcTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPplcTempfield1(String pplcTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pplcTempfield1;
	}

	public String getPplcTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPplcTempfield2(String pplcTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pplcTempfield2;
	}

	public String getPplcTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPplcTempfield3(String pplcTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = pplcTempfield3;
	}

	public String getPplcTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPplcTempfield4(String pplcTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = pplcTempfield4;
	}

	public String getPplcActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPplcActive(String pplcActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pplcActive;
	}

	public String getPplcCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPplcCreatedby(String pplcCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pplcCreatedby;
	}

	public String getPplcCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPplcCreatedon(String pplcCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pplcCreatedon;
	}

	public String getPplcModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPplcModifiedon(String pplcModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pplcModifiedon;
	}

	public void setWoRespDetail(List <BAL_PlmTlWorespdtl> woRespDetail) {
		this.woRespDetail = woRespDetail;
	}

	public List <BAL_PlmTlWorespdtl> getWoRespDetail() {
		return woRespDetail;
	}

	public void setWoRespMast(List <BAL_PlmTlWorespmst> woRespMast) {
		this.woRespMast = woRespMast;
	}

	public List <BAL_PlmTlWorespmst> getWoRespMast() {
		return woRespMast;
	}
	
	
	public String toJsonManual() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    appendField(sb, "pplcKeyid", getPplcKeyid(), true);
	    appendField(sb, "pplcFactoryid", getPplcFactoryid(), false);
	    appendField(sb, "pplcSectionid", getPplcSectionid(), false);
	    appendField(sb, "pplcCellid", getPplcCellid(), false);
	    appendField(sb, "pplcMachineid", getPplcMachineid(), false);
	    appendField(sb, "pplcLevel", getPplcLevel(), false);
	    appendField(sb, "pplcWeekno", getPplcWeekno(), false);
	    appendField(sb, "pplcMonthly", getPplcMonthly(), false);
	    appendField(sb, "pplcQuarterly", getPplcQuarterly(), false);
	    appendField(sb, "pplcHalfyearly", getPplcHalfyearly(), false);
	    appendField(sb, "pplcYearly", getPplcYearly(), false);
	    appendField(sb, "pplcYearly2", getPplcYearly2(), false);
	    appendField(sb, "pplcYearly3", getPplcYearly3(), false);
	    appendField(sb, "pplcYearly4", getPplcYearly4(), false);
	    appendField(sb, "pplcYearly5", getPplcYearly5(), false);
	    appendField(sb, "pplcYearly6", getPplcYearly6(), false);
	    appendField(sb, "pplcYearly7", getPplcYearly7(), false);
	    appendField(sb, "pplcYearly8", getPplcYearly8(), false);
	    appendField(sb, "pplcYearly9", getPplcYearly9(), false);
	    appendField(sb, "pplcYearly10", getPplcYearly10(), false);
	    appendField(sb, "pplcFrequency", getPplcFrequency(), false);
	    appendField(sb, "pplcAssemblyid", getPplcAssemblyid(), false);
	    appendField(sb, "pplcTempfield1", getPplcTempfield1(), false);
	    appendField(sb, "pplcTempfield2", getPplcTempfield2(), false);
	    appendField(sb, "pplcTempfield3", getPplcTempfield3(), false);
	    appendField(sb, "pplcTempfield4", getPplcTempfield4(), false);
	    appendField(sb, "pplcFlid", getPplcFlid(), false);
	    appendField(sb, "pplcElementid", getPplcElementid(), false);
	    appendField(sb, "pplcActive", getPplcActive(), false);
	    appendField(sb, "pplcCreatedby", getPplcCreatedby(), false);
	    appendField(sb, "pplcCreatedon", getPplcCreatedon(), false);
	    appendField(sb, "pplcModifiedon", getPplcModifiedon(), false);
	    sb.append("}");
	    return sb.toString();
	}

	private void appendField(StringBuilder sb, String key, String value, boolean first) {
	    if (!first) sb.append(",");
	    sb.append("\"").append(key).append("\":");
	    if (value == null) {
	        sb.append("null");
	    } else {
	        sb.append("\"").append(escapeJson(value)).append("\"");
	    }
	}

	private String escapeJson(String value) {
	    return value.replace("\\", "\\\\")
	                .replace("\"", "\\\"")
	                .replace("\n", "\\n")
	                .replace("\r", "\\r")
	                .replace("\t", "\\t");
	}

	public static BAL_PlmTlPlanconfiguration fromJson(String json) {
	    JSONObject obj = JSONObject.fromObject(json);
	    BAL_PlmTlPlanconfiguration result = new BAL_PlmTlPlanconfiguration();
	    result.setPplcKeyid(obj.optString("keyid"));
	    result.setPplcFactoryid(obj.optString("factoryid"));
	    result.setPplcSectionid(obj.optString("sectionid"));
	    result.setPplcCellid(obj.optString("cellid"));
	    result.setPplcMachineid(obj.optString("machineid"));
	    result.setPplcLevel(obj.optString("level"));
	    result.setPplcWeekno(obj.optString("weekno"));
	    result.setPplcMonthly(obj.optString("monthly"));
	    result.setPplcQuarterly(obj.optString("quarterly"));
	    result.setPplcHalfyearly(obj.optString("halfyearly"));
	    result.setPplcYearly(obj.optString("yearly"));
	    result.setPplcYearly2(obj.optString("yearly2"));
	    result.setPplcYearly3(obj.optString("yearly3"));
	    result.setPplcYearly4(obj.optString("yearly4"));
	    result.setPplcYearly5(obj.optString("yearly5"));
	    result.setPplcYearly6(obj.optString("yearly6"));
	    result.setPplcYearly7(obj.optString("yearly7"));
	    result.setPplcYearly8(obj.optString("yearly8"));
	    result.setPplcYearly9(obj.optString("yearly9"));
	    result.setPplcYearly10(obj.optString("yearly10"));
	    result.setPplcFrequency(obj.optString("frequency"));
	    result.setPplcAssemblyid(obj.optString("assemblyid"));
	    result.setPplcTempfield1(obj.optString("tempfield1"));
	    result.setPplcTempfield2(obj.optString("tempfield2"));
	    result.setPplcTempfield3(obj.optString("tempfield3"));
	    result.setPplcTempfield4(obj.optString("tempfield4"));
	    result.setPplcFlid(obj.optString("flid"));
	    result.setPplcElementid(obj.optString("elementid"));
	    result.setPplcActive(obj.optString("active"));
	    result.setPplcCreatedby(obj.optString("createdby"));
	    result.setPplcCreatedon(obj.optString("createdon"));
	    result.setPplcModifiedon(obj.optString("modifiedon"));
	    return result;
	}

	

}

