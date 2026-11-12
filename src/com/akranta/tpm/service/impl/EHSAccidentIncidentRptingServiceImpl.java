package com.akranta.tpm.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.SheAccBean;
import com.akranta.tpm.bean.SheTlIncidentEmployeenewBean;
import com.akranta.tpm.bean.SheTlIncidentEmployeetBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.SheTlIncidentEmployeenewDao;
import com.akranta.tpm.dao.SheTlIncidentmstnewDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.SheTlIncidentEmployeenewDaoImpl;
import com.akranta.tpm.dao.impl.SheTlIncidentmstnewDaoImpl;
import com.akranta.tpm.dao.sql.SheTlIncidentmstSql;
import com.akranta.tpm.dao.sql.SheTlIncidentmstnewSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ACCIDENTITCExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.model.SheTlIncidentEmployeenew;

import com.akranta.tpm.model.SheTlIncidentmstnew;
import com.akranta.tpm.service.EHSAccidentIncidentRptingService;
import com.akranta.tpm.service.api.EmployeeGroupServiceApi;
import com.akranta.tpm.service.api.SheTlIncidentmstnewServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class EHSAccidentIncidentRptingServiceImpl implements EHSAccidentIncidentRptingService{
	SheTlIncidentmstnewDao sheTlIncidentmstnewDao;
	SheTlIncidentEmployeenewDao sheTlIncidentEmployeenewDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	DBActionTemplate  dbActionTemplate ;
	SheTlIncidentmstnewServiceApi serviceApi;
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void EHSAccidentRptingServiceImplJwt(String JwtToken) {
		try{
			sheTlIncidentmstnewDao.SheTlIncidentmstnewDaoImplJwt(JwtToken);
			serviceApi = new SheTlIncidentmstnewServiceApi(JwtToken);
			
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	

	public EHSAccidentIncidentRptingServiceImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		sheTlIncidentmstnewDao = new SheTlIncidentmstnewDaoImpl(dbActionTemplate);
		sheTlIncidentEmployeenewDao =new SheTlIncidentEmployeenewDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	@Override
	public SheTlIncidentmstnew create(SheTlIncidentmstnew newSheTlIncidentmst,SheTlIncidentmstnew oldSheTlIncidentmst,  SheAccBean sheAccIncBean ) throws ValidationExceptions, Exception
	{
		String validationsFor;
		validationsFor = "create";
		
		System.out.println("INSIDE THE SERVICE IMPL");
		String occuredDate = Constants.passNullDate;
		String reportedDate = Constants.futureNullDate;
		
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmOccurredDate()))
		{
			if(UIUtils.isValidKeyId(sheAccIncBean.getSinmOccurredtime()))
					occuredDate = newSheTlIncidentmst.getSinmOccurredDate()+ " "+sheAccIncBean.getSinmOccurredtime();
		}
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmReportedDate()))
		{
			if(UIUtils.isValidKeyId(sheAccIncBean.getSinmReportedtime()))
				reportedDate = newSheTlIncidentmst.getSinmReportedDate()+ " "+sheAccIncBean.getSinmReportedtime();
		}
		System.out.println("Reported Date "+reportedDate);
		System.out.println("Occured Date "+occuredDate);
		
		//newSheTlIncidentmst.setSinmOccurredDate(occuredDate);		
		//newSheTlIncidentmst.setSinmReportedDate(reportedDate);
		
		newSheTlIncidentmst.setSinmOccurredDate(CommonFunctions.pg_getDateTimeFromTimeStamp(occuredDate+":00"));		
		newSheTlIncidentmst.setSinmReportedDate(CommonFunctions.pg_getDateTimeFromTimeStamp(reportedDate+":00"));
		
		//VALIDATION COMMENTED TILL THE FILE RECIEVED - VAL
		//validations.validate(newSheTlIncidentmst,"EHSAccidentInc",validationsFor);
		System.out.println("before the fill values");	
		fillValues(newSheTlIncidentmst,oldSheTlIncidentmst,sheAccIncBean);
		System.out.println("after the fill values");	
		if(newSheTlIncidentmst.getSinmStatus()==null)
		{
		newSheTlIncidentmst.setSinmStatus("P");
		}
		if(newSheTlIncidentmst.getSinmVerifiedstatus()==null)
		{
		newSheTlIncidentmst.setSinmVerifiedstatus("N");
		}
		if(newSheTlIncidentmst.getSinmApprovalstatus()==null)
		{
		newSheTlIncidentmst.setSinmApprovalstatus("N");
		}
		System.out.println("OUTSIDE THE FILL VALUES  IMPL");
		//return sheTlIncidentmstnewDao.create(newSheTlIncidentmst);
		return serviceApi.insertRecord(newSheTlIncidentmst);
	}
	@Override
	public SheTlIncidentmstnew update(SheTlIncidentmstnew newSheTlIncidentmst,SheTlIncidentmstnew oldSheTlIncidentmst,  SheAccBean sheAccIncBean)  throws  BusinessApplicationExceptions , Exception
	{
		/*String validationsFor = "update";;
		if(UIUtils.isValidKeyId(sheAccIncBean.getFormMode()))
		{
			
			if(sheAccIncBean.getFormMode().trim().equals("create"))
				validationsFor  = "create";
		}
		CommonFunctions.debugMsg(".........................Date "+newSheTlIncidentmst.getSinmAnalysedDate());
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedDate()))
		{
			CommonFunctions.debugMsg(".........................Date "+sheAccIncBean.getSinmAnalysedtime());
			if(UIUtils.isValidKeyId(sheAccIncBean.getSinmAnalysedtime()))
				newSheTlIncidentmst.setSinmAnalysedDate(newSheTlIncidentmst.getSinmAnalysedDate()+ " "+sheAccIncBean.getSinmAnalysedtime());
			CommonFunctions.debugMsg(".........................Date "+newSheTlIncidentmst.getSinmAnalysedDate());
		}
		String occuredDate = Constants.passNullDate;
		String reportedDate = Constants.futureNullDate;
		CommonFunctions.debugMsg(".........................Date "+occuredDate);
		//CommonFunctions.debugMsg(oldSheTlIncidentmst.getSinmOccurredDate());
		if(oldSheTlIncidentmst != null)
		{
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmOccurredDate()))
		{
			if(UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmOccurredDate()))
				occuredDate = oldSheTlIncidentmst.getSinmOccurredDate();
		}
		
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmReportedDate()))
		{
			if(UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmReportedDate()))
				reportedDate = oldSheTlIncidentmst.getSinmReportedDate();
		}
		}
		
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmOccurredDate()))
		{
			if(UIUtils.isValidKeyId(sheAccIncBean.getSinmOccurredtime()))
					occuredDate = newSheTlIncidentmst.getSinmOccurredDate()+ " "+sheAccIncBean.getSinmOccurredtime();
		}
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmReportedDate()))
		{
			if(UIUtils.isValidKeyId(sheAccIncBean.getSinmReportedtime()))
				reportedDate = newSheTlIncidentmst.getSinmReportedDate()+ " "+sheAccIncBean.getSinmReportedtime();
		}
		
		newSheTlIncidentmst.setSinmOccurredDate(occuredDate);		
		newSheTlIncidentmst.setSinmReportedDate(reportedDate);
		
		
			validations.validate(newSheTlIncidentmst,"EHSAccidentInc",validationsFor);
		if("C".equals(newSheTlIncidentmst.getSinmStatus()))	
		{
			List<String[]> planStatus = dbActionTemplate.getDataList(SheTlIncidentmstnewSql.selectActionPlanStatus(newSheTlIncidentmst.getSinmKeyid()));
			CommonFunctions.debugMsg(".....planStatus....... "+planStatus);
			
			if(planStatus.size()>0)
			{
				for(int i=0;i<planStatus.size();i++)
				{
					if(planStatus.get(i)[0].equals("P"))
					{  
						throw new BusinessApplicationExceptions("Actionplanpending");
					}
				}
				
			}
			
		}
			*/

		newSheTlIncidentmst.setSinmOccurredDate(CommonFunctions.pg_getDateTimeFromDate(newSheTlIncidentmst.getSinmOccurredDate()));		
		newSheTlIncidentmst.setSinmReportedDate(CommonFunctions.pg_getDateTimeFromDate(newSheTlIncidentmst.getSinmReportedDate()));
		
		fillValues(newSheTlIncidentmst,oldSheTlIncidentmst,sheAccIncBean);
		System.out.println("inside the upadate service Impl");
		if(newSheTlIncidentmst.getSinmStatus()==null)
		{
		newSheTlIncidentmst.setSinmStatus("P");
		}
		if(newSheTlIncidentmst.getSinmVerifiedstatus()==null)
		{
		newSheTlIncidentmst.setSinmVerifiedstatus("N");
		}
		if(newSheTlIncidentmst.getSinmApprovalstatus()==null)
		{
		newSheTlIncidentmst.setSinmApprovalstatus("N");
		}
		//return sheTlIncidentmstnewDao.update(newSheTlIncidentmst);
		return serviceApi.insertRecord(newSheTlIncidentmst);
	}
	@Override
	public List<GenTlAllmoduleimgfile> saveImg(SheTlIncidentmstnew sheTlIncidentmst) throws Exception,BusinessApplicationExceptions {
		List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = sheTlIncidentmst.getAllmoduleimgfile();
		System.out.println("newGenTlAllmoduleimgfile"+newGenTlAllmoduleimgfile.size()+""+newGenTlAllmoduleimgfile);
		if( newGenTlAllmoduleimgfile != null )//&& CommonFunctions.isValidKeyId(newGenTlAllmoduleimgfile.getToimFilename()) )
		{
			
			newGenTlAllmoduleimgfile = fillImgValues(sheTlIncidentmst);
			return this.sheTlIncidentmstnewDao.saveOplImg(sheTlIncidentmst);
		}
		return null;
		
	}
	private List<GenTlAllmoduleimgfile> fillImgValues(SheTlIncidentmstnew sheTlIncidentmst) {

		List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = sheTlIncidentmst.getAllmoduleimgfile();
		List<GenTlAllmoduleimgfile> genTlAllmoduleimgfileList =new ArrayList<GenTlAllmoduleimgfile>();
		for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:newGenTlAllmoduleimgfile)
		{
			System.out.println("lllllll");
			String dateTime = CommonFunctions.dateTimeNow();
			System.out.println(dateTime);
			
			genTlAllmoduleimgfile.setImflRefkeyid(sheTlIncidentmst.getSinmKeyid());
			genTlAllmoduleimgfile.setImflModifiedon(sheTlIncidentmst.getSinmModifiedon());
			long length =0;
			
			String fileName = genTlAllmoduleimgfile.getImflFilename();
			if( CommonFunctions.isValidKeyId(fileName))
			{
				fileName =  fileName.substring(fileName.lastIndexOf("/")+1);
				genTlAllmoduleimgfile.setImflFilename(fileName );
				fileName =genTlAllmoduleimgfile.getImflBlobimage() + fileName;
				System.out.println(" fileName " + fileName);
				genTlAllmoduleimgfile.setImflBlobimage(fileName);
				
				if( CommonFunctions.isFileExists(fileName ) )
					length = new File(fileName).length();
				
				genTlAllmoduleimgfile.setImflBloblength(Long.toString(length) );
				
				if(genTlAllmoduleimgfile.getImflRefdoctype()==null)
					genTlAllmoduleimgfile.setImflRefdoctype("OPL");
				if( genTlAllmoduleimgfile.getImflTempfield1() == null )
					genTlAllmoduleimgfile.setImflTempfield1("{}");
				if( genTlAllmoduleimgfile.getImflTempfield2() == null )
					genTlAllmoduleimgfile.setImflTempfield2("{}");

				System.out.println("IMS : " +genTlAllmoduleimgfile.getImflRefkeyid());
				
				genTlAllmoduleimgfileList.add(genTlAllmoduleimgfile);
			}
		}

		return genTlAllmoduleimgfileList; 
	}
	@Override
	public SheTlIncidentEmployeenew create(SheTlIncidentEmployeenew newsheTlIncidentEmployee,SheTlIncidentEmployeenew existSheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) throws BusinessApplicationExceptions,ValidationExceptions,Exception {

		try {
			String validationsFor = "create";
		    if(UIUtils.isValidKeyId(sheTlIncidentEmployeetBean.getContract()) && sheTlIncidentEmployeetBean.getContract().equals("Y"))
			{
		    	//commented for now - validation
				validationsFor = "contractcreate";
				validations.validate(sheTlIncidentEmployeetBean,"EHSAccidentIncedentEmployeeCreation",validationsFor);
			}
			
			
			validations.validate(newsheTlIncidentEmployee,"EHSAccidentIncedentEmployeeCreation",validationsFor);
			
			fillValuesincidentEmployee(newsheTlIncidentEmployee,existSheTlIncidentEmployee,sheTlIncidentEmployeetBean);
			//List<String[]> count  =  entBatchCreationDao.getBatchFacultyCount(newEntTlBatchEmployeeLink);
			//return sheTlIncidentEmployeenewDao.create(newsheTlIncidentEmployee,sheTlIncidentEmployeetBean);
			return serviceApi.insertEmployee(newsheTlIncidentEmployee, sheTlIncidentEmployeetBean);
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}
	@Override
	public SheTlIncidentEmployeenew update(SheTlIncidentEmployeenew newsheTlIncidentEmployee,SheTlIncidentEmployeenew existSheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) throws Exception {
		
		try {
			String validationsFor = "create";
			if(UIUtils.isValidKeyId(sheTlIncidentEmployeetBean.getContract()) && sheTlIncidentEmployeetBean.getContract().equals("Y"))
			{
				validationsFor = "contractcreate";
				validations.validate(sheTlIncidentEmployeetBean,"EHSAccidentIncedentEmployeeCreation",validationsFor);
			}
			validations.validate(newsheTlIncidentEmployee,"EHSAccidentIncedentEmployeeCreation",validationsFor);
			fillValuesincidentEmployee(newsheTlIncidentEmployee,existSheTlIncidentEmployee,sheTlIncidentEmployeetBean);
			
			//return sheTlIncidentEmployeenewDao.update(newsheTlIncidentEmployee,sheTlIncidentEmployeetBean);
			return serviceApi.insertEmployee(newsheTlIncidentEmployee, sheTlIncidentEmployeetBean);
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
		
	}
public SheTlIncidentEmployeenew delete(SheTlIncidentEmployeenew sheTlIncidentEmployee) throws Exception {
		
		return sheTlIncidentEmployeenewDao.delete(sheTlIncidentEmployee);
	}
	private SheTlIncidentEmployeenew fillValuesincidentEmployee(SheTlIncidentEmployeenew newsheTlIncidentEmployee,
			SheTlIncidentEmployeenew existSheTlIncidentEmployee,
			SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) {
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		if(newsheTlIncidentEmployee.getSineInjuryModeid()==null)
			newsheTlIncidentEmployee.setSineInjuryModeid("{}");
		
		if(newsheTlIncidentEmployee.getSineEmpmKeyid()==null)
			newsheTlIncidentEmployee.setSineEmpmKeyid("{}");
		
		if( newsheTlIncidentEmployee.getSineBodypart() == null )
			newsheTlIncidentEmployee.setSineBodypart("{}");
		
		if( newsheTlIncidentEmployee.getSineSincKeyid() == null )
			newsheTlIncidentEmployee.setSineSincKeyid("{}");
		
		if( newsheTlIncidentEmployee.getSineRemarks() == null )
			newsheTlIncidentEmployee.setSineRemarks("{}");
		
		if( newsheTlIncidentEmployee.getSineInjuryTypeid() == null )
			newsheTlIncidentEmployee.setSineInjuryTypeid("{}");
		
		/*if( newsheTlIncidentEmployee.getSineCompany() == null )
			newsheTlIncidentEmployee.setSineCompany("{}");
		else
		{
			if(UIUtils.isValidKeyId(newsheTlIncidentEmployee.getSiemCompany()))
			newsheTlIncidentEmployee.setSiemCompany(newsheTlIncidentEmployee.getSiemCompany().toUpperCase());
		}*/
		
		if( newsheTlIncidentEmployee.getSineMode() == null )
			newsheTlIncidentEmployee.setSineMode("-");
		
		if( newsheTlIncidentEmployee.getSineType() == null )
			newsheTlIncidentEmployee.setSineType("-");
		
		if( newsheTlIncidentEmployee.getSineLocation() == null )
			newsheTlIncidentEmployee.setSineLocation("-");
		
		if( newsheTlIncidentEmployee.getSineTempfield5() == null )
			newsheTlIncidentEmployee.setSineTempfield5("-");
		
		return newsheTlIncidentEmployee;
		
	}
	@Override
	public List<String[]> getAllIncidents(CommonFilter commonFilter) throws Exception
	{
		return sheTlIncidentmstnewDao.getAllIncidents(commonFilter);
	}
	@Override
	public SheTlIncidentEmployeenew recallEmployee(String keyId) throws Exception {
		return sheTlIncidentmstnewDao.recallEmployee(keyId);
	}
	@Override
	public List<String[]> getIncidentEmployee(SheTlIncidentEmployeenew sheTlIncidentEmployee)throws Exception
	{
		return this.sheTlIncidentmstnewDao.getIncidentEmployee(sheTlIncidentEmployee);
	}
	@Override
	public SheTlIncidentmstnew select(String keyid) throws Exception
	{
		//return sheTlIncidentmstnewDao.select(keyid);
		return serviceApi.getSheTlIncidentmstnewById(keyid);
	}
	@Override
	public List<ComboBox> getIncidentTypeComboList(CommonFilter commonFilter, ComboFilter BodyPart) throws Exception {
		
		//ComboFilter BodyPart = new ComboFilter();
		BodyPart.setIdField("ACTY_KEYID");
		BodyPart.setNameField("ACTY_NAME");
		
		BodyPart.setTableName(TableNames.TBL_SHE_TL_ACCIDENT_TYPEMST);
		
		return commonFilterDao.fillComboValues(BodyPart);
	}
	@Override
	public List<ComboBox> getBodyPartComboList(CommonFilter commonFilter, ComboFilter BodyPart) throws Exception {
		
	//	ComboFilter BodyPart = new ComboFilter();
		BodyPart.setIdField("IBOP_KEYID");
		BodyPart.setCodeField("IBOP_NAME");
		
		BodyPart.setTableName(TableNames.TBL_SHE_TL_INCIDENTBODYPARTMST);
		
		return commonFilterDao.fillComboValues(BodyPart);
	}
	@Override
	public List<ComboBox> getTypeComboList(CommonFilter commonFilter,ComboFilter injury) throws Exception 
	{
	//	ComboFilter type = new ComboFilter();
		injury.setIdField("ICJM_KEYID");
		//injury.setCodeField("INJM_CODE");
		injury.setNameField("ICJM_NAME");
		
		injury.setTableName(TableNames.TBL_SHE_TL_INCIINJURYMODEMST);
		
		return commonFilterDao.fillComboValues(injury);
	}
	@Override
	public List<ComboBox> getInjuryComboList(CommonFilter commonFilter,ComboFilter injury) throws Exception 
	{
		//ComboFilter injury = new ComboFilter();
		injury.setIdField("INJM_KEYID");
		//injury.setCodeField("INJM_CODE");
		injury.setNameField("INJM_NAME");
		
		injury.setTableName(TableNames.TBL_SHE_TL_INJURYMODEMST);
		
		return commonFilterDao.fillComboValues(injury);
	}
	
	@Override
	public SheAccBean getImage(String fileName, String filePath,SheAccBean sheAccIncBean,SheTlIncidentmstnew sheTlIncidentmst) throws Exception {
		
		System.out.println("Inside Service Impl img");
		List<GenTlAllmoduleimgfile> insImgList = new ArrayList<GenTlAllmoduleimgfile>();
		if(sheTlIncidentmst!=null)
		{

			GenTlAllmoduleimgfile oplBeforeImage = new GenTlAllmoduleimgfile();
				
			oplBeforeImage.setImflBlobimage(filePath);
			oplBeforeImage.setImflFilename(fileName);
			oplBeforeImage.setImflRefkeyid(sheTlIncidentmst.getSinmKeyid());
			oplBeforeImage.setImflRefdoctype("INC");
			oplBeforeImage.setImflImagetype("PRE");
			insImgList.add(oplBeforeImage);
			
			GenTlAllmoduleimgfile oplAfterImage = new GenTlAllmoduleimgfile();
			
			oplAfterImage.setImflBlobimage(filePath);
			oplAfterImage.setImflFilename(fileName);
			oplAfterImage.setImflRefkeyid(sheTlIncidentmst.getSinmKeyid());
			oplAfterImage.setImflRefdoctype("INC");
			oplAfterImage.setImflImagetype("AFT");
			insImgList.add(oplAfterImage);

		}
		insImgList = sheTlIncidentmstnewDao.getImage(insImgList);
		for( GenTlAllmoduleimgfile genTlAllmoduleimgfile : insImgList){
			if( "AFT".equals(genTlAllmoduleimgfile.getImflImagetype() ))
				sheAccIncBean.setAfter(genTlAllmoduleimgfile.getImflFilename());
			else if( "PRE".equals(genTlAllmoduleimgfile.getImflImagetype()) )
				sheAccIncBean.setBefore(genTlAllmoduleimgfile.getImflFilename());
		}
		//oplTlMst.setAllmoduleimgfile(oplImgList);
		return sheAccIncBean;
	}

private SheTlIncidentmstnew fillValues(SheTlIncidentmstnew newSheTlIncidentmst,SheTlIncidentmstnew oldSheTlIncidentmst,  SheAccBean sheAccIncBean) {
		
	
	System.out.println("INSIDE THE FIll VALUES IMPL");
		newSheTlIncidentmst.setSinmActive("Y");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		newSheTlIncidentmst.setSinmCreatedon(dateTime);
		newSheTlIncidentmst.setSinmModifiedon(dateTime);
		
		
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmFactKeyid()))
			newSheTlIncidentmst.setSinmFactKeyid("{}");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmFlid()))
			newSheTlIncidentmst.setSinmFlid("{}");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmElementid()))
			newSheTlIncidentmst.setSinmElementid("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmSectKeyid()))
			newSheTlIncidentmst.setSinmSectKeyid("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmCellKeyid()))
			newSheTlIncidentmst.setSinmCellKeyid("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmMachKeyid()))
			newSheTlIncidentmst.setSinmMachKeyid("{}");		
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmOccurredShift()))
			newSheTlIncidentmst.setSinmOccurredShift("{}");		
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmReportedShift()))
			newSheTlIncidentmst.setSinmReportedShift("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmReportedBy()))
			newSheTlIncidentmst.setSinmReportedBy("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmDepartment()))
			newSheTlIncidentmst.setSinmDepartment("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmDivision()))
			newSheTlIncidentmst.setSinmDivision("{}");
		/** added for new table structure **/
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmInvrptdate()))
			newSheTlIncidentmst.setSinmInvrptdate(Constants.pgPassNullDateTime);
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmTargetdate()))
			newSheTlIncidentmst.setSinmTargetdate(Constants.pgPassNullDateTime);
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmReviseddate()))
			newSheTlIncidentmst.setSinmReviseddate(Constants.pgPassNullDateTime);
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmDatecompleted()))
			newSheTlIncidentmst.setSinmDatecompleted(Constants.pgPassNullDateTime);
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmDateverified()))
			newSheTlIncidentmst.setSinmDateverified(Constants.pgPassNullDateTime);
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmApprovaldate()))
			newSheTlIncidentmst.setSinmApprovaldate(Constants.pgPassNullDateTime);
		
		/** end of new field added***/
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmArea()))
			newSheTlIncidentmst.setSinmArea("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAccidentType()))
			newSheTlIncidentmst.setSinmAccidentType("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAccidentDesc()))
			newSheTlIncidentmst.setSinmAccidentDesc("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmImmediateAction()))
			newSheTlIncidentmst.setSinmImmediateAction("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmCarecommended()))
			newSheTlIncidentmst.setSinmCarecommended("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmNoofemployees()))
			newSheTlIncidentmst.setSinmNoofemployees("0");
		
		if(UIUtils.isValidKeyId(sheAccIncBean.getFormMode()))
		{
			if(sheAccIncBean.getFormMode().equals("create"))
			{
				
				if(oldSheTlIncidentmst != null)
				{
					/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmYyno()))
					{
						//if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedBy()))
							newSheTlIncidentmst.setSinmStatus("P");
					}
					else
					{
						//newSheTlIncidentmst.setSinmStatus(oldSheTlIncidentmst.getSinmStatus());
						newSheTlIncidentmst.setSinmStatus("C");
					}*/ // by karthikeyan
			
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmExactDesc()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmExactDesc()))
							newSheTlIncidentmst.setSinmExactDesc(newSheTlIncidentmst.getSinmAccidentDesc());
					}
					else
					{
						newSheTlIncidentmst.setSinmExactDesc(oldSheTlIncidentmst.getSinmExactDesc());
					}
			
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmExactType()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmExactType()))
							newSheTlIncidentmst.setSinmExactType(newSheTlIncidentmst.getSinmAccidentType());
					}
					else
					{
						newSheTlIncidentmst.setSinmExactType(oldSheTlIncidentmst.getSinmExactType());
					}
					
			
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmAnalysedBy()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedBy()))
							newSheTlIncidentmst.setSinmAnalysedBy("{}");
					}
					else
					{
						newSheTlIncidentmst.setSinmAnalysedBy(oldSheTlIncidentmst.getSinmAnalysedBy());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmAnalysedDate()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedDate()))
							newSheTlIncidentmst.setSinmAnalysedDate(Constants.pgPassNullDateTime);
					}
					else
					{
						newSheTlIncidentmst.setSinmAnalysedDate(oldSheTlIncidentmst.getSinmAnalysedDate());
					}
					
					newSheTlIncidentmst.setSinmAnalysedDate(CommonFunctions.pg_getDateTimeFromDate(newSheTlIncidentmst.getSinmAnalysedDate()));
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmPhenomena()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmPhenomena()))
							newSheTlIncidentmst.setSinmPhenomena("{}");
					}
					else
					{
						newSheTlIncidentmst.setSinmPhenomena(oldSheTlIncidentmst.getSinmPhenomena());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmWhywhyflag()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWhywhyflag()))
							newSheTlIncidentmst.setSinmWhywhyflag("P");
					}
					else
					{
						newSheTlIncidentmst.setSinmWhywhyflag(oldSheTlIncidentmst.getSinmWhywhyflag());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmRootcause()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmRootcause()))
							newSheTlIncidentmst.setSinmRootcause("{}");
					}
					else
					{
						newSheTlIncidentmst.setSinmRootcause(oldSheTlIncidentmst.getSinmRootcause());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmCountermeasure()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmCountermeasure()))
							newSheTlIncidentmst.setSinmCountermeasure("{}");
					}
					else
					{
						newSheTlIncidentmst.setSinmCountermeasure(oldSheTlIncidentmst.getSinmCountermeasure());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmRootcausedueto()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmRootcausedueto()))
							newSheTlIncidentmst.setSinmRootcausedueto("-");
					}
					else
					{
						newSheTlIncidentmst.setSinmRootcausedueto(oldSheTlIncidentmst.getSinmRootcausedueto());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmIdea()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmIdea()))
							newSheTlIncidentmst.setSinmIdea("{}");
					}
					else
					{
						newSheTlIncidentmst.setSinmIdea(oldSheTlIncidentmst.getSinmIdea());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmAnalysedShift()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedShift()))
							newSheTlIncidentmst.setSinmAnalysedShift("{}");
					}
					else
					{
						newSheTlIncidentmst.setSinmAnalysedShift(oldSheTlIncidentmst.getSinmAnalysedShift());
					}
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmWhywhyno()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWhywhyno()))
							newSheTlIncidentmst.setSinmWhywhyno("{}");
					}
					else
					{
						newSheTlIncidentmst.setSinmWhywhyno(oldSheTlIncidentmst.getSinmWhywhyno());
					}
				}
				else
				{
					
					fillAnalysisValues(newSheTlIncidentmst);
				}
			}
			else
			{
				//CommonFunctions.debugMsg("WHY WHY else : "+oldSheTlIncidentmst.getSinmWhywhyflag());
				fillSafetyAnalysisValues(newSheTlIncidentmst,oldSheTlIncidentmst);
			}
		}
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmLossproperty()))
			newSheTlIncidentmst.setSinmLossproperty("{}");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWitness1()))
			newSheTlIncidentmst.setSinmWitness1("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWitness2()))
			newSheTlIncidentmst.setSinmWitness2("-");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmLossproperty()))
			newSheTlIncidentmst.setSinmLossproperty("{}");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmDirectcost()))
			newSheTlIncidentmst.setSinmDirectcost("0");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmTempfield1()))
			newSheTlIncidentmst.setSinmTempfield1("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmTempfield2()))
			newSheTlIncidentmst.setSinmTempfield2("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmTempfield3()))
			newSheTlIncidentmst.setSinmTempfield3("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmTempfield4()))
			newSheTlIncidentmst.setSinmTempfield4("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmTempfield5()))
			newSheTlIncidentmst.setSinmTempfield5("-");		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmTempfield6()))
			newSheTlIncidentmst.setSinmTempfield6("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmTempfield7()))
			newSheTlIncidentmst.setSinmTempfield7("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmInvestigationRemarks()))
			newSheTlIncidentmst.setSinmInvestigationRemarks("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmInvrpRemarks()))
			newSheTlIncidentmst.setSinmInvrpRemarks("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmResponsibilityRemarks()))
			newSheTlIncidentmst.setSinmResponsibilityRemarks("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmCompletedRemarks()))
			newSheTlIncidentmst.setSinmCompletedRemarks("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmVerifiedRemarks()))
			newSheTlIncidentmst.setSinmVerifiedRemarks("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmApprovalRemarks()))
			newSheTlIncidentmst.setSinmApprovalRemarks("-");
		
		
		
		return newSheTlIncidentmst;
	}
private SheTlIncidentmstnew fillAnalysisValues(SheTlIncidentmstnew newSheTlIncidentmst)
{
	
	System.out.println("INSIDE THE FILLANALYAISS IMPL");
	CommonFunctions.debugMsg("fill values.........."+newSheTlIncidentmst.getSinmStatus());
	CommonFunctions.debugMsg("fill values.........."+newSheTlIncidentmst.getSinmAnalysedBy());
	/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmStatus()))
		newSheTlIncidentmst.setSinmStatus("P");*/
	//by karthikeyan
	/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmYyno()))
		newSheTlIncidentmst.setSinmStatus("P");
	else 
		newSheTlIncidentmst.setSinmStatus("C"); */
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmExactDesc()))
		newSheTlIncidentmst.setSinmExactDesc(newSheTlIncidentmst.getSinmAccidentDesc());
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmExactType()))
		newSheTlIncidentmst.setSinmExactType(newSheTlIncidentmst.getSinmAccidentType());
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedBy()))
		newSheTlIncidentmst.setSinmAnalysedBy("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedDate()))
		newSheTlIncidentmst.setSinmAnalysedDate(Constants.pgPassNullDateTime);
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmPhenomena()))
		newSheTlIncidentmst.setSinmPhenomena("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWhywhyflag()))
		newSheTlIncidentmst.setSinmWhywhyflag("P");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmRootcause()))
		newSheTlIncidentmst.setSinmRootcause("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmCountermeasure()))
		newSheTlIncidentmst.setSinmCountermeasure("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmRootcausedueto()))
		newSheTlIncidentmst.setSinmRootcausedueto("-");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmIdea()))
		newSheTlIncidentmst.setSinmIdea("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedShift()))
		newSheTlIncidentmst.setSinmAnalysedShift("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWhywhyno()))
		newSheTlIncidentmst.setSinmWhywhyno("{}");
	
	
	return newSheTlIncidentmst;
}
private SheTlIncidentmstnew fillSafetyAnalysisValues(SheTlIncidentmstnew newSheTlIncidentmst,SheTlIncidentmstnew oldSheTlIncidentmst)
{
	
	CommonFunctions.debugMsg("fill values.........."+newSheTlIncidentmst.getSinmStatus());
	CommonFunctions.debugMsg("fill values.........."+newSheTlIncidentmst.getSinmAnalysedBy());
	/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmStatus()))
		newSheTlIncidentmst.setSinmStatus("P");*/
	if(oldSheTlIncidentmst != null)
	{
		/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmYyno()))
		{
			//if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedBy()))
				newSheTlIncidentmst.setSinmStatus("P");
		}
		else
		{
			//newSheTlIncidentmst.setSinmStatus(oldSheTlIncidentmst.getSinmStatus());
			newSheTlIncidentmst.setSinmStatus("C");
		} */ // by karthikeyan

		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmExactDesc()))
		{
			if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmExactDesc()))
				newSheTlIncidentmst.setSinmExactDesc(oldSheTlIncidentmst.getSinmAccidentDesc());
			else
				newSheTlIncidentmst.setSinmExactDesc("{}");
		}
		

		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmExactType()))
		{
			if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmExactType()))
				newSheTlIncidentmst.setSinmExactType(oldSheTlIncidentmst.getSinmAccidentType());
			else
				newSheTlIncidentmst.setSinmExactType("{}");
		}
		
		

		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedBy()))
		{
			//if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmAnalysedBy()))
				//newSheTlIncidentmst.setSinmAnalysedBy(oldSheTlIncidentmst.getSinmAnalysedBy());
			//else
				newSheTlIncidentmst.setSinmAnalysedBy("{}");
		}
	
	//	if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmAnalysedDate()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedDate()))
				newSheTlIncidentmst.setSinmAnalysedDate(Constants.pgPassNullDateTime);
		//}
			//else
			//{
			//newSheTlIncidentmst.setSinmAnalysedDate(newSheTlIncidentmst.getSinmAnalysedDate());
			//}
	
		//if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmPhenomena()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmPhenomena()))
				newSheTlIncidentmst.setSinmPhenomena("{}");
		//}
		//else
		//{
			//newSheTlIncidentmst.setSinmPhenomena(oldSheTlIncidentmst.getSinmPhenomena());
		//}
	
		//if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmWhywhyflag()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWhywhyflag()))
				newSheTlIncidentmst.setSinmWhywhyflag("P");
		//}
		else
		{
			newSheTlIncidentmst.setSinmWhywhyflag(oldSheTlIncidentmst.getSinmWhywhyflag());
		}
	
		//if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmRootcause()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmRootcause()))
				newSheTlIncidentmst.setSinmRootcause("{}");
		//}
		//else
		//{
			//newSheTlIncidentmst.setSinmRootcause(oldSheTlIncidentmst.getSinmRootcause());
		//}
	
	//	if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmCountermeasure()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmCountermeasure()))
				newSheTlIncidentmst.setSinmCountermeasure("{}");
		//}
		//else
		//{
		//	newSheTlIncidentmst.setSinmCountermeasure(oldSheTlIncidentmst.getSinmCountermeasure());
		//}
	
	//	if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmRootcausedueto()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmRootcausedueto()))
				newSheTlIncidentmst.setSinmRootcausedueto("-");
	//	}
		//else
		//{
		//	newSheTlIncidentmst.setSinmRootcausedueto(oldSheTlIncidentmst.getSinmRootcausedueto());
		//}
	
		//if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmIdea()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmIdea()))
				newSheTlIncidentmst.setSinmIdea("{}");
		//}
		//else
		//{
		//	newSheTlIncidentmst.setSinmIdea(oldSheTlIncidentmst.getSinmIdea());
		//}
	
	//	if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmAnalysedShift()))
	//	{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedShift()))
				newSheTlIncidentmst.setSinmAnalysedShift("{}");
	//	}
	//	else
	//	{
	//		newSheTlIncidentmst.setSinmAnalysedShift(oldSheTlIncidentmst.getSinmAnalysedShift());
		//}
		if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSinmWhywhyno()))
		{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWhywhyno()))
				newSheTlIncidentmst.setSinmWhywhyno("{}");
		}
		else
		{
			newSheTlIncidentmst.setSinmWhywhyno(oldSheTlIncidentmst.getSinmWhywhyno());
		}
	}
	else
	{
		/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmYyno()))
			newSheTlIncidentmst.setSinmStatus("P");
		else 
			newSheTlIncidentmst.setSinmStatus("C"); *///by karthikeyan
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmExactDesc()))
			newSheTlIncidentmst.setSinmExactDesc(newSheTlIncidentmst.getSinmAccidentDesc());
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmExactType()))
			newSheTlIncidentmst.setSinmExactType(newSheTlIncidentmst.getSinmAccidentType());
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedBy()))
			newSheTlIncidentmst.setSinmAnalysedBy("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedDate()))
			newSheTlIncidentmst.setSinmAnalysedDate(Constants.passNullDate);
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmPhenomena()))
			newSheTlIncidentmst.setSinmPhenomena("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWhywhyflag()))
			newSheTlIncidentmst.setSinmWhywhyflag("P");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmRootcause()))
			newSheTlIncidentmst.setSinmRootcause("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmCountermeasure()))
			newSheTlIncidentmst.setSinmCountermeasure("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmRootcausedueto()))
			newSheTlIncidentmst.setSinmRootcausedueto("-");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmIdea()))
			newSheTlIncidentmst.setSinmIdea("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmAnalysedShift()))
			newSheTlIncidentmst.setSinmAnalysedShift("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSinmWhywhyno()))
			newSheTlIncidentmst.setSinmWhywhyno("{}");
	}
	
	
	return newSheTlIncidentmst;
}

