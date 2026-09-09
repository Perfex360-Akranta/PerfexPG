package com.akranta.tpm.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlTopicmstDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.EntTlTargetgroupdtlSql;
import com.akranta.tpm.dao.sql.EntTlTargetgroupmstSql;
import com.akranta.tpm.dao.sql.EntTlTopicLinkRoledtlSql;
import com.akranta.tpm.dao.sql.EntTlTopicmstSql;
import com.akranta.tpm.dao.sql.EntTlTrainingareaSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMachinemstSql;
import com.akranta.tpm.dao.sql.GenTlRolemstSql;
import com.akranta.tpm.dao.sql.GenTlSusamstSql;
//import com.akranta.tpm.dao.sql.GenTlUnsafeworkpracticeSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.VocTlChecklistcustomerSql;
import com.akranta.tpm.dao.sql.VocTlChecklistdtlSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTargetgroupdtl;
import com.akranta.tpm.model.EntTlTargetgroupmst;
import com.akranta.tpm.model.EntTlTopicLinkRoledtl;
import com.akranta.tpm.model.EntTlTopicmst;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlRolemst;
import com.akranta.tpm.model.GenTlSusamst;
import com.akranta.tpm.model.VocTlChecklistcustomer;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlTopicmstDaoImpl implements EntTlTopicmstDao {


	private DBActionTemplate dbActionTemplate; 
	EntTlTopicmstSql entTlTopicmstSql = new EntTlTopicmstSql();

	public EntTlTopicmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public EntTlTopicmst create(EntTlTopicmst entTlTopicmst,EntTlTrainingarea newEntTlTrainingarea) 	throws BusinessApplicationExceptions, Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTopicmstSql entTlTopicmstSql = new EntTlTopicmstSql(); 
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql(); 
			//genTlMouldmst.setMldmmouldid(dbActionTemplate.getSequenceNumber(GenTlMouldmstSql.TBL_GEN_TL_MOULDMST,10,"MLD",null,null)); // set the sequnce number 
			entTlTopicmst.setTopiKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_TOPICMST,10,"TOP",null,null)); // set the sequnce number
			
			if(!CommonFunctions.isValidKeyId(entTlTopicmst.getTopiParentid()))			
				entTlTopicmst.setTopiParentid(entTlTopicmst.getTopiKeyid());					
			
			else			
				sqls.add("update " + TableNames.TBL_ENT_TL_TOPICMST + " set TOPI_ISCHILD='N' where TOPI_KEYID='" + entTlTopicmst.getTopiParentid() +  "' " );			
			
			sqls.add(EntTlTopicmstSql.getInsertSql(entTlTopicmstSql.getTopiDbFields(), entTlTopicmst.getSaveArray())); // add insert sql for master table
			if(CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarParentid())){
				newEntTlTrainingarea.setTrarRefid(entTlTopicmst.getTopiKeyid());
				newEntTlTrainingarea.setTrarKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_TRAININGAREA,20,"TRA",null,null)); // set the sequnce number
				sqls.add(entTlTrainingareaSql.getInsertSql(entTlTrainingareaSql.getTrarDbFields(), newEntTlTrainingarea.getSaveArray())); // add insert sql for master table
			}
				
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls			
		return entTlTopicmst;
	}

	
	public EntTlTopicmst update(EntTlTopicmst entTlTopicmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlTopicmstSql entTlTopicmstSql = new EntTlTopicmstSql();

			sqls.add(entTlTopicmstSql.getUpdateSql(entTlTopicmstSql.getTopiDbFields(), entTlTopicmst.getSaveArray()));
			String sql = "update ent_tl_trainingarea set TRAR_NAME = '"+entTlTopicmst.getTopiName()+"' where TRAR_REFID = '"+entTlTopicmst.getTopiKeyid()+"'";
			sqls.add(sql);
			dbActionTemplate.executeStatements(sqls);
			
		return entTlTopicmst;
	}
	
	public EntTlTopicmst delete(EntTlTopicmst entTlTopicmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlTopicmstSql entTlTopicmstSql = new EntTlTopicmstSql();
			
			sqls.add(entTlTopicmstSql.getDeleteSql(entTlTopicmstSql.getTopiDbFields(), entTlTopicmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		return entTlTopicmst;
	}

	@Override
	public EntTlTopicmst select(EntTlTopicmst entTlTopicmst) throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		EntTlTopicmstSql entTlTopicmstSql = new EntTlTopicmstSql();
		String sql = entTlTopicmstSql.getSelectSql(entTlTopicmstSql.getTopiDbFields(), entTlTopicmst.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		//String Qaud_keyid=qtmTlDockaudit.getQaudkeyid();
		Object [] args =  new Object [] {};
		entTlTopicmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+entTlTopicmst.getTopiKeyid());
		return entTlTopicmst;
	}
	
	@Override
	public List<EntTlTopicmst> selectList(EntTlTopicmst entTlTopicmst)throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		EntTlTopicmstSql entTlTopicmstSql = new EntTlTopicmstSql();
		String sql = entTlTopicmstSql.getSelectSql(entTlTopicmstSql.getTopiDbFields(), entTlTopicmst.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		//String Qaud_keyid=qtmTlDockaudit.getQaudkeyid();
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillTopicmstList(resultList);		
	}
	private List<EntTlTopicmst> fillTopicmstList(List<String []> resultList) throws SQLException
	{
		   List<EntTlTopicmst> menus = new ArrayList<EntTlTopicmst>();
		   for( String [] row : resultList )
		   {			  
			   EntTlTopicmst entTlTopicmst = new EntTlTopicmst();
			   entTlTopicmst.setTopiKeyid(row[0]);
			   entTlTopicmst.setTopiCode(row[1]);
			   entTlTopicmst.setTopiName(row[2]);	
			   entTlTopicmst.setTopiParentid(row[3]);
			   entTlTopicmst.setTopiIschild(row[4]);			   
			   entTlTopicmst.setTopiEvaluationtypeid(row[6]);
			   entTlTopicmst.setTopiRemarks(row[7]);
			   entTlTopicmst.setTopiEffectiveDate(row[8]);
			   entTlTopicmst.setTopiInactiveDate(row[9]);			   
			   menus.add(entTlTopicmst);			   
		   }
  		  return menus;
	 }

	@Override
	public List<String[]> getTopicDet(CommonFilter commonFilter,String TopicKeyid) {

		try
		{
			List<String> params = new ArrayList<String>();
			String sql =EntTlTopicmstSql.TopicDetailGrid(commonFilter,TopicKeyid);
			
			String countsql = CommonFilterSqls.countSql(sql.toString(), commonFilter.getGridFilter());
			String cntStr = dbActionTemplate.getSingleValue(countsql);
			int count = Integer.parseInt(cntStr);
			commonFilter.setTotalRecordCnt(count);

			if( count > 0  ){
				GridParams gridParams = new GridParams();
				gridParams.setFromRow(commonFilter.getFromRow());
				gridParams.setToRow(commonFilter.getToRow());
				String oSql =  CommonFilterSqls.addPaginationParams(sql.toString(), gridParams);
				CommonMessage.debugMsg("sql " + oSql);
				List<String[]> operator = dbActionTemplate.getDataList(oSql, params);
				return operator;

			}
		    			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
		
	}
	
	@Override
	public Workbook TopicExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getTopicReport(commonFilter);
			CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			/*
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);*/
			return excelUtils.writeToExcel(rs,format,0,-1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}
	
	private ResultSet getTopicReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String sql =EntTlTopicmstSql.TopicDetailGrid(commonFilter,commonFilter.getKey());
		return dbActionTemplate.getData(sql);
	}

	@Override
	public EntTlTopicmst createTopic(EntTlTopicmst newEntTlTopicmst,EntTlTopicLinkRoledtl newEntTlLinkRoledtl) throws BusinessApplicationExceptions, Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTopicmstSql entTlTopicmstSql = new EntTlTopicmstSql(); 
	    EntTlTopicLinkRoledtlSql entTlTopicLinkRoledtlSql=new EntTlTopicLinkRoledtlSql();
	          
			//genTlMouldmst.setMldmmouldid(dbActionTemplate.getSequenceNumber(GenTlMouldmstSql.TBL_GEN_TL_MOULDMST,10,"MLD",null,null)); // set the sequnce number 
			newEntTlTopicmst.setTopiKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_TOPICMST,10,"TOP",null,null)); // set the sequnce number
			
			/*if(!CommonFunctions.isValidKeyId(newEntTlTopicmst.getTopiSpokeid()))			
				newEntTlTopicmst.setTopiSpokeid(newEntTlTopicmst.getTopiKeyid());					
			
			else			
				sqls.add("update " + TableNames.TBL_ENT_TL_TOPICMST + " set TOPI_ISCHILD='N' where TOPI_KEYID='" + newEntTlTopicmst.getTopiKeyid() +  "' " );*/			
			
			sqls.add(EntTlTopicmstSql.getInsertSql(entTlTopicmstSql.getTopiDbFields(), newEntTlTopicmst.getSaveArray())); // add insert sql for master table
			/*if(!CommonFunctions.isValidKeyId(newEntTlLinkRoledtl.getToprKeyid())){
				
				newEntTlLinkRoledtl.setToprTopiKeyid(newEntTlTopicmst.getTopiKeyid());
				newEntTlLinkRoledtl.setToprKeyid(dbActionTemplate.getSequenceNumber(entTlTopicLinkRoledtlSql.TBL_ENT_TL_TOPIC_LINK_ROLEDTL,20,"TOPR",null,null)); // set the sequnce number
				sqls.add(entTlTopicLinkRoledtlSql.getInsertSql(entTlTopicLinkRoledtlSql.getToprDbFields(), newEntTlLinkRoledtl.getSaveArray())); // add insert sql for master table
			   	
			}*/
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls			
		return newEntTlTopicmst;
	}

	@Override
	public void DeleteTopiclist(String keyid) throws Exception {
	
			List<String > sqls = new ArrayList<String>();
			//sqls.add(GenTlMomKpiLinkSql.DeleteMomRow(keyid));
			sqls.add( EntTlTopicLinkRoledtlSql.DeleteTopiclist(keyid));
  
			dbActionTemplate.executeStatements(sqls);
	}

	@Override
	public EntTlTopicmst updateTopic(EntTlTopicmst newEntTlTopicmst,EntTlTopicLinkRoledtl newEntTlLinkRoledtl,String trRoleid) throws Exception {
		List<String> sqls = new ArrayList<String>();
		EntTlTopicLinkRoledtlSql newEntTlTopicLinkRoledtlSql= new EntTlTopicLinkRoledtlSql(); 
			sqls.add(entTlTopicmstSql.getUpdateSql(entTlTopicmstSql.getTopiDbFields(), newEntTlTopicmst.getSaveArray()));
			/*if(!UIUtils.isValidKeyId(newEntTlLinkRoledtl.getToprKeyid()))
			{
				newEntTlLinkRoledtl.setToprTopiKeyid(newEntTlTopicmst.getTopiKeyid());
				newEntTlLinkRoledtl.setToprRoleKeyid(trRoleid);
				newEntTlLinkRoledtl.setToprKeyid(dbActionTemplate.getSequenceNumber(EntTlTopicLinkRoledtlSql.TBL_ENT_TL_TOPIC_LINK_ROLEDTL,10,"TOPR",  "MMYY", "Y")); // set the sequnce number
				CommonMessage.debugMsg("momd Key ID  ::::"+newEntTlLinkRoledtl.getToprKeyid());
				sqls.add(EntTlTopicLinkRoledtlSql.getInsertSql(newEntTlTopicLinkRoledtlSql.getToprDbFields(), newEntTlLinkRoledtl.getSaveArray()));								
			}*/
			//newEntTlLinkRoledtl.setToprKeyid(dbActionTemplate.getSequenceNumber(EntTlTopicLinkRoledtlSql.TBL_ENT_TL_TOPIC_LINK_ROLEDTL,10,"TOPR",  "MMYY", "Y")); // set the sequnce number
		//	newEntTlLinkRoledtl.setToprRoleKeyid(trRoleid);
		//	newEntTlLinkRoledtl.setToprTopiKeyid(newEntTlTopicmst.getTopiKeyid());
		//	sqls.add(EntTlTopicLinkRoledtlSql.getInsertSql(newEntTlTopicLinkRoledtlSql.getToprDbFields(), newEntTlLinkRoledtl.getSaveArray()));
			//sqls.add(EntTlTopicLinkRoledtlSql.getUpdateSql(newEntTlTopicLinkRoledtlSql.getToprDbFields(), newEntTlTopicLinkRoledtl.getSaveArray()));	
				
			/*else
			{
				String sql = "update ENT_TL_TOPIC_LINK_ROLEDTL set TOPR_ROLE_KEYID = '"+trRoleid+"' where TOPR_TOPI_KEYID = '"+newEntTlTopicmst.getTopiKeyid()+"'";
				sqls.add(sql);
				
			}*/
			
			dbActionTemplate.executeStatements(sqls);
			
		return newEntTlTopicmst;
		
	}

	@Override
	public EntTlTopicmst deleteTopicmst(EntTlTopicmst entTlTopicmst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		EntTlTopicLinkRoledtl newEntTlTopicLinkRoledtl=new EntTlTopicLinkRoledtl(); 
		EntTlTopicLinkRoledtlSql newEntTlTopicLinkRoledtlSql=new EntTlTopicLinkRoledtlSql(); 
		//EntTlTopicmstSql entTlTopicmstSql = new EntTlTopicmstSql();
			sqls.add("Delete from " +EntTlTopicLinkRoledtlSql.TBL_ENT_TL_TOPIC_LINK_ROLEDTL+" where TOPR_TOPI_KEYID='"+entTlTopicmst.getTopiKeyid()+"'");
			sqls.add(entTlTopicmstSql.getDeleteSql(entTlTopicmstSql.getTopiDbFields(), entTlTopicmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		return entTlTopicmst;
	}

	@Override
	public List<String[]> getSql(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = entTlTopicmstSql.getRecall(keyid); 
		List<String[]> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	@Override
	public List<String[]> getselect(CommonFilter commonfilter) throws Exception {
		// TODO Auto-generated method stub
		//CommonFilter commonFilter = new CommonFilter();
		StringBuffer sql = new StringBuffer();
		//sql.append("select 'empmkeyid' as keyid,'SelectVal' as checks,'Employee Code' as code, 'Employee Name'as name,'Roleid'as roleid from dual union all");
		//sql.append("   select Empm_keyid as keyid,''as checks,empm_code as code,empm_name as name,EMPM_ROLEID as roleid from gen_tl_employeemst where 1=1");
		
//		sql.append("  SELECT * FROM ( " );
//		sql.append("  select DISTINCT Empm_keyid as keyid,''as checks,empm_code ,empm_name ,ROLE_NAME  "); 
//		sql.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM, GEN_TL_ROLEMST where 1=1 ");
//		sql.append("  AND EMPM_KEYID = FRT_EMPM_KEYID ");
//		sql.append("  AND ROLE_KEYID (+) = EMPM_ROLEID ");
//		
//		sql.append("  AND FRT_FNLN_KEYID = '"+commonfilter.getFlid()+"' ");
//		
//		/*GridParams  GridParams = new GridParams();
//		GridParams.setGridFilters(commonfilter.getGridFilter());
//		GridParams.setFromRow(commonfilter.getFromRow());
//		GridParams.setToRow(commonfilter.getToRow());*/
//		
//		List<String[]> gridData ;
//		sql.append("  ORDER BY EMPM_NAME ");
//		
//		sql.append( " ) WHERE 1 = 1 " );
		
		sql.append("  SELECT * FROM ( ");
		sql.append("  SELECT DISTINCT Empm_keyid as keyid, '' as checks, empm_code, empm_name, ROLE_NAME ");
		sql.append("  FROM GEN_TL_EMPLOYEEMST ");
		sql.append("  INNER JOIN GEN_TL_FNLNROLETEAM ON EMPM_KEYID = FRT_EMPM_KEYID ");
		sql.append("  LEFT JOIN GEN_TL_ROLEMST ON ROLE_KEYID = EMPM_ROLEID ");
		sql.append("  WHERE 1=1 ");

		sql.append("  AND FRT_FNLN_KEYID = '" + commonfilter.getFlid() + "' ");

		/*GridParams  GridParams = new GridParams();
		GridParams.setGridFilters(commonfilter.getGridFilter());
		GridParams.setFromRow(commonfilter.getFromRow());
		GridParams.setToRow(commonfilter.getToRow());*/

		List<String[]> gridData;
		sql.append("  ORDER BY EMPM_NAME ");

		sql.append(" ) subquery WHERE 1 = 1 ");
		sql.append(FilterCondSql.makeGridFilterCond(commonfilter.getGridFilter()) );
		
		CommonMessage.debugMsg("sql==="+sql);
		String countsql = CommonFilterSqls.countSql(sql.toString(), commonfilter.getGridFilter());
		String cntStr = dbActionTemplate.getSingleValue(countsql);
		int count = Integer.parseInt(cntStr);
		commonfilter.setTotalRecordCnt(count);
		if( count > 0  ){
			
			GridParams gridParams = new GridParams();
			gridParams.setFromRow(commonfilter.getFromRow());
			gridParams.setToRow(commonfilter.getToRow());
			String oSql =  CommonFilterSqls.addPaginationParams(sql.toString(), gridParams);
			CommonMessage.debugMsg("sql " + oSql);
			gridData = dbActionTemplate.getDataList(oSql.toString());
			return gridData;
			
			
		}
		
		throw new NoDataFoundException("No Data Found");
	}

	@Override
	public GenTlRolemst createunique(GenTlRolemst newgentlrolemst) throws Exception {
		// TODO Auto-generated method stub
		
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
			GenTlRolemstSql genTlRolemstSql = new GenTlRolemstSql(); // contains dbtable,field names, Field types and related sqls  of master table
			 List<GenTlEmployeemst> newGenTlEmployeemst=newgentlrolemst.getEmployeedetails();
			
			 
			 	CommonMessage.debugMsg("dao impl");
				newgentlrolemst.setRoleKeyid(dbActionTemplate.getSequenceNumber(GenTlRolemstSql.TBL_GEN_TL_ROLEMST,6,"ERL","","")); // set the sequnce number 
				sqls.add(GenTlRolemstSql.getInsertSql(genTlRolemstSql.getRoleDbFields(), newgentlrolemst.getSaveArray())); // add insert sql for master table

			 for( GenTlEmployeemst employeelink : newGenTlEmployeemst)
				{
					sqls.add("update GEN_TL_EMPLOYEEMST SET EMPM_ROLEID ='" +newgentlrolemst.getRoleKeyid()+"' WHERE EMPM_KEYID='"+employeelink.getEmpmKeyid()+"'");
				}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return newgentlrolemst;
	}
	

	@Override
	public GenTlRolemst updateunique(GenTlRolemst newgentlrolemst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		GenTlRolemstSql genTlRolemstSql = new GenTlRolemstSql();
		 List<GenTlEmployeemst> newGenTlEmployeemst=newgentlrolemst.getEmployeedetails();

			sqls.add(GenTlRolemstSql.getUpdateSql(genTlRolemstSql.getRoleDbFields(), newgentlrolemst.getSaveArray()));
			for( GenTlEmployeemst employeelink : newGenTlEmployeemst)
			{
				sqls.add("update GEN_TL_EMPLOYEEMST SET EMPM_ROLEID ='" +newgentlrolemst.getRoleKeyid()+"' WHERE EMPM_KEYID='"+employeelink.getEmpmKeyid()+"'");
			}
			dbActionTemplate.executeStatements(sqls);
			
		
		return newgentlrolemst;
	}
	

	@Override
	public List<String[]> getselectmain(CommonFilter commonFilter) throws Exception {
		
		// 10Mar2026 -- Converted Oracle to Postgres 
		
//		StringBuffer sql = new StringBuffer();
//		sql.append(" SELECT ROLE_KEYID AS KEYID , SECT_NAME DMT,CELL_NAME JH,ROLE_NAME ,TO_CHAR(CNT),FUNCTIONALLOC FUNCTIONALLOCATION ");
//		sql.append(" FROM GEN_TL_ROLEMST ,GEN_VW_FNLN,GEN_MV_FLIDHIERARCHY, ");
//		sql.append(" (SELECT COUNT(DISTINCT Empm_Keyid) CNT, FRT_FNLN_KEYID, ROLE_KEYID AS MSTKEYID FROM GEN_TL_ROLEMST,GEN_TL_EMPLOYEEMST, Gen_Tl_Fnlnroleteam ");
//		sql.append(" WHERE ROLE_KEYID = EMPM_ROLEID And FRT_Empm_Keyid = Empm_Keyid GROUP BY FRT_FNLN_KEYID, ROLE_KEYID) ");
//		sql.append(" WHERE ROLE_KEYID=MSTKEYID AND FRT_FNLN_KEYID = FLID AND FNLN_KEYID(+) = ROLE_FLID  ");
//		sql.append(" AND ROLE_FLID =  FLID " ); 
//		sql.append(" AND INSTR(PARENTFLIDS||FLID, '"+commonFilter.getFlid()+"' ) > 0 " ); 
//		sql.append(" AND INSTR(ROLE_ELEMENTID,'"+commonFilter.getLocation().getId()+"')>0 ");
//				
//		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT r.ROLE_KEYID AS KEYID, ");
		sql.append("        f.SECT_NAME AS DMT, ");
		sql.append("        f.CELL_NAME AS JH, ");
		sql.append("        r.ROLE_NAME, ");
		sql.append("        CAST(x.CNT AS VARCHAR) AS CNT, ");
		sql.append("        f.FUNCTIONALLOC AS FUNCTIONALLOCATION ");
		sql.append(" FROM GEN_TL_ROLEMST r ");
		sql.append(" JOIN ( ");
		sql.append("        SELECT COUNT(DISTINCT e.EMPM_KEYID) AS CNT, ");
		sql.append("               frt.FRT_FNLN_KEYID, ");
		sql.append("               rm.ROLE_KEYID AS MSTKEYID ");
		sql.append("        FROM GEN_TL_ROLEMST rm ");
		sql.append("        JOIN GEN_TL_EMPLOYEEMST e ");
		sql.append("          ON rm.ROLE_KEYID = e.EMPM_ROLEID ");
		sql.append("        JOIN GEN_TL_FNLNROLETEAM frt ");
		sql.append("          ON frt.FRT_EMPM_KEYID = e.EMPM_KEYID ");
		sql.append("        GROUP BY frt.FRT_FNLN_KEYID, rm.ROLE_KEYID ");
		sql.append("      ) x ");
		sql.append("   ON r.ROLE_KEYID = x.MSTKEYID ");
		sql.append(" JOIN GEN_MV_FLIDHIERARCHY h ");
		sql.append("   ON x.FRT_FNLN_KEYID = h.FLID ");
		sql.append("  AND r.ROLE_FLID = h.FLID ");
		sql.append(" LEFT JOIN GEN_VW_FNLN f ");
		sql.append("   ON f.FNLN_KEYID = r.ROLE_FLID ");
		sql.append(" WHERE STRPOS(COALESCE(h.PARENTFLIDS,'') || COALESCE(h.FLID,''), '" + commonFilter.getFlid() + "') > 0 ");
		sql.append("   AND STRPOS(COALESCE(r.ROLE_ELEMENTID,''), '" + commonFilter.getLocation().getId() + "') > 0 ");

		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
		
		CommonMessage.debugMsg("sql for debug " + sql.toString());
		
		List<String[]> dataList;
		
		String countsql = CommonFilterSqls.countSql(sql.toString(), commonFilter.getGridFilter());
		String cntStr = dbActionTemplate.getSingleValue(countsql);
		int count = Integer.parseInt(cntStr);
		commonFilter.setTotalRecordCnt(count);
		CommonMessage.debugMsg("sql in side DAOImpl " + sql.toString());

		if( count > 0  ){
			
			GridParams gridParams = new GridParams();
			gridParams.setFromRow(commonFilter.getFromRow());
			gridParams.setToRow(commonFilter.getToRow());
			String oSql =  CommonFilterSqls.addPaginationParams(sql.toString(), gridParams);
			CommonMessage.debugMsg("sql " + oSql);
			dataList =  dbActionTemplate.getDataList(oSql.toString());
			return dataList;
			
			
		}
		throw new NoDataFoundException("No Data Found");
		
		
		/*
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ROLE_KEYID AS KEYID , SECT_NAME DMT,CELL_NAME JH,ROLE_NAME ,TO_CHAR(CNT),FUNCTIONALLOC FUNCTIONALLOCATION ");
		sql.append(" FROM GEN_TL_ROLEMST ,GEN_VW_FNLN,GEN_MV_FLIDHIERARCHY, (SELECT COUNT(*) CNT, ROLE_KEYID AS MSTKEYID FROM GEN_TL_ROLEMST,GEN_TL_EMPLOYEEMST ");
		sql.append(" WHERE ROLE_KEYID = EMPM_ROLEID GROUP BY ROLE_KEYID) WHERE ROLE_KEYID=MSTKEYID AND FNLN_KEYID(+) = ROLE_FLID  ");
		sql.append(" AND ROLE_FLID =  FLID " ); 
		sql.append(" AND INSTR(PARENTFLIDS||FLID, '"+commonFilter.getFlid()+"' ) > 0 " ); 
		sql.append(" AND INSTR(ROLE_ELEMENTID,'"+commonFilter.getLocation().getId()+"')>0 ");
		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		CommonMessage.debugMsg(" filter "+(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()))+" sql "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql.toString());
		return dataList;*/
		
	}

	@Override
	public GenTlRolemst getrolemain(String keyId) {
		// TODO Auto-generated method stub
		GenTlRolemst  genTlRolemst = new GenTlRolemst();
		try
		{
			GenTlRolemstSql genTlrolemstsql = new GenTlRolemstSql();
			
			String sql =  genTlrolemstsql.getselectsql();
			Object [] args =  new Object [] {keyId };
			genTlRolemst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
			CommonMessage.debugMsg(sql+""+keyId);
			CommonMessage.debugMsg( "dao impl "+genTlRolemst.getRoleKeyid());
			
          }catch(Exception e){
			
		}
          return genTlRolemst;
	}

	@Override
	public GenTlRolemst deleteunique(GenTlRolemst newgentlrolemst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		GenTlRolemstSql genTlRolemstsql = new GenTlRolemstSql();
			
			sqls.add(GenTlRolemstSql.getDeleteSql(genTlRolemstsql.getRoleDbFields(), newgentlrolemst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		return newgentlrolemst;
	}
	@Override
	public int selectCount(CommonFilter commonFilter) throws Exception {
		String sql = entTlTopicmstSql.getCountAll(commonFilter);
		CommonMessage.debugMsg("Count sql....."+sql);
		String returnData = dbActionTemplate.getSingleValue(sql);
		int retData = Integer.parseInt(returnData);
		CommonMessage.debugMsg(retData);
		return  retData;
	}
	@Override
	public List<String[]> getselectdEmpRole(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sqls = new StringBuffer();
		//	CommonMessage.debugMsg("inside the get data"+commonFilter.getFactoryId());
//			sqls.append("  SELECT * FROM ( ");
//			CommonMessage.debugMsg("inside the get data"+sqls);
//			sqls.append("   select  Distinct Empm_keyid as keyid,ROLE_NAME ,empm_code||'-'||empm_name as name,EMPM_ROLEID as roleid  "); 
//			sqls.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,GEN_TL_ROLEMST where 1=1 ");
//			sqls.append("  AND EMPM_KEYID = FRT_EMPM_KEYID AND EMPM_ROLEID = ROLE_KEYID ");
//			CommonMessage.debugMsg("inside the get data"+sqls);
//			sqls.append("  AND FRT_FNLN_KEYID = '"+commonFilter.getFlid()+"' ");
//			CommonMessage.debugMsg("inside the get data"+sqls);
//			sqls.append("  AND EMPM_ROLEID = '"+commonFilter.getParamCode()+"'");
//			CommonMessage.debugMsg("inside the get data"+sqls);
//			//sqls.append(" AND INSTR(ROLE_ELEMENTID,'"+commonFilter.getFactoryId()+"')>0 ");
//			sqls.append( " ) WHERE 1 = 1 " );
		sqls.append("  SELECT * FROM ( ");
		CommonMessage.debugMsg("inside the get data" + sqls);
		sqls.append("   SELECT DISTINCT Empm_keyid as keyid, ROLE_NAME, empm_code || '-' || empm_name as name, EMPM_ROLEID as roleid ");
		sqls.append("  FROM GEN_TL_EMPLOYEEMST ");
		sqls.append("  INNER JOIN GEN_TL_FNLNROLETEAM ON EMPM_KEYID = FRT_EMPM_KEYID ");
		sqls.append("  INNER JOIN GEN_TL_ROLEMST ON EMPM_ROLEID = ROLE_KEYID ");
		sqls.append("  WHERE 1=1 ");
		CommonMessage.debugMsg("inside the get data" + sqls);
		sqls.append("  AND FRT_FNLN_KEYID = '" + commonFilter.getFlid() + "' ");
		CommonMessage.debugMsg("inside the get data" + sqls);
		sqls.append("  AND EMPM_ROLEID = '" + commonFilter.getParamCode() + "'");
		CommonMessage.debugMsg("inside the get data" + sqls);
		//sqls.append(" AND POSITION('" + commonFilter.getFactoryId() + "' IN ROLE_ELEMENTID) > 0 ");
		sqls.append(" ) subquery WHERE 1 = 1 ");
			CommonMessage.debugMsg("inside the get data"+sqls);
		sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		CommonMessage.debugMsg("sql get selected employee"+sqls.toString());
		List<String[]> dataList =  dbActionTemplate.getDataList(sqls.toString());
		CommonMessage.debugMsg("data is not listing");
		return dataList;
	}

	@Override
	public List<String[]> getselectmaintarget(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		
		sql.append("  SELECT tgtm_keyid AS keyid, tgtm_title, TO_CHAR (cnt) FROM ent_tl_targetgroupmst,");
		sql.append("(SELECT   COUNT (*) cnt, tgtm_keyid AS mstkeyid FROM ent_tl_targetgroupmst, ent_tl_targetgroupdtl WHERE tgtm_keyid(+) = tgtd_tgtm_keyid GROUP BY tgtm_keyid) WHERE tgtm_keyid = mstkeyid");
		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		CommonMessage.debugMsg(" sql "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql.toString());
		return dataList;
	}

	@Override
	public EntTlTargetgroupmst gettargetmain(String keyId) {
		// TODO Auto-generated method stub
		EntTlTargetgroupmst  entTlTargetgroupmst = new EntTlTargetgroupmst();
		try
		{
			EntTlTargetgroupmstSql enttlTargetgroupmstsql = new EntTlTargetgroupmstSql();
			
			String sql =  enttlTargetgroupmstsql.getselectsql();
			Object [] args =  new Object [] {keyId };
			entTlTargetgroupmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
			CommonMessage.debugMsg(sql+""+keyId);
			CommonMessage.debugMsg( "dao impl "+entTlTargetgroupmst.getTgtmKeyid());
			
          }catch(Exception e){
			
		}
          return entTlTargetgroupmst;
	}

	@Override
	public List<String[]> getselecttarget(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		
		sql.append("  SELECT empm_keyid AS keyid, '' AS checks, empm_code AS code, empm_name AS NAME, TGTD_TGTM_KEYID AS trgtid,TGTD_KEYID FROM gen_tl_employeemst,ent_tl_targetgroupdtl,GEN_TL_FNLNROLETEAM WHERE 1 = 1 and TGTD_EMPID(+) =EMPM_KEYID AND EMPM_KEYID = FRT_EMPM_KEYID AND FRT_FNLN_KEYID ='"+commonFilter.getFlid() +"'");
		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		CommonMessage.debugMsg("sql "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql.toString());
		return dataList;
	}
	

	@Override
	public EntTlTargetgroupmst createtarget(EntTlTargetgroupmst newEntTlTargetGroupmst)throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTargetgroupmstSql entTlTargetgroupmstSql = new EntTlTargetgroupmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlTargetgroupdtlSql entTlTargetgroupdtlSql = new EntTlTargetgroupdtlSql();
		
			newEntTlTargetGroupmst.setTgtmKeyid(dbActionTemplate.getSequenceNumber(EntTlTargetgroupmstSql.TBL_ENT_TL_TARGETGROUPMST,10,"TGM","","")); // set the sequnce number 
			sqls.add(EntTlTargetgroupmstSql.getInsertSql(entTlTargetgroupmstSql.getTgtmDbFields(), newEntTlTargetGroupmst.getSaveArray())); // add insert sql for master table
			List<EntTlTargetgroupdtl> newenttargetgp=newEntTlTargetGroupmst.getTargetgroupdtl();
			for( EntTlTargetgroupdtl targetgp : newenttargetgp)
			{
				sqls.add(EntTlTargetgroupdtlSql.getDeleteSql(entTlTargetgroupdtlSql.getTgtdDbFields(), targetgp.getSaveArray()));
				
				targetgp.setTgtdTgtmKeyid(newEntTlTargetGroupmst.getTgtmKeyid());
				targetgp.setTgtdKeyid(dbActionTemplate.getSequenceNumber(EntTlTargetgroupdtlSql.TBL_ENT_TL_TARGETGROUPDTL,10,"TGD","","")); // set the sequnce number 
				sqls.add(EntTlTargetgroupdtlSql.getInsertSql(entTlTargetgroupdtlSql.getTgtdDbFields(), targetgp.getSaveArray())); 
			}
			
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return newEntTlTargetGroupmst;
	}
	
	
	@Override
	public EntTlTargetgroupmst updatetarget(EntTlTargetgroupmst newEntTlTargetGroupmst)throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		EntTlTargetgroupmstSql entTlTargetgroupmstSql = new EntTlTargetgroupmstSql();
		EntTlTargetgroupdtlSql entTlTargetgroupdtlSql = new EntTlTargetgroupdtlSql();

			sqls.add(EntTlTargetgroupmstSql.getUpdateSql(entTlTargetgroupmstSql.getTgtmDbFields(), newEntTlTargetGroupmst.getSaveArray()));
			List<EntTlTargetgroupdtl> newenttargetgp=newEntTlTargetGroupmst.getTargetgroupdtl();
			sqls.add(EntTlTargetgroupdtlSql.getDelete(newEntTlTargetGroupmst.getTgtmKeyid()));
			for( EntTlTargetgroupdtl targetgp : newenttargetgp)
			{

					targetgp.setTgtdTgtmKeyid(newEntTlTargetGroupmst.getTgtmKeyid());
					targetgp.setTgtdKeyid(dbActionTemplate.getSequenceNumber(EntTlTargetgroupdtlSql.TBL_ENT_TL_TARGETGROUPDTL,10,"TGD","","")); // set the sequnce number 
					sqls.add(EntTlTargetgroupdtlSql.getInsertSql(entTlTargetgroupdtlSql.getTgtdDbFields(), targetgp.getSaveArray())); 
			}
			
			dbActionTemplate.executeStatements(sqls);
			
		return newEntTlTargetGroupmst;
	}

	@Override
	public EntTlTargetgroupmst deletetarget(EntTlTargetgroupmst entTlTargetgroupmst) throws Exception {
		// TODO Auto-generated method stub

		List<String> sqls = new ArrayList<String>();
		EntTlTargetgroupmstSql entTlTargetgroupmstsql = new EntTlTargetgroupmstSql();
			sqls.add(EntTlTargetgroupdtlSql.getDelete(entTlTargetgroupmst.getTgtmKeyid()));
			sqls.add(EntTlTargetgroupmstSql.getDeleteSql(entTlTargetgroupmstsql.getTgtmDbFields(), entTlTargetgroupmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		return entTlTargetgroupmst;
	}

	@Override
	public Workbook gettargetExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception, SQLException {
		// TODO Auto-generated method stub
		 ResultSet rs = null;
		   try{
				rs =   gettargetSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils( colmodel);
				return excelUtils.writeToExcel(rs,format,0,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		
	}

	private ResultSet gettargetSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("Before  Function..."+commonFilter.getFromRow());
		String sql = EntTlTargetgroupmstSql.gettarget(commonFilter);
		CommonMessage.debugMsg("sql the Function..."+sql);
		return  dbActionTemplate.getData(sql);				
	
}

	@Override
	public EntTlTargetgroupdtl deletetargetdtl(List<EntTlTargetgroupdtl> newentTlTargetgroupdtl) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>();
		EntTlTargetgroupdtlSql entTlTargetgroupdtlsql = new EntTlTargetgroupdtlSql();
		EntTlTargetgroupdtl entTlTargetgroupdtl = new EntTlTargetgroupdtl();
	
		for( EntTlTargetgroupdtl targetgp : newentTlTargetgroupdtl)
		{
		
			sqls.add(EntTlTargetgroupdtlSql.getDeletedtl(targetgp.getTgtdKeyid()));
		}
			dbActionTemplate.executeStatements(sqls);
			
		return entTlTargetgroupdtl;
	}

	@Override
	public GenTlRolemst deleteUPEmployee(GenTlRolemst newgentlrolemst)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		GenTlRolemstSql genTlRolemstsql = new GenTlRolemstSql();
		GenTlRolemst genTlRolemst  = new GenTlRolemst();
		String updateSql =null;	
	    String sqlDel = "Delete from gen_tl_rolemst where role_keyid='"+newgentlrolemst.getEmployeedetails().get(0).getEmpmRoleid()+"'";
	   
		for(GenTlEmployeemst gentlemployeemst : newgentlrolemst.getEmployeedetails())
		{
			updateSql = "Update gen_tl_employeemst set EMPM_ROLEID = '{}' where empm_keyid= '"+gentlemployeemst.getEmpmKeyid()+"'";
			sqls.add(updateSql);
		}
		 	//sqls.add(sqlDel);
			dbActionTemplate.executeStatements(sqls);
			
		return genTlRolemst;
	}

	@Override
	public Workbook getUniquePositionExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		
		/*
		
		ResultSet rs = null;
		   try{
			   StringBuffer sql = new StringBuffer();
				sql.append(" SELECT ROLE_KEYID AS KEYID , SECT_NAME DMT,CELL_NAME JH,ROLE_NAME ,TO_CHAR(CNT),FUNCTIONALLOC FUNCTIONALLOCATION ");
				sql.append(" FROM GEN_TL_ROLEMST ,GEN_VW_FNLN,GEN_MV_FLIDHIERARCHY, (SELECT COUNT(*) CNT, ROLE_KEYID AS MSTKEYID FROM GEN_TL_ROLEMST,GEN_TL_EMPLOYEEMST ");
				sql.append(" WHERE ROLE_KEYID = EMPM_ROLEID GROUP BY ROLE_KEYID) WHERE ROLE_KEYID=MSTKEYID AND FNLN_KEYID(+) = ROLE_FLID  ");
				sql.append(" AND ROLE_FLID =  FLID " ); 
				sql.append(" AND INSTR(PARENTFLIDS||FLID, '"+commonFilter.getFlid()+"' ) > 0 " ); 
				sql.append(" AND INSTR(ROLE_ELEMENTID,'"+commonFilter.getLocation().getId()+"')>0 ");
						
				sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
				
			
				String countsql = CommonFilterSqls.countSql(sql.toString(), commonFilter.getGridFilter());
				String cntStr = dbActionTemplate.getSingleValue(countsql);
				int count = Integer.parseInt(cntStr);
				commonFilter.setTotalRecordCnt(count);
				if( count > 0  ){
					
					GridParams gridParams = new GridParams();
					gridParams.setFromRow(commonFilter.getFromRow());
					gridParams.setToRow(commonFilter.getToRow());
					String oSql =  CommonFilterSqls.addPaginationParams(sql.toString(), gridParams);
					CommonMessage.debugMsg("sql " + oSql);
					//dataList =  dbActionTemplate.getDataList(oSql.toString());
					//return dataList;
					rs = dbActionTemplate.getData(oSql.toString());					
					ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
					CommonMessage.debugMsg("U R in : EntTopicmst DAO") ;
					return excelUtils.writeToExcel(rs,format,0,0,0);
				}
				throw new NoDataFoundException("No Data Found");
				
			}finally{
					   if( rs != null)
			 	   			  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				     
			}  			

		
		*/
	
		
		
		ResultSet rs = null;
		   try{
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT ROLE_KEYID AS KEYID , SECT_NAME DMT,CELL_NAME JH,ROLE_NAME ,TO_CHAR(CNT),FUNCTIONALLOC FUNCTIONALLOCATION ");
				sql.append(" FROM GEN_TL_ROLEMST ,GEN_VW_FNLN,GEN_MV_FLIDHIERARCHY, (SELECT COUNT(*) CNT, ROLE_KEYID AS MSTKEYID FROM GEN_TL_ROLEMST,GEN_TL_EMPLOYEEMST ");
				sql.append(" WHERE ROLE_KEYID = EMPM_ROLEID GROUP BY ROLE_KEYID) WHERE ROLE_KEYID=MSTKEYID AND FNLN_KEYID(+) = ROLE_FLID  ");
				sql.append(" AND ROLE_FLID =  FLID " ); 
				sql.append(" AND INSTR(PARENTFLIDS||FLID, '"+commonFilter.getFlid()+"' ) > 0 " ); 
				sql.append(" AND INSTR(ROLE_ELEMENTID,'"+commonFilter.getLocation().getId()+"')>0 ");
				sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );				
				CommonMessage.debugMsg(" filter "+(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()))+" sql "+sql);				
				rs = dbActionTemplate.getData(sql.toString());					
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				CommonMessage.debugMsg("U R in : EntTopicmst DAO") ;
				return excelUtils.writeToExcel(rs,format,0,0,0);
					
			}finally{
					   if( rs != null)
			 	   			  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				     
			}	 			
		}
	public String getLocationid(String locationid) throws Exception{	
	    String sql="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID= '"+locationid+"' ";
	   // CommonMessage.debugMsg("The LocationId Is:::"+sql);
		return dbActionTemplate.getSingleValue(sql);
	}
	}