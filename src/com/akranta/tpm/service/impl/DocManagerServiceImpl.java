package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DocMgrBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.DcmTlDocumentlayoutDao;
import com.akranta.tpm.dao.DcmTlDocumentmanagerDao;
import com.akranta.tpm.dao.DocTlRoleRightsDao;
import com.akranta.tpm.dao.DocTlTemplateDefMstDao;
import com.akranta.tpm.dao.DocTlTemplateDefvalDtlDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DcmTlDocumentlayoutDaoImpl;
import com.akranta.tpm.dao.impl.DcmTlDocumentmanagerDaoImpl;
import com.akranta.tpm.dao.impl.DocTlRoleRightsDaoImpl;
import com.akranta.tpm.dao.impl.DocTlTemplateDefMstDaoImpl;
import com.akranta.tpm.dao.impl.DocTlTemplateDefvalDtlDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.DcmTlDocumentlayout;
import com.akranta.tpm.model.DcmTlDocumentmanager;
import com.akranta.tpm.model.DcmTlRevisionhistory;
import com.akranta.tpm.model.DocTlRoleRights;
import com.akranta.tpm.model.DocTlTemplateDefDtl;
import com.akranta.tpm.model.DocTlTemplateDefMst;
import com.akranta.tpm.model.DocTlTemplateDefvalDtl;
import com.akranta.tpm.model.GenTlFilemanager;
import com.akranta.tpm.service.DocManagerService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class DocManagerServiceImpl implements DocManagerService{
	DcmTlDocumentlayoutDao dcmTlDocumentlayoutDao;
	DcmTlDocumentmanagerDao dcmTlDocumentmanagerDao;
	DocTlRoleRightsDao docTlRoleRightsDao;
	DocTlTemplateDefvalDtlDao docTlTemplateDefvalDtlDao;
	DocTlTemplateDefMstDao docTlTemplateDefMstDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	public DocManagerServiceImpl(DBActionTemplate dbActionTemplate)
	{
		docTlTemplateDefMstDao=new DocTlTemplateDefMstDaoImpl(dbActionTemplate);
		dcmTlDocumentlayoutDao = new DcmTlDocumentlayoutDaoImpl(dbActionTemplate);
		dcmTlDocumentmanagerDao = new DcmTlDocumentmanagerDaoImpl(dbActionTemplate);
		docTlRoleRightsDao = new DocTlRoleRightsDaoImpl(dbActionTemplate);
		docTlTemplateDefvalDtlDao = new DocTlTemplateDefvalDtlDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	public DcmTlDocumentmanagerDao getDocManagerDao()
	{
		return dcmTlDocumentmanagerDao;
	}
	public List<DcmTlDocumentlayout> getAllDocument(DcmTlDocumentlayout dcmTlDocumentlayout)throws Exception
	{
		return this.dcmTlDocumentlayoutDao.getAllDocument(dcmTlDocumentlayout);
	}
	public String getAllParent(String parentId,int level)throws Exception
	{
		return this.dcmTlDocumentlayoutDao.getAllParent(parentId,level);
	}
	public DcmTlDocumentmanager getFolderPath(String id)throws Exception
	{
		return this.dcmTlDocumentmanagerDao.getFolderPath(id);
	}
	public DcmTlDocumentlayout getFolderList(String id)throws Exception
	{
		return this.dcmTlDocumentlayoutDao.getFolderList(id);
	}
	
	public String getSubjectArea(String subjectAreaId)throws Exception 	{
		return this.dcmTlDocumentmanagerDao.getSubjectArea(subjectAreaId);		
	}

	public String getCategory(String categoryId)throws Exception	{
		return this.dcmTlDocumentmanagerDao.getCategory(categoryId);
	}
	public DocTlTemplateDefMst createDocTemplate(DocTlTemplateDefMst newDocTlTemplateDefMst,DocTlTemplateDefMst existDocTlTemplateDefMst) throws Exception {
		fillValues(newDocTlTemplateDefMst,existDocTlTemplateDefMst);
		String validationsFor="create";
		if(newDocTlTemplateDefMst.getDocTempTlDtl()!=null && newDocTlTemplateDefMst.getDocTempTlDtl().size()>0){
			for(int i=0;i<newDocTlTemplateDefMst.getDocTempTlDtl().size();i++){
				DocTlTemplateDefDtl newDocTlTemplateDefdtl=newDocTlTemplateDefMst.getDocTempTlDtl().get(i);
				validations.validate(newDocTlTemplateDefdtl,"DocTemplate",validationsFor);
				
			}
		}
		return this.docTlTemplateDefMstDao.create(newDocTlTemplateDefMst,existDocTlTemplateDefMst);
	}
	private DocTlTemplateDefMst fillValues(DocTlTemplateDefMst newDocTlTemplateDefMst,DocTlTemplateDefMst existDocTlTemplateDefMst) {
		
		String dateTime = CommonFunctions.dateTimeNow();
		if(newDocTlTemplateDefMst.getDtpmActive()==null)
			newDocTlTemplateDefMst.setDtpmActive("Y");
		if(newDocTlTemplateDefMst.getDtpmCode()==null)
			newDocTlTemplateDefMst.setDtpmCode("{}");
		
		if(newDocTlTemplateDefMst.getDtpmCreatedon()==null)
			newDocTlTemplateDefMst.setDtpmCreatedon(dateTime);
		if(newDocTlTemplateDefMst.getDtpmDocumenttype()==null)
			newDocTlTemplateDefMst.setDtpmDocumenttype("{}");
		if(newDocTlTemplateDefMst.getDtpmModifiedon()==null)
			newDocTlTemplateDefMst.setDtpmModifiedon(dateTime);
		if(newDocTlTemplateDefMst.getDtpmTempfield()==null)
			newDocTlTemplateDefMst.setDtpmTempfield("-");
		if(newDocTlTemplateDefMst.getDtpmTempfield2()==null)
			newDocTlTemplateDefMst.setDtpmTempfield2("-");
		if(newDocTlTemplateDefMst.getDtpmTempfield3()==null)
			newDocTlTemplateDefMst.setDtpmTempfield3("-");
		if(newDocTlTemplateDefMst.getDtpmTempfield4()==null)
			newDocTlTemplateDefMst.setDtpmTempfield4("-");
		if(newDocTlTemplateDefMst.getDtpmTempfield5()==null)
			newDocTlTemplateDefMst.setDtpmTempfield5("-");
		
		return newDocTlTemplateDefMst;
			
	}
	@Override
	public DocTlTemplateDefMst updateDocTemplate(DocTlTemplateDefMst newDocTlTemplateDefMst,DocTlTemplateDefMst existDocTlTemplateDefMst) throws Exception {
		
		fillValues(newDocTlTemplateDefMst,existDocTlTemplateDefMst);
		
		return this.docTlTemplateDefMstDao.update(newDocTlTemplateDefMst,existDocTlTemplateDefMst); 
	}
	public List<String[]> getdocTempList(CommonFilter commonFilter, String type) throws BusinessApplicationExceptions, Exception {
		return this.docTlTemplateDefMstDao.getdocTempList(commonFilter,type);
	}
	@Override
	public DocTlTemplateDefMst deleteDocTempData(DocTlTemplateDefMst newDocTlTemplateDefMst, DocTlTemplateDefDtl newDocTlTemplateDefdtl) throws Exception {
	
		return this.docTlTemplateDefMstDao.deleteDocTempData(newDocTlTemplateDefMst,newDocTlTemplateDefdtl);
	}
	@Override
	public DocTlTemplateDefDtl deleteDocTemp(DocTlTemplateDefDtl doctempdtl) throws BusinessApplicationExceptions, Exception {
		
		return this.docTlTemplateDefMstDao.deleteDocTemp(doctempdtl);
	}

	public DcmTlDocumentmanager getDocMgr(String id)throws Exception
	{
		return this.dcmTlDocumentmanagerDao.getDocMgr(id);
	}
	public List<DcmTlDocumentmanager> searchFile(String keywords, String fromDate, String toDate, String title, String subjectArea, 
			String category, String owner, String changes,String description, String approvedBy,String type,String schCond,DocTlTemplateDefvalDtl docTlTemplateDefvalDtl) throws Exception
	{
		String condSql = "";
		String allCondSql = "";
		if(docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl()!= null && docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().size()>0) 
		{
			
			condSql = "AND Dtpv_Keyid IN (SELECT Dtpv_Keyid FROM DOC_TL_TEMPLATE_DEFVAL_DTL WHERE (";
			allCondSql =" AND "+docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().size()+"=";
			allCondSql += " (Select Count(*) From Doc_Tl_Template_Defval_Dtl Where ";
			for(int i =0;i<docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().size();i++)
			{
				DocTlTemplateDefvalDtl docTlTemplate = docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().get(i);
				if(i!=0)
				{
					condSql += " OR(";
					allCondSql +=" OR(";
				}				
				if(UIUtils.isValidKeyId(docTlTemplate.getDtpvDtpdKeyid()))
				{
					condSql += "DTPV_DTPD_KEYID='"+docTlTemplate.getDtpvDtpdKeyid()+"'";
					allCondSql += "DTPV_DTPD_KEYID='"+docTlTemplate.getDtpvDtpdKeyid()+"'";
				}
					condSql += " AND ";
					allCondSql += " AND ";
				if(UIUtils.isValidKeyId(docTlTemplate.getDtpvValue()))
				{
					condSql += "DTPV_VALUE='"+docTlTemplate.getDtpvValue().trim().toUpperCase()+"'";
					allCondSql += "DTPV_VALUE='"+docTlTemplate.getDtpvValue().trim().toUpperCase()+"'";
				}
				if(i!=0)
				{
					condSql += ")";
					allCondSql += ")";
				}
			}
			condSql += ")";
			allCondSql += ")";
			
			if(UIUtils.isValidKeyId(schCond))
			{
				if(schCond.equals("all"))
					condSql += allCondSql;
			}
			condSql += ")";
		}
		
		return this.dcmTlDocumentmanagerDao.searchFile(keywords,  fromDate,  toDate,  title,  subjectArea, 
				 category,  owner,  changes, description,  approvedBy,type,condSql);
	}
	public DcmTlDocumentlayout create(DcmTlDocumentlayout newDcmTlDocumentlayout,DcmTlDocumentlayout  existDcmTlDocumentlayout )
	throws Exception {

		try {
			CommonMessage.debugMsg("before save fill service");
			String validationsFor;			
			validationsFor = "create";
			
		//	validations.validate(newGenTlCellmst,"cellCreation",validationsFor);
			//fillValues(newDcmTlDocumentlayout,existDcmTlDocumentlayout );
			
			return dcmTlDocumentlayoutDao.create(newDcmTlDocumentlayout);
		
			
		}catch (ValidationExceptions e){
				CommonMessage.debugMsg(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
	public DcmTlDocumentlayout delete(DcmTlDocumentlayout newDcmTlDocumentlayout)
	throws Exception {

		return dcmTlDocumentlayoutDao.delete(newDcmTlDocumentlayout);
	}

	public DcmTlDocumentlayout update(DcmTlDocumentlayout newDcmTlDocumentlayout,DcmTlDocumentlayout  existDcmTlDocumentlayout )throws Exception {
		//fillValues(newDcmTlDocumentlayout,existDcmTlDocumentlayout );
		return dcmTlDocumentlayoutDao.update(newDcmTlDocumentlayout);
	}
	
	/*
	 * public DcmTlDocumentmanager createFile(DcmTlDocumentmanager
	 * newDcmTlDocumentmanager,DcmTlDocumentmanager existDcmTlDocumentmanager)
	 * throws Exception {
	 * 
	 * try { CommonMessage.debugMsg("before save fill service File"); String
	 * validationsFor; validationsFor = "keywords";
	 * 
	 * 
	 * validations.validate(newDcmTlDocumentmanager,"revHistory","keywords");
	 * //fillValues(newDcmTlDocumentlayout,existDcmTlDocumentlayout );
	 * 
	 * if(!UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmKeywords())) {
	 * newDcmTlDocumentmanager.setDmdmKeywords("{}"); }
	 * 
	 * if(!UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmDescription())) {
	 * newDcmTlDocumentmanager.setDmdmDescription("{}"); }
	 * 
	 * 
	 * return dcmTlDocumentmanagerDao.create(newDcmTlDocumentmanager);
	 * 
	 * 
	 * }catch (ValidationExceptions e){ CommonMessage.debugMsg(e.getMessage()); throw
	 * new ValidationExceptions(e.getMessage()); } }
	 */	
	
	@Override
	public DcmTlDocumentmanager createFile(DcmTlDocumentmanager newDcmTlDocumentmanager,
	                                       DcmTlDocumentmanager existDcmTlDocumentmanager) throws Exception {
	    try {
	        CommonMessage.debugMsg("before save fill service File");

	        // Defaults for optional text fields
	        if (!UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmKeywords())) {
	            newDcmTlDocumentmanager.setDmdmKeywords("{}");
	        }
	        if (!UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmDescription())) {
	            newDcmTlDocumentmanager.setDmdmDescription("{}");
	        }

	        // *** Critical fix: do NOT pass Oracle's EMPTY_BLOB() to PostgreSQL ***
	        // Leave the blob empty (NULL) and length 0. The SQL builder will place the literal as-is.
	        if (newDcmTlDocumentmanager.getDmdmBloblength() == null
	                || newDcmTlDocumentmanager.getDmdmBloblength().trim().isEmpty()) {
	            newDcmTlDocumentmanager.setDmdmBloblength("0");
	        }
	        if (newDcmTlDocumentmanager.getDmdmBlobfile() == null
	                || "EMPTY_BLOB()".equalsIgnoreCase(newDcmTlDocumentmanager.getDmdmBlobfile().trim())) {
	            newDcmTlDocumentmanager.setDmdmBlobfile("NULL"); // <- works on Oracle and PostgreSQL
	        }

	        // keep your existing validation choice (you removed revHistory)
	        // validations.validate(newDcmTlDocumentmanager,"revHistory","keywords");  // intentionally not used

	        return dcmTlDocumentmanagerDao.create(newDcmTlDocumentmanager);

	    } catch (ValidationExceptions e) {
	        CommonMessage.debugMsg(e.getMessage());
	        throw new ValidationExceptions(e.getMessage());
	    }
	}
	
	public DcmTlDocumentmanager deleteFile(DcmTlDocumentmanager newDcmTlDocumentmanager)
	throws Exception {

		return dcmTlDocumentmanagerDao.delete(newDcmTlDocumentmanager);
	}

	public DcmTlDocumentmanager updateFile(DcmTlDocumentmanager newDcmTlDocumentmanager,DcmTlDocumentmanager  existDcmTlDocumentmanager,DcmTlRevisionhistory dcmTlRevisionhistory )throws Exception {
		//fillValues(newDcmTlDocumentlayout,existDcmTlDocumentlayout );
		try
		{
			CommonMessage.debugMsg("Service Impl ");
			String validationsFor = "update";
			//if(UIUtils.isValidKeyId(dcmTlRevisionhistory.getDmrhCreatedby()))
			//{
				validations.validate(dcmTlRevisionhistory,"revHistory",validationsFor);
				
			//}
			validations.validate(newDcmTlDocumentmanager,"revHistory","keywords");
			
			fillRevisionHistoryValues(newDcmTlDocumentmanager,dcmTlRevisionhistory);
			
			if(!UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmKeywords()))
			{
				newDcmTlDocumentmanager.setDmdmKeywords("{}");
			}
			if(!UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmDescription()))
			{
				newDcmTlDocumentmanager.setDmdmDescription("{}");
			}
			
			
			/*if(!UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmKeywords()))
			{
				if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmKeywords()))
					newDcmTlDocumentmanager.setDmdmKeywords(existDcmTlDocumentmanager.getDmdmKeywords());
				else
					newDcmTlDocumentmanager.setDmdmKeywords("{}");
			}*/
			
			   // *** Critical fix: do NOT pass Oracle's EMPTY_BLOB() to PostgreSQL ***
	        // Leave the blob empty (NULL) and length 0. The SQL builder will place the literal as-is.
	        if (newDcmTlDocumentmanager.getDmdmBloblength() == null
	                || newDcmTlDocumentmanager.getDmdmBloblength().trim().isEmpty()) {
	            newDcmTlDocumentmanager.setDmdmBloblength("0");
	        }
	        if (newDcmTlDocumentmanager.getDmdmBlobfile() == null
	                || "EMPTY_BLOB()".equalsIgnoreCase(newDcmTlDocumentmanager.getDmdmBlobfile().trim())) {
	            newDcmTlDocumentmanager.setDmdmBlobfile("NULL"); // <- works on Oracle and PostgreSQL
	        }

			
			return dcmTlDocumentmanagerDao.update(newDcmTlDocumentmanager,dcmTlRevisionhistory);
			//return null;
		}
		catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		
	}
	
	public DocTlRoleRights createUserRights(DocTlRoleRights newDocTlRoleRights,DocTlRoleRights  existDocTlRoleRights,DocMgrBean docMgrBean)
	throws Exception {

		try {
			CommonMessage.debugMsg("before save fill service UserRights");
			String validationsFor;			
			validationsFor = "create";
			
			validations.validate(newDocTlRoleRights,"revHistory",validationsFor);
			fillUserRightsValues(newDocTlRoleRights,existDocTlRoleRights,docMgrBean);
			
			return docTlRoleRightsDao.create(newDocTlRoleRights);
		
			
		}catch (ValidationExceptions e){
				CommonMessage.debugMsg(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
	public DocTlRoleRights deleteUserRights(DocTlRoleRights newDocTlRoleRights)
	throws Exception {

		return docTlRoleRightsDao.delete(newDocTlRoleRights);
	}

	public DocTlRoleRights updateUserRights(DocTlRoleRights newDocTlRoleRights,DocTlRoleRights  existDocTlRoleRights,DocMgrBean docMgrBean)throws Exception {
		//fillValues(newDcmTlDocumentlayout,existDcmTlDocumentlayout );
		try
		{
			CommonMessage.debugMsg("Service Impl ");
			String validationsFor = "update";
			
			validations.validate(newDocTlRoleRights,"revHistory","keywords");
			
			fillUserRightsValues(newDocTlRoleRights,existDocTlRoleRights,docMgrBean);
			
			return docTlRoleRightsDao.update(newDocTlRoleRights);
			
		}
		catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		
	}
	public DocTlTemplateDefvalDtl createValues(DocTlTemplateDefvalDtl docTlTemplateDefvalDtl)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		String validationsFor="create";			
		if(docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl()!= null && docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().size()>0) 
		{
			for(int i =0;i<docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().size();i++)
			{
				DocTlTemplateDefvalDtl docTlTemplate = docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().get(i);
				validations.validate(docTlTemplate,"revHistory",validationsFor);
			}
		}
		
		return docTlTemplateDefvalDtlDao.create(docTlTemplateDefvalDtl);
	}
	public List<ComboBox> getTypeCombo(String condSql, ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("DTPM_DOCUMENTTYPE");
		comboFilter.setIdField("DTPM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_DOC_TL_TEMPLATE_DEF_MST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	
	public List<ComboBox> getsubjectAreaCombo(String condSql, ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("DSAM_NAME");
		comboFilter.setIdField("DSAM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_DCM_TL_SUBJECTAREAMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	public List<ComboBox> getComboCategory(String condSql, ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("DMCM_NAME");
		comboFilter.setIdField("DMCM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_DCM_TL_CATEGORYMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<ComboBox> getComboSearch(String searchText, ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setIdField("DMDM_KEYID");		
		comboFilter.setCodeField("DMDM_KEYWORDS");		
		comboFilter.setTableName(TableNames.TBL_DCM_TL_DOCUMENTMANAGER);
		StringBuffer sb = new StringBuffer();
		sb.append("AND DMDM_KEYWORDS LIKE '%"+searchText+"%'");
		comboFilter.setCondSql(sb.toString());
		return dcmTlDocumentmanagerDao.fillSearchBoxValues(comboFilter);
	}
	public List<ComboBox> getComboRole(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("ROLE_NAME");
		comboFilter.setIdField("ROLE_KEYID");	
		comboFilter.setTableName(TableNames.TBL_ADM_TL_ROLEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<String []> getRevisionHistory(String fileId) throws Exception
	{
		return this.dcmTlDocumentmanagerDao.getRevisionHistory(fileId);
	}
	public List<String []> getUserRights(String folderId) throws Exception
	{
		return this.dcmTlDocumentmanagerDao.getUserRights(folderId);
	}
	public List<String []> getTypeKeywords(String typeId,String fileId,String flag) throws Exception
	{
		return this.dcmTlDocumentmanagerDao.getTypeKeywords(typeId,fileId,flag);
	}
	private DcmTlRevisionhistory fillRevisionHistoryValues(DcmTlDocumentmanager newDcmTlDocumentmanager,DcmTlRevisionhistory dcmTlRevisionhistory )
	{
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		
		if(UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmKeyid()))
			dcmTlRevisionhistory.setDmrhDocid(newDcmTlDocumentmanager.getDmdmKeyid());
		if(UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmCreatedby()))
			dcmTlRevisionhistory.setDmrhCreatedby(newDcmTlDocumentmanager.getDmdmCreatedby());
		
		if(UIUtils.isValidKeyId(dcmTlRevisionhistory.getDmrhCreatedby()))
			dcmTlRevisionhistory.setDmrhRevby(dcmTlRevisionhistory.getDmrhCreatedby());

		
		if(!UIUtils.isValidKeyId(dcmTlRevisionhistory.getDmrhChanges()))
			dcmTlRevisionhistory.setDmrhChanges("{}");
		
		
		CommonMessage.debugMsg(" 1 Fillvalues "+newDcmTlDocumentmanager.getDmdmKeywords());
		
		if(!UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmKeywords()))
		{
			newDcmTlDocumentmanager.setDmdmKeywords("{}");
		}
		
		CommonMessage.debugMsg(" 1 Fillvalues :: After:: "+newDcmTlDocumentmanager.getDmdmKeywords());
		
		CommonMessage.debugMsg(" 2 Fillvalues "+newDcmTlDocumentmanager.getDmdmDescription());
		
		if(!UIUtils.isValidKeyId(newDcmTlDocumentmanager.getDmdmDescription()))
		{
			newDcmTlDocumentmanager.setDmdmDescription("{}");
		}
		
		CommonMessage.debugMsg(" 2 Fillvalues :: After :: "+newDcmTlDocumentmanager.getDmdmDescription());
		
		dcmTlRevisionhistory.setDmrhRevdate(dateTime);
		dcmTlRevisionhistory.setDmrhModifiedon(dateTime);
		dcmTlRevisionhistory.setDmrhActive("Y");
		dcmTlRevisionhistory.setDmrhTemp2("{}");
		dcmTlRevisionhistory.setDmrhTemp3("{}");
		return dcmTlRevisionhistory;
		
	}
	private DocTlRoleRights fillUserRightsValues(DocTlRoleRights newDocTlRoleRights,DocTlRoleRights existDocTlRoleRights,DocMgrBean docMgrBean)
	{
		
		String dateTime = CommonFunctions.dateTimeNow();
		String view = docMgrBean.getView();
		String modify = docMgrBean.getModify();
		String del = docMgrBean.getDelete();
		String dld = docMgrBean.getDownload();
		String userRights = docMgrBean.getUserrights();
		
		int r = 0;
		newDocTlRoleRights.setRlriCreatedon(dateTime);
		if(existDocTlRoleRights != null)
		{
			if(UIUtils.isValidKeyId(existDocTlRoleRights.getRlriModifiedon()))
				newDocTlRoleRights.setRlriModifiedon(existDocTlRoleRights.getRlriModifiedon());
			else
				newDocTlRoleRights.setRlriModifiedon(dateTime);
		}
		else
			newDocTlRoleRights.setRlriModifiedon(dateTime);
		
		newDocTlRoleRights.setRlriActive("Y");
		newDocTlRoleRights.setRlriTempfield1("-");
		newDocTlRoleRights.setRlriTempfield2("-");
		newDocTlRoleRights.setRlriTempfield3("-");
		newDocTlRoleRights.setRlriTempfield4("-");
		
		
		if(UIUtils.isValidKeyId(view))
		{
			
			r = Integer.parseInt(view);
		}
		if(UIUtils.isValidKeyId(modify))
		{
			
			if(r == 0)
				r = Integer.parseInt(modify);
			else
				r |= Integer.parseInt(modify);
		}
		if(UIUtils.isValidKeyId(del))
		{
			
			if(r == 0)
				r = Integer.parseInt(del);
			else
				r |= Integer.parseInt(del);
		}
		if(UIUtils.isValidKeyId(dld))
		{
			
			if(r == 0)
				r = Integer.parseInt(dld);
			else
				r |= Integer.parseInt(dld);
		}
		if(UIUtils.isValidKeyId(userRights))
		{
			
			if(r == 0)
				r = Integer.parseInt(userRights);
			else
				r |= Integer.parseInt(userRights);
		}
		
		CommonMessage.debugMsg("Rights : "+r);
		
	
		newDocTlRoleRights.setRlriRights(Integer.toString(r));
		return newDocTlRoleRights;
	}
	@Override
	public List<String[]> getdocumentTempList(CommonFilter commonFilter,String keyId) {
		
		return this.docTlTemplateDefMstDao.getdocumentTempList(commonFilter,keyId);
	}
	@Override
	public int selectCount(CommonFilter commonFilter) throws Exception {
		return this.docTlTemplateDefMstDao.selectCount(commonFilter);
	}
	@Override
	public DocTlTemplateDefMst getdocTempGridList(String keyId) throws NoDataFoundException, SQLException, Exception {
		
		return this.docTlTemplateDefMstDao.getdocTempGridList(keyId);
	}
	@Override
	public List<String[]> getDocData(String documentNo, String documentType) throws Exception {
		return this.docTlTemplateDefMstDao.getDocData( documentNo, documentType);
	}

}
