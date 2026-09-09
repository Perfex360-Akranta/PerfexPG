package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class OplTlLesson {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		oplid, employeeid, date, active
	}

	public OplTlLesson()
	{
		saveArray = new  Object [ 4 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOpllOplid() {
		return (String) saveArray[ tableFldConstants.oplid.ordinal() ];
	}

	public void setOpllOplid(String opll_oplid) {
		saveArray[ tableFldConstants.oplid.ordinal() ] = opll_oplid;
	}

	public String getOpllEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setOpllEmployeeid(String opll_employeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = opll_employeeid;
	}

	public String getOpllDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setOpllDate(String opll_date) {
		saveArray[ tableFldConstants.date.ordinal() ] = opll_date;
	}

	public String getOpllActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOpllActive(String opll_active) {
		saveArray[ tableFldConstants.active.ordinal() ] = opll_active;
	}



}

