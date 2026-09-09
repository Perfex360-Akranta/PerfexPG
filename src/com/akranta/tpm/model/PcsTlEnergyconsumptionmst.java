package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;

public class PcsTlEnergyconsumptionmst {

	private  Object [] saveArray = null;  
	private List<PcsTlEnergyconsumptiondtl> pcsTlEnergyconsumptiondtl;  

	public enum   tableFldConstants
	{
		keyid, date, totalmaterialcon, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, tempfield8
		, tempfield9, tempfield10, active, createdby, createdon, modifiedon
	}

	public PcsTlEnergyconsumptionmst()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEncmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEncmKeyid(String encmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = encmKeyid;
	}

	public String getEncmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setEncmDate(String encmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = encmDate;
	}

	public String getEncmTotalmaterialcon() {
		return (String) saveArray[ tableFldConstants.totalmaterialcon.ordinal() ];
	}

	public void setEncmTotalmaterialcon(String encmTotalmaterialcon) {
		saveArray[ tableFldConstants.totalmaterialcon.ordinal() ] = encmTotalmaterialcon;
	}

	public String getEncmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEncmTempfield1(String encmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = encmTempfield1;
	}

	public String getEncmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEncmTempfield2(String encmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = encmTempfield2;
	}

	public String getEncmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEncmTempfield3(String encmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = encmTempfield3;
	}

	public String getEncmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEncmTempfield4(String encmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = encmTempfield4;
	}

	public String getEncmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEncmTempfield5(String encmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = encmTempfield5;
	}

	public String getEncmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setEncmTempfield6(String encmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = encmTempfield6;
	}

	public String getEncmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setEncmTempfield7(String encmTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = encmTempfield7;
	}

	public String getEncmTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setEncmTempfield8(String encmTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = encmTempfield8;
	}

	public String getEncmTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setEncmTempfield9(String encmTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = encmTempfield9;
	}

	public String getEncmTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setEncmTempfield10(String encmTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = encmTempfield10;
	}

	public String getEncmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEncmActive(String encmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = encmActive;
	}

	public String getEncmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEncmCreatedby(String encmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = encmCreatedby;
	}

	public String getEncmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEncmCreatedon(String encmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = encmCreatedon;
	}

	public String getEncmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEncmModifiedon(String encmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = encmModifiedon;
	}

	public void setPcsTlEnergyconsumptiondtl(List<PcsTlEnergyconsumptiondtl> pcsTlEnergyconsumptiondtl) {
		this.pcsTlEnergyconsumptiondtl = pcsTlEnergyconsumptiondtl;
	}

	public List<PcsTlEnergyconsumptiondtl> getPcsTlEnergyconsumptiondtl() {
		return pcsTlEnergyconsumptiondtl;
	}

	public void setEnergyconsumptiondtlGrid(List<PcsTlEnergyconsumptiondtl> energyconsumptiondtlGrid) {
		this.pcsTlEnergyconsumptiondtl = pcsTlEnergyconsumptiondtl;
		
	}



	

}

