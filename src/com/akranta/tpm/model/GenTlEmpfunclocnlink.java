package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;

public class GenTlEmpfunclocnlink {

	private  Object [] saveArray = null;  
	private List<GenTlEmpfunclocnlink> methodGenTlEmpfunclocnlink;

	public enum   tableFldConstants
	{
		keyid, employeeid, funclocn, funclocntype, active, createdby
		, createdon, modifiedon
	}

	public GenTlEmpfunclocnlink()
	{
		saveArray = new  Object [ 8 ];
		methodGenTlEmpfunclocnlink = new ArrayList<GenTlEmpfunclocnlink>();
	}
   
    public List<GenTlEmpfunclocnlink> getmethodGenTlEmpfunclocnlink() 
	{
		return methodGenTlEmpfunclocnlink;
	}
	public void setmethodGenTlEmpfunclocnlink(List<GenTlEmpfunclocnlink> methodGenTlEmpfunclocnlink) {
		this.methodGenTlEmpfunclocnlink=methodGenTlEmpfunclocnlink;
	}
   
	public Object[] getSaveArray() {
		return saveArray;
	}
    //setEfll_keyid
	public String getEfllkeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEfllkeyid(String efllkeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = efllkeyid;
	}

	public String getEfllemployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setEfllemployeeid(String efllemployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = efllemployeeid;
	}

	public String getEfllfunclocn() {
		return (String) saveArray[ tableFldConstants.funclocn.ordinal() ];
	}

	public void setEfllfunclocn(String efllfunclocn) {
		saveArray[ tableFldConstants.funclocn.ordinal() ] = efllfunclocn;
	}

	public String getEfllfunclocntype() {
		return (String) saveArray[ tableFldConstants.funclocntype.ordinal() ];
	}

	public void setEfllfunclocntype(String efllfunclocntype) {
		saveArray[ tableFldConstants.funclocntype.ordinal() ] = efllfunclocntype;
	}

	public String getEfllactive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEfllactive(String efllactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = efllactive;
	}

	public String getEfllcreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEfllcreatedby(String efllcreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = efllcreatedby;
	}

	public String getEfllcreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEfllcreatedon(String efllcreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = efllcreatedon;
	}

	public String getEfllmodifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEfllmodifiedon(String efllmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = efllmodifiedon;
	}

	

}

