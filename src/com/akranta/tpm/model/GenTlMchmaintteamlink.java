package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenTlMchmaintteamlink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		machineid, maintenanceteamid, tempfield1, tempfield2, active
		, createdby, createdon, modifiedon
	}

	public GenTlMchmaintteamlink()
	{
		saveArray = new  Object [ 8 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	 public Object getValue(tableFldConstants field) {
	        return saveArray[field.ordinal()];
	    }

	    public void setValue(tableFldConstants field, Object value) {
	        saveArray[field.ordinal()] = value;
	    }

	
	public String getMcmtMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMcmtMachineid(String mcmtMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = mcmtMachineid;
	}

	public String getMcmtMaintenanceteamid() {
		return (String) saveArray[ tableFldConstants.maintenanceteamid.ordinal() ];
	}

	public void setMcmtMaintenanceteamid(String mcmtMaintenanceteamid) {
		saveArray[ tableFldConstants.maintenanceteamid.ordinal() ] = mcmtMaintenanceteamid;
	}

	public String getMcmtTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMcmtTempfield1(String mcmtTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mcmtTempfield1;
	}

	public String getMcmtTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMcmtTempfield2(String mcmtTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mcmtTempfield2;
	}

	public String getMcmtActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMcmtActive(String mcmtActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mcmtActive;
	}

	public String getMcmtCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMcmtCreatedby(String mcmtCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mcmtCreatedby;
	}

	public String getMcmtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMcmtCreatedon(String mcmtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mcmtCreatedon;
	}

	public String getMcmtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMcmtModifiedon(String mcmtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mcmtModifiedon;
	}
	
	public String toJsonManual() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;
        for (tableFldConstants field : tableFldConstants.values()) {
            if (!first) sb.append(",");
            sb.append("\"").append(field.name()).append("\":");
            
            Object val = saveArray[field.ordinal()];
            if (val == null) {
                sb.append("null");
            } else {
                sb.append("\"").append(val.toString()).append("\"");
            }
            first = false;
        }

        sb.append("}");
        return sb.toString();
    }

    public static GenTlMchmaintteamlink fromJson(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        GenTlMchmaintteamlink link = new GenTlMchmaintteamlink();

        for (tableFldConstants field : tableFldConstants.values()) {
            Object valueObj = obj.opt(field.name());
            String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
            link.setValue(field, val);
        }

        return link;
    }

    public static List<GenTlMchmaintteamlink> fromJsonList(String json) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<GenTlMchmaintteamlink> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            GenTlMchmaintteamlink link = new GenTlMchmaintteamlink();

            for (tableFldConstants field : tableFldConstants.values()) {
                Object valueObj = obj.opt(field.name());
                String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
                link.setValue(field, val);
            }

            list.add(link);
        }

        return list;
    }

    public static String toJsonManualList(List<GenTlMchmaintteamlink> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(list.get(i).toJsonManual());
        }

        sb.append("]");
        return sb.toString();
    }

}

