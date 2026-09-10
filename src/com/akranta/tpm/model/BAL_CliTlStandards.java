/*Author MANIKANDAN*/
package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BAL_CliTlStandards {
	private List <BAL_PlmTlMultiplemethodsmst> methodDetail;
	private List <BAL_PlmTlToolsdtl> toolsDetail;
	private BAL_BdmTlYycountermeasurelink countermeasureLink;	
	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, date, factoryid, sectionid, cellid, machineid, assemblyid
		, phenomenaid, causeid, jhid, tradeid, shiftid, time, effectivedate
		, formatno, issueno, issuedate, refdoctype, refdocno, departmentmgr
		, sectionmgr, groupleader, groupno, activitytype, howmuchduration
		, correctiveaction, whatactivity, wherelocation, standard, whyifnotdone
		, frequencyunit, frequency, responsibilityid, responsibilitydesgid
		, istoolsreq, howmethod, startdate, startweekno, lastdonedate
		, lastweekno, nextduedate, nextdueweekno, monthweekno, preparedbyid
		, wogenflag, lastwoid, lastwogendate, lastfeedbackid, lastfeedbackdate
		, inactivateddate,elementid,flid, active, createdby, createdon, modifiedon
	}

	public BAL_CliTlStandards()
	{
		saveArray = new  Object [ 56 ];
		methodDetail = new ArrayList<BAL_PlmTlMultiplemethodsmst>();
		toolsDetail = new ArrayList<BAL_PlmTlToolsdtl>();
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getClisKeyid() {
		return (String) saveArray[tableFldConstants.keyid.ordinal() ];
	}

	public void setClisKeyid(String cliskeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cliskeyid;
	}

	public String getClisDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setClisDate(String clisdate) {
		saveArray[ tableFldConstants.date.ordinal() ] = clisdate;
	}

	public String getClisFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setClisFactoryid(String clisfactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = clisfactoryid;
	}

	public String getClisSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setClisSectionid(String clissectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = clissectionid;
	}

	public String getClisCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setClisCellid(String cliscellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = cliscellid;
	}

	public String getClisMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setClisMachineid(String clismachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = clismachineid;
	}

	public String getClisAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setClisAssemblyid(String clisassemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = clisassemblyid;
	}

	public String getClisPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setClisPhenomenaid(String clisphenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = clisphenomenaid;
	}

	public String getClisCauseid() {
		return (String) saveArray[ tableFldConstants.causeid.ordinal() ];
	}

	public void setClisCauseid(String cliscauseid) {
		saveArray[ tableFldConstants.causeid.ordinal() ] = cliscauseid;
	}

	public String getClisJhid() {
		return (String) saveArray[ tableFldConstants.jhid.ordinal() ];
	}

	public void setClisJhid(String clisjhid) {
		saveArray[ tableFldConstants.jhid.ordinal() ] = clisjhid;
	}

	public String getClisTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setClisTradeid(String clistradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = clistradeid;
	}

	public String getClisShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setClisShiftid(String clisshiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = clisshiftid;
	}

	public String getClisTime() {
		return (String) saveArray[ tableFldConstants.time.ordinal() ];
	}

	public void setClisTime(String clistime) {
		saveArray[ tableFldConstants.time.ordinal() ] = clistime;
	}

	public String getClisEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setClisEffectivedate(String cliseffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = cliseffectivedate;
	}

	public String getClisFormatno() {
		return (String) saveArray[ tableFldConstants.formatno.ordinal() ];
	}

	public void setClisFormatno(String clisformatno) {
		saveArray[ tableFldConstants.formatno.ordinal() ] = clisformatno;
	}

	public String getClisIssueno() {
		return (String) saveArray[ tableFldConstants.issueno.ordinal() ];
	}

	public void setClisIssueno(String clisissueno) {
		saveArray[ tableFldConstants.issueno.ordinal() ] = clisissueno;
	}

	public String getClisIssuedate() {
		return (String) saveArray[ tableFldConstants.issuedate.ordinal() ];
	}

	public void setClisIssuedate(String clisissuedate) {
		saveArray[ tableFldConstants.issuedate.ordinal() ] = clisissuedate;
	}

	public String getClisRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setClisRefdoctype(String clisrefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = clisrefdoctype;
	}

	public String getClisRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setClisRefdocno(String clisrefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = clisrefdocno;
	}

	public String getClisDepartmentmgr() {
		return (String) saveArray[ tableFldConstants.departmentmgr.ordinal() ];
	}

	public void setClisDepartmentmgr(String clisdepartmentmgr) {
		saveArray[ tableFldConstants.departmentmgr.ordinal() ] = clisdepartmentmgr;
	}

	public String getClisSectionmgr() {
		return (String) saveArray[ tableFldConstants.sectionmgr.ordinal() ];
	}

	public void setClisSectionmgr(String clissectionmgr) {
		saveArray[ tableFldConstants.sectionmgr.ordinal() ] = clissectionmgr;
	}

	public String getClisGroupleader() {
		return (String) saveArray[ tableFldConstants.groupleader.ordinal() ];
	}

	public void setClisGroupleader(String clisgroupleader) {
		saveArray[ tableFldConstants.groupleader.ordinal() ] = clisgroupleader;
	}

	public String getClisGroupno() {
		return (String) saveArray[ tableFldConstants.groupno.ordinal() ];
	}

	public void setClisGroupno(String clisgroupno) {
		saveArray[ tableFldConstants.groupno.ordinal() ] = clisgroupno;
	}

	public String getClisActivitytype() {
		return (String) saveArray[ tableFldConstants.activitytype.ordinal() ];
	}

	public void setClisActivitytype(String clisactivitytype) {
		saveArray[ tableFldConstants.activitytype.ordinal() ] = clisactivitytype;
	}

	public String getClisHowmuchduration() {
		return (String) saveArray[ tableFldConstants.howmuchduration.ordinal() ];
	}

	public void setClisHowmuchduration(String clishowmuchduration) {
		saveArray[ tableFldConstants.howmuchduration.ordinal() ] = clishowmuchduration;
	}

	public String getClisCorrectiveaction() {
		return (String) saveArray[ tableFldConstants.correctiveaction.ordinal() ];
	}

	public void setClisCorrectiveaction(String cliscorrectiveaction) {
		saveArray[ tableFldConstants.correctiveaction.ordinal() ] = cliscorrectiveaction;
	}

	public String getClisWhatactivity() {
		return (String) saveArray[ tableFldConstants.whatactivity.ordinal() ];
	}

	public void setClisWhatactivity(String cliswhatactivity) {
		saveArray[ tableFldConstants.whatactivity.ordinal() ] = cliswhatactivity;
	}

	public String getClisWherelocation() {
		return (String) saveArray[ tableFldConstants.wherelocation.ordinal() ];
	}

	public void setClisWherelocation(String cliswherelocation) {
		saveArray[ tableFldConstants.wherelocation.ordinal() ] = cliswherelocation;
	}

	public String getClisStandard() {
		return (String) saveArray[ tableFldConstants.standard.ordinal() ];
	}

	public void setClisStandard(String clisstandard) {
		saveArray[ tableFldConstants.standard.ordinal() ] = clisstandard;
	}

	public String getClisWhyifnotdone() {
		return (String) saveArray[ tableFldConstants.whyifnotdone.ordinal() ];
	}

	public void setClisWhyifnotdone(String cliswhyifnotdone) {
		saveArray[ tableFldConstants.whyifnotdone.ordinal() ] = cliswhyifnotdone;
	}

	public String getClisFrequencyunit() {
		return (String) saveArray[ tableFldConstants.frequencyunit.ordinal() ];
	}

	public void setClisFrequencyunit(String clisfrequencyunit) {
		saveArray[ tableFldConstants.frequencyunit.ordinal() ] = clisfrequencyunit;
	}

	public String getClisFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setClisFrequency(String clisfrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] =clisfrequency;
	}

	public String getClisResponsibilityid() {
		return (String) saveArray[ tableFldConstants.responsibilityid.ordinal() ];
	}

	public void setClisResponsibilityid(String clisresponsibilityid) {
		saveArray[ tableFldConstants.responsibilityid.ordinal() ] = clisresponsibilityid;
	}

	public String getClisResponsibilitydesgid() {
		return (String) saveArray[ tableFldConstants.responsibilitydesgid.ordinal() ];
	}

	public void setClisResponsibilitydesgid(String clisresponsibilitydesgid) {
		saveArray[ tableFldConstants.responsibilitydesgid.ordinal() ] = clisresponsibilitydesgid;
	}

	public String getClisIstoolsreq() {
		return (String) saveArray[ tableFldConstants.istoolsreq.ordinal() ];
	}

	public void setClisIstoolsreq(String clisistoolsreq) {
		saveArray[ tableFldConstants.istoolsreq.ordinal() ] = clisistoolsreq;
	}

	public String getClisHowmethod() {
		return (String) saveArray[ tableFldConstants.howmethod.ordinal() ];
	}

	public void setClisHowmethod(String clishowmethod) {
		saveArray[ tableFldConstants.howmethod.ordinal() ] = clishowmethod;
	}

	public String getClisStartdate() {
		return (String) saveArray[ tableFldConstants.startdate.ordinal() ];
	}

	public void setClisStartdate(String clisstartdate) {
		saveArray[ tableFldConstants.startdate.ordinal() ] = clisstartdate;
	}

	public String getClisStartweekno() {
		return (String) saveArray[ tableFldConstants.startweekno.ordinal() ];
	}

	public void setClisStartweekno(String clisstartweekno) {
		saveArray[ tableFldConstants.startweekno.ordinal() ] = clisstartweekno;
	}

	public String getClisLastdonedate() {
		return (String) saveArray[ tableFldConstants.lastdonedate.ordinal() ];
	}

	public void setClisLastdonedate(String clislastdonedate) {
		saveArray[ tableFldConstants.lastdonedate.ordinal() ] = clislastdonedate;
	}

	public String getClisLastweekno() {
		return (String) saveArray[ tableFldConstants.lastweekno.ordinal() ];
	}

	public void setClisLastweekno(String clislastweekno) {
		saveArray[ tableFldConstants.lastweekno.ordinal() ] = clislastweekno;
	}

	public String getClisNextduedate() {
		return (String) saveArray[ tableFldConstants.nextduedate.ordinal() ];
	}

	public void setClisNextduedate(String clisnextduedate) {
		saveArray[ tableFldConstants.nextduedate.ordinal() ] = clisnextduedate;
	}

	public String getClisNextdueweekno() {
		return (String) saveArray[ tableFldConstants.nextdueweekno.ordinal() ];
	}

	public void setClisNextdueweekno(String clisnextdueweekno) {
		saveArray[ tableFldConstants.nextdueweekno.ordinal() ] = clisnextdueweekno;
	}

	public String getClisMonthweekno() {
		return (String) saveArray[ tableFldConstants.monthweekno.ordinal() ];
	}

	public void setClisMonthweekno(String clismonthweekno) {
		saveArray[ tableFldConstants.monthweekno.ordinal() ] = clismonthweekno;
	}

	public String getClisPreparedbyid() {
		return (String) saveArray[ tableFldConstants.preparedbyid.ordinal() ];
	}

	public void setClisPreparedbyid(String clispreparedbyid) {
		saveArray[ tableFldConstants.preparedbyid.ordinal() ] = clispreparedbyid;
	}

	public String getClisWogenflag() {
		return (String) saveArray[ tableFldConstants.wogenflag.ordinal() ];
	}

	public void setClisWogenflag(String cliswogenflag) {
		saveArray[ tableFldConstants.wogenflag.ordinal() ] = cliswogenflag;
	}

	public String getClisLastwoid() {
		return (String) saveArray[ tableFldConstants.lastwoid.ordinal() ];
	}

	public void setClisLastwoid(String clislastwoid) {
		saveArray[ tableFldConstants.lastwoid.ordinal() ] = clislastwoid;
	}

	public String getClisLastwogendate() {
		return (String) saveArray[ tableFldConstants.lastwogendate.ordinal() ];
	}

	public void setClisLastwogendate(String clislastwogendate) {
		saveArray[ tableFldConstants.lastwogendate.ordinal() ] = clislastwogendate;
	}

	public String getClisLastfeedbackid() {
		return (String) saveArray[ tableFldConstants.lastfeedbackid.ordinal() ];
	}

	public void setClisLastfeedbackid(String clislastfeedbackid) {
		saveArray[ tableFldConstants.lastfeedbackid.ordinal() ] = clislastfeedbackid;
	}

	public String getClisLastfeedbackdate() {
		return (String) saveArray[ tableFldConstants.lastfeedbackdate.ordinal() ];
	}

	public void setClisLastfeedbackdate(String clislastfeedbackdate) {
		saveArray[ tableFldConstants.lastfeedbackdate.ordinal() ] = clislastfeedbackdate;
	}

	public String getClisInactivateddate() {
		return (String) saveArray[ tableFldConstants.inactivateddate.ordinal() ];
	}

	public void setClisInactivateddate(String clisinactivateddate) {
		saveArray[ tableFldConstants.inactivateddate.ordinal() ] = clisinactivateddate;
	}
	public String getClisFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setClisFlid(String clisflid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = clisflid;
	}
	public String getClisElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setClisElementid(String cliselementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = cliselementid;
	}

	public String getClisActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setClisActive(String clisactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = clisactive;
	}

	public String getClisCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setClisCreatedby(String cliscreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cliscreatedby;
	}

	public String getClisCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setClisCreatedon(String cliscreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cliscreatedon;
	}

	public String getClisModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setClisModifiedon(String clismodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = clismodifiedon;
	}

	public void setMethodDetail(List <BAL_PlmTlMultiplemethodsmst> methodDetail) {
		this.methodDetail = methodDetail;
	}

	public List <BAL_PlmTlMultiplemethodsmst> getMethodDetail() {
		return methodDetail;
	}
