package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTtgCalEmpatScore{

	private  Object [] saveArray = null;  
	private List<EntTlTtgCalEmpatScore> methodEntTlTtgCalEmpatScore;
    private String traKeyid;
	public enum   tableFldConstants
	{
		keyid,etcm_keyid,etce_empm_keyid,etce_keyid, assessmentcom,maxmarks,cutoff,type,dept
		,presentabsent,attdate ,score ,result,remarks,scoredate,filemgnid,tempfield1, tempfield2,
		 tempfield3,tempfield4,tempfield5, createdby,active,createdon, modifiedon
	}

	public EntTlTtgCalEmpatScore()
	{
		saveArray = new  Object [ 25 ];
		methodEntTlTtgCalEmpatScore=new ArrayList<EntTlTtgCalEmpatScore>();
	}
	public List<EntTlTtgCalEmpatScore> getmethodEntTlTtgCalEmpatScore() 
	{
		return methodEntTlTtgCalEmpatScore;
	}
	public void setmethodEntTlTtgCalEmpatScore(List <EntTlTtgCalEmpatScore> methodEntTlTtgCalEmpatScore) {
		this.methodEntTlTtgCalEmpatScore=methodEntTlTtgCalEmpatScore;
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getEtcaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEtcaKeyid(String etcaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = etcaKeyid;
	}

	public String getEtcaEtcmKeyid() {
		return (String) saveArray[ tableFldConstants.etcm_keyid.ordinal() ];
	}

	public void setEtcaEtcmKeyid(String etcaetcmKeyid) {
		saveArray[ tableFldConstants.etcm_keyid.ordinal() ] = etcaetcmKeyid;
	}

	public String getEtcaEtceEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.etce_empm_keyid.ordinal() ];
	}

	public void setEtcaEtceEmpmKeyid(String etcaEtceEmpmKeyid) {
		saveArray[ tableFldConstants.etce_empm_keyid.ordinal() ] = etcaEtceEmpmKeyid;
	}
	
	public String getEtcaEtceKeyid() {
		return (String) saveArray[ tableFldConstants.etce_keyid.ordinal() ];
	}

	public void setEtcaEtceKeyid(String etcaEtceKeyid) {
		saveArray[ tableFldConstants.etce_keyid.ordinal() ] = etcaEtceKeyid;
	}

	public String getEtcaAssessmentCom() {
		return (String) saveArray[ tableFldConstants.assessmentcom.ordinal() ];
	}

	public void setEtcaAssessmentCom(String etcaAssessmentCom) {
		saveArray[ tableFldConstants.assessmentcom.ordinal() ] = etcaAssessmentCom;
	}
	
	public String getEtcaMaxMarks() {
		return (String) saveArray[ tableFldConstants.maxmarks.ordinal() ];
	}

	public void setEtcaMaxMarks(String etcaMaxMarks) {
		saveArray[ tableFldConstants.maxmarks.ordinal() ] = etcaMaxMarks;
	}
	public String getEtcaCutOff() {
		return (String) saveArray[ tableFldConstants.cutoff.ordinal() ];
	}

	public void setEtcaCutOff(String etcaCutOff) {
		saveArray[ tableFldConstants.cutoff.ordinal() ] = etcaCutOff;
	}
	public String getEtcaType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setEtcaType(String etcaTye) {
		saveArray[ tableFldConstants.type.ordinal() ] = etcaTye;
	}
	public String getEtcaDept() {
		return (String) saveArray[ tableFldConstants.dept.ordinal() ];
	}

	public void setEtcaDept(String etcaDept) {
		saveArray[ tableFldConstants.dept.ordinal() ] = etcaDept;
	}
	public String getEtcaPresentAbsent() {
		return (String) saveArray[ tableFldConstants.presentabsent.ordinal() ];
	}

	public void setEtcaPresentAbsent(String etcaPresentAbsent) {
		saveArray[ tableFldConstants.presentabsent.ordinal() ] = etcaPresentAbsent;
	}
	public String getEtcaAddDate() {
		return (String) saveArray[ tableFldConstants.attdate.ordinal() ];
	}

	public void setEtcaAddDate(String etcaAddDate) {
		saveArray[ tableFldConstants.attdate.ordinal() ] = etcaAddDate;
	}
	public String getEtcaScore() {
		return (String) saveArray[ tableFldConstants.score.ordinal() ];
	}

	public void setEtcaScore(String etcaScore) {
		saveArray[ tableFldConstants.score.ordinal() ] = etcaScore;
	}
	
	public String getEtcaResult() {
		return (String) saveArray[ tableFldConstants.result.ordinal() ];
	}

	public void setEtcaResult(String etcaResult) {
		saveArray[ tableFldConstants.result.ordinal() ] = etcaResult;
	}
	
	public String getEtcaRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setEtcaRemarks(String etcaRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = etcaRemarks;
	}
	public String getEtcaScoreDate() {
		return (String) saveArray[ tableFldConstants.scoredate.ordinal() ];
	}

	public void setEtcaScoreDate(String etcaScoreDate) {
		saveArray[ tableFldConstants.scoredate.ordinal() ] = etcaScoreDate;
	}
	public String getEtcaFileMgnId() {
		return (String) saveArray[ tableFldConstants.filemgnid.ordinal() ];
	}

	public void setEtcaFileMgnId(String etcaFileMgnId) {
		saveArray[ tableFldConstants.filemgnid.ordinal() ] = etcaFileMgnId;
	}
	public String getEtcaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEtcaTempfield1(String etcaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = etcaTempfield1;
	}

	public String getEtcaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEtcaTempfield2(String etcaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = etcaTempfield2;
	}
	
	
	public String getEtcaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEtcaTempfield3(String etcaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = etcaTempfield3;
	}
	
	public String getEtcaTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEtcaTempfield4(String etcaTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = etcaTempfield4;
	}
	
	public String getEtcaTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEtcaTempfield5(String etcaTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = etcaTempfield5;
	}
	public String getEtcaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEtcaCreatedby(String etcaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = etcaCreatedby;
	}

	public String getEtcaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEtcaActive(String etcaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = etcaActive;
	}

	public String getEtcaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEtcaCreatedon(String etcaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = etcaCreatedon;
	}

	public String getEtcaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEtcaModifiedon(String etcaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = etcaModifiedon;
	}
	
public String getTraKeyid() {
		return traKeyid;
	}

	public void  setTraKeyid(String traKeyid) {
		this.traKeyid = traKeyid;
	}
  
}

