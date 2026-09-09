package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.QtmTlIntrejectionmstDao;
import com.akranta.tpm.dao.sql.QtmTlIntrejectionmstSql;
import com.akranta.tpm.model.QtmTlIntrejectionmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class QtmTlIntrejectionmstDaoImpl implements QtmTlIntrejectionmstDao {


	private DBActionTemplate dbActionTemplate; 

	public QtmTlIntrejectionmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public QtmTlIntrejectionmst create(QtmTlIntrejectionmst qtmTlIntrejectionmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlIntrejectionmstSql qtmTlIntrejectionmstsql = new QtmTlIntrejectionmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			qtmTlIntrejectionmst.setQirmKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST)); // set the sequnce number
			if (!UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmParentmasterid())) 
				qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
			if (!UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmLinkmasterid())) 
				qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());	
			//qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
			//qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());			
			qtmTlIntrejectionmst.setQirmReferencekeyid(qtmTlIntrejectionmst.getQirmKeyid());
			String elementid = getElementID(qtmTlIntrejectionmst.getQirmMachineid());
			CommonMessage.debugMsg("elementid...."+elementid);
			qtmTlIntrejectionmst.setQirmElementid(elementid);
				
			sqls.add(QtmTlIntrejectionmstSql.getInsertSql(qtmTlIntrejectionmstsql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return qtmTlIntrejectionmst;
	}
	
	public QtmTlIntrejectionmst update(QtmTlIntrejectionmst qtmTlIntrejectionmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		QtmTlIntrejectionmstSql qtmTlIntrejectionmstSql = new QtmTlIntrejectionmstSql();
		try {

			sqls.add(QtmTlIntrejectionmstSql.getUpdateSql(qtmTlIntrejectionmstSql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return qtmTlIntrejectionmst;
	}
	
	public QtmTlIntrejectionmst delete(QtmTlIntrejectionmst qtmTlIntrejectionmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		QtmTlIntrejectionmstSql qtmTlIntrejectionmstsql = new QtmTlIntrejectionmstSql();
		try {
			
			sqls.add(qtmTlIntrejectionmstsql.getDeleteSql(qtmTlIntrejectionmstsql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return qtmTlIntrejectionmst;
	}
	
	public String getElementID(String originalId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select fnln_elementid from gen_tl_functionallocn where fnln_originalid = '"+originalId+"'";
		CommonMessage.debugMsg(sql);
		String getElementID = dbActionTemplate.getSingleValue(sql);
	
		return getElementID;
	}
}

