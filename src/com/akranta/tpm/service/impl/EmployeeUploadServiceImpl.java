package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;

import org.xml.sax.SAXParseException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EmployeeUploadDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.EmployeeUploadDaoImpl;
import com.akranta.tpm.model.AdmTlRoleMenuLink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeemstUpl;
import com.akranta.tpm.service.EmployeeUploadService;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EmployeeUploadServiceImpl implements EmployeeUploadService{

	private Validations validations ;
	DBActionTemplate  dbActionTemplate ;
	CommonFilterDao commonFilterDao;
	EmployeeUploadDao employeeUploadDao;
    public EmployeeUploadServiceImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate  =dbActionTemplate;
		validations = new Validations();
		commonFilterDao=new CommonFilterDaoImpl(dbActionTemplate);
		employeeUploadDao=new EmployeeUploadDaoImpl(dbActionTemplate);
}

   @Override
 public String populateTempTable(String excelFileName,
		GenTlEmployeemstUpl genTlEmployeemstUpl) throws UploadException, ValidationExceptions, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, IOException{
	try{
		
		validations.validate(genTlEmployeemstUpl,"pcsOtherLoss","create");
		
	}catch(ValidationExceptions e){
		throw new ValidationExceptions(e.getMessage());
	} catch (org.xml.sax.SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	fillValues(genTlEmployeemstUpl);
	return employeeUploadDao.populateTempTable( excelFileName,genTlEmployeemstUpl);
}
   
	public List<String[]> getEmployeeList(CommonFilter commonFilter)throws Exception{
		return employeeUploadDao.getEmployeeList(commonFilter);
	}
   
    public String EmployeeUploadValidate(String errflag) throws Exception{
    	return employeeUploadDao.EmployeeUploadValidate(errflag);
    }
    public String EmployeeUploadDelete(String errflag) throws Exception{
    	 return employeeUploadDao.EmployeeUploadDelete(errflag);
    }

    public String EmployeeCreate(String errflag) throws Exception{
    	return employeeUploadDao.EmployeeCreate(errflag);
    }

    public String ErrorFlagCount() throws Exception{
    	 return employeeUploadDao.ErrorFlagCount();
    }
 
private GenTlEmployeemstUpl fillValues(GenTlEmployeemstUpl genTlEmployeemstUpl) {
	String dateTime = CommonFunctions.dateTimeNow();
	
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuActive()))
		genTlEmployeemstUpl.setEmpuActive("Y");	
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuCreatedon()))
		genTlEmployeemstUpl.setEmpuCreatedon(dateTime);
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuModifiedon()))
		genTlEmployeemstUpl.setEmpuModifiedon(dateTime);
    if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuExcelrowno()))
    	genTlEmployeemstUpl.setEmpuExcelrowno("0");
    if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuErrorflag()))
    	genTlEmployeemstUpl.setEmpuErrorflag("N");
    if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuErrormsg()))
    	genTlEmployeemstUpl.setEmpuErrormsg("-");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuFactoryid())) 
	   genTlEmployeemstUpl.setEmpuFactoryid("-");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuCellid()))
	   genTlEmployeemstUpl.setEmpuCellid("-");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuSectionid()))
	   genTlEmployeemstUpl.setEmpuSectionid("-");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuGradeid()))
	   genTlEmployeemstUpl.setEmpuGradeid("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuIscellmanager()))
		genTlEmployeemstUpl.setEmpuIscellmanager("N");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuIsoperator()))
		genTlEmployeemstUpl.setEmpuIsoperator("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuIssectionmanager()))
		genTlEmployeemstUpl.setEmpuIssectionmanager("-");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuIsshiftincharge()))
	   genTlEmployeemstUpl.setEmpuIsshiftincharge("N");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuPersonalinfo()))
	   genTlEmployeemstUpl.setEmpuPersonalinfo("-");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuFactoryid()))
	   genTlEmployeemstUpl.setEmpuFactoryid("-");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRemarks()))
	   genTlEmployeemstUpl.setEmpuRemarks("-"); 
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuTradeid()))
	   genTlEmployeemstUpl.setEmpuTradeid("-");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuExtensionphone()))
	   genTlEmployeemstUpl.setEmpuExtensionphone("-");
   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuCompanyid()))
	   genTlEmployeemstUpl.setEmpuCompanyid("-");
    if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuDesignationid()))
    	genTlEmployeemstUpl.setEmpuDesignationid("-");
    if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuDepartmentid()))
    	genTlEmployeemstUpl.setEmpuDepartmentid("-");
    if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuLocationid()))
    	genTlEmployeemstUpl.setEmpuLocationid("-");
    
