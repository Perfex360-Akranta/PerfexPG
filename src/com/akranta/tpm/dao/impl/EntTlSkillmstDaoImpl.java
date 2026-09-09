package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.bind.ParseConversionEvent;

import net.sf.json.JSONObject;

import org.apache.poi.ss.formula.ptg.TblPtg;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.taglibs.standard.lang.jpath.adapter.Convert;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.EntTlSkillmstDao;
import com.akranta.tpm.dao.sql.EntTlSkillmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
/* dao implementation */
public class EntTlSkillmstDaoImpl implements EntTlSkillmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlSkillmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception
	{
		try
		{
			String sql = "select  fnln_originalid,fnln_elementid,fnln_parentid,fnln_elementtype,NEWDISPLAYCODE";
				   //sql += " from GEN_TL_FUNCTIONALLOCN,FTL_VW_LAYOUTDISPLAYCODE where fnln_originalid = originalid(+) ";
				   sql += " from gen_vw_funclocndept,FTL_VW_LAYOUTDISPLAYCODE where fnln_originalid = originalid(+) ";
				   
			if( functionalLocn.getElementId().equals("1"))
				sql +=	"and fnln_elementtype ='CMP' ";
			else
				sql +=	"and fnln_parentid ='"+functionalLocn.getElementId() +"' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
				
			sql +=" and fnln_active='Y'";
			CommonMessage.debugMsg(" sql " + sql);
			List resultList = dbActionTemplate.getDataList(sql);
			CommonMessage.debugMsg(" resultList size " + resultList.size());
			return fillLocation(resultList);
		}
		catch (Exception e)
		{			
			throw new Exception(e.getMessage()); 			
		}
		
	}
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
			   menus.add(fl);			   
		   }
  		  return menus;
	 }
	
	public List<EntTlSkillmst> getEntTlSkillmstValues(String factId,String DeptId) throws Exception
	{				
		StringBuffer sql= new StringBuffer();
		sql.append(" SELECT SKIL_KEYID,SKIL_CODE,SKIL_NAME,SKIL_PARENTID,SKIL_ISCHILD,SKIL_FACT_KEYID,SKIL_EVALUATIONTYPEID, " );
		sql.append(" SKIL_REMARKS,SKIL_EFFECTIVE_DATE,SKIL_INACTIVE_DATE,SKIL_TEMPFIELD1,SKIL_TEMPFIELD2,SKIL_TEMPFIELD3, " );
		sql.append(" SKIL_TEMPFIELD4,SKIL_TEMPFIELD5,SKIL_ACTIVE,SKIL_CREATEDBY,SKIL_CREATEDON,SKIL_MODIFIEDON " );
		sql.append(" FROM " + TableNames.TBL_ENT_TL_SKILLMST );
		sql.append(" WHERE 1=1 ");
		sql.append(" AND SKIL_ACTIVE='Y' ");
		sql.append(" AND SKIL_KEYID=SKIL_PARENTID ");	
		
		if (CommonFunctions.isValidKeyId(factId)){
			sql.append(" AND SKIL_FACT_KEYID='" + factId + "'");
		}
		if (CommonFunctions.isValidKeyId(DeptId)){
			sql.append(" AND SKIL_DEPT_KEYID='" + DeptId + "'");
		}
		sql.append(" ORDER BY SKIL_PARENTID,SKIL_KEYID " );		
		CommonMessage.debugMsg(" sql " + sql.toString());
	
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		return fillSkillmstList(resultList);		
	}

	public List<EntTlSkillmst> getAllSkill(EntTlSkillmst entTlSkillmst) throws Exception
	{		
		StringBuffer sql= new StringBuffer();
		sql.append(" SELECT SKIL_KEYID,SKIL_CODE,SKIL_NAME,SKIL_PARENTID,SKIL_ISCHILD,SKIL_FACT_KEYID,SKIL_EVALUATIONTYPEID, " );
		sql.append(" SKIL_REMARKS,SKIL_EFFECTIVE_DATE,SKIL_INACTIVE_DATE,SKIL_TEMPFIELD1,SKIL_TEMPFIELD2,SKIL_TEMPFIELD3, " );
		sql.append(" SKIL_TEMPFIELD4,SKIL_TEMPFIELD5,SKIL_ACTIVE,SKIL_CREATEDBY,SKIL_CREATEDON,SKIL_MODIFIEDON " );
		sql.append(" FROM " + TableNames.TBL_ENT_TL_SKILLMST );
		sql.append(" WHERE 1=1 ");
		sql.append(" AND SKIL_ACTIVE='Y' ");		
		sql.append(" AND SKIL_KEYID<>SKIL_PARENTID ");
		
		if (CommonFunctions.isValidKeyId(entTlSkillmst.getSkilParentid())){
			sql.append(" AND SKIL_PARENTID='" + entTlSkillmst.getSkilParentid() + "'");
		}
		
		if (CommonFunctions.isValidKeyId(entTlSkillmst.getSkilFactKeyid())){
			sql.append(" AND SKIL_FACT_KEYID='" + entTlSkillmst.getSkilFactKeyid() + "'");
		}
		
		sql.append(" ORDER BY SKIL_PARENTID,SKIL_KEYID " );
		
		CommonMessage.debugMsg(" sql " + sql.toString());
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());		
		return fillSkillmstList(resultList);		
	}
	public int getSkillLevel(EntTlSkillmst entTlSkillmst)throws Exception
	{		
		String sql;
		int menuLevel = 0;
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql(); 	
		sql=entTlSkillmstSql.getSkillLevelSql(entTlSkillmstSql.getSkilDbFields(), entTlSkillmst.getSaveArray());
		CommonMessage.debugMsg(" sql " + sql);
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
		 
		for( String [] row : resultList )
	    {			  
			 menuLevel= Integer.parseInt( row[0]);			   		   
	    }
		return menuLevel;
	}
	public int getConfigSkillLevel()throws Exception
	{		
		String sql;
		int menuLevel = 0;
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql(); 	
		sql=entTlSkillmstSql.getConfigSkillLevel();
		CommonMessage.debugMsg(" sql " + sql);
		
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
		
		for( String [] row : resultList )
	    {			  
			 menuLevel=Integer.parseInt( row[0]);			   		   
	    }
		return menuLevel;
	}
	public EntTlSkillmst create(EntTlSkillmst entTlSkillmst) 	throws BusinessApplicationExceptions, Exception 
	{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql(); 		
		try{		
			entTlSkillmst.setSkilKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_SKILLMST)); // set the sequnce number
			
			if(!CommonFunctions.isValidKeyId(entTlSkillmst.getSkilParentid()))			
				entTlSkillmst.setSkilParentid(entTlSkillmst.getSkilKeyid());					
			
			else			
				sqls.add("update " + TableNames.TBL_ENT_TL_SKILLMST + " set SKIL_ISCHILD='N' where SKIL_KEYID='" + entTlSkillmst.getSkilParentid() +  "' " );			
			
			sqls.add(EntTlSkillmstSql.getInsertSql(entTlSkillmstSql.getSkilDbFields(), entTlSkillmst.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls			
		}		
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlSkillmst;
	}
	
	public EntTlSkillmst update(EntTlSkillmst entTlSkillmst)	throws BusinessApplicationExceptions,Exception 
	{ 		
		List<String> sqls = new ArrayList<String>();
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql();
		try {
			sqls.add(entTlSkillmstSql.getUpdateSql(entTlSkillmstSql.getSkilDbFields(), entTlSkillmst.getSaveArray()));			
			dbActionTemplate.executeStatements(sqls);	
			return entTlSkillmst;
		} 
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}		
	}
	
	
	public List<String []> getParentElem(String elemId) throws Exception
	{
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql();
		String[] elemArr = elemId.split("-");
		String layout = elemArr[elemArr.length-1].substring(0, 3);
		String sql = null;	
		return dbActionTemplate.getDataList(sql);
	}
	
	public List<String []> getChildElem(List<String> childElem,String formField,String start,String end) throws Exception
	{
		String sql = null;
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql();
		if(childElem.size() > 0 )
		{
			//sql = entTlSkillmstSql.getChildSql(childElem.size(),childElem.get(0).substring(0, 3),start,end);
		}
		else
		{
			//sql = entTlSkillmstSql.getAllChildSql(formField,start,end);		
			com.akranta.tpm.utils.CommonMessage.debugMsg("All Chlid Sql "+sql);		
			return dbActionTemplate.getDataList(sql);
		}
		CommonMessage.debugMsg("Sql -->"+sql);
		
		return dbActionTemplate.getDataList(sql, childElem);
	}
	
	public String getTotalCount(List<String> childElem,String formfield) throws Exception
	{
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql();
		String sql = null;
		if(childElem.size() > 0 )
		{
			//sql = entTlSkillmst.getTotalChildSql(childElem.size(),childElem.get(0).substring(0, 3));
		}
		else
		{
				
			com.akranta.tpm.utils.CommonMessage.debugMsg("All Chlid Sql "+sql);
			return dbActionTemplate.getSingleValue(sql);
		}
		CommonMessage.debugMsg("Sql -->"+sql);
		
		List<String[]> dataList =  dbActionTemplate.getDataList(sql,childElem);
		String returnCount = null;
		
		if(dataList.size() >0)
			returnCount = dataList.get(0)[0];
		return returnCount;
	}
	

	private List<EntTlSkillmst> fillSkillmstList(List<String []> resultList) throws SQLException
	{
		   List<EntTlSkillmst> menus = new ArrayList<EntTlSkillmst>();
		   for( String [] row : resultList )
		   {			  
			   EntTlSkillmst entTlSkillmst = new EntTlSkillmst();
			   entTlSkillmst.setSkilKeyid(row[0]);
			   entTlSkillmst.setSkilCode(row[1]);
			   entTlSkillmst.setSkilName(row[2]);	
			   entTlSkillmst.setSkilParentid(row[3]);
			   entTlSkillmst.setSkilIschild(row[4]);
			   entTlSkillmst.setSkilFactKeyid(row[5]);
			   entTlSkillmst.setSkilEvaluationtypeid(row[6]);
			   entTlSkillmst.setSkilRemarks(row[7]);
			   entTlSkillmst.setSkilEffectiveDate(row[8]);
			   entTlSkillmst.setSkilInactiveDate(row[9]);			   
			   menus.add(entTlSkillmst);			   
		   }
  		  return menus;
	 }
	
	public EntTlSkillmst delete(EntTlSkillmst entTlSkillmst)throws BusinessApplicationExceptions,Exception {
		List<String> sqls = new ArrayList<String>();
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql();
		try {
			sqls.add(entTlSkillmstSql.getDeleteSql(entTlSkillmstSql.getSkilDbFields(), entTlSkillmst.getSaveArray()));			
			dbActionTemplate.executeStatements(sqls);				
			return entTlSkillmst;
			
		}catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}	
		
	}

	@Override
	public EntTlSkillmst select(EntTlSkillmst entTlSkillmst) throws Exception 
	{
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql();
		String sql = entTlSkillmstSql.getSelectSql(entTlSkillmstSql.getSkilDbFields(), entTlSkillmst.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		
		Object [] args =  new Object [] {};
		entTlSkillmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return entTlSkillmst;
	}
	
	
	public List<EntTlSkillmst> selectList(EntTlSkillmst entTlSkillmst)throws Exception 
	{
		EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql();
		String sql = entTlSkillmstSql.getSelectSql(entTlSkillmstSql.getSkilDbFields(), entTlSkillmst.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL : "+sql);
	
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		return fillSkillmstList(resultList);		
	}
	
	
	public  List<String[]> getSearchNode(String searchNode,String originalId) throws Exception
	{
		try
		{		
			EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql();
			String sql = entTlSkillmstSql.getSearchNodeSql(searchNode,originalId);
			CommonMessage.debugMsg("Search SQL : "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	public  List<String[]> getSearchSkillLevel(String originalId) throws Exception
	{
		try
		{		
			EntTlSkillmstSql entTlSkillmstSql = new EntTlSkillmstSql();
			String sql = entTlSkillmstSql.getSearchSkillLevelSql(originalId);
			CommonMessage.debugMsg("Search SQL : "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public List<String[]> getSkillLevelInvent(CommonFilter commonFilter)throws Exception 
	{
		try
		{
			List<String> paramValues = getFilterParamValues(commonFilter);			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_SKILLLEVELINVENTORY", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt+"--"+paramValues.get(0));
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));				
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage());			
		}
	}

	
	public Workbook skillLevelInventoryExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception 
	{
		ResultSet rs = null;
		   try{			
				rs =   getskillLevelInventoryReportResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colModel);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condFormatA = new XLConditionalFormats();
				condFormatA.setFontColor(new RGB(252,10,54)); 
				condFormatA.setFontName(XLConditionalFormats.FONT_DEFAULT);
				condFormatA.setFontHeightPoint((short)8);
				condFormatA.setFontBoldWeight((short)10);
				condFormatA.setFromCol(0);
				condFormatA.setToCol(-1);
				condFormatA.setOperator(ComparisonOperator.EQUAL);
				condFormatA.setCondValue("A"); 
				condFormatA.setSymbolStr("Actual");
				condFormatA.setIdentfier("A");
				condFormats.add(condFormatA);
				
				XLConditionalFormats condFormatB = new XLConditionalFormats();
				condFormatB.setFontColor(new RGB(7,244,54)); 
				condFormatB.setFontName(XLConditionalFormats.FONT_DEFAULT);
				condFormatB.setFontHeightPoint((short)8);
				condFormatB.setFontBoldWeight((short)10);
				condFormatB.setFromCol(0);
				condFormatB.setToCol(-1);
				condFormatB.setOperator(ComparisonOperator.EQUAL);
				condFormatB.setCondValue("B");
				condFormatB.setSymbolStr("Desired");
				condFormatB.setIdentfier("B");
				condFormats.add(condFormatB);
				
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getskillLevelInventoryReportResultSet(CommonFilter commonFilter) throws Exception 
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_SKILLLEVELINVENTORY", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter)
	{
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	

	
	public void getAllSkillLevel(List<String[]> skillLevel) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append("SELECT SKLM_LEVEL||'-'||SKLM_DESCRIPTION  FROM "+TableNames.TBL_ENT_TL_SKILL_LEVELMST+" ORDER BY SKLM_LEVEL");
		dbActionTemplate.populateDataList(sql.toString(),skillLevel);
		CommonMessage.debugMsg("Sql: " +sql.toString());		
			
		
	}

	@Override
	public List<String[]> getResource() throws Exception {
StringBuffer sql=new StringBuffer();
sql.append("select 'Employee','UP 1','UP 2','UP 3','UP 4','UP 5','UP 6','UP 7','UP 8','UP 9','UP 10'"
		+ " from dual "
		+ " union all "

		+ " select 'Employee 1','400','','80','','','','','','',''"
		+ " from dual  "
		+ " union all"
		+ " select 'Employee 2','','','200','','100','','180','','',''"
		+ " from dual"

		+ " union all"
		+ " select 'Employee 3','80','200','','','','','','','','' "
		+ " from dual"
		+ " union all"
		+ " select 'Employee 4','480','0','','','','','','','',''"
		+ " from dual"
		+ " union all"
		+ " select 'Employee 5','','240','','240','','','','','',''"
		+ " from dual"
		+ " union all"
		+ " select 'Employee 6','','','','','','','','','',''"
		+ " from dual"
		+ " union all"
		+ " select 'Employee 7','','','','','','240','','240','',''"
		+ " from dual"
		+ " union all"
		+ " select 'Employee 8','','','','0','240','','240','','',''"
		+ " from dual"
		+ " union all"
		+ " select 'Employee 9','400','80','','','','','','','',''"
		+ " from dual"
		+ " union all"
		+ " select 'Employee 10','','','','0','0','','','','','480'"
		+ " from dual" );
		
CommonMessage.debugMsg("sql..." + sql);
List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
return gridData;
	}
	
	
}

