package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.DcmTlDocumentlayout;

public interface DcmTlDocumentlayoutDao {

	public abstract DcmTlDocumentlayout create(DcmTlDocumentlayout dcmTlDocumentlayout) throws Exception;
	public abstract DcmTlDocumentlayout update(DcmTlDocumentlayout dcmTlDocumentlayout) throws Exception;
	public abstract DcmTlDocumentlayout delete(DcmTlDocumentlayout dcmTlDocumentlayout) throws Exception;
	public List<DcmTlDocumentlayout> getAllDocument(DcmTlDocumentlayout dcmTlDocumentlayout)throws Exception;
	public String getAllParent(String parentId,int level)throws Exception;
	public DcmTlDocumentlayout getFolderList(String id)throws Exception;

}

