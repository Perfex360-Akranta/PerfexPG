package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PlmTlGenmaintenance.tableFldConstants;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PlmTlCbmstdcadtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, inspectionid, zoneid, zonecolor, desirablereading, upperlimit
		, lowerlimit, correctiveaction, cbmcondition, pmstandardid, uomid
		, measuringmethod, active, createdby, createdon, modifiedon
	}

	public BAL_PlmTlCbmstdcadtl()
	{
		saveArray = new  Object [ 16 ];
	}
	public  void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getCmdtKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCmdtKeyid(String cmdtKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cmdtKeyid;
	}

	public String getCmdtInspectionid() {
		return (String) saveArray[ tableFldConstants.inspectionid.ordinal() ];
	}

	public void setCmdtInspectionid(String cmdtInspectionid) {
		saveArray[ tableFldConstants.inspectionid.ordinal() ] = cmdtInspectionid;
	}

	public String getCmdtZoneid() {
		return (String) saveArray[ tableFldConstants.zoneid.ordinal() ];
	}

	public void setCmdtZoneid(String cmdtZoneid) {
		saveArray[ tableFldConstants.zoneid.ordinal() ] = cmdtZoneid;
	}

	public String getCmdtZonecolor() {
		return (String) saveArray[ tableFldConstants.zonecolor.ordinal() ];
	}

	public void setCmdtZonecolor(String cmdtZonecolor) {
		saveArray[ tableFldConstants.zonecolor.ordinal() ] = cmdtZonecolor;
	}

	public String getCmdtDesirablereading() {
		return (String) saveArray[ tableFldConstants.desirablereading.ordinal() ];
	}

	public void setCmdtDesirablereading(String cmdtDesirablereading) {
		saveArray[ tableFldConstants.desirablereading.ordinal() ] = cmdtDesirablereading;
	}

	public String getCmdtUpperlimit() {
		return (String) saveArray[ tableFldConstants.upperlimit.ordinal() ];
	}

	public void setCmdtUpperlimit(String cmdtUpperlimit) {
		saveArray[ tableFldConstants.upperlimit.ordinal() ] = cmdtUpperlimit;
	}

	public String getCmdtLowerlimit() {
		return (String) saveArray[ tableFldConstants.lowerlimit.ordinal() ];
	}

	public void setCmdtLowerlimit(String cmdtLowerlimit) {
		saveArray[ tableFldConstants.lowerlimit.ordinal() ] = cmdtLowerlimit;
	}

	public String getCmdtCorrectiveaction() {
		return (String) saveArray[ tableFldConstants.correctiveaction.ordinal() ];
	}

	public void setCmdtCorrectiveaction(String cmdtCorrectiveaction) {
		saveArray[ tableFldConstants.correctiveaction.ordinal() ] = cmdtCorrectiveaction;
	}

	public String getCmdtCbmcondition() {
		return (String) saveArray[ tableFldConstants.cbmcondition.ordinal() ];
	}

	public void setCmdtCbmcondition(String cmdtCbmcondition) {
		saveArray[ tableFldConstants.cbmcondition.ordinal() ] = cmdtCbmcondition;
	}

	public String getCmdtPmstandardid() {
		return (String) saveArray[ tableFldConstants.pmstandardid.ordinal() ];
	}

	public void setCmdtPmstandardid(String cmdtPmstandardid) {
		saveArray[ tableFldConstants.pmstandardid.ordinal() ] = cmdtPmstandardid;
	}

	public String getCmdtUomid() {
		return (String) saveArray[ tableFldConstants.uomid.ordinal() ];
	}

	public void setCmdtUomid(String cmdtUomid) {
		saveArray[ tableFldConstants.uomid.ordinal() ] = cmdtUomid;
	}

	public String getCmdtMeasuringmethod() {
		return (String) saveArray[ tableFldConstants.measuringmethod.ordinal() ];
	}

	public void setCmdtMeasuringmethod(String cmdtMeasuringmethod) {
		saveArray[ tableFldConstants.measuringmethod.ordinal() ] = cmdtMeasuringmethod;
	}

	public String getCmdtActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCmdtActive(String cmdtActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cmdtActive;
	}

	public String getCmdtCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCmdtCreatedby(String cmdtCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cmdtCreatedby;
	}

	public String getCmdtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCmdtCreatedon(String cmdtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cmdtCreatedon;
	}

	public String getCmdtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCmdtModifiedon(String cmdtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cmdtModifiedon;
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
	            if (field.name() == "keyid" && val == null) {
	                sb.append("null");
	            } else if (val == null) {
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

	public static String toJsonManualList(List<BAL_PlmTlCbmstdcadtl> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}

	public void setValue(tableFldConstants field, Object val) {
	    saveArray[field.ordinal()] = val;
	}

	public static BAL_PlmTlCbmstdcadtl fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    BAL_PlmTlCbmstdcadtl cbm = new BAL_PlmTlCbmstdcadtl();
	    CommonMessage.debugMsg("RAW JSON Response: :" + json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = field.name();

	        CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	        String val = obj.optString(field.name(), null);
	        cbm.setValue(field, val != null && val.equals("null") ? null : val);
	    }
	    return cbm;
	}

	public static List<BAL_PlmTlCbmstdcadtl> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<BAL_PlmTlCbmstdcadtl> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        BAL_PlmTlCbmstdcadtl cbm = new BAL_PlmTlCbmstdcadtl();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            cbm.setValue(field, val);
	        }

	        list.add(cbm);
	    }

	    return list;
	}
}