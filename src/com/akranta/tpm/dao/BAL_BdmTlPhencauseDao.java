package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.BAL_PhenCauseBean;
import com.akranta.tpm.model.BAL_BdmTlPhenomenamst;
import com.akranta.tpm.model.BAL_BdmTlCausemst;
import com.akranta.tpm.model.BAL_BdmTlPhncauselink;

public interface BAL_BdmTlPhencauseDao {

//	public abstract BdmTlPhenomenamst create(BdmTlPhenomenamst bdmTlPhenomenamst) throws Exception;
//	public abstract BdmTlPhenomenamst update(BdmTlPhenomenamst bdmTlPhenomenamst) throws Exception;
//	public abstract BdmTlPhenomenamst delete(BdmTlPhenomenamst bdmTlPhenomenamst) throws Exception;
//
//	//public BdmTlPhenomenamst phenSelect(String keyid) throws Exception;
//	
//	public abstract BdmTlCausemst create(BdmTlCausemst bdmTlCausemst) throws Exception;
//	public abstract BdmTlCausemst update(BdmTlCausemst bdmTlCausemst) throws Exception;
//	public abstract BdmTlCausemst delete(BdmTlCausemst bdmTlCausemst) throws Exception;

	//public BdmTlCausemst causeSelect(String keyid) throws Exception;
	
	public abstract BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink bdmTlPhncauselink, BAL_PhenCauseBean phenCauseBean) throws Exception;
	public abstract BAL_BdmTlPhncauselink update(BAL_BdmTlPhncauselink bdmTlPhncauselink, BAL_PhenCauseBean phenCauseBean) throws Exception;
	public abstract BAL_BdmTlPhncauselink delete(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception;
	BAL_BdmTlPhncauselink deleteCause(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception;
	public BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink bdmTlPhncauselink,List<String> phenCauseValues) throws Exception;
	public BAL_BdmTlPhncauselink select(String keyid) throws Exception;
	public List<BAL_BdmTlPhncauselink> getAllLocation(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception;
	public  List<String[]>  getSearchNode(String searchNode) throws Exception;
	public  String getCmpFromPhnCauseLink(String searchNode) throws Exception;
	public List<String []> getParentElem(String elemId) throws Exception;
	public String getTotalCount(List<String> childElem,String formfield) throws Exception;
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end,GridParams gridParams) throws Exception;
	public BAL_BdmTlPhenomenamst selectPhenomena(String keyid) throws Exception;
	public BAL_BdmTlCausemst selectCause(String keyid) throws Exception;
	
	
}

