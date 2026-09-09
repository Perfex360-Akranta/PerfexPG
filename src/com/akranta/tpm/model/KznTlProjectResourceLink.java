package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KznTlProjectResourceLink {

	private  Object [] saveArray = null;  
	private String isDelete="";
	private List<KznTlProjectResourceLink> resourceLinks;
	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, lead_memb, empm_keyid, role_keyid,hrsestimate, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, createdby, active
		, createdon, modifiedon
	}

	public KznTlProjectResourceLink()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getKprlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKprlKeyid(String kprlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kprlKeyid;
	}
	public String getKprlHrsestimate() {
		return (String) saveArray[ tableFldConstants.hrsestimate.ordinal() ];
	}

	public void setKprlHrsestimate(String Hrsestimate) {
		saveArray[ tableFldConstants.hrsestimate.ordinal() ] = Hrsestimate;
	}


	public String getKprlKzpmKeyid() {
		return (String) saveArray[ tableFldConstants.kzpm_keyid.ordinal() ];
	}

	public void setKprlKzpmKeyid(String kprlKzpmKeyid) {
		saveArray[ tableFldConstants.kzpm_keyid.ordinal() ] = kprlKzpmKeyid;
	}

	public String getKprlLeadMemb() {
		return (String) saveArray[ tableFldConstants.lead_memb.ordinal() ];
	}

	public void setKprlLeadMemb(String kprlLeadMemb) {
		saveArray[ tableFldConstants.lead_memb.ordinal() ] = kprlLeadMemb;
	}

	public String getKprlEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setKprlEmpmKeyid(String kprlEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = kprlEmpmKeyid;
	}

	public String getKprlRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setKprlRoleKeyid(String kprlRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = kprlRoleKeyid;
	}

	public String getKprlTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKprlTempfield1(String kprlTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kprlTempfield1;
	}

	public String getKprlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKprlTempfield2(String kprlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kprlTempfield2;
	}

	public String getKprlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKprlTempfield3(String kprlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kprlTempfield3;
	}

	public String getKprlTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKprlTempfield4(String kprlTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kprlTempfield4;
	}

	public String getKprlTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKprlTempfield5(String kprlTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kprlTempfield5;
	}

	public String getKprlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKprlCreatedby(String kprlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kprlCreatedby;
	}

	public String getKprlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKprlActive(String kprlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kprlActive;
	}

	public String getKprlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKprlCreatedon(String kprlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kprlCreatedon;
	}

	public String getKprlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKprlModifiedon(String kprlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kprlModifiedon;
	}

	public void setResourceLinks(List<KznTlProjectResourceLink> resourceLinks) {
		this.resourceLinks = resourceLinks;
	}

	public List<KznTlProjectResourceLink> getResourceLinks() {
		return resourceLinks;
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
  
  public static String toJsonManualList(List<KznTlProjectResourceLink> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
  public static String toJsonManualListSave(List<KznTlProjectResourceLink> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	    	if (i > 0) sb.append(",");
	    	sb.append(" { ");
	    	sb.append("\"resourceLink\":");
	        sb.append(list.get(i).toJsonManual());
		    sb.append(",\"isDelete\":");
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

  

  
  public static KznTlProjectResourceLink fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlProjectResourceLink prl = new KznTlProjectResourceLink();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        prl.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return prl;
	}
  
  public static List<KznTlProjectResourceLink> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KznTlProjectResourceLink> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KznTlProjectResourceLink prl = new KznTlProjectResourceLink();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            prl.setValue(field, val);
	        }

	        list.add(prl);
	    }

	    return list;
	}

}

