package com.akranta.tpm.service.impl;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServlet;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AbnTlAbnormalityDao;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.impl.AbnTlAbnormalityDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AbnTlAbnhistorydtl;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AbnTlDtl;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.AbnormalityFormService;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AbnormalityFormServiceImpl
 */
public class AbnormalityFormServiceImpl  implements AbnormalityFormService {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private CommonFilterDao commonFilterDao;
	private AbnTlAbnormalityDao abnDao;
	private Validations validations ;
	private AbnormalityServiceApi abnServiceApi;
    public AbnormalityFormServiceImpl(DBActionTemplate dbActionTemplate){
    	try{
        commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
        abnDao = new AbnTlAbnormalityDaoImpl(dbActionTemplate);
        validations = new Validations();
//        abnServiceApi = new AbnormalityServiceApi();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
    
    public void AbnormalityFormServiceImplJwt(String JwtToken){
    	try{
    	abnDao.AbnTlAbnormalityDaoImplJwt(JwtToken);
        abnServiceApi = new AbnormalityServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }

	public List<ComboBox> getdepartmentcombo(String condSql,ComboFilter comboFilter) throws Exception {
		CommonMessage.debugMsg(condSql);
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("ABNM_KEYID");
		//comboFilter.setNameField("ABNM_NAME");
		comboFilter.setIdField("ABNM_KEYID");
		
		if ( UIUtils.isValidKeyId(condSql) ) 
			comboFilter.setCondSql(" AND ABNM_KEYID = '" + condSql + "'" );
		
		comboFilter.setTableName(TableNames.TBL_ABN_TL_ABNORMALITY);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getAssemblycombo(String condSql,ComboFilter comboFilter) throws Exception {
		CommonMessage.debugMsg(condSql);
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("ASSM_CODE");
		comboFilter.setNameField("ASSM_NAME");
		comboFilter.setIdField("ASSM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_ASSEMBLYMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	
	public List<ComboBox> getImpactcombo(String formName,ComboFilter comboFilter) throws Exception {
		CommonMessage.debugMsg(formName);
		//ComboFilter comboFilter = new ComboFilter();
	
		 if(formName!=null && formName.equalsIgnoreCase("SHE"))
		{
		//	comboFilter.setCodeField("SAIM_CODE");
			 comboFilter.setNameField("SAIM_NAME");
			 comboFilter.setIdField("SAIM_KEYID");
			 comboFilter.setTableName(TableNames.TBL_SHE_TL_ABNIMPACTMST);	
		}
		 else{
			 comboFilter.setCodeField("ABIM_CODE");
			 comboFilter.setNameField("ABIM_NAME");
			 comboFilter.setIdField("ABIM_KEYID");
			 comboFilter.setTableName(TableNames.TBL_ABN_TL_IMPACTMST);
		 }
			 
			
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	@Override
	public List<ComboBox> getEquipmentcombo(String condSql,ComboFilter comboFilter) throws Exception {
		CommonMessage.debugMsg(condSql);
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("MCHM_MACHINENO");
		comboFilter.setNameField("MCHM_MACHINENAME");
		comboFilter.setIdField("MCHM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	public List<ComboBox> getIssueNocombo(String condSql,ComboFilter comboFilter) throws Exception {
		CommonMessage.debugMsg(condSql);
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("ABNM_ISSUENO");
		comboFilter.setNameField("ABNM_ISSUENO");
		comboFilter.setIdField("ABNM_ISSUENO");
		comboFilter.setTableName(TableNames.TBL_ABN_TL_ABNORMALITY);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getDetectedBycombo(ComboFilter comboFilter) throws Exception {
		//CommonMessage.debugMsg(condSql);  String condSql,
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getTypecombo(String abnCode,String abnType,ComboFilter comboFilter) throws Exception {
		CommonMessage.debugMsg("condSql ="+abnCode);
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("ABTM_NAME");
		comboFilter.setNameField("ABTM_CODE");
		comboFilter.setIdField("ABTM_KEYID"); 
		
		String condSql = " AND ABTM_KEYID != 'ABT0000'";
		condSql+=" AND ABTM_ACTIVE = 'Y' ";
		if ( UIUtils.isValidKeyId(abnCode) ) 
			condSql+=" AND ABTM_CODE = '" + abnCode + "'";
		if(UIUtils.isValidKeyId(abnType) && abnType.equals("ABN") )
			condSql+=" AND ABTM_TYPEFLAG NOT IN('H','S')" ;
		else if(UIUtils.isValidKeyId(abnType) && abnType.equals("HTA"))
			condSql+=" AND ABTM_TYPEFLAG='H'" ;
		else if(UIUtils.isValidKeyId(abnType) && abnType.equals("SOC"))
			condSql+=" AND ABTM_TYPEFLAG='S'" ;
		else if(UIUtils.isValidKeyId(abnType) && abnType.equals("UNS"))
			condSql+=" AND ABTM_TYPEFLAG='U' " ;
		comboFilter.setTableName(TableNames.TBL_ABN_TL_TYPEMST);
		comboFilter.setCondSql(condSql);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getSubTypecombo(String typeId, String formName,ComboFilter comboFilter) throws Exception {

		CommonMessage.debugMsg("typeId  in dao impl ="+typeId);
		//ComboFilter comboFilter = new ComboFilter();

		comboFilter.setCodeField("AHSM_CODE");
		comboFilter.setNameField("AHSM_NAME");
		comboFilter.setIdField("AHSM_KEYID");
			
		comboFilter.setTableName(TableNames.TBL_ABN_TL_HTASOCMST);
			
		if( UIUtils.isValidKeyId(typeId) )
			comboFilter.setCondSql(" AND AHSM_ABNORMALITYTYPE = '" + typeId + "'" );
		
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	@Override
	public List<ComboBox> getTagClasscombo(String condSql,ComboFilter comboFilter) throws Exception {
		CommonMessage.debugMsg(condSql);
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("TAGM_NAME");
		comboFilter.setCodeField("TAGM_CODE");
		comboFilter.setIdField("TAGM_KEYID");
		
		comboFilter.setCondSql(condSql);
		
		comboFilter.setTableName(TableNames.TBL_ABN_TL_TAGMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getCostCentercombo(String condSql) throws Exception {
		CommonMessage.debugMsg(condSql);
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("CSTM_CODE");
		//comboFilter.setNameField("ABNM_TAGCLASSID");
		comboFilter.setIdField("CSTM_CODE");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_COSTCENTERMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getCategorycombo(String formName,ComboFilter comboFilter) throws Exception {
		CommonMessage.debugMsg(formName);
		//ComboFilter comboFilter = new ComboFilter();
	
		 if(formName!=null && formName.equalsIgnoreCase("SHE"))
		{
			 comboFilter.setNameField("SACM_NAME");
			 comboFilter.setIdField("SACM_KEYID");
			 comboFilter.setTableName(TableNames.TBL_SHE_TL_ABNCATEGORYMST);
		}			
		 else 
		{
			 comboFilter.setNameField("ABCM_NAME");
			 comboFilter.setCodeField("ABCM_CODE");
			 comboFilter.setIdField("ABCM_KEYID");
			 comboFilter.setCondSql(" AND ABCM_ACTIVE='Y' ");
			 comboFilter.setTableName(TableNames.TBL_ABN_TL_CATEGORYMST);
		}
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getTradecombo(ComboFilter comboFilter) throws Exception {
		//CommonMessage.debugMsg(condSql);String condSql,
		//tradeComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("TRDM_CODE");
		comboFilter.setNameField("TRDM_NAME");
		comboFilter.setIdField("TRDM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getcompletedBycombo(ComboFilter cmpbyComboFilter) throws Exception {
		//CommonMessage.debugMsg(condSql);  String condSql,
		//ComboFilter comboFilter = new ComboFilter();
		cmpbyComboFilter.setCodeField("EMPM_CODE");
		cmpbyComboFilter.setNameField("EMPM_NAME");
		cmpbyComboFilter.setIdField("EMPM_KEYID");
		cmpbyComboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(cmpbyComboFilter);
	}
	
	@Override
	public List<ComboBox> getMainTypecombo(String condSql,ComboFilter comboFilter) throws Exception {
		CommonMessage.debugMsg(condSql);
		//ComboFilter comboFilter = new ComboFilter();
		
		//comboFilter.setCodeField("SAMT_CODE");
		comboFilter.setNameField("SAMT_NAME");
		comboFilter.setIdField("SAMT_KEYID");
		comboFilter.setTableName(TableNames.TBL_SHE_TL_ABNMAINTYPEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	
	public String getShift(String flid, String time)throws  BusinessApplicationExceptions, Exception 
	{		
		CommonMessage.debugMsg("get Shift in serveice impl");
		return this.abnDao.getShift(flid,time);		
	}
	public List<String[]> getAbnormalityDetails(String keyId) throws Exception
	{
		return this.abnDao.getAbnormalityDetails(keyId);	
	}

	@SuppressWarnings("unused")
	@Override
	public AbnTlAbnormality create(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality, 
			AbnormalityBean abnBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception {

	try {
		    
			String validationsFor;		
			String tagClass=null;
			String abnTypeFlag=null;
			CommonMessage.debugMsg("Inside the Create Function");
		
			String elementId = commonFilterDao.getElementID(newAbnTlAbnormality.getAbnmFlid());
			CommonMessage.debugMsg("elementId...."+elementId);
			newAbnTlAbnormality.setAbnmElementid(elementId);
			
			if(abnBean.getPillarName().equalsIgnoreCase("JH"))
			{	
			
				if(UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmTagclassid()))
					 tagClass = getTagCode(newAbnTlAbnormality.getAbnmTagclassid());
				
				if(UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmTypeid()))
				abnTypeFlag=getAbnTypeFlag(newAbnTlAbnormality.getAbnmTypeid());
				
				CommonMessage.debugMsg("funLocId.........");
				CommonMessage.debugMsg("The tagClass:::::"+tagClass);
				if(tagClass!=null && tagClass.equals("RED")&& newAbnTlAbnormality.getAbnmStatus().equals("C"))
				{
					validationsFor="redTagCompletion";
				}
				else if(tagClass!=null && tagClass.equals("RED"))
				{
					CommonMessage.debugMsg("test red tag");
					validationsFor="redTagTest";
				}
				else if(newAbnTlAbnormality.getAbnmStatus().equals("C"))
				{
					validationsFor="completed";
				}
				else
					validationsFor = "create";
				
				 
				CommonMessage.debugMsg(" validationsFor =========="+validationsFor);
				
				CommonMessage.debugMsg(" This is the checking "+abnTypeFlag);
				
				CommonMessage.debugMsg(" After that checking :: checking this one "+newAbnTlAbnormality.getAbnmRelatedto());
				
				if(abnTypeFlag!=null && abnTypeFlag.equals("S"))
				{
					CommonMessage.debugMsg("SOC VAL Validationss");
					//validations.validate(newAbnTlAbnormality,"AbnormalityCreation","SOCType");
				}
				
				//if(newAbnTlAbnormality.getAbnmRelatedto().equalsIgnoreCase("MCH")){
					//validations.validate(newAbnTlAbnormality,"AbnormalityCreation","MachineType");
				//}
				
				CommonMessage.debugMsg(" validationsFor ========== validationsFor1122 "+validationsFor);
			validations.validate(newAbnTlAbnormality,"AbnormalityCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			CommonMessage.debugMsg(" validationsFor ========== validationsFor3344 "+validationsFor);
			}
			
			else if(abnBean.getPillarName().equalsIgnoreCase("SHE"))//For Hse Abnormality
			{
				//AbnTlDtl abnTlDtl = newAbnTlAbnormality.getAbnTlDtl();
				
				if(newAbnTlAbnormality.getAbnmStatus().equals("C"))
				{
					//validationsFor="hseCompletion";
				}
				//else
					//validationsFor = "hseCreate";
				
				
				//validations.validate(newAbnTlAbnormality,"AbnormalityCreation",validationsFor);
				
				//if(abnTlDtl != null)
				//CommonMessage.debugMsg("abnTlDtl ="+abnTlDtl.getAbndImprovementteam());
					//validations.validate(abnTlDtl,"AbnormalityCreation","Safety");
			}
			CommonMessage.debugMsg("After validation");

			fillValues(newAbnTlAbnormality,oldAbnTlAbnormality,abnBean);
			CommonMessage.debugMsg("After Fill values");

		//	AbnTlDtl abnTlDtl = fillHseAbnormalityValues(newAbnTlAbnormality,oldAbnTlAbnormality,abnBean);
		//	CommonMessage.debugMsg("After Fill Values.................."+abnTlDtl);
			
//			return abnDao.create(newAbnTlAbnormality);	
			return abnServiceApi.insertRecord(newAbnTlAbnormality);
			
		}catch (ValidationExceptions e){
			
			e.printStackTrace();
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		
	}

	
	public AbnTlAbnormality update(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality, 
			AbnormalityBean abnBean,WomTlWomst womTlWomst) throws ValidationExceptions,BusinessApplicationExceptions, Exception {

		CommonMessage.debugMsg("Inside the ServiceImpl update");
		String validationsFor="update";
		String tagClass=null;
		String abnTypeFlag=null;
		String elementId = commonFilterDao.getElementID(newAbnTlAbnormality.getAbnmFlid());
		newAbnTlAbnormality.setAbnmElementid(elementId);
		if(abnBean.getPillarName().equalsIgnoreCase("JH"))
		{	
		
			if(UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmTagclassid()))
				tagClass = getTagCode(newAbnTlAbnormality.getAbnmTagclassid());
			
			if(UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmTypeid()))
				abnTypeFlag=getAbnTypeFlag(newAbnTlAbnormality.getAbnmTypeid());
			CommonMessage.debugMsg(tagClass + " : "+newAbnTlAbnormality.getAbnmStatus());
				
			if(tagClass!=null && tagClass.equals("RED")&& newAbnTlAbnormality.getAbnmStatus().equals("C"))
			{
				validationsFor="redTagCompletion";
			}
			else if(tagClass!=null && tagClass.equals("RED"))
			{
				CommonMessage.debugMsg("test red tag");
				validationsFor="redTagTest";
			}
			else if(newAbnTlAbnormality.getAbnmStatus().equals("C"))
				{validationsFor="completed";}
			else
				validationsFor = "update";
			
			
			CommonMessage.debugMsg("validationsFor  ="+validationsFor);
			
			if(abnTypeFlag!=null && abnTypeFlag.equals("S"))
			{
				CommonMessage.debugMsg("SOC VAL Validationss");
				validations.validate(newAbnTlAbnormality,"AbnormalityCreation","SOCType");
			}
			CommonMessage.debugMsg("Show CompDate : "+abnBean.getShowCompDate());
			if(womTlWomst != null)
			{
				if(UIUtils.isValidKeyId(womTlWomst.getWomsAllotteddate()))
				{
					CommonMessage.debugMsg("Allotteddate() : "+womTlWomst.getWomsAllotteddate());
					abnBean.setWoallotteddatetime(womTlWomst.getWomsAllotteddate());
					if(UIUtils.isValidKeyId(abnBean.getAbnmstarttime()))
						abnBean.setAbnwostartdatetime(newAbnTlAbnormality.getAbnmWostarttime() + " "+abnBean.getAbnmstarttime());
					else
						abnBean.setAbnwostartdatetime(newAbnTlAbnormality.getAbnmWostarttime());
					CommonMessage.debugMsg("Allotteddate() : "+abnBean.getAbnwostartdatetime());
					if(!UIUtils.isValidKeyId(abnBean.getShowCompDate()))
						validations.validate(abnBean,"AbnormalityCreation","datetime");
					else
					{
						if(abnBean.getShowCompDate().equals("N"))
							validations.validate(abnBean,"AbnormalityCreation","datetime");
					}
				}
			}
			validations.validate(newAbnTlAbnormality,"AbnormalityCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			CommonMessage.debugMsg("After");
		}
		else if(abnBean.getPillarName().equalsIgnoreCase("SHE"))//For Hse Abnormality
		{
			//AbnTlDtl abnTlDtl = newAbnTlAbnormality.getAbnTlDtl();
			
			if(newAbnTlAbnormality.getAbnmStatus().equals("C"))
			{
				validationsFor="hseCompletion";
			}
			else
				validationsFor = "hseupdate";
			
			validations.validate(newAbnTlAbnormality,"AbnormalityCreation",validationsFor);
			
		/*	if(abnTlDtl != null)
				validations.validate(abnTlDtl,"AbnormalityCreation","Safety");*/
		}
		
		fillValues(newAbnTlAbnormality,oldAbnTlAbnormality,abnBean);		
		//CommonMessage.debugMsg("before....."+newAbnTlAbnormality.getAbnTlDtl().getAbndResponsiblity());
		//AbnTlDtl abnTlDtl = fillHseAbnormalityValues(newAbnTlAbnormality,oldAbnTlAbnormality,abnBean);
		AbnTlAbnhistorydtl abnTlAbnhistorydtl = fillAbnHistory(newAbnTlAbnormality,oldAbnTlAbnormality);
		
	//	CommonMessage.debugMsg("after....."+newAbnTlAbnormality.getAbnTlDtl().getAbndResponsiblity());
		//return abnDao.update(newAbnTlAbnormality,womTlWomst,abnTlAbnhistorydtl);
		return abnServiceApi.insertRecord(newAbnTlAbnormality);
		
		
	}
	
	
//	private AbnTlAbnormality fillValues(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality,AbnormalityBean abnBean) throws BusinessApplicationExceptions 
//	{
//		try{
//			CommonMessage.debugMsg("Inside fill Values");
//			newAbnTlAbnormality.setAbnmActive("Y");
//			String dateTime = CommonFunctions.dateTimeNow();
//			String currentDate = CommonFunctions.getDate();
//			CommonMessage.debugMsg("Inside fill Values"+currentDate);
//			if(newAbnTlAbnormality.getAbnmKeyid() == null )			
//			{	CommonMessage.debugMsg("Inside fill Values"+dateTime);
//				newAbnTlAbnormality.setAbnmCreatedon(dateTime);	
////			   newAbnTlAbnormality.setAbnmCreatedon(currentDate);	
//			}					
//			else
//			{	
//				CommonMessage.debugMsg("Else");
//				CommonMessage.debugMsg(oldAbnTlAbnormality.getAbnmCreatedon());
//				newAbnTlAbnormality.setAbnmCreatedon(oldAbnTlAbnormality.getAbnmCreatedon() );	
//			}
//			CommonMessage.debugMsg("Inside fill Valueslgfkjgfj");
//			newAbnTlAbnormality.setAbnmModifiedon(currentDate);
//			CommonMessage.debugMsg("fill values:");
//			
//			 newAbnTlAbnormality.setAbnmMultipleabn("N");
//			 
//			if( newAbnTlAbnormality.getAbnmAssemblyid() == null )
//				newAbnTlAbnormality.setAbnmAssemblyid("{}");
//		
//			if( newAbnTlAbnormality.getAbnmBlockdiagramref() == null )
//				newAbnTlAbnormality.setAbnmBlockdiagramref("{}");
//			
//			if( newAbnTlAbnormality.getAbnmCategoryid() == null )
//				newAbnTlAbnormality.setAbnmCategoryid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmCellid() == null )
//				newAbnTlAbnormality.setAbnmCellid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmCompletedby() == null )
//				newAbnTlAbnormality.setAbnmCompletedby("{}");
//			
//			if( newAbnTlAbnormality.getAbnmCountermeasure() == null )
//				newAbnTlAbnormality.setAbnmCountermeasure("{}");
//			
//			if( newAbnTlAbnormality.getAbnmDate() == null )
//				newAbnTlAbnormality.setAbnmDate(currentDate);
//			
//			if( newAbnTlAbnormality.getAbnmCreatedby() == null )
//				newAbnTlAbnormality.setAbnmCreatedby("{}");
//			
//			if( newAbnTlAbnormality.getAbnmDescription() == null )
//				newAbnTlAbnormality.setAbnmDescription("{}");
//			
//			if( newAbnTlAbnormality.getAbnmDetailedesc() == null )
//				newAbnTlAbnormality.setAbnmDetailedesc("{}");
//			
//			CommonMessage.debugMsg("abnBean Spinner time"+abnBean.getAbnmDetectedbytime());
//			
//			if( newAbnTlAbnormality.getAbnmDetectiondate() == null )
//				newAbnTlAbnormality.setAbnmDetectiondate(currentDate);
//			else
//			{
//				String DetectedDateSpn=UIUtils.getActualDateForm(newAbnTlAbnormality.getAbnmDetectiondate());
//				String dd=DetectedDateSpn+" "+abnBean.getAbnmDetectedbytime();
//				CommonMessage.debugMsg("DetectedDateSpn="+DetectedDateSpn);
//				newAbnTlAbnormality.setAbnmDetectiondate(dd);
////				newAbnTlAbnormality.setAbnmDetectiondate(CommonFunctions.pg_getDate(DetectedDateSpn));
//			}	
//			if( newAbnTlAbnormality.getAbnmDowntime() == null )
//				newAbnTlAbnormality.setAbnmDowntime("0");
//			
//			if( newAbnTlAbnormality.getAbnmEquipmentid() == null )
//				newAbnTlAbnormality.setAbnmEquipmentid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmFeedbackdate() == null )
//				newAbnTlAbnormality.setAbnmFeedbackdate(Constants.passNullDate);
//			
//			if( newAbnTlAbnormality.getAbnmFeedbackid() == null )
//				newAbnTlAbnormality.setAbnmFeedbackid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmImpactid() == null )
//				newAbnTlAbnormality.setAbnmImpactid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmPriority() == null )
//				newAbnTlAbnormality.setAbnmPriority("{}");
//			
//			if( newAbnTlAbnormality.getAbnmPreventivemeasure() == null )
//				newAbnTlAbnormality.setAbnmPreventivemeasure("{}");
//			
//			if( newAbnTlAbnormality.getAbnmRefdocid() == null )
//				newAbnTlAbnormality.setAbnmRefdocid("{}");
//			
//			
//			if( newAbnTlAbnormality.getAbnmRefdoctype() == null )
//				newAbnTlAbnormality.setAbnmRefdoctype("{}");
//			
//			if( newAbnTlAbnormality.getAbnmRemarks() == null )
//				newAbnTlAbnormality.setAbnmRemarks("{}");
//			
//			if( newAbnTlAbnormality.getAbnmResponsetime() == null )
//				newAbnTlAbnormality.setAbnmResponsetime("0");			
//			
//			if( newAbnTlAbnormality.getAbnmRevisionno() == null )
//				newAbnTlAbnormality.setAbnmRevisionno("{}");
//			
//			if( newAbnTlAbnormality.getAbnmSectionid() == null )
//				newAbnTlAbnormality.setAbnmSectionid("{}");
//			
//			newAbnTlAbnormality.setAbnmShiftid("{}");
//			
//			//getShift(newAbnTlAbnormality,abnBean);
//			
//		/*	
//			if(abnBean.getFormActionMode().equals("Completed")||(abnBean.getFormActionMode().equals("View")))
//				newAbnTlAbnormality.setAbnmStatus("C");
//			else
//				newAbnTlAbnormality.setAbnmStatus("P");*/
//			
//			
//			if( newAbnTlAbnormality.getAbnmSubtype() == null )
//				newAbnTlAbnormality.setAbnmSubtype("{}");
//			
//			if( newAbnTlAbnormality.getAbnmTagclassid() == null )
//				newAbnTlAbnormality.setAbnmTagclassid("{}");			
//			
//			if( newAbnTlAbnormality.getAbnmTargetdate() == null )
//				newAbnTlAbnormality.setAbnmTargetdate(currentDate);
//			else
//				newAbnTlAbnormality.setAbnmTargetdate(newAbnTlAbnormality.getAbnmTargetdate());
//			CommonMessage.debugMsg("rEMARKS : "+newAbnTlAbnormality.getAbnmTargetremarks());
//			if( newAbnTlAbnormality.getAbnmTargetremarks() == null )
//				newAbnTlAbnormality.setAbnmTargetremarks("{}");
//			CommonMessage.debugMsg("rEMARKS : "+newAbnTlAbnormality.getAbnmTargetremarks());
//			if( newAbnTlAbnormality.getAbnmContaminant() == null )
//				newAbnTlAbnormality.setAbnmContaminant("{}");
//			
//			if( newAbnTlAbnormality.getAbnmMode() == null )
//				newAbnTlAbnormality.setAbnmMode("{}");
//		
//			//if( newAbnTlAbnormality.getAbnmFactoryid() == null )
//				newAbnTlAbnormality.setAbnmFactoryid("{}");
//			
//			if(abnBean.getPillarName().equalsIgnoreCase("JH"))
//			{	
//				newAbnTlAbnormality.setAbnmPillar("JH");
//			}
//			else if(abnBean.getPillarName().equalsIgnoreCase("SHE"))
//			{
//				newAbnTlAbnormality.setAbnmPillar("SHE");
//				newAbnTlAbnormality.setAbnmRefdoctype("SHE");
//				if(newAbnTlAbnormality.getAbnmEquipmentid() == null)
//					newAbnTlAbnormality.setAbnmEquipmentid("{}");
//			}
//			if( newAbnTlAbnormality.getAbnmSafetypatrol() == null )
//				newAbnTlAbnormality.setAbnmSafetypatrol("{}");
//			
//			if( newAbnTlAbnormality.getAbnmRelatedto() == null )
//				newAbnTlAbnormality.setAbnmRelatedto("{}");
//			
//			if(!UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmMould()))
//				newAbnTlAbnormality.setAbnmMould("{}");		
//			
//			if( newAbnTlAbnormality.getAbnmTradeid() == null )
//				newAbnTlAbnormality.setAbnmTradeid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmTypeid() == null )
//				newAbnTlAbnormality.setAbnmTypeid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmWhatcause() == null )
//				newAbnTlAbnormality.setAbnmWhatcause("{}");			
//			
//			if( newAbnTlAbnormality.getAbnmWhyabnhappened() == null )
//				newAbnTlAbnormality.setAbnmWhyabnhappened("{}");
//			
//			if( newAbnTlAbnormality.getAbnmWodetailid() == null )
//				newAbnTlAbnormality.setAbnmWodetailid("{}");
//		
//			if( newAbnTlAbnormality.getAbnmWomasterid() == null )
//				newAbnTlAbnormality.setAbnmWomasterid("{}");
//			
//			CommonMessage.debugMsg("pillar id....."+newAbnTlAbnormality.getAbnmPillarid());
//			CommonMessage.debugMsg("element id....."+newAbnTlAbnormality.getAbnmElementid());
//			if( newAbnTlAbnormality.getAbnmPillarid() == null )
//				newAbnTlAbnormality.setAbnmPillarid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmRepeatedabn() == null )
//				newAbnTlAbnormality.setAbnmRepeatedabn("{}");
//			
//			if( newAbnTlAbnormality.getAbnmAfeemid() == null )
//				newAbnTlAbnormality.setAbnmAfeemid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmEffectivedate() == null )
//				newAbnTlAbnormality.setAbnmEffectivedate(Constants.passNullDate);
//			
//			if( newAbnTlAbnormality.getAbnmNotifysap() == null )
//				newAbnTlAbnormality.setAbnmNotifysap("-");
//			
//			if( newAbnTlAbnormality.getAbnmTentativrDate() == null )
//				newAbnTlAbnormality.setAbnmTentativrDate(Constants.passNullDate);
//			else
//				newAbnTlAbnormality.setAbnmTentativrDate(newAbnTlAbnormality.getAbnmTentativrDate());
//			
//			
//			if( newAbnTlAbnormality.getAbnmShutdownid() == null )
//				newAbnTlAbnormality.setAbnmShutdownid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmShutdownmaint() == null )
//				newAbnTlAbnormality.setAbnmShutdownmaint("-");
//			
//			
//			if( newAbnTlAbnormality.getAbnmAccecpatncerequired() == null )
//				newAbnTlAbnormality.setAbnmAccecpatncerequired("-");
//			
//			if( newAbnTlAbnormality.getAbnmAccecptDate() == null )
//				newAbnTlAbnormality.setAbnmAccecptDate(Constants.passNullDate);
//			else
//				newAbnTlAbnormality.setAbnmAccecptDate(newAbnTlAbnormality.getAbnmAccecptDate());
//			
//			if( newAbnTlAbnormality.getAbnmAccecpted() == null )
//				newAbnTlAbnormality.setAbnmAccecpted("-");
//			
//			if( newAbnTlAbnormality.getAbnmElementid() == null)
//				newAbnTlAbnormality.setAbnmElementid("{}");
//			
//			if( newAbnTlAbnormality.getAbnmWoreceiveddate() == null )
//				newAbnTlAbnormality.setAbnmWoreceiveddate(Constants.passNullDate);
//			
//			if( newAbnTlAbnormality.getAbnmOthers() == null )
//				newAbnTlAbnormality.setAbnmOthers("-");
//			if(newAbnTlAbnormality.getAbnmResponsibleid()==null)
//				newAbnTlAbnormality.setAbnmResponsibleid("-");
//			
//			CommonMessage.debugMsg("newAbnTlAbnormality.getAbnmRepOthers()....."+newAbnTlAbnormality.getAbnmRepOthers());
//			if( newAbnTlAbnormality.getAbnmRepOthers() == null )
//				newAbnTlAbnormality.setAbnmRepOthers("-");
//			 if(newAbnTlAbnormality.getAbnmResponsibleid()==null)
//		    	  newAbnTlAbnormality.setAbnmResponsibleid("-");
//			      newAbnTlAbnormality.setAbnmMultipleabn("N");	
//			if( newAbnTlAbnormality.getAbnmTempfield4() == null )
//				newAbnTlAbnormality.setAbnmTempfield4("-");
//			
//			if( newAbnTlAbnormality.getAbnmTempfield5() == null )
//				newAbnTlAbnormality.setAbnmTempfield5("-");
//			
//			if( newAbnTlAbnormality.getAbnmTempfield6() == null )
//				newAbnTlAbnormality.setAbnmTempfield6("-");
//			
//			if( newAbnTlAbnormality.getAbnmTempfield7() == null )
//				newAbnTlAbnormality.setAbnmTempfield7("-");
//			
//			if( newAbnTlAbnormality.getAbnmTempfield8() == null )
//				newAbnTlAbnormality.setAbnmTempfield8("-");
//			
//			if( newAbnTlAbnormality.getAbnmTempfield9() == null )
//				newAbnTlAbnormality.setAbnmTempfield9("-");
//			
//			if( newAbnTlAbnormality.getAbnmTempfield10() == null )
//				newAbnTlAbnormality.setAbnmTempfield10("-");
//			
//			if( newAbnTlAbnormality.getAbnmWorktime() == null )
//				newAbnTlAbnormality.setAbnmWorktime("0");	
//			
//			CommonMessage.debugMsg("Wostarttime"+newAbnTlAbnormality.getAbnmWostarttime());
//			CommonMessage.debugMsg("Woendtime"+newAbnTlAbnormality.getAbnmWoendtime());
//
//			
//			if( newAbnTlAbnormality.getAbnmWostarttime() == null )
//				newAbnTlAbnormality.setAbnmWostarttime(currentDate);
//			else
//			{
//				CommonMessage.debugMsg("Else Starttime"+abnBean.getAbnmstarttime());
//				if(UIUtils.isValidKeyId(abnBean.getAbnmstarttime()))
//					newAbnTlAbnormality.setAbnmWostarttime(newAbnTlAbnormality.getAbnmWostarttime() + " "+abnBean.getAbnmstarttime());
//				else
//					newAbnTlAbnormality.setAbnmWostarttime(newAbnTlAbnormality.getAbnmWostarttime());
//			}
//					
//			if(newAbnTlAbnormality.getAbnmStatus().equals("P"))
//				newAbnTlAbnormality.setAbnmWoendtime(Constants.passNullDate);
//			else
//				{
//				if(newAbnTlAbnormality.getAbnmWoendtime() ==null)
//				{
//					if(UIUtils.isValidKeyId(abnBean.getAbnmendtime()))
//					{
//						if(UIUtils.isValidKeyId(abnBean.getAbnmenddate()))
//							newAbnTlAbnormality.setAbnmWoendtime(abnBean.getAbnmenddate() + " "+abnBean.getAbnmendtime());
//					}
//					else
//						newAbnTlAbnormality.setAbnmWoendtime(currentDate);	
//				}
//			
//				else
//				{
//					CommonMessage.debugMsg("Else End Time"+abnBean.getAbnmendtime());
//					if(UIUtils.isValidKeyId(abnBean.getAbnmendtime()))
//					{
//						CommonMessage.debugMsg("END Date : "+abnBean.getAbnmenddate());
//						if(UIUtils.isValidKeyId(abnBean.getAbnmenddate()))
//							newAbnTlAbnormality.setAbnmWoendtime(abnBean.getAbnmenddate() + " "+abnBean.getAbnmendtime());
//						else
//							newAbnTlAbnormality.setAbnmWoendtime(newAbnTlAbnormality.getAbnmWoendtime() + " "+abnBean.getAbnmendtime());
//					}
//					else
//						newAbnTlAbnormality.setAbnmWoendtime( newAbnTlAbnormality.getAbnmWoendtime());
//				}
//			}
//				CommonMessage.debugMsg("fill values::::::"+newAbnTlAbnormality.getAbnmWoendtime());
//				
//			/** Set Hse Abnormality Values **/	
//			//if(newAbnTlAbnormality.getAbnTlDtl()!=null && !(newAbnTlAbnormality.getAbnTlDtl().equals("")))
//			//	newAbnTlAbnormality.setAbnTlDtl(fillHseAbnormalityValues(newAbnTlAbnormality,oldAbnTlAbnormality));			
//				
//				//newAbnTlAbnormality.setAbnTlAbnhistorydtl(fillAbnHistory(newAbnTlAbnormality,oldAbnTlAbnormality));
//			CommonMessage.debugMsg(" In Side the Service IMPL after Fill values");
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		return newAbnTlAbnormality; 
//			
//		}
	
	
	
private AbnTlAbnormality fillValues(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality,AbnormalityBean abnBean) throws BusinessApplicationExceptions 
{
	try{
		CommonMessage.debugMsg("Inside fill Values");
		newAbnTlAbnormality.setAbnmActive("Y");
		String currentDateTime = CommonFunctions.pg_dateTimeNow();
		String currentDate = CommonFunctions.pg_getDate();
		CommonMessage.debugMsg("Inside fill Values"+currentDate);
		if(newAbnTlAbnormality.getAbnmKeyid() == null )			
		{	CommonMessage.debugMsg("Inside fill Values"+currentDateTime);
//			newAbnTlAbnormality.setAbnmCreatedon(dateTime);	
		   newAbnTlAbnormality.setAbnmCreatedon(currentDateTime);	
		}					
		else
		{	
			CommonMessage.debugMsg("Else");
			CommonMessage.debugMsg(oldAbnTlAbnormality.getAbnmCreatedon());
			newAbnTlAbnormality.setAbnmCreatedon(oldAbnTlAbnormality.getAbnmCreatedon() );	
		}
		CommonMessage.debugMsg("Inside fill Valueslgfkjgfj");
		newAbnTlAbnormality.setAbnmModifiedon(currentDateTime);
		CommonMessage.debugMsg("fill values:");
		
		 newAbnTlAbnormality.setAbnmMultipleabn("N");
		 
		if( newAbnTlAbnormality.getAbnmAssemblyid() == null )
			newAbnTlAbnormality.setAbnmAssemblyid("{}");
	
		if( newAbnTlAbnormality.getAbnmBlockdiagramref() == null )
			newAbnTlAbnormality.setAbnmBlockdiagramref("{}");
		
		if( newAbnTlAbnormality.getAbnmCategoryid() == null )
			newAbnTlAbnormality.setAbnmCategoryid("{}");
		
		if( newAbnTlAbnormality.getAbnmCellid() == null )
			newAbnTlAbnormality.setAbnmCellid("{}");
		
		if( newAbnTlAbnormality.getAbnmCompletedby() == null )
			newAbnTlAbnormality.setAbnmCompletedby("{}");
		
		if( newAbnTlAbnormality.getAbnmCountermeasure() == null )
			newAbnTlAbnormality.setAbnmCountermeasure("{}");
		
		if( newAbnTlAbnormality.getAbnmDate() == null )
			newAbnTlAbnormality.setAbnmDate(currentDateTime);
		
		if( newAbnTlAbnormality.getAbnmCreatedby() == null )
			newAbnTlAbnormality.setAbnmCreatedby("{}");
		
		if( newAbnTlAbnormality.getAbnmDescription() == null )
			newAbnTlAbnormality.setAbnmDescription("{}");
		
		if( newAbnTlAbnormality.getAbnmDetailedesc() == null )
			newAbnTlAbnormality.setAbnmDetailedesc("{}");
		
		CommonMessage.debugMsg("abnBean Spinner time"+newAbnTlAbnormality.getAbnmDetectiondate()+":"+abnBean.getAbnmDetectedbytime());
		
		if( newAbnTlAbnormality.getAbnmDetectiondate() == null )
			newAbnTlAbnormality.setAbnmDetectiondate(currentDateTime);
		else
		{
			String DetectedDateSpn=UIUtils.getActualDateForm(newAbnTlAbnormality.getAbnmDetectiondate());
			String dd=DetectedDateSpn+" "+abnBean.getAbnmDetectedbytime()+":00";
			CommonMessage.debugMsg("DetectedDateSpan="+dd);
//			newAbnTlAbnormality.setAbnmDetectiondate(dd);
			newAbnTlAbnormality.setAbnmDetectiondate(CommonFunctions.pg_getDateTimeFromTimeStamp(dd));
		}	
		if( newAbnTlAbnormality.getAbnmDowntime() == null )
			newAbnTlAbnormality.setAbnmDowntime("0");
		
		if( newAbnTlAbnormality.getAbnmEquipmentid() == null )
			newAbnTlAbnormality.setAbnmEquipmentid("{}");
		
		if( newAbnTlAbnormality.getAbnmFeedbackdate() == null )
			newAbnTlAbnormality.setAbnmFeedbackdate(Constants.pgPassNullDateTime);
		
		if( newAbnTlAbnormality.getAbnmFeedbackid() == null )
			newAbnTlAbnormality.setAbnmFeedbackid("{}");
		
		if( newAbnTlAbnormality.getAbnmImpactid() == null )
			newAbnTlAbnormality.setAbnmImpactid("{}");
		
		if( newAbnTlAbnormality.getAbnmPriority() == null )
			newAbnTlAbnormality.setAbnmPriority("{}");
		
		if( newAbnTlAbnormality.getAbnmPreventivemeasure() == null )
			newAbnTlAbnormality.setAbnmPreventivemeasure("{}");
		
		if( newAbnTlAbnormality.getAbnmRefdocid() == null )
			newAbnTlAbnormality.setAbnmRefdocid("{}");
		
		
		if( newAbnTlAbnormality.getAbnmRefdoctype() == null )
			newAbnTlAbnormality.setAbnmRefdoctype("{}");
		
		if( newAbnTlAbnormality.getAbnmRemarks() == null )
			newAbnTlAbnormality.setAbnmRemarks("{}");
		
		if( newAbnTlAbnormality.getAbnmResponsetime() == null )
			newAbnTlAbnormality.setAbnmResponsetime("0");			
		
		if( newAbnTlAbnormality.getAbnmRevisionno() == null )
			newAbnTlAbnormality.setAbnmRevisionno("{}");
		
		if( newAbnTlAbnormality.getAbnmSectionid() == null )
			newAbnTlAbnormality.setAbnmSectionid("{}");
		
		newAbnTlAbnormality.setAbnmShiftid("{}");
		
		//getShift(newAbnTlAbnormality,abnBean);
		
	/*	
		if(abnBean.getFormActionMode().equals("Completed")||(abnBean.getFormActionMode().equals("View")))
			newAbnTlAbnormality.setAbnmStatus("C");
		else
			newAbnTlAbnormality.setAbnmStatus("P");*/
		
		
		if( newAbnTlAbnormality.getAbnmSubtype() == null )
			newAbnTlAbnormality.setAbnmSubtype("{}");
		
		if( newAbnTlAbnormality.getAbnmTagclassid() == null )
			newAbnTlAbnormality.setAbnmTagclassid("{}");			
		CommonMessage.debugMsg("Targetdate : "+newAbnTlAbnormality.getAbnmTargetdate());
		if( newAbnTlAbnormality.getAbnmTargetdate() == null )
			newAbnTlAbnormality.setAbnmTargetdate(currentDateTime);
		else
			newAbnTlAbnormality.setAbnmTargetdate(CommonFunctions.pg_getDateTimeFromDate(newAbnTlAbnormality.getAbnmTargetdate()));
		CommonMessage.debugMsg("rEMARKS : "+newAbnTlAbnormality.getAbnmTargetremarks());
		if( newAbnTlAbnormality.getAbnmTargetremarks() == null )
			newAbnTlAbnormality.setAbnmTargetremarks("{}");
		CommonMessage.debugMsg("rEMARKS : "+newAbnTlAbnormality.getAbnmTargetremarks());
		if( newAbnTlAbnormality.getAbnmContaminant() == null )
			newAbnTlAbnormality.setAbnmContaminant("{}");
		
		if( newAbnTlAbnormality.getAbnmMode() == null )
			newAbnTlAbnormality.setAbnmMode("{}");
	
		//if( newAbnTlAbnormality.getAbnmFactoryid() == null )
			newAbnTlAbnormality.setAbnmFactoryid("{}");
		
		if(abnBean.getPillarName().equalsIgnoreCase("JH"))
		{	
			newAbnTlAbnormality.setAbnmPillar("JH");
		}
		else if(abnBean.getPillarName().equalsIgnoreCase("SHE"))
		{
			newAbnTlAbnormality.setAbnmPillar("SHE");
			newAbnTlAbnormality.setAbnmRefdoctype("SHE");
			//if(newAbnTlAbnormality.getAbnmEquipmentid() == null)
				newAbnTlAbnormality.setAbnmEquipmentid("{}");
		}
		if( newAbnTlAbnormality.getAbnmSafetypatrol() == null )
			newAbnTlAbnormality.setAbnmSafetypatrol("{}");
		
		if( newAbnTlAbnormality.getAbnmRelatedto() == null )
			newAbnTlAbnormality.setAbnmRelatedto("{}");
		
		if(!UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmMould()))
			newAbnTlAbnormality.setAbnmMould("{}");		
		
		if( newAbnTlAbnormality.getAbnmTradeid() == null )
			newAbnTlAbnormality.setAbnmTradeid("{}");
		
		if( newAbnTlAbnormality.getAbnmTypeid() == null )
			newAbnTlAbnormality.setAbnmTypeid("{}");
		
		if( newAbnTlAbnormality.getAbnmWhatcause() == null )
			newAbnTlAbnormality.setAbnmWhatcause("{}");			
		
		if( newAbnTlAbnormality.getAbnmWhyabnhappened() == null )
			newAbnTlAbnormality.setAbnmWhyabnhappened("{}");
		
		if( newAbnTlAbnormality.getAbnmWodetailid() == null )
			newAbnTlAbnormality.setAbnmWodetailid("{}");
	
		if( newAbnTlAbnormality.getAbnmWomasterid() == null )
			newAbnTlAbnormality.setAbnmWomasterid("{}");
		
		CommonMessage.debugMsg("pillar id....."+newAbnTlAbnormality.getAbnmPillarid());
		CommonMessage.debugMsg("element id....."+newAbnTlAbnormality.getAbnmElementid());
		if( newAbnTlAbnormality.getAbnmPillarid() == null )
			newAbnTlAbnormality.setAbnmPillarid("{}");
		
		if( newAbnTlAbnormality.getAbnmRepeatedabn() == null )
			newAbnTlAbnormality.setAbnmRepeatedabn("{}");
		
		if( newAbnTlAbnormality.getAbnmAfeemid() == null )
			newAbnTlAbnormality.setAbnmAfeemid("{}");
		
		if( newAbnTlAbnormality.getAbnmEffectivedate() == null ) {
			newAbnTlAbnormality.setAbnmEffectivedate(Constants.pgPassNullDateTime);
		}else {
			newAbnTlAbnormality.setAbnmEffectivedate(CommonFunctions.pg_getDateTimeFromDate(newAbnTlAbnormality.getAbnmEffectivedate()));
		}
			
		
		if( newAbnTlAbnormality.getAbnmNotifysap() == null )
			newAbnTlAbnormality.setAbnmNotifysap("-");
		
		if( newAbnTlAbnormality.getAbnmTentativrDate() == null )
			newAbnTlAbnormality.setAbnmTentativrDate(Constants.pgPassNullDateTime);
		else
			newAbnTlAbnormality.setAbnmTentativrDate(newAbnTlAbnormality.getAbnmTentativrDate());
		
		
		if( newAbnTlAbnormality.getAbnmShutdownid() == null )
			newAbnTlAbnormality.setAbnmShutdownid("{}");
		
		if( newAbnTlAbnormality.getAbnmShutdownmaint() == null )
			newAbnTlAbnormality.setAbnmShutdownmaint("-");
		
		
		if( newAbnTlAbnormality.getAbnmAccecpatncerequired() == null )
			newAbnTlAbnormality.setAbnmAccecpatncerequired("-");
		
		if( newAbnTlAbnormality.getAbnmAccecptDate() == null )
			newAbnTlAbnormality.setAbnmAccecptDate(Constants.pgPassNullDateTime);
		else
			newAbnTlAbnormality.setAbnmAccecptDate(CommonFunctions.pg_getDateTimeFromDate(newAbnTlAbnormality.getAbnmAccecptDate()));
		
		if( newAbnTlAbnormality.getAbnmAccecpted() == null )
			newAbnTlAbnormality.setAbnmAccecpted("-");
		
		if( newAbnTlAbnormality.getAbnmElementid() == null)
			newAbnTlAbnormality.setAbnmElementid("{}");
		
		if( newAbnTlAbnormality.getAbnmWoreceiveddate() == null )
			newAbnTlAbnormality.setAbnmWoreceiveddate(Constants.pgPassNullDateTime);
		
		if( newAbnTlAbnormality.getAbnmOthers() == null )
			newAbnTlAbnormality.setAbnmOthers("-");
		if(newAbnTlAbnormality.getAbnmResponsibleid()==null)
			newAbnTlAbnormality.setAbnmResponsibleid("-");
		
		CommonMessage.debugMsg("newAbnTlAbnormality.getAbnmRepOthers()....."+newAbnTlAbnormality.getAbnmRepOthers());
		if( newAbnTlAbnormality.getAbnmRepOthers() == null )
			newAbnTlAbnormality.setAbnmRepOthers("-");
		 if(newAbnTlAbnormality.getAbnmResponsibleid()==null)
	    	  newAbnTlAbnormality.setAbnmResponsibleid("-");
		      newAbnTlAbnormality.setAbnmMultipleabn("N");	
		if( newAbnTlAbnormality.getAbnmTempfield4() == null )
			newAbnTlAbnormality.setAbnmTempfield4("-");
		
		if( newAbnTlAbnormality.getAbnmTempfield5() == null )
			newAbnTlAbnormality.setAbnmTempfield5("-");
		
		if( newAbnTlAbnormality.getAbnmTempfield6() == null )
			newAbnTlAbnormality.setAbnmTempfield6("-");
		
		if( newAbnTlAbnormality.getAbnmTempfield7() == null )
			newAbnTlAbnormality.setAbnmTempfield7("-");
		
		if( newAbnTlAbnormality.getAbnmTempfield8() == null )
			newAbnTlAbnormality.setAbnmTempfield8("-");
		
		if( newAbnTlAbnormality.getAbnmTempfield9() == null )
			newAbnTlAbnormality.setAbnmTempfield9("-");
		
		if( newAbnTlAbnormality.getAbnmTempfield10() == null )
			newAbnTlAbnormality.setAbnmTempfield10("-");
		
		if( newAbnTlAbnormality.getAbnmWorktime() == null )
			newAbnTlAbnormality.setAbnmWorktime("0");	
		
		CommonMessage.debugMsg("Wostarttime"+newAbnTlAbnormality.getAbnmWostarttime());
		CommonMessage.debugMsg("Woendtime"+newAbnTlAbnormality.getAbnmWoendtime());

		
		if( newAbnTlAbnormality.getAbnmWostarttime() == null )
			newAbnTlAbnormality.setAbnmWostarttime(currentDateTime);
		else
		{
			CommonMessage.debugMsg("Else Starttime"+abnBean.getAbnmstarttime());
			if(UIUtils.isValidKeyId(abnBean.getAbnmstarttime()))
				newAbnTlAbnormality.setAbnmWostarttime(newAbnTlAbnormality.getAbnmWostarttime() + " "+abnBean.getAbnmstarttime());
			else
				newAbnTlAbnormality.setAbnmWostarttime(newAbnTlAbnormality.getAbnmWostarttime());
		}
				
		if(newAbnTlAbnormality.getAbnmStatus().equals("P"))
			newAbnTlAbnormality.setAbnmWoendtime(Constants.pgPassNullDateTime);
		else
			{
			if(newAbnTlAbnormality.getAbnmWoendtime() ==null)
			{
				if(UIUtils.isValidKeyId(abnBean.getAbnmendtime()))
				{
					if(UIUtils.isValidKeyId(abnBean.getAbnmenddate()))
						newAbnTlAbnormality.setAbnmWoendtime(abnBean.getAbnmenddate() + " "+abnBean.getAbnmendtime());
				}
				else
					newAbnTlAbnormality.setAbnmWoendtime(currentDateTime);	
			}
		
			else
			{
				CommonMessage.debugMsg("Else End Time"+abnBean.getAbnmendtime());
				if(UIUtils.isValidKeyId(abnBean.getAbnmendtime()))
				{
					CommonMessage.debugMsg("END Date : "+abnBean.getAbnmenddate());
					if(UIUtils.isValidKeyId(abnBean.getAbnmenddate()))
						newAbnTlAbnormality.setAbnmWoendtime(abnBean.getAbnmenddate() + " "+abnBean.getAbnmendtime());
					else
						newAbnTlAbnormality.setAbnmWoendtime(newAbnTlAbnormality.getAbnmWoendtime() + " "+abnBean.getAbnmendtime());
				}
				else
					newAbnTlAbnormality.setAbnmWoendtime(CommonFunctions.pg_getDateTimeFromDate( newAbnTlAbnormality.getAbnmWoendtime()));
			}
		}
			CommonMessage.debugMsg("fill values::::::"+newAbnTlAbnormality.getAbnmWoendtime());
			
		/** Set Hse Abnormality Values **/	
		//if(newAbnTlAbnormality.getAbnTlDtl()!=null && !(newAbnTlAbnormality.getAbnTlDtl().equals("")))
		//	newAbnTlAbnormality.setAbnTlDtl(fillHseAbnormalityValues(newAbnTlAbnormality,oldAbnTlAbnormality));			
			
			//newAbnTlAbnormality.setAbnTlAbnhistorydtl(fillAbnHistory(newAbnTlAbnormality,oldAbnTlAbnormality));
		CommonMessage.debugMsg(" In Side the Service IMPL after Fill values");
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return newAbnTlAbnormality; 
		
	}

private AbnTlAbnhistorydtl fillAbnHistory(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality) {

	AbnTlAbnhistorydtl abnTlAbnhistorydtl = newAbnTlAbnormality.getAbnTlAbnhistorydtl();
	CommonMessage.debugMsg("history 1");
	String dateTime = CommonFunctions.dateTimeNow();
	CommonMessage.debugMsg("history 1.1...."+ dateTime);
	//CommonMessage.debugMsg("history 1.1...."+ abnTlAbnhistorydtl.getAbnhKeyid());
	
	//if(abnTlAbnhistorydtl.getAbnhKeyid()==null )			
		abnTlAbnhistorydtl.setAbnhCreatedon(dateTime);
	CommonMessage.debugMsg("history 2");
	if(abnTlAbnhistorydtl.getAbnhModifiedon()==null)
		abnTlAbnhistorydtl.setAbnhModifiedon(dateTime);	
	CommonMessage.debugMsg("history 3");
	abnTlAbnhistorydtl.setAbnhAbnmKeyid(newAbnTlAbnormality.getAbnmKeyid());
	abnTlAbnhistorydtl.setAbnhActive("Y");
	CommonMessage.debugMsg("history 4");
	if(abnTlAbnhistorydtl.getAbnhChangeby()==null)
		abnTlAbnhistorydtl.setAbnhChangeby("{}");
	if(abnTlAbnhistorydtl.getAbnhCreatedby() == null)
		abnTlAbnhistorydtl.setAbnhChangeby("{}");
	if(abnTlAbnhistorydtl.getAbnhDate() == null)
		abnTlAbnhistorydtl.setAbnhDate(Constants.passNullDate);
	CommonMessage.debugMsg("history 5");
	if(abnTlAbnhistorydtl.getAbnhReasons() == null)
		abnTlAbnhistorydtl.setAbnhReasons("{}");
	if(abnTlAbnhistorydtl.getAbnhTempfield1() == null)
		abnTlAbnhistorydtl.setAbnhTempfield1("-");
	if(abnTlAbnhistorydtl.getAbnhTempfield2() == null)
		abnTlAbnhistorydtl.setAbnhTempfield2("-");
	if(abnTlAbnhistorydtl.getAbnhTempfield3() == null)
		abnTlAbnhistorydtl.setAbnhTempfield3("-");
	if(abnTlAbnhistorydtl.getAbnhTempfield4() == null)
		abnTlAbnhistorydtl.setAbnhTempfield4("-");
	CommonMessage.debugMsg("history 6");
	if(abnTlAbnhistorydtl.getAbnhTempfield5() == null)
		abnTlAbnhistorydtl.setAbnhTempfield5("-");
	if(abnTlAbnhistorydtl.getAbnhTempfield6() == null)
		abnTlAbnhistorydtl.setAbnhTempfield6("-");
	if(abnTlAbnhistorydtl.getAbnhType() == null)
		abnTlAbnhistorydtl.setAbnhType("{}");
	CommonMessage.debugMsg("history 7");
	return abnTlAbnhistorydtl;
}

/*private AbnTlDtl fillHseAbnormalityValues(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality, AbnormalityBean abnBean) 
{
	CommonMessage.debugMsg("HseAbnormalities Values:::");
	AbnTlDtl abnTlDtl = newAbnTlAbnormality.getAbnTlDtl();	
	AbnTlDtl oldAbnTlDtl  = null;
	CommonMessage.debugMsg("Inside fill Values");
	newAbnTlAbnormality.setAbnmActive("Y");
	String dateTime = CommonFunctions.dateTimeNow();
	
	if( oldAbnTlAbnormality != null)
	{
		oldAbnTlDtl = oldAbnTlAbnormality.getAbnTlDtl();
	}	
	
	abnTlDtl.setAbndActive("Y");
	if(abnTlDtl.getAbndKeyid() == null )			
		abnTlDtl.setAbndCreatedon(dateTime);
	else
		abnTlDtl.setAbndCreatedon(oldAbnTlDtl.getAbndCreatedon());
	
	if(abnTlDtl.getAbndModifiedon()==null)
		abnTlDtl.setAbndModifiedon(dateTime);	
	abnTlDtl.setAbndPriority("L");
	if(UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmKeyid()))
		abnTlDtl.setAbndAbnormalityid(newAbnTlAbnormality.getAbnmKeyid());
	
			CommonMessage.debugMsg(abnTlDtl.getAbndHirarefno() + " abnTlDtl.getAbndHirarefno()");
	
			if( !UIUtils.isValidKeyId(abnTlDtl.getAbndHirarefno()))
		abnTlDtl.setAbndHirarefno("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndPokayokeid()))
		abnTlDtl.setAbndPokayokeid("{}");
	
	CommonMessage.debugMsg("jhbgh......"+abnTlDtl.getAbndResponsiblity());
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndResponsiblity()))
		abnTlDtl.setAbndResponsiblity("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndImmediateaction()))
		abnTlDtl.setAbndImmediateaction("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndEffectleadsto()))
		abnTlDtl.setAbndEffectleadsto("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndAvoidrecurrence()))
		abnTlDtl.setAbndAvoidrecurrence("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndIspokayokeprovided()))
		abnTlDtl.setAbndIspokayokeprovided("N");
	
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndCircleid()))
		abnTlDtl.setAbndCircleid("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndTempfield2()))
		abnTlDtl.setAbndTempfield2("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndTempfield3()))
		abnTlDtl.setAbndTempfield3("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndTempfield4()))
		abnTlDtl.setAbndTempfield4("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndTempfield5()))
		abnTlDtl.setAbndTempfield5("{}");
	
	if( !UIUtils.isValidKeyId(abnTlDtl.getAbndImprovementteam()))
		abnTlDtl.setAbndImprovementteam("X");
	
	CommonMessage.debugMsg("ImprovementTeam"+abnTlDtl.getAbndImprovementteam());
	
	if(abnBean.getPillarName().equalsIgnoreCase("JH"))
		return null;
	else
		return abnTlDtl;
}*/

@Override
public AbnTlAbnormality select(String keyid) throws Exception {
	CommonMessage.debugMsg("ServiceImpl:"+keyid);
//	return this.abnDao.select(keyid);
	return abnServiceApi.select(keyid);
}


@Override
public AbnTlAbnormality abnformfill(String abnId) {
	// TODO Auto-generated method stub
	return this.abnDao.abnformfill(abnId);
}

@Override
public List<String[]> getAbnUpdatedRow(String keyId) throws Exception {
	// TODO Auto-generated method stub
	return this.abnDao.getAbnUpdatedRow(keyId);
}

@Override
public List<String[]> getAllModifyForm(CommonFilter commonFilter,String setStatus,String pillarType)
		throws Exception {
	// TODO Auto-generated method stub
	return this.abnDao.getModifyFormDao(commonFilter,setStatus,pillarType);
}

@Override
public AbnTlAbnormality delete(AbnTlAbnormality newAbnTlAbnormality)
		throws ValidationExceptions, Exception {
	
	return abnDao.delete(newAbnTlAbnormality);
}

@Override
public List<String> getTgtDateFromConfig() throws Exception {
	// TODO Auto-generated method stub
	return abnDao.getTgtDateFromConfig();
}


public void getShift(AbnTlAbnormality newAbnTlAbnormality,AbnormalityBean abnBean) throws BusinessApplicationExceptions
{
	CommonMessage.debugMsg("Inside getShift"+abnBean.getFactory());
		String shift;
		try {
			shift = getShift(newAbnTlAbnormality.getAbnmFlid(),abnBean.getAbnmDetectedbytime());
			CommonMessage.debugMsg(" shift in fil values"+shift);
			if(shift!=null && shift.length()>0)
			{	
				if( newAbnTlAbnormality.getAbnmShiftid() == null )
					newAbnTlAbnormality.setAbnmShiftid(shift);
			}
		} 
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("BUSS "+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}


private String getTagCode(String tagclassId)throws Exception
{
	return abnDao.getTagCode(tagclassId);
}

@Override
public String getAbnTypeFlag(String abtmKeyid) throws Exception {
	
	 return abnDao.getAbnTypeFlag(abtmKeyid);
}
private String getElementID(String flid)throws Exception {

	 return abnDao.getElementID(flid);
}


@Override
public AbnTlDtl selectHseData(String abdKeyId,String flag) throws Exception {
	// TODO Auto-generated method stub
	return this.abnDao.selectHseData(abdKeyId,flag);
}

public String checkTag() throws Exception{
	return this.abnDao.checkTag();
}
public String checkGen() throws Exception{
	return this.abnDao.checkGen();
}
public String checkDirectEntry(String woID)throws Exception
{
	return this.abnDao.checkDirectEntry(woID);
}

public String showAbnQuery()throws Exception
{
	return this.abnDao.showAbnQuery();
}
@Override
public Workbook abnExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,String reportType)throws Exception {
	
	return abnDao.abnExportExcel(commonFilter,tblJSONObj,reportType);
}

@Override
public List<String[]> getAbnormalityDetails(String keyId, GridParams gridParams)throws Exception {
	return this.abnDao.getAbnormalityDetails(keyId,gridParams);
}

@Override
public String getAbnormalityDetailsCount(String keyId, GridParams gridParams)throws Exception {
	return this.abnDao.getAbnormalityDetailsCount(keyId,gridParams);
}

@Override
public Workbook AbnDetailsExportExcel(JSONObject tblJSONObj, String format,GridParams gridParams,String keyId,String count) throws Exception {
	return this.abnDao.getExportExcel(tblJSONObj,format,gridParams, keyId,count);
}

@Override
public List<String[]> getAllAbnormalityDetails(CommonFilter commonFilter,String keyid,String columnId)throws Exception {
	return this.abnDao.getAllAbnormalityDetails(commonFilter,keyid,columnId);
}

@Override
public Workbook getHSEAbnExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
	return this.abnDao.getHSEAbnExportExcel(commonFilter,  colModel,rptFormat);
}

@Override
public List<AbnTlAbnormality> updateBulkTagRemoval(List<AbnTlAbnormality> newAbnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality) throws ValidationExceptions,BusinessApplicationExceptions, Exception  {

	try{
	//fillValues(newAbnTlAbnormality,oldAbnTlAbnormality,abnBean);		
	
	//AbnTlDtl abnTlDtl = fillHseAbnormalityValues(newAbnTlAbnormality,oldAbnTlAbnormality,abnBean);
	CommonMessage.debugMsg("before redTagCompletion");
	for( AbnTlAbnormality abnTlAbnormality :newAbnTlAbnormality)
	{
	//validations.validate(abnTlAbnormality,"AbnormalityCreation","redTagCompletionTest");
	}
	//CommonMessage.debugMsg("affter redTagCompletion"+newAbnTlAbnormality.getClass().getName());
	
	return abnDao.updateBulkTagRemoval(newAbnTlAbnormality);
	
	}
	catch (ValidationExceptions e){
		CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
		throw new ValidationExceptions(e.getMessage());
	}	
}

@Override
public List<String[]> getRepeatedAbn(CommonFilter commonFilter)	throws Exception {
	//return this.abnDao.getRepeatedAbn(commonFilter);
	return this.abnDao.getRepeatedAbn(commonFilter);
	}

@Override
public List<String[]> getAbnAllocation(CommonFilter commonFilter)	throws Exception {
	return this.abnDao.getAbnAllocation(commonFilter);
}

@Override
public List<ComboBox> getAfeemcombo(ComboFilter comboFilter) throws Exception {
	

//	comboFilter.setCodeField("ABNM_ISSUENO");
	comboFilter.setNameField("AFEM_NAME");
	comboFilter.setIdField("AFEM_KEYID");
	comboFilter.setTableName(TableNames.TBL_ABN_TL_AFEEMMST);
	comboFilter.setOrderByField("AFEM_KEYID");
	return commonFilterDao.fillComboValues(comboFilter);	
}

@Override
public List<AbnTlAbnormality> updateAbnAllocation(List<AbnTlAbnormality> newAbnTlAbnormality,AbnTlAbnormality existAbnTlAbnormality) throws Exception {

	try{
	//fillValues(newAbnTlAbnormality,oldAbnTlAbnormality,abnBean);		
	
	//AbnTlDtl abnTlDtl = fillHseAbnormalityValues(newAbnTlAbnormality,oldAbnTlAbnormality,abnBean);
	CommonMessage.debugMsg("before redTagCompletion");
	CommonMessage.debugMsg("affter redTagCompletion"+newAbnTlAbnormality.getClass().getName());
	
//	return abnDao.updateAbnAllocation(newAbnTlAbnormality);
	return abnServiceApi.updateAbnAllocation(newAbnTlAbnormality);
	
	}
	catch (ValidationExceptions e){
		CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
		throw new ValidationExceptions(e.getMessage());
	}	

}

@Override
public Workbook getAbnAllocationExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
	// TODO Auto-generated method stub
	return this.abnDao.getAbnAllocationExportExcel(commonFilter,  colModel,rptFormat);
}

@Override
public String getAbnTradeValues(String abnmRespons) throws Exception {
	return abnDao.getAbnTradeValues(abnmRespons);
}

@Override
public AbnTlAbnormality updateabncomp(AbnTlAbnormality newAbnTlAbnormality)
		throws Exception {
	// TODO Auto-generated method stub
//	return abnDao.updateabncomp(newAbnTlAbnormality);
	return abnServiceApi.updateAbnComp(newAbnTlAbnormality);
}

@Override
public List<String[]> FillabnpopData(String keyid) throws Exception {
	// TODO Auto-generated method stub
	return abnDao.FillabnpopData(keyid);
}

@Override
public String getServiceCount(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return abnDao.getServiceCount(commonFilter);
}
/*public List<ComboBox> getSubType(String typeId,ComboFilter htatypeComboFilter) throws Exception{
	htatypeComboFilter.setCodeField("AHSM_CODE");
	htatypeComboFilter.setNameField("AHSM_NAME");
	htatypeComboFilter.setIdField("AHSM_KEYID");
		
	htatypeComboFilter.setTableName(TableNames.TBL_ABN_TL_HTASOCMST);
		
	if( UIUtils.isValidKeyId(typeId) )
		htatypeComboFilter.setCondSql(" AND AHSM_ABNORMALITYTYPE = '" + typeId + "'" );
	return commonFilterDao.fillComboValues(htatypeComboFilter);
}*/

public String getSubType(String typeId)throws Exception{
	return abnDao.getSubType(typeId);
}

public List<AbnTlAbnormality> Multiplecreate(List<AbnTlAbnormality> newAbnTlAbnormality,AbnormalityBean abnormalityBean,String flid,String sectionId,AdmTlUsermst createdBy,String ism,String docID,String types,String remarks) throws ValidationExceptions,BusinessApplicationExceptions, Exception{
	try {
		String validationsFor;		
		String tagClass=null;
		String abnTypeFlag=null;
		CommonMessage.debugMsg("Inside the Create Function");
		
	    List<AbnTlAbnormality> AbnList=newAbnTlAbnormality;
		for(AbnTlAbnormality getElementId :AbnList){
			String elementId = commonFilterDao.getElementID(flid);
			getElementId.setAbnmElementid(elementId);
			getElementId.setAbnmFlid(flid);
			getElementId.setAbnmSectionid(sectionId);
			getElementId.setAbnmCreatedby(createdBy.getUsrm_ccno());
		}
	   	
		
//		return abnDao.Multiplecreate(AbnMultiplefillValues(newAbnTlAbnormality,abnormalityBean,ism,docID,types,remarks));
		return abnServiceApi.MultipleSave(AbnMultiplefillValues(newAbnTlAbnormality,abnormalityBean,ism,docID,types,remarks));
		
	}catch (ValidationExceptions e){
		CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
		throw new ValidationExceptions(e.getMessage());
	}
}
	
private List<AbnTlAbnormality> AbnMultiplefillValues(List<AbnTlAbnormality> newAbnTlAbnormality,AbnormalityBean abnormalityBean,String ism,String docID,String types,String remarks){
	// TODO Auto-generated method stub
		List<AbnTlAbnormality> abnormalityLinks =newAbnTlAbnormality;
   		List<AbnTlAbnormality> abnormalityLinkList = new ArrayList<AbnTlAbnormality>();
   		int index=0;
		String currentDate = CommonFunctions.getDate();
   		if(abnormalityLinks!=null)
   		for( AbnTlAbnormality abnormalitydata : abnormalityLinks)
   		{	
   			String dateTime = CommonFunctions.pg_dateTimeNow();
   			AbnTlAbnormality genEntTlBatchEmployeeLink1 = abnormalityLinks.get(index);
   			index++;
   			abnormalitydata.setAbnmActive("Y");
   			abnormalitydata.setAbnmModifiedon(dateTime);
   			abnormalitydata.setAbnmCreatedon(dateTime);
   			String tagClass=abnormalitydata.getAbnmTagclassid();
   			String Countermeaure=abnormalitydata.getAbnmCountermeasure();
   			CommonMessage.debugMsg("The Countermeaure:::"+Countermeaure);
   			
   			if(tagClass.equals("TAG000002")){  //Red Tag
   			  abnormalitydata.setAbnmStatus("P");
   			  
   			}
   			else if(tagClass.equals("TAG000001") && Countermeaure!=null){ //White Tag
  				 abnormalitydata.setAbnmStatus("C");
  			}
   			else if(tagClass.equals("TAG000001")){  //White Tag
   				abnormalitydata.setAbnmStatus("P");
   			}
   			
   			if(tagClass.equals("TAG000002") && ism.equals("true")){
   				abnormalitydata.setAbnmShutdownmaint("Y");
   			}
   			else{
   				abnormalitydata.setAbnmShutdownmaint("-");
   			}
   			
   			if(types.equals("VCC")){
   				abnormalitydata.setAbnmRefdocid(docID);
   				abnormalitydata.setAbnmRefdoctype(types);
   				abnormalitydata.setAbnmRemarks(remarks);
			}
   			else{
   				abnormalitydata.setAbnmRefdocid("{}");
   				abnormalitydata.setAbnmRefdoctype("{}");
   			}
   					
   			if(!UIUtils.isValidKeyId(abnormalitydata.getAbnmFlid())){
   				abnormalitydata.setAbnmFlid("{}");
   			 }
   		 if(abnormalitydata.getAbnmFactoryid()==null)
   			abnormalitydata.setAbnmFactoryid("{}");
         if(abnormalitydata.getAbnmAssemblyid()==null)
        	 abnormalitydata.setAbnmAssemblyid("{}");
         if(abnormalitydata.getAbnmShiftid()==null)
        	 abnormalitydata.setAbnmShiftid("{}");
         if( abnormalitydata.getAbnmBlockdiagramref() == null )
        	 abnormalitydata.setAbnmBlockdiagramref("{}");		
   	  if( abnormalitydata.getAbnmCategoryid() == null )
   		   abnormalitydata.setAbnmCategoryid("{}");
   	if( abnormalitydata.getAbnmCellid() == null )
           abnormalitydata.setAbnmCellid("{}");
   	  if(abnormalitydata.getAbnmDetectedby()==null)	
   		   abnormalitydata.setAbnmDetectedby("{}");
   	  if( abnormalitydata.getAbnmCompletedby() == null )
   		   abnormalitydata.setAbnmCompletedby("{}");	
   		if( abnormalitydata.getAbnmCountermeasure() == null )
   			abnormalitydata.setAbnmCountermeasure("{}");	
   		if( abnormalitydata.getAbnmDate() == null )
   			abnormalitydata.setAbnmDate(dateTime);
   	if( abnormalitydata.getAbnmCreatedby()== null)
   			abnormalitydata.setAbnmCreatedby("{}");
   		if( abnormalitydata.getAbnmDescription() == null )
   			abnormalitydata.setAbnmDescription("{}");
   		if( abnormalitydata.getAbnmDetailedesc() == null )
   			abnormalitydata.setAbnmDetailedesc("{}");
   		if( abnormalitydata.getAbnmDetectiondate() == null )
   			abnormalitydata.setAbnmDetectiondate(dateTime);
   		else
   		{
   			abnormalitydata.setAbnmDetectiondate(CommonFunctions.pg_getDateTimeFromDate(abnormalitydata.getAbnmDetectiondate()));
   		}	
   		
   		
   		if( abnormalitydata.getAbnmDowntime() == null )
   			abnormalitydata.setAbnmDowntime("0");
   		
   		if( abnormalitydata.getAbnmEquipmentid() == null )
   			abnormalitydata.setAbnmEquipmentid("{}");
   		
   		if( abnormalitydata.getAbnmFeedbackdate() == null )
   			abnormalitydata.setAbnmFeedbackdate(Constants.pgPassNullDateTime);
   		
   		if( abnormalitydata.getAbnmFeedbackid() == null )
   			abnormalitydata.setAbnmFeedbackid("{}");
   		
   		if( abnormalitydata.getAbnmImpactid() == null )
   			abnormalitydata.setAbnmImpactid("{}");
   		
   		if( abnormalitydata.getAbnmPriority() == null )
   			abnormalitydata.setAbnmPriority("{}");
   		
   		if( abnormalitydata.getAbnmPreventivemeasure() == null )
   			abnormalitydata.setAbnmPreventivemeasure("{}");
   		
   /*		if( abnormalitydata.getAbnmRefdocid() == null )
   			abnormalitydata.setAbnmRefdocid("{}");
   		
   		if( abnormalitydata.getAbnmRefdoctype() == null )
   			abnormalitydata.setAbnmRefdoctype("{}");
   		*/
   		if( abnormalitydata.getAbnmRemarks() == null )
   			abnormalitydata.setAbnmRemarks("{}");
   		
   		if( abnormalitydata.getAbnmResponsetime() == null )
   			abnormalitydata.setAbnmResponsetime("0");			
   		
   		if( abnormalitydata.getAbnmRevisionno() == null )
   			abnormalitydata.setAbnmRevisionno("{}");
   		
   		if( abnormalitydata.getAbnmSectionid() == null )
   			abnormalitydata.setAbnmSectionid("{}");

   		if( abnormalitydata.getAbnmSubtype() == null )
   			abnormalitydata.setAbnmSubtype("{}");
   		
   		if( abnormalitydata.getAbnmTagclassid() == null )
   			abnormalitydata.setAbnmTagclassid("{}");			
   		
   		if( abnormalitydata.getAbnmTargetdate() == null )
   			abnormalitydata.setAbnmTargetdate(dateTime);
   		else
   		{
   			abnormalitydata.setAbnmTargetdate(CommonFunctions.pg_getDateTimeFromDate(abnormalitydata.getAbnmTargetdate()));
   		}
   		if( abnormalitydata.getAbnmTargetremarks() == null )
   			abnormalitydata.setAbnmTargetremarks("{}");
   		if( abnormalitydata.getAbnmContaminant() == null )
   			abnormalitydata.setAbnmContaminant("{}");
   		
   		if( abnormalitydata.getAbnmMode() == null )
   			abnormalitydata.setAbnmMode("{}");

   		abnormalitydata.setAbnmFactoryid("{}");
   		abnormalitydata.setAbnmPillar("JH");
   		
   		 
   		
  	/*	if(abnormalityBean.getPillarName().equalsIgnoreCase("JH"))
   		{	
  			abnormalitydata.setAbnmPillar("JH");
   		}
   		else if(abnormalityBean.getPillarName().equalsIgnoreCase("SHE"))
   		{
   			abnormalitydata.setAbnmPillar("SHE");
   			abnormalitydata.setAbnmRefdoctype("SHE");
   			abnormalitydata.setAbnmEquipmentid("{}");
   		}*/
   		
   		if( abnormalitydata.getAbnmSafetypatrol() == null )
   			abnormalitydata.setAbnmSafetypatrol("{}");
   		
   		if( abnormalitydata.getAbnmRelatedto() == null )
   			abnormalitydata.setAbnmRelatedto("{}");
   		
   		if(!UIUtils.isValidKeyId(abnormalitydata.getAbnmMould()))
   			abnormalitydata.setAbnmMould("{}");		
   		
   		if( abnormalitydata.getAbnmTradeid() == null )
   			abnormalitydata.setAbnmTradeid("{}");
   		
   		if( abnormalitydata.getAbnmTypeid() == null )
   			abnormalitydata.setAbnmTypeid("{}");
   		
   		if( abnormalitydata.getAbnmWhatcause() == null )
   			abnormalitydata.setAbnmWhatcause("{}");			
   		
   		if( abnormalitydata.getAbnmWhyabnhappened() == null )
   			abnormalitydata.setAbnmWhyabnhappened("{}");
   		
   		if( abnormalitydata.getAbnmWodetailid() == null )
   			abnormalitydata.setAbnmWodetailid("{}");
   	
   		if( abnormalitydata.getAbnmWomasterid() == null )
   			abnormalitydata.setAbnmWomasterid("{}");
   	
   		if( abnormalitydata.getAbnmPillarid() == null )
   			abnormalitydata.setAbnmPillarid("{}");
   		
   		if( abnormalitydata.getAbnmRepeatedabn() == null )
   			abnormalitydata.setAbnmRepeatedabn("{}");
   		
   		if( abnormalitydata.getAbnmAfeemid() == null )
   			abnormalitydata.setAbnmAfeemid("{}");
   		
   		if( abnormalitydata.getAbnmEffectivedate() == null )
   			abnormalitydata.setAbnmEffectivedate(Constants.pgPassNullDateTime);
   		
   		if( abnormalitydata.getAbnmNotifysap() == null )
   			abnormalitydata.setAbnmNotifysap("-");
   		
   		if( abnormalitydata.getAbnmTentativrDate() == null )
   			abnormalitydata.setAbnmTentativrDate(Constants.pgPassNullDateTime);
   		
   		 if(abnormalitydata.getAbnmShutdownid()==null)
   			 abnormalitydata.setAbnmShutdownid("{}");
 
   		if( abnormalitydata.getAbnmAccecpatncerequired() == null )
   			abnormalitydata.setAbnmAccecpatncerequired("-");
   		
   		if( abnormalitydata.getAbnmAccecptDate() == null )
   			abnormalitydata.setAbnmAccecptDate(Constants.pgPassNullDateTime);
   		
   		if( abnormalitydata.getAbnmAccecpted() == null )
   			abnormalitydata.setAbnmAccecpted("-");
   		
   		if( abnormalitydata.getAbnmElementid() == null)
   			abnormalitydata.setAbnmElementid("{}");
   		
   		if( abnormalitydata.getAbnmWoreceiveddate() == null )
   			abnormalitydata.setAbnmWoreceiveddate(Constants.pgPassNullDateTime);
   		
   		if( abnormalitydata.getAbnmOthers() == null )
   			abnormalitydata.setAbnmOthers("-");
   		if( abnormalitydata.getAbnmRepOthers() == null )
   			abnormalitydata.setAbnmRepOthers("-");
   		
         if(abnormalitydata.getAbnmResponsibleid()==null)
        	 abnormalitydata.setAbnmResponsibleid("-");
         
         abnormalitydata.setAbnmMultipleabn("Y");
         if(abnormalitydata.getAbnmTempfield4()==null)
        	 abnormalitydata.setAbnmTempfield4("-");
         if(abnormalitydata.getAbnmTempfield5()==null)
        	 abnormalitydata.setAbnmTempfield5("-");
         if(abnormalitydata.getAbnmTempfield6()==null)
        	 abnormalitydata.setAbnmTempfield6("-");
         if(abnormalitydata.getAbnmTempfield7()==null)
        	 abnormalitydata.setAbnmTempfield7("-");
         if(abnormalitydata.getAbnmTempfield8()==null)
        	 abnormalitydata.setAbnmTempfield8("-");
         if(abnormalitydata.getAbnmTempfield9()==null)
        	 abnormalitydata.setAbnmTempfield9("-");
         if(abnormalitydata.getAbnmTempfield10()==null)
        	 abnormalitydata.setAbnmTempfield10("-");
         if( abnormalitydata.getAbnmWorktime() == null )
        	 abnormalitydata.setAbnmWorktime("0");
         abnormalitydata.setAbnmWostarttime(dateTime);
         abnormalitydata.setAbnmWoendtime(dateTime);
   
   			abnormalityLinkList.add(abnormalitydata);
   	}
   	return abnormalityLinkList;
}


/*
private AbnTlAbnormality AbnMultiplefillValues(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality,AbnormalityBean abnBean) throws BusinessApplicationExceptions 
{	
	String dateTime = CommonFunctions.dateTimeNow();
	String currentDate = CommonFunctions.getDate();
      if(!UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmKeyid())){
    	  newAbnTlAbnormality.setAbnmActive("Y");
    	  newAbnTlAbnormality.setAbnmCreatedon(dateTime);
    	  newAbnTlAbnormality.setAbnmModifiedon(dateTime);
	}
	else{
  	  newAbnTlAbnormality.setAbnmActive("Y");
  	  newAbnTlAbnormality.setAbnmCreatedon(dateTime);
  	  newAbnTlAbnormality.setAbnmModifiedon(dateTime);
	}
      if(newAbnTlAbnormality.getAbnmFlid()==null)
    	  newAbnTlAbnormality.setAbnmFlid("{}");
      if(newAbnTlAbnormality.getAbnmFactoryid()==null)
    	  newAbnTlAbnormality.setAbnmFactoryid("{}");
      if(newAbnTlAbnormality.getAbnmAssemblyid()==null)
    	  newAbnTlAbnormality.setAbnmAssemblyid("{}");
      if(newAbnTlAbnormality.getAbnmShiftid()==null)
    	  newAbnTlAbnormality.setAbnmShiftid("{}");
      if( newAbnTlAbnormality.getAbnmBlockdiagramref() == null )
			newAbnTlAbnormality.setAbnmBlockdiagramref("{}");		
	  if( newAbnTlAbnormality.getAbnmCategoryid() == null )
			newAbnTlAbnormality.setAbnmCategoryid("{}");
	        newAbnTlAbnormality.setAbnmCellid("{}");
	  if(newAbnTlAbnormality.getAbnmDetectedby()==null)	
		  newAbnTlAbnormality.setAbnmDetectedby("{}");
	  if( newAbnTlAbnormality.getAbnmCompletedby() == null )
			newAbnTlAbnormality.setAbnmCompletedby("{}");	
		if( newAbnTlAbnormality.getAbnmCountermeasure() == null )
			newAbnTlAbnormality.setAbnmCountermeasure("{}");	
		if( newAbnTlAbnormality.getAbnmDate() == null )
			newAbnTlAbnormality.setAbnmDate(currentDate);
		if( newAbnTlAbnormality.getAbnmCreatedby() == null )
			newAbnTlAbnormality.setAbnmCreatedby("{}");
		if( newAbnTlAbnormality.getAbnmDescription() == null )
			newAbnTlAbnormality.setAbnmDescription("{}");
		if( newAbnTlAbnormality.getAbnmDetailedesc() == null )
			newAbnTlAbnormality.setAbnmDetailedesc("{}");
		if( newAbnTlAbnormality.getAbnmDetectiondate() == null )
			newAbnTlAbnormality.setAbnmDetectiondate(dateTime);
		else
		{
			String DetectedDateSpn=UIUtils.getActualDateForm(newAbnTlAbnormality.getAbnmDetectiondate());
			String dd=DetectedDateSpn+" "+abnBean.getAbnmDetectedbytime();
			CommonMessage.debugMsg("DetectedDateSpn="+DetectedDateSpn);
			newAbnTlAbnormality.setAbnmDetectiondate(dd);
		}	
		if( newAbnTlAbnormality.getAbnmDowntime() == null )
			newAbnTlAbnormality.setAbnmDowntime("0");
		
		if( newAbnTlAbnormality.getAbnmEquipmentid() == null )
			newAbnTlAbnormality.setAbnmEquipmentid("{}");
		
		if( newAbnTlAbnormality.getAbnmFeedbackdate() == null )
			newAbnTlAbnormality.setAbnmFeedbackdate(Constants.passNullDate);
		
		if( newAbnTlAbnormality.getAbnmFeedbackid() == null )
			newAbnTlAbnormality.setAbnmFeedbackid("{}");
		
		if( newAbnTlAbnormality.getAbnmImpactid() == null )
			newAbnTlAbnormality.setAbnmImpactid("{}");
		
		if( newAbnTlAbnormality.getAbnmPriority() == null )
			newAbnTlAbnormality.setAbnmPriority("{}");
		
		if( newAbnTlAbnormality.getAbnmPreventivemeasure() == null )
			newAbnTlAbnormality.setAbnmPreventivemeasure("{}");
		
		if( newAbnTlAbnormality.getAbnmRefdocid() == null )
			newAbnTlAbnormality.setAbnmRefdocid("{}");
		
		
		if( newAbnTlAbnormality.getAbnmRefdoctype() == null )
			newAbnTlAbnormality.setAbnmRefdoctype("{}");
		
		if( newAbnTlAbnormality.getAbnmRemarks() == null )
			newAbnTlAbnormality.setAbnmRemarks("{}");
		
		if( newAbnTlAbnormality.getAbnmResponsetime() == null )
			newAbnTlAbnormality.setAbnmResponsetime("0");			
		
		if( newAbnTlAbnormality.getAbnmRevisionno() == null )
			newAbnTlAbnormality.setAbnmRevisionno("{}");
		
		if( newAbnTlAbnormality.getAbnmSectionid() == null )
			newAbnTlAbnormality.setAbnmSectionid("{}");
		
		
		if( newAbnTlAbnormality.getAbnmSubtype() == null )
			newAbnTlAbnormality.setAbnmSubtype("{}");
		
		if( newAbnTlAbnormality.getAbnmTagclassid() == null )
			newAbnTlAbnormality.setAbnmTagclassid("{}");			
		
		if( newAbnTlAbnormality.getAbnmTargetdate() == null )
			newAbnTlAbnormality.setAbnmTargetdate(currentDate);
		CommonMessage.debugMsg("rEMARKS : "+newAbnTlAbnormality.getAbnmTargetremarks());
		if( newAbnTlAbnormality.getAbnmTargetremarks() == null )
			newAbnTlAbnormality.setAbnmTargetremarks("{}");
		if( newAbnTlAbnormality.getAbnmContaminant() == null )
			newAbnTlAbnormality.setAbnmContaminant("{}");
		
		if( newAbnTlAbnormality.getAbnmMode() == null )
			newAbnTlAbnormality.setAbnmMode("{}");

			newAbnTlAbnormality.setAbnmFactoryid("{}");
		
		if(abnBean.getPillarName().equalsIgnoreCase("JH"))
		{	
			newAbnTlAbnormality.setAbnmPillar("JH");
		}
		else if(abnBean.getPillarName().equalsIgnoreCase("SHE"))
		{
			newAbnTlAbnormality.setAbnmPillar("SHE");
			newAbnTlAbnormality.setAbnmRefdoctype("SHE");
			newAbnTlAbnormality.setAbnmEquipmentid("{}");
		}
		if( newAbnTlAbnormality.getAbnmSafetypatrol() == null )
			newAbnTlAbnormality.setAbnmSafetypatrol("{}");
		
		if( newAbnTlAbnormality.getAbnmRelatedto() == null )
			newAbnTlAbnormality.setAbnmRelatedto("{}");
		
		if(!UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmMould()))
			newAbnTlAbnormality.setAbnmMould("{}");		
		
		if( newAbnTlAbnormality.getAbnmTradeid() == null )
			newAbnTlAbnormality.setAbnmTradeid("{}");
		
		if( newAbnTlAbnormality.getAbnmTypeid() == null )
			newAbnTlAbnormality.setAbnmTypeid("{}");
		
		if( newAbnTlAbnormality.getAbnmWhatcause() == null )
			newAbnTlAbnormality.setAbnmWhatcause("{}");			
		
		if( newAbnTlAbnormality.getAbnmWhyabnhappened() == null )
			newAbnTlAbnormality.setAbnmWhyabnhappened("{}");
		
		if( newAbnTlAbnormality.getAbnmWodetailid() == null )
			newAbnTlAbnormality.setAbnmWodetailid("{}");
	
		if( newAbnTlAbnormality.getAbnmWomasterid() == null )
			newAbnTlAbnormality.setAbnmWomasterid("{}");
	
		if( newAbnTlAbnormality.getAbnmPillarid() == null )
			newAbnTlAbnormality.setAbnmPillarid("{}");
		
		if( newAbnTlAbnormality.getAbnmRepeatedabn() == null )
			newAbnTlAbnormality.setAbnmRepeatedabn("{}");
		
		if( newAbnTlAbnormality.getAbnmAfeemid() == null )
			newAbnTlAbnormality.setAbnmAfeemid("{}");
		
		if( newAbnTlAbnormality.getAbnmEffectivedate() == null )
			newAbnTlAbnormality.setAbnmEffectivedate(Constants.passNullDate);
		
		if( newAbnTlAbnormality.getAbnmNotifysap() == null )
			newAbnTlAbnormality.setAbnmNotifysap("-");
		
		if( newAbnTlAbnormality.getAbnmTentativrDate() == null )
			newAbnTlAbnormality.setAbnmTentativrDate(Constants.passNullDate);
		
		
		if( newAbnTlAbnormality.getAbnmShutdownid() == null )
			newAbnTlAbnormality.setAbnmShutdownid("{}");
		
		if( newAbnTlAbnormality.getAbnmShutdownmaint() == null )
			newAbnTlAbnormality.setAbnmShutdownmaint("-");
		
		
		if( newAbnTlAbnormality.getAbnmAccecpatncerequired() == null )
			newAbnTlAbnormality.setAbnmAccecpatncerequired("-");
		
		if( newAbnTlAbnormality.getAbnmAccecptDate() == null )
			newAbnTlAbnormality.setAbnmAccecptDate(Constants.passNullDate);
		
		if( newAbnTlAbnormality.getAbnmAccecpted() == null )
			newAbnTlAbnormality.setAbnmAccecpted("-");
		
		if( newAbnTlAbnormality.getAbnmElementid() == null)
			newAbnTlAbnormality.setAbnmElementid("{}");
		
		if( newAbnTlAbnormality.getAbnmWoreceiveddate() == null )
			newAbnTlAbnormality.setAbnmWoreceiveddate(Constants.passNullDate);
		
		if( newAbnTlAbnormality.getAbnmOthers() == null )
			newAbnTlAbnormality.setAbnmOthers("-");
		if( newAbnTlAbnormality.getAbnmRepOthers() == null )
			newAbnTlAbnormality.setAbnmRepOthers("-");
		
      if(newAbnTlAbnormality.getAbnmResponsibleid()==null)
    	  newAbnTlAbnormality.setAbnmResponsibleid("-");
      
    	  newAbnTlAbnormality.setAbnmMultipleabn("Y");
      if(newAbnTlAbnormality.getAbnmTempfield4()==null)
    	  newAbnTlAbnormality.setAbnmTempfield4("-");
      if(newAbnTlAbnormality.getAbnmTempfield5()==null)
    	  newAbnTlAbnormality.setAbnmTempfield5("-");
      if(newAbnTlAbnormality.getAbnmTempfield6()==null)
    	  newAbnTlAbnormality.setAbnmTempfield6("-");
      if(newAbnTlAbnormality.getAbnmTempfield7()==null)
    	  newAbnTlAbnormality.setAbnmTempfield7("-");
      if(newAbnTlAbnormality.getAbnmTempfield8()==null)
    	  newAbnTlAbnormality.setAbnmTempfield8("-");
      if(newAbnTlAbnormality.getAbnmTempfield9()==null)
    	  newAbnTlAbnormality.setAbnmTempfield9("-");
      if(newAbnTlAbnormality.getAbnmTempfield10()==null)
    	  newAbnTlAbnormality.setAbnmTempfield10("-");
      if( newAbnTlAbnormality.getAbnmWorktime() == null )
			newAbnTlAbnormality.setAbnmWorktime("0");
            newAbnTlAbnormality.setAbnmWostarttime(currentDate);
            newAbnTlAbnormality.setAbnmWoendtime(currentDate);
		
		CommonMessage.debugMsg("Wostarttime"+newAbnTlAbnormality.getAbnmWostarttime());
		CommonMessage.debugMsg("Woendtime"+newAbnTlAbnormality.getAbnmWoendtime());

		
		
		newAbnTlAbnormality.setabnTldtl(fillValuesAbnDetails(newAbnTlAbnormality,oldAbnTlAbnormality));
	    return newAbnTlAbnormality;	
	    
}
*/



/*private List<AbnTlDtl> fillValuesAbnDetails(AbnTlAbnormality newAbnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality) {
	List<AbnTlDtl> AbnTlDtlList=newAbnTlAbnormality.getabnTldtl();
	List<AbnTlDtl> AbnTlDtlListnew= new ArrayList<AbnTlDtl>();
			
	for(int i=0;i<AbnTlDtlList.size();i++){
		AbnTlDtl newAbnTlDtl=AbnTlDtlList.get(i);
		
		String dateTime = CommonFunctions.dateTimeNow();		
		
		
		if( ! UIUtils.isValidKeyId(newAbnTlDtl.getAbndKeyid()))
		{
			newAbnTlDtl.setAbndActive("Y");
			newAbnTlDtl.setAbndCreatedon(dateTime);
            newAbnTlDtl.setAbndModifiedon(dateTime);
		}
		else{
			newAbnTlDtl.setAbndActive("Y");
			newAbnTlDtl.setAbndCreatedon(dateTime);
            newAbnTlDtl.setAbndModifiedon(dateTime);
		}
		if(newAbnTlDtl.getAbndAbnormalityid()==null)
             newAbnTlDtl.setAbndAbnormalityid("{}");
		if(newAbnTlDtl.getAbndIspokayokeprovided()==null)
			  newAbnTlDtl.setAbndIspokayokeprovided("N");
			  newAbnTlDtl.setAbndPriority("L");
		if(newAbnTlDtl.getAbndHirarefno()==null)
				newAbnTlDtl.setAbndHirarefno("{}");
		if(newAbnTlDtl.getAbndImmediateaction()==null)
			newAbnTlDtl.setAbndImmediateaction("{}");
		if(newAbnTlDtl.getAbndEffectleadsto()==null)
			newAbnTlDtl.setAbndEffectleadsto("{}");
		if(newAbnTlDtl.getAbndAvoidrecurrence()==null)
 			newAbnTlDtl.setAbndAvoidrecurrence("{}");
        if(newAbnTlDtl.getAbndPokayokeid()==null)
        	newAbnTlDtl.setAbndPokayokeid("{}");
		 if(newAbnTlDtl.getAbndImprovementteam()==null)
			 newAbnTlDtl.setAbndImprovementteam("X");
		 if(newAbnTlDtl.getAbndResponsiblity()==null)
			 newAbnTlDtl.setAbndResponsiblity("{}");
		if(newAbnTlDtl.getAbndCircleid()==null)
            newAbnTlDtl.setAbndCircleid("{}");
		if(newAbnTlDtl.getAbndTempfield2()==null)
            newAbnTlDtl.setAbndTempfield2("{}");
		if(newAbnTlDtl.getAbndTempfield3()==null)
            newAbnTlDtl.setAbndTempfield3("{}");		
		if(newAbnTlDtl.getAbndTempfield4()==null)
            newAbnTlDtl.setAbndTempfield4("{}");		
		if(newAbnTlDtl.getAbndTempfield5()==null)
            newAbnTlDtl.setAbndTempfield5("{}");		

		   AbnTlDtlListnew.add(newAbnTlDtl);			
		}
	return AbnTlDtlList;
}*/

@Override
public List<String[]> getMultipleAbnDetail(List<String> keyids)
throws Exception {
// TODO Auto-generated method stub
//return this.abnDao.getMultipleAbnDetail(keyids);
return this.abnServiceApi.getMultipleAbnDetail(keyids);
}

@Override
public List<String[]> getMultipleAbngridDetail(CommonFilter commonFilter)
throws Exception {
// TODO Auto-generated method stub
return this.abnDao.getMultipleAbngridDetail(commonFilter);
}

@Override
public List<String[]> getIndividualModifyForm(CommonFilter commonFilter,String setStatus,String pillarType)
		throws Exception {
	// TODO Auto-generated method stub
	return this.abnDao.getIndividualModifyForm(commonFilter,setStatus,pillarType);
}
@Override
public Workbook IndividualabnExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,String reportType)throws Exception {
	
	return abnDao.IndividualabnExportExcel(commonFilter,tblJSONObj,reportType);
}

}
