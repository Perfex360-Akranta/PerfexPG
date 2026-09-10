package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.bean.BalWorkOrderDetailsLstBean;
import com.akranta.tpm.bean.WorkOrderDetailsLstBean;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BalPlmTlWofeedback {

	private  Object [] saveArray = null;  
	private BalPlmTlSparecostactual spareCostActual;
	private BalPlmTlSpareconsumed spareConsumed;
	private List<BalPlmTlMultipleResp> plmTlMultipleResp; 
    private List<BalWorkOrderDetailsLstBean> wobean;
    private String obsvTargetDate = null;
    private String obsvResponsibility = null;
    private String cbmReading = null;
    private String cbmNextDueDate = null;
    private String cbmMinReading = null;
    private String cbmMaxReading = null;
    private String cbmAdjustedReading = null;
    private String pmstandId = null;
    private String pmCalendarId = null;
    
    
	public enum   tableFldConstants
	{
		feedbackid, wodetailid, feedbackdate, machineid, status, action
		, startdate, enddate, completeddate, duration, isprodstopped
		, prodstartdate, currentreading, adjustedreading, uom, whywhyflag
		, whywhyid, amcflag, amcdetailid, spareflag, sparecost, manpowercost
		, contractorcost, othercost, observation, feedback, completedby
		, rescheduleflag, reschedulereason, nextinspectiondate, remarks
		, rootcause, countermeasure, mchcondition,machinetakeovertime, createdby, modifiedon
		, createdon
	}

	public BalPlmTlWofeedback()
	{
		saveArray = new  Object [ 38 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWofbFeedbackid() {
		return (String) saveArray[ tableFldConstants.feedbackid.ordinal() ];
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public void setWofbFeedbackid(String wofbFeedbackid) {
		saveArray[ tableFldConstants.feedbackid.ordinal() ] = wofbFeedbackid;
	}

	public String getWofbWodetailid() {
		return (String) saveArray[ tableFldConstants.wodetailid.ordinal() ];
	}

	public void setWofbWodetailid(String wofbWodetailid) {
		saveArray[ tableFldConstants.wodetailid.ordinal() ] = wofbWodetailid;
	}

	public String getWofbFeedbackdate() {
		return (String) saveArray[ tableFldConstants.feedbackdate.ordinal() ];
	}

	public void setWofbFeedbackdate(String wofbFeedbackdate) {
		saveArray[ tableFldConstants.feedbackdate.ordinal() ] = wofbFeedbackdate;
	}

	public String getWofbMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setWofbMachineid(String wofbMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = wofbMachineid;
	}

	public String getWofbStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setWofbStatus(String wofbStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = wofbStatus;
	}

	public String getWofbAction() {
		return (String) saveArray[ tableFldConstants.action.ordinal() ];
	}

	public void setWofbAction(String wofbAction) {
		saveArray[ tableFldConstants.action.ordinal() ] = wofbAction;
	}

	public String getWofbStartdate() {
		return (String) saveArray[ tableFldConstants.startdate.ordinal() ];
	}

	public void setWofbStartdate(String wofbStartdate) {
		saveArray[ tableFldConstants.startdate.ordinal() ] = wofbStartdate;
	}

	public String getWofbEnddate() {
		return (String) saveArray[ tableFldConstants.enddate.ordinal() ];
	}

	public void setWofbEnddate(String wofbEnddate) {
		saveArray[ tableFldConstants.enddate.ordinal() ] = wofbEnddate;
	}

	public String getWofbCompleteddate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}

	public void setWofbCompleteddate(String wofbCompleteddate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = wofbCompleteddate;
	}

	public String getWofbDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setWofbDuration(String wofbDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = wofbDuration;
	}

	public String getWofbIsprodstopped() {
		return (String) saveArray[ tableFldConstants.isprodstopped.ordinal() ];
	}

	public void setWofbIsprodstopped(String wofbIsprodstopped) {
		saveArray[ tableFldConstants.isprodstopped.ordinal() ] = wofbIsprodstopped;
	}

	public String getWofbProdstartdate() {
		return (String) saveArray[ tableFldConstants.prodstartdate.ordinal() ];
	}

	public void setWofbProdstartdate(String wofbProdstartdate) {
		saveArray[ tableFldConstants.prodstartdate.ordinal() ] = wofbProdstartdate;
	}

	public String getWofbCurrentreading() {
		return (String) saveArray[ tableFldConstants.currentreading.ordinal() ];
	}

	public void setWofbCurrentreading(String wofbCurrentreading) {
		saveArray[ tableFldConstants.currentreading.ordinal() ] = wofbCurrentreading;
	}

	public String getWofbAdjustedreading() {
		return (String) saveArray[ tableFldConstants.adjustedreading.ordinal() ];
	}

	public void setWofbAdjustedreading(String wofbAdjustedreading) {
		saveArray[ tableFldConstants.adjustedreading.ordinal() ] = wofbAdjustedreading;
	}

	public String getWofbUom() {
		return (String) saveArray[ tableFldConstants.uom.ordinal() ];
	}

	public void setWofbUom(String wofbUom) {
		saveArray[ tableFldConstants.uom.ordinal() ] = wofbUom;
	}

	public String getWofbWhywhyflag() {
		return (String) saveArray[ tableFldConstants.whywhyflag.ordinal() ];
	}

	public void setWofbWhywhyflag(String wofbWhywhyflag) {
		saveArray[ tableFldConstants.whywhyflag.ordinal() ] = wofbWhywhyflag;
	}

	public String getWofbWhywhyid() {
		return (String) saveArray[ tableFldConstants.whywhyid.ordinal() ];
	}

	public void setWofbWhywhyid(String wofbWhywhyid) {
		saveArray[ tableFldConstants.whywhyid.ordinal() ] = wofbWhywhyid;
	}

	public String getWofbAmcflag() {
		return (String) saveArray[ tableFldConstants.amcflag.ordinal() ];
	}

	public void setWofbAmcflag(String wofbAmcflag) {
		saveArray[ tableFldConstants.amcflag.ordinal() ] = wofbAmcflag;
	}

	public String getWofbAmcdetailid() {
		return (String) saveArray[ tableFldConstants.amcdetailid.ordinal() ];
	}

	public void setWofbAmcdetailid(String wofbAmcdetailid) {
		saveArray[ tableFldConstants.amcdetailid.ordinal() ] = wofbAmcdetailid;
	}

	public String getWofbSpareflag() {
		return (String) saveArray[ tableFldConstants.spareflag.ordinal() ];
	}

	public void setWofbSpareflag(String wofbSpareflag) {
		saveArray[ tableFldConstants.spareflag.ordinal() ] = wofbSpareflag;
	}

	public String getWofbSparecost() {
		return (String) saveArray[ tableFldConstants.sparecost.ordinal() ];
	}

	public void setWofbSparecost(String wofbSparecost) {
		saveArray[ tableFldConstants.sparecost.ordinal() ] = wofbSparecost;
	}

	public String getWofbManpowercost() {
		return (String) saveArray[ tableFldConstants.manpowercost.ordinal() ];
	}

	public void setWofbManpowercost(String wofbManpowercost) {
		saveArray[ tableFldConstants.manpowercost.ordinal() ] = wofbManpowercost;
	}

	public String getWofbContractorcost() {
		return (String) saveArray[ tableFldConstants.contractorcost.ordinal() ];
	}

	public void setWofbContractorcost(String wofbContractorcost) {
		saveArray[ tableFldConstants.contractorcost.ordinal() ] = wofbContractorcost;
	}

	public String getWofbOthercost() {
		return (String) saveArray[ tableFldConstants.othercost.ordinal() ];
	}

	public void setWofbOthercost(String wofbOthercost) {
		saveArray[ tableFldConstants.othercost.ordinal() ] = wofbOthercost;
	}

	public String getWofbObservation() {
		return (String) saveArray[ tableFldConstants.observation.ordinal() ];
	}

	public void setWofbObservation(String wofbObservation) {
		saveArray[ tableFldConstants.observation.ordinal() ] = wofbObservation;
	}

	public String getWofbFeedback() {
		return (String) saveArray[ tableFldConstants.feedback.ordinal() ];
	}

	public void setWofbFeedback(String wofbFeedback) {
		saveArray[ tableFldConstants.feedback.ordinal() ] = wofbFeedback;
	}

	public String getWofbCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setWofbCompletedby(String wofbCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = wofbCompletedby;
	}

	public String getWofbRescheduleflag() {
		return (String) saveArray[ tableFldConstants.rescheduleflag.ordinal() ];
	}

	public void setWofbRescheduleflag(String wofbRescheduleflag) {
		saveArray[ tableFldConstants.rescheduleflag.ordinal() ] = wofbRescheduleflag;
	}

	public String getWofbReschedulereason() {
		return (String) saveArray[ tableFldConstants.reschedulereason.ordinal() ];
	}

	public void setWofbReschedulereason(String wofbReschedulereason) {
		saveArray[ tableFldConstants.reschedulereason.ordinal() ] = wofbReschedulereason;
	}

	public String getWofbNextinspectiondate() {
		return (String) saveArray[ tableFldConstants.nextinspectiondate.ordinal() ];
	}

	public void setWofbNextinspectiondate(String wofbNextinspectiondate) {
		saveArray[ tableFldConstants.nextinspectiondate.ordinal() ] = wofbNextinspectiondate;
	}

	public String getWofbRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setWofbRemarks(String wofbRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = wofbRemarks;
	}

	public String getWofbRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setWofbRootcause(String wofbRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = wofbRootcause;
	}

	public String getWofbCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setWofbCountermeasure(String wofbCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = wofbCountermeasure;
	}

	public String getWofbMchcondition() {
		return (String) saveArray[ tableFldConstants.mchcondition.ordinal() ];
	}

	public void setWofbMchcondition(String wofbMchcondition) {
		saveArray[ tableFldConstants.mchcondition.ordinal() ] = wofbMchcondition;
	}
	public String getWofbMachinetakeovertime() {
		return (String) saveArray[ tableFldConstants.machinetakeovertime.ordinal() ];
	}

	public void setWofbMachinetakeovertime(String wofbMachinetakeovertime) {
		saveArray[ tableFldConstants.machinetakeovertime.ordinal() ] = wofbMachinetakeovertime;
	}

	public String getWofbCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWofbCreatedby(String wofbCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wofbCreatedby;
	}

	public String getWofbModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWofbModifiedon(String wofbModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wofbModifiedon;
	}

	public String getWofbCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWofbCreatedon(String wofbCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wofbCreatedon;
	}

	public void setSpareConsumed(BalPlmTlSpareconsumed spareConsumed) {
		this.spareConsumed = spareConsumed;
	}

	public BalPlmTlSpareconsumed getSpareConsumed() {
		return spareConsumed;
	}

	public List<BalPlmTlMultipleResp> getplmTlMultipleResp() {
		return plmTlMultipleResp;
	}

	public void setplmTlMultipleResp(List<BalPlmTlMultipleResp> pmMultiResp) {
		this.plmTlMultipleResp = pmMultiResp;
	}
	
	public void setSpareCostActual(BalPlmTlSparecostactual spareCostActual) {
		this.spareCostActual = spareCostActual;
	}

	public BalPlmTlSparecostactual getSpareCostActual() {
		return spareCostActual;
	}

	public void setWobean(List<BalWorkOrderDetailsLstBean> wobean) {
		this.wobean = wobean;
	}

	public List<BalWorkOrderDetailsLstBean> getWobean() {
		return wobean;
	}

	public String getObsvTargetDate() {
		return obsvTargetDate;
	}

	public void setObsvTargetDate(String obsvTargetDate) {
		this.obsvTargetDate = obsvTargetDate;
	}

	public String getObsvResponsibility() {
		return obsvResponsibility;
	}

	public void setObsvResponsibility(String obsvResponsibility) {
		this.obsvResponsibility = obsvResponsibility;
	}

	public String getCbmNextDueDate() {
		return cbmNextDueDate;
	}

	public void setCbmNextDueDate(String cbmNextDueDate) {
		this.cbmNextDueDate = cbmNextDueDate;
	}

	public String getCbmMinReading() {
		return cbmMinReading;
	}

	public void setCbmMinReading(String cbmMinReading) {
		this.cbmMinReading = cbmMinReading;
	}

	public String getCbmMaxReading() {
		return cbmMaxReading;
	}

	public void setCbmMaxReading(String cbmMaxReading) {
		this.cbmMaxReading = cbmMaxReading;
	}

	public void setCbmReading(String cbmReading) {
		this.cbmReading = cbmReading;
	}

	public String getCbmReading() {
		return cbmReading;
	}

	public void setCbmAdjustedReading(String cbmAdjustedReading) {
		this.cbmAdjustedReading = cbmAdjustedReading;
	}

	public String getCbmAdjustedReading() {
		return cbmAdjustedReading;
	}

	public void setPmCalendarId(String pmCalendarId) {
		this.pmCalendarId = pmCalendarId;
	}

	public String getPmCalendarId() {
		return pmCalendarId;
	}

	public void setPmstandId(String pmstandId) {
		this.pmstandId = pmstandId;
	}

	public String getPmstandId() {
		return pmstandId;
	}
	
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

	public static BalPlmTlWofeedback fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		BalPlmTlWofeedback dtl = new BalPlmTlWofeedback();
		CommonMessage.debugMsg("RAW JSON Response: :" + json);

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();

			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(field.name(), null);
			if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
			dtl.setValue(field, val);

		}
		return dtl;
	}

	public static List<BalPlmTlWofeedback> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BalPlmTlWofeedback> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			BalPlmTlWofeedback dtl = new BalPlmTlWofeedback();

			for (tableFldConstants field : tableFldConstants.values()) {
				String key = field.name();

				Object valueObj = obj.opt(key);
				String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
				
				dtl.setValue(field, val);
			}

			list.add(dtl);
		}

		return list;
	}
	
	public static String toJsonManualList(List<BalPlmTlWofeedback> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (int i = 0; i < list.size(); i++) {
			if (i > 0)
				sb.append(",");
			sb.append(list.get(i).toJsonManual());
		}

		sb.append("]");
		return sb.toString();

}

}