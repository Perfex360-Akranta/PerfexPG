package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.QtmTlCapa.tableFldConstants;

import net.sf.json.JSONObject;

public class QtmTlCapaWorkflow{
	private  Object [] saveArray = null;  
	public enum   tableFldConstants
	{
        keyid,caparefid,roleid,approvedby,status,date,
        remarks,tempfield1,tempfield2,tempfield3,tempfield4,
        active,createdby,createdon,modifiedon
	}
	
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	public QtmTlCapaWorkflow()
	{
		saveArray = new  Object [15];
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getQcwfKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQcwfKeyid(String qcwfKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qcwfKeyid;
	}

	public String getQcwfCaparefid() {
		return (String) saveArray[ tableFldConstants.caparefid.ordinal() ];
	}

	public void setQcwfCaparefid(String qcwfCaparefid) {
		saveArray[ tableFldConstants.caparefid.ordinal() ] = qcwfCaparefid;
	}

	public String getQcwfRoleid() {
		return (String) saveArray[ tableFldConstants.roleid.ordinal() ];
	}

	public void setQcwfRoleid(String qcwfRoleid) {
		saveArray[ tableFldConstants.roleid.ordinal() ] =qcwfRoleid;
	}

	public String getQcwfApproveby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setQcwfApproveby(String qcwfApproveby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] =qcwfApproveby;
	}

	public String getQcwfStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}
	
	public void setQcwfStatus(String qcwfStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = qcwfStatus;
	}

	public String getQcwfDate(){
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setQcwfRemarks(String qcwfRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = qcwfRemarks;
	}

	public String getQcwfRemarks(){
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setQcwfDate(String qcwfDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = qcwfDate;
	}

	public String getQcwfTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setQcwfTempfield1(String qcwfTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = qcwfTempfield1;
	}

	public String getQcwfTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setQcwfTempfield2(String qcwfTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = qcwfTempfield2;
	}

	public String getQcwfTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setQcwfTempfield3(String qcwfTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = qcwfTempfield3;
	}

	public String getQcwfTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setQcwfTempfield4(String qcwfTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = qcwfTempfield4;
	}



	public String getQcwfActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQcwfActive(String qcwfActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qcwfActive;
	}

	public String getQcwfCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQcwfCreatedby(String qcwfCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qcwfCreatedby;
	}

	public String getQcwfCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQcwfCreatedon(String qcwfCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qcwfCreatedon;
	}

	public String getQcwfModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQcwfModifiedon(String qcwfModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = qcwfModifiedon;
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
  
  public static QtmTlCapaWorkflow fromJson(String json) {
	  System.out.println("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  QtmTlCapaWorkflow mst = new QtmTlCapaWorkflow();
	  System.out.println("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	System.out.println("JSON[" + field + "] :"+obj.get(key));
	        //String val = obj.optString(field.name(), null);
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
	        //mst.setValue(field, val != null && val.equals("null") ? null : val);
	    	mst.setValue(field, val);
	    }
	    return mst;
	}
	
}