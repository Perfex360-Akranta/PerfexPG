package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;

public class KznTlDmtnotebookmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, type, flid, responsibleid, targetdate, completeddate, discussion
		, actionplan, remarks, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, active, createdby, createdon, modifiedon
	}

	public KznTlDmtnotebookmst()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getDmtnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDmtnKeyid(String dmtnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dmtnKeyid;
	}

	public String getDmtnType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setDmtnType(String dmtnType) {
		saveArray[ tableFldConstants.type.ordinal() ] = dmtnType;
	}

	public String getDmtnFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setDmtnFlid(String dmtnFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = dmtnFlid;
	}

	public String getDmtnResponsibleid() {
		return (String) saveArray[ tableFldConstants.responsibleid.ordinal() ];
	}

	public void setDmtnResponsibleid(String dmtnResponsibleid) {
		saveArray[ tableFldConstants.responsibleid.ordinal() ] = dmtnResponsibleid;
	}

	public String getDmtnTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setDmtnTargetdate(String dmtnTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = dmtnTargetdate;
	}

	public String getDmtnCompleteddate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}

	public void setDmtnCompleteddate(String dmtnCompleteddate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = dmtnCompleteddate;
	}

	public String getDmtnDiscussion() {
		return (String) saveArray[ tableFldConstants.discussion.ordinal() ];
	}

	public void setDmtnDiscussion(String dmtnDiscussion) {
		saveArray[ tableFldConstants.discussion.ordinal() ] = dmtnDiscussion;
	}

	public String getDmtnActionplan() {
		return (String) saveArray[ tableFldConstants.actionplan.ordinal() ];
	}

	public void setDmtnActionplan(String dmtnActionplan) {
		saveArray[ tableFldConstants.actionplan.ordinal() ] = dmtnActionplan;
	}

	public String getDmtnRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setDmtnRemarks(String dmtnRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = dmtnRemarks;
	}

	public String getDmtnTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setDmtnTempfield1(String dmtnTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = dmtnTempfield1;
	}

	public String getDmtnTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDmtnTempfield2(String dmtnTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dmtnTempfield2;
	}

	public String getDmtnTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDmtnTempfield3(String dmtnTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dmtnTempfield3;
	}

	public String getDmtnTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDmtnTempfield4(String dmtnTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dmtnTempfield4;
	}

	public String getDmtnTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setDmtnTempfield5(String dmtnTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = dmtnTempfield5;
	}

	public String getDmtnTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setDmtnTempfield6(String dmtnTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = dmtnTempfield6;
	}

	public String getDmtnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDmtnActive(String dmtnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dmtnActive;
	}

	public String getDmtnCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDmtnCreatedby(String dmtnCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dmtnCreatedby;
	}

	public String getDmtnCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDmtnCreatedon(String dmtnCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dmtnCreatedon;
	}

	public String getDmtnModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDmtnModifiedon(String dmtnModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dmtnModifiedon;
	}

	public static String getDeleteSql(TableFieldType[] dmtnDbFields,
			Object[] saveArray2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSaveArray(Object[] dataArr) {
		this.saveArray = dataArr;
		// TODO Auto-generated method stub
		
	}

}

