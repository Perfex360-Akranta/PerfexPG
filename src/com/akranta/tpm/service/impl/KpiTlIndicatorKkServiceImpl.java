package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.taglibs.standard.lang.jpath.adapter.Convert;
import org.json.simple.JSONObject;
import org.xml.sax.SAXException;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.KpiTlIndicatorKkBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.KpiTlIndicatorDao;
import com.akranta.tpm.dao.KpiTlIndicatorKkDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.KpiTlIndicatorDaoImpl;
import com.akranta.tpm.dao.impl.KpiTlIndicatorKkDaoImpl;
import com.akranta.tpm.dao.sql.KpiTlIndicatorDeptLinkSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMchcirclelink;
import com.akranta.tpm.model.KpiTlIndicator;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.model.KpiTlIndicatorKk;
import com.akranta.tpm.service.KpiTlIndicatorKkService;


import com.akranta.tpm.service.api.KpiTlActualandIndicatorServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class KpiTlIndicatorKkServiceImpl implements KpiTlIndicatorKkService{
	
	private KpiTlActualandIndicatorServiceApi kpiTlActualandIndicatorServiceApi;

	private KpiTlIndicatorKkDao kpiTlIndicatorKkDao; 
	private KpiTlIndicatorDao kpiTlIndicatorDao;
	private CommonFilterDao commonFilterDao ;
	private Validations validations ;
	public static final String DATE_TIME_FORMAT_NOW = "dd-MMM-yyyy HH:mm:ss";
	
	public KpiTlIndicatorKkServiceImpl(DBActionTemplate dbActionTemplate)
	{
		kpiTlIndicatorKkDao = new KpiTlIndicatorKkDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		kpiTlIndicatorDao = new KpiTlIndicatorDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	
	public void KpiTlIndicatorKkServiceImplJwt(String JwtToken){
    	try{
    		kpiTlIndicatorDao.KpiTlIndicatorDaoImplJwt(JwtToken);
    		kpiTlIndicatorKkDao.KpiTlIndicatorKkDaoImplJwt(JwtToken);
    		kpiTlActualandIndicatorServiceApi = new KpiTlActualandIndicatorServiceApi(JwtToken);

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	@Override	
	public List<KpiTlIndicatorKk> getKpiTlIndicatorKkValues(KpiTlIndicatorKk KpiTlIndicatorKk) throws Exception {
		//return this.kpiTlIndicatorKkDao.getKpiTlIndicatorKkValues(KpiTlIndicatorKk);
		return this.kpiTlActualandIndicatorServiceApi.getKpiTlIndicatorKkValues(KpiTlIndicatorKk);
	}
	
	public List<KpiTlIndicatorKk> getAllkeyInd(KpiTlIndicatorKk KpiTlIndicatorKk) throws Exception {
		//return this.kpiTlIndicatorKkDao.getAllkeyInd(KpiTlIndicatorKk);
		return this.kpiTlActualandIndicatorServiceApi.getAllkeyInds(KpiTlIndicatorKk);
	}
	
	public KpiTlIndicatorKk create(KpiTlIndicatorKk newKpiTlIndicatorKk,KpiTlIndicatorKk oldKpiTlIndicatorKk)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		//try{
			String validationsFor="create";					
			validations.validate(newKpiTlIndicatorKk,"KpiTlIndicatorKk",validationsFor);	
			newKpiTlIndicatorKk=fillValues( newKpiTlIndicatorKk,  oldKpiTlIndicatorKk);
			return this.kpiTlIndicatorKkDao.create(newKpiTlIndicatorKk);
		//}
		/* (ValidationExceptions e){
			CommonMessage.debugMsg("valiadate e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}*/
	}
	
	public KpiTlIndicatorKk update(KpiTlIndicatorKk newKpiTlIndicatorKk,KpiTlIndicatorKk oldKpiTlIndicatorKk)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		try{
			String validationsFor="create";		
			validations.validate(newKpiTlIndicatorKk,"KpiTlIndicatorKk",validationsFor);	
			newKpiTlIndicatorKk=fillValues( newKpiTlIndicatorKk,  oldKpiTlIndicatorKk);
			return this.kpiTlIndicatorKkDao.update(newKpiTlIndicatorKk);
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}	
	
	@Override
	public List<ComboBox> getParentComboList(CommonFilter commonFilter,ComboFilter keyInd )throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter keyInd = new ComboFilter();
		String condSql="";
		keyInd.setIdField("KINK_KEYID");		
		keyInd.setNameField("KINK_INDICATORNAME");
		keyInd.setTableName(TableNames.TBL_KPI_TL_INDICATOR);
		condSql+=" AND KINK_KEYID=KINK_PARENTID ";
		if(CommonFunctions.isValidKeyId(commonFilter.getPillarWise()))
			condSql+=" AND KINK_PILLARID='"+commonFilter.getPillarWise()+"' ";
		if(CommonFunctions.isValidKeyId(commonFilter.getLossId()))
			condSql+=" AND KINK_LOCATION='"+commonFilter.getLossId()+"' ";
		keyInd.setCondSql(condSql);
		return commonFilterDao.fillComboValues(keyInd);
	}
	
	public List<ComboBox> getUomComboList(ComboFilter keyInd)throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter keyInd = new ComboFilter();		
		keyInd.setIdField("UOMM_KEYID");		
		keyInd.setNameField("UOMM_DESCRIPTION");
		keyInd.setTableName(TableNames.TBL_ADM_TL_UOMMST);		
		return commonFilterDao.fillComboValues(keyInd);
	}
	
	public List<ComboBox> getCostAreaComboList(ComboFilter keyInd)throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter keyInd = new ComboFilter();		
		keyInd.setIdField("COAR_KEYID");		
		keyInd.setNameField("COAR_NAME");
		keyInd.setTableName(TableNames.TBL_KPI_TL_COSTAREAMST);		
		return commonFilterDao.fillComboValues(keyInd);
	}	
	
	public List<ComboBox> getDeptComboList(ComboFilter keyInd)throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter keyInd = new ComboFilter();		
		keyInd.setIdField("DEPT_KEYID");		
		keyInd.setNameField("DEPT_NAME");
		keyInd.setTableName(TableNames.TBL_GEN_TL_DEPARTMENTMST);		
		return commonFilterDao.fillComboValues(keyInd);
	}
	
	private KpiTlIndicatorKk fillValues(KpiTlIndicatorKk newKpiTlIndicatorKk, KpiTlIndicatorKk oldKpiTlIndicatorKk)
	{
		
		newKpiTlIndicatorKk.setKinkActive("Y");
		
		
		String dateTime = CommonFunctions.dateTimeNow();
		if(newKpiTlIndicatorKk.getKinkKeyid() == null)			
		{	
			newKpiTlIndicatorKk.setKinkCreatedon(dateTime);	
		}
		else
		{
			newKpiTlIndicatorKk.setKinkCreatedon(oldKpiTlIndicatorKk.getKinkCreatedon());
		}
		if(newKpiTlIndicatorKk.getKinkIndicatorname()== null)
			newKpiTlIndicatorKk.setKinkIndicatorname("{}");
		
		if(newKpiTlIndicatorKk.getKinkIndicatorcode()== null)
			newKpiTlIndicatorKk.setKinkIndicatorcode("{}");
		
		if(newKpiTlIndicatorKk.getKinkDescription()== null)
			newKpiTlIndicatorKk.setKinkDescription("{}");

		if(newKpiTlIndicatorKk.getKinkLevelno()== null)
			newKpiTlIndicatorKk.setKinkLevelno("0");
		if(newKpiTlIndicatorKk.getKinkParentid()== null)
			newKpiTlIndicatorKk.setKinkParentid("{}");	
		
		if("1".equals(newKpiTlIndicatorKk.getKinkParentid()))
			newKpiTlIndicatorKk.setKinkParentid(null);	
		
		if(newKpiTlIndicatorKk.getKinkSortno()== null)
			newKpiTlIndicatorKk.setKinkSortno("{}");
		
		if(newKpiTlIndicatorKk.getKinkIschild()== null)
			newKpiTlIndicatorKk.setKinkIschild("Y");			
		
		if(newKpiTlIndicatorKk.getKinkInputtype()== null)
			newKpiTlIndicatorKk.setKinkInputtype("-");	
		
		if(newKpiTlIndicatorKk.getKinkInputentry()== null)
			newKpiTlIndicatorKk.setKinkInputentry("-");	
		
		if(newKpiTlIndicatorKk.getKinkIdentifier()== null)
			newKpiTlIndicatorKk.setKinkIdentifier("{}");
		
		if(newKpiTlIndicatorKk.getKinkManualcalctype()== null)
			newKpiTlIndicatorKk.setKinkManualcalctype("X");
		
		if(newKpiTlIndicatorKk.getKinkUomid()== null)
			newKpiTlIndicatorKk.setKinkUomid("{}");
		if(newKpiTlIndicatorKk.getKinkFrequency()== null)
			newKpiTlIndicatorKk.setKinkFrequency("X");
		
		if(newKpiTlIndicatorKk.getKinkExcelname()== null)
			newKpiTlIndicatorKk.setKinkExcelname("{}");	
		
		if(newKpiTlIndicatorKk.getKinkDeptKeyid()== null)
			newKpiTlIndicatorKk.setKinkDeptKeyid("{}");
		
		if(newKpiTlIndicatorKk.getKinkCostarea()== null)
			newKpiTlIndicatorKk.setKinkCostarea("{}");
		
		if(newKpiTlIndicatorKk.getKinkTargetneed()== null)
			newKpiTlIndicatorKk.setKinkTargetneed("Y");
		
		if(newKpiTlIndicatorKk.getKinkPillarid()== null)
			newKpiTlIndicatorKk.setKinkPillarid("{}");
		
		newKpiTlIndicatorKk.setKinkModifiedon(dateTime);	
				
		newKpiTlIndicatorKk.setKinkTempfield3("-");
		newKpiTlIndicatorKk.setKinkTempfield4("-");
		newKpiTlIndicatorKk.setKinkTempfield5("-");
		newKpiTlIndicatorKk.setKinkTempfield6("-");
		newKpiTlIndicatorKk.setKinkTempfield7("-");
		newKpiTlIndicatorKk.setKinkTempfield8("-");
		newKpiTlIndicatorKk.setKinkTempfield9("-");
		newKpiTlIndicatorKk.setKinkTempfield10("-");	
		return newKpiTlIndicatorKk;
	}
	
	@Override
	public KpiTlIndicatorKk delete(KpiTlIndicatorKk KpiTlIndicatorKk) throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		//return this.kpiTlIndicatorKkDao.delete(KpiTlIndicatorKk);
		return this.kpiTlActualandIndicatorServiceApi.delete(KpiTlIndicatorKk);
	}
	@Override
	public KpiTlIndicatorDeptLink deleteDeptLink(KpiTlIndicatorDeptLink KpiTlIndicatorDeptLink) throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		return this.kpiTlIndicatorKkDao.deleteDeptLink(KpiTlIndicatorDeptLink);
	}
	
	@Override

	public String validatekeyIndLevel(KpiTlIndicatorKk KpiTlIndicatorKk)throws ValidationExceptions,BusinessApplicationExceptions, Exception{
		int menuLevel=0;
		int configLevel=0;
		String validate=null;
		try{
		menuLevel=this.kpiTlIndicatorKkDao.getkeyIndLevel(KpiTlIndicatorKk);
			String keyid = KpiTlIndicatorKk.getKinkKeyid();
			CommonMessage.debugMsg("key id _______________"+keyid);
			menuLevel=this.kpiTlActualandIndicatorServiceApi.getnewkeyIndLevellllll(keyid);
			
			menuLevel=menuLevel;
			CommonMessage.debugMsg("menuLevel:" + menuLevel);
			//configLevel=this.kpiTlIndicatorKkDao.getConfigkeyIndLevel();
			configLevel=this.kpiTlActualandIndicatorServiceApi.getConfigkeyIndLevel();
			CommonMessage.debugMsg("configLevel:" + configLevel);
			if (menuLevel<configLevel){
				validate="Valid";			
			}	
			else{
				validate=Convert.toString(configLevel);
			}
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		return validate;
	}
	@Override
	public String validateDelkeyIndLevel(KpiTlIndicatorKk kpiTlIndicatorKk)throws Exception{
		int menuLevel=0;		
		String validate="Not Valid";
		List<KpiTlIndicatorKk> newKpiTlIndicatorKk=new ArrayList<KpiTlIndicatorKk>();
		//newKpiTlIndicatorKk=this.kpiTlIndicatorKkDao.getAllkeyInd(kpiTlIndicatorKk);
		newKpiTlIndicatorKk = this.kpiTlActualandIndicatorServiceApi.getAllkeyInds(kpiTlIndicatorKk);
		menuLevel=newKpiTlIndicatorKk.size();	
		CommonMessage.debugMsg("menuLevel:" + menuLevel);
		if (menuLevel<=0){
			validate="Valid";			
		}		
		return validate;
	}
	
	@Override
	public KpiTlIndicator select(KpiTlIndicator KpiTlIndicatorKk) throws Exception {
		// TODO Auto-generated method stub
		//return this.kpiTlIndicatorKkDao.select(KpiTlIndicatorKk);
		return this.kpiTlActualandIndicatorServiceApi.select(KpiTlIndicatorKk);
	}
	
	@Override
	public List<KpiTlIndicatorKk> selectList(KpiTlIndicatorKk KpiTlIndicatorKk)throws Exception{
		// TODO Auto-generated method stub
		return this.kpiTlIndicatorKkDao.selectList(KpiTlIndicatorKk);
	}
	
	@Override
	public  List<String[]>  getSearchNode(String searchNode,String originalId) throws Exception{
		return this.kpiTlIndicatorKkDao.getSearchNode(searchNode,originalId);
	}
	
	public List<ComboBox> getIndicatorCombo(String type,ComboFilter comboFilter ) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("TRAR_NAME");
		if( UIUtils.isValidKeyId(type) )
		{
			comboFilter.setCondSql(" AND  TRAR_REFTYPE = '" + type +"'");
		}		
		//comboFilter.setIdField("TRAR_KEYID");
		comboFilter.setIdField("TRAR_NAME");
		comboFilter.setNameField("TRAR_NAME");
		comboFilter.setOrderByField("TRAR_NAME");		
		comboFilter.setTableName(TableNames.TBL_ENT_TL_TRAININGAREA);
		
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	
	@Override
	public String getPillarKeyId(String pillarCode)throws Exception {
		// TODO Auto-generated method stub
		//return this.kpiTlIndicatorKkDao.getPillarKeyId(pillarCode);
		return this.kpiTlActualandIndicatorServiceApi.getPillarKeyId(pillarCode);
	}
	public String getSortNo(KpiTlIndicatorKk newKpiTlIndicatorKk)throws Exception{
		// TODO Auto-generated method stub
		return this.kpiTlIndicatorKkDao.getSortNo(newKpiTlIndicatorKk);	
	}

	@Override
	public List<ComboBox> getIndicatorComboList(CommonFilter commonFilter,String pillCode,ComboFilter keyInd)throws Exception
	{
		//ComboFilter keyInd = new ComboFilter();	
		String condSql="";
		String type=commonFilter.getType();
		String pillarId=commonFilter.getPillarWise();
		if(CommonFunctions.isValidKeyId(type)){
			condSql+=" AND KINK_TYPE='"+type+"' ";
		}
		if(CommonFunctions.isValidKeyId(pillarId)){
			condSql+=" AND KINK_PILLARID='"+pillarId+"' ";
		}
		keyInd.setIdField("KINK_KEYID");		
		keyInd.setNameField("KINK_INDICATORNAME");
		//keyInd.setCodeField("KINK_INDICATORCODE");
		keyInd.setTableName(TableNames.TBL_KPI_TL_INDICATOR+","+TableNames.TBL_GEN_TL_TPMPILLARMST);
		keyInd.setCondSql(" AND KINK_LOCATION IN (SELECT LOCN_KEYID FROM GEN_VW_FNLN WHERE FNLN_KEYID ='"+ pillCode +"')" + condSql);
		keyInd.setOrderByField("KINK_SORTNO");
		//keyInd.setCondSql(" AND KINK_PILLARID =TPMP_KEYID AND TPMP_CODE='"+pillCode+"' ");
		return commonFilterDao.fillComboValues(keyInd);
	}

	@Override
	public List<String[]> getKPIProd( GridParams gridParams,CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return kpiTlIndicatorKkDao.getKPIProd(gridParams,commonFilter);
	}

	/*@Override
	public List<String[]> getKPIProdData(String indicatorId,GridParams gridParams,String pillCode) throws Exception {
		// TODO Auto-generated method stub
		return kpiTlIndicatorKkDao.getKPIProdData(indicatorId,gridParams,pillCode);
	}*/

	@Override
	public KpiTlIndicatorDeptLink createPillFactLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink, String usrm_ccno,String pillCode,String drillLevel,String indicatorId,String deptId,String isIndicatorFactory )throws Exception
	{
	
		if(isIndicatorFactory.equals("true"))
		{
			 CommonMessage.debugMsg("INSID  THE IF IN SERVICE IMPL");
		PillFactLinkFillValues(kpiTlIndicatorDeptLink,usrm_ccno,drillLevel);
		}
		PillFactLinkFillValues(kpiTlIndicatorDeptLink,usrm_ccno,drillLevel);
		CommonMessage.debugMsg("usrm_ccno:::"+usrm_ccno);
		kpiTlIndicatorDeptLink.setKidlCreatedby(usrm_ccno);
		CommonMessage.debugMsg("kpiTlIndicatorDeptLink.getKidlCreatedby"+kpiTlIndicatorDeptLink.getKidlCreatedby());
		//PillFactLinkFillValues(kpiTlIndicatorDeptLink,usrm_ccno,drillLevel);
		CommonMessage.debugMsg("INSIDE THE SERVICE IMPL");
		CommonMessage.debugMsg("pillCode"+pillCode);
		CommonMessage.debugMsg("drillLevel"+drillLevel);
		CommonMessage.debugMsg("indicatorId"+indicatorId);
		CommonMessage.debugMsg("deptId"+deptId);
		CommonMessage.debugMsg("isIndicatorFactory"+isIndicatorFactory);
		
		//return kpiTlIndicatorKkDao.createIndicatorDeptLink(kpiTlIndicatorDeptLink,pillCode,indicatorId,deptId,isIndicatorFactory,drillLevel);
		return kpiTlActualandIndicatorServiceApi.createPillFactLink(kpiTlIndicatorDeptLink, usrm_ccno, pillCode, drillLevel, indicatorId, deptId, isIndicatorFactory);
	}
	
	private List<KpiTlIndicatorDeptLink> PillFactLinkFillValues(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink, String usrm_ccno, String drillLevel)
	{
	    String dateTime = CommonFunctions.pg_dateTimeNow();

	    CommonMessage.debugMsg("INSID THE SERVICE IMPL Fill values!");

	    kpiTlIndicatorDeptLink.setKidlCreatedby(usrm_ccno);
	    
	    List<KpiTlIndicatorDeptLink> newKpiTlIndicatorDeptLinks = kpiTlIndicatorDeptLink.getmethodPillarFactlink();
	    List<KpiTlIndicatorDeptLink> newKpiTlIndicatorDeptLinkList = new ArrayList<KpiTlIndicatorDeptLink>();
	    
	    kpiTlIndicatorDeptLink.setKidlDepttype(kpiTlIndicatorDeptLink.getKidlDepttype());
	    
	    if(kpiTlIndicatorDeptLink.getKidlActive() == null){
	        kpiTlIndicatorDeptLink.setKidlActive("Y");
	    }
	    
	    kpiTlIndicatorDeptLink.setKidlEffectivedate(dateTime);
	    kpiTlIndicatorDeptLink.setKidlTempfield1("-");
	    kpiTlIndicatorDeptLink.setKidlTempfield2("-");
	    kpiTlIndicatorDeptLink.setKidlTempfield3("-");
	    kpiTlIndicatorDeptLink.setKidlTempfield4("-");
	    kpiTlIndicatorDeptLink.setKidlTempfield5("-");
	    kpiTlIndicatorDeptLink.setKidlCreatedby(usrm_ccno);
	    kpiTlIndicatorDeptLink.setKidlCreatedon(dateTime);
	    kpiTlIndicatorDeptLink.setKidlModifiedon(dateTime);
	    kpiTlIndicatorDeptLink.setKidlInactivedate(Constants.pgFutureNullDateTime);

	    // ========== FIX: Add IF-ELSE condition ==========
	    
	    // Check if list exists and has items
	    if(newKpiTlIndicatorDeptLinks != null && newKpiTlIndicatorDeptLinks.size() > 0)
	    {
	        // HAS LIST - Process list items
	        CommonMessage.debugMsg("PROCESSING LIST WITH " + newKpiTlIndicatorDeptLinks.size() + " ITEMS");
	        
	        int index = 0;
	        
	        for(KpiTlIndicatorDeptLink genKpiTlIndicatorDeptLink : newKpiTlIndicatorDeptLinks)
	        {
	            String dateTime1 = CommonFunctions.pg_dateTimeNow();

	            CommonMessage.debugMsg("INSIDE THE SERVICE IMPL::;>>>>>" + dateTime1);
	            genKpiTlIndicatorDeptLink.setKidlCreatedby(usrm_ccno);
	            genKpiTlIndicatorDeptLink.setKidlCreatedon(dateTime1);
	            genKpiTlIndicatorDeptLink.setKidlModifiedon(dateTime1);

	            KpiTlIndicatorDeptLink __genKpiTlIndicatorDeptLink1__ = (KpiTlIndicatorDeptLink)kpiTlIndicatorDeptLink.getmethodPillarFactlink().get(index);
	            index++;
	            
	            genKpiTlIndicatorDeptLink.setKidlDepttype(kpiTlIndicatorDeptLink.getKidlDepttype());
	            
	            if(genKpiTlIndicatorDeptLink.getKidlActive() == null){
	                genKpiTlIndicatorDeptLink.setKidlActive("Y");
	            }
	            
	            genKpiTlIndicatorDeptLink.setKidlEffectivedate(dateTime);
	            genKpiTlIndicatorDeptLink.setKidlTempfield1("-");
	            genKpiTlIndicatorDeptLink.setKidlTempfield2("-");
	            genKpiTlIndicatorDeptLink.setKidlTempfield3("-");
	            genKpiTlIndicatorDeptLink.setKidlTempfield4("-");
	            genKpiTlIndicatorDeptLink.setKidlTempfield5("-");
	            
	            if(genKpiTlIndicatorDeptLink.getKidlInactivedate() == null){
	                genKpiTlIndicatorDeptLink.setKidlInactivedate(Constants.pgFutureNullDateTime);
	            }
	            
	            newKpiTlIndicatorDeptLinkList.add(genKpiTlIndicatorDeptLink);
	        }
	    }
	    else
	    {
	        // NO LIST - Use main object
	        CommonMessage.debugMsg("PROCESSING SINGLE ITEM");
	        newKpiTlIndicatorDeptLinkList.add(kpiTlIndicatorDeptLink);
	    }
	    
	    

	    return newKpiTlIndicatorDeptLinkList;
	}

