package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_BdmTlYyeffectivemst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, refdocid, refdoctype, wwms_keyid, effectivedate
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, active, createdby, createdon, modifiedon
	}

	public BAL_BdmTlYyeffectivemst()
	{
		saveArray = new  Object [ 17 ];
	}

	private List<BAL_BdmTlYyeffectivedtl> bdmTlYyeffectivedtl ;
	private List<BAL_BdmTlYyeffectivemst> bdmTlYyeffectivemst ;
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getYyefKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setYyefKeyid(String yyefKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = yyefKeyid;
	}

	public String getYyefFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setYyefFlid(String yyefFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = yyefFlid;
	}

	public String getYyefRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setYyefRefdocid(String yyefRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = yyefRefdocid;
	}

	public String getYyefRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setYyefRefdoctype(String yyefRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = yyefRefdoctype;
	}

	public String getYyefWwmsKeyid() {
		return (String) saveArray[ tableFldConstants.wwms_keyid.ordinal() ];
	}

	public void setYyefWwmsKeyid(String yyefWwmsKeyid) {
		saveArray[ tableFldConstants.wwms_keyid.ordinal() ] = yyefWwmsKeyid;
	}

	public String getYyefEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setYyefEffectivedate(String yyefEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = yyefEffectivedate;
	}

	public String getYyefTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setYyefTempfield1(String yyefTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = yyefTempfield1;
	}

	public String getYyefTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setYyefTempfield2(String yyefTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = yyefTempfield2;
	}

	public String getYyefTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setYyefTempfield3(String yyefTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = yyefTempfield3;
	}

	public String getYyefTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setYyefTempfield4(String yyefTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = yyefTempfield4;
	}

	public String getYyefTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setYyefTempfield5(String yyefTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = yyefTempfield5;
	}

	public String getYyefTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setYyefTempfield6(String yyefTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = yyefTempfield6;
	}

	public String getYyefTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setYyefTempfield7(String yyefTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = yyefTempfield7;
	}

	public String getYyefActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setYyefActive(String yyefActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = yyefActive;
	}

	public String getYyefCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setYyefCreatedby(String yyefCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = yyefCreatedby;
	}

	public String getYyefCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setYyefCreatedon(String yyefCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = yyefCreatedon;
	}

	public String getYyefModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setYyefModifiedon(String yyefModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = yyefModifiedon;
	}

	public List<BAL_BdmTlYyeffectivedtl> getBdmTlYyeffectivedtl() {
		return bdmTlYyeffectivedtl;
	}

	public void setBdmTlYyeffectivedtl(List<BAL_BdmTlYyeffectivedtl> bdmTlYyeffectivedtl) {
		this.bdmTlYyeffectivedtl = bdmTlYyeffectivedtl;
	}

	public List<BAL_BdmTlYyeffectivemst> getBdmTlYyeffectivemst() {
		return bdmTlYyeffectivemst;
	}

	public void setBdmTlYyeffectivemst(List<BAL_BdmTlYyeffectivemst> bdmTlYyeffectivemst) {
		this.bdmTlYyeffectivemst = bdmTlYyeffectivemst;
	}

}

