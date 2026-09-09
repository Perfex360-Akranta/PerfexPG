package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlMchparameterlink {

	private  Object [] saveArray = null;
	private List<GenTlMchparameterlink> equipmentParameterGrid;  
	

	public enum   tableFldConstants
	{
		keyid, parameterid, description, date, tempfield1, tempfield2
		, active, createdby, createdon, modifiedon
	}

	public GenTlMchparameterlink()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMplkKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMplkKeyid(String mplkKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mplkKeyid;
	}

	public String getMplkParameterid() {
		return (String) saveArray[ tableFldConstants.parameterid.ordinal() ];
	}

	public void setMplkParameterid(String mplkParameterid) {
		saveArray[ tableFldConstants.parameterid.ordinal() ] = mplkParameterid;
	}

	public String getMplkDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setMplkDescription(String mplkDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = mplkDescription;
	}

	public String getMplkDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setMplkDate(String mplkDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = mplkDate;
	}

	public String getMplkTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMplkTempfield1(String mplkTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mplkTempfield1;
	}

	public String getMplkTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMplkTempfield2(String mplkTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mplkTempfield2;
	}

	public String getMplkActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMplkActive(String mplkActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mplkActive;
	}

	public String getMplkCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMplkCreatedby(String mplkCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mplkCreatedby;
	}

	public String getMplkCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMplkCreatedon(String mplkCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mplkCreatedon;
	}

	public String getMplkModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMplkModifiedon(String mplkModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mplkModifiedon;
	}

	public void setequipmentParameterGrid(List<GenTlMchparameterlink> equipmentParameterGrid) {
		this.setEquipmentParameterGrid(equipmentParameterGrid);
		
	}

	public void setEquipmentParameterGrid(List<GenTlMchparameterlink> equipmentParameterGrid) {
		this.equipmentParameterGrid = equipmentParameterGrid;
	}

	public List<GenTlMchparameterlink> getEquipmentParameterGrid() {
		return equipmentParameterGrid;
	}

}