@Override
public String singleResponsiblty(String keyid, String responsevalue, String targetdate,SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {
	  //return sheTlIncidentmstnewDao.AssignResponse(keyid,responsevalue,targetdate,sheTlIncidentmstnew);
	return serviceApi.assignResponsibility(keyid, responsevalue, targetdate, sheTlIncidentmstnew);
}

@Override
public String ActionClosure(String keyid, String responsevalue, String targetdate, String reviseddate,
		String status, String correctiveaction, SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {
	    //return sheTlIncidentmstnewDao.AssignActionClosure(keyid,responsevalue,targetdate,reviseddate,status,correctiveaction,sheTlIncidentmstnew);
		return serviceApi.incidentClosure(keyid, responsevalue, targetdate, reviseddate, status, correctiveaction, sheTlIncidentmstnew);
}

@Override
public String ActionVerification(String keyid, String responsevalue, String targetdate, String verifystatus,SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {
	//return sheTlIncidentmstnewDao.ActionVerification(keyid,responsevalue,targetdate,verifystatus,sheTlIncidentmstnew);
	return serviceApi.verifyIncident(keyid,responsevalue,targetdate,verifystatus,sheTlIncidentmstnew);
}

@Override
public String saveClosure(String keyid, String responsevalue, String targetdate, String verifystatus,SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception{
	//return sheTlIncidentmstnewDao.saveClosure(keyid,responsevalue,targetdate,verifystatus,sheTlIncidentmstnew);
	return serviceApi.incidentApproval(keyid,responsevalue,targetdate,verifystatus,sheTlIncidentmstnew);
}

@Override
public List<String[]> getActionPlankeyid(String keyid) throws NoDataFoundException, Exception {
	// TODO Auto-generated method stub
	StringBuffer sql =new StringBuffer();
	
	
	sql.append("SELECT APLM_KEYID,APLD_KEYID from SHE_TL_INCIDENTMSTNEW,GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL where  APLM_MASTERREFID= SINM_KEYID ");
	sql.append("AND APLM_KEYID = APLD_APLM_KEYID AND APLD_RESPONSIBILITY= SINM_RESPONSIBILITY ");
	sql.append("AND APLD_TARGETDATE= SINM_TARGETDATE AND SINM_KEYID='"+keyid+"'");
	
	
	
	
	Object [] args = {} ;
	
	System.out.println("sql:::"+sql.toString());
	
	List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
	
	System.out.println("userDatas:::"+userDatas);
	
	return userDatas;
}

@Override
public List<String> getUpdateList(SheTlIncidentmstnew sheTlIncidentmst) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
       StringBuffer sql =new StringBuffer();
       List<String> sqls=new ArrayList<String>();
//       if(sheTlIncidentmst.getSinmInvestigationRemarks()==null||sheTlIncidentmst.getSinmInvestigationRemarks().length()==0)
//       {
//    	   sheTlIncidentmst.setSinmInvestigationRemarks("-");
//       }
//	
//	sql.append(" UPDATE SHE_TL_INCIDENTMSTNEW SET SINM_ACCIDENT_DESC='"+sheTlIncidentmst.getSinmExactDesc()+"' ");
//	sql.append(", SINM_EXACT_TYPE='"+sheTlIncidentmst.getSinmExactType()+"' ");
//	sql.append(", SINM_ANALYSED_BY='"+sheTlIncidentmst.getSinmAnalysedBy()+"' ");
//	sql.append(",  SINM_ANALYSED_DATE=TO_DATE('"+sheTlIncidentmst.getSinmAnalysedDate()+"', 'DD-Mon-YYYY HH24:MI') ");
//	sql.append(", SINM_ANALYSED_SHIFT='"+sheTlIncidentmst.getSinmAnalysedShift()+"' ");
//	sql.append(",SINM_RPTRECOMMENDATIONS='"+sheTlIncidentmst.getSinmRptrecommendations()+"' ");
//	sql.append(", SINM_INVESTIGATION='"+sheTlIncidentmst.getSinmInvestigation()+"' ");
//	sql.append(", SINM_INVESTRMRKS='"+sheTlIncidentmst.getSinmInvestigationRemarks()+"' ");
//	if(sheTlIncidentmst.getSinmRootcausedueto()==null)
//	{	
//	sql.append(", SINM_ROOTCAUSEDUETO='"+"-"+"'");
//	}
//	else{
//		sql.append(", SINM_ROOTCAUSEDUETO='"+sheTlIncidentmst.getSinmRootcausedueto()+"'");
//	}
//	sql.append(", SINM_STATUS='"+"P"+"'");
//	
//	sql.append(" where SINM_KEYID='"+sheTlIncidentmst.getSinmKeyid()+"'");
//	//sql.append(" SINM_KEYID='"+keyid+"'");
//	sqls.add(sql.toString());
//	System.out.println("sqls"+sqls.toString());
//	dbActionTemplate.executeStatements(sqls);
       
       SheTlIncidentmstnew updatemst = serviceApi.updateIncidentRecord(sheTlIncidentmst);
       
	return sqls;
}

@Override
public SheTlIncidentmstnew delete(SheTlIncidentmstnew newSheTlIncidentmst) throws Exception {
	// TODO Auto-generated method stub
	//return sheTlIncidentmstnewDao.delete(newSheTlIncidentmst);
	return serviceApi.deleteIncident(newSheTlIncidentmst);
}

@Override
public Workbook AccidentReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
		String mode) throws Exception {
	// TODO Auto-generated method stub
	return sheTlIncidentmstnewDao.AccidentReportExportExcel(commonFilter,tblJSONObj,format,mode);
}

@Override
public List<String[]> getNearMissEmpMailIds(String glbType, String flid, String combotype) throws NoDataFoundException, Exception {
	// TODO Auto-generated method stub
	return sheTlIncidentmstnewDao.getNearMissEmpMailIds(glbType,flid,combotype);
}

@Override
public List<String[]> getIncidentReleatedFileManager(String momKeyId) throws Exception{
	// TODO Auto-generated method stub
	return sheTlIncidentmstnewDao.getIncidentReleatedFileManager(momKeyId);
}

public Workbook AccidentExcelView(String keyid, String flid, String format,String path,String imagepath,CommonFilter commonFilter) throws Exception
{
	System.out.println("AccidentExcelView::::");
	List<String[]>  acciodentData=sheTlIncidentmstnewDao.getfillaccidentdata(keyid,flid);
	List<String[]>  accidentEmpData=sheTlIncidentmstnewDao.getfillaccidentEmpData(keyid);
	List<String[]>  accidentresData=sheTlIncidentmstnewDao.getfillaccidentResData(keyid);
	Workbook wb=(new ACCIDENTITCExcelTemplate(dbActionTemplate)).fillValues(acciodentData,accidentEmpData,accidentresData,path,keyid,imagepath,flid);
	//Workbook wb=(new ACCIDENTITCExcelTemplate(dbActionTemplate)).fillValues(acciodentData,accidentEmpData,path);
	//Workbook wb=(new ACCIDENTITCExcelTemplate(dbActionTemplate)).fillValues(accidentEmpData,path);
    return wb;
}


@Override
public List<String[]> getAllImageDetail(String keyId,String fileName,CommonFilter commonFilter)
		throws Exception {
	
	return this.sheTlIncidentmstnewDao.getAllImageDetail(keyId,fileName,commonFilter);
}

public List<GenTlAllmoduleimgfile> getIncidentAllImages(String fileName,String path, String keyId) throws Exception{

	
	List<GenTlAllmoduleimgfile> IncidentImgList = new ArrayList<GenTlAllmoduleimgfile>();
	if(UIUtils.isValidKeyId(keyId))
	{
		//DcmTlDocumentmanager IncidentImage = new DcmTlDocumentmanager();
		GenTlAllmoduleimgfile FirstIncidentImage = new GenTlAllmoduleimgfile();
		
		FirstIncidentImage.setImflBlobimage(path);
		FirstIncidentImage.setImflFilename(fileName);
		FirstIncidentImage.setImflRefkeyid(keyId);
		FirstIncidentImage.setImflRefdoctype("INC");
		FirstIncidentImage.setImflImagetype("IN1");
		IncidentImgList.add(FirstIncidentImage);
		
     GenTlAllmoduleimgfile SecondIncidentImage = new GenTlAllmoduleimgfile();
     SecondIncidentImage.setImflBlobimage(path);
     SecondIncidentImage.setImflFilename(fileName);
     SecondIncidentImage.setImflRefkeyid(keyId);
     SecondIncidentImage.setImflRefdoctype("INC");
     SecondIncidentImage.setImflImagetype("IN2");
	 IncidentImgList.add(SecondIncidentImage);
		
	 GenTlAllmoduleimgfile ThirdIncidentImage = new GenTlAllmoduleimgfile();
	 ThirdIncidentImage.setImflBlobimage(path);
	 ThirdIncidentImage.setImflFilename(fileName);
	 ThirdIncidentImage.setImflRefkeyid(keyId);
	 ThirdIncidentImage.setImflRefdoctype("INC");
	 ThirdIncidentImage.setImflImagetype("IN3");
	 IncidentImgList.add(ThirdIncidentImage);
     
	 GenTlAllmoduleimgfile FourthIncidentImage = new GenTlAllmoduleimgfile();
	 FourthIncidentImage.setImflBlobimage(path);
	 FourthIncidentImage.setImflFilename(fileName);
	 FourthIncidentImage.setImflRefkeyid(keyId);
	 FourthIncidentImage.setImflRefdoctype("INC");
	 FourthIncidentImage.setImflImagetype("IN4");
	 IncidentImgList.add(FourthIncidentImage);
	 
	 GenTlAllmoduleimgfile FithIncidentImage = new GenTlAllmoduleimgfile();
	 FithIncidentImage.setImflBlobimage(path);
	 FithIncidentImage.setImflFilename(fileName);
	 FithIncidentImage.setImflRefkeyid(keyId);
	 FithIncidentImage.setImflRefdoctype("INC");
	 FithIncidentImage.setImflImagetype("IN5");
	 IncidentImgList.add(FithIncidentImage);
	 
	 GenTlAllmoduleimgfile SexthIncidentImage = new GenTlAllmoduleimgfile();
	 SexthIncidentImage.setImflBlobimage(path);
	 SexthIncidentImage.setImflFilename(fileName);
	 SexthIncidentImage.setImflRefkeyid(keyId);
	 SexthIncidentImage.setImflRefdoctype("INC");
	 SexthIncidentImage.setImflImagetype("IN6");
	 IncidentImgList.add(SexthIncidentImage);
	 
	}
	System.out.println("List Size ="+IncidentImgList.size());			
	return this.sheTlIncidentmstnewDao.getIncidentImages(IncidentImgList);
}

@Override
public String getImageCount(String keyid) throws Exception {
	// TODO Auto-generated method stub
	String Query="select count(*) from GEN_TL_ALLMODULEIMGFILE where IMFL_REFKEYID='"+keyid+"'";
	return dbActionTemplate.getSingleValue(Query);
	
}

@Override
public String deleteimage(String keyid, String imagType) throws Exception {
	// TODO Auto-generated method stub
	String Query="delete from GEN_TL_ALLMODULEIMGFILE where IMFL_REFKEYID='"+keyid+"' AND IMFL_IMAGETYPE='"+imagType+"'" ;
	System.out.println("Delete Query::::"+Query);
	return dbActionTemplate.getSingleValue(Query);
}

@Override
public String getempcount(String keyid) throws Exception {
	// TODO Auto-generated method stub
	String Query="select count(*) from SHE_TL_INCIDENT_EMPLOYEENEW where SINE_SINC_KEYID='"+keyid+"'";
	return dbActionTemplate.getSingleValue(Query);
	
}

}
