package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMommst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlMomdtl {

	private  Object [] saveArray = null;  
//	private GenTlActionplandtl isActionPlanDetail;
    private List<GenTlActionplanmst> ActionPlanmst;
	public enum   tableFldConstants
	{
		keyid, momskeyid, discussiontype, discussiondetails, actionplanid
		, remarks,pillar, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public GenTlMomdtl()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getMomdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMomdKeyid(String momdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = momdKeyid;
	}

	public String getMomdMomsKeyid() {
		return (String) saveArray[ tableFldConstants.momskeyid.ordinal() ];
	}

	public void setMomdMomsKeyid(String momdMomsKeyid) {
		saveArray[ tableFldConstants.momskeyid.ordinal() ] = momdMomsKeyid;
	}

	public String getMomdDiscussionType() {
		return (String) saveArray[ tableFldConstants.discussiontype.ordinal() ];
	}

	public void setMomdDiscussionType(String momdDiscussionType) {
		saveArray[ tableFldConstants.discussiontype.ordinal() ] = momdDiscussionType;
	}

	public String getMomdDiscussionDetails() {
		return (String) saveArray[ tableFldConstants.discussiondetails.ordinal() ];
	}

	public void setMomdDiscussionDetails(String momdDiscussionDetails) {
		saveArray[ tableFldConstants.discussiondetails.ordinal() ] = momdDiscussionDetails;
	}

	public String getMomdActionplanId() {
		return (String) saveArray[ tableFldConstants.actionplanid.ordinal() ];
	}

	public void setMomdActionplanId(String momdActionplanId) {
		saveArray[ tableFldConstants.actionplanid.ordinal() ] = momdActionplanId;
	}

	public String getMomdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMomdRemarks(String momdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = momdRemarks;
	}

	public String getMomdPillar() {
		return (String) saveArray[ tableFldConstants.pillar.ordinal() ];
	}

	public void setMomdPillar(String momdPillar) {
		saveArray[ tableFldConstants.pillar.ordinal() ] = momdPillar;
	}
	
	public String getMomdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMomdTempfield1(String momdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = momdTempfield1;
	}

	public String getMomdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMomdTempfield2(String momdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = momdTempfield2;
	}

	public String getMomdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMomdTempfield3(String momdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = momdTempfield3;
	}

	public String getMomdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMomdTempfield4(String momdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = momdTempfield4;
	}

	public String getMomdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMomdTempfield5(String momdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = momdTempfield5;
	}

	public String getMomdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMomdActive(String momdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = momdActive;
	}

	public String getMomdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMomdCreatedby(String momdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = momdCreatedby;
	}

	public String getMomdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMomdCreatedon(String momdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = momdCreatedon;
	}

	public String getMomdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMomdModifiedon(String momdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = momdModifiedon;
	}
/*	public GenTlActionplandtl getisActionPlanDetail() {
		return isActionPlanDetail;
}
public void setisActionPlanDetail(GenTlActionplandtl isActionPlanDetail){
	this.isActionPlanDetail= isActionPlanDetail;
}*/

	public List<GenTlActionplanmst> getActionPlanmst() {
		return ActionPlanmst;
	}
	public void setActionPlanmst(List<GenTlActionplanmst> ActionPlanmst){
		this.ActionPlanmst= ActionPlanmst;
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
				if (field.name() == "keyid" && val == null) {
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

	public static GenTlMomdtl fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		GenTlMomdtl dtl = new GenTlMomdtl();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
			dtl.setValue(field, val);

		}
		return dtl;
	}

	public static List<GenTlMomdtl> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<GenTlMomdtl> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			GenTlMomdtl dtl = new GenTlMomdtl();

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
	
	public static String toJsonManualList(List<GenTlMomdtl> list) {
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

