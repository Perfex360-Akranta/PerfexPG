package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.controller.UIUtils;

import java.util.List;

import com.akranta.tpm.dao.GenTlSectionmstDao;
import com.akranta.tpm.dao.sql.GenTlFactorymstSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.GenTlPbumstSql;
import com.akranta.tpm.dao.sql.GenTlSectionmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.GenTlCellmstSql.tableFldConstants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlPbumst;
import com.akranta.tpm.model.GenTlSectionmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class GenTlSectionmstDaoImpl implements GenTlSectionmstDao {
	private static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  
	private FunctionalLocValidations functionalLocValidations = null;
	private DBActionTemplate dbActionTemplate;
	

	public GenTlSectionmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		functionalLocValidations = new FunctionalLocValidations(dbActionTemplate);
		
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlSectionmst create(GenTlSectionmst genTlSectionmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlSectionmstSql genTlSectionmstSql = new GenTlSectionmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		GenTlPbumst newGenTlPbumst=new GenTlPbumst();
		
		try
		{
			
	        genTlSectionmst.setSectKeyid(dbActionTemplate.getSequenceNumber(GenTlSectionmstSql.TBL_GEN_TL_SECTIONMST, 10,"LIN", "MMYY", "Y")); // set the sequnce number
		    GenTlFunctionallocn newGenTlFunctionallocn = genTlSectionmst.getGenTlFunctionallocn();	
		    String sectname=genTlSectionmst.getSectName();
			 String sectcode=genTlSectionmst.getSectCode();
			 CommonMessage.debugMsg("SBUNAME:"+sectname+"SBUCODE:"+sectcode);
			 String sectnameupp=sectname.toUpperCase();
			 String sectcodeupp=sectcode.toUpperCase();
			 CommonMessage.debugMsg("SBUNAME:"+sectnameupp+"SBUCODE:"+sectcodeupp);
			 genTlSectionmst.setSectName(sectnameupp);
			 genTlSectionmst.setSectCode(sectcodeupp);
		    
		    newGenTlFunctionallocn.setFnlnOriginalid(genTlSectionmst.getSectKeyid());
		    newGenTlFunctionallocn.setFnlnActive("Y");
			newGenTlFunctionallocn.setFnlnDescription(sectnameupp);
			newGenTlFunctionallocn.setFnlnDisplaycode(sectcodeupp);
			
		   // newGenTlFunctionallocn.setFnlnElementid(genTlSectionmst.getSectKeyid());
			newGenTlFunctionallocn.setFnlnElementid(genTlSectionmst.getSectCompanyid()+"-"+genTlSectionmst.getLocation()+"-"+genTlSectionmst.getSbu()+"-"+genTlSectionmst.getSectFactoryid()+"-"+genTlSectionmst.getSectKeyid());
		    newGenTlFunctionallocn.setFnlnParentid(genTlSectionmst.getSectCompanyid()+"-"+genTlSectionmst.getLocation()+"-"+genTlSectionmst.getSbu()+"-"+genTlSectionmst.getSectFactoryid());
			
			newGenTlFunctionallocn.setFnlnElementtype("L");
			
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
			StringBuilder Sql=new StringBuilder();
			Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(genTlSectionmst.getSectFactoryid()).append("'");		
			
			String elementId =dbActionTemplate.getSingleValue(Sql.toString());
			CommonMessage.debugMsg("ELEMENTID"+elementId);
			
			sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			genTlSectionmst.setSectFlid(elementId);   //tttt
			sqls.add(GenTlSectionmstSql.getInsertSql(genTlSectionmstSql.getSectDbFields(), genTlSectionmst.getSaveArray())); // add insert sql for master table
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			CommonMessage.debugMsg("BEFORE CALLING PROCEDURE");
			List<String> paramValues = new ArrayList<String>();
					Object[] outParam    = null;
			dbActionTemplate.processPLSQLProcedures("REFRESH_MV",paramValues,outParam);
					CommonMessage.debugMsg("procedure returned");
	
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlSectionmst;
	}
	
	public GenTlSectionmst update(GenTlSectionmst genTlSectionmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlSectionmstSql genTlSectionmstSql = new GenTlSectionmstSql();
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		String sectname=genTlSectionmst.getSectName();
		 String sectcode=genTlSectionmst.getSectCode();
		 CommonMessage.debugMsg("SBUNAME:"+sectname+"SBUCODE:"+sectcode);
		 String sectnameupp=sectname.toUpperCase();
		 String sectcodeupp=sectcode.toUpperCase();
		 CommonMessage.debugMsg("SBUNAME:"+sectnameupp+"SBUCODE:"+sectcodeupp);
		 genTlSectionmst.setSectName(sectnameupp);
		 genTlSectionmst.setSectCode(sectcodeupp);
		sqls.add(GenTlSectionmstSql.getUpdateSql(genTlSectionmstSql.getSectDbFields(), genTlSectionmst.getSaveArray()));
	GenTlFunctionallocn newGenTlFunctionallocn = genTlSectionmst.getGenTlFunctionallocn();
	/*if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
			{
			 CommonMessage.debugMsg("seccccDaoImpl12");
				newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
				
			}else
				CommonMessage.debugMsg("seccccDaoImpl3");
				
		CommonMessage.debugMsg("inside dao impl update for function " +newGenTlFunctionallocn.getFnlnOriginalid());
		//functionalLocValidations.elementExistsinFunctionalLoc(newGenTlFunctionallocn.getFnlnOriginalid());
		String location =dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_LOCATIONMST, "LOCN_COMPANYID", "LOCN_KEYID", genTlSectionmst.getSectCompanyid());
		if(CommonFunctions.isValidKeyId(location))
			newGenTlFunctionallocn.setFnlnParentid(location +"-"+genTlSectionmst.getSectCompanyid());
		else
			newGenTlFunctionallocn.setFnlnParentid(genTlSectionmst.getSectCompanyid());*/
	 /*if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
		{
			CommonMessage.debugMsg("insideLoop");
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
			
		}else
			CommonMessage.debugMsg("outSideLoop");
	   
	 CommonFunctions.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid());   //ttttt
		
		if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid())){
			newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getCellSectionid()+"-"+genTlCellmst.getCellKeyid());
			newGenTlFunctionallocn.setFnlnElementid(genTlSectionmst.getSectKeyid());
			
		}
     else {
    	 newGenTlFunctionallocn.setFnlnElementid(genTlSectionmst.getSectCompanyid()+"-"+genTlSectionmst.getLocation()+"-"+genTlSectionmst.getSbu()+"-"+genTlSectionmst.getSectFactoryid()+"-"+genTlSectionmst.getSectKeyid());
		    	
			
		}
		newGenTlFunctionallocn.setFnlnOriginalid(genTlSectionmst.getSectKeyid());*/
		
		//newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCellCompanyid() +"-"+genTlCellmst.getCellSectionid());
		//newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getLocation()+"-"+genTlCellmst.getSbu()+"-"+genTlCellmst.getCellFactoryid()+"-"+genTlCellmst.getCellSectionid() );
		CommonMessage.debugMsg("genTlSectionmst.getSectCompanyid()"+genTlSectionmst.getSectCompanyid());
		genTlSectionmst.getSectFlid();
		CommonMessage.debugMsg("genTlSectionmst.getSectFlid();"+genTlSectionmst.getSectFlid());
		newGenTlFunctionallocn.setFnlnKeyid(genTlSectionmst.getSectFlid());
		newGenTlFunctionallocn.setFnlnElementtype("L"); 
		 newGenTlFunctionallocn.setFnlnElementid(genTlSectionmst.getSectCompanyid()+"-"+genTlSectionmst.getLocation()+"-"+genTlSectionmst.getSbu()+"-"+genTlSectionmst.getSectFactoryid()+"-"+genTlSectionmst.getSectKeyid());
			
		newGenTlFunctionallocn.setFnlnParentid(genTlSectionmst.getSectCompanyid()+"-"+genTlSectionmst.getLocation()+"-"+genTlSectionmst.getSbu()+"-"+genTlSectionmst.getSectFactoryid());
		newGenTlFunctionallocn.setFnlnDescription(sectnameupp);
		newGenTlFunctionallocn.setFnlnDisplaycode(sectcodeupp);
		
		sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));

		dbActionTemplate.executeStatements(sqls);
	CommonMessage.debugMsg("BEFORE CALLING PROCEDURE");
		List<String> paramValues = new ArrayList<String>();
				Object[] outParam    = null;
		dbActionTemplate.processPLSQLProcedures("REFRESH_MV",paramValues,outParam);
				CommonMessage.debugMsg("procedure returned");

		return genTlSectionmst;
	}
	
	public GenTlSectionmst delete(String delemode,GenTlSectionmst genTlSectionmst) throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlSectionmstSql genTlSectionmstSql = new GenTlSectionmstSql();
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		String originalId= genTlSectionmst.getSectKeyid(); 
		CommonMessage.debugMsg("inside dao impl update for function 111111111111111111111111111111111 sqls " + genTlSectionmst.getSectKeyid());
		if (!delemode.equals("I"))
		functionalLocValidations.checkOriginalIdExistsinFunctionalLoc(originalId);
		try {
			
			sqls.add(GenTlSectionmstSql.getDeleteSql(delemode,genTlSectionmstSql.getSectDbFields(), genTlSectionmst.getSaveArray()));
			 //sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));
			String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
			 CommonMessage.debugMsg("SectiondeleteQuery          "+inactFun);
			if(inactFun.length()>0){
			  	sqls.add(  " " + " UPDATE " + TBL_GEN_TL_FUNCTIONALLOCN + " SET "+" FNLN_ACTIVE " +" = 'N'" +" where FNLN_ORIGINALID =  '"+originalId+"' ");	
				 
			}
			CommonMessage.debugMsg("inside dao impl update for function  sqls" +sqls);
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlSectionmst;
	}

	@Override
	public List<String[]> getGenTlSectionmst(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}
	public GenTlSectionmst select(String keyid) throws  Exception 
	{
		
		GenTlSectionmst genTlSectionmst = new GenTlSectionmst();
		String sql = GenTlSectionmstSql.genTlSectionmstSql();
		CommonMessage.debugMsg("in dao " );
		Object args [] = new Object [] { keyid };
		genTlSectionmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlSectionmst;
	}

	@Override
	public GenTlSectionmst filldmtcontrol(String dMTkeyid) throws Exception {
		GenTlSectionmst newGenTlSectionmst = new GenTlSectionmst();
		//GenTlSectionmstSql genTlSectionmstSql = new GenTlSectionmstSql();
		String sql = GenTlSectionmstSql.genTlSectionmstSql();
		CommonMessage.debugMsg(" Inside Dao Impl :: 11 "+sql);
		Object args [] = new Object [] {dMTkeyid};
		newGenTlSectionmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonMessage.debugMsg(" Inside Dao Impl :: 22 "+newGenTlSectionmst.getSectKeyid());
		CommonMessage.debugMsg(" Inside Dao Impl :: 22 "+newGenTlSectionmst.getSectActive());
		return newGenTlSectionmst;
	}
	
}

