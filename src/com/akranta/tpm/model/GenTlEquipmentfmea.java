package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlEquipmentfmea {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flnid, functionfail, component, function, severity, occurrence
		, detection, targetdate, responsible, potentialfailmode, potentialeffectfail
		, currentcontrol, recommendedaction, actiontaken, tempfield1
		, tempfield2, tempfield3, tempfield4, active, createdby, createdon
		, modifiedon
	}

	public GenTlEquipmentfmea()
	{
		saveArray = new  Object [ 23 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}

	public String getEfmaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEfmaKeyid(String efmaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = efmaKeyid;
	}

	public String getEfmaFlnid() {
		return (String) saveArray[ tableFldConstants.flnid.ordinal() ];
	}

	public void setEfmaFlnid(String efmaFlnid) {
		saveArray[ tableFldConstants.flnid.ordinal() ] = efmaFlnid;
	}

	public String getEfmaFunctionfail() {
		return (String) saveArray[ tableFldConstants.functionfail.ordinal() ];
	}

	public void setEfmaFunctionfail(String efmaFunctionfail) {
		saveArray[ tableFldConstants.functionfail.ordinal() ] = efmaFunctionfail;
	}

	public String getEfmaComponent() {
		return (String) saveArray[ tableFldConstants.component.ordinal() ];
	}

	public void setEfmaComponent(String efmaComponent) {
		saveArray[ tableFldConstants.component.ordinal() ] = efmaComponent;
	}

	public String getEfmaFunction() {
		return (String) saveArray[ tableFldConstants.function.ordinal() ];
	}

	public void setEfmaFunction(String efmaFunction) {
		saveArray[ tableFldConstants.function.ordinal() ] = efmaFunction;
	}

	public String getEfmaSeverity() {
		return (String) saveArray[ tableFldConstants.severity.ordinal() ];
	}

	public void setEfmaSeverity(String efmaSeverity) {
		saveArray[ tableFldConstants.severity.ordinal() ] = efmaSeverity;
	}

	public String getEfmaOccurrence() {
		return (String) saveArray[ tableFldConstants.occurrence.ordinal() ];
	}

	public void setEfmaOccurrence(String efmaOccurrence) {
		saveArray[ tableFldConstants.occurrence.ordinal() ] = efmaOccurrence;
	}

	public String getEfmaDetection() {
		return (String) saveArray[ tableFldConstants.detection.ordinal() ];
	}

	public void setEfmaDetection(String efmaDetection) {
		saveArray[ tableFldConstants.detection.ordinal() ] = efmaDetection;
	}

	public String getEfmaTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setEfmaTargetdate(String efmaTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = efmaTargetdate;
	}

	public String getEfmaResponsible() {
		return (String) saveArray[ tableFldConstants.responsible.ordinal() ];
	}

	public void setEfmaResponsible(String efmaResponsible) {
		saveArray[ tableFldConstants.responsible.ordinal() ] = efmaResponsible;
	}

	public String getEfmaPotentialfailmode() {
		return (String) saveArray[ tableFldConstants.potentialfailmode.ordinal() ];
	}

	public void setEfmaPotentialfailmode(String efmaPotentialfailmode) {
		saveArray[ tableFldConstants.potentialfailmode.ordinal() ] = efmaPotentialfailmode;
	}

	public String getEfmaPotentialeffectfail() {
		return (String) saveArray[ tableFldConstants.potentialeffectfail.ordinal() ];
	}

	public void setEfmaPotentialeffectfail(String efmaPotentialeffectfail) {
		saveArray[ tableFldConstants.potentialeffectfail.ordinal() ] = efmaPotentialeffectfail;
	}

	public String getEfmaCurrentcontrol() {
		return (String) saveArray[ tableFldConstants.currentcontrol.ordinal() ];
	}

	public void setEfmaCurrentcontrol(String efmaCurrentcontrol) {
		saveArray[ tableFldConstants.currentcontrol.ordinal() ] = efmaCurrentcontrol;
	}

	public String getEfmaRecommendedaction() {
		return (String) saveArray[ tableFldConstants.recommendedaction.ordinal() ];
	}

	public void setEfmaRecommendedaction(String efmaRecommendedaction) {
		saveArray[ tableFldConstants.recommendedaction.ordinal() ] = efmaRecommendedaction;
	}

	public String getEfmaActiontaken() {
		return (String) saveArray[ tableFldConstants.actiontaken.ordinal() ];
	}

	public void setEfmaActiontaken(String efmaActiontaken) {
		saveArray[ tableFldConstants.actiontaken.ordinal() ] = efmaActiontaken;
	}

	public String getEfmaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEfmaTempfield1(String efmaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = efmaTempfield1;
	}

	public String getEfmaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEfmaTempfield2(String efmaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = efmaTempfield2;
	}

	public String getEfmaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEfmaTempfield3(String efmaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = efmaTempfield3;
	}

	public String getEfmaTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEfmaTempfield4(String efmaTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = efmaTempfield4;
	}

	public String getEfmaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEfmaActive(String efmaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = efmaActive;
	}

	public String getEfmaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEfmaCreatedby(String efmaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = efmaCreatedby;
	}

	public String getEfmaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEfmaCreatedon(String efmaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = efmaCreatedon;
	}

	public String getEfmaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEfmaModifiedon(String efmaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = efmaModifiedon;
	}

}

