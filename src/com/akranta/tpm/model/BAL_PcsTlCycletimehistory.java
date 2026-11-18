package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PcsTlCycletimemst.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PcsTlCycletimehistory {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, cellid, productid, cycletime, manpower, fromdate, tilldate
		, prodgroupid, machineid, sectionid, factoryid, cavity, mandrels
		, tempfield1, tempfield2, tempfield3, active, createdby, createdon
		, modifiedon
	}

	public BAL_PcsTlCycletimehistory()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	public String getCythKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCythKeyid(String cythKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cythKeyid;
	}

	public String getCythCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setCythCellid(String cythCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = cythCellid;
	}

	public String getCythProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setCythProductid(String cythProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = cythProductid;
	}

	public String getCythCycletime() {
		return (String) saveArray[ tableFldConstants.cycletime.ordinal() ];
	}

	public void setCythCycletime(String cythCycletime) {
		saveArray[ tableFldConstants.cycletime.ordinal() ] = cythCycletime;
	}

	public String getCythManpower() {
		return (String) saveArray[ tableFldConstants.manpower.ordinal() ];
	}

	public void setCythManpower(String cythManpower) {
		saveArray[ tableFldConstants.manpower.ordinal() ] = cythManpower;
	}

	public String getCythFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setCythFromdate(String cythFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = cythFromdate;
	}

	public String getCythTilldate() {
		return (String) saveArray[ tableFldConstants.tilldate.ordinal() ];
	}

	public void setCythTilldate(String cythTilldate) {
		saveArray[ tableFldConstants.tilldate.ordinal() ] = cythTilldate;
	}

	public String getCythProdgroupid() {
		return (String) saveArray[ tableFldConstants.prodgroupid.ordinal() ];
	}

	public void setCythProdgroupid(String cythProdgroupid) {
		saveArray[ tableFldConstants.prodgroupid.ordinal() ] = cythProdgroupid;
	}

	public String getCythMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setCythMachineid(String cythMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = cythMachineid;
	}

	public String getCythSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setCythSectionid(String cythSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = cythSectionid;
	}

	public String getCythFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setCythFactoryid(String cythFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = cythFactoryid;
	}

	public String getCythCavity() {
		return (String) saveArray[ tableFldConstants.cavity.ordinal() ];
	}

	public void setCythCavity(String cythCavity) {
		saveArray[ tableFldConstants.cavity.ordinal() ] = cythCavity;
	}

	public String getCythMandrels() {
		return (String) saveArray[ tableFldConstants.mandrels.ordinal() ];
	}

	public void setCythMandrels(String cythMandrels) {
		saveArray[ tableFldConstants.mandrels.ordinal() ] = cythMandrels;
	}

	public String getCythTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCythTempfield1(String cythTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = cythTempfield1;
	}

	public String getCythTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCythTempfield2(String cythTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cythTempfield2;
	}

	public String getCythTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCythTempfield3(String cythTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cythTempfield3;
	}

	public String getCythActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCythActive(String cythActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cythActive;
	}

	public String getCythCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCythCreatedby(String cythCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cythCreatedby;
	}

	public String getCythCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCythCreatedon(String cythCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cythCreatedon;
	}

	public String getCythModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCythModifiedon(String cythModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cythModifiedon;
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
  
  public static String toJsonManualList(List<BAL_PcsTlCycletimehistory> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
	
	
	public static BAL_PcsTlCycletimehistory fromJson(String json) {
		  //CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  BAL_PcsTlCycletimehistory hst = new BAL_PcsTlCycletimehistory();
		  //CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	//CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        hst.setValue(field, val);
		    	
		    }
		    return hst;
		}
	  
	public static List<BAL_PcsTlCycletimehistory> fromJsonList(String json) {

		   // CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		    JSONArray jsonArray = JSONArray.fromObject(json);
		    List<BAL_PcsTlCycletimehistory> list = new ArrayList<>();

		    for (int i = 0; i < jsonArray.length(); i++) {

		        JSONObject obj = jsonArray.getJSONObject(i);
		        BAL_PcsTlCycletimehistory hst = new BAL_PcsTlCycletimehistory();

		        for (tableFldConstants field : tableFldConstants.values()) {
		            String key = field.name();

		            String val = obj.optString(key, null);
		            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
		                val = "";
		            }

		            hst.setValue(field, val);
		        }

		        list.add(hst);
		    }

		    return list;
		}

}

