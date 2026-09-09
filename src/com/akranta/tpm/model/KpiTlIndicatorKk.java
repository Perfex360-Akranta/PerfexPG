package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KpiTlIndicatorKk {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, indicatorname, indicatorcode, description, parentid, levelno
		, sortno, ischild, inputtype, inputentry, identifier, manualcalctype
		, uomid, frequency, excelname, dept_keyid, costarea, targetneed
		, pillarid, tempfield3, tempfield4, tempfield5, tempfield6
		, tempfield7, tempfield8, tempfield9, tempfield10,location, active, createdby
		, createdon, modifiedon
	}

	public KpiTlIndicatorKk()
	{
		saveArray = new  Object [ 32 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	public String getKinkKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKinkKeyid(String kinkKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kinkKeyid;
	}

	public String getKinkIndicatorname() {
		return (String) saveArray[ tableFldConstants.indicatorname.ordinal() ];
	}

	public void setKinkIndicatorname(String kinkIndicatorname) {
		saveArray[ tableFldConstants.indicatorname.ordinal() ] = kinkIndicatorname;
	}

	public String getKinkIndicatorcode() {
		return (String) saveArray[ tableFldConstants.indicatorcode.ordinal() ];
	}

	public void setKinkIndicatorcode(String kinkIndicatorcode) {
		saveArray[ tableFldConstants.indicatorcode.ordinal() ] = kinkIndicatorcode;
	}

	public String getKinkDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setKinkDescription(String kinkDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = kinkDescription;
	}

	public String getKinkParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setKinkParentid(String kinkParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = kinkParentid;
	}

	public String getKinkLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setKinkLevelno(String kinkLevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = kinkLevelno;
	}

	public String getKinkSortno() {
		return (String) saveArray[ tableFldConstants.sortno.ordinal() ];
	}

	public void setKinkSortno(String kinkSortno) {
		saveArray[ tableFldConstants.sortno.ordinal() ] = kinkSortno;
	}

	public String getKinkIschild() {
		return (String) saveArray[ tableFldConstants.ischild.ordinal() ];
	}

	public void setKinkIschild(String kinkIschild) {
		saveArray[ tableFldConstants.ischild.ordinal() ] = kinkIschild;
	}

	public String getKinkInputtype() {
		return (String) saveArray[ tableFldConstants.inputtype.ordinal() ];
	}

	public void setKinkInputtype(String kinkInputtype) {
		saveArray[ tableFldConstants.inputtype.ordinal() ] = kinkInputtype;
	}

	public String getKinkInputentry() {
		return (String) saveArray[ tableFldConstants.inputentry.ordinal() ];
	}

	public void setKinkInputentry(String kinkInputentry) {
		saveArray[ tableFldConstants.inputentry.ordinal() ] = kinkInputentry;
	}

	public String getKinkIdentifier() {
		return (String) saveArray[ tableFldConstants.identifier.ordinal() ];
	}

	public void setKinkIdentifier(String kinkIdentifier) {
		saveArray[ tableFldConstants.identifier.ordinal() ] = kinkIdentifier;
	}

	public String getKinkManualcalctype() {
		return (String) saveArray[ tableFldConstants.manualcalctype.ordinal() ];
	}

	public void setKinkManualcalctype(String kinkManualcalctype) {
		saveArray[ tableFldConstants.manualcalctype.ordinal() ] = kinkManualcalctype;
	}

	public String getKinkUomid() {
		return (String) saveArray[ tableFldConstants.uomid.ordinal() ];
	}

	public void setKinkUomid(String kinkUomid) {
		saveArray[ tableFldConstants.uomid.ordinal() ] = kinkUomid;
	}

	public String getKinkFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setKinkFrequency(String kinkFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = kinkFrequency;
	}

	public String getKinkExcelname() {
		return (String) saveArray[ tableFldConstants.excelname.ordinal() ];
	}

	public void setKinkExcelname(String kinkExcelname) {
		saveArray[ tableFldConstants.excelname.ordinal() ] = kinkExcelname;
	}

	public String getKinkDeptKeyid() {
		return (String) saveArray[ tableFldConstants.dept_keyid.ordinal() ];
	}

	public void setKinkDeptKeyid(String kinkDeptKeyid) {
		saveArray[ tableFldConstants.dept_keyid.ordinal() ] = kinkDeptKeyid;
	}

	public String getKinkCostarea() {
		return (String) saveArray[ tableFldConstants.costarea.ordinal() ];
	}

	public void setKinkCostarea(String kinkCostarea) {
		saveArray[ tableFldConstants.costarea.ordinal() ] = kinkCostarea;
	}

	public String getKinkTargetneed() {
		return (String) saveArray[ tableFldConstants.targetneed.ordinal() ];
	}

	public void setKinkTargetneed(String kinkTargetneed) {
		saveArray[ tableFldConstants.targetneed.ordinal() ] = kinkTargetneed;
	}

	public String getKinkPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setKinkPillarid(String kinkPillarid) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = kinkPillarid;
	}

	public String getKinkTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKinkTempfield3(String kinkTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kinkTempfield3;
	}

	public String getKinkTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKinkTempfield4(String kinkTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kinkTempfield4;
	}

	public String getKinkTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKinkTempfield5(String kinkTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kinkTempfield5;
	}

	public String getKinkTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setKinkTempfield6(String kinkTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = kinkTempfield6;
	}

	public String getKinkTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setKinkTempfield7(String kinkTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = kinkTempfield7;
	}

	public String getKinkTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setKinkTempfield8(String kinkTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = kinkTempfield8;
	}

	public String getKinkTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setKinkTempfield9(String kinkTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = kinkTempfield9;
	}

	public String getKinkTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setKinkTempfield10(String kinkTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = kinkTempfield10;
	}
	
	public String getKinkLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setKinkLocation(String kinkLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = kinkLocation;
	}
	
	public String getKinkActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKinkActive(String kinkActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kinkActive;
	}

	public String getKinkCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKinkCreatedby(String kinkCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kinkCreatedby;
	}

	public String getKinkCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKinkCreatedon(String kinkCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kinkCreatedon;
	}

	public String getKinkModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKinkModifiedon(String kinkModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kinkModifiedon;
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
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
	            if(field.name() == "keyid" && val == null) {
	            	sb.append("null");
	            }else if (val == null) {
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
	
	public static List<KpiTlIndicatorKk> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KpiTlIndicatorKk> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KpiTlIndicatorKk kpi = new KpiTlIndicatorKk();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            kpi.setValue(field, val);
	        }

	        list.add(kpi);
	    }

	    return list;
	}
	
	
	 public static KpiTlIndicatorKk fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  KpiTlIndicatorKk kpi = new KpiTlIndicatorKk();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        kpi.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return kpi;
		}
	 
	 
	 
	 public static String toJsonManualList(List<KpiTlIndicatorKk> list) {
		    StringBuilder sb = new StringBuilder();
		    sb.append("[");

		    for (int i = 0; i < list.size(); i++) {
		        if (i > 0) sb.append(",");
		        sb.append(list.get(i).toJsonManual());
		    }

		    sb.append("]");
		    return sb.toString();
		}
	

}

