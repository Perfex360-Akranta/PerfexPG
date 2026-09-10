package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMomdtl.tableFldConstants;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BalPlmTlCalendar {

	private  Object [] saveArray = null;  
	
	private List<BalWoObservation> woObservations ;
	
	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, assemblyid, subassemblyid
		, entrytype, pmfreq, pmrefid, jobtype, pmsource, tradeid, workorderid
		, whatactivity, calendaryear, monthweek, fromdate, tilldate, maxcompletiondate
		, scheduledfrom, scheduledtill, scheduledweek, allottedto, status
		, completedby, feedbackid, feedbackduedate, feedbackdate, effplancompdate
		, worksummaryid, duration, remarks, machinecond, downtime, starttime
		, endtime, responsibility, frequencyvalue, issparereq, istoolsreq
		, relatedto, mouldid, locationid, flid, tempfield5,elementid, active
		, createdby, createdon, modifiedon
	}

	public BalPlmTlCalendar()
	{
		saveArray = new  Object [ 51 ];
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
	public String getPmclKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPmclKeyid(String pmclKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pmclKeyid;
	}

	public String getPmclFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setPmclFactoryid(String pmclFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = pmclFactoryid;
	}

	public String getPmclSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setPmclSectionid(String pmclSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = pmclSectionid;
	}

	public String getPmclCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setPmclCellid(String pmclCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = pmclCellid;
	}

	public String getPmclMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setPmclMachineid(String pmclMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = pmclMachineid;
	}

	public String getPmclAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setPmclAssemblyid(String pmclAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = pmclAssemblyid;
	}

	public String getPmclSubassemblyid() {
		return (String) saveArray[ tableFldConstants.subassemblyid.ordinal() ];
	}

	public void setPmclSubassemblyid(String pmclSubassemblyid) {
		saveArray[ tableFldConstants.subassemblyid.ordinal() ] = pmclSubassemblyid;
	}

	public String getPmclEntrytype() {
		return (String) saveArray[ tableFldConstants.entrytype.ordinal() ];
	}

	public void setPmclEntrytype(String pmclEntrytype) {
		saveArray[ tableFldConstants.entrytype.ordinal() ] = pmclEntrytype;
	}

	public String getPmclPmfreq() {
		return (String) saveArray[ tableFldConstants.pmfreq.ordinal() ];
	}

	public void setPmclPmfreq(String pmclPmfreq) {
		saveArray[ tableFldConstants.pmfreq.ordinal() ] = pmclPmfreq;
	}

	public String getPmclPmrefid() {
		return (String) saveArray[ tableFldConstants.pmrefid.ordinal() ];
	}

	public void setPmclPmrefid(String pmclPmrefid) {
		saveArray[ tableFldConstants.pmrefid.ordinal() ] = pmclPmrefid;
	}

	public String getPmclJobtype() {
		return (String) saveArray[ tableFldConstants.jobtype.ordinal() ];
	}

	public void setPmclJobtype(String pmclJobtype) {
		saveArray[ tableFldConstants.jobtype.ordinal() ] = pmclJobtype;
	}

	public String getPmclPmsource() {
		return (String) saveArray[ tableFldConstants.pmsource.ordinal() ];
	}

	public void setPmclPmsource(String pmclPmsource) {
		saveArray[ tableFldConstants.pmsource.ordinal() ] = pmclPmsource;
	}

	public String getPmclTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setPmclTradeid(String pmclTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = pmclTradeid;
	}

	public String getPmclWorkorderid() {
		return (String) saveArray[ tableFldConstants.workorderid.ordinal() ];
	}

	public void setPmclWorkorderid(String pmclWorkorderid) {
		saveArray[ tableFldConstants.workorderid.ordinal() ] = pmclWorkorderid;
	}

	public String getPmclWhatactivity() {
		return (String) saveArray[ tableFldConstants.whatactivity.ordinal() ];
	}

	public void setPmclWhatactivity(String pmclWhatactivity) {
		saveArray[ tableFldConstants.whatactivity.ordinal() ] = pmclWhatactivity;
	}

	public String getPmclCalendaryear() {
		return (String) saveArray[ tableFldConstants.calendaryear.ordinal() ];
	}

	public void setPmclCalendaryear(String pmclCalendaryear) {
		saveArray[ tableFldConstants.calendaryear.ordinal() ] = pmclCalendaryear;
	}

	public String getPmclMonthweek() {
		return (String) saveArray[ tableFldConstants.monthweek.ordinal() ];
	}

	public void setPmclMonthweek(String pmclMonthweek) {
		saveArray[ tableFldConstants.monthweek.ordinal() ] = pmclMonthweek;
	}

	public String getPmclFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setPmclFromdate(String pmclFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = pmclFromdate;
	}

	public String getPmclTilldate() {
		return (String) saveArray[ tableFldConstants.tilldate.ordinal() ];
	}

	public void setPmclTilldate(String pmclTilldate) {
		saveArray[ tableFldConstants.tilldate.ordinal() ] = pmclTilldate;
	}

	public String getPmclMaxcompletiondate() {
		return (String) saveArray[ tableFldConstants.maxcompletiondate.ordinal() ];
	}

	public void setPmclMaxcompletiondate(String pmclMaxcompletiondate) {
		saveArray[ tableFldConstants.maxcompletiondate.ordinal() ] = pmclMaxcompletiondate;
	}

	public String getPmclScheduledfrom() {
		return (String) saveArray[ tableFldConstants.scheduledfrom.ordinal() ];
	}

	public void setPmclScheduledfrom(String pmclScheduledfrom) {
		saveArray[ tableFldConstants.scheduledfrom.ordinal() ] = pmclScheduledfrom;
	}

	public String getPmclScheduledtill() {
		return (String) saveArray[ tableFldConstants.scheduledtill.ordinal() ];
	}

	public void setPmclScheduledtill(String pmclScheduledtill) {
		saveArray[ tableFldConstants.scheduledtill.ordinal() ] = pmclScheduledtill;
	}

	public String getPmclScheduledweek() {
		return (String) saveArray[ tableFldConstants.scheduledweek.ordinal() ];
	}

	public void setPmclScheduledweek(String pmclScheduledweek) {
		saveArray[ tableFldConstants.scheduledweek.ordinal() ] = pmclScheduledweek;
	}

	public String getPmclAllottedto() {
		return (String) saveArray[ tableFldConstants.allottedto.ordinal() ];
	}

	public void setPmclAllottedto(String pmclAllottedto) {
		saveArray[ tableFldConstants.allottedto.ordinal() ] = pmclAllottedto;
	}

	public String getPmclStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setPmclStatus(String pmclStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = pmclStatus;
	}

	public String getPmclCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setPmclCompletedby(String pmclCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = pmclCompletedby;
	}

	public String getPmclFeedbackid() {
		return (String) saveArray[ tableFldConstants.feedbackid.ordinal() ];
	}

	public void setPmclFeedbackid(String pmclFeedbackid) {
		saveArray[ tableFldConstants.feedbackid.ordinal() ] = pmclFeedbackid;
	}

	public String getPmclFeedbackduedate() {
		return (String) saveArray[ tableFldConstants.feedbackduedate.ordinal() ];
	}

	public void setPmclFeedbackduedate(String pmclFeedbackduedate) {
		saveArray[ tableFldConstants.feedbackduedate.ordinal() ] = pmclFeedbackduedate;
	}

	public String getPmclFeedbackdate() {
		return (String) saveArray[ tableFldConstants.feedbackdate.ordinal() ];
	}

	public void setPmclFeedbackdate(String pmclFeedbackdate) {
		saveArray[ tableFldConstants.feedbackdate.ordinal() ] = pmclFeedbackdate;
	}

	public String getPmclEffplancompdate() {
		return (String) saveArray[ tableFldConstants.effplancompdate.ordinal() ];
	}

	public void setPmclEffplancompdate(String pmclEffplancompdate) {
		saveArray[ tableFldConstants.effplancompdate.ordinal() ] = pmclEffplancompdate;
	}

	public String getPmclWorksummaryid() {
		return (String) saveArray[ tableFldConstants.worksummaryid.ordinal() ];
	}

	public void setPmclWorksummaryid(String pmclWorksummaryid) {
		saveArray[ tableFldConstants.worksummaryid.ordinal() ] = pmclWorksummaryid;
	}

	public String getPmclDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setPmclDuration(String pmclDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = pmclDuration;
	}

	public String getPmclRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setPmclRemarks(String pmclRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = pmclRemarks;
	}

	public String getPmclMachinecond() {
		return (String) saveArray[ tableFldConstants.machinecond.ordinal() ];
	}

	public void setPmclMachinecond(String pmclMachinecond) {
		saveArray[ tableFldConstants.machinecond.ordinal() ] = pmclMachinecond;
	}

	public String getPmclDowntime() {
		return (String) saveArray[ tableFldConstants.downtime.ordinal() ];
	}

	public void setPmclDowntime(String pmclDowntime) {
		saveArray[ tableFldConstants.downtime.ordinal() ] = pmclDowntime;
	}

	public String getPmclStarttime() {
		return (String) saveArray[ tableFldConstants.starttime.ordinal() ];
	}

	public void setPmclStarttime(String pmclStarttime) {
		saveArray[ tableFldConstants.starttime.ordinal() ] = pmclStarttime;
	}

	public String getPmclEndtime() {
		return (String) saveArray[ tableFldConstants.endtime.ordinal() ];
	}

	public void setPmclEndtime(String pmclEndtime) {
		saveArray[ tableFldConstants.endtime.ordinal() ] = pmclEndtime;
	}

	public String getPmclResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setPmclResponsibility(String pmclResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = pmclResponsibility;
	}

	public String getPmclFrequencyvalue() {
		return (String) saveArray[ tableFldConstants.frequencyvalue.ordinal() ];
	}

	public void setPmclFrequencyvalue(String pmclFrequencyvalue) {
		saveArray[ tableFldConstants.frequencyvalue.ordinal() ] = pmclFrequencyvalue;
	}

	public String getPmclIssparereq() {
		return (String) saveArray[ tableFldConstants.issparereq.ordinal() ];
	}

	public void setPmclIssparereq(String pmclIssparereq) {
		saveArray[ tableFldConstants.issparereq.ordinal() ] = pmclIssparereq;
	}

	public String getPmclIstoolsreq() {
		return (String) saveArray[ tableFldConstants.istoolsreq.ordinal() ];
	}

	public void setPmclIstoolsreq(String pmclIstoolsreq) {
		saveArray[ tableFldConstants.istoolsreq.ordinal() ] = pmclIstoolsreq;
	}

	public String getPmclRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setPmclRelatedto(String pmclRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = pmclRelatedto;
	}

	public String getPmclMouldid() {
		return (String) saveArray[ tableFldConstants.mouldid.ordinal() ];
	}

	public void setPmclMouldid(String pmclMouldid) {
		saveArray[ tableFldConstants.mouldid.ordinal() ] = pmclMouldid;
	}

	public String getPmclLocationid() {
		return (String) saveArray[ tableFldConstants.locationid.ordinal() ];
	}

	public void setPmclLocationid(String locationid) {
		saveArray[ tableFldConstants.locationid.ordinal() ] = locationid;
	}

	public String getPmclFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPmclFlid(String flid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = flid;
	}

	public String getPmclTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPmclTempfield5(String pmclTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = pmclTempfield5;
	}

	public String getPmclElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setPmclElementid(String elementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = elementid;
	}

	
	public String getPmclActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPmclActive(String pmclActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pmclActive;
	}

	public String getPmclCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPmclCreatedby(String pmclCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pmclCreatedby;
	}

	public String getPmclCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPmclCreatedon(String pmclCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pmclCreatedon;
	}

	public String getPmclModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPmclModifiedon(String pmclModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pmclModifiedon;
	}

	public List<BalWoObservation> getWoObservations() {
		return woObservations;
	}

	public void setWoObservations(List<BalWoObservation> woObservations) {
		this.woObservations = woObservations;
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

	public static BalPlmTlCalendar fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		BalPlmTlCalendar dtl = new BalPlmTlCalendar();
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

	public static List<BalPlmTlCalendar> fromJsonList(String json) {

		CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);

		JSONArray jsonArray = JSONArray.fromObject(json);
		List<BalPlmTlCalendar> list = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			BalPlmTlCalendar dtl = new BalPlmTlCalendar();

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
	
	public static String toJsonManualList(List<BalPlmTlCalendar> list) {
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

