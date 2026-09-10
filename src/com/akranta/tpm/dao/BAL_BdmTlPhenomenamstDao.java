package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.model.BAL_BdmTlCausemst;
import com.akranta.tpm.model.BAL_BdmTlPhenomenamst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;

public interface BAL_BdmTlPhenomenamstDao {

	public abstract BAL_BdmTlPhenomenamst create(BAL_BdmTlPhenomenamst bdmTlPhenomenamst,BAL_BDFormBean bdFormBean) throws Exception;
	public abstract BAL_BdmTlPhenomenamst update(BAL_BdmTlPhenomenamst bdmTlPhenomenamst) throws Exception;
	public abstract BAL_BdmTlPhenomenamst delete(BAL_BdmTlPhenomenamst bdmTlPhenomenamst) throws Exception;
	public  List<FunctionalLocn> getPhenomenaCause(FunctionalLocn functionalLocn) throws Exception;
	public abstract BAL_BdmTlPhenomenamst createPhenomena(BAL_BdmTlPhenomenamst bdmTlPhenomenamst)throws Exception;
	public abstract BAL_BdmTlPhenomenamst updatePhenomena(BAL_BdmTlPhenomenamst bdmTlPhenomenamst)throws Exception;
	void BAL_BdmTlPhenomenamstDaoImplJwt(String jwtToken);
	List<String[]> getPhenomenaGridData(CommonFilter commonFilter, List<String> assemblyIdList) throws Exception;
	List<String[]> getCauseList(CommonFilter commonFilter, String phenId) throws Exception;
	
	
	public abstract BAL_BdmTlCausemst createCause(BAL_BdmTlCausemst bdmTlCausemst)throws Exception;
	public abstract BAL_BdmTlCausemst updateCause(BAL_BdmTlCausemst bdmTlCausemst)throws Exception;
	
	public List<String[]> getUndefinedPhenomena(String mode) throws Exception;
	public Workbook getUndefPhenReport(String mode, JSONObject colmodel,String rptFormat)throws Exception;

}

