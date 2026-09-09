package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.NearMissDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlNearmissreportdtlSql;
import com.akranta.tpm.dao.sql.GenTlCriticalprocessSql;
import com.akranta.tpm.dao.sql.GenTlNearmissreportmstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlNearmissreportdtl;
import com.akranta.tpm.model.GenTlNearmissreportmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;


public class NearMissDaoImpl implements NearMissDao {
	private DBActionTemplate dbActionTemplate;
	private GenTlNearmissreportmstSql genTlNearmissreportmstsql;
	
	public NearMissDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
		 genTlNearmissreportmstsql=new GenTlNearmissreportmstSql();

	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	@Override
	public List<String[]> getAllNear(CommonFilter commonFilter) throws Exception {
		
		StringBuffer sql = new StringBuffer();
	/*	sql.append(" select * from (");
		sql.append(" SELECT 'Keyid' as keyid ,'Function Location' as functionloc ,'Prepared By' as preparedby , 'Department' as Department,");
		sql.append(" 'Date' as ccurrencedatetime,'Description' as Discription,'Status' AS STATUS,'Severity' as Severity,");
		sql.append(" 'Problem Recurrence' as ProbableRecurrence ,'Action Recommended' as ActionRecommended,'Responsibility' as Responsibility,");
		sql.append(" 'Target Date' as TargetDate from dual ");
		sql.append("  union all ");
		sql.append(" select nmrt_keyid, FUNCTIONALLOC,a.empm_name,sect_name,to_char(nmrt_occurrencedatetime,'dd-mon-yyyy') ,");
		sql.append(" nmrt_descnearmiss,decode(NMRT_STATUS,'P','PENDING','C','COMPLETE'),nmsp_name, prrr_name,nmrt_Actionrecommended,b.empm_name,to_char(nmrt_targetdate,'dd-mon-yyyy')");
		sql.append(" from GEN_TL_NEARMISSREPORTMST,gen_tl_employeemst a,gen_tl_employeemst b,SHE_TL_NEARMISSSEVERITY,SHE_TL_PROBABLERECURANCERATE,GEN_VW_FNLN");
		sql.append(" where  FNLN_KEYID(+)=NMRT_FLNID and NMRT_EMPLOYEEID =a.empm_keyid(+)  and nmrt_responsibility=b.empm_keyid (+)  ");
		sql.append(" and nmrt_severitypotentialid =nmsp_keyid(+) and nmrt_probablerecrate=prrr_keyid(+) and a.EMPM_LOCATION='"+FilterCondSql.getComboSelectionId(commonFilter.getLocation()));
		sql.append("' ) WHERE 1=1 " + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		
		sql.append(" select * from (");
		sql.append(" SELECT 'Keyid' as keyid ,'Function Location' as functionloc ,'Prepared By' as preparedby , 'Department' as Department,");
		sql.append(" 'Date' as ccurrencedatetime,'Description' as Description ,'Severity' as Severity,");
		sql.append(" 'Investigation','Problem Recurrence' as ProbableRecurrence ,'Action Recommended' as ActionRecommended,'Status' as Status,'Responsibility' as Responsibility,");
		sql.append(" 'Target Date' as TargetDate,'DATAORDER' AS DATAORDER from dual ");
		sql.append("  union all ");
		sql.append(" select nmrt_keyid, FUNCTIONALLOC,a.empm_name,sect_name,to_char(nmrt_occurrencedatetime,'dd-mon-yyyy') ,");
		sql.append(" nmrt_descnearmiss,nmsp_name,NMRT_INVESTIGATION, prrr_name,nmrt_Actionrecommended,decode(NMRT_STATUS,'P','Pending','C','Completed') as Status,b.empm_name,to_char(nmrt_targetdate,'dd-mon-yyyy'),to_char(nmrt_occurrencedatetime,'YYYYMMDD') AS DATAORDER ");
		sql.append(" from GEN_TL_NEARMISSREPORTMST,gen_tl_employeemst a,gen_tl_employeemst b,SHE_TL_NEARMISSSEVERITY,SHE_TL_PROBABLERECURANCERATE,GEN_VW_FNLN");
		sql.append(" where  FNLN_KEYID(+)=NMRT_FLNID and NMRT_EMPLOYEEID =a.empm_keyid(+)  and nmrt_responsibility=b.empm_keyid (+)  ");
		sql.append(" and nmrt_severitypotentialid =nmsp_keyid(+) and nmrt_probablerecrate=prrr_keyid(+) and a.EMPM_LOCATION='"+FilterCondSql.getComboSelectionId(commonFilter.getLocation())+"'");
		sql.append("  AND NMRT_FLNID =  '"+commonFilter.getFlid()+"'");
		sql.append(" ) WHERE 1=1 " + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		sql.append(" ORDER BY DATAORDER DESC ");
		CommonMessage.debugMsg("sql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		gridData
		*/

		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		condParms+="REPORTTYPE="+commonFilter.getType()+";";
		
		String mode =commonFilter.getAbnAllch();
		
		CommonMessage.debugMsg(" DaoImpl :: mode :: "+mode+" Checking for type :: "+commonFilter.getType());
		
		if(UIUtils.isValidKeyId(mode))
			condParms+="Mode="+mode+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.EHS_FN_NEARMISSMAINGRID", paramValues);
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
		return dataList;
		
	}
	@Override
	public List<String[]> getNear(String masterKeyid) throws Exception{
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT "); 
		sql.append(" NMUA_KEYID,NEAR_KEYID AS \"keyid\",NEAR_NAME AS \"Unsafe Act\",'' ");  
		sql.append(" FROM ");
		sql.append(" SHE_TL_NEARMISS , GEN_TL_NEARMISSREPORTDTL,GEN_TL_NEARMISSREPORTMST "); 
		sql.append(" WHERE ");
		sql.append(" NEAR_KEYID=NMUA_NEARKEYID(+) ");
	    sql.append(" AND NMRT_KEYID(+) =  NMUA_NEARKEYID ");
	    //if(UIUtils.isValidKeyId(masterKeyid)){
	    	sql.append(" AND NMUA_NMRTKEYID(+) ='"+masterKeyid+"' ");
	    //}
	    sql.append(" AND NEAR_CODE = 'UA' ");
	    sql.append(" ORDER BY ");
	    sql.append(" NEAR_KEYID ");
	    
	    
		/*sql.append("select  NMUA_KEYID,near_keyid as \"keyid\",near_name as \"Unsafe Act\",''  from she_tl_nearmiss , Gen_Tl_Nearmissreportdtl");
		sql.append(" where ");
		sql.append("NEAR_KEYID=NMUA_NEARKEYID(+) ");
		if(UIUtils.isValidKeyId(masterKeyid)){
			sql.append("and NMUA_NMRTKEYID(+) ='");//NMUA_KEYID
			sql.append(masterKeyid);
			sql.append("'");
		}
		sql.append("and near_code = 'UA' ORDER BY near_keyid");*/
		CommonMessage.debugMsg("sql..." + sql.toString());
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
		
	}
	
