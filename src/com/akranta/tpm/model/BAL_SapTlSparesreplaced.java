package com.akranta.tpm.model;
import java.util.List;

public class BAL_SapTlSparesreplaced { 

	private  Object [] saveArray = null;  
	private List<BAL_SapTlSparesreplaced> sparesReplaced;
    private String ExistDocNumber;
    private String refDocId;
    private String orderType;
	public enum   tableFldConstants
	{
		keyId,spareno, docnumber, quantity, date, storagelocation, rate, value
		, sparename, stockavailable,  tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public BAL_SapTlSparesreplaced()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSspmSpareno() {
		return (String) saveArray[ tableFldConstants.spareno.ordinal() ];
	}

	public void setSspmSpareno(String sspmSpareno) {
		saveArray[ tableFldConstants.spareno.ordinal() ] = sspmSpareno;
	}

	public String getSspmDocnumber() {
		return (String) saveArray[ tableFldConstants.docnumber.ordinal() ];
	}

	public void setSspmDocnumber(String sspmDocnumber) {
		saveArray[ tableFldConstants.docnumber.ordinal() ] = sspmDocnumber;
	}

	public String getSspmQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setSspmQuantity(String sspmQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = sspmQuantity;
	}

	public String getSspmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setSspmDate(String sspmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = sspmDate;
	}

	public String getSspmStoragelocation() {
		return (String) saveArray[ tableFldConstants.storagelocation.ordinal() ];
	}

	public void setSspmStoragelocation(String sspmStoragelocation) {
		saveArray[ tableFldConstants.storagelocation.ordinal() ] = sspmStoragelocation;
	}

	public String getSspmRate() {
		return (String) saveArray[ tableFldConstants.rate.ordinal() ];
	}

	public void setSspmRate(String sspmRate) {
		saveArray[ tableFldConstants.rate.ordinal() ] = sspmRate;
	}

	public String getSspmValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setSspmValue(String sspmValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = sspmValue;
	}

	public String getSspmSparename() {
		return (String) saveArray[ tableFldConstants.sparename.ordinal() ];
	}

	public void setSspmSparename(String sspmsparename) {
		saveArray[ tableFldConstants.sparename.ordinal() ] = sspmsparename;
	}

	public String getSspmStockAvailable() {
		return (String) saveArray[ tableFldConstants.stockavailable.ordinal() ];
	}

	public void setSspmStockAvailable(String sspmstockavailable) {
		saveArray[ tableFldConstants.stockavailable.ordinal() ] = sspmstockavailable;
	}

	public String getSspmKeyId() {
		return (String) saveArray[ tableFldConstants.keyId.ordinal() ];
	}

	public void setSspmKeyId(String sspmKeyid) {
		saveArray[ tableFldConstants.keyId.ordinal() ] = sspmKeyid;
	}

	public String getSspmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSspmTempfield4(String sspmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = sspmTempfield4;
	}

	public String getSspmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSspmTempfield5(String sspmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = sspmTempfield5;
	}

	public String getSspmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSspmActive(String sspmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sspmActive;
	}

	public String getSspmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSspmCreatedby(String sspmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sspmCreatedby;
	}

	public String getSspmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSspmCreatedon(String sspmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sspmCreatedon;
	}

	public String getSspmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSspmModifiedon(String sspmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sspmModifiedon;
	}

	public List <BAL_SapTlSparesreplaced> getSparesReplaced() {
		return sparesReplaced; 
	}

	public void setSparesReplaced(List <BAL_SapTlSparesreplaced> sparesReplaced) {
		this.sparesReplaced = sparesReplaced;
	}

	public String getExistDocNumber() {
		return ExistDocNumber;
	}

	public void setExistDocNumber(String existDocNumber) {
		ExistDocNumber = existDocNumber;
	}

	public String getRefDocId() {
		return refDocId;
	}

	public void setRefDocId(String refDocId) {
		this.refDocId = refDocId;
	}
	public void setOrderType(String orderType){
		this.orderType = orderType;
	}

	public String getOrderType(){
		return orderType;
	}
}

