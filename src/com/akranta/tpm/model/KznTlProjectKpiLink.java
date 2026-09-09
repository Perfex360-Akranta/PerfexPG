package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlKkprojectprioritymst.tableFldConstants;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KznTlProjectKpiLink {

	private  Object [] saveArray = null;  
	
	private String isDelete="";

	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, kink_keyid, baseval, targetval, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, createdby, active
		, createdon, modifiedon
	}

	public KznTlProjectKpiLink()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete=isDelete;
	}

	public String getKpklKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKpklKeyid(String kpklKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kpklKeyid;
	}

	public String getKpklKzpmKeyid() {
		return (String) saveArray[ tableFldConstants.kzpm_keyid.ordinal() ];
	}

	public void setKpklKzpmKeyid(String kpklKzpmKeyid) {
		saveArray[ tableFldConstants.kzpm_keyid.ordinal() ] = kpklKzpmKeyid;
	}

	public String getKpklKinkKeyid() {
		return (String) saveArray[ tableFldConstants.kink_keyid.ordinal() ];
	}

	public void setKpklKinkKeyid(String kpklKinkKeyid) {
		saveArray[ tableFldConstants.kink_keyid.ordinal() ] = kpklKinkKeyid;
	}

	public String getKpklBaseval() {
		return (String) saveArray[ tableFldConstants.baseval.ordinal() ];
	}

	public void setKpklBaseval(String kpklBaseval) {
		saveArray[ tableFldConstants.baseval.ordinal() ] = kpklBaseval;
	}

	public String getKpklTargetval() {
		return (String) saveArray[ tableFldConstants.targetval.ordinal() ];
	}

	public void setKpklTargetval(String kpklTargetval) {
		saveArray[ tableFldConstants.targetval.ordinal() ] = kpklTargetval;
	}

	public String getKpklTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKpklTempfield1(String kpklTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kpklTempfield1;
	}

	public String getKpklTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKpklTempfield2(String kpklTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kpklTempfield2;
	}

	public String getKpklTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKpklTempfield3(String kpklTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kpklTempfield3;
	}

	public String getKpklTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKpklTempfield4(String kpklTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kpklTempfield4;
	}

	public String getKpklTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKpklTempfield5(String kpklTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kpklTempfield5;
	}

	public String getKpklCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKpklCreatedby(String kpklCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kpklCreatedby;
	}

	public String getKpklActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKpklActive(String kpklActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kpklActive;
	}

	public String getKpklCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKpklCreatedon(String kpklCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kpklCreatedon;
	}

	public String getKpklModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKpklModifiedon(String kpklModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kpklModifiedon;
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
  
  public static String toJsonManualList(List<KznTlProjectKpiLink> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	    	if (i > 0) sb.append(",");
	    	sb.append(" { ");
	    	sb.append("\"kpiLink\":");
	        sb.append(list.get(i).toJsonManual());
		    sb.append(",\"IsDelete\":");
		    if(CommonFunctions.isValidKeyId(list.get(i).getIsDelete())) {
		    	sb.append("\"").append(list.get(i).getIsDelete()).append("\"");
		    }else {
		    	sb.append("\"").append("N").append("\"");
		    }
		    sb.append(" } ");
		    
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
  
  
  public static KznTlProjectKpiLink fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlProjectKpiLink pkl = new KznTlProjectKpiLink();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        pkl.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return pkl;
	}
  
  public static List<KznTlProjectKpiLink> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KznTlProjectKpiLink> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KznTlProjectKpiLink pkl = new KznTlProjectKpiLink();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            pkl.setValue(field, val);
	        }

	        list.add(pkl);
	    }

	    return list;
	}

}

