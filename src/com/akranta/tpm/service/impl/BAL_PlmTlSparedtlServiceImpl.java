/*Created By : Siddharth.A*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_SprPckupFormBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.BAL_PlmTlSparedtlDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.BAL_PlmTlSparedtlDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.OplTlPillarlink;
import com.akranta.tpm.model.BAL_PlmTlSparedtl;
import com.akranta.tpm.service.BAL_PlmTlSparedtlService;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.BAL_PlmTlSparedtlServiceApi;

public class BAL_PlmTlSparedtlServiceImpl implements BAL_PlmTlSparedtlService {
	
	private BAL_PlmTlSparedtlDao plmTlSparedtlDao ;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	private BAL_PlmTlSparedtlServiceApi sparesapi;
	
	public BAL_PlmTlSparedtlServiceImpl(DBActionTemplate dbActionTemplate)
	{
		plmTlSparedtlDao = new BAL_PlmTlSparedtlDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
 	}
	
	public void BAL_PlmTlSparedtlServiceImplJwt(String JwtToken){
		try{
			plmTlSparedtlDao.BAL_PlmTlSparedtlDaoImplJwt(JwtToken);
			sparesapi = new BAL_PlmTlSparedtlServiceApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    // TODO Auto-generated constructor stub
	}
	
	public void setPlmTlSparedtlDao(BAL_PlmTlSparedtlDao PlmTlSparedtlDao)
	{
		this.plmTlSparedtlDao = PlmTlSparedtlDao;
	}

	public List<BAL_PlmTlSparedtl> save(List<BAL_PlmTlSparedtl> newPlmTlSparedtl,List<BAL_PlmTlSparedtl> oldPlmTlSparedtl,  BAL_SprPckupFormBean sprPckupFormBean)  throws ValidationExceptions, Exception 
	{
		
		for( BAL_PlmTlSparedtl plmTlSparedtl:newPlmTlSparedtl)
		{
			System.out.println("newPlmTlSparedtl in save func"+newPlmTlSparedtl.size());
			validations.validate(plmTlSparedtl,"SparesPickupCreation","create");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			
		}
		
		for(BAL_PlmTlSparedtl plmTlSparedtl:newPlmTlSparedtl)
		{
			System.out.println("Quantity in service impl"+plmTlSparedtl.getPspdQuantity());
			//if((newplmTlSparedtl.getPspdQuantity()==null )||(newplmTlSparedtl.getPspdQuantity().equals("0")))
			//{	
			validations.validate(plmTlSparedtl,"SparesPickupCreation","create");
		}
		
		fillValues(newPlmTlSparedtl,oldPlmTlSparedtl,sprPckupFormBean);
	
		//return  plmTlSparedtlDao.save(newPlmTlSparedtl);
		return  sparesapi.saveSparesPickup(newPlmTlSparedtl);
		
		
	}


	@Override
	public List<String[]> getAllMultiSelectSpr() throws Exception {
		// TODO Auto-generated method stub
		return this.plmTlSparedtlDao.getMultiSelectSpr();
	}

	@Override
	public BAL_PlmTlSparedtl delete(BAL_PlmTlSparedtl plmTlSparedtl) throws Exception {
		// TODO Auto-generated method stub
		return plmTlSparedtlDao.delete(plmTlSparedtl);
	}
	
	public List<ComboBox> getPartNo(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("SPRM_PARTNO");
		comboFilter.setNameField("SPRM_PARTNAME");
		comboFilter.setIdField("SPRM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SPARESMST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	
	@Override
	public List<String[]> getSprNameSelectSpr(String sprmKeyid)	throws Exception {
	
		return plmTlSparedtlDao.getSprNameSelectSpr(sprmKeyid);
	}

	@Override
	public List<String[]> getAllSprPickup(String standardId) throws Exception {

		//return plmTlSparedtlDao.getSprPickup(standardId);
		return sparesapi.getSprPickup(standardId);
		
		
	}

	@Override
	public List<String[]> getAlldeleteSpr(String sprId) throws Exception {
		
		return plmTlSparedtlDao.getDeleteSpr(sprId);
		
	}

	@Override
	public List<String[]> getDeleteAll(String standardId) throws Exception
	{
	
		// TODO Auto-generated method stub
		return plmTlSparedtlDao.getDeleteAll(standardId);
	}


	@Override
	public List<String[]> getAllSprCount(List<BAL_PlmTlSparedtl> spareCountList, String fnlnParentid)throws Exception
	{
		return plmTlSparedtlDao.getAllSprCount(spareCountList,fnlnParentid);
	}

	/*
	 * private List<BAL_PlmTlSparedtl> fillValues(List<BAL_PlmTlSparedtl>
	 * newPlmTlSparedtl,List<BAL_PlmTlSparedtl> oldPlmTlSparedtls,
	 * BAL_SprPckupFormBean sprPckupFormBean) {
	 * System.out.println("Inside Service impl");
	 * 
	 * String dateTime = CommonFunctions.dateTimeNow(); BAL_PlmTlSparedtl
	 * oldPlmTlSparedtl = null;
	 * 
	 * List<BAL_PlmTlSparedtl> plmTlSparedtList =new ArrayList<BAL_PlmTlSparedtl>();
	 * if( oldPlmTlSparedtls != null && oldPlmTlSparedtls.size() > 0 )
	 * oldPlmTlSparedtl = (BAL_PlmTlSparedtl)oldPlmTlSparedtls.get(0);
	 * 
	 * 
	 * //
	 * System.out.println("oldPlmTlSparedtl ="+oldPlmTlSparedtl.getPspdCreatedon());
	 * System.out.println("oldPlmTlSparedtl="+oldPlmTlSparedtls.size());
	 * System.out.println("sd");
	 * System.out.print("newPlmTlSparedtl"+newPlmTlSparedtl.size()); for(
	 * BAL_PlmTlSparedtl plmTlSparedtl : newPlmTlSparedtl) {
	 * System.out.println("Inside for loop");
	 * System.out.println("srvce Created on="+plmTlSparedtl.getPspdCreatedon());
	 * if(plmTlSparedtl.getPspdKeyid()==null) {
	 * plmTlSparedtl.setPspdCreatedon(dateTime);
	 * System.out.println("created on if keyid null"+plmTlSparedtl.getPspdCreatedon(
	 * )); } else {
	 * System.out.println("Inside Else created on"+oldPlmTlSparedtl.getPspdCreatedon
	 * ()); plmTlSparedtl.setPspdCreatedon(plmTlSparedtl.getPspdCreatedon()); }
	 * plmTlSparedtl.setPspdModifiedon(dateTime);
	 * System.out.println("fill values----="+plmTlSparedtl.getPspdModifiedon());
	 * System.out.println("plmTlSparedtl.getPspdKeyid() fill values="+plmTlSparedtl.
	 * getPspdKeyid());
	 * plmTlSparedtl.setPspdCreatedby(sprPckupFormBean.getCreatedBy());
	 * 
	 * plmTlSparedtl.setPspdStandardid(sprPckupFormBean.getStandardId());
	 * 
	 * System.out.println(plmTlSparedtl.getPspdCreatedby());
	 * System.out.println(plmTlSparedtl.getPspdCreatedon());
	 * System.out.println(plmTlSparedtl.getPspdKeyid() );
	 * System.out.println(plmTlSparedtl.getPspdModifiedon());
	 * System.out.println(plmTlSparedtl.getPspdQuantity());
	 * System.out.println(plmTlSparedtl.getPspdSpareid());
	 * System.out.println(plmTlSparedtl.getPspdStandardid());
	 * 
	 * 
	 * plmTlSparedtList.add(plmTlSparedtl); } return plmTlSparedtList;
	 * 
	 * }
	 */
	
	private List<BAL_PlmTlSparedtl> fillValues(List<BAL_PlmTlSparedtl> newPlmTlSparedtl,List<BAL_PlmTlSparedtl> oldPlmTlSparedtls, BAL_SprPckupFormBean sprPckupFormBean)
	{
		System.out.println("Inside Service impl");

		String dateTime = CommonFunctions.pg_dateTimeNow();
		BAL_PlmTlSparedtl oldPlmTlSparedtl = null;

		List<BAL_PlmTlSparedtl> plmTlSparedtList =new ArrayList<BAL_PlmTlSparedtl>();
		if( oldPlmTlSparedtls != null && oldPlmTlSparedtls.size() > 0 )
			oldPlmTlSparedtl =  (BAL_PlmTlSparedtl)oldPlmTlSparedtls.get(0);


	//	System.out.println("oldPlmTlSparedtl ="+oldPlmTlSparedtl.getPspdCreatedon());
		// FIX: guard the debug print too — oldPlmTlSparedtls is legitimately null on
		// the first save for a given standardId (nothing in session yet), which was
		// throwing an NPE here before we ever got to the actual save logic below.
		System.out.println("oldPlmTlSparedtl="+ (oldPlmTlSparedtls != null ? oldPlmTlSparedtls.size() : 0));
		System.out.println("sd");
		System.out.print("newPlmTlSparedtl"+newPlmTlSparedtl.size());
		for( BAL_PlmTlSparedtl plmTlSparedtl : newPlmTlSparedtl)
		{
			System.out.println("Inside for loop");
			System.out.println("srvce Created on="+plmTlSparedtl.getPspdCreatedon());
			if(plmTlSparedtl.getPspdKeyid()==null)
			{
				plmTlSparedtl.setPspdCreatedon(dateTime);
				System.out.println("created on if keyid null"+plmTlSparedtl.getPspdCreatedon());
			}
			else
			{
				// FIX: this branch also referenced oldPlmTlSparedtl.getPspdCreatedon()
				// unconditionally for logging, which would NPE whenever oldPlmTlSparedtl
				// is null (keyid non-null but no matching old record). Guarded it too.
				System.out.println("Inside Else created on"+ (oldPlmTlSparedtl != null ? oldPlmTlSparedtl.getPspdCreatedon() : null));
				plmTlSparedtl.setPspdCreatedon(plmTlSparedtl.getPspdCreatedon());
			}
			plmTlSparedtl.setPspdModifiedon(dateTime);
			System.out.println("fill values----="+plmTlSparedtl.getPspdModifiedon());
			System.out.println("plmTlSparedtl.getPspdKeyid() fill values="+plmTlSparedtl.getPspdKeyid());
			plmTlSparedtl.setPspdCreatedby(sprPckupFormBean.getCreatedBy());

			plmTlSparedtl.setPspdStandardid(sprPckupFormBean.getStandardId());

			System.out.println(plmTlSparedtl.getPspdCreatedby());
			System.out.println(plmTlSparedtl.getPspdCreatedon());
			System.out.println(plmTlSparedtl.getPspdKeyid() );
			System.out.println(plmTlSparedtl.getPspdModifiedon());
			System.out.println(plmTlSparedtl.getPspdQuantity());
			System.out.println(plmTlSparedtl.getPspdSpareid());
			System.out.println(plmTlSparedtl.getPspdStandardid());


			plmTlSparedtList.add(plmTlSparedtl);
		}
		return plmTlSparedtList;

	}

	

	
}
