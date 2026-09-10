package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.ArrayList;
import java.util.List;

//import org.apache.catalina.connector.Request;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.bean.BAL_CauseBean;
import com.akranta.tpm.bean.BAL_GenTlAssemblymstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.BAL_PhenCauseBean;
import com.akranta.tpm.bean.BAL_PhenomenaBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_BdmTlPhenomenamstDao;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_BdmTlPhencauseDao;
import com.akranta.tpm.dao.impl.BAL_BdmTlPhenomenamstDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.BAL_BdmTlPhenCauseDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BAL_BdmTlPhenomenamst;
import com.akranta.tpm.model.BAL_BdmTlCausemst;
import com.akranta.tpm.model.BAL_BdmTlPhncauselink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GenTlToolsmst;
import com.akranta.tpm.service.BAL_PhenCauseService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

import com.akranta.tpm.service.api.BALBreakDownDropdownServiceApi;

public class BAL_PhenCauseServiceImpl implements BAL_PhenCauseService {

	private BAL_BdmTlPhencauseDao bdmTlPhenCauseDao ; 
	private BAL_BdmTlPhenomenamstDao bdmTlPhenomenamstDao;
	private BAL_CommonFilterDao commonFilterDao;		
	private Validations validations ;
	
	private BALBreakDownDropdownServiceApi serviceApi;
	
	public BAL_PhenCauseServiceImpl(DBActionTemplate dbActionTemplate)
	{
		bdmTlPhenCauseDao = new BAL_BdmTlPhenCauseDaoImpl(dbActionTemplate);
		bdmTlPhenomenamstDao = new BAL_BdmTlPhenomenamstDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}	
	
