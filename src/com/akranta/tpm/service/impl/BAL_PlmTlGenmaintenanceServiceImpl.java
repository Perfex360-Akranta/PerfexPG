package com.akranta.tpm.service.impl;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GeneralMaintainanceBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_PlmTlGenmaintenanceDao;
//import com.akranta.tpm.dao.PlmTlGenmaintenanceDao;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.BAL_PlmTlGenmaintenanceDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BAL_BdmTlSetupadjsplit;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PlmTlGenmaintenance;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.BAL_PlmTlGenmaintenanceService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.BAL_GeneralMaintenanceServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;


public class BAL_PlmTlGenmaintenanceServiceImpl implements BAL_PlmTlGenmaintenanceService {
	private BAL_CommonFilterDao commonFilterDao;
	private BAL_PlmTlGenmaintenanceDao plmTlGenmaintenanceDao;
	private Validations validations ;
	private BAL_GeneralMaintenanceServiceApi GeneralMaintenanceServiceApi;
    FunctionCallApi fnCallApi;
	
    public void BAL_PlmTlGenmaintenanceServiceImplJwt(String JwtToken){
    	try{
    		plmTlGenmaintenanceDao.BAL_PlmTlGenmaintenanceDaoImplJwt(JwtToken);
    		GeneralMaintenanceServiceApi = new BAL_GeneralMaintenanceServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	
	public BAL_PlmTlGenmaintenanceServiceImpl(DBActionTemplate dbActionTemplate)
	{
		plmTlGenmaintenanceDao = new BAL_PlmTlGenmaintenanceDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	@Override
	public List<ComboBox> getGmntTradeCombo(String condSql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("TRDM_NAME");
		comboFilter.setIdField("TRDM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getGmntcompletedbyCombo(String condSql, ComboFilter comboFilter)throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	@Override
	public List<ComboBox> getGmntreptdbyidCombo(String condSql, ComboFilter comboFilter)throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getGmntshiftCombo(String condSql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setNameField("SFTM_NAME");
		comboFilter.setCodeField("SFTM_CODE");
		comboFilter.setIdField("SFTM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}
	public List<ComboBox> getSubLossshiftCombo(String fromDate,String toDate,String factId,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
//		comboFilter.setNameField("SFTM_NAME");
		comboFilter.setCodeField("SFTM_CODE");
		comboFilter.setIdField("SFTM_KEYID");
		comboFilter.setTableName("GEN_TL_HOLIDAYMST");
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT SFTM_KEYID, TO_CHAR(STARTTIME, 'DD-MON-YYYY') STARTTIME, SFTM_CODE, '0', '0', '',");
		sb.append("ROUND((LEAST(ENDTIME, BDCLOSETIME) - GREATEST(BDTIME, STARTTIME)) * 1440) SHIFTMINUTES FROM(");
		sb.append("Select To_Date('"+fromDate+"' , 'DD-MON-YYYY HH24:MI') Bdtime, Sftm_Keyid,");
		sb.append("TO_DATE('"+toDate+"' , 'DD-MON-YYYY HH24:MI') BDCLOSETIME,");
		sb.append("SFTM_STARTTIME + DECODE(SIGN(TO_NUMBER(TO_CHAR(SFTM_ENDTIME, 'HH24MI')) -");
		sb.append("TO_NUMBER(TO_CHAR(SFTM_STARTTIME, 'HH24MI'))), 1, VARTIME - TRUNC(SFTM_STARTTIME),");
		sb.append("VARTIME - 1 - TRUNC(SFTM_STARTTIME)) STARTTIME,  SFTM_ENDTIME + (VARTIME - TRUNC(SFTM_ENDTIME))");
		sb.append(" ENDTIME, SFTM_CODE  FROM "+TableNames.TBL_GEN_TL_SHIFTMST+",(");
		sb.append("SELECT TRUNC(TO_DATE('"+fromDate+"', 'DD-MON-YYYY HH24:MI') + (ADDVAL - 1))  - 1 VARTIME");
		sb.append(" FROM DUAL, (SELECT ROWNUM ADDVAL FROM TAB) WHERE ");
		sb.append("TO_DATE('"+fromDate+"', 'DD-MON-YYYY HH24:MI') + (ADDVAL - 1) - 1  <=");
		sb.append("TO_DATE('"+toDate+"', 'DD-MON-YYYY HH24:MI') + 2) Where");
		sb.append(" SFTM_SHIFTORDER<='3' AND SFTM_FACTORYID = '"+factId+"')");
         
		sb.append(" WHERE STARTTIME BETWEEN BDTIME AND BDCLOSETIME");
		sb.append(" OR ENDTIME BETWEEN BDTIME AND BDCLOSETIME");
		sb.append(" OR BDTIME BETWEEN STARTTIME AND ENDTIME");
		sb.append(" OR BDCLOSETIME >= STARTTIME AND BDCLOSETIME < ENDTIME)");
     
		sb.append(" WHERE Trunc(Holm_Date(+)) = Trunc(To_Date(Starttime, 'DD-MON-YYYY'))  And Shiftminutes > 0");
		comboFilter.setCondSql(sb.toString()); 
		return plmTlGenmaintenanceDao.fillComboValues(comboFilter);
		
	}
	
	public List<ComboBox> getSubLossCombo(String condSql,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setNameField("PLCM_MAPFIELD");
		comboFilter.setCodeField("PLCM_PARAMETERNAME");
		comboFilter.setIdField("PLCM_KEYID");
		comboFilter.setTableName(TableNames.TBL_PCS_TL_LOGCONFIGURATION);
		condSql = " AND Plcm_Parentid In";
		condSql +="(Select Plcm_Parentid From "+TableNames.TBL_PCS_TL_LOGCONFIGURATION ;
		condSql += " WHERE PLCM_PARAMETERCODE = 'SETUPANDADJ')";
		condSql +=" AND PLCM_PARAMETERCODE <> 'SETUPANDADJ'";
		comboFilter.setCondSql(condSql); 
		return commonFilterDao.fillComboValues(comboFilter);
		
	}
	@Override
	public List<ComboBox> getGmntmouldCombo(String condSql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setNameField("MLDM_MOULDID");
		comboFilter.setCodeField("MLDM_DESCRIPTION");
		comboFilter.setIdField("MLDM_MOULDID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MOULDMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public BAL_PlmTlGenmaintenance create(BAL_PlmTlGenmaintenance newPlmTlGenmaintenance,BAL_PlmTlGenmaintenance existPlmTlGenmaintenance,BAL_GeneralMaintainanceBean generalMaintainanceBean) throws Exception {
		// TODO Auto-generated method stub
		try {
//				String validationsFor = "create";
//				validations.validate(newPlmTlGenmaintenance,"GeneralMaintainance",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
//				
//				/*adding data for spinners**/
//				newPlmTlGenmaintenance.setGmntOccureddate(newPlmTlGenmaintenance.getGmntOccureddate()+"  "+generalMaintainanceBean.getOccuredTime());
//				if( UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntWostartdate())){
//
//					newPlmTlGenmaintenance.setGmntWostartdate(newPlmTlGenmaintenance.getGmntWostartdate()+"  "+generalMaintainanceBean.getWorkstartTime());
//				}if( UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntWoenddate())){
//				    newPlmTlGenmaintenance.setGmntWoenddate(newPlmTlGenmaintenance.getGmntWoenddate()+"  "+generalMaintainanceBean.getWorkendTime());
//				}
			
			//-----------------------------------------------------------
			 String validationsFor = "create";
		        validations.validate(newPlmTlGenmaintenance,
		            "GeneralMaintainance",validationsFor);
				
		 
		        //-----------------------------------------------------------------
				fillValues(newPlmTlGenmaintenance,existPlmTlGenmaintenance,generalMaintainanceBean);
				checkSetupDuration(newPlmTlGenmaintenance);
				//for inserting in clisCalendar procedure
				//return  plmTlGenmaintenanceDao.create(newPlmTlGenmaintenance);
				return GeneralMaintenanceServiceApi.saveGeneralmainteneance(newPlmTlGenmaintenance);
			   }catch (ValidationExceptions e){
				
				throw new ValidationExceptions(e.getMessage());
				
			}	
		
	}

	@Override
	public BAL_PlmTlGenmaintenance update(BAL_PlmTlGenmaintenance newPlmTlGenmaintenance,BAL_PlmTlGenmaintenance existPlmTlGenmaintenance,
			BAL_GeneralMaintainanceBean generalMaintainanceBean,WomTlWomst womTlWomst) throws Exception {
		// TODO Auto-generated method stub
		try {

			String validationsFor = "update";
			validations.validate(newPlmTlGenmaintenance,"GeneralMaintainance",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
			
			/*adding data for spinners**/
//			newPlmTlGenmaintenance.setGmntOccureddate(newPlmTlGenmaintenance.getGmntOccureddate()+"  "+generalMaintainanceBean.getOccuredTime());
//			if( UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntWostartdate()))
//				newPlmTlGenmaintenance.setGmntWostartdate(newPlmTlGenmaintenance.getGmntWostartdate()+"  "+generalMaintainanceBean.getWorkstartTime());
//			if( UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntWoenddate()))
//			newPlmTlGenmaintenance.setGmntWoenddate(newPlmTlGenmaintenance.getGmntWoenddate()+"  "+generalMaintainanceBean.getWorkendTime());
			
			/*adding data for spinners**/
			

		
			//-------------------------------------------------------
			fillValues(newPlmTlGenmaintenance,existPlmTlGenmaintenance,generalMaintainanceBean);
			checkSetupDuration(newPlmTlGenmaintenance);
			//for inserting in clisCalendar procedure
			//return  plmTlGenmaintenanceDao.update(newPlmTlGenmaintenance,womTlWomst);
			return GeneralMaintenanceServiceApi.saveGeneralmainteneance(newPlmTlGenmaintenance);

		   }catch (ValidationExceptions e){
			
			throw new ValidationExceptions(e.getMessage());
			
		}	
	}
	private BAL_PlmTlGenmaintenance checkSetupDuration(BAL_PlmTlGenmaintenance newPlmTlGenmaintenance) throws BusinessApplicationExceptions
	{
		
		if(newPlmTlGenmaintenance.getBdmTlSetupadjsplit()!= null && newPlmTlGenmaintenance.getBdmTlSetupadjsplit().size()>0)
		{
    		int duration = 0;
    		CommonFunctions.debugMsg("Size : "+newPlmTlGenmaintenance.getBdmTlSetupadjsplit().size());
    		for(int i =0;i<newPlmTlGenmaintenance.getBdmTlSetupadjsplit().size();i++)
			{	
				BAL_BdmTlSetupadjsplit bdmTlSetupadjsplitmst = (BAL_BdmTlSetupadjsplit)newPlmTlGenmaintenance.getBdmTlSetupadjsplit().get(i);
				if(UIUtils.isValidKeyId(bdmTlSetupadjsplitmst.getSupsDuration()))
				duration = duration + Integer.parseInt(bdmTlSetupadjsplitmst.getSupsDuration());
				CommonFunctions.debugMsg("duration "+i+" : "+ duration);
			}
    		CommonFunctions.debugMsg("downtime "+newPlmTlGenmaintenance.getGmntDowntime()+" Duration : "+ duration);
    		if(UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntDowntime()))
    		{
    			if(Integer.parseInt(newPlmTlGenmaintenance.getGmntDowntime()) != duration)
    				throw new BusinessApplicationExceptions("lossDuration,");
    		}
		}
		return newPlmTlGenmaintenance;
	}
	private BAL_PlmTlGenmaintenance fillValues(BAL_PlmTlGenmaintenance newPlmTlGenmaintenance,BAL_PlmTlGenmaintenance existPlmTlGenmaintenance,BAL_GeneralMaintainanceBean generalMaintainanceBean ) 
	{// TODO Auto-generated method stub
		//newPlmTlGenmaintenance.
		newPlmTlGenmaintenance.setGmntActive("Y");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		if(newPlmTlGenmaintenance.getGmntKeyid() == null ){
			newPlmTlGenmaintenance.setGmntCreatedon(dateTime);
		}
		else{
			System.out.println("Testing in ...Service impl"+newPlmTlGenmaintenance.getGmntCreatedon());
			newPlmTlGenmaintenance.setGmntCreatedon(dateTime);
		}
		 if(newPlmTlGenmaintenance.getGmntKeyid() == null ){
			newPlmTlGenmaintenance.setGmntModifiedon(dateTime);
		 }else
			newPlmTlGenmaintenance.setGmntModifiedon(newPlmTlGenmaintenance.getGmntCreatedon());
		
		 	newPlmTlGenmaintenance.setGmntModifiedon(dateTime);
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntAction()) )
			newPlmTlGenmaintenance.setGmntAction("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntActivitytype() ) )
			newPlmTlGenmaintenance.setGmntActivitytype("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntAllocateddate() ) )
			newPlmTlGenmaintenance.setGmntAllocateddate(dateTime);
		String date= newPlmTlGenmaintenance.getGmntBookeddate();
		newPlmTlGenmaintenance.setGmntBookeddate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntBookeddate() ) )
			newPlmTlGenmaintenance.setGmntBookeddate(dateTime);
		
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntCompletedby() ) )
			newPlmTlGenmaintenance.setGmntCompletedby("{}");
		
		String date1= newPlmTlGenmaintenance.getGmntCompleteddate();
		newPlmTlGenmaintenance.setGmntCompleteddate(CommonFunctions.pg_getDateTimeFromDate(date1));
		
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntCompleteddate() ) )
			newPlmTlGenmaintenance.setGmntCompleteddate(dateTime);

		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntContractorcost()) )
			newPlmTlGenmaintenance.setGmntContractorcost("0");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntCountermeasure()) )
			newPlmTlGenmaintenance.setGmntCountermeasure("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntDowntime()) )
			newPlmTlGenmaintenance.setGmntDowntime("0");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntFactoryid()) )
			newPlmTlGenmaintenance.setGmntFactoryid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntIsyy()) )
			newPlmTlGenmaintenance.setGmntIsyy("Y");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntLineid()) )
			newPlmTlGenmaintenance.setGmntLineid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntMachineid()) )
			newPlmTlGenmaintenance.setGmntMachineid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntManpowercost()) )
			newPlmTlGenmaintenance.setGmntManpowercost("0");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntMchcondition()) )
			newPlmTlGenmaintenance.setGmntMchcondition("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntMouldid()) )
			newPlmTlGenmaintenance.setGmntMouldid("{}");
		String time = generalMaintainanceBean.getOccuredTime();
		System.out.println("Occured Time "+time);
		
		//String date2= newPlmTlGenmaintenance.getGmntOccureddate();
		if( UIUtils.isValidKeyId(generalMaintainanceBean.getOccuredTime()) ) 
		{
		String joinedOccuredTime = newPlmTlGenmaintenance.getGmntOccureddate()+" "+generalMaintainanceBean.getOccuredTime()+":00";
		newPlmTlGenmaintenance.setGmntOccureddate(CommonFunctions.pg_getDateTimeFromTimeStamp(joinedOccuredTime));
		}
		//newPlmTlGenmaintenance.setGmntOccureddate(CommonFunctions.pg_getDateTimeFromDate(date2));
		
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntOccureddate()) )
			newPlmTlGenmaintenance.setGmntOccureddate(dateTime);
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntOthercost()) )
			newPlmTlGenmaintenance.setGmntOthercost("0");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntPartlocation()) )
			newPlmTlGenmaintenance.setGmntPartlocation("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntPctrmeasure()) )
			newPlmTlGenmaintenance.setGmntPctrmeasure("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntPhenid()) )
			newPlmTlGenmaintenance.setGmntPhenid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntProblem()) )
			newPlmTlGenmaintenance.setGmntProblem("{}");
		
		/*
		 * String date3= newPlmTlGenmaintenance.getGmntReceiveddate();
		 * newPlmTlGenmaintenance.setGmntReceiveddate(CommonFunctions.
		 * pg_getDateFromPGTimeStamp(date3));
		 */
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntReceiveddate()) )
			newPlmTlGenmaintenance.setGmntReceiveddate(dateTime);
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntRefdocid()) )
			newPlmTlGenmaintenance.setGmntRefdocid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntRefdoctype()) )
			newPlmTlGenmaintenance.setGmntRefdoctype("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntRelatedto()) )
			newPlmTlGenmaintenance.setGmntRelatedto("MCH");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntRemarks()) )
			newPlmTlGenmaintenance.setGmntRemarks("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntReportedby()) )
			newPlmTlGenmaintenance.setGmntReportedby("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntResponsetime()) )
			newPlmTlGenmaintenance.setGmntResponsetime("0");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntRootcause()) )
			newPlmTlGenmaintenance.setGmntRootcause("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntRootcauseid()) )
			newPlmTlGenmaintenance.setGmntRootcauseid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntSectionid()) )
			newPlmTlGenmaintenance.setGmntSectionid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntShift()) )
			newPlmTlGenmaintenance.setGmntShift("{}");
		
		String date4= newPlmTlGenmaintenance.getGmntShiftdate();
		newPlmTlGenmaintenance.setGmntShiftdate(CommonFunctions.pg_getDateTimeFromDate(date4));
		
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntShiftdate()) )
			newPlmTlGenmaintenance.setGmntShiftdate(dateTime);
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntSparecost()) )
			newPlmTlGenmaintenance.setGmntSparecost("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntStationid()) )
			newPlmTlGenmaintenance.setGmntStationid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntStatus()) )
			newPlmTlGenmaintenance.setGmntStatus("P");
		
		
		String date5= newPlmTlGenmaintenance.getGmntTargetdate();
		newPlmTlGenmaintenance.setGmntTargetdate(CommonFunctions.pg_getDateTimeFromDate(date5));
		
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTargetdate()) )
			newPlmTlGenmaintenance.setGmntTargetdate(dateTime);
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTrade()) )
			newPlmTlGenmaintenance.setGmntTrade("{}");
		CommonFunctions.debugMsg(newPlmTlGenmaintenance.getGmntOrderType()+" Order Type in fill values");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntOrderType()) )			
			newPlmTlGenmaintenance.setGmntOrderType("-");
		CommonFunctions.debugMsg(newPlmTlGenmaintenance.getGmntOrderType()+" Order Type in fill values");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getErrppostNo()))
			newPlmTlGenmaintenance.setErrppostNo("0");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getErrppostStatus()) )
			newPlmTlGenmaintenance.setErrppostStatus("X");
		if( UIUtils.isValidKeyId(generalMaintainanceBean.getIssparesY()))
			newPlmTlGenmaintenance.setIsSpares(generalMaintainanceBean.getIssparesY());
		CommonFunctions.debugMsg("checkspares in fill"+generalMaintainanceBean.getIssparesY());
		if(  UIUtils.isValidKeyId(generalMaintainanceBean.getIssparesN()))
			newPlmTlGenmaintenance.setIsSpares(generalMaintainanceBean.getIssparesN());
		if( UIUtils.isValidKeyId(generalMaintainanceBean.getIssparesW()))
			newPlmTlGenmaintenance.setIsSpares(generalMaintainanceBean.getIssparesW());
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield1()) )
			newPlmTlGenmaintenance.setGmntTempfield1("-");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield2()) )
			newPlmTlGenmaintenance.setGmntTempfield2("-");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield3()) )
			newPlmTlGenmaintenance.setGmntTempfield3("-");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield4()) )
			newPlmTlGenmaintenance.setGmntTempfield4("-");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield5()) )
			newPlmTlGenmaintenance.setGmntTempfield5("-");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield6()) )
			newPlmTlGenmaintenance.setGmntTempfield6("-");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield7()) )
			newPlmTlGenmaintenance.setGmntTempfield7("-");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield8()) )
			newPlmTlGenmaintenance.setGmntTempfield8("-");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield9()) )
			newPlmTlGenmaintenance.setGmntTempfield9("-");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntTempfield10()) )
			newPlmTlGenmaintenance.setGmntTempfield10("-");
		
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntElementid()) )
			newPlmTlGenmaintenance.setGmntElementid("-");
		
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntFlid()) )
			newPlmTlGenmaintenance.setGmntFlid("-");
	
		System.out.println(newPlmTlGenmaintenance.getGmntWostartdate()+" WORK START DATE");
		System.out.println(newPlmTlGenmaintenance.getGmntWoenddate()+" WORK END DATE");
		//String date6= newPlmTlGenmaintenance.getGmntWoenddate();
		//newPlmTlGenmaintenance.setGmntWoenddate(CommonFunctions.pg_getDateTimeFromDate(date6));
		String time6 = generalMaintainanceBean.getWorkendTime();
		System.out.println("Workend Time "+time);
		
		//String date2= newPlmTlGenmaintenance.getGmntOccureddate();
		if( UIUtils.isValidKeyId(generalMaintainanceBean.getWorkendTime()) ) 
		{
		String joinedWoenddate = newPlmTlGenmaintenance.getGmntWoenddate()+" "+generalMaintainanceBean.getWorkendTime()+":00";
		newPlmTlGenmaintenance.setGmntWoenddate(CommonFunctions.pg_getDateTimeFromTimeStamp(joinedWoenddate));
		}
		
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntWoenddate()) )
			newPlmTlGenmaintenance.setGmntWoenddate(Constants.pgPassNullDateTime);
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntWorkhours()) )
			newPlmTlGenmaintenance.setGmntWorkhours("0");
		
		String time7 = generalMaintainanceBean.getWorkstartTime();
		System.out.println("Workstart Time "+time7);
		
		//String date2= newPlmTlGenmaintenance.getGmntOccureddate();
		if( UIUtils.isValidKeyId(generalMaintainanceBean.getWorkstartTime()) ) 
		{
		String joinedWostartdate = newPlmTlGenmaintenance.getGmntWostartdate()+" "+generalMaintainanceBean.getWorkstartTime()+":00";
		newPlmTlGenmaintenance.setGmntWostartdate(CommonFunctions.pg_getDateTimeFromTimeStamp(joinedWostartdate));
		}
		//String date7= newPlmTlGenmaintenance.getGmntWostartdate();
		//newPlmTlGenmaintenance.setGmntWostartdate(CommonFunctions.pg_getDateTimeFromDate(date7));
		
		System.out.println(newPlmTlGenmaintenance.getGmntWostartdate()+" WORK START DATE TWO");
		System.out.println(newPlmTlGenmaintenance.getGmntWoenddate()+" WORK END DATE TWO");
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntWostartdate()) )
			newPlmTlGenmaintenance.setGmntWostartdate(Constants.pgPassNullDateTime);
		if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntYyno()) )
			newPlmTlGenmaintenance.setGmntYyno("0");
			
		
		return newPlmTlGenmaintenance;
	}

	@Override
	public BAL_PlmTlGenmaintenance delete(BAL_PlmTlGenmaintenance newPlmTlGenmaintenance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("delete");
		return GeneralMaintenanceServiceApi.delete(newPlmTlGenmaintenance);
		//return  plmTlGenmaintenanceDao.delete(newPlmTlGenmaintenance);
		
	}
	public BAL_BdmTlSetupadjsplit deleteSubLoss(BAL_BdmTlSetupadjsplit bdmTlSetupadjsplit) throws Exception{
		return  plmTlGenmaintenanceDao.deleteSubLoss(bdmTlSetupadjsplit);
	}
	@Override
	public List<String[]> getdataGenMain(CommonFilter commonFilter,String relto, String setupAdj) {
		// TODO Auto-generated method stub
		return this.plmTlGenmaintenanceDao.getdataGenMain(commonFilter,relto,setupAdj);
	}
	
	public List<String[]> getSetupAdjSubLoss(String refDocId) {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("servide Im");
		return this.plmTlGenmaintenanceDao.getSetupAdjSubLoss(refDocId);
	}

	@Override
	public BAL_PlmTlGenmaintenance getFillValue(String docno) throws Exception {
		// TODO Auto-generated method stub
		//return this.plmTlGenmaintenanceDao.getFillValue(docno);
		return GeneralMaintenanceServiceApi.getGenMaintenanceByKeyid(docno);

	}

	@Override
	public String getShift(List<String> paramValues) {
		// TODO Auto-generated method stub
			return this.plmTlGenmaintenanceDao.getShift(paramValues);		
		
	}
	@Override
	public Workbook genMaintainanaceExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat,String relto) throws Exception {
		// TODO Auto-generated method stub
		return this.plmTlGenmaintenanceDao.getGenMaintainanaceReport(commonFilter,colmodel,rptFormat,relto);
	}

	

	


}