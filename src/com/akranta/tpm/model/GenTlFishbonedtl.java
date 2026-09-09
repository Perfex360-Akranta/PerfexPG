package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlFishbonedtl {

	private  Object [] saveArray = null;  
	private String FismParent;
	

	public enum   tableFldConstants
	{
		keyid, fism_keyid, cause, parentid, orderno, levelno, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon, remarks
	}

	public GenTlFishbonedtl()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFisdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFisdKeyid(String fisdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fisdKeyid;
	}

	public String getFisdFismKeyid() {
		return (String) saveArray[ tableFldConstants.fism_keyid.ordinal() ];
	}

	public void setFisdFismKeyid(String fisdFismKeyid) {
		saveArray[ tableFldConstants.fism_keyid.ordinal() ] = fisdFismKeyid;
	}

	public String getFisdCause() {
		return (String) saveArray[ tableFldConstants.cause.ordinal() ];
	}

	public void setFisdCause(String fisdCause) {
		saveArray[ tableFldConstants.cause.ordinal() ] = fisdCause;
	}

	public String getFisdParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setFisdParentid(String fisdParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = fisdParentid;
	}

	public String getFisdOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setFisdOrderno(String fisdOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = fisdOrderno;
	}

	public String getFisdLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setFisdLevelno(String fisdLevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = fisdLevelno;
	}

	public String getFisdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFisdTempfield1(String fisdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fisdTempfield1;
	}

	public String getFisdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFisdTempfield2(String fisdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fisdTempfield2;
	}

	public String getFisdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFisdTempfield3(String fisdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fisdTempfield3;
	}

	public String getFisdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFisdTempfield4(String fisdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = fisdTempfield4;
	}

	public String getFisdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFisdTempfield5(String fisdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fisdTempfield5;
	}

	public String getFisdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFisdActive(String fisdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fisdActive;
	}

	public String getFisdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFisdCreatedby(String fisdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fisdCreatedby;
	}

	public String getFisdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFisdCreatedon(String fisdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fisdCreatedon;
	}

	public String getFisdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFisdModifiedon(String fisdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fisdModifiedon;
	}
    public String getFisdRemarks() {
        return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
    }

    public void setFisdRemarks(String fisdRemarks) {
        saveArray[ tableFldConstants.remarks.ordinal() ] = fisdRemarks;
    }


	public void setFismParent(String fismParent) {
		FismParent = fismParent;
	}

	public String getFismParent() {
		return FismParent;
	}

}