	public void BAL_PhenCauseServiceImplJwt(String JwtToken){
    	try{
    		bdmTlPhenomenamstDao.BAL_BdmTlPhenomenamstDaoImplJwt(JwtToken);
    		//genTlAssemblymstServiceApi = new BAL_GenTlAssemblymstServiceApi(JwtToken);
    		
    		serviceApi = new BALBreakDownDropdownServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<ComboBox> getsAssemblyCombo(String condSql) throws Exception
	{
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("ASSM_CODE");
		
		comboFilter.setNameField("ASSM_NAME");
		comboFilter.setIdField("ASSM_KEYID");
		comboFilter.setTableName(TableNames.TBL_BAL_GEN_TL_ASSEMBLYMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getPhenomena(String condSql) throws Exception
	{
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("BPHM_PHENOMENATYPE");
		comboFilter.setNameField("BPHM_PHENOMENANAME");
		comboFilter.setIdField("BPHM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BAL_BDM_TL_PHENOMENAMST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getCause(String condSql) throws Exception
	{
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("BCSM_CODE");
		comboFilter.setNameField("BCSM_NAME");
		comboFilter.setIdField("BCSM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BAL_BDM_TL_CAUSEMST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}

//pcl
	public BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink newBdmTlPhncauselink,BAL_BdmTlPhncauselink oldBdmTlPhncauselink, BAL_PhenCauseBean phenCauseBean ) throws ValidationExceptions,Exception {
		String validationsFor;
		System.out.println("Inside Impl");
		validationsFor = "create";			
		//validations.validate(newBdmTlPhncauselink,"phenCauseCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		System.out.println("After Validate");
		System.out.println("serc-comb"+phenCauseBean.getCombinedId());
		fillValues(newBdmTlPhncauselink,oldBdmTlPhncauselink, phenCauseBean);
		System.out.println("after fil"+phenCauseBean.getCombinedId());
		// commented and added by priyanka 
		//return bdmTlPhenCauseDao.create(newBdmTlPhncauselink,phenCauseBean);
		
		BAL_BdmTlPhenomenamst phen = (BAL_BdmTlPhenomenamst) newBdmTlPhncauselink.getBdmTlPhenomenamst().get(0);
		
		 BAL_BdmTlPhenomenamst savedPhen = serviceApi.savePhenomenaApi(phen);

		    newBdmTlPhncauselink.setBpclOriginalid(savedPhen.getBphmKeyid());
		    newBdmTlPhncauselink.setBpclParentid(phenCauseBean.getCombinedId());
		    newBdmTlPhncauselink.setBpclElementid(savedPhen.getBphmKeyid());
		    newBdmTlPhncauselink.setBpclDisplaycode(savedPhen.getBphmPhenomenaname());
		    newBdmTlPhncauselink.setBpclElementtype("PHN");

		    return newBdmTlPhncauselink;
	}	
		
	public BAL_BdmTlPhncauselink update(BAL_BdmTlPhncauselink newBdmTlPhncauselink,BAL_BdmTlPhncauselink oldBdmTlPhncauselink, BAL_PhenCauseBean phenCauseBean )  throws ValidationExceptions, Exception {
		System.out.println("update " +oldBdmTlPhncauselink.getBpclOriginalid());
		//validations.validate(newBdmTlPhncauselink,"phenCauseCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		
		fillValues(newBdmTlPhncauselink,oldBdmTlPhncauselink, phenCauseBean);
		
		//return bdmTlPhenCauseDao.update(newBdmTlPhncauselink,phenCauseBean);
		 BAL_BdmTlPhenomenamst phen = (BAL_BdmTlPhenomenamst) newBdmTlPhncauselink.getBdmTlPhenomenamst().get(0);

		    BAL_BdmTlPhenomenamst savedPhen;
		    if (!UIUtils.isValidKeyId(phen.getBphmKeyid())) {
		        savedPhen = serviceApi.savePhenomenaApi(phen);
		    } else {
		        savedPhen = serviceApi.updatePhenomenaApi(phen);
		    }

		    newBdmTlPhncauselink.setBpclOriginalid(savedPhen.getBphmKeyid());
		    newBdmTlPhncauselink.setBpclParentid(phenCauseBean.getCombinedId());
		    newBdmTlPhncauselink.setBpclElementid(savedPhen.getBphmKeyid());
		    newBdmTlPhncauselink.setBpclDisplaycode(savedPhen.getBphmPhenomenaname());
		    newBdmTlPhncauselink.setBpclElementtype("PHN");

		    return newBdmTlPhncauselink;
	}
	
	public BAL_BdmTlPhncauselink select(String UtilField) throws Exception
	{
		return this.bdmTlPhenCauseDao.select(UtilField);
	}		
		
	public BAL_BdmTlPhncauselink delete(BAL_BdmTlPhncauselink bdmTlPhncauselink ) throws Exception {
		//return bdmTlPhenCauseDao.delete(bdmTlPhncauselink);	
		
		String phenId = bdmTlPhncauselink.getBpclOriginalid();
	    serviceApi.deletePhenomenaApi(phenId);
	    return bdmTlPhncauselink;
	}
	
	public BAL_BdmTlPhncauselink deleteCause(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception {
	    //return bdmTlPhenCauseDao.deleteCause(bdmTlPhncauselink);
	    
	    String causeId = bdmTlPhncauselink.getBpclOriginalid();
	    serviceApi.deleteCauseApi(causeId);
	    
	    return bdmTlPhncauselink;
	}
	
	
	public BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink bdmTlPhncauselink,
			List<String> phenCauseValues) throws Exception {
		 
		return bdmTlPhenCauseDao.create(bdmTlPhncauselink,phenCauseValues);
	}
	public BAL_BdmTlPhenomenamst create(BAL_BdmTlPhenomenamst newBdmTlPhenomenamst,
			BAL_BdmTlPhenomenamst oldBdmTlPhenomenamst, BAL_PhenomenaBean phenBean)
			throws ValidationExceptions, Exception {
		try {
			
			String validationsFor;
			validationsFor = "create";
			validations.validate(newBdmTlPhenomenamst,"phencreation",validationsFor);
			fillPhenomenaMstValues(newBdmTlPhenomenamst,oldBdmTlPhenomenamst,phenBean);
			return this.bdmTlPhenomenamstDao.createPhenomena(newBdmTlPhenomenamst);			
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}
	public BAL_BdmTlPhenomenamst update(BAL_BdmTlPhenomenamst newBdmTlPhenomenamst,
			BAL_BdmTlPhenomenamst oldBdmTlPhenomenamst, BAL_PhenomenaBean phenBean)
			throws ValidationExceptions, Exception {
		try {
			
			System.out.println("updatee");
			validations.validate(newBdmTlPhenomenamst,"phencreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			fillPhenomenaMstValues(newBdmTlPhenomenamst,oldBdmTlPhenomenamst,phenBean);
				
			return this.bdmTlPhenomenamstDao.updatePhenomena(newBdmTlPhenomenamst);	
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
	@Override
	public List<String[]> getPhenomenaGridData(CommonFilter commonFilter, List<String> assemblyIdList) throws Exception {
	    return bdmTlPhenomenamstDao.getPhenomenaGridData(commonFilter, assemblyIdList);
	}
	
	@Override
	public List<String[]> getCauseGridData(CommonFilter commonFilter, String phenId) throws Exception {
	    return bdmTlPhenomenamstDao.getCauseList(commonFilter, phenId);
	}
	
	
	public BAL_BdmTlCausemst create(BAL_BdmTlCausemst newBdmTlCausemst,BAL_BdmTlCausemst oldBdmTlCausemst, BAL_CauseBean causeBean ) throws ValidationExceptions, Exception
	{
		try {
			
			String validationsFor;
			validationsFor = "create";
			validations.validate(newBdmTlCausemst,"phencreation",validationsFor);
			fillCauseMstValues(newBdmTlCausemst,oldBdmTlCausemst,causeBean);
			//return this.bdmTlPhenomenamstDao.createCause(newBdmTlCausemst);
			return serviceApi.saveCauseApi(newBdmTlCausemst);
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}
	public BAL_BdmTlCausemst update(BAL_BdmTlCausemst newBdmTlCausemst,BAL_BdmTlCausemst oldBdmTlCausemst, BAL_CauseBean causeBean ) throws Exception
	{
		try {
			
			System.out.println("updatee");
			validations.validate(newBdmTlCausemst,"phencreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			fillCauseMstValues(newBdmTlCausemst,oldBdmTlCausemst,causeBean);
				
			//return this.bdmTlPhenomenamstDao.updateCause(newBdmTlCausemst);	
			return serviceApi.updateCauseApi(newBdmTlCausemst);
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
	public List<BAL_BdmTlPhncauselink> getAllLocation(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception
	{
		return this.bdmTlPhenCauseDao.getAllLocation(bdmTlPhncauselink);
	}
	public  List<String[]>  getSearchNode(String searchNode) throws Exception{
		return this.bdmTlPhenCauseDao.getSearchNode(searchNode);
	}
	public String getCmpFromPhnCauseLink(String searchNode) throws Exception{
		return this.bdmTlPhenCauseDao.getCmpFromPhnCauseLink(searchNode);
	}
	public List<String []> getParentElem(String elemId) throws Exception {

		return this.bdmTlPhenCauseDao.getParentElem(elemId);
	}
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end,GridParams gridParams) throws Exception{
		return this.bdmTlPhenCauseDao.getChildElem(childElem,formfield,start,end,gridParams);
	}
	public String getTotalCount(List<String> childElem,String formfield) throws Exception
	{
		return this.bdmTlPhenCauseDao.getTotalCount(childElem,formfield);
	}
	public BAL_BdmTlPhenomenamst selectPhenomena(String keyid) throws Exception {
		// TODO Auto-generated method stub
		//return this.bdmTlPhenCauseDao.selectPhenomena(keyid);
		return serviceApi.selectPhenomenaApi(keyid);
	}
	public BAL_BdmTlCausemst selectCause(String keyid) throws Exception{
		//return this.bdmTlPhenCauseDao.selectCause(keyid);
		return serviceApi.selectCauseApi(keyid);
	}
	
	
private BAL_BdmTlPhncauselink fillValues(BAL_BdmTlPhncauselink newBdmTlPhncauselink,BAL_BdmTlPhncauselink oldBdmTlPhncauselink,BAL_PhenCauseBean phenCauseBean) {
		
		BAL_BdmTlPhenomenamst bdmTlPhenomenamst = (BAL_BdmTlPhenomenamst)newBdmTlPhncauselink.getBdmTlPhenomenamst().get(0);
		//commented by priyanka 
		//if(bdmTlPhenomenamst.getBphmKeyid() == null) 
			newBdmTlPhncauselink.setBdmTlPhenomenamst((fillPhenomenaValues(newBdmTlPhncauselink,oldBdmTlPhncauselink,phenCauseBean)));

//		BAL_BdmTlCausemst bdmTlCausemst = (BAL_BdmTlCausemst)newBdmTlPhncauselink.getBdmTlCausemst().get(0);
//		//commented by priyanka 
//		//if(bdmTlCausemst.getBcsmKeyid() == null)		
//			newBdmTlPhncauselink.setBdmTlCausemst((fillCauseValues(newBdmTlPhncauselink,oldBdmTlPhncauselink,phenCauseBean)));
//	
//		if(newBdmTlPhncauselink.getBpclDisplaycode() == null )			
//			newBdmTlPhncauselink.setBpclDisplaycode("{}");
//		System.out.println(newBdmTlPhncauselink.getBpclDisplaycode());
//		
//		newBdmTlPhncauselink.setBpclActive("Y");		
		//String dateTime = CommonFunctions.dateTimeNow();
		
		System.out.println("fill values:");

		// bpcl_originalid  = phenomena keyid
		// bpcl_parentid	= assembly id
		// bpcl_elementid	= parentid - phenomeaid
		
			
		return newBdmTlPhncauselink; 
		
	}

private List<BAL_BdmTlPhenomenamst> fillPhenomenaValues(BAL_BdmTlPhncauselink newBdmTlPhncauselink,BAL_BdmTlPhncauselink oldBdmTlPhncauselink,BAL_PhenCauseBean phenCauseBean) 
{
	System.out.println("Phenomena");	
	//String dateTime = CommonFunctions.dateTimeNow();
	String dateTime = CommonFunctions.pg_dateTimeNow(); // change here
	
	List<BAL_BdmTlPhenomenamst> newBdmTlPhenomenamst = newBdmTlPhncauselink.getBdmTlPhenomenamst();
	if(newBdmTlPhenomenamst.isEmpty()) 
	{
		System.out.println("Print this one");
	}
	List<BAL_BdmTlPhenomenamst> oldBdmTlPhenomenamst = null;
	BAL_BdmTlPhenomenamst oldBdmTlPhenomenams = null;	
	
	System.out.println("INS");
	//BdmTlPhenomenamst oldBdmTlPhenomenamst = null;
	if( oldBdmTlPhncauselink != null){
		oldBdmTlPhenomenamst = oldBdmTlPhncauselink.getBdmTlPhenomenamst();
		if( oldBdmTlPhenomenamst != null && oldBdmTlPhenomenamst.size() > 0 )
		{
			oldBdmTlPhenomenams = oldBdmTlPhenomenamst.get(0);
		}
	}		
	
	List<BAL_BdmTlPhenomenamst> newBdmTlPhenomenaList = new ArrayList<BAL_BdmTlPhenomenamst>();
	
	for( BAL_BdmTlPhenomenamst bdmTlPhenomenamst :newBdmTlPhenomenamst)
	{	
		// commented and added here by priyanka
		/*
		 * if(bdmTlPhenomenamst.getBphmKeyid() == null )
		 * bdmTlPhenomenamst.setBphmCreatedon(dateTime); else
		 * //bdmTlPhenomenamst.setBphmCreatedon(dateTime);
		 */	
		
		if (bdmTlPhenomenamst.getBphmKeyid() == null 
	            || bdmTlPhenomenamst.getBphmKeyid().trim().equals("")) {

	        bdmTlPhenomenamst.setBphmCreatedon(dateTime);

	    } else {

	        if (oldBdmTlPhenomenams != null 
	                && oldBdmTlPhenomenams.getBphmCreatedon() != null) {
	            bdmTlPhenomenamst.setBphmCreatedon(oldBdmTlPhenomenams.getBphmCreatedon());
	        } else {
	            bdmTlPhenomenamst.setBphmCreatedon(dateTime);
	        }
	    }
		// end 
		bdmTlPhenomenamst.setBphmModifiedon(dateTime);
		bdmTlPhenomenamst.setBphmActive("Y");
		
		if(bdmTlPhenomenamst.getBphmPhenomenatype() == null)
			bdmTlPhenomenamst.setBphmPhenomenatype("BD");
		if(bdmTlPhenomenamst.getBphmPhenomenaname() == null)
			bdmTlPhenomenamst.setBphmPhenomenaname("{}");
		if(bdmTlPhenomenamst.getBphmShortname() == null)
			bdmTlPhenomenamst.setBphmShortname("{}");
		if(bdmTlPhenomenamst.getBphmRemarks()== null)
			bdmTlPhenomenamst.setBphmRemarks("{}");
		if(bdmTlPhenomenamst.getBphmAssemblyid()== null)
			bdmTlPhenomenamst.setBphmAssemblyid("{}");
		if(bdmTlPhenomenamst.getBphmLevelno()== null)
			bdmTlPhenomenamst.setBphmLevelno("1");
		if(bdmTlPhenomenamst.getBphmIsphnnotdefined()== null)
			bdmTlPhenomenamst.setBphmIsphnnotdefined("N");
		if(bdmTlPhenomenamst.getBphmCausenotneeded()== null)
			bdmTlPhenomenamst.setBphmCausenotneeded("Y");
		if(bdmTlPhenomenamst.getBphmChildflag()== null)
			bdmTlPhenomenamst.setBphmChildflag("N");		
		if(!UIUtils.isValidKeyId(bdmTlPhenomenamst.getBphmRelatedto()))
			bdmTlPhenomenamst.setBphmRelatedto("MCH");
		bdmTlPhenomenamst.setTempfield1("N");
		bdmTlPhenomenamst.setTempfield2("N");
		bdmTlPhenomenamst.setTempfield3("N");
		
		newBdmTlPhenomenaList.add(bdmTlPhenomenamst);
	}	
	return newBdmTlPhenomenaList;
}

private List<BAL_BdmTlCausemst> fillCauseValues(BAL_BdmTlPhncauselink newBdmTlPhncauselink,BAL_BdmTlPhncauselink oldBdmTlPhncauselink,BAL_PhenCauseBean phenCauseBean) 
{
	System.out.println("Cause");	
	String dateTime = CommonFunctions.dateTimeNow();
	
	List<BAL_BdmTlCausemst> newBdmTlCausemst = newBdmTlPhncauselink.getBdmTlCausemst();	
	List<BAL_BdmTlCausemst> oldBdmTlCausemst = null;
	BAL_BdmTlCausemst oldBdmTlCauses = null;
	
	//BdmTlPhenomenamst oldBdmTlPhenomenamst = null;
	if( oldBdmTlPhncauselink != null){
		oldBdmTlCausemst = oldBdmTlPhncauselink.getBdmTlCausemst();
		if( oldBdmTlCausemst != null && oldBdmTlCausemst.size() > 0 )
		{
			oldBdmTlCauses = oldBdmTlCausemst.get(0);
		}
	}		
	List<BAL_BdmTlCausemst> newBdmTlCauseList = new ArrayList<BAL_BdmTlCausemst>();
	
	for( BAL_BdmTlCausemst bdmTlCausemst :newBdmTlCausemst)
	{	
		if(bdmTlCausemst.getBcsmKeyid() == null )			
			bdmTlCausemst.setBcsmCreatedon(dateTime);
		else
			bdmTlCausemst.setBcsmCreatedon(dateTime);
		CommonFunctions.debugMsg("CauseDefine in impl "+bdmTlCausemst.getBcsmIscausedefined());
		if (UIUtils.isValidKeyId(bdmTlCausemst.getBcsmIscausedefined())) 
			bdmTlCausemst.setBcsmIscausedefined(bdmTlCausemst.getBcsmIscausedefined());
		else
			bdmTlCausemst.setBcsmIscausedefined("N");
		
		System.out.println(bdmTlCausemst.getBcsmIscausedefined());
		
		if (bdmTlCausemst.getBcsmRemarks() ==null)
			bdmTlCausemst.setBcsmRemarks("{}");
		
		bdmTlCausemst.setBcsmModifiedon(dateTime);
		bdmTlCausemst.setBcsmActive("Y");
		
		newBdmTlCauseList.add(bdmTlCausemst);
	}	
		return newBdmTlCauseList;
}
private BAL_BdmTlCausemst fillCauseMstValues(BAL_BdmTlCausemst newBdmTlCausemst,BAL_BdmTlCausemst oldBdmTlCausemst, BAL_CauseBean causeBean) {
	newBdmTlCausemst.setBcsmActive("Y");
	//String dateTime = CommonFunctions.dateTimeNow();
	String dateTime = CommonFunctions.pg_dateTimeNow();
	newBdmTlCausemst.setBcsmCreatedon(dateTime);		
	newBdmTlCausemst.setBcsmModifiedon(dateTime);
	
	if(!UIUtils.isValidKeyId(newBdmTlCausemst.getBcsmIscausedefined()))
		newBdmTlCausemst.setBcsmIscausedefined("N");
	if(!UIUtils.isValidKeyId(newBdmTlCausemst.getBcsmName()))
		newBdmTlCausemst.setBcsmName("{}");
	if(!UIUtils.isValidKeyId(newBdmTlCausemst.getBcsmPhenomenaid()))
		newBdmTlCausemst.setBcsmPhenomenaid("{}");
	if(!UIUtils.isValidKeyId(newBdmTlCausemst.getBcsmRemarks()))
		newBdmTlCausemst.setBcsmRemarks("{}");
	
	
	return newBdmTlCausemst;
}
private BAL_BdmTlPhenomenamst fillPhenomenaMstValues(BAL_BdmTlPhenomenamst newBdmTlPhenomenamst,BAL_BdmTlPhenomenamst oldBdmTlPhenomenamst, BAL_PhenomenaBean phenomenaBean) {
	newBdmTlPhenomenamst.setBphmActive("Y");
	String dateTime = CommonFunctions.dateTimeNow();
	newBdmTlPhenomenamst.setBphmCreatedon(dateTime);		
	newBdmTlPhenomenamst.setBphmModifiedon(dateTime);
	
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmPhenomenatype()))
		newBdmTlPhenomenamst.setBphmPhenomenatype("BD");
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmPhenomenaname()))
		newBdmTlPhenomenamst.setBphmPhenomenaname("{}");
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmShortname()))
		newBdmTlPhenomenamst.setBphmShortname("{}");
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmRemarks()))
		newBdmTlPhenomenamst.setBphmRemarks("{}");
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmAssemblyid()))
		newBdmTlPhenomenamst.setBphmAssemblyid("{}");
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmLevelno()))
		newBdmTlPhenomenamst.setBphmLevelno("1");
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmIsphnnotdefined()))
		newBdmTlPhenomenamst.setBphmIsphnnotdefined("N");
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmCausenotneeded()))
		newBdmTlPhenomenamst.setBphmCausenotneeded("Y");
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmChildflag()))
		newBdmTlPhenomenamst.setBphmChildflag("N");
	if(!UIUtils.isValidKeyId(newBdmTlPhenomenamst.getBphmRelatedto()))
		newBdmTlPhenomenamst.setBphmRelatedto("MCH");
	newBdmTlPhenomenamst.setTempfield1("N");
	newBdmTlPhenomenamst.setTempfield2("N");
	newBdmTlPhenomenamst.setTempfield3("N");
	return newBdmTlPhenomenamst;
}


}
