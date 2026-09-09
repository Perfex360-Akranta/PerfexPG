package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class AdmTlLoginframework {


	private  Object [] saveArray = null;  

	public enum   tableFldConstants
	{
		keyid, usernamelength, defsyspassword, minpasslength, maxpasslength
		, alphabets, numerals, passchangegap, passchangefreq, passgraceperiod
		, passintimation, passneverexpires, passhistoryremember, mincharpasschange
		, failedloginattempts, isloginaudit, ispassaudit, isprivaudit
		, createddate, ispassautogen,ispolicyactive,minpwdchangedays,active ,createdby, createdon, modifiedon
	}
	
	public AdmTlLoginframework()
	{
		saveArray = new  Object [ 26 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getLgfrKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setLgfrKeyid(String lgfrKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = lgfrKeyid;
	}

	public String getLgfrUsernamelength() {
		return (String) saveArray[ tableFldConstants.usernamelength.ordinal() ];
	}

	public void setLgfrUsernamelength(String lgfrUsernamelength) {
		saveArray[ tableFldConstants.usernamelength.ordinal() ] = lgfrUsernamelength;
	}

	public String getLgfrDefsyspassword() {
		return (String) saveArray[ tableFldConstants.defsyspassword.ordinal() ];
	}

	public void setLgfrDefsyspassword(String lgfrDefsyspassword) {
		saveArray[ tableFldConstants.defsyspassword.ordinal() ] = lgfrDefsyspassword;
	}

	public String getLgfrMinpasslength() {
		return (String) saveArray[ tableFldConstants.minpasslength.ordinal() ];
	}

	public void setLgfrMinpasslength(String lgfrMinpasslength) {
		saveArray[ tableFldConstants.minpasslength.ordinal() ] = lgfrMinpasslength;
	}

	public String getLgfrMaxpasslength() {
		return (String) saveArray[ tableFldConstants.maxpasslength.ordinal() ];
	}

	public void setLgfrMaxpasslength(String lgfrMaxpasslength) {
		saveArray[ tableFldConstants.maxpasslength.ordinal() ] = lgfrMaxpasslength;
	}

	public String getLgfrAlphabets() {
		return (String) saveArray[ tableFldConstants.alphabets.ordinal() ];
	}

	public void setLgfrAlphabets(String lgfrAlphabets) {
		saveArray[ tableFldConstants.alphabets.ordinal() ] = lgfrAlphabets;
	}

	public String getLgfrNumerals() {
		return (String) saveArray[ tableFldConstants.numerals.ordinal() ];
	}

	public void setLgfrNumerals(String lgfrNumerals) {
		saveArray[ tableFldConstants.numerals.ordinal() ] = lgfrNumerals;
	}

	public String getLgfrPasschangegap() {
		return (String) saveArray[ tableFldConstants.passchangegap.ordinal() ];
	}

	public void setLgfrPasschangegap(String lgfrPasschangegap) {
		saveArray[ tableFldConstants.passchangegap.ordinal() ] = lgfrPasschangegap;
	}

	public String getLgfrPasschangefreq() {
		return (String) saveArray[ tableFldConstants.passchangefreq.ordinal() ];
	}

	public void setLgfrPasschangefreq(String lgfrPasschangefreq) {
		saveArray[ tableFldConstants.passchangefreq.ordinal() ] = lgfrPasschangefreq;
	}

	public String getLgfrPassgraceperiod() {
		return (String) saveArray[ tableFldConstants.passgraceperiod.ordinal() ];
	}

	public void setLgfrPassgraceperiod(String lgfrPassgraceperiod) {
		saveArray[ tableFldConstants.passgraceperiod.ordinal() ] = lgfrPassgraceperiod;
	}

	public String getLgfrPassintimation() {
		return (String) saveArray[ tableFldConstants.passintimation.ordinal() ];
	}

	public void setLgfrPassintimation(String lgfrPassintimation) {
		saveArray[ tableFldConstants.passintimation.ordinal() ] = lgfrPassintimation;
	}

	public String getLgfrPassneverexpires() {
		return (String) saveArray[ tableFldConstants.passneverexpires.ordinal() ];
	}

	public void setLgfrPassneverexpires(String lgfrPassneverexpires) {
		saveArray[ tableFldConstants.passneverexpires.ordinal() ] = lgfrPassneverexpires;
	}

	public String getLgfrPasshistoryremember() {
		return (String) saveArray[ tableFldConstants.passhistoryremember.ordinal() ];
	}

	public void setLgfrPasshistoryremember(String lgfrPasshistoryremember) {
		saveArray[ tableFldConstants.passhistoryremember.ordinal() ] = lgfrPasshistoryremember;
	}

	public String getLgfrMincharpasschange() {
		return (String) saveArray[ tableFldConstants.mincharpasschange.ordinal() ];
	}

	public void setLgfrMincharpasschange(String lgfrMincharpasschange) {
		saveArray[ tableFldConstants.mincharpasschange.ordinal() ] = lgfrMincharpasschange;
	}

	public String getLgfrFailedloginattempts() {
		return (String) saveArray[ tableFldConstants.failedloginattempts.ordinal() ];
	}

	public void setLgfrFailedloginattempts(String lgfrFailedloginattempts) {
		saveArray[ tableFldConstants.failedloginattempts.ordinal() ] = lgfrFailedloginattempts;
	}

	public String getLgfrIsloginaudit() {
		return (String) saveArray[ tableFldConstants.isloginaudit.ordinal() ];
	}

	public void setLgfrIsloginaudit(String lgfrIsloginaudit) {
		saveArray[ tableFldConstants.isloginaudit.ordinal() ] = lgfrIsloginaudit;
	}

	public String getLgfrIspassaudit() {
		return (String) saveArray[ tableFldConstants.ispassaudit.ordinal() ];
	}

	public void setLgfrIspassaudit(String lgfrIspassaudit) {
		saveArray[ tableFldConstants.ispassaudit.ordinal() ] = lgfrIspassaudit;
	}

	public String getLgfrIsprivaudit() {
		return (String) saveArray[ tableFldConstants.isprivaudit.ordinal() ];
	}

	public void setLgfrIsprivaudit(String lgfrIsprivaudit) {
		saveArray[ tableFldConstants.isprivaudit.ordinal() ] = lgfrIsprivaudit;
	}

	public String getLgfrCreateddate() {
		return (String) saveArray[ tableFldConstants.createddate.ordinal() ];
	}

	public void setLgfrCreateddate(String lgfrCreateddate) {
		saveArray[ tableFldConstants.createddate.ordinal() ] = lgfrCreateddate;
	}
	
	public String getLgfrIspassautogen() {
		return (String) saveArray[ tableFldConstants.ispassautogen.ordinal() ];
	}

	public void setLgfrIspassautogen(String lgfrIspassautogen) {
		saveArray[ tableFldConstants.ispassautogen.ordinal() ] = lgfrIspassautogen;
	}
	public String getLgfrIspolicyactive() {
		return (String) saveArray[ tableFldConstants.ispolicyactive.ordinal() ];
	}

	public void setLgfrIspolicyactive(String lgfrIspolicyactive) {
		saveArray[ tableFldConstants.ispolicyactive.ordinal() ] = lgfrIspolicyactive;
	}
	public String getLgfrminpwdchangedays() {
		return (String) saveArray[ tableFldConstants.minpwdchangedays.ordinal() ];
	}

	public void setLgfrminpwdchangedays(String lgfrminpwdchangedays) {
		saveArray[ tableFldConstants.minpwdchangedays.ordinal() ] = lgfrminpwdchangedays;
	}
	
	public String getLgfrActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setLgfrActive(String active) {
		saveArray[ tableFldConstants.active.ordinal() ] = active;
	}
	

	public String getLgfrCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setLgfrCreatedby(String lgfrCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = lgfrCreatedby;
	}

	public String getLgfrCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setLgfrCreatedon(String lgfrCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = lgfrCreatedon;
	}

	public String getLgfrModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setLgfrModifiedon(String lgfrModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = lgfrModifiedon;
	}

	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}

}

