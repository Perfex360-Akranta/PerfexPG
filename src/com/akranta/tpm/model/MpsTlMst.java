package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.MspTlMst.tableFldConstants;

public class MpsTlMst {

	private  Object [] saveArray = null;  
	private List<GenTlAllmoduleimgfile> allmoduleimgfile;
	private List <MpsTlImprovementsdtl> mpsTlImprovementsdtl;
	private List <MpsTlDtl> mpsTlDtl;

	public enum   tableFldConstants
	{
		keyid, createddate, improvementtheme, problem, registrationcode
		, controlno, cellid, machineid, workcentreid, costcentreid, cause
		, beforeimprovement, afterimprovement, iscosteffective, standardization
		, change, others, totalamount, effsavingsreported, responsiblity
		, status, completedby, completeddate, completedremarks, opinion
		, ishdrequired, tempfield1, tempfield2, tempfield3, tempfield4
		, modifiedby, elementid, flid, active, createdby, createdon, modifiedon
	}

	public MpsTlMst()
	{
		saveArray = new  Object [ 37 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}


	public String getMpsmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMpsmKeyid(String mpsmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mpsmKeyid;
	}

	public String getMpsmCreateddate() {
		return (String) saveArray[ tableFldConstants.createddate.ordinal() ];
	}

	public void setMpsmCreateddate(String mpsmCreateddate) {
		saveArray[ tableFldConstants.createddate.ordinal() ] = mpsmCreateddate;
	}

	public String getMpsmImprovementtheme() {
		return (String) saveArray[ tableFldConstants.improvementtheme.ordinal() ];
	}

	public void setMpsmImprovementtheme(String mpsmImprovementtheme) {
		saveArray[ tableFldConstants.improvementtheme.ordinal() ] = mpsmImprovementtheme;
	}

	public String getMpsmProblem() {
		return (String) saveArray[ tableFldConstants.problem.ordinal() ];
	}

	public void setMpsmProblem(String mpsmProblem) {
		saveArray[ tableFldConstants.problem.ordinal() ] = mpsmProblem;
	}

	public String getMpsmRegistrationcode() {
		return (String) saveArray[ tableFldConstants.registrationcode.ordinal() ];
	}

	public void setMpsmRegistrationcode(String mpsmRegistrationcode) {
		saveArray[ tableFldConstants.registrationcode.ordinal() ] = mpsmRegistrationcode;
	}

	public String getMpsmControlno() {
		return (String) saveArray[ tableFldConstants.controlno.ordinal() ];
	}

	public void setMpsmControlno(String mpsmControlno) {
		saveArray[ tableFldConstants.controlno.ordinal() ] = mpsmControlno;
	}

	public String getMpsmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setMpsmCellid(String mpsmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = mpsmCellid;
	}

	public String getMpsmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMpsmMachineid(String mpsmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = mpsmMachineid;
	}

	public String getMpsmWorkcentreid() {
		return (String) saveArray[ tableFldConstants.workcentreid.ordinal() ];
	}

	public void setMpsmWorkcentreid(String mpsmWorkcentreid) {
		saveArray[ tableFldConstants.workcentreid.ordinal() ] = mpsmWorkcentreid;
	}

	public String getMpsmCostcentreid() {
		return (String) saveArray[ tableFldConstants.costcentreid.ordinal() ];
	}

	public void setMpsmCostcentreid(String mpsmCostcentreid) {
		saveArray[ tableFldConstants.costcentreid.ordinal() ] = mpsmCostcentreid;
	}

	public String getMpsmCause() {
		return (String) saveArray[ tableFldConstants.cause.ordinal() ];
	}

	public void setMpsmCause(String mpsmCause) {
		saveArray[ tableFldConstants.cause.ordinal() ] = mpsmCause;
	}

	public String getMpsmBeforeimprovement() {
		return (String) saveArray[ tableFldConstants.beforeimprovement.ordinal() ];
	}

	public void setMpsmBeforeimprovement(String mpsmBeforeimprovement) {
		saveArray[ tableFldConstants.beforeimprovement.ordinal() ] = mpsmBeforeimprovement;
	}

	public String getMpsmAfterimprovement() {
		return (String) saveArray[ tableFldConstants.afterimprovement.ordinal() ];
	}

	public void setMpsmAfterimprovement(String mpsmAfterimprovement) {
		saveArray[ tableFldConstants.afterimprovement.ordinal() ] = mpsmAfterimprovement;
	}

	public String getMpsmIscosteffective() {
		return (String) saveArray[ tableFldConstants.iscosteffective.ordinal() ];
	}

	public void setMpsmIscosteffective(String mpsmIscosteffective) {
		saveArray[ tableFldConstants.iscosteffective.ordinal() ] = mpsmIscosteffective;
	}

	public String getMpsmStandardization() {
		return (String) saveArray[ tableFldConstants.standardization.ordinal() ];
	}

	public void setMpsmStandardization(String mpsmStandardization) {
		saveArray[ tableFldConstants.standardization.ordinal() ] = mpsmStandardization;
	}

	public String getMpsmChange() {
		return (String) saveArray[ tableFldConstants.change.ordinal() ];
	}

	public void setMpsmChange(String mpsmChange) {
		saveArray[ tableFldConstants.change.ordinal() ] = mpsmChange;
	}

	public String getMpsmOthers() {
		return (String) saveArray[ tableFldConstants.others.ordinal() ];
	}

	public void setMpsmOthers(String mpsmOthers) {
		saveArray[ tableFldConstants.others.ordinal() ] = mpsmOthers;
	}

	public String getMpsmTotalamount() {
		return (String) saveArray[ tableFldConstants.totalamount.ordinal() ];
	}

	public void setMpsmTotalamount(String mpsmTotalamount) {
		saveArray[ tableFldConstants.totalamount.ordinal() ] = mpsmTotalamount;
	}

	public String getMpsmEffsavingsreported() {
		return (String) saveArray[ tableFldConstants.effsavingsreported.ordinal() ];
	}

	public void setMpsmEffsavingsreported(String mpsmEffsavingsreported) {
		saveArray[ tableFldConstants.effsavingsreported.ordinal() ] = mpsmEffsavingsreported;
	}

	public String getMpsmResponsiblity() {
		return (String) saveArray[ tableFldConstants.responsiblity.ordinal() ];
	}

	public void setMpsmResponsiblity(String mpsmResponsiblity) {
		saveArray[ tableFldConstants.responsiblity.ordinal() ] = mpsmResponsiblity;
	}

	public String getMpsmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setMpsmStatus(String mpsmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = mpsmStatus;
	}

	public String getMpsmCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setMpsmCompletedby(String mpsmCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = mpsmCompletedby;
	}

	public String getMpsmCompleteddate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}

