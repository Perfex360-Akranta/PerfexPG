package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class AdmTlScrollmsgmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, roleid, cellid, shiftid, message, status, priority
		, fromdate, todate, noexpiry, noofocc, dwmy, optionno, recurrencefreq
		, weekday, weekno, dayonmonth, monthonyear, regenno, advanced
		, range, istobedisplayed, dis_alllevel, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public AdmTlScrollmsgmst()
	{
		saveArray = new  Object [ 31 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSmsgKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSmsgKeyid(String smsgKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = smsgKeyid;
	}

	public String getSmsgFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSmsgFlid(String smsgFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = smsgFlid;
	}

	public String getSmsgRoleid() {
		return (String) saveArray[ tableFldConstants.roleid.ordinal() ];
	}

	public void setSmsgRoleid(String smsgRoleid) {
		saveArray[ tableFldConstants.roleid.ordinal() ] = smsgRoleid;
	}

	public String getSmsgCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setSmsgCellid(String smsgCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = smsgCellid;
	}

	public String getSmsgShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setSmsgShiftid(String smsgShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = smsgShiftid;
	}

	public String getSmsgMessage() {
		return (String) saveArray[ tableFldConstants.message.ordinal() ];
	}

	public void setSmsgMessage(String smsgMessage) {
		saveArray[ tableFldConstants.message.ordinal() ] = smsgMessage;
	}

	public String getSmsgStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setSmsgStatus(String smsgStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = smsgStatus;
	}

	public String getSmsgPriority() {
		return (String) saveArray[ tableFldConstants.priority.ordinal() ];
	}

	public void setSmsgPriority(String smsgPriority) {
		saveArray[ tableFldConstants.priority.ordinal() ] = smsgPriority;
	}

	public String getSmsgFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setSmsgFromdate(String smsgFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = smsgFromdate;
	}

	public String getSmsgTodate() {
		return (String) saveArray[ tableFldConstants.todate.ordinal() ];
	}

	public void setSmsgTodate(String smsgTodate) {
		saveArray[ tableFldConstants.todate.ordinal() ] = smsgTodate;
	}

	public String getSmsgNoexpiry() {
		return (String) saveArray[ tableFldConstants.noexpiry.ordinal() ];
	}

	public void setSmsgNoexpiry(String smsgNoexpiry) {
		saveArray[ tableFldConstants.noexpiry.ordinal() ] = smsgNoexpiry;
	}

	public String getSmsgNoofocc() {
		return (String) saveArray[ tableFldConstants.noofocc.ordinal() ];
	}

	public void setSmsgNoofocc(String smsgNoofocc) {
		saveArray[ tableFldConstants.noofocc.ordinal() ] = smsgNoofocc;
	}

	public String getSmsgDwmy() {
		return (String) saveArray[ tableFldConstants.dwmy.ordinal() ];
	}

	public void setSmsgDwmy(String smsgDwmy) {
		saveArray[ tableFldConstants.dwmy.ordinal() ] = smsgDwmy;
	}

	public String getSmsgOptionno() {
		return (String) saveArray[ tableFldConstants.optionno.ordinal() ];
	}

	public void setSmsgOptionno(String smsgOptionno) {
		saveArray[ tableFldConstants.optionno.ordinal() ] = smsgOptionno;
	}

	public String getSmsgRecurrencefreq() {
		return (String) saveArray[ tableFldConstants.recurrencefreq.ordinal() ];
	}

	public void setSmsgRecurrencefreq(String smsgRecurrencefreq) {
		saveArray[ tableFldConstants.recurrencefreq.ordinal() ] = smsgRecurrencefreq;
	}

	public String getSmsgWeekday() {
		return (String) saveArray[ tableFldConstants.weekday.ordinal() ];
	}

	public void setSmsgWeekday(String smsgWeekday) {
		saveArray[ tableFldConstants.weekday.ordinal() ] = smsgWeekday;
	}

	public String getSmsgWeekno() {
		return (String) saveArray[ tableFldConstants.weekno.ordinal() ];
	}

	public void setSmsgWeekno(String smsgWeekno) {
		saveArray[ tableFldConstants.weekno.ordinal() ] = smsgWeekno;
	}

	public String getSmsgDayonmonth() {
		return (String) saveArray[ tableFldConstants.dayonmonth.ordinal() ];
	}

	public void setSmsgDayonmonth(String smsgDayonmonth) {
		saveArray[ tableFldConstants.dayonmonth.ordinal() ] = smsgDayonmonth;
	}

	public String getSmsgMonthonyear() {
		return (String) saveArray[ tableFldConstants.monthonyear.ordinal() ];
	}

	public void setSmsgMonthonyear(String smsgMonthonyear) {
		saveArray[ tableFldConstants.monthonyear.ordinal() ] = smsgMonthonyear;
	}

	public String getSmsgRegenno() {
		return (String) saveArray[ tableFldConstants.regenno.ordinal() ];
	}

	public void setSmsgRegenno(String smsgRegenno) {
		saveArray[ tableFldConstants.regenno.ordinal() ] = smsgRegenno;
	}

	public String getSmsgAdvanced() {
		return (String) saveArray[ tableFldConstants.advanced.ordinal() ];
	}

	public void setSmsgAdvanced(String smsgAdvanced) {
		saveArray[ tableFldConstants.advanced.ordinal() ] = smsgAdvanced;
	}

	public String getSmsgRange() {
		return (String) saveArray[ tableFldConstants.range.ordinal() ];
	}

	public void setSmsgRange(String smsgRange) {
		saveArray[ tableFldConstants.range.ordinal() ] = smsgRange;
	}

	public String getSmsgIstobedisplayed() {
		return (String) saveArray[ tableFldConstants.istobedisplayed.ordinal() ];
	}

	public void setSmsgIstobedisplayed(String smsgIstobedisplayed) {
		saveArray[ tableFldConstants.istobedisplayed.ordinal() ] = smsgIstobedisplayed;
	}

	public String getSmsgDisAlllevel() {
		return (String) saveArray[ tableFldConstants.dis_alllevel.ordinal() ];
	}

	public void setSmsgDisAlllevel(String smsgdisAlllevel) {
		saveArray[ tableFldConstants.dis_alllevel.ordinal() ] = smsgdisAlllevel;
	}

	public String getSmsgTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSmsgTempfield2(String smsgTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = smsgTempfield2;
	}

	public String getSmsgTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSmsgTempfield3(String smsgTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = smsgTempfield3;
	}

	public String getSmsgTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSmsgTempfield4(String smsgTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = smsgTempfield4;
	}

	public String getSmsgActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSmsgActive(String smsgActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = smsgActive;
	}

	public String getSmsgCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSmsgCreatedby(String smsgCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = smsgCreatedby;
	}

	public String getSmsgCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSmsgCreatedon(String smsgCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = smsgCreatedon;
	}

	public String getSmsgModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSmsgModifiedon(String smsgModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = smsgModifiedon;
	}

}

