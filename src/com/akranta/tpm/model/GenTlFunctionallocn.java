package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlLocationmst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlFunctionallocn {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		originalid, elementid, parentid, displaycode, description, elementtype
		, active,keyid
	}

	public GenTlFunctionallocn()
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


	public String getFnlnOriginalid() {
		return (String) saveArray[ tableFldConstants.originalid.ordinal() ];
	}

	public void setFnlnOriginalid(String fnlnOriginalid) {
		saveArray[ tableFldConstants.originalid.ordinal() ] = fnlnOriginalid;
	}

	public String getFnlnElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setFnlnElementid(String fnlnElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = fnlnElementid;
	}

	public String getFnlnParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setFnlnParentid(String fnlnParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = fnlnParentid;
	}

	public String getFnlnDisplaycode() {
		return (String) saveArray[ tableFldConstants.displaycode.ordinal() ];
	}

	public void setFnlnDisplaycode(String fnlnDisplaycode) {
		saveArray[ tableFldConstants.displaycode.ordinal() ] = fnlnDisplaycode;
	}

	public String getFnlnDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setFnlnDescription(String fnlnDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = fnlnDescription;
	}

	public String getFnlnElementtype() {
		return (String) saveArray[ tableFldConstants.elementtype.ordinal() ];
	}

	public void setFnlnElementtype(String fnlnElementtype) {
		saveArray[ tableFldConstants.elementtype.ordinal() ] = fnlnElementtype;
	}

	public String getFnlnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFnlnActive(String fnlnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fnlnActive;
	}
	
	public String getFnlnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFnlnKeyid(String fnlnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fnlnKeyid;
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
                if (val == null) {
                    sb.append("null");
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
     * Parse a single location from JSON string
     */
    public static GenTlFunctionallocn fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        GenTlFunctionallocn location = new GenTlFunctionallocn();

        for (tableFldConstants field : tableFldConstants.values()) {
            Object valueObj = obj.opt(field.name());
            String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
            location.setValue(field, val);
        }

        return location;
    }

    /**
     * Parse a list of locations from JSON array string
     */
    public static List<GenTlFunctionallocn> fromJsonList(String json) {
        CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

        JSONArray jsonArray = JSONArray.fromObject(json);
        List<GenTlFunctionallocn> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            GenTlFunctionallocn location = new GenTlFunctionallocn();

            for (tableFldConstants field : tableFldConstants.values()) {
                Object valueObj = obj.opt(field.name());
                String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
                location.setValue(field, val);
            }

            list.add(location);
        }

        return list;
    }

    /**
     * Convert a list of locations to JSON array string
     */
    public static String toJsonManualList(List<GenTlFunctionallocn> list) {
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

