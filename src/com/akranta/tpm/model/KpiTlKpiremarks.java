package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KpiTlActualKk.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KpiTlKpiremarks {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, indicatorid, flid, date, remarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public KpiTlKpiremarks()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getKprmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKprmKeyid(String kprmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kprmKeyid;
	}

	public String getKprmIndicatorid() {
		return (String) saveArray[ tableFldConstants.indicatorid.ordinal() ];
	}

	public void setKprmIndicatorid(String kprmIndicatorid) {
		saveArray[ tableFldConstants.indicatorid.ordinal() ] = kprmIndicatorid;
	}

	public String getKprmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setKprmFlid(String kprmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = kprmFlid;
	}

	public String getKprmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setKprmDate(String kprmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = kprmDate;
	}

	public String getKprmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setKprmRemarks(String kprmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = kprmRemarks;
	}

	public String getKprmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKprmTempfield1(String kprmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kprmTempfield1;
	}

	public String getKprmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKprmTempfield2(String kprmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kprmTempfield2;
	}

	public String getKprmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKprmTempfield3(String kprmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kprmTempfield3;
	}

	public String getKprmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKprmTempfield4(String kprmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kprmTempfield4;
	}

	public String getKprmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKprmTempfield5(String kprmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kprmTempfield5;
	}

	public String getKprmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKprmActive(String kprmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kprmActive;
	}

	public String getKprmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKprmCreatedby(String kprmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kprmCreatedby;
	}

	public String getKprmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKprmCreatedon(String kprmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kprmCreatedon;
	}

	public String getKprmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKprmModifiedon(String kprmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kprmModifiedon;
	}
	
	
	//getter setter
	
			public Object getValue(tableFldConstants field) {
		        return saveArray[field.ordinal()];
		    }

		    public void setValue(tableFldConstants field, Object value) {
		        saveArray[field.ordinal()] = value;
		    }
		    
		    
		 // to json
		    
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
		    
		    public static KpiTlKpiremarks fromJson(String json) {
		        CommonMessage.debugMsg("RAW JSON Response (Master): [" + json + "]");

		        JSONObject obj = JSONObject.fromObject(json);
		        KpiTlKpiremarks rmk = new KpiTlKpiremarks();

		        for (tableFldConstants field : tableFldConstants.values()) {
		            String key = field.name();
		            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.opt(key));
		            String val = obj.optString(field.name(), null);
		            rmk.setValue(field, val != null && val.equals("null") ? null : val);
		        }

		        return rmk;
		    }
	
	//from json list
	public static List<KpiTlKpiremarks> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KpiTlKpiremarks> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KpiTlKpiremarks rmk = new KpiTlKpiremarks();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            rmk.setValue(field, val);
	        }

	        list.add(rmk);
	    }

	    return list;
	}

	//to json list

	public static String toJsonManualList(List<KpiTlKpiremarks> list) {
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



