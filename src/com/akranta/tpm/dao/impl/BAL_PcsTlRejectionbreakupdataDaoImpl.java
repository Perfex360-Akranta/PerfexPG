package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PcsTlRejectionbreakupdataDao;
import com.akranta.tpm.dao.sql.BAL_PcsTlRejectionbreakupdataSql;
import com.akranta.tpm.model.BAL_PcsTlRejectionbreakupdata;
import com.akranta.tpm.model.BAL_PcsTlRejectionbreakupdata;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_PcsTlRejectionbreakupdataDaoImpl implements BAL_PcsTlRejectionbreakupdataDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_PcsTlRejectionbreakupdataDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_PcsTlRejectionbreakupdata create(BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata,List<BAL_PcsTlRejectionbreakupdata> rejectionGridList) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_PcsTlRejectionbreakupdataSql pcsTlRejectionbreakupdataSql = new BAL_PcsTlRejectionbreakupdataSql(); // contains dbtable,field names, Field types and related sqls  of master table
		List<BAL_PcsTlRejectionbreakupdata> rejectionbreakup=rejectionGridList;
		CommonFunctions.debugMsg("ListSize.."+rejectionbreakup.size());
		try{
			if(rejectionbreakup != null && rejectionbreakup.size()>0)
			{
				
				for(BAL_PcsTlRejectionbreakupdata pcstlRejectiondata: rejectionbreakup)
			
					{
					if(!UIUtils.isValidKeyId(pcstlRejectiondata.getPrbdKeyid()) || pcstlRejectiondata.getPrbdKeyid().length()<4)
					{
						pcstlRejectiondata.setPrbdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlRejectionbreakupdataSql.TBL_BAL_PCS_TL_REJECTIONBREAKUPDATA)); // set the sequnce number 
						sqls.add(BAL_PcsTlRejectionbreakupdataSql.getInsertSql(pcsTlRejectionbreakupdataSql.getPrbdDbFields(), pcstlRejectiondata.getSaveArray())); // add insert sql for master table
					}
					else
					{
						sqls.add(BAL_PcsTlRejectionbreakupdataSql.getUpdateSql(pcsTlRejectionbreakupdataSql.getPrbdDbFields(), pcstlRejectiondata.getSaveArray()));
					
					}
				}
			}
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlRejectionbreakupdata;
	}
	
	public BAL_PcsTlRejectionbreakupdata update(BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata,List<BAL_PcsTlRejectionbreakupdata> rejectionGridList) 	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_PcsTlRejectionbreakupdataSql pcsTlRejectionbreakupdataSql = new BAL_PcsTlRejectionbreakupdataSql();
		try {
				sqls.add(BAL_PcsTlRejectionbreakupdataSql.getUpdateSql(pcsTlRejectionbreakupdataSql.getPrbdDbFields(), pcsTlRejectionbreakupdata.getSaveArray()));
				dbActionTemplate.executeStatements(sqls);
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return pcsTlRejectionbreakupdata;
	}
	
	public BAL_PcsTlRejectionbreakupdata delete(BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_PcsTlRejectionbreakupdataSql pcsTlRejectionbreakupdataSql = new BAL_PcsTlRejectionbreakupdataSql();
		try {
			
			sqls.add(BAL_PcsTlRejectionbreakupdataSql.getDeleteSql(pcsTlRejectionbreakupdataSql.getPrbdDbFields(), pcsTlRejectionbreakupdata.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlRejectionbreakupdata;
	}

	@Override
	public BAL_PcsTlRejectionbreakupdata deleteRejectionbreakupdata(String keyid)
			throws BusinessApplicationExceptions, Exception {
		BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata = new BAL_PcsTlRejectionbreakupdata();
		
		List<String> sqls = new ArrayList<String>();
		sqls.add(BAL_PcsTlRejectionbreakupdataSql.DeleterejectionRow(keyid));
		dbActionTemplate.executeStatements(sqls);
	
	return pcsTlRejectionbreakupdata;
	}
	
}

