package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SheTlDeriskdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, dram_keyid, rasd_keyid, date, preparedby, probablityid
		, seviorityid, riskval, risklevelid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public SheTlDeriskdtl()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getDradKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDradKeyid(String dradKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dradKeyid;
	}

	public String getDradDramKeyid() {
		return (String) saveArray[ tableFldConstants.dram_keyid.ordinal() ];
	}

	public void setDradDramKeyid(String dradDramKeyid) {
		saveArray[ tableFldConstants.dram_keyid.ordinal() ] = dradDramKeyid;
	}

	public String getDradRasdKeyid() {
		return (String) saveArray[ tableFldConstants.rasd_keyid.ordinal() ];
	}

	public void setDradRasdKeyid(String dradRasdKeyid) {
		saveArray[ tableFldConstants.rasd_keyid.ordinal() ] = dradRasdKeyid;
	}

	public String getDradDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setDradDate(String dradDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = dradDate;
	}

	public String getDradPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setDradPreparedby(String dradPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = dradPreparedby;
	}

	public String getDradProbablityid() {
		return (String) saveArray[ tableFldConstants.probablityid.ordinal() ];
	}

	public void setDradProbablityid(String dradProbablityid) {
		saveArray[ tableFldConstants.probablityid.ordinal() ] = dradProbablityid;
	}

	public String getDradSeviorityid() {
		return (String) saveArray[ tableFldConstants.seviorityid.ordinal() ];
	}

	public void setDradSeviorityid(String dradSeviorityid) {
		saveArray[ tableFldConstants.seviorityid.ordinal() ] = dradSeviorityid;
	}

	public String getDradRiskval() {
		return (String) saveArray[ tableFldConstants.riskval.ordinal() ];
	}

	public void setDradRiskval(String dradRiskval) {
		saveArray[ tableFldConstants.riskval.ordinal() ] = dradRiskval;
	}

	public String getDradRisklevelid() {
		return (String) saveArray[ tableFldConstants.risklevelid.ordinal() ];
	}

	public void setDradRisklevelid(String dradRisklevelid) {
		saveArray[ tableFldConstants.risklevelid.ordinal() ] = dradRisklevelid;
	}

	public String getDradTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setDradTempfield1(String dradTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = dradTempfield1;
	}

	public String getDradTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDradTempfield2(String dradTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dradTempfield2;
	}

	public String getDradTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDradTempfield3(String dradTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dradTempfield3;
	}

	public String getDradTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDradTempfield4(String dradTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dradTempfield4;
	}

	public String getDradTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setDradTempfield5(String dradTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = dradTempfield5;
	}

	public String getDradActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDradActive(String dradActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dradActive;
	}

	public String getDradCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDradCreatedby(String dradCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dradCreatedby;
	}

	public String getDradCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDradCreatedon(String dradCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dradCreatedon;
	}

	public String getDradModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDradModifiedon(String dradModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dradModifiedon;
	}

}

