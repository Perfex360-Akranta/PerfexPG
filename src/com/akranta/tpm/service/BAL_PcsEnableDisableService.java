/*Created By : Siddharth.A*/
package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.model.BAL_PcsTlEnablelosscapture;
import com.akranta.tpm.model.BAL_PcsTlLosscelllink;
import com.akranta.tpm.model.BAL_PcsTlLossphenomenamst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlLosscelllink;
import com.akranta.tpm.model.PcsTlLossphenomenamst;


public interface BAL_PcsEnableDisableService {
	
	public List<String[]> getAllPcsEnblDsblMCH(String factId, String sectId, String cellId, String flid) throws Exception;
	public List<String[]> getAllPcsEnblDsblSECT(String factId, String sectId)throws Exception;
	public List<String[]> getAllPcsEnblDsblCELL(String factId, String sectId,String cellId) throws Exception;
	//public List<PcsTlEnablelosscapture> save(List<PcsTlEnablelosscapture> pcsEblDsblList,List<PcsTlEnablelosscapture> existPcsTlEnablelosscapture,
		//	PcsEnableDisableFormBean pcsEnableDisableFormBean)throws Exception;
	public List<BAL_PcsTlEnablelosscapture> save(List<BAL_PcsTlEnablelosscapture> pcsEblDsblList,String createdby)throws Exception;
	public List<String[]> getAllSectCount(String sectId);
	//public List<ComboBox> getPhenomenaComboList(CommonFilter commonFilter)throws Exception;
	//elumalai
	public List<ComboBox> getPhenomenaComboList(ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getMainLossComboList(ComboFilter comboFilter)throws Exception;
	
	//public List<ComboBox> getMainLossComboList(CommonFilter commonFilter)throws Exception;
	public List<String[]> getFactory(GridParams gridParams, String lossId, String phenID)throws Exception;
	public List<String[]> getPhenomena(GridParams gridParams, String phenId, String lossId)throws Exception;
	public List<String[]> getLossNames(GridParams gridParams, String jhId)throws Exception;
	public List<String[]> getJH(GridParams gridParams, String lossId)throws Exception;
	public BAL_PcsTlLossphenomenamst createPhenomenaLossFactoryLink(String usrm_ccno,
			BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst)throws Exception;
	//public List<ComboBox> getfactid(ComboFilter comboFilter)throws Exception;
	public BAL_PcsTlLosscelllink createLossJHLink(String usrm_ccno,
			List<BAL_PcsTlLosscelllink> pcsTlLosscelllinkList)throws Exception;
	
	public List<String[]> getComboTextContent(String phenId, String type)throws Exception;
	public BAL_PcsTlLossphenomenamst updatePhenomenaLossFactoryLink(String usrm_ccno,
			BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst)throws Exception;
	
	//elumalai
	List<String[]> getPcsEnblDsblMCHWithHeaders(String sectId) throws Exception;                                      // new
	public void BAL_PcsEnableDisableServiceImplJwt(String jwtToken);
	
	
	



}
