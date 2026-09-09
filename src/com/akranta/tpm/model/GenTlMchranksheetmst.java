package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlMchranksheetmst {

	private  Object [] saveArray = null;  
	private String rank;
	private List<GenTlMchranksheetdtl> mchRankDtl ;
	public enum   tableFldConstants
	{
		keyid, machineid, evaluationdate, totalscore, rankskill, remarks
		, evaluatedby, active, createdby, createdon, modifiedon ,rank
	}

	public GenTlMchranksheetmst()
	{
		saveArray = new  Object [ 11 ];
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public List<GenTlMchranksheetdtl> getMchRankDtl() {
		return mchRankDtl;
	}
	public void setMchRankDtl(List<GenTlMchranksheetdtl> mchRankDtl) {
		this.mchRankDtl = mchRankDtl;
	}
	public String getMrsmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMrsmKeyid(String mrsmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mrsmKeyid;
	}

	public String getMrsmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMrsmMachineid(String mrsmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = mrsmMachineid;
	}

	public String getMrsmEvaluationdate() {
		return (String) saveArray[ tableFldConstants.evaluationdate.ordinal() ];
	}

	public void setMrsmEvaluationdate(String mrsmEvaluationdate) {
		saveArray[ tableFldConstants.evaluationdate.ordinal() ] = mrsmEvaluationdate;
	}

	public String getMrsmTotalscore() {
		return (String) saveArray[ tableFldConstants.totalscore.ordinal() ];
	}

	public void setMrsmTotalscore(String mrsmTotalscore) {
		saveArray[ tableFldConstants.totalscore.ordinal() ] = mrsmTotalscore;
	}

	public String getMrsmRankskill() {
		return (String) saveArray[ tableFldConstants.rankskill.ordinal() ];
	}

	public void setMrsmRankskill(String mrsmRankskill) {
		saveArray[ tableFldConstants.rankskill.ordinal() ] = mrsmRankskill;
	}

	public String getMrsmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMrsmRemarks(String mrsmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = mrsmRemarks;
	}

	public String getMrsmEvaluatedby() {
		return (String) saveArray[ tableFldConstants.evaluatedby.ordinal() ];
	}

	public void setMrsmEvaluatedby(String mrsmEvaluatedby) {
		saveArray[ tableFldConstants.evaluatedby.ordinal() ] = mrsmEvaluatedby;
	}

	public String getMrsmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMrsmActive(String mrsmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mrsmActive;
	}

	public String getMrsmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMrsmCreatedby(String mrsmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mrsmCreatedby;
	}

	public String getMrsmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMrsmCreatedon(String mrsmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mrsmCreatedon;
	}

	public String getMrsmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMrsmModifiedon(String mrsmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mrsmModifiedon;
	}
	
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getRank() {
		return rank;
	}
}

