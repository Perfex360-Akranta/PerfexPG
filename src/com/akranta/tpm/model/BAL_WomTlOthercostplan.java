package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlOthercostplan {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		woid, doctype, othercostmstid, requestedby, amount, remarks, date
		, tempfield2, tempfield3, tempfield4, tempfield5, createdby, createdon
		, modifiedon
	}

	public BAL_WomTlOthercostplan()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOtcpWoid() {
		return (String) saveArray[ tableFldConstants.woid.ordinal() ];
	}

	public void setOtcpWoid(String otcpWoid) {
		saveArray[ tableFldConstants.woid.ordinal() ] = otcpWoid;
	}

	public String getOtcpDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setOtcpDoctype(String otcpDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = otcpDoctype;
	}

	public String getOtcpOthercostmstid() {
		return (String) saveArray[ tableFldConstants.othercostmstid.ordinal() ];
	}

	public void setOtcpOthercostmstid(String otcpOthercostmstid) {
		saveArray[ tableFldConstants.othercostmstid.ordinal() ] = otcpOthercostmstid;
	}

	public String getOtcpRequestedby() {
		return (String) saveArray[ tableFldConstants.requestedby.ordinal() ];
	}

	public void setOtcpRequestedby(String otcpRequestedby) {
		saveArray[ tableFldConstants.requestedby.ordinal() ] = otcpRequestedby;
	}

	public String getOtcpAmount() {
		return (String) saveArray[ tableFldConstants.amount.ordinal() ];
	}

	public void setOtcpAmount(String otcpAmount) {
		saveArray[ tableFldConstants.amount.ordinal() ] = otcpAmount;
	}

	public String getOtcpRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setOtcpRemarks(String otcpRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = otcpRemarks;
	}

	public String getOtcpDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setOtcpDate(String otcpDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = otcpDate;
	}

	public String getOtcpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setOtcpTempfield2(String otcpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = otcpTempfield2;
	}

	public String getOtcpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setOtcpTempfield3(String otcpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = otcpTempfield3;
	}

	public String getOtcpTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOtcpTempfield4(String otcpTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = otcpTempfield4;
	}

	public String getOtcpTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setOtcpTempfield5(String otcpTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = otcpTempfield5;
	}

	public String getOtcpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOtcpCreatedby(String otcpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = otcpCreatedby;
	}

	public String getOtcpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOtcpCreatedon(String otcpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = otcpCreatedon;
	}

	public String getOtcpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOtcpModifiedon(String otcpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = otcpModifiedon;
	}
    // =========================================================================
    //  BUILD REQUEST JSON  –  same style as the other cost models
    // =========================================================================
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
     * /api/bdm/other-cost/save) back into a BAL_WomTlOthercostplan instance.
     */
    public static BAL_WomTlOthercostplan fromJson(String json) {
        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
        BAL_WomTlOthercostplan otcp = new BAL_WomTlOthercostplan();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            otcp.saveArray[field.ordinal()] = val;
        }
        return otcp;
    }
}

