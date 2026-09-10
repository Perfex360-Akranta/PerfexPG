package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_PlmTlStandards {
	private List <BAL_PlmTlMethodsmst> methodDetail;
	private List <BAL_PlmTlToolsdtl> toolsDetail;	
	private List<BAL_PlmTlMultipleResp> plmTlMultipleResp;
	private List <BAL_PlmTlPmsftpermitlink> permitlinkDetail;
	private BdmTlYycountermeasurelink countermeasureLink;	
	private List <BAL_PlmTlCbmstdcadtl>  cbmData;
	private  Object [] saveArray = null;  

	public enum   tableFldConstants
	{
		keyid, date, factoryid, sectionid, cellid, machineid, assemblyid
		, subassemblyid, eqpgroupid, source, supplierid, tradeid, frequency
		, frequencyunit, uomid, howmethod, duration, activitytype, activitysubtype
		, bomid, location, activity, standard, machinecondition, planconfigstatus
		, issparesreq, istoolsreq, refdoctype, refdocno, preparedbyid
		, formatno, effectivedate, wogenflag, phenomenaid, causeid, routenumber
		, includeinshutdownmaint, groupno, resultifnotdone, correctiveaction
		, issftpermitreq, safetyinstruction, inactivateddate, monthweekno
		, relatedto, mouldid, locationid,flid, elementid, maxvalue
		, minvalue, target, tempfield8, tempfield9, tempfield10
		, active, createdby, createdon, modifiedon
	}

	public BAL_PlmTlStandards()
	{
		saveArray = new  Object [ 59 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public  void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}
	public String getPmsdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPmsdKeyid(String pmsdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pmsdKeyid;
	}

	public String getPmsdDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPmsdDate(String pmsdDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = pmsdDate;
	}

	public String getPmsdFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setPmsdFactoryid(String pmsdFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = pmsdFactoryid;
	}

	public String getPmsdSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setPmsdSectionid(String pmsdSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = pmsdSectionid;
	}

	public String getPmsdCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setPmsdCellid(String pmsdCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = pmsdCellid;
	}

	public String getPmsdMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setPmsdMachineid(String pmsdMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = pmsdMachineid;
	}

	public String getPmsdAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setPmsdAssemblyid(String pmsdAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = pmsdAssemblyid;
	}

	public String getPmsdSubassemblyid() {
		return (String) saveArray[ tableFldConstants.subassemblyid.ordinal() ];
	}

	public void setPmsdSubassemblyid(String pmsdSubassemblyid) {
		saveArray[ tableFldConstants.subassemblyid.ordinal() ] = pmsdSubassemblyid;
	}

	public String getPmsdEqpgroupid() {
		return (String) saveArray[ tableFldConstants.eqpgroupid.ordinal() ];
	}

	public void setPmsdEqpgroupid(String pmsdEqpgroupid) {
		saveArray[ tableFldConstants.eqpgroupid.ordinal() ] = pmsdEqpgroupid;
	}

	public String getPmsdSource() {
		return (String) saveArray[ tableFldConstants.source.ordinal() ];
	}

	public void setPmsdSource(String pmsdSource) {
		saveArray[ tableFldConstants.source.ordinal() ] = pmsdSource;
	}

	public String getPmsdSupplierid() {
		return (String) saveArray[ tableFldConstants.supplierid.ordinal() ];
	}

	public void setPmsdSupplierid(String pmsdSupplierid) {
		saveArray[ tableFldConstants.supplierid.ordinal() ] = pmsdSupplierid;
	}

	public String getPmsdTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setPmsdTradeid(String pmsdTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = pmsdTradeid;
	}

	public String getPmsdFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setPmsdFrequency(String pmsdFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = pmsdFrequency;
	}

	public String getPmsdFrequencyunit() {
		return (String) saveArray[ tableFldConstants.frequencyunit.ordinal() ];
	}

	public void setPmsdFrequencyunit(String pmsdFrequencyunit) {
		saveArray[ tableFldConstants.frequencyunit.ordinal() ] = pmsdFrequencyunit;
	}

	public String getPmsdUomid() {
		return (String) saveArray[ tableFldConstants.uomid.ordinal() ];
	}

	public void setPmsdUomid(String pmsdUomid) {
		saveArray[ tableFldConstants.uomid.ordinal() ] = pmsdUomid;
	}

	public String getPmsdHowmethod() {
		return (String) saveArray[ tableFldConstants.howmethod.ordinal() ];
	}

	public void setPmsdHowmethod(String pmsdHowmethod) {
		saveArray[ tableFldConstants.howmethod.ordinal() ] = pmsdHowmethod;
	}

	public String getPmsdDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setPmsdDuration(String pmsdDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = pmsdDuration;
	}

	public String getPmsdActivitytype() {
		return (String) saveArray[ tableFldConstants.activitytype.ordinal() ];
	}

	public void setPmsdActivitytype(String pmsdActivitytype) {
		saveArray[ tableFldConstants.activitytype.ordinal() ] = pmsdActivitytype;
	}

	public String getPmsdActivitysubtype() {
		return (String) saveArray[ tableFldConstants.activitysubtype.ordinal() ];
	}

	public void setPmsdActivitysubtype(String pmsdActivitysubtype) {
		saveArray[ tableFldConstants.activitysubtype.ordinal() ] = pmsdActivitysubtype;
	}

	public String getPmsdBomid() {
		return (String) saveArray[ tableFldConstants.bomid.ordinal() ];
	}

	public void setPmsdBomid(String pmsdBomid) {
		saveArray[ tableFldConstants.bomid.ordinal() ] = pmsdBomid;
	}

	public String getPmsdLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setPmsdLocation(String pmsdLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = pmsdLocation;
	}

	public String getPmsdActivity() {
		return (String) saveArray[ tableFldConstants.activity.ordinal() ];
	}

	public void setPmsdActivity(String pmsdActivity) {
		saveArray[ tableFldConstants.activity.ordinal() ] = pmsdActivity;
	}

	public String getPmsdStandard() {
		return (String) saveArray[ tableFldConstants.standard.ordinal() ];
	}

	public void setPmsdStandard(String pmsdStandard) {
		saveArray[ tableFldConstants.standard.ordinal() ] = pmsdStandard;
	}

	public String getPmsdMachinecondition() {
		return (String) saveArray[ tableFldConstants.machinecondition.ordinal() ];
	}

	public void setPmsdMachinecondition(String pmsdMachinecondition) {
		saveArray[ tableFldConstants.machinecondition.ordinal() ] = pmsdMachinecondition;
	}

	public String getPmsdPlanconfigstatus() {
		return (String) saveArray[ tableFldConstants.planconfigstatus.ordinal() ];
	}

	public void setPmsdPlanconfigstatus(String pmsdPlanconfigstatus) {
		saveArray[ tableFldConstants.planconfigstatus.ordinal() ] = pmsdPlanconfigstatus;
	}

	public String getPmsdIssparesreq() {
		return (String) saveArray[ tableFldConstants.issparesreq.ordinal() ];
	}

	public void setPmsdIssparesreq(String pmsdIssparesreq) {
		saveArray[ tableFldConstants.issparesreq.ordinal() ] = pmsdIssparesreq;
	}

	public String getPmsdIstoolsreq() {
		return (String) saveArray[ tableFldConstants.istoolsreq.ordinal() ];
	}

	public void setPmsdIstoolsreq(String pmsdIstoolsreq) {
		saveArray[ tableFldConstants.istoolsreq.ordinal() ] = pmsdIstoolsreq;
	}

	public String getPmsdRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setPmsdRefdoctype(String pmsdRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = pmsdRefdoctype;
	}

	public String getPmsdRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setPmsdRefdocno(String pmsdRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = pmsdRefdocno;
	}

	public String getPmsdPreparedbyid() {
		return (String) saveArray[ tableFldConstants.preparedbyid.ordinal() ];
	}

	public void setPmsdPreparedbyid(String pmsdPreparedbyid) {
		saveArray[ tableFldConstants.preparedbyid.ordinal() ] = pmsdPreparedbyid;
	}

	public String getPmsdFormatno() {
		return (String) saveArray[ tableFldConstants.formatno.ordinal() ];
	}

	public void setPmsdFormatno(String pmsdFormatno) {
		saveArray[ tableFldConstants.formatno.ordinal() ] = pmsdFormatno;
	}

	public String getPmsdEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setPmsdEffectivedate(String pmsdEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = pmsdEffectivedate;
	}

	public String getPmsdWogenflag() {
		return (String) saveArray[ tableFldConstants.wogenflag.ordinal() ];
	}

	public void setPmsdWogenflag(String pmsdWogenflag) {
		saveArray[ tableFldConstants.wogenflag.ordinal() ] = pmsdWogenflag;
	}

	public String getPmsdPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setPmsdPhenomenaid(String pmsdPhenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = pmsdPhenomenaid;
	}

	public String getPmsdCauseid() {
		return (String) saveArray[ tableFldConstants.causeid.ordinal() ];
	}

	public void setPmsdCauseid(String pmsdCauseid) {
		saveArray[ tableFldConstants.causeid.ordinal() ] = pmsdCauseid;
	}

	public String getPmsdRoutenumber() {
		return (String) saveArray[ tableFldConstants.routenumber.ordinal() ];
	}

	public void setPmsdRoutenumber(String pmsdRoutenumber) {
		saveArray[ tableFldConstants.routenumber.ordinal() ] = pmsdRoutenumber;
	}

	public String getPmsdIncludeinshutdownmaint() {
		return (String) saveArray[ tableFldConstants.includeinshutdownmaint.ordinal() ];
	}

	public void setPmsdIncludeinshutdownmaint(String pmsdIncludeinshutdownmaint) {
		saveArray[ tableFldConstants.includeinshutdownmaint.ordinal() ] = pmsdIncludeinshutdownmaint;
	}

	public String getPmsdGroupno() {
		return (String) saveArray[ tableFldConstants.groupno.ordinal() ];
	}

	public void setPmsdGroupno(String pmsdGroupno) {
		saveArray[ tableFldConstants.groupno.ordinal() ] = pmsdGroupno;
	}

	public String getPmsdResultifnotdone() {
		return (String) saveArray[ tableFldConstants.resultifnotdone.ordinal() ];
	}

	public void setPmsdResultifnotdone(String pmsdResultifnotdone) {
		saveArray[ tableFldConstants.resultifnotdone.ordinal() ] = pmsdResultifnotdone;
	}

	public String getPmsdCorrectiveaction() {
		return (String) saveArray[ tableFldConstants.correctiveaction.ordinal() ];
	}

	public void setPmsdCorrectiveaction(String pmsdCorrectiveaction) {
		saveArray[ tableFldConstants.correctiveaction.ordinal() ] = pmsdCorrectiveaction;
	}

	public String getPmsdIssftpermitreq() {
		return (String) saveArray[ tableFldConstants.issftpermitreq.ordinal() ];
	}

	public void setPmsdIssftpermitreq(String pmsdIssftpermitreq) {
		saveArray[ tableFldConstants.issftpermitreq.ordinal() ] = pmsdIssftpermitreq;
	}

	public String getPmsdSafetyinstruction() {
		return (String) saveArray[ tableFldConstants.safetyinstruction.ordinal() ];
	}

	public void setPmsdSafetyinstruction(String pmsdSafetyinstruction) {
		saveArray[ tableFldConstants.safetyinstruction.ordinal() ] = pmsdSafetyinstruction;
	}

	public String getPmsdInactivateddate() {
		return (String) saveArray[ tableFldConstants.inactivateddate.ordinal() ];
	}

	public void setPmsdInactivateddate(String pmsdInactivateddate) {
		saveArray[ tableFldConstants.inactivateddate.ordinal() ] = pmsdInactivateddate;
	}

	public String getPmsdMonthweekno() {
		return (String) saveArray[ tableFldConstants.monthweekno.ordinal() ];
	}

	public void setPmsdMonthweekno(String pmsdMonthweekno) {
		saveArray[ tableFldConstants.monthweekno.ordinal() ] = pmsdMonthweekno;
	}

	public String getPmsdRelatedTo() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setPmsdRelatedTo(String pmsdRelatedTo) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = pmsdRelatedTo;
	}

	public String getPmsdMouldid() {
		return (String) saveArray[ tableFldConstants.mouldid.ordinal() ];
	}

	public void setPmsdMouldid(String pmsdMouldid) {
		saveArray[ tableFldConstants.mouldid.ordinal() ] = pmsdMouldid;
	}

	public String getPmsdLocationid() {
		return (String) saveArray[ tableFldConstants.locationid.ordinal() ];
	}

	public void setPmsdLocationid(String pmsdLocationid) {
		saveArray[ tableFldConstants.locationid.ordinal() ] = pmsdLocationid;
	}
	public String getPmsdFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPmsdFlid(String PmsdFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = PmsdFlid;
	}
	public String getPmsdElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setPmsdElementid(String pmsdElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = pmsdElementid;
	}

	public String getPmsdMaxValue() {
		return (String) saveArray[ tableFldConstants.maxvalue.ordinal() ];
	}

	public void setPmsdMaxValue(String pmsdMaxValue) {
		saveArray[ tableFldConstants.maxvalue.ordinal() ] = pmsdMaxValue;
	}

	public String getPmsdMinValue() {
		return (String) saveArray[ tableFldConstants.minvalue.ordinal() ];
	}

	public void setPmsdMinValue(String pmsdMinValue) {
		saveArray[ tableFldConstants.minvalue.ordinal() ] = pmsdMinValue;
	}

	public String getPmsdTarget() {
		return (String) saveArray[ tableFldConstants.target.ordinal() ];
	}

	public void setPmsdTarget(String pmsdTarget) {
		saveArray[ tableFldConstants.target.ordinal() ] = pmsdTarget;
	}

	public String getPmsdTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setPmsdTempfield8(String pmsdTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = pmsdTempfield8;
	}

	public String getPmsdTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setPmsdTempfield9(String pmsdTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = pmsdTempfield9;
	}

	public String getPmsdTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setPmsdTempfield10(String pmsdTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = pmsdTempfield10;
	}

	public String getPmsdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPmsdActive(String pmsdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pmsdActive;
	}

	public String getPmsdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPmsdCreatedby(String pmsdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pmsdCreatedby;
	}

	public String getPmsdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPmsdCreatedon(String pmsdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pmsdCreatedon;
	}

	public String getPmsdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPmsdModifiedon(String pmsdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pmsdModifiedon;
	}

	public void setPermitlinkDetail(List <BAL_PlmTlPmsftpermitlink> permitlinkDetail) {
		this.permitlinkDetail = permitlinkDetail;
	}

	public List <BAL_PlmTlPmsftpermitlink> getPermitlinkDetail() {
		return permitlinkDetail;
	}
	
	public void setplmTlMultipleResp(List <BAL_PlmTlMultipleResp> multiResp) {
		this.plmTlMultipleResp = multiResp;
	}

	public List <BAL_PlmTlMultipleResp> getplmTlMultipleResp() {
		return plmTlMultipleResp;
	}
	public void setToolsDetail(List <BAL_PlmTlToolsdtl> toolsDetail) {
		this.toolsDetail = toolsDetail;
	}

	public List <BAL_PlmTlToolsdtl> getToolsDetail() {
		return toolsDetail;
	}

	public void setMethodDetail(List <BAL_PlmTlMethodsmst> methodDetail) {
		this.methodDetail = methodDetail;
	}

	public List <BAL_PlmTlMethodsmst> getMethodDetail() {
		return methodDetail;
	}

	public void setCountermeasureLink(BdmTlYycountermeasurelink countermeasureLink) {
		this.countermeasureLink = countermeasureLink;
	}

	public BdmTlYycountermeasurelink getCountermeasureLink() {
		return countermeasureLink;
	}

	public void setCbmData(List <BAL_PlmTlCbmstdcadtl> cbmData) {
		this.cbmData = cbmData;
	}

	public List <BAL_PlmTlCbmstdcadtl> getCbmData() {
		return cbmData;
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

	public static String toJsonManualList(List<BAL_PlmTlStandards> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }
	    sb.append("]");
	    return sb.toString();
	}

	public void setValue(tableFldConstants field, Object val) {
	    saveArray[field.ordinal()] = val;
	}

	public static BAL_PlmTlStandards fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    BAL_PlmTlStandards pmsd = new BAL_PlmTlStandards();
	    CommonMessage.debugMsg("RAW JSON Response: :" + json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = field.name();

	        CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
	        String val = obj.optString(field.name(), null);
	        pmsd.setValue(field, val != null && val.equals("null") ? null : val);
	    }
	    return pmsd;
	}

	public static List<BAL_PlmTlStandards> fromJsonList(String json) {

	    CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<BAL_PlmTlStandards> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        BAL_PlmTlStandards pmsd = new BAL_PlmTlStandards();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	            String val = (valueObj == null || "null".equals(valueObj.toString()))
	                         ? null
	                         : valueObj.toString();

	            pmsd.setValue(field, val);
	        }

	        list.add(pmsd);
	    }

	    return list;
	}

	
}

