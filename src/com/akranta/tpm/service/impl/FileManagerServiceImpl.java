
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CompanyBean;

import com.akranta.tpm.bean.GenTlCellmstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.FileManagerDao;

import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.FileManagerDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;

import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlFilemanager;
import com.akranta.tpm.model.GenTlFunctionallocn;

import com.akranta.tpm.model.GenTlCellmst;
import com.akranta.tpm.service.FileManagerService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class FileManagerServiceImpl implements FileManagerService {
	private FileManagerDao fileManagerDao;
 
	private Validations validations ;
	public FileManagerServiceImpl(DBActionTemplate dbActionTemplate)
	{
		fileManagerDao =  new FileManagerDaoImpl(dbActionTemplate);
		 
		validations = new Validations();
	}
	
	 
	public FileManagerDao getFileManagerDao(){
		return fileManagerDao;
	}
  

	public GenTlFilemanager create(GenTlFilemanager newGenTlFilemanager,GenTlFilemanager  existGenTlFilemanager )
	throws Exception {

		try {
			CommonMessage.debugMsg("before save fill service");
			String validationsFor;
			
			validationsFor = "create";
			
		//	validations.validate(newGenTlCellmst,"cellCreation",validationsFor);
			fillValues(newGenTlFilemanager,existGenTlFilemanager );
			CommonMessage.debugMsg("after save fill ");
			return fileManagerDao.create(newGenTlFilemanager);
		
			
		}catch (ValidationExceptions e){
				CommonMessage.debugMsg(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	 

	private GenTlFilemanager fillValues(GenTlFilemanager newGenTlFilemanager,GenTlFilemanager  existGenTlFilemanager) {
		newGenTlFilemanager.setFlmnActive("Y");
		CommonMessage.debugMsg("lllllll keyid " +newGenTlFilemanager.getFlmnKeyid());
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		
		if(! UIUtils.isValidKeyId( newGenTlFilemanager.getFlmnKeyid() )){
			
			newGenTlFilemanager.setFlmnCreatedon(dateTime);
			newGenTlFilemanager.setFlmnModifiedon(dateTime);
		}
		else
			newGenTlFilemanager.setFlmnModifiedon(dateTime);
			//newGenTlFilemanager.setFlmnCreatedon(existGenTlFilemanager.getFlmnCreatedon());
			
			
			
		
			//CommonMessage.debugMsg("lllllll keyidcreatedon " +exitGenTlFilemanager.getFlmnCreatedon());
			//newGenTlFilemanager.setFlmnCreatedon(exitGenTlFilemanager.getFlmnCreatedon());
		
			
			
		
		
		
		
			CommonMessage.debugMsg("fillvalue start");
		if( ! UIUtils.isValidKeyId( newGenTlFilemanager.getFlmnDescription()))
			newGenTlFilemanager.setFlmnDescription("{}");
		
		if( newGenTlFilemanager.getFlmnDoctype() == null )
			newGenTlFilemanager.setFlmnDoctype("{}");
	
	//	if( newGenTlFilemanager.getFlmnActive() == null )
		//	newGenTlFilemanager.setFlmnActive("{}");
		
		if( newGenTlFilemanager.getFlmnBloblength()== null )
			newGenTlFilemanager.setFlmnBloblength("0");
		
		//if( newGenTlFilemanager.getFlmnFileblob() == null )
		newGenTlFilemanager.setFlmnFileblob("EMPTY_BLOB()");
	
		if( newGenTlFilemanager.getFlmnFilename() == null )
			newGenTlFilemanager.setFlmnFilename("{}");
		
		if( newGenTlFilemanager.getFlmnRefdocno() == null )
			newGenTlFilemanager.setFlmnRefdocno("{}");
		
		if( newGenTlFilemanager.getFlmnRefdoctype() == null )
			newGenTlFilemanager.setFlmnRefdoctype("{}");
		
		if( newGenTlFilemanager.getFlmnSlno() == null )
			newGenTlFilemanager.setFlmnSlno("0");
			
		if( newGenTlFilemanager.getFlmnTemp1()== null )
			newGenTlFilemanager.setFlmnTemp1("N");	
		
		if( newGenTlFilemanager.getFlmnTemp2()== null )	
			newGenTlFilemanager.setFlmnTemp2("N");
		
		if( newGenTlFilemanager.getFlmnTemp3()== null )
			newGenTlFilemanager.setFlmnTemp3("N");
		
		if( newGenTlFilemanager.getFlmnTemp4()== null )	
			newGenTlFilemanager.setFlmnTemp4("N");
		
		if( newGenTlFilemanager.getFlmnTemp5()== null )
			newGenTlFilemanager.setFlmnTemp5("N");
		
	
		if( newGenTlFilemanager.getFlmnKeyid()== null )
			newGenTlFilemanager.setFlmnKeyid("{}");	
		
		if( newGenTlFilemanager.getFlmnCreatedby()== null )
			newGenTlFilemanager.setFlmnCreatedby("{}");	
		
		//	fillFunctionLoc(newGenTlFilemanager);
		return newGenTlFilemanager;
		
	}
	
	private GenTlFunctionallocn fillFunctionLoc(GenTlCellmst newGenTlCellmst){
		CommonMessage.debugMsg("setFnlnOriginalid");
		GenTlFunctionallocn  newGenTlFunctionallocn = new GenTlFunctionallocn();
		
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlCellmst.getCellKeyid());
		
		//CommonFunctions.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid());
		//if	(newGenTlCellmst.getFactLocationid()!=null){
			newGenTlFunctionallocn.setFnlnElementid(newGenTlCellmst.getCellFactoryid()+"-"+newGenTlCellmst.getCellSectionid()+"-"+newGenTlCellmst.getCellKeyid());
		//}else {
			
		//	newGenTlFunctionallocn.setFnlnElementid(newGenTlCellmst.getCellKeyid());
		//}
		newGenTlFunctionallocn.setFnlnElementtype("C");
		
		newGenTlFunctionallocn.setFnlnParentid(newGenTlCellmst.getCellFactoryid() +"-"+newGenTlCellmst.getCellSectionid() );
		
		newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlCellmst.getCellCode());
		newGenTlFunctionallocn.setFnlnDescription(newGenTlCellmst.getCellName());
		newGenTlFunctionallocn.setFnlnActive("Y");
		newGenTlCellmst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		return newGenTlFunctionallocn;
	}





	@Override
	public List<GenTlFilemanager> getFileText(String documentNo,String documentType) throws Exception {
		// TODO Auto-generated method stub
		return fileManagerDao.getAllFileText(documentNo,documentType);
	}





	@Override
	public GenTlFilemanager delete(GenTlFilemanager newGenTlFilemanager)
			throws Exception {
		
		return fileManagerDao.delete(newGenTlFilemanager);
	}
	
	public GenTlFilemanager update(GenTlFilemanager newGenTlFilemanager,GenTlFilemanager  existGenTlFilemanager )throws Exception {
		fillValues(newGenTlFilemanager,existGenTlFilemanager );
	return fileManagerDao.update(newGenTlFilemanager);
	}


	@Override
	public String getFileCount(String documentNo, String getCount)
			throws Exception {
		// TODO Auto-generated method stub
		return fileManagerDao.getFileCount(documentNo,getCount);
	}


	@Override
	public String getDocLayoutId(String documentType) throws Exception {
		// TODO Auto-generated method stub
		return fileManagerDao.getDocLayoutId( documentType);
	}
	
	@Override
	public String getTypemstid(String documentType)throws Exception {
		return fileManagerDao.getTypemstid( documentType);
	}

/*	public GenTlCellmst update(GenTlCellmst newGenTlCellmst,GenTlCellmst oldGenTlCellmst,GenTlCellmstBean genTlCellmstBean) throws Exception {	// TODO Auto-generated method stub
			
				CommonMessage.debugMsg("updatee" +newGenTlCellmst);
				validations.validate(newGenTlCellmst,"cellCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				fillValues(newGenTlCellmst,oldGenTlCellmst,genTlCellmstBean);
			
			return genTlCellmstDao.update(newGenTlCellmst);
	}
			
	
	@Override
	public GenTlCellmst delete(String delemode,GenTlCellmst genTlCellmst) throws Exception {
		// TODO Auto-generated method stub
		return genTlCellmstDao.delete(delemode,genTlCellmst);
	}*/


	
	


}
