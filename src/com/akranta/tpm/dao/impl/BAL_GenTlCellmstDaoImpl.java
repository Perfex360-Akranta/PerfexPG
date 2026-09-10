package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.dao.BAL_GenTlCellmstDao;
import com.akranta.tpm.dao.sql.BAL_GenTlCellmstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.BAL_GenTlSectionmstSql;
import com.akranta.tpm.dao.sql.TableNames;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlCellmst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.model.BAL_GenTlSectionmst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.controller.UIUtils;

/* dao implementation */
public class BAL_GenTlCellmstDaoImpl implements BAL_GenTlCellmstDao {
	private static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  
	private FunctionalLocValidations functionalLocValidations = null;
	private DBActionTemplate dbActionTemplate; 

	public BAL_GenTlCellmstDaoImpl(DBActionTemplate dbActionTemplate ) 
	{
		this.dbActionTemplate = dbActionTemplate;
		functionalLocValidations = new FunctionalLocValidations(dbActionTemplate);
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_GenTlCellmst create(BAL_GenTlCellmst genTlCellmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>();  
		BAL_GenTlCellmstSql genTlCellmstSql = new BAL_GenTlCellmstSql(); 
		//GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
	    genTlCellmst.setCellKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlCellmstSql.TBL_GEN_TL_CELLMST, 10, "CEL", "MMYY", "Y")); // set the sequnce number
		/*GenTlFunctionallocn newGenTlFunctionallocn = genTlCellmst.getGenTlFunctionallocn();	
		newGenTlFunctionallocn.setFnlnActive("Y");
		newGenTlFunctionallocn.setFnlnDescription(genTlCellmst.getCellName());
		newGenTlFunctionallocn.setFnlnDisplaycode(genTlCellmst.getCellCode());
		
		newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getCellFactoryid()+"-"+genTlCellmst.getCellSectionid()+"-"+genTlCellmst.getCellKeyid());
		
		newGenTlFunctionallocn.setFnlnOriginalid(genTlCellmst.getCellKeyid());
		
		newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCellCompanyid() +"-"+genTlCellmst.getCellSectionid());
		newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCellFactoryid()+"-"+genTlCellmst.getCellSectionid());
		newGenTlFunctionallocn.setFnlnElementtype("JH");
		newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
		
		StringBuilder Sql=new StringBuilder();   //tttt
		Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(genTlCellmst.getCellSectionid()).append("'");		
	    String elementId =dbActionTemplate.getSingleValue(Sql.toString());
		sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
		*/
		
		sqls.add(BAL_GenTlCellmstSql.getInsertSql(genTlCellmstSql.getCellDbFields(), genTlCellmst.getSaveArray()));
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	
		return genTlCellmst;
	}
	
	public BAL_GenTlCellmst update(BAL_GenTlCellmst genTlCellmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlCellmstSql genTlCellmstSql = new BAL_GenTlCellmstSql();
		//GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		try {

			sqls.add(BAL_GenTlCellmstSql.getUpdateSql(genTlCellmstSql.getCellDbFields(), genTlCellmst.getSaveArray()));
			/*GenTlFunctionallocn newGenTlFunctionallocn = genTlCellmst.getGenTlFunctionallocn();
			
			 if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
				{
					CommonFunctions.debugMsg("insideLoop");
					newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
					
				}else
					CommonFunctions.debugMsg("outSideLoop");
			   
			 CommonFunctions.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid());   //ttttt
				
				if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid())){
					newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getCellSectionid()+"-"+genTlCellmst.getCellKeyid());
					
				}
		        else {
					
					newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCellKeyid());
				}
				newGenTlFunctionallocn.setFnlnOriginalid(genTlCellmst.getCellKeyid());
				
				newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCellCompanyid() +"-"+genTlCellmst.getCellSectionid());
				
				sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			*/
				dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlCellmst;
	}
	
	public BAL_GenTlCellmst delete(String delemode,BAL_GenTlCellmst genTlCellmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_GenTlCellmstSql genTlCellmstSql = new BAL_GenTlCellmstSql();
		String originalId= genTlCellmst.getCellKeyid(); 
		System.out.println("inside dao impl update for function 111111111111111111111111111111111 sqls " + genTlCellmst.getCellKeyid());
		if (!delemode.equals("I"))
		functionalLocValidations.checkOriginalIdExistsinFunctionalLoc(originalId);
		try {
			
			sqls.add(BAL_GenTlCellmstSql.getDeleteSql(delemode,genTlCellmstSql.getCellDbFields(), genTlCellmst.getSaveArray()));
			String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
			 
			if(inactFun.length()>0){
			  	sqls.add(  " " + " UPDATE " + TBL_GEN_TL_FUNCTIONALLOCN + " SET "+" FNLN_ACTIVE " +" = 'N'" +" where FNLN_ORIGINALID =  '"+originalId+"' ");	
				 
			}
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlCellmst;
	}

	@Override
	public BAL_GenTlCellmst select(String keyid) throws Exception {
		BAL_GenTlCellmst genTlCellmst = new BAL_GenTlCellmst();
		String sql = BAL_GenTlCellmstSql.getGenTlCellmstSql();
		
		Object args [] = new Object [] { keyid };
		genTlCellmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlCellmst;
	}
	
	public String getCompany(BAL_GenTlCellmst genTlCellmst) throws SQLException
	{
		
		return dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FUNCTIONALLOCN, "FNLN_PARENTID", "FNLN_ORIGINALID", genTlCellmst.getCellFactoryid());
		
	}

	@Override
	public List<String[]> getGenTlCellmst(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BAL_GenTlCellmst fillcellcontrol(String cellkeyid) throws Exception{
		BAL_GenTlCellmst genTlCellmst = new BAL_GenTlCellmst();
		//GenTlCellmstSql genTlCellmstSql = new GenTlCellmstSql();
		String sql = BAL_GenTlCellmstSql.getGenTlCellmstSql();
		System.out.println(" Inside Dao Impl :: 11 "+sql);
		Object args [] = new Object [] {cellkeyid};
		genTlCellmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	    CommonFunctions.debugMsg(" Inside Dao Impl :: 22 "+genTlCellmst.getCellKeyid());
	    CommonFunctions.debugMsg(" Inside Dao Impl :: 22 "+genTlCellmst.getCellActive());
	    CommonFunctions.debugMsg(" Inside Dao Impl :: 22 "+genTlCellmst.getCellFlid());
		return genTlCellmst;
	}
	
}

