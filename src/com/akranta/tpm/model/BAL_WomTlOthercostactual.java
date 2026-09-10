package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlOthercostactual {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		woid, doctype, othercostmstid, requestedby, amount, remarks, date
		, tempfield1, tempfield2, tempfield3, tempfield4, createdby, createdon
		, modifiedon
	}

	public BAL_WomTlOthercostactual()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOtcdWoid() {
		return (String) saveArray[ tableFldConstants.woid.ordinal() ];
	}

	public void setOtcdWoid(String otcdWoid) {
		saveArray[ tableFldConstants.woid.ordinal() ] = otcdWoid;
	}

	public String getOtcdDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setOtcdDoctype(String otcdDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = otcdDoctype;
	}

	public String getOtcdOthercostmstid() {
		return (String) saveArray[ tableFldConstants.othercostmstid.ordinal() ];
	}

	public void setOtcdOthercostmstid(String otcdOthercostmstid) {
		saveArray[ tableFldConstants.othercostmstid.ordinal() ] = otcdOthercostmstid;
	}

	public String getOtcdRequestedby() {
		return (String) saveArray[ tableFldConstants.requestedby.ordinal() ];
	}

	public void setOtcdRequestedby(String otcdRequestedby) {
		saveArray[ tableFldConstants.requestedby.ordinal() ] = otcdRequestedby;
	}

	public String getOtcdAmount() {
		return (String) saveArray[ tableFldConstants.amount.ordinal() ];
	}

	public void setOtcdAmount(String otcdAmount) {
		saveArray[ tableFldConstants.amount.ordinal() ] = otcdAmount;
	}

	public String getOtcdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setOtcdRemarks(String otcdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = otcdRemarks;
	}

	public String getOtcdDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setOtcdDate(String otcdDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = otcdDate;
	}

	public String getOtcdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setOtcdTempfield1(String otcdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = otcdTempfield1;
	}

	public String getOtcdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setOtcdTempfield2(String otcdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = otcdTempfield2;
	}

	public String getOtcdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setOtcdTempfield3(String otcdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = otcdTempfield3;
	}

	public String getOtcdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOtcdTempfield4(String otcdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = otcdTempfield4;
	}

	public String getOtcdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOtcdCreatedby(String otcdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = otcdCreatedby;
	}

	public String getOtcdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOtcdCreatedon(String otcdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = otcdCreatedon;
	}

	public String getOtcdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOtcdModifiedon(String otcdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = otcdModifiedon;
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

    public static BAL_WomTlOthercostactual fromJson(String json) {
        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
        BAL_WomTlOthercostactual otcd = new BAL_WomTlOthercostactual();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            otcd.saveArray[field.ordinal()] = val;
        }
        return otcd;
    }

}

