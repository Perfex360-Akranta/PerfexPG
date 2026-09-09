package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlFnlnrolemapDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFnlnrolemapSql;
import com.akranta.tpm.dao.sql.GenTlFnlnroleteamSql;
import com.akranta.tpm.dao.sql.GenTlTeamtradelinkSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlFnlnrolemap;
import com.akranta.tpm.model.GenTlFnlnroleteam;
import com.akranta.tpm.model.GenTlTeamtradelink;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
/* dao implementation */
public class GenTlFnlnrolemapDaoImpl implements GenTlFnlnrolemapDao {

	private DBActionTemplate dbActionTemplate; 
	private FunctionCallApi  fnCallApi;
	public GenTlFnlnrolemapDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void GenTlFnlnrolemapDaoImplJwt(String JwtToken) 
	{
		try{
			//momServiceApi = new MomServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlFnlnrolemap create(GenTlFnlnrolemap genTlFnlnrolemap) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlFnlnrolemapSql genTlFnlnrolemapSql = new GenTlFnlnrolemapSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genTlFnlnrolemap.setFrlKeyid(dbActionTemplate.getSequenceNumber(GenTlFnlnrolemapSql.TBL_GEN_TL_FNLNROLEMAP, 10, "FRM", "","")); // set the sequnce number 
			sqls.add(GenTlFnlnrolemapSql.getInsertSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray())); // add insert sql for master table
					
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlFnlnrolemap;
	}
	
	public GenTlFnlnrolemap update(GenTlFnlnrolemap genTlFnlnrolemap)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlFnlnrolemapSql genTlFnlnrolemapSql = new GenTlFnlnrolemapSql();
		try {

			sqls.add(GenTlFnlnrolemapSql.getUpdateSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlFnlnrolemap;
	}
	
	public GenTlFnlnrolemap delete(GenTlFnlnrolemap genTlFnlnrolemap)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlFnlnrolemapSql genTlFnlnrolemapSql = new GenTlFnlnrolemapSql();
		try {
			sqls.add(genTlFnlnrolemapSql.getDeleteTradeSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray()));
			sqls.add(genTlFnlnrolemapSql.getDeleteTeamSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray()));
			sqls.add(genTlFnlnrolemapSql.getDeleteSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlFnlnrolemap;
	}

