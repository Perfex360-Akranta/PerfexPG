package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_PlmTlMethodtasklist {

	private  Object [] saveArray = null;  
	private String idMinVal;
	private String idMaxVal;
	private String iddirectVal;
	private String idCriteria;
    List<BAL_PlmTlMethodtasklist> plmTlMethodtasklist; 
   
	public enum   tableFldConstants
	{
		keyid, machineid, operation, checkingtool, idealcondition, typeofcheck
		, actualcondition
	}

	public BAL_PlmTlMethodtasklist()
	{
		saveArray = new  Object [ 7 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMtskKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMtskKeyid(String mtskKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mtskKeyid;
	}

	public String getMtskMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMtskMachineid(String mtskMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = mtskMachineid;
	}

	public String getMtskOperation() {
		return (String) saveArray[ tableFldConstants.operation.ordinal() ];
	}

	public void setMtskOperation(String mtskOperation) {
		saveArray[ tableFldConstants.operation.ordinal() ] = mtskOperation;
	}

	public String getMtskCheckingtool() {
		return (String) saveArray[ tableFldConstants.checkingtool.ordinal() ];
	}

	public void setMtskCheckingtool(String mtskCheckingtool) {
		saveArray[ tableFldConstants.checkingtool.ordinal() ] = mtskCheckingtool;
	}

	public String getMtskIdealcondition() {
		return (String) saveArray[ tableFldConstants.idealcondition.ordinal() ];
	}

	public void setMtskIdealcondition(String mtskIdealcondition) {
		saveArray[ tableFldConstants.idealcondition.ordinal() ] = mtskIdealcondition;
	}

	public String getMtskTypeofcheck() {
		return (String) saveArray[ tableFldConstants.typeofcheck.ordinal() ];
	}

	public void setMtskTypeofcheck(String mtskTypeofcheck) {
		saveArray[ tableFldConstants.typeofcheck.ordinal() ] = mtskTypeofcheck;
	}

	public String getMtskActualcondition() {
		return (String) saveArray[ tableFldConstants.actualcondition.ordinal() ];
	}

	public void setMtskActualcondition(String mtskActualcondition) {
		saveArray[ tableFldConstants.actualcondition.ordinal() ] = mtskActualcondition;
	}

	public String getIdMinVal() {
		return idMinVal;
	}

	public void setIdMinVal(String idMinVal) {
		this.idMinVal = idMinVal;
	}

	public String getIdMaxVal() {
		return idMaxVal;
	}

	public void setIdMaxVal(String idMaxVal) {
		this.idMaxVal = idMaxVal;
	}

	public String getIddirectVal() {
		return iddirectVal;
	}

	public void setIddirectVal(String iddirectVal) {
		this.iddirectVal = iddirectVal;
	}

	public String getIdCriteria() {
		return idCriteria;
	}

	public void setIdCriteria(String idCriteria) {
		this.idCriteria = idCriteria;
	}
	 public void setPlmTlMethodtasklist(List <BAL_PlmTlMethodtasklist> plmTlMethodtasklist) {
			this.plmTlMethodtasklist = plmTlMethodtasklist;
	}

	public List <BAL_PlmTlMethodtasklist> getPlmTlMethodtasklist() {
		return plmTlMethodtasklist;
	}

}

