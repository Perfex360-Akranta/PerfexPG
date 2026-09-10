package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.BAL_GenTlFactorymstDao;
//import com.akranta.tpm.dao.sql.BAL_GenTlAssemblymstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFactorymstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.BAL_GenTlFactorymst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_GenTlFactorymstDaoImpl implements BAL_GenTlFactorymstDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_GenTlFactorymstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_GenTlFactorymst create(BAL_GenTlFactorymst genTlFactorymst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_GenTlFactorymstSql genTlFactorymstSql = new BAL_GenTlFactorymstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		//GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		//try{
		
			//genTlFactorymst.setFactKeyid(dbActionTemplate.getSequenceNumber(GenTlFactorymstSql.TBL_GEN_TL_FACTORYMST)); // set the sequnce number 
		    /*getSequenceNo modified by manikandan for new functional Location*/
		    genTlFactorymst.setFactKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlFactorymstSql.TBL_GEN_TL_FACTORYMST, 10, "FCT", null, null)); // set the sequnce number
		    
		    
			sqls.add(BAL_GenTlFactorymstSql.getInsertSql(genTlFactorymstSql.getFactDbFields(), genTlFactorymst.getSaveArray())); // add insert sql for master table
			
			/*GenTlFunctionallocn newGenTlFunctionallocn = genTlFactorymst.getGenTlFunctionallocn();	
			
			newGenTlFunctionallocn.setFnlnOriginalid(genTlFactorymst.getFactKeyid());
			
			
			if(CommonFunctions.isValidKeyId(genTlFactorymst.getFactLocationid())){
				newGenTlFunctionallocn.setFnlnElementid(genTlFactorymst.getFactLocationid()+"-"+genTlFactorymst.getFactKeyid());
				
			}else {
				
				newGenTlFunctionallocn.setFnlnElementid(genTlFactorymst.getFactKeyid());
			}
		
		
			sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			*/
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		/*}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}*/
		return genTlFactorymst;
	}
	
	public BAL_GenTlFactorymst update(BAL_GenTlFactorymst genTlFactorymst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlFactorymstSql genTlFactorymstSql = new BAL_GenTlFactorymstSql();
		//GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		
		sqls.add(BAL_GenTlFactorymstSql.getUpdateSql(genTlFactorymstSql.getFactDbFields(), genTlFactorymst.getSaveArray()));
		
		/*GenTlFunctionallocn newGenTlFunctionallocn = genTlFactorymst.getGenTlFunctionallocn();
		System.out.println("inside dao impl update for function " +newGenTlFunctionallocn.getFnlnOriginalid());
		//functionalLocValidations.elementExistsinFunctionalLoc(newGenTlFunctionallocn.getFnlnOriginalid());
		newGenTlFunctionallocn.setFnlnOriginalid(genTlFactorymst.getFactKeyid());
		
		
		if(CommonFunctions.isValidKeyId(genTlFactorymst.getFactLocationid())){
			newGenTlFunctionallocn.setFnlnElementid(genTlFactorymst.getFactLocationid()+"-"+genTlFactorymst.getFactKeyid());
			
		}else {
			
			newGenTlFunctionallocn.setFnlnElementid(genTlFactorymst.getFactKeyid());
		}
	
		sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
		*/
		dbActionTemplate.executeStatements(sqls);
			
		
		
		return genTlFactorymst;
	}
	
	public BAL_GenTlFactorymst delete(BAL_GenTlFactorymst genTlFactorymst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_GenTlFactorymstSql genTlFactorymstSql = new BAL_GenTlFactorymstSql();
		try {
			
			sqls.add(BAL_GenTlFactorymstSql.getDeleteSql(genTlFactorymstSql.getFactDbFields(), genTlFactorymst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlFactorymst;
	}

	
	@Override
	public List<String[]> getGenTlFactorymst(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BAL_GenTlFactorymst select(String keyid) throws  Exception 
	{
		
		BAL_GenTlFactorymst genTlFactorymst = new BAL_GenTlFactorymst();
		String sql = BAL_GenTlFactorymstSql.getFactorymstSql();
		System.out.println("in dao " );
		Object args [] = new Object [] { keyid };
		genTlFactorymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlFactorymst;
	}

	@Override
	public List<ComboBox> getGenTlFactorymstcombo(ComboFilter comboFilter) {
		// TODO Auto-generated method stub
		return null;
	}
	}
	


