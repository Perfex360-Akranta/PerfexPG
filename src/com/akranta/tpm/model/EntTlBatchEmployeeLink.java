package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlBatchEmployeeLink {

	private  Object [] saveArray = null;  
	private List <EntTlBatchEmployeeLink> methodEntTlBatchEmployeeLink;

	public enum   tableFldConstants
	{
		bstdkeyid, bstdbachkeyid, bstdempmkeyid, bstdefffromdate, bstdefftilldate
		, bstdtempfield1, bstdtempfield2, bstdtempfield3, bstdtempfield4
		, bstdtempfield5, bstdactive, bstdcreatedby, bstdcreatedon, bstdmodifiedon
	}

	public EntTlBatchEmployeeLink()
	{
		saveArray = new  Object [ 14 ];
		methodEntTlBatchEmployeeLink = new ArrayList<EntTlBatchEmployeeLink>();
	}
	public List<EntTlBatchEmployeeLink> getmethodEntTlBatchEmployeeLink() 
	{
		return methodEntTlBatchEmployeeLink;
	}
	public void setmethodEntTlBatchEmployeeLink(List <EntTlBatchEmployeeLink> methodEntTlBatchEmployeeLink) {
		this.methodEntTlBatchEmployeeLink=methodEntTlBatchEmployeeLink;
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getBstdkeyid() {
		return (String) saveArray[ tableFldConstants.bstdkeyid.ordinal() ];
	}

	public void setBstdkeyid(String bstdkeyid) {
		saveArray[ tableFldConstants.bstdkeyid.ordinal() ] = bstdkeyid;
	}

	public String getBstdbachkeyid() {
		return (String) saveArray[ tableFldConstants.bstdbachkeyid.ordinal() ];
	}

	public void setBstdbachkeyid(String bstdbachkeyid) {
		saveArray[ tableFldConstants.bstdbachkeyid.ordinal() ] = bstdbachkeyid;
	}

	public String getBstdempmkeyid() {
		return (String) saveArray[ tableFldConstants.bstdempmkeyid.ordinal() ];
	}

	public void setBstdempmkeyid(String bstdempmkeyid) {
		saveArray[ tableFldConstants.bstdempmkeyid.ordinal() ] = bstdempmkeyid;
	}

	public String getBstdefffromdate() {
		return (String) saveArray[ tableFldConstants.bstdefffromdate.ordinal() ];
	}

	public void setBstdefffromdate(String bstdefffromdate) {
		saveArray[ tableFldConstants.bstdefffromdate.ordinal() ] = bstdefffromdate;
	}

	public String getBstdefftilldate() {
		return (String) saveArray[ tableFldConstants.bstdefftilldate.ordinal() ];
	}

	public void setBstdefftilldate(String bstdefftilldate) {
		saveArray[ tableFldConstants.bstdefftilldate.ordinal() ] = bstdefftilldate;
	}

	public String getBstdtempfield1() {
		return (String) saveArray[ tableFldConstants.bstdtempfield1.ordinal() ];
	}

	public void setBstdtempfield1(String bstdtempfield1) {
		saveArray[ tableFldConstants.bstdtempfield1.ordinal() ] = bstdtempfield1;
	}

	public String getBstdtempfield2() {
		return (String) saveArray[ tableFldConstants.bstdtempfield2.ordinal() ];
	}

	public void setBstdtempfield2(String bstdtempfield2) {
		saveArray[ tableFldConstants.bstdtempfield2.ordinal() ] = bstdtempfield2;
	}

	public String getBstdtempfield3() {
		return (String) saveArray[ tableFldConstants.bstdtempfield3.ordinal() ];
	}

	public void setBstdtempfield3(String bstdtempfield3) {
		saveArray[ tableFldConstants.bstdtempfield3.ordinal() ] = bstdtempfield3;
	}

	public String getBstdtempfield4() {
		return (String) saveArray[ tableFldConstants.bstdtempfield4.ordinal() ];
	}

	public void setBstdtempfield4(String bstdtempfield4) {
		saveArray[ tableFldConstants.bstdtempfield4.ordinal() ] = bstdtempfield4;
	}

	public String getBstdtempfield5() {
		return (String) saveArray[ tableFldConstants.bstdtempfield5.ordinal() ];
	}

	public void setBstdtempfield5(String bstdtempfield5) {
		saveArray[ tableFldConstants.bstdtempfield5.ordinal() ] = bstdtempfield5;
	}

	public String getBstdactive() {
		return (String) saveArray[ tableFldConstants.bstdactive.ordinal() ];
	}

	public void setBstdactive(String bstdactive) {
		saveArray[ tableFldConstants.bstdactive.ordinal() ] = bstdactive;
	}

	public String getBstdcreatedby() {
		return (String) saveArray[ tableFldConstants.bstdcreatedby.ordinal() ];
	}

	public void setBstdcreatedby(String bstdcreatedby) {
		saveArray[ tableFldConstants.bstdcreatedby.ordinal() ] = bstdcreatedby;
	}

	public String getBstdcreatedon() {
		return (String) saveArray[ tableFldConstants.bstdcreatedon.ordinal() ];
	}

	public void setBstdcreatedon(String bstdcreatedon) {
		saveArray[ tableFldConstants.bstdcreatedon.ordinal() ] = bstdcreatedon;
	}

	public String getBstdmodifiedon() {
		return (String) saveArray[ tableFldConstants.bstdmodifiedon.ordinal() ];
	}

	public void setBstdmodifiedon(String bstdmodifiedon) {
		saveArray[ tableFldConstants.bstdmodifiedon.ordinal() ] = bstdmodifiedon;
	}

}

