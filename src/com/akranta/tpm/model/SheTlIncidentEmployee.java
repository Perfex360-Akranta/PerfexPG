package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SheTlIncidentEmployee {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, sinc_keyid, empm_keyid, injury_modeid, bodypart, remarks
		, injury_typeid, mode, type, location, tempfield5
	}

	public SheTlIncidentEmployee()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSiemKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSiemKeyid(String siemKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = siemKeyid;
	}

	public String getSiemSincKeyid() {
		return (String) saveArray[ tableFldConstants.sinc_keyid.ordinal() ];
	}

	public void setSiemSincKeyid(String siemSincKeyid) {
		saveArray[ tableFldConstants.sinc_keyid.ordinal() ] = siemSincKeyid;
	}

	public String getSiemEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setSiemEmpmKeyid(String siemEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = siemEmpmKeyid;
	}

	public String getSiemInjuryModeid() {
		return (String) saveArray[ tableFldConstants.injury_modeid.ordinal() ];
	}

	public void setSiemInjuryModeid(String siemInjuryModeid) {
		saveArray[ tableFldConstants.injury_modeid.ordinal() ] = siemInjuryModeid;
	}

	public String getSiemBodypart() {
		return (String) saveArray[ tableFldConstants.bodypart.ordinal() ];
	}

	public void setSiemBodypart(String siemBodypart) {
		saveArray[ tableFldConstants.bodypart.ordinal() ] = siemBodypart;
	}

	public String getSiemRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSiemRemarks(String siemRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = siemRemarks;
	}

	public String getSiemInjuryTypeid() {
		return (String) saveArray[ tableFldConstants.injury_typeid.ordinal() ];
	}

	public void setSiemInjuryTypeid(String siemInjuryTypeid) {
		saveArray[ tableFldConstants.injury_typeid.ordinal() ] = siemInjuryTypeid;
	}

	public String getSiemMode() {
		return (String) saveArray[ tableFldConstants.mode.ordinal() ];
	}

	public void setSiemMode(String siemMode) {
		saveArray[ tableFldConstants.mode.ordinal() ] = siemMode;
	}

	public String getSiemType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setSiemType(String siemType) {
		saveArray[ tableFldConstants.type.ordinal() ] = siemType;
	}

	public String getSiemLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setSiemLocation(String siemLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = siemLocation;
	}

	public String getSiemTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSiemTempfield5(String siemTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = siemTempfield5;
	}

	

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
}

