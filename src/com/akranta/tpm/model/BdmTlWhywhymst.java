package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class BdmTlWhywhymst {

	private  Object [] saveArray = null;
	private String elementid;
	
	private List<BdmTlWhywhydtl> bdmTlWhywhydtl;
	private BdmTlYyeffectivemst bdmTlYyeffectivemst;
	private List<BdmTlYyeffectivedtl> bdmTlYyeffectivedtl;
	private List<BdmTlYydonebymst> bdmTlYydonebymst;
	private List<BdmTlYyproblemattbymst> bdmTlYyproblemattbymst;

	
	public enum   tableFldConstants
	{
		keyid, date, factoryid, sectionid, lossid, cellid, subcellid
		, machineid, assemblyid, targetpillarid, refdoctype, refdocno
		, phenomenaid, finalaction, sparesreplaced, checksmade, symptombefore
		, youdidnot, countermeasureid, countermeasure, rootcauseid, rootcause
		, isjh, ispm, iskk, isopl, preventivemeasureid, preventivemeasure
		, maintinchargeid, status, ishdpossible, prevdate, preveffectiveness
		, ispy, isojt, ojtdesc, issop, sopdesc, iskzn, ispokayoke, formtype
		, pokayoke, accidentdesc, accidentphen, prevno, prevperson, iseffective
		, flid, area, problem, timespent, problemattendby, whywhydoneby
		, reportdatetime, othercheckpoints,sparesid,pillarid,productid, active, createdby, createdon
		, modifiedon,tradeid,apprRoleid,approvedBy,apprvedOn,appStatus, appRemarks,iscobd,cobdvalue,cobdhours
		
	}

	public BdmTlWhywhymst()
	{
		saveArray = new  Object [ 71 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getWwmsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWwmsKeyid(String wwmsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wwmsKeyid;
	}

	public String getWwmsDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setWwmsDate(String wwmsDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = wwmsDate;
	}

	public String getWwmsFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setWwmsFactoryid(String wwmsFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = wwmsFactoryid;
	}

	public String getWwmsSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setWwmsSectionid(String wwmsSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = wwmsSectionid;
	}

	public String getWwmsLossid() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setWwmsLossid(String wwmsLossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = wwmsLossid;
	}

	public String getWwmsCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setWwmsCellid(String wwmsCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = wwmsCellid;
	}

	public String getWwmsSubcellid() {
		return (String) saveArray[ tableFldConstants.subcellid.ordinal() ];
	}

	public void setWwmsSubcellid(String wwmsSubcellid) {
		saveArray[ tableFldConstants.subcellid.ordinal() ] = wwmsSubcellid;
	}

	public String getWwmsMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setWwmsMachineid(String wwmsMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = wwmsMachineid;
	}

	public String getWwmsAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setWwmsAssemblyid(String wwmsAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = wwmsAssemblyid;
	}

	public String getWwmsTargetpillarid() {
		return (String) saveArray[ tableFldConstants.targetpillarid.ordinal() ];
	}

	public void setWwmsTargetpillarid(String wwmsTargetpillarid) {
		saveArray[ tableFldConstants.targetpillarid.ordinal() ] = wwmsTargetpillarid;
	}

	public String getWwmsRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setWwmsRefdoctype(String wwmsRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = wwmsRefdoctype;
	}

	public String getWwmsRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setWwmsRefdocno(String wwmsRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = wwmsRefdocno;
	}

	public String getWwmsPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setWwmsPhenomenaid(String wwmsPhenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = wwmsPhenomenaid;
	}

	public String getWwmsFinalaction() {
		return (String) saveArray[ tableFldConstants.finalaction.ordinal() ];
	}

	public void setWwmsFinalaction(String wwmsFinalaction) {
		saveArray[ tableFldConstants.finalaction.ordinal() ] = wwmsFinalaction;
	}

	public String getWwmsSparesreplaced() {
		return (String) saveArray[ tableFldConstants.sparesreplaced.ordinal() ];
	}

	public void setWwmsSparesreplaced(String wwmsSparesreplaced) {
		saveArray[ tableFldConstants.sparesreplaced.ordinal() ] = wwmsSparesreplaced;
	}

	public String getWwmsChecksmade() {
		return (String) saveArray[ tableFldConstants.checksmade.ordinal() ];
	}

	public void setWwmsChecksmade(String wwmsChecksmade) {
		saveArray[ tableFldConstants.checksmade.ordinal() ] = wwmsChecksmade;
	}

	public String getWwmsSymptombefore() {
		return (String) saveArray[ tableFldConstants.symptombefore.ordinal() ];
	}

	public void setWwmsSymptombefore(String wwmsSymptombefore) {
		saveArray[ tableFldConstants.symptombefore.ordinal() ] = wwmsSymptombefore;
	}

	public String getWwmsYoudidnot() {
		return (String) saveArray[ tableFldConstants.youdidnot.ordinal() ];
	}

	public void setWwmsYoudidnot(String wwmsYoudidnot) {
		saveArray[ tableFldConstants.youdidnot.ordinal() ] = wwmsYoudidnot;
	}

	public String getWwmsCountermeasureid() {
		return (String) saveArray[ tableFldConstants.countermeasureid.ordinal() ];
	}

	public void setWwmsCountermeasureid(String wwmsCountermeasureid) {
		saveArray[ tableFldConstants.countermeasureid.ordinal() ] = wwmsCountermeasureid;
	}

	public String getWwmsCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setWwmsCountermeasure(String wwmsCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = wwmsCountermeasure;
	}

	public String getWwmsRootcauseid() {
		return (String) saveArray[ tableFldConstants.rootcauseid.ordinal() ];
	}

	public void setWwmsRootcauseid(String wwmsRootcauseid) {
		saveArray[ tableFldConstants.rootcauseid.ordinal() ] = wwmsRootcauseid;
	}

	public String getWwmsRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setWwmsRootcause(String wwmsRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = wwmsRootcause;
	}

	public String getWwmsIsjh() {
		return (String) saveArray[ tableFldConstants.isjh.ordinal() ];
	}

	public void setWwmsIsjh(String wwmsIsjh) {
		saveArray[ tableFldConstants.isjh.ordinal() ] = wwmsIsjh;
	}

	public String getWwmsIspm() {
		return (String) saveArray[ tableFldConstants.ispm.ordinal() ];
	}

	public void setWwmsIspm(String wwmsIspm) {
		saveArray[ tableFldConstants.ispm.ordinal() ] = wwmsIspm;
	}

	public String getWwmsIskk() {
		return (String) saveArray[ tableFldConstants.iskk.ordinal() ];
	}

	public void setWwmsIskk(String wwmsIskk) {
		saveArray[ tableFldConstants.iskk.ordinal() ] = wwmsIskk;
	}

	public String getWwmsIsopl() {
		return (String) saveArray[ tableFldConstants.isopl.ordinal() ];
	}

	public void setWwmsIsopl(String wwmsIsopl) {
		saveArray[ tableFldConstants.isopl.ordinal() ] = wwmsIsopl;
	}

	public String getWwmsPreventivemeasureid() {
		return (String) saveArray[ tableFldConstants.preventivemeasureid.ordinal() ];
	}

	public void setWwmsPreventivemeasureid(String wwmsPreventivemeasureid) {
		saveArray[ tableFldConstants.preventivemeasureid.ordinal() ] = wwmsPreventivemeasureid;
	}

	public String getWwmsPreventivemeasure() {
		return (String) saveArray[ tableFldConstants.preventivemeasure.ordinal() ];
	}

	public void setWwmsPreventivemeasure(String wwmsPreventivemeasure) {
		saveArray[ tableFldConstants.preventivemeasure.ordinal() ] = wwmsPreventivemeasure;
	}

	public String getWwmsMaintinchargeid() {
		return (String) saveArray[ tableFldConstants.maintinchargeid.ordinal() ];
	}

	public void setWwmsMaintinchargeid(String wwmsMaintinchargeid) {
		saveArray[ tableFldConstants.maintinchargeid.ordinal() ] = wwmsMaintinchargeid;
	}

	public String getWwmsStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setWwmsStatus(String wwmsStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = wwmsStatus;
	}

	public String getWwmsIshdpossible() {
		return (String) saveArray[ tableFldConstants.ishdpossible.ordinal() ];
	}

	public void setWwmsIshdpossible(String wwmsIshdpossible) {
		saveArray[ tableFldConstants.ishdpossible.ordinal() ] = wwmsIshdpossible;
	}

	public String getWwmsPrevdate() {
		return (String) saveArray[ tableFldConstants.prevdate.ordinal() ];
	}

	public void setWwmsPrevdate(String wwmsPrevdate) {
		saveArray[ tableFldConstants.prevdate.ordinal() ] = wwmsPrevdate;
	}

	public String getWwmsPreveffectiveness() {
		return (String) saveArray[ tableFldConstants.preveffectiveness.ordinal() ];
	}

	public void setWwmsPreveffectiveness(String wwmsPreveffectiveness) {
		saveArray[ tableFldConstants.preveffectiveness.ordinal() ] = wwmsPreveffectiveness;
	}

	public String getWwmsIspy() {
		return (String) saveArray[ tableFldConstants.ispy.ordinal() ];
	}

	public void setWwmsIspy(String wwmsIspy) {
		saveArray[ tableFldConstants.ispy.ordinal() ] = wwmsIspy;
	}

	public String getWwmsIsojt() {
		return (String) saveArray[ tableFldConstants.isojt.ordinal() ];
	}

	public void setWwmsIsojt(String wwmsIsojt) {
		saveArray[ tableFldConstants.isojt.ordinal() ] = wwmsIsojt;
	}

	public String getWwmsOjtdesc() {
		return (String) saveArray[ tableFldConstants.ojtdesc.ordinal() ];
	}

	public void setWwmsOjtdesc(String wwmsOjtdesc) {
		saveArray[ tableFldConstants.ojtdesc.ordinal() ] = wwmsOjtdesc;
	}

	public String getWwmsIssop() {
		return (String) saveArray[ tableFldConstants.issop.ordinal() ];
	}

	public void setWwmsIssop(String wwmsIssop) {
		saveArray[ tableFldConstants.issop.ordinal() ] = wwmsIssop;
	}

	public String getWwmsSopdesc() {
		return (String) saveArray[ tableFldConstants.sopdesc.ordinal() ];
	}

	public void setWwmsSopdesc(String wwmsSopdesc) {
		saveArray[ tableFldConstants.sopdesc.ordinal() ] = wwmsSopdesc;
	}

	public String getWwmsIskzn() {
		return (String) saveArray[ tableFldConstants.iskzn.ordinal() ];
	}

	public void setWwmsIskzn(String wwmsIskzn) {
		saveArray[ tableFldConstants.iskzn.ordinal() ] = wwmsIskzn;
	}

	public String getWwmsIspokayoke() {
		return (String) saveArray[ tableFldConstants.ispokayoke.ordinal() ];
	}

	public void setWwmsIspokayoke(String wwmsIspokayoke) {
		saveArray[ tableFldConstants.ispokayoke.ordinal() ] = wwmsIspokayoke;
	}

	public String getWwmsFormtype() {
		return (String) saveArray[ tableFldConstants.formtype.ordinal() ];
	}

	public void setWwmsFormtype(String wwmsFormtype) {
		saveArray[ tableFldConstants.formtype.ordinal() ] = wwmsFormtype;
	}

	public String getWwmsPokayoke() {
		return (String) saveArray[ tableFldConstants.pokayoke.ordinal() ];
	}

	public void setWwmsPokayoke(String wwmsPokayoke) {
		saveArray[ tableFldConstants.pokayoke.ordinal() ] = wwmsPokayoke;
	}

	public String getWwmsAccidentdesc() {
		return (String) saveArray[ tableFldConstants.accidentdesc.ordinal() ];
	}

	public void setWwmsAccidentdesc(String wwmsAccidentdesc) {
		saveArray[ tableFldConstants.accidentdesc.ordinal() ] = wwmsAccidentdesc;
	}

	public String getWwmsAccidentphen() {
		return (String) saveArray[ tableFldConstants.accidentphen.ordinal() ];
	}

	public void setWwmsAccidentphen(String wwmsAccidentphen) {
		saveArray[ tableFldConstants.accidentphen.ordinal() ] = wwmsAccidentphen;
	}

	public String getWwmsPrevno() {
		return (String) saveArray[ tableFldConstants.prevno.ordinal() ];
	}

	public void setWwmsPrevno(String wwmsPrevno) {
		saveArray[ tableFldConstants.prevno.ordinal() ] = wwmsPrevno;
	}

	public String getWwmsPrevperson() {
		return (String) saveArray[ tableFldConstants.prevperson.ordinal() ];
	}

	public void setWwmsPrevperson(String wwmsPrevperson) {
		saveArray[ tableFldConstants.prevperson.ordinal() ] = wwmsPrevperson;
	}

	public String getWwmsIseffective() {
		return (String) saveArray[ tableFldConstants.iseffective.ordinal() ];
	}

	public void setWwmsIseffective(String wwmsIseffective) {
		saveArray[ tableFldConstants.iseffective.ordinal() ] = wwmsIseffective;
	}

	public String getWwmsFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setWwmsFlid(String wwmsFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = wwmsFlid;
	}

	public String getWwmsArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setWwmsArea(String wwmsArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = wwmsArea;
	}

	public String getWwmsProblem() {
		return (String) saveArray[ tableFldConstants.problem.ordinal() ];
	}

	public void setWwmsProblem(String wwmsProblem) {
		saveArray[ tableFldConstants.problem.ordinal() ] = wwmsProblem;
	}

	public String getWwmsTimespent() {
		return (String) saveArray[ tableFldConstants.timespent.ordinal() ];
	}

	public void setWwmsTimespent(String wwmsTimespent) {
		saveArray[ tableFldConstants.timespent.ordinal() ] = wwmsTimespent;
	}

	public String getWwmsProblemattendby() {
		return (String) saveArray[ tableFldConstants.problemattendby.ordinal() ];
	}

	public void setWwmsProblemattendby(String wwmsProblemattendby) {
		saveArray[ tableFldConstants.problemattendby.ordinal() ] = wwmsProblemattendby;
	}

	public String getWwmsWhywhydoneby() {
		return (String) saveArray[ tableFldConstants.whywhydoneby.ordinal() ];
	}

	public void setWwmsWhywhydoneby(String wwmsWhywhydoneby) {
		saveArray[ tableFldConstants.whywhydoneby.ordinal() ] = wwmsWhywhydoneby;
	}

	public String getWwmsReportdatetime() {
		return (String) saveArray[ tableFldConstants.reportdatetime.ordinal() ];
	}

	public void setWwmsReportdatetime(String wwmsReportdatetime) {
		saveArray[ tableFldConstants.reportdatetime.ordinal() ] = wwmsReportdatetime;
	}

	public String getWwmsOthercheckpoints() {
		return (String) saveArray[ tableFldConstants.othercheckpoints.ordinal() ];
	}

	public void setWwmsOthercheckpoints(String wwmsOthercheckpoints) {
		saveArray[ tableFldConstants.othercheckpoints.ordinal() ] = wwmsOthercheckpoints;
	}

	public String getWwmsSparesId() {
		return (String) saveArray[ tableFldConstants.sparesid.ordinal() ];
	}

	public void setWwmsSparesId(String wwmsSparesId) {
		saveArray[ tableFldConstants.sparesid.ordinal() ] = wwmsSparesId;
	}
	
	public String getWwmsPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setWwmsPillarid(String wwmsPillarId) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = wwmsPillarId;
	}
	
	public String getWwmsProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setWwmsProductid(String wwmsProductId) {
		saveArray[ tableFldConstants.productid.ordinal() ] = wwmsProductId;
	}

	
	public String getWwmsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWwmsActive(String wwmsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wwmsActive;
	}

	public String getWwmsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWwmsCreatedby(String wwmsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wwmsCreatedby;
	}

	public String getWwmsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWwmsCreatedon(String wwmsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wwmsCreatedon;
	}

	public String getWwmsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWwmsModifiedon(String wwmsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wwmsModifiedon;
	}
	
	public String getWwmsTradeId() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}
	
	public void setWwmsTradeId(String wwmsTradeId){
		saveArray[ tableFldConstants.tradeid.ordinal() ]= wwmsTradeId;
	}
	

	
	public String getWwmsApprRoleid() {
		return (String) saveArray[ tableFldConstants.apprRoleid.ordinal() ];
	}
	
	public void setWwmsApprRoleid(String wwmsApprRoleid){
		saveArray[ tableFldConstants.apprRoleid.ordinal() ]= wwmsApprRoleid;
	}
	
	public String getWwmsApprovedBy() {
		return (String) saveArray[ tableFldConstants.approvedBy.ordinal() ];
	}
	
	public void setWwmsApprovedBy(String wwmsApprovedBy){
		saveArray[ tableFldConstants.approvedBy.ordinal() ]= wwmsApprovedBy;
	}
	
	public String getWwmsApprvedOn() {
		return (String) saveArray[ tableFldConstants.apprvedOn.ordinal() ];
	}
	
	public void setWwmsApprvedOn(String wwmsApprvedOn){
		saveArray[ tableFldConstants.apprvedOn.ordinal() ]= wwmsApprvedOn;
	}
	
	public String getWwmsAppStatus() {
		return (String) saveArray[ tableFldConstants.appStatus.ordinal() ];
	}
	
	public void setWwmsAppStatus(String wwmsAppStatus){
		saveArray[ tableFldConstants.appStatus.ordinal() ]= wwmsAppStatus;
	}
	
	public String getWwmsAppRemarks() {
		return (String) saveArray[ tableFldConstants.appRemarks.ordinal() ];
	}
	
	public void setWwmsAppRemarks(String wwmsAppRemarks){
		saveArray[ tableFldConstants.appRemarks.ordinal() ]= wwmsAppRemarks;
	}
	
	public String getWwmsIscobd() {
		return (String) saveArray[ tableFldConstants.iscobd.ordinal() ];
	}
	
	public void setWwmsIscobd(String iscobd){
		saveArray[ tableFldConstants.iscobd.ordinal() ]= iscobd;
	}
	
	public String getWwmsCobdvalue() {
		return (String) saveArray[ tableFldConstants.cobdvalue.ordinal() ];
	}
	
	public void setWwmsCobdvalue(String cobdvalue){
		saveArray[ tableFldConstants.cobdvalue.ordinal() ]= cobdvalue;
	}
	public String getWwmsCobdhours() {
		return (String) saveArray[ tableFldConstants.cobdhours.ordinal() ];
	}
	
	public void setWwmsCobdhours(String cobdhours){
		saveArray[ tableFldConstants.cobdhours.ordinal() ]= cobdhours;
	}
	
	
	public BdmTlYyeffectivemst getBdmTlYyeffectivemst() {
	    return bdmTlYyeffectivemst;
	}

	public void setBdmTlYyeffectivemst(BdmTlYyeffectivemst bdmTlYyeffectivemst) {
	    this.bdmTlYyeffectivemst = bdmTlYyeffectivemst;
	}

	public List<BdmTlYyeffectivedtl> getBdmTlYyeffectivedtl() {
	    return bdmTlYyeffectivedtl;
	}

	public void setBdmTlYyeffectivedtl(List<BdmTlYyeffectivedtl> bdmTlYyeffectivedtl) {
	    this.bdmTlYyeffectivedtl = bdmTlYyeffectivedtl;
	}

	public List<BdmTlYydonebymst> getBdmTlYydonebymst() {
	    return bdmTlYydonebymst;
	}

	public void setBdmTlYydonebymst(List<BdmTlYydonebymst> bdmTlYydonebymst) {
	    this.bdmTlYydonebymst = bdmTlYydonebymst;
	}

	public List<BdmTlYyproblemattbymst> getBdmTlYyproblemattbymst() {
	    return bdmTlYyproblemattbymst;
	}

	public void setBdmTlYyproblemattbymst(List<BdmTlYyproblemattbymst> bdmTlYyproblemattbymst) {
	    this.bdmTlYyproblemattbymst = bdmTlYyproblemattbymst;
	}

	public String toJsonManual() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    boolean first = true;
	    for (tableFldConstants field : tableFldConstants.values()) {
	        int index = field.ordinal();
	        if (index < saveArray.length) {
	            if (!first) sb.append(",");
	            sb.append("\"").append(field.name()).append("\":");
	            Object val = saveArray[index];
	            if (field.name().equals("keyid") && val == null) {
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

	public static BdmTlWhywhymst fromJson(String json) {
	    JSONObject obj = JSONObject.fromObject(json);
	    BdmTlWhywhymst mst = new BdmTlWhywhymst();
	    for (tableFldConstants field : tableFldConstants.values()) {
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
	        mst.saveArray[field.ordinal()] = val != null && val.equals("null") ? null : val;
	    }
	    return mst;
	}
	
	
	
	
	public void setBdmTlWhywhydtl(List<BdmTlWhywhydtl> bdmTlWhywhydtl) {
		this.bdmTlWhywhydtl = bdmTlWhywhydtl;
	}

	public List<BdmTlWhywhydtl> getBdmTlWhywhydtl() {
		return bdmTlWhywhydtl;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}
	
	//Mano
	@Override
	public String toString() {
	    return "BdmTlWhywhymst{" +
	            "bdmTlWhywhydtl=" + (bdmTlWhywhydtl != null ? bdmTlWhywhydtl.toString() : "null") +
	            '}';
	}

	

}

