package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlControlandresponseplan.tableFldConstants;

import net.sf.json.JSONObject;

public class QtmTlCapa {
	private  Object [] saveArray = null;  
	public enum   tableFldConstants
	{
		keyid,flid,date,qualityissue,quantityeffect,defect,uom,
        todmt,tojh,complaintraisedby,todmtleaader,areaincharge,type,
        tempfield2,tempfield3,tempfield4,tempfield5, active, createdby,
        createdon,modifiedon
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	public QtmTlCapa()
	{
		saveArray = new  Object [21];
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getQcapKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQcapKeyid(String qcapKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qcapKeyid;
	}

	public String getQcapFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setQcapFlid(String qcapFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = qcapFlid;
	}

	public String getQcapDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setQcapDate(String qcapDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = qcapDate;
	}

	public String getQcapQualityIssue() {
		return (String) saveArray[ tableFldConstants.qualityissue.ordinal() ];
	}

	public void setQcapQualityIssue(String qcapQualityissue) {
		saveArray[ tableFldConstants.qualityissue.ordinal() ] = qcapQualityissue;
	}

	public String getQcapQuantityeffect() {
		return (String) saveArray[ tableFldConstants.quantityeffect.ordinal() ];
	}
	
	public void setQcapQuantityeffect(String qcapQuantityeffect) {
		saveArray[ tableFldConstants.quantityeffect.ordinal() ] = qcapQuantityeffect;
	}

	public String getQcapDefect() {
		return (String) saveArray[ tableFldConstants.defect.ordinal() ];
	}

	public void setQcapDefect(String qcapDefect) {
		saveArray[ tableFldConstants.defect.ordinal() ] = qcapDefect;
	}

	public String getQcapUom() {
		return (String) saveArray[ tableFldConstants.uom.ordinal() ];
	}

	public void setQcapUom(String qcapUom) {
		saveArray[ tableFldConstants.uom.ordinal() ] = qcapUom;
	}

	public String getQcapTodmt() {
		return (String) saveArray[ tableFldConstants.todmt.ordinal() ];
	}

	public void setQcapTodmt(String qcapTodmt) {
		saveArray[ tableFldConstants.todmt.ordinal() ] = qcapTodmt;
	}

	public String getQcapTojh() {
		return (String) saveArray[ tableFldConstants.tojh.ordinal() ];
	}

	public void setQcapTojh(String qcapTojh) {
		saveArray[ tableFldConstants.tojh.ordinal() ] = qcapTojh;
	}
      
         public String getQcapComplaintraisedby() {
		return (String) saveArray[ tableFldConstants.complaintraisedby.ordinal() ];
	}

	public void setQcapComplaintraisedby(String qcapComplaintraisedby) {
		saveArray[ tableFldConstants.complaintraisedby.ordinal() ] = qcapComplaintraisedby;
	}

	public String getQcapTodmtleader() {
		return (String) saveArray[ tableFldConstants.todmtleaader.ordinal() ];
	}

	public void setQcapTodmtleader(String qcapTodmtleader) {
		saveArray[ tableFldConstants.todmtleaader.ordinal() ] = qcapTodmtleader;
	}

	public String getQcapAreaincharge() {
		return (String) saveArray[ tableFldConstants.areaincharge.ordinal() ];
	}

	public void setQcapAreaincharge(String qcapAreaincharge) {
		saveArray[ tableFldConstants.areaincharge.ordinal() ] =qcapAreaincharge;
	}

	public String getQcapType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setQcapType(String qcapType) {
		saveArray[ tableFldConstants.type.ordinal() ] = qcapType;
	}

	public String getQcapTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setQcapTempfield2(String qcapTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = qcapTempfield2;
	}

	public String getQcapTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setQcapTempfield3(String qcapTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = qcapTempfield3;
	}

	public String getQcapTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setQcapTempfield4(String qcapTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = qcapTempfield4;
	}

	public String getQcapTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setQcapTempfield5(String qcapTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = qcapTempfield5;
	}

	public String getQcapActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQcapActive(String qcapActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qcapActive;
	}

	public String getQcapCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQcapCreatedby(String qcapCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qcapCreatedby;
	}

	public String getQcapCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQcapCreatedon(String qcapCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qcapCreatedon;
	}

	public String getQcapModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQcapModifiedon(String qcapModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =qcapModifiedon;
	}
	public void setSaveArray(Object[] saveArray) {
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
  
  public static QtmTlCapa fromJson(String json) {
	  System.out.println("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  QtmTlCapa mst = new QtmTlCapa();
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




