package com.akranta.tpm.model;

import java.util.List;
//import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

//import org.json.JSONObject;

public class AbnTlAbnormality {

	private  Object [] saveArray = null; 
	private AbnTlDtl abnTlDtl;
	private AbnTlAbnhistorydtl abnTlAbnhistorydtl;
	private List<GenTlTeamDoucmentLink> TeamList;
    private List<AbnTlDtl> abnTldtl;
	public enum   tableFldConstants
	{
		keyid, 
		date, 
		refdoctype, 
		refdocid, 
		detectiondate, 
		detectedby, 
		equipmentid, 
		sectionid, 
		cellid, 
		assemblyid, 
		shiftid, 
		tradeid, 
		woreceiveddate, 
		responsetime, 
		worktime, 
		wostarttime, 
		woendtime, 
		downtime, 
		description, 
		typeid, 
		whyabnhappened, 
		whatcause, 
		tagclassid, 
		categoryid, 
		impactid, 
		countermeasure, 
		preventivemeasure, 
		status, 
		targetdate, 
		targetremarks, 
		completedby, 
		womasterid, 
		wodetailid, 
		feedbackid, 
		feedbackdate, 
		remarks, 
		blockdiagramref, 
		revisionno, 
		priority, 
		detailedesc, 
		subtype, 
		contaminant, 
		mode, 
		factoryid, 
		pillar, 
		safetypatrol, 
		relatedto, 
		mould, 
		flid, 
		elementid, 
		pillarid, 
		repeatedabn, 
		afeemid, 
		effectivedate, 
		notifysap, 
		shutdownmaint, 
		tentativedate, 
		shutdownid,
		accecpatncerequired,
		accecptdate,
		accecpted,
		others,
		repotheres,
		responsibleid,
		multipleabn,
		tempfield4,
		tempfield5,
		tempfield6,
		tempfield7,
		tempfield8,
		tempfield9,
		tempfield10,		
		active, 
		createdby, 
		createdon, 
		modifiedon
	}

