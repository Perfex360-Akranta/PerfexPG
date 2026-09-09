package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlDmcprojectprioritymst {

	private  Object [] saveArray = null;  
 
	
	private List<KznTlDmcprojectprioritymst> master;
	private List<KznTlDmcprojectprioritydtl> detail;
	
	public enum   tableFldConstants
	{
		keyid, dmcm_keyid, flid, approvedby, projectscore, rank, tempfield1
		, tempfield2, tempfield3, tempfield4, createdby, active, createdon
		, modifiedon
	}

	public KznTlDmcprojectprioritymst()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getDmpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDmpmKeyid(String dmpmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dmpmKeyid;
	}

	public String getDmpmDmcmKeyid() {
		return (String) saveArray[ tableFldConstants.dmcm_keyid.ordinal() ];
	}

	public void setDmpmDmcmKeyid(String dmpmDmcmKeyid) {
		saveArray[ tableFldConstants.dmcm_keyid.ordinal() ] = dmpmDmcmKeyid;
	}

	public String getDmpmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setDmpmFlid(String dmpmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = dmpmFlid;
	}

	public String getDmpmApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setDmpmApprovedby(String dmpmApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = dmpmApprovedby;
	}

	public String getDmpmProjectscore() {
		return (String) saveArray[ tableFldConstants.projectscore.ordinal() ];
	}

	public void setDmpmProjectscore(String dmpmProjectscore) {
		saveArray[ tableFldConstants.projectscore.ordinal() ] = dmpmProjectscore;
	}

	public String getDmpmRank() {
		return (String) saveArray[ tableFldConstants.rank.ordinal() ];
	}

	public void setDmpmRank(String dmpmRank) {
		saveArray[ tableFldConstants.rank.ordinal() ] = dmpmRank;
	}

	public String getDmpmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setDmpmTempfield1(String dmpmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = dmpmTempfield1;
	}

	public String getDmpmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDmpmTempfield2(String dmpmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dmpmTempfield2;
	}

	public String getDmpmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDmpmTempfield3(String dmpmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dmpmTempfield3;
	}

	public String getDmpmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDmpmTempfield4(String dmpmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dmpmTempfield4;
	}

	public String getDmpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDmpmCreatedby(String dmpmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dmpmCreatedby;
	}

	public String getDmpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDmpmActive(String dmpmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dmpmActive;
	}

	public String getDmpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDmpmCreatedon(String dmpmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dmpmCreatedon;
	}

	public String getDmpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDmpmModifiedon(String dmpmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dmpmModifiedon;
	}
	public void setMaster(List<KznTlDmcprojectprioritymst> master) {
		this.master = master;
	}

	public List<KznTlDmcprojectprioritymst> getMaster() {
		return master;
	}

	public void setDetail(List<KznTlDmcprojectprioritydtl> detail) {
		this.detail = detail;
	}

	public List<KznTlDmcprojectprioritydtl> getDetail() {
		return detail;
	}

}

