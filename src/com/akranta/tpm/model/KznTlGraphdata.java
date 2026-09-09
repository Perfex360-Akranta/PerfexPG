package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlMst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KznTlGraphdata {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		kaizenid, datemonthyear, beforedata, afterdata, charttype, createdon
	}

	public KznTlGraphdata()
	{
		saveArray = new  Object [ 6 ];
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
	public String getKzgdKaizenid() {
		return (String) saveArray[ tableFldConstants.kaizenid.ordinal() ];
	}

	public void setKzgdKaizenid(String kzgdKaizenid) {
		saveArray[ tableFldConstants.kaizenid.ordinal() ] = kzgdKaizenid;
	}

	public String getKzgdDatemonthyear() {
		return (String) saveArray[ tableFldConstants.datemonthyear.ordinal() ];
	}

	public void setKzgdDatemonthyear(String kzgdDatemonthyear) {
		saveArray[ tableFldConstants.datemonthyear.ordinal() ] = kzgdDatemonthyear;
	}

	public String getKzgdBeforedata() {
		return (String) saveArray[ tableFldConstants.beforedata.ordinal() ];
	}

	public void setKzgdBeforedata(String kzgdBeforedata) {
		saveArray[ tableFldConstants.beforedata.ordinal() ] = kzgdBeforedata;
	}

	public String getKzgdAfterdata() {
		return (String) saveArray[ tableFldConstants.afterdata.ordinal() ];
	}

	public void setKzgdAfterdata(String kzgdAfterdata) {
		saveArray[ tableFldConstants.afterdata.ordinal() ] = kzgdAfterdata;
	}

	public String getKzgdCharttype() {
		return (String) saveArray[ tableFldConstants.charttype.ordinal() ];
	}

	public void setKzgdCharttype(String kzgdCharttype) {
		saveArray[ tableFldConstants.charttype.ordinal() ] = kzgdCharttype;
	}

	public String getKzgdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKzgdCreatedon(String kzgdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kzgdCreatedon;
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
  
  public static KznTlGraphdata fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlGraphdata mst = new KznTlGraphdata();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        mst.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return mst;
	}

}

