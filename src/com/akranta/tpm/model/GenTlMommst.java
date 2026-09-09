package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlMommst {

	private  Object [] saveArray = null; 
	

	
	private List<GenTlMomdtl> MomeetingDetail ;
	private List<GenTlMomKpiLink> MomeetignKpi;
	private List<GenTlMomattendance> MomeettingAttence;
	private GenTlActionplanmst isActionPlanMaster;
	private List<GenTlActionplandtl> isActionPlanDetail;
	private List<GenTlActionplanmst>  Momactionplanmst;
	//private List<GenTlActionplandtl> Momactionplandtl;*/
	private String elementid;
	
	public enum   tableFldConstants
	{
		keyid, flid, date, shiftid, ismeetinghappen, safetytalk, remarks
		, meetingno, meetingtitle, meetingtype, pillarid, ismessageboard, agenda,others, momsPillargroup
		, refdocid,refdoctype,ismail,tempefield1,tempefield2,tempefield3,active, createdby, createdon, modifiedon
	}

	public GenTlMommst()
	{
		setMomeetingDetail(new ArrayList<GenTlMomdtl> ());
		setMomeettingAttence(new ArrayList<GenTlMomattendance>());
		/*setMomactionplanmst(new ArrayList<GenTlActionplanmst>());
		setMomactionplandtl(new ArrayList<GenTlActionplandtl>());*/
		saveArray = new  Object [ 25];
	}
	
	

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public String getMomsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMomsKeyid(String momsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = momsKeyid;
	}

	public String getMomsFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMomsFlid(String momsFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = momsFlid;
	}

	public String getMomsDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setMomsDate(String momsDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = momsDate;
	}

	public String getMomsShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setMomsShiftid(String momsShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = momsShiftid;
	}

	public String getMomsIsmeetinghappen() {
		return (String) saveArray[ tableFldConstants.ismeetinghappen.ordinal() ];
	}

	public void setMomsIsmeetinghappen(String momsIsmeetinghappen) {
		saveArray[ tableFldConstants.ismeetinghappen.ordinal() ] = momsIsmeetinghappen;
	}

	public String getMomsSafetytalk() {
		return (String) saveArray[ tableFldConstants.safetytalk.ordinal() ];
	}

	public void setMomsSafetytalk(String momsSafetytalk) {
		saveArray[ tableFldConstants.safetytalk.ordinal() ] = momsSafetytalk;
	}

	public String getMomsRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMomsRemarks(String momsRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = momsRemarks;
	}
	
	public String getMomsMeetingno()
	{
		return (String) saveArray[ tableFldConstants.meetingno.ordinal() ];
	}

	public void setMomsMeetingno(String momsMeetingno) 
	{
		saveArray[ tableFldConstants.meetingno.ordinal() ] = momsMeetingno;
	}
	
	public String getMomsMeetingtitle() {
		return (String) saveArray[ tableFldConstants.meetingtitle.ordinal() ];
	}

	public void setMomsMeetingtitle(String momsMeetingtitle) {
		saveArray[ tableFldConstants.meetingtitle.ordinal() ] = momsMeetingtitle;
	}
	
	public String getMomsMeetingtype() {
		return (String) saveArray[ tableFldConstants.meetingtype.ordinal() ];
	}

	public void setMomsMeetingtype(String momsMeetingtype) {
		saveArray[ tableFldConstants.meetingtype.ordinal() ] = momsMeetingtype;
	}
	
	public String getMomsPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setMomsPillarid(String momsPillarid) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = momsPillarid;
	}


	public String getMomsIsmessageboard() {
		return (String) saveArray[ tableFldConstants.ismessageboard.ordinal() ];
	}

	public void setMomsIsmessageboard(String momsIsmessageboard) {
		saveArray[ tableFldConstants.ismessageboard.ordinal() ] = momsIsmessageboard;
	}

	public String getMomsAgenda() {
		return (String) saveArray[ tableFldConstants.agenda.ordinal() ];
	}

	public void setMomsAgenda(String momsAgenda) {
		saveArray[ tableFldConstants.agenda.ordinal() ] = momsAgenda;
	}

	public String getMomsOthers() {
		return (String) saveArray[ tableFldConstants.others.ordinal() ];
	}

	public void setMomsOthers(String momsOthers) {
		saveArray[ tableFldConstants.others.ordinal() ] = momsOthers;
	}

	public String getMomsPillargroup() {
		return (String) saveArray[ tableFldConstants.momsPillargroup.ordinal() ];
	}

	public void setMomsPillargroup(String momsPillargroup) {
		saveArray[ tableFldConstants.momsPillargroup.ordinal() ] = momsPillargroup;
	}
	
	public String getMomsRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setMomsRefdocid(String momsRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = momsRefdocid;
	}

	public String getMomsRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setMomsRefdoctype(String momsRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = momsRefdoctype;
	}
	
	
	public String getMomsIsMailTrig() {
		return (String) saveArray[ tableFldConstants.ismail.ordinal() ];
	}

	public void setMomsIsMailTrig(String momsIsMailTrig) {
		saveArray[ tableFldConstants.ismail.ordinal() ] = momsIsMailTrig;
	}
    

	
	public String getMomsTempField1() {
		return (String) saveArray[ tableFldConstants.tempefield1.ordinal() ];
	}

	public void setMomsTempField1(String momsTempField1) {
		saveArray[ tableFldConstants.tempefield1.ordinal() ] = momsTempField1;
	}
	
	public String getMomsTempField2() {
		return (String) saveArray[ tableFldConstants.tempefield2.ordinal() ];
	}

	public void setMomsTempField2(String momsTempField2) {
		saveArray[ tableFldConstants.tempefield2.ordinal() ] = momsTempField2;
	}
	
	public String getMomsTempField3() {
		return (String) saveArray[ tableFldConstants.tempefield3.ordinal() ];
	}

	public void setMomsTempField3(String momsTempField3) {
		saveArray[ tableFldConstants.tempefield3.ordinal() ] = momsTempField3;
	}

	public String getMomsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMomsActive(String momsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = momsActive;
	}

	public String getMomsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMomsCreatedby(String momsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = momsCreatedby;
	}

	public String getMomsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMomsCreatedon(String momsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = momsCreatedon;
	}

	public String getMomsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMomsModifiedon(String momsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = momsModifiedon;
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
		
	}

	public void setMomeetingDetail(List<GenTlMomdtl> momeetingDetail) {
		this.MomeetingDetail = momeetingDetail;
	}

	public List<GenTlMomdtl> getMomeetingDetail() {
		return MomeetingDetail;
	}
	public void setMomeetingKPI(List<GenTlMomKpiLink> momeetingKpi) {
		this.MomeetignKpi = momeetingKpi;
	}

	public List<GenTlMomKpiLink> getMomeetingKPI() {
		return MomeetignKpi;
	}
	
	public void setMomeettingAttence(List<GenTlMomattendance> momeetinMomattendances) {
		this.MomeettingAttence = momeetinMomattendances;
	}

	public List<GenTlMomattendance> getMomeetinMomattendances() {
		return MomeettingAttence;
	}
	public void setMomactionplanmst(List<GenTlActionplanmst> momactionplanmst) {
		this.Momactionplanmst = momactionplanmst;
	}

	public List<GenTlActionplanmst> getMomactionplanmst() {
		return Momactionplanmst;
	}
	
	public void setisActionPlanDetail(List<GenTlActionplandtl> isactionPlanDetail) {
		this.isActionPlanDetail = isactionPlanDetail;
	}

	public List<GenTlActionplandtl> getisActionPlanDetail() {
		return isActionPlanDetail;
	}
	
	/*public void setMomactionplandtl(List<GenTlActionplandtl> momactionplandtl) {
		this.Momactionplandtl = momactionplandtl;
	}

	public List<GenTlActionplandtl> getMomactionplandtl() {
		return Momactionplandtl;
	}
	public void setElementid(String elementid) {
		this.elementid = elementid;
	}*/

	public String getElementid() {
		return elementid;
	}
	public GenTlActionplanmst getisActionPlanMaster() {
			return isActionPlanMaster;
		}
	public void setisActionPlanMaster(GenTlActionplanmst isActionPlanMaster){
			this.isActionPlanMaster= isActionPlanMaster;
		}
	/*public GenTlActionplandtl getisActionPlanDetail() {
			return isActionPlanDetail;
	}
	public void setisActionPlanDetail(GenTlActionplandtl isActionPlanDetail){
		this.isActionPlanDetail= isActionPlanDetail;
	}*/
	
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
	  
	  public static GenTlMommst fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  GenTlMommst mom = new GenTlMommst();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        mom.setValue(field,  val);
		        //mom.setValue(field, val != null && val.equals("null") ? null : val);
		    	
		    }
		    return mom;
		}
}

