package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenTlMachineskillmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		machineid, skilldescription, skillfordepartment, tempfield1, tempfield2
		, active, createdby, createdon, modifiedon
	}

	public GenTlMachineskillmst()
	{
		saveArray = new  Object [ 9 ];
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

	public String getMskmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMskmMachineid(String mskmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = mskmMachineid;
	}

	public String getMskmSkilldescription() {
		return (String) saveArray[ tableFldConstants.skilldescription.ordinal() ];
	}

	public void setMskmSkilldescription(String mskmSkilldescription) {
		saveArray[ tableFldConstants.skilldescription.ordinal() ] = mskmSkilldescription;
	}

	public String getMskmSkillfordepartment() {
		return (String) saveArray[ tableFldConstants.skillfordepartment.ordinal() ];
	}

	public void setMskmSkillfordepartment(String mskmSkillfordepartment) {
		saveArray[ tableFldConstants.skillfordepartment.ordinal() ] = mskmSkillfordepartment;
	}

	public String getMskmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMskmTempfield1(String mskmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mskmTempfield1;
	}

	public String getMskmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMskmTempfield2(String mskmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mskmTempfield2;
	}

	public String getMskmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMskmActive(String mskmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mskmActive;
	}

	public String getMskmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMskmCreatedby(String mskmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mskmCreatedby;
	}

	public String getMskmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMskmCreatedon(String mskmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mskmCreatedon;
	}

	public String getMskmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMskmModifiedon(String mskmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mskmModifiedon;
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

    public static GenTlMachineskillmst fromJson(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        GenTlMachineskillmst skill = new GenTlMachineskillmst();

        for (tableFldConstants field : tableFldConstants.values()) {
            Object valueObj = obj.opt(field.name());
            String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
            skill.setValue(field, val);
        }

        return skill;
    }

    public static List<GenTlMachineskillmst> fromJsonList(String json) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<GenTlMachineskillmst> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            GenTlMachineskillmst skill = new GenTlMachineskillmst();

            for (tableFldConstants field : tableFldConstants.values()) {
                Object valueObj = obj.opt(field.name());
                String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
                skill.setValue(field, val);
            }

            list.add(skill);
        }

        return list;
    }

    public static String toJsonManualList(List<GenTlMachineskillmst> list, String department) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(",");
            // Ensure skillfordepartment is set
            list.get(i).setMskmSkillfordepartment(department);
            sb.append(list.get(i).toJsonManual());
        }

        sb.append("]");
        return sb.toString();
    }

}

