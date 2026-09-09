package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EntTlProgTargetRolesBean;
import com.akranta.tpm.bean.EntTlProgTargetSkillsBean;
import com.akranta.tpm.bean.ProgrammstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlProgTargetRolesDao;
import com.akranta.tpm.dao.EntTlProgTargetSkillsDao;
import com.akranta.tpm.dao.EntTlProgrammstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EntTlProgTargetRolesDaoImpl;
import com.akranta.tpm.dao.impl.EntTlProgTargetSkillsDaoImpl;
import com.akranta.tpm.dao.impl.EntTlProgrammstDaoImpl;
import com.akranta.tpm.dao.impl.PlmTlGenmaintenanceDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.EntTlProgImpactSkills;
import com.akranta.tpm.model.EntTlProgTargetRoles;
import com.akranta.tpm.model.EntTlProgTargetSkills;
import com.akranta.tpm.model.EntTlProgrammst;
import com.akranta.tpm.model.EntTlRoleSkillRating;
import com.akranta.tpm.service.EntTlProgrammstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EntTlProgrammstServiceImpl implements EntTlProgrammstService {
	private CommonFilterDao commonFilterDao;
	private EntTlProgrammstDao entTlProgrammstDao;
	private EntTlProgTargetRolesDao entTlProgTargetRolesDao;
	private Validations validations ;
	private EntTlProgTargetSkillsDao entTlProgTargetSkillsDao;
	public EntTlProgrammstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entTlProgrammstDao = new EntTlProgrammstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
		/*Skill master*/
		entTlProgTargetSkillsDao= new EntTlProgTargetSkillsDaoImpl(dbActionTemplate);
		entTlProgTargetRolesDao = new EntTlProgTargetRolesDaoImpl(dbActionTemplate); 
	}
	@Override
	public List<ComboBox> getProgkeyidCombo(String condsql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setIdField("PROG_KEYID");
		comboFilter.setNameField("PROG_NAME || '-' || ROLE_NAME " );		
		
		if( UIUtils.isValidKeyId(condsql)  ){
			comboFilter.setCondSql(" AND ROLE_KEYID = PROG_UNIQUEPOS  AND PROG_TRAR_KEYID = '" + condsql + "'");
		}
		comboFilter.setTableName(" ENT_TL_PROGRAMMST, GEN_TL_ROLEMST " );
		comboFilter.setCondSql(" AND ROLE_KEYID = PROG_UNIQUEPOS " );

		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getProgkeyid1levCombo(String condsql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setIdField("PROG_KEYID");
		comboFilter.setNameField("PROG_NAME" );	
		comboFilter.setCondSql(" AND PROG_KEYID in ( SELECT ASMD_PROG_KEYID FROM ENT_TL_ASSESSMENTDTL  WHERE (ASMD_CURRENT_RATE >1  AND ASMD_CURRENT_RATE <=3) ) ");	
		comboFilter.setTableName(" ENT_TL_PROGRAMMST " );
		

		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<ComboBox> getProgkeyid4levCombo(String condsql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setIdField("PROG_KEYID");
		comboFilter.setNameField("PROG_NAME" );	
		comboFilter.setCondSql(" AND PROG_KEYID in ( SELECT ASMD_PROG_KEYID FROM ENT_TL_ASSESSMENTDTL  WHERE (ASMD_CURRENT_RATE >=3  AND ASMD_CURRENT_RATE <=4) ) ");	
		comboFilter.setTableName(" ENT_TL_PROGRAMMST " );
		

		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getSkillTypeCombo(String condsql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("STYP_NAME");
		comboFilter.setIdField("STYP_KEYID");
		/*if( UIUtils.isValidKeyId(condsql)  ){
			comboFilter.setCondSql(" AND STYP_KEYID = '" + condsql + "'");
		}*/
		comboFilter.setTableName(TableNames.TBL_ENT_TL_SKILLTYPE);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getSpokeKeyidCombo(String keyId,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("SPOK_NAME");
		comboFilter.setIdField("SPOK_KEYID");
		if(UIUtils.isValidKeyId(keyId))
			comboFilter.setCondSql("AND SPOK_KEYID in (select distinct SPOKID from ent_vw_spoketopic  where TOPICID='"+keyId+"' )");
		//if(UIUtils.isValidKeyId(ProgkeyId))
			//comboFilter.setCondSql("AND SPOK_KEYID in (select distinct PROG_SPOKE_KEYID from ent_tl_programmst  where PROG_SPOKE_KEYID='"+ProgkeyId+"' )");
		comboFilter.setTableName(TableNames.TBL_ENT_TL_SPOKEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	/*Skill Combo Fill*/
	@Override
	public List<ComboBox> getDelvModeCombo(String condSql,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("TMOD_NAME");
		comboFilter.setIdField("TMOD_KEYID");
		comboFilter.setTableName(TableNames.TBL_ENT_TL_DELIVERYMODEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getEvalTypeCombo(String condSql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("EVAL_NAME");
		comboFilter.setCode("EVAL_CODE");
		comboFilter.setIdField("EVAL_KEYID");
		comboFilter.setTableName(TableNames.TBL_ENT_TL_EVALUATIONTYPEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<ComboBox> getSkillkeyidCombo(String condSql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("TOPI_NAME");
		comboFilter.setCode("TOPI_CODE");
		comboFilter.setIdField("TOPI_KEYID");
		comboFilter.setCondSql(" AND TOPI_ISCHILD = 'Y'" );
		comboFilter.setTableName(TableNames.TBL_ENT_TL_TOPICMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getRoleCombo(String Rtalid,String progKeyid,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setIdField("RTAL_KEYID");
		comboFilter.setNameField("ROLENAME");
		//comboFilter.setOrderByField("ROLE_NAME");		
		
		if( UIUtils.isValidKeyId(Rtalid)  ){
			if(Rtalid.startsWith("TRA"))
				comboFilter.setCondSql(" AND RTAL_TRAR_KEYID = '"+Rtalid+"'");
			else
				comboFilter.setCondSql(" AND RTAL_KEYID = '"+Rtalid+"'");
			
		}
		if( UIUtils.isValidKeyId(progKeyid)){
			String sql = " AND RTAL_KEYID NOT IN(SELECT RTAL_KEYID FROM ENT_TL_PROG_TARGET_ROLES,ENT_VW_TRAININGAREAROLE," +
					" ENT_VW_TRAININGAREACHILDPATH WHERE   PRTR_TRAR_KEYID =RTAL_KEYID  AND RTAL_TRAR_KEYID = KEYID AND PRTR_PROG_KEYID ='"+progKeyid+"')";
			comboFilter.setCondSql(" AND RTAL_TRAR_KEYID = '"+Rtalid+"'" +sql );
		}
		comboFilter.setTableName(TableNames.TBL_ENT_VW_TRAININGAREAROLE);		
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public EntTlProgrammst create(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
		try{
			String validationsFor = "create";
			CommonMessage.debugMsg("Inside the ServiceImpl Create");
			validations.validate(newEntTlProgrammst,"Entprogram",validationsFor);
			fillValues(newEntTlProgrammst,existEntTlProgrammst,programmstBean);
			CommonMessage.debugMsg("After Filling tool Values");
			//for inserting in clisCalendar procedure
			
		}catch (ValidationExceptions e){
			
			e.printStackTrace();
			CommonMessage.debugMsg(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	catch(Exception e){
			
		}
		return  entTlProgrammstDao.create(newEntTlProgrammst);
	}
	@Override
	public EntTlProgrammst update(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
		try{
			String validationsFor = "update";
			CommonMessage.debugMsg("Inside the ServiceImpl update");
			validations.validate(newEntTlProgrammst,"Entprogram",validationsFor);
			fillValues(newEntTlProgrammst,existEntTlProgrammst,programmstBean);
			CommonMessage.debugMsg("After Filling tool Values");
			//for inserting in clisCalendar procedure
			
			}catch (ValidationExceptions e){
			e.printStackTrace();
			CommonMessage.debugMsg(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}catch(Exception e){
			
		}
		return  entTlProgrammstDao.update(newEntTlProgrammst);
	}
	private EntTlProgrammst fillValues(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean) {
		newEntTlProgrammst.setProgActive("Y");
		/*if(newEntTlProgrammst.getProgKeyid() == null ){
		CommonMessage.debugMsg("fillvalues  :"+newEntTlProgrammst.getProgKeyid());
		CommonMessage.debugMsg("newprogBenift  :"+newEntTlProgrammst.getProgBenifit() );
		}
		if(existEntTlProgrammst.getProgKeyid() != null ){
		CommonMessage.debugMsg("existprogkwy   :"+existEntTlProgrammst.getProgKeyid());
		CommonMessage.debugMsg("exist progBenift  :"+existEntTlProgrammst.getProgBenifit() );
		}
*/		if(newEntTlProgrammst.getProgKeyid() != null ){
		CommonMessage.debugMsg("fillvalues  :"+newEntTlProgrammst.getProgKeyid());
		CommonMessage.debugMsg("fillvaluescrdton   :"+newEntTlProgrammst.getProgCreatedon());
	    }
	
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgKeyid() ))
			newEntTlProgrammst.setProgCreatedon(dateTime);
		else{
			
				newEntTlProgrammst.setProgCreatedon(existEntTlProgrammst.getProgCreatedon());
			
		}
		newEntTlProgrammst.setProgModifiedon(dateTime);
		
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgBenifit()  ))
			newEntTlProgrammst.setProgBenifit("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgCode()  ))
			newEntTlProgrammst.setProgCode("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgContactInfo()  ))
			newEntTlProgrammst.setProgContactInfo("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTrarKeyid()  ))
			newEntTlProgrammst.setProgTrarKeyid("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgIsEvaluationNeed()  ))
			newEntTlProgrammst.setProgIsEvaluationNeed("N");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgMaxDuration()  ))
			newEntTlProgrammst.setProgMaxDuration("0");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgMinDuration()  ))
			newEntTlProgrammst.setProgMinDuration("0");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgName()  ))
			newEntTlProgrammst.setProgName("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgPurpose()  ))
			newEntTlProgrammst.setProgPurpose("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgRemarks()  ))
			newEntTlProgrammst.setProgRemarks("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgType()  ))
			newEntTlProgrammst.setProgType("X");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgSpokeKeyid()  ))
			newEntTlProgrammst.setProgSpokeKeyid("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgFunction()  ))
			newEntTlProgrammst.setProgFunction("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgUniquepos()))
			newEntTlProgrammst.setProgUniquepos("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgMonth()  ))
			newEntTlProgrammst.setProgMonth("X");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgMaterialReady() ))
			newEntTlProgrammst.setProgMaterialReady("N");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTgtmKeyid()  ))
			newEntTlProgrammst.setProgTgtmKeyid("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgRepeatedProgram()))
			newEntTlProgrammst.setProgRepeatedProgram("N");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgFrequency()))
			newEntTlProgrammst.setProgFrequency("{}");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgEffectiveFrom()))
			newEntTlProgrammst.setProgEffectiveFrom(Constants.passNullDate);
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgEffectiveTill()))
			newEntTlProgrammst.setProgEffectiveTill(Constants.futureNullDate);
		
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgActive()  ))
			newEntTlProgrammst.setProgActive("Y");

	/*	if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTempfield3()  ))
			newEntTlProgrammst.setProgTempfield3("X");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTempfield4()  ))
			newEntTlProgrammst.setProgTempfield4("X");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTempfield5()  ))
			newEntTlProgrammst.setProgTempfield5("X");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTempfield6()  ))
			newEntTlProgrammst.setProgTempfield6("X");
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTempfield7()  ))
			newEntTlProgrammst.setProgTempfield7("X");*/
		
		CommonMessage.debugMsg(dateTime);
		
		return newEntTlProgrammst;
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<String[]> getgridData() throws Exception {
		// TODO Auto-generated method stub
		return entTlProgrammstDao.getgridData();
	}
	@Override
	public EntTlProgrammst fillFormvalues(String progKey) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		return entTlProgrammstDao.fillFormvalues(progKey);
	}
	@Override
	public EntTlProgTargetSkills createSkill(EntTlProgTargetSkills newEntTlProgTargetSkills,EntTlProgTargetSkills existEntTlProgTargetSkills,
			EntTlProgTargetSkillsBean entTlProgTargetSkillsBean) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
		try{
			String validationsFor = "create";
			CommonMessage.debugMsg("Inside the ServiceImpl Create");
			validations.validate(newEntTlProgTargetSkills,"EntSkillprogram",validationsFor);
			CommonMessage.debugMsg("BEFORE Filling skill Values");
			fillValuesSkill(newEntTlProgTargetSkills,existEntTlProgTargetSkills,entTlProgTargetSkillsBean);
			CommonMessage.debugMsg("After Filling skill Values");
		
		}catch (ValidationExceptions e){
			e.printStackTrace();
			CommonMessage.debugMsg(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	catch(Exception e){
			
		}
		return  entTlProgTargetSkillsDao.create(newEntTlProgTargetSkills);
	}
	@Override
	public EntTlProgTargetSkills updateSkill(EntTlProgTargetSkills newEntTlProgTargetSkills,EntTlProgTargetSkills existEntTlProgTargetSkills,
			EntTlProgTargetSkillsBean entTlProgTargetSkillsBean) throws BusinessApplicationExceptions,Exception {
		// TODO Auto-generated method stub
		try{
			String validationsFor = "update";
			CommonMessage.debugMsg("Inside the ServiceImpl Update");
			validations.validate(newEntTlProgTargetSkills,"EntSkillprogram",validationsFor);
			fillValuesSkill(newEntTlProgTargetSkills,existEntTlProgTargetSkills,entTlProgTargetSkillsBean);
			CommonMessage.debugMsg("After Updating skill Values");
			
		}catch(Exception e){
			
		}
		return  entTlProgTargetSkillsDao.update(newEntTlProgTargetSkills);
	}
	
	private EntTlProgTargetSkills fillValuesSkill(EntTlProgTargetSkills newEntTlProgTargetSkills,
			EntTlProgTargetSkills existEntTlProgTargetSkills, EntTlProgTargetSkillsBean entTlProgTargetSkillsBean) {
		
		newEntTlProgTargetSkills.setPrtsActive("Y");
		
		CommonMessage.debugMsg("fillvalues skills :");
		
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		if(newEntTlProgTargetSkills.getPrtsKeyid() == null )
			newEntTlProgTargetSkills.setPrtsCreatedon(dateTime);
		else
			newEntTlProgTargetSkills.setPrtsCreatedon(dateTime);
		
		newEntTlProgTargetSkills.setPrtsModifiedon(dateTime);
		
		if(!UIUtils.isValidKeyId(newEntTlProgTargetSkills.getPrtsEffFromDate()  ))
			newEntTlProgTargetSkills.setPrtsEffFromDate(dateTime);
		if(!UIUtils.isValidKeyId(newEntTlProgTargetSkills.getPrtsEffTillDate() ) )
			newEntTlProgTargetSkills.setPrtsEffTillDate(Constants.futureNullDate);
		if(newEntTlProgTargetSkills.getPrtsProgKeyid()== null )
			newEntTlProgTargetSkills.setPrtsProgKeyid("{}");
		if(newEntTlProgTargetSkills.getPrtsSkilDeliverymode() == null )
			newEntTlProgTargetSkills.setPrtsSkilDeliverymode("{}");
		if(newEntTlProgTargetSkills.getPrtsSkilEvaluvationtype() == null )
			newEntTlProgTargetSkills.setPrtsSkilEvaluvationtype("{}");
		if(newEntTlProgTargetSkills.getPrtsTopiKeyid() == null )
			newEntTlProgTargetSkills.setPrtsTopiKeyid("{}");
		if(newEntTlProgTargetSkills.getPrtsImpactSkillrate() == null )
			newEntTlProgTargetSkills.setPrtsImpactSkillrate("{}");
		if(newEntTlProgTargetSkills.getPrtsOrderno() == null )
			newEntTlProgTargetSkills.setPrtsOrderno("0");
		if(newEntTlProgTargetSkills.getPrtsRatingType() == null )
			newEntTlProgTargetSkills.setPrtsRatingType("A");

		if(newEntTlProgTargetSkills.getPrtsSpokeKeyid() == null )
			newEntTlProgTargetSkills.setPrtsSpokeKeyid("{}");
		//if(newEntTlProgTargetSkills.getPrtsTempfield1() == null )
		//	newEntTlProgTargetSkills.setPrtsTempfield1("X");

		if(newEntTlProgTargetSkills.getPrtsTempfield2() == null )
			newEntTlProgTargetSkills.setPrtsTempfield2("X");
		if(newEntTlProgTargetSkills.getPrtsTempfield3() == null )
			newEntTlProgTargetSkills.setPrtsTempfield3("X");
		if(newEntTlProgTargetSkills.getPrtsTempfield4() == null )
			newEntTlProgTargetSkills.setPrtsTempfield4("X");
		if(newEntTlProgTargetSkills.getPrtsTempfield5() == null )
			newEntTlProgTargetSkills.setPrtsTempfield5("X");
		
		newEntTlProgTargetSkills.setEntTlProgImpactSkills(refImpactSkillFillValues(newEntTlProgTargetSkills,existEntTlProgTargetSkills));
		CommonMessage.debugMsg(dateTime);
		
		return newEntTlProgTargetSkills;
		// TODO Auto-generated method stub
		
	}
	private List<EntTlProgImpactSkills> refImpactSkillFillValues(EntTlProgTargetSkills newEntTlProgTargetSkills,
			EntTlProgTargetSkills existEntTlProgTargetSkills) {
		// TODO Auto-generated method stub
		//List<EntTlProgImpactSkills> newentTlProgImpactSkills = (List<EntTlProgImpactSkills>) new EntTlProgImpactSkills();
		try {
			String rattingDetails = newEntTlProgTargetSkills.getRattingDetails();
			String[] rattingDetList = rattingDetails.split(",") ;
			
			List<EntTlProgImpactSkills> entTlProgImpactSkillsList = new ArrayList<EntTlProgImpactSkills>();
			//for( EntTlRoleSkillRating  :newEntTlRoleSkillRatings)
			CommonMessage.debugMsg("rattingDetList.length"+rattingDetList.length);
			
			for (int i=0;i<rattingDetList.length;i++)
			{	
				EntTlProgImpactSkills newentTlProgImpactSkills = new EntTlProgImpactSkills();
				String[] rattingData = rattingDetList[i].split(":") ;
				CommonMessage.debugMsg("rattingData[0]"+rattingData[0]);
				//CommonMessage.debugMsg("rattingData[1]"+rattingData[1]);
				newentTlProgImpactSkills.setPimsActive("Y");
				
				CommonMessage.debugMsg("fillvalues Impact :");
				
				String dateTime = CommonFunctions.dateTimeNow();
				CommonMessage.debugMsg(dateTime);
				if(newentTlProgImpactSkills.getPimsKeyid() == null )
					newentTlProgImpactSkills.setPimsCreatedon(dateTime);
				else
					newentTlProgImpactSkills.setPimsCreatedon(dateTime);
				
				newentTlProgImpactSkills.setPimsModifiedon(dateTime);
				
				
				if(newentTlProgImpactSkills.getPimsEffFromDate() == null )
					newentTlProgImpactSkills.setPimsEffFromDate(dateTime);
				if(newentTlProgImpactSkills.getPimsEffTillDate() == null )
					newentTlProgImpactSkills.setPimsEffTillDate(Constants.futureNullDate);
				
				
					String skrmKeyid = entTlProgTargetSkillsDao.getRattingKeyid(rattingData[0]);
					newentTlProgImpactSkills.setPimsSkrmKeyid(skrmKeyid);
				
					newentTlProgImpactSkills.setPimsMinpoints(rattingData[1]);
					
				if(newentTlProgImpactSkills.getPimsSkrmKeyid() == null )
					newentTlProgImpactSkills.setPimsSkrmKeyid("0");
				
				if(newentTlProgImpactSkills.getPimsMinpoints() == null )
					newentTlProgImpactSkills.setPimsMinpoints("0");
				if(newentTlProgImpactSkills.getPimsSkrmKeyid() == null )
					newentTlProgImpactSkills.setPimsSkrmKeyid("{}");
				if(newentTlProgImpactSkills.getPimsTempfield1() == null )
					newentTlProgImpactSkills.setPimsTempfield1("X");
				if(newentTlProgImpactSkills.getPimsTempfield2() == null )
					newentTlProgImpactSkills.setPimsTempfield2("X");
				if(newentTlProgImpactSkills.getPimsTempfield3() == null )
					newentTlProgImpactSkills.setPimsTempfield3("X");
				if(newentTlProgImpactSkills.getPimsTempfield4() == null )
					newentTlProgImpactSkills.setPimsTempfield4("X");
				if(newentTlProgImpactSkills.getPimsTempfield5() == null )
					newentTlProgImpactSkills.setPimsTempfield5("X");
				entTlProgImpactSkillsList.add(newentTlProgImpactSkills);
							
			}
			return entTlProgImpactSkillsList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  
		
	}
	@Override
	public EntTlProgTargetSkills getSkilformValues(String progKeyId) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		return entTlProgTargetSkillsDao.getSkilformValues(progKeyId);
	}
	@Override
	public List<String[]> getskillgridData(String string) throws Exception {
		// TODO Auto-generated method stub
		return entTlProgTargetSkillsDao.getskillgridData(string);
	}
	@Override
	public List<String[]> getRolegridData(String progKeyId) throws Exception {
		// TODO Auto-generated method stub
		return entTlProgTargetRolesDao.getRolegridData(progKeyId) ;
	}
	@Override
	public EntTlProgTargetRoles createRole(EntTlProgTargetRoles newEntTlProgTargetRoles,EntTlProgTargetRoles existEntTlProgTargetRoles,
			EntTlProgTargetRolesBean entTlProgTargetRolesBean) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
		try{
			String validationsFor = "create";
			CommonMessage.debugMsg("Inside the ServiceImpl Role Create");
			validations.validate(newEntTlProgTargetRoles,"EntRoleValidation",validationsFor);
			fillValuesRole(newEntTlProgTargetRoles,existEntTlProgTargetRoles,entTlProgTargetRolesBean);
			CommonMessage.debugMsg("After Filling tool Values");
			
			
		}catch (ValidationExceptions e){
			e.printStackTrace();
			CommonMessage.debugMsg(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}catch(Exception e){
			
		}
		return  entTlProgTargetRolesDao.create(newEntTlProgTargetRoles);
	}
	private void fillValuesRole(EntTlProgTargetRoles newEntTlProgTargetRoles,EntTlProgTargetRoles existEntTlProgTargetRoles,
			EntTlProgTargetRolesBean entTlProgTargetRolesBean) {
		// TODO Auto-generated method stub
		newEntTlProgTargetRoles.setPrtrActive("Y");
		
		CommonMessage.debugMsg("fillvalues  :");
		
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		if(newEntTlProgTargetRoles.getPrtrKeyid() == null )
			newEntTlProgTargetRoles.setPrtrCreatedon(dateTime);
		else
			newEntTlProgTargetRoles.setPrtrCreatedon(existEntTlProgTargetRoles.getPrtrCreatedon());
		
		newEntTlProgTargetRoles.setPrtrModifiedon(dateTime);
		
		if(newEntTlProgTargetRoles.getPrtrEffFromDate() == null )
			newEntTlProgTargetRoles.setPrtrEffFromDate(Constants.passNullDate);
		if(newEntTlProgTargetRoles.getPrtrEffTillDate() == null )
			newEntTlProgTargetRoles.setPrtrEffTillDate(Constants.futureNullDate);
		if(newEntTlProgTargetRoles.getPrtrProgKeyid()== null )
			newEntTlProgTargetRoles.setPrtrProgKeyid("{}");
		if(newEntTlProgTargetRoles.getPrtrTrarKeyid()== null )
			newEntTlProgTargetRoles.setPrtrTrarKeyid("{}");
		
		if(newEntTlProgTargetRoles.getPrtrTempfield1() == null )
			newEntTlProgTargetRoles.setPrtrTempfield1("X");
		if(newEntTlProgTargetRoles.getPrtrTempfield2() == null )
			newEntTlProgTargetRoles.setPrtrTempfield2("X");
		if(newEntTlProgTargetRoles.getPrtrTempfield3() == null )
			newEntTlProgTargetRoles.setPrtrTempfield3("X");
		if(newEntTlProgTargetRoles.getPrtrTempfield4() == null )
			newEntTlProgTargetRoles.setPrtrTempfield4("X");
		if(newEntTlProgTargetRoles.getPrtrTempfield5() == null )
			newEntTlProgTargetRoles.setPrtrTempfield5("X");
	}
	@Override
	public EntTlProgTargetRoles updateRole(EntTlProgTargetRoles newEntTlProgTargetRoles,EntTlProgTargetRoles existEntTlProgTargetRoles,
			EntTlProgTargetRolesBean entTlProgTargetRolesBean) throws Exception {
		// TODO Auto-generated method stub
		try{
			fillValuesRole(newEntTlProgTargetRoles,existEntTlProgTargetRoles,entTlProgTargetRolesBean);
			CommonMessage.debugMsg("After Filling tool Values");
		
			
		}catch(Exception e){
			
		}
		return  entTlProgTargetRolesDao.update(newEntTlProgTargetRoles);
	}
	@Override
	public EntTlProgTargetRoles getRoleformValues(String progKeyId) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		return entTlProgTargetRolesDao.getRoleformValues(progKeyId);
	}
	@Override
	public String delgridData(String prtsKeyid) throws Exception {
		// TODO Auto-generated method stub
		
		return  entTlProgTargetSkillsDao.delgridData(prtsKeyid);
	
	}
	@Override
	public String  delRolegridData(String prtrKeyid) throws Exception {
		// TODO Auto-generated method stub
		return entTlProgTargetRolesDao.delRolegridData(prtrKeyid);
	}
	@Override
	public String chkKeyExists(String progKeyId) throws SQLException, BusinessApplicationExceptions {
		// TODO Auto-generated method stub
		return entTlProgTargetSkillsDao.chkKeyExists(progKeyId);
	}
	@Override
	public String chkKeyRoleExists(String progKeyId) throws Exception {
		// TODO Auto-generated method stub
		return entTlProgTargetRolesDao.chkKeyRoleExists(progKeyId);
	}
	@Override
	public EntTlProgrammst deleteProg(EntTlProgrammst newEntTlProgrammst) throws BusinessApplicationExceptions,Exception {
		// TODO Auto-generated method stub
		return entTlProgrammstDao.delete(newEntTlProgrammst);
	}
	@Override
	public String getevalTypeId(String topickeyId) throws Exception {
		// TODO Auto-generated method stub
		return entTlProgrammstDao.getevalTypeId(topickeyId);
	}
}
