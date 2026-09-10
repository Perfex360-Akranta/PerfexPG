package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.PlanConfigExceptions;
import com.akranta.tpm.Exceptions.SequenceNumGenException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.Exceptions.WoResponsibilityExpection;
import  com.akranta.tpm.bean.BAL_PlanConfigurationBean;
import com.akranta.tpm.bean.BAL_WOResponsibilityBean;
import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlProductionplan;
import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration;
import com.akranta.tpm.model.BAL_PlmTlWorespdtl;
import com.akranta.tpm.model.BAL_PlmTlWorespmst;

public interface BAL_PlmTlPlanconfigurationService {


	List<String[]> getdataPlanConfig(String factId, String machId, String cellId, String chkValue);

	List<String[]> getYearHirerachy(String selYear) throws Exception;
	public PcsTlProductionplan create(PcsTlProductionplan newPcsTlProductionplan,PcsTlProductionplan oldPcsTlProductionplan)throws ValidationExceptions, Exception;
	BAL_PlmTlPlanconfiguration create(
			BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,
			BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration,
			BAL_PlanConfigurationBean planConfigurationBean, String usrConfirmation) throws  PlanConfigExceptions,WoResponsibilityExpection, SequenceNumGenException, BusinessApplicationExceptions, Exception ;

	BAL_PlmTlPlanconfiguration update(
			BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,
			BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration,
			BAL_PlanConfigurationBean planConfigurationBean) throws ValidationExceptions;

	List<ComboBox> getplnconfigTradeCombo(String string, ComboFilter comboFilter) throws Exception;

	List<ComboBox> getplnconfigDesignationCombo(String string, ComboFilter comboFilte) throws Exception;

	

	BAL_PlmTlWorespmst saveWorkresp(BAL_PlmTlWorespmst newplmTlWorespmst,
			BAL_PlmTlWorespmst existplmTlWorespmst, BAL_WOResponsibilityBean wOResponsibilityBean);

	BAL_PlmTlWorespdtl saveWorkDetail(BAL_PlmTlWorespdtl newplmTlWorespdtl,
			BAL_PlmTlWorespmst existplmTlWorespdtl);

	List<String[]> getwoRespGridadd(String workrepmstkeyid);

	List<String[]> getwoRespGriddata(String workrepmstkeyid);
	
	List<String[]> getPlanEntry(String machineId,String entryDate,String productId,CommonFilter commonFilter);

	BAL_PlmTlPlanconfiguration getplanConfigdata(String planConfigKey) throws  Exception;
	
	public void BAL_PlmTlPlanconfigurationImplJwt(String jwtToken); 

	



	
}
