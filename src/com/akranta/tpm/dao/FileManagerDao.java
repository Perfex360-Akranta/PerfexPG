 
package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.GenTlFilemanager;

public interface FileManagerDao {

	public abstract GenTlFilemanager create(GenTlFilemanager genTlFilemanager) throws Exception;
	public abstract GenTlFilemanager update(GenTlFilemanager genTlFilemanager) throws Exception;
	public abstract GenTlFilemanager delete(GenTlFilemanager genTlFilemanager) throws Exception;
	public abstract List<GenTlFilemanager> getAllFileText(String documentNo, String documentType) throws Exception;
	public DBActionTemplate getDbActionTemplate() ;
	public abstract String getFileCount(String documentNo, String getCount)throws Exception;
	public abstract String getDocLayoutId(String documentType)throws Exception;
	String getTypemstid(String documentType)throws Exception;

}

