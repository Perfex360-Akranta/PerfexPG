package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlFnlnroleteam {

	private  Object [] saveArray = null;  
	//private  Object [] saveArray = null;
	private String teamTrade=null;
	private String trade=null;
	private String processId=null;
	private String subProcessId=null;
	private String subSubProcessId=null;
	private List<GenTlTeamtradelink> genTlTeamtradelinkList ;
	public enum   tableFldConstants
	{
		keyid, frl_keyid,fnln_keyid, role_keyid, empm_keyid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public GenTlFnlnroleteam()
	{
		saveArray = new  Object [ 14 ];
	}	

	public void setTeamtradelink(List<GenTlTeamtradelink> genTlTeamtradelinkList) {
		this.genTlTeamtradelinkList = genTlTeamtradelinkList;
	}	
	public List<GenTlTeamtradelink> getTeamtradelink() {		
		return genTlTeamtradelinkList;
	}
	
	public void setTeamTrade(String teamTrade) {
		this.teamTrade = teamTrade;
	}	
	public String getTeamTrade() {		
		return teamTrade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}	
	public String getTrade() {		
		return trade;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}	
	public String getProcessId() {		
		return processId;
	}
	public void setSubProcessId(String subProcessId) {
		this.subProcessId = subProcessId;
	}	
	public String getSubProcessId() {		
		return subProcessId;
	}
	public void setSubSubProcessId(String subSubProcessId) {
		this.subSubProcessId = subSubProcessId;
	}	
	public String getSubSubProcessId() {		
		return subSubProcessId;
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFrtKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFrtKeyid(String frlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = frlKeyid;
	}
	
	public String getFrtFrlKeyid() {
		return (String) saveArray[ tableFldConstants.frl_keyid.ordinal() ];
	}

	public void setFrtFrlKeyid(String frtFrlKeyid) {
		saveArray[ tableFldConstants.frl_keyid.ordinal() ] = frtFrlKeyid;
	}

	public String getFrtFnlnKeyid() {
		return (String) saveArray[ tableFldConstants.fnln_keyid.ordinal() ];
	}

	public void setFrtFnlnKeyid(String frtFnlnKeyid) {
		saveArray[ tableFldConstants.fnln_keyid.ordinal() ] = frtFnlnKeyid;
	}

	public String getFrtRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setFrtRoleKeyid(String frtRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = frtRoleKeyid;
	}

	public String getFrtEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setFrtEmpmKeyid(String frtEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = frtEmpmKeyid;
	}

	public String getFrtTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFrtTempfield1(String frtTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = frtTempfield1;
	}

	public String getFrtTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFrtTempfield2(String frtTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = frtTempfield2;
	}

	public String getFrtTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFrtTempfield3(String frtTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = frtTempfield3;
	}

	public String getFrtTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFrtTempfield4(String frtTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = frtTempfield4;
	}

	public String getFrtTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFrtTempfield5(String frtTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = frtTempfield5;
	}

	public String getFrtActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFrtActive(String frtActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = frtActive;
	}

	public String getFrtCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFrtCreatedby(String frtCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = frtCreatedby;
	}

	public String getFrtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFrtCreatedon(String frtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = frtCreatedon;
	}

	public String getFrtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFrtModifiedon(String frtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = frtModifiedon;
	}

}