/*for tools pick up*/
	public void setToolsDetail(List <BAL_PlmTlToolsdtl> toolsDetail) {
		this.toolsDetail = toolsDetail;
	}

	public List <BAL_PlmTlToolsdtl> getToolsDetail() {
		System.out.println("inside getToolDetail");
		return this.toolsDetail ;
	}

	public void setCountermeasureLink(BAL_BdmTlYycountermeasurelink countermeasureLink) {
		this.countermeasureLink = countermeasureLink;
	}

	public BAL_BdmTlYycountermeasurelink getCountermeasureLink() {
		return countermeasureLink;
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
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
				if ("keyid".equals(field.name()) && (val == null || val.toString().trim().isEmpty())) {
					sb.append("null");
				} else if (val == null || val.toString().trim().isEmpty()) {
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

	/**
	 * Deserialize a single row from JSON back into a bean.
	 */
	public static BAL_CliTlStandards fromJson(String json) {
		CommonMessage.debugMsg("RAW CliTlStandards JSON: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);
		BAL_CliTlStandards bean = new BAL_CliTlStandards();

		for (tableFldConstants field : tableFldConstants.values()) {
			String key = field.name();
			CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
			String val = obj.optString(key, null);
			if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
				val = "";
			}
			bean.setValue(field, val);
		}
		return bean;
	}

	/**
	 * Serialize a whole list of rows to a JSON array, e.g. for the
	 * "details" array in the multi-row CLTI save payload.
	 */
	public static String toJsonManualList(List<BAL_CliTlStandards> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (i > 0) sb.append(",");
				sb.append(list.get(i).toJsonManual());
			}
		}

		sb.append("]");
		return sb.toString();
	}

	/**
	 * Deserialize a JSON array of rows back into beans.
	 */
	public static List<BAL_CliTlStandards> fromJsonList(String json) {
		List<BAL_CliTlStandards> list = new ArrayList<>();
		if (json == null || json.trim().isEmpty()) {
			return list;
		}
		JSONArray arr = JSONArray.fromObject(json);
		for (int i = 0; i < arr.length(); i++) {
			JSONObject rowObj = arr.getJSONObject(i);
			list.add(fromJson(rowObj.toString()));
		}
		return list;
	}
}

