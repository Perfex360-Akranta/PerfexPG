package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlUtilitycostactual {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		wokeyid, doctype, utilitymstid, requestedby, quantity, minutes
		, cost, totalvalue, remarks, date, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, createdon, modifiedon
	}

	public BAL_WomTlUtilitycostactual()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getUtcaWokeyid() {
		return (String) saveArray[ tableFldConstants.wokeyid.ordinal() ];
	}

	public void setUtcaWokeyid(String utcaWokeyid) {
		saveArray[ tableFldConstants.wokeyid.ordinal() ] = utcaWokeyid;
	}

	public String getUtcaDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setUtcaDoctype(String utcaDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = utcaDoctype;
	}

	public String getUtcaUtilitymstid() {
		return (String) saveArray[ tableFldConstants.utilitymstid.ordinal() ];
	}

	public void setUtcaUtilitymstid(String utcaUtilitymstid) {
		saveArray[ tableFldConstants.utilitymstid.ordinal() ] = utcaUtilitymstid;
	}

	public String getUtcaRequestedby() {
		return (String) saveArray[ tableFldConstants.requestedby.ordinal() ];
	}

	public void setUtcaRequestedby(String utcaRequestedby) {
		saveArray[ tableFldConstants.requestedby.ordinal() ] = utcaRequestedby;
	}

	public String getUtcaQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setUtcaQuantity(String utcaQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = utcaQuantity;
	}

	public String getUtcaMinutes() {
		return (String) saveArray[ tableFldConstants.minutes.ordinal() ];
	}

	public void setUtcaMinutes(String utcaMinutes) {
		saveArray[ tableFldConstants.minutes.ordinal() ] = utcaMinutes;
	}

	public String getUtcaCost() {
		return (String) saveArray[ tableFldConstants.cost.ordinal() ];
	}

	public void setUtcaCost(String utcaCost) {
		saveArray[ tableFldConstants.cost.ordinal() ] = utcaCost;
	}

	public String getUtcaTotalvalue() {
		return (String) saveArray[ tableFldConstants.totalvalue.ordinal() ];
	}

	public void setUtcaTotalvalue(String utcaTotalvalue) {
		saveArray[ tableFldConstants.totalvalue.ordinal() ] = utcaTotalvalue;
	}

	public String getUtcaRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setUtcaRemarks(String utcaRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = utcaRemarks;
	}

	public String getUtcaDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setUtcaDate(String utcaDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = utcaDate;
	}

	public String getUtcaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setUtcaTempfield2(String utcaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = utcaTempfield2;
	}

	public String getUtcaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setUtcaTempfield3(String utcaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = utcaTempfield3;
	}

	public String getUtcaTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setUtcaTempfield4(String utcaTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = utcaTempfield4;
	}

	public String getUtcaTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setUtcaTempfield5(String utcaTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = utcaTempfield5;
	}

	public String getUtcaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setUtcaCreatedby(String utcaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = utcaCreatedby;
	}

	public String getUtcaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setUtcaCreatedon(String utcaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = utcaCreatedon;
	}

	public String getUtcaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setUtcaModifiedon(String utcaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = utcaModifiedon;
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

    public static BAL_WomTlUtilitycostactual fromJson(String json) {
        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
        BAL_WomTlUtilitycostactual utca = new BAL_WomTlUtilitycostactual();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            utca.saveArray[field.ordinal()] = val;
        }
        return utca;
    }
}

