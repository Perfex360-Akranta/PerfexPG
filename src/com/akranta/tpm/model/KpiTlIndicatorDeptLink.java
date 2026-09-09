package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KpiTlIndicatorDeptLink {

	private  Object [] saveArray = null;  
	private List <KpiTlIndicatorDeptLink> methodKpiTlIndicatorDeptLink;
	private String kidlIsDelete="";
	public enum   tableFldConstants
	{
		keyid, indicatorid, deptid, depttype, pillarid, effectivedate
		, inactivedate, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public KpiTlIndicatorDeptLink()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKidlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKidlKeyid(String kidlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kidlKeyid;
	}

	public String getKidlIndicatorid() {
		return (String) saveArray[ tableFldConstants.indicatorid.ordinal() ];
	}

	public void setKidlIndicatorid(String kidlIndicatorid) {
		saveArray[ tableFldConstants.indicatorid.ordinal() ] = kidlIndicatorid;
	}

	public String getKidlDeptid() {
		return (String) saveArray[ tableFldConstants.deptid.ordinal() ];
	}

	public void setKidlDeptid(String kidlDeptid) {
		saveArray[ tableFldConstants.deptid.ordinal() ] = kidlDeptid;
	}

	public String getKidlDepttype() {
		return (String) saveArray[ tableFldConstants.depttype.ordinal() ];
	}

	public void setKidlDepttype(String kidlDepttype) {
		saveArray[ tableFldConstants.depttype.ordinal() ] = kidlDepttype;
	}

	public String getKidlPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setKidlPillarid(String kidlPillarid) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = kidlPillarid;
	}

	public String getKidlEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setKidlEffectivedate(String kidlEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = kidlEffectivedate;
	}

	public String getKidlInactivedate() {
		return (String) saveArray[ tableFldConstants.inactivedate.ordinal() ];
	}

	public void setKidlInactivedate(String kidlInactivedate) {
		saveArray[ tableFldConstants.inactivedate.ordinal() ] = kidlInactivedate;
	}

	public String getKidlTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKidlTempfield1(String kidlTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kidlTempfield1;
	}

	public String getKidlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKidlTempfield2(String kidlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kidlTempfield2;
	}

	public String getKidlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKidlTempfield3(String kidlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kidlTempfield3;
	}

	public String getKidlTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKidlTempfield4(String kidlTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kidlTempfield4;
	}

	public String getKidlTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKidlTempfield5(String kidlTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kidlTempfield5;
	}

	public String getKidlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKidlActive(String kidlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kidlActive;
	}

	public String getKidlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKidlCreatedby(String kidlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kidlCreatedby;
	}

	public String getKidlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKidlCreatedon(String kidlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kidlCreatedon;
	}

	public String getKidlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKidlModifiedon(String kidlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kidlModifiedon;
	}

	public void setmethodPillarFactlink(
			List<KpiTlIndicatorDeptLink> pillarFactLinkList) {
		this.methodKpiTlIndicatorDeptLink =pillarFactLinkList;
		
	}
	public List <KpiTlIndicatorDeptLink>  getmethodPillarFactlink(
			) {
		return this.methodKpiTlIndicatorDeptLink ;
		
	}
	public void setIsDelete(String kidlIsDelete) {
		this.kidlIsDelete = kidlIsDelete;
	}

	public String getIsDelete() {
		return this.kidlIsDelete;
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
  
//  public static String toJsonManualList(List<KpiTlIndicatorDeptLink> list) {			
//	    StringBuilder sb = new StringBuilder();
//	    sb.append("[");
//
//	    for (int i = 0; i < list.size(); i++) {
//	        if (i > 0) sb.append(",");
//	        sb.append(list.get(i).toJsonManual());
//	    }
//
//	    sb.append("]");
//	    return sb.toString();
//	}
	
	public static String toJsonManualList(List<KpiTlIndicatorDeptLink> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	    	if (i > 0) sb.append(",");
	    	sb.append(" { ");
	    	sb.append("\"kpiTlIndicatorDeptLink\":");
	        sb.append(list.get(i).toJsonManual());
		    sb.append(",\"isDelete\":");
		    if(CommonFunctions.isValidKeyId(list.get(i).getIsDelete())) {
		    	sb.append("\"").append(list.get(i).getIsDelete()).append("\"");
		    }else {
		    	sb.append("\"").append("N").append("\"");
		    }
		    sb.append(" } ");
		    
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
  
  public static KpiTlIndicatorDeptLink fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KpiTlIndicatorDeptLink abn = new KpiTlIndicatorDeptLink();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        abn.setValue(field, val != null && val.equals("null") ? null : val);
	    	
	    }
	    return abn;
	}
  
  public static List<KpiTlIndicatorDeptLink> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<KpiTlIndicatorDeptLink> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KpiTlIndicatorDeptLink abn = new KpiTlIndicatorDeptLink();

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

