package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlMessageboard {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, title, content, effectivefrom, effectiveto, shownfordays
		, type, showngrouptype, flid, roleid, employeeid, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public GenTlMessageboard()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMsgbKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMsgbKeyid(String msgbKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = msgbKeyid;
	}

	public String getMsgbTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setMsgbTitle(String msgbTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = msgbTitle;
	}

	public String getMsgbContent() {
		return (String) saveArray[ tableFldConstants.content.ordinal() ];
	}

	public void setMsgbContent(String msgbContent) {
		saveArray[ tableFldConstants.content.ordinal() ] = msgbContent;
	}

	public String getMsgbEffectivefrom() {
		return (String) saveArray[ tableFldConstants.effectivefrom.ordinal() ];
	}

	public void setMsgbEffectivefrom(String msgbEffectivefrom) {
		saveArray[ tableFldConstants.effectivefrom.ordinal() ] = msgbEffectivefrom;
	}

	public String getMsgbEffectiveto() {
		return (String) saveArray[ tableFldConstants.effectiveto.ordinal() ];
	}

	public void setMsgbEffectiveto(String msgbEffectiveto) {
		saveArray[ tableFldConstants.effectiveto.ordinal() ] = msgbEffectiveto;
	}

	public String getMsgbShownfordays() {
		return (String) saveArray[ tableFldConstants.shownfordays.ordinal() ];
	}

	public void setMsgbShownfordays(String msgbShownfordays) {
		saveArray[ tableFldConstants.shownfordays.ordinal() ] = msgbShownfordays;
	}

	public String getMsgbType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setMsgbType(String msgbType) {
		saveArray[ tableFldConstants.type.ordinal() ] = msgbType;
	}

	public String getMsgbShowngrouptype() {
		return (String) saveArray[ tableFldConstants.showngrouptype.ordinal() ];
	}

	public void setMsgbShowngrouptype(String msgbShowngrouptype) {
		saveArray[ tableFldConstants.showngrouptype.ordinal() ] = msgbShowngrouptype;
	}

	public String getMsgbFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMsgbFlid(String msgbFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = msgbFlid;
	}

	public String getMsgbRoleid() {
		return (String) saveArray[ tableFldConstants.roleid.ordinal() ];
	}

	public void setMsgbRoleid(String msgbRoleid) {
		saveArray[ tableFldConstants.roleid.ordinal() ] = msgbRoleid;
	}

	public String getMsgbEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setMsgbEmployeeid(String msgbEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = msgbEmployeeid;
	}

	public String getMsgbTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMsgbTempfield1(String msgbTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = msgbTempfield1;
	}

	public String getMsgbTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMsgbTempfield2(String msgbTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = msgbTempfield2;
	}

	public String getMsgbTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMsgbTempfield3(String msgbTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = msgbTempfield3;
	}

	public String getMsgbTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMsgbTempfield4(String msgbTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = msgbTempfield4;
	}

	public String getMsgbTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMsgbTempfield5(String msgbTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = msgbTempfield5;
	}

	public String getMsgbActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMsgbActive(String msgbActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = msgbActive;
	}

	public String getMsgbCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMsgbCreatedby(String msgbCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = msgbCreatedby;
	}

	public String getMsgbCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMsgbCreatedon(String msgbCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = msgbCreatedon;
	}

	public String getMsgbModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMsgbModifiedon(String msgbModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = msgbModifiedon;
	}

   public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
		
	}
}

