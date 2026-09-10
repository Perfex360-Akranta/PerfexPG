package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.controller.UIUtils;

import java.util.List;

import com.akranta.tpm.dao.BAL_GenTlSectionmstDao;
import com.akranta.tpm.dao.sql.BAL_GenTlFactorymstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.BAL_GenTlPbumstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlSectionmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.BAL_GenTlCellmstSql.tableFldConstants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlFactorymst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.model.BAL_GenTlPbumst;
import com.akranta.tpm.model.BAL_GenTlSectionmst;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_GenTlSectionmstDaoImpl implements BAL_GenTlSectionmstDao {
	private static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  
	private FunctionalLocValidations functionalLocValidations = null;
	private DBActionTemplate dbActionTemplate;
	

	public BAL_GenTlSectionmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		functionalLocValidations = new FunctionalLocValidations(dbActionTemplate);
		
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_GenTlSectionmst create(BAL_GenTlSectionmst genTlSectionmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_GenTlSectionmstSql genTlSectionmstSql = new BAL_GenTlSectionmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		//GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		//GenTlPbumst newGenTlPbumst=new GenTlPbumst();
			
        genTlSectionmst.setSectKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlSectionmstSql.TBL_GEN_TL_SECTIONMST, 10,"LIN", "MMYY", "Y")); // set the sequnce number
	    /*GenTlFunctionallocn newGenTlFunctionallocn = genTlSectionmst.getGenTlFunctionallocn();	
		newGenTlFunctionallocn.setFnlnActive("Y");
		newGenTlFunctionallocn.setFnlnDescription(genTlSectionmst.getSectName());
		newGenTlFunctionallocn.setFnlnDisplaycode(genTlSectionmst.getSectCode());
		
	    newGenTlFunctionallocn.setFnlnElementid(genTlSectionmst.getSectKeyid());
		newGenTlFunctionallocn.setFnlnElementid(genTlSectionmst.getSectFactoryid()+"-"+genTlSectionmst.getSectKeyid());
	    newGenTlFunctionallocn.setFnlnParentid(genTlSectionmst.getSectFactoryid());
		
		newGenTlFunctionallocn.setFnlnElementtype("DMT");
		
		newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
		StringBuilder Sql=new StringBuilder();
		Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(genTlSectionmst.getSectFactoryid()).append("'");		
		
		String elementId =dbActionTemplate.getSingleValue(Sql.toString());
		
		sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
		*/

		sqls.add(BAL_GenTlSectionmstSql.getInsertSql(genTlSectionmstSql.getSectDbFields(), genTlSectionmst.getSaveArray())); // add insert sql for master table
		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return genTlSectionmst;
	}
	
	public BAL_GenTlSectionmst update(BAL_GenTlSectionmst genTlSectionmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlSectionmstSql genTlSectionmstSql = new BAL_GenTlSectionmstSql();
		//GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		sqls.add(BAL_GenTlSectionmstSql.getUpdateSql(genTlSectionmstSql.getSectDbFields(), genTlSectionmst.getSaveArray()));
		/*GenTlFunctionallocn newGenTlFunctionallocn = genTlSectionmst.getGenTlFunctionallocn();
		 if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
			{
			 CommonFunctions.debugMsg("seccccDaoImpl12");
				newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
				
			}else
				CommonFunctions.debugMsg("seccccDaoImpl3");
				
		System.out.println("inside dao impl update for function " +newGenTlFunctionallocn.getFnlnOriginalid());
		//functionalLocValidations.elementExistsinFunctionalLoc(newGenTlFunctionallocn.getFnlnOriginalid());
		String location =dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_LOCATIONMST, "LOCN_COMPANYID", "LOCN_KEYID", genTlSectionmst.getSectCompanyid());
		if(CommonFunctions.isValidKeyId(location))
			newGenTlFunctionallocn.setFnlnParentid(location +"-"+genTlSectionmst.getSectCompanyid());
		else
			newGenTlFunctionallocn.setFnlnParentid(genTlSectionmst.getSectCompanyid());
		
		sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
		*/
		dbActionTemplate.executeStatements(sqls);
	
		return genTlSectionmst;
	}
	
	public BAL_GenTlSectionmst delete(String delemode,BAL_GenTlSectionmst genTlSectionmst) throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_GenTlSectionmstSql genTlSectionmstSql = new BAL_GenTlSectionmstSql();
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql();
		String originalId= genTlSectionmst.getSectKeyid(); 
		System.out.println("inside dao impl update for function 111111111111111111111111111111111 sqls " + genTlSectionmst.getSectKeyid());
		if (!delemode.equals("I"))
		functionalLocValidations.checkOriginalIdExistsinFunctionalLoc(originalId);
		try {
			
			sqls.add(BAL_GenTlSectionmstSql.getDeleteSql(delemode,genTlSectionmstSql.getSectDbFields(), genTlSectionmst.getSaveArray()));
			 //sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));
			String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
			 CommonFunctions.debugMsg("SectiondeleteQuery          "+inactFun);
			if(inactFun.length()>0){
			  	sqls.add(  " " + " UPDATE " + TBL_GEN_TL_FUNCTIONALLOCN + " SET "+" FNLN_ACTIVE " +" = 'N'" +" where FNLN_ORIGINALID =  '"+originalId+"' ");	
				 
			}
			System.out.println("inside dao impl update for function  sqls" +sqls);
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
	public BAL_GenTlSectionmst select(String keyid) throws  Exception 
	{
		
		BAL_GenTlSectionmst genTlSectionmst = new BAL_GenTlSectionmst();
		String sql = BAL_GenTlSectionmstSql.genTlSectionmstSql();
		System.out.println("in dao " );
		Object args [] = new Object [] { keyid };
		genTlSectionmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlSectionmst;
	}

	@Override
	public BAL_GenTlSectionmst filldmtcontrol(String dMTkeyid) throws Exception {
		BAL_GenTlSectionmst newGenTlSectionmst = new BAL_GenTlSectionmst();
		//GenTlSectionmstSql genTlSectionmstSql = new GenTlSectionmstSql();
		String sql = BAL_GenTlSectionmstSql.genTlSectionmstSql();
		System.out.println(" Inside Dao Impl :: 11 "+sql);
		Object args [] = new Object [] {dMTkeyid};
		newGenTlSectionmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		System.out.println(" Inside Dao Impl :: 22 "+newGenTlSectionmst.getSectKeyid());
		System.out.println(" Inside Dao Impl :: 22 "+newGenTlSectionmst.getSectActive());
		return newGenTlSectionmst;
	}
	
}

