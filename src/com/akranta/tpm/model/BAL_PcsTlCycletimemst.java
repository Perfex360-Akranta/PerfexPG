package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PcsTlCycletimemst {

	private  Object [] saveArray = null;  
	
	private String selectionid;
	public enum   tableFldConstants
	{
		keyid, cellid, productid, cycletime, manpower, fromdate, tilldate
		, prodgroupid, machineid, sectionid, factoryid, cavity, mandrels
		, tempfield1, tempfield2, tempfield3, active, createdby, createdon
		, modifiedon
	}

	public BAL_PcsTlCycletimemst()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		 this.saveArray = saveArray;
	}
	
	public String getCytmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCytmKeyid(String cytmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cytmKeyid;
	}

	public String getCytmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setCytmCellid(String cytmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = cytmCellid;
	}

	public String getCytmProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setCytmProductid(String cytmProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = cytmProductid;
	}

	public String getCytmCycletime() {
		return (String) saveArray[ tableFldConstants.cycletime.ordinal() ];
	}

	public void setCytmCycletime(String cytmCycletime) {
		saveArray[ tableFldConstants.cycletime.ordinal() ] = cytmCycletime;
	}

	public String getCytmManpower() {
		return (String) saveArray[ tableFldConstants.manpower.ordinal() ];
	}

	public void setCytmManpower(String cytmManpower) {
		saveArray[ tableFldConstants.manpower.ordinal() ] = cytmManpower;
	}

	public String getCytmFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setCytmFromdate(String cytmFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = cytmFromdate;
	}

	public String getCytmTilldate() {
		return (String) saveArray[ tableFldConstants.tilldate.ordinal() ];
	}

	public void setCytmTilldate(String cytmTilldate) {
		saveArray[ tableFldConstants.tilldate.ordinal() ] = cytmTilldate;
	}

	public String getCytmProdgroupid() {
		return (String) saveArray[ tableFldConstants.prodgroupid.ordinal() ];
	}

	public void setCytmProdgroupid(String cytmProdgroupid) {
		saveArray[ tableFldConstants.prodgroupid.ordinal() ] = cytmProdgroupid;
	}

	public String getCytmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setCytmMachineid(String cytmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = cytmMachineid;
	}

	public String getCytmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setCytmSectionid(String cytmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = cytmSectionid;
	}

	public String getCytmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setCytmFactoryid(String cytmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = cytmFactoryid;
	}

	public String getCytmCavity() {
		return (String) saveArray[ tableFldConstants.cavity.ordinal() ];
	}

	public void setCytmCavity(String cytmCavity) {
		saveArray[ tableFldConstants.cavity.ordinal() ] = cytmCavity;
	}

	public String getCytmMandrels() {
		return (String) saveArray[ tableFldConstants.mandrels.ordinal() ];
	}

	public void setCytmMandrels(String cytmMandrels) {
		saveArray[ tableFldConstants.mandrels.ordinal() ] = cytmMandrels;
	}

	public String getCytmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCytmTempfield1(String cytmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = cytmTempfield1;
	}

	public String getCytmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCytmTempfield2(String cytmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cytmTempfield2;
	}

	public String getCytmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCytmTempfield3(String cytmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cytmTempfield3;
	}

	public String getCytmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCytmActive(String cytmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cytmActive;
	}

	public String getCytmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCytmCreatedby(String cytmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cytmCreatedby;
	}

	public String getCytmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCytmCreatedon(String cytmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cytmCreatedon;
	}

	public String getCytmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCytmModifiedon(String cytmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cytmModifiedon;
	}

		public void setSelectionid(String selectionid) {
		this.selectionid = selectionid;
	}

	public String getSelectionid() {
		return selectionid;
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
  
  public static String toJsonManualList(List<BAL_PcsTlCycletimemst> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
	
	
	public static BAL_PcsTlCycletimemst fromJson(String json) {
		  //CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  BAL_PcsTlCycletimemst mst = new BAL_PcsTlCycletimemst();
		  //CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	//CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        mst.setValue(field, val);
		    	
		    }
		    return mst;
		}
	  
	public static List<BAL_PcsTlCycletimemst> fromJsonList(String json) {

		   // CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		    JSONArray jsonArray = JSONArray.fromObject(json);
		    List<BAL_PcsTlCycletimemst> list = new ArrayList<>();

		    for (int i = 0; i < jsonArray.length(); i++) {

		        JSONObject obj = jsonArray.getJSONObject(i);
		        BAL_PcsTlCycletimemst mst = new BAL_PcsTlCycletimemst();

		        for (tableFldConstants field : tableFldConstants.values()) {
		            String key = field.name();

		            String val = obj.optString(key, null);
		            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
		                val = "";
		            }

		            mst.setValue(field, val);
		        }

		        list.add(mst);
		    }

		    return list;
		}

}

