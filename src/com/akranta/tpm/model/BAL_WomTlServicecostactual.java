package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_WomTlServicecostactual {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		woid, doctype, serviceid, billno, billvalue, billdateflag, billdate
		, jobdescription, remarks, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, createdon, modifiedon
	}

	public BAL_WomTlServicecostactual()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSvcaWoid() {
		return (String) saveArray[ tableFldConstants.woid.ordinal() ];
	}

	public void setSvcaWoid(String svcaWoid) {
		saveArray[ tableFldConstants.woid.ordinal() ] = svcaWoid;
	}

	public String getSvcaDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setSvcaDoctype(String svcaDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = svcaDoctype;
	}

	public String getSvcaServiceid() {
		return (String) saveArray[ tableFldConstants.serviceid.ordinal() ];
	}

	public void setSvcaServiceid(String svcaServiceid) {
		saveArray[ tableFldConstants.serviceid.ordinal() ] = svcaServiceid;
	}

	public String getSvcaBillno() {
		return (String) saveArray[ tableFldConstants.billno.ordinal() ];
	}

	public void setSvcaBillno(String svcaBillno) {
		saveArray[ tableFldConstants.billno.ordinal() ] = svcaBillno;
	}

	public String getSvcaBillvalue() {
		return (String) saveArray[ tableFldConstants.billvalue.ordinal() ];
	}

	public void setSvcaBillvalue(String svcaBillvalue) {
		saveArray[ tableFldConstants.billvalue.ordinal() ] = svcaBillvalue;
	}

	public String getSvcaBilldateflag() {
		return (String) saveArray[ tableFldConstants.billdateflag.ordinal() ];
	}

	public void setSvcaBilldateflag(String svcaBilldateflag) {
		saveArray[ tableFldConstants.billdateflag.ordinal() ] = svcaBilldateflag;
	}

	public String getSvcaBilldate() {
		return (String) saveArray[ tableFldConstants.billdate.ordinal() ];
	}

	public void setSvcaBilldate(String svcaBilldate) {
		saveArray[ tableFldConstants.billdate.ordinal() ] = svcaBilldate;
	}

	public String getSvcaJobdescription() {
		return (String) saveArray[ tableFldConstants.jobdescription.ordinal() ];
	}

	public void setSvcaJobdescription(String svcaJobdescription) {
		saveArray[ tableFldConstants.jobdescription.ordinal() ] = svcaJobdescription;
	}

	public String getSvcaRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSvcaRemarks(String svcaRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = svcaRemarks;
	}

	public String getSvcaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSvcaTempfield1(String svcaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = svcaTempfield1;
	}

	public String getSvcaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSvcaTempfield2(String svcaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = svcaTempfield2;
	}

	public String getSvcaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSvcaTempfield3(String svcaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = svcaTempfield3;
	}

	public String getSvcaTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSvcaTempfield4(String svcaTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = svcaTempfield4;
	}

	public String getSvcaTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSvcaTempfield5(String svcaTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = svcaTempfield5;
	}

	public String getSvcaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSvcaCreatedby(String svcaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = svcaCreatedby;
	}

	public String getSvcaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSvcaCreatedon(String svcaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = svcaCreatedon;
	}

	public String getSvcaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSvcaModifiedon(String svcaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = svcaModifiedon;
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
     * /api/bdm/service-cost-actual/save) back into a
     * BAL_WomTlServicecostactual instance.
     */
    public static BAL_WomTlServicecostactual fromJson(String json) {
        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
        BAL_WomTlServicecostactual svca = new BAL_WomTlServicecostactual();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            svca.saveArray[field.ordinal()] = val;
        }
        return svca;
    }

}

