/*Created By : Siddharth.A*/
package com.akranta.tpm.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.PcsEntryDao;
import com.akranta.tpm.dao.PcsTlEnablelosscaptureDao;
import com.akranta.tpm.dao.PcsTlLosscelllinkDao;
import com.akranta.tpm.dao.PcsTlLossphenomenamstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.PcsEntryDaoImpl;
import com.akranta.tpm.dao.impl.PcsTlEnablelosscaptureDaoImpl;
import com.akranta.tpm.dao.impl.PcsTlLosscelllinkDaoImpl;
import com.akranta.tpm.dao.impl.PcsTlLossphenomenamstDaoImpl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlEnablelosscapture;
import com.akranta.tpm.model.PcsTlLosscelllink;
import com.akranta.tpm.model.PcsTlLossphenfactorylink;
import com.akranta.tpm.model.PcsTlLossphenomenamst;
import com.akranta.tpm.service.PcsEnableDisableService;
import com.akranta.tpm.service.api.PcsEnableDisableServiceApi;
import com.akranta.tpm.service.api.PcsEntryServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class PcsEnableDisableServiceImpl implements PcsEnableDisableService {
	
	private static final PcsTlLossphenomenamst PcsTlLossphenfactorylink = null;
	private PcsTlEnablelosscaptureDao pcsTlEnablelosscaptureDao ;
	private PcsTlLossphenomenamstDao pcsTlLossphenomenamstDao;
	private PcsTlLosscelllinkDao pcsTlLosscelllinkDao;
	private PcsEntryDao pcsEntryDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	
	private PcsEnableDisableServiceApi  pcsEnableDisableServiceApi;
	
	public PcsEnableDisableServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsTlLosscelllinkDao = new PcsTlLosscelllinkDaoImpl(dbActionTemplate);
		pcsTlEnablelosscaptureDao = new PcsTlEnablelosscaptureDaoImpl(dbActionTemplate);
		pcsTlLossphenomenamstDao=new PcsTlLossphenomenamstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
		pcsEntryDao = new PcsEntryDaoImpl(dbActionTemplate);
 	}
	
	
	public void PcsEnableDisableServiceImplJwt(String JwtToken){
	    try{
	    //	oplTlMstDao.OplTlMstDaoImplJwt(JwtToken);   // dao side
	        // (Optional) if you want service-level direct access
	    	pcsTlEnablelosscaptureDao.PcsTlEnablelosscaptureDaoImplJwt(JwtToken);
	    	pcsEnableDisableServiceApi = new PcsEnableDisableServiceApi(JwtToken);
	    	
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	public void setPlmTlSparedtlDao(PcsTlEnablelosscaptureDao pcsTlEnablelosscaptureDao)
	{
		this.pcsTlEnablelosscaptureDao = pcsTlEnablelosscaptureDao;
	}


	@Override
	public List<String[]> getAllPcsEnblDsblMCH(String factId,String sectId,String cellid, String flid) throws Exception {
			// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside service Impl");
		return pcsTlEnablelosscaptureDao.getAllPcsEnblDsblMCH(factId,sectId,cellid, flid);
	}

	@Override
	public List<String[]> getAllPcsEnblDsblSECT(String factId, String sectId)throws Exception {
		// TODO Auto-generated method stub
		return pcsTlEnablelosscaptureDao.getAllPcsEnblDsblSECT(factId,sectId);
	}

	@Override
	public List<String[]> getAllPcsEnblDsblCELL(String factId, String sectId,String cellId)throws Exception {
		// TODO Auto-generated method stub
		return pcsTlEnablelosscaptureDao.getAllPcsEnblDsblCELL(factId,sectId,cellId);
	}
	
	@Override
	public List<String[]> getAllSectCount(String sectId) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside Service Impl for Cnt");
		return pcsTlEnablelosscaptureDao.getAllSectCount(sectId);
	}

	public List<PcsTlEnablelosscapture> save(List<PcsTlEnablelosscapture> newpcsTlEnablelosscapture,List<PcsTlEnablelosscapture> oldpcsTlEnablelosscapture,  PcsEnableDisableFormBean pcsEnableDisableFormBean)  throws Exception 
	{
		
	/*	for( PcsTlEnablelosscapture pcsTlEnablelosscapture:newpcsTlEnablelosscapture)
		{
			CommonMessage.debugMsg("newPlmTlSparedtl in save func"+newpcsTlEnablelosscapture.size());
			validations.validate(pcsTlEnablelosscapture,"SparesPickupCreation","create");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			
		}
		
		*/
	
		if (pcsEnableDisableFormBean.getForCell().equals("Y")) {
			newpcsTlEnablelosscapture=fillForCellValue(newpcsTlEnablelosscapture,oldpcsTlEnablelosscapture,pcsEnableDisableFormBean);
		} else {			
			fillValues(newpcsTlEnablelosscapture,oldpcsTlEnablelosscapture,pcsEnableDisableFormBean);
		}
	
		return  pcsTlEnablelosscaptureDao.save(newpcsTlEnablelosscapture, pcsEnableDisableFormBean);
	}

	private List<PcsTlEnablelosscapture> fillValues(List<PcsTlEnablelosscapture> newpcsTlEnablelosscapture,List<PcsTlEnablelosscapture> oldpcsTlEnablelosscapture,
			PcsEnableDisableFormBean pcsEnableDisableFormBean)
	{
		// TODO Auto-generated method stub
	
		CommonMessage.debugMsg("Inside Service impl");
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		
		List<PcsTlEnablelosscapture> pcsTlEnablelosscaptureList =new ArrayList<PcsTlEnablelosscapture>();
		if( oldpcsTlEnablelosscapture != null && oldpcsTlEnablelosscapture.size() > 0 )
	//		oldPlmTlSparedtl =  (PlmTlSparedtl)oldPlmTlSparedtls.get(0);
		//	pcsTlEnablelosscaptureList=(List<PcsTlEnablelosscapture>)oldpcsTlEnablelosscapture.get(0);
		
	//	CommonMessage.debugMsg("oldPlmTlSparedtl ="+oldPlmTlSparedtl.getPspdCreatedon());
		CommonMessage.debugMsg("oldpcsTlEnablelosscapture="+oldpcsTlEnablelosscapture.size());
		CommonMessage.debugMsg("sd");
		System.out.print("newpcsTlEnablelosscapture"+newpcsTlEnablelosscapture.size());
		for( PcsTlEnablelosscapture pcsTlEnablelosscapture : newpcsTlEnablelosscapture)
		{	
			CommonMessage.debugMsg("Inside for loop");
			CommonMessage.debugMsg("srvce Created on="+pcsTlEnablelosscapture.getPelcCreatedon());
		//	if(pcsTlEnablelosscapture.getPelcKeyid()==null)			
		//	{	
				pcsTlEnablelosscapture.setPelcCreatedon(dateTime);
				CommonMessage.debugMsg("created on if keyid null"+pcsTlEnablelosscapture.getPelcCreatedon());
		//	}	
		//	else
		//	{
		//		pcsTlEnablelosscapture.setPelcCreatedon(pcsTlEnablelosscapture.getPelcCreatedon());
			//}
			pcsTlEnablelosscapture.setPelcModifiedon(dateTime);
		//	pcsTlEnablelosscapture.setPelcCreatedby(sprPckupFormBean.getCreatedBy());
			pcsTlEnablelosscapture.setPelcCreatedby("EMP/0001");
		
			pcsTlEnablelosscapture.setPelcActive("Y");
			if(pcsTlEnablelosscapture.getPelcEffectivefrom()==null)
				pcsTlEnablelosscapture.setPelcEffectivefrom(dateTime);
			
			if(pcsTlEnablelosscapture.getPelcEffectivetill()==null)
				pcsTlEnablelosscapture.setPelcEffectivetill(dateTime);
			
			if(pcsTlEnablelosscapture.getPelcMachineid()==null)
				pcsTlEnablelosscapture.setPelcMachineid("{}");
			
			if(pcsTlEnablelosscapture.getPelcCellid()==null)
				pcsTlEnablelosscapture.setPelcCellid("{}");
			

			pcsTlEnablelosscapture.setPelcFlid(pcsEnableDisableFormBean.getFlid());
			if(pcsTlEnablelosscapture.getPelcFlid()==null)
				pcsTlEnablelosscapture.setPelcFlid("{}");
			

			if(pcsTlEnablelosscapture.getPelcElementid()==null)
				pcsTlEnablelosscapture.setPelcElementid("{}");

			if(pcsTlEnablelosscapture.getPelcType()==null)
				pcsTlEnablelosscapture.setPelcType("S");
			
			if(pcsTlEnablelosscapture.getPelcIsgroupbased()==null)
				pcsTlEnablelosscapture.setPelcIsgroupbased("N");
			
			pcsTlEnablelosscaptureList.add(pcsTlEnablelosscapture);
		}
		return pcsTlEnablelosscaptureList;
		
	}

	private List<PcsTlEnablelosscapture> fillForCellValue(List<PcsTlEnablelosscapture> newpcsTlEnablelosscapture,List<PcsTlEnablelosscapture> oldpcsTlEnablelosscapture,
			PcsEnableDisableFormBean pcsEnableDisableFormBean)
	{

		CommonMessage.debugMsg("Inside fillForCellValue");
		
		String dateTime = CommonFunctions.dateTimeNow();		
		
		List<PcsTlEnablelosscapture> pcsTlEnablelosscaptureList =new ArrayList<PcsTlEnablelosscapture>();
		
		//if( oldpcsTlEnablelosscapture != null && oldpcsTlEnablelosscapture.size() > 0 )
		
		//System.out.print("newpcsTlEnablelosscapture"+newpcsTlEnablelosscapture.size());
		
		for( PcsTlEnablelosscapture pcsTlEnablelosscapture : newpcsTlEnablelosscapture)
		{	
			CommonMessage.debugMsg("Inside for loop");
			//CommonMessage.debugMsg("srvce Created on="+pcsTlEnablelosscapture.getPelcCreatedon());
			
			pcsTlEnablelosscapture.setPelcCreatedon(dateTime);
			//CommonMessage.debugMsg("created on if keyid null"+pcsTlEnablelosscapture.getPelcCreatedon());
			pcsTlEnablelosscapture.setPelcModifiedon(dateTime);
			pcsTlEnablelosscapture.setPelcCreatedby("EMP/0001");
		
			pcsTlEnablelosscapture.setPelcActive("Y");
			if(pcsTlEnablelosscapture.getPelcEffectivefrom()==null)
				pcsTlEnablelosscapture.setPelcEffectivefrom(dateTime);
			
			if(pcsTlEnablelosscapture.getPelcEffectivetill()==null)
				pcsTlEnablelosscapture.setPelcEffectivetill(dateTime);
			
			//if(pcsTlEnablelosscapture.getPelcMachineid()==null)
				pcsTlEnablelosscapture.setPelcMachineid("{}");
			
			if(pcsTlEnablelosscapture.getPelcCellid()==null)
				pcsTlEnablelosscapture.setPelcCellid("{}");

			
			pcsTlEnablelosscapture.setPelcFlid(pcsEnableDisableFormBean.getFlid());
			if(pcsTlEnablelosscapture.getPelcFlid()==null)
				pcsTlEnablelosscapture.setPelcFlid("{}");
			
			if(pcsTlEnablelosscapture.getPelcElementid()==null)
				pcsTlEnablelosscapture.setPelcElementid("{}");

			//if(pcsTlEnablelosscapture.getPelcType()==null)
			pcsTlEnablelosscapture.setPelcType("C");
			
			if(pcsTlEnablelosscapture.getPelcIsgroupbased()==null)
				pcsTlEnablelosscapture.setPelcIsgroupbased("N");
			
			CommonMessage.debugMsg("before List loop");
			pcsTlEnablelosscaptureList.add(pcsTlEnablelosscapture);
			return pcsTlEnablelosscaptureList;
		}
		
		CommonMessage.debugMsg("Exit for loop");
		return pcsTlEnablelosscaptureList;
		
	}

	@Override
	public List<ComboBox> getPhenomenaComboList(ComboFilter comboFilter)
			throws Exception {
		// TODO Auto-generated method stub
		
		//ComboFilter phenomena = new ComboFilter();
		comboFilter.setIdField("PLPM_KEYID");
		comboFilter.setNameField("PLPM_NAME");
		comboFilter.setTableName("PCS_TL_LOSSPHENOMENAMST");
		comboFilter.setCondSql(" AND PLPM_NAME <> '-'");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getMainLossComboList(ComboFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
	//	ComboFilter mainLoss = new ComboFilter();
		commonFilter.setIdField("KEYID");
		commonFilter.setNameField("CHILDLOSS");
		commonFilter.setTableName("pcs_vw_LOSSNAMES,pcs_tl_lossphenomenamst");
		commonFilter.setCondSql("and KEYID =PLPM_MAINLOSS");
		return commonFilterDao.fillComboValues(commonFilter);
	}

	@Override
	public List<String[]> getFactory(GridParams gridParams,String lossId,String phenID) throws Exception {
		// TODO Auto-generated method stub

		//	return  pcsTlEnablelosscaptureDao.getFactory(gridParams,lossId,phenID);
		return  pcsEnableDisableServiceApi.getFactory(gridParams,lossId,phenID);
	}

	@Override
	public List<String[]> getJH(GridParams gridParams,String lossId) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlEnablelosscaptureDao.getJH(gridParams,lossId);
	}

	@Override
	public List<String[]> getPhenomena(GridParams gridParams,String phenId,String lossId) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlEnablelosscaptureDao.getPhenomena(gridParams,phenId,lossId);
	}

	@Override
	public List<String[]> getLossNames(GridParams gridParams,String jhId) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlEnablelosscaptureDao.getLossNames(gridParams,jhId);
	}
	
	@Override
	public PcsTlLossphenomenamst createPhenomenaLossFactoryLink(String usrm_ccno,
			PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception 
	{
		String validationsFor = "create";
		// ----------- validations Commented -- Vignesh 12Nov2025 ----------------- //
		validations.validate( pcsTlLossphenomenamst,"PhenLossReason",validationsFor);
		/*List<PcsTlLossphenfactorylink> newPcsTlLossphenomenamstLinks = pcsTlLossphenomenamst.getpcsTlLossphenfactorylink();
		CommonMessage.debugMsg("newPcsTlLossphenomenamstLinks.size():"+newPcsTlLossphenomenamstLinks.size());
		for(int i=0;i<newPcsTlLossphenomenamstLinks.size();i++)
		{
			validations.validate( newPcsTlLossphenomenamstLinks.get(i),"PhenLossReasonFactLink",validationsFor);
		}*/
		fillValues(pcsTlLossphenomenamst,usrm_ccno);
		CommonMessage.debugMsg( " inside create of service impl enable disable "+ usrm_ccno);
	
	//	return  pcsTlLossphenomenamstDao.create(pcsTlLossphenomenamst);
		// --- Vignesh Api
		return  pcsEnableDisableServiceApi.create(pcsTlLossphenomenamst);
		
	}
	
	public PcsTlLosscelllink createLossJHLink(String usrm_ccno,
			List<PcsTlLosscelllink> pcsTlLosscelllinkList)throws Exception {
	
		//String validationsFor = "create";
		//validations.validate( pcsTlLosscelllinkList,"PhenLossReason",validationsFor);
		CommonMessage.debugMsg("pcsTlLosscelllinkList.size"+pcsTlLosscelllinkList.size());
		
		List<PcsTlLosscelllink> pcsTlLosscelllinkListNew = fillValuesForJHLink(usrm_ccno, pcsTlLosscelllinkList);
		
		CommonMessage.debugMsg("pcsTlLosscelllinkListNew.size"+pcsTlLosscelllinkListNew.size());
		return  pcsTlLosscelllinkDao.createJHLink(pcsTlLosscelllinkListNew);
		
	}

	
	public List<PcsTlLosscelllink> fillValuesForJHLink(String usrm_ccno, List<PcsTlLosscelllink> pcsTlLosscelllinkList) {
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		CommonMessage.debugMsg("fillValuesForJHLink ");
		
		List<PcsTlLosscelllink> newPcsTlLosscelllink = new ArrayList<PcsTlLosscelllink>();
		for( PcsTlLosscelllink pcsTlLosscelllink1 : pcsTlLosscelllinkList)
		{
			
			CommonMessage.debugMsg("JH Tempfield2 "+pcsTlLosscelllink1.getPlflTempfield2());
			
			if (UIUtils.isValidKeyId(pcsTlLosscelllink1.getPlflTempfield2())  ) { 
				
				if(pcsTlLosscelllink1.getPlflTempfield2().equals("1") || 
						( pcsTlLosscelllink1.getPlflKeyid()!=null && UIUtils.isValidKeyId(pcsTlLosscelllink1.getPlflKeyid()))) {
					
					CommonMessage.debugMsg("JH ====="+pcsTlLosscelllink1.getPlflTempfield2());
					PcsTlLosscelllink pcsTlLosscelllink = new PcsTlLosscelllink();
				
					pcsTlLosscelllink.setPlflEffectivedate(dateTime);
					pcsTlLosscelllink.setPlflInactivedate(dateTime);
					
					if(pcsTlLosscelllink1.getPlflTempfield2().equals("1"))
						pcsTlLosscelllink.setPlflActive("Y");
					else
						pcsTlLosscelllink.setPlflActive("N");
					
					pcsTlLosscelllink.setPlflCreatedby(usrm_ccno);
					pcsTlLosscelllink.setPlflCreatedon(dateTime);
					pcsTlLosscelllink.setPlflModifiedon(dateTime);
					pcsTlLosscelllink.setPlflTempfield1("-");
					pcsTlLosscelllink.setPlflTempfield2("-");
					
					pcsTlLosscelllink.setPlflCellid(pcsTlLosscelllink1.getPlflCellid());
					pcsTlLosscelllink.setPlflParameterid(pcsTlLosscelllink1.getPlflParameterid());
					
					if (UIUtils.isValidKeyId(pcsTlLosscelllink1.getPlflKeyid()))
						pcsTlLosscelllink.setPlflKeyid(pcsTlLosscelllink1.getPlflKeyid());
					else
						pcsTlLosscelllink.setPlflKeyid(" ");
					
					newPcsTlLosscelllink.add(pcsTlLosscelllink);
					
				}
			}	
		}
		
		return newPcsTlLosscelllink;
		
	}

	public List<PcsTlLossphenfactorylink> fillValues(PcsTlLossphenomenamst pcsTlLossphenomenamst,String usrm_ccno)
	{
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		pcsTlLossphenomenamst.setPlpmActive("Y");
		pcsTlLossphenomenamst.setPlpmCreatedby(usrm_ccno);
		pcsTlLossphenomenamst.setPlpmCreatedon(dateTime);
		pcsTlLossphenomenamst.setPlpmModifiedon(dateTime);
		pcsTlLossphenomenamst.setPlpmTempfield1("-");
		pcsTlLossphenomenamst.setPlpmTempfield2("-");
		pcsTlLossphenomenamst.setPlpmTempfield3("-");
		List<PcsTlLossphenfactorylink> newPcsTlLossphenomenamstLinks = pcsTlLossphenomenamst.getpcsTlLossphenfactorylink();
		List<PcsTlLossphenfactorylink> newPcsTlLossphenfactorylinkList = new ArrayList<PcsTlLossphenfactorylink>();
		int index=0;
		for( PcsTlLossphenfactorylink pcsTlLossphenfactorylink : newPcsTlLossphenomenamstLinks)
		{	
			pcsTlLossphenfactorylink.setPpflActive("Y");
			pcsTlLossphenfactorylink.setPpflCreatedby(usrm_ccno);
			pcsTlLossphenfactorylink.setPpflCreatedon(dateTime);
			pcsTlLossphenfactorylink.setPpflModifiedon(dateTime);
			pcsTlLossphenfactorylink.setPpflTempfield1("-");
			pcsTlLossphenfactorylink.setPpflTempfield2("-");
			PcsTlLossphenfactorylink pcsTlLossphenfactorylink1 = newPcsTlLossphenomenamstLinks.get(index);
			pcsTlLossphenfactorylink.setPpflFactoryid(pcsTlLossphenfactorylink1.getPpflFactoryid());
			index++;
			newPcsTlLossphenfactorylinkList.add(pcsTlLossphenfactorylink);
			
		}
		return newPcsTlLossphenfactorylinkList;
	}

	@Override
	public List<String[]> getComboTextContent(String phenId,String type) throws Exception {
		
		return pcsTlLossphenomenamstDao.getComboTextContent(phenId,type);
	}

	@Override
	public PcsTlLossphenomenamst updatePhenomenaLossFactoryLink(String usrm_ccno,
			PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception {
		fillValues(pcsTlLossphenomenamst,usrm_ccno);
		CommonMessage.debugMsg("inside service impl for pcse nable disable");
		return  pcsTlLossphenomenamstDao.update(pcsTlLossphenomenamst);
		
	}
	
	
	@Override
	public PcsTlLossphenomenamst deletePhenomenaLoss(String usrm_ccno,
			PcsTlLossphenomenamst pcsTlLossphenomenamst, String mstKeyid) throws Exception {
		fillValues(pcsTlLossphenomenamst,usrm_ccno);
		CommonMessage.debugMsg("inside service impl for pcse enable disable for delete " + mstKeyid );
	//	return  pcsTlLossphenomenamstDao.delete(pcsTlLossphenomenamst,mstKeyid);
		return  pcsEnableDisableServiceApi.delete(pcsTlLossphenomenamst,mstKeyid);
		
	}
	 // -- vignesh 
	
	public  Workbook getExcelreport(CommonFilter commonFilter,
			JSONObject tblJSONObj, String formats,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		return pcsEntryDao.getExcelreport(commonFilter, tblJSONObj, formats,gridParams);
	}
	//------------- Vignesh added -------------------------------//
	
	// com.akranta.tpm.service.PcsTlLossphenomenamstService

	// com.akranta.tpm.service.impl.PcsTlLossphenomenamstServiceIm 
	    
	     
//	@Override
//	public com.akranta.tpm.model.PcsTlLossphenomenamst deletePhenomenaLoss(
//	        String usrm_ccno,
//	        com.akranta.tpm.model.PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception {
//
//	    String keyId = pcsTlLossphenomenamst.getPlpmKeyid();
//	    if (keyId == null || keyId.trim().isEmpty()) {
//	        throw new Exception("Phenomena KeyId is required");
//	    }
//
//	    int links = pcsTlLossphenomenamstDao.countLinksForPhenomena(keyId);
//	    if (links > 0) {
//	        // Business rule: do NOT delete if mapped to Functional Location
//	        throw new Exception("Cannot delete. This Phenomena is mapped to Functional Location(s) and must be unmapped first.");
//	    }
//
//	    // proceed with delete
//	    return pcsTlLossphenomenamstDao.delete(pcsTlLossphenomenamst);
//	}


	
	//------------- Vignesh added -------------------------------//

	@Override
	public void savemultiple(List<PcsTlLossphenfactorylink> pcsTlLossphenomenamstmap)throws Exception {
		Validations validations = new Validations();
		 validations.validate(pcsTlLossphenomenamstmap, "", "create");
		//fillValues( pcsTlLossphenomenamstmap,String usrm_ccno);
		  this.pcsTlEnablelosscaptureDao.savemultiple(pcsTlLossphenomenamstmap);
		
	}

	@Override
	public List<String[]> getLossPhenMst(GridParams gridParams,
			CommonFilter commonFilter) throws Exception {
		return pcsTlEnablelosscaptureDao.getLossPhenMst(gridParams, commonFilter);
		
	}
	
	public List<String[]> getPFLProd( GridParams gridParams,CommonFilter commonFilter)
	throws Exception {
return pcsTlEnablelosscaptureDao.getPFLProd(gridParams,commonFilter);
}
	public PcsTlLossphenfactorylink savemultiple1(PcsTlLossphenfactorylink pcsTlLossphenfactorylink, String usrm_ccno,String pillCode,String drillLevel,String deptId, String isIndicatorFactory )throws Exception
	{
		CommonMessage.debugMsg("U R in Service----------");
		CommonMessage.debugMsg("U R in Service----------" + usrm_ccno);
		
		
	//	if(isIndicatorFactory.equals("true"))
	//    phenomenaLossfillValues( pcsTlLossphenfactorylink, usrm_ccno);
		
		 //  changing to this - Vignesh 
		
		// Always set audit fields if we have any rows to save/delete
		if (pcsTlLossphenfactorylink.getmethodPillarFactlink() != null
		        && !pcsTlLossphenfactorylink.getmethodPillarFactlink().isEmpty()) {
		    phenomenaLossfillValues(pcsTlLossphenfactorylink, usrm_ccno);
		}
		
		pcsTlLossphenfactorylink.setPpflCreatedby(usrm_ccno);
		CommonMessage.debugMsg("U R in Service-------After Fill value---");
		 //-- vignesh -- adding 
		List<PcsTlLossphenfactorylink> list = pcsTlLossphenfactorylink.getmethodPillarFactlink();
		CommonMessage.debugMsg("pillarFactList size=" + (list == null ? 0 : list.size()));

		if (list != null) {
		    for (int i = 0; i < list.size(); i++) {
		        PcsTlLossphenfactorylink r = list.get(i);
		        if (r == null) continue;
		        CommonMessage.debugMsg("Row " + i +
		                " phenId=" + r.getPpflPlpmKeyid() +
		                " factoryId=" + r.getPpflFactoryid() +
		                " isDelete=" + r.getIsDelete() +
		                " createdBy=" + r.getPpflCreatedby());
		    }
		}

		CommonMessage.debugMsg("Parent createdBy=" + pcsTlLossphenfactorylink.getPpflCreatedby());

		
		
		
	//	return pcsTlEnablelosscaptureDao.savemultiple1(pcsTlLossphenfactorylink,pillCode,deptId,drillLevel);
		return pcsEnableDisableServiceApi.savemultiple1(pcsTlLossphenfactorylink,pillCode,deptId,drillLevel);
		
	}
	
	private List<PcsTlLossphenfactorylink> phenomenaLossfillValues(PcsTlLossphenfactorylink pcsTlLossphenfactorylink, String usrm_ccno) 
	{
		List<PcsTlLossphenfactorylink> newPhenomenaLinks = pcsTlLossphenfactorylink.getmethodPillarFactlink();
		
		List<PcsTlLossphenfactorylink> newPhenomenaLinkList = new ArrayList<PcsTlLossphenfactorylink>();
		int index=0;
		for( PcsTlLossphenfactorylink genKpiTlIndicatorDeptLink : newPhenomenaLinks)
		{	
			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			genKpiTlIndicatorDeptLink.setPpflCreatedby(usrm_ccno);
			
			genKpiTlIndicatorDeptLink.setPpflCreatedon(dateTime);
			genKpiTlIndicatorDeptLink.setPpflModifiedon(dateTime);
			//PcsTlLossphenfactorylink genKpiTlIndicatorDeptLink1 = (PcsTlLossphenfactorylink)pcsTlLossphenfactorylink.getmethodPillarFactlink().get(index);
			index++;
		//	genKpiTlIndicatorDeptLink.setKidlDepttype(kpiTlIndicatorDeptLink.getKidlDepttype());			
			if(genKpiTlIndicatorDeptLink.getPpflActive()==null)
			genKpiTlIndicatorDeptLink.setPpflActive("Y");
			genKpiTlIndicatorDeptLink.setPpflTempfield1("-");
			genKpiTlIndicatorDeptLink.setPpflTempfield2("-");		
			newPhenomenaLinkList.add(genKpiTlIndicatorDeptLink);
		}
		return newPhenomenaLinkList;
	}

	@Override
	public String validatePhenomenaLink(PcsTlLossphenfactorylink pcsTlLossphenfactorylink)
			throws Exception {
		
		return pcsTlEnablelosscaptureDao.validatePhenomenaLink(pcsTlLossphenfactorylink);
	}

	
}
