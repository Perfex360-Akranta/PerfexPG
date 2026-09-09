package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlControlandresponseplan.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlQpoint {

	private  Object [] saveArray = null;  
	
	private QtmTlQpointdtls qtmTlQpointdtls;
	private String isDtlTrue;
	
	public enum   tableFldConstants
	{
		keyid, flid, elementid, area, kpov, qpoint, preparedby, date
		, nooflocations, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public QtmTlQpoint()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getQptmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQptmKeyid(String qptmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qptmKeyid;
	}

	public String getQptmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setQptmFlid(String qptmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = qptmFlid;
	}

	public String getQptmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setQptmElementid(String qptmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = qptmElementid;
	}

	public String getQptmArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setQptmArea(String qptmArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = qptmArea;
	}

	public String getQptmKpov() {
		return (String) saveArray[ tableFldConstants.kpov.ordinal() ];
	}

	public void setQptmKpov(String qptmKpov) {
		saveArray[ tableFldConstants.kpov.ordinal() ] = qptmKpov;
	}

	public String getQptmQpoint() {
		return (String) saveArray[ tableFldConstants.qpoint.ordinal() ];
	}

	public void setQptmQpoint(String qptmQpoint) {
		saveArray[ tableFldConstants.qpoint.ordinal() ] = qptmQpoint;
	}

	public String getQptmPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setQptmPreparedby(String qptmPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = qptmPreparedby;
	}

	public String getQptmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setQptmDate(String qptmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = qptmDate;
	}

	public String getQptmNooflocations() {
		return (String) saveArray[ tableFldConstants.nooflocations.ordinal() ];
	}

	public void setQptmNooflocations(String qptmNooflocations) {
		saveArray[ tableFldConstants.nooflocations.ordinal() ] = qptmNooflocations;
	}

	public String getQptmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setQptmTempfield1(String qptmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = qptmTempfield1;
	}

	public String getQptmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setQptmTempfield2(String qptmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = qptmTempfield2;
	}

	public String getQptmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setQptmTempfield3(String qptmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = qptmTempfield3;
	}

	public String getQptmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setQptmTempfield4(String qptmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = qptmTempfield4;
	}

	public String getQptmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setQptmTempfield5(String qptmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = qptmTempfield5;
	}

	public String getQptmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQptmActive(String qptmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qptmActive;
	}

	public String getQptmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQptmCreatedby(String qptmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qptmCreatedby;
	}

	public String getQptmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQptmCreatedon(String qptmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qptmCreatedon;
	}

	public String getQptmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQptmModifiedon(String qptmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = qptmModifiedon;
	}

	public void setQtmTlQpointdtls(QtmTlQpointdtls qtmTlQpointdtls) {
		this.qtmTlQpointdtls = qtmTlQpointdtls;
	}

	public QtmTlQpointdtls getQtmTlQpointdtls() {
		return qtmTlQpointdtls;
	}

	public void setIsDtlTrue(String isDtlTrue) {
		this.isDtlTrue = isDtlTrue;
	}

	public String getIsDtlTrue() {
		return isDtlTrue;
	}

	public void setSaveArray(Object[] dataArr) {
		// TODO Auto-generated method stub
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
  
  public static QtmTlQpoint fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  QtmTlQpoint mst = new QtmTlQpoint();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        //mst.setValue(field, val != null && val.equals("null") ? null : val);
	        
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
	        mst.setValue(field, val);
	    	
	    }
	    return mst;
	}
  
	

}

