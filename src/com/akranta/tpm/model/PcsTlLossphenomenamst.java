package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlLossphenomenamst {

	private  Object [] saveArray = null;  
	
	List<PcsTlLossphenfactorylink> pcsTlLossphenfactorylink;

	public enum   tableFldConstants
	{
		keyid, name, mainloss, tempfield1, tempfield2, tempfield3, active
		, createdby, createdon, modifiedon
	}

	public PcsTlLossphenomenamst()
	{
		setPcsTlLossphenfactorylink(new ArrayList<PcsTlLossphenfactorylink>());
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPlpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPlpmKeyid(String plpmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = plpmKeyid;
	}

	public String getPlpmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setPlpmName(String plpmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = plpmName;
	}

	public String getPlpmMainloss() {
		return (String) saveArray[ tableFldConstants.mainloss.ordinal() ];
	}

	public void setPlpmMainloss(String plpmMainloss) {
		saveArray[ tableFldConstants.mainloss.ordinal() ] = plpmMainloss;
	}

	public String getPlpmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPlpmTempfield1(String plpmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = plpmTempfield1;
	}

	public String getPlpmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPlpmTempfield2(String plpmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = plpmTempfield2;
	}

	public String getPlpmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPlpmTempfield3(String plpmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = plpmTempfield3;
	}

	public String getPlpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPlpmActive(String plpmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = plpmActive;
	}

	public String getPlpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPlpmCreatedby(String plpmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = plpmCreatedby;
	}

	public String getPlpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPlpmCreatedon(String plpmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = plpmCreatedon;
	}

	public String getPlpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPlpmModifiedon(String plpmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = plpmModifiedon;
	}

	public void setPcsTlLossphenfactorylink(List<PcsTlLossphenfactorylink> pcsTlLossphenfactorylink) {
		this.pcsTlLossphenfactorylink = pcsTlLossphenfactorylink;
	}

	public List<PcsTlLossphenfactorylink> getpcsTlLossphenfactorylink() {
		return pcsTlLossphenfactorylink;
	}

}

