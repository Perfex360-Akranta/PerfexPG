package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlSusadtl {

	private  Object [] saveArray = null;  
	List <GenTlAllmoduleimgfile> images =null;

	public enum   tableFldConstants
	{
		keyid, susm_keyid, susa_keyid, conformance, nonconformance, image
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, active, createdby, createdon, modifiedon
	}

	public GenTlSusadtl()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSusdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSusdKeyid(String susdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = susdKeyid;
	}

	public String getSusdSusmKeyid() {
		return (String) saveArray[ tableFldConstants.susm_keyid.ordinal() ];
	}

	public void setSusdSusmKeyid(String susdSusmKeyid) {
		saveArray[ tableFldConstants.susm_keyid.ordinal() ] = susdSusmKeyid;
	}

	public String getSusdSusaKeyid() {
		return (String) saveArray[ tableFldConstants.susa_keyid.ordinal() ];
	}

	public void setSusdSusaKeyid(String susdSusaKeyid) {
		saveArray[ tableFldConstants.susa_keyid.ordinal() ] = susdSusaKeyid;
	}

	public String getSusdConformance() {
		return (String) saveArray[ tableFldConstants.conformance.ordinal() ];
	}

	public void setSusdConformance(String susdConformance) {
		saveArray[ tableFldConstants.conformance.ordinal() ] = susdConformance;
	}

	public String getSusdNonconformance() {
		return (String) saveArray[ tableFldConstants.nonconformance.ordinal() ];
	}

	public void setSusdNonconformance(String susdNonconformance) {
		saveArray[ tableFldConstants.nonconformance.ordinal() ] = susdNonconformance;
	}

	public String getSusdImage() {
		return (String) saveArray[ tableFldConstants.image.ordinal() ];
	}

	public void setSusdImage(String susdImage) {
		saveArray[ tableFldConstants.image.ordinal() ] = susdImage;
	}

	public String getSusdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSusdTempfield2(String susdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = susdTempfield2;
	}

	public String getSusdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSusdTempfield3(String susdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = susdTempfield3;
	}

	public String getSusdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSusdTempfield4(String susdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = susdTempfield4;
	}

	public String getSusdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setSusdTempfield5(String susdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = susdTempfield5;
	}

	public String getSusdTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setSusdTempfield6(String susdTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = susdTempfield6;
	}

	public String getSusdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSusdActive(String susdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = susdActive;
	}

	public String getSusdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSusdCreatedby(String susdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = susdCreatedby;
	}

	public String getSusdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSusdCreatedon(String susdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = susdCreatedon;
	}

	public String getSusdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSusdModifiedon(String susdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = susdModifiedon;
	}

	public List<GenTlAllmoduleimgfile> getImages() {
		return images;
	}

	public void setImages(List<GenTlAllmoduleimgfile> images) {
		this.images = images;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

}

