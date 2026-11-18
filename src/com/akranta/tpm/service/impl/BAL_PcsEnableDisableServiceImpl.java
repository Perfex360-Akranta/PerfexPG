/*Created By : Siddharth.A*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.BAL_PcsTlEnablelosscaptureDaoImpl;
import com.akranta.tpm.dao.impl.BAL_PcsTlLosscelllinkDaoImpl;
import com.akranta.tpm.dao.impl.BAL_PcsTlLossphenomenamstDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.PcsTlLosscelllinkDaoImpl;
import com.akranta.tpm.dao.impl.PcsTlLossphenomenamstDaoImpl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.BAL_PcsTlEnablelosscaptureDao;
import com.akranta.tpm.dao.BAL_PcsTlLosscelllinkDao;
import com.akranta.tpm.dao.BAL_PcsTlLossphenomenamstDao;
import com.akranta.tpm.dao.impl.PcsTlEnablelosscaptureDaoImpl;
import com.akranta.tpm.model.BAL_PcsTlEnablelosscapture;
import com.akranta.tpm.model.BAL_PcsTlLosscelllink;
import com.akranta.tpm.model.BAL_PcsTlLossphenfactorylink;
import com.akranta.tpm.model.BAL_PcsTlLossphenomenamst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.PcsTlEnablelosscapture;
import com.akranta.tpm.model.PcsTlLosscelllink;
import com.akranta.tpm.model.PcsTlLossphenfactorylink;
import com.akranta.tpm.model.PcsTlLossphenomenamst;
import com.akranta.tpm.service.BAL_PcsEnableDisableService;
import com.akranta.tpm.service.api.BAL_PcsTlEnablelosscaptureServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_PcsEnableDisableServiceImpl implements BAL_PcsEnableDisableService {
	
	private BAL_PcsTlEnablelosscaptureDao pcsTlEnablelosscaptureDao ;
	private BAL_PcsTlLossphenomenamstDao pcsTlLossphenomenamstDao;
	private BAL_PcsTlLosscelllinkDao pcsTlLosscelllinkDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	private BAL_PcsTlEnablelosscaptureServiceApi EnablelosscaptureServiceApi;	
	
	
	public BAL_PcsEnableDisableServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsTlLosscelllinkDao = new BAL_PcsTlLosscelllinkDaoImpl(dbActionTemplate);
		pcsTlEnablelosscaptureDao = new BAL_PcsTlEnablelosscaptureDaoImpl(dbActionTemplate);
		pcsTlLossphenomenamstDao=new BAL_PcsTlLossphenomenamstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
 	}
	
	//jwt
	@Override 
	 public void BAL_PcsEnableDisableServiceImplJwt(String jwtToken) { 
	        try { 
	        	EnablelosscaptureServiceApi = new BAL_PcsTlEnablelosscaptureServiceApi(jwtToken); 
	        	pcsTlEnablelosscaptureDao.BAL_PcsTlEnablelosscaptureDaoImplJwt(jwtToken);
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	}
	
	public void setPlmTlSparedtlDao(BAL_PcsTlEnablelosscaptureDao pcsTlEnablelosscaptureDao)
	{
		this.pcsTlEnablelosscaptureDao = pcsTlEnablelosscaptureDao;
	}


	@Override
	public List<String[]> getAllPcsEnblDsblMCH(String factId,String sectId,String cellid, String flid) throws Exception {
			// TODO Auto-generated method stub
		System.out.println("Inside service Impl");
		//pcsTlEnablelosscaptureDao.BAL_PcsTlEnablelosscaptureDaoImplJwt(jwtToken);
		return pcsTlEnablelosscaptureDao.getAllPcsEnblDsblMCH(factId,sectId,cellid, flid);
	}
	
	//elumalai
	@Override
	public List<String[]> getPcsEnblDsblMCHWithHeaders(String sectId) throws Exception {
	    System.out.println("Inside service Impl getPcsEnblDsblMCHWithHeaders");
	    return pcsTlEnablelosscaptureDao.getPcsEnblDsblMCHWithHeaders(sectId);
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
		System.out.println("Inside Service Impl for Cnt");
		return pcsTlEnablelosscaptureDao.getAllSectCount(sectId);
	}

	//public List<PcsTlEnablelosscapture> save(List<PcsTlEnablelosscapture> newpcsTlEnablelosscapture,List<PcsTlEnablelosscapture> oldpcsTlEnablelosscapture,  PcsEnableDisableFormBean pcsEnableDisableFormBean)  throws Exception 
	public List<BAL_PcsTlEnablelosscapture> save(List<BAL_PcsTlEnablelosscapture> newpcsTlEnablelosscapture,String createdby)  throws Exception 

	{
		//if (pcsEnableDisableFormBean.getForCell().equals("Y")){
			//newpcsTlEnablelosscapture=fillForCellValue(newpcsTlEnablelosscapture,oldpcsTlEnablelosscapture,pcsEnableDisableFormBean);
		//} else {			
		//	fillValues(newpcsTlEnablelosscapture,oldpcsTlEnablelosscapture,pcsEnableDisableFormBean);
		fillValues(newpcsTlEnablelosscapture, createdby);

		
		//	}
		//return  pcsTlEnablelosscaptureDao.save(newpcsTlEnablelosscapture, pcsEnableDisableFormBean);
			//return  pcsTlEnablelosscaptureDao.save(newpcsTlEnablelosscapture);
		return  EnablelosscaptureServiceApi.createorupdate(newpcsTlEnablelosscapture);

	}

	//private List<PcsTlEnablelosscapture> fillValues(List<PcsTlEnablelosscapture> newpcsTlEnablelosscapture,List<PcsTlEnablelosscapture> oldpcsTlEnablelosscapture,
		//	PcsEnableDisableFormBean pcsEnableDisableFormBean)
	private List<BAL_PcsTlEnablelosscapture> fillValues(List<BAL_PcsTlEnablelosscapture> newpcsTlEnablelosscapture,String createdby)
	{
		// TODO Auto-generated method stub
	
		System.out.println("Inside Service impl");
		
		//String dateTime = CommonFunctions.dateTimeNow();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		
		List<BAL_PcsTlEnablelosscapture> pcsTlEnablelosscaptureList =new ArrayList<BAL_PcsTlEnablelosscapture>();
	//	if( oldpcsTlEnablelosscapture != null && oldpcsTlEnablelosscapture.size() > 0 )
	//		oldPlmTlSparedtl =  (PlmTlSparedtl)oldPlmTlSparedtls.get(0);
		//	pcsTlEnablelosscaptureList=(List<PcsTlEnablelosscapture>)oldpcsTlEnablelosscapture.get(0);
		
	//	System.out.println("oldPlmTlSparedtl ="+oldPlmTlSparedtl.getPspdCreatedon());
		//System.out.println("oldpcsTlEnablelosscapture="+oldpcsTlEnablelosscapture.size());
		System.out.println("sd");
		System.out.print("newpcsTlEnablelosscapture"+newpcsTlEnablelosscapture.size());
		for( BAL_PcsTlEnablelosscapture pcsTlEnablelosscapture : newpcsTlEnablelosscapture)
		{	
			System.out.println("Inside for loop");
			System.out.println("srvce Created on="+pcsTlEnablelosscapture.getPelcCreatedon());
		//	if(pcsTlEnablelosscapture.getPelcKeyid()==null)			
		//	{	
				pcsTlEnablelosscapture.setPelcCreatedon(dateTime);
				System.out.println("created on if keyid null"+pcsTlEnablelosscapture.getPelcCreatedon());
		//	}	
		//	else
		//	{
		//		pcsTlEnablelosscapture.setPelcCreatedon(pcsTlEnablelosscapture.getPelcCreatedon());
			//}
			pcsTlEnablelosscapture.setPelcModifiedon(dateTime);
		//	pcsTlEnablelosscapture.setPelcCreatedby(sprPckupFormBean.getCreatedBy());
			pcsTlEnablelosscapture.setPelcCreatedby(createdby);
		
			pcsTlEnablelosscapture.setPelcActive("Y");
			if(pcsTlEnablelosscapture.getPelcEffectivefrom()==null)
				pcsTlEnablelosscapture.setPelcEffectivefrom(dateTime);
			
			if(pcsTlEnablelosscapture.getPelcEffectivetill()==null)
				pcsTlEnablelosscapture.setPelcEffectivetill(dateTime);
			
			if(pcsTlEnablelosscapture.getPelcMachineid()==null)
				pcsTlEnablelosscapture.setPelcMachineid("{}");
			
			if(pcsTlEnablelosscapture.getPelcCellid()==null)
				pcsTlEnablelosscapture.setPelcCellid("{}");
			

			//pcsTlEnablelosscapture.setPelcFlid(pcsEnableDisableFormBean.getFlid());
			if(pcsTlEnablelosscapture.getPelcFlid()==null)
				pcsTlEnablelosscapture.setPelcFlid("{}");
			

			if(pcsTlEnablelosscapture.getPelcElementid()==null)
				pcsTlEnablelosscapture.setPelcElementid("{}");

			if(pcsTlEnablelosscapture.getPelcType()==null)
				pcsTlEnablelosscapture.setPelcType("S");
			
			if(pcsTlEnablelosscapture.getPelcIsgroupbased()==null)
				pcsTlEnablelosscapture.setPelcIsgroupbased("N");
			if(pcsTlEnablelosscapture.getPelcOption()==null)
			  pcsTlEnablelosscapture.setPelcOption("-");
			if(pcsTlEnablelosscapture.getPelcLineid()==null)
				pcsTlEnablelosscapture.setPelcLineid("-");
			pcsTlEnablelosscaptureList.add(pcsTlEnablelosscapture);
		}
		return pcsTlEnablelosscaptureList;
		
	}

/*	public List<ComboBox> getfactid(ComboFilter comboFilter)throws Exception
	{
		comboFilter.setTableName("GEN_TL_SECTIONMST");
		comboFilter.setNameField("SECT_NAME");
		comboFilter.setIdField("SECT_KEYID");
		return commonFilterDao.fillComboValues(comboFilter) ;
	}	*/
	private List<BAL_PcsTlEnablelosscapture> fillForCellValue(List<BAL_PcsTlEnablelosscapture> newpcsTlEnablelosscapture,List<PcsTlEnablelosscapture> oldpcsTlEnablelosscapture,
			PcsEnableDisableFormBean pcsEnableDisableFormBean)
	{

		System.out.println("Inside fillForCellValue");
		
		String dateTime = CommonFunctions.dateTimeNow();		
		
		List<BAL_PcsTlEnablelosscapture> pcsTlEnablelosscaptureList =new ArrayList<BAL_PcsTlEnablelosscapture>();
		
		//if( oldpcsTlEnablelosscapture != null && oldpcsTlEnablelosscapture.size() > 0 )
		
		//System.out.print("newpcsTlEnablelosscapture"+newpcsTlEnablelosscapture.size());
		
		for( BAL_PcsTlEnablelosscapture pcsTlEnablelosscapture : newpcsTlEnablelosscapture)
		{	
			System.out.println("Inside for loop");
			//System.out.println("srvce Created on="+pcsTlEnablelosscapture.getPelcCreatedon());
			
			pcsTlEnablelosscapture.setPelcCreatedon(dateTime);
			//System.out.println("created on if keyid null"+pcsTlEnablelosscapture.getPelcCreatedon());
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
			
			System.out.println("before List loop");
			pcsTlEnablelosscaptureList.add(pcsTlEnablelosscapture);
			return pcsTlEnablelosscaptureList;
		}
		
		System.out.println("Exit for loop");
		return pcsTlEnablelosscaptureList;
		
	}

	@Override
	public List<ComboBox> getPhenomenaComboList(ComboFilter combofilter)
			throws Exception {
		// TODO Auto-generated method stub
		
		ComboFilter phenomena = new ComboFilter();
		phenomena.setIdField("PLPM_KEYID");
		phenomena.setNameField("PLPM_NAME");
		phenomena.setTableName("PCS_TL_LOSSPHENOMENAMST");
		phenomena.setCondSql(" AND PLPM_NAME <> '-'");
		return commonFilterDao.fillComboValues(phenomena);
	}
	@Override
	public List<ComboBox> getMainLossComboList(ComboFilter comboFilter)
			throws Exception {
		// TODO Auto-generated method stub
		ComboFilter mainLoss = new ComboFilter();
		mainLoss.setIdField("KEYID");
		mainLoss.setNameField("CHILDLOSS");
		mainLoss.setTableName("pcs_vw_LOSSNAMES,pcs_tl_lossphenomenamst");
		mainLoss.setCondSql("and KEYID =PLPM_MAINLOSS");
		return commonFilterDao.fillComboValues(mainLoss);
	}

	@Override
	public List<String[]> getFactory(GridParams gridParams,String lossId,String phenID) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlEnablelosscaptureDao.getFactory(gridParams,lossId,phenID);
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
	public BAL_PcsTlLossphenomenamst createPhenomenaLossFactoryLink(String usrm_ccno,
			BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception 
	{
		String validationsFor = "create";
		validations.validate( pcsTlLossphenomenamst,"PhenLossReason",validationsFor);
		/*List<PcsTlLossphenfactorylink> newPcsTlLossphenomenamstLinks = pcsTlLossphenomenamst.getpcsTlLossphenfactorylink();
		CommonFunctions.debugMsg("newPcsTlLossphenomenamstLinks.size():"+newPcsTlLossphenomenamstLinks.size());
		for(int i=0;i<newPcsTlLossphenomenamstLinks.size();i++)
		{
			validations.validate( newPcsTlLossphenomenamstLinks.get(i),"PhenLossReasonFactLink",validationsFor);
		}*/
		fillValues(pcsTlLossphenomenamst,usrm_ccno);
		return  pcsTlLossphenomenamstDao.create(pcsTlLossphenomenamst);
		
	}
	
	public BAL_PcsTlLosscelllink createLossJHLink(String usrm_ccno,
			List<BAL_PcsTlLosscelllink> pcsTlLosscelllinkList)throws Exception {
	
		//String validationsFor = "create";
		//validations.validate( pcsTlLosscelllinkList,"PhenLossReason",validationsFor);
		CommonFunctions.debugMsg("pcsTlLosscelllinkList.size"+pcsTlLosscelllinkList.size());
		
		List<BAL_PcsTlLosscelllink> pcsTlLosscelllinkListNew = fillValuesForJHLink(usrm_ccno, pcsTlLosscelllinkList);
		
		CommonFunctions.debugMsg("pcsTlLosscelllinkListNew.size"+pcsTlLosscelllinkListNew.size());
		return  pcsTlLosscelllinkDao.createJHLink(pcsTlLosscelllinkListNew);
		
	}

	
	public List<BAL_PcsTlLosscelllink> fillValuesForJHLink(String usrm_ccno, List<BAL_PcsTlLosscelllink> pcsTlLosscelllinkList) {
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		CommonFunctions.debugMsg("fillValuesForJHLink ");
		
		List<BAL_PcsTlLosscelllink> newPcsTlLosscelllink = new ArrayList<BAL_PcsTlLosscelllink>();
		for( BAL_PcsTlLosscelllink pcsTlLosscelllink1 : pcsTlLosscelllinkList)
		{
			
			CommonFunctions.debugMsg("JH Tempfield2 "+pcsTlLosscelllink1.getPlflTempfield2());
			
			if (UIUtils.isValidKeyId(pcsTlLosscelllink1.getPlflTempfield2())  ) { 
				
				if(pcsTlLosscelllink1.getPlflTempfield2().equals("1") || 
						( pcsTlLosscelllink1.getPlflKeyid()!=null && UIUtils.isValidKeyId(pcsTlLosscelllink1.getPlflKeyid()))) {
					
					
					CommonFunctions.debugMsg("JH ====="+pcsTlLosscelllink1.getPlflTempfield2());
					BAL_PcsTlLosscelllink pcsTlLosscelllink = new BAL_PcsTlLosscelllink();
				
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

	public List<BAL_PcsTlLossphenfactorylink> fillValues(BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst,String usrm_ccno)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		pcsTlLossphenomenamst.setPlpmActive("Y");
		pcsTlLossphenomenamst.setPlpmCreatedby(usrm_ccno);
		pcsTlLossphenomenamst.setPlpmCreatedon(dateTime);
		pcsTlLossphenomenamst.setPlpmModifiedon(dateTime);
		pcsTlLossphenomenamst.setPlpmTempfield1("-");
		pcsTlLossphenomenamst.setPlpmTempfield2("-");
		pcsTlLossphenomenamst.setPlpmTempfield3("-");
		List<BAL_PcsTlLossphenfactorylink> newPcsTlLossphenomenamstLinks = pcsTlLossphenomenamst.getpcsTlLossphenfactorylink();
		List<BAL_PcsTlLossphenfactorylink> newPcsTlLossphenfactorylinkList = new ArrayList<BAL_PcsTlLossphenfactorylink>();
		int index=0;
		for( BAL_PcsTlLossphenfactorylink pcsTlLossphenfactorylink : newPcsTlLossphenomenamstLinks)
		{	
			pcsTlLossphenfactorylink.setPpflActive("Y");
			pcsTlLossphenfactorylink.setPpflCreatedby(usrm_ccno);
			pcsTlLossphenfactorylink.setPpflCreatedon(dateTime);
			pcsTlLossphenfactorylink.setPpflModifiedon(dateTime);
			pcsTlLossphenfactorylink.setPpflTempfield1("-");
			pcsTlLossphenfactorylink.setPpflTempfield2("-");
			BAL_PcsTlLossphenfactorylink pcsTlLossphenfactorylink1 = newPcsTlLossphenomenamstLinks.get(index);
			pcsTlLossphenfactorylink.setPpflFactoryid(pcsTlLossphenfactorylink1.getPpflFactoryid());
			index++;
			newPcsTlLossphenfactorylinkList.add(pcsTlLossphenfactorylink);
		}
		return newPcsTlLossphenfactorylinkList;
	}

	@Override
	public List<String[]> getComboTextContent(String phenId,String type) throws Exception {
		// TODO Auto-generated method stub
		return pcsTlLossphenomenamstDao.getComboTextContent(phenId,type);
	}

	@Override
	public BAL_PcsTlLossphenomenamst updatePhenomenaLossFactoryLink(String usrm_ccno,
			BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception {
		fillValues(pcsTlLossphenomenamst,usrm_ccno);
		return  pcsTlLossphenomenamstDao.update(pcsTlLossphenomenamst);
		
	}

	/*
	 * @Override public List<ComboBox> getMainLossComboList(CommonFilter
	 * commonFilter) throws Exception { // TODO Auto-generated method stub return
	 * null; }
	 */

	

	
}
