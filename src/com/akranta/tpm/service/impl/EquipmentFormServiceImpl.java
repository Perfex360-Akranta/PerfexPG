package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EquipmentBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlMachinemstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.GenTlMachinemstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.EntTlRoleEmpLink;
import com.akranta.tpm.model.GenTlCellmst;
import com.akranta.tpm.model.GenTlMachinemst;
import com.akranta.tpm.model.GenTlMachineskillmst;
import com.akranta.tpm.model.GenTlMchcirclelink;
import com.akranta.tpm.model.GenTlMchemplink;
import com.akranta.tpm.model.GenTlMchmaintteamlink;
import com.akranta.tpm.model.GenTlMchparameterlink;
import com.akranta.tpm.model.GenTlMchsubmchlink;
import com.akranta.tpm.service.EquipmentFormService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.service.api.MachineMasterServiceApi;


public class EquipmentFormServiceImpl implements EquipmentFormService {
	private CommonFilterDao commonFilterDao;
	private GenTlMachinemstDao equipmentDao;
	private Validations validations ;
	private MachineMasterServiceApi machinemasterserviceapi;
	
	public EquipmentFormServiceImpl(DBActionTemplate dbActionTemplate)
	{
		equipmentDao =  new GenTlMachinemstDaoImpl(dbActionTemplate);	
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	
	public void EquipmentFormServiceImplJwt(String JwtToken){
    	try{
    		equipmentDao.GenTlMachinemstDaoImplJwt(JwtToken);
    		machinemasterserviceapi = new MachineMasterServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	/*public void setGenTlEmployeemstDao(GenTlEmployeemstDao employeeDao)
	{
		this.employeeDao = employeeDao;
	}*/
	
	

	@Override
	public List<ComboBox> getEquipmentGroup(String condSql) throws Exception {
		
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("EQGM_CODE");
		comboFilter.setNameField("EQGM_NAME");
		comboFilter.setIdField("EQGM_KEYID");
		//comboFilter.setOrderByField("MCHM_MODIFIEDON desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EQPGROUPMST);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getWorkCenter(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("WKCM_CODE");
		comboFilter.setNameField("WKCM_NAME");
		comboFilter.setIdField("WKCM_KEYID");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_WORKCENTREMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getMachineRank(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("MCHM_MACHINERANK");
		comboFilter.setNameField("mchm_machinerank");
		comboFilter.setIdField("mchm_machinerank");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getCircle(String condSql) throws Exception {

		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("CRCM_CODE");
		comboFilter.setNameField("CRCM_NAME");
		comboFilter.setIdField("CRCM_KEYID");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_CIRCLEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getPurpose(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("PRPM_CODE");
		comboFilter.setNameField("PRPM_NAME");
		comboFilter.setIdField("PRPM_KEYID");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_PURPOSEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getCategory(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter(); 
		comboFilter.setCodeField("catm_code");
		comboFilter.setNameField("catm_name");
		comboFilter.setIdField("catm_keyid");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_CATEGORYMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getSubCategory(String category,ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("SBCM_CODE");
		comboFilter.setNameField("SBCM_NAME");
		comboFilter.setIdField("SBCM_KEYID");
		if( UIUtils.isValidKeyId(category) )
			comboFilter.setCondSql(" and SBCM_CATEGORYID ='"+category+"'");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBCATEGORYMST);
		
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getMachineName(String condSql) throws Exception {

		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("SBCM_CODE");
		comboFilter.setNameField("SBCM_NAME");
		comboFilter.setIdField("SBCM_KEYID");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBCATEGORYMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getJHstep(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("JHSM_CODE");
		comboFilter.setNameField("JHSM_NAME");
		comboFilter.setIdField("JHSM_KEYID");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_JHSTEPMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getMachineKeyId(String keyid) throws Exception {

		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("MCHM_MACHINENO");
		comboFilter.setNameField("MCHM_MACHINENAME");
		comboFilter.setIdField("MCHM_KEYID");
	//	comboFilter.setOrderByField("MCHM_MODIFIEDON");
		comboFilter.setCondSql("and mchm_cellid ='"+keyid+"'");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public GenTlMachinemst select(String keyid) throws Exception {
		CommonMessage.debugMsg("ServiceImpl:"+keyid);
		/*	GenTlMachinemst genTlMachinemst = new GenTlMachinemst();
		if(genTlMachinemst.getMchmAmcdate().equals(Constants.passNullDate))
			genTlMachinemst.setMchmAmcdate("");
		if(genTlMachinemst.getMchmEffectivedate().equals(Constants.passNullDate))
			genTlMachinemst.setMchmEffectivedate("");
		if(genTlMachinemst.getMchmManufactureddate().equals("01-Jan-1801 00:00:00"))
			genTlMachinemst.setMchmManufactureddate("");*/
		
		//return this.equipmentDao.getselect(keyid);
	return this.machinemasterserviceapi.getEquipmentMasterByIdAsObject(keyid);
	}

	public String createCircle(GenTlMchcirclelink newGenTlMchcirclelink,GenTlMchcirclelink existGenTlMchcirclelink, EquipmentBean equipmentBean)	throws Exception {
		
		try {		
			//fillValuesCircle(newGenTlMchcirclelink,existGenTlMchcirclelink,equipmentBean);	
			List<GenTlMchcirclelink> newGenTlMchcirclelinks = newGenTlMchcirclelink.getCirclegrid();
			List<GenTlMchcirclelink> newGenTlMchcirclelinksList = new ArrayList<GenTlMchcirclelink>();
			int index=0;
			for( GenTlMchcirclelink genTlMchcirclelink : newGenTlMchcirclelinks)
			{	CommonMessage.debugMsg("fill values for circle1......");
				
				GenTlMchcirclelink genTlMchcirclelink1 = (GenTlMchcirclelink)newGenTlMchcirclelink.getCirclegrid().get(index);
				genTlMchcirclelink.setMclkCreatedby(equipmentBean.getCreatedby());		
				genTlMchcirclelink.setMclkMachineid(equipmentBean.getMchId());
				genTlMchcirclelink.setMclkCircleid(genTlMchcirclelink1.getMclkCircleid());
				genTlMchcirclelink.setMclkActive("Y");
				
			String dateTime = CommonFunctions.dateTimeNow();		
						
			if(genTlMchcirclelink.getMclkKeyid() == null )			
			{	
				genTlMchcirclelink.setMclkCreatedon(dateTime);			
			}					
			else
			{	
				genTlMchcirclelink.setMclkCreatedon(existGenTlMchcirclelink.getMclkCreatedon());
			}
			CommonMessage.debugMsg("fill values for circle3......");
			genTlMchcirclelink.setMclkModifiedon(dateTime);
			
		
			if( genTlMchcirclelink.getMclkCircleid() == null )
				genTlMchcirclelink.setMclkCircleid("{}");
			
			if( genTlMchcirclelink.getMclkMachineid() == null )
				genTlMchcirclelink.setMclkMachineid("{}");			
			
			if( genTlMchcirclelink.getMclkCreatedby() == null )
				genTlMchcirclelink.setMclkCreatedby("{}");		
			
			newGenTlMchcirclelinksList.add(genTlMchcirclelink);
			index++;
			}
			CommonMessage.debugMsg("fill values for circle123......");
			String circle = equipmentDao.createCircle(newGenTlMchcirclelinksList);
			CommonMessage.debugMsg("circle....--"+circle);
			return circle;
		}catch (ValidationExceptions e){

			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
	

	public GenTlMachinemst create(GenTlMachinemst newgenTlMachinemst,
			GenTlMachinemst existGenTlMachinemst, EquipmentBean equipmentBean) throws ValidationExceptions,Exception {
		
		try {			
			String validationsFor;			
			validationsFor = "create";
			
			validations.validate(newgenTlMachinemst,"EquipmentCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			validations.validate(equipmentBean,"EquipmentCreation",validationsFor);
			
			fillValues(newgenTlMachinemst,existGenTlMachinemst,equipmentBean);		
			
			CommonMessage.debugMsg("Create Fill values Element ID "+newgenTlMachinemst.getGenTlFunctionallocn().getFnlnElementid());
			CommonMessage.debugMsg("Create Fill values Parent ID "+newgenTlMachinemst.getGenTlFunctionallocn().getFnlnParentid());
			//return equipmentDao.create(newgenTlMachinemst);
			return machinemasterserviceapi.saveMachineMaster(newgenTlMachinemst);
		
			
		}catch (ValidationExceptions e){

			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}


	public GenTlMachinemst update(GenTlMachinemst newgenTlMachinemst,
			GenTlMachinemst existGenTlMachinemst, EquipmentBean equipmentBean) throws ValidationExceptions,Exception {
	//	String validationsFor = "create";
		CommonMessage.debugMsg("Inside the ServiceImpl update");
		CommonMessage.debugMsg("CostTest:"+newgenTlMachinemst.getMchmCostcentreid());
		//Mano - for validation xml file
		validations.validate(newgenTlMachinemst,"EquipmentCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		validations.validate(equipmentBean,"EquipmentCreation","update");
		//CommonMessage.debugMsg("After");
		
		fillValues(newgenTlMachinemst,existGenTlMachinemst,equipmentBean);	
		CommonMessage.debugMsg("Aft Fill val");	
		String elementId = newgenTlMachinemst.getGenTlFunctionallocn().getFnlnElementid();
		elementId  = elementId.substring(0,elementId.lastIndexOf('-'));
		String parentId = newgenTlMachinemst.getGenTlFunctionallocn().getFnlnParentid();
		parentId  = parentId.substring(0,parentId.lastIndexOf('-'));
		CommonMessage.debugMsg("Update Fill values Element ID "+elementId);
		CommonMessage.debugMsg("Update Fill values Parent ID "+parentId);
		
		newgenTlMachinemst.getGenTlFunctionallocn().setFnlnElementid(elementId);
		newgenTlMachinemst.getGenTlFunctionallocn().setFnlnParentid(parentId);
		//return equipmentDao.update(newgenTlMachinemst);	
		return machinemasterserviceapi.saveMachineMaster(newgenTlMachinemst);
	}

	private List<GenTlMchcirclelink> fillValuesCircle(GenTlMchcirclelink newGenTlMchcirclelink,GenTlMchcirclelink existGenTlMchcirclelink, EquipmentBean equipmentBean) {
		CommonMessage.debugMsg("fill values for circle......");
		List<GenTlMchcirclelink> newGenTlMchcirclelinks = newGenTlMchcirclelink.getCirclegrid();
		List<GenTlMchcirclelink> newEntTlRoleEmpLinkList = new ArrayList<GenTlMchcirclelink>();
		int index=0;
		for( GenTlMchcirclelink genTlMchcirclelink : newGenTlMchcirclelinks)
		{	CommonMessage.debugMsg("fill values for circle1......");
			index++;
			GenTlMchcirclelink genTlMchcirclelink1 = (GenTlMchcirclelink)newGenTlMchcirclelink.getCirclegrid().get(index);
			
			CommonMessage.debugMsg("genTlMchcirclelink1.getMclkCircleid().........."+genTlMchcirclelink1.getMclkCircleid());
			genTlMchcirclelink.setMclkCircleid(genTlMchcirclelink1.getMclkCircleid());
			genTlMchcirclelink.setMclkActive("Y");
			CommonMessage.debugMsg("fill values for circle2......");
		String dateTime = CommonFunctions.dateTimeNow();		
					
		if(genTlMchcirclelink.getMclkKeyid() == null )			
		{	
			genTlMchcirclelink.setMclkCreatedon(dateTime);			
		}					
		else
		{	
			genTlMchcirclelink.setMclkCreatedon(existGenTlMchcirclelink.getMclkCreatedon());
		}
		CommonMessage.debugMsg("fill values for circle3......");
		genTlMchcirclelink.setMclkModifiedon(dateTime);
		
	
		if( genTlMchcirclelink.getMclkCircleid() == null )
			genTlMchcirclelink.setMclkCircleid("{}");
		
		if( genTlMchcirclelink.getMclkMachineid() == null )
			genTlMchcirclelink.setMclkMachineid("{}");			
		CommonMessage.debugMsg("fill values for circle4......");
		newEntTlRoleEmpLinkList.add(genTlMchcirclelink);
		}
		CommonMessage.debugMsg("fill values for circle123......");
		return newEntTlRoleEmpLinkList; 
	}
	private GenTlMachinemst fillValues(GenTlMachinemst newgenTlMachinemst,GenTlMachinemst oldGenTlMachinemst, EquipmentBean equipmentBean) throws Exception {
	
		
		
			newgenTlMachinemst.setMchmActive("Y");
			
		
			String dateTime = CommonFunctions.pg_dateTimeNow();		
						
			if(newgenTlMachinemst.getMchmKeyid() == null )			
			{	
				newgenTlMachinemst.setMchmCreatedon(dateTime);			
			}					
			else
			{	
				newgenTlMachinemst.setMchmCreatedon(oldGenTlMachinemst.getMchmCreatedon());
			}
			
			newgenTlMachinemst.setMchmModifiedon(dateTime);
			CommonMessage.debugMsg("element id m"+newgenTlMachinemst.getMchmElementid());
			CommonMessage.debugMsg("Data Identified...."+newgenTlMachinemst.getMchmIncludeforproduction());
			if(newgenTlMachinemst.getMchmElementid() == null)
				newgenTlMachinemst.setMchmElementid("{}");
			String date = newgenTlMachinemst.getMchmAmcdate();
			newgenTlMachinemst.setMchmAmcdate(CommonFunctions.pg_getDateTimeFromDate(date));
			
			
			if( newgenTlMachinemst.getMchmAmcdate() == null )
				newgenTlMachinemst.setMchmAmcdate(Constants.pgPassNullDateTime);
			
			if( newgenTlMachinemst.getMchmAmcremarks() == null )
				newgenTlMachinemst.setMchmAmcremarks("{}");		
			
			String date1 = newgenTlMachinemst.getMchmAmcrenewaldate();
			newgenTlMachinemst.setMchmAmcrenewaldate(CommonFunctions.pg_getDateTimeFromDate(date1));
			
			 
			if( newgenTlMachinemst.getMchmAmcrenewaldate() == null )
				newgenTlMachinemst.setMchmAmcrenewaldate(Constants.pgPassNullDateTime);
			
			if( newgenTlMachinemst.getMchmAmcvendor() == null )
				newgenTlMachinemst.setMchmAmcvendor("{}");
			
			if( newgenTlMachinemst.getMchmCategory() == null )
				newgenTlMachinemst.setMchmCategory("{}");
			
			if( newgenTlMachinemst.getMchmCellid() == null )
				newgenTlMachinemst.setMchmCellid("{}");
			
			if( newgenTlMachinemst.getMchmCircleid() == null )
				newgenTlMachinemst.setMchmCircleid("{}");			
			
			if( newgenTlMachinemst.getMchmConnectedload() == null )
				newgenTlMachinemst.setMchmConnectedload("{}");
			
			if( newgenTlMachinemst.getMchmControltype() == null )
				newgenTlMachinemst.setMchmControltype("{}");
			
			if( newgenTlMachinemst.getMchmCostcentreid() == null )
				newgenTlMachinemst.setMchmCostcentreid("{}");
			
			if( newgenTlMachinemst.getMchmCreatedby() == null )
				newgenTlMachinemst.setMchmCreatedby("{}");
			
			if( newgenTlMachinemst.getMchmCurrencyid() == null )
				newgenTlMachinemst.setMchmCurrencyid("{}");
			
			if( newgenTlMachinemst.getMchmDbno() == null )
				newgenTlMachinemst.setMchmDbno("{}");
			
			String date2 = newgenTlMachinemst.getMchmEffectivedate();
			newgenTlMachinemst.setMchmEffectivedate(CommonFunctions.pg_getDateTimeFromDate(date2));
			
			
			if( newgenTlMachinemst.getMchmEffectivedate() == null )
				newgenTlMachinemst.setMchmEffectivedate(Constants.pgPassNullDateTime);
			
			if( newgenTlMachinemst.getMchmEquipmentgroup() == null )
				newgenTlMachinemst.setMchmEquipmentgroup("{}");
			
			String date3 = newgenTlMachinemst.getMchmInactivateddate();
			newgenTlMachinemst.setMchmInactivateddate(CommonFunctions.pg_getDateTimeFromDate(date3));
			
			
			if( newgenTlMachinemst.getMchmInactivateddate() == null )
				newgenTlMachinemst.setMchmInactivateddate(Constants.pgPassNullDateTime);
			
			String date4 = newgenTlMachinemst.getMchmInstalleddate();
			newgenTlMachinemst.setMchmInstalleddate(CommonFunctions.pg_getDateTimeFromDate(date4));
			
			
			
			if( newgenTlMachinemst.getMchmInstalleddate() == null )
				newgenTlMachinemst.setMchmInstalleddate(Constants.pgPassNullDateTime);
			
			if( newgenTlMachinemst.getMchmIpfreq() == null )
				newgenTlMachinemst.setMchmIpfreq("{}");			
			
			if( newgenTlMachinemst.getMchmIpfreqmax() == null )
				newgenTlMachinemst.setMchmIpfreqmax("{}");
			
			if( newgenTlMachinemst.getMchmIpfreqmin() == null )
				newgenTlMachinemst.setMchmIpfreqmin("{}");
			
			if( newgenTlMachinemst.getMchmIpvolt() == null )
				newgenTlMachinemst.setMchmIpvolt("{}");			
			
			if( newgenTlMachinemst.getMchmIpvoltmax() == null )
				newgenTlMachinemst.setMchmIpvoltmax("{}");
			
			if( newgenTlMachinemst.getMchmIpvoltmin() == null )
				newgenTlMachinemst.setMchmIpvoltmin("{}");
			
			if( newgenTlMachinemst.getMchmIscavityormandrel() == null )
				newgenTlMachinemst.setMchmIscavityormandrel("M");			

			if( newgenTlMachinemst.getMchmIsunderamc() == null )
				newgenTlMachinemst.setMchmIsunderamc("Y");			
			
			if( newgenTlMachinemst.getMchmIsunderwarranty() == null )
				newgenTlMachinemst.setMchmIsunderwarranty("Y");
			
			if( newgenTlMachinemst.getMchmJhstep() == null )
				newgenTlMachinemst.setMchmJhstep("{}");		
			
			String date5 = newgenTlMachinemst.getMchmJhstepdate();
			newgenTlMachinemst.setMchmJhstepdate(CommonFunctions.pg_getDateTimeFromDate(date5));
			
			
			if( newgenTlMachinemst.getMchmJhstepdate() == null )
				newgenTlMachinemst.setMchmJhstepdate(Constants.pgPassNullDateTime);
			
			if( newgenTlMachinemst.getMchmMachinename() == null )
				newgenTlMachinemst.setMchmMachinename("{}");			
			
			if( newgenTlMachinemst.getMchmMachineno() == null )
				newgenTlMachinemst.setMchmMachineno("{}");
			
			if( newgenTlMachinemst.getMchmMachineorder() == null )
				newgenTlMachinemst.setMchmMachineorder("0");
			
			if( newgenTlMachinemst.getMchmMachinerank() == null )
				newgenTlMachinemst.setMchmMachinerank("{}");
			
			if( newgenTlMachinemst.getMchmMake() == null )
				newgenTlMachinemst.setMchmMake("{}");
			
			String date6 = newgenTlMachinemst.getMchmManufactureddate();
			newgenTlMachinemst.setMchmManufactureddate(CommonFunctions.pg_getDateTimeFromDate(date6));
			
			
			if( newgenTlMachinemst.getMchmManufactureddate() == null )
				newgenTlMachinemst.setMchmManufactureddate(Constants.pgPassNullDateTime);			
			
			if( newgenTlMachinemst.getMchmManufacturerid() == null )
				newgenTlMachinemst.setMchmManufacturerid("{}");
			
			if( newgenTlMachinemst.getMchmMaxmeterreading() == null )
				newgenTlMachinemst.setMchmMaxmeterreading("{}");
			
			if( newgenTlMachinemst.getMchmMfrremarks() == null )
				newgenTlMachinemst.setMchmMfrremarks("{}");			
			
			if( newgenTlMachinemst.getMchmMfrslno() == null )
				newgenTlMachinemst.setMchmMfrslno("{}");
			
			if( newgenTlMachinemst.getMchmModel() == null )
				newgenTlMachinemst.setMchmModel("{}");
			
			if( newgenTlMachinemst.getMchmPhase() == null )
				newgenTlMachinemst.setMchmPhase("{}");	
			
			String date7 = newgenTlMachinemst.getMchmPodate();
			newgenTlMachinemst.setMchmPodate(CommonFunctions.pg_getDateTimeFromDate(date7));
			

			if( newgenTlMachinemst.getMchmPodate() == null )
				newgenTlMachinemst.setMchmPodate(Constants.pgPassNullDateTime);
			
			if( newgenTlMachinemst.getMchmPono() == null )
				newgenTlMachinemst.setMchmPono("{}");
			
			if( newgenTlMachinemst.getMchmPowersupply() == null )
				newgenTlMachinemst.setMchmPowersupply("{}");
			
			String date8 = newgenTlMachinemst.getMchmPurchasedate();
			newgenTlMachinemst.setMchmPurchasedate(CommonFunctions.pg_getDateTimeFromDate(date8));
			
			
			if( newgenTlMachinemst.getMchmPurchasedate() == null )
				newgenTlMachinemst.setMchmPurchasedate(Constants.pgPassNullDateTime);					
			
			if( newgenTlMachinemst.getMchmPurchaseprice() == null )
				newgenTlMachinemst.setMchmPurchaseprice("0");
			
			if( newgenTlMachinemst.getMchmPurpose() == null )
				newgenTlMachinemst.setMchmPurpose("{}");			

			if( newgenTlMachinemst.getMchmRemarks() == null )
				newgenTlMachinemst.setMchmRemarks("{}");
			
			if( newgenTlMachinemst.getMchmSbno() == null )
				newgenTlMachinemst.setMchmSbno("{}");
			
			if( newgenTlMachinemst.getMchmSpecification() == null )
				newgenTlMachinemst.setMchmSpecification("{}");	
			
			if( newgenTlMachinemst.getMchmSubcategory() == null )
				newgenTlMachinemst.setMchmSubcategory("{}");			
			
			if( newgenTlMachinemst.getMchmSubcellid() == null )
				newgenTlMachinemst.setMchmSubcellid("{}");
			
			if( newgenTlMachinemst.getMchmSupplierid() == null )
				newgenTlMachinemst.setMchmSupplierid("{}");			

			if( newgenTlMachinemst.getMchmSupplierremarks() == null )
				newgenTlMachinemst.setMchmSupplierremarks("{}");
			
			if( newgenTlMachinemst.getMchmTechnicalid() == null )
				newgenTlMachinemst.setMchmTechnicalid("{}");
			
			if( newgenTlMachinemst.getMchmType() == null )
				newgenTlMachinemst.setMchmType("MCH");	
			
			if( newgenTlMachinemst.getMchmTradeid() == null )
				newgenTlMachinemst.setMchmTradeid("{}");			
			if( newgenTlMachinemst.getMchmTempfield3()== null )
				newgenTlMachinemst.setMchmTempfield3("{}");
			if( newgenTlMachinemst.getMchmTempfield4()== null )
				newgenTlMachinemst.setMchmTempfield4("{}");
			if( newgenTlMachinemst.getMchmTempfield5()== null )
				newgenTlMachinemst.setMchmTempfield5("{}");
//			if( newgenTlMachinemst.getMchmFunctionalLocn()== null )
//				newgenTlMachinemst.setMchmFunctionalLocn("{}");
//			
//			if( newgenTlMachinemst.getMchmPlannerGroup()== null )
//				newgenTlMachinemst.setMchmPlannerGroup("{}");			
//
//			if( newgenTlMachinemst.getMchmWbsElement()== null )
//				newgenTlMachinemst.setMchmWbsElement("{}");
//			
//			if( newgenTlMachinemst.getMchmPurchaseOrg()== null )
//				newgenTlMachinemst.setMchmPurchaseOrg("{}");
//			
//			if( newgenTlMachinemst.getMchmMaintPlant()== null )
//				newgenTlMachinemst.setMchmMaintPlant("{}");
//			
//			if( newgenTlMachinemst.getMchmPlanningPlant()== null )
//				newgenTlMachinemst.setMchmPlanningPlant("{}");
			String date9 = newgenTlMachinemst.getMchmWarrantydate();
			newgenTlMachinemst.setMchmWarrantydate(CommonFunctions.pg_getDateTimeFromDate(date9));
			
						
			if( newgenTlMachinemst.getMchmWarrantydate() == null )
				newgenTlMachinemst.setMchmWarrantydate(Constants.pgPassNullDateTime);
			
			if( newgenTlMachinemst.getMchmWires() == null )
				newgenTlMachinemst.setMchmWires("{}");	
			
			if( newgenTlMachinemst.getMchmWorkcenter() == null )
				newgenTlMachinemst.setMchmWorkcenter("{}");		
			
			if(newgenTlMachinemst.getMchmIncludeforproduction() == null)
				newgenTlMachinemst.setMchmIncludeforproduction("N");
			
			if(newgenTlMachinemst.getMchmGivesfinaloutput() == null)
				newgenTlMachinemst.setMchmGivesfinaloutput("N");
			
				
			if(newgenTlMachinemst.getOperatorgrid() != null && newgenTlMachinemst.getOperatorgrid().size() > 0  )
			{
				CommonMessage.debugMsg("Operator");
				newgenTlMachinemst.setOperatorgrid(operatorFillValues(newgenTlMachinemst,oldGenTlMachinemst,equipmentBean));
			}
			
			if(newgenTlMachinemst.getMaintainceGrid() !=null && newgenTlMachinemst.getMaintainceGrid().size()>0)
			{
				CommonMessage.debugMsg("Maintaince");
				newgenTlMachinemst.setMaintainceGrid(maintainceFillValues(newgenTlMachinemst,oldGenTlMachinemst,equipmentBean));
			}
		
			if(newgenTlMachinemst.getOperatorSkillGrid() != null && newgenTlMachinemst.getOperatorSkillGrid().size()>0)
			{
				newgenTlMachinemst.setOperatorSkillGrid(OperatorSkillFillValues(newgenTlMachinemst,oldGenTlMachinemst,equipmentBean));
			}
			
			if(newgenTlMachinemst.getMaintainceSkillGrid() != null && newgenTlMachinemst.getMaintainceSkillGrid().size()>0)
			{
				newgenTlMachinemst.setMaintainceSkillGrid(MaintainceSkillFillValues(newgenTlMachinemst,oldGenTlMachinemst,equipmentBean));
			}
		
			if(newgenTlMachinemst.getEquipmentParameterGrid() != null && newgenTlMachinemst.getEquipmentParameterGrid().size() != 0  )
			{
				CommonMessage.debugMsg("Equipment Parameter");
				newgenTlMachinemst.setEquipmentParameterGrid(EquipmentParameterFillValues(newgenTlMachinemst,oldGenTlMachinemst,equipmentBean));
			}
			
			if(newgenTlMachinemst.getSubEquipmentGrid() != null  && newgenTlMachinemst.getSubEquipmentGrid().size()>0)
			{
				CommonMessage.debugMsg("Sub Equipment");
				newgenTlMachinemst.setSubEquipmentGrid(SubEquipmentFillValues(newgenTlMachinemst,oldGenTlMachinemst,equipmentBean));
			}
			//CommonMessage.debugMsg("SubEquipment");
			fillFunctionLoc(newgenTlMachinemst,equipmentBean);
			return newgenTlMachinemst; 
			
		}
	
	
	private GenTlFunctionallocn fillFunctionLoc(GenTlMachinemst newgenTlMachinemst,EquipmentBean equipmentBean) throws Exception{

		GenTlFunctionallocn  newGenTlFunctionallocn = new GenTlFunctionallocn();
		
		newGenTlFunctionallocn.setFnlnOriginalid(newgenTlMachinemst.getMchmKeyid());
		
		
		CommonMessage.debugMsg(equipmentBean.getFormMode()+"Mode");
		CommonMessage.debugMsg(equipmentBean.getFormActionMode()+"ActionMode");
		//Get Element Id By machine Id Mano
		String elementId = "";
		if (equipmentBean.getFormMode() == FormModes.modify) {

			String machineId = newgenTlMachinemst.getMchmKeyid();
			elementId = equipmentDao.getElementByMachineId(machineId);
			
			
			CommonMessage.debugMsg("Entered ");
			CommonMessage.debugMsg("Entered "+elementId);
			newGenTlFunctionallocn.setFnlnElementid(elementId);
			newGenTlFunctionallocn.setFnlnParentid(elementId);
			
			CommonMessage.debugMsg(newGenTlFunctionallocn.getFnlnElementid()+"inside if");
			
		}
		
		else 
		{
			newGenTlFunctionallocn.setFnlnElementid(newgenTlMachinemst.getGenTlFunctionallocn().getFnlnElementid());
			newGenTlFunctionallocn.setFnlnParentid(newgenTlMachinemst.getGenTlFunctionallocn().getFnlnElementid());
		}
		
		CommonMessage.debugMsg("manoelement"+elementId);
		
		
		
		newGenTlFunctionallocn.setFnlnElementtype("M");
		
		CommonMessage.debugMsg(newGenTlFunctionallocn.getFnlnElementid()+"outside  if");
		
		
		newGenTlFunctionallocn.setFnlnDisplaycode(newgenTlMachinemst.getMchmMachineno()+"-"+newgenTlMachinemst.getMchmMachinename());
		newGenTlFunctionallocn.setFnlnDescription(newgenTlMachinemst.getMchmMachineno()+"-"+newgenTlMachinemst.getMchmMachinename());
		newGenTlFunctionallocn.setFnlnActive("Y");
		newGenTlFunctionallocn.setFnlnKeyid("{}");
		
		newgenTlMachinemst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		
		return newGenTlFunctionallocn;
	}
	
	
	
	
	
private List<GenTlMchsubmchlink> SubEquipmentFillValues(GenTlMachinemst newgenTlMachinemst,GenTlMachinemst oldGenTlMachinemst, EquipmentBean equipmentBean) {
		
CommonMessage.debugMsg("Inside Sub Equipment Fill Values");
	
	String dateTime = CommonFunctions.pg_dateTimeNow();
	List<GenTlMchsubmchlink> newGenTlMchsubmchlink = newgenTlMachinemst.getSubEquipmentGrid();
	List<GenTlMchsubmchlink> oldgenTlMchsubmchlink = null;
	GenTlMchsubmchlink oldgenTlMchsubmchlinkValues  = null;
	
	if( oldGenTlMachinemst != null){		
		oldgenTlMchsubmchlink = newgenTlMachinemst.getSubEquipmentGrid();
		CommonMessage.debugMsg("get(0) "+oldgenTlMchsubmchlink.get(0));
		if( oldgenTlMchsubmchlink != null && oldgenTlMchsubmchlink.size() > 0 )
		{
			oldgenTlMchsubmchlinkValues = oldgenTlMchsubmchlink.get(0);				
		}
	}
	
	List<GenTlMchsubmchlink> newgenTlMchsubmchlinkList = new ArrayList<GenTlMchsubmchlink>();
	for( GenTlMchsubmchlink genTlMchsubmchlink :newGenTlMchsubmchlink)
	{			
		CommonMessage.debugMsg(" sub Equipment Parameter");
		if(genTlMchsubmchlink.getScmlCellid() == null )			
		{				
			genTlMchsubmchlink.setScmlCellid(newgenTlMachinemst.getMchmCellid());
		}	
		else{
			genTlMchsubmchlink.setScmlCreatedon(dateTime);
		}
		
		genTlMchsubmchlink.setScmlCreatedon(dateTime);
		
		if( genTlMchsubmchlink.getScmlChildmchid() == null )			
			genTlMchsubmchlink.setScmlChildmchid("{}");
		
		if( genTlMchsubmchlink.getScmlParentmchid() == null )				
			genTlMchsubmchlink.setScmlParentmchid(newgenTlMachinemst.getMchmKeyid());
		
		if( genTlMchsubmchlink.getScmlSectionid() == null )			
			genTlMchsubmchlink.setScmlSectionid(equipmentBean.getSection());
					
		CommonMessage.debugMsg("Section value Service:"+equipmentBean.getSection());
		
		newgenTlMchsubmchlinkList.add(genTlMchsubmchlink);
		CommonMessage.debugMsg("test:"+genTlMchsubmchlink.getScmlCellid());
	}
	CommonMessage.debugMsg("Sub Equipment Fill values:---:"+newgenTlMchsubmchlinkList.size());			
	
	return newgenTlMchsubmchlinkList;
	
	}

private List<GenTlMchparameterlink> EquipmentParameterFillValues(GenTlMachinemst newgenTlMachinemst,GenTlMachinemst oldGenTlMachinemst, EquipmentBean equipmentBean) {
		

	String dateTime = CommonFunctions.pg_dateTimeNow();
	List<GenTlMchparameterlink> newgenTlMchparameterlink = newgenTlMachinemst.getEquipmentParameterGrid();
	 
	List<GenTlMchparameterlink> newgenTlMchparameterlinkList = new ArrayList<GenTlMchparameterlink>();
	
	for(GenTlMchparameterlink genTlMchparameterlink:newgenTlMchparameterlink)
	{	
		if( ! UIUtils.isValidKeyId(genTlMchparameterlink.getMplkDescription()) && ! UIUtils.isValidKeyId(genTlMchparameterlink.getMplkTempfield1()) )
			continue;
		
		if(genTlMchparameterlink.getMplkKeyid() == null )			
		{				
			genTlMchparameterlink.setMplkKeyid(dateTime);
		}	
		else{
			genTlMchparameterlink.setMplkCreatedon(dateTime);
		}

		genTlMchparameterlink.setMplkModifiedon(dateTime);
		genTlMchparameterlink.setMplkActive("Y");
		
		genTlMchparameterlink.setMplkCreatedby(newgenTlMachinemst.getMchmCreatedby());
		
		if( genTlMchparameterlink.getMplkDate() == null )			
			genTlMchparameterlink.setMplkDate(Constants.pgPassNullDateTime);
		
		if( genTlMchparameterlink.getMplkDescription() != null )
		{
			genTlMchparameterlink.setMplkDescription(genTlMchparameterlink.getMplkDescription());
		}
		else
			genTlMchparameterlink.setMplkDescription("{}");

		if( genTlMchparameterlink.getMplkTempfield1() != null)
			genTlMchparameterlink.setMplkDescription(genTlMchparameterlink.getMplkTempfield1());
		
		if( genTlMchparameterlink.getMplkParameterid() == null )			
			genTlMchparameterlink.setMplkParameterid("{}");
					
				
		genTlMchparameterlink.setMplkTempfield1("{}");
		genTlMchparameterlink.setMplkTempfield2("{}");	
		
		genTlMchparameterlink.setMplkCreatedon(Constants.pgPassNullDateTime);
		
		newgenTlMchparameterlinkList.add(genTlMchparameterlink);

	}
	
	return newgenTlMchparameterlinkList;
	
	}

private List<GenTlMachineskillmst> MaintainceSkillFillValues(GenTlMachinemst newMaintainceSkill,GenTlMachinemst oldMaintainceSkill, EquipmentBean equipmentBean) {
		
		/*CommonMessage.debugMsg("Maintaince Skill Fill Values");
		
		String dateTime = CommonFunctions.dateTimeNow();
		List<GenTlMachineskillmst> maintgenTlMachineskillmst = newgenTlMachinemst.getMaintainceSkillGrid();
		List<GenTlMachineskillmst> maintoldgenTlMachineskillmst = null;
		GenTlMachineskillmst maintoldgenTlMachineskillmstValues  = null;
		
		if( oldGenTlMachinemst != null){		
			maintoldgenTlMachineskillmst = oldGenTlMachinemst.getMaintainceSkillGrid();
			CommonMessage.debugMsg("get(0) "+maintoldgenTlMachineskillmst.get(0));
			if( maintoldgenTlMachineskillmst != null && maintoldgenTlMachineskillmst.size() > 0 )
			{
				maintoldgenTlMachineskillmstValues = maintoldgenTlMachineskillmst.get(0);				
			}
		}
		
		List<GenTlMachineskillmst> newgenTlMachineskillmstMaintList = new ArrayList<GenTlMachineskillmst>();
		for( GenTlMachineskillmst maintence :maintgenTlMachineskillmst)
		{			
			CommonMessage.debugMsg("test");
			if(maintence.getMskmMachineid() == null )			
			{				
				maintence.setMskmMachineid(dateTime);
			}	
			else{
				maintence.setMskmCreatedon(dateTime);
			}
			
			maintence.setMskmModifiedon(dateTime);
			maintence.setMskmActive("Y");
			maintence.setMskmCreatedby(newgenTlMachinemst.getMchmCreatedby());
							
			
			if( maintence.getMskmMachineid() == null )			
				maintence.setMskmMachineid("{}");
			
			if( maintence.getMskmSkilldescription() == null )				
				maintence.setMskmSkilldescription("{}");
			
			if( maintence.getMskmSkillfordepartment() == null )			
				maintence.setMskmSkillfordepartment("M");
						
			if( maintence.getMskmTempfield1() == null )			
				maintence.setMskmTempfield1("{}");
			
			if( maintence.getMskmTempfield2() == null )			
				maintence.setMskmTempfield2("{}");	
			
			if(maintence.getMskmCreatedon()==null)
				maintence.setMskmCreatedon(Constants.passNullDate);
			
			newgenTlMachineskillmstMaintList.add(maintence);
			CommonMessage.debugMsg("test:"+maintence.getMskmMachineid());
		}
		CommonMessage.debugMsg("Maintaince Fill values:"+newgenTlMachineskillmstMaintList.size());			
		
		return newgenTlMachineskillmstMaintList;*/	
	
	CommonMessage.debugMsg("Maintaince Skill Fill Values");
	
	String dateTime = CommonFunctions.pg_dateTimeNow();
	CommonMessage.debugMsg("dateTime:"+dateTime);
	List<GenTlMachineskillmst> newMaintainceskillmst = newMaintainceSkill.getMaintainceSkillGrid();	
	CommonMessage.debugMsg("test maintaince skill data");
	List<GenTlMachineskillmst> oldMaintainceskillmst = null;
	GenTlMachineskillmst oldMaintainceskillmstValues  = null;
	
	if( newMaintainceSkill != null){	
		CommonMessage.debugMsg("old id not emplty");
		oldMaintainceskillmst = newMaintainceSkill.getMaintainceSkillGrid();
		CommonMessage.debugMsg(oldMaintainceskillmst);
		CommonMessage.debugMsg("get(0) "+oldMaintainceskillmst.size());
		
		if( oldMaintainceskillmst != null && oldMaintainceskillmst.size() > 0 )
		{
			oldMaintainceskillmstValues = oldMaintainceskillmst.get(0);	
			CommonMessage.debugMsg("if end");
		}
	}
	
	List<GenTlMachineskillmst> newMaintaincemstList = new ArrayList<GenTlMachineskillmst>();
	for( GenTlMachineskillmst genMaintainceskillmst :newMaintainceskillmst)
	{			
		CommonMessage.debugMsg("test");
		if(genMaintainceskillmst.getMskmMachineid() == null )			
		{				
			genMaintainceskillmst.setMskmMachineid(dateTime);
		}	
		else{
			genMaintainceskillmst.setMskmCreatedon(dateTime);
		}
		
		genMaintainceskillmst.setMskmModifiedon(dateTime);
		genMaintainceskillmst.setMskmActive("Y");
		genMaintainceskillmst.setMskmCreatedby(newMaintainceSkill.getMchmCreatedby());
							
		
		if( genMaintainceskillmst.getMskmMachineid() == null )			
			genMaintainceskillmst.setMskmMachineid("{}");
		
		if( genMaintainceskillmst.getMskmSkilldescription() == null )				
			genMaintainceskillmst.setMskmSkilldescription("{}");
		
		if( genMaintainceskillmst.getMskmSkillfordepartment() == null )			
			genMaintainceskillmst.setMskmSkillfordepartment("M");
					
		if( genMaintainceskillmst.getMskmTempfield1() == null )			
			genMaintainceskillmst.setMskmTempfield1("{}");
		
		if( genMaintainceskillmst.getMskmTempfield2() == null )			
			genMaintainceskillmst.setMskmTempfield2("{}");	
		
		if(genMaintainceskillmst.getMskmCreatedon()==null)
			genMaintainceskillmst.setMskmCreatedon(Constants.pgPassNullDateTime);
		
		newMaintaincemstList.add(genMaintainceskillmst);
		CommonMessage.debugMsg("test:"+genMaintainceskillmst.getMskmMachineid());
	}
	CommonMessage.debugMsg("Maintaince Skill Fill values:---:"+newMaintaincemstList.size());			
	
	return newMaintaincemstList;
		
	}


	private List<GenTlMachineskillmst> OperatorSkillFillValues(GenTlMachinemst newgenTlMachinemst,GenTlMachinemst oldGenTlMachinemst, EquipmentBean equipmentBean) {
		
		CommonMessage.debugMsg("OperatorSkill Fill Values");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMachineskillmst> newgenTlMachineskillmst = newgenTlMachinemst.getOperatorSkillGrid();
		List<GenTlMachineskillmst> oldgenTlMachineskillmst = null;
		GenTlMachineskillmst oldgenTlMachineskillmstValues  = null;
		
		if( oldGenTlMachinemst != null){		
			oldgenTlMachineskillmst = newgenTlMachinemst.getOperatorSkillGrid();
			CommonMessage.debugMsg("get(0) "+oldgenTlMachineskillmst.get(0));
			if( oldgenTlMachineskillmst != null && oldgenTlMachineskillmst.size() > 0 )
			{
				oldgenTlMachineskillmstValues = oldgenTlMachineskillmst.get(0);				
			}
		}
		
		List<GenTlMachineskillmst> newgenTlMachineskillmstList = new ArrayList<GenTlMachineskillmst>();
		for( GenTlMachineskillmst genTlMachineskillmst :newgenTlMachineskillmst)
		{			
			CommonMessage.debugMsg("test");
			if(genTlMachineskillmst.getMskmMachineid() == null )			
			{				
				genTlMachineskillmst.setMskmMachineid(dateTime);
			}	
			else{
				genTlMachineskillmst.setMskmCreatedon(dateTime);
			}
			
			genTlMachineskillmst.setMskmModifiedon(dateTime);
			genTlMachineskillmst.setMskmActive("Y");
			genTlMachineskillmst.setMskmCreatedby(newgenTlMachinemst.getMchmCreatedby());
								
			
			if( genTlMachineskillmst.getMskmMachineid() == null )			
				genTlMachineskillmst.setMskmMachineid("{}");
			
			if( genTlMachineskillmst.getMskmSkilldescription() == null )				
				genTlMachineskillmst.setMskmSkilldescription("{}");
			
			if( genTlMachineskillmst.getMskmSkillfordepartment() == null )			
				genTlMachineskillmst.setMskmSkillfordepartment("O");
						
			if( genTlMachineskillmst.getMskmTempfield1() == null )			
				genTlMachineskillmst.setMskmTempfield1("{}");
			
			if( genTlMachineskillmst.getMskmTempfield2() == null )			
				genTlMachineskillmst.setMskmTempfield2("{}");	
			
			if(genTlMachineskillmst.getMskmCreatedon()==null)
				genTlMachineskillmst.setMskmCreatedon(Constants.pgPassNullDateTime);
			
			newgenTlMachineskillmstList.add(genTlMachineskillmst);
			CommonMessage.debugMsg("test:"+genTlMachineskillmst.getMskmMachineid());
		}
		CommonMessage.debugMsg("operator Fill values:---:"+newgenTlMachineskillmstList.size());			
		
		return newgenTlMachineskillmstList;
		
		
	}
	
	private List<GenTlMchmaintteamlink> maintainceFillValues(GenTlMachinemst newgenTlMachinemst,GenTlMachinemst oldGenTlMachinemst, EquipmentBean equipmentBean) {
		
		
CommonMessage.debugMsg("Maintaince grid Fill Values");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMchmaintteamlink> newgenTlMchmaintteamlink = newgenTlMachinemst.getMaintainceGrid();
		List<GenTlMchmaintteamlink> oldgenTlMchmaintteamlink = null;
		GenTlMchmaintteamlink oldgenTlMchmaintteamlinkValues  = null;
		
		if( oldGenTlMachinemst != null){		
			oldgenTlMchmaintteamlink = newgenTlMachinemst.getMaintainceGrid();
			CommonMessage.debugMsg("get(0) "+oldgenTlMchmaintteamlink.get(0));
			if( oldgenTlMchmaintteamlink != null && oldgenTlMchmaintteamlink.size() > 0 )
			{
				oldgenTlMchmaintteamlinkValues = oldgenTlMchmaintteamlink.get(0);				
			}
		}
		
		List<GenTlMchmaintteamlink> newgenTlMchmaintteamlinkList = new ArrayList<GenTlMchmaintteamlink>();
		for( GenTlMchmaintteamlink genTlMchmaintteamlink :newgenTlMchmaintteamlink)
		{			
			CommonMessage.debugMsg("test");
			if(genTlMchmaintteamlink.getMcmtMachineid() == null )			
			{				
				genTlMchmaintteamlink.setMcmtMachineid(dateTime);
			}	
			else{
				genTlMchmaintteamlink.setMcmtCreatedon(dateTime);
			}
			
			genTlMchmaintteamlink.setMcmtModifiedon(dateTime);
			genTlMchmaintteamlink.setMcmtActive("Y");
			genTlMchmaintteamlink.setMcmtCreatedby(newgenTlMachinemst.getMchmCreatedby());
								
			
			if( genTlMchmaintteamlink.getMcmtMachineid() == null )			
				genTlMchmaintteamlink.setMcmtMachineid("{}");
			
			if( genTlMchmaintteamlink.getMcmtMaintenanceteamid() == null )				
				genTlMchmaintteamlink.setMcmtMaintenanceteamid("{}");
		
						
			if( genTlMchmaintteamlink.getMcmtTempfield1() == null )			
				genTlMchmaintteamlink.setMcmtTempfield1("{}");
			
			if( genTlMchmaintteamlink.getMcmtTempfield2() == null )			
				genTlMchmaintteamlink.setMcmtTempfield2("{}");	
			
			if(genTlMchmaintteamlink.getMcmtCreatedon()==null)
				genTlMchmaintteamlink.setMcmtCreatedon(Constants.pgPassNullDateTime);
			
			newgenTlMchmaintteamlinkList.add(genTlMchmaintteamlink);
			CommonMessage.debugMsg("test:"+genTlMchmaintteamlink.getMcmtMachineid());
		}
		CommonMessage.debugMsg("Maintaince grid Fill values:"+newgenTlMchmaintteamlinkList.size());			
		
		return newgenTlMchmaintteamlinkList;
		
	}

	private List<GenTlMchemplink> operatorFillValues(GenTlMachinemst newgenTlMachinemst,GenTlMachinemst oldGenTlMachinemst, EquipmentBean equipmentBean) {
		CommonMessage.debugMsg("operator Fill Values");		
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMchemplink> newGenTlMchemplink = newgenTlMachinemst.getOperatorgrid();
		List<GenTlMchemplink> oldGenTlMchemplink = null;
		GenTlMchemplink oldGenTlMchemplinkValues  = null;
		CommonMessage.debugMsg("Operator grid value:"+newgenTlMachinemst.getOperatorgrid());
		
		if( oldGenTlMachinemst != null){	
			
			CommonMessage.debugMsg("Test:"+newgenTlMachinemst.getOperatorgrid());
			oldGenTlMchemplink = newgenTlMachinemst.getOperatorgrid();
			CommonMessage.debugMsg(oldGenTlMchemplink);
			CommonMessage.debugMsg("get(0) "+oldGenTlMchemplink.get(0));
			if( oldGenTlMchemplink != null && oldGenTlMchemplink.size() > 0 )
			{
				oldGenTlMchemplinkValues = oldGenTlMchemplink.get(0);
				
			}
		}
		List<GenTlMchemplink> newGenTlMchemplinkList = new ArrayList<GenTlMchemplink>();
		for( GenTlMchemplink genTlMchemplink :newGenTlMchemplink)
		{			
			CommonMessage.debugMsg("test");
			if(genTlMchemplink.getMcemMachineid() == null )			
			{				
				genTlMchemplink.setMcemMachineid(dateTime);
			}	
			else{
				genTlMchemplink.setMcemCreatedon(dateTime);
			}
			
			genTlMchemplink.setMcemModifiedon(dateTime);
			genTlMchemplink.setMcemActive("Y");
			genTlMchemplink.setMcemCreatedby(newgenTlMachinemst.getMchmCreatedby());
			genTlMchemplink.setMcemModifiedon(dateTime);		
			
			
			if( genTlMchemplink.getMcemEmployeeid() == null )				
				genTlMchemplink.setMcemEmployeeid("{}");				
			
			if( genTlMchemplink.getMcemMachineid() == null )			
				genTlMchemplink.setMcemMachineid("{}");
			
			if( genTlMchemplink.getMcemTempfield1() == null )				
				genTlMchemplink.setMcemTempfield1("{}");
			
			if( genTlMchemplink.getMcemTempfield2() == null )			
				genTlMchemplink.setMcemTempfield2("{}");	
			
			if(genTlMchemplink.getMcemCreatedon()==null)
				genTlMchemplink.setMcemCreatedon(Constants.pgPassNullDateTime);
			
			newGenTlMchemplinkList.add(genTlMchemplink);
			CommonMessage.debugMsg("test:"+genTlMchemplink.getMcemEmployeeid());
		}
		CommonMessage.debugMsg("Operator Fill values:"+newGenTlMchemplinkList.size());
		
		return newGenTlMchemplinkList;
	
		
	}
	@Override
	public List<ComboBox> getPowerSupply(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("MCHM_MACHINENO");
		//mano
		comboFilter.setNameField("mchm_powersupply");
		comboFilter.setIdField("mchm_powersupply");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getConnectedLoad(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("MCHM_MACHINENO");
		//mano
		comboFilter.setNameField("mchm_connectedload");
		comboFilter.setIdField("mchm_connectedload");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}
	
	public List<ComboBox> getCostCentre(String costId) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("MCHM_MACHINENO");
		comboFilter.setNameField("CELL_NAME");
		comboFilter.setIdField("CELL_KEYID");
		comboFilter.setCondSql("and  cell_costcentreid='"+costId+"'");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_CELLMST);
		CommonMessage.debugMsg("testttttt:"+comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
	}


	@Override
	public List<ComboBox> getDBNo(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("MCHM_MACHINENO");
		//mano
		comboFilter.setNameField("mchm_dbno");
		comboFilter.setIdField("mchm_dbno");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getSBNo(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
	//	comboFilter.setCodeField("MCHM_MACHINENO");
		//mano
		comboFilter.setNameField("mchm_sbno");
		comboFilter.setIdField("mchm_sbno");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getManufacture(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("MCHM_MACHINENO");
		comboFilter.setNameField("MCHM_MANUFACTURERID");
		comboFilter.setIdField("MCHM_MANUFACTURERID");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getMake(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("MCHM_MACHINENO");
		//mano
		comboFilter.setNameField("mchm_make");
		comboFilter.setIdField("mchm_make");
		//comboFilter.setOrderByField("MCHM_MODIFIEDON desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getModel(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("MCHM_MACHINENO");
		comboFilter.setNameField("MCHM_MODEL");
		comboFilter.setIdField("MCHM_MODEL");
		//comboFilter.setOrderByField("MCHM_MODIFIEDON desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getSupplier(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("supm_code");
		comboFilter.setNameField("supm_name1");
		comboFilter.setIdField("supm_keyid");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SUPPLIERMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getUnit(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("JHSM_CODE");
		comboFilter.setNameField("CURM_NAME");
		comboFilter.setIdField("CURM_KEYID");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_CURRENCYMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getProvider(ComboFilter comboFilter) throws Exception {

		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("AMVM_CODE");
		comboFilter.setNameField("AMVM_NAME");
		comboFilter.setIdField("AMVM_KEYID");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_AMCVENDORMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public GenTlMachinemst delete(String delMode, GenTlMachinemst newGenTlMachinemst) throws ValidationExceptions, Exception {

		CommonMessage.debugMsg("inside service impl "+delMode);
		return equipmentDao.delete(delMode, newGenTlMachinemst);
		
	}

	@Override
	public List<String[]> getAllOperator(String eqpId) throws Exception {
		return this.equipmentDao.getOperator(eqpId);
	}

	@Override
	public List<String[]> getAllOperatorData(String factId) throws Exception {

		//return this.equipmentDao.getOperatorData(factId);
		return this.machinemasterserviceapi.getOperatorData(factId);
		
	}

	@Override
	public List<String[]> getAllSkillData() throws Exception {

		//return this.equipmentDao.getSkillData();
		
		return this.machinemasterserviceapi.getOperatorSkillData();
	}

	@Override
	public List<String[]> getAllMaintainceData() throws Exception {

		//return this.equipmentDao.getMaintainceData();
		return this.machinemasterserviceapi.getMaintenanceTeamDataForMachine();
	}

	@Override
	public List<String[]> getAllMaintSkillData() throws Exception {
		//return this.equipmentDao.getMaintSkillData();
		return this.machinemasterserviceapi.getMaintenanceSkillData();
	}

	@Override
	public List<String[]> getAllEquipParmData() throws Exception {
		return this.equipmentDao.getEquipParmData();
	}

	@Override
	public List<String[]> getAllSubEquipData(String section) throws Exception {
		return this.equipmentDao.getSubEquipData(section);
	}

	@Override
	public List<String[]> getAllEquipmentParm(String machineId) throws Exception {
		//return this.equipmentDao.getEquipmentParm(machineId);
		return this.machinemasterserviceapi.getEquipmentData(machineId);
	}

	@Override
	public List<String[]> getOperatorRecallData(String oprRecall)
			throws Exception {
		//return this.equipmentDao.getOperatorRecall(oprRecall);
		return this.machinemasterserviceapi.recallOperatorData(oprRecall);
	}

	@Override
	public List<String[]> getOperatorSkillRecallData(String recall)
			throws Exception {
		//return this.equipmentDao.getOperatorSkillRecall(recall);
		return this.machinemasterserviceapi.recallOperatorSkillData(recall);
	}

	@Override
	public List<String[]> getMaintainceRecallData(String recall)
			throws Exception {
		//return this.equipmentDao.getMaintainceRecall(recall);
		return this.machinemasterserviceapi.recallMaintenanceData(recall);
	}

	@Override
	public List<String[]> getMaintainceSkillRecallData(String recall)
			throws Exception {
		//return this.equipmentDao.getMaintainceSkillRecall(recall);
		return this.machinemasterserviceapi.recallMaintenanceSkillData(recall);
	}

	@Override
	public List<String[]> getMasterGrid(CommonFilter commonFilter) throws Exception {
		
		return this.equipmentDao.getMainGrid(commonFilter);
	}

	@Override
	public List<String[]> getEquipmentParameterRecallData(String recall)
			throws Exception {
		//return this.equipmentDao.getEquipmentParameterRecall(recall);
		return this.machinemasterserviceapi.recallEquipmentParameterData(recall);
	}

	@Override
	public List<ComboBox> getSubSection(ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("SBCL_CODE");
		comboFilter.setNameField("SBCL_NAME");
		comboFilter.setIdField("SBCL_KEYID");
		//comboFilter.setOrderByField("Mchm_Modifiedon desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBCELLMST);
		CommonMessage.debugMsg(comboFilter);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<String[]> getSubEquipmentRecallData(String recall)
			throws Exception {
		return this.equipmentDao.getSubEquipmentRecall(recall);
	}

	@Override
	public List<String[]> getAllSubEquipDataForEqp(String sectionId,
			String eqpId) throws Exception {
		//return this.equipmentDao.getAllSubEquipDataForEqp(sectionId,eqpId);
		return this.machinemasterserviceapi.getSubEquipmentData(sectionId,eqpId);
	}


	public void deleteOperatorSkill(String machineId, String skillName) throws Exception{
		 //this.equipmentDao.deleteOperatorSkill(machineId, skillName);
		
		this.machinemasterserviceapi.deleteOperatorSkill(machineId, skillName);
	}
	
	public void deleteMainTeamSkill(String machineId, String skillName) throws Exception{
		 //this.equipmentDao.deleteMainTeamSkill(machineId, skillName);
		this.machinemasterserviceapi.deleteMaintenanceSkill(machineId, skillName);
		
	}
	
	public void deleteOperatorMachineLink(String machineId, String empId) throws Exception{
		// this.equipmentDao.deleteOperatorMachineLink(machineId, empId);
		this.machinemasterserviceapi.deleteOperatorMachineLink(machineId, empId);
	}
	public void deleteMaintTeamMachineLink(String machineId, String maintTeamId) throws Exception{
		 //this.equipmentDao.deleteMaintTeamMachineLink(machineId, maintTeamId);
		this.machinemasterserviceapi.deleteMaintenanceTeamMachineLink(machineId, maintTeamId);
	}

	@Override
	public Workbook EquipmentFormExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.equipmentDao.EquipmentFormExportExcel(commonFilter,  colModel,rptFormat);
	}

	@Override
	public int selectCount(CommonFilter commonFilter) throws Exception {
		return this.equipmentDao.selectCount(commonFilter);
	}

	@Override
	public List<String[]> getInactive(String keyIds) throws Exception {
		return this.equipmentDao.getInactive(keyIds);
	}

	@Override
	public List<String[]> getAll(CommonFilter commonFilter) throws Exception {
		return this.equipmentDao.getAll(commonFilter);
	}

	@Override
	public List<String[]> getAllCircle(String mchId) throws Exception {
		return this.equipmentDao.getAllCircle(mchId);
	}

	@Override
	public List<String[]> getFormCircle(String mchId) throws Exception {
		//return this.equipmentDao.getFormCircle(mchId);
		return this.machinemasterserviceapi.getFormCircle(mchId);
	}

	@Override
	public String deleteCircle(GenTlMchcirclelink newGenTlMchcirclelink,GenTlMchcirclelink existGenTlMchcirclelink,EquipmentBean equipmentBean) throws Exception {
		return equipmentDao.deleteCircle(newGenTlMchcirclelink);
	}

	@Override
	public List<String[]> getAllPM() throws Exception {
		// TODO Auto-generated method stub
		return equipmentDao.getAllPM();
	}

	@Override
	public List<String[]> getAllPMgrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return equipmentDao.getAllPMgrid(commonFilter);
	}

	public List<String[]> getEquipOtherDetails(String machineId) throws Exception {
		// TODO Auto-generated method stub
		return equipmentDao.getEquipOtherDetails(machineId);
	}



	

}
