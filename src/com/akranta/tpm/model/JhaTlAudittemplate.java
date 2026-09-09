package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.JhaTlAuditparameter.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class JhaTlAudittemplate {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, masterid, parametername, parameterdescription,evidence, maximumpoints
		,reviewptslno,criteriaslno,tempfield3,tempfield4,tempfield5, active, createdby, createdon, modifiedon
	}

	public JhaTlAudittemplate()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getJautKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setJautKeyid(String jautKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = jautKeyid;
	}

	public String getJautMasterid() {
		return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
	}

	public void setJautMasterid(String jautMasterid) {
		saveArray[ tableFldConstants.masterid.ordinal() ] = jautMasterid;
	}

	public String getJautParametername() {
		return (String) saveArray[ tableFldConstants.parametername.ordinal() ];
	}

	public void setJautParametername(String jautParametername) {
		saveArray[ tableFldConstants.parametername.ordinal() ] = jautParametername;
	}	
		
	public String getJautParameterdescription() {
		return (String) saveArray[ tableFldConstants.parameterdescription.ordinal() ];
	}

	public void setJautParameterdescription(String jautParameterdescription) {
		saveArray[ tableFldConstants.parameterdescription.ordinal() ] = jautParameterdescription;
	}
	
	public String getJautEvidence() {
		return (String) saveArray[ tableFldConstants.evidence.ordinal() ];
	}

	public void setJautEvidence(String jautEvidence) {
		saveArray[ tableFldConstants.evidence.ordinal() ] = jautEvidence;
	}

	public String getJautMaximumpoints() {
		return (String) saveArray[ tableFldConstants.maximumpoints.ordinal() ];
	}

	public void setJautMaximumpoints(String jautMaximumpoints) {
		saveArray[ tableFldConstants.maximumpoints.ordinal() ] = jautMaximumpoints;
	}
	
	public String getJautReviewPtSlno() {
		return (String) saveArray[ tableFldConstants.reviewptslno.ordinal() ];
	}

	public void setJautReviewPtSlno(String jautTempfield1) {
		saveArray[ tableFldConstants.reviewptslno.ordinal() ] = jautTempfield1;
	}
	
	public String getJautCriteriaSlno() {
		return (String) saveArray[ tableFldConstants.criteriaslno.ordinal() ];
	}

	public void setJautCriteriaSlno(String jautTempfield2) {
		saveArray[ tableFldConstants.criteriaslno.ordinal() ] = jautTempfield2;
	}
	
	public String getJautTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setJautTempfield3(String jautTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = jautTempfield3;
	}
	
	public String getJautTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setJautTempfield4(String jautTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = jautTempfield4;
	}
	
	public String getJautTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setJautTempfield5(String jautTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = jautTempfield5;
	}

	public String getJautActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJautActive(String jautActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jautActive;
	}

	public String getJautCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJautCreatedby(String jautCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jautCreatedby;
	}

	public String getJautCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJautCreatedon(String jautCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jautCreatedon;
	}

	public String getJautModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJautModifiedon(String jautModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = jautModifiedon;
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

    public static JhaTlAudittemplate fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        JhaTlAudittemplate template = new JhaTlAudittemplate();
        CommonMessage.debugMsg("RAW JSON Response: :" + json);

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
            String val = obj.optString(field.name(), null);
            template.setValue(field, val != null && val.equals("null") ? null : val);
        }

        return template;
    }

    public static List<JhaTlAudittemplate> fromJsonList(String json) {
        CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

        JSONArray jsonArray = JSONArray.fromObject(json);
        List<JhaTlAudittemplate> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            JhaTlAudittemplate template = new JhaTlAudittemplate();

            for (tableFldConstants field : tableFldConstants.values()) {
                String key = field.name();
                Object valueObj = obj.opt(key);
                String val = (valueObj == null || "null".equals(valueObj.toString())) 
                    ? null : valueObj.toString();
                template.setValue(field, val);
            }

            list.add(template);
        }

        return list;
    }

    public static String toJsonManualList(List<JhaTlAudittemplate> list) {
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

