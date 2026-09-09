package com.akranta.tpm.model;


import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class AdmTlUsersessions {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		userid, sessionno, sessiondate, logintime, logouttime, sessionstatus
		, pcname, ipaddress, sessionid, tempfield1, tempfield2, tempfield3
	}

	public AdmTlUsersessions()
	{
		saveArray = new  Object [ 12 ];
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

	public String getUsseUserid() {
		return (String) saveArray[ tableFldConstants.userid.ordinal() ];
	}

	public void setUsseUserid(String usseUserid) {
		saveArray[ tableFldConstants.userid.ordinal() ] = usseUserid;
	}

	public String getUsseSessionno() {
		return (String) saveArray[ tableFldConstants.sessionno.ordinal() ];
	}

	public void setUsseSessionno(String usseSessionno) {
		saveArray[ tableFldConstants.sessionno.ordinal() ] = usseSessionno;
	}

	public String getUsseSessiondate() {
		return (String) saveArray[ tableFldConstants.sessiondate.ordinal() ];
	}

	public void setUsseSessiondate(String usseSessiondate) {
		saveArray[ tableFldConstants.sessiondate.ordinal() ] = usseSessiondate;
	}

	public String getUsseLogintime() {
		return (String) saveArray[ tableFldConstants.logintime.ordinal() ];
	}

	public void setUsseLogintime(String usseLogintime) {
		saveArray[ tableFldConstants.logintime.ordinal() ] = usseLogintime;
	}

	public String getUsseLogouttime() {
		return (String) saveArray[ tableFldConstants.logouttime.ordinal() ];
	}

	public void setUsseLogouttime(String usseLogouttime) {
		saveArray[ tableFldConstants.logouttime.ordinal() ] = usseLogouttime;
	}

	public String getUsseSessionstatus() {
		return (String) saveArray[ tableFldConstants.sessionstatus.ordinal() ];
	}

	public void setUsseSessionstatus(String usseSessionstatus) {
		saveArray[ tableFldConstants.sessionstatus.ordinal() ] = usseSessionstatus;
	}

	public String getUssePcname() {
		return (String) saveArray[ tableFldConstants.pcname.ordinal() ];
	}

	public void setUssePcname(String ussePcname) {
		saveArray[ tableFldConstants.pcname.ordinal() ] = ussePcname;
	}

	public String getUsseIpaddress() {
		return (String) saveArray[ tableFldConstants.ipaddress.ordinal() ];
	}

	public void setUsseIpaddress(String usseIpaddress) {
		saveArray[ tableFldConstants.ipaddress.ordinal() ] = usseIpaddress;
	}

	public String getUsseSessionid() {
		return (String) saveArray[ tableFldConstants.sessionid.ordinal() ];
	}

	public void setUsseSessionid(String usseSessionid) {
		saveArray[ tableFldConstants.sessionid.ordinal() ] = usseSessionid;
	}

	public String getUsseTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setUsseTempfield1(String usseTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = usseTempfield1;
	}

	public String getUsseTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setUsseTempfield2(String usseTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = usseTempfield2;
	}

	public String getUsseTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setUsseTempfield3(String usseTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = usseTempfield3;
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
	  

	  
	  public static AdmTlUsersessions fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  AdmTlUsersessions session = new AdmTlUsersessions();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        session.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return session;
		}

}

