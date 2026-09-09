package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class BdmTlCriticalityassessment {

	private  Object [] saveArray = null;  
	private String totalRating;

	public enum   tableFldConstants
	{
		keyid, elementid, flid, equipmentid, criteriaid, scores, date
		, doneby, remarks, tempfield6, tempfield7, tempfield8, tempfield9
		, tempfield10, tradeid , tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, active, createdon, modifiedon
	}

	public BdmTlCriticalityassessment()
	{
		saveArray = new  Object [ 23 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getCasmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCasmKeyid(String casmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = casmKeyid;
	}

	public String getCasmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setCasmElementid(String casmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = casmElementid;
	}

	public String getCasmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setCasmFlid(String casmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = casmFlid;
	}

	public String getCasmEquipmentid() {
		return (String) saveArray[ tableFldConstants.equipmentid.ordinal() ];
	}

	public void setCasmEquipmentid(String casmEquipmentid) {
		saveArray[ tableFldConstants.equipmentid.ordinal() ] = casmEquipmentid;
	}

	public String getCasmCriteriaid() {
		return (String) saveArray[ tableFldConstants.criteriaid.ordinal() ];
	}

	public void setCasmCriteriaid(String casmCriteriaid) {
		saveArray[ tableFldConstants.criteriaid.ordinal() ] = casmCriteriaid;
	}

	public String getCasmScores() {
		return (String) saveArray[ tableFldConstants.scores.ordinal() ];
	}

	public void setCasmScores(String casmScores) {
		saveArray[ tableFldConstants.scores.ordinal() ] = casmScores;
	}

	public String getCasmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setCasmDate(String casmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = casmDate;
	}

	public String getCasmDoneby() {
		return (String) saveArray[ tableFldConstants.doneby.ordinal() ];
	}

	public void setCasmDoneby(String casmDoneby) {
		saveArray[ tableFldConstants.doneby.ordinal() ] = casmDoneby;
	}

	public String getCasmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setCasmRemarks(String casmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = casmRemarks;
	}

	public String getCasmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setCasmTempfield6(String casmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = casmTempfield6;
	}

	public String getCasmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setCasmTempfield7(String casmTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = casmTempfield7;
	}

	public String getCasmTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setCasmTempfield8(String casmTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = casmTempfield8;
	}

	public String getCasmTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setCasmTempfield9(String casmTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = casmTempfield9;
	}

	public String getCasmTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setCasmTempfield10(String casmTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = casmTempfield10;
	}

	public String getCasmTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setCasmTradeid(String casmTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = casmTradeid;
	}

	public String getCasmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCasmTempfield2(String casmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = casmTempfield2;
	}

	public String getCasmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCasmTempfield3(String casmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = casmTempfield3;
	}

	public String getCasmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCasmTempfield4(String casmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = casmTempfield4;
	}

	public String getCasmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setCasmTempfield5(String casmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = casmTempfield5;
	}

	public String getCasmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCasmCreatedby(String casmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = casmCreatedby;
	}

	public String getCasmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCasmActive(String casmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = casmActive;
	}

	public String getCasmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCasmCreatedon(String casmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = casmCreatedon;
	}

	public String getCasmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCasmModifiedon(String casmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = casmModifiedon;
	}
	public String getCasmTotalRating() {
		return totalRating;
	}

	public void setCasmTotalRating(String totalRating) {
		this.totalRating=totalRating;
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

public static String toJsonManualList(List<BdmTlCriticalityassessment> list) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    for (int i = 0; i < list.size(); i++) {
        if (i > 0) sb.append(",");
        sb.append(list.get(i).toJsonManual());
    }

    sb.append("]");
    return sb.toString();
}


//public static AbnTlAbnormality fromJson(String json) {
//  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");
//
//  JSONObject obj = JSONObject.fromObject(json);
//
//  AbnTlAbnormality abn = new AbnTlAbnormality();
//  CommonMessage.debugMsg("RAW JSON Response: :"+json);
//
//    for (tableFldConstants field : tableFldConstants.values()) {
//    	String key = field.name();
//    	
//    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
//    	Object rawVal = obj.opt(key);  // Returns Object or null if key not found
//    	String val = null;
//
//    	if (rawVal != null) {
//    	    String strVal = rawVal.toString();
//    	    if (!strVal.equals("{}") && !strVal.equals("-") && !strVal.equalsIgnoreCase("null")) {
//    	        val = strVal;
//    	    }
//    	}
//    	abn.setValue(field, val);
//    	
//    }
//    return abn;
//}

public static BdmTlCriticalityassessment fromJson(String json) {
  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

  JSONObject obj = JSONObject.fromObject(json);

  BdmTlCriticalityassessment bdm = new BdmTlCriticalityassessment();
  CommonMessage.debugMsg("RAW JSON Response: :"+json);

    for (tableFldConstants field : tableFldConstants.values()) {
    	String key = field.name();
    	
    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
        String val = obj.optString(field.name(), null);
        bdm.setValue(field, val != null && val.equals("null") ? null : val);
    	
    }
    return bdm;
}

public static List<BdmTlCriticalityassessment> fromJsonList(String json) {

    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

    JSONArray jsonArray = JSONArray.fromObject(json);
    List<BdmTlCriticalityassessment> list = new ArrayList<>();

    for (int i = 0; i < jsonArray.length(); i++) {

        JSONObject obj = jsonArray.getJSONObject(i);
        BdmTlCriticalityassessment bdm = new BdmTlCriticalityassessment();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();

            Object valueObj = obj.opt(key);
            String val = (valueObj == null || "null".equals(valueObj.toString()))
                         ? null
                         : valueObj.toString();

            bdm.setValue(field, val);
        }

        list.add(bdm);
    }

    return list;
}

}