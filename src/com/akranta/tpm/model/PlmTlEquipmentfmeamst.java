package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.PlmTlEquipmentfmeadtl.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class PlmTlEquipmentfmeamst {

	private  Object [] saveArray = null;  
	
	List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtl;

	public enum   tableFldConstants
	{
		keyid, flid, date, no, equipid, supequipid, preparedby, coreteam
		, doctype, docmstid, docdtlsid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	public PlmTlEquipmentfmeamst()
	{
		setPlmTlEquipmentfmeadtl(new ArrayList());
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFmeqKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFmeqKeyid(String fmeqKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fmeqKeyid;
	}

	public String getFmeqFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setFmeqFlid(String fmeqFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = fmeqFlid;
	}

	public String getFmeqDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setFmeqDate(String fmeqDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = fmeqDate;
	}

	public String getFmeqNo() {
		return (String) saveArray[ tableFldConstants.no.ordinal() ];
	}

	public void setFmeqNo(String fmeqNo) {
		saveArray[ tableFldConstants.no.ordinal() ] = fmeqNo;
	}

	public String getFmeqEquipid() {
		return (String) saveArray[ tableFldConstants.equipid.ordinal() ];
	}

	public void setFmeqEquipid(String fmeqEquipid) {
		saveArray[ tableFldConstants.equipid.ordinal() ] = fmeqEquipid;
	}

	public String getFmeqSupequipid() {
		return (String) saveArray[ tableFldConstants.supequipid.ordinal() ];
	}

	public void setFmeqSupequipid(String fmeqSupequipid) {
		saveArray[ tableFldConstants.supequipid.ordinal() ] = fmeqSupequipid;
	}

	public String getFmeqPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setFmeqPreparedby(String fmeqPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = fmeqPreparedby;
	}

	public String getFmeqCoreteam() {
		return (String) saveArray[ tableFldConstants.coreteam.ordinal() ];
	}

	public void setFmeqCoreteam(String fmeqCoreteam) {
		saveArray[ tableFldConstants.coreteam.ordinal() ] = fmeqCoreteam;
	}
	
	public String getFmeqDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setFmeqDoctype(String fmeqDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = fmeqDoctype;
	}

	public String getFmeqDocmstid() {
		return (String) saveArray[ tableFldConstants.docmstid.ordinal() ];
	}

	public void setFmeqDocmstid(String fmeqDocmstid) {
		saveArray[ tableFldConstants.docmstid.ordinal() ] = fmeqDocmstid;
	}

	public String getFmeqDocdtlsid() {
		return (String) saveArray[ tableFldConstants.docdtlsid.ordinal() ];
	}

	public void setFmeqDocdtlsid(String fmdmDocdtlsid) {
		saveArray[ tableFldConstants.docdtlsid.ordinal() ] = fmdmDocdtlsid;
	}

	public String getFmeqTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFmeqTempfield1(String fmeqTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fmeqTempfield1;
	}

	public String getFmeqTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFmeqTempfield2(String fmeqTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fmeqTempfield2;
	}

	public String getFmeqTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFmeqTempfield3(String fmeqTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fmeqTempfield3;
	}

	public String getFmeqTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFmeqTempfield4(String fmeqTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fmeqTempfield4;
	}

	public String getFmeqTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFmeqTempfield5(String fmeqTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fmeqTempfield5;
	}

	public String getFmeqActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFmeqActive(String fmeqActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fmeqActive;
	}

	public String getFmeqCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFmeqCreatedby(String fmeqCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fmeqCreatedby;
	}

	public String getFmeqCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFmeqCreatedon(String fmeqCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fmeqCreatedon;
	}

	public String getFmeqModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFmeqModifiedon(String fmeqModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fmeqModifiedon;
	}

	public void setPlmTlEquipmentfmeadtl(List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtl) {
		this.plmTlEquipmentfmeadtl = plmTlEquipmentfmeadtl;
	}

	public List<PlmTlEquipmentfmeadtl> getplmTlEquipmentfmeadtl() {
		return plmTlEquipmentfmeadtl;
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
	 public static PlmTlEquipmentfmeamst fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  PlmTlEquipmentfmeamst equip = new PlmTlEquipmentfmeamst();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        equip.setValue(field, val);
		    	
		    }
		    return equip;
		}

}

