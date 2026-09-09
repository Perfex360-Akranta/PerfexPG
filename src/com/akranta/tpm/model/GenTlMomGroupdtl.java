package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlMomGroupdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, mgrm_keyid, empm_keyid, tempfield1, tempfield2, active
		, createdby, createdon, modifiedon
	}

	public GenTlMomGroupdtl()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMgrdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMgrdKeyid(String mgrdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mgrdKeyid;
	}

	public String getMgrdMgrmKeyid() {
		return (String) saveArray[ tableFldConstants.mgrm_keyid.ordinal() ];
	}

	public void setMgrdMgrmKeyid(String mgrdMgrmKeyid) {
		saveArray[ tableFldConstants.mgrm_keyid.ordinal() ] = mgrdMgrmKeyid;
	}

	public String getMgrdEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setMgrdEmpmKeyid(String mgrdEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = mgrdEmpmKeyid;
	}

	public String getMgrdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMgrdTempfield1(String mgrdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mgrdTempfield1;
	}

	public String getMgrdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMgrdTempfield2(String mgrdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mgrdTempfield2;
	}

	public String getMgrdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMgrdActive(String mgrdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mgrdActive;
	}

	public String getMgrdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMgrdCreatedby(String mgrdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mgrdCreatedby;
	}

	public String getMgrdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMgrdCreatedon(String mgrdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mgrdCreatedon;
	}

	public String getMgrdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMgrdModifiedon(String mgrdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mgrdModifiedon;
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
	                if (!first)
	                    sb.append(",");
	                sb.append("\"").append(field.name()).append("\":");
	                Object val = saveArray[index];
	                if (field.name().equals("keyid") && val == null) {
	                    sb.append("null");
	                } else if (val == null) {
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

	    /**
	     * Parse JSON string to object
	     */
	    public static GenTlMomGroupdtl fromJson(String json) {
	        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	        JSONObject obj = JSONObject.fromObject(json);
	        GenTlMomGroupdtl dtl = new GenTlMomGroupdtl();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();
	            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	            String val = obj.optString(field.name(), null);
	            dtl.setValue(field, val != null && val.equals("null") ? null : val);
	        }
	        return dtl;
	    }

}

