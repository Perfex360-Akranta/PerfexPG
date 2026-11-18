package com.akranta.tpm.model;

import com.akranta.tpm.model.BAL_PcsTlDtl.tableFldConstants;

public class BAL_PcsTlHtProductionHistory {
	
private  Object [] saveArray = null;  
	
	public void setSaveArray(Object[] saveArray) {
	this.saveArray = saveArray;
}

	public enum   tableFldConstants
	{
		keyid, cellid, date, shiftid,  productid, batchquantity,weightprice,area,fromdate,todate,remarks,
		tempfield1, tempfield2, tempfield3, active, createdby, createdon, modifiedon
	}

	public BAL_PcsTlHtProductionHistory()
	{
		saveArray = new  Object [ 18 ];
	}
	public Object[] getSaveArray() {
		return saveArray;
	}
	public String getKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKeyid(String keyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = keyid;
	}

	public String getCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setCellid(String cellId) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = cellId;
	}

	public String getDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}
	
	public void setDate(String date) {
		saveArray[ tableFldConstants.date.ordinal() ] = date;
	}
	public String getProductid(){
		return (String )saveArray [tableFldConstants.productid.ordinal() ];
	}
	public void setProductid(String productid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = productid;
	}
	public String getShiftid(){
		return (String )saveArray [tableFldConstants.shiftid.ordinal() ];
	}
	public void setShiftid(String shiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = shiftid;
	}
	public String getBatchQuantity(){
		return (String )saveArray [tableFldConstants.batchquantity.ordinal() ];
	}
	public void setBatchQuantity(String batchquantity) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = batchquantity;
	}
	public String getWeightPrice(){
		return (String )saveArray [tableFldConstants.weightprice.ordinal() ];
	}
	public void setWeightPrice(String weightprice) {
		saveArray[ tableFldConstants.weightprice.ordinal() ] = weightprice;
	}
	public String getArea(){
		return (String )saveArray [tableFldConstants.area.ordinal() ];
	}
	public void setArea(String area) {
		saveArray[ tableFldConstants.area.ordinal() ] = area;
	}
	public String getFromDate(){
		return (String )saveArray [tableFldConstants.fromdate.ordinal() ];
	}
	public void setFromDate(String fromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = fromdate;
	}	
	public String getToDate(){
		return (String )saveArray [tableFldConstants.todate.ordinal() ];
	}
	public void setToDate(String todate) {
		saveArray[ tableFldConstants.todate.ordinal() ] = todate;
	}
	public String getRemarks(){
		return (String )saveArray [tableFldConstants.remarks.ordinal() ];
	}
	public void setRemarks(String remarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = remarks;
	}
	
	public String getTempField1(){
		return (String )saveArray [tableFldConstants.tempfield1.ordinal() ];
	}
	public void setTempField1(String tempField1) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = tempField1;
	}
	public String getTempField2(){
		return (String )saveArray [tableFldConstants.tempfield2.ordinal() ];
	}
	public void setTempField2(String tempField2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tempField2;
	}
	public String getTempField3(){
		return (String )saveArray [tableFldConstants.tempfield3.ordinal() ];
	}
	public void setTempField3(String tempField3) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tempField3;
	}
	public String getActive(){
		return (String )saveArray [tableFldConstants.active.ordinal() ];
	}
	public void setActive(String active) {
		saveArray[ tableFldConstants.active.ordinal() ] = active;
	}
	public String getCreatedon(){
		return (String )saveArray [tableFldConstants.createdon.ordinal() ];
	}
	public void setCreatedon(String createdon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = createdon;
	}
	
	public String getCreatedby(){
		return (String )saveArray [tableFldConstants.createdby.ordinal() ];
	}
	public void getCreatedby(String createdby) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = createdby;
	}
	public String getModifiedon(){
		return (String )saveArray [tableFldConstants.modifiedon.ordinal() ];
	}
	public void getModifiedon(String modifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = modifiedon;
	}
	
	
}
