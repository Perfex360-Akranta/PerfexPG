package com.akranta.tpm.model;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class AdmTlUsermst {

	private  Object [] saveArray = null;  
	//private String usrm_username;
		
	private String windowsGroup = null;
	public enum   tableFldConstants
	{
		keyid, userpin, username, ccno, loginid, password, defaultpassword
		, securitypolicyid, designationid, departmentid, extensionphone
		, lastpwdchanged, lastlogindate, isuserlocked, isactive, isadministartor
		, ispwdlockenabled, istemplateuser, remarks,loginatempt ,isvalidityreq,validfrom , validtill , createdby, createdon
		, modifiedon
	}
	


	public AdmTlUsermst()
	{
		saveArray = new  Object [ 26 ];
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
	
	public String getUsrm_keyid() {
		return (String)saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setUsrm_keyid(String usrm_keyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = usrm_keyid;
	}

	public String getUsrm_userpin() {
		return ""+saveArray[ tableFldConstants.userpin.ordinal() ] ;
	}

	public void setUsrm_userpin(String usrm_userpin) {
		//CommonMessage.debugMsg(usrm_userpin);
		saveArray[ tableFldConstants.userpin.ordinal() ] = usrm_userpin;
	}

	public String getUsrm_username() {
		//CommonMessage.debugMsg(" (String)saveArray[ tableFldConstants.username.ordinal() ] " + (String)saveArray[ tableFldConstants.username.ordinal() ]);
	//	this.usrm_username = (String)saveArray[ tableFldConstants.username.ordinal() ];
	//	return this.usrm_username;
		return (String)saveArray[ tableFldConstants.username.ordinal() ] ;
	}

	public void setUsrm_username(String usrm_username) {
		//this.usrm_username=usrm_username;
		saveArray[ tableFldConstants.username.ordinal() ] = usrm_username;
	}

	public String getUsrm_ccno() {
		return (String)saveArray[ tableFldConstants.ccno.ordinal() ] ;
	}

	public void setUsrm_ccno(String usrm_ccno) {
		saveArray[ tableFldConstants.ccno.ordinal() ] = usrm_ccno;
	}

	public String getUsrm_loginid() {
		return (String)saveArray[ tableFldConstants.loginid.ordinal() ] ;
	}

	public void setUsrm_loginid(String usrm_loginid) {
		saveArray[ tableFldConstants.loginid.ordinal() ] = usrm_loginid;
	}

	public String getUsrm_password() {
		return (String)saveArray[ tableFldConstants.password.ordinal() ];
	}

	public void setUsrm_password(String usrm_password) {
		saveArray[ tableFldConstants.password.ordinal() ] = usrm_password;
	}

	public String getUsrm_defaultpassword() {
		return (String)saveArray[ tableFldConstants.defaultpassword.ordinal() ];
	}

	public void setUsrm_defaultpassword(String usrm_defaultpassword) {
		saveArray[ tableFldConstants.defaultpassword.ordinal() ] = usrm_defaultpassword;
	}

	public String getUsrm_securitypolicyid() {
		return (String)saveArray[ tableFldConstants.securitypolicyid.ordinal() ];
	}

	public void setUsrm_securitypolicyid(String usrm_securitypolicyid) {
		saveArray[ tableFldConstants.securitypolicyid.ordinal() ] = usrm_securitypolicyid;
	}

	public String getUsrm_designationid() {
		return (String)saveArray[ tableFldConstants.designationid.ordinal() ];
	}

	public void setUsrm_designationid(String usrm_designationid) {
		saveArray[ tableFldConstants.designationid.ordinal() ] = usrm_designationid;
	}

	public String getUsrm_departmentid() {
		return (String)saveArray[ tableFldConstants.departmentid.ordinal() ] ;
	}

	public void setUsrm_departmentid(String usrm_departmentid) {
		saveArray[ tableFldConstants.departmentid.ordinal() ] = usrm_departmentid;
	}

	public String getUsrm_extensionphone() {
		return (String)saveArray[ tableFldConstants.extensionphone.ordinal() ];
	}

	public void setUsrm_extensionphone(String usrm_extensionphone) {
		saveArray[ tableFldConstants.extensionphone.ordinal() ] = usrm_extensionphone;
	}

	public String getUsrm_lastpwdchanged() {
		return (String)saveArray[ tableFldConstants.lastpwdchanged.ordinal() ];
	}

	public void setUsrm_lastpwdchanged(String usrm_lastpwdchanged) {
		saveArray[ tableFldConstants.lastpwdchanged.ordinal() ] = usrm_lastpwdchanged;
	}

	public String getUsrm_lastlogindate() {
		return (String)saveArray[ tableFldConstants.lastlogindate.ordinal() ] ;
	}

	public void setUsrm_lastlogindate(String usrm_lastlogindate) {
		saveArray[ tableFldConstants.lastlogindate.ordinal() ] = usrm_lastlogindate;
	}

	public String getUsrm_isuserlocked() {
		return (String)saveArray[ tableFldConstants.isuserlocked.ordinal() ];
	}

	public void setUsrm_isuserlocked(String usrm_isuserlocked) {
		saveArray[ tableFldConstants.isuserlocked.ordinal() ] = usrm_isuserlocked;
	}

	public String getUsrm_isactive() {
		return (String)saveArray[ tableFldConstants.isactive.ordinal() ];
	}

	public void setUsrm_isactive(String usrm_isactive) {
		saveArray[ tableFldConstants.isactive.ordinal() ] = usrm_isactive;
	}

	public String getUsrm_isadministartor() {
		return (String)saveArray[ tableFldConstants.isadministartor.ordinal() ];
	}

	public void setUsrm_isadministartor(String usrm_isadministartor) {
		saveArray[ tableFldConstants.isadministartor.ordinal() ] = usrm_isadministartor;
	}

	public String getUsrm_ispwdlockenabled() {
		return (String)saveArray[ tableFldConstants.ispwdlockenabled.ordinal() ];
	}

	public void setUsrm_ispwdlockenabled(String usrm_ispwdlockenabled) {
		saveArray[ tableFldConstants.ispwdlockenabled.ordinal() ] = usrm_ispwdlockenabled;
	}

	public String getUsrm_istemplateuser() {
		return (String)saveArray[ tableFldConstants.istemplateuser.ordinal() ];
	}

	public void setUsrm_istemplateuser(String usrm_istemplateuser) {
		saveArray[ tableFldConstants.istemplateuser.ordinal() ] = usrm_istemplateuser;
	}

	public String getUsrm_remarks() {
		return (String)saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setUsrm_remarks(String usrm_remarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = usrm_remarks;
	}

	
	public String getUsrm_loginatempt() {
		return (String)saveArray[ tableFldConstants.loginatempt.ordinal() ];
	}

	public void setUsrm_loginatempt(String usrm_loginatempt) {
		saveArray[ tableFldConstants.loginatempt.ordinal() ] = usrm_loginatempt;
	}
	
	public String getUsrm_isvalidityreq() {
		return (String)saveArray[ tableFldConstants.isvalidityreq.ordinal() ];
	}

	public void setUsrm_isvalidityreq(String usrm_isvalidityreq) {
		saveArray[ tableFldConstants.isvalidityreq.ordinal() ] = usrm_isvalidityreq;
	}
	
	public String getUsrm_validfrom() {
		return (String)saveArray[ tableFldConstants.validfrom.ordinal() ];
	}

	public void setUsrm_validfrom(String usrm_validfrom) {
		saveArray[ tableFldConstants.validfrom.ordinal() ] = usrm_validfrom;
	}
	
	public String getUsrm_validtill() {
		return (String)saveArray[ tableFldConstants.validtill.ordinal() ];
	}

	public void setUsrm_validtill(String usrm_validtill) {
		saveArray[ tableFldConstants.validtill.ordinal() ] = usrm_validtill;
	}
	
	
	
	public String getUsrm_createdby() {
		return (String)saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setUsrm_createdby(String usrm_createdby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = usrm_createdby;
	}

	public String getUsrm_createdon() {
		return (String)saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setUsrm_createdon(String usrm_createdon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = usrm_createdon;
	}

	public String getUsrm_modifiedon() {
		return (String)saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setUsrm_modifiedon(String usrm_modifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = usrm_modifiedon;
	}

	public void setWindowsGroup(String windowsGroup) {
		this.windowsGroup = windowsGroup;
	}

	public String getWindowsGroup() {
		return windowsGroup;
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
	
	public static AdmTlUsermst fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  AdmTlUsermst user = new AdmTlUsermst();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	//CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        user.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return user;
		}

}

