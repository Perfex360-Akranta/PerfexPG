package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTrainingarea {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, parentid, levelno, elementtype, refid, reftype, locationid
		, remarks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, tempfield8, tempfield9, tempfield10
		, active, createdby, createdon, modifiedon
	}

	public EntTlTrainingarea()
	{
		saveArray = new  Object [ 23 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getTrarKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTrarKeyid(String trarKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = trarKeyid;
	}

	public String getTrarName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setTrarName(String trarName) {
		saveArray[ tableFldConstants.name.ordinal() ] = trarName;
	}

	public String getTrarParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setTrarParentid(String trarParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = trarParentid;
	}

	public String getTrarLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setTrarLevelno(String trarLevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = trarLevelno;
	}

	public String getTrarElementtype() {
		return (String) saveArray[ tableFldConstants.elementtype.ordinal() ];
	}

	public void setTrarElementtype(String trarElementtype) {
		saveArray[ tableFldConstants.elementtype.ordinal() ] = trarElementtype;
	}

	public String getTrarRefid() {
		return (String) saveArray[ tableFldConstants.refid.ordinal() ];
	}

	public void setTrarRefid(String trarRefid) {
		saveArray[ tableFldConstants.refid.ordinal() ] = trarRefid;
	}

	public String getTrarReftype() {
		return (String) saveArray[ tableFldConstants.reftype.ordinal() ];
	}

	public void setTrarReftype(String trarReftype) {
		saveArray[ tableFldConstants.reftype.ordinal() ] = trarReftype;
	}

	public String getTrarLocationid() {
		return (String) saveArray[ tableFldConstants.locationid.ordinal() ];
	}

	public void setTrarLocationid(String trarLocationid) {
		saveArray[ tableFldConstants.locationid.ordinal() ] = trarLocationid;
	}

	public String getTrarRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setTrarRemarks(String trarRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = trarRemarks;
	}

	public String getTrarTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTrarTempfield1(String trarTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = trarTempfield1;
	}

	public String getTrarTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTrarTempfield2(String trarTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = trarTempfield2;
	}

	public String getTrarTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTrarTempfield3(String trarTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = trarTempfield3;
	}

	public String getTrarTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTrarTempfield4(String trarTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = trarTempfield4;
	}

	public String getTrarTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTrarTempfield5(String trarTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = trarTempfield5;
	}

	public String getTrarTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setTrarTempfield6(String trarTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = trarTempfield6;
	}

	public String getTrarTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setTrarTempfield7(String trarTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = trarTempfield7;
	}

	public String getTrarTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setTrarTempfield8(String trarTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = trarTempfield8;
	}

	public String getTrarTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setTrarTempfield9(String trarTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = trarTempfield9;
	}

	public String getTrarTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setTrarTempfield10(String trarTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = trarTempfield10;
	}

	public String getTrarActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTrarActive(String trarActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = trarActive;
	}

	public String getTrarCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTrarCreatedby(String trarCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = trarCreatedby;
	}

	public String getTrarCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTrarCreatedon(String trarCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = trarCreatedon;
	}

	public String getTrarModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTrarModifiedon(String trarModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = trarModifiedon;
	}

	

}

