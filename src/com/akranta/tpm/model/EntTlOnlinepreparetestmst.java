package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlOnlinepreparetestmst {

	private  Object [] saveArray = null;  
	private String elementid;

	
	private List<EntTlOnlinepreparetestdtl> prepareTest;
	public enum   tableFldConstants
	{
		keyid, programid, topicid, validity, noofquestion, testnumber
		, description, totalmarks, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, active, createdon, duration
		, modifiedon
	}

	public EntTlOnlinepreparetestmst()
	{
		saveArray = new  Object [ 18 ];
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOlpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setOlpmKeyid(String olpmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = olpmKeyid;
	}

	public String getOlpmProgramid() {
		return (String) saveArray[ tableFldConstants.programid.ordinal() ];
	}

	public void setOlpmProgramid(String olpmProgramid) {
		saveArray[ tableFldConstants.programid.ordinal() ] = olpmProgramid;
	}

	public String getOlpmTopicid() {
		return (String) saveArray[ tableFldConstants.topicid.ordinal() ];
	}

	public void setOlpmTopicid(String olpmTopicid) {
		saveArray[ tableFldConstants.topicid.ordinal() ] = olpmTopicid;
	}

	public String getOlpmValidity() {
		return (String) saveArray[ tableFldConstants.validity.ordinal() ];
	}

	public void setOlpmValidity(String olpmValidity) {
		saveArray[ tableFldConstants.validity.ordinal() ] = olpmValidity;
	}

	public String getOlpmNoofquestion() {
		return (String) saveArray[ tableFldConstants.noofquestion.ordinal() ];
	}

	public void setOlpmNoofquestion(String olpmNoofquestion) {
		saveArray[ tableFldConstants.noofquestion.ordinal() ] = olpmNoofquestion;
	}

	public String getOlpmTestnumber() {
		return (String) saveArray[ tableFldConstants.testnumber.ordinal() ];
	}

	public void setOlpmTestnumber(String olpmTestnumber) {
		saveArray[ tableFldConstants.testnumber.ordinal() ] = olpmTestnumber;
	}

	public String getOlpmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setOlpmDescription(String olpmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = olpmDescription;
	}

	public String getOlpmTotalmarks() {
		return (String) saveArray[ tableFldConstants.totalmarks.ordinal() ];
	}

	public void setOlpmTotalmarks(String olpmTotalmarks) {
		saveArray[ tableFldConstants.totalmarks.ordinal() ] = olpmTotalmarks;
	}

	public String getOlpmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setOlpmTempfield1(String olpmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = olpmTempfield1;
	}

	public String getOlpmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setOlpmTempfield2(String olpmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = olpmTempfield2;
	}

	public String getOlpmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setOlpmTempfield3(String olpmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = olpmTempfield3;
	}

	public String getOlpmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOlpmTempfield4(String olpmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = olpmTempfield4;
	}

	public String getOlpmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setOlpmTempfield5(String olpmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = olpmTempfield5;
	}

	public String getOlpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOlpmCreatedby(String olpmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = olpmCreatedby;
	}

	public String getOlpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOlpmActive(String olpmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = olpmActive;
	}

	public String getOlpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOlpmCreatedon(String olpmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = olpmCreatedon;
	}

	public String getOlpmDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setOlpmDuration(String olpmDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = olpmDuration;
	}

	public String getOlpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOlpmModifiedon(String olpmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = olpmModifiedon;
	}

	public void setPrepareTest(List<EntTlOnlinepreparetestdtl> prepareTest) {
		this.prepareTest = prepareTest;
	}

	public List<EntTlOnlinepreparetestdtl> getPrepareTest() {
		return prepareTest;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}
	public String getElementid() {
		return elementid;
	}
}

