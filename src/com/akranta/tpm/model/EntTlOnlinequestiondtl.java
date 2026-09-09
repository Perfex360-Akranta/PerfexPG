package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlOnlinequestiondtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, olqm_keyid, answer, correctanswer, sortorder, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, createdby, active
		, createdon, modifiedon
	}

	public EntTlOnlinequestiondtl()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOlqdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setOlqdKeyid(String olqdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = olqdKeyid;
	}

	public String getOlqdOlqmKeyid() {
		return (String) saveArray[ tableFldConstants.olqm_keyid.ordinal() ];
	}

	public void setOlqdOlqmKeyid(String olqdOlqmKeyid) {
		saveArray[ tableFldConstants.olqm_keyid.ordinal() ] = olqdOlqmKeyid;
	}

	public String getOlqdAnswer() {
		return (String) saveArray[ tableFldConstants.answer.ordinal() ];
	}

	public void setOlqdAnswer(String olqdAnswer) {
		saveArray[ tableFldConstants.answer.ordinal() ] = olqdAnswer;
	}

	public String getOlqdCorrectanswer() {
		return (String) saveArray[ tableFldConstants.correctanswer.ordinal() ];
	}

	public void setOlqdCorrectanswer(String olqdCorrectanswer) {
		saveArray[ tableFldConstants.correctanswer.ordinal() ] = olqdCorrectanswer;
	}

	public String getOlqdSortorder() {
		return (String) saveArray[ tableFldConstants.sortorder.ordinal() ];
	}

	public void setOlqdSortorder(String olqdSortorder) {
		saveArray[ tableFldConstants.sortorder.ordinal() ] = olqdSortorder;
	}

	public String getOlqdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setOlqdTempfield1(String olqdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = olqdTempfield1;
	}

	public String getOlqdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setOlqdTempfield2(String olqdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = olqdTempfield2;
	}

	public String getOlqdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setOlqdTempfield3(String olqdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = olqdTempfield3;
	}

	public String getOlqdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOlqdTempfield4(String olqdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = olqdTempfield4;
	}

	public String getOlqdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setOlqdTempfield5(String olqdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = olqdTempfield5;
	}

	public String getOlqdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOlqdCreatedby(String olqdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = olqdCreatedby;
	}

	public String getOlqdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOlqdActive(String olqdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = olqdActive;
	}

	public String getOlqdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOlqdCreatedon(String olqdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = olqdCreatedon;
	}

	public String getOlqdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOlqdModifiedon(String olqdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = olqdModifiedon;
	}

}

