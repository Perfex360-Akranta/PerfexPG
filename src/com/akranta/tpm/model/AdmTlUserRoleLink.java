package com.akranta.tpm.model;

import com.akranta.tpm.model.AdmTlUsermst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class AdmTlUserRoleLink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		userid, roleid, active, createdby, createdon, modifiedon
	}

	public AdmTlUserRoleLink()
	{
		saveArray = new  Object [ 6 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
public void setSaveArray(Object [] saveArray) {
		
		this.saveArray = saveArray;
	}

	public String getArulUserid() {
		return (String) saveArray[ tableFldConstants.userid.ordinal() ];
	}

	public void setArulUserid(String arulUserid) {
		saveArray[ tableFldConstants.userid.ordinal() ] = arulUserid;
	}

	public String getArulRoleid() {
		return (String) saveArray[ tableFldConstants.roleid.ordinal() ];
	}

	public void setArulRoleid(String arulRoleid) {
		saveArray[ tableFldConstants.roleid.ordinal() ] = arulRoleid;
	}

	public String getArulActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setArulActive(String arulActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = arulActive;
	}

	public String getArulCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setArulCreatedby(String arulCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = arulCreatedby;
	}

	public String getArulCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setArulCreatedon(String arulCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = arulCreatedon;
	}

	public String getArulModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setArulModifiedon(String arulModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = arulModifiedon;
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
	
	public static AdmTlUserRoleLink fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  AdmTlUserRoleLink user = new AdmTlUserRoleLink();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        user.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return user;
		}

}

