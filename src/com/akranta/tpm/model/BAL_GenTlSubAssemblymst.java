package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_GenTlSubAssemblymst {
    private Object[] saveArray = null;

    public enum tableFldConstants {
        keyid, assemblyid, code, name, description, remarks, active, createdby, createdon, modifiedon
    }

    public BAL_GenTlSubAssemblymst() {
        saveArray = new Object[10];
    }

    public Object[] getSaveArray() { return saveArray; }
    public void setSaveArray(Object[] saveArray) { this.saveArray = saveArray; }
    
    public void setValue(tableFldConstants field, Object val) {
        saveArray[field.ordinal()] = val;
    }

    public Object getValue(tableFldConstants field) {
        return saveArray[field.ordinal()];
    }

    public String getSbamKeyid() { return (String) saveArray[tableFldConstants.keyid.ordinal()]; }
    public void setSbamKeyid(String v) { saveArray[tableFldConstants.keyid.ordinal()] = v; }

    public String getSbamAssemblyid() { return (String) saveArray[tableFldConstants.assemblyid.ordinal()]; }
    public void setSbamAssemblyid(String v) { saveArray[tableFldConstants.assemblyid.ordinal()] = v; }

    public String getSbamCode() { return (String) saveArray[tableFldConstants.code.ordinal()]; }
    public void setSbamCode(String v) { saveArray[tableFldConstants.code.ordinal()] = v; }

    public String getSbamName() { return (String) saveArray[tableFldConstants.name.ordinal()]; }
    public void setSbamName(String v) { saveArray[tableFldConstants.name.ordinal()] = v; }

    public String getSbamDescription() { return (String) saveArray[tableFldConstants.description.ordinal()]; }
    public void setSbamDescription(String v) { saveArray[tableFldConstants.description.ordinal()] = v; }

    public String getSbamRemarks() { return (String) saveArray[tableFldConstants.remarks.ordinal()]; }
    public void setSbamRemarks(String v) { saveArray[tableFldConstants.remarks.ordinal()] = v; }

    public String getSbamActive() { return (String) saveArray[tableFldConstants.active.ordinal()]; }
    public void setSbamActive(String v) { saveArray[tableFldConstants.active.ordinal()] = v; }

    public String getSbamCreatedby() { return (String) saveArray[tableFldConstants.createdby.ordinal()]; }
    public void setSbamCreatedby(String v) { saveArray[tableFldConstants.createdby.ordinal()] = v; }

    public String getSbamCreatedon() { return (String) saveArray[tableFldConstants.createdon.ordinal()]; }
    public void setSbamCreatedon(String v) { saveArray[tableFldConstants.createdon.ordinal()] = v; }

    public String getSbamModifiedon() { return (String) saveArray[tableFldConstants.modifiedon.ordinal()]; }
    public void setSbamModifiedon(String v) { saveArray[tableFldConstants.modifiedon.ordinal()] = v; }
    
	
	  public String toJsonManual() {		
		  StringBuilder sb = new StringBuilder();
		  sb.append("{");
	  
	  boolean first = true; 
	  for (tableFldConstants field :tableFldConstants.values()) 
	  {
		  int index = field.ordinal(); if (index <
	  saveArray.length) { if (!first) sb.append(",");
	  sb.append("\"").append(field.name()).append("\":"); Object val =
	  saveArray[index]; 
	  if (field.name().equals("keyid") && val == null) 
	  {
		  sb.append("null"); } else if (val == null) { 
			  //sb.append("\"{}\"");
			  sb.append("\"\"");
		  }
		  else {
			  sb.append("\"").append(val.toString()).append("\""); 
			  }
	  first = false; 
	  }
		  }
	  
	  	sb.append("}"); return sb.toString(); 
	  }
	 
    
		/*
		 * public String toJsonManual() { StringBuilder sb = new StringBuilder();
		 * sb.append("{");
		 * 
		 * boolean first = true; for (tableFldConstants field :
		 * tableFldConstants.values()) { int index = field.ordinal(); if (index <
		 * saveArray.length) { if (!first) sb.append(",");
		 * sb.append("\"").append(field.name()).append("\":"); Object val =
		 * saveArray[index]; String fieldName = field.name(); if
		 * ((fieldName.equals("keyid") || fieldName.equals("code") ||
		 * fieldName.equals("createdon") || fieldName.equals("modifiedon")) && val ==
		 * null) { sb.append("null"); } else if (val == null) { sb.append("\"{}\""); }
		 * else { sb.append("\"").append(val.toString()).append("\""); } first = false;
		 * } }
		 * 
		 * sb.append("}"); return sb.toString(); }
		 */

    public static BAL_GenTlSubAssemblymst fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);

        BAL_GenTlSubAssemblymst sbam = new BAL_GenTlSubAssemblymst();
        CommonMessage.debugMsg("RAW JSON Response: :" + json);

        for (tableFldConstants field : tableFldConstants.values()) {
            //String key = field.name();
        	
        	String val = obj.optString(field.name(), null);

            if (val == null
                    || "null".equalsIgnoreCase(val)
                    || "{}".equals(val)
                    || "[object Object]".equalsIgnoreCase(val)) {

                val = "";
            }

            //CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
            //String val = obj.optString(field.name(), null);
            //sbam.setValue(field, val != null && val.equals("null") ? null : val);
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.opt(field.name()));
            sbam.setValue(field, val);
        }
        return sbam;
    }

    public static List<BAL_GenTlSubAssemblymst> fromJsonList(String json) {

        CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

        JSONArray jsonArray = JSONArray.fromObject(json);
        List<BAL_GenTlSubAssemblymst> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);
            BAL_GenTlSubAssemblymst sbam = new BAL_GenTlSubAssemblymst();

            for (tableFldConstants field : tableFldConstants.values()) {
                
            	// commented and added by priyanka 
            	//String key = field.name();
            	String val = obj.optString(field.name(), null);
            	
            	if (val == null|| "null".equalsIgnoreCase(val)|| "{}".equals(val)|| "[object Object]".equalsIgnoreCase(val)) {
            			val = "";
            	}

                //Object valueObj = obj.opt(key);
                //String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
            		
            	// end 
                sbam.setValue(field, val);
            }

            list.add(sbam);
        }

        return list;
    }

	/*
	 * public static String toJsonManualList(List<BAL_GenTlSubAssemblymst> list) {
	 * StringBuilder sb = new StringBuilder(); sb.append("[");
	 * 
	 * for (int i = 0; i < list.size(); i++) { if (i > 0) sb.append(",");
	 * sb.append(list.get(i).toJsonManual()); }
	 * 
	 * sb.append("]"); return sb.toString(); }
	 */
    
    public static String toJsonManualList(
            List<BAL_GenTlSubAssemblymst> list) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                if (i > 0) {
                    sb.append(",");
                }

                BAL_GenTlSubAssemblymst item = list.get(i);

                if (item != null) {
                    sb.append(item.toJsonManual());
                } else {
                    sb.append("null");
                }
            }
        }

        sb.append("]");

        return sb.toString();
    }
}