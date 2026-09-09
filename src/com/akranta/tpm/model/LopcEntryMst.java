package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class LopcEntryMst{
		private  Object [] saveArray = null;  
		private String elementid;
		public enum   tableFldConstants{
			
			keyid,lopccategoryid,occurrencedatetime,fnlid,employeeid,lopcdesc,identifiedby,
			prepareddatetime,tempfield1,tempfield2,tempfield3,tempfield4,tempfield5,
            active, createdby, createdon, modifiedon
		}

		public LopcEntryMst()
		{
			saveArray = new  Object [ 18 ];
		
		}
		
		public Object[] getSaveArray() {
			return saveArray;
		}
		public void setSaveArray(Object[] saveArray) {
		
			this.saveArray = saveArray;
		}

		public String getLoemKeyid() {
			return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
		}

		public void setLoemKeyid(String loemKeyid) {
			saveArray[ tableFldConstants.keyid.ordinal() ] = loemKeyid;
		}

		public String getLoemLocacategoryid() {
			return (String) saveArray[ tableFldConstants.lopccategoryid.ordinal() ];
		}

		public void setLoemLocacategoryid(String loemLocacategoryid) {
			saveArray[ tableFldConstants.lopccategoryid.ordinal() ] = loemLocacategoryid;
		}

		public String getLoemOccurencedatetime() {
			return (String) saveArray[ tableFldConstants.occurrencedatetime.ordinal() ];
		}

		public void setLoemOccurencedatetime(String loemOccurencedatetime) {
			saveArray[ tableFldConstants.occurrencedatetime.ordinal() ] = loemOccurencedatetime;
		}
		
		public String getLoemFnlid() {
			return (String) saveArray[ tableFldConstants.fnlid.ordinal() ];
		}

		public void setLoemFnlid(String loemFnlid) {
			saveArray[ tableFldConstants.fnlid.ordinal() ] = loemFnlid;
		}

		public String getLoemEmployeeid() {
			return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
		}

		public void setLoemEmployeeid(String loemEmployeeid) {
			saveArray[ tableFldConstants.employeeid.ordinal() ] = loemEmployeeid;
		}

		public String getLoemLopcdesc() {
			return (String) saveArray[ tableFldConstants.lopcdesc.ordinal() ];
		}

		public void setLoemLopcdesc(String loemLopcdesc) {
			saveArray[ tableFldConstants.lopcdesc.ordinal() ] = loemLopcdesc;
		}

		public String getLoemIdentifiedby() {
			return (String) saveArray[ tableFldConstants.identifiedby.ordinal() ];
		}

		public void setLoemIdentifiedby(String loemIdentifiedby) {
			saveArray[ tableFldConstants.identifiedby.ordinal() ] =loemIdentifiedby;
		}

		public String getLoemPrepareddatetime() {
			return (String) saveArray[ tableFldConstants.prepareddatetime.ordinal() ];
		}

		public void setLoemPrepareddatetime(String loemPrepareddatetime) {
			saveArray[ tableFldConstants.prepareddatetime.ordinal() ] = loemPrepareddatetime;
		}

		public String getLoemTempfield1() {
			return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
		}

		public void setLoemTempfield1(String loemTempfield1) {
			saveArray[ tableFldConstants.tempfield1.ordinal() ] =loemTempfield1;
		}
		
		public String getLoemTempfield2() {
			return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
		}

		public void setLoemTempfield2(String loemTempfield2) {
			saveArray[ tableFldConstants.tempfield2.ordinal() ] =loemTempfield2;
		}
		
		public String getLoemTempfield3() {
			return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
		}

		public void setLoemTempfield3(String loemTempfield3) {
			saveArray[ tableFldConstants.tempfield3.ordinal() ] =loemTempfield3;
		}
		
		public String getLoemTempfield4() {
			return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
		}

		public void setLoemTempfield4(String loemTempfield4) {
			saveArray[ tableFldConstants.tempfield4.ordinal() ] =loemTempfield4;
		}
		
		public String getLoemTempfield5() {
			return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
		}

		public void setLoemTempfield5(String loemTempfield5) {
			saveArray[ tableFldConstants.tempfield5.ordinal() ] =loemTempfield5;
		}
		
		public String getLoemActive() {
			return (String) saveArray[ tableFldConstants.active.ordinal() ];
		}

		public void setLoemActive(String loemActive) {
			saveArray[ tableFldConstants.active.ordinal() ] =loemActive;
		}

		public String getLoemCreatedby() {
			return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
		}

		public void setLoemCreatedby(String loemCreatedby) {
			saveArray[ tableFldConstants.createdby.ordinal() ] = loemCreatedby;
		}

		public String getLoemCreatedon() {
			return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
		}

		public void setLoemCreatedon(String loemCreatedon) {
			saveArray[ tableFldConstants.createdon.ordinal() ] = loemCreatedon;
		}

		public String getLoemModifiedon() {
			return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
		}

		public void setLoemModifiedon(String loemModifiedon) {
			saveArray[ tableFldConstants.modifiedon.ordinal() ] =loemModifiedon;
		}
		
		public void setElementid(String elementid) {
			this.elementid = elementid;
		}

		public String getElementid() {
			return elementid;
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
	                if (field.name().equals("keyid") && val == null) {
	                    sb.append("null");
	                } else if (val == null) {
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
	    
	    /**
	     * Create LopcEntryMst object from JSON string
	     */
	    public static LopcEntryMst fromJson(String json) {
	        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	        JSONObject obj = JSONObject.fromObject(json);
	        LopcEntryMst mst = new LopcEntryMst();
	        
	        CommonMessage.debugMsg("RAW JSON Response: " + json);

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();
	            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	            String val = obj.optString(field.name(), null);
	            mst.setValue(field, val != null && val.equals("null") ? null : val);
	        }
	        
	        return mst;
	    }
		

	}

