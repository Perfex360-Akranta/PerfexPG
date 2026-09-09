package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlProjectResourceLink.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KznTlKkprojectprioritydtl {

	private  Object [] saveArray = null;  
	
	private String flag;
	public enum   tableFldConstants
	{
		keyid, kppm_keyid, kkpm_keyid, score, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public KznTlKkprojectprioritydtl()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKppdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKppdKeyid(String kppdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kppdKeyid;
	}

	public String getKppdKppmKeyid() {
		return (String) saveArray[ tableFldConstants.kppm_keyid.ordinal() ];
	}

	public void setKppdKppmKeyid(String kppdKppmKeyid) {
		saveArray[ tableFldConstants.kppm_keyid.ordinal() ] = kppdKppmKeyid;
	}

	public String getKppdKkpmKeyid() {
		return (String) saveArray[ tableFldConstants.kkpm_keyid.ordinal() ];
	}

	public void setKppdKkpmKeyid(String kppdKkpmKeyid) {
		saveArray[ tableFldConstants.kkpm_keyid.ordinal() ] = kppdKkpmKeyid;
	}

	public String getKppdScore() {
		return (String) saveArray[ tableFldConstants.score.ordinal() ];
	}

	public void setKppdScore(String kppdScore) {
		saveArray[ tableFldConstants.score.ordinal() ] = kppdScore;
	}

	public String getKppdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKppdTempfield1(String kppdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kppdTempfield1;
	}

	public String getKppdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKppdTempfield2(String kppdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kppdTempfield2;
	}

	public String getKppdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKppdTempfield3(String kppdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kppdTempfield3;
	}

	public String getKppdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKppdTempfield4(String kppdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kppdTempfield4;
	}

	public String getKppdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKppdTempfield5(String kppdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kppdTempfield5;
	}

	public String getKppdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKppdCreatedby(String kppdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kppdCreatedby;
	}

	public String getKppdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKppdActive(String kppdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kppdActive;
	}

	public String getKppdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKppdCreatedon(String kppdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kppdCreatedon;
	}

	public String getKppdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKppdModifiedon(String kppdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kppdModifiedon;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
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
  
  public static String toJsonManualList(List<KznTlKkprojectprioritydtl> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
  

  public static KznTlKkprojectprioritydtl fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlKkprojectprioritydtl ppd = new KznTlKkprojectprioritydtl();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        ppd.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return ppd;
	}
  
  public static List<KznTlKkprojectprioritydtl> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KznTlKkprojectprioritydtl> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KznTlKkprojectprioritydtl ppd = new KznTlKkprojectprioritydtl();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            ppd.setValue(field, val);
	        }

	        list.add(ppd);
	    }

	    return list;
	}

}