	public AbnTlAbnormality()
	{
		saveArray = new  Object [ 76 ];
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

	public String getAbnmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAbnmKeyid(String abnmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = abnmKeyid;
	}

	public String getAbnmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setAbnmDate(String abnmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = abnmDate;
	}

	public String getAbnmRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setAbnmRefdoctype(String abnmRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = abnmRefdoctype;
	}

	public String getAbnmRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setAbnmRefdocid(String abnmRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = abnmRefdocid;
	}

	public String getAbnmDetectiondate() {
		return (String) saveArray[ tableFldConstants.detectiondate.ordinal() ];
	}

	public void setAbnmDetectiondate(String abnmDetectiondate) {
		saveArray[ tableFldConstants.detectiondate.ordinal() ] = abnmDetectiondate;
	}

	public String getAbnmDetectedby() {
		return (String) saveArray[ tableFldConstants.detectedby.ordinal() ];
	}
	
	public void setAbnmDetectedby(String abnmDetectedby) {
		saveArray[ tableFldConstants.detectedby.ordinal() ] = abnmDetectedby;
	}

	public String getAbnmEquipmentid() {
		return (String) saveArray[ tableFldConstants.equipmentid.ordinal() ];
	}

	public void setAbnmEquipmentid(String abnmEquipmentid) {
		saveArray[ tableFldConstants.equipmentid.ordinal() ] = abnmEquipmentid;
	}

	public String getAbnmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setAbnmSectionid(String abnmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = abnmSectionid;
	}

	public String getAbnmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setAbnmCellid(String abnmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = abnmCellid;
	}

	public String getAbnmAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setAbnmAssemblyid(String abnmAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = abnmAssemblyid;
	}

	public String getAbnmShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setAbnmShiftid(String abnmShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = abnmShiftid;
	}

	public String getAbnmTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setAbnmTradeid(String abnmTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = abnmTradeid;
	}

	public String getAbnmWoreceiveddate() {
		return (String) saveArray[ tableFldConstants.woreceiveddate.ordinal() ];
	}

	public void setAbnmWoreceiveddate(String abnmWoreceiveddate) {
		saveArray[ tableFldConstants.woreceiveddate.ordinal() ] = abnmWoreceiveddate;
	}

	public String getAbnmResponsetime() {
		return (String) saveArray[ tableFldConstants.responsetime.ordinal() ];
	}

	public void setAbnmResponsetime(String abnmResponsetime) {
		saveArray[ tableFldConstants.responsetime.ordinal() ] = abnmResponsetime;
	}

	public String getAbnmWorktime() {
		return (String) saveArray[ tableFldConstants.worktime.ordinal() ];
	}

	public void setAbnmWorktime(String abnmWorktime) {
		saveArray[ tableFldConstants.worktime.ordinal() ] = abnmWorktime;
	}

	public String getAbnmWostarttime() {
		return (String) saveArray[ tableFldConstants.wostarttime.ordinal() ];
	}

	public void setAbnmWostarttime(String abnmWostarttime) {
		saveArray[ tableFldConstants.wostarttime.ordinal() ] = abnmWostarttime;
	}

	public String getAbnmWoendtime() {
		return (String) saveArray[ tableFldConstants.woendtime.ordinal() ];
	}

	public void setAbnmWoendtime(String abnmWoendtime) {
		saveArray[ tableFldConstants.woendtime.ordinal() ] = abnmWoendtime;
	}

	public String getAbnmDowntime() {
		return (String) saveArray[ tableFldConstants.downtime.ordinal() ];
	}

	public void setAbnmDowntime(String abnmDowntime) {
		saveArray[ tableFldConstants.downtime.ordinal() ] = abnmDowntime;
	}

	public String getAbnmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setAbnmDescription(String abnmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = abnmDescription;
	}

	public String getAbnmTypeid() {
		return (String) saveArray[ tableFldConstants.typeid.ordinal() ];
	}

	public void setAbnmTypeid(String abnmTypeid) {
		saveArray[ tableFldConstants.typeid.ordinal() ] = abnmTypeid;
	}

	public String getAbnmWhyabnhappened() {
		return (String) saveArray[ tableFldConstants.whyabnhappened.ordinal() ];
	}

	public void setAbnmWhyabnhappened(String abnmWhyabnhappened) {
		saveArray[ tableFldConstants.whyabnhappened.ordinal() ] = abnmWhyabnhappened;
	}

	public String getAbnmWhatcause() {
		return (String) saveArray[ tableFldConstants.whatcause.ordinal() ];
	}

	public void setAbnmWhatcause(String abnmWhatcause) {
		saveArray[ tableFldConstants.whatcause.ordinal() ] = abnmWhatcause;
	}

	public String getAbnmTagclassid() {
		return (String) saveArray[ tableFldConstants.tagclassid.ordinal() ];
	}

	public void setAbnmTagclassid(String abnmTagclassid) {
		saveArray[ tableFldConstants.tagclassid.ordinal() ] = abnmTagclassid;
	}

	public String getAbnmCategoryid() {
		return (String) saveArray[ tableFldConstants.categoryid.ordinal() ];
	}

	public void setAbnmCategoryid(String abnmCategoryid) {
		saveArray[ tableFldConstants.categoryid.ordinal() ] = abnmCategoryid;
	}

	public String getAbnmImpactid() {
		return (String) saveArray[ tableFldConstants.impactid.ordinal() ];
	}

	public void setAbnmImpactid(String abnmImpactid) {
		saveArray[ tableFldConstants.impactid.ordinal() ] = abnmImpactid;
	}

	public String getAbnmCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setAbnmCountermeasure(String abnmCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = abnmCountermeasure;
	}

	public String getAbnmPreventivemeasure() {
		return (String) saveArray[ tableFldConstants.preventivemeasure.ordinal() ];
	}

	public void setAbnmPreventivemeasure(String abnmPreventivemeasure) {
		saveArray[ tableFldConstants.preventivemeasure.ordinal() ] = abnmPreventivemeasure;
	}

	public String getAbnmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setAbnmStatus(String abnmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = abnmStatus;
	}

	public String getAbnmTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setAbnmTargetdate(String abnmTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = abnmTargetdate;
	}

	public String getAbnmTargetremarks() {
		return (String) saveArray[ tableFldConstants.targetremarks.ordinal() ];
	}

	public void setAbnmTargetremarks(String abnmTargetremarks) {
		saveArray[ tableFldConstants.targetremarks.ordinal() ] = abnmTargetremarks;
	}

	public String getAbnmCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setAbnmCompletedby(String abnmCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = abnmCompletedby;
	}

	public String getAbnmWomasterid() {
		return (String) saveArray[ tableFldConstants.womasterid.ordinal() ];
	}

	public void setAbnmWomasterid(String abnmWomasterid) {
		saveArray[ tableFldConstants.womasterid.ordinal() ] = abnmWomasterid;
	}

	public String getAbnmWodetailid() {
		return (String) saveArray[ tableFldConstants.wodetailid.ordinal() ];
	}

	public void setAbnmWodetailid(String abnmWodetailid) {
		saveArray[ tableFldConstants.wodetailid.ordinal() ] = abnmWodetailid;
	}

	public String getAbnmFeedbackid() {
		return (String) saveArray[ tableFldConstants.feedbackid.ordinal() ];
	}

	public void setAbnmFeedbackid(String abnmFeedbackid) {
		saveArray[ tableFldConstants.feedbackid.ordinal() ] = abnmFeedbackid;
	}

	public String getAbnmFeedbackdate() {
		return (String) saveArray[ tableFldConstants.feedbackdate.ordinal() ];
	}

	public void setAbnmFeedbackdate(String abnmFeedbackdate) {
		saveArray[ tableFldConstants.feedbackdate.ordinal() ] = abnmFeedbackdate;
	}

	public String getAbnmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setAbnmRemarks(String abnmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = abnmRemarks;
	}

	public String getAbnmBlockdiagramref() {
		return (String) saveArray[ tableFldConstants.blockdiagramref.ordinal() ];
	}

	public void setAbnmBlockdiagramref(String abnmBlockdiagramref) {
		saveArray[ tableFldConstants.blockdiagramref.ordinal() ] = abnmBlockdiagramref;
	}

	public String getAbnmRevisionno() {
		return (String) saveArray[ tableFldConstants.revisionno.ordinal() ];
	}

	public void setAbnmRevisionno(String abnmRevisionno) {
		saveArray[ tableFldConstants.revisionno.ordinal() ] = abnmRevisionno;
	}

	public String getAbnmPriority() {
		return (String) saveArray[ tableFldConstants.priority.ordinal() ];
	}

	public void setAbnmPriority(String abnmPriority) {
		saveArray[ tableFldConstants.priority.ordinal() ] = abnmPriority;
	}

	public String getAbnmDetailedesc() {
		return (String) saveArray[ tableFldConstants.detailedesc.ordinal() ];
	}

	public void setAbnmDetailedesc(String abnmDetailedesc) {
		saveArray[ tableFldConstants.detailedesc.ordinal() ] = abnmDetailedesc;
	}

	public String getAbnmSubtype() {
		return (String) saveArray[ tableFldConstants.subtype.ordinal() ];
	}

	public void setAbnmSubtype(String abnmSubtype) {
		saveArray[ tableFldConstants.subtype.ordinal() ] = abnmSubtype;
	}

	public String getAbnmContaminant() {
		return (String) saveArray[ tableFldConstants.contaminant.ordinal() ];
	}

	public void setAbnmContaminant(String abnmContaminant) {
		saveArray[ tableFldConstants.contaminant.ordinal() ] = abnmContaminant;
	}

	public String getAbnmMode() {
		return (String) saveArray[ tableFldConstants.mode.ordinal() ];
	}

	public void setAbnmMode(String abnmMode) {
		saveArray[ tableFldConstants.mode.ordinal() ] = abnmMode;
	}

	public String getAbnmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setAbnmFactoryid(String abnmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = abnmFactoryid;
	}

	public String getAbnmPillar() {
		return (String) saveArray[ tableFldConstants.pillar.ordinal() ];
	}

	public void setAbnmPillar(String abnmPillar) {
		saveArray[ tableFldConstants.pillar.ordinal() ] = abnmPillar;
	}

	public String getAbnmSafetypatrol() {
		return (String) saveArray[ tableFldConstants.safetypatrol.ordinal() ];
	}

	public void setAbnmSafetypatrol(String abnmSafetypatrol) {
		saveArray[ tableFldConstants.safetypatrol.ordinal() ] = abnmSafetypatrol;
	}

	public String getAbnmRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setAbnmRelatedto(String abnmRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = abnmRelatedto;
	}

	public String getAbnmMould() {
		return (String) saveArray[ tableFldConstants.mould.ordinal() ];
	}

	public void setAbnmMould(String abnmMould) {
		saveArray[ tableFldConstants.mould.ordinal() ] = abnmMould;
	}
	
	public String getAbnmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setAbnmFlid(String abnmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = abnmFlid;
	}
	public String getAbnmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setAbnmElementid(String abnmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = abnmElementid;
	}
	
	public String getAbnmPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setAbnmPillarid(String abnmPillarid) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = abnmPillarid;
	}

