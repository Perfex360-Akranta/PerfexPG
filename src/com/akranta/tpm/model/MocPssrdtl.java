package com.akranta.tpm.model;


public class MocPssrdtl {

	private  Object [] saveArray = null; 
	private MocPssrmst mocPssrmst;
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		keyid,masterid,mocid,rownum,qstnid,observation,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public MocPssrdtl()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getPsrdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPsrdKeyid(String psrdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = psrdKeyid;
	}
	

	public String getPsrdmasterid() {
		return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
	}

	public void setPsrdmasterid(String psrdmasterid) {
		saveArray[ tableFldConstants.masterid.ordinal() ] =psrdmasterid;
	}
	public String getPsrdMocid() {
		return (String) saveArray[ tableFldConstants.mocid.ordinal() ];
	}

	public void setPsrdMocid(String psrdMocid) {
		saveArray[ tableFldConstants.mocid.ordinal() ] =psrdMocid;
	}
	
	public String getPsrdrownum() {
		return (String) saveArray[ tableFldConstants.rownum.ordinal() ];
	}

	public void setPsrdrownum(String psrdrownum) {
		saveArray[ tableFldConstants.rownum.ordinal() ] =psrdrownum;
	} 
	
	public String getPsrdQstnid() {
		return (String) saveArray[ tableFldConstants.qstnid.ordinal() ];
	}

	public void setPsrdQstnid(String qsrdQstnid) {
		saveArray[ tableFldConstants.qstnid.ordinal() ] =qsrdQstnid;
	} 

	public String getPsrdobsrv() {
		return (String) saveArray[ tableFldConstants.observation.ordinal() ];
	}

	public void setPsrdobsrv(String psrdobsrv) {
		saveArray[ tableFldConstants.observation.ordinal() ] =psrdobsrv;
	} 


	public String getPsrdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}
	
	public void setPsrdTempfield1(String psrdTempfield1){
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = psrdTempfield1;
	} 
  
	public String getPsrdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}
	
	public void setPsrdTempfield2(String psrdTempfield2){
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = psrdTempfield2;
	} 
  
	public String getPsrdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}
	
	public void setPsrdTempfield3(String psrdTempfield3){
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = psrdTempfield3;
	}
 
	public String getPsrdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}
	
	public void setPsrdTempfield4(String psrdTempfield4){
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = psrdTempfield4;
	} 
  
	public String getPsrdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}
	
	public void setPsrdTempfield5(String psrdTempfield5){
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = psrdTempfield5;
	} 
  
   public String getPsrdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}
	public void setPsrdCreatedby(String psrdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =psrdCreatedby;
	}
	
	public String getPsrdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPsrdActive(String psrdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] =psrdActive;
	}

	public String getPsrdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPsrdCreatedon(String psrdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =psrdCreatedon;
	}
	
	public String getPsrdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPsrdModifiedon(String psrdModifiedby) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =psrdModifiedby;
	}
	

	public void setPsrdPssrmst(MocPssrmst mocPssrmst) {
		this.mocPssrmst = mocPssrmst;
	}

	public MocPssrmst getPsrdPssrmst() {
		return mocPssrmst;
	} 

}

