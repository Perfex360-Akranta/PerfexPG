package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PlmTlShutdowncalDao;
import com.akranta.tpm.dao.sql.BAL_PlmTlShutdowncalSql;
import com.akranta.tpm.model.BAL_PlmTlShutdowncal;

/* dao implementation */
public class BAL_PlmTlShutdowncalDaoImpl implements BAL_PlmTlShutdowncalDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_PlmTlShutdowncalDaoImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		this.dbActionTemplate = dbActionTemplate;
	}



	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_PlmTlShutdowncal create(BAL_PlmTlShutdowncal plmTlShutdowncal) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_PlmTlShutdowncalSql plmTlShutdowncalSql = new BAL_PlmTlShutdowncalSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			plmTlShutdowncal.setSdclKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlShutdowncalSql.TBL_PLM_TL_SHUTDOWNCAL, 12, "SDC", "MMYY", "Y")); // set the sequnce number 
			sqls.add(BAL_PlmTlShutdowncalSql.getInsertSql(plmTlShutdowncalSql.getSdclDbFields(), plmTlShutdowncal.getSaveArray())); // add insert sql for master table
			//PLM_PR_SHUTDOWNCALGEN(vSDCLKEYID, RECCOUNT OUT PARAM)
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			System.out.println("inside generate function daoImpl" + plmTlShutdowncal.getSdclKeyid());
			List<String> inParamValues = new ArrayList<String>();
						
			inParamValues.add(plmTlShutdowncal.getSdclKeyid());
			
			System.out.println("inParamValues  :"+inParamValues);
			 
			Object [] outParams = new Object[ 1 ];
			System.out.println("outParams   :"+outParams);
			dbActionTemplate.processPLSQLProcedures("PLM_PR_SHUTDOWNCALGEN",inParamValues,outParams);
			System.out.println("outParamsAfter   :"+outParams);
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return plmTlShutdowncal;
	}
	
	public BAL_PlmTlShutdowncal update(BAL_PlmTlShutdowncal plmTlShutdowncal)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_PlmTlShutdowncalSql plmTlShutdowncalSql = new BAL_PlmTlShutdowncalSql();
		try {

			sqls.add(BAL_PlmTlShutdowncalSql.getUpdateSql(plmTlShutdowncalSql.getSdclDbFields(), plmTlShutdowncal.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return plmTlShutdowncal;
	}
	
	public BAL_PlmTlShutdowncal delete(BAL_PlmTlShutdowncal plmTlShutdowncal)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_PlmTlShutdowncalSql plmTlShutdowncalSql = new BAL_PlmTlShutdowncalSql();
		try {
			
			sqls.add(BAL_PlmTlShutdowncalSql.getDeleteSql(plmTlShutdowncalSql.getSdclDbFields(), plmTlShutdowncal.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return plmTlShutdowncal;
	}
	
}

