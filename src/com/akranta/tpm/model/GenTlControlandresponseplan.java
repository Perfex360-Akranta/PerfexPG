package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.QtmTlKnowwhymst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class GenTlControlandresponseplan {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, elementid, processstep, kpov, frequency, whererecorded
		, controllimits, measurementmethod, whomeasures, decisionrule
		, uom, speclimits, samplesize, informto, correctiveaction, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public GenTlControlandresponseplan()
	{
		saveArray = new  Object [ 25 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}



	public String getCarpKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	public void setCarpKeyid(String carpKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = carpKeyid;
	}

    public String getCarpFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setCarpFlid(String carpFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = carpFlid;
	}

	public String getCarpElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setCarpElementid(String carpElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = carpElementid;
	}
	
	public String getCarpProcessstep() {
		return (String) saveArray[ tableFldConstants.processstep.ordinal() ];
	}

	public void setCarpProcessstep(String carpProcessstep) {
		saveArray[ tableFldConstants.processstep.ordinal() ] = carpProcessstep;
	}

	public String getCarpKpov() {
		return (String) saveArray[ tableFldConstants.kpov.ordinal() ];
	}

	public void setCarpKpov(String carpKpov) {
		saveArray[ tableFldConstants.kpov.ordinal() ] = carpKpov;
	}

	public String getCarpFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setCarpFrequency(String carpFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = carpFrequency;
	}

	public String getCarpWhererecorded() {
		return (String) saveArray[ tableFldConstants.whererecorded.ordinal() ];
	}

	public void setCarpWhererecorded(String carpWhererecorded) {
		saveArray[ tableFldConstants.whererecorded.ordinal() ] = carpWhererecorded;
	}

	public String getCarpControllimits() {
		return (String) saveArray[ tableFldConstants.controllimits.ordinal() ];
	}

	public void setCarpControllimits(String carpControllimits) {
		saveArray[ tableFldConstants.controllimits.ordinal() ] = carpControllimits;
	}

	public String getCarpMeasurementmethod() {
		return (String) saveArray[ tableFldConstants.measurementmethod.ordinal() ];
	}

	public void setCarpMeasurementmethod(String carpMeasurementmethod) {
		saveArray[ tableFldConstants.measurementmethod.ordinal() ] = carpMeasurementmethod;
	}

	public String getCarpWhomeasures() {
		return (String) saveArray[ tableFldConstants.whomeasures.ordinal() ];
	}

	public void setCarpWhomeasures(String carpWhomeasures) {
		saveArray[ tableFldConstants.whomeasures.ordinal() ] = carpWhomeasures;
	}

	public String getCarpDecisionrule() {
		return (String) saveArray[ tableFldConstants.decisionrule.ordinal() ];
	}

	public void setCarpDecisionrule(String carpDecisionrule) {
		saveArray[ tableFldConstants.decisionrule.ordinal() ] = carpDecisionrule;
	}

	public String getCarpUom() {
		return (String) saveArray[ tableFldConstants.uom.ordinal() ];
	}

	public void setCarpUom(String carpUom) {
		saveArray[ tableFldConstants.uom.ordinal() ] = carpUom;
	}

	public String getCarpSpeclimits() {
		return (String) saveArray[ tableFldConstants.speclimits.ordinal() ];
	}

	public void setCarpSpeclimits(String carpSpeclimits) {
		saveArray[ tableFldConstants.speclimits.ordinal() ] = carpSpeclimits;
	}

	public String getCarpSamplesize() {
		return (String) saveArray[ tableFldConstants.samplesize.ordinal() ];
	}

	public void setCarpSamplesize(String carpSamplesize) {
		saveArray[ tableFldConstants.samplesize.ordinal() ] = carpSamplesize;
	}

	public String getCarpInformto() {
		return (String) saveArray[ tableFldConstants.informto.ordinal() ];
	}

	public void setCarpInformto(String carpInformto) {
		saveArray[ tableFldConstants.informto.ordinal() ] = carpInformto;
	}

	public String getCarpCorrectiveaction() {
		return (String) saveArray[ tableFldConstants.correctiveaction.ordinal() ];
	}

	public void setCarpCorrectiveaction(String carpCorrectiveaction) {
		saveArray[ tableFldConstants.correctiveaction.ordinal() ] = carpCorrectiveaction;
	}

	public String getCarpTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCarpTempfield1(String carpTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = carpTempfield1;
	}

	public String getCarpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCarpTempfield2(String carpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = carpTempfield2;
	}

	public String getCarpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCarpTempfield3(String carpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = carpTempfield3;
	}

	public String getCarpTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setCarpTempfield4(String carpTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = carpTempfield4;
	}
    
	public String getCarpTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setCarpTempfield5(String carpTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = carpTempfield5;
	}
	
	public String getCarpActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCarpActive(String carpActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = carpActive;
	}

	public String getCarpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCarpCreatedby(String carpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = carpCreatedby;
	}

	public String getCarpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCarpCreatedon(String carpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = carpCreatedon;
	}

	public String getCarpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCarpModifiedon(String carpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = carpModifiedon;
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
  
  public static GenTlControlandresponseplan fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  GenTlControlandresponseplan mst = new GenTlControlandresponseplan();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        //String val = obj.optString(field.name(), null);
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
	        //mst.setValue(field, val != null && val.equals("null") ? null : val);
	    	mst.setValue(field, val);
	    }
	    return mst;
	}
  
  

	
}

