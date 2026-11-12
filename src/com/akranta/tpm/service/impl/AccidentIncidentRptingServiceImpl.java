package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.SheAccIncBean;
import com.akranta.tpm.bean.SheActionPlanBean;
import com.akranta.tpm.controller.UIUtils;

import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.SheTlActionplanDao;
import com.akranta.tpm.dao.SheTlIncidentEmployeeDao;
import com.akranta.tpm.dao.SheTlIncidentmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.SheTlActionplanDaoImpl;
import com.akranta.tpm.dao.impl.SheTlIncidentEmployeeDaoImpl;
import com.akranta.tpm.dao.impl.SheTlIncidentmstDaoImpl;
import com.akranta.tpm.dao.sql.SheTlIncidentmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.AccidentExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlActionplan;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.model.SheTlIncidentmst;
import com.akranta.tpm.service.AccidentIncidentRptingService;
import com.akranta.tpm.bean.SheTlIncidentEmployeetBean;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class AccidentIncidentRptingServiceImpl implements AccidentIncidentRptingService {
	private SheTlIncidentmstDao sheTlIncidentmstDao;
	private SheTlActionplanDao sheTlActionplanDao;
	private SheTlIncidentEmployeeDao sheTlIncidentEmployeeDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	DBActionTemplate  dbActionTemplate ; 
	public AccidentIncidentRptingServiceImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		sheTlIncidentEmployeeDao=new SheTlIncidentEmployeeDaoImpl(dbActionTemplate);
		sheTlIncidentmstDao = new SheTlIncidentmstDaoImpl(dbActionTemplate);
		sheTlActionplanDao = new SheTlActionplanDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}

	
	

	
	
	public SheTlIncidentmst create(SheTlIncidentmst newSheTlIncidentmst,SheTlIncidentmst oldSheTlIncidentmst,  SheAccIncBean sheAccIncBean ) throws ValidationExceptions, Exception
	{
		String validationsFor;
		validationsFor = "create";
		
		String occuredDate = Constants.passNullDate;
		String reportedDate = Constants.futureNullDate;
		
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSincOccurredDate()))
		{
			if(UIUtils.isValidKeyId(sheAccIncBean.getSincOccurredtime()))
					occuredDate = newSheTlIncidentmst.getSincOccurredDate()+ " "+sheAccIncBean.getSincOccurredtime();
		}
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSincReportedDate()))
		{
			if(UIUtils.isValidKeyId(sheAccIncBean.getSincReportedtime()))
				reportedDate = newSheTlIncidentmst.getSincReportedDate()+ " "+sheAccIncBean.getSincReportedtime();
		}
		
		newSheTlIncidentmst.setSincOccurredDate(occuredDate);		
		newSheTlIncidentmst.setSincReportedDate(reportedDate);
			
		validations.validate(newSheTlIncidentmst,"sheAccidentInc",validationsFor);
		fillValues(newSheTlIncidentmst,oldSheTlIncidentmst,sheAccIncBean);
		return sheTlIncidentmstDao.create(newSheTlIncidentmst);		
	}
	public SheTlIncidentmst update(SheTlIncidentmst newSheTlIncidentmst,SheTlIncidentmst oldSheTlIncidentmst,  SheAccIncBean sheAccIncBean)  throws  BusinessApplicationExceptions , Exception
	{
		String validationsFor = "update";;
		if(UIUtils.isValidKeyId(sheAccIncBean.getFormMode()))
		{
			
			if(sheAccIncBean.getFormMode().trim().equals("create"))
				validationsFor  = "create";
		}
		CommonFunctions.debugMsg(".........................Date "+newSheTlIncidentmst.getSincAnalysedDate());
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedDate()))
		{
			CommonFunctions.debugMsg(".........................Date "+sheAccIncBean.getSincAnalysedtime());
			if(UIUtils.isValidKeyId(sheAccIncBean.getSincAnalysedtime()))
				newSheTlIncidentmst.setSincAnalysedDate(newSheTlIncidentmst.getSincAnalysedDate()+ " "+sheAccIncBean.getSincAnalysedtime());
			CommonFunctions.debugMsg(".........................Date "+newSheTlIncidentmst.getSincAnalysedDate());
		}
		String occuredDate = Constants.passNullDate;
		String reportedDate = Constants.futureNullDate;
		CommonFunctions.debugMsg(".........................Date "+occuredDate);
		//CommonFunctions.debugMsg(oldSheTlIncidentmst.getSincOccurredDate());
		if(oldSheTlIncidentmst != null)
		{
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSincOccurredDate()))
		{
			if(UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincOccurredDate()))
				occuredDate = oldSheTlIncidentmst.getSincOccurredDate();
		}
		
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSincReportedDate()))
		{
			if(UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincReportedDate()))
				reportedDate = oldSheTlIncidentmst.getSincReportedDate();
		}
		}
		
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSincOccurredDate()))
		{
			if(UIUtils.isValidKeyId(sheAccIncBean.getSincOccurredtime()))
					occuredDate = newSheTlIncidentmst.getSincOccurredDate()+ " "+sheAccIncBean.getSincOccurredtime();
		}
		if(UIUtils.isValidKeyId(newSheTlIncidentmst.getSincReportedDate()))
		{
			if(UIUtils.isValidKeyId(sheAccIncBean.getSincReportedtime()))
				reportedDate = newSheTlIncidentmst.getSincReportedDate()+ " "+sheAccIncBean.getSincReportedtime();
		}
		
		newSheTlIncidentmst.setSincOccurredDate(occuredDate);		
		newSheTlIncidentmst.setSincReportedDate(reportedDate);
		
		
			validations.validate(newSheTlIncidentmst,"sheAccidentInc",validationsFor);
		if("C".equals(newSheTlIncidentmst.getSincStatus()))	
		{
			List<String[]> planStatus = dbActionTemplate.getDataList(SheTlIncidentmstSql.selectActionPlanStatus(newSheTlIncidentmst.getSincKeyid()));
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
			
			/* Comment on 30-Sep-2014 yy status not update 
			List<String[]> yyStatus = dbActionTemplate.getDataList(SheTlIncidentmstSql.selectwhywhyStatus(newSheTlIncidentmst.getSincKeyid()));
			CommonFunctions.debugMsg(".....yyStatus....... "+yyStatus);
			
			if(planStatus.size()>0)
			{
				for(int i=0;i<yyStatus.size();i++)
				{
					if(yyStatus.get(i)[0].equals("P"))
					{  
						throw new BusinessApplicationExceptions("WhyWhypending");
					}
				}
				
			}
			*/
			
		}
			
		
		fillValues(newSheTlIncidentmst,oldSheTlIncidentmst,sheAccIncBean);
		return sheTlIncidentmstDao.update(newSheTlIncidentmst);		
	}
	public SheTlIncidentmst delete(SheTlIncidentmst sheTlIncidentmst) throws Exception
	{
		return sheTlIncidentmstDao.delete(sheTlIncidentmst);
	}
	public List<String[]> getAllIncidents(CommonFilter commonFilter) throws Exception
	{
		return sheTlIncidentmstDao.getAllIncidents(commonFilter);
	}
	public SheTlIncidentmst select(String keyid) throws Exception
	{
		return sheTlIncidentmstDao.select(keyid);
	}
	public List<ComboBox> getInjuryComboList(CommonFilter commonFilter,ComboFilter injury) throws Exception 
	{
		//ComboFilter injury = new ComboFilter();
		injury.setIdField("INJM_KEYID");
		//injury.setCodeField("INJM_CODE");
		injury.setNameField("INJM_NAME");
		
		injury.setTableName(TableNames.TBL_SHE_TL_INJURYMODEMST);
		
		return commonFilterDao.fillComboValues(injury);
	}
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
	public List<ComboBox> getBodyPartComboList(CommonFilter commonFilter, ComboFilter BodyPart) throws Exception {
		
	//	ComboFilter BodyPart = new ComboFilter();
		BodyPart.setIdField("IBOP_KEYID");
		BodyPart.setCodeField("IBOP_NAME");
		
		BodyPart.setTableName(TableNames.TBL_SHE_TL_INCIDENTBODYPARTMST);
		
		return commonFilterDao.fillComboValues(BodyPart);
	}
	@Override
	public List<ComboBox> getIncidentTypeComboList(CommonFilter commonFilter, ComboFilter BodyPart) throws Exception {
		
		//ComboFilter BodyPart = new ComboFilter();
		BodyPart.setIdField("ACTY_KEYID");
		BodyPart.setNameField("ACTY_NAME");
		
		BodyPart.setTableName(TableNames.TBL_SHE_TL_ACCIDENT_TYPEMST);
		
		return commonFilterDao.fillComboValues(BodyPart);
	}
	public SheTlIncidentEmployee create(SheTlIncidentEmployee newsheTlIncidentEmployee,SheTlIncidentEmployee existSheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean) throws BusinessApplicationExceptions,ValidationExceptions,Exception {

		try {
			String validationsFor = "create";
		
		
			if(UIUtils.isValidKeyId(sheTlIncidentEmployeetBean.getContract()) && sheTlIncidentEmployeetBean.getContract().equals("Y"))
			{
				validationsFor = "contractcreate";
				validations.validate(sheTlIncidentEmployeetBean,"AccidentIncedentEmployeeCreation",validationsFor);
			}
			
			
			validations.validate(newsheTlIncidentEmployee,"AccidentIncedentEmployeeCreation",validationsFor);
			
			fillValuesincidentEmployee(newsheTlIncidentEmployee,existSheTlIncidentEmployee,sheTlIncidentEmployeetBean);
			//List<String[]> count  =  entBatchCreationDao.getBatchFacultyCount(newEntTlBatchEmployeeLink);
			return sheTlIncidentEmployeeDao.create(newsheTlIncidentEmployee,sheTlIncidentEmployeetBean);
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
	public SheTlIncidentEmployee update(SheTlIncidentEmployee newsheTlIncidentEmployee,SheTlIncidentEmployee existSheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean) throws Exception {
		
		try {
			String validationsFor = "create";
			if(UIUtils.isValidKeyId(sheTlIncidentEmployeetBean.getContract()) && sheTlIncidentEmployeetBean.getContract().equals("Y"))
			{
				validationsFor = "contractcreate";
				validations.validate(sheTlIncidentEmployeetBean,"AccidentIncedentEmployeeCreation",validationsFor);
			}
			validations.validate(newsheTlIncidentEmployee,"AccidentIncedentEmployeeCreation",validationsFor);
			fillValuesincidentEmployee(newsheTlIncidentEmployee,existSheTlIncidentEmployee,sheTlIncidentEmployeetBean);
			
			return sheTlIncidentEmployeeDao.update(newsheTlIncidentEmployee,sheTlIncidentEmployeetBean);
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
		
	}
	public SheTlIncidentEmployee delete(SheTlIncidentEmployee sheTlIncidentEmployee) throws Exception {
		
		return sheTlIncidentEmployeeDao.delete(sheTlIncidentEmployee);
	}
	public SheTlActionplan create(SheTlActionplan newSheTlActionplan,SheTlActionplan existSheTlActionplan,SheActionPlanBean sheActionPlanBean) throws Exception {
		try {
			String validationsFor = "create";
			validations.validate(newSheTlActionplan,"ActionPlan",validationsFor);
			fillValuesActionPlan(newSheTlActionplan,existSheTlActionplan,sheActionPlanBean);
			return sheTlActionplanDao.create(newSheTlActionplan);
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}
		
	}
	public SheTlActionplan update(SheTlActionplan newSheTlActionplan,SheTlActionplan existSheTlActionplan,SheActionPlanBean sheActionPlanBean) throws Exception {
		try {
			String validationsFor = "create";
			validations.validate(newSheTlActionplan,"ActionPlan",validationsFor);
			fillValuesActionPlan(newSheTlActionplan,existSheTlActionplan,sheActionPlanBean);
			return sheTlActionplanDao.update(newSheTlActionplan);
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}
		
	}
	
	
	public List<String[]> getIncidentActionPlan(SheTlActionplan sheTlActionplan)throws Exception {
		return this.sheTlActionplanDao.getIncidentActionPlan(sheTlActionplan);
	}
	public SheTlActionplan deleteActionPlan(SheTlActionplan sheTlActionplan) throws Exception {
		return sheTlActionplanDao.deleteActionPlan(sheTlActionplan);
	}

	private SheTlIncidentEmployee fillValuesincidentEmployee(SheTlIncidentEmployee newsheTlIncidentEmployee,
			SheTlIncidentEmployee existSheTlIncidentEmployee,
			SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean) {
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		if(newsheTlIncidentEmployee.getSiemInjuryModeid()==null)
			newsheTlIncidentEmployee.setSiemInjuryModeid("{}");
		
		if(newsheTlIncidentEmployee.getSiemEmpmKeyid()==null)
			newsheTlIncidentEmployee.setSiemEmpmKeyid("{}");
		
		if( newsheTlIncidentEmployee.getSiemBodypart() == null )
			newsheTlIncidentEmployee.setSiemBodypart("{}");
		
		if( newsheTlIncidentEmployee.getSiemSincKeyid() == null )
			newsheTlIncidentEmployee.setSiemSincKeyid("{}");
		
		if( newsheTlIncidentEmployee.getSiemRemarks() == null )
			newsheTlIncidentEmployee.setSiemRemarks("{}");
		
		if( newsheTlIncidentEmployee.getSiemInjuryTypeid() == null )
			newsheTlIncidentEmployee.setSiemInjuryTypeid("{}");
		
		/*if( newsheTlIncidentEmployee.getSiemCompany() == null )
			newsheTlIncidentEmployee.setSiemCompany("{}");
		else
		{
			if(UIUtils.isValidKeyId(newsheTlIncidentEmployee.getSiemCompany()))
			newsheTlIncidentEmployee.setSiemCompany(newsheTlIncidentEmployee.getSiemCompany().toUpperCase());
		}*/
		
		if( newsheTlIncidentEmployee.getSiemMode() == null )
			newsheTlIncidentEmployee.setSiemMode("-");
		
		if( newsheTlIncidentEmployee.getSiemType() == null )
			newsheTlIncidentEmployee.setSiemType("-");
		
		if( newsheTlIncidentEmployee.getSiemLocation() == null )
			newsheTlIncidentEmployee.setSiemLocation("-");
		
		if( newsheTlIncidentEmployee.getSiemTempfield5() == null )
			newsheTlIncidentEmployee.setSiemTempfield5("-");
		
		return newsheTlIncidentEmployee;
		
	}
	@Override
	public List<String[]> getIncidentEmployee(SheTlIncidentEmployee sheTlIncidentEmployee)throws Exception
	{
		return this.sheTlIncidentEmployeeDao.getIncidentEmployee(sheTlIncidentEmployee);
	}

	private SheTlIncidentmst fillValues(SheTlIncidentmst newSheTlIncidentmst,SheTlIncidentmst oldSheTlIncidentmst,  SheAccIncBean sheAccIncBean) {
		
		newSheTlIncidentmst.setSincActive("Y");
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		newSheTlIncidentmst.setSincCreatedon(dateTime);
		newSheTlIncidentmst.setSincModifiedon(dateTime);
		
		
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincFactKeyid()))
			newSheTlIncidentmst.setSincFactKeyid("{}");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincFlid()))
			newSheTlIncidentmst.setSincFlid("{}");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincElementid()))
			newSheTlIncidentmst.setSincElementid("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincSectKeyid()))
			newSheTlIncidentmst.setSincSectKeyid("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincCellKeyid()))
			newSheTlIncidentmst.setSincCellKeyid("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincMachKeyid()))
			newSheTlIncidentmst.setSincMachKeyid("{}");		
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincOccurredShift()))
			newSheTlIncidentmst.setSincOccurredShift("{}");		
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincReportedShift()))
			newSheTlIncidentmst.setSincReportedShift("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincReportedBy()))
			newSheTlIncidentmst.setSincReportedBy("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincDepartment()))
			newSheTlIncidentmst.setSincDepartment("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincDivision()))
			newSheTlIncidentmst.setSincDivision("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincArea()))
			newSheTlIncidentmst.setSincArea("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAccidentType()))
			newSheTlIncidentmst.setSincAccidentType("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAccidentDesc()))
			newSheTlIncidentmst.setSincAccidentDesc("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincImmediateAction()))
			newSheTlIncidentmst.setSincImmediateAction("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincRecommendations()))
			newSheTlIncidentmst.setSincRecommendations("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincNoofemployees()))
			newSheTlIncidentmst.setSincNoofemployees("0");
		
		if(UIUtils.isValidKeyId(sheAccIncBean.getFormMode()))
		{
			if(sheAccIncBean.getFormMode().equals("create"))
			{
				
				if(oldSheTlIncidentmst != null)
				{
					/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincYyno()))
					{
						//if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedBy()))
							newSheTlIncidentmst.setSincStatus("P");
					}
					else
					{
						//newSheTlIncidentmst.setSincStatus(oldSheTlIncidentmst.getSincStatus());
						newSheTlIncidentmst.setSincStatus("C");
					}*/ // by karthikeyan
			
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincExactDesc()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincExactDesc()))
							newSheTlIncidentmst.setSincExactDesc(newSheTlIncidentmst.getSincAccidentDesc());
					}
					else
					{
						newSheTlIncidentmst.setSincExactDesc(oldSheTlIncidentmst.getSincExactDesc());
					}
			
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincExactType()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincExactType()))
							newSheTlIncidentmst.setSincExactType(newSheTlIncidentmst.getSincAccidentType());
					}
					else
					{
						newSheTlIncidentmst.setSincExactType(oldSheTlIncidentmst.getSincExactType());
					}
					
			
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincAnalysedBy()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedBy()))
							newSheTlIncidentmst.setSincAnalysedBy("{}");
					}
					else
					{
						newSheTlIncidentmst.setSincAnalysedBy(oldSheTlIncidentmst.getSincAnalysedBy());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincAnalysedDate()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedDate()))
							newSheTlIncidentmst.setSincAnalysedDate(Constants.passNullDate);
					}
					else
					{
						newSheTlIncidentmst.setSincAnalysedDate(oldSheTlIncidentmst.getSincAnalysedDate());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincPhenomena()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincPhenomena()))
							newSheTlIncidentmst.setSincPhenomena("{}");
					}
					else
					{
						newSheTlIncidentmst.setSincPhenomena(oldSheTlIncidentmst.getSincPhenomena());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincWhywhyflag()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincWhywhyflag()))
							newSheTlIncidentmst.setSincWhywhyflag("P");
					}
					else
					{
						newSheTlIncidentmst.setSincWhywhyflag(oldSheTlIncidentmst.getSincWhywhyflag());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincRootcause()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincRootcause()))
							newSheTlIncidentmst.setSincRootcause("{}");
					}
					else
					{
						newSheTlIncidentmst.setSincRootcause(oldSheTlIncidentmst.getSincRootcause());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincCountermeasure()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincCountermeasure()))
							newSheTlIncidentmst.setSincCountermeasure("{}");
					}
					else
					{
						newSheTlIncidentmst.setSincCountermeasure(oldSheTlIncidentmst.getSincCountermeasure());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincRootcausedueto()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincRootcausedueto()))
							newSheTlIncidentmst.setSincRootcausedueto("-");
					}
					else
					{
						newSheTlIncidentmst.setSincRootcausedueto(oldSheTlIncidentmst.getSincRootcausedueto());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincIdea()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincIdea()))
							newSheTlIncidentmst.setSincIdea("{}");
					}
					else
					{
						newSheTlIncidentmst.setSincIdea(oldSheTlIncidentmst.getSincIdea());
					}
				
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincAnalysedShift()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedShift()))
							newSheTlIncidentmst.setSincAnalysedShift("{}");
					}
					else
					{
						newSheTlIncidentmst.setSincAnalysedShift(oldSheTlIncidentmst.getSincAnalysedShift());
					}
					if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincYyno()))
					{
						if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincYyno()))
							newSheTlIncidentmst.setSincYyno("{}");
					}
					else
					{
						newSheTlIncidentmst.setSincYyno(oldSheTlIncidentmst.getSincYyno());
					}
				}
				else
				{
					
					fillAnalysisValues(newSheTlIncidentmst);
				}
			}
			else
			{
				//CommonFunctions.debugMsg("WHY WHY else : "+oldSheTlIncidentmst.getSincWhywhyflag());
				fillSafetyAnalysisValues(newSheTlIncidentmst,oldSheTlIncidentmst);
			}
		}
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincLossproperty()))
			newSheTlIncidentmst.setSincLossproperty("{}");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincDirectcost()))
			newSheTlIncidentmst.setSincDirectcost("0");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield5()))
			newSheTlIncidentmst.setSincTempfield5("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield6()))
			newSheTlIncidentmst.setSincTempfield6("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield7()))
			newSheTlIncidentmst.setSincTempfield7("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield8()))
			newSheTlIncidentmst.setSincTempfield8("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield9()))
			newSheTlIncidentmst.setSincTempfield9("-");		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield10()))
			newSheTlIncidentmst.setSincTempfield10("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield11()))
			newSheTlIncidentmst.setSincTempfield11("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield12()))
			newSheTlIncidentmst.setSincTempfield12("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield13()))
			newSheTlIncidentmst.setSincTempfield13("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield14()))
			newSheTlIncidentmst.setSincTempfield14("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield15()))
			newSheTlIncidentmst.setSincTempfield15("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield16()))
			newSheTlIncidentmst.setSincTempfield16("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield17()))
			newSheTlIncidentmst.setSincTempfield17("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield18()))
			newSheTlIncidentmst.setSincTempfield18("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield19()))
			newSheTlIncidentmst.setSincTempfield19("-");
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincTempfield20()))
			newSheTlIncidentmst.setSincTempfield20("-");
		
		return newSheTlIncidentmst;
	}

private SheTlIncidentmst fillAnalysisValues(SheTlIncidentmst newSheTlIncidentmst)
{
	
	CommonFunctions.debugMsg("fill values.........."+newSheTlIncidentmst.getSincStatus());
	CommonFunctions.debugMsg("fill values.........."+newSheTlIncidentmst.getSincAnalysedBy());
	/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincStatus()))
		newSheTlIncidentmst.setSincStatus("P");*/
	//by karthikeyan
	/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincYyno()))
		newSheTlIncidentmst.setSincStatus("P");
	else 
		newSheTlIncidentmst.setSincStatus("C"); */
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincExactDesc()))
		newSheTlIncidentmst.setSincExactDesc(newSheTlIncidentmst.getSincAccidentDesc());
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincExactType()))
		newSheTlIncidentmst.setSincExactType(newSheTlIncidentmst.getSincAccidentType());
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedBy()))
		newSheTlIncidentmst.setSincAnalysedBy("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedDate()))
		newSheTlIncidentmst.setSincAnalysedDate(Constants.passNullDate);
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincPhenomena()))
		newSheTlIncidentmst.setSincPhenomena("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincWhywhyflag()))
		newSheTlIncidentmst.setSincWhywhyflag("P");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincRootcause()))
		newSheTlIncidentmst.setSincRootcause("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincCountermeasure()))
		newSheTlIncidentmst.setSincCountermeasure("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincRootcausedueto()))
		newSheTlIncidentmst.setSincRootcausedueto("-");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincIdea()))
		newSheTlIncidentmst.setSincIdea("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedShift()))
		newSheTlIncidentmst.setSincAnalysedShift("{}");
	
	if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincYyno()))
		newSheTlIncidentmst.setSincYyno("{}");
	
	
	return newSheTlIncidentmst;
}
private SheTlIncidentmst fillSafetyAnalysisValues(SheTlIncidentmst newSheTlIncidentmst,SheTlIncidentmst oldSheTlIncidentmst)
{
	
	CommonFunctions.debugMsg("fill values.........."+newSheTlIncidentmst.getSincStatus());
	CommonFunctions.debugMsg("fill values.........."+newSheTlIncidentmst.getSincAnalysedBy());
	/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincStatus()))
		newSheTlIncidentmst.setSincStatus("P");*/
	if(oldSheTlIncidentmst != null)
	{
		/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincYyno()))
		{
			//if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedBy()))
				newSheTlIncidentmst.setSincStatus("P");
		}
		else
		{
			//newSheTlIncidentmst.setSincStatus(oldSheTlIncidentmst.getSincStatus());
			newSheTlIncidentmst.setSincStatus("C");
		} */ // by karthikeyan

		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincExactDesc()))
		{
			if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincExactDesc()))
				newSheTlIncidentmst.setSincExactDesc(oldSheTlIncidentmst.getSincAccidentDesc());
			else
				newSheTlIncidentmst.setSincExactDesc("{}");
		}
		

		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincExactType()))
		{
			if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincExactType()))
				newSheTlIncidentmst.setSincExactType(oldSheTlIncidentmst.getSincAccidentType());
			else
				newSheTlIncidentmst.setSincExactType("{}");
		}
		
		

		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedBy()))
		{
			//if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincAnalysedBy()))
				//newSheTlIncidentmst.setSincAnalysedBy(oldSheTlIncidentmst.getSincAnalysedBy());
			//else
				newSheTlIncidentmst.setSincAnalysedBy("{}");
		}
	
	//	if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincAnalysedDate()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedDate()))
				newSheTlIncidentmst.setSincAnalysedDate(Constants.passNullDate);
		//}
			//else
			//{
			//newSheTlIncidentmst.setSincAnalysedDate(newSheTlIncidentmst.getSincAnalysedDate());
			//}
	
		//if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincPhenomena()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincPhenomena()))
				newSheTlIncidentmst.setSincPhenomena("{}");
		//}
		//else
		//{
			//newSheTlIncidentmst.setSincPhenomena(oldSheTlIncidentmst.getSincPhenomena());
		//}
	
		//if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincWhywhyflag()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincWhywhyflag()))
				newSheTlIncidentmst.setSincWhywhyflag("P");
		//}
		else
		{
			newSheTlIncidentmst.setSincWhywhyflag(oldSheTlIncidentmst.getSincWhywhyflag());
		}
	
		//if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincRootcause()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincRootcause()))
				newSheTlIncidentmst.setSincRootcause("{}");
		//}
		//else
		//{
			//newSheTlIncidentmst.setSincRootcause(oldSheTlIncidentmst.getSincRootcause());
		//}
	
	//	if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincCountermeasure()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincCountermeasure()))
				newSheTlIncidentmst.setSincCountermeasure("{}");
		//}
		//else
		//{
		//	newSheTlIncidentmst.setSincCountermeasure(oldSheTlIncidentmst.getSincCountermeasure());
		//}
	
	//	if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincRootcausedueto()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincRootcausedueto()))
				newSheTlIncidentmst.setSincRootcausedueto("-");
	//	}
		//else
		//{
		//	newSheTlIncidentmst.setSincRootcausedueto(oldSheTlIncidentmst.getSincRootcausedueto());
		//}
	
		//if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincIdea()))
		//{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincIdea()))
				newSheTlIncidentmst.setSincIdea("{}");
		//}
		//else
		//{
		//	newSheTlIncidentmst.setSincIdea(oldSheTlIncidentmst.getSincIdea());
		//}
	
	//	if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincAnalysedShift()))
	//	{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedShift()))
				newSheTlIncidentmst.setSincAnalysedShift("{}");
	//	}
	//	else
	//	{
	//		newSheTlIncidentmst.setSincAnalysedShift(oldSheTlIncidentmst.getSincAnalysedShift());
		//}
		if(!UIUtils.isValidKeyId(oldSheTlIncidentmst.getSincYyno()))
		{
			if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincYyno()))
				newSheTlIncidentmst.setSincYyno("{}");
		}
		else
		{
			newSheTlIncidentmst.setSincYyno(oldSheTlIncidentmst.getSincYyno());
		}
	}
	else
	{
		/*if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincYyno()))
			newSheTlIncidentmst.setSincStatus("P");
		else 
			newSheTlIncidentmst.setSincStatus("C"); *///by karthikeyan
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincExactDesc()))
			newSheTlIncidentmst.setSincExactDesc(newSheTlIncidentmst.getSincAccidentDesc());
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincExactType()))
			newSheTlIncidentmst.setSincExactType(newSheTlIncidentmst.getSincAccidentType());
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedBy()))
			newSheTlIncidentmst.setSincAnalysedBy("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedDate()))
			newSheTlIncidentmst.setSincAnalysedDate(Constants.passNullDate);
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincPhenomena()))
			newSheTlIncidentmst.setSincPhenomena("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincWhywhyflag()))
			newSheTlIncidentmst.setSincWhywhyflag("P");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincRootcause()))
			newSheTlIncidentmst.setSincRootcause("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincCountermeasure()))
			newSheTlIncidentmst.setSincCountermeasure("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincRootcausedueto()))
			newSheTlIncidentmst.setSincRootcausedueto("-");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincIdea()))
			newSheTlIncidentmst.setSincIdea("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincAnalysedShift()))
			newSheTlIncidentmst.setSincAnalysedShift("{}");
		
		if(!UIUtils.isValidKeyId(newSheTlIncidentmst.getSincYyno()))
			newSheTlIncidentmst.setSincYyno("{}");
	}
	
	
	return newSheTlIncidentmst;
}


