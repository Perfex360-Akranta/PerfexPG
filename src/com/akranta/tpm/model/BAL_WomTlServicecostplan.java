package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlServicecostplan {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		woid, doctype, serviceid, billno, billvalue, billdateflag, billdate
		, jobdescription, remarks, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, createdon, modifiedon
	}

	public BAL_WomTlServicecostplan()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSvcpWoid() {
		return (String) saveArray[ tableFldConstants.woid.ordinal() ];
	}

	public void setSvcpWoid(String svcpWoid) {
		saveArray[ tableFldConstants.woid.ordinal() ] = svcpWoid;
	}

	public String getSvcpDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setSvcpDoctype(String svcpDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = svcpDoctype;
	}

	public String getSvcpServiceid() {
		return (String) saveArray[ tableFldConstants.serviceid.ordinal() ];
	}

	public void setSvcpServiceid(String svcpServiceid) {
		saveArray[ tableFldConstants.serviceid.ordinal() ] = svcpServiceid;
	}

	public String getSvcpBillno() {
		return (String) saveArray[ tableFldConstants.billno.ordinal() ];
	}

	public void setSvcpBillno(String svcpBillno) {
		saveArray[ tableFldConstants.billno.ordinal() ] = svcpBillno;
	}

	public String getSvcpBillvalue() {
		return (String) saveArray[ tableFldConstants.billvalue.ordinal() ];
	}

	public void setSvcpBillvalue(String svcpBillvalue) {
		saveArray[ tableFldConstants.billvalue.ordinal() ] = svcpBillvalue;
	}

	public String getSvcpBilldateflag() {
		return (String) saveArray[ tableFldConstants.billdateflag.ordinal() ];
	}

	public void setSvcpBilldateflag(String svcpBilldateflag) {
		saveArray[ tableFldConstants.billdateflag.ordinal() ] = svcpBilldateflag;
	}

	public String getSvcpBilldate() {
		return (String) saveArray[ tableFldConstants.billdate.ordinal() ];
	}

	public void setSvcpBilldate(String svcpBilldate) {
		saveArray[ tableFldConstants.billdate.ordinal() ] = svcpBilldate;
	}

	public String getSvcpJobdescription() {
		return (String) saveArray[ tableFldConstants.jobdescription.ordinal() ];
	}

	public void setSvcpJobdescription(String svcpJobdescription) {
		saveArray[ tableFldConstants.jobdescription.ordinal() ] = svcpJobdescription;
	}

	public String getSvcpRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSvcpRemarks(String svcpRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = svcpRemarks;
	}

	public String getSvcpTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSvcpTempfield1(String svcpTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = svcpTempfield1;
	}

	public String getSvcpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSvcpTempfield2(String svcpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = svcpTempfield2;
	}

	public String getSvcpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSvcpTempfield3(String svcpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = svcpTempfield3;
	}

	public String getSvcpTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSvcpTempfield4(String svcpTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = svcpTempfield4;
	}

	public String getSvcpTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSvcpTempfield5(String svcpTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = svcpTempfield5;
	}

	public String getSvcpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSvcpCreatedby(String svcpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = svcpCreatedby;
	}

	public String getSvcpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSvcpCreatedon(String svcpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = svcpCreatedon;
	}

	public String getSvcpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSvcpModifiedon(String svcpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = svcpModifiedon;
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
     * /api/bdm/service-cost/save) back into a BAL_WomTlServicecostplan
     * instance.
     */
    public static BAL_WomTlServicecostplan fromJson(String json) {
        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
        BAL_WomTlServicecostplan svcp = new BAL_WomTlServicecostplan();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            svcp.saveArray[field.ordinal()] = val;
        }
        return svcp;
    }
}

