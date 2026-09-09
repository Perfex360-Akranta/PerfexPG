package com.akranta.tpm.dao.impl;
import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.dao.PcsTlLossphenomenamstDao;
import com.akranta.tpm.dao.sql.PcsTlLossphenomenamstSql;
import com.akranta.tpm.model.PcsTlLossphenomenamst;
import com.akranta.tpm.dao.sql.PcsTlLossphenfactorylinkSql;
import com.akranta.tpm.model.PcsTlLossphenfactorylink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
/* dao implementation */
public class PcsTlLossphenomenamstDaoImpl implements PcsTlLossphenomenamstDao 
{
	private DBActionTemplate dbActionTemplate; 
	PcsTlLossphenomenamstSql pcsTlLossphenomenamstSql; 
	PcsTlLossphenfactorylinkSql pcsTlLossphenfactorylinkSql; 
	PcsTlLossphenfactorylink pcsTlLossphenfactorylink;
	public PcsTlLossphenomenamstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		pcsTlLossphenomenamstSql = new PcsTlLossphenomenamstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		pcsTlLossphenfactorylinkSql = new PcsTlLossphenfactorylinkSql();
		pcsTlLossphenfactorylink= new PcsTlLossphenfactorylink ();
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public PcsTlLossphenomenamst create(PcsTlLossphenomenamst pcsTlLossphenomenamst) 	throws Exception 
	{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		try
		{
			pcsTlLossphenomenamst.setPlpmKeyid(dbActionTemplate.getSequenceNumber(PcsTlLossphenomenamstSql.TBL_PCS_TL_LOSSPHENOMENAMST)); // set the sequnce number 
			sqls.add(PcsTlLossphenomenamstSql.getInsertSql(pcsTlLossphenomenamstSql.getPlpmDbFields(), pcsTlLossphenomenamst.getSaveArray()));
			//sqls.add(PcsTlLossphenfactorylinkSql.getDeleteSql(PcsTlLossphenfactorylinkSql.getPpflDbFields(),pcsTlLossphenomenamst.getPlpmKeyid()));
			popSqlsForPcsTlLossphenfactorylink(sqls, pcsTlLossphenomenamst.getpcsTlLossphenfactorylink(), pcsTlLossphenomenamst.getPlpmKeyid());
			CommonMessage.debugMsg("sqls...."+sqls.toString());
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls	
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlLossphenomenamst;
	}
	
	public PcsTlLossphenomenamst update(PcsTlLossphenomenamst pcsTlLossphenomenamst)	throws Exception
	{ 
		List<String> sqls = new ArrayList<String>();
		try 
		{
			sqls.add(PcsTlLossphenomenamstSql.getUpdateSql(pcsTlLossphenomenamstSql.getPlpmDbFields(), pcsTlLossphenomenamst.getSaveArray()));
			//sqls.add(PcsTlLossphenfactorylinkSql.getDeleteSql(PcsTlLossphenfactorylinkSql.getPpflDbFields(),pcsTlLossphenomenamst.getPlpmKeyid()));
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
	// -----------------------------Vignesh -----------------------//
//	public PcsTlLossphenomenamst delete(PcsTlLossphenomenamst pcsTlLossphenomenamst, String mstKeyid)throws Exception
//	{
//		
//		List<String> sqls = new ArrayList<String>();
//		try 
//		{
//			CommonMessage.debugMsg("inside phen master daoimpl " + mstKeyid );
//			
//			sqls.add(PcsTlLossphenomenamstSql.getDeleteSql(pcsTlLossphenomenamstSql.getPlpmDbFields(), pcsTlLossphenomenamst.getSaveArray()));
//			//sqls.add(PcsTlLossphenfactorylinkSql.getDeleteSql(PcsTlLossphenfactorylinkSql.getPpflDbFields(),  pcsTlLossphenomenamst.getPlpmKeyid() ));
//			CommonMessage.debugMsg("inside phen master daoimpl sql fields " + pcsTlLossphenomenamstSql.getPlpmDbFields() );
//			
//			CommonMessage.debugMsg("inside phen master daoimpl  array for sql " + pcsTlLossphenomenamst.getSaveArray() );
//			
//			dbActionTemplate.executeStatements(sqls);			
//		}
//		catch( Exception e)
//		{
//			throw new Exception(e.getMessage());
//		}
//		return pcsTlLossphenomenamst;
//	}
	
	// PcsTlLossphenomenamstDaoImpl.java
	@Override
	public int countLinksForPhenomena(String mstKeyid) throws Exception {
		
		 CommonMessage.debugMsg("inside countlink  daoimpl keyid  " + mstKeyid );
	    try {
	        String sql = PcsTlLossphenomenamstSql.getLinkCountSqlByPhenomena(mstKeyid);

	        // If you have a helper that returns a single scalar, prefer it:
	        // String v = dbActionTemplate.getSingleValue(sql);

	        // Using your current pattern with getDataList:
	        List<String[]> rows = dbActionTemplate.getDataList(sql);
	        if (rows != null && !rows.isEmpty() && rows.get(0) != null && rows.get(0).length > 0) {
	            String v = rows.get(0)[0];
	            return (v == null || v.trim().isEmpty()) ? 0 : Integer.parseInt(v.trim());
	        }
	        return 0;
	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	}
	
	
	
	
	
	
	// PcsTlLossphenomenamstDaoImpl.java
	public PcsTlLossphenomenamst delete(PcsTlLossphenomenamst pcsTlLossphenomenamst, String mstKeyid) throws Exception {
	   
		  CommonMessage.debugMsg("inside phen master daoimpl keyid  " + mstKeyid );
		try {
	        if (mstKeyid == null || mstKeyid.trim().isEmpty()) {
	            throw new Exception("Phenomena KeyId is required.");
	        }

	        // ✅ Guard: only delete when not linked
	        int linked = countLinksForPhenomena(mstKeyid);
	        if (linked > 0) {
	            // Let the servlet show this as an alert (you already wired that path)
	            throw new Exception("Cannot delete. This Phenomena is mapped to Functional Location(s) and must be unmapped first.");
	        }

	        List<String> sqls = new ArrayList<>();
	        CommonMessage.debugMsg("inside phen master daoimpl " + mstKeyid);

	        sqls.add(PcsTlLossphenomenamstSql.getDeleteSql(
	                pcsTlLossphenomenamstSql.getPlpmDbFields(),
	                pcsTlLossphenomenamst.getSaveArray()
	        ));

	        CommonMessage.debugMsg("inside phen master daoimpl sql fields " + pcsTlLossphenomenamstSql.getPlpmDbFields());
	        CommonMessage.debugMsg("inside phen master daoimpl array for sql " + pcsTlLossphenomenamst.getSaveArray());

	        dbActionTemplate.executeStatements(sqls);
	        return pcsTlLossphenomenamst;

	    } catch (Exception e) {
	        // bubble up so your servlet can send {tpmException: "..."} and show alert
	        throw new Exception(e.getMessage());
	    }
	}

	
	
	


	
	// -----------------------------Vignesh -----------------------//
	private void popSqlsForPcsTlLossphenfactorylink(List<String>  sqls,  List <PcsTlLossphenfactorylink> pcsTlLossphenfactorylinkList,String keyId ) throws Exception
	{
		if( pcsTlLossphenfactorylinkList != null)
		{
			int index=0;
			for(PcsTlLossphenfactorylink pcsTlLossphenfactorylink : pcsTlLossphenfactorylinkList )
			{	
				pcsTlLossphenfactorylink.setPpflPlpmKeyid(keyId);
				index++;
				pcsTlLossphenfactorylink.setPpflKeyid(dbActionTemplate.getSequenceNumber(PcsTlLossphenfactorylinkSql.TBL_PCS_TL_LOSSPHENFACTORYLINK));
				sqls.add(PcsTlLossphenfactorylinkSql.getInsertSql(PcsTlLossphenfactorylinkSql.getPpflDbFields(), pcsTlLossphenfactorylink.getSaveArray()));	
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

