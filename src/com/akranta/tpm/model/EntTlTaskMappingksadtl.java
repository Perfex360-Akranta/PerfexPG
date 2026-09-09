package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTaskMappingksadtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, tmkm_keyid, task, ksa, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, active, createdby
		, createdon, modifiedon
	}

	public EntTlTaskMappingksadtl()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTmkdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTmkdKeyid(String tmkdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tmkdKeyid;
	}

	public String getTmkdTmkmKeyid() {
		return (String) saveArray[ tableFldConstants.tmkm_keyid.ordinal() ];
	}

	public void setTmkdTmkmKeyid(String tmkdTmkmKeyid) {
		saveArray[ tableFldConstants.tmkm_keyid.ordinal() ] = tmkdTmkmKeyid;
	}

	public String getTmkdTask() {
		return (String) saveArray[ tableFldConstants.task.ordinal() ];
	}

	public void setTmkdTask(String tmkdTask) {
		saveArray[ tableFldConstants.task.ordinal() ] = tmkdTask;
	}

	public String getTmkdKsa() {
		return (String) saveArray[ tableFldConstants.ksa.ordinal() ];
	}

	public void setTmkdKsa(String tmkdKsa) {
		saveArray[ tableFldConstants.ksa.ordinal() ] = tmkdKsa;
	}

	public String getTmkdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTmkdTempfield1(String tmkdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = tmkdTempfield1;
	}

	public String getTmkdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTmkdTempfield2(String tmkdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tmkdTempfield2;
	}

	public String getTmkdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTmkdTempfield3(String tmkdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tmkdTempfield3;
	}

	public String getTmkdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTmkdTempfield4(String tmkdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = tmkdTempfield4;
	}

	public String getTmkdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTmkdTempfield5(String tmkdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = tmkdTempfield5;
	}

	public String getTmkdTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setTmkdTempfield6(String tmkdTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = tmkdTempfield6;
	}

	public String getTmkdTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setTmkdTempfield7(String tmkdTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = tmkdTempfield7;
	}

	public String getTmkdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTmkdActive(String tmkdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tmkdActive;
	}

	public String getTmkdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTmkdCreatedby(String tmkdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tmkdCreatedby;
	}

	public String getTmkdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTmkdCreatedon(String tmkdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tmkdCreatedon;
	}

	public String getTmkdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTmkdModifiedon(String tmkdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tmkdModifiedon;
	}

}

