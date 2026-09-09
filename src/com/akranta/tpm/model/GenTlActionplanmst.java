package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class GenTlActionplanmst {

	private  Object [] saveArray = null;  
	private  GenTlActionplandtl genTlActionplandtl= null;  
	private  List<GenTlActionplandtl> genTlActionplandtlList= null; 
	private String IsActionplan;
    private GenTlActionplandtl isActionPlanKey;
    private List<GenTlMomdtl> MomddtlKeyid;
	
	public enum   tableFldConstants
	{
		keyid, masterrefid, detailrefid, refdoctype, flid, elementid
		, maintask, pillarid, status, remarks, plandate, tempfiled2
		, tempfiled3, tempfiled4, tempfiled5, active, createdby, createdon
		, modifiedon
	}

	public GenTlActionplanmst()
	{
		saveArray = new  Object [ 19 ];
	}
	public void setGenTlActionplandtlList(List<GenTlActionplandtl> genTlActionplandtlList) {
		this.genTlActionplandtlList = genTlActionplandtlList;
	}

	public List<GenTlActionplandtl> getGenTlActionplandtlList() {
		return genTlActionplandtlList;
	}
	public void setGenTlActionplandtl(GenTlActionplandtl genTlActionplandtl) {
		this.genTlActionplandtl = genTlActionplandtl;
	}
	public GenTlActionplandtl getGenTlActionplandtl() {
		return genTlActionplandtl;
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		 this.saveArray = saveArray;
	}
	public String getAplmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAplmKeyid(String aplmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = aplmKeyid;
	}

	public String getAplmMasterrefid() {
		return (String) saveArray[ tableFldConstants.masterrefid.ordinal() ];
	}

	public void setAplmMasterrefid(String aplmMasterrefid) {
		saveArray[ tableFldConstants.masterrefid.ordinal() ] = aplmMasterrefid;
	}

	public String getAplmDetailrefid() {
		return (String) saveArray[ tableFldConstants.detailrefid.ordinal() ];
	}

	public void setAplmDetailrefid(String aplmDetailrefid) {
		saveArray[ tableFldConstants.detailrefid.ordinal() ] = aplmDetailrefid;
	}

	public String getAplmRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setAplmRefdoctype(String aplmRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = aplmRefdoctype;
	}

	public String getAplmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setAplmFlid(String aplmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = aplmFlid;
	}

	public String getAplmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setAplmElementid(String aplmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = aplmElementid;
	}

	public String getAplmMaintask() {
		return (String) saveArray[ tableFldConstants.maintask.ordinal() ];
	}

	public void setAplmMaintask(String aplmMaintask) {
		saveArray[ tableFldConstants.maintask.ordinal() ] = aplmMaintask;
	}

	public String getAplmPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setAplmPillarid(String aplmPillarid) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = aplmPillarid;
	}

	public String getAplmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setAplmStatus(String aplmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = aplmStatus;
	}

	public String getAplmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setAplmRemarks(String aplmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = aplmRemarks;
	}

	public String getAplmPlandate() {
		return (String) saveArray[ tableFldConstants.plandate.ordinal() ];
	}

	public void setAplmPlandate(String aplmPlandate) {
		saveArray[ tableFldConstants.plandate.ordinal() ] = aplmPlandate;
	}

	public String getAplmTempfiled2() {
		return (String) saveArray[ tableFldConstants.tempfiled2.ordinal() ];
	}

	public void setAplmTempfiled2(String aplmTempfiled2) {
		saveArray[ tableFldConstants.tempfiled2.ordinal() ] = aplmTempfiled2;
	}

	public String getAplmTempfiled3() {
		return (String) saveArray[ tableFldConstants.tempfiled3.ordinal() ];
	}

	public void setAplmTempfiled3(String aplmTempfiled3) {
		saveArray[ tableFldConstants.tempfiled3.ordinal() ] = aplmTempfiled3;
	}

	public String getAplmTempfiled4() {
		return (String) saveArray[ tableFldConstants.tempfiled4.ordinal() ];
	}

	public void setAplmTempfiled4(String aplmTempfiled4) {
		saveArray[ tableFldConstants.tempfiled4.ordinal() ] = aplmTempfiled4;
	}

	public String getAplmTempfiled5() {
		return (String) saveArray[ tableFldConstants.tempfiled5.ordinal() ];
	}

	public void setAplmTempfiled5(String aplmTempfiled5) {
		saveArray[ tableFldConstants.tempfiled5.ordinal() ] = aplmTempfiled5;
	}

	public String getAplmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAplmActive(String aplmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = aplmActive;
	}

	public String getAplmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAplmCreatedby(String aplmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = aplmCreatedby;
	}

	public String getAplmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAplmCreatedon(String aplmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = aplmCreatedon;
	}

	public String getAplmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAplmModifiedon(String aplmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = aplmModifiedon;
	}
	
	
	
	public void setIsActionplan(String IsActionplan) {
		this.IsActionplan = IsActionplan;
	}
	public String getIsActionplan() {
		return IsActionplan;
	}
	public GenTlActionplandtl getisActionPlanKey() {
		return isActionPlanKey;
	}
public void setisActionPlanKey(GenTlActionplandtl isActionPlanKey){
		this.isActionPlanKey= isActionPlanKey;
	}
public void setMomddtlKeyid(List<GenTlMomdtl> MomddtlKeyid) {
	this.MomddtlKeyid = MomddtlKeyid;
}

public List<GenTlMomdtl> getMomddtlKeyid() {
	return MomddtlKeyid;
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




public static GenTlActionplanmst fromJson(String json) {
  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

  JSONObject obj = JSONObject.fromObject(json);

  GenTlActionplanmst abn = new GenTlActionplanmst();
  CommonMessage.debugMsg("RAW JSON Response: :"+json);

    for (tableFldConstants field : tableFldConstants.values()) {
    	String key = field.name();
    	
    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
        String val = obj.optString(field.name(), null);
        abn.setValue(field, val != null && val.equals("null") ? null : val);
    	
    }
    return abn;
}

public static List<GenTlActionplanmst> fromJsonList(String json) {

    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

    JSONArray jsonArray = JSONArray.fromObject(json);
    List<GenTlActionplanmst> list = new ArrayList<>();

    for (int i = 0; i < jsonArray.length(); i++) {

        JSONObject obj = jsonArray.getJSONObject(i);
        GenTlActionplanmst abn = new GenTlActionplanmst();

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

