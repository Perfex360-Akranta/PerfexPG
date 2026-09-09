package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlEvaluationdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, keva_keyid, kzncretriaid, kzncriteriaval, kznm_keyid, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public KznTlEvaluationdtl()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKedlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKedlKeyid(String kedlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kedlKeyid;
	}

	public String getKedlKevaKeyid() {
		return (String) saveArray[ tableFldConstants.keva_keyid.ordinal() ];
	}

	public void setKedlKevaKeyid(String kedlKevaKeyid) {
		saveArray[ tableFldConstants.keva_keyid.ordinal() ] = kedlKevaKeyid;
	}

	public String getKedlKzncretriaid() {
		return (String) saveArray[ tableFldConstants.kzncretriaid.ordinal() ];
	}

	public void setKedlKzncretriaid(String kedlKzncretriaid) {
		saveArray[ tableFldConstants.kzncretriaid.ordinal() ] = kedlKzncretriaid;
	}

	public String getKedlKzncriteriaval() {
		return (String) saveArray[ tableFldConstants.kzncriteriaval.ordinal() ];
	}

	public void setKedlKzncriteriaval(String kedlKzncriteriaval) {
		saveArray[ tableFldConstants.kzncriteriaval.ordinal() ] = kedlKzncriteriaval;
	}

	public String getKedlKznmKeyid() {
		return (String) saveArray[ tableFldConstants.kznm_keyid.ordinal() ];
	}

	public void setKedlKznmKeyid(String kedlKznmKeyid) {
		saveArray[ tableFldConstants.kznm_keyid.ordinal() ] = kedlKznmKeyid;
	}

	public String getKedlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKedlTempfield2(String kedlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kedlTempfield2;
	}

	public String getKedlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKedlTempfield3(String kedlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kedlTempfield3;
	}

	public String getKedlTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKedlTempfield4(String kedlTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kedlTempfield4;
	}

	public String getKedlTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKedlTempfield5(String kedlTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kedlTempfield5;
	}

	public String getKedlTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setKedlTempfield6(String kedlTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = kedlTempfield6;
	}

	public String getKedlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKedlActive(String kedlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kedlActive;
	}

	public String getKedlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKedlCreatedby(String kedlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kedlCreatedby;
	}

	public String getKedlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKedlCreatedon(String kedlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kedlCreatedon;
	}

	public String getKedlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKedlModifiedon(String kedlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kedlModifiedon;
	}

}