	@Override
	public List<String[]> getRoleMappingGrid(CommonFilter commonFilter)
			throws Exception {
		/*StringBuffer sql = new StringBuffer();
		
		CommonMessage.debugMsg("");		
		
		sql.append(" select FRL_KEYID keyid,'0' selectv,'0' checkv,ROLE_KEYID,ROLE_NAME,FRL_NOOFPERSONS ");
		sql.append(" from adm_tl_rolemst,gen_tl_fnlnrolemap where ROLE_ACTIVE='Y' ");
		sql.append(" and  FRL_FNLN_KEYID(+)='"+commonFilter.getFlid()+"' and FRL_ROLE_KEYID(+)=ROLE_KEYID ");
		sql.append(" order by ROLE_NAME ");
		
		//if (CommonFunctions.isValidKeyId(commonFilter.getFlid())){}
		CommonMessage.debugMsg("getRoleMappingGridsql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		CommonMessage.debugMsg("getRoleMappingGrid value" + gridData.get(1));
		return gridData;*/	
		
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms="FlId="+commonFilter.getFlid()+";";
		}
		
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_PC_MASTERS.GEN_FN_ROLEMAPLIST", paramValues);
	}
	
	
	// --------------------- Vignesh 24Oct2025 ----------------------------------------------------------//
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FunctionalLocn> getAllfnLocation(FunctionalLocn functionalLocn) throws Exception {
	    try {
	        String elemType = functionalLocn.getElementType(); // assume non-null as in your code
	        StringBuilder sql = new StringBuilder();

	        CommonMessage.debugMsg("functionalLocn.getElementtype() " + functionalLocn.getElementType());
	        CommonMessage.debugMsg("functionalLocn.getParentNumber() " + functionalLocn.getParentNumber());
	        CommonMessage.debugMsg("functionalLocn.getParentId() " + functionalLocn.getParentId());

	        // ---- ANSI JOIN version (Postgres-friendly) ----
	        sql.append("SELECT ")
	           .append("  f.fnln_originalid,")
	           .append("  f.fnln_elementid,")
	           .append("  f.fnln_parentid,")
	           .append("  f.fnln_elementtype,")
	           .append("  ld.displaycode,")
	           .append("  f.fnln_keyid,")
	           .append("  v.locn_keyid ")
	           .append("FROM GEN_TL_FUNCTIONALLOCN f ")
	           // was: , (SELECT LOCN_KEYID, FNLN_ORIGINALID ORIGINALIDVW FROM GEN_VW_FNLN LOC)
	           // and FNLN_ORIGINALID = ORIGINALIDVW  (inner join)
	           .append("JOIN (SELECT locn_keyid, fnln_originalid AS originalidvw ")
	           .append("      FROM GEN_VW_FNLN loc) v ")
	           .append("  ON v.originalidvw = f.fnln_originalid ")
	           // was: fnln_originalid = originalid(+)  (left join to keep f-rows even if no layout row)
	           .append("LEFT JOIN FTL_VW_LAYOUTDISPLAYCODE ld ")
	           .append("  ON ld.originalid = f.fnln_originalid ")
	           .append("WHERE 1=1 ");

	        // ---- Your dynamic filters preserved verbatim (just prefixed with alias f.) ----
	        if ("FL".equals(elemType)) {
	            sql.append(" AND f.fnln_elementtype = 'CMP' ");
	        }
	        if ("CMP".equals(elemType)) {
	            sql.append(" AND f.fnln_elementtype = 'LCN' ");
	            sql.append(" AND f.fnln_parentid LIKE '").append(functionalLocn.getParentId()).append("' ");
	            sql.append(" AND f.fnln_elementtype <> '").append(elemType).append("' ");
	        }
	        if ("LCN".equals(elemType)) {
	            // sql.append(" AND f.fnln_elementtype = 'F' ");
	            sql.append(" AND f.fnln_parentid LIKE '").append(functionalLocn.getParentNumber()).append("' ");
	            sql.append(" AND f.fnln_elementtype <> '").append(elemType).append("' ");
	        }
	        if ("F".equals(elemType)) {
	            sql.append(" AND f.fnln_elementtype = 'SBU' ");
	            sql.append(" AND f.fnln_parentid LIKE '").append(functionalLocn.getParentNumber()).append("%' ");
	            sql.append(" AND f.fnln_elementtype <> '").append(elemType).append("' ");
	        }
	        if ("SBU".equals(elemType)) {
	            sql.append(" AND f.fnln_elementtype = 'PBU' ");
	            sql.append(" AND f.fnln_parentid LIKE '").append(functionalLocn.getParentNumber()).append("%' ");
	            sql.append(" AND f.fnln_elementtype <> '").append(elemType).append("' ");
	        }
	        if ("PBU".equals(elemType)) {
	            sql.append(" AND f.fnln_elementtype = 'L' ");
	            sql.append(" AND f.fnln_parentid LIKE '").append(functionalLocn.getParentNumber()).append("%' ");
	            sql.append(" AND f.fnln_elementtype <> '").append(elemType).append("' ");
	        }
	        if ("L".equals(elemType)) {
	            sql.append(" AND f.fnln_elementtype = 'C' ");
	            sql.append(" AND f.fnln_parentid LIKE '").append(functionalLocn.getParentNumber()).append("%' ");
	            sql.append(" AND f.fnln_elementtype <> '").append(elemType).append("' ");
	        }
	        if ("C".equals(elemType)) {
	            sql.append(" AND f.fnln_elementtype = 'M' ");
	            sql.append(" AND f.fnln_parentid LIKE '").append(functionalLocn.getParentNumber()).append("%' ");
	            sql.append(" AND f.fnln_elementtype <> '").append(elemType).append("' ");
	        }
	        if ("M".equals(elemType)) {
	            sql.append(" AND f.fnln_elementtype = 'A' ");
	            sql.append(" AND f.fnln_parentid LIKE '").append(functionalLocn.getParentNumber()).append("%' ");
	            sql.append(" AND f.fnln_elementtype <> '").append(elemType).append("' ");
	        }

	        sql.append(" AND f.fnln_active = 'Y' ");

	        CommonMessage.debugMsg(" sql " + sql);
	        List resultList = dbActionTemplate.getDataList(sql.toString());
	        CommonMessage.debugMsg(" resultList size " + resultList.size());
	        return fillLocation(resultList);
	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	}
	
		
//	@SuppressWarnings("unchecked")
//	@Override
//	public  List<FunctionalLocn> getAllfnLocation(FunctionalLocn functionalLocn) throws Exception
//	{
//		try
//		{	String sql = "";
//		
//		   CommonMessage.debugMsg("functionalLocn.getElementtype()"+ functionalLocn.getElementType());
//		   CommonMessage.debugMsg("functionalLocn.getParentNumber()"+ functionalLocn.getParentNumber());
//		   CommonMessage.debugMsg("functionalLocn.getParentId()"+ functionalLocn.getParentId());
//			
//			sql = "select  fnln_originalid,fnln_elementid,fnln_parentid,fnln_elementtype,displaycode,FNLN_KEYID,LOCN_KEYID ";
//		    sql += " from GEN_TL_FUNCTIONALLOCN,FTL_VW_LAYOUTDISPLAYCODE,(SELECT LOCN_KEYID,FNLN_ORIGINALID ORIGINALIDVW FROM GEN_VW_FNLN LOC)" +
//		    		" where fnln_originalid = originalid(+) AND FNLN_ORIGINALID=ORIGINALIDVW ";		
//		
//		        
//		    
//		    if( functionalLocn.getElementType().equals("FL"))
//		    {				
//		    	sql +=	"and fnln_elementtype = 'CMP'";
//		    }
//		    if( functionalLocn.getElementType().equals("CMP"))
//		    {
//		    	sql +=	"and fnln_elementtype ='LCN'";
//		    	sql +=	"and fnln_parentid like '"+ functionalLocn.getParentId() +"' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
//		    }
//		    if( functionalLocn.getElementType().equals("LCN"))
//		    {
//		    	//sql +=	"and fnln_elementtype = 'F'";
//		    	sql +=	"and fnln_parentid like '"+ functionalLocn.getParentNumber()+"' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
//		    }
//		    if( functionalLocn.getElementType().equals("F"))
//		    {
//		    	sql +=	"and fnln_elementtype = 'SBU'";
//		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentNumber() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
//		    }
//		    if( functionalLocn.getElementType().equals("SBU"))
//		    {
//		    	sql +=	"and fnln_elementtype = 'PBU'";
//		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentNumber() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
//		    }
//		    if( functionalLocn.getElementType().equals("PBU"))
//		    {
//		    	sql +=	"and fnln_elementtype = 'L'";
//		    	sql +=	"and fnln_parentid like'"+functionalLocn.getParentNumber() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
//		    }
//		   
//		    if( functionalLocn.getElementType().equals("L"))
//		    {
//		    	sql +=	"and fnln_elementtype = 'C'";
//		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentNumber() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
//		    }	
//		    
//		    if( functionalLocn.getElementType().equals("C"))
//		    {
//		    	sql +=	"and fnln_elementtype = 'M'";
//		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentNumber() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
//		    }	
//		    if( functionalLocn.getElementType().equals("M"))
//		    {
//		    	sql +=	"and fnln_elementtype = 'A'";
//		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentNumber() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
//		    }	
//			
//				sql +=" and fnln_active='Y'";
//			
//			
//			CommonMessage.debugMsg(" sql " + sql);
//			List resultList = dbActionTemplate.getDataList(sql);
//			CommonMessage.debugMsg(" resultList size " + resultList.size());
//			return fillLocation(resultList);			
//		}
//		catch (Exception e)
//		{			
//			throw new Exception(e.getMessage()); 			
//		}
//		
//	}
//	
	// --------------------- Vignesh 24Oct2025 ----------------------------------------------------------//
	private List<FunctionalLocn> fillLocation(List<String []> resultList) throws SQLException
	{
	
		   List<FunctionalLocn> menus = new ArrayList<FunctionalLocn>();
		   for( String [] row : resultList )
		   {
			   FunctionalLocn fl = new FunctionalLocn();
			   fl.setOriginalId(row[0]);
			   fl.setElementId(row[1]);
			   fl.setParentId(row[2]);
			   fl.setElementType(row[3]);
			   fl.setDisplayCode(row[4]);	
			   fl.setTempParentId(row[5]);	
			   fl.setCellOrder(row[6]);	
			   menus.add(fl);			   
		   }
  		  return menus;
	 }

	@Override
	public List<GenTlFnlnrolemap> create(
			List<GenTlFnlnrolemap> genTlFnlnrolemapList) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlFnlnrolemapSql genTlFnlnrolemapSql = new GenTlFnlnrolemapSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFnlnrolemap genTlFnlnrolemap=new GenTlFnlnrolemap();
		try{
			for(int i=0 ;i<=genTlFnlnrolemapList.size()-1;i++){				
				genTlFnlnrolemap=genTlFnlnrolemapList.get(i);
				if (!CommonFunctions.isValidKeyId(genTlFnlnrolemap.getFrlKeyid())){
					genTlFnlnrolemap.setFrlKeyid(dbActionTemplate.getSequenceNumber(GenTlFnlnrolemapSql.TBL_GEN_TL_FNLNROLEMAP, 10, "FRM", "","")); // set the sequnce number // set the sequnce number
					sqls.add(GenTlFnlnrolemapSql.getInsertSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray()));
				}
				else{
					sqls.add(GenTlFnlnrolemapSql.getUpdateSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray())); 
				}
				sqls.add(GenTlFnlnrolemapSql.getUpdateRoleMstSql( genTlFnlnrolemap.getSaveArray()));
				//dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlFnlnrolemapList;
	}

	@Override
	public List<String[]> getRoleTeamCntrlGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms="FlId="+commonFilter.getFlid()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_PC_MASTERS.GEN_FN_ROLEMAPCTRLS", paramValues);
	}

	@Override
	public List<GenTlFnlnrolemap> delete(
			List<GenTlFnlnrolemap> genTlFnlnrolemapList) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlFnlnrolemapSql genTlFnlnrolemapSql = new GenTlFnlnrolemapSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFnlnrolemap genTlFnlnrolemap=new GenTlFnlnrolemap();
		try{
			for(int i=0 ;i<=genTlFnlnrolemapList.size()-1;i++){				
				genTlFnlnrolemap=genTlFnlnrolemapList.get(i);
				if (CommonFunctions.isValidKeyId(genTlFnlnrolemap.getFrlKeyid())){
					sqls.add(genTlFnlnrolemapSql.getDeleteTradeSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray()));
					sqls.add(genTlFnlnrolemapSql.getDeleteTeamSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray()));
					sqls.add(GenTlFnlnrolemapSql.getDeleteSql(genTlFnlnrolemapSql.getFrlDbFields(), genTlFnlnrolemap.getSaveArray())); // add insert sql for master table
					dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				}		
			}
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlFnlnrolemapList;
	}

	@Override
	public List<GenTlFnlnroleteam> createTeam(List<GenTlFnlnroleteam> genTlFnlnroleteamList) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlFnlnroleteamSql genTlFnlnroleteamSql = new GenTlFnlnroleteamSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFnlnroleteam genTlFnlnroleteam=new GenTlFnlnroleteam();
		try{
			for(int i=0 ;i<=genTlFnlnroleteamList.size()-1;i++){				
				genTlFnlnroleteam=genTlFnlnroleteamList.get(i);
				if (!CommonFunctions.isValidKeyId(genTlFnlnroleteam.getFrtKeyid())){
					genTlFnlnroleteam.setFrtKeyid(dbActionTemplate.getSequenceNumber(GenTlFnlnroleteamSql.TBL_GEN_TL_FNLNROLETEAM, 10, "FRM", "","")); // set the sequnce number // set the sequnce number
					sqls.add(genTlFnlnroleteamSql.getInsertSql(genTlFnlnroleteamSql.getFrlDbFields(), genTlFnlnroleteam.getSaveArray())); // add insert sql for master table					
				}
				else{	
					sqls.add(genTlFnlnroleteamSql.getDeleteTradeSql(genTlFnlnroleteamSql.getFrlDbFields(), genTlFnlnroleteam.getSaveArray())); // add insert sql for master table
					sqls.add(genTlFnlnroleteamSql.getUpdateSql(genTlFnlnroleteamSql.getFrlDbFields(), genTlFnlnroleteam.getSaveArray())); // add insert sql for master table
				}				
				String LocationId=dbActionTemplate.getSingleValue("select LOCN_KEYID from gen_vw_fnln where fnln_keyid='"+genTlFnlnroleteam.getFrtFnlnKeyid()+"'");
				String SbuId=dbActionTemplate.getSingleValue("select SBUT_KEYID from gen_vw_fnln where fnln_keyid='"+genTlFnlnroleteam.getFrtFnlnKeyid()+"'");
				CommonMessage.debugMsg(" LocationId:" + LocationId+",SbuId="+SbuId);
				if((UIUtils.isValidKeyId(LocationId)) && (UIUtils.isValidKeyId(SbuId))){
					sqls.add(genTlFnlnroleteamSql.getUpdateEmployeeMstSql(genTlFnlnroleteam.getFrtEmpmKeyid(),LocationId,SbuId)); // add insert sql for master table
				}
				sqls=getGenTlTeamtradelinkSql(genTlFnlnroleteam,sqls);
			}			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
			//procedure for skill index update
			List<String> paramValuesSkill = new ArrayList<>();//PROCEDURE CHANGES
			paramValuesSkill.add(genTlFnlnroleteamList.get(0).getFrtFnlnKeyid());//PROCEDURE CHANGES
			dbActionTemplate.processPLSQLProceduresNew("PC_SYNC_SKILL_INDEX_COUNT_DEBUG",paramValuesSkill , null);//PROCEDURE CHANGES
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlFnlnroleteamList;
	}
	
	public List<String> getGenTlTeamtradelinkSql(GenTlFnlnroleteam genTlFnlnroleteam,List<String> sqls) throws Exception {
		// TODO Auto-generated method stub		
		GenTlTeamtradelinkSql genTlTeamtradelinkSql = new GenTlTeamtradelinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlTeamtradelink genTlTeamtradelink=new GenTlTeamtradelink();
		try{
			if(genTlFnlnroleteam.getTeamtradelink()!=null){
				List<GenTlTeamtradelink> genTlTeamtradelinkList=genTlFnlnroleteam.getTeamtradelink();
				CommonMessage.debugMsg("start Of  fillValues genTlFnlnrolemapList:"+ genTlTeamtradelinkList.size());
				
				for(int i=0 ;i<=genTlTeamtradelinkList.size()-1;i++){				
					genTlTeamtradelink=genTlTeamtradelinkList.get(i);
					genTlTeamtradelink.setFrpFrtKeyid(genTlFnlnroleteam.getFrtKeyid());
					if (!CommonFunctions.isValidKeyId(genTlTeamtradelink.getFrpKeyid())){				
						genTlTeamtradelink.setFrpKeyid(dbActionTemplate.getSequenceNumber(GenTlTeamtradelinkSql.TBL_GEN_TL_TEAMTRADELINK, 10, "FRP", "","")); // set the sequnce number // set the sequnce number
						sqls.add(genTlTeamtradelinkSql.getInsertSql(genTlTeamtradelinkSql.getFrpDbFields(), genTlTeamtradelink.getSaveArray())); // add insert sql for master table				
					}
					else{
						sqls.add(genTlTeamtradelinkSql.getUpdateSql(genTlTeamtradelinkSql.getFrpDbFields(), genTlTeamtradelink.getSaveArray())); // add insert sql for master table					
					}
				}
			}
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return sqls;
	}

	@Override
	public List<GenTlFnlnroleteam> deleteTeam(
			List<GenTlFnlnroleteam> genTlFnlnroleteamList) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlFnlnroleteamSql genTlFnlnroleteamSql = new GenTlFnlnroleteamSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFnlnroleteam genTlFnlnroleteam=new GenTlFnlnroleteam();
		try{
			for(int i=0 ;i<=genTlFnlnroleteamList.size()-1;i++){				
				genTlFnlnroleteam=genTlFnlnroleteamList.get(i);
				if (CommonFunctions.isValidKeyId(genTlFnlnroleteam.getFrtKeyid())){
					sqls.add(genTlFnlnroleteamSql.getDeleteTradeSql(genTlFnlnroleteamSql.getFrlDbFields(), genTlFnlnroleteam.getSaveArray())); // add insert sql for master table
					sqls.add(genTlFnlnroleteamSql.getDeleteSql(genTlFnlnroleteamSql.getFrlDbFields(), genTlFnlnroleteam.getSaveArray())); // add insert sql for master table
					dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				}
			}
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlFnlnroleteamList;
	}
	
	@Override
	public GenTlFnlnroleteam deleteTeam(GenTlFnlnroleteam genTlFnlnroleteam)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sf = new StringBuffer();
		
		CommonMessage.debugMsg("FrtKeyid::"+genTlFnlnroleteam.getFrtKeyid());
		CommonMessage.debugMsg("EmpID"+genTlFnlnroleteam.getFrtEmpmKeyid());
		
		String EmpKeyid=dbActionTemplate.getSingleValue("SELECT FRT_EMPM_KEYID FROM GEN_TL_FNLNROLETEAM WHERE FRT_KEYID IN ('"+genTlFnlnroleteam.getFrtKeyid()+"' )");
		CommonMessage.debugMsg("empid"+EmpKeyid);
		
		String FnlnKeyId =dbActionTemplate.getSingleValue("SELECT FRT_FNLN_KEYID FROM GEN_TL_FNLNROLETEAM WHERE FRT_KEYID IN ('"+genTlFnlnroleteam.getFrtKeyid()+"' )");//PROCEDURE CHANGES
		
		sf.append(" SELECT ROLE_NAME FROM GEN_TL_EMPLOYEEMST, GEN_TL_ROLEMST WHERE 1=1 AND ROLE_KEYID (+)=EMPM_ROLEID   " );
		sf.append(" AND EMPM_KEYID IN (select FRT_EMPM_KEYID from GEN_TL_FNLNROLETEAM where FRT_KEYID IN ('"+genTlFnlnroleteam.getFrtKeyid()+"') )" );
		
	//	String uniquePosMap = dbActionTemplate.getSingleValue(sf.toString());
		
	//	if(UIUtils.isValidKeyId(uniquePosMap) && uniquePosMap.length()>3) 
			//throw new Exception("Employee is Mapped with " + uniquePosMap + " Unique Position. Remove from unique position and Try.");
		//	throw new Exception("Employee is Mapped with " + uniquePosMap + " Unique Position. Remove from unique position and Try.");
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlFnlnroleteamSql genTlFnlnroleteamSql = new GenTlFnlnroleteamSql(); // contains dbtable,field names, Field types and related sqls  of master table
		sqls.add(genTlFnlnroleteamSql.getDeleteTradeSql(genTlFnlnroleteamSql.getFrlDbFields(), genTlFnlnroleteam.getSaveArray())); // add insert sql for master table
		sqls.add(genTlFnlnroleteamSql.getDeleteSql(genTlFnlnroleteamSql.getFrlDbFields(), genTlFnlnroleteam.getSaveArray())); // add insert sql for master table
		sqls.add(genTlFnlnroleteamSql.RemoveUniquePosition(EmpKeyid));
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
		List<String> paramValuesSkill = new ArrayList<>();
		paramValuesSkill.add(FnlnKeyId);//PROCEDURE CHANGES
		dbActionTemplate.processPLSQLProceduresNew("PC_SYNC_SKILL_INDEX_COUNT_DEBUG",paramValuesSkill , null);//PROCEDURE CHANGES
		
		return genTlFnlnroleteam;
	}

	@Override
	public List<String[]> getRoleTeamGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms="FlId="+commonFilter.getFlid()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="RoleId="+commonFilter.getType()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_PC_MASTERS.GEN_FN_ROLETEAMMULEMPLIST", paramValues);
	}
	
	@Override
	public List<String[]> getRoleTeamEmpGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms="FlId="+commonFilter.getFlid()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="RoleId="+commonFilter.getType()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="EmpmId="+commonFilter.getKey()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_PC_MASTERS.GEN_FN_ROLEEMPLIST", paramValues);
	}

	@Override
	public List<String[]> getRoleMappingMainGrid(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms="FlId="+commonFilter.getFlid()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="RoleId="+commonFilter.getType()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_PC_MASTERS.GEN_FN_ROLEMAPFNLNLIST", paramValues);
	}
	
	@Override
	public List<String[]> getRoleTeamMainGrid(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms="FlId="+commonFilter.getFlid()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="RoleId="+commonFilter.getType()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_PC_MASTERS.GEN_FN_ROLETEAMFNLNLIST", paramValues);
	}

	@Override
	public String SelectOriginalId(String flId) throws Exception {
		// TODO Auto-generated method stub
		String Sql=" select parentid from GEN_VW_FUNCLOCN where "
		 +" originalid in (select FNLN_ORIGINALID from GEN_VW_FNLN where FNLN_KEYID='"+flId+"') ";		
		String OriginalId="";
		CommonMessage.debugMsg("Sql"+Sql);
		OriginalId=dbActionTemplate.getSingleValue(Sql);
		CommonMessage.debugMsg("OriginalId"+OriginalId);
		return OriginalId;
	}

	@Override
	public String SelectFnlnName(String flId) throws Exception {
		// TODO Auto-generated method stub
		String Sql=" select DISPLAYCODE from GEN_VW_FUNCLOCN where "
		 +" originalid in (select FNLN_ORIGINALID from GEN_VW_FNLN where FNLN_KEYID='"+flId+"') ";		
		String OriginalId="";
		CommonMessage.debugMsg("Sql"+Sql);
		OriginalId=dbActionTemplate.getSingleValue(Sql);
		CommonMessage.debugMsg("DISPLAYCODE"+OriginalId);
		return OriginalId;
	}

	@Override
	public String getTradeType(String trade) throws Exception {
		// TODO Auto-generated method stub
		String Sql="SELECT TRDM_CLASSIFICATION FROM GEN_TL_TRADEMST WHERE TRDM_KEYID='"+trade+"'";		
		String type="";
		CommonMessage.debugMsg("Sql"+Sql);
		type=dbActionTemplate.getSingleValue(Sql);
		CommonMessage.debugMsg("type"+type);
		return type;
	}

	@Override
	public String getSubProcessId(String subSubProcessId) throws Exception {
		// TODO Auto-generated method stub
		String Sql="SELECT SBSP_SUBPROCESSID FROM QTM_TL_SUBSUBPROCESSMST WHERE SBSP_KEYID='"+subSubProcessId+"'";		
		String subProcessId="";
		CommonMessage.debugMsg("Sql"+Sql);
		subProcessId=dbActionTemplate.getSingleValue(Sql);
		CommonMessage.debugMsg("subProcessId"+subProcessId);
		return subProcessId;
	}

	@Override
	public String getProcessId(String subProcessId) throws Exception {
		// TODO Auto-generated method stub
		String Sql="SELECT SUBP_PROCESSID FROM QTM_TL_SUBPROCESSMST WHERE SUBP_KEYID='"+subProcessId+"'";		
		String processId="";
		CommonMessage.debugMsg("Sql"+Sql);
		processId=dbActionTemplate.getSingleValue(Sql);
		CommonMessage.debugMsg("processId"+processId);
		return processId;
	}
	@Override
	public List<String[]> getRoleTeamList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = RoleTeamRelatedParams(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_FN_ROLETEAMLIST", paramValues);
	}
	
	@Override
	public List<String[]> getRoleAllList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = RoleTeamRelatedParams(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_FN_ROLEALLLIST", paramValues);
	}
	@Override
	public List<String[]> getEmpTeamList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = RoleTeamRelatedParams(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> dataList = dbActionTemplate.processFunctionCalls("GEN_FN_EMPTEAMLIST", paramValues);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt....."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
	}
	
	@Override
	public List<String[]> getEmpAllList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = RoleTeamRelatedParams(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//return dbActionTemplate.processFunctionCalls("GEN_PC_MASTERS.GEN_FN_EMPALLLIST", paramValues);
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_EMPALLLIST", paramValues);
		CommonMessage.debugMsg("After " +commonFilter.getViewClick());
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt....."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
	}

	@Override
	public List<String[]> getRoleTeamAllMainGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = RoleTeamRelatedParams(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_FN_ROLETEAMALLFNLNLIST", paramValues);
	}
	
	private String RoleTeamRelatedParams(CommonFilter commonFilter){
		String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter);
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="LEVEL="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getKK())){
			condParms+="ROLEMAPID="+commonFilter.getKK()+";";
		}
		/*if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms+="FLID="+commonFilter.getFlid()+";";
		}*/
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="ROLEID="+commonFilter.getType()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getFactoryId())){
			condParms+="LOCATION="+commonFilter.getFactoryId()+";";
		}
		return condParms;
	}

	@Override
	public Workbook getRoleTeamAllExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getRoleTeamAllResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getRoleTeamAllResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = RoleTeamRelatedParams(commonFilter);		
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		return   dbActionTemplate.dbFunctionCall("GEN_FN_ROLETEAMALLFNLNLIST", paramValues);
	}

	@Override
	public String SelectLocation(String flId) throws Exception {
		
		String Sql=" select LOCN_KEYID from GEN_VW_FNLN where FNLN_KEYID='"+flId+"' ";		
			String locationId="";
			CommonMessage.debugMsg("Sql"+Sql);
			locationId=dbActionTemplate.getSingleValue(Sql);
			CommonMessage.debugMsg("locationId"+locationId);
			return locationId;
	}
	public List<String[]>  getlevelrole(String flId) throws Exception {
		
			CommonMessage.debugMsg("+ Character.toChars(39) +"+ Character.toChars(39));
		    String Sql=" select FLID, " + " LEVEL "  + " ,LOCN_KEYID From GEN_MV_FLIDHIERARCHY,GEN_VW_FNLN WHERE 1=1 AND FNLN_KEYID = FLID ";
		    if (CommonFunctions.isValidKeyId(flId))
		    {
		    	Sql = Sql + " AND FLID = '" + flId +"' ";
		    }
		    
		    CommonMessage.debugMsg("reloadAllGrids sql" + Sql);
			return dbActionTemplate.getDataList(Sql);
	}
    
