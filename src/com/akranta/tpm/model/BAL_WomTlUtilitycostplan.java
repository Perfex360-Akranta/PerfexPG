package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlUtilitycostplan {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		wokeyid, doctype, utilitymstid, requestedby, quantity, minutes
		, cost, totalvalue, remarks, date, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, createdon, modifiedon
	}

	public BAL_WomTlUtilitycostplan()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getUtcpWokeyid() {
		return (String) saveArray[ tableFldConstants.wokeyid.ordinal() ];
	}

	public void setUtcpWokeyid(String utcpWokeyid) {
		saveArray[ tableFldConstants.wokeyid.ordinal() ] = utcpWokeyid;
	}

	public String getUtcpDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setUtcpDoctype(String utcpDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = utcpDoctype;
	}

	public String getUtcpUtilitymstid() {
		return (String) saveArray[ tableFldConstants.utilitymstid.ordinal() ];
	}

	public void setUtcpUtilitymstid(String utcpUtilitymstid) {
		saveArray[ tableFldConstants.utilitymstid.ordinal() ] = utcpUtilitymstid;
	}

	public String getUtcpRequestedby() {
		return (String) saveArray[ tableFldConstants.requestedby.ordinal() ];
	}

	public void setUtcpRequestedby(String utcpRequestedby) {
		saveArray[ tableFldConstants.requestedby.ordinal() ] = utcpRequestedby;
	}

	public String getUtcpQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setUtcpQuantity(String utcpQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = utcpQuantity;
	}

	public String getUtcpMinutes() {
		return (String) saveArray[ tableFldConstants.minutes.ordinal() ];
	}

	public void setUtcpMinutes(String utcpMinutes) {
		saveArray[ tableFldConstants.minutes.ordinal() ] = utcpMinutes;
	}

	public String getUtcpCost() {
		return (String) saveArray[ tableFldConstants.cost.ordinal() ];
	}

	public void setUtcpCost(String utcpCost) {
		saveArray[ tableFldConstants.cost.ordinal() ] = utcpCost;
	}

	public String getUtcpTotalvalue() {
		return (String) saveArray[ tableFldConstants.totalvalue.ordinal() ];
	}

	public void setUtcpTotalvalue(String utcpTotalvalue) {
		saveArray[ tableFldConstants.totalvalue.ordinal() ] = utcpTotalvalue;
	}

	public String getUtcpRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setUtcpRemarks(String utcpRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = utcpRemarks;
	}

	public String getUtcpDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setUtcpDate(String utcpDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = utcpDate;
	}

	public String getUtcpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setUtcpTempfield2(String utcpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = utcpTempfield2;
	}

	public String getUtcpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setUtcpTempfield3(String utcpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = utcpTempfield3;
	}

	public String getUtcpTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setUtcpTempfield4(String utcpTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = utcpTempfield4;
	}

	public String getUtcpTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setUtcpTempfield5(String utcpTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = utcpTempfield5;
	}

	public String getUtcpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setUtcpCreatedby(String utcpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = utcpCreatedby;
	}

	public String getUtcpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setUtcpCreatedon(String utcpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = utcpCreatedon;
	}

	public String getUtcpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setUtcpModifiedon(String utcpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = utcpModifiedon;
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
     * /api/bdm/utility-cost/save) back into a BAL_WomTlUtilitycostplan
     * instance.
     */
    public static BAL_WomTlUtilitycostplan fromJson(String json) {
        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
        BAL_WomTlUtilitycostplan utcp = new BAL_WomTlUtilitycostplan();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            utcp.saveArray[field.ordinal()] = val;
        }
        return utcp;
    }
}

