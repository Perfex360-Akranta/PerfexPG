package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class BAL_BdmTlPhenomenamst {

	private List<BAL_BdmTlCausemst> bdmTlCausemst ;
	private List<BAL_BdmTlPhncauselink> phnCauseLink ;
	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, phenomenatype, phenomenaname, shortname, remarks, assemblyid
		, levelno, childflag, isphnnotdefined, causenotneeded,relatedto,tempfield1,tempfield2,tempfield3, active
		, createdby, createdon, modifiedon
	}

	public BAL_BdmTlPhenomenamst()
	{
		setBdmTlCausemst(new ArrayList<BAL_BdmTlCausemst> ());
		setPhnCauseLink(new ArrayList<BAL_BdmTlPhncauselink> ());
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	public List<BAL_BdmTlCausemst> getBdmTlCausemst() {
		return bdmTlCausemst;
	}

	public void setBdmTlCausemst(List<BAL_BdmTlCausemst> bdmTlCausemst) {
		this.bdmTlCausemst = bdmTlCausemst;
	}

	public List<BAL_BdmTlPhncauselink> getPhnCauseLink() {
		return phnCauseLink;
	}

	public void setPhnCauseLink(List<BAL_BdmTlPhncauselink> phnCauseLink) {
		this.phnCauseLink = phnCauseLink;
	}

	public String getBphmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBphmKeyid(String bphmkeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bphmkeyid;
	}

	public String getBphmPhenomenatype() {
		return (String) saveArray[ tableFldConstants.phenomenatype.ordinal() ];
	}

	public void setBphmPhenomenatype(String bphmphenomenatype) {
		saveArray[ tableFldConstants.phenomenatype.ordinal() ] = bphmphenomenatype;
	}

	public String getBphmPhenomenaname() {
		return (String) saveArray[ tableFldConstants.phenomenaname.ordinal() ];
	}

	public void setBphmPhenomenaname(String bphmphenomenaname) {
		saveArray[ tableFldConstants.phenomenaname.ordinal() ] = bphmphenomenaname;
	}

	public String getBphmShortname() {
		return (String) saveArray[ tableFldConstants.shortname.ordinal() ];
	}

	public void setBphmShortname(String bphmshortname) {
		saveArray[ tableFldConstants.shortname.ordinal() ] = bphmshortname;
	}

	public String getBphmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setBphmRemarks(String bphmremarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = bphmremarks;
	}

	public String getBphmAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setBphmAssemblyid(String bphmassemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = bphmassemblyid;
	}

	public String getBphmLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setBphmLevelno(String bphmlevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = bphmlevelno;
	}

	public String getBphmChildflag() {
		return (String) saveArray[ tableFldConstants.childflag.ordinal() ];
	}

	public void setBphmChildflag(String bphmchildflag) {
		saveArray[ tableFldConstants.childflag.ordinal() ] = bphmchildflag;
	}

	public String getBphmIsphnnotdefined() {
		return (String) saveArray[ tableFldConstants.isphnnotdefined.ordinal() ];
	}

	public void setBphmIsphnnotdefined(String bphmisphnnotdefined) {
		saveArray[ tableFldConstants.isphnnotdefined.ordinal() ] = bphmisphnnotdefined;
	}

	public String getBphmCausenotneeded() {
		return (String) saveArray[ tableFldConstants.causenotneeded.ordinal() ];
	}

	public void setBphmCausenotneeded(String bphmcausenotneeded) {
		saveArray[ tableFldConstants.causenotneeded.ordinal() ] = bphmcausenotneeded;
	}
	public String getBphmRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setBphmRelatedto(String bphmrelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = bphmrelatedto;
	}
	public String getTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTempfield1(String bphmtempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = bphmtempfield1;
	}
	public String getTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTempfield2(String bphmtempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = bphmtempfield2;
	}
	public String getTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTempfield3(String bphmtempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = bphmtempfield3;
	}

	public String getBphmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBphmActive(String bphmactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bphmactive;
	}

	public String getBphmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBphmCreatedby(String bphmcreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bphmcreatedby;
	}

	public String getBphmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBphmCreatedon(String bphmcreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bphmcreatedon;
	}

	public String getBphmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBphmModifiedon(String bphmmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bphmmodifiedon;
	}
	
	// added by priyanka 
	
	public void setValue(tableFldConstants field, Object val) {
	    saveArray[field.ordinal()] = val;
	}

	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
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
	            if ("keyid".equals(field.name()) && val == null) {
	                sb.append("null");
	            } else if (val == null || "{}".equals(val.toString())
	                    || "null".equalsIgnoreCase(val.toString())
	                    || "[object Object]".equalsIgnoreCase(val.toString())) {
	                sb.append("\"\"");
	            } else {
	                String value = val.toString()
	                        .replace("\\", "\\\\")
	                        .replace("\"", "\\\"")
	                        .replace("\r", "\\r")
	                        .replace("\n", "\\n");

	                sb.append("\"")
	                  .append(value)
	                  .append("\"");
	            }
	            first = false;
	        }
	    }

	    sb.append("}");
	    return sb.toString();
	}

	public static BAL_BdmTlPhenomenamst fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    BAL_BdmTlPhenomenamst phen = new BAL_BdmTlPhenomenamst();
	    CommonMessage.debugMsg("RAW JSON Response: :" + json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String val = obj.optString(field.name(), null);

	        if (val == null
	                || "null".equalsIgnoreCase(val)
	                || "{}".equals(val)
	                || "[object Object]".equalsIgnoreCase(val)) {

	            val = "";
	        }

	        phen.setValue(field, val);
	    }
	    return phen;
	}

	public static List<BAL_BdmTlPhenomenamst> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<BAL_BdmTlPhenomenamst> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        BAL_BdmTlPhenomenamst phen = new BAL_BdmTlPhenomenamst();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String val = obj.optString(field.name(), null);

	            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)
	                    || "[object Object]".equalsIgnoreCase(val)) {
	                val = "";
	            }

	            phen.setValue(field, val);
	        }

	        list.add(phen);
	    }

	    return list;
	}

	public static String toJsonManualList(List<BAL_BdmTlPhenomenamst> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    if (list != null) {

	        for (int i = 0; i < list.size(); i++) {
	            if (i > 0) {
	                sb.append(",");
	            }
	            BAL_BdmTlPhenomenamst phen = list.get(i);

	            if (phen != null) {
	                sb.append(phen.toJsonManual());
	            } else {
	                sb.append("null");
	            }
	        }
	    }

	    sb.append("]");
	    return sb.toString();
	}

}

