package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlUpstreamdefectMst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class JhaTlVisualsopmst {

	private  Object [] saveArray = null;  
	private String elementid;	

	public enum   tableFldConstants
	{
		keyid, flnid, equipmentid, productid, operation, safetyinstruction
		, effectofnoncompliance, preparedby, approvedby, issuedby, status
		, nextlevel, maintsection, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public JhaTlVisualsopmst()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getVsomKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVsomKeyid(String vsomKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = vsomKeyid;
	}

	public String getVsomFlnid() {
		return (String) saveArray[ tableFldConstants.flnid.ordinal() ];
	}

	public void setVsomFlnid(String vsomFlnid) {
		saveArray[ tableFldConstants.flnid.ordinal() ] = vsomFlnid;
	}

	public String getVsomEquipmentid() {
		return (String) saveArray[ tableFldConstants.equipmentid.ordinal() ];
	}

	public void setVsomEquipmentid(String vsomEquipmentid) {
		saveArray[ tableFldConstants.equipmentid.ordinal() ] = vsomEquipmentid;
	}

	public String getVsomProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setVsomProductid(String vsomProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = vsomProductid;
	}

	public String getVsomOperation() {
		return (String) saveArray[ tableFldConstants.operation.ordinal() ];
	}

	public void setVsomOperation(String vsomOperation) {
		saveArray[ tableFldConstants.operation.ordinal() ] = vsomOperation;
	}

	public String getVsomSafetyinstruction() {
		return (String) saveArray[ tableFldConstants.safetyinstruction.ordinal() ];
	}

	public void setVsomSafetyinstruction(String vsomSafetyinstruction) {
		saveArray[ tableFldConstants.safetyinstruction.ordinal() ] = vsomSafetyinstruction;
	}

	public String getVsomEffectofnoncompliance() {
		return (String) saveArray[ tableFldConstants.effectofnoncompliance.ordinal() ];
	}

	public void setVsomEffectofnoncompliance(String vsomEffectofnoncompliance) {
		saveArray[ tableFldConstants.effectofnoncompliance.ordinal() ] = vsomEffectofnoncompliance;
	}

	public String getVsomPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setVsomPreparedby(String vsomPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = vsomPreparedby;
	}

	public String getVsomApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setVsomApprovedby(String vsomApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = vsomApprovedby;
	}

	public String getVsomIssuedby() {
		return (String) saveArray[ tableFldConstants.issuedby.ordinal() ];
	}

	public void setVsomIssuedby(String vsomIssuedby) {
		saveArray[ tableFldConstants.issuedby.ordinal() ] = vsomIssuedby;
	}

	public String getVsomStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setVsomStatus(String vsomStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = vsomStatus;
	}

	public String getVsomNextlevel() {
		return (String) saveArray[ tableFldConstants.nextlevel.ordinal() ];
	}

	public void setVsomNextlevel(String vsomNextlevel) {
		saveArray[ tableFldConstants.nextlevel.ordinal() ] = vsomNextlevel;
	}

	public String getVsomMaintsection() {
		return (String) saveArray[ tableFldConstants.maintsection.ordinal() ];
	}

	public void setVsomMaintsection(String vsomMaintsection) {
		saveArray[ tableFldConstants.maintsection.ordinal() ] = vsomMaintsection;
	}

	public String getVsomTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setVsomTempfield4(String vsomTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = vsomTempfield4;
	}

	public String getVsomTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setVsomTempfield5(String vsomTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = vsomTempfield5;
	}

	public String getVsomActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVsomActive(String vsomActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = vsomActive;
	}

	public String getVsomCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVsomCreatedby(String vsomCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = vsomCreatedby;
	}

	public String getVsomCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVsomCreatedon(String vsomCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = vsomCreatedon;
	}

	public String getVsomModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVsomModifiedon(String vsomModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = vsomModifiedon;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}
	
	//getter setter
	
	public Object getValue(tableFldConstants field) {
        return saveArray[field.ordinal()];
    }

    public void setValue(tableFldConstants field, Object value) {
        saveArray[field.ordinal()] = value;
    }
	
	//insert json
	
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
                if (field.name().equals("keyid") && val == null) {
                    sb.append("null");
                } else if (val == null) {
                    sb.append("\"\"");
                } else {
                    sb.append("\"").append(val.toString().replace("\"", "\\\"")).append("\"");
                }
                first = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }
	
	// from json
	public static JhaTlVisualsopmst fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response (Master): [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        JhaTlVisualsopmst mst = new JhaTlVisualsopmst();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.opt(key));
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            mst.setValue(field, val);
        }

        return mst;
    }

}

