package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlSparecostplan {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		woid, doctype, sparesid, quantity, rate, value, refdocno, requestedby
		, date, tempfield1, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public BAL_WomTlSparecostplan()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWscpWoid() {
		return (String) saveArray[ tableFldConstants.woid.ordinal() ];
	}

	public void setWscpWoid(String wscpWoid) {
		saveArray[ tableFldConstants.woid.ordinal() ] = wscpWoid;
	}

	public String getWscpDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setWscpDoctype(String wscpDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = wscpDoctype;
	}

	public String getWscpSparesid() {
		return (String) saveArray[ tableFldConstants.sparesid.ordinal() ];
	}

	public void setWscpSparesid(String wscpSparesid) {
		saveArray[ tableFldConstants.sparesid.ordinal() ] = wscpSparesid;
	}

	public String getWscpQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setWscpQuantity(String wscpQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = wscpQuantity;
	}

	public String getWscpRate() {
		return (String) saveArray[ tableFldConstants.rate.ordinal() ];
	}

	public void setWscpRate(String wscpRate) {
		saveArray[ tableFldConstants.rate.ordinal() ] = wscpRate;
	}

	public String getWscpValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setWscpValue(String wscpValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = wscpValue;
	}

	public String getWscpRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setWscpRefdocno(String wscpRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = wscpRefdocno;
	}

	public String getWscpRequestedby() {
		return (String) saveArray[ tableFldConstants.requestedby.ordinal() ];
	}

	public void setWscpRequestedby(String wscpRequestedby) {
		saveArray[ tableFldConstants.requestedby.ordinal() ] = wscpRequestedby;
	}

	public String getWscpDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setWscpDate(String wscpDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = wscpDate;
	}

	public String getWscpTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWscpTempfield1(String wscpTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wscpTempfield1;
	}

	public String getWscpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWscpTempfield2(String wscpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wscpTempfield2;
	}

	public String getWscpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWscpTempfield3(String wscpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wscpTempfield3;
	}

	public String getWscpActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWscpActive(String wscpActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wscpActive;
	}

	public String getWscpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWscpCreatedby(String wscpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wscpCreatedby;
	}

	public String getWscpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWscpCreatedon(String wscpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wscpCreatedon;
	}

	public String getWscpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWscpModifiedon(String wscpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wscpModifiedon;
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
     * /api/bdm/spare-cost/save) back into a BAL_WomTlSparecostplan instance.
     */
    public static BAL_WomTlSparecostplan fromJson(String json) {
        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
        BAL_WomTlSparecostplan wscp = new BAL_WomTlSparecostplan();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            wscp.saveArray[field.ordinal()] = val;
        }
        return wscp;
    }

}

