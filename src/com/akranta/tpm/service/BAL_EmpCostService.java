package com.akranta.tpm.service;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CostInfoBean;
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


public interface BAL_EmpCostService {

	public List<String []> getEmpCost (String EmpId) throws Exception;
	public List<String []> getContractorCost (String EmpId) throws Exception;
	public List<String []> getSpareCost (String EmpId) throws Exception;
	public List<String []> getServiceCost (String ServiceId) throws Exception;
	public List<String []> getUtilityCost (String UtilityId, String effDate) throws Exception;
	public List<String []> getOtherCost (String OtherId) throws Exception;	
		
	public List<String[]> getCostInfoView(CommonFilter commonFilter) throws Exception;	
//spread query
	public List<String[]> getGridSummaryQuery(String woId) throws Exception;	
	public List<String[]> getPageTotal(String formName, String formType, String woId) throws Exception;
	
	public List<String[]> getGridEstQuery(String fromName, String woId) throws Exception;
	public List<String[]> getGridActQuery(String fromName, String woId) throws Exception;
	
	public List<String[]> getGridManPowerQry(String fromName, String fromType, String woId) throws Exception;
	
	public String getCheckManpowerExists(String formType, String woId) throws Exception;
	
	public BAL_WomTlManpowercostplan createEmpEstimate(BAL_WomTlManpowercostplan newWomTlManpowercostplan,BAL_WomTlManpowercostplan oldWomTlManpowercostplan ) throws ValidationExceptions, Exception;
	public BAL_WomTlManpowercostplan updateEmpEstimate(BAL_WomTlManpowercostplan newWomTlManpowercostplan,BAL_WomTlManpowercostplan oldWomTlManpowercostplan)  throws Exception;
	public BAL_WomTlManpowercostplan deleteEmpEstimate(BAL_WomTlManpowercostplan womTlManpowercostplan ) throws Exception;

	public BAL_WomTlManpowercostactual createEmpActual(BAL_WomTlManpowercostactual newWomTlManpowercostactual,BAL_WomTlManpowercostactual oldWomTlManpowercostactual, BAL_CostInfoBean costInfoBean ) throws ValidationExceptions, Exception;
	public BAL_WomTlManpowercostactual updateEmpActual(BAL_WomTlManpowercostactual newWomTlManpowercostactual,BAL_WomTlManpowercostactual oldWomTlManpowercostactual, BAL_CostInfoBean costInfoBean )  throws Exception;
	public BAL_WomTlManpowercostactual deleteEmpActual(BAL_WomTlManpowercostactual womTlManpowercostactual ) throws Exception;


//spare	
	public BAL_WomTlSparecostplan createSpareEstimate(BAL_WomTlSparecostplan newWomTlSparecostplan,BAL_WomTlSparecostplan oldWomTlSparecostplan ) throws ValidationExceptions, Exception;
	public BAL_WomTlSparecostplan updateSpareEstimate(BAL_WomTlSparecostplan newWomTlSparecostplan,BAL_WomTlSparecostplan oldWomTlSparecostplan)  throws Exception;
	public BAL_WomTlSparecostplan deleteSpareEstimate(BAL_WomTlSparecostplan womTlSparecostplan ) throws Exception;

	public BAL_WomTlSparecostactual createSpareActual(BAL_WomTlSparecostactual newWomTlSparecostactual,BAL_WomTlSparecostactual oldWomTlSparecostactual ) throws ValidationExceptions, Exception;
	public BAL_WomTlSparecostactual updateSpareActual(BAL_WomTlSparecostactual newWomTlSparecostactual,BAL_WomTlSparecostactual oldWomTlSparecostactual)  throws Exception;
	public BAL_WomTlSparecostactual deleteSpareActual(BAL_WomTlSparecostactual womTlSparecostactual ) throws Exception;

//service
	public BAL_WomTlServicecostplan createServiceEstimate(BAL_WomTlServicecostplan newWomTlServicecostplan,BAL_WomTlServicecostplan oldWomTlServicecostplan ) throws ValidationExceptions, Exception;
	public BAL_WomTlServicecostplan updateServiceEstimate(BAL_WomTlServicecostplan newWomTlServicecostplan,BAL_WomTlServicecostplan oldWomTlServicecostplan)  throws Exception;
	public BAL_WomTlServicecostplan deleteServiceEstimate(BAL_WomTlServicecostplan womTlServicecostplan ) throws Exception;

	public BAL_WomTlServicecostactual createServiceActual(BAL_WomTlServicecostactual newWomTlServicecostactual,BAL_WomTlServicecostactual oldWomTlServicecostactual ) throws ValidationExceptions, Exception;
	public BAL_WomTlServicecostactual updateServiceActual(BAL_WomTlServicecostactual newWomTlServicecostactual,BAL_WomTlServicecostactual oldWomTlServicecostactual)  throws Exception;
	public BAL_WomTlServicecostactual deleteServiceActual(BAL_WomTlServicecostactual womTlServicecostactual ) throws Exception;
	
//Utility
	public BAL_WomTlUtilitycostplan createUtilityEstimate(BAL_WomTlUtilitycostplan newWomTlUtilitycostplan,BAL_WomTlUtilitycostplan oldWomTlUtilitycostplan ) throws ValidationExceptions, Exception;
	public BAL_WomTlUtilitycostplan updateUtilityEstimate(BAL_WomTlUtilitycostplan newWomTlUtilitycostplan,BAL_WomTlUtilitycostplan oldWomTlUtilitycostplan)  throws Exception;
	public BAL_WomTlUtilitycostplan deleteUtilityEstimate(BAL_WomTlUtilitycostplan womTlUtilitycostplan ) throws Exception;

	public BAL_WomTlUtilitycostactual createUtilityActual(BAL_WomTlUtilitycostactual newWomTlUtilitycostactual,BAL_WomTlUtilitycostactual oldWomTlUtilitycostactual ) throws ValidationExceptions, Exception;
	public BAL_WomTlUtilitycostactual updateUtilityActual(BAL_WomTlUtilitycostactual newWomTlUtilitycostactual,BAL_WomTlUtilitycostactual oldWomTlUtilitycostactual)  throws Exception;
	public BAL_WomTlUtilitycostactual deleteUtilityActual(BAL_WomTlUtilitycostactual womTlUtilitycostactual ) throws Exception;

//Other
	public BAL_WomTlOthercostplan createOtherEstimate(BAL_WomTlOthercostplan newWomTlOthercostplan,BAL_WomTlOthercostplan oldWomTlOthercostplan ) throws ValidationExceptions, Exception;
	public BAL_WomTlOthercostplan updateOtherEstimate(BAL_WomTlOthercostplan newWomTlOthercostplan,BAL_WomTlOthercostplan oldWomTlOthercostplan)  throws Exception;
	public BAL_WomTlOthercostplan deleteOtherEstimate(BAL_WomTlOthercostplan womTlOthercostplan ) throws Exception;

	public BAL_WomTlOthercostactual createOtherActual(BAL_WomTlOthercostactual newWomTlOthercostactual,BAL_WomTlOthercostactual oldWomTlOthercostactual ) throws ValidationExceptions, Exception;
	public BAL_WomTlOthercostactual updateOtherActual(BAL_WomTlOthercostactual newWomTlOthercostactual,BAL_WomTlOthercostactual oldWomTlOthercostactual)  throws Exception;
	public BAL_WomTlOthercostactual deleteOtherActual(BAL_WomTlOthercostactual womTlOthercostactual ) throws Exception;
	public Workbook getAllCostInfoExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	
	
	public void BAL_EmpCostServiceImplJwt(String JwtToken);
	
		
	
}



