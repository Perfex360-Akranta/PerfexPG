package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlOtherlossentry {

	private  Object [] saveArray = null;  
	private String excelName;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, lossid, lossdate, lossvalue, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, tempfield7, tempfield8, active, createdby, createdon, modifiedon
	}

	public PcsTlOtherlossentry()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	public String getOlseKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setOlseKeyid(String olseKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = olseKeyid;
	}

	public String getOlseFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setOlseFlid(String olseFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = olseFlid;
	}

	public String getOlseElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setOlseElementid(String olseElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = olseElementid;
	}

	public String getOlseDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setOlseDate(String olseDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = olseDate;
	}

	public String getOlseLossid() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setOlseLossid(String olseLossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = olseLossid;
	}

	public String getOlseLossdate() {
		return (String) saveArray[ tableFldConstants.lossdate.ordinal() ];
	}

	public void setOlseLossdate(String olseLossdate) {
		saveArray[ tableFldConstants.lossdate.ordinal() ] = olseLossdate;
	}

	public String getOlseLossvalue() {
		return (String) saveArray[ tableFldConstants.lossvalue.ordinal() ];
	}

	public void setOlseLossvalue(String olseLossvalue) {
		saveArray[ tableFldConstants.lossvalue.ordinal() ] = olseLossvalue;
	}

	public String getOlseTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setOlseTempfield1(String olseTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = olseTempfield1;
	}

	public String getOlseTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setOlseTempfield2(String olseTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = olseTempfield2;
	}

	public String getOlseTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setOlseTempfield3(String olseTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = olseTempfield3;
	}

	public String getOlseTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOlseTempfield4(String olseTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = olseTempfield4;
	}

	public String getOlseTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setOlseTempfield5(String olseTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = olseTempfield5;
	}

	public String getOlseTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setOlseTempfield6(String olseTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = olseTempfield6;
	}

	public String getOlseTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setOlseTempfield7(String olseTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = olseTempfield7;
	}

	public String getOlseTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setOlseTempfield8(String olseTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = olseTempfield8;
	}

	public String getOlseActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOlseActive(String olseActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = olseActive;
	}

	public String getOlseCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOlseCreatedby(String olseCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = olseCreatedby;
	}

	public String getOlseCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOlseCreatedon(String olseCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = olseCreatedon;
	}

	public String getOlseModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOlseModifiedon(String olseModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = olseModifiedon;
	}

}

