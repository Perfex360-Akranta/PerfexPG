
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BdmTlDtlDao;
import com.akranta.tpm.dao.BdmTlMstDao;
import com.akranta.tpm.dao.BdmTlWhywhymstDao;
import com.akranta.tpm.dao.BreakdownDao;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.impl.BdmTlDtlDaoImpl;
import com.akranta.tpm.dao.impl.BdmTlMstDaoImpl;
import com.akranta.tpm.dao.impl.BdmTlWhywhymstDaoImpl;
import com.akranta.tpm.dao.impl.BreakdownDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.BdmTlMst;
import com.akranta.tpm.model.BdmTlNewphncausereq;
import com.akranta.tpm.model.BdmTlPhenomenamst;
import com.akranta.tpm.model.BdmTlShiftwisesplit;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlUnplannedmaintdtl;
import com.akranta.tpm.model.PlmTlUnplannedmaintmst;
import com.akranta.tpm.model.SapExternalRepair;
import com.akranta.tpm.model.SapExternalServiceDtl;
import com.akranta.tpm.model.SapExternalServiceMst;
import com.akranta.tpm.model.SapTlMaintenanceOrdermst;
import com.akranta.tpm.model.SapTlMaintenanceorder;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.BreakdownService;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.service.api.PcsEntryServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;


