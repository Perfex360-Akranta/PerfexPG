package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlManpowercostactual {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		maintwoid, doctype, manpowerid, normalwt, holidaywt, otherwt
		, normalrate, holidayrate, otherrate, totalvalue, noofhelpers
		, skillflag, skillid, date, activity, remarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, createdon, modifiedon
	}

	public BAL_WomTlManpowercostactual()
	{
		saveArray = new  Object [ 24 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMpcsMaintwoid() {
		return (String) saveArray[ tableFldConstants.maintwoid.ordinal() ];
	}

	public void setMpcsMaintwoid(String mpcsMaintwoid) {
		saveArray[ tableFldConstants.maintwoid.ordinal() ] = mpcsMaintwoid;
	}

	public String getMpcsDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setMpcsDoctype(String mpcsDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = mpcsDoctype;
	}

	public String getMpcsManpowerid() {
		return (String) saveArray[ tableFldConstants.manpowerid.ordinal() ];
	}

	public void setMpcsManpowerid(String mpcsManpowerid) {
		saveArray[ tableFldConstants.manpowerid.ordinal() ] = mpcsManpowerid;
	}

	public String getMpcsNormalwt() {
		return (String) saveArray[ tableFldConstants.normalwt.ordinal() ];
	}

	public void setMpcsNormalwt(String mpcsNormalwt) {
		saveArray[ tableFldConstants.normalwt.ordinal() ] = mpcsNormalwt;
	}

	public String getMpcsHolidaywt() {
		return (String) saveArray[ tableFldConstants.holidaywt.ordinal() ];
	}

	public void setMpcsHolidaywt(String mpcsHolidaywt) {
		saveArray[ tableFldConstants.holidaywt.ordinal() ] = mpcsHolidaywt;
	}

	public String getMpcsOtherwt() {
		return (String) saveArray[ tableFldConstants.otherwt.ordinal() ];
	}

	public void setMpcsOtherwt(String mpcsOtherwt) {
		saveArray[ tableFldConstants.otherwt.ordinal() ] = mpcsOtherwt;
	}

	public String getMpcsNormalrate() {
		return (String) saveArray[ tableFldConstants.normalrate.ordinal() ];
	}

	public void setMpcsNormalrate(String mpcsNormalrate) {
		saveArray[ tableFldConstants.normalrate.ordinal() ] = mpcsNormalrate;
	}

	public String getMpcsHolidayrate() {
		return (String) saveArray[ tableFldConstants.holidayrate.ordinal() ];
	}

	public void setMpcsHolidayrate(String mpcsHolidayrate) {
		saveArray[ tableFldConstants.holidayrate.ordinal() ] = mpcsHolidayrate;
	}

	public String getMpcsOtherrate() {
		return (String) saveArray[ tableFldConstants.otherrate.ordinal() ];
	}

	public void setMpcsOtherrate(String mpcsOtherrate) {
		saveArray[ tableFldConstants.otherrate.ordinal() ] = mpcsOtherrate;
	}

	public String getMpcsTotalvalue() {
		return (String) saveArray[ tableFldConstants.totalvalue.ordinal() ];
	}

	public void setMpcsTotalvalue(String mpcsTotalvalue) {
		saveArray[ tableFldConstants.totalvalue.ordinal() ] = mpcsTotalvalue;
	}

	public String getMpcsNoofhelpers() {
		return (String) saveArray[ tableFldConstants.noofhelpers.ordinal() ];
	}

	public void setMpcsNoofhelpers(String mpcsNoofhelpers) {
		saveArray[ tableFldConstants.noofhelpers.ordinal() ] = mpcsNoofhelpers;
	}

	public String getMpcsSkillflag() {
		return (String) saveArray[ tableFldConstants.skillflag.ordinal() ];
	}

	public void setMpcsSkillflag(String mpcsSkillflag) {
		saveArray[ tableFldConstants.skillflag.ordinal() ] = mpcsSkillflag;
	}

	public String getMpcsSkillid() {
		return (String) saveArray[ tableFldConstants.skillid.ordinal() ];
	}

	public void setMpcsSkillid(String mpcsSkillid) {
		saveArray[ tableFldConstants.skillid.ordinal() ] = mpcsSkillid;
	}

	public String getMpcsDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setMpcsDate(String mpcsDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = mpcsDate;
	}

	public String getMpcsActivity() {
		return (String) saveArray[ tableFldConstants.activity.ordinal() ];
	}

	public void setMpcsActivity(String mpcsActivity) {
		saveArray[ tableFldConstants.activity.ordinal() ] = mpcsActivity;
	}

	public String getMpcsRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMpcsRemarks(String mpcsRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = mpcsRemarks;
	}

	public String getMpcsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMpcsTempfield1(String mpcsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mpcsTempfield1;
	}

	public String getMpcsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMpcsTempfield2(String mpcsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mpcsTempfield2;
	}

	public String getMpcsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMpcsTempfield3(String mpcsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mpcsTempfield3;
	}

	public String getMpcsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMpcsTempfield4(String mpcsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mpcsTempfield4;
	}

	public String getMpcsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMpcsTempfield5(String mpcsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mpcsTempfield5;
	}

	public String getMpcsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMpcsCreatedby(String mpcsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mpcsCreatedby;
	}

	public String getMpcsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMpcsCreatedon(String mpcsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mpcsCreatedon;
	}

	public String getMpcsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMpcsModifiedon(String mpcsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mpcsModifiedon;
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
	                    sb.append("\"{}\"");
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
	     * /api/bdm/manpower-cost-actual/save) back into a
	     * BAL_WomTlManpowercostactual instance.
	     */
	    public static BAL_WomTlManpowercostactual fromJson(String json) {
	        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
	        BAL_WomTlManpowercostactual mpcs = new BAL_WomTlManpowercostactual();
	        for (tableFldConstants field : tableFldConstants.values()) {
	            String val = obj.optString(field.name(), null);
	            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
	            mpcs.saveArray[field.ordinal()] = val;
	        }
	        return mpcs;
	    }
}

