
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

//import org.apache.catalina.connector.Request;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CostInfoBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_CostInfoRptDao;
import com.akranta.tpm.dao.BAL_WomTlManpowercostactualDao;
import com.akranta.tpm.dao.BAL_WomTlManpowercostplanDao;
import com.akranta.tpm.dao.BAL_WomTlOthercostactualDao;
import com.akranta.tpm.dao.BAL_WomTlOthercostplanDao;
import com.akranta.tpm.dao.BAL_WomTlServicecostactualDao;
import com.akranta.tpm.dao.BAL_WomTlServicecostplanDao;
import com.akranta.tpm.dao.BAL_WomTlSparecostactualDao;
import com.akranta.tpm.dao.BAL_WomTlSparecostplanDao;
import com.akranta.tpm.dao.BAL_WomTlUtilitycostactualDao;
import com.akranta.tpm.dao.BAL_WomTlUtilitycostplanDao;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.BAL_CostInfoRptDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlManpowercostactualDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlManpowercostplanDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlOthercostactualDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlOthercostplanDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlServicecostactualDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlServicecostplanDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlSparecostactualDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlSparecostplanDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlUtilitycostactualDaoImpl;
import com.akranta.tpm.dao.impl.BAL_WomTlUtilitycostplanDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_WomTlManpowercostactual;
import com.akranta.tpm.model.BAL_WomTlManpowercostplan;
import com.akranta.tpm.model.BAL_WomTlOthercostactual;
import com.akranta.tpm.model.BAL_WomTlOthercostplan;
import com.akranta.tpm.model.BAL_WomTlServicecostactual;
import com.akranta.tpm.model.BAL_WomTlServicecostplan;
import com.akranta.tpm.model.BAL_WomTlSparecostactual;
import com.akranta.tpm.model.BAL_WomTlSparecostplan;
import com.akranta.tpm.model.BAL_WomTlUtilitycostactual;
import com.akranta.tpm.model.BAL_WomTlUtilitycostplan;
import com.akranta.tpm.service.BAL_EmpCostService;
import com.akranta.tpm.service.api.BdmServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_EmpCostServiceImpl implements BAL_EmpCostService {

	private BAL_CostInfoRptDao costInfoRptDao ;
	
	private BAL_WomTlManpowercostplanDao womTlManpowercostplanDao ;
	private BAL_WomTlManpowercostactualDao womTlManpowercostactualDao ;
	
	private BAL_WomTlSparecostplanDao womTlSparecostplanDao ;
	private BAL_WomTlSparecostactualDao womTlSparecostactualDao ;
	
	private BAL_WomTlServicecostplanDao womTlServicecostplanDao ;
	private BAL_WomTlServicecostactualDao womTlServicecostactualDao ;

	private BAL_WomTlUtilitycostplanDao womTlUtilitycostplanDao ;
	private BAL_WomTlUtilitycostactualDao womTlUtilitycostactualDao ;

	private BAL_WomTlOthercostplanDao womTlOthercostplanDao ;
	private BAL_WomTlOthercostactualDao womTlOthercostactualDao ;
	
	private Validations validations ;
	private BdmServiceApi bdmServiceApi;
	
	public BAL_EmpCostServiceImpl(DBActionTemplate dbActionTemplate)
	{
		costInfoRptDao = new BAL_CostInfoRptDaoImpl(dbActionTemplate);
		womTlManpowercostplanDao = new BAL_WomTlManpowercostplanDaoImpl(dbActionTemplate);
		womTlManpowercostactualDao = new BAL_WomTlManpowercostactualDaoImpl(dbActionTemplate);		
		womTlSparecostplanDao = new BAL_WomTlSparecostplanDaoImpl(dbActionTemplate);
		womTlSparecostactualDao = new BAL_WomTlSparecostactualDaoImpl(dbActionTemplate);	
		womTlServicecostplanDao = new BAL_WomTlServicecostplanDaoImpl(dbActionTemplate);
		womTlServicecostactualDao = new BAL_WomTlServicecostactualDaoImpl(dbActionTemplate);		
		womTlUtilitycostplanDao = new BAL_WomTlUtilitycostplanDaoImpl(dbActionTemplate);
		womTlUtilitycostactualDao = new BAL_WomTlUtilitycostactualDaoImpl(dbActionTemplate);
		womTlOthercostplanDao = new BAL_WomTlOthercostplanDaoImpl(dbActionTemplate);
		womTlOthercostactualDao = new BAL_WomTlOthercostactualDaoImpl(dbActionTemplate);

		validations = new Validations();
	}	
	
	public void BAL_EmpCostServiceImplJwt(String JwtToken){
		try{
			costInfoRptDao.BAL_CostInfoRptDaoImplJwt(JwtToken);
			bdmServiceApi = new BdmServiceApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    // TODO Auto-generated constructor stub
	}
	
	public List<String []> getEmpCost(String EmpId) throws Exception
	{
		CommonFunctions.debugMsg("Inside empcost");
		return this.womTlManpowercostplanDao.getEmpCost(EmpId);
	}

	public List<String []> getContractorCost(String EmpId) throws Exception
	{
		CommonFunctions.debugMsg("Inside empcost");
		return this.womTlManpowercostplanDao.getContractorCost(EmpId);
	}
	
	public List<String []> getSpareCost(String SpareId) throws Exception
	{
		CommonFunctions.debugMsg("Inside empcost");
		return this.womTlSparecostplanDao.getSpareCost(SpareId);
	}	

	public List<String []> getServiceCost(String ServiceId) throws Exception
	{
		CommonFunctions.debugMsg("Inside empcost Service cost");
		return this.womTlServicecostplanDao.getServiceCost(ServiceId);
	}	

	public List<String []> getUtilityCost(String UtilityId, String effDate) throws Exception
	{
		CommonFunctions.debugMsg("Inside Utility cost");
		return this.womTlUtilitycostplanDao.getUtilityCost(UtilityId, effDate);
	}	

	public List<String []> getOtherCost(String OtherId) throws Exception
	{
		CommonFunctions.debugMsg("Inside Other cost");
		return this.womTlOthercostplanDao.getOtherCost(OtherId);
	}	

//grid query
	public List<String[]> getCostInfoView(CommonFilter commonFilter) throws Exception
	{
		return this.costInfoRptDao.getCostInfoView(commonFilter);
	}

	public List<String[]> getGridSummaryQuery(String woId) throws Exception
	{
		//return this.costInfoRptDao.getGridSummaryQuery(woId);
		return this.bdmServiceApi.getGridSummary(woId);
	}
	
	public List<String[]> getGridEstQuery(String formName, String woId) throws Exception
	{
		//return this.costInfoRptDao.getGridEstQuery(formName, woId);
		return this.bdmServiceApi.getGridEstimate(formName, woId);
	}
	public List<String[]> getGridActQuery(String formName, String woId) throws Exception
	{
		//return this.costInfoRptDao.getGridActQuery(formName, woId);
		return this.bdmServiceApi.getGridActual(formName, woId);
	}
	
	public List<String[]> getPageTotal(String formName, String formType, String woId) throws Exception
	{
		//return this.costInfoRptDao.getPageTotal(formName, formType, woId);
		return this.bdmServiceApi.getPageTotal(formName, formType, woId);
	}

	
	public List<String[]> getGridManPowerQry(String formName, String formType,String woId) throws Exception
	{
		return this.womTlManpowercostactualDao.getGridManPowerQry(formName,formType, woId);
	}
	
	public String getCheckManpowerExists(String formType, String woId) throws Exception
	{
		return this.womTlManpowercostactualDao.getCheckManpowerExists(formType,woId);

	}
	

	
//empcost estimate
	public BAL_WomTlManpowercostplan createEmpEstimate(BAL_WomTlManpowercostplan newWomTlManpowercostplan,BAL_WomTlManpowercostplan oldWomTlManpowercostplan ) throws ValidationExceptions,Exception {
		try {
			CommonFunctions.debugMsg("Inside Impl");			
			validations.validate(newWomTlManpowercostplan,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			CommonFunctions.debugMsg("After Validate");			
			fillValuesEmpCostEst(newWomTlManpowercostplan,oldWomTlManpowercostplan);
			//return womTlManpowercostplanDao.create(newWomTlManpowercostplan);
			return bdmServiceApi.saveManpowerCost(newWomTlManpowercostplan);
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
	}	
		
	public BAL_WomTlManpowercostplan updateEmpEstimate(BAL_WomTlManpowercostplan newWomTlManpowercostplan,BAL_WomTlManpowercostplan oldWomTlManpowercostplan)  throws ValidationExceptions, Exception {
		CommonFunctions.debugMsg("update " +oldWomTlManpowercostplan.getMpcpWoid());
		validations.validate(newWomTlManpowercostplan,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
		fillValuesEmpCostEst(newWomTlManpowercostplan,oldWomTlManpowercostplan);		
		//return womTlManpowercostplanDao.update(newWomTlManpowercostplan);
		return bdmServiceApi.saveManpowerCost(newWomTlManpowercostplan);
	}
	
	public BAL_WomTlManpowercostplan selectEmpEstimate(String UtilField) throws Exception
	{
		return this.womTlManpowercostplanDao.select(UtilField);
	}		
		
	public BAL_WomTlManpowercostplan deleteEmpEstimate(BAL_WomTlManpowercostplan womTlManpowercostplan ) throws Exception {
		//return womTlManpowercostplanDao.delete(womTlManpowercostplan);		
		bdmServiceApi.deleteManpowerCost(womTlManpowercostplan);
	    return womTlManpowercostplan;
	}	
	
//actual
	public BAL_WomTlManpowercostactual createEmpActual(BAL_WomTlManpowercostactual newWomTlManpowercostactual,BAL_WomTlManpowercostactual oldWomTlManpowercostactual, BAL_CostInfoBean costInfoBean  ) throws ValidationExceptions,Exception {
		//try {
			CommonFunctions.debugMsg("Inside Impl");			
			validations.validate(newWomTlManpowercostactual,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			
			if(costInfoBean.getMpcsDate()!=null)
				validations.validate(costInfoBean,"costInfoCreation","create");
			
			CommonFunctions.debugMsg("After Validate");
			fillValuesEmpCostAct(newWomTlManpowercostactual,oldWomTlManpowercostactual);
			//return womTlManpowercostactualDao.create(newWomTlManpowercostactual);
			return bdmServiceApi.saveManpowerCostActual(newWomTlManpowercostactual);
		//}catch (ValidationExceptions e){
		//	throw new ValidationExceptions(e.getMessage());
		//}
	}	
		
	public BAL_WomTlManpowercostactual updateEmpActual(BAL_WomTlManpowercostactual newWomTlManpowercostactual,BAL_WomTlManpowercostactual oldWomTlManpowercostactual, BAL_CostInfoBean costInfoBean )  throws ValidationExceptions, Exception {
		CommonFunctions.debugMsg("update " +oldWomTlManpowercostactual.getMpcsMaintwoid());
		validations.validate(newWomTlManpowercostactual,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		
		if(costInfoBean.getMpcsDate()!=null)
			validations.validate(costInfoBean,"costInfoCreation","create");
		
		CommonFunctions.debugMsg("After Validate");		
		fillValuesEmpCostAct(newWomTlManpowercostactual,oldWomTlManpowercostactual);
		
		//return womTlManpowercostactualDao.update(newWomTlManpowercostactual);
		return bdmServiceApi.saveManpowerCostActual(newWomTlManpowercostactual);
	}
	
		
	public BAL_WomTlManpowercostactual deleteEmpActual(BAL_WomTlManpowercostactual womTlManpowercostactual ) throws Exception {
		//return womTlManpowercostactualDao.delete(womTlManpowercostactual);
		bdmServiceApi.deleteManpowerCostActual(womTlManpowercostactual);
	    return womTlManpowercostactual;
	}		
	
	
//spares estimate
	public BAL_WomTlSparecostplan createSpareEstimate(BAL_WomTlSparecostplan newWomTlSparecostplan,BAL_WomTlSparecostplan oldWomTlSparecostplan ) throws ValidationExceptions, Exception {
		try {
			CommonFunctions.debugMsg("Inside Impl");			
			validations.validate(newWomTlSparecostplan,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			CommonFunctions.debugMsg("After Validate");			
			fillValuesSpareCostEst(newWomTlSparecostplan,oldWomTlSparecostplan);
			//return womTlSparecostplanDao.create(newWomTlSparecostplan);
			return bdmServiceApi.saveSpareCost(newWomTlSparecostplan);
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
	}	
	public BAL_WomTlSparecostplan updateSpareEstimate(BAL_WomTlSparecostplan newWomTlSparecostplan,BAL_WomTlSparecostplan oldWomTlSparecostplan)  throws Exception {
		CommonFunctions.debugMsg("update " +oldWomTlSparecostplan.getWscpWoid());
		validations.validate(newWomTlSparecostplan,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
		fillValuesSpareCostEst(newWomTlSparecostplan,oldWomTlSparecostplan);		
		//return womTlSparecostplanDao.update(newWomTlSparecostplan);
		return bdmServiceApi.saveSpareCost(newWomTlSparecostplan);
	}
		
	public BAL_WomTlSparecostplan deleteSpareEstimate(BAL_WomTlSparecostplan womTlSparecostplan ) throws Exception {
		//return womTlSparecostplanDao.delete(womTlSparecostplan);
		bdmServiceApi.deleteSpareCost(womTlSparecostplan);
	    return womTlSparecostplan;
	}	
	
//Spare actual
	public BAL_WomTlSparecostactual createSpareActual(BAL_WomTlSparecostactual newWomTlSparecostactual,BAL_WomTlSparecostactual oldWomTlSparecostactual ) throws ValidationExceptions,Exception {
		try {
			CommonFunctions.debugMsg("Inside Impl");			
			validations.validate(newWomTlSparecostactual,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			CommonFunctions.debugMsg("After Validate");			
			fillValuesSpareCostAct(newWomTlSparecostactual,oldWomTlSparecostactual);
			//return womTlSparecostactualDao.create(newWomTlSparecostactual);
			return bdmServiceApi.saveSpareCostActual(newWomTlSparecostactual);
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
	}	
		
	public BAL_WomTlSparecostactual updateSpareActual(BAL_WomTlSparecostactual newWomTlSparecostactual,BAL_WomTlSparecostactual oldWomTlSparecostactual)  throws ValidationExceptions, Exception {
		CommonFunctions.debugMsg("update " +oldWomTlSparecostactual.getWscaWoid());
		validations.validate(newWomTlSparecostactual,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
		fillValuesSpareCostAct(newWomTlSparecostactual,oldWomTlSparecostactual);		
		//return womTlSparecostactualDao.update(newWomTlSparecostactual);
		return bdmServiceApi.saveSpareCostActual(newWomTlSparecostactual);
	}
	
	public BAL_WomTlSparecostactual deleteSpareActual(BAL_WomTlSparecostactual womTlSparecostactual ) throws Exception {
		//return womTlSparecostactualDao.delete(womTlSparecostactual);	
		bdmServiceApi.deleteSpareCostActual(womTlSparecostactual);
	    return womTlSparecostactual;
	}	
	
//service estimate
	public BAL_WomTlServicecostplan createServiceEstimate(BAL_WomTlServicecostplan newWomTlServicecostplan,BAL_WomTlServicecostplan oldWomTlServicecostplan ) throws ValidationExceptions, Exception {
		try {
			CommonFunctions.debugMsg("Inside Impl");			
			validations.validate(newWomTlServicecostplan,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			CommonFunctions.debugMsg("After Validate");			
			fillValuesServiceCostEst(newWomTlServicecostplan,oldWomTlServicecostplan);
			//return womTlServicecostplanDao.create(newWomTlServicecostplan);
			return bdmServiceApi.saveServiceCost(newWomTlServicecostplan);
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
	}	
	public BAL_WomTlServicecostplan updateServiceEstimate(BAL_WomTlServicecostplan newWomTlServicecostplan,BAL_WomTlServicecostplan oldWomTlServicecostplan)  throws Exception {
		CommonFunctions.debugMsg("update " +oldWomTlServicecostplan.getSvcpWoid());
		validations.validate(newWomTlServicecostplan,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
		fillValuesServiceCostEst(newWomTlServicecostplan,oldWomTlServicecostplan);		
		//return womTlServicecostplanDao.update(newWomTlServicecostplan);
		return bdmServiceApi.saveServiceCost(newWomTlServicecostplan);
	}
		
	public BAL_WomTlServicecostplan deleteServiceEstimate(BAL_WomTlServicecostplan womTlServicecostplan ) throws Exception {
		//return womTlServicecostplanDao.delete(womTlServicecostplan);	
		bdmServiceApi.deleteServiceCost(womTlServicecostplan);
	    return womTlServicecostplan;
	}	
	
//Service actual
	public BAL_WomTlServicecostactual createServiceActual(BAL_WomTlServicecostactual newWomTlServicecostactual,BAL_WomTlServicecostactual oldWomTlServicecostactual ) throws ValidationExceptions,Exception {
		try {
			CommonFunctions.debugMsg("Inside Impl");			
			validations.validate(newWomTlServicecostactual,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			CommonFunctions.debugMsg("After Validate");			
			fillValuesServiceCostAct(newWomTlServicecostactual,oldWomTlServicecostactual);
			//return womTlServicecostactualDao.create(newWomTlServicecostactual);
			return bdmServiceApi.saveServiceCostActual(newWomTlServicecostactual);
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
	}	
		
	public BAL_WomTlServicecostactual updateServiceActual(BAL_WomTlServicecostactual newWomTlServicecostactual,BAL_WomTlServicecostactual oldWomTlServicecostactual)  throws ValidationExceptions, Exception {
		CommonFunctions.debugMsg("update " +oldWomTlServicecostactual.getSvcaWoid());
		validations.validate(newWomTlServicecostactual,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
		fillValuesServiceCostAct(newWomTlServicecostactual,oldWomTlServicecostactual);		
		//return womTlServicecostactualDao.update(newWomTlServicecostactual);
		return bdmServiceApi.saveServiceCostActual(newWomTlServicecostactual);
	}
	
	public BAL_WomTlServicecostactual deleteServiceActual(BAL_WomTlServicecostactual womTlSparecostactual ) throws Exception {
		//return womTlServicecostactualDao.delete(womTlSparecostactual);	
		bdmServiceApi.deleteServiceCostActual(womTlSparecostactual);
	    return womTlSparecostactual;
	}	
		
	
//Utility estimate
		public BAL_WomTlUtilitycostplan createUtilityEstimate(BAL_WomTlUtilitycostplan newWomTlUtilitycostplan,BAL_WomTlUtilitycostplan oldWomTlUtilitycostplan ) throws ValidationExceptions, Exception {
			try {
				CommonFunctions.debugMsg("Inside Impl");			
				validations.validate(newWomTlUtilitycostplan,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				CommonFunctions.debugMsg("After Validate");			
				fillValuesUtilityCostEst(newWomTlUtilitycostplan,oldWomTlUtilitycostplan);
				//return womTlUtilitycostplanDao.create(newWomTlUtilitycostplan);
				return bdmServiceApi.saveUtilityCost(newWomTlUtilitycostplan);
			}catch (ValidationExceptions e){
				throw new ValidationExceptions(e.getMessage());
			}
		}	
		public BAL_WomTlUtilitycostplan updateUtilityEstimate(BAL_WomTlUtilitycostplan newWomTlUtilitycostplan,BAL_WomTlUtilitycostplan oldWomTlUtilitycostplan)  throws Exception {
			CommonFunctions.debugMsg("update " +oldWomTlUtilitycostplan.getUtcpWokeyid());
			validations.validate(newWomTlUtilitycostplan,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
			fillValuesUtilityCostEst(newWomTlUtilitycostplan,oldWomTlUtilitycostplan);		
			//return womTlUtilitycostplanDao.update(newWomTlUtilitycostplan);
			return bdmServiceApi.saveUtilityCost(newWomTlUtilitycostplan);
		}
			
		public BAL_WomTlUtilitycostplan deleteUtilityEstimate(BAL_WomTlUtilitycostplan womTlUtilitycostplan ) throws Exception {
			//return womTlUtilitycostplanDao.delete(womTlUtilitycostplan);	
			bdmServiceApi.deleteUtilityCost(womTlUtilitycostplan);
		    return womTlUtilitycostplan;
		}	
		
	//Utility actual
		public BAL_WomTlUtilitycostactual createUtilityActual(BAL_WomTlUtilitycostactual newWomTlUtilitycostactual,BAL_WomTlUtilitycostactual oldWomTlUtilitycostactual ) throws ValidationExceptions,Exception {
			try {
				CommonFunctions.debugMsg("Inside Impl");			
				validations.validate(newWomTlUtilitycostactual,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				CommonFunctions.debugMsg("After Validate");			
				fillValuesUtilityCostAct(newWomTlUtilitycostactual,oldWomTlUtilitycostactual);
				//return womTlUtilitycostactualDao.create(newWomTlUtilitycostactual);
				return bdmServiceApi.saveUtilityCostActual(newWomTlUtilitycostactual);
			}catch (ValidationExceptions e){
				throw new ValidationExceptions(e.getMessage());
			}
		}	
			
		public BAL_WomTlUtilitycostactual updateUtilityActual(BAL_WomTlUtilitycostactual newWomTlUtilitycostactual,BAL_WomTlUtilitycostactual oldWomTlUtilitycostactual)  throws ValidationExceptions, Exception {
			CommonFunctions.debugMsg("update " +oldWomTlUtilitycostactual.getUtcaWokeyid());
			validations.validate(newWomTlUtilitycostactual,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
			fillValuesUtilityCostAct(newWomTlUtilitycostactual,oldWomTlUtilitycostactual);		
			//return womTlUtilitycostactualDao.update(newWomTlUtilitycostactual);
			return bdmServiceApi.saveUtilityCostActual(newWomTlUtilitycostactual);
		}
		
		public BAL_WomTlUtilitycostactual deleteUtilityActual(BAL_WomTlUtilitycostactual womTlUtilitycostactual ) throws Exception {
			//return womTlUtilitycostactualDao.delete(womTlUtilitycostactual);	
			bdmServiceApi.deleteUtilityCostActual(womTlUtilitycostactual);
		    return womTlUtilitycostactual;
		}		
		
//Other estimate
		public BAL_WomTlOthercostplan createOtherEstimate(BAL_WomTlOthercostplan newWomTlOthercostplan,BAL_WomTlOthercostplan oldWomTlOthercostplan ) throws ValidationExceptions, Exception {
			try {
				CommonFunctions.debugMsg("Inside Impl");			
				validations.validate(newWomTlOthercostplan,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				CommonFunctions.debugMsg("After Validate");			
				fillValuesOtherCostEst(newWomTlOthercostplan,oldWomTlOthercostplan);
				//return womTlOthercostplanDao.create(newWomTlOthercostplan);
				return bdmServiceApi.saveOtherCost(newWomTlOthercostplan);
			}catch (ValidationExceptions e){
				throw new ValidationExceptions(e.getMessage());
			}
		}	
		public BAL_WomTlOthercostplan updateOtherEstimate(BAL_WomTlOthercostplan newWomTlOthercostplan,BAL_WomTlOthercostplan oldWomTlOthercostplan)  throws Exception {
			CommonFunctions.debugMsg("update " +oldWomTlOthercostplan.getOtcpWoid());
			validations.validate(newWomTlOthercostplan,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
			fillValuesOtherCostEst(newWomTlOthercostplan,oldWomTlOthercostplan);		
			//return womTlOthercostplanDao.update(newWomTlOthercostplan);
			return bdmServiceApi.saveOtherCost(newWomTlOthercostplan);
		}
			
		public BAL_WomTlOthercostplan deleteOtherEstimate(BAL_WomTlOthercostplan womTlOthercostplan ) throws Exception {
			//return womTlOthercostplanDao.delete(womTlOthercostplan);
			bdmServiceApi.deleteOtherCost(womTlOthercostplan);
		    return womTlOthercostplan;
		}	
		
	//Other actual
		public BAL_WomTlOthercostactual createOtherActual(BAL_WomTlOthercostactual newWomTlOthercostactual,BAL_WomTlOthercostactual oldWomTlOthercostactual ) throws ValidationExceptions,Exception {
			try {
				CommonFunctions.debugMsg("Inside Impl");			
				validations.validate(newWomTlOthercostactual,"costInfoCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				CommonFunctions.debugMsg("After Validate");			
				fillValuesOtherCostAct(newWomTlOthercostactual,oldWomTlOthercostactual);
				//return womTlOthercostactualDao.create(newWomTlOthercostactual);
				return bdmServiceApi.saveOtherCostActual(newWomTlOthercostactual);
			}catch (ValidationExceptions e){
				throw new ValidationExceptions(e.getMessage());
			}
		}	
			
		public BAL_WomTlOthercostactual updateOtherActual(BAL_WomTlOthercostactual newWomTlOthercostactual,BAL_WomTlOthercostactual oldWomTlOthercostactual)  throws ValidationExceptions, Exception {
			CommonFunctions.debugMsg("update " +oldWomTlOthercostactual.getOtcdWoid());
			validations.validate(newWomTlOthercostactual,"costInfoCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
			fillValuesOtherCostAct(newWomTlOthercostactual,oldWomTlOthercostactual);		
			//return womTlOthercostactualDao.update(newWomTlOthercostactual);
			return bdmServiceApi.saveOtherCostActual(newWomTlOthercostactual);
		}
		
		public BAL_WomTlOthercostactual deleteOtherActual(BAL_WomTlOthercostactual womTlOthercostactual ) throws Exception {
			//return womTlOthercostactualDao.delete(womTlOthercostactual);		
			bdmServiceApi.deleteOtherCostActual(womTlOthercostactual);
		    return womTlOthercostactual;
		}				

private BAL_WomTlManpowercostplan fillValuesEmpCostEst(BAL_WomTlManpowercostplan newWomTlManpowercostplan,BAL_WomTlManpowercostplan oldWomTlManpowercostplan) {
		
	CommonFunctions.debugMsg("fill values:");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();
	
		newWomTlManpowercostplan.setMpcpActive("Y");
		newWomTlManpowercostplan.setMpcpCreatedon(dateTime);
		newWomTlManpowercostplan.setMpcpModifiedon(dateTime);
		
		//newWomTlManpowercostplan.setMpcpDoctype("BDM");		
		//newWomTlManpowercostplan.setMpcpWoid("MW0055");
		
		if( newWomTlManpowercostplan.getMpcpHolidaycost() == null )
			newWomTlManpowercostplan.setMpcpHolidaycost("0");
		
		if( newWomTlManpowercostplan.getMpcpOthermins() == null )
			newWomTlManpowercostplan.setMpcpOthermins("0");
		if( newWomTlManpowercostplan.getMpcpHolidaymins() == null )
		newWomTlManpowercostplan.setMpcpHolidaymins("0");
		if( newWomTlManpowercostplan.getMpcpOthercost() == null )
		newWomTlManpowercostplan.setMpcpOthercost("0");
		
		String NormalCost ="";
		NormalCost = newWomTlManpowercostplan.getMpcpNormalcost();
		newWomTlManpowercostplan.setMpcpNormalcost(NormalCost);
		
		if( newWomTlManpowercostplan.getMpcpNoofhelpers() == null )
			newWomTlManpowercostplan.setMpcpNoofhelpers("0");
		
		if( newWomTlManpowercostplan.getMpcpSkillflag() == null )
			newWomTlManpowercostplan.setMpcpSkillflag("E");
		
		if (newWomTlManpowercostplan.getMpcpDate()==null)
			newWomTlManpowercostplan.setMpcpDate(dateTime);
		
		newWomTlManpowercostplan.setMpcpRemarks("{}");
		newWomTlManpowercostplan.setMpcpTempfield1("{}");
		newWomTlManpowercostplan.setMpcpTempfield2("{}");
		newWomTlManpowercostplan.setMpcpTempfield3("{}");
		newWomTlManpowercostplan.setMpcpTempfield4("{}");
		newWomTlManpowercostplan.setMpcpTempfield5("{}");
		newWomTlManpowercostplan.setMpcpActivity("{}");
		
		return newWomTlManpowercostplan; 
	}

private BAL_WomTlManpowercostactual fillValuesEmpCostAct(BAL_WomTlManpowercostactual newWomTlManpowercostactual,BAL_WomTlManpowercostactual oldWomTlManpowercostactual) {
	
	CommonFunctions.debugMsg("fill values: actual");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();
	
		newWomTlManpowercostactual.setMpcsCreatedon(dateTime);
		newWomTlManpowercostactual.setMpcsModifiedon(dateTime);
		
		//newWomTlManpowercostactual.setMpcsDoctype("BDM");		
		//newWomTlManpowercostactual.setMpcsMaintwoid("MW0055");			
		
		if( newWomTlManpowercostactual.getMpcsNoofhelpers() == null )
			newWomTlManpowercostactual.setMpcsNoofhelpers("0");
		
		if( newWomTlManpowercostactual.getMpcsRemarks() == null || newWomTlManpowercostactual.getMpcsRemarks() == "")
			newWomTlManpowercostactual.setMpcsRemarks("{}");
		
		if( newWomTlManpowercostactual.getMpcsSkillflag() == null )
			newWomTlManpowercostactual.setMpcsSkillflag("E");
		String date = newWomTlManpowercostactual.getMpcsDate();
		newWomTlManpowercostactual.setMpcsDate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		if (newWomTlManpowercostactual.getMpcsDate()==null) 
			newWomTlManpowercostactual.setMpcsDate(dateTime);
		
		newWomTlManpowercostactual.setMpcsTempfield1("{}");
		newWomTlManpowercostactual.setMpcsTempfield2("{}");
		newWomTlManpowercostactual.setMpcsTempfield3("{}");
		newWomTlManpowercostactual.setMpcsTempfield4("{}");
		newWomTlManpowercostactual.setMpcsTempfield5("{}");
		
		if( newWomTlManpowercostactual.getMpcsActivity() == null )
			newWomTlManpowercostactual.setMpcsActivity("{}");
		
		return newWomTlManpowercostactual; 
	}

//spares fill values
private BAL_WomTlSparecostplan fillValuesSpareCostEst(BAL_WomTlSparecostplan newWomTlSparecostplan,BAL_WomTlSparecostplan oldWomTlSparecostplan) {
	
	CommonFunctions.debugMsg("fill values:");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();
	
		newWomTlSparecostplan.setWscpActive("Y");
		newWomTlSparecostplan.setWscpCreatedon(dateTime);
		newWomTlSparecostplan.setWscpModifiedon(dateTime);
		
		//newWomTlSparecostplan.setWscpDoctype("BDM");		
		//newWomTlSparecostplan.setWscpWoid("MW0055");
		
		newWomTlSparecostplan.setWscpRefdocno("{}");
		
		if( newWomTlSparecostplan.getWscpRate() == null )
			newWomTlSparecostplan.setWscpRate("0");		
		if( newWomTlSparecostplan.getWscpValue() == null )
			newWomTlSparecostplan.setWscpValue("0");
		
		if (newWomTlSparecostplan.getWscpDate()==null)
			newWomTlSparecostplan.setWscpDate(dateTime);
	
		newWomTlSparecostplan.setWscpTempfield1("{}");
		newWomTlSparecostplan.setWscpTempfield2("{}");
		newWomTlSparecostplan.setWscpTempfield3("{}");
		
		return newWomTlSparecostplan; 
	}

private BAL_WomTlSparecostactual fillValuesSpareCostAct(BAL_WomTlSparecostactual newWomTlSparecostactual,BAL_WomTlSparecostactual oldWomTlSparecostactual) {
	
	CommonFunctions.debugMsg("fill values:");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();
	
		//newWomTlSparecostactual.setWscaActive("Y");
		newWomTlSparecostactual.setWscaCreatedon(dateTime);
		newWomTlSparecostactual.setWscaModifiedon(dateTime);		
		
		//newWomTlSparecostactual.setWscaDoctype("BDM");		
		//newWomTlSparecostactual.setWscaWoid("MW0055");
		
		newWomTlSparecostactual.setWscaRefdocno("{}");
		newWomTlSparecostactual.setWscaSitreference("{}");
		
		if( newWomTlSparecostactual.getWscaRate() == null )
			newWomTlSparecostactual.setWscaRate("0");		
		if( newWomTlSparecostactual.getWscaValue() == null )
			newWomTlSparecostactual.setWscaValue("0");
		
		if( newWomTlSparecostactual.getWscaDate() == null )
			newWomTlSparecostactual.setWscaDate(dateTime);		
		
		
		
		newWomTlSparecostactual.setWscaTempfield1("{}");
		newWomTlSparecostactual.setWscaTempfield2("{}");		
		
		return newWomTlSparecostactual; 
	}

//service fill values
private BAL_WomTlServicecostplan fillValuesServiceCostEst(BAL_WomTlServicecostplan newWomTlServicecostplan,BAL_WomTlServicecostplan oldWomTlServicecostplan) {
	
	CommonFunctions.debugMsg("fill values:");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		newWomTlServicecostplan.setSvcpCreatedon(dateTime);
		newWomTlServicecostplan.setSvcpModifiedon(dateTime);
		
		//newWomTlServicecostplan.setSvcpDoctype("BDM");		
		//newWomTlServicecostplan.setSvcpWoid("MW0055");
		
		newWomTlServicecostplan.setSvcpBilldateflag("Y");
		CommonFunctions.debugMsg("fill values1111");
		if( newWomTlServicecostplan.getSvcpBillno() == null )
			newWomTlServicecostplan.setSvcpBillno("{}");
		
		if( newWomTlServicecostplan.getSvcpRemarks() == null )
			newWomTlServicecostplan.setSvcpRemarks("{}");
		
		if (newWomTlServicecostplan.getSvcpBilldate()==null)
			newWomTlServicecostplan.setSvcpBilldate(dateTime);
		
		newWomTlServicecostplan.setSvcpTempfield1("{}");
		newWomTlServicecostplan.setSvcpTempfield2("{}");
		newWomTlServicecostplan.setSvcpTempfield3("{}");
		newWomTlServicecostplan.setSvcpTempfield4("{}");
		newWomTlServicecostplan.setSvcpTempfield5("{}");
		
		return newWomTlServicecostplan; 
	}

private BAL_WomTlServicecostactual fillValuesServiceCostAct(BAL_WomTlServicecostactual newWomTlServicecostactual,BAL_WomTlServicecostactual oldWomTlServicecostactual) {
	
	CommonFunctions.debugMsg("fill values:");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();
	
		newWomTlServicecostactual.setSvcaCreatedon(dateTime);
		newWomTlServicecostactual.setSvcaModifiedon(dateTime);
			
		newWomTlServicecostactual.setSvcaBilldateflag("Y");		
		
		if( newWomTlServicecostactual.getSvcaBillno() == null )
			newWomTlServicecostactual.setSvcaBillno("{}");		
		String date= newWomTlServicecostactual.getSvcaBilldate();
		newWomTlServicecostactual.setSvcaBilldate(CommonFunctions.pg_getDateTimeFromDate(date));
		if( newWomTlServicecostactual.getSvcaBilldate() == null )
			newWomTlServicecostactual.setSvcaBilldate(dateTime);
		if (!UIUtils.isValidKeyId(newWomTlServicecostactual.getSvcaRemarks()))
			newWomTlServicecostactual.setSvcaRemarks("{}");
		newWomTlServicecostactual.setSvcaTempfield1("{}");
		newWomTlServicecostactual.setSvcaTempfield2("{}");		
		newWomTlServicecostactual.setSvcaTempfield3("{}");
		newWomTlServicecostactual.setSvcaTempfield4("{}");
		newWomTlServicecostactual.setSvcaTempfield5("{}");
		
		return newWomTlServicecostactual; 
	}

//Utility fill values
private BAL_WomTlUtilitycostplan fillValuesUtilityCostEst(BAL_WomTlUtilitycostplan newWomTlUtilitycostplan,BAL_WomTlUtilitycostplan oldWomTlUtilitycostplan) {
	
	CommonFunctions.debugMsg("fill values:");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		newWomTlUtilitycostplan.setUtcpCreatedon(dateTime);
		newWomTlUtilitycostplan.setUtcpModifiedon(dateTime);
		
		//newWomTlUtilitycostplan.setUtcpDoctype("BDM");		
		//newWomTlUtilitycostplan.setUtcpWokeyid("MW0055");	
		
		CommonFunctions.debugMsg("fill values1111");
		if( newWomTlUtilitycostplan.getUtcpRemarks() == null )
			newWomTlUtilitycostplan.setUtcpRemarks("{}");
		
		if (newWomTlUtilitycostplan.getUtcpDate()==null)
			newWomTlUtilitycostplan.setUtcpDate(dateTime);
		
		newWomTlUtilitycostplan.setUtcpTempfield2("{}");
		newWomTlUtilitycostplan.setUtcpTempfield3("{}");
		newWomTlUtilitycostplan.setUtcpTempfield4("{}");
		newWomTlUtilitycostplan.setUtcpTempfield5("{}");
		
		return newWomTlUtilitycostplan; 
	}

private BAL_WomTlUtilitycostactual fillValuesUtilityCostAct(BAL_WomTlUtilitycostactual newWomTlUtilitycostactual,BAL_WomTlUtilitycostactual oldWomTlUtilitycostactual) {
	
	CommonFunctions.debugMsg("fill values:");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();
	
		newWomTlUtilitycostactual.setUtcaCreatedon(dateTime);
		newWomTlUtilitycostactual.setUtcaModifiedon(dateTime);		
		
		//newWomTlUtilitycostactual.setUtcaDoctype("BDM");		
		//newWomTlUtilitycostactual.setUtcaWokeyid("MW0055");
		String date = newWomTlUtilitycostactual.getUtcaDate();
		newWomTlUtilitycostactual.setUtcaDate(CommonFunctions.pg_getDateTimeFromDate(date));
		if( newWomTlUtilitycostactual.getUtcaRemarks() == null )
			newWomTlUtilitycostactual.setUtcaRemarks("{}");
		
		newWomTlUtilitycostactual.setUtcaTempfield2("{}");		
		newWomTlUtilitycostactual.setUtcaTempfield3("{}");
		newWomTlUtilitycostactual.setUtcaTempfield4("{}");
		newWomTlUtilitycostactual.setUtcaTempfield5("{}");
		
		return newWomTlUtilitycostactual; 
	}

//Other fill values
private BAL_WomTlOthercostplan fillValuesOtherCostEst(BAL_WomTlOthercostplan newWomTlOthercostplan,BAL_WomTlOthercostplan oldWomTlOthercostplan) {
	
	CommonFunctions.debugMsg("fill values:");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		newWomTlOthercostplan.setOtcpCreatedon(dateTime);
		newWomTlOthercostplan.setOtcpModifiedon(dateTime);
		
		//newWomTlOthercostplan.setOtcpDoctype("BDM");		
		//newWomTlOthercostplan.setOtcpWoid("MW0055");	
		
		CommonFunctions.debugMsg("fill values1111");
		
		if( newWomTlOthercostplan.getOtcpRemarks() == null )
			newWomTlOthercostplan.setOtcpRemarks("{}");
		
		newWomTlOthercostplan.setOtcpDate(dateTime);
		
		newWomTlOthercostplan.setOtcpTempfield2("{}");
		newWomTlOthercostplan.setOtcpTempfield3("{}");
		newWomTlOthercostplan.setOtcpTempfield4("{}");
		newWomTlOthercostplan.setOtcpTempfield5("{}");
		
		return newWomTlOthercostplan; 
	}

private BAL_WomTlOthercostactual fillValuesOtherCostAct(BAL_WomTlOthercostactual newWomTlOthercostactual,BAL_WomTlOthercostactual oldWomTlOthercostactual) {
	
	CommonFunctions.debugMsg("fill values:");
	
		String dateTime = CommonFunctions.pg_dateTimeNow();
	
		newWomTlOthercostactual.setOtcdCreatedon(dateTime);
		newWomTlOthercostactual.setOtcdModifiedon(dateTime);		
		
		//newWomTlOthercostactual.setOtcdDoctype("BDM");		
		//newWomTlOthercostactual.setOtcdWoid("MW0055");
		String date = newWomTlOthercostactual.getOtcdDate();
		newWomTlOthercostactual.setOtcdDate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		if( newWomTlOthercostactual.getOtcdRemarks() == null )
			newWomTlOthercostactual.setOtcdRemarks("{}");
		
		newWomTlOthercostactual.setOtcdTempfield1("{}");
		newWomTlOthercostactual.setOtcdTempfield2("{}");		
		newWomTlOthercostactual.setOtcdTempfield3("{}");
		newWomTlOthercostactual.setOtcdTempfield4("{}");
		
		
		return newWomTlOthercostactual; 
	}
public Workbook getAllCostInfoExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
	return this.womTlOthercostplanDao.getAllCostInfoExcel( commonFilter,tblJSONObj,format);
}
}
