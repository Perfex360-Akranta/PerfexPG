/*Created By : Siddharth.A*/
package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.model.PcsTlEnablelosscapture;
import com.akranta.tpm.model.PcsTlLosscelllink;
import com.akranta.tpm.model.PcsTlLossphenfactorylink;
import com.akranta.tpm.model.PcsTlLossphenomenamst;

import net.sf.json.JSONObject;


public interface PcsEnableDisableService {
	
	public List<String[]> getAllPcsEnblDsblMCH(String factId, String sectId, String cellId, String flid) throws Exception;
	public List<String[]> getAllPcsEnblDsblSECT(String factId, String sectId)throws Exception;
	public List<String[]> getAllPcsEnblDsblCELL(String factId, String sectId,String cellId) throws Exception;
	public List<PcsTlEnablelosscapture> save(List<PcsTlEnablelosscapture> pcsEblDsblList,List<PcsTlEnablelosscapture> existPcsTlEnablelosscapture,
			PcsEnableDisableFormBean pcsEnableDisableFormBean)throws Exception;
	public List<String[]> getAllSectCount(String sectId);
	//public List<ComboBox> getPhenomenaComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getPhenomenaComboList(ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getMainLossComboList(ComboFilter comboFilter)throws Exception;
	//public List<ComboBox> getMainLossComboList(CommonFilter commonFilter)throws Exception;
	public List<String[]> getFactory(GridParams gridParams, String lossId, String phenID)throws Exception;
	public List<String[]> getPhenomena(GridParams gridParams, String phenId, String lossId)throws Exception;
	public List<String[]> getLossNames(GridParams gridParams, String jhId)throws Exception;
	public List<String[]> getJH(GridParams gridParams, String lossId)throws Exception;
	public PcsTlLossphenomenamst createPhenomenaLossFactoryLink(String usrm_ccno,
			PcsTlLossphenomenamst pcsTlLossphenomenamst)throws Exception;
	
	public PcsTlLosscelllink createLossJHLink(String usrm_ccno,
			List<PcsTlLosscelllink> pcsTlLosscelllinkList)throws Exception;
	
	public List<String[]> getComboTextContent(String phenId, String type)throws Exception;
	public PcsTlLossphenomenamst updatePhenomenaLossFactoryLink(String usrm_ccno,
			PcsTlLossphenomenamst pcsTlLossphenomenamst)throws Exception;
	public void savemultiple(List<PcsTlLossphenfactorylink> pcsTlLossphenomenamstmap)  throws Exception;	
	public List<String[]> getLossPhenMst( GridParams gridParams, CommonFilter commonFilter)throws Exception;
	public List<String[]> getPFLProd( GridParams gridParams,CommonFilter commonFilter)
	throws Exception;
	public PcsTlLossphenfactorylink savemultiple1(PcsTlLossphenfactorylink pcsTlLossphenfactorylink, String usrm_ccno,String pillCode,String drillLevel,String deptId,String isIndicatorFactory )throws Exception;
	public String validatePhenomenaLink(PcsTlLossphenfactorylink kpiTlIndicatorDeptLink) throws Exception;
	
	
	// ------- Vignesh
	public  PcsTlLossphenomenamst deletePhenomenaLoss(String usrm_ccno, PcsTlLossphenomenamst pcsTlLossphenomenamst,  String mstKeyid) throws Exception;
	
	
	public   Workbook getExcelreport(CommonFilter commonFilter,
			JSONObject tblJSONObj, String formats, GridParams gridParams) throws Exception ;

	// ------- Vignesh
	
	public void PcsEnableDisableServiceImplJwt(String string);

}