	public String getAbnmRepeatedabn() {
		return (String) saveArray[ tableFldConstants.repeatedabn.ordinal() ];
	}

	public void setAbnmRepeatedabn(String abnmRepeatedabn) {
		saveArray[ tableFldConstants.repeatedabn.ordinal() ] = abnmRepeatedabn;
	}

	public String getAbnmAfeemid() {
		return (String) saveArray[ tableFldConstants.afeemid.ordinal() ];
	}

	public void setAbnmAfeemid(String abnmAfeemid) {
		saveArray[ tableFldConstants.afeemid.ordinal() ] = abnmAfeemid;
	}

	


	public String getAbnmEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setAbnmEffectivedate(String abnmEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = abnmEffectivedate;
	}

	public String getAbnmNotifysap() {
		return (String) saveArray[ tableFldConstants.notifysap.ordinal() ];
	}

	public void setAbnmNotifysap(String abnmNotifysap) {
		saveArray[ tableFldConstants.notifysap.ordinal() ] = abnmNotifysap;
	}

	public String getAbnmShutdownmaint() {
		return (String) saveArray[ tableFldConstants.shutdownmaint.ordinal() ];
	}

	public void setAbnmShutdownmaint(String abnmShutdownmaint) {
		saveArray[ tableFldConstants.shutdownmaint.ordinal() ] = abnmShutdownmaint;
	}

