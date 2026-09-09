package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MspTlIndicatorsMst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, title, pillar,tempfield1,flid,elementid
		,active, createdby, createdon, modifiedon
	}

	public MspTlIndicatorsMst()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getMspiKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMspiKeyid(String mspiKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mspiKeyid;
	}

	public String getMspiFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setMspiFactoryid(String mspiFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = mspiFactoryid;
	}

	public String getMspiSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setMspiSectionid(String mspiSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = mspiSectionid;
	}
	
	public String getMspiFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setMspiFlid(String mspiflid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = mspiflid;
	}
	
	public String getMspiElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setMspiElementid(String mspielementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = mspielementid;
	}
	

	public String getMspiCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setMspiCellid(String mspiCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = mspiCellid;
	}

	public String getMspiTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setMspiTitle(String mspiTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = mspiTitle;
	}
	public String getMspiPillar() {
		return (String) saveArray[ tableFldConstants.pillar.ordinal() ];
	}

	public void setMspiPillar(String mspiPillar) {
		saveArray[ tableFldConstants.pillar.ordinal() ] = mspiPillar;
	}
	public String getMspiTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMspiTempfield1(String mspiTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mspiTempfield1;
	}

/*	public String getMspiTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMspiTempfield2(String mspiTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mspiTempfield2;
	}

	public String getMspiTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMspiTempfield3(String mspiTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mspiTempfield3;
	}*/

	public String getMspiActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMspiActive(String mspiActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mspiActive;
	}

	public String getMspiCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMspiCreatedby(String mspiCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mspiCreatedby;
	}

	public String getMspiCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMspiCreatedon(String mspiCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mspiCreatedon;
	}

	public String getMspiModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMspiModifiedon(String mspiModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mspiModifiedon;
	}

}

