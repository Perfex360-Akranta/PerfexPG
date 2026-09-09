package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class JhaTlAuditparameter {

	private  Object [] saveArray = null;  
	private List<JhaTlTemplatemchlink> EquipmentGrid;
	private List<JhaTlTemplatesteplink> JHStepGrid;
	private List<JhaTlTemplatelevellink> jhLevelGrid;
	private List<JhaTlAudittemplate> JhAuditTemplate;
	private List<JhaTlTemplategradelink> jhGradeGrid;
	
	public enum   tableFldConstants
	{
		keyid, templatename, templatecode, auditpillar,audittype, auditlevel,evidence, remarks, revisionno, revisiondate,
		criteriamax,tempfield2,tempfield3,tempfield4,tempfield5, active, createdby, createdon, modifiedon
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public JhaTlAuditparameter()
	{
		saveArray = new  Object [ 19];		
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getJhapKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setJhapKeyid(String jhapKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = jhapKeyid;
	}

	public String getJhapTemplatename() {
		return (String) saveArray[ tableFldConstants.templatename.ordinal() ];
	}

	public void setJhapTemplatename(String jhapTemplatename) {
		saveArray[ tableFldConstants.templatename.ordinal() ] = jhapTemplatename;
	}

	public String getJhapTemplatecode() {
		return (String) saveArray[ tableFldConstants.templatecode.ordinal() ];
	}

	public void setJhapTemplatecode(String jhapTemplatecode) {
		saveArray[ tableFldConstants.templatecode.ordinal() ] = jhapTemplatecode;
	}
	
	public String getJhapAuditpillar() {
		return (String) saveArray[ tableFldConstants.auditpillar.ordinal() ];
	}

	public void setJhapAuditpillar(String jhapAuditpillar) {
		saveArray[ tableFldConstants.auditpillar.ordinal() ] = jhapAuditpillar;
	}
	
	public String getJhapAudittype() {
		return (String) saveArray[ tableFldConstants.audittype.ordinal() ];
	}

	public void setJhapAudittype(String jhapAudittype) {
		saveArray[ tableFldConstants.audittype.ordinal() ] = jhapAudittype;
	}
	
	public String getJhapAuditlevel() {
		return (String) saveArray[ tableFldConstants.auditlevel.ordinal() ];
	}
	
	public void setJhapAuditlevel(String jhapAuditlevel) {
		saveArray[ tableFldConstants.auditlevel.ordinal() ] = jhapAuditlevel;
	}
	
	public String getJhapEvidence() {
		return (String) saveArray[ tableFldConstants.evidence.ordinal() ];
	}
	
	public void setJhapEvidence(String jhapEvidence) {
		saveArray[ tableFldConstants.evidence.ordinal() ] = jhapEvidence;
	}
		
	public String getJhapRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setJhapRemarks(String jhapRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = jhapRemarks;
	}

	public String getJhapRevisionno() {
		return (String) saveArray[ tableFldConstants.revisionno.ordinal() ];
	}

	public void setJhapRevisionno(String jhapRevisionno) {
		saveArray[ tableFldConstants.revisionno.ordinal() ] = jhapRevisionno;
	}

	public String getJhapRevisiondate() {
		return (String) saveArray[ tableFldConstants.revisiondate.ordinal() ];
	}

	public void setJhapRevisiondate(String jhapRevisiondate) {
		saveArray[ tableFldConstants.revisiondate.ordinal() ] = jhapRevisiondate;
	}

	public String getJhapCriteriamax() {
		return (String) saveArray[ tableFldConstants.criteriamax.ordinal() ];
	}

	public void setJhapCriteriamax(String jhapCriteriamax) {
		saveArray[ tableFldConstants.criteriamax.ordinal() ] = jhapCriteriamax;
	}
	
	public String getJhapTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setJhapTempfield2(String jhapTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = jhapTempfield2;
	}
	
	public String getJhapTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setJhapTempfield3(String jhapTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = jhapTempfield3;
	}
	
	public String getJhapTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setJhapTempfield4(String jhapTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = jhapTempfield4;
	}
	
	public String getJhapTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setJhapTempfield5(String jhapTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = jhapTempfield5;
	}
	
	public String getJhapActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJhapActive(String jhapActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jhapActive;
	}

	public String getJhapCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJhapCreatedby(String jhapCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jhapCreatedby;
	}

	public String getJhapCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJhapCreatedon(String jhapCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jhapCreatedon;
	}

	public String getJhapModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJhapModifiedon(String jhapModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = jhapModifiedon;
	}
	public void setEquipmentGrid(List<JhaTlTemplatemchlink> equipmentGrid) {
		EquipmentGrid = equipmentGrid;
	}
	public List<JhaTlTemplatemchlink> getEquipmentGrid() {
		return EquipmentGrid;
	}
	public void setJHStepGrid(List<JhaTlTemplatesteplink> jHStepGrid) {
		JHStepGrid = jHStepGrid;
	}
	public List<JhaTlTemplatesteplink> getJHStepGrid() {
		return JHStepGrid;
	}
	public void setJhLevelGrid(List<JhaTlTemplatelevellink> jhLevelGrid) {
		this.jhLevelGrid = jhLevelGrid;
	}
	public List<JhaTlTemplatelevellink> getJhLevelGrid() {
		return jhLevelGrid;
	}
	public void setJhAuditTemplate(List<JhaTlAudittemplate> jhAuditTemplate) {
		JhAuditTemplate = jhAuditTemplate;
	}
	public List<JhaTlAudittemplate> getJhAuditTemplate() {
		return JhAuditTemplate;
	}
	public void setJhGradeGrid(List<JhaTlTemplategradelink> jhGradeGrid) {
		this.jhGradeGrid = jhGradeGrid;
	}
	public List<JhaTlTemplategradelink> getJhGradeGrid() {
		return jhGradeGrid;
	}
	
	
	
	 
    public Object getValue(tableFldConstants field) {
        return saveArray[field.ordinal()];
    }

    /**
     * Set value by field constant
     */
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
    public static JhaTlAuditparameter fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        JhaTlAuditparameter parameter = new JhaTlAuditparameter();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
            String val = obj.optString(field.name(), null);
            parameter.setValue(field, val != null && val.equals("null") ? null : val);
        }
        
        return parameter;
    }

    /**
     * Parse JSON array to List of objects
     */
    public static List<JhaTlAuditparameter> fromJsonList(String json) {
        CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

        JSONArray jsonArray = JSONArray.fromObject(json);
        List<JhaTlAuditparameter> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            JhaTlAuditparameter parameter = new JhaTlAuditparameter();

            for (tableFldConstants field : tableFldConstants.values()) {
                String key = field.name();
                Object valueObj = obj.opt(key);
                String val = (valueObj == null || "null".equals(valueObj.toString())) 
                    ? null : valueObj.toString();
                parameter.setValue(field, val);
            }

            list.add(parameter);
        }

        return list;
    }

    /**
     * Convert List of objects to JSON array string
     */
    public static String toJsonManualList(List<JhaTlAuditparameter> list) {
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

	



