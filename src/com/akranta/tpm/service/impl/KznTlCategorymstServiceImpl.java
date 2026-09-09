/*Created By : Siddharth.A*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.dao.KznTlCategorymstDao;
import com.akranta.tpm.dao.impl.KznTlCategorymstDaoImpl;
import com.akranta.tpm.model.KznTlCategorymst;
import com.akranta.tpm.model.KznTlSubcategorymst;
import com.akranta.tpm.service.KznTlCategorymstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class KznTlCategorymstServiceImpl implements KznTlCategorymstService {
	
	private KznTlCategorymstDao kznTlCategorymstDao ; 
	private Validations validations ;
	
	
	public KznTlCategorymstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		kznTlCategorymstDao = new KznTlCategorymstDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void setOplTlCategorymstDao(KznTlCategorymstDao kznTlCategorymstDao)
	{
		this.kznTlCategorymstDao = kznTlCategorymstDao;
	}
	@Override
	public KznTlCategorymst create(KznTlCategorymst newKznTlCategorymst,KznTlCategorymst oldKznTlCategorymst,  KaizenFormBean kaizenFormBean) throws ValidationExceptions,Exception 
	{

		try 
		{	
			CommonMessage.debugMsg("Inside Service Impl");
			validations.validate(newKznTlCategorymst,"oplcreation","category");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			
			fillValues(newKznTlCategorymst,oldKznTlCategorymst,kaizenFormBean);
		
			return kznTlCategorymstDao.create(newKznTlCategorymst);
		}
		
		catch (ValidationExceptions e)
		{
			CommonMessage.debugMsg(" validate n create exception"+e.getLocalizedMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}

	@Override
	public KznTlCategorymst update(KznTlCategorymst newKznTlCategorymst,KznTlCategorymst oldKznTlCategorymst, KaizenFormBean kaizenFormBean)  throws ValidationExceptions, Exception {

	//	validations.validate(newKznTlCategorymst,"oplcreation","edit");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
		fillValues(newKznTlCategorymst,oldKznTlCategorymst,kaizenFormBean);
		return kznTlCategorymstDao.update(newKznTlCategorymst);
	}
	
	
	@Override
	public KznTlSubcategorymst create(KznTlSubcategorymst newKznTlSubcategorymst,KznTlSubcategorymst oldKznTlSubcategorymst,KaizenFormBean kaizenFormBean) throws ValidationExceptions,Exception 
	{
		CommonMessage.debugMsg("Inside Service Impl");
		validations.validate(newKznTlSubcategorymst,"oplcreation","category");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
		
		fillValues(newKznTlSubcategorymst, oldKznTlSubcategorymst, kaizenFormBean);
		return kznTlCategorymstDao.create(newKznTlSubcategorymst);
	}

	@Override
	public KznTlSubcategorymst update(KznTlSubcategorymst newKznTlSubcategorymst,KznTlSubcategorymst oldKznTlSubcategorymst,KaizenFormBean kaizenFormBean) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
		fillValues(newKznTlSubcategorymst, oldKznTlSubcategorymst, kaizenFormBean);
		
		return kznTlCategorymstDao.update(newKznTlSubcategorymst);
	}
	

	public List<String[]> getAllPillarNames( ) throws Exception
	{
		return this.kznTlCategorymstDao.getPillarNames();
	}
	
	@Override
	public List<Object> getKznCategories(String categoryKeyid) throws Exception {
		// TODO Auto-generated method stub
		return this.kznTlCategorymstDao.getKznCategories(categoryKeyid);
	}
	
	@Override
	public List<Object> getAllSubCategory(String ksmKeyId) throws Exception {
		// TODO Auto-generated method stub
		return this.kznTlCategorymstDao.getSubCategory(ksmKeyId);
	}


	public KznTlCategorymst deleteAllCategoryNames(KznTlCategorymst kznTlCategorymst) throws Exception
	{
		return this.kznTlCategorymstDao.deleteCategoryNames(kznTlCategorymst);
	}
	
	public KznTlSubcategorymst deleteAllSubcategoryNames(KznTlSubcategorymst kznTlSubcategorymst) throws Exception {
		return this.kznTlCategorymstDao.deleteSubcategoryNames(kznTlSubcategorymst);
	}
	
	
	private KznTlCategorymst fillValues(KznTlCategorymst newKznTlCategorymst,KznTlCategorymst oldKznTlCategorymst, KaizenFormBean kaizenFormBean)
	{
		
		CommonMessage.debugMsg("Inside Service impl fillValues");
		newKznTlCategorymst.setKctmActive("Y");
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		CommonMessage.debugMsg("newKznTlMst.getKctmKeyid()"+newKznTlCategorymst.getKctmKeyid());
		
		if(newKznTlCategorymst.getKctmKeyid() == null )
		{	
			CommonMessage.debugMsg("nul, val");
			newKznTlCategorymst.setKctmCreatedon(dateTime);
		}
		else
		{
			CommonMessage.debugMsg("else");
			newKznTlCategorymst.setKctmCreatedon(dateTime);
			CommonMessage.debugMsg("else"+newKznTlCategorymst.getKctmCreatedon());
		}
	
		newKznTlCategorymst.setKctmModifiedon(dateTime);
		CommonMessage.debugMsg("newKznTlCategorymst.getKctmTpmpillarid()="+newKznTlCategorymst.getKctmTpmpillarid());
	
		if( newKznTlCategorymst.getKctmDescription() == null )
			newKznTlCategorymst.setKctmDescription("{}");
		
		if( newKznTlCategorymst.getKctmRemarks() == null )
			newKznTlCategorymst.setKctmRemarks("{}");
		
		CommonMessage.debugMsg("In fill Service impl="+newKznTlCategorymst);
		
		return newKznTlCategorymst;
		
	}

	

	private KznTlSubcategorymst fillValues(KznTlSubcategorymst newKznTlSubcategorymst,KznTlSubcategorymst oldKznTlSubcategorymst, KaizenFormBean kaizenFormBean)
	{
		
		CommonMessage.debugMsg("Inside KznTlSubcategorymst Service impl");
		newKznTlSubcategorymst.setKscmActive("Y");
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		CommonMessage.debugMsg("newKznTlSubcategorymst.getKscmKeyid()"+newKznTlSubcategorymst.getKscmKeyid());
		
		if(newKznTlSubcategorymst.getKscmKeyid() == null )
		{	
			CommonMessage.debugMsg("nul, val");
			newKznTlSubcategorymst.setKscmCreatedon(dateTime);
		}
		else
		{
			
			newKznTlSubcategorymst.setKscmCreatedon(dateTime);
		}
	
		newKznTlSubcategorymst.setKscmModifiedon(dateTime);
		CommonMessage.debugMsg("newKznTlSubcategorymst.getKscmKctmKeyid()="+newKznTlSubcategorymst.getKscmKctmKeyid());
	
			
		CommonMessage.debugMsg("newKznTlSubcategorymst.getKscmSubcatname()="+newKznTlSubcategorymst.getKscmSubcatname());
		
		CommonMessage.debugMsg("newKznTlSubcategorymst.getKscmSubcatcode()="+newKznTlSubcategorymst.getKscmSubcatcode());
		
		
		if( newKznTlSubcategorymst.getKscmTempfield1() == null )
			newKznTlSubcategorymst.setKscmTempfield1("{}");
		
		if( newKznTlSubcategorymst.getKscmTempfield2() == null )
			newKznTlSubcategorymst.setKscmTempfield2("{}");
		
		CommonMessage.debugMsg("In fill Service impl="+newKznTlSubcategorymst);
		
		return newKznTlSubcategorymst;
		
	}





	

	

	
}
