package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.DcmTlDocumentmanager;
import com.akranta.tpm.model.DcmTlRevisionhistory;
import com.akranta.tpm.model.DocTlTemplateDefvalDtl;

public interface DcmTlDocumentmanagerDao {

	public abstract DcmTlDocumentmanager create(DcmTlDocumentmanager dcmTlDocumentmanager) throws Exception;
	public abstract DcmTlDocumentmanager update(DcmTlDocumentmanager dcmTlDocumentmanager,DcmTlRevisionhistory dcmTlRevisionhistory) throws Exception;
	public abstract DcmTlDocumentmanager delete(DcmTlDocumentmanager dcmTlDocumentmanager) throws Exception;
	public DcmTlDocumentmanager getFolderPath(String id)throws Exception;
	public DcmTlDocumentmanager getDocMgr(String id)throws Exception;
	public List<DcmTlDocumentmanager> searchFile(String keywords, String fromDate, String toDate, String title, String subjectArea, 
			String category, String owner, String changes,String description, String approvedBy,String type,String condSql) throws Exception;
	public List<String []> getRevisionHistory(String fileId) throws Exception;
	public List<String []> getUserRights(String folderId) throws Exception;
	public List<String []> getTypeKeywords(String typeId,String fileId,String flag) throws Exception;
	public List<ComboBox> fillSearchBoxValues(ComboFilter comboFilter) throws Exception;
	public DBActionTemplate getDbActionTemplate() ;

	public String getSubjectArea(String subjectAreaId)throws Exception ;
	public String getCategory(String categoryId)throws Exception;
	
}

