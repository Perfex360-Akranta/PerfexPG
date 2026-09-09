package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MpsTlDtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		resultid, mpsid, resulttype, modifiedby, active, createdby, createdon
		, modifiedon
	}

	public MpsTlDtl()
	{
		saveArray = new  Object [ 8 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMpsdResultid() {
		return (String) saveArray[ tableFldConstants.resultid.ordinal() ];
	}

	public void setMpsdResultid(String mpsdResultid) {
		saveArray[ tableFldConstants.resultid.ordinal() ] = mpsdResultid;
	}

	public String getMpsdMpsid() {
		return (String) saveArray[ tableFldConstants.mpsid.ordinal() ];
	}

	public void setMpsdMpsid(String mpsdMpsid) {
		saveArray[ tableFldConstants.mpsid.ordinal() ] = mpsdMpsid;
	}

	public String getMpsdResulttype() {
		return (String) saveArray[ tableFldConstants.resulttype.ordinal() ];
	}

	public void setMpsdResulttype(String mpsdResulttype) {
		saveArray[ tableFldConstants.resulttype.ordinal() ] = mpsdResulttype;
	}

	public String getMpsdModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setMpsdModifiedby(String mpsdModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = mpsdModifiedby;
	}

	public String getMpsdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMpsdActive(String mpsdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mpsdActive;
	}

	public String getMpsdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMpsdCreatedby(String mpsdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mpsdCreatedby;
	}

	public String getMpsdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMpsdCreatedon(String mpsdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mpsdCreatedon;
	}

	public String getMpsdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMpsdModifiedon(String mpsdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mpsdModifiedon;
	}

}