//-------------------------------------***********************
	@Override
	public List<String[]> getEmployeeGridData(CommonFilter commonFilter)
			throws Exception {
		try
		{
			
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			CommonMessage.debugMsg("Inside daoimpl");
			
			String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			if(CommonFunctions.isValidKeyId(commonFilter.getActionKeyId())){
				condParms+="ACTIVEYN="+commonFilter.getActionKeyId()+";";
			}
			
			//sriram16
			if(commonFilter.getEmpwiseType() != null && CommonFunctions.isValidKeyId(commonFilter.getEmpwiseType())){
			    condParms+="CATEGORY="+commonFilter.getEmpwiseType()+";";
			}//sriram16
			
			paramValues.add(condParms);
		
			paramValues.add(commonParams);
		
			
			
						
			//CommonMessage.debugMsg("ParamValues:"+paramValues);
			
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_EMPLOYEE_ROLE_VIEW", paramValues);
			//List<String[]> dataList =   dbActionTemplate.processFunctionCalls("GEN_ROLEVIEW_TEST.GEN_FN_EMPLOYEE_ROLE_VIEW", paramValues);
			CommonMessage.debugMsg("Inside daoimpl 5: "+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}			}
			CommonMessage.debugMsg("Test --->" +dataList.size());
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

@Override
public Workbook getEmployeeRoleLocationExportExcel(CommonFilter commonFilter,
		JSONObject tblJSONObj, String format) throws Exception {
	 List<String> paramValues = new ArrayList<String>();				
	 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
	 String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	
	 if(CommonFunctions.isValidKeyId(commonFilter.getActionKeyId())){
			condParms+="ACTIVEYN="+commonFilter.getActionKeyId()+";";
		}
	//sriram16
		if(commonFilter.getEmpwiseType() != null && CommonFunctions.isValidKeyId(commonFilter.getEmpwiseType())){
		    condParms+="CATEGORY="+commonFilter.getEmpwiseType()+";";
		}//sriram16
	 paramValues.add(condParms);	 
	 paramValues.add(commonParams);
	 ResultSet rs = null;
	 try{
	 	rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_EMPLOYEE_ROLE_VIEW", paramValues);
	 	
	 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		return excelUtils.writeToExcel(rs,format,0,1,0 );
	 		  
	 }finally{
		   if( rs != null)
	 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		     
	 }
}
/*
 * Transaction Summary Data
 */
@Override
public List<String[]> getTransactionSummaryGridData(CommonFilter commonFilter)
		throws Exception {
	try
	{
		
		CommonMessage.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();	
		CommonMessage.debugMsg("Inside daoimpl");
		
		String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		
		paramValues.add(condParms);
		
		paramValues.add(commonParams);
		CommonMessage.debugMsg("Inside daoimpl 4");
					
		List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_TRANS_SUMMARY_REPORT_PG", paramValues);
		
		CommonMessage.debugMsg("Inside daoimpl 5: "+dataList.size());
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}			}
		CommonMessage.debugMsg("Test --->" +dataList.size());
		return dataList; 
		
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
	}
}

	/*
	 * Transaction summary Export excel
	 */
