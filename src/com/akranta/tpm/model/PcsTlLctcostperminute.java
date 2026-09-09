package com.akranta.tpm.model;

import java.util.List;

public class PcsTlLctcostperminute {

	private  Object [] saveArray = null; 
	
	private List <PcsTlLctcostperminute> methodslist;
	
	public enum   tableFldConstants
	{
		keyid, elementid, elementtype, fromdate, todate, costperminute
		, latestflag, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon,ckPcsLossCost
	}

	public PcsTlLctcostperminute()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getLcpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setLcpmKeyid(String lcpmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = lcpmKeyid;
	}

	public String getLcpmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setLcpmElementid(String lcpmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = lcpmElementid;
	}

	public String getLcpmElementtype() {
		return (String) saveArray[ tableFldConstants.elementtype.ordinal() ];
	}

	public void setLcpmElementtype(String lcpmElementtype) {
		saveArray[ tableFldConstants.elementtype.ordinal() ] = lcpmElementtype;
	}

	public String getLcpmFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setLcpmFromdate(String lcpmFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = lcpmFromdate;
	}

	public String getLcpmTodate() {
		return (String) saveArray[ tableFldConstants.todate.ordinal() ];
	}

	public void setLcpmTodate(String lcpmTodate) {
		saveArray[ tableFldConstants.todate.ordinal() ] = lcpmTodate;
	}

	public String getLcpmCostperminute() {
		return (String) saveArray[ tableFldConstants.costperminute.ordinal() ];
	}

	public void setLcpmCostperminute(String lcpmCostperminute) {
		saveArray[ tableFldConstants.costperminute.ordinal() ] = lcpmCostperminute;
	}

	public String getLcpmLatestflag() {
		return (String) saveArray[ tableFldConstants.latestflag.ordinal() ];
	}

	public void setLcpmLatestflag(String lcpmLatestflag) {
		saveArray[ tableFldConstants.latestflag.ordinal() ] = lcpmLatestflag;
	}

	public String getLcpmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setLcpmTempfield1(String lcpmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = lcpmTempfield1;
	}

	public String getLcpmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setLcpmTempfield2(String lcpmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = lcpmTempfield2;
	}

	public String getLcpmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setLcpmTempfield3(String lcpmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = lcpmTempfield3;
	}

	public String getLcpmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setLcpmTempfield4(String lcpmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = lcpmTempfield4;
	}

	public String getLcpmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setLcpmTempfield5(String lcpmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = lcpmTempfield5;
	}

	public String getLcpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setLcpmActive(String lcpmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = lcpmActive;
	}

	public String getLcpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setLcpmCreatedby(String lcpmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = lcpmCreatedby;
	}

	public String getLcpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setLcpmCreatedon(String lcpmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = lcpmCreatedon;
	}

	public String getLcpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setLcpmModifiedon(String lcpmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = lcpmModifiedon;
	}
	public String getCkPcsLossCost() {
		return (String) saveArray[ tableFldConstants.ckPcsLossCost.ordinal() ];
	}

	public void setCkPcsLossCost(String ckPcsLossCost) {
		saveArray[ tableFldConstants.ckPcsLossCost.ordinal() ] = ckPcsLossCost;
	}

	public void setMethodslist(List <PcsTlLctcostperminute> methodslist) {
		this.methodslist = methodslist;
	}

	public List <PcsTlLctcostperminute> getMethodslist() {
		return methodslist;
	}

/*	public void setPcsTlLctcostperminute(List<PcsTlLctcostperminute> pcsTlLctcostperminute) {
		this.pcsTlLctcostperminute = pcsTlLctcostperminute;
	}

	public List<PcsTlLctcostperminute> getPcsTlLctcostperminute() {
		return pcsTlLctcostperminute;
	}*/
	

}

