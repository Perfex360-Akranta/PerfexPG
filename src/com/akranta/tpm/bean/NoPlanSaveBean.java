package com.akranta.tpm.bean;

public class NoPlanSaveBean {

	private  Object [] saveArray = null;  
	public enum   tableFldConstants
	{
		mchkeyid, shiftorder,shiftdate
	}
	public NoPlanSaveBean()
	{
		saveArray = new  Object [ 3 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMachineKeyid() {
		return (String) saveArray[ tableFldConstants.mchkeyid.ordinal() ];
	}

	public void setMachineKeyid(String mchKeyid) {
		saveArray[ tableFldConstants.mchkeyid.ordinal() ] = mchKeyid;
	}

	public String getShiftOrder() {
		return (String) saveArray[ tableFldConstants.shiftorder.ordinal() ];
	}

	public void setShiftOrder(String shiftOrder) {
		saveArray[ tableFldConstants.shiftorder.ordinal() ] = shiftOrder;
	}

	public String getShiftDate() {
		return (String) saveArray[ tableFldConstants.shiftdate.ordinal() ];
	}

	public void setShiftDate(String shiftDate) {
		saveArray[ tableFldConstants.shiftdate.ordinal() ] = shiftDate;
	}

	}

