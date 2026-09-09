package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DocMgrBean;
import com.akranta.tpm.dao.DcmTlDocumentmanagerDao;
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


public interface DocManagerService {	
	public List<DcmTlDocumentlayout> getAllDocument(DcmTlDocumentlayout dcmTlDocumentlayout)throws Exception;
	public String getAllParent(String parentId,int level)throws Exception;
	public DcmTlDocumentmanager getFolderPath(String id)throws Exception;
	public DcmTlDocumentlayout getFolderList(String id)throws Exception;
	public DcmTlDocumentmanager getDocMgr(String id)throws Exception;	
	public List<DcmTlDocumentmanager> searchFile(String keywords, String fromDate, String toDate, String title, String subjectArea, 
			String category, String owner, String changes,String description, String approvedBy,String type,String schCond,DocTlTemplateDefvalDtl docTlTemplateDefvalDtl) throws Exception;
	
	public String getSubjectArea(String subjectAreaId)throws Exception;
	public String getCategory(String categoryId)throws Exception;	
	DcmTlDocumentlayout create(DcmTlDocumentlayout newDcmTlDocumentlayout, DcmTlDocumentlayout existDcmTlDocumentlayout)throws Exception;
	DcmTlDocumentlayout delete(DcmTlDocumentlayout newDcmTlDocumentlayout)throws Exception;
	DcmTlDocumentlayout update(DcmTlDocumentlayout newDcmTlDocumentlayout, DcmTlDocumentlayout existDcmTlDocumentlayout)throws Exception;
	DcmTlDocumentmanager createFile(DcmTlDocumentmanager newDcmTlDocumentmanager, DcmTlDocumentmanager existDcmTlDocumentmanager)throws Exception;
	DcmTlDocumentmanager deleteFile(DcmTlDocumentmanager newDcmTlDocumentmanager)throws Exception;
	DcmTlDocumentmanager updateFile(DcmTlDocumentmanager newDcmTlDocumentmanager, DcmTlDocumentmanager existDcmTlDocumentmanager,DcmTlRevisionhistory dcmTlRevisionhistory)throws Exception;
	DocTlRoleRights createUserRights(DocTlRoleRights newDocTlRoleRights, DocTlRoleRights existDocTlRoleRights,DocMgrBean docMgrBean)throws Exception;
	DocTlRoleRights deleteUserRights(DocTlRoleRights newDocTlRoleRights)throws Exception;
	DocTlRoleRights updateUserRights(DocTlRoleRights newDocTlRoleRights, DocTlRoleRights existDocTlRoleRights,DocMgrBean docMgrBean)throws Exception;
	public DocTlTemplateDefvalDtl createValues(DocTlTemplateDefvalDtl docTlTemplateDefvalDtl)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public List<ComboBox> getsubjectAreaCombo(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getTypeCombo(String condSq, ComboFilter comboFilterl) throws Exception;
	public List<ComboBox> getComboCategory(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getComboSearch(String searchTxt, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getComboRole(String searchTxt, ComboFilter comboFilter) throws Exception;
	public List<String []> getRevisionHistory(String fileId) throws Exception;
	public List<String []> getUserRights(String folderId) throws Exception;
	public List<String []> getTypeKeywords(String typeId,String fileId,String flag) throws Exception;
	public DcmTlDocumentmanagerDao getDocManagerDao();
	public DocTlTemplateDefMst createDocTemplate(DocTlTemplateDefMst newDocTlTemplateDefMst,DocTlTemplateDefMst existDocTlTemplateDefMst) throws Exception;
	public DocTlTemplateDefMst updateDocTemplate(DocTlTemplateDefMst newDocTlTemplateDefMst,DocTlTemplateDefMst existDocTlTemplateDefMst) throws Exception;
	public List<String[]> getdocTempList(CommonFilter commonFilter, String type) throws BusinessApplicationExceptions, Exception;
	public DocTlTemplateDefMst deleteDocTempData(DocTlTemplateDefMst newDocTlTemplateDefMst, DocTlTemplateDefDtl newDocTlTemplateDefdtl) throws Exception;
	public DocTlTemplateDefDtl deleteDocTemp(DocTlTemplateDefDtl doctempdtl) throws BusinessApplicationExceptions, Exception;
	public List<String[]> getdocumentTempList(CommonFilter commonFilter, String keyId);
	public int selectCount(CommonFilter commonFilter) throws Exception;
	public DocTlTemplateDefMst getdocTempGridList(String keyId) throws NoDataFoundException, SQLException, Exception;
	public List<String[]> getDocData(String documentNo, String documentType) throws Exception;

}