public  class BreakdownServiceImpl implements BreakdownService {

private BreakdownDao breakdownDao;
private BdmTlMstDao bdmTlMstDao;
private BdmTlDtlDao bdmTlDtlDao;
private BdmTlWhywhymstDao bdmTlWhywhymstDao;
//private BdmTlNewphncausereqDao bdmTlNewphncausereqDao;
//private BdmTlPhenomenamstDao bdmTlPhenomenamstDao;
private CommonFilterDao commonFilterDao;
private Validations validations ;


	
	public BreakdownServiceImpl(DBActionTemplate dbActionTemplate)
	{
		breakdownDao =  new BreakdownDaoImpl(dbActionTemplate);
		bdmTlMstDao = new BdmTlMstDaoImpl(dbActionTemplate);
		bdmTlDtlDao = new BdmTlDtlDaoImpl(dbActionTemplate);
		bdmTlWhywhymstDao = new BdmTlWhywhymstDaoImpl(dbActionTemplate);
		//bdmTlNewphncausereqDao = new BdmTlNewphncausereqDaoImpl(dbActionTemplate);
		//bdmTlPhenomenamstDao = new BdmTlPhenomenamstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void BreakdownServiceImplJwt(String JwtToken){
	    try{
	    	bdmTlMstDao.BdmTlMstDaoImplJwt(JwtToken);   // dao side
	        // (Optional) if you want service-level direct access
	     //    oplServiceApi = new OplTlMstServiceApi(JwtToken);
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	public BdmTlMst create(BdmTlMst newBdmTlMst,BdmTlMst oldBdmTlMst,  BDFormBean bdFormBean) throws ValidationExceptions,Exception {

		try {
			
			String validationsFor;
			
			//if(employeeBean.getFormActionMode() != null && employeeBean.getFormActionMode().equals("emp") )
			//	validationsFor = "emp";
			//else
				validationsFor = "create";
			if(UIUtils.isValidKeyId(newBdmTlMst.getBdmsRepeatedbdflag()))
			{
				CommonMessage.debugMsg("Inside service IMPL : "+newBdmTlMst.getBdmsRepeatedbdflag());
				if(newBdmTlMst.getBdmsRepeatedbdflag().equals("Y"))
				{
					CommonMessage.debugMsg("YES");
				}
				else
				{
					validations.validate(newBdmTlMst,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
					validations.validate(bdFormBean,"bdcreation",validationsFor);
				
			
					List<BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
					for(BdmTlDtl bdmTlDtl : bddtls )
					{
						validations.validate(bdmTlDtl,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
					}
				}
			}
			else
			{
				
					validations.validate(newBdmTlMst,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
					validations.validate(bdFormBean,"bdcreation",validationsFor);
				
			
					List<BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
					for(BdmTlDtl bdmTlDtl : bddtls )
					{
						validations.validate(bdmTlDtl,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
					}
				
			}
		
			fillValues(newBdmTlMst,oldBdmTlMst,bdFormBean);
			/*String phen = getPhenType(newBdmTlMst.getBdmsFinalphenomena());
			if(UIUtils.isValidKeyId(phen))
			{
				if(phen.equals("ND"))
					throw new BusinessApplicationExceptions("UndefinedPhen");
			}*/
			CommonMessage.debugMsg("After Filling Values");
			CommonMessage.debugMsg("Serv Impl BDMDETAIL Size : "+newBdmTlMst.getBdmDetail().size());
			return bdmTlMstDao.create(newBdmTlMst);		
			
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
	
	public BdmTlMst update(BdmTlMst newBdmTlMst,BdmTlMst oldBdmTlMst,
			BDFormBean bdFormBean,WomTlWomst womTlWomst) throws Exception 
	{
		String validationsFor = "create";
		CommonMessage.debugMsg("Inside the ServiceImpl u");
		if(UIUtils.isValidKeyId(newBdmTlMst.getBdmsRepeatedbdflag()))
		{
			CommonMessage.debugMsg("Inside service IMPL : "+newBdmTlMst.getBdmsRepeatedbdflag());
			if(newBdmTlMst.getBdmsRepeatedbdflag().equals("Y"))
			{
				CommonMessage.debugMsg("YES");
			}
			else
			{
				validations.validate(newBdmTlMst,"bdcreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				CommonMessage.debugMsg("After Validation in Mst S Upd");
				List<BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
				for(BdmTlDtl bdmTlDtl : bddtls )
				{
					 validations.validate(bdmTlDtl,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
				}
			}
		}
		else
		{
			
			validations.validate(newBdmTlMst,"bdcreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			
			CommonMessage.debugMsg("After Validation in Mst S Upd");
			List<BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
			for(BdmTlDtl bdmTlDtl : bddtls )
			{
				 validations.validate(bdmTlDtl,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
				 CommonMessage.debugMsg("Final Action in Service IMPL : "+bdmTlDtl.getBdanFinalaction() + " --------------> "+newBdmTlMst.getBdmsWoendflag() + " : "+newBdmTlMst.getBdmsWostartflag());
				 if(UIUtils.isValidKeyId(bdmTlDtl.getBdanFinalaction()))//if(UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoendflag()) && newBdmTlMst.getBdmsWoendflag().equals("Y")&&
				 {
					 validations.validate(newBdmTlMst,"bdcreation","FinalAction");
					 validations.validate(bdmTlDtl,"bdcreation","FinalAction");
				 }
			}
			
		}
		
		CommonMessage.debugMsg("After Update");
		fillValues(newBdmTlMst,oldBdmTlMst,bdFormBean);	
		if(UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoprodaccepflag()) && UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoendflag()))
		{
			if(newBdmTlMst.getBdmsWoprodaccepflag().equals("Y") && newBdmTlMst.getBdmsWoendflag().equals("Y"))				
			{
				 validations.validate(newBdmTlMst,"bdcreation","prodAcceptance");
			}
		}
		//if(newBdmTlMst.getBdmsWostartflag().equals("Y") && newBdmTlMst.getBdmsWoendflag().equals("Y"))
			//validations.validate(newBdmTlMst,"bdcreation","validDateBD");
		return bdmTlMstDao.update(newBdmTlMst,womTlWomst);
		
	}
	
	public BdmTlMst delete(BdmTlMst bdmTlMst)throws Exception 
	{
		return bdmTlMstDao.delete(bdmTlMst);
	}
	public WomTlCommunicationlog saveCommTxt(WomTlCommunicationlog newWomTlCommunicationlog,WomTlCommunicationlog oldWomTlCommunicationlog,  BDFormBean bdFormBean ) throws Exception 
	{
		fillComTxtValues(newWomTlCommunicationlog,oldWomTlCommunicationlog,bdFormBean);
		return bdmTlMstDao.saveCommTxt(newWomTlCommunicationlog);
		
	}
	public String deleteCommLog(String comLogKeyid) throws Exception  
	{
	
		return bdmTlMstDao.deleteCommLog(comLogKeyid);
		
	}

	public List<String[]> getAllBreakdown(CommonFilter commonFilter) throws Exception
	{
		return this.breakdownDao.getBreakdown(commonFilter);
	}
	public BdmTlMst select(String keyid) throws Exception {
		return this.bdmTlMstDao.select(keyid);
	}
	public BdmTlDtl selectBd(String keyid) throws Exception {
		return this.bdmTlDtlDao.selectBd(keyid);
	}
	public PlmTlUnplannedmaintmst selectUPM(String keyid) throws Exception {
		return this.bdmTlMstDao.selectUPM(keyid);
	}
	public PlmTlUnplannedmaintdtl selectUPMDetail(String keyid) throws Exception {
		return this.bdmTlMstDao.selectUPMDetail(keyid);
	}
	public BdmTlWhywhymst selectWhyWhy(String keyid) throws Exception
	{
		return this.bdmTlWhywhymstDao.select(keyid);
	}
	public List<String[]> getAllBD(CommonFilter commonFilter) throws Exception
	{
		CommonMessage.debugMsg("service impl");
		return this.bdmTlMstDao.getAllBD(commonFilter);
	}
	
	@Override
	public List<String[]> getAllBreakdownnew(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getAllBreakdownnew(commonFilter);
	}
	@Override
	public List<String[]> getSapInfoList(String wwNo) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getSapInfoList(wwNo);
	}
	@Override
	public List<String[]> getExtServiceList(CommonFilter commonFilter,  String ExtMasterId) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getExtServiceList(commonFilter,ExtMasterId);
	}
	@Override
	public List<String[]> getExtSubList(String wwNo) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getExtSubList(wwNo);
	}
	@Override
	public List<String[]> getExtRepairList(String wwNo) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getExtRepairList(wwNo);
	}
	public List<ComboBox> getComboShift(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("SFTM_CODE");
		comboFilter.setIdField("SFTM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<ComboBox> getMould(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("MLDM_DESCRIPTION");
		comboFilter.setIdField("MLDM_MOULDID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MOULDMST);
		if(UIUtils.isValidKeyId(condSql))
		{
			StringBuffer sb = new StringBuffer();
			sb.append("AND MLDM_MOULDID IN(SELECT MMLK_MOULDID FROM "+TableNames.TBL_GEN_TL_MOULDMACHINELINK);
			sb.append(" WHERE MMLK_MACHINEID = '"+condSql+"')");
			comboFilter.setCondSql(sb.toString());
		}
		return commonFilterDao.fillComboValues(comboFilter);					
	}
	public List<ComboBox> getRepeatedbdno(String condSql,String assmId,String eqpId,String idFlag,ComboFilter comboFilter) throws Exception
	{
//	public List<ComboBox> getRepeatedbdno(String condSql) throws Exception
	
		//ComboFilter comboFilter = new ComboFilter();
		
		if(UIUtils.isValidKeyId(idFlag))
		{
			comboFilter.setNameField("UPMM_REPORTEDDATE");
			comboFilter.setCodeField("UPMM_KEYID");
			comboFilter.setIdField("UPMM_KEYID");	
			comboFilter.setTableName("PLM_TL_UNPLANNEDMAINTMST");
			if(UIUtils.isValidKeyId(assmId))
			{
				condSql += "AND UPMM_ACTIVE ='Y' AND UPMM_STATUS <> 'D'";					
				condSql += " AND UPMM_ASSEMBLYID = '"+assmId+"'";
			}
			if(UIUtils.isValidKeyId(eqpId))
			{
				condSql += " AND UPMM_MACHINEID = '"+eqpId+"'";
				condSql += " AND  UPMM_KEYID <> '-99' AND LENGTH(UPMM_KEYID) >  10 AND UPMM_STATUS ='C' ";//AND UPMM_STATUS ='C'
				//condSQL += " AND  TO_CHAR(UPMM_REPORTEDDATE,'MON-YYYY') = '"+repDate.substring(3, 11)+"'";  
				//condSQL += " AND UPMM_REPORTEDDATE > TO_DATE('"+repDate+"','DD-MON-YYYY HH24:MI')";
				condSql += " AND UPMM_REPEATEDBDFLAG ='N'";
			}
			
		}
		else
		{
			comboFilter.setNameField("BDMS_REPORTEDDATE");
			comboFilter.setCodeField("BDMS_KEYID");
			comboFilter.setIdField("BDMS_KEYID");	
			comboFilter.setTableName(TableNames.TBL_BDM_TL_MST);
			if(UIUtils.isValidKeyId(assmId))
			{
				condSql += "AND BDMS_ACTIVE ='Y' AND BDMS_STATUS <> 'D'";					
				condSql += " AND BDMS_ASSEMBLYID = '"+assmId+"'";
			}
			if(UIUtils.isValidKeyId(eqpId))
			{
				condSql += " AND BDMS_MACHINEID = '"+eqpId+"'";
				condSql += " AND  BDMS_KEYID <> '-99' AND LENGTH(BDMS_KEYID) >  10 AND BDMS_STATUS ='C' ";//AND BDMS_STATUS ='C'
				//condSQL += " AND  TO_CHAR(BDMS_REPORTEDDATE,'MON-YYYY') = '"+repDate.substring(3, 11)+"'";  
				//condSQL += " AND BDMS_REPORTEDDATE > TO_DATE('"+repDate+"','DD-MON-YYYY HH24:MI')";
				condSql += " AND BDMS_REPEATEDBDFLAG ='N'";
			}
		}
		if (condSql.length() >0)
			comboFilter.setCondSql(condSql);
		
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getFinalTrade(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("TRDM_CODE");
		comboFilter.setNameField("TRDM_NAME");
		comboFilter.setIdField("TRDM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getAlarm(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("BALM_NAME");
		comboFilter.setIdField("BALM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BDM_TL_ALARMMST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getFailureType(String condSql,ComboFilter comboFilter) throws Exception
	{
		String condn = "";
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("FLTM_CODE");
		comboFilter.setNameField("FLTM_NAME");
		comboFilter.setIdField("FLTM_KEYID");
		comboFilter.setTableName(TableNames.TBL_BDM_TL_FAILURETYPEMST);
		if(UIUtils.isValidKeyId(condSql))
			condn = "AND FLTM_RELATEDTO = '"+condSql+"'";
		comboFilter.setCondSql(condn);
		CommonMessage.debugMsg(" get id failur ::::"+comboFilter.getIdField());
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getPhenomena(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		StringBuffer sb = new StringBuffer();
		if(!UIUtils.isValidKeyId(condSql))
			condSql = "{}";
		String[] condFlag = condSql.split("-");
		if(UIUtils.isValidKeyId(condFlag[0]))
		{
			if(condFlag[0].equals("MLD"))
			{
				comboFilter.setNameField("BPHM_PHENOMENANAME");
				comboFilter.setIdField("MPHL_BPHMID ");	
				comboFilter.setTableName(TableNames.TBL_GEN_TL_MOULDPHENLINK +","+TableNames.TBL_BDM_TL_PHENOMENAMST);
			}
			else
			{
				comboFilter.setNameField("PCT_DISPLAYCODE");
				comboFilter.setIdField("PCT_ORGINALID");	
				comboFilter.setTableName(TableNames.TBL_BDM_VW_PHENCASLAYOUT);
			}
		}
		else
		{
			comboFilter.setNameField("PCT_DISPLAYCODE");
			comboFilter.setIdField("PCT_ORGINALID");	
			comboFilter.setTableName(TableNames.TBL_BDM_VW_PHENCASLAYOUT);
			
		}
		
		//comboFilter.setCodeField("BPHM_PHENOMENATYPE");
		
		CommonMessage.debugMsg("Machine ID : "+condSql);
		
		/*if(UIUtils.isValidKeyId(condFlag[0]))
		{
			if(condFlag[0].equals("MLD"))
			{
				sb.append(" AND PCT_ORIGINALID IN (SELECT M
			}
		}*/
		if(UIUtils.isValidKeyId(condSql))
		{
			if(condFlag[0].equals("MLD"))
			{
				sb.append("AND MPHL_BPHMID = BPHM_KEYID AND MPHL_MOULDID = '"+condFlag[1]+"'");
			}
			else
			{
			sb.append(" AND PCT_ELEMENTID LIKE '%"+condSql+"%'");
			sb.append(" AND PCT_ELEMENTTYPE ='PHN'" );	
			}
		}
		else
			sb.append(" AND PCT_ELEMENTTYPE ='PHN'" );	
						
			sb.append(" UNION SELECT DISTINCT BPHM_KEYID id ,BPHM_PHENOMENANAME text  from "+TableNames.TBL_BDM_TL_PHENOMENAMST);
			sb.append(" where BPHM_PHENOMENANAME = 'NOT DEFINED'");
			comboFilter.setCondSql(sb.toString());
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getCause(String condSql,String phenId,String assmId,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("BCSM_CODE");
		//comboFilter.setNameField("BCSM_NAME");
		//comboFilter.setIdField("BCSM_KEYID");		
		comboFilter.setNameField("BPCL_DISPLAYCODE");
		comboFilter.setIdField("BPCL_ORIGINALID");	
		condSql = "AND BPCL_ELEMENTTYPE = 'CAS'";
		if (UIUtils.isValidKeyId(phenId))
		{
			condSql = "AND INSTR(BPCL_PARENTID, '"+phenId+"')>0 ";
		}
		if (UIUtils.isValidKeyId(assmId))
		{
			condSql = "AND INSTR(BPCL_PARENTID, '"+assmId+"')>0 ";
		}
			comboFilter.setCondSql(condSql); 
			
		//comboFilter.setTableName(TableNames.TBL_BDM_TL_CAUSEMST);
		comboFilter.setTableName(TableNames.TBL_BDM_TL_PHNCAUSELINK);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getBDClassification(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("TPMP_CODE");
		comboFilter.setNameField("BCLM_NAME");
		comboFilter.setIdField("BCLM_KEYID");	
		comboFilter.setTableName("BDM_VW_CLASSIFICATION");
		
		if (condSql.length() >0)
			comboFilter.setCondSql(condSql); 
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	
	public List<ComboBox> getSpare(String condSql,String assmId,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		
		comboFilter.setNameField("FNLN_DISPLAYCODE");
		comboFilter.setIdField("FNLN_ORIGINALID");	
		comboFilter.setTableName("gen_tl_functionallocn");
		condSql = "AND FNLN_ELEMENTTYPE = 'SPR'";
		if (UIUtils.isValidKeyId(assmId))
			condSql += "AND INSTR(FNLN_PARENTID, '"+assmId+"')>0 ";
			comboFilter.setCondSql(condSql); 
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	/*public List<String[]> getShift(List<String> paramValues)
	{		
		return this.bdmTlMstDao.getShift(paramValues);		
	}*/
	public List<String[]> getPillarClassfcn(String pillar) throws Exception
	{
		return this.bdmTlMstDao.getPillarClassfcn(pillar);
	}
	public String getShift(ShiftBean shiftBean)
	{		
		return this.bdmTlMstDao.getShift(shiftBean);		
	}
	public List<String[]> getDownTime(List<String> paramValues)
	{
		return this.bdmTlMstDao.getDownTime(paramValues);	
	}
	public List<String[]> getCommText(String bdId)
	{
		return this.bdmTlMstDao.getCommText(bdId);	
	}
	public List<String[]> getYY(String wwNo)
	{
		return this.bdmTlMstDao.getYY(wwNo);
	}
	public List<String[]> getRootCause(String wwNo) throws Exception
	{
		return this.bdmTlMstDao.getRootCause(wwNo);
	}
	public String getPhenType(String bookedPhen)
	{
		return this.bdmTlMstDao.getPhenType(bookedPhen);	
	}
	public Workbook breakdownExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.breakdownRpt(commonFilter,colmodel,rptFormat);
	}

private BdmTlMst fillValues(BdmTlMst newBdmTlMst,BdmTlMst oldBdmTlMst,BDFormBean bdFormBean) {
		
		newBdmTlMst.setBdmsActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("Time : "+bdFormBean.getBdmsReportedtime());
		CommonMessage.debugMsg("Key ID : "+newBdmTlMst.getBdmsKeyid());
		//CommonMessage.debugMsg("Old CreatedON : "+oldBdmTlMst.getBdmsCreatedon());
		/*if(UIUtils.isValidKeyId(oldBdmTlMst.getBdmsKeyid()) )			
		{	
			newBdmTlMst.setBdmsCreatedon(oldBdmTlMst.getBdmsCreatedon());			
		}					
		else
		{*/	
			//newBdmTlMst.setBdmsCreatedon(oldBdmTlMst.getBdmsCreatedon());
			newBdmTlMst.setBdmsCreatedon(dateTime);
		//}
		
		newBdmTlMst.setBdmsModifiedon(dateTime);
		CommonMessage.debugMsg("fill values:");
		
		if( newBdmTlMst.getBdmsEntrydate() == null )
			newBdmTlMst.setBdmsEntrydate(dateTime);		
		
		if( newBdmTlMst.getBdmsShiftid() == null )
			newBdmTlMst.setBdmsShiftid("{}");
		
		if( newBdmTlMst.getBdmsPartlocationid()== null )
			newBdmTlMst.setBdmsPartlocationid("{}");
		
		if( newBdmTlMst.getBdmsAlarmdescription()== null )
			newBdmTlMst.setBdmsAlarmdescription("{}");
		
		if( newBdmTlMst.getBdmsElementid()== null )
			newBdmTlMst.setBdmsElementid("{}");
		
		if( newBdmTlMst.getBdmsFlid()== null )
			newBdmTlMst.setBdmsFlid("{}");
		
		if( newBdmTlMst.getBdmsBdtype()== null )
			newBdmTlMst.setBdmsBdtype("E");
		
		if( newBdmTlMst.getBdmsReporteddate()== null )
			newBdmTlMst.setBdmsReporteddate(dateTime);
		
		
	if(!UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoprodaccepflag()))
		newBdmTlMst.setBdmsWoprodaccepflag("N");
	if(!UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoendflag()))
		newBdmTlMst.setBdmsWoendflag("N");
	if(!UIUtils.isValidKeyId(newBdmTlMst.getBdmsWostartflag()))
		newBdmTlMst.setBdmsWostartflag("N");
	if(!UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoallottedflag()))
		newBdmTlMst.setBdmsWoallottedflag("N");
	
	//if( newBdmTlMst.getBdmsProdaccepdate() == null )
		
	
	if(newBdmTlMst.getBdmsWoprodaccepflag().equals("Y"))
	{
		if( newBdmTlMst.getBdmsProdaccepdate()!= null )
		{			
			if(bdFormBean.getBdmsProdacceptime() != null)
			{
				CommonMessage.debugMsg("Received : "+newBdmTlMst.getBdmsProdaccepdate()+" "+bdFormBean.getBdmsProdacceptime());
				newBdmTlMst.setBdmsProdaccepdate(newBdmTlMst.getBdmsProdaccepdate()+" "+bdFormBean.getBdmsProdacceptime());
			}
		}
	}
	else
		newBdmTlMst.setBdmsProdaccepdate(Constants.futureNullDate);
	
		if( newBdmTlMst.getBdmsReporteddate()!= null )
		{			
			if(bdFormBean.getBdmsReportedtime() != null)
			{
				CommonMessage.debugMsg("Reported : "+newBdmTlMst.getBdmsReporteddate()+" "+bdFormBean.getBdmsReportedtime());
				newBdmTlMst.setBdmsReporteddate(newBdmTlMst.getBdmsReporteddate()+" "+bdFormBean.getBdmsReportedtime());
			}
		}
		
		
			
		if(newBdmTlMst.getBdmsWoallottedflag().equals("Y"))
		{
			if( newBdmTlMst.getBdmsReceiveddate()!= null )
			{			
				if(bdFormBean.getBdmsReceivedtime() != null)
				{
					CommonMessage.debugMsg("Received : "+newBdmTlMst.getBdmsReceiveddate()+" "+bdFormBean.getBdmsReceivedtime());
					newBdmTlMst.setBdmsReceiveddate(newBdmTlMst.getBdmsReceiveddate()+" "+bdFormBean.getBdmsReceivedtime());
				}
			}
		}
		else
			newBdmTlMst.setBdmsReceiveddate(dateTime);
			
		
	
			
		
		if(newBdmTlMst.getBdmsWostartflag().equals("Y"))
		{
			if( newBdmTlMst.getBdmsWostarttime()!= null )
			{			
				if(bdFormBean.getBdmsWostart() != null)
				{
					CommonMessage.debugMsg("Received getBdmsWostart: "+newBdmTlMst.getBdmsWostarttime()+" "+bdFormBean.getBdmsWostart());
					newBdmTlMst.setBdmsWostarttime(newBdmTlMst.getBdmsWostarttime()+" "+bdFormBean.getBdmsWostart());
				}
			}
		}
		else{
			newBdmTlMst.setBdmsWostarttime(Constants.passNullDate+" 00:00");
			CommonMessage.debugMsg("Received getBdmsWostart: date "+newBdmTlMst.getBdmsWostarttime());
		}
		if(newBdmTlMst.getBdmsWoendflag().equals("Y"))
		{
			if( newBdmTlMst.getBdmsWoendtime()!= null )
			{			
				if(bdFormBean.getBdmsWoend() != null)
				{
					CommonMessage.debugMsg("Received : "+newBdmTlMst.getBdmsWoendtime()+" "+bdFormBean.getBdmsWoend());
					newBdmTlMst.setBdmsWoendtime(newBdmTlMst.getBdmsWoendtime()+" "+bdFormBean.getBdmsWoend());
				}
			}
		}
		else
			newBdmTlMst.setBdmsWoendtime(Constants.futureNullDate+" 00:00");	
			

		if( newBdmTlMst.getBdmsBreaktime()== null )
			newBdmTlMst.setBdmsBreaktime("0");		

		//if( newBdmTlMst.getBdmsActualworktime()== null )
		//	newBdmTlMst.setBdmsActualworktime("0");		
		
		if( newBdmTlMst.getBdmsElementid()== null )
			newBdmTlMst.setBdmsElementid("{}");
		
		if( newBdmTlMst.getBdmsDowntime()== null )
			newBdmTlMst.setBdmsDowntime("0");
		
		if( newBdmTlMst.getBdmsPhenomenadescription()== null )
			newBdmTlMst.setBdmsPhenomenadescription("{}");
		
		if( newBdmTlMst.getBdmsBookedcause()== null )
			newBdmTlMst.setBdmsBookedcause("{}");
		
		if( newBdmTlMst.getBdmsFinalphenomena()== null )
			newBdmTlMst.setBdmsFinalphenomena("{}");
		
		if( newBdmTlMst.getBdmsFinalcause()== null )
			newBdmTlMst.setBdmsFinalcause("{}");
		
		if( !UIUtils.isValidKeyId(newBdmTlMst.getBdmsRepeatedbdflag()) )
			 newBdmTlMst.setBdmsRepeatedbdflag("N");
		
		if( newBdmTlMst.getBdmsBookedtrade()== null )
			newBdmTlMst.setBdmsBookedtrade("{}");
		
		if( newBdmTlMst.getBdmsFinaltrade()== null )
		{
			if( UIUtils.isValidKeyId(newBdmTlMst.getBdmsBookedtrade() ))
				newBdmTlMst.setBdmsFinaltrade(newBdmTlMst.getBdmsBookedtrade());
			else
				newBdmTlMst.setBdmsFinaltrade("{}");
		}
		
		
		if( newBdmTlMst.getBdmsProblemdescription()== null )
			newBdmTlMst.setBdmsProblemdescription("{}");
		
		if( newBdmTlMst.getBdmsIsbdlocked()== null )
			newBdmTlMst.setBdmsIsbdlocked("X");
		
		if( newBdmTlMst.getBdmsShiftincharge()== null )
			newBdmTlMst.setBdmsShiftincharge("{}");
		
		if( newBdmTlMst.getBdmsStatus()== null )
		{
			if( UIUtils.isValidKeyId(newBdmTlMst.getBdmsDowntime()) && !newBdmTlMst.getBdmsDowntime().equals("0"))
					newBdmTlMst.setBdmsStatus("C");
			else
					newBdmTlMst.setBdmsStatus("X");
		}
		
		if( newBdmTlMst.getBdmsBookedby()== null )
			newBdmTlMst.setBdmsBookedby("{}");
		
		if( newBdmTlMst.getBdmsRemarks() == null )
			newBdmTlMst.setBdmsRemarks("{}");
		
		if( newBdmTlMst.getBdmsBookingtype() == null )
			newBdmTlMst.setBdmsBookingtype("ONL");
		
		if( newBdmTlMst.getBdmsBdrelatedto()== null )
			newBdmTlMst.setBdmsBdrelatedto("XXX");
		
		if( newBdmTlMst.getBdmsWno() == null )
			newBdmTlMst.setBdmsWno("{}");
		
		if( newBdmTlMst.getBdmsSpareid() == null )
			newBdmTlMst.setBdmsSpareid("{}");
		
		if( newBdmTlMst.getBdmsPriority() == null )
			newBdmTlMst.setBdmsPriority("0");
		
		if( newBdmTlMst.getBdmsWoallottedflag() == null )
			newBdmTlMst.setBdmsWoallottedflag("X");
		
		if( newBdmTlMst.getBdmsWostartflag() == null )
			newBdmTlMst.setBdmsWostartflag("X");
		
		if( newBdmTlMst.getBdmsWoendflag() == null )
			newBdmTlMst.setBdmsWoendflag("X");

		
		
		if( newBdmTlMst.getBdmsSubassemblyid() == null )
			newBdmTlMst.setBdmsSubassemblyid("{}");
		
		if( newBdmTlMst.getBdmsRepeatedbdflag() == null )
			newBdmTlMst.setBdmsRepeatedbdflag("X");
		
		if( newBdmTlMst.getBdmsRepeatedbdno() == null )
			newBdmTlMst.setBdmsRepeatedbdno("{}");
	
		if( newBdmTlMst.getBdmsSectionid() == null )
			newBdmTlMst.setBdmsSectionid("{}");
		
		if( newBdmTlMst.getBdmsCellid() == null )
			newBdmTlMst.setBdmsCellid("{}");
		
		if( newBdmTlMst.getBdmsMachineid() == null )
			newBdmTlMst.setBdmsMachineid("{}");
		
		if( newBdmTlMst.getBdmsFinaltrade() == null )
			newBdmTlMst.setBdmsFinaltrade("{}");
		
		if( newBdmTlMst.getBdmsRelatedto() == null )
			newBdmTlMst.setBdmsRelatedto("{}");
		
		if( newBdmTlMst.getBdmsMould() == null )
			newBdmTlMst.setBdmsMould("{}");
		
		if( newBdmTlMst.getBdmsAssemblyid() == null )
			newBdmTlMst.setBdmsAssemblyid("{}");
		
		if( newBdmTlMst.getBdmsBookedphenomena() == null )
			newBdmTlMst.setBdmsBookedphenomena("{}");
		
		if( newBdmTlMst.getBdmsProcessid() == null )
			newBdmTlMst.setBdmsProcessid("{}");
		
		if( newBdmTlMst.getBdmsIsstandby() == null )
			newBdmTlMst.setBdmsIsstandby("N");
		
		if( newBdmTlMst.getBdmsStandbyequipment()== null )
			newBdmTlMst.setBdmsStandbyequipment("{}");
		
		if( newBdmTlMst.getBdmsBreakdowntime()== null )
			newBdmTlMst.setBdmsBreakdowntime("0");
		
		if( newBdmTlMst.getBdmsProductionstop()== null )
			newBdmTlMst.setBdmsProductionstop("-");
		
		
		if( newBdmTlMst.getBdmsImmediateaction()== null )
			newBdmTlMst.setBdmsImmediateaction("-");
		
		if( newBdmTlMst.getBdmsTempfield3()== null )
			newBdmTlMst.setBdmsTempfield3("-");
		
		if( newBdmTlMst.getBdmsTempfield4()== null )
			newBdmTlMst.setBdmsTempfield4("-");
		
		if( newBdmTlMst.getBdmsTempfield5()== null )
			newBdmTlMst.setBdmsTempfield5("-");
		
		if( newBdmTlMst.getBdmsTempfield6()== null )
			newBdmTlMst.setBdmsTempfield6("-");
		
		if( newBdmTlMst.getBdmsTempfield7()== null )
			newBdmTlMst.setBdmsTempfield7("-");
			
		newBdmTlMst.setBdmDetail(detailFillValues(newBdmTlMst,oldBdmTlMst,bdFormBean));
		newBdmTlMst.setBdmShiftwise(shiftFillValues(newBdmTlMst,oldBdmTlMst,bdFormBean));
			//CommonMessage.debugMsg("getBdmDetail "+	newBdmTlMst.getBdmDetail().get(0).toString());
		return newBdmTlMst; 
		
	}


private List<BdmTlDtl> detailFillValues(BdmTlMst newBdmTlMst,BdmTlMst oldBdmTlMst,BDFormBean bdFormBean) 
{
	CommonMessage.debugMsg("Detail 1");
	CommonMessage.debugMsg("Chk Box : " +bdFormBean.getIssparesY());
	CommonMessage.debugMsg("No : " +bdFormBean.getIssparesN());
	String dateTime = CommonFunctions.dateTimeNow();
	List<BdmTlDtl> newBdmTlDtl = newBdmTlMst.getBdmDetail();
	List<BdmTlDtl> oldBdmTlDtl = null;
	BdmTlDtl oldBdmTlDtls  = null;
	CommonMessage.debugMsg("Detail 2");
	if( oldBdmTlMst != null){
		oldBdmTlDtl = oldBdmTlMst.getBdmDetail();
		if( oldBdmTlDtl != null && oldBdmTlDtl.size() > 0 )
		{
			oldBdmTlDtls = oldBdmTlDtl.get(0);
		}
	}	
	List<BdmTlDtl> newBdmTlDtlList = new ArrayList<BdmTlDtl>();
	for( BdmTlDtl bdmTlDtl :newBdmTlDtl)
	{	
		if(bdmTlDtl.getBdanKeyid() == null )			
		{	
			bdmTlDtl.setBdanCreatedon(dateTime);
		}	
		else{
			//bdmTlDtl.setBdanCreatedon(oldBdmTlDtls.getBdanCreatedon());
			bdmTlDtl.setBdanCreatedon(dateTime);
		}
		
		bdmTlDtl.setBdanModifiedon(dateTime);
		bdmTlDtl.setBdanActive("Y");
		bdmTlDtl.setBdanCreatedby(newBdmTlMst.getBdmsCreatedby());
	//	bdmTlDtl.setBdanBdms_keyid(newBdmTlMst.getBdmsKeyid());

		//genTlEmployeedtl.setEmpdModifiedon(dateTime);
		if( bdmTlDtl.getBdanFinalphenomena() == null )
			bdmTlDtl.setBdanFinalphenomena("{}");
		
		if( bdmTlDtl.getBdanFinalcause() == null )
			bdmTlDtl.setBdanFinalcause("{}");
		
		if( bdmTlDtl.getBdanTradeid() == null )
			bdmTlDtl.setBdanTradeid("{}");
		
		if( bdmTlDtl.getBdanFinalaction() == null )
			bdmTlDtl.setBdanFinalaction("{}");
		
		if( bdmTlDtl.getBdanCountermeasure() == null )
			bdmTlDtl.setBdanCountermeasure("{}");
		
		if( bdmTlDtl.getBdanWwrequired() == null )
		{
			if(UIUtils.isValidKeyId(bdmTlDtl.getBdanWwno()))
				bdmTlDtl.setBdanWwrequired("Y");
			else
				bdmTlDtl.setBdanWwrequired("N");
		}
		
		if( !UIUtils.isValidKeyId(bdmTlDtl.getBdanWwno()))
			bdmTlDtl.setBdanWwno("{}");
		
		if( bdmTlDtl.getBdanRootcause() == null )
			bdmTlDtl.setBdanRootcause("{}");
		
		if( bdmTlDtl.getBdanPreventivemeasure() == null )
			bdmTlDtl.setBdanPreventivemeasure("{}");
		
		if( bdmTlDtl.getBdanRootcauseid() == null )
			bdmTlDtl.setBdanRootcauseid("{}");
		
		if( bdmTlDtl.getBdanCountermeasureid() == null )
			bdmTlDtl.setBdanCountermeasureid("{}");
		
		if( bdmTlDtl.getBdanPreventivemeasureid() == null )
			bdmTlDtl.setBdanPreventivemeasureid("{}");
		
		if( bdmTlDtl.getBdanBreakdowntime() == null )
			bdmTlDtl.setBdanBreakdowntime("0");
		
		if( bdmTlDtl.getBdanWorktime() == null )
			bdmTlDtl.setBdanWorktime("0");
		
		if( bdmTlDtl.getBdanCategoryid() == null )
			bdmTlDtl.setBdanCategoryid("{}");
		
	CommonMessage.debugMsg("spares replaced +++++++++"+bdFormBean.getIssparesY());
	
	/*	bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesY());
			
		if(bdmTlDtl.getBdanIssparesreplaced() == null)
			bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesN());
		
		if(bdmTlDtl.getBdanIssparesreplaced() == null)
			bdmTlDtl.setBdanIssparesreplaced("X");*/
		bdmTlDtl.setBdanIssparesreplaced("X");
		if(UIUtils.isValidKeyId(bdFormBean.getIssparesY()))
			bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesY());
		if(UIUtils.isValidKeyId(bdFormBean.getIssparesN()))
			bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesN());
		if(UIUtils.isValidKeyId(bdFormBean.getIssparesW()))
			bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesW());
		

		CommonMessage.debugMsg("spares replaced ;lllllllll"+bdmTlDtl.getBdanIssparesreplaced());

		if( bdmTlDtl.getBdanAlarmno() == null )
			bdmTlDtl.setBdanAlarmno("{}");
		
		if( bdmTlDtl.getBdanManpowercost() == null )
			bdmTlDtl.setBdanManpowercost("0");
		
		if( bdmTlDtl.getBdanContractorcost() == null )
			bdmTlDtl.setBdanContractorcost("0");
		
		if( bdmTlDtl.getBdanSparescost() == null )
			bdmTlDtl.setBdanSparescost("0");
		
		if( bdmTlDtl.getBdanOthercost() == null )
			bdmTlDtl.setBdanOthercost("0");
		
		if( bdmTlDtl.getBdanStatus() == null )
			bdmTlDtl.setBdanStatus("X");
		
		if( bdmTlDtl.getBdanRemarks() == null )
		{
			if(UIUtils.isValidKeyId(newBdmTlMst.getBdmsRemarks()))
				bdmTlDtl.setBdanRemarks(newBdmTlMst.getBdmsRemarks());
			else
				bdmTlDtl.setBdanRemarks("{}");
		}
			
		
		if( bdmTlDtl.getBdanErppoststatus() == null )
			bdmTlDtl.setBdanErppoststatus("X");
		CommonMessage.debugMsg("Status In Service Impl : "+bdmTlDtl.getBdanErppoststatus());
		if( bdmTlDtl.getBdanErppoststatus() != null )
		{
			if( bdmTlDtl.getBdanErppoststatus().equals("BOOKING"))
				bdmTlDtl.setBdanErppoststatus("X");
			else if( bdmTlDtl.getBdanErppoststatus().equals("BOOKED"))
				bdmTlDtl.setBdanErppoststatus("B");
			else if( bdmTlDtl.getBdanErppoststatus().equals("ALLOTTED"))
				bdmTlDtl.setBdanErppoststatus("A");
			else if( bdmTlDtl.getBdanErppoststatus().equals("WORK IN PROGRESS"))
				bdmTlDtl.setBdanErppoststatus("W");
			else
				bdmTlDtl.setBdanErppoststatus("C");
		}
			
		CommonMessage.debugMsg("Status In Service Impl : "+bdmTlDtl.getBdanErppoststatus());
		if( bdmTlDtl.getBdanProblemseverity() == null )
			bdmTlDtl.setBdanProblemseverity("X");
		
		if( bdmTlDtl.getBdanErpnumber() == null )
			bdmTlDtl.setBdanErpnumber("0");
		
		if( bdmTlDtl.getBdanIsapproved() == null )
			bdmTlDtl.setBdanIsapproved("X");
		
		if( bdmTlDtl.getBdanApproverdby() == null )
			bdmTlDtl.setBdanApproverdby("{}");
		
		if( bdmTlDtl.getBdanCostcentre() == null )
			bdmTlDtl.setBdanCostcentre("{}");
		
		if( bdmTlDtl.getBdanFailuretype() == null )
			bdmTlDtl.setBdanFailuretype("{}");
		
		if( bdmTlDtl.getBdanClassificationid() == null )
			bdmTlDtl.setBdanClassificationid("{}");
		
		if( bdmTlDtl.getBdanActiontakenby() == null )
			bdmTlDtl.setBdanActiontakenby("{}");
		
		if( bdmTlDtl.getBdanCompletedby() == null )
			bdmTlDtl.setBdanCompletedby("{}");
	
		
		newBdmTlDtlList.add(bdmTlDtl);
	}
	
	return newBdmTlDtlList;
}

private List<BdmTlShiftwisesplit> shiftFillValues(BdmTlMst newBdmTlMst,BdmTlMst oldBdmTlMst,BDFormBean bdFormBean) 
{
	CommonMessage.debugMsg("Shift Fill Values");
	String dateTime = CommonFunctions.dateTimeNow();
	List<BdmTlShiftwisesplit> newBdmTlShiftwisesplit = newBdmTlMst.getBdmShiftwise();
	List<BdmTlShiftwisesplit> oldBdmTlShiftwisesplit = null;
	BdmTlShiftwisesplit oldBdmTlShiftwisesplitValues  = null;
	if( oldBdmTlMst != null){		
		oldBdmTlShiftwisesplit = oldBdmTlMst.getBdmShiftwise();
		
		if( oldBdmTlShiftwisesplit != null && oldBdmTlShiftwisesplit.size() > 0 )
		{
			oldBdmTlShiftwisesplitValues = oldBdmTlShiftwisesplit.get(0);
			
		}
	}
	List<BdmTlShiftwisesplit> newBdmTlShiftwiseList = new ArrayList<BdmTlShiftwisesplit>();
	for( BdmTlShiftwisesplit bdmTlShiftwisesplit :newBdmTlShiftwisesplit)
	{			
		if(bdmTlShiftwisesplit.getBdssKeyid() == null )			
		{				
			bdmTlShiftwisesplit.setBdssCreatedon(dateTime);
		}	
		else{
			bdmTlShiftwisesplit.setBdssCreatedon(dateTime);
		}
		
		bdmTlShiftwisesplit.setBdssModifiedon(dateTime);
		bdmTlShiftwisesplit.setBdssActive("Y");
		bdmTlShiftwisesplit.setBdssCreatedby(newBdmTlMst.getBdmsCreatedby());
		
		if( bdmTlShiftwisesplit.getBdssFactoryid() == null )
		{
			if(newBdmTlMst.getBdmsFactoryid() != null)
				bdmTlShiftwisesplit.setBdssFactoryid(newBdmTlMst.getBdmsFactoryid());
			else
				bdmTlShiftwisesplit.setBdssFactoryid("{}");
		}
		if( bdmTlShiftwisesplit.getBdssSectionid() == null )
		{
			if(newBdmTlMst.getBdmsSectionid()!= null)
				bdmTlShiftwisesplit.setBdssSectionid(newBdmTlMst.getBdmsSectionid());
			else
				bdmTlShiftwisesplit.setBdssSectionid("{}");
		}
		if( bdmTlShiftwisesplit.getBdssCellid() == null )
		{
			if(newBdmTlMst.getBdmsCellid()!= null)
				bdmTlShiftwisesplit.setBdssCellid(newBdmTlMst.getBdmsCellid());
			else
				bdmTlShiftwisesplit.setBdssCellid("{}");
		}
		if( bdmTlShiftwisesplit.getBdssMachineid() == null )
		{
			if(newBdmTlMst.getBdmsMachineid()!= null)
				bdmTlShiftwisesplit.setBdssMachineid(newBdmTlMst.getBdmsMachineid());
			else
				bdmTlShiftwisesplit.setBdssMachineid("{}");
		}
		if( bdmTlShiftwisesplit.getBdssAssemblyid() == null )
		{
			if(newBdmTlMst.getBdmsAssemblyid()!= null)
				bdmTlShiftwisesplit.setBdssAssemblyid(newBdmTlMst.getBdmsAssemblyid());
			else
				bdmTlShiftwisesplit.setBdssAssemblyid("{}");
		}
		if( bdmTlShiftwisesplit.getBdssPhenomenaid() == null )
		{
			if(newBdmTlMst.getBdmsBookedphenomena()!= null)
				bdmTlShiftwisesplit.setBdssPhenomenaid(newBdmTlMst.getBdmsBookedphenomena());
			else
				bdmTlShiftwisesplit.setBdssPhenomenaid("{}");
		}
		if( bdmTlShiftwisesplit.getBdssCauseid() == null )
		{
			if(newBdmTlMst.getBdmsBookedcause()!= null)
				bdmTlShiftwisesplit.setBdssCauseid(newBdmTlMst.getBdmsBookedcause());
			else
				bdmTlShiftwisesplit.setBdssCauseid("{}");
		}
		if( bdmTlShiftwisesplit.getBdssBdentrydate() == null )
		{
			if(newBdmTlMst.getBdmsReporteddate()!= null)
				bdmTlShiftwisesplit.setBdssBdentrydate(newBdmTlMst.getBdmsReporteddate());
			else
				bdmTlShiftwisesplit.setBdssBdentrydate(dateTime);
		}
		if( bdmTlShiftwisesplit.getBdssBdsplitdate() == null )
			bdmTlShiftwisesplit.setBdssBdsplitdate(dateTime);
		if( bdmTlShiftwisesplit.getBdssShiftid() == null )
			bdmTlShiftwisesplit.setBdssShiftid("{}");
		if( bdmTlShiftwisesplit.getBdssBdno() == null )
			bdmTlShiftwisesplit.setBdssBdno("{}");
		if( bdmTlShiftwisesplit.getBdssDowntime() == null )
			bdmTlShiftwisesplit.setBdssDowntime("{}");
		if( bdmTlShiftwisesplit.getBdssNoplantime() == null )
			bdmTlShiftwisesplit.setBdssNoplantime("{}");
		if( bdmTlShiftwisesplit.getBdssRelatedto() == null )
			bdmTlShiftwisesplit.setBdssRelatedto("XXX");
		if( bdmTlShiftwisesplit.getBdssActivityno() == null )
			bdmTlShiftwisesplit.setBdssActivityno("{}");
		
		newBdmTlShiftwiseList.add(bdmTlShiftwisesplit);
	}
	return newBdmTlShiftwiseList;
	
}	

private BdmTlNewphncausereq fillPhenValues(BdmTlNewphncausereq newBdmTlNewphncausereq,BdmTlNewphncausereq oldBdmTlNewphncausereq, BDFormBean bdFormBean) {
	
	String[] bdDetails = null;
	CommonMessage.debugMsg(bdFormBean.getBphmBddetails());
	newBdmTlNewphncausereq.setBnprActive("Y");
	String dateTime = CommonFunctions.dateTimeNow();
	if(newBdmTlNewphncausereq.getBnprKeyid() == null )			
	{	
		newBdmTlNewphncausereq.setBnprCreatedon(dateTime);			
	}					
	else
	{
		newBdmTlNewphncausereq.setBnprCreatedon(dateTime);
	}
	
	newBdmTlNewphncausereq.setBnprModifiedon(dateTime);
	if(bdFormBean.getBphmBddetails() != null)
	{
		bdDetails = bdFormBean.getBphmBddetails().split(">");
	}
	
	if( newBdmTlNewphncausereq.getBnprRequesteddate() == null )
		newBdmTlNewphncausereq.setBnprRequesteddate(dateTime);	
	if( newBdmTlNewphncausereq.getBnprRequestedby() == null )
		newBdmTlNewphncausereq.setBnprRequestedby(newBdmTlNewphncausereq.getBnprCreatedby());	
	if( newBdmTlNewphncausereq.getBnprDocno() == null )
	{
		if(bdFormBean.getBphmBddetails() != null)
			newBdmTlNewphncausereq.setBnprDocno(bdDetails[0]);
		else
			newBdmTlNewphncausereq.setBnprDocno("{}");
	}
	if( newBdmTlNewphncausereq.getBnprDocdate() == null )
	{
		if(bdFormBean.getBphmBddetails() != null)
			newBdmTlNewphncausereq.setBnprDocdate(bdDetails[1]);
		else
			newBdmTlNewphncausereq.setBnprDocdate(dateTime);
	}
	if( newBdmTlNewphncausereq.getBnprPhenomenatype() == null )
		newBdmTlNewphncausereq.setBnprPhenomenatype("BD");	
	if( newBdmTlNewphncausereq.getBnprProposedphn() == null )
		newBdmTlNewphncausereq.setBnprProposedphn("{}");	
	if( newBdmTlNewphncausereq.getBnprIscausereq() == null )
		newBdmTlNewphncausereq.setBnprIscausereq("Y");	
	if( newBdmTlNewphncausereq.getBnprProposedcause() == null )
		newBdmTlNewphncausereq.setBnprProposedcause("{}");
	if( newBdmTlNewphncausereq.getBnprApprovedphn() == null )
	{
		if(bdFormBean.getBphmAssemblyid() != null)
			newBdmTlNewphncausereq.setBnprApprovedphn(bdFormBean.getBphmAssemblyid());
		else
			newBdmTlNewphncausereq.setBnprApprovedphn("{}");
	}
	if( newBdmTlNewphncausereq.getBnprIscausereqapp() == null )
		newBdmTlNewphncausereq.setBnprIscausereqapp("N");
	if( newBdmTlNewphncausereq.getBnprApprovedcause() == null )
		newBdmTlNewphncausereq.setBnprApprovedcause("{}");
	if( newBdmTlNewphncausereq.getBnprOldphenomenaid() == null )
		newBdmTlNewphncausereq.setBnprOldphenomenaid("{}");
	if( newBdmTlNewphncausereq.getBnprOldcauseid() == null )
		newBdmTlNewphncausereq.setBnprOldcauseid("{}");
	if( newBdmTlNewphncausereq.getBnprNewphenomenaid() == null )
		newBdmTlNewphncausereq.setBnprNewphenomenaid("{}");
	if( newBdmTlNewphncausereq.getBnprNewcauseid() == null )
		newBdmTlNewphncausereq.setBnprNewcauseid("{}");
	if( newBdmTlNewphncausereq.getBnprIsalreadyexist() == null )
		newBdmTlNewphncausereq.setBnprIsalreadyexist("N");
	if( newBdmTlNewphncausereq.getBnprApprovedby() == null )
		newBdmTlNewphncausereq.setBnprApprovedby("{}");
	if( newBdmTlNewphncausereq.getBnprApproveddate() == null )
		newBdmTlNewphncausereq.setBnprApproveddate(dateTime);
	if( newBdmTlNewphncausereq.getBnprApprovedflag()== null )
		newBdmTlNewphncausereq.setBnprApprovedflag("N");
	  
	return newBdmTlNewphncausereq;
}


private BdmTlPhenomenamst fillPhenomenaValues(BdmTlPhenomenamst newBdmTlPhenomenamst,BdmTlPhenomenamst oldBdmTlPhenomenamst, BDFormBean bdFormBean) {
	
	newBdmTlPhenomenamst.setBphmActive("Y");
	String dateTime = CommonFunctions.dateTimeNow();
	if(newBdmTlPhenomenamst.getBphmKeyid() == null )			
	{	
		newBdmTlPhenomenamst.setBphmCreatedon(dateTime);			
	}					
	else
	{
		newBdmTlPhenomenamst.setBphmCreatedon(dateTime);
	}	
	newBdmTlPhenomenamst.setBphmModifiedon(dateTime);
	
	if(newBdmTlPhenomenamst.getBphmPhenomenatype() == null)
		newBdmTlPhenomenamst.setBphmPhenomenatype("BD");
	if(newBdmTlPhenomenamst.getBphmPhenomenaname() == null)
		newBdmTlPhenomenamst.setBphmPhenomenaname("{}");
	if(newBdmTlPhenomenamst.getBphmShortname() == null)
		newBdmTlPhenomenamst.setBphmShortname("{}");
	if(newBdmTlPhenomenamst.getBphmRemarks()== null)
		newBdmTlPhenomenamst.setBphmRemarks("{}");
	if(newBdmTlPhenomenamst.getBphmAssemblyid()== null)
		newBdmTlPhenomenamst.setBphmAssemblyid("{}");
	if(newBdmTlPhenomenamst.getBphmLevelno()== null)
		newBdmTlPhenomenamst.setBphmLevelno("1");
	if(newBdmTlPhenomenamst.getBphmIsphnnotdefined()== null)
		newBdmTlPhenomenamst.setBphmIsphnnotdefined("N");
	if(newBdmTlPhenomenamst.getBphmCausenotneeded()== null)
		newBdmTlPhenomenamst.setBphmCausenotneeded("Y");
	if(newBdmTlPhenomenamst.getBphmChildflag()== null)
		newBdmTlPhenomenamst.setBphmChildflag("N");
	
	return newBdmTlPhenomenamst;

}

private WomTlCommunicationlog fillComTxtValues(WomTlCommunicationlog newWomTlCommunicationlog,WomTlCommunicationlog oldWomTlCommunicationlog,BDFormBean bdFormBean) {
	// TODO Auto-generated method stub
	newWomTlCommunicationlog.setWcmlActive("Y");
	String dateTime = CommonFunctions.dateTimeNow();	
	newWomTlCommunicationlog.setWcmlCreatedon(dateTime);	
	newWomTlCommunicationlog.setWcmlModifiedon(dateTime);
	newWomTlCommunicationlog.setWcmlDate(dateTime);
	
	if(newWomTlCommunicationlog.getWcmlWonumber()== null)
		newWomTlCommunicationlog.setWcmlWonumber("{}");
	if(newWomTlCommunicationlog.getWcmlCommunicationtext()== null)
		newWomTlCommunicationlog.setWcmlCommunicationtext("{}");
	if(newWomTlCommunicationlog.getWcmlEnteredby()== null)
		newWomTlCommunicationlog.setWcmlEnteredby("{}");
	//if(newWomTlCommunicationlog.getWcmlLevel()== null)
		//newWomTlCommunicationlog.setWcmlLevel("INFORMATION TECHNOLOGY");
	if(newWomTlCommunicationlog.getWcmlDisplayorderno()== null)
		newWomTlCommunicationlog.setWcmlDisplayorderno("2");
	
	
	CommonMessage.debugMsg("1 "+newWomTlCommunicationlog.getWcmlCreatedon());
	CommonMessage.debugMsg(newWomTlCommunicationlog.getWcmlLevel());
	return newWomTlCommunicationlog;
}

@Override
public SapExternalServiceMst createExtService(
		SapExternalServiceMst newSapExternalServiceMst,
		SapExternalServiceMst existSapExternalServiceMst, BDFormBean bdFormBean)
		throws Exception {
	// TODO Auto-generated method stub
	try {
		
			String validationsFor;
			validationsFor = "create";
		 	validations.validate(newSapExternalServiceMst,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		    fillValuesExterServices(newSapExternalServiceMst,existSapExternalServiceMst,bdFormBean);
		    CommonMessage.debugMsg("ceatedBYService  "+newSapExternalServiceMst.getExtmActive());
		    CommonMessage.debugMsg("After Filling Values ExterServices");
		
	}catch (ValidationExceptions e){			
		CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
		throw new ValidationExceptions(e.getMessage());
	}	
	return bdmTlMstDao.createExtService(newSapExternalServiceMst);
}

private SapExternalServiceMst fillValuesExterServices(
		SapExternalServiceMst newSapExternalServiceMst,
		SapExternalServiceMst existSapExternalServiceMst, BDFormBean bdFormBean) {
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("Key ID : "+newSapExternalServiceMst.getExtmKeyid());
		if(UIUtils.isValidKeyId(existSapExternalServiceMst.getExtmKeyid()) )			
		{	
			newSapExternalServiceMst.setExtmCreatedon(existSapExternalServiceMst.getExtmCreatedon());			
		}					
		else
		{	
			newSapExternalServiceMst.setExtmCreatedon(dateTime);
		 }
		newSapExternalServiceMst.setExtmModifiedon(dateTime);
		newSapExternalServiceMst.setExtmActive("Y");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmAgreement()))
	    	newSapExternalServiceMst.setExtmAgreement("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmCostElement()))
	    	newSapExternalServiceMst.setExtmCostElement("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmDate()))
	    	newSapExternalServiceMst.setExtmDate(Constants.passNullDate);
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmEquipment()))
	    	newSapExternalServiceMst.setExtmEquipment("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmExtSubContract()))
	    	newSapExternalServiceMst.setExtmExtSubContract("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmFlid()))
	    	newSapExternalServiceMst.setExtmFlid("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmFwOrder()))
	    	newSapExternalServiceMst.setExtmFwOrder("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmInfoRecord()))
	    	newSapExternalServiceMst.setExtmInfoRecord("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmMaterialGroup()))
	    	newSapExternalServiceMst.setExtmMaterialGroup("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmNotificationno()))
	    	newSapExternalServiceMst.setExtmNotificationno("0");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmOperationQty()))
	    	newSapExternalServiceMst.setExtmOperationQty("0");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmOrderno()))
	    	newSapExternalServiceMst.setExtmOrderno("0");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPer()))
	    	newSapExternalServiceMst.setExtmPer("0");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPlanDeliverytime()))
	    	newSapExternalServiceMst.setExtmPlanDeliverytime(Constants.futureNullDate);
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPrice()))
	    	newSapExternalServiceMst.setExtmPrice("0");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPurchaseGroup()))
	    	newSapExternalServiceMst.setExtmPurchaseGroup("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmRecipient()))
	    	newSapExternalServiceMst.setExtmRecipient("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmRequisitioner()))
	    	newSapExternalServiceMst.setExtmRequisitioner("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmShift()))
	    	newSapExternalServiceMst.setExtmShift("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmSortterm()))
	    	newSapExternalServiceMst.setExtmSortterm("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmTrackNo()))
	    	newSapExternalServiceMst.setExtmTrackNo("0");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmType()))
	    	newSapExternalServiceMst.setExtmType("N");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmUnloadPoint()))
	    	newSapExternalServiceMst.setExtmUnloadPoint("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmVendor()))
	    	newSapExternalServiceMst.setExtmVendor("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmTaskId()))
	    	newSapExternalServiceMst.setExtmTaskId("{}");
	    if(!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPurchaseOrg()))
	    	newSapExternalServiceMst.setExtmPurchaseOrg("{}");

   
	// TODO Auto-generated method stub
	    return newSapExternalServiceMst;
}

@Override
public SapExternalServiceMst updateExtService(
		SapExternalServiceMst newSapExternalServiceMst,
		SapExternalServiceMst existSapExternalServiceMst, BDFormBean bdFormBean)
		throws Exception {
	// TODO Auto-generated method stub
	try {
		
		String validationsFor;
		validationsFor = "create";
	 	validations.validate(newSapExternalServiceMst,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
	    fillValuesExterServices(newSapExternalServiceMst,existSapExternalServiceMst,bdFormBean);
	    
	    CommonMessage.debugMsg("After Update Filling Values ExterServices");
	
}catch (ValidationExceptions e){			
	CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
	throw new ValidationExceptions(e.getMessage());
}	
return bdmTlMstDao.updateExtService(newSapExternalServiceMst);
}

@Override
public SapExternalServiceMst getExtServiceData(String extKeyid) throws Exception {
	// TODO Auto-generated method stub
	return bdmTlMstDao.getExtServiceData(extKeyid) ;
}

@Override
public SapExternalServiceDtl createExtServiceDtl(
		SapExternalServiceDtl newSapExternalServiceDtl,
		SapExternalServiceDtl existSapExternalServiceDtl, BDFormBean bdFormBean)
		throws Exception {
	// TODO Auto-generated method stub
	try {
		
		String validationsFor;
		validationsFor = "create";
	 	validations.validate(newSapExternalServiceDtl,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
	    fillValuesExterServicesDtl(newSapExternalServiceDtl,existSapExternalServiceDtl,bdFormBean);
	    CommonMessage.debugMsg("ceatedBYService  "+newSapExternalServiceDtl.getExtdCreatedby());
	    CommonMessage.debugMsg("After Filling Values ExterServices");
	
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		return bdmTlMstDao.createExtServiceDtl(newSapExternalServiceDtl);

}

private SapExternalServiceDtl fillValuesExterServicesDtl( SapExternalServiceDtl newSapExternalServiceDtl,
		SapExternalServiceDtl existSapExternalServiceDtl, BDFormBean bdFormBean) {
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("Key ID : "+newSapExternalServiceDtl.getExtdKeyid());
		if(UIUtils.isValidKeyId(existSapExternalServiceDtl.getExtdKeyid()) )			
		{	
			newSapExternalServiceDtl.setExtdCreatedon(existSapExternalServiceDtl.getExtdCreatedon());			
		}					
		else
		{	
			newSapExternalServiceDtl.setExtdCreatedon(dateTime);
		  }
		newSapExternalServiceDtl.setExtdModifiedon(dateTime);
		newSapExternalServiceDtl.setExtdActive("Y");
		if(!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdCostElement()) )			
		{
			newSapExternalServiceDtl.setExtdCostElement("{}");
		}
		if(!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdCurrency()) )			
		{
			newSapExternalServiceDtl.setExtdCurrency("{}");
		}
		if(!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdQty()) )			
		{
			newSapExternalServiceDtl.setExtdQty("0");
		}
		if(!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdServiceNo()) )			
		{
			newSapExternalServiceDtl.setExtdServiceNo("{}");
		}
		if(!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdServiceText()) )			
		{
			newSapExternalServiceDtl.setExtdServiceText("{}");
		}
		if(!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdUom()) )			
		{
			newSapExternalServiceDtl.setExtdUom("{}");
		}
		if(!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdValue()) )			
		{
			newSapExternalServiceDtl.setExtdValue("0");
		}
		if(!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdLineNo()) )			
		{
			newSapExternalServiceDtl.setExtdLineNo("{}");
		}
		if(!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdTotalPrice()) )			
		{
			newSapExternalServiceDtl.setExtdTotalPrice("-");
		}
		
