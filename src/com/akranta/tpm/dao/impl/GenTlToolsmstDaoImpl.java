package com.akranta.tpm.dao.impl;


import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.GenTlToolsmstDao;
import com.akranta.tpm.dao.sql.GenTlToolsimgSql;
import com.akranta.tpm.dao.sql.GenTlToolsmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.GenTlToolsmst;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
/* dao implementation */
public class GenTlToolsmstDaoImpl implements GenTlToolsmstDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlToolsmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlToolsmst create(GenTlToolsmst genTlToolsmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlToolsmstSql genTlToolsmstSql = new GenTlToolsmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genTlToolsmst.setTolmKeyid(dbActionTemplate.getSequenceNumber(GenTlToolsmstSql.TBL_GEN_TL_TOOLSMST,8,"TOL",null,null)); // set the sequnce number 
			sqls.add(GenTlToolsmstSql.getInsertSql(genTlToolsmstSql.getTolmDbFields(), genTlToolsmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlToolsmst;
	}
	
	public GenTlToolsmst update(GenTlToolsmst genTlToolsmst)	throws Exception { 
		com.akranta.tpm.utils.CommonMessage.debugMsg("update in service impl");
		List<String> sqls = new ArrayList<String>();
		com.akranta.tpm.utils.CommonMessage.debugMsg("after list inivldsk update in service impl");
		GenTlToolsmstSql genTlToolsmstSql = new GenTlToolsmstSql();
		try {
			CommonMessage.debugMsg(" sqlssssss in tollsmst try catc");
			sqls.add(GenTlToolsmstSql.getUpdateSql(genTlToolsmstSql.getTolmDbFields(), genTlToolsmst.getSaveArray()));			
			CommonMessage.debugMsg(" sqls " + sqls.get(0));
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonMessage.debugMsg(" errror " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		return genTlToolsmst;
	}
	
	public GenTlToolsmst delete(GenTlToolsmst genTlToolsmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlToolsmstSql genTlToolsmstSql = new GenTlToolsmstSql();
		try {
			
			sqls.add(GenTlToolsmstSql.getDeleteSql(genTlToolsmstSql.getTolmDbFields(), genTlToolsmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlToolsmst;
	}
	
	public GenTlToolsmst select(String UtilField) throws Exception
	{
		try
		{
			GenTlToolsmst genTlToolsmst = new GenTlToolsmst();
			String sql = null;
			
			sql = GenTlToolsmstSql.selectSql();			
			CommonMessage.debugMsg("DAO SQL : "+sql);
			Object [] args =  new Object [] { UtilField };
			genTlToolsmst.setSaveArray(dbActionTemplate.getDataArr(sql,args ) );
			return  genTlToolsmst;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public GenTlToolsimg insertToolImg(GenTlToolsimg genTlToolsimg) 	throws Exception {
		
		try{
			List<String> sqls = new ArrayList<String>();
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			
			sqls.add(GenTlToolsimgSql.getDeleteSql());
			sqls.add(GenTlToolsimgSql.getInsertSqlForImg());
			
			Object [] delValue	= { genTlToolsimg.getToimKeyid() };
			int [] delTypes =  { Types.VARCHAR};
			
			java.sql.Timestamp  timeStamp = CommonFunctions.PGconvertoSqlTimeStamp(genTlToolsimg.getToimModifiedon()); 
			Object [] insValues = { genTlToolsimg.getToimKeyid(),genTlToolsimg.getToimBlobimage(),genTlToolsimg.getToimBloblength(),
								genTlToolsimg.getToimFilename(),timeStamp};
			int [] insDataType = { Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.TIMESTAMP};
			
			valueList.add(delValue);
			valueList.add(insValues);
			
			dataTypes.add(delTypes);
			dataTypes.add(insDataType);
			
			dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlToolsimg;
	}
	
	public GenTlToolsimg updateToolImg(GenTlToolsimg genTlToolsimg) 	throws Exception {
		
		try{
			String sql = GenTlToolsimgSql.getUpdateSqlForImg();
			
			java.sql.Timestamp  timeStamp = CommonFunctions.convertoSqlTimeStamp(genTlToolsimg.getToimModifiedon()); 
			Object [] values = { genTlToolsimg.getToimBlobimage(),genTlToolsimg.getToimBloblength(),
					genTlToolsimg.getToimFilename(),timeStamp,genTlToolsimg.getToimKeyid()};
			int [] dataTypes = { Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.TIMESTAMP, Types.VARCHAR};
			
			dbActionTemplate.executeStatement(sql, values, dataTypes);
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlToolsimg;
	}

	public GenTlToolsimg getToolsImage(GenTlToolsimg genTlToolsimg) throws NoDataFoundException, Exception{
		
		String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_TOOLSIMG, "TOIM_FILENAME", "TOIM_KEYID", genTlToolsimg.getToimKeyid());
		if( fileName != null ){
			if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
			
			String fileNamePath = genTlToolsimg.getToimBlobimage()+  fileName; 
			String condSql = " AND TOIM_KEYID = '" + genTlToolsimg.getToimKeyid() + "'";
			
			String imgFileName = genTlToolsimg.getToimFilename()+fileName;
			genTlToolsimg.setToimFilename(imgFileName);
			
			CommonMessage.debugMsg(" fileName " + fileNamePath);
			
		//	if(CommonFunctions.isFileExists(fileNamePath)){
				
		//	}else{
			
				dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_TOOLSIMG, "TOIM_BLOBIMAGE", condSql, fileNamePath);
		//	}	
			return 	genTlToolsimg;
		}
		return null;
	}
	
}

