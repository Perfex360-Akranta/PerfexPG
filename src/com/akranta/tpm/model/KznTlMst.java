package com.akranta.tpm.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.akranta.tpm.dao.sql.KznTlMstSql;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KznTlMst {

	private Object[] saveArray = null;
	private List<GenTlAllmoduleimgfile> allmoduleimgfile;
	private List<KznTlPillarlink> PillarLink;
	private List<KznTlLosslink> LossLink;
	private List<KznTlGraphdata> graphData;
	private KznTlHdmst kznTlHdmst;

	public enum tableFldConstants {
		keyid, date, factoryid, tpmpillarid, sectionid, cellid, machineid, lossid, resultarea, teammembers, theme,
		themecategoryid, benchmark, target, startdate, enddate, presentproblem, presentimage, wwms_keyid, rootcause,
		idea, countermeasure, afterimage, resultdescription, resultimage, benefits, benefitsimage, isprovidingchanging,
		reversibleirreversible, kaizenlink, kaizenlinktype, assemblyid, phenomenaid, causeid, materialcost, labourcost,
		howtosustain, additionaldetails, additionalimage, ishdpossible, noofhds, preparedid, prepareddate, approvedid,
		approveddate, isworequired, refdoctype, refdocno, status, woid, wofeedbackid, completeddate, completedid,
		remarks, operations, whattosustain, sustainfreq, totalcost, circleid, departmentid, costcentreid, istpmkzn,
		materialno, isworthformp, creationflag, relatedto, mouldid, elementid, flid, utiliseforfuture, resultareasec,
		ideagroupindividual, kzbnkeyid, benefittype, benefitvalue, costperhour, costperequipment, verifyamount,
		aprovLevel, analysis, isWhywhy, fipRequired, fipNumber, kaizenupload, kpiid, activitypillarid, active,
		createdby, createdon, modifiedon, industry4, industrycategory, espNames, csmValue, icoe, pcoe, fip
	}

	public KznTlMst() {
		saveArray = new Object[97];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public Object getValue(tableFldConstants field) {
		return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
		saveArray[field.ordinal()] = value;
	}

	public String getKznmKeyid() {
		return (String) saveArray[tableFldConstants.keyid.ordinal()];
	}

	public void setKznmKeyid(String kznmKeyid) {
		saveArray[tableFldConstants.keyid.ordinal()] = kznmKeyid;
	}

	public String getKznmDate() {
		return (String) saveArray[tableFldConstants.date.ordinal()];
	}

	public void setKznmDate(String kznmDate) {
		saveArray[tableFldConstants.date.ordinal()] = kznmDate;
	}

	public String getKznmFactoryid() {
		return (String) saveArray[tableFldConstants.factoryid.ordinal()];
	}

	public void setKznmFactoryid(String kznmFactoryid) {
		saveArray[tableFldConstants.factoryid.ordinal()] = kznmFactoryid;
	}

	public String getKznmTpmpillarid() {
		return (String) saveArray[tableFldConstants.tpmpillarid.ordinal()];
	}

	public void setKznmTpmpillarid(String kznmTpmpillarid) {
		saveArray[tableFldConstants.tpmpillarid.ordinal()] = kznmTpmpillarid;
	}

	public String getKznmSectionid() {
		return (String) saveArray[tableFldConstants.sectionid.ordinal()];
	}

	public void setKznmSectionid(String kznmSectionid) {
		saveArray[tableFldConstants.sectionid.ordinal()] = kznmSectionid;
	}

	public String getKznmCellid() {
		return (String) saveArray[tableFldConstants.cellid.ordinal()];
	}

	public void setKznmCellid(String kznmCellid) {
		saveArray[tableFldConstants.cellid.ordinal()] = kznmCellid;
	}

	public String getKznmMachineid() {
		return (String) saveArray[tableFldConstants.machineid.ordinal()];
	}

	public void setKznmMachineid(String kznmMachineid) {
		saveArray[tableFldConstants.machineid.ordinal()] = kznmMachineid;
	}

	public String getKznmLossid() {
		return (String) saveArray[tableFldConstants.lossid.ordinal()];
	}

	public void setKznmLossid(String kznmLossid) {
		saveArray[tableFldConstants.lossid.ordinal()] = kznmLossid;
	}

	public String getKznmResultarea() {
		return (String) saveArray[tableFldConstants.resultarea.ordinal()];
	}

	public void setKznmResultarea(String kznmResultarea) {
		saveArray[tableFldConstants.resultarea.ordinal()] = kznmResultarea;
	}

	public String getKznmTeammembers() {
		return (String) saveArray[tableFldConstants.teammembers.ordinal()];
	}

	public void setKznmTeammembers(String kznmTeammembers) {
		saveArray[tableFldConstants.teammembers.ordinal()] = kznmTeammembers;
	}

	public String getKznmTheme() {
		return (String) saveArray[tableFldConstants.theme.ordinal()];
	}

	public void setKznmTheme(String kznmTheme) {
		saveArray[tableFldConstants.theme.ordinal()] = kznmTheme;
	}

	public String getKznmThemecategoryid() {
		return (String) saveArray[tableFldConstants.themecategoryid.ordinal()];
	}

	public void setKznmThemecategoryid(String kznmThemecategoryid) {
		saveArray[tableFldConstants.themecategoryid.ordinal()] = kznmThemecategoryid;
	}

	public String getKznmBenchmark() {
		return (String) saveArray[tableFldConstants.benchmark.ordinal()];
	}

	public void setKznmBenchmark(String kznmBenchmark) {
		saveArray[tableFldConstants.benchmark.ordinal()] = kznmBenchmark;
	}

	public String getKznmTarget() {
		return (String) saveArray[tableFldConstants.target.ordinal()];
	}

	public void setKznmTarget(String kznmTarget) {
		saveArray[tableFldConstants.target.ordinal()] = kznmTarget;
	}

	public String getKznmStartdate() {
		return (String) saveArray[tableFldConstants.startdate.ordinal()];
	}

	public void setKznmStartdate(String kznmStartdate) {
		saveArray[tableFldConstants.startdate.ordinal()] = kznmStartdate;
	}

	public String getKznmEnddate() {
		return (String) saveArray[tableFldConstants.enddate.ordinal()];
	}

	public void setKznmEnddate(String kznmEnddate) {
		saveArray[tableFldConstants.enddate.ordinal()] = kznmEnddate;
	}

	public String getKznmPresentproblem() {
		return (String) saveArray[tableFldConstants.presentproblem.ordinal()];
	}

	public void setKznmPresentproblem(String kznmPresentproblem) {
		saveArray[tableFldConstants.presentproblem.ordinal()] = kznmPresentproblem;
	}

	public String getKznmPresentimage() {
		return (String) saveArray[tableFldConstants.presentimage.ordinal()];
	}

	public void setKznmPresentimage(String kznmPresentimage) {
		saveArray[tableFldConstants.presentimage.ordinal()] = kznmPresentimage;
	}

	public String getKznmWwmsKeyid() {
		return (String) saveArray[tableFldConstants.wwms_keyid.ordinal()];
	}

	public void setKznmWwmsKeyid(String kznmWwmsKeyid) {
		saveArray[tableFldConstants.wwms_keyid.ordinal()] = kznmWwmsKeyid;
	}

	public String getKznmRootcause() {
		return (String) saveArray[tableFldConstants.rootcause.ordinal()];
	}

	public void setKznmRootcause(String kznmRootcause) {
		saveArray[tableFldConstants.rootcause.ordinal()] = kznmRootcause;
	}

	public String getKznmIdea() {
		return (String) saveArray[tableFldConstants.idea.ordinal()];
	}

	public void setKznmIdea(String kznmIdea) {
		saveArray[tableFldConstants.idea.ordinal()] = kznmIdea;
	}

	public String getKznmCountermeasure() {
		return (String) saveArray[tableFldConstants.countermeasure.ordinal()];
	}

	public void setKznmCountermeasure(String kznmCountermeasure) {
		saveArray[tableFldConstants.countermeasure.ordinal()] = kznmCountermeasure;
	}

	public String getKznmAfterimage() {
		return (String) saveArray[tableFldConstants.afterimage.ordinal()];
	}

	public void setKznmAfterimage(String kznmAfterimage) {
		saveArray[tableFldConstants.afterimage.ordinal()] = kznmAfterimage;
	}

	public String getKznmResultdescription() {
		return (String) saveArray[tableFldConstants.resultdescription.ordinal()];
	}

	public void setKznmResultdescription(String kznmResultdescription) {
		saveArray[tableFldConstants.resultdescription.ordinal()] = kznmResultdescription;
	}

	public String getKznmResultimage() {
		return (String) saveArray[tableFldConstants.resultimage.ordinal()];
	}

	public void setKznmResultimage(String kznmResultimage) {
		saveArray[tableFldConstants.resultimage.ordinal()] = kznmResultimage;
	}

	public String getKznmBenefits() {
		return (String) saveArray[tableFldConstants.benefits.ordinal()];
	}

	public void setKznmBenefits(String kznmBenefits) {
		saveArray[tableFldConstants.benefits.ordinal()] = kznmBenefits;
	}

	public String getKznmBenefitsimage() {
		return (String) saveArray[tableFldConstants.benefitsimage.ordinal()];
	}

	public void setKznmBenefitsimage(String kznmBenefitsimage) {
		saveArray[tableFldConstants.benefitsimage.ordinal()] = kznmBenefitsimage;
	}

	public String getKznmIsprovidingchanging() {
		return (String) saveArray[tableFldConstants.isprovidingchanging.ordinal()];
	}

	public void setKznmIsprovidingchanging(String kznmIsprovidingchanging) {
		saveArray[tableFldConstants.isprovidingchanging.ordinal()] = kznmIsprovidingchanging;
	}

	public String getKznmReversibleirreversible() {
		return (String) saveArray[tableFldConstants.reversibleirreversible.ordinal()];
	}

	public void setKznmReversibleirreversible(String kznmReversibleirreversible) {
		saveArray[tableFldConstants.reversibleirreversible.ordinal()] = kznmReversibleirreversible;
	}

	public String getKznmKaizenlink() {
		return (String) saveArray[tableFldConstants.kaizenlink.ordinal()];
	}

	public void setKznmKaizenlink(String kznmKaizenlink) {
		saveArray[tableFldConstants.kaizenlink.ordinal()] = kznmKaizenlink;
	}

	public String getKznmKaizenlinktype() {
		return (String) saveArray[tableFldConstants.kaizenlinktype.ordinal()];
	}

	public void setKznmKaizenlinktype(String kznmKaizenlinktype) {
		saveArray[tableFldConstants.kaizenlinktype.ordinal()] = kznmKaizenlinktype;
	}

	public String getKznmAssemblyid() {
		return (String) saveArray[tableFldConstants.assemblyid.ordinal()];
	}

	public void setKznmAssemblyid(String kznmAssemblyid) {
		saveArray[tableFldConstants.assemblyid.ordinal()] = kznmAssemblyid;
	}

	public String getKznmPhenomenaid() {
		return (String) saveArray[tableFldConstants.phenomenaid.ordinal()];
	}

	public void setKznmPhenomenaid(String kznmPhenomenaid) {
		saveArray[tableFldConstants.phenomenaid.ordinal()] = kznmPhenomenaid;
	}

	public String getKznmCauseid() {
		return (String) saveArray[tableFldConstants.causeid.ordinal()];
	}

	public void setKznmCauseid(String kznmCauseid) {
		saveArray[tableFldConstants.causeid.ordinal()] = kznmCauseid;
	}

	public String getKznmMaterialcost() {
		return (String) saveArray[tableFldConstants.materialcost.ordinal()];
	}

	public void setKznmMaterialcost(String kznmMaterialcost) {
		saveArray[tableFldConstants.materialcost.ordinal()] = kznmMaterialcost;
	}

	public String getKznmLabourcost() {
		return (String) saveArray[tableFldConstants.labourcost.ordinal()];
	}

	public void setKznmLabourcost(String kznmLabourcost) {
		saveArray[tableFldConstants.labourcost.ordinal()] = kznmLabourcost;
	}

	public String getKznmHowtosustain() {
		return (String) saveArray[tableFldConstants.howtosustain.ordinal()];
	}

	public void setKznmHowtosustain(String kznmHowtosustain) {
		saveArray[tableFldConstants.howtosustain.ordinal()] = kznmHowtosustain;
	}

	public String getKznmAdditionaldetails() {
		return (String) saveArray[tableFldConstants.additionaldetails.ordinal()];
	}

	public void setKznmAdditionaldetails(String kznmAdditionaldetails) {
		saveArray[tableFldConstants.additionaldetails.ordinal()] = kznmAdditionaldetails;
	}

	public String getKznmAdditionalimage() {
		return (String) saveArray[tableFldConstants.additionalimage.ordinal()];
	}

	public void setKznmAdditionalimage(String kznmAdditionalimage) {
		saveArray[tableFldConstants.additionalimage.ordinal()] = kznmAdditionalimage;
	}

	public String getKznmIshdpossible() {
		return (String) saveArray[tableFldConstants.ishdpossible.ordinal()];
	}

	public void setKznmIshdpossible(String kznmIshdpossible) {
		saveArray[tableFldConstants.ishdpossible.ordinal()] = kznmIshdpossible;
	}

	public String getKznmNoofhds() {
		return (String) saveArray[tableFldConstants.noofhds.ordinal()];
	}

	public void setKznmNoofhds(String kznmNoofhds) {
		saveArray[tableFldConstants.noofhds.ordinal()] = kznmNoofhds;
	}

	public String getKznmPreparedid() {
		return (String) saveArray[tableFldConstants.preparedid.ordinal()];
	}

	public void setKznmPreparedid(String kznmPreparedid) {
		saveArray[tableFldConstants.preparedid.ordinal()] = kznmPreparedid;
	}

	public String getKznmPrepareddate() {
		return (String) saveArray[tableFldConstants.prepareddate.ordinal()];
	}

	public void setKznmPrepareddate(String kznmPrepareddate) {
		saveArray[tableFldConstants.prepareddate.ordinal()] = kznmPrepareddate;
	}

	public String getKznmApprovedid() {
		return (String) saveArray[tableFldConstants.approvedid.ordinal()];
	}

	public void setKznmApprovedid(String kznmApprovedid) {
		saveArray[tableFldConstants.approvedid.ordinal()] = kznmApprovedid;
	}

	public String getKznmApproveddate() {
		return (String) saveArray[tableFldConstants.approveddate.ordinal()];
	}

	public void setKznmApproveddate(String kznmApproveddate) {
		saveArray[tableFldConstants.approveddate.ordinal()] = kznmApproveddate;
	}

	public String getKznmIsworequired() {
		return (String) saveArray[tableFldConstants.isworequired.ordinal()];
	}

	public void setKznmIsworequired(String kznmIsworequired) {
		saveArray[tableFldConstants.isworequired.ordinal()] = kznmIsworequired;
	}

	public String getKznmRefdoctype() {
		return (String) saveArray[tableFldConstants.refdoctype.ordinal()];
	}

	public void setKznmRefdoctype(String kznmRefdoctype) {
		saveArray[tableFldConstants.refdoctype.ordinal()] = kznmRefdoctype;
	}

	public String getKznmRefdocno() {
		return (String) saveArray[tableFldConstants.refdocno.ordinal()];
	}

	public void setKznmRefdocno(String kznmRefdocno) {
		saveArray[tableFldConstants.refdocno.ordinal()] = kznmRefdocno;
	}

	public String getKznmStatus() {
		return (String) saveArray[tableFldConstants.status.ordinal()];
	}

	public void setKznmStatus(String kznmStatus) {
		saveArray[tableFldConstants.status.ordinal()] = kznmStatus;
	}

	public String getKznmWoid() {
		return (String) saveArray[tableFldConstants.woid.ordinal()];
	}

	public void setKznmWoid(String kznmWoid) {
		saveArray[tableFldConstants.woid.ordinal()] = kznmWoid;
	}

	public String getKznmWofeedbackid() {
		return (String) saveArray[tableFldConstants.wofeedbackid.ordinal()];
	}

	public void setKznmWofeedbackid(String kznmWofeedbackid) {
		saveArray[tableFldConstants.wofeedbackid.ordinal()] = kznmWofeedbackid;
	}

	public String getKznmCompleteddate() {
		return (String) saveArray[tableFldConstants.completeddate.ordinal()];
	}

	public void setKznmCompleteddate(String kznmCompleteddate) {
		saveArray[tableFldConstants.completeddate.ordinal()] = kznmCompleteddate;
	}

	public String getKznmCompletedid() {
		return (String) saveArray[tableFldConstants.completedid.ordinal()];
	}

	public void setKznmCompletedid(String kznmCompletedid) {
		saveArray[tableFldConstants.completedid.ordinal()] = kznmCompletedid;
	}

	public String getKznmRemarks() {
		return (String) saveArray[tableFldConstants.remarks.ordinal()];
	}

	public void setKznmRemarks(String kznmRemarks) {
		saveArray[tableFldConstants.remarks.ordinal()] = kznmRemarks;
	}

	public String getKznmOperations() {
		return (String) saveArray[tableFldConstants.operations.ordinal()];
	}

	public void setKznmOperations(String kznmOperations) {
		saveArray[tableFldConstants.operations.ordinal()] = kznmOperations;
	}

	public String getKznmWhattosustain() {
		return (String) saveArray[tableFldConstants.whattosustain.ordinal()];
	}

	public void setKznmWhattosustain(String kznmWhattosustain) {
		saveArray[tableFldConstants.whattosustain.ordinal()] = kznmWhattosustain;
	}

	public String getKznmSustainfreq() {
		return (String) saveArray[tableFldConstants.sustainfreq.ordinal()];
	}

	public void setKznmSustainfreq(String kznmSustainfreq) {
		saveArray[tableFldConstants.sustainfreq.ordinal()] = kznmSustainfreq;
	}

	public String getKznmTotalcost() {
		return (String) saveArray[tableFldConstants.totalcost.ordinal()];
	}

	public void setKznmTotalcost(String kznmTotalcost) {
		saveArray[tableFldConstants.totalcost.ordinal()] = kznmTotalcost;
	}

	public String getKznmCircleid() {
		return (String) saveArray[tableFldConstants.circleid.ordinal()];
	}

	public void setKznmCircleid(String kznmCircleid) {
		saveArray[tableFldConstants.circleid.ordinal()] = kznmCircleid;
	}

	public String getKznmDepartmentid() {
		return (String) saveArray[tableFldConstants.departmentid.ordinal()];
	}

	public void setKznmDepartmentid(String kznmDepartmentid) {
		saveArray[tableFldConstants.departmentid.ordinal()] = kznmDepartmentid;
	}

	public String getKznmCostcentreid() {
		return (String) saveArray[tableFldConstants.costcentreid.ordinal()];
	}

	public void setKznmCostcentreid(String kznmCostcentreid) {
		saveArray[tableFldConstants.costcentreid.ordinal()] = kznmCostcentreid;
	}

	public String getKznmIstpmkzn() {
		return (String) saveArray[tableFldConstants.istpmkzn.ordinal()];
	}

	public void setKznmIstpmkzn(String kznmIstpmkzn) {
		saveArray[tableFldConstants.istpmkzn.ordinal()] = kznmIstpmkzn;
	}

	public String getKznmMaterialno() {
		return (String) saveArray[tableFldConstants.materialno.ordinal()];
	}

	public void setKznmMaterialno(String kznmMaterialno) {
		saveArray[tableFldConstants.materialno.ordinal()] = kznmMaterialno;
	}

	public String getKznmIsworthformp() {
		return (String) saveArray[tableFldConstants.isworthformp.ordinal()];
	}

	public void setKznmIsworthformp(String kznmIsworthformp) {
		saveArray[tableFldConstants.isworthformp.ordinal()] = kznmIsworthformp;
	}

	public String getKznmCreationflag() {
		return (String) saveArray[tableFldConstants.creationflag.ordinal()];
	}

	public void setKznmCreationflag(String kznmCreationflag) {
		saveArray[tableFldConstants.creationflag.ordinal()] = kznmCreationflag;
	}

	public String getKznmRelatedto() {
		return (String) saveArray[tableFldConstants.relatedto.ordinal()];
	}

	public void setKznmRelatedto(String kznmRelatedto) {
		saveArray[tableFldConstants.relatedto.ordinal()] = kznmRelatedto;
	}

	public String getKznmMouldid() {
		return (String) saveArray[tableFldConstants.mouldid.ordinal()];
	}

	public void setKznmMouldid(String kznmMouldid) {
		saveArray[tableFldConstants.mouldid.ordinal()] = kznmMouldid;
	}

	public String getKznmResultareasec() {
		return (String) saveArray[tableFldConstants.resultareasec.ordinal()];
	}

	public void setKznmResultareasec(String kznmResultareasec) {
		saveArray[tableFldConstants.resultareasec.ordinal()] = kznmResultareasec;
	}

	public String getKznmUtiliseforfuture() {
		return (String) saveArray[tableFldConstants.utiliseforfuture.ordinal()];
	}

	public void setKznmUtiliseforfuture(String kznmUtiliseforfuture) {
		saveArray[tableFldConstants.utiliseforfuture.ordinal()] = kznmUtiliseforfuture;
	}

	public String getKznmKzbnkeyid() {
		return (String) saveArray[tableFldConstants.kzbnkeyid.ordinal()];
	}

	public void setKznmKzbnkeyid(String kznmKzbnkeyid) {
		saveArray[tableFldConstants.kzbnkeyid.ordinal()] = kznmKzbnkeyid;
	}

	public String getKznmElementid() {
		return (String) saveArray[tableFldConstants.elementid.ordinal()];
	}

	public void setKznmElementid(String kznmElementid) {
		saveArray[tableFldConstants.elementid.ordinal()] = kznmElementid;
	}

	public String getKznmFlid() {
		return (String) saveArray[tableFldConstants.flid.ordinal()];
	}

	public void setKznmFlid(String kznmFlid) {
		saveArray[tableFldConstants.flid.ordinal()] = kznmFlid;
	}

	public String getKznmIdeagroupindividual() {
		return (String) saveArray[tableFldConstants.ideagroupindividual.ordinal()];
	}

	public void setKznmIdeagroupindividual(String kznmIdeagroupindividual) {
		saveArray[tableFldConstants.ideagroupindividual.ordinal()] = kznmIdeagroupindividual;
	}

	public String getKznmBenefittype() {
		return (String) saveArray[tableFldConstants.benefittype.ordinal()];
	}

	public void setKznmBenefittype(String kznmBenefittype) {
		saveArray[tableFldConstants.benefittype.ordinal()] = kznmBenefittype;
	}

	public String getKznmBenefitvalue() {
		return (String) saveArray[tableFldConstants.benefitvalue.ordinal()];
	}

	public void setKznmBenefitvalue(String kznmBenefitvalue) {
		saveArray[tableFldConstants.benefitvalue.ordinal()] = kznmBenefitvalue;
	}

	public String getKznmCostperhour() {
		return (String) saveArray[tableFldConstants.costperhour.ordinal()];
	}

	public void setKznmCostperhour(String kznmCostperhour) {
		saveArray[tableFldConstants.costperhour.ordinal()] = kznmCostperhour;
	}

	public String getKznmCostperequipment() {
		return (String) saveArray[tableFldConstants.costperequipment.ordinal()];
	}

	public void setKznmCostperequipment(String kznmCostperequipment) {
		saveArray[tableFldConstants.costperequipment.ordinal()] = kznmCostperequipment;
	}

	public String getKznmVerifyamount() {
		return (String) saveArray[tableFldConstants.verifyamount.ordinal()];
	}

	public void setKznmVerifyamount(String kznmVerifyamount) {
		saveArray[tableFldConstants.verifyamount.ordinal()] = kznmVerifyamount;
	}

	public String getKznmApprovLevel() {
		return (String) saveArray[tableFldConstants.aprovLevel.ordinal()];
	}

	public void setKznmApprovLevel(String kznmApprovLevel) {
		saveArray[tableFldConstants.aprovLevel.ordinal()] = kznmApprovLevel;
	}

	public String getKznmAnalysis() {
		return (String) saveArray[tableFldConstants.analysis.ordinal()];
	}

	public void setKznmAnalysis(String kznmAnalysis) {
		saveArray[tableFldConstants.analysis.ordinal()] = kznmAnalysis;
	}

	public String getKznmIswhywhy() {
		return (String) saveArray[tableFldConstants.isWhywhy.ordinal()];
	}

	public void setKznmIswhywhy(String kznmIswhywhy) {
		saveArray[tableFldConstants.isWhywhy.ordinal()] = kznmIswhywhy;
	}

	public String getKznmFipRequired() {
		return (String) saveArray[tableFldConstants.fipRequired.ordinal()];
	}

	public void setKznmFipRequired(String kznmfipRequired) {
		saveArray[tableFldConstants.fipRequired.ordinal()] = kznmfipRequired;
	}

	public String getKznmFipNumber() {
		return (String) saveArray[tableFldConstants.fipNumber.ordinal()];
	}

	public void setKznmFipNumber(String kznmFipNumber) {
		saveArray[tableFldConstants.fipNumber.ordinal()] = kznmFipNumber;
	}

	public String getKznmKaizenUpload() {
		return (String) saveArray[tableFldConstants.kaizenupload.ordinal()];
	}

	public void setKznmKaizenUpload(String kznmKaizenUpload) {
		saveArray[tableFldConstants.kaizenupload.ordinal()] = kznmKaizenUpload;
	}

	public String getKznmKpiid() {
		return (String) saveArray[tableFldConstants.kpiid.ordinal()];
	}

	public void setKznmKpiid(String kznmKpiid) {
		saveArray[tableFldConstants.kpiid.ordinal()] = kznmKpiid;
	}

	public String getKznmActivitypillarid() {
		return (String) saveArray[tableFldConstants.activitypillarid.ordinal()];
	}

	public void setKznmActivitypillarid(String kznmActivitypillarid) {
		saveArray[tableFldConstants.activitypillarid.ordinal()] = kznmActivitypillarid;
	}

	public String getKznmActive() {
		return (String) saveArray[tableFldConstants.active.ordinal()];
	}

	public void setKznmActive(String kznmActive) {
		saveArray[tableFldConstants.active.ordinal()] = kznmActive;
	}

	public String getKznmCreatedby() {
		return (String) saveArray[tableFldConstants.createdby.ordinal()];
	}

	public void setKznmCreatedby(String kznmCreatedby) {
		saveArray[tableFldConstants.createdby.ordinal()] = kznmCreatedby;
	}

	public String getKznmCreatedon() {
		return (String) saveArray[tableFldConstants.createdon.ordinal()];
	}

	public void setKznmCreatedon(String kznmCreatedon) {
		saveArray[tableFldConstants.createdon.ordinal()] = kznmCreatedon;
	}

	public String getKznmModifiedon() {
		return (String) saveArray[tableFldConstants.modifiedon.ordinal()];
	}

	public void setKznmModifiedon(String kznmModifiedon) {
		saveArray[tableFldConstants.modifiedon.ordinal()] = kznmModifiedon;
	}

	public String getKznmIndustryReq() {
		return (String) saveArray[tableFldConstants.industry4.ordinal()];
	}

	public void setKznmIndustryReq(String kznmIndustryReq) {
		saveArray[tableFldConstants.industry4.ordinal()] = kznmIndustryReq;
	}

	public String getKznmIndustry() {
		return (String) saveArray[tableFldConstants.industrycategory.ordinal()];
	}

	public void setKznmIndustry(String kznmIndustry) {
		saveArray[tableFldConstants.industrycategory.ordinal()] = kznmIndustry;
	}

	////////////////////
	public String getKznmEspNames() {
		return (String) saveArray[tableFldConstants.espNames.ordinal()];
	}

	public void setKznmEspNames(String kznmEspNames) {
		saveArray[tableFldConstants.espNames.ordinal()] = kznmEspNames;
	}

	public String getKznmCsmValue() {
		return (String) saveArray[tableFldConstants.csmValue.ordinal()];
	}

	public void setKznmCsmValue(String kznmCsmValue) {
		saveArray[tableFldConstants.csmValue.ordinal()] = kznmCsmValue;
	}

	public String getKznmIcoe() {
		return (String) saveArray[tableFldConstants.icoe.ordinal()];
	}

	public void setKznmIcoe(String kznmIcoe) {
		saveArray[tableFldConstants.icoe.ordinal()] = kznmIcoe;
	}

	public String getKznmPcoe() {
		return (String) saveArray[tableFldConstants.pcoe.ordinal()];
	}

	public void setKznmPcoe(String kznmPcoe) {
		saveArray[tableFldConstants.pcoe.ordinal()] = kznmPcoe;
	}

	public String getKznmFip() {
		return (String) saveArray[tableFldConstants.fip.ordinal()];
	}

	public void setKznmFip(String kznmFip) {
		saveArray[tableFldConstants.fip.ordinal()] = kznmFip;
	}

	///////////////////
	/**
	 * @param allmoduleimgfile the allmoduleimgfile to set
	 */
	public void setAllmoduleimgfile(List<GenTlAllmoduleimgfile> allmoduleimgfile) {
		this.allmoduleimgfile = allmoduleimgfile;
	}

	/**
	 * @return the allmoduleimgfile
	 */
	public List<GenTlAllmoduleimgfile> getAllmoduleimgfile() {
		return allmoduleimgfile;
	}

	/**
	 * @param pillarLink the pillarLink to set
	 */
	public void setPillarLink(List<KznTlPillarlink> pillarLink) {
		PillarLink = pillarLink;
	}

	/**
	 * @return the pillarLink
	 */
	public List<KznTlPillarlink> getPillarLink() {
		return PillarLink;
	}

	public void setKznTlHdmst(KznTlHdmst kznTlHdmst) {
		this.kznTlHdmst = kznTlHdmst;
	}

	public KznTlHdmst getKznTlHdmst() {
		return kznTlHdmst;
	}

	public void setLossLink(List<KznTlLosslink> lossLink) {
		LossLink = lossLink;
	}

	public List<KznTlLosslink> getLossLink() {
		return LossLink;
	}

	public void setGraphData(List<KznTlGraphdata> graphData) {
		this.graphData = graphData;
	}

	public List<KznTlGraphdata> getGraphData() {
		return graphData;
	}
/*
	public String toJsonManual() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		boolean first = true;
		for (tableFldConstants field : tableFldConstants.values()) {
			int index = field.ordinal();
			if (index < saveArray.length) {
				if (!first)
					sb.append(",");
				sb.append("\"").append(field.name()).append("\":");
				Object val = saveArray[index];
				if (field.name() == "keyid" && val == null) {
					sb.append("null");
				} else if (val == null) {
					sb.append("\"{}\"");
				} else {
					sb.append("\"").append(val.toString()).append("\"");
				}
				first = false;
			}
		}

		sb.append("}");
		return sb.toString();
	}
*/
	
	  public static KznTlMst fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");
	  
	  JSONObject obj = JSONObject.fromObject(json);
	  
	  KznTlMst mst = new KznTlMst();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);
	  
	  for (tableFldConstants field : tableFldConstants.values()) { 
		  String key =  field.name();
	  
	  
	  CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key)); 
	 
	  String val =  obj.optString(field.name(), null);

	  if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val) || "-".equals(val)) {
          val = "";
       }
	  mst.setValue(field, val != null &&  val.equals("null") ? null : val);
	  
	  if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val) || "-".equals(val)) {
          val = "";
       }
	  }
	  return mst; }
	  
	 
	/*
	 * public static KznTlMst fromJson(String json) {
	 * 
	 * CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");
	 * 
	 * // 1️⃣ Basic validation if (json == null || json.trim().isEmpty()) { throw
	 * new IllegalArgumentException("Empty JSON response received"); }
	 * 
	 * json = json.trim();
	 * 
	 * // 2️⃣ Handle API error cases like [] if (json.startsWith("[")) { throw new
	 * IllegalStateException( "Expected JSONObject but received JSONArray: " + json
	 * ); }
	 * 
	 * // 3️⃣ Must start with '{' if (!json.startsWith("{")) { throw new
	 * IllegalStateException( "Invalid JSON (not an object): " + json ); }
	 * 
	 * JSONObject obj; try { obj = JSONObject.fromObject(json); } catch (Exception
	 * e) { throw new RuntimeException( "Failed to parse JSON safely: " + json, e );
	 * }
	 * 
	 * KznTlMst mst = new KznTlMst();
	 * 
	 * // 4️⃣ Enum-driven safe mapping for (tableFldConstants field :
	 * tableFldConstants.values()) {
	 * 
	 * String key = field.name(); String rawVal = obj.optString(key, null); String
	 * cleanVal = normalize(rawVal);
	 * 
	 * CommonMessage.debugMsg( "JSON[" + key + "] raw=" + rawVal + " | clean=" +
	 * cleanVal );
	 * 
	 * mst.setValue(field, cleanVal); }
	 * 
	 * return mst; } private static String normalize(String val) {
	 * 
	 * if (val == null) return null;
	 * 
	 * val = val.trim();
	 * 
	 * // junk placeholders coming from UI / legacy code if (val.isEmpty() ||
	 * "null".equalsIgnoreCase(val) || "{}".equals(val) || "<**>".equals(val) ||
	 * "-".equals(val)) { return null; }
	 * 
	 * // default fake dates if ("01-Jan-1801".equals(val)) { return null; }
	 * 
	 * return val; }
	 */

	  public String toJsonManual() {
		    StringBuilder sb = new StringBuilder();
		  
		    sb.append("{");
		    
		    // Define the date formatter for ISO format
		    DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		    
		    boolean first = true;
		    for (tableFldConstants field : tableFldConstants.values()) {
		        int index = field.ordinal();
		        if (index < saveArray.length) {
		            if (!first)
		                sb.append(",");
		            sb.append("\"").append(field.name()).append("\":");
		            Object val = saveArray[index];
		            
		            if (field.name(). equals("keyid")  && val == null) {
		                sb.append("null");
		            } else if (val == null) {
		                sb.append("\"{}\"");
		            } else {
		                // Check if the value is a date/time type and format it
		                if (val instanceof LocalDateTime) {
		                    sb.append("\"").append(((LocalDateTime) val).format(isoFormatter)).append("\"");
		                } else if (val instanceof java.util.Date) {
		                    LocalDateTime ldt = LocalDateTime.ofInstant(
		                        ((java.util.Date) val).toInstant(), 
		                        java.time.ZoneId.systemDefault()
		                    );
		                    sb.append("\"").append(ldt.format(isoFormatter)).append("\"");
		                } else {
		                    sb.append("\"").append(val.toString()).append("\"");
		                }
		            }
		            first = false;
		        }
		    }
		    
		    sb.append("}");
		    return sb.toString();
		}
}