	public String getAbnmTentativrDate() {
		return (String) saveArray[ tableFldConstants.tentativedate.ordinal() ];
	}

	public void setAbnmTentativrDate(String abnmTentative) {
		saveArray[ tableFldConstants.tentativedate.ordinal() ] = abnmTentative;
	}

	public String getAbnmShutdownid() {
		return (String) saveArray[ tableFldConstants.shutdownid.ordinal() ];
	}

	public void setAbnmShutdownid(String abnmShutdownid) {
		saveArray[ tableFldConstants.shutdownid.ordinal() ] = abnmShutdownid;
	}

	
	public String getAbnmAccecpatncerequired() {
		return (String) saveArray[ tableFldConstants.accecpatncerequired.ordinal() ];
	}

	public void setAbnmAccecpatncerequired(String abnmAccecpatncerequired) {
		saveArray[ tableFldConstants.accecpatncerequired.ordinal() ] = abnmAccecpatncerequired;
	}

	public String getAbnmAccecptDate() {
		return (String) saveArray[ tableFldConstants.accecptdate.ordinal() ];
	}

	public void setAbnmAccecptDate(String abnmAccecptdate) {
		saveArray[ tableFldConstants.accecptdate.ordinal() ] = abnmAccecptdate;
	}
	
	public String getAbnmAccecpted() {
		return (String) saveArray[ tableFldConstants.accecpted.ordinal() ];
	}

	public void setAbnmAccecpted(String abnmAccecpted) {
		saveArray[ tableFldConstants.accecpted.ordinal() ] = abnmAccecpted;
	}

	public String getAbnmOthers() {
		return (String) saveArray[ tableFldConstants.others.ordinal() ];
	}

	public void setAbnmOthers(String abnmOthers) {
		saveArray[ tableFldConstants.others.ordinal() ] = abnmOthers;
	}


	public String getAbnmRepOthers() {
		return (String) saveArray[ tableFldConstants.repotheres.ordinal() ];
	}

