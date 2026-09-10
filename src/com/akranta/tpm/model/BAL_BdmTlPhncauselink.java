package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_BdmTlPhncauselink {

	private  Object [] saveArray = null;  
	

	private List<BAL_BdmTlPhenomenamst> bdmTlPhenomenamst ;
	private List<BAL_BdmTlCausemst> bdmTlCausemst ;
	
	public enum   tableFldConstants
	{
		originalid, elementid, parentid, displaycode, elementtype, active
	}

	public BAL_BdmTlPhncauselink()
	{
		setBdmTlPhenomenamst(new ArrayList<BAL_BdmTlPhenomenamst> ());
		setBdmTlCausemst(new ArrayList<BAL_BdmTlCausemst> ());
		saveArray = new  Object [ 6 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	// added by priyanka on 16/07/2026
	public void setValue(tableFldConstants field, Object val) {
	    saveArray[field.ordinal()] = val;
	}

	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}
	// end 

	public List<BAL_BdmTlPhenomenamst> getBdmTlPhenomenamst() {
		return bdmTlPhenomenamst;
	}

	public void setBdmTlPhenomenamst(List<BAL_BdmTlPhenomenamst> bdmTlPhenomenamst) {
		this.bdmTlPhenomenamst = bdmTlPhenomenamst;
	}

	public List<BAL_BdmTlCausemst> getBdmTlCausemst() {
		return bdmTlCausemst;
	}

	public void setBdmTlCausemst(List<BAL_BdmTlCausemst> bdmTlCausemst) {
		this.bdmTlCausemst = bdmTlCausemst;
	}

	public String getBpclOriginalid() {
		return (String) saveArray[ tableFldConstants.originalid.ordinal() ];
	}

	public void setBpclOriginalid(String bpcl_originalid) {
		saveArray[ tableFldConstants.originalid.ordinal() ] = bpcl_originalid;
	}

	public String getBpclElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setBpclElementid(String bpcl_elementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = bpcl_elementid;
	}

	public String getBpclParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setBpclParentid(String bpcl_parentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = bpcl_parentid;
	}

	public String getBpclDisplaycode() {
		return (String) saveArray[ tableFldConstants.displaycode.ordinal() ];
	}

	public void setBpclDisplaycode(String bpcl_displaycode) {
		saveArray[ tableFldConstants.displaycode.ordinal() ] = bpcl_displaycode;
	}

	public String getBpclElementtype() {
		return (String) saveArray[ tableFldConstants.elementtype.ordinal() ];
	}

	public void setBpclElementtype(String bpcl_elementtype) {
		saveArray[ tableFldConstants.elementtype.ordinal() ] = bpcl_elementtype;
	}

	public String getBpclActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBpclActive(String bpcl_active) {
		saveArray[ tableFldConstants.active.ordinal() ] = bpcl_active;
	}
	
	// added by priyanka on 16/07/2026
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
	            if (field.name().equals("originalid") && val == null) {
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

	public static BAL_BdmTlPhncauselink fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    BAL_BdmTlPhncauselink bpcl = new BAL_BdmTlPhncauselink();
	    CommonMessage.debugMsg("RAW JSON Response: :" + json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = field.name();

	        CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	        String val = obj.optString(field.name(), null);
	        bpcl.setValue(field, val != null && val.equals("null") ? null : val);
	    }
	    return bpcl;
	}

	public static List<BAL_BdmTlPhncauselink> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<BAL_BdmTlPhncauselink> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        BAL_BdmTlPhncauselink bpcl = new BAL_BdmTlPhncauselink();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

	            bpcl.setValue(field, val);
	        }

	        list.add(bpcl);
	    }

	    return list;
	}

	public static String toJsonManualList(List<BAL_BdmTlPhncauselink> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0)
	            sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
	// end 

}

