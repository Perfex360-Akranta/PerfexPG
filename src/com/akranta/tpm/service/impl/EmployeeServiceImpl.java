
package com.akranta.tpm.service.impl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlEmployeemstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlEmployeemstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.service.EmployeeService;
import com.akranta.tpm.service.api.EmployeeCreationServiceApi;
import com.akranta.tpm.service.api.EmployeeGroupServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;


public  class EmployeeServiceImpl implements EmployeeService {
	
private static String SEQGEN_EMPLOYEECODE_NUM="SEQGEN_EMPLOYEECODE_NUM";
private GenTlEmployeemstDao employeeDao;
private CommonFilterDao commonFilterDao;
private Validations validations ;
private DBActionTemplate dbActionTemplate;
EmployeeCreationServiceApi serviceApi;

	public EmployeeServiceImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		employeeDao =  new GenTlEmployeemstDaoImpl(dbActionTemplate);	
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setGenTlEmployeemstDao(GenTlEmployeemstDao employeeDao)
	{
		this.employeeDao = employeeDao;
	}
	
	public void EmployeeServiceImplJwt(String JwtToken){
    	try{
    		employeeDao.GenTlEmployeemstDaoImplJwt(JwtToken);
    		serviceApi = new EmployeeCreationServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }

	public GenTlEmployeemst create(GenTlEmployeemst newGenTlEmployeemst,GenTlEmployeemst oldGenTlEmployeemst,  EmployeeBean employeeBean) throws ValidationExceptions,Exception {

		try {
			
			String validationsFor;
			
			//if(employeeBean.getFormActionMode() != null && employeeBean.getFormActionMode().equals("emp") )
			//	validationsFor = "emp";
			//else
				validationsFor = "create";
			CommonMessage.debugMsg("Inside the Create Function");
			if(newGenTlEmployeemst.getEmpmEmployeetype().equals("C"))
			{
				if(newGenTlEmployeemst.getEmpmCode()==null)
					newGenTlEmployeemst.setEmpmCode(dbActionTemplate.getSequenceNumber(SEQGEN_EMPLOYEECODE_NUM,5,"C","",""));
				if(newGenTlEmployeemst.getEmpmEmployeenumber()==null)
					newGenTlEmployeemst.setEmpmEmployeenumber(newGenTlEmployeemst.getEmpmCode());
			}
			if(newGenTlEmployeemst.getEmpmEmployeenumber()==null)
				newGenTlEmployeemst.setEmpmEmployeenumber(newGenTlEmployeemst.getEmpmCode());
			validations.validate(newGenTlEmployeemst,"employee",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			List<GenTlEmployeedtl> employeedtls = newGenTlEmployeemst.getEmployeeDetail();
			for(GenTlEmployeedtl genTlEmployeedtl : employeedtls )
			{
				validations.validate(genTlEmployeedtl,"employee",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			}
			
			CommonMessage.debugMsg("After validation");
			fillValues(newGenTlEmployeemst,oldGenTlEmployeemst,employeeBean);
			
//			return employeeDao.create(newGenTlEmployeemst);
//			return serviceApi.saveEmployee(newGenTlEmployeemst);
			
			GenTlEmployeemst genTlEmployeemst = serviceApi.saveEmployee(newGenTlEmployeemst);
			newGenTlEmployeemst.setEmpmKeyid(genTlEmployeemst.getEmpmKeyid());
			employeeDao.insertEmployeeImage(newGenTlEmployeemst);
			return genTlEmployeemst;
			
		}catch (ValidationExceptions e){

			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
	
	public GenTlEmployeemst update(GenTlEmployeemst newGenTlEmployeemst,GenTlEmployeemst oldGenTlEmployeemst,
			EmployeeBean employeeBean) throws Exception 
	{
		CommonMessage.debugMsg("oldGenTlEmployeemst:::"+oldGenTlEmployeemst);
		CommonMessage.debugMsg("oldGenTlEmployeemst:::"+oldGenTlEmployeemst.getEmpmEmployeetype());
		String validationsFor = "create";
		CommonMessage.debugMsg("Inside the ServiceImpl u");
		
		//sriram below line comment 22-oct-2025
		//if(newGenTlEmployeemst.getEmpmEmployeetype().equals("C"))
		//sriram below if added 22-oct-2025
		if ("C".equals(newGenTlEmployeemst.getEmpmEmployeetype())) 
		{
			if(newGenTlEmployeemst.getEmpmCode()==null)
				newGenTlEmployeemst.setEmpmCode(dbActionTemplate.getSequenceNumber(SEQGEN_EMPLOYEECODE_NUM,5,"C","",""));
			if(newGenTlEmployeemst.getEmpmEmployeenumber()==null)
				newGenTlEmployeemst.setEmpmEmployeenumber(newGenTlEmployeemst.getEmpmCode());
		}
		if(newGenTlEmployeemst.getEmpmEmployeenumber()==null)
			newGenTlEmployeemst.setEmpmEmployeenumber(newGenTlEmployeemst.getEmpmCode());
		
		
		validations.validate(newGenTlEmployeemst,"employee","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		List<GenTlEmployeedtl> employeedtls = newGenTlEmployeemst.getEmployeeDetail();
		for(GenTlEmployeedtl genTlEmployeedtl : employeedtls )
		{
			CommonMessage.debugMsg(" Birth Date " + employeeBean.getEmpdBirthdate());
			validations.validate(genTlEmployeedtl,"employee",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
		}
		if(employeeBean.getEmpdBirthdate()!=null)
			validations.validate(employeeBean,"employee",validationsFor);
		CommonMessage.debugMsg("  5555555555 After");
		
		fillValues(newGenTlEmployeemst,oldGenTlEmployeemst,employeeBean);	
	
		CommonMessage.debugMsg("update:"+newGenTlEmployeemst);
		GenTlEmployeemst genTlEmployeemst = serviceApi.saveEmployee(newGenTlEmployeemst);
		employeeDao.insertEmployeeImage(newGenTlEmployeemst);
		
		return genTlEmployeemst;
		
	}
	
	public GenTlEmployeemst delete(GenTlEmployeemst genTlEmployeemst)throws Exception 
	{
		return employeeDao.delete(genTlEmployeemst);
	}
	private List<GenTlEmployeeimg> imageFillValues(GenTlEmployeemst newGenTlEmployeemst,GenTlEmployeemst oldGenTlEmployeemst)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("Time 123   : "+dateTime);
		List<GenTlEmployeeimg> employeeimg = newGenTlEmployeemst.getEmployeeImg();
		CommonMessage.debugMsg("The employeeimg:Size:::"+employeeimg.size());
		List<GenTlEmployeeimg> newGenTlEmployeeimgList = new ArrayList<GenTlEmployeeimg>();
		CommonMessage.debugMsg("EmpImg....................");
		for( GenTlEmployeeimg genTlEmployeeimg : employeeimg)
		{	
			genTlEmployeeimg.setEmpiModifiedon(dateTime);
			long length =0;
			String fileName = genTlEmployeeimg.getEmpiFilename();
			CommonMessage.debugMsg("fileName..."+fileName);
			if( CommonFunctions.isValidKeyId(fileName)){
				fileName =  fileName.substring(fileName.lastIndexOf("/")+1);
				genTlEmployeeimg.setEmpiFilename(fileName);
				fileName =genTlEmployeeimg.getEmpiBlobimage() + fileName;
				CommonMessage.debugMsg(" fileName " + fileName);
				genTlEmployeeimg.setEmpiBlobimage(fileName);
				
				if( CommonFunctions.isFileExists(fileName ) )
					length = new File(fileName).length();
				
				genTlEmployeeimg.setEmpiBloblength(Long.toString(length) );
				newGenTlEmployeeimgList.add(genTlEmployeeimg);
			}
		}
		return newGenTlEmployeeimgList;
	}
	
	
	private List<GenTlEmployeedtl> contactFillValues(GenTlEmployeemst newGenTlEmployeemst,GenTlEmployeemst oldGenTlEmployeemst,EmployeeBean employeeBean) 
	{
		CommonMessage.debugMsg("Detail 1");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlEmployeedtl> newGenTlEmployeedtls = newGenTlEmployeemst.getEmployeeDetail();
		List<GenTlEmployeedtl> oldGenTlEmployeedtls = null;
		GenTlEmployeedtl oldGenTlEmployeedtl  = null;
		CommonMessage.debugMsg("Detail 2");
		if( oldGenTlEmployeemst != null){
			oldGenTlEmployeedtls = oldGenTlEmployeemst.getEmployeeDetail();
			if( oldGenTlEmployeedtls != null && oldGenTlEmployeedtls.size() > 0 )
				oldGenTlEmployeedtl = oldGenTlEmployeedtls.get(0);
		}	
		CommonMessage.debugMsg("Detail 3");
		List<GenTlEmployeedtl> newGenTlEmployeedtlList = new ArrayList<GenTlEmployeedtl>();
		for( GenTlEmployeedtl genTlEmployeedtl :newGenTlEmployeedtls)
		{	
			if(genTlEmployeedtl.getEmpdKeyid() == null )			
			{	
				CommonMessage.debugMsg("Detail");
				genTlEmployeedtl.setEmpdCreatedon(dateTime);
			}	
			else{
				genTlEmployeedtl.setEmpdCreatedon(oldGenTlEmployeedtl.getEmpdCreatedon());
			}
			
			genTlEmployeedtl.setEmpdModifiedon(dateTime);
			genTlEmployeedtl.setEmpdActive("Y");
			genTlEmployeedtl.setEmpdCreatedby(newGenTlEmployeemst.getEmpmCreatedby());

			genTlEmployeedtl.setEmpdModifiedon(dateTime);
			
			/*if(employeeBean.getEmpdBirthdate()==null)
				genTlEmployeedtl.setEmpdBirthdate(Constants.passNullDate);*/
			String birthDate = genTlEmployeedtl.getEmpdBirthdate();
			genTlEmployeedtl.setEmpdBirthdate(CommonFunctions.pg_getDateTimeFromDate(birthDate));
			if( genTlEmployeedtl.getEmpdBirthdate() == null )
				genTlEmployeedtl.setEmpdBirthdate(Constants.pgPassNullDateTime);
			
			if( genTlEmployeedtl.getEmpdAddress() == null )
				genTlEmployeedtl.setEmpdAddress("{}");
			
			if( genTlEmployeedtl.getEmpdCityid() == null )
				genTlEmployeedtl.setEmpdCityid("{}");
			
			if( genTlEmployeedtl.getEmpdStateid() == null )
				genTlEmployeedtl.setEmpdStateid("{}");
			
			if( genTlEmployeedtl.getEmpdCountryid() == null )
				genTlEmployeedtl.setEmpdCountryid("{}");
			
			if( genTlEmployeedtl.getEmpdPhone() == null )
				genTlEmployeedtl.setEmpdPhone("{}");
			
			if( genTlEmployeedtl.getEmpdRemarks() == null )
				genTlEmployeedtl.setEmpdRemarks("{}");
			
			if( genTlEmployeedtl.getEmpdCurrentexperience() == null )
				genTlEmployeedtl.setEmpdCurrentexperience("0");
			
			if( genTlEmployeedtl.getEmpdOtherexperience() == null )
				genTlEmployeedtl.setEmpdOtherexperience("0");
			
			if( genTlEmployeedtl.getEmpdTotalexperience() == null )
				genTlEmployeedtl.setEmpdTotalexperience("0");
			
			if( genTlEmployeedtl.getEmpdDiscipline() == null )
				genTlEmployeedtl.setEmpdDiscipline("{}");
			
			if( genTlEmployeedtl.getEmpdQualification() == null )
				genTlEmployeedtl.setEmpdQualification("{}");
			
			if( genTlEmployeedtl.getEmpdImage() == null )
				genTlEmployeedtl.setEmpdImage("{}");
			
			newGenTlEmployeedtlList.add(genTlEmployeedtl);
		}
		
		return newGenTlEmployeedtlList;
	}
	
	
	private GenTlEmployeemst fillValues(GenTlEmployeemst newGenTlEmployeemst,GenTlEmployeemst oldGenTlEmployeemst,EmployeeBean employeeBean) throws Exception {
		CommonMessage.debugMsg("fill values"+newGenTlEmployeemst);
	
		//newGenTlEmployeemst.setEmpmActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		//CommonMessage.debugMsg(oldGenTlEmployeemst.getEmpmKeyid());
		if(newGenTlEmployeemst.getEmpmKeyid() == null )			
		{	
			CommonMessage.debugMsg("after gen:");
			newGenTlEmployeemst.setEmpmCreatedon(dateTime);	
			
			CommonMessage.debugMsg("after gen:");
		}					
		else
		{	
			newGenTlEmployeemst.setEmpmCreatedon(dateTime);	
			//newGenTlEmployeemst.setEmpmCreatedon(oldGenTlEmployeemst.getEmpmCreatedon());			
		}
		
		newGenTlEmployeemst.setEmpmModifiedon(dateTime);
/*		if( newGenTlEmployeemst.getEmpmName() == null )
			newGenTlEmployeemst.setEmpmName("{}");
*/		
		CommonMessage.debugMsg("fill values:");
		
		if( newGenTlEmployeemst.getEmpmFactoryid() == null )
			newGenTlEmployeemst.setEmpmFactoryid("{}");
	
		if( newGenTlEmployeemst.getEmpmSectionid() == null )
			newGenTlEmployeemst.setEmpmSectionid("{}");
		
		if( newGenTlEmployeemst.getEmpmCellid() == null )
			newGenTlEmployeemst.setEmpmCellid("{}");
		
		if( newGenTlEmployeemst.getEmpmGradeid() == null )
			newGenTlEmployeemst.setEmpmGradeid("{}");		
		
		if( newGenTlEmployeemst.getEmpmSkillcategory() == null )
			newGenTlEmployeemst.setEmpmSkillcategory("{}");		
		
		if( newGenTlEmployeemst.getEmpmGender() == null )
			newGenTlEmployeemst.setEmpmGender("{}");
		
		if( newGenTlEmployeemst.getEmpmEmail() == null )
			newGenTlEmployeemst.setEmpmEmail("{}");
		
		if( newGenTlEmployeemst.getEmpmMobile() == null )
			newGenTlEmployeemst.setEmpmMobile("{}");
		
		if( newGenTlEmployeemst.getEmpmTradeid() == null )
			newGenTlEmployeemst.setEmpmTradeid("{}");
		
		if( newGenTlEmployeemst.getEmpmActive() == null )
			newGenTlEmployeemst.setEmpmActive("Y");
		
		if( newGenTlEmployeemst.getEmpmCode() == null )
			newGenTlEmployeemst.setEmpmCode("{}");
		
		
		if( newGenTlEmployeemst.getEmpmDepartmentid() == null )
			newGenTlEmployeemst.setEmpmDepartmentid("{}");
		
		if( newGenTlEmployeemst.getEmpmDesignationid() == null )
			newGenTlEmployeemst.setEmpmDesignationid("{}");
		
		if( newGenTlEmployeemst.getEmpmEmployeenumber() == null )
			newGenTlEmployeemst.setEmpmEmployeenumber("{}");
		
		CommonMessage.debugMsg("type:"+newGenTlEmployeemst.getEmpmEmployeetype());
		if( newGenTlEmployeemst.getEmpmEmployeetype() == null )
			newGenTlEmployeemst.setEmpmEmployeetype("R");
		
		
		if( newGenTlEmployeemst.getEmpmExtensionphone() == null )
			newGenTlEmployeemst.setEmpmExtensionphone("{}");
		
		if( newGenTlEmployeemst.getEmpmIscellmanager() == null )
			newGenTlEmployeemst.setEmpmIscellmanager("N");
		
		
		if( newGenTlEmployeemst.getEmpmIssectionmanager() == null )
			newGenTlEmployeemst.setEmpmIssectionmanager("N");
		
		if( newGenTlEmployeemst.getEmpmIsoperator() == null )
			newGenTlEmployeemst.setEmpmIsoperator("N");
		
		if( newGenTlEmployeemst.getEmpmCompany() == null )
			newGenTlEmployeemst.setEmpmCompany("{}");
		
		if( newGenTlEmployeemst.getEmpmRoleid() == null )
			newGenTlEmployeemst.setEmpmRoleid("{}");
		
		if( newGenTlEmployeemst.getEmpmSbuId() == null )
			newGenTlEmployeemst.setEmpmSbuId("{}");
		
		if( newGenTlEmployeemst.getEmpmenablemail() == null )
	    	newGenTlEmployeemst.setEmpmenablemail("-");
		
		if( newGenTlEmployeemst.getEmpmLocation() == null )
			newGenTlEmployeemst.setEmpmLocation("{}");
		
		if( newGenTlEmployeemst.getEmpmIsshiftincharge() == null )
			newGenTlEmployeemst.setEmpmIsshiftincharge("N");
		
		CommonMessage.debugMsg("cell manager..."+newGenTlEmployeemst.getEmpmIscellmanager());
		CommonMessage.debugMsg("Section manager..."+newGenTlEmployeemst.getEmpmIssectionmanager());
		
		CommonMessage.debugMsg("Date::::::"+newGenTlEmployeemst.getEmpmJoineddate());		
		
		/*if(employeeBean.getEmpmJoineddate()==null)
			newGenTlEmployeemst.setEmpmJoineddate(Constants.passNullDate);*/
		
		String joinedDate = newGenTlEmployeemst.getEmpmJoineddate();
		newGenTlEmployeemst.setEmpmJoineddate(CommonFunctions.pg_getDateTimeFromDate(joinedDate));
		if( newGenTlEmployeemst.getEmpmJoineddate() == null )
			newGenTlEmployeemst.setEmpmJoineddate(Constants.pgPassNullDateTime);
		
		
		if( newGenTlEmployeemst.getEmpmPersonalinfo() == null )
			newGenTlEmployeemst.setEmpmPersonalinfo("{}");
		
		if( newGenTlEmployeemst.getEmpmRemarks() == null )
			newGenTlEmployeemst.setEmpmRemarks("{}");
		
		if( newGenTlEmployeemst.getEmpmCreatedby() == null )
			newGenTlEmployeemst.setEmpmCreatedby("{}");	
		
			CommonMessage.debugMsg("fill values:"+newGenTlEmployeemst);
			
			newGenTlEmployeemst.setEmployeeDetail( contactFillValues(newGenTlEmployeemst,oldGenTlEmployeemst,employeeBean))	;
			newGenTlEmployeemst.setEmployeeImg(imageFillValues(newGenTlEmployeemst,oldGenTlEmployeemst));
			
		return newGenTlEmployeemst; 
	}
	
	@Override
	public List<String[]> getAllEmployeeService() throws Exception {
		// TODO Auto-generated method stub
		return this.employeeDao.getAllEmployee();
	}

	@Override
	public List<ComboBox> getcompanycombo(ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("COMP_CODE");
		comboFilter.setNameField("COMP_NAME");
		comboFilter.setIdField("COMP_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_COMPANYMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	@Override
	public List<ComboBox> getlocationcombo(ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("LOCN_CODE");
		comboFilter.setNameField("LOCN_NAME");
		comboFilter.setIdField("LOCN_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_LOCATIONMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getdepartmentcombo(ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("DEPT_CODE");
		comboFilter.setNameField("DEPT_NAME");
		comboFilter.setIdField("DEPT_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_DEPARTMENTMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getEMPMdesignation(ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("DESG_NAME");
		comboFilter.setCodeField("DESG_CODE");
		comboFilter.setIdField("DESG_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_DESIGNATIONMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getEMPMGrade(String condSql,ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("GRDM_CODE");
		comboFilter.setNameField("GRDM_NAME");
		comboFilter.setIdField("GRDM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPGRADEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getEMPMTrade(String condSql,ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("TRDM_NAME");
		//comboFilter.setCodeField("TRDM_CODE");
		comboFilter.setIdField("TRDM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getEmpmKeyid(String condSql,ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");		
		//comboFilter.setOrderByField("EMPM_CREATEDON");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getEmpmCity(String condSql,ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("CTYM_CODE");		
		comboFilter.setNameField("CTYM_NAME");
		comboFilter.setIdField("CTYM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_CITYMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getEMPMNAME(String condSql,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenTlEmployeemst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("ServiceImpl:"+keyid);
		//return this.employeeDao.select(keyid);
		return serviceApi.selectData(keyid);
	}

	@Override
	public List<ComboBox> getEmpdStateid(String condSql,ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("STAM_CODE");		
		comboFilter.setNameField("STAM_NAME");
		comboFilter.setIdField("STAM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_STATEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getEmpdCountryid(String condSql,ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("CONM_CODE");		
		comboFilter.setNameField("CONM_NAME");
		comboFilter.setIdField("CONM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_COUNTRYMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public GenTlEmployeedtl getselect(String keyid) throws Exception {
		CommonMessage.debugMsg("ServiceImpl:"+keyid);
		return this.employeeDao.getselect(keyid);
	}

	@Override
	public GenTlEmployeeimg selectImg(String nodeId) throws Exception {
		
		return this.employeeDao.selectImg(nodeId);
	}

	@Override
	public GenTlEmployeeimg getLayoutImg(GenTlEmployeeimg genTlEmployeeimg)	throws Exception {
		
		return this.employeeDao.getLayoutImg(genTlEmployeeimg);
	}

	@Override
	public List<String[]> getAllFactoryname(String Employeeid,String Funloclink) throws Exception {
		// TODO Auto-generated method stub
		return this.employeeDao.getAllFactoryname(Employeeid,Funloclink);
	
	
	}

	@Override
	public List<String[]> getAllSectionName(String Employeeid,String Funloclink) throws Exception {
		// TODO Auto-generated method stub
		return this.employeeDao.getAllSectionName(Employeeid,Funloclink);
	
	
	}

	
	@Override
	public List<String[]> getAllLineName(String Employeeid,String Funloclink) throws Exception {
		// TODO Auto-generated method stub
		return this.employeeDao.getAllLineName(Employeeid,Funloclink);
	
	
	}
	@Override
	public List<String[]> getAllEquipmentName(String Employeeid,String Funloclink) throws Exception {
		// TODO Auto-generated method stub
		return this.employeeDao.getAllEquipmentName(Employeeid,Funloclink);  
	}
	@Override
	public List<String[]> getRoleEmpGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.employeeDao.getRoleEmpGrid(commonFilter);
	}
	public String EmployeeData(String empKeyid,String empName,String empPhoneNo,String empEmail) throws Exception{
		  return this.employeeDao.EmployeeData(empKeyid, empName, empPhoneNo, empEmail);
	}
	public GenTlEmployeemst imagecreate(GenTlEmployeemst newGenTlEmployeemst,GenTlEmployeemst oldGenTlEmployeemst,  EmployeeBean employeeBean,String userkeyid ) throws ValidationExceptions, Exception{
		newGenTlEmployeemst.setEmployeeImg(imageFillValues(newGenTlEmployeemst,oldGenTlEmployeemst)); 
		return this.employeeDao.imagecreate(newGenTlEmployeemst,userkeyid);
	}
	public List<String[]> getEmpData(String userKeyid)throws Exception{
		return this.employeeDao.getEmpData(userKeyid);
	}

}