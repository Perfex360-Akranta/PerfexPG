package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlMomGroupmst {

	private  Object [] saveArray = null;  
	
	private List<GenTlMomGroupdtl> GroupMemberDetail ;

	public enum   tableFldConstants
	{
		keyid, name, pillarid, flid, emailid, tempfield2, active, createdby
		, createdon, modifiedon
	}

	public GenTlMomGroupmst()
	{
		setGroupMemberDetail (new ArrayList<GenTlMomGroupdtl> ());
		saveArray = new  Object [ 10 ];
	}

	

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getMgrmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMgrmKeyid(String mgrmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mgrmKeyid;
	}

	public String getMgrmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setMgrmName(String mgrmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = mgrmName;
	}

	public String getMgrmPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setMgrmPillarid(String mgrmPillarid) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = mgrmPillarid;
	}

	public String getMgrmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMgrmFlid(String mgrmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = mgrmFlid;
	}

	public String getMgrmEmailid() {
		return (String) saveArray[ tableFldConstants.emailid.ordinal() ];
	}

	public void setMgrmEmailid(String mgrmEmailid) {
		saveArray[ tableFldConstants.emailid.ordinal() ] = mgrmEmailid;
	}

	public String getMgrmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMgrmTempfield2(String mgrmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mgrmTempfield2;
	}

	public String getMgrmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMgrmActive(String mgrmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mgrmActive;
	}

	public String getMgrmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMgrmCreatedby(String mgrmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mgrmCreatedby;
	}

	public String getMgrmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMgrmCreatedon(String mgrmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mgrmCreatedon;
	}

	public String getMgrmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMgrmModifiedon(String mgrmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mgrmModifiedon;
	}
	
	public void setGroupMemberDetail(List<GenTlMomGroupdtl> groupMemberDetail) {
		this.GroupMemberDetail = groupMemberDetail;
	}

	public List<GenTlMomGroupdtl> getGroupMemberDetail() {
		// TODO Auto-generated method stub
		return GroupMemberDetail;
		
		
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
                if (!first)
                    sb.append(",");
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
    public static GenTlMomGroupmst fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        GenTlMomGroupmst mst = new GenTlMomGroupmst();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
            String val = obj.optString(field.name(), null);
            mst.setValue(field, val != null && val.equals("null") ? null : val);
        }
        return mst;
    }

    /**
     * Convert list to JSON array string
     */
    public static String toJsonManualList(List<GenTlMomGroupdtl> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (i > 0)
                    sb.append(",");
                sb.append(list.get(i).toJsonManual());
            }
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Parse JSON array to list
     */
    public static List<GenTlMomGroupdtl> fromJsonList(String json) {
        CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

        JSONArray jsonArray = JSONArray.fromObject(json);
        List<GenTlMomGroupdtl> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            GenTlMomGroupdtl dtl = new GenTlMomGroupdtl();

            for (GenTlMomGroupdtl.tableFldConstants field : GenTlMomGroupdtl.tableFldConstants.values()) {
                String key = field.name();
                Object valueObj = obj.opt(key);
                String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
                dtl.setValue(field, val);
            }

            list.add(dtl);
        }

        return list;
    }
	

}

