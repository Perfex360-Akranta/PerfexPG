package com.akranta.tpm.service;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CauseBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.BAL_PhenCauseBean;
import com.akranta.tpm.bean.BAL_PhenomenaBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_BdmTlPhenomenamst;
import com.akranta.tpm.model.BAL_BdmTlCausemst;
import com.akranta.tpm.model.BAL_BdmTlPhncauselink;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlAssemblymst;


import java.util.List;

public interface BAL_PhenCauseService {
	
	public List<ComboBox> getsAssemblyCombo(String condSql) throws Exception;
	public List<ComboBox> getPhenomena(String condSql) throws Exception;
	public List<ComboBox> getCause(String condSql) throws Exception;
//	
	public BAL_BdmTlPhenomenamst create(BAL_BdmTlPhenomenamst newBdmTlPhenomenamst,BAL_BdmTlPhenomenamst oldBdmTlPhenomenamst, BAL_PhenomenaBean phenBean ) throws ValidationExceptions, Exception;
	public BAL_BdmTlPhenomenamst update(BAL_BdmTlPhenomenamst newBdmTlPhenomenamst,BAL_BdmTlPhenomenamst oldBdmTlPhenomenamst, BAL_PhenomenaBean phenBean )  throws Exception;
	public void BAL_PhenCauseServiceImplJwt(String JwtToken);
	List<String[]> getPhenomenaGridData(CommonFilter commonFilter, List<String> assemblyIdList) throws Exception;
//	public BdmTlPhenomenamst delete(BdmTlPhenomenamst bdmTlPhenomenamst ) throws Exception;
//	
//	//public BdmTlPhenomenamst phenSelect(String Util) throws Exception;
//
////cause	
	public BAL_BdmTlCausemst create(BAL_BdmTlCausemst newBdmTlCausemst,BAL_BdmTlCausemst oldBdmTlCausemst, BAL_CauseBean causeBean ) throws ValidationExceptions, Exception;
	public BAL_BdmTlCausemst update(BAL_BdmTlCausemst newBdmTlCausemst,BAL_BdmTlCausemst oldBdmTlCausemst, BAL_CauseBean causeBean )  throws Exception;
	
	//	public BdmTlCausemst delete(BdmTlCausemst bdmBdmTlCausemst ) throws Exception;
//	
	//public BdmTlCausemst causeSelect(String Util) throws Exception;
//link	
	public BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink newBdmTlPhncauselink,BAL_BdmTlPhncauselink oldBdmTlPhncauselink, BAL_PhenCauseBean phenCauseBean ) throws ValidationExceptions, Exception;
	public BAL_BdmTlPhncauselink update(BAL_BdmTlPhncauselink newBdmTlPhncauselink,BAL_BdmTlPhncauselink oldBdmTlPhncauselink, BAL_PhenCauseBean phenCauseBean )  throws Exception;
	public BAL_BdmTlPhncauselink delete(BAL_BdmTlPhncauselink bdmTlPhncauselink ) throws Exception;
	BAL_BdmTlPhncauselink deleteCause(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception;
	
	List<String[]> getCauseGridData(CommonFilter commonFilter, String phenId) throws Exception;
	

	public BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink bdmTlPhncauselink,List<String> phenCauseValues)throws Exception;
	public BAL_BdmTlPhncauselink select(String Util) throws Exception;	
	public List<BAL_BdmTlPhncauselink> getAllLocation(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception;
	public  List<String[]>  getSearchNode(String searchNode) throws Exception;
	public String getCmpFromPhnCauseLink(String searchNode) throws Exception;
	public List<String []> getParentElem(String elemId) throws Exception;
	public String getTotalCount(List<String> childElem,String formfield) throws Exception;
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end,GridParams gridParams) throws Exception;
	public BAL_BdmTlPhenomenamst selectPhenomena(String keyid) throws Exception;
	public BAL_BdmTlCausemst selectCause(String keyid) throws Exception;
	
	
}



