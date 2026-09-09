package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlSkillReviewpointmstDao;
import com.akranta.tpm.dao.sql.EntTlSkillReviewpointdetSql;
import com.akranta.tpm.dao.sql.EntTlSkillReviewpointmstSql;

import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillReviewpointdet;
import com.akranta.tpm.model.EntTlSkillReviewpointmst;
import com.akranta.tpm.service.api.FunctionCallApi;
//import com.akranta.tpm.service.api.SkillIndexServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlSkillReviewpointmstDaoImpl implements EntTlSkillReviewpointmstDao {


	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;
//	private SkillIndexServiceApi serviceApi;
	EntTlSkillReviewpointmstSql entTlSkillReviewpointmstSql = new EntTlSkillReviewpointmstSql();

	public EntTlSkillReviewpointmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void EntTlSkillReviewpointmstDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public EntTlSkillReviewpointmst create(EntTlSkillReviewpointmst entTlSkillReviewpointmst,EntTlSkillReviewpointdet entTlReviewPpointdet) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlSkillReviewpointmstSql entTlSkillReviewpointmstSql = new EntTlSkillReviewpointmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlSkillReviewpointdetSql entTlSkillReviewpointdetSql = new EntTlSkillReviewpointdetSql();
		
		entTlSkillReviewpointmst.setSirmKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillReviewpointmstSql.TBL_ENT_TL_SKILL_REVIEWPOINTMST)); // set the sequnce number 
		sqls.add(EntTlSkillReviewpointmstSql.getInsertSql(entTlSkillReviewpointmstSql.getSirmDbFields(), entTlSkillReviewpointmst.getSaveArray())); // add insert sql for master table						
		if(!CommonFunctions.isValidKeyId(entTlReviewPpointdet.getSirdKeyid()))	
		{
				CommonMessage.debugMsg("  newGenTlMommst10 " +  entTlReviewPpointdet.getSirdReviewpoint());// get detail info from list in Mommeeting object
				//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
				entTlReviewPpointdet.setSirdKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillReviewpointdetSql.TBL_ENT_TL_SKILL_REVIEWPOINTDET, 10, "SIRD", "MMYY", "Y"));
				CommonMessage.debugMsg("momd Key ID  ::::"+entTlReviewPpointdet.getSirdKeyid());
				entTlReviewPpointdet.setSirdSirmKeyid(entTlSkillReviewpointmst.getSirmKeyid());
				sqls.add(EntTlSkillReviewpointdetSql.getInsertSql(entTlSkillReviewpointdetSql.getSirdDbFields(), entTlReviewPpointdet.getSaveArray()));// add insert sql for detail table
	            CommonMessage.debugMsg("sql  " +sqls);	
        			            
		}
		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		 
		return entTlSkillReviewpointmst;
	}
	
	public EntTlSkillReviewpointmst update(EntTlSkillReviewpointmst entTlSkillReviewpointmst,EntTlSkillReviewpointdet entTlReviewPpointdet)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlSkillReviewpointmstSql entTlSkillReviewpointmstSql = new EntTlSkillReviewpointmstSql();
		EntTlSkillReviewpointdetSql entTlSkillReviewpointdetSql = new EntTlSkillReviewpointdetSql();
		
		sqls.add(EntTlSkillReviewpointmstSql.getUpdateSql(entTlSkillReviewpointmstSql.getSirmDbFields(), entTlSkillReviewpointmst.getSaveArray()));
		if(!CommonFunctions.isValidKeyId(entTlReviewPpointdet.getSirdKeyid()))
				{
					CommonMessage.debugMsg("  newGenTlMommst10 " +  entTlReviewPpointdet.getSirdReviewpoint());// get detail info from list in Mommeeting object
					//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
					entTlReviewPpointdet.setSirdKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillReviewpointdetSql.TBL_ENT_TL_SKILL_REVIEWPOINTDET, 10, "SIRD", "MMYY", "Y"));
					CommonMessage.debugMsg("momd Key ID  ::::"+entTlReviewPpointdet.getSirdKeyid());
					entTlReviewPpointdet.setSirdSirmKeyid(entTlSkillReviewpointmst.getSirmKeyid());
					sqls.add(EntTlSkillReviewpointdetSql.getInsertSql(entTlSkillReviewpointdetSql.getSirdDbFields(), entTlReviewPpointdet.getSaveArray()));// add insert sql for detail table
		            CommonMessage.debugMsg("sql  " +sqls);	
				}
		if(CommonFunctions.isValidKeyId(entTlReviewPpointdet.getSirdKeyid()))
		{
			
			entTlReviewPpointdet.setSirdSirmKeyid(entTlSkillReviewpointmst.getSirmKeyid());
			sqls.add(EntTlSkillReviewpointdetSql.getUpdateSql(entTlSkillReviewpointdetSql.getSirdDbFields(), entTlReviewPpointdet.getSaveArray()));// add insert sql for detail table
            CommonMessage.debugMsg("sql  " +sqls);
            
		}
	
		dbActionTemplate.executeStatements(sqls);
			
		
		return entTlSkillReviewpointmst;
	}
	
	public EntTlSkillReviewpointmst delete(EntTlSkillReviewpointmst entTlSkillReviewpointmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlSkillReviewpointmstSql entTlSkillReviewpointmstSql = new EntTlSkillReviewpointmstSql();
		EntTlSkillReviewpointdetSql entTlSkillReviewpointdetSql = new EntTlSkillReviewpointdetSql();
			sqls.add("Delete from " +entTlSkillReviewpointdetSql.TBL_ENT_TL_SKILL_REVIEWPOINTDET+" where SIRD_SIRM_KEYID='"+entTlSkillReviewpointmst.getSirmKeyid()+"'");
			sqls.add(entTlSkillReviewpointmstSql.getDeleteSql(entTlSkillReviewpointmstSql.getSirmDbFields(), entTlSkillReviewpointmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		return entTlSkillReviewpointmst;
	}

	@Override
	public List<String[]> getRpDetail(CommonFilter commonFilter,String mstkeyid) {
		
		try
		{
			List<String> params = new ArrayList<String>();
			String sql =entTlSkillReviewpointmstSql.RpDetailGrid(commonFilter,mstkeyid);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public EntTlSkillReviewpointmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +mstkeyid);
		EntTlSkillReviewpointmst newEntTlSkillReviewpointmst = new EntTlSkillReviewpointmst();		
		String sql = EntTlSkillReviewpointmstSql.getSkillRpSelectSql();				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { mstkeyid };
		newEntTlSkillReviewpointmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+newEntTlSkillReviewpointmst.getSirmKeyid());
		return newEntTlSkillReviewpointmst;
	}

	@Override
	public void DeleteRplist(String keyid) throws Exception, BusinessApplicationExceptions {
			List<String > sqls = new ArrayList<String>();
			//sqls.add(GenTlMomKpiLinkSql.DeleteMomRow(keyid));
			sqls.add( EntTlSkillReviewpointdetSql.DeleteRplist(keyid));
			dbActionTemplate.executeStatements(sqls);
		
	}

	@Override
	public List<String[]> getSIMainGrid(CommonFilter commonFilter, String reportName) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("SERVICE IMPL");
		//CommonMessage.debugMsg("SERVICE IMPL"+commonFilter.getGetCol());
		List<String> paramValues = new ArrayList<String>();	
		CommonMessage.debugMsg("CommonFilter.getFlid()==="+commonFilter.getFlid());
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		
		CommonMessage.debugMsg(condParms);
		if (UIUtils.isValidKeyId( commonFilter.getHrschkbox()))
		
				condParms+= "FORHEADER=Y;";
				
				
	
		//CommonMessage.debugMsg("SERVICE IMPL"+commonParams);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		CommonMessage.debugMsg("test to............");
		CommonMessage.debugMsg("SERVICE IMPL"+commonParams);
		
		CommonMessage.debugMsg("SERVICE IMPL"+commonParams);
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		List<String[]> dataList =null;
		//dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("GEN_TROUBLESHOOTING.ENT_FN_SKILL_ASSEMENTMAINVIEW", paramValues);

		if (!reportName.equals("REVIEW") )
			
			//dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_SKILL_ASSEMENTMAINVIEW", paramValues);
			
			dataList = fnCallApi.callFunction("ENT_FN_SKILL_ASSEMENTMAINVIEW_SB",paramValues,3,false);
			
		else
			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_SKILL_REVIEWPOINT", paramValues);

		
		
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg("out ");
		return dataList; 
	}
	@Override
	public EntTlSkillReviewpointmst createmst(EntTlSkillReviewpointmst newentTlSkillReviewpointmst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlSkillReviewpointmstSql entTlSkillReviewpointmstSql = new EntTlSkillReviewpointmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlSkillReviewpointdetSql entTlSkillReviewpointdetSql = new EntTlSkillReviewpointdetSql();
		try{
		
			newentTlSkillReviewpointmst.setSirmKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillReviewpointmstSql.TBL_ENT_TL_SKILL_REVIEWPOINTMST)); // set the sequnce number 
			sqls.add(EntTlSkillReviewpointmstSql.getInsertSql(entTlSkillReviewpointmstSql.getSirmDbFields(), newentTlSkillReviewpointmst.getSaveArray())); // add insert sql for master table
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}
		 
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return newentTlSkillReviewpointmst;
	}

	@Override
