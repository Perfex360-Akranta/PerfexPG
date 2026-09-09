package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.JhaTlAuditmst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class JhaTlAuditdtl {

	private  Object [] saveArray = null;  
	private List <JhaTlAuditdtl> methodJhaTlAuditdtl;
	

	public enum   tableFldConstants
	{
		keyid, jhauditmasterid, parameterid, maximumpoints, pointsscored, remarks
		,ncremarks,ncactionplan,ncstatus,ncclosed
		,tempfield1,tempfield2,tempfield3,tempfield4,tempfield5
		,active, createdby, createdon, modifiedon
	}

	public JhaTlAuditdtl()
	{
		saveArray = new  Object [ 19 ];
		methodJhaTlAuditdtl = new ArrayList<JhaTlAuditdtl>();
	}
	
	public List<JhaTlAuditdtl> getmethodjhaTlAuditdtl() 
	{
		
		return methodJhaTlAuditdtl;
		
	}
	public void setmethodjhaTlAuditdtl(List <JhaTlAuditdtl> methodjhaTlAuditdtl) {
		this.methodJhaTlAuditdtl=methodjhaTlAuditdtl;
	}
	
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getJhadKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setJhadKeyid(String jhadKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = jhadKeyid;
	}

	public String getJhadJhauditmasterid() {
		return (String) saveArray[ tableFldConstants.jhauditmasterid.ordinal() ];
	}

	public void setJhadJhauditmasterid(String jhadJhauditmasterid) {
		saveArray[ tableFldConstants.jhauditmasterid.ordinal() ] = jhadJhauditmasterid;
	}

	public String getJhadParameterid() {
		return (String) saveArray[ tableFldConstants.parameterid.ordinal() ];
	}

	public void setJhadParameterid(String jhadParameterid) {
		saveArray[ tableFldConstants.parameterid.ordinal() ] = jhadParameterid;
	}

	public String getJhadMaximumpoints() {
		return (String) saveArray[ tableFldConstants.maximumpoints.ordinal() ];
	}

	public void setJhadMaximumpoints(String jhadMaximumpoints) {
		saveArray[ tableFldConstants.maximumpoints.ordinal() ] = jhadMaximumpoints;
	}

	public String getJhadPointsscored() {
		return (String) saveArray[ tableFldConstants.pointsscored.ordinal() ];
	}

	public void setJhadPointsscored(String jhadPointsscored) {
		saveArray[ tableFldConstants.pointsscored.ordinal() ] = jhadPointsscored;
	}

	public String getJhadRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setJhadRemarks(String jhadRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = jhadRemarks;
	}

	public String getJhadNcremarks() {
		return (String) saveArray[ tableFldConstants.ncremarks.ordinal() ];
	}

	public void setJhadNcremarks(String jhadNcremarks) {
		saveArray[ tableFldConstants.ncremarks.ordinal() ] = jhadNcremarks;
	}
	
	public String getJhadNcactionplan() {
		return (String) saveArray[ tableFldConstants.ncactionplan.ordinal() ];
	}

	public void setJhadNcactionplan(String jhadNcactionplan) {
		saveArray[ tableFldConstants.ncactionplan.ordinal() ] = jhadNcactionplan;
	}
	
	public String getJhadNcstatus() {
		return (String) saveArray[ tableFldConstants.ncstatus.ordinal() ];
	}

	public void setJhadNcstatus(String jhadNcstatus) {
		saveArray[ tableFldConstants.ncstatus.ordinal() ] = jhadNcstatus;
	}
	
	public String getJhadNcclosed() {
		return (String) saveArray[ tableFldConstants.ncclosed.ordinal() ];
	}

	public void setJhadNcclosed(String jhadNcclosed) {
		saveArray[ tableFldConstants.ncclosed.ordinal() ] = jhadNcclosed;
	}
	
	public String getJhadTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setJhadTempfield1(String jhadTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = jhadTempfield1;
	}
	
	public String getJhadTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setJhadTempfield2(String jhadTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = jhadTempfield2;
	}
	
	public String getJhadTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setJhadTempfield3(String jhadTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = jhadTempfield3;
	}
	
	public String getJhadTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setJhadTempfield4(String jhadTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = jhadTempfield4;
	}
	
	public String getJhadTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setJhadTempfield5(String jhadTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = jhadTempfield5;
	}

	
	public String getJhadActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJhadActive(String jhadActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jhadActive;
	}

	public String getJhadCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJhadCreatedby(String jhadCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jhadCreatedby;
	}

	public String getJhadCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJhadCreatedon(String jhadCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jhadCreatedon;
	}

	public String getJhadModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJhadModifiedon(String jhadModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = jhadModifiedon;
	}
	
	//03-01-2026
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	 // JSON Methods
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

    public static JhaTlAuditdtl fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        JhaTlAuditdtl dtl = new JhaTlAuditdtl();
        CommonMessage.debugMsg("RAW JSON Response: :" + json);

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
            String val = obj.optString(field.name(), null);
            dtl.setValue(field, val != null && val.equals("null") ? null : val);
        }

        return dtl;
    }

    public static List<JhaTlAuditdtl> fromJsonList(String json) {
        CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

        JSONArray jsonArray = JSONArray.fromObject(json);
        List<JhaTlAuditdtl> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            JhaTlAuditdtl dtl = new JhaTlAuditdtl();

            for (tableFldConstants field : tableFldConstants.values()) {
                String key = field.name();
                Object valueObj = obj.opt(key);
                String val = (valueObj == null || "null".equals(valueObj.toString())) 
                    ? null : valueObj.toString();
                dtl.setValue(field, val);
            }

            list.add(dtl);
        }

        return list;
    }

    public static String toJsonManualList(List<JhaTlAuditdtl> list) {
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