public Workbook getTransactionSummaryGridDataExportExcel(CommonFilter commonFilter,
		JSONObject tblJSONObj, String format) throws Exception {
	 List<String> paramValues = new ArrayList<String>();				
	 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
	 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
	 paramValues.add(condParms);	 
	 paramValues.add(commonParams);
	 CommonMessage.debugMsg("U are in Dao------------");
	 ResultSet rs = null;
	 try{
	 	rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_TRANS_SUMMARY_REPORT_PG", paramValues);
	 	//dbActionTemplate.processFunctionCallsWithColHeaders("GEN_ROLEVIEW_TEST.GEN_FN_TRANS_SUMMARY_REPORT", paramValues);
	 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		return excelUtils.writeToExcel(rs,format,2,0,0 );
	 		  
	 }finally{
		   if( rs != null)
	 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		     
	 }
	
	
}

@Override
public List<String[]> getlocation(String flid) throws Exception {
	// TODO Auto-generated method stub
	StringBuilder sql = new StringBuilder();
	sql.append("  select fnln_description  ");
	sql.append("  from GEN_MV_FLIDHIERARCHY ");
	sql.append("  where flid in ");
	sql.append("  (select SUBSTR(parentflids,15,12) from GEN_MV_FLIDHIERARCHY where flid='"+flid+"') ");
	
	CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
	
	List<String[]>  loactionData  = dbActionTemplate.getDataList(sql.toString());
    return loactionData;
}




