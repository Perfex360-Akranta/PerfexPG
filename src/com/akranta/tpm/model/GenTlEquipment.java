package com.akranta.tpm.model;

public class GenTlEquipment {
private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, equipmentname, dateofmanufacture, equipmentcategory, equipmentsubcategory, manufacturerserialno,
		installationdate,active,remarks,tempfield1, tempfield2, tempfield3, tempfield4, createdby, createdon
		, modifiedon
	}

	public GenTlEquipment()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}

	public String getEquiKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEquiKeyid(String equiKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = equiKeyid;
	}

	public String getEquiequipmentname() {
		return (String) saveArray[ tableFldConstants.equipmentname.ordinal() ];
	}

	public void setEquiequipmentname(String equiequipmentname) {
		saveArray[ tableFldConstants.equipmentname.ordinal() ] = equiequipmentname;
	}

	public String getEquidateofmanufacture() {
		return (String) saveArray[ tableFldConstants.dateofmanufacture.ordinal() ];
	}

	public void setEquidateofmanufacture(String equidateofmanufacture) {
		saveArray[ tableFldConstants.dateofmanufacture.ordinal() ] = equidateofmanufacture;
	}

	public String getEquiequipmentcategory() {
		return (String) saveArray[ tableFldConstants.equipmentcategory.ordinal() ];
	}

	public void setEquiequipmentcategory(String equiequipmentcategory) {
		saveArray[ tableFldConstants.equipmentcategory.ordinal() ] = equiequipmentcategory;
	}

	public String getEquiequipmentsubcategory() {
		return (String) saveArray[ tableFldConstants.equipmentsubcategory.ordinal() ];
	}

	public void setEquiequipmentsubcategory(String equiequipmentsubcategory) {
		saveArray[ tableFldConstants.equipmentsubcategory.ordinal() ] = equiequipmentsubcategory;
	}

	public String getEquimanufacturerserialno() {
		return (String) saveArray[ tableFldConstants.manufacturerserialno.ordinal() ];
	}

	public void setEquimanufacturerserialno(String equimanufacturerserialno) {
		saveArray[ tableFldConstants.manufacturerserialno.ordinal() ] = equimanufacturerserialno;
	}

	public String getAchTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setAchTempfield1(String achTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = achTempfield1;
	}

	public String getAchTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setAchTempfield2(String achTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = achTempfield2;
	}

	public String getAchTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAchTempfield3(String achTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = achTempfield3;
	}

	public String getAchTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setAchTempfield4(String achTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = achTempfield4;
	}

	public String getAchActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAchActive(String achActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = achActive;
	}

	public String getAchCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAchCreatedby(String achCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = achCreatedby;
	}

	public String getAchCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAchCreatedon(String achCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = achCreatedon;
	}

	public String getAchModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAchModifiedon(String achModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = achModifiedon;
	}

}

