package com.akranta.tpm.model;

import com.akranta.tpm.model.JhnTlSlamst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlComplaintgallery {

	private  Object [] saveArray = null;  
	private String elementid;

	public enum   tableFldConstants
	{
		keyid,customerid, gradeproduct, correctiveaction, preventiveaction
		, complaintdescription, complaintdate, manufacturedate,  gradespecification 
		, flid, elementid, source , defectid , defectqty ,tempfield1, tempfield2,tempfield3, active, createdby
		, createdon, modifiedon
	}

	public QtmTlComplaintgallery()
	{
		saveArray = new  Object [ 21 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	
	public String getCmgaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCmgaKeyid(String cmgaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cmgaKeyid;
	}

	
	public String getCmgaCustomerid() {
		return (String) saveArray[ tableFldConstants.customerid.ordinal() ];
	}

	public void setCmgaCustomerid(String cmgaCustomerid) {
		saveArray[ tableFldConstants.customerid.ordinal() ] = cmgaCustomerid;
	}

	public String getCmgaGradeproduct() {
		return (String) saveArray[ tableFldConstants.gradeproduct.ordinal() ];
	}

	public void setCmgaGradeproduct(String cmgaGradeproduct) {
		saveArray[ tableFldConstants.gradeproduct.ordinal() ] = cmgaGradeproduct;
	}

	public String getCmgaCorrectiveaction() {
		return (String) saveArray[ tableFldConstants.correctiveaction.ordinal() ];
	}

	public void setCmgaCorrectiveaction(String cmgaCorrectiveaction) {
		saveArray[ tableFldConstants.correctiveaction.ordinal() ] = cmgaCorrectiveaction;
	}

	public String getCmgaPreventiveaction() {
		return (String) saveArray[ tableFldConstants.preventiveaction.ordinal() ];
	}

	public void setCmgaPreventiveaction(String cmgaPreventiveaction) {
		saveArray[ tableFldConstants.preventiveaction.ordinal() ] = cmgaPreventiveaction;
	}

	public String getCmgaComplaintdescription() {
		return (String) saveArray[ tableFldConstants.complaintdescription.ordinal() ];
	}

	public void setCmgaComplaintdescription(String cmgaComplaintdescription) {
		saveArray[ tableFldConstants.complaintdescription.ordinal() ] = cmgaComplaintdescription;
	}

	public String getCmgaComplaintdate() {
		return (String) saveArray[ tableFldConstants.complaintdate.ordinal() ];
	}

	public void setCmgaComplaintdate(String cmgaComplaintdate) {
		saveArray[ tableFldConstants.complaintdate.ordinal() ] = cmgaComplaintdate;
	}

	public String getCmgaManufacturedate() {
		return (String) saveArray[ tableFldConstants.manufacturedate.ordinal() ];
	}

	public void setCmgaManufacturedate(String cmgaManufacturedate) {
		saveArray[ tableFldConstants.manufacturedate.ordinal() ] = cmgaManufacturedate;
	}

	public String getCmgaGradespecification() {
		return (String) saveArray[ tableFldConstants.gradespecification.ordinal() ];
	}
	
	public void setCmgaGradespecification(String cmgaGradespecification) {
		saveArray[ tableFldConstants.gradespecification.ordinal() ] = cmgaGradespecification;
	}

	public String getCmgaFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setCmgaFlid(String cmgaflid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = cmgaflid;
	}

	public String getCmgaElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setCmgaElementid(String cmgaelementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = cmgaelementid;
	}
	
	public String getCmgaSource() {
		return (String) saveArray[ tableFldConstants.source.ordinal() ];
	}

	public void setCmgaSource(String cmgaSource) {
		saveArray[ tableFldConstants.source.ordinal() ] = cmgaSource;
	}	
	
	public String getCmgaDefectid() {
		return (String) saveArray[ tableFldConstants.defectid.ordinal() ];
	}

	public void setCmgaDefectid(String cmgaDefectid) {
		saveArray[ tableFldConstants.defectid.ordinal() ] = cmgaDefectid;
	}

	public String getCmgaDefectqty() {
		return (String) saveArray[ tableFldConstants.defectqty.ordinal() ];
	}

	public void setCmgaDefectqty(String cmgaDefectqty) {
		saveArray[ tableFldConstants.defectqty.ordinal() ] = cmgaDefectqty;
	}

	
	
	public String getCmgaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCmgaTempfield1(String cmgaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = cmgaTempfield1;
	}

	
	public String getCmgaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCmgaTempfield2(String cmgaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cmgaTempfield2;
	}


	public String getCmgaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCmgaTempfield3(String cmgaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cmgaTempfield3;
	}

	
	public String getCmgaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCmgaActive(String cmgaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cmgaActive;
	}

	public String getCmgaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCmgaCreatedby(String cmgaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cmgaCreatedby;
	}

	public String getCmgaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCmgaCreatedon(String cmgaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cmgaCreatedon;
	}

	public String getCmgaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCmgaModifiedon(String cmgaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cmgaModifiedon;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}

	
	/**
	 * Generic setValue method to set values by field enum
	 */
	public void setValue(tableFldConstants field, String value) {
	    if (field != null) {
	        saveArray[field.ordinal()] = value;
	    }
	}

	/**
	 * Convert object to JSON string manually
	 */
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
	                sb.append("\"\"");
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
	 * Parse JSON string to complaint gallery object
	 */
	public static QtmTlComplaintgallery fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    QtmTlComplaintgallery complaint = new QtmTlComplaintgallery();

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = field.name();
	        CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
	        complaint.setValue(field, val != null && val.equals("null") ? null : val);
	    }
	    return complaint;
	}
}

