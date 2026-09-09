package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.dao.GenTlMchrankskillmstDao;
import com.akranta.tpm.dao.sql.GenTlMchrankskillmstSql;
import com.akranta.tpm.model.GenTlMchrankskillmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlMchrankskillmstDaoImpl implements GenTlMchrankskillmstDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlMchrankskillmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlMchrankskillmst create(GenTlMchrankskillmst genTlMchrankskillmst) 	throws Exception {
		CommonMessage.debugMsg("daoimpl");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlMchrankskillmstSql genTlMchrankskillmstSql = new GenTlMchrankskillmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genTlMchrankskillmst.setMrskKeyid(dbActionTemplate.getSequenceNumber(GenTlMchrankskillmstSql.TBL_GEN_TL_MCHRANKSKILLMST)); // set the sequnce number 
			sqls.add(GenTlMchrankskillmstSql.getInsertSql(genTlMchrankskillmstSql.getMrskDbFields(), genTlMchrankskillmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlMchrankskillmst;
	}
	
	public GenTlMchrankskillmst update(GenTlMchrankskillmst genTlMchrankskillmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlMchrankskillmstSql genTlMchrankskillmstSql = new GenTlMchrankskillmstSql();
		try {

			sqls.add(GenTlMchrankskillmstSql.getUpdateSql(genTlMchrankskillmstSql.getMrskDbFields(), genTlMchrankskillmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlMchrankskillmst;
	}
	
	public GenTlMchrankskillmst delete(GenTlMchrankskillmst genTlMchrankskillmst)
			throws Exception, BusinessApplicationExceptions {

		List<String> sqls = new ArrayList<String>();
		GenTlMchrankskillmstSql genTlMchrankskillmstSql = new GenTlMchrankskillmstSql();
		try {
			
			sqls.add(genTlMchrankskillmstSql.getDeleteSql(genTlMchrankskillmstSql.getMrskDbFields(), genTlMchrankskillmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}
		catch(BusinessApplicationExceptions e){
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlMchrankskillmst;
	}
	
	public List<String[]> getAllMchrankskill() throws Exception {
		/*String sql="SELECT MRSK_KEYID,MRSK_NAME,MRSK_CODE,MRSK_MAXIMUMPOINTS";
		sql+=" FROM GEN_TL_MCHRANKSKILLMST";
	    CommonMessage.debugMsg("sql..."+sql);
	    List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData ;*/
		
		StringBuffer sql = new StringBuffer();
		String sql1 ="";
	    sql.append(" select '','Criteria A','A','80','100'" +
	 		" from dual union all" +
	 		" select '','Criteria B','B','60','80'" +
	 		" from dual union all"+
	 		" select '','Criteria C','C','40','60'" +
	 		" from dual ");
	sql1 = sql.toString();
	CommonMessage.debugMsg("sql..." + sql);
	List<String[]> gridData = dbActionTemplate.getDataList(sql1);
	CommonMessage.debugMsg("Grid value" + gridData.get(1));
	return gridData;
	}
	
}

