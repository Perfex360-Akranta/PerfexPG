package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlFeedbackDao;
import com.akranta.tpm.dao.sql.EntTlFeedbackSql;
import com.akranta.tpm.model.EntTlFeedback;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlFeedbackDaoImpl implements EntTlFeedbackDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlFeedbackDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlFeedback create(EntTlFeedback entTlFeedback) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlFeedbackSql entTlFeedbackSql = new EntTlFeedbackSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			CommonMessage.debugMsg("Size : "+entTlFeedback.getFeedback().size());
			if(entTlFeedback.getFeedbackdatas() != null && entTlFeedback.getFeedbackdatas().size() >= 0)
			{
				for(int i =0;i<entTlFeedback.getFeedbackdatas().size();i++)
				{	
					EntTlFeedback fbObj = (EntTlFeedback) entTlFeedback.getFeedbackdatas().get(i);
					CommonMessage.debugMsg("Key : " + i + ".........> "+fbObj.getFeedFbpmKeyid());
					if(CommonFunctions.isValidKeyId(fbObj.getFeedFermKeyid()))
					{
						entTlFeedback.setFeedFermKeyid(fbObj.getFeedFermKeyid());
						entTlFeedback.setFeedFbpmKeyid(fbObj.getFeedFbpmKeyid());					
						entTlFeedback.setFeedKeyid(dbActionTemplate.getSequenceNumber(EntTlFeedbackSql.TBL_ENT_TL_FEEDBACK)); // set the sequnce number 
						sqls.add(EntTlFeedbackSql.getInsertSql(entTlFeedbackSql.getFeedDbFields(), entTlFeedback.getSaveArray())); // add insert sql for master table
					}
					
				}
			}
			if(entTlFeedback.getFeedback() != null && entTlFeedback.getFeedback().size() >= 0)
			{
				for(int i =0;i<entTlFeedback.getFeedback().size();i++)
				{	
					EntTlFeedback quesFeedback = (EntTlFeedback) entTlFeedback.getFeedback().get(i);
					CommonMessage.debugMsg("Key : " + i + ".........> "+quesFeedback.getFeedFbpmKeyid());
					if(CommonFunctions.isValidKeyId(quesFeedback.getFeedAnswer()))
					{
						entTlFeedback.setFeedAnswer(quesFeedback.getFeedAnswer());
						entTlFeedback.setFeedFbpmKeyid(quesFeedback.getFeedFbpmKeyid());					
						entTlFeedback.setFeedKeyid(dbActionTemplate.getSequenceNumber(EntTlFeedbackSql.TBL_ENT_TL_FEEDBACK)); // set the sequnce number 
						sqls.add(EntTlFeedbackSql.getInsertSql(entTlFeedbackSql.getFeedDbFields(), entTlFeedback.getSaveArray())); // add insert sql for master table
					}
					
				}
			}
			CommonMessage.debugMsg("Size of SQL : " +sqls.size());
			

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlFeedback;
	}
	
	public EntTlFeedback update(EntTlFeedback entTlFeedback)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlFeedbackSql entTlFeedbackSql = new EntTlFeedbackSql();
		try {

			sqls.add(EntTlFeedbackSql.getUpdateSql(entTlFeedbackSql.getFeedDbFields(), entTlFeedback.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlFeedback;
	}
	
	public EntTlFeedback delete(EntTlFeedback entTlFeedback)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlFeedbackSql entTlFeedbackSql = new EntTlFeedbackSql();
		try {
			
			sqls.add(EntTlFeedbackSql.getDeleteSql(entTlFeedbackSql.getFeedDbFields(), entTlFeedback.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlFeedback;
	}
	
}

