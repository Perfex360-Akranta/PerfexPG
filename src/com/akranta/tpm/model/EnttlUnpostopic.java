package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EnttlUnpostopic{

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid,location,dmt,jh,uniqposkeyid,topicid,mapdate,createdate,createby,modifieddate,
		modifiedby,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, active,createdon,modifiedon
	}

	public EnttlUnpostopic()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getEtuqKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEtuqKeyid(String etuqKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = etuqKeyid;
	}

	public String getEtuqLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setEtuqLocation(String etuqLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = etuqLocation;
	}

	public String getEtuqDmt() {
		return (String) saveArray[ tableFldConstants.dmt.ordinal() ];
	}

	public void setEtuqDmt(String etuqDmt) {
		saveArray[ tableFldConstants.dmt.ordinal() ] = etuqDmt;
	}

	public String getEtuqJh() {
		return (String) saveArray[ tableFldConstants.jh.ordinal() ];
	}

	public void setEtuqJh(String etuqJh) {
		saveArray[ tableFldConstants.jh.ordinal() ] = etuqJh;
	}

	public String getEtuqUniposKeyid(){
		return (String) saveArray[ tableFldConstants.uniqposkeyid.ordinal() ];
	}

	public void setEtuqUniposKeyid(String etuqUniposKeyid) {
		saveArray[ tableFldConstants.uniqposkeyid.ordinal() ] = etuqUniposKeyid;
	}
     
	public String getEtuqTopicid(){
		return (String) saveArray[ tableFldConstants.topicid.ordinal() ];
	}

	public void setEtuqTopicid(String etuqTopicid) {
		saveArray[ tableFldConstants.topicid.ordinal() ] = etuqTopicid;
	}
	public String getEtuqMapdate(){
		return (String) saveArray[ tableFldConstants.mapdate.ordinal() ];
	}

	public void setEtuqMapdate(String etuqMapdate) {
		saveArray[ tableFldConstants.mapdate.ordinal() ] = etuqMapdate;
	}

	public String getEtuqCreateDate(){
		return (String) saveArray[ tableFldConstants.createdate.ordinal() ];
	}

	public void setEtuqCreateDate(String etuqCreateDate) {
		saveArray[ tableFldConstants.createdate.ordinal() ] = etuqCreateDate;
	}

	public String getEtuqCreateBy(){
		return (String) saveArray[ tableFldConstants.createby.ordinal() ];
	}

	public void setEtuqCreateBy(String etuqCreateBy) {
		saveArray[ tableFldConstants.createby.ordinal() ] = etuqCreateBy;
	}
	public String getEtuqModifiedDate(){
		return (String) saveArray[ tableFldConstants.modifieddate.ordinal() ];
	}

	public void setEtuqModifiedDate(String etuqModifiedDate) {
		saveArray[ tableFldConstants.modifieddate.ordinal() ] = etuqModifiedDate;
	}
	
	public String getEtuqModifiedBy(){
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setEtuqModifiedBy(String etuqModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = etuqModifiedby;
	}
	
	public String getEtuqTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEtuqTempfield1(String etuqTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = etuqTempfield1;
	}

	public String getEtuqTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEtuqTempfield2(String etuqTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = etuqTempfield2;
	}
	
	
	public String getEtuqTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEtuqTempfield3(String etuqTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = etuqTempfield3;
	}
	
	public String getEtuqTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEtuqTempfield4(String etuqTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = etuqTempfield4;
	}
	
	public String getEtuqTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEtuqTempfield5(String etuqTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = etuqTempfield5;
	}


	public String getEtuqActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEtuqActive(String etuqActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = etuqActive;
	}

	public String getEtuqCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEtuqCreatedon(String etuqCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = etuqCreatedon;
	}

	public String getEtuqModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEtuqModifiedon(String etuqModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = etuqModifiedon;
	}
}

