package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_BdmTlNewphncausereq {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, requesteddate, requestedby, docno, docdate, phenomenatype
		, proposedphn, iscausereq, proposedcause, approvedphn, iscausereqapp
		, approvedcause, isalreadyexist, oldphenomenaid, oldcauseid, newphenomenaid
		, newcauseid, approvedby, approveddate, approvedflag, active
		, createdby, createdon, modifiedon
	}

	public BAL_BdmTlNewphncausereq()
	{
		saveArray = new  Object [ 24 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getBnprKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBnprKeyid(String bnprkeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bnprkeyid;
	}

	public String getBnprRequesteddate() {
		return (String) saveArray[ tableFldConstants.requesteddate.ordinal() ];
	}

	public void setBnprRequesteddate(String bnprrequesteddate) {
		saveArray[ tableFldConstants.requesteddate.ordinal() ] = bnprrequesteddate;
	}

	public String getBnprRequestedby() {
		return (String) saveArray[ tableFldConstants.requestedby.ordinal() ];
	}

	public void setBnprRequestedby(String bnprrequestedby) {
		saveArray[ tableFldConstants.requestedby.ordinal() ] = bnprrequestedby;
	}

	public String getBnprDocno() {
		return (String) saveArray[ tableFldConstants.docno.ordinal() ];
	}

	public void setBnprDocno(String bnprdocno) {
		saveArray[ tableFldConstants.docno.ordinal() ] = bnprdocno;
	}

	public String getBnprDocdate() {
		return (String) saveArray[ tableFldConstants.docdate.ordinal() ];
	}

	public void setBnprDocdate(String bnprdocdate) {
		saveArray[ tableFldConstants.docdate.ordinal() ] = bnprdocdate;
	}

	public String getBnprPhenomenatype() {
		return (String) saveArray[ tableFldConstants.phenomenatype.ordinal() ];
	}

	public void setBnprPhenomenatype(String bnprphenomenatype) {
		saveArray[ tableFldConstants.phenomenatype.ordinal() ] = bnprphenomenatype;
	}

	public String getBnprProposedphn() {
		return (String) saveArray[ tableFldConstants.proposedphn.ordinal() ];
	}

	public void setBnprProposedphn(String bnprproposedphn) {
		saveArray[ tableFldConstants.proposedphn.ordinal() ] = bnprproposedphn;
	}

	public String getBnprIscausereq() {
		return (String) saveArray[ tableFldConstants.iscausereq.ordinal() ];
	}

	public void setBnprIscausereq(String bnpriscausereq) {
		saveArray[ tableFldConstants.iscausereq.ordinal() ] = bnpriscausereq;
	}

	public String getBnprProposedcause() {
		return (String) saveArray[ tableFldConstants.proposedcause.ordinal() ];
	}

	public void setBnprProposedcause(String bnprproposedcause) {
		saveArray[ tableFldConstants.proposedcause.ordinal() ] = bnprproposedcause;
	}

	public String getBnprApprovedphn() {
		return (String) saveArray[ tableFldConstants.approvedphn.ordinal() ];
	}

	public void setBnprApprovedphn(String bnprapprovedphn) {
		saveArray[ tableFldConstants.approvedphn.ordinal() ] = bnprapprovedphn;
	}

	public String getBnprIscausereqapp() {
		return (String) saveArray[ tableFldConstants.iscausereqapp.ordinal() ];
	}

	public void setBnprIscausereqapp(String bnpriscausereqapp) {
		saveArray[ tableFldConstants.iscausereqapp.ordinal() ] = bnpriscausereqapp;
	}

	public String getBnprApprovedcause() {
		return (String) saveArray[ tableFldConstants.approvedcause.ordinal() ];
	}

	public void setBnprApprovedcause(String bnprapprovedcause) {
		saveArray[ tableFldConstants.approvedcause.ordinal() ] = bnprapprovedcause;
	}

	public String getBnprIsalreadyexist() {
		return (String) saveArray[ tableFldConstants.isalreadyexist.ordinal() ];
	}

	public void setBnprIsalreadyexist(String bnprisalreadyexist) {
		saveArray[ tableFldConstants.isalreadyexist.ordinal() ] = bnprisalreadyexist;
	}

	public String getBnprOldphenomenaid() {
		return (String) saveArray[ tableFldConstants.oldphenomenaid.ordinal() ];
	}

	public void setBnprOldphenomenaid(String bnproldphenomenaid) {
		saveArray[ tableFldConstants.oldphenomenaid.ordinal() ] = bnproldphenomenaid;
	}

	public String getBnprOldcauseid() {
		return (String) saveArray[ tableFldConstants.oldcauseid.ordinal() ];
	}

	public void setBnprOldcauseid(String bnproldcauseid) {
		saveArray[ tableFldConstants.oldcauseid.ordinal() ] = bnproldcauseid;
	}

	public String getBnprNewphenomenaid() {
		return (String) saveArray[ tableFldConstants.newphenomenaid.ordinal() ];
	}

	public void setBnprNewphenomenaid(String bnprnewphenomenaid) {
		saveArray[ tableFldConstants.newphenomenaid.ordinal() ] = bnprnewphenomenaid;
	}

	public String getBnprNewcauseid() {
		return (String) saveArray[ tableFldConstants.newcauseid.ordinal() ];
	}

	public void setBnprNewcauseid(String bnprnewcauseid) {
		saveArray[ tableFldConstants.newcauseid.ordinal() ] = bnprnewcauseid;
	}

	public String getBnprApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setBnprApprovedby(String bnprapprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = bnprapprovedby;
	}

	public String getBnprApproveddate() {
		return (String) saveArray[ tableFldConstants.approveddate.ordinal() ];
	}

	public void setBnprApproveddate(String bnprapproveddate) {
		saveArray[ tableFldConstants.approveddate.ordinal() ] = bnprapproveddate;
	}

	public String getBnprApprovedflag() {
		return (String) saveArray[ tableFldConstants.approvedflag.ordinal() ];
	}

	public void setBnprApprovedflag(String bnprapprovedflag) {
		saveArray[ tableFldConstants.approvedflag.ordinal() ] = bnprapprovedflag;
	}

	public String getBnprActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBnprActive(String bnpractive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bnpractive;
	}

	public String getBnprCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBnprCreatedby(String bnprcreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bnprcreatedby;
	}

	public String getBnprCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBnprCreatedon(String bnprcreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bnprcreatedon;
	}

	public String getBnprModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBnprModifiedon(String bnprmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bnprmodifiedon;
	}

}

