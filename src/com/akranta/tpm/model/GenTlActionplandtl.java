package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlActionplanmst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class GenTlActionplandtl {

	private  Object [] saveArray = null;  
	

	private List<GenTlActionplandtl> Actionplanlist ;
	private String Actionplandes;
	
	public enum   tableFldConstants
	{
		keyid, aplm_keyid, tradeid, actionplan, howtodo, responsibility
		, targetdate, status, compleatedon, completedby, countermeasure
		, remarks, others, tempfiled2, tempfiled3, tempfiled4, tempfiled5
		, active, createdby, createdon, modifiedon
	}

	public GenTlActionplandtl()
	{
		saveArray = new  Object [ 21 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getApldKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setApldKeyid(String apldKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = apldKeyid;
	}

	public String getApldAplmKeyid() {
		return (String) saveArray[ tableFldConstants.aplm_keyid.ordinal() ];
	}

	public void setApldAplmKeyid(String apldAplmKeyid) {
		saveArray[ tableFldConstants.aplm_keyid.ordinal() ] = apldAplmKeyid;
	}

	public String getApldTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setApldTradeid(String apldTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = apldTradeid;
	}

	public String getApldActionplan() {
		return (String) saveArray[ tableFldConstants.actionplan.ordinal() ];
	}

	public void setApldActionplan(String apldActionplan) {
		saveArray[ tableFldConstants.actionplan.ordinal() ] = apldActionplan;
	}

	public String getApldHowtodo() {
		return (String) saveArray[ tableFldConstants.howtodo.ordinal() ];
	}

	public void setApldHowtodo(String apldHowtodo) {
		saveArray[ tableFldConstants.howtodo.ordinal() ] = apldHowtodo;
	}

	public String getApldResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setApldResponsibility(String apldResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = apldResponsibility;
	}

	public String getApldTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setApldTargetdate(String apldTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = apldTargetdate;
	}

	public String getApldStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setApldStatus(String apldStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = apldStatus;
	}

	public String getApldCompleatedon() {
		return (String) saveArray[ tableFldConstants.compleatedon.ordinal() ];
	}

	public void setApldCompleatedon(String apldCompleatedon) {
		saveArray[ tableFldConstants.compleatedon.ordinal() ] = apldCompleatedon;
	}

	public String getApldCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setApldCompletedby(String apldCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = apldCompletedby;
	}

	public String getApldCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setApldCountermeasure(String apldCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = apldCountermeasure;
	}

	public String getApldRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setApldRemarks(String apldRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = apldRemarks;
	}

	public String getApldOthers() {
		return (String) saveArray[ tableFldConstants.others.ordinal() ];
	}

	public void setApldOthers(String apldOthers) {
		saveArray[ tableFldConstants.others.ordinal() ] = apldOthers;
	}

	public String getApldTempfiled2() {
		return (String) saveArray[ tableFldConstants.tempfiled2.ordinal() ];
	}

	public void setApldTempfiled2(String apldTempfiled2) {
		saveArray[ tableFldConstants.tempfiled2.ordinal() ] = apldTempfiled2;
	}

	public String getApldTempfiled3() {
		return (String) saveArray[ tableFldConstants.tempfiled3.ordinal() ];
	}

	public void setApldTempfiled3(String apldTempfiled3) {
		saveArray[ tableFldConstants.tempfiled3.ordinal() ] = apldTempfiled3;
	}

	public String getApldTempfiled4() {
		return (String) saveArray[ tableFldConstants.tempfiled4.ordinal() ];
	}

	public void setApldTempfiled4(String apldTempfiled4) {
		saveArray[ tableFldConstants.tempfiled4.ordinal() ] = apldTempfiled4;
	}

	public String getApldTempfiled5() {
		return (String) saveArray[ tableFldConstants.tempfiled5.ordinal() ];
	}

	public void setApldTempfiled5(String apldTempfiled5) {
		saveArray[ tableFldConstants.tempfiled5.ordinal() ] = apldTempfiled5;
	}

	public String getApldActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setApldActive(String apldActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = apldActive;
	}

	public String getApldCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setApldCreatedby(String apldCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = apldCreatedby;
	}

	public String getApldCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setApldCreatedon(String apldCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = apldCreatedon;
	}

	public String getApldModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setApldModifiedon(String apldModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = apldModifiedon;
	}

	public void setActionplanlist(List<GenTlActionplandtl> actGridList1) {
		// TODO Auto-generated method stub
		this.Actionplanlist = actGridList1;
    }

	public List<GenTlActionplandtl> getActionplanlist() {
		// TODO Auto-generated method stub
		return Actionplanlist;
    }
	public void setActionplandes(String Actionplandes) {
		this.Actionplandes = Actionplandes;
	}
	public String getActionplandes() {
		return Actionplandes;
	}
	/*public void setActionplanidenfr(String actionplan) {
		// TODO Auto-generated method stub
		this.Actionplaniden = actionplan;
	}

	public String getActionplanidenfr() {
		// TODO Auto-generated method stub
		return Actionplaniden;
	}*/
	
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

	public static String toJsonManualList(List<GenTlActionplandtl> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}




	public static GenTlActionplandtl fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  GenTlActionplandtl abn = new GenTlActionplandtl();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        abn.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return abn;
	}

	public static List<GenTlActionplandtl> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<GenTlActionplandtl> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        GenTlActionplandtl abn = new GenTlActionplandtl();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            abn.setValue(field, val);
	        }

	        list.add(abn);
	    }

	    return list;
	}
	
}

