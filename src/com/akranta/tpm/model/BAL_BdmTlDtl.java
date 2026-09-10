package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_BdmTlDtl {

	private  Object [] saveArray = null;  
	
	private String chkCompletedBy;
	private String chkFailure;
	
	public enum   tableFldConstants
	{
		keyid, bdms_keyid, finalphenomena, finalcause, finalaction, tradeid
		, countermeasure, wwrequired, wwno, rootcause, preventivemeasure
		, rootcauseid, countermeasureid, preventivemeasureid, breakdowntime
		, worktime, classificationid, categoryid, issparesreplaced, alarmno
		, manpowercost, contractorcost, sparescost, othercost, status
		, remarks, actiontakenby, completedby, costcentre, erppoststatus
		, problemseverity, failuretype, isapproved, approverdby, erpnumber
		, otherfailuretype, active, createdby, createdon, modifiedon
	}

	public BAL_BdmTlDtl()
	{
		saveArray = new  Object [ 40 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getBdanKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBdanKeyid(String bdankeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bdankeyid;
	}

	public String getBdanBdms_keyid() {
		return (String) saveArray[ tableFldConstants.bdms_keyid.ordinal() ];
	}

	public void setBdanBdms_keyid(String bdanbdms_keyid) {
		saveArray[ tableFldConstants.bdms_keyid.ordinal() ] = bdanbdms_keyid;
	}

	public String getBdanFinalphenomena() {
		return (String) saveArray[ tableFldConstants.finalphenomena.ordinal() ];
	}

	public void setBdanFinalphenomena(String bdanfinalphenomena) {
		saveArray[ tableFldConstants.finalphenomena.ordinal() ] = bdanfinalphenomena;
	}

	public String getBdanFinalcause() {
		return (String) saveArray[ tableFldConstants.finalcause.ordinal() ];
	}

	public void setBdanFinalcause(String bdanfinalcause) {
		saveArray[ tableFldConstants.finalcause.ordinal() ] = bdanfinalcause;
	}

	public String getBdanFinalaction() {
		return (String) saveArray[ tableFldConstants.finalaction.ordinal() ];
	}

	public void setBdanFinalaction(String bdanfinalaction) {
		saveArray[ tableFldConstants.finalaction.ordinal() ] = bdanfinalaction;
	}

	public String getBdanTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setBdanTradeid(String bdantradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = bdantradeid;
	}

	public String getBdanCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setBdanCountermeasure(String bdancountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = bdancountermeasure;
	}

	public String getBdanWwrequired() {
		return (String) saveArray[ tableFldConstants.wwrequired.ordinal() ];
	}

	public void setBdanWwrequired(String bdanwwrequired) {
		saveArray[ tableFldConstants.wwrequired.ordinal() ] = bdanwwrequired;
	}

	public String getBdanWwno() {
		return (String) saveArray[ tableFldConstants.wwno.ordinal() ];
	}

	public void setBdanWwno(String bdanwwno) {
		saveArray[ tableFldConstants.wwno.ordinal() ] = bdanwwno;
	}

	public String getBdanRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setBdanRootcause(String bdanrootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = bdanrootcause;
	}

	public String getBdanPreventivemeasure() {
		return (String) saveArray[ tableFldConstants.preventivemeasure.ordinal() ];
	}

	public void setBdanPreventivemeasure(String bdanpreventivemeasure) {
		saveArray[ tableFldConstants.preventivemeasure.ordinal() ] = bdanpreventivemeasure;
	}

	public String getBdanRootcauseid() {
		return (String) saveArray[ tableFldConstants.rootcauseid.ordinal() ];
	}

	public void setBdanRootcauseid(String bdanrootcauseid) {
		saveArray[ tableFldConstants.rootcauseid.ordinal() ] = bdanrootcauseid;
	}

	public String getBdanCountermeasureid() {
		return (String) saveArray[ tableFldConstants.countermeasureid.ordinal() ];
	}

	public void setBdanCountermeasureid(String bdancountermeasureid) {
		saveArray[ tableFldConstants.countermeasureid.ordinal() ] = bdancountermeasureid;
	}

	public String getBdanPreventivemeasureid() {
		return (String) saveArray[ tableFldConstants.preventivemeasureid.ordinal() ];
	}

	public void setBdanPreventivemeasureid(String bdanpreventivemeasureid) {
		saveArray[ tableFldConstants.preventivemeasureid.ordinal() ] = bdanpreventivemeasureid;
	}

	public String getBdanBreakdowntime() {
		return (String) saveArray[ tableFldConstants.breakdowntime.ordinal() ];
	}

	public void setBdanBreakdowntime(String bdanbreakdowntime) {
		saveArray[ tableFldConstants.breakdowntime.ordinal() ] = bdanbreakdowntime;
	}

	public String getBdanWorktime() {
		return (String) saveArray[ tableFldConstants.worktime.ordinal() ];
	}

	public void setBdanWorktime(String bdanworktime) {
		saveArray[ tableFldConstants.worktime.ordinal() ] = bdanworktime;
	}

	public String getBdanClassificationid() {
		return (String) saveArray[ tableFldConstants.classificationid.ordinal() ];
	}

	public void setBdanClassificationid(String bdanclassificationid) {
		saveArray[ tableFldConstants.classificationid.ordinal() ] = bdanclassificationid;
	}

	public String getBdanCategoryid() {
		return (String) saveArray[ tableFldConstants.categoryid.ordinal() ];
	}

	public void setBdanCategoryid(String bdancategoryid) {
		saveArray[ tableFldConstants.categoryid.ordinal() ] = bdancategoryid;
	}

	public String getBdanIssparesreplaced() {
		return (String) saveArray[ tableFldConstants.issparesreplaced.ordinal() ];
	}

	public void setBdanIssparesreplaced(String bdanissparesreplaced) {
		saveArray[ tableFldConstants.issparesreplaced.ordinal() ] = bdanissparesreplaced;
	}

	public String getBdanAlarmno() {
		return (String) saveArray[ tableFldConstants.alarmno.ordinal() ];
	}

	public void setBdanAlarmno(String bdanalarmno) {
		saveArray[ tableFldConstants.alarmno.ordinal() ] = bdanalarmno;
	}

	public String getBdanManpowercost() {
		return (String) saveArray[ tableFldConstants.manpowercost.ordinal() ];
	}

	public void setBdanManpowercost(String bdanmanpowercost) {
		saveArray[ tableFldConstants.manpowercost.ordinal() ] = bdanmanpowercost;
	}

	public String getBdanContractorcost() {
		return (String) saveArray[ tableFldConstants.contractorcost.ordinal() ];
	}

	public void setBdanContractorcost(String bdancontractorcost) {
		saveArray[ tableFldConstants.contractorcost.ordinal() ] = bdancontractorcost;
	}

	public String getBdanSparescost() {
		return (String) saveArray[ tableFldConstants.sparescost.ordinal() ];
	}

	public void setBdanSparescost(String bdansparescost) {
		saveArray[ tableFldConstants.sparescost.ordinal() ] = bdansparescost;
	}

	public String getBdanOthercost() {
		return (String) saveArray[ tableFldConstants.othercost.ordinal() ];
	}

	public void setBdanOthercost(String bdanothercost) {
		saveArray[ tableFldConstants.othercost.ordinal() ] = bdanothercost;
	}

	public String getBdanStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setBdanStatus(String bdanstatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = bdanstatus;
	}

	public String getBdanRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setBdanRemarks(String bdanremarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = bdanremarks;
	}

	public String getBdanActiontakenby() {
		return (String) saveArray[ tableFldConstants.actiontakenby.ordinal() ];
	}

	public void setBdanActiontakenby(String bdanactiontakenby) {
		saveArray[ tableFldConstants.actiontakenby.ordinal() ] = bdanactiontakenby;
	}

	public String getBdanCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setBdanCompletedby(String bdancompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = bdancompletedby;
	}

	public String getBdanCostcentre() {
		return (String) saveArray[ tableFldConstants.costcentre.ordinal() ];
	}

	public void setBdanCostcentre(String bdancostcentre) {
		saveArray[ tableFldConstants.costcentre.ordinal() ] = bdancostcentre;
	}

	public String getBdanErppoststatus() {
		return (String) saveArray[ tableFldConstants.erppoststatus.ordinal() ];
	}

	public void setBdanErppoststatus(String bdanerppoststatus) {
		saveArray[ tableFldConstants.erppoststatus.ordinal() ] = bdanerppoststatus;
	}

	public String getBdanProblemseverity() {
		return (String) saveArray[ tableFldConstants.problemseverity.ordinal() ];
	}

	public void setBdanProblemseverity(String bdanproblemseverity) {
		saveArray[ tableFldConstants.problemseverity.ordinal() ] = bdanproblemseverity;
	}

	public String getBdanFailuretype() {
		return (String) saveArray[ tableFldConstants.failuretype.ordinal() ];
	}

	public void setBdanFailuretype(String bdanfailuretype) {
		saveArray[ tableFldConstants.failuretype.ordinal() ] = bdanfailuretype;
	}
	
	public String getBdanIsapproved() {
		return (String) saveArray[ tableFldConstants.isapproved.ordinal() ];
	}

	public void setBdanIsapproved(String bdanisapproved) {
		saveArray[ tableFldConstants.isapproved.ordinal() ] = bdanisapproved;
	}

	public String getBdanApproverdby() {
		return (String) saveArray[ tableFldConstants.approverdby.ordinal() ];
	}

	public void setBdanApproverdby(String bdanapproverdby) {
		saveArray[ tableFldConstants.approverdby.ordinal() ] = bdanapproverdby;
	}

	public String getBdanErpnumber() {
		return (String) saveArray[ tableFldConstants.erpnumber.ordinal() ];
	}

	public void setBdanErpnumber(String bdanErpnumber) {
		saveArray[ tableFldConstants.erpnumber.ordinal() ] = bdanErpnumber;
	}
	
	public String getBdanOtherFailuretype() {
		return (String) saveArray[ tableFldConstants.otherfailuretype.ordinal() ];
	}

	public void setBdanOtherFailuretype(String bdanotherfailuretype) {
		saveArray[ tableFldConstants.otherfailuretype.ordinal() ] = bdanotherfailuretype;
	}
	
	public String getBdanActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBdanActive(String bdanactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bdanactive;
	}

	public String getBdanCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBdanCreatedby(String bdancreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bdancreatedby;
	}

	public String getBdanCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBdanCreatedon(String bdancreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bdancreatedon;
	}

	public String getBdanModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBdanModifiedon(String bdanmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bdanmodifiedon;
	}

	public void setChkCompletedBy(String chkCompletedBy) {
		this.chkCompletedBy = chkCompletedBy;
	}

	public String getChkCompletedBy() {
		return chkCompletedBy;
	}
	public void setchkFailure(String bdanfailure) {
		this.chkFailure = bdanfailure;
	}

	public String getchkFailure() {
		return chkFailure;
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
                if (field == tableFldConstants.keyid && val == null) {
                    sb.append("null");
                } else if (val == null) {
                    sb.append("\"{}\"");
                } else {
                    sb.append("\"")
                      .append(val.toString().replace("\\", "\\\\")
                                            .replace("\"", "\\\""))
                      .append("\"");
                }
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Deserialises a JSON string (Spring response "detail" object) back into a
     * BAL_BdmTlDtl instance.
     */
    public static BAL_BdmTlDtl fromJson(String json) {
        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(json);
        BAL_BdmTlDtl dtl = new BAL_BdmTlDtl();
        for (tableFldConstants field : tableFldConstants.values()) {
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            dtl.saveArray[field.ordinal()] = val;
        }
        return dtl;
    }
}

