package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.dao.GenTlCellmstDao;
import com.akranta.tpm.dao.sql.GenTlCellmstSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.TableNames;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlCellmst;
import com.akranta.tpm.model.GenTlFunctionallocn;
//import com.akranta.tpm.model.GenTlSectionmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class GenTlCellmstDaoImpl implements GenTlCellmstDao {
	private static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  
	private FunctionalLocValidations functionalLocValidations = null;
	private DBActionTemplate dbActionTemplate; 
	String fnlkeyid;

	public GenTlCellmstDaoImpl(DBActionTemplate dbActionTemplate ) 
	{
		this.dbActionTemplate = dbActionTemplate;
		functionalLocValidations = new FunctionalLocValidations(dbActionTemplate);
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlCellmst create(GenTlCellmst genTlCellmst) 	throws Exception {

		
	List<String> sqls = new ArrayList<String>(); 
		List<String> sqlsnew = new ArrayList<String>();
		/* sqls for execution */ 
		GenTlCellmstSql genTlCellmstSql = new GenTlCellmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		
		try{
			
			
			//GenTlCellmstSql.TBL_GEN_TL_CELLMST, 10, "CEL", "MMYY", "Y"
			genTlCellmst.setCellKeyid(dbActionTemplate.getSequenceNumber(GenTlCellmstSql.TBL_GEN_TL_CELLMST, 10, "CEL", "", "Y")); // set the sequnce number 
			
			
			//GenTlFunctionallocn newGenTlFunctionallocn = newGenTlSbumst.getGenTlFunctionallocn();
			
			GenTlFunctionallocn newGenTlFunctionallocn = genTlCellmst.getGenTlFunctionallocn();
			
			CommonMessage.debugMsg(newGenTlFunctionallocn);
			
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12, "FNLN", "", " ")); // set the sequnce number
		//	genTlCellmst.setPbutFlid(newGenTlFunctionallocn.getFnlnKeyid());
			fnlkeyid=newGenTlFunctionallocn.getFnlnKeyid();
			
			CommonMessage.debugMsg("fnlkeyid:::"+fnlkeyid);
			
			
			CommonMessage.debugMsg("inside the pbu create method FLKEYID"+newGenTlFunctionallocn.getFnlnKeyid());
			StringBuilder Sql=new StringBuilder();
			
			Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(genTlCellmst.getCellSectionid()).append("'");		
			CommonMessage.debugMsg("FFFFFFFF     "+Sql );
			String elementId =dbActionTemplate.getSingleValue(Sql.toString());
			CommonMessage.debugMsg("ELEMENT ID:-"+elementId);
			
			newGenTlFunctionallocn.setFnlnActive("Y");
			
			String cellname=genTlCellmst.getCellName();
			 String cellcode=genTlCellmst.getCellCode();
			 CommonMessage.debugMsg("SBUNAME:"+cellname+"SBUCODE:"+cellcode);
			 String cellnameupp=cellname.toUpperCase();
			 String cellcodeupp=cellcode.toUpperCase();
			 CommonMessage.debugMsg("SBUNAME:"+cellnameupp+"SBUCODE:"+cellcodeupp);
			 genTlCellmst.setCellName(cellnameupp);
			 genTlCellmst.setCellCode(cellcodeupp);
			CommonMessage.debugMsg("PBUT NAME:::::"+genTlCellmst.getCellName());
			CommonMessage.debugMsg("PBUT CODE::::"+genTlCellmst.getCellCode());
			newGenTlFunctionallocn.setFnlnDescription(cellnameupp);
			newGenTlFunctionallocn.setFnlnDisplaycode(cellcodeupp);
			
			//newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCompany()+"-"+genTlCellmst.getLocation()+"-"+genTlCellmst.getCellSectionid()+"-"+genTlCellmst.getCellKeyid());
			
			newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getLocation()+"-"+genTlCellmst.getSbu()+"-"+genTlCellmst.getCellFactoryid()+"-"+genTlCellmst.getCellSectionid()+"-"+genTlCellmst.getCellKeyid());
			
			
			newGenTlFunctionallocn.setFnlnOriginalid(genTlCellmst.getCellKeyid());
			
			//newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCompany()+"-"+genTlCellmst.getLocation()+"-"+genTlCellmst.getPbutSbuid());//+"-"+genTlCellmst.getCellSectionid()
			newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getLocation()+"-"+genTlCellmst.getSbu()+"-"+genTlCellmst.getCellFactoryid()+"-"+genTlCellmst.getCellSectionid() );
			
			newGenTlFunctionallocn.setFnlnElementtype("C");
			
			//genTlCellmst.setCellFlid(elementId);   //tttttt
			
			genTlCellmst.setCellFlid(fnlkeyid);
			//newGenTlFunctionallocn.setFnlnKeyid(elementId);
			CommonMessage.debugMsg("genTlCellmst.getCellFlid(:::"+genTlCellmst.getCellFlid());
			
			CommonMessage.debugMsg("inside the dao impl insert before");
			sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
    		CommonMessage.debugMsg("inside the dao impl insert after");
			sqls.add(GenTlCellmstSql.getInsertSql(genTlCellmstSql.getCellDbFields(), genTlCellmst.getSaveArray())); // add insert sql for master table
			
			dbActionTemplate.executeStatements(sqls);// execute the block of sqls
			CommonMessage.debugMsg("BEFORE CALLING PROCEDURE");
			List<String> paramValues = new ArrayList<String>();
					Object[] outParam    = null;
			dbActionTemplate.processPLSQLProcedures("REFRESH_MV",paramValues,outParam);
					CommonMessage.debugMsg("procedure returned");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		}
		return genTlCellmst;
	}
	@Override
	public GenTlCellmst update(GenTlCellmst genTlCellmst) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>();
		GenTlCellmstSql genTlCellmstSql = new GenTlCellmstSql();
	GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		try {
			String cellname=genTlCellmst.getCellName();
			 String cellcode=genTlCellmst.getCellCode();
			 CommonMessage.debugMsg("SBUNAME:"+cellname+"SBUCODE:"+cellcode);
			 String cellnameupp=cellname.toUpperCase();
			 String cellcodeupp=cellcode.toUpperCase();
			 CommonMessage.debugMsg("SBUNAME:"+cellnameupp+"SBUCODE:"+cellcodeupp);
			 genTlCellmst.setCellName(cellnameupp);
			 genTlCellmst.setCellCode(cellcodeupp);
			CommonMessage.debugMsg("PBUT NAME:::::"+genTlCellmst.getCellName());
			CommonMessage.debugMsg("PBUT CODE::::"+genTlCellmst.getCellCode());
			
			sqls.add(GenTlCellmstSql.getUpdateSql(genTlCellmstSql.getCellDbFields(), genTlCellmst.getSaveArray()));
		GenTlFunctionallocn newGenTlFunctionallocn = genTlCellmst.getGenTlFunctionallocn();
		
		newGenTlFunctionallocn.getFnlnKeyid();
			
			/* if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
				{
					CommonMessage.debugMsg("insideLoop");
					newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
					
				}else
					CommonMessage.debugMsg("outSideLoop");
			   
			 CommonFunctions.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid());   //ttttt
				
				if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid())){
					//newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getCellSectionid()+"-"+genTlCellmst.getCellKeyid());
					newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCellKeyid());
					
				}
		        else {
		        	newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getLocation()+"-"+genTlCellmst.getSbu()+"-"+genTlCellmst.getCellFactoryid()+"-"+genTlCellmst.getCellSectionid()+"-"+genTlCellmst.getCellKeyid());
					
					
				}
				//newGenTlFunctionallocn.setFnlnOriginalid(genTlCellmst.getCellKeyid());
*/				
				//newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCellCompanyid() +"-"+genTlCellmst.getCellSectionid());
		newGenTlFunctionallocn.setFnlnElementid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getLocation()+"-"+genTlCellmst.getSbu()+"-"+genTlCellmst.getCellFactoryid()+"-"+genTlCellmst.getCellSectionid()+"-"+genTlCellmst.getCellKeyid());
		String fnlnkeyid=genTlCellmst.getCellFlid();
		CommonMessage.debugMsg("fnlnkeyid::::"+fnlnkeyid);
		newGenTlFunctionallocn.setFnlnKeyid(fnlnkeyid);
		newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCellCompanyid()+"-"+genTlCellmst.getLocation()+"-"+genTlCellmst.getSbu()+"-"+genTlCellmst.getCellFactoryid()+"-"+genTlCellmst.getCellSectionid() );
				newGenTlFunctionallocn.setFnlnDescription(cellnameupp);
				newGenTlFunctionallocn.setFnlnDisplaycode(cellcodeupp);
				sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
		
				dbActionTemplate.executeStatements(sqls);
		CommonMessage.debugMsg("BEFORE CALLING PROCEDURE");
				List<String> paramValues = new ArrayList<String>();
						Object[] outParam    = null;
				dbActionTemplate.processPLSQLProcedures("REFRESH_MV",paramValues,outParam);
						CommonMessage.debugMsg("procedure returned");
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return genTlCellmst;
		}
	public GenTlCellmst delete(String delemode,GenTlCellmst genTlCellmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlCellmstSql genTlCellmstSql = new GenTlCellmstSql();
		String originalId= genTlCellmst.getCellKeyid(); 
		CommonMessage.debugMsg("inside dao impl update for function 111111111111111111111111111111111 sqls " + genTlCellmst.getCellKeyid());
		if (!delemode.equals("I"))
		functionalLocValidations.checkOriginalIdExistsinFunctionalLoc(originalId);
		try {
			
			sqls.add(GenTlCellmstSql.getDeleteSql(delemode,genTlCellmstSql.getCellDbFields(), genTlCellmst.getSaveArray()));
			
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
	public GenTlCellmst select(String keyid) throws Exception {
		GenTlCellmst genTlCellmst = new GenTlCellmst();
		String sql = GenTlCellmstSql.getGenTlCellmstSql();
		
		Object args [] = new Object [] { keyid };
		genTlCellmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlCellmst;
	}
	
	public String getCompany(GenTlCellmst genTlCellmst) throws SQLException
	{
		
		return dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FUNCTIONALLOCN, "FNLN_PARENTID", "FNLN_ORIGINALID", genTlCellmst.getCellFactoryid());
		
	}
	
	public String getLocation(GenTlCellmst genTlCellmst) throws SQLException
	{
		
		return dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FUNCTIONALLOCN, "FNLN_PARENTID", "FNLN_ORIGINALID", genTlCellmst.getCellFactoryid());
		
	}

	@Override
	public List<String[]> getGenTlCellmst(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenTlCellmst fillcellcontrol(String cellkeyid) throws Exception{
		GenTlCellmst genTlCellmst = new GenTlCellmst();
		//GenTlCellmstSql genTlCellmstSql = new GenTlCellmstSql();
		String sql = GenTlCellmstSql.getGenTlCellmstSql();
		CommonMessage.debugMsg(" Inside Dao Impl :: 11 "+sql);
		Object args [] = new Object [] {cellkeyid};
		genTlCellmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	    CommonMessage.debugMsg(" Inside Dao Impl :: 22 "+genTlCellmst.getCellKeyid());
	    CommonMessage.debugMsg(" Inside Dao Impl :: 22 "+genTlCellmst.getCellActive());
	    CommonMessage.debugMsg(" Inside Dao Impl :: 22 "+genTlCellmst.getCellFlid());
		return genTlCellmst;
	}



	

	}
	


