package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class BdmTlWwbladtl {

	private  Object [] saveArray = null;  
	private String wwblParent;

	public enum   tableFldConstants
	{
		keyid, wwbl_keyid, phenomena_factor, verification, parentid, orderno, levelno, islastfactor
		, countermeasure, skilltype, responsiblity, targetdate, status
		, active, createdby, createdon, modifiedon,reoccur,actiontaken,completedby,completedon,remarks
	}
	
	public BdmTlWwbladtl()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWwbdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWwbdKeyid(String wwbdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wwbdKeyid;
	}

	public String getWwbdWwblKeyid() {
		return (String) saveArray[ tableFldConstants.wwbl_keyid.ordinal() ];
	}

	public void setWwbdWwblKeyid(String wwbdWwblKeyid) {
		saveArray[ tableFldConstants.wwbl_keyid.ordinal() ] = wwbdWwblKeyid;
	}

	public String getWwbdPhenomenaFactor() {
		return (String) saveArray[ tableFldConstants.phenomena_factor.ordinal() ];
	}

	public void setWwbdPhenomenaFactor(String wwbdPhenomenaFactor) {
		saveArray[ tableFldConstants.phenomena_factor.ordinal() ] = wwbdPhenomenaFactor;
	}

	public String getWwbdVerification() {
		return (String) saveArray[ tableFldConstants.verification.ordinal() ];
	}

	public void setWwbdVerification(String wwbdVerification) {
		saveArray[ tableFldConstants.verification.ordinal() ] = wwbdVerification;
	}
	
	public String getWwbdParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setWwbdParentid(String wwbdParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = wwbdParentid;
	}

	public String getWwbdOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setWwbdOrderno(String wwbdOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = wwbdOrderno;
	}

	public String getWwbdLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setWwbdLevelno(String wwbdLevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = wwbdLevelno;
	}
	
	public String getWwbdIslastfactor() {
		return (String) saveArray[ tableFldConstants.islastfactor.ordinal() ];
	}

	public void setWwbdIslastfactor(String wwbdIslastfactor) {
		saveArray[ tableFldConstants.islastfactor.ordinal() ] = wwbdIslastfactor;
	}

	public String getWwbdCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setWwbdCountermeasure(String wwbdCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = wwbdCountermeasure;
	}

	public String getWwbdSkilltype() {
		return (String) saveArray[ tableFldConstants.skilltype.ordinal() ];
	}

	public void setWwbdSkilltype(String wwbdSkilltype) {
		saveArray[ tableFldConstants.skilltype.ordinal() ] = wwbdSkilltype;
	}

	public String getWwbdResponsiblity() {
		return (String) saveArray[ tableFldConstants.responsiblity.ordinal() ];
	}

	public void setWwbdResponsiblity(String wwbdResponsiblity) {
		saveArray[ tableFldConstants.responsiblity.ordinal() ] = wwbdResponsiblity;
	}
	
	

	public String getWwbdTargetDate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setWwbdTargetDate(String wwbdTargetDate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = wwbdTargetDate;
	}
	
	

	public String getWwbdStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setWwbdStatus(String wwbdStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = wwbdStatus;
	}

	public String getWwbdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWwbdActive(String wwbdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wwbdActive;
	}

	public String getWwbdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWwbdCreatedby(String wwbdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wwbdCreatedby;
	}

	public String getWwbdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWwbdCreatedon(String wwbdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wwbdCreatedon;
	}

	public String getWwbdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWwbdModifiedon(String wwbdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wwbdModifiedon;
	}
		
	public String getWwbdReoccur() {
		return (String) saveArray[ tableFldConstants.reoccur.ordinal() ];
	}

	public void setWwbdReoccur(String wwbdReoccur) {
		saveArray[ tableFldConstants.reoccur.ordinal() ] = wwbdReoccur;
	}
	
	public String getWwbdAction() {
		return (String) saveArray[ tableFldConstants.actiontaken.ordinal() ];
	}

	public void setWwbdAction(String wwbdAction) {
		saveArray[ tableFldConstants.actiontaken.ordinal() ] = wwbdAction;
	}
	
	public String getwwbdCompletedBy() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setWwbdCompletedBy(String wwbdCompletedBy) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = wwbdCompletedBy;
	}
	
	
	public String getWwbdCompletedon() {
		return (String) saveArray[ tableFldConstants.completedon.ordinal() ];
	}

	public void setWwbdCompletedon(String wwbdCompletedon) {
		saveArray[ tableFldConstants.completedon.ordinal() ] = wwbdCompletedon;
	}
	
	public String getWwbdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setWwbdRemarks(String wwbdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = wwbdRemarks;
	}
	
		public void setWwblParent(String wwblParent) {
		this.wwblParent = wwblParent;
	}

	public String getWwblParent() {
		return wwblParent;
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
     * Create detail object from JSON string
     */
    public static BdmTlWwbladtl fromJson(String json) {
        CommonMessage.debugMsg("RAW Detail JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        BdmTlWwbladtl dtl = new BdmTlWwbladtl();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("Detail JSON[" + field + "] :" + obj.get(key));
            String val = obj.optString(field.name(), null);
            dtl.setValue(field, val != null && val.equals("null") ? null : val);
        }
        return dtl;
    }

}

