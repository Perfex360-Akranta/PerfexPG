package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class QtmTlQpointdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, qpmkeyid, qparameter, specification, effectofparam, measuringequip
		, monitoringmethod, fourm, frequency, tempfield1, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedon
	}

	public QtmTlQpointdtl()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getQpdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQpdKeyid(String qpdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qpdKeyid;
	}

	public String getQpdQpmkeyid() {
		return (String) saveArray[ tableFldConstants.qpmkeyid.ordinal() ];
	}

	public void setQpdQpmkeyid(String qpdQpmkeyid) {
		saveArray[ tableFldConstants.qpmkeyid.ordinal() ] = qpdQpmkeyid;
	}

	public String getQpdQparameter() {
		return (String) saveArray[ tableFldConstants.qparameter.ordinal() ];
	}

	public void setQpdQparameter(String qpdQparameter) {
		saveArray[ tableFldConstants.qparameter.ordinal() ] = qpdQparameter;
	}

	public String getQpdSpecification() {
		return (String) saveArray[ tableFldConstants.specification.ordinal() ];
	}

	public void setQpdSpecification(String qpdSpecification) {
		saveArray[ tableFldConstants.specification.ordinal() ] = qpdSpecification;
	}

	public String getQpdEffectofparam() {
		return (String) saveArray[ tableFldConstants.effectofparam.ordinal() ];
	}

	public void setQpdEffectofparam(String qpdEffectofparam) {
		saveArray[ tableFldConstants.effectofparam.ordinal() ] = qpdEffectofparam;
	}

	public String getQpdMeasuringequip() {
		return (String) saveArray[ tableFldConstants.measuringequip.ordinal() ];
	}

	public void setQpdMeasuringequip(String qpdMeasuringequip) {
		saveArray[ tableFldConstants.measuringequip.ordinal() ] = qpdMeasuringequip;
	}

	public String getQpdMonitoringmethod() {
		return (String) saveArray[ tableFldConstants.monitoringmethod.ordinal() ];
	}

	public void setQpdMonitoringmethod(String qpdMonitoringmethod) {
		saveArray[ tableFldConstants.monitoringmethod.ordinal() ] = qpdMonitoringmethod;
	}

	public String getQpdFourm() {
		return (String) saveArray[ tableFldConstants.fourm.ordinal() ];
	}

	public void setQpdFourm(String qpdFourm) {
		saveArray[ tableFldConstants.fourm.ordinal() ] = qpdFourm;
	}

	public String getQpdFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setQpdFrequency(String qpdFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = qpdFrequency;
	}

	public String getQpdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setQpdTempfield1(String qpdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = qpdTempfield1;
	}

	public String getQpdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setQpdTempfield2(String qpdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = qpdTempfield2;
	}

	public String getQpdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setQpdTempfield3(String qpdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = qpdTempfield3;
	}

	public String getQpdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setQpdTempfield4(String qpdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = qpdTempfield4;
	}

	public String getQpdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQpdActive(String qpdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qpdActive;
	}

	public String getQpdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setQpdCreatedby(String qpdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = qpdCreatedby;
	}

	public String getQpdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setQpdCreatedon(String qpdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = qpdCreatedon;
	}

	public String getQpdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setQpdModifiedon(String qpdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = qpdModifiedon;
	}

	public void setSaveArray(Object[] dataArr) {
		// TODO Auto-generated method stub
		this.saveArray = dataArr ;
	}

}

