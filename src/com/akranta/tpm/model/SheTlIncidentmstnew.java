package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SheTlIncidentmstnew {

	private  Object [] saveArray = null;  
	private List<GenTlAllmoduleimgfile> allmoduleimgfile;
	

	public enum   tableFldConstants
	{
		keyid, elementid, flid, locn_keyid, fact_keyid, sect_keyid, cell_keyid
		, mach_keyid, department, division, area, accident_type, accident_desc
		, immediate_action, rptrecommendations, lossproperty, directcost
		, noofemployees, occurred_date, occurred_shift, reported_date
		, reported_shift, reported_by,witness1,witness2, exact_desc, exact_type, investigation,invstremarks
		, phenomena, whywhyflag, whywhyno, rootcause, countermeasure
		, rootcausedueto, idea, analysed_by, analysed_date, analysed_shift
		, invrptid, invrptby,invrptremarks, invrptdate, carecommended, responsibility
		, targetdate,responseremarks, actionplanno, status, reviseddate, actiontaken
		, completedby, datecompleted,completeremarks, verifiedstatus, verifiedby, dateverified,verifyremarks
		, approvalstatus, approvalby, approvaldate,approvalremarks,tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, tempfield7
		, active
		, createdby, modifiedby, createdon, modifiedon
	}

	public SheTlIncidentmstnew()
	{
		saveArray = new  Object [ 74 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getSinmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSinmKeyid(String sinmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sinmKeyid;
	}

	public String getSinmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setSinmElementid(String sinmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = sinmElementid;
	}

	public String getSinmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSinmFlid(String sinmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = sinmFlid;
	}

	public String getSinmLocnKeyid() {
		return (String) saveArray[ tableFldConstants.locn_keyid.ordinal() ];
	}

	public void setSinmLocnKeyid(String sinmLocnKeyid) {
		saveArray[ tableFldConstants.locn_keyid.ordinal() ] = sinmLocnKeyid;
	}

	public String getSinmFactKeyid() {
		return (String) saveArray[ tableFldConstants.fact_keyid.ordinal() ];
	}

	public void setSinmFactKeyid(String sinmFactKeyid) {
		saveArray[ tableFldConstants.fact_keyid.ordinal() ] = sinmFactKeyid;
	}

	public String getSinmSectKeyid() {
		return (String) saveArray[ tableFldConstants.sect_keyid.ordinal() ];
	}

	public void setSinmSectKeyid(String sinmSectKeyid) {
		saveArray[ tableFldConstants.sect_keyid.ordinal() ] = sinmSectKeyid;
	}

	public String getSinmCellKeyid() {
		return (String) saveArray[ tableFldConstants.cell_keyid.ordinal() ];
	}

	public void setSinmCellKeyid(String sinmCellKeyid) {
		saveArray[ tableFldConstants.cell_keyid.ordinal() ] = sinmCellKeyid;
	}

	public String getSinmMachKeyid() {
		return (String) saveArray[ tableFldConstants.mach_keyid.ordinal() ];
	}

	public void setSinmMachKeyid(String sinmMachKeyid) {
		saveArray[ tableFldConstants.mach_keyid.ordinal() ] = sinmMachKeyid;
	}

	public String getSinmDepartment() {
		return (String) saveArray[ tableFldConstants.department.ordinal() ];
	}

	public void setSinmDepartment(String sinmDepartment) {
		saveArray[ tableFldConstants.department.ordinal() ] = sinmDepartment;
	}

	public String getSinmDivision() {
		return (String) saveArray[ tableFldConstants.division.ordinal() ];
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	

	public void setSinmDivision(String sinmDivision) {
		saveArray[ tableFldConstants.division.ordinal() ] = sinmDivision;
	}

	public String getSinmArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setSinmArea(String sinmArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = sinmArea;
	}

	public String getSinmAccidentType() {
		return (String) saveArray[ tableFldConstants.accident_type.ordinal() ];
	}

	public void setSinmAccidentType(String sinmAccidentType) {
		saveArray[ tableFldConstants.accident_type.ordinal() ] = sinmAccidentType;
	}

	public String getSinmAccidentDesc() {
		return (String) saveArray[ tableFldConstants.accident_desc.ordinal() ];
	}

	public void setSinmAccidentDesc(String sinmAccidentDesc) {
		saveArray[ tableFldConstants.accident_desc.ordinal() ] = sinmAccidentDesc;
	}

	public String getSinmImmediateAction() {
		return (String) saveArray[ tableFldConstants.immediate_action.ordinal() ];
	}

	public void setSinmImmediateAction(String sinmImmediateAction) {
		saveArray[ tableFldConstants.immediate_action.ordinal() ] = sinmImmediateAction;
	}

	public String getSinmRptrecommendations() {
		return (String) saveArray[ tableFldConstants.rptrecommendations.ordinal() ];
	}

	public void setSinmRptrecommendations(String sinmRptrecommendations) {
		saveArray[ tableFldConstants.rptrecommendations.ordinal() ] = sinmRptrecommendations;
	}

	public String getSinmLossproperty() {
		return (String) saveArray[ tableFldConstants.lossproperty.ordinal() ];
	}

	public void setSinmLossproperty(String sinmLossproperty) {
		saveArray[ tableFldConstants.lossproperty.ordinal() ] = sinmLossproperty;
	}

	public String getSinmDirectcost() {
		return (String) saveArray[ tableFldConstants.directcost.ordinal() ];
	}

	public void setSinmDirectcost(String sinmDirectcost) {
		saveArray[ tableFldConstants.directcost.ordinal() ] = sinmDirectcost;
	}

	public String getSinmNoofemployees() {
		return (String) saveArray[ tableFldConstants.noofemployees.ordinal() ];
	}

	public void setSinmNoofemployees(String sinmNoofemployees) {
		saveArray[ tableFldConstants.noofemployees.ordinal() ] = sinmNoofemployees;
	}

	public String getSinmOccurredDate() {
		return (String) saveArray[ tableFldConstants.occurred_date.ordinal() ];
	}

	public void setSinmOccurredDate(String sinmOccurredDate) {
		saveArray[ tableFldConstants.occurred_date.ordinal() ] = sinmOccurredDate;
	}

	public String getSinmOccurredShift() {
		return (String) saveArray[ tableFldConstants.occurred_shift.ordinal() ];
	}

	public void setSinmOccurredShift(String sinmOccurredShift) {
		saveArray[ tableFldConstants.occurred_shift.ordinal() ] = sinmOccurredShift;
	}

	public String getSinmReportedDate() {
		return (String) saveArray[ tableFldConstants.reported_date.ordinal() ];
	}

	public void setSinmReportedDate(String sinmReportedDate) {
		saveArray[ tableFldConstants.reported_date.ordinal() ] = sinmReportedDate;
	}

	public String getSinmReportedShift() {
		return (String) saveArray[ tableFldConstants.reported_shift.ordinal() ];
	}

	public void setSinmReportedShift(String sinmReportedShift) {
		saveArray[ tableFldConstants.reported_shift.ordinal() ] = sinmReportedShift;
	}

	public String getSinmReportedBy() {
		return (String) saveArray[ tableFldConstants.reported_by.ordinal() ];
	}

	public void setSinmReportedBy(String sinmReportedBy) {
		saveArray[ tableFldConstants.reported_by.ordinal() ] = sinmReportedBy;
	}
    
	public String getSinmWitness1() {
		return (String) saveArray[ tableFldConstants.witness1.ordinal() ];
	}

	public void setSinmWitness1(String sinmWitness1) {
		saveArray[ tableFldConstants.witness1.ordinal() ] = sinmWitness1;
	}

	
	public String getSinmWitness2() {
		return (String) saveArray[ tableFldConstants.witness2.ordinal() ];
	}

	public void setSinmWitness2(String sinmWitness2) {
		saveArray[ tableFldConstants.witness2.ordinal() ] = sinmWitness2;
	}

	
	
	public String getSinmExactDesc() {
		return (String) saveArray[ tableFldConstants.exact_desc.ordinal() ];
	}

	public void setSinmExactDesc(String sinmExactDesc) {
		saveArray[ tableFldConstants.exact_desc.ordinal() ] = sinmExactDesc;
	}

	public String getSinmExactType() {
		return (String) saveArray[ tableFldConstants.exact_type.ordinal() ];
	}

	public void setSinmExactType(String sinmExactType) {
		saveArray[ tableFldConstants.exact_type.ordinal() ] = sinmExactType;
	}

	public String getSinmInvestigation() {
		return (String) saveArray[ tableFldConstants.investigation.ordinal() ];
	}

	public void setSinmInvestigation(String sinmInvestigation) {
		saveArray[ tableFldConstants.investigation.ordinal() ] = sinmInvestigation;
	}
	
	public String getSinmInvestigationRemarks() {
		return (String) saveArray[ tableFldConstants.invstremarks.ordinal() ];
	}

	public void setSinmInvestigationRemarks(String sinmInvestigationRemarks) {
		saveArray[ tableFldConstants.invstremarks.ordinal() ] = sinmInvestigationRemarks;
	}

	public String getSinmPhenomena() {
		return (String) saveArray[ tableFldConstants.phenomena.ordinal() ];
	}

	public void setSinmPhenomena(String sinmPhenomena) {
		saveArray[ tableFldConstants.phenomena.ordinal() ] = sinmPhenomena;
	}

	public String getSinmWhywhyflag() {
		return (String) saveArray[ tableFldConstants.whywhyflag.ordinal() ];
	}

	public void setSinmWhywhyflag(String sinmWhywhyflag) {
		saveArray[ tableFldConstants.whywhyflag.ordinal() ] = sinmWhywhyflag;
	}

	public String getSinmWhywhyno() {
		return (String) saveArray[ tableFldConstants.whywhyno.ordinal() ];
	}

	public void setSinmWhywhyno(String sinmWhywhyno) {
		saveArray[ tableFldConstants.whywhyno.ordinal() ] = sinmWhywhyno;
	}

	public String getSinmRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setSinmRootcause(String sinmRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = sinmRootcause;
	}

	public String getSinmCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setSinmCountermeasure(String sinmCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = sinmCountermeasure;
	}

	public String getSinmRootcausedueto() {
		return (String) saveArray[ tableFldConstants.rootcausedueto.ordinal() ];
	}

	public void setSinmRootcausedueto(String sinmRootcausedueto) {
		saveArray[ tableFldConstants.rootcausedueto.ordinal() ] = sinmRootcausedueto;
	}

	public String getSinmIdea() {
		return (String) saveArray[ tableFldConstants.idea.ordinal() ];
	}

	public void setSinmIdea(String sinmIdea) {
		saveArray[ tableFldConstants.idea.ordinal() ] = sinmIdea;
	}

	public String getSinmAnalysedBy() {
		return (String) saveArray[ tableFldConstants.analysed_by.ordinal() ];
	}

	public void setSinmAnalysedBy(String sinmAnalysedBy) {
		saveArray[ tableFldConstants.analysed_by.ordinal() ] = sinmAnalysedBy;
	}

	public String getSinmAnalysedDate() {
		return (String) saveArray[ tableFldConstants.analysed_date.ordinal() ];
	}

	public void setSinmAnalysedDate(String sinmAnalysedDate) {
		saveArray[ tableFldConstants.analysed_date.ordinal() ] = sinmAnalysedDate;
	}

	public String getSinmAnalysedShift() {
		return (String) saveArray[ tableFldConstants.analysed_shift.ordinal() ];
	}

	public void setSinmAnalysedShift(String sinmAnalysedShift) {
		saveArray[ tableFldConstants.analysed_shift.ordinal() ] = sinmAnalysedShift;
	}

	public String getSinmInvrptid() {
		return (String) saveArray[ tableFldConstants.invrptid.ordinal() ];
	}

	public void setSinmInvrptid(String sinmInvrptid) {
		saveArray[ tableFldConstants.invrptid.ordinal() ] = sinmInvrptid;
	}

	public String getSinmInvrptby() {
		return (String) saveArray[ tableFldConstants.invrptby.ordinal() ];
	}

	public void setSinmInvrptby(String sinmInvrptby) {
		saveArray[ tableFldConstants.invrptby.ordinal() ] = sinmInvrptby;
	}
	
	public String getSinmInvrpRemarks() {
		return (String) saveArray[ tableFldConstants.invrptremarks.ordinal() ];
	}

	public void setSinmInvrpRemarks(String SinmInvrpRemarks) {
		saveArray[ tableFldConstants.invrptremarks.ordinal() ] = SinmInvrpRemarks;
	}

	public String getSinmInvrptdate() {
		return (String) saveArray[ tableFldConstants.invrptdate.ordinal() ];
	}

	public void setSinmInvrptdate(String sinmInvrptdate) {
		saveArray[ tableFldConstants.invrptdate.ordinal() ] = sinmInvrptdate;
	}

	public String getSinmCarecommended() {
		return (String) saveArray[ tableFldConstants.carecommended.ordinal() ];
	}

	public void setSinmCarecommended(String sinmCarecommended) {
		saveArray[ tableFldConstants.carecommended.ordinal() ] = sinmCarecommended;
	}

	public String getSinmResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setSinmResponsibility(String sinmResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = sinmResponsibility;
	}

	public String getSinmTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setSinmTargetdate(String sinmTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = sinmTargetdate;
	}
     
	
	public String getSinmResponsibilityRemarks() {
		return (String) saveArray[ tableFldConstants.responseremarks.ordinal() ];
	}

	public void setSinmResponsibilityRemarks(String sinmResponsibilityRemarks) {
		saveArray[ tableFldConstants.responseremarks.ordinal() ] = sinmResponsibilityRemarks;
	}
	public String getSinmActionplanno() {
		return (String) saveArray[ tableFldConstants.actionplanno.ordinal() ];
	}

	public void setSinmActionplanno(String sinmActionplanno) {
		saveArray[ tableFldConstants.actionplanno.ordinal() ] = sinmActionplanno;
	}

	public String getSinmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setSinmStatus(String sinmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = sinmStatus;
	}

	public String getSinmReviseddate() {
		return (String) saveArray[ tableFldConstants.reviseddate.ordinal() ];
	}

	public void setSinmReviseddate(String sinmReviseddate) {
		saveArray[ tableFldConstants.reviseddate.ordinal() ] = sinmReviseddate;
	}

	public String getSinmActiontaken() {
		return (String) saveArray[ tableFldConstants.actiontaken.ordinal() ];
	}

	public void setSinmActiontaken(String sinmActiontaken) {
		saveArray[ tableFldConstants.actiontaken.ordinal() ] = sinmActiontaken;
	}

	public String getSinmCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setSinmCompletedby(String sinmCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = sinmCompletedby;
	}

	public String getSinmDatecompleted() {
		return (String) saveArray[ tableFldConstants.datecompleted.ordinal() ];
	}

	public void setSinmDatecompleted(String sinmDatecompleted) {
		saveArray[ tableFldConstants.datecompleted.ordinal() ] = sinmDatecompleted;
	}
   
	public String getSinmCompletedRemarks() {
		return (String) saveArray[ tableFldConstants.completeremarks.ordinal() ];
	}

	public void setSinmCompletedRemarks(String sinmCompletedRemarks) {
		saveArray[ tableFldConstants.completeremarks.ordinal() ] = sinmCompletedRemarks;
	}
	
	public String getSinmVerifiedstatus() {
		return (String) saveArray[ tableFldConstants.verifiedstatus.ordinal() ];
	}

	public void setSinmVerifiedstatus(String sinmVerifiedstatus) {
		saveArray[ tableFldConstants.verifiedstatus.ordinal() ] = sinmVerifiedstatus;
	}

	public String getSinmVerifiedby() {
		return (String) saveArray[ tableFldConstants.verifiedby.ordinal() ];
	}

	public void setSinmVerifiedby(String sinmVerifiedby) {
		saveArray[ tableFldConstants.verifiedby.ordinal() ] = sinmVerifiedby;
	}

	public String getSinmDateverified() {
		return (String) saveArray[ tableFldConstants.dateverified.ordinal() ];
	}

	public void setSinmDateverified(String sinmDateverified) {
		saveArray[ tableFldConstants.dateverified.ordinal() ] = sinmDateverified;
	}
    
	public String getSinmVerifiedRemarks() {
		return (String) saveArray[ tableFldConstants.verifyremarks.ordinal() ];
	}

	public void setSinmVerifiedRemarks(String sinmVerifiedRemarks) {
		saveArray[ tableFldConstants.verifyremarks.ordinal() ] = sinmVerifiedRemarks;
	}
	public String getSinmApprovalstatus() {
		return (String) saveArray[ tableFldConstants.approvalstatus.ordinal() ];
	}

	public void setSinmApprovalstatus(String sinmApprovalstatus) {
		saveArray[ tableFldConstants.approvalstatus.ordinal() ] = sinmApprovalstatus;
	}

	public String getSinmApprovalby() {
		return (String) saveArray[ tableFldConstants.approvalby.ordinal() ];
	}

	public void setSinmApprovalby(String sinmApprovalby) {
		saveArray[ tableFldConstants.approvalby.ordinal() ] = sinmApprovalby;
	}

	public String getSinmApprovaldate() {
		return (String) saveArray[ tableFldConstants.approvaldate.ordinal() ];
	}

	public void setSinmApprovaldate(String sinmApprovaldate) {
		saveArray[ tableFldConstants.approvaldate.ordinal() ] = sinmApprovaldate;
	}
	public String getSinmApprovalRemarks() {
		return (String) saveArray[ tableFldConstants.approvalremarks.ordinal() ];
	}

	public void setSinmApprovalRemarks(String sinmApprovalby) {
		saveArray[ tableFldConstants.approvalremarks.ordinal() ] = sinmApprovalby;
	}
	

	public String getSinmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSinmTempfield1(String sinmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = sinmTempfield1;
	}

	public String getSinmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSinmTempfield2(String sinmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = sinmTempfield2;
	}

	public String getSinmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSinmTempfield3(String sinmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = sinmTempfield3;
	}

	public String getSinmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSinmTempfield4(String sinmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = sinmTempfield4;
	}

	public String getSinmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSinmTempfield5(String sinmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = sinmTempfield5;
	}

	public String getSinmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setSinmTempfield6(String sinmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = sinmTempfield6;
	}

	public String getSinmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setSinmTempfield7(String sinmTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = sinmTempfield7;
	}

	

	public String getSinmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSinmActive(String sinmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sinmActive;
	}

	public String getSinmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSinmCreatedby(String sinmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sinmCreatedby;
	}

	public String getSinmModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setSinmModifiedby(String sinmModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = sinmModifiedby;
	}

	public String getSinmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSinmCreatedon(String sinmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sinmCreatedon;
	}

	public String getSinmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSinmModifiedon(String sinmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sinmModifiedon;
	}
	public void setAllmoduleimgfile(List<GenTlAllmoduleimgfile> allmoduleimgfile) {
		this.allmoduleimgfile = allmoduleimgfile;
	}

	public List<GenTlAllmoduleimgfile> getAllmoduleimgfile() {
		return allmoduleimgfile;
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
	
	public static String toJsonManualList(List<SheTlIncidentmstnew> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(list.get(i).toJsonManual());
	    }

	    sb.append("]");
	    return sb.toString();
	}
	
	public static SheTlIncidentmstnew fromJson(String json) {
		  System.out.println("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  SheTlIncidentmstnew she = new SheTlIncidentmstnew();
		  System.out.println("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	System.out.println("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        she.setValue(field, val);
		    	
		    }
		    return she;
		}
	
	 public static List<SheTlIncidentmstnew> fromJsonList(String json) {

		    System.out.println("RAW JSON ARRAY Response: " + json);

		    JSONArray jsonArray = JSONArray.fromObject(json);
		    List<SheTlIncidentmstnew> list = new ArrayList<>();

		    for (int i = 0; i < jsonArray.length(); i++) {

		        JSONObject obj = jsonArray.getJSONObject(i);
		        SheTlIncidentmstnew she = new SheTlIncidentmstnew();

		        for (tableFldConstants field : tableFldConstants.values()) {
		            String key = field.name();

		            Object valueObj = obj.opt(key);
		            String val = (valueObj == null || "null".equals(valueObj.toString()))
		                         ? null
		                         : valueObj.toString();

		            she.setValue(field, val);
		        }

		        list.add(she);
		    }

		    return list;
		}

}