private SheTlActionplan fillValuesActionPlan(SheTlActionplan newSheTlActionplan,SheTlActionplan existSheTlActionplan,SheActionPlanBean sheActionPlanBean) {
		
		CommonFunctions.debugMsg("test data fill values...............");
		
			String dateTime = CommonFunctions.dateTimeNow();
		
		if(newSheTlActionplan.getSacpCompletedBy()==null)
			newSheTlActionplan.setSacpCompletedBy("{}");
		
		if(newSheTlActionplan.getSacpCompletedDate()==null)
			newSheTlActionplan.setSacpCompletedDate(Constants.passNullDate);
		
		if( newSheTlActionplan.getSacpCreatedBy() == null )
			newSheTlActionplan.setSacpCreatedBy("{}");
		
		if( newSheTlActionplan.getSacpCreatedDate() == null )
			newSheTlActionplan.setSacpCreatedDate(Constants.passNullDate);
		
		if( newSheTlActionplan.getSacpDescription() == null )
			newSheTlActionplan.setSacpDescription("{}");
		
		if( newSheTlActionplan.getSacpKeyid() == null )
			newSheTlActionplan.setSacpKeyid("{}");
		
		if( newSheTlActionplan.getSacpRemarks() == null )
			newSheTlActionplan.setSacpRemarks("{}");
		
		if( newSheTlActionplan.getSacpRespDept() == null )
			newSheTlActionplan.setSacpRespDept("{}");
		
		if( newSheTlActionplan.getSacpResponsiblity() == null )
			newSheTlActionplan.setSacpResponsiblity("{}");
		
		if( newSheTlActionplan.getSacpSincKeyid() == null )
			newSheTlActionplan.setSacpSincKeyid("{}");
		
		if( newSheTlActionplan.getSacpStatus() == null )
			newSheTlActionplan.setSacpStatus("P");
		
		if( newSheTlActionplan.getSacpTargetDate() == null )
			newSheTlActionplan.setSacpTargetDate(Constants.passNullDate);
		
		if( newSheTlActionplan.getSacpTempfield1() == null )
			newSheTlActionplan.setSacpTempfield1("-");
		
		if( newSheTlActionplan.getSacpTempfield2() == null )
			newSheTlActionplan.setSacpTempfield2("-");
		
		if( newSheTlActionplan.getSacpTempfield3() == null )
			newSheTlActionplan.setSacpTempfield3("-");
		
		if( newSheTlActionplan.getSacpTempfield4() == null )
			newSheTlActionplan.setSacpTempfield4("-");
		
		if( newSheTlActionplan.getSacpTempfield5() == null )
			newSheTlActionplan.setSacpTempfield5("-");
		
		if( newSheTlActionplan.getSacpTempfield6() == null )
			newSheTlActionplan.setSacpTempfield6("-");
		
		if( newSheTlActionplan.getSacpTempfield7() == null )
			newSheTlActionplan.setSacpTempfield7("-");
		
		if( newSheTlActionplan.getSacpTempfield8() == null )
			newSheTlActionplan.setSacpTempfield8("-");
		
		if( newSheTlActionplan.getSacpTempfield9() == null )
			newSheTlActionplan.setSacpTempfield9("-");
		
		if( newSheTlActionplan.getSacpTempfield10() == null )
			newSheTlActionplan.setSacpTempfield10("-");
		
		CommonFunctions.debugMsg("test data fill values...............");
		return newSheTlActionplan;
		
	}


