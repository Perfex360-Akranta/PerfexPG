package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.SkillCheckListBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlSkillChecklistDao;

import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EntTlSkillChecklistDaoImpl;

import com.akranta.tpm.model.EntTlChecklistdtl;
import com.akranta.tpm.model.EntTlChecklistmst;
import com.akranta.tpm.model.EntTlSkillChecklist;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.service.SkillCheckListService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;


public class SlillCheckListServiceImpl implements SkillCheckListService{

	
	private EntTlSkillChecklistDao entTlSkillChecklistDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	public SlillCheckListServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entTlSkillChecklistDao =  new EntTlSkillChecklistDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public EntTlSkillChecklist create(EntTlSkillChecklist newEntTlSkillChecklist,EntTlSkillChecklist existEntTlSkillChecklist) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntTlChecklistmst select(String keyId,String dtlId) throws Exception {
		return this.entTlSkillChecklistDao.selectAll(keyId,dtlId);
	}

	
	public EntTlChecklistmst create(EntTlChecklistmst newEntTlChecklistmst,EntTlChecklistmst existEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception {
		
		try {			
			String validationsFor;			
			validationsFor = "create";
			CommonMessage.debugMsg("Validation..");
			
			
			List<EntTlChecklistdtl> newEntTlChecklistdtls = newEntTlChecklistmst.getCheckListDetail();
			CommonMessage.debugMsg("test123"+newEntTlChecklistdtls.size());
			validations.validate(newEntTlChecklistmst,"SkillCheckListCreation",validationsFor);
			for( EntTlChecklistdtl entTlChecklistdtl :newEntTlChecklistdtls)
			{
				CommonMessage.debugMsg("test123");
				validations.validate(entTlChecklistdtl,"SkillCheckListCreation",validationsFor);	
				
			}
			
			
			
			
			
			//validations.validate(newEntTlChecklistmst,"SkillCheckListCreation","createDtl");
			fillValues(newEntTlChecklistmst,existEntTlChecklistmst,skillCheckListBean);				
			return entTlSkillChecklistDao.create(newEntTlChecklistmst,skillCheckListBean);
		
			
		}catch (ValidationExceptions e){

			CommonMessage.debugMsg("e.getMessage(): to shown..."+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (Exception e){

			CommonMessage.debugMsg("e.getMessage()123:"+e.getMessage());
			throw new Exception(e.getMessage());
			
		}
		
	}

	private EntTlChecklistmst fillValues(EntTlChecklistmst newEntTlChecklistmst,EntTlChecklistmst existEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ValidationExceptions {
		
		//validations.validate(newEntTlChecklistmst,"SkillCheckListCreation","update");
		String dateTime = CommonFunctions.dateTimeNow();		
		CommonMessage.debugMsg("test data 1");
		if(newEntTlChecklistmst.getChkmKeyid() == null )			
		{	
			newEntTlChecklistmst.setChkmCreatedon(dateTime);			
		}					
		else
		{	CommonMessage.debugMsg("test data 12");
			newEntTlChecklistmst.setChkmCreatedon(dateTime);
		}
		CommonMessage.debugMsg("test data 2");
		newEntTlChecklistmst.setChkmModifiedon(dateTime);
		
		
		if( newEntTlChecklistmst.getChkmTempfield1() == null )
			newEntTlChecklistmst.setChkmTempfield1("-");
		
		if( newEntTlChecklistmst.getChkmTempfield2() == null )
			newEntTlChecklistmst.setChkmTempfield2("-");
		CommonMessage.debugMsg("test data 3");
		if( newEntTlChecklistmst.getChkmTempfield3() == null )
			newEntTlChecklistmst.setChkmTempfield3("-");			
		
		if( newEntTlChecklistmst.getChkmTempfield4() == null )
			newEntTlChecklistmst.setChkmTempfield4("-");
		
		if( newEntTlChecklistmst.getChkmTopiKeyid() == null )
			newEntTlChecklistmst.setChkmTopiKeyid("{}");
		CommonMessage.debugMsg("test data 4");	
		if( newEntTlChecklistmst.getChkmActive() == null )
			newEntTlChecklistmst.setChkmActive("Y");
		
		if( newEntTlChecklistmst.getChkmSkrmKeyid() == null )
			newEntTlChecklistmst.setChkmSkrmKeyid("{}");
		CommonMessage.debugMsg("test data 5");
		if( newEntTlChecklistmst.getChkmCreatedby() == null )
			newEntTlChecklistmst.setChkmCreatedby("{}");
		
		CommonMessage.debugMsg("SubEquipment out of fill valuuew");
		
		newEntTlChecklistmst.setCheckListDetail(checkListDtlFillValues(newEntTlChecklistmst,existEntTlChecklistmst,skillCheckListBean))	;
		return newEntTlChecklistmst; 
		
	}

	private List<EntTlChecklistdtl> checkListDtlFillValues(	EntTlChecklistmst newEntTlChecklistmst,EntTlChecklistmst existEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ValidationExceptions {
		
		String dateTime = CommonFunctions.dateTimeNow();	
		CommonMessage.debugMsg("detail table...........");
		List<EntTlChecklistdtl> newEntTlChecklistdtls = newEntTlChecklistmst.getCheckListDetail();
		
		List<EntTlChecklistdtl> oldEntTlChecklistdtls = null;
		EntTlChecklistdtl existEntTlChecklistdtl  = null;
		
		if( existEntTlChecklistmst != null){
			oldEntTlChecklistdtls = existEntTlChecklistmst.getCheckListDetail();
			if( oldEntTlChecklistdtls != null && oldEntTlChecklistdtls.size() > 0 )
				existEntTlChecklistdtl = oldEntTlChecklistdtls.get(0);
		}	CommonMessage.debugMsg("detail table0...........");
		
		List<EntTlChecklistdtl> newEntTlChecklistdtlList = new ArrayList<EntTlChecklistdtl>();
		CommonMessage.debugMsg("detail table01...........");
		CommonMessage.debugMsg("newEntTlChecklistdtls"+newEntTlChecklistdtls.size());
		for( EntTlChecklistdtl entTlChecklistdtl :newEntTlChecklistdtls)
		{
			//validations.validate(entTlChecklistdtl,"SkillCheckListCreation","updateDtl");
			CommonMessage.debugMsg("detail table1...........");
			if(entTlChecklistdtl.getChkdKeyid() == null )			
			{	
				entTlChecklistdtl.setChkdCreatedon(dateTime);			
			}					
			else
			{	
				entTlChecklistdtl.setChkdCreatedon(dateTime);	
			}
			CommonMessage.debugMsg("detail table2...........");
			entTlChecklistdtl.setChkdModifiedon(dateTime);
			
			
			if( entTlChecklistdtl.getChkdChkmKeyid() == null )
				entTlChecklistdtl.setChkdChkmKeyid("{}");
			
			if( entTlChecklistdtl.getChkdEffectiveDate() == null )
				entTlChecklistdtl.setChkdEffectiveDate(Constants.passNullDate);
			
			if( entTlChecklistdtl.getChkdInactiveDate() == null )
				entTlChecklistdtl.setChkdInactiveDate(Constants.passNullDate);			
			
			if( entTlChecklistdtl.getChkdName() == null )
				entTlChecklistdtl.setChkdName("{}");
			CommonMessage.debugMsg("detail table3...........");
			if( entTlChecklistdtl.getChkdOrderno() == null )
				entTlChecklistdtl.setChkdOrderno("{}");
					
			if( entTlChecklistdtl.getChkdRemarks() == null )
				entTlChecklistdtl.setChkdRemarks("{}");
			
			if( entTlChecklistdtl.getChkdTempfield1() == null )
				entTlChecklistdtl.setChkdTempfield1("-");
			
			if( entTlChecklistdtl.getChkdTempfield2() == null )
				entTlChecklistdtl.setChkdTempfield2("-");
			CommonMessage.debugMsg("detail table4...........");
			if( entTlChecklistdtl.getChkdTempfield3() == null )
				entTlChecklistdtl.setChkdTempfield3("-");
			
			if( entTlChecklistdtl.getChkdTempfield4() == null )
				entTlChecklistdtl.setChkdTempfield4("-");
			
			if( entTlChecklistdtl.getChkdCreatedon() == null )
				entTlChecklistdtl.setChkdCreatedon("{}");
			CommonMessage.debugMsg("detail table5...........");
			if( entTlChecklistdtl.getChkdActive() == null )
				entTlChecklistdtl.setChkdActive("Y");
			CommonMessage.debugMsg("detail table6...........");
			newEntTlChecklistdtlList.add(entTlChecklistdtl);
			CommonMessage.debugMsg("Check list from detail table to be considered...........");
			CommonMessage.debugMsg("test data..........0"+entTlChecklistdtl.getChkdName());
			
		}
		
		
		return newEntTlChecklistdtlList;
	}

	@Override
	public List<String[]> selectCheckList(String keyId) throws Exception {
		return this.entTlSkillChecklistDao.selectCheckList(keyId);
	}

	@Override
	public List<String[]> getAllCheckList() throws Exception {
		return this.entTlSkillChecklistDao.getAllCheckList();
	}

	@Override
	public EntTlChecklistmst update(EntTlChecklistmst newEntTlChecklistmst,EntTlChecklistmst existEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception {
		
		String validationsFor;			
		validationsFor = "update";
		List<EntTlChecklistdtl> newEntTlChecklistdtls = newEntTlChecklistmst.getCheckListDetail();
		
		validations.validate(newEntTlChecklistmst,"SkillCheckListCreation",validationsFor);
		for( EntTlChecklistdtl entTlChecklistdtl :newEntTlChecklistdtls)
		{			
			validations.validate(entTlChecklistdtl,"SkillCheckListCreation",validationsFor);			
		}	
		fillValues(newEntTlChecklistmst,existEntTlChecklistmst,skillCheckListBean);	
		return entTlSkillChecklistDao.update(newEntTlChecklistmst,skillCheckListBean);
	}

	@Override
	public EntTlChecklistmst delete(EntTlChecklistmst newEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception {
		return this.entTlSkillChecklistDao.delete(newEntTlChecklistmst,skillCheckListBean);
		
	}

	@Override
	public List<String[]> selectCheckListMainGrid() throws Exception {
		return this.entTlSkillChecklistDao.selectCheckListMainGrid();
	}

	@Override
	public Workbook SkillExportExcel(JSONObject colModel, String rptFormat) throws Exception {
		return this.entTlSkillChecklistDao.SkillExportExcel( colModel,rptFormat);
	}

	@Override
	public EntTlChecklistmst selectRank(String topicId, String rattingId) throws Exception {
		return this.entTlSkillChecklistDao.selectRank(topicId, rattingId);
	}

	@Override
	public EntTlChecklistdtl selectDtl(String keyId) throws Exception {
		return this.entTlSkillChecklistDao.selectDtl(keyId);
	}

	@Override
	public EntTlChecklistdtl deleteDetail(EntTlChecklistdtl newEntTlChecklistdtl) throws Exception {
		return this.entTlSkillChecklistDao.deleteDetail(newEntTlChecklistdtl);
	}

	@Override
	public List<String[]> selectCheckListRating(String topicId, String rattingId)throws Exception {
		return this.entTlSkillChecklistDao.selectCheckListRating(topicId, rattingId);
	}

	

}
