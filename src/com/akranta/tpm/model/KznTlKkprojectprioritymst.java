package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlProjectResourceLink.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KznTlKkprojectprioritymst {

	private  Object [] saveArray = null;  
	
	private List<KznTlKkprojectprioritymst> master;
	private List<KznTlKkprojectprioritydtl> detail;
	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, flid, approvedby, projectscore, tempfield1
		, tempfield2, tempfield3, tempfield4, rank, createdby, active
		, createdon, modifiedon
	}

	public KznTlKkprojectprioritymst()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKppmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKppmKeyid(String kppmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kppmKeyid;
	}

	public String getKppmKzpmKeyid() {
		return (String) saveArray[ tableFldConstants.kzpm_keyid.ordinal() ];
	}

	public void setKppmKzpmKeyid(String kppmKzpmKeyid) {
		saveArray[ tableFldConstants.kzpm_keyid.ordinal() ] = kppmKzpmKeyid;
	}

	public String getKppmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setKppmFlid(String kppmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = kppmFlid;
	}

	public String getKppmApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setKppmApprovedby(String kppmApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = kppmApprovedby;
	}

	public String getKppmProjectscore() {
		return (String) saveArray[ tableFldConstants.projectscore.ordinal() ];
	}

	public void setKppmProjectscore(String kppmProjectscore) {
		saveArray[ tableFldConstants.projectscore.ordinal() ] = kppmProjectscore;
	}

	public String getKppmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKppmTempfield1(String kppmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kppmTempfield1;
	}

	public String getKppmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKppmTempfield2(String kppmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kppmTempfield2;
	}

	public String getKppmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKppmTempfield3(String kppmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kppmTempfield3;
	}

	public String getKppmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKppmTempfield4(String kppmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kppmTempfield4;
	}

	public String getKppmRank() {
		return (String) saveArray[ tableFldConstants.rank.ordinal() ];
	}

	public void setKppmRank(String kppmRank) {
		saveArray[ tableFldConstants.rank.ordinal() ] = kppmRank;
	}

	public String getKppmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKppmCreatedby(String kppmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kppmCreatedby;
	}

	public String getKppmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKppmActive(String kppmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kppmActive;
	}

	public String getKppmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKppmCreatedon(String kppmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kppmCreatedon;
	}

	public String getKppmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKppmModifiedon(String kppmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kppmModifiedon;
	}

	public void setMaster(List<KznTlKkprojectprioritymst> master) {
		this.master = master;
	}

	public List<KznTlKkprojectprioritymst> getMaster() {
		return master;
	}

	public void setDetail(List<KznTlKkprojectprioritydtl> detail) {
		this.detail = detail;
	}

	public List<KznTlKkprojectprioritydtl> getDetail() {
		return detail;
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
  
  public static String toJsonManualList(List<KznTlKkprojectprioritymst> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
  
  
  public static KznTlKkprojectprioritymst fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlKkprojectprioritymst ppm = new KznTlKkprojectprioritymst();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        ppm.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return ppm;
	}
  
  public static List<KznTlKkprojectprioritymst> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KznTlKkprojectprioritymst> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KznTlKkprojectprioritymst ppm = new KznTlKkprojectprioritymst();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            ppm.setValue(field, val);
	        }

	        list.add(ppm);
	    }

	    return list;
	}

}

