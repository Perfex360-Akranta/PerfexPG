package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlBudgetmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, batch_keyid, bsdl_keyid, ebet_keyid, date, billno, billdate
		, quantity, amount, remarks, enteredby, accounthead, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public EntTlBudgetmst()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getBudgKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBudgKeyid(String budgKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = budgKeyid;
	}

	public String getBudgBatchKeyid() {
		return (String) saveArray[ tableFldConstants.batch_keyid.ordinal() ];
	}

	public void setBudgBatchKeyid(String budgBatchKeyid) {
		saveArray[ tableFldConstants.batch_keyid.ordinal() ] = budgBatchKeyid;
	}

	public String getBudgBsdlKeyid() {
		return (String) saveArray[ tableFldConstants.bsdl_keyid.ordinal() ];
	}

	public void setBudgBsdlKeyid(String budgBsdlKeyid) {
		saveArray[ tableFldConstants.bsdl_keyid.ordinal() ] = budgBsdlKeyid;
	}

	public String getBudgEbetKeyid() {
		return (String) saveArray[ tableFldConstants.ebet_keyid.ordinal() ];
	}

	public void setBudgEbetKeyid(String budgEbetKeyid) {
		saveArray[ tableFldConstants.ebet_keyid.ordinal() ] = budgEbetKeyid;
	}

	public String getBudgDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setBudgDate(String budgDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = budgDate;
	}

	public String getBudgBillno() {
		return (String) saveArray[ tableFldConstants.billno.ordinal() ];
	}

	public void setBudgBillno(String budgBillno) {
		saveArray[ tableFldConstants.billno.ordinal() ] = budgBillno;
	}

	public String getBudgBilldate() {
		return (String) saveArray[ tableFldConstants.billdate.ordinal() ];
	}

	public void setBudgBilldate(String budgBilldate) {
		saveArray[ tableFldConstants.billdate.ordinal() ] = budgBilldate;
	}

	public String getBudgQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setBudgQuantity(String budgQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = budgQuantity;
	}

	public String getBudgAmount() {
		return (String) saveArray[ tableFldConstants.amount.ordinal() ];
	}

	public void setBudgAmount(String budgAmount) {
		saveArray[ tableFldConstants.amount.ordinal() ] = budgAmount;
	}

	public String getBudgRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setBudgRemarks(String budgRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = budgRemarks;
	}

	public String getBudgEnteredby() {
		return (String) saveArray[ tableFldConstants.enteredby.ordinal() ];
	}

	public void setBudgEnteredby(String budgEnteredby) {
		saveArray[ tableFldConstants.enteredby.ordinal() ] = budgEnteredby;
	}

	public String getBudgAccounthead() {
		return (String) saveArray[ tableFldConstants.accounthead.ordinal() ];
	}

	public void setBudgAccounthead(String budgAccounthead) {
		saveArray[ tableFldConstants.accounthead.ordinal() ] = budgAccounthead;
	}

	public String getBudgTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setBudgTempfield3(String budgTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = budgTempfield3;
	}

	public String getBudgTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setBudgTempfield4(String budgTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = budgTempfield4;
	}

	public String getBudgTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setBudgTempfield5(String budgTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = budgTempfield5;
	}

	public String getBudgActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBudgActive(String budgActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = budgActive;
	}

	public String getBudgCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBudgCreatedby(String budgCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = budgCreatedby;
	}

	public String getBudgCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBudgCreatedon(String budgCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = budgCreatedon;
	}

	public String getBudgModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBudgModifiedon(String budgModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = budgModifiedon;
	}

}

