package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SheTlAccinvestigationmst {

	private  Object [] saveArray = null;  
	private List<SheTlAccinvestigationdtl>  listSheTlAccinvestigationdtl;

	public enum   tableFldConstants
	{
		keyid, employee, pnocontractor, accdate, acctime, acclocation
		, department, bodypartaffected, natureofinjury, accdescription
		, treatment, shiftincharge, operators, findings, immediatecause
		, rootcause, responsibility, flid, elementid, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, active, createdby
		, createdon, modifiedon
	}

	public SheTlAccinvestigationmst()
	{
		saveArray = new  Object [ 28 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getAinmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAinmKeyid(String ainmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ainmKeyid;
	}

	public String getAinmEmployee() {
		return (String) saveArray[ tableFldConstants.employee.ordinal() ];
	}

	public void setAinmEmployee(String ainmEmployee) {
		saveArray[ tableFldConstants.employee.ordinal() ] = ainmEmployee;
	}

	public String getAinmPnocontractor() {
		return (String) saveArray[ tableFldConstants.pnocontractor.ordinal() ];
	}

	public void setAinmPnocontractor(String ainmPnocontractor) {
		saveArray[ tableFldConstants.pnocontractor.ordinal() ] = ainmPnocontractor;
	}

	public String getAinmAccdate() {
		return (String) saveArray[ tableFldConstants.accdate.ordinal() ];
	}

	public void setAinmAccdate(String ainmAccdate) {
		saveArray[ tableFldConstants.accdate.ordinal() ] = ainmAccdate;
	}

	public String getAinmAcctime() {
		return (String) saveArray[ tableFldConstants.acctime.ordinal() ];
	}

	public void setAinmAcctime(String ainmAcctime) {
		saveArray[ tableFldConstants.acctime.ordinal() ] = ainmAcctime;
	}

	public String getAinmAcclocation() {
		return (String) saveArray[ tableFldConstants.acclocation.ordinal() ];
	}

	public void setAinmAcclocation(String ainmAcclocation) {
		saveArray[ tableFldConstants.acclocation.ordinal() ] = ainmAcclocation;
	}

	public String getAinmDepartment() {
		return (String) saveArray[ tableFldConstants.department.ordinal() ];
	}

	public void setAinmDepartment(String ainmDepartment) {
		saveArray[ tableFldConstants.department.ordinal() ] = ainmDepartment;
	}

	public String getAinmBodypartaffected() {
		return (String) saveArray[ tableFldConstants.bodypartaffected.ordinal() ];
	}

	public void setAinmBodypartaffected(String ainmBodypartaffected) {
		saveArray[ tableFldConstants.bodypartaffected.ordinal() ] = ainmBodypartaffected;
	}

	public String getAinmNatureofinjury() {
		return (String) saveArray[ tableFldConstants.natureofinjury.ordinal() ];
	}

	public void setAinmNatureofinjury(String ainmNatureofinjury) {
		saveArray[ tableFldConstants.natureofinjury.ordinal() ] = ainmNatureofinjury;
	}

	public String getAinmAccdescription() {
		return (String) saveArray[ tableFldConstants.accdescription.ordinal() ];
	}

	public void setAinmAccdescription(String ainmAccdescription) {
		saveArray[ tableFldConstants.accdescription.ordinal() ] = ainmAccdescription;
	}

	public String getAinmTreatment() {
		return (String) saveArray[ tableFldConstants.treatment.ordinal() ];
	}

	public void setAinmTreatment(String ainmTreatment) {
		saveArray[ tableFldConstants.treatment.ordinal() ] = ainmTreatment;
	}

	public String getAinmShiftincharge() {
		return (String) saveArray[ tableFldConstants.shiftincharge.ordinal() ];
	}

	public void setAinmShiftincharge(String ainmShiftincharge) {
		saveArray[ tableFldConstants.shiftincharge.ordinal() ] = ainmShiftincharge;
	}

	public String getAinmOperators() {
		return (String) saveArray[ tableFldConstants.operators.ordinal() ];
	}

	public void setAinmOperators(String ainmOperators) {
		saveArray[ tableFldConstants.operators.ordinal() ] = ainmOperators;
	}

	public String getAinmFindings() {
		return (String) saveArray[ tableFldConstants.findings.ordinal() ];
	}

	public void setAinmFindings(String ainmFindings) {
		saveArray[ tableFldConstants.findings.ordinal() ] = ainmFindings;
	}

	public String getAinmImmediatecause() {
		return (String) saveArray[ tableFldConstants.immediatecause.ordinal() ];
	}

	public void setAinmImmediatecause(String ainmImmediatecause) {
		saveArray[ tableFldConstants.immediatecause.ordinal() ] = ainmImmediatecause;
	}

	public String getAinmRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setAinmRootcause(String ainmRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = ainmRootcause;
	}

	public String getAinmResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setAinmResponsibility(String ainmResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = ainmResponsibility;
	}

	public String getAinmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setAinmFlid(String ainmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = ainmFlid;
	}

	public String getAinmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setAinmElementid(String ainmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = ainmElementid;
	}

	public String getAinmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAinmTempfield3(String ainmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = ainmTempfield3;
	}

	public String getAinmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setAinmTempfield4(String ainmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = ainmTempfield4;
	}

	public String getAinmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setAinmTempfield5(String ainmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = ainmTempfield5;
	}

	public String getAinmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setAinmTempfield6(String ainmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = ainmTempfield6;
	}

	public String getAinmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setAinmTempfield7(String ainmTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = ainmTempfield7;
	}

	public String getAinmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAinmActive(String ainmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ainmActive;
	}

	public String getAinmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAinmCreatedby(String ainmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ainmCreatedby;
	}

	public String getAinmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAinmCreatedon(String ainmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ainmCreatedon;
	}

	public String getAinmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAinmModifiedon(String ainmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ainmModifiedon;
	}

	public void setSheTlAccinvestigationdtl(List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl) {
		this.listSheTlAccinvestigationdtl = listSheTlAccinvestigationdtl;
	}

	public List<SheTlAccinvestigationdtl> getSheTlAccinvestigationdtl() {
		return listSheTlAccinvestigationdtl;
	}

	public void setSaveArray(Object[] SaveArray) {
		this.saveArray=SaveArray;
	}

}

