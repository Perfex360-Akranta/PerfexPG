package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.QtmTlCapaWorkflow.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QtmTlCapaActionmst{
	private  Object [] saveArray = null;  
	public enum   tableFldConstants
	{
        keyid,masterid,correctiveaction,rootcause,preventiveaction,fourmcondition,
        defecttype,responsibleid,targetdate,status,completedon,actionplanid,whywhwyid,suggestionid,
        tempfield1,tempfield2,tempfield3,tempfield4,tempfield5,tempfield6,tempfield7,tempfield8,
        tempfield9,tempfield10,active,createdby,createdon,modifiedon
	}
	
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	public QtmTlCapaActionmst()
	{
		saveArray = new  Object [28];
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getQcpaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQcpaKeyid(String qcpaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qcpaKeyid;
	}

	public String getQcpaQcapkeyid() {
		return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
	}

	public void setQcpaQcapkeyid(String qcpaQcapkeyid) {
		saveArray[ tableFldConstants.masterid.ordinal() ] = qcpaQcapkeyid;
	}

	public String getQcpaCorrectiveaction() {
		return (String) saveArray[ tableFldConstants.correctiveaction.ordinal() ];
	}

	public void setQcpaCorrectiveaction(String qcpaCorrectiveaction) {
		saveArray[ tableFldConstants.correctiveaction.ordinal() ] = qcpaCorrectiveaction;
	}

	public String getQcpaRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setQcpaRootcause(String qcpaRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = qcpaRootcause;
	}

	public String getQcpaPreventiveaction() {
		return (String) saveArray[ tableFldConstants.preventiveaction.ordinal() ];
	}
	
	public void setQcpaPreventiveaction(String qcpaPreventiveaction) {
		saveArray[ tableFldConstants.preventiveaction.ordinal() ] = qcpaPreventiveaction;
	}
	
	public String getQcpa4mcondition() {
		return (String) saveArray[ tableFldConstants.fourmcondition.ordinal() ];
	}
	
	public void setQcpa4mcondition(String qcpa4mcondition) {
		saveArray[ tableFldConstants.fourmcondition.ordinal() ] = qcpa4mcondition;
	}
	
	public String getQcpaDefecttype() {
		return (String) saveArray[ tableFldConstants.defecttype.ordinal() ];
	}
	
	public void setQcpaDefecttype(String qcpaDefecttype) {
		saveArray[ tableFldConstants.defecttype.ordinal() ] = qcpaDefecttype;
	}
	

	public String getQcpaResponsibleid() {
		return (String) saveArray[ tableFldConstants.responsibleid.ordinal() ];
	}

	public void setQcpaResponsibleid(String qcpaResponsibleid) {
		saveArray[ tableFldConstants.responsibleid.ordinal() ] = qcpaResponsibleid;
	}

	public String getQcpaTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setQcpaTargetdate(String qcpaTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = qcpaTargetdate;
	}

	public String getQcpaStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setQcpaStatus(String qcpaStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = qcpaStatus;
	}

	public String getQcpaCompletedon() {
		return (String) saveArray[ tableFldConstants.completedon.ordinal() ];
	}

	public void setQcpaCompletedon(String qcpaCompletedon) {
		saveArray[ tableFldConstants.completedon.ordinal() ] = qcpaCompletedon;
	}
	public String getQcpaActionplanid() {
		return (String) saveArray[ tableFldConstants.actionplanid.ordinal() ];
	}

	public void setQcpaActionplanid(String qcpaActionplanid) {
		saveArray[ tableFldConstants.actionplanid.ordinal() ] = qcpaActionplanid;
	}
	public String getQcpaWhywhyid() {
		return (String) saveArray[ tableFldConstants.whywhwyid.ordinal() ];
	}

	public void setQcpaWhywhyid(String qcpaWhywhyid) {
		saveArray[ tableFldConstants.whywhwyid.ordinal() ]=qcpaWhywhyid;
	}
	
	public String getQcpaSuggestionid() {
		return (String) saveArray[ tableFldConstants.suggestionid.ordinal() ];
	}

	public void setQcpaSuggestionid(String qcpaSuggestionid) {
		saveArray[ tableFldConstants.suggestionid.ordinal() ]=qcpaSuggestionid;
	}
	
	public String getQcpaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setQcpaTempfield1(String qcpaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = qcpaTempfield1;
	}

	public String getQcpaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setQcpaTempfield2(String qcpaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = qcpaTempfield2;
	}

	public String getQcpaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setQcpaTempfield3(String qcpaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = qcpaTempfield3;
	}

	public String getQcpaTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setQcpaTempfield4(String qcpaTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = qcpaTempfield4;
	}

	public String getQcpaTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setQcpaTempfield5(String qcpaTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = qcpaTempfield5;
	}

	public String getQcpaTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setQcpaTempfield6(String qcpaTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = qcpaTempfield6;
	}
	public String getQcpaTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setQcpaTempfield7(String qcpaTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = qcpaTempfield7;
	}
	public String getQcpaTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setQcpaTempfield8(String qcpaTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = qcpaTempfield8;
	}
	public String getQcpaTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setQcpaTempfield9(String qcpaTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = qcpaTempfield9;
	}
	public String getQcpaTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setQcpaTempfield10(String qcpaTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = qcpaTempfield10;
	}
	public String getQcpaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQcpaActive(String qcpaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qcpaActive;
	}

	public String getQcpaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQcpaCreatedby(String qcpaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qcpaCreatedby;
	}

	public String getQcpaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQcpaCreatedon(String qcpaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qcpaCreatedon;
	}

	public String getQcpaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQcpaModifiedon(String qcpaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = qcpaModifiedon;
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
	            if(field.name() == "keyid" && val == null) {
	            	sb.append("null");
	            }else if (val == null) {
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
  
  public static QtmTlCapaActionmst fromJson(String json) {
	  System.out.println("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  QtmTlCapaActionmst mst = new QtmTlCapaActionmst();
	  System.out.println("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	System.out.println("JSON[" + field + "] :"+obj.get(key));
	        //String val = obj.optString(field.name(), null);
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
	        //mst.setValue(field, val != null && val.equals("null") ? null : val);
	    	mst.setValue(field, val);
	    }
	    return mst;
	}
	public static List<QtmTlCapaActionmst> fromJsonList(String json) {

		System.out.println("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<QtmTlCapaActionmst> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			QtmTlCapaActionmst dtl = new QtmTlCapaActionmst();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				Object valueObj = obj.opt(key);
				String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
				
				dtl.setValue(field, val);
			}

			list.add(dtl);
		}

		return list;
	}
	
	public static String toJsonManualList(List<QtmTlCapaActionmst> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (int i = 0; i < list.size(); i++) {
			if (i > 0)
				sb.append(",");
			sb.append(list.get(i).toJsonManual());
		}

		sb.append("]");
		return sb.toString();
	}

}




