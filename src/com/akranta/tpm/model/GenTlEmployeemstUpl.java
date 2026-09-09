package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlEmployeemstUpl {

	private  Object [] saveArray = null;  
	private String excelName;
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	private GenTlFunctionallocn genTlFunctionallocn;
	public enum   tableFldConstants
	{
		keyid,excelrowno,name,code,employeetype, employeenumber,companyid,companycode,locationid,locationcode,cellid,
		sectionid,loginid,defaultpassword,roleid1,roleid2,roleid3,roleid4,roleid5,roleid6,
		roleid7,roleid8,roleid9,roleid10,joineddate,departmentid,departmentcode
		,designationid,designationcode,factoryid, isshiftincharge, iscellmanager
		,tradeid, extensionphone,gender, mobile, email, personalinfo
		,remarks, issectionmanager, skillcategory, gradeid, isoperator
		,sbuid, embmenablemail, active, createdby, createdon, modifiedon,errorflag,errormsg
	}

	public GenTlEmployeemstUpl()
	{
		saveArray = new  Object [ 51 ];
	}

	
	public Object[] getSaveArray() {
		return saveArray;
	}
	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	public String getEmpuKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEmpuKeyid(String EmpuKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = EmpuKeyid;
	}
	public String getEmpuExcelrowno() {
		return (String) saveArray[ tableFldConstants.excelrowno.ordinal() ];
	}

	public void setEmpuExcelrowno(String EmpuExcelrowno) {
		saveArray[ tableFldConstants.excelrowno.ordinal() ] = EmpuExcelrowno;
	}

	public String getEmpuName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setEmpuName(String EmpuName) {
		saveArray[ tableFldConstants.name.ordinal() ] = EmpuName;
	}

	public String getEmpuCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setEmpuCode(String EmpuCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = EmpuCode;
	}

	public String getEmpuEmployeetype() {
		return (String) saveArray[ tableFldConstants.employeetype.ordinal() ];
	}

	public void setEmpuEmployeetype(String EmpuEmployeetype) {
		saveArray[ tableFldConstants.employeetype.ordinal() ] = EmpuEmployeetype;
	}

	public String getEmpuEmployeenumber() {
		return (String) saveArray[ tableFldConstants.employeenumber.ordinal() ];
	}

	public void setEmpuEmployeenumber(String EmpuEmployeenumber) {
		saveArray[ tableFldConstants.employeenumber.ordinal() ] = EmpuEmployeenumber;
	}
	
	public String getEmpuCompanyid() {
		return (String) saveArray[ tableFldConstants.companyid.ordinal() ];
	}

	public void setEmpuCompanyid(String EmpuCompanyid) {
		saveArray[ tableFldConstants.companyid.ordinal() ] = EmpuCompanyid;
	}
	public String getEmpuCompanycode() {
		return (String) saveArray[ tableFldConstants.companycode.ordinal() ];
	}

	public void setEmpuCompanycode(String EmpuCompanycode) {
		saveArray[ tableFldConstants.companycode.ordinal() ] = EmpuCompanycode;
	}

	public String getEmpuLocationid() {
		return (String) saveArray[ tableFldConstants.locationid.ordinal() ];
	}

	public void setEmpuLocationid(String EmpuLocationid) {
		saveArray[ tableFldConstants.locationid.ordinal() ] = EmpuLocationid;
	}
	
	public String getEmpuLocationcode() {
		return (String) saveArray[ tableFldConstants.locationcode.ordinal() ];
	}

	public void setEmpuLocationcode(String EmpuLocationcode) {
		saveArray[ tableFldConstants.locationcode.ordinal() ] = EmpuLocationcode;
	}
	
	public String getEmpuCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setEmpuCellid(String EmpuCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = EmpuCellid;
	}

	public String getEmpuSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setEmpuSectionid(String EmpuSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = EmpuSectionid;
	}

	public String getEmpuLoginid() {
		return (String) saveArray[ tableFldConstants.loginid.ordinal() ];
	}

	public void setEmpuLoginid(String EmpuLoginid) {
		saveArray[ tableFldConstants.loginid.ordinal() ] = EmpuLoginid;
	}

	public String getEmpuDefaultpassword() {
		return (String) saveArray[ tableFldConstants.defaultpassword.ordinal() ];
	}

	public void setEmpuDefaultpassword(String EmpuDefaultpassword) {
		saveArray[ tableFldConstants.defaultpassword.ordinal() ] = EmpuDefaultpassword;
	}
	

	public String getEmpuRoleid1() {
		return (String) saveArray[ tableFldConstants.roleid1.ordinal() ];
	}

	public void setEmpuRoleid1(String EmpuEmpuRoleid1) {
		saveArray[ tableFldConstants.roleid1.ordinal() ] = EmpuEmpuRoleid1;
	}
	
	public String getEmpuRoleid2() {
		return (String) saveArray[ tableFldConstants.roleid2.ordinal() ];
	}

	public void setEmpuRoleid2(String EmpuEmpuRoleid2) {
		saveArray[ tableFldConstants.roleid2.ordinal() ] = EmpuEmpuRoleid2;
	}
	
	public String getEmpuRoleid3() {
		return (String) saveArray[ tableFldConstants.roleid3.ordinal() ];
	}

	public void setEmpuRoleid3(String EmpuEmpuRoleid3) {
		saveArray[ tableFldConstants.roleid3.ordinal() ] = EmpuEmpuRoleid3;
	}
	
	public String getEmpuRoleid4() {
		return (String) saveArray[ tableFldConstants.roleid4.ordinal() ];
	}

	public void setEmpuRoleid4(String EmpuEmpuRoleid4) {
		saveArray[ tableFldConstants.roleid4.ordinal() ] = EmpuEmpuRoleid4;
	}
	
	public String getEmpuRoleid5() {
		return (String) saveArray[ tableFldConstants.roleid5.ordinal() ];
	}

	public void setEmpuRoleid5(String EmpuEmpuRoleid5) {
		saveArray[ tableFldConstants.roleid5.ordinal() ] = EmpuEmpuRoleid5;
	}
	
	public String getEmpuRoleid6() {
		return (String) saveArray[ tableFldConstants.roleid6.ordinal() ];
	}

	public void setEmpuRoleid6(String EmpuEmpuRoleid6) {
		saveArray[ tableFldConstants.roleid6.ordinal() ] = EmpuEmpuRoleid6;
	}

	public String getEmpuRoleid7() {
		return (String) saveArray[ tableFldConstants.roleid7.ordinal() ];
	}

	public void setEmpuRoleid7(String EmpuEmpuRoleid7) {
		saveArray[ tableFldConstants.roleid7.ordinal() ] = EmpuEmpuRoleid7;
	}
	
	public String getEmpuRoleid8() {
		return (String) saveArray[ tableFldConstants.roleid8.ordinal() ];
	}

	public void setEmpuRoleid8(String EmpuEmpuRoleid8) {
		saveArray[ tableFldConstants.roleid8.ordinal() ] = EmpuEmpuRoleid8;
	}
	
	public String getEmpuRoleid9() {
		return (String) saveArray[ tableFldConstants.roleid9.ordinal() ];
	}

	public void setEmpuRoleid9(String EmpuEmpuRoleid9) {
		saveArray[ tableFldConstants.roleid9.ordinal() ] = EmpuEmpuRoleid9;
	}
	
	public String getEmpuRoleid10() {
		return (String) saveArray[ tableFldConstants.roleid10.ordinal() ];
	}

	public void setEmpuRoleid10(String EmpuEmpuRoleid10) {
		saveArray[ tableFldConstants.roleid10.ordinal() ] = EmpuEmpuRoleid10;
	}


	public String getEmpuJoineddate() {
		return (String) saveArray[ tableFldConstants.joineddate.ordinal() ];
	}

	public void setEmpuJoineddate(String EmpuJoineddate) {
		saveArray[ tableFldConstants.joineddate.ordinal() ] = EmpuJoineddate;
	}

	public String getEmpuDepartmentid() {
		return (String) saveArray[ tableFldConstants.departmentid.ordinal() ];
	}

	public void setEmpuDepartmentid(String EmpuDepartmentid) {
		saveArray[ tableFldConstants.departmentid.ordinal() ] = EmpuDepartmentid;
	}
	
	public String getEmpuDepartmentcode() {
		return (String) saveArray[ tableFldConstants.departmentcode.ordinal() ];
	}

	public void setEmpuDepartmentcode(String EmpuDepartmentcode) {
		saveArray[ tableFldConstants.departmentid.ordinal() ] = EmpuDepartmentcode;
	}


	public String getEmpuDesignationid() {
		return (String) saveArray[ tableFldConstants.designationid.ordinal() ];
	}

	public void setEmpuDesignationid(String EmpuDesignationid) {
		saveArray[ tableFldConstants.designationid.ordinal() ] = EmpuDesignationid;
	}
	
	public String getEmpuDesignationcode() {
		return (String) saveArray[ tableFldConstants.designationcode.ordinal() ];
	}

	public void setEmpuDesignationcode(String EmpuDesignationcode) {
		saveArray[ tableFldConstants.designationcode.ordinal() ] = EmpuDesignationcode;
	}

	public String getEmpuFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setEmpuFactoryid(String EmpuFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = EmpuFactoryid;
	}

	public String getEmpuIsshiftincharge() {
		return (String) saveArray[ tableFldConstants.isshiftincharge.ordinal() ];
	}

	public void setEmpuIsshiftincharge(String EmpuIsshiftincharge) {
		saveArray[ tableFldConstants.isshiftincharge.ordinal() ] = EmpuIsshiftincharge;
	}

	public String getEmpuIscellmanager() {
		return (String) saveArray[ tableFldConstants.iscellmanager.ordinal() ];
	}

	public void setEmpuIscellmanager(String EmpuIscellmanager) {
		saveArray[ tableFldConstants.iscellmanager.ordinal() ] = EmpuIscellmanager;
	}


	public String getEmpuTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setEmpuTradeid(String EmpuTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = EmpuTradeid;
	}

	public String getEmpuExtensionphone() {
		return (String) saveArray[ tableFldConstants.extensionphone.ordinal() ];
	}

	public void setEmpuExtensionphone(String EmpuExtensionphone) {
		saveArray[ tableFldConstants.extensionphone.ordinal() ] = EmpuExtensionphone;
	}

	public String getEmpuGender() {
		return (String) saveArray[ tableFldConstants.gender.ordinal() ];
	}

	public void setEmpuGender(String EmpuGender) {
		saveArray[ tableFldConstants.gender.ordinal() ] = EmpuGender;
	}

	public String getEmpuMobile() {
		return (String) saveArray[ tableFldConstants.mobile.ordinal() ];
	}

	public void setEmpuMobile(String EmpuMobile) {
		saveArray[ tableFldConstants.mobile.ordinal() ] = EmpuMobile;
	}

	public String getEmpuEmail() {
		return (String) saveArray[ tableFldConstants.email.ordinal() ];
	}

	public void setEmpuEmail(String EmpuEmail) {
		saveArray[ tableFldConstants.email.ordinal() ] = EmpuEmail;
	}

	public String getEmpuPersonalinfo() {
		return (String) saveArray[ tableFldConstants.personalinfo.ordinal() ];
	}

	public void setEmpuPersonalinfo(String EmpuPersonalinfo) {
		saveArray[ tableFldConstants.personalinfo.ordinal() ] = EmpuPersonalinfo;
	}

	public String getEmpuRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setEmpuRemarks(String EmpuRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = EmpuRemarks;
	}

	public String getEmpuIssectionmanager() {
		return (String) saveArray[ tableFldConstants.issectionmanager.ordinal() ];
	}

	public void setEmpuIssectionmanager(String EmpuIssectionmanager) {
		saveArray[ tableFldConstants.issectionmanager.ordinal() ] = EmpuIssectionmanager;
	}

	public String getEmpuSkillcategory() {
		return (String) saveArray[ tableFldConstants.skillcategory.ordinal() ];
	}

	public void setEmpuSkillcategory(String EmpuSkillcategory) {
		saveArray[ tableFldConstants.skillcategory.ordinal() ] = EmpuSkillcategory;
	}

	public String getEmpuGradeid() {
		return (String) saveArray[ tableFldConstants.gradeid.ordinal() ];
	}

	public void setEmpuGradeid(String EmpuGradeid) {
		saveArray[ tableFldConstants.gradeid.ordinal() ] = EmpuGradeid;
	}

	public String getEmpuIsoperator() {
		return (String) saveArray[ tableFldConstants.isoperator.ordinal() ];
	}

	public void setEmpuIsoperator(String EmpuIsoperator) {
		saveArray[ tableFldConstants.isoperator.ordinal() ] = EmpuIsoperator;
	}

	public String getEmpuSbuId() {
		return (String) saveArray[ tableFldConstants.sbuid.ordinal() ];
	}

	public void setEmpuSbuId(String EmpuSbuId) {
		saveArray[ tableFldConstants.sbuid.ordinal() ] = EmpuSbuId;
	}

	public String getEmpuenablemail() {
		return (String) saveArray[ tableFldConstants.embmenablemail.ordinal() ];
	}

	public void setEmpuenablemail(String embmenablemail) {
		saveArray[ tableFldConstants.embmenablemail.ordinal() ] = embmenablemail;
	}


	public String getEmpuActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEmpuActive(String EmpuActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = EmpuActive;
	}

	public String getEmpuCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEmpuCreatedby(String EmpuCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = EmpuCreatedby;
	}

	public String getEmpuCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEmpuCreatedon(String EmpuCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = EmpuCreatedon;
	}

	public String getEmpuModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}
	public void setEmpuModifiedon(String EmpuModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = EmpuModifiedon;
	}
	
	public String getEmpuErrorflag() {
		return (String) saveArray[ tableFldConstants.errorflag.ordinal() ];
	}
	public void setEmpuErrorflag(String EmpuErrorflag) {
		saveArray[ tableFldConstants.errorflag.ordinal() ] = EmpuErrorflag;
	}
	
	public String getEmpuErrormsg() {
		return (String) saveArray[ tableFldConstants.errormsg.ordinal() ];
	}
	public void setEmpuErrormsg(String EmpuErrormsg) {
		saveArray[ tableFldConstants.errormsg.ordinal() ] = EmpuErrormsg;
	}
	

	public GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}
	public static String getMenuList(String RoleId,String MenuId,String RootId) {
		// TODO Auto-generated method stub
		  StringBuilder sb=new StringBuilder();
	if(RoleId==null){
			CommonMessage.debugMsg("If");
		  sb.append("  SELECT decode( ARML_ROLEID,'','N','Y'),MENUNO AS MENUNO, PARENTNO AS PARENTNO, ");
			  sb.append(" ROOTID AS ROOTID,ROOTNAME AS ROOTNAME,PARENTNAME AS PARENTNAME, MENUNAME,MENUPATH ");
			  sb.append(" FROM ADM_VW_MENULIST,ADM_TL_ROLE_MENU_LINK WHERE ACTIVE='Y' and MENUNO=ARML_MENUID(+)");
		}
		else{
			CommonMessage.debugMsg("Else");
			  sb.append(" SELECT decode( ARML_ROLEID,'','','Y'),MENUNO AS MENUNO, PARENTNO AS PARENTNO, ");
			  sb.append(" ROOTID AS ROOTID,ROOTNAME AS ROOTNAME,PARENTNAME AS PARENTNAME, MENUNAME,MENUPATH as MENUPATH");
			  sb.append(" FROM ADM_VW_MENULIST,ADM_TL_ROLE_MENU_LINK WHERE ACTIVE='Y' and MENUNO=ARML_MENUID(+) and ARML_ROLEID(+)='"+RoleId+"'  ");	
	if(MenuId.length()>=1){ 
		
		CommonMessage.debugMsg("If"+MenuId);
		sb.append(" and MENUNO='"+MenuId+"'  ");	

	}
	if(RootId.length()>=1){
		CommonMessage.debugMsg("If RootId"+RootId);
		sb.append(" and ROOTID='"+RootId+"'  ");	

	}
		}
	     CommonMessage.debugMsg("The Final Data"+sb.toString());
	     return sb.toString();
	}	
	
}

