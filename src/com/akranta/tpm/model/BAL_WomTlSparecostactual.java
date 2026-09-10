package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlSparecostactual {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		woid, sitreference, sparesid, quantity, rate, value, refdocno
		, doctype, requestedby, date, tempfield1, tempfield2, createdby
		, createdon, modifiedon
	}

	public BAL_WomTlSparecostactual()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWscaWoid() {
		return (String) saveArray[ tableFldConstants.woid.ordinal() ];
	}

	public void setWscaWoid(String wscaWoid) {
		saveArray[ tableFldConstants.woid.ordinal() ] = wscaWoid;
	}

	public String getWscaSitreference() {
		return (String) saveArray[ tableFldConstants.sitreference.ordinal() ];
	}

	public void setWscaSitreference(String wscaSitreference) {
		saveArray[ tableFldConstants.sitreference.ordinal() ] = wscaSitreference;
	}

	public String getWscaSparesid() {
		return (String) saveArray[ tableFldConstants.sparesid.ordinal() ];
	}

	public void setWscaSparesid(String wscaSparesid) {
		saveArray[ tableFldConstants.sparesid.ordinal() ] = wscaSparesid;
	}

	public String getWscaQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setWscaQuantity(String wscaQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = wscaQuantity;
	}

	public String getWscaRate() {
		return (String) saveArray[ tableFldConstants.rate.ordinal() ];
	}

	public void setWscaRate(String wscaRate) {
		saveArray[ tableFldConstants.rate.ordinal() ] = wscaRate;
	}

	public String getWscaValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setWscaValue(String wscaValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = wscaValue;
	}

	public String getWscaRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setWscaRefdocno(String wscaRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = wscaRefdocno;
	}

	public String getWscaDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setWscaDoctype(String wscaDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = wscaDoctype;
	}

	public String getWscaRequestedby() {
		return (String) saveArray[ tableFldConstants.requestedby.ordinal() ];
	}

	public void setWscaRequestedby(String wscaRequestedby) {
		saveArray[ tableFldConstants.requestedby.ordinal() ] = wscaRequestedby;
	}

	public String getWscaDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setWscaDate(String wscaDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = wscaDate;
	}

	public String getWscaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWscaTempfield1(String wscaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wscaTempfield1;
	}

	public String getWscaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWscaTempfield2(String wscaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wscaTempfield2;
	}

	public String getWscaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWscaCreatedby(String wscaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wscaCreatedby;
	}

	public String getWscaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWscaCreatedon(String wscaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wscaCreatedon;
	}

	public String getWscaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWscaModifiedon(String wscaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wscaModifiedon;
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
     * /api/bdm/spare-cost-actual/save) back into a BAL_WomTlSparecostactual
     * instance.
     */
    public static BAL_WomTlSparecostactual fromJson(String json) {
        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
        BAL_WomTlSparecostactual wsca = new BAL_WomTlSparecostactual();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            wsca.saveArray[field.ordinal()] = val;
        }
        return wsca;
    }

}

