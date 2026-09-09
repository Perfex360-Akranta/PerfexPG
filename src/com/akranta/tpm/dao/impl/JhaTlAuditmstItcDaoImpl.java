package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.JhaTlAuditmstItcDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.JhaTlAuditdtlSql;
import com.akranta.tpm.dao.sql.JhaTlAuditmstSql;
import com.akranta.tpm.dao.sql.JhaTlAuditparameterSql;
import com.akranta.tpm.dao.sql.JhaTlTemplatelevellinkSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditdtl;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAuditparameter;
import com.akranta.tpm.model.JhaTlTemplatelevellink;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class JhaTlAuditmstItcDaoImpl implements JhaTlAuditmstItcDao {


	private DBActionTemplate dbActionTemplate; 
	
	
	private JHAuditSheetCreationItcServiApi jhauditSheetCreationItcServiApi;
	FunctionCallApi fnCallApi;

	public JhaTlAuditmstItcDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	
	public void JhaTlAuditmstItcDaoImplJwt(String JwtToken) 
	{
		try{
			jhauditSheetCreationItcServiApi = new JHAuditSheetCreationItcServiApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public JhaTlAuditmst create(JhaTlAuditmst jhaTlAuditmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		List<String> sql = new ArrayList<String>();
		JhaTlAuditmstSql jhaTlAuditmstSql = new JhaTlAuditmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			
			String elementId = jhaTlAuditmst.getElementid();
		 	String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,JhaTlAuditmstSql.TBL_JHA_TL_AUDITMST);
			
			jhaTlAuditmst.setJhamKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 10, "JH", "DDMM", "Y"));  
			//jhaTlAuditmst.setJhamKeyid(dbActionTemplate.getSequenceNumber(JhaTlAuditmstSql.TBL_JHA_TL_AUDITMST, 10, "JHM", "DDMM", "Y"));  
			sqls.add(JhaTlAuditmstSql.getInsertSql(jhaTlAuditmstSql.getJhamDbFields(), jhaTlAuditmst.getSaveArray())); // add insert sql for master table
			insertAuditDtl(jhaTlAuditmst,sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
			String count =JhaTlAuditmstSql.getcountjhStep(jhaTlAuditmst.getJhamFlid());
			count=dbActionTemplate.getSingleValue(count);
			
			if(count.equals("0")){
				String checkPass =JhaTlAuditmstSql.getPassSql(jhaTlAuditmst.getJhamFlid());
				checkPass=dbActionTemplate.getSingleValue(checkPass);
				int passVal = Integer.parseInt(checkPass);				
				String jhStep =JhaTlAuditmstSql.getjhStepKeyIdsql(jhaTlAuditmst.getJhamFlid());
				String jhId =dbActionTemplate.getSingleValue(jhStep);
				if(UIUtils.isValidKeyId(jhId) && (passVal<=0)){
					sql.add(JhaTlAuditmstSql.updateMachineMst(jhId,jhaTlAuditmst.getJhamFlid()));
					dbActionTemplate.executeStatements(sql);
				}
			}		
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditmst;
	}
	private List<String> insertAuditDtl(JhaTlAuditmst jhaTlAuditmst,List<String> sqls) throws Exception {
		JhaTlAuditdtlSql jhaTlAuditdtlSql = new JhaTlAuditdtlSql();
		CommonMessage.debugMsg("jhaTlAuditdtl.get="+jhaTlAuditmst.getAuditDtl());
		if(jhaTlAuditmst.getAuditDtl()!= null && jhaTlAuditmst.getAuditDtl().size()>0) // check for detail table data
		{
			CommonMessage.debugMsg("jhaTlAuditdtl="+jhaTlAuditmst.getAuditDtl());			
	    	for(int i =0;i<jhaTlAuditmst.getAuditDtl().size();i++)
			{	
	    		JhaTlAuditdtl jhaTlAuditdtl = (JhaTlAuditdtl)jhaTlAuditmst.getAuditDtl().get(i); // get detail info from list in empployee object
	    		CommonMessage.debugMsg("jhaTlAuditdtl.getJhadKeyid()222222"+jhaTlAuditdtl.getJhadKeyid());
	    		if(jhaTlAuditdtl.getJhadRemarks()==null){
	    			jhaTlAuditdtl.setJhadRemarks("{}");
				}
				if(jhaTlAuditdtl.getJhadActive()==null){
					jhaTlAuditdtl.setJhadActive(jhaTlAuditmst.getJhamActive());
				}
				if(jhaTlAuditdtl.getJhadCreatedby()==null){
					jhaTlAuditdtl.setJhadCreatedby(jhaTlAuditmst.getJhamCreatedby());
				}
				if(jhaTlAuditdtl.getJhadCreatedon()==null){
					jhaTlAuditdtl.setJhadCreatedon(jhaTlAuditmst.getJhamCreatedon());
				}
				if(jhaTlAuditdtl.getJhadModifiedon()==null){
					jhaTlAuditdtl.setJhadModifiedon(jhaTlAuditmst.getJhamModifiedon());
				}
				jhaTlAuditdtl.setJhadJhauditmasterid(jhaTlAuditmst.getJhamKeyid());	
				if(!CommonFunctions.isValidKeyId(jhaTlAuditdtl.getJhadKeyid())){
					jhaTlAuditdtl.setJhadKeyid(dbActionTemplate.getSequenceNumber(jhaTlAuditdtlSql.TBL_JHA_TL_AUDITDTL, 12, "JHD", "DDMM", "Y"));
					sqls.add(JhaTlAuditdtlSql.getInsertSql(jhaTlAuditdtlSql.getJhadDbFields(), jhaTlAuditdtl.getSaveArray()));// add insert sql for detail table
				}else{
					CommonMessage.debugMsg("jhaTlAuditdtl.getJhadKeyid()"+jhaTlAuditdtl.getJhadKeyid());
					sqls.add(JhaTlAuditdtlSql.getUpdateSql(jhaTlAuditdtlSql.getJhadDbFields(), jhaTlAuditdtl.getSaveArray()));
				}
			}
		}
		return sqls;
	}
	public JhaTlAuditmst update(JhaTlAuditmst jhaTlAuditmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		JhaTlAuditmstSql jhaTlAuditmstSql = new JhaTlAuditmstSql();
		try {

			sqls.add(JhaTlAuditmstSql.getUpdateSql(jhaTlAuditmstSql.getJhamDbFields(), jhaTlAuditmst.getSaveArray()));
			insertAuditDtl(jhaTlAuditmst,sqls);
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return jhaTlAuditmst;
	}
	
	public JhaTlAuditmst delete(JhaTlAuditmst jhaTlAuditmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		
		JhaTlAuditmstSql jhaTlAuditmstSql = new JhaTlAuditmstSql();
		JhaTlAuditdtlSql jhaTlAuditdtlSql = new JhaTlAuditdtlSql();
		try {
			List<JhaTlAuditdtl> jhaTlAuditdtlList= jhaTlAuditmst.getAuditDtl();
			
			if( jhaTlAuditdtlList != null && jhaTlAuditdtlList.size()> 0 )
			{	
				for( JhaTlAuditdtl jhaTlAuditdtl : jhaTlAuditdtlList ){
					jhaTlAuditdtl.setJhadJhauditmasterid(jhaTlAuditmst.getJhamKeyid());
					//String dtlId = dbActionTemplate.getSingleValue(JhaTlAuditdtlSql.TBL_JHA_TL_AUDITDTL,"JHAD_KEYID", "JHAD_JHAUDITMASTERID", jhaTlAuditmst.getJhamKeyid());
					jhaTlAuditdtl.setJhadJhauditmasterid(jhaTlAuditmst.getJhamKeyid());
					sqls.add(JhaTlAuditdtlSql.getDeleteSql(jhaTlAuditdtlSql.getJhadDbFields(),jhaTlAuditdtl.getSaveArray()));
				}
				
			}
			sqls.add(JhaTlAuditmstSql.getDeleteSql(jhaTlAuditmstSql.getJhamDbFields(), jhaTlAuditmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditmst;
	}
	public List<String[]> getjhAuditGridSql(JhaTlAuditmst jhaTlAuditmst,String templateId,String jhamKeyID,String auditType,String jhstepid )throws Exception
	{
		try
		{                          
			//String sql = JhaTlAuditmstSql.getjhAuditGridSql(jhaTlAuditmst,machineId,jhamKeyID);
			StringBuffer sql=new StringBuffer();
			//if(CommonFunctions.isValidKeyId(jhamKeyID)){
			/*
			 * sql.
			 * append("  SELECT  JAUT_KEYID,JAUT_REVIEWPTSLNO, JAUT_PARAMETERNAME, JAUT_CRITERIASLNO AS ACTUALSCORE, JAUT_PARAMETERDESCRIPTION,JAUT_EVIDENCE, JAUT_MAXIMUMPOINTS , "
			 * ); sql.
			 * append(" '' AS PARAMETERID, '' AS PARAMETERNAME,JHAD_POINTSSCORED,JHAD_REMARKS JHAD_REMARKS,JHAD_KEYID ,  "
			 * ); sql.
			 * append(" JHAD_NCREMARKS as NC_remarks,JHAD_NCACTIONPLAN as AtionPlan ,JHAD_NCACTIONPLAN as AtionPlanKeyId, "
			 * ); sql.
			 * append(" DECODE (jhad_ncstatus, 'C', 'Completed', 'P', 'Pending') AS status,"
			 * );
			 * sql.append(" DECODE (jhad_ncclosed, 'C', 'Yes', 'P', 'No') AS nc_closed    "
			 * ); sql.
			 * append("FROM   JHA_TL_AUDITDTL,JHA_TL_AUDITTEMPLATE WHERE   JAUT_ACTIVE = 'Y' AND JHAD_PARAMETERID(+)=JAUT_KEYID "
			 * ); if(CommonFunctions.isValidKeyId(jhamKeyID)){
			 * sql.append(" AND JHAD_JHAUDITMASTERID(+)= '"+jhamKeyID+"'"); } else{
			 * sql.append(" AND JHAD_JHAUDITMASTERID(+)= ''"); }
			 * if(CommonFunctions.isValidKeyId(templateId)){
			 * sql.append(" AND JAUT_MASTERID= '"+templateId+"'"); }
			 * 
			 * sql.
			 * append(" ORDER BY TO_NUMBER(JAUT_REVIEWPTSLNO),TO_NUMBER(JAUT_CRITERIASLNO)  "
			 * ) ;
			 */
				 
				//changes by sriram 2
				sql.append(" SELECT t.JAUT_KEYID, t.JAUT_REVIEWPTSLNO, t.JAUT_PARAMETERNAME, ");
				sql.append(" t.JAUT_CRITERIASLNO AS ACTUALSCORE, t.JAUT_PARAMETERDESCRIPTION, t.JAUT_EVIDENCE, t.JAUT_MAXIMUMPOINTS, ");
				sql.append(" '' AS PARAMETERID, '' AS PARAMETERNAME, ");
				sql.append(" d.JHAD_POINTSSCORED, d.JHAD_REMARKS, d.JHAD_KEYID, ");
				sql.append(" d.JHAD_NCREMARKS AS NC_remarks, d.JHAD_NCACTIONPLAN AS AtionPlan, d.JHAD_NCACTIONPLAN AS AtionPlanKeyId, ");
				sql.append(" CASE d.jhad_ncstatus WHEN 'C' THEN 'Completed' WHEN 'P' THEN 'Pending' END AS status, ");
				sql.append(" CASE d.jhad_ncclosed WHEN 'C' THEN 'Yes' WHEN 'P' THEN 'No' END AS nc_closed ");
				sql.append(" FROM JHA_TL_AUDITTEMPLATE t ");
				sql.append(" LEFT JOIN JHA_TL_AUDITDTL d ON d.JHAD_PARAMETERID = t.JAUT_KEYID ");

				if(CommonFunctions.isValidKeyId(jhamKeyID)){
				    sql.append(" AND d.JHAD_JHAUDITMASTERID = '" + jhamKeyID + "' ");
				} else {
				    sql.append(" AND d.JHAD_JHAUDITMASTERID IS NULL ");   // ✅ Postgres equivalent of = ''
				}

				if(CommonFunctions.isValidKeyId(templateId)){
				    sql.append(" WHERE t.JAUT_ACTIVE = 'Y' AND t.JAUT_MASTERID = '" + templateId + "' ");
				} else {
				    sql.append(" WHERE t.JAUT_ACTIVE = 'Y' ");
				}

				sql.append(" ORDER BY CAST(t.JAUT_REVIEWPTSLNO AS INTEGER), CAST(t.JAUT_CRITERIASLNO AS INTEGER) ");

			/*}
			else if(!(CommonFunctions.isValidKeyId(jhamKeyID)) && CommonFunctions.isValidKeyId(flId)){
				sql.append("  SELECT  JAUT_KEYID,JAUT_REVIEWPTSLNO, JAUT_PARAMETERNAME,JAUT_CRITERIASLNO,  JAUT_PARAMETERDESCRIPTION,JAUT_EVIDENCE, JAUT_MAXIMUMPOINTS ,  ");				
				sql.append(" '' AS PARAMETERID, '' AS PARAMETERNAME");				
				sql.append(" ,'' JHAD_POINTSSCORED,'' JHAD_REMARKS,'' as JhadKeyid ,  '' as NC_remarks,'' as AtionPlan,'' as AtionPlanKeyId,'' as Status,' ' as NC_Closed   ");				
				sql.append(" FROM   JHA_TL_AUDITTEMPLATE ");				
				sql.append("	WHERE   JAUT_ACTIVE = 'Y'  AND JAUT_MASTERID = '"+templateId+"'");
				sql.append(" ORDER BY TO_NUMBER(JAUT_REVIEWPTSLNO),TO_NUMBER(JAUT_CRITERIASLNO)  " ) ;
			}*/
			CommonMessage.debugMsg("templateId :"+templateId);		
			CommonMessage.debugMsg("getjhAuditGridSql sql:"+sql);			
			return dbActionTemplate.getDataList(sql.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public List<String[]> getgradeid(String point,String keyId)throws Exception
	{
		try
		{                          
			
			String sql = JhaTlAuditmstSql.getgradeidsql(point,keyId);
			CommonMessage.debugMsg("grade sql..."+sql);
			List< String[]> gradeList = dbActionTemplate.getDataList(sql);
			//String jnnj = dbActionTemplate.getSingleValue(sql);
			CommonMessage.debugMsg("...."+gradeList.size());
					
			return gradeList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public List<String[]> getMinPoints(String parameter,String auditTeam)throws Exception
	{
		try
		{                          
			String sql = JhaTlAuditmstSql.getminPointssql(parameter,auditTeam);
			CommonMessage.debugMsg("getMinPoints sql...."+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public JhaTlAuditmst select(JhaTlAuditmst jhaTlAuditmst) throws Exception {
		JhaTlAuditmstSql jhaTlAuditmstSql = new JhaTlAuditmstSql();
		String sql = JhaTlAuditmstSql.getAUDITMSTSql(jhaTlAuditmstSql.getJhamDbFields(), jhaTlAuditmst.getSaveArray());//jhaTlAuditmst.getJhamKeyid(),jhaTlAuditmst.getJhamFlid(),jhaTlAuditmst.getJhamAuditteamid(),jhaTlAuditmst.getJhamAudittype(),jhaTlAuditmst.getJhamAuditpillar(),jhaTlAuditmst.getJhamJhstepid(),jhaTlAuditmst.getJhamAuditdate(),jhaTlAuditmst.getJhamAuditortype());
		String cntSql="Select count(*) from ("+sql+")";
		String cnt=dbActionTemplate.getSingleValue(cntSql);
		CommonMessage.debugMsg("select sql...."+sql);
		CommonMessage.debugMsg("Cnt...."+cnt);
		if(Integer.parseInt(cnt)==1){
			CommonMessage.debugMsg("Cnt...."+cnt);
			Object args [] = new Object [] {};
			jhaTlAuditmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		}
		return jhaTlAuditmst;
	}
	
	public String getAuditLevelCurrent(CommonFilter commonFilter) throws Exception {
		String sql = JhaTlAuditmstSql.getAuditLevelCurrentSql(commonFilter);
		CommonMessage.debugMsg("select sql...."+sql);
		Object args [] = new Object [] {};
		return dbActionTemplate.getSingleValue(sql);
	}
	
	@Override
	public String getSelectCnt(JhaTlAuditmst jhaTlAuditmst) throws Exception{
		String sql = JhaTlAuditmstSql.getSelectCntSql(jhaTlAuditmst.getJhamFlid());
		CommonMessage.debugMsg("select sql...."+sql);
		Object args [] = new Object [] {};
		return dbActionTemplate.getSingleValue(sql);
	}
	
	@Override
	public String getjhStepid(String flId) throws Exception {
		// TODO Auto-generated method stub
		String sqls =JhaTlAuditmstSql.getjhStepsql(flId);
		return dbActionTemplate.getSingleValue(sqls);
	}
	
	@Override
	public JhaTlTemplatelevellink getAuditLevel(String templateId,String flId, String jhStepId) throws Exception {
		// TODO Auto-generated method stub
		JhaTlTemplatelevellink jhaTlTemplatelevellink = new JhaTlTemplatelevellink();
		Object args [] = new Object [] {};
		String sql =JhaTlAuditmstSql.getAuditLevel(templateId,flId, jhStepId);
		jhaTlTemplatelevellink.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		//return dbActionTemplate.getSingleValue(sqls);
		return jhaTlTemplatelevellink;
	}
	 
		//changes by sriram 1//native query neeeded
		@Override
		public JhaTlAuditmst getExistingjhmKeyid(String templateId,String flId, String date, String auditType, String stepId) throws Exception {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT * FROM JHA_TL_AUDITMST WHERE JHAM_KEYID = ( " );  
			sql.append(" select MAX(JHAM_KEYID) from JHA_TL_AUDITMST, JHA_TL_AUDITDTL " ); 
			sql.append(" WHERE JHAD_JHAUDITMASTERID = JHAM_KEYID " ); 
			sql.append(" AND JHAM_FLID = '" + flId  + "' AND JHAM_AUDITTEAMID ='" + templateId  + "' " );
			sql.append(" AND JHAM_AUDITTYPE ='" + auditType + "' " );
			sql.append(" AND JHAM_JHSTEPID ='" + stepId + "' " );
			/* sql.append(" AND TRUNC(JHAM_AUDITDATE)='" + date + "' " ); */
			// ✅ Postgres date comparison
			if (date != null && !date.equalsIgnoreCase("null") && !date.isEmpty()) {
			    sql.append(" AND JHAM_AUDITDATE::date = TO_DATE('" + date + "', 'DD-Mon-YYYY') ");
			}
	
			sql.append(" ) ");
			
			CommonMessage.debugMsg("sql-----"+sql.toString());
			JhaTlAuditmst jhaTlAuditmst = new JhaTlAuditmst();
			Object args [] = new Object [] {};
			List<String[]> cntList = dbActionTemplate.getDataList(sql.toString());
			if (cntList.size()>0) {
				jhaTlAuditmst.setSaveArray(dbActionTemplate.getDataArr(sql.toString(), args));
			}
			else {
				//for last Auditor Name
				StringBuffer sql1 = new StringBuffer();
				sql1.append(" SELECT * FROM JHA_TL_AUDITMST WHERE JHAM_KEYID = ( " );  
				sql1.append(" select MAX(JHAM_KEYID) from JHA_TL_AUDITMST, JHA_TL_AUDITDTL " ); 
				sql1.append(" WHERE JHAD_JHAUDITMASTERID = JHAM_KEYID " ); 
				sql1.append(" AND JHAM_FLID = '" + flId  + "' AND JHAM_AUDITTEAMID ='" + templateId  + "' " );
				sql1.append(" AND JHAM_AUDITTYPE ='" + auditType + "' " );
				sql1.append(" AND JHAM_JHSTEPID ='" + stepId + "' " );
				//sql.append(" AND TRUNC(JHAM_AUDITDATE)='" + date  + "' " );
				sql1.append(" ) ");
				CommonMessage.debugMsg("sql22222-----"+sql1.toString());
				cntList = dbActionTemplate.getDataList(sql1.toString());
				if (cntList.size()>0) {
					jhaTlAuditmst.setSaveArray(dbActionTemplate.getDataArr(sql1.toString(), args));
					jhaTlAuditmst.setJhamKeyid("");
				}
			}
					
			return jhaTlAuditmst;
			
		}
	
	@Override
	public String getMinMarks(String auditLevel,String auditTemplate) throws Exception {
		// TODO Auto-generated method stub
		String sqls =JhaTlAuditmstSql.getMinMarkssql(auditLevel,auditTemplate);
		return dbActionTemplate.getSingleValue(sqls);
	}
	@Override
	public String getjhStepIdforFlid(String flid) throws Exception {
		// TODO Auto-generated method stub
		String sqls =JhaTlAuditmstSql.getjhStepKeyIdsql(flid);
		return dbActionTemplate.getSingleValue(sqls);
	}
	public String getjhauditLevelCountforFlid(String flid) throws Exception {
		// TODO Auto-generated method stub
		String sqls =JhaTlAuditmstSql.getcountjhStep(flid);
		return dbActionTemplate.getSingleValue(sqls);
	}

	@Override
	public Workbook getjhAuditMultiExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		
		 ResultSet rs = null;
		   try{
			
			   rs =   getjhAuditMultiResultSet(commonFilter)	;
			   ExcelUtils excelUtils = new ExcelUtils(colModel);
			   return excelUtils.writeToExcel(rs,rptFormat,8,2,0 ); //elumalai
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	@Override
	public List<String[]> getjhAuditParamterGrid(CommonFilter commonFilter ) {
		try
		{                          
			String sql = JhaTlAuditmstSql.getjhAuditParamterGrid(commonFilter);
			CommonMessage.debugMsg("getjhAuditParamterGrid sql.."+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		/*List<String[]> dataList = null;	
		try {
			
		List<String> paramValues = new ArrayList<String>();	
		String type = commonFilter.getType();
		String condParam = FilterCondSql.getJHCLITRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		CommonMessage.debugMsg("test inside dao impl1...."+commonFilter.getJhTemplateId());
		condParam += "MASTERID="+commonFilter.getJhTemplateId()+";";
		paramValues.add(condParam);
		paramValues.add(commonParams);
			
		dataList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_PC_AUDIT.JHN_FN_AUDITTEMPLATE", paramValues);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}		
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return dataList; */
	}

	@Override
	public List<String[]> getjhAuditStepGrid(CommonFilter commonFilter)	throws Exception {
		try
		{                       
			String sql = "";
			if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
				sql = JhaTlAuditmstSql.getjhAuditStepGrid(commonFilter);
			else
				sql = JhaTlAuditmstSql.getjhAuditStepDefaultGrid(commonFilter);
			
			CommonMessage.debugMsg("sql..."+sql);
			/*StringBuffer sql=new StringBuffer();
			//String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			//sql.append(" select 'keyId' keyId,'Review Point' item,'Criteria for scoring' description,'Max Scores' maxScores,'Delete' deleteBtn from dual ");
			//sql.append( " union ");
			sql.append(" select '0' keyId,'0' LevelNo,'Level0' JHLevelName,'' SelectV,'' SelectVal from dual ");
			sql.append( " union ");
			sql.append( " select '1' keyId,'1' LevelNo,'Level1' JHLevelName,'' SelectV,'' SelectVal from dual ");
			sql.append( " union ");
			sql.append( " select '2' keyId,'2' LevelNo,'Level2' JHLevelName,'' SelectV,'' SelectVal from dual ");
			sql.append( " union ");
			sql.append( " select '3' keyId,'3' LevelNo,'Level3' JHLevelName,'' SelectV,'' SelectVal from dual ");
			sql.append( " union ");
			sql.append( " select '4' keyId,'4' LevelNo,'Level4' JHLevelName,'' SelectV,'' SelectVal from dual ");
			*/
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String[]> getjhAuditEquipmentGrid(CommonFilter commonFilter)throws Exception {
		try
		{       CommonMessage.debugMsg("commonFilter.getJtw()::"+commonFilter.getJtw());        
			String sql = "";
			if (commonFilter.getType().equals("EQU")){
				if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId())){
					sql = JhaTlAuditmstSql.getjhAuditEquipmentGrid(commonFilter);}
				else{
					sql = JhaTlAuditmstSql.getjhAuditEquipmentDefaultGrid(commonFilter);}
			}
			else if (commonFilter.getType().equals("CEL")){
				if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
					sql = "";//JhaTlAuditmstSql.getjhAuditCellGrid(commonFilter);}
				else
					sql = "";//JhaTlAuditmstSql.getjhAuditCellDefaultGrid(commonFilter);}
			}
			CommonMessage.debugMsg("sql..."+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String[]> getjhAuditLevelGrid(CommonFilter commonFilter)throws Exception {
		try
		{                          
			String sql = JhaTlAuditmstSql.getjhAuditLevelGrid(commonFilter);			
			CommonMessage.debugMsg("sql..."+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JhaTlAuditparameter recallValues(JhaTlAuditparameter jhaTlAuditparameter) throws Exception {
		
		//JhaTlAuditparameter jhaTlAuditparameter = new JhaTlAuditparameter();		
		String sql = JhaTlAuditmstSql.recallValues(jhaTlAuditparameter.getJhapKeyid());
		CommonMessage.debugMsg("sql..."+sql);
		Object args [] = new Object [] {};
		jhaTlAuditparameter.setSaveArray(dbActionTemplate.getDataArr(sql, args));	
				
		return jhaTlAuditparameter;

	}

	@Override
	public JhaTlAuditparameter deleteAuditPatameter(JhaTlAuditparameter newJhaTlAuditparameter) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		
		List<String> sqls = new ArrayList<String>();
		
		JhaTlAuditparameterSql jhaTlAuditparameterSql = new JhaTlAuditparameterSql();
		
		try {
			sqls.add(JhaTlAuditparameterSql.getDeleteAuditTemplateSql(newJhaTlAuditparameter.getJhapKeyid()));
			sqls.add(JhaTlTemplatelevellinkSql.getDeleteSql(newJhaTlAuditparameter.getJhapKeyid()));
			sqls.add(JhaTlAuditparameterSql.getDeleteSql(jhaTlAuditparameterSql.getJhapDbFields(), newJhaTlAuditparameter.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage()+"FK_JHAM_AUDITTEAMID,");
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return newJhaTlAuditparameter;

	}

	@Override
	public List<String[]> getDMTMultiLevelAuditGrid(CommonFilter commonFilter)
			throws Exception {
		
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms="FLID="+commonFilter.getFlid()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="PARAMID="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getAuditRpt())){
			condParms+="AUDITTYPE="+commonFilter.getAuditRpt()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getFromMonth())){
			condParms+="MONTH="+commonFilter.getFromMonth()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//return dbActionTemplate.processFunctionCalls("JHN_FN_AUDITREPORT", paramValues);
		return fnCallApi.callFunction("JHN_FN_AUDITREPORT_SB", paramValues,9,true);
	}

	@Override
	public JhaTlTemplatelevellink getAppLvel(JhaTlTemplatelevellink jhaTlTemplatelevellink) throws Exception {
		//JhaTlAuditparameter jhaTlAuditparameter = new JhaTlAuditparameter();		
		String sql = "SELECT * FROM JHA_TL_TEMPLATELEVELLINK WHERE JTLL_TEMPLATEID='" + jhaTlTemplatelevellink.getJtllTemplateid() + "'";
		CommonMessage.debugMsg("sql..."+sql);
		Object args [] = new Object [] {};
		jhaTlTemplatelevellink.setSaveArray(dbActionTemplate.getDataArr(sql, args));	
				
		return jhaTlTemplatelevellink;
	}

	@Override
	public List<String[]> getAuditReportGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		/*if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms="FLID="+commonFilter.getFlid()+";";
		}*/
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="AUDITTYPE="+commonFilter.getType()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="PILLAR="+commonFilter.getKey()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		//return fnCallApi.callFunction("JHN_FN_AUDITLIST_SB", paramValues,2,true);
		//return dbActionTemplate.processFunctionCalls("JHN_FN_AUDITLIST", paramValues);
		
		List<String[]> dataList= fnCallApi.callFunction("JHN_FN_AUDITLIST_SB", paramValues,2,true);
		//return dbActionTemplate.processFunctionCalls("JHN_FN_AUDITLIST", paramValues);
		
		 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }		
		
		return dataList; 
	}
	
	public List<String[]> getjhAuditSheetfillGrid(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();				
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms+="PILLAR="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getType())){
				condParms+="AUDITTYPE="+commonFilter.getType()+";";
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("commonFilter.getViewClick():"+commonFilter.getViewClick());
			
			
			//List<String[]> rootRptList=dbActionTemplate.processFunctionCalls("JHN_FN_JHAUDITSHEET", paramValues);	
			List<String[]> rootRptList=fnCallApi.callFunction("JHN_FN_JHAUDITSHEET_SB", paramValues,2,true);
			
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }		
			return rootRptList;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
			
		}
	}
	public Workbook jhAuditSheetExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   jhAuditSheetReportResultSet(commonFilter);
			CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			return excelUtils.writeToExcel(rs,rptFormat,1,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	public Workbook getjhAuditMultiGridExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   jhAuditMultiGridReportResultSet(commonFilter);
			CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			return excelUtils.writeToExcel(rs,rptFormat, 2,1,0 );//elumalai
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet jhAuditMultiGridReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		/*
		 * if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
		 * condParms="FLID="+commonFilter.getFlid()+";"; }
		 */
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="AUDITTYPE="+commonFilter.getType()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="PILLAR="+commonFilter.getKey()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.NewdbFunctionCall2("JHN_FN_AUDITLIST", paramValues);
	}
	private ResultSet jhAuditSheetReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();	
		
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="PILLAR="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="AUDITTYPE="+commonFilter.getType()+";";
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("commonFilter.getViewClick():"+commonFilter.getViewClick());
		ResultSet rs = dbActionTemplate.NewdbFunctionCall2("JHN_FN_JHAUDITSHEET", paramValues);
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}
	private ResultSet getjhAuditMultiResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();	
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms="FLID="+commonFilter.getFlid()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="PARAMID="+commonFilter.getKey()+";";
		}	
		if(CommonFunctions.isValidKeyId(commonFilter.getAuditRpt())){
			condParms+="AUDITTYPE="+commonFilter.getAuditRpt()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getFromMonth())){
			condParms+="MONTH="+commonFilter.getFromMonth()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);	
		
		return dbActionTemplate.NewdbFunctionCall2("JHN_FN_AUDITREPORT", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	public String getJhLeader(String flid) throws Exception {
		// TODO Auto-generated method stub
		String sql = JhaTlAuditmstSql.getJhLeader(flid);			
		CommonMessage.debugMsg("getJhLeader sql..."+sql);
		return dbActionTemplate.getSingleValue(sql);
	}
	public JhaTlAuditmst createJhDmt(JhaTlAuditmst jhaTlAuditmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		List<String> sql = new ArrayList<String>();
		JhaTlAuditmstSql jhaTlAuditmstSql = new JhaTlAuditmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			String elementId = jhaTlAuditmst.getElementid();
		 	String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,JhaTlAuditmstSql.TBL_JHA_TL_AUDITMST);
			
			jhaTlAuditmst.setJhamKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 10, "JH", "DDMM", "Y"));  
			//jhaTlAuditmst.setJhamKeyid(dbActionTemplate.getSequenceNumber(JhaTlAuditmstSql.TBL_JHA_TL_AUDITMST, 10, "JHM", "DDMM", "Y"));  
			sqls.add(JhaTlAuditmstSql.getInsertSql(jhaTlAuditmstSql.getJhamDbFields(), jhaTlAuditmst.getSaveArray())); // add insert sql for master table
			//insertAuditDtl(jhaTlAuditmst,sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
			/*String count =JhaTlAuditmstSql.getcountjhStep(jhaTlAuditmst.getJhamFlid());
			count=dbActionTemplate.getSingleValue(count);
			
			if(count.equals("0")){
				String checkPass =JhaTlAuditmstSql.getPassSql(jhaTlAuditmst.getJhamFlid());
				checkPass=dbActionTemplate.getSingleValue(checkPass);
				int passVal = Integer.parseInt(checkPass);				
				String jhStep =JhaTlAuditmstSql.getjhStepKeyIdsql(jhaTlAuditmst.getJhamFlid());
				String jhId =dbActionTemplate.getSingleValue(jhStep);
				if(UIUtils.isValidKeyId(jhId) && (passVal<=0)){
					sql.add(JhaTlAuditmstSql.updateMachineMst(jhId,jhaTlAuditmst.getJhamFlid()));
					dbActionTemplate.executeStatements(sql);
				}
			}	*/	
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditmst;
	}
	
	public JhaTlAuditmst updateJhDmt(JhaTlAuditmst jhaTlAuditmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		JhaTlAuditmstSql jhaTlAuditmstSql = new JhaTlAuditmstSql();
		try {

			sqls.add(JhaTlAuditmstSql.getUpdateSql(jhaTlAuditmstSql.getJhamDbFields(), jhaTlAuditmst.getSaveArray()));
			insertAuditDtl(jhaTlAuditmst,sqls);
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return jhaTlAuditmst;
	}
	public List<String[]> getjhAuditUploadfillGrid(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();				
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms+="PILLAR="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getType())){
				condParms+="AUDITTYPE="+commonFilter.getType()+";";
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("commonFilter.getViewClick():"+commonFilter.getViewClick());
			List<String[]> rootRptList=fnCallApi.callFunction("JHN_FN_JHAUDITUPLOAD_SB", paramValues,2,true);
			
			//List<String[]> rootRptList=dbActionTemplate.processFunctionCalls("JHN_FN_JHAUDITUPLOAD", paramValues);	
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }		
			return rootRptList;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
			
		}
	}
	
	public Workbook jhAuditUploadExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   jhAuditSheetUploadResultSet(commonFilter);
			//CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			return excelUtils.writeToExcel(rs,rptFormat,1,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet jhAuditSheetUploadResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();	
		
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="PILLAR="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="AUDITTYPE="+commonFilter.getType()+";";
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("commonFilter.getViewClick():"+commonFilter.getViewClick());
		ResultSet rs = dbActionTemplate.NewdbFunctionCall2("JHN_FN_JHAUDITUPLOAD", paramValues);
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}

	@Override
	public List<String[]> getlastauditgrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();				
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			CommonMessage.debugMsg("cond Parms"+condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg("comm Parms"+commonParams);
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms+="PILLAR="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getType())){
				condParms+="AUDITTYPE="+commonFilter.getType()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
				condParms+="FLID"+commonFilter.getFlid()+";";
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("commonFilter.getViewClick():"+commonFilter.getViewClick());
			//List<String[]> rootRptList=dbActionTemplate.processFunctionCalls("JHN_FN_JHAUDITLASTTHREEVIEW", paramValues);
			List<String[]> rootRptList=fnCallApi.callFunction("JHN_FN_JHAUDITLASTTHREEVIEW_SB", paramValues,3,true);
			CommonMessage.debugMsg("Root List IS >>>>"+rootRptList);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }		
			return rootRptList;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
			
		}
	}
	
	
	
	
}

