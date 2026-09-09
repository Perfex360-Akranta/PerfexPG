package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.JhaTlAuditdtl.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class GenTlUpstreamdefectDet {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, upsm_keyid, informto, rawmaterial, defect, correctionaction
		, preventiveaction, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public GenTlUpstreamdefectDet()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getUpsdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setUpsdKeyid(String upsdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = upsdKeyid;
	}

	public String getUpsdUpsmKeyid() {
		return (String) saveArray[ tableFldConstants.upsm_keyid.ordinal() ];
	}

	public void setUpsdUpsmKeyid(String upsdUpsmKeyid) {
		saveArray[ tableFldConstants.upsm_keyid.ordinal() ] = upsdUpsmKeyid;
	}

	public String getUpsdInformto() {
		return (String) saveArray[ tableFldConstants.informto.ordinal() ];
	}

	public void setUpsdInformto(String upsdInformto) {
		saveArray[ tableFldConstants.informto.ordinal() ] = upsdInformto;
	}

	public String getUpsdRawmaterial() {
		return (String) saveArray[ tableFldConstants.rawmaterial.ordinal() ];
	}

	public void setUpsdRawmaterial(String upsdRawmaterial) {
		saveArray[ tableFldConstants.rawmaterial.ordinal() ] = upsdRawmaterial;
	}

	public String getUpsdDefect() {
		return (String) saveArray[ tableFldConstants.defect.ordinal() ];
	}

	public void setUpsdDefect(String upsdDefect) {
		saveArray[ tableFldConstants.defect.ordinal() ] = upsdDefect;
	}

	public String getUpsdCorrectionaction() {
		return (String) saveArray[ tableFldConstants.correctionaction.ordinal() ];
	}

	public void setUpsdCorrectionaction(String upsdCorrectionaction) {
		saveArray[ tableFldConstants.correctionaction.ordinal() ] = upsdCorrectionaction;
	}

	public String getUpsdPreventiveaction() {
		return (String) saveArray[ tableFldConstants.preventiveaction.ordinal() ];
	}

	public void setUpsdPreventiveaction(String upsdPreventiveaction) {
		saveArray[ tableFldConstants.preventiveaction.ordinal() ] = upsdPreventiveaction;
	}

	public String getUpsdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setUpsdTempfield1(String upsdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = upsdTempfield1;
	}

	public String getUpsdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setUpsdTempfield2(String upsdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = upsdTempfield2;
	}

	public String getUpsdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setUpsdTempfield3(String upsdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = upsdTempfield3;
	}

	public String getUpsdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setUpsdTempfield4(String upsdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = upsdTempfield4;
	}

	public String getUpsdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setUpsdTempfield5(String upsdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = upsdTempfield5;
	}

	public String getUpsdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setUpsdActive(String upsdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = upsdActive;
	}

	public String getUpsdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setUpsdCreatedby(String upsdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = upsdCreatedby;
	}

	public String getUpsdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setUpsdCreatedon(String upsdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = upsdCreatedon;
	}

	public String getUpsdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setUpsdModifiedon(String upsdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = upsdModifiedon;
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
	     * Parse JSON string to GenTlUpstreamdefectDet object
	     * ✅ FIXED: Changed return type from GenTlUpstreamdefectMst to GenTlUpstreamdefectDet
	     */
	    public static GenTlUpstreamdefectDet fromJson(String json) {
	        CommonMessage.debugMsg("RAW JSON Response (Detail): [" + json + "]");

	        JSONObject obj = JSONObject.fromObject(json);
	        GenTlUpstreamdefectDet det = new GenTlUpstreamdefectDet();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();
	            String val = obj.optString(field.name(), null);
	            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
	            det.setValue(field, val);
	        }

	        return det;
	    }

	    /**
	     * Parse JSON array string to List of GenTlUpstreamdefectDet objects
	     * ✅ FIXED: Changed return type from List<GenTlUpstreamdefectMst> to List<GenTlUpstreamdefectDet>
	     */
	    public static List<GenTlUpstreamdefectDet> fromJsonList(String json) {
	        CommonMessage.debugMsg("RAW JSON ARRAY Response (Detail): " + json);

	        JSONArray jsonArray = JSONArray.fromObject(json);
	        List<GenTlUpstreamdefectDet> list = new ArrayList<>();

	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject obj = jsonArray.getJSONObject(i);
	            GenTlUpstreamdefectDet det = new GenTlUpstreamdefectDet();

	            for (tableFldConstants field : tableFldConstants.values()) {
	                String key = field.name();
	                Object valueObj = obj.opt(key);
	                String val = valueObj.toString();
	                if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
		                val = "";
		            }
					/*
					  String val = (valueObj == null || "null".equals(valueObj.toString())) ? null
					  : valueObj.toString();
					 */
	              
	                det.setValue(field, val);
	            }

	            list.add(det);
	        }

	        return list;
	    }

	    /**
	     * Convert list of GenTlUpstreamdefectDet objects to JSON array string
	     * ✅ FIXED: Changed parameter type from List<GenTlUpstreamdefectMst> to List<GenTlUpstreamdefectDet>
	     */
	    public static String toJsonManualList(List<GenTlUpstreamdefectDet> list) {
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

//	    @Override
//	    public String toString() {
//	        return "GenTlUpstreamdefectDet{" +
//	                "keyid=" + getUpsdKeyid() +
//	                ", upsmKeyid=" + getUpsdUpsmKeyid() +
//	                ", informto=" + getUpsdInformto() +
//	                ", rawmaterial=" + getUpsdRawmaterial() +
//	                ", defect=" + getUpsdDefect() +
//	                ", correctionaction=" + getUpsdCorrectionaction() +
//	                ", preventiveaction=" + getUpsdPreventiveaction() +
//	                '}';
//	    }

}

