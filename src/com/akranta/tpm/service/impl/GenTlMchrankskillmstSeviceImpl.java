package com.akranta.tpm.service.impl;

import java.util.List;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlMchrankskillmstBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlMchrankskillmstDao;
import com.akranta.tpm.dao.PlmTlCriteriamstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlMchrankskillmstDaoImpl;
import com.akranta.tpm.dao.impl.PlmTlCriteriamstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMchrankskillmst;
import com.akranta.tpm.model.PlmTlCriteriamst;
import com.akranta.tpm.service.GenTlMchrankskillmstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class GenTlMchrankskillmstSeviceImpl  implements GenTlMchrankskillmstService {

	private GenTlMchrankskillmstDao genTlMchrankskillmstDao;
	private PlmTlCriteriamstDao plmTlCriteriamstDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	
	public GenTlMchrankskillmstSeviceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlMchrankskillmstDao = new GenTlMchrankskillmstDaoImpl(dbActionTemplate);
		plmTlCriteriamstDao = new PlmTlCriteriamstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public List<ComboBox> getIncidentTypeComboList(CommonFilter commonFilter) throws Exception {
		
		ComboFilter BodyPart = new ComboFilter();
		BodyPart.setIdField("MRSK_KEYID");
		BodyPart.setCodeField("MRSK_CODE");
		BodyPart.setNameField("MRSK_NAME");
		
		BodyPart.setTableName(TableNames.TBL_GEN_TL_MCHRANKSKILLMST);
		
		return commonFilterDao.fillComboValues(BodyPart);
	}
	
	public List<String[]> getAllMchrankskill() throws Exception {
		// TODO Auto-generated method stub	
			return this.genTlMchrankskillmstDao.getAllMchrankskill();
	}

	@Override
	public GenTlMchrankskillmst getMchrankskill(String mchrankskillKeyId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public GenTlMchrankskillmst create(GenTlMchrankskillmst newGenTlMchrankskillmst,GenTlMchrankskillmst existGenTlMchrankskillmst,GenTlMchrankskillmstBean genTlMchrankskillmstBean) throws ValidationExceptions,Exception {
				
			CommonMessage.debugMsg("serv impl");
			validations.validate(newGenTlMchrankskillmst,"GenTlMchrankskillmst","create");
			fillvalues(newGenTlMchrankskillmst,existGenTlMchrankskillmst,genTlMchrankskillmstBean);
			CommonMessage.debugMsg("serv impl2");
			return genTlMchrankskillmstDao.create(newGenTlMchrankskillmst);
	}
	
	public GenTlMchrankskillmst update(GenTlMchrankskillmst newGenTlMchrankskillmst,GenTlMchrankskillmst existGenTlMchrankskillmst,GenTlMchrankskillmstBean genTlMchrankskillmstBean)throws Exception {
		// TODO Auto-generated method stub
		try
		
		{			
			 validations.validate(newGenTlMchrankskillmst,"GenTlMchrankskillmst","update"); 
			 
			 fillvalues(newGenTlMchrankskillmst,existGenTlMchrankskillmst,genTlMchrankskillmstBean);
			 return genTlMchrankskillmstDao.update(newGenTlMchrankskillmst);
			
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
			
		}
	}
	
	public GenTlMchrankskillmst delete(GenTlMchrankskillmst newGenTlMchrankskillmst)throws BusinessApplicationExceptions, Exception  {
		// TODO Auto-generated method stub
		
		return genTlMchrankskillmstDao.delete(newGenTlMchrankskillmst);
	
	}
	
	private GenTlMchrankskillmst fillvalues(GenTlMchrankskillmst newGenTlMchrankskillmst,GenTlMchrankskillmst existGenTlMchrankskillmst,GenTlMchrankskillmstBean genTlMchrankskillmstBean) {
		// TODO Auto-generated method stub
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		if(newGenTlMchrankskillmst.getMrskName()== null)
			newGenTlMchrankskillmst.setMrskName("{}");
		if(newGenTlMchrankskillmst.getMrskCode()== null)
			newGenTlMchrankskillmst.setMrskCode("{}");
		if(newGenTlMchrankskillmst.getMrskMaximumpoints()== null)
			newGenTlMchrankskillmst.setMrskMaximumpoints("-");
		if(newGenTlMchrankskillmst.getMrskActive()== null)
			newGenTlMchrankskillmst.setMrskActive("Y");
		if(newGenTlMchrankskillmst.getMrskCreatedon()== null)
			newGenTlMchrankskillmst.setMrskCreatedon(dateTime);
		if(newGenTlMchrankskillmst.getMrskModifiedon()== null)
			newGenTlMchrankskillmst.setMrskModifiedon(dateTime);
		
		return newGenTlMchrankskillmst;
	}

	@Override
	public PlmTlCriteriamst create(PlmTlCriteriamst newePlmTlCriteriamst,
			PlmTlCriteriamst existPlmTlCriteriamst) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("serv impl");
		validations.validate(newePlmTlCriteriamst,"PlmTlCriteriamst","create");
		fillvalues(newePlmTlCriteriamst,existPlmTlCriteriamst);
		CommonMessage.debugMsg("serv impl2");
		return plmTlCriteriamstDao.create(newePlmTlCriteriamst);
	}

	@Override
	public PlmTlCriteriamst update(PlmTlCriteriamst newePlmTlCriteriamst,
			PlmTlCriteriamst existPlmTlCriteriamst) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("serv impl");
		validations.validate(newePlmTlCriteriamst,"PlmTlCriteriamst","update");
		fillvalues(newePlmTlCriteriamst,existPlmTlCriteriamst);
		CommonMessage.debugMsg("serv impl2");
		return plmTlCriteriamstDao.update(newePlmTlCriteriamst);
	}

	@Override
	public PlmTlCriteriamst delete(PlmTlCriteriamst newePlmTlCriteriamst)
			throws Exception {
		// TODO Auto-generated method stub
		return plmTlCriteriamstDao.delete(newePlmTlCriteriamst);
	}
	
	private PlmTlCriteriamst fillvalues(PlmTlCriteriamst newPlmTlCriteriamst,PlmTlCriteriamst existPlmTlCriteriamst) {
		// TODO Auto-generated method stub
		
		String dateTime = CommonFunctions.dateTimeNow();
		if(newPlmTlCriteriamst.getCriaFlid()== null)
			newPlmTlCriteriamst.setCriaFlid("{}");
		if(newPlmTlCriteriamst.getCriaName()== null)
			newPlmTlCriteriamst.setCriaName("{}");
		if(newPlmTlCriteriamst.getCriaCode()== null)
			newPlmTlCriteriamst.setCriaCode("{}");
		if(newPlmTlCriteriamst.getCriaMinimumpoints()== null)
			newPlmTlCriteriamst.setCriaMinimumpoints("0");
		if(newPlmTlCriteriamst.getCriaMaximumpoints()== null)
			newPlmTlCriteriamst.setCriaMaximumpoints("0");
		if(newPlmTlCriteriamst.getCriaTradeid()== null)
		newPlmTlCriteriamst.setCriaTradeid("-");
		newPlmTlCriteriamst.setCriaTempfield2("-");
		newPlmTlCriteriamst.setCriaTempfield3("-");
		newPlmTlCriteriamst.setCriaTempfield4("-");
		newPlmTlCriteriamst.setCriaTempfield5("-");
		if(newPlmTlCriteriamst.getCriaActive()== null)
			newPlmTlCriteriamst.setCriaActive("Y");
		if(newPlmTlCriteriamst.getCriaCreatedon()== null)
			newPlmTlCriteriamst.setCriaCreatedon(dateTime);
		if(newPlmTlCriteriamst.getCriaModifiedon()== null)
			newPlmTlCriteriamst.setCriaModifiedon(dateTime);
		
		return newPlmTlCriteriamst;
	}

	@Override
	public List<String[]> getCriteriaMstList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return plmTlCriteriamstDao.getCriteriaMstList(commonFilter);
	}
}