	public void setAbnmRepOthers(String abnmRepotheres) {
		saveArray[ tableFldConstants.repotheres.ordinal() ] = abnmRepotheres;
	}

	public String getAbnmResponsibleid() {
		return (String) saveArray[ tableFldConstants.responsibleid.ordinal() ];
	}

	public void setAbnmResponsibleid(String abnmResponsibleid) {
		saveArray[ tableFldConstants.responsibleid.ordinal() ] = abnmResponsibleid;
	}
	public String getAbnmMultipleabn() {
		return (String) saveArray[ tableFldConstants.multipleabn.ordinal() ];
	}

	public void setAbnmMultipleabn(String abnmMultipleabn) {
		saveArray[ tableFldConstants.multipleabn.ordinal() ] = abnmMultipleabn;
	}
	public String getAbnmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setAbnmTempfield4(String abnmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = abnmTempfield4;
	}
	public String getAbnmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setAbnmTempfield5(String abnmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = abnmTempfield5;
	}
	public String getAbnmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setAbnmTempfield6(String abnmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = abnmTempfield6;
	}
	public String getAbnmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setAbnmTempfield7(String abnmTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = abnmTempfield7;
	}
	public String getAbnmTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setAbnmTempfield8(String abnmTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = abnmTempfield8;
	}
	public String getAbnmTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setAbnmTempfield9(String abnmTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = abnmTempfield9;
	}
	public String getAbnmTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setAbnmTempfield10(String abnmTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = abnmTempfield10;
	}

	public String getAbnmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAbnmActive(String abnmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = abnmActive;
	}

	public String getAbnmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAbnmCreatedby(String abnmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = abnmCreatedby;
	}

	public String getAbnmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAbnmCreatedon(String abnmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = abnmCreatedon;
	}

	public String getAbnmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAbnmModifiedon(String abnmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = abnmModifiedon;
	}

	public void setAbnTlDtl(AbnTlDtl abnTlDtl) {
		this.abnTlDtl = abnTlDtl;
	}

	public AbnTlDtl getAbnTlDtl() {
		return abnTlDtl;
	}

	public void setTeamList(List<GenTlTeamDoucmentLink> teamList) {
		TeamList = teamList;
	}

	public List<GenTlTeamDoucmentLink> getTeamList() {
		return TeamList;
	}

	public AbnTlAbnhistorydtl getAbnTlAbnhistorydtl() {
		return abnTlAbnhistorydtl;
	}

	public void setAbnTlAbnhistorydtl(AbnTlAbnhistorydtl abnTlAbnhistorydtl) {
		this.abnTlAbnhistorydtl = abnTlAbnhistorydtl;
	}
   public List<AbnTlDtl> getabnTldtl(){
	   return abnTldtl;
   }
  public void setabnTldtl(List<AbnTlDtl> AbnTldtl){
	 this.abnTldtl=AbnTldtl;
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
	            if(field.name() == "keyid" && val == null) {
	            	sb.append("null");
	            }else if (val == null) {
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
  
  public static String toJsonManualList(List<AbnTlAbnormality> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
  
 
  
  public static AbnTlAbnormality fromJson(String json) {
	  //CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  AbnTlAbnormality abn = new AbnTlAbnormality();
	  //CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	//CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
	        abn.setValue(field, val);
	    	
	    }
	    return abn;
	}
  
  public static List<AbnTlAbnormality> fromJsonList(String json) {

	   // CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

	    JSONArray jsonArray = JSONArray.fromObject(json);
	    List<AbnTlAbnormality> list = new ArrayList<>();

	    for (int i = 0; i < jsonArray.length(); i++) {

	        JSONObject obj = jsonArray.getJSONObject(i);
	        AbnTlAbnormality abn = new AbnTlAbnormality();

	        for (tableFldConstants field : tableFldConstants.values()) {
	            String key = field.name();

	            Object valueObj = obj.opt(key);
	           // String val = valueObj.toString();
	            String val = (valueObj == null) ? "" : valueObj.toString();
	            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
//	            String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();

	            abn.setValue(field, val);
	        }

	        list.add(abn);
	    }

	    return list;
	}
  
  

}

