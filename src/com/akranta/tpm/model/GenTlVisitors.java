package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMommst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlVisitors {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, moms_keyid, visitorname, purpose, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public GenTlVisitors()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getVisiKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVisiKeyid(String visiKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = visiKeyid;
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public String getVisiMomsKeyid() {
		return (String) saveArray[ tableFldConstants.moms_keyid.ordinal() ];
	}

	public void setVisiMomsKeyid(String visiMomsKeyid) {
		saveArray[ tableFldConstants.moms_keyid.ordinal() ] = visiMomsKeyid;
	}

	public String getVisiVisitorname() {
		return (String) saveArray[ tableFldConstants.visitorname.ordinal() ];
	}

	public void setVisiVisitorname(String visiVisitorname) {
		saveArray[ tableFldConstants.visitorname.ordinal() ] = visiVisitorname;
	}

	public String getVisiPurpose() {
		return (String) saveArray[ tableFldConstants.purpose.ordinal() ];
	}

	public void setVisiPurpose(String visiPurpose) {
		saveArray[ tableFldConstants.purpose.ordinal() ] = visiPurpose;
	}

	public String getVisiTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setVisiTempfield1(String visiTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = visiTempfield1;
	}

	public String getVisiTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setVisiTempfield2(String visiTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = visiTempfield2;
	}

	public String getVisiTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setVisiTempfield3(String visiTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = visiTempfield3;
	}

	public String getVisiTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setVisiTempfield4(String visiTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = visiTempfield4;
	}

	public String getVisiTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setVisiTempfield5(String visiTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = visiTempfield5;
	}

	public String getVisiActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVisiActive(String visiActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = visiActive;
	}

	public String getVisiCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVisiCreatedby(String visiCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = visiCreatedby;
	}

	public String getVisiCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVisiCreatedon(String visiCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = visiCreatedon;
	}

	public String getVisiModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVisiModifiedon(String visiModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = visiModifiedon;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
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
  
  public static GenTlVisitors fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  GenTlVisitors visi = new GenTlVisitors();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        visi.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return visi;
	}

}

