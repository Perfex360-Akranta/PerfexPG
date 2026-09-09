package com.akranta.tpm.dao.impl;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlLocationmstDao;
import com.akranta.tpm.dao.sql.GenTlCellmstSql;
import com.akranta.tpm.dao.sql.GenTlFactorymstSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.GenTlLocationmstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlLocationmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class GenTlLocationmstDaoImpl implements GenTlLocationmstDao {
	private static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  


	private DBActionTemplate dbActionTemplate; 

	public GenTlLocationmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlLocationmst create(GenTlLocationmst genTlLocationmst) 	throws Exception {
		CommonMessage.debugMsg("DaoImpl");

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlLocationmstSql genTlLocationmstSql = new GenTlLocationmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		try{
		    genTlLocationmst.setLocnKeyid(dbActionTemplate.getSequenceNumber(GenTlLocationmstSql.TBL_GEN_TL_LOCATIONMST, 10, "LCN", "", "")); // set the sequnce number
			

			GenTlFunctionallocn newGenTlFunctionallocn = genTlLocationmst.getGenTlFunctionallocn();	
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
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
			sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			StringBuilder Sql=new StringBuilder();
			Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(genTlLocationmst.getLocnCompanyid()).append("'");		
			String elementId =dbActionTemplate.getSingleValue(Sql.toString());
		
			genTlLocationmst.setLocnFlid(elementId);
			sqls.add(GenTlLocationmstSql.getInsertSql(genTlLocationmstSql.getLocnDbFields(), genTlLocationmst.getSaveArray())); 
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlLocationmst;
	}
	
	public GenTlLocationmst update(GenTlLocationmst genTlLocationmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlLocationmstSql genTlLocationmstSql = new GenTlLocationmstSql();
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		StringBuilder Sql=new StringBuilder();
		try {
			
		
			sqls.add(GenTlLocationmstSql.getUpdateSql(genTlLocationmstSql.getLocnDbFields(), genTlLocationmst.getSaveArray()));
		   
			GenTlFunctionallocn newGenTlFunctionallocn = genTlLocationmst.getGenTlFunctionallocn();
			if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
			{
				CommonMessage.debugMsg("location1");
				newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
				
			}
			
			else
				CommonMessage.debugMsg("location12");	
			sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlLocationmst;
	}
	
	public GenTlLocationmst delete(GenTlLocationmst genTlLocationmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlLocationmstSql genTlLocationmstSql = new GenTlLocationmstSql();
		//GenTlCellmstSql genTlCellmstSql = new GenTlCellmstSql();
		String originalId= genTlLocationmst.getLocnKeyid(); 
		try {
			
			sqls.add(GenTlLocationmstSql.getDeleteSql(genTlLocationmstSql.getLocnDbFields(), genTlLocationmst.getSaveArray()));
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
	public GenTlLocationmst select(String keyid) throws  Exception {
		GenTlLocationmst genTlLocationmst = new GenTlLocationmst();
		String sql = GenTlLocationmstSql.getLocationmstSql();
		CommonMessage.debugMsg("in dao " );
		Object args [] = new Object [] { keyid };
		CommonMessage.debugMsg("FlidDaoimpl    "+genTlLocationmst.getLocnFlid());
		CommonMessage.debugMsg(genTlLocationmst.getLocnCode());
		genTlLocationmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlLocationmst;
	}

	@Override
	public List<String[]> getGenTlLocationmst(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