@Override
public SheTlIncidentEmployee recallEmployee(String keyId) throws Exception {
	return sheTlIncidentmstDao.recallEmployee(keyId);
}

public SheTlActionplan recallAction(String keyId) throws Exception {
	return sheTlIncidentmstDao.recallAction(keyId);
}


@Override
public Workbook accidentIncidentReportExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
	return sheTlIncidentmstDao.accidentIncidentReportExportExcel(commonFilter,tblJSONObj,format);
}

public List<String[]> getAccidentInvest(CommonFilter commonFilter)throws Exception {
	return this.sheTlIncidentmstDao.getAccidentInvest(commonFilter);
}


@Override
public Workbook getAccInvestExportExcel(String format, String path,	String imagePath, CommonFilter commonFilter,String insId) throws Exception {
	// List<String[]> kaizenData = oplDao.getOplExcelReport(oPLId,format,commonFilter);
	//List<String[]> AccInvestData = sheTlIncidentmstDao.getAccInvestExportExcel(format,commonFilter);
	Map<Integer, List<String[]>> AccInvestData = sheTlIncidentmstDao.getAccInvestExportExcel(format,commonFilter,insId);
	//return this.improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReport(kaizId,format);
	
	CommonFunctions.debugMsg("Service impl 123.........");
	Workbook wb = ( new AccidentExcelTemplate(dbActionTemplate  )).fillValues(AccInvestData,format,path,imagePath,insId);
	
	CommonFunctions.debugMsg("Service implto be gae........."+wb);
	return wb;
}


