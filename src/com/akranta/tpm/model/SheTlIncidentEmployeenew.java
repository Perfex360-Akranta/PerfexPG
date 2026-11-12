package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.SheTlIncidentmstnew.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SheTlIncidentEmployeenew {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, sinc_keyid, empm_keyid, injury_modeid, bodypart, remarks
		, injury_typeid, mode, type, location, tempfield5
	}

	public SheTlIncidentEmployeenew()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	public String getSineKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSineKeyid(String sineKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sineKeyid;
	}

	public String getSineSincKeyid() {
		return (String) saveArray[ tableFldConstants.sinc_keyid.ordinal() ];
	}

	public void setSineSincKeyid(String sineSincKeyid) {
		saveArray[ tableFldConstants.sinc_keyid.ordinal() ] = sineSincKeyid;
	}

	public String getSineEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setSineEmpmKeyid(String sineEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = sineEmpmKeyid;
	}

	public String getSineInjuryModeid() {
		return (String) saveArray[ tableFldConstants.injury_modeid.ordinal() ];
	}

	public void setSineInjuryModeid(String sineInjuryModeid) {
		saveArray[ tableFldConstants.injury_modeid.ordinal() ] = sineInjuryModeid;
	}

	public String getSineBodypart() {
		return (String) saveArray[ tableFldConstants.bodypart.ordinal() ];
	}

	public void setSineBodypart(String sineBodypart) {
		saveArray[ tableFldConstants.bodypart.ordinal() ] = sineBodypart;
	}

	public String getSineRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSineRemarks(String sineRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = sineRemarks;
	}

	public String getSineInjuryTypeid() {
		return (String) saveArray[ tableFldConstants.injury_typeid.ordinal() ];
	}

	public void setSineInjuryTypeid(String sineInjuryTypeid) {
		saveArray[ tableFldConstants.injury_typeid.ordinal() ] = sineInjuryTypeid;
	}

	public String getSineMode() {
		return (String) saveArray[ tableFldConstants.mode.ordinal() ];
	}

	public void setSineMode(String sineMode) {
		saveArray[ tableFldConstants.mode.ordinal() ] = sineMode;
	}

	public String getSineType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setSineType(String sineType) {
		saveArray[ tableFldConstants.type.ordinal() ] = sineType;
	}

	public String getSineLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setSineLocation(String sineLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = sineLocation;
	}

	public String getSineTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSineTempfield5(String sineTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = sineTempfield5;
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
	
	public static String toJsonManualList(List<SheTlIncidentEmployeenew> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
	
	public static SheTlIncidentEmployeenew fromJson(String json) {
		  System.out.println("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  SheTlIncidentEmployeenew she = new SheTlIncidentEmployeenew();
		  System.out.println("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	System.out.println("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        she.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return she;
		}
	
	 public static List<SheTlIncidentEmployeenew> fromJsonList(String json) {

		    System.out.println("RAW JSON ARRAY Response: " + json);

		    JSONArray jsonArray = JSONArray.fromObject(json);
		    List<SheTlIncidentEmployeenew> list = new ArrayList<>();

		    for (int i = 0; i < jsonArray.length(); i++) {

		        JSONObject obj = jsonArray.getJSONObject(i);
		        SheTlIncidentEmployeenew she = new SheTlIncidentEmployeenew();

		        for (tableFldConstants field : tableFldConstants.values()) {
		            String key = field.name();

		            Object valueObj = obj.opt(key);
		            String val = (valueObj == null || "null".equals(valueObj.toString()))
		                         ? null
		                         : valueObj.toString();

		            she.setValue(field, val);
		        }

		        list.add(she);
		    }

		    return list;
		}

}


