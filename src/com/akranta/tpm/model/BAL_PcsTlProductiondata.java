package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PcsTlProductiondata {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, cellid, date, shiftid, hour, productid, productmodel
		, manpower, cycletime, producedqty, rejectedqty, reworkqty, shiftnoplan
		, hourlynoplan, noplanduration, remarks, tempfield1, tempfield2
		, tempfield3, active, createdby, createdon, modifiedon
	}

	public BAL_PcsTlProductiondata()
	{
		saveArray = new  Object [ 24 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPprdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPprdKeyid(String pprdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pprdKeyid;
	}

	public String getPprdFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPprdFlid(String pprdFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = pprdFlid;
	}

	public String getPprdCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setPprdCellid(String pprdCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = pprdCellid;
	}

	public String getPprdDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPprdDate(String pprdDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = pprdDate;
	}

	public String getPprdShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setPprdShiftid(String pprdShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = pprdShiftid;
	}

	public String getPprdHour() {
		return (String) saveArray[ tableFldConstants.hour.ordinal() ];
	}

	public void setPprdHour(String pprdHour) {
		saveArray[ tableFldConstants.hour.ordinal() ] = pprdHour;
	}

	public String getPprdProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setPprdProductid(String pprdProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = pprdProductid;
	}

	public String getPprdProductmodel() {
		return (String) saveArray[ tableFldConstants.productmodel.ordinal() ];
	}

	public void setPprdProductmodel(String pprdProductmodel) {
		saveArray[ tableFldConstants.productmodel.ordinal() ] = pprdProductmodel;
	}

	public String getPprdManpower() {
		return (String) saveArray[ tableFldConstants.manpower.ordinal() ];
	}

	public void setPprdManpower(String pprdManpower) {
		saveArray[ tableFldConstants.manpower.ordinal() ] = pprdManpower;
	}

	public String getPprdCycletime() {
		return (String) saveArray[ tableFldConstants.cycletime.ordinal() ];
	}

	public void setPprdCycletime(String pprdCycletime) {
		saveArray[ tableFldConstants.cycletime.ordinal() ] = pprdCycletime;
	}

	public String getPprdProducedqty() {
		return (String) saveArray[ tableFldConstants.producedqty.ordinal() ];
	}

	public void setPprdProducedqty(String pprdProducedqty) {
		saveArray[ tableFldConstants.producedqty.ordinal() ] = pprdProducedqty;
	}

	public String getPprdRejectedqty() {
		return (String) saveArray[ tableFldConstants.rejectedqty.ordinal() ];
	}

	public void setPprdRejectedqty(String pprdRejectedqty) {
		saveArray[ tableFldConstants.rejectedqty.ordinal() ] = pprdRejectedqty;
	}

	public String getPprdReworkqty() {
		return (String) saveArray[ tableFldConstants.reworkqty.ordinal() ];
	}

	public void setPprdReworkqty(String pprdReworkqty) {
		saveArray[ tableFldConstants.reworkqty.ordinal() ] = pprdReworkqty;
	}

	public String getPprdShiftnoplan() {
		return (String) saveArray[ tableFldConstants.shiftnoplan.ordinal() ];
	}

	public void setPprdShiftnoplan(String pprdShiftnoplan) {
		saveArray[ tableFldConstants.shiftnoplan.ordinal() ] = pprdShiftnoplan;
	}

	public String getPprdHourlynoplan() {
		return (String) saveArray[ tableFldConstants.hourlynoplan.ordinal() ];
	}

	public void setPprdHourlynoplan(String pprdHourlynoplan) {
		saveArray[ tableFldConstants.hourlynoplan.ordinal() ] = pprdHourlynoplan;
	}

	public String getPprdNoplanduration() {
		return (String) saveArray[ tableFldConstants.noplanduration.ordinal() ];
	}

	public void setPprdNoplanduration(String pprdNoplanduration) {
		saveArray[ tableFldConstants.noplanduration.ordinal() ] = pprdNoplanduration;
	}

	public String getPprdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setPprdRemarks(String pprdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = pprdRemarks;
	}

	public String getPprdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPprdTempfield1(String pprdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pprdTempfield1;
	}

	public String getPprdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPprdTempfield2(String pprdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pprdTempfield2;
	}

	public String getPprdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPprdTempfield3(String pprdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = pprdTempfield3;
	}

	public String getPprdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPprdActive(String pprdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pprdActive;
	}

	public String getPprdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPprdCreatedby(String pprdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pprdCreatedby;
	}

	public String getPprdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPprdCreatedon(String pprdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pprdCreatedon;
	}

	public String getPprdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPprdModifiedon(String pprdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pprdModifiedon;
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
	  public static BAL_PcsTlProductiondata fromJson(String json) {
		  //CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  BAL_PcsTlProductiondata abn = new BAL_PcsTlProductiondata();
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
	  
	  public static List<BAL_PcsTlProductiondata> fromJsonList(String json) {

		   // CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		    JSONArray jsonArray = JSONArray.fromObject(json);
		    List<BAL_PcsTlProductiondata> list = new ArrayList<>();

		    for (int i = 0; i < jsonArray.length(); i++) {

		        JSONObject obj = jsonArray.getJSONObject(i);
		        BAL_PcsTlProductiondata abn = new BAL_PcsTlProductiondata();

		        for (tableFldConstants field : tableFldConstants.values()) {
		            String key = field.name();

		            Object valueObj = obj.opt(key);
		           // String val = valueObj.toString();
		            String val = (valueObj == null) ? "" : valueObj.toString();
		            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
		                val = "";
		            }
//		            String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

		            abn.setValue(field, val);
		        }

		        list.add(abn);
		    }

		    return list;
		}

}

