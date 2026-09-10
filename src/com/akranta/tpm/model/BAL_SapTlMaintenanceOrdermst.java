package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.SapTlMaintenanceorder.tableFldConstants;

public class BAL_SapTlMaintenanceOrdermst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid,refdocid, refdoctype, activitytype, priority, machineid, fnlocation, plannergrp
		, mntworkcenter, orderdesc, startdt, finishdt, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public BAL_SapTlMaintenanceOrdermst()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMomsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMomsKeyid(String momsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = momsKeyid;
	}
	public String getMomsRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setMomsRefdocid(String momsRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = momsRefdocid;
	}

	public String getMomsRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}


	public String getMomsActivitytype() {
		return (String) saveArray[ tableFldConstants.activitytype.ordinal() ];
	}

	public void setMomsActivitytype(String momsActivitytype) {
		saveArray[ tableFldConstants.activitytype.ordinal() ] = momsActivitytype;
	}

	public String getMomsPriority() {
		return (String) saveArray[ tableFldConstants.priority.ordinal() ];
	}

	public void setMomsPriority(String momsPriority) {
		saveArray[ tableFldConstants.priority.ordinal() ] = momsPriority;
	}

	public String getMomsMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMomsMachineid(String momsMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = momsMachineid;
	}

	public String getMomsFnlocation() {
		return (String) saveArray[ tableFldConstants.fnlocation.ordinal() ];
	}

	public void setMomsFnlocation(String momsFnlocation) {
		saveArray[ tableFldConstants.fnlocation.ordinal() ] = momsFnlocation;
	}

	public String getMomsPlannergrp() {
		return (String) saveArray[ tableFldConstants.plannergrp.ordinal() ];
	}

	public void setMomsPlannergrp(String momsPlannergrp) {
		saveArray[ tableFldConstants.plannergrp.ordinal() ] = momsPlannergrp;
	}

	public String getMomsMntworkcenter() {
		return (String) saveArray[ tableFldConstants.mntworkcenter.ordinal() ];
	}

	public void setMomsMntworkcenter(String momsMntworkcenter) {
		saveArray[ tableFldConstants.mntworkcenter.ordinal() ] = momsMntworkcenter;
	}

	public String getMomsOrderdesc() {
		return (String) saveArray[ tableFldConstants.orderdesc.ordinal() ];
	}

	public void setMomsOrderdesc(String momsOrderdesc) {
		saveArray[ tableFldConstants.orderdesc.ordinal() ] = momsOrderdesc;
	}

	public String getMomsStartdt() {
		return (String) saveArray[ tableFldConstants.startdt.ordinal() ];
	}

	public void setMomsStartdt(String momsStartdt) {
		saveArray[ tableFldConstants.startdt.ordinal() ] = momsStartdt;
	}

	public String getMomsFinishdt() {
		return (String) saveArray[ tableFldConstants.finishdt.ordinal() ];
	}

	public void setMomsFinishdt(String momsFinishdt) {
		saveArray[ tableFldConstants.finishdt.ordinal() ] = momsFinishdt;
	}

	public String getMomsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMomsTempfield1(String momsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = momsTempfield1;
	}

	public String getMomsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMomsTempfield2(String momsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = momsTempfield2;
	}

	public String getMomsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMomsTempfield3(String momsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = momsTempfield3;
	}

	public String getMomsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMomsTempfield4(String momsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = momsTempfield4;
	}

	public String getMomsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMomsTempfield5(String momsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = momsTempfield5;
	}

	public String getMomsTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setMomsTempfield6(String momsTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = momsTempfield6;
	}

	public String getMomsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMomsActive(String momsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = momsActive;
	}

	public String getMomsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMomsCreatedby(String momsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = momsCreatedby;
	}

	public String getMomsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMomsCreatedon(String momsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = momsCreatedon;
	}

	public String getMomsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMomsModifiedon(String momsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = momsModifiedon;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
		
	}

}

