package com.akranta.tpm.dao.impl;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_GenTlLocationmstDao;
import com.akranta.tpm.dao.sql.BAL_GenTlCellmstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFactorymstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.BAL_GenTlLocationmstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.model.BAL_GenTlLocationmst;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_GenTlLocationmstDaoImpl implements BAL_GenTlLocationmstDao {
	private static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  


	private DBActionTemplate dbActionTemplate; 

	public BAL_GenTlLocationmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_GenTlLocationmst create(BAL_GenTlLocationmst genTlLocationmst) 	throws Exception {
		CommonFunctions.debugMsg("DaoImpl");

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_GenTlLocationmstSql genTlLocationmstSql = new BAL_GenTlLocationmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql(); 
		try{
		    genTlLocationmst.setLocnKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlLocationmstSql.TBL_GEN_TL_LOCATIONMST, 10, "LCN", "", "")); // set the sequnce number
			

			BAL_GenTlFunctionallocn newGenTlFunctionallocn = genTlLocationmst.getGenTlFunctionallocn();	
			newGenTlFunctionallocn.setFnlnActive("Y");
			newGenTlFunctionallocn.setFnlnDescription(genTlLocationmst.getLocnName());
			newGenTlFunctionallocn.setFnlnDisplaycode(genTlLocationmst.getLocnCode());
			if(genTlLocationmst.getLocnCompanyid()!=null){
				newGenTlFunctionallocn.setFnlnElementid(genTlLocationmst.getLocnCompanyid()+"-"+genTlLocationmst.getLocnKeyid());
				
			}else {
				
				newGenTlFunctionallocn.setFnlnElementid(genTlLocationmst.getLocnKeyid());
				
			}
			
			newGenTlFunctionallocn.setFnlnOriginalid(genTlLocationmst.getLocnKeyid());
			
			newGenTlFunctionallocn.setFnlnParentid(genTlLocationmst.getLocnCompanyid());
			newGenTlFunctionallocn.setFnlnElementtype("LCN");
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
			sqls.add(BAL_GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			StringBuilder Sql=new StringBuilder();
			Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(genTlLocationmst.getLocnCompanyid()).append("'");		
			String elementId =dbActionTemplate.getSingleValue(Sql.toString());
		
			genTlLocationmst.setLocnFlid(elementId);
			sqls.add(BAL_GenTlLocationmstSql.getInsertSql(genTlLocationmstSql.getLocnDbFields(), genTlLocationmst.getSaveArray())); 
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlLocationmst;
	}
	
	public BAL_GenTlLocationmst update(BAL_GenTlLocationmst genTlLocationmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlLocationmstSql genTlLocationmstSql = new BAL_GenTlLocationmstSql();
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql(); 
		StringBuilder Sql=new StringBuilder();
		try {
			
		
			sqls.add(BAL_GenTlLocationmstSql.getUpdateSql(genTlLocationmstSql.getLocnDbFields(), genTlLocationmst.getSaveArray()));
		   
			BAL_GenTlFunctionallocn newGenTlFunctionallocn = genTlLocationmst.getGenTlFunctionallocn();
			if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
			{
				CommonFunctions.debugMsg("location1");
				newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
				
			}
			
			else
				CommonFunctions.debugMsg("location12");	
			sqls.add(BAL_GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlLocationmst;
	}
	
	public BAL_GenTlLocationmst delete(BAL_GenTlLocationmst genTlLocationmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_GenTlLocationmstSql genTlLocationmstSql = new BAL_GenTlLocationmstSql();
		//GenTlCellmstSql genTlCellmstSql = new GenTlCellmstSql();
		String originalId= genTlLocationmst.getLocnKeyid(); 
		try {
			
			sqls.add(BAL_GenTlLocationmstSql.getDeleteSql(genTlLocationmstSql.getLocnDbFields(), genTlLocationmst.getSaveArray()));
			/*String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
			 
			if(inactFun.length()>0){
			  	sqls.add(  " " + " UPDATE " + TBL_GEN_TL_FUNCTIONALLOCN + " SET "+" FNLN_ACTIVE " +" = 'N'" +" where FNLN_ORIGINALID =  '"+originalId+"' ");	
				 
			}*/
			

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlLocationmst;
	}

	@Override
	public BAL_GenTlLocationmst select(String keyid) throws  Exception {
		BAL_GenTlLocationmst genTlLocationmst = new BAL_GenTlLocationmst();
		String sql = BAL_GenTlLocationmstSql.getLocationmstSql();
		System.out.println("in dao " );
		Object args [] = new Object [] { keyid };
		CommonFunctions.debugMsg("FlidDaoimpl    "+genTlLocationmst.getLocnFlid());
		CommonFunctions.debugMsg(genTlLocationmst.getLocnCode());
		genTlLocationmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlLocationmst;
	}

	@Override
	public List<String[]> getGenTlLocationmst(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

