package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlControlandresponseplan.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class KznTlKaizenbankmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, kaizen, benefit, targetdate, pqcdsme
		, suggestedby, responsibility, completedon, status, accrejremarks,acrejby,
		implementedby,verifyremarks,impremarks,compremarks,acceptrejon,implementedon,
		verifiedon,verifiedby,completedby,ehsrelated,ehsstatus,refdoctype,refdocno,
		others,implementcost,approvalflag,mocrequired,active,createdby, createdon, modifiedon,espsname,
		mocitem,tempfield2,tempfield3,nonjhesp
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	public KznTlKaizenbankmst()
	{
		saveArray = new  Object [ 40 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getKzbnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKzbnKeyid(String kzbnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kzbnKeyid;
	}

	public String getKzbnFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setKzbnFlid(String kzbnFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = kzbnFlid;
	}

	public String getKzbnElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setKzbnElementid(String kzbnElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = kzbnElementid;
	}

	public String getKzbnDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setKzbnDate(String kzbnDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = kzbnDate;
	}

	public String getKzbnKaizen() {
		return (String) saveArray[ tableFldConstants.kaizen.ordinal() ];
	}

	public void setKzbnKaizen(String kzbnKaizen) {
		saveArray[ tableFldConstants.kaizen.ordinal() ] = kzbnKaizen;
	}

	public String getKzbnBenefit() {
		return (String) saveArray[ tableFldConstants.benefit.ordinal() ];
	}

	public void setKzbnBenefit(String kzbnBenefit) {
		saveArray[ tableFldConstants.benefit.ordinal() ] = kzbnBenefit;
	}

	public String getKzbnTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setKzbnTargetdate(String kzbnTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = kzbnTargetdate;
	}

	public String getKzbnPqcdsme() {
		return (String) saveArray[ tableFldConstants.pqcdsme.ordinal() ];
	}

	public void setKzbnPqcdsme(String kzbnPqcdsme) {
		saveArray[ tableFldConstants.pqcdsme.ordinal() ] = kzbnPqcdsme;
	}

	public String getKzbnSuggestedby() {
		return (String) saveArray[ tableFldConstants.suggestedby.ordinal() ];
	}

	public void setKzbnSuggestedby(String kzbnSuggestedby) {
		saveArray[ tableFldConstants.suggestedby.ordinal() ] = kzbnSuggestedby;
	}

	public String getKzbnResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setKzbnResponsibility(String kzbnResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = kzbnResponsibility;
	}

	public String getKzbnCompletedon() {
		return (String) saveArray[ tableFldConstants.completedon.ordinal() ];
	}

	public void setKzbnCompletedon(String kzbnCompletedon) {
		saveArray[ tableFldConstants.completedon.ordinal() ] = kzbnCompletedon;
	}

	public String getKzbnStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setKzbnStatus(String kzbnStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = kzbnStatus;
	}

	public String getKzbnAccrejremarks() {
		return (String) saveArray[ tableFldConstants.accrejremarks.ordinal() ];
	}

	public void setKzbnAccrejremarks(String kzbnAccrejremarks) {
		saveArray[ tableFldConstants.accrejremarks.ordinal() ] = kzbnAccrejremarks;
	}

	public String getKzbnAcrejby() {
		return (String) saveArray[ tableFldConstants.acrejby.ordinal() ];
	}

	public void setKzbnAcrejby(String kzbnAcrejby) {
		saveArray[ tableFldConstants.acrejby.ordinal() ] = kzbnAcrejby;
	}

	public String getKzbnImplementedby() {
		return (String) saveArray[ tableFldConstants.implementedby.ordinal() ];
	}

	public void setKzbnImplementedby(String kzbnImplementedby) {
		saveArray[ tableFldConstants.implementedby.ordinal() ] = kzbnImplementedby;
	}
	
	public String getKzbnVerifyremarks() {
		return (String) saveArray[ tableFldConstants.verifyremarks.ordinal() ];
	}

	public void setKzbnVerifyremarks(String kzbnVerifyremarks) {
		saveArray[ tableFldConstants.verifyremarks.ordinal() ] = kzbnVerifyremarks;
	}
	
	public String getKzbnImpremarks() {
		return (String) saveArray[ tableFldConstants.impremarks.ordinal() ];
	}

	public void setKzbnImpremarks(String kzbnImpremarks) {
		saveArray[ tableFldConstants.impremarks.ordinal() ] = kzbnImpremarks;
	}
	
	public String getKzbnCompremarks() {
		return (String) saveArray[ tableFldConstants.compremarks.ordinal() ];
	}

	public void setKzbnCompremarks(String kzbnCompremarks) {
		saveArray[ tableFldConstants.compremarks.ordinal() ] = kzbnCompremarks;
	}
	
	public String getKzbnAcceptrejon() {
		return (String) saveArray[ tableFldConstants.acceptrejon.ordinal() ];
	}

	public void setKzbnAcceptrejon(String kzbnAcceptrejon) {
		saveArray[ tableFldConstants.acceptrejon.ordinal() ] = kzbnAcceptrejon;
	}
	
	public String getKzbnImplementedon() {
		return (String) saveArray[ tableFldConstants.implementedon.ordinal() ];
	}

	public void setKzbnImplementedon(String kzbnImplementedon) {
		saveArray[ tableFldConstants.implementedon.ordinal() ] = kzbnImplementedon;
	}
	
	public String getKzbnVerifiedon() {
		return (String) saveArray[ tableFldConstants.verifiedon.ordinal() ];
	}

	public void setKzbnVerifiedon(String kzbnVerifiedon) {
		saveArray[ tableFldConstants.verifiedon.ordinal() ] = kzbnVerifiedon;
	}
	
	public String getKzbnVerifiedby() {
		return (String) saveArray[ tableFldConstants.verifiedby.ordinal() ];
	}

	public void setKzbnVerifiedby(String kzbnVerifiedby) {
		saveArray[ tableFldConstants.verifiedby.ordinal() ] = kzbnVerifiedby;
	}
	
	public String getKzbnCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setKzbnCompletedby(String kzbnCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = kzbnCompletedby;
	}
	
	public String getKzbnCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKzbnCreatedby(String kzbnCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kzbnCreatedby;
	}

	public String getKzbnEhsrelated() {
		return (String) saveArray[ tableFldConstants.ehsrelated.ordinal() ];
	}

	public void setKzbnEhsrelated(String kzbnEhsrelated) {
		saveArray[ tableFldConstants.ehsrelated.ordinal() ] = kzbnEhsrelated;
	}
	
	public String getKzbnEhsstatus() {
		return (String) saveArray[ tableFldConstants.ehsstatus.ordinal() ];
	}

	public void setKzbnEhsstatus(String kzbnTempfield1) {
		saveArray[ tableFldConstants.ehsstatus.ordinal() ] = kzbnTempfield1;
	}
	
	public String getKzbnRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setKzbnRefdoctype(String kzbnRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = kzbnRefdoctype;
	}
	
	public String getKzbnRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setKzbnRefdocno(String kzbnRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = kzbnRefdocno;
	}
	
	public String getkzbnOthers() {
		return (String) saveArray[ tableFldConstants.others.ordinal() ];
	}

	public void setkzbnOthers(String kzbnOthers) {  
		saveArray[ tableFldConstants.others.ordinal() ] = kzbnOthers;
	}
	
	public String getKzbnImplementcost() {
		return (String) saveArray[ tableFldConstants.implementcost.ordinal() ];
	}

	public void setKzbnImplementcost(String kzbnImplementcost) {
		saveArray[ tableFldConstants.implementcost.ordinal() ] = kzbnImplementcost;
	}
	
	public String getKzbnApprovalflag() {
		return (String) saveArray[ tableFldConstants.approvalflag.ordinal() ];
	}

	public void getKzbnApprovalflag(String kzbnApprovalflag) {
		saveArray[ tableFldConstants.approvalflag.ordinal() ] = kzbnApprovalflag;
	}
	
	public String getKzbnmocrequired() {
		return (String) saveArray[ tableFldConstants.mocrequired.ordinal() ];
	}

	public void setKzbnmocrequired(String kzbnmocrequired) {
		saveArray[ tableFldConstants.mocrequired.ordinal() ] = kzbnmocrequired;
	}

	public String getKzbnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKzbnActive(String kzbnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kzbnActive;
	}
	
	public String getKzbnCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKzbnCreatedon(String kzbnCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kzbnCreatedon;
	}

	public String getKzbnModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKzbnModifiedon(String kzbnModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kzbnModifiedon;
	}
	
	public String getKzbnEspName() {
		return (String) saveArray[ tableFldConstants.espsname.ordinal() ];
	}

	public void setKzbnEspName(String KzbnEspName) {
		saveArray[ tableFldConstants.espsname.ordinal() ] = KzbnEspName;
	}
	public String getKzbnNonJhEsp() {
		return (String) saveArray[ tableFldConstants.nonjhesp.ordinal() ];
	}

	public void setKzbnNonJhEsp(String kzbnNonJhEsp) {
		saveArray[ tableFldConstants.nonjhesp.ordinal() ] = kzbnNonJhEsp;
	}
	public String getKzbnMocitem() {
		return (String) saveArray[ tableFldConstants.mocitem.ordinal() ];
	}

	public void setKzbnMocitem(String mocitem) {
		saveArray[ tableFldConstants.mocitem.ordinal() ] = mocitem;
	}
	public String getKzbnTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKzbnTempfield2(String tempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tempfield2;
	}
	public String getKzbnTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKzbnTempfield3(String tempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tempfield3;
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
  
  public static  KznTlKaizenbankmst fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlKaizenbankmst mst = new KznTlKaizenbankmst();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	    	Object valueObj = obj.opt(field.name());
	       // String val = obj.optString(field.name(), null);
	        String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
            	val = "";
            }
	        mst.setValue(field, val);
	    	
	    }
	    return mst;
	}
  
  
  public static String toJsonManualList(List<KznTlKaizenbankmst> kznTlKaizenbankmst) {
		StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < kznTlKaizenbankmst.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(kznTlKaizenbankmst.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}

	/*
	 * public static List<KznTlKaizenbankmst> fromJsonList(String jsonResponse) { //
	 * TODO Auto-generated method stub return null; }
	 */
	public static List<KznTlKaizenbankmst> fromJsonList(String jsonResponse) {
	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + jsonResponse);

	    JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
	    List<KznTlKaizenbankmst> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        KznTlKaizenbankmst kznBnkMst = new KznTlKaizenbankmst();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            kznBnkMst.setValue(field, val);
	        }

	        list.add(kznBnkMst);
	    }

	    return list;
	}
  
  
	
}

