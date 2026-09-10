package com.akranta.tpm.model;
/**
 * Author:N Arun
 * Created on:25.11.2011
 */
import java.util.ArrayList;

import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;
import com.akranta.tpm.model.GenTlMomattendance.tableFldConstants;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenTlAssemblymst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, code, name, description, remarks, type,relatedto, active, createdby
		, createdon, modifiedon
	}

	public GenTlAssemblymst()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	// added by priyanka on 16/07/2026
	public void setValue(tableFldConstants field, Object val) {
	    saveArray[field.ordinal()] = val;
	}

	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}
	//end 

	public String getAssmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAssmKeyid(String assmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = assmKeyid;
	}

	public String getAssmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setAssmCode(String assmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = assmCode;
	}
	
	public String getAssmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setAssmName(String assmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = assmName;
	}

	public String getAssmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setAssmDescription(String assmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = assmDescription;
	}

	public String getAssmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setAssmRemarks(String assmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = assmRemarks;
	}

	public String getAssmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setAssmType(String assmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = assmType;
	}

	
	public String getAssmRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setAssmRelatedto(String assmRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = assmRelatedto;
	}	
	
	public String getAssmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAssmActive(String assmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = assmActive;
	}

	public String getAssmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAssmCreatedby(String assmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = assmCreatedby;
	}

	public String getAssmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAssmCreatedon(String assmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = assmCreatedon;
	}

	public String getAssmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAssmModifiedon(String assmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = assmModifiedon;
	}
	
	
	
	// added by priyanka on 16/07/2026
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
	            //if(field.name() == "keyid" && val == null) 
	            if ("keyid".equals(field.name()) && val == null){
	            	sb.append("null");
	            }else if (val == null|| "{}".equals(val.toString())
	                    || "null".equalsIgnoreCase(val.toString())
	                    || "[object Object]".equalsIgnoreCase(val.toString())) {
	                //sb.append("\"{}\"");
	            	 sb.append("\"\"");
	            } else {
	                //sb.append("\"").append(val.toString()).append("\"");
	            	String value = val.toString()
	                        .replace("\\", "\\\\")
	                        .replace("\"", "\\\"")
	                        .replace("\r", "\\r")
	                        .replace("\n", "\\n");

	                sb.append("\"")
	                  .append(value)
	                  .append("\"");
	            }
	            first = false;
	        }
	    }

	    sb.append("}");
	    return sb.toString();
	}
  
  public static GenTlAssemblymst fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  GenTlAssemblymst assm = new GenTlAssemblymst();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	//String key = field.name();
	    	String val = obj.optString(field.name(), null);
	    	
	    	//CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        //String val = obj.optString(field.name(), null);
	        //assm.setValue(field, val != null && val.equals("null") ? null : val);
	    	 if (val == null
	                 || "null".equalsIgnoreCase(val)
	                 || "{}".equals(val)
	                 || "[object Object]".equalsIgnoreCase(val)) {

	             val = "";
	         }

	         assm.setValue(field, val);
	    	
	    }
	    return assm;
	}
  
  public static List<GenTlAssemblymst> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<GenTlAssemblymst> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			GenTlAssemblymst assm = new GenTlAssemblymst();

			for (tableFldConstants field : tableFldConstants.values()) {
				//String key = field.name();

				//Object valueObj = obj.opt(key);
				//String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
				
				String val =obj.optString(field.name(), null);
				
				if (val == null|| "null".equalsIgnoreCase(val)|| "{}".equals(val)
						|| "[object Object]".equalsIgnoreCase(val)) {
					  val = "";
	            }
				

				assm.setValue(field, val);
			}

			list.add(assm);
		}

		return list;
	}
	
	public static String toJsonManualList(List<GenTlAssemblymst> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		//for (int i = 0; i < list.size(); i++) {
			//if (i > 0)
				//sb.append(",");
			//sb.append(list.get(i).toJsonManual());
		//}
		
		if (list != null) {

	        for (int i = 0; i < list.size(); i++) {
	        	if (i > 0) {
	                sb.append(",");
	            }
	            GenTlAssemblymst assm = list.get(i);

	            if (assm != null) {
	                sb.append(assm.toJsonManual());
	            } else {
	                sb.append("null");
	            }
	        }
	    }

		sb.append("]");
		return sb.toString();
	}
	
	
 }

