package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlTeamDoucmentLink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, docno, doctype, teamid, emptype, temp2, temp3, active, createdby
		, createdon, modifiedon
	}

	public GenTlTeamDoucmentLink()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTmdlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTmdlKeyid(String tmdlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tmdlKeyid;
	}

	public String getTmdlDocno() {
		return (String) saveArray[ tableFldConstants.docno.ordinal() ];
	}

	public void setTmdlDocno(String tmdlDocno) {
		saveArray[ tableFldConstants.docno.ordinal() ] = tmdlDocno;
	}

	public String getTmdlDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setTmdlDoctype(String tmdlDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = tmdlDoctype;
	}

	public String getTmdlTeamid() {
		return (String) saveArray[ tableFldConstants.teamid.ordinal() ];
	}

	public void setTmdlTeamid(String tmdlTeamid) {
		saveArray[ tableFldConstants.teamid.ordinal() ] = tmdlTeamid;
	}

	public String getTmdlEmptype() {
		return (String) saveArray[ tableFldConstants.emptype.ordinal() ];
	}

	public void setTmdlEmptype(String tmdlEmptype) {
		saveArray[ tableFldConstants.emptype.ordinal() ] = tmdlEmptype;
	}

	public String getTmdlTemp2() {
		return (String) saveArray[ tableFldConstants.temp2.ordinal() ];
	}

	public void setTmdlTemp2(String tmdlTemp2) {
		saveArray[ tableFldConstants.temp2.ordinal() ] = tmdlTemp2;
	}

	public String getTmdlTemp3() {
		return (String) saveArray[ tableFldConstants.temp3.ordinal() ];
	}

	public void setTmdlTemp3(String tmdlTemp3) {
		saveArray[ tableFldConstants.temp3.ordinal() ] = tmdlTemp3;
	}

	public String getTmdlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTmdlActive(String tmdlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tmdlActive;
	}

	public String getTmdlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTmdlCreatedby(String tmdlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tmdlCreatedby;
	}

	public String getTmdlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTmdlCreatedon(String tmdlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tmdlCreatedon;
	}

	public String getTmdlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTmdlModifiedon(String tmdlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tmdlModifiedon;
	}

}

