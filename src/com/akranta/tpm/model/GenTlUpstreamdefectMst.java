package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlUpstreamdefectMst {

	private  Object [] saveArray = null;  
	
	private GenTlUpstreamdefectDet Upstreamdefect ;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, area, title, remakrs, inspectionlotno
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public GenTlUpstreamdefectMst()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}
	
	public String getUpsmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setUpsmKeyid(String upsmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = upsmKeyid;
	}

	public String getUpsmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setUpsmFlid(String upsmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = upsmFlid;
	}

	public String getUpsmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setUpsmElementid(String upsmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = upsmElementid;
	}

	public String getUpsmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setUpsmDate(String upsmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = upsmDate;
	}

	public String getUpsmArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setUpsmArea(String upsmArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = upsmArea;
	}

	public String getUpsmTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setUpsmTitle(String upsmTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = upsmTitle;
	}

	public String getUpsmRemakrs() {
		return (String) saveArray[ tableFldConstants.remakrs.ordinal() ];
	}

	public void setUpsmRemakrs(String upsmRemakrs) {
		saveArray[ tableFldConstants.remakrs.ordinal() ] = upsmRemakrs;
	}

	public String getUpsmInspectionlotno() {
		return (String) saveArray[ tableFldConstants.inspectionlotno.ordinal() ];
	}

	public void setUpsmInspectionlotno(String upsmInspectionlotno) {
		saveArray[ tableFldConstants.inspectionlotno.ordinal() ] = upsmInspectionlotno;
	}

	public String getUpsmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setUpsmTempfield1(String upsmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = upsmTempfield1;
	}

	public String getUpsmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setUpsmTempfield2(String upsmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = upsmTempfield2;
	}

	public String getUpsmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setUpsmTempfield3(String upsmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = upsmTempfield3;
	}

	public String getUpsmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setUpsmTempfield4(String upsmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = upsmTempfield4;
	}

	public String getUpsmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setUpsmTempfield5(String upsmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = upsmTempfield5;
	}

	public String getUpsmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setUpsmActive(String upsmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = upsmActive;
	}

	public String getUpsmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setUpsmCreatedby(String upsmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = upsmCreatedby;
	}

	public String getUpsmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setUpsmCreatedon(String upsmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = upsmCreatedon;
	}

	public String getUpsmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setUpsmModifiedon(String upsmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = upsmModifiedon;
	}
	
	
	public GenTlUpstreamdefectDet getUpstreamdefect() {
		return Upstreamdefect;
	}

	public void setUpstreamdefect(GenTlUpstreamdefectDet upstreamdefect) {
		Upstreamdefect = upstreamdefect;
	}

	
	
	 // ===== UTILITY METHODS =====

    public Object getValue(tableFldConstants field) {
        return saveArray[field.ordinal()];
    }

    public void setValue(tableFldConstants field, Object value) {
        saveArray[field.ordinal()] = value;
    }

    // ===== JSON METHODS =====

    /**
     * Convert GenTlUpstreamdefectMst object to JSON string manually
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
                    sb.append("\"\"");
                } else {
                    sb.append("\"").append(val.toString().replace("\"", "\\\"")).append("\"");
                }
                first = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * Parse JSON string to GenTlUpstreamdefectMst object
     */
    public static GenTlUpstreamdefectMst fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response (Master): [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        GenTlUpstreamdefectMst mst = new GenTlUpstreamdefectMst();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.opt(key));
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            mst.setValue(field, val);
        }	

        return mst;
    }

    /**
     * Parse JSON array string to List of GenTlUpstreamdefectMst objects
     */
    public static List<GenTlUpstreamdefectMst> fromJsonList(String json) {
        CommonMessage.debugMsg("RAW JSON ARRAY Response (Master): " + json);

        JSONArray jsonArray = JSONArray.fromObject(json);
        List<GenTlUpstreamdefectMst> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            GenTlUpstreamdefectMst mst = new GenTlUpstreamdefectMst();

            for (tableFldConstants field : tableFldConstants.values()) {
                String key = field.name();
                Object valueObj = obj.opt(key);
                String val = valueObj.toString();
                if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
                
//                String val = (valueObj == null || "null".equals(valueObj.toString())) 
//                    ? null : valueObj.toString();
                
               
                mst.setValue(field, val);
            }

            list.add(mst);
        }

        return list;
    }

    /**
     * Convert List of GenTlUpstreamdefectMst objects to JSON array string
     */
    public static String toJsonManualList(List<GenTlUpstreamdefectMst> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }

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