		newSapExternalServiceDtl.setExtdTempField2("-");
		newSapExternalServiceDtl.setExtdTempField3("-");
     return newSapExternalServiceDtl;
	// TODO Auto-generated method stub
	
}

@Override
public SapExternalServiceDtl updateExtServiceDtl(
		SapExternalServiceDtl newSapExternalServiceDtl,
		SapExternalServiceDtl existSapExternalServiceDtl, BDFormBean bdFormBean)
		throws Exception {
	// TODO Auto-generated method stub
try {
		
		String validationsFor;
		validationsFor = "upddate";
	 	validations.validate(newSapExternalServiceDtl,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
	    fillValuesExterServicesDtl(newSapExternalServiceDtl,existSapExternalServiceDtl,bdFormBean);
	    CommonMessage.debugMsg("ceatedBYService  "+newSapExternalServiceDtl.getExtdCreatedby());
	    CommonMessage.debugMsg("After Filling Values ExterServices");
	
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		return bdmTlMstDao.UpdateExtServiceDtl(newSapExternalServiceDtl);

}

@Override
public SapExternalServiceDtl getserviceDtlData(String detailKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	return bdmTlMstDao.getServiceDtlData(detailKeyid);
}

@Override
public String delteExtDetail(String detailKeyid) throws Exception {
	// TODO Auto-generated method stub
	return bdmTlMstDao.delteExtDetail(detailKeyid) ;
}

@Override
public SapExternalRepair createExtRepairDtl(
		SapExternalRepair newSapExternalRepair,
		SapExternalRepair existSapExternalRepair, BDFormBean bdFormBean)
		throws Exception {
	// TODO Auto-generated method stub
try {
		
		String validationsFor;
		validationsFor = "create";
	 	validations.validate(newSapExternalRepair,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
	    fillValuesExterRepairDtl(newSapExternalRepair,existSapExternalRepair,bdFormBean);
	    CommonMessage.debugMsg("ceatedBYService  "+newSapExternalRepair.getExtrCreatedby());
	    CommonMessage.debugMsg("After Filling Values ExterServices");
	
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		return bdmTlMstDao.createExtRepairDtl(newSapExternalRepair);

}
@Override
public SapExternalRepair updateExtRepairDtl(
		SapExternalRepair newSapExternalRepair,
		SapExternalRepair existSapExternalRepair, BDFormBean bdFormBean)
		throws Exception {
	// TODO Auto-generated method stub
try {
		
		String validationsFor;
		validationsFor = "update";
	 	validations.validate(newSapExternalRepair,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
	 	fillValuesExterRepairDtl(newSapExternalRepair,existSapExternalRepair,bdFormBean);
	    
	    CommonMessage.debugMsg("After Update Filling Values ExterServices");
	
}catch (ValidationExceptions e){			
	CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
	throw new ValidationExceptions(e.getMessage());
}	
return bdmTlMstDao.updateExtRepair(newSapExternalRepair);
}
private SapExternalRepair fillValuesExterRepairDtl(SapExternalRepair newSapExternalRepair,
		SapExternalRepair existSapExternalRepair, BDFormBean bdFormBean) {
	String dateTime = CommonFunctions.dateTimeNow();
	CommonMessage.debugMsg("Key ID : "+newSapExternalRepair.getExtrKeyid());
	/*if(UIUtils.isValidKeyId(existSapExternalRepair.getExtrKeyid()) )			
	{	
		newSapExternalRepair.setExtrCreatedon(existSapExternalRepair.getExtrCreatedon());			
	}					
	else
	{	*/
		newSapExternalRepair.setExtrCreatedon(dateTime);
	// }
	newSapExternalRepair.setExtrModifiedon(dateTime);
	newSapExternalRepair.setExtrActive("Y");
	CommonMessage.debugMsg("getExtrActive  "+newSapExternalRepair.getExtrActive());
    if(!UIUtils.isValidKeyId(newSapExternalRepair.getExtrComponentNo()))
    	newSapExternalRepair.setExtrComponentNo("{}");
    
    if(!UIUtils.isValidKeyId(newSapExternalRepair.getExtrItemCategory()))
    	newSapExternalRepair.setExtrItemCategory("{}");
    
    if(!UIUtils.isValidKeyId(newSapExternalRepair.getExtrMatReworkIndi()))
    	newSapExternalRepair.setExtrMatReworkIndi("{}");
    
    if(!UIUtils.isValidKeyId(newSapExternalRepair.getExtrPartno()))
    	newSapExternalRepair.setExtrPartno("{}");
    
    if(!UIUtils.isValidKeyId(newSapExternalRepair.getExtrRequirementQty()))
    	newSapExternalRepair.setExtrRequirementQty("0");
    
    if(!UIUtils.isValidKeyId(newSapExternalRepair.getExtrStorageLocation()))
    	newSapExternalRepair.setExtrStorageLocation("{}");
    
    if(!UIUtils.isValidKeyId(newSapExternalRepair.getExtrUom()))
    	newSapExternalRepair.setExtrUom("{}");
    if(!UIUtils.isValidKeyId(newSapExternalRepair.getExtrLineNo()))
    	newSapExternalRepair.setExtrLineNo("{}");
    	newSapExternalRepair.setExtrTempField1("-");
    //if(!UIUtils.isValidKeyId(newSapExternalRepair.getExtrTempField2()))
    	newSapExternalRepair.setExtrTempField2("-");
    	newSapExternalRepair.setExtrTempField3("-");
    		return newSapExternalRepair;
	// TODO Auto-generated method stub
	
}

@Override
public SapExternalRepair getRepairDtlData(String detailRepairKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	return bdmTlMstDao.getRepairDtlData(detailRepairKeyid);
}

@Override
public String delteExtRprDetail(String rpeDetailKeyid) throws Exception {
	// TODO Auto-generated method stub
	return bdmTlMstDao.delteExtRprDetail(rpeDetailKeyid);
}

@Override
public SapTlMaintenanceOrdermst getSapSpareInFoData(String bdKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	return bdmTlMstDao.getSapSpareInFoData(bdKeyid);
}



@Override
public List<String[]> getExtServiceList(CommonFilter commonFilter,String womsId, String taskId)
		throws Exception {
	// TODO Auto-generated method stub
	return this.bdmTlMstDao.getExtServiceList(commonFilter,womsId,taskId);
}

@Override
public String getServicemstId(String taskid) throws Exception {
	// TODO Auto-generated method stub
	return bdmTlMstDao.getServicemstId(taskid) ;
}
public List<String[]> getServiceText(String servmId) throws Exception {
	return bdmTlMstDao.getServiceText(servmId);
}

@Override
public List<ComboBox> getServiceNo(ComboFilter comboFilter) throws Exception {
	// TODO Auto-generated method stub

		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("SERVM_ACTIVITY_NUMBER");
		comboFilter.setNameField("SERVM_SHORT_TEXT");
		comboFilter.setIdField("SERVM_KEYID");	
		comboFilter.setTableName("GEN_TL_SERVICEMST");
		return commonFilterDao.fillComboValues(comboFilter);		
	
}

@Override
public List<String[]> getBDAnalysisRpt(CommonFilter commonFilter)throws Exception {
// TODO Auto-generated method stub
return this.bdmTlMstDao.getBDAnalysisRpt(commonFilter);
}

@Override
public Workbook getBreakdownAnalysisExportExcel(CommonFilter commonFilter,
		JSONObject tblJSONObj, String format) throws IOException, SQLException, Exception {
	// TODO Auto-generated method stub
	return this.bdmTlMstDao.getsummaryAnalysisExportExcel(commonFilter,  tblJSONObj,format);
}


}