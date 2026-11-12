package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SheTlPlnjobemplink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, pjob_keyid, pjob_employeeid, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public SheTlPlnjobemplink()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getPjelKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPjelKeyid(String pjelKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pjelKeyid;
	}

	public String getPjelPjobKeyid() {
		return (String) saveArray[ tableFldConstants.pjob_keyid.ordinal() ];
	}

	public void setPjelPjobKeyid(String pjelPjobKeyid) {
		saveArray[ tableFldConstants.pjob_keyid.ordinal() ] = pjelPjobKeyid;
	}

	public String getPjelPjobEmployeeid() {
		return (String) saveArray[ tableFldConstants.pjob_employeeid.ordinal() ];
	}

	public void setPjelPjobEmployeeid(String pjelPjobEmployeeid) {
		saveArray[ tableFldConstants.pjob_employeeid.ordinal() ] = pjelPjobEmployeeid;
	}

	public String getPjelTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPjelTempfield1(String pjelTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pjelTempfield1;
	}

	public String getPjelTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPjelTempfield2(String pjelTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pjelTempfield2;
	}

	public String getPjelTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPjelTempfield3(String pjelTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = pjelTempfield3;
	}

	public String getPjelTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPjelTempfield4(String pjelTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = pjelTempfield4;
	}

	public String getPjelActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPjelActive(String pjobActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pjobActive;
	}

	public String getPjelCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPjelCreatedby(String pjobCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pjobCreatedby;
	}

	public String getPjelCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPjelCreatedon(String pjobCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pjobCreatedon;
	}

	public String getPjelModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPjelModifiedon(String pjobModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pjobModifiedon;
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
     * Convert detail object to JSON string
     */
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
                if (field.name().equals("keyid") && val == null) {
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

    /**
     * Parse JSON string to SheTlPlnjobemplink object
     */
    public static SheTlPlnjobemplink fromJson(String json) {
        System.out.println("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        SheTlPlnjobemplink detail = new SheTlPlnjobemplink();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            System.out.println("JSON[" + field + "] :" + obj.get(key));
            String val = obj.optString(field.name(), null);
            detail.setValue(field, val != null && val.equals("null") ? null : val);
        }
        return detail;
    }

    /**
     * Parse JSON array to list of SheTlPlnjobemplink objects
     */
    public static List<SheTlPlnjobemplink> fromJsonList(String json) {
        System.out.println("RAW JSON ARRAY Response: " + json);

        JSONArray jsonArray = JSONArray.fromObject(json);
        List<SheTlPlnjobemplink> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            SheTlPlnjobemplink detail = new SheTlPlnjobemplink();

            for (tableFldConstants field : tableFldConstants.values()) {
                Object valueObj = obj.opt(field.name());
                String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
                detail.setValue(field, val);
            }
            list.add(detail);
        }
        return list;
    }

    /**
     * Convert list of SheTlPlnjobemplink objects to JSON array string
     */
    public static String toJsonManualList(List<SheTlPlnjobemplink> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(list.get(i).toJsonManual());
            }
        }

        sb.append("]");
        return sb.toString();
    }
}

