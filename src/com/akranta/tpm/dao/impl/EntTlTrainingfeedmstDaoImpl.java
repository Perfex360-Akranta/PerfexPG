package com.akranta.tpm.dao.impl;

import com.akranta.tpm.utils.CommonMessage;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlTrainingfeedmstDao;
import com.akranta.tpm.dao.sql.EntTlFeedbackformdtlSql;
import com.akranta.tpm.dao.sql.EntTlTrainingfeedmstSql;
import com.akranta.tpm.dao.sql.EntTlTrainingmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMomattendanceSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
//import com.akranta.tpm.dao.sql.GenTlVisualcntchecklistdtlSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlFeedbackformdtl;
import com.akranta.tpm.model.EntTlTrainingfeedmst;
import com.akranta.tpm.model.EntTlTrainingmst;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlTrainingfeedmstDaoImpl implements EntTlTrainingfeedmstDao 
 {


	private DBActionTemplate dbActionTemplate; 

	public EntTlTrainingfeedmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlTrainingfeedmst create(EntTlTrainingfeedmst entTlTrainingfeedmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTrainingfeedmstSql entTlTrainingfeedmstSql = new EntTlTrainingfeedmstSql();// contains dbtable,field names, Field types and related sqls  of master table
		EntTlFeedbackformdtlSql enFeedbackformdtlSql = new EntTlFeedbackformdtlSql();
		CommonMessage.debugMsg("ENT training Feee form Dao Impl ::;");
		try{
		
			entTlTrainingfeedmst.setTfmsKeyid(dbActionTemplate.getSequenceNumber(EntTlTrainingfeedmstSql.TBL_ENT_TL_TRAININGFEEDMST, 10, "TFMS", "MMYY", "Y")); // set the sequnce number 
			sqls.add(EntTlTrainingfeedmstSql.getInsertSql(entTlTrainingfeedmstSql.getTfmsDbFields(), entTlTrainingfeedmst.getSaveArray())); // add insert sql for master table			
			List<EntTlFeedbackformdtl> newEntTlFeedbackformdtls= entTlTrainingfeedmst.getFeedbackFormdeatils();
			List<EntTlFeedbackformdtl> newEntTlFeedbackformdtlsip= entTlTrainingfeedmst.getFeedbackFormdetailIp();
			if( newEntTlFeedbackformdtls != null && newEntTlFeedbackformdtls.size()> 0 && newEntTlFeedbackformdtlsip != null && newEntTlFeedbackformdtlsip.size()> 0)
			{	
				for( EntTlFeedbackformdtl entTlFeedbackformdtl : newEntTlFeedbackformdtls)
				{	
					CommonMessage.debugMsg("  newGenTlMommst1 " +  entTlFeedbackformdtl.getFbfdFbpmKeyid());// get detail info from list in Mommeeting object
					entTlFeedbackformdtl.setFbfdKeyid(dbActionTemplate.getSequenceNumber(enFeedbackformdtlSql.TBL_ENT_TL_FEEDBACKFORMDTL, 10, "FBFD", "MMYY", "Y"));
					CommonMessage.debugMsg("momd Key ID  ::::"+entTlFeedbackformdtl.getFbfdKeyid());
					entTlFeedbackformdtl.setFbfdTmstKeyid(entTlTrainingfeedmst.getTfmsKeyid());
					sqls.add(EntTlFeedbackformdtlSql.getInsertSql(enFeedbackformdtlSql.getFbfdDbFields(), entTlFeedbackformdtl.getSaveArray()));// add insert sql for detail table
		            CommonMessage.debugMsg("sql  " +sqls);	    
		        }
				for( EntTlFeedbackformdtl entTlFeedbackformdtl : newEntTlFeedbackformdtlsip)
				{	
					CommonMessage.debugMsg("  newGenTlMommst1 " +  entTlFeedbackformdtl.getFbfdFbpmKeyid());// get detail info from list in Mommeeting object
					//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
					entTlFeedbackformdtl.setFbfdKeyid(dbActionTemplate.getSequenceNumber(enFeedbackformdtlSql.TBL_ENT_TL_FEEDBACKFORMDTL, 10, "FBFD", "MMYY", "Y"));
					CommonMessage.debugMsg("momd Key ID  ::::"+entTlFeedbackformdtl.getFbfdKeyid());
					entTlFeedbackformdtl.setFbfdTmstKeyid(entTlTrainingfeedmst.getTfmsKeyid());
					sqls.add(EntTlFeedbackformdtlSql.getInsertSql(enFeedbackformdtlSql.getFbfdDbFields(), entTlFeedbackformdtl.getSaveArray()));// add insert sql for detail table
		            CommonMessage.debugMsg("sql  " +sqls);	    
		        }
		}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlTrainingfeedmst;
	}
	
	public EntTlTrainingfeedmst update(EntTlTrainingfeedmst entTlTrainingfeedmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlTrainingfeedmstSql entTlTrainingfeedmstSql = new EntTlTrainingfeedmstSql();
		EntTlFeedbackformdtlSql enFeedbackformdtlSql = new EntTlFeedbackformdtlSql();
		
		try {
			
			sqls.add(EntTlTrainingfeedmstSql.getUpdateSql(entTlTrainingfeedmstSql.getTfmsDbFields(), entTlTrainingfeedmst.getSaveArray()));
			List<EntTlFeedbackformdtl> entTlFeedbackformdtls= entTlTrainingfeedmst.getFeedbackFormdeatils();
			List<EntTlFeedbackformdtl> entTlFeedbackformdtlsIp= entTlTrainingfeedmst.getFeedbackFormdetailIp();
			
			CommonMessage.debugMsg("SQL ---------"+sqls.get(0));
			if( entTlFeedbackformdtls != null && entTlFeedbackformdtls.size()> 0 )
			{	
				//CommonMessage.debugMsg();
				for( EntTlFeedbackformdtl entTlFeedbackformdtl : entTlFeedbackformdtls)
				{					
					CommonMessage.debugMsg("entTlFeedbackformdtl.getFbfdKeyid()  "+entTlFeedbackformdtl.getFbfdKeyid() );
					if(UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdKeyid())&& UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdFbpmKeyid())&& UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdFermKeyid()) && UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdTmstKeyid())){
						sqls.add(enFeedbackformdtlSql.getUpdateSql(enFeedbackformdtlSql.getFbfdDbFields(), entTlFeedbackformdtl.getSaveArray()));
					}else if(UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdKeyid()) && !UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdFbpmKeyid()) && UIUtils.isValidKeyId( entTlFeedbackformdtl.getFbfdTmstKeyid())) {
						sqls.add(enFeedbackformdtlSql.getDeleteSql(enFeedbackformdtlSql.getFbfdDbFields(), entTlFeedbackformdtl.getSaveArray()));
					}				 
					else{						
						CommonMessage.debugMsg("  newGenTlMommst1 " +  entTlFeedbackformdtl.getFbfdKeyid());// get detail info from list in Mommeeting object
						entTlFeedbackformdtl.setFbfdKeyid(dbActionTemplate.getSequenceNumber(EntTlFeedbackformdtlSql.TBL_ENT_TL_FEEDBACKFORMDTL, 10, "FBFD", "MMYY", "Y"));
						CommonMessage.debugMsg("Fbfd Key ID  ::::"+entTlFeedbackformdtl.getFbfdKeyid());
						entTlFeedbackformdtl.setFbfdTmstKeyid(entTlTrainingfeedmst.getTfmsKeyid());
						sqls.add(enFeedbackformdtlSql.getInsertSql(enFeedbackformdtlSql.getFbfdDbFields(), entTlFeedbackformdtl.getSaveArray()));
						}							
					
				}
			}
			if( entTlFeedbackformdtlsIp != null && entTlFeedbackformdtlsIp.size()> 0 )
			{	
				
				for( EntTlFeedbackformdtl entTlFeedbackformdtl : entTlFeedbackformdtlsIp)
				{
					CommonMessage.debugMsg("entTlFeedbackformdtl.getFbfdKeyid()  "+entTlFeedbackformdtl.getFbfdKeyid() );
					if(UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdKeyid()) && UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdFbpmKeyid()) && UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdFermKeyid()) && UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdTmstKeyid())){
						sqls.add(enFeedbackformdtlSql.getUpdateSql(enFeedbackformdtlSql.getFbfdDbFields(), entTlFeedbackformdtl.getSaveArray()));
					}else if(UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdKeyid()) && !UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdFbpmKeyid()) && UIUtils.isValidKeyId(entTlFeedbackformdtl.getFbfdTmstKeyid())) {
						sqls.add(enFeedbackformdtlSql.getDeleteSql(enFeedbackformdtlSql.getFbfdDbFields(), entTlFeedbackformdtl.getSaveArray()));
					}
					else{						
						CommonMessage.debugMsg("  newGenTlMommst1 " +  entTlFeedbackformdtl.getFbfdKeyid());// get detail info from list in Mommeeting object
						entTlFeedbackformdtl.setFbfdKeyid(dbActionTemplate.getSequenceNumber(EntTlFeedbackformdtlSql.TBL_ENT_TL_FEEDBACKFORMDTL, 10, "FBFD", "MMYY", "Y"));
						CommonMessage.debugMsg("Fbfd Key ID  ::::"+entTlFeedbackformdtl.getFbfdKeyid());
						entTlFeedbackformdtl.setFbfdTmstKeyid(entTlTrainingfeedmst.getTfmsKeyid());
						sqls.add(enFeedbackformdtlSql.getInsertSql(enFeedbackformdtlSql.getFbfdDbFields(), entTlFeedbackformdtl.getSaveArray()));
						}		
					
				}
			}
		dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return entTlTrainingfeedmst;
	}
	
	public EntTlTrainingfeedmst delete(EntTlTrainingfeedmst entTlTrainingfeedmst)throws Exception 
	{

		CommonMessage.debugMsg("delete insert in DAOIMPL");
		List<String> sqls = new ArrayList<String>();
		EntTlTrainingfeedmstSql entTlTrainingfeedmstSql = new EntTlTrainingfeedmstSql();
		EntTlFeedbackformdtlSql entTlFeedbackformdtlSql = new EntTlFeedbackformdtlSql();
		EntTlFeedbackformdtl newEntTlFeedbackformdtl=new EntTlFeedbackformdtl();
		//List<GenTlMomdtl> genTlMomdtls= genTlMommst.getMomeetingDetail();      
       //CommonMessage.debugMsg("dao impl sql "+genTlMomdtls.size());
		CommonMessage.debugMsg("delete the detail table");
		CommonMessage.debugMsg("delete the detail table");
	    //genTlMomdtl.setMomdKeyid(genTlMommst.getMomsKeyid());
		sqls.add("Delete from "+entTlFeedbackformdtlSql.TBL_ENT_TL_FEEDBACKFORMDTL+" where  FBFD_TMST_KEYID ='"+ entTlTrainingfeedmst.getTfmsKeyid()+"'");
		
		sqls.add(EntTlTrainingfeedmstSql.getDeleteSql(entTlTrainingfeedmstSql.getTfmsDbFields(), entTlTrainingfeedmst.getSaveArray()));
			
		dbActionTemplate.executeStatements(sqls);			
		return entTlTrainingfeedmst;
	}

	@Override
	public EntTlTrainingfeedmst select(String tfmsKeyid) throws NoDataFoundException, SQLException, Exception
	{
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +tfmsKeyid);
		EntTlTrainingfeedmst newEntTlTrainingfeedmst = new EntTlTrainingfeedmst();		
		String sql = EntTlTrainingfeedmstSql.getTrainingSelectSql();				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { tfmsKeyid };
		newEntTlTrainingfeedmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+newEntTlTrainingfeedmst.getTfmsKeyid());
		return newEntTlTrainingfeedmst;
	}

	@Override
	public Workbook TrainingFeedbackExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getTrainingFeedbackReport(commonFilter);
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
			return excelUtils.writeToExcel(rs,format,1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}

	private ResultSet getTrainingFeedbackReport(CommonFilter commonFilter) throws Exception
	{
		
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return dbActionTemplate.dbFunctionCall("TEST_PC_TEST.ENT_FN_TRAININGFEEDBACK", paramValues);
	}

	@Override
	public List<String[]> getTrainingFebMain(CommonFilter commonFilter) throws Exception 
	{
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("test to............");		
		paramValues.add(condParms);
		paramValues.add(commonParams);		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGFEEDBACK", paramValues);
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}

	@Override
	public List<String[]> getTrainingGrid(CommonFilter commonFilter,String typeCp, String keyid) throws Exception
	{
		CommonMessage.debugMsg("TrainingGrid::::: ");
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("test to getTrainingGrid.....");		
		paramValues.add(condParms+"TYPECP="+typeCp+";MSTKEYID="+keyid+";");
		paramValues.add(commonParams);
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_FBFORMRATING", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}

	@Override
	public List<String[]> getTrainingGridIn(CommonFilter commonFilter,String typeIP, String keyid) throws Exception
	{
		
		CommonMessage.debugMsg("TrainingGrid::::: IP:::");
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("test to getTrainingGrid.....");		
		paramValues.add(condParms+"TYPECP="+typeIP+";MSTKEYID="+keyid+";");
		paramValues.add(commonParams);
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_FBFORMRATING", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}

	@Override
	public String getDesignation(String empId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> gettrainingfeedbackexcelview(String tfbfId,
			String format, String path, String flid, String user,String title,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
			CommonMessage.debugMsg(" User :: "+tfbfId+" flid :: "+flid+" user :: "+user);
			
			StringBuilder sql = new StringBuilder();
			
			sql.append(" select TFMS_KEYID,PROG_NAME,TO_CHAR(TFMS_DATE,'dd-Mon-yyyy'),TFMS_DURATION,FTYM_NAME,EMPM_NAME,DESG_NAME ");  
			sql.append(" from  ENT_TL_TRAININGFEEDMST,GEN_TL_DESIGNATIONMST,GEN_TL_EMPLOYEEMST,ENT_TL_FACULTYMST,ENT_TL_PROGRAMMST ");
			sql.append(" where TFMS_DESIGNATION=DESG_KEYID  AND TFMS_PARTICIPANTNAME=EMPM_KEYID AND TFMS_TITLE=PROG_KEYID AND FTYM_KEYID=TFMS_FACULTY ");
			sql.append(" AND TFMS_KEYID= '"+tfbfId+"' ");
							 
			CommonMessage.debugMsg(" sql... 12345678 "+sql);
		    
		    List<String[]>  traningfeedbackform  = dbActionTemplate.getDataList(sql.toString());
		    return traningfeedbackform;

	}

	@Override
	public List<String[]> gettrainingfeedbackgridexcelview(String tfbfId,
			String format, String path, String flid, String user, String title,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT  FBFD_KEYID  FBFDKEYID,FBPM_KEYID FBPMKEYID , FBPM_DESCRIPTION  CourseContent,TO_CHAR(DECODE(FERM_ORDER, 1,'a', '')) AS FER005, TO_CHAR(DECODE(FERM_ORDER, 2,'a', '')) AS FER004,");  
		sql.append(" TO_CHAR(DECODE(FERM_ORDER, 3,'a', '')) AS FER003, TO_CHAR(DECODE(FERM_ORDER, 4,'a', '')) AS FER002,  ");
		sql.append(" TO_CHAR(DECODE(FERM_ORDER, 5,'a', '')) AS FER001,3 AS DATAORDER ");
		sql.append(" FROM (SELECT LEVEL AS SLN, FBPM_KEYID, FBPM_DESCRIPTION ");
		sql.append(" FROM ENT_TL_FEEDBACKPARAMMST ");
		sql.append(" WHERE FBPM_TYPE = 'CP' ");
		sql.append(" START WITH FBPM_KEYID = FBPM_PARENTID ");
		sql.append(" CONNECT BY NOCYCLE PRIOR FBPM_KEYID = FBPM_PARENTID), ");
		sql.append(" ENT_TL_TRAININGFEEDMST, ENT_TL_FEEDBACK_RATINGMST,  ENT_TL_FEEDBACKFORMDTL ");
		sql.append(" WHERE ");
		sql.append(" FBPM_KEYID= FBFD_FBPM_KEYID (+) ");
		sql.append(" AND FERM_KEYID(+) = FBFD_FERM_KEYID ");
		sql.append(" AND TFMS_KEYID(+) = FBFD_TMST_KEYID ");
		sql.append(" AND FBFD_TMST_KEYID(+)='" + tfbfId + "' ORDER BY DATAORDER , FBPMKEYID ");

		CommonMessage.debugMsg(" sql... 12345678 "+sql);
	    
	    List<String[]>  traningfeedbackform  = dbActionTemplate.getDataList(sql.toString());
	    return traningfeedbackform;
	}

	@Override
	public List<String[]> gettrainingfeedbackscndgridexcelview(String tfbfId,
			String format, String path, String flid, String user, String title,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
        
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT  FBFD_KEYID  FBFDKEYID,FBPM_KEYID FBPMKEYID , FBPM_DESCRIPTION  CourseContent ,");  
		sql.append(" TO_CHAR(DECODE(FERM_ORDER, 1,'a', '')) AS FER005, TO_CHAR(DECODE(FERM_ORDER, 2,'a', '')) AS FER004, TO_CHAR(DECODE(FERM_ORDER, 3,'a', '')) AS FER003, TO_CHAR(DECODE(FERM_ORDER, 4,'a', '')) AS FER002, TO_CHAR(DECODE(FERM_ORDER, 5,'a', '')) AS FER001,3 AS DATAORDER ");
		sql.append(" FROM (SELECT LEVEL AS SLN, FBPM_KEYID, FBPM_DESCRIPTION FROM ENT_TL_FEEDBACKPARAMMST ");
		sql.append(" WHERE FBPM_TYPE = 'IP' START WITH FBPM_KEYID = FBPM_PARENTID ");
		sql.append(" CONNECT BY NOCYCLE PRIOR FBPM_KEYID = FBPM_PARENTID),ENT_TL_TRAININGFEEDMST, ENT_TL_FEEDBACK_RATINGMST,  ENT_TL_FEEDBACKFORMDTL ");
		sql.append(" WHERE FBPM_KEYID= FBFD_FBPM_KEYID (+) AND FERM_KEYID(+) = FBFD_FERM_KEYID ");
		sql.append(" AND TFMS_KEYID(+) = FBFD_TMST_KEYID ");
		sql.append(" AND FBFD_TMST_KEYID(+)='" + tfbfId + "' ORDER BY DATAORDER , FBPMKEYID ");

		CommonMessage.debugMsg(" sql... 12345678 "+sql);
	    
	    List<String[]>  traningfeedbackform  = dbActionTemplate.getDataList(sql.toString());
	    return traningfeedbackform;
	}
	
}

