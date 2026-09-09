package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlDmcfipcreationmst {

	private  Object [] saveArray = null;  
	private String kkeyid;
	private String mode;
	private String elementType;
	private List<KznTlProjectResourceLink> kznTlProjectResourceLinkList=null;
	private GenTlWorkflowInfo genTlWorkflowInfo;
	private String elementid;

	public enum   tableFldConstants
	{
		keyid, flid, startdate, enddate, projectname, area, projectchamp
		, projectno, benefits, savings, projectmetrics, problemstatement
		, businesscase, goalobj, scopeconst, definestage, measurestage
		, analysestage, controlstage, improvestage, closurestage, imprcategory
		, istangible, isintangible, verifiedamnt, amtverifyremarks, wave
		, oldresponsibility, belt, tempfield4, active, createdby
		, createdon, modifiedon
	}

	public KznTlDmcfipcreationmst()
	{
		saveArray = new  Object [ 34 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object [] saveArray) {		
		 this.saveArray = saveArray;
	}
	public String getDmcmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDmcmKeyid(String dmcmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dmcmKeyid;
	}

	public String getDmcmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setDmcmFlid(String dmcmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = dmcmFlid;
	}

	public String getDmcmStartdate() {
		return (String) saveArray[ tableFldConstants.startdate.ordinal() ];
	}

	public void setDmcmStartdate(String dmcmStartdate) {
		saveArray[ tableFldConstants.startdate.ordinal() ] = dmcmStartdate;
	}

	public String getDmcmEnddate() {
		return (String) saveArray[ tableFldConstants.enddate.ordinal() ];
	}

	public void setDmcmEnddate(String dmcmEnddate) {
		saveArray[ tableFldConstants.enddate.ordinal() ] = dmcmEnddate;
	}

	public String getDmcmProjectname() {
		return (String) saveArray[ tableFldConstants.projectname.ordinal() ];
	}

	public void setDmcmProjectname(String dmcmProjectname) {
		saveArray[ tableFldConstants.projectname.ordinal() ] = dmcmProjectname;
	}

	public String getDmcmArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setDmcmArea(String dmcmArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = dmcmArea;
	}

	public String getDmcmProjectchamp() {
		return (String) saveArray[ tableFldConstants.projectchamp.ordinal() ];
	}

	public void setDmcmProjectchamp(String dmcmProjectchamp) {
		saveArray[ tableFldConstants.projectchamp.ordinal() ] = dmcmProjectchamp;
	}

	public String getDmcmProjectno() {
		return (String) saveArray[ tableFldConstants.projectno.ordinal() ];
	}

	public void setDmcmProjectno(String dmcmProjectno) {
		saveArray[ tableFldConstants.projectno.ordinal() ] = dmcmProjectno;
	}

	public String getDmcmBenefits() {
		return (String) saveArray[ tableFldConstants.benefits.ordinal() ];
	}

	public void setDmcmBenefits(String dmcmBenefits) {
		saveArray[ tableFldConstants.benefits.ordinal() ] = dmcmBenefits;
	}

	public String getDmcmSavings() {
		return (String) saveArray[ tableFldConstants.savings.ordinal() ];
	}

	public void setDmcmSavings(String dmcmSavings) {
		saveArray[ tableFldConstants.savings.ordinal() ] = dmcmSavings;
	}

	public String getDmcmProjectmetrics() {
		return (String) saveArray[ tableFldConstants.projectmetrics.ordinal() ];
	}

	public void setDmcmProjectmetrics(String dmcmProjectmetrics) {
		saveArray[ tableFldConstants.projectmetrics.ordinal() ] = dmcmProjectmetrics;
	}

	public String getDmcmProblemstatement() {
		return (String) saveArray[ tableFldConstants.problemstatement.ordinal() ];
	}

	public void setDmcmProblemstatement(String dmcmProblemstatement) {
		saveArray[ tableFldConstants.problemstatement.ordinal() ] = dmcmProblemstatement;
	}

	public String getDmcmBusinesscase() {
		return (String) saveArray[ tableFldConstants.businesscase.ordinal() ];
	}

	public void setDmcmBusinesscase(String dmcmBusinesscase) {
		saveArray[ tableFldConstants.businesscase.ordinal() ] = dmcmBusinesscase;
	}

	public String getDmcmGoalobj() {
		return (String) saveArray[ tableFldConstants.goalobj.ordinal() ];
	}

	public void setDmcmGoalobj(String dmcmGoalobj) {
		saveArray[ tableFldConstants.goalobj.ordinal() ] = dmcmGoalobj;
	}

	public String getDmcmScopeconst() {
		return (String) saveArray[ tableFldConstants.scopeconst.ordinal() ];
	}

	public void setDmcmScopeconst(String dmcmScopeconst) {
		saveArray[ tableFldConstants.scopeconst.ordinal() ] = dmcmScopeconst;
	}

	public String getDmcmDefinestage() {
		return (String) saveArray[ tableFldConstants.definestage.ordinal() ];
	}

	public void setDmcmDefinestage(String dmcmDefinestage) {
		saveArray[ tableFldConstants.definestage.ordinal() ] = dmcmDefinestage;
	}

	public String getDmcmMeasurestage() {
		return (String) saveArray[ tableFldConstants.measurestage.ordinal() ];
	}

	public void setDmcmMeasurestage(String dmcmMeasurestage) {
		saveArray[ tableFldConstants.measurestage.ordinal() ] = dmcmMeasurestage;
	}

	public String getDmcmAnalysestage() {
		return (String) saveArray[ tableFldConstants.analysestage.ordinal() ];
	}

	public void setDmcmAnalysestage(String dmcmAnalysestage) {
		saveArray[ tableFldConstants.analysestage.ordinal() ] = dmcmAnalysestage;
	}

	public String getDmcmControlstage() {
		return (String) saveArray[ tableFldConstants.controlstage.ordinal() ];
	}

	public void setDmcmControlstage(String dmcmControlstage) {
		saveArray[ tableFldConstants.controlstage.ordinal() ] = dmcmControlstage;
	}

	public String getDmcmImprovestage() {
		return (String) saveArray[ tableFldConstants.improvestage.ordinal() ];
	}

	public void setDmcmImprovestage(String dmcmImprovestage) {
		saveArray[ tableFldConstants.improvestage.ordinal() ] = dmcmImprovestage;
	}

	public String getDmcmClosurestage() {
		return (String) saveArray[ tableFldConstants.closurestage.ordinal() ];
	}

	public void setDmcmClosurestage(String dmcmClosurestage) {
		saveArray[ tableFldConstants.closurestage.ordinal() ] = dmcmClosurestage;
	}

	public String getDmcmImprcategory() {
		return (String) saveArray[ tableFldConstants.imprcategory.ordinal() ];
	}

	public void setDmcmImprcategory(String dmcmImprcategory) {
		saveArray[ tableFldConstants.imprcategory.ordinal() ] = dmcmImprcategory;
	}

	public String getDmcmIstangible() {
		return (String) saveArray[ tableFldConstants.istangible.ordinal() ];
	}

	public void setDmcmIstangible(String dmcmIstangible) {
		saveArray[ tableFldConstants.istangible.ordinal() ] = dmcmIstangible;
	}

	public String getDmcmIsintangible() {
		return (String) saveArray[ tableFldConstants.isintangible.ordinal() ];
	}

	public void setDmcmIsintangible(String dmcmIsintangible) {
		saveArray[ tableFldConstants.isintangible.ordinal() ] = dmcmIsintangible;
	}

	public String getDmcmVerifiedamnt() {
		return (String) saveArray[ tableFldConstants.verifiedamnt.ordinal() ];
	}

	public void setDmcmVerifiedamnt(String dmcmVerifiedamnt) {
		saveArray[ tableFldConstants.verifiedamnt.ordinal() ] = dmcmVerifiedamnt;
	}

	public String getDmcmAmtverifyremarks() {
		return (String) saveArray[ tableFldConstants.amtverifyremarks.ordinal() ];
	}

	public void setDmcmAmtverifyremarks(String dmcmAmtverifyremarks) {
		saveArray[ tableFldConstants.amtverifyremarks.ordinal() ] = dmcmAmtverifyremarks;
	}

	public String getDmcmWave() {
		return (String) saveArray[ tableFldConstants.wave.ordinal() ];
	}

	public void setDmcmWave(String dmcmWave) {
		saveArray[ tableFldConstants.wave.ordinal() ] = dmcmWave;
	}

	public String getDmcmOldresponsibility() {
		return (String) saveArray[ tableFldConstants.oldresponsibility.ordinal() ];
	}

	public void setDmcmOldresponsibility(String dmcmOldresponsibility) {
		saveArray[ tableFldConstants.oldresponsibility.ordinal() ] = dmcmOldresponsibility;
	}
	public String getDmcmBelt() {
		return (String) saveArray[ tableFldConstants.belt.ordinal() ];
	}

	public void setDmcmBelt(String dmcmBelt) {
		saveArray[ tableFldConstants.belt.ordinal() ] = dmcmBelt;
	}

	public String getDmcmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDmcmTempfield4(String dmcmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dmcmTempfield4;
	}

	public String getDmcmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDmcmActive(String dmcmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dmcmActive;
	}

	public String getDmcmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDmcmCreatedby(String dmcmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dmcmCreatedby;
	}

	public String getDmcmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDmcmCreatedon(String dmcmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dmcmCreatedon;
	}

	public String getDmcmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDmcmModifiedon(String dmcmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dmcmModifiedon;
	}
	public void setKkeyid(String kkeyid) {
		this.kkeyid = kkeyid;
	}

	public String getKkeyid() {
		return kkeyid;
	}

	public void setWorkFlowApp(GenTlWorkflowInfo genTlWorkflowInfo) {
		// TODO Auto-generated method stub
		this.genTlWorkflowInfo= genTlWorkflowInfo;
	}
	public GenTlWorkflowInfo getWorkFlowApp() {
		// TODO Auto-generated method stub
		return this.genTlWorkflowInfo;		
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}
	public List<KznTlProjectResourceLink> getProjectResourceList() {
		return kznTlProjectResourceLinkList;
	}

	public void setProjectResourceList(List<KznTlProjectResourceLink> kznTlProjectResourceLinkList) {
		this.kznTlProjectResourceLinkList = kznTlProjectResourceLinkList;
	}

}

