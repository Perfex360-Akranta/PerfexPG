package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_SapTlMaintenanceOrderdtl {

	private  Object [] saveArray = null;  
	private String existDocNumber;
	private List <BAL_SapTlSparesreplaced> sparesSAPData;
    private String refdocid; 
	public enum   tableFldConstants
	{
		keyid, momskeyid, opno, controlkey, opdesc, work, workunit
		, duration, durationunit, cckey, fieldkey, checkingtool, idealcondn
		, typeofcheck, actualcondn, wbselement, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public BAL_SapTlMaintenanceOrderdtl()
	{
		saveArray = new  Object [ 26 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getModtKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setModtKeyid(String modtKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = modtKeyid;
	}

	public String getModtMomsKeyid() {
		return (String) saveArray[ tableFldConstants.momskeyid.ordinal() ];
	}

	public void setModtMomsKeyid(String modtMomsKeyid) {
		saveArray[ tableFldConstants.momskeyid.ordinal() ] = modtMomsKeyid;
	}
 
	public String getModtOpno() {
		return (String) saveArray[ tableFldConstants.opno.ordinal() ];
	}

	public void setModtOpno(String modtOpno) {
		saveArray[ tableFldConstants.opno.ordinal() ] = modtOpno;
	}

	public String getModtControlkey() {
		return (String) saveArray[ tableFldConstants.controlkey.ordinal() ];
	}

	public void setModtControlkey(String modtControlkey) {
		saveArray[ tableFldConstants.controlkey.ordinal() ] = modtControlkey;
	}

	public String getModtOpdesc() {
		return (String) saveArray[ tableFldConstants.opdesc.ordinal() ];
	}

	public void setModtOpdesc(String modtOpdesc) {
		saveArray[ tableFldConstants.opdesc.ordinal() ] = modtOpdesc;
	}

	public String getModtWork() {
		return (String) saveArray[ tableFldConstants.work.ordinal() ];
	}

	public void setModtWork(String modtWork) {
		saveArray[ tableFldConstants.work.ordinal() ] = modtWork;
	}

	public String getModtWorkunit() {
		return (String) saveArray[ tableFldConstants.workunit.ordinal() ];
	}

	public void setModtWorkunit(String modtWorkunit) {
		saveArray[ tableFldConstants.workunit.ordinal() ] = modtWorkunit;
	}

	public String getModtDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setModtDuration(String modtDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = modtDuration;
	}

	public String getModtDurationunit() {
		return (String) saveArray[ tableFldConstants.durationunit.ordinal() ];
	}

	public void setModtDurationunit(String modtDurationunit) {
		saveArray[ tableFldConstants.durationunit.ordinal() ] = modtDurationunit;
	}

	public String getModtCckey() {
		return (String) saveArray[ tableFldConstants.cckey.ordinal() ];
	}

	public void setModtCckey(String modtCckey) {
		saveArray[ tableFldConstants.cckey.ordinal() ] = modtCckey;
	}

	public String getModtFieldkey() {
		return (String) saveArray[ tableFldConstants.fieldkey.ordinal() ];
	}

	public void setModtFieldkey(String modtFieldkey) {
		saveArray[ tableFldConstants.fieldkey.ordinal() ] = modtFieldkey;
	}

	public String getModtCheckingtool() {
		return (String) saveArray[ tableFldConstants.checkingtool.ordinal() ];
	}

	public void setModtCheckingtool(String modtCheckingtool) {
		saveArray[ tableFldConstants.checkingtool.ordinal() ] = modtCheckingtool;
	}

	public String getModtIdealcondn() {
		return (String) saveArray[ tableFldConstants.idealcondn.ordinal() ];
	}

	public void setModtIdealcondn(String modtIdealcondn) {
		saveArray[ tableFldConstants.idealcondn.ordinal() ] = modtIdealcondn;
	}

	public String getModtTypeofcheck() {
		return (String) saveArray[ tableFldConstants.typeofcheck.ordinal() ];
	}

	public void setModtTypeofcheck(String modtTypeofcheck) {
		saveArray[ tableFldConstants.typeofcheck.ordinal() ] = modtTypeofcheck;
	}

	public String getModtActualcondn() {
		return (String) saveArray[ tableFldConstants.actualcondn.ordinal() ];
	}

	public void setModtActualcondn(String modtActualcondn) {
		saveArray[ tableFldConstants.actualcondn.ordinal() ] = modtActualcondn;
	}

	public String getModtWbselement() {
		return (String) saveArray[ tableFldConstants.wbselement.ordinal() ];
	}

	public void setModtWbselement(String modtWbselement) {
		saveArray[ tableFldConstants.wbselement.ordinal() ] = modtWbselement;
	}

	public String getModtTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setModtTempfield1(String modtTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = modtTempfield1;
	}

	public String getModtTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setModtTempfield2(String modtTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = modtTempfield2;
	}

	public String getModtTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setModtTempfield3(String modtTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = modtTempfield3;
	}

	public String getModtTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setModtTempfield4(String modtTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = modtTempfield4;
	}

	public String getModtTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setModtTempfield5(String modtTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = modtTempfield5;
	}

	public String getModtTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setModtTempfield6(String modtTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = modtTempfield6;
	}

	public String getModtActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setModtActive(String modtActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = modtActive;
	}

	public String getModtCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setModtCreatedby(String modtCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = modtCreatedby;
	}

	public String getModtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setModtCreatedon(String modtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = modtCreatedon;
	}

	public String getModtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setModtModifiedon(String modtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = modtModifiedon;
	}

	public String getExistDocNumber() {
		return existDocNumber;
	}

	public void setExistDocNumber(String existDocNumber) {
		this.existDocNumber = existDocNumber;
	}

	public List <BAL_SapTlSparesreplaced> getSparesSAPData() {
		return sparesSAPData;
	}

	public void setSparesSAPData(List <BAL_SapTlSparesreplaced> sparesSAPData) {
		this.sparesSAPData = sparesSAPData;
	}

	public String getRefdocid() {
		return refdocid;
	}

	public void setRefdocid(String refdocid) {
		this.refdocid = refdocid;
	}

}

