package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class FieldAuditSheetmst{


		private  Object [] saveArray = null;  
		private List<FieldAuditSheetdtl> auditDtl ;
		private String elementid;

		public enum   tableFldConstants
		{
			
			keyid,flid,date,jobdesc,shift,serprovider,violations,
			evaluatedby,noofesp,donedmt,donejh,tradeid,tempfield1,tempfield2,tempfield3,tempfield4,tempfield5,
            active, createdby, createdon, modifiedon
		}

		public FieldAuditSheetmst()
		{
			saveArray = new  Object [ 21 ];
		
		}
		
		public Object[] getSaveArray() {
			return saveArray;
		}
		public void setSaveArray(Object[] saveArray) {
		
			this.saveArray = saveArray;
		}
		public Object getValue(tableFldConstants field) {
		    return saveArray[field.ordinal()];
		}

		public void setValue(tableFldConstants field, Object value) {
		    saveArray[field.ordinal()] = value;
		}
		public List<FieldAuditSheetdtl> getAuditDtl() {
			return auditDtl;
		}

		public void setAuditDtl(List<FieldAuditSheetdtl> fieldAuditSheetdtl) {
			this.auditDtl = fieldAuditSheetdtl;
		}

		public String getFasmKeyid() {
			return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
		}

		public void setFasmKeyid(String fasmKeyid) {
			saveArray[ tableFldConstants.keyid.ordinal() ] = fasmKeyid;
		}

		public String getFasmFlid() {
			return (String) saveArray[ tableFldConstants.flid.ordinal() ];
		}

		public void setFasmFlid(String fasmFlid) {
			saveArray[ tableFldConstants.flid.ordinal() ] = fasmFlid;
		}

		public String getFasmDate() {
			return (String) saveArray[ tableFldConstants.date.ordinal() ];
		}

		public void setFasmDate(String fasmDate) {
			saveArray[ tableFldConstants.date.ordinal() ] = fasmDate;
		}
		
		public String getFasmJobdesc() {
			return (String) saveArray[ tableFldConstants.jobdesc.ordinal() ];
		}

		public void setFasmJobdesc(String fasmJobdesc) {
			saveArray[ tableFldConstants.jobdesc.ordinal() ] = fasmJobdesc;
		}

		public String getFasmShift() {
			return (String) saveArray[ tableFldConstants.shift.ordinal() ];
		}

		public void setFasmShift(String fasmShift) {
			saveArray[ tableFldConstants.shift.ordinal() ] = fasmShift;
		}

		public String getFasmSerprovider() {
			return (String) saveArray[ tableFldConstants.serprovider.ordinal() ];
		}

		public void setFasmSerprovider(String fasmSerprovider) {
			saveArray[ tableFldConstants.serprovider.ordinal() ] = fasmSerprovider;
		}

		public String getFasmViolations() {
			return (String) saveArray[ tableFldConstants.violations.ordinal() ];
		}

		public void setFasmViolations(String fasmViolations) {
			saveArray[ tableFldConstants.violations.ordinal() ] =fasmViolations;
		}

		public String getFasmEvaluatedby() {
			return (String) saveArray[ tableFldConstants.evaluatedby.ordinal() ];
		}

		public void setFasmEvaluatedby(String fasmEvaluatedby) {
			saveArray[ tableFldConstants.evaluatedby.ordinal() ] = fasmEvaluatedby;
		}

		public String getFasmNoofesp() {
			return (String) saveArray[ tableFldConstants.noofesp.ordinal() ];
		}

		public void setFasmNoofesp(String fasmNoofesp) {
			saveArray[ tableFldConstants.noofesp.ordinal() ] =fasmNoofesp;
		}
		
		public String getFasmDonedmt() {
			return (String) saveArray[ tableFldConstants.donedmt.ordinal() ];
		}

		public void setFasmDonedmt(String fasmDonedmt) {
			saveArray[ tableFldConstants.donedmt.ordinal() ] =fasmDonedmt;
		}
		public String getFasmDonejh() {
			return (String) saveArray[ tableFldConstants.donejh.ordinal() ];
		}

		public void setFasmDonejh(String fasmDonejh) {
			saveArray[ tableFldConstants.donejh.ordinal() ] =fasmDonejh;
		}
		public String getFasmTradeid() {
			return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
		}

		public void setFasmTradeid(String fasmTradeid) {
			saveArray[ tableFldConstants.tradeid.ordinal() ] =fasmTradeid;
		}
		
		public String getFasmTempfield1() {
			return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
		}

		public void setFasmTempfield1(String fasmTempfield1) {
			saveArray[ tableFldConstants.tempfield1.ordinal() ] = fasmTempfield1;
		}
		
		public String getFasmTempfield2() {
			return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
		}

		public void setFasmTempfield2(String fasmTempfield2) {
			saveArray[ tableFldConstants.tempfield2.ordinal() ] = fasmTempfield2;
		}
                    public String getFasmTempfield3() {
			return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
		}

		public void setFasmTempfield3(String fasmTempfield3) {
			saveArray[ tableFldConstants.tempfield3.ordinal() ] = fasmTempfield3;
		}
                public String getFasmTempfield4() {
			return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
		}

		public void setFasmTempfield4(String fasmTempfield4) {
			saveArray[ tableFldConstants.tempfield4.ordinal() ] = fasmTempfield4;
		}
       public String getFasmTempfield5() {
			return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
		}

		public void setFasmTempfield5(String fasmTempfield5) {
			saveArray[ tableFldConstants.tempfield5.ordinal() ] = fasmTempfield5;
		}
		public String getFasmActive() {
			return (String) saveArray[ tableFldConstants.active.ordinal() ];
		}

		public void setFasmActive(String fasmActive) {
			saveArray[ tableFldConstants.active.ordinal() ] =fasmActive;
		}

		public String getFasmCreatedby() {
			return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
		}

		public void setFasmCreatedby(String fasmCreatedby) {
			saveArray[ tableFldConstants.createdby.ordinal() ] = fasmCreatedby;
		}

		public String getFasmCreatedon() {
			return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
		}

		public void setFasmCreatedon(String fasmCreatedon) {
			saveArray[ tableFldConstants.createdon.ordinal() ] = fasmCreatedon;
		}

		public String getFasmModifiedon() {
			return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
		}

		public void setFasmModifiedon(String fasmModifiedon) {
			saveArray[ tableFldConstants.modifiedon.ordinal() ] =fasmModifiedon;
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
	  
	  public static FieldAuditSheetmst fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  FieldAuditSheetmst mst = new FieldAuditSheetmst();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        mst.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return mst;
		}

	}

