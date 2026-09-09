package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class HazopMst{
	private  Object [] saveArray=null;  
	private List<HazopDtl> hazopDtl;

	public enum   tableFldConstants
	{
		keyid,mocmkeyid,kzbnkeyid,date,facility,team,pidno,node,
		designintent,flid,directhazop,tempfield3, tempfield4, tempfield5,
		active, createdby, createdon, modifiedon
	}


	public HazopMst()
	{
		saveArray = new  Object [  18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getHzomKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}
	public void setHzomKeyid(String hzomKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = hzomKeyid;
	}
	
  public  List<HazopDtl> getHazopDetails(){
	  return hazopDtl;
  }
  public void setHazopDetails(List<HazopDtl> hazopDtl){
	  this.hazopDtl=hazopDtl;
  }
	public String getHzomMocmKeyid() {
		return (String) saveArray[ tableFldConstants.mocmkeyid.ordinal() ];
	}

	public void setHzomMocmKeyid(String hzomMocmKeyid) {
		saveArray[ tableFldConstants.mocmkeyid.ordinal() ]=hzomMocmKeyid;
	}
	
	public String getHzomKzbnKeyid() {
		return (String) saveArray[ tableFldConstants.kzbnkeyid.ordinal() ];
	}

	public void setHzomKzbnKeyid(String  hzomKzbnKeyid) {
		saveArray[ tableFldConstants.kzbnkeyid.ordinal() ] =hzomKzbnKeyid;
	}
	
	public String getHzomDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setHzomDate(String  hzomDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = hzomDate;
	}
	
	public String getHzomFacility() {
		return (String) saveArray[ tableFldConstants.facility.ordinal() ];
	}

	public void setHzomFacility(String hzomFacility) {
		saveArray[ tableFldConstants.facility.ordinal() ] =hzomFacility;
	}
	
	public String getHzomTeam() {
		return (String) saveArray[ tableFldConstants.team.ordinal() ];
	}

	public void setHzomTeam(String hzomTeam) {
		saveArray[ tableFldConstants.team.ordinal() ]=hzomTeam;
	}
	public String getHzomPidno() {
		return (String) saveArray[ tableFldConstants.pidno.ordinal() ];
	}

	public void setHzomPidno(String hzomPidno) {
		saveArray[ tableFldConstants.pidno.ordinal() ]=hzomPidno;
	}
	public String getHzomNode() {
		return (String) saveArray[ tableFldConstants.node.ordinal() ];
	}

	public void setHzomNode(String hzomnode) {
		saveArray[ tableFldConstants.node.ordinal() ]=hzomnode;
	}
	public String getHzomDesignintent() {
		return (String) saveArray[ tableFldConstants.designintent.ordinal() ];
	}

	public void setHzomDesignintent(String hzomdesignintent) {
		saveArray[ tableFldConstants.designintent.ordinal() ]=hzomdesignintent;
	}
	public String getHzomFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setHzomFlid(String hzomflid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = hzomflid;
	}
	
	public String getHzomDirecthazop() {
		return (String) saveArray[ tableFldConstants.directhazop.ordinal() ];
	}

	public void setHzomDirecthazop(String hzomdirecthazop) {
		saveArray[ tableFldConstants.directhazop.ordinal() ] = hzomdirecthazop;
	}
	public String getHzomTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setHzomTempfield3(String hzomTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] =hzomTempfield3;
	}
	public String getHzomTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setHzomTempfield4(String hzomTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] =hzomTempfield4;
	}
	public String getHzomTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setHzomTempfield5(String hzomTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] =hzomTempfield5;
	}
	
	public String getHzomActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setHzomActive(String hzomActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = hzomActive;
	}

	public String getHzomCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setHzomCreatedby(String hzomCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =hzomCreatedby;
	}

	public String getHzomCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setHzomCreatedon(String hzomCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =hzomCreatedon;
	}

	public String getHzomModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setHzomModifiedon(String hzomModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =hzomModifiedon;
	}

}

