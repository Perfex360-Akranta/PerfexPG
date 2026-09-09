package com.akranta.tpm.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
//import com.akranta.tpm.bean.GenTlAchievementsBean;
//import com.akranta.tpm.bean.GenTlDesignfmeaBean;
//import com.akranta.tpm.bean.GenTlEquipmentfmeaBean;
//import com.akranta.tpm.bean.GenTlProcessfmeaBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.FMEAFormatDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.FMEAFormatDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.FmeaExcelTemplate;
//import com.akranta.tpm.exportreport.PlannedJobObserExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.GenTlAchievements;
//import com.akranta.tpm.model.GenTlDesignfmea;
import com.akranta.tpm.model.GenTlEquipmentfmea;
//import com.akranta.tpm.model.GenTlProcessfmea;
import com.akranta.tpm.model.PlmTlDesignfmeadtl;
import com.akranta.tpm.model.PlmTlDesignfmeamst;
import com.akranta.tpm.model.PlmTlEquipmentfmeadtl;
import com.akranta.tpm.model.PlmTlEquipmentfmeamst;
import com.akranta.tpm.model.PlmTlProcessfmeadtl;
import com.akranta.tpm.model.PlmTlProcessfmeamst;
//import com.akranta.tpm.model.SheTlRiskassessmentdtl;
import com.akranta.tpm.service.FMEAFormatService;

