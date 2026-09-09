package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class BdmTlWwblamst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, preparedby, prepareddate, problem, phenomena, mechanism
		, lopcid, lopcempid, lopcyn, active, createdby, createdon
		, modifiedon,wwblinvestigation
	
	}

	public BdmTlWwblamst()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

	public String getWwblKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWwblKeyid(String wwblKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wwblKeyid;
	}

	public String getWwblFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setWwblFlid(String wwblFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = wwblFlid;
	}

	public String getWwblPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setWwblPreparedby(String wwblPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = wwblPreparedby;
	}

	public String getWwblPrepareddate() {
		return (String) saveArray[ tableFldConstants.prepareddate.ordinal() ];
	}

	public void setWwblPrepareddate(String wwblPrepareddate) {
		saveArray[ tableFldConstants.prepareddate.ordinal() ] = wwblPrepareddate;
	}

	public String getWwblProblem() {
		return (String) saveArray[ tableFldConstants.problem.ordinal() ];
	}

	public void setWwblProblem(String wwblProblem) {
		saveArray[ tableFldConstants.problem.ordinal() ] = wwblProblem;
	}

	public String getWwblPhenomena() {
		return (String) saveArray[ tableFldConstants.phenomena.ordinal() ];
	}

	public void setWwblPhenomena(String wwblPhenomena) {
		saveArray[ tableFldConstants.phenomena.ordinal() ] = wwblPhenomena;
	}

	public String getWwblMechanism() {
		return (String) saveArray[ tableFldConstants.mechanism.ordinal() ];
	}

	public void setWwblMechanism(String wwblMechanism) {
		saveArray[ tableFldConstants.mechanism.ordinal() ] = wwblMechanism;
	}

	public String getWwblLopcId() {
		return (String) saveArray[ tableFldConstants.lopcid.ordinal() ];
	}

	public void setWwblLopcId(String wwblLopcEmpId) {
		saveArray[ tableFldConstants.lopcid.ordinal() ] = wwblLopcEmpId;
	}

	public String getWwblLopcEmpId() {
		return (String) saveArray[ tableFldConstants.lopcempid.ordinal() ];
	}

	public void setWwblLopcEmpId(String wwblLopcEmpId) {
		saveArray[ tableFldConstants.lopcempid.ordinal() ] = wwblLopcEmpId;
	}

	public String getWwblLopcYn() {
		return (String) saveArray[ tableFldConstants.lopcyn.ordinal() ];
	}

	public void setWwblLopcYn(String wwblLopcYn) {
		saveArray[ tableFldConstants.lopcyn.ordinal() ] = wwblLopcYn;
	}

	public String getWwblActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWwblActive(String wwblActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wwblActive;
	}

	public String getWwblCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWwblCreatedby(String wwblCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wwblCreatedby;
	}

	public String getWwblCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWwblCreatedon(String wwblCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wwblCreatedon;
	}

	public String getWwblModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWwblModifiedon(String wwblModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wwblModifiedon;
	}
	
	public String getWwblInvestigation() {
		return (String) saveArray[ tableFldConstants.wwblinvestigation.ordinal() ];
	}

	public void setWwblInvestigation(String wwblInvestigation) {
		saveArray[ tableFldConstants.wwblinvestigation.ordinal() ] = wwblInvestigation;
		
	}
	 public Object getValue(tableFldConstants field) {
	        return saveArray[field.ordinal()];
	    }

	    public void setValue(tableFldConstants field, Object value) {
	        saveArray[field.ordinal()] = value;
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
                if (field.name().equals("keyid") && val == null) {
                    sb.append("null");
                } else if (val == null) {
                    sb.append("\"{}\"");
                } else {
                    sb.append("\"").append(val.toString()).append("\"");
                }
                first = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }

    /**
     * Create object from JSON string
     */
    public static BdmTlWwblamst fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);

        BdmTlWwblamst mst = new BdmTlWwblamst();
        CommonMessage.debugMsg("RAW JSON Response: :" + json);

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();

            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
            String val = obj.optString(field.name(), null);
            mst.setValue(field, val != null && val.equals("null") ? null : val);
        }
        return mst;
    }

}

