package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SheTlIncidentmst {

	private  Object [] saveArray = null;  
	private List<GenTlAllmoduleimgfile> allmoduleimgfile;

	public enum   tableFldConstants
	{
		keyid, locn_keyid, fact_keyid, sect_keyid, cell_keyid, mach_keyid
		, occurred_date, occurred_shift, reported_date, reported_shift
		, reported_by, department, division, area, accident_type, accident_desc
		, immediate_action, recommendations, noofemployees, status, exact_desc
		, exact_type, analysed_by, analysed_date, phenomena, whywhyflag
		, rootcause, countermeasure, rootcausedueto, idea, analysed_shift
		, yyno, lossproperty, directcost, tempfield5, tempfield6
		, tempfield7, tempfield8, tempfield9, tempfield10, tempfield11
		, tempfield12, tempfield13, tempfield14, tempfield15, tempfield16
		, tempfield17, tempfield18, tempfield19, tempfield20, elementid,flid,active
		, createdby, createdon, modifiedon
	}

	public SheTlIncidentmst()
	{
		saveArray = new  Object [ 56];
		setAllmoduleimgfile(new ArrayList<GenTlAllmoduleimgfile>());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getSincKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSincKeyid(String sincKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sincKeyid;
	}

	public String getSincLocnKeyid() {
		return (String) saveArray[ tableFldConstants.locn_keyid.ordinal() ];
	}

	public void setSincLocnKeyid(String sincLocnKeyid) {
		saveArray[ tableFldConstants.locn_keyid.ordinal() ] = sincLocnKeyid;
	}

	public String getSincFactKeyid() {
		return (String) saveArray[ tableFldConstants.fact_keyid.ordinal() ];
	}

	public void setSincFactKeyid(String sincFactKeyid) {
		saveArray[ tableFldConstants.fact_keyid.ordinal() ] = sincFactKeyid;
	}

	public String getSincSectKeyid() {
		return (String) saveArray[ tableFldConstants.sect_keyid.ordinal() ];
	}

	public void setSincSectKeyid(String sincSectKeyid) {
		saveArray[ tableFldConstants.sect_keyid.ordinal() ] = sincSectKeyid;
	}

	public String getSincCellKeyid() {
		return (String) saveArray[ tableFldConstants.cell_keyid.ordinal() ];
	}

	public void setSincCellKeyid(String sincCellKeyid) {
		saveArray[ tableFldConstants.cell_keyid.ordinal() ] = sincCellKeyid;
	}

	public String getSincMachKeyid() {
		return (String) saveArray[ tableFldConstants.mach_keyid.ordinal() ];
	}

	public void setSincMachKeyid(String sincMachKeyid) {
		saveArray[ tableFldConstants.mach_keyid.ordinal() ] = sincMachKeyid;
	}

	public String getSincOccurredDate() {
		return (String) saveArray[ tableFldConstants.occurred_date.ordinal() ];
	}

	public void setSincOccurredDate(String sincOccurredDate) {
		saveArray[ tableFldConstants.occurred_date.ordinal() ] = sincOccurredDate;
	}

	public String getSincOccurredShift() {
		return (String) saveArray[ tableFldConstants.occurred_shift.ordinal() ];
	}

	public void setSincOccurredShift(String sincOccurredShift) {
		saveArray[ tableFldConstants.occurred_shift.ordinal() ] = sincOccurredShift;
	}

	public String getSincReportedDate() {
		return (String) saveArray[ tableFldConstants.reported_date.ordinal() ];
	}

	public void setSincReportedDate(String sincReportedDate) {
		saveArray[ tableFldConstants.reported_date.ordinal() ] = sincReportedDate;
	}

	public String getSincReportedShift() {
		return (String) saveArray[ tableFldConstants.reported_shift.ordinal() ];
	}

	public void setSincReportedShift(String sincReportedShift) {
		saveArray[ tableFldConstants.reported_shift.ordinal() ] = sincReportedShift;
	}

	public String getSincReportedBy() {
		return (String) saveArray[ tableFldConstants.reported_by.ordinal() ];
	}

	public void setSincReportedBy(String sincReportedBy) {
		saveArray[ tableFldConstants.reported_by.ordinal() ] = sincReportedBy;
	}

	public String getSincDepartment() {
		return (String) saveArray[ tableFldConstants.department.ordinal() ];
	}

	public void setSincDepartment(String sincDepartment) {
		saveArray[ tableFldConstants.department.ordinal() ] = sincDepartment;
	}

	public String getSincDivision() {
		return (String) saveArray[ tableFldConstants.division.ordinal() ];
	}

	public void setSincDivision(String sincDivision) {
		saveArray[ tableFldConstants.division.ordinal() ] = sincDivision;
	}

	public String getSincArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setSincArea(String sincArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = sincArea;
	}

	public String getSincAccidentType() {
		return (String) saveArray[ tableFldConstants.accident_type.ordinal() ];
	}

	public void setSincAccidentType(String sincAccidentType) {
		saveArray[ tableFldConstants.accident_type.ordinal() ] = sincAccidentType;
	}

	public String getSincAccidentDesc() {
		return (String) saveArray[ tableFldConstants.accident_desc.ordinal() ];
	}

	public void setSincAccidentDesc(String sincAccidentDesc) {
		saveArray[ tableFldConstants.accident_desc.ordinal() ] = sincAccidentDesc;
	}

	public String getSincImmediateAction() {
		return (String) saveArray[ tableFldConstants.immediate_action.ordinal() ];
	}

	public void setSincImmediateAction(String sincImmediateAction) {
		saveArray[ tableFldConstants.immediate_action.ordinal() ] = sincImmediateAction;
	}

	public String getSincRecommendations() {
		return (String) saveArray[ tableFldConstants.recommendations.ordinal() ];
	}

	public void setSincRecommendations(String sincRecommendations) {
		saveArray[ tableFldConstants.recommendations.ordinal() ] = sincRecommendations;
	}

	public String getSincNoofemployees() {
		return (String) saveArray[ tableFldConstants.noofemployees.ordinal() ];
	}

	public void setSincNoofemployees(String sincNoofemployees) {
		saveArray[ tableFldConstants.noofemployees.ordinal() ] = sincNoofemployees;
	}

	public String getSincStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setSincStatus(String sincStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = sincStatus;
	}

	public String getSincExactDesc() {
		return (String) saveArray[ tableFldConstants.exact_desc.ordinal() ];
	}

	public void setSincExactDesc(String sincExactDesc) {
		saveArray[ tableFldConstants.exact_desc.ordinal() ] = sincExactDesc;
	}

	public String getSincExactType() {
		return (String) saveArray[ tableFldConstants.exact_type.ordinal() ];
	}

	public void setSincExactType(String sincExactType) {
		saveArray[ tableFldConstants.exact_type.ordinal() ] = sincExactType;
	}

	public String getSincAnalysedBy() {
		return (String) saveArray[ tableFldConstants.analysed_by.ordinal() ];
	}

	public void setSincAnalysedBy(String sincAnalysedBy) {
		saveArray[ tableFldConstants.analysed_by.ordinal() ] = sincAnalysedBy;
	}

	public String getSincAnalysedDate() {
		return (String) saveArray[ tableFldConstants.analysed_date.ordinal() ];
	}

	public void setSincAnalysedDate(String sincAnalysedDate) {
		saveArray[ tableFldConstants.analysed_date.ordinal() ] = sincAnalysedDate;
	}

	public String getSincPhenomena() {
		return (String) saveArray[ tableFldConstants.phenomena.ordinal() ];
	}

	public void setSincPhenomena(String sincPhenomena) {
		saveArray[ tableFldConstants.phenomena.ordinal() ] = sincPhenomena;
	}

	public String getSincWhywhyflag() {
		return (String) saveArray[ tableFldConstants.whywhyflag.ordinal() ];
	}

	public void setSincWhywhyflag(String sincWhywhyflag) {
		saveArray[ tableFldConstants.whywhyflag.ordinal() ] = sincWhywhyflag;
	}

	public String getSincRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setSincRootcause(String sincRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = sincRootcause;
	}

	public String getSincCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setSincCountermeasure(String sincCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = sincCountermeasure;
	}

	public String getSincRootcausedueto() {
		return (String) saveArray[ tableFldConstants.rootcausedueto.ordinal() ];
	}

	public void setSincRootcausedueto(String sincRootcausedueto) {
		saveArray[ tableFldConstants.rootcausedueto.ordinal() ] = sincRootcausedueto;
	}

	public String getSincIdea() {
		return (String) saveArray[ tableFldConstants.idea.ordinal() ];
	}

	public void setSincIdea(String sincIdea) {
		saveArray[ tableFldConstants.idea.ordinal() ] = sincIdea;
	}

	public String getSincAnalysedShift() {
		return (String) saveArray[ tableFldConstants.analysed_shift.ordinal() ];
	}

	public void setSincAnalysedShift(String sincAnalysedShift) {
		saveArray[ tableFldConstants.analysed_shift.ordinal() ] = sincAnalysedShift;
	}

	public String getSincYyno() {
		return (String) saveArray[ tableFldConstants.yyno.ordinal() ];
	}

	public void setSincYyno(String sincYyno) {
		saveArray[ tableFldConstants.yyno.ordinal() ] = sincYyno;
	}

	public String getSincLossproperty() {
		return (String) saveArray[ tableFldConstants.lossproperty.ordinal() ];
	}

	public void setSincLossproperty(String sincLossproperty) {
		saveArray[ tableFldConstants.lossproperty.ordinal() ] = sincLossproperty;
	}

	public String getSincDirectcost() {
		return (String) saveArray[ tableFldConstants.directcost.ordinal() ];
	}

	public void setSincDirectcost(String sincDirectcost) {
		saveArray[ tableFldConstants.directcost.ordinal() ] = sincDirectcost;
	}

	public String getSincTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSincTempfield5(String sincTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = sincTempfield5;
	}

	public String getSincTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setSincTempfield6(String sincTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = sincTempfield6;
	}

	public String getSincTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setSincTempfield7(String sincTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = sincTempfield7;
	}

	public String getSincTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setSincTempfield8(String sincTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = sincTempfield8;
	}

	public String getSincTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setSincTempfield9(String sincTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = sincTempfield9;
	}

	public String getSincTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setSincTempfield10(String sincTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = sincTempfield10;
	}

	public String getSincTempfield11() {
		return (String) saveArray[ tableFldConstants.tempfield11.ordinal() ];
	}

	public void setSincTempfield11(String sincTempfield11) {
		saveArray[ tableFldConstants.tempfield11.ordinal() ] = sincTempfield11;
	}

	public String getSincTempfield12() {
		return (String) saveArray[ tableFldConstants.tempfield12.ordinal() ];
	}

	public void setSincTempfield12(String sincTempfield12) {
		saveArray[ tableFldConstants.tempfield12.ordinal() ] = sincTempfield12;
	}

	public String getSincTempfield13() {
		return (String) saveArray[ tableFldConstants.tempfield13.ordinal() ];
	}

	public void setSincTempfield13(String sincTempfield13) {
		saveArray[ tableFldConstants.tempfield13.ordinal() ] = sincTempfield13;
	}

	public String getSincTempfield14() {
		return (String) saveArray[ tableFldConstants.tempfield14.ordinal() ];
	}

	public void setSincTempfield14(String sincTempfield14) {
		saveArray[ tableFldConstants.tempfield14.ordinal() ] = sincTempfield14;
	}

	public String getSincTempfield15() {
		return (String) saveArray[ tableFldConstants.tempfield15.ordinal() ];
	}

	public void setSincTempfield15(String sincTempfield15) {
		saveArray[ tableFldConstants.tempfield15.ordinal() ] = sincTempfield15;
	}

	public String getSincTempfield16() {
		return (String) saveArray[ tableFldConstants.tempfield16.ordinal() ];
	}

	public void setSincTempfield16(String sincTempfield16) {
		saveArray[ tableFldConstants.tempfield16.ordinal() ] = sincTempfield16;
	}

	public String getSincTempfield17() {
		return (String) saveArray[ tableFldConstants.tempfield17.ordinal() ];
	}

	public void setSincTempfield17(String sincTempfield17) {
		saveArray[ tableFldConstants.tempfield17.ordinal() ] = sincTempfield17;
	}

	public String getSincTempfield18() {
		return (String) saveArray[ tableFldConstants.tempfield18.ordinal() ];
	}

	public void setSincTempfield18(String sincTempfield18) {
		saveArray[ tableFldConstants.tempfield18.ordinal() ] = sincTempfield18;
	}

	public String getSincTempfield19() {
		return (String) saveArray[ tableFldConstants.tempfield19.ordinal() ];
	}

	public void setSincTempfield19(String sincTempfield19) {
		saveArray[ tableFldConstants.tempfield19.ordinal() ] = sincTempfield19;
	}

	public String getSincTempfield20() {
		return (String) saveArray[ tableFldConstants.tempfield20.ordinal() ];
	}

	public void setSincTempfield20(String sincTempfield20) {
		saveArray[ tableFldConstants.tempfield20.ordinal() ] = sincTempfield20;
	}

	public String getSincActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSincActive(String sincActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sincActive;
	}

	public String getSincCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSincCreatedby(String sincCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sincCreatedby;
	}

	public String getSincCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSincCreatedon(String sincCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sincCreatedon;
	}

	public String getSincModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSincModifiedon(String sincModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sincModifiedon;
	}
	public void setAllmoduleimgfile(List<GenTlAllmoduleimgfile> allmoduleimgfile) {
		this.allmoduleimgfile = allmoduleimgfile;
	}

	public List<GenTlAllmoduleimgfile> getAllmoduleimgfile() {
		return allmoduleimgfile;
	}

	public String getSincFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSincFlid(String sincFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = sincFlid;
	}

	public String getSincElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setSincElementid(String sincElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = sincElementid;
	}

}