@Override
public Workbook getAccidentInvestExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
	return this.sheTlIncidentmstDao.getAccidentInvestExportExcel(commonFilter,colmodel,rptFormat);
}


@Override
public List<GenTlAllmoduleimgfile> saveImg(SheTlIncidentmst sheTlIncidentmst) throws Exception,BusinessApplicationExceptions {
	List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = sheTlIncidentmst.getAllmoduleimgfile();
	System.out.println("newGenTlAllmoduleimgfile"+newGenTlAllmoduleimgfile.size()+""+newGenTlAllmoduleimgfile);
	if( newGenTlAllmoduleimgfile != null )//&& CommonFunctions.isValidKeyId(newGenTlAllmoduleimgfile.getToimFilename()) )
	{
		
		newGenTlAllmoduleimgfile = fillImgValues(sheTlIncidentmst);
		return this.sheTlIncidentmstDao.saveOplImg(sheTlIncidentmst);
	}
	return null;
	
}


private List<GenTlAllmoduleimgfile> fillImgValues(SheTlIncidentmst sheTlIncidentmst) {

	List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = sheTlIncidentmst.getAllmoduleimgfile();
	List<GenTlAllmoduleimgfile> genTlAllmoduleimgfileList =new ArrayList<GenTlAllmoduleimgfile>();
	for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:newGenTlAllmoduleimgfile)
	{
		System.out.println("lllllll");
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println(dateTime);
		
		genTlAllmoduleimgfile.setImflRefkeyid(sheTlIncidentmst.getSincKeyid());
		genTlAllmoduleimgfile.setImflModifiedon(sheTlIncidentmst.getSincModifiedon());
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
public SheAccIncBean getImage(String fileName, String filePath,SheAccIncBean sheAccIncBean,SheTlIncidentmst sheTlIncidentmst) throws Exception {
	
	System.out.println("Inside Service Impl img");
	List<GenTlAllmoduleimgfile> insImgList = new ArrayList<GenTlAllmoduleimgfile>();
	if(sheTlIncidentmst!=null)
	{

		GenTlAllmoduleimgfile oplBeforeImage = new GenTlAllmoduleimgfile();
			
		oplBeforeImage.setImflBlobimage(filePath);
		oplBeforeImage.setImflFilename(fileName);
		oplBeforeImage.setImflRefkeyid(sheTlIncidentmst.getSincKeyid());
		oplBeforeImage.setImflRefdoctype("INC");
		oplBeforeImage.setImflImagetype("PRE");
		insImgList.add(oplBeforeImage);
		
		GenTlAllmoduleimgfile oplAfterImage = new GenTlAllmoduleimgfile();
		
		oplAfterImage.setImflBlobimage(filePath);
		oplAfterImage.setImflFilename(fileName);
		oplAfterImage.setImflRefkeyid(sheTlIncidentmst.getSincKeyid());
		oplAfterImage.setImflRefdoctype("INC");
		oplAfterImage.setImflImagetype("AFT");
		insImgList.add(oplAfterImage);

	}
	insImgList = sheTlIncidentmstDao.getImage(insImgList);
	for( GenTlAllmoduleimgfile genTlAllmoduleimgfile : insImgList){
		if( "AFT".equals(genTlAllmoduleimgfile.getImflImagetype() ))
			sheAccIncBean.setAfter(genTlAllmoduleimgfile.getImflFilename());
		else if( "PRE".equals(genTlAllmoduleimgfile.getImflImagetype()) )
			sheAccIncBean.setBefore(genTlAllmoduleimgfile.getImflFilename());
	}
	//oplTlMst.setAllmoduleimgfile(oplImgList);
	return sheAccIncBean;
}


@Override
public List<GenTlAllmoduleimgfile> getImageExcel(String fileName,String filePath, String insKeyid) throws Exception {
	
	System.out.println("Inside Service Impl EXL image");
	List<GenTlAllmoduleimgfile> insImgList = new ArrayList<GenTlAllmoduleimgfile>();
	if(UIUtils.isValidKeyId(insKeyid))
	{

		GenTlAllmoduleimgfile oplBeforeImage = new GenTlAllmoduleimgfile();
			
		oplBeforeImage.setImflBlobimage(filePath);
		oplBeforeImage.setImflFilename(fileName);
		oplBeforeImage.setImflRefkeyid(insKeyid);
		oplBeforeImage.setImflRefdoctype("INC");
		oplBeforeImage.setImflImagetype("PRE");
		insImgList.add(oplBeforeImage);
		
		GenTlAllmoduleimgfile oplAfterImage = new GenTlAllmoduleimgfile();
		
		oplAfterImage.setImflBlobimage(filePath);
		oplAfterImage.setImflFilename(fileName);
		oplAfterImage.setImflRefkeyid(insKeyid);
		oplAfterImage.setImflRefdoctype("INC");
		oplAfterImage.setImflImagetype("AFT");
		insImgList.add(oplAfterImage);

	}
	
//	oplTlMst.setAllmoduleimgfile(oplImgList);
//	return oplTlMst;
	System.out.println("List Size ="+insImgList.size());
	System.out.println("List Size ="+insImgList.get(0).getImflFilename());
			
	return sheTlIncidentmstDao.getImageExcel(insImgList);
	//return this.oplDao.getoplImage(oplImgList);
}


@Override
public List<String[]> getAccidentInvRpt(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return this.sheTlIncidentmstDao.getAccidentInvRpt(commonFilter);
}


@Override
public Workbook AccInvRptExcel(CommonFilter commonFilter, JSONObject colmodel,String format) throws SQLException, IOException, Exception {
	// TODO Auto-generated method stub
	return this.sheTlIncidentmstDao.AccInvRptExcel(commonFilter,colmodel,format);
}


	
	
}



