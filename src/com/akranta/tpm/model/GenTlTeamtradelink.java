package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlTeamtradelink {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, frt_keyid, tradeid, processid, subprocessid, subsubprocessid
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public GenTlTeamtradelink()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFrpKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFrpKeyid(String frpKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = frpKeyid;
	}

	public String getFrpFrtKeyid() {
		return (String) saveArray[ tableFldConstants.frt_keyid.ordinal() ];
	}

	public void setFrpFrtKeyid(String frpFrtKeyid) {
		saveArray[ tableFldConstants.frt_keyid.ordinal() ] = frpFrtKeyid;
	}

	public String getFrpTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setFrpTradeid(String frpTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = frpTradeid;
	}

	public String getFrpProcessid() {
		return (String) saveArray[ tableFldConstants.processid.ordinal() ];
	}

	public void setFrpProcessid(String frpProcessid) {
		saveArray[ tableFldConstants.processid.ordinal() ] = frpProcessid;
	}

	public String getFrpSubprocessid() {
		return (String) saveArray[ tableFldConstants.subprocessid.ordinal() ];
	}

	public void setFrpSubprocessid(String frpSubprocessid) {
		saveArray[ tableFldConstants.subprocessid.ordinal() ] = frpSubprocessid;
	}

	public String getFrpSubsubprocessid() {
		return (String) saveArray[ tableFldConstants.subsubprocessid.ordinal() ];
	}

	public void setFrpSubsubprocessid(String frpSubsubprocessid) {
		saveArray[ tableFldConstants.subsubprocessid.ordinal() ] = frpSubsubprocessid;
	}

	public String getFrpTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFrpTempfield1(String frpTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = frpTempfield1;
	}

	public String getFrpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFrpTempfield2(String frpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = frpTempfield2;
	}

	public String getFrpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFrpTempfield3(String frpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = frpTempfield3;
	}

	public String getFrpTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFrpTempfield4(String frpTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = frpTempfield4;
	}

	public String getFrpTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFrpTempfield5(String frpTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = frpTempfield5;
	}

	public String getFrpActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFrpActive(String frpActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = frpActive;
	}

	public String getFrpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFrpCreatedby(String frpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = frpCreatedby;
	}

	public String getFrpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFrpCreatedon(String frpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = frpCreatedon;
	}

	public String getFrpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFrpModifiedon(String frpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = frpModifiedon;
	}

}

