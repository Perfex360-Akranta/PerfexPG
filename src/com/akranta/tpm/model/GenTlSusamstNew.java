package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlSusamstNew{
	private  Object [] saveArray = null; 
	private List<GenTlSusaAddBehaviormst> AddBehaviourMst=null;  
	public enum   tableFldConstants
	{
		keyid,flid,date,discussiontype,discussionno,preparedby ,personrole,
		discussionsumm,otherparticipants,susadoneJh,susadoneby, tempfield3,
		tempfield4, tempfield5, createdby,active,createdon,modifiedon
	}
  
	public GenTlSusamstNew()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getSusnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSusnKeyid(String susnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = susnKeyid;
	}

	public String getSusnFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSusnFlid(String susnFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = susnFlid;
	}

	public String getSusnDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setSusnDate(String susnDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = susnDate;
	}

	public String getSusnDiscussionType() {
		return (String) saveArray[ tableFldConstants.discussiontype.ordinal() ];
	}

	public void setSusnDiscussionType(String susnDiscussionType) {
		saveArray[ tableFldConstants.discussiontype.ordinal() ] = susnDiscussionType;
	}
	
	public String getSusnDiscussionNo() {
		return (String) saveArray[ tableFldConstants.discussionno.ordinal() ];
	}

	public void setSusnDiscussionNo(String susnDiscussionNo) {
		saveArray[ tableFldConstants.discussionno.ordinal() ] = susnDiscussionNo;
	}


	public String getSusnPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setSusnPreparedby(String susnPrearedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = susnPrearedby;
	}

	public String getSusnPersonRole() {
		return (String) saveArray[ tableFldConstants.personrole.ordinal() ];
	}

	public void setSusnPersonRole(String susnPersonRole) {
		saveArray[ tableFldConstants.personrole.ordinal() ] = susnPersonRole;
	}
	public String getSusnDiscussionSumm() {
		return (String) saveArray[ tableFldConstants.discussionsumm.ordinal() ];
	}

	public void setSusnDiscussionSumm(String susnDiscussionSumm) {
		saveArray[ tableFldConstants.discussionsumm.ordinal() ] = susnDiscussionSumm;
	}

	public String getSusnOtherParticipants() {
		return (String) saveArray[ tableFldConstants.otherparticipants.ordinal() ];
	}

	public void setSusnOtherParticipants(String susnOtherParticipants) {
		saveArray[ tableFldConstants.otherparticipants.ordinal() ] =susnOtherParticipants;
	}

	public String getSusnSusaDoneJh() {
		return (String) saveArray[ tableFldConstants.susadoneJh.ordinal() ];
	}

	public void setSusnSusaDoneJh(String susnSusadonejh) {
		saveArray[ tableFldConstants.susadoneJh.ordinal() ] = susnSusadonejh;
	}

	public String getSusnSusaDoneby() {
		return (String) saveArray[ tableFldConstants.susadoneby.ordinal() ];
	}

	public void setSusnSusaDoneby(String susnSusaDoneby) {
		saveArray[ tableFldConstants.susadoneby.ordinal() ]=susnSusaDoneby;
	}
	
	public String getSusnTempField3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSusnTempField3(String susnTempField3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = susnTempField3;
	}

	public String getSusnTempField4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSusnTempField4(String susnTempField4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = susnTempField4;
	}
	public String getSusnTempField5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSusnTempField5(String susnTempField5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = susnTempField5;
	}
	public String getSusnCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSusnCreatedby(String susnCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =susnCreatedby;
	}
	
	public String getSusnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSusnActive(String susnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = susnActive;
	}

	public String getSusnCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSusnCreatedon(String susnCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = susnCreatedon;
	}

	public String getSusnModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSusnModifiedon(String susnModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = susnModifiedon;
	}
	
	public List<GenTlSusaAddBehaviormst> getAddBehaviourMst() {
		return AddBehaviourMst;
	}

	public void setAddBehaviourMst(List<GenTlSusaAddBehaviormst> genTlSusaAddBehaviormst) {
		this.AddBehaviourMst=genTlSusaAddBehaviormst;
	}
}

