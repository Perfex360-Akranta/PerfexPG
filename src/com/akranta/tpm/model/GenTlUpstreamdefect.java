package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlUpstreamdefect {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, elementid, area, date, inspectionlotno, informto
		, rawmaterial, defect, correctionaction, preventiveaction, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public GenTlUpstreamdefect()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

	public String getUpsdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setUpsdKeyid(String upsdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = upsdKeyid;
	}

	public String getUpsdFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setUpsdFlid(String upsdFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = upsdFlid;
	}

	public String getUpsdElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setUpsdElementid(String upsdElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = upsdElementid;
	}

	public String getUpsdArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setUpsdArea(String upsdArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = upsdArea;
	}

	public String getUpsdDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setUpsdDate(String upsdDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = upsdDate;
	}

	public String getUpsdInspectionlotno() {
		return (String) saveArray[ tableFldConstants.inspectionlotno.ordinal() ];
	}

	public void setUpsdInspectionlotno(String upsdInspectionlotno) {
		saveArray[ tableFldConstants.inspectionlotno.ordinal() ] = upsdInspectionlotno;
	}

	public String getUpsdInformto() {
		return (String) saveArray[ tableFldConstants.informto.ordinal() ];
	}

	public void setUpsdInformto(String upsdInformto) {
		saveArray[ tableFldConstants.informto.ordinal() ] = upsdInformto;
	}

	public String getUpsdRawmaterial() {
		return (String) saveArray[ tableFldConstants.rawmaterial.ordinal() ];
	}

	public void setUpsdRawmaterial(String upsdRawmaterial) {
		saveArray[ tableFldConstants.rawmaterial.ordinal() ] = upsdRawmaterial;
	}

	public String getUpsdDefect() {
		return (String) saveArray[ tableFldConstants.defect.ordinal() ];
	}

	public void setUpsdDefect(String upsdDefect) {
		saveArray[ tableFldConstants.defect.ordinal() ] = upsdDefect;
	}

	public String getUpsdCorrectionaction() {
		return (String) saveArray[ tableFldConstants.correctionaction.ordinal() ];
	}

	public void setUpsdCorrectionaction(String upsdCorrectionaction) {
		saveArray[ tableFldConstants.correctionaction.ordinal() ] = upsdCorrectionaction;
	}

	public String getUpsdPreventiveaction() {
		return (String) saveArray[ tableFldConstants.preventiveaction.ordinal() ];
	}

	public void setUpsdPreventiveaction(String upsdPreventiveaction) {
		saveArray[ tableFldConstants.preventiveaction.ordinal() ] = upsdPreventiveaction;
	}

	public String getUpsdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setUpsdTempfield1(String upsdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = upsdTempfield1;
	}

	public String getUpsdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setUpsdTempfield2(String upsdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = upsdTempfield2;
	}

	public String getUpsdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setUpsdTempfield3(String upsdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = upsdTempfield3;
	}

	public String getUpsdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setUpsdTempfield4(String upsdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = upsdTempfield4;
	}

	public String getUpsdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setUpsdTempfield5(String upsdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = upsdTempfield5;
	}

	public String getUpsdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setUpsdActive(String upsdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = upsdActive;
	}

	public String getUpsdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setUpsdCreatedby(String upsdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = upsdCreatedby;
	}

	public String getUpsdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setUpsdCreatedon(String upsdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = upsdCreatedon;
	}

	public String getUpsdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setUpsdModifiedon(String upsdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = upsdModifiedon;
	}

}

