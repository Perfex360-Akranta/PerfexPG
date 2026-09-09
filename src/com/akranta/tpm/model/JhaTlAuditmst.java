	package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class JhaTlAuditmst {

	private  Object [] saveArray = null;  
	private List<JhaTlAuditdtl> auditDtl ;
	private String elementid;

	public enum   tableFldConstants
	{
		
		keyid, auditdate,auditpillar,audittype,flid,  auditteamid, auditorname, leadername
		, totalpoints, auditortype, nextauditdate, nextauditteam, jhstepid
		, status,auditupload,tempfield2,tempfield3,tempfield4,tempfield5, active, createdby, createdon, modifiedon
	}

	public JhaTlAuditmst()
	{
		saveArray = new  Object [ 23 ];
		setAuditDtl(new ArrayList<JhaTlAuditdtl> ());
	
	}
	
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getJhamKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setJhamKeyid(String jhamKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = jhamKeyid;
	}

	public String getJhamAuditdate() {
		return (String) saveArray[ tableFldConstants.auditdate.ordinal() ];
	}

	public void setJhamAuditdate(String jhamAuditdate) {
		saveArray[ tableFldConstants.auditdate.ordinal() ] = jhamAuditdate;
	}

	public String getJhamAuditpillar() {
		return (String) saveArray[ tableFldConstants.auditpillar.ordinal() ];
	}

	public void setJhamAuditpillar(String jhamAuditpillar) {
		saveArray[ tableFldConstants.auditpillar.ordinal() ] = jhamAuditpillar;
	}
	
	public String getJhamFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setJhamFlid(String jhamFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = jhamFlid;
	}
	
	public String getJhamAudittype() {
		return (String) saveArray[ tableFldConstants.audittype.ordinal() ];
	}

	public void setJhamAudittype(String jhamAudittype) {
		saveArray[ tableFldConstants.audittype.ordinal() ] = jhamAudittype;
	}

	public String getJhamAuditteamid() {
		return (String) saveArray[ tableFldConstants.auditteamid.ordinal() ];
	}

	public void setJhamAuditteamid(String jhamAuditteamid) {
		saveArray[ tableFldConstants.auditteamid.ordinal() ] = jhamAuditteamid;
	}

	public String getJhamAuditorname() {
		return (String) saveArray[ tableFldConstants.auditorname.ordinal() ];
	}

	public void setJhamAuditorname(String jhamAuditorname) {
		saveArray[ tableFldConstants.auditorname.ordinal() ] = jhamAuditorname;
	}

	public String getJhamLeadername() {
		return (String) saveArray[ tableFldConstants.leadername.ordinal() ];
	}

	public void setJhamLeadername(String jhamLeadername) {
		saveArray[ tableFldConstants.leadername.ordinal() ] = jhamLeadername;
	}
//changes sriram 22sep
	/*
	 * public String getJhamTotalpoints() { return (String) saveArray[
	 * tableFldConstants.totalpoints.ordinal() ]; }
	 * 
	 * public void setJhamTotalpoints(String jhamTotalpoints) { saveArray[
	 * tableFldConstants.totalpoints.ordinal() ] = jhamTotalpoints; }
	 */

	
	public String getJhamTotalpoints() {
	    Object value = saveArray[tableFldConstants.totalpoints.ordinal()];
	    return value == null ? "" : String.valueOf(value);
	}

	public void setJhamTotalpoints(String jhamTotalpoints) {
	    saveArray[tableFldConstants.totalpoints.ordinal()] = jhamTotalpoints;
	}

	public String getJhamAuditortype() {
		return (String) saveArray[ tableFldConstants.auditortype.ordinal() ];
	}

	public void setJhamAuditortype(String jhamAuditortype) {
		saveArray[ tableFldConstants.auditortype.ordinal() ] = jhamAuditortype;
	}

	public String getJhamNextauditdate() {
		return (String) saveArray[ tableFldConstants.nextauditdate.ordinal() ];
	}

	public void setJhamNextauditdate(String jhamNextauditdate) {
		saveArray[ tableFldConstants.nextauditdate.ordinal() ] = jhamNextauditdate;
	}

	public String getJhamNextauditteam() {
		return (String) saveArray[ tableFldConstants.nextauditteam.ordinal() ];
	}

	public void setJhamNextauditteam(String jhamNextauditteam) {
		saveArray[ tableFldConstants.nextauditteam.ordinal() ] = jhamNextauditteam;
	}

	public String getJhamJhstepid() {
		return (String) saveArray[ tableFldConstants.jhstepid.ordinal() ];
	}

	public void setJhamJhstepid(String jhamJhstepid) {
		saveArray[ tableFldConstants.jhstepid.ordinal() ] = jhamJhstepid;
	}

	public String getJhamStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setJhamStatus(String jhamStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = jhamStatus;
	}
	
	public String getJhamAuditupload() {
		return (String) saveArray[ tableFldConstants.auditupload.ordinal() ];
	}

	public void setJhamAuditupload(String jhamAuditupload) {
		saveArray[ tableFldConstants.auditupload.ordinal() ] = jhamAuditupload;
	}
	
	public String getJhamTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setJhamTempfield2(String jhamTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = jhamTempfield2;
	}
	
	public String getJhamTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setJhamTempfield3(String jhamTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = jhamTempfield3;
	}
	
	public String getJhamTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setJhamTempfield4(String jhamTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = jhamTempfield4;
	}
	
	public String getJhamTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setJhamTempfield5(String jhamTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = jhamTempfield5;
	}

	public String getJhamActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJhamActive(String jhamActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jhamActive;
	}

	public String getJhamCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJhamCreatedby(String jhamCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jhamCreatedby;
	}

	public String getJhamCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJhamCreatedon(String jhamCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jhamCreatedon;
	}

	public String getJhamModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJhamModifiedon(String jhamModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = jhamModifiedon;
	}
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

	public List<JhaTlAuditdtl> getAuditDtl() {
		return auditDtl;
	}

	public void setAuditDtl(List<JhaTlAuditdtl> auditDtl) {
		this.auditDtl = auditDtl;
	}
	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}
	
	// 03-01-2026
	
	public Object getValue(tableFldConstants field) {
        return saveArray[field.ordinal()];
    }

    public void setValue(tableFldConstants field, Object value) {
        saveArray[field.ordinal()] = value;
    }

    /**
     * Convert object to JSON string manually
     */
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
     * Parse JSON string to object
     */
    public static JhaTlAuditmst fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);

        JhaTlAuditmst audit = new JhaTlAuditmst();
        CommonMessage.debugMsg("RAW JSON Response: " + json);

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
            String val = obj.optString(field.name(), null);
            audit.setValue(field, val != null && val.equals("null") ? null : val);
        }
        
        return audit;
    }

    /**
     * Parse JSON array to List of objects
     * ADDED: 03-01-2026 - Required for API getAllAudits() method
     */
    public static List<JhaTlAuditmst> fromJsonList(String json) {
        CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

        JSONArray jsonArray = JSONArray.fromObject(json);
        List<JhaTlAuditmst> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            JhaTlAuditmst audit = new JhaTlAuditmst();

            for (tableFldConstants field : tableFldConstants.values()) {
                String key = field.name();
                Object valueObj = obj.opt(key);
                String val = (valueObj == null || "null".equals(valueObj.toString())) 
                    ? null : valueObj.toString();
                audit.setValue(field, val);
            }

            list.add(audit);
        }

        return list;
    }

    /**
     * Convert List of objects to JSON array string
     * ADDED: 03-01-2026 - Required for API batch operations
     */
    public static String toJsonManualList(List<JhaTlAuditmst> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(list.get(i).toJsonManual());
        }

        sb.append("]");
        return sb.toString();
    }
}
