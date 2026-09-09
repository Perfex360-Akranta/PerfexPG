package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTrgCalSession {

	private  Object [] saveArray = null;  
    
	private String isSession;
	private String sessionFromTime;
	private String sessionTillTime;
	

	public enum   tableFldConstants
	{
		keyid, etcm_keyid, etcm_flid, name, sessiondate, fromdate,tilldate,dateadd
		,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, active
		, createdon, modifiedon
	}

	public EntTlTrgCalSession()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getEtcsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEtcsKeyid(String etcsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = etcsKeyid;
	}

	public String getEtcsEtcmKeyid() {
		return (String) saveArray[ tableFldConstants.etcm_keyid.ordinal() ];
	}

	public void setEtcsEtcmKeyid(String etcsEtcmKeyid) {
		saveArray[ tableFldConstants.etcm_keyid.ordinal() ] = etcsEtcmKeyid;
	}

	public String getEtcsEtcmFlid() {
		return (String) saveArray[ tableFldConstants.etcm_flid.ordinal() ];
	}

	public void setEtcsEtcmFlid(String etcsEtcmFlid) {
		saveArray[ tableFldConstants.etcm_flid.ordinal() ] = etcsEtcmFlid;
	}

	public String getEtcsName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setEtcsName(String etcsName) {
		saveArray[ tableFldConstants.name.ordinal() ] = etcsName;
	}

	public String getEtcsSessionDate() {
		return (String) saveArray[ tableFldConstants.sessiondate.ordinal() ];
	}

	public void setEtcsSessionDate(String etcsSessionDate) {
		saveArray[ tableFldConstants.sessiondate.ordinal() ] = etcsSessionDate;
	}
	public String getEtcsFromDate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setEtcsFromDate(String etcsFromDate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = etcsFromDate;
	}
	public String getEtcsTillDate() {
		return (String) saveArray[ tableFldConstants.tilldate.ordinal() ];
	}

	public void setEtcsTillDate(String etcsTillDate) {
		saveArray[ tableFldConstants.tilldate.ordinal() ] = etcsTillDate;
	}
    
	public String getEtcsDateAdd() {
		return (String) saveArray[ tableFldConstants.dateadd.ordinal() ];
	}

	public void setEtcsDateAdd(String etcsDateAdd) {
		saveArray[ tableFldConstants.dateadd.ordinal() ] = etcsDateAdd;
	}
	
	public String getEtcsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEtcsTempfield1(String etcsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = etcsTempfield1;
	}

	public String getEtcsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEtcsTempfield2(String etcsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = etcsTempfield2;
	}
	
	
	public String getEtcsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEtcsTempfield3(String etcsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = etcsTempfield3;
	}
	
	public String getEtcsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEtcsTempfield4(String etcsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = etcsTempfield4;
	}
	
	public String getEtcsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEtcsTempfield5(String etcsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = etcsTempfield5;
	}


	public String getEtcsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEtcsActive(String etcsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = etcsActive;
	}
	
	public String getEtcsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEtcsCreatedon(String etcsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = etcsCreatedon;
	}

	public String getEtcsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEtcsModifiedon(String etcsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = etcsModifiedon;
	}
	
	public String getIsSession(){
		return isSession;
	}
	public void setIsSession(String isSession){
		this.isSession=isSession;
	}
	
	

	public String getSessionFromTime() {
		return sessionFromTime;
	}
	
	public void setSessionFromTime(String sessionFromTime) {
		this.sessionFromTime = sessionFromTime;
	}
	

	public String getSessionTillTime() {
		return sessionTillTime;
	}
	
	public void setSessionTillTime(String sessionTillTime) {
		this.sessionTillTime = sessionTillTime;
	}

}

