package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.QtmTlIntrejectionmst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KznTlBestmst {

	private  Object [] saveArray = null;  
	private List<KznTlBestdtl> kznTlBestdtl ;
	public enum   tableFldConstants
	{
		keyid, flid, employeeid, date, month, level, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public KznTlBestmst()
	{
		setKznTlBestdtl(new ArrayList<KznTlBestdtl> ());
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	public String getKzbmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKzbmKeyid(String kzbmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kzbmKeyid;
	}

	public String getKzbmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setKzbmFlid(String kzbmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = kzbmFlid;
	}

	public String getKzbmEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setKzbmEmployeeid(String kzbmEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = kzbmEmployeeid;
	}

	public String getKzbmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setKzbmDate(String kzbmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = kzbmDate;
	}

	public String getKzbmMonth() {
		return (String) saveArray[ tableFldConstants.month.ordinal() ];
	}

	public void setKzbmMonth(String kzbmMonth) {
		saveArray[ tableFldConstants.month.ordinal() ] = kzbmMonth;
	}

	public String getKzbmLevel() {
		return (String) saveArray[ tableFldConstants.level.ordinal() ];
	}

	public void setKzbmLevel(String kzbmLevel) {
		saveArray[ tableFldConstants.level.ordinal() ] = kzbmLevel;
	}

	public String getKzbmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKzbmTempfield1(String kzbmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kzbmTempfield1;
	}

	public String getKzbmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKzbmTempfield2(String kzbmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kzbmTempfield2;
	}

	public String getKzbmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKzbmTempfield3(String kzbmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kzbmTempfield3;
	}

	public String getKzbmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKzbmTempfield4(String kzbmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kzbmTempfield4;
	}

	public String getKzbmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKzbmTempfield5(String kzbmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kzbmTempfield5;
	}

	public String getKzbmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKzbmActive(String kzbmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kzbmActive;
	}

	public String getKzbmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKzbmCreatedby(String kzbmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kzbmCreatedby;
	}

	public String getKzbmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKzbmCreatedon(String kzbmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kzbmCreatedon;
	}

	public String getKzbmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKzbmModifiedon(String kzbmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kzbmModifiedon;
	}
	public void setKznTlBestdtl(List<KznTlBestdtl> kznTlBestdtl) {
		this.kznTlBestdtl = kznTlBestdtl;
	}

	public List<KznTlBestdtl> getKznTlBestdtl() {
		return kznTlBestdtl;
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
	  
	  public static KznTlBestmst fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  KznTlBestmst mst = new KznTlBestmst();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        mst.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return mst;
		}

}

