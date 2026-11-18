package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PcsTlProductiondata.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PcsTlLosscapture {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, date, shiftid, fromtime, totime, losstime, lossreason
		, lossid, tradeid, prod_impact, prod_imp_qty, lossdescription, pldetailsid
		, losshrno, lossremarks, machinid, source,sourceName,lubTime,cleanTime,tightime,inspectTime,toolId,tempField2,tempField3, active, createdby
		, createdon, modifiedon
	}

	public BAL_PcsTlLosscapture()
	{
		saveArray = new  Object [ 30 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPlosKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPlosKeyid(String plosKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = plosKeyid;
	}

	public String getPlosFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPlosFlid(String plosFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = plosFlid;
	}

	public String getPlosDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPlosDate(String plosDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = plosDate;
	}

	public String getPlosShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setPlosShiftid(String plosShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = plosShiftid;
	}

	public String getPlosFromtime() {
		return (String) saveArray[ tableFldConstants.fromtime.ordinal() ];
	}

	public void setPlosFromtime(String plosFromtime) {
		saveArray[ tableFldConstants.fromtime.ordinal() ] = plosFromtime;
	}

	public String getPlosTotime() {
		return (String) saveArray[ tableFldConstants.totime.ordinal() ];
	}

	public void setPlosTotime(String plosTotime) {
		saveArray[ tableFldConstants.totime.ordinal() ] = plosTotime;
	}

	public String getPlosLosstime() {
		return (String) saveArray[ tableFldConstants.losstime.ordinal() ];
	}

	public void setPlosLosstime(String plosLosstime) {
		saveArray[ tableFldConstants.losstime.ordinal() ] = plosLosstime;
	}

	public String getPlosLossreason() {
		return (String) saveArray[ tableFldConstants.lossreason.ordinal() ];
	}

	public void setPlosLossreason(String plosLossreason) {
		saveArray[ tableFldConstants.lossreason.ordinal() ] = plosLossreason;
	}

	public String getPlosLossid() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setPlosLossid(String plosLossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = plosLossid;
	}

	public String getPlosTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setPlosTradeid(String plosTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = plosTradeid;
	}

	public String getPlosProdImpact() {
		return (String) saveArray[ tableFldConstants.prod_impact.ordinal() ];
	}

	public void setPlosProdImpact(String plosProdImpact) {
		saveArray[ tableFldConstants.prod_impact.ordinal() ] = plosProdImpact;
	}

	public String getPlosProdImpQty() {
		return (String) saveArray[ tableFldConstants.prod_imp_qty.ordinal() ];
	}

	public void setPlosProdImpQty(String plosProdImpQty) {
		saveArray[ tableFldConstants.prod_imp_qty.ordinal() ] = plosProdImpQty;
	}

	public String getPLosLossdescription() {
		return (String) saveArray[ tableFldConstants.lossdescription.ordinal() ];
	}

	public void setPlosLossdescription(String plosLossdescription) {
		saveArray[ tableFldConstants.lossdescription.ordinal() ] = plosLossdescription;
	}

	public String getPlospldetailsid() {
		return (String) saveArray[ tableFldConstants.pldetailsid.ordinal() ];
	}

	public void setPlosPldetailsid(String plosPldetailsid) {
		saveArray[ tableFldConstants.pldetailsid.ordinal() ] = plosPldetailsid;
	}

	public String getPloslosshrno() {
		return (String) saveArray[ tableFldConstants.losshrno.ordinal() ];
	}

	public void setPloslosshrno(String ploslosshrno) {
		saveArray[ tableFldConstants.losshrno.ordinal() ] = ploslosshrno;
	}

	public String getPlosRemarks() {
		return (String) saveArray[ tableFldConstants.lossremarks.ordinal() ];
	}

	public void setPlosRemarks(String ploslossremarks) {
		saveArray[ tableFldConstants.lossremarks.ordinal() ] = ploslossremarks;
	}

	public String getPlosMachineId() {
		return (String) saveArray[ tableFldConstants.machinid.ordinal() ];
	}

	public void setPlosMachineId(String plosMachineId) {
		saveArray[ tableFldConstants.machinid.ordinal() ] = plosMachineId;
	}

	public String getPlosSource() {
		return (String) saveArray[ tableFldConstants.source.ordinal() ];
	}

	public void setPlosSource(String source) {
		saveArray[ tableFldConstants.source.ordinal() ] = source;
	}
	
	//
	
	public String getPlosSourcename() {
		return (String) saveArray[ tableFldConstants.sourceName.ordinal() ];
	}

	public void setPlosSourcename(String sorceName) {
		saveArray[ tableFldConstants.sourceName.ordinal() ] = sorceName;
	}
	public String getPlosCleantime() {
		return  (String) saveArray[ tableFldConstants.cleanTime.ordinal() ];
	}

	public void setPlosCleanTime(String cleanTime) {
		saveArray[ tableFldConstants.cleanTime.ordinal() ] = cleanTime;
	}
	public String getPlosLubtime() {
		return (String) saveArray[ tableFldConstants.lubTime.ordinal() ];
	}

	public void setPlosLubTime(String lubTime) {
		saveArray[ tableFldConstants.lubTime.ordinal() ] = lubTime;
	}
	public String getPlosTightTime() {
		return (String) saveArray[ tableFldConstants.tightime.ordinal() ];
	}

	public void setPlosTightTime(String tightTime) {
		saveArray[ tableFldConstants.tightime.ordinal() ] = tightTime;
	}
	public String getPlosInspectTime() {
		return (String) saveArray[ tableFldConstants.inspectTime.ordinal() ];
	}

	public void setPlosInspectTime(String inspectTime) {
		saveArray[ tableFldConstants.inspectTime.ordinal() ] = inspectTime;
	}
	
	//
	public String getPlosToolId() {
		return (String) saveArray[ tableFldConstants.toolId.ordinal() ];
	}
	
	public void setPlosToolId(String plosToolId ){
		saveArray[tableFldConstants.toolId.ordinal()]=plosToolId;
	}
	public void setPlosTempfield2(String plosTempfield2){
		saveArray[tableFldConstants.tempField2.ordinal()]= plosTempfield2;
	}
	public void setPlosTempfield3(String plosTempfield3){
		saveArray[tableFldConstants.tempField3.ordinal()]= plosTempfield3;
	}

	public String getPlosActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPlosActive(String plosActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = plosActive;
	}

	public String getPlosCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPlosCreatedby(String plosCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = plosCreatedby;
	}

	public String getPlosCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPlosCreatedon(String plosCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = plosCreatedon;
	}
	public String getPlosModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}
	public void setPlosModifiedon(String plosModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = plosModifiedon;
	}
	
	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	//to json
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
	  
	  public static String toJsonManualList(List<AbnTlAbnormality> list) {
		    StringBuilder sb = new StringBuilder();
		    sb.append("[");

		    for (int i = 0; i < list.size(); i++) {
		        if (i > 0) sb.append(",");
		        sb.append(list.get(i).toJsonManual());
		    }

		    sb.append("]");
		    return sb.toString();
		}
	  
	  //from json
	  public static BAL_PcsTlLosscapture fromJson(String json) {
		  //CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  BAL_PcsTlLosscapture abn = new BAL_PcsTlLosscapture();
		  //CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	//CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        abn.setValue(field, val);
		    	
		    }
		    return abn;
		}
	  
	  public static List<BAL_PcsTlLosscapture> fromJsonList(String json) {

		   // CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		    JSONArray jsonArray = JSONArray.fromObject(json);
		    List<BAL_PcsTlLosscapture> list = new ArrayList<>();

		    for (int i = 0; i < jsonArray.length(); i++) {

		        JSONObject obj = jsonArray.getJSONObject(i);
		        BAL_PcsTlLosscapture loss = new BAL_PcsTlLosscapture();
		        for (tableFldConstants field : tableFldConstants.values()) {
		            String key = field.name();

		            Object valueObj = obj.opt(key);
		           // String val = valueObj.toString();
		            String val = (valueObj == null) ? "" : valueObj.toString();
		            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
		                val = "";
		            }
//		            String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

		            loss.setValue(field, val);
		        }

		        list.add(loss);
		    }

		    return list;
		}

}

