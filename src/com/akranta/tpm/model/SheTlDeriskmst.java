package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SheTlDeriskmst {

	private  Object [] saveArray = null;  
	
	private List<SheTlDeriskdtl> sheTlDeriskdtlList;
	public enum   tableFldConstants
	{
		keyid, rasm_keyid, date, preparedby, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public SheTlDeriskmst()
	{
		saveArray = new  Object [ 13 ];
	}
	public void setSaveArray(Object[] dataArr) {
		 this.saveArray = dataArr;
	}
	public Object[] getSaveArray() {
		return saveArray;
	}
	public List<SheTlDeriskdtl> getDeRiskDetails() {
		return sheTlDeriskdtlList;
	}

	public void setDeRiskDetails(List<SheTlDeriskdtl> sheTlDeriskdtlList) {
		this.sheTlDeriskdtlList = sheTlDeriskdtlList;
	}
	public String getDramKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDramKeyid(String dramKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dramKeyid;
	}

	public String getDramRasmKeyid() {
		return (String) saveArray[ tableFldConstants.rasm_keyid.ordinal() ];
	}

	public void setDramRasmKeyid(String dramRasmKeyid) {
		saveArray[ tableFldConstants.rasm_keyid.ordinal() ] = dramRasmKeyid;
	}

	public String getDramDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setDramDate(String dramDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = dramDate;
	}

	public String getDramPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setDramPreparedby(String dramPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = dramPreparedby;
	}

	public String getDramTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setDramTempfield1(String dramTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = dramTempfield1;
	}

	public String getDramTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDramTempfield2(String dramTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dramTempfield2;
	}

	public String getDramTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDramTempfield3(String dramTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dramTempfield3;
	}

	public String getDramTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDramTempfield4(String dramTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dramTempfield4;
	}

	public String getDramTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setDramTempfield5(String dramTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = dramTempfield5;
	}

	public String getDramActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDramActive(String dramActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dramActive;
	}

	public String getDramCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDramCreatedby(String dramCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dramCreatedby;
	}

	public String getDramCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDramCreatedon(String dramCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dramCreatedon;
	}

	public String getDramModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDramModifiedon(String dramModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dramModifiedon;
	}

}

