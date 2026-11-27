package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONObject;

public class BAL_PcsTlPcsBd {
	
	private  Object [] saveArray = null;
	private List<BAL_PcsTlPcsBd> pcsbd ;
	public enum   tableFldConstants
	{
	 keyid,bdmKeyid,active,createdOn,createdBy;
	}
	public BAL_PcsTlPcsBd()
	{
		setPcsTlPcsBd(new ArrayList<BAL_PcsTlPcsBd> ());
		
		saveArray = new  Object [ 5 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public List<BAL_PcsTlPcsBd> getPcsTlPcsBd(){
		return pcsbd;
	}
	private void setPcsTlPcsBd(List<BAL_PcsTlPcsBd> pcsbd) {
		
		this.pcsbd=pcsbd;
				
	}
	public String getPcsbdKeyid(){
		return (String) saveArray[tableFldConstants.keyid.ordinal()];
	}
	public void setPcsbdKeyid(String bdKeyid){
		
		saveArray[tableFldConstants.keyid.ordinal()]=bdKeyid;
	}
	public String getPcsBdmsKeyid(){
		return(String) saveArray[tableFldConstants.bdmKeyid.ordinal()];
	}
	public void setPcsBdmsKeyid(String bdmsKeyid){
		saveArray[tableFldConstants.bdmKeyid.ordinal()]=bdmsKeyid;
	}
	public String getActive(){
		return(String) saveArray[tableFldConstants.active.ordinal()];
	}
	public void setActive(String active){
		saveArray[tableFldConstants.active.ordinal()]=active;
	}
	public String getCreatedOn(){
		return (String) saveArray[tableFldConstants.createdOn.ordinal()];
	}
	public void setCreatedOn(String createdOn){
		 saveArray[tableFldConstants.createdOn.ordinal()]=createdOn;
	}
	public String getCreatedBy(){
		 return (String) saveArray[tableFldConstants.createdBy.ordinal()];
	}
	public void setCreatedBy(String createdBy){
		saveArray[tableFldConstants.createdBy.ordinal()]=createdBy;
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	
	
	 public static BAL_PcsTlPcsBd fromJson(String json) {
		  //CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  BAL_PcsTlPcsBd pcsbd = new BAL_PcsTlPcsBd();
		  //CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	//CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        pcsbd.setValue(field, val);
		    	
		    }
		    return pcsbd;
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

	  
}
