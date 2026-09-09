package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.OplTlMst.tableFldConstants;

public class MspTlMst {

	private  Object [] saveArray = null;	
	private List<MspTlDtl> mspTlDtl ;
	
	public enum   tableFldConstants
	{
		keyid, indicatorid, factoryid, sectionid, cellid, planstartdate
		, plantilldate, actualstartdate, actualtilldate, planduration
		, actualduration, responsibility, status, completedby, planstartweek
		, plantillweek, actualstartweek, actualtillweek, tempfield10
		, tempfield9, tempfield8, tempfield7, tempfield6, tempfield5
		, tempfield4, tempfield3, tempfield2, tempfield1, elementid, flid,active, createdby
		, createdon, modifiedon
	}

	public MspTlMst()
	{
		saveArray = new  Object [ 34 ];
		setMspTlDtl(new ArrayList<MspTlDtl>());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}


	
	
	public String getMpmsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMpmsKeyid(String mpmsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mpmsKeyid;
	}

	public String getMpmsIndicatorid() {
		return (String) saveArray[ tableFldConstants.indicatorid.ordinal() ];
	}

	public void setMpmsIndicatorid(String mpmsIndicatorid) {
		saveArray[ tableFldConstants.indicatorid.ordinal() ] = mpmsIndicatorid;
	}

	public String getMpmsFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setMpmsFactoryid(String mpmsFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = mpmsFactoryid;
	}

	public String getMpmsSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setMpmsSectionid(String mpmsSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = mpmsSectionid;
	}

	public String getMpmsCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setMpmsCellid(String mpmsCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = mpmsCellid;
	}

	public String getMpmsPlanstartdate() {
		return (String) saveArray[ tableFldConstants.planstartdate.ordinal() ];
	}

	public void setMpmsPlanstartdate(String mpmsPlanstartdate) {
		saveArray[ tableFldConstants.planstartdate.ordinal() ] = mpmsPlanstartdate;
	}

	public String getMpmsPlantilldate() {
		return (String) saveArray[ tableFldConstants.plantilldate.ordinal() ];
	}

	public void setMpmsPlantilldate(String mpmsPlantilldate) {
		saveArray[ tableFldConstants.plantilldate.ordinal() ] = mpmsPlantilldate;
	}

	public String getMpmsActualstartdate() {
		return (String) saveArray[ tableFldConstants.actualstartdate.ordinal() ];
	}

	public void setMpmsActualstartdate(String mpmsActualstartdate) {
		saveArray[ tableFldConstants.actualstartdate.ordinal() ] = mpmsActualstartdate;
	}

	public String getMpmsActualtilldate() {
		return (String) saveArray[ tableFldConstants.actualtilldate.ordinal() ];
	}

	public void setMpmsActualtilldate(String mpmsActualtilldate) {
		saveArray[ tableFldConstants.actualtilldate.ordinal() ] = mpmsActualtilldate;
	}

	public String getMpmsPlanduration() {
		return (String) saveArray[ tableFldConstants.planduration.ordinal() ];
	}

	public void setMpmsPlanduration(String mpmsPlanduration) {
		saveArray[ tableFldConstants.planduration.ordinal() ] = mpmsPlanduration;
	}

	public String getMpmsActualduration() {
		return (String) saveArray[ tableFldConstants.actualduration.ordinal() ];
	}

	public void setMpmsActualduration(String mpmsActualduration) {
		saveArray[ tableFldConstants.actualduration.ordinal() ] = mpmsActualduration;
	}

	public String getMpmsResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setMpmsResponsibility(String mpmsResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = mpmsResponsibility;
	}

	public String getMpmsStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setMpmsStatus(String mpmsStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = mpmsStatus;
	}

	public String getMpmsCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setMpmsCompletedby(String mpmsCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = mpmsCompletedby;
	}

	public String getMpmsPlanstartweek() {
		return (String) saveArray[ tableFldConstants.planstartweek.ordinal() ];
	}

	public void setMpmsPlanstartweek(String mpmsPlanstartweek) {
		saveArray[ tableFldConstants.planstartweek.ordinal() ] = mpmsPlanstartweek;
	}

	public String getMpmsPlantillweek() {
		return (String) saveArray[ tableFldConstants.plantillweek.ordinal() ];
	}

	public void setMpmsPlantillweek(String mpmsPlantillweek) {
		saveArray[ tableFldConstants.plantillweek.ordinal() ] = mpmsPlantillweek;
	}

	public String getMpmsActualstartweek() {
		return (String) saveArray[ tableFldConstants.actualstartweek.ordinal() ];
	}

	public void setMpmsActualstartweek(String mpmsActualstartweek) {
		saveArray[ tableFldConstants.actualstartweek.ordinal() ] = mpmsActualstartweek;
	}

	public String getMpmsActualtillweek() {
		return (String) saveArray[ tableFldConstants.actualtillweek.ordinal() ];
	}

	public void setMpmsActualtillweek(String mpmsActualtillweek) {
		saveArray[ tableFldConstants.actualtillweek.ordinal() ] = mpmsActualtillweek;
	}

	public String getMpmsTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setMpmsTempfield10(String mpmsTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = mpmsTempfield10;
	}

	public String getMpmsTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setMpmsTempfield9(String mpmsTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = mpmsTempfield9;
	}

	public String getMpmsTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setMpmsTempfield8(String mpmsTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = mpmsTempfield8;
	}

	public String getMpmsTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setMpmsTempfield7(String mpmsTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = mpmsTempfield7;
	}

	public String getMpmsTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setMpmsTempfield6(String mpmsTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = mpmsTempfield6;
	}

	public String getMpmsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMpmsTempfield5(String mpmsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mpmsTempfield5;
	}

	public String getMpmsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMpmsTempfield4(String mpmsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mpmsTempfield4;
	}

	public String getMpmsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMpmsTempfield3(String mpmsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mpmsTempfield3;
	}

	public String getMpmsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMpmsTempfield2(String mpmsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mpmsTempfield2;
	}

	public String getMpmsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMpmsTempfield1(String mpmsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mpmsTempfield1;
	}
    
	public String getMpmsElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setMpmsElementid(String mpmsElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = mpmsElementid;
	}

	public String getMpmsFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMpmsFlid(String mpmsFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = mpmsFlid;
	}
	
	public String getMpmsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMpmsActive(String mpmsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mpmsActive;
	}

	public String getMpmsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMpmsCreatedby(String mpmsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mpmsCreatedby;
	}

	public String getMpmsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMpmsCreatedon(String mpmsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mpmsCreatedon;
	}

	public String getMpmsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMpmsModifiedon(String mpmsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mpmsModifiedon;
	}

	public List<MspTlDtl> getMspTlDtl() {
		return mspTlDtl;
	}

	public void setMspTlDtl(List<MspTlDtl> mspTlDtl) {
		this.mspTlDtl = mspTlDtl;
	}

}

