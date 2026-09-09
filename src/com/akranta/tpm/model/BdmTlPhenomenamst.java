package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BdmTlPhenomenamst {

	private List<BdmTlCausemst> bdmTlCausemst ;
	private List<BdmTlPhncauselink> phnCauseLink ;
	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, phenomenatype, phenomenaname, shortname, remarks, assemblyid
		, levelno, childflag, isphnnotdefined, causenotneeded,relatedto,tempfield1,tempfield2,tempfield3, active
		, createdby, createdon, modifiedon
	}

	public BdmTlPhenomenamst()
	{
		setBdmTlCausemst(new ArrayList<BdmTlCausemst> ());
		setPhnCauseLink(new ArrayList<BdmTlPhncauselink> ());
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	public List<BdmTlCausemst> getBdmTlCausemst() {
		return bdmTlCausemst;
	}

	public void setBdmTlCausemst(List<BdmTlCausemst> bdmTlCausemst) {
		this.bdmTlCausemst = bdmTlCausemst;
	}

	public List<BdmTlPhncauselink> getPhnCauseLink() {
		return phnCauseLink;
	}

	public void setPhnCauseLink(List<BdmTlPhncauselink> phnCauseLink) {
		this.phnCauseLink = phnCauseLink;
	}

	public String getBphmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBphmKeyid(String bphmkeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bphmkeyid;
	}

	public String getBphmPhenomenatype() {
		return (String) saveArray[ tableFldConstants.phenomenatype.ordinal() ];
	}

	public void setBphmPhenomenatype(String bphmphenomenatype) {
		saveArray[ tableFldConstants.phenomenatype.ordinal() ] = bphmphenomenatype;
	}

	public String getBphmPhenomenaname() {
		return (String) saveArray[ tableFldConstants.phenomenaname.ordinal() ];
	}

	public void setBphmPhenomenaname(String bphmphenomenaname) {
		saveArray[ tableFldConstants.phenomenaname.ordinal() ] = bphmphenomenaname;
	}

	public String getBphmShortname() {
		return (String) saveArray[ tableFldConstants.shortname.ordinal() ];
	}

	public void setBphmShortname(String bphmshortname) {
		saveArray[ tableFldConstants.shortname.ordinal() ] = bphmshortname;
	}

	public String getBphmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setBphmRemarks(String bphmremarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = bphmremarks;
	}

	public String getBphmAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setBphmAssemblyid(String bphmassemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = bphmassemblyid;
	}

	public String getBphmLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setBphmLevelno(String bphmlevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = bphmlevelno;
	}

	public String getBphmChildflag() {
		return (String) saveArray[ tableFldConstants.childflag.ordinal() ];
	}

	public void setBphmChildflag(String bphmchildflag) {
		saveArray[ tableFldConstants.childflag.ordinal() ] = bphmchildflag;
	}

	public String getBphmIsphnnotdefined() {
		return (String) saveArray[ tableFldConstants.isphnnotdefined.ordinal() ];
	}

	public void setBphmIsphnnotdefined(String bphmisphnnotdefined) {
		saveArray[ tableFldConstants.isphnnotdefined.ordinal() ] = bphmisphnnotdefined;
	}

	public String getBphmCausenotneeded() {
		return (String) saveArray[ tableFldConstants.causenotneeded.ordinal() ];
	}

	public void setBphmCausenotneeded(String bphmcausenotneeded) {
		saveArray[ tableFldConstants.causenotneeded.ordinal() ] = bphmcausenotneeded;
	}
	public String getBphmRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setBphmRelatedto(String bphmrelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = bphmrelatedto;
	}
	public String getTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTempfield1(String bphmtempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = bphmtempfield1;
	}
	public String getTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTempfield2(String bphmtempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = bphmtempfield2;
	}
	public String getTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTempfield3(String bphmtempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = bphmtempfield3;
	}

	public String getBphmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBphmActive(String bphmactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bphmactive;
	}

	public String getBphmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBphmCreatedby(String bphmcreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bphmcreatedby;
	}

	public String getBphmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBphmCreatedon(String bphmcreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bphmcreatedon;
	}

	public String getBphmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBphmModifiedon(String bphmmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bphmmodifiedon;
	}

}

