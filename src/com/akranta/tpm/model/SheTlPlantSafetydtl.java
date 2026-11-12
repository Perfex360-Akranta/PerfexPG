
package com.akranta.tpm.model;


import java.util.List;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class SheTlPlantSafetydtl {

private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, spsd_keyid, observe, type,relatedto, recomaction, correcaction, preventiveaction
		, responsibilty, targetdate,reponseremarks, status, actiontaken, completedby
		,completedate,completeremarks,verified,verifiedby,verifieddate,verifeidremarks,tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon,reviseddate
	}
	
	public SheTlPlantSafetydtl()
	{
		saveArray = new  Object [ 30];
	}
  
	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] dataArr) {
		 this.saveArray = dataArr;
	}
	public String getSpsdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSpsdKeyid(String spsdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = spsdKeyid;
	}

	public String getSpsdSpsmKeyid() {
		return (String) saveArray[ tableFldConstants.spsd_keyid.ordinal() ];
	}

	public void setSpsdSpsmKeyid(String spsdSdamKeyid) {
		saveArray[ tableFldConstants.spsd_keyid.ordinal() ] = spsdSdamKeyid;
	}

	public String getSpsdObserve() {
		return (String) saveArray[ tableFldConstants.observe.ordinal() ];
	}

	public void setSpsdObserve(String spsdObserve) {
		saveArray[ tableFldConstants.observe.ordinal() ] = spsdObserve;
	}

	public String getSpsdType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setSpsdType(String spsdType) {
		saveArray[ tableFldConstants.type.ordinal() ] = spsdType;
	}
	
	
	public String getSpsdRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setSpsdRelatedto(String spsdRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = spsdRelatedto;
	}
	
	
	
	public String getSpsdRecomAction() {
		return (String) saveArray[ tableFldConstants.recomaction.ordinal() ];
	}

	public void setSpsdRecomAction(String spsdRecomaction) {
		saveArray[ tableFldConstants.recomaction.ordinal() ] = spsdRecomaction;
	}

	public String getSpsdCorrectAction() {
		return (String) saveArray[ tableFldConstants.correcaction.ordinal() ];
	}

	public void setSpsdCorrectAction(String spsdCorrectaction) {
		saveArray[ tableFldConstants.correcaction.ordinal() ] = spsdCorrectaction;
	}

	public String getSpsdPreventiveAction() {
		return (String) saveArray[ tableFldConstants.preventiveaction.ordinal() ];
	}

	public void setSpsdPreventiveAction(String spsdPreventiveaction) {
		saveArray[ tableFldConstants.preventiveaction.ordinal() ] = spsdPreventiveaction;
	}

	public String getSpsdResponsibilty() {
		return (String) saveArray[ tableFldConstants.responsibilty.ordinal() ];
	}

	public void setSpsdResponsibilty(String spsdResponsibilty) {
		saveArray[ tableFldConstants.responsibilty.ordinal() ] = spsdResponsibilty;
	}
   
	public String getSpsdResponRemarks() {
		return (String) saveArray[ tableFldConstants.reponseremarks.ordinal() ];
	}

	public void setSpsdResponRemarks(String spsdResponRemarks) {
		saveArray[ tableFldConstants.reponseremarks.ordinal() ] = spsdResponRemarks;
	}

	public String getSpsdTargetDate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setSpsdTargetDate(String spsdTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = spsdTargetdate;
	}

	public String getSpsdStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setSpsdStatus(String spsdStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = spsdStatus;
	}

	public String getSpsdActiontaken() {
		return (String) saveArray[ tableFldConstants.actiontaken.ordinal() ];
	}

	public void setSpsdActiontaken(String spsdActiontaken) {
		saveArray[ tableFldConstants.actiontaken.ordinal() ] = spsdActiontaken;
	}

	public String getSpsdCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setSpsdCompletedby(String spsdcompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = spsdcompletedby;
	}

	public String getSpsdCompletedate() {
		return (String) saveArray[ tableFldConstants.completedate.ordinal() ];
	}

	public void setSpsdCompletedate(String spsdCompletedate) {
		saveArray[ tableFldConstants.completedate.ordinal() ] = spsdCompletedate;
	}
	
	
	public String getSpsdCompleteRemarks() {
		return (String) saveArray[ tableFldConstants.completeremarks.ordinal() ];
	}

	public void setSpsdCompleteRemarks(String spsdCompleteRemarks) {
		saveArray[ tableFldConstants.completeremarks.ordinal() ] = spsdCompleteRemarks;
	}
	
	
	public String getSpsdVerified() {
		return (String) saveArray[ tableFldConstants.verified.ordinal() ];
	}

	public void setSpsdVerified(String spsdVerified) {
		saveArray[ tableFldConstants.verified.ordinal() ] = spsdVerified;
	}
	
	
	public String getSpsdVerifiedBy() {
		return (String) saveArray[ tableFldConstants.verifiedby.ordinal() ];
	}

	public void setSpsdVerifiedBy(String spsdVerifiedby) {
		saveArray[ tableFldConstants.verifiedby.ordinal() ] = spsdVerifiedby;
	}
	
	
	
	public String getSpsdVerifiedDate() {
		return (String) saveArray[ tableFldConstants.verifieddate.ordinal() ];
	}

	public void   setSpsdVerifiedDate(String spsdVerifieddate) {
		saveArray[ tableFldConstants.verifieddate.ordinal() ] = spsdVerifieddate;
	}
    
	public String getSpsdVerifiedRemarks() {
		return (String) saveArray[ tableFldConstants.verifeidremarks.ordinal() ];
	}

	public void setSpsdVerifiedRemarks(String spsdVerifiedRemarks) {
		saveArray[ tableFldConstants.verifeidremarks.ordinal() ] = spsdVerifiedRemarks;
	}
	public String getSpsdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSpsdTempfield1(String spsdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = spsdTempfield1;
	}

	public String getSpsdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSpsdTempfield2(String spsdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = spsdTempfield2;
	}

	public String getSpsdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSpsdTempfield3(String spsdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = spsdTempfield3;
	}

	public String getSpsdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSpsdTempfield4(String spsdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = spsdTempfield4;
	}

	public String getSpsdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSpsdTempfield5(String spsdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = spsdTempfield5;
	}

	public String getSpsdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSpsdActive(String spsdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = spsdActive;
	}

	public String getSpsdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSpsdCreatedby(String spsdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = spsdCreatedby;
	}

	public String getSpsdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSpsdCreatedon(String spsdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = spsdCreatedon;
	}

	public String getSpsdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSpsdModifiedon(String spsdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = spsdModifiedon;
	}

	public String getSpsdReviseddate() {
		return (String) saveArray[ tableFldConstants.reviseddate.ordinal() ];
	}

	public void setSpsdReviseddate(String spsdReviseddate) {
		saveArray[ tableFldConstants.reviseddate.ordinal() ] = spsdReviseddate;
	}	
	
	public void setValue(tableFldConstants field, String value) {
        if (field != null) saveArray[field.ordinal()] = value;
    }

    public String toJsonManual() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (tableFldConstants field : tableFldConstants.values()) {
            int idx = field.ordinal();
            if (idx < saveArray.length) {
                if (!first) sb.append(",");
                sb.append("\"").append(field.name()).append("\":");
                Object val = saveArray[idx];
                if (field == tableFldConstants.keyid && val == null) {
                    sb.append("null");
                } else if (val == null) {
                    sb.append("\"{}\"");
                } else {
                    sb.append("\"").append(val).append("\"");
                }
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static SheTlPlantSafetydtl fromJson(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        SheTlPlantSafetydtl dtl = new SheTlPlantSafetydtl();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            dtl.setValue(field, val != null && val.equals("null") ? null : val);
        }
        return dtl;
    }

    public static List<SheTlPlantSafetydtl> fromJsonList(String json) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<SheTlPlantSafetydtl> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            SheTlPlantSafetydtl dtl = new SheTlPlantSafetydtl();
            for (tableFldConstants field : tableFldConstants.values()) {
                Object valueObj = obj.opt(field.name());
                String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
                dtl.setValue(field, val);
            }
            list.add(dtl);
        }
        return list;
    }

    public static String toJsonManualList(List<SheTlPlantSafetydtl> list) {
        StringBuilder sb = new StringBuilder("[");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(list.get(i).toJsonManual());
            }
        }
        sb.append("]");
        return sb.toString();
    }
	
}