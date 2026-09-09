package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.dao.FileManagerDao;
import com.akranta.tpm.model.GenTlFilemanager;

public interface FileManagerService {

	GenTlFilemanager create(GenTlFilemanager newGenTlFilemanager, GenTlFilemanager existGenTlFilemanager)throws Exception;

	List<GenTlFilemanager> getFileText(String documentNo, String documentType) throws Exception;

	GenTlFilemanager delete(GenTlFilemanager newGenTlFilemanager)throws Exception;

	GenTlFilemanager update(GenTlFilemanager newGenTlFilemanager, GenTlFilemanager existGenTlFilemanager)throws Exception;
	public FileManagerDao getFileManagerDao();

	String getFileCount(String documentNo, String string)throws Exception;

	String getDocLayoutId(String documentType)throws Exception;
	String getTypemstid(String documentType)throws Exception;
	

}