//		private List<KpiTlIndicatorDeptLink> PillFactLinkFillValues(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink, String usrm_ccno,String drillLevel) 
//	{
//		String dateTime = CommonFunctions.pg_dateTimeNow();
//	
//		 CommonMessage.debugMsg("INJSID THE SERVICE IMPL!");
//		
//		 
//		 kpiTlIndicatorDeptLink.setKidlCreatedby(usrm_ccno);
//		 //kpiTlIndicatorDeptLink.setKidlInactivedate(Constants.passNullDate);
//		List<KpiTlIndicatorDeptLink> newKpiTlIndicatorDeptLinks = kpiTlIndicatorDeptLink.getmethodPillarFactlink();
//		List<KpiTlIndicatorDeptLink> newKpiTlIndicatorDeptLinkList = new ArrayList<KpiTlIndicatorDeptLink>();
//		kpiTlIndicatorDeptLink.setKidlDepttype(kpiTlIndicatorDeptLink.getKidlDepttype());			
//		if(kpiTlIndicatorDeptLink.getKidlActive()==null){
//			kpiTlIndicatorDeptLink.setKidlActive("Y");
//		}
//		kpiTlIndicatorDeptLink.setKidlEffectivedate(dateTime);
//		kpiTlIndicatorDeptLink.setKidlTempfield1("-");
//		kpiTlIndicatorDeptLink.setKidlTempfield2("-");
//		kpiTlIndicatorDeptLink.setKidlTempfield3("-");
//		kpiTlIndicatorDeptLink.setKidlTempfield4("-");
//		kpiTlIndicatorDeptLink.setKidlTempfield5("-");
//		kpiTlIndicatorDeptLink.setKidlCreatedby(usrm_ccno);
//		kpiTlIndicatorDeptLink.setKidlCreatedon(dateTime);
//		kpiTlIndicatorDeptLink.setKidlModifiedon(dateTime);
//		
//		kpiTlIndicatorDeptLink.setKidlInactivedate(Constants.futureNullDate);
//
//		newKpiTlIndicatorDeptLinkList.add(kpiTlIndicatorDeptLink);
//		int index=0;
//		
//		//uncommented by sriram start
//		
//		/*for( KpiTlIndicatorDeptLink genKpiTlIndicatorDeptLink : newKpiTlIndicatorDeptLinks)
//		{	
//			String dateTime = CommonFunctions.dateTimeNow();
//			
//			CommonMessage.debugMsg("INSIDE THE SERVICE IMPL::;>>>>>"+dateTime);
//			genKpiTlIndicatorDeptLink.setKidlCreatedby(usrm_ccno);
//			genKpiTlIndicatorDeptLink.setKidlCreatedon(dateTime);
//			genKpiTlIndicatorDeptLink.setKidlModifiedon(dateTime);
//			
//			KpiTlIndicatorDeptLink genKpiTlIndicatorDeptLink1 = (KpiTlIndicatorDeptLink)kpiTlIndicatorDeptLink.getmethodPillarFactlink().get(index);
//			index++;
//			//genKpiTlIndicatorDeptLink.setKidlIndicatorid(genKpiTlIndicatorDeptLink1.getKidlIndicatorid());
//			//genKpiTlIndicatorDeptLink.setKidlDeptid(genKpiTlIndicatorDeptLink1.getKidlDeptid());
//			//genKpiTlIndicatorDeptLink.setKidlIsDelete(genKpiTlIndicatorDeptLink1.getKidlIsDelete());
//			genKpiTlIndicatorDeptLink.setKidlDepttype(kpiTlIndicatorDeptLink.getKidlDepttype());			
//			if(genKpiTlIndicatorDeptLink.getKidlActive()==null){
//			genKpiTlIndicatorDeptLink.setKidlActive("Y");
//			}
//			genKpiTlIndicatorDeptLink.setKidlEffectivedate(dateTime);
//			genKpiTlIndicatorDeptLink.setKidlTempfield1("-");
//			genKpiTlIndicatorDeptLink.setKidlTempfield2("-");
//			genKpiTlIndicatorDeptLink.setKidlTempfield3("-");
//			genKpiTlIndicatorDeptLink.setKidlTempfield4("-");
//			genKpiTlIndicatorDeptLink.setKidlTempfield5("-");
//			if(genKpiTlIndicatorDeptLink.getKidlInactivedate()==null){
//			genKpiTlIndicatorDeptLink.setKidlInactivedate(Constants.futureNullDate);
//			}
//			newKpiTlIndicatorDeptLinkList.add(genKpiTlIndicatorDeptLink);
//		}*/
//		
//		// end 
//		return newKpiTlIndicatorDeptLinkList;
//		//return kpiTlIndicatorDeptLink;
//	}

	@Override
	public Workbook KPIProdFactExportExcel(net.sf.json.JSONObject tblJSONObj, String format, String compId, String factId,String sectionId,String cellId,String drillLevel, String indicatorId, String pillCode,String pillarId,GridParams gridparams)throws Exception 
	{
		return kpiTlIndicatorKkDao.KpiExportExcel(tblJSONObj,format, compId,factId,sectionId,cellId,drillLevel, indicatorId, pillCode,pillarId,gridparams);
	}

	@Override
	public List<String[]> getKpiReport(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return kpiTlIndicatorKkDao.getKpiReport(commonFilter);
	}

	@Override
	public KpiTlIndicator createKPI(KpiTlIndicator newKpiTlIndicator,KpiTlIndicator existKpiTlIndicator) throws Exception {

		try{
			
			 CommonMessage.debugMsg("Description before fillValues: '" + newKpiTlIndicator.getKinkDescription() + "'");
		        
			String validationsFor="create";					
			validations.validate(newKpiTlIndicator,"KpiTlIndicatorKk",validationsFor);	
			newKpiTlIndicator=fillValues( newKpiTlIndicator,  existKpiTlIndicator);
			//return this.kpiTlIndicatorDao.create(newKpiTlIndicator);
			
			newKpiTlIndicator.setKinkIndicatorname(newKpiTlIndicator.getKinkDescription());
			
			return kpiTlActualandIndicatorServiceApi.insertRecord(newKpiTlIndicator);
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}

	@Override
	public KpiTlIndicator updateKPI(KpiTlIndicator newKpiTlIndicator,KpiTlIndicator existKpiTlIndicator) throws Exception {

		try{
			String validationsFor="create";					
			//validations.validate(newKpiTlIndicator,"KpiTlIndicatorKk",validationsFor);
			CommonMessage.debugMsg("njbcfg0......"+newKpiTlIndicator.getKinkKeyid());
			validations.validate(newKpiTlIndicator,"KpiTlIndicatorKk",validationsFor);	
			//newKpiTlIndicator=fillValues( newKpiTlIndicator,  existKpiTlIndicator);
			CommonMessage.debugMsg("parentId......"+newKpiTlIndicator.getKinkParentid());
			CommonMessage.debugMsg("njbcfg1......"+newKpiTlIndicator.getKinkKeyid());
			fillValues( newKpiTlIndicator,  existKpiTlIndicator);
			CommonMessage.debugMsg("njbcfg......"+newKpiTlIndicator.getKinkKeyid());
			//return this.kpiTlIndicatorDao.update(newKpiTlIndicator);
			return kpiTlActualandIndicatorServiceApi.updateRecord(newKpiTlIndicator);
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}
	
	private KpiTlIndicator fillValues(KpiTlIndicator newKpiTlIndicator,	KpiTlIndicator existKpiTlIndicator) {
	
		newKpiTlIndicator.setKinkActive("Y");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		if(newKpiTlIndicator.getKinkKeyid()==null)			
		{	
			newKpiTlIndicator.setKinkCreatedon(dateTime);	
		}
		else
		{
			newKpiTlIndicator.setKinkCreatedon(dateTime);
		}
		
		 if(newKpiTlIndicator.getKinkDescription()==null || 
			       newKpiTlIndicator.getKinkDescription().trim().isEmpty() || 
			       "{}".equals(newKpiTlIndicator.getKinkDescription().trim())) {
			        throw new IllegalArgumentException("Description cannot be empty or null");
			    }
		
	
//		if(newKpiTlIndicator.getKinkIndicatorname()==null)
//			newKpiTlIndicator.setKinkIndicatorname("{}");
//		
		if(newKpiTlIndicator.getKinkIndicatorcode()==null)
			newKpiTlIndicator.setKinkIndicatorcode("{}");
		
		if(newKpiTlIndicator.getKinkDescription()==null)
			newKpiTlIndicator.setKinkDescription("{}");

		if(newKpiTlIndicator.getKinkType()==null)
			newKpiTlIndicator.setKinkType("-");
		
		if(newKpiTlIndicator.getKinkLevelno()==null)
			newKpiTlIndicator.setKinkLevelno("0");
		if(newKpiTlIndicator.getKinkParentid()==null)
			newKpiTlIndicator.setKinkParentid("{}");	
		
		if(newKpiTlIndicator.getKinkSortno()==null)
			newKpiTlIndicator.setKinkSortno("{}");
		
		if(newKpiTlIndicator.getKinkIschild()==null)
			newKpiTlIndicator.setKinkIschild("Y");			
		
		if(newKpiTlIndicator.getKinkInputtype()==null)
			newKpiTlIndicator.setKinkInputtype("-");	
		
		if(newKpiTlIndicator.getKinkInputentry()==null)
			newKpiTlIndicator.setKinkInputentry("-");	
		
		if(newKpiTlIndicator.getKinkIdentifier()==null)
			newKpiTlIndicator.setKinkIdentifier("{}");
		
		if(newKpiTlIndicator.getKinkManualcalctype()==null)
			newKpiTlIndicator.setKinkManualcalctype("X");
		
		if(newKpiTlIndicator.getKinkUomid()==null)
			newKpiTlIndicator.setKinkUomid("{}");
		if(newKpiTlIndicator.getKinkFrequency()==null)
			newKpiTlIndicator.setKinkFrequency("X");
		
		if(newKpiTlIndicator.getKinkExcelname()==null)
			newKpiTlIndicator.setKinkExcelname("{}");	
		
		if(newKpiTlIndicator.getKinkDeptKeyid()==null)
			newKpiTlIndicator.setKinkDeptKeyid("{}");
		
		if(newKpiTlIndicator.getKinkCostarea()==null)
			newKpiTlIndicator.setKinkCostarea("{}");
		
		if(newKpiTlIndicator.getKinkTargetneed()==null)
			newKpiTlIndicator.setKinkTargetneed("Y");
		
		if(newKpiTlIndicator.getKinkPillarid()==null)
			newKpiTlIndicator.setKinkPillarid("{}");
		
		if(newKpiTlIndicator.getKinkImpactarea()==null)
			newKpiTlIndicator.setKinkImpactarea("{}");
		
		if(newKpiTlIndicator.getKinkGoals()==null)
			newKpiTlIndicator.setKinkGoals("{}");
		
		if(newKpiTlIndicator.getKinkSourceofkpi()==null)
			newKpiTlIndicator.setKinkSourceofkpi("{}");
		
		if(newKpiTlIndicator.getKinkKpireason()==null)
			newKpiTlIndicator.setKinkKpireason("{}");
		
		if(newKpiTlIndicator.getKinkAnnualtarget()==null)
			newKpiTlIndicator.setKinkAnnualtarget("{}");
		
		String KpiTlIndicator = newKpiTlIndicator.getKinkStartdate();
		newKpiTlIndicator.setKinkStartdate(CommonFunctions.pg_getDateTimeFromDate(newKpiTlIndicator.getKinkStartdate()));
		if(newKpiTlIndicator.getKinkStartdate()==null)
			newKpiTlIndicator.setKinkStartdate(Constants.pgFutureNullDateTime);
		
		String KpiTlIndicator2 = newKpiTlIndicator.getKinkEnddate();
		newKpiTlIndicator.setKinkEnddate(CommonFunctions.pg_getDateTimeFromDate(newKpiTlIndicator.getKinkEnddate()));
		if(newKpiTlIndicator.getKinkEnddate()==null)
			newKpiTlIndicator.setKinkEnddate(Constants.pgFutureNullDateTime);
				
		if(newKpiTlIndicator.getKinkLocation()==null)
			newKpiTlIndicator.setKinkLocation("{}");
		
		newKpiTlIndicator.setKinkModifiedon(dateTime);	
				
		
		return newKpiTlIndicator;

		
	}

	@Override
	public KpiTlIndicatorKk select(KpiTlIndicatorKk KpiTlIndicatorKk)	throws Exception {
		return this.kpiTlIndicatorKkDao.select(KpiTlIndicatorKk);	
	}

	@Override
	public String getSortNo(KpiTlIndicator kpiTlIndicatorKk) throws Exception {
		// TODO Auto-generated method stub
		//return this.kpiTlIndicatorDao.getSortNo(kpiTlIndicatorKk);	
		return this.kpiTlActualandIndicatorServiceApi.getSortNo(kpiTlIndicatorKk);
	}

	@Override
	public String getLocation(String flId) throws Exception {
		// TODO Auto-generated method stub
		//return this.kpiTlIndicatorDao.getLocation(flId);	
		return this.kpiTlActualandIndicatorServiceApi.getLocation(flId);
	}

	@Override
	public String validatekeyIndLink(
			KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception {
		// TODO Auto-generated method stub
		//return this.kpiTlIndicatorKkDao.validatekeyIndLink(kpiTlIndicatorDeptLink);
		return this.kpiTlActualandIndicatorServiceApi.validatekeyIndLink(kpiTlIndicatorDeptLink);
	}
	@Override
	public String validateKeyInactiveListLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception{
	   return this.kpiTlIndicatorKkDao.validateKeyInactiveListLink(kpiTlIndicatorDeptLink);  	
	}
	
	public String validatekeyActiveLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception{
		   return this.kpiTlIndicatorKkDao.validatekeyActiveLink(kpiTlIndicatorDeptLink);
	}

	@Override
	public String deptid(String kpid,String flid) throws SQLException {
		// TODO Auto-generated method stub
		return this.kpiTlIndicatorKkDao.deptid(kpid,flid);
	}

	@Override
	public String dapartmentid(String kidlkeyid) throws SQLException {
		// TODO Auto-generated method stub
		return this.kpiTlIndicatorKkDao.dapartmentid(kidlkeyid);
	}
	@Override
	public List<String[]> getKPIActiveInactiveProd( GridParams gridParams,CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return kpiTlIndicatorKkDao.getKPIActiveInactiveProd(gridParams,commonFilter);
	}



   
	 
	 
	
}
