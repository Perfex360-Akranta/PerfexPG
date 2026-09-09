package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlIntrejectiondtl {

	private  Object [] saveArray = null;  
	private List<QtmTlIntrejectiondtl> qtmTlIntrejectiondtl ;

	public enum   tableFldConstants
	{
		keyid, prodfamilyid, processid, phenomenaid, causeid, quantity
		, m4type, type, backlogflag, referenceid, remarks, wwmasterid
		, entrytype, masterid, qhb_keyid, plrk_keyid, subprocessid, qty
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public QtmTlIntrejectiondtl()
	{
		saveArray = new  Object [ 24 ];
		setQtmTlIntrejectiondtl(new ArrayList<QtmTlIntrejectiondtl> ());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public String getQirdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQirdKeyid(String qirdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qirdKeyid;
	}

	public String getQirdProdfamilyid() {
		return (String) saveArray[ tableFldConstants.prodfamilyid.ordinal() ];
	}

	public void setQirdProdfamilyid(String qirdProdfamilyid) {
		saveArray[ tableFldConstants.prodfamilyid.ordinal() ] = qirdProdfamilyid;
	}

	public String getQirdProcessid() {
		return (String) saveArray[ tableFldConstants.processid.ordinal() ];
	}

	public void setQirdProcessid(String qirdProcessid) {
		saveArray[ tableFldConstants.processid.ordinal() ] = qirdProcessid;
	}

	public String getQirdPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setQirdPhenomenaid(String qirdPhenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = qirdPhenomenaid;
	}

	public String getQirdCauseid() {
		return (String) saveArray[ tableFldConstants.causeid.ordinal() ];
	}

	public void setQirdCauseid(String qirdCauseid) {
		saveArray[ tableFldConstants.causeid.ordinal() ] = qirdCauseid;
	}

	public String getQirdQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setQirdQuantity(String qirdQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = qirdQuantity;
	}

	public String getQird4mtype() {
		return (String) saveArray[ tableFldConstants.m4type.ordinal() ];
	}

	public void setQird4mtype(String qird4mtype) {
		saveArray[ tableFldConstants.m4type.ordinal() ] = qird4mtype;
	}

	public String getQirdType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setQirdType(String qirdType) {
		saveArray[ tableFldConstants.type.ordinal() ] = qirdType;
	}

	public String getQirdBacklogflag() {
		return (String) saveArray[ tableFldConstants.backlogflag.ordinal() ];
	}

	public void setQirdBacklogflag(String qirdBacklogflag) {
		saveArray[ tableFldConstants.backlogflag.ordinal() ] = qirdBacklogflag;
	}

	public String getQirdReferenceid() {
		return (String) saveArray[ tableFldConstants.referenceid.ordinal() ];
	}

	public void setQirdReferenceid(String qirdReferenceid) {
		saveArray[ tableFldConstants.referenceid.ordinal() ] = qirdReferenceid;
	}

	public String getQirdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setQirdRemarks(String qirdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = qirdRemarks;
	}

	public String getQirdWwmasterid() {
		return (String) saveArray[ tableFldConstants.wwmasterid.ordinal() ];
	}

	public void setQirdWwmasterid(String qirdWwmasterid) {
		saveArray[ tableFldConstants.wwmasterid.ordinal() ] = qirdWwmasterid;
	}

	public String getQirdEntrytype() {
		return (String) saveArray[ tableFldConstants.entrytype.ordinal() ];
	}

	public void setQirdEntrytype(String qirdEntrytype) {
		saveArray[ tableFldConstants.entrytype.ordinal() ] = qirdEntrytype;
	}

	public List<QtmTlIntrejectiondtl> getQtmTlIntrejectiondtl() {
		return qtmTlIntrejectiondtl;
	}

	public void setQtmTlIntrejectiondtl(
			List<QtmTlIntrejectiondtl> qtmTlIntrejectiondtl) {
		this.qtmTlIntrejectiondtl = qtmTlIntrejectiondtl;
	}

	public String getQirdMasterid() {
		return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
	}

	public void setQirdMasterid(String qirdMasterid) {
		saveArray[ tableFldConstants.masterid.ordinal() ] = qirdMasterid;
	}

	public String getQirdQhbKeyid() {
		return (String) saveArray[ tableFldConstants.qhb_keyid.ordinal() ];
	}

	public void setQirdQhbKeyid(String qirdQhbKeyid) {
		saveArray[ tableFldConstants.qhb_keyid.ordinal() ] = qirdQhbKeyid;
	}

	public String getPlrkKeyid() {
		return (String) saveArray[ tableFldConstants.plrk_keyid.ordinal() ];
	}

	public void setPlrkKeyid(String qirdPlrkKeyid) {
		saveArray[ tableFldConstants.plrk_keyid.ordinal() ] = qirdPlrkKeyid;
	}

	public String getQirdSubprocessid() {
		return (String) saveArray[ tableFldConstants.subprocessid.ordinal() ];
	}

	public void setQirdSubprocessid(String QirdSubprocessid) {
		saveArray[ tableFldConstants.subprocessid.ordinal() ] = QirdSubprocessid;
	}

	public String getQirdQty() {
		return (String) saveArray[ tableFldConstants.qty.ordinal() ];
	}

	public void setQirdQty(String QirdQty) {
		saveArray[ tableFldConstants.qty.ordinal() ] = QirdQty;
	}

	public String getQirdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setQirdTempfield4(String qirdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = qirdTempfield4;
	}

	public String getQirdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setQirdTempfield5(String qirdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = qirdTempfield5;
	}

	public String getQirdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQirdActive(String qirdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qirdActive;
	}

	public String getQirdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQirdCreatedby(String qirdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qirdCreatedby;
	}

	public String getQirdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQirdCreatedon(String qirdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qirdCreatedon;
	}

	public String getQirdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQirdModifiedon(String qirdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = qirdModifiedon;
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
	  
	  public static QtmTlIntrejectiondtl fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  QtmTlIntrejectiondtl dtl = new QtmTlIntrejectiondtl();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        dtl.setValue(field, val);
		    	
		    	
		    }
		    return dtl;
		}

}

