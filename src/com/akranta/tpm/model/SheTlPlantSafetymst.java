
package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class SheTlPlantSafetymst { 

	private  Object [] saveArray = null;  
	private List<SheTlPlantSafetydtl> sheTlPlantSafetydtlList;
	private String elementid;

	public enum   tableFldConstants
	{
		keyid, flid, safetydate, doneby, safetytype,  members, remarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}
	public SheTlPlantSafetymst()
	{
		saveArray = new  Object [ 16 ];
		 sheTlPlantSafetydtlList = new ArrayList<>();
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] dataArr) {
		 this.saveArray = dataArr;
	}
	
	public List<SheTlPlantSafetydtl> getSafetyDetails() {
		return sheTlPlantSafetydtlList;
	}

	public void setSafetyDetails(List<SheTlPlantSafetydtl> sheTlPlantSafetydtlList) {
		this.sheTlPlantSafetydtlList = sheTlPlantSafetydtlList;
	}
	
	public String getSpsmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSpsmKeyid(String spsmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = spsmKeyid;
	}

	public String getSpsmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSpsmFlid(String spsmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = spsmFlid;
	}
	
	public String getSpsmDate() {
		return (String) saveArray[ tableFldConstants.safetydate.ordinal() ];
	}

	public void setSpsmDate(String spsmDate) {
		saveArray[ tableFldConstants.safetydate.ordinal() ] = spsmDate;
	}
	
	public String getSpsmDoneby() {
		return (String) saveArray[ tableFldConstants.doneby.ordinal() ];
	}

	public void setSpsmDoneby(String spsmDoneby) {
		saveArray[ tableFldConstants.doneby.ordinal() ] = spsmDoneby;
	}
	
	public String getSpsmType() {
		return (String) saveArray[ tableFldConstants.safetytype.ordinal() ];
	}

	public void setSpsmType(String spsmType) {
		saveArray[ tableFldConstants.safetytype.ordinal() ] = spsmType;
	}
	
	
	public String getSpsmMembers() {
		return (String) saveArray[ tableFldConstants.members.ordinal() ];
	}

	public void setSpsmMembers(String spsmMembers) {
		saveArray[ tableFldConstants.members.ordinal() ] = spsmMembers;
	}

	public String getSpsmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}
	
	public void setSpsmRemarks(String spsmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = spsmRemarks;
	}

	public String getSpsmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSpsmTempfield1(String spsmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = spsmTempfield1;
	}

	public String getSpsmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSpsmTempfield2(String spsmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = spsmTempfield2;
	}

	public String getSpsmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSpsmTempfield3(String spsmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = spsmTempfield3;
	}

	public String getSpsmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSpsmTempfield4(String spsmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = spsmTempfield4;
	}

	public String getSpsmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSpsmTempfield5(String spsmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = spsmTempfield5;
	}

	public String getSpsmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSpsmActive(String spsmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = spsmActive;
	}

	public String getSpsmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSpsmCreatedby(String spsmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = spsmCreatedby;
	}

	public String getSpsmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSpsmCreatedon(String spsmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = spsmCreatedon;
	}

	public String getSpsmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSpsmModifiedon(String spsmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = spsmModifiedon;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}

	// FIXED: Added setValue method that was missing
	public void setValue(tableFldConstants field, String value) {
        if (field != null) saveArray[field.ordinal()] = value;
    }

    public String toJsonManual() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (tableFldConstants field : tableFldConstants.values()) {
            int idx = field.ordinal();
            if (idx < saveArray.length) {
                if (!first) sb.append(",");
                sb.append("\"").append(field.name()).append("\":");
                Object val = saveArray[idx];
                if (field == tableFldConstants.keyid && val == null) {
                    sb.append("null");
                } else if (val == null) {
                    sb.append("\"{}\"");
                } else {
                    sb.append("\"").append(val).append("\"");
                }
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static SheTlPlantSafetymst fromJson(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        SheTlPlantSafetymst mst = new SheTlPlantSafetymst();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            mst.setValue(field, val != null && val.equals("null") ? null : val);
        }
        return mst;
    }


}























