package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.model.BAL_PcsTlEnablelosscapture;
import com.akranta.tpm.model.PcsTlEnablelosscapture;
import com.akranta.tpm.model.PcsTlLossphenomenamst;

public interface BAL_PcsTlEnablelosscaptureDao {

	public abstract PcsTlEnablelosscapture create(PcsTlEnablelosscapture pcsTlEnablelosscapture) throws Exception;
	public abstract PcsTlEnablelosscapture update(PcsTlEnablelosscapture pcsTlEnablelosscapture) throws Exception;
	public abstract PcsTlEnablelosscapture delete(PcsTlEnablelosscapture pcsTlEnablelosscapture) throws Exception;
	public List<String[]> getAllPcsEnblDsblMCH(String factId, String sectId, String cellid, String flid)throws Exception;
	public List<String[]> getAllPcsEnblDsblCELL(String factId, String sectId,String cellId) throws Exception;
	public List<String[]> getAllPcsEnblDsblSECT(String factId, String sectId) throws Exception;
	//public List<PcsTlEnablelosscapture> save(List<PcsTlEnablelosscapture> newpcsTlEnablelosscapture, 
			//PcsEnableDisableFormBean pcsEnableDisableFormBean)throws Exception;
	public List<BAL_PcsTlEnablelosscapture> save(List<BAL_PcsTlEnablelosscapture> newpcsTlEnablelosscapture)throws Exception;
	public List<String[]> getAllSectCount(String sectId);
	public abstract List<String[]> getFactory(GridParams gridParams, String lossId, String phenID)throws Exception;
	public abstract List<String[]> getJH(GridParams gridParams, String lossId)throws Exception;
	public abstract List<String[]> getPhenomena(GridParams gridParams, String phenId, String lossId)throws Exception;
	public abstract List<String[]> getLossNames(GridParams gridParams, String jhId)throws Exception;
	public abstract PcsTlLossphenomenamst getFactoryLossPhenomenaLink(PcsTlLossphenomenamst pcsTlLossphenomenamst)throws Exception;
	
	//elumalai
	List<String[]> getPcsEnblDsblMCHWithHeaders(String sectId) throws Exception;                                      // new
	public abstract void BAL_PcsTlEnablelosscaptureDaoImplJwt(String jwtToken); 
	
	
}

