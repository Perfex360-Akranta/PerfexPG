package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.PlmTlEquipmentfmeamst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class PlmTlProcessfmeamst {

	private  Object [] saveArray = null;  
	
	List<PlmTlProcessfmeadtl> plmTlProcessfmeadtl;

	public enum   tableFldConstants
	{
		keyid, flid, date, no, processid, supprocessid, preparedby, coreteam
		, doctype, docmstid, docdtlsid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public PlmTlProcessfmeamst()
	{
		setPlmTlProcessfmeadtl(new ArrayList());
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFmpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFmpmKeyid(String fmpmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fmpmKeyid;
	}

	public String getFmpmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setFmpmFlid(String fmpmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = fmpmFlid;
	}

	public String getFmpmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setFmpmDate(String fmpmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = fmpmDate;
	}

	public String getFmpmNo() {
		return (String) saveArray[ tableFldConstants.no.ordinal() ];
	}

	public void setFmpmNo(String fmpmNo) {
		saveArray[ tableFldConstants.no.ordinal() ] = fmpmNo;
	}

	public String getFmpmProcessid() {
		return (String) saveArray[ tableFldConstants.processid.ordinal() ];
	}

	public void setFmpmProcessid(String fmpmProcessid) {
		saveArray[ tableFldConstants.processid.ordinal() ] = fmpmProcessid;
	}

	public String getFmpmSupprocessid() {
		return (String) saveArray[ tableFldConstants.supprocessid.ordinal() ];
	}

	public void setFmpmSupprocessid(String fmpmSupprocessid) {
		saveArray[ tableFldConstants.supprocessid.ordinal() ] = fmpmSupprocessid;
	}

	public String getFmpmPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setFmpmPreparedby(String fmpmPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = fmpmPreparedby;
	}

	public String getFmpmCoreteam() {
		return (String) saveArray[ tableFldConstants.coreteam.ordinal() ];
	}

	public void setFmpmCoreteam(String fmpmCoreteam) {
		saveArray[ tableFldConstants.coreteam.ordinal() ] = fmpmCoreteam;
	}
	
	public String getFmpmDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setFmpmDoctype(String fmpmDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = fmpmDoctype;
	}

	public String getFmpmDocmstid() {
		return (String) saveArray[ tableFldConstants.docmstid.ordinal() ];
	}

	public void setFmpmDocmstid(String fmpmDocmstid) {
		saveArray[ tableFldConstants.docmstid.ordinal() ] = fmpmDocmstid;
	}

	public String getFmpmDocdtlsid() {
		return (String) saveArray[ tableFldConstants.docdtlsid.ordinal() ];
	}

	public void setFmpmDocdtlsid(String fmpmDocdtlsid) {
		saveArray[ tableFldConstants.docdtlsid.ordinal() ] = fmpmDocdtlsid;
	}
	
	public String getFmpmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFmpmTempfield1(String fmpmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fmpmTempfield1;
	}

	public String getFmpmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFmpmTempfield2(String fmpmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fmpmTempfield2;
	}

	public String getFmpmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFmpmTempfield3(String fmpmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fmpmTempfield3;
	}

	public String getFmpmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFmpmTempfield4(String fmpmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fmpmTempfield4;
	}

	public String getFmpmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFmpmTempfield5(String fmpmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fmpmTempfield5;
	}

	public String getFmpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFmpmActive(String fmpmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fmpmActive;
	}

	public String getFmpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFmpmCreatedby(String fmpmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fmpmCreatedby;
	}

	public String getFmpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFmpmCreatedon(String fmpmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fmpmCreatedon;
	}

	public String getFmpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFmpmModifiedon(String fmpmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fmpmModifiedon;
	}

	public void setPlmTlProcessfmeadtl(List<PlmTlProcessfmeadtl> plmTlProcessfmeadtl) {
		this.plmTlProcessfmeadtl = plmTlProcessfmeadtl;
	}

	public List<PlmTlProcessfmeadtl> getplmTlProcessfmeadtl() {
		return plmTlProcessfmeadtl;
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
	 public static PlmTlProcessfmeamst fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  PlmTlProcessfmeamst equip = new PlmTlProcessfmeamst();
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