/*   if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuJoineddate()))
	   genTlEmployeemstUpl.setEmpuJoineddate(dateTime);*/
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid1()))
		genTlEmployeemstUpl.setEmpuRoleid1("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid2()))
		genTlEmployeemstUpl.setEmpuRoleid2("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid3()))
		genTlEmployeemstUpl.setEmpuRoleid3("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid4()))
		genTlEmployeemstUpl.setEmpuRoleid4("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid5()))
		genTlEmployeemstUpl.setEmpuRoleid5("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid6()))
		genTlEmployeemstUpl.setEmpuRoleid6("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid7()))
		genTlEmployeemstUpl.setEmpuRoleid7("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid8()))
		genTlEmployeemstUpl.setEmpuRoleid8("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid9()))
		genTlEmployeemstUpl.setEmpuRoleid9("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuRoleid10()))
		genTlEmployeemstUpl.setEmpuRoleid10("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuSbuId()))
		genTlEmployeemstUpl.setEmpuSbuId("-");
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuSkillcategory()))
		genTlEmployeemstUpl.setEmpuSkillcategory("-"); 
	if(!UIUtils.isValidKeyId(genTlEmployeemstUpl.getEmpuenablemail()))
		genTlEmployeemstUpl.setEmpuenablemail("-");
		
	return genTlEmployeemstUpl;
}


@Override
public void resetpwd(List<String> loginIds) throws Exception {
	
	employeeUploadDao.resetpwd(loginIds);
}

@Override
public List<String[]> ActiveMenuList(CommonFilter commonFilter,GridParams gridParams) throws Exception {
	// TODO Auto-generated method stub
	return employeeUploadDao.ActiveMenuList(commonFilter,gridParams);
}
public List<AdmTlRoleMenuLink> createBasis(List<AdmTlRoleMenuLink> employeeAddList, String mocKeyid) throws Exception{
	return employeeUploadDao.createBasis(employeeAddList,mocKeyid);
}

@Override
public List<ComboBox> getMenuList(ComboFilter combofilter) throws Exception {
	// TODO Auto-generated method stub
	String Table="ADM_VW_MENULIST";
	combofilter.setIdField("MENUNO");
	combofilter.setNameField("MENUNAME");
	//combofilter.setCondSql(" AND MENUNAME NOT IN ('-','') ");
		combofilter.setTableName(Table);
	return commonFilterDao.fillComboValues(combofilter);
}

@Override
public List<ComboBox> getTPMMenuPillar(ComboFilter combofilter)
		throws Exception {
	// TODO Auto-generated method stub
	String Table="ADM_VW_MENULIST";
	combofilter.setIdField("ROOTID");
	combofilter.setNameField("ROOTNAME");

combofilter.setCondSql("AND ROOTID NOT IN (1379,1473,322,358,1049,296,1130,1178,20)");

	combofilter.setTableName(Table);
	return commonFilterDao.fillComboValues(combofilter);
}
@Override
public List<String[]> getEmpData(GridParams gridParams,String location) throws Exception {
	// TODO Auto-generated method stub
	return employeeUploadDao.getEmpData(gridParams,location);
}	

@Override
public void EmpActive(List<String> EmpIds) throws Exception {
	
	employeeUploadDao.EmpActive(EmpIds);
}

@Override
public  void EmpInActive(List<String> loginId,String ValidTillDate) throws Exception{
	employeeUploadDao.EmpInActive(loginId,ValidTillDate);
}

public List<String[]> getDocData(String RefDocType) throws Exception{
	return employeeUploadDao.getDocData(RefDocType);
}
public List<ComboBox> getRefDocType(ComboFilter combofilter) throws Exception{
	String Table="GEN_TL_REFDOCTYPEMST";
	combofilter.setIdField("GRDT_KEYID");
	combofilter.setNameField("GRDT_NAME");
    combofilter.setTableName(Table);
	return commonFilterDao.fillComboValues(combofilter);
}

public List<ComboBox> getRefDocNo(ComboFilter combofilter) throws Exception{
	String Table="DCM_TL_DOCUMENTMANAGER";
	combofilter.setIdField("DMDM_KEYID");
	combofilter.setNameField("DMDM_REFDOCNO");
    combofilter.setTableName(Table);
	return commonFilterDao.fillComboValues(combofilter);	
}
public List<ComboBox> getKeyWords(ComboFilter combofilter) throws Exception{
	String Table="DCM_TL_DOCUMENTMANAGER";
	combofilter.setIdField("DMDM_KEYID");
	combofilter.setNameField("DMDM_KEYWORDS");
    combofilter.setTableName(Table);
	return commonFilterDao.fillComboValues(combofilter);	
}
public List<ComboBox> getDescription(ComboFilter combofilter) throws Exception{
	String Table="DCM_TL_DOCUMENTMANAGER";
	combofilter.setIdField("DMDM_KEYID");
	combofilter.setNameField("DMDM_DESCRIPTION");
    combofilter.setTableName(Table);
	return commonFilterDao.fillComboValues(combofilter);	
}


}
