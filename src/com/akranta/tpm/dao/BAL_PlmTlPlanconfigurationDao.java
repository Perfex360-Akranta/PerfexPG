package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.SequenceNumGenException;
import com.akranta.tpm.Exceptions.WoResponsibilityExpection;
import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.PlmTlPlanconfiguration;

public interface BAL_PlmTlPlanconfigurationDao {

	public abstract BAL_PlmTlPlanconfiguration create(BAL_PlmTlPlanconfiguration plmTlPlanconfiguration) throws SequenceNumGenException,BusinessApplicationExceptions, WoResponsibilityExpection;
	public abstract BAL_PlmTlPlanconfiguration update(BAL_PlmTlPlanconfiguration plmTlPlanconfiguration) throws Exception;
	public abstract BAL_PlmTlPlanconfiguration delete(BAL_PlmTlPlanconfiguration plmTlPlanconfiguration) throws Exception;
	
	public abstract List<String[]> getdataPlanConfig(String factId,String machId, String cellId,String chkValue);
	public abstract List<String[]> getYearHirerachy(String selYear) throws Exception;
	public abstract List<String[]> getwoRespGriddata(String workrepmstkeyid);
	public abstract List<String[]> getwoRespGridadd(String workrepmstkeyid);
	public abstract List<String[]> getPlanEntry(String machineId,String entryDate,String productId,CommonFilter commonFilter);
	public abstract BAL_PlmTlPlanconfiguration getplanConfigdata(String planConfigKey) throws Exception;
	public abstract void generateCalendar(String substring,
			String pplcFactoryid, String pplcSectionid, String pplcCellid,
			String pplcMachineid, String pplcAssemblyid, String pplcFrequency,String fromMonth) throws Exception;
	public void insertAllMach(
			BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration, String string) throws Exception;
	
	public abstract void BAL_PlmTlPlanconfigurationDaoImplJwt(String jwtToken); 
	
	
	
	

}