public EntTlSkillReviewpointmst updatemst(EntTlSkillReviewpointmst newentTlSkillReviewpointmst) throws Exception {
		List<String> sqls = new ArrayList<String>();
		EntTlSkillReviewpointmstSql entTlSkillReviewpointmstSql = new EntTlSkillReviewpointmstSql();
		
		
		sqls.add(EntTlSkillReviewpointmstSql.getUpdateSql(entTlSkillReviewpointmstSql.getSirmDbFields(), newentTlSkillReviewpointmst.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
		
		return newentTlSkillReviewpointmst;	
	 	}
	
	@Override
	public List<String[]> getSkillAssessmentReport(CommonFilter commonFilter)
			throws Exception {
		
		CommonMessage.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();
		String condParms=null;
		String condParms1=null;
		String condParms2=null;
		String condParms3=null;
		
		CommonMessage.debugMsg("commonFilter.getStartDate()"+commonFilter.getStartDate());
		if(UIUtils.isValidKeyId(commonFilter.getFlid()))
			condParms =commonFilter.getFlid();
		if (UIUtils.isValidKeyId(commonFilter.getEmmLinkKeyId()))
			condParms1 =commonFilter.getEmmLinkKeyId();	
		if (UIUtils.isValidKeyId(commonFilter.getStartDate()))
			condParms2 =commonFilter.getStartDate();	
		if (UIUtils.isValidKeyId(commonFilter.getEmpch()))
			condParms3 =commonFilter.getEmpch();
		
		paramValues.add(condParms); 
		paramValues.add(condParms1);
		paramValues.add(condParms2);
		paramValues.add(condParms3);
		
		CommonMessage.debugMsg(commonFilter.getFlid()+"  paramValues..11111111"+condParms);
		CommonMessage.debugMsg(commonFilter.getEmmLinkKeyId()+" paramValues..11111111"+condParms1);
		//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_SKILLINDEXREVIEW", paramValues);
		
		
		
		List<String[]> dataList =  fnCallApi.callMultiParamFunction("GEN_FN_SKILLINDEXREVIEW_SB", paramValues,4,false);
		CommonMessage.debugMsg("Printing through SPRINGBOOT");
		for(String[] arr:dataList) 
		{
			CommonMessage.debugMsg(Arrays.toString(arr));
		}
		
		
		
		
		return dataList;
	
		
		/*StringBuffer sql = new StringBuffer();
		sql.append("select  'Skill Index Assessment Sheet','Skill Index Assessment Sheet','Puneet','Puneet','Ravi','Ravi','Parush','Parush','Naidu','Naidu' from dual union all");
		sql.append(" SELECT 'Criteria','Review Point','','','','','','','',''  FROM DUAL " );
		sql.append(" union all SELECT SPOK_NAME,SIRD_REVIEWPOINT,'','','','','','','','' FROM  Ent_Tl_Skill_Reviewpointmst,Ent_Tl_Skill_Reviewpointdet,ENT_TL_SPOKEMST where SIRM_KEYID=SIRD_SIRM_KEYID(+) and  SIRM_SPOK_KEYID=SPOK_KEYID(+)");
		CommonMessage.debugMsg("sql...."+sql.toString());
		return dbActionTemplate.getDataList(sql.toString());*/
	}

	@Override
	public Workbook SkillIndexExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getSkillInexReport(commonFilter);
			CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
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
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}

	public String getRecallReivewPoints(String flid, String roleId) throws Exception {
		
		String sql = " SELECT SIRM_KEYID FROM ENT_TL_SKILL_REVIEWPOINTMST WHERE SIRM_FLID ='" + flid + "' ";
		sql+=   " AND SIRM_ROLE_KEYID='" + roleId + "' ";
		String keyId = dbActionTemplate.getSingleValue(sql);
		return keyId;
	}
	
	private ResultSet getSkillInexReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_SKILL_REVIEWPOINT", paramValues);
	}

	@Override
	public Workbook getskillIndexExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception 
			
	{
		
		ResultSet rs = null;
		 try
		 {
				rs =   getskillIndexExcel(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format,2,0,0 );
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
		}

	private ResultSet getskillIndexExcel(CommonFilter commonFilter) throws Exception 
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			CommonMessage.debugMsg("result");
			return dbActionTemplate.NewdbFunctionCall2("ENT_FN_SKILL_ASSEMENTMAINVIEW", paramValues);
		}

	private List<String> getFilterParamValues(CommonFilter commonFilter) 
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	}

		

	