@Override
public List<String[]> getTradeRoleLinkGrid(CommonFilter commonFilter) throws Exception {

	List<String> paramValues = new ArrayList<String>();

	String condParms = "ACTION=GET;";
	if (CommonFunctions.isValidKeyId(commonFilter.getKey())) {
		condParms += "TRADEID=" + commonFilter.getKey() + ";";
	}

	String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	paramValues.add(condParms);
	paramValues.add(commonParams);

	List<String[]> dataList = fnCallApi.callFunction(
				"GEN_FN_TRADEROLELINK_SB", paramValues, 3, false);

// ✅ Read back the total count that callFunction wrote into paramValues.get(0)
	if (commonFilter.getViewClick() == 'Y') {
		String totalCnt = paramValues.get(0);
		CommonMessage.debugMsg("TradeRoleLink totalCnt: " + totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if (isInteger) {
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		}
	}

	return dataList;
}


@Override
public void saveTradeRoleLink(String tradeId, String roleId, String createdBy) throws Exception {

    // NEW: check if this trade is already linked to a DIFFERENT active role
    String tradeCheckSql = "SELECT gtrl_roleid FROM gen_tl_trade_role_link "
            + "WHERE gtrl_tradeid = '" + tradeId + "' "
            + "AND gtrl_active = 'Y' "
            + "AND gtrl_roleid <> '" + roleId + "'";

    String existingRoleId = dbActionTemplate.getSingleValue(tradeCheckSql);

    if (existingRoleId != null && !existingRoleId.trim().isEmpty()) {
        throw new Exception("This Trade is already linked to a Role. A Trade can be linked to only one Role.");
    }

    // ── existing logic below, unchanged ──
    String checkSql = "SELECT gtrl_active FROM gen_tl_trade_role_link "
            + "WHERE gtrl_tradeid = '" + tradeId + "' AND gtrl_roleid = '" + roleId + "'";

    String existingActive = dbActionTemplate.getSingleValue(checkSql);

    List<String> sqls = new ArrayList<String>();

    if (existingActive == null || existingActive.trim().isEmpty()) {
        String insertSql = "INSERT INTO gen_tl_trade_role_link "
                + "(gtrl_tradeid, gtrl_roleid, gtrl_active, gtrl_createdby, gtrl_createdon, gtrl_modifiedon) "
                + "VALUES ('" + tradeId + "', '" + roleId + "', 'Y', '" + createdBy + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        sqls.add(insertSql);

    } else if ("Y".equals(existingActive.trim())) {
        throw new Exception("This Trade-Role combination already exists");

    } else {
        String updateSql = "UPDATE gen_tl_trade_role_link SET "
                + "gtrl_active = 'Y', "
                + "gtrl_modifiedon = CURRENT_TIMESTAMP "
                + "WHERE gtrl_tradeid = '" + tradeId + "' AND gtrl_roleid = '" + roleId + "'";
        sqls.add(updateSql);
    }

    dbActionTemplate.executeStatements(sqls);
}

@Override
 public void deleteTradeRoleLink(String tradeId, String roleId) throws Exception {
     String sql = "UPDATE gen_tl_trade_role_link "
                + "SET    gtrl_active     = 'N', "
                + "       gtrl_modifiedon = CURRENT_TIMESTAMP "
                + "WHERE  gtrl_tradeid = '" + tradeId + "' "
                + "AND    gtrl_roleid  = '" + roleId + "'";
     CommonMessage.debugMsg("TradeRoleLink delete SQL: " + sql);
     List<String> sqls = new ArrayList<>();
     sqls.add(sql);
     dbActionTemplate.executeStatements(sqls);
     CommonMessage.debugMsg("TradeRoleLink soft-deleted tradeId=[" + tradeId + "] roleId=[" + roleId + "]");
 }


@Override
 public List<String[]> getPillarRoleLinkList(CommonFilter commonFilter, String pillarId, String roleId) throws Exception {
     try {
         if (fnCallApi == null) {
             throw new Exception("FunctionCallApi not initialized.");
         }

         List<String> paramValues = new ArrayList<>();

         String condParms = "";
         if (CommonFunctions.isValidKeyId(pillarId)) {
             condParms += "PILLARID=" + pillarId + ";";
         }
         if (CommonFunctions.isValidKeyId(roleId)) {
             condParms += "ROLEID=" + roleId + ";";
         }
         String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

         CommonMessage.debugMsg("getPillarRoleLinkList condParms=[" + condParms + "]");

         paramValues.add(condParms);
         paramValues.add(commonParams);

         List<String[]> dataList = fnCallApi.callFunction(
                 "GEN_FN_PILLARROLELINK_SB", paramValues, 3, true);

         if (commonFilter.getViewClick() == 'Y') {
             String totalCnt = paramValues.get(0);
             boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
             if (isInteger) {
                 commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
             }
         }

         return dataList;

     } catch (Exception e) {
         CommonMessage.debugMsg("getPillarRoleLinkList error: " + e.getMessage());
         throw new Exception(e.getMessage());
     }
 }

@Override
 public void savePillarRoleLink(String pillarId, String roleId, String createdBy) throws Exception {

     String checkSql = "SELECT yyrl_active FROM gen_tl_whywhy_pillar_rolelink "
             + "WHERE yyrl_pillarid = '" + pillarId + "' AND yyrl_roleid = '" + roleId + "'";
     String existingActive = dbActionTemplate.getSingleValue(checkSql);

     List<String> sqls = new ArrayList<String>();

     if (existingActive == null || existingActive.trim().isEmpty()) {

         String newKeyid = dbActionTemplate.getSequenceNumber(
                 TableNames.TBL_GEN_TL_WHYWHY_PILLAR_ROLELINK);

         System.out.println("PillarRoleLink new keyid : " + newKeyid);

         // NEW: fetch pillar code from pillar master
         String pillarCode = dbActionTemplate.getSingleValue(
                 "SELECT tpmp_code FROM gen_tl_tpmpillarmst WHERE tpmp_keyid = '" + pillarId + "'");

         // NEW: fetch role code and role name from role master
         String roleCode = dbActionTemplate.getSingleValue(
                 "SELECT role_code FROM adm_tl_rolemst WHERE role_keyid = '" + roleId + "'");
         String roleName = dbActionTemplate.getSingleValue(
                 "SELECT role_name FROM adm_tl_rolemst WHERE role_keyid = '" + roleId + "'");

         String insertSql = "INSERT INTO gen_tl_whywhy_pillar_rolelink "
                 + "(yyrl_keyid, yyrl_pillarid, yyrl_pillarcode, yyrl_roleid, yyrl_rolecode, yyrl_rolename, "
                 + "yyrl_active, yyrl_createdby, yyrl_createdon, yyrl_modifiedon) "
                 + "VALUES ('" + newKeyid + "', '" + pillarId + "', '" + pillarCode + "', "
                 + "'" + roleId + "', '" + roleCode + "', '" + roleName + "', "
                 + "'Y', '" + createdBy + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
         sqls.add(insertSql);

     } else if ("Y".equals(existingActive.trim())) {
         throw new Exception("This Pillar-Role combination already exists");
     } else {
         String updateSql = "UPDATE gen_tl_whywhy_pillar_rolelink SET "
                 + "yyrl_active = 'Y', "
                 + "yyrl_modifiedon = CURRENT_TIMESTAMP "
                 + "WHERE yyrl_pillarid = '" + pillarId + "' AND yyrl_roleid = '" + roleId + "'";
         sqls.add(updateSql);
     }

     dbActionTemplate.executeStatements(sqls);
 }
 

 @Override
 public void deletePillarRoleLink(String pillarId, String roleId) throws Exception {
     String sql = "UPDATE gen_tl_whywhy_pillar_rolelink "
                + "SET    yyrl_active     = 'N', "
                + "       yyrl_modifiedon = CURRENT_TIMESTAMP "
                + "WHERE  yyrl_pillarid = '" + pillarId + "' "
                + "AND    yyrl_roleid   = '" + roleId + "'";

     CommonMessage.debugMsg("PillarRoleLink delete SQL: " + sql);

     List<String> sqls = new ArrayList<>();
     sqls.add(sql);
     dbActionTemplate.executeStatements(sqls);

     CommonMessage.debugMsg("PillarRoleLink soft-deleted pillarId=[" + pillarId + "] roleId=[" + roleId + "]");
 }
 
 // end 


	
	
}

