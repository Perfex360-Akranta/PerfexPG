package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlManpowercostplan {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		woid, doctype, manpowerid, normalmins, holidaymins, othermins
		, normalcost, holidaycost, othercost, totalvalue, noofhelpers
		, skillflag, skillid, date, activity, remarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public BAL_WomTlManpowercostplan()
	{
		saveArray = new  Object [ 25 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMpcpWoid() {
		return (String) saveArray[ tableFldConstants.woid.ordinal() ];
	}

	public void setMpcpWoid(String mpcpWoid) {
		saveArray[ tableFldConstants.woid.ordinal() ] = mpcpWoid;
	}

	public String getMpcpDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setMpcpDoctype(String mpcpDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = mpcpDoctype;
	}

	public String getMpcpManpowerid() {
		return (String) saveArray[ tableFldConstants.manpowerid.ordinal() ];
	}

	public void setMpcpManpowerid(String mpcpManpowerid) {
		saveArray[ tableFldConstants.manpowerid.ordinal() ] = mpcpManpowerid;
	}

	public String getMpcpNormalmins() {
		return (String) saveArray[ tableFldConstants.normalmins.ordinal() ];
	}

	public void setMpcpNormalmins(String mpcpNormalmins) {
		saveArray[ tableFldConstants.normalmins.ordinal() ] = mpcpNormalmins;
	}

	public String getMpcpHolidaymins() {
		return (String) saveArray[ tableFldConstants.holidaymins.ordinal() ];
	}

	public void setMpcpHolidaymins(String mpcpHolidaymins) {
		saveArray[ tableFldConstants.holidaymins.ordinal() ] = mpcpHolidaymins;
	}

	public String getMpcpOthermins() {
		return (String) saveArray[ tableFldConstants.othermins.ordinal() ];
	}

	public void setMpcpOthermins(String mpcpOthermins) {
		saveArray[ tableFldConstants.othermins.ordinal() ] = mpcpOthermins;
	}

	public String getMpcpNormalcost() {
		return (String) saveArray[ tableFldConstants.normalcost.ordinal() ];
	}

	public void setMpcpNormalcost(String mpcpNormalcost) {
		saveArray[ tableFldConstants.normalcost.ordinal() ] = mpcpNormalcost;
	}

	public String getMpcpHolidaycost() {
		return (String) saveArray[ tableFldConstants.holidaycost.ordinal() ];
	}

	public void setMpcpHolidaycost(String mpcpHolidaycost) {
		saveArray[ tableFldConstants.holidaycost.ordinal() ] = mpcpHolidaycost;
	}

	public String getMpcpOthercost() {
		return (String) saveArray[ tableFldConstants.othercost.ordinal() ];
	}

	public void setMpcpOthercost(String mpcpOthercost) {
		saveArray[ tableFldConstants.othercost.ordinal() ] = mpcpOthercost;
	}

	public String getMpcpTotalvalue() {
		return (String) saveArray[ tableFldConstants.totalvalue.ordinal() ];
	}

	public void setMpcpTotalvalue(String mpcpTotalvalue) {
		saveArray[ tableFldConstants.totalvalue.ordinal() ] = mpcpTotalvalue;
	}

	public String getMpcpNoofhelpers() {
		return (String) saveArray[ tableFldConstants.noofhelpers.ordinal() ];
	}

	public void setMpcpNoofhelpers(String mpcpNoofhelpers) {
		saveArray[ tableFldConstants.noofhelpers.ordinal() ] = mpcpNoofhelpers;
	}

	public String getMpcpSkillflag() {
		return (String) saveArray[ tableFldConstants.skillflag.ordinal() ];
	}

	public void setMpcpSkillflag(String mpcpSkillflag) {
		saveArray[ tableFldConstants.skillflag.ordinal() ] = mpcpSkillflag;
	}

	public String getMpcpSkillid() {
		return (String) saveArray[ tableFldConstants.skillid.ordinal() ];
	}

	public void setMpcpSkillid(String mpcpSkillid) {
		saveArray[ tableFldConstants.skillid.ordinal() ] = mpcpSkillid;
	}

	public String getMpcpDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setMpcpDate(String mpcpDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = mpcpDate;
	}

	public String getMpcpActivity() {
		return (String) saveArray[ tableFldConstants.activity.ordinal() ];
	}

	public void setMpcpActivity(String mpcpActivity) {
		saveArray[ tableFldConstants.activity.ordinal() ] = mpcpActivity;
	}

	public String getMpcpRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMpcpRemarks(String mpcpRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = mpcpRemarks;
	}

	public String getMpcpTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMpcpTempfield1(String mpcpTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mpcpTempfield1;
	}

	public String getMpcpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMpcpTempfield2(String mpcpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mpcpTempfield2;
	}

	public String getMpcpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMpcpTempfield3(String mpcpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mpcpTempfield3;
	}

	public String getMpcpTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMpcpTempfield4(String mpcpTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mpcpTempfield4;
	}

	public String getMpcpTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMpcpTempfield5(String mpcpTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mpcpTempfield5;
	}

	public String getMpcpActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMpcpActive(String mpcpActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mpcpActive;
	}

	public String getMpcpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMpcpCreatedby(String mpcpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mpcpCreatedby;
	}

	public String getMpcpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMpcpCreatedon(String mpcpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mpcpCreatedon;
	}

	public String getMpcpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMpcpModifiedon(String mpcpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mpcpModifiedon;
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
	                    sb.append("\"{}\"");        // empty placeholder, matches legacy nulls
	                } else {
	                    sb.append("\"")
	                      .append(val.toString().replace("\\", "\\\\")
	                                            .replace("\"", "\\\""))
	                      .append("\"");
	                }
	                first = false;
	            }
	        }
	        sb.append("}");
	        return sb.toString();
	    }

	    /**
	     * Deserialises a JSON string (Spring response body for
	     * /api/bdm/manpower-cost/save) back into a BAL_WomTlManpowercostplan
	     * instance.
	     */
	    public static BAL_WomTlManpowercostplan fromJson(String json) {
	        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
	        BAL_WomTlManpowercostplan mpcp = new BAL_WomTlManpowercostplan();
	        for (tableFldConstants field : tableFldConstants.values()) {
	            String val = obj.optString(field.name(), null);
	            // Treat JSON null / literal "null" / "{}" placeholder as empty string
	            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
	            mpcp.saveArray[field.ordinal()] = val;
	        }
	        return mpcp;
	    }

}

