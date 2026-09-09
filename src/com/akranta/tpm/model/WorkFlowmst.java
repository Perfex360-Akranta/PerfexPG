package com.akranta.tpm.model;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlProjectcreationmst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class WorkFlowmst {

	private  Object [] saveArray = null;  
	private List<GenTlWorkflowdtl> genTlWorkflowdtlList ;

	public enum   tableFldConstants
	{
		keyid, name, noofstage, tempfield1, tempfield2, tempfield3, tempfield4
		, active, createdby, createdon, modifiedon
	}

	public WorkFlowmst()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setWorkFlowDtls(List<GenTlWorkflowdtl> genTlWorkflowdtlList) {
		this.genTlWorkflowdtlList = genTlWorkflowdtlList;
	}	
	
	public List<GenTlWorkflowdtl> getWorkFlowDtls() {		
		return genTlWorkflowdtlList;
	}	

	public String getWrkmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWrkmKeyid(String wrkmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wrkmKeyid;
	}

	public String getWrkmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setWrkmName(String wrkmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = wrkmName;
	}

	public String getWrkmNoofstage() {
		return (String) saveArray[ tableFldConstants.noofstage.ordinal() ];
	}

	public void setWrkmNoofstage(String wrkmNoofstage) {
		saveArray[ tableFldConstants.noofstage.ordinal() ] = wrkmNoofstage;
	}

	public String getWrkmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWrkmTempfield1(String wrkmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wrkmTempfield1;
	}

	public String getWrkmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWrkmTempfield2(String wrkmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wrkmTempfield2;
	}

	public String getWrkmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWrkmTempfield3(String wrkmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wrkmTempfield3;
	}

	public String getWrkmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setWrkmTempfield4(String wrkmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = wrkmTempfield4;
	}

	public String getWrkmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWrkmActive(String wrkmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wrkmActive;
	}

	public String getWrkmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWrkmCreatedby(String wrkmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wrkmCreatedby;
	}

	public String getWrkmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWrkmCreatedon(String wrkmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wrkmCreatedon;
	}

	public String getWrkmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWrkmModifiedon(String wrkmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wrkmModifiedon;
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
  
 
  
  
  public static WorkFlowmst fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  WorkFlowmst wfm = new WorkFlowmst();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
	        wfm.setValue(field,  val);
	    	
	    }
	    return wfm;
	}

}

