package com.akranta.tpm.model;


public class KznTlLosslink {

	private  Object [] saveArray = null;  
	private String selectLossFlag;

	public enum   tableFldConstants
	{
		kaizenid, tpmpillarid, lossid, active, createdby, createdon, modifiedon
	}

	public KznTlLosslink()
	{
		saveArray = new  Object [ 7 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKzllKaizenid() {
		return (String) saveArray[ tableFldConstants.kaizenid.ordinal() ];
	}

	public void setKzllKaizenid(String kzllKaizenid) {
		saveArray[ tableFldConstants.kaizenid.ordinal() ] = kzllKaizenid;
	}

	public String getKzllTpmpillarid() {
		return (String) saveArray[ tableFldConstants.tpmpillarid.ordinal() ];
	}

	public void setKzllTpmpillarid(String kzllTpmpillarid) {
		saveArray[ tableFldConstants.tpmpillarid.ordinal() ] = kzllTpmpillarid;
	}

	public String getKzllLossid() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setKzllLossid(String kzllLossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = kzllLossid;
	}

	public String getKzllActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKzllActive(String kzllActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kzllActive;
	}

	public String getKzllCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKzllCreatedby(String kzllCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kzllCreatedby;
	}

	public String getKzllCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKzllCreatedon(String kzllCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kzllCreatedon;
	}

	public String getKzllModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKzllModifiedon(String kzllModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kzllModifiedon;
	}

	public void setSelectLossFlag(String selectLossFlag) {
		this.selectLossFlag = selectLossFlag;
	}

	public String getSelectLossFlag() {
		return selectLossFlag;
	}

}

