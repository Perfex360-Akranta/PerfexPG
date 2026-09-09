package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.QtmTlQpoint.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlQpointdtls {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, qptm_keyid, qpoint, nooflocations, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public QtmTlQpointdtls()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getQptdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQptdKeyid(String qptdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qptdKeyid;
	}

	public String getQptdQptmKeyid() {
		return (String) saveArray[ tableFldConstants.qptm_keyid.ordinal() ];
	}

	public void setQptdQptmKeyid(String qptdQptmKeyid) {
		saveArray[ tableFldConstants.qptm_keyid.ordinal() ] = qptdQptmKeyid;
	}

	public String getQptdQpoint() {
		return (String) saveArray[ tableFldConstants.qpoint.ordinal() ];
	}

	public void setQptdQpoint(String qptdQpoint) {
		saveArray[ tableFldConstants.qpoint.ordinal() ] = qptdQpoint;
	}

	public String getQptdNooflocations() {
		return (String) saveArray[ tableFldConstants.nooflocations.ordinal() ];
	}

	public void setQptdNooflocations(String qptdNooflocations) {
		saveArray[ tableFldConstants.nooflocations.ordinal() ] = qptdNooflocations;
	}

	public String getQptdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setQptdTempfield1(String qptdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = qptdTempfield1;
	}

	public String getQptdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setQptdTempfield2(String qptdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = qptdTempfield2;
	}

	public String getQptdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setQptdTempfield3(String qptdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = qptdTempfield3;
	}

	public String getQptdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setQptdTempfield4(String qptdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = qptdTempfield4;
	}

	public String getQptdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setQptdTempfield5(String qptdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = qptdTempfield5;
	}

	public String getQptdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQptdCreatedby(String qptdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qptdCreatedby;
	}

	public String getQptdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQptdActive(String qptdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qptdActive;
	}

	public String getQptdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQptdCreatedon(String qptdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qptdCreatedon;
	}

	public String getQptdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQptdModifiedon(String qptdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = qptdModifiedon;
	}

	
	public void setSaveArray(Object[] dataArr) {
		this.saveArray = dataArr ;
		
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
  
  public static QtmTlQpointdtls fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  QtmTlQpointdtls mst = new QtmTlQpointdtls();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        mst.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return mst;
	}
}

