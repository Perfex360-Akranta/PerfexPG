package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlProjectResourceLink.tableFldConstants;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KznTlProjectChecklistLink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, projectid, checklistid, include, verifiedby, verifiedstatus
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public KznTlProjectChecklistLink()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPcllKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPcllKeyid(String pcllKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pcllKeyid;
	}

	public String getPcllProjectid() {
		return (String) saveArray[ tableFldConstants.projectid.ordinal() ];
	}

	public void setPcllProjectid(String pcllProjectid) {
		saveArray[ tableFldConstants.projectid.ordinal() ] = pcllProjectid;
	}

	public String getPcllChecklistid() {
		return (String) saveArray[ tableFldConstants.checklistid.ordinal() ];
	}

	public void setPcllChecklistid(String pcllChecklistid) {
		saveArray[ tableFldConstants.checklistid.ordinal() ] = pcllChecklistid;
	}

	public String getPcllInclude() {
		return (String) saveArray[ tableFldConstants.include.ordinal() ];
	}

	public void setPcllInclude(String pcllInclude) {
		saveArray[ tableFldConstants.include.ordinal() ] = pcllInclude;
	}

	public String getPcllVerifiedby() {
		return (String) saveArray[ tableFldConstants.verifiedby.ordinal() ];
	}

	public void setPcllVerifiedby(String pcllVerifiedby) {
		saveArray[ tableFldConstants.verifiedby.ordinal() ] = pcllVerifiedby;
	}

	public String getPcllVerifiedstatus() {
		return (String) saveArray[ tableFldConstants.verifiedstatus.ordinal() ];
	}

	public void setPcllVerifiedstatus(String pcllVerifiedstatus) {
		saveArray[ tableFldConstants.verifiedstatus.ordinal() ] = pcllVerifiedstatus;
	}

	public String getPcllTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPcllTempfield1(String pcllTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pcllTempfield1;
	}

	public String getPcllTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPcllTempfield2(String pcllTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pcllTempfield2;
	}

	public String getPcllTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPcllTempfield3(String pcllTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = pcllTempfield3;
	}

	public String getPcllTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPcllTempfield4(String pcllTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = pcllTempfield4;
	}

	public String getPcllTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPcllTempfield5(String pcllTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = pcllTempfield5;
	}

	public String getPcllActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPcllActive(String pcllActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pcllActive;
	}

	public String getPcllCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPcllCreatedby(String pcllCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pcllCreatedby;
	}

	public String getPcllCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPcllCreatedon(String pcllCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pcllCreatedon;
	}

	public String getPcllModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPcllModifiedon(String pcllModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pcllModifiedon;
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
  
  public static String toJsonManualList(List<KznTlProjectChecklistLink> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
  


  

  
  public static KznTlProjectChecklistLink fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlProjectChecklistLink pcl = new KznTlProjectChecklistLink();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        pcl.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return pcl;
	}
  
  public static List<KznTlProjectChecklistLink> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KznTlProjectChecklistLink> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KznTlProjectChecklistLink pcl = new KznTlProjectChecklistLink();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            pcl.setValue(field, val);
	        }

	        list.add(pcl);
	    }

	    return list;
	}

}