import com.akranta.tpm.service.api.FmeaServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class FMEAFormatServiceImpl implements FMEAFormatService{
	private FmeaServiceApi fmeaServiceApi;
	private FMEAFormatDao fmeaFormatDao;
	private Validations validation;
	private CommonFilterDao commonFilterDao ;
	String validationsFor;
	DBActionTemplate  dbActionTemplate ; 
	
	public FMEAFormatServiceImpl(DBActionTemplate dbActionTemplate)
	{
		fmeaFormatDao =  new FMEAFormatDaoImpl(dbActionTemplate);
		validation = new Validations();
		this.dbActionTemplate  =dbActionTemplate ; 
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);		
	}
	
	public void FMEAFormatServiceImplJwt(String JwtToken){
    	try{
    		fmeaFormatDao.FMEAFormatDaoImplJwt(JwtToken);
    		fmeaServiceApi  = new FmeaServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
	
	@Override
	public List<ComboBox> getSystemComboList(ComboFilter system,String keyid) throws Exception {	
		system.setIdField("SYSM_KEYID");
		system.setCodeField("SYSM_CODE");
		system.setNameField("SYSM_NAME");		
		system.setTableName("PLM_TL_SYSTEMMST");
		return commonFilterDao.fillComboValues(system);
	}

	@Override
	public List<ComboBox> getSubSystemComboList(ComboFilter subSystem, String keyid)
			throws Exception {
		subSystem.setIdField("SSYM_KEYID");	
		subSystem.setCodeField("SSYM_CODE");	
		subSystem.setNameField("SSYM_NAME");		
		subSystem.setTableName("PLM_TL_SUBSYSTEMMST");
		return commonFilterDao.fillComboValues(subSystem);
	}
	
	@Override
	public List<ComboBox> getSubEqupmentComboList(
			ComboFilter subequipment, String flid, String cellid) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" flid :: "+flid);
		subequipment.setIdField("ESEC_KEYID");	
		subequipment.setCodeField("ESEC_CODE");	
		subequipment.setNameField("ESEC_NAME");		
		subequipment.setTableName("GEN_TL_EQUIP_SECTIONMST");
		if(UIUtils.isValidKeyId(flid))
			subequipment.setCondSql(" and ESEC_PARENTFLID = '"+flid+"'");
		
		if(UIUtils.isValidKeyId(cellid))
			subequipment.setCondSql(" AND ESEC_PARENTFLID IN ( SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='" + cellid + "') ");
		
		
		return commonFilterDao.fillComboValues(subequipment);
	}	
	
	@Override
	public List<ComboBox> getOccuranceComboList(ComboFilter occurance, String keyid)
			throws Exception {
		occurance.setIdField("OCCF_KEYID");		
		occurance.setCodeField("OCCF_NAME");		
		occurance.setTableName("PLM_TL_FMEAOCCURENCEMST");
		//Added by hari  04.05.2026
		occurance.setOrderByField("CAST(REGEXP_REPLACE(OCCF_CODE, '[^0-9]', '', 'g') AS INTEGER)");
		return commonFilterDao.fillComboValues(occurance);
	}
	
	@Override
	public List<ComboBox> getDetectionComboList(ComboFilter occurance, String keyid)
			throws Exception {
		occurance.setIdField("DETF_KEYID");		
		occurance.setCodeField("DETF_NAME");		
		occurance.setTableName("PLM_TL_FMEADETECTIONMST");
		//Added by hari  04.05.2026
		occurance.setOrderByField("CAST(REGEXP_REPLACE(DETF_NAME, '[^0-9]', '', 'g') AS INTEGER)");
		return commonFilterDao.fillComboValues(occurance);
	}
	@Override
	public List<ComboBox> getSeverityComboList(ComboFilter occurance, String keyid)
			throws Exception {
		occurance.setIdField("SEVF_KEYID");		
		occurance.setCodeField("SEVF_NAME");		
		occurance.setTableName("PLM_TL_FMEASEVERITYMST");
		//Added by hari  04.05.2026
		occurance.setOrderByField("CAST(REGEXP_REPLACE(SEVF_CODE, '[^0-9]', '', 'g') AS INTEGER)");
				
		return commonFilterDao.fillComboValues(occurance);
	}
	
	@Override
	public List<ComboBox> getComponentComboList(ComboFilter occurance, String keyid)
			throws Exception {
		occurance.setIdField("CMPN_KEYID");	
		occurance.setCodeField("CMPN_CODE");	
		occurance.setNameField("CMPN_NAME");		
		occurance.setTableName("PLM_TL_COMPONENTMST");
		if (UIUtils.isValidKeyId(keyid))
			occurance.setCondSql(" AND SUBP_PROCESSID ='" + keyid + "' ");
		return commonFilterDao.fillComboValues(occurance);
	}

	@Override
	public List<String[]> getFMEADtlsList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return fmeaFormatDao.getFMEADtlsList(commonFilter);
	}

	@Override
	public List<String[]> getFMEAReviewList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return fmeaFormatDao.getFMEAReviewList(commonFilter);
	}

	@Override
	public List<String[]> getFMEAMstList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return fmeaFormatDao.getFMEAMstList(commonFilter);
	}

	@Override
	public PlmTlDesignfmeamst create(PlmTlDesignfmeamst newPlmTlDesignfmeamst,
			PlmTlDesignfmeamst existPlmTlDesignfmeamst) throws ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		try{
			//validation.validate(  newPlmTlDesignfmeamst,"PlmTlDesignfmeamst","create");		
			newPlmTlDesignfmeamst=fillValues( newPlmTlDesignfmeamst,existPlmTlDesignfmeamst);
			return fmeaFormatDao.create(newPlmTlDesignfmeamst);
		}
		catch (ValidationExceptions e) {				
			throw new ValidationExceptions(e.getMessage());
		}
		catch (Exception e) {				
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public PlmTlDesignfmeamst update(PlmTlDesignfmeamst newPlmTlDesignfmeamst,
			PlmTlDesignfmeamst existPlmTlDesignfmeamst) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
		try{
			validation.validate(  newPlmTlDesignfmeamst,"PlmTlDesignfmeamst","update");		
			newPlmTlDesignfmeamst=fillValues( newPlmTlDesignfmeamst,existPlmTlDesignfmeamst);
			return fmeaFormatDao.update(newPlmTlDesignfmeamst);
		}
		catch (ValidationExceptions e) {				
			throw new ValidationExceptions(e.getMessage());
		}
		catch (Exception e) {				
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public PlmTlEquipmentfmeamst create(
			PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst,
			PlmTlEquipmentfmeamst existPlmTlEquipmentfmeamst) throws Exception {
		// TODO Auto-generated method stub
		validation.validate(  newPlmTlEquipmentfmeamst,"PlmTlEquipmentfmeamst","create");		
		newPlmTlEquipmentfmeamst=fillValues( newPlmTlEquipmentfmeamst,existPlmTlEquipmentfmeamst);
		//return fmeaFormatDao.create(newPlmTlEquipmentfmeamst);
		return fmeaServiceApi.insertRecord(newPlmTlEquipmentfmeamst);
	}

	@Override
	public PlmTlEquipmentfmeamst update(
			PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst,
			PlmTlEquipmentfmeamst existPlmTlEquipmentfmeamst) throws Exception {
		// TODO Auto-generated method stub
		validation.validate(  newPlmTlEquipmentfmeamst,"PlmTlEquipmentfmeamst","update");		
		newPlmTlEquipmentfmeamst=fillValues( newPlmTlEquipmentfmeamst,existPlmTlEquipmentfmeamst);
		//return fmeaFormatDao.update(newPlmTlEquipmentfmeamst);
		return fmeaServiceApi.insertRecord(newPlmTlEquipmentfmeamst);
	}

	@Override
	public PlmTlProcessfmeamst create(
			PlmTlProcessfmeamst newPlmTlProcessfmeamst,
			PlmTlProcessfmeamst existPlmTlProcessfmeamst) throws Exception {
		// TODO Auto-generated method stub
		validation.validate(  newPlmTlProcessfmeamst,"PlmTlProcessfmeamst","create");		
		newPlmTlProcessfmeamst=fillValues( newPlmTlProcessfmeamst,existPlmTlProcessfmeamst);
		//return fmeaFormatDao.create(newPlmTlProcessfmeamst);
		return fmeaServiceApi.insertprocessRecords(newPlmTlProcessfmeamst);
	}

	@Override
	public PlmTlProcessfmeamst update(
			PlmTlProcessfmeamst newPlmTlProcessfmeamst,
			PlmTlProcessfmeamst existPlmTlProcessfmeamst) throws Exception {
		// TODO Auto-generated method stub
		validation.validate(  newPlmTlProcessfmeamst,"PlmTlProcessfmeamst","update");		
		newPlmTlProcessfmeamst=fillValues( newPlmTlProcessfmeamst,existPlmTlProcessfmeamst);
		//return fmeaFormatDao.update(newPlmTlProcessfmeamst);
		return fmeaServiceApi.insertprocessRecords(newPlmTlProcessfmeamst);
		
	}

	private PlmTlDesignfmeamst fillValues(PlmTlDesignfmeamst newPlmTlDesignfmeamst,PlmTlDesignfmeamst existPlmTlDesignfmeamst) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("fillValues");
		String dateTime = CommonFunctions.dateTimeNow();		
		newPlmTlDesignfmeamst.setFmdmActive("Y");
		newPlmTlDesignfmeamst.setFmdmCreatedon(dateTime);
		newPlmTlDesignfmeamst.setFmdmModifiedon(dateTime);
		
	/*
		if(newPlmTlDesignfmeamst.getFmdmFlid() == null )
			newPlmTlDesignfmeamst.setFmdmFlid("{}");
		
		if(newPlmTlDesignfmeamst.getFmdmDate() == null )
			newPlmTlDesignfmeamst.setFmdmDate(Constants.futureNullDate);
		
		if(newPlmTlDesignfmeamst.getFmdmPreparedby() == null )
			newPlmTlDesignfmeamst.setFmdmPreparedby("{}");
		
		
		if(newPlmTlDesignfmeamst.getFmdmSystemid() == null )
			newPlmTlDesignfmeamst.setFmdmSystemid("{}");
		
		if(newPlmTlDesignfmeamst.getFmdmSupsystemid() == null )
			newPlmTlDesignfmeamst.setFmdmSupsystemid("{}");
		*/
		if(newPlmTlDesignfmeamst.getFmdmNo() == null )
			newPlmTlDesignfmeamst.setFmdmNo("{}");
		
		if(newPlmTlDesignfmeamst.getFmdmCoreteam() == null )
			newPlmTlDesignfmeamst.setFmdmCoreteam("{}");		
		
		
		if(newPlmTlDesignfmeamst.getFmdmDoctype() == null )
			newPlmTlDesignfmeamst.setFmdmDoctype("{}");	
		
		if(newPlmTlDesignfmeamst.getFmdmDocmstid() == null )
			newPlmTlDesignfmeamst.setFmdmDocmstid("{}");	
		
		if(newPlmTlDesignfmeamst.getFmdmDocdtlsid() == null )
			newPlmTlDesignfmeamst.setFmdmDocdtlsid("{}");
		
		if(newPlmTlDesignfmeamst.getFmdmComponentid() == null )
			newPlmTlDesignfmeamst.setFmdmComponentid("{}");
		
		if(newPlmTlDesignfmeamst.getFmdmTempfield1() == null )
			newPlmTlDesignfmeamst.setFmdmTempfield1("-");
		
		if(newPlmTlDesignfmeamst.getFmdmTempfield2() == null )
			newPlmTlDesignfmeamst.setFmdmTempfield2("-");
		
		if(newPlmTlDesignfmeamst.getFmdmTempfield3() == null )
			newPlmTlDesignfmeamst.setFmdmTempfield3("-");		
		
		if(newPlmTlDesignfmeamst.getFmdmTempfield4() == null )
			newPlmTlDesignfmeamst.setFmdmTempfield4("-");
		
		if(newPlmTlDesignfmeamst.getFmdmTempfield5() == null )
			newPlmTlDesignfmeamst.setFmdmTempfield5("-");
		
		newPlmTlDesignfmeamst.setPlmTlDesignfmeadtl(fillValuesDesignDetails(newPlmTlDesignfmeamst));
		return newPlmTlDesignfmeamst;
	}

	private List<PlmTlDesignfmeadtl> fillValuesDesignDetails(PlmTlDesignfmeamst newPlmTlDesignfmeamst) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		List<PlmTlDesignfmeadtl> plmTlDesignfmeadtlList=newPlmTlDesignfmeamst.getplmTlDesignfmeadtl();
		//CommonMessage.debugMsg("start Of  fillValues genTlFnlnrolemapList:"+ plmTlDesignfmeadtlList.size());
		if (newPlmTlDesignfmeamst.getplmTlDesignfmeadtl()!=null){
			for(int i=0 ;i<=plmTlDesignfmeadtlList.size()-1;i++){		
				plmTlDesignfmeadtlList.get(i).setFmddCreatedon(dateTime);
				plmTlDesignfmeadtlList.get(i).setFmddCreatedby(newPlmTlDesignfmeamst.getFmdmCreatedby());
				plmTlDesignfmeadtlList.get(i).setFmddModifiedon(dateTime);
				plmTlDesignfmeadtlList.get(i).setFmddActive("Y");
	
				if (plmTlDesignfmeadtlList.get(i).getFmddItem() == null)
					plmTlDesignfmeadtlList.get(i).setFmddItem("{}");
	
				if (plmTlDesignfmeadtlList.get(i).getFmddFunction() == null)
					plmTlDesignfmeadtlList.get(i).setFmddFunction("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddPotentialfailmode() == null)
					plmTlDesignfmeadtlList.get(i).setFmddPotentialfailmode("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddPotentialeffectfail() == null)
					plmTlDesignfmeadtlList.get(i).setFmddPotentialeffectfail("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddPotentialcausefail() == null)
					plmTlDesignfmeadtlList.get(i).setFmddPotentialcausefail("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddSeverityKeyid() == null)
					plmTlDesignfmeadtlList.get(i).setFmddSeverityKeyid("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddOccurrenceKeyid() == null)
					plmTlDesignfmeadtlList.get(i).setFmddOccurrenceKeyid("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddDetectionKeyid() == null)
					plmTlDesignfmeadtlList.get(i).setFmddDetectionKeyid("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddCurrentcontrol() == null)
					plmTlDesignfmeadtlList.get(i).setFmddCurrentcontrol("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddRpn() == null)
					plmTlDesignfmeadtlList.get(i).setFmddRpn("0");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddActionplan() == null)
					plmTlDesignfmeadtlList.get(i).setFmddActionplan("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddReseverityKeyid()== null)
					plmTlDesignfmeadtlList.get(i).setFmddReseverityKeyid("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddReoccurrenceKeyid() == null)
					plmTlDesignfmeadtlList.get(i).setFmddReoccurrenceKeyid("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddRedetectionKeyid() == null)
					plmTlDesignfmeadtlList.get(i).setFmddRedetectionKeyid("{}");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddRerpn() == null)
					plmTlDesignfmeadtlList.get(i).setFmddRerpn("0");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddReseverityKeyid()== null){
					plmTlDesignfmeadtlList.get(i).setFmddReviewby("{}");
					plmTlDesignfmeadtlList.get(i).setFmddRedate(Constants.futureNullDate);
				}
				else{
					if (plmTlDesignfmeadtlList.get(i).getFmddReviewby() == null)
						plmTlDesignfmeadtlList.get(i).setFmddReviewby(newPlmTlDesignfmeamst.getFmdmCreatedby());
					
					if (plmTlDesignfmeadtlList.get(i).getFmddRedate() == null)
						plmTlDesignfmeadtlList.get(i).setFmddRedate(dateTime);
				}
				
				if (plmTlDesignfmeadtlList.get(i).getFmddTempfield1() == null)
					plmTlDesignfmeadtlList.get(i).setFmddTempfield1("-");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddTempfield2() == null)
					plmTlDesignfmeadtlList.get(i).setFmddTempfield2("-");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddTempfield2() == null)
					plmTlDesignfmeadtlList.get(i).setFmddTempfield2("-");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddTempfield3() == null)
					plmTlDesignfmeadtlList.get(i).setFmddTempfield3("-");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddTempfield4() == null)
					plmTlDesignfmeadtlList.get(i).setFmddTempfield4("-");
				
				if (plmTlDesignfmeadtlList.get(i).getFmddTempfield5() == null)				
					plmTlDesignfmeadtlList.get(i).setFmddTempfield5("-");
			}
		}
		CommonMessage.debugMsg("End Of  fillValues plmTlDesignfmeadtlList");
		return plmTlDesignfmeadtlList;
	}
	private PlmTlEquipmentfmeamst fillValues(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst,PlmTlEquipmentfmeamst existPlmTlEquipmentfmeamst) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("fillValues");
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		newPlmTlEquipmentfmeamst.setFmeqActive("Y");
		newPlmTlEquipmentfmeamst.setFmeqCreatedon(dateTime);
		newPlmTlEquipmentfmeamst.setFmeqModifiedon(dateTime);
		
		//if(newPlmTlEquipmentfmeamst.getFmeqKeyid() == null )
		 // newPlmTlEquipmentfmeamst.setFmeqKeyid("{}");
		
		//if(newPlmTlEquipmentfmeamst.getFmeqFlid() == null )
			//newPlmTlEquipmentfmeamst.setFmeqFlid("{}");
		String FmeqDate = newPlmTlEquipmentfmeamst.getFmeqDate();
		newPlmTlEquipmentfmeamst.setFmeqDate(CommonFunctions.pg_getDateTimeFromDate(newPlmTlEquipmentfmeamst.getFmeqDate()));
		if(newPlmTlEquipmentfmeamst.getFmeqDate() == null )
		newPlmTlEquipmentfmeamst.setFmeqDate(Constants.pgFutureNullDate);
		
		if(newPlmTlEquipmentfmeamst.getFmeqPreparedby() == null )
			newPlmTlEquipmentfmeamst.setFmeqPreparedby("{}");		
		
		if(newPlmTlEquipmentfmeamst.getFmeqEquipid() == null )
			newPlmTlEquipmentfmeamst.setFmeqEquipid("{}");
		
		if(newPlmTlEquipmentfmeamst.getFmeqSupequipid() == null )
			newPlmTlEquipmentfmeamst.setFmeqSupequipid("{}");
		if(newPlmTlEquipmentfmeamst.getFmeqNo() == null )
			newPlmTlEquipmentfmeamst.setFmeqNo("{}");
		
		if(newPlmTlEquipmentfmeamst.getFmeqCoreteam() == null )
			newPlmTlEquipmentfmeamst.setFmeqCoreteam("{}");	
		
		if(newPlmTlEquipmentfmeamst.getFmeqDoctype() == null )
			newPlmTlEquipmentfmeamst.setFmeqDoctype("{}");	
		
		if(newPlmTlEquipmentfmeamst.getFmeqDocmstid() == null )
			newPlmTlEquipmentfmeamst.setFmeqDocmstid("{}");	
		
		if(newPlmTlEquipmentfmeamst.getFmeqDocdtlsid() == null )
			newPlmTlEquipmentfmeamst.setFmeqDocdtlsid("{}");
		
		if(newPlmTlEquipmentfmeamst.getFmeqTempfield1() == null )
			newPlmTlEquipmentfmeamst.setFmeqTempfield1("-");
		
		if(newPlmTlEquipmentfmeamst.getFmeqTempfield2() == null )
			newPlmTlEquipmentfmeamst.setFmeqTempfield2("-");
		
		if(newPlmTlEquipmentfmeamst.getFmeqTempfield3() == null )
			newPlmTlEquipmentfmeamst.setFmeqTempfield3("-");		
		
		if(newPlmTlEquipmentfmeamst.getFmeqTempfield4() == null )
			newPlmTlEquipmentfmeamst.setFmeqTempfield4("-");
		
		if(newPlmTlEquipmentfmeamst.getFmeqTempfield5() == null )
			newPlmTlEquipmentfmeamst.setFmeqTempfield5("-");
		
		newPlmTlEquipmentfmeamst.setPlmTlEquipmentfmeadtl(fillValuesEquipDetails(newPlmTlEquipmentfmeamst));
		return newPlmTlEquipmentfmeamst;
	}
	private List<PlmTlEquipmentfmeadtl> fillValuesEquipDetails(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtlList=newPlmTlEquipmentfmeamst.getplmTlEquipmentfmeadtl();
		//CommonMessage.debugMsg("start Of  fillValues genTlFnlnrolemapList:"+ plmTlEquipmentfmeadtlList.size());
		if (newPlmTlEquipmentfmeamst.getplmTlEquipmentfmeadtl()!=null){
			for(int i=0 ;i<=plmTlEquipmentfmeadtlList.size()-1;i++){		
				plmTlEquipmentfmeadtlList.get(i).setFmedCreatedon(dateTime);
				plmTlEquipmentfmeadtlList.get(i).setFmedCreatedby(newPlmTlEquipmentfmeamst.getFmeqCreatedby());
				plmTlEquipmentfmeadtlList.get(i).setFmedModifiedon(dateTime);
				plmTlEquipmentfmeadtlList.get(i).setFmedActive("Y");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedFmeqKeyid() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedFmeqKeyid("");
				
	
				if (plmTlEquipmentfmeadtlList.get(i).getFmedComponent() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedComponent("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedFunctionfail() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedFunctionfail("{}");
	
				if (plmTlEquipmentfmeadtlList.get(i).getFmedFunction() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedFunction("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedPotentialfailmode() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedPotentialfailmode("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedPotentialeffectfail() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedPotentialeffectfail("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedPotentialcausefail() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedPotentialcausefail("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedSeverityKeyid() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedSeverityKeyid("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedOccurrenceKeyid() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedOccurrenceKeyid("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedDetectionKeyid() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedDetectionKeyid("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedCurrentcontrol() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedCurrentcontrol("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedRpn() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedRpn("0");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedActionplan() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedActionplan("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedReseverityKeyid()== null)
					plmTlEquipmentfmeadtlList.get(i).setFmedReseverityKeyid("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedReoccurrenceKeyid() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedReoccurrenceKeyid("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedRedetectionKeyid() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedRedetectionKeyid("{}");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedRerpn() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedRerpn("0");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedReseverityKeyid()== null){
					plmTlEquipmentfmeadtlList.get(i).setFmedReviewby("{}");
					plmTlEquipmentfmeadtlList.get(i).setFmedRedate(Constants.futureNullDate);
				}
				else{
					if (plmTlEquipmentfmeadtlList.get(i).getFmedReviewby() == null)
						plmTlEquipmentfmeadtlList.get(i).setFmedReviewby(newPlmTlEquipmentfmeamst.getFmeqCreatedby());
					
					if (plmTlEquipmentfmeadtlList.get(i).getFmedRedate() == null)
						plmTlEquipmentfmeadtlList.get(i).setFmedRedate(dateTime);
				}
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedTempfield1() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedTempfield1("-");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedTempfield2() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedTempfield2("-");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedTempfield2() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedTempfield2("-");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedTempfield3() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedTempfield3("-");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedTempfield4() == null)
					plmTlEquipmentfmeadtlList.get(i).setFmedTempfield4("-");
				
				if (plmTlEquipmentfmeadtlList.get(i).getFmedTempfield5() == null)				
					plmTlEquipmentfmeadtlList.get(i).setFmedTempfield5("-");
			}
		}
		CommonMessage.debugMsg("End Of  fillValues plmTlEquipmentfmeadtlList");
		return plmTlEquipmentfmeadtlList;
	}
	
	private PlmTlProcessfmeamst fillValues(PlmTlProcessfmeamst newPlmTlProcessfmeamst,PlmTlProcessfmeamst existPlmTlProcessfmeamst) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("fillValues");
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		newPlmTlProcessfmeamst.setFmpmActive("Y");
		newPlmTlProcessfmeamst.setFmpmCreatedon(dateTime);
		newPlmTlProcessfmeamst.setFmpmModifiedon(dateTime);
		
		/*if(newPlmTlProcessfmeamst.getFmpmKeyid() == null )
		  newPlmTlProcessfmeamst.setFmpmKeyid("{}");
		
		if(newPlmTlProcessfmeamst.getFmpmFlid() == null )
			newPlmTlProcessfmeamst.setFmpmFlid("{}");*/
		
		String FmeqDate = newPlmTlProcessfmeamst.getFmpmDate();
		newPlmTlProcessfmeamst.setFmpmDate(CommonFunctions.pg_getDateTimeFromDate(newPlmTlProcessfmeamst.getFmpmDate()));
		
		if(newPlmTlProcessfmeamst.getFmpmDate() == null )
			newPlmTlProcessfmeamst.setFmpmDate(Constants.pgFutureNullDate);
		
		/*if(newPlmTlProcessfmeamst.getFmpmPreparedby() == null )
			newPlmTlProcessfmeamst.setFmpmPreparedby("{}");
					
		if(newPlmTlProcessfmeamst.getFmpmProcessid() == null )
			newPlmTlProcessfmeamst.setFmpmProcessid("{}");
		
		if(newPlmTlProcessfmeamst.getFmpmSupprocessid() == null )
			newPlmTlProcessfmeamst.setFmpmSupprocessid("{}");
		*/

		if(newPlmTlProcessfmeamst.getFmpmCoreteam() == null )
			newPlmTlProcessfmeamst.setFmpmCoreteam("{}");
		
		if(newPlmTlProcessfmeamst.getFmpmDoctype() == null )
			newPlmTlProcessfmeamst.setFmpmDoctype("{}");	
		
		if(newPlmTlProcessfmeamst.getFmpmDocmstid() == null )
			newPlmTlProcessfmeamst.setFmpmDocmstid("{}");	
		
		if(newPlmTlProcessfmeamst.getFmpmDocdtlsid() == null )
			newPlmTlProcessfmeamst.setFmpmDocdtlsid("{}");
		
		if(newPlmTlProcessfmeamst.getFmpmNo() == null )
			newPlmTlProcessfmeamst.setFmpmNo("{}");			
		
		if(newPlmTlProcessfmeamst.getFmpmTempfield1() == null )
			newPlmTlProcessfmeamst.setFmpmTempfield1("-");
		
		if(newPlmTlProcessfmeamst.getFmpmTempfield2() == null )
			newPlmTlProcessfmeamst.setFmpmTempfield2("-");
		
		if(newPlmTlProcessfmeamst.getFmpmTempfield3() == null )
			newPlmTlProcessfmeamst.setFmpmTempfield3("-");		
		
		if(newPlmTlProcessfmeamst.getFmpmTempfield4() == null )
			newPlmTlProcessfmeamst.setFmpmTempfield4("-");
		
		if(newPlmTlProcessfmeamst.getFmpmTempfield5() == null )
			newPlmTlProcessfmeamst.setFmpmTempfield5("-");
		
		newPlmTlProcessfmeamst.setPlmTlProcessfmeadtl(fillValuesProcessDetails(newPlmTlProcessfmeamst));
		return newPlmTlProcessfmeamst;
	}
	private List<PlmTlProcessfmeadtl> fillValuesProcessDetails(PlmTlProcessfmeamst newPlmTlProcessfmeamst) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<PlmTlProcessfmeadtl> plmTlProcessfmeadtlList=newPlmTlProcessfmeamst.getplmTlProcessfmeadtl();
		//CommonMessage.debugMsg("start Of  fillValues genTlFnlnrolemapList:"+ plmTlProcessfmeadtlList.size());
		if (newPlmTlProcessfmeamst.getplmTlProcessfmeadtl()!=null){
			for(int i=0 ;i<=plmTlProcessfmeadtlList.size()-1;i++){		
				plmTlProcessfmeadtlList.get(i).setFmpdCreatedon(dateTime);
				plmTlProcessfmeadtlList.get(i).setFmpdCreatedby(newPlmTlProcessfmeamst.getFmpmCreatedby());
				plmTlProcessfmeadtlList.get(i).setFmpdModifiedon(dateTime);
				plmTlProcessfmeadtlList.get(i).setFmpdActive("Y");
	
				if (plmTlProcessfmeadtlList.get(i).getFmpdKeyprocessinput() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdKeyprocessinput("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdProcessstep() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdProcessstep("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdPotentialfailmode() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdPotentialfailmode("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdPotentialeffectfail() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdPotentialeffectfail("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdPotentialcausefail() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdPotentialcausefail("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdSeverityKeyid() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdSeverityKeyid("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdOccurrenceKeyid() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdOccurrenceKeyid("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdDetectionKeyid() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdDetectionKeyid("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdCurrentcontrol() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdCurrentcontrol("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdRpn() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdRpn("0");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdReseverityKeyid()== null)
					plmTlProcessfmeadtlList.get(i).setFmpdReseverityKeyid("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdReoccurrenceKeyid() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdReoccurrenceKeyid("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdRedetectionKeyid() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdRedetectionKeyid("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdRerpn() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdRerpn("0");
				
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdReseverityKeyid()== null){
					plmTlProcessfmeadtlList.get(i).setFmpdReviewby("{}");
					plmTlProcessfmeadtlList.get(i).setFmpdRedate(Constants.futureNullDate);
				}
				else{
					if (plmTlProcessfmeadtlList.get(i).getFmpdReviewby() == null)
						plmTlProcessfmeadtlList.get(i).setFmpdReviewby(newPlmTlProcessfmeamst.getFmpmCreatedby());
					
					if (plmTlProcessfmeadtlList.get(i).getFmpdRedate() == null)
						plmTlProcessfmeadtlList.get(i).setFmpdRedate(dateTime);
				}
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdActionplan() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdActionplan("{}");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdTempfield1() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdTempfield1("-");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdTempfield2() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdTempfield2("-");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdTempfield2() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdTempfield2("-");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdTempfield3() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdTempfield3("-");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdTempfield4() == null)
					plmTlProcessfmeadtlList.get(i).setFmpdTempfield4("-");
				
				if (plmTlProcessfmeadtlList.get(i).getFmpdTempfield5() == null)				
					plmTlProcessfmeadtlList.get(i).setFmpdTempfield5("-");
			}
		}
		CommonMessage.debugMsg("End Of  fillValues plmTlProcessfmeadtlList");
		return plmTlProcessfmeadtlList;
	}

	@Override
	public List<String[]> select(String keyId, String type) throws Exception {
		// TODO Auto-generated method stub
		//return fmeaFormatDao.select(keyId,type);
		return fmeaServiceApi.select(keyId, type);
	}

	@Override
	public PlmTlProcessfmeamst delete(PlmTlProcessfmeamst newPlmTlProcessfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		return fmeaFormatDao.delete(newPlmTlProcessfmeamst);
	}

	@Override
	public PlmTlDesignfmeamst delete(PlmTlDesignfmeamst newPlmTlDesignfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		return fmeaFormatDao.delete(newPlmTlDesignfmeamst);
	}

	@Override
	public PlmTlEquipmentfmeamst delete(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst) throws Exception {
		// TODO Auto-generated method stub
		return fmeaFormatDao.delete(newPlmTlEquipmentfmeamst);
	}
	
	@Override
	public PlmTlProcessfmeamst deleteDtls(PlmTlProcessfmeamst newPlmTlProcessfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		//return fmeaFormatDao.deleteDtls(newPlmTlProcessfmeamst);
		return fmeaServiceApi.deleteDtls(newPlmTlProcessfmeamst);
		
	}

	@Override
	public PlmTlDesignfmeamst deleteDtls(PlmTlDesignfmeamst newPlmTlDesignfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		return fmeaFormatDao.deleteDtls(newPlmTlDesignfmeamst);
	}

	@Override
	public PlmTlEquipmentfmeamst deleteDtls(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst) throws Exception {
		// TODO Auto-generated method stub
//		return fmeaFormatDao.deleteDtls(newPlmTlEquipmentfmeamst);
		return fmeaServiceApi.EquipdeleteDtl(newPlmTlEquipmentfmeamst);
	}

	@Override
	public Workbook getFmeaListExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return fmeaFormatDao.getFmeaListExportExcel(commonFilter,tblJSONObj,format);
	}

	@Override
	public Workbook getFmeaExcel(String keyid, String format,String path,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, List<String[]>> fmeaData = fmeaFormatDao.fmeaExcel(keyid,commonFilter);
		List<String[]> actionpln=fmeaFormatDao.getActionplanExcel(keyid);
		CommonMessage.debugMsg(" Inside Service Impl :: "+fmeaData.size()+";"+fmeaData+" Inside Service Impl :: "+fmeaData.get(0).size());
		/** Sugumar added for Action plan First record need to show in excel on 3May2016**/
		//Workbook wb=(new FmeaExcelTemplate(dbActionTemplate)).fillValues(fmeaData, format,path, keyid,commonFilter.getBdType());
		Workbook wb=(new FmeaExcelTemplate(dbActionTemplate)).fillValues(fmeaData, format,path, keyid,commonFilter.getBdType(),actionpln);
		return wb;
	}

	@Override
	public List<String[]> getTotalVal(String severity, String occurance,String detection) throws Exception {
		return fmeaFormatDao.getTotalVal(severity,occurance,detection);
	}

	@Override
	public String getflid(String cellid) throws Exception {
		// TODO Auto-generated method stub
		return fmeaFormatDao.getFlid(cellid);
	}
	
	public List<String[]> getExistingFmeaByEquip(
	        String equipId, String flid) throws Exception {
	    return fmeaFormatDao.getExistingFmeaByEquip(equipId, flid);
	}

	

	
}