	public void setMpsmCompleteddate(String mpsmCompleteddate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = mpsmCompleteddate;
	}

	public String getMpsmCompletedremarks() {
		return (String) saveArray[ tableFldConstants.completedremarks.ordinal() ];
	}

	public void setMpsmCompletedremarks(String mpsmCompletedremarks) {
		saveArray[ tableFldConstants.completedremarks.ordinal() ] = mpsmCompletedremarks;
	}

	public String getMpsmOpinion() {
		return (String) saveArray[ tableFldConstants.opinion.ordinal() ];
	}

	public void setMpsmOpinion(String mpsmOpinion) {
		saveArray[ tableFldConstants.opinion.ordinal() ] = mpsmOpinion;
	}

	public String getMpsmIshdrequired() {
		return (String) saveArray[ tableFldConstants.ishdrequired.ordinal() ];
	}

	public void setMpsmIshdrequired(String mpsmIshdrequired) {
		saveArray[ tableFldConstants.ishdrequired.ordinal() ] = mpsmIshdrequired;
	}

	public String getMpsmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMpsmTempfield1(String mpsmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mpsmTempfield1;
	}

	public String getMpsmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMpsmTempfield2(String mpsmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mpsmTempfield2;
	}

	public String getMpsmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMpsmTempfield3(String mpsmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mpsmTempfield3;
	}

	public String getMpsmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMpsmTempfield4(String mpsmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mpsmTempfield4;
	}

	public String getMpsmModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setMpsmModifiedby(String mpsmModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = mpsmModifiedby;
	}

	public String getMpsmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setMpsmElementid(String mpsmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = mpsmElementid;
	}

	public String getMpsmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMpsmFlid(String mpsmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = mpsmFlid;
	}

	public String getMpsmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMpsmActive(String mpsmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mpsmActive;
	}

	public String getMpsmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMpsmCreatedby(String mpsmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mpsmCreatedby;
	}

	public String getMpsmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMpsmCreatedon(String mpsmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mpsmCreatedon;
	}

	public String getMpsmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMpsmModifiedon(String mpsmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mpsmModifiedon;
	}

	
	public void setAllmoduleimgfile(List<GenTlAllmoduleimgfile> allmoduleimgfile) {
		this.allmoduleimgfile = allmoduleimgfile;
	}

	public List<GenTlAllmoduleimgfile> getAllmoduleimgfile() {
		return allmoduleimgfile;
	}

	public void setMpsTlImprovementsdtl(List <MpsTlImprovementsdtl> mpsTlImprovementsdtl) {
		this.mpsTlImprovementsdtl = mpsTlImprovementsdtl;
	}

	public List <MpsTlImprovementsdtl> getMpsTlImprovementsdtl() {
		return mpsTlImprovementsdtl;
	}

	public void setMpsTlDtl(List <MpsTlDtl> mpsTlDtl) {
		this.mpsTlDtl = mpsTlDtl;
	}

	public List <MpsTlDtl> getMpsTlDtl() {
		return mpsTlDtl;
	}

	



}

