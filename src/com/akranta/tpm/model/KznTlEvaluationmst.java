package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlEvaluationmst {

	private  Object [] saveArray = null; 
	private String detail=new String(); 
	private List<KznTlEvaluationdtl> kznTlEvaluationdtl ;

	public enum   tableFldConstants
	{
		keyid, kaizenid, flid, employeeid, date, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public KznTlEvaluationmst()
	{
		setKznTlEvaluationdtl(new ArrayList<KznTlEvaluationdtl> ());
		saveArray = new  Object [ 15 ];
	}
	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getKevaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKevaKeyid(String kevaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kevaKeyid;
	}

	public String getKevaKaizenid() {
		return (String) saveArray[ tableFldConstants.kaizenid.ordinal() ];
	}

	public void setKevaKaizenid(String kevaKaizenid) {
		saveArray[ tableFldConstants.kaizenid.ordinal() ] = kevaKaizenid;
	}

	public String getKevaFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setKevaFlid(String kevaFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = kevaFlid;
	}

	public String getKevaEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setKevaEmployeeid(String kevaEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = kevaEmployeeid;
	}

	public String getKevaDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setKevaDate(String kevaDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = kevaDate;
	}

	public String getKevaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKevaTempfield1(String kevaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kevaTempfield1;
	}

	public String getKevaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKevaTempfield2(String kevaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kevaTempfield2;
	}

	public String getKevaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKevaTempfield3(String kevaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kevaTempfield3;
	}

	public String getKevaTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKevaTempfield4(String kevaTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kevaTempfield4;
	}

	public String getKevaTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKevaTempfield5(String kevaTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kevaTempfield5;
	}

	public String getKevaTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setKevaTempfield6(String kevaTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = kevaTempfield6;
	}

	public String getKevaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKevaActive(String kevaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kevaActive;
	}

	public String getKevaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKevaCreatedby(String kevaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kevaCreatedby;
	}

	public String getKevaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKevaCreatedon(String kevaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kevaCreatedon;
	}

	public String getKevaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKevaModifiedon(String kevaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kevaModifiedon;
	}

	public void setKznTlEvaluationdtl(List<KznTlEvaluationdtl> kznTlEvaluationdtl) {
		this.kznTlEvaluationdtl = kznTlEvaluationdtl;
	}

	public List<KznTlEvaluationdtl> getKznTlEvaluationdtl() {
		return kznTlEvaluationdtl;
	}
	
	public String getKevaDetail() {
		return detail;
	}

	public void setKevaDetail(String kevaDetail) {
		this.detail = kevaDetail;
	}
}

