package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlProjectKpiLink.tableFldConstants;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KznTlProjectKaizenLink {

	private  Object [] saveArray = null;  
	private String isDelete="";
	private List<KznTlProjectKaizenLink> projectKaizen;
	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, kznm_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, active, createdon, modifiedon
	}

	public KznTlProjectKaizenLink()
	{
		saveArray = new  Object [ 12 ];
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

	public String getKplkKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKplkKeyid(String kplkKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kplkKeyid;
	}

	public String getKplkKzpmKeyid() {
		return (String) saveArray[ tableFldConstants.kzpm_keyid.ordinal() ];
	}

	public void setKplkKzpmKeyid(String kplkKzpmKeyid) {
		saveArray[ tableFldConstants.kzpm_keyid.ordinal() ] = kplkKzpmKeyid;
	}

	public String getKplkKznmKeyid() {
		return (String) saveArray[ tableFldConstants.kznm_keyid.ordinal() ];
	}

	public void setKplkKznmKeyid(String kplkKznmKeyid) {
		saveArray[ tableFldConstants.kznm_keyid.ordinal() ] = kplkKznmKeyid;
	}

	public String getKplkTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKplkTempfield1(String kplkTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kplkTempfield1;
	}

	public String getKplkTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKplkTempfield2(String kplkTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kplkTempfield2;
	}

	public String getKplkTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKplkTempfield3(String kplkTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kplkTempfield3;
	}

	public String getKplkTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKplkTempfield4(String kplkTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kplkTempfield4;
	}

	public String getKplkTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKplkTempfield5(String kplkTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kplkTempfield5;
	}

	public String getKplkCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKplkCreatedby(String kplkCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kplkCreatedby;
	}

	public String getKplkActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKplkActive(String kplkActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kplkActive;
	}

	public String getKplkCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKplkCreatedon(String kplkCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kplkCreatedon;
	}

	public String getKplkModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKplkModifiedon(String kplkModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kplkModifiedon;
	}

	public void setProjectKaizen(List<KznTlProjectKaizenLink> projectKaizen) {
		this.projectKaizen = projectKaizen;
	}

	public List<KznTlProjectKaizenLink> getProjectKaizen() {
		return projectKaizen;
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
	
	public static String toJsonManualList(KznTlProjectKaizenLink kazienLink ) {
		
		List<KznTlProjectKaizenLink> kazienLinkList = kazienLink.getProjectKaizen();
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    String dateTime = CommonFunctions.pg_dateTimeNow();
	    for (int i = 0; i < kazienLinkList.size(); i++) {
	    	if(kazienLinkList.get(i).getKplkActive()==null){
	    		kazienLinkList.get(i).setKplkActive("Y");
			}
			if(kazienLinkList.get(i).getKplkCreatedon()==null){
				kazienLinkList.get(i).setKplkCreatedon(dateTime);
			}
			if(kazienLinkList.get(i).getKplkModifiedon()==null){
				kazienLinkList.get(i).setKplkModifiedon(dateTime);
			}
			if(kazienLinkList.get(i).getKplkTempfield1()==null){
				kazienLinkList.get(i).setKplkTempfield1("-");
			}
			if(kazienLinkList.get(i).getKplkTempfield2()==null){
				kazienLinkList.get(i).setKplkTempfield2("-");
			}
			if(kazienLinkList.get(i).getKplkTempfield3()==null){
				kazienLinkList.get(i).setKplkTempfield3("-");
			}
			if(kazienLinkList.get(i).getKplkTempfield4()==null){
				kazienLinkList.get(i).setKplkTempfield4("-");
			}
			if(kazienLinkList.get(i).getKplkTempfield5()==null){
				kazienLinkList.get(i).setKplkTempfield5("-");
			}
			kazienLinkList.get(i).setKplkCreatedby(kazienLink.getKplkCreatedby());
	    	
	    	if (i > 0) sb.append(",");
	    	sb.append(" { ");
	    	sb.append("\"kaizenLink\":");
	        sb.append(kazienLinkList.get(i).toJsonManual());
		    sb.append(",\"isDelete\":");
		    if(CommonFunctions.isValidKeyId(kazienLinkList.get(i).getIsDelete())) {
		    	sb.append("\"").append(kazienLinkList.get(i).getIsDelete()).append("\"");
		    }else {
		    	sb.append("\"").append("N").append("\"");
		    }
		    sb.append(" } ");
		    
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
	/*
	 * public static String toJsonManualList(List<KznTlProjectKaizenLink> list) {
	 * StringBuilder sb = new StringBuilder(); sb.append("[");
	 * 
	 * for (int i = 0; i < list.size(); i++) {
	 * 
	 * 
	 * if (i > 0) sb.append(","); sb.append(" { "); sb.append("\"kaizenLink\":");
	 * sb.append(list.get(i).toJsonManual()); sb.append(",\"isDelete\":");
	 * if(CommonFunctions.isValidKeyId(list.get(i).getIsDelete())) {
	 * sb.append("\"").append(list.get(i).getIsDelete()).append("\""); }else {
	 * sb.append("\"").append("N").append("\""); } sb.append(" } ");
	 * 
	 * }
	 * 
	 * sb.append("]"); return sb.toString(); }
	 */
  
  
  
  public static KznTlProjectKaizenLink fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlProjectKaizenLink pkl = new KznTlProjectKaizenLink();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        pkl.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return pkl;
	}
  
  public static List<KznTlProjectKaizenLink> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KznTlProjectKaizenLink> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KznTlProjectKaizenLink pkl = new KznTlProjectKaizenLink();

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

