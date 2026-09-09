package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.JhaTlAuditdtlItcDao;
import com.akranta.tpm.dao.sql.JhaTlAuditdtlSql;
import com.akranta.tpm.model.JhaTlAuditdtl;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class JhaTlAuditdtlItcDaoImpl implements JhaTlAuditdtlItcDao {


	private DBActionTemplate dbActionTemplate; 

	public JhaTlAuditdtlItcDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public JhaTlAuditdtl create(JhaTlAuditdtl jhaTlAuditdtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		JhaTlAuditdtlSql jhaTlAuditdtlSql = new JhaTlAuditdtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		JhaTlAuditmst jhaTlAuditmst = new JhaTlAuditmst();
		try{
			for(int i =0;i<jhaTlAuditdtl.getmethodjhaTlAuditdtl().size();i++)
			{	
				JhaTlAuditdtl newjhaTlAuditdtl = (JhaTlAuditdtl)jhaTlAuditdtl.getmethodjhaTlAuditdtl().get(i); // get detail info from list in empployee object
				newjhaTlAuditdtl.setJhadJhauditmasterid(jhaTlAuditdtl.getJhadJhauditmasterid());
				if(newjhaTlAuditdtl.getJhadRemarks()==null){
					newjhaTlAuditdtl.setJhadRemarks(jhaTlAuditdtl.getJhadRemarks());
				}
				if(newjhaTlAuditdtl.getJhadActive()==null){
					newjhaTlAuditdtl.setJhadActive(jhaTlAuditdtl.getJhadActive());
				}
				if(newjhaTlAuditdtl.getJhadCreatedby()==null){
					newjhaTlAuditdtl.setJhadCreatedby(jhaTlAuditdtl.getJhadCreatedby());
				}
				if(newjhaTlAuditdtl.getJhadCreatedon()==null){
					newjhaTlAuditdtl.setJhadCreatedon(jhaTlAuditdtl.getJhadCreatedon());
				}
				if(newjhaTlAuditdtl.getJhadModifiedon()==null){
					newjhaTlAuditdtl.setJhadModifiedon(jhaTlAuditdtl.getJhadModifiedon());
				}
				CommonMessage.debugMsg("jhaTlAuditdtl.getJhadGradeid()"+jhaTlAuditdtl.getmethodjhaTlAuditdtl().size());
				newjhaTlAuditdtl.setJhadKeyid(dbActionTemplate.getSequenceNumber(JhaTlAuditdtlSql.TBL_JHA_TL_AUDITDTL)); // set the sequnce number 
				sqls.add(JhaTlAuditdtlSql.getInsertSql(jhaTlAuditdtlSql.getJhadDbFields(), newjhaTlAuditdtl.getSaveArray())); // add insert sql for master table
			
			}
		
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditdtl;
	}
	
	public JhaTlAuditdtl update(JhaTlAuditdtl jhaTlAuditdtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		JhaTlAuditdtlSql jhaTlAuditdtlSql = new JhaTlAuditdtlSql();
		try {
			CommonMessage.debugMsg("InsideUpdate");
			for(int i =0;i<jhaTlAuditdtl.getmethodjhaTlAuditdtl().size();i++)
			{	
				CommonMessage.debugMsg("jhaTlAuditdtl.getmethodjhaTlAuditdtl()"+jhaTlAuditdtl.getmethodjhaTlAuditdtl());
				JhaTlAuditdtl newjhaTlAuditdtl = (JhaTlAuditdtl)jhaTlAuditdtl.getmethodjhaTlAuditdtl().get(i); 
				sqls.add(JhaTlAuditdtlSql.getUpdateSql(jhaTlAuditdtlSql.getJhadDbFields(), newjhaTlAuditdtl.getSaveArray()));
			
				dbActionTemplate.executeStatements(sqls);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return jhaTlAuditdtl;
	}
	
	public JhaTlAuditdtl delete(JhaTlAuditdtl jhaTlAuditdtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		JhaTlAuditdtlSql jhaTlAuditdtlSql = new JhaTlAuditdtlSql();
		try {
			
			sqls.add(JhaTlAuditdtlSql.getDeleteSql(jhaTlAuditdtlSql.getJhadDbFields(), jhaTlAuditdtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditdtl;
	}
	
}