	@Override
	public List<String[]> getNearMiss(String masterKeyid) throws Exception{
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT "); 
		sql.append(" NMUA_KEYID,NEAR_KEYID AS \"keyid\",NEAR_NAME AS \"Unsafe Act\",'' ");  
		sql.append(" FROM ");
		sql.append(" SHE_TL_NEARMISS , GEN_TL_NEARMISSREPORTDTL,GEN_TL_NEARMISSREPORTMST "); 
		sql.append(" WHERE ");
		sql.append(" NEAR_KEYID=NMUA_NEARKEYID(+) ");
	    sql.append(" AND NMRT_KEYID(+) =  NMUA_NEARKEYID ");
	    //if(UIUtils.isValidKeyId(masterKeyid)){
	    	sql.append(" AND NMUA_NMRTKEYID(+) ='"+masterKeyid+"' ");
	    //}
	    sql.append(" AND NEAR_CODE = 'UC' ");
	    sql.append(" ORDER BY ");
	    sql.append(" NEAR_KEYID ");
	    
		/*sql.append("select  NMUA_KEYID,near_keyid as \"keyid\",near_name as \"Unsafe Act\",''  from she_tl_nearmiss , Gen_Tl_Nearmissreportdtl");
		sql.append(" where ");
		sql.append("NEAR_KEYID=NMUA_NEARKEYID(+) ");
		if(UIUtils.isValidKeyId(masterKeyid)){
			sql.append("and NMUA_NMRTKEYID(+) ='");//
			sql.append(masterKeyid);
			sql.append("'");
		}*/
				
		CommonMessage.debugMsg("sql..." + sql.toString());
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
		
	}
	public GenTlNearmissreportmst create(GenTlNearmissreportmst genTlNearmissreportmst) throws Exception{
		CommonMessage.debugMsg("Inside DAO...."); 
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlNearmissreportmstSql genTlNearmissreportmstsql = new GenTlNearmissreportmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		checkDuplicateEntry(genTlNearmissreportmst);
			if(UIUtils.isValidKeyId(genTlNearmissreportmst.getNmrtKeyid()))
				sqls.add(GenTlNearmissreportmstSql.getUpdateSql(genTlNearmissreportmstsql.getNmrtDbFields(), genTlNearmissreportmst.getSaveArray()));
			else{
				CommonMessage.debugMsg("Inside DAO else....");
				String elementId = genTlNearmissreportmst.getElementid();
			 	String location = null;
			 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlNearmissreportmstSql.TBL_GEN_TL_NEARMISSREPORTMST);
			 	genTlNearmissreportmst.setNmrtKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 10, "NM" ,"YY" ,"Y")); // set the sequnce number
				//genTlNearmissreportmst.setNmrtKeyid(dbActionTemplate.getSequenceNumber(GenTlNearmissreportmstSql.TBL_GEN_TL_NEARMISSREPORTMST, 10, "NMR" ,"YYMM" ,"Y")); // set the sequnce number 
				sqls.add(GenTlNearmissreportmstSql.getInsertSql(genTlNearmissreportmstsql.getNmrtDbFields(), genTlNearmissreportmst.getSaveArray())); // add insert sql for master table
			}
			CommonMessage.debugMsg("Inside DAO for details..eeeeeeeeeeeeee..");
			//CommonMessage.debugMsg("Inside DAO for details...."+genTlNearmissreportmst.getnearmissreportDtl().size());
			if(genTlNearmissreportmst.getnearmissreportDtl()!=null){
				CommonMessage.debugMsg("Inside DAO for details....");
				GenTlNearmissreportdtlSql genTlNearmissreportdtlSql=new GenTlNearmissreportdtlSql();
				for(int i=0;i<genTlNearmissreportmst.getnearmissreportDtl().size();i++){
					GenTlNearmissreportdtl genTlNearmissreportdtl=genTlNearmissreportmst.getnearmissreportDtl().get(i);
					fillNearmissreportdtlValues(genTlNearmissreportdtl,genTlNearmissreportmst);
					if(UIUtils.isValidKeyId(genTlNearmissreportdtl.getNmuaKeyid())){
						sqls.add(GenTlNearmissreportdtlSql.getUpdateSql(genTlNearmissreportdtlSql.getNmuaDbFields(), genTlNearmissreportdtl.getSaveArray()));
					}
					else{
						genTlNearmissreportdtl.setNmuaKeyid(dbActionTemplate.getSequenceNumber(GenTlNearmissreportdtlSql.TBL_GEN_TL_NEARMISSREPORTDTL, 10, "NMU" ,"YYMM" ,"Y")); // set the sequnce number 
						sqls.add(GenTlNearmissreportdtlSql.getInsertSql(genTlNearmissreportdtlSql.getNmuaDbFields(), genTlNearmissreportdtl.getSaveArray())); // add insert sql for master table
	
					}
				}
			}
			CommonMessage.debugMsg("insert Sql" + sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return genTlNearmissreportmst;
	}
	private void checkDuplicateEntry(GenTlNearmissreportmst genTlNearmissreportmst) throws Exception {

		String checkSql = GenTlNearmissreportdtlSql.checkDuplicate(genTlNearmissreportmst);
		CommonMessage.debugMsg("checkSql...."+checkSql);
		String checkVal = dbActionTemplate.getSingleValue(checkSql);
		int val = Integer.parseInt(checkVal);
		if(val>0)
			throw new Exception("NEAR_EXIST");
	}
	private void fillNearmissreportdtlValues(GenTlNearmissreportdtl newGenTlNearmissreportdtl,GenTlNearmissreportmst genTlNearmissreportmst) {
		CommonMessage.debugMsg("Inside DAO..fillNearmissreportdtlValues..");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaNmrtkeyid()))
			newGenTlNearmissreportdtl.setNmuaNmrtkeyid(genTlNearmissreportmst.getNmrtKeyid());
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaActive()))
			newGenTlNearmissreportdtl.setNmuaActive("Y");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaCreatedon()))
			newGenTlNearmissreportdtl.setNmuaCreatedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaModifiedon()))
			newGenTlNearmissreportdtl.setNmuaModifiedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaTempfield1()))
				newGenTlNearmissreportdtl.setNmuaTempfield1("-");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaTempfield2()))
				newGenTlNearmissreportdtl.setNmuaTempfield2("-");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaTempfield3()))
				newGenTlNearmissreportdtl.setNmuaTempfield3("-");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaTempfield4()))
				newGenTlNearmissreportdtl.setNmuaTempfield4("-");
	}
	public GenTlNearmissreportmst update(GenTlNearmissreportmst genTlNearmissreportmst){ 
		CommonMessage.debugMsg("In Update Dao Impl::::"+genTlNearmissreportmst.getNmrtKeyid());
		List<String> sqls = new ArrayList<String>();
		GenTlNearmissreportmstSql genTlNearmissreportmstSql = new GenTlNearmissreportmstSql();
		//CommonMessage.debugMsg("Detail detail in update size"+genTlNearmissreportmst.getnearmissreportDtl().size());
		//CommonMessage.debugMsg("Inside DAO for details update mode....");
		try {

			
			GenTlNearmissreportdtlSql genTlNearmissreportdtlSql=new GenTlNearmissreportdtlSql();
			if(genTlNearmissreportmst.getnearmissreportDtl()!=null){
				sqls.add(GenTlNearmissreportmstSql.getUpdateSql(genTlNearmissreportmstSql.getNmrtDbFields(), genTlNearmissreportmst.getSaveArray()));
				for(int i=0;i<genTlNearmissreportmst.getnearmissreportDtl().size();i++){
					GenTlNearmissreportdtl genTlNearmissreportdtl=genTlNearmissreportmst.getnearmissreportDtl().get(i);
					fillNearmissreportdtlValues(genTlNearmissreportdtl,genTlNearmissreportmst);
					
					//checking
					if(genTlNearmissreportdtl.getNmuaNearkeyid()==null && genTlNearmissreportdtl.getNmuaKeyid()!=null){
						CommonMessage.debugMsg("delete....");
						//CommonMessage.debugMsg("NmuaNearkeyid...."+genTlNearmissreportdtl.getNmuaNearkeyid());
						//CommonMessage.debugMsg("NmuaKeyid...."+genTlNearmissreportdtl.getNmuaKeyid());
						sqls.add(GenTlNearmissreportdtlSql.getDeleteSql(genTlNearmissreportdtlSql.getNmuaDbFields(), genTlNearmissreportdtl.getSaveArray()));
					
					}
					else if(genTlNearmissreportdtl.getNmuaNearkeyid()!=null && genTlNearmissreportdtl.getNmuaKeyid()==null){
						CommonMessage.debugMsg("insert....");
						//CommonMessage.debugMsg("NmuaNearkeyid...."+genTlNearmissreportdtl.getNmuaNearkeyid());
						//CommonMessage.debugMsg("NmuaKeyid...."+genTlNearmissreportdtl.getNmuaKeyid());
						genTlNearmissreportdtl.setNmuaKeyid(dbActionTemplate.getSequenceNumber(GenTlNearmissreportdtlSql.TBL_GEN_TL_NEARMISSREPORTDTL, 10, "NMU" ,"YYMM" ,"Y")); // set the sequnce number 
						sqls.add(GenTlNearmissreportdtlSql.getInsertSql(genTlNearmissreportdtlSql.getNmuaDbFields(), genTlNearmissreportdtl.getSaveArray())); // add insert sql for master table
	
					}
					else{
						CommonMessage.debugMsg("do Nothing....");
					}
				}
			}else{
				sqls.add(GenTlNearmissreportmstSql.getUpdateSql(genTlNearmissreportmstSql.getNmrtDbFields(), genTlNearmissreportmst.getSaveArray()));
			}
			
			CommonMessage.debugMsg("Delete sql" + sqls);
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
		}
		
		return genTlNearmissreportmst;
	}
	@Override
	public GenTlNearmissreportmst getGridDdatas(String keyId) throws NoDataFoundException, SQLException, Exception {
		
		GenTlNearmissreportmst genTlNearmissreportmst=new GenTlNearmissreportmst();
		GenTlNearmissreportmstSql genTlNearmissreportmstSql=new GenTlNearmissreportmstSql();
		String sql=genTlNearmissreportmstSql.getSingleData();
		Object args[]=new Object[]{keyId};
		genTlNearmissreportmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		/*sql.append("select nmrt_keyid,empm_name,sect_name,to_char(nmrt_occurrencedatetime,'dd-mon-yyyy') ,nmrt_descnearmiss,nmsp_name,");
		sql.append(" prrr_name,nmrt_Actionrecommended,empm_name,to_char(nmrt_targetdate,'dd-mon-yyyy')");
		sql.append(" from GEN_TL_NEARMISSREPORTMST,gen_tl_employeemst,	SHE_TL_NEARMISSSEVERITY	,SHE_TL_PROBABLERECURANCERATE,	gen_tl_sectionmst ");
		sql.append(" where  nmrt_Active='Y' and empm_keyid=NMRT_EMPLOYEEID and nmrt_responsibility=NMRT_EMPLOYEEID ");
		sql.append(" and nmsp_keyid=nmrt_severitypotentialid and prrr_keyid=nmrt_probablerecrate and sect_keyid=nmrt_deptid");
		sql.append(" and nmrt_keyid='"+keyId+"' ");*/
		return genTlNearmissreportmst;
	}
	@Override
	public List<String[]> getSingleNearMissDetail(String keyId)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from Gen_Tl_Nearmissreportdtl where NMUA_NMRTKEYID =");
		sql.append("'");
		sql.append(keyId);
		sql.append("'");
		
		CommonMessage.debugMsg("sql..." + sql.toString());
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	
	public Workbook getNearMissExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		
		        ResultSet rs = null;
		    try{
				
				rs =   getNearMissResultSet1(commonFilter1);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				
				return excelUtils.writeToExcel(rs,format, 2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	 private ResultSet getNearMissResultSet1(CommonFilter commonFilter1)throws Exception {
			// TODO Auto-generated method stub
			/*StringBuffer sql = new StringBuffer();
		    sql.append(" select * from (");
			sql.append(" SELECT 'Keyid' as keyid ,'Function Location' as functionloc ,'Prepared By' as preparedby , 'Department' as Department,");
			sql.append(" 'Date' as ccurrencedatetime,'Description' as Description ,'Severity' as Severity,");
			sql.append(" 'Investigation','Problem Recurrence' as ProbableRecurrence ,'Action Recommended' as ActionRecommended,'Status' as Status,'Responsibility' as Responsibility,");
			sql.append(" 'Target Date' as TargetDate,'DATAORDER' AS DATAORDER from dual ");
			sql.append("  union all ");
			sql.append(" select nmrt_keyid, FUNCTIONALLOC,a.empm_name,sect_name,to_char(nmrt_occurrencedatetime,'dd-mon-yyyy') ,");
			sql.append(" nmrt_descnearmiss,nmsp_name,NMRT_INVESTIGATION, prrr_name,nmrt_Actionrecommended,decode(NMRT_STATUS,'P','Pending','C','Completed') as Status,b.empm_name,to_char(nmrt_targetdate,'dd-mon-yyyy'),to_char(nmrt_occurrencedatetime,'YYYYMMDD') AS DATAORDER ");
			sql.append(" from GEN_TL_NEARMISSREPORTMST,gen_tl_employeemst a,gen_tl_employeemst b,SHE_TL_NEARMISSSEVERITY,SHE_TL_PROBABLERECURANCERATE,GEN_VW_FNLN");
			sql.append(" where  FNLN_KEYID(+)=NMRT_FLNID and NMRT_EMPLOYEEID =a.empm_keyid(+)  and nmrt_responsibility=b.empm_keyid (+)  ");
			sql.append(" and nmrt_severitypotentialid =nmsp_keyid(+) and nmrt_probablerecrate=prrr_keyid(+) and a.EMPM_LOCATION='"+FilterCondSql.getComboSelectionId(commonFilter1.getLocation())+"'");
			sql.append("  AND NMRT_FLNID =  '"+commonFilter1.getFlid()+"'");
			sql.append(" ) WHERE 1=1 " + FilterCondSql.makeGridFilterCond(commonFilter1.getGridFilter()) );
			sql.append(" ORDER BY DATAORDER DESC ");
			CommonMessage.debugMsg("sql57546..." + sql);
			List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
			
			return dbActionTemplate.getData(sql.toString());
			*/
		 
		    List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter1);			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return dbActionTemplate.dbFunctionCall("TEST_PC_TEST2.EHS_FN_NEARMISSMAINGRID", paramValues);
			
		 
		}
	private ResultSet getNearMissResultSet(CommonFilter commonFilter1)throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		/*sql.append(" select * from (");
		sql.append(" SELECT 'Keyid' as keyid ,'Function Location' as functionloc ,'Prepared By' as preparedby , 'Department' as Department,");
		sql.append(" 'Date' as ccurrencedatetime,'Description' as Description ,'Severity' as Severity,");
		sql.append(" 'Problem Recurrence' as ProbableRecurrence ,'Action Recommended' as ActionRecommended,'Responsibility' as Responsibility,");
		sql.append(" 'Target Date' as TargetDate,'DATAORDER' AS DATAORDER from dual ");
		sql.append("  union all ");
		sql.append(" select nmrt_keyid, FUNCTIONALLOC,a.empm_name,sect_name,to_char(nmrt_occurrencedatetime,'dd-mon-yyyy') ,");
		sql.append(" nmrt_descnearmiss,nmsp_name, prrr_name,nmrt_Actionrecommended,b.empm_name,to_char(nmrt_targetdate,'dd-mon-yyyy'),to_char(nmrt_occurrencedatetime,'YYYYMMDD') AS DATAORDER ");
		sql.append(" from GEN_TL_NEARMISSREPORTMST,gen_tl_employeemst a,gen_tl_employeemst b,SHE_TL_NEARMISSSEVERITY,SHE_TL_PROBABLERECURANCERATE,GEN_VW_FNLN");
		sql.append(" where  FNLN_KEYID(+)=NMRT_FLNID and NMRT_EMPLOYEEID =a.empm_keyid(+)  and nmrt_responsibility=b.empm_keyid (+)  ");
		sql.append(" and nmrt_severitypotentialid =nmsp_keyid(+) and nmrt_probablerecrate=prrr_keyid(+) and a.EMPM_LOCATION='"+FilterCondSql.getComboSelectionId(commonFilter1.getLocation()));
		sql.append("' ) WHERE 1=1 " + FilterCondSql.makeGridFilterCond(commonFilter1.getGridFilter()) );
		sql.append(" ORDER BY DATAORDER DESC ");
		*/
		sql.append(" select * from (");
		sql.append(" SELECT 'Keyid' as keyid ,'Function Location' as functionloc ,'Prepared By' as preparedby , 'Department' as Department,");
		sql.append(" 'Date' as ccurrencedatetime,'Description' as Discription,'Status' AS STATUS,'Severity' as Severity,");
		sql.append(" 'Problem Recurrence' as ProbableRecurrence ,'Action Recommended' as ActionRecommended,'Status' AS Status,'Responsibility' as Responsibility,");
		sql.append(" 'Target Date' as TargetDate from dual ");
		sql.append("  union all ");
		sql.append(" select nmrt_keyid, FUNCTIONALLOC,a.empm_name,sect_name,to_char(nmrt_occurrencedatetime,'dd-mon-yyyy') ,");
		sql.append(" nmrt_descnearmiss,decode(NMRT_STATUS,'P','PENDING','C','COMPLETE'),nmsp_name, prrr_name,nmrt_Actionrecommended,NMRT_STATUS as Status,b.empm_name,to_char(nmrt_targetdate,'dd-mon-yyyy')");
		sql.append(" from GEN_TL_NEARMISSREPORTMST,gen_tl_employeemst a,gen_tl_employeemst b,SHE_TL_NEARMISSSEVERITY,SHE_TL_PROBABLERECURANCERATE,GEN_VW_FNLN");
		sql.append(" where  FNLN_KEYID(+)=NMRT_FLNID and NMRT_EMPLOYEEID =a.empm_keyid(+)  and nmrt_responsibility=b.empm_keyid (+)  ");
		sql.append(" and nmrt_severitypotentialid =nmsp_keyid(+) and nmrt_probablerecrate=prrr_keyid(+) and a.EMPM_LOCATION='"+FilterCondSql.getComboSelectionId(commonFilter1.getLocation()));
		sql.append("' ) WHERE 1=1 " + FilterCondSql.makeGridFilterCond(commonFilter1.getGridFilter()) );
		
		CommonMessage.debugMsg("sql..." + sql);
		//List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		
		//return  gridData;
		
		return dbActionTemplate.getData(sql.toString());
	}
	@Override
	public GenTlNearmissreportmst delete(GenTlNearmissreportmst genTlNearmissreportmst) throws Exception{
		GenTlNearmissreportdtl genTlNearmissreportdtl = new GenTlNearmissreportdtl();
		List<String> sqls = new ArrayList<String>();
	String keyId;
	keyId=genTlNearmissreportmst.getNmrtKeyid();
		String sql1 = GenTlNearmissreportdtlSql.getDeleteDtl(keyId);
		String sql = GenTlNearmissreportmstSql.getDeleteMst(keyId);
		sqls.add(sql1);
		sqls.add(sql);
		///CommonMessage.debugMsg("keyId..." + genTlNearmissreportmst.getNmrtKeyid());
		//CommonMessage.debugMsg("sql for dtl..." + sql1);
		//CommonMessage.debugMsg("sql mst..." + sql);
    	//Object args[] = new Object[] {keyId};
    	dbActionTemplate.executeStatements(sqls);
    	return genTlNearmissreportmst;
    	 
		
			 
	}
	@Override
	public List<String[]> getAllNeargrd() throws Exception {
		// TODO Auto-generated method stub
		
			
		String sql="  select 'HR ','24-Nov-2013','Furnish','30','5' FROM DUAL";
	       sql+=" UNION ALL select 'EBU','12-Sep-2013','Carbonate','80','8' FROM DUAL";
	       sql+=" UNION ALL  select 'BUILDING 2','28-Dec-2013','Mild','100','10' FROM DUAL";
	       sql+=" UNION ALL  select 'FMS','1-Aug-2013','Cost','120','10' FROM DUAL";
	       sql+=" UNION ALL  select 'PATCH CORD','5-Mar-2013','Report','200','17' FROM DUAL";
	       CommonMessage.debugMsg("sql "+sql);
			List<String[]> dataList =  dbActionTemplate.getDataList(sql);
			return dataList;
	}
	
	@Override
	public List<String[]> getNearmissmonthrpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		    paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;

			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("SOP_PC_SOP.GEN_FN_NEARMISSMONTHRPT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	@Override
	public Workbook getNearMissMonthExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub

        ResultSet rs = null;
    try{
		
		rs =   getNearMissMonthResultSet(commonFilter1);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		
		return excelUtils.writeToExcel(rs,format, 3,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}
	private ResultSet getNearMissMonthResultSet(CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValues(commonFilter1);
		
		return dbActionTemplate.dbFunctionCall("SOP_PC_SOP.GEN_FN_NEARMISSMONTHRPT", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter1) {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter1);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	@Override
	public List<String[]> actnplnstatus(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = GenTlNearmissreportmstSql.selectData(keyid);
		CommonMessage.debugMsg(" sql :: 1234 ::  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}
	@Override
	public List<String[]> getfillnearmissdata(String keyid, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.empm_name as employeename,to_char(nmrt_prepareddatetime,'DD-MON-YYYY HH:MM'),sect_name as dmt,cell_code as jh,to_char(nmrt_occurrencedatetime,'DD-MON-YYYY HH:MM'),nmrt_descnearmiss, ");
		sql.append(" nmsp_name,prrr_name,nmrt_actionrecommended,b.empm_name,to_char(nmrt_targetdate,'DD-MON-YYYY'),DECODE(nmrt_status,'P','Pending','C','Completed') ");
		sql.append(" from GEN_TL_NEARMISSREPORTMST,gen_tl_employeemst a,gen_tl_employeemst b, ");
		sql.append(" SHE_TL_PROBABLERECURANCERATE,SHE_TL_NEARMISSSEVERITY,GEN_VW_FNLN ");
		sql.append(" where NMRT_EMPLOYEEID  =a.empm_keyid(+) AND nmrt_responsibility =b.empm_keyid (+) ");
		sql.append(" AND nmrt_probablerecrate = prrr_keyid AND NMSP_KEYID=NMRT_SEVERITYPOTENTIALID AND fnln_keyid=nmrt_flnid AND nmrt_keyid='"+keyid+"' ");
		
		CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
		CommonMessage.debugMsg("The Fill Near Miss Data:"+sql);
		List<String[]>  mstrdata  = dbActionTemplate.getDataList(sql.toString());
	    return mstrdata;
	}
	@Override
	public List<String[]> getfillunsafeconditiondata(String keyid, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT  NMUA_KEYID,NEAR_NAME AS UnsafeAct ");
		sql.append(" FROM SHE_TL_NEARMISS,GEN_TL_NEARMISSREPORTDTL,GEN_TL_NEARMISSREPORTMST ");
		sql.append(" WHERE  NEAR_KEYID=NMUA_NEARKEYID(+) AND NMRT_KEYID(+) =  NMUA_NEARKEYID ");
		sql.append(" AND NMUA_NMRTKEYID(+) ='"+keyid+"' AND NEAR_CODE = 'UC'  ORDER BY  NEAR_KEYID ");
		
		CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
		CommonMessage.debugMsg("Fill UnsafeCondion Data:"+sql);
		List<String[]>  unsafeconditiondata  = dbActionTemplate.getDataList(sql.toString());
	    return unsafeconditiondata;
	}
	@Override
	public List<String[]> getfillunsafeactdata(String keyid, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NMUA_KEYID,NEAR_NAME AS UnsafeAct ");
		sql.append(" FROM  SHE_TL_NEARMISS,GEN_TL_NEARMISSREPORTDTL,GEN_TL_NEARMISSREPORTMST ");
		sql.append(" WHERE  NEAR_KEYID=NMUA_NEARKEYID(+)  AND NMRT_KEYID(+) =  NMUA_NEARKEYID ");
		sql.append(" AND NMUA_NMRTKEYID(+) ='"+keyid+"' AND NEAR_CODE ='UA' ORDER BY NEAR_KEYID ");
		
		CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
		CommonMessage.debugMsg("The Fill unsafeact Data:"+sql);
		List<String[]>  unsafeactdata  = dbActionTemplate.getDataList(sql.toString());
	    return unsafeactdata;
	}
	@Override
	public List<String[]> getNewNearmissRptReportgraph(
			CommonFilter chrtCommonFilter) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String[]> getNewNearmissmonthrpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		    paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;

			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("SOP_PC_SOP.GEN_FN_NEWNEARMISSMONTHWISERPT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	@Override
	public List<String[]> getNearmisscountrpt(CommonFilter commonFilter) throws Exception{
		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		    paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;

			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("SOP_PC_SOP.GEN_FN_NEARMISSCOUNTRPT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
		
	}
	@Override
	public List<String[]> getNearmissCountRptgraph(CommonFilter commonFilter,String rowId) throws Exception{
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		    paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;

			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("SOP_PC_SOP.GEN_FN_NEARMISSCOUNTRPT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	

	@Override
	public Workbook getNearMissCountToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub

        ResultSet rs = null;
    try{
		
		rs =   getNearMissCountResultSet(commonFilter1);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		
		return excelUtils.writeToExcel(rs,format, 3,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}
	private ResultSet getNearMissCountResultSet(CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValuesData(commonFilter1);
		
		return dbActionTemplate.dbFunctionCall("SOP_PC_SOP.GEN_FN_NEARMISSCOUNTRPT", paramValues);
	}
	private List<String> getFilterParamValuesData(CommonFilter commonFilter1) {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter1);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}	
	
	public List<String[]> getNearmissTrendcountrpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.EHS_FN_NEARMISSTRENDCOUNT", paramValues);
			CommonMessage.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}

	}
	@Override
	public List<String[]> getNearmissTrendcountrptgraph(CommonFilter commonFilter, String rowId) throws Exception {
		try
		{
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			 List<String[]> rootRptList;
			 CommonMessage.debugMsg("paramValues"+paramValues);
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST2.EHS_FN_NEARMISSTRENDCOUNT", paramValues);			
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
			throw new Exception(e.getMessage()); 
		}
	}
	
	@Override
	public Workbook getNearMissTrendToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub

        ResultSet rs = null;
    try{
		
		rs =   getNearMissTrendResultSet(commonFilter1);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		
		return excelUtils.writeToExcel(rs,format, 3,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}
	private ResultSet getNearMissTrendResultSet(CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValuesDataCon(commonFilter1);
		
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST2.EHS_FN_NEARMISSTRENDCOUNT", paramValues);
	}
	private List<String> getFilterParamValuesDataCon(CommonFilter commonFilter1) {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter1);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}		
	
	public List<String[]> getNearMissCumcount(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside wertgwer DAO Impl");
				
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
	
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	
			//CommonMessage.debugMsg(" :: dashboardtype :: DaoImpl ::"+commonFilter.getType());
			
			//if(UIUtils.isValidKeyId(commonFilter.getType()))
			  //    condParms +="EMPILLAR="+commonFilter.getType()+";";
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside  opl DAO Impl Cummulative " + paramValues);
			
			
			List<String[]> CumCountList;
			CumCountList = dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.HSE_FN_NEARMISSCUMCOUNT", paramValues);	
			 if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
			 }
		
			return CumCountList	;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public Workbook getNewNearCumCountExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub

        ResultSet rs = null;
    try{
		
		rs =   getNearMissCumulativeCountResultSet(commonFilter1);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		
		return excelUtils.writeToExcel(rs,format, 3,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}
	private ResultSet getNearMissCumulativeCountResultSet(CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValuesDataList(commonFilter1);
		
		return dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_NEARMISSCUMCOUNT", paramValues);
	}
	private List<String> getFilterParamValuesDataList(CommonFilter commonFilter1) {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter1);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	public List<String[]> getNearMissCountList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Before Function");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.GEN_FN_NEARMISSCOUNTRPT", paramValues); 
			CommonMessage.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	public Workbook NearMissCountExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try
		   {
			
			rs =   getNearMissCountExportExcel(commonFilter);
			CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			//condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
			}finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
private ResultSet getNearMissCountExportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonMessage.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("SAP_PC_REPORTS.GEN_FN_NEARMISSCOUNTRPT", paramValues);
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}
@Override
public Workbook getNewNearMissMonthExportToExcel(JSONObject colmodel,
		String format, CommonFilter commonFilter1) throws Exception {
	// TODO Auto-generated method stub

    ResultSet rs = null;
try{
	
	rs =   getNewNearMissMonthResultSet(commonFilter1);
	ExcelUtils excelUtils = new ExcelUtils(colmodel);
	
	return excelUtils.writeToExcel(rs,format, 3,0,0 );
	
   }finally{
	   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
   }
}
private ResultSet getNewNearMissMonthResultSet(CommonFilter commonFilter1) throws Exception {
	// TODO Auto-generated method stub
	List<String> paramValues = getFilterParamValues(commonFilter1);
	
	return dbActionTemplate.dbFunctionCall("SOP_PC_SOP.GEN_FN_NEWNEARMISSMONTHWISERPT", paramValues);
}
@Override
public List<String[]> getSafeactStratificationrpt(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	try{
	
	List<String> paramValues = new ArrayList<String>();
	
	String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			
	
	

	CommonMessage.debugMsg("condParms>>>>"+condParms);
	List<String[]> dataList = null;
	String type=commonFilter.getStatus();
	CommonMessage.debugMsg("type>>>>"+type);
	if(type!=null)
	{
		if(type.equals("UA"))
		{
			condParms+="NMTYPE="+"UA"+";";
			CommonMessage.debugMsg("condParms>>>UA>"+condParms);
			//condParms += "EXCEL="+"NOEXCEL"+";";
			//dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_SAFEACTSTRATIFICATION", paramValues);
		}
		else{
			condParms+="NMTYPE="+"UC"+";";
			CommonMessage.debugMsg("condParms>>UC>>"+condParms);
			//condParms+="NMTYPE=UA"+";";
			//dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_SAFEACTSTRATIFICATION", paramValues);
		}
	}
	
	else{
		condParms+="NMTYPE="+"UA"+";";
		CommonMessage.debugMsg("else condParms>>>UA>"+condParms);

	}
	CommonMessage.debugMsg("ParamValues>>>"+paramValues);
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_SAFEACTSTRATIFICATION", paramValues);

	CommonMessage.debugMsg("Common Params::::::"+paramValues);
	
	CommonMessage.debugMsg("Before Function");
	//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_SAFEACTSTRATIFICATION", paramValues); 
	CommonMessage.debugMsg("Length...."+dataList.size());
	if( commonFilter.getViewClick() == 'Y'){
		String totalCnt = paramValues.get(0); 
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if(  isInteger ){
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		}
	}
	return dataList; 
}
catch (Exception e)
{
	throw new Exception(e.getMessage()); 
	
}
	
}


@Override
public List<String[]> getpiegraph(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	
	try{
	List<String> paramValues = new ArrayList<String>();
	
	String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			
	//paramValues.add(condParms);
	//paramValues.add(commonParams);
	String type=commonFilter.getStatus();
	CommonMessage.debugMsg("type Of Pie in Dao>>>>"+type);
	if(type!=null)
	{
		if(type.equals("UA"))
		{
			condParms+="NMTYPE="+"UA"+";";
			//condParms += "EXCEL="+"NOEXCEL"+";";
			//dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_SAFEACTSTRATIFICATION", paramValues);
		}
		else{
			condParms+="NMTYPE="+"UC"+";";
			//condParms+="NMTYPE=UA"+";";
			//dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_SAFEACTSTRATIFICATION", paramValues);
		}
	}/*else{
		condParms+="NMTYPE="+"UA"+";";
	}*/
	CommonMessage.debugMsg("ParamValues>>>"+paramValues);
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	CommonMessage.debugMsg("Before Function");
	List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_UNSAFEACTPIE", paramValues); 
	CommonMessage.debugMsg("Length...."+dataList.size());
	if( commonFilter.getViewClick() == 'Y'){
		String totalCnt = paramValues.get(0); 
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if(  isInteger ){
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		}
	}
	return dataList;
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		
	}
}
@Override
public Workbook unsafeCond(CommonFilter commonFilter, JSONObject colmodel, String format) throws Exception {
	// TODO Auto-generated method stub

	
		ResultSet rs = null;
		try {

			rs = SafeActStratificationResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue((char) 252 + ""); // Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format, 4, 0, 0);
		/*	rs = SafeActStratificationResultSet(commonFilter);
			ExcelUtils ex = new ExcelUtils(colmodel);
			
			return ex.writeToExcel(rs,format, 3,0,0 );*/

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet SafeActStratificationResultSet(CommonFilter commonFilter) throws Exception {
		//List<String> paramValues = getFilterParamValues(commonFilter);
		try
		{
			CommonMessage.debugMsg("chk grph in dao");
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 CommonMessage.debugMsg("INSIDE DAO IMPL" + commonFilter.getTotal());
		
			 String type=commonFilter.getStatus();
			
			 if(type!=null)
				{
					if(type.equals("UA"))
					{
						condParms+="NMTYPE="+"UA"+";";
						//condParms += "EXCEL="+"NOEXCEL"+";";
						//dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_SAFEACTSTRATIFICATION", paramValues);
					}
					else{
						condParms+="NMTYPE="+"UC"+";";
						//condParms+="NMTYPE=UA"+";";
						//dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_SAFEACTSTRATIFICATION", paramValues);
					}
				}else{
					condParms+="NMTYPE="+"UA"+";";
				}
				CommonMessage.debugMsg("ParamValues>>>"+paramValues);
				paramValues.add(condParms);
				paramValues.add(commonParams);
				
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST1.ADM_FN_SAFEACTSTRATIFICATION", paramValues);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		
	}
	@Override
	public List<String[]> getNewNearmissIdentified(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		    paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;

			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST4_PC_TEST4.GEN_FN_NEWNEARMISSREPT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	public Workbook EmployeeNearMissExcel(CommonFilter commonFilter, JSONObject colModel, String format)
			throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getEmployeeNearmissIdentifiedResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
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
			return excelUtils.writeToExcel(rs,format, 3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getEmployeeNearmissIdentifiedResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValuesIDf(commonFilter);
		return dbActionTemplate.dbFunctionCall("TEST4_PC_TEST4.GEN_FN_NEWNEARMISSREPT", paramValues);
	}
	
	private List<String> getFilterParamValuesIDf(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);		
		return paramValues;
	}

}
