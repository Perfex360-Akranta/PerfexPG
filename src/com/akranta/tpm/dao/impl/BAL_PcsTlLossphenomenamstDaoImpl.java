package com.akranta.tpm.dao.impl;
import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.dao.BAL_PcsTlLossphenomenamstDao;
import com.akranta.tpm.dao.sql.PcsTlLossphenomenamstSql;
import com.akranta.tpm.model.BAL_PcsTlLossphenomenamst;
import com.akranta.tpm.dao.sql.BAL_PcsTlLossphenfactorylinkSql;
import com.akranta.tpm.model.BAL_PcsTlLossphenomenamst;
import com.akranta.tpm.model.BAL_PcsTlLossphenfactorylink;
import com.akranta.tpm.utils.CommonFunctions;
/* dao implementation */
public class BAL_PcsTlLossphenomenamstDaoImpl implements BAL_PcsTlLossphenomenamstDao 
{
	private DBActionTemplate dbActionTemplate; 
	PcsTlLossphenomenamstSql pcsTlLossphenomenamstSql; 
	BAL_PcsTlLossphenfactorylinkSql pcsTlLossphenfactorylinkSql; 
	BAL_PcsTlLossphenfactorylink pcsTlLossphenfactorylink;
	public BAL_PcsTlLossphenomenamstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		pcsTlLossphenomenamstSql = new PcsTlLossphenomenamstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		pcsTlLossphenfactorylinkSql = new BAL_PcsTlLossphenfactorylinkSql();
		pcsTlLossphenfactorylink= new BAL_PcsTlLossphenfactorylink ();
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public BAL_PcsTlLossphenomenamst create(BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst) 	throws Exception 
	{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		try
		{
			pcsTlLossphenomenamst.setPlpmKeyid(dbActionTemplate.getSequenceNumber(PcsTlLossphenomenamstSql.TBL_PCS_TL_LOSSPHENOMENAMST)); // set the sequnce number 
			sqls.add(PcsTlLossphenomenamstSql.getInsertSql(pcsTlLossphenomenamstSql.getPlpmDbFields(), pcsTlLossphenomenamst.getSaveArray()));
			sqls.add(BAL_PcsTlLossphenfactorylinkSql.getDeleteSql(BAL_PcsTlLossphenfactorylinkSql.getPpflDbFields(),pcsTlLossphenomenamst.getPlpmKeyid()));
			popSqlsForPcsTlLossphenfactorylink(sqls, pcsTlLossphenomenamst.getpcsTlLossphenfactorylink(), pcsTlLossphenomenamst.getPlpmKeyid());
			CommonFunctions.debugMsg("sqls...."+sqls.toString());
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls	
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlLossphenomenamst;
	}
	
	public BAL_PcsTlLossphenomenamst update(BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst)	throws Exception
	{ 
		List<String> sqls = new ArrayList<String>();
		try 
		{
			sqls.add(PcsTlLossphenomenamstSql.getUpdateSql(pcsTlLossphenomenamstSql.getPlpmDbFields(), pcsTlLossphenomenamst.getSaveArray()));
			sqls.add(BAL_PcsTlLossphenfactorylinkSql.getDeleteSql(BAL_PcsTlLossphenfactorylinkSql.getPpflDbFields(),pcsTlLossphenomenamst.getPlpmKeyid()));
			popSqlsForPcsTlLossphenfactorylink(sqls, pcsTlLossphenomenamst.getpcsTlLossphenfactorylink(), pcsTlLossphenomenamst.getPlpmKeyid());
			
			
			dbActionTemplate.executeStatements(sqls);		
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return pcsTlLossphenomenamst;
	}
	
	public BAL_PcsTlLossphenomenamst delete(BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst)throws Exception
	{
		List<String> sqls = new ArrayList<String>();
		try 
		{
			sqls.add(PcsTlLossphenomenamstSql.getDeleteSql(pcsTlLossphenomenamstSql.getPlpmDbFields(), pcsTlLossphenomenamst.getSaveArray()));
			//sqls.add(PcsTlLossphenfactorylinkSql.getDeleteSql(PcsTlLossphenfactorylinkSql.getPpflDbFields(),  pcsTlLossphenomenamst.getPlpmKeyid() ));
			dbActionTemplate.executeStatements(sqls);			
		}
		catch( Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlLossphenomenamst;
	}
	
	private void popSqlsForPcsTlLossphenfactorylink(List<String>  sqls,  List <BAL_PcsTlLossphenfactorylink> pcsTlLossphenfactorylinkList,String keyId ) throws Exception
	{
		if( pcsTlLossphenfactorylinkList != null)
		{
			int index=0;
			for(BAL_PcsTlLossphenfactorylink pcsTlLossphenfactorylink : pcsTlLossphenfactorylinkList )
			{	
				pcsTlLossphenfactorylink.setPpflPlpmKeyid(keyId);
				index++;
				pcsTlLossphenfactorylink.setPpflKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlLossphenfactorylinkSql.TBL_PCS_TL_LOSSPHENFACTORYLINK));
				sqls.add(BAL_PcsTlLossphenfactorylinkSql.getInsertSql(BAL_PcsTlLossphenfactorylinkSql.getPpflDbFields(), pcsTlLossphenfactorylink.getSaveArray()));	
			}
		}
	}
	@Override
	public List<String[]> getComboTextContent(String keyId,String type) throws Exception {
		// TODO Auto-generated method stub
		String sql=pcsTlLossphenomenamstSql.getComboTextContent(keyId,type);
		return dbActionTemplate.getDataList(sql);
		
	}
}

