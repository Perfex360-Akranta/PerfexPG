package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.io.File;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.UtilityFormBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlToolsmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlToolsmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.GenTlToolsmst;
import com.akranta.tpm.service.UtilityService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class UtilityServiceImpl implements UtilityService {


	private GenTlToolsmstDao genTlToolsmstDao ; 
	private CommonFilterDao commonFilterDao;		
	private Validations validations ;
	public UtilityServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlToolsmstDao = new GenTlToolsmstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public List<ComboBox> getUtilityIdCombo(String condSql,ComboFilter comboFilter ) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("TOLM_CODE");
		comboFilter.setIdField("TOLM_KEYID");
		comboFilter.setNameField("TOLM_NAME");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TOOLSMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}

	@Override
	public List<ComboBox> getCategoryCombo(String condSql,ComboFilter comboFilter ) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("CATM_CODE");
		comboFilter.setIdField("CATM_KEYID");
		comboFilter.setNameField("CATM_NAME");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_CATEGORYMST);	
		return commonFilterDao.fillComboValues(comboFilter);			
	}

	public GenTlToolsmst create(GenTlToolsmst newGenTlToolsmst,GenTlToolsmst oldGenTlToolsmst,UtilityFormBean utilityFormBean) throws ValidationExceptions,Exception {

		try {
			
			String validationsFor;
			CommonMessage.debugMsg("Inside Impl");
			validationsFor = "create";			
			validations.validate(newGenTlToolsmst,"utilityCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			CommonMessage.debugMsg("After Validate");
			fillValues(newGenTlToolsmst,oldGenTlToolsmst,utilityFormBean);
			
			return genTlToolsmstDao.create(newGenTlToolsmst);			
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}
	}

	public GenTlToolsmst update(GenTlToolsmst newGenTlToolsmst,GenTlToolsmst oldGenTlToolsmst, UtilityFormBean utilityFormBean)  throws ValidationExceptions, Exception {
		CommonMessage.debugMsg("update " +oldGenTlToolsmst.getTolmCreatedon());
		validations.validate(newGenTlToolsmst,"utilityCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		fillValues(newGenTlToolsmst,oldGenTlToolsmst,utilityFormBean);
		
		return genTlToolsmstDao.update(newGenTlToolsmst);
	}

	@Override
	public GenTlToolsmst delete(GenTlToolsmst genTlToolsmst) throws Exception {

		return genTlToolsmstDao.delete(genTlToolsmst);
	}

	public GenTlToolsmst select(String UtilField) throws Exception
	{
		return this.genTlToolsmstDao.select(UtilField);
	}		
	
	public GenTlToolsimg insertToolImg(GenTlToolsmst newGenTlToolsmst ) 	throws Exception{
		
		GenTlToolsimg genTlToolsimg = newGenTlToolsmst.getGenTlToolsimg();
		
		if( genTlToolsimg != null && CommonFunctions.isValidKeyId(genTlToolsimg.getToimFilename()) ){
			
			genTlToolsimg = fillToolsImgValues(newGenTlToolsmst);
			return genTlToolsmstDao.insertToolImg(genTlToolsimg);
		}
		return null;
	}
	public GenTlToolsimg updateToolImg(GenTlToolsmst newGenTlToolsmst) 	throws Exception {
		GenTlToolsimg genTlToolsimg = newGenTlToolsmst.getGenTlToolsimg();
		
		if( genTlToolsimg != null && CommonFunctions.isValidKeyId(genTlToolsimg.getToimFilename()) ){
			
			genTlToolsimg = fillToolsImgValues(newGenTlToolsmst);
			return genTlToolsmstDao.updateToolImg(genTlToolsimg);
		}
		return null;
	}
	public GenTlToolsimg getToolsImage(GenTlToolsmst genTlToolsmst) throws NoDataFoundException, Exception{
	
		 GenTlToolsimg genTlToolsimg = genTlToolsmst.getGenTlToolsimg();
		 genTlToolsimg.setToimKeyid(genTlToolsmst.getTolmKeyid());
		 return genTlToolsmstDao.getToolsImage(genTlToolsimg);
		  
	}
	
	private GenTlToolsmst fillValues(GenTlToolsmst newGenTlToolsmst,GenTlToolsmst oldGenTlToolsmst,UtilityFormBean utilityFormBean)
	{
		newGenTlToolsmst.setTolmActive("Y");
		CommonMessage.debugMsg("lllllll");
		
		String dateTime = CommonFunctions.pg_pldateTimeNow();
		CommonMessage.debugMsg(dateTime);
		//CommonMessage.debugMsg(oldGenTlToolsmst.getTolmCreatedon());
		
				
		if(newGenTlToolsmst.getTolmKeyid() == null )
		//{
			newGenTlToolsmst.setTolmCreatedon(dateTime);
	//	}
		else
	//	{
			//newGenTlToolsmst.setTolmCreatedon(dateTime);
			
			newGenTlToolsmst.setTolmCreatedon(oldGenTlToolsmst.getTolmCreatedon());
//			String createdondate = oldGenTlToolsmst.getTolmCreatedon();
//		newGenTlToolsmst.setTolmCreatedon(CommonFunctions.pg_pggetFormatDateFromDate(oldGenTlToolsmst.getTolmCreatedon()));
	//	}	
		
		CommonMessage.debugMsg(dateTime);
		newGenTlToolsmst.setTolmModifiedon(dateTime);
		newGenTlToolsmst.setTolmType(utilityFormBean.getFormType());
		
		if( newGenTlToolsmst.getTolmRemarks() == null )
			newGenTlToolsmst.setTolmRemarks("{}");
		if( newGenTlToolsmst.getTolmIsimageavl() == null )
			newGenTlToolsmst.setTolmIsimageavl("N");

		CommonMessage.debugMsg("IMS : " +newGenTlToolsmst.getTolmKeyid());
		
		return newGenTlToolsmst; 
	}

	private GenTlToolsimg fillToolsImgValues(GenTlToolsmst genTlToolsmst){
		GenTlToolsimg genTlToolsimg = genTlToolsmst.getGenTlToolsimg();
	
		genTlToolsimg.setToimKeyid(genTlToolsmst.getTolmKeyid());
		//genTlToolsimg.setToimBlobimage(genTlToolsimg.getToimFilename());
		genTlToolsimg.setToimModifiedon(genTlToolsmst.getTolmModifiedon() );
		long length =0;
		
		String fileName = genTlToolsimg.getToimFilename();
		if( CommonFunctions.isValidKeyId(fileName)){
			fileName =  fileName.substring(fileName.lastIndexOf("/")+1);
			genTlToolsimg.setToimFilename(fileName );
			fileName =genTlToolsimg.getToimBlobimage() + fileName;
			CommonMessage.debugMsg(" fileName " + fileName);
			genTlToolsimg.setToimBlobimage(fileName);
			
			if( CommonFunctions.isFileExists(fileName ) )
				length = new File(fileName).length();
			
			genTlToolsimg.setToimBloblength(Long.toString(length) );
			
			return genTlToolsimg;
		}
		return genTlToolsimg;
	}
	
}
