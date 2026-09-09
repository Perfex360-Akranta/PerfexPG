package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenTlMchemplink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		machineid, employeeid, tempfield1, tempfield2, active, createdby
		, createdon, modifiedon
	}

	public GenTlMchemplink()
	{
		saveArray = new  Object [ 8 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMcemMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMcemMachineid(String mcemMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = mcemMachineid;
	}

	public String getMcemEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setMcemEmployeeid(String mcemEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = mcemEmployeeid;
	}

	public String getMcemTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMcemTempfield1(String mcemTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mcemTempfield1;
	}

	public String getMcemTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMcemTempfield2(String mcemTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mcemTempfield2;
	}

	public String getMcemActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMcemActive(String mcemActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mcemActive;
	}

	public String getMcemCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMcemCreatedby(String mcemCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mcemCreatedby;
	}

	public String getMcemCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMcemCreatedon(String mcemCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mcemCreatedon;
	}

	public String getMcemModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMcemModifiedon(String mcemModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mcemModifiedon;
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

    public static GenTlMchemplink fromJson(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        GenTlMchemplink link = new GenTlMchemplink();

        for (tableFldConstants field : tableFldConstants.values()) {
            Object valueObj = obj.opt(field.name());
            String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
            link.setValue(field, val);
        }

        return link;
    }

    public static List<GenTlMchemplink> fromJsonList(String json) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<GenTlMchemplink> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            GenTlMchemplink link = new GenTlMchemplink();

            for (tableFldConstants field : tableFldConstants.values()) {
                Object valueObj = obj.opt(field.name());
                String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
                link.setValue(field, val);
            }

            list.add(link);
        }

        return list;
    }

    public static String toJsonManualList(List<GenTlMchemplink> list) {
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

