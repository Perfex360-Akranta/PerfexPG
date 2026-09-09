package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.dao.GenTlEmpfunclocnlinkDao;
import com.akranta.tpm.dao.sql.EntTlBatchEmployeeLinkSql;
import com.akranta.tpm.dao.sql.GenTlEmpfunclocnlinkSql;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.GenTlEmpfunclocnlink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

  /* dao implementation */
 public class GenTlEmpfunclocnlinkDaoImpl implements GenTlEmpfunclocnlinkDao {


	private DBActionTemplate dbActionTemplate; 

	/*
	 * public JhaTlFiveSAuditareamstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	 */
	public GenTlEmpfunclocnlinkDaoImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public List<GenTlEmpfunclocnlink> create(List<GenTlEmpfunclocnlink> newgentlempfunclocnlinkList, EmployeeBean employeeBean) 	throws Exception {

		 
		GenTlEmpfunclocnlinkSql genTlEmpfunclocnlinkSql = new GenTlEmpfunclocnlinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			List<GenTlEmpfunclocnlink> methodslist = newgentlempfunclocnlinkList;
			GenSequenceNumber sequenceNumber= new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(), GenTlEmpfunclocnlinkSql.TBL_GEN_TL_EMPFUNCLOCNLINK,15, "EFLL", "", "Y");
			
		    //GenTlEmpfunclocnlink.setEfllkeyid(dbActionTemplate.getSequenceNumber(GenTlEmpfunclocnlinkSql.TBL_GEN_TL_EMPFUNCLOCNLINK)); // set the sequnce number
			
		    //GenTlEmpfunclocnlink.setEfllkeyid(dbActionTemplate.getSequenceNumber(GenTlEmpfunclocnlinkSql.TBL_GEN_TL_EMPFUNCLOCNLINK)); // set the sequnce number 
			String delsql="delete from "+ GenTlEmpfunclocnlinkSql.TBL_GEN_TL_EMPFUNCLOCNLINK+" where Efll_employeeid = '"+employeeBean.getEmpIdForFuncLoc()+"'";
			sqls.add(delsql);
			if(methodslist != null && methodslist.size()>0)
			{
				for(GenTlEmpfunclocnlink FactLinkList:methodslist)
				{
					
					String seqNo=sequenceNumber.getSequnceNumber();
					FactLinkList.setEfllkeyid(seqNo);
					CommonMessage.debugMsg("FactLinkList "+FactLinkList.getEfllemployeeid());
					sqls.add(GenTlEmpfunclocnlinkSql.getInsertSql(genTlEmpfunclocnlinkSql.getEfllDbFields(), ((GenTlEmpfunclocnlink) FactLinkList).getSaveArray())); // add insert sql for master table
					//sqls.add(EntTlBatchEmployeeLinkSql .getInsertSql(entTlBatchEmployeeLinkSql.getEnt_DbFields(), empBatchLink.getSaveArray()));
				}
			}
			
			//sqls.add(GenTlEmpfunclocnlinkSql.getInsertSql(genTlEmpfunclocnlinkSql.getEfllDbFields(), ((GenTlEmpfunclocnlink) factorylinkList).getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		  return null;
		//return genTlEmpfunclocnlink;
	}
	
	public GenTlEmpfunclocnlink update(GenTlEmpfunclocnlink genTlEmpfunclocnlink)throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlEmpfunclocnlinkSql genTlEmpfunclocnlinkSql = new GenTlEmpfunclocnlinkSql();
		try {

			sqls.add(GenTlEmpfunclocnlinkSql.getUpdateSql(genTlEmpfunclocnlinkSql.getEfllDbFields(), genTlEmpfunclocnlink.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlEmpfunclocnlink;
	}
	
	public GenTlEmpfunclocnlink delete(GenTlEmpfunclocnlink genTlEmpfunclocnlink)throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlEmpfunclocnlinkSql genTlEmpfunclocnlinkSql = new GenTlEmpfunclocnlinkSql();
		try {
			
			sqls.add(genTlEmpfunclocnlinkSql.getDeleteSql(genTlEmpfunclocnlinkSql.getEfllDbFields(), genTlEmpfunclocnlink.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlEmpfunclocnlink;
	}

	@Override
	public List<GenTlEmpfunclocnlink> create(
			GenTlEmpfunclocnlink newGenTlEmpfunclocnlink) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

