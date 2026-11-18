package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;


import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PcsTlEnablelosscapture {

	private  Object [] saveArray = null;  

	public enum   tableFldConstants
	{
		keyid, factoryid, cellid, ispcsenabled, effectivefrom, effectivetill
		, qtyortimebased, isgroupbased, ishtlog, type, machineid, option
		, lineid, flid, elementid, active, createdby, createdon, modifiedon
	}

	public BAL_PcsTlEnablelosscapture()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPelcKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public void setPelcKeyid(String pelcKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pelcKeyid;
	}

	public String getPelcFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setPelcFactoryid(String pelcFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = pelcFactoryid;
	}

	public String getPelcCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setPelcCellid(String pelcCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = pelcCellid;
	}

	public String getPelcIspcsenabled() {
		return (String) saveArray[ tableFldConstants.ispcsenabled.ordinal() ];
	}

	public void setPelcIspcsenabled(String pelcIspcsenabled) {
		saveArray[ tableFldConstants.ispcsenabled.ordinal() ] = pelcIspcsenabled;
	}

	public String getPelcEffectivefrom() {
		return (String) saveArray[ tableFldConstants.effectivefrom.ordinal() ];
	}

	public void setPelcEffectivefrom(String pelcEffectivefrom) {
		saveArray[ tableFldConstants.effectivefrom.ordinal() ] = pelcEffectivefrom;
	}

	public String getPelcEffectivetill() {
		return (String) saveArray[ tableFldConstants.effectivetill.ordinal() ];
	}

	public void setPelcEffectivetill(String pelcEffectivetill) {
		saveArray[ tableFldConstants.effectivetill.ordinal() ] = pelcEffectivetill;
	}

	public String getPelcQtyortimebased() {
		return (String) saveArray[ tableFldConstants.qtyortimebased.ordinal() ];
	}

	public void setPelcQtyortimebased(String pelcQtyortimebased) {
		saveArray[ tableFldConstants.qtyortimebased.ordinal() ] = pelcQtyortimebased;
	}

	public String getPelcIsgroupbased() {
		return (String) saveArray[ tableFldConstants.isgroupbased.ordinal() ];
	}

	public void setPelcIsgroupbased(String pelcIsgroupbased) {
		saveArray[ tableFldConstants.isgroupbased.ordinal() ] = pelcIsgroupbased;
	}

	public String getPelcIshtlog() {
		return (String) saveArray[ tableFldConstants.ishtlog.ordinal() ];
	}

	public void setPelcIshtlog(String pelcIshtlog) {
		saveArray[ tableFldConstants.ishtlog.ordinal() ] = pelcIshtlog;
	}

	public String getPelcType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setPelcType(String pelcType) {
		saveArray[ tableFldConstants.type.ordinal() ] = pelcType;
	}

	public String getPelcMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setPelcMachineid(String pelcMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = pelcMachineid;
	}

	public String getPelcOption() {
		return (String) saveArray[ tableFldConstants.option.ordinal() ];
	}

	public void setPelcOption(String pelcOption) {
		saveArray[ tableFldConstants.option.ordinal() ] = pelcOption;
	}

	public String getPelcLineid() {
		return (String) saveArray[ tableFldConstants.lineid.ordinal() ];
	}

	public void setPelcLineid(String pelcLineid) {
		saveArray[ tableFldConstants.lineid.ordinal() ] = pelcLineid;
	}


	public String getPelcFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPelcFlid(String pelcFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = pelcFlid;
	}

	public String getPelcElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setPelcElementid(String pelcElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = pelcElementid;
	}


	public String getPelcActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPelcActive(String pelcActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pelcActive;
	}

	public String getPelcCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPelcCreatedby(String pelcCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pelcCreatedby;
	}

	public String getPelcCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPelcCreatedon(String pelcCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pelcCreatedon;
	}

	public String getPelcModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPelcModifiedon(String pelcModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pelcModifiedon;
	}
	
	//set value
	 public void setValue(tableFldConstants field, Object value) {
	        saveArray[field.ordinal()] = value;
	    }
	
	
    
	
	
	// from json
	
	
	public static List<BAL_PcsTlEnablelosscapture> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BAL_PcsTlEnablelosscapture> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			BAL_PcsTlEnablelosscapture mst = new BAL_PcsTlEnablelosscapture();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				Object valueObj = obj.opt(key);
				String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
				
				mst.setValue(field, val);
			}

			list.add(mst);
		}

		return list;
	}
	
	//to json
	
	public static String toJsonManualList(List<BAL_PcsTlEnablelosscapture> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");

	        Object[] data = list.get(i).getSaveArray();   // data of ONE record
	        sb.append("{");

	        boolean first = true;
	        for (tableFldConstants field : tableFldConstants.values()) {
	            int index = field.ordinal();
	            if (index < data.length) {
	                if (!first) sb.append(",");
	                sb.append("\"").append(field.name()).append("\":");
	                Object val = data[index];
	                if (field.name().equals("keyid") && val == null) {
	                    sb.append("null");
	                } else if (val == null) {
	                    sb.append("\"\"");
	                } else {
	                    sb.append("\"")
	                      .append(val.toString().replace("\\", "\\\\").replace("\"", "\\\""))
	                      .append("\"");
	                }
	                first = false;
	            }
	        }
	        sb.append("}");
	    }

	    sb.append("]");
	    return sb.toString();
	}
	
	/*
	 * public static String toJsonManualList(List<BAL_PcsTlEnablelosscapture> list)
	 * { StringBuilder sb = new StringBuilder(); sb.append("[");
	 * 
	 * for (int i = 0; i < list.size(); i++) { if (i > 0) sb.append(",");
	 * sb.append(list.get(i).toJsonManual()); }
	 * 
	 * sb.append("]"); return sb.toString(); }
	 */

}

