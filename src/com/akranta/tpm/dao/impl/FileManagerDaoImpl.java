 
package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.FileManagerDao;
 
import com.akranta.tpm.dao.sql.GenTlFilemanagerSql;
import com.akranta.tpm.model.GenTlFilemanager;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class FileManagerDaoImpl implements FileManagerDao {


	private DBActionTemplate dbActionTemplate; 

	public FileManagerDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public DBActionTemplate getDbActionTemplate() {
		return this.dbActionTemplate ;
	}

	public GenTlFilemanager create(GenTlFilemanager genTlFilemanager) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlFilemanagerSql genTlFilemanagerSql = new GenTlFilemanagerSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
	//	try{
		
			genTlFilemanager.setFlmnKeyid(dbActionTemplate.getSequenceNumber(GenTlFilemanagerSql.TBL_GEN_TL_FILEMANAGER,10,"FLM","","")); // set the sequnce number 
			sqls.add(GenTlFilemanagerSql.getInsertSql(genTlFilemanagerSql.getFlmnDbFields(), genTlFilemanager.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
	/*	}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}*/
		return genTlFilemanager;
	}
	
	public GenTlFilemanager update(GenTlFilemanager genTlFilemanager)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlFilemanagerSql genTlFilemanagerSql = new GenTlFilemanagerSql();
		try {

			sqls.add(GenTlFilemanagerSql.getUpdateSql(genTlFilemanagerSql.getFlmnDbFields(), genTlFilemanager.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlFilemanager;
	}
	
	public GenTlFilemanager delete(GenTlFilemanager genTlFilemanager)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlFilemanagerSql genTlFilemanagerSql = new GenTlFilemanagerSql();
		try {
			
			sqls.add(GenTlFilemanagerSql.getDeleteSql(genTlFilemanagerSql.getFlmnDbFields(), genTlFilemanager.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlFilemanager;
	}

	@Override
	public List<GenTlFilemanager> getAllFileText(String documentNo,String documentType) throws Exception {
		GenTlFilemanager genTlFilemanager = new GenTlFilemanager();
		String sql = GenTlFilemanagerSql.getreloadgridSql(documentNo,documentType);
		return  (List<GenTlFilemanager>) dbActionTemplate.getDataList(sql, genTlFilemanager);
	 
		 
		
	}

	@Override
	public String getFileCount(String documentNo, String getCount)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = GenTlFilemanagerSql.getgridcountSql(documentNo,getCount);
		CommonMessage.debugMsg("GetFileCount Sql=="+sql);
		
		String count = dbActionTemplate.getSingleValue(sql);
		return count;
	}

	@Override
	public String getDocLayoutId(String documentType) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT DMLY_KEYID FROM DCM_TL_DOCUMENTLAYOUT WHERE 1=1 AND DMLY_NAME='"+documentType+"'";
		String docLayout = dbActionTemplate.getSingleValue(sql);
		return docLayout;
	}
	public String getTypemstid(String documentType)throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT DTPM_KEYID FROM DOC_TL_TEMPLATE_DEF_MST WHERE 1=1 AND UPPER(DTPM_CODE) =UPPER('"+documentType+"')";
		String docLayout = dbActionTemplate.getSingleValue(sql);
		return docLayout;
	}
}

