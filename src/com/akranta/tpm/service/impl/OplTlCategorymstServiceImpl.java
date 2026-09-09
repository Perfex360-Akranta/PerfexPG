/*Created By : Siddharth.A*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.dao.OplTlCategorymstDao;
import com.akranta.tpm.dao.impl.OplTlCategorymstDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.OplTlCategorymst;
import com.akranta.tpm.service.OplTlCategorymstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class OplTlCategorymstServiceImpl implements OplTlCategorymstService {
	
	private OplTlCategorymstDao oplTlCategorymstDao ; 
//	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	
	
	public OplTlCategorymstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		oplTlCategorymstDao = new OplTlCategorymstDaoImpl(dbActionTemplate);
//		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void setOplTlCategorymstDao(OplTlCategorymstDao oplTlCategorymstDao)
	{
		this.oplTlCategorymstDao = oplTlCategorymstDao;
	}
	@Override
	public OplTlCategorymst create(OplTlCategorymst newOplTlCategorymst,OplTlCategorymst oldOplTlCategorymst,  OplFormBean oplFormBean) throws ValidationExceptions,Exception 
	{

		try 
		{	
			CommonMessage.debugMsg("Inside Service Impl");
		
			String validationsFor;
			
			if(oplFormBean.getFormActionMode() != null && oplFormBean.getFormActionMode().equals("category") )
				validationsFor = "category";
			else
				validationsFor = "edit";
			
			validations.validate(newOplTlCategorymst,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			
			fillValues(newOplTlCategorymst,oldOplTlCategorymst,oplFormBean);
		
			return oplTlCategorymstDao.create(newOplTlCategorymst);
			//return newOplTlMst;
			
		}
		
		catch (ValidationExceptions e)
		{
			CommonMessage.debugMsg(" validate n create exception"+e.getLocalizedMessage());
			throw new ValidationExceptions(e.getMessage());
			//JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "OplCreationExceptions");
			//errMessage.put("formMode",oplFormBean.getFormActionMode());
			//errMessage.put("tpmException", "Data Not Saved");
			//out.print(errMessage.toString());
		
		}	
	}

	@Override
	public OplTlCategorymst update(OplTlCategorymst newOplTlCategorymst,OplTlCategorymst oldOplTlCategorymst,  OplFormBean oplFormBean)  throws ValidationExceptions, Exception {

		validations.validate(newOplTlCategorymst,"oplcreation","edit");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
		fillValues(newOplTlCategorymst,oldOplTlCategorymst,oplFormBean);
	
		return oplTlCategorymstDao.update(newOplTlCategorymst);
	}

	public List<String[]> getAllPillarNames(CommonFilter commonfilter) throws Exception
	{
		return this.oplTlCategorymstDao.getPillarNames(commonfilter);
	}


	public OplTlCategorymst deleteAllCategoryNames(OplTlCategorymst oplTlCategorymst) throws Exception
	{
		return this.oplTlCategorymstDao.deleteCategoryNames(oplTlCategorymst);
	}
	


	private OplTlCategorymst fillValues(OplTlCategorymst newOplTlCategorymst,OplTlCategorymst oldOplTlCategorymst, OplFormBean oplFormBean)
	{
		
		CommonMessage.debugMsg("Inside Service impl");
		newOplTlCategorymst.setOplcActive("Y");
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		CommonMessage.debugMsg("newOplTlMst.getOplcKeyid()"+newOplTlCategorymst.getOplcKeyid());
		
		if(newOplTlCategorymst.getOplcKeyid() == null )
		{	
			CommonMessage.debugMsg("nul, val");
			newOplTlCategorymst.setOplcCreatedon(dateTime);
		}
		else
		{
			
			newOplTlCategorymst.setOplcCreatedon(oldOplTlCategorymst.getOplcCreatedon());
		}
	
		newOplTlCategorymst.setOplcModifiedon(dateTime);
		CommonMessage.debugMsg("newOplTlCategorymst.getOplcTpmpillarid()="+newOplTlCategorymst.getOplcTpmpillarid());
	
			
		CommonMessage.debugMsg("newOplTlCategorymst.getOplcName()="+newOplTlCategorymst.getOplcName());
		
		CommonMessage.debugMsg("newOplTlCategorymst.getOplcCode()="+newOplTlCategorymst.getOplcCode());
		
		if( newOplTlCategorymst.getOplcDescription() == null )
			newOplTlCategorymst.setOplcDescription("{}");
		
		if( newOplTlCategorymst.getOplcRemarks() == null )
			newOplTlCategorymst.setOplcRemarks("{}");
		
		CommonMessage.debugMsg("In fill Service impl="+newOplTlCategorymst);
		
		return newOplTlCategorymst;
		
	}

	@Override
	public List<Object> getOplCategories(String categoryKeyid) throws Exception {
		// TODO Auto-generated method stub
		return this.oplTlCategorymstDao.getOplCategories(categoryKeyid);
	}


	
}
