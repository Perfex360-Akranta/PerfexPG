package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhkTlKaizenmst {

	private  Object [] saveArray = null;  
	
	private List<GenTlAllmoduleimgfile> allmoduleimgfile;
	public enum   tableFldConstants
	{
		keyid, date, suggestionno, suggestiondate, factoryid, sectionid
		, cellid, machineid, description, implementedby, implementeddate
		, approvedby, approveddate, beforedescription, afterdescription
		, categoryid, problemdescription, countermeasure, improvement
		, results, benefits, remarks, status, active, createdby, createdon
		, modifiedon
	}

	public JhkTlKaizenmst()
	{
		saveArray = new  Object [ 27 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getJhkzKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setJhkzKeyid(String jhkzKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = jhkzKeyid;
	}

	public String getJhkzDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setJhkzDate(String jhkzDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = jhkzDate;
	}

	public String getJhkzSuggestionno() {
		return (String) saveArray[ tableFldConstants.suggestionno.ordinal() ];
	}

	public void setJhkzSuggestionno(String jhkzSuggestionno) {
		saveArray[ tableFldConstants.suggestionno.ordinal() ] = jhkzSuggestionno;
	}

	public String getJhkzSuggestiondate() {
		return (String) saveArray[ tableFldConstants.suggestiondate.ordinal() ];
	}

	public void setJhkzSuggestiondate(String jhkzSuggestiondate) {
		saveArray[ tableFldConstants.suggestiondate.ordinal() ] = jhkzSuggestiondate;
	}

	public String getJhkzFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setJhkzFactoryid(String jhkzFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = jhkzFactoryid;
	}

	public String getJhkzSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setJhkzSectionid(String jhkzSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = jhkzSectionid;
	}

	public String getJhkzCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setJhkzCellid(String jhkzCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = jhkzCellid;
	}

	public String getJhkzMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setJhkzMachineid(String jhkzMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = jhkzMachineid;
	}

	public String getJhkzDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setJhkzDescription(String jhkzDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = jhkzDescription;
	}

	public String getJhkzImplementedby() {
		return (String) saveArray[ tableFldConstants.implementedby.ordinal() ];
	}

	public void setJhkzImplementedby(String jhkzImplementedby) {
		saveArray[ tableFldConstants.implementedby.ordinal() ] = jhkzImplementedby;
	}

	public String getJhkzImplementeddate() {
		return (String) saveArray[ tableFldConstants.implementeddate.ordinal() ];
	}

	public void setJhkzImplementeddate(String jhkzImplementeddate) {
		saveArray[ tableFldConstants.implementeddate.ordinal() ] = jhkzImplementeddate;
	}

	public String getJhkzApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setJhkzApprovedby(String jhkzApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = jhkzApprovedby;
	}

	public String getJhkzApproveddate() {
		return (String) saveArray[ tableFldConstants.approveddate.ordinal() ];
	}

	public void setJhkzApproveddate(String jhkzApproveddate) {
		saveArray[ tableFldConstants.approveddate.ordinal() ] = jhkzApproveddate;
	}

	public String getJhkzBeforedescription() {
		return (String) saveArray[ tableFldConstants.beforedescription.ordinal() ];
	}

	public void setJhkzBeforedescription(String jhkzBeforedescription) {
		saveArray[ tableFldConstants.beforedescription.ordinal() ] = jhkzBeforedescription;
	}

	public String getJhkzAfterdescription() {
		return (String) saveArray[ tableFldConstants.afterdescription.ordinal() ];
	}

	public void setJhkzAfterdescription(String jhkzAfterdescription) {
		saveArray[ tableFldConstants.afterdescription.ordinal() ] = jhkzAfterdescription;
	}

	public String getJhkzCategoryid() {
		return (String) saveArray[ tableFldConstants.categoryid.ordinal() ];
	}

	public void setJhkzCategoryid(String jhkzCategoryid) {
		saveArray[ tableFldConstants.categoryid.ordinal() ] = jhkzCategoryid;
	}

	public String getJhkzProblemdescription() {
		return (String) saveArray[ tableFldConstants.problemdescription.ordinal() ];
	}

	public void setJhkzProblemdescription(String jhkzProblemdescription) {
		saveArray[ tableFldConstants.problemdescription.ordinal() ] = jhkzProblemdescription;
	}

	public String getJhkzCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setJhkzCountermeasure(String jhkzCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = jhkzCountermeasure;
	}

	public String getJhkzImprovement() {
		return (String) saveArray[ tableFldConstants.improvement.ordinal() ];
	}

	public void setJhkzImprovement(String jhkzImprovement) {
		saveArray[ tableFldConstants.improvement.ordinal() ] = jhkzImprovement;
	}

	public String getJhkzResults() {
		return (String) saveArray[ tableFldConstants.results.ordinal() ];
	}

	public void setJhkzResults(String jhkzResults) {
		saveArray[ tableFldConstants.results.ordinal() ] = jhkzResults;
	}

	public String getJhkzBenefits() {
		return (String) saveArray[ tableFldConstants.benefits.ordinal() ];
	}

	public void setJhkzBenefits(String jhkzBenefits) {
		saveArray[ tableFldConstants.benefits.ordinal() ] = jhkzBenefits;
	}

	public String getJhkzRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setJhkzRemarks(String jhkzRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = jhkzRemarks;
	}

	public String getJhkzStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setJhkzStatus(String jhkzStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = jhkzStatus;
	}

	public String getJhkzActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setJhkzActive(String jhkzActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = jhkzActive;
	}

	public String getJhkzCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setJhkzCreatedby(String jhkzCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = jhkzCreatedby;
	}

	public String getJhkzCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setJhkzCreatedon(String jhkzCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = jhkzCreatedon;
	}

	public String getJhkzModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setJhkzModifiedon(String jhkzModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = jhkzModifiedon;
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
		
	}

	public void setAllmoduleimgfile(List<GenTlAllmoduleimgfile> allmoduleimgfile) {
		this.allmoduleimgfile = allmoduleimgfile;
		
	}
	public List<GenTlAllmoduleimgfile> getAllmoduleimgfile() {
		return allmoduleimgfile;
	}

}

